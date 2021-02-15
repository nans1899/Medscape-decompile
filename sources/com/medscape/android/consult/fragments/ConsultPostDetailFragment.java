package com.medscape.android.consult.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.analytics.ClickStreamManager;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.EmojiFilter;
import com.medscape.android.consult.activity.ConsultPostDetailActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.consult.adapters.ConsultMentionablesListAdapter;
import com.medscape.android.consult.adapters.ConsultPostDetailAdapter;
import com.medscape.android.consult.interfaces.ICommentReplySelectedListener;
import com.medscape.android.consult.interfaces.ICommentUpdateListener;
import com.medscape.android.consult.interfaces.IFeedReceivedListener;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.ILoadMoreRepliesListener;
import com.medscape.android.consult.interfaces.IMentionSelectedListener;
import com.medscape.android.consult.interfaces.IMentionTokenListenerByID;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.IPostDetailCommentSelectedListener;
import com.medscape.android.consult.interfaces.IPostReceivedListener;
import com.medscape.android.consult.interfaces.IResponseFilterListener;
import com.medscape.android.consult.interfaces.IStringBuilderCreatedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

public class ConsultPostDetailFragment extends Fragment implements IPostDetailCommentSelectedListener, ICommentReplySelectedListener, ILoadMoreRepliesListener, IResponseFilterListener, ILoadMoreListener, IPostDeletedListener {
    static final String TAG = ConsultPostDetailFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public ConsultPostDetailAdapter mAdapter;
    /* access modifiers changed from: private */
    public int mCommentReplyPosition = -1;
    /* access modifiers changed from: private */
    public ConsultComment mCommentReplyingTo;
    /* access modifiers changed from: private */
    public ConsultFeed mCurrentFeed;
    /* access modifiers changed from: private */
    public String mCurrentMentionQuery = "";
    /* access modifiers changed from: private */
    public int mCurrentSortType = Constants.CONSULT_COMMENT_FILTER_OLDEST;
    /* access modifiers changed from: private */
    public LinearLayoutManager mLayoutManager;
    /* access modifiers changed from: private */
    public RecyclerView mMentionablesList;
    /* access modifiers changed from: private */
    public ConsultMentionablesListAdapter mMentionsAdapter;
    /* access modifiers changed from: private */
    public int mNumberOfMentionsToQueryForPostBody = 0;
    /* access modifiers changed from: private */
    public ConsultPost mPost;
    /* access modifiers changed from: private */
    public View mProgressDialog;
    RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public EditText mResponseEditText;
    /* access modifiers changed from: private */
    public View mRootView;
    /* access modifiers changed from: private */
    public boolean mScrollToFilter = false;
    /* access modifiers changed from: private */
    public Button mSendButton;
    /* access modifiers changed from: private */
    public HashMap<String, List<ConsultUser>> mSpanMap = new HashMap<>();
    /* access modifiers changed from: private */
    public boolean mWaitingForAfterTextChanged = false;

    /* access modifiers changed from: private */
    public boolean isStartPositionWithinSpan(ForegroundColorSpan foregroundColorSpan) {
        return foregroundColorSpan != null;
    }

    /* access modifiers changed from: private */
    public boolean isUserDeletingText(int i, int i2) {
        return i > i2;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_consult_detail, viewGroup, false);
        this.mRootView = inflate;
        EditText editText = (EditText) inflate.findViewById(R.id.response_text);
        this.mResponseEditText = editText;
        editText.setFilters(new InputFilter[]{new EmojiFilter()});
        Button button = (Button) this.mRootView.findViewById(R.id.send_button);
        this.mSendButton = button;
        button.setEnabled(false);
        this.mProgressDialog = this.mRootView.findViewById(R.id.progressBar);
        setUpRecyclerView();
        setUpMentionsRecyclerView();
        addTextChangedListenerForResponse();
        addSendClickListener();
        loadPost();
        return this.mRootView;
    }

    public static ConsultPostDetailFragment newInstance(Bundle bundle) {
        ConsultPostDetailFragment consultPostDetailFragment = new ConsultPostDetailFragment();
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable(Constants.EXTRA_CONSULT_POST);
            if (parcelable instanceof ConsultPost) {
                consultPostDetailFragment.setPost((ConsultPost) parcelable);
            }
            consultPostDetailFragment.setScrollToFilter(bundle.getBoolean(Constants.EXTRA_CONSULT_SCROLL_TO_DETAIL_FILTER));
        }
        return consultPostDetailFragment;
    }

    public void setPost(ConsultPost consultPost) {
        this.mPost = consultPost;
    }

    /* access modifiers changed from: private */
    public void setTitle() {
        ActionBar supportActionBar = ((ConsultPostDetailActivity) getActivity()).getSupportActionBar();
        if (supportActionBar != null) {
            String subject = this.mPost.getSubject();
            if (StringExtensions.isNullOrEmpty(subject)) {
                subject = "Consult";
            }
            supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + subject + "</font>"));
        }
    }

    public void setScrollToFilter(boolean z) {
        this.mScrollToFilter = z;
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) this.mRootView.findViewById(R.id.detail_recycler);
        this.mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        this.mLayoutManager = linearLayoutManager;
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        if (this.mAdapter == null) {
            this.mAdapter = new ConsultPostDetailAdapter(getActivity(), this, this, this, this, this, this);
        }
        this.mAdapter.setPost(this.mPost, this.mScrollToFilter);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }

    private void setUpMentionsRecyclerView() {
        this.mMentionablesList = (RecyclerView) this.mRootView.findViewById(R.id.mentionables);
        this.mMentionablesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (this.mMentionsAdapter == null) {
            this.mMentionsAdapter = new ConsultMentionablesListAdapter(new IMentionSelectedListener() {
                public void onMentionSelected(ConsultUser consultUser, Integer num, int i) {
                    ConsultPostDetailFragment.this.mMentionablesList.setVisibility(8);
                    ConsultPostDetailFragment.this.mRecyclerView.setVisibility(0);
                    String str = "@" + consultUser.getDisplayName();
                    List list = (List) ConsultPostDetailFragment.this.mSpanMap.get(str);
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(consultUser);
                    ConsultPostDetailFragment.this.mSpanMap.put(str, list);
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ConsultPostDetailFragment.this.mResponseEditText.getText());
                    if (spannableStringBuilder.length() > 0 && num.intValue() <= i && i <= spannableStringBuilder.length() && num.intValue() >= 0) {
                        spannableStringBuilder.replace(num.intValue(), i, "");
                    }
                    spannableStringBuilder.insert(num.intValue(), str);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(ConsultPostDetailFragment.this.getResources().getColor(R.color.medscape_blue)), num.intValue(), num.intValue() + str.length(), 33);
                    ConsultPostDetailFragment.this.mResponseEditText.setText(spannableStringBuilder);
                    ConsultPostDetailFragment.this.mResponseEditText.setSelection(num.intValue() + str.length());
                }
            });
        }
        this.mMentionablesList.setAdapter(this.mMentionsAdapter);
    }

    private void loadPost() {
        ConsultDataManager.getInstance(getActivity(), getActivity()).getFullPostForPost(this.mPost, new IPostReceivedListener() {
            public void onPostReceived(ConsultPost consultPost) {
                boolean z;
                if (consultPost != null) {
                    if (ConsultPostDetailFragment.this.mPost.getConsultAssets() == null || consultPost.getConsultAssets() == null || consultPost.getConsultAssets().size() != ConsultPostDetailFragment.this.mPost.getConsultAssets().size()) {
                        z = true;
                    } else {
                        int i = 0;
                        z = true;
                        while (i < ConsultPostDetailFragment.this.mPost.getConsultAssets().size() && (z = ConsultPostDetailFragment.this.mPost.getConsultAssets().get(i).getAssetUrl().equals(consultPost.getConsultAssets().get(i).getAssetUrl()))) {
                            i++;
                        }
                    }
                    displayPost(consultPost, z, true);
                }
            }

            private void displayPost(ConsultPost consultPost, boolean z, boolean z2) {
                ConsultPostDetailFragment.this.setPost(consultPost);
                ConsultPostDetailFragment.this.mPost.setmIsImageRefreshNotRequired(z);
                ConsultPostDetailFragment.this.setTitle();
                ConsultPostDetailFragment.this.mAdapter.setPost(ConsultPostDetailFragment.this.mPost, false);
                if (!ConsultPostDetailFragment.this.mScrollToFilter) {
                    ConsultPostDetailFragment.this.mAdapter.notifyDataSetChanged();
                }
                if (z2) {
                    ConsultPostDetailFragment.this.refreshFeed();
                }
                if (ConsultPostDetailFragment.this.mScrollToFilter) {
                    ConsultPostDetailFragment.this.mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                            ConsultPostDetailFragment.this.mLayoutManager.scrollToPositionWithOffset(2, 0);
                            ConsultPostDetailFragment.this.mRecyclerView.removeOnLayoutChangeListener(this);
                        }
                    });
                }
            }

            public void onPostRequestFailed(final MedscapeException medscapeException) {
                try {
                    displayPost(ConsultPostDetailFragment.this.mPost, false, false);
                    ConsultPostDetailFragment.this.mAdapter.handleLoadCommentsFailure();
                    medscapeException.showSnackBar(ConsultPostDetailFragment.this.mRootView, -2, "OK", new View.OnClickListener() {
                        public void onClick(View view) {
                            medscapeException.dismissSnackBar();
                        }
                    });
                } catch (Exception unused) {
                    Trace.w(ConsultPostDetailFragment.TAG, "Failed to load full post");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void refreshFeed() {
        ConsultDataManager.getInstance(getActivity(), getActivity()).getCommentsForPost(this.mPost, this.mCurrentFeed, this.mCurrentSortType, new IFeedReceivedListener() {
            public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
                ConsultFeed unused = ConsultPostDetailFragment.this.mCurrentFeed = consultFeed;
                ConsultPostDetailFragment.this.mAdapter.setComments(consultFeed);
            }

            public void onFailedToReceiveFeed(MedscapeException medscapeException, int i, String str) {
                try {
                    medscapeException.showAlert(ConsultPostDetailFragment.this.getActivity(), "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }, (String) null, (DialogInterface.OnClickListener) null);
                } catch (Exception unused) {
                    Trace.w(ConsultPostDetailFragment.TAG, "Failed to show error when getting comments");
                }
                ConsultPostDetailFragment.this.mAdapter.handleLoadCommentsFailure();
            }
        });
    }

    /* access modifiers changed from: private */
    public ForegroundColorSpan[] getCurrentSpansForMentions() {
        return (ForegroundColorSpan[]) this.mResponseEditText.getText().getSpans(0, this.mResponseEditText.length(), ForegroundColorSpan.class);
    }

    private void addTextChangedListenerForResponse() {
        EditText editText = this.mResponseEditText;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (!ConsultPostDetailFragment.this.mWaitingForAfterTextChanged && ConsultPostDetailFragment.this.mSpanMap != null && ConsultPostDetailFragment.this.mSpanMap.size() > 0 && ConsultPostDetailFragment.this.isUserDeletingText(i2, i3)) {
                        boolean unused = ConsultPostDetailFragment.this.mWaitingForAfterTextChanged = true;
                        ForegroundColorSpan access$1300 = ConsultPostDetailFragment.this.getSpan(i);
                        if (ConsultPostDetailFragment.this.isStartPositionWithinSpan(access$1300)) {
                            Trace.i(ConsultPostDetailFragment.TAG, "Delete span");
                            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ConsultPostDetailFragment.this.mResponseEditText.getText());
                            String spannableStringBuilder2 = spannableStringBuilder.toString();
                            int spanStart = spannableStringBuilder.getSpanStart(access$1300);
                            int spanEnd = spannableStringBuilder.getSpanEnd(access$1300);
                            String substring = spannableStringBuilder2.substring(spanStart, spanEnd);
                            SpannableStringBuilder replace = spannableStringBuilder.replace(spanStart, spanEnd, "");
                            replace.removeSpan(access$1300);
                            ConsultPostDetailFragment.this.modifySpanMapForDeletedSpan(substring);
                            ConsultPostDetailFragment.this.mResponseEditText.setText(replace);
                            ConsultPostDetailFragment.this.mResponseEditText.setSelection(spanStart);
                        }
                    }
                }

                public void afterTextChanged(Editable editable) {
                    boolean unused = ConsultPostDetailFragment.this.mWaitingForAfterTextChanged = false;
                    if (StringUtil.isNotEmpty(ConsultPostDetailFragment.this.mResponseEditText.getText().toString())) {
                        ConsultPostDetailFragment.this.mSendButton.setTextColor(ConsultPostDetailFragment.this.getResources().getColor(R.color.medscape_blue));
                        ConsultPostDetailFragment.this.mSendButton.setEnabled(true);
                    } else {
                        ConsultPostDetailFragment.this.mSendButton.setTextColor(ConsultPostDetailFragment.this.getResources().getColor(R.color.consult_status));
                        ConsultPostDetailFragment.this.mSendButton.setEnabled(false);
                    }
                    if (!ConsultPostDetailFragment.this.shouldQueryServerForMentions()) {
                        String unused2 = ConsultPostDetailFragment.this.mCurrentMentionQuery = editable.toString();
                        ConsultPostDetailFragment.this.mMentionablesList.setVisibility(8);
                        ConsultPostDetailFragment.this.mRecyclerView.setVisibility(0);
                    } else if (editable != null) {
                        final int access$1800 = ConsultPostDetailFragment.this.getLastPositionOfAtSymbolBeforeCursor();
                        final int length = ConsultPostDetailFragment.this.getValueOfSubstringAfterLastAtSymbolBeforeCursor().length() + access$1800;
                        String unused3 = ConsultPostDetailFragment.this.mCurrentMentionQuery = editable.toString().substring(access$1800 + 1, length);
                        Trace.i(ConsultPostDetailFragment.TAG, "Querying server for user mentions");
                        ConsultDataManager.getInstance(ConsultPostDetailFragment.this.getActivity(), ConsultPostDetailFragment.this.getActivity()).getMentionablesForText(ConsultPostDetailFragment.this.mCurrentMentionQuery, (ConsultFeed) null, new IFeedReceivedListener() {
                            public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
                                if (consultFeed != null && consultFeed.getTotalItems() > 0 && StringUtil.isNotEmpty(ConsultPostDetailFragment.this.mCurrentMentionQuery) && StringUtil.isNotEmpty(str) && ConsultPostDetailFragment.this.mCurrentMentionQuery.equalsIgnoreCase(str)) {
                                    ConsultPostDetailFragment.this.mMentionsAdapter.setData(consultFeed.getFeedItems(), access$1800, length);
                                    ConsultPostDetailFragment.this.mMentionsAdapter.notifyDataSetChanged();
                                    ConsultPostDetailFragment.this.mMentionablesList.setVisibility(0);
                                    ConsultPostDetailFragment.this.mRecyclerView.setVisibility(8);
                                }
                            }

                            public void onFailedToReceiveFeed(MedscapeException medscapeException, int i, String str) {
                                ConsultPostDetailFragment.this.mMentionablesList.setVisibility(8);
                                ConsultPostDetailFragment.this.mRecyclerView.setVisibility(0);
                            }
                        });
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void modifySpanMapForDeletedSpan(String str) {
        if (this.mSpanMap.containsKey(str)) {
            List list = this.mSpanMap.get(str);
            if (list == null) {
                this.mSpanMap.remove(str);
            } else if (list.size() > 1) {
                list.remove(0);
                this.mSpanMap.put(str, list);
            } else {
                this.mSpanMap.remove(str);
            }
        }
    }

    /* access modifiers changed from: private */
    public int getLastPositionOfAtSymbolBeforeCursor() {
        int selectionStart = this.mResponseEditText.getSelectionStart();
        String obj = this.mResponseEditText.getText().toString();
        if (StringUtil.isNotEmpty(obj) && obj.length() >= selectionStart) {
            String substring = obj.substring(0, selectionStart);
            if (substring.contains("@")) {
                return getLastPositionOfAtSymbolInString(substring);
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public String getValueOfSubstringAfterLastAtSymbolBeforeCursor() {
        int selectionStart = this.mResponseEditText.getSelectionStart();
        String obj = this.mResponseEditText.getText().toString();
        if (StringUtil.isNotEmpty(obj) && obj.length() >= selectionStart) {
            String substring = obj.substring(0, selectionStart);
            if (substring.contains("@")) {
                return substring.substring(getLastPositionOfAtSymbolInString(substring));
            }
        }
        return "";
    }

    /* access modifiers changed from: private */
    public boolean shouldQueryServerForMentions() {
        int selectionStart = this.mResponseEditText.getSelectionStart();
        String obj = this.mResponseEditText.getText().toString();
        if (!StringUtil.isNotEmpty(obj) || obj.length() < selectionStart) {
            return false;
        }
        String substring = obj.substring(0, selectionStart);
        if (!substring.contains("@")) {
            return false;
        }
        int lastPositionOfAtSymbolInString = getLastPositionOfAtSymbolInString(substring);
        if (shouldQueryValue(substring.substring(lastPositionOfAtSymbolInString), lastPositionOfAtSymbolInString)) {
            return true;
        }
        return false;
    }

    private boolean shouldQueryValue(String str, int i) {
        return !str.contains("  ") && !str.contains(IOUtils.LINE_SEPARATOR_UNIX) && str.length() > 1 && !isStartPositionASpan(i);
    }

    private boolean isStartPositionASpan(int i) {
        ForegroundColorSpan[] currentSpansForMentions = getCurrentSpansForMentions();
        if (currentSpansForMentions == null || currentSpansForMentions.length <= 0) {
            return false;
        }
        for (ForegroundColorSpan spanStart : currentSpansForMentions) {
            if (this.mResponseEditText.getText().getSpanStart(spanStart) == i) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public ForegroundColorSpan getSpan(int i) {
        ForegroundColorSpan[] currentSpansForMentions = getCurrentSpansForMentions();
        if (currentSpansForMentions != null && currentSpansForMentions.length > 0) {
            for (ForegroundColorSpan foregroundColorSpan : currentSpansForMentions) {
                Editable text = this.mResponseEditText.getText();
                int spanStart = text.getSpanStart(foregroundColorSpan);
                int spanEnd = text.getSpanEnd(foregroundColorSpan);
                Trace.i(TAG, "StartPosition is " + i + " spanStart is " + spanStart + " spanEnd is " + spanEnd);
                if (i >= spanStart && i < spanEnd) {
                    return foregroundColorSpan;
                }
            }
        }
        return null;
    }

    private int getLastPositionOfAtSymbolInString(String str) {
        if (str.contains("@")) {
            return str.lastIndexOf("@");
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public void createPostBodyFromResponseEditText(IStringBuilderCreatedListener iStringBuilderCreatedListener) {
        List list;
        IStringBuilderCreatedListener iStringBuilderCreatedListener2 = iStringBuilderCreatedListener;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mResponseEditText.getText());
        ForegroundColorSpan[] currentSpansForMentions = getCurrentSpansForMentions();
        if (currentSpansForMentions == null || currentSpansForMentions.length <= 0) {
            iStringBuilderCreatedListener2.onStringBuilderCreated(spannableStringBuilder);
            return;
        }
        int i = 0;
        this.mNumberOfMentionsToQueryForPostBody = 0;
        this.mNumberOfMentionsToQueryForPostBody = getNumberOfMentionsToQuery(currentSpansForMentions, spannableStringBuilder);
        int length = currentSpansForMentions.length;
        int i2 = 0;
        while (i2 < length) {
            ForegroundColorSpan foregroundColorSpan = currentSpansForMentions[i2];
            final int spanStart = spannableStringBuilder.getSpanStart(foregroundColorSpan);
            final int spanEnd = spannableStringBuilder.getSpanEnd(foregroundColorSpan);
            final String charSequence = spannableStringBuilder.subSequence(spanStart, spanEnd).toString();
            if (this.mSpanMap.containsKey(charSequence) && (list = this.mSpanMap.get(charSequence)) != null && list.size() > 0 && list.get(i) != null) {
                if (StringUtil.isNotEmpty(((ConsultUser) list.get(i)).getMentionToken())) {
                    spannableStringBuilder.replace(spanStart, spanEnd, ((ConsultUser) list.get(i)).getMentionToken());
                } else if (isAdded() && getActivity() != null) {
                    ConsultDataManager instance = ConsultDataManager.getInstance(getActivity(), getActivity());
                    String userId = ((ConsultUser) list.get(i)).getUserId();
                    final SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
                    AnonymousClass6 r11 = r0;
                    final IStringBuilderCreatedListener iStringBuilderCreatedListener3 = iStringBuilderCreatedListener;
                    AnonymousClass6 r0 = new IMentionTokenListenerByID() {
                        public void onRecievingMentionToken(String str) {
                            if (StringUtil.isNotEmpty(str)) {
                                ConsultPostDetailFragment consultPostDetailFragment = ConsultPostDetailFragment.this;
                                int unused = consultPostDetailFragment.mNumberOfMentionsToQueryForPostBody = consultPostDetailFragment.mNumberOfMentionsToQueryForPostBody - 1;
                                spannableStringBuilder2.replace(spanStart, spanEnd, str);
                                if (ConsultPostDetailFragment.this.mNumberOfMentionsToQueryForPostBody == 0) {
                                    iStringBuilderCreatedListener3.onStringBuilderCreated(spannableStringBuilder2);
                                    return;
                                }
                                return;
                            }
                            onErrorRecievingMentionToken();
                        }

                        public void onErrorRecievingMentionToken() {
                            ConsultDataManager.getInstance(ConsultPostDetailFragment.this.getActivity(), ConsultPostDetailFragment.this.getActivity()).getMentionablesForText(charSequence, (ConsultFeed) null, new IFeedReceivedListener() {
                                public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
                                    int unused = ConsultPostDetailFragment.this.mNumberOfMentionsToQueryForPostBody = ConsultPostDetailFragment.this.mNumberOfMentionsToQueryForPostBody - 1;
                                    if (consultFeed != null && consultFeed.getFeedItems().size() > 0) {
                                        ConsultFeedItem consultFeedItem = consultFeed.getFeedItems().get(0);
                                        if (consultFeedItem instanceof ConsultUser) {
                                            spannableStringBuilder2.replace(spanStart, spanEnd, ((ConsultUser) consultFeedItem).getMentionToken());
                                        }
                                    }
                                    if (ConsultPostDetailFragment.this.mNumberOfMentionsToQueryForPostBody == 0) {
                                        iStringBuilderCreatedListener3.onStringBuilderCreated(spannableStringBuilder2);
                                    }
                                }

                                public void onFailedToReceiveFeed(MedscapeException medscapeException, int i, String str) {
                                    int unused = ConsultPostDetailFragment.this.mNumberOfMentionsToQueryForPostBody = ConsultPostDetailFragment.this.mNumberOfMentionsToQueryForPostBody - 1;
                                    if (ConsultPostDetailFragment.this.mNumberOfMentionsToQueryForPostBody == 0) {
                                        iStringBuilderCreatedListener3.onStringBuilderCreated(spannableStringBuilder2);
                                    }
                                }
                            });
                        }
                    };
                    instance.getMentionableToken(userId, r11);
                }
            }
            i2++;
            i = 0;
        }
        if (this.mNumberOfMentionsToQueryForPostBody == 0) {
            iStringBuilderCreatedListener2.onStringBuilderCreated(spannableStringBuilder);
        }
    }

    private int getNumberOfMentionsToQuery(ForegroundColorSpan[] foregroundColorSpanArr, SpannableStringBuilder spannableStringBuilder) {
        List list;
        if (foregroundColorSpanArr == null || spannableStringBuilder == null || this.mSpanMap == null) {
            return 0;
        }
        int i = 0;
        for (ForegroundColorSpan foregroundColorSpan : foregroundColorSpanArr) {
            if (foregroundColorSpan != null) {
                String charSequence = spannableStringBuilder.subSequence(spannableStringBuilder.getSpanStart(foregroundColorSpan), spannableStringBuilder.getSpanEnd(foregroundColorSpan)).toString();
                if (this.mSpanMap.containsKey(charSequence) && (list = this.mSpanMap.get(charSequence)) != null && list.size() > 0 && list.get(0) != null && !StringUtil.isNotEmpty(((ConsultUser) list.get(0)).getMentionToken())) {
                    i++;
                }
            }
        }
        return i;
    }

    private void addSendClickListener() {
        this.mSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                ConsultPostDetailFragment.this.disableButtonsWhileSubmitting(view);
                ConsultPostDetailFragment.this.mProgressDialog.setVisibility(0);
                AppboyEventsHandler.logDailyEvent(ConsultPostDetailFragment.this.getContext(), AppboyConstants.APPBOY_EVENT_CONSULT_COMMENT, ConsultPostDetailFragment.this.getActivity());
                final ConsultComment consultComment = new ConsultComment();
                ConsultPostDetailFragment.this.createPostBodyFromResponseEditText(new IStringBuilderCreatedListener() {
                    public void onStringBuilderCreated(SpannableStringBuilder spannableStringBuilder) {
                        if (spannableStringBuilder != null) {
                            consultComment.setCommentBody(spannableStringBuilder.toString());
                        }
                        if (ConsultPostDetailFragment.this.mCommentReplyingTo != null) {
                            ConsultDataManager.getInstance(ConsultPostDetailFragment.this.getActivity(), ConsultPostDetailFragment.this.getActivity()).saveCommentReplyForComment(ConsultPostDetailFragment.this.mCommentReplyingTo, consultComment, new ICommentUpdateListener() {
                                public void onCommentUpdated(ConsultComment consultComment) {
                                    OmnitureManager.get().trackModule(ConsultPostDetailFragment.this.getActivity(), "consult", "consult-post-" + ConsultPostDetailFragment.this.mPost.getPostId(), "reply", (Map<String, Object>) null);
                                    LocalBroadcastManager.getInstance(ConsultPostDetailFragment.this.getActivity()).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
                                    ConsultComment unused = ConsultPostDetailFragment.this.mCommentReplyingTo = null;
                                    ConsultPostDetailFragment.this.mProgressDialog.setVisibility(8);
                                    for (ForegroundColorSpan removeSpan : ConsultPostDetailFragment.this.getCurrentSpansForMentions()) {
                                        ConsultPostDetailFragment.this.mResponseEditText.getText().removeSpan(removeSpan);
                                    }
                                    ConsultPostDetailFragment.this.mResponseEditText.setText("");
                                    Util.hideKeyboard(ConsultPostDetailFragment.this.getActivity());
                                    ConsultPostDetailFragment.this.mAdapter.appendReplyToEnd(consultComment, ConsultPostDetailFragment.this.mCommentReplyPosition);
                                    int unused2 = ConsultPostDetailFragment.this.mCommentReplyPosition = -1;
                                    ConsultPostDetailFragment.this.enableButtons(view);
                                }

                                public void onCommentUpdateFailed(MedscapeException medscapeException) {
                                    ConsultPostDetailFragment.this.showPostCommentError(medscapeException);
                                    ConsultPostDetailFragment.this.mProgressDialog.setVisibility(8);
                                    ConsultPostDetailFragment.this.enableButtons(view);
                                }
                            });
                        } else {
                            ConsultDataManager.getInstance(ConsultPostDetailFragment.this.getActivity(), ConsultPostDetailFragment.this.getActivity()).saveCommentForPost(consultComment, ConsultPostDetailFragment.this.mPost, new ICommentUpdateListener() {
                                public void onCommentUpdated(ConsultComment consultComment) {
                                    OmnitureManager.get().trackModule(ConsultPostDetailFragment.this.getActivity(), "consult", "consult-post-" + ConsultPostDetailFragment.this.mPost.getPostId(), "reply", (Map<String, Object>) null);
                                    LocalBroadcastManager.getInstance(ConsultPostDetailFragment.this.getActivity()).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
                                    ConsultPostDetailFragment.this.mProgressDialog.setVisibility(8);
                                    for (ForegroundColorSpan removeSpan : ConsultPostDetailFragment.this.getCurrentSpansForMentions()) {
                                        ConsultPostDetailFragment.this.mResponseEditText.getText().removeSpan(removeSpan);
                                    }
                                    ConsultPostDetailFragment.this.mResponseEditText.setText("");
                                    Util.hideKeyboard(ConsultPostDetailFragment.this.getActivity());
                                    if (ConsultPostDetailFragment.this.mCurrentSortType == 3027) {
                                        ConsultPostDetailFragment.this.mAdapter.appendCommentToEnd(consultComment);
                                    } else {
                                        ConsultPostDetailFragment.this.mAdapter.insertCommentInBeginning(consultComment);
                                    }
                                    ConsultPostDetailFragment.this.enableButtons(view);
                                }

                                public void onCommentUpdateFailed(MedscapeException medscapeException) {
                                    ConsultPostDetailFragment.this.showPostCommentError(medscapeException);
                                    ConsultPostDetailFragment.this.mProgressDialog.setVisibility(8);
                                    ConsultPostDetailFragment.this.enableButtons(view);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void showPostCommentError(MedscapeException medscapeException) {
        try {
            medscapeException.showAlert(getActivity(), "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (String) null, (DialogInterface.OnClickListener) null);
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show error for sending post");
        }
    }

    public void onPostDetailCommentSelected() {
        Util.showKeyboard(getActivity());
        this.mResponseEditText.requestFocus();
    }

    public void onCommentReplySelected(ConsultComment consultComment, ConsultComment consultComment2, int i) {
        String format = String.format("@%s", new Object[]{consultComment.getPoster().getDisplayName()});
        this.mSpanMap.clear();
        ArrayList arrayList = new ArrayList();
        arrayList.add(consultComment.getPoster());
        this.mSpanMap.put(format, arrayList);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(format);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.medscape_blue)), 0, format.length(), 33);
        spannableStringBuilder.append(":");
        Util.showKeyboard(getActivity());
        this.mResponseEditText.setText(spannableStringBuilder);
        this.mResponseEditText.setSelection(spannableStringBuilder.length());
        this.mResponseEditText.requestFocus();
        if (consultComment2 == null) {
            this.mCommentReplyingTo = consultComment;
        } else {
            this.mCommentReplyingTo = consultComment2;
        }
        this.mCommentReplyPosition = i;
    }

    public void onLoadMoreRepliesForComment(final ConsultComment consultComment, final int i) {
        this.mAdapter.showLoadingMoreProgressSpinner(i);
        ConsultDataManager.getInstance(getActivity(), getActivity()).getRepliesForComment(consultComment, consultComment.getRepliesFeed(), new IFeedReceivedListener() {
            public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
                ConsultPostDetailFragment.this.mAdapter.setRepliesForCommentAtPosition(consultComment, consultFeed, i);
            }

            public void onFailedToReceiveFeed(MedscapeException medscapeException, int i, String str) {
                try {
                    medscapeException.showAlert(ConsultPostDetailFragment.this.getActivity(), "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }, (String) null, (DialogInterface.OnClickListener) null);
                } catch (Exception unused) {
                    Trace.w(ConsultPostDetailFragment.TAG, "Failed to show error when getting replies");
                }
                ConsultPostDetailFragment.this.mAdapter.handleLoadRepliesFailure(i);
            }
        });
    }

    public void onResponseFilterSelected(int i) {
        if (i != this.mCurrentSortType) {
            this.mCurrentSortType = i;
            this.mAdapter.clearResultsForSort(this.mPost);
            this.mCurrentFeed = null;
            refreshFeed();
        }
    }

    public void onMoreRequested() {
        this.mAdapter.showLoadingMoreProgressSpinner(-1);
        refreshFeed();
    }

    public void onPostDeleted(int i) {
        getActivity().startActivity(new Intent(getActivity(), ConsultTimelineActivity.class));
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void disableButtonsWhileSubmitting(View view) {
        view.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void enableButtons(View view) {
        view.setEnabled(true);
    }

    public void onResume() {
        super.onResume();
        if (!Util.isOnline(getContext()) && this.mRootView != null) {
            new MedscapeException(getResources().getString(R.string.internet_required)).showSnackBar(this.mRootView, -1);
        }
        if (this.mPost != null && getActivity() != null) {
            String trackPageView = OmnitureManager.get().trackPageView(getActivity(), "consult", "consult", "postdetail", this.mPost.getPostId(), (String) null, (Map<String, Object>) null);
            ((BaseActivity) getActivity()).setCurrentPvid(trackPageView);
            ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
            String postId = this.mPost.getPostId();
            String generateAssetId = chronicleIDUtil.generateAssetId(postId, (String) null, "consult/postdetail/" + this.mPost.getPostId());
            ClickStreamManager clickStreamManager = ClickStreamManager.INSTANCE;
            FragmentActivity activity = getActivity();
            ClickstreamConstants.EventType eventType = ClickstreamConstants.EventType.pageView;
            clickStreamManager.sendEvent(activity, eventType, "consult/postdetail/" + this.mPost.getPostId(), (HashMap<String, String>) null, (Impression[]) null, (String[]) null, trackPageView, generateAssetId);
        }
    }
}

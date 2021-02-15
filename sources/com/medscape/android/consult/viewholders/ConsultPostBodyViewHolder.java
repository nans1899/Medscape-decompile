package com.medscape.android.consult.viewholders;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.BaseMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AndroidPdfViewerActivity;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.consult.activity.ConsultPostActivity;
import com.medscape.android.consult.activity.ConsultProfileActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.consult.adapters.ConsultHorizontalImageAdapter;
import com.medscape.android.consult.interfaces.ICommunityDataReceivedListener;
import com.medscape.android.consult.interfaces.ICommunityRequestCompleteListener;
import com.medscape.android.consult.interfaces.IImageSelectedListener;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.IViewUpdateListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.CustomTypefaceSpan;
import com.medscape.android.util.HtmlFormatter;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.view.PageIndicator;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class ConsultPostBodyViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public static final String TAG = ConsultPostBodyViewHolder.class.getSimpleName();
    private View caseResolved;
    private View caseResolvedImage;
    private View divider;
    View mBodyLayout;
    private View mCMEEligbileView;
    private TextView mChatCount;
    public Activity mContext;
    /* access modifiers changed from: private */
    public IPostDeletedListener mDeletedListener;
    protected TextView mDescriptionView;
    private ProgressBar mDownVoteProgress;
    private TextView mDownVoteView;
    private ForegroundColorSpan mForegroundBlueSpan;
    private ForegroundColorSpan mForegroundGreySpan;
    private IImageSelectedListener mImageSelectedListener;
    private RecyclerView mImageViewer;
    private ConsultHorizontalImageAdapter mImageViewerAdapter;
    /* access modifiers changed from: private */
    public PageIndicator mIndicator;
    private LayoutInflater mInflater;
    private View mOverflowView;
    private TypefaceSpan mRobotoMediumSpan;
    private ViewGroup mTagsLayout;
    protected TextView mTitleView;
    private ProgressBar mUpVoteProgress;
    private TextView mUpVoteView;
    /* access modifiers changed from: private */
    public IViewUpdateListener updateListener;

    private boolean doesViewFitRemainingWidth(int i, int i2, double d) {
        return ((double) (i + i2)) <= d;
    }

    public ConsultPostBodyViewHolder(View view) {
        super(view);
    }

    public ConsultPostBodyViewHolder(View view, Activity activity, IPostDeletedListener iPostDeletedListener) {
        super(view);
        initializeBottomLayout(view, activity, iPostDeletedListener);
        initializeTopLayout(view, activity);
    }

    public void initializeTopLayout(View view, Activity activity) {
        this.mContext = activity;
        this.mInflater = (LayoutInflater) activity.getSystemService("layout_inflater");
        this.mImageViewer = (RecyclerView) view.findViewById(R.id.imageViewer);
        this.mBodyLayout = view.findViewById(R.id.body_layout);
        this.mIndicator = (PageIndicator) view.findViewById(R.id.indicator);
        this.mTitleView = (TextView) view.findViewById(R.id.post_title);
        this.mDescriptionView = (TextView) view.findViewById(R.id.post_description);
        this.mTagsLayout = (ViewGroup) view.findViewById(R.id.tags_layout);
        this.mCMEEligbileView = view.findViewById(R.id.cme_eligible_label);
        this.caseResolved = view.findViewById(R.id.case_resolved_label);
        this.caseResolvedImage = view.findViewById(R.id.case_resolved_checkmark);
        this.divider = view.findViewById(R.id.view_divider);
        new PagerSnapHelper().attachToRecyclerView(this.mImageViewer);
    }

    public void initializeBottomLayout(View view, Activity activity, IPostDeletedListener iPostDeletedListener) {
        this.mContext = activity;
        this.mChatCount = (TextView) view.findViewById(R.id.chat_count);
        this.mOverflowView = view.findViewById(R.id.overflow_icon);
        ImageView imageView = (ImageView) view.findViewById(R.id.comment_icon);
        if (imageView != null) {
            imageView.setColorFilter(Color.argb(255, 85, 85, 85));
        }
        this.mUpVoteProgress = (ProgressBar) view.findViewById(R.id.progress_bar_up_vote);
        this.mDownVoteProgress = (ProgressBar) view.findViewById(R.id.progress_bar_down_vote);
        this.mUpVoteView = (TextView) view.findViewById(R.id.up_vote);
        this.mDownVoteView = (TextView) view.findViewById(R.id.down_vote);
        this.mRobotoMediumSpan = new CustomTypefaceSpan("", Typeface.createFromAsset(this.mContext.getAssets(), "font/Roboto-Medium.ttf"));
        this.mForegroundBlueSpan = new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.medscape_blue));
        this.mForegroundGreySpan = new ForegroundColorSpan(ContextCompat.getColor(this.mContext, R.color.material_grey));
        this.mDeletedListener = iPostDeletedListener;
    }

    public void bindPost(ConsultPost consultPost, int i, IImageSelectedListener iImageSelectedListener, IViewUpdateListener iViewUpdateListener) {
        bindTopLayout(consultPost, iImageSelectedListener, iViewUpdateListener);
        bindBottomLayout(consultPost, i);
    }

    /* access modifiers changed from: protected */
    public void bindTopLayout(ConsultPost consultPost, IImageSelectedListener iImageSelectedListener, IViewUpdateListener iViewUpdateListener) {
        this.updateListener = iViewUpdateListener;
        if (consultPost == null) {
            return;
        }
        if (!consultPost.isLowQualityPost() || consultPost.isLowQualityPostShown()) {
            this.mBodyLayout.setVisibility(0);
            if (consultPost.isShowSponsored()) {
                this.mBodyLayout.setBackgroundColor(this.mContext.getResources().getColor(R.color.consult_sponsored_post));
            } else {
                this.mBodyLayout.setBackgroundColor(this.mContext.getResources().getColor(R.color.white));
            }
            this.mImageSelectedListener = iImageSelectedListener;
            if (StringUtil.isNotEmpty(consultPost.getSubject())) {
                this.mTitleView.setText(HtmlFormatter.removeTrailingLineBreaks(new SpannableStringBuilder(Html.fromHtml(consultPost.getSubject().replace(IOUtils.LINE_SEPARATOR_UNIX, "<br />")))));
            }
            if (StringUtil.isNotEmpty(consultPost.getPostBody())) {
                this.mDescriptionView.setText(HtmlFormatter.removeTrailingLineBreaks(new SpannableStringBuilder(Html.fromHtml(consultPost.getPostBody()))));
                this.mDescriptionView.setMovementMethod(new BaseMovementMethod() {
                    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
                        int indexOf;
                        if (motionEvent.getAction() != 1) {
                            return super.onTouchEvent(textView, spannable, motionEvent);
                        }
                        int x = ((int) motionEvent.getX()) - textView.getTotalPaddingLeft();
                        int y = ((int) motionEvent.getY()) - textView.getTotalPaddingTop();
                        int scrollX = x + textView.getScrollX();
                        int scrollY = y + textView.getScrollY();
                        Layout layout = textView.getLayout();
                        int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), (float) scrollX);
                        URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(offsetForHorizontal, offsetForHorizontal, URLSpan.class);
                        if (uRLSpanArr.length != 0) {
                            SpannableString spannableString = (SpannableString) textView.getText();
                            String charSequence = spannableString.subSequence(spannableString.getSpanStart(uRLSpanArr[0]) + 1, spannableString.getSpanEnd(uRLSpanArr[0])).toString();
                            String url = uRLSpanArr[0].getURL();
                            if (StringUtil.isNotEmpty(url)) {
                                boolean contains = url.contains("/members/");
                                if (!ConsultUtils.isUrlInString(url) || contains) {
                                    if (contains && (indexOf = url.indexOf("/members/")) != -1) {
                                        url = url.substring(indexOf + 9);
                                    }
                                    if (StringUtil.isNotEmpty(url) && StringUtil.isNotEmpty(charSequence)) {
                                        ConsultUser consultUser = new ConsultUser();
                                        consultUser.setUserId(url);
                                        consultUser.setDisplayName(charSequence);
                                        Intent intent = new Intent(ConsultPostBodyViewHolder.this.mContext, ConsultProfileActivity.class);
                                        intent.putExtra(Constants.CONSULT_USER_BUNDLE_KEY, consultUser);
                                        ConsultPostBodyViewHolder.this.mContext.startActivity(intent);
                                    }
                                } else if (!url.endsWith(".pdf")) {
                                    WebviewUtil.Companion.launchPlainWebView(ConsultPostBodyViewHolder.this.mContext, url, "", "", "none", "consult", "", 0, false);
                                } else {
                                    Intent intent2 = new Intent(ConsultPostBodyViewHolder.this.mContext, AndroidPdfViewerActivity.class);
                                    intent2.putExtra("pdf_url", url);
                                    ConsultPostBodyViewHolder.this.mContext.startActivity(intent2);
                                }
                            }
                        }
                        return true;
                    }
                });
            }
            if (this.mImageViewer != null) {
                if (consultPost.getConsultAssets() == null || consultPost.getConsultAssets().size() <= 0) {
                    this.mImageViewer.setVisibility(8);
                    this.mIndicator.setVisibility(8);
                } else {
                    ConsultHorizontalImageAdapter consultHorizontalImageAdapter = this.mImageViewerAdapter;
                    if (consultHorizontalImageAdapter == null || consultHorizontalImageAdapter.areNotSame(consultPost.getConsultAssets())) {
                        this.mImageViewer.setVisibility(0);
                        this.mIndicator.setVisibility(8);
                        this.mImageViewerAdapter = new ConsultHorizontalImageAdapter(consultPost, consultPost.getConsultAssets(), this.mImageSelectedListener);
                        RecyclerView recyclerView = this.mImageViewer;
                        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 0, false));
                        this.mImageViewer.setAdapter(this.mImageViewerAdapter);
                        setUpPageIndicatorForPost(consultPost);
                    }
                }
            }
            if (this.mCMEEligbileView != null) {
                if (consultPost.isCMEEligible()) {
                    this.mCMEEligbileView.setVisibility(0);
                } else {
                    this.mCMEEligbileView.setVisibility(8);
                }
            }
            checkForCaseResolvedStatus(consultPost);
            checkIfDividerShouldBeShown(consultPost);
            addTags(consultPost);
            return;
        }
        this.mBodyLayout.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void bindBottomLayout(ConsultPost consultPost, int i) {
        if (consultPost != null) {
            updateUpVoteView(consultPost);
            updateDownVoteView(consultPost);
            addUpVoteClickListener(consultPost);
            addDownVoteClickListener(consultPost);
            updateVoteProgress(consultPost);
            addOverflowListener(consultPost, i);
            this.mChatCount.setText(String.format("%s", new Object[]{ConsultUtils.getFormattedConsultCount(consultPost.getCommentCount())}));
        }
    }

    private void checkIfDividerShouldBeShown(ConsultPost consultPost) {
        if (consultPost.isCaseResolved() && consultPost.isCMEEligible()) {
            this.divider.setVisibility(0);
        }
    }

    private void checkForCaseResolvedStatus(ConsultPost consultPost) {
        if (this.caseResolved != null && this.caseResolvedImage != null) {
            if (consultPost.isCaseResolved()) {
                this.caseResolved.setVisibility(0);
                this.caseResolvedImage.setVisibility(0);
                return;
            }
            this.caseResolved.setVisibility(8);
            this.caseResolvedImage.setVisibility(8);
        }
    }

    private void updateVoteProgress(ConsultPost consultPost) {
        if (consultPost.isUpVoteProgress()) {
            this.mUpVoteProgress.setVisibility(0);
            this.mUpVoteView.setVisibility(4);
            this.mDownVoteView.setEnabled(false);
        } else {
            this.mUpVoteProgress.setVisibility(8);
            this.mUpVoteView.setVisibility(0);
            this.mDownVoteView.setEnabled(true);
            this.mUpVoteView.setEnabled(true);
        }
        if (consultPost.isDownVoteProgress()) {
            this.mDownVoteProgress.setVisibility(0);
            this.mDownVoteView.setVisibility(4);
            this.mUpVoteView.setEnabled(false);
            return;
        }
        this.mDownVoteProgress.setVisibility(8);
        this.mDownVoteView.setVisibility(0);
        this.mUpVoteView.setEnabled(true);
        this.mDownVoteView.setEnabled(true);
    }

    private void setUpPageIndicatorForPost(ConsultPost consultPost) {
        if (consultPost.getConsultAssets() == null || consultPost.getConsultAssets().size() <= 1) {
            this.mIndicator.setVisibility(8);
            return;
        }
        this.mIndicator.setVisibility(0);
        this.mIndicator.setTotalPages(consultPost.getConsultAssets().size());
        this.mIndicator.setSelectedPage(0);
        this.mImageViewer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    ConsultPostBodyViewHolder.this.mIndicator.setSelectedPage(((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition());
                }
            }
        });
    }

    private void addTags(ConsultPost consultPost) {
        List<String> tags;
        this.mTagsLayout.removeAllViews();
        if (consultPost != null && (tags = consultPost.getTags()) != null && tags.size() > 0) {
            LinearLayout linearLayout = new LinearLayout(this.mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(0);
            Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            double width = ((double) defaultDisplay.getWidth()) - (((double) displayMetrics.density) * 40.0d);
            int i = 0;
            for (String next : tags) {
                if (StringUtil.isNotEmpty(next) && !StringUtil.onlyNumbers(next)) {
                    View inflate = this.mInflater.inflate(R.layout.consult_tag_view, (ViewGroup) null, false);
                    View findViewById = inflate.findViewById(R.id.tag_wrapper);
                    ((TextView) findViewById.findViewById(R.id.tag_text)).setText(Html.fromHtml(next));
                    findViewById.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
                    findViewById.measure(0, 0);
                    if (!doesViewFitRemainingWidth(findViewById.getMeasuredWidth() + 15, i, width)) {
                        this.mTagsLayout.addView(linearLayout);
                        linearLayout = new LinearLayout(this.mContext);
                        linearLayout.setLayoutParams(layoutParams);
                        linearLayout.setOrientation(0);
                        i = 0;
                    }
                    linearLayout.addView(inflate);
                    i = i + findViewById.getMeasuredWidth() + 15 + 10;
                    inflate.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            TextView textView = (TextView) view.findViewById(R.id.tag_text);
                            if (textView != null) {
                                String charSequence = textView.getText().toString();
                                if (StringUtil.isNotEmpty(charSequence)) {
                                    Intent intent = new Intent(ConsultPostBodyViewHolder.this.mContext, ConsultTimelineActivity.class);
                                    intent.putExtra(Constants.EXTRA_CONSULT_CLICKED_TAG, charSequence);
                                    ConsultPostBodyViewHolder.this.mContext.startActivity(intent);
                                }
                            }
                        }
                    });
                }
            }
            this.mTagsLayout.addView(linearLayout);
        }
    }

    private double[] getSizeOfAsset(String str) {
        double[] dArr = new double[2];
        try {
            if (StringUtil.isNotEmpty(str)) {
                Matcher matcher = Pattern.compile("size(\\d+)x(\\d+)x").matcher(str);
                if (matcher.find()) {
                    String group = matcher.group(0);
                    if (StringUtil.isNotEmpty(group) && group.contains("size")) {
                        try {
                            dArr[0] = (double) Integer.parseInt(group.substring(4, group.indexOf("x")));
                            dArr[1] = (double) Integer.parseInt(group.substring(group.indexOf("x") + 1, group.lastIndexOf("x")));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to get size for asset");
        }
        return dArr;
    }

    private void addOverflowListener(final ConsultPost consultPost, final int i) {
        View view = this.mOverflowView;
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(ConsultPostBodyViewHolder.this.mContext, view);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()) {
                                case R.id.delete:
                                    ConsultPostBodyViewHolder.this.deletePost(consultPost, i);
                                    return true;
                                case R.id.edit:
                                    ConsultPostBodyViewHolder.this.editPost(consultPost);
                                    return true;
                                case R.id.follow_post:
                                    ConsultPostBodyViewHolder.this.setFollowPost(consultPost);
                                    return true;
                                case R.id.inappropriate:
                                    ConsultPostBodyViewHolder.this.reportAbuse(consultPost);
                                    return true;
                                default:
                                    return true;
                            }
                        }
                    });
                    if (ConsultDataManager.getInstance(ConsultPostBodyViewHolder.this.mContext, (Activity) null).isCurrentUser(consultPost.getPoster())) {
                        popupMenu.inflate(R.menu.consult_owner_overflow_menu);
                    } else {
                        popupMenu.inflate(R.menu.consult_overflow_menu);
                        MenuItem findItem = popupMenu.getMenu().findItem(R.id.follow_post);
                        if (findItem != null) {
                            if (consultPost.getFollowState() == 3020) {
                                findItem.setTitle(ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.consult_unfollow_post));
                            } else {
                                findItem.setTitle(ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.consult_follow_post));
                            }
                        }
                    }
                    popupMenu.show();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void setFollowPost(final ConsultPost consultPost) {
        int followState = consultPost.getFollowState();
        final int i = Constants.FOLLOWING_STATE_FOLLOWING;
        if (followState == 3020) {
            i = Constants.FOLLOWING_STATE_NOT_FOLLOWING;
        }
        ConsultDataManager.getInstance(this.mContext, (Activity) null).setFollowPost(consultPost, i, new ICommunityRequestCompleteListener() {
            public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                Trace.i(ConsultPostBodyViewHolder.TAG, "Successfully toggled follow state");
                consultPost.setFollowState(i);
            }

            public void onCommunityRequestFailed(MedscapeException medscapeException) {
                Trace.i(ConsultPostBodyViewHolder.TAG, "Toggle follow state failed");
                try {
                    if (ConsultPostBodyViewHolder.this.mContext != null && (ConsultPostBodyViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert(ConsultPostBodyViewHolder.this.mContext, ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to show alert toggle follow state failed");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void deletePost(ConsultPost consultPost, final int i) {
        if (consultPost != null) {
            ConsultDataManager.getInstance(this.mContext, (Activity) null).deletePost(consultPost, new ICommunityRequestCompleteListener() {
                public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                    Trace.i(ConsultPostBodyViewHolder.TAG, "Successfully deleted post");
                    if (ConsultPostBodyViewHolder.this.mDeletedListener != null) {
                        ConsultPostBodyViewHolder.this.mDeletedListener.onPostDeleted(i);
                    }
                }

                public void onCommunityRequestFailed(MedscapeException medscapeException) {
                    Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to delete post");
                    try {
                        if (ConsultPostBodyViewHolder.this.mContext != null && (ConsultPostBodyViewHolder.this.mContext instanceof Activity)) {
                            medscapeException.showAlert(ConsultPostBodyViewHolder.this.mContext, ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }, (String) null, (DialogInterface.OnClickListener) null);
                        }
                    } catch (Exception unused) {
                        Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to show alert that delete post failed");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void editPost(ConsultPost consultPost) {
        if (consultPost != null) {
            Intent intent = new Intent(this.mContext, ConsultPostActivity.class);
            intent.putExtra(Constants.EXTRA_CONSULT_EDIT_POST, consultPost);
            this.mContext.startActivity(intent);
        }
    }

    /* access modifiers changed from: private */
    public void reportAbuse(ConsultPost consultPost) {
        if (consultPost != null) {
            ConsultDataManager.getInstance(this.mContext, (Activity) null).reportAbuseForContentId(consultPost.getContentId(), consultPost.getContentTypeId(), new ICommunityDataReceivedListener() {
                public void onCommunityDataReceived(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        ConsultPostBodyViewHolder.this.showReportAbuseSuccess();
                    } else {
                        onFailedToReceiveCommunityData(new MedscapeException(ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                    }
                }

                public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                    try {
                        if (ConsultPostBodyViewHolder.this.mContext != null && (ConsultPostBodyViewHolder.this.mContext instanceof Activity)) {
                            medscapeException.showAlert(ConsultPostBodyViewHolder.this.mContext, ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }, (String) null, (DialogInterface.OnClickListener) null);
                        }
                    } catch (Exception unused) {
                        Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to show error liking post");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showReportAbuseSuccess() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
            builder.setTitle(this.mContext.getResources().getString(R.string.consult_report_abuse_thanks));
            builder.setMessage(this.mContext.getResources().getString(R.string.consult_report_abuse_success));
            builder.setPositiveButton(this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show disclaimer for consult photo editing");
        }
    }

    private void addUpVoteClickListener(final ConsultPost consultPost) {
        TextView textView = this.mUpVoteView;
        if (textView != null && consultPost != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    consultPost.setUpVoteProgress(true);
                    if (!consultPost.isUpVoted()) {
                        OmnitureManager omnitureManager = OmnitureManager.get();
                        Activity activity = ConsultPostBodyViewHolder.this.mContext;
                        omnitureManager.trackModule(activity, "consult", "consult-upv-p" + consultPost.getPostId(), "upv", (Map<String, Object>) null);
                        ConsultPostBodyViewHolder.this.applyUpVoteToPost(consultPost);
                    } else {
                        ConsultPostBodyViewHolder.this.removeUpVoteFromPost(consultPost);
                    }
                    if (ConsultPostBodyViewHolder.this.updateListener != null) {
                        ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                    }
                }
            });
        }
    }

    private void addDownVoteClickListener(final ConsultPost consultPost) {
        TextView textView = this.mDownVoteView;
        if (textView != null && consultPost != null) {
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    consultPost.setDownVoteProgress(true);
                    if (!consultPost.isDownVoted()) {
                        OmnitureManager omnitureManager = OmnitureManager.get();
                        Activity activity = ConsultPostBodyViewHolder.this.mContext;
                        omnitureManager.trackModule(activity, "consult", "consult-dnv-p" + consultPost.getPostId(), "dnv", (Map<String, Object>) null);
                        ConsultPostBodyViewHolder.this.applyDownVoteToPost(consultPost);
                    } else {
                        ConsultPostBodyViewHolder.this.removeDownVoteFromPost(consultPost);
                    }
                    if (ConsultPostBodyViewHolder.this.updateListener != null) {
                        ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateUpVoteView(ConsultPost consultPost) {
        if (this.mUpVoteView != null && consultPost != null) {
            String string = this.mContext.getString(R.string.up_vote);
            String voteLabelWithCount = ConsultUtils.getVoteLabelWithCount(string, consultPost.getUpVoteCount());
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(voteLabelWithCount);
            spannableStringBuilder.setSpan(consultPost.isUpVoted() ? this.mForegroundBlueSpan : this.mForegroundGreySpan, 0, string.length(), 18);
            spannableStringBuilder.setSpan(this.mRobotoMediumSpan, 0, voteLabelWithCount.length(), 18);
            this.mUpVoteView.setText(spannableStringBuilder);
        }
    }

    /* access modifiers changed from: private */
    public void updateDownVoteView(ConsultPost consultPost) {
        if (this.mDownVoteView != null && consultPost != null) {
            String string = this.mContext.getString(R.string.down_vote);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
            spannableStringBuilder.setSpan(consultPost.isDownVoted() ? this.mForegroundBlueSpan : this.mForegroundGreySpan, 0, string.length(), 18);
            spannableStringBuilder.setSpan(this.mRobotoMediumSpan, 0, string.length(), 18);
            this.mDownVoteView.setText(spannableStringBuilder);
        }
    }

    /* access modifiers changed from: private */
    public void applyUpVoteToPost(final ConsultPost consultPost) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).upVoteContent(consultPost.getContentId(), consultPost.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                if (jSONObject != null) {
                    if (ConsultPostBodyViewHolder.this.mContext != null && !(ConsultPostBodyViewHolder.this.mContext instanceof ConsultTimelineActivity)) {
                        LocalBroadcastManager.getInstance(ConsultPostBodyViewHolder.this.mContext).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
                    }
                    consultPost.setIsUpVoted(true);
                    ConsultPost consultPost = consultPost;
                    consultPost.setUpVoteCount(consultPost.getUpVoteCount() + 1);
                    ConsultPostBodyViewHolder.this.updateUpVoteView(consultPost);
                    if (consultPost.isDownVoted()) {
                        consultPost.setIsDownVoted(false);
                        ConsultPost consultPost2 = consultPost;
                        consultPost2.setDownVoteCount(consultPost2.getDownVoteCount() - 1);
                        ConsultPostBodyViewHolder.this.updateDownVoteView(consultPost);
                    }
                } else {
                    onFailedToReceiveCommunityData(new MedscapeException(ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                }
                consultPost.setUpVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                consultPost.setUpVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
                try {
                    if (ConsultPostBodyViewHolder.this.mContext != null && (ConsultPostBodyViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert(ConsultPostBodyViewHolder.this.mContext, ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to show error liking post");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void removeUpVoteFromPost(final ConsultPost consultPost) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).deleteUpVoteContent(consultPost.getContentId(), consultPost.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                if (jSONObject == null || !jSONObject.optBoolean("data")) {
                    onFailedToReceiveCommunityData(new MedscapeException(ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                } else {
                    if (ConsultPostBodyViewHolder.this.mContext != null && !(ConsultPostBodyViewHolder.this.mContext instanceof ConsultTimelineActivity)) {
                        LocalBroadcastManager.getInstance(ConsultPostBodyViewHolder.this.mContext).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
                    }
                    consultPost.setIsUpVoted(false);
                    ConsultPost consultPost = consultPost;
                    consultPost.setUpVoteCount(consultPost.getUpVoteCount() - 1);
                    ConsultPostBodyViewHolder.this.updateUpVoteView(consultPost);
                }
                consultPost.setUpVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                consultPost.setUpVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
                try {
                    if (ConsultPostBodyViewHolder.this.mContext != null && (ConsultPostBodyViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert(ConsultPostBodyViewHolder.this.mContext, ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to show error liking post");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void applyDownVoteToPost(final ConsultPost consultPost) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).downVoteContent(consultPost.getContentId(), consultPost.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                if (jSONObject != null) {
                    if (ConsultPostBodyViewHolder.this.mContext != null && !(ConsultPostBodyViewHolder.this.mContext instanceof ConsultTimelineActivity)) {
                        LocalBroadcastManager.getInstance(ConsultPostBodyViewHolder.this.mContext).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
                    }
                    consultPost.setIsDownVoted(true);
                    ConsultPost consultPost = consultPost;
                    consultPost.setDownVoteCount(consultPost.getDownVoteCount() + 1);
                    ConsultPostBodyViewHolder.this.updateDownVoteView(consultPost);
                    if (consultPost.isUpVoted()) {
                        consultPost.setIsUpVoted(false);
                        ConsultPost consultPost2 = consultPost;
                        consultPost2.setUpVoteCount(consultPost2.getUpVoteCount() - 1);
                        ConsultPostBodyViewHolder.this.updateUpVoteView(consultPost);
                    }
                } else {
                    onFailedToReceiveCommunityData(new MedscapeException(ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                }
                consultPost.setDownVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                consultPost.setDownVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
                try {
                    if (ConsultPostBodyViewHolder.this.mContext != null && (ConsultPostBodyViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert(ConsultPostBodyViewHolder.this.mContext, ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to show error liking post");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void removeDownVoteFromPost(final ConsultPost consultPost) {
        ConsultDataManager.getInstance(this.mContext, (Activity) null).deleteDownVoteContent(consultPost.getContentId(), consultPost.getContentTypeId(), new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                if (jSONObject == null || !jSONObject.optBoolean("data")) {
                    onFailedToReceiveCommunityData(new MedscapeException(ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_message_title), ConsultPostBodyViewHolder.this.mContext.getString(R.string.consult_error_missing_contentId)));
                } else {
                    if (ConsultPostBodyViewHolder.this.mContext != null && !(ConsultPostBodyViewHolder.this.mContext instanceof ConsultTimelineActivity)) {
                        LocalBroadcastManager.getInstance(ConsultPostBodyViewHolder.this.mContext).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
                    }
                    consultPost.setIsDownVoted(false);
                    ConsultPost consultPost = consultPost;
                    consultPost.setDownVoteCount(consultPost.getDownVoteCount() - 1);
                    ConsultPostBodyViewHolder.this.updateDownVoteView(consultPost);
                }
                consultPost.setDownVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                consultPost.setDownVoteProgress(false);
                if (ConsultPostBodyViewHolder.this.updateListener != null) {
                    ConsultPostBodyViewHolder.this.updateListener.onViewUpdated();
                }
                try {
                    if (ConsultPostBodyViewHolder.this.mContext != null && (ConsultPostBodyViewHolder.this.mContext instanceof Activity)) {
                        medscapeException.showAlert(ConsultPostBodyViewHolder.this.mContext, ConsultPostBodyViewHolder.this.mContext.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, (String) null, (DialogInterface.OnClickListener) null);
                    }
                } catch (Exception unused) {
                    Trace.w(ConsultPostBodyViewHolder.TAG, "Failed to show error liking post");
                }
            }
        });
    }
}

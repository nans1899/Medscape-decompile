package com.medscape.android.consult.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.exifinterface.media.ExifInterface;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.EmojiFilter;
import com.medscape.android.consult.adapters.ConsultMentionablesListAdapter;
import com.medscape.android.consult.interfaces.IFeedReceivedListener;
import com.medscape.android.consult.interfaces.IFileCreatedListener;
import com.medscape.android.consult.interfaces.IMentionSelectedListener;
import com.medscape.android.consult.interfaces.IPostUploadedListener;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.ConsultAsset;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.tasks.CreateFileForBitMapTask;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.medscape.android.util.media.PhotoUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

public class ConsultPostActivity extends BaseActivity {
    private static final int ID_REQUEST_STORAGE = 55;
    private static final int MAX_BODY_LENGTH = 3000;
    private static final int MAX_TITLE_LENGTH = 150;
    /* access modifiers changed from: private */
    public static final String TAG = ConsultPostActivity.class.getSimpleName();
    /* access modifiers changed from: private */
    public ConsultMentionablesListAdapter mAdapter;
    private View mAddTagsLayout;
    /* access modifiers changed from: private */
    public EditText mAdditionalDetails;
    /* access modifiers changed from: private */
    public TextView mAdditionalDetailsTitle;
    private String mConsultFeedTag;
    private int mConsultFeedType;
    /* access modifiers changed from: private */
    public View mCurrentImageView;
    /* access modifiers changed from: private */
    public String mCurrentMentionQuery = "";
    /* access modifiers changed from: private */
    public ImageView mFifthImage;
    /* access modifiers changed from: private */
    public View mFifthProgressBar;
    /* access modifiers changed from: private */
    public ImageView mFirstImage;
    /* access modifiers changed from: private */
    public View mFirstProgressBar;
    /* access modifiers changed from: private */
    public ImageView mFourthImage;
    /* access modifiers changed from: private */
    public View mFourthProgressBar;
    private int mImageThumbnailHeight = 0;
    /* access modifiers changed from: private */
    public Map<ImageView, Boolean> mImageViewStatus = new HashMap();
    private LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public boolean mIsUpdate = false;
    /* access modifiers changed from: private */
    public View mLayoutBelowAdditionalDetails;
    /* access modifiers changed from: private */
    public MedscapeException mMedscapeException;
    /* access modifiers changed from: private */
    public RecyclerView mMentionablesList;
    private Map<ForegroundColorSpan, String> mMissingMentionMap = new HashMap();
    /* access modifiers changed from: private */
    public TextView mPostDetailsCharacterCount;
    private String mPostId;
    private EditText mPostTitle;
    private TextView mPostTitleCharacterCount;
    /* access modifiers changed from: private */
    public ProgressBar mProgress;
    /* access modifiers changed from: private */
    public ScrollView mScrollView;
    /* access modifiers changed from: private */
    public ImageView mSecondImage;
    /* access modifiers changed from: private */
    public View mSecondProgressBar;
    /* access modifiers changed from: private */
    public HashMap<String, List<ConsultUser>> mSpanMap = new HashMap<>();
    /* access modifiers changed from: private */
    public Button mSubmitButton;
    /* access modifiers changed from: private */
    public ArrayList<String> mTags = new ArrayList<>();
    private ViewGroup mTagsLayout;
    private TextView mTagsTitle;
    /* access modifiers changed from: private */
    public ImageView mThirdImage;
    /* access modifiers changed from: private */
    public View mThirdProgressBar;
    /* access modifiers changed from: private */
    public boolean mWaitingForAfterTextChanged = false;

    private boolean doesViewFitRemainingWidth(int i, int i2, double d) {
        return ((double) (i + i2)) <= d;
    }

    /* access modifiers changed from: private */
    public boolean isStartPositionWithinSpan(ForegroundColorSpan foregroundColorSpan) {
        return foregroundColorSpan != null;
    }

    /* access modifiers changed from: private */
    public boolean isUserDeletingText(int i, int i2) {
        return i > i2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Bundle extras;
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_consult_post);
        setActionBarTitle(false);
        setActionBar();
        this.mScrollView = (ScrollView) findViewById(R.id.main_scroll);
        this.mInflater = (LayoutInflater) getSystemService("layout_inflater");
        this.mLayoutBelowAdditionalDetails = findViewById(R.id.layout_below_addition_details);
        this.mTagsLayout = (ViewGroup) findViewById(R.id.tags_layout);
        this.mPostTitle = (EditText) findViewById(R.id.post_title);
        this.mImageViewStatus = new HashMap();
        this.mPostTitle.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() == R.id.post_title) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((motionEvent.getAction() & 255) == 1) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        this.mPostTitle.setFilters(new InputFilter[]{new EmojiFilter(), new InputFilter.LengthFilter(150)});
        this.mAdditionalDetailsTitle = (TextView) findViewById(R.id.addition_details_label);
        EditText editText = (EditText) findViewById(R.id.post_additional_details);
        this.mAdditionalDetails = editText;
        editText.setFilters(new InputFilter[]{new EmojiFilter(), new InputFilter.LengthFilter(3000)});
        this.mAdditionalDetails.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                boolean canScrollVertically = ConsultPostActivity.this.mAdditionalDetails.canScrollVertically(1);
                boolean canScrollVertically2 = ConsultPostActivity.this.mAdditionalDetails.canScrollVertically(-1);
                if (!canScrollVertically && !canScrollVertically2) {
                    return false;
                }
                ConsultPostActivity.this.mScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        this.mAdditionalDetails.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    ConsultPostActivity.this.mScrollView.smoothScrollTo(0, ConsultPostActivity.this.mAdditionalDetailsTitle.getTop());
                }
            }
        });
        this.mPostTitleCharacterCount = (TextView) findViewById(R.id.character_count);
        this.mPostDetailsCharacterCount = (TextView) findViewById(R.id.details_character_count);
        this.mTagsTitle = (TextView) findViewById(R.id.tags_title);
        this.mFirstImage = (ImageView) findViewById(R.id.first_image);
        this.mSecondImage = (ImageView) findViewById(R.id.second_image);
        this.mThirdImage = (ImageView) findViewById(R.id.third_image);
        this.mFourthImage = (ImageView) findViewById(R.id.fourth_image);
        this.mFifthImage = (ImageView) findViewById(R.id.fifth_image);
        this.mFirstProgressBar = findViewById(R.id.progressBarFirst);
        this.mSecondProgressBar = findViewById(R.id.progressBarSecond);
        this.mThirdProgressBar = findViewById(R.id.progressBarThird);
        this.mFourthProgressBar = findViewById(R.id.progressBarFourth);
        this.mFifthProgressBar = findViewById(R.id.progressBarFifth);
        this.mAddTagsLayout = findViewById(R.id.add_tags_layout);
        this.mSubmitButton = (Button) findViewById(R.id.submit_button);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.white_progress);
        this.mProgress = progressBar;
        if (progressBar != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(-1, PorterDuff.Mode.MULTIPLY);
        }
        resizeImageViews();
        updatePostTitleCharacterCount();
        updatePostDetailsCharacterCount();
        addTextChangeListenerForPostTitle();
        addTextChangeListenerForAdditionalDetails();
        addTagsClickedListener();
        setUpRecyclerView();
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            this.mConsultFeedType = extras.getInt(Constants.EXTRA_CONSULT_TIMELINE_FEED_TYPE);
            this.mConsultFeedTag = extras.getString(Constants.EXTRA_CONSULT_TIMELINE_FEED_TAG);
            Parcelable parcelable = extras.getParcelable(Constants.EXTRA_CONSULT_EDIT_POST);
            if (parcelable instanceof ConsultPost) {
                this.mIsUpdate = true;
                updateWithPost((ConsultPost) parcelable);
            }
        }
    }

    private void updateWithPost(ConsultPost consultPost) {
        if (consultPost != null) {
            setActionBarTitle(true);
            setActionBar();
            this.mPostId = consultPost.getPostId();
            String subject = consultPost.getSubject();
            if (StringUtil.isNotEmpty(subject)) {
                this.mPostTitle.setText(Html.fromHtml(subject));
            }
            String postBody = consultPost.getPostBody();
            if (StringUtil.isNotEmpty(postBody)) {
                this.mAdditionalDetails.setText(Html.fromHtml(postBody));
            }
            recreateSpanMap(postBody);
            updateTagsWithPost(consultPost);
            List<ConsultAsset> consultAssets = consultPost.getConsultAssets();
            if (consultAssets != null && !consultAssets.isEmpty()) {
                updateImageViewsWithPost(consultAssets);
            }
        }
    }

    private void recreateSpanMap(String str) {
        if (str.contains("members")) {
            String substring = str.substring(str.indexOf("members/") + 8);
            String substring2 = substring.substring(0, substring.indexOf("\""));
            String substring3 = substring.substring(substring.indexOf("user-profile\">") + 14);
            String substring4 = substring3.substring(0, substring3.indexOf("</a>"));
            Trace.e("taest", "Test");
            ConsultUser consultUser = new ConsultUser();
            consultUser.setDisplayName(substring4);
            consultUser.setUserId(substring2);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mAdditionalDetails.getText());
            int indexOf = spannableStringBuilder.toString().indexOf(substring4);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.medscape_blue)), indexOf, substring4.length() + indexOf, 33);
            this.mAdditionalDetails.setText(spannableStringBuilder);
            List list = this.mSpanMap.get(substring4);
            if (list == null) {
                list = new ArrayList();
            }
            list.add(consultUser);
            this.mSpanMap.put(substring4, list);
            recreateSpanMap(substring3);
        }
    }

    private void updateTagsWithPost(ConsultPost consultPost) {
        List<String> tags;
        if (consultPost != null && (tags = consultPost.getTags()) != null && !tags.isEmpty()) {
            this.mTags.addAll(tags);
            displayTags(this.mTags);
            updateTagsTitle();
        }
    }

    private void updateImageViewsWithPost(List<ConsultAsset> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ConsultAsset consultAsset = list.get(i);
                if (consultAsset != null) {
                    String assetUrl = consultAsset.getAssetUrl();
                    if (StringUtil.isNotEmpty(assetUrl)) {
                        ImageView imageViewForPosition = getImageViewForPosition(i);
                        GlideApp.with((View) imageViewForPosition).load(assetUrl).placeholder((int) R.drawable.icon_uploadimage).into(imageViewForPosition);
                        this.mImageViewStatus.put(imageViewForPosition, true);
                    }
                }
            }
        }
    }

    private void setUpRecyclerView() {
        this.mMentionablesList = (RecyclerView) findViewById(R.id.mentionables);
        this.mMentionablesList.setLayoutManager(new LinearLayoutManager(this));
        if (this.mAdapter == null) {
            this.mAdapter = new ConsultMentionablesListAdapter(new IMentionSelectedListener() {
                public void onMentionSelected(ConsultUser consultUser, Integer num, int i) {
                    ConsultPostActivity.this.mMentionablesList.setVisibility(8);
                    ConsultPostActivity.this.mLayoutBelowAdditionalDetails.setVisibility(0);
                    String str = "@" + consultUser.getDisplayName();
                    List list = (List) ConsultPostActivity.this.mSpanMap.get(str);
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(consultUser);
                    ConsultPostActivity.this.mSpanMap.put(str, list);
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ConsultPostActivity.this.mAdditionalDetails.getText());
                    spannableStringBuilder.replace(num.intValue(), i, "");
                    spannableStringBuilder.insert(num.intValue(), str);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(ConsultPostActivity.this.getResources().getColor(R.color.medscape_blue)), num.intValue(), num.intValue() + str.length(), 33);
                    if (spannableStringBuilder.toString().length() > 3000) {
                        ConsultPostActivity.this.mAdditionalDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(spannableStringBuilder.toString().length())});
                    }
                    ConsultPostActivity.this.mAdditionalDetails.setText(spannableStringBuilder);
                    ConsultPostActivity.this.mAdditionalDetails.setSelection(num.intValue() + str.length());
                }
            });
        }
        this.mMentionablesList.setAdapter(this.mAdapter);
    }

    private ImageView getImageViewForPosition(int i) {
        if (i == 0) {
            return this.mFirstImage;
        }
        if (i == 1) {
            return this.mSecondImage;
        }
        if (i == 2) {
            return this.mThirdImage;
        }
        if (i == 3) {
            return this.mFourthImage;
        }
        if (i == 4) {
            return this.mFifthImage;
        }
        return null;
    }

    private void setActionBarTitle(boolean z) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            return;
        }
        if (z) {
            supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.consult_edit_post_label) + "</font>"));
            return;
        }
        supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + getResources().getString(R.string.consult_post_label) + "</font>"));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.consult_post_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332 && menuItem.getItemId() != R.id.action_post_cancel) {
            return true;
        }
        handleClose();
        return true;
    }

    private void handleClose() {
        if (!hasUnsavedChanges()) {
            finish();
        } else {
            showConfirmDiscardAlert();
        }
    }

    private void showConfirmDiscardAlert() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getResources().getString(R.string.consult_confirm_discard_post_title));
            builder.setMessage(getResources().getString(R.string.consult_confirm_discard_post_message));
            builder.setNegativeButton(getResources().getString(R.string.consult_confirm_keep), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.consult_confirm_discard), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ConsultPostActivity.this.finish();
                }
            });
            builder.show();
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show discard message alert");
        }
    }

    private boolean hasUnsavedChanges() {
        EditText editText = this.mPostTitle;
        boolean z = (editText == null || editText.getText() == null || !StringUtil.isNotEmpty(this.mPostTitle.getText().toString().trim())) ? false : true;
        EditText editText2 = this.mAdditionalDetails;
        boolean z2 = (editText2 == null || editText2.getText() == null || !StringUtil.isNotEmpty(this.mAdditionalDetails.getText().toString().trim())) ? false : true;
        ArrayList<String> arrayList = this.mTags;
        boolean z3 = arrayList != null && arrayList.size() > 0;
        boolean z4 = isImageSet(this.mFirstImage) || isImageSet(this.mSecondImage) || isImageSet(this.mThirdImage) || isImageSet(this.mFourthImage) || isImageSet(this.mFifthImage);
        if (z || z2 || z3 || z4) {
            return true;
        }
        return false;
    }

    public void onBackPressed() {
        handleClose();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Util.hideKeyboard(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mIsUpdate) {
            this.mPvid = OmnitureManager.get().trackPageView(this, "consult", "consult", "editpost", (String) null, (String) null, (Map<String, Object>) null);
        } else {
            this.mPvid = OmnitureManager.get().trackPageView(this, "consult", "consult", "newpost", (String) null, (String) null, (Map<String, Object>) null);
        }
    }

    private void resizeImageViews() {
        this.mFirstImage.measure(0, 0);
        this.mImageThumbnailHeight = this.mFirstImage.getMeasuredHeight();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.mImageThumbnailHeight);
        layoutParams.addRule(13);
        this.mFirstImage.setLayoutParams(layoutParams);
        this.mSecondImage.setLayoutParams(layoutParams);
        this.mThirdImage.setLayoutParams(layoutParams);
        this.mFourthImage.setLayoutParams(layoutParams);
        this.mFifthImage.setLayoutParams(layoutParams);
    }

    private void addTextChangeListenerForPostTitle() {
        EditText editText = this.mPostTitle;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    ConsultPostActivity.this.updatePostTitleCharacterCount();
                }
            });
        }
    }

    private void addTextChangeListenerForAdditionalDetails() {
        EditText editText = this.mAdditionalDetails;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() {
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (!ConsultPostActivity.this.mWaitingForAfterTextChanged && ConsultPostActivity.this.mSpanMap != null && ConsultPostActivity.this.mSpanMap.size() > 0 && ConsultPostActivity.this.isUserDeletingText(i2, i3)) {
                        boolean unused = ConsultPostActivity.this.mWaitingForAfterTextChanged = true;
                        ForegroundColorSpan access$900 = ConsultPostActivity.this.getSpan(i);
                        if (ConsultPostActivity.this.isStartPositionWithinSpan(access$900)) {
                            Trace.i(ConsultPostActivity.TAG, "Delete span");
                            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ConsultPostActivity.this.mAdditionalDetails.getText());
                            String spannableStringBuilder2 = spannableStringBuilder.toString();
                            int spanStart = spannableStringBuilder.getSpanStart(access$900);
                            int spanEnd = spannableStringBuilder.getSpanEnd(access$900);
                            String substring = spannableStringBuilder2.substring(spanStart, spanEnd);
                            SpannableStringBuilder replace = spannableStringBuilder.replace(spanStart, spanEnd, "");
                            replace.removeSpan(access$900);
                            ConsultPostActivity.this.modifySpanMapForDeletedSpan(substring);
                            ConsultPostActivity.this.mAdditionalDetails.setText(replace);
                            ConsultPostActivity.this.mAdditionalDetails.setSelection(spanStart);
                            if (replace.toString().length() <= 3000) {
                                ConsultPostActivity.this.mAdditionalDetails.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3000)});
                            }
                            TextView access$1300 = ConsultPostActivity.this.mPostDetailsCharacterCount;
                            ConsultPostActivity consultPostActivity = ConsultPostActivity.this;
                            access$1300.setText(consultPostActivity.getString(R.string.consult_post_detail_char_count, new Object[]{replace.toString().length() + ""}));
                        }
                    }
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    ConsultPostActivity.this.updatePostDetailsCharacterCount();
                }

                public void afterTextChanged(Editable editable) {
                    boolean unused = ConsultPostActivity.this.mWaitingForAfterTextChanged = false;
                    if (ConsultPostActivity.this.shouldQueryServerForMentions()) {
                        final int access$1600 = ConsultPostActivity.this.getLastPositionOfAtSymbolBeforeCursor();
                        final int length = ConsultPostActivity.this.getValueOfSubstringAfterLastAtSymbolBeforeCursor().length() + access$1600;
                        String unused2 = ConsultPostActivity.this.mCurrentMentionQuery = editable.toString().substring(access$1600 + 1, length);
                        Trace.i(ConsultPostActivity.TAG, "Querying server for user mentions");
                        ConsultPostActivity consultPostActivity = ConsultPostActivity.this;
                        ConsultDataManager.getInstance(consultPostActivity, consultPostActivity).getMentionablesForText(ConsultPostActivity.this.mCurrentMentionQuery, (ConsultFeed) null, new IFeedReceivedListener() {
                            public void onFeedReceived(ConsultFeed consultFeed, int i, String str) {
                                if (consultFeed != null && consultFeed.getTotalItems() > 0 && StringUtil.isNotEmpty(ConsultPostActivity.this.mCurrentMentionQuery) && StringUtil.isNotEmpty(str) && ConsultPostActivity.this.mCurrentMentionQuery.equalsIgnoreCase(str)) {
                                    ConsultPostActivity.this.mAdapter.setData(consultFeed.getFeedItems(), access$1600, length);
                                    ConsultPostActivity.this.mAdapter.notifyDataSetChanged();
                                    ConsultPostActivity.this.mMentionablesList.setVisibility(0);
                                    ConsultPostActivity.this.mLayoutBelowAdditionalDetails.setVisibility(8);
                                }
                            }

                            public void onFailedToReceiveFeed(MedscapeException medscapeException, int i, String str) {
                                ConsultPostActivity.this.mMentionablesList.setVisibility(8);
                                ConsultPostActivity.this.mLayoutBelowAdditionalDetails.setVisibility(0);
                            }
                        });
                        return;
                    }
                    String unused3 = ConsultPostActivity.this.mCurrentMentionQuery = editable.toString();
                    ConsultPostActivity.this.mMentionablesList.setVisibility(8);
                    ConsultPostActivity.this.mLayoutBelowAdditionalDetails.setVisibility(0);
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
        int selectionStart = this.mAdditionalDetails.getSelectionStart();
        String obj = this.mAdditionalDetails.getText().toString();
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
        int selectionStart = this.mAdditionalDetails.getSelectionStart();
        String obj = this.mAdditionalDetails.getText().toString();
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
        int selectionStart = this.mAdditionalDetails.getSelectionStart();
        String obj = this.mAdditionalDetails.getText().toString();
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
            if (this.mAdditionalDetails.getText().getSpanStart(spanStart) == i) {
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
                Editable text = this.mAdditionalDetails.getText();
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

    private ForegroundColorSpan[] getCurrentSpansForMentions() {
        return (ForegroundColorSpan[]) this.mAdditionalDetails.getText().getSpans(0, this.mAdditionalDetails.length(), ForegroundColorSpan.class);
    }

    private void checkSubmitButtonVisibility() {
        if (this.mPostTitle.getText().toString().isEmpty() || this.mAdditionalDetails.getText().toString().isEmpty()) {
            this.mSubmitButton.setAlpha(0.5f);
            this.mSubmitButton.setEnabled(false);
            return;
        }
        this.mSubmitButton.setAlpha(1.0f);
        this.mSubmitButton.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public void updatePostTitleCharacterCount() {
        TextView textView = this.mPostTitleCharacterCount;
        if (textView != null) {
            String string = getResources().getString(R.string.consult_post_title_char_count);
            textView.setText(String.format(string, new Object[]{this.mPostTitle.getText().length() + ""}));
            checkSubmitButtonVisibility();
        }
    }

    /* access modifiers changed from: private */
    public void updatePostDetailsCharacterCount() {
        TextView textView = this.mPostDetailsCharacterCount;
        if (textView != null) {
            textView.setText(getString(R.string.consult_post_detail_char_count, new Object[]{this.mAdditionalDetails.getText().length() + ""}));
            checkSubmitButtonVisibility();
        }
    }

    private void addTagsClickedListener() {
        this.mAddTagsLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OmnitureManager.get().markModule("consult-tags", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
                Intent intent = new Intent(ConsultPostActivity.this, ConsultTagSelectionActivity.class);
                if (ConsultPostActivity.this.mTags != null) {
                    intent.putStringArrayListExtra(Constants.CONSULT_SELECTED_TAGS, ConsultPostActivity.this.mTags);
                }
                ConsultPostActivity.this.startActivityForResult(intent, 4002);
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateTagsTitle() {
        ArrayList<String> arrayList = this.mTags;
        if (arrayList == null || arrayList.size() == 0) {
            this.mTagsTitle.setText(getResources().getString(R.string.consult_post_add_tag));
            return;
        }
        TextView textView = this.mTagsTitle;
        String string = getResources().getString(R.string.consult_post_add_tags);
        textView.setText(String.format(string, new Object[]{this.mTags.size() + ""}));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 4002) {
            if (intent != null) {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(Constants.CONSULT_SELECTED_TAGS);
                this.mTags = stringArrayListExtra;
                displayTags(stringArrayListExtra);
                updateTagsTitle();
            }
        } else if (i == 4003 || i == 4004 || i == 4005 || i == 4017 || i == 4018) {
            handleImageCaptureResult(i2, i);
        } else if (i == 4006 || i == 4007 || i == 4008 || i == 4019 || i == 4020) {
            handleImageChooseResult(i2, i, intent);
        } else if (i == 4009 || i == 4010 || i == 4011 || i == 4021 || i == 4022) {
            Bitmap bitmap = null;
            try {
                FileInputStream openFileInput = openFileInput(intent.getStringExtra(Constants.EXTRA_CONSULT_POST_BITMAP));
                bitmap = BitmapFactory.decodeStream(openFileInput);
                openFileInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ImageView imageViewForRequestCode = getImageViewForRequestCode(i);
            if (imageViewForRequestCode != null) {
                setBitmapForImageView(bitmap, imageViewForRequestCode);
            }
        }
    }

    private ImageView getImageViewForRequestCode(int i) {
        if (i == 4009) {
            return this.mFirstImage;
        }
        if (i == 4010) {
            return this.mSecondImage;
        }
        if (i == 4011) {
            return this.mThirdImage;
        }
        if (i == 4021) {
            return this.mFourthImage;
        }
        if (i == 4022) {
            return this.mFifthImage;
        }
        return null;
    }

    private void setBitmapForImageView(final Bitmap bitmap, final ImageView imageView) {
        imageView.setImageDrawable((Drawable) null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.mImageThumbnailHeight);
        layoutParams.addRule(13);
        imageView.setLayoutParams(layoutParams);
        imageView.post(new Runnable() {
            public void run() {
                imageView.setImageBitmap(bitmap);
                ConsultPostActivity.this.mImageViewStatus.put(imageView, true);
                imageView.requestLayout();
            }
        });
    }

    /* access modifiers changed from: private */
    public void displayTags(ArrayList<String> arrayList) {
        this.mTagsLayout.removeAllViews();
        if (arrayList != null && arrayList.size() > 0) {
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(0);
            Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            double width = ((double) defaultDisplay.getWidth()) - (((double) displayMetrics.density) * 80.0d);
            Iterator<String> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                View inflate = this.mInflater.inflate(R.layout.consult_post_tag_view, (ViewGroup) null, false);
                View findViewById = inflate.findViewById(R.id.tag_wrapper);
                ((TextView) findViewById.findViewById(R.id.tag_text)).setText(Html.fromHtml(it.next()));
                findViewById.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
                findViewById.measure(0, 0);
                if (!doesViewFitRemainingWidth(findViewById.getMeasuredWidth() + 15, i, width)) {
                    this.mTagsLayout.addView(linearLayout);
                    linearLayout = new LinearLayout(this);
                    linearLayout.setLayoutParams(layoutParams);
                    linearLayout.setOrientation(0);
                    i = 0;
                }
                linearLayout.addView(inflate);
                i = i + findViewById.getMeasuredWidth() + 15 + 10;
                inflate.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        TextView textView;
                        if (ConsultPostActivity.this.mTags != null && (textView = (TextView) view.findViewById(R.id.tag_text)) != null) {
                            ConsultPostActivity.this.mTags.remove(textView.getText().toString());
                            ConsultPostActivity consultPostActivity = ConsultPostActivity.this;
                            consultPostActivity.displayTags(consultPostActivity.mTags);
                            ConsultPostActivity.this.updateTagsTitle();
                        }
                    }
                });
            }
            this.mTagsLayout.addView(linearLayout);
        }
    }

    public void showAddOrEditImageAlert(View view) {
        if (view != null && (view instanceof ImageView)) {
            ImageView imageView = (ImageView) view;
            if (isImageSet(imageView)) {
                showEditImageAlert(imageView);
            } else {
                showAddImageAlert(imageView);
            }
        }
    }

    private void showEditImageAlert(final View view) {
        try {
            OmnitureManager.get().trackModule(this, "consult", "consult-editphoto", getImageViewPosition(view), (Map<String, Object>) null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.dialog_edit_title));
            builder.setNegativeButton(getResources().getString(R.string.dialog_edit_title), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (view == ConsultPostActivity.this.mFirstImage) {
                        ConsultPostActivity.this.mFirstProgressBar.setVisibility(0);
                    } else if (view == ConsultPostActivity.this.mSecondImage) {
                        ConsultPostActivity.this.mSecondProgressBar.setVisibility(0);
                    } else if (view == ConsultPostActivity.this.mThirdImage) {
                        ConsultPostActivity.this.mThirdProgressBar.setVisibility(0);
                    } else if (view == ConsultPostActivity.this.mFourthImage) {
                        ConsultPostActivity.this.mFourthProgressBar.setVisibility(0);
                    } else if (view == ConsultPostActivity.this.mFifthImage) {
                        ConsultPostActivity.this.mFifthProgressBar.setVisibility(0);
                    }
                    new CreateFileForBitMapTask(ConsultPostActivity.this, ((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap(), new IFileCreatedListener() {
                        public void onFileCreated(String str) {
                            if (StringUtil.isNotEmpty(str)) {
                                Intent intent = new Intent(ConsultPostActivity.this, ConsultEditPictureActivity.class);
                                int i = Constants.REQUEST_CODE_PICTURE_EDIT_FIRST;
                                if (view == ConsultPostActivity.this.mFirstImage) {
                                    ConsultPostActivity.this.mFirstProgressBar.setVisibility(8);
                                } else if (view == ConsultPostActivity.this.mSecondImage) {
                                    i = Constants.REQUEST_CODE_PICTURE_EDIT_SECOND;
                                    ConsultPostActivity.this.mSecondProgressBar.setVisibility(8);
                                } else if (view == ConsultPostActivity.this.mThirdImage) {
                                    i = Constants.REQUEST_CODE_PICTURE_EDIT_THIRD;
                                    ConsultPostActivity.this.mThirdProgressBar.setVisibility(8);
                                } else if (view == ConsultPostActivity.this.mFourthImage) {
                                    i = Constants.REQUEST_CODE_PICTURE_EDIT_FOURTH;
                                    ConsultPostActivity.this.mFourthProgressBar.setVisibility(8);
                                } else if (view == ConsultPostActivity.this.mFifthImage) {
                                    ConsultPostActivity.this.mFifthProgressBar.setVisibility(8);
                                }
                                intent.putExtra(Constants.EXTRA_CONSULT_POST_BITMAP, str);
                                ConsultPostActivity.this.startActivityForResult(intent, i);
                                return;
                            }
                            Trace.w(ConsultPostActivity.TAG, "Failed to send bitmap back to post");
                            new MedscapeException(ConsultPostActivity.this.getResources().getString(R.string.failed_to_load_image_for_edit)).showToast(ConsultPostActivity.this, 1);
                        }
                    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.dialog_edit_delete), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    View view = view;
                    if (view instanceof ImageView) {
                        ((ImageView) view).setImageDrawable(ConsultPostActivity.this.getResources().getDrawable(R.drawable.icon_uploadimage));
                        ConsultPostActivity.this.mImageViewStatus.put((ImageView) view, false);
                    }
                }
            });
            builder.show();
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show disclaimer for consult photo editing");
        }
    }

    private void showAddImageAlert(final View view) {
        OmnitureManager.get().trackModule(this, "consult", "consult-addphoto", getImageViewPosition(view), (Map<String, Object>) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.add_image_for_post));
        builder.setPositiveButton(getString(R.string.take_a_photo), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ConsultPostActivity.this.isFinishing()) {
                    return;
                }
                if (PhotoUtil.hasCamera(ConsultPostActivity.this)) {
                    int i2 = 4003;
                    if (view == ConsultPostActivity.this.mSecondImage) {
                        i2 = Constants.REQUEST_CODE_PICTURE_CAPTURE_SECOND;
                    } else if (view == ConsultPostActivity.this.mThirdImage) {
                        i2 = Constants.REQUEST_CODE_PICTURE_CAPTURE_THIRD;
                    } else if (view == ConsultPostActivity.this.mFourthImage) {
                        i2 = Constants.REQUEST_CODE_PICTURE_CAPTURE_FOURTH;
                    } else if (view == ConsultPostActivity.this.mFifthImage) {
                        i2 = Constants.REQUEST_CODE_PICTURE_CAPTURE_FIFTH;
                    }
                    PhotoUtil.takePhoto(ConsultPostActivity.this, i2);
                    return;
                }
                try {
                    new MedscapeException(ConsultPostActivity.this.getString(R.string.no_camera_error_title), ConsultPostActivity.this.getString(R.string.no_camera_error)).showAlert(ConsultPostActivity.this, "OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }, (String) null, (DialogInterface.OnClickListener) null);
                } catch (Exception unused) {
                    Trace.w(ConsultPostActivity.TAG, "Failed to show error for no camera");
                }
            }
        });
        builder.setNegativeButton(getString(R.string.choose_from_library), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                View unused = ConsultPostActivity.this.mCurrentImageView = view;
                if (Build.VERSION.SDK_INT < 23 || PermissionChecker.checkSelfPermission(ConsultPostActivity.this, "android.permission.READ_EXTERNAL_STORAGE") == 0) {
                    ConsultPostActivity.this.launchPhotoPicker();
                } else {
                    ActivityCompat.requestPermissions(ConsultPostActivity.this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, 55);
                }
            }
        });
        final AlertDialog create = builder.create();
        Util.adjustAlertDialogueButtonSize(create);
        if (!isFinishing()) {
            runOnUiThread(new Runnable() {
                public void run() {
                    try {
                        Trace.i(ConsultPostActivity.TAG, "Showing profile image upload alert");
                        create.show();
                    } catch (Exception unused) {
                        Trace.w(ConsultPostActivity.TAG, "Failed to show profile image upload alert");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void launchPhotoPicker() {
        if (this.mCurrentImageView != null) {
            try {
                Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                if (!isFinishing()) {
                    int i = Constants.REQUEST_CODE_PICTURE_CHOOSE_FIRST;
                    if (this.mCurrentImageView == this.mSecondImage) {
                        i = Constants.REQUEST_CODE_PICTURE_CHOOSE_SECOND;
                    } else if (this.mCurrentImageView == this.mThirdImage) {
                        i = Constants.REQUEST_CODE_PICTURE_CHOOSE_THIRD;
                    } else if (this.mCurrentImageView == this.mFourthImage) {
                        i = Constants.REQUEST_CODE_PICTURE_CHOOSE_FOURTH;
                    } else if (this.mCurrentImageView == this.mFifthImage) {
                        i = Constants.REQUEST_CODE_PICTURE_CHOOSE_FIFTH;
                    }
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, i);
                    } else {
                        new MedscapeException(getResources().getString(R.string.no_app_found_load_image)).showToast(this, 1);
                    }
                }
            } catch (Throwable unused) {
                new MedscapeException(getString(R.string.failed_to_load_image)).showToast(this, 1);
            }
        }
    }

    private void handleImageCaptureResult(int i, int i2) {
        if (i == -1) {
            Intent intent = new Intent(this, ConsultEditPictureActivity.class);
            int i3 = Constants.REQUEST_CODE_PICTURE_EDIT_FIRST;
            if (i2 == 4004) {
                i3 = Constants.REQUEST_CODE_PICTURE_EDIT_SECOND;
            } else if (i2 == 4005) {
                i3 = Constants.REQUEST_CODE_PICTURE_EDIT_THIRD;
            } else if (i2 == 4017) {
                i3 = Constants.REQUEST_CODE_PICTURE_EDIT_FOURTH;
            } else if (i2 == 4018) {
                i3 = Constants.REQUEST_CODE_PICTURE_EDIT_FIFTH;
            }
            intent.putExtra(Constants.EXTRA_CONSULT_IMAGE_FROM_CAPTURE, true);
            startActivityForResult(intent, i3);
            return;
        }
        PhotoUtil.clearRecentPhotoLocation();
    }

    private void handleImageChooseResult(int i, int i2, Intent intent) {
        if (i == -1) {
            String filePathForImageFromGallery = PhotoUtil.getFilePathForImageFromGallery(this, intent);
            if (StringUtil.isNotEmpty(filePathForImageFromGallery)) {
                File file = new File(filePathForImageFromGallery);
                Intent intent2 = new Intent(this, ConsultEditPictureActivity.class);
                intent2.putExtra(Constants.EXTRA_CONSULT_IMAGE_PATH, file.getAbsolutePath());
                int i3 = Constants.REQUEST_CODE_PICTURE_EDIT_FIRST;
                if (i2 == 4007) {
                    i3 = Constants.REQUEST_CODE_PICTURE_EDIT_SECOND;
                } else if (i2 == 4008) {
                    i3 = Constants.REQUEST_CODE_PICTURE_EDIT_THIRD;
                } else if (i2 == 4019) {
                    i3 = Constants.REQUEST_CODE_PICTURE_EDIT_FOURTH;
                } else if (i2 == 4020) {
                    i3 = Constants.REQUEST_CODE_PICTURE_EDIT_FIFTH;
                }
                startActivityForResult(intent2, i3);
                return;
            }
            PhotoUtil.clearRecentPhotoLocation();
            return;
        }
        PhotoUtil.clearRecentPhotoLocation();
    }

    private SpannableStringBuilder createPostBodyFromAdditionalDetails() {
        List list;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mAdditionalDetails.getText());
        ForegroundColorSpan[] currentSpansForMentions = getCurrentSpansForMentions();
        if (currentSpansForMentions != null && currentSpansForMentions.length > 0) {
            for (ForegroundColorSpan foregroundColorSpan : currentSpansForMentions) {
                int spanStart = spannableStringBuilder.getSpanStart(foregroundColorSpan);
                int spanEnd = spannableStringBuilder.getSpanEnd(foregroundColorSpan);
                String charSequence = spannableStringBuilder.subSequence(spanStart, spanEnd).toString();
                if (this.mSpanMap.containsKey(charSequence) && (list = this.mSpanMap.get(charSequence)) != null && list.size() > 0) {
                    if (StringUtil.isNotEmpty(((ConsultUser) list.get(0)).getMentionToken())) {
                        spannableStringBuilder.replace(spanStart, spanEnd, ((ConsultUser) list.get(0)).getMentionToken());
                    } else {
                        this.mMissingMentionMap.put(foregroundColorSpan, ((ConsultUser) list.get(0)).getUserId());
                    }
                }
            }
        }
        return spannableStringBuilder;
    }

    public void onSubmitClick(View view) {
        MedscapeException medscapeException = this.mMedscapeException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        if (validatePost()) {
            disableButtonsWhileSubmitting();
            this.mSubmitButton.setText("");
            this.mProgress.setVisibility(0);
            ConsultPost consultPost = new ConsultPost();
            consultPost.setSubject(this.mPostTitle.getText().toString());
            consultPost.setTags(this.mTags);
            consultPost.setPostId(this.mPostId);
            SpannableStringBuilder createPostBodyFromAdditionalDetails = createPostBodyFromAdditionalDetails();
            if (createPostBodyFromAdditionalDetails != null) {
                consultPost.setPostBody(createPostBodyFromAdditionalDetails.toString());
            }
            List<ConsultAsset> assetsFromImageViews = getAssetsFromImageViews();
            if (assetsFromImageViews != null && !assetsFromImageViews.isEmpty()) {
                consultPost.setConsultAssets(assetsFromImageViews);
            }
            AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_CONSULT_POST, this);
            publishPost(consultPost, createPostBodyFromAdditionalDetails);
        }
    }

    private void publishPost(ConsultPost consultPost, SpannableStringBuilder spannableStringBuilder) {
        ConsultDataManager.getInstance(this, this).sendPostToServer(consultPost, this.mIsUpdate, this.mMissingMentionMap, spannableStringBuilder, new IPostUploadedListener() {
            public void onPostSentToServer(ConsultPost consultPost) {
                if (ConsultPostActivity.this.mIsUpdate) {
                    OmnitureManager.get().trackModule(ConsultPostActivity.this, "consult", "consult-editpost", "edit", (Map<String, Object>) null);
                    LocalBroadcastManager.getInstance(ConsultPostActivity.this).sendBroadcast(new Intent(Constants.CONSULT_TIMELINE_CHANGED_UPDATEACTION));
                    ConsultPostActivity.this.setResult(0);
                } else if (ConsultPostActivity.this.shouldShowOnCurrentTimeLineScreen(consultPost)) {
                    Intent intent = new Intent();
                    intent.putExtra(Constants.EXTRA_CONSULT_POST_UPLOADED, consultPost);
                    ConsultPostActivity.this.setResult(-1, intent);
                }
                if (!ConsultPostActivity.this.mIsUpdate) {
                    OmnitureManager.get().trackModule(ConsultPostActivity.this, "consult", "consult-createpost", "create", (Map<String, Object>) null);
                }
                ConsultPostActivity.this.finish();
            }

            public void onPostFailed(MedscapeException medscapeException) {
                ConsultPostActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        ConsultPostActivity.this.mSubmitButton.setText(ConsultPostActivity.this.getResources().getString(R.string.consult_post_submit));
                        ConsultPostActivity.this.mProgress.setVisibility(8);
                        ConsultPostActivity.this.enableButtons();
                    }
                });
                MedscapeException unused = ConsultPostActivity.this.mMedscapeException = medscapeException;
                ConsultPostActivity.this.mMedscapeException.showSnackBar(ConsultPostActivity.this.mScrollView, -2, ConsultPostActivity.this.getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new View.OnClickListener() {
                    public void onClick(View view) {
                        ConsultPostActivity.this.mMedscapeException.dismissSnackBar();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean shouldShowOnCurrentTimeLineScreen(ConsultPost consultPost) {
        List<String> tags;
        if (consultPost != null) {
            int i = this.mConsultFeedType;
            if (i == 3004 || i == 3002) {
                return true;
            }
            if (i == 3003 && StringUtil.isNotEmpty(this.mConsultFeedTag) && (tags = consultPost.getTags()) != null && tags.size() > 0) {
                for (String equalsIgnoreCase : tags) {
                    if (equalsIgnoreCase.equalsIgnoreCase(this.mConsultFeedTag)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<ConsultAsset> getAssetsFromImageViews() {
        ConsultAsset assetFromImageView;
        ConsultAsset assetFromImageView2;
        ConsultAsset assetFromImageView3;
        ConsultAsset assetFromImageView4;
        ConsultAsset assetFromImageView5;
        ArrayList arrayList = new ArrayList();
        if (isImageSet(this.mFirstImage) && (assetFromImageView5 = getAssetFromImageView(this.mFirstImage)) != null) {
            arrayList.add(assetFromImageView5);
        }
        if (isImageSet(this.mSecondImage) && (assetFromImageView4 = getAssetFromImageView(this.mSecondImage)) != null) {
            arrayList.add(assetFromImageView4);
        }
        if (isImageSet(this.mThirdImage) && (assetFromImageView3 = getAssetFromImageView(this.mThirdImage)) != null) {
            arrayList.add(assetFromImageView3);
        }
        if (isImageSet(this.mFourthImage) && (assetFromImageView2 = getAssetFromImageView(this.mFourthImage)) != null) {
            arrayList.add(assetFromImageView2);
        }
        if (isImageSet(this.mFifthImage) && (assetFromImageView = getAssetFromImageView(this.mFifthImage)) != null) {
            arrayList.add(assetFromImageView);
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r1.mImageViewStatus;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isImageSet(android.widget.ImageView r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x001c
            java.util.Map<android.widget.ImageView, java.lang.Boolean> r0 = r1.mImageViewStatus
            if (r0 == 0) goto L_0x001c
            boolean r0 = r0.containsKey(r2)
            if (r0 == 0) goto L_0x001c
            java.util.Map<android.widget.ImageView, java.lang.Boolean> r0 = r1.mImageViewStatus
            java.lang.Object r2 = r0.get(r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x001c
            r2 = 1
            goto L_0x001d
        L_0x001c:
            r2 = 0
        L_0x001d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.activity.ConsultPostActivity.isImageSet(android.widget.ImageView):boolean");
    }

    private ConsultAsset getAssetFromImageView(ImageView imageView) {
        if (imageView == null) {
            return null;
        }
        ConsultAsset consultAsset = new ConsultAsset();
        consultAsset.setBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());
        return consultAsset;
    }

    private boolean validatePost() {
        boolean z;
        String obj = this.mPostTitle.getText().toString();
        String obj2 = this.mAdditionalDetails.getText().toString();
        String string = getResources().getString(R.string.consult_error_post_no_title);
        boolean z2 = false;
        if (!StringUtil.isNotEmpty(obj) || !StringUtil.isNotEmpty(obj2)) {
            if (!StringUtil.isNotEmpty(obj) && !StringUtil.isNotEmpty(obj2)) {
                string = getResources().getString(R.string.consult_error_post_no_title_details);
            } else if (!StringUtil.isNotEmpty(obj2)) {
                string = getResources().getString(R.string.consult_error_post_no_details);
            }
            z = false;
        } else {
            z = true;
        }
        if (this.mAdditionalDetails.getText().length() > 3000) {
            string = getResources().getString(R.string.consult_error_post_max_characters);
        } else {
            z2 = z;
        }
        if (!z2) {
            try {
                new MedscapeException(getString(R.string.consult_error_message_title), string).showAlert(this, "OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }, (String) null, (DialogInterface.OnClickListener) null);
            } catch (Exception unused) {
                Trace.w(TAG, "Failed to show invalid post alert");
            }
        }
        return z2;
    }

    private void disableButtonsWhileSubmitting() {
        this.mFirstImage.setEnabled(false);
        this.mSecondImage.setEnabled(false);
        this.mThirdImage.setEnabled(false);
        this.mFourthImage.setEnabled(false);
        this.mFifthImage.setEnabled(false);
        this.mAddTagsLayout.setEnabled(false);
        this.mSubmitButton.setEnabled(false);
    }

    /* access modifiers changed from: private */
    public void enableButtons() {
        this.mFirstImage.setEnabled(true);
        this.mSecondImage.setEnabled(true);
        this.mThirdImage.setEnabled(true);
        this.mFourthImage.setEnabled(true);
        this.mFifthImage.setEnabled(true);
        this.mAddTagsLayout.setEnabled(true);
        this.mSubmitButton.setEnabled(true);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 55 && iArr.length > 0 && iArr[0] == 0) {
            launchPhotoPicker();
        }
    }

    private String getImageViewPosition(View view) {
        if (view == this.mSecondImage) {
            return AppEventsConstants.EVENT_PARAM_VALUE_YES;
        }
        if (view == this.mThirdImage) {
            return ExifInterface.GPS_MEASUREMENT_2D;
        }
        if (view == this.mFourthImage) {
            return ExifInterface.GPS_MEASUREMENT_3D;
        }
        return view == this.mFifthImage ? "4" : AppEventsConstants.EVENT_PARAM_VALUE_NO;
    }

    public void setActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(true);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
        }
    }
}

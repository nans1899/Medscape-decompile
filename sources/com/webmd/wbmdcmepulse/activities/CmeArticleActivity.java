package com.webmd.wbmdcmepulse.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CmeArticleActivity;
import com.webmd.wbmdcmepulse.adapters.ScreenSlidePagerAdapter;
import com.webmd.wbmdcmepulse.animations.ZoomOutPageTransformer;
import com.webmd.wbmdcmepulse.builders.HtmlBuilder;
import com.webmd.wbmdcmepulse.controllers.ArticleController;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.customviews.HtmlListView;
import com.webmd.wbmdcmepulse.fragments.ArticleQuestionsFragment;
import com.webmd.wbmdcmepulse.fragments.ArticleReferencesFragment;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.CPEvent;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.Graphic;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import com.webmd.wbmdcmepulse.models.articles.Question;
import com.webmd.wbmdcmepulse.models.articles.QuestionRequest;
import com.webmd.wbmdcmepulse.models.articles.QuestionResponse;
import com.webmd.wbmdcmepulse.models.articles.QuestionState;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.articles.Slide;
import com.webmd.wbmdcmepulse.models.interfaces.IImageLoadedEvent;
import com.webmd.wbmdcmepulse.models.interfaces.ITestScoreListener;
import com.webmd.wbmdcmepulse.models.interfaces.IViewPagerClickListener;
import com.webmd.wbmdcmepulse.models.parsers.articles.HtmlParser;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.CpEventsLoggingTask;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.ExtensionsAspectRatio;
import com.webmd.wbmdcmepulse.models.utils.QnaScoreCalculator;
import com.webmd.wbmdcmepulse.models.utils.SimpleOrientationListener;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.models.utils.closecaptioning.CloseCaptionProvider;
import com.webmd.wbmdcmepulse.models.utils.closecaptioning.CloseCaptionTextView;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdcmepulse.providers.CmeArticleProvider;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CmeArticleActivity extends CmeBaseActivity implements DialogInterface.OnDismissListener {
    private static final Integer REQUEST_EXTERNAL_STORAGE = 220;
    /* access modifiers changed from: private */
    public static String TAG = CmeArticleActivity.class.getSimpleName();
    private final String ASPECT_RATIO_16_9 = "16x9";
    private final String ASPECT_RATIO_4_3 = "4x3";
    private boolean hasQna;
    /* access modifiers changed from: private */
    public Target[] imageTargets;
    private ArticleCopyTextView mAbbreviationsTextView;
    /* access modifiers changed from: private */
    public boolean mAreVideoControlsVisible;
    /* access modifiers changed from: private */
    public Article mArticle;
    /* access modifiers changed from: private */
    public ArticleController mArticleController;
    /* access modifiers changed from: private */
    public String mArticleId;
    private CmeArticleProvider mArticleProvider;
    /* access modifiers changed from: private */
    public Map<String, ArticleQuestionsFragment> mArticleQuestionsFragments;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public RelativeLayout mControlsLayout;
    /* access modifiers changed from: private */
    public int mCurrentOrientation;
    /* access modifiers changed from: private */
    public int mCurrentPage;
    /* access modifiers changed from: private */
    public List<Question> mCurrentQuestions;
    /* access modifiers changed from: private */
    public int mCurrentSlidePosition;
    /* access modifiers changed from: private */
    public int mCurrentVideoPosition;
    private String mDownloadUrl;
    private View mEarnCmeCreditButton;
    /* access modifiers changed from: private */
    public int mExpectedOrientation = -1;
    private String mFeedUrl;
    private boolean mFirstTestSubmission;
    private Button mFullScreenButton;
    private boolean mHasSlides;
    private boolean mHasVideo;
    private boolean mHasVideoBeenPlayed = false;
    private boolean mIsEvaluationRequired;
    private boolean mIsFullScreenActive;
    private boolean mIsInitialized = false;
    /* access modifiers changed from: private */
    public boolean mIsLastActionSubmit = false;
    /* access modifiers changed from: private */
    public boolean mIsNavigating;
    private boolean mIsReferringActivityDialog;
    /* access modifiers changed from: private */
    public boolean mIsScorePassing;
    /* access modifiers changed from: private */
    public boolean mIsSettingImagePageManual;
    private QuestionState mLastUsedQuestionState;
    private String mMaxCredits;
    private TabLayout mMediaSlidesTabLayout;
    private LinearLayout mNonVideoContentLayout;
    private double mPassScore;
    /* access modifiers changed from: private */
    public Button mPlayPauseButton;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private List<QuestionState> mQuestionStates;
    /* access modifiers changed from: private */
    public Questionnaire mQuestionnaire;
    /* access modifiers changed from: private */
    public Button mQuestionsContinueButton;
    /* access modifiers changed from: private */
    public ArticleCopyTextView mReferencesTv;
    private String mReferringLink;
    private String mReferringModule;
    private String mReferringPage;
    private String mReferringQuery;
    /* access modifiers changed from: private */
    public RelativeLayout mRootLayout;
    /* access modifiers changed from: private */
    public ScrollView mScrollView;
    /* access modifiers changed from: private */
    public SeekBar mSeekBar;
    /* access modifiers changed from: private */
    public ViewPager mSlidesPager;
    /* access modifiers changed from: private */
    public TextView mTimeElapsedTextView;
    /* access modifiers changed from: private */
    public TextView mTimeRemainingTextView;
    /* access modifiers changed from: private */
    public int mTotalQuestionsRemainng;
    private String mUpActivity;
    /* access modifiers changed from: private */
    public String mVideoAspectRadio;
    /* access modifiers changed from: private */
    public RelativeLayout mVideoContainerRelativeLatout;
    private AlphaAnimation mVideoControlsFadeOutAnimation;
    /* access modifiers changed from: private */
    public VideoView mVideoView;
    /* access modifiers changed from: private */
    public Runnable onEverySecond = new Runnable() {
        public void run() {
            if (CmeArticleActivity.this.mSeekBar != null) {
                CmeArticleActivity.this.mSeekBar.setProgress(CmeArticleActivity.this.mVideoView.getCurrentPosition());
            }
            Trace.d("VideoViewArt", "Current: " + CmeArticleActivity.this.mVideoView.getCurrentPosition() + " Duration: " + CmeArticleActivity.this.mVideoView.getDuration());
            if (CmeArticleActivity.this.mSeekBar != null && CmeArticleActivity.this.mVideoView.isPlaying()) {
                CmeArticleActivity.this.mSeekBar.postDelayed(CmeArticleActivity.this.onEverySecond, 1000);
                CmeArticleActivity cmeArticleActivity = CmeArticleActivity.this;
                int unused = cmeArticleActivity.mCurrentVideoPosition = cmeArticleActivity.mVideoView.getCurrentPosition();
                CmeArticleActivity cmeArticleActivity2 = CmeArticleActivity.this;
                CmeArticleActivity.this.mTimeElapsedTextView.setText(cmeArticleActivity2.getMinutesAndSecondsFromMilliseconds(cmeArticleActivity2.mCurrentVideoPosition));
                TextView access$5300 = CmeArticleActivity.this.mTimeRemainingTextView;
                StringBuilder sb = new StringBuilder();
                sb.append("-");
                CmeArticleActivity cmeArticleActivity3 = CmeArticleActivity.this;
                sb.append(cmeArticleActivity3.getMinutesAndSecondsFromMilliseconds(cmeArticleActivity3.mVideoView.getDuration() - CmeArticleActivity.this.mVideoView.getCurrentPosition()));
                access$5300.setText(sb.toString());
            }
        }
    };

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_article);
        initializeToolBar();
        this.mContext = this;
        this.mRootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        this.mNonVideoContentLayout = (LinearLayout) findViewById(R.id.full_screen_hide_layout);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        setTitleBar(getResources().getString(R.string.title_activity_cmeactivityinfo));
        Intent intent = getIntent();
        this.mArticleId = intent.getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
        this.mArticle = (Article) intent.getParcelableExtra(Constants.BUNDLE_KEY_ARTICLE);
        this.mQuestionnaire = (Questionnaire) intent.getParcelableExtra(Constants.BUNDLE_KEY_ARTICLE_QNA);
        this.mIsEvaluationRequired = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, false);
        this.mCurrentPage = intent.getIntExtra(Constants.BUNDLE_KEY_ARTICLE_PAGE_NUMBER, 0);
        this.mIsReferringActivityDialog = intent.getBooleanExtra(Constants.BUNDLE_KEY_IS_DIAlOG, false);
        this.mUpActivity = intent.getStringExtra(Constants.RETURN_ACTIVITY);
        ArticleController articleController = new ArticleController(this.mContext, this.mUserProfile);
        this.mArticleController = articleController;
        articleController.setIsEducationalImpactChallenge(getIntent().getBooleanExtra(Constants.BUNDLE_KEY_IS_EIC, false));
        this.mQuestionStates = new ArrayList();
        this.mIsNavigating = false;
        this.mHasVideoBeenPlayed = false;
        this.hasQna = true;
        this.mIsSettingImagePageManual = false;
        this.mFeedUrl = intent.getStringExtra(Constants.BUNDLE_KEY_FEED_URL);
        this.mMaxCredits = intent.getStringExtra(Constants.BUNDLE_KEY_MAX_CREDITS);
        if (bundle != null) {
            if (bundle.containsKey("mCurrentVideoPosition")) {
                this.mCurrentVideoPosition = bundle.getInt("mCurrentVideoPosition");
            }
            if (bundle.containsKey("mIsSettingImagePageManual")) {
                this.mIsSettingImagePageManual = bundle.getBoolean("mIsSettingImagePageManual");
            }
            if (bundle.containsKey("mCurrentPage")) {
                this.mCurrentPage = bundle.getInt("mCurrentPage");
            }
            if (bundle.containsKey("hasQna")) {
                this.hasQna = bundle.getBoolean("hasQna");
            }
            if (bundle.containsKey(Constants.CONTENT_IS_SAVED)) {
                this.mIsSaved = bundle.getBoolean(Constants.CONTENT_IS_SAVED);
            }
            if (bundle.containsKey(Constants.BUNDLE_KEY_FEED_URL)) {
                this.mFeedUrl = bundle.getString(Constants.BUNDLE_KEY_FEED_URL);
            }
            if (bundle.containsKey(Constants.BUNDLE_KEY_MAX_CREDITS)) {
                this.mMaxCredits = bundle.getString(Constants.BUNDLE_KEY_MAX_CREDITS);
            }
        }
        this.mReferringPage = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_PAGE);
        this.mReferringModule = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_MODULE);
        this.mReferringLink = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_LINK);
        this.mReferringQuery = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_QUERY);
        init();
        setOrientationListner();
    }

    public void onResume() {
        Map<String, ArticleQuestionsFragment> map;
        super.onResume();
        enableQuestionsContinueButton();
        if (this.mIsInitialized && (map = this.mArticleQuestionsFragments) != null) {
            for (String str : map.keySet()) {
                this.mArticleQuestionsFragments.get(str).setTestScoreListener(getTestScoreListener(this.mLastUsedQuestionState));
                this.mIsLastActionSubmit = false;
            }
        }
        if (this.mCurrentVideoPosition < 0) {
            this.mCurrentVideoPosition = 0;
        }
        if (!this.mIsInitialized) {
            this.mIsInitialized = true;
        }
        this.mIsSettingImagePageManual = false;
        initializeByline();
        setUpReferencesTextView();
        setUpAbbreviationsTextView();
        initializeSlideImagePagerAdapter();
        if (!isLockScreenVisible()) {
            trackOmniturePaging();
        }
        checkIfSaved(this.mFeedUrl, this.mArticleId);
    }

    /* access modifiers changed from: private */
    public void trackOmniturePaging() {
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.asset", new ChronicleIDUtil().generateAssetId(this.mArticleId, this.mArticle.assetId, String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId})));
        int i = this.mCurrentPage;
        if (i > 0) {
            WBMDOmnitureManager.sendPageView(String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW_PAGE, new Object[]{this.mArticleId, Integer.toString(i)}), hashMap, (WBMDOmnitureModule) null);
        } else if (i == 0) {
            trackOmniture(hashMap);
        }
    }

    private void trackOmniture(HashMap<String, String> hashMap) {
        if (!Extensions.isStringNullOrEmpty(this.mArticleId)) {
            String format = String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId});
            if (Extensions.isStringNullOrEmpty(this.mReferringPage) || this.mIsReferringActivityDialog) {
                WBMDOmnitureManager.sendPageView(format, hashMap, (WBMDOmnitureModule) null);
            } else if (this.mReferringPage.contains("activitytracker")) {
                WBMDOmnitureManager.sendPageView(format, hashMap, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, this.mReferringPage));
            } else if (this.mReferringPage.contains("browse-search")) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("wapp.querytext", this.mReferringQuery);
                hashMap2.putAll(hashMap);
                WBMDOmnitureManager.sendPageView(format, hashMap2, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, this.mReferringPage));
            } else {
                WBMDOmnitureManager.sendPageView(format, hashMap, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, String.format(OmnitureData.PAGE_NAME_FEED, new Object[]{this.mReferringPage})));
            }
        }
    }

    public void onPause() {
        if (this.mHasVideo) {
            pauseVideo();
            this.mCurrentVideoPosition = this.mVideoView.getCurrentPosition();
        }
        this.mIsSettingImagePageManual = true;
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Target[] targetArr = this.imageTargets;
        if (targetArr != null) {
            for (Target target : targetArr) {
                if (target != null) {
                    Picasso.get().cancelRequest(target);
                }
            }
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (this.mIsFullScreenActive) {
            setRequestedOrientation(1);
            this.mExpectedOrientation = 1;
        } else if (this.mIsNavigating) {
        } else {
            if (this.mCurrentPage > 0) {
                pageBack();
            } else if (Extensions.isStringNullOrEmpty(this.mUpActivity)) {
                super.onBackPressed();
            } else if (this.mUpActivity.equals(Constants.HOME_ACTIVITY_NAME)) {
                Utilities.goToHomeScreen(this);
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return setMenuItems(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_save) {
            onSaveButtonClicked(this, this.mArticle, this.mArticleId, this.mMaxCredits, this.mFeedUrl);
            return true;
        } else if (itemId == R.id.action_share) {
            Article article = this.mArticle;
            if (article != null) {
                shareArticle(this, this.mFeedUrl, article.title);
            }
            return true;
        } else if (itemId == R.id.action_activity_tracker) {
            launchCMETracker(this);
            return true;
        } else if (itemId != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (!this.mIsNavigating) {
                if (this.mCurrentPage > 0) {
                    pageBack();
                } else if (Extensions.isStringNullOrEmpty(this.mUpActivity)) {
                    finish();
                } else if (this.mUpActivity.equals(Constants.HOME_ACTIVITY_NAME)) {
                    finish();
                }
            }
            return true;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (!isVideoNotVisible()) {
            if (getResources().getConfiguration().orientation == 2) {
                this.mIsFullScreenActive = true;
                setFullScreenModeParams();
            } else {
                this.mIsFullScreenActive = false;
                removeFullScreenModeParams();
            }
        }
        if (!isSlidesNotVisible() && getResources().getConfiguration().orientation == 2) {
            launchFullScreenSlides(true);
        }
        ViewPager viewPager = this.mSlidesPager;
        if (viewPager != null) {
            viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    if (CmeArticleActivity.this.getResources().getConfiguration().orientation == 2 || CmeArticleActivity.this.getResources().getConfiguration().orientation == 1) {
                        CmeArticleActivity.this.initializeSlideImagePagerAdapter();
                        try {
                            if (Build.VERSION.SDK_INT < 16) {
                                CmeArticleActivity.this.mSlidesPager.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            } else {
                                CmeArticleActivity.this.mSlidesPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        } catch (Exception unused) {
                            Trace.w(CmeArticleActivity.TAG, "Failed to remove global layout listener");
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 5002) {
            setDefaultOrientation();
            ViewPager viewPager = this.mSlidesPager;
            if (viewPager != null) {
                this.mIsSettingImagePageManual = true;
                viewPager.setCurrentItem(intent.getIntExtra("bundle_key_image_slide_position", 0));
                this.mIsSettingImagePageManual = false;
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("mIsSettingImagePageManual", this.mIsSettingImagePageManual);
        bundle.putInt("mCurrentPage", this.mCurrentPage);
        bundle.putBoolean("hasQna", this.hasQna);
        bundle.putInt("mCurrentVideoPosition", this.mCurrentVideoPosition);
        bundle.putBoolean(Constants.CONTENT_IS_SAVED, this.mIsSaved);
        bundle.putString(Constants.BUNDLE_KEY_FEED_URL, this.mFeedUrl);
        bundle.putString(Constants.BUNDLE_KEY_MAX_CREDITS, this.mMaxCredits);
        super.onSaveInstanceState(bundle);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        ArticleCopyTextView articleCopyTextView = this.mReferencesTv;
        if (articleCopyTextView != null) {
            articleCopyTextView.setClickable(true);
        }
    }

    private void init() {
        this.mArticleProvider = new CmeArticleProvider(this);
        setUpArticle();
        if (isActivityExpired()) {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setNeutralButton((CharSequence) getResources().getString(R.string.activity_expire_dialog_button_continue), (DialogInterface.OnClickListener) $$Lambda$CmeArticleActivity$Pc9XEx8MjSLoMRVUiVXxIvGcmB0.INSTANCE).setMessage((CharSequence) getResources().getString(R.string.activity_expired_text)).setTitle((CharSequence) getResources().getString(R.string.activity_expired_title)).create().show();
        }
    }

    /* access modifiers changed from: private */
    public void setUpEarnCreditButton() {
        View view;
        if (!hasPostTest() && (view = this.mEarnCmeCreditButton) != null) {
            view.setVisibility(8);
        }
        View findViewById = findViewById(R.id.earn_credit_btn);
        this.mEarnCmeCreditButton = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CmeArticleActivity.this.lambda$setUpEarnCreditButton$1$CmeArticleActivity(view);
            }
        });
        if (shouldShowEarnCmeButton()) {
            this.mEarnCmeCreditButton.setVisibility(0);
        } else {
            this.mEarnCmeCreditButton.setVisibility(4);
        }
    }

    public /* synthetic */ void lambda$setUpEarnCreditButton$1$CmeArticleActivity(View view) {
        if (this.mArticleController.getIsEducationalImpactChallenge()) {
            goToPage(this.mArticle.pages.size() - 1);
            return;
        }
        Questionnaire questionnaire = this.mQuestionnaire;
        if (questionnaire == null) {
            return;
        }
        if (questionnaire.getShouldGoToEvaluation()) {
            goToEvaluationScreenviaContinueScreen(true);
            return;
        }
        Intent intent = new Intent(this, CmePostTestActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_QNA, this.mQuestionnaire);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_PASS_SCORE, this.mPassScore);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE, this.mArticle);
        intent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, this.mIsEvaluationRequired);
        intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, this.mArticle.isMocEligible);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, this.mArticle.title);
        intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
        startActivity(intent);
    }

    private boolean hasPostTest() {
        boolean z = false;
        for (ActivityTest next : this.mQuestionnaire.tests) {
            if (next.formatType.equals("POST") || next.formatType.equals("INTERNAL")) {
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void setUpActivityLayerButton() {
        View findViewById = findViewById(R.id.activty_layer_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$setUpActivityLayerButton$2$CmeArticleActivity(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setUpActivityLayerButton$2$CmeArticleActivity(View view) {
        if (this.mArticle != null) {
            Intent intent = new Intent(this, CmeArticleInfoActivity.class);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE, this.mArticle);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_QNA, this.mQuestionnaire);
            intent.putExtra(Constants.BUNDLE_KEY_IS_DIAlOG, true);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_PAGE_NUMBER, this.mCurrentPage);
            intent.putExtra(Constants.BUNDLE_KEY_IS_EIC, this.mArticleController.getIsEducationalImpactChallenge());
            intent.putExtra(Constants.BUNDLE_KEY_REFERRING_PAGE, this.mReferringPage);
            intent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, this.mReferringLink);
            intent.putExtra(Constants.BUNDLE_KEY_FEED_URL, this.mFeedUrl);
            intent.putExtra(Constants.BUNDLE_KEY_MAX_CREDITS, this.mMaxCredits);
            intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_up, R.anim.no_animation);
        }
    }

    private void setContinueButtonToEarnCmeCredit() {
        this.mQuestionsContinueButton.setText("Earn CME Credit");
        this.mQuestionsContinueButton.setTextColor(-1);
        this.mQuestionsContinueButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.cme_continue_button_ripple));
        this.mQuestionsContinueButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CmeArticleActivity.this.lambda$setContinueButtonToEarnCmeCredit$3$CmeArticleActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$setContinueButtonToEarnCmeCredit$3$CmeArticleActivity(View view) {
        disableQuestionsContinueButton();
        boolean hasPostTest = hasPostTest();
        Questionnaire questionnaire = this.mQuestionnaire;
        if (questionnaire != null && questionnaire.getShouldGoToEvaluation()) {
            goToEvaluationScreenviaContinueScreen(true);
        } else if (hasPostTest) {
            Intent intent = new Intent(this, CmePostTestActivity.class);
            intent.putExtra(Constants.BUNDLE_KEY_QNA, this.mQuestionnaire);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_PASS_SCORE, this.mPassScore);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE, this.mArticle);
            intent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, this.mIsEvaluationRequired);
            intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, this.mArticle.isMocEligible);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, this.mArticle.title);
            intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
            startActivity(intent);
        } else {
            goToCongratulationsActivity();
        }
    }

    /* access modifiers changed from: private */
    public void setUpDownloadSlidesButton() {
        if (!Extensions.isStringNullOrEmpty(this.mArticle.slidesDownloadUrl)) {
            View findViewById = findViewById(R.id.download_slides_button);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$setUpDownloadSlidesButton$5$CmeArticleActivity(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setUpDownloadSlidesButton$5$CmeArticleActivity(View view) {
        if (!Utilities.isNetworkAvailable(this)) {
            new CMEPulseException(getString(R.string.error_connection_required)).showSnackBar(this.mRootLayout, 0, getString(R.string.retry), new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$null$4$CmeArticleActivity(view);
                }
            });
        } else {
            addDownloadSlidesButton();
        }
    }

    public /* synthetic */ void lambda$null$4$CmeArticleActivity(View view) {
        addDownloadSlidesButton();
    }

    private void addDownloadSlidesButton() {
        if (Build.VERSION.SDK_INT < 23) {
            downloadFile(this.mArticle.slidesDownloadUrl);
        } else if (checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            downloadFile(this.mArticle.slidesDownloadUrl);
        } else {
            this.mDownloadUrl = this.mArticle.slidesDownloadUrl;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, REQUEST_EXTERNAL_STORAGE.intValue());
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == REQUEST_EXTERNAL_STORAGE.intValue() && iArr[0] == 0 && !this.mDownloadUrl.isEmpty()) {
            downloadFile(this.mDownloadUrl);
        }
    }

    private void downloadFile(String str) {
        Utilities.downloadFile(this, this.mRootLayout, str);
    }

    /* access modifiers changed from: private */
    public void setUpPrescriptionToLeanButton() {
        if (!Extensions.isStringNullOrEmpty(this.mArticle.prescriptionToLeanUrl)) {
            View findViewById = findViewById(R.id.prescription_to_learn_button);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$setUpPrescriptionToLeanButton$6$CmeArticleActivity(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setUpPrescriptionToLeanButton$6$CmeArticleActivity(View view) {
        Intent intent = new Intent(this, CmeWebActivity.class);
        intent.putExtra(Constants.WEB_VIEW_URL_KEY, "https://docs.google.com/viewer?url=" + this.mArticle.prescriptionToLeanUrl);
        intent.putExtra(Constants.WEB_VIEW_TITLE_KEY, "Prescription to Learn");
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void setUpWebReprintButton() {
        if (!Extensions.isStringNullOrEmpty(this.mArticle.webReprintUrl)) {
            View findViewById = findViewById(R.id.web_reprint_button);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$setUpWebReprintButton$7$CmeArticleActivity(view);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setUpWebReprintButton$7$CmeArticleActivity(View view) {
        Intent intent = new Intent(this, CmeWebActivity.class);
        intent.putExtra(Constants.WEB_VIEW_URL_KEY, "https://docs.google.com/viewer?url=" + this.mArticle.webReprintUrl);
        intent.putExtra(Constants.WEB_VIEW_TITLE_KEY, "Web Reprint");
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void setUpArticle() {
        try {
            if (this.mArticle != null) {
                initializeArticle(this.mArticle);
                return;
            }
            if (this.mProgressBar != null) {
                this.mProgressBar.setVisibility(0);
            }
            if (!Utilities.isNetworkAvailable(this)) {
                if (this.mProgressBar != null) {
                    this.mProgressBar.setVisibility(4);
                }
                new CMEPulseException(getString(R.string.error_connection_required)).showSnackBar(findViewById(R.id.root_layout), 0, getString(R.string.retry), new View.OnClickListener() {
                    public final void onClick(View view) {
                        CmeArticleActivity.this.lambda$setUpArticle$8$CmeArticleActivity(view);
                    }
                });
                return;
            }
            this.mArticleProvider.getArticle(this.mArticleId, new ICallbackEvent<Article, CMEPulseException>() {
                public void onError(CMEPulseException cMEPulseException) {
                    if (CmeArticleActivity.this.mProgressBar != null) {
                        CmeArticleActivity.this.mProgressBar.setVisibility(4);
                    }
                    cMEPulseException.showSnackBar(CmeArticleActivity.this.mRootLayout, -2, CmeArticleActivity.this.getString(R.string.error_article_service), new View.OnClickListener() {
                        public final void onClick(View view) {
                            CmeArticleActivity.AnonymousClass2.this.lambda$onError$0$CmeArticleActivity$2(view);
                        }
                    });
                    Trace.e(CmeArticleActivity.TAG, cMEPulseException.getMessage());
                }

                public /* synthetic */ void lambda$onError$0$CmeArticleActivity$2(View view) {
                    CmeArticleActivity.this.setUpArticle();
                }

                public void onCompleted(Article article) {
                    Article unused = CmeArticleActivity.this.mArticle = article;
                    boolean unused2 = CmeArticleActivity.this.mIsSettingImagePageManual = true;
                    CmeArticleActivity.this.initializeArticle(article);
                    boolean unused3 = CmeArticleActivity.this.mIsSettingImagePageManual = false;
                }
            });
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
        }
    }

    public /* synthetic */ void lambda$setUpArticle$8$CmeArticleActivity(View view) {
        setUpArticle();
    }

    /* access modifiers changed from: private */
    public void initializeArticle(Article article) {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        this.mPassScore = QnaScoreCalculator.getPassingScore(article.eligibilities, this.mUserProfile);
        this.mScrollView = (ScrollView) findViewById(R.id.scroll_view);
        initializeVideo();
        initializeSlides(article);
        initializeVideoSlidesTabLayout();
        setDefaultOrientation();
        initializeArticleSummaryViews(article);
        setUpReferencesTextView();
        if (!Extensions.isStringNullOrEmpty(article.qnaId)) {
            setUpQna(article.qnaId, article);
            return;
        }
        this.hasQna = false;
        this.mQuestionsContinueButton = (Button) findViewById(R.id.questions_remaining_continue);
        setUpSections(article, this.mCurrentPage);
        setUpActivityLayerButton();
        ProgressBar progressBar2 = this.mProgressBar;
        if (progressBar2 != null) {
            progressBar2.setVisibility(4);
        }
    }

    private void initializeArticleSummaryViews(Article article) {
        CustomFontTextView customFontTextView = (CustomFontTextView) findViewById(R.id.article_title_text_view);
        customFontTextView.setText(article.title);
        if (!Extensions.isStringNullOrEmpty(article.cmeFlag) && !article.cmeFlag.equals("null")) {
            SpannableString spannableString = new SpannableString(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.cmeFlag);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), 1, article.cmeFlag.length() + 1, 0);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.app_accent_color)), 1, article.cmeFlag.length() + 1, 0);
            customFontTextView.append(spannableString);
        }
        if (article.byLine != null && article.byLine.contains("CME Author:")) {
            article.byLine = article.byLine.replace("CME Author:", "<br/>CME Author:");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (article.releaseDate != null) {
            ((ArticleCopyTextView) findViewById(R.id.summaryTextView)).setText("Medscape Education : " + article.contentType + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + simpleDateFormat.format(article.releaseDate.getTime()));
        }
        if (article.expirationDate != null) {
            ((ArticleCopyTextView) findViewById(R.id.valid_through_text_view)).setText("Valid through " + simpleDateFormat.format(article.expirationDate.getTime()));
        }
    }

    private void initializeByline() {
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) findViewById(R.id.article_byline_text_view);
        if (!Extensions.isStringNullOrEmpty(this.mArticle.byLine)) {
            articleCopyTextView.setText(Html.fromHtml(this.mArticle.byLine.trim()) + " | ");
        }
        SpannableString spannableString = new SpannableString(getString(R.string.disclosures_link_text));
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.linkcolor)), 0, spannableString.length(), 0);
        spannableString.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
            }

            public void onClick(View view) {
                Intent intent = new Intent(CmeArticleActivity.this.mContext, CmeArticleDisclosuresActivity.class);
                intent.putParcelableArrayListExtra(Constants.BUNDLE_KEY_DISCLOSURES, (ArrayList) CmeArticleActivity.this.mArticle.contributors);
                CmeArticleActivity.this.startActivity(intent);
                CmeArticleActivity.this.overridePendingTransition(R.anim.slide_in_up, R.anim.no_animation);
            }
        }, 0, spannableString.length(), 33);
        articleCopyTextView.append(spannableString);
        articleCopyTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* access modifiers changed from: private */
    public void setUpReferencesTextView() {
        ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(this.mContext);
        this.mReferencesTv = articleCopyTextView;
        articleCopyTextView.setText(getResources().getString(R.string.article_references_link));
        this.mReferencesTv.setTextColor(getResources().getColor(R.color.linkcolor));
        if (this.mArticle.references == null || this.mArticle.references.content.size() <= 0) {
            this.mReferencesTv.setVisibility(8);
            return;
        }
        this.mReferencesTv.setTextColor(getResources().getColor(R.color.linkcolor));
        this.mReferencesTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CmeArticleActivity.this.mReferencesTv.setClickable(false);
                ArticleReferencesFragment.newInstance(CmeArticleActivity.this.mArticle.references).show(CmeArticleActivity.this.getSupportFragmentManager(), Constants.FRAGMENT_TAG_ARTICLE_REFERENCES);
            }
        });
    }

    private void setUpAbbreviationsTextView() {
        ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(this.mContext);
        this.mAbbreviationsTextView = articleCopyTextView;
        articleCopyTextView.setText(getResources().getString(R.string.abbreviations_link_text));
        this.mAbbreviationsTextView.setTextColor(getResources().getColor(R.color.linkcolor));
        if (this.mArticle.abbreviations == null || this.mArticle.abbreviations.length <= 0) {
            this.mAbbreviationsTextView.setVisibility(8);
            return;
        }
        this.mAbbreviationsTextView.setTextColor(getResources().getColor(R.color.linkcolor));
        this.mAbbreviationsTextView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CmeArticleActivity.this.lambda$setUpAbbreviationsTextView$9$CmeArticleActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$setUpAbbreviationsTextView$9$CmeArticleActivity(View view) {
        this.mAbbreviationsTextView.setClickable(false);
        ArticleReferencesFragment newInstance = ArticleReferencesFragment.newInstance(this.mArticle.abbreviations, "Abbreviations");
        newInstance.setStyle(0, R.style.CustomDialog);
        newInstance.show(getSupportFragmentManager(), Constants.FRAGMENT_TAG_ARTICLE_ABBREVIATIONS);
    }

    private void initializeVideoSlidesTabLayout() {
        if (this.mHasSlides && this.mHasVideo) {
            TabLayout tabLayout = (TabLayout) findViewById(R.id.video_slides_tab);
            this.mMediaSlidesTabLayout = tabLayout;
            tabLayout.setId(R.id.video_slides_tab);
            TabLayout tabLayout2 = this.mMediaSlidesTabLayout;
            tabLayout2.addTab(tabLayout2.newTab().setText((CharSequence) "Media"));
            TabLayout tabLayout3 = this.mMediaSlidesTabLayout;
            tabLayout3.addTab(tabLayout3.newTab().setText((CharSequence) "Slides"));
            this.mMediaSlidesTabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) new TabLayout.OnTabSelectedListener() {
                public void onTabReselected(TabLayout.Tab tab) {
                }

                public void onTabUnselected(TabLayout.Tab tab) {
                }

                public void onTabSelected(TabLayout.Tab tab) {
                    String str;
                    if (tab.getPosition() == 0) {
                        CmeArticleActivity.this.mSlidesPager.setVisibility(8);
                        CmeArticleActivity.this.mVideoContainerRelativeLatout.setVisibility(0);
                        CmeArticleActivity.this.mVideoContainerRelativeLatout.post(new Runnable() {
                            public final void run() {
                                CmeArticleActivity.AnonymousClass5.this.lambda$onTabSelected$0$CmeArticleActivity$5();
                            }
                        });
                        str = "video";
                    } else if (tab.getPosition() == 1) {
                        CmeArticleActivity.this.mSlidesPager.setVisibility(0);
                        CmeArticleActivity.this.mVideoContainerRelativeLatout.setVisibility(8);
                        str = OmnitureData.LINK_NAME_TAB_SLIDES;
                    } else {
                        str = null;
                    }
                    WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(OmnitureData.MODULE_NAME_VIDEO_SLIDES_TAB, str, String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{CmeArticleActivity.this.mArticleId})));
                }

                public /* synthetic */ void lambda$onTabSelected$0$CmeArticleActivity$5() {
                    CmeArticleActivity.this.mVideoContainerRelativeLatout.requestLayout();
                    CmeArticleActivity.this.mVideoView.requestLayout();
                }
            });
        }
    }

    private void setImageAspectRatio(ImageView imageView) {
        if (getResources().getConfiguration().orientation == 1) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            int adjustedHeight = ExtensionsAspectRatio.getAdjustedHeight(4, 3, this.mRootLayout.getLayoutParams().width);
            imageView.setMinimumWidth(displayMetrics.widthPixels);
            imageView.setMinimumHeight(adjustedHeight);
            return;
        }
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        int adjustedHeight2 = ExtensionsAspectRatio.getAdjustedHeight(16, 9, this.mRootLayout.getLayoutParams().width);
        imageView.setMinimumWidth(displayMetrics2.widthPixels);
        imageView.setMinimumHeight(adjustedHeight2);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0408  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setUpSections(com.webmd.wbmdcmepulse.models.articles.Article r19, int r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r20
            r3 = 1
            r1.mFirstTestSubmission = r3
            java.util.concurrent.ConcurrentHashMap r4 = new java.util.concurrent.ConcurrentHashMap
            r4.<init>()
            r1.mArticleQuestionsFragments = r4
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r1.mCurrentQuestions = r4
            com.webmd.wbmdcmepulse.models.articles.QuestionState r4 = new com.webmd.wbmdcmepulse.models.articles.QuestionState
            r4.<init>()
            int r5 = com.webmd.wbmdcmepulse.R.id.sections_linear_layout
            android.view.View r5 = r1.findViewById(r5)
            android.widget.LinearLayout r5 = (android.widget.LinearLayout) r5
            android.widget.RelativeLayout r6 = new android.widget.RelativeLayout
            android.content.Context r7 = r1.mContext
            r6.<init>(r7)
            r7 = 0
            r6.setGravity(r7)
            r8 = 15
            r6.setPadding(r7, r7, r7, r8)
            r5.removeAllViewsInLayout()
            if (r2 >= 0) goto L_0x003b
            r2 = 0
            goto L_0x004a
        L_0x003b:
            java.util.List<com.webmd.wbmdcmepulse.models.articles.ArticlePage> r8 = r0.pages
            int r8 = r8.size()
            if (r2 < r8) goto L_0x004a
            java.util.List<com.webmd.wbmdcmepulse.models.articles.ArticlePage> r2 = r0.pages
            int r2 = r2.size()
            int r2 = r2 - r3
        L_0x004a:
            java.util.List<com.webmd.wbmdcmepulse.models.articles.ArticlePage> r0 = r0.pages
            java.lang.Object r0 = r0.get(r2)
            com.webmd.wbmdcmepulse.models.articles.ArticlePage r0 = (com.webmd.wbmdcmepulse.models.articles.ArticlePage) r0
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Section> r0 = r0.sections
            java.util.Iterator r8 = r0.iterator()
            r0 = 0
        L_0x0059:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0439
            java.lang.Object r9 = r8.next()
            com.webmd.wbmdcmepulse.models.articles.Section r9 = (com.webmd.wbmdcmepulse.models.articles.Section) r9
            com.webmd.wbmdcmepulse.models.articles.Article r12 = r1.mArticle
            java.util.List<com.webmd.wbmdcmepulse.models.articles.QuestionPageMapItem> r12 = r12.questionPageMap
            java.util.Iterator r12 = r12.iterator()
            java.lang.String r13 = ""
            r14 = r13
        L_0x0070:
            boolean r15 = r12.hasNext()
            if (r15 == 0) goto L_0x008b
            java.lang.Object r15 = r12.next()
            com.webmd.wbmdcmepulse.models.articles.QuestionPageMapItem r15 = (com.webmd.wbmdcmepulse.models.articles.QuestionPageMapItem) r15
            java.lang.String r10 = r15.pageNumber
            java.lang.String r11 = java.lang.Integer.toString(r2)
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x0070
            java.lang.String r14 = r15.testId
            goto L_0x0070
        L_0x008b:
            java.lang.String r10 = r9.title
            r1.addTitleTextViewToArticle(r5, r10)
            java.util.List<com.webmd.wbmdcmepulse.models.articles.SubSection> r10 = r9.subsections
            int r10 = r10.size()
            com.squareup.picasso.Target[] r10 = new com.squareup.picasso.Target[r10]
            r1.imageTargets = r10
            r10 = 0
        L_0x009b:
            java.util.List<com.webmd.wbmdcmepulse.models.articles.SubSection> r11 = r9.subsections
            int r11 = r11.size()
            if (r10 >= r11) goto L_0x0059
            java.util.List<com.webmd.wbmdcmepulse.models.articles.SubSection> r11 = r9.subsections
            java.lang.Object r11 = r11.get(r10)
            com.webmd.wbmdcmepulse.models.articles.SubSection r11 = (com.webmd.wbmdcmepulse.models.articles.SubSection) r11
            if (r11 == 0) goto L_0x042f
            java.util.List<com.webmd.wbmdcmepulse.models.articles.HtmlObject> r12 = r11.content
            if (r12 == 0) goto L_0x042f
            java.util.List<com.webmd.wbmdcmepulse.models.articles.HtmlObject> r11 = r11.content
            java.util.Iterator r11 = r11.iterator()
            r12 = r4
            r4 = r0
        L_0x00b9:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x0427
            java.lang.Object r0 = r11.next()
            r15 = r0
            com.webmd.wbmdcmepulse.models.articles.HtmlObject r15 = (com.webmd.wbmdcmepulse.models.articles.HtmlObject) r15
            if (r15 == 0) goto L_0x041b
            java.lang.String r0 = r15.htmlType
            if (r0 == 0) goto L_0x041b
            java.lang.String r0 = r15.htmlType
            r16 = -1
            int r7 = r0.hashCode()
            r3 = 110115790(0x6903bce, float:5.4254655E-35)
            if (r7 == r3) goto L_0x00e9
            r3 = 1469583516(0x5798109c, float:3.34394208E14)
            if (r7 == r3) goto L_0x00df
            goto L_0x00f3
        L_0x00df:
            java.lang.String r3 = "inline_activity_test"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00f3
            r0 = 1
            goto L_0x00f4
        L_0x00e9:
            java.lang.String r3 = "table"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x00f3
            r0 = 0
            goto L_0x00f4
        L_0x00f3:
            r0 = -1
        L_0x00f4:
            if (r0 == 0) goto L_0x0408
            r3 = 1
            if (r0 == r3) goto L_0x0300
            java.lang.String r0 = r15.content
            java.lang.String r3 = "graphic xlink"
            boolean r0 = r0.contains(r3)
            if (r0 != 0) goto L_0x020f
            java.lang.String r0 = r15.content
            java.lang.String r3 = "fig"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x0119
            java.lang.String r0 = r15.content
            java.lang.String r3 = "graphic"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x0119
            goto L_0x020f
        L_0x0119:
            java.lang.String r0 = r15.htmlType
            java.lang.String r3 = "study_highlights"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0132
            com.webmd.wbmdcmepulse.models.articles.Article r0 = r1.mArticle
            com.webmd.wbmdcmepulse.models.articles.HtmlObject r0 = r0.studyHighlights
            if (r0 == 0) goto L_0x041b
            com.webmd.wbmdcmepulse.models.articles.Article r0 = r1.mArticle
            com.webmd.wbmdcmepulse.models.articles.HtmlObject r0 = r0.studyHighlights
            r1.addListToArticle(r5, r0)
            goto L_0x041b
        L_0x0132:
            java.lang.String r0 = r15.htmlType
            java.lang.String r3 = "pearls_for_practice"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x014b
            com.webmd.wbmdcmepulse.models.articles.Article r0 = r1.mArticle
            com.webmd.wbmdcmepulse.models.articles.HtmlObject r0 = r0.pearlsForPractice
            if (r0 == 0) goto L_0x041b
            com.webmd.wbmdcmepulse.models.articles.Article r0 = r1.mArticle
            com.webmd.wbmdcmepulse.models.articles.HtmlObject r0 = r0.pearlsForPractice
            r1.addListToArticle(r5, r0)
            goto L_0x041b
        L_0x014b:
            java.lang.String r0 = r15.htmlType
            java.lang.String r3 = "ul"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x020a
            java.lang.String r0 = r15.htmlType
            java.lang.String r3 = "ol"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0161
            goto L_0x020a
        L_0x0161:
            java.lang.String r0 = r15.htmlType
            java.lang.String r3 = "h3"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0172
            java.lang.String r0 = r15.content
            r1.addTitleTextViewToArticle(r5, r0)
            goto L_0x041b
        L_0x0172:
            java.lang.String r0 = r15.htmlType
            java.lang.String r3 = "button"
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x017e
            goto L_0x041b
        L_0x017e:
            com.webmd.wbmdcmepulse.models.articles.Article r0 = r1.mArticle
            com.webmd.wbmdcmepulse.models.articles.References r0 = r0.references
            if (r0 == 0) goto L_0x01e4
            com.webmd.wbmdcmepulse.models.articles.Article r0 = r1.mArticle
            com.webmd.wbmdcmepulse.models.articles.References r0 = r0.references
            java.util.List<java.lang.String> r0 = r0.content
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x01e4
            java.lang.String r0 = r15.content
            boolean r0 = com.webmd.wbmdcmepulse.models.utils.Extensions.isStringNullOrEmpty(r0)
            if (r0 != 0) goto L_0x041b
            java.lang.String r0 = r15.content
            boolean r0 = r1.isCMELink(r0)
            if (r0 == 0) goto L_0x041b
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = new com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView
            android.content.Context r7 = r1.mContext
            r0.<init>(r7)
            r7 = 0
            r0.setAutoLinkMask(r7)
            r7 = 1
            r0.setLinksClickable(r7)
            java.util.regex.Pattern r7 = android.util.Patterns.WEB_URL
            java.lang.String r3 = "ref://"
            android.text.util.Linkify.addLinks(r0, r7, r3)
            android.text.method.MovementMethod r3 = android.text.method.LinkMovementMethod.getInstance()
            r0.setMovementMethod(r3)
            java.lang.String r3 = r15.content
            com.webmd.wbmdcmepulse.models.articles.Article r7 = r1.mArticle
            com.webmd.wbmdcmepulse.models.articles.References r7 = r7.references
            java.util.List<java.lang.String> r7 = r7.content
            android.text.SpannableStringBuilder r3 = com.webmd.wbmdcmepulse.builders.HtmlBuilder.getHtmlStringForTextView(r1, r3, r7)
            r0.setText(r3)
            android.content.res.Resources r3 = r18.getResources()
            int r7 = com.webmd.wbmdcmepulse.R.color.black
            int r3 = r3.getColor(r7)
            r0.setTextColor(r3)
            r3 = 40
            r7 = 0
            r0.setPadding(r7, r7, r7, r3)
            r5.addView(r0)
            goto L_0x041b
        L_0x01e4:
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = new com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView
            android.content.Context r3 = r1.mContext
            r0.<init>(r3)
            java.lang.String r3 = com.webmd.wbmdcmepulse.builders.HtmlBuilder.getHtmlForHtmlObject(r15)
            r0.append(r3)
            android.content.res.Resources r3 = r18.getResources()
            int r7 = com.webmd.wbmdcmepulse.R.color.black
            int r3 = r3.getColor(r7)
            r0.setTextColor(r3)
            r3 = 40
            r7 = 0
            r0.setPadding(r7, r7, r7, r3)
            r5.addView(r0)
            goto L_0x041b
        L_0x020a:
            r1.addListToArticle(r5, r15)
            goto L_0x041b
        L_0x020f:
            com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser r0 = new com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser
            java.lang.String r3 = r15.content
            r0.<init>(r3)
            android.widget.LinearLayout$LayoutParams r3 = new android.widget.LinearLayout$LayoutParams     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            r7 = -2
            r3.<init>(r7, r7)     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            r7 = 1065353216(0x3f800000, float:1.0)
            r3.weight = r7     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            r7 = 17
            r3.gravity = r7     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            com.webmd.wbmdcmepulse.models.articles.Graphic r0 = r0.Parse()     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            java.lang.String r7 = r0.label     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            boolean r7 = com.webmd.wbmdcmepulse.models.utils.Extensions.isStringNullOrEmpty(r7)     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            if (r7 != 0) goto L_0x025f
            android.widget.TextView r7 = new android.widget.TextView     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            android.content.Context r15 = r1.mContext     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            r7.<init>(r15)     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            r15.<init>()     // Catch:{ XmlPullParserException -> 0x02f0, IOException -> 0x02e0 }
            r16 = r4
            java.lang.String r4 = "<p><strong>"
            r15.append(r4)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            java.lang.String r4 = r0.label     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r15.append(r4)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            java.lang.String r4 = "</strong></p>"
            r15.append(r4)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            java.lang.String r4 = r15.toString()     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            android.text.Spanned r4 = android.text.Html.fromHtml(r4)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r7.setText(r4)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r7.setLayoutParams(r3)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r5.addView(r7)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            goto L_0x0261
        L_0x025f:
            r16 = r4
        L_0x0261:
            android.widget.ImageView r4 = new android.widget.ImageView     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            android.content.Context r7 = r1.mContext     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r4.<init>(r7)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            java.lang.String r7 = r0.url     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            java.lang.String r15 = "content/"
            java.lang.String r7 = r7.replace(r15, r13)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r0.url = r7     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            android.util.DisplayMetrics r7 = new android.util.DisplayMetrics     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            android.view.WindowManager r15 = r18.getWindowManager()     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            android.view.Display r15 = r15.getDefaultDisplay()     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r15.getMetrics(r7)     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            int r7 = r7.widthPixels     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r15.<init>()     // Catch:{ XmlPullParserException -> 0x02de, IOException -> 0x02dc }
            r17 = r8
            java.lang.String r8 = r0.url     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r15.append(r8)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            java.lang.String r8 = "?interpolation=lanczos-none&resize="
            r15.append(r8)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r15.append(r7)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            java.lang.String r7 = "px:*"
            r15.append(r7)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            java.lang.String r7 = r15.toString()     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r0.url = r7     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            java.lang.String r7 = r0.url     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r4.setTag(r7)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            com.squareup.picasso.Target[] r7 = r1.imageTargets     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            com.webmd.wbmdcmepulse.activities.CmeArticleActivity$7 r8 = new com.webmd.wbmdcmepulse.activities.CmeArticleActivity$7     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r8.<init>(r4)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r7[r10] = r8     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            com.squareup.picasso.Picasso r7 = com.squareup.picasso.Picasso.get()     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            java.lang.String r8 = r0.url     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            com.squareup.picasso.RequestCreator r7 = r7.load((java.lang.String) r8)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            int r8 = com.webmd.wbmdcmepulse.R.drawable.cme_placeholder_image     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            com.squareup.picasso.RequestCreator r7 = r7.placeholder((int) r8)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            com.squareup.picasso.Target[] r8 = r1.imageTargets     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r8 = r8[r10]     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r7.into((com.squareup.picasso.Target) r8)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            com.webmd.wbmdcmepulse.activities.-$$Lambda$CmeArticleActivity$4br1si5PWB7GvFicYS1POcQGLOw r7 = new com.webmd.wbmdcmepulse.activities.-$$Lambda$CmeArticleActivity$4br1si5PWB7GvFicYS1POcQGLOw     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r7.<init>(r0)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r4.setOnClickListener(r7)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r4.setLayoutParams(r3)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            r5.addView(r4)     // Catch:{ XmlPullParserException -> 0x02da, IOException -> 0x02d8 }
            goto L_0x041f
        L_0x02d8:
            r0 = move-exception
            goto L_0x02e5
        L_0x02da:
            r0 = move-exception
            goto L_0x02f5
        L_0x02dc:
            r0 = move-exception
            goto L_0x02e3
        L_0x02de:
            r0 = move-exception
            goto L_0x02f3
        L_0x02e0:
            r0 = move-exception
            r16 = r4
        L_0x02e3:
            r17 = r8
        L_0x02e5:
            java.lang.String r3 = TAG
            java.lang.String r0 = r0.getMessage()
            com.wbmd.wbmdcommons.logging.Trace.e(r3, r0)
            goto L_0x041f
        L_0x02f0:
            r0 = move-exception
            r16 = r4
        L_0x02f3:
            r17 = r8
        L_0x02f5:
            java.lang.String r3 = TAG
            java.lang.String r0 = r0.getMessage()
            com.wbmd.wbmdcommons.logging.Trace.e(r3, r0)
            goto L_0x041f
        L_0x0300:
            r16 = r4
            r17 = r8
            com.webmd.wbmdcmepulse.models.articles.Questionnaire r0 = r1.mQuestionnaire
            java.util.List<com.webmd.wbmdcmepulse.models.articles.ActivityTest> r0 = r0.tests
            java.util.Iterator r3 = r0.iterator()
            r0 = 0
        L_0x030d:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x0421
            java.lang.Object r7 = r3.next()
            com.webmd.wbmdcmepulse.models.articles.ActivityTest r7 = (com.webmd.wbmdcmepulse.models.articles.ActivityTest) r7
            java.lang.String r8 = r7.id
            r16 = r0
            java.lang.String r0 = r15.content
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L_0x0404
            com.webmd.wbmdcmepulse.models.articles.QuestionState r4 = new com.webmd.wbmdcmepulse.models.articles.QuestionState
            r4.<init>(r7)
            java.lang.String r0 = r15.content
            r4.tag = r0
            r8 = 0
            r4.isPoll = r8
            r4.showAnswerTable = r8
            r4.isResponded = r8
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Question> r0 = r7.questions
            java.util.Iterator r0 = r0.iterator()
        L_0x033b:
            boolean r12 = r0.hasNext()
            if (r12 == 0) goto L_0x036b
            java.lang.Object r12 = r0.next()
            com.webmd.wbmdcmepulse.models.articles.Question r12 = (com.webmd.wbmdcmepulse.models.articles.Question) r12
            boolean r8 = r4.isResponded
            if (r8 != 0) goto L_0x0352
            boolean r8 = r12.isResponded
            if (r8 == 0) goto L_0x0352
            r8 = 1
            r4.isResponded = r8
        L_0x0352:
            boolean r8 = r4.isPoll
            if (r8 != 0) goto L_0x035d
            boolean r8 = r12.isPoll
            if (r8 == 0) goto L_0x035d
            r8 = 1
            r4.isPoll = r8
        L_0x035d:
            boolean r8 = r4.showAnswerTable
            if (r8 != 0) goto L_0x0369
            boolean r8 = r12.showAnswersTable
            if (r8 == 0) goto L_0x0369
            boolean r8 = r12.showAnswersTable
            r4.showAnswerTable = r8
        L_0x0369:
            r8 = 0
            goto L_0x033b
        L_0x036b:
            java.lang.String r0 = r7.title
            boolean r0 = r1.isPreTestPage(r0)
            r4.isPreTest = r0
            java.lang.String r0 = r7.id
            boolean r0 = r0.equals(r14)
            if (r0 == 0) goto L_0x03bf
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = r1.mReferencesTv
            if (r0 == 0) goto L_0x0393
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 == 0) goto L_0x038c
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r8 = r1.mReferencesTv
            r0.removeView(r8)
        L_0x038c:
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = r1.mReferencesTv
            r6.addView(r0)
            r0 = 1
            goto L_0x0394
        L_0x0393:
            r0 = 0
        L_0x0394:
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r8 = r1.mAbbreviationsTextView
            if (r8 == 0) goto L_0x03bd
            android.view.ViewParent r0 = r8.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 == 0) goto L_0x03a5
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r8 = r1.mAbbreviationsTextView
            r0.removeView(r8)
        L_0x03a5:
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = r1.mAbbreviationsTextView
            r6.addView(r0)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r8 = -2
            r0.<init>(r8, r8)
            r8 = 11
            r0.addRule(r8)
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r8 = r1.mAbbreviationsTextView
            r8.setLayoutParams(r0)
            r16 = 1
            goto L_0x03bf
        L_0x03bd:
            r16 = r0
        L_0x03bf:
            android.widget.FrameLayout r0 = new android.widget.FrameLayout     // Catch:{ Exception -> 0x03d6 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x03d6 }
            java.lang.String r8 = r7.id     // Catch:{ Exception -> 0x03d6 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x03d6 }
            r0.setId(r8)     // Catch:{ Exception -> 0x03d6 }
            r5.addView(r0)     // Catch:{ Exception -> 0x03d6 }
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Question> r8 = r7.questions     // Catch:{ Exception -> 0x03d6 }
            r1.setUpArticleTestFragment(r0, r4, r8)     // Catch:{ Exception -> 0x03d6 }
            goto L_0x03e0
        L_0x03d6:
            r0 = move-exception
            java.lang.String r8 = TAG
            java.lang.String r0 = r0.getMessage()
            com.wbmd.wbmdcommons.logging.Trace.e(r8, r0)
        L_0x03e0:
            if (r16 == 0) goto L_0x03e5
            r5.addView(r6)
        L_0x03e5:
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Question> r0 = r1.mCurrentQuestions
            int r0 = r0.size()
            if (r0 != 0) goto L_0x03f7
            java.util.List<com.webmd.wbmdcmepulse.models.articles.QuestionState> r0 = r1.mQuestionStates
            r0.add(r4)
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Question> r0 = r7.questions
            r1.mCurrentQuestions = r0
            goto L_0x03fe
        L_0x03f7:
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Question> r0 = r1.mCurrentQuestions
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Question> r7 = r7.questions
            r0.addAll(r7)
        L_0x03fe:
            r12 = r4
            r0 = r16
            r4 = 1
            goto L_0x030d
        L_0x0404:
            r0 = r16
            goto L_0x030d
        L_0x0408:
            r16 = r4
            r17 = r8
            android.content.Context r0 = r1.mContext
            com.webmd.wbmdcmepulse.activities.CmeArticleActivity$6 r3 = new com.webmd.wbmdcmepulse.activities.CmeArticleActivity$6
            r3.<init>(r10)
            android.widget.TableLayout r0 = com.webmd.wbmdcmepulse.builders.ArticleBuilder.buildTableLayout(r15, r0, r3)
            r5.addView(r0)
            goto L_0x041f
        L_0x041b:
            r16 = r4
            r17 = r8
        L_0x041f:
            r4 = r16
        L_0x0421:
            r8 = r17
            r3 = 1
            r7 = 0
            goto L_0x00b9
        L_0x0427:
            r16 = r4
            r17 = r8
            r4 = r12
            r0 = r16
            goto L_0x0431
        L_0x042f:
            r17 = r8
        L_0x0431:
            int r10 = r10 + 1
            r8 = r17
            r3 = 1
            r7 = 0
            goto L_0x009b
        L_0x0439:
            if (r0 != 0) goto L_0x0482
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = r1.mReferencesTv
            if (r0 == 0) goto L_0x0453
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 == 0) goto L_0x044c
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r3 = r1.mReferencesTv
            r0.removeView(r3)
        L_0x044c:
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = r1.mReferencesTv
            r6.addView(r0)
            r7 = 1
            goto L_0x0454
        L_0x0453:
            r7 = 0
        L_0x0454:
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = r1.mAbbreviationsTextView
            if (r0 == 0) goto L_0x047c
            android.view.ViewParent r0 = r0.getParent()
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            if (r0 == 0) goto L_0x0465
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r3 = r1.mAbbreviationsTextView
            r0.removeView(r3)
        L_0x0465:
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r0 = r1.mAbbreviationsTextView
            r6.addView(r0)
            android.widget.RelativeLayout$LayoutParams r0 = new android.widget.RelativeLayout$LayoutParams
            r3 = -2
            r0.<init>(r3, r3)
            r3 = 11
            r0.addRule(r3)
            com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView r3 = r1.mAbbreviationsTextView
            r3.setLayoutParams(r0)
            r3 = 1
            goto L_0x047d
        L_0x047c:
            r3 = r7
        L_0x047d:
            if (r3 == 0) goto L_0x0482
            r5.addView(r6)
        L_0x0482:
            boolean r0 = r4.isPreTest
            r1.handleMediaViewsForPreTestPage(r0)
            r1.showHideEarnCmeButton(r2)
            java.util.List<com.webmd.wbmdcmepulse.models.articles.Question> r0 = r1.mCurrentQuestions
            int r0 = r0.size()
            r1.mTotalQuestionsRemainng = r0
            r1.mLastUsedQuestionState = r4
            r1.setQuestionsContinueButtonState(r0, r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.activities.CmeArticleActivity.setUpSections(com.webmd.wbmdcmepulse.models.articles.Article, int):void");
    }

    public /* synthetic */ void lambda$setUpSections$10$CmeArticleActivity(Graphic graphic, View view) {
        ArrayList arrayList = new ArrayList();
        Slide slide = new Slide();
        slide.graphicUrl = graphic.url;
        arrayList.add(slide);
        Intent intent = new Intent(this.mContext, CmeImageViewerActivity.class);
        intent.putParcelableArrayListExtra("bundle_key_image_slides", arrayList);
        intent.putExtra(Constants.BUNDLE_KEY_IMAGE_WIDTH, graphic.width);
        intent.putExtra(Constants.BUNDLE_KEY_IMAGE_HEIGHT, graphic.height);
        intent.putExtra("bundle_key_image_slide_position", 0);
        intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.mArticleId);
        intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.mArticle.assetId);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public boolean isCMELink(String str) {
        if (str == null || !str.contains("href")) {
            return true;
        }
        return !str.substring(str.indexOf("href")).contains("/qna/processor");
    }

    private void addTitleTextViewToArticle(LinearLayout linearLayout, String str) {
        if (!Extensions.isStringNullOrEmpty(str)) {
            TextView textView = new TextView(this.mContext);
            textView.setText(str);
            textView.setPadding(0, 0, 0, 20);
            textView.setTextSize(1, 18.0f);
            textView.setTextAppearance(this, R.style.redTextView);
            if (this.mArticle.references != null && this.mArticle.references.content.size() > 0) {
                textView.setAutoLinkMask(0);
                textView.setLinksClickable(true);
                Linkify.addLinks(textView, Patterns.WEB_URL, "ref://");
                textView.setMovementMethod(LinkMovementMethod.getInstance());
                textView.setText(HtmlBuilder.getHtmlStringForTextView(this, str, this.mArticle.references.content));
            }
            linearLayout.addView(textView);
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
            layoutParams.setMargins(5, 0, 20, 20);
            imageView.setLayoutParams(layoutParams);
            imageView.setBackgroundColor(-7829368);
            linearLayout.addView(imageView);
        }
    }

    private void handleMediaViewsForPreTestPage(boolean z) {
        if (z) {
            this.mArticleController.setIsEducationalImpactChallenge(true);
        } else if (this.mHasSlides && this.mHasVideo) {
            if (isVideoNotVisible() && isSlidesNotVisible()) {
                this.mVideoContainerRelativeLatout.setVisibility(0);
            }
            this.mMediaSlidesTabLayout.setVisibility(0);
        } else if (this.mHasVideo) {
            this.mVideoContainerRelativeLatout.setVisibility(0);
        } else if (this.mHasSlides) {
            this.mSlidesPager.setVisibility(0);
        }
    }

    private boolean isSlidesNotVisible() {
        ViewPager viewPager = this.mSlidesPager;
        return viewPager == null || viewPager.getVisibility() == 8 || this.mSlidesPager.getVisibility() == 4;
    }

    private boolean isVideoNotVisible() {
        RelativeLayout relativeLayout = this.mVideoContainerRelativeLatout;
        return relativeLayout == null || relativeLayout.getVisibility() == 8 || this.mVideoContainerRelativeLatout.getVisibility() == 4;
    }

    private void addListToArticle(LinearLayout linearLayout, HtmlObject htmlObject) {
        try {
            HtmlParser htmlParser = new HtmlParser();
            HtmlListView htmlListView = new HtmlListView(this.mContext);
            htmlObject.content = "<list>" + htmlObject.content + "</list>";
            htmlListView.inflate(htmlParser.buildListViewString(htmlObject));
            linearLayout.addView(htmlListView);
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
        }
    }

    private void showHideEarnCmeButton(int i) {
        if (shouldShowEarnCmeButton() && this.mArticleController.getIsEducationalImpactChallenge()) {
            if (i == 0 || i == this.mArticle.pages.size() - 1) {
                this.mEarnCmeCreditButton.setVisibility(4);
            } else {
                this.mEarnCmeCreditButton.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: private */
    public void setUpQna(final String str, final Article article) {
        ProgressBar progressBar = this.mProgressBar;
        if (progressBar != null) {
            progressBar.setVisibility(0);
        }
        if (this.mQuestionnaire != null) {
            this.mIsScorePassing = false;
            this.mQuestionsContinueButton = (Button) findViewById(R.id.questions_remaining_continue);
            setUpEarnCreditButton();
            setUpActivityLayerButton();
            setUpDownloadSlidesButton();
            setUpPrescriptionToLeanButton();
            setUpWebReprintButton();
            setPageNumberTitleBar();
            setUpSections(article, this.mCurrentPage);
            ProgressBar progressBar2 = this.mProgressBar;
            if (progressBar2 != null) {
                progressBar2.setVisibility(4);
                return;
            }
            return;
        }
        this.mArticleProvider.getQna(str, new ICallbackEvent<Questionnaire, CMEPulseException>() {
            public void onError(CMEPulseException cMEPulseException) {
                if (CmeArticleActivity.this.mProgressBar != null) {
                    CmeArticleActivity.this.mProgressBar.setVisibility(4);
                }
                cMEPulseException.showSnackBar(CmeArticleActivity.this.mRootLayout, -2, CmeArticleActivity.this.getString(R.string.retry), new View.OnClickListener(str, article) {
                    public final /* synthetic */ String f$1;
                    public final /* synthetic */ Article f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        CmeArticleActivity.AnonymousClass8.this.lambda$onError$0$CmeArticleActivity$8(this.f$1, this.f$2, view);
                    }
                });
            }

            public /* synthetic */ void lambda$onError$0$CmeArticleActivity$8(String str, Article article, View view) {
                CmeArticleActivity.this.setUpQna(str, article);
            }

            public void onCompleted(Questionnaire questionnaire) {
                Questionnaire unused = CmeArticleActivity.this.mQuestionnaire = questionnaire;
                boolean unused2 = CmeArticleActivity.this.mIsScorePassing = false;
                CmeArticleActivity cmeArticleActivity = CmeArticleActivity.this;
                Button unused3 = cmeArticleActivity.mQuestionsContinueButton = (Button) cmeArticleActivity.findViewById(R.id.questions_remaining_continue);
                CmeArticleActivity.this.setUpEarnCreditButton();
                CmeArticleActivity.this.setUpActivityLayerButton();
                CmeArticleActivity.this.setUpDownloadSlidesButton();
                CmeArticleActivity.this.setUpPrescriptionToLeanButton();
                CmeArticleActivity.this.setUpWebReprintButton();
                CmeArticleActivity.this.setPageNumberTitleBar();
                CmeArticleActivity cmeArticleActivity2 = CmeArticleActivity.this;
                cmeArticleActivity2.setUpSections(article, cmeArticleActivity2.mCurrentPage);
                if (CmeArticleActivity.this.mProgressBar != null) {
                    CmeArticleActivity.this.mProgressBar.setVisibility(4);
                }
            }
        }, this);
    }

    /* access modifiers changed from: private */
    public void setPageNumberTitleBar() {
        if (this.mCurrentPage >= 0) {
            setTitleBar((this.mCurrentPage + 1) + " of " + this.mArticle.pages.size());
            return;
        }
        setTitleBar("1 of " + this.mArticle.pages.size());
    }

    private void setUpArticleTestFragment(View view, QuestionState questionState, List<Question> list) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ArticleQuestionsFragment newInstance = ArticleQuestionsFragment.newInstance(list, this.mPassScore, isActivityExpired(), getTestScoreListener(questionState), this.mUserProfile);
        supportFragmentManager.beginTransaction().replace(view.getId(), (Fragment) newInstance, questionState.tag).commit();
        this.mArticleQuestionsFragments.put(questionState.tag, newInstance);
        if (questionState.isResponded && !questionState.isScorable) {
            for (String next : this.mArticleQuestionsFragments.keySet()) {
                ArticleQuestionsFragment articleQuestionsFragment = this.mArticleQuestionsFragments.get(next);
                if (next.equals(questionState.formId)) {
                    articleQuestionsFragment.disableAll();
                }
            }
        }
    }

    private ITestScoreListener getTestScoreListener(final QuestionState questionState) {
        return new ITestScoreListener() {
            public void onTestAnswerChanged(int i, boolean z) {
                if (questionState.isResponded && !questionState.isScorable) {
                    for (String str : CmeArticleActivity.this.mArticleQuestionsFragments.keySet()) {
                        ArticleQuestionsFragment articleQuestionsFragment = (ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(str);
                        if (str.equals(questionState.formId)) {
                            articleQuestionsFragment.disableAll();
                        }
                    }
                }
                if (!z) {
                    CmeArticleActivity cmeArticleActivity = CmeArticleActivity.this;
                    int unused = cmeArticleActivity.mTotalQuestionsRemainng = cmeArticleActivity.mTotalQuestionsRemainng - 1;
                } else if (questionState.tag.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    int unused2 = CmeArticleActivity.this.mTotalQuestionsRemainng = i;
                }
                CmeArticleActivity cmeArticleActivity2 = CmeArticleActivity.this;
                cmeArticleActivity2.setQuestionsContinueButtonState(cmeArticleActivity2.mTotalQuestionsRemainng, CmeArticleActivity.this.mCurrentQuestions.size(), questionState);
            }

            public void onAnswersSubmitted(boolean z, double d, QuestionResponse[] questionResponseArr, boolean z2) {
                CmeArticleActivity.this.handleAnswersSubmitted(z, questionResponseArr, z2, questionState);
                if ((questionState.formatType.equals("POST") || (questionState.formatType.equals("INTERNAL") && questionState.isScorable)) && !questionState.isResponded) {
                    questionState.isResponded = true;
                    try {
                        new CpEventsLoggingTask(new CPEvent(CmeArticleActivity.this).buildQnaParticipationEvent(CmeArticleActivity.this.mQuestionnaire.id, CmeArticleActivity.this.mArticle.contentGroup, CmeArticleActivity.this.mArticle.blockCode, CmeArticleActivity.this.mArticle.leadConcept, CmeArticleActivity.this.mArticle.leadSpec, CmeArticleActivity.this.mArticleId), CmeArticleActivity.this.mContext).execute(new Void[0]);
                    } catch (Exception e) {
                        Trace.e(CmeArticleActivity.TAG, e.getMessage());
                    }
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public void trackOmnitureAnswerSubmitted(boolean z, QuestionState questionState) {
        String str;
        String str2;
        if (questionState.isScorable && z) {
            str2 = this.mQuestionnaire.id;
            str = OmnitureData.MODULE_NAME_QNA_SCORABLE;
        } else if (questionState.isScorable) {
            str = String.format(OmnitureData.MODULE_NAME_QNA, new Object[]{this.mQuestionnaire.id, questionState.formId});
            str2 = OmnitureData.LINK_NAME_QNA_VALIDATION;
        } else {
            str = String.format(OmnitureData.MODULE_NAME_QNA, new Object[]{this.mQuestionnaire.id, questionState.formId});
            str2 = "success";
        }
        if (this.mFirstTestSubmission && questionState.isScorable) {
            WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(OmnitureData.MODULE_NAME_QNA_FIRST_SUBMISSION, this.mQuestionnaire.id, String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId})));
            this.mFirstTestSubmission = false;
        }
        if (!z) {
            WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(str, str2, String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId})));
            return;
        }
        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(String.format(OmnitureData.MODULE_NAME_QNA, new Object[]{this.mQuestionnaire.id, questionState.formId}), "success", String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId})));
        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(str, str2, String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId})));
    }

    /* access modifiers changed from: private */
    public void handleAnswersSubmitted(boolean z, QuestionResponse[] questionResponseArr, boolean z2, QuestionState questionState) {
        if (!Utilities.isNetworkAvailable(this)) {
            new CMEPulseException(getString(R.string.error_connection_required)).showSnackBar(this.mRootLayout, 0, getString(R.string.retry), new View.OnClickListener(z, questionResponseArr, z2, questionState) {
                public final /* synthetic */ boolean f$1;
                public final /* synthetic */ QuestionResponse[] f$2;
                public final /* synthetic */ boolean f$3;
                public final /* synthetic */ QuestionState f$4;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                    this.f$4 = r5;
                }

                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$handleAnswersSubmitted$11$CmeArticleActivity(this.f$1, this.f$2, this.f$3, this.f$4, view);
                }
            });
            this.mIsLastActionSubmit = false;
            this.mQuestionsContinueButton.setClickable(true);
            this.mQuestionsContinueButton.setEnabled(true);
            return;
        }
        submitAnswers(z, questionResponseArr, z2, questionState);
    }

    public /* synthetic */ void lambda$handleAnswersSubmitted$11$CmeArticleActivity(boolean z, QuestionResponse[] questionResponseArr, boolean z2, QuestionState questionState, View view) {
        submitAnswers(z, questionResponseArr, z2, questionState);
    }

    private void submitAnswers(boolean z, QuestionResponse[] questionResponseArr, boolean z2, QuestionState questionState) {
        QuestionRequest buildQuestionRequest = buildQuestionRequest(questionResponseArr, questionState.formatType, questionState.formId);
        this.mQuestionsContinueButton.setText("Submitting...");
        final QuestionState questionState2 = questionState;
        final boolean z3 = z;
        final QuestionRequest questionRequest = buildQuestionRequest;
        final boolean z4 = z2;
        this.mArticleProvider.submitQuestionResponseList(buildQuestionRequest, new ICallbackEvent<String, CMEPulseException>() {
            public void onError(CMEPulseException cMEPulseException) {
                CmeArticleActivity.this.enableQuestionsContinueButton();
                WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(String.format(OmnitureData.MODULE_NAME_QNA, new Object[]{CmeArticleActivity.this.mQuestionnaire.id, questionState2.formId}), "error", String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{CmeArticleActivity.this.mArticleId})));
                Snackbar make = Snackbar.make((View) CmeArticleActivity.this.mRootLayout, (CharSequence) "Service Unavailable.", -1);
                make.getView().setBackgroundColor(CmeArticleActivity.this.getResources().getColor(R.color.app_accent_color));
                make.show();
                CmeArticleActivity.this.mQuestionsContinueButton.setText("Submit");
                Trace.e(CmeArticleActivity.TAG, cMEPulseException.getMessage());
            }

            public void onCompleted(String str) {
                CmeArticleActivity.this.trackOmnitureAnswerSubmitted(z3, questionState2);
                boolean unused = CmeArticleActivity.this.mIsScorePassing = z3;
                int length = questionRequest.questionResponseList.length;
                boolean z = true;
                if (CmeArticleActivity.this.mArticleController.shouldShowAnimatePoll(questionState2) && CmeArticleActivity.this.mIsLastActionSubmit) {
                    for (String str2 : CmeArticleActivity.this.mArticleQuestionsFragments.keySet()) {
                        ((ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(str2)).animatePoll(false);
                    }
                    Snackbar make = Snackbar.make((View) CmeArticleActivity.this.mRootLayout, (CharSequence) "See your colleagues responses", -1);
                    make.getView().setBackgroundColor(CmeArticleActivity.this.getResources().getColor(R.color.app_accent_color));
                    make.show();
                } else if (!CmeArticleActivity.this.mIsLastActionSubmit || questionState2.isScorable) {
                    if (CmeArticleActivity.this.mIsScorePassing) {
                        try {
                            new CpEventsLoggingTask(new CPEvent(CmeArticleActivity.this).buildQnaCompletionEvent(CmeArticleActivity.this.mQuestionnaire.id, CmeArticleActivity.this.mArticle.contentGroup, CmeArticleActivity.this.mArticle.blockCode, CmeArticleActivity.this.mArticle.leadConcept, CmeArticleActivity.this.mArticle.leadConcept, CmeArticleActivity.this.mArticleId), CmeArticleActivity.this.mContext).execute(new Void[0]);
                        } catch (Exception e) {
                            Trace.e(CmeArticleActivity.TAG, e.getMessage());
                        }
                    } else if (!z4) {
                        for (String str3 : CmeArticleActivity.this.mArticleQuestionsFragments.keySet()) {
                            ArticleQuestionsFragment articleQuestionsFragment = (ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(str3);
                            if (str3.equals(questionState2.formId) && articleQuestionsFragment.getNextIncorrectQuestion() != null) {
                                CmeArticleActivity.this.mArticleController.goToQuestion(articleQuestionsFragment.getNextIncorrectQuestion());
                            }
                        }
                        if (CmeArticleActivity.this.mIsLastActionSubmit) {
                            CmeArticleActivity.this.showMinScoreReqSnackBar();
                            boolean unused2 = CmeArticleActivity.this.mIsLastActionSubmit = false;
                        }
                    } else if (CmeArticleActivity.this.mIsLastActionSubmit) {
                        CmeArticleActivity.this.showMinScoreReqSnackBar();
                        for (String str4 : CmeArticleActivity.this.mArticleQuestionsFragments.keySet()) {
                            ArticleQuestionsFragment articleQuestionsFragment2 = (ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(str4);
                            articleQuestionsFragment2.displayValidation();
                            if (str4.equals(questionState2.formId) && articleQuestionsFragment2.getNextIncorrectQuestion() != null) {
                                CmeArticleActivity.this.mArticleController.goToQuestion(articleQuestionsFragment2.getNextIncorrectQuestion());
                            }
                        }
                        boolean unused3 = CmeArticleActivity.this.mIsLastActionSubmit = false;
                    } else if (!questionState2.isScorable) {
                        for (String str5 : CmeArticleActivity.this.mArticleQuestionsFragments.keySet()) {
                            ((ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(str5)).displayValidationDisableAllQuestions();
                        }
                    }
                    z = false;
                } else {
                    CmeArticleActivity.this.displayQuestionsSubmittedSnackBar(length);
                }
                CmeArticleActivity.this.setQuestionsContinueButtonState(0, length, questionState2, z);
                if (questionState2.isResponded && !questionState2.isScorable) {
                    for (String str6 : CmeArticleActivity.this.mArticleQuestionsFragments.keySet()) {
                        ArticleQuestionsFragment articleQuestionsFragment3 = (ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(str6);
                        if (str6.equals(questionState2.formId)) {
                            articleQuestionsFragment3.disableAll();
                        }
                    }
                }
                if (!questionState2.isResponded && !questionState2.isScorable) {
                    for (String str7 : CmeArticleActivity.this.mArticleQuestionsFragments.keySet()) {
                        ArticleQuestionsFragment articleQuestionsFragment4 = (ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(str7);
                        if (str7.equals(questionState2.formId)) {
                            articleQuestionsFragment4.disableAll();
                        }
                    }
                }
                if (questionState2.tag.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                    ArticleQuestionsFragment articleQuestionsFragment5 = (ArticleQuestionsFragment) CmeArticleActivity.this.mArticleQuestionsFragments.get(ExifInterface.GPS_MEASUREMENT_3D);
                    if (articleQuestionsFragment5.questionAnswer != null) {
                        articleQuestionsFragment5.questionAnswer.setEnabled(false);
                    }
                }
            }
        }, this);
    }

    /* access modifiers changed from: private */
    public void setQuestionsContinueButtonState(int i, int i2, QuestionState questionState) {
        setQuestionsContinueButtonState(i, i2, questionState, false);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setQuestionsContinueButtonState(int r10, int r11, com.webmd.wbmdcmepulse.models.articles.QuestionState r12, boolean r13) {
        /*
            r9 = this;
            boolean r13 = r9.hasQna
            java.lang.String r0 = "This Activity has Expired"
            r1 = -1
            r2 = 0
            if (r13 == 0) goto L_0x0117
            com.webmd.wbmdcmepulse.models.articles.Questionnaire r13 = r9.mQuestionnaire
            boolean r13 = r13.isPassed
            r3 = 1
            if (r13 != 0) goto L_0x001e
            com.webmd.wbmdcmepulse.models.articles.Questionnaire r13 = r9.mQuestionnaire
            double r4 = r13.initialParsedAnswersScore
            double r6 = r9.mPassScore
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 < 0) goto L_0x001b
            r4 = 1
            goto L_0x001c
        L_0x001b:
            r4 = 0
        L_0x001c:
            r13.isPassed = r4
        L_0x001e:
            com.webmd.wbmdcmepulse.models.articles.Questionnaire r13 = r9.mQuestionnaire
            boolean r13 = r13.getShouldGoToEvaluation()
            if (r13 == 0) goto L_0x0031
            com.webmd.wbmdcmepulse.models.articles.Questionnaire r13 = r9.mQuestionnaire
            boolean r13 = r13.evaluationCompleted
            if (r13 != 0) goto L_0x0031
            r9.setContinueButtonToEarnCmeCredit()
            goto L_0x0109
        L_0x0031:
            com.webmd.wbmdcmepulse.models.articles.Questionnaire r13 = r9.mQuestionnaire
            boolean r13 = r13.isPassed
            if (r13 == 0) goto L_0x005f
            boolean r13 = r9.isCurrentPageLastPage()
            if (r13 == 0) goto L_0x005f
            android.widget.Button r10 = r9.mQuestionsContinueButton
            java.lang.String r11 = "You have already completed this activity"
            r10.setText(r11)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setTextColor(r1)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            android.content.res.Resources r11 = r9.getResources()
            int r12 = com.webmd.wbmdcmepulse.R.drawable.grey_button_ripple
            android.graphics.drawable.Drawable r11 = r11.getDrawable(r12)
            r10.setBackgroundDrawable(r11)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setClickable(r2)
            goto L_0x010a
        L_0x005f:
            boolean r13 = r9.isActivityExpired()
            if (r13 == 0) goto L_0x008b
            boolean r13 = r9.isCurrentPageLastPage()
            if (r13 == 0) goto L_0x008b
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setText(r0)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setTextColor(r1)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            android.content.res.Resources r11 = r9.getResources()
            int r12 = com.webmd.wbmdcmepulse.R.drawable.grey_button_ripple
            android.graphics.drawable.Drawable r11 = r11.getDrawable(r12)
            r10.setBackgroundDrawable(r11)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setClickable(r2)
            goto L_0x010a
        L_0x008b:
            boolean r13 = r9.isCurrentPageLastPage()
            if (r13 == 0) goto L_0x00ab
            if (r11 == 0) goto L_0x00a7
            if (r10 != 0) goto L_0x009d
            boolean r13 = r12.isScorable
            if (r13 != 0) goto L_0x009d
            boolean r13 = r9.mIsLastActionSubmit
            if (r13 != 0) goto L_0x00a7
        L_0x009d:
            if (r10 != 0) goto L_0x00ab
            boolean r13 = r12.isScorable
            if (r13 != 0) goto L_0x00ab
            boolean r13 = r12.isResponded
            if (r13 == 0) goto L_0x00ab
        L_0x00a7:
            r9.setContinueButtonToEarnCmeCredit()
            goto L_0x0109
        L_0x00ab:
            com.webmd.wbmdcmepulse.controllers.ArticleController r13 = r9.mArticleController
            boolean r13 = r13.isPollableQuestion(r12)
            if (r13 == 0) goto L_0x00bb
            boolean r13 = r9.mIsLastActionSubmit
            if (r13 == 0) goto L_0x00bb
            r9.setContinueButtonToNextGoToNextPage()
            goto L_0x0109
        L_0x00bb:
            com.webmd.wbmdcmepulse.controllers.ArticleController r13 = r9.mArticleController
            boolean r13 = r13.isPollableQuestion(r12)
            if (r13 == 0) goto L_0x00c7
            r9.setQuestionsContinueButtonToSubmit(r12, r10)
            goto L_0x0109
        L_0x00c7:
            if (r12 == 0) goto L_0x00df
            boolean r13 = r9.isCurrentPageLastPage()
            if (r13 == 0) goto L_0x00df
            boolean r13 = r12.isScorable
            if (r13 != 0) goto L_0x00df
            boolean r13 = r12.isResponded
            if (r13 != 0) goto L_0x00db
            boolean r13 = r9.mIsLastActionSubmit
            if (r13 == 0) goto L_0x00df
        L_0x00db:
            r9.setContinueButtonToEarnCmeCredit()
            goto L_0x0109
        L_0x00df:
            if (r12 == 0) goto L_0x00ed
            boolean r13 = r12.isResponded
            if (r13 == 0) goto L_0x00ed
            boolean r13 = r12.isScorable
            if (r13 != 0) goto L_0x00ed
            r9.setContinueButtonToNextGoToNextPage()
            goto L_0x0109
        L_0x00ed:
            if (r11 == 0) goto L_0x0106
            if (r12 == 0) goto L_0x00fa
            boolean r11 = r12.isScorable
            if (r11 != 0) goto L_0x00fa
            boolean r11 = r9.mIsLastActionSubmit
            if (r11 == 0) goto L_0x00fa
            goto L_0x0106
        L_0x00fa:
            boolean r11 = r9.mIsScorePassing
            if (r11 == 0) goto L_0x0102
            r9.goToCongratulationsActivity()
            goto L_0x0109
        L_0x0102:
            r9.setQuestionsContinueButtonToSubmit(r12, r10)
            goto L_0x0109
        L_0x0106:
            r9.setContinueButtonToNextGoToNextPage()
        L_0x0109:
            r2 = 1
        L_0x010a:
            if (r2 == 0) goto L_0x013a
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setEnabled(r3)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setClickable(r3)
            goto L_0x013a
        L_0x0117:
            boolean r10 = r9.isActivityExpired()
            r11 = 4
            if (r10 == 0) goto L_0x012e
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setText(r0)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setTextColor(r1)
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setClickable(r2)
            goto L_0x0133
        L_0x012e:
            android.widget.Button r10 = r9.mQuestionsContinueButton
            r10.setVisibility(r11)
        L_0x0133:
            android.widget.ProgressBar r10 = r9.mProgressBar
            if (r10 == 0) goto L_0x013a
            r10.setVisibility(r11)
        L_0x013a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.activities.CmeArticleActivity.setQuestionsContinueButtonState(int, int, com.webmd.wbmdcmepulse.models.articles.QuestionState, boolean):void");
    }

    /* access modifiers changed from: private */
    public void enableQuestionsContinueButton() {
        Button button = this.mQuestionsContinueButton;
        if (button != null) {
            button.postDelayed(new Runnable() {
                public final void run() {
                    CmeArticleActivity.this.lambda$enableQuestionsContinueButton$12$CmeArticleActivity();
                }
            }, 1000);
        }
    }

    public /* synthetic */ void lambda$enableQuestionsContinueButton$12$CmeArticleActivity() {
        this.mQuestionsContinueButton.setEnabled(true);
        this.mQuestionsContinueButton.setClickable(true);
    }

    private void goToCongratulationsActivity() {
        Intent intent = new Intent(this, CmeCongratulationsActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, this.mArticle.title);
        intent.putExtra(Constants.BUNDLE_KEY_QNA_ID, this.mQuestionnaire.id);
        intent.putExtra(Constants.BUNDLE_KEY_QNA, this.mQuestionnaire);
        intent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, this.mIsEvaluationRequired);
        intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, this.mArticle.isMocEligible);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
        intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
        intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.mArticle.assetId);
        startActivity(intent);
    }

    private void goToEvaluationScreenviaContinueScreen(boolean z) {
        Intent intent = new Intent(this, CmeContinueActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, this.mArticle.title);
        intent.putExtra(Constants.BUNDLE_KEY_QNA_ID, this.mQuestionnaire.id);
        intent.putExtra(Constants.BUNDLE_KEY_QNA, this.mQuestionnaire);
        intent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, this.mIsEvaluationRequired);
        intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, this.mArticle.isMocEligible);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
        intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
        intent.putExtra(Constants.BUNDLE_KEY_EVAL_STAND_ALONE, z);
        intent.putExtra(Constants.BUNDLE_KEY_GOTO_EVAL, true);
        startActivity(intent);
    }

    private boolean isCurrentPageLastPage() {
        return this.mCurrentPage == this.mArticle.pages.size() - 1;
    }

    private void setContinueButtonToNextGoToNextPage() {
        this.mQuestionsContinueButton.setTextColor(-1);
        this.mQuestionsContinueButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.cme_continue_button_ripple));
        this.mQuestionsContinueButton.setText("Continue to Next Page");
        this.mQuestionsContinueButton.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CmeArticleActivity.this.lambda$setContinueButtonToNextGoToNextPage$13$CmeArticleActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$setContinueButtonToNextGoToNextPage$13$CmeArticleActivity(View view) {
        disableQuestionsContinueButton();
        this.mIsLastActionSubmit = false;
        int i = this.mCurrentPage + 1;
        this.mCurrentPage = i;
        goToPage(i);
    }

    private void disableQuestionsContinueButton() {
        this.mQuestionsContinueButton.setEnabled(false);
        this.mQuestionsContinueButton.setEnabled(false);
    }

    private void goToPage(int i) {
        this.mRootLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
        this.mRootLayout.postDelayed(new Runnable(i) {
            public final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                CmeArticleActivity.this.lambda$goToPage$15$CmeArticleActivity(this.f$1);
            }
        }, 600);
    }

    public /* synthetic */ void lambda$goToPage$15$CmeArticleActivity(int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.fade_in);
        this.mRootLayout.setVisibility(4);
        this.mIsNavigating = true;
        this.mCurrentPage = i;
        setPageNumberTitleBar();
        trackOmniturePaging();
        setUpSections(this.mArticle, i);
        this.mScrollView.scrollTo(0, 0);
        this.mIsNavigating = false;
        this.mRootLayout.startAnimation(loadAnimation);
        this.mRootLayout.postDelayed(new Runnable() {
            public final void run() {
                CmeArticleActivity.this.lambda$null$14$CmeArticleActivity();
            }
        }, 300);
    }

    public /* synthetic */ void lambda$null$14$CmeArticleActivity() {
        this.mRootLayout.setVisibility(0);
    }

    private void pageBack() {
        this.mIsNavigating = true;
        this.mArticleProvider.getQna(this.mArticle.qnaId, new ICallbackEvent<Questionnaire, CMEPulseException>() {
            public void onError(CMEPulseException cMEPulseException) {
                cMEPulseException.showSnackBar(CmeArticleActivity.this.mRootLayout, 0, (String) null, (View.OnClickListener) null);
                boolean unused = CmeArticleActivity.this.mIsNavigating = false;
            }

            public void onCompleted(Questionnaire questionnaire) {
                CmeArticleActivity.this.mRootLayout.startAnimation(AnimationUtils.loadAnimation(CmeArticleActivity.this.mContext, R.anim.fade_out));
                CmeArticleActivity.this.mRootLayout.postDelayed(new Runnable(questionnaire) {
                    public final /* synthetic */ Questionnaire f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        CmeArticleActivity.AnonymousClass11.this.lambda$onCompleted$1$CmeArticleActivity$11(this.f$1);
                    }
                }, 600);
            }

            public /* synthetic */ void lambda$onCompleted$1$CmeArticleActivity$11(Questionnaire questionnaire) {
                Animation loadAnimation = AnimationUtils.loadAnimation(CmeArticleActivity.this.mContext, R.anim.fade_in);
                CmeArticleActivity.this.mRootLayout.setVisibility(4);
                Questionnaire unused = CmeArticleActivity.this.mQuestionnaire = questionnaire;
                CmeArticleActivity cmeArticleActivity = CmeArticleActivity.this;
                int unused2 = cmeArticleActivity.mCurrentPage = cmeArticleActivity.mCurrentPage - 1;
                CmeArticleActivity.this.setPageNumberTitleBar();
                CmeArticleActivity.this.setUpReferencesTextView();
                CmeArticleActivity.this.trackOmniturePaging();
                CmeArticleActivity cmeArticleActivity2 = CmeArticleActivity.this;
                cmeArticleActivity2.setUpSections(cmeArticleActivity2.mArticle, CmeArticleActivity.this.mCurrentPage);
                boolean unused3 = CmeArticleActivity.this.mIsNavigating = false;
                CmeArticleActivity.this.mRootLayout.startAnimation(loadAnimation);
                CmeArticleActivity.this.mRootLayout.postDelayed(new Runnable() {
                    public final void run() {
                        CmeArticleActivity.AnonymousClass11.this.lambda$null$0$CmeArticleActivity$11();
                    }
                }, 300);
            }

            public /* synthetic */ void lambda$null$0$CmeArticleActivity$11() {
                CmeArticleActivity.this.mRootLayout.setVisibility(0);
            }
        }, this);
    }

    private void setQuestionsContinueButtonToSubmit(QuestionState questionState, int i) {
        if (i > 0) {
            this.mQuestionsContinueButton.setTextColor(-1);
            this.mQuestionsContinueButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.grey_button_ripple));
            if (i == 1) {
                Button button = this.mQuestionsContinueButton;
                button.setText(i + " Question Remaining to Proceed");
            } else {
                Button button2 = this.mQuestionsContinueButton;
                button2.setText(i + " Questions Remaining to Proceed");
            }
            this.mQuestionsContinueButton.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$setQuestionsContinueButtonToSubmit$17$CmeArticleActivity(view);
                }
            });
            return;
        }
        this.mQuestionsContinueButton.setText("Submit");
        this.mQuestionsContinueButton.setTextColor(-1);
        this.mQuestionsContinueButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.cme_continue_button_ripple));
        this.mQuestionsContinueButton.setOnClickListener(new View.OnClickListener(questionState) {
            public final /* synthetic */ QuestionState f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                CmeArticleActivity.this.lambda$setQuestionsContinueButtonToSubmit$18$CmeArticleActivity(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$setQuestionsContinueButtonToSubmit$17$CmeArticleActivity(View view) {
        disableQuestionsContinueButton();
        new Handler().post(new Runnable() {
            public final void run() {
                CmeArticleActivity.this.lambda$null$16$CmeArticleActivity();
            }
        });
    }

    public /* synthetic */ void lambda$null$16$CmeArticleActivity() {
        for (String str : this.mArticleQuestionsFragments.keySet()) {
            View nextAnswerableQuestion = this.mArticleQuestionsFragments.get(str).getNextAnswerableQuestion();
            if (nextAnswerableQuestion != null) {
                nextAnswerableQuestion.setFocusable(true);
                nextAnswerableQuestion.setFocusableInTouchMode(true);
                nextAnswerableQuestion.requestFocus();
                nextAnswerableQuestion.clearFocus();
                return;
            }
        }
    }

    public /* synthetic */ void lambda$setQuestionsContinueButtonToSubmit$18$CmeArticleActivity(QuestionState questionState, View view) {
        disableQuestionsContinueButton();
        this.mIsLastActionSubmit = true;
        ((ArticleQuestionsFragment) getSupportFragmentManager().findFragmentByTag(questionState.tag)).submitAnswers();
    }

    /* access modifiers changed from: private */
    public void displayQuestionsSubmittedSnackBar(int i) {
        if (i > 0) {
            Snackbar make = Snackbar.make((View) this.mRootLayout, (CharSequence) "Thanks! Your answers have been submitted", -1);
            make.getView().setBackgroundColor(getResources().getColor(R.color.app_accent_color));
            make.show();
        }
    }

    private QuestionRequest buildQuestionRequest(QuestionResponse[] questionResponseArr, String str, String str2) {
        QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.questionResponseList = questionResponseArr;
        questionRequest.questionnaireId = this.mQuestionnaire.id;
        questionRequest.formId = str2;
        questionRequest.guid = this.mUserProfile.getBasicProfile().getUserId();
        questionRequest.questionType = str;
        return questionRequest;
    }

    private void setTitleBar(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void fadeInVideoControlsLayout() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(250);
        alphaAnimation.setStartOffset(0);
        alphaAnimation.setFillAfter(true);
        this.mControlsLayout.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                boolean unused = CmeArticleActivity.this.mAreVideoControlsVisible = true;
            }

            public void onAnimationEnd(Animation animation) {
                CmeArticleActivity.this.mControlsLayout.setVisibility(0);
                CmeArticleActivity.this.mControlsLayout.requestLayout();
                CmeArticleActivity.this.mPlayPauseButton.postDelayed(new Runnable() {
                    public final void run() {
                        CmeArticleActivity.AnonymousClass12.this.lambda$onAnimationEnd$0$CmeArticleActivity$12();
                    }
                }, 100);
            }

            public /* synthetic */ void lambda$onAnimationEnd$0$CmeArticleActivity$12() {
                CmeArticleActivity.this.mPlayPauseButton.setEnabled(true);
            }
        });
    }

    private void initializeSlides(Article article) {
        if (article.slides == null || article.slides.size() <= 0) {
            this.mHasSlides = false;
            return;
        }
        this.mHasSlides = true;
        ViewPager viewPager = (ViewPager) findViewById(R.id.slides_pager);
        this.mSlidesPager = viewPager;
        viewPager.setOffscreenPageLimit(2);
        initializeSlideImagePagerAdapter();
    }

    /* access modifiers changed from: private */
    public void initializeSlideImagePagerAdapter() {
        int i;
        if (this.mSlidesPager != null) {
            $$Lambda$CmeArticleActivity$FSZih32uIegEEBFfNy7EuBdv5GY r7 = new IViewPagerClickListener() {
                public final void onSlideClicked(int i) {
                    CmeArticleActivity.this.lambda$initializeSlideImagePagerAdapter$19$CmeArticleActivity(i);
                }
            };
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            final int i2 = displayMetrics.widthPixels;
            if (Utilities.isPhone() || getResources().getConfiguration().orientation != 2) {
                i = ExtensionsAspectRatio.getAdjustedHeight(4, 3, i2);
            } else {
                i2 = displayMetrics.widthPixels - ((int) (((float) displayMetrics.widthPixels) * 0.25f));
                i = ExtensionsAspectRatio.getAdjustedHeight(16, 9, displayMetrics.widthPixels);
            }
            this.mSlidesPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    if (!Utilities.isNetworkAvailable(CmeArticleActivity.this)) {
                        new CMEPulseException(CmeArticleActivity.this.getString(R.string.error_connection_required)).showSnackBar(CmeArticleActivity.this.mRootLayout, 0, (String) null, (View.OnClickListener) null);
                    }
                    boolean z = CmeArticleActivity.this.mCurrentSlidePosition != i;
                    int unused = CmeArticleActivity.this.mCurrentSlidePosition = i;
                    if (!CmeArticleActivity.this.mIsSettingImagePageManual && z) {
                        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(OmnitureData.MODULE_NAME_SLIDES_SWIPE, Integer.toString(i + 1), String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{CmeArticleActivity.this.mArticleId})));
                    }
                }
            });
            this.mSlidesPager.getLayoutParams().width = i2;
            this.mSlidesPager.getLayoutParams().height = i;
            final int adjustedHeight = ExtensionsAspectRatio.getAdjustedHeight(4, 3, i2);
            ScreenSlidePagerAdapter screenSlidePagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), this.mArticle.slides, this.mContext, this.mArticleId, false, r7, new IImageLoadedEvent() {
                public void loadImage(String str, ImageView imageView) {
                }

                public void loadImage(String str, ImageView imageView, int i, int i2) {
                }

                public void onImageLoaded(ImageView imageView, Bitmap bitmap) {
                    CmeArticleActivity.this.mSlidesPager.post(new Runnable(imageView, adjustedHeight, i2) {
                        public final /* synthetic */ ImageView f$1;
                        public final /* synthetic */ int f$2;
                        public final /* synthetic */ int f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        public final void run() {
                            CmeArticleActivity.AnonymousClass14.this.lambda$onImageLoaded$1$CmeArticleActivity$14(this.f$1, this.f$2, this.f$3);
                        }
                    });
                }

                public /* synthetic */ void lambda$onImageLoaded$1$CmeArticleActivity$14(ImageView imageView, int i, int i2) {
                    imageView.setMinimumHeight(i);
                    imageView.setMaxHeight(i);
                    imageView.setMinimumWidth(i2);
                    imageView.setMaxWidth(i2);
                    imageView.post(new Runnable(imageView) {
                        public final /* synthetic */ ImageView f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            CmeArticleActivity.AnonymousClass14.this.lambda$null$0$CmeArticleActivity$14(this.f$1);
                        }
                    });
                }

                public /* synthetic */ void lambda$null$0$CmeArticleActivity$14(ImageView imageView) {
                    CmeArticleActivity.this.mSlidesPager.requestLayout();
                    ViewPager access$100 = CmeArticleActivity.this.mSlidesPager;
                    imageView.getClass();
                    access$100.post(new Runnable(imageView) {
                        public final /* synthetic */ ImageView f$0;

                        {
                            this.f$0 = r1;
                        }

                        public final void run() {
                            this.f$0.requestLayout();
                        }
                    });
                }
            });
            if (Utilities.isPhone()) {
                this.mSlidesPager.setPageTransformer(true, new ZoomOutPageTransformer());
            }
            this.mSlidesPager.setAdapter(screenSlidePagerAdapter);
            int i3 = this.mCurrentSlidePosition;
            if (i3 > 0) {
                this.mSlidesPager.setCurrentItem(i3);
            }
        }
    }

    public /* synthetic */ void lambda$initializeSlideImagePagerAdapter$19$CmeArticleActivity(int i) {
        this.mIsSettingImagePageManual = true;
        Intent intent = new Intent(this.mContext, CmeImageViewerActivity.class);
        intent.putParcelableArrayListExtra("bundle_key_image_slides", this.mArticle.slides);
        intent.putExtra("bundle_key_image_slide_position", this.mSlidesPager.getCurrentItem());
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
        intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.mArticle.assetId);
        startActivityForResult(intent, 5002);
    }

    private void setFullScreenModeParams() {
        this.mRootLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        getSupportActionBar().hide();
        getWindow().setFlags(1024, 1024);
        this.mQuestionsContinueButton.setVisibility(8);
        ViewPager viewPager = this.mSlidesPager;
        if (viewPager != null) {
            viewPager.setVisibility(8);
        }
        TabLayout tabLayout = this.mMediaSlidesTabLayout;
        if (tabLayout != null) {
            tabLayout.setVisibility(8);
        }
        this.mNonVideoContentLayout.setVisibility(8);
        this.mFullScreenButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.mContext.getResources().getDrawable(R.drawable.ic_fullscreen_exit_white_24dp), (Drawable) null);
        this.mVideoView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                CmeArticleActivity.this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                if (CmeArticleActivity.this.getResources().getConfiguration().orientation == 1) {
                    CmeArticleActivity.this.mVideoView.postDelayed(new Runnable(displayMetrics) {
                        public final /* synthetic */ DisplayMetrics f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            CmeArticleActivity.AnonymousClass15.this.lambda$onGlobalLayout$1$CmeArticleActivity$15(this.f$1);
                        }
                    }, 300);
                } else if (CmeArticleActivity.this.getResources().getConfiguration().orientation == 2) {
                    CmeArticleActivity.this.mVideoView.postDelayed(new Runnable(displayMetrics) {
                        public final /* synthetic */ DisplayMetrics f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            CmeArticleActivity.AnonymousClass15.this.lambda$onGlobalLayout$3$CmeArticleActivity$15(this.f$1);
                        }
                    }, 300);
                }
                int i = displayMetrics.widthPixels;
                if (CmeArticleActivity.this.mVideoView.canPause() && i > 0) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, -2);
                    layoutParams.addRule(12);
                    layoutParams.addRule(14);
                    CmeArticleActivity.this.mControlsLayout.setLayoutParams(layoutParams);
                    try {
                        if (Build.VERSION.SDK_INT < 16) {
                            CmeArticleActivity.this.mVideoView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            CmeArticleActivity.this.mVideoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    } catch (Exception unused) {
                        Trace.w(CmeArticleActivity.TAG, "Failed to remove global layout listener");
                    }
                }
            }

            public /* synthetic */ void lambda$onGlobalLayout$1$CmeArticleActivity$15(DisplayMetrics displayMetrics) {
                int i;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) CmeArticleActivity.this.mVideoContainerRelativeLatout.getLayoutParams();
                ViewGroup.LayoutParams layoutParams2 = CmeArticleActivity.this.mVideoView.getLayoutParams();
                if (Extensions.isStringNullOrEmpty(CmeArticleActivity.this.mVideoAspectRadio) || !CmeArticleActivity.this.mVideoAspectRadio.equals("16x9")) {
                    i = ExtensionsAspectRatio.getAdjustedHeight(4, 3, displayMetrics.widthPixels);
                } else {
                    i = ExtensionsAspectRatio.getAdjustedHeight(16, 9, displayMetrics.widthPixels);
                }
                layoutParams.width = displayMetrics.widthPixels;
                layoutParams.height = i;
                layoutParams2.width = displayMetrics.widthPixels;
                layoutParams2.height = i;
                CmeArticleActivity.this.mVideoView.setLayoutParams(layoutParams2);
                CmeArticleActivity.this.mVideoView.setMinimumWidth(displayMetrics.widthPixels);
                ((FrameLayout.LayoutParams) ((LinearLayout) CmeArticleActivity.this.findViewById(R.id.scroll_view_wrapper)).getLayoutParams()).setMargins(0, (displayMetrics.heightPixels / 2) - (CmeArticleActivity.this.mVideoContainerRelativeLatout.getLayoutParams().height / 2), 0, 0);
                CmeArticleActivity.this.mVideoView.post(new Runnable() {
                    public final void run() {
                        CmeArticleActivity.AnonymousClass15.this.lambda$null$0$CmeArticleActivity$15();
                    }
                });
            }

            public /* synthetic */ void lambda$null$0$CmeArticleActivity$15() {
                CmeArticleActivity.this.mVideoView.requestLayout();
            }

            public /* synthetic */ void lambda$onGlobalLayout$3$CmeArticleActivity$15(DisplayMetrics displayMetrics) {
                int i;
                int i2;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) CmeArticleActivity.this.mVideoContainerRelativeLatout.getLayoutParams();
                ViewGroup.LayoutParams layoutParams2 = CmeArticleActivity.this.mVideoView.getLayoutParams();
                if (Extensions.isStringNullOrEmpty(CmeArticleActivity.this.mVideoAspectRadio) || !CmeArticleActivity.this.mVideoAspectRadio.equals("16x9")) {
                    i = ExtensionsAspectRatio.getAdjustedWidth(4, 3, displayMetrics.heightPixels);
                    i2 = displayMetrics.heightPixels;
                } else {
                    i2 = ExtensionsAspectRatio.getAdjustedHeight(16, 9, displayMetrics.widthPixels);
                    i = displayMetrics.widthPixels;
                }
                layoutParams.width = i;
                layoutParams.height = i2;
                layoutParams2.width = displayMetrics.widthPixels;
                layoutParams2.height = i2;
                CmeArticleActivity.this.mVideoView.setLayoutParams(layoutParams2);
                CmeArticleActivity.this.mVideoView.setMinimumWidth(displayMetrics.widthPixels);
                ((FrameLayout.LayoutParams) ((LinearLayout) CmeArticleActivity.this.findViewById(R.id.scroll_view_wrapper)).getLayoutParams()).setMargins(0, (displayMetrics.heightPixels / 2) - (CmeArticleActivity.this.mVideoContainerRelativeLatout.getLayoutParams().height / 2), 0, 0);
                CmeArticleActivity.this.mVideoView.post(new Runnable() {
                    public final void run() {
                        CmeArticleActivity.AnonymousClass15.this.lambda$null$2$CmeArticleActivity$15();
                    }
                });
            }

            public /* synthetic */ void lambda$null$2$CmeArticleActivity$15() {
                CmeArticleActivity.this.mVideoView.requestLayout();
            }
        });
    }

    private void setDefaultVideoParams(boolean z) {
        this.mIsFullScreenActive = false;
        this.mRootLayout.setBackgroundColor(getResources().getColor(R.color.backcolor));
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
        getWindow().clearFlags(1024);
        this.mNonVideoContentLayout.setVisibility(0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoContainerRelativeLatout.getLayoutParams();
        if (getResources().getConfiguration().orientation == 1 || Utilities.isPhone()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            layoutParams.width = displayMetrics.widthPixels < displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
            layoutParams.height = ExtensionsAspectRatio.getAdjustedHeight(4, 3, layoutParams.width);
        } else {
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
            int i = displayMetrics2.widthPixels - ((int) (((float) displayMetrics2.widthPixels) * 0.25f));
            int adjustedHeight = ExtensionsAspectRatio.getAdjustedHeight(16, 9, displayMetrics2.widthPixels);
            layoutParams.width = i;
            layoutParams.height = adjustedHeight;
            this.mVideoView.getLayoutParams().width = i;
            this.mVideoView.getLayoutParams().height = adjustedHeight;
        }
        ((FrameLayout.LayoutParams) ((LinearLayout) findViewById(R.id.scroll_view_wrapper)).getLayoutParams()).setMargins(0, 0, 0, 0);
        if (!z) {
            return;
        }
        if (this.mHasSlides && this.mHasVideo) {
            this.mArticleController.setViewVisibile(this.mVideoContainerRelativeLatout);
            this.mArticleController.setViewVisibile(this.mMediaSlidesTabLayout);
        } else if (this.mHasSlides) {
            this.mArticleController.setViewVisibile(this.mSlidesPager);
        } else if (this.mHasVideo) {
            this.mArticleController.setViewVisibile(this.mVideoContainerRelativeLatout);
        }
    }

    private void initializeVideo() {
        this.mHasVideo = false;
        if (!Extensions.isStringNullOrEmpty(this.mArticle.videoUrl)) {
            this.mHasVideo = true;
            this.mIsFullScreenActive = false;
            this.mAreVideoControlsVisible = true;
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.video_progress_bar);
            this.mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
            TextView textView = (TextView) findViewById(R.id.time_elapsed_text_view);
            this.mTimeElapsedTextView = textView;
            int i = this.mCurrentVideoPosition;
            if (i <= 0) {
                textView.setText("00:00");
            } else {
                this.mTimeElapsedTextView.setText(getMinutesAndSecondsFromMilliseconds(i));
            }
            this.mTimeRemainingTextView = (TextView) findViewById(R.id.time_remaining_text_view);
            this.mVideoContainerRelativeLatout = (RelativeLayout) findViewById(R.id.video_container);
            this.mControlsLayout = (RelativeLayout) findViewById(R.id.controls_layout);
            MediaController mediaController = new MediaController(this);
            VideoView videoView = (VideoView) findViewById(R.id.main_video_view);
            this.mVideoView = videoView;
            videoView.setMediaController(mediaController);
            mediaController.setVisibility(4);
            this.mVideoView.setVideoURI(Uri.parse(this.mArticle.videoUrl));
            resizeControlsToMatchVideoWidth();
            Button button = (Button) findViewById(R.id.close_caption_button);
            CloseCaptionTextView closeCaptionTextView = (CloseCaptionTextView) findViewById(R.id.close_caption_text);
            button.setVisibility(8);
            closeCaptionTextView.setVisibility(8);
            button.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.lambda$initializeVideo$20(CloseCaptionTextView.this, view);
                }
            });
            if (!Extensions.isStringNullOrEmpty(this.mArticle.videoCCURL)) {
                CloseCaptionProvider.getCloseCaptions(this, this.mArticle.videoCCURL, new CloseCaptionProvider.CloseCaptionListener(button, closeCaptionTextView) {
                    public final /* synthetic */ Button f$1;
                    public final /* synthetic */ CloseCaptionTextView f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onCaptionsRecieved(ArrayList arrayList) {
                        CmeArticleActivity.this.lambda$initializeVideo$21$CmeArticleActivity(this.f$1, this.f$2, arrayList);
                    }
                });
            }
            this.mFullScreenButton = (Button) findViewById(R.id.full_screen_button);
            Button button2 = (Button) findViewById(R.id.play_pause_button);
            this.mPlayPauseButton = button2;
            button2.setVisibility(4);
            setDefaultVideoParams(false);
            this.mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    return CmeArticleActivity.this.lambda$initializeVideo$24$CmeArticleActivity(mediaPlayer, i, i2);
                }
            });
            this.mVideoView.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return CmeArticleActivity.this.lambda$initializeVideo$25$CmeArticleActivity(view, motionEvent);
                }
            });
            this.mControlsLayout.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return CmeArticleActivity.this.lambda$initializeVideo$26$CmeArticleActivity(view, motionEvent);
                }
            });
            this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener(progressBar) {
                public final /* synthetic */ ProgressBar f$1;

                {
                    this.f$1 = r2;
                }

                public final void onPrepared(MediaPlayer mediaPlayer) {
                    CmeArticleActivity.this.lambda$initializeVideo$30$CmeArticleActivity(this.f$1, mediaPlayer);
                }
            });
            this.mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public final void onCompletion(MediaPlayer mediaPlayer) {
                    CmeArticleActivity.this.lambda$initializeVideo$31$CmeArticleActivity(mediaPlayer);
                }
            });
            SeekBar seekBar = this.mSeekBar;
            if (seekBar != null) {
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                        if (z) {
                            CmeArticleActivity.this.mVideoView.seekTo(i);
                        }
                    }
                });
            }
            this.mFullScreenButton.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return CmeArticleActivity.this.lambda$initializeVideo$32$CmeArticleActivity(view, motionEvent);
                }
            });
            this.mTimeRemainingTextView.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return CmeArticleActivity.this.lambda$initializeVideo$33$CmeArticleActivity(view, motionEvent);
                }
            });
            this.mTimeElapsedTextView.setOnTouchListener(new View.OnTouchListener() {
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return CmeArticleActivity.this.lambda$initializeVideo$34$CmeArticleActivity(view, motionEvent);
                }
            });
            this.mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    CmeArticleActivity.this.lambda$initializeVideo$36$CmeArticleActivity(view);
                }
            });
        }
    }

    static /* synthetic */ void lambda$initializeVideo$20(CloseCaptionTextView closeCaptionTextView, View view) {
        if (closeCaptionTextView.getVisibility() == 0) {
            closeCaptionTextView.setVisibility(8);
        } else {
            closeCaptionTextView.setVisibility(0);
        }
    }

    public /* synthetic */ void lambda$initializeVideo$21$CmeArticleActivity(Button button, CloseCaptionTextView closeCaptionTextView, ArrayList arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            button.setVisibility(0);
            closeCaptionTextView.setVideoView(this.mVideoView);
            closeCaptionTextView.setCloseCaption(arrayList);
        }
    }

    public /* synthetic */ boolean lambda$initializeVideo$24$CmeArticleActivity(MediaPlayer mediaPlayer, int i, int i2) {
        String str;
        if (Utilities.isNetworkAvailable(this)) {
            str = getResources().getString(R.string.dialog_msg_could_not_play_video);
        } else {
            str = getResources().getString(R.string.error_connection_required);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this, R.style.CMEPulseTheme_AlertDialog).setNeutralButton(R.string.alert_ok, (DialogInterface.OnClickListener) $$Lambda$CmeArticleActivity$3NgaD_oXlIWVIxeI6OKZaafLG3Q.INSTANCE).setMessage((CharSequence) str).setTitle(R.string.dialog_title_could_not_play_video).create().show();
            return true;
        }
        new AlertDialog.Builder(this, R.style.CMEPulseTheme_AlertDialog).setNeutralButton(R.string.alert_ok, (DialogInterface.OnClickListener) $$Lambda$CmeArticleActivity$7dlcj5WBoXRLQC79wen38GPA3TU.INSTANCE).setMessage((CharSequence) str).setTitle(R.string.dialog_title_could_not_play_video).create().show();
        return true;
    }

    public /* synthetic */ boolean lambda$initializeVideo$25$CmeArticleActivity(View view, MotionEvent motionEvent) {
        AlphaAnimation alphaAnimation = this.mVideoControlsFadeOutAnimation;
        if (alphaAnimation != null) {
            alphaAnimation.cancel();
        }
        if (!this.mAreVideoControlsVisible) {
            fadeInVideoControlsLayout();
        }
        if (!this.mVideoView.isPlaying()) {
            return true;
        }
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                CmeArticleActivity.this.fadeOutVideoControlsLayout();
            }
        }, 2000);
        return true;
    }

    public /* synthetic */ boolean lambda$initializeVideo$26$CmeArticleActivity(View view, MotionEvent motionEvent) {
        keepVideoControlsVisible(motionEvent);
        return true;
    }

    public /* synthetic */ void lambda$initializeVideo$30$CmeArticleActivity(ProgressBar progressBar, MediaPlayer mediaPlayer) {
        mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                CmeArticleActivity.this.lambda$null$27$CmeArticleActivity(mediaPlayer, i, i2);
            }
        });
        this.mSeekBar.setMax(this.mVideoView.getDuration());
        this.mSeekBar.setOnTouchListener(new View.OnTouchListener() {
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return CmeArticleActivity.this.lambda$null$28$CmeArticleActivity(view, motionEvent);
            }
        });
        this.mVideoView.post(new Runnable() {
            public final void run() {
                CmeArticleActivity.this.lambda$null$29$CmeArticleActivity();
            }
        });
        this.mTimeRemainingTextView.setText(getMinutesAndSecondsFromMilliseconds(this.mVideoView.getDuration()));
        this.mPlayPauseButton.setVisibility(0);
        progressBar.setVisibility(4);
    }

    public /* synthetic */ void lambda$null$27$CmeArticleActivity(MediaPlayer mediaPlayer, int i, int i2) {
        if (i > 0 && i2 > 0) {
            if (((double) (((float) i) / ((float) i2))) > 1.5d) {
                this.mVideoAspectRadio = "16x9";
            } else {
                this.mVideoAspectRadio = "4x3";
            }
        }
    }

    public /* synthetic */ boolean lambda$null$28$CmeArticleActivity(View view, MotionEvent motionEvent) {
        keepVideoControlsVisible(motionEvent);
        return false;
    }

    public /* synthetic */ void lambda$null$29$CmeArticleActivity() {
        this.mVideoView.start();
        this.mVideoView.pause();
    }

    public /* synthetic */ void lambda$initializeVideo$31$CmeArticleActivity(MediaPlayer mediaPlayer) {
        this.mTimeRemainingTextView.setText(getMinutesAndSecondsFromMilliseconds(0));
        this.mTimeElapsedTextView.setText("00:00");
        this.mVideoView.pause();
        this.mPlayPauseButton.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(R.drawable.ic_play_arrow_white_24dp), (Drawable) null, (Drawable) null, (Drawable) null);
        fadeInVideoControlsLayout();
        mediaPlayer.seekTo(1000);
        this.mVideoView.seekTo(5);
        this.mCurrentVideoPosition = 0;
    }

    public /* synthetic */ boolean lambda$initializeVideo$32$CmeArticleActivity(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            if (!this.mIsFullScreenActive) {
                this.mIsFullScreenActive = true;
                setRequestedOrientation(0);
                this.mExpectedOrientation = 2;
            } else {
                setRequestedOrientation(1);
                this.mExpectedOrientation = 1;
            }
            if (this.mExpectedOrientation == this.mCurrentOrientation) {
                setAutoRotateOrientation(2000);
            }
        }
        keepVideoControlsVisible(motionEvent);
        return true;
    }

    public /* synthetic */ boolean lambda$initializeVideo$33$CmeArticleActivity(View view, MotionEvent motionEvent) {
        keepVideoControlsVisible(motionEvent);
        return true;
    }

    public /* synthetic */ boolean lambda$initializeVideo$34$CmeArticleActivity(View view, MotionEvent motionEvent) {
        keepVideoControlsVisible(motionEvent);
        return true;
    }

    public /* synthetic */ void lambda$initializeVideo$36$CmeArticleActivity(View view) {
        String str;
        this.mPlayPauseButton.setEnabled(false);
        if (!this.mHasVideoBeenPlayed) {
            this.mVideoView.seekTo(0);
            this.mHasVideoBeenPlayed = true;
        }
        if (this.mVideoView.isPlaying()) {
            pauseVideo();
            str = OmnitureData.LINK_NAME_VIDEO_PAUSE;
        } else {
            playVideo();
            str = OmnitureData.LINK_NAME_VIDEO_PLAY;
        }
        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(OmnitureData.MODULE_NAME_VIDEO, str, String.format(OmnitureData.PAGE_NAME_ARTICLE_VIEW, new Object[]{this.mArticleId})));
        this.mPlayPauseButton.postDelayed(new Runnable() {
            public final void run() {
                CmeArticleActivity.this.lambda$null$35$CmeArticleActivity();
            }
        }, 100);
    }

    public /* synthetic */ void lambda$null$35$CmeArticleActivity() {
        this.mPlayPauseButton.setEnabled(true);
    }

    private void playVideo() {
        this.mVideoView.seekTo(this.mCurrentVideoPosition);
        this.mVideoView.start();
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            seekBar.postDelayed(this.onEverySecond, 1000);
        }
        this.mPlayPauseButton.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(R.drawable.ic_pause_white_24dp), (Drawable) null, (Drawable) null, (Drawable) null);
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                CmeArticleActivity.this.fadeOutVideoControlsLayout();
            }
        }, 2000);
    }

    private void resizeControlsToMatchVideoWidth() {
        this.mVideoView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int width = CmeArticleActivity.this.mVideoView.getWidth();
                if (CmeArticleActivity.this.mVideoView.canPause() && width > 0) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, -2);
                    layoutParams.addRule(12);
                    layoutParams.addRule(14);
                    CmeArticleActivity.this.mControlsLayout.setLayoutParams(layoutParams);
                    try {
                        if (Build.VERSION.SDK_INT < 16) {
                            CmeArticleActivity.this.mVideoView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            CmeArticleActivity.this.mVideoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    } catch (Exception unused) {
                        Trace.w(CmeArticleActivity.TAG, "Failed to remove global layout listener");
                    }
                }
            }
        });
    }

    private void pauseVideo() {
        VideoView videoView = this.mVideoView;
        if (videoView != null && this.mPlayPauseButton != null) {
            videoView.pause();
            this.mPlayPauseButton.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(R.drawable.ic_play_arrow_white_24dp), (Drawable) null, (Drawable) null, (Drawable) null);
            fadeInVideoControlsLayout();
        }
    }

    private void keepVideoControlsVisible(MotionEvent motionEvent) {
        if (!this.mAreVideoControlsVisible) {
            AlphaAnimation alphaAnimation = this.mVideoControlsFadeOutAnimation;
            if (alphaAnimation != null) {
                alphaAnimation.cancel();
            }
            fadeInVideoControlsLayout();
        }
        if (motionEvent.getAction() == 1 && this.mVideoView.isPlaying()) {
            new Handler().postDelayed(new Runnable() {
                public final void run() {
                    CmeArticleActivity.this.fadeOutVideoControlsLayout();
                }
            }, 2000);
        }
    }

    /* access modifiers changed from: private */
    public String getMinutesAndSecondsFromMilliseconds(int i) {
        long j = (long) i;
        return String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)))});
    }

    /* access modifiers changed from: private */
    public void fadeOutVideoControlsLayout() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        this.mVideoControlsFadeOutAnimation = alphaAnimation;
        alphaAnimation.setDuration(1000);
        this.mVideoControlsFadeOutAnimation.setStartOffset(2000);
        this.mVideoControlsFadeOutAnimation.setRepeatMode(2);
        this.mControlsLayout.startAnimation(this.mVideoControlsFadeOutAnimation);
        this.mVideoControlsFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                boolean unused = CmeArticleActivity.this.mAreVideoControlsVisible = false;
            }

            public void onAnimationEnd(Animation animation) {
                CmeArticleActivity.this.mControlsLayout.setVisibility(4);
            }
        });
    }

    private boolean isPreTestPage(String str) {
        if (this.mCurrentPage != 0) {
            return false;
        }
        String replace = str.replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "");
        if (replace.equals("lla:pre") || replace.equals("plla:pre") || replace.equals("pre:lla") || replace.equals("pre:plla") || replace.equals(ActivityTest.PRE_TEST_TYPE_ASSESSMENT)) {
            return true;
        }
        return false;
    }

    private boolean isActivityExpired() {
        return this.mArticle.expirationDate != null && this.mArticle.expirationDate.before(Calendar.getInstance());
    }

    private boolean shouldShowEarnCmeButton() {
        Questionnaire questionnaire = this.mQuestionnaire;
        return (questionnaire == null || !questionnaire.isPassed || (this.mQuestionnaire.getShouldGoToEvaluation() && !this.mQuestionnaire.evaluationCompleted)) && !isActivityExpired() && !this.mArticle.suppressCmeLink;
    }

    /* access modifiers changed from: private */
    public void showMinScoreReqSnackBar() {
        String str;
        double ceil = Math.ceil(this.mPassScore);
        double d = this.mPassScore;
        if (ceil == d) {
            str = String.valueOf((int) d);
        } else {
            str = String.valueOf(d);
        }
        RelativeLayout relativeLayout = this.mRootLayout;
        Snackbar make = Snackbar.make((View) relativeLayout, (CharSequence) "You must achieve a minimum score of " + str + "% to earn credit for this activity.", -1);
        make.getView().setBackgroundColor(getResources().getColor(R.color.app_accent_color));
        make.show();
    }

    private void removeFullScreenModeParams() {
        setDefaultVideoParams(true);
        Button button = this.mQuestionsContinueButton;
        if (button != null) {
            button.setVisibility(0);
        }
        this.mFullScreenButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, this.mContext.getResources().getDrawable(R.drawable.ic_fullscreen_white_24dp), (Drawable) null);
    }

    private void launchFullScreenSlides(boolean z) {
        this.mIsSettingImagePageManual = true;
        Intent intent = new Intent(this.mContext, CmeImageViewerActivity.class);
        intent.putParcelableArrayListExtra("bundle_key_image_slides", this.mArticle.slides);
        intent.putExtra("bundle_key_image_slide_position", this.mSlidesPager.getCurrentItem());
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
        intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.mArticle.assetId);
        intent.putExtra(Constants.BUNDLE_KEY_FULL_SCREEN_ORNTN, z);
        startActivityForResult(intent, 5002);
    }

    private void setDefaultOrientation() {
        if (Utilities.isPhone()) {
            setRequestedOrientation(1);
            setAutoRotateOrientation(2000);
            return;
        }
        setRequestedOrientation(-1);
    }

    /* access modifiers changed from: private */
    public void setAutoRotateOrientation(int i) {
        this.mRootLayout.postDelayed(new Runnable() {
            public final void run() {
                CmeArticleActivity.this.lambda$setAutoRotateOrientation$37$CmeArticleActivity();
            }
        }, (long) i);
    }

    public /* synthetic */ void lambda$setAutoRotateOrientation$37$CmeArticleActivity() {
        if (Utilities.isUserAutoRotationTurnedOn(this)) {
            setRequestedOrientation(-1);
        }
    }

    private void setOrientationListner() {
        this.mExpectedOrientation = -1;
        new SimpleOrientationListener(this) {
            public void onSimpleOrientationChanged(int i) {
                int unused = CmeArticleActivity.this.mCurrentOrientation = i;
                if (CmeArticleActivity.this.mExpectedOrientation == CmeArticleActivity.this.mCurrentOrientation) {
                    CmeArticleActivity.this.setAutoRotateOrientation(2000);
                    int unused2 = CmeArticleActivity.this.mExpectedOrientation = -1;
                }
            }
        }.enable();
    }
}

package com.webmd.wbmdcmepulse.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.MutableLiveData;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.squareup.picasso.Picasso;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.adapters.ArticleInfoContributorDisclosureAdapter;
import com.webmd.wbmdcmepulse.builders.HtmlBuilder;
import com.webmd.wbmdcmepulse.customviews.AnimatedExpandableListView;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.customviews.CustomTypeFaceSpan;
import com.webmd.wbmdcmepulse.customviews.HtmlListView;
import com.webmd.wbmdcmepulse.customviews.ImageCarouselView;
import com.webmd.wbmdcmepulse.exceptions.IncompatibleArticleException;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.SavedArticle;
import com.webmd.wbmdcmepulse.models.articles.Accreditor;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.Contributor;
import com.webmd.wbmdcmepulse.models.articles.ContributorComment;
import com.webmd.wbmdcmepulse.models.articles.Eligibility;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import com.webmd.wbmdcmepulse.models.articles.Provider;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.parsers.articles.HtmlParser;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.ExtensionsAspectRatio;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdcmepulse.providers.CmeArticleProvider;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.xmlpull.v1.XmlPullParserException;

public class CmeArticleInfoActivity extends CmeBaseActivity {
    /* access modifiers changed from: private */
    public static String TAG = CmeArticleInfoActivity.class.getSimpleName();
    private int mAccentColor;
    int mAccreditorImageHeight;
    int mAccreditorImageWidth;
    protected MutableLiveData<Article> mArticle = new MutableLiveData<>();
    private String mArticleAssetId;
    protected String mArticleId;
    /* access modifiers changed from: private */
    public String mArticleTitle;
    private LinearLayout mContentLinearLayout;
    private Context mContext;
    private int mCurrentPage;
    /* access modifiers changed from: private */
    public String mFeedUrl;
    /* access modifiers changed from: private */
    public Thread mImageCarouselThread;
    private boolean mIsDialog;
    private boolean mIsEic;
    private boolean mIsEvalRequired;
    /* access modifiers changed from: private */
    public boolean mIsImageCarouselRunning;
    /* access modifiers changed from: private */
    public LinearLayout mMainLinearLayout;
    private String mMaxCredits;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private CmeArticleProvider mProvider;
    private Questionnaire mQuestionnaire;
    private String mReferringLink;
    private String mReferringModule;
    private String mReferringPage;
    private String mReferringQuery;
    private ImageView mSupportGrantImageView;
    /* access modifiers changed from: private */
    public String upTargetActivity;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Log.d(TAG, "onCreate Entered");
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_articleinfo);
        new OmnitureData(this);
        initializeToolBar();
        this.mAccentColor = getResources().getInteger(R.integer.AccentColor);
        setUpDefaultActionBar("CME Activity", true);
        Log.d(TAG, "called super, set content view and set up action bar");
        this.mContext = this;
        this.mIsEvalRequired = false;
        this.mSupportGrantImageView = (ImageView) findViewById(R.id.suppport_grant_iv);
        Log.d(TAG, "Getting intent");
        Intent intent = getIntent();
        int i = -1;
        if (intent != null) {
            Log.d(TAG, "intent not null");
            this.mArticle.postValue(intent.getParcelableExtra(Constants.BUNDLE_KEY_ARTICLE));
            this.mReferringPage = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_PAGE);
            this.mReferringModule = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_MODULE);
            this.mReferringLink = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_LINK);
            this.mReferringQuery = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_QUERY);
            i = intent.getIntExtra(Constants.BUNDLE_KEY_REFERRING_LINK, -1);
            this.mIsDialog = intent.getBooleanExtra(Constants.BUNDLE_KEY_IS_DIAlOG, false);
            this.mCurrentPage = intent.getIntExtra(Constants.BUNDLE_KEY_ARTICLE_PAGE_NUMBER, 0);
            this.mQuestionnaire = (Questionnaire) intent.getParcelableExtra(Constants.BUNDLE_KEY_ARTICLE_QNA);
            this.mIsEic = intent.getBooleanExtra(Constants.BUNDLE_KEY_IS_EIC, false);
            this.upTargetActivity = intent.getStringExtra(Constants.RETURN_ACTIVITY);
            this.mArticleId = intent.getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
            this.mFeedUrl = intent.getStringExtra(Constants.BUNDLE_KEY_FEED_URL);
            this.mArticleTitle = intent.getStringExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE);
            this.mMaxCredits = intent.getStringExtra(Constants.BUNDLE_KEY_MAX_CREDITS);
        }
        if (i >= 0) {
            this.mReferringLink = Integer.toString(i);
        }
        Log.d(TAG, "checking if saved instance state is null");
        if (bundle != null) {
            Log.d(TAG, "saved instance state is not null");
            if (!Extensions.isStringNullOrEmpty(this.mReferringPage)) {
                this.mReferringPage = bundle.getString(Constants.BUNDLE_KEY_REFERRING_PAGE);
            }
            if (!Extensions.isStringNullOrEmpty(this.mReferringModule)) {
                this.mReferringModule = bundle.getString(Constants.BUNDLE_KEY_REFERRING_MODULE);
            }
            if (!Extensions.isStringNullOrEmpty(this.mReferringLink)) {
                this.mReferringLink = bundle.getString(Constants.BUNDLE_KEY_REFERRING_LINK);
            }
            if (!Extensions.isStringNullOrEmpty(this.mReferringQuery)) {
                this.mReferringQuery = bundle.getString(Constants.BUNDLE_KEY_REFERRING_QUERY);
            }
            this.mMaxCredits = bundle.getString(Constants.BUNDLE_KEY_MAX_CREDITS);
            if (this.mArticle == null) {
                this.mArticle = (MutableLiveData) bundle.getParcelable(Constants.BUNDLE_KEY_ARTICLE);
            }
            if (bundle.containsKey(Constants.CONTENT_IS_SAVED)) {
                this.mIsSaved = bundle.getBoolean(Constants.CONTENT_IS_SAVED);
            }
        }
        Log.d(TAG, "setting up views");
        this.mProvider = new CmeArticleProvider(this);
        this.mContentLinearLayout = (LinearLayout) findViewById(R.id.cmeActivityInfoTextView);
        this.mProgressBar = (ProgressBar) findViewById(R.id.activityInfoProgressIndicator);
        this.mMainLinearLayout = (LinearLayout) findViewById(R.id.activityInfoMainLinearLayout);
        Log.d(TAG, "getting uri rom intent.getData");
        Uri uri = null;
        if (intent != null) {
            uri = intent.getData();
        }
        handleDeepLink(uri);
        Log.d(TAG, "reached end of onCreate");
    }

    /* access modifiers changed from: protected */
    public void setAssetId(String str) {
        this.mArticleAssetId = str;
    }

    private void handleDeepLink(final Uri uri) {
        if (!Extensions.isStringNullOrEmpty(this.mArticleId)) {
            Log.d(TAG, "article id already exists");
            setUpActivityLayer();
        } else if (!AccountProvider.isUserLoggedIn(this)) {
            returnToLandingActivity();
        } else if (this.mUserProfile == null) {
            try {
                logInUser(new ICallbackEvent<Boolean, CMEPulseException>() {
                    public void onError(CMEPulseException cMEPulseException) {
                        Trace.d(CmeArticleInfoActivity.TAG, cMEPulseException.getMessage());
                        CmeArticleInfoActivity.this.returnToLandingActivity();
                    }

                    public void onCompleted(Boolean bool) {
                        if (bool.booleanValue()) {
                            Trace.d(CmeArticleInfoActivity.TAG, "Logged in user from keychain");
                            Uri uri = uri;
                            if (uri != null) {
                                CmeArticleInfoActivity.this.mArticleId = uri.getLastPathSegment();
                            }
                            CmeArticleInfoActivity.this.setUpActivityLayer();
                            String unused = CmeArticleInfoActivity.this.upTargetActivity = Constants.HOME_ACTIVITY_NAME;
                            return;
                        }
                        Trace.d(CmeArticleInfoActivity.TAG, "Could not in user from keychain");
                        CmeArticleInfoActivity.this.returnToLandingActivity();
                    }
                });
            } catch (Exception e) {
                Trace.e(TAG, e.getMessage());
                returnToLandingActivity();
            }
        } else if (uri == null || Extensions.isStringNullOrEmpty(uri.getLastPathSegment())) {
            Log.e(TAG, "Empty Article ID Sent. Check to make sure you are adding an article id");
            finish();
        } else {
            Log.d(TAG, "getting article id from last path segment");
            this.mArticleId = uri.getLastPathSegment();
            String str = TAG;
            Log.d(str, "article id from last path segment is: " + this.mArticleId);
            this.upTargetActivity = Constants.HOME_ACTIVITY_NAME;
            setUpActivityLayer();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return setMenuItems(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        MutableLiveData<Article> mutableLiveData;
        int itemId = menuItem.getItemId();
        if (itemId == R.id.action_save) {
            onSaveButtonClicked(this, this.mArticle.getValue(), this.mArticleId, this.mMaxCredits, this.mFeedUrl);
            return true;
        } else if (itemId == R.id.action_share) {
            if (!(!StringExtensions.isNullOrEmpty(this.mArticleTitle) || (mutableLiveData = this.mArticle) == null || mutableLiveData.getValue() == null)) {
                this.mArticleTitle = this.mArticle.getValue().title;
            }
            shareArticle(this, this.mFeedUrl, this.mArticleTitle);
            return true;
        } else if (itemId == R.id.action_activity_tracker) {
            launchCMETracker(this);
            return true;
        } else if (itemId != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            onBackPressed();
            return true;
        }
    }

    public void onBackPressed() {
        if (this.mIsDialog) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MutableLiveData<Article> mutableLiveData;
        super.onResume();
        if (!(isLockScreenVisible() || (mutableLiveData = this.mArticle) == null || mutableLiveData.getValue() == null)) {
            trackOmniture();
        }
        checkIfSaved(this.mFeedUrl, this.mArticleId);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Thread thread = this.mImageCarouselThread;
        if (thread != null) {
            this.mIsImageCarouselRunning = false;
            thread.interrupt();
            this.mImageCarouselThread = null;
        }
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString(Constants.BUNDLE_KEY_REFERRING_PAGE, this.mReferringPage);
        bundle.putString(Constants.BUNDLE_KEY_REFERRING_MODULE, this.mReferringModule);
        bundle.putString(Constants.BUNDLE_KEY_REFERRING_LINK, this.mReferringLink);
        bundle.putString(Constants.BUNDLE_KEY_REFERRING_QUERY, this.mReferringQuery);
        bundle.putString(Constants.BUNDLE_KEY_FEED_URL, this.mFeedUrl);
        bundle.putBoolean(Constants.CONTENT_IS_SAVED, this.mIsSaved);
        bundle.putString(Constants.BUNDLE_KEY_MAX_CREDITS, this.mMaxCredits);
        bundle.putParcelable(Constants.BUNDLE_KEY_ARTICLE, this.mArticle.getValue());
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: private */
    public void trackOmniture() {
        String str = this.mArticleId;
        if (str != null) {
            String str2 = null;
            String format = String.format(OmnitureData.PAGE_NAME_ARTICLE_INFO_LAYER, new Object[]{str});
            String generateAssetId = new ChronicleIDUtil().generateAssetId(this.mArticleId, this.mArticleAssetId, format);
            HashMap hashMap = new HashMap();
            hashMap.put("wapp.asset", generateAssetId);
            if (!Extensions.isStringNullOrEmpty(this.mReferringPage)) {
                str2 = String.format(this.mReferringPage, new Object[0]);
            }
            WBMDOmnitureManager.sendPageView(format, hashMap, new WBMDOmnitureModule(this.mReferringModule, this.mReferringLink, str2));
        }
    }

    private void setUpContinueButton() {
        Button button = (Button) findViewById(R.id.cmeContinueToArticle);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CmeArticleInfoActivity.this.goToArticle();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setUpActivityLayer() {
        MutableLiveData<Article> mutableLiveData = this.mArticle;
        if (mutableLiveData == null || mutableLiveData.getValue() == null) {
            this.mProvider.getArticle(this.mArticleId, new ICallbackEvent<Article, CMEPulseException>() {
                public void onError(CMEPulseException cMEPulseException) {
                    if (cMEPulseException != null) {
                        if (Extensions.isStringNullOrEmpty(cMEPulseException.getMessage()) || !cMEPulseException.getMessage().equals(IncompatibleArticleException.CLASS_NAME)) {
                            CmeArticleInfoActivity.this.mProgressBar.setVisibility(8);
                            if (Extensions.isStringNullOrEmpty(cMEPulseException.getMessage()) || !cMEPulseException.getMessage().equals(CmeArticleInfoActivity.this.getResources().getString(R.string.error_connection_required))) {
                                CmeArticleInfoActivity.this.goToArticleWebView();
                            } else {
                                CmeArticleInfoActivity.this.registerForceUp();
                                cMEPulseException.showSnackBar(CmeArticleInfoActivity.this.mMainLinearLayout, -2, CmeArticleInfoActivity.this.getString(R.string.retry), new View.OnClickListener() {
                                    public void onClick(View view) {
                                        CmeArticleInfoActivity.this.mProgressBar.setVisibility(0);
                                        CmeArticleInfoActivity.this.mArticle = new MutableLiveData<>();
                                        CmeArticleInfoActivity.this.setUpActivityLayer();
                                    }
                                });
                            }
                        } else {
                            CmeArticleInfoActivity.this.goToArticleWebView();
                        }
                        Trace.e(CmeArticleInfoActivity.TAG, cMEPulseException.getMessage());
                    }
                }

                public void onCompleted(Article article) {
                    CmeArticleInfoActivity.this.registerForceUp();
                    if (StringExtensions.isNullOrEmpty(CmeArticleInfoActivity.this.mArticleTitle)) {
                        String unused = CmeArticleInfoActivity.this.mArticleTitle = article.title;
                    }
                    if (StringExtensions.isNullOrEmpty(CmeArticleInfoActivity.this.mFeedUrl)) {
                        CmeArticleInfoActivity cmeArticleInfoActivity = CmeArticleInfoActivity.this;
                        String unused2 = cmeArticleInfoActivity.mFeedUrl = Utilities.getCMEUrl(cmeArticleInfoActivity, cmeArticleInfoActivity.mArticleId);
                    }
                    CmeArticleInfoActivity.this.mArticle.postValue(article);
                    CmeArticleInfoActivity.this.setAssetId(article.assetId);
                    if (!CmeArticleInfoActivity.this.isLockScreenVisible()) {
                        CmeArticleInfoActivity.this.trackOmniture();
                    }
                    CmeArticleInfoActivity.this.initializeActivityLayerViews(article);
                }
            });
        } else {
            initializeActivityLayerViews(this.mArticle.getValue());
        }
    }

    /* access modifiers changed from: private */
    public void initializeActivityLayerViews(Article article) {
        setUpContinueButton();
        if (!Extensions.isStringNullOrEmpty(article.cmeFlag) && !article.cmeFlag.equals("null")) {
            CustomFontTextView customFontTextView = (CustomFontTextView) findViewById(R.id.article_cme_flag_view);
            customFontTextView.setVisibility(0);
            customFontTextView.setText(article.cmeFlag);
        }
        ((CustomFontTextView) findViewById(R.id.article_title_text_view)).setText(article.title);
        CustomFontTextView customFontTextView2 = (CustomFontTextView) findViewById(R.id.article_byline_text_view);
        if (!Extensions.isStringNullOrEmpty(article.byLine)) {
            if (article.byLine.contains("CME Author")) {
                article.byLine = article.byLine.replace("CME Author:", "<br/>CME Author:");
            }
            customFontTextView2.setText(Html.fromHtml(article.byLine.trim()));
            customFontTextView2.setTypeface((Typeface) null, 2);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        if (article.releaseDate != null) {
            ((CustomFontTextView) findViewById(R.id.summaryTextView)).setText("CME Release:" + simpleDateFormat.format(article.releaseDate.getTime()));
        }
        if (article.expirationDate != null) {
            ((CustomFontTextView) findViewById(R.id.valid_through_text_view)).setText("Valid for credit through " + simpleDateFormat.format(article.expirationDate.getTime()));
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = (int) (((double) displayMetrics.widthPixels) * 0.75d);
        this.mAccreditorImageWidth = i;
        this.mAccreditorImageHeight = ExtensionsAspectRatio.getAdjustedHeight(75, 17, i);
        startCarouselThread(article);
        addTitleTextViewToArticle(this.mContentLinearLayout, "Credits Available", 60);
        addEligibilities(article);
        try {
            addTargetAudienceText(article);
        } catch (IOException e) {
            Trace.e(TAG, e.getMessage());
        } catch (XmlPullParserException e2) {
            Trace.e(TAG, e2.getMessage());
        }
        addFacultyDisclosureCmeReviewerInformation(article);
        LinearLayout viewsForContributors = getViewsForContributors(article.contributors);
        if (viewsForContributors != null) {
            this.mContentLinearLayout.addView(viewsForContributors);
        }
        if (article.eligibilities != null && article.eligibilities.size() > 0) {
            LinearLayout addAccredtationStatements = addAccredtationStatements(article);
            if (addAccredtationStatements.getChildCount() > 0) {
                addTitleTextViewToArticle(this.mContentLinearLayout, "Accreditation Statements");
                this.mContentLinearLayout.addView(addAccredtationStatements);
            }
        }
        if (article.creditInstructions != null) {
            addTitleTextViewToArticle(this.mContentLinearLayout, "Instructions for Participation and Credit", 0);
            ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(this.mContext);
            articleCopyTextView.setTextColor(ContextCompat.getColor(this.mContext, R.color.general_grey));
            articleCopyTextView.setPadding(0, 0, 4, 30);
            articleCopyTextView.setText(HtmlBuilder.removeTrailingLineBreaks(getParticipationAndCreditHtml(article)));
            this.mContentLinearLayout.addView(articleCopyTextView);
            this.mContentLinearLayout.setPadding(0, 0, 0, 30);
        }
        this.mProgressBar.setVisibility(8);
        this.mMainLinearLayout.setVisibility(0);
    }

    private void startCarouselThread(Article article) {
        this.mIsImageCarouselRunning = true;
        if (article.supportGrantBadges != null && article.supportGrantBadges.size() > 0) {
            try {
                this.mSupportGrantImageView.setMinimumWidth(this.mAccreditorImageWidth);
                this.mSupportGrantImageView.setMinimumHeight(this.mAccreditorImageHeight);
                this.mSupportGrantImageView.setMaxWidth(this.mAccreditorImageWidth);
                this.mSupportGrantImageView.setMaxHeight(this.mAccreditorImageHeight);
                if (article.supportGrantBadges.size() == 1) {
                    this.mSupportGrantImageView.setTag(article.supportGrantBadges.get(0));
                    Picasso.get().load(article.supportGrantBadges.get(0)).placeholder(R.drawable.cme_placeholder_image).into(this.mSupportGrantImageView);
                    return;
                }
                final ImageCarouselView imageCarouselView = new ImageCarouselView(this.mContext, this.mSupportGrantImageView, article.supportGrantBadges, getSupportFragmentManager());
                this.mImageCarouselThread = new Thread() {
                    public void run() {
                        while (CmeArticleInfoActivity.this.mIsImageCarouselRunning) {
                            try {
                                CmeArticleInfoActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        try {
                                            imageCarouselView.setCurrentImage();
                                        } catch (Exception e) {
                                            boolean unused = CmeArticleInfoActivity.this.mIsImageCarouselRunning = false;
                                            Trace.e(CmeArticleInfoActivity.TAG, e.getMessage());
                                        }
                                    }
                                });
                                sleep(10000);
                            } catch (InterruptedException e) {
                                Trace.e(CmeArticleInfoActivity.TAG, e.getMessage());
                            }
                        }
                    }
                };
                this.mSupportGrantImageView.post(new Runnable() {
                    public void run() {
                        CmeArticleInfoActivity.this.mImageCarouselThread.start();
                    }
                });
            } catch (Exception e) {
                Trace.e(TAG, e.getMessage());
            }
        }
    }

    private void addEligibilities(Article article) {
        boolean z;
        boolean z2;
        Article article2 = article;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
        if (!(article2 == null || article2.eligibilities == null || this.mUserProfile == null)) {
            UserProfession professionProfile = this.mUserProfile.getProfessionProfile();
            int i = 0;
            boolean z3 = false;
            boolean z4 = false;
            while (i < article2.eligibilities.size()) {
                Eligibility eligibility = article2.eligibilities.get(i);
                if (Utilities.getCreditTypeFromProfessionId(professionProfile.getProfessionId(), professionProfile.getOccupation()) == eligibility.type) {
                    this.mIsEvalRequired = eligibility.isEvalRequired;
                    z3 = true;
                }
                if (eligibility.type != 6) {
                    for (Accreditor next : eligibility.Accreditors) {
                        if ((next.eligbleAudience.contains("Physicians") || next.eligbleAudience.contains("MOC")) && !Extensions.isStringNullOrEmpty(next.creditDescription)) {
                            z2 = z3;
                            if (eligibility.type == 9) {
                                article2.isMocEligible = true;
                                int length = spannableStringBuilder2.length();
                                spannableStringBuilder2.append("ABIM Diplomats");
                                setBoldTypeSpan(spannableStringBuilder2, length, spannableStringBuilder2.length());
                                spannableStringBuilder2.append(" - maximum of ");
                                spannableStringBuilder2.append(String.valueOf(eligibility.MaxCredits));
                                spannableStringBuilder2.append(" Points for ABIM MOC");
                                spannableStringBuilder2.append("\n\n");
                                if (Utilities.isUserMocEligibilie(this, professionProfile.getProfessionId(), professionProfile.getSpecialityId()) && next.rank.equals("primary")) {
                                    arrayList.add("Points for ABIM MOC");
                                }
                            } else {
                                int length2 = spannableStringBuilder2.length();
                                spannableStringBuilder2.append(next.eligbleAudience);
                                setBoldTypeSpan(spannableStringBuilder2, length2, spannableStringBuilder2.length());
                                spannableStringBuilder2.append(" - maximum of ");
                                spannableStringBuilder2.append(String.valueOf(eligibility.MaxCredits));
                                spannableStringBuilder2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                                spannableStringBuilder2.append(next.creditDescription);
                                spannableStringBuilder2.append("\n\n");
                                if (next.rank.equals("primary") && professionProfile.getProfessionId().equals(UserProfile.PHYSICIAN_ID)) {
                                    arrayList.add(next.creditDescription);
                                }
                            }
                        } else {
                            z2 = z3;
                            if (next.eligbleAudience.contains("Nurse")) {
                                int length3 = spannableStringBuilder2.length();
                                spannableStringBuilder2.append(next.eligbleAudience);
                                setBoldTypeSpan(spannableStringBuilder2, length3, spannableStringBuilder2.length());
                                spannableStringBuilder2.append(" - ").append(next.creditDescription);
                                spannableStringBuilder2.append("awarded ");
                                spannableStringBuilder2.append(String.valueOf(eligibility.MaxCredits));
                                spannableStringBuilder2.append(" of continuing nursing education for RNs and APNs");
                                spannableStringBuilder2.append("\n\n");
                                if (Utilities.getCreditTypeFromProfessionId(professionProfile.getProfessionId(), professionProfile.getOccupationId()) != 6) {
                                    arrayList.add(next.creditDescription + " of continuing nursing education for RNs and APNs");
                                }
                            } else {
                                int length4 = spannableStringBuilder.length();
                                spannableStringBuilder.append(next.eligbleAudience);
                                setBoldTypeSpan(spannableStringBuilder, length4, spannableStringBuilder.length());
                                spannableStringBuilder.append(" - ").append(String.valueOf(eligibility.MaxCredits));
                                spannableStringBuilder.append(" of ").append(next.creditDescription);
                                spannableStringBuilder.append("\n\n");
                                if (professionProfile.getProfessionId().equals(UserProfile.PHARMACIST_ID)) {
                                    arrayList.add(eligibility.MaxCredits + " of " + next.creditDescription);
                                }
                            }
                        }
                        z3 = z2;
                    }
                    z = z3;
                } else {
                    z = z3;
                    if (eligibility.type == 6) {
                        z4 = eligibility.isEvalRequired;
                    }
                }
                i++;
                z3 = z;
            }
            if (!z3) {
                this.mIsEvalRequired = z4;
            }
        }
        spannableStringBuilder2.append(spannableStringBuilder);
        if (spannableStringBuilder2.length() > 2) {
            spannableStringBuilder2.replace(spannableStringBuilder2.length() - 2, spannableStringBuilder2.length() - 1, "");
        }
        TextView textView = new TextView(this.mContext);
        textView.setTextSize(15.0f);
        textView.setLineSpacing(12.0f, 0.9f);
        textView.setText(spannableStringBuilder2);
        textView.setPadding(0, 0, 0, 0);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mContentLinearLayout.addView(textView);
        addTitleTextViewToArticle(this.mContentLinearLayout, "You are eligible for", 0, 0, true);
        if (arrayList.size() == 0) {
            arrayList.add("Letter of Completion");
        }
        HtmlListView htmlListView = new HtmlListView(this.mContext);
        htmlListView.inflate(arrayList, "");
        htmlListView.setPadding(0, 0, 0, 20);
        this.mContentLinearLayout.addView(htmlListView);
    }

    private LinearLayout getViewsForContributors(List<Contributor> list) {
        if (list.size() <= 0) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Contributor next : list) {
            if (linkedHashMap.containsKey(next.contribType)) {
                List list2 = (List) linkedHashMap.get(next.contribType);
                if (list2 != null) {
                    list2.add(next);
                }
                linkedHashMap.put(next.contribType, list2);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                linkedHashMap.put(next.contribType, arrayList);
            }
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (((List) entry.getValue()).size() > 0) {
                addTitleTextViewToArticle(linearLayout, (String) entry.getKey(), 20, 30, true);
                int i = 0;
                for (Contributor contributor : (List) entry.getValue()) {
                    if (i > 0) {
                        addDivider(linearLayout, 20, 40);
                    }
                    addContributor(linearLayout, contributor);
                    i++;
                }
            }
        }
        return linearLayout;
    }

    public LinearLayout addContributor(LinearLayout linearLayout, Contributor contributor) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.article_info_contributors, (ViewGroup) null, false);
        CustomFontTextView customFontTextView = (CustomFontTextView) inflate.findViewById(R.id.contributor_title);
        final AnimatedExpandableListView animatedExpandableListView = (AnimatedExpandableListView) inflate.findViewById(R.id.contributor_disclosure);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.contributor_detail_layout);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.contributor_image);
        ((CustomFontTextView) inflate.findViewById(R.id.contributor_name)).setText(contributor.name);
        String str = "";
        if (!Extensions.isStringNullOrEmpty(contributor.image)) {
            String replaceAll = contributor.image.replace("/professional_assets/medscape/images/person/", Utilities.generateEnvironment(this, "https://img%s.medscape.com/person/")).replaceAll("/webmd", str);
            imageView.setVisibility(0);
            imageView.setMinimumWidth(200);
            imageView.setMinimumHeight(300);
            imageView.setTag(replaceAll);
            Picasso.get().load(replaceAll).placeholder(R.drawable.cme_placeholder_image).into(imageView);
            linearLayout2.setPadding(15, 0, 0, 0);
        } else {
            imageView.setVisibility(8);
            linearLayout2.setPadding(0, 0, 0, 0);
        }
        if (contributor.comments.size() > 0) {
            StringBuilder sb = new StringBuilder();
            String str2 = str;
            for (int i = 0; i < contributor.comments.size(); i++) {
                ContributorComment contributorComment = contributor.comments.get(i);
                if (contributorComment.title.equals("Title")) {
                    str = contributorComment.body;
                } else if (contributorComment.title.equals("Disclosure")) {
                    str2 = contributorComment.body;
                } else {
                    sb.append(contributorComment.body);
                }
            }
            if (!Extensions.isStringNullOrEmpty(str)) {
                customFontTextView.setText(str);
            } else {
                customFontTextView.setVisibility(8);
            }
            ArrayList arrayList = new ArrayList();
            if (!Extensions.isStringNullOrEmpty(str2)) {
                arrayList.add(str2);
            }
            String sb2 = sb.toString();
            if (!Extensions.isStringNullOrEmpty(sb2)) {
                arrayList.add(sb2);
            }
            if (arrayList.size() > 0) {
                ArrayList arrayList2 = new ArrayList();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                arrayList2.add("Disclosures");
                arrayList2.add("Divider");
                linkedHashMap.put(arrayList2.get(0), arrayList);
                linkedHashMap.put(arrayList2.get(1), arrayList);
                animatedExpandableListView.setAdapter(new ArticleInfoContributorDisclosureAdapter(this.mContext, arrayList2, linkedHashMap));
                animatedExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                        if (i != 0) {
                            return true;
                        }
                        if (animatedExpandableListView.isGroupExpanded(i)) {
                            animatedExpandableListView.collapseGroupWithAnimation(i);
                            return true;
                        }
                        animatedExpandableListView.expandGroupWithAnimation(i);
                        return true;
                    }
                });
            }
        }
        linearLayout.addView(inflate);
        return linearLayout;
    }

    public void addFacultyDisclosureCmeReviewerInformation(Article article) {
        StringBuilder sb = new StringBuilder();
        if (article.cmeReviwereNursePlanner != null) {
            sb.append("<h4>");
            sb.append(getColoredString("CME Reviewer/Nurse Planner", this.mAccentColor, 3));
            sb.append("</h4>");
            sb.append("<p><strong>");
            sb.append(getColoredString(article.cmeReviwereNursePlanner.name, ViewCompat.MEASURED_STATE_MASK));
            sb.append("</strong></p>");
            sb.append("<p>");
            for (int size = article.cmeReviwereNursePlanner.comments.size() - 1; size >= 0; size--) {
                sb.append(getColoredString(article.cmeReviwereNursePlanner.comments.get(size).body, ViewCompat.MEASURED_STATE_MASK));
                sb.append("<br/>");
            }
            sb.append("</p>");
            TextView textView = new TextView(this.mContext);
            textView.setPadding(0, 0, 0, 30);
            textView.setText(HtmlBuilder.removeTrailingLineBreaks(Html.fromHtml(sb.toString())));
            this.mContentLinearLayout.addView(textView);
        }
    }

    private LinearLayout addAccredtationStatements(Article article) {
        String str;
        Article article2 = article;
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        int i = 2;
        int i2 = this.mAccreditorImageHeight / 2;
        int i3 = (this.mAccreditorImageWidth / 2) + 20;
        if (getResources().getConfiguration().orientation == 1) {
            if (Utilities.isPhone()) {
                i2 = (this.mAccreditorImageHeight + 100) / 2;
            } else {
                i2 = (this.mAccreditorImageHeight + 80) / 2;
            }
        } else if (getResources().getConfiguration().orientation == 2) {
            if (Utilities.isPhone()) {
                i2 = (this.mAccreditorImageHeight + 100) / 2;
            } else {
                i2 = this.mAccreditorImageHeight / 2;
            }
        }
        if (article2.miscProvider != null) {
            if (!Extensions.isStringNullOrEmpty(article2.miscProvider.LogoUri)) {
                ImageView imageView = new ImageView(this);
                imageView.setVisibility(0);
                imageView.setMinimumWidth(i3);
                imageView.setMinimumHeight(i2);
                imageView.setTag(article2.miscProvider.LogoUri);
                Picasso.get().load(article2.miscProvider.LogoUri).placeholder(R.drawable.cme_placeholder_image).resize(i3, i2).onlyScaleDown().into(imageView);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.gravity = 3;
                imageView.setLayoutParams(layoutParams);
                linearLayout.addView(imageView);
            }
            if (!Extensions.isStringNullOrEmpty(article2.miscProvider.Statement)) {
                ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(this.mContext);
                articleCopyTextView.setTextColor(ContextCompat.getColor(this.mContext, R.color.general_grey));
                articleCopyTextView.setPadding(0, 10, 0, 10);
                articleCopyTextView.setClickable(true);
                articleCopyTextView.setLinksClickable(true);
                articleCopyTextView.setMovementMethod(LinkMovementMethod.getInstance());
                articleCopyTextView.setText(article2.miscProvider.Statement + IOUtils.LINE_SEPARATOR_UNIX);
                linearLayout.addView(articleCopyTextView);
            }
        }
        int i4 = 0;
        while (i4 < article2.eligibilities.size()) {
            Eligibility eligibility = article2.eligibilities.get(i4);
            if (!Extensions.isStringNullOrEmpty(eligibility.CreditStatement) || !Extensions.isStringNullOrEmpty(eligibility.AccreditationStatement)) {
                for (Accreditor next : eligibility.Accreditors) {
                    if (next.rank.equals("primary")) {
                        CustomFontTextView customFontTextView = new CustomFontTextView(this.mContext, "Roboto-Medium.ttf");
                        customFontTextView.append("For " + next.eligbleAudience);
                        customFontTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        customFontTextView.setTextSize(i, 16.0f);
                        customFontTextView.setPadding(0, 0, 0, 30);
                        linearLayout.addView(customFontTextView);
                    }
                }
                if (eligibility.Providers != null && eligibility.Providers.size() > 0) {
                    for (Provider next2 : eligibility.Providers) {
                        ImageView imageView2 = new ImageView(this.mContext);
                        imageView2.setImageDrawable(getResources().getDrawable(R.drawable.cme_placeholder_image));
                        if (!Extensions.isStringNullOrEmpty(next2.LogoUri)) {
                            String generateEnvironment = Utilities.generateEnvironment(this.mContext, "https://img%s.medscape.com/");
                            if (next2.Name.equalsIgnoreCase("Medscape")) {
                                str = generateEnvironment + "grant_attribution/";
                            } else {
                                str = generateEnvironment + "provider/";
                            }
                            String replace = next2.LogoUri.replace("/webmd/professional_assets/medscape/images/provider/", str).replace("1.150x34", "");
                            Trace.d(TAG, "Provider Image: " + replace);
                            imageView2.setVisibility(0);
                            imageView2.setMinimumWidth(i3);
                            imageView2.setMinimumHeight(i2);
                            imageView2.setTag(replace);
                            Picasso.get().load(replace).placeholder(R.drawable.cme_placeholder_image).resize(i3, i2).onlyScaleDown().into(imageView2);
                            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                            layoutParams2.gravity = 3;
                            imageView2.setLayoutParams(layoutParams2);
                        }
                        if (eligibility.Accreditors != null && eligibility.Accreditors.size() > 0 && next2.Name.equals("Medscape") && article2.isMocEligible && eligibility.Accreditors.get(0).eligbleAudience.equals("Physicians")) {
                            ImageView imageView3 = new ImageView(this.mContext);
                            imageView3.setImageDrawable(getResources().getDrawable(R.drawable.abim_moc));
                            linearLayout.addView(imageView3);
                            imageView3.setTag("abim_moc_image");
                            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
                            layoutParams3.gravity = 3;
                            layoutParams3.width = i3;
                            layoutParams3.height = i2;
                            imageView3.setLayoutParams(layoutParams3);
                        }
                        if (!Extensions.isStringNullOrEmpty(next2.LogoUri)) {
                            linearLayout.addView(imageView2);
                        }
                    }
                }
                ArticleCopyTextView articleCopyTextView2 = new ArticleCopyTextView(this.mContext);
                articleCopyTextView2.setTextColor(ContextCompat.getColor(this.mContext, R.color.general_grey));
                articleCopyTextView2.setPadding(0, 30, 0, 0);
                articleCopyTextView2.setClickable(true);
                articleCopyTextView2.setLinksClickable(true);
                articleCopyTextView2.setMovementMethod(LinkMovementMethod.getInstance());
                articleCopyTextView2.setText(Html.fromHtml(eligibility.AccreditationStatement + eligibility.CreditStatement));
                linearLayout.setClickable(true);
                linearLayout.addView(articleCopyTextView2);
            }
            i4++;
            i = 2;
        }
        return linearLayout;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
            return true;
        }
    }

    private Spanned getParticipationAndCreditHtml(Article article) {
        StringBuilder sb = new StringBuilder();
        if (article.creditInstructions.Paragraphs != null && article.creditInstructions.Paragraphs.size() > 0) {
            for (int i = 0; i < article.creditInstructions.Paragraphs.size() - 1; i++) {
                sb.append("<p>");
                sb.append(getColoredString(article.creditInstructions.Paragraphs.get(i), ViewCompat.MEASURED_STATE_MASK));
                sb.append("</p>");
            }
        }
        if (article.creditInstructions.InstructionList != null && article.creditInstructions.InstructionList.size() > 0) {
            int i2 = 1;
            for (String coloredString : article.creditInstructions.InstructionList) {
                sb.append(i2);
                sb.append(". ");
                sb.append(getColoredString(coloredString, ViewCompat.MEASURED_STATE_MASK));
                sb.append("<br/>");
                i2++;
            }
        }
        if (article.creditInstructions.Paragraphs != null && article.creditInstructions.Paragraphs.size() > 0) {
            sb.append("<p>");
            sb.append(getColoredString(article.creditInstructions.Paragraphs.get(article.creditInstructions.Paragraphs.size() - 1), ViewCompat.MEASURED_STATE_MASK));
            sb.append("</p>");
        }
        return Html.fromHtml(sb.toString());
    }

    private void addTargetAudienceText(Article article) throws IOException, XmlPullParserException {
        boolean z;
        if (!Extensions.isStringNullOrEmpty(article.targetAudienceStatement)) {
            addTitleTextViewToArticle(this.mContentLinearLayout, "Target Audience and Goal Statement", 5, 20, true);
            ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(this.mContext);
            articleCopyTextView.setText(article.targetAudienceStatement);
            articleCopyTextView.setTextColor(ContextCompat.getColor(this.mContext, R.color.general_grey));
            articleCopyTextView.setPadding(0, 0, 0, 35);
            this.mContentLinearLayout.addView(articleCopyTextView);
            z = true;
        } else {
            z = false;
        }
        if (!Extensions.isStringNullOrEmpty(article.goalStatement)) {
            if (!z) {
                addTitleTextViewToArticle(this.mContentLinearLayout, "Target Audience and Goal Statement", 0, 20, true);
            }
            ArticleCopyTextView articleCopyTextView2 = new ArticleCopyTextView(this.mContext);
            articleCopyTextView2.setText(article.goalStatement);
            articleCopyTextView2.setTextColor(ContextCompat.getColor(this.mContext, R.color.general_grey));
            this.mContentLinearLayout.addView(articleCopyTextView2);
        }
        if (article.objectiveStatement != null) {
            for (HtmlObject next : article.objectiveStatement) {
                String str = next.htmlType;
                char c = 65535;
                int hashCode = str.hashCode();
                if (hashCode != 112) {
                    if (hashCode != 3549) {
                        if (hashCode == 3735 && str.equals("ul")) {
                            c = 1;
                        }
                    } else if (str.equals("ol")) {
                        c = 2;
                    }
                } else if (str.equals("p")) {
                    c = 0;
                }
                if (c == 0) {
                    ArticleCopyTextView articleCopyTextView3 = new ArticleCopyTextView(this.mContext);
                    articleCopyTextView3.setTextColor(ContextCompat.getColor(this.mContext, R.color.general_grey));
                    articleCopyTextView3.setPadding(0, 30, 0, 0);
                    articleCopyTextView3.setText(HtmlBuilder.removeTrailingLineBreaks(Html.fromHtml(next.content)));
                    this.mContentLinearLayout.addView(articleCopyTextView3);
                } else if (c == 1) {
                    HtmlParser htmlParser = new HtmlParser();
                    HtmlListView htmlListView = new HtmlListView(this.mContext);
                    htmlListView.inflateSubLists(htmlParser.parseHtmlList(next.content, "ul"));
                    this.mContentLinearLayout.addView(htmlListView);
                } else if (c == 2) {
                    HtmlListView htmlListView2 = new HtmlListView(this.mContext);
                    htmlListView2.inflateSubLists(new HtmlParser().parseHtmlList(next.content, "ol"));
                    this.mContentLinearLayout.addView(htmlListView2);
                }
            }
        }
    }

    private void addTitleTextViewToArticle(LinearLayout linearLayout, String str, int i, int i2, boolean z) {
        if (!Extensions.isStringNullOrEmpty(str)) {
            if (z) {
                addDivider(linearLayout, i, 30);
            }
            CustomFontTextView customFontTextView = new CustomFontTextView(this.mContext, "Roboto-Medium.ttf");
            customFontTextView.setText(str);
            customFontTextView.setPadding(0, 0, 0, i2);
            customFontTextView.setTextSize(2, 17.0f);
            customFontTextView.setTextColor(ContextCompat.getColor(this.mContext, R.color.black));
            linearLayout.addView(customFontTextView);
        }
    }

    private void addTitleTextViewToArticle(LinearLayout linearLayout, String str, int i) {
        addTitleTextViewToArticle(linearLayout, str, i, 20, true);
    }

    private void addTitleTextViewToArticle(LinearLayout linearLayout, String str) {
        addTitleTextViewToArticle(linearLayout, str, 40, 20, true);
    }

    private void addDivider(LinearLayout linearLayout, int i, int i2) {
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
        layoutParams.setMargins(5, i, 20, i2);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundColor(ContextCompat.getColor(this.mContext, R.color.general_grey));
        linearLayout.addView(imageView);
    }

    public String getColoredString(String str, int i) {
        return getColoredString(str, i, 4);
    }

    public String getColoredString(String str, int i, int i2) {
        return "<font size='" + i2 + "' color='" + i + "'>" + str + "</font>";
    }

    public SpannableStringBuilder setBoldTypeSpan(SpannableStringBuilder spannableStringBuilder, int i, int i2) {
        Typeface createFromAsset = Typeface.createFromAsset(this.mContext.getAssets(), "font/Roboto-Medium.ttf");
        if (createFromAsset != null) {
            spannableStringBuilder.setSpan(new CustomTypeFaceSpan("", createFromAsset), i, i2, 33);
        }
        return spannableStringBuilder;
    }

    /* access modifiers changed from: private */
    public void goToArticleWebView() {
        Article value = this.mArticle.getValue();
        if (value != null) {
            value.id = this.mArticleId;
            value.title = this.mArticleTitle;
            this.mArticle.postValue(value);
        }
        Intent cMEWebViewIntent = getCMEWebViewIntent();
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.mArticleAssetId);
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_FEED_URL, this.mFeedUrl);
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_PAGE, this.mReferringPage);
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_MODULE, this.mReferringModule);
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, this.mReferringLink);
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_QUERY, this.mReferringQuery);
        cMEWebViewIntent.putExtra(Constants.RETURN_ACTIVITY, this.upTargetActivity);
        cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, this.mArticleTitle);
        if (getIntent().hasExtra(Constants.BUNDLE_KEY_SAVED_ARTICLE)) {
            cMEWebViewIntent.putExtra(Constants.BUNDLE_KEY_SAVED_ARTICLE, (SavedArticle) getIntent().getParcelableExtra(Constants.BUNDLE_KEY_SAVED_ARTICLE));
        }
        startActivity(cMEWebViewIntent);
        finish();
    }

    /* access modifiers changed from: protected */
    public Intent getCMEWebViewIntent() {
        return new Intent(this, CmeArticleWebActivity.class);
    }

    /* access modifiers changed from: private */
    public void goToArticle() {
        Intent cMEArticleActivityIntent = getCMEArticleActivityIntent();
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE, this.mArticle.getValue());
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_QNA, this.mQuestionnaire);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, this.mIsEvalRequired);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_PAGE_NUMBER, this.mCurrentPage);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_IS_EIC, this.mIsEic);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_PAGE, this.mReferringPage);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_MODULE, this.mReferringModule);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, this.mReferringLink);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_REFERRING_QUERY, this.mReferringQuery);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_IS_DIAlOG, this.mIsDialog);
        if (getIntent().hasExtra(Constants.BUNDLE_KEY_ARTICLE_THUMB_URL)) {
            cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_ARTICLE_THUMB_URL, getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_THUMB_URL));
        }
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_FEED_URL, this.mFeedUrl);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_MAX_CREDITS, this.mMaxCredits);
        cMEArticleActivityIntent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
        startActivity(cMEArticleActivityIntent);
    }

    public Intent getCMEArticleActivityIntent() {
        return new Intent(this, CmeArticleActivity.class);
    }
}

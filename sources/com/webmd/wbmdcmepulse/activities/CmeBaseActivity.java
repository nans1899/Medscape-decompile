package com.webmd.wbmdcmepulse.activities;

import android.app.ActionBar;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.volley.AuthFailureError;
import com.ib.foreceup.util.Util;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.receivers.ShareDataObservable;
import com.wbmd.wbmdcommons.receivers.ShareReceiver;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.SavedArticle;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.Eligibility;
import com.webmd.wbmdcmepulse.models.parsers.UserProfileParser;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdproffesionalauthentication.model.BasicInfo;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.omniture.OmnitureManager;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;

public abstract class CmeBaseActivity extends AppCompatActivity implements Observer {
    private static String TAG = CmeBaseActivity.class.getSimpleName();
    private static boolean mIsInBackground;
    private static int sessionDepth = 0;
    protected BroadcastReceiver mArticleSaveReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(CmeBaseActivity.this.mArticleSaveReceiver);
            CmeBaseActivity.this.mIsSaved = intent.getBooleanExtra(Constants.CONTENT_IS_SAVED, false);
            CmeBaseActivity.this.invalidateOptionsMenu();
            if (CmeBaseActivity.this.mIsSaved && CmeBaseActivity.this.mIsShouldSaveMessage) {
                Toast.makeText(context, CmeBaseActivity.this.getResources().getString(R.string.education_article_saved), 0).show();
            }
        }
    };
    private BroadcastReceiver mForceupReceiver;
    protected boolean mIsSaved;
    protected boolean mIsShouldSaveMessage;
    protected String mReferringLink;
    protected String mReferringModule;
    protected String mReferringPage;
    protected UserProfile mUserProfile;

    /* access modifiers changed from: protected */
    public String getUserAgentAppName() {
        return " cmepulseapp/";
    }

    /* access modifiers changed from: package-private */
    public abstract void trackOmniturePageView();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Resources resources = getResources();
        if (resources != null && resources.getBoolean(R.bool.isPhone)) {
            setRequestedOrientation(1);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.app_accent_dark));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mUserProfile = (UserProfile) extras.getParcelable(Constants.BUNDLE_KEY_USER_PROFILE);
        }
        if (this.mUserProfile == null && bundle != null) {
            this.mUserProfile = (UserProfile) bundle.getParcelable(Constants.BUNDLE_KEY_USER_PROFILE);
        }
        if (!(this instanceof CmeArticleInfoActivity)) {
            registerForceUp();
        }
    }

    /* access modifiers changed from: package-private */
    public void registerForceUp() {
        this.mForceupReceiver = Util.registerForceupReceiver(this);
    }

    /* access modifiers changed from: protected */
    public void initializeToolBar() {
        if (findViewById(R.id.toolbar) != null) {
            setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mForceupReceiver == null && !(this instanceof CmeArticleInfoActivity)) {
            this.mForceupReceiver = Util.registerForceupReceiver(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Util.unregisterForceupReceiver(this, this.mForceupReceiver);
        this.mForceupReceiver = null;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (mIsInBackground) {
            trackOmniturePageView();
            mIsInBackground = false;
        }
        sessionDepth++;
    }

    public void onStop() {
        super.onStop();
        int i = sessionDepth;
        if (i > 0) {
            sessionDepth = i - 1;
        }
        if (sessionDepth == 0) {
            mIsInBackground = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Util.unregisterForceupReceiver(this, this.mForceupReceiver);
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void setUpDefaultActionBar(String str, boolean z) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(str);
            actionBar.setDisplayHomeAsUpEnabled(z);
        }
        androidx.appcompat.app.ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
            supportActionBar.setDisplayHomeAsUpEnabled(z);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isLockScreenVisible() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService("keyguard");
        return keyguardManager != null && keyguardManager.inKeyguardRestrictedInputMode();
    }

    public void onSaveInstanceState(Bundle bundle) {
        UserProfile userProfile = this.mUserProfile;
        if (userProfile != null) {
            bundle.putParcelable(Constants.BUNDLE_KEY_USER_PROFILE, userProfile);
        }
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void logInUser(final ICallbackEvent<Boolean, CMEPulseException> iCallbackEvent) {
        if (AccountProvider.isUserLoggedIn(this)) {
            AccountProvider.signIn(this, new ICallbackEvent<Object, Exception>() {
                public void onError(Exception exc) {
                    if (exc.getCause() instanceof AuthFailureError) {
                        iCallbackEvent.onError(new CMEPulseException("Auth Failure"));
                    } else {
                        CmeBaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                iCallbackEvent.onCompleted(true);
                            }
                        });
                    }
                }

                public void onCompleted(Object obj) {
                    JSONObject jSONObject = (JSONObject) obj;
                    String parseLoginError = LoginErrorParser.parseLoginError(CmeBaseActivity.this.getApplicationContext(), jSONObject);
                    if (Extensions.isStringNullOrEmpty(parseLoginError)) {
                        CmeBaseActivity cmeBaseActivity = CmeBaseActivity.this;
                        cmeBaseActivity.mUserProfile = UserProfileParser.getUserProfile(cmeBaseActivity, jSONObject, false);
                        CmeBaseActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                iCallbackEvent.onCompleted(true);
                            }
                        });
                        return;
                    }
                    iCallbackEvent.onError(new CMEPulseException(parseLoginError));
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void returnToLandingActivity() {
        Utilities.goToLandingPAge(this);
        finish();
    }

    /* access modifiers changed from: protected */
    public void checkIfSaved(String str, String str2) {
        this.mIsShouldSaveMessage = false;
        Intent intent = new Intent(Constants.CONTENT_CHECK_SAVE);
        intent.putExtra(Constants.CONTENT_SAVE_URL, str);
        intent.putExtra(Constants.CONTENT_SAVE_ARTICLEID, str2);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mArticleSaveReceiver, new IntentFilter(Constants.CONTENT_RESPONSE_SAVE));
    }

    /* access modifiers changed from: protected */
    public void onSaveButtonClicked(Context context, Object obj, String str, String str2, String str3) {
        Article article;
        SavedArticle savedArticle;
        if (obj instanceof Article) {
            savedArticle = new SavedArticle();
            article = (Article) obj;
        } else if (obj instanceof SavedArticle) {
            savedArticle = (SavedArticle) obj;
            article = null;
        } else {
            return;
        }
        UserProfile userProfile = this.mUserProfile;
        if (!(userProfile == null || userProfile.getBasicProfile() == null || article == null)) {
            BasicInfo basicProfile = this.mUserProfile.getBasicProfile();
            savedArticle.articleId = str;
            savedArticle.userId = basicProfile.getUserId();
            savedArticle.title = article.title;
            try {
                savedArticle.publishDate = new SimpleDateFormat("MM/dd/yyyy").format(article.releaseDate.getTime());
            } catch (Exception e) {
                Trace.e(TAG, e.getMessage());
            }
            savedArticle.creditType = article.cmeFlag;
            savedArticle.detail = article.metaDescription;
            if (getIntent().hasExtra(Constants.BUNDLE_KEY_ARTICLE_THUMB_URL)) {
                savedArticle.imageUrl = getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_THUMB_URL);
            }
            if (!Extensions.isStringNullOrEmpty(str2)) {
                float f = 0.0f;
                for (Eligibility next : article.eligibilities) {
                    if (next.MaxCredits > f) {
                        f = next.MaxCredits;
                    }
                }
                str2 = String.valueOf(f);
            }
            savedArticle.maxCredits = str2;
            savedArticle.publishType = "Medscape Education " + article.contentType;
            if (!Extensions.isStringNullOrEmpty(savedArticle.creditType) && savedArticle.creditType.length() > 2 && savedArticle.creditType.contains("[")) {
                savedArticle.creditType = Utilities.removeJsonFromCreditType(savedArticle.creditType);
            }
            if (!Extensions.isStringNullOrEmpty(savedArticle.maxCredits) && !savedArticle.maxCredits.contains("Credits")) {
                savedArticle.maxCredits += " Credits";
            } else if (!(article.eligibilities == null || article.eligibilities.size() <= 0 || article.eligibilities.get(0) == null || article.eligibilities.get(0).MaxCredits == 0.0f)) {
                savedArticle.maxCredits = String.valueOf(article.eligibilities.get(0).MaxCredits) + " Credits";
            }
        }
        this.mIsShouldSaveMessage = true;
        performSaveUnsave(str3, savedArticle);
    }

    /* access modifiers changed from: protected */
    public void performSaveUnsave(String str, SavedArticle savedArticle) {
        OmnitureManager.get().trackModule(this, "education", "save", this.mIsSaved ? com.medscape.android.Constants.OMNITURE_MLINK_UNSAVE : FeedConstants.CME_ITEM, (Map<String, Object>) null);
        Intent intent = new Intent(Constants.CONTENT_SAVE_UNSAVE_ACTION);
        intent.putExtra(Constants.CONTENT_SAVE_URL, str);
        if (savedArticle != null) {
            intent.putExtra(Constants.CONTENT_SAVE_TITLE, savedArticle.title);
        }
        intent.putExtra(Constants.CONTENT_IS_SAVED, this.mIsSaved);
        intent.putExtra(Constants.CONTENT_SAVE_ARTICLE, savedArticle);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mArticleSaveReceiver, new IntentFilter(Constants.CONTENT_RESPONSE_SAVE));
    }

    /* access modifiers changed from: protected */
    public boolean setMenuItems(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_overflow, menu);
        if (menu == null || !this.mIsSaved) {
            return true;
        }
        menu.findItem(R.id.action_save).setIcon(R.drawable.action_save_fill_white_24dp);
        return true;
    }

    /* access modifiers changed from: protected */
    public void launchCMETracker(Context context) {
        Intent intent = new Intent(context, CMETrackerActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void shareArticle(Context context, String str, String str2) {
        if (!StringExtensions.isNullOrEmpty(str2)) {
            PendingIntent createPendingIntent = new ShareReceiver().createPendingIntent(context);
            if (Build.VERSION.SDK_INT <= 22) {
                createPendingIntent = null;
                OmnitureManager.get().trackModule(this, "education", ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), FeedConstants.CME_ITEM, (Map<String, Object>) null);
            } else {
                ShareDataObservable.INSTANCE.addObserver(this);
            }
            com.wbmd.wbmdcommons.utils.Util.share(context, str, str2, "", (String) null, createPendingIntent);
        }
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof ShareDataObservable) {
            if (obj instanceof String) {
                OmnitureManager.get().trackModule(this, "education", ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), obj.toString(), (Map<String, Object>) null);
            }
            ShareDataObservable.INSTANCE.deleteObserver(this);
        }
    }
}

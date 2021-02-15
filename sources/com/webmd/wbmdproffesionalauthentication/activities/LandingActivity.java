package com.webmd.wbmdproffesionalauthentication.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdomnituremanager.ProfessionalOmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.constants.SharedPreferenceConstants;
import com.webmd.wbmdproffesionalauthentication.keychain.KeychainManager;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.AuthComponentConstants;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter;
import java.util.Map;
import org.json.JSONObject;

public class LandingActivity extends BaseActivity {
    public static final String DEBUG_EXTRA = "registration_component_debug_setting";
    public static final int DEBUG_REQUEST = 11011;
    private final int LOGIN_REQUEST = 1011;
    Button continueBtn;
    LinearLayout continueLayout;
    TextView continueMessageTV;
    ProgressBar continueProgressBar;
    private boolean debug = false;
    boolean isSignUpClicked = false;
    private boolean mIsKeychainAvailable;
    /* access modifiers changed from: private */
    public String mPassword;
    /* access modifiers changed from: private */
    public String mUserName;
    ProgressBar newUserProgressBar;
    TextView notYouTV;
    LinearLayout rootLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_landing);
        setSupportActionBar((Toolbar) findViewById(R.id.landing_toolbar));
        Intent intent = getIntent();
        if (intent != null) {
            this.debug = intent.getBooleanExtra(DEBUG_EXTRA, false);
        }
        setTitleEmpty();
        setUpViews();
        setUpListeners();
        if (!this.debug) {
            retrieveKeyChain();
        } else if (intent == null) {
            retrieveKeyChain();
        } else if (!intent.getBooleanExtra(AuthComponentConstants.EXTRA_LANDING_FLAG, true)) {
            this.mIsKeychainAvailable = false;
            initializeViews();
        } else {
            retrieveKeyChain();
        }
    }

    private void setUpViews() {
        this.continueLayout = (LinearLayout) findViewById(R.id.landing_activity_continue_layout);
        this.continueBtn = (Button) findViewById(R.id.landing_activity_continue_button);
        this.continueProgressBar = (ProgressBar) findViewById(R.id.continue_progress);
        this.notYouTV = (TextView) findViewById(R.id.text_view_keychain_not_you);
        this.continueMessageTV = (TextView) findViewById(R.id.text_view_keychain_message);
        this.newUserProgressBar = (ProgressBar) findViewById(R.id.signupProgress);
        this.rootLayout = (LinearLayout) findViewById(R.id.landing_activity_root_layout);
        ProgressBar progressBar = this.continueProgressBar;
        if (progressBar != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.auth_component_white), PorterDuff.Mode.MULTIPLY);
        }
    }

    private void setUpListeners() {
        this.continueBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!Util.isOnline(LandingActivity.this)) {
                    Util.showSnackBar(LandingActivity.this.rootLayout, LandingActivity.this.getString(R.string.error_connection_required));
                    LandingActivity.this.continueProgressBar.setVisibility(8);
                    return;
                }
                WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule("keychain", "cont", WBMDOmnitureManager.shared.getLastSentPage()));
                LandingActivity.this.continueProgressBar.setVisibility(0);
                LandingActivity landingActivity = LandingActivity.this;
                AccountProvider.signInUser(landingActivity, landingActivity.mUserName, LandingActivity.this.mPassword, 1, new ICallbackEvent<Object, Exception>() {
                    public void onError(final Exception exc) {
                        LandingActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(LandingActivity.this, VolleyErrorConverter.exceptionToMessage(LandingActivity.this, exc), 0).show();
                                LandingActivity.this.continueProgressBar.setVisibility(8);
                                LandingActivity.this.continueLayout.setVisibility(8);
                                LandingActivity.this.startActivityForResult(new Intent(LandingActivity.this, LoginActivity.class), 1011);
                                LandingActivity.this.finish();
                            }
                        });
                    }

                    public void onCompleted(final Object obj) {
                        LandingActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                LandingActivity.this.continueProgressBar.setVisibility(8);
                                String parseLoginError = LoginErrorParser.parseLoginError(LandingActivity.this, (JSONObject) obj);
                                if (StringExtensions.isNullOrEmpty(parseLoginError)) {
                                    SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
                                    sharedPreferenceProvider.save(SharedPreferenceConstants.USER_NAME_KEYCHAIN_KEY, LandingActivity.this.mUserName, LandingActivity.this.getApplicationContext());
                                    sharedPreferenceProvider.save(SharedPreferenceConstants.PASSWORD_KEYCHAIN_KEY, LandingActivity.this.mPassword, LandingActivity.this.getApplicationContext());
                                    LandingActivity.this.finish();
                                    return;
                                }
                                Snackbar.make((View) LandingActivity.this.rootLayout, (CharSequence) parseLoginError, -1).show();
                                LandingActivity.this.showNonKeyChainView();
                            }
                        });
                    }
                });
            }
        });
        this.notYouTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WBMDOmnitureManager.shared.setModule("keychain");
                WBMDOmnitureManager.shared.setLink("notu");
                LandingActivity.this.continueLayout.setVisibility(8);
                LandingActivity.this.showNonKeyChainView();
            }
        });
    }

    private void initializeViews() {
        if (!this.mIsKeychainAvailable) {
            showNonKeyChainView();
            return;
        }
        View findViewById = findViewById(R.id.toolbar_image_wrapper);
        View findViewById2 = findViewById(R.id.text_view_welcome_title);
        if (StringExtensions.isNullOrEmpty(getResources().getString(R.string.registration_welcome_title))) {
            findViewById.setVisibility(0);
        } else {
            findViewById2.setVisibility(0);
        }
        this.continueMessageTV.setText(String.format(getResources().getString(R.string.keychain_logged_in_message), new Object[]{this.mUserName}));
        this.continueLayout.setVisibility(0);
        WBMDOmnitureManager.sendPageView(ProfessionalOmnitureData.buildPage("login", "view", (String) null, ProfessionalOmnitureData.appendAppPrefix(this, "keychain-", true)), (Map<String, String>) null, (WBMDOmnitureModule) null);
    }

    /* access modifiers changed from: private */
    public void showNonKeyChainView() {
        View findViewById = findViewById(R.id.toolbar_image_wrapper);
        View findViewById2 = findViewById(R.id.text_view_welcome_title);
        findViewById.setVisibility(0);
        findViewById2.setVisibility(8);
        ((TextView) findViewById(R.id.btnSignIn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WBMDOmnitureManager.shared.setModule("start");
                WBMDOmnitureManager.shared.setLink("exist");
                LandingActivity.this.startActivityForResult(new Intent(LandingActivity.this, LoginActivity.class), 1011);
            }
        });
        ((TextView) findViewById(R.id.btnSignUp)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (LandingActivity.this.isSignUpClicked) {
                    return;
                }
                if (Util.isOnline(LandingActivity.this.getApplicationContext())) {
                    LandingActivity.this.isSignUpClicked = true;
                    WBMDOmnitureManager.shared.setModule("start");
                    WBMDOmnitureManager.shared.setLink(AppSettingsData.STATUS_NEW);
                    LandingActivity.this.newUserProgressBar.setVisibility(0);
                    RegistrationProvider.getInstance(LandingActivity.this).loadRegistrationData(new ICallbackEvent<JSONObject, String>() {
                        public void onError(String str) {
                            Util.showSnackBar(LandingActivity.this.rootLayout, LandingActivity.this.getString(R.string.error_service_unavailable));
                            LandingActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    LandingActivity.this.newUserProgressBar.setVisibility(8);
                                }
                            });
                            LandingActivity.this.isSignUpClicked = false;
                        }

                        public void onCompleted(JSONObject jSONObject) {
                            LandingActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    LandingActivity.this.newUserProgressBar.setVisibility(8);
                                }
                            });
                            LandingActivity.this.isSignUpClicked = false;
                            LandingActivity.this.startActivityForResult(new Intent(LandingActivity.this, RegistrationActivity.class), 1011);
                        }
                    });
                    return;
                }
                Util.showSnackBar(LandingActivity.this.rootLayout, LandingActivity.this.getString(R.string.internet_required));
            }
        });
        TextView textView = (TextView) findViewById(R.id.landing_debug);
        if (this.debug) {
            textView.setVisibility(0);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    LandingActivity.this.setResult(LandingActivity.DEBUG_REQUEST);
                    LandingActivity.this.finish();
                }
            });
        } else {
            textView.setVisibility(8);
        }
        findViewById(R.id.non_keychain_views_wrapper).setVisibility(0);
        WBMDOmnitureManager.sendPageViewWithSharedModule(ProfessionalOmnitureData.buildPage("login", "view", (String) null, ProfessionalOmnitureData.appendAppPrefix(this, "new-user-start-", true)), (Map<String, String>) null);
    }

    private void retrieveKeyChain() {
        Map<String, String> keychain = KeychainManager.getKeychain(this);
        if (keychain.size() > 0) {
            this.mUserName = keychain.get("username");
            this.mPassword = keychain.get("password");
            if (!StringExtensions.isNullOrEmpty(this.mUserName) && !StringExtensions.isNullOrEmpty(this.mPassword)) {
                this.mIsKeychainAvailable = true;
            }
        } else {
            this.mIsKeychainAvailable = false;
        }
        initializeViews();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1011 && i2 == -1) {
            setResult(-1);
            finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        setResult(0);
        finish();
        super.onBackPressed();
    }
}

package com.webmd.wbmdproffesionalauthentication.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.net.MailTo;
import com.google.android.material.snackbar.Snackbar;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdomnituremanager.ProfessionalOmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.constants.Constants;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter;
import java.util.Map;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private TextView forgotPassword;
    private boolean isFromLogout = false;
    /* access modifiers changed from: private */
    public boolean isFromOnCreate = false;
    /* access modifiers changed from: private */
    public boolean isLoginInProgress = false;
    /* access modifiers changed from: private */
    public ProgressBar mInlineWhiteProgressBar;
    /* access modifiers changed from: private */
    public EditText mPasswordEditText;
    /* access modifiers changed from: private */
    public Button mSignInButton;
    /* access modifiers changed from: private */
    public EditText mUserNameEditText;
    /* access modifiers changed from: private */
    public RelativeLayout rootLayout;
    private TextView supportText;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        setSupportActionBar((Toolbar) findViewById(R.id.log_in_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setUpViews();
        setUpListeners();
        WBMDOmnitureManager.sendPageViewWithSharedModule(ProfessionalOmnitureData.buildPage("login", "view", (String) null, ProfessionalOmnitureData.appendAppPrefix(this, "login-", true)), (Map<String, String>) null);
        this.isFromOnCreate = true;
        this.isFromLogout = getIntent().getBooleanExtra(Constants.LOGIN_FROM_LOGOUT, false);
    }

    private void setUpViews() {
        this.mUserNameEditText = (EditText) findViewById(R.id.edit_text_user_name);
        this.mPasswordEditText = (EditText) findViewById(R.id.edit_text_password);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.white_inline_progress_bar);
        this.mInlineWhiteProgressBar = progressBar;
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.auth_component_white), PorterDuff.Mode.MULTIPLY);
        this.mSignInButton = (Button) findViewById(R.id.button_login);
        this.rootLayout = (RelativeLayout) findViewById(R.id.login_root_layout);
        this.forgotPassword = (TextView) findViewById(R.id.sign_in_forgot_password);
        this.supportText = (TextView) findViewById(R.id.sign_in_support_email);
    }

    private void setUpListeners() {
        this.mSignInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Util.hideKeyboard(LoginActivity.this);
                LoginActivity.this.mInlineWhiteProgressBar.setVisibility(0);
                boolean unused = LoginActivity.this.isLoginInProgress = true;
                String trim = LoginActivity.this.mUserNameEditText.getText().toString().trim();
                String obj = LoginActivity.this.mPasswordEditText.getText().toString();
                if (StringExtensions.isNullOrEmpty(trim) || StringExtensions.isNullOrEmpty(obj)) {
                    Util.showSnackBar(LoginActivity.this.rootLayout, LoginActivity.this.getString(R.string.sign_in_error_fields_required));
                    LoginActivity.this.mInlineWhiteProgressBar.setVisibility(8);
                    boolean unused2 = LoginActivity.this.isLoginInProgress = false;
                    LoginActivity.this.sendLoginClickPing();
                    return;
                }
                AccountProvider.signInUser(LoginActivity.this, trim, obj, 1, new ICallbackEvent<Object, Exception>() {
                    public void onError(final Exception exc) {
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                LoginActivity.this.sendLoginClickPing();
                                LoginActivity.this.mInlineWhiteProgressBar.setVisibility(8);
                                boolean unused = LoginActivity.this.isLoginInProgress = false;
                                Snackbar.make((View) LoginActivity.this.rootLayout, (CharSequence) VolleyErrorConverter.exceptionToMessage(LoginActivity.this, exc), -1).show();
                            }
                        });
                    }

                    public void onCompleted(final Object obj) {
                        LoginActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                LoginActivity.this.sendLoginClickPing();
                                LoginActivity.this.mInlineWhiteProgressBar.setVisibility(8);
                                boolean unused = LoginActivity.this.isLoginInProgress = false;
                                String parseLoginError = LoginErrorParser.parseLoginError(LoginActivity.this.getApplicationContext(), (JSONObject) obj);
                                if (StringExtensions.isNullOrEmpty(parseLoginError)) {
                                    LoginActivity.this.setResult(-1);
                                    LoginActivity.this.finish();
                                    return;
                                }
                                Snackbar.make((View) LoginActivity.this.rootLayout, (CharSequence) parseLoginError, -1).show();
                            }
                        });
                    }
                });
            }
        });
        this.forgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
        this.supportText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
                intent.putExtra("android.intent.extra.EMAIL", new String[]{LoginActivity.this.getString(R.string.sign_in_support_email)});
                if (intent.resolveActivity(LoginActivity.this.getPackageManager()) != null) {
                    LoginActivity.this.startActivity(intent);
                }
            }
        });
        this.mPasswordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                LoginActivity.this.mSignInButton.performClick();
                return false;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        if (!this.isLoginInProgress) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mUserNameEditText.postDelayed(new Runnable() {
            public void run() {
                if (LoginActivity.this.isFromOnCreate) {
                    LoginActivity.this.mUserNameEditText.requestFocusFromTouch();
                }
                boolean unused = LoginActivity.this.isFromOnCreate = false;
                Util.showKeyboard(LoginActivity.this);
            }
        }, 300);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Util.hideKeyboard(this);
    }

    /* access modifiers changed from: private */
    public void sendLoginClickPing() {
        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule("reg-login", ProfessionalOmnitureData.getAppNamePrefix(this), WBMDOmnitureManager.shared.getLastSentPage()));
    }
}

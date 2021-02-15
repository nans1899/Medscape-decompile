package com.webmd.wbmdproffesionalauthentication.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.net.MailTo;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import org.json.JSONObject;

public class ForgotPasswordActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public ImageView clearIcon;
    /* access modifiers changed from: private */
    public EditText mEmailEditText;
    private TextView mNeedHelpTextView;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public LinearLayout mRootView;
    private Button mSubmitButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.forgot_password_activity);
        setSupportActionBar((Toolbar) findViewById(R.id.forgot_password_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setUpViews();
        setListeners();
    }

    private void setUpViews() {
        this.mRootView = (LinearLayout) findViewById(R.id.root_view);
        this.mEmailEditText = (EditText) findViewById(R.id.forgot_password_email);
        this.mSubmitButton = (Button) findViewById(R.id.forgot_password_sumbit);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.forgot_password_loading_spinner);
        this.mProgressBar = progressBar;
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.auth_component_white), PorterDuff.Mode.MULTIPLY);
        this.mNeedHelpTextView = (TextView) findViewById(R.id.forgot_password_support_email);
        this.clearIcon = (ImageView) findViewById(R.id.forgot_password_clear_icon);
        this.mEmailEditText.requestFocus();
        Util.showKeyboard(this);
    }

    private void setListeners() {
        this.mEmailEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() > 0) {
                    ForgotPasswordActivity.this.clearIcon.setVisibility(0);
                } else {
                    ForgotPasswordActivity.this.clearIcon.setVisibility(8);
                }
            }
        });
        this.clearIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ForgotPasswordActivity.this.mEmailEditText.setText("");
            }
        });
        this.mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ForgotPasswordActivity.this.mProgressBar.setVisibility(0);
                Util.hideKeyboard(ForgotPasswordActivity.this);
                String obj = ForgotPasswordActivity.this.mEmailEditText.getText().toString();
                if (!Util.isOnline(ForgotPasswordActivity.this)) {
                    Util.showSnackBar(ForgotPasswordActivity.this.mRootView, ForgotPasswordActivity.this.getString(R.string.error_connection_required));
                    ForgotPasswordActivity.this.mProgressBar.setVisibility(8);
                } else if (!Util.isValidEmail(obj)) {
                    ForgotPasswordActivity forgotPasswordActivity = ForgotPasswordActivity.this;
                    forgotPasswordActivity.showCustomToast(forgotPasswordActivity.getApplicationContext(), ForgotPasswordActivity.this.getString(R.string.forgot_password_noemail), 1);
                    ForgotPasswordActivity.this.mProgressBar.setVisibility(8);
                } else {
                    AccountProvider.sendPasswordResetRequest(ForgotPasswordActivity.this, obj, "en_us", new ICallbackEvent<JSONObject, String>() {
                        public void onError(final String str) {
                            ForgotPasswordActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    ForgotPasswordActivity.this.mProgressBar.setVisibility(8);
                                    ForgotPasswordActivity.this.showCustomToast(ForgotPasswordActivity.this, str, 1);
                                }
                            });
                        }

                        public void onCompleted(final JSONObject jSONObject) {
                            ForgotPasswordActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    JSONObject jSONObject = jSONObject;
                                    if (jSONObject == null) {
                                        return;
                                    }
                                    if (!jSONObject.isNull("errorDescription")) {
                                        String optString = jSONObject.optString("errorDescription");
                                        if (!StringExtensions.isNullOrEmpty(optString)) {
                                            ForgotPasswordActivity.this.mProgressBar.setVisibility(8);
                                            ForgotPasswordActivity.this.showCustomToast(ForgotPasswordActivity.this, optString, 1);
                                            return;
                                        }
                                        ForgotPasswordActivity.this.mProgressBar.setVisibility(8);
                                        ForgotPasswordActivity.this.showCustomToast(ForgotPasswordActivity.this, ForgotPasswordActivity.this.getString(R.string.forgot_password_error_request_fail), 1);
                                        return;
                                    }
                                    ForgotPasswordActivity.this.mProgressBar.setVisibility(8);
                                    ForgotPasswordActivity.this.showCustomToast(ForgotPasswordActivity.this, ForgotPasswordActivity.this.getString(R.string.forgot_password_success), 1);
                                    ForgotPasswordActivity.this.finish();
                                }
                            });
                        }
                    });
                }
            }
        });
        this.mNeedHelpTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
                intent.putExtra("android.intent.extra.EMAIL", new String[]{ForgotPasswordActivity.this.getString(R.string.sign_in_support_email)});
                if (intent.resolveActivity(ForgotPasswordActivity.this.getPackageManager()) != null) {
                    ForgotPasswordActivity.this.startActivity(intent);
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public void showCustomToast(Context context, String str, int i) {
        if (context != null && str != null) {
            Toast.makeText(context, str, i).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Util.hideKeyboard(this);
    }
}

package com.webmd.wbmdproffesionalauthentication.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.textfield.TextInputLayout;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdomnituremanager.ProfessionalOmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.activities.LoginActivity;
import com.webmd.wbmdproffesionalauthentication.activities.RegistrationActivity;
import com.webmd.wbmdproffesionalauthentication.model.BasicInfo;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.RegValidator;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter;
import com.webmd.wbmdproffesionalauthentication.viewmodels.NewsletterViewModel;
import java.util.Map;
import org.json.JSONObject;

public class SignUpProfileFragment extends Fragment {
    static String TAG = SignUpProfileFragment.class.getSimpleName();
    boolean hasThirdPage = false;
    boolean isRegisterInProgress = false;
    /* access modifiers changed from: private */
    public BasicInfo mBasicInfo;
    Button mBtnDone;
    EditText mEvEmailAddress;
    TextInputLayout mEvEmailAddressLayout;
    EditText mEvFirstName;
    TextInputLayout mEvFirstNameLayout;
    EditText mEvLastName;
    TextInputLayout mEvLastNameLayout;
    EditText mEvPassword;
    TextInputLayout mEvPasswordLayout;
    EditText mEvPostalCode;
    TextInputLayout mEvPostalCodeLayout;
    EditText mEvZipCode;
    TextInputLayout mEvZipCodeLayout;
    boolean mIsEmailValid = false;
    boolean mIsFirstNameValid = false;
    boolean mIsLastNameValid = false;
    boolean mIsPasswordValid = false;
    boolean mIsZipCodeValid = false;
    ProgressBar mProgressEmail;
    ProgressBar mProgressSignUp;
    /* access modifiers changed from: private */
    public View mRootView;
    /* access modifiers changed from: private */
    public UserProfile mUserProfile;
    /* access modifiers changed from: private */
    public boolean mValidateEmail;
    TextView mtvDisclaimer;
    private TextView newsLetterDisclaimer;
    /* access modifiers changed from: private */
    public SwitchCompat newsletterSwitch;
    /* access modifiers changed from: private */
    public NewsletterViewModel newsletterViewModel;
    RegValidator validator;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setUserProfile();
        setTitle();
        Util.showKeyboard(getActivity());
        View inflate = layoutInflater.inflate(R.layout.fragment_sign_up_profile, viewGroup, false);
        this.mRootView = inflate;
        EditText editText = (EditText) inflate.findViewById(R.id.evFirstName);
        this.mEvFirstName = editText;
        editText.requestFocus();
        this.mEvLastName = (EditText) this.mRootView.findViewById(R.id.evLastName);
        this.mEvEmailAddress = (EditText) this.mRootView.findViewById(R.id.ev_email_address);
        this.mEvPassword = (EditText) this.mRootView.findViewById(R.id.ev_password);
        this.mEvZipCode = (EditText) this.mRootView.findViewById(R.id.ev_zipcode);
        this.mEvPostalCode = (EditText) this.mRootView.findViewById(R.id.evPostalCode);
        this.mBtnDone = (Button) this.mRootView.findViewById(R.id.btnDone);
        this.newsletterSwitch = (SwitchCompat) this.mRootView.findViewById(R.id.profession_newsletter);
        this.newsLetterDisclaimer = (TextView) this.mRootView.findViewById(R.id.news_letter_disclaimer);
        this.mProgressEmail = (ProgressBar) this.mRootView.findViewById(R.id.progressEmail);
        this.mEvFirstNameLayout = initializeInputTextLayout(this.mRootView, R.id.evFirstNameLayout, R.string.signup_firstname);
        this.mEvLastNameLayout = initializeInputTextLayout(this.mRootView, R.id.evLastNameLayout, R.string.signup_lastname);
        this.mEvEmailAddressLayout = initializeInputTextLayout(this.mRootView, R.id.evEmailAddressLayout, R.string.signup_email);
        this.mEvPasswordLayout = initializeInputTextLayout(this.mRootView, R.id.evPasswordLayout, R.string.signup_password);
        this.mEvZipCodeLayout = initializeInputTextLayout(this.mRootView, R.id.evZipCodeLayout, R.string.signup_zipcode);
        UserProfile userProfile = this.mUserProfile;
        if (!(userProfile == null || userProfile.getProfessionProfile() == null || "us".equalsIgnoreCase(this.mUserProfile.getProfessionProfile().getCountryCode()))) {
            this.mEvZipCodeLayout.setVisibility(8);
            TextInputLayout initializeInputTextLayout = initializeInputTextLayout(this.mRootView, R.id.evPostalCodeLayout, R.string.signup_postalcode);
            this.mEvPostalCodeLayout = initializeInputTextLayout;
            initializeInputTextLayout.setVisibility(0);
        }
        this.mtvDisclaimer = (TextView) this.mRootView.findViewById(R.id.tvDisclaimer);
        if (getActivity() != null && (getActivity() instanceof RegistrationActivity)) {
            ((RegistrationActivity) getActivity()).setSpannableTextForTerms(this.mtvDisclaimer);
        }
        this.validator = new RegValidator();
        this.mBtnDone.setAlpha(0.5f);
        ProgressBar progressBar = (ProgressBar) this.mRootView.findViewById(R.id.progressSignUp);
        this.mProgressSignUp = progressBar;
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.auth_component_white), PorterDuff.Mode.MULTIPLY);
        this.mBtnDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Util.hideKeyboard(SignUpProfileFragment.this.getActivity());
                SignUpProfileFragment.this.mBasicInfo.setFirstName(SignUpProfileFragment.this.mEvFirstName.getText().toString());
                SignUpProfileFragment.this.mBasicInfo.setLastName(SignUpProfileFragment.this.mEvLastName.getText().toString());
                SignUpProfileFragment.this.mBasicInfo.setPassword(SignUpProfileFragment.this.mEvPassword.getText().toString());
                SignUpProfileFragment.this.mBasicInfo.setEmailAddress(SignUpProfileFragment.this.mEvEmailAddress.getText().toString());
                if (!"us".equalsIgnoreCase(SignUpProfileFragment.this.mUserProfile.getProfessionProfile().getCountryCode())) {
                    SignUpProfileFragment.this.mBasicInfo.setZipCode(SignUpProfileFragment.this.mEvPostalCode.getText().toString());
                } else {
                    SignUpProfileFragment.this.mBasicInfo.setZipCode(SignUpProfileFragment.this.mEvZipCode.getText().toString());
                }
                if (!Util.isOnline(SignUpProfileFragment.this.getActivity())) {
                    Util.showSnackBar(SignUpProfileFragment.this.mRootView, SignUpProfileFragment.this.getString(R.string.error_connection_required));
                } else if (SignUpProfileFragment.this.hasThirdPage) {
                    WBMDOmnitureManager.shared.setModule("reg-new2");
                    WBMDOmnitureManager.shared.setLink(ProfessionalOmnitureData.getAppNamePrefix(SignUpProfileFragment.this.getActivity()));
                    ((RegistrationActivity) SignUpProfileFragment.this.getActivity()).loadNextFragment(new SignUpEducationFragment(), "education");
                } else {
                    SignUpProfileFragment.this.mProgressSignUp.setVisibility(0);
                    SignUpProfileFragment.this.getActivity().getWindow().setFlags(16, 16);
                    SignUpProfileFragment.this.isRegisterInProgress = true;
                    SignUpProfileFragment.this.mBtnDone.setEnabled(false);
                    RegistrationProvider.getInstance(SignUpProfileFragment.this.getContext()).registerUser(SignUpProfileFragment.this.getContext(), SignUpProfileFragment.this.mUserProfile, new ICallbackEvent<Boolean, String>() {
                        public void onError(String str) {
                            if (str == null) {
                                SignUpProfileFragment.this.sendRegistrationOmniture(false);
                            } else if (StringExtensions.isNotEmpty(str)) {
                                if (str.equalsIgnoreCase("OAUTH_FAILED")) {
                                    SignUpProfileFragment.this.showRetryDialog();
                                } else {
                                    SignUpProfileFragment.this.sendRegistrationOmniture(false);
                                    Util.showSnackBar(SignUpProfileFragment.this.mRootView, str);
                                }
                            }
                            if (SignUpProfileFragment.this.getActivity() != null) {
                                SignUpProfileFragment.this.getActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        SignUpProfileFragment.this.mBtnDone.setEnabled(true);
                                        SignUpProfileFragment.this.mProgressSignUp.setVisibility(8);
                                        SignUpProfileFragment.this.getActivity().getWindow().clearFlags(16);
                                        SignUpProfileFragment.this.isRegisterInProgress = false;
                                    }
                                });
                            }
                        }

                        public void onCompleted(Boolean bool) {
                            if (bool.booleanValue()) {
                                SignUpProfileFragment.this.sendRegistrationOmniture(true);
                                if (SignUpProfileFragment.this.getActivity() != null) {
                                    SignUpProfileFragment.this.getActivity().setResult(-1);
                                    SignUpProfileFragment.this.getActivity().finish();
                                    return;
                                }
                                return;
                            }
                            SignUpProfileFragment.this.sendRegistrationOmniture(false);
                            Util.showSnackBar(SignUpProfileFragment.this.mRootView, SignUpProfileFragment.this.getString(R.string.error_register_user));
                            SignUpProfileFragment.this.mProgressSignUp.setVisibility(8);
                            SignUpProfileFragment.this.getActivity().getWindow().clearFlags(16);
                            SignUpProfileFragment.this.isRegisterInProgress = false;
                        }
                    });
                    WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule("reg-new2", ProfessionalOmnitureData.getAppNamePrefix(SignUpProfileFragment.this.getActivity()), WBMDOmnitureManager.shared.getLastSentPage()));
                }
            }
        });
        if (this.hasThirdPage) {
            this.mBtnDone.setText(getResources().getString(R.string.landing_activity_continue));
            this.mtvDisclaimer.setVisibility(8);
        } else {
            this.mtvDisclaimer.setVisibility(0);
        }
        return this.mRootView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.newsletterViewModel == null) {
            this.newsletterViewModel = (NewsletterViewModel) ViewModelProviders.of(getActivity()).get(NewsletterViewModel.class);
        }
        setUpNewsletterSwitcher();
    }

    private void setUpNewsletterSwitcher() {
        if (this.newsletterViewModel.getShowNewsletterToggle().getValue().booleanValue()) {
            this.newsletterSwitch.setVisibility(0);
            this.newsLetterDisclaimer.setVisibility(0);
            this.newsletterSwitch.setChecked(this.newsletterViewModel.getNewsLetterToggleStatus().getValue().booleanValue());
        } else {
            this.newsletterSwitch.setVisibility(8);
            this.newsLetterDisclaimer.setVisibility(8);
        }
        this.newsletterSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignUpProfileFragment.this.newsletterViewModel.setNewsLetterToggleStatus(SignUpProfileFragment.this.newsletterSwitch.isChecked());
            }
        });
    }

    /* access modifiers changed from: private */
    public void showRetryDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.sign_up_dialog_retry_title);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.sign_up_dialog_button_retry, new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, int i) {
                AccountProvider.signInUser(SignUpProfileFragment.this.getContext(), SignUpProfileFragment.this.mBasicInfo.getEmailAddress(), SignUpProfileFragment.this.mBasicInfo.getPassword(), new ICallbackEvent<Object, Exception>() {
                    public void onError(Exception exc) {
                        String exceptionToMessage = VolleyErrorConverter.exceptionToMessage(SignUpProfileFragment.this.getContext(), exc);
                        if (exc != null && StringExtensions.isNullOrEmpty(exceptionToMessage)) {
                            exceptionToMessage = exc.getMessage();
                        }
                        Util.showSnackBar(SignUpProfileFragment.this.mRootView, exceptionToMessage);
                        dialogInterface.dismiss();
                        SignUpProfileFragment.this.showRetryDialog();
                        AccountProvider.logCrashlyticEvent(SignUpProfileFragment.this.getContext(), exc, "reg_prof_error", exceptionToMessage, false);
                    }

                    public void onCompleted(Object obj) {
                        String parseLoginError = LoginErrorParser.parseLoginError(SignUpProfileFragment.this.getContext(), (JSONObject) obj);
                        if (!StringExtensions.isNullOrEmpty(parseLoginError)) {
                            Util.showSnackBar(SignUpProfileFragment.this.mRootView, parseLoginError);
                            dialogInterface.dismiss();
                            SignUpProfileFragment.this.showRetryDialog();
                            AccountProvider.logCrashlyticEvent(SignUpProfileFragment.this.getContext(), new Exception(parseLoginError), "reg_prof_login_parse", parseLoginError, false);
                        } else if (SignUpProfileFragment.this.getActivity() != null) {
                            SignUpProfileFragment.this.getActivity().setResult(-1);
                            SignUpProfileFragment.this.getActivity().finish();
                        }
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.sign_up_dialog_button_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SignUpProfileFragment.this.startActivity(new Intent(SignUpProfileFragment.this.getActivity(), LoginActivity.class));
                SignUpProfileFragment.this.getActivity().finish();
            }
        });
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    builder.show();
                }
            });
        }
    }

    public void onResume() {
        super.onResume();
        isUserModelValid();
        addFirstNameValidator();
        addLastNameValidator();
        addEmailValidator();
        addPasswordValidator();
        if (!(this.mEvZipCode == null || this.mEvZipCodeLayout == null)) {
            addZipCodeValidator();
        }
        if (!(this.mEvPostalCode == null || this.mEvPostalCodeLayout == null)) {
            addPostalCodeValidator();
        }
        trackOmniture();
    }

    public void onPause() {
        super.onPause();
        Util.hideKeyboard(getActivity());
    }

    public void trackOmniture() {
        WBMDOmnitureManager.sendPageViewWithSharedModule(ProfessionalOmnitureData.buildPage("registration", "view", (String) null, ProfessionalOmnitureData.appendAppPrefix(getContext(), "screen-2-", true)), (Map<String, String>) null);
    }

    private void addFirstNameValidator() {
        this.mEvFirstName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpProfileFragment.this.mIsFirstNameValid = false;
                SignUpProfileFragment.this.isUserModelValid();
                int validateName = SignUpProfileFragment.this.validator.validateName(charSequence.toString());
                if (validateName == -1) {
                    SignUpProfileFragment.this.mIsFirstNameValid = true;
                    SignUpProfileFragment.this.isUserModelValid();
                    SignUpProfileFragment.this.mEvFirstNameLayout.setError("");
                    SignUpProfileFragment signUpProfileFragment = SignUpProfileFragment.this;
                    signUpProfileFragment.clearBackground(signUpProfileFragment.mEvFirstNameLayout);
                    return;
                }
                SignUpProfileFragment.this.mEvFirstNameLayout.setError(SignUpProfileFragment.this.getString(validateName));
                SignUpProfileFragment signUpProfileFragment2 = SignUpProfileFragment.this;
                signUpProfileFragment2.clearBackground(signUpProfileFragment2.mEvFirstNameLayout);
            }
        });
        this.mEvFirstName.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpProfileFragment.this.validateEmail();
                return false;
            }
        });
    }

    private void addLastNameValidator() {
        this.mEvLastName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpProfileFragment.this.mIsLastNameValid = false;
                SignUpProfileFragment.this.isUserModelValid();
                int validateName = SignUpProfileFragment.this.validator.validateName(charSequence.toString());
                if (validateName == -1) {
                    SignUpProfileFragment.this.mIsLastNameValid = true;
                    SignUpProfileFragment.this.isUserModelValid();
                    SignUpProfileFragment.this.mEvLastNameLayout.setError("");
                    SignUpProfileFragment signUpProfileFragment = SignUpProfileFragment.this;
                    signUpProfileFragment.clearBackground(signUpProfileFragment.mEvLastNameLayout);
                    return;
                }
                SignUpProfileFragment.this.mEvLastNameLayout.setError(SignUpProfileFragment.this.getString(validateName));
                SignUpProfileFragment signUpProfileFragment2 = SignUpProfileFragment.this;
                signUpProfileFragment2.clearBackground(signUpProfileFragment2.mEvLastNameLayout);
            }
        });
        this.mEvLastName.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpProfileFragment.this.validateEmail();
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void validateEmail() {
        if (this.mValidateEmail) {
            this.mEvEmailAddressLayout.setError("");
            clearBackground(this.mEvEmailAddressLayout);
            this.mProgressEmail.setVisibility(0);
            this.validator.validateEmail(this.mEvEmailAddress.getText().toString(), new ICallbackEvent<Boolean, Integer>() {
                public void onError(final Integer num) {
                    SignUpProfileFragment.this.mIsEmailValid = false;
                    if (SignUpProfileFragment.this.isAdded() && SignUpProfileFragment.this.getActivity() != null) {
                        SignUpProfileFragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                SignUpProfileFragment.this.mEvEmailAddressLayout.setError(SignUpProfileFragment.this.getString(num.intValue()));
                                SignUpProfileFragment.this.mProgressEmail.setVisibility(8);
                                SignUpProfileFragment.this.isUserModelValid();
                                SignUpProfileFragment.this.clearBackground(SignUpProfileFragment.this.mEvEmailAddressLayout);
                                if (num.intValue() == R.string.registration_page2_uniqueemail) {
                                    SignUpProfileFragment.this.showLogInPrompt();
                                }
                            }
                        });
                    }
                }

                public void onCompleted(final Boolean bool) {
                    if (SignUpProfileFragment.this.isAdded() && SignUpProfileFragment.this.getActivity() != null) {
                        SignUpProfileFragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                SignUpProfileFragment.this.mProgressEmail.setVisibility(8);
                                if (bool.booleanValue()) {
                                    SignUpProfileFragment.this.mIsEmailValid = true;
                                    SignUpProfileFragment.this.isUserModelValid();
                                    SignUpProfileFragment.this.mEvEmailAddressLayout.setError("");
                                    SignUpProfileFragment.this.clearBackground(SignUpProfileFragment.this.mEvEmailAddressLayout);
                                    return;
                                }
                                SignUpProfileFragment.this.mEvEmailAddressLayout.setError(SignUpProfileFragment.this.getString(R.string.error_sign_up_data));
                                SignUpProfileFragment.this.clearBackground(SignUpProfileFragment.this.mEvEmailAddressLayout);
                            }
                        });
                    }
                }
            }, getActivity());
        }
        this.mValidateEmail = false;
    }

    private void addEmailValidator() {
        this.mEvEmailAddress.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                SignUpProfileFragment.this.validateEmail();
                return false;
            }
        });
        this.mEvEmailAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                boolean unused = SignUpProfileFragment.this.mValidateEmail = true;
                SignUpProfileFragment.this.mIsEmailValid = false;
                SignUpProfileFragment.this.disableDoneButton();
            }
        });
    }

    private void addPasswordValidator() {
        this.mEvPassword.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpProfileFragment.this.mIsPasswordValid = false;
                SignUpProfileFragment.this.isUserModelValid();
            }

            public void afterTextChanged(Editable editable) {
                int validatePassword = SignUpProfileFragment.this.validator.validatePassword(editable.toString());
                if (validatePassword == -1) {
                    SignUpProfileFragment.this.mIsPasswordValid = true;
                    SignUpProfileFragment.this.isUserModelValid();
                    SignUpProfileFragment.this.mEvPasswordLayout.setError("");
                    SignUpProfileFragment signUpProfileFragment = SignUpProfileFragment.this;
                    signUpProfileFragment.clearBackground(signUpProfileFragment.mEvPasswordLayout);
                    return;
                }
                SignUpProfileFragment.this.mEvPasswordLayout.setError(SignUpProfileFragment.this.getString(validatePassword));
                SignUpProfileFragment signUpProfileFragment2 = SignUpProfileFragment.this;
                signUpProfileFragment2.clearBackground(signUpProfileFragment2.mEvPasswordLayout);
            }
        });
        this.mEvPassword.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpProfileFragment.this.validateEmail();
                return false;
            }
        });
    }

    private void addZipCodeValidator() {
        this.mEvZipCode.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpProfileFragment.this.mIsZipCodeValid = false;
                SignUpProfileFragment.this.isUserModelValid();
                if (SignUpProfileFragment.this.validator != null && charSequence != null && SignUpProfileFragment.this.mUserProfile != null && SignUpProfileFragment.this.mUserProfile.getProfessionProfile() != null) {
                    String validateZIP = SignUpProfileFragment.this.validator.validateZIP(SignUpProfileFragment.this.getContext(), charSequence.toString(), SignUpProfileFragment.this.mUserProfile.getProfessionProfile().getCountryCode());
                    if (StringExtensions.isNullOrEmpty(validateZIP)) {
                        SignUpProfileFragment.this.mIsZipCodeValid = true;
                        SignUpProfileFragment.this.isUserModelValid();
                        SignUpProfileFragment.this.mEvZipCodeLayout.setError("");
                        SignUpProfileFragment signUpProfileFragment = SignUpProfileFragment.this;
                        signUpProfileFragment.clearBackground(signUpProfileFragment.mEvZipCodeLayout);
                        return;
                    }
                    SignUpProfileFragment.this.mEvZipCodeLayout.setError(validateZIP);
                    SignUpProfileFragment signUpProfileFragment2 = SignUpProfileFragment.this;
                    signUpProfileFragment2.clearBackground(signUpProfileFragment2.mEvZipCodeLayout);
                }
            }
        });
        this.mEvZipCode.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpProfileFragment.this.validateEmail();
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void clearBackground(TextInputLayout textInputLayout) {
        if (isAdded() && getActivity() != null && textInputLayout != null && textInputLayout.getEditText() != null && textInputLayout.getEditText().getBackground() != null) {
            textInputLayout.getEditText().getBackground().setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), PorterDuff.Mode.SRC_IN);
        }
    }

    private void addPostalCodeValidator() {
        this.mEvPostalCode.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpProfileFragment.this.mIsZipCodeValid = false;
                SignUpProfileFragment.this.isUserModelValid();
                if (SignUpProfileFragment.this.validator != null && charSequence != null && SignUpProfileFragment.this.mUserProfile != null && SignUpProfileFragment.this.mUserProfile.getProfessionProfile() != null) {
                    String validateZIP = SignUpProfileFragment.this.validator.validateZIP(SignUpProfileFragment.this.getContext(), charSequence.toString(), SignUpProfileFragment.this.mUserProfile.getProfessionProfile().getCountryCode());
                    if (StringExtensions.isNullOrEmpty(validateZIP)) {
                        SignUpProfileFragment.this.mIsZipCodeValid = true;
                        SignUpProfileFragment.this.isUserModelValid();
                        SignUpProfileFragment.this.mEvPostalCodeLayout.setError("");
                        SignUpProfileFragment signUpProfileFragment = SignUpProfileFragment.this;
                        signUpProfileFragment.clearBackground(signUpProfileFragment.mEvPostalCodeLayout);
                        return;
                    }
                    SignUpProfileFragment.this.mEvPostalCodeLayout.setError(validateZIP);
                    SignUpProfileFragment signUpProfileFragment2 = SignUpProfileFragment.this;
                    signUpProfileFragment2.clearBackground(signUpProfileFragment2.mEvPostalCodeLayout);
                }
            }
        });
        this.mEvPostalCode.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpProfileFragment.this.validateEmail();
                return false;
            }
        });
    }

    private TextInputLayout initializeInputTextLayout(View view, int i, int i2) {
        TextInputLayout textInputLayout = (TextInputLayout) view.findViewById(i);
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setHint(getString(i2));
        return textInputLayout;
    }

    /* access modifiers changed from: private */
    public void isUserModelValid() {
        if (!this.mIsFirstNameValid || !this.mIsZipCodeValid || !this.mIsPasswordValid || !this.mIsEmailValid || !this.mIsLastNameValid) {
            disableDoneButton();
        } else {
            enableDoneButton();
        }
    }

    private void enableDoneButton() {
        this.mBtnDone.setEnabled(true);
        this.mBtnDone.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public void disableDoneButton() {
        this.mBtnDone.setEnabled(false);
        this.mBtnDone.setAlpha(0.5f);
    }

    private void setUserProfile() {
        UserProfile instance = UserProfile.getInstance();
        this.mUserProfile = instance;
        this.mBasicInfo = instance.getBasicProfile();
        String professionId = this.mUserProfile.getProfessionProfile().getProfessionId();
        if (professionId == null) {
            return;
        }
        if (professionId.equals(UserProfile.PHYSICIAN_ID) || professionId.equals(UserProfile.MEDICAL_LABORATORY_ID)) {
            this.hasThirdPage = true;
        }
    }

    private void setTitle() {
        if (getActivity() != null && (getActivity() instanceof RegistrationActivity)) {
            if (UserProfile.getInstance().getSteps() == 0) {
                ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up_plain));
                return;
            }
            ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up, 2, Integer.valueOf(UserProfile.getInstance().getSteps())));
        }
    }

    /* access modifiers changed from: private */
    public void showLogInPrompt() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.alertDialog);
        builder.setTitle((CharSequence) getString(R.string.registration_page2_address_in_use_title));
        builder.setMessage((CharSequence) getString(R.string.registration_page2_address_in_use_msg));
        builder.setPositiveButton((CharSequence) getString(R.string.registration_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SignUpProfileFragment.this.startActivity(new Intent(SignUpProfileFragment.this.getContext(), LoginActivity.class));
            }
        });
        builder.setNegativeButton((CharSequence) getString(R.string.registration_cancel), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public boolean isRegisterInProgress() {
        return this.isRegisterInProgress;
    }

    public void setNewsletterViewModel(NewsletterViewModel newsletterViewModel2) {
        this.newsletterViewModel = newsletterViewModel2;
    }

    public void sendRegistrationOmniture(boolean z) {
        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(z ? "reg-success" : "reg-error", ProfessionalOmnitureData.appendAppPrefix(getContext(), "f-", true), WBMDOmnitureManager.shared.getLastSentPage()));
    }
}

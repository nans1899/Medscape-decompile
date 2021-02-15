package com.webmd.wbmdproffesionalauthentication.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.webmd.wbmdproffesionalauthentication.license.LicenseResponse;
import com.webmd.wbmdproffesionalauthentication.license.LicenseViewModel;
import com.webmd.wbmdproffesionalauthentication.model.UserEducation;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.EnvironmentUtil;
import com.webmd.wbmdproffesionalauthentication.utilities.ListTypes;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

public class SignUpEducationFragment extends Fragment {
    View btnBirthYear;
    Button btnDone;
    View btnGraduationYear;
    View btnSchool;
    View btnSchoolCountry;
    View btnSchoolState;
    boolean isRegisterInProgress = false;
    View licenseLayout;
    EditText licenseNumber;
    TextView licenseTitle;
    LicenseViewModel licenseViewModel;
    TextInputLayout licenseWrapper;
    UserEducation mEducationProfile;
    ProgressBar mProgressDone;
    RegistrationProvider mRegistrationDataProvider;
    UserProfile mUserProfile;
    TextView mtvDisclaimer;
    AppCompatCheckBox noLicense;
    View rootView;

    public void trackOmniture() {
        WBMDOmnitureManager.sendPageViewWithSharedModule(ProfessionalOmnitureData.buildPage("registration", "view", (String) null, ProfessionalOmnitureData.appendAppPrefix(getContext(), "screen-3-", true)), (Map<String, String>) null);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.licenseViewModel == null) {
            this.licenseViewModel = (LicenseViewModel) ViewModelProviders.of((Fragment) this).get(LicenseViewModel.class);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setTitle();
        this.mRegistrationDataProvider = RegistrationProvider.getInstance(getContext());
        UserProfile instance = UserProfile.getInstance();
        this.mUserProfile = instance;
        this.mEducationProfile = instance.getEducationProfile();
        this.licenseViewModel.setCountry(this.mUserProfile.getProfessionProfile().getCountryCode());
        this.licenseViewModel.setEnvironment(EnvironmentUtil.Companion.getUrlPrefixForServiceEnv(getContext()));
        View inflate = layoutInflater.inflate(R.layout.fragment_signup_education, viewGroup, false);
        this.rootView = inflate;
        this.licenseTitle = (TextView) inflate.findViewById(R.id.license_title);
        this.licenseNumber = (EditText) this.rootView.findViewById(R.id.license_number);
        this.licenseWrapper = (TextInputLayout) this.rootView.findViewById(R.id.license_edit_text_wrapper);
        this.noLicense = (AppCompatCheckBox) this.rootView.findViewById(R.id.no_license_id);
        this.licenseLayout = this.rootView.findViewById(R.id.license_layout);
        this.btnSchoolState = this.rootView.findViewById(R.id.btnSchoolState);
        this.btnSchoolCountry = this.rootView.findViewById(R.id.btnSchoolCountry);
        this.btnSchool = this.rootView.findViewById(R.id.btnSchool);
        this.btnBirthYear = this.rootView.findViewById(R.id.btnBirthYear);
        this.btnGraduationYear = this.rootView.findViewById(R.id.btnGraduationYear);
        Button button = (Button) this.rootView.findViewById(R.id.btnDone);
        this.btnDone = button;
        button.setAlpha(0.5f);
        this.mtvDisclaimer = (TextView) this.rootView.findViewById(R.id.tvDisclaimer);
        if (getActivity() instanceof RegistrationActivity) {
            ((RegistrationActivity) getActivity()).setSpannableTextForTerms(this.mtvDisclaimer);
        }
        ProgressBar progressBar = (ProgressBar) this.rootView.findViewById(R.id.progressDone);
        this.mProgressDone = progressBar;
        progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.auth_component_white), PorterDuff.Mode.MULTIPLY);
        this.btnDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SignUpEducationFragment.this.mProgressDone.setVisibility(0);
                if (SignUpEducationFragment.this.licenseViewModel.isLicenseValid() || SignUpEducationFragment.this.licenseViewModel.getBypassValidation()) {
                    SignUpEducationFragment.this.startRegistration();
                } else {
                    SignUpEducationFragment.this.licenseViewModel.validateLicence(true);
                }
            }
        });
        this.noLicense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SignUpEducationFragment.this.licenseNumber.getText().clear();
                }
                SignUpEducationFragment.this.licenseNumber.setEnabled(!z);
                SignUpEducationFragment.this.licenseViewModel.noLicenseChecked(z);
                boolean unused = SignUpEducationFragment.this.isValid();
            }
        });
        this.licenseNumber.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpEducationFragment.this.licenseViewModel.setLicense(charSequence.toString());
                SignUpEducationFragment.this.licenseWrapper.setError("");
                boolean unused = SignUpEducationFragment.this.isValid();
            }
        });
        this.licenseNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (!SignUpEducationFragment.this.licenseViewModel.getLicenseNumber().isEmpty()) {
                    SignUpEducationFragment.this.licenseViewModel.validateLicence(false);
                }
                return false;
            }
        });
        this.licenseNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z && !SignUpEducationFragment.this.licenseViewModel.getLicenseNumber().isEmpty()) {
                    SignUpEducationFragment.this.licenseViewModel.validateLicence(false);
                }
            }
        });
        this.licenseViewModel.getLicencesValidator().getValidationResult().removeObservers(this);
        this.licenseViewModel.getLicencesValidator().getValidationResult().observe(this, new Observer<LicenseResponse>() {
            public void onChanged(LicenseResponse licenseResponse) {
                SignUpEducationFragment.this.mProgressDone.setVisibility(8);
                if (licenseResponse == null) {
                    return;
                }
                if (licenseResponse.isValid()) {
                    SignUpEducationFragment.this.mEducationProfile.setLicenseId(SignUpEducationFragment.this.licenseViewModel.getLicenseNumber());
                    SignUpEducationFragment.this.licenseWrapper.setError("");
                    if (SignUpEducationFragment.this.licenseViewModel.getShouldContinueWithRegistration()) {
                        SignUpEducationFragment.this.startRegistration();
                        return;
                    }
                    return;
                }
                SignUpEducationFragment.this.licenseWrapper.setError(licenseResponse.getErrorDescription());
            }
        });
        setViews();
        return this.rootView;
    }

    public void startRegistration() {
        if (Util.isOnline(getActivity())) {
            Util.hideKeyboard(getActivity());
            this.btnDone.setEnabled(false);
            this.btnDone.setClickable(false);
            this.mProgressDone.setVisibility(0);
            getActivity().getWindow().setFlags(16, 16);
            this.isRegisterInProgress = true;
            this.mRegistrationDataProvider.registerUser(getContext(), this.mUserProfile, new ICallbackEvent<Boolean, String>() {
                public void onError(final String str) {
                    if (SignUpEducationFragment.this.getActivity() != null) {
                        SignUpEducationFragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                String str = str;
                                if (str == null) {
                                    SignUpEducationFragment.this.sendRegistrationOmniture(false);
                                } else if (StringExtensions.isNotEmpty(str)) {
                                    if (str.equalsIgnoreCase("OAUTH_FAILED")) {
                                        SignUpEducationFragment.this.showRetryDialog();
                                    } else if (SignUpEducationFragment.this.isAdded() && SignUpEducationFragment.this.getActivity() != null) {
                                        SignUpEducationFragment.this.sendRegistrationOmniture(false);
                                        Util.showSnackBar(SignUpEducationFragment.this.rootView, str);
                                    }
                                }
                                SignUpEducationFragment.this.btnDone.setEnabled(true);
                                SignUpEducationFragment.this.btnDone.setClickable(true);
                                SignUpEducationFragment.this.mProgressDone.setVisibility(8);
                                SignUpEducationFragment.this.getActivity().getWindow().clearFlags(16);
                                SignUpEducationFragment.this.isRegisterInProgress = false;
                            }
                        });
                    }
                }

                public void onCompleted(final Boolean bool) {
                    if (SignUpEducationFragment.this.getActivity() != null) {
                        SignUpEducationFragment.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                if (bool.booleanValue()) {
                                    SignUpEducationFragment.this.sendRegistrationOmniture(true);
                                    if (SignUpEducationFragment.this.getActivity() != null) {
                                        SignUpEducationFragment.this.getActivity().setResult(-1);
                                        SignUpEducationFragment.this.getActivity().finish();
                                        return;
                                    }
                                    return;
                                }
                                SignUpEducationFragment.this.sendRegistrationOmniture(false);
                                Util.showSnackBar(SignUpEducationFragment.this.rootView, SignUpEducationFragment.this.getString(R.string.error_register_user));
                                SignUpEducationFragment.this.mProgressDone.setVisibility(8);
                                SignUpEducationFragment.this.isRegisterInProgress = false;
                                SignUpEducationFragment.this.getActivity().getWindow().clearFlags(16);
                            }
                        });
                    }
                }
            });
            WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule("reg-new3", ProfessionalOmnitureData.getAppNamePrefix(getContext()), WBMDOmnitureManager.shared.getLastSentPage()));
            return;
        }
        Util.showSnackBar(this.rootView, getString(R.string.error_connection_required));
        this.mProgressDone.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showRetryDialog() {
        if (isAdded() || getContext() != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle((CharSequence) getString(R.string.signup_login_failed));
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, int i) {
                    AccountProvider.signInUser(SignUpEducationFragment.this.getContext(), SignUpEducationFragment.this.mUserProfile.getBasicProfile().getEmailAddress(), SignUpEducationFragment.this.mUserProfile.getBasicProfile().getPassword(), new ICallbackEvent<Object, Exception>() {
                        public void onError(Exception exc) {
                            String exceptionToMessage = VolleyErrorConverter.exceptionToMessage(SignUpEducationFragment.this.getContext(), exc);
                            if (exc != null && StringExtensions.isNullOrEmpty(exceptionToMessage)) {
                                exceptionToMessage = exc.getMessage();
                            }
                            Util.showSnackBar(SignUpEducationFragment.this.rootView, exceptionToMessage);
                            dialogInterface.dismiss();
                            SignUpEducationFragment.this.showRetryDialog();
                            AccountProvider.logCrashlyticEvent(SignUpEducationFragment.this.getContext(), exc, "reg_edu_error", exceptionToMessage, false);
                        }

                        public void onCompleted(Object obj) {
                            String parseLoginError = LoginErrorParser.parseLoginError(SignUpEducationFragment.this.getContext(), (JSONObject) obj);
                            if (StringExtensions.isNullOrEmpty(parseLoginError)) {
                                SignUpEducationFragment.this.getActivity().setResult(-1);
                                SignUpEducationFragment.this.getActivity().finish();
                                return;
                            }
                            Util.showSnackBar(SignUpEducationFragment.this.rootView, parseLoginError);
                            dialogInterface.dismiss();
                            SignUpEducationFragment.this.showRetryDialog();
                            AccountProvider.logCrashlyticEvent(SignUpEducationFragment.this.getContext(), new Exception(parseLoginError), "reg_edu_login_parse", parseLoginError, false);
                        }
                    });
                }
            });
            builder.setNegativeButton(R.string.registration_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SignUpEducationFragment.this.startActivity(new Intent(SignUpEducationFragment.this.getActivity(), LoginActivity.class));
                    SignUpEducationFragment.this.getActivity().finish();
                }
            });
            if (isAdded() && getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        builder.create().show();
                    }
                });
            }
        }
    }

    public void onResume() {
        super.onResume();
        trackOmniture();
    }

    private void setViews() {
        UserEducation userEducation = this.mEducationProfile;
        if (userEducation != null) {
            if (userEducation.getSchoolState() == null || this.mEducationProfile.getSchoolStateId() == null || !this.mEducationProfile.getSchoolStateId().equals("00")) {
                this.btnSchoolCountry.setVisibility(8);
            } else {
                this.btnSchoolCountry.setVisibility(0);
            }
            if (this.btnSchoolCountry.getVisibility() == 0 && StringExtensions.isNotEmpty(this.mEducationProfile.getSchoolCountry())) {
                this.btnSchool.setVisibility(0);
            } else if (!StringExtensions.isNotEmpty(this.mEducationProfile.getSchoolState()) || !StringExtensions.isNotEmpty(this.mEducationProfile.getSchoolCountry())) {
                this.btnSchool.setVisibility(8);
            } else {
                this.btnSchool.setVisibility(0);
            }
            setLabels(this.btnSchoolState, R.id.textViewLabel, R.string.signup_schoolstate);
            setLabels(this.btnSchoolCountry, R.id.textViewLabel, R.string.signup_schoolcountry);
            setLabels(this.btnSchool, R.id.textViewLabel, R.string.signup_school);
            setLabels(this.btnBirthYear, R.id.textViewLabel, R.string.signup_birthyear);
            setLabels(this.btnGraduationYear, R.id.textViewLabel, R.string.signup_anticipatedgraduation);
            UserProfile userProfile = this.mUserProfile;
            if (!(userProfile == null || userProfile.getProfessionProfile() == null || !UserProfile.PHYSICIAN_ID.equalsIgnoreCase(this.mUserProfile.getProfessionProfile().getProfessionId()))) {
                setLabels(this.btnGraduationYear, R.id.textViewLabel, R.string.signup_graduation);
            }
            setValue(this.btnSchoolState, R.id.textViewValue, this.mEducationProfile.getSchoolState());
            setValue(this.btnSchoolCountry, R.id.textViewValue, this.mEducationProfile.getSchoolCountry());
            setValue(this.btnSchool, R.id.textViewValue, this.mEducationProfile.getSchool());
            setValue(this.btnBirthYear, R.id.textViewValue, this.mEducationProfile.getBirthYear());
            setValue(this.btnGraduationYear, R.id.textViewValue, this.mEducationProfile.getGraduationYear());
            attachClickListener(this.btnSchoolState, ListTypes.school_state);
            attachClickListener(this.btnSchoolCountry, ListTypes.school_country);
            attachClickListener(this.btnSchool, ListTypes.school);
            attachClickListener(this.btnBirthYear, ListTypes.birth_year);
            attachClickListener(this.btnGraduationYear, ListTypes.graduation_year);
            isValid();
        }
        UserProfile userProfile2 = this.mUserProfile;
        if (userProfile2 == null || userProfile2.getProfessionProfile() == null || this.mUserProfile.getProfessionProfile().getProfessionId() == null || this.mUserProfile.getProfessionProfile().getProfessionId().equals(UserProfile.MEDICAL_LABORATORY_ID)) {
            this.licenseLayout.setVisibility(8);
            this.licenseViewModel.setBypassValidation(true);
            return;
        }
        if (this.licenseViewModel.getFieldLabel().isEmpty()) {
            this.licenseTitle.setVisibility(8);
            this.licenseWrapper.setHintEnabled(true);
            this.licenseWrapper.setHint(this.licenseViewModel.getFieldHint());
        } else {
            this.licenseTitle.setVisibility(0);
            this.licenseTitle.setText(this.licenseViewModel.getFieldLabel());
            this.licenseWrapper.setHintEnabled(false);
            this.licenseNumber.setHint(this.licenseViewModel.getFieldHint());
        }
        if (this.licenseViewModel.getShowToggle()) {
            this.noLicense.setVisibility(0);
        } else {
            this.noLicense.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public boolean isValid() {
        if (!isLabelComplete(this.btnSchoolState) || !isLabelComplete(this.btnSchoolCountry) || !isLabelComplete(this.btnSchool) || !isLabelComplete(this.btnBirthYear) || !isLabelComplete(this.btnGraduationYear) || (!this.licenseViewModel.isRegisterButtonActive() && !this.licenseViewModel.getBypassValidation())) {
            this.btnDone.setAlpha(0.5f);
            this.btnDone.setEnabled(false);
            return false;
        }
        this.btnDone.setAlpha(1.0f);
        this.btnDone.setEnabled(true);
        return true;
    }

    private boolean isLabelComplete(View view) {
        if (view.getVisibility() == 0) {
            return !((TextView) view.findViewById(R.id.textViewValue)).getText().toString().equalsIgnoreCase("") && !((TextView) view.findViewById(R.id.textViewValue)).getText().toString().equalsIgnoreCase(getResources().getString(R.string.sign_up_profile_default));
        }
        return true;
    }

    private void attachClickListener(View view, final ListTypes listTypes) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Calendar instance = Calendar.getInstance();
                Map linkedHashMap = new LinkedHashMap();
                int i = AnonymousClass12.$SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes[listTypes.ordinal()];
                if (i == 1) {
                    linkedHashMap = SignUpEducationFragment.this.mRegistrationDataProvider.getStates();
                } else if (i == 2) {
                    linkedHashMap = SignUpEducationFragment.this.mRegistrationDataProvider.getCountries("en_us");
                } else if (i == 3) {
                    if (!StringExtensions.isNotEmpty(SignUpEducationFragment.this.mEducationProfile.getSchoolCountryId())) {
                        SignUpEducationFragment.this.mEducationProfile.setSchoolCountryId("us");
                    }
                    linkedHashMap = !SignUpEducationFragment.this.mEducationProfile.getSchoolStateId().equals("00") ? SignUpEducationFragment.this.mRegistrationDataProvider.getSchools(SignUpEducationFragment.this.mEducationProfile.getSchoolStateId()) : SignUpEducationFragment.this.mRegistrationDataProvider.getNonUSASchools(SignUpEducationFragment.this.mEducationProfile.getSchoolCountryId());
                } else if (i == 4) {
                    for (int i2 = instance.get(1) - 20; i2 > 1900; i2 += -1) {
                        linkedHashMap.put(i2 + "", i2 + "");
                    }
                } else if (i == 5) {
                    String professionId = SignUpEducationFragment.this.mUserProfile.getProfessionProfile().getProfessionId();
                    if (professionId == null || !professionId.equals(UserProfile.PHYSICIAN_ID)) {
                        if (professionId == null || !professionId.equals(UserProfile.MEDICAL_LABORATORY_ID)) {
                            for (int i3 = instance.get(1); i3 >= instance.get(1) - 20; i3 += -1) {
                                linkedHashMap.put(i3 + "", i3 + "");
                            }
                        } else {
                            for (int i4 = instance.get(1); i4 < instance.get(1) + 20; i4++) {
                                linkedHashMap.put(i4 + "", i4 + "");
                            }
                        }
                    } else if (StringExtensions.isNotEmpty(SignUpEducationFragment.this.mEducationProfile.getBirthYear())) {
                        int parseInt = Integer.parseInt(SignUpEducationFragment.this.mEducationProfile.getBirthYear()) + 20;
                        for (int i5 = instance.get(1); i5 >= parseInt; i5 += -1) {
                            linkedHashMap.put(i5 + "", i5 + "");
                        }
                    } else {
                        for (int i6 = instance.get(1); i6 > 1920; i6 += -1) {
                            linkedHashMap.put(i6 + "", i6 + "");
                        }
                    }
                }
                if (linkedHashMap.size() > 0) {
                    ListSelectionFragment listSelectionFragment = new ListSelectionFragment();
                    listSelectionFragment.setListData(linkedHashMap);
                    listSelectionFragment.setListType(listTypes);
                    ((RegistrationActivity) SignUpEducationFragment.this.getActivity()).loadNextFragment(listSelectionFragment, (String) null);
                }
            }
        });
    }

    /* renamed from: com.webmd.wbmdproffesionalauthentication.fragments.SignUpEducationFragment$12  reason: invalid class name */
    static /* synthetic */ class AnonymousClass12 {
        static final /* synthetic */ int[] $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes[] r0 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes = r0
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.school_state     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.school_country     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.school     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.birth_year     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x003e }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.graduation_year     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdproffesionalauthentication.fragments.SignUpEducationFragment.AnonymousClass12.<clinit>():void");
        }
    }

    private void setLabels(View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        if (textView != null) {
            textView.setText(getResources().getString(i2));
        }
    }

    private void setValue(View view, int i, String str) {
        if (!StringExtensions.isNotEmpty(str)) {
            str = getResources().getString(R.string.sign_up_profile_default);
        }
        TextView textView = (TextView) view.findViewById(i);
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void setTitle() {
        if (getActivity() != null && (getActivity() instanceof RegistrationActivity)) {
            if (UserProfile.getInstance().getSteps() == 0) {
                ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up_plain));
                return;
            }
            ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up, 3, Integer.valueOf(UserProfile.getInstance().getSteps())));
        }
    }

    public boolean isRegisterInProgress() {
        return this.isRegisterInProgress;
    }

    public void sendRegistrationOmniture(boolean z) {
        WBMDOmnitureManager.sendModuleAction(new WBMDOmnitureModule(z ? "reg-success" : "reg-error", ProfessionalOmnitureData.appendAppPrefix(getContext(), "f-", true), WBMDOmnitureManager.shared.getLastSentPage()));
    }

    public void setLicenseViewModel(LicenseViewModel licenseViewModel2) {
        this.licenseViewModel = licenseViewModel2;
    }
}

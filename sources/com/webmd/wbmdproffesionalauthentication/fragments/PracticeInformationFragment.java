package com.webmd.wbmdproffesionalauthentication.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.material.textfield.TextInputLayout;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.activities.LoginActivity;
import com.webmd.wbmdproffesionalauthentication.activities.RegistrationActivity;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.omniture.OmnitureManager;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider;
import com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes;
import com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.SpecialCountriesRegistrationData;
import com.webmd.wbmdproffesionalauthentication.utilities.RegValidator;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter;
import java.util.Map;
import org.json.JSONObject;

public class PracticeInformationFragment extends Fragment {
    TextInputLayout cityLayout;
    SpecialCountriesRegistrationData countryData;
    Button doneButton;
    EditText extension;
    /* access modifiers changed from: private */
    public boolean isCityValid = false;
    private boolean isLicenceValid = false;
    /* access modifiers changed from: private */
    public boolean isNoLicense = false;
    /* access modifiers changed from: private */
    public boolean isPhoneValid = false;
    TextView licenseHeader;
    EditText licenseId;
    RegistrationProvider mRegistrationDataProvider;
    UserProfile mUserProfile;
    AppCompatCheckBox noLicense;
    EditText phoneCode;
    TextInputLayout phoneLayout;
    ProgressBar regProgress;
    View rootView;
    String userCountry;
    RegValidator validator;
    EditText workCity;
    EditText workPhone;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.rootView = layoutInflater.inflate(R.layout.fragment_practice_information, viewGroup, false);
        this.countryData = new SpecialCountriesRegistrationData();
        this.mRegistrationDataProvider = RegistrationProvider.getInstance(getContext());
        this.mUserProfile = UserProfile.getInstance();
        this.validator = new RegValidator();
        this.userCountry = this.mUserProfile.getProfessionProfile().getCountry();
        setUpViews();
        setTitle();
        fillViews();
        setUpListeners();
        return this.rootView;
    }

    private void setUpViews() {
        this.phoneCode = (EditText) this.rootView.findViewById(R.id.practice_info_phone_code);
        this.workPhone = (EditText) this.rootView.findViewById(R.id.practice_info_phone_number);
        this.extension = (EditText) this.rootView.findViewById(R.id.practice_info_extension);
        this.workCity = (EditText) this.rootView.findViewById(R.id.practice_info_city);
        this.licenseHeader = (TextView) this.rootView.findViewById(R.id.practice_info_license_header);
        this.licenseId = (EditText) this.rootView.findViewById(R.id.practice_info_license_number);
        this.noLicense = (AppCompatCheckBox) this.rootView.findViewById(R.id.practice_info_no_license_id);
        this.doneButton = (Button) this.rootView.findViewById(R.id.practice_info_done_button);
        this.regProgress = (ProgressBar) this.rootView.findViewById(R.id.practice_info_progress);
        this.phoneLayout = (TextInputLayout) this.rootView.findViewById(R.id.practice_info_phone_layout);
        this.cityLayout = (TextInputLayout) this.rootView.findViewById(R.id.practice_info_city_layout);
    }

    private void fillViews() {
        String country = UserProfile.getInstance().getProfessionProfile().getCountry();
        this.phoneCode.setText(this.countryData.getCountryData(country, CountryDataTypes.phoneCode));
        this.licenseHeader.setText(this.countryData.getCountryData(country, CountryDataTypes.licenseName));
        this.licenseId.setHint(this.countryData.getCountryData(country, CountryDataTypes.licenseHint));
        this.noLicense.setText(getString(R.string.practice_info_no_license, this.countryData.getCountryData(country, CountryDataTypes.licenseName)));
        this.doneButton.setAlpha(0.65f);
        this.doneButton.setEnabled(false);
        if (country.equals("Mexico") || country.equals("Brazil")) {
            this.workPhone.setInputType(3);
        } else {
            this.workPhone.setInputType(2);
        }
    }

    private void setUpListeners() {
        this.noLicense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    PracticeInformationFragment.this.doneButton.setEnabled(true);
                    PracticeInformationFragment.this.doneButton.setAlpha(1.0f);
                    boolean unused = PracticeInformationFragment.this.isNoLicense = true;
                    return;
                }
                PracticeInformationFragment.this.doneButton.setEnabled(false);
                PracticeInformationFragment.this.doneButton.setAlpha(0.65f);
                boolean unused2 = PracticeInformationFragment.this.isNoLicense = false;
            }
        });
        this.doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = PracticeInformationFragment.this.isNoLicense;
            }
        });
        addPhoneNumberListener();
        addCityListener();
        addLicenseListener();
    }

    private void setTitle() {
        if (UserProfile.getInstance().getSteps() == 0) {
            ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up_plain));
            return;
        }
        ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up, 4, Integer.valueOf(UserProfile.getInstance().getSteps())));
    }

    /* access modifiers changed from: private */
    public void showRetryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle((CharSequence) getString(R.string.signup_login_failed));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, int i) {
                AccountProvider.signInUser(PracticeInformationFragment.this.getContext(), PracticeInformationFragment.this.mUserProfile.getBasicProfile().getEmailAddress(), PracticeInformationFragment.this.mUserProfile.getBasicProfile().getPassword(), new ICallbackEvent<Object, Exception>() {
                    public void onError(Exception exc) {
                        Util.showSnackBar(PracticeInformationFragment.this.rootView, VolleyErrorConverter.exceptionToMessage(PracticeInformationFragment.this.getContext(), exc));
                        dialogInterface.dismiss();
                        PracticeInformationFragment.this.showRetryDialog();
                    }

                    public void onCompleted(Object obj) {
                        String parseLoginError = LoginErrorParser.parseLoginError(PracticeInformationFragment.this.getContext(), (JSONObject) obj);
                        if (StringExtensions.isNullOrEmpty(parseLoginError)) {
                            PracticeInformationFragment.this.getActivity().setResult(-1);
                            PracticeInformationFragment.this.getActivity().finish();
                            return;
                        }
                        Util.showSnackBar(PracticeInformationFragment.this.rootView, parseLoginError);
                        dialogInterface.dismiss();
                        PracticeInformationFragment.this.showRetryDialog();
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.registration_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PracticeInformationFragment.this.startActivity(new Intent(PracticeInformationFragment.this.getActivity(), LoginActivity.class));
                PracticeInformationFragment.this.getActivity().finish();
            }
        });
        builder.create().show();
    }

    private void registerUser() {
        if (Util.isOnline(getActivity())) {
            Util.hideKeyboard(getActivity());
            this.doneButton.setEnabled(false);
            this.doneButton.setClickable(false);
            this.regProgress.setVisibility(0);
            this.mRegistrationDataProvider.registerUser(getContext(), this.mUserProfile, new ICallbackEvent<Boolean, String>() {
                public void onError(String str) {
                    if (str != null && StringExtensions.isNotEmpty(str)) {
                        if (str.equalsIgnoreCase("OAUTH_FAILED")) {
                            PracticeInformationFragment.this.showRetryDialog();
                        } else if (PracticeInformationFragment.this.isAdded() && PracticeInformationFragment.this.getActivity() != null) {
                            Util.showSnackBar(PracticeInformationFragment.this.rootView, str);
                        }
                    }
                    PracticeInformationFragment.this.doneButton.setEnabled(true);
                    PracticeInformationFragment.this.doneButton.setClickable(true);
                    PracticeInformationFragment.this.regProgress.setVisibility(8);
                }

                public void onCompleted(Boolean bool) {
                    if (bool.booleanValue()) {
                        OmnitureManager.get().trackModuleAbsolute(PracticeInformationFragment.this.getContext(), "other", "reg-success", "f-msp", (Map<String, Object>) null);
                        PracticeInformationFragment.this.getActivity().setResult(-1);
                        PracticeInformationFragment.this.getActivity().finish();
                        return;
                    }
                    Util.showSnackBar(PracticeInformationFragment.this.rootView, PracticeInformationFragment.this.getString(R.string.error_register_user));
                }
            });
            OmnitureManager.get().trackModuleAbsolute(getContext(), "other", "reg-new3", "msp", (Map<String, Object>) null);
            return;
        }
        Util.showSnackBar(this.rootView, getString(R.string.error_connection_required));
    }

    private void addPhoneNumberListener() {
        this.workPhone.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String validatePhoneNumber = PracticeInformationFragment.this.validator.validatePhoneNumber(PracticeInformationFragment.this.mUserProfile.getProfessionProfile().getCountry(), charSequence.toString());
                if (!validatePhoneNumber.isEmpty()) {
                    boolean unused = PracticeInformationFragment.this.isPhoneValid = false;
                    PracticeInformationFragment.this.phoneLayout.setError(validatePhoneNumber);
                    return;
                }
                boolean unused2 = PracticeInformationFragment.this.isPhoneValid = true;
                PracticeInformationFragment.this.phoneLayout.setError("");
            }
        });
    }

    private void addCityListener() {
        this.workCity.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (StringExtensions.isNullOrEmpty(charSequence.toString())) {
                    boolean unused = PracticeInformationFragment.this.isCityValid = false;
                    PracticeInformationFragment.this.cityLayout.setError(PracticeInformationFragment.this.getString(R.string.practice_info_no_value_error));
                    return;
                }
                boolean unused2 = PracticeInformationFragment.this.isCityValid = true;
                PracticeInformationFragment.this.cityLayout.setError("");
            }
        });
    }

    private void addLicenseListener() {
        this.licenseId.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }
}

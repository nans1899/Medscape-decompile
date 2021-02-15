package com.webmd.wbmdproffesionalauthentication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.Scopes;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdomnituremanager.ProfessionalOmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.activities.RegistrationActivity;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.providers.RegistrationProvider;
import com.webmd.wbmdproffesionalauthentication.utilities.ListTypes;
import java.util.Hashtable;
import java.util.Map;

public class SignUpProfessionFragment extends Fragment {
    private Button mButtonContinue;
    private View mCountryView;
    private View mOccupationView;
    private View mProfessionView;
    /* access modifiers changed from: private */
    public RegistrationProvider mRegistrationProvider;
    private View mSpecialityView;
    private View mSubSpecialitiesView;
    UserProfile mUserProfile;
    UserProfession mUserProfileProfession;

    public SignUpProfessionFragment() {
        UserProfile instance = UserProfile.getInstance();
        this.mUserProfile = instance;
        this.mUserProfileProfession = instance.getProfessionProfile();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_signup_profession, viewGroup, false);
        this.mCountryView = inflate.findViewById(R.id.buttonCountry);
        this.mOccupationView = inflate.findViewById(R.id.buttonOccupation);
        this.mSpecialityView = inflate.findViewById(R.id.buttonSpeciality);
        this.mSubSpecialitiesView = inflate.findViewById(R.id.buttonSubSpeciality);
        this.mProfessionView = inflate.findViewById(R.id.button_profession);
        Button button = (Button) inflate.findViewById(R.id.buttonContinue);
        this.mButtonContinue = button;
        button.setAlpha(0.5f);
        this.mRegistrationProvider = RegistrationProvider.getInstance(getContext());
        setTitle();
        this.mButtonContinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SignUpProfessionFragment.this.isUserProfessionValid()) {
                    WBMDOmnitureManager.shared.setModule("reg-new1");
                    WBMDOmnitureManager.shared.setLink(ProfessionalOmnitureData.getAppNamePrefix(SignUpProfessionFragment.this.getContext()));
                    ((RegistrationActivity) SignUpProfessionFragment.this.getActivity()).loadNextFragment(new SignUpProfileFragment(), Scopes.PROFILE);
                }
            }
        });
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initializeViews();
    }

    public void onResume() {
        super.onResume();
        WBMDOmnitureManager.sendPageViewWithSharedModule(ProfessionalOmnitureData.buildPage("registration", "view", (String) null, ProfessionalOmnitureData.appendAppPrefix(getActivity(), "screen-1-", true)), (Map<String, String>) null);
        initializeViews();
    }

    private void initializeViews() {
        this.mOccupationView.setVisibility(8);
        this.mSpecialityView.setVisibility(8);
        this.mSubSpecialitiesView.setVisibility(8);
        setLabels(this.mCountryView, R.id.textViewLabel, R.string.sign_up_profile_label_country);
        setLabels(this.mProfessionView, R.id.textViewLabel, R.string.sign_up_profile_label_proffesion);
        setLabels(this.mSpecialityView, R.id.textViewLabel, R.string.sign_up_profile_label_primary_specialty);
        setLabels(this.mSubSpecialitiesView, R.id.textViewLabel, R.string.sign_up_profile_label_sub_spec);
        setLabels(this.mOccupationView, R.id.textViewLabel, R.string.sign_up_profile_label_occupation);
        attachClickListener(this.mCountryView, ListTypes.countries);
        attachClickListener(this.mProfessionView, ListTypes.professions);
        attachClickListener(this.mSpecialityView, ListTypes.specialty);
        attachClickListener(this.mSubSpecialitiesView, ListTypes.subspecialty);
        attachClickListener(this.mOccupationView, ListTypes.occupation);
        this.mButtonContinue.setEnabled(isUserProfessionValid());
        if (this.mButtonContinue.isEnabled()) {
            this.mButtonContinue.setAlpha(1.0f);
        } else {
            this.mButtonContinue.setAlpha(0.5f);
        }
        if (!StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getCountry())) {
            setValue(this.mCountryView, R.id.textViewValue, this.mUserProfileProfession.getCountry());
        } else {
            setValue(this.mCountryView, R.id.textViewValue, getString(R.string.sign_up_profile_country_default));
            this.mUserProfileProfession.setCountry(getString(R.string.sign_up_profile_country_default));
        }
        if (!StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getProfession())) {
            setValue(this.mProfessionView, R.id.textViewValue, this.mUserProfileProfession.getProfession());
            setStateForProfession();
        } else {
            setValue(this.mProfessionView, R.id.textViewValue, getString(R.string.sign_up_profile_default));
        }
        if (!StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getSpeciality())) {
            this.mSpecialityView.setVisibility(0);
            setValue(this.mSpecialityView, R.id.textViewValue, this.mUserProfileProfession.getSpeciality());
            setStateForSpeciality();
        } else {
            setValue(this.mSpecialityView, R.id.textViewValue, getString(R.string.sign_up_profile_default));
        }
        if (!StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getSubSpecialty())) {
            this.mSubSpecialitiesView.setVisibility(0);
            setValue(this.mSubSpecialitiesView, R.id.textViewValue, this.mUserProfileProfession.getSubSpecialty());
        } else {
            setValue(this.mSubSpecialitiesView, R.id.textViewValue, getString(R.string.sign_up_profile_default));
        }
        if (!StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getOccupation())) {
            this.mOccupationView.setVisibility(0);
            setValue(this.mOccupationView, R.id.textViewValue, this.mUserProfileProfession.getOccupation());
        } else {
            setValue(this.mOccupationView, R.id.textViewValue, getString(R.string.sign_up_profile_default));
        }
        setLabels(this.mSpecialityView, R.id.textViewLabel, R.string.sign_up_profile_label_primary_specialty);
        if (!StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getProfessionId()) && this.mUserProfileProfession.getProfessionId().equalsIgnoreCase(UserProfile.MEDICAL_LABORATORY_ID)) {
            setLabels(this.mSpecialityView, R.id.textViewLabel, R.string.sign_up_profile_anticipatedspeciality);
        }
    }

    private void setStateForSpeciality() {
        Map<String, String> subSpeciality = this.mRegistrationProvider.getSubSpeciality(this.mUserProfileProfession.getProfessionId(), this.mUserProfileProfession.getSpeciality());
        if (subSpeciality == null || subSpeciality.size() <= 0) {
            this.mSubSpecialitiesView.setVisibility(8);
        } else {
            this.mSubSpecialitiesView.setVisibility(0);
        }
    }

    private void setStateForProfession() {
        Map<String, String> speciality = this.mRegistrationProvider.getSpeciality(this.mUserProfileProfession.getProfessionId());
        if (speciality == null || speciality.size() <= 0) {
            this.mSpecialityView.setVisibility(8);
            this.mSubSpecialitiesView.setVisibility(8);
        } else {
            this.mSpecialityView.setVisibility(0);
        }
        Map<String, String> occupations = this.mRegistrationProvider.getOccupations(this.mUserProfileProfession.getProfessionId());
        if (occupations == null || occupations.size() <= 0) {
            this.mOccupationView.setVisibility(8);
        } else {
            this.mOccupationView.setVisibility(0);
        }
    }

    private void attachClickListener(View view, final ListTypes listTypes) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Map hashtable = new Hashtable();
                int i = AnonymousClass3.$SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes[listTypes.ordinal()];
                String str = "";
                if (i == 1) {
                    hashtable = SignUpProfessionFragment.this.mRegistrationProvider.getCountries("en_us");
                    if (SignUpProfessionFragment.this.mUserProfile.getProfessionProfile() != null) {
                        str = SignUpProfessionFragment.this.mUserProfile.getProfessionProfile().getCountry();
                    }
                } else if (i == 2) {
                    hashtable = SignUpProfessionFragment.this.mRegistrationProvider.getAvailableProfessions(SignUpProfessionFragment.this.mUserProfileProfession.getCountryCode());
                    if (SignUpProfessionFragment.this.mUserProfile.getProfessionProfile() != null) {
                        str = SignUpProfessionFragment.this.mUserProfile.getProfessionProfile().getProfession();
                    }
                } else if (i == 3) {
                    hashtable = SignUpProfessionFragment.this.mRegistrationProvider.getSpeciality(SignUpProfessionFragment.this.mUserProfileProfession.getProfessionId());
                    str = SignUpProfessionFragment.this.mUserProfile.getProfessionProfile().getSpeciality();
                } else if (i == 4) {
                    hashtable = SignUpProfessionFragment.this.mRegistrationProvider.getSubSpeciality(SignUpProfessionFragment.this.mUserProfileProfession.getProfessionId(), SignUpProfessionFragment.this.mUserProfileProfession.getSpeciality());
                    str = SignUpProfessionFragment.this.mUserProfile.getProfessionProfile().getSubSpecialty();
                } else if (i == 5) {
                    hashtable = SignUpProfessionFragment.this.mRegistrationProvider.getOccupations(SignUpProfessionFragment.this.mUserProfileProfession.getProfessionId());
                    str = SignUpProfessionFragment.this.mUserProfile.getProfessionProfile().getOccupation();
                }
                if (hashtable.size() > 0) {
                    ListSelectionFragment listSelectionFragment = new ListSelectionFragment();
                    listSelectionFragment.setListData(hashtable);
                    if (str != null) {
                        listSelectionFragment.setSelected(str);
                    }
                    listSelectionFragment.setListType(listTypes);
                    ((RegistrationActivity) SignUpProfessionFragment.this.getActivity()).loadNextFragment(listSelectionFragment, (String) null);
                }
            }
        });
    }

    /* renamed from: com.webmd.wbmdproffesionalauthentication.fragments.SignUpProfessionFragment$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
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
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.countries     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.professions     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.specialty     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.subspecialty     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x003e }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.occupation     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdproffesionalauthentication.fragments.SignUpProfessionFragment.AnonymousClass3.<clinit>():void");
        }
    }

    private void setValue(View view, int i, String str) {
        if (str == null) {
            str = getResources().getString(R.string.sign_up_profile_default);
        }
        TextView textView = (TextView) view.findViewById(i);
        if (textView != null) {
            textView.setText(str);
        }
    }

    private void setLabels(View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        if (textView != null) {
            textView.setText(getResources().getString(i2));
        }
    }

    public boolean isUserProfessionValid() {
        boolean z = false;
        if (!StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getCountry()) && !StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getProfession())) {
            Map<String, String> speciality = this.mRegistrationProvider.getSpeciality(this.mUserProfileProfession.getProfessionId());
            if (speciality != null && speciality.size() > 0) {
                if (StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getSpeciality())) {
                    return false;
                }
                Map<String, String> subSpeciality = this.mRegistrationProvider.getSubSpeciality(this.mUserProfileProfession.getProfessionId(), this.mUserProfileProfession.getSpeciality());
                if (subSpeciality != null && subSpeciality.size() > 0 && StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getSubSpecialty())) {
                    return false;
                }
            }
            Map<String, String> occupations = this.mRegistrationProvider.getOccupations(this.mUserProfileProfession.getProfessionId());
            z = true;
            if (occupations != null && occupations.size() > 0) {
                return !StringExtensions.isNullOrEmpty(this.mUserProfileProfession.getOccupation());
            }
        }
        return z;
    }

    public void setTitle() {
        if (getActivity() != null && (getActivity() instanceof RegistrationActivity)) {
            if (UserProfile.getInstance().getSteps() == 0) {
                ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up_plain));
                return;
            }
            ((RegistrationActivity) getActivity()).setCustomTitle(getString(R.string.sign_up, 1, Integer.valueOf(UserProfile.getInstance().getSteps())));
        }
    }
}

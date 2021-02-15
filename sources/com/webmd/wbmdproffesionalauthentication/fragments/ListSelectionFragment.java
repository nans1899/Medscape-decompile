package com.webmd.wbmdproffesionalauthentication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.activities.RegistrationActivity;
import com.webmd.wbmdproffesionalauthentication.adapters.SelectableListAdapter;
import com.webmd.wbmdproffesionalauthentication.interfaces.IListItemSelectionListener;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.utilities.ListTypes;
import com.webmd.wbmdproffesionalauthentication.viewmodels.NewsletterViewModel;
import java.util.Map;

public class ListSelectionFragment extends Fragment implements IListItemSelectionListener {
    private Map<String, String> mListItems;
    private ListTypes mListType;
    private String mSelectedItem = "";
    private RecyclerView mSelectionListView;
    private NewsletterViewModel viewModel;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_list_selection, viewGroup, false);
        setTitle();
        this.mSelectionListView = (RecyclerView) inflate.findViewById(R.id.listViewSelection);
        SelectableListAdapter selectableListAdapter = new SelectableListAdapter(this.mListItems, this.mSelectedItem, this.mListType, this);
        this.mSelectionListView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mSelectionListView.setAdapter(selectableListAdapter);
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.viewModel = (NewsletterViewModel) ViewModelProviders.of(getActivity()).get(NewsletterViewModel.class);
    }

    public void setListData(Map<String, String> map) {
        this.mListItems = map;
    }

    public void setListType(ListTypes listTypes) {
        this.mListType = listTypes;
    }

    public void setSelected(String str) {
        this.mSelectedItem = str;
    }

    /* renamed from: com.webmd.wbmdproffesionalauthentication.fragments.ListSelectionFragment$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
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
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.school_state     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.school_country     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.school     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x006c }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.birth_year     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.webmd.wbmdproffesionalauthentication.utilities.ListTypes r1 = com.webmd.wbmdproffesionalauthentication.utilities.ListTypes.graduation_year     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdproffesionalauthentication.fragments.ListSelectionFragment.AnonymousClass1.<clinit>():void");
        }
    }

    public void onItemSelected(String str, String str2, ListTypes listTypes) {
        switch (AnonymousClass1.$SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes[listTypes.ordinal()]) {
            case 1:
                UserProfile.getInstance().getProfessionProfile().setCountry(str);
                UserProfile.getInstance().getProfessionProfile().setCountryCode(str2);
                this.viewModel.setCountryCode(str2);
                break;
            case 2:
                UserProfile.getInstance().getProfessionProfile().setProfession(str);
                UserProfile.getInstance().getProfessionProfile().setProfessionId(str2);
                break;
            case 3:
                UserProfile.getInstance().getProfessionProfile().setSpeciality(str);
                UserProfile.getInstance().getProfessionProfile().setSpecialityId(str2);
                break;
            case 4:
                UserProfile.getInstance().getProfessionProfile().setSubSpecialty(str);
                UserProfile.getInstance().getProfessionProfile().setSubSpecialityId(str2);
                break;
            case 5:
                UserProfile.getInstance().getProfessionProfile().setOccupation(str);
                UserProfile.getInstance().getProfessionProfile().setOccupationId(str2);
                break;
            case 6:
                UserProfile.getInstance().getEducationProfile().setSchoolState(str);
                UserProfile.getInstance().getEducationProfile().setSchoolStateId(str2);
                break;
            case 7:
                UserProfile.getInstance().getEducationProfile().setSchoolCountry(str);
                UserProfile.getInstance().getEducationProfile().setSchoolCountryId(str2);
                break;
            case 8:
                UserProfile.getInstance().getEducationProfile().setSchool(str);
                UserProfile.getInstance().getEducationProfile().setSchoolId(str2);
                break;
            case 9:
                UserProfile.getInstance().getEducationProfile().setBirthYear(str);
                break;
            case 10:
                UserProfile.getInstance().getEducationProfile().setGraduationYear(str);
                break;
        }
        getActivity().onBackPressed();
    }

    private void setTitle() {
        String str;
        if (this.mListType != null) {
            switch (AnonymousClass1.$SwitchMap$com$webmd$wbmdproffesionalauthentication$utilities$ListTypes[this.mListType.ordinal()]) {
                case 1:
                    str = getString(R.string.toolbar_title_country);
                    break;
                case 2:
                    str = getString(R.string.toolbar_title_professions);
                    break;
                case 3:
                    str = getString(R.string.toolbar_title_specialty);
                    break;
                case 4:
                    str = getString(R.string.toolbar_title_subspecialties);
                    break;
                case 5:
                    str = getString(R.string.toolbar_title_ocuppations);
                    break;
                case 6:
                    str = getString(R.string.toolbar_title_school_state);
                    break;
                case 7:
                    str = getString(R.string.toolbar_title_school_country);
                    break;
                case 8:
                    str = getString(R.string.toolbar_title_school);
                    break;
                case 9:
                    str = getString(R.string.toolbar_title_year_of_birth);
                    break;
                case 10:
                    str = getString(R.string.toolbar_title_year_of_graduation);
                    break;
            }
        }
        str = "";
        ((RegistrationActivity) getActivity()).setCustomTitle(str);
    }
}

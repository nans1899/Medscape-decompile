package com.webmd.wbmdcmepulse.fragments.cmetracker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CmeAbimVerificationActivity;
import com.webmd.wbmdcmepulse.fragments.BaseFragment;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.providers.CMETrackerDataProvider;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;

public class CMETrackerHeaderFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public static final String TAG = CMETrackerHeaderFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public static ICallbackEvent<Boolean, String> mCallbck;
    private TextView mAbimMocButton;
    private OnFragmentInteractionListener mListener;
    /* access modifiers changed from: private */
    public String mMocLink;
    private View mRootView;

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public static CMETrackerHeaderFragment newInstance(ICallbackEvent<Boolean, String> iCallbackEvent, UserProfile userProfile) {
        CMETrackerHeaderFragment cMETrackerHeaderFragment = new CMETrackerHeaderFragment();
        mCallbck = iCallbackEvent;
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEY_USER_PROFILE, userProfile);
        cMETrackerHeaderFragment.mUserProfile = userProfile;
        cMETrackerHeaderFragment.setArguments(bundle);
        return cMETrackerHeaderFragment;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 6000) {
            return;
        }
        if (i2 != -1) {
            mCallbck.onCompleted(false);
        } else if (this.mAbimMocButton != null) {
            logInUser(new ICallbackEvent<Boolean, CMEPulseException>() {
                public void onError(CMEPulseException cMEPulseException) {
                    if (cMEPulseException != null) {
                        Trace.e(CMETrackerHeaderFragment.TAG, cMEPulseException.getMessage());
                    }
                }

                public void onCompleted(Boolean bool) {
                    CMETrackerHeaderFragment.this.setUpMocButton();
                    CMETrackerHeaderFragment.mCallbck.onCompleted(bool);
                }
            });
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.cme_fragment_cmetracker_header, viewGroup, false);
        this.mRootView = inflate;
        TextView textView = (TextView) inflate.findViewById(R.id.cmeTrackerHeaderCreditTextView);
        textView.setText(getArguments().getString(CMETrackerDataProvider.CME_CREDITS_KEY));
        try {
            if (this.mUserProfile != null) {
                String professionId = this.mUserProfile.getProfessionProfile().getProfessionId();
                if (professionId.equals(UserProfile.NURSE_ID) || professionId.equals(UserProfile.PHARMACIST_ID)) {
                    ((TextView) this.mRootView.findViewById(R.id.cme_credit_total_label)).setText("CE / Rx");
                    textView.setText(getArguments().getString(CMETrackerDataProvider.CE_CREDITS_KEY));
                }
            }
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
        }
        ((CustomFontTextView) this.mRootView.findViewById(R.id.mocTrackerHeaderCreditTextView)).setText(getArguments().getString(CMETrackerDataProvider.MOC_CREDITS_KEY));
        ((TextView) this.mRootView.findViewById(R.id.locTrackerHeaderCreditTextView)).setText(getArguments().getString(CMETrackerDataProvider.LOC_CREDITS_KEY));
        this.mAbimMocButton = (TextView) this.mRootView.findViewById(R.id.abim_id_button);
        setUpMocButton();
        View findViewById = this.mRootView.findViewById(R.id.cmeTrackerStateRequirementsButton);
        if (this.mUserProfile == null || this.mUserProfile.getProfessionProfile() == null || (!this.mUserProfile.getProfessionProfile().getProfessionId().equals(UserProfile.PHYSICIAN_ID) && !this.mUserProfile.getProfessionProfile().getProfessionId().equals(UserProfile.NURSE_ID) && !this.mUserProfile.getProfessionProfile().getProfessionId().equals(UserProfile.PHARMACIST_ID))) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
                /* JADX WARNING: Removed duplicated region for block: B:23:0x006b  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onClick(android.view.View r5) {
                    /*
                        r4 = this;
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r5 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        com.webmd.wbmdproffesionalauthentication.model.UserProfile r5 = r5.mUserProfile
                        com.webmd.wbmdproffesionalauthentication.model.UserProfession r5 = r5.getProfessionProfile()
                        java.lang.String r5 = r5.getProfessionId()
                        int r0 = r5.hashCode()
                        r1 = 56
                        r2 = 2
                        r3 = 1
                        if (r0 == r1) goto L_0x0033
                        r1 = 1567(0x61f, float:2.196E-42)
                        if (r0 == r1) goto L_0x0029
                        r1 = 1569(0x621, float:2.199E-42)
                        if (r0 == r1) goto L_0x001f
                        goto L_0x003d
                    L_0x001f:
                        java.lang.String r0 = "12"
                        boolean r5 = r5.equals(r0)
                        if (r5 == 0) goto L_0x003d
                        r5 = 1
                        goto L_0x003e
                    L_0x0029:
                        java.lang.String r0 = "10"
                        boolean r5 = r5.equals(r0)
                        if (r5 == 0) goto L_0x003d
                        r5 = 0
                        goto L_0x003e
                    L_0x0033:
                        java.lang.String r0 = "8"
                        boolean r5 = r5.equals(r0)
                        if (r5 == 0) goto L_0x003d
                        r5 = 2
                        goto L_0x003e
                    L_0x003d:
                        r5 = -1
                    L_0x003e:
                        java.lang.String r0 = "https://www%s.medscape.org/public/staterequirements"
                        if (r5 == 0) goto L_0x006b
                        if (r5 == r3) goto L_0x005e
                        if (r5 == r2) goto L_0x0051
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r5 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        android.content.Context r5 = r5.getContext()
                        java.lang.String r5 = com.webmd.wbmdcmepulse.models.utils.Utilities.generateEnvironment(r5, r0)
                        goto L_0x0075
                    L_0x0051:
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r5 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        android.content.Context r5 = r5.getContext()
                        java.lang.String r0 = "https://www%s.medscape.org/public/pharmcestaterequirements"
                        java.lang.String r5 = com.webmd.wbmdcmepulse.models.utils.Utilities.generateEnvironment(r5, r0)
                        goto L_0x0075
                    L_0x005e:
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r5 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        android.content.Context r5 = r5.getContext()
                        java.lang.String r0 = "https://www%s.medscape.org/public/nursecestaterequirements"
                        java.lang.String r5 = com.webmd.wbmdcmepulse.models.utils.Utilities.generateEnvironment(r5, r0)
                        goto L_0x0075
                    L_0x006b:
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r5 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        android.content.Context r5 = r5.getContext()
                        java.lang.String r5 = com.webmd.wbmdcmepulse.models.utils.Utilities.generateEnvironment(r5, r0)
                    L_0x0075:
                        android.content.Intent r0 = new android.content.Intent
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r1 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        android.content.Context r1 = r1.getContext()
                        java.lang.Class<com.webmd.wbmdcmepulse.activities.CmeWebActivity> r2 = com.webmd.wbmdcmepulse.activities.CmeWebActivity.class
                        r0.<init>(r1, r2)
                        java.lang.String r1 = "URL_KEY"
                        r0.putExtra(r1, r5)
                        java.lang.String r5 = "TITLE_KEY"
                        java.lang.String r1 = "State Requirements"
                        r0.putExtra(r5, r1)
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r5 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        r5.startActivity(r0)
                        com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment r5 = com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.this
                        androidx.fragment.app.FragmentActivity r5 = r5.getActivity()
                        int r0 = com.webmd.wbmdcmepulse.R.anim.fade_in
                        int r1 = com.webmd.wbmdcmepulse.R.anim.fade_out
                        r5.overridePendingTransition(r0, r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.AnonymousClass2.onClick(android.view.View):void");
                }
            });
        }
        return this.mRootView;
    }

    /* access modifiers changed from: private */
    public void setUpMocButton() {
        if (isEligibleForMoc()) {
            if (this.mAbimMocButton != null) {
                boolean z = getArguments().getBoolean("resubmitABIM");
                if (z) {
                    this.mAbimMocButton.setVisibility(0);
                    if (z) {
                        this.mAbimMocButton.setText(getString(R.string.resubmit_abim_id));
                    } else {
                        this.mAbimMocButton.setText(getString(R.string.submit_abim_id));
                    }
                } else if (hasAbimId()) {
                    this.mAbimMocButton.setVisibility(8);
                }
            }
            TextView textView = this.mAbimMocButton;
            if (textView != null && textView.getVisibility() == 0) {
                this.mAbimMocButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(CMETrackerHeaderFragment.this.getContext(), CmeAbimVerificationActivity.class);
                        intent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, CMETrackerHeaderFragment.this.mMocLink);
                        CMETrackerHeaderFragment.this.startActivityForResult(intent, Constants.REQUEST_CODE_MOC_ID);
                    }
                });
                return;
            }
            return;
        }
        View findViewById = this.mRootView.findViewById(R.id.abim_credit_total_layout);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        TextView textView2 = this.mAbimMocButton;
        if (textView2 != null) {
            textView2.setVisibility(8);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.getProfessionProfile();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean hasAbimId() {
        /*
            r2 = this;
            com.webmd.wbmdproffesionalauthentication.model.UserProfile r0 = r2.mUserProfile
            com.webmd.wbmdproffesionalauthentication.model.UserProfile r1 = r2.mUserProfile
            if (r1 == 0) goto L_0x0022
            com.webmd.wbmdproffesionalauthentication.model.UserProfession r0 = r0.getProfessionProfile()
            if (r0 == 0) goto L_0x0022
            androidx.fragment.app.FragmentActivity r1 = r2.getActivity()
            if (r1 == 0) goto L_0x0022
            androidx.fragment.app.FragmentActivity r1 = r2.getActivity()
            java.lang.String r0 = r0.getAbimId(r1)
            boolean r0 = com.webmd.wbmdcmepulse.models.utils.Extensions.isStringNullOrEmpty(r0)
            if (r0 != 0) goto L_0x0022
            r0 = 1
            goto L_0x0023
        L_0x0022:
            r0 = 0
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.fragments.cmetracker.CMETrackerHeaderFragment.hasAbimId():boolean");
    }

    private boolean isEligibleForMoc() {
        UserProfile userProfile = this.mUserProfile;
        if (userProfile == null) {
            return false;
        }
        return Utilities.isUserMocEligible(getActivity(), userProfile);
    }

    public void onResume() {
        super.onResume();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
            return;
        }
        throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }

    public void onDetach() {
        super.onDetach();
        this.mListener = null;
    }
}

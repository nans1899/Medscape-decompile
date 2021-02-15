package com.webmd.wbmdcmepulse.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import org.json.JSONObject;

public class BaseFragment extends Fragment {
    public UserProfile mUserProfile;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            Parcelable parcelable = getArguments().getParcelable(Constants.BUNDLE_KEY_USER_PROFILE);
            if ((parcelable instanceof UserProfile) && this.mUserProfile == null) {
                this.mUserProfile = (UserProfile) parcelable;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void logInUser(final ICallbackEvent<Boolean, CMEPulseException> iCallbackEvent) {
        if (AccountProvider.isUserLoggedIn(getContext())) {
            AccountProvider.signIn(getContext(), new ICallbackEvent<Object, Exception>() {
                public void onError(Exception exc) {
                }

                public void onCompleted(Object obj) {
                    String parseLoginError = LoginErrorParser.parseLoginError(BaseFragment.this.getActivity(), (JSONObject) obj);
                    if (StringExtensions.isNullOrEmpty(parseLoginError)) {
                        iCallbackEvent.onCompleted(true);
                    } else {
                        iCallbackEvent.onError(new CMEPulseException(parseLoginError));
                    }
                }
            });
        }
    }
}

package com.medscape.android.interfaces;

import com.medscape.android.auth.OAuthResponse;

public interface IOAuthCompleteListener {
    void onOAuthComplete(OAuthResponse oAuthResponse);
}

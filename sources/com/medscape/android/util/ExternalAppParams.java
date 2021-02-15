package com.medscape.android.util;

import android.net.Uri;

public class ExternalAppParams {
    private String mActionString;
    private String mButtonText;
    private String mMarketUri;
    private String mMessage;
    private String mPackagerId;
    private String mTargetUri;

    public String getMessage() {
        return this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = Uri.decode(str);
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public void setButtonText(String str) {
        this.mButtonText = Uri.decode(str);
    }

    public String getPackagerId() {
        return this.mPackagerId;
    }

    public void setPackagerId(String str) {
        this.mPackagerId = Uri.decode(str);
    }

    public String getTargetUri() {
        return this.mTargetUri;
    }

    public void setTargetUri(String str) {
        this.mTargetUri = Uri.decode(str);
    }

    public String getMarketUri() {
        return this.mMarketUri;
    }

    public void setMarketUri(String str) {
        this.mMarketUri = Uri.decode(str);
    }

    public String getActionString() {
        return this.mActionString;
    }

    public void setActionString(String str) {
        this.mActionString = Uri.decode(str);
    }
}

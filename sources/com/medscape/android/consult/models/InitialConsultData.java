package com.medscape.android.consult.models;

public class InitialConsultData {
    private String mAuthToken;
    private ConsultUser mUser;
    private ZimbraConfigResponse mZimbraConfigResponse;

    public void setConsultUser(ConsultUser consultUser) {
        this.mUser = consultUser;
    }

    public ConsultUser getConsultUser() {
        return this.mUser;
    }

    public void setZimbraConfigResponse(ZimbraConfigResponse zimbraConfigResponse) {
        this.mZimbraConfigResponse = zimbraConfigResponse;
    }

    public ZimbraConfigResponse getZimbraConfigResponse() {
        return this.mZimbraConfigResponse;
    }

    public void setAuthToken(String str) {
        this.mAuthToken = str;
    }

    public String getAuthToken() {
        return this.mAuthToken;
    }
}

package com.adobe.mobile;

public class VisitorID {
    public VisitorIDAuthenticationState authenticationState = VisitorIDAuthenticationState.VISITOR_ID_AUTHENTICATION_STATE_UNKNOWN;
    public String id;
    public final String idOrigin;
    public final String idType;

    public enum VisitorIDAuthenticationState {
        VISITOR_ID_AUTHENTICATION_STATE_UNKNOWN(0, "unknown"),
        VISITOR_ID_AUTHENTICATION_STATE_AUTHENTICATED(1, "authenticated"),
        VISITOR_ID_AUTHENTICATION_STATE_LOGGED_OUT(2, "logged_out");
        
        private final String textValue;
        private final int value;

        private VisitorIDAuthenticationState(int i, String str) {
            this.value = i;
            this.textValue = str;
        }

        /* access modifiers changed from: protected */
        public int getValue() {
            return this.value;
        }

        /* access modifiers changed from: protected */
        public String getTextValue() {
            return this.textValue;
        }
    }

    protected VisitorID(String str, String str2, String str3, VisitorIDAuthenticationState visitorIDAuthenticationState) throws IllegalStateException {
        String cleanContextDataKey = StaticMethods.cleanContextDataKey(str2);
        if (cleanContextDataKey == null || cleanContextDataKey.length() == 0) {
            throw new IllegalStateException("idType must not be null/empty");
        }
        this.idOrigin = str;
        this.idType = cleanContextDataKey;
        this.id = str3;
        this.authenticationState = visitorIDAuthenticationState;
    }

    /* access modifiers changed from: protected */
    public boolean isVisitorID(String str) {
        return this.idType.equals(str);
    }

    /* access modifiers changed from: protected */
    public String serializeIdentifierKeyForAnalyticsID() {
        return this.idType + ".id";
    }

    /* access modifiers changed from: protected */
    public String serializeAuthenticationKeyForAnalyticsID() {
        return this.idType + ".as";
    }
}

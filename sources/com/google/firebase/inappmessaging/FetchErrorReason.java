package com.google.firebase.inappmessaging;

import com.google.protobuf.Internal;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public enum FetchErrorReason implements Internal.EnumLite {
    UNSPECIFIED_FETCH_ERROR(0),
    SERVER_ERROR(1),
    CLIENT_ERROR(2),
    NETWORK_ERROR(3);
    
    public static final int CLIENT_ERROR_VALUE = 2;
    public static final int NETWORK_ERROR_VALUE = 3;
    public static final int SERVER_ERROR_VALUE = 1;
    public static final int UNSPECIFIED_FETCH_ERROR_VALUE = 0;
    private static final Internal.EnumLiteMap<FetchErrorReason> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new Internal.EnumLiteMap<FetchErrorReason>() {
            public FetchErrorReason findValueByNumber(int i) {
                return FetchErrorReason.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static FetchErrorReason valueOf(int i) {
        return forNumber(i);
    }

    public static FetchErrorReason forNumber(int i) {
        if (i == 0) {
            return UNSPECIFIED_FETCH_ERROR;
        }
        if (i == 1) {
            return SERVER_ERROR;
        }
        if (i == 2) {
            return CLIENT_ERROR;
        }
        if (i != 3) {
            return null;
        }
        return NETWORK_ERROR;
    }

    public static Internal.EnumLiteMap<FetchErrorReason> internalGetValueMap() {
        return internalValueMap;
    }

    private FetchErrorReason(int i) {
        this.value = i;
    }
}

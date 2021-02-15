package com.google.firebase.inappmessaging;

import com.google.protobuf.Internal;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public enum RenderErrorReason implements Internal.EnumLite {
    UNSPECIFIED_RENDER_ERROR(0),
    IMAGE_FETCH_ERROR(1),
    IMAGE_DISPLAY_ERROR(2),
    IMAGE_UNSUPPORTED_FORMAT(3);
    
    public static final int IMAGE_DISPLAY_ERROR_VALUE = 2;
    public static final int IMAGE_FETCH_ERROR_VALUE = 1;
    public static final int IMAGE_UNSUPPORTED_FORMAT_VALUE = 3;
    public static final int UNSPECIFIED_RENDER_ERROR_VALUE = 0;
    private static final Internal.EnumLiteMap<RenderErrorReason> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new Internal.EnumLiteMap<RenderErrorReason>() {
            public RenderErrorReason findValueByNumber(int i) {
                return RenderErrorReason.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static RenderErrorReason valueOf(int i) {
        return forNumber(i);
    }

    public static RenderErrorReason forNumber(int i) {
        if (i == 0) {
            return UNSPECIFIED_RENDER_ERROR;
        }
        if (i == 1) {
            return IMAGE_FETCH_ERROR;
        }
        if (i == 2) {
            return IMAGE_DISPLAY_ERROR;
        }
        if (i != 3) {
            return null;
        }
        return IMAGE_UNSUPPORTED_FORMAT;
    }

    public static Internal.EnumLiteMap<RenderErrorReason> internalGetValueMap() {
        return internalValueMap;
    }

    private RenderErrorReason(int i) {
        this.value = i;
    }
}

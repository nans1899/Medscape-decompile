package com.google.firebase.inappmessaging;

import com.google.protobuf.Internal;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public enum DismissType implements Internal.EnumLite {
    UNKNOWN_DISMISS_TYPE(0),
    AUTO(1),
    CLICK(2),
    SWIPE(3);
    
    public static final int AUTO_VALUE = 1;
    public static final int CLICK_VALUE = 2;
    public static final int SWIPE_VALUE = 3;
    public static final int UNKNOWN_DISMISS_TYPE_VALUE = 0;
    private static final Internal.EnumLiteMap<DismissType> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new Internal.EnumLiteMap<DismissType>() {
            public DismissType findValueByNumber(int i) {
                return DismissType.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static DismissType valueOf(int i) {
        return forNumber(i);
    }

    public static DismissType forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_DISMISS_TYPE;
        }
        if (i == 1) {
            return AUTO;
        }
        if (i == 2) {
            return CLICK;
        }
        if (i != 3) {
            return null;
        }
        return SWIPE;
    }

    public static Internal.EnumLiteMap<DismissType> internalGetValueMap() {
        return internalValueMap;
    }

    private DismissType(int i) {
        this.value = i;
    }
}

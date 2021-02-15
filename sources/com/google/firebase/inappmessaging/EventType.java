package com.google.firebase.inappmessaging;

import com.google.protobuf.Internal;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public enum EventType implements Internal.EnumLite {
    UNKNOWN_EVENT_TYPE(0),
    IMPRESSION_EVENT_TYPE(1),
    CLICK_EVENT_TYPE(2);
    
    public static final int CLICK_EVENT_TYPE_VALUE = 2;
    public static final int IMPRESSION_EVENT_TYPE_VALUE = 1;
    public static final int UNKNOWN_EVENT_TYPE_VALUE = 0;
    private static final Internal.EnumLiteMap<EventType> internalValueMap = null;
    private final int value;

    static {
        internalValueMap = new Internal.EnumLiteMap<EventType>() {
            public EventType findValueByNumber(int i) {
                return EventType.forNumber(i);
            }
        };
    }

    public final int getNumber() {
        return this.value;
    }

    @Deprecated
    public static EventType valueOf(int i) {
        return forNumber(i);
    }

    public static EventType forNumber(int i) {
        if (i == 0) {
            return UNKNOWN_EVENT_TYPE;
        }
        if (i == 1) {
            return IMPRESSION_EVENT_TYPE;
        }
        if (i != 2) {
            return null;
        }
        return CLICK_EVENT_TYPE;
    }

    public static Internal.EnumLiteMap<EventType> internalGetValueMap() {
        return internalValueMap;
    }

    private EventType(int i) {
        this.value = i;
    }
}

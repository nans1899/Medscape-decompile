package com.comscore.streaming;

public interface ContentDeliveryMode {
    public static final int[] ALLOWED_VALUES = {LINEAR, ON_DEMAND};
    public static final int LINEAR = 501;
    public static final int ON_DEMAND = 502;
}

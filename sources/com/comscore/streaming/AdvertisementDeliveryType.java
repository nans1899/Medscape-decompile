package com.comscore.streaming;

public interface AdvertisementDeliveryType {
    public static final int[] ALLOWED_VALUES = {NATIONAL, LOCAL, SYNDICATION};
    public static final int LOCAL = 1102;
    public static final int NATIONAL = 1101;
    public static final int SYNDICATION = 1103;
}

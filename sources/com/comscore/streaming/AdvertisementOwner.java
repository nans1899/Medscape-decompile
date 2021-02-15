package com.comscore.streaming;

public interface AdvertisementOwner {
    public static final int[] ALLOWED_VALUES = {DISTRIBUTOR, ORIGINATOR, MULTIPLE, NONE};
    public static final int DISTRIBUTOR = 1201;
    public static final int MULTIPLE = 1203;
    public static final int NONE = 1204;
    public static final int ORIGINATOR = 1202;
}

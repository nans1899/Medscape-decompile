package com.comscore.streaming;

public interface ContentFeedType {
    public static final int[] ALLOWED_VALUES = {EAST_HD, WEST_HD, EAST_SD, WEST_SD, 300};
    public static final int EAST_HD = 301;
    public static final int EAST_SD = 303;
    public static final int OTHER = 300;
    public static final int WEST_HD = 302;
    public static final int WEST_SD = 304;
}

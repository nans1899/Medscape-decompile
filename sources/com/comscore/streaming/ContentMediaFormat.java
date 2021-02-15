package com.comscore.streaming;

public interface ContentMediaFormat {
    public static final int[] ALLOWED_VALUES = {1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 1009, 1010, 1012, 1013, 1014, 1015};
    public static final int EXTRA_EPISODE = 1012;
    public static final int EXTRA_GENERIC = 1010;
    public static final int EXTRA_MOVIE = 1013;
    public static final int FULL_CONTENT_EPISODE = 1002;
    public static final int FULL_CONTENT_GENERIC = 1001;
    public static final int FULL_CONTENT_MOVIE = 1003;
    public static final int FULL_CONTENT_PODCAST = 1014;
    public static final int PARTIAL_CONTENT_EPISODE = 1005;
    public static final int PARTIAL_CONTENT_GENERIC = 1004;
    public static final int PARTIAL_CONTENT_MOVIE = 1006;
    public static final int PARTIAL_CONTENT_PODCAST = 1015;
    public static final int PREVIEW_EPISODE = 1008;
    public static final int PREVIEW_GENERIC = 1007;
    public static final int PREVIEW_MOVIE = 1009;
}

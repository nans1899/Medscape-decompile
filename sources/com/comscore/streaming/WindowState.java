package com.comscore.streaming;

public interface WindowState {
    public static final int[] ALLOWED_VALUES = {400, 401, MINIMIZED, MAXIMIZED};
    public static final int FULL_SCREEN = 401;
    public static final int MAXIMIZED = 403;
    public static final int MINIMIZED = 402;
    public static final int NORMAL = 400;
}

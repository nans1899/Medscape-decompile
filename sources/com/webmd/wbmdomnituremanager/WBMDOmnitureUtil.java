package com.webmd.wbmdomnituremanager;

import java.util.Date;

public class WBMDOmnitureUtil {
    public static String generateNewPvid() {
        String valueOf = String.valueOf(Math.round((float) (new Date().getTime() / 1000)));
        String substring = String.valueOf(Math.random()).substring(2, 10);
        return valueOf + substring;
    }
}

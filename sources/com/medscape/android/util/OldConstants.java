package com.medscape.android.util;

import android.content.Context;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.medscape.android.MedscapeApplication;

public class OldConstants {
    public static final String CME_TRACKER_URL = "https://www.medscape.org/activitytracker";
    public static final String CR_WEB_URL = "https://emedicine.medscape.com/article/";
    public static final String DRUG_WEB_URL = "https://reference.medscape.com/drug/";
    public static final int FEED_TYPE_JSON_CME = 9;
    public static final int FEED_TYPE_JSON_HOME = 7;
    public static final int FEED_TYPE_JSON_NEWS = 8;
    public static final int FORMULARY_UPDATE_REQUEST_CODE = 1212;
    public static final long FORMULARY_UPDATE_TIME = 2592000000L;
    public static final String MAPP = getMAPP("mapp");
    public static final int OBFUSCATE_GUID_VALUE = 27;
    public static int POP_UP_WINDOW_WIDTH = PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS;
    public static final int POST_MESSAGE_TO_FACEBOOK = 9876;
    public static Context mContext = MedscapeApplication.get();

    private static String getMAPP(String str) {
        String applicationVersion = Util.getApplicationVersion(mContext);
        return applicationVersion != null ? applicationVersion.replace(".", "") : "22";
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFormularyURL(java.lang.String r6) {
        /*
            int r0 = r6.hashCode()
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case 206969445: goto L_0x003f;
                case 446124970: goto L_0x0035;
                case 568937877: goto L_0x002b;
                case 568961724: goto L_0x0021;
                case 568961725: goto L_0x0017;
                case 568961726: goto L_0x000d;
                default: goto L_0x000c;
            }
        L_0x000c:
            goto L_0x0049
        L_0x000d:
            java.lang.String r0 = "environment_qa02"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = 4
            goto L_0x004a
        L_0x0017:
            java.lang.String r0 = "environment_qa01"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = 2
            goto L_0x004a
        L_0x0021:
            java.lang.String r0 = "environment_qa00"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = 1
            goto L_0x004a
        L_0x002b:
            java.lang.String r0 = "environment_perf"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = 3
            goto L_0x004a
        L_0x0035:
            java.lang.String r0 = "environment_dev01"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = 5
            goto L_0x004a
        L_0x003f:
            java.lang.String r0 = "environment_production"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0049
            r6 = 0
            goto L_0x004a
        L_0x0049:
            r6 = -1
        L_0x004a:
            if (r6 == 0) goto L_0x0068
            if (r6 == r5) goto L_0x0065
            if (r6 == r4) goto L_0x0062
            if (r6 == r3) goto L_0x005f
            if (r6 == r2) goto L_0x005c
            if (r6 == r1) goto L_0x0059
            java.lang.String r6 = ""
            goto L_0x006a
        L_0x0059:
            java.lang.String r6 = "http://api.dev01.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            goto L_0x006a
        L_0x005c:
            java.lang.String r6 = "http://api.qa02.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            goto L_0x006a
        L_0x005f:
            java.lang.String r6 = "http://api.perf.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            goto L_0x006a
        L_0x0062:
            java.lang.String r6 = "http://api.qa01.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            goto L_0x006a
        L_0x0065:
            java.lang.String r6 = "http://api.qa00.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            goto L_0x006a
        L_0x0068:
            java.lang.String r6 = "https://api.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
        L_0x006a:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.OldConstants.getFormularyURL(java.lang.String):java.lang.String");
    }
}

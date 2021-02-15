package net.media.android.bidder.base.models.internal;

import java.io.Serializable;
import mnetinternal.c;

public final class Logger implements Serializable {
    public static final String ADDRESS = "address";
    public static final String DEVICE = "device";
    public static final String ERROR = "error";
    public static final String EVENT = "event";
    public static final String LOCATION = "location";
    public static final String LOG = "log";
    public static final String NETWORK = "network";
    public static final String PUBLISHER = "publisher";
    public static final String TIMEZONE = "timezone";
    public static final String USER_AGENT = "user_agent";
    @c(a = "lvl")
    private String loggingLevel;
    @c(a = "lg")
    private Object message;
    @c(a = "plt")
    private String platform;
    @c(a = "ts")
    private long timestamp;
    @c(a = "tp")
    private String type;

    public Logger() {
        this.platform = "android";
    }

    public Logger(String str, Object obj, String str2) {
        this.platform = "android";
        this.type = str;
        this.message = obj;
        this.loggingLevel = str2;
        this.timestamp = System.currentTimeMillis();
    }

    public Logger(String str, Object obj) {
        this(str, obj, (String) null);
    }

    public String getType() {
        return this.type;
    }

    public Object getMessage() {
        return this.message;
    }

    public String getLoggingLevel() {
        return this.loggingLevel;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setMessage(Object obj) {
        this.message = obj;
    }

    public void setLoggingLevel(String str) {
        this.loggingLevel = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public boolean isLogType() {
        return LOG.equals(this.type);
    }

    public boolean isErrorType() {
        return "error".equals(this.type);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isEventType() {
        /*
            r4 = this;
            java.lang.String r0 = r4.type
            int r1 = r0.hashCode()
            r2 = 0
            r3 = 1
            switch(r1) {
                case -2076227591: goto L_0x0052;
                case -1335157162: goto L_0x0048;
                case -1147692044: goto L_0x003e;
                case 96891546: goto L_0x0034;
                case 1447404028: goto L_0x002a;
                case 1843485230: goto L_0x0020;
                case 1901043637: goto L_0x0016;
                case 1917799825: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x005c
        L_0x000c:
            java.lang.String r1 = "user_agent"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 4
            goto L_0x005d
        L_0x0016:
            java.lang.String r1 = "location"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 3
            goto L_0x005d
        L_0x0020:
            java.lang.String r1 = "network"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 2
            goto L_0x005d
        L_0x002a:
            java.lang.String r1 = "publisher"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 7
            goto L_0x005d
        L_0x0034:
            java.lang.String r1 = "event"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 0
            goto L_0x005d
        L_0x003e:
            java.lang.String r1 = "address"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 6
            goto L_0x005d
        L_0x0048:
            java.lang.String r1 = "device"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 1
            goto L_0x005d
        L_0x0052:
            java.lang.String r1 = "timezone"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 5
            goto L_0x005d
        L_0x005c:
            r0 = -1
        L_0x005d:
            switch(r0) {
                case 0: goto L_0x0061;
                case 1: goto L_0x0061;
                case 2: goto L_0x0061;
                case 3: goto L_0x0061;
                case 4: goto L_0x0061;
                case 5: goto L_0x0061;
                case 6: goto L_0x0061;
                case 7: goto L_0x0061;
                default: goto L_0x0060;
            }
        L_0x0060:
            return r2
        L_0x0061:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: net.media.android.bidder.base.models.internal.Logger.isEventType():boolean");
    }
}

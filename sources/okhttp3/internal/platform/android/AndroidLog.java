package okhttp3.internal.platform.android;

import android.util.Log;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001a\u00020\fJ\u0018\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\nH\u0002J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLog;", "", "()V", "MAX_LOG_LENGTH", "", "configuredLoggers", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Ljava/util/logging/Logger;", "knownLoggers", "", "", "androidLog", "", "loggerName", "logLevel", "message", "t", "", "androidLog$okhttp", "enable", "enableLogging", "logger", "tag", "loggerTag", "okhttp"}, k = 1, mv = {1, 4, 0})
/* compiled from: AndroidLog.kt */
public final class AndroidLog {
    public static final AndroidLog INSTANCE = new AndroidLog();
    private static final int MAX_LOG_LENGTH = 4000;
    private static final CopyOnWriteArraySet<Logger> configuredLoggers = new CopyOnWriteArraySet<>();
    private static final Map<String, String> knownLoggers;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Package packageR = OkHttpClient.class.getPackage();
        String name = packageR != null ? packageR.getName() : null;
        if (name != null) {
            linkedHashMap.put(name, "OkHttp");
        }
        Map map = linkedHashMap;
        String name2 = OkHttpClient.class.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "OkHttpClient::class.java.name");
        map.put(name2, "okhttp.OkHttpClient");
        String name3 = Http2.class.getName();
        Intrinsics.checkNotNullExpressionValue(name3, "Http2::class.java.name");
        map.put(name3, "okhttp.Http2");
        String name4 = TaskRunner.class.getName();
        Intrinsics.checkNotNullExpressionValue(name4, "TaskRunner::class.java.name");
        map.put(name4, "okhttp.TaskRunner");
        map.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        knownLoggers = MapsKt.toMap(map);
    }

    private AndroidLog() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005d, code lost:
        r11 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void androidLog$okhttp(java.lang.String r8, int r9, java.lang.String r10, java.lang.Throwable r11) {
        /*
            r7 = this;
            java.lang.String r0 = "loggerName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "message"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r8 = r7.loggerTag(r8)
            boolean r0 = android.util.Log.isLoggable(r8, r9)
            if (r0 == 0) goto L_0x006a
            if (r11 == 0) goto L_0x002e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            java.lang.String r10 = "\n"
            r0.append(r10)
            java.lang.String r10 = android.util.Log.getStackTraceString(r11)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
        L_0x002e:
            r11 = 0
            int r0 = r10.length()
        L_0x0033:
            if (r11 >= r0) goto L_0x006a
            r1 = r10
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r2 = 10
            r4 = 0
            r5 = 4
            r6 = 0
            r3 = r11
            int r1 = kotlin.text.StringsKt.indexOf$default((java.lang.CharSequence) r1, (char) r2, (int) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
            r2 = -1
            if (r1 == r2) goto L_0x0046
            goto L_0x0047
        L_0x0046:
            r1 = r0
        L_0x0047:
            int r2 = r11 + 4000
            int r2 = java.lang.Math.min(r1, r2)
            if (r10 == 0) goto L_0x0062
            java.lang.String r11 = r10.substring(r11, r2)
            java.lang.String r3 = "(this as java.lang.Strin…ing(startIndex, endIndex)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r3)
            android.util.Log.println(r9, r8, r11)
            if (r2 < r1) goto L_0x0060
            int r11 = r2 + 1
            goto L_0x0033
        L_0x0060:
            r11 = r2
            goto L_0x0047
        L_0x0062:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "null cannot be cast to non-null type java.lang.String"
            r8.<init>(r9)
            throw r8
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.platform.android.AndroidLog.androidLog$okhttp(java.lang.String, int, java.lang.String, java.lang.Throwable):void");
    }

    private final String loggerTag(String str) {
        String str2 = knownLoggers.get(str);
        return str2 != null ? str2 : StringsKt.take(str, 23);
    }

    public final void enable() {
        for (Map.Entry next : knownLoggers.entrySet()) {
            enableLogging((String) next.getKey(), (String) next.getValue());
        }
    }

    private final void enableLogging(String str, String str2) {
        Level level;
        Logger logger = Logger.getLogger(str);
        if (configuredLoggers.add(logger)) {
            Intrinsics.checkNotNullExpressionValue(logger, "logger");
            logger.setUseParentHandlers(false);
            if (Log.isLoggable(str2, 3)) {
                level = Level.FINE;
            } else if (Log.isLoggable(str2, 4)) {
                level = Level.INFO;
            } else {
                level = Level.WARNING;
            }
            logger.setLevel(level);
            logger.addHandler(AndroidLogHandler.INSTANCE);
        }
    }
}

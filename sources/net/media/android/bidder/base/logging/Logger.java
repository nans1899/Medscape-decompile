package net.media.android.bidder.base.logging;

import android.util.Log;
import net.media.android.bidder.base.models.internal.ErrorLog;

public final class Logger {
    public static void debug(String str, String str2) {
    }

    public static void error(String str, String str2) {
    }

    public static void error(String str, String str2, Throwable th) {
    }

    public static void info(String str, String str2) {
    }

    public static void warning(String str, String str2, Throwable th) {
    }

    public static void notify(String str, String str2, Throwable th) {
        try {
            b.a().a(new net.media.android.bidder.base.models.internal.Logger("error", new ErrorLog(str, str2, th)));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("##Logger", "Exception in logger.notify, ignored to avoid recursive calls");
        }
    }

    public static void warning(String str, String str2) {
        Log.w(str, str2);
    }
}

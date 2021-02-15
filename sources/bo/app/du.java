package bo.app;

import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public final class du {
    private static final String a = AppboyLogger.getAppboyLogTag(du.class);
    private static final TimeZone b = TimeZone.getTimeZone("UTC");
    private static final EnumSet<u> c = EnumSet.of(u.SHORT, u.LONG, u.ANDROID_LOGCAT);

    public static long a() {
        return System.currentTimeMillis() / 1000;
    }

    public static double b() {
        return ((double) System.currentTimeMillis()) / 1000.0d;
    }

    public static long c() {
        return System.currentTimeMillis();
    }

    public static Date a(String str, u uVar) {
        if (StringUtils.isNullOrBlank(str)) {
            String str2 = a;
            AppboyLogger.w(str2, "Null or blank date string received: " + str);
            return null;
        } else if (!c.contains(uVar)) {
            String str3 = a;
            AppboyLogger.w(str3, "Unsupported date format. Returning null. Got date format: " + uVar);
            return null;
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(uVar.a(), Locale.US);
            simpleDateFormat.setTimeZone(b);
            try {
                return simpleDateFormat.parse(str);
            } catch (Exception e) {
                String str4 = a;
                AppboyLogger.e(str4, "Exception parsing date " + str + ". Returning null", e);
                return null;
            }
        }
    }

    public static String a(Date date, u uVar) {
        if (!c.contains(uVar)) {
            String str = a;
            AppboyLogger.w(str, "Unsupported date format: " + uVar + ". Defaulting to " + u.LONG);
            uVar = u.LONG;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(uVar.a(), Locale.US);
        simpleDateFormat.setTimeZone(b);
        return simpleDateFormat.format(date);
    }

    public static Date a(int i, int i2, int i3) {
        return a(i, i2, i3, 0, 0, 0);
    }

    public static Date a(int i, int i2, int i3, int i4, int i5, int i6) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2, i3, i4, i5, i6);
        gregorianCalendar.setTimeZone(b);
        return gregorianCalendar.getTime();
    }

    public static Date a(long j) {
        return new Date(j * 1000);
    }

    public static long a(Date date) {
        return date.getTime() / 1000;
    }
}

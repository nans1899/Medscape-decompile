package mnetinternal;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.b;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AnalyticsEvent;
import net.media.android.bidder.base.models.internal.DisplaySize;

public final class cv {
    public static boolean a() {
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return true;
        }
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean b() {
        return Build.FINGERPRINT.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && Build.DEVICE.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) || "google_sdk".equals(Build.PRODUCT);
    }

    public static long a(Context context) {
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
            return memoryInfo.totalMem / 1024;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("/proc/meminfo", "r");
            Matcher matcher = Pattern.compile("(\\d+)").matcher(randomAccessFile.readLine());
            String str = "";
            while (matcher.find()) {
                str = matcher.group(1);
            }
            randomAccessFile.close();
            return Long.parseLong(str) / 1024;
        } catch (IOException e) {
            Logger.error("##DeviceUtil##", e.getMessage());
            return 0;
        }
    }

    public static long c() {
        long j;
        long j2;
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            j = statFs.getBlockSizeLong();
        } else {
            j = (long) statFs.getBlockSize();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            j2 = statFs.getAvailableBlocksLong();
        } else {
            j2 = (long) statFs.getAvailableBlocks();
        }
        return j2 * j;
    }

    public static long d() {
        try {
            if (Runtime.getRuntime().maxMemory() != Long.MAX_VALUE) {
                return Runtime.getRuntime().maxMemory();
            }
            return Runtime.getRuntime().totalMemory();
        } catch (Exception unused) {
            return 0;
        }
    }

    public static boolean b(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null || (runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.importance == 100 && context.getPackageName().contains(next.processName)) {
                return true;
            }
        }
        return false;
    }

    public static int e() {
        if (Build.VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }
        return f();
    }

    private static int f() {
        try {
            File[] listFiles = new File("/sys/devices/system/cpu/").listFiles(new a());
            if (listFiles == null) {
                return 0;
            }
            return listFiles.length;
        } catch (Exception unused) {
            return 1;
        }
    }

    private static class a implements FileFilter {
        private a() {
        }

        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]+", file.getName());
        }
    }

    private static String f(Context context) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread() || b.a().e()) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            Class<?> cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            if (invoke != null) {
                Object invoke2 = cls2.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
                if (invoke2 != null) {
                    Logger.debug("##DeviceUtil##", "advertising id: " + invoke2);
                    return (String) invoke2;
                }
                net.media.android.bidder.base.analytics.b.a().a(AnalyticsEvent.Events.newEvent("null_advertising_id").addProperty("customer_id", MNet.getCustomerId()));
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static boolean c(Context context) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return false;
        }
        try {
            Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            Class<?> cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
            if (invoke != null) {
                return ((Boolean) cls2.getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])).booleanValue();
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public static String d(Context context) {
        String str;
        if (b.a().e()) {
            return "";
        }
        String a2 = cb.a().a("__mn__unique_device_id");
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Logger.error("##DeviceUtil##", "getUniqueDeviceId() called from UI thread");
            return "";
        }
        String f = f(context);
        if (TextUtils.isEmpty(f)) {
            str = cb.a().a("__mn__unique_device_id_gen");
            if (TextUtils.isEmpty(str)) {
                str = String.format("gen:%s", new Object[]{UUID.randomUUID().toString()});
                cb.a().a("__mn__unique_device_id_gen", str);
            }
        } else {
            str = String.format("ifa:%s", new Object[]{f});
        }
        cb.a().a("__mn__unique_device_id", str);
        return str;
    }

    public static DisplaySize a(Display display, DisplayMetrics displayMetrics) {
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (Build.VERSION.SDK_INT < 17) {
            try {
                i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(display, new Object[0])).intValue();
            } catch (Exception e) {
                Logger.debug("##DeviceUtil##", e.getMessage());
            }
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point point = new Point();
                Display.class.getMethod("getRealSize", new Class[]{Point.class}).invoke(display, new Object[]{point});
                i = point.x;
                i2 = point.y;
            } catch (Exception e2) {
                Logger.debug("##DeviceUtil##", e2.getMessage());
            }
        }
        return new DisplaySize(i, i2);
    }

    public static boolean e(Context context) {
        if (context.getResources() == null || context.getResources().getConfiguration() == null || (context.getResources().getConfiguration().screenLayout & 15) < 3) {
            return false;
        }
        return true;
    }

    public static long a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        InetAddress.getByName(str).isReachable(i);
        return System.currentTimeMillis() - currentTimeMillis;
    }
}

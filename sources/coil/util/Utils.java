package coil.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StatFs;
import androidx.core.content.ContextCompat;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\r2\b\b\u0001\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0016\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006J\u000e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u001a\u001a\u00020\u0011J\u0006\u0010\u001b\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcoil/util/Utils;", "", "()V", "CACHE_DIRECTORY_NAME", "", "DISK_CACHE_PERCENTAGE", "", "LOW_MEMORY_MULTIPLIER", "MAX_DISK_CACHE_SIZE", "", "MIN_DISK_CACHE_SIZE", "STANDARD_MULTIPLIER", "calculateAllocationByteCount", "", "width", "height", "config", "Landroid/graphics/Bitmap$Config;", "calculateAvailableMemorySize", "context", "Landroid/content/Context;", "percentage", "calculateDiskCacheSize", "cacheDirectory", "Ljava/io/File;", "getDefaultAvailableMemoryPercentage", "getDefaultBitmapConfig", "getDefaultBitmapPoolPercentage", "getDefaultCacheDirectory", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Utils.kt */
public final class Utils {
    private static final String CACHE_DIRECTORY_NAME = "image_cache";
    private static final double DISK_CACHE_PERCENTAGE = 0.02d;
    public static final Utils INSTANCE = new Utils();
    private static final double LOW_MEMORY_MULTIPLIER = 0.15d;
    private static final long MAX_DISK_CACHE_SIZE = 262144000;
    private static final long MIN_DISK_CACHE_SIZE = 10485760;
    private static final double STANDARD_MULTIPLIER = 0.2d;

    private Utils() {
    }

    public final int calculateAllocationByteCount(int i, int i2, Bitmap.Config config) {
        return i * i2 * Bitmaps.getBytesPerPixel(config);
    }

    public final File getDefaultCacheDirectory(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        File file = new File(context.getCacheDir(), CACHE_DIRECTORY_NAME);
        file.mkdirs();
        return file;
    }

    public final long calculateDiskCacheSize(File file) {
        Intrinsics.checkParameterIsNotNull(file, "cacheDirectory");
        try {
            StatFs statFs = new StatFs(file.getAbsolutePath());
            return RangesKt.coerceIn((long) (DISK_CACHE_PERCENTAGE * ((double) (Build.VERSION.SDK_INT > 18 ? statFs.getBlockCountLong() : (long) statFs.getBlockCount())) * ((double) (Build.VERSION.SDK_INT > 18 ? statFs.getBlockSizeLong() : (long) statFs.getBlockSize()))), (long) MIN_DISK_CACHE_SIZE, (long) MAX_DISK_CACHE_SIZE);
        } catch (Exception unused) {
            return MIN_DISK_CACHE_SIZE;
        }
    }

    public final double getDefaultBitmapPoolPercentage() {
        int i = Build.VERSION.SDK_INT;
        return (19 <= i && 25 >= i) ? 0.5d : 0.25d;
    }

    public final Bitmap.Config getDefaultBitmapConfig() {
        return Build.VERSION.SDK_INT >= 26 ? Bitmap.Config.HARDWARE : Bitmap.Config.ARGB_8888;
    }

    public final long calculateAvailableMemorySize(Context context, double d) {
        int i;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
        if (systemService != null) {
            ActivityManager activityManager = (ActivityManager) systemService;
            if ((context.getApplicationInfo().flags & 1048576) != 0) {
                i = activityManager.getLargeMemoryClass();
            } else {
                i = activityManager.getMemoryClass();
            }
            double d2 = (double) 1024;
            return (long) (d * ((double) i) * d2 * d2);
        }
        throw new IllegalStateException(("System service of type " + ActivityManager.class + " was not found.").toString());
    }

    public final double getDefaultAvailableMemoryPercentage(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = ContextCompat.getSystemService(context, ActivityManager.class);
        if (systemService != null) {
            if (Build.VERSION.SDK_INT < 19 || ((ActivityManager) systemService).isLowRamDevice()) {
                return LOW_MEMORY_MULTIPLIER;
            }
            return 0.2d;
        }
        throw new IllegalStateException(("System service of type " + ActivityManager.class + " was not found.").toString());
    }
}

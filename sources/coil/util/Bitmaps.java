package coil.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0005H\u0000\u001a\u000e\u0010\n\u001a\u00020\t*\u0004\u0018\u00010\u0002H\u0000\u001a\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\b\u001a\u000e\u0010\u000f\u001a\u00020\u0002*\u0004\u0018\u00010\u0002H\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00058@X\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"isHardware", "", "Landroid/graphics/Bitmap$Config;", "(Landroid/graphics/Bitmap$Config;)Z", "safeConfig", "Landroid/graphics/Bitmap;", "getSafeConfig", "(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap$Config;", "getAllocationByteCountCompat", "", "getBytesPerPixel", "toDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "context", "Landroid/content/Context;", "toSoftware", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* renamed from: coil.util.-Bitmaps  reason: invalid class name */
/* compiled from: Bitmaps.kt */
public final class Bitmaps {
    public static final int getBytesPerPixel(Bitmap.Config config) {
        if (config == Bitmap.Config.ALPHA_8) {
            return 1;
        }
        if (config == Bitmap.Config.RGB_565 || config == Bitmap.Config.ARGB_4444) {
            return 2;
        }
        return (Build.VERSION.SDK_INT < 26 || config != Bitmap.Config.RGBA_F16) ? 4 : 8;
    }

    public static final BitmapDrawable toDrawable(Bitmap bitmap, Context context) {
        Intrinsics.checkParameterIsNotNull(bitmap, "$this$toDrawable");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        return new BitmapDrawable(resources, bitmap);
    }

    public static final int getAllocationByteCountCompat(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "$this$getAllocationByteCountCompat");
        if (!bitmap.isRecycled()) {
            try {
                if (Build.VERSION.SDK_INT >= 19) {
                    return bitmap.getAllocationByteCount();
                }
                return bitmap.getHeight() * bitmap.getRowBytes();
            } catch (Exception unused) {
                return Utils.INSTANCE.calculateAllocationByteCount(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
            }
        } else {
            throw new IllegalStateException(("Cannot obtain size for recycled Bitmap: " + bitmap + " [" + bitmap.getWidth() + " x " + bitmap.getHeight() + "] + " + bitmap.getConfig()).toString());
        }
    }

    public static final boolean isHardware(Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, "$this$isHardware");
        return Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE;
    }

    public static final Bitmap.Config getSafeConfig(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "$this$safeConfig");
        Bitmap.Config config = bitmap.getConfig();
        return config != null ? config : Bitmap.Config.ARGB_8888;
    }

    public static final Bitmap.Config toSoftware(Bitmap.Config config) {
        return (config == null || isHardware(config)) ? Bitmap.Config.ARGB_8888 : config;
    }
}

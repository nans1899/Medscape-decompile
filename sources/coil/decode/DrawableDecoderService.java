package coil.decode;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.bitmappool.BitmapPool;
import coil.size.OriginalSize;
import coil.size.PixelSize;
import coil.size.Scale;
import coil.size.Size;
import coil.util.Bitmaps;
import coil.util.Extensions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.media.android.bidder.base.common.Constants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0002J(\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcoil/decode/DrawableDecoderService;", "", "bitmapPool", "Lcoil/bitmappool/BitmapPool;", "(Lcoil/bitmappool/BitmapPool;)V", "convert", "Landroid/graphics/Bitmap;", "drawable", "Landroid/graphics/drawable/Drawable;", "config", "Landroid/graphics/Bitmap$Config;", "size", "Lcoil/size/Size;", "scale", "Lcoil/size/Scale;", "allowInexactSize", "", "isConfigValid", "bitmap", "isSizeValid", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DrawableDecoderService.kt */
public final class DrawableDecoderService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DEFAULT_SIZE = 512;
    private final BitmapPool bitmapPool;

    public DrawableDecoderService(BitmapPool bitmapPool2) {
        Intrinsics.checkParameterIsNotNull(bitmapPool2, "bitmapPool");
        this.bitmapPool = bitmapPool2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcoil/decode/DrawableDecoderService$Companion;", "", "()V", "DEFAULT_SIZE", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DrawableDecoderService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final Bitmap convert(Drawable drawable, Bitmap.Config config, Size size, Scale scale, boolean z) {
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        Intrinsics.checkParameterIsNotNull(size, "size");
        Intrinsics.checkParameterIsNotNull(scale, "scale");
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
            if (isConfigValid(bitmap, config) && isSizeValid(z, size, bitmap, scale)) {
                return bitmap;
            }
        }
        int width = Extensions.getWidth(drawable);
        int i = 512;
        if (width <= 0) {
            width = 512;
        }
        int height = Extensions.getHeight(drawable);
        if (height > 0) {
            i = height;
        }
        PixelSize computePixelSize = DecodeUtils.computePixelSize(width, i, size, scale);
        int component1 = computePixelSize.component1();
        int component2 = computePixelSize.component2();
        Bitmap bitmap2 = this.bitmapPool.get(component1, component2, Bitmaps.toSoftware(config));
        Rect bounds = drawable.getBounds();
        int i2 = bounds.left;
        int i3 = bounds.top;
        int i4 = bounds.right;
        int i5 = bounds.bottom;
        drawable.setBounds(0, 0, component1, component2);
        drawable.draw(new Canvas(bitmap2));
        drawable.setBounds(i2, i3, i4, i5);
        return bitmap2;
    }

    private final boolean isConfigValid(Bitmap bitmap, Bitmap.Config config) {
        return bitmap.getConfig() == Bitmaps.toSoftware(config);
    }

    private final boolean isSizeValid(boolean z, Size size, Bitmap bitmap, Scale scale) {
        return z || (size instanceof OriginalSize) || Intrinsics.areEqual((Object) size, (Object) DecodeUtils.computePixelSize(bitmap.getWidth(), bitmap.getHeight(), size, scale));
    }
}

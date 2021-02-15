package coil.transform;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import coil.bitmappool.BitmapPool;
import coil.size.Size;
import coil.util.Bitmaps;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J)\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lcoil/transform/CircleCropTransformation;", "Lcoil/transform/Transformation;", "()V", "key", "", "transform", "Landroid/graphics/Bitmap;", "pool", "Lcoil/bitmappool/BitmapPool;", "input", "size", "Lcoil/size/Size;", "(Lcoil/bitmappool/BitmapPool;Landroid/graphics/Bitmap;Lcoil/size/Size;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CircleCropTransformation.kt */
public final class CircleCropTransformation implements Transformation {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final PorterDuffXfermode XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcoil/transform/CircleCropTransformation$Companion;", "", "()V", "XFERMODE", "Landroid/graphics/PorterDuffXfermode;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: CircleCropTransformation.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public String key() {
        String name = CircleCropTransformation.class.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "CircleCropTransformation::class.java.name");
        return name;
    }

    public Object transform(BitmapPool bitmapPool, Bitmap bitmap, Size size, Continuation<? super Bitmap> continuation) {
        Paint paint = new Paint(3);
        int min = Math.min(bitmap.getWidth(), bitmap.getHeight());
        float f = ((float) min) / 2.0f;
        Bitmap bitmap2 = bitmapPool.get(min, min, Bitmaps.getSafeConfig(bitmap));
        Canvas canvas = new Canvas(bitmap2);
        canvas.drawCircle(f, f, f, paint);
        paint.setXfermode(XFERMODE);
        canvas.drawBitmap(bitmap, f - (((float) bitmap.getWidth()) / 2.0f), f - (((float) bitmap.getHeight()) / 2.0f), paint);
        bitmapPool.put(bitmap);
        return bitmap2;
    }
}

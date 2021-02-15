package coil.transform;

import android.graphics.Bitmap;
import coil.bitmappool.BitmapPool;
import coil.size.Size;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J)\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lcoil/transform/Transformation;", "", "key", "", "transform", "Landroid/graphics/Bitmap;", "pool", "Lcoil/bitmappool/BitmapPool;", "input", "size", "Lcoil/size/Size;", "(Lcoil/bitmappool/BitmapPool;Landroid/graphics/Bitmap;Lcoil/size/Size;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Transformation.kt */
public interface Transformation {
    String key();

    Object transform(BitmapPool bitmapPool, Bitmap bitmap, Size size, Continuation<? super Bitmap> continuation);
}

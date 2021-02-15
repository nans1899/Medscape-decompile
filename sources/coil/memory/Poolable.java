package coil.memory;

import android.graphics.Bitmap;
import coil.base.R;
import coil.target.PoolableViewTarget;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bb\u0018\u00002\u00020\u0001J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR#\u0010\f\u001a\u0004\u0018\u00010\u000b*\u0006\u0012\u0002\b\u00030\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b8Â\u0002@Â\u0002X\u000e¨\u0006\u0010"}, d2 = {"Lcoil/memory/Poolable;", "", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "getReferenceCounter", "()Lcoil/memory/BitmapReferenceCounter;", "target", "Lcoil/target/PoolableViewTarget;", "getTarget", "()Lcoil/target/PoolableViewTarget;", "value", "Landroid/graphics/Bitmap;", "bitmap", "decrement", "", "increment", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TargetDelegate.kt */
interface Poolable {
    void decrement(Bitmap bitmap);

    BitmapReferenceCounter getReferenceCounter();

    PoolableViewTarget<?> getTarget();

    void increment(Bitmap bitmap);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: TargetDelegate.kt */
    public static final class DefaultImpls {
        /* JADX WARNING: type inference failed for: r1v0, types: [coil.target.PoolableViewTarget, coil.target.PoolableViewTarget<?>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static android.graphics.Bitmap getBitmap(coil.memory.Poolable r0, coil.target.PoolableViewTarget<?> r1) {
            /*
                android.view.View r0 = r1.getView()
                int r1 = coil.base.R.id.coil_bitmap
                java.lang.Object r0 = r0.getTag(r1)
                boolean r1 = r0 instanceof android.graphics.Bitmap
                if (r1 != 0) goto L_0x000f
                r0 = 0
            L_0x000f:
                android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: coil.memory.Poolable.DefaultImpls.getBitmap(coil.memory.Poolable, coil.target.PoolableViewTarget):android.graphics.Bitmap");
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [coil.target.PoolableViewTarget, coil.target.PoolableViewTarget<?>] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void setBitmap(coil.memory.Poolable r0, coil.target.PoolableViewTarget<?> r1, android.graphics.Bitmap r2) {
            /*
                android.view.View r0 = r1.getView()
                int r1 = coil.base.R.id.coil_bitmap
                r0.setTag(r1, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: coil.memory.Poolable.DefaultImpls.setBitmap(coil.memory.Poolable, coil.target.PoolableViewTarget, android.graphics.Bitmap):void");
        }

        public static void increment(Poolable poolable, Bitmap bitmap) {
            if (bitmap != null) {
                poolable.getReferenceCounter().increment(bitmap);
            }
        }

        public static void decrement(Poolable poolable, Bitmap bitmap) {
            Object tag = poolable.getTarget().getView().getTag(R.id.coil_bitmap);
            if (!(tag instanceof Bitmap)) {
                tag = null;
            }
            Bitmap bitmap2 = (Bitmap) tag;
            if (bitmap2 != null) {
                poolable.getReferenceCounter().decrement(bitmap2);
            }
            poolable.getTarget().getView().setTag(R.id.coil_bitmap, bitmap);
        }
    }
}

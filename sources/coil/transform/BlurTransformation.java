package coil.transform;

import android.content.Context;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J)\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lcoil/transform/BlurTransformation;", "Lcoil/transform/Transformation;", "context", "Landroid/content/Context;", "radius", "", "sampling", "(Landroid/content/Context;FF)V", "key", "", "transform", "Landroid/graphics/Bitmap;", "pool", "Lcoil/bitmappool/BitmapPool;", "input", "size", "Lcoil/size/Size;", "(Lcoil/bitmappool/BitmapPool;Landroid/graphics/Bitmap;Lcoil/size/Size;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BlurTransformation.kt */
public final class BlurTransformation implements Transformation {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final float DEFAULT_RADIUS = 10.0f;
    private static final float DEFAULT_SAMPLING = 1.0f;
    private final Context context;
    private final float radius;
    private final float sampling;

    public BlurTransformation(Context context2) {
        this(context2, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null);
    }

    public BlurTransformation(Context context2, float f) {
        this(context2, f, 0.0f, 4, (DefaultConstructorMarker) null);
    }

    public BlurTransformation(Context context2, float f, float f2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
        this.radius = f;
        this.sampling = f2;
        double d = (double) f;
        boolean z = true;
        if (d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && d <= 25.0d) {
            if (!(this.sampling <= ((float) 0) ? false : z)) {
                throw new IllegalArgumentException("sampling must be > 0.".toString());
            }
            return;
        }
        throw new IllegalArgumentException("radius must be in [0, 25].".toString());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BlurTransformation(Context context2, float f, float f2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? DEFAULT_RADIUS : f, (i & 4) != 0 ? 1.0f : f2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcoil/transform/BlurTransformation$Companion;", "", "()V", "DEFAULT_RADIUS", "", "DEFAULT_SAMPLING", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BlurTransformation.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public String key() {
        return BlurTransformation.class.getName() + '-' + this.radius + '-' + this.sampling;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0099  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object transform(coil.bitmappool.BitmapPool r6, android.graphics.Bitmap r7, coil.size.Size r8, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r9) {
        /*
            r5 = this;
            android.graphics.Paint r8 = new android.graphics.Paint
            r9 = 3
            r8.<init>(r9)
            int r9 = r7.getWidth()
            float r9 = (float) r9
            float r0 = r5.sampling
            float r9 = r9 / r0
            int r9 = (int) r9
            int r0 = r7.getHeight()
            float r0 = (float) r0
            float r1 = r5.sampling
            float r0 = r0 / r1
            int r0 = (int) r0
            android.graphics.Bitmap$Config r1 = coil.util.Bitmaps.getSafeConfig(r7)
            android.graphics.Bitmap r9 = r6.get(r9, r0, r1)
            android.graphics.Canvas r0 = new android.graphics.Canvas
            r0.<init>(r9)
            r1 = 1
            float r2 = (float) r1
            float r3 = r5.sampling
            float r4 = r2 / r3
            float r2 = r2 / r3
            r0.scale(r4, r2)
            r2 = 0
            r0.drawBitmap(r7, r2, r2, r8)
            r8 = 0
            r0 = r8
            android.renderscript.RenderScript r0 = (android.renderscript.RenderScript) r0
            r2 = r8
            android.renderscript.Allocation r2 = (android.renderscript.Allocation) r2
            android.renderscript.ScriptIntrinsicBlur r8 = (android.renderscript.ScriptIntrinsicBlur) r8
            android.content.Context r3 = r5.context     // Catch:{ all -> 0x0086 }
            android.renderscript.RenderScript r0 = android.renderscript.RenderScript.create(r3)     // Catch:{ all -> 0x0086 }
            android.renderscript.Allocation$MipmapControl r3 = android.renderscript.Allocation.MipmapControl.MIPMAP_NONE     // Catch:{ all -> 0x0086 }
            android.renderscript.Allocation r1 = android.renderscript.Allocation.createFromBitmap(r0, r9, r3, r1)     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = "tmpInt"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)     // Catch:{ all -> 0x0082 }
            android.renderscript.Type r3 = r1.getType()     // Catch:{ all -> 0x0082 }
            android.renderscript.Allocation r2 = android.renderscript.Allocation.createTyped(r0, r3)     // Catch:{ all -> 0x0082 }
            android.renderscript.Element r3 = android.renderscript.Element.U8_4(r0)     // Catch:{ all -> 0x0082 }
            android.renderscript.ScriptIntrinsicBlur r8 = android.renderscript.ScriptIntrinsicBlur.create(r0, r3)     // Catch:{ all -> 0x0082 }
            float r3 = r5.radius     // Catch:{ all -> 0x0082 }
            r8.setRadius(r3)     // Catch:{ all -> 0x0082 }
            r8.setInput(r1)     // Catch:{ all -> 0x0082 }
            r8.forEach(r2)     // Catch:{ all -> 0x0082 }
            r2.copyTo(r9)     // Catch:{ all -> 0x0082 }
            if (r0 == 0) goto L_0x0071
            r0.destroy()
        L_0x0071:
            r1.destroy()
            if (r2 == 0) goto L_0x0079
            r2.destroy()
        L_0x0079:
            if (r8 == 0) goto L_0x007e
            r8.destroy()
        L_0x007e:
            r6.put(r7)
            return r9
        L_0x0082:
            r6 = move-exception
            r7 = r2
            r2 = r1
            goto L_0x0088
        L_0x0086:
            r6 = move-exception
            r7 = r2
        L_0x0088:
            if (r0 == 0) goto L_0x008d
            r0.destroy()
        L_0x008d:
            if (r2 == 0) goto L_0x0092
            r2.destroy()
        L_0x0092:
            if (r7 == 0) goto L_0x0097
            r7.destroy()
        L_0x0097:
            if (r8 == 0) goto L_0x009c
            r8.destroy()
        L_0x009c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.transform.BlurTransformation.transform(coil.bitmappool.BitmapPool, android.graphics.Bitmap, coil.size.Size, kotlin.coroutines.Continuation):java.lang.Object");
    }
}

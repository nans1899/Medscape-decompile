package coil.decode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import coil.bitmappool.BitmapPool;
import coil.util.Bitmaps;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import net.bytebuddy.implementation.MethodDelegation;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Source;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 !2\u00020\u0001:\u0003!\"#B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J1\u0010\u0012\u001a\u00020\u00132\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aJ\u001a\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J$\u0010\u001f\u001a\u00020\r*\u00020 2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder;", "Lcoil/decode/Decoder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "paint", "Landroid/graphics/Paint;", "applyExifTransformations", "Landroid/graphics/Bitmap;", "pool", "Lcoil/bitmappool/BitmapPool;", "inBitmap", "config", "Landroid/graphics/Bitmap$Config;", "isFlipped", "", "rotationDegrees", "", "decode", "Lcoil/decode/DecodeResult;", "source", "Lokio/BufferedSource;", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Lokio/BufferedSource;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handles", "mimeType", "", "shouldReadExifData", "computeConfig", "Landroid/graphics/BitmapFactory$Options;", "Companion", "ExceptionCatchingSource", "ExifInterfaceInputStream", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BitmapFactoryDecoder.kt */
public final class BitmapFactoryDecoder implements Decoder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String MIME_TYPE_HEIC = "image/heic";
    private static final String MIME_TYPE_HEIF = "image/heif";
    private static final String MIME_TYPE_JPEG = "image/jpeg";
    private static final String MIME_TYPE_WEBP = "image/webp";
    private static final String[] SUPPORTED_EXIF_MIME_TYPES = {MIME_TYPE_JPEG, MIME_TYPE_WEBP, MIME_TYPE_HEIC, MIME_TYPE_HEIF};
    private final Context context;
    private final Paint paint = new Paint(3);

    public boolean handles(BufferedSource bufferedSource, String str) {
        Intrinsics.checkParameterIsNotNull(bufferedSource, "source");
        return true;
    }

    public BitmapFactoryDecoder(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder$Companion;", "", "()V", "MIME_TYPE_HEIC", "", "MIME_TYPE_HEIF", "MIME_TYPE_JPEG", "MIME_TYPE_WEBP", "SUPPORTED_EXIF_MIME_TYPES", "", "[Ljava/lang/String;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BitmapFactoryDecoder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x021e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object decode(coil.bitmappool.BitmapPool r27, okio.BufferedSource r28, coil.size.Size r29, coil.decode.Options r30, kotlin.coroutines.Continuation<? super coil.decode.DecodeResult> r31) {
        /*
            r26 = this;
            r7 = r26
            r2 = r27
            r0 = r29
            android.graphics.BitmapFactory$Options r8 = new android.graphics.BitmapFactory$Options
            r8.<init>()
            coil.decode.BitmapFactoryDecoder$ExceptionCatchingSource r1 = new coil.decode.BitmapFactoryDecoder$ExceptionCatchingSource
            r3 = r28
            okio.Source r3 = (okio.Source) r3
            r1.<init>(r3)
            r3 = r1
            okio.Source r3 = (okio.Source) r3
            okio.BufferedSource r3 = okio.Okio.buffer((okio.Source) r3)
            r9 = 1
            r8.inJustDecodeBounds = r9
            okio.BufferedSource r4 = r3.peek()
            java.io.InputStream r4 = r4.inputStream()
            r5 = 0
            android.graphics.BitmapFactory.decodeStream(r4, r5, r8)
            java.lang.Exception r4 = r1.getException()
            if (r4 != 0) goto L_0x0229
            r10 = 0
            r8.inJustDecodeBounds = r10
            java.lang.String r4 = r8.outMimeType
            boolean r4 = r7.shouldReadExifData(r4)
            if (r4 == 0) goto L_0x0059
            androidx.exifinterface.media.ExifInterface r4 = new androidx.exifinterface.media.ExifInterface
            coil.decode.BitmapFactoryDecoder$ExifInterfaceInputStream r6 = new coil.decode.BitmapFactoryDecoder$ExifInterfaceInputStream
            okio.BufferedSource r11 = r3.peek()
            java.io.InputStream r11 = r11.inputStream()
            r6.<init>(r11)
            java.io.InputStream r6 = (java.io.InputStream) r6
            r4.<init>((java.io.InputStream) r6)
            boolean r6 = r4.isFlipped()
            int r4 = r4.getRotationDegrees()
            r11 = r4
            goto L_0x005b
        L_0x0059:
            r6 = 0
            r11 = 0
        L_0x005b:
            r4 = 90
            if (r11 == r4) goto L_0x0066
            r4 = 270(0x10e, float:3.78E-43)
            if (r11 != r4) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            r4 = 0
            goto L_0x0067
        L_0x0066:
            r4 = 1
        L_0x0067:
            if (r4 == 0) goto L_0x006c
            int r12 = r8.outHeight
            goto L_0x006e
        L_0x006c:
            int r12 = r8.outWidth
        L_0x006e:
            if (r4 == 0) goto L_0x0073
            int r4 = r8.outWidth
            goto L_0x0075
        L_0x0073:
            int r4 = r8.outHeight
        L_0x0075:
            r13 = r30
            android.graphics.Bitmap$Config r14 = r7.computeConfig(r8, r13, r6, r11)
            r8.inPreferredConfig = r14
            int r14 = android.os.Build.VERSION.SDK_INT
            r15 = 26
            if (r14 < r15) goto L_0x008f
            android.graphics.ColorSpace r14 = r30.getColorSpace()
            if (r14 == 0) goto L_0x008f
            android.graphics.ColorSpace r14 = r30.getColorSpace()
            r8.inPreferredColorSpace = r14
        L_0x008f:
            int r14 = android.os.Build.VERSION.SDK_INT
            if (r14 < r15) goto L_0x009c
            android.graphics.Bitmap$Config r14 = r8.inPreferredConfig
            android.graphics.Bitmap$Config r15 = android.graphics.Bitmap.Config.HARDWARE
            if (r14 == r15) goto L_0x009a
            goto L_0x009c
        L_0x009a:
            r14 = 0
            goto L_0x009d
        L_0x009c:
            r14 = 1
        L_0x009d:
            r8.inMutable = r14
            r8.inScaled = r10
            int r14 = r8.outWidth
            java.lang.String r15 = "inPreferredConfig"
            if (r14 <= 0) goto L_0x0189
            int r14 = r8.outHeight
            if (r14 > 0) goto L_0x00ad
            goto L_0x0189
        L_0x00ad:
            boolean r14 = r0 instanceof coil.size.PixelSize
            if (r14 != 0) goto L_0x00ce
            r8.inSampleSize = r9
            r8.inScaled = r10
            boolean r0 = r8.inMutable
            if (r0 == 0) goto L_0x00c8
            int r0 = r8.outWidth
            int r4 = r8.outHeight
            android.graphics.Bitmap$Config r12 = r8.inPreferredConfig
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r15)
            android.graphics.Bitmap r0 = r2.getDirty(r0, r4, r12)
            r8.inBitmap = r0
        L_0x00c8:
            r0 = r5
            r14 = r6
            r16 = r11
            goto L_0x0197
        L_0x00ce:
            coil.size.PixelSize r0 = (coil.size.PixelSize) r0
            int r14 = r0.component1()
            int r0 = r0.component2()
            coil.size.Scale r5 = r30.getScale()
            int r5 = coil.decode.DecodeUtils.calculateInSampleSize(r12, r4, r14, r0, r5)
            r8.inSampleSize = r5
            r16 = r11
            double r10 = (double) r12
            int r5 = r8.inSampleSize
            double r12 = (double) r5
            double r17 = r10 / r12
            double r4 = (double) r4
            int r10 = r8.inSampleSize
            double r10 = (double) r10
            double r19 = r4 / r10
            double r4 = (double) r14
            double r10 = (double) r0
            coil.size.Scale r25 = r30.getScale()
            r21 = r4
            r23 = r10
            double r4 = coil.decode.DecodeUtils.computeSizeMultiplier((double) r17, (double) r19, (double) r21, (double) r23, (coil.size.Scale) r25)
            boolean r0 = r30.getAllowInexactSize()
            r10 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r0 == 0) goto L_0x010a
            double r4 = kotlin.ranges.RangesKt.coerceAtMost((double) r4, (double) r10)
        L_0x010a:
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0110
            r0 = 1
            goto L_0x0111
        L_0x0110:
            r0 = 0
        L_0x0111:
            r8.inScaled = r0
            boolean r0 = r8.inScaled
            if (r0 == 0) goto L_0x0135
            double r10 = (double) r9
            r0 = 2147483647(0x7fffffff, float:NaN)
            int r12 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r12 <= 0) goto L_0x012a
            double r10 = (double) r0
            double r10 = r10 / r4
            int r10 = kotlin.math.MathKt.roundToInt((double) r10)
            r8.inDensity = r10
            r8.inTargetDensity = r0
            goto L_0x0135
        L_0x012a:
            r8.inDensity = r0
            double r10 = (double) r0
            double r10 = r10 * r4
            int r0 = kotlin.math.MathKt.roundToInt((double) r10)
            r8.inTargetDensity = r0
        L_0x0135:
            boolean r0 = r8.inMutable
            if (r0 == 0) goto L_0x0186
            int r0 = r8.inSampleSize
            if (r0 != r9) goto L_0x0150
            boolean r0 = r8.inScaled
            if (r0 != 0) goto L_0x0150
            int r0 = r8.outWidth
            int r4 = r8.outHeight
            android.graphics.Bitmap$Config r5 = r8.inPreferredConfig
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r15)
            android.graphics.Bitmap r0 = r2.getDirty(r0, r4, r5)
            r14 = r6
            goto L_0x0183
        L_0x0150:
            int r0 = android.os.Build.VERSION.SDK_INT
            r10 = 19
            if (r0 < r10) goto L_0x0181
            int r0 = r8.outWidth
            double r10 = (double) r0
            int r0 = r8.inSampleSize
            double r12 = (double) r0
            double r10 = r10 / r12
            int r0 = r8.outHeight
            double r12 = (double) r0
            int r0 = r8.inSampleSize
            r14 = r6
            double r6 = (double) r0
            double r12 = r12 / r6
            double r10 = r10 * r4
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r10 = r10 + r6
            double r10 = java.lang.Math.ceil(r10)
            int r0 = (int) r10
            double r4 = r4 * r12
            double r4 = r4 + r6
            double r4 = java.lang.Math.ceil(r4)
            int r4 = (int) r4
            android.graphics.Bitmap$Config r5 = r8.inPreferredConfig
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r15)
            android.graphics.Bitmap r0 = r2.getDirty(r0, r4, r5)
            goto L_0x0183
        L_0x0181:
            r14 = r6
            r0 = 0
        L_0x0183:
            r8.inBitmap = r0
            goto L_0x0187
        L_0x0186:
            r14 = r6
        L_0x0187:
            r0 = 0
            goto L_0x0197
        L_0x0189:
            r14 = r6
            r16 = r11
            r8.inSampleSize = r9
            r0 = 0
            r8.inScaled = r0
            r0 = 0
            r5 = r0
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            r8.inBitmap = r5
        L_0x0197:
            android.graphics.Bitmap r4 = r8.inBitmap
            r5 = r0
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ all -> 0x0219 }
            r6 = r0
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x0219 }
            r7 = r3
            okio.BufferedSource r7 = (okio.BufferedSource) r7     // Catch:{ all -> 0x020c }
            java.io.InputStream r7 = r7.inputStream()     // Catch:{ all -> 0x020c }
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r7, r0, r8)     // Catch:{ all -> 0x020c }
            kotlin.io.CloseableKt.closeFinally(r3, r6)     // Catch:{ all -> 0x0219 }
            java.lang.Exception r0 = r1.getException()     // Catch:{ all -> 0x0207 }
            if (r0 != 0) goto L_0x0200
            if (r7 == 0) goto L_0x01f0
            android.graphics.Bitmap$Config r4 = r8.inPreferredConfig
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r15)
            r1 = r26
            r2 = r27
            r3 = r7
            r5 = r14
            r6 = r16
            android.graphics.Bitmap r0 = r1.applyExifTransformations(r2, r3, r4, r5, r6)
            r1 = 0
            r0.setDensity(r1)
            r6 = r26
            android.content.Context r2 = r6.context
            android.content.res.Resources r2 = r2.getResources()
            java.lang.String r3 = "context.resources"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            android.graphics.drawable.BitmapDrawable r3 = new android.graphics.drawable.BitmapDrawable
            r3.<init>(r2, r0)
            android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
            int r0 = r8.inSampleSize
            if (r0 > r9) goto L_0x01ea
            boolean r0 = r8.inScaled
            if (r0 == 0) goto L_0x01e9
            goto L_0x01ea
        L_0x01e9:
            r9 = 0
        L_0x01ea:
            coil.decode.DecodeResult r0 = new coil.decode.DecodeResult
            r0.<init>(r3, r9)
            return r0
        L_0x01f0:
            r6 = r26
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "BitmapFactory returned a null Bitmap. Often this means BitmapFactory could not decode the image data read from the input source (e.g. network or disk) as it's not encoded as a valid image format."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0200:
            r6 = r26
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0205 }
            throw r0     // Catch:{ all -> 0x0205 }
        L_0x0205:
            r0 = move-exception
            goto L_0x020a
        L_0x0207:
            r0 = move-exception
            r6 = r26
        L_0x020a:
            r5 = r7
            goto L_0x021c
        L_0x020c:
            r0 = move-exception
            r6 = r26
            r1 = r0
            throw r1     // Catch:{ all -> 0x0211 }
        L_0x0211:
            r0 = move-exception
            r7 = r0
            kotlin.io.CloseableKt.closeFinally(r3, r1)     // Catch:{ all -> 0x0217 }
            throw r7     // Catch:{ all -> 0x0217 }
        L_0x0217:
            r0 = move-exception
            goto L_0x021c
        L_0x0219:
            r0 = move-exception
            r6 = r26
        L_0x021c:
            if (r4 == 0) goto L_0x0221
            r2.put(r4)
        L_0x0221:
            if (r5 == r4) goto L_0x0228
            if (r5 == 0) goto L_0x0228
            r2.put(r5)
        L_0x0228:
            throw r0
        L_0x0229:
            r6 = r7
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.decode.BitmapFactoryDecoder.decode(coil.bitmappool.BitmapPool, okio.BufferedSource, coil.size.Size, coil.decode.Options, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final boolean shouldReadExifData(String str) {
        return str != null && ArraysKt.contains((T[]) SUPPORTED_EXIF_MIME_TYPES, str);
    }

    private final Bitmap.Config computeConfig(BitmapFactory.Options options, Options options2, boolean z, int i) {
        Bitmap.Config config = options2.getConfig();
        if (z || i > 0) {
            config = Bitmaps.toSoftware(config);
        }
        if (options2.getAllowRgb565() && config == Bitmap.Config.ARGB_8888 && Intrinsics.areEqual((Object) options.outMimeType, (Object) MIME_TYPE_JPEG)) {
            config = Bitmap.Config.RGB_565;
        }
        return (Build.VERSION.SDK_INT < 26 || options.outConfig != Bitmap.Config.RGBA_F16 || config == Bitmap.Config.HARDWARE) ? config : Bitmap.Config.RGBA_F16;
    }

    private final Bitmap applyExifTransformations(BitmapPool bitmapPool, Bitmap bitmap, Bitmap.Config config, boolean z, int i) {
        Bitmap bitmap2;
        boolean z2 = i > 0;
        if (!z && !z2) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        float width = ((float) bitmap.getWidth()) / 2.0f;
        float height = ((float) bitmap.getHeight()) / 2.0f;
        if (z) {
            matrix.postScale(-1.0f, 1.0f, width, height);
        }
        if (z2) {
            matrix.postRotate((float) i, width, height);
        }
        RectF rectF = new RectF(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        matrix.mapRect(rectF);
        if (!(rectF.left == 0.0f && rectF.top == 0.0f)) {
            matrix.postTranslate(-rectF.left, -rectF.top);
        }
        if (i == 90 || i == 270) {
            bitmap2 = bitmapPool.get(bitmap.getHeight(), bitmap.getWidth(), config);
        } else {
            bitmap2 = bitmapPool.get(bitmap.getWidth(), bitmap.getHeight(), config);
        }
        new Canvas(bitmap2).drawBitmap(bitmap, matrix, this.paint);
        bitmapPool.put(bitmap);
        return bitmap2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\fH\u0016R.\u0010\b\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u00072\u000e\u0010\u0005\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder$ExceptionCatchingSource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "(Lokio/Source;)V", "<set-?>", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "getException", "()Ljava/lang/Exception;", "read", "", "sink", "Lokio/Buffer;", "byteCount", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BitmapFactoryDecoder.kt */
    private static final class ExceptionCatchingSource extends ForwardingSource {
        private Exception exception;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ExceptionCatchingSource(Source source) {
            super(source);
            Intrinsics.checkParameterIsNotNull(source, MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX);
        }

        public final Exception getException() {
            return this.exception;
        }

        public long read(Buffer buffer, long j) {
            Intrinsics.checkParameterIsNotNull(buffer, "sink");
            try {
                return super.read(buffer, j);
            } catch (Exception e) {
                this.exception = e;
                throw e;
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u0005H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\b\u0010\u0011\u001a\u00020\u0007H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcoil/decode/BitmapFactoryDecoder$ExifInterfaceInputStream;", "Ljava/io/InputStream;", "delegate", "(Ljava/io/InputStream;)V", "available", "", "close", "", "mark", "readlimit", "markSupported", "", "read", "b", "", "off", "len", "reset", "skip", "", "n", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BitmapFactoryDecoder.kt */
    private static final class ExifInterfaceInputStream extends InputStream {
        private final InputStream delegate;

        public int available() {
            return 1073741824;
        }

        public ExifInterfaceInputStream(InputStream inputStream) {
            Intrinsics.checkParameterIsNotNull(inputStream, MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX);
            this.delegate = inputStream;
        }

        public int read() {
            return this.delegate.read();
        }

        public int read(byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "b");
            return this.delegate.read(bArr);
        }

        public int read(byte[] bArr, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(bArr, "b");
            return this.delegate.read(bArr, i, i2);
        }

        public long skip(long j) {
            return this.delegate.skip(j);
        }

        public void close() {
            this.delegate.close();
        }

        public void mark(int i) {
            this.delegate.mark(i);
        }

        public void reset() {
            this.delegate.reset();
        }

        public boolean markSupported() {
            return this.delegate.markSupported();
        }
    }
}

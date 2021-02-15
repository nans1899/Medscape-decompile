package com.medscape.android.util.media;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.util.NumberUtil;
import com.medscape.android.util.ViewHelper;
import com.medscape.android.view.ViewCompat;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

public class BitmapLoader {
    public static final int MAX_BITMAP_SIZE;
    public static final int MAX_IMAGE_HEIGHT;
    public static final int MAX_IMAGE_WIDTH = MedscapeApplication.get().getResources().getDisplayMetrics().widthPixels;
    public static final int TYPE_RESOURCE = 0;
    public static final int TYPE_URI = 1;
    public static HashMap<String, Set<String>> sUrlToBitmapMap = new HashMap<>();

    static {
        int i = MedscapeApplication.get().getResources().getDisplayMetrics().widthPixels;
        MAX_IMAGE_HEIGHT = i;
        MAX_BITMAP_SIZE = MAX_IMAGE_WIDTH * i * 4;
    }

    public static Bitmap getBitmapFromAsset(View view, Object obj, int i) {
        return getSampledBitmapFromAsset(view, obj, i, (int[]) null).getBitmap();
    }

    public static SampledBitmap getSampledBitmapFromAsset(View view, Object obj, int i, int[] iArr) {
        return getSampledBitmapFromAsset(view, obj, obj.toString(), i, iArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x012c A[Catch:{ Exception -> 0x0159 }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x015f A[SYNTHETIC, Splitter:B:66:0x015f] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x016b A[SYNTHETIC, Splitter:B:72:0x016b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.medscape.android.util.media.SampledBitmap getSampledBitmapFromAsset(android.view.View r16, java.lang.Object r17, java.lang.String r18, int r19, int[] r20) {
        /*
            r0 = r16
            r1 = r19
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0025
            android.content.Context r4 = r16.getContext()     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            android.content.res.Resources r4 = r4.getResources()     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            r5 = r17
            java.lang.Integer r5 = (java.lang.Integer) r5     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            int r5 = r5.intValue()     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            java.io.InputStream r4 = r4.openRawResource(r5)     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            goto L_0x003b
        L_0x001d:
            r0 = move-exception
            r1 = r0
            goto L_0x0169
        L_0x0021:
            r0 = move-exception
            r4 = r2
            goto L_0x015a
        L_0x0025:
            if (r1 != r3) goto L_0x003a
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            java.io.File r5 = new java.io.File     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            r6 = r17
            android.net.Uri r6 = (android.net.Uri) r6     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            java.lang.String r6 = r6.getPath()     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            r5.<init>(r6)     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0021, all -> 0x001d }
            goto L_0x003b
        L_0x003a:
            r4 = r2
        L_0x003b:
            android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options     // Catch:{ Exception -> 0x0159 }
            r5.<init>()     // Catch:{ Exception -> 0x0159 }
            java.io.BufferedInputStream r6 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0159 }
            r6.<init>(r4)     // Catch:{ Exception -> 0x0159 }
            getBitmapDimensions(r6, r5)     // Catch:{ Exception -> 0x0159 }
            r6 = 0
            if (r20 != 0) goto L_0x00ab
            r7 = 2
            int[] r7 = new int[r7]     // Catch:{ Exception -> 0x0159 }
            getEstimatedImageDimensions(r0, r7)     // Catch:{ Exception -> 0x0159 }
            boolean r8 = r0 instanceof android.widget.ImageView     // Catch:{ Exception -> 0x0159 }
            if (r8 == 0) goto L_0x0060
            r8 = r0
            android.widget.ImageView r8 = (android.widget.ImageView) r8     // Catch:{ Exception -> 0x0159 }
            android.widget.ImageView$ScaleType r8 = r8.getScaleType()     // Catch:{ Exception -> 0x0159 }
            android.widget.ImageView$ScaleType r9 = android.widget.ImageView.ScaleType.CENTER_CROP     // Catch:{ Exception -> 0x0159 }
            if (r8 == r9) goto L_0x006b
        L_0x0060:
            r8 = r0
            android.widget.ImageView r8 = (android.widget.ImageView) r8     // Catch:{ Exception -> 0x0159 }
            android.widget.ImageView$ScaleType r8 = r8.getScaleType()     // Catch:{ Exception -> 0x0159 }
            android.widget.ImageView$ScaleType r9 = android.widget.ImageView.ScaleType.CENTER_INSIDE     // Catch:{ Exception -> 0x0159 }
            if (r8 != r9) goto L_0x00ad
        L_0x006b:
            int r8 = r5.outHeight     // Catch:{ Exception -> 0x0159 }
            int r9 = r5.outWidth     // Catch:{ Exception -> 0x0159 }
            r10 = r7[r6]     // Catch:{ Exception -> 0x0159 }
            r11 = r7[r3]     // Catch:{ Exception -> 0x0159 }
            r12 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r10 <= r11) goto L_0x008e
            r10 = r7[r3]     // Catch:{ Exception -> 0x0159 }
            double r10 = (double) r10     // Catch:{ Exception -> 0x0159 }
            r14 = r7[r6]     // Catch:{ Exception -> 0x0159 }
            double r14 = (double) r14     // Catch:{ Exception -> 0x0159 }
            double r10 = r10 / r14
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x008e
            r10 = r7[r6]     // Catch:{ Exception -> 0x0159 }
            double r10 = (double) r10     // Catch:{ Exception -> 0x0159 }
            double r12 = (double) r9     // Catch:{ Exception -> 0x0159 }
            double r10 = r10 / r12
            double r8 = (double) r8     // Catch:{ Exception -> 0x0159 }
            double r8 = r8 * r10
            int r8 = (int) r8     // Catch:{ Exception -> 0x0159 }
            r7[r3] = r8     // Catch:{ Exception -> 0x0159 }
            goto L_0x00ad
        L_0x008e:
            r10 = r7[r3]     // Catch:{ Exception -> 0x0159 }
            r11 = r7[r6]     // Catch:{ Exception -> 0x0159 }
            if (r10 <= r11) goto L_0x00ad
            r10 = r7[r6]     // Catch:{ Exception -> 0x0159 }
            double r10 = (double) r10     // Catch:{ Exception -> 0x0159 }
            r14 = r7[r3]     // Catch:{ Exception -> 0x0159 }
            double r14 = (double) r14     // Catch:{ Exception -> 0x0159 }
            double r10 = r10 / r14
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 >= 0) goto L_0x00ad
            r10 = r7[r3]     // Catch:{ Exception -> 0x0159 }
            double r10 = (double) r10     // Catch:{ Exception -> 0x0159 }
            double r12 = (double) r8     // Catch:{ Exception -> 0x0159 }
            double r10 = r10 / r12
            double r8 = (double) r9     // Catch:{ Exception -> 0x0159 }
            double r8 = r8 * r10
            int r8 = (int) r8     // Catch:{ Exception -> 0x0159 }
            r7[r6] = r8     // Catch:{ Exception -> 0x0159 }
            goto L_0x00ad
        L_0x00ab:
            r7 = r20
        L_0x00ad:
            r5.inDither = r6     // Catch:{ Exception -> 0x0159 }
            r8 = r7[r6]     // Catch:{ Exception -> 0x0159 }
            r7 = r7[r3]     // Catch:{ Exception -> 0x0159 }
            int r7 = calculateInSampleSize(r5, r8, r7)     // Catch:{ Exception -> 0x0159 }
            r5.inSampleSize = r7     // Catch:{ Exception -> 0x0159 }
            r5.inPurgeable = r3     // Catch:{ Exception -> 0x0159 }
            r5.inInputShareable = r3     // Catch:{ Exception -> 0x0159 }
            com.medscape.android.MedscapeApplication r7 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x0159 }
            r8 = r18
            com.medscape.android.util.media.SampledBitmap r7 = r7.getSampledBitmap(r8)     // Catch:{ Exception -> 0x0159 }
            if (r7 == 0) goto L_0x00d6
            int r8 = r7.getSampleSize()     // Catch:{ Exception -> 0x0159 }
            int r9 = r5.inSampleSize     // Catch:{ Exception -> 0x0159 }
            if (r8 > r9) goto L_0x00d6
            r4.close()     // Catch:{ Exception -> 0x0159 }
            goto L_0x0149
        L_0x00d6:
            r5.inJustDecodeBounds = r6     // Catch:{ Exception -> 0x0159 }
            r4.close()     // Catch:{ Exception -> 0x0159 }
            if (r1 != 0) goto L_0x00f3
            android.content.Context r0 = r16.getContext()     // Catch:{ Exception -> 0x0159 }
            android.content.res.Resources r0 = r0.getResources()     // Catch:{ Exception -> 0x0159 }
            r6 = r17
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch:{ Exception -> 0x0159 }
            int r6 = r6.intValue()     // Catch:{ Exception -> 0x0159 }
            java.io.InputStream r0 = r0.openRawResource(r6)     // Catch:{ Exception -> 0x0159 }
        L_0x00f1:
            r4 = r0
            goto L_0x0109
        L_0x00f3:
            if (r1 != r3) goto L_0x0108
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0159 }
            java.io.File r6 = new java.io.File     // Catch:{ Exception -> 0x0159 }
            r7 = r17
            android.net.Uri r7 = (android.net.Uri) r7     // Catch:{ Exception -> 0x0159 }
            java.lang.String r7 = r7.getPath()     // Catch:{ Exception -> 0x0159 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0159 }
            r0.<init>(r6)     // Catch:{ Exception -> 0x0159 }
            goto L_0x00f1
        L_0x0108:
            r4 = r2
        L_0x0109:
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeStream(r4, r2, r5)     // Catch:{ Exception -> 0x0159 }
            if (r6 == 0) goto L_0x013f
            if (r1 != r3) goto L_0x013f
            android.media.ExifInterface r0 = new android.media.ExifInterface     // Catch:{ StringIndexOutOfBoundsException -> 0x0125 }
            r1 = r17
            android.net.Uri r1 = (android.net.Uri) r1     // Catch:{ StringIndexOutOfBoundsException -> 0x0125 }
            java.lang.String r1 = r1.getPath()     // Catch:{ StringIndexOutOfBoundsException -> 0x0125 }
            r0.<init>(r1)     // Catch:{ StringIndexOutOfBoundsException -> 0x0125 }
            java.lang.String r1 = "Orientation"
            int r0 = r0.getAttributeInt(r1, r3)     // Catch:{ StringIndexOutOfBoundsException -> 0x0125 }
            goto L_0x012a
        L_0x0125:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Exception -> 0x0159 }
            r0 = 1
        L_0x012a:
            if (r0 <= r3) goto L_0x013f
            android.graphics.Matrix r11 = getRectifyingMatrixForExifOrientation(r0)     // Catch:{ Exception -> 0x0159 }
            r7 = 0
            r8 = 0
            int r9 = r6.getWidth()     // Catch:{ Exception -> 0x0159 }
            int r10 = r6.getHeight()     // Catch:{ Exception -> 0x0159 }
            r12 = 1
            android.graphics.Bitmap r6 = android.graphics.Bitmap.createBitmap(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0159 }
        L_0x013f:
            r4.close()     // Catch:{ Exception -> 0x0159 }
            com.medscape.android.util.media.SampledBitmap r7 = new com.medscape.android.util.media.SampledBitmap     // Catch:{ Exception -> 0x0159 }
            int r0 = r5.inSampleSize     // Catch:{ Exception -> 0x0159 }
            r7.<init>(r6, r0)     // Catch:{ Exception -> 0x0159 }
        L_0x0149:
            if (r4 == 0) goto L_0x0154
            r4.close()     // Catch:{ IOException -> 0x014f }
            goto L_0x0154
        L_0x014f:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x0154:
            return r7
        L_0x0155:
            r0 = move-exception
            r1 = r0
            r2 = r4
            goto L_0x0169
        L_0x0159:
            r0 = move-exception
        L_0x015a:
            r0.printStackTrace()     // Catch:{ all -> 0x0155 }
            if (r4 == 0) goto L_0x0168
            r4.close()     // Catch:{ IOException -> 0x0163 }
            goto L_0x0168
        L_0x0163:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()
        L_0x0168:
            return r2
        L_0x0169:
            if (r2 == 0) goto L_0x0174
            r2.close()     // Catch:{ IOException -> 0x016f }
            goto L_0x0174
        L_0x016f:
            r0 = move-exception
            r2 = r0
            r2.printStackTrace()
        L_0x0174:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.media.BitmapLoader.getSampledBitmapFromAsset(android.view.View, java.lang.Object, java.lang.String, int, int[]):com.medscape.android.util.media.SampledBitmap");
    }

    private static void getEstimatedImageDimensions(View view, int[] iArr) {
        if (view.getHeight() == 0 || view.getWidth() == 0) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            ViewGroup.LayoutParams layoutParams2 = ((View) view.getParent()).getLayoutParams();
            view.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, (layoutParams.width != -1 || layoutParams2 == null) ? layoutParams.width : layoutParams2.width), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, (layoutParams.height != -1 || layoutParams2 == null) ? layoutParams.height : layoutParams2.height));
            iArr[0] = view.getMeasuredWidth() > 0 ? view.getMeasuredWidth() : MAX_IMAGE_WIDTH;
            iArr[1] = view.getMeasuredHeight() > 0 ? view.getMeasuredHeight() : MAX_IMAGE_HEIGHT;
            return;
        }
        iArr[0] = view.getWidth();
        iArr[1] = view.getHeight();
    }

    public static void getBitmapDimensions(InputStream inputStream, BitmapFactory.Options options) {
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, (Rect) null, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3;
        int i4 = options.outHeight;
        int i5 = options.outWidth;
        int i6 = 1;
        if (i4 > i2 || i5 > i) {
            i3 = Math.max(Math.min(Math.round(((float) i4) / ((float) i2)), Math.round(((float) i5) / ((float) i))), 1);
        } else {
            i3 = 1;
        }
        int log2 = NumberUtil.log2(Integer.highestOneBit(i3));
        if (Math.abs(i3 - ((int) Math.pow(2.0d, (double) log2))) < Math.abs(i3 - ((int) Math.pow(2.0d, (double) (log2 + 1))))) {
            i6 = 0;
        }
        return calculateClampedSampleSize((int) Math.pow(2.0d, (double) (log2 + i6)), i5, i4);
    }

    private static int calculateClampedSampleSize(int i, int i2, int i3) {
        int i4 = i2 * i3 * 4;
        int i5 = i4 / (i * i);
        int i6 = MAX_BITMAP_SIZE;
        if (i5 <= i6) {
            return i;
        }
        double ceil = (double) ((int) Math.ceil(Math.sqrt(((double) i4) / ((double) i6))));
        int log2 = NumberUtil.log2(Integer.highestOneBit((int) ceil));
        return (int) Math.pow(2.0d, (double) (log2 + (Math.abs(ceil - ((double) ((int) Math.pow(2.0d, (double) log2)))) < Math.abs(ceil - ((double) ((int) Math.pow(2.0d, (double) (log2 + 1))))) ? 0 : 1)));
    }

    private static Matrix getRectifyingMatrixForExifOrientation(int i) {
        Matrix matrix = new Matrix();
        if (i == 2) {
            matrix.setScale(-1.0f, 1.0f);
        } else if (i == 3) {
            matrix.postRotate(180.0f);
        } else if (i == 4) {
            matrix.setScale(1.0f, -1.0f);
        } else if (i == 6) {
            matrix.postRotate(90.0f);
        } else if (i == 8) {
            matrix.postRotate(270.0f);
        }
        return matrix;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void trackBitmap(android.view.View r4, java.lang.String r5, com.medscape.android.util.media.SampledBitmap r6, boolean r7) {
        /*
            java.lang.Class<com.medscape.android.util.media.BitmapLoader> r0 = com.medscape.android.util.media.BitmapLoader.class
            monitor-enter(r0)
            if (r6 == 0) goto L_0x004d
            android.graphics.Bitmap r1 = r6.getBitmap()     // Catch:{ all -> 0x004a }
            if (r1 != 0) goto L_0x000c
            goto L_0x004d
        L_0x000c:
            java.util.HashMap<java.lang.String, java.util.Set<java.lang.String>> r1 = sUrlToBitmapMap     // Catch:{ all -> 0x004a }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x001d
            java.util.HashMap<java.lang.String, java.util.Set<java.lang.String>> r1 = sUrlToBitmapMap     // Catch:{ all -> 0x004a }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x004a }
            java.util.Set r1 = (java.util.Set) r1     // Catch:{ all -> 0x004a }
            goto L_0x0022
        L_0x001d:
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x004a }
            r1.<init>()     // Catch:{ all -> 0x004a }
        L_0x0022:
            java.lang.String r4 = com.medscape.android.util.ViewHelper.getUniqueId(r4)     // Catch:{ all -> 0x004a }
            r1.add(r4)     // Catch:{ all -> 0x004a }
            java.util.HashMap<java.lang.String, java.util.Set<java.lang.String>> r4 = sUrlToBitmapMap     // Catch:{ all -> 0x004a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004a }
            r2.<init>()     // Catch:{ all -> 0x004a }
            r2.append(r5)     // Catch:{ all -> 0x004a }
            java.lang.String r3 = ""
            r2.append(r3)     // Catch:{ all -> 0x004a }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x004a }
            r4.put(r2, r1)     // Catch:{ all -> 0x004a }
            if (r7 == 0) goto L_0x0048
            com.medscape.android.MedscapeApplication r4 = com.medscape.android.MedscapeApplication.get()     // Catch:{ all -> 0x004a }
            r4.addSampledBitmapToMemory(r5, r6)     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r0)
            return
        L_0x004a:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        L_0x004d:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.media.BitmapLoader.trackBitmap(android.view.View, java.lang.String, com.medscape.android.util.media.SampledBitmap, boolean):void");
    }

    public static synchronized void cleanupBitmap(String str, View view) {
        Bitmap bitmap;
        synchronized (BitmapLoader.class) {
            Set set = sUrlToBitmapMap.get(str);
            if (set != null) {
                set.remove(ViewHelper.getUniqueId(view));
                if (set.isEmpty()) {
                    if (view instanceof ImageView) {
                        Drawable drawable = ((ImageView) view).getDrawable();
                        if ((drawable instanceof BitmapDrawable) && ((BitmapDrawable) drawable).getBitmap() != null) {
                            ((ImageView) view).setImageBitmap((Bitmap) null);
                            ((ImageView) view).setImageDrawable((Drawable) null);
                            ((BitmapDrawable) drawable).getBitmap().recycle();
                        }
                    } else if ((view.getBackground() instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) view.getBackground()).getBitmap()) != null) {
                        ViewCompat.setBackground(view, (Drawable) null);
                        bitmap.recycle();
                    }
                    sUrlToBitmapMap.remove(str);
                    MedscapeApplication.get().removeSampledBitmap(str);
                }
            }
        }
    }
}

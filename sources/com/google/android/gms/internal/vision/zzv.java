package com.google.android.gms.internal.vision;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzv {
    public static Bitmap zzb(Bitmap bitmap, zzu zzu) {
        int i;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (zzu.rotation != 0) {
            Matrix matrix = new Matrix();
            int i2 = zzu.rotation;
            if (i2 == 0) {
                i = 0;
            } else if (i2 == 1) {
                i = 90;
            } else if (i2 == 2) {
                i = 180;
            } else if (i2 == 3) {
                i = 270;
            } else {
                throw new IllegalArgumentException("Unsupported rotation degree.");
            }
            matrix.postRotate((float) i);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        }
        if (zzu.rotation == 1 || zzu.rotation == 3) {
            zzu.width = height;
            zzu.height = width;
        }
        return bitmap;
    }
}

package com.google.android.gms.vision.text;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzah;
import com.google.android.gms.internal.vision.zzaj;
import com.google.android.gms.internal.vision.zzam;
import com.google.android.gms.internal.vision.zzan;
import com.google.android.gms.internal.vision.zzu;
import com.google.android.gms.internal.vision.zzv;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class TextRecognizer extends Detector<TextBlock> {
    private final zzan zzel;

    private TextRecognizer() {
        throw new IllegalStateException("Default constructor called");
    }

    private TextRecognizer(zzan zzan) {
        this.zzel = zzan;
    }

    /* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
    public static class Builder {
        private zzam zzem = new zzam();
        private Context zzg;

        public Builder(Context context) {
            this.zzg = context;
        }

        public TextRecognizer build() {
            return new TextRecognizer(new zzan(this.zzg, this.zzem));
        }
    }

    public final SparseArray<TextBlock> detect(Frame frame) {
        Bitmap bitmap;
        byte[] bArr;
        zzaj zzaj = new zzaj(new Rect());
        if (frame != null) {
            zzu zzd = zzu.zzd(frame);
            if (frame.getBitmap() != null) {
                bitmap = frame.getBitmap();
            } else {
                Frame.Metadata metadata = frame.getMetadata();
                ByteBuffer grayscaleImageData = frame.getGrayscaleImageData();
                int format = metadata.getFormat();
                int i = zzd.width;
                int i2 = zzd.height;
                if (!grayscaleImageData.hasArray() || grayscaleImageData.arrayOffset() != 0) {
                    byte[] bArr2 = new byte[grayscaleImageData.capacity()];
                    grayscaleImageData.get(bArr2);
                    bArr = bArr2;
                } else {
                    bArr = grayscaleImageData.array();
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                new YuvImage(bArr, format, i, i2, (int[]) null).compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            Bitmap zzb = zzv.zzb(bitmap, zzd);
            if (!zzaj.zzex.isEmpty()) {
                Rect rect = zzaj.zzex;
                int width = frame.getMetadata().getWidth();
                int height = frame.getMetadata().getHeight();
                int i3 = zzd.rotation;
                if (i3 == 1) {
                    rect = new Rect(height - rect.bottom, rect.left, height - rect.top, rect.right);
                } else if (i3 == 2) {
                    rect = new Rect(width - rect.right, height - rect.bottom, width - rect.left, height - rect.top);
                } else if (i3 == 3) {
                    rect = new Rect(rect.top, width - rect.right, rect.bottom, width - rect.left);
                }
                zzaj.zzex.set(rect);
            }
            zzd.rotation = 0;
            zzah[] zza = this.zzel.zza(zzb, zzd, zzaj);
            SparseArray sparseArray = new SparseArray();
            for (zzah zzah : zza) {
                SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzah.zzev);
                if (sparseArray2 == null) {
                    sparseArray2 = new SparseArray();
                    sparseArray.append(zzah.zzev, sparseArray2);
                }
                sparseArray2.append(zzah.zzew, zzah);
            }
            SparseArray<TextBlock> sparseArray3 = new SparseArray<>(sparseArray.size());
            for (int i4 = 0; i4 < sparseArray.size(); i4++) {
                sparseArray3.append(sparseArray.keyAt(i4), new TextBlock((SparseArray) sparseArray.valueAt(i4)));
            }
            return sparseArray3;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    public final boolean isOperational() {
        return this.zzel.isOperational();
    }

    public final void release() {
        super.release();
        this.zzel.zzq();
    }
}

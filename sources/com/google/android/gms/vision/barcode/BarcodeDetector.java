package com.google.android.gms.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzk;
import com.google.android.gms.internal.vision.zzm;
import com.google.android.gms.internal.vision.zzu;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;

/* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
public final class BarcodeDetector extends Detector<Barcode> {
    private final zzm zzbr;

    private BarcodeDetector() {
        throw new IllegalStateException("Default constructor called");
    }

    private BarcodeDetector(zzm zzm) {
        this.zzbr = zzm;
    }

    /* compiled from: com.google.android.gms:play-services-vision@@20.1.2 */
    public static class Builder {
        private zzk zzbs = new zzk();
        private Context zzg;

        public Builder(Context context) {
            this.zzg = context;
        }

        public Builder setBarcodeFormats(int i) {
            this.zzbs.zzbt = i;
            return this;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector(new zzm(this.zzg, this.zzbs));
        }
    }

    public final void release() {
        super.release();
        this.zzbr.zzq();
    }

    public final SparseArray<Barcode> detect(Frame frame) {
        Barcode[] barcodeArr;
        if (frame != null) {
            zzu zzd = zzu.zzd(frame);
            if (frame.getBitmap() != null) {
                barcodeArr = this.zzbr.zza(frame.getBitmap(), zzd);
                if (barcodeArr == null) {
                    throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
                }
            } else {
                barcodeArr = this.zzbr.zza(frame.getGrayscaleImageData(), zzd);
            }
            SparseArray<Barcode> sparseArray = new SparseArray<>(barcodeArr.length);
            for (Barcode barcode : barcodeArr) {
                sparseArray.append(barcode.rawValue.hashCode(), barcode);
            }
            return sparseArray;
        }
        throw new IllegalArgumentException("No frame supplied.");
    }

    public final boolean isOperational() {
        return this.zzbr.isOperational();
    }
}

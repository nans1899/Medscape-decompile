package com.google.android.gms.vision;

import android.util.SparseArray;
import com.google.android.gms.vision.Frame;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class Detector<T> {
    private final Object zzah = new Object();
    private Processor<T> zzai;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public interface Processor<T> {
        void receiveDetections(Detections<T> detections);

        void release();
    }

    public abstract SparseArray<T> detect(Frame frame);

    public boolean isOperational() {
        return true;
    }

    public boolean setFocus(int i) {
        return true;
    }

    public void release() {
        synchronized (this.zzah) {
            if (this.zzai != null) {
                this.zzai.release();
                this.zzai = null;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static class Detections<T> {
        private final SparseArray<T> zzal;
        private final Frame.Metadata zzam;
        private final boolean zzan;

        public Detections(SparseArray<T> sparseArray, Frame.Metadata metadata, boolean z) {
            this.zzal = sparseArray;
            this.zzam = metadata;
            this.zzan = z;
        }

        public SparseArray<T> getDetectedItems() {
            return this.zzal;
        }

        public Frame.Metadata getFrameMetadata() {
            return this.zzam;
        }

        public boolean detectorIsOperational() {
            return this.zzan;
        }
    }

    public void receiveFrame(Frame frame) {
        Frame.Metadata metadata = new Frame.Metadata(frame.getMetadata());
        metadata.zzf();
        Detections detections = new Detections(detect(frame), metadata, isOperational());
        synchronized (this.zzah) {
            if (this.zzai != null) {
                this.zzai.receiveDetections(detections);
            } else {
                throw new IllegalStateException("Detector processor must first be set with setProcessor in order to receive detection results.");
            }
        }
    }

    public void setProcessor(Processor<T> processor) {
        synchronized (this.zzah) {
            if (this.zzai != null) {
                this.zzai.release();
            }
            this.zzai = processor;
        }
    }
}

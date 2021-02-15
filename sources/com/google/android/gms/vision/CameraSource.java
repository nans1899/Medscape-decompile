package com.google.android.gms.vision;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.android.gms.common.images.Size;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public class CameraSource {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    /* access modifiers changed from: private */
    public int facing;
    /* access modifiers changed from: private */
    public int rotation;
    /* access modifiers changed from: private */
    public Context zzg;
    /* access modifiers changed from: private */
    public final Object zzh;
    /* access modifiers changed from: private */
    public Camera zzi;
    /* access modifiers changed from: private */
    public Size zzj;
    /* access modifiers changed from: private */
    public float zzk;
    /* access modifiers changed from: private */
    public int zzl;
    /* access modifiers changed from: private */
    public int zzm;
    /* access modifiers changed from: private */
    public boolean zzn;
    /* access modifiers changed from: private */
    public String zzo;
    private SurfaceTexture zzp;
    private boolean zzq;
    private Thread zzr;
    /* access modifiers changed from: private */
    public zza zzs;
    /* access modifiers changed from: private */
    public Map<byte[], ByteBuffer> zzt;

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public interface ShutterCallback {
        void onShutter();
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private class zzb implements Camera.PreviewCallback {
        private zzb() {
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.zzs.zza(bArr, camera);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private class zzc implements Camera.PictureCallback {
        /* access modifiers changed from: private */
        public PictureCallback zzaf;

        private zzc() {
        }

        public final void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.zzaf;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.zzh) {
                if (CameraSource.this.zzi != null) {
                    CameraSource.this.zzi.startPreview();
                }
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static class zzd implements Camera.ShutterCallback {
        /* access modifiers changed from: private */
        public ShutterCallback zzag;

        private zzd() {
        }

        public final void onShutter() {
            ShutterCallback shutterCallback = this.zzag;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    public void release() {
        synchronized (this.zzh) {
            stop();
            this.zzs.release();
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    static class zze {
        private Size zzaj;
        private Size zzak;

        public zze(Camera.Size size, @Nullable Camera.Size size2) {
            this.zzaj = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzak = new Size(size2.width, size2.height);
            }
        }

        public final Size zzd() {
            return this.zzaj;
        }

        @Nullable
        public final Size zze() {
            return this.zzak;
        }
    }

    public CameraSource start() throws IOException {
        synchronized (this.zzh) {
            if (this.zzi != null) {
                return this;
            }
            this.zzi = zzc();
            SurfaceTexture surfaceTexture = new SurfaceTexture(100);
            this.zzp = surfaceTexture;
            this.zzi.setPreviewTexture(surfaceTexture);
            this.zzq = true;
            this.zzi.startPreview();
            Thread thread = new Thread(this.zzs);
            this.zzr = thread;
            thread.setName("gms.vision.CameraSource");
            this.zzs.setActive(true);
            this.zzr.start();
            return this;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private class zza implements Runnable {
        private final Object lock = new Object();
        private boolean zzaa = true;
        private long zzab;
        private int zzac = 0;
        private ByteBuffer zzad;
        private Detector<?> zzx;
        private long zzz = SystemClock.elapsedRealtime();

        zza(Detector<?> detector) {
            this.zzx = detector;
        }

        /* access modifiers changed from: package-private */
        public final void release() {
            this.zzx.release();
            this.zzx = null;
        }

        /* access modifiers changed from: package-private */
        public final void setActive(boolean z) {
            synchronized (this.lock) {
                this.zzaa = z;
                this.lock.notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public final void zza(byte[] bArr, Camera camera) {
            synchronized (this.lock) {
                if (this.zzad != null) {
                    camera.addCallbackBuffer(this.zzad.array());
                    this.zzad = null;
                }
                if (!CameraSource.this.zzt.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.zzab = SystemClock.elapsedRealtime() - this.zzz;
                this.zzac++;
                this.zzad = (ByteBuffer) CameraSource.this.zzt.get(bArr);
                this.lock.notifyAll();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r6.zzx.receiveFrame(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0075, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0077, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            android.util.Log.e("CameraSource", "Exception thrown from receiver.", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x008e, code lost:
            com.google.android.gms.vision.CameraSource.zzb(r6.zzae).addCallbackBuffer(r2.array());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x009b, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r6 = this;
            L_0x0000:
                java.lang.Object r0 = r6.lock
                monitor-enter(r0)
            L_0x0003:
                boolean r1 = r6.zzaa     // Catch:{ all -> 0x009c }
                if (r1 == 0) goto L_0x001b
                java.nio.ByteBuffer r1 = r6.zzad     // Catch:{ all -> 0x009c }
                if (r1 != 0) goto L_0x001b
                java.lang.Object r1 = r6.lock     // Catch:{ InterruptedException -> 0x0011 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0011 }
                goto L_0x0003
            L_0x0011:
                r1 = move-exception
                java.lang.String r2 = "CameraSource"
                java.lang.String r3 = "Frame processing loop terminated."
                android.util.Log.d(r2, r3, r1)     // Catch:{ all -> 0x009c }
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                return
            L_0x001b:
                boolean r1 = r6.zzaa     // Catch:{ all -> 0x009c }
                if (r1 != 0) goto L_0x0021
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                return
            L_0x0021:
                com.google.android.gms.vision.Frame$Builder r1 = new com.google.android.gms.vision.Frame$Builder     // Catch:{ all -> 0x009c }
                r1.<init>()     // Catch:{ all -> 0x009c }
                java.nio.ByteBuffer r2 = r6.zzad     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.CameraSource r3 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x009c }
                com.google.android.gms.common.images.Size r3 = r3.zzj     // Catch:{ all -> 0x009c }
                int r3 = r3.getWidth()     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.CameraSource r4 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x009c }
                com.google.android.gms.common.images.Size r4 = r4.zzj     // Catch:{ all -> 0x009c }
                int r4 = r4.getHeight()     // Catch:{ all -> 0x009c }
                r5 = 17
                com.google.android.gms.vision.Frame$Builder r1 = r1.setImageData(r2, r3, r4, r5)     // Catch:{ all -> 0x009c }
                int r2 = r6.zzac     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setId(r2)     // Catch:{ all -> 0x009c }
                long r2 = r6.zzab     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setTimestampMillis(r2)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.CameraSource r2 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x009c }
                int r2 = r2.rotation     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setRotation(r2)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Frame r1 = r1.build()     // Catch:{ all -> 0x009c }
                java.nio.ByteBuffer r2 = r6.zzad     // Catch:{ all -> 0x009c }
                r3 = 0
                r6.zzad = r3     // Catch:{ all -> 0x009c }
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                com.google.android.gms.vision.Detector<?> r0 = r6.zzx     // Catch:{ Exception -> 0x0077 }
                r0.receiveFrame(r1)     // Catch:{ Exception -> 0x0077 }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.zzi
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x0075:
                r0 = move-exception
                goto L_0x008e
            L_0x0077:
                r0 = move-exception
                java.lang.String r1 = "CameraSource"
                java.lang.String r3 = "Exception thrown from receiver."
                android.util.Log.e(r1, r3, r0)     // Catch:{ all -> 0x0075 }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.zzi
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x008e:
                com.google.android.gms.vision.CameraSource r1 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r1 = r1.zzi
                byte[] r2 = r2.array()
                r1.addCallbackBuffer(r2)
                throw r0
            L_0x009c:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x009c }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zza.run():void");
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static class Builder {
        private final Detector<?> zzx;
        private CameraSource zzy;

        public Builder(Context context, Detector<?> detector) {
            CameraSource cameraSource = new CameraSource();
            this.zzy = cameraSource;
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector != null) {
                this.zzx = detector;
                Context unused = cameraSource.zzg = context;
            } else {
                throw new IllegalArgumentException("No detector supplied.");
            }
        }

        public Builder setRequestedFps(float f) {
            if (f > 0.0f) {
                float unused = this.zzy.zzk = f;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid fps: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                StringBuilder sb = new StringBuilder(45);
                sb.append("Invalid preview size: ");
                sb.append(i);
                sb.append("x");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            }
            int unused = this.zzy.zzl = i;
            int unused2 = this.zzy.zzm = i2;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                int unused = this.zzy.facing = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(27);
            sb.append("Invalid camera: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setAutoFocusEnabled(boolean z) {
            boolean unused = this.zzy.zzn = z;
            return this;
        }

        public Builder setFocusMode(String str) {
            if (!str.equals("continuous-video") && !str.equals("continuous-picture")) {
                Log.w("CameraSource", String.format("FocusMode %s is not supported for now.", new Object[]{str}));
                str = null;
            }
            String unused = this.zzy.zzo = str;
            return this;
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzy;
            cameraSource.getClass();
            zza unused = cameraSource.zzs = new zza(this.zzx);
            return this.zzy;
        }
    }

    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzh) {
            if (this.zzi != null) {
                return this;
            }
            Camera zzc2 = zzc();
            this.zzi = zzc2;
            zzc2.setPreviewDisplay(surfaceHolder);
            this.zzi.startPreview();
            this.zzr = new Thread(this.zzs);
            this.zzs.setActive(true);
            this.zzr.start();
            this.zzq = false;
            return this;
        }
    }

    public void stop() {
        synchronized (this.zzh) {
            this.zzs.setActive(false);
            if (this.zzr != null) {
                try {
                    this.zzr.join();
                } catch (InterruptedException unused) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzr = null;
            }
            if (this.zzi != null) {
                this.zzi.stopPreview();
                this.zzi.setPreviewCallbackWithBuffer((Camera.PreviewCallback) null);
                try {
                    if (this.zzq) {
                        this.zzi.setPreviewTexture((SurfaceTexture) null);
                    } else {
                        this.zzi.setPreviewDisplay((SurfaceHolder) null);
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
                    sb.append("Failed to clear camera preview: ");
                    sb.append(valueOf);
                    Log.e("CameraSource", sb.toString());
                }
                this.zzi.release();
                this.zzi = null;
            }
            this.zzt.clear();
        }
    }

    public Size getPreviewSize() {
        return this.zzj;
    }

    public int getCameraFacing() {
        return this.facing;
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzh) {
            if (this.zzi != null) {
                zzd zzd2 = new zzd();
                ShutterCallback unused = zzd2.zzag = shutterCallback;
                zzc zzc2 = new zzc();
                PictureCallback unused2 = zzc2.zzaf = pictureCallback;
                this.zzi.takePicture(zzd2, (Camera.PictureCallback) null, (Camera.PictureCallback) null, zzc2);
            }
        }
    }

    private CameraSource() {
        this.zzh = new Object();
        this.facing = 0;
        this.zzk = 30.0f;
        this.zzl = 1024;
        this.zzm = 768;
        this.zzn = false;
        this.zzt = new HashMap();
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01f1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final android.hardware.Camera zzc() throws java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            int r1 = r0.facing
            android.hardware.Camera$CameraInfo r2 = new android.hardware.Camera$CameraInfo
            r2.<init>()
            r3 = 0
            r4 = 0
        L_0x000b:
            int r5 = android.hardware.Camera.getNumberOfCameras()
            r6 = -1
            if (r4 >= r5) goto L_0x001d
            android.hardware.Camera.getCameraInfo(r4, r2)
            int r5 = r2.facing
            if (r5 != r1) goto L_0x001a
            goto L_0x001e
        L_0x001a:
            int r4 = r4 + 1
            goto L_0x000b
        L_0x001d:
            r4 = -1
        L_0x001e:
            if (r4 == r6) goto L_0x0236
            android.hardware.Camera r1 = android.hardware.Camera.open(r4)
            int r2 = r0.zzl
            int r5 = r0.zzm
            android.hardware.Camera$Parameters r6 = r1.getParameters()
            java.util.List r7 = r6.getSupportedPreviewSizes()
            java.util.List r6 = r6.getSupportedPictureSizes()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r9 = r7.iterator()
        L_0x003d:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x007d
            java.lang.Object r10 = r9.next()
            android.hardware.Camera$Size r10 = (android.hardware.Camera.Size) r10
            int r11 = r10.width
            float r11 = (float) r11
            int r12 = r10.height
            float r12 = (float) r12
            float r11 = r11 / r12
            java.util.Iterator r12 = r6.iterator()
        L_0x0054:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x003d
            java.lang.Object r13 = r12.next()
            android.hardware.Camera$Size r13 = (android.hardware.Camera.Size) r13
            int r14 = r13.width
            float r14 = (float) r14
            int r15 = r13.height
            float r15 = (float) r15
            float r14 = r14 / r15
            float r14 = r11 - r14
            float r14 = java.lang.Math.abs(r14)
            r15 = 1008981770(0x3c23d70a, float:0.01)
            int r14 = (r14 > r15 ? 1 : (r14 == r15 ? 0 : -1))
            if (r14 >= 0) goto L_0x0054
            com.google.android.gms.vision.CameraSource$zze r11 = new com.google.android.gms.vision.CameraSource$zze
            r11.<init>(r10, r13)
            r8.add(r11)
            goto L_0x003d
        L_0x007d:
            int r6 = r8.size()
            java.lang.String r9 = "CameraSource"
            r10 = 0
            if (r6 != 0) goto L_0x00a4
            java.lang.String r6 = "No preview sizes have a corresponding same-aspect-ratio picture size"
            android.util.Log.w(r9, r6)
            java.util.Iterator r6 = r7.iterator()
        L_0x008f:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00a4
            java.lang.Object r7 = r6.next()
            android.hardware.Camera$Size r7 = (android.hardware.Camera.Size) r7
            com.google.android.gms.vision.CameraSource$zze r11 = new com.google.android.gms.vision.CameraSource$zze
            r11.<init>(r7, r10)
            r8.add(r11)
            goto L_0x008f
        L_0x00a4:
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            int r6 = r8.size()
            r7 = 2147483647(0x7fffffff, float:NaN)
            r12 = r10
            r11 = 0
            r13 = 2147483647(0x7fffffff, float:NaN)
        L_0x00b2:
            if (r11 >= r6) goto L_0x00da
            java.lang.Object r14 = r8.get(r11)
            int r11 = r11 + 1
            com.google.android.gms.vision.CameraSource$zze r14 = (com.google.android.gms.vision.CameraSource.zze) r14
            com.google.android.gms.common.images.Size r15 = r14.zzd()
            int r16 = r15.getWidth()
            int r16 = r16 - r2
            int r16 = java.lang.Math.abs(r16)
            int r15 = r15.getHeight()
            int r15 = r15 - r5
            int r15 = java.lang.Math.abs(r15)
            int r15 = r16 + r15
            if (r15 >= r13) goto L_0x00b2
            r12 = r14
            r13 = r15
            goto L_0x00b2
        L_0x00da:
            if (r12 == 0) goto L_0x022e
            com.google.android.gms.common.images.Size r2 = r12.zze()
            com.google.android.gms.common.images.Size r5 = r12.zzd()
            r0.zzj = r5
            float r5 = r0.zzk
            r6 = 1148846080(0x447a0000, float:1000.0)
            float r5 = r5 * r6
            int r5 = (int) r5
            android.hardware.Camera$Parameters r6 = r1.getParameters()
            java.util.List r6 = r6.getSupportedPreviewFpsRange()
            java.util.Iterator r6 = r6.iterator()
            r8 = r10
        L_0x00fa:
            boolean r11 = r6.hasNext()
            r12 = 1
            if (r11 == 0) goto L_0x011d
            java.lang.Object r11 = r6.next()
            int[] r11 = (int[]) r11
            r13 = r11[r3]
            int r13 = r5 - r13
            r12 = r11[r12]
            int r12 = r5 - r12
            int r13 = java.lang.Math.abs(r13)
            int r12 = java.lang.Math.abs(r12)
            int r13 = r13 + r12
            if (r13 >= r7) goto L_0x00fa
            r8 = r11
            r7 = r13
            goto L_0x00fa
        L_0x011d:
            if (r8 == 0) goto L_0x0226
            android.hardware.Camera$Parameters r5 = r1.getParameters()
            if (r2 == 0) goto L_0x0130
            int r6 = r2.getWidth()
            int r2 = r2.getHeight()
            r5.setPictureSize(r6, r2)
        L_0x0130:
            com.google.android.gms.common.images.Size r2 = r0.zzj
            int r2 = r2.getWidth()
            com.google.android.gms.common.images.Size r6 = r0.zzj
            int r6 = r6.getHeight()
            r5.setPreviewSize(r2, r6)
            r2 = r8[r3]
            r6 = r8[r12]
            r5.setPreviewFpsRange(r2, r6)
            r2 = 17
            r5.setPreviewFormat(r2)
            android.content.Context r2 = r0.zzg
            java.lang.String r6 = "window"
            java.lang.Object r2 = r2.getSystemService(r6)
            android.view.WindowManager r2 = (android.view.WindowManager) r2
            android.view.Display r2 = r2.getDefaultDisplay()
            int r2 = r2.getRotation()
            if (r2 == 0) goto L_0x0187
            if (r2 == r12) goto L_0x0184
            r6 = 2
            if (r2 == r6) goto L_0x0181
            r6 = 3
            if (r2 == r6) goto L_0x017e
            r6 = 31
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            java.lang.String r6 = "Bad rotation value: "
            r7.append(r6)
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            android.util.Log.e(r9, r2)
            goto L_0x0187
        L_0x017e:
            r2 = 270(0x10e, float:3.78E-43)
            goto L_0x0188
        L_0x0181:
            r2 = 180(0xb4, float:2.52E-43)
            goto L_0x0188
        L_0x0184:
            r2 = 90
            goto L_0x0188
        L_0x0187:
            r2 = 0
        L_0x0188:
            android.hardware.Camera$CameraInfo r6 = new android.hardware.Camera$CameraInfo
            r6.<init>()
            android.hardware.Camera.getCameraInfo(r4, r6)
            int r4 = r6.facing
            if (r4 != r12) goto L_0x019e
            int r4 = r6.orientation
            int r4 = r4 + r2
            int r4 = r4 % 360
            int r2 = 360 - r4
            int r2 = r2 % 360
            goto L_0x01a6
        L_0x019e:
            int r4 = r6.orientation
            int r4 = r4 - r2
            int r4 = r4 + 360
            int r4 = r4 % 360
            r2 = r4
        L_0x01a6:
            int r6 = r4 / 90
            r0.rotation = r6
            r1.setDisplayOrientation(r2)
            r5.setRotation(r4)
            java.lang.String r2 = r0.zzo
            if (r2 == 0) goto L_0x01d7
            java.util.List r2 = r5.getSupportedFocusModes()
            java.lang.String r4 = r0.zzo
            boolean r2 = r2.contains(r4)
            if (r2 == 0) goto L_0x01c6
            java.lang.String r2 = r0.zzo
            r5.setFocusMode(r2)
            goto L_0x01d7
        L_0x01c6:
            java.lang.Object[] r2 = new java.lang.Object[r12]
            java.lang.String r4 = r0.zzo
            r2[r3] = r4
            java.lang.String r3 = "FocusMode %s is not supported on this device."
            java.lang.String r2 = java.lang.String.format(r3, r2)
            android.util.Log.w(r9, r2)
            r0.zzo = r10
        L_0x01d7:
            java.lang.String r2 = r0.zzo
            if (r2 != 0) goto L_0x01f6
            boolean r2 = r0.zzn
            if (r2 == 0) goto L_0x01f6
            java.util.List r2 = r5.getSupportedFocusModes()
            java.lang.String r3 = "continuous-video"
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L_0x01f1
            r5.setFocusMode(r3)
            r0.zzo = r3
            goto L_0x01f6
        L_0x01f1:
            java.lang.String r2 = "Camera auto focus is not supported on this device."
            android.util.Log.i(r9, r2)
        L_0x01f6:
            r1.setParameters(r5)
            com.google.android.gms.vision.CameraSource$zzb r2 = new com.google.android.gms.vision.CameraSource$zzb
            r2.<init>()
            r1.setPreviewCallbackWithBuffer(r2)
            com.google.android.gms.common.images.Size r2 = r0.zzj
            byte[] r2 = r0.zza((com.google.android.gms.common.images.Size) r2)
            r1.addCallbackBuffer(r2)
            com.google.android.gms.common.images.Size r2 = r0.zzj
            byte[] r2 = r0.zza((com.google.android.gms.common.images.Size) r2)
            r1.addCallbackBuffer(r2)
            com.google.android.gms.common.images.Size r2 = r0.zzj
            byte[] r2 = r0.zza((com.google.android.gms.common.images.Size) r2)
            r1.addCallbackBuffer(r2)
            com.google.android.gms.common.images.Size r2 = r0.zzj
            byte[] r2 = r0.zza((com.google.android.gms.common.images.Size) r2)
            r1.addCallbackBuffer(r2)
            return r1
        L_0x0226:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Could not find suitable preview frames per second range."
            r1.<init>(r2)
            throw r1
        L_0x022e:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Could not find suitable preview size."
            r1.<init>(r2)
            throw r1
        L_0x0236:
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "Could not find requested camera."
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zzc():android.hardware.Camera");
    }

    private final byte[] zza(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((long) ((size.getHeight() * size.getWidth()) * ImageFormat.getBitsPerPixel(17)))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.zzt.put(bArr, wrap);
        return bArr;
    }
}

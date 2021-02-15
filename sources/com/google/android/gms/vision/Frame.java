package com.google.android.gms.vision;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public class Frame {
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;
    public static final int ROTATION_90 = 1;
    private final Metadata zzao;
    /* access modifiers changed from: private */
    @Nullable
    public ByteBuffer zzap;
    /* access modifiers changed from: private */
    @Nullable
    public zza zzaq;
    /* access modifiers changed from: private */
    @Nullable
    public Bitmap zzar;

    public Metadata getMetadata() {
        return this.zzao;
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static class Builder {
        private final Frame zzax = new Frame();

        public Builder setBitmap(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Bitmap unused = this.zzax.zzar = bitmap;
            Metadata metadata = this.zzax.getMetadata();
            int unused2 = metadata.width = width;
            int unused3 = metadata.height = height;
            return this;
        }

        public Builder setPlanes(Image.Plane[] planeArr, int i, int i2, int i3) {
            if (planeArr == null) {
                throw new IllegalArgumentException("Null image data supplied.");
            } else if (planeArr.length != 3) {
                throw new IllegalArgumentException("Only android.graphics.ImageFormat#YUV_420_888 is supported which should have 3 planes.");
            } else if (planeArr[0].getBuffer().capacity() >= i * i2) {
                zza unused = this.zzax.zzaq = new zza(planeArr);
                Metadata metadata = this.zzax.getMetadata();
                int unused2 = metadata.width = i;
                int unused3 = metadata.height = i2;
                int unused4 = metadata.format = i3;
                return this;
            } else {
                throw new IllegalArgumentException("Invalid image data size.");
            }
        }

        public Builder setImageData(ByteBuffer byteBuffer, int i, int i2, int i3) {
            if (byteBuffer == null) {
                throw new IllegalArgumentException("Null image data supplied.");
            } else if (byteBuffer.capacity() < i * i2) {
                throw new IllegalArgumentException("Invalid image data size.");
            } else if (i3 == 16 || i3 == 17 || i3 == 842094169) {
                ByteBuffer unused = this.zzax.zzap = byteBuffer;
                Metadata metadata = this.zzax.getMetadata();
                int unused2 = metadata.width = i;
                int unused3 = metadata.height = i2;
                int unused4 = metadata.format = i3;
                return this;
            } else {
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unsupported image format: ");
                sb.append(i3);
                throw new IllegalArgumentException(sb.toString());
            }
        }

        public Builder setId(int i) {
            int unused = this.zzax.getMetadata().id = i;
            return this;
        }

        public Builder setTimestampMillis(long j) {
            long unused = this.zzax.getMetadata().zzaz = j;
            return this;
        }

        public Builder setRotation(int i) {
            int unused = this.zzax.getMetadata().rotation = i;
            return this;
        }

        public Frame build() {
            if (this.zzax.zzap != null || this.zzax.zzar != null || this.zzax.zzaq != null) {
                return this.zzax;
            }
            throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
        }
    }

    @Nullable
    public Image.Plane[] getPlanes() {
        zza zza2 = this.zzaq;
        if (zza2 == null) {
            return null;
        }
        return zza2.getPlanes();
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    public static class Metadata {
        /* access modifiers changed from: private */
        public int format = -1;
        /* access modifiers changed from: private */
        public int height;
        /* access modifiers changed from: private */
        public int id;
        /* access modifiers changed from: private */
        public int rotation;
        /* access modifiers changed from: private */
        public int width;
        /* access modifiers changed from: private */
        public long zzaz;

        public Metadata() {
        }

        public Metadata(Metadata metadata) {
            this.width = metadata.getWidth();
            this.height = metadata.getHeight();
            this.id = metadata.getId();
            this.zzaz = metadata.getTimestampMillis();
            this.rotation = metadata.getRotation();
            this.format = metadata.getFormat();
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public int getId() {
            return this.id;
        }

        public long getTimestampMillis() {
            return this.zzaz;
        }

        public int getRotation() {
            return this.rotation;
        }

        public int getFormat() {
            return this.format;
        }

        public final void zzf() {
            if (this.rotation % 2 != 0) {
                int i = this.width;
                this.width = this.height;
                this.height = i;
            }
            this.rotation = 0;
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
    private static class zza {
        private final Image.Plane[] zzay;

        zza(Image.Plane[] planeArr) {
            this.zzay = planeArr;
        }

        /* access modifiers changed from: package-private */
        public final Image.Plane[] getPlanes() {
            return this.zzay;
        }
    }

    public ByteBuffer getGrayscaleImageData() {
        Bitmap bitmap = this.zzar;
        if (bitmap == null) {
            return this.zzap;
        }
        int width = bitmap.getWidth();
        int height = this.zzar.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        this.zzar.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((int) ((((float) Color.red(iArr[i2])) * 0.299f) + (((float) Color.green(iArr[i2])) * 0.587f) + (((float) Color.blue(iArr[i2])) * 0.114f)));
        }
        return ByteBuffer.wrap(bArr);
    }

    public Bitmap getBitmap() {
        return this.zzar;
    }

    private Frame() {
        this.zzao = new Metadata();
        this.zzap = null;
        this.zzaq = null;
        this.zzar = null;
    }
}

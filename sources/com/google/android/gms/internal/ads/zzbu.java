package com.google.android.gms.internal.ads;

import androidx.core.view.MotionEventCompat;
import java.nio.ByteBuffer;
import java.util.Date;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbu extends zzeof {
    private Date zzde;
    private Date zzdf;
    private long zzdg;
    private long zzdh;
    private double zzdi = 1.0d;
    private float zzdj = 1.0f;
    private zzeop zzdk = zzeop.zziui;
    private long zzdl;
    private int zzdm;
    private int zzdn;
    private int zzdo;
    private int zzdp;
    private int zzdq;
    private int zzdr;

    public zzbu() {
        super("mvhd");
    }

    public final long zzr() {
        return this.zzdg;
    }

    public final long getDuration() {
        return this.zzdh;
    }

    public final void zzl(ByteBuffer byteBuffer) {
        zzr(byteBuffer);
        if (getVersion() == 1) {
            this.zzde = zzeom.zzfv(zzbq.zzh(byteBuffer));
            this.zzdf = zzeom.zzfv(zzbq.zzh(byteBuffer));
            this.zzdg = zzbq.zzf(byteBuffer);
            this.zzdh = zzbq.zzh(byteBuffer);
        } else {
            this.zzde = zzeom.zzfv(zzbq.zzf(byteBuffer));
            this.zzdf = zzeom.zzfv(zzbq.zzf(byteBuffer));
            this.zzdg = zzbq.zzf(byteBuffer);
            this.zzdh = zzbq.zzf(byteBuffer);
        }
        this.zzdi = zzbq.zzi(byteBuffer);
        byte[] bArr = new byte[2];
        byteBuffer.get(bArr);
        this.zzdj = ((float) ((short) ((bArr[1] & 255) | ((short) (0 | ((bArr[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK)))))) / 256.0f;
        zzbq.zzg(byteBuffer);
        zzbq.zzf(byteBuffer);
        zzbq.zzf(byteBuffer);
        this.zzdk = zzeop.zzs(byteBuffer);
        this.zzdm = byteBuffer.getInt();
        this.zzdn = byteBuffer.getInt();
        this.zzdo = byteBuffer.getInt();
        this.zzdp = byteBuffer.getInt();
        this.zzdq = byteBuffer.getInt();
        this.zzdr = byteBuffer.getInt();
        this.zzdl = zzbq.zzf(byteBuffer);
    }

    public final String toString() {
        return "MovieHeaderBox[" + "creationTime=" + this.zzde + ";" + "modificationTime=" + this.zzdf + ";" + "timescale=" + this.zzdg + ";" + "duration=" + this.zzdh + ";" + "rate=" + this.zzdi + ";" + "volume=" + this.zzdj + ";" + "matrix=" + this.zzdk + ";" + "nextTrackId=" + this.zzdl + "]";
    }
}

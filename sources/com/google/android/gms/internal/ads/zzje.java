package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzje implements zzie {
    private int zzahm = -1;
    private float zzaia = 1.0f;
    private float zzaib = 1.0f;
    private ByteBuffer zzalf;
    private int zzami = -1;
    private ByteBuffer zzamm;
    private boolean zzamn;
    private zzjf zzamw;
    private ShortBuffer zzamx;
    private long zzamy;
    private long zzamz;

    public zzje() {
        ByteBuffer byteBuffer = zzaiy;
        this.zzamm = byteBuffer;
        this.zzamx = byteBuffer.asShortBuffer();
        this.zzalf = zzaiy;
    }

    public final int zzfk() {
        return 2;
    }

    public final float zza(float f) {
        float zza = zzpq.zza(f, 0.1f, 8.0f);
        this.zzaia = zza;
        return zza;
    }

    public final float zzb(float f) {
        this.zzaib = zzpq.zza(f, 0.1f, 8.0f);
        return f;
    }

    public final long zzgf() {
        return this.zzamy;
    }

    public final long zzgg() {
        return this.zzamz;
    }

    public final boolean zzb(int i, int i2, int i3) throws zzih {
        if (i3 != 2) {
            throw new zzih(i, i2, i3);
        } else if (this.zzami == i && this.zzahm == i2) {
            return false;
        } else {
            this.zzami = i;
            this.zzahm = i2;
            return true;
        }
    }

    public final boolean isActive() {
        return Math.abs(this.zzaia - 1.0f) >= 0.01f || Math.abs(this.zzaib - 1.0f) >= 0.01f;
    }

    public final int zzfj() {
        return this.zzahm;
    }

    public final void zzn(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.zzamy += (long) remaining;
            this.zzamw.zza(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int zzgh = (this.zzamw.zzgh() * this.zzahm) << 1;
        if (zzgh > 0) {
            if (this.zzamm.capacity() < zzgh) {
                ByteBuffer order = ByteBuffer.allocateDirect(zzgh).order(ByteOrder.nativeOrder());
                this.zzamm = order;
                this.zzamx = order.asShortBuffer();
            } else {
                this.zzamm.clear();
                this.zzamx.clear();
            }
            this.zzamw.zzb(this.zzamx);
            this.zzamz += (long) zzgh;
            this.zzamm.limit(zzgh);
            this.zzalf = this.zzamm;
        }
    }

    public final void zzfl() {
        this.zzamw.zzfl();
        this.zzamn = true;
    }

    public final ByteBuffer zzfm() {
        ByteBuffer byteBuffer = this.zzalf;
        this.zzalf = zzaiy;
        return byteBuffer;
    }

    public final boolean zzfe() {
        if (!this.zzamn) {
            return false;
        }
        zzjf zzjf = this.zzamw;
        return zzjf == null || zzjf.zzgh() == 0;
    }

    public final void flush() {
        zzjf zzjf = new zzjf(this.zzami, this.zzahm);
        this.zzamw = zzjf;
        zzjf.setSpeed(this.zzaia);
        this.zzamw.zzc(this.zzaib);
        this.zzalf = zzaiy;
        this.zzamy = 0;
        this.zzamz = 0;
        this.zzamn = false;
    }

    public final void reset() {
        this.zzamw = null;
        ByteBuffer byteBuffer = zzaiy;
        this.zzamm = byteBuffer;
        this.zzamx = byteBuffer.asShortBuffer();
        this.zzalf = zzaiy;
        this.zzahm = -1;
        this.zzami = -1;
        this.zzamy = 0;
        this.zzamz = 0;
        this.zzamn = false;
    }
}

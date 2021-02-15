package com.google.android.gms.internal.ads;

import java.io.OutputStream;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzeiy extends OutputStream {
    private static final byte[] zziex = new byte[0];
    private byte[] buffer = new byte[128];
    private final int zziey = 128;
    private final ArrayList<zzeip> zziez = new ArrayList<>();
    private int zzifa;
    private int zzifb;

    zzeiy(int i) {
    }

    public final synchronized void write(int i) {
        if (this.zzifb == this.buffer.length) {
            zzfx(1);
        }
        byte[] bArr = this.buffer;
        int i2 = this.zzifb;
        this.zzifb = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public final synchronized void write(byte[] bArr, int i, int i2) {
        if (i2 <= this.buffer.length - this.zzifb) {
            System.arraycopy(bArr, i, this.buffer, this.zzifb, i2);
            this.zzifb += i2;
            return;
        }
        int length = this.buffer.length - this.zzifb;
        System.arraycopy(bArr, i, this.buffer, this.zzifb, length);
        int i3 = i2 - length;
        zzfx(i3);
        System.arraycopy(bArr, i + length, this.buffer, 0, i3);
        this.zzifb = i3;
    }

    public final synchronized zzeip zzbdw() {
        if (this.zzifb >= this.buffer.length) {
            this.zziez.add(new zzeiz(this.buffer));
            this.buffer = zziex;
        } else if (this.zzifb > 0) {
            byte[] bArr = this.buffer;
            int i = this.zzifb;
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i));
            this.zziez.add(new zzeiz(bArr2));
        }
        this.zzifa += this.zzifb;
        this.zzifb = 0;
        return zzeip.zzl(this.zziez);
    }

    private final synchronized int size() {
        return this.zzifa + this.zzifb;
    }

    public final String toString() {
        return String.format("<ByteString.Output@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    private final void zzfx(int i) {
        this.zziez.add(new zzeiz(this.buffer));
        int length = this.zzifa + this.buffer.length;
        this.zzifa = length;
        this.buffer = new byte[Math.max(this.zziey, Math.max(i, length >>> 1))];
        this.zzifb = 0;
    }
}

package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zziz implements zzie {
    private int zzahm = -1;
    private ByteBuffer zzalf = zzaiy;
    private int zzami = -1;
    private int[] zzamj;
    private boolean zzamk;
    private int[] zzaml;
    private ByteBuffer zzamm = zzaiy;
    private boolean zzamn;

    public final int zzfk() {
        return 2;
    }

    public final void zzb(int[] iArr) {
        this.zzamj = iArr;
    }

    public final boolean zzb(int i, int i2, int i3) throws zzih {
        boolean z = !Arrays.equals(this.zzamj, this.zzaml);
        int[] iArr = this.zzamj;
        this.zzaml = iArr;
        if (iArr == null) {
            this.zzamk = false;
            return z;
        } else if (i3 != 2) {
            throw new zzih(i, i2, i3);
        } else if (!z && this.zzami == i && this.zzahm == i2) {
            return false;
        } else {
            this.zzami = i;
            this.zzahm = i2;
            this.zzamk = i2 != this.zzaml.length;
            int i4 = 0;
            while (true) {
                int[] iArr2 = this.zzaml;
                if (i4 >= iArr2.length) {
                    return true;
                }
                int i5 = iArr2[i4];
                if (i5 < i2) {
                    this.zzamk = (i5 != i4) | this.zzamk;
                    i4++;
                } else {
                    throw new zzih(i, i2, i3);
                }
            }
        }
    }

    public final boolean isActive() {
        return this.zzamk;
    }

    public final int zzfj() {
        int[] iArr = this.zzaml;
        return iArr == null ? this.zzahm : iArr.length;
    }

    public final void zzn(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int length = (((limit - position) / (this.zzahm * 2)) * this.zzaml.length) << 1;
        if (this.zzamm.capacity() < length) {
            this.zzamm = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
        } else {
            this.zzamm.clear();
        }
        while (position < limit) {
            for (int i : this.zzaml) {
                this.zzamm.putShort(byteBuffer.getShort((i * 2) + position));
            }
            position += this.zzahm << 1;
        }
        byteBuffer.position(limit);
        this.zzamm.flip();
        this.zzalf = this.zzamm;
    }

    public final void zzfl() {
        this.zzamn = true;
    }

    public final ByteBuffer zzfm() {
        ByteBuffer byteBuffer = this.zzalf;
        this.zzalf = zzaiy;
        return byteBuffer;
    }

    public final boolean zzfe() {
        return this.zzamn && this.zzalf == zzaiy;
    }

    public final void flush() {
        this.zzalf = zzaiy;
        this.zzamn = false;
    }

    public final void reset() {
        flush();
        this.zzamm = zzaiy;
        this.zzahm = -1;
        this.zzami = -1;
        this.zzaml = null;
        this.zzamk = false;
    }
}

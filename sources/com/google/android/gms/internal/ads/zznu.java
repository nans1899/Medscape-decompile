package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zznu implements zzob {
    private final int length;
    private int zzahv;
    private final zzho[] zzbfo;
    private final zznq zzbgw;
    private final int[] zzbgx;
    private final long[] zzbgy;

    public zznu(zznq zznq, int... iArr) {
        int i = 0;
        zzoz.checkState(iArr.length > 0);
        this.zzbgw = (zznq) zzoz.checkNotNull(zznq);
        int length2 = iArr.length;
        this.length = length2;
        this.zzbfo = new zzho[length2];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            this.zzbfo[i2] = zznq.zzbe(iArr[i2]);
        }
        Arrays.sort(this.zzbfo, new zznw());
        this.zzbgx = new int[this.length];
        while (true) {
            int i3 = this.length;
            if (i < i3) {
                this.zzbgx[i] = zznq.zzh(this.zzbfo[i]);
                i++;
            } else {
                this.zzbgy = new long[i3];
                return;
            }
        }
    }

    public final zznq zzil() {
        return this.zzbgw;
    }

    public final int length() {
        return this.zzbgx.length;
    }

    public final zzho zzbe(int i) {
        return this.zzbfo[i];
    }

    public final int zzbf(int i) {
        return this.zzbgx[0];
    }

    public int hashCode() {
        if (this.zzahv == 0) {
            this.zzahv = (System.identityHashCode(this.zzbgw) * 31) + Arrays.hashCode(this.zzbgx);
        }
        return this.zzahv;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zznu zznu = (zznu) obj;
            return this.zzbgw == zznu.zzbgw && Arrays.equals(this.zzbgx, zznu.zzbgx);
        }
    }
}

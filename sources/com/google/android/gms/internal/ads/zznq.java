package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zznq {
    public final int length;
    private int zzahv;
    private final zzho[] zzbfo;

    public zznq(zzho... zzhoArr) {
        zzoz.checkState(zzhoArr.length > 0);
        this.zzbfo = zzhoArr;
        this.length = zzhoArr.length;
    }

    public final zzho zzbe(int i) {
        return this.zzbfo[i];
    }

    public final int zzh(zzho zzho) {
        int i = 0;
        while (true) {
            zzho[] zzhoArr = this.zzbfo;
            if (i >= zzhoArr.length) {
                return -1;
            }
            if (zzho == zzhoArr[i]) {
                return i;
            }
            i++;
        }
    }

    public final int hashCode() {
        if (this.zzahv == 0) {
            this.zzahv = Arrays.hashCode(this.zzbfo) + 527;
        }
        return this.zzahv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zznq zznq = (zznq) obj;
            return this.length == zznq.length && Arrays.equals(this.zzbfo, zznq.zzbfo);
        }
    }
}

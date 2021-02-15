package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zznp {
    public static final zznp zzbgu = new zznp(new zznq[0]);
    public final int length;
    private int zzahv;
    private final zznq[] zzbgv;

    public zznp(zznq... zznqArr) {
        this.zzbgv = zznqArr;
        this.length = zznqArr.length;
    }

    public final zznq zzbd(int i) {
        return this.zzbgv[i];
    }

    public final int zza(zznq zznq) {
        for (int i = 0; i < this.length; i++) {
            if (this.zzbgv[i] == zznq) {
                return i;
            }
        }
        return -1;
    }

    public final int hashCode() {
        if (this.zzahv == 0) {
            this.zzahv = Arrays.hashCode(this.zzbgv);
        }
        return this.zzahv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zznp zznp = (zznp) obj;
            return this.length == zznp.length && Arrays.equals(this.zzbgv, zznp.zzbgv);
        }
    }
}

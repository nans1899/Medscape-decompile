package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzod {
    public final int length;
    private int zzahv;
    private final zzob[] zzbhu;

    public zzod(zzob... zzobArr) {
        this.zzbhu = zzobArr;
        this.length = zzobArr.length;
    }

    public final zzob zzbg(int i) {
        return this.zzbhu[i];
    }

    public final zzob[] zzim() {
        return (zzob[]) this.zzbhu.clone();
    }

    public final int hashCode() {
        if (this.zzahv == 0) {
            this.zzahv = Arrays.hashCode(this.zzbhu) + 527;
        }
        return this.zzahv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzbhu, ((zzod) obj).zzbhu);
    }
}

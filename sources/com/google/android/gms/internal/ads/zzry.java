package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzry {
    final long value;
    final int zzbtz;
    final String zzbuj;

    zzry(long j, String str, int i) {
        this.value = j;
        this.zzbuj = str;
        this.zzbtz = i;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzry)) {
            zzry zzry = (zzry) obj;
            if (zzry.value == this.value && zzry.zzbtz == this.zzbtz) {
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.value;
    }
}

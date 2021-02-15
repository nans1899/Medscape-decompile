package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdql implements zzdqj {
    private final String zzcn;

    public zzdql(String str) {
        this.zzcn = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzdql)) {
            return false;
        }
        return this.zzcn.equals(((zzdql) obj).zzcn);
    }

    public final int hashCode() {
        return this.zzcn.hashCode();
    }

    public final String toString() {
        return this.zzcn;
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbae {
    public final int count;
    public final String name;
    private final double zzecj;
    private final double zzeck;
    public final double zzecl;

    public zzbae(String str, double d, double d2, double d3, int i) {
        this.name = str;
        this.zzeck = d;
        this.zzecj = d2;
        this.zzecl = d3;
        this.count = i;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("minBound", Double.valueOf(this.zzeck)).add("maxBound", Double.valueOf(this.zzecj)).add("percent", Double.valueOf(this.zzecl)).add("count", Integer.valueOf(this.count)).toString();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbae)) {
            return false;
        }
        zzbae zzbae = (zzbae) obj;
        if (Objects.equal(this.name, zzbae.name) && this.zzecj == zzbae.zzecj && this.zzeck == zzbae.zzeck && this.count == zzbae.count && Double.compare(this.zzecl, zzbae.zzecl) == 0) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.name, Double.valueOf(this.zzecj), Double.valueOf(this.zzeck), Double.valueOf(this.zzecl), Integer.valueOf(this.count));
    }
}

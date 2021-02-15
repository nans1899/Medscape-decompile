package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdti extends zzdtd {
    private final String zzhmg;
    private final boolean zzxj;
    private final boolean zzzf;

    private zzdti(String str, boolean z, boolean z2) {
        this.zzhmg = str;
        this.zzxj = z;
        this.zzzf = z2;
    }

    public final String zzavt() {
        return this.zzhmg;
    }

    public final boolean zzavu() {
        return this.zzxj;
    }

    public final boolean zzcm() {
        return this.zzzf;
    }

    public final String toString() {
        String str = this.zzhmg;
        boolean z = this.zzxj;
        boolean z2 = this.zzzf;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 99);
        sb.append("AdShield2Options{clientVersion=");
        sb.append(str);
        sb.append(", shouldGetAdvertisingId=");
        sb.append(z);
        sb.append(", isGooglePlayServicesAvailable=");
        sb.append(z2);
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzdtd) {
            zzdtd zzdtd = (zzdtd) obj;
            return this.zzhmg.equals(zzdtd.zzavt()) && this.zzxj == zzdtd.zzavu() && this.zzzf == zzdtd.zzcm();
        }
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = (((this.zzhmg.hashCode() ^ 1000003) * 1000003) ^ (this.zzxj ? 1231 : 1237)) * 1000003;
        if (!this.zzzf) {
            i = 1237;
        }
        return hashCode ^ i;
    }
}

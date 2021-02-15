package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdtk extends zzdtg {
    private String zzhmg;
    private Boolean zzhmh;
    private Boolean zzhmi;

    zzdtk() {
    }

    public final zzdtg zzhb(String str) {
        if (str != null) {
            this.zzhmg = str;
            return this;
        }
        throw new NullPointerException("Null clientVersion");
    }

    public final zzdtg zzbq(boolean z) {
        this.zzhmh = Boolean.valueOf(z);
        return this;
    }

    public final zzdtg zzbr(boolean z) {
        this.zzhmi = true;
        return this;
    }

    public final zzdtd zzavw() {
        String str = "";
        if (this.zzhmg == null) {
            str = str.concat(" clientVersion");
        }
        if (this.zzhmh == null) {
            str = String.valueOf(str).concat(" shouldGetAdvertisingId");
        }
        if (this.zzhmi == null) {
            str = String.valueOf(str).concat(" isGooglePlayServicesAvailable");
        }
        if (str.isEmpty()) {
            return new zzdti(this.zzhmg, this.zzhmh.booleanValue(), this.zzhmi.booleanValue());
        }
        String valueOf = String.valueOf(str);
        throw new IllegalStateException(valueOf.length() != 0 ? "Missing required properties:".concat(valueOf) : new String("Missing required properties:"));
    }
}

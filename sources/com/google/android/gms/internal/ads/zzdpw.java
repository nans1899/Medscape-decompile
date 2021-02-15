package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdpw {
    private zzdpv zzhgr = null;
    private zzdpv zzhgs = null;
    private zzdpv zzhgt = null;
    private zzdpv zzhgu = null;
    private zzdpv zzhgv = null;
    private zzdpv zzhgw = null;
    private zzdpv zzhgx = null;
    private zzdpv zzhgy = null;

    public final void zza(zzdpv zzdpv) {
        this.zzhgu = zzdpv;
    }

    public final void onAdClosed() {
        zzdpv zzdpv = this.zzhgu;
        if (zzdpv != null) {
            zzdpv.execute();
        }
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdmj implements zzdlk {
    private final String zzdgm;
    private final String zzdjk;
    private final zzatw zzfuw;

    zzdmj(zzatw zzatw, String str, String str2) {
        this.zzfuw = zzatw;
        this.zzdgm = str;
        this.zzdjk = str2;
    }

    public final void zzq(Object obj) {
        zzatw zzatw = this.zzfuw;
        ((zzavl) obj).zza(new zzavu(zzatw.getType(), zzatw.getAmount()), this.zzdgm, this.zzdjk);
    }
}

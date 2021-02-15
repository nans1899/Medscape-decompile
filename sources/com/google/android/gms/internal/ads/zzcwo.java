package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcwo implements zzeoy<zzcwl> {
    private final zzeph<zzckp> zzetd;
    private final zzeph<zzdow> zzeuo;
    private final zzeph<zzcmu> zzeup;

    private zzcwo(zzeph<zzdow> zzeph, zzeph<zzckp> zzeph2, zzeph<zzcmu> zzeph3) {
        this.zzeuo = zzeph;
        this.zzetd = zzeph2;
        this.zzeup = zzeph3;
    }

    public static zzcwo zzq(zzeph<zzdow> zzeph, zzeph<zzckp> zzeph2, zzeph<zzcmu> zzeph3) {
        return new zzcwo(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzcwl(this.zzeuo.get(), this.zzetd.get(), this.zzeup.get());
    }
}

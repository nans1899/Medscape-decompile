package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcrx implements zzeoy<zzcrw> {
    private final zzeph<zzcru> zzeyu;
    private final zzeph<zzdzb> zzgly;

    private zzcrx(zzeph<zzcru> zzeph, zzeph<zzdzb> zzeph2) {
        this.zzeyu = zzeph;
        this.zzgly = zzeph2;
    }

    public static zzcrx zzav(zzeph<zzcru> zzeph, zzeph<zzdzb> zzeph2) {
        return new zzcrx(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzcrw(this.zzeyu.get(), this.zzgly.get());
    }
}

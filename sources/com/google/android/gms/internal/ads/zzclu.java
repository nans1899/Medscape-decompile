package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzclu implements zzeoy<zzcln> {
    private final zzeph<zzdmd> zzevr;
    private final zzeph<zzts> zzggl;

    private zzclu(zzeph<zzts> zzeph, zzeph<zzdmd> zzeph2) {
        this.zzggl = zzeph;
        this.zzevr = zzeph2;
    }

    public static zzclu zzag(zzeph<zzts> zzeph, zzeph<zzdmd> zzeph2) {
        return new zzclu(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return new zzcln(this.zzggl.get(), this.zzevr.get());
    }
}

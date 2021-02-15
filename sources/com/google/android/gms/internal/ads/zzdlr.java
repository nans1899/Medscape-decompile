package com.google.android.gms.internal.ads;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdlr implements zzdvu<zzcqm, zzdlt> {
    private final /* synthetic */ zzdlp zzhcd;

    zzdlr(zzdlp zzdlp) {
        this.zzhcd = zzdlp;
    }

    @NullableDecl
    public final /* synthetic */ Object apply(@NullableDecl Object obj) {
        zzbbq.zzc("", (zzcqm) obj);
        zzayp.zzei("Failed to get a cache key, reverting to legacy flow.");
        zzdlp zzdlp = this.zzhcd;
        zzdlt unused = zzdlp.zzhcc = new zzdlt((zzatc) null, zzdlp.zzatb(), (zzdlr) null);
        return this.zzhcd.zzhcc;
    }
}

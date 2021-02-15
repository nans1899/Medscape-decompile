package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdgi implements zzdgx<zzdgj> {
    private final zzbbx zzbpe;
    private final zzdzb zzgcz;
    private final Context zzvr;

    public zzdgi(zzdzb zzdzb, Context context, zzbbx zzbbx) {
        this.zzgcz = zzdzb;
        this.zzvr = context;
        this.zzbpe = zzbbx;
    }

    public final zzdzc<zzdgj> zzarj() {
        return this.zzgcz.zze(new zzdgl(this));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdgj zzary() throws Exception {
        boolean isCallerInstantApp = Wrappers.packageManager(this.zzvr).isCallerInstantApp();
        zzp.zzkr();
        boolean zzaz = zzayu.zzaz(this.zzvr);
        String str = this.zzbpe.zzbre;
        zzp.zzkt();
        boolean zzxy = zzazd.zzxy();
        zzp.zzkr();
        return new zzdgj(isCallerInstantApp, zzaz, str, zzxy, zzayu.zzaw(this.zzvr), DynamiteModule.getRemoteVersion(this.zzvr, ModuleDescriptor.MODULE_ID), DynamiteModule.getLocalVersion(this.zzvr, ModuleDescriptor.MODULE_ID));
    }
}

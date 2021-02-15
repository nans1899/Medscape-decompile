package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.ads.internal.zza;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcjv {
    private final zzawl zzbog;
    private final zzeg zzeom;
    private final Executor zzfoc;
    /* access modifiers changed from: private */
    public final zzbvh zzfsg;
    private final zzbwa zzfwy;
    private final zzbuc zzfxw;
    private final zzbmu zzfxx;
    private final zzcae zzfxz;
    private final zzbvv zzges;
    private final zzbyf zzget;
    private final zza zzgfi;
    private final zzbvb zzgfj;
    /* access modifiers changed from: private */
    public final zzbxu zzgfk;

    public zzcjv(zzbuc zzbuc, zzbvh zzbvh, zzbvv zzbvv, zzbwa zzbwa, zzbyf zzbyf, Executor executor, zzcae zzcae, zzbmu zzbmu, zza zza, zzbvb zzbvb, zzawl zzawl, zzeg zzeg, zzbxu zzbxu) {
        this.zzfxw = zzbuc;
        this.zzfsg = zzbvh;
        this.zzges = zzbvv;
        this.zzfwy = zzbwa;
        this.zzget = zzbyf;
        this.zzfoc = executor;
        this.zzfxz = zzcae;
        this.zzfxx = zzbmu;
        this.zzgfi = zza;
        this.zzgfj = zzbvb;
        this.zzbog = zzawl;
        this.zzeom = zzeg;
        this.zzgfk = zzbxu;
    }

    public final void zzb(zzbgj zzbgj, boolean z) {
        zzdw zzca;
        zzbgj.zzabw().zza(new zzcjy(this), this.zzges, this.zzfwy, new zzcjx(this), new zzcka(this), z, (zzahp) null, this.zzgfi, new zzckf(this), this.zzbog);
        zzbgj.setOnTouchListener(new zzcjz(this));
        zzbgj.setOnClickListener(new zzckc(this));
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcqx)).booleanValue() && (zzca = this.zzeom.zzca()) != null) {
            zzca.zzb(zzbgj.getView());
        }
        this.zzfxz.zza(zzbgj, this.zzfoc);
        this.zzfxz.zza(new zzckb(zzbgj), this.zzfoc);
        this.zzfxz.zzv(zzbgj.getView());
        zzbgj.zza("/trackActiveViewUnit", (zzahq<? super zzbgj>) new zzcke(this, zzbgj));
        this.zzfxx.zzo(zzbgj);
        if (!((Boolean) zzwm.zzpx().zzd(zzabb.zzcoh)).booleanValue()) {
            zzbvb zzbvb = this.zzgfj;
            zzbgj.getClass();
            zzbvb.zza(zzckd.zzn(zzbgj), this.zzfoc);
        }
    }

    public static zzdzc<?> zza(zzbgj zzbgj, String str, String str2) {
        zzbcg zzbcg = new zzbcg();
        zzbgj.zzabw().zza((zzbhu) new zzckg(zzbcg));
        zzbgj.zzb(str, str2, (String) null);
        return zzbcg;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzbgj zzbgj, zzbgj zzbgj2, Map map) {
        this.zzfxx.zzf(zzbgj);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzae(View view) {
        this.zzgfi.recordClick();
        zzawl zzawl = this.zzbog;
        if (zzawl != null) {
            zzawl.zzvs();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ boolean zza(View view, MotionEvent motionEvent) {
        this.zzgfi.recordClick();
        zzawl zzawl = this.zzbog;
        if (zzawl == null) {
            return false;
        }
        zzawl.zzvs();
        return false;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaoa() {
        this.zzfsg.onAdLeftApplication();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(String str, String str2) {
        this.zzget.onAppEvent(str, str2);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaob() {
        this.zzfxw.onAdClicked();
    }
}

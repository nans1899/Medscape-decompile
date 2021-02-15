package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzbtp;
import com.google.android.gms.internal.ads.zzbys;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdai implements zzdae<zzbqo> {
    /* access modifiers changed from: private */
    public final zzbix zzgpt;
    private final Context zzgsl;
    private final zzdom zzgsm;
    /* access modifiers changed from: private */
    public final zzdac zzgtw;
    private zzbqv zzgtx;

    public zzdai(zzbix zzbix, Context context, zzdac zzdac, zzdom zzdom) {
        this.zzgpt = zzbix;
        this.zzgsl = context;
        this.zzgtw = zzdac;
        this.zzgsm = zzdom;
    }

    public final boolean isLoading() {
        zzbqv zzbqv = this.zzgtx;
        return zzbqv != null && zzbqv.isLoading();
    }

    public final boolean zza(zzvg zzvg, String str, zzdad zzdad, zzdag<? super zzbqo> zzdag) throws RemoteException {
        zzcda zzcda;
        zzp.zzkr();
        if (zzayu.zzbe(this.zzgsl) && zzvg.zzcho == null) {
            zzayp.zzfc("Failed to load the ad because app ID is missing.");
            this.zzgpt.zzadi().execute(new zzdah(this));
            return false;
        } else if (str == null) {
            zzayp.zzfc("Ad unit ID should not be null for NativeAdLoader.");
            this.zzgpt.zzadi().execute(new zzdak(this));
            return false;
        } else {
            zzdox.zze(this.zzgsl, zzvg.zzche);
            zzdok zzatn = this.zzgsm.zzh(zzvg).zzec(zzdad instanceof zzdaf ? ((zzdaf) zzdad).zzgtu : 1).zzatn();
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyi)).booleanValue()) {
                zzcda = this.zzgpt.zzadu().zza(new zzbtp.zza().zzce(this.zzgsl).zza(zzatn).zzajv()).zza(new zzbys.zza().zzakr()).zza(this.zzgtw.zzaqw()).zzaex();
            } else {
                zzcda = this.zzgpt.zzadu().zza(new zzbtp.zza().zzce(this.zzgsl).zza(zzatn).zzajv()).zza(new zzbys.zza().zza(this.zzgtw.zzaqz(), this.zzgpt.zzadi()).zza(this.zzgtw.zzara(), this.zzgpt.zzadi()).zza(this.zzgtw.zzarb(), this.zzgpt.zzadi()).zza(this.zzgtw.zzarc(), this.zzgpt.zzadi()).zza(this.zzgtw.zzaqy(), this.zzgpt.zzadi()).zza(zzatn.zzhfg, this.zzgpt.zzadi()).zzakr()).zza(this.zzgtw.zzaqw()).zzaex();
            }
            this.zzgpt.zzaea().zzed(1);
            zzbqv zzbqv = new zzbqv(this.zzgpt.zzadk(), this.zzgpt.zzadj(), zzcda.zzaev().zzajh());
            this.zzgtx = zzbqv;
            zzbqv.zza((zzdyr<zzbqo>) new zzdaj(this, zzdag, zzcda));
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzard() {
        this.zzgtw.zzara().zzk(zzdpe.zza(zzdpg.INVALID_AD_UNIT_ID, (String) null, (zzva) null));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzare() {
        this.zzgtw.zzara().zzk(zzdpe.zza(zzdpg.APP_ID_MISSING, (String) null, (zzva) null));
    }
}

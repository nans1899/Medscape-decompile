package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.internal.ads.zzbtp;
import com.google.android.gms.internal.ads.zzbys;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdnb implements zzdae<zzcjg> {
    /* access modifiers changed from: private */
    public final Executor zzfoc;
    private final zzdoj zzfuj;
    private final zzdom zzgsm;
    private final zzbix zzguj;
    private final Context zzhah;
    /* access modifiers changed from: private */
    public final zzdlv<zzcjj, zzcjg> zzhaj;
    /* access modifiers changed from: private */
    public final zzdmc zzhcq;
    private zzdzc<zzcjg> zzhcr;

    public zzdnb(Context context, Executor executor, zzbix zzbix, zzdlv<zzcjj, zzcjg> zzdlv, zzdmc zzdmc, zzdom zzdom, zzdoj zzdoj) {
        this.zzhah = context;
        this.zzfoc = executor;
        this.zzguj = zzbix;
        this.zzhaj = zzdlv;
        this.zzhcq = zzdmc;
        this.zzgsm = zzdom;
        this.zzfuj = zzdoj;
    }

    public final boolean isLoading() {
        zzdzc<zzcjg> zzdzc = this.zzhcr;
        return zzdzc != null && !zzdzc.isDone();
    }

    public final boolean zza(zzvg zzvg, String str, zzdad zzdad, zzdag<? super zzcjg> zzdag) throws RemoteException {
        zzaum zzaum = new zzaum(zzvg, str);
        String str2 = zzdad instanceof zzdmy ? ((zzdmy) zzdad).zzhco : null;
        if (zzaum.zzbuu == null) {
            zzayp.zzfc("Ad unit ID should not be null for rewarded video ad.");
            this.zzfoc.execute(new zzdna(this));
            return false;
        }
        zzdzc<zzcjg> zzdzc = this.zzhcr;
        if (zzdzc != null && !zzdzc.isDone()) {
            return false;
        }
        zzdox.zze(this.zzhah, zzaum.zzdqr.zzche);
        zzdok zzatn = this.zzgsm.zzgt(zzaum.zzbuu).zze(zzvn.zzpl()).zzh(zzaum.zzdqr).zzatn();
        zzdnh zzdnh = new zzdnh((zzdnc) null);
        zzdnh.zzfsk = zzatn;
        zzdnh.zzhco = str2;
        zzdzc<zzcjg> zza = this.zzhaj.zza(new zzdlw(zzdnh), new zzdnd(this));
        this.zzhcr = zza;
        zzdyq.zza(zza, new zzdnc(this, zzdag, zzdnh), this.zzfoc);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final void zzea(int i) {
        this.zzgsm.zzatm().zzeb(i);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzcjm zze(zzdlu zzdlu) {
        zzdnh zzdnh = (zzdnh) zzdlu;
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcyf)).booleanValue()) {
            return this.zzguj.zzadv().zzf(new zzbtp.zza().zzce(this.zzhah).zza(zzdnh.zzfsk).zzfx(zzdnh.zzhco).zza(this.zzfuj).zzajv()).zzf(new zzbys.zza().zzakr());
        }
        zzdmc zzb = zzdmc.zzb(this.zzhcq);
        return this.zzguj.zzadv().zzf(new zzbtp.zza().zzce(this.zzhah).zza(zzdnh.zzfsk).zzfx(zzdnh.zzhco).zza(this.zzfuj).zzajv()).zzf(new zzbys.zza().zza((zzbuh) zzb, this.zzfoc).zza((zzbvs) zzb, this.zzfoc).zza((zzbui) zzb, this.zzfoc).zza((AdMetadataListener) zzb, this.zzfoc).zza((zzbuv) zzb, this.zzfoc).zza((zzbwl) zzb, this.zzfoc).zza((zzdls) zzb).zzakr());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzatc() {
        this.zzhcq.zzk(zzdpe.zza(zzdpg.INVALID_AD_UNIT_ID, (String) null, (zzva) null));
    }
}

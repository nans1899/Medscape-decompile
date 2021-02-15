package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbqo;
import com.google.android.gms.internal.ads.zzbtn;
import com.google.android.gms.internal.ads.zzua;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdkz<R extends zzbtn<AdT>, AdT extends zzbqo> implements zzdlv<R, AdT> {
    private final Executor executor;
    private final zzdlv<R, AdT> zzhaj;
    private final zzdlv<R, zzdli<AdT>> zzhbl;
    private final zzdqs<AdT> zzhbm;
    private R zzhbn;

    public zzdkz(zzdlv<R, AdT> zzdlv, zzdlv<R, zzdli<AdT>> zzdlv2, zzdqs<AdT> zzdqs, Executor executor2) {
        this.zzhaj = zzdlv;
        this.zzhbl = zzdlv2;
        this.zzhbm = zzdqs;
        this.executor = executor2;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzasw */
    public final synchronized R zzasv() {
        return this.zzhbn;
    }

    public final synchronized zzdzc<AdT> zza(zzdlw zzdlw, zzdlx<R> zzdlx) {
        zzdok zzafs;
        zzdlx<R> zzdlx2;
        zzdlw zzdlw2;
        zzafs = ((zzbtn) zzdlx.zzc(zzdlw.zzhcf).zzaey()).zzafs();
        zzdlx2 = zzdlx;
        zzdlw2 = zzdlw;
        return zzdyl.zzg(this.zzhbl.zza(zzdlw, zzdlx)).zzb(new zzdky(this, zzdlw, new zzdlc(zzdlx2, zzdlw2, zzafs.zzhfa, zzafs.zzhfb, this.executor, zzafs.zzhfe, (zzdqj) null), zzdlx), this.executor);
    }

    private final zzdzc<AdT> zza(zzdqk<AdT> zzdqk, zzdlw zzdlw, zzdlx<R> zzdlx) {
        zzbtq<R> zzc = zzdlx.zzc(zzdlw.zzhcf);
        if (zzdqk.zzhir != null) {
            zzbtn zzbtn = (zzbtn) zzc.zzaey();
            if (zzbtn.zzaft() != null) {
                zzdqk.zzhir.zzaiy().zzb(zzbtn.zzaft());
            }
            return zzdyq.zzaf(zzdqk.zzhir);
        }
        zzc.zza(zzdqk.zzevd);
        zzdzc<AdT> zza = this.zzhaj.zza(zzdlw, new zzdlb(zzc));
        this.zzhbn = (zzbtn) this.zzhaj.zzasv();
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdlx zzdlx, zzdqw zzdqw) throws Exception {
        if (zzdqw == null || zzdqw.zzhbz == null || zzdqw.zzhjh == null) {
            throw new zzcof(zzdpg.INTERNAL_ERROR, "Empty prefetch");
        }
        zzdqw.zzhbz.zzhiq.zzaji().zze((zzua.zzb) ((zzejz) zzua.zzb.zzni().zza(zzua.zzb.zza.zzng().zzb(zzua.zzb.zzc.IN_MEMORY).zza(zzua.zzb.zzd.zznk())).zzbgt()));
        return zza(zzdqw.zzhbz, ((zzdlc) zzdqw.zzhjh).zzhbr, zzdlx);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(zzdlw zzdlw, zzdlc zzdlc, zzdlx zzdlx, zzdli zzdli) throws Exception {
        if (zzdli != null) {
            zzdlc zzdlc2 = new zzdlc(zzdlc.zzhbq, zzdlc.zzhbr, zzdlc.zzdqr, zzdlc.zzbuu, zzdlc.executor, zzdlc.zzgvr, zzdli.zzhbs);
            if (zzdli.zzhbz != null) {
                this.zzhbn = null;
                this.zzhbm.zzb((zzdqy) zzdlc2);
                return zza(zzdli.zzhbz, zzdlw, zzdlx);
            }
            zzdzc<zzdqw<AdT>> zzc = this.zzhbm.zzc((zzdqy) zzdlc2);
            if (zzc != null) {
                this.zzhbn = (zzbtn) zzdlx.zzc(zzdlw.zzhcf).zzaey();
                return zzdyq.zzb(zzc, new zzdla(this, zzdlx), this.executor);
            }
            this.zzhbm.zzb((zzdqy) zzdlc2);
            zzdlw = new zzdlw(zzdlw.zzhcf, zzdli.zzgkg);
        }
        zzdzc<AdT> zza = this.zzhaj.zza(zzdlw, zzdlx);
        this.zzhbn = (zzbtn) this.zzhaj.zzasv();
        return zza;
    }
}

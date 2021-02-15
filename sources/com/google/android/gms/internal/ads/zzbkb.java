package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbkb extends zzdhp {
    private zzeph<String> zzevp;
    private zzeph<zzcmg> zzewf;
    private zzeph<Map<zzdsf, zzcmg>> zzewh;
    private zzeph<Set<zzcab<zzdso>>> zzewj;
    private zzeph<Set<zzcab<zzdso>>> zzewq;
    private zzeph zzewr;
    private zzeph<zzdsi> zzews;
    private zzeph<ApplicationInfo> zzexd;
    private zzeph<zzdit> zzeyi;
    private final /* synthetic */ zzbjv zzfbq;
    private final zzdiy zzffu;
    private zzeph<zzdhl> zzffv;
    private zzeph<String> zzffw;
    private zzeph<zzdhv> zzffx;
    private zzeph<Integer> zzffy;
    private zzeph<zzdhz> zzffz;
    private zzeph<zzdif> zzfga;
    private zzeph<zzdik> zzfgb;
    private zzeph<Boolean> zzfgc;
    private zzeph<zzdix> zzfgd;
    private zzeph<zzdjl> zzfge;
    private zzeph<zzcmg> zzfgf;
    private zzeph<zzcmg> zzfgg;
    private zzeph<zzcmg> zzfgh;

    private zzbkb(zzbjv zzbjv, zzdiy zzdiy) {
        this.zzfbq = zzbjv;
        this.zzffu = zzdiy;
        this.zzffv = new zzdhm(zzblw.zzfmr, this.zzfbq.zzetf, this.zzfbq.zzeta, zzdri.zzavg());
        this.zzffw = new zzdjb(zzdiy);
        this.zzffx = new zzdhx(zzbjm.zzess, this.zzfbq.zzetf, this.zzffw, zzdri.zzavg());
        this.zzffy = new zzdjc(zzdiy);
        this.zzffz = new zzdid(zzblw.zzfmr, this.zzffy, this.zzfbq.zzetf, this.zzfbq.zzeua, this.zzfbq.zzeta, zzdri.zzavg());
        this.zzfga = new zzdih(zzblq.zzfmn, zzdri.zzavg(), this.zzfbq.zzetf);
        this.zzfgb = new zzdim(zzbls.zzfmo, zzdri.zzavg(), this.zzffw);
        this.zzfgc = new zzdjd(zzdiy);
        this.zzexd = new zzdja(zzdiy);
        this.zzeyi = new zzdiv(this.zzfbq.zzeuc, this.zzfbq.zzeta, this.zzfgc, this.zzexd);
        this.zzfgd = new zzdiz(zzblu.zzfmp, this.zzfbq.zzeta, this.zzfbq.zzetf);
        this.zzfge = new zzdjn(zzdri.zzavg());
        this.zzevp = new zzdjf(zzdiy);
        this.zzewf = zzeov.zzas(zzclv.zzaor());
        this.zzfgf = zzeov.zzas(zzclt.zzaoq());
        this.zzfgg = zzeov.zzas(zzclx.zzaot());
        this.zzfgh = zzeov.zzas(zzclz.zzaov());
        this.zzewh = ((zzepc) ((zzepc) ((zzepc) ((zzepc) zzepa.zzic(4).zza(zzdsf.GMS_SIGNALS, this.zzewf)).zza(zzdsf.BUILD_URL, this.zzfgf)).zza(zzdsf.HTTP, this.zzfgg)).zza(zzdsf.PRE_PROCESS, this.zzfgh)).zzbkj();
        this.zzewj = zzeov.zzas(new zzcmb(this.zzevp, this.zzfbq.zzetf, zzdri.zzavg(), this.zzewh));
        zzepd<Set<zzcab<zzdso>>> zzbkk = zzepd.zzar(0, 1).zzav(this.zzewj).zzbkk();
        this.zzewq = zzbkk;
        this.zzewr = zzdsq.zzar(zzbkk);
        this.zzews = zzeov.zzas(zzdsn.zzx(zzdri.zzavg(), this.zzfbq.zzeta, this.zzewr));
    }

    private final zzdio zzafk() {
        return new zzdio((zzaao) zzepe.zza(new zzaao(), "Cannot return null from a non-@Nullable @Provides method"), zzdri.zzavh(), (List) zzepe.zza(this.zzffu.zzasj(), "Cannot return null from a non-@Nullable @Provides method"));
    }

    private final zzdhr zzafl() {
        return new zzdhr(zzblt.zzahb(), zzdri.zzavh(), (String) zzepe.zza(this.zzffu.zzasg(), "Cannot return null from a non-@Nullable @Provides method"), this.zzffu.zzash());
    }

    public final zzdgw<JSONObject> zzafm() {
        zzdzb zzavh = zzdri.zzavh();
        long longValue = ((Long) zzwm.zzpx().zzd(zzabb.zzcsm)).longValue();
        long longValue2 = ((Long) zzwm.zzpx().zzd(zzabb.zzcsu)).longValue();
        return zzdhb.zza(zzavh, zzdws.zza((zzdgx) zzepe.zza(new zzdfi(new zzdik(zzblp.zzagx(), zzdri.zzavh(), zzdjb.zzc(this.zzffu)), 0, (ScheduledExecutorService) this.zzfbq.zzeta.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzdgx) zzepe.zza(new zzdfi(new zzdit(zzblc.zzb(this.zzfbq.zzesw), (ScheduledExecutorService) this.zzfbq.zzeta.get(), this.zzffu.zzasi(), zzdja.zzb(this.zzffu)), longValue, (ScheduledExecutorService) this.zzfbq.zzeta.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzdgx) zzepe.zza(new zzdfi(new zzdix(zzblr.zzagz(), (ScheduledExecutorService) this.zzfbq.zzeta.get(), zzbjd.zza(this.zzfbq.zzesx)), longValue2, (ScheduledExecutorService) this.zzfbq.zzeta.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzdgx) zzepe.zza(new zzdfi(new zzdhl(zzblt.zzahb(), zzbjd.zza(this.zzfbq.zzesx), (ScheduledExecutorService) this.zzfbq.zzeta.get(), zzdri.zzavh()), 0, (ScheduledExecutorService) this.zzfbq.zzeta.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzdgx) zzepe.zza(new zzdfi(new zzdjl(zzdri.zzavh()), 0, (ScheduledExecutorService) this.zzfbq.zzeta.get()), "Cannot return null from a non-@Nullable @Provides method"), (zzdgx) zzepe.zza(zzdje.zzasn(), "Cannot return null from a non-@Nullable @Provides method"), new zzdhv((zzatj) null, zzbjd.zza(this.zzfbq.zzesx), zzdjb.zzc(this.zzffu), zzdri.zzavh()), new zzdif(zzbln.zzagv(), zzdri.zzavh(), zzbjd.zza(this.zzfbq.zzesx)), zzafk(), zzafl(), new zzdhz(zzblt.zzahb(), this.zzffu.zzasm(), zzbjd.zza(this.zzfbq.zzesx), (zzayb) this.zzfbq.zzeua.get(), (ScheduledExecutorService) this.zzfbq.zzeta.get(), zzdri.zzavh()), (zzdgx) this.zzfbq.zzeub.get()));
    }

    public final zzdgw<JSONObject> zzafn() {
        return zzdjj.zza(zzblp.zzagx(), this.zzfbq.zzeub.get(), zzafl(), zzafk(), zzeov.zzat(this.zzffv), zzeov.zzat(this.zzffx), zzeov.zzat(this.zzffz), zzeov.zzat(this.zzfga), zzeov.zzat(this.zzfgb), zzeov.zzat(this.zzeyi), zzeov.zzat(this.zzfgd), zzeov.zzat(this.zzfge), zzdri.zzavh(), (ScheduledExecutorService) this.zzfbq.zzeta.get());
    }

    public final zzdsi zzafo() {
        return this.zzews.get();
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.AdMetadataListener;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdmc extends AdMetadataListener implements zzbuh, zzbui, zzbuv, zzbvs, zzbwl, zzdls {
    private final zzdpw zzhao;
    private final AtomicReference<AdMetadataListener> zzhcg = new AtomicReference<>();
    private final AtomicReference<zzavg> zzhch = new AtomicReference<>();
    private final AtomicReference<zzavd> zzhci = new AtomicReference<>();
    private final AtomicReference<zzaug> zzhcj = new AtomicReference<>();
    private final AtomicReference<zzavl> zzhck = new AtomicReference<>();
    private final AtomicReference<zzaub> zzhcl = new AtomicReference<>();
    private final AtomicReference<zzyi> zzhcm = new AtomicReference<>();
    private zzdmc zzhcn = null;

    public zzdmc(zzdpw zzdpw) {
        this.zzhao = zzdpw;
    }

    public static zzdmc zzb(zzdmc zzdmc) {
        zzdmc zzdmc2 = new zzdmc(zzdmc.zzhao);
        zzdmc2.zzb((zzdls) zzdmc);
        return zzdmc2;
    }

    public final void zzb(zzavg zzavg) {
        this.zzhch.set(zzavg);
    }

    public final void zzb(zzavd zzavd) {
        this.zzhci.set(zzavd);
    }

    public final void zzb(zzavl zzavl) {
        this.zzhck.set(zzavl);
    }

    public final void zza(AdMetadataListener adMetadataListener) {
        this.zzhcg.set(adMetadataListener);
    }

    public final void zzc(zzyi zzyi) {
        this.zzhcm.set(zzyi);
    }

    @Deprecated
    public final void zzb(zzaug zzaug) {
        this.zzhcj.set(zzaug);
    }

    @Deprecated
    public final void zzb(zzaub zzaub) {
        this.zzhcl.set(zzaub);
    }

    public final void onAdLoaded() {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhch, zzdmf.zzhaw);
                zzdll.zza(zzdmc.zzhcj, zzdme.zzhaw);
                return;
            }
        }
    }

    public final void zzk(zzva zzva) {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                int i = zzva.errorCode;
                zzdll.zza(zzdmc.zzhch, new zzdmr(zzva));
                zzdll.zza(zzdmc.zzhch, new zzdmq(i));
                zzdll.zza(zzdmc.zzhcj, new zzdmt(i));
                return;
            }
        }
    }

    public final void onAdOpened() {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhci, zzdms.zzhaw);
                zzdll.zza(zzdmc.zzhcj, zzdmv.zzhaw);
                return;
            }
        }
    }

    public final void onAdClosed() {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdmc.zzhao.onAdClosed();
                zzdll.zza(zzdmc.zzhci, zzdmu.zzhaw);
                zzdll.zza(zzdmc.zzhcj, zzdmx.zzhaw);
                return;
            }
        }
    }

    public final void onAdLeftApplication() {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhcj, zzdmw.zzhaw);
                return;
            }
        }
    }

    public final void onRewardedVideoStarted() {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhcj, zzdmh.zzhaw);
                return;
            }
        }
    }

    public final void zzb(zzatw zzatw, String str, String str2) {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhci, new zzdmg(zzatw));
                zzdll.zza(zzdmc.zzhck, new zzdmj(zzatw, str, str2));
                zzdll.zza(zzdmc.zzhcj, new zzdmi(zzatw));
                zzdll.zza(zzdmc.zzhcl, new zzdml(zzatw, str, str2));
                return;
            }
        }
    }

    public final void onRewardedVideoCompleted() {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhcj, zzdmk.zzhaw);
                return;
            }
        }
    }

    public final void zzj(zzva zzva) {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhci, new zzdmn(zzva));
                zzdll.zza(zzdmc.zzhci, new zzdmm(zzva));
                return;
            }
        }
    }

    public final void onAdMetadataChanged() {
        zzdmc zzdmc = this.zzhcn;
        if (zzdmc != null) {
            zzdmc.onAdMetadataChanged();
        } else {
            zzdll.zza(this.zzhcg, zzdmp.zzhaw);
        }
    }

    public final void zzb(zzvp zzvp) {
        zzdmc zzdmc = this;
        while (true) {
            zzdmc zzdmc2 = zzdmc.zzhcn;
            if (zzdmc2 != null) {
                zzdmc = zzdmc2;
            } else {
                zzdll.zza(zzdmc.zzhcm, new zzdmo(zzvp));
                return;
            }
        }
    }

    public final void zzb(zzdls zzdls) {
        this.zzhcn = (zzdmc) zzdls;
    }
}

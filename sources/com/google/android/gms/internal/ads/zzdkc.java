package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.zzp;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdkc implements zzp, zzbui, zzbwb, zzdls {
    private final zzdpw zzhao;
    private final AtomicReference<zzsh> zzhap = new AtomicReference<>();
    private final AtomicReference<zzsm> zzhaq = new AtomicReference<>();
    private final AtomicReference<zzsq> zzhar = new AtomicReference<>();
    private final AtomicReference<zzbwb> zzhas = new AtomicReference<>();
    private final AtomicReference<zzp> zzhat = new AtomicReference<>();
    private zzdkc zzhau = null;

    public zzdkc(zzdpw zzdpw) {
        this.zzhao = zzdpw;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void zzun() {
    }

    public static zzdkc zzb(zzdkc zzdkc) {
        zzdkc zzdkc2 = new zzdkc(zzdkc.zzhao);
        zzdkc2.zzb((zzdls) zzdkc);
        return zzdkc2;
    }

    public final void zzb(zzsh zzsh) {
        this.zzhap.set(zzsh);
    }

    public final void zzb(zzsm zzsm) {
        this.zzhaq.set(zzsm);
    }

    public final void zza(zzsq zzsq) {
        this.zzhar.set(zzsq);
    }

    public final void zza(zzbwb zzbwb) {
        this.zzhas.set(zzbwb);
    }

    public final void zza(zzp zzp) {
        this.zzhat.set(zzp);
    }

    public final void zzb(zzsg zzsg) {
        zzdkc zzdkc = this;
        while (true) {
            zzdkc zzdkc2 = zzdkc.zzhau;
            if (zzdkc2 != null) {
                zzdkc = zzdkc2;
            } else {
                zzdll.zza(zzdkc.zzhap, new zzdkf(zzsg));
                return;
            }
        }
    }

    public final void zzk(zzva zzva) {
        zzdkc zzdkc = this;
        while (true) {
            zzdkc zzdkc2 = zzdkc.zzhau;
            if (zzdkc2 != null) {
                zzdkc = zzdkc2;
            } else {
                zzdll.zza(zzdkc.zzhap, new zzdke(zzva));
                zzdll.zza(zzdkc.zzhap, new zzdkh(zzva));
                return;
            }
        }
    }

    public final void onAdClosed() {
        zzdkc zzdkc = this;
        while (true) {
            zzdkc zzdkc2 = zzdkc.zzhau;
            if (zzdkc2 != null) {
                zzdkc = zzdkc2;
            } else {
                zzdkc.zzhao.onAdClosed();
                zzdll.zza(zzdkc.zzhaq, zzdkg.zzhaw);
                zzdll.zza(zzdkc.zzhar, zzdkj.zzhaw);
                return;
            }
        }
    }

    public final void zzajx() {
        zzdkc zzdkc = this;
        while (true) {
            zzdkc zzdkc2 = zzdkc.zzhau;
            if (zzdkc2 != null) {
                zzdkc = zzdkc2;
            } else {
                zzdll.zza(zzdkc.zzhas, zzdki.zzhaw);
                zzdll.zza(zzdkc.zzhar, zzdkl.zzhaw);
                return;
            }
        }
    }

    public final void zzum() {
        zzdkc zzdkc = this;
        while (true) {
            zzdkc zzdkc2 = zzdkc.zzhau;
            if (zzdkc2 != null) {
                zzdkc = zzdkc2;
            } else {
                zzdll.zza(zzdkc.zzhat, zzdkk.zzhaw);
                return;
            }
        }
    }

    public final void zzb(zzdls zzdls) {
        this.zzhau = (zzdkc) zzdls;
    }
}

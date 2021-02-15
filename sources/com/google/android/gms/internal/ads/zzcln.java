package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zztu;
import com.google.android.gms.internal.ads.zzua;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcln implements zzbui, zzbuz, zzbvs, zzbws, zzbyo, zzux {
    private final zzts zzggj;
    private boolean zzggk = false;

    public zzcln(zzts zzts, @Nullable zzdmd zzdmd) {
        this.zzggj = zzts;
        zzts.zza(zztu.zza.zzb.AD_REQUEST);
        if (zzdmd != null) {
            zzts.zza(zztu.zza.zzb.REQUEST_IS_PREFETCH);
        }
    }

    public final void zzd(zzatc zzatc) {
    }

    public final void zzb(zzdog zzdog) {
        this.zzggj.zza((zztr) new zzclq(zzdog));
    }

    public final void onAdLoaded() {
        this.zzggj.zza(zztu.zza.zzb.AD_LOADED);
    }

    public final void zzk(zzva zzva) {
        switch (zzva.errorCode) {
            case 1:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD_INVALID_REQUEST);
                return;
            case 2:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD_NETWORK_ERROR);
                return;
            case 3:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD_NO_FILL);
                return;
            case 4:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD_TIMEOUT);
                return;
            case 5:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD_CANCELLED);
                return;
            case 6:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD_NO_ERROR);
                return;
            case 7:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD_NOT_FOUND);
                return;
            default:
                this.zzggj.zza(zztu.zza.zzb.AD_FAILED_TO_LOAD);
                return;
        }
    }

    public final synchronized void onAdImpression() {
        this.zzggj.zza(zztu.zza.zzb.AD_IMPRESSION);
    }

    public final synchronized void onAdClicked() {
        if (!this.zzggk) {
            this.zzggj.zza(zztu.zza.zzb.AD_FIRST_CLICK);
            this.zzggk = true;
            return;
        }
        this.zzggj.zza(zztu.zza.zzb.AD_SUBSEQUENT_CLICK);
    }

    public final void zzc(zzua.zzb zzb) {
        this.zzggj.zza((zztr) new zzclp(zzb));
        this.zzggj.zza(zztu.zza.zzb.REQUEST_LOADED_FROM_CACHE);
    }

    public final void zzd(zzua.zzb zzb) {
        this.zzggj.zza((zztr) new zzcls(zzb));
        this.zzggj.zza(zztu.zza.zzb.REQUEST_SAVED_TO_CACHE);
    }

    public final void zze(zzua.zzb zzb) {
        this.zzggj.zza((zztr) new zzclr(zzb));
        this.zzggj.zza(zztu.zza.zzb.REQUEST_PREFETCH_INTERCEPTED);
    }

    public final void zzbg(boolean z) {
        zztu.zza.zzb zzb;
        zzts zzts = this.zzggj;
        if (z) {
            zzb = zztu.zza.zzb.REQUESTED_CACHE_KEY_FROM_SERVICE_SUCCEEDED;
        } else {
            zzb = zztu.zza.zzb.REQUESTED_CACHE_KEY_FROM_SERVICE_FAILED;
        }
        zzts.zza(zzb);
    }

    public final void zzbh(boolean z) {
        zztu.zza.zzb zzb;
        zzts zzts = this.zzggj;
        if (z) {
            zzb = zztu.zza.zzb.NOTIFIED_CACHE_HIT_TO_SERVICE_SUCCEEDED;
        } else {
            zzb = zztu.zza.zzb.NOTIFIED_CACHE_HIT_TO_SERVICE_FAILED;
        }
        zzts.zza(zzb);
    }

    public final void zzake() {
        this.zzggj.zza(zztu.zza.zzb.REQUEST_FAILED_TO_LOAD_FROM_CACHE);
    }
}

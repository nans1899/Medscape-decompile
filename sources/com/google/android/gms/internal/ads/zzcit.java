package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.overlay.zzv;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzcit implements zzp, zzv, zzagt, zzagv, zzux {
    private zzux zzcgv;
    private zzagt zzdep;
    private zzagv zzdeq;
    private zzp zzdpm;
    private zzv zzdpq;

    private zzcit() {
    }

    public final synchronized void onAdClicked() {
        if (this.zzcgv != null) {
            this.zzcgv.onAdClicked();
        }
    }

    public final synchronized void zzum() {
        if (this.zzdpm != null) {
            this.zzdpm.zzum();
        }
    }

    public final synchronized void onPause() {
        if (this.zzdpm != null) {
            this.zzdpm.onPause();
        }
    }

    public final synchronized void onResume() {
        if (this.zzdpm != null) {
            this.zzdpm.onResume();
        }
    }

    public final synchronized void zzun() {
        if (this.zzdpm != null) {
            this.zzdpm.zzun();
        }
    }

    public final synchronized void zzvd() {
        if (this.zzdpq != null) {
            this.zzdpq.zzvd();
        }
    }

    public final synchronized void zza(String str, Bundle bundle) {
        if (this.zzdep != null) {
            this.zzdep.zza(str, bundle);
        }
    }

    public final synchronized void onAppEvent(String str, String str2) {
        if (this.zzdeq != null) {
            this.zzdeq.onAppEvent(str, str2);
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zza(zzux zzux, zzagt zzagt, zzp zzp, zzagv zzagv, zzv zzv) {
        this.zzcgv = zzux;
        this.zzdep = zzagt;
        this.zzdpm = zzp;
        this.zzdeq = zzagv;
        this.zzdpq = zzv;
    }

    /* synthetic */ zzcit(zzciq zzciq) {
        this();
    }
}

package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzarb;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzs extends zzarb {
    private Activity zzaas;
    private boolean zzdou = false;
    private AdOverlayInfoParcel zzdpw;
    private boolean zzdpx = false;

    public zzs(Activity activity, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.zzdpw = adOverlayInfoParcel;
        this.zzaas = activity;
    }

    public final void onActivityResult(int i, int i2, Intent intent) throws RemoteException {
    }

    public final void onBackPressed() throws RemoteException {
    }

    public final void onRestart() throws RemoteException {
    }

    public final void onStart() throws RemoteException {
    }

    public final void zzad(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final void zzdq() throws RemoteException {
    }

    public final boolean zzut() throws RemoteException {
        return false;
    }

    public final void onCreate(Bundle bundle) {
        boolean z = false;
        if (bundle != null && bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false)) {
            z = true;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzdpw;
        if (adOverlayInfoParcel == null) {
            this.zzaas.finish();
        } else if (z) {
            this.zzaas.finish();
        } else {
            if (bundle == null) {
                if (adOverlayInfoParcel.zzcgv != null) {
                    this.zzdpw.zzcgv.onAdClicked();
                }
                if (!(this.zzaas.getIntent() == null || !this.zzaas.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true) || this.zzdpw.zzdpm == null)) {
                    this.zzdpw.zzdpm.zzun();
                }
            }
            zzp.zzkp();
            if (!zza.zza((Context) this.zzaas, this.zzdpw.zzdpl, this.zzdpw.zzdpq)) {
                this.zzaas.finish();
            }
        }
    }

    public final void onResume() throws RemoteException {
        if (this.zzdou) {
            this.zzaas.finish();
            return;
        }
        this.zzdou = true;
        if (this.zzdpw.zzdpm != null) {
            this.zzdpw.zzdpm.onResume();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) throws RemoteException {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdou);
    }

    public final void onPause() throws RemoteException {
        if (this.zzdpw.zzdpm != null) {
            this.zzdpw.zzdpm.onPause();
        }
        if (this.zzaas.isFinishing()) {
            zzvc();
        }
    }

    public final void onStop() throws RemoteException {
        if (this.zzaas.isFinishing()) {
            zzvc();
        }
    }

    public final void onDestroy() throws RemoteException {
        if (this.zzaas.isFinishing()) {
            zzvc();
        }
    }

    private final synchronized void zzvc() {
        if (!this.zzdpx) {
            if (this.zzdpw.zzdpm != null) {
                this.zzdpw.zzdpm.zzum();
            }
            this.zzdpx = true;
        }
    }
}

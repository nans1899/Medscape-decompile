package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdtq implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final Object lock = new Object();
    private boolean zzgke = false;
    private boolean zzgkf = false;
    private final zzduk zzhmu;
    private final zzduc zzhmv;

    zzdtq(Context context, Looper looper, zzduc zzduc) {
        this.zzhmv = zzduc;
        this.zzhmu = new zzduk(context, looper, this, this, 12800000);
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public final void onConnectionSuspended(int i) {
    }

    /* access modifiers changed from: package-private */
    public final void zzawd() {
        synchronized (this.lock) {
            if (!this.zzgke) {
                this.zzgke = true;
                this.zzhmu.checkAvailabilityAndConnect();
            }
        }
    }

    private final void zzapz() {
        synchronized (this.lock) {
            if (this.zzhmu.isConnected() || this.zzhmu.isConnecting()) {
                this.zzhmu.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    public final void onConnected(Bundle bundle) {
        synchronized (this.lock) {
            if (!this.zzgkf) {
                this.zzgkf = true;
                try {
                    this.zzhmu.zzawm().zza(new zzdui(this.zzhmv.toByteArray()));
                    zzapz();
                } catch (Exception unused) {
                    zzapz();
                } catch (Throwable th) {
                    zzapz();
                    throw th;
                }
            }
        }
    }
}

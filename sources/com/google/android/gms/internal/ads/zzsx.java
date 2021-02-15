package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzsx implements BaseGmsClient.BaseConnectionCallbacks {
    private final /* synthetic */ zzsw zzbuz;

    zzsx(zzsw zzsw) {
        this.zzbuz = zzsw;
    }

    public final void onConnected(Bundle bundle) {
        synchronized (this.zzbuz.lock) {
            try {
                if (this.zzbuz.zzbvb != null) {
                    zztf unused = this.zzbuz.zzbvc = this.zzbuz.zzbvb.zzmz();
                }
            } catch (DeadObjectException e) {
                zzayp.zzc("Unable to obtain a cache service instance.", e);
                this.zzbuz.disconnect();
            }
            this.zzbuz.lock.notifyAll();
        }
    }

    public final void onConnectionSuspended(int i) {
        synchronized (this.zzbuz.lock) {
            zztf unused = this.zzbuz.zzbvc = null;
            this.zzbuz.lock.notifyAll();
        }
    }
}

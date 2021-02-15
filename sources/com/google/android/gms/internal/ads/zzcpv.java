package com.google.android.gms.internal.ads;

import android.os.Binder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzcpv implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    protected final Object mLock = new Object();
    protected final zzbcg<InputStream> zzdja = new zzbcg<>();
    protected boolean zzgke = false;
    protected boolean zzgkf = false;
    protected zzatc zzgkg;
    protected zzask zzgkh;

    /* access modifiers changed from: protected */
    public final void zzapz() {
        synchronized (this.mLock) {
            this.zzgkf = true;
            if (this.zzgkh.isConnected() || this.zzgkh.isConnecting()) {
                this.zzgkh.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    public void onConnectionSuspended(int i) {
        zzayp.zzef("Cannot connect to remote service, fallback to local instance.");
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        zzayp.zzef("Disconnected from remote ad request service.");
        this.zzdja.setException(new zzcqm(zzdpg.INTERNAL_ERROR));
    }
}

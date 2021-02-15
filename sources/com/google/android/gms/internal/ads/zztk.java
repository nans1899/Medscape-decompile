package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.ads.internal.zzp;
import java.io.InputStream;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zztk {
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public zztb zzbvb;
    /* access modifiers changed from: private */
    public boolean zzbvm;
    private final Context zzvr;

    zztk(Context context) {
        this.zzvr = context;
    }

    /* access modifiers changed from: package-private */
    public final Future<InputStream> zzb(zzte zzte) {
        zztj zztj = new zztj(this);
        zztm zztm = new zztm(this, zzte, zztj);
        zztq zztq = new zztq(this, zztj);
        synchronized (this.lock) {
            zztb zztb = new zztb(this.zzvr, zzp.zzlf().zzyj(), zztm, zztq);
            this.zzbvb = zztb;
            zztb.checkAvailabilityAndConnect();
        }
        return zztj;
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.lock) {
            if (this.zzbvb != null) {
                this.zzbvb.disconnect();
                this.zzbvb = null;
                Binder.flushPendingCommands();
            }
        }
    }
}

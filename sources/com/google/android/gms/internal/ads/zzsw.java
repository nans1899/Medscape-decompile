package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzsw {
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    private final Runnable zzbva = new zzsv(this);
    /* access modifiers changed from: private */
    public zztb zzbvb;
    /* access modifiers changed from: private */
    public zztf zzbvc;
    private Context zzvr;

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0047, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initialize(android.content.Context r3) {
        /*
            r2 = this;
            if (r3 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = r2.lock
            monitor-enter(r0)
            android.content.Context r1 = r2.zzvr     // Catch:{ all -> 0x0048 }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x000c:
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x0048 }
            r2.zzvr = r3     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zzabb.zzcsz     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0028
            r2.connect()     // Catch:{ all -> 0x0048 }
            goto L_0x0046
        L_0x0028:
            com.google.android.gms.internal.ads.zzaaq<java.lang.Boolean> r3 = com.google.android.gms.internal.ads.zzabb.zzcsy     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzaax r1 = com.google.android.gms.internal.ads.zzwm.zzpx()     // Catch:{ all -> 0x0048 }
            java.lang.Object r3 = r1.zzd(r3)     // Catch:{ all -> 0x0048 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x0048 }
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0046
            com.google.android.gms.internal.ads.zzsy r3 = new com.google.android.gms.internal.ads.zzsy     // Catch:{ all -> 0x0048 }
            r3.<init>(r2)     // Catch:{ all -> 0x0048 }
            com.google.android.gms.internal.ads.zzrg r1 = com.google.android.gms.ads.internal.zzp.zzku()     // Catch:{ all -> 0x0048 }
            r1.zza(r3)     // Catch:{ all -> 0x0048 }
        L_0x0046:
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            return
        L_0x0048:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0048 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzsw.initialize(android.content.Context):void");
    }

    public final void zzmv() {
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcta)).booleanValue()) {
            synchronized (this.lock) {
                connect();
                zzp.zzkr();
                zzayu.zzeba.removeCallbacks(this.zzbva);
                zzp.zzkr();
                zzayu.zzeba.postDelayed(this.zzbva, ((Long) zzwm.zzpx().zzd(zzabb.zzctb)).longValue());
            }
        }
    }

    public final zzsz zza(zzte zzte) {
        synchronized (this.lock) {
            if (this.zzbvc == null) {
                zzsz zzsz = new zzsz();
                return zzsz;
            }
            try {
                zzsz zza = this.zzbvc.zza(zzte);
                return zza;
            } catch (RemoteException e) {
                zzayp.zzc("Unable to call into cache service.", e);
                return new zzsz();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            android.content.Context r1 = r3.zzvr     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.zztb r1 = r3.zzbvb     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x000c
            goto L_0x0021
        L_0x000c:
            com.google.android.gms.internal.ads.zzsx r1 = new com.google.android.gms.internal.ads.zzsx     // Catch:{ all -> 0x0023 }
            r1.<init>(r3)     // Catch:{ all -> 0x0023 }
            com.google.android.gms.internal.ads.zzta r2 = new com.google.android.gms.internal.ads.zzta     // Catch:{ all -> 0x0023 }
            r2.<init>(r3)     // Catch:{ all -> 0x0023 }
            com.google.android.gms.internal.ads.zztb r1 = r3.zza((com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks) r1, (com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener) r2)     // Catch:{ all -> 0x0023 }
            r3.zzbvb = r1     // Catch:{ all -> 0x0023 }
            r1.checkAvailabilityAndConnect()     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            return
        L_0x0021:
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            return
        L_0x0023:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzsw.connect():void");
    }

    private final synchronized zztb zza(BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        return new zztb(this.zzvr, zzp.zzlf().zzyj(), baseConnectionCallbacks, baseOnConnectionFailedListener);
    }

    /* access modifiers changed from: private */
    public final void disconnect() {
        synchronized (this.lock) {
            if (this.zzbvb != null) {
                if (this.zzbvb.isConnected() || this.zzbvb.isConnecting()) {
                    this.zzbvb.disconnect();
                }
                this.zzbvb = null;
                this.zzbvc = null;
                Binder.flushPendingCommands();
            }
        }
    }
}

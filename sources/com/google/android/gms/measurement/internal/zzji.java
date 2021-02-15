package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzji implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzio zza;
    /* access modifiers changed from: private */
    public volatile boolean zzb;
    private volatile zzeo zzc;

    protected zzji(zzio zzio) {
        this.zza = zzio;
    }

    public final void zza(Intent intent) {
        this.zza.zzc();
        Context zzm = this.zza.zzm();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzq().zzw().zza("Connection attempt already in progress");
                return;
            }
            this.zza.zzq().zzw().zza("Using local app measurement service");
            this.zzb = true;
            instance.bindService(zzm, intent, this.zza.zza, 129);
        }
    }

    public final void zza() {
        if (this.zzc != null && (this.zzc.isConnected() || this.zzc.isConnecting())) {
            this.zzc.disconnect();
        }
        this.zzc = null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r3.zza.zzq().zze().zza("Service connect failed to get IMeasurementService");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0062 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r4, android.os.IBinder r5) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onServiceConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r4)
            monitor-enter(r3)
            r4 = 0
            if (r5 != 0) goto L_0x001f
            r3.zzb = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzio r4 = r3.zza     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzq()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzet r4 = r4.zze()     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "Service connected with null binder"
            r4.zza(r5)     // Catch:{ all -> 0x001c }
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x001c:
            r4 = move-exception
            goto L_0x0099
        L_0x001f:
            r0 = 0
            java.lang.String r1 = r5.getInterfaceDescriptor()     // Catch:{ RemoteException -> 0x0062 }
            java.lang.String r2 = "com.google.android.gms.measurement.internal.IMeasurementService"
            boolean r2 = r2.equals(r1)     // Catch:{ RemoteException -> 0x0062 }
            if (r2 == 0) goto L_0x0052
            if (r5 != 0) goto L_0x002f
            goto L_0x0042
        L_0x002f:
            java.lang.String r1 = "com.google.android.gms.measurement.internal.IMeasurementService"
            android.os.IInterface r1 = r5.queryLocalInterface(r1)     // Catch:{ RemoteException -> 0x0062 }
            boolean r2 = r1 instanceof com.google.android.gms.measurement.internal.zzej     // Catch:{ RemoteException -> 0x0062 }
            if (r2 == 0) goto L_0x003c
            com.google.android.gms.measurement.internal.zzej r1 = (com.google.android.gms.measurement.internal.zzej) r1     // Catch:{ RemoteException -> 0x0062 }
            goto L_0x0041
        L_0x003c:
            com.google.android.gms.measurement.internal.zzel r1 = new com.google.android.gms.measurement.internal.zzel     // Catch:{ RemoteException -> 0x0062 }
            r1.<init>(r5)     // Catch:{ RemoteException -> 0x0062 }
        L_0x0041:
            r0 = r1
        L_0x0042:
            com.google.android.gms.measurement.internal.zzio r5 = r3.zza     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzw()     // Catch:{ RemoteException -> 0x0062 }
            java.lang.String r1 = "Bound to IMeasurementService interface"
            r5.zza(r1)     // Catch:{ RemoteException -> 0x0062 }
            goto L_0x0071
        L_0x0052:
            com.google.android.gms.measurement.internal.zzio r5 = r3.zza     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()     // Catch:{ RemoteException -> 0x0062 }
            com.google.android.gms.measurement.internal.zzet r5 = r5.zze()     // Catch:{ RemoteException -> 0x0062 }
            java.lang.String r2 = "Got binder with a wrong descriptor"
            r5.zza(r2, r1)     // Catch:{ RemoteException -> 0x0062 }
            goto L_0x0071
        L_0x0062:
            com.google.android.gms.measurement.internal.zzio r5 = r3.zza     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzet r5 = r5.zze()     // Catch:{ all -> 0x001c }
            java.lang.String r1 = "Service connect failed to get IMeasurementService"
            r5.zza(r1)     // Catch:{ all -> 0x001c }
        L_0x0071:
            if (r0 != 0) goto L_0x0089
            r3.zzb = r4     // Catch:{ all -> 0x001c }
            com.google.android.gms.common.stats.ConnectionTracker r4 = com.google.android.gms.common.stats.ConnectionTracker.getInstance()     // Catch:{ IllegalArgumentException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzio r5 = r3.zza     // Catch:{ IllegalArgumentException -> 0x0097 }
            android.content.Context r5 = r5.zzm()     // Catch:{ IllegalArgumentException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzio r0 = r3.zza     // Catch:{ IllegalArgumentException -> 0x0097 }
            com.google.android.gms.measurement.internal.zzji r0 = r0.zza     // Catch:{ IllegalArgumentException -> 0x0097 }
            r4.unbindService(r5, r0)     // Catch:{ IllegalArgumentException -> 0x0097 }
            goto L_0x0097
        L_0x0089:
            com.google.android.gms.measurement.internal.zzio r4 = r3.zza     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzfo r4 = r4.zzp()     // Catch:{ all -> 0x001c }
            com.google.android.gms.measurement.internal.zzjl r5 = new com.google.android.gms.measurement.internal.zzjl     // Catch:{ all -> 0x001c }
            r5.<init>(r3, r0)     // Catch:{ all -> 0x001c }
            r4.zza((java.lang.Runnable) r5)     // Catch:{ all -> 0x001c }
        L_0x0097:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            return
        L_0x0099:
            monitor-exit(r3)     // Catch:{ all -> 0x001c }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzji.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zza.zzq().zzv().zza("Service disconnected");
        this.zza.zzp().zza((Runnable) new zzjk(this, componentName));
    }

    public final void zzb() {
        this.zza.zzc();
        Context zzm = this.zza.zzm();
        synchronized (this) {
            if (this.zzb) {
                this.zza.zzq().zzw().zza("Connection attempt already in progress");
            } else if (this.zzc == null || (!this.zzc.isConnecting() && !this.zzc.isConnected())) {
                this.zzc = new zzeo(zzm, Looper.getMainLooper(), this, this);
                this.zza.zzq().zzw().zza("Connecting to remote service");
                this.zzb = true;
                this.zzc.checkAvailabilityAndConnect();
            } else {
                this.zza.zzq().zzw().zza("Already awaiting connection attempt");
            }
        }
    }

    public final void onConnected(Bundle bundle) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
        synchronized (this) {
            try {
                this.zza.zzp().zza((Runnable) new zzjn(this, (zzej) this.zzc.getService()));
            } catch (DeadObjectException | IllegalStateException unused) {
                this.zzc = null;
                this.zzb = false;
            }
        }
    }

    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zza.zzq().zzv().zza("Service connection suspended");
        this.zza.zzp().zza((Runnable) new zzjm(this));
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzer zzc2 = this.zza.zzy.zzc();
        if (zzc2 != null) {
            zzc2.zzh().zza("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzb = false;
            this.zzc = null;
        }
        this.zza.zzp().zza((Runnable) new zzjp(this));
    }
}

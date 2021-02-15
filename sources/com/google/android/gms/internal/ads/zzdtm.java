package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.internal.ads.zzcf;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdtm implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final String packageName;
    private final HandlerThread zzedb;
    private zzduk zzhmj;
    private final String zzhmk;
    private final LinkedBlockingQueue<zzcf.zza> zzhml = new LinkedBlockingQueue<>();

    public zzdtm(Context context, String str, String str2) {
        this.packageName = str;
        this.zzhmk = str2;
        HandlerThread handlerThread = new HandlerThread("GassClient");
        this.zzedb = handlerThread;
        handlerThread.start();
        this.zzhmj = new zzduk(context, this.zzedb.getLooper(), this, this, 9200000);
        this.zzhmj.checkAvailabilityAndConnect();
    }

    public final zzcf.zza zzee(int i) {
        zzcf.zza zza;
        try {
            zza = this.zzhml.poll(CoroutineLiveDataKt.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            zza = null;
        }
        return zza == null ? zzavy() : zza;
    }

    private final zzdun zzavx() {
        try {
            return this.zzhmj.zzawm();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            this.zzhml.put(zzavy());
        } catch (InterruptedException unused) {
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|11|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        zzapz();
        r3.zzedb.quit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r3.zzhml.put(zzavy());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        zzapz();
        r3.zzedb.quit();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzdun r4 = r3.zzavx()
            if (r4 == 0) goto L_0x0041
            com.google.android.gms.internal.ads.zzduj r0 = new com.google.android.gms.internal.ads.zzduj     // Catch:{ all -> 0x0025 }
            java.lang.String r1 = r3.packageName     // Catch:{ all -> 0x0025 }
            java.lang.String r2 = r3.zzhmk     // Catch:{ all -> 0x0025 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.ads.zzdul r4 = r4.zza((com.google.android.gms.internal.ads.zzduj) r0)     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.ads.zzcf$zza r4 = r4.zzawn()     // Catch:{ all -> 0x0025 }
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.zzcf$zza> r0 = r3.zzhml     // Catch:{ all -> 0x0025 }
            r0.put(r4)     // Catch:{ all -> 0x0025 }
            r3.zzapz()
            android.os.HandlerThread r4 = r3.zzedb
            r4.quit()
            return
        L_0x0025:
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.ads.zzcf$zza> r4 = r3.zzhml     // Catch:{ InterruptedException -> 0x0039, all -> 0x002f }
            com.google.android.gms.internal.ads.zzcf$zza r0 = zzavy()     // Catch:{ InterruptedException -> 0x0039, all -> 0x002f }
            r4.put(r0)     // Catch:{ InterruptedException -> 0x0039, all -> 0x002f }
            goto L_0x0039
        L_0x002f:
            r4 = move-exception
            r3.zzapz()
            android.os.HandlerThread r0 = r3.zzedb
            r0.quit()
            throw r4
        L_0x0039:
            r3.zzapz()
            android.os.HandlerThread r4 = r3.zzedb
            r4.quit()
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdtm.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.zzhml.put(zzavy());
        } catch (InterruptedException unused) {
        }
    }

    private final void zzapz() {
        zzduk zzduk = this.zzhmj;
        if (zzduk == null) {
            return;
        }
        if (zzduk.isConnected() || this.zzhmj.isConnecting()) {
            this.zzhmj.disconnect();
        }
    }

    private static zzcf.zza zzavy() {
        return (zzcf.zza) ((zzejz) zzcf.zza.zzaq().zzn(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID).zzbgt());
    }
}

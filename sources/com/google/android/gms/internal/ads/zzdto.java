package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.internal.ads.zzbw;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
final class zzdto implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final long startTime;
    private final HandlerThread zzedb;
    private zzduk zzhmj;
    private final LinkedBlockingQueue<zzduv> zzhml;
    private final String zzhmn;
    private final String zzhmo;
    private final int zzhmp = 1;
    private final zzdtc zzvw;
    private final zzgo zzvy;

    public zzdto(Context context, int i, zzgo zzgo, String str, String str2, String str3, zzdtc zzdtc) {
        this.zzhmn = str;
        this.zzvy = zzgo;
        this.zzhmo = str2;
        this.zzvw = zzdtc;
        HandlerThread handlerThread = new HandlerThread("GassDGClient");
        this.zzedb = handlerThread;
        handlerThread.start();
        this.startTime = System.currentTimeMillis();
        this.zzhmj = new zzduk(context, this.zzedb.getLooper(), this, this, 19621000);
        this.zzhml = new LinkedBlockingQueue<>();
        this.zzhmj.checkAvailabilityAndConnect();
    }

    public final zzduv zzef(int i) {
        zzduv zzduv;
        try {
            zzduv = this.zzhml.poll(50000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zzb(Constants.TAG_FEED_LIST, this.startTime, e);
            zzduv = null;
        }
        zzb(com.medscape.android.Constants.CONSULT_FEEDTYPE_ALL, this.startTime, (Exception) null);
        if (zzduv != null) {
            if (zzduv.status == 7) {
                zzdtc.zzb(zzbw.zza.zzc.DISABLED);
            } else {
                zzdtc.zzb(zzbw.zza.zzc.ENABLED);
            }
        }
        return zzduv == null ? zzavz() : zzduv;
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
            zzb(com.medscape.android.Constants.REQUEST_CODE_PICTURE_EDIT_THIRD, this.startTime, (Exception) null);
            this.zzhml.put(zzavz());
        } catch (InterruptedException unused) {
        }
    }

    public final void onConnected(Bundle bundle) {
        zzdun zzavx = zzavx();
        if (zzavx != null) {
            try {
                zzduv zza = zzavx.zza(new zzdut(this.zzhmp, this.zzvy, this.zzhmn, this.zzhmo));
                zzb(5011, this.startTime, (Exception) null);
                this.zzhml.put(zza);
            } catch (Throwable th) {
                zzb(Constants.TAG_USER_SUBSCRIPTION, this.startTime, new Exception(th));
            } finally {
                zzapz();
                this.zzedb.quit();
            }
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            zzb(com.medscape.android.Constants.REQUEST_CODE_PICTURE_CROP, this.startTime, (Exception) null);
            this.zzhml.put(zzavz());
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

    private static zzduv zzavz() {
        return new zzduv((byte[]) null, 1);
    }

    private final void zzb(int i, long j, Exception exc) {
        zzdtc zzdtc = this.zzvw;
        if (zzdtc != null) {
            zzdtc.zza(i, System.currentTimeMillis() - j, exc);
        }
    }
}

package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.ConnectionResult;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcpt extends zzcpv {
    public zzcpt(Context context) {
        this.zzgkh = new zzask(context, zzp.zzlf().zzyj(), this, this);
    }

    public final zzdzc<InputStream> zzi(zzatc zzatc) {
        synchronized (this.mLock) {
            if (this.zzgke) {
                zzbcg zzbcg = this.zzdja;
                return zzbcg;
            }
            this.zzgke = true;
            this.zzgkg = zzatc;
            this.zzgkh.checkAvailabilityAndConnect();
            this.zzdja.addListener(new zzcpw(this), zzbbz.zzeeu);
            zzbcg zzbcg2 = this.zzdja;
            return zzbcg2;
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzayp.zzef("Cannot connect to remote service, fallback to local instance.");
        this.zzdja.setException(new zzcqm(zzdpg.INTERNAL_ERROR));
    }

    public final void onConnected(Bundle bundle) {
        synchronized (this.mLock) {
            if (!this.zzgkf) {
                this.zzgkf = true;
                try {
                    this.zzgkh.zzve().zza(this.zzgkg, (zzasw) new zzcpy(this));
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zzdja.setException(new zzcqm(zzdpg.INTERNAL_ERROR));
                } catch (Throwable th) {
                    zzp.zzkv().zza(th, "RemoteAdRequestClientTask.onConnected");
                    this.zzdja.setException(new zzcqm(zzdpg.INTERNAL_ERROR));
                }
            }
        }
    }
}

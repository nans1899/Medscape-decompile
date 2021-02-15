package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.ConnectionResult;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcqc extends zzcpv {
    private String zzgkm;
    private int zzgkn = zzcqd.zzgko;

    public zzcqc(Context context) {
        this.zzgkh = new zzask(context, zzp.zzlf().zzyj(), this, this);
    }

    public final zzdzc<InputStream> zzk(zzatc zzatc) {
        synchronized (this.mLock) {
            if (this.zzgkn != zzcqd.zzgko && this.zzgkn != zzcqd.zzgkp) {
                zzdzc<InputStream> immediateFailedFuture = zzdyq.immediateFailedFuture(new zzcqm(zzdpg.INVALID_REQUEST));
                return immediateFailedFuture;
            } else if (this.zzgke) {
                zzbcg zzbcg = this.zzdja;
                return zzbcg;
            } else {
                this.zzgkn = zzcqd.zzgkp;
                this.zzgke = true;
                this.zzgkg = zzatc;
                this.zzgkh.checkAvailabilityAndConnect();
                this.zzdja.addListener(new zzcqb(this), zzbbz.zzeeu);
                zzbcg zzbcg2 = this.zzdja;
                return zzbcg2;
            }
        }
    }

    public final zzdzc<InputStream> zzgm(String str) {
        synchronized (this.mLock) {
            if (this.zzgkn != zzcqd.zzgko && this.zzgkn != zzcqd.zzgkq) {
                zzdzc<InputStream> immediateFailedFuture = zzdyq.immediateFailedFuture(new zzcqm(zzdpg.INVALID_REQUEST));
                return immediateFailedFuture;
            } else if (this.zzgke) {
                zzbcg zzbcg = this.zzdja;
                return zzbcg;
            } else {
                this.zzgkn = zzcqd.zzgkq;
                this.zzgke = true;
                this.zzgkm = str;
                this.zzgkh.checkAvailabilityAndConnect();
                this.zzdja.addListener(new zzcqe(this), zzbbz.zzeeu);
                zzbcg zzbcg2 = this.zzdja;
                return zzbcg2;
            }
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
                    if (this.zzgkn == zzcqd.zzgkp) {
                        this.zzgkh.zzve().zzc(this.zzgkg, new zzcpy(this));
                    } else if (this.zzgkn == zzcqd.zzgkq) {
                        this.zzgkh.zzve().zza(this.zzgkm, (zzasw) new zzcpy(this));
                    } else {
                        this.zzdja.setException(new zzcqm(zzdpg.INTERNAL_ERROR));
                    }
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zzdja.setException(new zzcqm(zzdpg.INTERNAL_ERROR));
                } catch (Throwable th) {
                    zzp.zzkv().zza(th, "RemoteUrlAndCacheKeyClientTask.onConnected");
                    this.zzdja.setException(new zzcqm(zzdpg.INTERNAL_ERROR));
                }
            }
        }
    }
}

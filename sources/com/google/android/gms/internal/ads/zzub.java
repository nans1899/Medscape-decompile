package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzub {
    private final byte[] zzbya;
    private int zzbyb;
    private int zzbyc;
    private final /* synthetic */ zztx zzbyd;

    private zzub(zztx zztx, byte[] bArr) {
        this.zzbyd = zztx;
        this.zzbya = bArr;
    }

    public final synchronized void zzdx() {
        try {
            if (this.zzbyd.zzbxz) {
                this.zzbyd.zzbxy.zzc(this.zzbya);
                this.zzbyd.zzbxy.zzs(this.zzbyb);
                this.zzbyd.zzbxy.zzt(this.zzbyc);
                this.zzbyd.zzbxy.zza((int[]) null);
                this.zzbyd.zzbxy.zzdx();
            }
        } catch (RemoteException e) {
            zzbbq.zzb("Clearcut log failed", e);
        }
    }

    public final zzub zzbx(int i) {
        this.zzbyb = i;
        return this;
    }

    public final zzub zzby(int i) {
        this.zzbyc = i;
        return this;
    }
}

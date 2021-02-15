package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zztl implements Runnable {
    private final zztm zzbvn;
    private final zztb zzbvo;
    private final zzte zzbvp;
    private final zzbcg zzbvq;

    zztl(zztm zztm, zztb zztb, zzte zzte, zzbcg zzbcg) {
        this.zzbvn = zztm;
        this.zzbvo = zztb;
        this.zzbvp = zzte;
        this.zzbvq = zzbcg;
    }

    public final void run() {
        zztm zztm = this.zzbvn;
        zztb zztb = this.zzbvo;
        zzte zzte = this.zzbvp;
        zzbcg zzbcg = this.zzbvq;
        try {
            zzsz zza = zztb.zzmz().zza(zzte);
            if (!zza.zzmw()) {
                zzbcg.setException(new RuntimeException("No entry contents."));
                zztm.zzbvl.disconnect();
                return;
            }
            zztn zztn = new zztn(zztm, zza.zzmx(), 1);
            int read = zztn.read();
            if (read != -1) {
                zztn.unread(read);
                zzbcg.set(zztn);
                return;
            }
            throw new IOException("Unable to read from cache.");
        } catch (RemoteException | IOException e) {
            zzayp.zzc("Unable to obtain a cache service instance.", e);
            zzbcg.setException(e);
            zztm.zzbvl.disconnect();
        }
    }
}

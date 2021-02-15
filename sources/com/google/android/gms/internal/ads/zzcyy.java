package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcyy {
    private final zzcku zzgij;
    private final ConcurrentHashMap<String, zzaph> zzgsc = new ConcurrentHashMap<>();

    public zzcyy(zzcku zzcku) {
        this.zzgij = zzcku;
    }

    public final void zzgo(String str) {
        try {
            this.zzgsc.put(str, this.zzgij.zzdk(str));
        } catch (RemoteException e) {
            zzayp.zzc("Couldn't create RTB adapter : ", e);
        }
    }

    @CheckForNull
    public final zzaph zzgp(String str) {
        if (this.zzgsc.containsKey(str)) {
            return this.zzgsc.get(str);
        }
        return null;
    }
}

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.MuteThisAdReason;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzye implements MuteThisAdReason {
    private final String description;
    private zzyd zzcjk;

    public zzye(zzyd zzyd) {
        String str;
        this.zzcjk = zzyd;
        try {
            str = zzyd.getDescription();
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
            str = null;
        }
        this.description = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final zzyd zzql() {
        return this.zzcjk;
    }

    public final String toString() {
        return this.description;
    }
}

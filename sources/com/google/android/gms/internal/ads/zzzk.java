package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzzk extends zzyc {
    private final String description;
    private final String zzcks;

    public zzzk(String str, String str2) {
        this.description = str;
        this.zzcks = str2;
    }

    public final String getDescription() throws RemoteException {
        return this.description;
    }

    public final String zzqk() throws RemoteException {
        return this.zzcks;
    }
}

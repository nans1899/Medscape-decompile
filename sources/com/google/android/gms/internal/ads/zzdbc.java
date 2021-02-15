package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.RemoteException;
import java.util.ArrayList;
import javax.annotation.Nonnull;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzdbc implements zzdyr<ArrayList<Uri>> {
    private final /* synthetic */ zzart zzguo;

    zzdbc(zzdaq zzdaq, zzart zzart) {
        this.zzguo = zzart;
    }

    public final void zzb(Throwable th) {
        try {
            zzart zzart = this.zzguo;
            String valueOf = String.valueOf(th.getMessage());
            zzart.onError(valueOf.length() != 0 ? "Internal error: ".concat(valueOf) : new String("Internal error: "));
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }

    public final /* synthetic */ void onSuccess(@Nonnull Object obj) {
        try {
            this.zzguo.onSuccess((ArrayList) obj);
        } catch (RemoteException e) {
            zzbbq.zzc("", e);
        }
    }
}

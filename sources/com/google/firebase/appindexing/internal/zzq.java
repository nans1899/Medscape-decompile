package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.icing.zzaa;
import com.google.android.gms.internal.icing.zzaj;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzq extends zzs {
    private final /* synthetic */ zza[] zzfm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzq(zzn zzn, zza[] zzaArr) {
        super((zzq) null);
        this.zzfm = zzaArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaa zzaa) throws RemoteException {
        zzaa.zza(new zzaj.zzc(this), this.zzfm);
    }
}

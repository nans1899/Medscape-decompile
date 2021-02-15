package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.measurement.internal.zzgw;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.6.0 */
final class zzbl extends zzag.zzb {
    private final /* synthetic */ zzgw zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbl(zzag zzag, zzgw zzgw) {
        super(zzag);
        this.zzd = zzag;
        this.zzc = zzgw;
    }

    /* access modifiers changed from: package-private */
    public final void zza() throws RemoteException {
        for (int i = 0; i < this.zzd.zzf.size(); i++) {
            if (this.zzc.equals(((Pair) this.zzd.zzf.get(i)).first)) {
                Log.w(this.zzd.zzc, "OnEventListener already registered.");
                return;
            }
        }
        zzag.zzd zzd2 = new zzag.zzd(this.zzc);
        this.zzd.zzf.add(new Pair(this.zzc, zzd2));
        this.zzd.zzm.registerOnMeasurementEventListener(zzd2);
    }
}

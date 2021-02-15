package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.instream.InstreamAd;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzajp {
    private final zzwz zzacr;
    private final Context zzvr;

    public zzajp(Context context, String str) {
        this((Context) Preconditions.checkNotNull(context, "context cannot be null"), zzwm.zzpu().zzb(context, str, new zzanc()));
    }

    private zzajp(Context context, zzwz zzwz) {
        this.zzvr = context;
        this.zzacr = zzwz;
    }

    public final zzajp zza(InstreamAd.InstreamAdLoadCallback instreamAdLoadCallback) {
        try {
            this.zzacr.zza((zzajk) new zzajn(instreamAdLoadCallback));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        return this;
    }

    public final zzajp zza(zzajo zzajo) {
        try {
            this.zzacr.zza(new zzajc(zzajo));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
        return this;
    }

    public final zzajq zztg() {
        try {
            return new zzajq(this.zzvr, this.zzacr.zzqf());
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
            return null;
        }
    }
}

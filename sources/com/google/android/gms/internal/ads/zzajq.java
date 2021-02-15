package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzajq {
    private final zzvl zzacs;
    private final zzwu zzact;
    private final Context zzvr;

    zzajq(Context context, zzwu zzwu) {
        this(context, zzwu, zzvl.zzchs);
    }

    private zzajq(Context context, zzwu zzwu, zzvl zzvl) {
        this.zzvr = context;
        this.zzact = zzwu;
        this.zzacs = zzvl;
    }

    private final void zza(zzyw zzyw) {
        try {
            this.zzact.zzb(zzvl.zza(this.zzvr, zzyw));
        } catch (RemoteException e) {
            zzbbq.zze("#007 Could not call remote method.", e);
        }
    }

    public final void loadAd(AdRequest adRequest) {
        zza(adRequest.zzdr());
    }

    public final void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zzdr());
    }
}

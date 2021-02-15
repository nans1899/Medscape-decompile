package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class zzbqo {
    protected final zzdnv zzeot;
    protected final zzdog zzfmw;
    private final zzbvb zzfrq;
    private final zzbvr zzfrr;
    private final zzdls zzfrs;
    private final zzbty zzfrt;
    private final zzbwv zzfru;

    protected zzbqo(zzbqn zzbqn) {
        this.zzfmw = zzbqn.zzfmw;
        this.zzeot = zzbqn.zzeot;
        this.zzfrq = zzbqn.zzfrq;
        this.zzfrr = zzbqn.zzfrr;
        this.zzfrs = zzbqn.zzfrs;
        this.zzfrt = zzbqn.zzfrt;
        this.zzfru = zzbqn.zzfru;
    }

    public final zzbvb zzaiw() {
        return this.zzfrq;
    }

    public void zzaih() {
        this.zzfrr.onAdLoaded();
    }

    public final zzbty zzaix() {
        return this.zzfrt;
    }

    public void destroy() {
        this.zzfrq.zzcc((Context) null);
    }

    public final zzdls zzaiy() {
        return this.zzfrs;
    }

    public final zzbxq zzaiz() {
        return this.zzfru.zzaiz();
    }
}

package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzckg implements zzbhu {
    private final zzbcg zzbvu;

    zzckg(zzbcg zzbcg) {
        this.zzbvu = zzbcg;
    }

    public final void zzai(boolean z) {
        zzbcg zzbcg = this.zzbvu;
        if (z) {
            zzbcg.set(null);
        } else {
            zzbcg.setException(new Exception("Ad Web View failed to load."));
        }
    }
}

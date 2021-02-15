package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.zzua;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcco implements zzp, zzbvs {
    private final zzbbx zzbpe;
    private final zzbgj zzdgy;
    private final zzdnv zzeot;
    private IObjectWrapper zzfrd;
    private final zzua.zza.C0021zza zzfxc;
    private final Context zzvr;

    public zzcco(Context context, zzbgj zzbgj, zzdnv zzdnv, zzbbx zzbbx, zzua.zza.C0021zza zza) {
        this.zzvr = context;
        this.zzdgy = zzbgj;
        this.zzeot = zzdnv;
        this.zzbpe = zzbbx;
        this.zzfxc = zza;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void onAdLoaded() {
        if ((this.zzfxc == zzua.zza.C0021zza.REWARD_BASED_VIDEO_AD || this.zzfxc == zzua.zza.C0021zza.INTERSTITIAL || this.zzfxc == zzua.zza.C0021zza.APP_OPEN) && this.zzeot.zzdtm && this.zzdgy != null && com.google.android.gms.ads.internal.zzp.zzlg().zzp(this.zzvr)) {
            int i = this.zzbpe.zzeel;
            int i2 = this.zzbpe.zzeem;
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(".");
            sb.append(i2);
            IObjectWrapper zza = com.google.android.gms.ads.internal.zzp.zzlg().zza(sb.toString(), this.zzdgy.getWebView(), "", "javascript", this.zzeot.zzhec.getVideoEventsOwner());
            this.zzfrd = zza;
            if (zza != null && this.zzdgy.getView() != null) {
                com.google.android.gms.ads.internal.zzp.zzlg().zza(this.zzfrd, this.zzdgy.getView());
                this.zzdgy.zzap(this.zzfrd);
                com.google.android.gms.ads.internal.zzp.zzlg().zzab(this.zzfrd);
            }
        }
    }

    public final void zzum() {
        this.zzfrd = null;
    }

    public final void zzun() {
        zzbgj zzbgj;
        if (this.zzfrd != null && (zzbgj = this.zzdgy) != null) {
            zzbgj.zza("onSdkImpression", (Map<String, ?>) new HashMap());
        }
    }
}

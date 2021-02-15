package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.internal.ads.zzua;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzccs implements zzbuh, zzbzu {
    private final View view;
    private final zzawo zzbqq;
    private final zzawp zzfrn;
    private final zzua.zza.C0021zza zzfxc;
    private String zzfxe;
    private final Context zzvr;

    public zzccs(zzawp zzawp, Context context, zzawo zzawo, View view2, zzua.zza.C0021zza zza) {
        this.zzfrn = zzawp;
        this.zzvr = context;
        this.zzbqq = zzawo;
        this.view = view2;
        this.zzfxc = zza;
    }

    public final void onAdLeftApplication() {
    }

    public final void onRewardedVideoCompleted() {
    }

    public final void onRewardedVideoStarted() {
    }

    public final void zzajj() {
    }

    public final void onAdOpened() {
        View view2 = this.view;
        if (!(view2 == null || this.zzfxe == null)) {
            this.zzbqq.zzh(view2.getContext(), this.zzfxe);
        }
        this.zzfrn.zzam(true);
    }

    public final void onAdClosed() {
        this.zzfrn.zzam(false);
    }

    public final void zzajl() {
        String zzae = this.zzbqq.zzae(this.zzvr);
        this.zzfxe = zzae;
        String valueOf = String.valueOf(zzae);
        String str = this.zzfxc == zzua.zza.C0021zza.REWARD_BASED_VIDEO_AD ? "/Rewarded" : "/Interstitial";
        this.zzfxe = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
    }

    @ParametersAreNonnullByDefault
    public final void zzb(zzatw zzatw, String str, String str2) {
        if (this.zzbqq.zzac(this.zzvr)) {
            try {
                this.zzbqq.zza(this.zzvr, this.zzbqq.zzah(this.zzvr), this.zzfrn.getAdUnitId(), zzatw.getType(), zzatw.getAmount());
            } catch (RemoteException e) {
                zzayp.zzd("Remote Exception to get reward item.", e);
            }
        }
    }
}

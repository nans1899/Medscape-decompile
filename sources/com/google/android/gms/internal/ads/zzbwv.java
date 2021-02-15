package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.reward.AdMetadataListener;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbwv extends AdMetadataListener implements AppEventListener, zzbuh, zzbuv, zzbuz, zzbwb, zzbwl, zzux {
    private final zzbxq zzfvf = new zzbxq(this);
    /* access modifiers changed from: private */
    @Nullable
    public zzczs zzfvg;
    /* access modifiers changed from: private */
    @Nullable
    public zzczp zzfvh;
    /* access modifiers changed from: private */
    @Nullable
    public zzczr zzfvi;
    /* access modifiers changed from: private */
    @Nullable
    public zzczn zzfvj;
    /* access modifiers changed from: private */
    @Nullable
    public zzdkc zzfvk;
    /* access modifiers changed from: private */
    @Nullable
    public zzdmc zzfvl;

    public final zzbxq zzaiz() {
        return this.zzfvf;
    }

    public final void onAdOpened() {
        zza(this.zzfvg, zzbwu.zzfve);
        zza(this.zzfvl, zzbwx.zzfve);
    }

    public final void onAdClosed() {
        zza(this.zzfvg, zzbxg.zzfve);
        zza(this.zzfvl, zzbxi.zzfve);
    }

    public final void onAdLeftApplication() {
        zza(this.zzfvg, zzbxl.zzfve);
        zza(this.zzfvl, zzbxk.zzfve);
    }

    public final void onRewardedVideoStarted() {
        zza(this.zzfvg, zzbxn.zzfve);
        zza(this.zzfvl, zzbxm.zzfve);
    }

    public final void zzb(zzatw zzatw, String str, String str2) {
        zza(this.zzfvg, new zzbxp(zzatw, str, str2));
        zza(this.zzfvl, new zzbxo(zzatw, str, str2));
    }

    public final void onRewardedVideoCompleted() {
        zza(this.zzfvg, zzbww.zzfve);
        zza(this.zzfvl, zzbwz.zzfve);
    }

    public final void onAdClicked() {
        zza(this.zzfvg, zzbwy.zzfve);
        zza(this.zzfvh, zzbxb.zzfve);
    }

    public final void onAppEvent(String str, String str2) {
        zza(this.zzfvi, new zzbxa(str, str2));
    }

    public final void zzb(zzvp zzvp) {
        zza(this.zzfvj, new zzbxd(zzvp));
        zza(this.zzfvl, new zzbxc(zzvp));
    }

    public final void onAdImpression() {
        zza(this.zzfvg, zzbxf.zzfve);
    }

    public final void zzajx() {
        zza(this.zzfvk, zzbxe.zzfve);
    }

    public final void onAdMetadataChanged() {
        zza(this.zzfvl, zzbxh.zzfve);
    }

    public final void zzj(zzva zzva) {
        zza(this.zzfvl, new zzbxj(zzva));
    }

    private static <T> void zza(T t, zzbxt<T> zzbxt) {
        if (t != null) {
            zzbxt.zzq(t);
        }
    }
}

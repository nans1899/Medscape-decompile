package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzagk {
    /* access modifiers changed from: private */
    public final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener zzded;
    /* access modifiers changed from: private */
    public final NativeCustomTemplateAd.OnCustomClickListener zzdee;
    private NativeCustomTemplateAd zzdef;

    public zzagk(NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
        this.zzded = onCustomTemplateAdLoadedListener;
        this.zzdee = onCustomClickListener;
    }

    public final zzafl zzsx() {
        return new zzagl(this);
    }

    public final zzafk zzsy() {
        if (this.zzdee == null) {
            return null;
        }
        return new zzagm(this);
    }

    /* access modifiers changed from: private */
    public final synchronized NativeCustomTemplateAd zzb(zzafa zzafa) {
        if (this.zzdef != null) {
            return this.zzdef;
        }
        zzafb zzafb = new zzafb(zzafa);
        this.zzdef = zzafb;
        return zzafb;
    }
}

package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.gms.internal.ads.zzbgj;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzk {
    public final int index;
    public final ViewGroup parent;
    public final ViewGroup.LayoutParams zzdpi;
    public final Context zzvr;

    public zzk(zzbgj zzbgj) throws zzi {
        this.zzdpi = zzbgj.getLayoutParams();
        ViewParent parent2 = zzbgj.getParent();
        this.zzvr = zzbgj.zzabr();
        if (parent2 == null || !(parent2 instanceof ViewGroup)) {
            throw new zzi("Could not get the parent of the WebView for an overlay.");
        }
        ViewGroup viewGroup = (ViewGroup) parent2;
        this.parent = viewGroup;
        this.index = viewGroup.indexOfChild(zzbgj.getView());
        this.parent.removeView(zzbgj.getView());
        zzbgj.zzba(true);
    }
}

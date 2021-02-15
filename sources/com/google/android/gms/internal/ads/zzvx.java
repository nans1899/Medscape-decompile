package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzvx {
    /* access modifiers changed from: private */
    public final zzve zzcik;
    /* access modifiers changed from: private */
    public final zzvf zzcil;
    /* access modifiers changed from: private */
    public final zzzl zzcim;
    /* access modifiers changed from: private */
    public final zzagi zzcin;
    /* access modifiers changed from: private */
    public final zzauk zzcio;
    private final zzavo zzcip;
    /* access modifiers changed from: private */
    public final zzaqz zzciq;
    /* access modifiers changed from: private */
    public final zzagh zzcir;

    public zzvx(zzve zzve, zzvf zzvf, zzzl zzzl, zzagi zzagi, zzauk zzauk, zzavo zzavo, zzaqz zzaqz, zzagh zzagh) {
        this.zzcik = zzve;
        this.zzcil = zzvf;
        this.zzcim = zzzl;
        this.zzcin = zzagi;
        this.zzcio = zzauk;
        this.zzcip = zzavo;
        this.zzciq = zzaqz;
        this.zzcir = zzagh;
    }

    /* access modifiers changed from: private */
    public static void zza(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("action", "no_ads_fallback");
        bundle.putString("flow", str);
        zzwm.zzpt().zza(context, zzwm.zzpz().zzbre, "gmob-apps", bundle, true);
    }

    public final zzxc zza(Context context, zzvn zzvn, String str, zzanb zzanb) {
        return (zzxc) new zzwf(this, context, zzvn, str, zzanb).zzd(context, false);
    }

    public final zzwz zzb(Context context, String str, zzanb zzanb) {
        return (zzwz) new zzwg(this, context, str, zzanb).zzd(context, false);
    }

    public final zzaei zza(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzaei) new zzwi(this, frameLayout, frameLayout2, context).zzd(context, false);
    }

    public final zzael zza(View view, HashMap<String, View> hashMap, HashMap<String, View> hashMap2) {
        return (zzael) new zzwl(this, view, hashMap, hashMap2).zzd(view.getContext(), false);
    }

    public final zzauy zzc(Context context, String str, zzanb zzanb) {
        return (zzauy) new zzvz(this, context, str, zzanb).zzd(context, false);
    }

    public final zzaqy zzb(Activity activity) {
        zzvy zzvy = new zzvy(this, activity);
        Intent intent = activity.getIntent();
        boolean z = false;
        if (!intent.hasExtra("com.google.android.gms.ads.internal.overlay.useClientJar")) {
            zzbbq.zzfc("useClientJar flag not found in activity intent extras.");
        } else {
            z = intent.getBooleanExtra("com.google.android.gms.ads.internal.overlay.useClientJar", false);
        }
        return (zzaqy) zzvy.zzd(activity, z);
    }

    public final zzaxl zza(Context context, zzanb zzanb) {
        return (zzaxl) new zzwb(this, context, zzanb).zzd(context, false);
    }

    public final zzaqp zzb(Context context, zzanb zzanb) {
        return (zzaqp) new zzwd(this, context, zzanb).zzd(context, false);
    }
}

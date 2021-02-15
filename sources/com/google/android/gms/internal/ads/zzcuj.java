package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.browser.customtabs.CustomTabsIntent;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzb;
import com.google.android.gms.ads.internal.overlay.zzv;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcuj implements zzcta<zzcbe> {
    private final zzdnt zzfmt;
    private final Executor zzfoc;
    private final zzcce zzgos;
    private final Context zzvr;

    public zzcuj(Context context, Executor executor, zzcce zzcce, zzdnt zzdnt) {
        this.zzvr = context;
        this.zzgos = zzcce;
        this.zzfoc = executor;
        this.zzfmt = zzdnt;
    }

    public final boolean zza(zzdog zzdog, zzdnv zzdnv) {
        return (this.zzvr instanceof Activity) && PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzacc.zzk(this.zzvr) && !TextUtils.isEmpty(zzf(zzdnv));
    }

    public final zzdzc<zzcbe> zzb(zzdog zzdog, zzdnv zzdnv) {
        String zzf = zzf(zzdnv);
        return zzdyq.zzb(zzdyq.zzaf(null), new zzcum(this, zzf != null ? Uri.parse(zzf) : null, zzdog, zzdnv), this.zzfoc);
    }

    private static String zzf(zzdnv zzdnv) {
        try {
            return zzdnv.zzhdu.getString("tab_url");
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zza(Uri uri, zzdog zzdog, zzdnv zzdnv, Object obj) throws Exception {
        try {
            CustomTabsIntent build = new CustomTabsIntent.Builder().build();
            build.intent.setData(uri);
            zzb zzb = new zzb(build.intent);
            zzbcg zzbcg = new zzbcg();
            zzcbg zza = this.zzgos.zza(new zzbre(zzdog, zzdnv, (String) null), new zzcbf(new zzcul(zzbcg)));
            zzbcg.set(new AdOverlayInfoParcel(zzb, (zzux) null, zza.zzagk(), (zzv) null, new zzbbx(0, 0, false)));
            this.zzfmt.zzwp();
            return zzdyq.zzaf(zza.zzagj());
        } catch (Throwable th) {
            zzayp.zzc("Error in CustomTabsAdRenderer", th);
            throw th;
        }
    }
}

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import io.branch.indexing.ContentDiscoveryManifest;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdha implements zzdgu<Bundle> {
    private final boolean zzdvb;
    private final boolean zzdvc;
    private final String zzdvd;
    private final boolean zzdve;
    private final boolean zzdvf;
    private final boolean zzdvg;
    private final String zzdvh;
    private final String zzdvi;
    private final String zzdvj;
    private final boolean zzdvk;
    private final ArrayList<String> zzgyi;
    private final String zzgyj;
    private final String zzgyk;
    private final long zzgyl;

    public zzdha(boolean z, boolean z2, String str, boolean z3, boolean z4, boolean z5, String str2, ArrayList<String> arrayList, @Nullable String str3, @Nullable String str4, String str5, boolean z6, String str6, long j) {
        this.zzdvb = z;
        this.zzdvc = z2;
        this.zzdvd = str;
        this.zzdve = z3;
        this.zzdvf = z4;
        this.zzdvg = z5;
        this.zzdvh = str2;
        this.zzgyi = arrayList;
        this.zzdvi = str3;
        this.zzdvj = str4;
        this.zzgyj = str5;
        this.zzdvk = z6;
        this.zzgyk = str6;
        this.zzgyl = j;
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        bundle.putBoolean("cog", this.zzdvb);
        bundle.putBoolean("coh", this.zzdvc);
        bundle.putString("gl", this.zzdvd);
        bundle.putBoolean("simulator", this.zzdve);
        bundle.putBoolean("is_latchsky", this.zzdvf);
        bundle.putBoolean("is_sidewinder", this.zzdvg);
        bundle.putString("hl", this.zzdvh);
        if (!this.zzgyi.isEmpty()) {
            bundle.putStringArrayList("hl_list", this.zzgyi);
        }
        bundle.putString(ContentDiscoveryManifest.MANIFEST_VERSION_KEY, this.zzdvi);
        bundle.putString("submodel", this.zzgyk);
        Bundle zza = zzdot.zza(bundle, "device");
        bundle.putBundle("device", zza);
        zza.putString("build", this.zzgyj);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcrq)).booleanValue()) {
            zza.putLong("remaining_data_partition_space", this.zzgyl);
        }
        Bundle zza2 = zzdot.zza(zza, "browser");
        zza.putBundle("browser", zza2);
        zza2.putBoolean("is_browser_custom_tabs_capable", this.zzdvk);
        if (!TextUtils.isEmpty(this.zzdvj)) {
            Bundle zza3 = zzdot.zza(zza, "play_store");
            zza.putBundle("play_store", zza3);
            zza3.putString("package_version", this.zzdvj);
        }
    }
}

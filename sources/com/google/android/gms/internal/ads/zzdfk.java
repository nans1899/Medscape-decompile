package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import io.branch.indexing.ContentDiscoveryManifest;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfk implements zzdgu<Bundle>, zzdgx<zzdgu<Bundle>> {
    private final ApplicationInfo applicationInfo;
    private final PackageInfo zzdqs;

    zzdfk(ApplicationInfo applicationInfo2, PackageInfo packageInfo) {
        this.applicationInfo = applicationInfo2;
        this.zzdqs = packageInfo;
    }

    public final zzdzc<zzdgu<Bundle>> zzarj() {
        return zzdyq.zzaf(this);
    }

    public final /* synthetic */ void zzs(Object obj) {
        Bundle bundle = (Bundle) obj;
        String str = this.applicationInfo.packageName;
        PackageInfo packageInfo = this.zzdqs;
        String str2 = null;
        Integer valueOf = packageInfo == null ? null : Integer.valueOf(packageInfo.versionCode);
        bundle.putString(ContentDiscoveryManifest.PACKAGE_NAME_KEY, str);
        if (valueOf != null) {
            bundle.putInt("vc", valueOf.intValue());
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvv)).booleanValue()) {
            PackageInfo packageInfo2 = this.zzdqs;
            if (packageInfo2 != null) {
                str2 = packageInfo2.versionName;
            }
            if (str2 != null) {
                bundle.putString("vnm", str2);
            }
        }
    }
}

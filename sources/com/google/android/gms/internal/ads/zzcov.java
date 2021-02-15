package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcov implements zzeoy<PackageInfo> {
    private final zzeph<Context> zzesu;
    private final zzeph<ApplicationInfo> zzgjm;

    private zzcov(zzeph<Context> zzeph, zzeph<ApplicationInfo> zzeph2) {
        this.zzesu = zzeph;
        this.zzgjm = zzeph2;
    }

    public static zzcov zzaq(zzeph<Context> zzeph, zzeph<ApplicationInfo> zzeph2) {
        return new zzcov(zzeph, zzeph2);
    }

    public final /* synthetic */ Object get() {
        return zzcol.zza(this.zzesu.get(), this.zzgjm.get());
    }
}

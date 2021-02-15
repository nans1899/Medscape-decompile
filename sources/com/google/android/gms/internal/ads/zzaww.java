package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzaww implements zzaxe {
    private final Context zzcyt;
    private final String zzdgm;

    zzaww(Context context, String str) {
        this.zzcyt = context;
        this.zzdgm = str;
    }

    public final void zzb(zzbiq zzbiq) {
        Context context = this.zzcyt;
        zzbiq.zzb(ObjectWrapper.wrap(context), this.zzdgm, context.getPackageName());
    }
}

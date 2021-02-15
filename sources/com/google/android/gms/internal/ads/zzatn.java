package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzatn {
    /* access modifiers changed from: private */
    public WeakHashMap<Context, zzatp> zzdvm = new WeakHashMap<>();

    public final Future<zzatl> zzy(Context context) {
        return zzbbz.zzeep.zze(new zzatm(this, context));
    }
}

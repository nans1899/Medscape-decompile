package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.WeakHashMap;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzqn {
    private final Object lock = new Object();
    private final Context zzaai;
    private final zzbbx zzbpe;
    private final WeakHashMap<Object, Object> zzbqy = new WeakHashMap<>();
    private final ArrayList<Object> zzbqz = new ArrayList<>();
    private final zzakr zzbra;

    public zzqn(Context context, zzbbx zzbbx) {
        this.zzaai = context.getApplicationContext();
        this.zzbpe = zzbbx;
        this.zzbra = new zzakr(context.getApplicationContext(), zzbbx, (String) zzwm.zzpx().zzd(zzabb.zzclt));
    }
}

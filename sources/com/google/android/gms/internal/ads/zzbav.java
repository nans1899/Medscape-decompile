package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbav {
    private Map<Integer, Bitmap> zzedd = new ConcurrentHashMap();
    private AtomicInteger zzede = new AtomicInteger(0);

    public final Bitmap zza(Integer num) {
        return this.zzedd.get(num);
    }
}

package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbqs<AdT> implements zzbqp<AdT> {
    private final Map<String, zzcta<AdT>> zzfrw;

    zzbqs(Map<String, zzcta<AdT>> map) {
        this.zzfrw = map;
    }

    public final zzcta<AdT> zze(int i, String str) {
        return this.zzfrw.get(str);
    }
}

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbyf extends zzbyr<AppEventListener> implements zzagv {
    public zzbyf(Set<zzcab<AppEventListener>> set) {
        super(set);
    }

    public final synchronized void onAppEvent(String str, String str2) {
        zza(new zzbye(str, str2));
    }
}

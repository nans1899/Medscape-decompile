package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class CustomEventExtras implements NetworkExtras {
    private final HashMap<String, Object> zzesj = new HashMap<>();

    public final Object getExtra(String str) {
        return this.zzesj.get(str);
    }

    public final void setExtra(String str, Object obj) {
        this.zzesj.put(str, obj);
    }
}

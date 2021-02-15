package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfq implements zzdgu<Bundle> {
    private String zzdrb;

    public zzdfq(String str) {
        this.zzdrb = str;
    }

    public final /* synthetic */ void zzs(Object obj) {
        ((Bundle) obj).putString("request_id", this.zzdrb);
    }
}

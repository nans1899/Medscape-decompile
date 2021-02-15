package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdfj implements zzeoy<zzdfh> {
    private final zzeph<Context> zzesu;
    private final zzeph<zzdzb> zzgvb;
    private final zzeph<Set<String>> zzgvo;

    private zzdfj(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2, zzeph<Set<String>> zzeph3) {
        this.zzgvb = zzeph;
        this.zzesu = zzeph2;
        this.zzgvo = zzeph3;
    }

    public static zzdfj zzu(zzeph<zzdzb> zzeph, zzeph<Context> zzeph2, zzeph<Set<String>> zzeph3) {
        return new zzdfj(zzeph, zzeph2, zzeph3);
    }

    public final /* synthetic */ Object get() {
        return new zzdfh(this.zzgvb.get(), this.zzesu.get(), this.zzgvo.get());
    }
}

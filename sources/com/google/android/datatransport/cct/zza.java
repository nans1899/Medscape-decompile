package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.zzc;
import com.google.android.datatransport.runtime.retries.Function;

final /* synthetic */ class zza implements Function {
    private final zzc zza;

    private zza(zzc zzc) {
        this.zza = zzc;
    }

    public static Function zza(zzc zzc) {
        return new zza(zzc);
    }

    public Object apply(Object obj) {
        return this.zza.zza((zzc.zza) obj);
    }
}

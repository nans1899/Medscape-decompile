package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzhr implements zzkv {
    private final /* synthetic */ zzgy zza;

    zzhr(zzgy zzgy) {
        this.zza = zzgy;
    }

    public final void zza(String str, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_err", bundle, str);
        } else {
            this.zza.zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_err", bundle);
        }
    }
}

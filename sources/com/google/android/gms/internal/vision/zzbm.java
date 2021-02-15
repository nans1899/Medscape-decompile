package com.google.android.gms.internal.vision;

import android.util.Base64;
import android.util.Log;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzbm extends zzbi<T> {
    private final /* synthetic */ zzbp zzgs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbm(zzbo zzbo, String str, Object obj, boolean z, zzbp zzbp) {
        super(zzbo, str, obj, true, (zzbn) null);
        this.zzgs = zzbp;
    }

    /* access modifiers changed from: package-private */
    public final T zza(Object obj) {
        if (obj instanceof String) {
            try {
                return this.zzgs.zzb(Base64.decode((String) obj, 3));
            } catch (IOException | IllegalArgumentException unused) {
            }
        }
        String zzag = super.zzag();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzag).length() + 27 + String.valueOf(valueOf).length());
        sb.append("Invalid byte[] value for ");
        sb.append(zzag);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}

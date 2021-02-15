package com.google.android.gms.internal.icing;

import android.util.Log;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzbr extends zzbq<Boolean> {
    zzbr(zzbu zzbu, String str, Boolean bool) {
        super(zzbu, str, bool, (zzbs) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzax.zzbt.matcher(str).matches()) {
                return true;
            }
            if (zzax.zzbu.matcher(str).matches()) {
                return false;
            }
        }
        String zzu = super.zzu();
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(String.valueOf(zzu).length() + 28 + String.valueOf(valueOf).length());
        sb.append("Invalid boolean value for ");
        sb.append(zzu);
        sb.append(": ");
        sb.append(valueOf);
        Log.e("PhenotypeFlag", sb.toString());
        return null;
    }
}

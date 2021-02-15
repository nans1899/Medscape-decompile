package com.google.android.gms.internal.vision;

import android.content.Context;
import android.net.Uri;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public final class zzbo {
    final String zzgt;
    final Uri zzgu;
    final String zzgv;
    final String zzgw;
    final boolean zzgx;
    final boolean zzgy;
    final boolean zzgz;
    final boolean zzha;
    @Nullable
    final zzcw<Context, Boolean> zzhb;

    public zzbo(Uri uri) {
        this((String) null, uri, "", "", false, false, false, false, (zzcw<Context, Boolean>) null);
    }

    private zzbo(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzcw<Context, Boolean> zzcw) {
        this.zzgt = str;
        this.zzgu = uri;
        this.zzgv = str2;
        this.zzgw = str3;
        this.zzgx = z;
        this.zzgy = z2;
        this.zzgz = z3;
        this.zzha = z4;
        this.zzhb = zzcw;
    }

    public final zzbo zzf(String str) {
        boolean z = this.zzgx;
        if (!z) {
            return new zzbo(this.zzgt, this.zzgu, str, this.zzgw, z, this.zzgy, this.zzgz, this.zzha, this.zzhb);
        }
        throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
    }

    public final <T> zzbi<T> zza(String str, T t, zzbp<T> zzbp) {
        return zzbi.zza(this, str, t, zzbp, true);
    }
}

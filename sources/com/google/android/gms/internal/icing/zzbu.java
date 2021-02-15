package com.google.android.gms.internal.icing;

import android.content.Context;
import android.net.Uri;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzbu {
    final String zzdk;
    final Uri zzdl;
    final String zzdm;
    final String zzdn;
    final boolean zzdo;
    final boolean zzdp;
    final boolean zzdq;
    final boolean zzdr;
    @Nullable
    final zzby<Context, Boolean> zzds;

    public zzbu(Uri uri) {
        this((String) null, uri, "", "", false, false, false, false, (zzby<Context, Boolean>) null);
    }

    private zzbu(String str, Uri uri, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, @Nullable zzby<Context, Boolean> zzby) {
        this.zzdk = null;
        this.zzdl = uri;
        this.zzdm = str2;
        this.zzdn = str3;
        this.zzdo = false;
        this.zzdp = false;
        this.zzdq = false;
        this.zzdr = false;
        this.zzds = null;
    }

    public final zzbq<Boolean> zza(String str, boolean z) {
        return zzbq.zza(this, str, z);
    }
}

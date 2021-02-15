package com.google.android.gms.internal.ads;

import org.apache.commons.io.IOUtils;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbai implements zzai {
    private final /* synthetic */ String zzecs;
    private final /* synthetic */ zzban zzect;

    zzbai(zzbag zzbag, String str, zzban zzban) {
        this.zzecs = str;
        this.zzect = zzban;
    }

    public final void zzc(zzao zzao) {
        String str = this.zzecs;
        String zzao2 = zzao.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(zzao2).length());
        sb.append("Failed to load URL: ");
        sb.append(str);
        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        sb.append(zzao2);
        zzayp.zzfe(sb.toString());
        this.zzect.zzb(null);
    }
}

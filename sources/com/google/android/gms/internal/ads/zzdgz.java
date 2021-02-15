package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzdgz implements Runnable {
    private final zzdgx zzgyg;
    private final long zzgyh;

    zzdgz(zzdgx zzdgx, long j) {
        this.zzgyg = zzdgx;
        this.zzgyh = j;
    }

    public final void run() {
        zzdgx zzdgx = this.zzgyg;
        long j = this.zzgyh;
        String canonicalName = zzdgx.getClass().getCanonicalName();
        long elapsedRealtime = zzp.zzky().elapsedRealtime() - j;
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 40);
        sb.append("Signal runtime : ");
        sb.append(canonicalName);
        sb.append(" = ");
        sb.append(elapsedRealtime);
        zzayp.zzei(sb.toString());
    }
}

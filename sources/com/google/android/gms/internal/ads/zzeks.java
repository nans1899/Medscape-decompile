package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzeks {
    private static final zzejm zzief = zzejm.zzbft();
    private zzeip zzikw;
    private volatile zzelj zzikx;
    private volatile zzeip zziky;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzeks)) {
            return false;
        }
        zzeks zzeks = (zzeks) obj;
        zzelj zzelj = this.zzikx;
        zzelj zzelj2 = zzeks.zzikx;
        if (zzelj == null && zzelj2 == null) {
            return zzbdw().equals(zzeks.zzbdw());
        }
        if (zzelj != null && zzelj2 != null) {
            return zzelj.equals(zzelj2);
        }
        if (zzelj != null) {
            return zzelj.equals(zzeks.zzm(zzelj.zzbgn()));
        }
        return zzm(zzelj2.zzbgn()).equals(zzelj2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.zzelj zzm(com.google.android.gms.internal.ads.zzelj r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.ads.zzelj r0 = r1.zzikx
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzelj r0 = r1.zzikx     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzikx = r2     // Catch:{ zzekj -> 0x0012 }
            com.google.android.gms.internal.ads.zzeip r0 = com.google.android.gms.internal.ads.zzeip.zzier     // Catch:{ zzekj -> 0x0012 }
            r1.zziky = r0     // Catch:{ zzekj -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzikx = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.ads.zzeip r2 = com.google.android.gms.internal.ads.zzeip.zzier     // Catch:{ all -> 0x001a }
            r1.zziky = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.ads.zzelj r2 = r1.zzikx
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeks.zzm(com.google.android.gms.internal.ads.zzelj):com.google.android.gms.internal.ads.zzelj");
    }

    public final zzelj zzn(zzelj zzelj) {
        zzelj zzelj2 = this.zzikx;
        this.zzikw = null;
        this.zziky = null;
        this.zzikx = zzelj;
        return zzelj2;
    }

    public final int zzbgh() {
        if (this.zziky != null) {
            return this.zziky.size();
        }
        if (this.zzikx != null) {
            return this.zzikx.zzbgh();
        }
        return 0;
    }

    public final zzeip zzbdw() {
        if (this.zziky != null) {
            return this.zziky;
        }
        synchronized (this) {
            if (this.zziky != null) {
                zzeip zzeip = this.zziky;
                return zzeip;
            }
            if (this.zzikx == null) {
                this.zziky = zzeip.zzier;
            } else {
                this.zziky = this.zzikx.zzbdw();
            }
            zzeip zzeip2 = this.zziky;
            return zzeip2;
        }
    }
}

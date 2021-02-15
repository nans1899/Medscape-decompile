package com.google.android.gms.internal.icing;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public class zzem {
    private static final zzdo zzgd = zzdo.zzaz();
    private zzct zzlt;
    private volatile zzfh zzlu;
    private volatile zzct zzlv;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzem)) {
            return false;
        }
        zzem zzem = (zzem) obj;
        zzfh zzfh = this.zzlu;
        zzfh zzfh2 = zzem.zzlu;
        if (zzfh == null && zzfh2 == null) {
            return zzad().equals(zzem.zzad());
        }
        if (zzfh != null && zzfh2 != null) {
            return zzfh.equals(zzfh2);
        }
        if (zzfh != null) {
            return zzfh.equals(zzem.zzg(zzfh.zzbr()));
        }
        return zzg(zzfh2.zzbr()).equals(zzfh2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.icing.zzfh zzg(com.google.android.gms.internal.icing.zzfh r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.icing.zzfh r0 = r1.zzlu
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.icing.zzfh r0 = r1.zzlu     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzlu = r2     // Catch:{ zzeh -> 0x0012 }
            com.google.android.gms.internal.icing.zzct r0 = com.google.android.gms.internal.icing.zzct.zzgi     // Catch:{ zzeh -> 0x0012 }
            r1.zzlv = r0     // Catch:{ zzeh -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzlu = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.icing.zzct r2 = com.google.android.gms.internal.icing.zzct.zzgi     // Catch:{ all -> 0x001a }
            r1.zzlv = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.icing.zzfh r2 = r1.zzlu
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.icing.zzem.zzg(com.google.android.gms.internal.icing.zzfh):com.google.android.gms.internal.icing.zzfh");
    }

    public final zzfh zzh(zzfh zzfh) {
        zzfh zzfh2 = this.zzlu;
        this.zzlt = null;
        this.zzlv = null;
        this.zzlu = zzfh;
        return zzfh2;
    }

    public final int zzbl() {
        if (this.zzlv != null) {
            return this.zzlv.size();
        }
        if (this.zzlu != null) {
            return this.zzlu.zzbl();
        }
        return 0;
    }

    public final zzct zzad() {
        if (this.zzlv != null) {
            return this.zzlv;
        }
        synchronized (this) {
            if (this.zzlv != null) {
                zzct zzct = this.zzlv;
                return zzct;
            }
            if (this.zzlu == null) {
                this.zzlv = zzct.zzgi;
            } else {
                this.zzlv = this.zzlu.zzad();
            }
            zzct zzct2 = this.zzlv;
            return zzct2;
        }
    }
}

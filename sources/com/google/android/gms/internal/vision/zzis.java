package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public class zzis {
    private static final zzho zzth = zzho.zzgf();
    private zzgs zzzn;
    private volatile zzjn zzzo;
    private volatile zzgs zzzp;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzis)) {
            return false;
        }
        zzis zzis = (zzis) obj;
        zzjn zzjn = this.zzzo;
        zzjn zzjn2 = zzis.zzzo;
        if (zzjn == null && zzjn2 == null) {
            return zzee().equals(zzis.zzee());
        }
        if (zzjn != null && zzjn2 != null) {
            return zzjn.equals(zzjn2);
        }
        if (zzjn != null) {
            return zzjn.equals(zzis.zzh(zzjn.zzgx()));
        }
        return zzh(zzjn2.zzgx()).equals(zzjn2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.vision.zzjn zzh(com.google.android.gms.internal.vision.zzjn r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.vision.zzjn r0 = r1.zzzo
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.vision.zzjn r0 = r1.zzzo     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzzo = r2     // Catch:{ zzin -> 0x0012 }
            com.google.android.gms.internal.vision.zzgs r0 = com.google.android.gms.internal.vision.zzgs.zztt     // Catch:{ zzin -> 0x0012 }
            r1.zzzp = r0     // Catch:{ zzin -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzzo = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.vision.zzgs r2 = com.google.android.gms.internal.vision.zzgs.zztt     // Catch:{ all -> 0x001a }
            r1.zzzp = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.vision.zzjn r2 = r1.zzzo
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzis.zzh(com.google.android.gms.internal.vision.zzjn):com.google.android.gms.internal.vision.zzjn");
    }

    public final zzjn zzi(zzjn zzjn) {
        zzjn zzjn2 = this.zzzo;
        this.zzzn = null;
        this.zzzp = null;
        this.zzzo = zzjn;
        return zzjn2;
    }

    public final int zzgz() {
        if (this.zzzp != null) {
            return this.zzzp.size();
        }
        if (this.zzzo != null) {
            return this.zzzo.zzgz();
        }
        return 0;
    }

    public final zzgs zzee() {
        if (this.zzzp != null) {
            return this.zzzp;
        }
        synchronized (this) {
            if (this.zzzp != null) {
                zzgs zzgs = this.zzzp;
                return zzgs;
            }
            if (this.zzzo == null) {
                this.zzzp = zzgs.zztt;
            } else {
                this.zzzp = this.zzzo.zzee();
            }
            zzgs zzgs2 = this.zzzp;
            return zzgs2;
        }
    }
}

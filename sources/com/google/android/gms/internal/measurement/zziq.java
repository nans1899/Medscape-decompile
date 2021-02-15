package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public class zziq {
    private static final zzhm zza = zzhm.zza();
    private zzgr zzb;
    private volatile zzjh zzc;
    private volatile zzgr zzd;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zziq)) {
            return false;
        }
        zziq zziq = (zziq) obj;
        zzjh zzjh = this.zzc;
        zzjh zzjh2 = zziq.zzc;
        if (zzjh == null && zzjh2 == null) {
            return zzc().equals(zziq.zzc());
        }
        if (zzjh != null && zzjh2 != null) {
            return zzjh.equals(zzjh2);
        }
        if (zzjh != null) {
            return zzjh.equals(zziq.zzb(zzjh.zzaa()));
        }
        return zzb(zzjh2.zzaa()).equals(zzjh2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzjh zzb(com.google.android.gms.internal.measurement.zzjh r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.measurement.zzjh r0 = r1.zzc
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzjh r0 = r1.zzc     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzc = r2     // Catch:{ zzih -> 0x0012 }
            com.google.android.gms.internal.measurement.zzgr r0 = com.google.android.gms.internal.measurement.zzgr.zza     // Catch:{ zzih -> 0x0012 }
            r1.zzd = r0     // Catch:{ zzih -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzc = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.measurement.zzgr r2 = com.google.android.gms.internal.measurement.zzgr.zza     // Catch:{ all -> 0x001a }
            r1.zzd = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.measurement.zzjh r2 = r1.zzc
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zziq.zzb(com.google.android.gms.internal.measurement.zzjh):com.google.android.gms.internal.measurement.zzjh");
    }

    public final zzjh zza(zzjh zzjh) {
        zzjh zzjh2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzjh;
        return zzjh2;
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzbo();
        }
        return 0;
    }

    public final zzgr zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                zzgr zzgr = this.zzd;
                return zzgr;
            }
            if (this.zzc == null) {
                this.zzd = zzgr.zza;
            } else {
                this.zzd = this.zzc.zzbj();
            }
            zzgr zzgr2 = this.zzd;
            return zzgr2;
        }
    }
}

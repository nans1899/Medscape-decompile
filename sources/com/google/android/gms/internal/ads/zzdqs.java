package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbqo;
import java.util.LinkedList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdqs<AdT extends zzbqo> {
    /* access modifiers changed from: private */
    public final zzdpz zzhbu;
    private final zzdpw zzhiy;
    /* access modifiers changed from: private */
    public zzdqy zzhiz;
    /* access modifiers changed from: private */
    public zzdzj<zzdqk<AdT>> zzhja;
    private zzdzc<zzdqk<AdT>> zzhjb;
    /* access modifiers changed from: private */
    public int zzhjc = zzdqi.zzhim;
    /* access modifiers changed from: private */
    public final zzdqv<AdT> zzhjd;
    private final LinkedList<zzdqy> zzhje;
    private final zzdyr<zzdqk<AdT>> zzhjf = new zzdqt(this);

    public zzdqs(zzdpz zzdpz, zzdpw zzdpw, zzdqv<AdT> zzdqv) {
        this.zzhbu = zzdpz;
        this.zzhiy = zzdpw;
        this.zzhjd = zzdqv;
        this.zzhje = new LinkedList<>();
        this.zzhiy.zza(new zzdqu(this));
    }

    public final void zzb(zzdqy zzdqy) {
        this.zzhje.add(zzdqy);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.android.gms.internal.ads.zzdzc<com.google.android.gms.internal.ads.zzdqw<AdT>> zzc(com.google.android.gms.internal.ads.zzdqy r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.zzaus()     // Catch:{ all -> 0x0044 }
            r1 = 0
            if (r0 == 0) goto L_0x000a
            monitor-exit(r3)
            return r1
        L_0x000a:
            int r0 = com.google.android.gms.internal.ads.zzdqi.zzhio     // Catch:{ all -> 0x0044 }
            r3.zzhjc = r0     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzdqy r0 = r3.zzhiz     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzdqj r0 = r0.zzasx()     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            com.google.android.gms.internal.ads.zzdqj r0 = r4.zzasx()     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0042
            com.google.android.gms.internal.ads.zzdqy r0 = r3.zzhiz     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzdqj r0 = r0.zzasx()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzdqj r2 = r4.zzasx()     // Catch:{ all -> 0x0044 }
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0044 }
            if (r0 != 0) goto L_0x002d
            goto L_0x0042
        L_0x002d:
            int r0 = com.google.android.gms.internal.ads.zzdqi.zzhin     // Catch:{ all -> 0x0044 }
            r3.zzhjc = r0     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzdzj<com.google.android.gms.internal.ads.zzdqk<AdT>> r0 = r3.zzhja     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzdqr r1 = new com.google.android.gms.internal.ads.zzdqr     // Catch:{ all -> 0x0044 }
            r1.<init>(r3)     // Catch:{ all -> 0x0044 }
            java.util.concurrent.Executor r4 = r4.getExecutor()     // Catch:{ all -> 0x0044 }
            com.google.android.gms.internal.ads.zzdzc r4 = com.google.android.gms.internal.ads.zzdyq.zzb(r0, r1, (java.util.concurrent.Executor) r4)     // Catch:{ all -> 0x0044 }
            monitor-exit(r3)
            return r4
        L_0x0042:
            monitor-exit(r3)
            return r1
        L_0x0044:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzdqs.zzc(com.google.android.gms.internal.ads.zzdqy):com.google.android.gms.internal.ads.zzdzc");
    }

    /* access modifiers changed from: private */
    public final void zzd(zzdqy zzdqy) {
        while (zzaus()) {
            if (zzdqy != null || !this.zzhje.isEmpty()) {
                if (zzdqy == null) {
                    zzdqy = this.zzhje.remove();
                }
                if (zzdqy.zzasx() == null || !this.zzhbu.zzb(zzdqy.zzasx())) {
                    zzdqy = null;
                } else {
                    this.zzhiz = zzdqy.zzasy();
                    this.zzhja = zzdzj.zzayi();
                    zzdzc<zzdqk<AdT>> zza = this.zzhjd.zza(this.zzhiz);
                    this.zzhjb = zza;
                    zzdyq.zza(zza, this.zzhjf, zzdqy.getExecutor());
                    return;
                }
            } else {
                return;
            }
        }
        if (zzdqy != null) {
            this.zzhje.add(zzdqy);
        }
    }

    private final boolean zzaus() {
        zzdzc<zzdqk<AdT>> zzdzc = this.zzhjb;
        return zzdzc == null || zzdzc.isDone();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaut() {
        synchronized (this) {
            zzd(this.zzhiz);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ zzdzc zzc(zzdqk zzdqk) throws Exception {
        zzdzc zzaf;
        synchronized (this) {
            zzaf = zzdyq.zzaf(new zzdqw(zzdqk, this.zzhiz));
        }
        return zzaf;
    }
}

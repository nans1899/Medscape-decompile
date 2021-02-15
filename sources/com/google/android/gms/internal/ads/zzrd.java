package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzrd {
    private final Object lock = new Object();
    private int zzbsd;
    private List<zzre> zzbse = new LinkedList();

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0059, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzre zzo(boolean r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.lock
            monitor-enter(r0)
            java.util.List<com.google.android.gms.internal.ads.zzre> r1 = r7.zzbse     // Catch:{ all -> 0x005a }
            int r1 = r1.size()     // Catch:{ all -> 0x005a }
            r2 = 0
            if (r1 != 0) goto L_0x0013
            java.lang.String r8 = "Queue empty"
            com.google.android.gms.internal.ads.zzayp.zzef(r8)     // Catch:{ all -> 0x005a }
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return r2
        L_0x0013:
            java.util.List<com.google.android.gms.internal.ads.zzre> r1 = r7.zzbse     // Catch:{ all -> 0x005a }
            int r1 = r1.size()     // Catch:{ all -> 0x005a }
            r3 = 2
            r4 = 0
            if (r1 < r3) goto L_0x0045
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            java.util.List<com.google.android.gms.internal.ads.zzre> r1 = r7.zzbse     // Catch:{ all -> 0x005a }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x005a }
            r3 = 0
        L_0x0026:
            boolean r5 = r1.hasNext()     // Catch:{ all -> 0x005a }
            if (r5 == 0) goto L_0x003e
            java.lang.Object r5 = r1.next()     // Catch:{ all -> 0x005a }
            com.google.android.gms.internal.ads.zzre r5 = (com.google.android.gms.internal.ads.zzre) r5     // Catch:{ all -> 0x005a }
            int r6 = r5.getScore()     // Catch:{ all -> 0x005a }
            if (r6 <= r8) goto L_0x003b
            r4 = r3
            r2 = r5
            r8 = r6
        L_0x003b:
            int r3 = r3 + 1
            goto L_0x0026
        L_0x003e:
            java.util.List<com.google.android.gms.internal.ads.zzre> r8 = r7.zzbse     // Catch:{ all -> 0x005a }
            r8.remove(r4)     // Catch:{ all -> 0x005a }
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return r2
        L_0x0045:
            java.util.List<com.google.android.gms.internal.ads.zzre> r1 = r7.zzbse     // Catch:{ all -> 0x005a }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x005a }
            com.google.android.gms.internal.ads.zzre r1 = (com.google.android.gms.internal.ads.zzre) r1     // Catch:{ all -> 0x005a }
            if (r8 == 0) goto L_0x0055
            java.util.List<com.google.android.gms.internal.ads.zzre> r8 = r7.zzbse     // Catch:{ all -> 0x005a }
            r8.remove(r4)     // Catch:{ all -> 0x005a }
            goto L_0x0058
        L_0x0055:
            r1.zzmc()     // Catch:{ all -> 0x005a }
        L_0x0058:
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            return r1
        L_0x005a:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005a }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrd.zzo(boolean):com.google.android.gms.internal.ads.zzre");
    }

    public final boolean zza(zzre zzre) {
        synchronized (this.lock) {
            if (this.zzbse.contains(zzre)) {
                return true;
            }
            return false;
        }
    }

    public final boolean zzb(zzre zzre) {
        synchronized (this.lock) {
            Iterator<zzre> it = this.zzbse.iterator();
            while (it.hasNext()) {
                zzre next = it.next();
                if (!zzp.zzkv().zzwt().zzxe()) {
                    if (zzre != next && next.zzlz().equals(zzre.zzlz())) {
                        it.remove();
                        return true;
                    }
                } else if (!zzp.zzkv().zzwt().zzxg() && zzre != next && next.zzmb().equals(zzre.zzmb())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzre zzre) {
        synchronized (this.lock) {
            if (this.zzbse.size() >= 10) {
                int size = this.zzbse.size();
                StringBuilder sb = new StringBuilder(41);
                sb.append("Queue is full, current size = ");
                sb.append(size);
                zzayp.zzef(sb.toString());
                this.zzbse.remove(0);
            }
            int i = this.zzbsd;
            this.zzbsd = i + 1;
            zzre.zzbw(i);
            zzre.zzmf();
            this.zzbse.add(zzre);
        }
    }
}

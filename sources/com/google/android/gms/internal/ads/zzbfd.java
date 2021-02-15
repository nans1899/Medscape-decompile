package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzbfd {
    private long zzdh;

    /* JADX WARNING: type inference failed for: r0v8, types: [com.google.android.gms.internal.ads.zzbp] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzq(java.nio.ByteBuffer r9) {
        /*
            r8 = this;
            long r0 = r8.zzdh
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0009
            return r0
        L_0x0009:
            java.nio.ByteBuffer r9 = r9.duplicate()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            r9.flip()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            com.google.android.gms.internal.ads.zzbfa r0 = new com.google.android.gms.internal.ads.zzbfa     // Catch:{ IOException | RuntimeException -> 0x0064 }
            r0.<init>(r9)     // Catch:{ IOException | RuntimeException -> 0x0064 }
            com.google.android.gms.internal.ads.zzbn r9 = new com.google.android.gms.internal.ads.zzbn     // Catch:{ IOException | RuntimeException -> 0x0064 }
            com.google.android.gms.internal.ads.zzbff r1 = com.google.android.gms.internal.ads.zzbff.zzelj     // Catch:{ IOException | RuntimeException -> 0x0064 }
            r9.<init>(r0, r1)     // Catch:{ IOException | RuntimeException -> 0x0064 }
            java.util.List r9 = r9.zzbkg()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ IOException | RuntimeException -> 0x0064 }
        L_0x0024:
            boolean r0 = r9.hasNext()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            r1 = 0
            if (r0 == 0) goto L_0x0038
            java.lang.Object r0 = r9.next()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            com.google.android.gms.internal.ads.zzbp r0 = (com.google.android.gms.internal.ads.zzbp) r0     // Catch:{ IOException | RuntimeException -> 0x0064 }
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzbr     // Catch:{ IOException | RuntimeException -> 0x0064 }
            if (r4 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzbr r0 = (com.google.android.gms.internal.ads.zzbr) r0     // Catch:{ IOException | RuntimeException -> 0x0064 }
            goto L_0x0039
        L_0x0038:
            r0 = r1
        L_0x0039:
            java.util.List r9 = r0.zzbkg()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ IOException | RuntimeException -> 0x0064 }
        L_0x0041:
            boolean r0 = r9.hasNext()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            if (r0 == 0) goto L_0x0054
            java.lang.Object r0 = r9.next()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            com.google.android.gms.internal.ads.zzbp r0 = (com.google.android.gms.internal.ads.zzbp) r0     // Catch:{ IOException | RuntimeException -> 0x0064 }
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzbu     // Catch:{ IOException | RuntimeException -> 0x0064 }
            if (r4 == 0) goto L_0x0041
            r1 = r0
            com.google.android.gms.internal.ads.zzbu r1 = (com.google.android.gms.internal.ads.zzbu) r1     // Catch:{ IOException | RuntimeException -> 0x0064 }
        L_0x0054:
            r4 = 1000(0x3e8, double:4.94E-321)
            long r6 = r1.getDuration()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            long r6 = r6 * r4
            long r0 = r1.zzr()     // Catch:{ IOException | RuntimeException -> 0x0064 }
            long r6 = r6 / r0
            r8.zzdh = r6     // Catch:{ IOException | RuntimeException -> 0x0064 }
            return r6
        L_0x0064:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbfd.zzq(java.nio.ByteBuffer):long");
    }
}

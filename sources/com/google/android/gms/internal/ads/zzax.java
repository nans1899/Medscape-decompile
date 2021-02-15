package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzax {
    long zzcm;
    final String zzcn;
    final String zzr;
    final long zzs;
    final long zzt;
    final long zzu;
    final long zzv;
    final List<zzu> zzx;

    private zzax(String str, String str2, long j, long j2, long j3, long j4, List<zzu> list) {
        this.zzcn = str;
        this.zzr = "".equals(str2) ? null : str2;
        this.zzs = j;
        this.zzt = j2;
        this.zzu = j3;
        this.zzv = j4;
        this.zzx = list;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    zzax(java.lang.String r14, com.google.android.gms.internal.ads.zzn r15) {
        /*
            r13 = this;
            java.lang.String r2 = r15.zzr
            long r3 = r15.zzs
            long r5 = r15.zzt
            long r7 = r15.zzu
            long r9 = r15.zzv
            java.util.List<com.google.android.gms.internal.ads.zzu> r0 = r15.zzx
            if (r0 == 0) goto L_0x0012
            java.util.List<com.google.android.gms.internal.ads.zzu> r15 = r15.zzx
            r11 = r15
            goto L_0x0047
        L_0x0012:
            java.util.Map<java.lang.String, java.lang.String> r15 = r15.zzw
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r15.size()
            r0.<init>(r1)
            java.util.Set r15 = r15.entrySet()
            java.util.Iterator r15 = r15.iterator()
        L_0x0025:
            boolean r1 = r15.hasNext()
            if (r1 == 0) goto L_0x0046
            java.lang.Object r1 = r15.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            com.google.android.gms.internal.ads.zzu r11 = new com.google.android.gms.internal.ads.zzu
            java.lang.Object r12 = r1.getKey()
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            r11.<init>(r12, r1)
            r0.add(r11)
            goto L_0x0025
        L_0x0046:
            r11 = r0
        L_0x0047:
            r0 = r13
            r1 = r14
            r0.<init>(r1, r2, r3, r5, r7, r9, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzax.<init>(java.lang.String, com.google.android.gms.internal.ads.zzn):void");
    }

    static zzax zzc(zzba zzba) throws IOException {
        if (zzav.zzb((InputStream) zzba) == 538247942) {
            return new zzax(zzav.zza(zzba), zzav.zza(zzba), zzav.zzc(zzba), zzav.zzc(zzba), zzav.zzc(zzba), zzav.zzc(zzba), zzav.zzb(zzba));
        }
        throw new IOException();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza(OutputStream outputStream) {
        try {
            zzav.zza(outputStream, 538247942);
            zzav.zza(outputStream, this.zzcn);
            zzav.zza(outputStream, this.zzr == null ? "" : this.zzr);
            zzav.zza(outputStream, this.zzs);
            zzav.zza(outputStream, this.zzt);
            zzav.zza(outputStream, this.zzu);
            zzav.zza(outputStream, this.zzv);
            List<zzu> list = this.zzx;
            if (list != null) {
                zzav.zza(outputStream, list.size());
                for (zzu next : list) {
                    zzav.zza(outputStream, next.getName());
                    zzav.zza(outputStream, next.getValue());
                }
            } else {
                zzav.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzaq.d("%s", e.toString());
            return false;
        }
    }
}

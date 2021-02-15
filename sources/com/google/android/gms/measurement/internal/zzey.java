package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.net.URL;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzey implements Runnable {
    private final URL zza;
    private final byte[] zzb;
    private final zzew zzc;
    private final String zzd;
    private final Map<String, String> zze;
    private final /* synthetic */ zzeu zzf;

    public zzey(zzeu zzeu, String str, URL url, byte[] bArr, Map<String, String> map, zzew zzew) {
        this.zzf = zzeu;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzew);
        this.zza = url;
        this.zzb = bArr;
        this.zzc = zzew;
        this.zzd = str;
        this.zze = map;
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00d1 A[SYNTHETIC, Splitter:B:47:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x010c A[SYNTHETIC, Splitter:B:60:0x010c] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0126  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r13 = this;
            java.lang.String r0 = "Error closing HTTP compressed POST connection output stream. appId"
            com.google.android.gms.measurement.internal.zzeu r1 = r13.zzf
            r1.zzb()
            r1 = 0
            r2 = 0
            com.google.android.gms.measurement.internal.zzeu r3 = r13.zzf     // Catch:{ IOException -> 0x0105, all -> 0x00ca }
            java.net.URL r4 = r13.zza     // Catch:{ IOException -> 0x0105, all -> 0x00ca }
            java.net.HttpURLConnection r3 = r3.zza((java.net.URL) r4)     // Catch:{ IOException -> 0x0105, all -> 0x00ca }
            java.util.Map<java.lang.String, java.lang.String> r4 = r13.zze     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            if (r4 == 0) goto L_0x003b
            java.util.Map<java.lang.String, java.lang.String> r4 = r13.zze     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.util.Set r4 = r4.entrySet()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
        L_0x001f:
            boolean r5 = r4.hasNext()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            if (r5 == 0) goto L_0x003b
            java.lang.Object r5 = r4.next()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.lang.Object r6 = r5.getKey()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.lang.Object r5 = r5.getValue()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            r3.addRequestProperty(r6, r5)     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            goto L_0x001f
        L_0x003b:
            byte[] r4 = r13.zzb     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            if (r4 == 0) goto L_0x0088
            com.google.android.gms.measurement.internal.zzeu r4 = r13.zzf     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            com.google.android.gms.measurement.internal.zzks r4 = r4.f_()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            byte[] r5 = r13.zzb     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            byte[] r4 = r4.zzc(r5)     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            com.google.android.gms.measurement.internal.zzeu r5 = r13.zzf     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            com.google.android.gms.measurement.internal.zzer r5 = r5.zzq()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            com.google.android.gms.measurement.internal.zzet r5 = r5.zzw()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.lang.String r6 = "Uploading data. size"
            int r7 = r4.length     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            r5.zza(r6, r7)     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            r5 = 1
            r3.setDoOutput(r5)     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.lang.String r5 = "Content-Encoding"
            java.lang.String r6 = "gzip"
            r3.addRequestProperty(r5, r6)     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            int r5 = r4.length     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            r3.setFixedLengthStreamingMode(r5)     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            r3.connect()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            r5.write(r4)     // Catch:{ IOException -> 0x0082, all -> 0x007c }
            r5.close()     // Catch:{ IOException -> 0x0082, all -> 0x007c }
            goto L_0x0088
        L_0x007c:
            r4 = move-exception
            r10 = r1
            r2 = r4
            r1 = r5
            goto L_0x00ce
        L_0x0082:
            r4 = move-exception
            r10 = r1
            r8 = r4
            r1 = r5
            goto L_0x0109
        L_0x0088:
            int r8 = r3.getResponseCode()     // Catch:{ IOException -> 0x00c7, all -> 0x00c4 }
            java.util.Map r11 = r3.getHeaderFields()     // Catch:{ IOException -> 0x00bf, all -> 0x00ba }
            com.google.android.gms.measurement.internal.zzeu r2 = r13.zzf     // Catch:{ IOException -> 0x00b6, all -> 0x00b1 }
            byte[] r10 = com.google.android.gms.measurement.internal.zzeu.zza((java.net.HttpURLConnection) r3)     // Catch:{ IOException -> 0x00b6, all -> 0x00b1 }
            if (r3 == 0) goto L_0x009b
            r3.disconnect()
        L_0x009b:
            com.google.android.gms.measurement.internal.zzeu r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzfo r0 = r0.zzp()
            com.google.android.gms.measurement.internal.zzez r1 = new com.google.android.gms.measurement.internal.zzez
            java.lang.String r6 = r13.zzd
            com.google.android.gms.measurement.internal.zzew r7 = r13.zzc
            r9 = 0
            r12 = 0
            r5 = r1
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.zza((java.lang.Runnable) r1)
            return
        L_0x00b1:
            r4 = move-exception
            r2 = r4
            r7 = r8
            r10 = r11
            goto L_0x00cf
        L_0x00b6:
            r4 = move-exception
            r7 = r8
            r10 = r11
            goto L_0x00c2
        L_0x00ba:
            r4 = move-exception
            r10 = r1
            r2 = r4
            r7 = r8
            goto L_0x00cf
        L_0x00bf:
            r4 = move-exception
            r10 = r1
            r7 = r8
        L_0x00c2:
            r8 = r4
            goto L_0x010a
        L_0x00c4:
            r4 = move-exception
            r10 = r1
            goto L_0x00cd
        L_0x00c7:
            r4 = move-exception
            r10 = r1
            goto L_0x0108
        L_0x00ca:
            r4 = move-exception
            r3 = r1
            r10 = r3
        L_0x00cd:
            r2 = r4
        L_0x00ce:
            r7 = 0
        L_0x00cf:
            if (r1 == 0) goto L_0x00e9
            r1.close()     // Catch:{ IOException -> 0x00d5 }
            goto L_0x00e9
        L_0x00d5:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzeu r4 = r13.zzf
            com.google.android.gms.measurement.internal.zzer r4 = r4.zzq()
            com.google.android.gms.measurement.internal.zzet r4 = r4.zze()
            java.lang.String r5 = r13.zzd
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r5)
            r4.zza(r0, r5, r1)
        L_0x00e9:
            if (r3 == 0) goto L_0x00ee
            r3.disconnect()
        L_0x00ee:
            com.google.android.gms.measurement.internal.zzeu r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzfo r0 = r0.zzp()
            com.google.android.gms.measurement.internal.zzez r1 = new com.google.android.gms.measurement.internal.zzez
            java.lang.String r5 = r13.zzd
            com.google.android.gms.measurement.internal.zzew r6 = r13.zzc
            r8 = 0
            r9 = 0
            r11 = 0
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r0.zza((java.lang.Runnable) r1)
            throw r2
        L_0x0105:
            r4 = move-exception
            r3 = r1
            r10 = r3
        L_0x0108:
            r8 = r4
        L_0x0109:
            r7 = 0
        L_0x010a:
            if (r1 == 0) goto L_0x0124
            r1.close()     // Catch:{ IOException -> 0x0110 }
            goto L_0x0124
        L_0x0110:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzeu r2 = r13.zzf
            com.google.android.gms.measurement.internal.zzer r2 = r2.zzq()
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()
            java.lang.String r4 = r13.zzd
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzer.zza((java.lang.String) r4)
            r2.zza(r0, r4, r1)
        L_0x0124:
            if (r3 == 0) goto L_0x0129
            r3.disconnect()
        L_0x0129:
            com.google.android.gms.measurement.internal.zzeu r0 = r13.zzf
            com.google.android.gms.measurement.internal.zzfo r0 = r0.zzp()
            com.google.android.gms.measurement.internal.zzez r1 = new com.google.android.gms.measurement.internal.zzez
            java.lang.String r5 = r13.zzd
            com.google.android.gms.measurement.internal.zzew r6 = r13.zzc
            r9 = 0
            r11 = 0
            r4 = r1
            r4.<init>(r5, r6, r7, r8, r9, r10)
            r0.zza((java.lang.Runnable) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzey.run():void");
    }
}

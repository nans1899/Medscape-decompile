package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzid;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzhs extends zzhq<zzid.zzd> {
    zzhs() {
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(zzjn zzjn) {
        return zzjn instanceof zzid.zze;
    }

    /* access modifiers changed from: package-private */
    public final zzht<zzid.zzd> zzh(Object obj) {
        return ((zzid.zze) obj).zzyg;
    }

    /* access modifiers changed from: package-private */
    public final zzht<zzid.zzd> zzi(Object obj) {
        return ((zzid.zze) obj).zzhe();
    }

    /* access modifiers changed from: package-private */
    public final void zzj(Object obj) {
        zzh(obj).zzej();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v19, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v31, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v32, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v35, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v36, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v37, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v38, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v39, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v40, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v41, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v42, resolved type: com.google.android.gms.internal.vision.zzig} */
    /* JADX WARNING: type inference failed for: r3v4, types: [java.lang.Double] */
    /* JADX WARNING: type inference failed for: r3v5, types: [java.lang.Float] */
    /* JADX WARNING: type inference failed for: r3v6, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r3v7, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r3v8, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r3v9, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r3v10, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r3v11, types: [java.lang.Boolean] */
    /* JADX WARNING: type inference failed for: r3v12, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r3v13, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r3v14, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r3v15, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r3v16, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r3v17, types: [com.google.android.gms.internal.vision.zzgs] */
    /* JADX WARNING: type inference failed for: r3v18, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r3v21, types: [java.lang.Integer] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f8, code lost:
        if (r5 != 18) goto L_0x0107;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <UT, UB> UB zza(com.google.android.gms.internal.vision.zzkc r5, java.lang.Object r6, com.google.android.gms.internal.vision.zzho r7, com.google.android.gms.internal.vision.zzht<com.google.android.gms.internal.vision.zzid.zzd> r8, UB r9, com.google.android.gms.internal.vision.zzkx<UT, UB> r10) throws java.io.IOException {
        /*
            r4 = this;
            com.google.android.gms.internal.vision.zzid$zzg r6 = (com.google.android.gms.internal.vision.zzid.zzg) r6
            com.google.android.gms.internal.vision.zzid$zzd r0 = r6.zzyr
            int r0 = r0.number
            com.google.android.gms.internal.vision.zzid$zzd r1 = r6.zzyr
            boolean r1 = r1.zzye
            com.google.android.gms.internal.vision.zzid$zzd r1 = r6.zzyr
            com.google.android.gms.internal.vision.zzlk r1 = r1.zzyd
            com.google.android.gms.internal.vision.zzlk r2 = com.google.android.gms.internal.vision.zzlk.ENUM
            r3 = 0
            if (r1 != r2) goto L_0x0028
            int r5 = r5.zzes()
            com.google.android.gms.internal.vision.zzih r7 = r3.zzh(r5)
            if (r7 != 0) goto L_0x0022
            java.lang.Object r5 = com.google.android.gms.internal.vision.zzkh.zza((int) r0, (int) r5, r9, r10)
            return r5
        L_0x0022:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            goto L_0x00da
        L_0x0028:
            int[] r10 = com.google.android.gms.internal.vision.zzhr.zztn
            com.google.android.gms.internal.vision.zzid$zzd r0 = r6.zzyr
            com.google.android.gms.internal.vision.zzlk r0 = r0.zzyd
            int r0 = r0.ordinal()
            r10 = r10[r0]
            switch(r10) {
                case 1: goto L_0x00d2;
                case 2: goto L_0x00c9;
                case 3: goto L_0x00c0;
                case 4: goto L_0x00b7;
                case 5: goto L_0x00ae;
                case 6: goto L_0x00a5;
                case 7: goto L_0x009c;
                case 8: goto L_0x0093;
                case 9: goto L_0x008a;
                case 10: goto L_0x0081;
                case 11: goto L_0x0078;
                case 12: goto L_0x006f;
                case 13: goto L_0x0065;
                case 14: goto L_0x005d;
                case 15: goto L_0x0057;
                case 16: goto L_0x0051;
                case 17: goto L_0x0045;
                case 18: goto L_0x0039;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x00da
        L_0x0039:
            com.google.android.gms.internal.vision.zzjn r10 = r6.zzyq
            java.lang.Class r10 = r10.getClass()
            java.lang.Object r3 = r5.zza(r10, (com.google.android.gms.internal.vision.zzho) r7)
            goto L_0x00da
        L_0x0045:
            com.google.android.gms.internal.vision.zzjn r10 = r6.zzyq
            java.lang.Class r10 = r10.getClass()
            java.lang.Object r3 = r5.zzb(r10, r7)
            goto L_0x00da
        L_0x0051:
            java.lang.String r3 = r5.readString()
            goto L_0x00da
        L_0x0057:
            com.google.android.gms.internal.vision.zzgs r3 = r5.zzex()
            goto L_0x00da
        L_0x005d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "Shouldn't reach here."
            r5.<init>(r6)
            throw r5
        L_0x0065:
            long r0 = r5.zzfd()
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            goto L_0x00da
        L_0x006f:
            int r5 = r5.zzfc()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            goto L_0x00da
        L_0x0078:
            long r0 = r5.zzfb()
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            goto L_0x00da
        L_0x0081:
            int r5 = r5.zzfa()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            goto L_0x00da
        L_0x008a:
            int r5 = r5.zzey()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            goto L_0x00da
        L_0x0093:
            boolean r5 = r5.zzev()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r5)
            goto L_0x00da
        L_0x009c:
            int r5 = r5.zzeu()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            goto L_0x00da
        L_0x00a5:
            long r0 = r5.zzet()
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            goto L_0x00da
        L_0x00ae:
            int r5 = r5.zzes()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
            goto L_0x00da
        L_0x00b7:
            long r0 = r5.zzeq()
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            goto L_0x00da
        L_0x00c0:
            long r0 = r5.zzer()
            java.lang.Long r3 = java.lang.Long.valueOf(r0)
            goto L_0x00da
        L_0x00c9:
            float r5 = r5.readFloat()
            java.lang.Float r3 = java.lang.Float.valueOf(r5)
            goto L_0x00da
        L_0x00d2:
            double r0 = r5.readDouble()
            java.lang.Double r3 = java.lang.Double.valueOf(r0)
        L_0x00da:
            com.google.android.gms.internal.vision.zzid$zzd r5 = r6.zzyr
            boolean r5 = r5.zzye
            if (r5 == 0) goto L_0x00e6
            com.google.android.gms.internal.vision.zzid$zzd r5 = r6.zzyr
            r8.zzb(r5, (java.lang.Object) r3)
            goto L_0x010c
        L_0x00e6:
            int[] r5 = com.google.android.gms.internal.vision.zzhr.zztn
            com.google.android.gms.internal.vision.zzid$zzd r7 = r6.zzyr
            com.google.android.gms.internal.vision.zzlk r7 = r7.zzyd
            int r7 = r7.ordinal()
            r5 = r5[r7]
            r7 = 17
            if (r5 == r7) goto L_0x00fb
            r7 = 18
            if (r5 == r7) goto L_0x00fb
            goto L_0x0107
        L_0x00fb:
            com.google.android.gms.internal.vision.zzid$zzd r5 = r6.zzyr
            java.lang.Object r5 = r8.zza(r5)
            if (r5 == 0) goto L_0x0107
            java.lang.Object r3 = com.google.android.gms.internal.vision.zzie.zzb(r5, r3)
        L_0x0107:
            com.google.android.gms.internal.vision.zzid$zzd r5 = r6.zzyr
            r8.zza(r5, (java.lang.Object) r3)
        L_0x010c:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhs.zza(com.google.android.gms.internal.vision.zzkc, java.lang.Object, com.google.android.gms.internal.vision.zzho, com.google.android.gms.internal.vision.zzht, java.lang.Object, com.google.android.gms.internal.vision.zzkx):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public final int zza(Map.Entry<?, ?> entry) {
        return ((zzid.zzd) entry.getKey()).number;
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzlq zzlq, Map.Entry<?, ?> entry) throws IOException {
        zzid.zzd zzd = (zzid.zzd) entry.getKey();
        if (zzd.zzye) {
            switch (zzhr.zztn[zzd.zzyd.ordinal()]) {
                case 1:
                    zzkh.zza(zzd.number, (List<Double>) (List) entry.getValue(), zzlq, false);
                    return;
                case 2:
                    zzkh.zzb(zzd.number, (List<Float>) (List) entry.getValue(), zzlq, false);
                    return;
                case 3:
                    zzkh.zzc(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 4:
                    zzkh.zzd(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 5:
                    zzkh.zzh(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 6:
                    zzkh.zzf(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 7:
                    zzkh.zzk(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 8:
                    zzkh.zzn(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 9:
                    zzkh.zzi(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 10:
                    zzkh.zzl(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 11:
                    zzkh.zzg(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 12:
                    zzkh.zzj(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 13:
                    zzkh.zze(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 14:
                    zzkh.zzh(zzd.number, (List) entry.getValue(), zzlq, false);
                    return;
                case 15:
                    zzkh.zzb(zzd.number, (List) entry.getValue(), zzlq);
                    return;
                case 16:
                    zzkh.zza(zzd.number, (List<String>) (List) entry.getValue(), zzlq);
                    return;
                case 17:
                    List list = (List) entry.getValue();
                    if (list != null && !list.isEmpty()) {
                        zzkh.zzb(zzd.number, (List<?>) (List) entry.getValue(), zzlq, (zzkf) zzkb.zzik().zzf(list.get(0).getClass()));
                        return;
                    }
                    return;
                case 18:
                    List list2 = (List) entry.getValue();
                    if (list2 != null && !list2.isEmpty()) {
                        zzkh.zza(zzd.number, (List<?>) (List) entry.getValue(), zzlq, (zzkf) zzkb.zzik().zzf(list2.get(0).getClass()));
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            switch (zzhr.zztn[zzd.zzyd.ordinal()]) {
                case 1:
                    zzlq.zza(zzd.number, ((Double) entry.getValue()).doubleValue());
                    return;
                case 2:
                    zzlq.zza(zzd.number, ((Float) entry.getValue()).floatValue());
                    return;
                case 3:
                    zzlq.zzi(zzd.number, ((Long) entry.getValue()).longValue());
                    return;
                case 4:
                    zzlq.zza(zzd.number, ((Long) entry.getValue()).longValue());
                    return;
                case 5:
                    zzlq.zzj(zzd.number, ((Integer) entry.getValue()).intValue());
                    return;
                case 6:
                    zzlq.zzc(zzd.number, ((Long) entry.getValue()).longValue());
                    return;
                case 7:
                    zzlq.zzm(zzd.number, ((Integer) entry.getValue()).intValue());
                    return;
                case 8:
                    zzlq.zza(zzd.number, ((Boolean) entry.getValue()).booleanValue());
                    return;
                case 9:
                    zzlq.zzk(zzd.number, ((Integer) entry.getValue()).intValue());
                    return;
                case 10:
                    zzlq.zzt(zzd.number, ((Integer) entry.getValue()).intValue());
                    return;
                case 11:
                    zzlq.zzj(zzd.number, ((Long) entry.getValue()).longValue());
                    return;
                case 12:
                    zzlq.zzl(zzd.number, ((Integer) entry.getValue()).intValue());
                    return;
                case 13:
                    zzlq.zzb(zzd.number, ((Long) entry.getValue()).longValue());
                    return;
                case 14:
                    zzlq.zzj(zzd.number, ((Integer) entry.getValue()).intValue());
                    return;
                case 15:
                    zzlq.zza(zzd.number, (zzgs) entry.getValue());
                    return;
                case 16:
                    zzlq.zza(zzd.number, (String) entry.getValue());
                    return;
                case 17:
                    zzlq.zzb(zzd.number, (Object) entry.getValue(), (zzkf) zzkb.zzik().zzf(entry.getValue().getClass()));
                    return;
                case 18:
                    zzlq.zza(zzd.number, (Object) entry.getValue(), (zzkf) zzkb.zzik().zzf(entry.getValue().getClass()));
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Object zza(zzho zzho, zzjn zzjn, int i) {
        return zzho.zza(zzjn, i);
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzkc zzkc, Object obj, zzho zzho, zzht<zzid.zzd> zzht) throws IOException {
        zzid.zzg zzg = (zzid.zzg) obj;
        zzht.zza(zzg.zzyr, (Object) zzkc.zza(zzg.zzyq.getClass(), zzho));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgs zzgs, Object obj, zzho zzho, zzht<zzid.zzd> zzht) throws IOException {
        byte[] bArr;
        zzid.zzg zzg = (zzid.zzg) obj;
        zzjn zzgv = zzg.zzyq.zzhd().zzgv();
        int size = zzgs.size();
        if (size == 0) {
            bArr = zzie.zzys;
        } else {
            byte[] bArr2 = new byte[size];
            zzgs.zza(bArr2, 0, 0, size);
            bArr = bArr2;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (wrap.hasArray()) {
            zzgr zzgr = new zzgr(wrap, true);
            zzkb.zzik().zzx(zzgv).zza(zzgv, zzgr, zzho);
            zzht.zza(zzg.zzyr, (Object) zzgv);
            if (zzgr.zzeo() != Integer.MAX_VALUE) {
                throw zzin.zzhl();
            }
            return;
        }
        throw new IllegalArgumentException("Direct buffers not yet supported");
    }
}

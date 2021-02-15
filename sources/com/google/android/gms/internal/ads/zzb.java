package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
public final class zzb {
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0039 A[Catch:{ all -> 0x0292 }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0268 A[Catch:{ all -> 0x0292 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.security.cert.X509Certificate[][] zza(java.lang.String r18) throws com.google.android.gms.internal.ads.zzh, java.lang.SecurityException, java.io.IOException {
        /*
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile
            java.lang.String r0 = "r"
            r2 = r18
            r1.<init>(r2, r0)
            android.util.Pair r0 = com.google.android.gms.internal.ads.zzi.zza(r1)     // Catch:{ all -> 0x0292 }
            if (r0 == 0) goto L_0x0270
            java.lang.Object r2 = r0.first     // Catch:{ all -> 0x0292 }
            r11 = r2
            java.nio.ByteBuffer r11 = (java.nio.ByteBuffer) r11     // Catch:{ all -> 0x0292 }
            java.lang.Object r0 = r0.second     // Catch:{ all -> 0x0292 }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x0292 }
            long r9 = r0.longValue()     // Catch:{ all -> 0x0292 }
            r2 = 20
            long r2 = r9 - r2
            r0 = 1
            r4 = 0
            r6 = 0
            int r7 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r7 < 0) goto L_0x0036
            r1.seek(r2)     // Catch:{ all -> 0x0292 }
            int r2 = r1.readInt()     // Catch:{ all -> 0x0292 }
            r3 = 1347094023(0x504b0607, float:1.36246794E10)
            if (r2 != r3) goto L_0x0036
            r2 = 1
            goto L_0x0037
        L_0x0036:
            r2 = 0
        L_0x0037:
            if (r2 != 0) goto L_0x0268
            long r7 = com.google.android.gms.internal.ads.zzi.zzc(r11)     // Catch:{ all -> 0x0292 }
            int r2 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x0247
            long r2 = com.google.android.gms.internal.ads.zzi.zzd(r11)     // Catch:{ all -> 0x0292 }
            long r2 = r2 + r7
            int r12 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r12 != 0) goto L_0x023f
            r2 = 32
            int r12 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r12 < 0) goto L_0x0226
            r2 = 24
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r2)     // Catch:{ all -> 0x0292 }
            java.nio.ByteOrder r12 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x0292 }
            r3.order(r12)     // Catch:{ all -> 0x0292 }
            int r12 = r3.capacity()     // Catch:{ all -> 0x0292 }
            long r12 = (long) r12     // Catch:{ all -> 0x0292 }
            long r12 = r7 - r12
            r1.seek(r12)     // Catch:{ all -> 0x0292 }
            byte[] r12 = r3.array()     // Catch:{ all -> 0x0292 }
            int r13 = r3.arrayOffset()     // Catch:{ all -> 0x0292 }
            int r14 = r3.capacity()     // Catch:{ all -> 0x0292 }
            r1.readFully(r12, r13, r14)     // Catch:{ all -> 0x0292 }
            r12 = 8
            long r13 = r3.getLong(r12)     // Catch:{ all -> 0x0292 }
            r15 = 2334950737559900225(0x20676953204b5041, double:1.3968830566012645E-152)
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 != 0) goto L_0x021e
            r13 = 16
            long r13 = r3.getLong(r13)     // Catch:{ all -> 0x0292 }
            r15 = 3617552046287187010(0x3234206b636f6c42, double:7.465385175170059E-67)
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 != 0) goto L_0x021e
            long r13 = r3.getLong(r6)     // Catch:{ all -> 0x0292 }
            int r3 = r3.capacity()     // Catch:{ all -> 0x0292 }
            long r2 = (long) r3     // Catch:{ all -> 0x0292 }
            int r15 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r15 < 0) goto L_0x0204
            r2 = 2147483639(0x7ffffff7, double:1.060997891E-314)
            int r15 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r15 > 0) goto L_0x0204
            r2 = 8
            long r2 = r2 + r13
            int r3 = (int) r2     // Catch:{ all -> 0x0292 }
            r15 = r13
            long r12 = (long) r3     // Catch:{ all -> 0x0292 }
            long r12 = r7 - r12
            int r14 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r14 < 0) goto L_0x01eb
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r3)     // Catch:{ all -> 0x0292 }
            java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x0292 }
            r3.order(r4)     // Catch:{ all -> 0x0292 }
            r1.seek(r12)     // Catch:{ all -> 0x0292 }
            byte[] r4 = r3.array()     // Catch:{ all -> 0x0292 }
            int r5 = r3.arrayOffset()     // Catch:{ all -> 0x0292 }
            int r14 = r3.capacity()     // Catch:{ all -> 0x0292 }
            r1.readFully(r4, r5, r14)     // Catch:{ all -> 0x0292 }
            long r4 = r3.getLong(r6)     // Catch:{ all -> 0x0292 }
            int r14 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r14 != 0) goto L_0x01c9
            java.lang.Long r4 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0292 }
            android.util.Pair r3 = android.util.Pair.create(r3, r4)     // Catch:{ all -> 0x0292 }
            java.lang.Object r4 = r3.first     // Catch:{ all -> 0x0292 }
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4     // Catch:{ all -> 0x0292 }
            java.lang.Object r3 = r3.second     // Catch:{ all -> 0x0292 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0292 }
            long r12 = r3.longValue()     // Catch:{ all -> 0x0292 }
            java.nio.ByteOrder r3 = r4.order()     // Catch:{ all -> 0x0292 }
            java.nio.ByteOrder r5 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x0292 }
            if (r3 != r5) goto L_0x01c1
            int r3 = r4.capacity()     // Catch:{ all -> 0x0292 }
            r5 = 24
            int r3 = r3 - r5
            r2 = 8
            java.nio.ByteBuffer r3 = zza((java.nio.ByteBuffer) r4, (int) r2, (int) r3)     // Catch:{ all -> 0x0292 }
        L_0x00fe:
            boolean r4 = r3.hasRemaining()     // Catch:{ all -> 0x0292 }
            if (r4 == 0) goto L_0x01b9
            int r6 = r6 + r0
            int r4 = r3.remaining()     // Catch:{ all -> 0x0292 }
            if (r4 < r2) goto L_0x01a0
            long r4 = r3.getLong()     // Catch:{ all -> 0x0292 }
            r14 = 4
            java.lang.String r0 = " size out of range: "
            java.lang.String r2 = "APK Signing Block entry #"
            int r17 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r17 < 0) goto L_0x0183
            r14 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r17 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r17 > 0) goto L_0x0183
            int r5 = (int) r4
            int r4 = r3.position()     // Catch:{ all -> 0x0292 }
            int r4 = r4 + r5
            int r14 = r3.remaining()     // Catch:{ all -> 0x0292 }
            if (r5 > r14) goto L_0x015a
            int r0 = r3.getInt()     // Catch:{ all -> 0x0292 }
            r2 = 1896449818(0x7109871a, float:6.810044E29)
            if (r0 != r2) goto L_0x0153
            int r5 = r5 + -4
            java.nio.ByteBuffer r4 = zza((java.nio.ByteBuffer) r3, (int) r5)     // Catch:{ all -> 0x0292 }
            com.google.android.gms.internal.ads.zze r0 = new com.google.android.gms.internal.ads.zze     // Catch:{ all -> 0x0292 }
            r2 = 0
            r3 = r0
            r5 = r12
            r12 = r2
            r3.<init>(r4, r5, r7, r9, r11)     // Catch:{ all -> 0x0292 }
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch:{ all -> 0x0292 }
            java.security.cert.X509Certificate[][] r0 = zza((java.nio.channels.FileChannel) r2, (com.google.android.gms.internal.ads.zze) r0)     // Catch:{ all -> 0x0292 }
            r1.close()     // Catch:{ all -> 0x0292 }
            r1.close()     // Catch:{ IOException -> 0x0152 }
        L_0x0152:
            return r0
        L_0x0153:
            r3.position(r4)     // Catch:{ all -> 0x0292 }
            r0 = 1
            r2 = 8
            goto L_0x00fe
        L_0x015a:
            com.google.android.gms.internal.ads.zzh r4 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            int r3 = r3.remaining()     // Catch:{ all -> 0x0292 }
            r7 = 91
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r8.<init>(r7)     // Catch:{ all -> 0x0292 }
            r8.append(r2)     // Catch:{ all -> 0x0292 }
            r8.append(r6)     // Catch:{ all -> 0x0292 }
            r8.append(r0)     // Catch:{ all -> 0x0292 }
            r8.append(r5)     // Catch:{ all -> 0x0292 }
            java.lang.String r0 = ", available: "
            r8.append(r0)     // Catch:{ all -> 0x0292 }
            r8.append(r3)     // Catch:{ all -> 0x0292 }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x0292 }
            r4.<init>(r0)     // Catch:{ all -> 0x0292 }
            throw r4     // Catch:{ all -> 0x0292 }
        L_0x0183:
            com.google.android.gms.internal.ads.zzh r3 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            r7 = 76
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r8.<init>(r7)     // Catch:{ all -> 0x0292 }
            r8.append(r2)     // Catch:{ all -> 0x0292 }
            r8.append(r6)     // Catch:{ all -> 0x0292 }
            r8.append(r0)     // Catch:{ all -> 0x0292 }
            r8.append(r4)     // Catch:{ all -> 0x0292 }
            java.lang.String r0 = r8.toString()     // Catch:{ all -> 0x0292 }
            r3.<init>(r0)     // Catch:{ all -> 0x0292 }
            throw r3     // Catch:{ all -> 0x0292 }
        L_0x01a0:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            r2 = 70
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r3.<init>(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "Insufficient data to read size of APK Signing Block entry #"
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r3.append(r6)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x01b9:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "No APK Signature Scheme v2 block in APK Signing Block"
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x01c1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "ByteBuffer byte order must be little endian"
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x01c9:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            r2 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r3.<init>(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "APK Signing Block sizes in header and footer do not match: "
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r3.append(r4)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = " vs "
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r4 = r15
            r3.append(r4)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x01eb:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            r2 = 59
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r3.<init>(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "APK Signing Block offset out of range: "
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r3.append(r12)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0204:
            r4 = r13
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            r2 = 57
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r3.<init>(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "APK Signing Block size out of range: "
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r3.append(r4)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x021e:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "No APK Signing Block before ZIP Central Directory"
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0226:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            r2 = 87
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r3.<init>(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "APK too small for APK Signing Block. ZIP Central Directory offset: "
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r3.append(r7)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x023f:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "ZIP Central Directory is not immediately followed by End of Central Directory"
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0247:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            r2 = 122(0x7a, float:1.71E-43)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r3.<init>(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "ZIP Central Directory offset out of range: "
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r3.append(r7)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = ". ZIP End of Central Directory offset: "
            r3.append(r2)     // Catch:{ all -> 0x0292 }
            r3.append(r9)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0268:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = "ZIP64 APK not supported"
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0270:
            com.google.android.gms.internal.ads.zzh r0 = new com.google.android.gms.internal.ads.zzh     // Catch:{ all -> 0x0292 }
            long r2 = r1.length()     // Catch:{ all -> 0x0292 }
            r4 = 102(0x66, float:1.43E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0292 }
            r5.<init>(r4)     // Catch:{ all -> 0x0292 }
            java.lang.String r4 = "Not an APK file: ZIP End of Central Directory record not found in file with "
            r5.append(r4)     // Catch:{ all -> 0x0292 }
            r5.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = " bytes"
            r5.append(r2)     // Catch:{ all -> 0x0292 }
            java.lang.String r2 = r5.toString()     // Catch:{ all -> 0x0292 }
            r0.<init>(r2)     // Catch:{ all -> 0x0292 }
            throw r0     // Catch:{ all -> 0x0292 }
        L_0x0292:
            r0 = move-exception
            r1.close()     // Catch:{ IOException -> 0x0296 }
        L_0x0296:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzb.zza(java.lang.String):java.security.cert.X509Certificate[][]");
    }

    private static X509Certificate[][] zza(FileChannel fileChannel, zze zze) throws SecurityException {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            try {
                ByteBuffer zza = zza(zze.zzb);
                int i = 0;
                int i2 = 0;
                while (zza.hasRemaining()) {
                    i2++;
                    try {
                        arrayList.add(zza(zza(zza), (Map<Integer, byte[]>) hashMap, instance));
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Failed to parse/verify signer #");
                        sb.append(i2);
                        sb.append(" block");
                        throw new SecurityException(sb.toString(), e);
                    }
                }
                if (i2 <= 0) {
                    throw new SecurityException("No signers found");
                } else if (!hashMap.isEmpty()) {
                    long zzb = zze.zzc;
                    long zzc = zze.zzd;
                    long zzd = zze.zze;
                    ByteBuffer zze2 = zze.zzf;
                    if (!hashMap.isEmpty()) {
                        FileChannel fileChannel2 = fileChannel;
                        zzf zzf = new zzf(fileChannel2, 0, zzb);
                        zzf zzf2 = new zzf(fileChannel2, zzc, zzd - zzc);
                        ByteBuffer duplicate = zze2.duplicate();
                        duplicate.order(ByteOrder.LITTLE_ENDIAN);
                        zzi.zza(duplicate, zzb);
                        zzd zzd2 = new zzd(duplicate);
                        int size = hashMap.size();
                        int[] iArr = new int[size];
                        int i3 = 0;
                        for (Integer intValue : hashMap.keySet()) {
                            iArr[i3] = intValue.intValue();
                            i3++;
                        }
                        try {
                            byte[][] zza2 = zza(iArr, new zzc[]{zzf, zzf2, zzd2});
                            while (i < size) {
                                int i4 = iArr[i];
                                if (MessageDigest.isEqual((byte[]) hashMap.get(Integer.valueOf(i4)), zza2[i])) {
                                    i++;
                                } else {
                                    throw new SecurityException(String.valueOf(zzb(i4)).concat(" digest of contents did not verify"));
                                }
                            }
                            return (X509Certificate[][]) arrayList.toArray(new X509Certificate[arrayList.size()][]);
                        } catch (DigestException e2) {
                            throw new SecurityException("Failed to compute digest(s) of contents", e2);
                        }
                    } else {
                        throw new SecurityException("No digests provided");
                    }
                } else {
                    throw new SecurityException("No content digests found");
                }
            } catch (IOException e3) {
                throw new SecurityException("Failed to read list of signers", e3);
            }
        } catch (CertificateException e4) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e4);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:142:0x0015 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.security.cert.X509Certificate[] zza(java.nio.ByteBuffer r22, java.util.Map<java.lang.Integer, byte[]> r23, java.security.cert.CertificateFactory r24) throws java.lang.SecurityException, java.io.IOException {
        /*
            java.nio.ByteBuffer r0 = zza((java.nio.ByteBuffer) r22)
            java.nio.ByteBuffer r1 = zza((java.nio.ByteBuffer) r22)
            byte[] r2 = zzb((java.nio.ByteBuffer) r22)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r4 = -1
            r7 = -1
            r8 = 0
            r9 = 0
        L_0x0015:
            boolean r10 = r1.hasRemaining()
            r11 = 8
            r12 = 769(0x301, float:1.078E-42)
            r13 = 514(0x202, float:7.2E-43)
            r14 = 513(0x201, float:7.19E-43)
            r15 = 1
            if (r10 == 0) goto L_0x00d8
            int r8 = r8 + 1
            java.nio.ByteBuffer r10 = zza((java.nio.ByteBuffer) r1)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            int r6 = r10.remaining()     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            if (r6 < r11) goto L_0x00b4
            int r6 = r10.getInt()     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r6)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r3.add(r11)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            if (r6 == r14) goto L_0x0046
            if (r6 == r13) goto L_0x0046
            if (r6 == r12) goto L_0x0046
            switch(r6) {
                case 257: goto L_0x0046;
                case 258: goto L_0x0046;
                case 259: goto L_0x0046;
                case 260: goto L_0x0046;
                default: goto L_0x0044;
            }     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
        L_0x0044:
            r11 = 0
            goto L_0x0047
        L_0x0046:
            r11 = 1
        L_0x0047:
            if (r11 != 0) goto L_0x004a
            goto L_0x0015
        L_0x004a:
            if (r7 == r4) goto L_0x00ac
            int r11 = zza((int) r6)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            int r12 = zza((int) r7)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.String r13 = "Unknown digestAlgorithm2: "
            r14 = 37
            r5 = 2
            if (r11 == r15) goto L_0x008e
            if (r11 != r5) goto L_0x0077
            if (r12 == r15) goto L_0x00aa
            if (r12 != r5) goto L_0x0062
            goto L_0x00a9
        L_0x0062:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.<init>(r14)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.append(r13)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.append(r12)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r0.<init>(r1)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            throw r0     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
        L_0x0077:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.<init>(r14)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.String r2 = "Unknown digestAlgorithm1: "
            r1.append(r2)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.append(r11)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r0.<init>(r1)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            throw r0     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
        L_0x008e:
            if (r12 == r15) goto L_0x00a9
            if (r12 != r5) goto L_0x0094
            r15 = -1
            goto L_0x00aa
        L_0x0094:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.<init>(r14)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.append(r13)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r1.append(r12)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r0.<init>(r1)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            throw r0     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
        L_0x00a9:
            r15 = 0
        L_0x00aa:
            if (r15 <= 0) goto L_0x0015
        L_0x00ac:
            byte[] r5 = zzb((java.nio.ByteBuffer) r10)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            r9 = r5
            r7 = r6
            goto L_0x0015
        L_0x00b4:
            java.lang.SecurityException r0 = new java.lang.SecurityException     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            java.lang.String r1 = "Signature record too short"
            r0.<init>(r1)     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
            throw r0     // Catch:{ IOException -> 0x00be, BufferUnderflowException -> 0x00bc }
        L_0x00bc:
            r0 = move-exception
            goto L_0x00bf
        L_0x00be:
            r0 = move-exception
        L_0x00bf:
            java.lang.SecurityException r1 = new java.lang.SecurityException
            r2 = 45
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Failed to parse signature record #"
            r3.append(r2)
            r3.append(r8)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x00d8:
            if (r7 != r4) goto L_0x00ec
            if (r8 != 0) goto L_0x00e4
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "No signatures found"
            r0.<init>(r1)
            throw r0
        L_0x00e4:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "No supported signatures found"
            r0.<init>(r1)
            throw r0
        L_0x00ec:
            java.lang.String r1 = "Unknown signature algorithm: 0x"
            if (r7 == r14) goto L_0x011d
            if (r7 == r13) goto L_0x011d
            if (r7 == r12) goto L_0x011a
            switch(r7) {
                case 257: goto L_0x0117;
                case 258: goto L_0x0117;
                case 259: goto L_0x0117;
                case 260: goto L_0x0117;
                default: goto L_0x00f7;
            }
        L_0x00f7:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            long r2 = (long) r7
            java.lang.String r2 = java.lang.Long.toHexString(r2)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x010d
            java.lang.String r1 = r1.concat(r2)
            goto L_0x0113
        L_0x010d:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r1 = r2
        L_0x0113:
            r0.<init>(r1)
            throw r0
        L_0x0117:
            java.lang.String r4 = "RSA"
            goto L_0x011f
        L_0x011a:
            java.lang.String r4 = "DSA"
            goto L_0x011f
        L_0x011d:
            java.lang.String r4 = "EC"
        L_0x011f:
            if (r7 == r14) goto L_0x0199
            if (r7 == r13) goto L_0x0191
            if (r7 == r12) goto L_0x0189
            switch(r7) {
                case 257: goto L_0x0170;
                case 258: goto L_0x0158;
                case 259: goto L_0x0150;
                case 260: goto L_0x0148;
                default: goto L_0x0128;
            }
        L_0x0128:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            long r2 = (long) r7
            java.lang.String r2 = java.lang.Long.toHexString(r2)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            int r3 = r2.length()
            if (r3 == 0) goto L_0x013e
            java.lang.String r1 = r1.concat(r2)
            goto L_0x0144
        L_0x013e:
            java.lang.String r2 = new java.lang.String
            r2.<init>(r1)
            r1 = r2
        L_0x0144:
            r0.<init>(r1)
            throw r0
        L_0x0148:
            java.lang.String r1 = "SHA512withRSA"
            r5 = 0
            android.util.Pair r1 = android.util.Pair.create(r1, r5)
            goto L_0x01a0
        L_0x0150:
            r5 = 0
            java.lang.String r1 = "SHA256withRSA"
            android.util.Pair r1 = android.util.Pair.create(r1, r5)
            goto L_0x01a0
        L_0x0158:
            java.security.spec.PSSParameterSpec r1 = new java.security.spec.PSSParameterSpec
            java.security.spec.MGF1ParameterSpec r19 = java.security.spec.MGF1ParameterSpec.SHA512
            r20 = 64
            r21 = 1
            java.lang.String r17 = "SHA-512"
            java.lang.String r18 = "MGF1"
            r16 = r1
            r16.<init>(r17, r18, r19, r20, r21)
            java.lang.String r5 = "SHA512withRSA/PSS"
            android.util.Pair r1 = android.util.Pair.create(r5, r1)
            goto L_0x0187
        L_0x0170:
            java.security.spec.PSSParameterSpec r1 = new java.security.spec.PSSParameterSpec
            java.security.spec.MGF1ParameterSpec r19 = java.security.spec.MGF1ParameterSpec.SHA256
            r20 = 32
            r21 = 1
            java.lang.String r17 = "SHA-256"
            java.lang.String r18 = "MGF1"
            r16 = r1
            r16.<init>(r17, r18, r19, r20, r21)
            java.lang.String r5 = "SHA256withRSA/PSS"
            android.util.Pair r1 = android.util.Pair.create(r5, r1)
        L_0x0187:
            r5 = 0
            goto L_0x01a0
        L_0x0189:
            java.lang.String r1 = "SHA256withDSA"
            r5 = 0
            android.util.Pair r1 = android.util.Pair.create(r1, r5)
            goto L_0x01a0
        L_0x0191:
            r5 = 0
            java.lang.String r1 = "SHA512withECDSA"
            android.util.Pair r1 = android.util.Pair.create(r1, r5)
            goto L_0x01a0
        L_0x0199:
            r5 = 0
            java.lang.String r1 = "SHA256withECDSA"
            android.util.Pair r1 = android.util.Pair.create(r1, r5)
        L_0x01a0:
            java.lang.Object r6 = r1.first
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r1 = r1.second
            java.security.spec.AlgorithmParameterSpec r1 = (java.security.spec.AlgorithmParameterSpec) r1
            java.security.KeyFactory r4 = java.security.KeyFactory.getInstance(r4)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            java.security.spec.X509EncodedKeySpec r8 = new java.security.spec.X509EncodedKeySpec     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            r8.<init>(r2)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            java.security.PublicKey r4 = r4.generatePublic(r8)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            java.security.Signature r8 = java.security.Signature.getInstance(r6)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            r8.initVerify(r4)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            if (r1 == 0) goto L_0x01c1
            r8.setParameter(r1)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
        L_0x01c1:
            r8.update(r0)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            boolean r1 = r8.verify(r9)     // Catch:{ NoSuchAlgorithmException -> 0x02ee, InvalidKeySpecException -> 0x02ec, InvalidKeyException -> 0x02ea, InvalidAlgorithmParameterException -> 0x02e8, SignatureException -> 0x02e6 }
            if (r1 == 0) goto L_0x02d6
            r0.clear()
            java.nio.ByteBuffer r1 = zza((java.nio.ByteBuffer) r0)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r6 = 0
        L_0x01d7:
            boolean r8 = r1.hasRemaining()
            if (r8 == 0) goto L_0x021e
            int r6 = r6 + r15
            java.nio.ByteBuffer r8 = zza((java.nio.ByteBuffer) r1)     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            int r9 = r8.remaining()     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            if (r9 < r11) goto L_0x01fa
            int r9 = r8.getInt()     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r9)     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            r4.add(r10)     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            if (r9 != r7) goto L_0x01d7
            byte[] r5 = zzb((java.nio.ByteBuffer) r8)     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            goto L_0x01d7
        L_0x01fa:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            java.lang.String r1 = "Record too short"
            r0.<init>(r1)     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
            throw r0     // Catch:{ IOException -> 0x0204, BufferUnderflowException -> 0x0202 }
        L_0x0202:
            r0 = move-exception
            goto L_0x0205
        L_0x0204:
            r0 = move-exception
        L_0x0205:
            java.io.IOException r1 = new java.io.IOException
            r2 = 42
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Failed to parse digest record #"
            r3.append(r2)
            r3.append(r6)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x021e:
            boolean r1 = r3.equals(r4)
            if (r1 == 0) goto L_0x02ce
            int r1 = zza((int) r7)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)
            r4 = r23
            java.lang.Object r3 = r4.put(r3, r5)
            byte[] r3 = (byte[]) r3
            if (r3 == 0) goto L_0x0251
            boolean r3 = java.security.MessageDigest.isEqual(r3, r5)
            if (r3 == 0) goto L_0x023d
            goto L_0x0251
        L_0x023d:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = zzb((int) r1)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            java.lang.String r2 = " contents digest does not match the digest specified by a preceding signer"
            java.lang.String r1 = r1.concat(r2)
            r0.<init>(r1)
            throw r0
        L_0x0251:
            java.nio.ByteBuffer r0 = zza((java.nio.ByteBuffer) r0)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = 0
        L_0x025b:
            boolean r4 = r0.hasRemaining()
            if (r4 == 0) goto L_0x0296
            int r3 = r3 + r15
            byte[] r4 = zzb((java.nio.ByteBuffer) r0)
            java.io.ByteArrayInputStream r5 = new java.io.ByteArrayInputStream     // Catch:{ CertificateException -> 0x027c }
            r5.<init>(r4)     // Catch:{ CertificateException -> 0x027c }
            r6 = r24
            java.security.cert.Certificate r5 = r6.generateCertificate(r5)     // Catch:{ CertificateException -> 0x027c }
            java.security.cert.X509Certificate r5 = (java.security.cert.X509Certificate) r5     // Catch:{ CertificateException -> 0x027c }
            com.google.android.gms.internal.ads.zzg r7 = new com.google.android.gms.internal.ads.zzg
            r7.<init>(r5, r4)
            r1.add(r7)
            goto L_0x025b
        L_0x027c:
            r0 = move-exception
            java.lang.SecurityException r1 = new java.lang.SecurityException
            r2 = 41
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r2)
            java.lang.String r2 = "Failed to decode certificate #"
            r4.append(r2)
            r4.append(r3)
            java.lang.String r2 = r4.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0296:
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x02c6
            r0 = 0
            java.lang.Object r0 = r1.get(r0)
            java.security.cert.X509Certificate r0 = (java.security.cert.X509Certificate) r0
            java.security.PublicKey r0 = r0.getPublicKey()
            byte[] r0 = r0.getEncoded()
            boolean r0 = java.util.Arrays.equals(r2, r0)
            if (r0 == 0) goto L_0x02be
            int r0 = r1.size()
            java.security.cert.X509Certificate[] r0 = new java.security.cert.X509Certificate[r0]
            java.lang.Object[] r0 = r1.toArray(r0)
            java.security.cert.X509Certificate[] r0 = (java.security.cert.X509Certificate[]) r0
            return r0
        L_0x02be:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "Public key mismatch between certificate and signature record"
            r0.<init>(r1)
            throw r0
        L_0x02c6:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "No certificates listed"
            r0.<init>(r1)
            throw r0
        L_0x02ce:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = "Signature algorithms don't match between digests and signatures records"
            r0.<init>(r1)
            throw r0
        L_0x02d6:
            java.lang.SecurityException r0 = new java.lang.SecurityException
            java.lang.String r1 = java.lang.String.valueOf(r6)
            java.lang.String r2 = " signature did not verify"
            java.lang.String r1 = r1.concat(r2)
            r0.<init>(r1)
            throw r0
        L_0x02e6:
            r0 = move-exception
            goto L_0x02ef
        L_0x02e8:
            r0 = move-exception
            goto L_0x02ef
        L_0x02ea:
            r0 = move-exception
            goto L_0x02ef
        L_0x02ec:
            r0 = move-exception
            goto L_0x02ef
        L_0x02ee:
            r0 = move-exception
        L_0x02ef:
            java.lang.SecurityException r1 = new java.lang.SecurityException
            java.lang.String r2 = java.lang.String.valueOf(r6)
            int r2 = r2.length()
            int r2 = r2 + 27
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Failed to verify "
            r3.append(r2)
            r3.append(r6)
            java.lang.String r2 = " signature"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzb.zza(java.nio.ByteBuffer, java.util.Map, java.security.cert.CertificateFactory):java.security.cert.X509Certificate[]");
    }

    private static byte[][] zza(int[] iArr, zzc[] zzcArr) throws DigestException {
        long j;
        int[] iArr2 = iArr;
        zzc[] zzcArr2 = zzcArr;
        int length = zzcArr2.length;
        long j2 = 0;
        int i = 0;
        long j3 = 0;
        int i2 = 0;
        while (true) {
            j = 1048576;
            if (i2 >= length) {
                break;
            }
            j3 += ((zzcArr2[i2].size() + 1048576) - 1) / 1048576;
            i2++;
        }
        if (j3 < 2097151) {
            int i3 = (int) j3;
            byte[][] bArr = new byte[iArr2.length][];
            for (int i4 = 0; i4 < iArr2.length; i4++) {
                byte[] bArr2 = new byte[((zzc(iArr2[i4]) * i3) + 5)];
                bArr2[0] = 90;
                zza(i3, bArr2, 1);
                bArr[i4] = bArr2;
            }
            byte[] bArr3 = new byte[5];
            bArr3[0] = -91;
            int length2 = iArr2.length;
            MessageDigest[] messageDigestArr = new MessageDigest[length2];
            int i5 = 0;
            while (i5 < iArr2.length) {
                String zzb = zzb(iArr2[i5]);
                try {
                    messageDigestArr[i5] = MessageDigest.getInstance(zzb);
                    i5++;
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(String.valueOf(zzb).concat(" digest not supported"), e);
                }
            }
            int length3 = zzcArr2.length;
            int i6 = 0;
            int i7 = 0;
            while (i6 < length3) {
                zzc zzc = zzcArr2[i6];
                long j4 = j2;
                int i8 = i7;
                int i9 = length3;
                long size = zzc.size();
                while (size > j2) {
                    int min = (int) Math.min(size, j);
                    zza(min, bArr3, 1);
                    for (int i10 = 0; i10 < length2; i10++) {
                        messageDigestArr[i10].update(bArr3);
                    }
                    long j5 = j4;
                    try {
                        zzc.zza(messageDigestArr, j5, min);
                        int i11 = 0;
                        while (i11 < iArr2.length) {
                            int i12 = iArr2[i11];
                            byte[] bArr4 = bArr3;
                            byte[] bArr5 = bArr[i11];
                            int zzc2 = zzc(i12);
                            int i13 = length2;
                            MessageDigest messageDigest = messageDigestArr[i11];
                            MessageDigest[] messageDigestArr2 = messageDigestArr;
                            int i14 = i8;
                            zzc zzc3 = zzc;
                            int digest = messageDigest.digest(bArr5, (i14 * zzc2) + 5, zzc2);
                            if (digest == zzc2) {
                                i11++;
                                i8 = i14;
                                bArr3 = bArr4;
                                length2 = i13;
                                messageDigestArr = messageDigestArr2;
                                zzc = zzc3;
                            } else {
                                String algorithm = messageDigest.getAlgorithm();
                                StringBuilder sb = new StringBuilder(String.valueOf(algorithm).length() + 46);
                                sb.append("Unexpected output size of ");
                                sb.append(algorithm);
                                sb.append(" digest: ");
                                sb.append(digest);
                                throw new RuntimeException(sb.toString());
                            }
                        }
                        int i15 = length2;
                        zzc zzc4 = zzc;
                        long j6 = (long) min;
                        long j7 = j5 + j6;
                        size -= j6;
                        i8++;
                        zzc[] zzcArr3 = zzcArr;
                        bArr3 = bArr3;
                        messageDigestArr = messageDigestArr;
                        j = 1048576;
                        j4 = j7;
                        j2 = 0;
                    } catch (IOException e2) {
                        StringBuilder sb2 = new StringBuilder(59);
                        sb2.append("Failed to digest chunk #");
                        sb2.append(i8);
                        sb2.append(" of section #");
                        sb2.append(i);
                        throw new DigestException(sb2.toString(), e2);
                    }
                }
                byte[] bArr6 = bArr3;
                int i16 = length2;
                i++;
                i6++;
                zzcArr2 = zzcArr;
                i7 = i8;
                length3 = i9;
                messageDigestArr = messageDigestArr;
                j2 = 0;
                j = 1048576;
            }
            byte[][] bArr7 = new byte[iArr2.length][];
            int i17 = 0;
            while (i17 < iArr2.length) {
                int i18 = iArr2[i17];
                byte[] bArr8 = bArr[i17];
                String zzb2 = zzb(i18);
                try {
                    bArr7[i17] = MessageDigest.getInstance(zzb2).digest(bArr8);
                    i17++;
                } catch (NoSuchAlgorithmException e3) {
                    throw new RuntimeException(String.valueOf(zzb2).concat(" digest not supported"), e3);
                }
            }
            return bArr7;
        }
        StringBuilder sb3 = new StringBuilder(37);
        sb3.append("Too many chunks: ");
        sb3.append(j3);
        throw new DigestException(sb3.toString());
    }

    private static int zza(int i) {
        if (i == 513) {
            return 1;
        }
        if (i == 514) {
            return 2;
        }
        if (i == 769) {
            return 1;
        }
        switch (i) {
            case 257:
            case 259:
                return 1;
            case 258:
            case 260:
                return 2;
            default:
                String valueOf = String.valueOf(Long.toHexString((long) i));
                throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown signature algorithm: 0x".concat(valueOf) : new String("Unknown signature algorithm: 0x"));
        }
    }

    private static String zzb(int i) {
        if (i == 1) {
            return "SHA-256";
        }
        if (i == 2) {
            return "SHA-512";
        }
        StringBuilder sb = new StringBuilder(44);
        sb.append("Unknown content digest algorthm: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    private static int zzc(int i) {
        if (i == 1) {
            return 32;
        }
        if (i == 2) {
            return 64;
        }
        StringBuilder sb = new StringBuilder(44);
        sb.append("Unknown content digest algorthm: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX INFO: finally extract failed */
    private static ByteBuffer zza(ByteBuffer byteBuffer, int i, int i2) {
        if (i2 >= 8) {
            int capacity = byteBuffer.capacity();
            if (i2 <= byteBuffer.capacity()) {
                int limit = byteBuffer.limit();
                int position = byteBuffer.position();
                try {
                    byteBuffer.position(0);
                    byteBuffer.limit(i2);
                    byteBuffer.position(8);
                    ByteBuffer slice = byteBuffer.slice();
                    slice.order(byteBuffer.order());
                    byteBuffer.position(0);
                    byteBuffer.limit(limit);
                    byteBuffer.position(position);
                    return slice;
                } catch (Throwable th) {
                    byteBuffer.position(0);
                    byteBuffer.limit(limit);
                    byteBuffer.position(position);
                    throw th;
                }
            } else {
                StringBuilder sb = new StringBuilder(41);
                sb.append("end > capacity: ");
                sb.append(i2);
                sb.append(" > ");
                sb.append(capacity);
                throw new IllegalArgumentException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder(38);
            sb2.append("end < start: ");
            sb2.append(i2);
            sb2.append(" < 8");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    private static ByteBuffer zza(ByteBuffer byteBuffer, int i) throws BufferUnderflowException {
        if (i >= 0) {
            int limit = byteBuffer.limit();
            int position = byteBuffer.position();
            int i2 = i + position;
            if (i2 < position || i2 > limit) {
                throw new BufferUnderflowException();
            }
            byteBuffer.limit(i2);
            try {
                ByteBuffer slice = byteBuffer.slice();
                slice.order(byteBuffer.order());
                byteBuffer.position(i2);
                return slice;
            } finally {
                byteBuffer.limit(limit);
            }
        } else {
            StringBuilder sb = new StringBuilder(17);
            sb.append("size: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private static ByteBuffer zza(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 4) {
            int i = byteBuffer.getInt();
            if (i < 0) {
                throw new IllegalArgumentException("Negative length");
            } else if (i <= byteBuffer.remaining()) {
                return zza(byteBuffer, i);
            } else {
                int remaining = byteBuffer.remaining();
                StringBuilder sb = new StringBuilder(101);
                sb.append("Length-prefixed field longer than remaining buffer. Field length: ");
                sb.append(i);
                sb.append(", remaining: ");
                sb.append(remaining);
                throw new IOException(sb.toString());
            }
        } else {
            int remaining2 = byteBuffer.remaining();
            StringBuilder sb2 = new StringBuilder(93);
            sb2.append("Remaining buffer too short to contain length of length-prefixed field. Remaining: ");
            sb2.append(remaining2);
            throw new IOException(sb2.toString());
        }
    }

    private static byte[] zzb(ByteBuffer byteBuffer) throws IOException {
        int i = byteBuffer.getInt();
        if (i < 0) {
            throw new IOException("Negative length");
        } else if (i <= byteBuffer.remaining()) {
            byte[] bArr = new byte[i];
            byteBuffer.get(bArr);
            return bArr;
        } else {
            int remaining = byteBuffer.remaining();
            StringBuilder sb = new StringBuilder(90);
            sb.append("Underflow while reading length-prefixed value. Length: ");
            sb.append(i);
            sb.append(", available: ");
            sb.append(remaining);
            throw new IOException(sb.toString());
        }
    }

    private static void zza(int i, byte[] bArr, int i2) {
        bArr[1] = (byte) i;
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) (i >>> 16);
        bArr[4] = (byte) (i >>> 24);
    }
}

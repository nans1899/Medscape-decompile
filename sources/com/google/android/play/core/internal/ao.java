package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.play.core.splitcompat.d;
import com.google.android.play.core.splitcompat.e;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

public final class ao {
    private final e a;
    private final Context b;
    private final d c;

    public ao(Context context, e eVar, d dVar, byte[] bArr) {
        this.a = eVar;
        this.c = dVar;
        this.b = context;
    }

    private static X509Certificate a(Signature signature) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        } catch (CertificateException e) {
            Log.e("SplitCompat", "Cannot decode certificate.", e);
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r5 = new java.lang.StringBuilder(java.lang.String.valueOf(r8).length() + 32);
        r5.append("Downloaded split ");
        r5.append(r8);
        r5.append(" is not signed.");
        r0 = r5.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a() {
        /*
            r14 = this;
            java.lang.String r0 = " is not signed."
            java.lang.String r1 = "Downloaded split "
            java.lang.String r2 = "SplitCompat"
            r3 = 0
            com.google.android.play.core.splitcompat.e r4 = r14.a     // Catch:{ IOException -> 0x0100 }
            java.io.File r4 = r4.c()     // Catch:{ IOException -> 0x0100 }
            r5 = 0
            android.content.Context r6 = r14.b     // Catch:{ NameNotFoundException -> 0x0023 }
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0023 }
            android.content.Context r7 = r14.b     // Catch:{ NameNotFoundException -> 0x0023 }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x0023 }
            r8 = 64
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r7, r8)     // Catch:{ NameNotFoundException -> 0x0023 }
            android.content.pm.Signature[] r6 = r6.signatures     // Catch:{ NameNotFoundException -> 0x0023 }
            goto L_0x0024
        L_0x0023:
            r6 = r5
        L_0x0024:
            if (r6 == 0) goto L_0x003e
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            int r7 = r6.length
            r8 = 0
        L_0x002d:
            if (r8 >= r7) goto L_0x003e
            r9 = r6[r8]
            java.security.cert.X509Certificate r9 = a((android.content.pm.Signature) r9)
            if (r9 != 0) goto L_0x0038
            goto L_0x003b
        L_0x0038:
            r5.add(r9)
        L_0x003b:
            int r8 = r8 + 1
            goto L_0x002d
        L_0x003e:
            if (r5 != 0) goto L_0x0042
            goto L_0x00fa
        L_0x0042:
            boolean r6 = r5.isEmpty()
            if (r6 != 0) goto L_0x00fa
            java.io.File[] r4 = r4.listFiles()
            java.util.Arrays.sort(r4)
            int r6 = r4.length
        L_0x0050:
            int r6 = r6 + -1
            if (r6 < 0) goto L_0x00f8
            r7 = r4[r6]
            java.lang.String r8 = r7.getAbsolutePath()     // Catch:{ Exception -> 0x00f1 }
            java.security.cert.X509Certificate[][] r9 = com.google.android.play.core.internal.h.a((java.lang.String) r8)     // Catch:{ Exception -> 0x00cb }
            if (r9 != 0) goto L_0x0061
            goto L_0x00ae
        L_0x0061:
            int r10 = r9.length     // Catch:{ Exception -> 0x00f1 }
            if (r10 == 0) goto L_0x00ae
            r10 = r9[r3]     // Catch:{ Exception -> 0x00f1 }
            int r10 = r10.length     // Catch:{ Exception -> 0x00f1 }
            if (r10 == 0) goto L_0x00ae
            boolean r8 = r5.isEmpty()     // Catch:{ Exception -> 0x00f1 }
            if (r8 == 0) goto L_0x0076
            java.lang.String r0 = "No certificates found for app."
        L_0x0071:
            android.util.Log.e(r2, r0)     // Catch:{ Exception -> 0x00f1 }
            goto L_0x00eb
        L_0x0076:
            java.util.Iterator r8 = r5.iterator()     // Catch:{ Exception -> 0x00f1 }
        L_0x007a:
            boolean r10 = r8.hasNext()     // Catch:{ Exception -> 0x00f1 }
            if (r10 == 0) goto L_0x009d
            java.lang.Object r10 = r8.next()     // Catch:{ Exception -> 0x00f1 }
            java.security.cert.X509Certificate r10 = (java.security.cert.X509Certificate) r10     // Catch:{ Exception -> 0x00f1 }
            int r11 = r9.length     // Catch:{ Exception -> 0x00f1 }
            r12 = 0
        L_0x0088:
            if (r12 < r11) goto L_0x0090
            java.lang.String r0 = "There's an app certificate that doesn't sign the split."
            android.util.Log.i(r2, r0)     // Catch:{ Exception -> 0x00f1 }
            goto L_0x00eb
        L_0x0090:
            r13 = r9[r12]     // Catch:{ Exception -> 0x00f1 }
            r13 = r13[r3]     // Catch:{ Exception -> 0x00f1 }
            boolean r13 = r13.equals(r10)     // Catch:{ Exception -> 0x00f1 }
            if (r13 != 0) goto L_0x007a
            int r12 = r12 + 1
            goto L_0x0088
        L_0x009d:
            com.google.android.play.core.splitcompat.e r8 = r14.a     // Catch:{ IOException -> 0x00a7 }
            java.io.File r8 = r8.a((java.io.File) r7)     // Catch:{ IOException -> 0x00a7 }
            r7.renameTo(r8)     // Catch:{ IOException -> 0x00a7 }
            goto L_0x0050
        L_0x00a7:
            r0 = move-exception
            java.lang.String r1 = "Cannot write verified split."
            android.util.Log.e(r2, r1, r0)
            return r3
        L_0x00ae:
            java.lang.String r4 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x00f1 }
            int r4 = r4.length()     // Catch:{ Exception -> 0x00f1 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f1 }
            int r4 = r4 + 32
            r5.<init>(r4)     // Catch:{ Exception -> 0x00f1 }
            r5.append(r1)     // Catch:{ Exception -> 0x00f1 }
            r5.append(r8)     // Catch:{ Exception -> 0x00f1 }
            r5.append(r0)     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r0 = r5.toString()     // Catch:{ Exception -> 0x00f1 }
            goto L_0x0071
        L_0x00cb:
            r4 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x00f1 }
            int r5 = r5.length()     // Catch:{ Exception -> 0x00f1 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f1 }
            int r5 = r5 + 32
            r6.<init>(r5)     // Catch:{ Exception -> 0x00f1 }
            r6.append(r1)     // Catch:{ Exception -> 0x00f1 }
            r6.append(r8)     // Catch:{ Exception -> 0x00f1 }
            r6.append(r0)     // Catch:{ Exception -> 0x00f1 }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x00f1 }
            android.util.Log.e(r2, r0, r4)     // Catch:{ Exception -> 0x00f1 }
        L_0x00eb:
            java.lang.String r0 = "Split verification failure."
            android.util.Log.e(r2, r0)     // Catch:{ Exception -> 0x00f1 }
            return r3
        L_0x00f1:
            r0 = move-exception
            java.lang.String r1 = "Split verification error."
            android.util.Log.e(r2, r1, r0)
            return r3
        L_0x00f8:
            r0 = 1
            return r0
        L_0x00fa:
            java.lang.String r0 = "No app certificates found."
            android.util.Log.e(r2, r0)
            return r3
        L_0x0100:
            r0 = move-exception
            java.lang.String r1 = "Cannot access directory for unverified splits."
            android.util.Log.e(r2, r1, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.ao.a():boolean");
    }

    public final boolean a(List<Intent> list) throws IOException {
        for (Intent stringExtra : list) {
            if (!this.a.b(stringExtra.getStringExtra("split_id")).exists()) {
                return false;
            }
        }
        return true;
    }
}

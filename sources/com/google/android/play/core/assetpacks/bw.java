package com.google.android.play.core.assetpacks;

import com.google.android.play.core.internal.aa;
import com.google.android.play.core.internal.ce;
import java.io.File;

final class bw {
    private static final aa a = new aa("ExtractChunkTaskHandler");
    private final byte[] b = new byte[8192];
    private final bb c;
    private final ce<w> d;
    private final ce<aw> e;
    private final cb f;

    bw(bb bbVar, ce<w> ceVar, ce<aw> ceVar2, cb cbVar) {
        this.c = bbVar;
        this.d = ceVar;
        this.e = ceVar2;
        this.f = cbVar;
    }

    private final File b(bv bvVar) {
        File a2 = this.c.a(bvVar.k, bvVar.a, bvVar.b, bvVar.c);
        if (!a2.exists()) {
            a2.mkdirs();
        }
        return a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x031c  */
    /* JADX WARNING: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0181 A[Catch:{ all -> 0x033c, all -> 0x0342, IOException -> 0x0348 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01b2 A[Catch:{ all -> 0x033c, all -> 0x0342, IOException -> 0x0348 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x01e6 A[Catch:{ all -> 0x033c, all -> 0x0342, IOException -> 0x0348 }] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02a1 A[SYNTHETIC, Splitter:B:96:0x02a1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.play.core.assetpacks.bv r23) {
        /*
            r22 = this;
            r1 = r22
            r2 = r23
            com.google.android.play.core.assetpacks.dr r0 = new com.google.android.play.core.assetpacks.dr
            com.google.android.play.core.assetpacks.bb r4 = r1.c
            java.lang.String r5 = r2.k
            int r6 = r2.a
            long r7 = r2.b
            java.lang.String r9 = r2.c
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r9)
            com.google.android.play.core.assetpacks.bb r10 = r1.c
            java.lang.String r11 = r2.k
            int r12 = r2.a
            long r13 = r2.b
            java.lang.String r15 = r2.c
            java.io.File r3 = r10.f(r11, r12, r13, r15)
            boolean r4 = r3.exists()
            if (r4 != 0) goto L_0x002b
            r3.mkdirs()
        L_0x002b:
            r11 = 3
            r12 = 2
            r13 = 1
            r14 = 0
            java.io.InputStream r3 = r2.i     // Catch:{ IOException -> 0x0348 }
            int r4 = r2.d     // Catch:{ IOException -> 0x0348 }
            if (r4 == r13) goto L_0x0037
            r15 = r3
            goto L_0x0040
        L_0x0037:
            java.util.zip.GZIPInputStream r4 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0348 }
            byte[] r5 = r1.b     // Catch:{ IOException -> 0x0348 }
            int r5 = r5.length     // Catch:{ IOException -> 0x0348 }
            r4.<init>(r3, r5)     // Catch:{ IOException -> 0x0348 }
            r15 = r4
        L_0x0040:
            int r3 = r2.e     // Catch:{ all -> 0x033c }
            r16 = 0
            if (r3 <= 0) goto L_0x017e
            com.google.android.play.core.assetpacks.dq r3 = r0.a()     // Catch:{ all -> 0x033c }
            int r4 = r3.e()     // Catch:{ all -> 0x033c }
            int r5 = r2.e     // Catch:{ all -> 0x033c }
            int r6 = r5 + -1
            if (r4 != r6) goto L_0x015e
            int r4 = r3.a()     // Catch:{ all -> 0x033c }
            if (r4 == r13) goto L_0x00d9
            if (r4 == r12) goto L_0x009c
            if (r4 != r11) goto L_0x0082
            com.google.android.play.core.internal.aa r4 = a     // Catch:{ all -> 0x033c }
            java.lang.String r5 = "Resuming central directory from last chunk."
            java.lang.Object[] r6 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r4.a(r5, r6)     // Catch:{ all -> 0x033c }
            long r3 = r3.c()     // Catch:{ all -> 0x033c }
            r0.a((java.io.InputStream) r15, (long) r3)     // Catch:{ all -> 0x033c }
            boolean r3 = r23.a()     // Catch:{ all -> 0x033c }
            if (r3 == 0) goto L_0x0078
        L_0x0074:
            r4 = r16
            goto L_0x017f
        L_0x0078:
            com.google.android.play.core.assetpacks.by r0 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x033c }
            java.lang.String r3 = "Chunk has ended twice during central directory. This should not be possible with chunk sizes of 50MB."
            int r4 = r2.j     // Catch:{ all -> 0x033c }
            r0.<init>((java.lang.String) r3, (int) r4)     // Catch:{ all -> 0x033c }
            throw r0     // Catch:{ all -> 0x033c }
        L_0x0082:
            com.google.android.play.core.assetpacks.by r0 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x033c }
            java.lang.Object[] r4 = new java.lang.Object[r13]     // Catch:{ all -> 0x033c }
            int r3 = r3.a()     // Catch:{ all -> 0x033c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x033c }
            r4[r14] = r3     // Catch:{ all -> 0x033c }
            java.lang.String r3 = "Slice checkpoint file corrupt. Unexpected FileExtractionStatus %s."
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x033c }
            int r4 = r2.j     // Catch:{ all -> 0x033c }
            r0.<init>((java.lang.String) r3, (int) r4)     // Catch:{ all -> 0x033c }
            throw r0     // Catch:{ all -> 0x033c }
        L_0x009c:
            com.google.android.play.core.internal.aa r3 = a     // Catch:{ all -> 0x033c }
            java.lang.String r4 = "Resuming zip entry from last chunk during local file header."
            java.lang.Object[] r5 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r3.a(r4, r5)     // Catch:{ all -> 0x033c }
            com.google.android.play.core.assetpacks.bb r3 = r1.c     // Catch:{ all -> 0x033c }
            java.lang.String r4 = r2.k     // Catch:{ all -> 0x033c }
            int r5 = r2.a     // Catch:{ all -> 0x033c }
            long r6 = r2.b     // Catch:{ all -> 0x033c }
            java.lang.String r8 = r2.c     // Catch:{ all -> 0x033c }
            r16 = r3
            r17 = r4
            r18 = r5
            r19 = r6
            r21 = r8
            java.io.File r3 = r16.d(r17, r18, r19, r21)     // Catch:{ all -> 0x033c }
            boolean r4 = r3.exists()     // Catch:{ all -> 0x033c }
            if (r4 == 0) goto L_0x00cf
            java.io.SequenceInputStream r4 = new java.io.SequenceInputStream     // Catch:{ all -> 0x033c }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ all -> 0x033c }
            r5.<init>(r3)     // Catch:{ all -> 0x033c }
            r4.<init>(r5, r15)     // Catch:{ all -> 0x033c }
            goto L_0x017f
        L_0x00cf:
            com.google.android.play.core.assetpacks.by r0 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x033c }
            java.lang.String r3 = "Checkpoint extension file not found."
            int r4 = r2.j     // Catch:{ all -> 0x033c }
            r0.<init>((java.lang.String) r3, (int) r4)     // Catch:{ all -> 0x033c }
            throw r0     // Catch:{ all -> 0x033c }
        L_0x00d9:
            com.google.android.play.core.internal.aa r4 = a     // Catch:{ all -> 0x033c }
            java.lang.Object[] r5 = new java.lang.Object[r13]     // Catch:{ all -> 0x033c }
            java.lang.String r6 = r3.b()     // Catch:{ all -> 0x033c }
            r5[r14] = r6     // Catch:{ all -> 0x033c }
            java.lang.String r6 = "Resuming zip entry from last chunk during file %s."
            r4.a(r6, r5)     // Catch:{ all -> 0x033c }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x033c }
            java.lang.String r5 = r3.b()     // Catch:{ all -> 0x033c }
            r4.<init>(r5)     // Catch:{ all -> 0x033c }
            boolean r5 = r4.exists()     // Catch:{ all -> 0x033c }
            if (r5 == 0) goto L_0x0154
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch:{ all -> 0x033c }
            java.lang.String r6 = "rw"
            r5.<init>(r4, r6)     // Catch:{ all -> 0x033c }
            long r6 = r3.c()     // Catch:{ all -> 0x033c }
            r5.seek(r6)     // Catch:{ all -> 0x033c }
            long r6 = r3.d()     // Catch:{ all -> 0x033c }
        L_0x0109:
            byte[] r3 = r1.b     // Catch:{ all -> 0x033c }
            int r3 = r3.length     // Catch:{ all -> 0x033c }
            long r8 = (long) r3     // Catch:{ all -> 0x033c }
            long r8 = java.lang.Math.min(r6, r8)     // Catch:{ all -> 0x033c }
            int r3 = (int) r8     // Catch:{ all -> 0x033c }
            byte[] r8 = r1.b     // Catch:{ all -> 0x033c }
            int r8 = r15.read(r8, r14, r3)     // Catch:{ all -> 0x033c }
            int r8 = java.lang.Math.max(r8, r14)     // Catch:{ all -> 0x033c }
            if (r8 > 0) goto L_0x011f
            goto L_0x0124
        L_0x011f:
            byte[] r9 = r1.b     // Catch:{ all -> 0x033c }
            r5.write(r9, r14, r8)     // Catch:{ all -> 0x033c }
        L_0x0124:
            long r10 = (long) r8     // Catch:{ all -> 0x033c }
            long r9 = r6 - r10
            r6 = 0
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 > 0) goto L_0x012e
            goto L_0x0133
        L_0x012e:
            if (r8 <= 0) goto L_0x0133
            r6 = r9
            r11 = 3
            goto L_0x0109
        L_0x0133:
            long r6 = r5.length()     // Catch:{ all -> 0x033c }
            r5.close()     // Catch:{ all -> 0x033c }
            if (r8 == r3) goto L_0x017e
            com.google.android.play.core.internal.aa r3 = a     // Catch:{ all -> 0x033c }
            java.lang.String r5 = "Chunk has ended while resuming the previous chunks file content."
            java.lang.Object[] r8 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r3.a(r5, r8)     // Catch:{ all -> 0x033c }
            java.lang.String r4 = r4.getCanonicalPath()     // Catch:{ all -> 0x033c }
            int r11 = r2.e     // Catch:{ all -> 0x033c }
            r3 = r0
            r5 = r6
            r7 = r9
            r9 = r11
            r3.a(r4, r5, r7, r9)     // Catch:{ all -> 0x033c }
            goto L_0x0074
        L_0x0154:
            com.google.android.play.core.assetpacks.by r0 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x033c }
            java.lang.String r3 = "Partial file specified in checkpoint does not exist. Corrupt directory."
            int r4 = r2.j     // Catch:{ all -> 0x033c }
            r0.<init>((java.lang.String) r3, (int) r4)     // Catch:{ all -> 0x033c }
            throw r0     // Catch:{ all -> 0x033c }
        L_0x015e:
            com.google.android.play.core.assetpacks.by r0 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x033c }
            java.lang.Object[] r4 = new java.lang.Object[r12]     // Catch:{ all -> 0x033c }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x033c }
            r4[r14] = r5     // Catch:{ all -> 0x033c }
            int r3 = r3.e()     // Catch:{ all -> 0x033c }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x033c }
            r4[r13] = r3     // Catch:{ all -> 0x033c }
            java.lang.String r3 = "Trying to resume with chunk number %s when previously processed chunk was number %s."
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x033c }
            int r4 = r2.j     // Catch:{ all -> 0x033c }
            r0.<init>((java.lang.String) r3, (int) r4)     // Catch:{ all -> 0x033c }
            throw r0     // Catch:{ all -> 0x033c }
        L_0x017e:
            r4 = r15
        L_0x017f:
            if (r4 == 0) goto L_0x0298
            com.google.android.play.core.assetpacks.bp r3 = new com.google.android.play.core.assetpacks.bp     // Catch:{ all -> 0x033c }
            r3.<init>(r4)     // Catch:{ all -> 0x033c }
            java.io.File r5 = r22.b(r23)     // Catch:{ all -> 0x033c }
        L_0x018a:
            com.google.android.play.core.assetpacks.dx r6 = r3.a()     // Catch:{ all -> 0x033c }
            java.lang.String r7 = r6.a()     // Catch:{ all -> 0x033c }
            if (r7 == 0) goto L_0x01a0
            java.lang.String r7 = r6.a()     // Catch:{ all -> 0x033c }
            java.lang.String r8 = "/"
            boolean r7 = r7.endsWith(r8)     // Catch:{ all -> 0x033c }
            if (r7 != 0) goto L_0x01ed
        L_0x01a0:
            boolean r7 = r6.d()     // Catch:{ all -> 0x033c }
            if (r7 != 0) goto L_0x01ed
            boolean r7 = r3.c()     // Catch:{ all -> 0x033c }
            if (r7 != 0) goto L_0x01ed
            int r7 = r6.c()     // Catch:{ all -> 0x033c }
            if (r7 != 0) goto L_0x01e6
            byte[] r7 = r6.e()     // Catch:{ all -> 0x033c }
            r0.a((byte[]) r7)     // Catch:{ all -> 0x033c }
            java.io.File r7 = new java.io.File     // Catch:{ all -> 0x033c }
            java.lang.String r8 = r6.a()     // Catch:{ all -> 0x033c }
            r7.<init>(r5, r8)     // Catch:{ all -> 0x033c }
            java.io.File r8 = r7.getParentFile()     // Catch:{ all -> 0x033c }
            r8.mkdirs()     // Catch:{ all -> 0x033c }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x033c }
            r8.<init>(r7)     // Catch:{ all -> 0x033c }
            byte[] r7 = r1.b     // Catch:{ all -> 0x033c }
            int r7 = r3.read(r7)     // Catch:{ all -> 0x033c }
        L_0x01d4:
            if (r7 <= 0) goto L_0x01e2
            byte[] r9 = r1.b     // Catch:{ all -> 0x033c }
            r8.write(r9, r14, r7)     // Catch:{ all -> 0x033c }
            byte[] r7 = r1.b     // Catch:{ all -> 0x033c }
            int r7 = r3.read(r7)     // Catch:{ all -> 0x033c }
            goto L_0x01d4
        L_0x01e2:
            r8.close()     // Catch:{ all -> 0x033c }
            goto L_0x01ed
        L_0x01e6:
            byte[] r7 = r6.e()     // Catch:{ all -> 0x033c }
            r0.a((byte[]) r7, (java.io.InputStream) r3)     // Catch:{ all -> 0x033c }
        L_0x01ed:
            boolean r7 = r3.b()     // Catch:{ all -> 0x033c }
            if (r7 != 0) goto L_0x01f9
            boolean r7 = r3.c()     // Catch:{ all -> 0x033c }
            if (r7 == 0) goto L_0x018a
        L_0x01f9:
            boolean r5 = r3.c()     // Catch:{ all -> 0x033c }
            if (r5 == 0) goto L_0x020f
            com.google.android.play.core.internal.aa r5 = a     // Catch:{ all -> 0x033c }
            java.lang.String r7 = "Writing central directory metadata."
            java.lang.Object[] r8 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r5.a(r7, r8)     // Catch:{ all -> 0x033c }
            byte[] r5 = r6.e()     // Catch:{ all -> 0x033c }
            r0.a((byte[]) r5, (java.io.InputStream) r4)     // Catch:{ all -> 0x033c }
        L_0x020f:
            boolean r4 = r23.a()     // Catch:{ all -> 0x033c }
            if (r4 != 0) goto L_0x0298
            boolean r4 = r6.d()     // Catch:{ all -> 0x033c }
            if (r4 == 0) goto L_0x022e
            com.google.android.play.core.internal.aa r3 = a     // Catch:{ all -> 0x033c }
            java.lang.String r4 = "Writing slice checkpoint for partial local file header."
            java.lang.Object[] r5 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r3.a(r4, r5)     // Catch:{ all -> 0x033c }
            byte[] r3 = r6.e()     // Catch:{ all -> 0x033c }
            int r4 = r2.e     // Catch:{ all -> 0x033c }
            r0.a((byte[]) r3, (int) r4)     // Catch:{ all -> 0x033c }
            goto L_0x0298
        L_0x022e:
            boolean r4 = r3.c()     // Catch:{ all -> 0x033c }
            if (r4 == 0) goto L_0x0243
            com.google.android.play.core.internal.aa r3 = a     // Catch:{ all -> 0x033c }
            java.lang.String r4 = "Writing slice checkpoint for central directory."
            java.lang.Object[] r5 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r3.a(r4, r5)     // Catch:{ all -> 0x033c }
            int r3 = r2.e     // Catch:{ all -> 0x033c }
            r0.a((int) r3)     // Catch:{ all -> 0x033c }
            goto L_0x0298
        L_0x0243:
            int r4 = r6.c()     // Catch:{ all -> 0x033c }
            if (r4 != 0) goto L_0x0279
            com.google.android.play.core.internal.aa r4 = a     // Catch:{ all -> 0x033c }
            java.lang.String r5 = "Writing slice checkpoint for partial file."
            java.lang.Object[] r7 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r4.a(r5, r7)     // Catch:{ all -> 0x033c }
            java.io.File r4 = new java.io.File     // Catch:{ all -> 0x033c }
            java.io.File r5 = r22.b(r23)     // Catch:{ all -> 0x033c }
            java.lang.String r7 = r6.a()     // Catch:{ all -> 0x033c }
            r4.<init>(r5, r7)     // Catch:{ all -> 0x033c }
            long r5 = r6.b()     // Catch:{ all -> 0x033c }
            long r7 = r3.d()     // Catch:{ all -> 0x033c }
            long r5 = r5 - r7
            long r7 = r4.length()     // Catch:{ all -> 0x033c }
            int r9 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r9 != 0) goto L_0x0271
            goto L_0x028a
        L_0x0271:
            com.google.android.play.core.assetpacks.by r0 = new com.google.android.play.core.assetpacks.by     // Catch:{ all -> 0x033c }
            java.lang.String r3 = "Partial file is of unexpected size."
            r0.<init>(r3)     // Catch:{ all -> 0x033c }
            throw r0     // Catch:{ all -> 0x033c }
        L_0x0279:
            com.google.android.play.core.internal.aa r4 = a     // Catch:{ all -> 0x033c }
            java.lang.String r5 = "Writing slice checkpoint for partial unextractable file."
            java.lang.Object[] r6 = new java.lang.Object[r14]     // Catch:{ all -> 0x033c }
            r4.a(r5, r6)     // Catch:{ all -> 0x033c }
            java.io.File r4 = r0.b()     // Catch:{ all -> 0x033c }
            long r5 = r4.length()     // Catch:{ all -> 0x033c }
        L_0x028a:
            java.lang.String r4 = r4.getCanonicalPath()     // Catch:{ all -> 0x033c }
            long r7 = r3.d()     // Catch:{ all -> 0x033c }
            int r9 = r2.e     // Catch:{ all -> 0x033c }
            r3 = r0
            r3.a(r4, r5, r7, r9)     // Catch:{ all -> 0x033c }
        L_0x0298:
            r15.close()     // Catch:{ IOException -> 0x0348 }
            boolean r3 = r23.a()
            if (r3 == 0) goto L_0x02c1
            int r3 = r2.e     // Catch:{ IOException -> 0x02a7 }
            r0.b(r3)     // Catch:{ IOException -> 0x02a7 }
            goto L_0x02c1
        L_0x02a7:
            r0 = move-exception
            com.google.android.play.core.internal.aa r3 = a
            java.lang.Object[] r4 = new java.lang.Object[r13]
            java.lang.String r5 = r0.getMessage()
            r4[r14] = r5
            java.lang.String r5 = "Writing extraction finished checkpoint failed with %s."
            r3.b(r5, r4)
            com.google.android.play.core.assetpacks.by r3 = new com.google.android.play.core.assetpacks.by
            int r2 = r2.j
            java.lang.String r4 = "Writing extraction finished checkpoint failed."
            r3.<init>(r4, r0, r2)
            throw r3
        L_0x02c1:
            com.google.android.play.core.internal.aa r0 = a
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r4 = r2.e
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r14] = r4
            java.lang.String r4 = r2.c
            r3[r13] = r4
            java.lang.String r4 = r2.k
            r3[r12] = r4
            int r4 = r2.j
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r5 = 3
            r3[r5] = r4
            java.lang.String r4 = "Extraction finished for chunk %s of slice %s of pack %s of session %s."
            r0.c(r4, r3)
            com.google.android.play.core.internal.ce<com.google.android.play.core.assetpacks.w> r0 = r1.d
            java.lang.Object r0 = r0.a()
            com.google.android.play.core.assetpacks.w r0 = (com.google.android.play.core.assetpacks.w) r0
            int r3 = r2.j
            java.lang.String r4 = r2.k
            java.lang.String r5 = r2.c
            int r6 = r2.e
            r0.a(r3, r4, r5, r6)
            java.io.InputStream r0 = r2.i     // Catch:{ IOException -> 0x02fd }
            r0.close()     // Catch:{ IOException -> 0x02fd }
            goto L_0x0317
        L_0x02fd:
            com.google.android.play.core.internal.aa r0 = a
            r3 = 3
            java.lang.Object[] r4 = new java.lang.Object[r3]
            int r3 = r2.e
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4[r14] = r3
            java.lang.String r3 = r2.c
            r4[r13] = r3
            java.lang.String r3 = r2.k
            r4[r12] = r3
            java.lang.String r3 = "Could not close file for chunk %s of slice %s of pack %s."
            r0.d(r3, r4)
        L_0x0317:
            int r0 = r2.h
            r3 = 3
            if (r0 != r3) goto L_0x033b
            com.google.android.play.core.internal.ce<com.google.android.play.core.assetpacks.aw> r0 = r1.e
            java.lang.Object r0 = r0.a()
            com.google.android.play.core.assetpacks.aw r0 = (com.google.android.play.core.assetpacks.aw) r0
            java.lang.String r3 = r2.k
            long r7 = r2.g
            r4 = 3
            r5 = 0
            com.google.android.play.core.assetpacks.cb r6 = r1.f
            double r9 = r6.a(r3, r2)
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r7
            com.google.android.play.core.assetpacks.AssetPackState r2 = com.google.android.play.core.assetpacks.AssetPackState.a(r2, r3, r4, r5, r7, r9)
            r0.a((com.google.android.play.core.assetpacks.AssetPackState) r2)
        L_0x033b:
            return
        L_0x033c:
            r0 = move-exception
            r3 = r0
            r15.close()     // Catch:{ all -> 0x0342 }
            goto L_0x0347
        L_0x0342:
            r0 = move-exception
            r4 = r0
            com.google.android.play.core.internal.cd.a(r3, r4)     // Catch:{ IOException -> 0x0348 }
        L_0x0347:
            throw r3     // Catch:{ IOException -> 0x0348 }
        L_0x0348:
            r0 = move-exception
            com.google.android.play.core.internal.aa r3 = a
            java.lang.Object[] r4 = new java.lang.Object[r13]
            java.lang.String r5 = r0.getMessage()
            r4[r14] = r5
            java.lang.String r5 = "IOException during extraction %s."
            r3.b(r5, r4)
            com.google.android.play.core.assetpacks.by r3 = new com.google.android.play.core.assetpacks.by
            r4 = 4
            java.lang.Object[] r4 = new java.lang.Object[r4]
            int r5 = r2.e
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4[r14] = r5
            java.lang.String r5 = r2.c
            r4[r13] = r5
            java.lang.String r5 = r2.k
            r4[r12] = r5
            int r5 = r2.j
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6 = 3
            r4[r6] = r5
            java.lang.String r5 = "Error extracting chunk %s of slice %s of pack %s of session %s."
            java.lang.String r4 = java.lang.String.format(r5, r4)
            int r2 = r2.j
            r3.<init>(r4, r0, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.bw.a(com.google.android.play.core.assetpacks.bv):void");
    }
}

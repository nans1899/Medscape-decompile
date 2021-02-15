package com.medscape.android.helper;

public class ZipUtils {
    private static final String TAG = "ZIP";

    /* JADX WARNING: Removed duplicated region for block: B:40:0x008a A[SYNTHETIC, Splitter:B:40:0x008a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void unzipWOPassword(java.io.FileInputStream r6, java.lang.String r7, java.lang.String r8, com.medscape.android.updater.OnUpdateListener r9, int r10) throws java.lang.Exception {
        /*
            r8 = 0
            java.util.zip.ZipInputStream r10 = new java.util.zip.ZipInputStream     // Catch:{ IOException -> 0x0083, Exception -> 0x007e }
            r10.<init>(r6)     // Catch:{ IOException -> 0x0083, Exception -> 0x007e }
            if (r9 == 0) goto L_0x0018
            java.lang.String r6 = "Decompressing..."
            r9.setProgressMessage(r6)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            goto L_0x0018
        L_0x000e:
            r6 = move-exception
            r8 = r10
            goto L_0x0088
        L_0x0012:
            r6 = move-exception
            r8 = r10
            goto L_0x007f
        L_0x0015:
            r6 = move-exception
            r8 = r10
            goto L_0x0084
        L_0x0018:
            r6 = 0
            r8 = 0
        L_0x001a:
            java.util.zip.ZipEntry r0 = r10.getNextEntry()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            if (r0 != 0) goto L_0x0024
            r10.close()     // Catch:{ IOException -> 0x0023 }
        L_0x0023:
            return
        L_0x0024:
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r2.<init>()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r2.append(r7)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            java.lang.String r3 = "/"
            r2.append(r3)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            java.lang.String r3 = r0.getName()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r2.append(r3)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            boolean r2 = r0.isDirectory()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            if (r2 == 0) goto L_0x004b
            r1.mkdirs()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            goto L_0x001a
        L_0x004b:
            long r2 = (long) r8     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            long r4 = r0.getCompressedSize()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            long r2 = r2 + r4
            int r8 = (int) r2     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r2 = 4096(0x1000, float:5.74E-42)
            r1.<init>(r0, r2)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
        L_0x0060:
            int r3 = r10.read(r2)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            if (r3 <= 0) goto L_0x006a
            r1.write(r2, r6, r3)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            goto L_0x0060
        L_0x006a:
            r1.flush()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r1.close()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            if (r9 == 0) goto L_0x0075
            r9.onProgressUpdate(r8)     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
        L_0x0075:
            r0.close()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            r10.closeEntry()     // Catch:{ IOException -> 0x0015, Exception -> 0x0012, all -> 0x000e }
            goto L_0x001a
        L_0x007c:
            r6 = move-exception
            goto L_0x0088
        L_0x007e:
            r6 = move-exception
        L_0x007f:
            r6.printStackTrace()     // Catch:{ all -> 0x007c }
            throw r6     // Catch:{ all -> 0x007c }
        L_0x0083:
            r6 = move-exception
        L_0x0084:
            r6.printStackTrace()     // Catch:{ all -> 0x007c }
            throw r6     // Catch:{ all -> 0x007c }
        L_0x0088:
            if (r8 == 0) goto L_0x008d
            r8.close()     // Catch:{ IOException -> 0x008d }
        L_0x008d:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.helper.ZipUtils.unzipWOPassword(java.io.FileInputStream, java.lang.String, java.lang.String, com.medscape.android.updater.OnUpdateListener, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a8 A[SYNTHETIC, Splitter:B:40:0x00a8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void unzip11(java.io.FileInputStream r6, java.lang.String r7, java.lang.String r8, com.medscape.android.updater.OnUpdateListener r9, int r10) throws java.lang.Exception {
        /*
            java.lang.String r0 = "/"
            java.lang.String r1 = "zipstuff"
            r2 = 0
            com.medscape.android.helper.ZipDecryptInputStream r3 = new com.medscape.android.helper.ZipDecryptInputStream     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r3.<init>(r6, r8)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.util.zip.ZipInputStream r6 = new java.util.zip.ZipInputStream     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r6.<init>(r3)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            int r10 = r10 * 3
            if (r9 == 0) goto L_0x0025
            java.lang.String r8 = "Decompressing..."
            r9.setProgressMessage(r8)     // Catch:{ IOException -> 0x0021, Exception -> 0x001d, all -> 0x0019 }
            goto L_0x0025
        L_0x0019:
            r7 = move-exception
            r2 = r6
            goto L_0x00a6
        L_0x001d:
            r7 = move-exception
            r2 = r6
            goto L_0x009d
        L_0x0021:
            r7 = move-exception
            r2 = r6
            goto L_0x00a2
        L_0x0025:
            if (r9 == 0) goto L_0x002a
            r9.setMaxProgress(r10)     // Catch:{ IOException -> 0x0021, Exception -> 0x001d, all -> 0x0019 }
        L_0x002a:
            java.util.zip.ZipInputStream r2 = new java.util.zip.ZipInputStream     // Catch:{ IOException -> 0x0021, Exception -> 0x001d, all -> 0x0019 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0021, Exception -> 0x001d, all -> 0x0019 }
            r6 = 0
            r8 = 0
        L_0x0031:
            java.util.zip.ZipEntry r3 = r2.getNextEntry()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            if (r3 == 0) goto L_0x0096
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r4.<init>()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r4.append(r10)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.lang.String r5 = ""
            r4.append(r5)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            android.util.Log.d(r1, r4)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.<init>()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.append(r7)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.append(r0)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.lang.String r3 = r3.getName()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.append(r3)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.lang.String r3 = r5.toString()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r4.<init>(r3)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r3 = 4096(0x1000, float:5.74E-42)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
        L_0x006a:
            int r5 = r2.read(r3)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            if (r5 <= 0) goto L_0x008f
            r4.write(r3, r6, r5)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            int r8 = r8 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.<init>()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.append(r8)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.append(r0)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r5.append(r10)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            android.util.Log.d(r1, r5)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            if (r9 == 0) goto L_0x006a
            r9.onProgressUpdate(r8)     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            goto L_0x006a
        L_0x008f:
            r4.close()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            r2.closeEntry()     // Catch:{ IOException -> 0x00a1, Exception -> 0x009c }
            goto L_0x0031
        L_0x0096:
            r2.close()     // Catch:{ IOException -> 0x0099 }
        L_0x0099:
            return
        L_0x009a:
            r7 = move-exception
            goto L_0x00a6
        L_0x009c:
            r7 = move-exception
        L_0x009d:
            r7.printStackTrace()     // Catch:{ all -> 0x009a }
            throw r7     // Catch:{ all -> 0x009a }
        L_0x00a1:
            r7 = move-exception
        L_0x00a2:
            r7.printStackTrace()     // Catch:{ all -> 0x009a }
            throw r7     // Catch:{ all -> 0x009a }
        L_0x00a6:
            if (r2 == 0) goto L_0x00ab
            r2.close()     // Catch:{ IOException -> 0x00ab }
        L_0x00ab:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.helper.ZipUtils.unzip11(java.io.FileInputStream, java.lang.String, java.lang.String, com.medscape.android.updater.OnUpdateListener, int):void");
    }
}

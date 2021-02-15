package com.appboy.support;

import android.content.Context;
import java.io.File;

public class WebContentUtils {
    private static final String a = AppboyLogger.getAppboyLogTag(WebContentUtils.class);

    public static String getLocalHtmlUrlFromRemoteUrl(File file, String str) {
        if (file == null) {
            AppboyLogger.w(a, "Internal cache directory is null. No local URL will be created.");
            return null;
        } else if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.w(a, "Remote zip url is null or empty. No local URL will be created.");
            return null;
        } else {
            String absolutePath = file.getAbsolutePath();
            String valueOf = String.valueOf(IntentUtils.getRequestCode());
            String str2 = absolutePath + "/" + valueOf;
            AppboyLogger.d(a, "Starting download of url: " + str);
            File downloadFileToPath = AppboyFileUtils.downloadFileToPath(str2, str, valueOf, ".zip");
            if (downloadFileToPath == null) {
                AppboyLogger.d(a, "Could not download zip file to local storage.");
                AppboyFileUtils.deleteFileOrDirectory(new File(str2));
                return null;
            }
            AppboyLogger.d(a, "Html content zip downloaded.");
            if (!a(str2, downloadFileToPath)) {
                AppboyLogger.w(a, "Error during the zip unpack.");
                AppboyFileUtils.deleteFileOrDirectory(new File(str2));
                return null;
            }
            AppboyLogger.d(a, "Html content zip unpacked.");
            return str2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: java.io.BufferedOutputStream} */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.util.zip.ZipInputStream] */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.util.zip.ZipInputStream] */
    /* JADX WARNING: type inference failed for: r1v7, types: [java.util.zip.ZipInputStream] */
    /* JADX WARNING: type inference failed for: r1v8, types: [java.util.zip.ZipInputStream] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v22 */
    /* JADX WARNING: type inference failed for: r1v29 */
    /* JADX WARNING: type inference failed for: r1v30 */
    /* JADX WARNING: type inference failed for: r1v31 */
    /* JADX WARNING: type inference failed for: r1v32 */
    /* JADX WARNING: type inference failed for: r1v33 */
    /* JADX WARNING: type inference failed for: r1v34 */
    /* JADX WARNING: type inference failed for: r1v35 */
    /* JADX WARNING: type inference failed for: r1v36 */
    /* JADX WARNING: type inference failed for: r1v39 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0122 A[Catch:{ IOException -> 0x011e }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cb A[SYNTHETIC, Splitter:B:61:0x00cb] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00d3 A[Catch:{ IOException -> 0x00cf }] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00e8 A[SYNTHETIC, Splitter:B:74:0x00e8] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00f0 A[Catch:{ IOException -> 0x00ec }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0105 A[SYNTHETIC, Splitter:B:87:0x0105] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x010d A[Catch:{ IOException -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x011a A[SYNTHETIC, Splitter:B:97:0x011a] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:58:0x00c2=Splitter:B:58:0x00c2, B:84:0x00fc=Splitter:B:84:0x00fc, B:71:0x00df=Splitter:B:71:0x00df} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean a(java.lang.String r8, java.io.File r9) {
        /*
            java.lang.String r0 = "IOException during closing of zip file unpacking streams."
            boolean r1 = com.appboy.support.StringUtils.isNullOrBlank(r8)
            r2 = 0
            if (r1 == 0) goto L_0x0011
            java.lang.String r8 = a
            java.lang.String r9 = "Unpack directory null or blank. Zip file not unpacked."
            com.appboy.support.AppboyLogger.i(r8, r9)
            return r2
        L_0x0011:
            if (r9 != 0) goto L_0x001b
            java.lang.String r8 = a
            java.lang.String r9 = "Zip file is null. Zip file not unpacked."
            com.appboy.support.AppboyLogger.i(r8, r9)
            return r2
        L_0x001b:
            java.io.File r1 = new java.io.File
            r1.<init>(r8)
            r1.mkdirs()
            r1 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x00dd, Exception -> 0x00c0, all -> 0x00bd }
            r3.<init>(r9)     // Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x00dd, Exception -> 0x00c0, all -> 0x00bd }
            java.io.BufferedInputStream r9 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x00dd, Exception -> 0x00c0, all -> 0x00bd }
            r9.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x00dd, Exception -> 0x00c0, all -> 0x00bd }
            java.util.zip.ZipInputStream r3 = new java.util.zip.ZipInputStream     // Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x00dd, Exception -> 0x00c0, all -> 0x00bd }
            r3.<init>(r9)     // Catch:{ FileNotFoundException -> 0x00fa, IOException -> 0x00dd, Exception -> 0x00c0, all -> 0x00bd }
            r9 = 8192(0x2000, float:1.14794E-41)
            byte[] r9 = new byte[r9]     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
        L_0x0037:
            java.util.zip.ZipEntry r4 = r3.getNextEntry()     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            if (r4 == 0) goto L_0x0098
            java.lang.String r5 = r4.getName()     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            java.util.Locale r6 = java.util.Locale.US     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            java.lang.String r6 = r5.toLowerCase(r6)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            java.lang.String r7 = "__macosx"
            boolean r6 = r6.startsWith(r7)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            if (r6 == 0) goto L_0x0050
            goto L_0x0037
        L_0x0050:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r6.<init>()     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r6.append(r8)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            java.lang.String r7 = "/"
            r6.append(r7)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r6.append(r5)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            java.lang.String r5 = r6.toString()     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            boolean r4 = r4.isDirectory()     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            if (r4 == 0) goto L_0x0073
            java.io.File r4 = new java.io.File     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r4.mkdirs()     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            goto L_0x0037
        L_0x0073:
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r6.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r4.<init>(r6)     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
        L_0x007d:
            int r1 = r3.read(r9)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0094, Exception -> 0x0092, all -> 0x0090 }
            r5 = -1
            if (r1 == r5) goto L_0x0088
            r4.write(r9, r2, r1)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0094, Exception -> 0x0092, all -> 0x0090 }
            goto L_0x007d
        L_0x0088:
            r4.close()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0094, Exception -> 0x0092, all -> 0x0090 }
            r3.closeEntry()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0094, Exception -> 0x0092, all -> 0x0090 }
            r1 = r4
            goto L_0x0037
        L_0x0090:
            r8 = move-exception
            goto L_0x00ae
        L_0x0092:
            r8 = move-exception
            goto L_0x00b3
        L_0x0094:
            r8 = move-exception
            goto L_0x00b7
        L_0x0096:
            r8 = move-exception
            goto L_0x00bb
        L_0x0098:
            r3.close()     // Catch:{ FileNotFoundException -> 0x00b9, IOException -> 0x00b5, Exception -> 0x00b1, all -> 0x00ac }
            r8 = 1
            r3.close()     // Catch:{ IOException -> 0x00a5 }
            if (r1 == 0) goto L_0x00ab
            r1.close()     // Catch:{ IOException -> 0x00a5 }
            goto L_0x00ab
        L_0x00a5:
            r9 = move-exception
            java.lang.String r1 = a
            com.appboy.support.AppboyLogger.e(r1, r0, r9)
        L_0x00ab:
            return r8
        L_0x00ac:
            r8 = move-exception
            r4 = r1
        L_0x00ae:
            r1 = r3
            goto L_0x0118
        L_0x00b1:
            r8 = move-exception
            r4 = r1
        L_0x00b3:
            r1 = r3
            goto L_0x00c2
        L_0x00b5:
            r8 = move-exception
            r4 = r1
        L_0x00b7:
            r1 = r3
            goto L_0x00df
        L_0x00b9:
            r8 = move-exception
            r4 = r1
        L_0x00bb:
            r1 = r3
            goto L_0x00fc
        L_0x00bd:
            r8 = move-exception
            r4 = r1
            goto L_0x0118
        L_0x00c0:
            r8 = move-exception
            r4 = r1
        L_0x00c2:
            java.lang.String r9 = a     // Catch:{ all -> 0x0117 }
            java.lang.String r3 = "Exception during unpack of zip file."
            com.appboy.support.AppboyLogger.e(r9, r3, r8)     // Catch:{ all -> 0x0117 }
            if (r1 == 0) goto L_0x00d1
            r1.close()     // Catch:{ IOException -> 0x00cf }
            goto L_0x00d1
        L_0x00cf:
            r8 = move-exception
            goto L_0x00d7
        L_0x00d1:
            if (r4 == 0) goto L_0x00dc
            r4.close()     // Catch:{ IOException -> 0x00cf }
            goto L_0x00dc
        L_0x00d7:
            java.lang.String r9 = a
            com.appboy.support.AppboyLogger.e(r9, r0, r8)
        L_0x00dc:
            return r2
        L_0x00dd:
            r8 = move-exception
            r4 = r1
        L_0x00df:
            java.lang.String r9 = a     // Catch:{ all -> 0x0117 }
            java.lang.String r3 = "IOException during unpack of zip file."
            com.appboy.support.AppboyLogger.e(r9, r3, r8)     // Catch:{ all -> 0x0117 }
            if (r1 == 0) goto L_0x00ee
            r1.close()     // Catch:{ IOException -> 0x00ec }
            goto L_0x00ee
        L_0x00ec:
            r8 = move-exception
            goto L_0x00f4
        L_0x00ee:
            if (r4 == 0) goto L_0x00f9
            r4.close()     // Catch:{ IOException -> 0x00ec }
            goto L_0x00f9
        L_0x00f4:
            java.lang.String r9 = a
            com.appboy.support.AppboyLogger.e(r9, r0, r8)
        L_0x00f9:
            return r2
        L_0x00fa:
            r8 = move-exception
            r4 = r1
        L_0x00fc:
            java.lang.String r9 = a     // Catch:{ all -> 0x0117 }
            java.lang.String r3 = "FileNotFoundException during unpack of zip file."
            com.appboy.support.AppboyLogger.e(r9, r3, r8)     // Catch:{ all -> 0x0117 }
            if (r1 == 0) goto L_0x010b
            r1.close()     // Catch:{ IOException -> 0x0109 }
            goto L_0x010b
        L_0x0109:
            r8 = move-exception
            goto L_0x0111
        L_0x010b:
            if (r4 == 0) goto L_0x0116
            r4.close()     // Catch:{ IOException -> 0x0109 }
            goto L_0x0116
        L_0x0111:
            java.lang.String r9 = a
            com.appboy.support.AppboyLogger.e(r9, r0, r8)
        L_0x0116:
            return r2
        L_0x0117:
            r8 = move-exception
        L_0x0118:
            if (r1 == 0) goto L_0x0120
            r1.close()     // Catch:{ IOException -> 0x011e }
            goto L_0x0120
        L_0x011e:
            r9 = move-exception
            goto L_0x0126
        L_0x0120:
            if (r4 == 0) goto L_0x012b
            r4.close()     // Catch:{ IOException -> 0x011e }
            goto L_0x012b
        L_0x0126:
            java.lang.String r1 = a
            com.appboy.support.AppboyLogger.e(r1, r0, r9)
        L_0x012b:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.WebContentUtils.a(java.lang.String, java.io.File):boolean");
    }

    public static File getHtmlInAppMessageAssetCacheDirectory(Context context) {
        return new File(context.getCacheDir().getPath() + "/" + "appboy-html-inapp-messages");
    }
}

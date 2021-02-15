package com.appboy.support;

import android.net.Uri;
import com.tapstream.sdk.http.RequestBuilders;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppboyFileUtils {
    public static final List<String> REMOTE_SCHEMES = Collections.unmodifiableList(Arrays.asList(new String[]{"http", RequestBuilders.DEFAULT_SCHEME, "ftp", "ftps", "about", "javascript"}));
    private static final String a = AppboyLogger.getAppboyLogTag(AppboyFileUtils.class);

    public static void deleteFileOrDirectory(File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    if (file.isDirectory()) {
                        for (String file2 : file.list()) {
                            deleteFileOrDirectory(new File(file, file2));
                        }
                    }
                    file.delete();
                }
            } catch (Exception e) {
                AppboyLogger.e(a, "Caught exception while trying to delete file or directory " + file.getName(), e);
            }
        }
    }

    public static boolean isRemoteUri(Uri uri) {
        if (uri == null) {
            AppboyLogger.i(a, "Null Uri received.");
            return false;
        }
        String scheme = uri.getScheme();
        if (!StringUtils.isNullOrBlank(scheme)) {
            return REMOTE_SCHEMES.contains(scheme);
        }
        AppboyLogger.i(a, "Null or blank Uri scheme.");
        return false;
    }

    public static boolean isLocalUri(Uri uri) {
        if (uri == null) {
            AppboyLogger.i(a, "Null Uri received.");
            return false;
        }
        String scheme = uri.getScheme();
        if (StringUtils.isNullOrBlank(scheme) || scheme.equals("file")) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v8, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v21, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v24, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: java.io.DataInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: java.io.DataInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011b A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0120 A[SYNTHETIC, Splitter:B:64:0x0120] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0128 A[Catch:{ Exception -> 0x0124 }, DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0153 A[SYNTHETIC, Splitter:B:79:0x0153] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x015b A[Catch:{ Exception -> 0x0157 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:59:0x0103=Splitter:B:59:0x0103, B:74:0x0136=Splitter:B:74:0x0136} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.File downloadFileToPath(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            java.lang.String r0 = "Exception during closing of file download streams."
            boolean r1 = com.appboy.Appboy.getOutboundNetworkRequestsOffline()
            r2 = 0
            if (r1 == 0) goto L_0x0020
            java.lang.String r7 = a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "SDK is offline. File not downloaded for url: "
            r9.append(r10)
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            com.appboy.support.AppboyLogger.i(r7, r8)
            return r2
        L_0x0020:
            boolean r1 = com.appboy.support.StringUtils.isNullOrBlank(r7)
            if (r1 == 0) goto L_0x002e
            java.lang.String r7 = a
            java.lang.String r8 = "Download directory null or blank. File not downloaded."
            com.appboy.support.AppboyLogger.i(r7, r8)
            return r2
        L_0x002e:
            boolean r1 = com.appboy.support.StringUtils.isNullOrBlank(r8)
            if (r1 == 0) goto L_0x003c
            java.lang.String r7 = a
            java.lang.String r8 = "Zip file url null or blank. File not downloaded."
            com.appboy.support.AppboyLogger.i(r7, r8)
            return r2
        L_0x003c:
            boolean r1 = com.appboy.support.StringUtils.isNullOrBlank(r9)
            if (r1 == 0) goto L_0x004a
            java.lang.String r7 = a
            java.lang.String r8 = "Output filename null or blank. File not downloaded."
            com.appboy.support.AppboyLogger.i(r7, r8)
            return r2
        L_0x004a:
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            r1.mkdirs()     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            boolean r1 = com.appboy.support.StringUtils.isNullOrBlank(r10)     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            if (r1 != 0) goto L_0x0067
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            r1.<init>()     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            r1.append(r9)     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            r1.append(r10)     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            java.lang.String r9 = r1.toString()     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
        L_0x0067:
            java.io.File r10 = new java.io.File     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            r10.<init>(r7, r9)     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            java.net.URL r7 = new java.net.URL     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            r7.<init>(r8)     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            java.net.URLConnection r7 = bo.app.k.a(r7)     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ Exception -> 0x0132, all -> 0x00ff }
            int r9 = r7.getResponseCode()     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            r1 = 200(0xc8, float:2.8E-43)
            if (r9 == r1) goto L_0x00a8
            java.lang.String r10 = a     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            r1.<init>()     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.lang.String r3 = "HTTP response code was "
            r1.append(r3)     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            r1.append(r9)     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.lang.String r9 = ". File with url "
            r1.append(r9)     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            r1.append(r8)     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.lang.String r9 = " could not be downloaded."
            r1.append(r9)     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.lang.String r9 = r1.toString()     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            com.appboy.support.AppboyLogger.d(r10, r9)     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            if (r7 == 0) goto L_0x00a7
            r7.disconnect()
        L_0x00a7:
            return r2
        L_0x00a8:
            r9 = 8192(0x2000, float:1.14794E-41)
            byte[] r9 = new byte[r9]     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.io.InputStream r3 = r7.getInputStream()     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x00f8, all -> 0x00f1 }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x00ee, all -> 0x00eb }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x00ee, all -> 0x00eb }
            r4.<init>(r10)     // Catch:{ Exception -> 0x00ee, all -> 0x00eb }
            r3.<init>(r4)     // Catch:{ Exception -> 0x00ee, all -> 0x00eb }
        L_0x00bf:
            int r4 = r1.read(r9)     // Catch:{ Exception -> 0x00e9, all -> 0x00e7 }
            r5 = -1
            if (r4 == r5) goto L_0x00cb
            r5 = 0
            r3.write(r9, r5, r4)     // Catch:{ Exception -> 0x00e9, all -> 0x00e7 }
            goto L_0x00bf
        L_0x00cb:
            r1.close()     // Catch:{ Exception -> 0x00e9, all -> 0x00e7 }
            r7.disconnect()     // Catch:{ Exception -> 0x00e9, all -> 0x00e7 }
            r3.close()     // Catch:{ Exception -> 0x00e9, all -> 0x00e7 }
            if (r7 == 0) goto L_0x00d9
            r7.disconnect()
        L_0x00d9:
            r1.close()     // Catch:{ Exception -> 0x00e0 }
            r3.close()     // Catch:{ Exception -> 0x00e0 }
            goto L_0x00e6
        L_0x00e0:
            r7 = move-exception
            java.lang.String r8 = a
            com.appboy.support.AppboyLogger.e(r8, r0, r7)
        L_0x00e6:
            return r10
        L_0x00e7:
            r9 = move-exception
            goto L_0x00f4
        L_0x00e9:
            r9 = move-exception
            goto L_0x00fb
        L_0x00eb:
            r9 = move-exception
            r3 = r2
            goto L_0x00f4
        L_0x00ee:
            r9 = move-exception
            r3 = r2
            goto L_0x00fb
        L_0x00f1:
            r9 = move-exception
            r1 = r2
            r3 = r1
        L_0x00f4:
            r6 = r9
            r9 = r7
            r7 = r6
            goto L_0x0103
        L_0x00f8:
            r9 = move-exception
            r1 = r2
            r3 = r1
        L_0x00fb:
            r6 = r9
            r9 = r7
            r7 = r6
            goto L_0x0136
        L_0x00ff:
            r7 = move-exception
            r9 = r2
            r1 = r9
            r3 = r1
        L_0x0103:
            java.lang.String r10 = a     // Catch:{ all -> 0x0165 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0165 }
            r4.<init>()     // Catch:{ all -> 0x0165 }
            java.lang.String r5 = "Throwable during download of file from url : "
            r4.append(r5)     // Catch:{ all -> 0x0165 }
            r4.append(r8)     // Catch:{ all -> 0x0165 }
            java.lang.String r8 = r4.toString()     // Catch:{ all -> 0x0165 }
            com.appboy.support.AppboyLogger.e(r10, r8, r7)     // Catch:{ all -> 0x0165 }
            if (r9 == 0) goto L_0x011e
            r9.disconnect()
        L_0x011e:
            if (r1 == 0) goto L_0x0126
            r1.close()     // Catch:{ Exception -> 0x0124 }
            goto L_0x0126
        L_0x0124:
            r7 = move-exception
            goto L_0x012c
        L_0x0126:
            if (r3 == 0) goto L_0x0131
            r3.close()     // Catch:{ Exception -> 0x0124 }
            goto L_0x0131
        L_0x012c:
            java.lang.String r8 = a
            com.appboy.support.AppboyLogger.e(r8, r0, r7)
        L_0x0131:
            return r2
        L_0x0132:
            r7 = move-exception
            r9 = r2
            r1 = r9
            r3 = r1
        L_0x0136:
            java.lang.String r10 = a     // Catch:{ all -> 0x0165 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0165 }
            r4.<init>()     // Catch:{ all -> 0x0165 }
            java.lang.String r5 = "Exception during download of file from url : "
            r4.append(r5)     // Catch:{ all -> 0x0165 }
            r4.append(r8)     // Catch:{ all -> 0x0165 }
            java.lang.String r8 = r4.toString()     // Catch:{ all -> 0x0165 }
            com.appboy.support.AppboyLogger.e(r10, r8, r7)     // Catch:{ all -> 0x0165 }
            if (r9 == 0) goto L_0x0151
            r9.disconnect()
        L_0x0151:
            if (r1 == 0) goto L_0x0159
            r1.close()     // Catch:{ Exception -> 0x0157 }
            goto L_0x0159
        L_0x0157:
            r7 = move-exception
            goto L_0x015f
        L_0x0159:
            if (r3 == 0) goto L_0x0164
            r3.close()     // Catch:{ Exception -> 0x0157 }
            goto L_0x0164
        L_0x015f:
            java.lang.String r8 = a
            com.appboy.support.AppboyLogger.e(r8, r0, r7)
        L_0x0164:
            return r2
        L_0x0165:
            r7 = move-exception
            if (r9 == 0) goto L_0x016b
            r9.disconnect()
        L_0x016b:
            if (r1 == 0) goto L_0x0173
            r1.close()     // Catch:{ Exception -> 0x0171 }
            goto L_0x0173
        L_0x0171:
            r8 = move-exception
            goto L_0x0179
        L_0x0173:
            if (r3 == 0) goto L_0x017e
            r3.close()     // Catch:{ Exception -> 0x0171 }
            goto L_0x017e
        L_0x0179:
            java.lang.String r9 = a
            com.appboy.support.AppboyLogger.e(r9, r0, r8)
        L_0x017e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.AppboyFileUtils.downloadFileToPath(java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.io.File");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0076 A[SYNTHETIC, Splitter:B:31:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007e A[Catch:{ Exception -> 0x007a }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009a A[SYNTHETIC, Splitter:B:41:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a2 A[Catch:{ Exception -> 0x009e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getAssetFileStringContents(android.content.res.AssetManager r8, java.lang.String r9) {
        /*
            java.lang.String r0 = "Exception attempting to close file download streams for path:"
            r1 = 0
            java.io.InputStream r8 = r8.open(r9)     // Catch:{ Exception -> 0x005b, all -> 0x0056 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0053, all -> 0x004e }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0053, all -> 0x004e }
            java.lang.String r4 = "UTF-8"
            r3.<init>(r8, r4)     // Catch:{ Exception -> 0x0053, all -> 0x004e }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0053, all -> 0x004e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004c }
            r3.<init>()     // Catch:{ Exception -> 0x004c }
        L_0x0018:
            java.lang.String r4 = r2.readLine()     // Catch:{ Exception -> 0x004c }
            if (r4 == 0) goto L_0x0027
            r3.append(r4)     // Catch:{ Exception -> 0x004c }
            r4 = 10
            r3.append(r4)     // Catch:{ Exception -> 0x004c }
            goto L_0x0018
        L_0x0027:
            java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x004c }
            if (r8 == 0) goto L_0x0033
            r8.close()     // Catch:{ Exception -> 0x0031 }
            goto L_0x0033
        L_0x0031:
            r8 = move-exception
            goto L_0x0037
        L_0x0033:
            r2.close()     // Catch:{ Exception -> 0x0031 }
            goto L_0x004b
        L_0x0037:
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            com.appboy.support.AppboyLogger.e(r2, r9, r8)
        L_0x004b:
            return r1
        L_0x004c:
            r3 = move-exception
            goto L_0x005e
        L_0x004e:
            r2 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L_0x0098
        L_0x0053:
            r3 = move-exception
            r2 = r1
            goto L_0x005e
        L_0x0056:
            r8 = move-exception
            r2 = r1
            r1 = r8
            r8 = r2
            goto L_0x0098
        L_0x005b:
            r3 = move-exception
            r8 = r1
            r2 = r8
        L_0x005e:
            java.lang.String r4 = a     // Catch:{ all -> 0x0097 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0097 }
            r5.<init>()     // Catch:{ all -> 0x0097 }
            java.lang.String r6 = "Exception attempting to get asset content for "
            r5.append(r6)     // Catch:{ all -> 0x0097 }
            r5.append(r9)     // Catch:{ all -> 0x0097 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0097 }
            com.appboy.support.AppboyLogger.e(r4, r5, r3)     // Catch:{ all -> 0x0097 }
            if (r8 == 0) goto L_0x007c
            r8.close()     // Catch:{ Exception -> 0x007a }
            goto L_0x007c
        L_0x007a:
            r8 = move-exception
            goto L_0x0082
        L_0x007c:
            if (r2 == 0) goto L_0x0096
            r2.close()     // Catch:{ Exception -> 0x007a }
            goto L_0x0096
        L_0x0082:
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            com.appboy.support.AppboyLogger.e(r2, r9, r8)
        L_0x0096:
            return r1
        L_0x0097:
            r1 = move-exception
        L_0x0098:
            if (r8 == 0) goto L_0x00a0
            r8.close()     // Catch:{ Exception -> 0x009e }
            goto L_0x00a0
        L_0x009e:
            r8 = move-exception
            goto L_0x00a6
        L_0x00a0:
            if (r2 == 0) goto L_0x00ba
            r2.close()     // Catch:{ Exception -> 0x009e }
            goto L_0x00ba
        L_0x00a6:
            java.lang.String r2 = a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            com.appboy.support.AppboyLogger.e(r2, r9, r8)
        L_0x00ba:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.support.AppboyFileUtils.getAssetFileStringContents(android.content.res.AssetManager, java.lang.String):java.lang.String");
    }
}

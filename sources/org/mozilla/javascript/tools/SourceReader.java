package org.mozilla.javascript.tools;

import java.net.MalformedURLException;
import java.net.URL;

public class SourceReader {
    public static URL toUrl(String str) {
        if (str.indexOf(58) < 2) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:86:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object readFileOrUrl(java.lang.String r10, boolean r11, java.lang.String r12) throws java.io.IOException {
        /*
            java.net.URL r0 = toUrl(r10)
            r1 = 0
            r2 = -1
            if (r0 != 0) goto L_0x001b
            java.io.File r3 = new java.io.File     // Catch:{ all -> 0x00f7 }
            r3.<init>(r10)     // Catch:{ all -> 0x00f7 }
            long r4 = r3.length()     // Catch:{ all -> 0x00f7 }
            int r10 = (int) r4     // Catch:{ all -> 0x00f7 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x00f7 }
            r4.<init>(r3)     // Catch:{ all -> 0x00f7 }
            r3 = r1
            r1 = r4
            r4 = r3
            goto L_0x0048
        L_0x001b:
            java.net.URLConnection r10 = r0.openConnection()     // Catch:{ all -> 0x00f7 }
            java.io.InputStream r3 = r10.getInputStream()     // Catch:{ all -> 0x00f7 }
            if (r11 == 0) goto L_0x003b
            org.mozilla.javascript.commonjs.module.provider.ParsedContentType r1 = new org.mozilla.javascript.commonjs.module.provider.ParsedContentType     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = r10.getContentType()     // Catch:{ all -> 0x0037 }
            r1.<init>(r4)     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = r1.getContentType()     // Catch:{ all -> 0x0037 }
            java.lang.String r1 = r1.getEncoding()     // Catch:{ all -> 0x0037 }
            goto L_0x003c
        L_0x0037:
            r10 = move-exception
            r1 = r3
            goto L_0x00f8
        L_0x003b:
            r4 = r1
        L_0x003c:
            int r10 = r10.getContentLength()     // Catch:{ all -> 0x0037 }
            r5 = 1048576(0x100000, float:1.469368E-39)
            if (r10 <= r5) goto L_0x0045
            r10 = -1
        L_0x0045:
            r9 = r3
            r3 = r1
            r1 = r9
        L_0x0048:
            if (r10 > 0) goto L_0x004c
            r10 = 4096(0x1000, float:5.74E-42)
        L_0x004c:
            byte[] r10 = org.mozilla.javascript.Kit.readStream(r1, r10)     // Catch:{ all -> 0x00f7 }
            if (r1 == 0) goto L_0x0055
            r1.close()
        L_0x0055:
            if (r11 != 0) goto L_0x0059
            goto L_0x00f6
        L_0x0059:
            java.lang.String r11 = "UTF-8"
            r1 = 0
            r5 = 1
            if (r3 != 0) goto L_0x00db
            int r3 = r10.length
            r6 = -2
            r7 = 2
            r8 = 3
            if (r3 <= r8) goto L_0x0079
            byte r3 = r10[r1]
            if (r3 != r2) goto L_0x0079
            byte r3 = r10[r5]
            if (r3 != r6) goto L_0x0079
            byte r3 = r10[r7]
            if (r3 != 0) goto L_0x0079
            byte r3 = r10[r8]
            if (r3 != 0) goto L_0x0079
            java.lang.String r12 = "UTF-32LE"
            goto L_0x00dc
        L_0x0079:
            int r3 = r10.length
            if (r3 <= r8) goto L_0x008f
            byte r3 = r10[r1]
            if (r3 != 0) goto L_0x008f
            byte r3 = r10[r5]
            if (r3 != 0) goto L_0x008f
            byte r3 = r10[r7]
            if (r3 != r6) goto L_0x008f
            byte r3 = r10[r8]
            if (r3 != r2) goto L_0x008f
            java.lang.String r12 = "UTF-32BE"
            goto L_0x00dc
        L_0x008f:
            int r3 = r10.length
            if (r3 <= r7) goto L_0x00a6
            byte r3 = r10[r1]
            r8 = -17
            if (r3 != r8) goto L_0x00a6
            byte r3 = r10[r5]
            r8 = -69
            if (r3 != r8) goto L_0x00a6
            byte r3 = r10[r7]
            r7 = -65
            if (r3 != r7) goto L_0x00a6
        L_0x00a4:
            r12 = r11
            goto L_0x00dc
        L_0x00a6:
            int r3 = r10.length
            if (r3 <= r5) goto L_0x00b4
            byte r3 = r10[r1]
            if (r3 != r2) goto L_0x00b4
            byte r3 = r10[r5]
            if (r3 != r6) goto L_0x00b4
            java.lang.String r12 = "UTF-16LE"
            goto L_0x00dc
        L_0x00b4:
            int r3 = r10.length
            if (r3 <= r5) goto L_0x00c2
            byte r3 = r10[r1]
            if (r3 != r6) goto L_0x00c2
            byte r3 = r10[r5]
            if (r3 != r2) goto L_0x00c2
            java.lang.String r12 = "UTF-16BE"
            goto L_0x00dc
        L_0x00c2:
            if (r12 != 0) goto L_0x00dc
            if (r0 != 0) goto L_0x00cd
            java.lang.String r11 = "file.encoding"
            java.lang.String r12 = java.lang.System.getProperty(r11)
            goto L_0x00dc
        L_0x00cd:
            if (r4 == 0) goto L_0x00d8
            java.lang.String r12 = "application/"
            boolean r12 = r4.startsWith(r12)
            if (r12 == 0) goto L_0x00d8
            goto L_0x00a4
        L_0x00d8:
            java.lang.String r12 = "US-ASCII"
            goto L_0x00dc
        L_0x00db:
            r12 = r3
        L_0x00dc:
            java.lang.String r11 = new java.lang.String
            r11.<init>(r10, r12)
            int r10 = r11.length()
            if (r10 <= 0) goto L_0x00f5
            char r10 = r11.charAt(r1)
            r12 = 65279(0xfeff, float:9.1475E-41)
            if (r10 != r12) goto L_0x00f5
            java.lang.String r10 = r11.substring(r5)
            goto L_0x00f6
        L_0x00f5:
            r10 = r11
        L_0x00f6:
            return r10
        L_0x00f7:
            r10 = move-exception
        L_0x00f8:
            if (r1 == 0) goto L_0x00fd
            r1.close()
        L_0x00fd:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.SourceReader.readFileOrUrl(java.lang.String, boolean, java.lang.String):java.lang.Object");
    }
}

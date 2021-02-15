package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

class FileBackedNativeSessionFile implements NativeSessionFile {
    private final String dataTransportFilename;
    private final File file;
    private final String reportsEndpointFilename;

    FileBackedNativeSessionFile(String str, String str2, File file2) {
        this.dataTransportFilename = str;
        this.reportsEndpointFilename = str2;
        this.file = file2;
    }

    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    public InputStream getStream() {
        if (this.file.exists() && this.file.isFile()) {
            try {
                return new FileInputStream(this.file);
            } catch (FileNotFoundException unused) {
            }
        }
        return null;
    }

    public CrashlyticsReport.FilesPayload.File asFilePayload() {
        byte[] asGzippedBytes = asGzippedBytes();
        if (asGzippedBytes != null) {
            return CrashlyticsReport.FilesPayload.File.builder().setContents(asGzippedBytes).setFilename(this.dataTransportFilename).build();
        }
        return null;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0043 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:36:0x0048 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x004f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] asGzippedBytes() {
        /*
            r7 = this;
            r0 = 8192(0x2000, float:1.14794E-41)
            byte[] r0 = new byte[r0]
            r1 = 0
            java.io.InputStream r2 = r7.getStream()     // Catch:{ IOException -> 0x0050 }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x0049 }
            r3.<init>()     // Catch:{ all -> 0x0049 }
            java.util.zip.GZIPOutputStream r4 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x0044 }
            r4.<init>(r3)     // Catch:{ all -> 0x0044 }
            if (r2 != 0) goto L_0x0021
            r4.close()     // Catch:{ all -> 0x0044 }
            r3.close()     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0020
            r2.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0020:
            return r1
        L_0x0021:
            int r5 = r2.read(r0)     // Catch:{ all -> 0x003f }
            if (r5 <= 0) goto L_0x002c
            r6 = 0
            r4.write(r0, r6, r5)     // Catch:{ all -> 0x003f }
            goto L_0x0021
        L_0x002c:
            r4.finish()     // Catch:{ all -> 0x003f }
            byte[] r0 = r3.toByteArray()     // Catch:{ all -> 0x003f }
            r4.close()     // Catch:{ all -> 0x0044 }
            r3.close()     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x003e
            r2.close()     // Catch:{ IOException -> 0x0050 }
        L_0x003e:
            return r0
        L_0x003f:
            r0 = move-exception
            r4.close()     // Catch:{ all -> 0x0043 }
        L_0x0043:
            throw r0     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x0048 }
        L_0x0048:
            throw r0     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r0 = move-exception
            if (r2 == 0) goto L_0x004f
            r2.close()     // Catch:{ all -> 0x004f }
        L_0x004f:
            throw r0     // Catch:{ IOException -> 0x0050 }
        L_0x0050:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.FileBackedNativeSessionFile.asGzippedBytes():byte[]");
    }
}

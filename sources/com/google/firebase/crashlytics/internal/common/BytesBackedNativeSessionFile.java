package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

class BytesBackedNativeSessionFile implements NativeSessionFile {
    private final byte[] bytes;
    private final String dataTransportFilename;
    private final String reportsEndpointFilename;

    BytesBackedNativeSessionFile(String str, String str2, byte[] bArr) {
        this.dataTransportFilename = str;
        this.reportsEndpointFilename = str2;
        this.bytes = bArr;
    }

    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    public InputStream getStream() {
        if (isEmpty()) {
            return null;
        }
        return new ByteArrayInputStream(this.bytes);
    }

    public CrashlyticsReport.FilesPayload.File asFilePayload() {
        byte[] asGzippedBytes = asGzippedBytes();
        if (asGzippedBytes == null) {
            return null;
        }
        return CrashlyticsReport.FilesPayload.File.builder().setContents(asGzippedBytes).setFilename(this.dataTransportFilename).build();
    }

    private boolean isEmpty() {
        byte[] bArr = this.bytes;
        return bArr == null || bArr.length == 0;
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0029 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] asGzippedBytes() {
        /*
            r4 = this;
            boolean r0 = r4.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x002f }
            r0.<init>()     // Catch:{ IOException -> 0x002f }
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch:{ all -> 0x002a }
            r2.<init>(r0)     // Catch:{ all -> 0x002a }
            byte[] r3 = r4.bytes     // Catch:{ all -> 0x0025 }
            r2.write(r3)     // Catch:{ all -> 0x0025 }
            r2.finish()     // Catch:{ all -> 0x0025 }
            byte[] r3 = r0.toByteArray()     // Catch:{ all -> 0x0025 }
            r2.close()     // Catch:{ all -> 0x002a }
            r0.close()     // Catch:{ IOException -> 0x002f }
            return r3
        L_0x0025:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x0029 }
        L_0x0029:
            throw r3     // Catch:{ all -> 0x002a }
        L_0x002a:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x002e }
        L_0x002e:
            throw r2     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.BytesBackedNativeSessionFile.asGzippedBytes():byte[]");
    }
}

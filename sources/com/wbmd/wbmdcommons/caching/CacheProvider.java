package com.wbmd.wbmdcommons.caching;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StatFs;
import android.util.Log;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheProvider implements ICacheProvider {
    public static final int DEFAULT_DISK_USAGE_BYTES = 2097152;
    static String TAG = CacheProvider.class.getSimpleName();
    final Context mContext;
    private final Map<String, CacheHeader> mEntries = new LinkedHashMap(16, 0.75f, true);
    private File mRootDirectory;

    public CacheProvider(Context context) {
        this.mContext = context;
    }

    public synchronized void initialize(File file, int i) {
        if (!file.exists() && !file.mkdirs()) {
            Log.e(TAG, "Error creating caching directory");
        }
        this.mRootDirectory = file;
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File fileInputStream : listFiles) {
                try {
                    CountingInputStream countingInputStream = new CountingInputStream(new FileInputStream(fileInputStream));
                    CacheHeader readHeader = CacheHeader.readHeader(countingInputStream);
                    this.mEntries.put(readHeader.key, readHeader);
                    countingInputStream.close();
                } catch (Exception unused) {
                }
            }
        }
    }

    public void clear() {
        if (this.mRootDirectory.exists()) {
            for (File delete : this.mRootDirectory.listFiles()) {
                delete.delete();
            }
        }
    }

    public void removeKey(String str) {
        File fileForKey = getFileForKey(str);
        if (fileForKey.exists()) {
            fileForKey.delete();
        }
        this.mEntries.remove(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005e, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.wbmd.wbmdcommons.caching.CacheProvider.Entry get(java.lang.String r8, boolean r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.util.Map<java.lang.String, com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader> r0 = r7.mEntries     // Catch:{ all -> 0x007c }
            boolean r0 = r0.containsKey(r8)     // Catch:{ all -> 0x007c }
            r1 = 0
            if (r0 == 0) goto L_0x007a
            java.util.Map<java.lang.String, com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader> r0 = r7.mEntries     // Catch:{ all -> 0x007c }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x007c }
            com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader r0 = (com.wbmd.wbmdcommons.caching.CacheProvider.CacheHeader) r0     // Catch:{ all -> 0x007c }
            com.wbmd.wbmdcommons.caching.CacheProvider$Entry r2 = new com.wbmd.wbmdcommons.caching.CacheProvider$Entry     // Catch:{ all -> 0x007c }
            r2.<init>()     // Catch:{ all -> 0x007c }
            java.lang.String r3 = r0.key     // Catch:{ all -> 0x007c }
            r2.key = r3     // Catch:{ all -> 0x007c }
            if (r9 != 0) goto L_0x0025
            boolean r9 = r0.hasExpired()     // Catch:{ all -> 0x007c }
            if (r9 == 0) goto L_0x0025
            monitor-exit(r7)
            return r1
        L_0x0025:
            java.io.File r8 = r7.getFileForKey(r8)     // Catch:{ all -> 0x007c }
            boolean r9 = r8.exists()     // Catch:{ all -> 0x007c }
            if (r9 == 0) goto L_0x007a
            com.wbmd.wbmdcommons.caching.CacheProvider$CountingInputStream r9 = new com.wbmd.wbmdcommons.caching.CacheProvider$CountingInputStream     // Catch:{ Exception -> 0x005f }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005f }
            r3.<init>(r8)     // Catch:{ Exception -> 0x005f }
            r9.<init>(r3)     // Catch:{ Exception -> 0x005f }
            com.wbmd.wbmdcommons.caching.CacheProvider.CacheHeader.readHeader(r9)     // Catch:{ Exception -> 0x005f }
            long r3 = r8.length()     // Catch:{ Exception -> 0x005f }
            int r8 = r9.bytesRead     // Catch:{ Exception -> 0x005f }
            long r5 = (long) r8     // Catch:{ Exception -> 0x005f }
            long r3 = r3 - r5
            int r8 = (int) r3     // Catch:{ Exception -> 0x005f }
            byte[] r8 = streamToBytes(r9, r8)     // Catch:{ Exception -> 0x005f }
            r2.data = r8     // Catch:{ Exception -> 0x005f }
            byte[] r8 = r2.data     // Catch:{ Exception -> 0x005f }
            if (r8 == 0) goto L_0x005d
            long r8 = r0.size     // Catch:{ Exception -> 0x005f }
            byte[] r0 = r2.data     // Catch:{ Exception -> 0x005f }
            int r0 = r0.length     // Catch:{ Exception -> 0x005f }
            long r3 = (long) r0
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 == 0) goto L_0x005d
            monitor-exit(r7)
            return r1
        L_0x005d:
            monitor-exit(r7)
            return r2
        L_0x005f:
            r8 = move-exception
            java.lang.String r9 = TAG     // Catch:{ all -> 0x007c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x007c }
            r0.<init>()     // Catch:{ all -> 0x007c }
            java.lang.String r2 = "Error in get CacheProvider"
            r0.append(r2)     // Catch:{ all -> 0x007c }
            java.lang.String r8 = r8.getMessage()     // Catch:{ all -> 0x007c }
            r0.append(r8)     // Catch:{ all -> 0x007c }
            java.lang.String r8 = r0.toString()     // Catch:{ all -> 0x007c }
            android.util.Log.e(r9, r8)     // Catch:{ all -> 0x007c }
        L_0x007a:
            monitor-exit(r7)
            return r1
        L_0x007c:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmdcommons.caching.CacheProvider.get(java.lang.String, boolean):com.wbmd.wbmdcommons.caching.CacheProvider$Entry");
    }

    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0091, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(com.wbmd.wbmdcommons.caching.CacheProvider.Entry r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            if (r7 == 0) goto L_0x0090
            byte[] r0 = r7.data     // Catch:{ all -> 0x008d }
            if (r0 == 0) goto L_0x0090
            byte[] r0 = r7.data     // Catch:{ all -> 0x008d }
            int r0 = r0.length     // Catch:{ all -> 0x008d }
            if (r0 == 0) goto L_0x0090
            java.lang.String r0 = r7.key     // Catch:{ all -> 0x008d }
            boolean r0 = com.wbmd.wbmdcommons.extensions.StringExtensions.isNullOrEmpty(r0)     // Catch:{ all -> 0x008d }
            if (r0 == 0) goto L_0x0016
            goto L_0x0090
        L_0x0016:
            java.util.Map<java.lang.String, com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader> r0 = r6.mEntries     // Catch:{ all -> 0x008d }
            java.lang.String r1 = r7.key     // Catch:{ all -> 0x008d }
            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x008d }
            if (r0 == 0) goto L_0x0027
            java.util.Map<java.lang.String, com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader> r0 = r6.mEntries     // Catch:{ all -> 0x008d }
            java.lang.String r1 = r7.key     // Catch:{ all -> 0x008d }
            r0.remove(r1)     // Catch:{ all -> 0x008d }
        L_0x0027:
            java.lang.String r0 = r7.key     // Catch:{ all -> 0x008d }
            java.io.File r0 = r6.getFileForKey(r0)     // Catch:{ all -> 0x008d }
            boolean r1 = r0.exists()     // Catch:{ all -> 0x008d }
            if (r1 == 0) goto L_0x0036
            r0.delete()     // Catch:{ all -> 0x008d }
        L_0x0036:
            java.io.File r1 = r6.mRootDirectory     // Catch:{ Exception -> 0x0074 }
            long r1 = getUsableSpace(r1)     // Catch:{ Exception -> 0x0074 }
            byte[] r3 = r7.data     // Catch:{ Exception -> 0x0074 }
            int r3 = r3.length     // Catch:{ Exception -> 0x0074 }
            long r3 = (long) r3     // Catch:{ Exception -> 0x0074 }
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0047
            r6.prune(r7)     // Catch:{ Exception -> 0x0074 }
        L_0x0047:
            com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader r1 = new com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader     // Catch:{ Exception -> 0x0074 }
            java.lang.String r2 = r7.key     // Catch:{ Exception -> 0x0074 }
            r1.<init>(r2, r7)     // Catch:{ Exception -> 0x0074 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0074 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0074 }
            boolean r0 = r1.writeHeader(r2)     // Catch:{ Exception -> 0x0074 }
            if (r0 == 0) goto L_0x0069
            byte[] r0 = r7.data     // Catch:{ Exception -> 0x0074 }
            r2.write(r0)     // Catch:{ Exception -> 0x0074 }
            r2.close()     // Catch:{ Exception -> 0x0074 }
            java.util.Map<java.lang.String, com.wbmd.wbmdcommons.caching.CacheProvider$CacheHeader> r0 = r6.mEntries     // Catch:{ Exception -> 0x0074 }
            java.lang.String r7 = r7.key     // Catch:{ Exception -> 0x0074 }
            r0.put(r7, r1)     // Catch:{ Exception -> 0x0074 }
            goto L_0x008b
        L_0x0069:
            r2.close()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r7 = TAG     // Catch:{ Exception -> 0x0074 }
            java.lang.String r0 = "Failed to write the header in cache"
            android.util.Log.e(r7, r0)     // Catch:{ Exception -> 0x0074 }
            goto L_0x008b
        L_0x0074:
            r7 = move-exception
            java.lang.String r0 = TAG     // Catch:{ all -> 0x008d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x008d }
            r1.<init>()     // Catch:{ all -> 0x008d }
            java.lang.String r2 = "Failed to write the value in cache"
            r1.append(r2)     // Catch:{ all -> 0x008d }
            r1.append(r7)     // Catch:{ all -> 0x008d }
            java.lang.String r7 = r1.toString()     // Catch:{ all -> 0x008d }
            android.util.Log.e(r0, r7)     // Catch:{ all -> 0x008d }
        L_0x008b:
            monitor-exit(r6)
            return
        L_0x008d:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        L_0x0090:
            monitor-exit(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.wbmdcommons.caching.CacheProvider.put(com.wbmd.wbmdcommons.caching.CacheProvider$Entry):void");
    }

    public static long getUsableSpace(File file) {
        StatFs statFs = new StatFs(file.getPath());
        return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
    }

    public static String hashKeyForDisk(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return bytesToHexString(instance.digest());
        } catch (NoSuchAlgorithmException unused) {
            return String.valueOf(str.hashCode());
        }
    }

    private static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    private File getFileForKey(String str) {
        return new File(this.mRootDirectory, str);
    }

    private void prune(Entry entry) {
        if (entry.data != null) {
            int length = entry.data.length;
            ArrayList<CacheHeader> arrayList = new ArrayList<>(this.mEntries.values());
            Collections.sort(arrayList, new CacheHeaderComparator());
            int i = 0;
            for (CacheHeader cacheHeader : arrayList) {
                if (length >= i) {
                    removeKey(cacheHeader.key);
                    i = (int) (((long) i) + cacheHeader.size);
                } else {
                    return;
                }
            }
        }
    }

    public class CacheHeaderComparator implements Comparator<CacheHeader> {
        public CacheHeaderComparator() {
        }

        public int compare(CacheHeader cacheHeader, CacheHeader cacheHeader2) {
            if (cacheHeader.ExpireTime < cacheHeader2.ExpireTime) {
                return -1;
            }
            return cacheHeader.ExpireTime > cacheHeader2.ExpireTime ? 1 : 0;
        }
    }

    static class CacheHeader {
        public long ExpireTime;
        public String key;
        public long size;

        public CacheHeader() {
        }

        public CacheHeader(String str, Entry entry) {
            this.key = str;
            this.size = entry.data == null ? 0 : (long) entry.data.length;
            this.ExpireTime = entry.ExpireTime;
        }

        public boolean writeHeader(OutputStream outputStream) {
            try {
                CacheProvider.writeString(outputStream, this.key);
                CacheProvider.writeLong(outputStream, this.size);
                CacheProvider.writeLong(outputStream, this.ExpireTime);
                outputStream.flush();
                return true;
            } catch (IOException e) {
                Trace.d("%s", e.toString());
                return false;
            }
        }

        public static CacheHeader readHeader(InputStream inputStream) throws IOException {
            CacheHeader cacheHeader = new CacheHeader();
            cacheHeader.key = CacheProvider.readString(inputStream);
            cacheHeader.size = CacheProvider.readLong(inputStream);
            cacheHeader.ExpireTime = CacheProvider.readLong(inputStream);
            return cacheHeader;
        }

        public boolean hasExpired() {
            return this.ExpireTime < System.currentTimeMillis();
        }
    }

    private static int read(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    static void writeString(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        writeLong(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String readString(InputStream inputStream) throws IOException {
        return new String(streamToBytes(inputStream, (int) readLong(inputStream)), "UTF-8");
    }

    static void writeLong(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) (j >>> 0)));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static long readLong(InputStream inputStream) throws IOException {
        return ((((long) read(inputStream)) & 255) << 0) | 0 | ((((long) read(inputStream)) & 255) << 8) | ((((long) read(inputStream)) & 255) << 16) | ((((long) read(inputStream)) & 255) << 24) | ((((long) read(inputStream)) & 255) << 32) | ((((long) read(inputStream)) & 255) << 40) | ((((long) read(inputStream)) & 255) << 48) | ((255 & ((long) read(inputStream))) << 56);
    }

    private static byte[] streamToBytes(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read == -1) {
                break;
            }
            i2 += read;
        }
        if (i2 == i) {
            return bArr;
        }
        throw new IOException("Expected " + i + " bytes, read " + i2 + " bytes");
    }

    public static class Entry {
        public long ExpireTime;
        public byte[] data;
        public String key;

        public boolean hasExpired() {
            return this.ExpireTime < System.currentTimeMillis();
        }
    }

    private static class CountingInputStream extends FilterInputStream {
        /* access modifiers changed from: private */
        public int bytesRead;

        private CountingInputStream(InputStream inputStream) {
            super(inputStream);
            this.bytesRead = 0;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read != -1) {
                this.bytesRead++;
            }
            return read;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.bytesRead += read;
            }
            return read;
        }
    }
}

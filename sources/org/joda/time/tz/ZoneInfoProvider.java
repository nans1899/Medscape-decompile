package org.joda.time.tz;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.DateTimeZone;

public class ZoneInfoProvider implements Provider {
    private final File iFileDir;
    /* access modifiers changed from: private */
    public final ClassLoader iLoader;
    private final String iResourcePath;
    private final Set<String> iZoneInfoKeys;
    private final Map<String, Object> iZoneInfoMap;

    public ZoneInfoProvider(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("No file directory provided");
        } else if (!file.exists()) {
            throw new IOException("File directory doesn't exist: " + file);
        } else if (file.isDirectory()) {
            this.iFileDir = file;
            this.iResourcePath = null;
            this.iLoader = null;
            this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
            this.iZoneInfoKeys = Collections.unmodifiableSortedSet(new TreeSet(this.iZoneInfoMap.keySet()));
        } else {
            throw new IOException("File doesn't refer to a directory: " + file);
        }
    }

    public ZoneInfoProvider(String str) throws IOException {
        this(str, (ClassLoader) null, false);
    }

    public ZoneInfoProvider(String str, ClassLoader classLoader) throws IOException {
        this(str, classLoader, true);
    }

    private ZoneInfoProvider(String str, ClassLoader classLoader, boolean z) throws IOException {
        if (str != null) {
            if (!str.endsWith("/")) {
                str = str + '/';
            }
            this.iFileDir = null;
            this.iResourcePath = str;
            if (classLoader == null && !z) {
                classLoader = getClass().getClassLoader();
            }
            this.iLoader = classLoader;
            this.iZoneInfoMap = loadZoneInfoMap(openResource("ZoneInfoMap"));
            this.iZoneInfoKeys = Collections.unmodifiableSortedSet(new TreeSet(this.iZoneInfoMap.keySet()));
            return;
        }
        throw new IllegalArgumentException("No resource path provided");
    }

    public DateTimeZone getZone(String str) {
        Object obj;
        if (str == null || (obj = this.iZoneInfoMap.get(str)) == null) {
            return null;
        }
        if (obj instanceof SoftReference) {
            DateTimeZone dateTimeZone = (DateTimeZone) ((SoftReference) obj).get();
            if (dateTimeZone != null) {
                return dateTimeZone;
            }
            return loadZoneData(str);
        } else if (str.equals(obj)) {
            return loadZoneData(str);
        } else {
            return getZone((String) obj);
        }
    }

    public Set<String> getAvailableIDs() {
        return this.iZoneInfoKeys;
    }

    /* access modifiers changed from: protected */
    public void uncaughtException(Exception exc) {
        exc.printStackTrace();
    }

    private InputStream openResource(String str) throws IOException {
        if (this.iFileDir != null) {
            return new FileInputStream(new File(this.iFileDir, str));
        }
        final String concat = this.iResourcePath.concat(str);
        InputStream inputStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction<InputStream>() {
            public InputStream run() {
                if (ZoneInfoProvider.this.iLoader != null) {
                    return ZoneInfoProvider.this.iLoader.getResourceAsStream(concat);
                }
                return ClassLoader.getSystemResourceAsStream(concat);
            }
        });
        if (inputStream != null) {
            return inputStream;
        }
        StringBuilder sb = new StringBuilder(40);
        sb.append("Resource not found: \"");
        sb.append(concat);
        sb.append("\" ClassLoader: ");
        ClassLoader classLoader = this.iLoader;
        sb.append(classLoader != null ? classLoader.toString() : "system");
        throw new IOException(sb.toString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x002c A[SYNTHETIC, Splitter:B:19:0x002c] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0032 A[SYNTHETIC, Splitter:B:24:0x0032] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.joda.time.DateTimeZone loadZoneData(java.lang.String r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.InputStream r1 = r5.openResource(r6)     // Catch:{ IOException -> 0x0020, all -> 0x001e }
            org.joda.time.DateTimeZone r2 = org.joda.time.tz.DateTimeZoneBuilder.readFrom((java.io.InputStream) r1, (java.lang.String) r6)     // Catch:{ IOException -> 0x001c }
            java.util.Map<java.lang.String, java.lang.Object> r3 = r5.iZoneInfoMap     // Catch:{ IOException -> 0x001c }
            java.lang.ref.SoftReference r4 = new java.lang.ref.SoftReference     // Catch:{ IOException -> 0x001c }
            r4.<init>(r2)     // Catch:{ IOException -> 0x001c }
            r3.put(r6, r4)     // Catch:{ IOException -> 0x001c }
            if (r1 == 0) goto L_0x0018
            r1.close()     // Catch:{ IOException -> 0x0018 }
        L_0x0018:
            return r2
        L_0x0019:
            r6 = move-exception
            r0 = r1
            goto L_0x0030
        L_0x001c:
            r2 = move-exception
            goto L_0x0022
        L_0x001e:
            r6 = move-exception
            goto L_0x0030
        L_0x0020:
            r2 = move-exception
            r1 = r0
        L_0x0022:
            r5.uncaughtException(r2)     // Catch:{ all -> 0x0019 }
            java.util.Map<java.lang.String, java.lang.Object> r2 = r5.iZoneInfoMap     // Catch:{ all -> 0x0019 }
            r2.remove(r6)     // Catch:{ all -> 0x0019 }
            if (r1 == 0) goto L_0x002f
            r1.close()     // Catch:{ IOException -> 0x002f }
        L_0x002f:
            return r0
        L_0x0030:
            if (r0 == 0) goto L_0x0035
            r0.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0035:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.tz.ZoneInfoProvider.loadZoneData(java.lang.String):org.joda.time.DateTimeZone");
    }

    private static Map<String, Object> loadZoneInfoMap(InputStream inputStream) throws IOException {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            readZoneInfoMap(dataInputStream, concurrentHashMap);
            concurrentHashMap.put("UTC", new SoftReference(DateTimeZone.UTC));
            return concurrentHashMap;
        } finally {
            try {
                dataInputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    private static void readZoneInfoMap(DataInputStream dataInputStream, Map<String, Object> map) throws IOException {
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        String[] strArr = new String[readUnsignedShort];
        int i = 0;
        for (int i2 = 0; i2 < readUnsignedShort; i2++) {
            strArr[i2] = dataInputStream.readUTF().intern();
        }
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        while (i < readUnsignedShort2) {
            try {
                map.put(strArr[dataInputStream.readUnsignedShort()], strArr[dataInputStream.readUnsignedShort()]);
                i++;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new IOException("Corrupt zone info map");
            }
        }
    }
}

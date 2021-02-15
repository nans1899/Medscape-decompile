package org.threeten.bp.zone;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.threeten.bp.jdk8.Jdk8Methods;

public final class TzdbZoneRulesProvider extends ZoneRulesProvider {
    private Set<String> loadedUrls = new CopyOnWriteArraySet();
    private List<String> regionIds;
    private final ConcurrentNavigableMap<String, Version> versions = new ConcurrentSkipListMap();

    public String toString() {
        return "TZDB";
    }

    public TzdbZoneRulesProvider() {
        if (!load(ZoneRulesProvider.class.getClassLoader())) {
            throw new ZoneRulesException("No time-zone rules found for 'TZDB'");
        }
    }

    public TzdbZoneRulesProvider(URL url) {
        try {
            if (!load(url)) {
                throw new ZoneRulesException("No time-zone rules found: " + url);
            }
        } catch (Exception e) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, e);
        }
    }

    public TzdbZoneRulesProvider(InputStream inputStream) {
        try {
            load(inputStream);
        } catch (Exception e) {
            throw new ZoneRulesException("Unable to load TZDB time-zone rules", e);
        }
    }

    /* access modifiers changed from: protected */
    public Set<String> provideZoneIds() {
        return new HashSet(this.regionIds);
    }

    /* access modifiers changed from: protected */
    public ZoneRules provideRules(String str, boolean z) {
        Jdk8Methods.requireNonNull(str, "zoneId");
        ZoneRules rules = ((Version) this.versions.lastEntry().getValue()).getRules(str);
        if (rules != null) {
            return rules;
        }
        throw new ZoneRulesException("Unknown time-zone ID: " + str);
    }

    /* access modifiers changed from: protected */
    public NavigableMap<String, ZoneRules> provideVersions(String str) {
        TreeMap treeMap = new TreeMap();
        for (Version version : this.versions.values()) {
            ZoneRules rules = version.getRules(str);
            if (rules != null) {
                treeMap.put(version.versionId, rules);
            }
        }
        return treeMap;
    }

    private boolean load(ClassLoader classLoader) {
        URL url = null;
        try {
            Enumeration<URL> resources = classLoader.getResources("org/threeten/bp/TZDB.dat");
            boolean z = false;
            while (resources.hasMoreElements()) {
                URL nextElement = resources.nextElement();
                try {
                    z |= load(nextElement);
                    URL url2 = nextElement;
                } catch (Exception e) {
                    e = e;
                    url = nextElement;
                    throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, e);
                }
            }
            return z;
        } catch (Exception e2) {
            e = e2;
            throw new ZoneRulesException("Unable to load TZDB time-zone rules: " + url, e);
        }
    }

    private boolean load(URL url) throws ClassNotFoundException, IOException, ZoneRulesException {
        boolean z = false;
        if (this.loadedUrls.add(url.toExternalForm())) {
            InputStream inputStream = null;
            try {
                inputStream = url.openStream();
                z = false | load(inputStream);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        return z;
    }

    private boolean load(InputStream inputStream) throws IOException, StreamCorruptedException {
        boolean z = false;
        for (Version next : loadData(inputStream)) {
            Version version = (Version) this.versions.putIfAbsent(next.versionId, next);
            if (version == null || version.versionId.equals(next.versionId)) {
                z = true;
            } else {
                throw new ZoneRulesException("Data already loaded for TZDB time-zone rules version: " + next.versionId);
            }
        }
        return z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Iterable<org.threeten.bp.zone.TzdbZoneRulesProvider.Version> loadData(java.io.InputStream r13) throws java.io.IOException, java.io.StreamCorruptedException {
        /*
            r12 = this;
            java.io.DataInputStream r0 = new java.io.DataInputStream
            r0.<init>(r13)
            byte r13 = r0.readByte()
            java.lang.String r1 = "File format not recognised"
            r2 = 1
            if (r13 != r2) goto L_0x0099
            java.lang.String r13 = r0.readUTF()
            java.lang.String r2 = "TZDB"
            boolean r13 = r2.equals(r13)
            if (r13 == 0) goto L_0x0093
            short r13 = r0.readShort()
            java.lang.String[] r1 = new java.lang.String[r13]
            r2 = 0
            r3 = 0
        L_0x0022:
            if (r3 >= r13) goto L_0x002d
            java.lang.String r4 = r0.readUTF()
            r1[r3] = r4
            int r3 = r3 + 1
            goto L_0x0022
        L_0x002d:
            short r3 = r0.readShort()
            java.lang.String[] r4 = new java.lang.String[r3]
            r5 = 0
        L_0x0034:
            if (r5 >= r3) goto L_0x003f
            java.lang.String r6 = r0.readUTF()
            r4[r5] = r6
            int r5 = r5 + 1
            goto L_0x0034
        L_0x003f:
            java.util.List r3 = java.util.Arrays.asList(r4)
            r12.regionIds = r3
            short r3 = r0.readShort()
            java.lang.Object[] r5 = new java.lang.Object[r3]
            r6 = 0
        L_0x004c:
            if (r6 >= r3) goto L_0x005c
            short r7 = r0.readShort()
            byte[] r7 = new byte[r7]
            r0.readFully(r7)
            r5[r6] = r7
            int r6 = r6 + 1
            goto L_0x004c
        L_0x005c:
            java.util.concurrent.atomic.AtomicReferenceArray r3 = new java.util.concurrent.atomic.AtomicReferenceArray
            r3.<init>(r5)
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>(r13)
            r6 = 0
        L_0x0067:
            if (r6 >= r13) goto L_0x0092
            short r7 = r0.readShort()
            java.lang.String[] r8 = new java.lang.String[r7]
            short[] r9 = new short[r7]
            r10 = 0
        L_0x0072:
            if (r10 >= r7) goto L_0x0085
            short r11 = r0.readShort()
            r11 = r4[r11]
            r8[r10] = r11
            short r11 = r0.readShort()
            r9[r10] = r11
            int r10 = r10 + 1
            goto L_0x0072
        L_0x0085:
            org.threeten.bp.zone.TzdbZoneRulesProvider$Version r7 = new org.threeten.bp.zone.TzdbZoneRulesProvider$Version
            r10 = r1[r6]
            r7.<init>(r10, r8, r9, r3)
            r5.add(r7)
            int r6 = r6 + 1
            goto L_0x0067
        L_0x0092:
            return r5
        L_0x0093:
            java.io.StreamCorruptedException r13 = new java.io.StreamCorruptedException
            r13.<init>(r1)
            throw r13
        L_0x0099:
            java.io.StreamCorruptedException r13 = new java.io.StreamCorruptedException
            r13.<init>(r1)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.threeten.bp.zone.TzdbZoneRulesProvider.loadData(java.io.InputStream):java.lang.Iterable");
    }

    static class Version {
        private final String[] regionArray;
        private final AtomicReferenceArray<Object> ruleData;
        private final short[] ruleIndices;
        /* access modifiers changed from: private */
        public final String versionId;

        Version(String str, String[] strArr, short[] sArr, AtomicReferenceArray<Object> atomicReferenceArray) {
            this.ruleData = atomicReferenceArray;
            this.versionId = str;
            this.regionArray = strArr;
            this.ruleIndices = sArr;
        }

        /* access modifiers changed from: package-private */
        public ZoneRules getRules(String str) {
            int binarySearch = Arrays.binarySearch(this.regionArray, str);
            if (binarySearch < 0) {
                return null;
            }
            try {
                return createRule(this.ruleIndices[binarySearch]);
            } catch (Exception e) {
                throw new ZoneRulesException("Invalid binary time-zone data: TZDB:" + str + ", version: " + this.versionId, e);
            }
        }

        /* access modifiers changed from: package-private */
        public ZoneRules createRule(short s) throws Exception {
            Object obj = this.ruleData.get(s);
            if (obj instanceof byte[]) {
                obj = Ser.read(new DataInputStream(new ByteArrayInputStream((byte[]) obj)));
                this.ruleData.set(s, obj);
            }
            return (ZoneRules) obj;
        }

        public String toString() {
            return this.versionId;
        }
    }
}

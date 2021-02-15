package org.joda.time.tz;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DefaultNameProvider implements NameProvider {
    private HashMap<Locale, Map<String, Map<String, Object>>> iByLocaleCache = createCache();
    private HashMap<Locale, Map<String, Map<Boolean, Object>>> iByLocaleCache2 = createCache();

    public String getShortName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        if (nameSet == null) {
            return null;
        }
        return nameSet[0];
    }

    public String getName(Locale locale, String str, String str2) {
        String[] nameSet = getNameSet(locale, str, str2);
        if (nameSet == null) {
            return null;
        }
        return nameSet[1];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d0, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.lang.String[] getNameSet(java.util.Locale r10, java.lang.String r11, java.lang.String r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = 0
            if (r10 == 0) goto L_0x00cf
            if (r11 == 0) goto L_0x00cf
            if (r12 != 0) goto L_0x000a
            goto L_0x00cf
        L_0x000a:
            java.util.HashMap<java.util.Locale, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Object>>> r1 = r9.iByLocaleCache     // Catch:{ all -> 0x00cc }
            java.lang.Object r1 = r1.get(r10)     // Catch:{ all -> 0x00cc }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ all -> 0x00cc }
            if (r1 != 0) goto L_0x001e
            java.util.HashMap<java.util.Locale, java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Object>>> r1 = r9.iByLocaleCache     // Catch:{ all -> 0x00cc }
            java.util.HashMap r2 = r9.createCache()     // Catch:{ all -> 0x00cc }
            r1.put(r10, r2)     // Catch:{ all -> 0x00cc }
            r1 = r2
        L_0x001e:
            java.lang.Object r2 = r1.get(r11)     // Catch:{ all -> 0x00cc }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x00cc }
            if (r2 != 0) goto L_0x00c2
            java.util.HashMap r2 = r9.createCache()     // Catch:{ all -> 0x00cc }
            r1.put(r11, r2)     // Catch:{ all -> 0x00cc }
            java.util.Locale r1 = java.util.Locale.ENGLISH     // Catch:{ all -> 0x00cc }
            java.text.DateFormatSymbols r1 = org.joda.time.DateTimeUtils.getDateFormatSymbols(r1)     // Catch:{ all -> 0x00cc }
            java.lang.String[][] r1 = r1.getZoneStrings()     // Catch:{ all -> 0x00cc }
            int r3 = r1.length     // Catch:{ all -> 0x00cc }
            r4 = 0
            r5 = 0
        L_0x003a:
            r6 = 5
            if (r5 >= r3) goto L_0x0050
            r7 = r1[r5]     // Catch:{ all -> 0x00cc }
            if (r7 == 0) goto L_0x004d
            int r8 = r7.length     // Catch:{ all -> 0x00cc }
            if (r8 < r6) goto L_0x004d
            r8 = r7[r4]     // Catch:{ all -> 0x00cc }
            boolean r8 = r11.equals(r8)     // Catch:{ all -> 0x00cc }
            if (r8 == 0) goto L_0x004d
            goto L_0x0051
        L_0x004d:
            int r5 = r5 + 1
            goto L_0x003a
        L_0x0050:
            r7 = r0
        L_0x0051:
            java.text.DateFormatSymbols r10 = org.joda.time.DateTimeUtils.getDateFormatSymbols(r10)     // Catch:{ all -> 0x00cc }
            java.lang.String[][] r10 = r10.getZoneStrings()     // Catch:{ all -> 0x00cc }
            int r1 = r10.length     // Catch:{ all -> 0x00cc }
            r3 = 0
        L_0x005b:
            if (r3 >= r1) goto L_0x0071
            r5 = r10[r3]     // Catch:{ all -> 0x00cc }
            if (r5 == 0) goto L_0x006e
            int r8 = r5.length     // Catch:{ all -> 0x00cc }
            if (r8 < r6) goto L_0x006e
            r8 = r5[r4]     // Catch:{ all -> 0x00cc }
            boolean r8 = r11.equals(r8)     // Catch:{ all -> 0x00cc }
            if (r8 == 0) goto L_0x006e
            r0 = r5
            goto L_0x0071
        L_0x006e:
            int r3 = r3 + 1
            goto L_0x005b
        L_0x0071:
            if (r7 == 0) goto L_0x00c2
            if (r0 == 0) goto L_0x00c2
            r10 = 2
            r11 = r7[r10]     // Catch:{ all -> 0x00cc }
            java.lang.String[] r1 = new java.lang.String[r10]     // Catch:{ all -> 0x00cc }
            r3 = r0[r10]     // Catch:{ all -> 0x00cc }
            r1[r4] = r3     // Catch:{ all -> 0x00cc }
            r3 = 1
            r5 = r0[r3]     // Catch:{ all -> 0x00cc }
            r1[r3] = r5     // Catch:{ all -> 0x00cc }
            r2.put(r11, r1)     // Catch:{ all -> 0x00cc }
            r11 = r7[r10]     // Catch:{ all -> 0x00cc }
            r1 = 4
            r5 = r7[r1]     // Catch:{ all -> 0x00cc }
            boolean r11 = r11.equals(r5)     // Catch:{ all -> 0x00cc }
            r5 = 3
            if (r11 == 0) goto L_0x00b3
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00cc }
            r11.<init>()     // Catch:{ all -> 0x00cc }
            r6 = r7[r1]     // Catch:{ all -> 0x00cc }
            r11.append(r6)     // Catch:{ all -> 0x00cc }
            java.lang.String r6 = "-Summer"
            r11.append(r6)     // Catch:{ all -> 0x00cc }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00cc }
            java.lang.String[] r10 = new java.lang.String[r10]     // Catch:{ all -> 0x00cc }
            r1 = r0[r1]     // Catch:{ all -> 0x00cc }
            r10[r4] = r1     // Catch:{ all -> 0x00cc }
            r0 = r0[r5]     // Catch:{ all -> 0x00cc }
            r10[r3] = r0     // Catch:{ all -> 0x00cc }
            r2.put(r11, r10)     // Catch:{ all -> 0x00cc }
            goto L_0x00c2
        L_0x00b3:
            r11 = r7[r1]     // Catch:{ all -> 0x00cc }
            java.lang.String[] r10 = new java.lang.String[r10]     // Catch:{ all -> 0x00cc }
            r1 = r0[r1]     // Catch:{ all -> 0x00cc }
            r10[r4] = r1     // Catch:{ all -> 0x00cc }
            r0 = r0[r5]     // Catch:{ all -> 0x00cc }
            r10[r3] = r0     // Catch:{ all -> 0x00cc }
            r2.put(r11, r10)     // Catch:{ all -> 0x00cc }
        L_0x00c2:
            java.lang.Object r10 = r2.get(r12)     // Catch:{ all -> 0x00cc }
            java.lang.String[] r10 = (java.lang.String[]) r10     // Catch:{ all -> 0x00cc }
            java.lang.String[] r10 = (java.lang.String[]) r10     // Catch:{ all -> 0x00cc }
            monitor-exit(r9)
            return r10
        L_0x00cc:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        L_0x00cf:
            monitor-exit(r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.tz.DefaultNameProvider.getNameSet(java.util.Locale, java.lang.String, java.lang.String):java.lang.String[]");
    }

    public String getShortName(Locale locale, String str, String str2, boolean z) {
        String[] nameSet = getNameSet(locale, str, str2, z);
        if (nameSet == null) {
            return null;
        }
        return nameSet[0];
    }

    public String getName(Locale locale, String str, String str2, boolean z) {
        String[] nameSet = getNameSet(locale, str, str2, z);
        if (nameSet == null) {
            return null;
        }
        return nameSet[1];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b5, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.lang.String[] getNameSet(java.util.Locale r10, java.lang.String r11, java.lang.String r12, boolean r13) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = 0
            if (r10 == 0) goto L_0x00b4
            if (r11 == 0) goto L_0x00b4
            if (r12 != 0) goto L_0x000a
            goto L_0x00b4
        L_0x000a:
            java.lang.String r12 = "Etc/"
            boolean r12 = r11.startsWith(r12)     // Catch:{ all -> 0x00b1 }
            r1 = 4
            if (r12 == 0) goto L_0x0017
            java.lang.String r11 = r11.substring(r1)     // Catch:{ all -> 0x00b1 }
        L_0x0017:
            java.util.HashMap<java.util.Locale, java.util.Map<java.lang.String, java.util.Map<java.lang.Boolean, java.lang.Object>>> r12 = r9.iByLocaleCache2     // Catch:{ all -> 0x00b1 }
            java.lang.Object r12 = r12.get(r10)     // Catch:{ all -> 0x00b1 }
            java.util.Map r12 = (java.util.Map) r12     // Catch:{ all -> 0x00b1 }
            if (r12 != 0) goto L_0x002b
            java.util.HashMap<java.util.Locale, java.util.Map<java.lang.String, java.util.Map<java.lang.Boolean, java.lang.Object>>> r12 = r9.iByLocaleCache2     // Catch:{ all -> 0x00b1 }
            java.util.HashMap r2 = r9.createCache()     // Catch:{ all -> 0x00b1 }
            r12.put(r10, r2)     // Catch:{ all -> 0x00b1 }
            r12 = r2
        L_0x002b:
            java.lang.Object r2 = r12.get(r11)     // Catch:{ all -> 0x00b1 }
            java.util.Map r2 = (java.util.Map) r2     // Catch:{ all -> 0x00b1 }
            if (r2 != 0) goto L_0x00a3
            java.util.HashMap r2 = r9.createCache()     // Catch:{ all -> 0x00b1 }
            r12.put(r11, r2)     // Catch:{ all -> 0x00b1 }
            java.util.Locale r12 = java.util.Locale.ENGLISH     // Catch:{ all -> 0x00b1 }
            java.text.DateFormatSymbols r12 = org.joda.time.DateTimeUtils.getDateFormatSymbols(r12)     // Catch:{ all -> 0x00b1 }
            java.lang.String[][] r12 = r12.getZoneStrings()     // Catch:{ all -> 0x00b1 }
            int r3 = r12.length     // Catch:{ all -> 0x00b1 }
            r4 = 0
            r5 = 0
        L_0x0047:
            r6 = 5
            if (r5 >= r3) goto L_0x005d
            r7 = r12[r5]     // Catch:{ all -> 0x00b1 }
            if (r7 == 0) goto L_0x005a
            int r8 = r7.length     // Catch:{ all -> 0x00b1 }
            if (r8 < r6) goto L_0x005a
            r8 = r7[r4]     // Catch:{ all -> 0x00b1 }
            boolean r8 = r11.equals(r8)     // Catch:{ all -> 0x00b1 }
            if (r8 == 0) goto L_0x005a
            goto L_0x005e
        L_0x005a:
            int r5 = r5 + 1
            goto L_0x0047
        L_0x005d:
            r7 = r0
        L_0x005e:
            java.text.DateFormatSymbols r10 = org.joda.time.DateTimeUtils.getDateFormatSymbols(r10)     // Catch:{ all -> 0x00b1 }
            java.lang.String[][] r10 = r10.getZoneStrings()     // Catch:{ all -> 0x00b1 }
            int r12 = r10.length     // Catch:{ all -> 0x00b1 }
            r3 = 0
        L_0x0068:
            if (r3 >= r12) goto L_0x007e
            r5 = r10[r3]     // Catch:{ all -> 0x00b1 }
            if (r5 == 0) goto L_0x007b
            int r8 = r5.length     // Catch:{ all -> 0x00b1 }
            if (r8 < r6) goto L_0x007b
            r8 = r5[r4]     // Catch:{ all -> 0x00b1 }
            boolean r8 = r11.equals(r8)     // Catch:{ all -> 0x00b1 }
            if (r8 == 0) goto L_0x007b
            r0 = r5
            goto L_0x007e
        L_0x007b:
            int r3 = r3 + 1
            goto L_0x0068
        L_0x007e:
            if (r7 == 0) goto L_0x00a3
            if (r0 == 0) goto L_0x00a3
            java.lang.Boolean r10 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x00b1 }
            r11 = 2
            java.lang.String[] r12 = new java.lang.String[r11]     // Catch:{ all -> 0x00b1 }
            r3 = r0[r11]     // Catch:{ all -> 0x00b1 }
            r12[r4] = r3     // Catch:{ all -> 0x00b1 }
            r3 = 1
            r5 = r0[r3]     // Catch:{ all -> 0x00b1 }
            r12[r3] = r5     // Catch:{ all -> 0x00b1 }
            r2.put(r10, r12)     // Catch:{ all -> 0x00b1 }
            java.lang.Boolean r10 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00b1 }
            java.lang.String[] r11 = new java.lang.String[r11]     // Catch:{ all -> 0x00b1 }
            r12 = r0[r1]     // Catch:{ all -> 0x00b1 }
            r11[r4] = r12     // Catch:{ all -> 0x00b1 }
            r12 = 3
            r12 = r0[r12]     // Catch:{ all -> 0x00b1 }
            r11[r3] = r12     // Catch:{ all -> 0x00b1 }
            r2.put(r10, r11)     // Catch:{ all -> 0x00b1 }
        L_0x00a3:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r13)     // Catch:{ all -> 0x00b1 }
            java.lang.Object r10 = r2.get(r10)     // Catch:{ all -> 0x00b1 }
            java.lang.String[] r10 = (java.lang.String[]) r10     // Catch:{ all -> 0x00b1 }
            java.lang.String[] r10 = (java.lang.String[]) r10     // Catch:{ all -> 0x00b1 }
            monitor-exit(r9)
            return r10
        L_0x00b1:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        L_0x00b4:
            monitor-exit(r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.tz.DefaultNameProvider.getNameSet(java.util.Locale, java.lang.String, java.lang.String, boolean):java.lang.String[]");
    }

    private HashMap createCache() {
        return new HashMap(7);
    }
}

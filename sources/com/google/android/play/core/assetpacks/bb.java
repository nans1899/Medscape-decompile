package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.google.android.play.core.internal.aa;
import com.google.android.play.core.internal.cd;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

final class bb {
    private static final aa a = new aa("AssetPackStorage");
    private static final long b = TimeUnit.DAYS.toMillis(14);
    private static final long c = TimeUnit.DAYS.toMillis(28);
    private final Context d;
    private final dl e;

    bb(Context context, dl dlVar) {
        this.d = context;
        this.e = dlVar;
    }

    private final File a(String str, int i) {
        return new File(g(str), String.valueOf(i));
    }

    private static List<String> a(PackageInfo packageInfo, String str) {
        ArrayList arrayList = new ArrayList();
        int i = (-Arrays.binarySearch(packageInfo.splitNames, str)) - 1;
        while (i < packageInfo.splitNames.length && packageInfo.splitNames[i].startsWith(str)) {
            arrayList.add(packageInfo.applicationInfo.splitSourceDirs[i]);
            i++;
        }
        return arrayList;
    }

    private static void a(File file) {
        if (file.listFiles() != null && file.listFiles().length > 1) {
            long b2 = b(file);
            for (File file2 : file.listFiles()) {
                if (!file2.getName().equals(String.valueOf(b2)) && !file2.getName().equals("stale.tmp")) {
                    c(file2);
                }
            }
        }
    }

    private static long b(File file) {
        if (file.exists()) {
            ArrayList arrayList = new ArrayList();
            try {
                for (File file2 : file.listFiles()) {
                    if (!file2.getName().equals("stale.tmp")) {
                        arrayList.add(Long.valueOf(file2.getName()));
                    }
                }
            } catch (NumberFormatException e2) {
                a.a((Throwable) e2, "Corrupt asset pack directories.", new Object[0]);
            }
            if (!arrayList.isEmpty()) {
                Collections.sort(arrayList);
                return ((Long) arrayList.get(arrayList.size() - 1)).longValue();
            }
        }
        return -1;
    }

    private static boolean c(File file) {
        File[] listFiles = file.listFiles();
        boolean z = true;
        if (listFiles != null) {
            for (File c2 : listFiles) {
                if (!c(c2)) {
                    z = false;
                }
            }
        }
        if (file.delete()) {
            return z;
        }
        return false;
    }

    private static long d(File file) {
        if (!file.isDirectory()) {
            return file.length();
        }
        File[] listFiles = file.listFiles();
        long j = 0;
        if (listFiles != null) {
            for (File d2 : listFiles) {
                j += d(d2);
            }
        }
        return j;
    }

    private final File g(String str) {
        return new File(i(), str);
    }

    private final File g(String str, int i, long j) {
        return new File(c(str, i, j), "merge.tmp");
    }

    private final List<File> g() {
        ArrayList arrayList = new ArrayList();
        try {
            if (!i().exists() || i().listFiles() == null) {
                return arrayList;
            }
            for (File file : i().listFiles()) {
                if (!file.getCanonicalPath().equals(h().getCanonicalPath())) {
                    arrayList.add(file);
                }
            }
            return arrayList;
        } catch (IOException e2) {
            a.b("Could not process directory while scanning installed packs. %s", e2);
        }
    }

    private final File h() {
        return new File(i(), "_tmp");
    }

    private final File h(String str, int i, long j) {
        return new File(new File(new File(h(), str), String.valueOf(i)), String.valueOf(j));
    }

    private final File i() {
        return new File(this.d.getFilesDir(), "assetpacks");
    }

    /* access modifiers changed from: package-private */
    public final long a() {
        return d(i());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.play.core.assetpacks.AssetLocation a(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            android.content.Context r2 = r8.d     // Catch:{ NameNotFoundException -> 0x0013 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0013 }
            android.content.Context r3 = r8.d     // Catch:{ NameNotFoundException -> 0x0013 }
            java.lang.String r3 = r3.getPackageName()     // Catch:{ NameNotFoundException -> 0x0013 }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r3, r0)     // Catch:{ NameNotFoundException -> 0x0013 }
            goto L_0x001d
        L_0x0013:
            com.google.android.play.core.internal.aa r2 = a
            java.lang.Object[] r3 = new java.lang.Object[r0]
            java.lang.String r4 = "Could not find PackageInfo."
            r2.b(r4, r3)
            r2 = r1
        L_0x001d:
            r3 = 1
            if (r2 == 0) goto L_0x0086
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 21
            if (r5 >= r6) goto L_0x0033
            android.content.pm.ApplicationInfo r2 = r2.applicationInfo
            java.lang.String r2 = r2.sourceDir
            r4.add(r2)
            goto L_0x0087
        L_0x0033:
            java.lang.String[] r5 = r2.splitNames
            if (r5 == 0) goto L_0x0059
            android.content.pm.ApplicationInfo r5 = r2.applicationInfo
            java.lang.String[] r5 = r5.splitSourceDirs
            if (r5 != 0) goto L_0x003e
            goto L_0x0059
        L_0x003e:
            java.lang.String[] r5 = r2.splitNames
            int r5 = java.util.Arrays.binarySearch(r5, r9)
            if (r5 >= 0) goto L_0x0052
            com.google.android.play.core.internal.aa r5 = a
            java.lang.Object[] r6 = new java.lang.Object[r3]
            r6[r0] = r9
            java.lang.String r7 = "Asset Pack '%s' is not installed."
            r5.a(r7, r6)
            goto L_0x0064
        L_0x0052:
            android.content.pm.ApplicationInfo r6 = r2.applicationInfo
            java.lang.String[] r6 = r6.splitSourceDirs
            r5 = r6[r5]
            goto L_0x0065
        L_0x0059:
            com.google.android.play.core.internal.aa r5 = a
            java.lang.Object[] r6 = new java.lang.Object[r3]
            r6[r0] = r9
            java.lang.String r7 = "No splits present for package %s."
            r5.a(r7, r6)
        L_0x0064:
            r5 = r1
        L_0x0065:
            if (r5 != 0) goto L_0x0071
            android.content.pm.ApplicationInfo r5 = r2.applicationInfo
            java.lang.String r5 = r5.sourceDir
            r4.add(r5)
            java.lang.String r5 = "config."
            goto L_0x007e
        L_0x0071:
            r4.add(r5)
            java.lang.String r5 = java.lang.String.valueOf(r9)
            java.lang.String r6 = ".config."
            java.lang.String r5 = r5.concat(r6)
        L_0x007e:
            java.util.List r2 = a((android.content.pm.PackageInfo) r2, (java.lang.String) r5)
            r4.addAll(r2)
            goto L_0x0087
        L_0x0086:
            r4 = r1
        L_0x0087:
            if (r4 == 0) goto L_0x00cc
            java.io.File r2 = new java.io.File
            java.lang.String r5 = "assets"
            r2.<init>(r5, r10)
            java.lang.String r2 = r2.getPath()
            java.util.Iterator r5 = r4.iterator()
        L_0x0098:
            boolean r6 = r5.hasNext()
            r7 = 2
            if (r6 == 0) goto L_0x00bc
            java.lang.Object r6 = r5.next()
            java.lang.String r6 = (java.lang.String) r6
            com.google.android.play.core.assetpacks.AssetLocation r6 = com.google.android.play.core.assetpacks.dd.a((java.lang.String) r6, (java.lang.String) r2)     // Catch:{ IOException -> 0x00ad }
            if (r6 == 0) goto L_0x0098
            r1 = r6
            goto L_0x00cc
        L_0x00ad:
            r9 = move-exception
            com.google.android.play.core.internal.aa r2 = a
            java.lang.Object[] r4 = new java.lang.Object[r7]
            r4[r0] = r6
            r4[r3] = r10
            java.lang.String r10 = "Failed to parse APK file '%s' looking for asset '%s'."
            r2.a((java.lang.Throwable) r9, (java.lang.String) r10, (java.lang.Object[]) r4)
            goto L_0x00cc
        L_0x00bc:
            com.google.android.play.core.internal.aa r2 = a
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r0] = r10
            r5[r3] = r9
            r5[r7] = r4
            java.lang.String r9 = "The asset %s is not present in Asset Pack %s. Searched in APKs: %s"
            r2.a(r9, r5)
        L_0x00cc:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.assetpacks.bb.a(java.lang.String, java.lang.String):com.google.android.play.core.assetpacks.AssetLocation");
    }

    /* access modifiers changed from: package-private */
    public final AssetLocation a(String str, String str2, AssetPackLocation assetPackLocation) {
        File file = new File(assetPackLocation.assetsPath(), str2);
        if (file.exists()) {
            return AssetLocation.a(file.getPath(), 0, file.length());
        }
        a.a("The asset %s is not present in Asset Pack %s. Searched in folder: %s", str2, str, assetPackLocation.assetsPath());
        return null;
    }

    /* access modifiers changed from: package-private */
    public final File a(String str, int i, long j) {
        return new File(a(str, i), String.valueOf(j));
    }

    /* access modifiers changed from: package-private */
    public final File a(String str, int i, long j, String str2) {
        return new File(new File(new File(h(str, i, j), "_slices"), "_unverified"), str2);
    }

    /* access modifiers changed from: package-private */
    public final void a(String str, int i, long j, int i2) throws IOException {
        File g = g(str, i, j);
        Properties properties = new Properties();
        properties.put("numberOfMerges", String.valueOf(i2));
        g.getParentFile().mkdirs();
        g.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(g);
        properties.store(fileOutputStream, (String) null);
        fileOutputStream.close();
    }

    /* access modifiers changed from: package-private */
    public final void a(List<String> list) {
        int a2 = this.e.a();
        List<File> g = g();
        int size = g.size();
        for (int i = 0; i < size; i++) {
            File file = g.get(i);
            if (!list.contains(file.getName()) && b(file) != ((long) a2)) {
                c(file);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean a(String str) {
        try {
            return c(str) != null;
        } catch (IOException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public final AssetPackLocation b(String str) throws IOException {
        String c2 = c(str);
        if (c2 == null) {
            return null;
        }
        File file = new File(c2, "assets");
        if (file.isDirectory()) {
            return AssetPackLocation.a(c2, file.getCanonicalPath());
        }
        a.b("Failed to find assets directory: %s", file);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final File b(String str, int i, long j) {
        return new File(a(str, i, j), "_metadata");
    }

    /* access modifiers changed from: package-private */
    public final File b(String str, int i, long j, String str2) {
        return new File(new File(new File(h(str, i, j), "_slices"), "_verified"), str2);
    }

    /* access modifiers changed from: package-private */
    public final Map<String, AssetPackLocation> b() {
        HashMap hashMap = new HashMap();
        try {
            for (File next : g()) {
                AssetPackLocation b2 = b(next.getName());
                if (b2 != null) {
                    hashMap.put(next.getName(), b2);
                }
            }
        } catch (IOException e2) {
            a.b("Could not process directory while scanning installed packs: %s", e2);
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public final File c(String str, int i, long j) {
        return new File(h(str, i, j), "_packs");
    }

    /* access modifiers changed from: package-private */
    public final File c(String str, int i, long j, String str2) {
        return new File(f(str, i, j, str2), "checkpoint.dat");
    }

    /* access modifiers changed from: package-private */
    public final String c(String str) throws IOException {
        int length;
        File file = new File(i(), str);
        if (!file.exists()) {
            a.a("Pack not found with pack name: %s", str);
            return null;
        }
        File file2 = new File(file, String.valueOf(this.e.a()));
        if (!file2.exists()) {
            a.a("Pack not found with pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
            return null;
        }
        File[] listFiles = file2.listFiles();
        if (listFiles == null || (length = listFiles.length) == 0) {
            a.a("No pack version found for pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
            return null;
        } else if (length <= 1) {
            return listFiles[0].getCanonicalPath();
        } else {
            a.b("Multiple pack versions found for pack name: %s app version: %s", str, Integer.valueOf(this.e.a()));
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        List<File> g = g();
        int size = g.size();
        for (int i = 0; i < size; i++) {
            File file = g.get(i);
            if (file.listFiles() != null) {
                a(file);
                long b2 = b(file);
                if (((long) this.e.a()) != b2) {
                    try {
                        new File(new File(file, String.valueOf(b2)), "stale.tmp").createNewFile();
                    } catch (IOException unused) {
                        a.b("Could not write staleness marker.", new Object[0]);
                    }
                }
                for (File a2 : file.listFiles()) {
                    a(a2);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final int d(String str, int i, long j) throws IOException {
        File g = g(str, i, j);
        if (!g.exists()) {
            return 0;
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(g);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("numberOfMerges") != null) {
                try {
                    return Integer.parseInt(properties.getProperty("numberOfMerges"));
                } catch (NumberFormatException e2) {
                    throw new by("Merge checkpoint file corrupt.", (Exception) e2);
                }
            } else {
                throw new by("Merge checkpoint file corrupt.");
            }
        } catch (Throwable th) {
            cd.a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: package-private */
    public final File d(String str, int i, long j, String str2) {
        return new File(f(str, i, j, str2), "checkpoint_ext.dat");
    }

    /* access modifiers changed from: package-private */
    public final void d() {
        List<File> g = g();
        int size = g.size();
        for (int i = 0; i < size; i++) {
            File file = g.get(i);
            if (file.listFiles() != null) {
                for (File file2 : file.listFiles()) {
                    File file3 = new File(file2, "stale.tmp");
                    if (file3.exists() && System.currentTimeMillis() - file3.lastModified() > c) {
                        c(file2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean d(String str) {
        if (g(str).exists()) {
            return c(g(str));
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public final int e(String str) {
        return (int) b(g(str));
    }

    /* access modifiers changed from: package-private */
    public final File e(String str, int i, long j) {
        return new File(new File(h(str, i, j), "_slices"), "_metadata");
    }

    /* access modifiers changed from: package-private */
    public final File e(String str, int i, long j, String str2) {
        return new File(f(str, i, j, str2), "slice.zip");
    }

    /* access modifiers changed from: package-private */
    public final void e() {
        if (h().exists()) {
            for (File file : h().listFiles()) {
                if (System.currentTimeMillis() - file.lastModified() > b) {
                    c(file);
                } else {
                    a(file);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final long f(String str) {
        return b(a(str, e(str)));
    }

    /* access modifiers changed from: package-private */
    public final File f(String str, int i, long j, String str2) {
        return new File(e(str, i, j), str2);
    }

    /* access modifiers changed from: package-private */
    public final void f() {
        c(i());
    }

    /* access modifiers changed from: package-private */
    public final void f(String str, int i, long j) {
        if (h(str, i, j).exists()) {
            c(h(str, i, j));
        }
    }
}

package com.google.android.play.core.splitcompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import com.google.android.play.core.internal.an;
import com.google.android.play.core.internal.ao;
import com.google.android.play.core.internal.aq;
import com.google.android.play.core.internal.bj;
import com.google.android.play.core.splitinstall.w;
import com.google.android.play.core.splitinstall.y;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class SplitCompat {
    private static final AtomicReference<SplitCompat> a = new AtomicReference<>((Object) null);
    /* access modifiers changed from: private */
    public final e b;
    private final Set<String> c = new HashSet();
    private final a d;

    private SplitCompat(Context context) {
        try {
            e eVar = new e(context);
            this.b = eVar;
            this.d = new a(eVar);
        } catch (PackageManager.NameNotFoundException e) {
            throw new bj("Failed to initialize FileStorage", e);
        }
    }

    public static boolean a() {
        return a.get() != null;
    }

    public static boolean a(Context context) {
        return a(context, true);
    }

    private static boolean a(Context context, boolean z) {
        if (!b()) {
            boolean compareAndSet = a.compareAndSet((Object) null, new SplitCompat(context));
            SplitCompat splitCompat = a.get();
            if (compareAndSet) {
                w.a.a(new an(context, d.a(), new ao(context, splitCompat.b, new d(), (byte[]) null), splitCompat.b, new aq(), (byte[]) null));
                y.a(new n(splitCompat));
                d.a().execute(new o(context));
            }
            try {
                splitCompat.b(context, z);
                return true;
            } catch (Exception e) {
                Log.e("SplitCompat", "Error installing additional splits", e);
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:79:0x01a4 A[SYNTHETIC, Splitter:B:79:0x01a4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void b(android.content.Context r9, boolean r10) throws java.io.IOException {
        /*
            r8 = this;
            monitor-enter(r8)
            if (r10 != 0) goto L_0x0010
            java.util.concurrent.Executor r0 = com.google.android.play.core.splitcompat.d.a()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.p r1 = new com.google.android.play.core.splitcompat.p     // Catch:{ all -> 0x024a }
            r1.<init>(r8)     // Catch:{ all -> 0x024a }
            r0.execute(r1)     // Catch:{ all -> 0x024a }
            goto L_0x0015
        L_0x0010:
            com.google.android.play.core.splitcompat.e r0 = r8.b     // Catch:{ all -> 0x024a }
            r0.a()     // Catch:{ all -> 0x024a }
        L_0x0015:
            java.lang.String r0 = r9.getPackageName()     // Catch:{ all -> 0x024a }
            r1 = 0
            android.content.pm.PackageManager r2 = r9.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0238 }
            android.content.pm.PackageInfo r2 = r2.getPackageInfo(r0, r1)     // Catch:{ NameNotFoundException -> 0x0238 }
            java.lang.String[] r3 = r2.splitNames     // Catch:{ NameNotFoundException -> 0x0238 }
            if (r3 != 0) goto L_0x002c
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ NameNotFoundException -> 0x0238 }
            r2.<init>()     // Catch:{ NameNotFoundException -> 0x0238 }
            goto L_0x0032
        L_0x002c:
            java.lang.String[] r2 = r2.splitNames     // Catch:{ NameNotFoundException -> 0x0238 }
            java.util.List r2 = java.util.Arrays.asList(r2)     // Catch:{ NameNotFoundException -> 0x0238 }
        L_0x0032:
            com.google.android.play.core.splitcompat.e r0 = r8.b     // Catch:{ all -> 0x024a }
            java.util.Set r0 = r0.d()     // Catch:{ all -> 0x024a }
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x024a }
            r3.<init>()     // Catch:{ all -> 0x024a }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x024a }
        L_0x0041:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x024a }
            if (r5 == 0) goto L_0x0066
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.r r5 = (com.google.android.play.core.splitcompat.r) r5     // Catch:{ all -> 0x024a }
            java.lang.String r5 = r5.b()     // Catch:{ all -> 0x024a }
            boolean r6 = r2.contains(r5)     // Catch:{ all -> 0x024a }
            if (r6 == 0) goto L_0x0041
            if (r10 != 0) goto L_0x005d
            r3.add(r5)     // Catch:{ all -> 0x024a }
            goto L_0x0062
        L_0x005d:
            com.google.android.play.core.splitcompat.e r6 = r8.b     // Catch:{ all -> 0x024a }
            r6.f(r5)     // Catch:{ all -> 0x024a }
        L_0x0062:
            r4.remove()     // Catch:{ all -> 0x024a }
            goto L_0x0041
        L_0x0066:
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x024a }
            if (r4 != 0) goto L_0x0078
            java.util.concurrent.Executor r4 = com.google.android.play.core.splitcompat.d.a()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.q r5 = new com.google.android.play.core.splitcompat.q     // Catch:{ all -> 0x024a }
            r5.<init>(r8, r3)     // Catch:{ all -> 0x024a }
            r4.execute(r5)     // Catch:{ all -> 0x024a }
        L_0x0078:
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x024a }
            r3.<init>()     // Catch:{ all -> 0x024a }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ all -> 0x024a }
        L_0x0081:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x024a }
            if (r5 == 0) goto L_0x009b
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.r r5 = (com.google.android.play.core.splitcompat.r) r5     // Catch:{ all -> 0x024a }
            java.lang.String r5 = r5.b()     // Catch:{ all -> 0x024a }
            boolean r6 = com.google.android.play.core.splitinstall.z.b(r5)     // Catch:{ all -> 0x024a }
            if (r6 != 0) goto L_0x0081
            r3.add(r5)     // Catch:{ all -> 0x024a }
            goto L_0x0081
        L_0x009b:
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x024a }
        L_0x009f:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x024a }
            if (r4 == 0) goto L_0x00b5
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x024a }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x024a }
            boolean r5 = com.google.android.play.core.splitinstall.z.b(r4)     // Catch:{ all -> 0x024a }
            if (r5 != 0) goto L_0x009f
            r3.add(r4)     // Catch:{ all -> 0x024a }
            goto L_0x009f
        L_0x00b5:
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ all -> 0x024a }
            int r4 = r0.size()     // Catch:{ all -> 0x024a }
            r2.<init>(r4)     // Catch:{ all -> 0x024a }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x024a }
        L_0x00c2:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x024a }
            if (r4 == 0) goto L_0x00f8
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.r r4 = (com.google.android.play.core.splitcompat.r) r4     // Catch:{ all -> 0x024a }
            java.lang.String r5 = r4.b()     // Catch:{ all -> 0x024a }
            boolean r5 = com.google.android.play.core.splitinstall.z.a(r5)     // Catch:{ all -> 0x024a }
            if (r5 != 0) goto L_0x00f4
            java.lang.String r5 = r4.b()     // Catch:{ all -> 0x024a }
            boolean r6 = com.google.android.play.core.splitinstall.z.a(r5)     // Catch:{ all -> 0x024a }
            if (r6 != 0) goto L_0x00ec
            java.lang.String r6 = "\\.config\\."
            r7 = 2
            java.lang.String[] r5 = r5.split(r6, r7)     // Catch:{ all -> 0x024a }
            r5 = r5[r1]     // Catch:{ all -> 0x024a }
            goto L_0x00ee
        L_0x00ec:
            java.lang.String r5 = ""
        L_0x00ee:
            boolean r5 = r3.contains(r5)     // Catch:{ all -> 0x024a }
            if (r5 == 0) goto L_0x00c2
        L_0x00f4:
            r2.add(r4)     // Catch:{ all -> 0x024a }
            goto L_0x00c2
        L_0x00f8:
            com.google.android.play.core.splitcompat.m r0 = new com.google.android.play.core.splitcompat.m     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.e r1 = r8.b     // Catch:{ all -> 0x024a }
            r0.<init>(r1)     // Catch:{ all -> 0x024a }
            com.google.android.play.core.internal.ap r1 = com.google.android.play.core.internal.aq.a()     // Catch:{ all -> 0x024a }
            java.lang.ClassLoader r3 = r9.getClassLoader()     // Catch:{ all -> 0x024a }
            if (r10 == 0) goto L_0x0111
            java.util.Set r0 = r0.a()     // Catch:{ all -> 0x024a }
            r1.a(r3, r0)     // Catch:{ all -> 0x024a }
            goto L_0x012f
        L_0x0111:
            java.util.Iterator r4 = r2.iterator()     // Catch:{ all -> 0x024a }
        L_0x0115:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x024a }
            if (r5 == 0) goto L_0x012f
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.r r5 = (com.google.android.play.core.splitcompat.r) r5     // Catch:{ all -> 0x024a }
            java.util.Set r5 = r0.a(r5)     // Catch:{ all -> 0x024a }
            if (r5 != 0) goto L_0x012b
            r4.remove()     // Catch:{ all -> 0x024a }
            goto L_0x0115
        L_0x012b:
            r1.a(r3, r5)     // Catch:{ all -> 0x024a }
            goto L_0x0115
        L_0x012f:
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x024a }
            r0.<init>()     // Catch:{ all -> 0x024a }
            java.util.Iterator r4 = r2.iterator()     // Catch:{ all -> 0x024a }
        L_0x0138:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x024a }
            if (r5 == 0) goto L_0x01ad
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.r r5 = (com.google.android.play.core.splitcompat.r) r5     // Catch:{ all -> 0x024a }
            java.util.zip.ZipFile r6 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x01a0 }
            java.io.File r7 = r5.a()     // Catch:{ IOException -> 0x01a0 }
            r6.<init>(r7)     // Catch:{ IOException -> 0x01a0 }
            java.lang.String r7 = "classes.dex"
            java.util.zip.ZipEntry r7 = r6.getEntry(r7)     // Catch:{ IOException -> 0x019e }
            r6.close()     // Catch:{ IOException -> 0x019e }
            if (r7 == 0) goto L_0x0196
            com.google.android.play.core.splitcompat.e r6 = r8.b     // Catch:{ all -> 0x024a }
            java.lang.String r7 = r5.b()     // Catch:{ all -> 0x024a }
            java.io.File r6 = r6.c((java.lang.String) r7)     // Catch:{ all -> 0x024a }
            java.io.File r7 = r5.a()     // Catch:{ all -> 0x024a }
            boolean r6 = r1.a(r3, r6, r7, r10)     // Catch:{ all -> 0x024a }
            if (r6 == 0) goto L_0x016d
            goto L_0x0196
        L_0x016d:
            java.io.File r5 = r5.a()     // Catch:{ all -> 0x024a }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x024a }
            java.lang.String r6 = java.lang.String.valueOf(r5)     // Catch:{ all -> 0x024a }
            int r6 = r6.length()     // Catch:{ all -> 0x024a }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x024a }
            int r6 = r6 + 24
            r7.<init>(r6)     // Catch:{ all -> 0x024a }
            java.lang.String r6 = "split was not installed "
            r7.append(r6)     // Catch:{ all -> 0x024a }
            r7.append(r5)     // Catch:{ all -> 0x024a }
            java.lang.String r5 = "SplitCompat"
            java.lang.String r6 = r7.toString()     // Catch:{ all -> 0x024a }
            android.util.Log.w(r5, r6)     // Catch:{ all -> 0x024a }
            goto L_0x0138
        L_0x0196:
            java.io.File r5 = r5.a()     // Catch:{ all -> 0x024a }
            r0.add(r5)     // Catch:{ all -> 0x024a }
            goto L_0x0138
        L_0x019e:
            r9 = move-exception
            goto L_0x01a2
        L_0x01a0:
            r9 = move-exception
            r6 = 0
        L_0x01a2:
            if (r6 == 0) goto L_0x01ac
            r6.close()     // Catch:{ IOException -> 0x01a8 }
            goto L_0x01ac
        L_0x01a8:
            r10 = move-exception
            com.google.android.play.core.internal.cd.a(r9, r10)     // Catch:{ all -> 0x024a }
        L_0x01ac:
            throw r9     // Catch:{ all -> 0x024a }
        L_0x01ad:
            com.google.android.play.core.splitcompat.a.b(r9, r0)     // Catch:{ all -> 0x024a }
            java.util.HashSet r9 = new java.util.HashSet     // Catch:{ all -> 0x024a }
            r9.<init>()     // Catch:{ all -> 0x024a }
            java.util.Iterator r10 = r2.iterator()     // Catch:{ all -> 0x024a }
        L_0x01b9:
            boolean r1 = r10.hasNext()     // Catch:{ all -> 0x024a }
            if (r1 == 0) goto L_0x022a
            java.lang.Object r1 = r10.next()     // Catch:{ all -> 0x024a }
            com.google.android.play.core.splitcompat.r r1 = (com.google.android.play.core.splitcompat.r) r1     // Catch:{ all -> 0x024a }
            java.io.File r2 = r1.a()     // Catch:{ all -> 0x024a }
            boolean r2 = r0.contains(r2)     // Catch:{ all -> 0x024a }
            if (r2 == 0) goto L_0x0200
            java.lang.String r2 = r1.b()     // Catch:{ all -> 0x024a }
            java.lang.String r3 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x024a }
            int r3 = r3.length()     // Catch:{ all -> 0x024a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x024a }
            int r3 = r3 + 30
            r4.<init>(r3)     // Catch:{ all -> 0x024a }
            java.lang.String r3 = "Split '"
            r4.append(r3)     // Catch:{ all -> 0x024a }
            r4.append(r2)     // Catch:{ all -> 0x024a }
            java.lang.String r2 = "' installation emulated"
            r4.append(r2)     // Catch:{ all -> 0x024a }
            java.lang.String r2 = "SplitCompat"
            java.lang.String r3 = r4.toString()     // Catch:{ all -> 0x024a }
            android.util.Log.d(r2, r3)     // Catch:{ all -> 0x024a }
            java.lang.String r1 = r1.b()     // Catch:{ all -> 0x024a }
            r9.add(r1)     // Catch:{ all -> 0x024a }
            goto L_0x01b9
        L_0x0200:
            java.lang.String r1 = r1.b()     // Catch:{ all -> 0x024a }
            java.lang.String r2 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x024a }
            int r2 = r2.length()     // Catch:{ all -> 0x024a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x024a }
            int r2 = r2 + 35
            r3.<init>(r2)     // Catch:{ all -> 0x024a }
            java.lang.String r2 = "Split '"
            r3.append(r2)     // Catch:{ all -> 0x024a }
            r3.append(r1)     // Catch:{ all -> 0x024a }
            java.lang.String r1 = "' installation not emulated."
            r3.append(r1)     // Catch:{ all -> 0x024a }
            java.lang.String r1 = "SplitCompat"
            java.lang.String r2 = r3.toString()     // Catch:{ all -> 0x024a }
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x024a }
            goto L_0x01b9
        L_0x022a:
            java.util.Set<java.lang.String> r10 = r8.c     // Catch:{ all -> 0x024a }
            monitor-enter(r10)     // Catch:{ all -> 0x024a }
            java.util.Set<java.lang.String> r0 = r8.c     // Catch:{ all -> 0x0235 }
            r0.addAll(r9)     // Catch:{ all -> 0x0235 }
            monitor-exit(r10)     // Catch:{ all -> 0x0235 }
            monitor-exit(r8)
            return
        L_0x0235:
            r9 = move-exception
            monitor-exit(r10)     // Catch:{ all -> 0x0235 }
            throw r9     // Catch:{ all -> 0x024a }
        L_0x0238:
            r9 = move-exception
            java.io.IOException r10 = new java.io.IOException     // Catch:{ all -> 0x024a }
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x024a }
            r2[r1] = r0     // Catch:{ all -> 0x024a }
            java.lang.String r0 = "Cannot load data for application '%s'"
            java.lang.String r0 = java.lang.String.format(r0, r2)     // Catch:{ all -> 0x024a }
            r10.<init>(r0, r9)     // Catch:{ all -> 0x024a }
            throw r10     // Catch:{ all -> 0x024a }
        L_0x024a:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitcompat.SplitCompat.b(android.content.Context, boolean):void");
    }

    private static boolean b() {
        return Build.VERSION.SDK_INT < 21;
    }

    /* access modifiers changed from: private */
    public final Set<String> c() {
        HashSet hashSet;
        synchronized (this.c) {
            hashSet = new HashSet(this.c);
        }
        return hashSet;
    }

    public static boolean install(Context context) {
        return a(context, false);
    }

    public static boolean installActivity(Context context) {
        if (b()) {
            return false;
        }
        SplitCompat splitCompat = a.get();
        if (splitCompat != null) {
            return splitCompat.d.a(context, splitCompat.c());
        }
        throw new IllegalStateException("SplitCompat.installActivity can only be called if SplitCompat.install is first called at startup on application context.");
    }
}

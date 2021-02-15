package com.google.android.play.core.missingsplits;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.play.core.internal.aa;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

final class b implements MissingSplitsManager {
    private static final aa a = new aa("MissingSplitsManagerImpl");
    private final Context b;
    private final Runtime c;
    private final a d;
    private final AtomicReference<Boolean> e;

    b(Context context, Runtime runtime, a aVar, AtomicReference<Boolean> atomicReference) {
        this.b = context;
        this.c = runtime;
        this.d = aVar;
        this.e = atomicReference;
    }

    private final boolean a() {
        try {
            ApplicationInfo applicationInfo = this.b.getPackageManager().getApplicationInfo(this.b.getPackageName(), 128);
            return (applicationInfo == null || applicationInfo.metaData == null || !Boolean.TRUE.equals(applicationInfo.metaData.get("com.android.vending.splits.required"))) ? false : true;
        } catch (PackageManager.NameNotFoundException unused) {
            a.d("App '%s' is not found in the PackageManager", this.b.getPackageName());
            return false;
        }
    }

    private static boolean a(Set<String> set) {
        return set.isEmpty() || (set.size() == 1 && set.contains(""));
    }

    private final Set<String> b() {
        if (Build.VERSION.SDK_INT < 21) {
            return Collections.emptySet();
        }
        try {
            PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0);
            HashSet hashSet = new HashSet();
            if (packageInfo == null || packageInfo.splitNames == null) {
                return hashSet;
            }
            Collections.addAll(hashSet, packageInfo.splitNames);
            return hashSet;
        } catch (PackageManager.NameNotFoundException unused) {
            a.d("App '%s' is not found in PackageManager", this.b.getPackageName());
            return Collections.emptySet();
        }
    }

    private final List<ActivityManager.AppTask> c() {
        List<ActivityManager.AppTask> appTasks = ((ActivityManager) this.b.getSystemService("activity")).getAppTasks();
        return appTasks == null ? Collections.emptyList() : appTasks;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:27|28) */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        a.d("App '%s' is not found in PackageManager", r8.b.getPackageName());
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006a */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0085 A[Catch:{ NameNotFoundException -> 0x0096 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean disableAppIfMissingRequiredSplits() {
        /*
            r8 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            r2 = 0
            if (r0 < r1) goto L_0x01ce
            java.util.concurrent.atomic.AtomicReference<java.lang.Boolean> r0 = r8.e
            monitor-enter(r0)
            java.util.concurrent.atomic.AtomicReference<java.lang.Boolean> r3 = r8.e     // Catch:{ all -> 0x01cb }
            java.lang.Object r3 = r3.get()     // Catch:{ all -> 0x01cb }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ all -> 0x01cb }
            r4 = 1
            if (r3 == 0) goto L_0x0017
            goto L_0x00af
        L_0x0017:
            java.util.concurrent.atomic.AtomicReference<java.lang.Boolean> r3 = r8.e     // Catch:{ all -> 0x01cb }
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01cb }
            if (r5 < r1) goto L_0x00a7
            android.content.Context r5 = r8.b     // Catch:{ all -> 0x01cb }
            android.content.pm.PackageManager r5 = r5.getPackageManager()     // Catch:{ all -> 0x01cb }
            android.content.Context r6 = r8.b     // Catch:{ NameNotFoundException -> 0x0096 }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ NameNotFoundException -> 0x0096 }
            r7 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r5 = r5.getApplicationInfo(r6, r7)     // Catch:{ NameNotFoundException -> 0x0096 }
            if (r5 == 0) goto L_0x00a7
            android.os.Bundle r6 = r5.metaData     // Catch:{ NameNotFoundException -> 0x0096 }
            if (r6 == 0) goto L_0x00a7
            java.lang.Boolean r6 = java.lang.Boolean.TRUE     // Catch:{ NameNotFoundException -> 0x0096 }
            android.os.Bundle r5 = r5.metaData     // Catch:{ NameNotFoundException -> 0x0096 }
            java.lang.String r7 = "com.android.vending.splits.required"
            java.lang.Object r5 = r5.get(r7)     // Catch:{ NameNotFoundException -> 0x0096 }
            boolean r5 = r6.equals(r5)     // Catch:{ NameNotFoundException -> 0x0096 }
            if (r5 == 0) goto L_0x00a7
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x01cb }
            if (r5 < r1) goto L_0x007b
            android.content.Context r1 = r8.b     // Catch:{ NameNotFoundException -> 0x006a }
            android.content.pm.PackageManager r1 = r1.getPackageManager()     // Catch:{ NameNotFoundException -> 0x006a }
            android.content.Context r5 = r8.b     // Catch:{ NameNotFoundException -> 0x006a }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ NameNotFoundException -> 0x006a }
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r5, r2)     // Catch:{ NameNotFoundException -> 0x006a }
            java.util.HashSet r5 = new java.util.HashSet     // Catch:{ NameNotFoundException -> 0x006a }
            r5.<init>()     // Catch:{ NameNotFoundException -> 0x006a }
            if (r1 == 0) goto L_0x007f
            java.lang.String[] r6 = r1.splitNames     // Catch:{ NameNotFoundException -> 0x006a }
            if (r6 == 0) goto L_0x007f
            java.lang.String[] r1 = r1.splitNames     // Catch:{ NameNotFoundException -> 0x006a }
            java.util.Collections.addAll(r5, r1)     // Catch:{ NameNotFoundException -> 0x006a }
            goto L_0x007f
        L_0x006a:
            com.google.android.play.core.internal.aa r1 = a     // Catch:{ all -> 0x01cb }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x01cb }
            android.content.Context r6 = r8.b     // Catch:{ all -> 0x01cb }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x01cb }
            r5[r2] = r6     // Catch:{ all -> 0x01cb }
            java.lang.String r6 = "App '%s' is not found in PackageManager"
            r1.d(r6, r5)     // Catch:{ all -> 0x01cb }
        L_0x007b:
            java.util.Set r5 = java.util.Collections.emptySet()     // Catch:{ all -> 0x01cb }
        L_0x007f:
            boolean r1 = r5.isEmpty()     // Catch:{ all -> 0x01cb }
            if (r1 != 0) goto L_0x0094
            int r1 = r5.size()     // Catch:{ all -> 0x01cb }
            if (r1 != r4) goto L_0x00a7
            java.lang.String r1 = ""
            boolean r1 = r5.contains(r1)     // Catch:{ all -> 0x01cb }
            if (r1 != 0) goto L_0x0094
            goto L_0x00a7
        L_0x0094:
            r1 = 1
            goto L_0x00a8
        L_0x0096:
            com.google.android.play.core.internal.aa r1 = a     // Catch:{ all -> 0x01cb }
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch:{ all -> 0x01cb }
            android.content.Context r6 = r8.b     // Catch:{ all -> 0x01cb }
            java.lang.String r6 = r6.getPackageName()     // Catch:{ all -> 0x01cb }
            r5[r2] = r6     // Catch:{ all -> 0x01cb }
            java.lang.String r6 = "App '%s' is not found in the PackageManager"
            r1.d(r6, r5)     // Catch:{ all -> 0x01cb }
        L_0x00a7:
            r1 = 0
        L_0x00a8:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ all -> 0x01cb }
            r3.set(r1)     // Catch:{ all -> 0x01cb }
        L_0x00af:
            java.util.concurrent.atomic.AtomicReference<java.lang.Boolean> r1 = r8.e     // Catch:{ all -> 0x01cb }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x01cb }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x01cb }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x01cb }
            monitor-exit(r0)     // Catch:{ all -> 0x01cb }
            if (r1 == 0) goto L_0x01b8
            java.util.List r0 = r8.c()
            java.util.Iterator r0 = r0.iterator()
        L_0x00c6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0108
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$AppTask r1 = (android.app.ActivityManager.AppTask) r1
            android.app.ActivityManager$RecentTaskInfo r3 = r1.getTaskInfo()
            if (r3 == 0) goto L_0x00c6
            android.app.ActivityManager$RecentTaskInfo r3 = r1.getTaskInfo()
            android.content.Intent r3 = r3.baseIntent
            if (r3 == 0) goto L_0x00c6
            android.app.ActivityManager$RecentTaskInfo r3 = r1.getTaskInfo()
            android.content.Intent r3 = r3.baseIntent
            android.content.ComponentName r3 = r3.getComponent()
            if (r3 == 0) goto L_0x00c6
            android.app.ActivityManager$RecentTaskInfo r1 = r1.getTaskInfo()
            android.content.Intent r1 = r1.baseIntent
            android.content.ComponentName r1 = r1.getComponent()
            java.lang.String r1 = r1.getClassName()
            java.lang.Class<com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity> r3 = com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity.class
            java.lang.String r3 = r3.getName()
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x00c6
            goto L_0x01b7
        L_0x0108:
            java.util.List r0 = r8.c()
            java.util.Iterator r0 = r0.iterator()
        L_0x0110:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x016c
            java.lang.Object r1 = r0.next()
            android.app.ActivityManager$AppTask r1 = (android.app.ActivityManager.AppTask) r1
            android.app.ActivityManager$RecentTaskInfo r1 = r1.getTaskInfo()
            if (r1 == 0) goto L_0x0110
            android.content.Intent r3 = r1.baseIntent
            if (r3 == 0) goto L_0x0110
            android.content.Intent r3 = r1.baseIntent
            android.content.ComponentName r3 = r3.getComponent()
            if (r3 == 0) goto L_0x0110
            android.content.Intent r1 = r1.baseIntent
            android.content.ComponentName r1 = r1.getComponent()
            java.lang.String r3 = r1.getClassName()
            java.lang.Class r1 = java.lang.Class.forName(r3)     // Catch:{ ClassNotFoundException -> 0x0152 }
        L_0x013c:
            if (r1 == 0) goto L_0x0110
            java.lang.Class<android.app.Activity> r3 = android.app.Activity.class
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x0150
            java.lang.Class r3 = r1.getSuperclass()
            if (r3 == r1) goto L_0x014e
            r1 = r3
            goto L_0x013c
        L_0x014e:
            r1 = 0
            goto L_0x013c
        L_0x0150:
            r0 = 1
            goto L_0x016d
        L_0x0152:
            com.google.android.play.core.internal.aa r5 = a
            java.lang.Object[] r6 = new java.lang.Object[r4]
            r6[r2] = r3
            java.lang.String r3 = "ClassNotFoundException when scanning class hierarchy of '%s'"
            r5.d(r3, r6)
            android.content.Context r3 = r8.b     // Catch:{ NameNotFoundException -> 0x016a }
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch:{ NameNotFoundException -> 0x016a }
            android.content.pm.ActivityInfo r1 = r3.getActivityInfo(r1, r2)     // Catch:{ NameNotFoundException -> 0x016a }
            if (r1 == 0) goto L_0x0110
            goto L_0x0150
        L_0x016a:
            goto L_0x0110
        L_0x016c:
            r0 = 0
        L_0x016d:
            com.google.android.play.core.missingsplits.a r1 = r8.d
            r1.b()
            java.util.List r1 = r8.c()
            java.util.Iterator r1 = r1.iterator()
        L_0x017a:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x018a
            java.lang.Object r3 = r1.next()
            android.app.ActivityManager$AppTask r3 = (android.app.ActivityManager.AppTask) r3
            r3.finishAndRemoveTask()
            goto L_0x017a
        L_0x018a:
            if (r0 == 0) goto L_0x01b2
            android.content.Context r0 = r8.b
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            android.content.ComponentName r1 = new android.content.ComponentName
            android.content.Context r3 = r8.b
            java.lang.Class<com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity> r5 = com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity.class
            r1.<init>(r3, r5)
            r0.setComponentEnabledSetting(r1, r4, r4)
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r1 = r8.b
            java.lang.Class<com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity> r3 = com.google.android.play.core.missingsplits.PlayCoreMissingSplitsActivity.class
            r0.<init>(r1, r3)
            r1 = 884998144(0x34c00000, float:3.5762787E-7)
            android.content.Intent r0 = r0.addFlags(r1)
            android.content.Context r1 = r8.b
            r1.startActivity(r0)
        L_0x01b2:
            java.lang.Runtime r0 = r8.c
            r0.exit(r2)
        L_0x01b7:
            return r4
        L_0x01b8:
            com.google.android.play.core.missingsplits.a r0 = r8.d
            boolean r0 = r0.a()
            if (r0 == 0) goto L_0x01ca
            com.google.android.play.core.missingsplits.a r0 = r8.d
            r0.c()
            java.lang.Runtime r0 = r8.c
            r0.exit(r2)
        L_0x01ca:
            return r2
        L_0x01cb:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x01cb }
            throw r1
        L_0x01ce:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.missingsplits.b.disableAppIfMissingRequiredSplits():boolean");
    }

    public final boolean isMissingRequiredSplits() {
        boolean booleanValue;
        synchronized (this.e) {
            if (this.e.get() == null) {
                AtomicReference<Boolean> atomicReference = this.e;
                boolean z = false;
                if (Build.VERSION.SDK_INT >= 21 && a()) {
                    if (a(b())) {
                        z = true;
                    }
                }
                atomicReference.set(Boolean.valueOf(z));
            }
            booleanValue = this.e.get().booleanValue();
        }
        return booleanValue;
    }
}

package com.google.android.play.core.splitinstall.testing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.internal.an;
import com.google.android.play.core.internal.ao;
import com.google.android.play.core.internal.aq;
import com.google.android.play.core.internal.bs;
import com.google.android.play.core.splitcompat.d;
import com.google.android.play.core.splitcompat.e;
import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener;
import com.google.android.play.core.splitinstall.f;
import com.google.android.play.core.splitinstall.z;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FakeSplitInstallManager implements SplitInstallManager {
    /* access modifiers changed from: private */
    public static final long b = TimeUnit.SECONDS.toMillis(1);
    private final Handler a = new Handler(Looper.getMainLooper());
    private final Context c;
    private final an d;
    private final z e;
    private final bs f;
    /* access modifiers changed from: private */
    public final com.google.android.play.core.internal.z<SplitInstallSessionState> g;
    private final Executor h;
    private final File i;
    private final AtomicReference<SplitInstallSessionState> j = new AtomicReference<>();
    /* access modifiers changed from: private */
    public final Set<String> k = Collections.synchronizedSet(new HashSet());
    /* access modifiers changed from: private */
    public final Set<String> l = Collections.synchronizedSet(new HashSet());
    /* access modifiers changed from: private */
    public final AtomicBoolean m = new AtomicBoolean(false);
    private final a n;

    public FakeSplitInstallManager(Context context, File file) throws PackageManager.NameNotFoundException {
        Executor a2 = d.a();
        aq aqVar = new aq();
        z zVar = new z(context, context.getPackageName());
        bs bsVar = new bs(context);
        ao aoVar = new ao(context, new e(context), new d(), (byte[]) null);
        a aVar = new a();
        this.n = aVar;
        this.g = new com.google.android.play.core.internal.z<>();
        this.c = context;
        this.i = file;
        this.e = zVar;
        this.f = bsVar;
        e eVar = new e(context);
        this.h = a2;
        this.d = new an(context, a2, aoVar, eVar, aqVar, (byte[]) null);
    }

    private final SplitInstallSessionState a(i iVar) {
        SplitInstallSessionState c2 = c();
        SplitInstallSessionState a2 = iVar.a(c2);
        if (!this.j.compareAndSet(c2, a2)) {
            return null;
        }
        return a2;
    }

    private static String a(String str) {
        return str.split("\\.config\\.", 2)[0];
    }

    private final void a(SplitInstallSessionState splitInstallSessionState) {
        this.a.post(new f(this, splitInstallSessionState));
    }

    static /* synthetic */ void a(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            File file = (File) list.get(i2);
            String a2 = aq.a(file);
            Uri fromFile = Uri.fromFile(file);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(fromFile, fakeSplitInstallManager.c.getContentResolver().getType(fromFile));
            intent.addFlags(1);
            intent.putExtra("module_name", a(a2));
            intent.putExtra("split_id", a2);
            arrayList.add(intent);
            arrayList2.add(a(aq.a(file)));
        }
        SplitInstallSessionState c2 = fakeSplitInstallManager.c();
        if (c2 != null) {
            fakeSplitInstallManager.h.execute(new g(fakeSplitInstallManager, c2.totalBytesToDownload(), arrayList, arrayList2, list2));
        }
    }

    /* access modifiers changed from: private */
    public final void a(List<Intent> list, List<String> list2, List<String> list3, long j2, boolean z) {
        List<Intent> list4 = list;
        this.d.b(list, new h(this, list2, list3, j2, z, list));
    }

    /* access modifiers changed from: private */
    public final boolean a(int i2, int i3, Long l2, Long l3, List<String> list, Integer num, List<String> list2) {
        SplitInstallSessionState a2 = a((i) new b(num, i2, i3, l2, l3, list, list2));
        if (a2 == null) {
            return false;
        }
        a(a2);
        return true;
    }

    private final SplitInstallSessionState c() {
        return this.j.get();
    }

    private final f d() {
        f c2 = this.e.c();
        if (c2 != null) {
            return c2;
        }
        throw new IllegalStateException("Language information could not be found. Make sure you are using the target application context, not the tests context, and the app is built as a bundle.");
    }

    /* access modifiers changed from: package-private */
    public final File a() {
        return this.i;
    }

    public final Task<Void> cancelInstall(int i2) {
        try {
            SplitInstallSessionState a2 = a((i) new e(i2));
            if (a2 != null) {
                a(a2);
            }
            return Tasks.a(null);
        } catch (SplitInstallException e2) {
            return Tasks.a((Exception) e2);
        }
    }

    public final Task<Void> deferredInstall(List<String> list) {
        return Tasks.a((Exception) new SplitInstallException(-5));
    }

    public final Task<Void> deferredLanguageInstall(List<Locale> list) {
        return Tasks.a((Exception) new SplitInstallException(-5));
    }

    public final Task<Void> deferredLanguageUninstall(List<Locale> list) {
        return Tasks.a((Exception) new SplitInstallException(-5));
    }

    public final Task<Void> deferredUninstall(List<String> list) {
        return Tasks.a((Exception) new SplitInstallException(-5));
    }

    public final Set<String> getInstalledLanguages() {
        return new HashSet(this.l);
    }

    public final Set<String> getInstalledModules() {
        return new HashSet(this.k);
    }

    public final Task<SplitInstallSessionState> getSessionState(int i2) {
        SplitInstallSessionState c2 = c();
        return (c2 == null || c2.sessionId() != i2) ? Tasks.a((Exception) new SplitInstallException(-4)) : Tasks.a(c2);
    }

    public final Task<List<SplitInstallSessionState>> getSessionStates() {
        SplitInstallSessionState c2 = c();
        return Tasks.a(c2 == null ? Collections.emptyList() : Collections.singletonList(c2));
    }

    public final void registerListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.g.a(splitInstallStateUpdatedListener);
    }

    public void setShouldNetworkError(boolean z) {
        this.m.set(z);
    }

    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, Activity activity, int i2) throws IntentSender.SendIntentException {
        return false;
    }

    public final boolean startConfirmationDialogForResult(SplitInstallSessionState splitInstallSessionState, IntentSenderForResultStarter intentSenderForResultStarter, int i2) throws IntentSender.SendIntentException {
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01c9, code lost:
        if (r4.contains(r6) == false) goto L_0x01cb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.play.core.tasks.Task<java.lang.Integer> startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest r22) {
        /*
            r21 = this;
            r9 = r21
            com.google.android.play.core.splitinstall.testing.d r0 = new com.google.android.play.core.splitinstall.testing.d     // Catch:{ SplitInstallException -> 0x0236 }
            r1 = r22
            r0.<init>(r1)     // Catch:{ SplitInstallException -> 0x0236 }
            com.google.android.play.core.splitinstall.SplitInstallSessionState r0 = r9.a((com.google.android.play.core.splitinstall.testing.i) r0)     // Catch:{ SplitInstallException -> 0x0236 }
            if (r0 != 0) goto L_0x001b
            com.google.android.play.core.splitinstall.SplitInstallException r0 = new com.google.android.play.core.splitinstall.SplitInstallException     // Catch:{ SplitInstallException -> 0x0236 }
            r1 = -100
            r0.<init>(r1)     // Catch:{ SplitInstallException -> 0x0236 }
            com.google.android.play.core.tasks.Task r0 = com.google.android.play.core.tasks.Tasks.a((java.lang.Exception) r0)     // Catch:{ SplitInstallException -> 0x0236 }
            return r0
        L_0x001b:
            int r0 = r0.sessionId()     // Catch:{ SplitInstallException -> 0x0236 }
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.List r2 = r22.getLanguages()
            java.util.Iterator r2 = r2.iterator()
        L_0x002c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0040
            java.lang.Object r3 = r2.next()
            java.util.Locale r3 = (java.util.Locale) r3
            java.lang.String r3 = r3.getLanguage()
            r10.add(r3)
            goto L_0x002c
        L_0x0040:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.io.File r3 = r9.i
            java.io.File[] r3 = r3.listFiles()
            java.lang.String r4 = "FakeSplitInstallManager"
            if (r3 != 0) goto L_0x0064
            java.lang.String r0 = "Specified splits directory does not exist."
            android.util.Log.w(r4, r0)
            com.google.android.play.core.splitinstall.SplitInstallException r0 = new com.google.android.play.core.splitinstall.SplitInstallException
            r1 = -5
            r0.<init>(r1)
            com.google.android.play.core.tasks.Task r0 = com.google.android.play.core.tasks.Tasks.a((java.lang.Exception) r0)
            return r0
        L_0x0064:
            int r5 = r3.length
            r6 = 0
            r13 = r6
            r12 = 0
        L_0x0069:
            if (r12 < r5) goto L_0x00e8
            java.lang.String r3 = java.lang.String.valueOf(r2)
            java.util.List r5 = r22.getModuleNames()
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r8 = java.lang.String.valueOf(r3)
            int r8 = r8.length()
            java.lang.String r12 = java.lang.String.valueOf(r5)
            int r12 = r12.length()
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            int r8 = r8 + 21
            int r8 = r8 + r12
            r15.<init>(r8)
            java.lang.String r8 = "availableSplits"
            r15.append(r8)
            r15.append(r3)
            java.lang.String r3 = " want "
            r15.append(r3)
            r15.append(r5)
            java.lang.String r3 = r15.toString()
            android.util.Log.i(r4, r3)
            java.util.HashSet r3 = new java.util.HashSet
            java.util.List r4 = r22.getModuleNames()
            r3.<init>(r4)
            boolean r2 = r2.containsAll(r3)
            if (r2 != 0) goto L_0x00c0
            com.google.android.play.core.splitinstall.SplitInstallException r0 = new com.google.android.play.core.splitinstall.SplitInstallException
            r1 = -2
            r0.<init>(r1)
            com.google.android.play.core.tasks.Task r0 = com.google.android.play.core.tasks.Tasks.a((java.lang.Exception) r0)
            return r0
        L_0x00c0:
            java.lang.Long r4 = java.lang.Long.valueOf(r6)
            java.lang.Long r5 = java.lang.Long.valueOf(r13)
            java.util.List r6 = r22.getModuleNames()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2 = 1
            r3 = 0
            r1 = r21
            r7 = r0
            r8 = r10
            r1.a(r2, r3, r4, r5, r6, r7, r8)
            java.util.concurrent.Executor r1 = r9.h
            com.google.android.play.core.splitinstall.testing.c r2 = new com.google.android.play.core.splitinstall.testing.c
            r2.<init>(r9, r11, r10)
            r1.execute(r2)
            com.google.android.play.core.tasks.Task r0 = com.google.android.play.core.tasks.Tasks.a(r0)
            return r0
        L_0x00e8:
            r15 = r3[r12]
            java.lang.String r6 = com.google.android.play.core.internal.aq.a(r15)
            java.lang.String r7 = a((java.lang.String) r6)
            java.util.List r8 = r22.getModuleNames()
            boolean r7 = r8.contains(r7)
            if (r7 != 0) goto L_0x0107
            r17 = r0
            r19 = r3
            r18 = r4
            r20 = r5
            r5 = 0
            goto L_0x01cb
        L_0x0107:
            java.lang.String r7 = a((java.lang.String) r6)
            java.util.HashSet r8 = new java.util.HashSet
            r17 = r0
            com.google.android.play.core.internal.bs r0 = r9.f
            java.util.List r0 = r0.a()
            r8.<init>(r0)
            com.google.android.play.core.splitinstall.f r0 = r21.d()
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]
            r16 = 0
            r1[r16] = r7
            java.util.List r1 = java.util.Arrays.asList(r1)
            java.util.Map r0 = r0.a(r1)
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.Collection r7 = r0.values()
            java.util.Iterator r7 = r7.iterator()
        L_0x0138:
            boolean r18 = r7.hasNext()
            if (r18 == 0) goto L_0x014e
            java.lang.Object r18 = r7.next()
            r19 = r3
            r3 = r18
            java.util.Set r3 = (java.util.Set) r3
            r1.addAll(r3)
            r3 = r19
            goto L_0x0138
        L_0x014e:
            r19 = r3
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.Iterator r7 = r8.iterator()
        L_0x0159:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0185
            java.lang.Object r8 = r7.next()
            java.lang.String r8 = (java.lang.String) r8
            r18 = r4
            java.lang.String r4 = "_"
            boolean r20 = r8.contains(r4)
            if (r20 == 0) goto L_0x017a
            r20 = r5
            r5 = -1
            java.lang.String[] r4 = r8.split(r4, r5)
            r5 = 0
            r8 = r4[r5]
            goto L_0x017d
        L_0x017a:
            r20 = r5
            r5 = 0
        L_0x017d:
            r3.add(r8)
            r4 = r18
            r5 = r20
            goto L_0x0159
        L_0x0185:
            r18 = r4
            r20 = r5
            r5 = 0
            java.util.Set<java.lang.String> r4 = r9.l
            r3.addAll(r4)
            r3.addAll(r10)
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x019f:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L_0x01bf
            java.lang.Object r7 = r0.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r8 = r7.getKey()
            boolean r8 = r3.contains(r8)
            if (r8 == 0) goto L_0x019f
            java.lang.Object r7 = r7.getValue()
            java.util.Collection r7 = (java.util.Collection) r7
            r4.addAll(r7)
            goto L_0x019f
        L_0x01bf:
            boolean r0 = r1.contains(r6)
            if (r0 == 0) goto L_0x0217
            boolean r0 = r4.contains(r6)
            if (r0 != 0) goto L_0x0217
        L_0x01cb:
            java.util.List r0 = r22.getLanguages()
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Set<java.lang.String> r3 = r9.k
            r1.<init>(r3)
            java.lang.String r3 = ""
            java.lang.String r4 = "base"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}
            java.util.List r3 = java.util.Arrays.asList(r3)
            r1.addAll(r3)
            com.google.android.play.core.splitinstall.f r3 = r21.d()
            java.util.Map r1 = r3.a(r1)
            java.util.Iterator r0 = r0.iterator()
        L_0x01f1:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0226
            java.lang.Object r3 = r0.next()
            java.util.Locale r3 = (java.util.Locale) r3
            java.lang.String r4 = r3.getLanguage()
            boolean r4 = r1.containsKey(r4)
            if (r4 == 0) goto L_0x01f1
            java.lang.String r3 = r3.getLanguage()
            java.lang.Object r3 = r1.get(r3)
            java.util.Set r3 = (java.util.Set) r3
            boolean r3 = r3.contains(r6)
            if (r3 == 0) goto L_0x01f1
        L_0x0217:
            long r0 = r15.length()
            long r13 = r13 + r0
            java.lang.String r0 = com.google.android.play.core.internal.aq.a(r15)
            r2.add(r0)
            r11.add(r15)
        L_0x0226:
            int r12 = r12 + 1
            r1 = r22
            r0 = r17
            r4 = r18
            r3 = r19
            r5 = r20
            r6 = 0
            goto L_0x0069
        L_0x0236:
            r0 = move-exception
            com.google.android.play.core.tasks.Task r0 = com.google.android.play.core.tasks.Tasks.a((java.lang.Exception) r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager.startInstall(com.google.android.play.core.splitinstall.SplitInstallRequest):com.google.android.play.core.tasks.Task");
    }

    public final void unregisterListener(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.g.b(splitInstallStateUpdatedListener);
    }
}

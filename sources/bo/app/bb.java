package bo.app;

import com.appboy.support.AppboyLogger;
import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

public final class bb {
    static final Pattern a = Pattern.compile("[a-z0-9_-]{1,120}");
    private static final String c = AppboyLogger.getAppboyLogTag(bb.class);
    /* access modifiers changed from: private */
    public static final OutputStream q = new OutputStream() {
        public void write(int i) {
        }
    };
    final ThreadPoolExecutor b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    /* access modifiers changed from: private */
    public final File d;
    private final File e;
    private final File f;
    private final File g;
    private final int h;
    private long i;
    /* access modifiers changed from: private */
    public final int j;
    private long k = 0;
    /* access modifiers changed from: private */
    public Writer l;
    private final LinkedHashMap<String, bc> m = new LinkedHashMap<>(0, 0.75f, true);
    /* access modifiers changed from: private */
    public int n;
    private long o = 0;
    private final Callable<Void> p = new Callable<Void>() {
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
            return null;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() {
            /*
                r4 = this;
                bo.app.bb r0 = bo.app.bb.this
                monitor-enter(r0)
                bo.app.bb r1 = bo.app.bb.this     // Catch:{ all -> 0x0028 }
                java.io.Writer r1 = r1.l     // Catch:{ all -> 0x0028 }
                r2 = 0
                if (r1 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x000e:
                bo.app.bb r1 = bo.app.bb.this     // Catch:{ all -> 0x0028 }
                r1.i()     // Catch:{ all -> 0x0028 }
                bo.app.bb r1 = bo.app.bb.this     // Catch:{ all -> 0x0028 }
                boolean r1 = r1.g()     // Catch:{ all -> 0x0028 }
                if (r1 == 0) goto L_0x0026
                bo.app.bb r1 = bo.app.bb.this     // Catch:{ all -> 0x0028 }
                r1.f()     // Catch:{ all -> 0x0028 }
                bo.app.bb r1 = bo.app.bb.this     // Catch:{ all -> 0x0028 }
                r3 = 0
                int unused = r1.n = r3     // Catch:{ all -> 0x0028 }
            L_0x0026:
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                return r2
            L_0x0028:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.bb.AnonymousClass1.call():java.lang.Void");
        }
    };

    private bb(File file, int i2, int i3, long j2) {
        File file2 = file;
        this.d = file2;
        this.h = i2;
        this.e = new File(file2, "journal");
        this.f = new File(file2, "journal.tmp");
        this.g = new File(file2, "journal.bkp");
        this.j = i3;
        this.i = j2;
    }

    public static bb a(File file, int i2, int i3, long j2) {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 > 0) {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            bb bbVar = new bb(file, i2, i3, j2);
            if (bbVar.e.exists()) {
                try {
                    bbVar.d();
                    bbVar.e();
                    return bbVar;
                } catch (IOException e2) {
                    String str = c;
                    AppboyLogger.e(str, "DiskLruCache " + file + " is corrupt: " + e2.getMessage() + ", removing");
                    bbVar.b();
                }
            }
            file.mkdirs();
            bb bbVar2 = new bb(file, i2, i3, j2);
            bbVar2.f();
            return bbVar2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|(1:19)(1:20)|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r9.n = r0 - r9.m.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006c, code lost:
        if (r1.b() != false) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
        f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        r9.l = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(r9.e, true), bo.app.be.a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x008b, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005f */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x008c=Splitter:B:23:0x008c, B:16:0x005f=Splitter:B:16:0x005f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d() {
        /*
            r9 = this;
            java.lang.String r0 = ", "
            bo.app.bd r1 = new bo.app.bd
            java.io.FileInputStream r2 = new java.io.FileInputStream
            java.io.File r3 = r9.e
            r2.<init>(r3)
            java.nio.charset.Charset r3 = bo.app.be.a
            r1.<init>(r2, r3)
            java.lang.String r2 = r1.a()     // Catch:{ all -> 0x00bb }
            java.lang.String r3 = r1.a()     // Catch:{ all -> 0x00bb }
            java.lang.String r4 = r1.a()     // Catch:{ all -> 0x00bb }
            java.lang.String r5 = r1.a()     // Catch:{ all -> 0x00bb }
            java.lang.String r6 = r1.a()     // Catch:{ all -> 0x00bb }
            java.lang.String r7 = "libcore.io.DiskLruCache"
            boolean r7 = r7.equals(r2)     // Catch:{ all -> 0x00bb }
            if (r7 == 0) goto L_0x008c
            java.lang.String r7 = "1"
            boolean r7 = r7.equals(r3)     // Catch:{ all -> 0x00bb }
            if (r7 == 0) goto L_0x008c
            int r7 = r9.h     // Catch:{ all -> 0x00bb }
            java.lang.String r7 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x00bb }
            boolean r4 = r7.equals(r4)     // Catch:{ all -> 0x00bb }
            if (r4 == 0) goto L_0x008c
            int r4 = r9.j     // Catch:{ all -> 0x00bb }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x00bb }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00bb }
            if (r4 == 0) goto L_0x008c
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x00bb }
            if (r4 == 0) goto L_0x008c
            r0 = 0
        L_0x0055:
            java.lang.String r2 = r1.a()     // Catch:{ EOFException -> 0x005f }
            r9.d((java.lang.String) r2)     // Catch:{ EOFException -> 0x005f }
            int r0 = r0 + 1
            goto L_0x0055
        L_0x005f:
            java.util.LinkedHashMap<java.lang.String, bo.app.bc> r2 = r9.m     // Catch:{ all -> 0x00bb }
            int r2 = r2.size()     // Catch:{ all -> 0x00bb }
            int r0 = r0 - r2
            r9.n = r0     // Catch:{ all -> 0x00bb }
            boolean r0 = r1.b()     // Catch:{ all -> 0x00bb }
            if (r0 == 0) goto L_0x0072
            r9.f()     // Catch:{ all -> 0x00bb }
            goto L_0x0088
        L_0x0072:
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ all -> 0x00bb }
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x00bb }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x00bb }
            java.io.File r4 = r9.e     // Catch:{ all -> 0x00bb }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ all -> 0x00bb }
            java.nio.charset.Charset r4 = bo.app.be.a     // Catch:{ all -> 0x00bb }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x00bb }
            r0.<init>(r2)     // Catch:{ all -> 0x00bb }
            r9.l = r0     // Catch:{ all -> 0x00bb }
        L_0x0088:
            bo.app.be.a((java.io.Closeable) r1)
            return
        L_0x008c:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bb }
            r7.<init>()     // Catch:{ all -> 0x00bb }
            java.lang.String r8 = "unexpected journal header: ["
            r7.append(r8)     // Catch:{ all -> 0x00bb }
            r7.append(r2)     // Catch:{ all -> 0x00bb }
            r7.append(r0)     // Catch:{ all -> 0x00bb }
            r7.append(r3)     // Catch:{ all -> 0x00bb }
            r7.append(r0)     // Catch:{ all -> 0x00bb }
            r7.append(r5)     // Catch:{ all -> 0x00bb }
            r7.append(r0)     // Catch:{ all -> 0x00bb }
            r7.append(r6)     // Catch:{ all -> 0x00bb }
            java.lang.String r0 = "]"
            r7.append(r0)     // Catch:{ all -> 0x00bb }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x00bb }
            r4.<init>(r0)     // Catch:{ all -> 0x00bb }
            throw r4     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r0 = move-exception
            bo.app.be.a((java.io.Closeable) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.bb.d():void");
    }

    private void d(String str) {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i2 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i2);
            if (indexOf2 == -1) {
                str2 = str.substring(i2);
                if (indexOf == 6 && str.startsWith("REMOVE")) {
                    this.m.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i2, indexOf2);
            }
            bc bcVar = this.m.get(str2);
            if (bcVar == null) {
                bcVar = new bc(str2, this.j, this.d);
                this.m.put(str2, bcVar);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith("CLEAN")) {
                String[] split = str.substring(indexOf2 + 1).split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                bcVar.c = true;
                bcVar.d = null;
                bcVar.a(split);
            } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith("DIRTY")) {
                bcVar.d = new a(bcVar);
            } else if (indexOf2 != -1 || indexOf != 4 || !str.startsWith("READ")) {
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void e() {
        a(this.f);
        Iterator<bc> it = this.m.values().iterator();
        while (it.hasNext()) {
            bc next = it.next();
            int i2 = 0;
            if (next.d == null) {
                while (i2 < this.j) {
                    this.k += next.b[i2];
                    i2++;
                }
            } else {
                next.d = null;
                while (i2 < this.j) {
                    a(next.a(i2));
                    a(next.b(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public synchronized void f() {
        if (this.l != null) {
            this.l.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f), be.a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write(AppEventsConstants.EVENT_PARAM_VALUE_YES);
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write(Integer.toString(this.h));
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write(Integer.toString(this.j));
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_UNIX);
            for (bc next : this.m.values()) {
                if (next.d != null) {
                    bufferedWriter.write("DIRTY " + next.a + 10);
                } else {
                    bufferedWriter.write("CLEAN " + next.a + next.a() + 10);
                }
            }
            bufferedWriter.close();
            if (this.e.exists()) {
                a(this.e, this.g, true);
            }
            a(this.f, this.e, false);
            this.g.delete();
            this.l = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e, true), be.a));
        } catch (Throwable th) {
            bufferedWriter.close();
            throw th;
        }
    }

    private static void a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void a(File file, File file2, boolean z) {
        if (z) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:32|33|28|27) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r11.n++;
        r11.l.append("READ " + r12 + 10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0057, code lost:
        if (g() == false) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0059, code lost:
        r11.b.submit(r11.p);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006e, code lost:
        return new bo.app.bb.b(r11, r12, r0.e, r8, r0.b, (bo.app.bb.AnonymousClass1) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0080, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006f */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0077  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized bo.app.bb.b a(java.lang.String r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            r11.h()     // Catch:{ all -> 0x0081 }
            r11.e((java.lang.String) r12)     // Catch:{ all -> 0x0081 }
            java.util.LinkedHashMap<java.lang.String, bo.app.bc> r0 = r11.m     // Catch:{ all -> 0x0081 }
            java.lang.Object r0 = r0.get(r12)     // Catch:{ all -> 0x0081 }
            bo.app.bc r0 = (bo.app.bc) r0     // Catch:{ all -> 0x0081 }
            r1 = 0
            if (r0 != 0) goto L_0x0014
            monitor-exit(r11)
            return r1
        L_0x0014:
            boolean r2 = r0.c     // Catch:{ all -> 0x0081 }
            if (r2 != 0) goto L_0x001a
            monitor-exit(r11)
            return r1
        L_0x001a:
            int r2 = r11.j     // Catch:{ all -> 0x0081 }
            java.io.InputStream[] r8 = new java.io.InputStream[r2]     // Catch:{ all -> 0x0081 }
            r2 = 0
            r3 = 0
        L_0x0020:
            int r4 = r11.j     // Catch:{ FileNotFoundException -> 0x006f }
            if (r3 >= r4) goto L_0x0032
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x006f }
            java.io.File r5 = r0.a((int) r3)     // Catch:{ FileNotFoundException -> 0x006f }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x006f }
            r8[r3] = r4     // Catch:{ FileNotFoundException -> 0x006f }
            int r3 = r3 + 1
            goto L_0x0020
        L_0x0032:
            int r1 = r11.n     // Catch:{ all -> 0x0081 }
            int r1 = r1 + 1
            r11.n = r1     // Catch:{ all -> 0x0081 }
            java.io.Writer r1 = r11.l     // Catch:{ all -> 0x0081 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0081 }
            r2.<init>()     // Catch:{ all -> 0x0081 }
            java.lang.String r3 = "READ "
            r2.append(r3)     // Catch:{ all -> 0x0081 }
            r2.append(r12)     // Catch:{ all -> 0x0081 }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x0081 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0081 }
            r1.append(r2)     // Catch:{ all -> 0x0081 }
            boolean r1 = r11.g()     // Catch:{ all -> 0x0081 }
            if (r1 == 0) goto L_0x0060
            java.util.concurrent.ThreadPoolExecutor r1 = r11.b     // Catch:{ all -> 0x0081 }
            java.util.concurrent.Callable<java.lang.Void> r2 = r11.p     // Catch:{ all -> 0x0081 }
            r1.submit(r2)     // Catch:{ all -> 0x0081 }
        L_0x0060:
            bo.app.bb$b r1 = new bo.app.bb$b     // Catch:{ all -> 0x0081 }
            long r6 = r0.e     // Catch:{ all -> 0x0081 }
            long[] r9 = r0.b     // Catch:{ all -> 0x0081 }
            r10 = 0
            r3 = r1
            r4 = r11
            r5 = r12
            r3.<init>(r5, r6, r8, r9)     // Catch:{ all -> 0x0081 }
            monitor-exit(r11)
            return r1
        L_0x006f:
            int r12 = r11.j     // Catch:{ all -> 0x0081 }
            if (r2 >= r12) goto L_0x007f
            r12 = r8[r2]     // Catch:{ all -> 0x0081 }
            if (r12 == 0) goto L_0x007f
            r12 = r8[r2]     // Catch:{ all -> 0x0081 }
            bo.app.be.a((java.io.Closeable) r12)     // Catch:{ all -> 0x0081 }
            int r2 = r2 + 1
            goto L_0x006f
        L_0x007f:
            monitor-exit(r11)
            return r1
        L_0x0081:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.bb.a(java.lang.String):bo.app.bb$b");
    }

    public a b(String str) {
        return a(str, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized bo.app.bb.a a(java.lang.String r6, long r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.h()     // Catch:{ all -> 0x0060 }
            r5.e((java.lang.String) r6)     // Catch:{ all -> 0x0060 }
            java.util.LinkedHashMap<java.lang.String, bo.app.bc> r0 = r5.m     // Catch:{ all -> 0x0060 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0060 }
            bo.app.bc r0 = (bo.app.bc) r0     // Catch:{ all -> 0x0060 }
            r1 = -1
            r3 = 0
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x0020
            if (r0 == 0) goto L_0x001e
            long r1 = r0.e     // Catch:{ all -> 0x0060 }
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0020
        L_0x001e:
            monitor-exit(r5)
            return r3
        L_0x0020:
            if (r0 != 0) goto L_0x0031
            bo.app.bc r0 = new bo.app.bc     // Catch:{ all -> 0x0060 }
            int r7 = r5.j     // Catch:{ all -> 0x0060 }
            java.io.File r8 = r5.d     // Catch:{ all -> 0x0060 }
            r0.<init>(r6, r7, r8)     // Catch:{ all -> 0x0060 }
            java.util.LinkedHashMap<java.lang.String, bo.app.bc> r7 = r5.m     // Catch:{ all -> 0x0060 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0060 }
            goto L_0x0037
        L_0x0031:
            bo.app.bb$a r7 = r0.d     // Catch:{ all -> 0x0060 }
            if (r7 == 0) goto L_0x0037
            monitor-exit(r5)
            return r3
        L_0x0037:
            bo.app.bb$a r7 = new bo.app.bb$a     // Catch:{ all -> 0x0060 }
            r7.<init>(r0)     // Catch:{ all -> 0x0060 }
            r0.d = r7     // Catch:{ all -> 0x0060 }
            java.io.Writer r8 = r5.l     // Catch:{ all -> 0x0060 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0060 }
            r0.<init>()     // Catch:{ all -> 0x0060 }
            java.lang.String r1 = "DIRTY "
            r0.append(r1)     // Catch:{ all -> 0x0060 }
            r0.append(r6)     // Catch:{ all -> 0x0060 }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x0060 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0060 }
            r8.write(r6)     // Catch:{ all -> 0x0060 }
            java.io.Writer r6 = r5.l     // Catch:{ all -> 0x0060 }
            r6.flush()     // Catch:{ all -> 0x0060 }
            monitor-exit(r5)
            return r7
        L_0x0060:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.bb.a(java.lang.String, long):bo.app.bb$a");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00f6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(bo.app.bb.a r10, boolean r11) {
        /*
            r9 = this;
            monitor-enter(r9)
            bo.app.bc r0 = r10.b     // Catch:{ all -> 0x00fd }
            bo.app.bb$a r1 = r0.d     // Catch:{ all -> 0x00fd }
            if (r1 != r10) goto L_0x00f7
            r1 = 0
            if (r11 == 0) goto L_0x0049
            boolean r2 = r0.c     // Catch:{ all -> 0x00fd }
            if (r2 != 0) goto L_0x0049
            r2 = 0
        L_0x0011:
            int r3 = r9.j     // Catch:{ all -> 0x00fd }
            if (r2 >= r3) goto L_0x0049
            boolean[] r3 = r10.c     // Catch:{ all -> 0x00fd }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x00fd }
            if (r3 == 0) goto L_0x002f
            java.io.File r3 = r0.b((int) r2)     // Catch:{ all -> 0x00fd }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x00fd }
            if (r3 != 0) goto L_0x002c
            r10.b()     // Catch:{ all -> 0x00fd }
            monitor-exit(r9)
            return
        L_0x002c:
            int r2 = r2 + 1
            goto L_0x0011
        L_0x002f:
            r10.b()     // Catch:{ all -> 0x00fd }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fd }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fd }
            r11.<init>()     // Catch:{ all -> 0x00fd }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x00fd }
            r11.append(r2)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00fd }
            r10.<init>(r11)     // Catch:{ all -> 0x00fd }
            throw r10     // Catch:{ all -> 0x00fd }
        L_0x0049:
            int r10 = r9.j     // Catch:{ all -> 0x00fd }
            if (r1 >= r10) goto L_0x0079
            java.io.File r10 = r0.b((int) r1)     // Catch:{ all -> 0x00fd }
            if (r11 == 0) goto L_0x0073
            boolean r2 = r10.exists()     // Catch:{ all -> 0x00fd }
            if (r2 == 0) goto L_0x0076
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x00fd }
            r10.renameTo(r2)     // Catch:{ all -> 0x00fd }
            long[] r10 = r0.b     // Catch:{ all -> 0x00fd }
            r3 = r10[r1]     // Catch:{ all -> 0x00fd }
            long r5 = r2.length()     // Catch:{ all -> 0x00fd }
            long[] r10 = r0.b     // Catch:{ all -> 0x00fd }
            r10[r1] = r5     // Catch:{ all -> 0x00fd }
            long r7 = r9.k     // Catch:{ all -> 0x00fd }
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.k = r7     // Catch:{ all -> 0x00fd }
            goto L_0x0076
        L_0x0073:
            a((java.io.File) r10)     // Catch:{ all -> 0x00fd }
        L_0x0076:
            int r1 = r1 + 1
            goto L_0x0049
        L_0x0079:
            int r10 = r9.n     // Catch:{ all -> 0x00fd }
            r1 = 1
            int r10 = r10 + r1
            r9.n = r10     // Catch:{ all -> 0x00fd }
            r10 = 0
            r0.d = r10     // Catch:{ all -> 0x00fd }
            boolean r10 = r0.c     // Catch:{ all -> 0x00fd }
            r10 = r10 | r11
            r2 = 10
            if (r10 == 0) goto L_0x00b9
            r0.c = r1     // Catch:{ all -> 0x00fd }
            java.io.Writer r10 = r9.l     // Catch:{ all -> 0x00fd }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fd }
            r1.<init>()     // Catch:{ all -> 0x00fd }
            java.lang.String r3 = "CLEAN "
            r1.append(r3)     // Catch:{ all -> 0x00fd }
            java.lang.String r3 = r0.a     // Catch:{ all -> 0x00fd }
            r1.append(r3)     // Catch:{ all -> 0x00fd }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x00fd }
            r1.append(r3)     // Catch:{ all -> 0x00fd }
            r1.append(r2)     // Catch:{ all -> 0x00fd }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00fd }
            r10.write(r1)     // Catch:{ all -> 0x00fd }
            if (r11 == 0) goto L_0x00db
            long r10 = r9.o     // Catch:{ all -> 0x00fd }
            r1 = 1
            long r1 = r1 + r10
            r9.o = r1     // Catch:{ all -> 0x00fd }
            r0.e = r10     // Catch:{ all -> 0x00fd }
            goto L_0x00db
        L_0x00b9:
            java.util.LinkedHashMap<java.lang.String, bo.app.bc> r10 = r9.m     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r0.a     // Catch:{ all -> 0x00fd }
            r10.remove(r11)     // Catch:{ all -> 0x00fd }
            java.io.Writer r10 = r9.l     // Catch:{ all -> 0x00fd }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00fd }
            r11.<init>()     // Catch:{ all -> 0x00fd }
            java.lang.String r1 = "REMOVE "
            r11.append(r1)     // Catch:{ all -> 0x00fd }
            java.lang.String r0 = r0.a     // Catch:{ all -> 0x00fd }
            r11.append(r0)     // Catch:{ all -> 0x00fd }
            r11.append(r2)     // Catch:{ all -> 0x00fd }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00fd }
            r10.write(r11)     // Catch:{ all -> 0x00fd }
        L_0x00db:
            java.io.Writer r10 = r9.l     // Catch:{ all -> 0x00fd }
            r10.flush()     // Catch:{ all -> 0x00fd }
            long r10 = r9.k     // Catch:{ all -> 0x00fd }
            long r0 = r9.i     // Catch:{ all -> 0x00fd }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x00ee
            boolean r10 = r9.g()     // Catch:{ all -> 0x00fd }
            if (r10 == 0) goto L_0x00f5
        L_0x00ee:
            java.util.concurrent.ThreadPoolExecutor r10 = r9.b     // Catch:{ all -> 0x00fd }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.p     // Catch:{ all -> 0x00fd }
            r10.submit(r11)     // Catch:{ all -> 0x00fd }
        L_0x00f5:
            monitor-exit(r9)
            return
        L_0x00f7:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00fd }
            r10.<init>()     // Catch:{ all -> 0x00fd }
            throw r10     // Catch:{ all -> 0x00fd }
        L_0x00fd:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.bb.a(bo.app.bb$a, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean g() {
        int i2 = this.n;
        return i2 >= 2000 && i2 >= this.m.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0089, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008b, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean c(java.lang.String r8) {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.h()     // Catch:{ all -> 0x008c }
            r7.e((java.lang.String) r8)     // Catch:{ all -> 0x008c }
            java.util.LinkedHashMap<java.lang.String, bo.app.bc> r0 = r7.m     // Catch:{ all -> 0x008c }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x008c }
            bo.app.bc r0 = (bo.app.bc) r0     // Catch:{ all -> 0x008c }
            r1 = 0
            if (r0 == 0) goto L_0x008a
            bo.app.bb$a r2 = r0.d     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x0017
            goto L_0x008a
        L_0x0017:
            int r2 = r7.j     // Catch:{ all -> 0x008c }
            if (r1 >= r2) goto L_0x0055
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x008c }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x008c }
            if (r3 == 0) goto L_0x0043
            boolean r3 = r2.delete()     // Catch:{ all -> 0x008c }
            if (r3 == 0) goto L_0x002c
            goto L_0x0043
        L_0x002c:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            r0.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x008c }
            r0.append(r2)     // Catch:{ all -> 0x008c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008c }
            r8.<init>(r0)     // Catch:{ all -> 0x008c }
            throw r8     // Catch:{ all -> 0x008c }
        L_0x0043:
            long r2 = r7.k     // Catch:{ all -> 0x008c }
            long[] r4 = r0.b     // Catch:{ all -> 0x008c }
            r5 = r4[r1]     // Catch:{ all -> 0x008c }
            long r2 = r2 - r5
            r7.k = r2     // Catch:{ all -> 0x008c }
            long[] r2 = r0.b     // Catch:{ all -> 0x008c }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x008c }
            int r1 = r1 + 1
            goto L_0x0017
        L_0x0055:
            int r0 = r7.n     // Catch:{ all -> 0x008c }
            r1 = 1
            int r0 = r0 + r1
            r7.n = r0     // Catch:{ all -> 0x008c }
            java.io.Writer r0 = r7.l     // Catch:{ all -> 0x008c }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x008c }
            r2.<init>()     // Catch:{ all -> 0x008c }
            java.lang.String r3 = "REMOVE "
            r2.append(r3)     // Catch:{ all -> 0x008c }
            r2.append(r8)     // Catch:{ all -> 0x008c }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x008c }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x008c }
            r0.append(r2)     // Catch:{ all -> 0x008c }
            java.util.LinkedHashMap<java.lang.String, bo.app.bc> r0 = r7.m     // Catch:{ all -> 0x008c }
            r0.remove(r8)     // Catch:{ all -> 0x008c }
            boolean r8 = r7.g()     // Catch:{ all -> 0x008c }
            if (r8 == 0) goto L_0x0088
            java.util.concurrent.ThreadPoolExecutor r8 = r7.b     // Catch:{ all -> 0x008c }
            java.util.concurrent.Callable<java.lang.Void> r0 = r7.p     // Catch:{ all -> 0x008c }
            r8.submit(r0)     // Catch:{ all -> 0x008c }
        L_0x0088:
            monitor-exit(r7)
            return r1
        L_0x008a:
            monitor-exit(r7)
            return r1
        L_0x008c:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.bb.c(java.lang.String):boolean");
    }

    private void h() {
        if (this.l == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void a() {
        if (this.l != null) {
            Iterator it = new ArrayList(this.m.values()).iterator();
            while (it.hasNext()) {
                bc bcVar = (bc) it.next();
                if (bcVar.d != null) {
                    bcVar.d.b();
                }
            }
            i();
            this.l.close();
            this.l = null;
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        while (this.k > this.i) {
            c((String) this.m.entrySet().iterator().next().getKey());
        }
    }

    public void b() {
        a();
        be.a(this.d);
    }

    private void e(String str) {
        if (!a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    public final class b implements Closeable {
        private final String b;
        private final long c;
        private final InputStream[] d;
        private final long[] e;

        private b(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.b = str;
            this.c = j;
            this.d = inputStreamArr;
            this.e = jArr;
        }

        public InputStream a(int i) {
            return this.d[i];
        }

        public void close() {
            for (InputStream a2 : this.d) {
                be.a((Closeable) a2);
            }
        }
    }

    public final class a {
        /* access modifiers changed from: private */
        public final bc b;
        /* access modifiers changed from: private */
        public final boolean[] c;
        /* access modifiers changed from: private */
        public boolean d;
        private boolean e;

        private a(bc bcVar) {
            this.b = bcVar;
            this.c = bcVar.c ? null : new boolean[bb.this.j];
        }

        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x002a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.io.OutputStream a(int r4) {
            /*
                r3 = this;
                if (r4 < 0) goto L_0x004f
                bo.app.bb r0 = bo.app.bb.this
                int r0 = r0.j
                if (r4 >= r0) goto L_0x004f
                bo.app.bb r0 = bo.app.bb.this
                monitor-enter(r0)
                bo.app.bc r1 = r3.b     // Catch:{ all -> 0x004c }
                bo.app.bb$a r1 = r1.d     // Catch:{ all -> 0x004c }
                if (r1 != r3) goto L_0x0046
                bo.app.bc r1 = r3.b     // Catch:{ all -> 0x004c }
                boolean r1 = r1.c     // Catch:{ all -> 0x004c }
                if (r1 != 0) goto L_0x001e
                boolean[] r1 = r3.c     // Catch:{ all -> 0x004c }
                r2 = 1
                r1[r4] = r2     // Catch:{ all -> 0x004c }
            L_0x001e:
                bo.app.bc r1 = r3.b     // Catch:{ all -> 0x004c }
                java.io.File r4 = r1.b((int) r4)     // Catch:{ all -> 0x004c }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x002a }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x002a }
                goto L_0x0038
            L_0x002a:
                bo.app.bb r1 = bo.app.bb.this     // Catch:{ all -> 0x004c }
                java.io.File r1 = r1.d     // Catch:{ all -> 0x004c }
                r1.mkdirs()     // Catch:{ all -> 0x004c }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0040 }
                r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x0040 }
            L_0x0038:
                bo.app.bb$a$a r4 = new bo.app.bb$a$a     // Catch:{ all -> 0x004c }
                r2 = 0
                r4.<init>(r1)     // Catch:{ all -> 0x004c }
                monitor-exit(r0)     // Catch:{ all -> 0x004c }
                return r4
            L_0x0040:
                java.io.OutputStream r4 = bo.app.bb.q     // Catch:{ all -> 0x004c }
                monitor-exit(r0)     // Catch:{ all -> 0x004c }
                return r4
            L_0x0046:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x004c }
                r4.<init>()     // Catch:{ all -> 0x004c }
                throw r4     // Catch:{ all -> 0x004c }
            L_0x004c:
                r4 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x004c }
                throw r4
            L_0x004f:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Expected index "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r4 = " to be greater than 0 and less than the maximum value count of "
                r1.append(r4)
                bo.app.bb r4 = bo.app.bb.this
                int r4 = r4.j
                r1.append(r4)
                java.lang.String r4 = r1.toString()
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.bb.a.a(int):java.io.OutputStream");
        }

        public void a() {
            if (this.d) {
                bb.this.a(this, false);
                bb.this.c(this.b.a);
            } else {
                bb.this.a(this, true);
            }
            this.e = true;
        }

        public void b() {
            bb.this.a(this, false);
        }

        /* renamed from: bo.app.bb$a$a  reason: collision with other inner class name */
        class C0000a extends FilterOutputStream {
            private C0000a(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    boolean unused2 = a.this.d = true;
                }
            }
        }
    }
}

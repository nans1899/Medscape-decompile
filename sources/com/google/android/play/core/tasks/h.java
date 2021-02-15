package com.google.android.play.core.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

final class h<ResultT> {
    private final Object a = new Object();
    private Queue<g<ResultT>> b;
    private boolean c;

    h() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = r2.b.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        if (r0 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        r2.c = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0021, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        r0.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        r1 = r2.a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.google.android.play.core.tasks.Task<ResultT> r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.a
            monitor-enter(r0)
            java.util.Queue<com.google.android.play.core.tasks.g<ResultT>> r1 = r2.b     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x0029
            boolean r1 = r2.c     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0029
            r1 = 1
            r2.c = r1     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
        L_0x000f:
            java.lang.Object r1 = r2.a
            monitor-enter(r1)
            java.util.Queue<com.google.android.play.core.tasks.g<ResultT>> r0 = r2.b     // Catch:{ all -> 0x0026 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0026 }
            com.google.android.play.core.tasks.g r0 = (com.google.android.play.core.tasks.g) r0     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x0021
            r3 = 0
            r2.c = r3     // Catch:{ all -> 0x0026 }
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            return
        L_0x0021:
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            r0.a(r3)
            goto L_0x000f
        L_0x0026:
            r3 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0026 }
            throw r3
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.tasks.h.a(com.google.android.play.core.tasks.Task):void");
    }

    public final void a(g<ResultT> gVar) {
        synchronized (this.a) {
            if (this.b == null) {
                this.b = new ArrayDeque();
            }
            this.b.add(gVar);
        }
    }
}

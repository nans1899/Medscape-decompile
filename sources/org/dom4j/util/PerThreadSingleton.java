package org.dom4j.util;

public class PerThreadSingleton implements SingletonStrategy {
    private ThreadLocal perThreadCache = new ThreadLocal();
    private String singletonClassName = null;

    public void reset() {
        this.perThreadCache = new ThreadLocal();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r0 = java.lang.Class.forName(r3.singletonClassName).newInstance();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0034, code lost:
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0029 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object instance() {
        /*
            r3 = this;
            java.lang.ThreadLocal r0 = r3.perThreadCache
            java.lang.Object r0 = r0.get()
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            if (r0 == 0) goto L_0x0016
            java.lang.Object r1 = r0.get()
            if (r1 != 0) goto L_0x0011
            goto L_0x0016
        L_0x0011:
            java.lang.Object r0 = r0.get()
            goto L_0x003f
        L_0x0016:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x0029 }
            java.lang.ClassLoader r0 = r0.getContextClassLoader()     // Catch:{ Exception -> 0x0029 }
            java.lang.String r1 = r3.singletonClassName     // Catch:{ Exception -> 0x0029 }
            java.lang.Class r0 = r0.loadClass(r1)     // Catch:{ Exception -> 0x0029 }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x0029 }
            goto L_0x0035
        L_0x0029:
            java.lang.String r0 = r3.singletonClassName     // Catch:{ Exception -> 0x0034 }
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x0034 }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x0034 }
            goto L_0x0035
        L_0x0034:
            r0 = 0
        L_0x0035:
            java.lang.ThreadLocal r1 = r3.perThreadCache
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r0)
            r1.set(r2)
        L_0x003f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.util.PerThreadSingleton.instance():java.lang.Object");
    }

    public void setSingletonClassName(String str) {
        this.singletonClassName = str;
    }
}

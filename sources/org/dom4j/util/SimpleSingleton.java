package org.dom4j.util;

public class SimpleSingleton implements SingletonStrategy {
    private String singletonClassName = null;
    private Object singletonInstance = null;

    public Object instance() {
        return this.singletonInstance;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void reset() {
        /*
            r2 = this;
            java.lang.String r0 = r2.singletonClassName
            if (r0 == 0) goto L_0x0025
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x0019 }
            java.lang.ClassLoader r0 = r0.getContextClassLoader()     // Catch:{ Exception -> 0x0019 }
            java.lang.String r1 = r2.singletonClassName     // Catch:{ Exception -> 0x0019 }
            java.lang.Class r0 = r0.loadClass(r1)     // Catch:{ Exception -> 0x0019 }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x0019 }
            r2.singletonInstance = r0     // Catch:{ Exception -> 0x0019 }
            goto L_0x0025
        L_0x0019:
            java.lang.String r0 = r2.singletonClassName     // Catch:{ Exception -> 0x0025 }
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x0025 }
            java.lang.Object r0 = r0.newInstance()     // Catch:{ Exception -> 0x0025 }
            r2.singletonInstance = r0     // Catch:{ Exception -> 0x0025 }
        L_0x0025:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.util.SimpleSingleton.reset():void");
    }

    public void setSingletonClassName(String str) {
        this.singletonClassName = str;
        reset();
    }
}

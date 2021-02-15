package com.tapstream.sdk;

public class Logging {
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static Logger logger;

    public interface Logger {
        void log(int i, String str);
    }

    private static class DefaultLogger implements Logger {
        private DefaultLogger() {
        }

        public void log(int i, String str) {
            System.out.println(str);
        }
    }

    public static synchronized void setLogger(Logger logger2) {
        synchronized (Logging.class) {
            logger = logger2;
        }
    }

    public static synchronized boolean isConfigured() {
        boolean z;
        synchronized (Logging.class) {
            z = logger != null;
        }
        return z;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        logger.log(6, "Unhandled exception in the logging system. This should never happen.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void log(int r3, java.lang.String r4, java.lang.Object... r5) {
        /*
            java.lang.Class<com.tapstream.sdk.Logging> r0 = com.tapstream.sdk.Logging.class
            monitor-enter(r0)
            com.tapstream.sdk.Logging$Logger r1 = logger     // Catch:{ all -> 0x0026 }
            r2 = 0
            if (r1 != 0) goto L_0x000f
            com.tapstream.sdk.Logging$DefaultLogger r1 = new com.tapstream.sdk.Logging$DefaultLogger     // Catch:{ all -> 0x0026 }
            r1.<init>()     // Catch:{ all -> 0x0026 }
            logger = r1     // Catch:{ all -> 0x0026 }
        L_0x000f:
            if (r4 != 0) goto L_0x0012
            goto L_0x0016
        L_0x0012:
            java.lang.String r2 = java.lang.String.format(r4, r5)     // Catch:{ Exception -> 0x001c }
        L_0x0016:
            com.tapstream.sdk.Logging$Logger r4 = logger     // Catch:{ Exception -> 0x001c }
            r4.log(r3, r2)     // Catch:{ Exception -> 0x001c }
            goto L_0x0024
        L_0x001c:
            com.tapstream.sdk.Logging$Logger r3 = logger     // Catch:{ all -> 0x0026 }
            r4 = 6
            java.lang.String r5 = "Unhandled exception in the logging system. This should never happen."
            r3.log(r4, r5)     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r0)
            return
        L_0x0026:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapstream.sdk.Logging.log(int, java.lang.String, java.lang.Object[]):void");
    }
}

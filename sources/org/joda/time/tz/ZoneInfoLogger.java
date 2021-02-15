package org.joda.time.tz;

public class ZoneInfoLogger {
    static ThreadLocal<Boolean> cVerbose = new ThreadLocal<Boolean>() {
        /* access modifiers changed from: protected */
        public Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    public static boolean verbose() {
        return cVerbose.get().booleanValue();
    }

    public static void set(boolean z) {
        cVerbose.set(Boolean.valueOf(z));
    }
}

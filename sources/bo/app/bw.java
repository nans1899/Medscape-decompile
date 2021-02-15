package bo.app;

import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class bw {
    private static final String a = AppboyLogger.getAppboyLogTag(bw.class);
    private final List<String> b = new ArrayList(32);
    private long c;
    private boolean d = false;
    private final Object e = new Object();
    private bq f;

    public void a(String str, String str2, Throwable th) {
        if (this.d) {
            if ((str2 == null || (!str2.contains("device_logs") && !str2.contains("test_user_data"))) && !d()) {
                synchronized (this.e) {
                    if (this.b.size() >= 32) {
                        b();
                    }
                    if (this.b.isEmpty() || this.c == 0) {
                        this.c = du.a();
                    }
                    String b2 = b(str, str2, th);
                    if (b2 != null) {
                        this.b.add(b2);
                    }
                }
            }
        }
    }

    public void a(boolean z) {
        synchronized (this.e) {
            if (!z) {
                this.b.clear();
            } else {
                AppboyLogger.i(a, "Test user device logging is enabled.", false);
            }
        }
        this.d = z;
    }

    public void a(bq bqVar) {
        this.f = bqVar;
    }

    public void a(cn cnVar) {
        a(cnVar.k());
    }

    public boolean a() {
        return this.d;
    }

    static String b(String str, String str2, Throwable th) {
        if (StringUtils.isNullOrBlank(str)) {
            return null;
        }
        if (StringUtils.isNullOrBlank(str2) && (th == null || StringUtils.isNullOrBlank(th.getMessage()))) {
            return null;
        }
        String str3 = c() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str;
        if (str2 != null) {
            str3 = str3 + ": " + str2;
        }
        if (th != null) {
            str3 = str3 + ": " + th.getMessage();
        }
        return str3.substring(0, Math.min(str3.length(), 1000));
    }

    private static String c() {
        return du.a(new Date(), u.ANDROID_LOGCAT);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        synchronized (this.e) {
            if (this.f != null) {
                ArrayList arrayList = new ArrayList();
                for (String add : this.b) {
                    arrayList.add(add);
                }
                this.f.a((List<String>) arrayList, this.c);
            }
            this.b.clear();
            this.c = 0;
        }
    }

    private boolean d() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace == null || stackTrace.length == 0) {
            return true;
        }
        StackTraceElement stackTraceElement = stackTrace[1];
        String methodName = stackTraceElement.getMethodName();
        String className = stackTraceElement.getClassName();
        int i = 0;
        for (StackTraceElement stackTraceElement2 : stackTrace) {
            if (stackTraceElement2.getClassName().equals(className) && stackTraceElement2.getMethodName().equals(methodName)) {
                i++;
            }
        }
        if (i != 1) {
            return true;
        }
        return false;
    }
}

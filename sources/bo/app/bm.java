package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.support.AppboyLogger;

public class bm {
    private static final String d = AppboyLogger.getAppboyLogTag(bm.class);
    final SharedPreferences a;
    final ad b;
    boolean c = false;
    private final dq e;

    public bm(Context context, ad adVar, dq dqVar) {
        this.b = adVar;
        this.e = dqVar;
        this.a = context.getSharedPreferences("com.appboy.storage.sessions.messaging_session", 0);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (c()) {
            AppboyLogger.d(d, "Publishing new messaging session event.");
            this.b.a(al.a, al.class);
            this.c = true;
            return;
        }
        AppboyLogger.d(d, "Messaging session not started.");
    }

    /* access modifiers changed from: package-private */
    public void b() {
        long a2 = du.a();
        String str = d;
        AppboyLogger.d(str, "Messaging session stopped. Adding new messaging session timestamp: " + a2);
        this.a.edit().putLong("messaging_session_timestamp", a2).apply();
        this.c = false;
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        long f = this.e.f();
        if (f == -1 || this.c) {
            return false;
        }
        long j = this.a.getLong("messaging_session_timestamp", -1);
        long a2 = du.a();
        String str = d;
        AppboyLogger.d(str, "Messaging session timeout: " + f + ", current diff: " + (a2 - j));
        if (j + f < a2) {
            return true;
        }
        return false;
    }
}

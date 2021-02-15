package bo.app;

import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.Collections;

public class di implements dl {
    private static final String a = AppboyLogger.getAppboyLogTag(di.class);
    private final dl b;
    private final ad c;
    private boolean d = false;

    public di(dl dlVar, ad adVar) {
        this.b = dlVar;
        this.c = adVar;
    }

    public void a(bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not adding event: " + bzVar);
            return;
        }
        try {
            this.b.a(bzVar);
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to insert event into storage.", e);
            a(this.c, e);
        }
    }

    public void b(bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not deleting event: " + bzVar);
            return;
        }
        try {
            this.b.b(bzVar);
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to delete event from storage.", e);
            a(this.c, e);
        }
    }

    public Collection<bz> a() {
        if (this.d) {
            AppboyLogger.w(a, "Storage provider is closed. Not getting all events.");
            return Collections.emptyList();
        }
        try {
            return this.b.a();
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to get all events from storage.", e);
            a(this.c, e);
            return Collections.emptyList();
        }
    }

    public void b() {
        AppboyLogger.w(a, "Setting this provider and internal storage provider to closed.");
        this.d = true;
        this.b.b();
    }

    private void a(ad adVar, Throwable th) {
        try {
            adVar.a(new av("A database exception has occurred. Please view the stack trace for more details.", th), av.class);
        } catch (Exception e) {
            AppboyLogger.e(a, "Failed to log throwable.", e);
        }
    }
}

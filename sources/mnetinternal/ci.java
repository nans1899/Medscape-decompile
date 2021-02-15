package mnetinternal;

import android.content.Context;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import net.media.android.bidder.base.logging.Logger;

public final class ci {
    /* access modifiers changed from: private */
    public static ci a = new ci();
    private List<ch> b = Collections.synchronizedList(new ArrayList());
    private AtomicBoolean c = new AtomicBoolean(false);

    private ci() {
    }

    public static void a(final Context context) {
        aa.a((Runnable) new ac() {
            public void a() {
                ci.a.b(context);
                ci.a.c();
            }
        });
    }

    public static ci a() {
        return a;
    }

    /* access modifiers changed from: private */
    public void b(Context context) {
        if (this.b.isEmpty()) {
            this.b.add(new cj(context));
            this.b.add(new cg(context));
            try {
                this.b.add(new cl(context));
            } catch (IOException e) {
                Logger.error("##DataTrackerManager##", e.getMessage(), e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        Logger.info("##DataTrackerManager##", "starting trackers");
        for (ch a2 : this.b) {
            a2.a();
        }
        this.c.set(true);
    }

    public void a(int i) {
        Logger.info("##DataTrackerManager##", "updating trackers");
        if (this.c.get()) {
            for (ch a2 : this.b) {
                a2.a(i);
            }
        }
    }
}

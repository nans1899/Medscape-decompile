package net.media.android.bidder.base.configs;

import com.mnet.gson.k;
import com.mnet.gson.n;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import mnetinternal.x;
import mnetinternal.z;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.internal.AdTrackerEvent;

public final class b {
    private static volatile b a;
    /* access modifiers changed from: private */
    public Map<String, Object> b = new ConcurrentHashMap();
    private Map<String, Object> c = new ConcurrentHashMap();

    private b() {
        c();
        x.a().a(AdTrackerEvent.EVENT_CONFIG_FETCH_COMPLETE, new z() {
            public void a(Object obj) {
                if (b.this.b != null) {
                    b.this.b.clear();
                    b.this.b();
                }
            }
        });
    }

    public static b a() {
        b bVar = a;
        if (bVar == null) {
            synchronized (b.class) {
                bVar = a;
                if (bVar == null) {
                    bVar = new b();
                    a = bVar;
                }
            }
        }
        bVar.b();
        return bVar;
    }

    /* access modifiers changed from: private */
    public void b() {
        Map<String, Object> map = this.b;
        if (map == null || map.size() <= 0) {
            Logger.debug("##PublisherTimeoutConfig##", "getting publisher timeout configs");
            n a2 = a.a().a("publisher_timeout_settings");
            if (a2 == null) {
                Logger.debug("##PublisherTimeoutConfig##", "publisher settings json null");
            } else {
                this.b.putAll((Map) a.b().a((k) a2, Map.class));
            }
        }
    }

    private void c() {
        this.c.put("gptrd", 1);
        this.c.put("hb_delay_extra", 100);
    }

    public int a(String str) {
        Map<String, Object> map = this.b;
        if (map != null && map.get(str) != null) {
            return (int) ((Double) this.b.get(str)).doubleValue();
        }
        Logger.debug("##PublisherTimeoutConfig##", "configs not available, returning default value");
        return ((Integer) this.c.get(str)).intValue();
    }
}

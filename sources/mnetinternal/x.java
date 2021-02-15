package mnetinternal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.media.android.bidder.base.logging.Logger;

public final class x {
    private static volatile x c;
    /* access modifiers changed from: private */
    public Map<String, List<z>> a = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<String, y> b = new ConcurrentHashMap();
    private ExecutorService d = Executors.newSingleThreadExecutor();

    private x() {
    }

    public static x a() {
        x xVar = c;
        if (xVar == null) {
            synchronized (x.class) {
                xVar = c;
                if (xVar == null) {
                    xVar = new x();
                    c = xVar;
                }
            }
        }
        return xVar;
    }

    public void a(final y yVar) {
        Logger.debug("###EventHub###", "Publishing action happened " + yVar.a());
        if (this.a.containsKey(yVar.a())) {
            this.d.submit(new ac() {
                public void a() {
                    x.this.b.put(yVar.a(), yVar);
                    for (z a2 : new ArrayList((Collection) x.this.a.get(yVar.a()))) {
                        try {
                            a2.a(yVar.b());
                        } catch (Exception e) {
                            Logger.error("###EventHub###", e.getMessage());
                            Logger.notify("###EventHub###", e.getMessage(), e);
                        }
                    }
                }
            });
        }
    }

    public y a(String str, z zVar) {
        Logger.debug("###EventHub###", "Registering listener for action " + str);
        if (this.b.containsKey(str) && !this.a.containsKey(str)) {
            this.a.get(str).add(zVar);
            return this.b.get(str);
        } else if (this.a.containsKey(str)) {
            this.a.get(str).add(zVar);
            return null;
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(zVar);
            this.a.put(str, arrayList);
            return null;
        }
    }

    public void b(String str, z zVar) {
        Map<String, List<z>> map;
        Logger.debug("###EventHub###", "Un-registering event hub action listener");
        if (this.b.containsKey(str) && (map = this.a) != null && map.get(str) != null) {
            this.a.get(str).remove(zVar);
        }
    }
}

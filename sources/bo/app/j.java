package bo.app;

import com.appboy.support.AppboyLogger;
import java.net.URI;
import java.util.Map;
import org.json.JSONObject;

final class j implements g {
    private static final String a = AppboyLogger.getAppboyLogTag(j.class);
    private final g b;

    public j(g gVar) {
        this.b = gVar;
    }

    public JSONObject a(URI uri, Map<String, String> map) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            return this.b.a(uri, map);
        } finally {
            String a2 = eb.a(uri, map, y.GET);
            long currentTimeMillis2 = System.currentTimeMillis();
            String str = a;
            AppboyLogger.d(str, "Request(id = " + a2 + ") Executed in [" + (currentTimeMillis2 - currentTimeMillis) + "ms] [" + y.GET.toString() + " : " + uri.toString() + "]");
        }
    }

    public JSONObject a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        URI uri2 = uri;
        Map<String, String> map2 = map;
        JSONObject jSONObject2 = jSONObject;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            return this.b.a(uri2, map2, jSONObject2);
        } finally {
            char c = 3;
            String a2 = eb.a(uri2, map2, jSONObject2, y.POST);
            long currentTimeMillis2 = System.currentTimeMillis();
            String str = a;
            AppboyLogger.d(str, "Request(id = " + a2 + ") Executed in [" + (currentTimeMillis2 - currentTimeMillis) + "ms] [" + y.POST.toString() + ":" + uri.toString() + "]");
        }
    }
}

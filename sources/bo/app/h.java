package bo.app;

import com.appboy.support.AppboyLogger;
import java.net.URI;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;

final class h implements g {
    private static final String a = AppboyLogger.getAppboyLogTag(h.class);
    private final g b;

    public h(g gVar) {
        this.b = gVar;
    }

    public JSONObject a(URI uri, Map<String, String> map) {
        String a2 = eb.a(uri, map, y.GET);
        a(uri, map, a2);
        JSONObject a3 = this.b.a(uri, map);
        a(a3, a2);
        return a3;
    }

    public JSONObject a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        String a2 = eb.a(uri, map, jSONObject, y.POST);
        a(uri, map, jSONObject, a2);
        JSONObject a3 = this.b.a(uri, map, jSONObject);
        a(a3, a2);
        return a3;
    }

    private void a(URI uri, Map<String, String> map, String str) {
        try {
            String str2 = a;
            AppboyLogger.d(str2, "Making request(id = " + str + ") to [" + uri.toString() + "] \nwith headers: [" + a(map) + "]");
        } catch (Exception e) {
            AppboyLogger.d(a, "Exception while logging request: ", (Throwable) e);
        }
    }

    private void a(URI uri, Map<String, String> map, JSONObject jSONObject, String str) {
        try {
            String str2 = a;
            AppboyLogger.d(str2, "Making request(id = " + str + ") to [" + uri.toString() + "] \nwith headers: [" + a(map) + "] \nand JSON parameters: \n[" + ed.a(jSONObject) + "]");
        } catch (Exception e) {
            AppboyLogger.d(a, "Exception while logging request: ", (Throwable) e);
        }
    }

    private void a(JSONObject jSONObject, String str) {
        String str2;
        if (jSONObject == null) {
            str2 = "none";
        } else {
            try {
                str2 = ed.a(jSONObject);
            } catch (Exception e) {
                AppboyLogger.d(a, "Exception while logging result: ", (Throwable) e);
                return;
            }
        }
        String str3 = a;
        AppboyLogger.d(str3, "Result(id = " + str + ") \n[" + str2 + "]");
    }

    private String a(Map<String, String> map) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry next : map.entrySet()) {
            arrayList.add("(" + ((String) next.getKey()) + " / " + ((String) next.getValue()) + ")");
        }
        StringBuilder sb = new StringBuilder();
        for (String append : arrayList) {
            sb.append(append);
            sb.append(", ");
        }
        if (sb.length() == 0) {
            return "";
        }
        return sb.substring(0, sb.length() - 2);
    }
}

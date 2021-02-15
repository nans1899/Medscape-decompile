package bo.app;

import org.json.JSONArray;
import org.json.JSONObject;

public final class gf {
    private static gl a(gg ggVar) {
        return new gk(ggVar);
    }

    public static gh a(String str, String str2, gl glVar) {
        Object a = gi.a(str);
        Object a2 = gi.a(str2);
        boolean z = a instanceof JSONObject;
        if (z && (a2 instanceof JSONObject)) {
            return a((JSONObject) a, (JSONObject) a2, glVar);
        }
        if ((a instanceof JSONArray) && (a2 instanceof JSONArray)) {
            return a((JSONArray) a, (JSONArray) a2, glVar);
        }
        if ((a instanceof gc) && (a2 instanceof gc)) {
            return a((gc) a, (gc) a2);
        }
        if (z) {
            return new gh().a("", a, a2);
        }
        return new gh().a("", a, a2);
    }

    public static gh a(JSONObject jSONObject, JSONObject jSONObject2, gl glVar) {
        return glVar.a(jSONObject, jSONObject2);
    }

    public static gh a(JSONArray jSONArray, JSONArray jSONArray2, gl glVar) {
        return glVar.a(jSONArray, jSONArray2);
    }

    public static gh a(gc gcVar, gc gcVar2) {
        gh ghVar = new gh();
        if (!gcVar.a().equals(gcVar2.a())) {
            ghVar.a("");
        }
        return ghVar;
    }

    public static gh a(String str, String str2, gg ggVar) {
        return a(str, str2, a(ggVar));
    }

    public static gh a(JSONObject jSONObject, JSONObject jSONObject2, gg ggVar) {
        return a(jSONObject, jSONObject2, a(ggVar));
    }
}

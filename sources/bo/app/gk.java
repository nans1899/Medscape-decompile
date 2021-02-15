package bo.app;

import org.json.JSONArray;
import org.json.JSONObject;

public class gk extends gj {
    gg a;

    public gk(gg ggVar) {
        this.a = ggVar;
    }

    public void c(String str, JSONObject jSONObject, JSONObject jSONObject2, gh ghVar) {
        b(str, jSONObject, jSONObject2, ghVar);
        if (!this.a.a()) {
            a(str, jSONObject, jSONObject2, ghVar);
        }
    }

    public void a(String str, Object obj, Object obj2, gh ghVar) {
        if (!(obj instanceof Number) || !(obj2 instanceof Number)) {
            if (!obj.getClass().isAssignableFrom(obj2.getClass())) {
                ghVar.a(str, obj, obj2);
            } else if (obj instanceof JSONArray) {
                e(str, (JSONArray) obj, (JSONArray) obj2, ghVar);
            } else if (obj instanceof JSONObject) {
                c(str, (JSONObject) obj, (JSONObject) obj2, ghVar);
            } else if (!obj.equals(obj2)) {
                ghVar.a(str, obj, obj2);
            }
        } else if (((Number) obj).doubleValue() != ((Number) obj2).doubleValue()) {
            ghVar.a(str, obj, obj2);
        }
    }

    public void e(String str, JSONArray jSONArray, JSONArray jSONArray2, gh ghVar) {
        if (jSONArray.length() != jSONArray2.length()) {
            ghVar.a(str + "[]: Expected " + jSONArray.length() + " values but got " + jSONArray2.length());
        } else if (jSONArray.length() != 0) {
            if (this.a.b()) {
                c(str, jSONArray, jSONArray2, ghVar);
            } else if (gm.c(jSONArray)) {
                b(str, jSONArray, jSONArray2, ghVar);
            } else if (gm.d(jSONArray)) {
                a(str, jSONArray, jSONArray2, ghVar);
            } else {
                d(str, jSONArray, jSONArray2, ghVar);
            }
        }
    }
}

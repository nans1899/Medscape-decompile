package bo.app;

import java.util.HashSet;
import java.util.Map;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONObject;

public abstract class gj implements gl {
    public final gh a(JSONObject jSONObject, JSONObject jSONObject2) {
        gh ghVar = new gh();
        c("", jSONObject, jSONObject2, ghVar);
        return ghVar;
    }

    public final gh a(JSONArray jSONArray, JSONArray jSONArray2) {
        gh ghVar = new gh();
        e("", jSONArray, jSONArray2, ghVar);
        return ghVar;
    }

    /* access modifiers changed from: protected */
    public void a(String str, JSONObject jSONObject, JSONObject jSONObject2, gh ghVar) {
        for (String next : gm.a(jSONObject2)) {
            if (!jSONObject.has(next)) {
                ghVar.b(str, next);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(String str, JSONObject jSONObject, JSONObject jSONObject2, gh ghVar) {
        for (String next : gm.a(jSONObject)) {
            Object obj = jSONObject.get(next);
            if (jSONObject2.has(next)) {
                a(gm.a(str, next), obj, jSONObject2.get(next), ghVar);
            } else {
                ghVar.a(str, next);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, JSONArray jSONArray, JSONArray jSONArray2, gh ghVar) {
        String a = gm.a(jSONArray);
        if (a == null || !gm.a(a, jSONArray2)) {
            d(str, jSONArray, jSONArray2, ghVar);
            return;
        }
        Map<Object, JSONObject> a2 = gm.a(jSONArray, a);
        Map<Object, JSONObject> a3 = gm.a(jSONArray2, a);
        for (Object next : a2.keySet()) {
            if (!a3.containsKey(next)) {
                ghVar.a(gm.a(str, a, next), a2.get(next));
            } else {
                a(gm.a(str, a, next), a2.get(next), a3.get(next), ghVar);
            }
        }
        for (Object next2 : a3.keySet()) {
            if (!a2.containsKey(next2)) {
                ghVar.b(gm.a(str, a, next2), a3.get(next2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(String str, JSONArray jSONArray, JSONArray jSONArray2, gh ghVar) {
        Map<T, Integer> a = gm.a(gm.b(jSONArray));
        Map<T, Integer> a2 = gm.a(gm.b(jSONArray2));
        for (T next : a.keySet()) {
            if (!a2.containsKey(next)) {
                ghVar.a(str + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, next);
            } else if (!a2.get(next).equals(a.get(next))) {
                ghVar.a(str + "[]: Expected " + a.get(next) + " occurrence(s) of " + next + " but got " + a2.get(next) + " occurrence(s)");
            }
        }
        for (T next2 : a2.keySet()) {
            if (!a.containsKey(next2)) {
                ghVar.b(str + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI, next2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void c(String str, JSONArray jSONArray, JSONArray jSONArray2, gh ghVar) {
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            Object obj2 = jSONArray2.get(i);
            a(str + "[" + i + "]", obj, obj2, ghVar);
        }
    }

    /* access modifiers changed from: protected */
    public void d(String str, JSONArray jSONArray, JSONArray jSONArray2, gh ghVar) {
        boolean z;
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj = jSONArray.get(i);
            int i2 = 0;
            while (true) {
                z = true;
                if (i2 >= jSONArray2.length()) {
                    z = false;
                    break;
                }
                Object obj2 = jSONArray2.get(i2);
                if (!hashSet.contains(Integer.valueOf(i2)) && obj2.getClass().equals(obj.getClass())) {
                    if (obj instanceof JSONObject) {
                        if (a((JSONObject) obj, (JSONObject) obj2).a()) {
                            hashSet.add(Integer.valueOf(i2));
                            break;
                        }
                    } else if (obj instanceof JSONArray) {
                        if (a((JSONArray) obj, (JSONArray) obj2).a()) {
                            hashSet.add(Integer.valueOf(i2));
                            break;
                        }
                    } else if (obj.equals(obj2)) {
                        hashSet.add(Integer.valueOf(i2));
                        break;
                    }
                }
                i2++;
            }
            if (!z) {
                ghVar.a(str + "[" + i + "] Could not find match for element " + obj);
                return;
            }
        }
    }
}

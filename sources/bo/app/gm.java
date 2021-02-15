package bo.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONObject;

public final class gm {
    private static Integer a = new Integer(1);

    public static Map<Object, JSONObject> a(JSONArray jSONArray, String str) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            hashMap.put(jSONObject.get(str), jSONObject);
        }
        return hashMap;
    }

    public static String a(JSONArray jSONArray) {
        for (String next : a((JSONObject) jSONArray.get(0))) {
            if (a(next, jSONArray)) {
                return next;
            }
        }
        return null;
    }

    public static boolean a(String str, JSONArray jSONArray) {
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < jSONArray.length()) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.has(str)) {
                    Object obj2 = jSONObject.get(str);
                    if (a(obj2) && !hashSet.contains(obj2)) {
                        hashSet.add(obj2);
                        i++;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static List<Object> b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.get(i));
        }
        return arrayList;
    }

    public static boolean c(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!a(jSONArray.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Object obj) {
        return !(obj instanceof JSONObject) && !(obj instanceof JSONArray);
    }

    public static boolean d(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            if (!(jSONArray.get(i) instanceof JSONObject)) {
                return false;
            }
        }
        return true;
    }

    public static Set<String> a(JSONObject jSONObject) {
        TreeSet treeSet = new TreeSet();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            treeSet.add(keys.next());
        }
        return treeSet;
    }

    public static String a(String str, String str2) {
        if ("".equals(str)) {
            return str2;
        }
        return str + "." + str2;
    }

    public static String a(String str, String str2, Object obj) {
        return str + "[" + str2 + "=" + obj + "]";
    }

    public static <T> Map<T, Integer> a(Collection<T> collection) {
        HashMap hashMap = new HashMap();
        for (T next : collection) {
            Integer num = (Integer) hashMap.get(next);
            if (num == null) {
                hashMap.put(next, a);
            } else {
                hashMap.put(next, new Integer(num.intValue() + 1));
            }
        }
        return hashMap;
    }
}

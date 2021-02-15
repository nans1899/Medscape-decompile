package bo.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class gi {
    public static Object a(final String str) {
        if (str.trim().startsWith("{")) {
            return new JSONObject(str);
        }
        if (str.trim().startsWith("[")) {
            return new JSONArray(str);
        }
        if (str.trim().startsWith("\"") || str.trim().matches("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?")) {
            return new gc() {
                public String a() {
                    return str;
                }
            };
        }
        throw new JSONException("Unparsable JSON string: " + str);
    }
}

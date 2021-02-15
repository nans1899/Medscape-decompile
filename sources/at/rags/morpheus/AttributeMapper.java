package at.rags.morpheus;

import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AttributeMapper {
    private Deserializer deserializer;
    private Gson gson;

    public AttributeMapper() {
        this.deserializer = new Deserializer();
        this.gson = new Gson();
    }

    public AttributeMapper(Deserializer deserializer2, Gson gson2) {
        this.deserializer = deserializer2;
        this.gson = gson2;
    }

    public void mapAttributeToObject(Resource resource, JSONObject jSONObject, Field field, String str) {
        try {
            Object obj = jSONObject.get(str);
            if (obj instanceof JSONArray) {
                List<Object> list = null;
                try {
                    list = createListFromJSONArray(jSONObject.getJSONArray(str), field);
                } catch (JSONException unused) {
                    Logger.debug(str + " is not an valid JSONArray.");
                }
                this.deserializer.setField(resource, field.getName(), list);
            } else if (obj.getClass() == JSONObject.class) {
                this.deserializer.setField(resource, field.getName(), this.gson.fromJson(obj.toString(), field.getType()));
            } else {
                this.deserializer.setField(resource, field.getName(), obj);
            }
        } catch (JSONException unused2) {
            Logger.debug("JSON attributes does not contain " + str);
        }
    }

    private List<Object> createListFromJSONArray(JSONArray jSONArray, Field field) {
        Type genericType = field.getGenericType();
        ArrayList arrayList = new ArrayList();
        if (genericType instanceof ParameterizedType) {
            for (Type type : ((ParameterizedType) genericType).getActualTypeArguments()) {
                Class<String> cls = (Class) type;
                for (int i = 0; jSONArray.length() > i; i++) {
                    Object obj = null;
                    try {
                        Object obj2 = jSONArray.get(i);
                        if (cls == String.class) {
                            obj = obj2.toString();
                        } else {
                            try {
                                obj = this.gson.fromJson(jSONArray.get(i).toString(), cls);
                            } catch (JSONException unused) {
                                Logger.debug("JSONArray does not contain index " + i + ".");
                            }
                        }
                        arrayList.add(obj);
                    } catch (JSONException unused2) {
                        Logger.debug("JSONArray does not contain index " + i + ".");
                    }
                }
            }
        }
        return arrayList;
    }

    public HashMap<String, Object> createMapFromJSONObject(JSONObject jSONObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                hashMap.put(next, jSONObject.get(next));
            } catch (JSONException unused) {
                Logger.debug("JSON does not contain " + next + ".");
            }
        }
        return hashMap;
    }
}

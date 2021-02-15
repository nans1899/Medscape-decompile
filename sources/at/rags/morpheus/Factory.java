package at.rags.morpheus;

import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Factory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Deserializer deserializer = new Deserializer();
    private static Mapper mapper = new Mapper();

    public static Resource newObjectFromJSONObject(JSONObject jSONObject, List<Resource> list) throws Exception {
        try {
            Resource createObjectFromString = deserializer.createObjectFromString(getTypeFromJson(jSONObject));
            try {
                createObjectFromString = mapper.mapId(createObjectFromString, jSONObject);
            } catch (Exception unused) {
                Logger.debug("JSON data does not contain id");
            }
            try {
                createObjectFromString = mapper.mapAttributes(createObjectFromString, jSONObject.getJSONObject(JSONAPISpecConstants.ATTRIBUTES));
            } catch (Exception unused2) {
                Logger.debug("JSON data does not contain attributes");
            }
            try {
                createObjectFromString = mapper.mapRelations(createObjectFromString, jSONObject.getJSONObject(JSONAPISpecConstants.RELATIONSHIPS), list);
            } catch (Exception unused3) {
                Logger.debug("JSON data does not contain relationships");
            }
            try {
                createObjectFromString.setMeta(mapper.getAttributeMapper().createMapFromJSONObject(jSONObject.getJSONObject(JSONAPISpecConstants.META)));
            } catch (Exception unused4) {
                Logger.debug("JSON data does not contain meta");
            }
            try {
                createObjectFromString.setLinks(mapper.mapLinks(jSONObject.getJSONObject(JSONAPISpecConstants.LINKS)));
            } catch (JSONException unused5) {
                Logger.debug("JSON data does not contain links");
            }
            return createObjectFromString;
        } catch (Exception e) {
            throw e;
        }
    }

    public static List<Resource> newObjectFromJSONArray(JSONArray jSONArray, List<Resource> list) throws Exception {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < jSONArray.length()) {
            JSONObject jSONObject = null;
            try {
                jSONObject = jSONArray.getJSONObject(i);
            } catch (JSONException unused) {
                Logger.debug("Was not able to get dataArray[" + i + "] as JSONObject.");
            }
            try {
                arrayList.add(newObjectFromJSONObject(jSONObject, list));
                i++;
            } catch (Exception e) {
                throw e;
            }
        }
        return arrayList;
    }

    public static String getTypeFromJson(JSONObject jSONObject) {
        try {
            return jSONObject.getString("type");
        } catch (JSONException unused) {
            Logger.debug("JSON data does not contain type");
            return null;
        }
    }

    public static void setDeserializer(Deserializer deserializer2) {
        deserializer = deserializer2;
    }

    public static void setMapper(Mapper mapper2) {
        mapper = mapper2;
    }
}

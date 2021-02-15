package at.rags.morpheus;

import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Morpheus {
    private Mapper mapper;

    public Morpheus() {
        this.mapper = new Mapper();
    }

    public Morpheus(AttributeMapper attributeMapper) {
        Mapper mapper2 = new Mapper(new Deserializer(), new Serializer(), attributeMapper);
        this.mapper = mapper2;
        Factory.setMapper(mapper2);
    }

    public JsonApiObject parse(String str) throws Exception {
        try {
            return parseFromJSONObject(new JSONObject(str));
        } catch (JSONException e) {
            throw e;
        }
    }

    private JsonApiObject parseFromJSONObject(JSONObject jSONObject) throws Exception {
        JsonApiObject jsonApiObject = new JsonApiObject();
        try {
            jsonApiObject.setIncluded(Factory.newObjectFromJSONArray(jSONObject.getJSONArray(JSONAPISpecConstants.INCLUDED), (List<Resource>) null));
        } catch (JSONException unused) {
            Logger.debug("JSON does not contain included");
        }
        try {
            jsonApiObject.setResources(Factory.newObjectFromJSONArray(jSONObject.getJSONArray("data"), jsonApiObject.getIncluded()));
        } catch (JSONException unused2) {
            Logger.debug("JSON does not contain data array");
        }
        try {
            jsonApiObject.setResource(Factory.newObjectFromJSONObject(jSONObject.getJSONObject("data"), jsonApiObject.getIncluded()));
        } catch (JSONException unused3) {
            Logger.debug("JSON does not contain data object");
        }
        try {
            jsonApiObject.setLinks(this.mapper.mapLinks(jSONObject.getJSONObject(JSONAPISpecConstants.LINKS)));
        } catch (JSONException unused4) {
            Logger.debug("JSON does not contain links object");
        }
        try {
            jsonApiObject.setMeta(this.mapper.getAttributeMapper().createMapFromJSONObject(jSONObject.getJSONObject(JSONAPISpecConstants.META)));
        } catch (JSONException unused5) {
            Logger.debug("JSON does not contain meta object");
        }
        try {
            jsonApiObject.setErrors(this.mapper.mapErrors(jSONObject.getJSONArray(JSONAPISpecConstants.ERRORS)));
        } catch (JSONException unused6) {
            Logger.debug("JSON does not contain errors object");
        }
        return jsonApiObject;
    }

    public String createJson(JsonApiObject jsonApiObject, Boolean bool) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        if (jsonApiObject.getResource() != null) {
            HashMap<String, Object> createData = this.mapper.createData(jsonApiObject.getResource(), true);
            if (createData != null) {
                hashMap.put("data", createData);
            }
            if (bool.booleanValue()) {
                arrayList.addAll(this.mapper.createIncluded(jsonApiObject.getResource()));
            }
        }
        if (jsonApiObject.getResources() != null) {
            ArrayList<HashMap<String, Object>> createData2 = this.mapper.createData(jsonApiObject.getResources(), true);
            if (createData2 != null) {
                hashMap.put("data", createData2);
            }
            if (bool.booleanValue()) {
                for (Resource createIncluded : jsonApiObject.getResources()) {
                    arrayList.addAll(this.mapper.createIncluded(createIncluded));
                }
            }
        }
        if (bool.booleanValue()) {
            hashMap.put(JSONAPISpecConstants.INCLUDED, arrayList);
        }
        return new GsonBuilder().serializeNulls().create().toJson((Object) hashMap);
    }
}

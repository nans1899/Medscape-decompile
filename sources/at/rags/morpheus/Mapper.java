package at.rags.morpheus;

import at.rags.morpheus.Annotations.Relationship;
import at.rags.morpheus.Exceptions.NotExtendingResourceException;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Mapper {
    private AttributeMapper attributeMapper;
    private Deserializer deserializer;
    private Serializer serializer;

    public Mapper() {
        this.deserializer = new Deserializer();
        this.serializer = new Serializer();
        this.attributeMapper = new AttributeMapper();
    }

    public Mapper(Deserializer deserializer2, Serializer serializer2, AttributeMapper attributeMapper2) {
        this.deserializer = deserializer2;
        this.serializer = serializer2;
        this.attributeMapper = attributeMapper2;
    }

    public Links mapLinks(JSONObject jSONObject) {
        Links links = new Links();
        try {
            links.setSelfLink(jSONObject.getString(JSONAPISpecConstants.SELF));
        } catch (JSONException unused) {
            Logger.debug("JSON link does not contain self");
        }
        try {
            links.setRelated(jSONObject.getString(JSONAPISpecConstants.RELATED));
        } catch (JSONException unused2) {
            Logger.debug("JSON link does not contain related");
        }
        try {
            links.setFirst(jSONObject.getString(JSONAPISpecConstants.FIRST));
        } catch (JSONException unused3) {
            Logger.debug("JSON link does not contain first");
        }
        try {
            links.setLast(jSONObject.getString(JSONAPISpecConstants.LAST));
        } catch (JSONException unused4) {
            Logger.debug("JSON link does not contain last");
        }
        try {
            links.setPrev(jSONObject.getString(JSONAPISpecConstants.PREV));
        } catch (JSONException unused5) {
            Logger.debug("JSON link does not contain prev");
        }
        try {
            links.setNext(jSONObject.getString(JSONAPISpecConstants.NEXT));
        } catch (JSONException unused6) {
            Logger.debug("JSON link does not contain next");
        }
        return links;
    }

    public Resource mapId(Resource resource, JSONObject jSONObject) throws NotExtendingResourceException {
        try {
            return this.deserializer.setIdField(resource, jSONObject.get("id"));
        } catch (JSONException unused) {
            Logger.debug("JSON data does not contain id.");
            return resource;
        }
    }

    public Resource mapAttributes(Resource resource, JSONObject jSONObject) {
        if (jSONObject == null) {
            return resource;
        }
        for (Field field : resource.getClass().getDeclaredFields()) {
            String name = field.getName();
            boolean z = false;
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation.annotationType() == SerializedName.class) {
                    name = ((SerializedName) annotation).value();
                }
                if (annotation.annotationType() == Relationship.class) {
                    z = true;
                }
            }
            if (!z) {
                this.attributeMapper.mapAttributeToObject(resource, jSONObject, field, name);
            }
        }
        return resource;
    }

    public Resource mapRelations(Resource resource, JSONObject jSONObject, List<Resource> list) throws Exception {
        HashMap<String, String> relationshipNames = getRelationshipNames(resource.getClass());
        for (String next : relationshipNames.keySet()) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                try {
                    this.deserializer.setField(resource, relationshipNames.get(next), matchIncludedToRelation(Factory.newObjectFromJSONObject(jSONObject2.getJSONObject("data"), (List<Resource>) null), list));
                } catch (JSONException unused) {
                    Logger.debug("JSON relationship does not contain data");
                }
                try {
                    this.deserializer.setField(resource, relationshipNames.get(next), matchIncludedToRelation(Factory.newObjectFromJSONArray(jSONObject2.getJSONArray("data"), (List<Resource>) null), list));
                } catch (JSONException unused2) {
                    Logger.debug("JSON relationship does not contain data");
                }
            } catch (JSONException unused3) {
                Logger.debug("Relationship named " + next + "not found in JSON");
            }
        }
        return resource;
    }

    public Resource matchIncludedToRelation(Resource resource, List<Resource> list) {
        if (list == null) {
            return resource;
        }
        for (Resource next : list) {
            if (resource.getId().equals(next.getId()) && resource.getClass().equals(next.getClass())) {
                return next;
            }
        }
        return resource;
    }

    public List<Resource> matchIncludedToRelation(List<Resource> list, List<Resource> list2) {
        ArrayList arrayList = new ArrayList();
        for (Resource matchIncludedToRelation : list) {
            arrayList.add(matchIncludedToRelation(matchIncludedToRelation, list2));
        }
        return arrayList;
    }

    public List<Error> mapErrors(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; jSONArray.length() > i; i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Error error = new Error();
                try {
                    error.setId(jSONObject.getString("id"));
                } catch (JSONException unused) {
                    Logger.debug("JSON object does not contain id");
                }
                try {
                    error.setStatus(jSONObject.getString("status"));
                } catch (JSONException unused2) {
                    Logger.debug("JSON object does not contain status");
                }
                try {
                    error.setCode(jSONObject.getString("code"));
                } catch (JSONException unused3) {
                    Logger.debug("JSON object does not contain code");
                }
                try {
                    error.setTitle(jSONObject.getString("title"));
                } catch (JSONException unused4) {
                    Logger.debug("JSON object does not contain title");
                }
                try {
                    error.setDetail(jSONObject.getString("detail"));
                } catch (JSONException unused5) {
                    Logger.debug("JSON object does not contain detail");
                }
                JSONObject jSONObject2 = null;
                try {
                    jSONObject2 = jSONObject.getJSONObject("source");
                } catch (JSONException unused6) {
                    Logger.debug("JSON object does not contain source");
                }
                if (jSONObject2 != null) {
                    Source source = new Source();
                    try {
                        source.setParameter(jSONObject2.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD));
                    } catch (JSONException unused7) {
                        Logger.debug("JSON object does not contain parameter");
                    }
                    try {
                        source.setPointer(jSONObject2.getString("pointer"));
                    } catch (JSONException unused8) {
                        Logger.debug("JSON object does not contain pointer");
                    }
                    error.setSource(source);
                }
                try {
                    JSONObject jSONObject3 = jSONObject.getJSONObject(JSONAPISpecConstants.LINKS);
                    ErrorLinks errorLinks = new ErrorLinks();
                    errorLinks.setAbout(jSONObject3.getString("about"));
                    error.setLinks(errorLinks);
                } catch (JSONException unused9) {
                    Logger.debug("JSON object does not contain links or about");
                }
                try {
                    error.setMeta(this.attributeMapper.createMapFromJSONObject(jSONObject.getJSONObject(JSONAPISpecConstants.META)));
                } catch (JSONException unused10) {
                    Logger.debug("JSON object does not contain JSONObject meta");
                }
                arrayList.add(error);
            } catch (JSONException unused11) {
                Logger.debug("No index " + i + " in error json array");
            }
        }
        return arrayList;
    }

    public ArrayList<HashMap<String, Object>> createData(List<Resource> list, boolean z) {
        try {
            String nameForResourceClass = nameForResourceClass(list.get(0).getClass());
            ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
            for (Resource next : list) {
                HashMap<String, Object> fieldsAsDictionary = this.serializer.getFieldsAsDictionary(next);
                HashMap hashMap = new HashMap();
                hashMap.put("type", nameForResourceClass);
                hashMap.put("id", next.getId());
                if (z) {
                    hashMap.put(JSONAPISpecConstants.ATTRIBUTES, fieldsAsDictionary);
                }
                HashMap<String, Object> createRelationships = createRelationships(next);
                if (createRelationships != null) {
                    hashMap.put(JSONAPISpecConstants.RELATIONSHIPS, createRelationships);
                }
                arrayList.add(hashMap);
            }
            return arrayList;
        } catch (Exception e) {
            Logger.debug(e.getMessage());
            return null;
        }
    }

    public HashMap<String, Object> createData(Resource resource, boolean z) {
        HashMap<String, Object> fieldsAsDictionary;
        try {
            String nameForResourceClass = nameForResourceClass(resource.getClass());
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("type", nameForResourceClass);
            hashMap.put("id", resource.getId());
            if (z && (fieldsAsDictionary = this.serializer.getFieldsAsDictionary(resource)) != null) {
                hashMap.put(JSONAPISpecConstants.ATTRIBUTES, fieldsAsDictionary);
            }
            HashMap<String, Object> createRelationships = createRelationships(resource);
            if (createRelationships != null) {
                hashMap.put(JSONAPISpecConstants.RELATIONSHIPS, createRelationships);
            }
            if (resource.getLinks() != null) {
                hashMap.put(JSONAPISpecConstants.LINKS, createLinks(resource));
            }
            return hashMap;
        } catch (Exception e) {
            Logger.debug(e.getMessage());
            return null;
        }
    }

    public HashMap<String, Object> createRelationships(Resource resource) {
        HashMap<String, Object> relationships = this.serializer.getRelationships(resource);
        HashMap<String, Object> hashMap = new HashMap<>();
        for (String next : relationships.keySet()) {
            Object obj = relationships.get(next);
            if (obj instanceof Resource) {
                if (resource.getNullableRelationships().contains(next)) {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("data", (Object) null);
                    hashMap.put(next, hashMap2);
                } else {
                    HashMap<String, Object> createData = createData((Resource) obj, false);
                    if (createData != null) {
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put("data", createData);
                        hashMap.put(next, hashMap3);
                    }
                }
            }
            if (obj instanceof ArrayList) {
                if (resource.getNullableRelationships().contains(next)) {
                    HashMap hashMap4 = new HashMap();
                    hashMap4.put("data", new ArrayList());
                    hashMap.put(next, hashMap4);
                } else {
                    ArrayList<HashMap<String, Object>> createData2 = createData((List<Resource>) (List) obj, false);
                    if (createData2 != null) {
                        HashMap hashMap5 = new HashMap();
                        hashMap5.put("data", createData2);
                        hashMap.put(next, hashMap5);
                    }
                }
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return hashMap;
    }

    public HashMap<String, Object> createLinks(Resource resource) {
        Links links = resource.getLinks();
        if (links == null) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        if (links.getSelfLink() != null) {
            hashMap.put(JSONAPISpecConstants.SELF, links.getSelfLink());
        }
        if (links.getRelated() != null) {
            hashMap.put(JSONAPISpecConstants.RELATED, links.getRelated());
        }
        if (links.getFirst() != null) {
            hashMap.put(JSONAPISpecConstants.FIRST, links.getFirst());
        }
        if (links.getLast() != null) {
            hashMap.put(JSONAPISpecConstants.LAST, links.getLast());
        }
        if (links.getPrev() != null) {
            hashMap.put(JSONAPISpecConstants.PREV, links.getPrev());
        }
        if (links.getNext() != null) {
            hashMap.put(JSONAPISpecConstants.NEXT, links.getNext());
        }
        if (links.getAbout() == null) {
            return hashMap;
        }
        hashMap.put("about", links.getAbout());
        return hashMap;
    }

    public ArrayList<HashMap<String, Object>> createIncluded(Resource resource) {
        ArrayList<HashMap<String, Object>> createData;
        HashMap<String, Object> createData2;
        HashMap<String, Object> relationships = this.serializer.getRelationships(resource);
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for (String str : relationships.keySet()) {
            Object obj = relationships.get(str);
            if ((obj instanceof Resource) && (createData2 = createData((Resource) obj, true)) != null) {
                arrayList.add(createData2);
            }
            if ((obj instanceof ArrayList) && (createData = createData((List<Resource>) (List) obj, true)) != null) {
                arrayList.addAll(createData);
            }
        }
        return arrayList;
    }

    private HashMap<String, String> getRelationshipNames(Class cls) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (Field field : cls.getDeclaredFields()) {
            String name = field.getName();
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation.annotationType() == SerializedName.class) {
                    name = ((SerializedName) annotation).value();
                }
                if (annotation.annotationType() == Relationship.class) {
                    hashMap.put(((Relationship) annotation).value(), name);
                }
            }
        }
        return hashMap;
    }

    private String nameForResourceClass(Class cls) throws Exception {
        for (String next : Deserializer.getRegisteredClasses().keySet()) {
            if (Deserializer.getRegisteredClasses().get(next) == cls) {
                return next;
            }
        }
        throw new Exception("Class " + cls.getSimpleName() + " not registered.");
    }

    public Deserializer getDeserializer() {
        return this.deserializer;
    }

    public AttributeMapper getAttributeMapper() {
        return this.attributeMapper;
    }

    public Serializer getSerializer() {
        return this.serializer;
    }
}

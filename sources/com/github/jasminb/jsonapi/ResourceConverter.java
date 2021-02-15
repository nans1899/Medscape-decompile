package com.github.jasminb.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.github.jasminb.jsonapi.exceptions.DocumentSerializationException;
import com.github.jasminb.jsonapi.exceptions.UnregisteredTypeException;
import com.github.jasminb.jsonapi.models.errors.Error;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResourceConverter {
    private String baseURL;
    private final ConverterConfiguration configuration;
    private final Set<DeserializationFeature> deserializationFeatures;
    private RelationshipResolver globalResolver;
    private final PropertyNamingStrategy namingStrategy;
    private final ObjectMapper objectMapper;
    private final ResourceCache resourceCache;
    private final Set<SerializationFeature> serializationFeatures;
    private final Map<Class<?>, RelationshipResolver> typedResolvers;

    public ResourceConverter(Class<?>... clsArr) {
        this((ObjectMapper) null, (String) null, clsArr);
    }

    public ResourceConverter(String str, Class<?>... clsArr) {
        this((ObjectMapper) null, str, clsArr);
    }

    public ResourceConverter(ObjectMapper objectMapper2, Class<?>... clsArr) {
        this(objectMapper2, (String) null, clsArr);
    }

    public ResourceConverter(ObjectMapper objectMapper2, String str, Class<?>... clsArr) {
        this.typedResolvers = new HashMap();
        this.deserializationFeatures = DeserializationFeature.getDefaultFeatures();
        this.serializationFeatures = SerializationFeature.getDefaultFeatures();
        this.configuration = new ConverterConfiguration(clsArr);
        this.baseURL = str == null ? "" : str;
        if (objectMapper2 != null) {
            this.objectMapper = objectMapper2;
        } else {
            ObjectMapper objectMapper3 = new ObjectMapper();
            this.objectMapper = objectMapper3;
            objectMapper3.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        if (this.objectMapper.getPropertyNamingStrategy() != null) {
            this.namingStrategy = this.objectMapper.getPropertyNamingStrategy();
        } else {
            this.namingStrategy = new PropertyNamingStrategy();
        }
        this.resourceCache = new ResourceCache();
    }

    public void setGlobalResolver(RelationshipResolver relationshipResolver) {
        this.globalResolver = relationshipResolver;
    }

    public void setTypeResolver(RelationshipResolver relationshipResolver, Class<?> cls) {
        if (relationshipResolver != null && ReflectionUtils.getTypeName(cls) != null) {
            this.typedResolvers.put(cls, relationshipResolver);
        }
    }

    @Deprecated
    public <T> T readObject(byte[] bArr, Class<T> cls) {
        return readDocument(bArr, cls).get();
    }

    @Deprecated
    public <T> List<T> readObjectCollection(byte[] bArr, Class<T> cls) {
        return readDocumentCollection(bArr, cls).get();
    }

    public <T> JSONAPIDocument<T> readDocument(byte[] bArr, Class<T> cls) {
        return readDocument((InputStream) new ByteArrayInputStream(bArr), cls);
    }

    public <T> JSONAPIDocument<T> readDocument(InputStream inputStream, Class<T> cls) {
        T t;
        try {
            this.resourceCache.init();
            JsonNode readTree = this.objectMapper.readTree(inputStream);
            ValidationUtils.ensureNotError(this.objectMapper, readTree);
            ValidationUtils.ensureValidResource(readTree);
            JsonNode jsonNode = readTree.get("data");
            boolean z = false;
            if (jsonNode == null || !jsonNode.isObject()) {
                t = null;
            } else {
                String createIdentifier = createIdentifier(jsonNode);
                boolean z2 = createIdentifier != null && this.resourceCache.contains(createIdentifier);
                if (z2) {
                    t = this.resourceCache.get(createIdentifier);
                } else {
                    t = readObject(jsonNode, cls, false);
                }
                z = z2;
            }
            this.resourceCache.cache(parseIncluded(readTree));
            if (t != null && !z) {
                handleRelationships(jsonNode, t);
            }
            JSONAPIDocument<T> jSONAPIDocument = new JSONAPIDocument<>(t, this.objectMapper);
            if (readTree.has(JSONAPISpecConstants.META)) {
                jSONAPIDocument.setMeta(mapMeta(readTree.get(JSONAPISpecConstants.META)));
            }
            if (readTree.has(JSONAPISpecConstants.LINKS)) {
                jSONAPIDocument.setLinks(new Links(mapLinks(readTree.get(JSONAPISpecConstants.LINKS))));
            }
            this.resourceCache.clear();
            return jSONAPIDocument;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        } catch (Throwable th) {
            this.resourceCache.clear();
            throw th;
        }
    }

    public <T> JSONAPIDocument<List<T>> readDocumentCollection(byte[] bArr, Class<T> cls) {
        return readDocumentCollection((InputStream) new ByteArrayInputStream(bArr), cls);
    }

    public <T> JSONAPIDocument<List<T>> readDocumentCollection(InputStream inputStream, Class<T> cls) {
        try {
            this.resourceCache.init();
            JsonNode readTree = this.objectMapper.readTree(inputStream);
            ValidationUtils.ensureNotError(this.objectMapper, readTree);
            ValidationUtils.ensureValidResource(readTree);
            JsonNode jsonNode = readTree.get("data");
            ArrayList arrayList = new ArrayList();
            if (jsonNode != null) {
                if (jsonNode.isArray()) {
                    Iterator<JsonNode> it = jsonNode.iterator();
                    while (it.hasNext()) {
                        arrayList.add(readObject(it.next(), cls, false));
                    }
                } else {
                    arrayList.add(readObject(jsonNode, cls, false));
                }
            }
            this.resourceCache.cache(parseIncluded(readTree));
            for (int i = 0; i < arrayList.size(); i++) {
                JsonNode jsonNode2 = (jsonNode == null || !jsonNode.isArray()) ? jsonNode : jsonNode.get(i);
                Object obj = arrayList.get(i);
                if (!(jsonNode2 == null || obj == null)) {
                    handleRelationships(jsonNode2, obj);
                }
            }
            JSONAPIDocument<List<T>> jSONAPIDocument = new JSONAPIDocument<>(arrayList, this.objectMapper);
            if (readTree.has(JSONAPISpecConstants.META)) {
                jSONAPIDocument.setMeta(mapMeta(readTree.get(JSONAPISpecConstants.META)));
            }
            if (readTree.has(JSONAPISpecConstants.LINKS)) {
                jSONAPIDocument.setLinks(new Links(mapLinks(readTree.get(JSONAPISpecConstants.LINKS))));
            }
            this.resourceCache.clear();
            return jSONAPIDocument;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        } catch (Throwable th) {
            this.resourceCache.clear();
            throw th;
        }
    }

    private <T> T readObject(JsonNode jsonNode, Class<T> cls, boolean z) throws IOException, IllegalAccessException, InstantiationException {
        Field linksField;
        Field metaField;
        String createIdentifier = createIdentifier(jsonNode);
        T t = this.resourceCache.get(createIdentifier);
        if (t == null) {
            Class<?> actualType = getActualType(jsonNode, cls);
            if (jsonNode.has(JSONAPISpecConstants.ATTRIBUTES)) {
                t = this.objectMapper.treeToValue(jsonNode.get(JSONAPISpecConstants.ATTRIBUTES), actualType);
            } else if (actualType.isInterface()) {
                t = null;
            } else {
                ObjectMapper objectMapper2 = this.objectMapper;
                t = objectMapper2.treeToValue(objectMapper2.createObjectNode(), actualType);
            }
            if (jsonNode.has(JSONAPISpecConstants.META) && (metaField = this.configuration.getMetaField(actualType)) != null) {
                metaField.set(t, this.objectMapper.treeToValue(jsonNode.get(JSONAPISpecConstants.META), this.configuration.getMetaType(actualType)));
            }
            if (jsonNode.has(JSONAPISpecConstants.LINKS) && (linksField = this.configuration.getLinksField(actualType)) != null) {
                linksField.set(t, new Links(mapLinks(jsonNode.get(JSONAPISpecConstants.LINKS))));
            }
            if (t != null) {
                this.resourceCache.cache(createIdentifier, t);
                setIdValue(t, jsonNode.get("id"));
                if (z) {
                    handleRelationships(jsonNode, t);
                }
            }
        }
        return t;
    }

    private Map<String, Object> parseIncluded(JsonNode jsonNode) throws IOException, IllegalAccessException, InstantiationException {
        HashMap hashMap = new HashMap();
        if (jsonNode.has(JSONAPISpecConstants.INCLUDED)) {
            Map<String, Object> includedResources = getIncludedResources(jsonNode);
            if (!includedResources.isEmpty()) {
                for (String next : includedResources.keySet()) {
                    hashMap.put(next, includedResources.get(next));
                }
                ArrayNode arrayNode = (ArrayNode) jsonNode.get(JSONAPISpecConstants.INCLUDED);
                for (int i = 0; i < arrayNode.size(); i++) {
                    JsonNode jsonNode2 = arrayNode.get(i);
                    Object obj = includedResources.get(createIdentifier(jsonNode2));
                    if (obj != null) {
                        handleRelationships(jsonNode2, obj);
                    }
                }
            }
        }
        return hashMap;
    }

    private Map<String, Object> getIncludedResources(JsonNode jsonNode) throws IOException, IllegalAccessException, InstantiationException {
        HashMap hashMap = new HashMap();
        if (jsonNode.has(JSONAPISpecConstants.INCLUDED)) {
            Iterator<JsonNode> it = jsonNode.get(JSONAPISpecConstants.INCLUDED).iterator();
            while (it.hasNext()) {
                JsonNode next = it.next();
                String asText = next.get("type").asText();
                Class<?> typeClass = this.configuration.getTypeClass(asText);
                if (typeClass != null) {
                    Object readObject = readObject(next, typeClass, false);
                    if (readObject != null) {
                        hashMap.put(createIdentifier(next), readObject);
                    }
                } else if (!this.deserializationFeatures.contains(DeserializationFeature.ALLOW_UNKNOWN_INCLUSIONS)) {
                    throw new IllegalArgumentException("Included section contains unknown resource type: " + asText);
                }
            }
        }
        return hashMap;
    }

    private void handleRelationships(JsonNode jsonNode, Object obj) throws IllegalAccessException, IOException, InstantiationException {
        Class<?> relationshipType;
        String link;
        Field relationshipLinksField;
        Field relationshipMetaField;
        JsonNode jsonNode2 = jsonNode.get(JSONAPISpecConstants.RELATIONSHIPS);
        if (jsonNode2 != null) {
            Iterator<String> fieldNames = jsonNode2.fieldNames();
            while (fieldNames.hasNext()) {
                String next = fieldNames.next();
                JsonNode jsonNode3 = jsonNode2.get(next);
                Field relationshipField = this.configuration.getRelationshipField(obj.getClass(), next);
                if (!(relationshipField == null || (relationshipType = this.configuration.getRelationshipType(obj.getClass(), next)) == null)) {
                    if (jsonNode3.has(JSONAPISpecConstants.META) && (relationshipMetaField = this.configuration.getRelationshipMetaField(obj.getClass(), next)) != null) {
                        relationshipMetaField.set(obj, this.objectMapper.treeToValue(jsonNode3.get(JSONAPISpecConstants.META), this.configuration.getRelationshipMetaType(obj.getClass(), next)));
                    }
                    if (jsonNode3.has(JSONAPISpecConstants.LINKS) && (relationshipLinksField = this.configuration.getRelationshipLinksField(obj.getClass(), next)) != null) {
                        relationshipLinksField.set(obj, new Links(mapLinks(jsonNode3.get(JSONAPISpecConstants.LINKS))));
                    }
                    boolean resolve = this.configuration.getFieldRelationship(relationshipField).resolve();
                    RelationshipResolver resolver = getResolver(relationshipType);
                    if (resolve && resolver != null && jsonNode3.has(JSONAPISpecConstants.LINKS)) {
                        JsonNode jsonNode4 = jsonNode3.get(JSONAPISpecConstants.LINKS).get(this.configuration.getFieldRelationship(relationshipField).relType().getRelName());
                        if (!(jsonNode4 == null || (link = getLink(jsonNode4)) == null)) {
                            if (isCollection(jsonNode3)) {
                                relationshipField.set(obj, readDocumentCollection((InputStream) new ByteArrayInputStream(resolver.resolve(link)), relationshipType).get());
                            } else {
                                relationshipField.set(obj, readDocument((InputStream) new ByteArrayInputStream(resolver.resolve(link)), relationshipType).get());
                            }
                        }
                    } else if (isCollection(jsonNode3)) {
                        Collection<?> createCollectionInstance = createCollectionInstance(relationshipField.getType());
                        Iterator<JsonNode> it = jsonNode3.get("data").iterator();
                        while (it.hasNext()) {
                            Object parseRelationship = parseRelationship(it.next(), relationshipType);
                            if (parseRelationship != null) {
                                createCollectionInstance.add(parseRelationship);
                            }
                        }
                        relationshipField.set(obj, createCollectionInstance);
                    } else {
                        Object parseRelationship2 = parseRelationship(jsonNode3.get("data"), relationshipType);
                        if (parseRelationship2 != null) {
                            relationshipField.set(obj, parseRelationship2);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String getLink(JsonNode jsonNode) {
        if (jsonNode.has("href")) {
            return jsonNode.get("href").asText();
        }
        return jsonNode.asText((String) null);
    }

    private Object parseRelationship(JsonNode jsonNode, Class<?> cls) throws IOException, IllegalAccessException, InstantiationException {
        if (!ValidationUtils.isRelationshipParsable(jsonNode)) {
            return null;
        }
        String createIdentifier = createIdentifier(jsonNode);
        if (this.resourceCache.contains(createIdentifier)) {
            return this.resourceCache.get(createIdentifier);
        }
        this.resourceCache.lock();
        try {
            return readObject(jsonNode, cls, true);
        } finally {
            this.resourceCache.unlock();
        }
    }

    private String createIdentifier(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("id");
        String trim = jsonNode2 != null ? jsonNode2.asText().trim() : "";
        if (!trim.isEmpty() || !this.deserializationFeatures.contains(DeserializationFeature.REQUIRE_RESOURCE_ID)) {
            return jsonNode.get("type").asText().concat(trim);
        }
        throw new IllegalArgumentException("Resource must have an non null and non-empty 'id' attribute!");
    }

    private void setIdValue(Object obj, JsonNode jsonNode) throws IllegalAccessException {
        Field idField = this.configuration.getIdField(obj.getClass());
        ResourceIdHandler idHandler = this.configuration.getIdHandler(obj.getClass());
        if (jsonNode != null) {
            idField.set(obj, idHandler.fromString(jsonNode.asText()));
        }
    }

    private String getIdValue(Object obj) throws IllegalAccessException {
        return this.configuration.getIdHandler(obj.getClass()).asString(this.configuration.getIdField(obj.getClass()).get(obj));
    }

    private boolean isCollection(JsonNode jsonNode) {
        JsonNode jsonNode2 = jsonNode.get("data");
        return jsonNode2 != null && jsonNode2.isArray();
    }

    @Deprecated
    public byte[] writeObject(Object obj) throws JsonProcessingException, IllegalAccessException {
        try {
            return writeDocument(new JSONAPIDocument(obj));
        } catch (DocumentSerializationException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] writeDocument(JSONAPIDocument<?> jSONAPIDocument) throws DocumentSerializationException {
        return writeDocument(jSONAPIDocument, (SerializationSettings) null);
    }

    public byte[] writeDocument(JSONAPIDocument<?> jSONAPIDocument, SerializationSettings serializationSettings) throws DocumentSerializationException {
        try {
            this.resourceCache.init();
            HashMap hashMap = new HashMap();
            ObjectNode createObjectNode = this.objectMapper.createObjectNode();
            if (jSONAPIDocument.get() != null) {
                createObjectNode.set("data", getDataNode(jSONAPIDocument.get(), hashMap, serializationSettings));
                createObjectNode = addIncludedSection(createObjectNode, hashMap);
            }
            if (jSONAPIDocument.getErrors() != null) {
                ArrayNode createArrayNode = this.objectMapper.createArrayNode();
                for (Error valueToTree : jSONAPIDocument.getErrors()) {
                    createArrayNode.add(this.objectMapper.valueToTree(valueToTree));
                }
                createObjectNode.set(JSONAPISpecConstants.ERRORS, createArrayNode);
            }
            serializeMeta(jSONAPIDocument, createObjectNode, serializationSettings);
            serializeLinks(jSONAPIDocument, createObjectNode, serializationSettings);
            byte[] writeValueAsBytes = this.objectMapper.writeValueAsBytes(createObjectNode);
            this.resourceCache.clear();
            return writeValueAsBytes;
        } catch (Exception e) {
            throw new DocumentSerializationException(e);
        } catch (Throwable th) {
            this.resourceCache.clear();
            throw th;
        }
    }

    private void serializeMeta(JSONAPIDocument<?> jSONAPIDocument, ObjectNode objectNode, SerializationSettings serializationSettings) {
        if (jSONAPIDocument.getMeta() != null && !jSONAPIDocument.getMeta().isEmpty() && shouldSerializeMeta(serializationSettings)) {
            objectNode.set(JSONAPISpecConstants.META, this.objectMapper.valueToTree(jSONAPIDocument.getMeta()));
        }
    }

    private void serializeLinks(JSONAPIDocument<?> jSONAPIDocument, ObjectNode objectNode, SerializationSettings serializationSettings) {
        if (jSONAPIDocument.getLinks() != null && !jSONAPIDocument.getLinks().getLinks().isEmpty() && shouldSerializeLinks(serializationSettings)) {
            objectNode.set(JSONAPISpecConstants.LINKS, this.objectMapper.valueToTree(jSONAPIDocument.getLinks()).get(JSONAPISpecConstants.LINKS));
        }
    }

    public byte[] writeDocumentCollection(JSONAPIDocument<? extends Iterable<?>> jSONAPIDocument) throws DocumentSerializationException {
        return writeDocumentCollection(jSONAPIDocument, (SerializationSettings) null);
    }

    public byte[] writeDocumentCollection(JSONAPIDocument<? extends Iterable<?>> jSONAPIDocument, SerializationSettings serializationSettings) throws DocumentSerializationException {
        try {
            this.resourceCache.init();
            ArrayNode createArrayNode = this.objectMapper.createArrayNode();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object dataNode : (Iterable) jSONAPIDocument.get()) {
                createArrayNode.add((JsonNode) getDataNode(dataNode, linkedHashMap, serializationSettings));
            }
            ObjectNode createObjectNode = this.objectMapper.createObjectNode();
            createObjectNode.set("data", createArrayNode);
            serializeMeta(jSONAPIDocument, createObjectNode, serializationSettings);
            serializeLinks(jSONAPIDocument, createObjectNode, serializationSettings);
            byte[] writeValueAsBytes = this.objectMapper.writeValueAsBytes(addIncludedSection(createObjectNode, linkedHashMap));
            this.resourceCache.clear();
            return writeValueAsBytes;
        } catch (Exception e) {
            throw new DocumentSerializationException(e);
        } catch (Throwable th) {
            this.resourceCache.clear();
            throw th;
        }
    }

    private ObjectNode getDataNode(Object obj, Map<String, ObjectNode> map, SerializationSettings serializationSettings) throws IllegalAccessException {
        String str;
        String str2;
        JsonNode jsonNode;
        JsonNode jsonNode2;
        ObjectNode objectNode;
        ObjectNode objectNode2;
        String str3;
        ObjectNode objectNode3;
        ObjectNode objectNode4;
        JsonNode jsonNode3;
        JsonNode jsonNode4;
        Object obj2 = obj;
        Map<String, ObjectNode> map2 = map;
        SerializationSettings serializationSettings2 = serializationSettings;
        ObjectNode createObjectNode = this.objectMapper.createObjectNode();
        ObjectNode objectNode5 = (ObjectNode) this.objectMapper.valueToTree(obj2);
        String idValue = getIdValue(obj);
        removeField(objectNode5, this.configuration.getIdField(obj.getClass()));
        Field metaField = this.configuration.getMetaField(obj.getClass());
        JsonNode removeField = metaField != null ? removeField(objectNode5, metaField) : null;
        JsonNode resourceLinks = getResourceLinks(obj2, objectNode5, idValue, serializationSettings2);
        if (resourceLinks == null || !resourceLinks.has(JSONAPISpecConstants.SELF)) {
            str = null;
        } else {
            JsonNode jsonNode5 = resourceLinks.get(JSONAPISpecConstants.SELF);
            str = jsonNode5 instanceof TextNode ? jsonNode5.textValue() : jsonNode5.get("href").asText();
        }
        createObjectNode.put("type", this.configuration.getTypeName(obj.getClass()));
        if (idValue != null) {
            createObjectNode.put("id", idValue);
            this.resourceCache.cache(idValue.concat(this.configuration.getTypeName(obj.getClass())), (Object) null);
        }
        createObjectNode.set(JSONAPISpecConstants.ATTRIBUTES, objectNode5);
        List<Field> relationshipFields = this.configuration.getRelationshipFields(obj.getClass());
        String str4 = JSONAPISpecConstants.META;
        if (relationshipFields != null) {
            ObjectNode createObjectNode2 = this.objectMapper.createObjectNode();
            Iterator<Field> it = relationshipFields.iterator();
            while (it.hasNext()) {
                Iterator<Field> it2 = it;
                Field next = it.next();
                JsonNode jsonNode6 = removeField;
                Object obj3 = next.get(obj2);
                removeField(objectNode5, next);
                if (obj3 != null) {
                    jsonNode3 = resourceLinks;
                    Relationship fieldRelationship = this.configuration.getFieldRelationship(next);
                    if (!fieldRelationship.serialise()) {
                        jsonNode4 = jsonNode6;
                        it = it2;
                        resourceLinks = jsonNode3;
                    } else {
                        String value = fieldRelationship.value();
                        objectNode4 = createObjectNode;
                        ObjectNode createObjectNode3 = this.objectMapper.createObjectNode();
                        createObjectNode2.set(value, createObjectNode3);
                        objectNode3 = createObjectNode2;
                        JsonNode relationshipMeta = getRelationshipMeta(obj2, value, serializationSettings2);
                        if (relationshipMeta != null) {
                            createObjectNode3.set(str4, relationshipMeta);
                            str3 = str4;
                            removeField(objectNode5, this.configuration.getRelationshipMetaField(obj.getClass(), value));
                        } else {
                            str3 = str4;
                        }
                        JsonNode relationshipLinks = getRelationshipLinks(obj2, fieldRelationship, str, serializationSettings2);
                        if (relationshipLinks != null) {
                            createObjectNode3.set(JSONAPISpecConstants.LINKS, relationshipLinks);
                            removeField(objectNode5, this.configuration.getRelationshipLinksField(obj.getClass(), value));
                        }
                        if (obj3 instanceof Collection) {
                            ArrayNode createArrayNode = this.objectMapper.createArrayNode();
                            Iterator it3 = ((Collection) obj3).iterator();
                            while (it3.hasNext()) {
                                Object next2 = it3.next();
                                ObjectNode objectNode6 = objectNode5;
                                String typeName = this.configuration.getTypeName(next2.getClass());
                                String idValue2 = getIdValue(next2);
                                Iterator it4 = it3;
                                ObjectNode createObjectNode4 = this.objectMapper.createObjectNode();
                                createObjectNode4.put("type", typeName);
                                createObjectNode4.put("id", idValue2);
                                createArrayNode.add((JsonNode) createObjectNode4);
                                if (shouldSerializeRelationship(value, serializationSettings2) && idValue2 != null) {
                                    String concat = idValue2.concat(typeName);
                                    if (!map2.containsKey(concat) && !this.resourceCache.contains(concat)) {
                                        map2.put(concat, getDataNode(next2, map2, serializationSettings2));
                                    }
                                }
                                Object obj4 = obj;
                                objectNode5 = objectNode6;
                                it3 = it4;
                            }
                            objectNode2 = objectNode5;
                            createObjectNode3.set("data", createArrayNode);
                        } else {
                            objectNode2 = objectNode5;
                            String typeName2 = this.configuration.getTypeName(obj3.getClass());
                            String idValue3 = getIdValue(obj3);
                            ObjectNode createObjectNode5 = this.objectMapper.createObjectNode();
                            createObjectNode5.put("type", typeName2);
                            if (idValue3 == null) {
                                ObjectNode objectNode7 = (ObjectNode) this.objectMapper.valueToTree(obj3);
                                removeField(objectNode7, this.configuration.getIdField(obj.getClass()));
                                createObjectNode5.set(JSONAPISpecConstants.ATTRIBUTES, objectNode7);
                                createObjectNode3.set("data", createObjectNode5);
                            } else {
                                createObjectNode5.put("id", idValue3);
                                createObjectNode3.set("data", createObjectNode5);
                                if (shouldSerializeRelationship(value, serializationSettings2) && idValue3 != null) {
                                    String concat2 = idValue3.concat(typeName2);
                                    if (!map2.containsKey(concat2)) {
                                        map2.put(concat2, getDataNode(obj3, map2, serializationSettings2));
                                    }
                                }
                            }
                        }
                    }
                } else {
                    objectNode4 = createObjectNode;
                    objectNode2 = objectNode5;
                    jsonNode3 = resourceLinks;
                    str3 = str4;
                    objectNode3 = createObjectNode2;
                }
                obj2 = obj;
                jsonNode4 = jsonNode6;
                it = it2;
                resourceLinks = jsonNode3;
                createObjectNode = objectNode4;
                createObjectNode2 = objectNode3;
                str4 = str3;
                objectNode5 = objectNode2;
            }
            ObjectNode objectNode8 = createObjectNode;
            jsonNode2 = removeField;
            jsonNode = resourceLinks;
            str2 = str4;
            ObjectNode objectNode9 = createObjectNode2;
            if (objectNode9.size() > 0) {
                objectNode = objectNode8;
                objectNode.set(JSONAPISpecConstants.RELATIONSHIPS, objectNode9);
            } else {
                objectNode = objectNode8;
            }
        } else {
            objectNode = createObjectNode;
            jsonNode2 = removeField;
            jsonNode = resourceLinks;
            str2 = str4;
        }
        if (jsonNode != null) {
            objectNode.set(JSONAPISpecConstants.LINKS, jsonNode);
        }
        if (jsonNode2 != null && shouldSerializeMeta(serializationSettings2)) {
            objectNode.set(str2, jsonNode2);
        }
        return objectNode;
    }

    @Deprecated
    public <T> byte[] writeObjectCollection(Iterable<T> iterable) throws JsonProcessingException, IllegalAccessException {
        try {
            return writeDocumentCollection(new JSONAPIDocument(iterable));
        } catch (DocumentSerializationException e) {
            if (e.getCause() instanceof JsonProcessingException) {
                throw ((JsonProcessingException) e.getCause());
            } else if (e.getCause() instanceof IllegalAccessException) {
                throw ((IllegalAccessException) e.getCause());
            } else {
                throw new RuntimeException(e.getCause());
            }
        }
    }

    public boolean isRegisteredType(Class<?> cls) {
        return this.configuration.isRegisteredType(cls);
    }

    private RelationshipResolver getResolver(Class<?> cls) {
        RelationshipResolver relationshipResolver = this.typedResolvers.get(cls);
        return relationshipResolver != null ? relationshipResolver : this.globalResolver;
    }

    private Map<String, Link> mapLinks(JsonNode jsonNode) {
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {
            Map.Entry next = fields.next();
            Link link = new Link();
            link.setHref(getLink((JsonNode) next.getValue()));
            if (((JsonNode) next.getValue()).has(JSONAPISpecConstants.META)) {
                link.setMeta(mapMeta(((JsonNode) next.getValue()).get(JSONAPISpecConstants.META)));
            }
            hashMap.put(next.getKey(), link);
        }
        return hashMap;
    }

    private Map<String, Object> mapMeta(JsonNode jsonNode) {
        try {
            return (Map) this.objectMapper.readValue(this.objectMapper.treeAsTokens(jsonNode), (JavaType) TypeFactory.defaultInstance().constructMapType((Class<? extends Map>) HashMap.class, (Class<?>) String.class, (Class<?>) Object.class));
        } catch (IOException unused) {
            return null;
        }
    }

    private ObjectNode addIncludedSection(ObjectNode objectNode, Map<String, ObjectNode> map) {
        if (!map.isEmpty()) {
            ArrayNode createArrayNode = this.objectMapper.createArrayNode();
            createArrayNode.addAll((Collection<? extends JsonNode>) map.values());
            objectNode.set(JSONAPISpecConstants.INCLUDED, createArrayNode);
        }
        return objectNode;
    }

    private Class<?> getActualType(JsonNode jsonNode, Class<?> cls) {
        String asText = jsonNode.get("type").asText();
        String typeName = this.configuration.getTypeName(cls);
        if (typeName != null && typeName.equals(asText)) {
            return cls;
        }
        Class<?> typeClass = this.configuration.getTypeClass(asText);
        if (typeClass != null && cls.isAssignableFrom(typeClass)) {
            return typeClass;
        }
        throw new UnregisteredTypeException(asText);
    }

    private Collection<?> createCollectionInstance(Class<?> cls) throws InstantiationException, IllegalAccessException {
        if (!cls.isInterface() && !Modifier.isAbstract(cls.getModifiers())) {
            return (Collection) cls.newInstance();
        }
        if (List.class.equals(cls) || Collection.class.equals(cls)) {
            return new ArrayList();
        }
        if (Set.class.equals(cls)) {
            return new HashSet();
        }
        throw new RuntimeException("Unable to create appropriate instance for type: " + cls.getSimpleName());
    }

    private JsonNode getRelationshipMeta(Object obj, String str, SerializationSettings serializationSettings) throws IllegalAccessException {
        Field relationshipMetaField;
        if (!shouldSerializeMeta(serializationSettings) || (relationshipMetaField = this.configuration.getRelationshipMetaField(obj.getClass(), str)) == null || relationshipMetaField.get(obj) == null) {
            return null;
        }
        return this.objectMapper.valueToTree(relationshipMetaField.get(obj));
    }

    private JsonNode getResourceLinks(Object obj, ObjectNode objectNode, String str, SerializationSettings serializationSettings) throws IllegalAccessException {
        Links links;
        Type type = this.configuration.getType(obj.getClass());
        Field linksField = this.configuration.getLinksField(obj.getClass());
        if (linksField != null) {
            links = (Links) linksField.get(obj);
            if (links != null) {
                removeField(objectNode, linksField);
            }
        } else {
            links = null;
        }
        if (shouldSerializeLinks(serializationSettings)) {
            HashMap hashMap = new HashMap();
            if (links != null) {
                hashMap.putAll(links.getLinks());
            }
            if (!type.path().trim().isEmpty() && !hashMap.containsKey(JSONAPISpecConstants.SELF) && str != null) {
                hashMap.put(JSONAPISpecConstants.SELF, new Link(createURL(this.baseURL, type.path().replace("{id}", str))));
            }
            if (!hashMap.isEmpty()) {
                return this.objectMapper.valueToTree(new Links(hashMap)).get(JSONAPISpecConstants.LINKS);
            }
        }
        return null;
    }

    private JsonNode getRelationshipLinks(Object obj, Relationship relationship, String str, SerializationSettings serializationSettings) throws IllegalAccessException {
        if (shouldSerializeLinks(serializationSettings)) {
            Field relationshipLinksField = this.configuration.getRelationshipLinksField(obj.getClass(), relationship.value());
            Links links = relationshipLinksField != null ? (Links) relationshipLinksField.get(obj) : null;
            HashMap hashMap = new HashMap();
            if (links != null) {
                hashMap.putAll(links.getLinks());
            }
            if (!relationship.path().trim().isEmpty() && !hashMap.containsKey(JSONAPISpecConstants.SELF)) {
                hashMap.put(JSONAPISpecConstants.SELF, new Link(createURL(str, relationship.path())));
            }
            if (!relationship.relatedPath().trim().isEmpty() && !hashMap.containsKey(JSONAPISpecConstants.RELATED)) {
                hashMap.put(JSONAPISpecConstants.RELATED, new Link(createURL(str, relationship.relatedPath())));
            }
            if (!hashMap.isEmpty()) {
                return this.objectMapper.valueToTree(new Links(hashMap)).get(JSONAPISpecConstants.LINKS);
            }
        }
        return null;
    }

    private String createURL(String str, String str2) {
        if (!str.endsWith("/")) {
            str = str.concat("/");
        }
        if (str2.startsWith("/")) {
            return str.concat(str2.substring(1));
        }
        return str.concat(str2);
    }

    private boolean shouldSerializeRelationship(String str, SerializationSettings serializationSettings) {
        if (serializationSettings != null) {
            if (serializationSettings.isRelationshipIncluded(str) && !serializationSettings.isRelationshipExcluded(str)) {
                return true;
            }
            if (serializationSettings.isRelationshipExcluded(str)) {
                return false;
            }
        }
        return this.serializationFeatures.contains(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
    }

    private boolean shouldSerializeLinks(SerializationSettings serializationSettings) {
        if (serializationSettings == null || serializationSettings.serializeLinks() == null) {
            return this.serializationFeatures.contains(SerializationFeature.INCLUDE_LINKS);
        }
        return serializationSettings.serializeLinks().booleanValue();
    }

    private boolean shouldSerializeMeta(SerializationSettings serializationSettings) {
        if (serializationSettings == null || serializationSettings.serializeMeta() == null) {
            return this.serializationFeatures.contains(SerializationFeature.INCLUDE_META);
        }
        return serializationSettings.serializeMeta().booleanValue();
    }

    private JsonNode removeField(ObjectNode objectNode, Field field) {
        if (field != null) {
            return objectNode.remove(this.namingStrategy.nameForField((MapperConfig<?>) null, (AnnotatedField) null, field.getName()));
        }
        return null;
    }

    public boolean registerType(Class<?> cls) {
        if (this.configuration.isRegisteredType(cls) || !ConverterConfiguration.isEligibleType(cls)) {
            return false;
        }
        return this.configuration.registerType(cls);
    }

    public void enableDeserializationOption(DeserializationFeature deserializationFeature) {
        this.deserializationFeatures.add(deserializationFeature);
    }

    public void disableDeserializationOption(DeserializationFeature deserializationFeature) {
        this.deserializationFeatures.remove(deserializationFeature);
    }

    public void enableSerializationOption(SerializationFeature serializationFeature) {
        this.serializationFeatures.add(serializationFeature);
    }

    public void disableSerializationOption(SerializationFeature serializationFeature) {
        this.serializationFeatures.remove(serializationFeature);
    }
}

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.medscape.android.slideshow.SlideshowPageFragment;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializer<Map<?, ?>> implements ContextualSerializer {
    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    private static final long serialVersionUID = 1;
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final Object _filterId;
    protected final HashSet<String> _ignoredEntries;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected final boolean _sortKeys;
    protected final Object _suppressableValue;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2) {
        super(Map.class, false);
        this._ignoredEntries = hashSet;
        this._keyType = javaType;
        this._valueType = javaType2;
        this._valueTypeIsStatic = z;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
        this._property = null;
        this._filterId = null;
        this._sortKeys = false;
        this._suppressableValue = null;
    }

    /* access modifiers changed from: protected */
    public void _ensureOverride() {
        if (getClass() != MapSerializer.class) {
            throw new IllegalStateException("Missing override in class " + getClass().getName());
        }
    }

    protected MapSerializer(MapSerializer mapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet) {
        super(Map.class, false);
        this._ignoredEntries = hashSet;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer._valueTypeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = beanProperty;
        this._filterId = mapSerializer._filterId;
        this._sortKeys = mapSerializer._sortKeys;
        this._suppressableValue = mapSerializer._suppressableValue;
    }

    @Deprecated
    protected MapSerializer(MapSerializer mapSerializer, TypeSerializer typeSerializer) {
        this(mapSerializer, typeSerializer, mapSerializer._suppressableValue);
    }

    protected MapSerializer(MapSerializer mapSerializer, TypeSerializer typeSerializer, Object obj) {
        super(Map.class, false);
        this._ignoredEntries = mapSerializer._ignoredEntries;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = mapSerializer._keySerializer;
        this._valueSerializer = mapSerializer._valueSerializer;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = mapSerializer._property;
        this._filterId = mapSerializer._filterId;
        this._sortKeys = mapSerializer._sortKeys;
        if (obj == JsonInclude.Include.NON_ABSENT) {
            obj = this._valueType.isReferenceType() ? JsonInclude.Include.NON_EMPTY : JsonInclude.Include.NON_NULL;
        }
        this._suppressableValue = obj;
    }

    protected MapSerializer(MapSerializer mapSerializer, Object obj, boolean z) {
        super(Map.class, false);
        this._ignoredEntries = mapSerializer._ignoredEntries;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer._valueTypeSerializer;
        this._keySerializer = mapSerializer._keySerializer;
        this._valueSerializer = mapSerializer._valueSerializer;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = mapSerializer._property;
        this._filterId = obj;
        this._sortKeys = z;
        this._suppressableValue = mapSerializer._suppressableValue;
    }

    public MapSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        if (this._valueTypeSerializer == typeSerializer) {
            return this;
        }
        _ensureOverride();
        return new MapSerializer(this, typeSerializer, (Object) null);
    }

    public MapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet, boolean z) {
        _ensureOverride();
        MapSerializer mapSerializer = new MapSerializer(this, beanProperty, jsonSerializer, jsonSerializer2, hashSet);
        return z != mapSerializer._sortKeys ? new MapSerializer(mapSerializer, this._filterId, z) : mapSerializer;
    }

    public MapSerializer withFilterId(Object obj) {
        if (this._filterId == obj) {
            return this;
        }
        _ensureOverride();
        return new MapSerializer(this, obj, this._sortKeys);
    }

    public MapSerializer withContentInclusion(Object obj) {
        if (obj == this._suppressableValue) {
            return this;
        }
        _ensureOverride();
        return new MapSerializer(this, this._valueTypeSerializer, obj);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.databind.ser.std.MapSerializer construct(java.lang.String[] r8, com.fasterxml.jackson.databind.JavaType r9, boolean r10, com.fasterxml.jackson.databind.jsontype.TypeSerializer r11, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r12, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r13, java.lang.Object r14) {
        /*
            if (r8 == 0) goto L_0x000b
            int r0 = r8.length
            if (r0 != 0) goto L_0x0006
            goto L_0x000b
        L_0x0006:
            java.util.HashSet r8 = com.fasterxml.jackson.databind.util.ArrayBuilders.arrayToSet(r8)
            goto L_0x000c
        L_0x000b:
            r8 = 0
        L_0x000c:
            r1 = r8
            if (r9 != 0) goto L_0x0014
            com.fasterxml.jackson.databind.JavaType r8 = UNSPECIFIED_TYPE
            r2 = r8
            r3 = r2
            goto L_0x001e
        L_0x0014:
            com.fasterxml.jackson.databind.JavaType r8 = r9.getKeyType()
            com.fasterxml.jackson.databind.JavaType r9 = r9.getContentType()
            r2 = r8
            r3 = r9
        L_0x001e:
            r8 = 0
            if (r10 != 0) goto L_0x002e
            if (r3 == 0) goto L_0x002c
            boolean r9 = r3.isFinal()
            if (r9 == 0) goto L_0x002c
            r8 = 1
            r10 = 1
            goto L_0x0038
        L_0x002c:
            r10 = 0
            goto L_0x0038
        L_0x002e:
            java.lang.Class r9 = r3.getRawClass()
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            if (r9 != r0) goto L_0x0038
            r4 = 0
            goto L_0x0039
        L_0x0038:
            r4 = r10
        L_0x0039:
            com.fasterxml.jackson.databind.ser.std.MapSerializer r8 = new com.fasterxml.jackson.databind.ser.std.MapSerializer
            r0 = r8
            r5 = r11
            r6 = r12
            r7 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            if (r14 == 0) goto L_0x0048
            com.fasterxml.jackson.databind.ser.std.MapSerializer r8 = r8.withFilterId((java.lang.Object) r14)
        L_0x0048:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.construct(java.lang.String[], com.fasterxml.jackson.databind.JavaType, boolean, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.JsonSerializer, java.lang.Object):com.fasterxml.jackson.databind.ser.std.MapSerializer");
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedMember annotatedMember;
        JsonSerializer jsonSerializer;
        JsonSerializer jsonSerializer2;
        boolean z;
        HashSet<String> hashSet;
        AnnotatedMember member;
        Object findFilterId;
        JsonInclude.Include contentInclusion;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        JsonSerializer jsonSerializer3 = null;
        if (beanProperty == null) {
            annotatedMember = null;
        } else {
            annotatedMember = beanProperty.getMember();
        }
        Object obj = this._suppressableValue;
        if (annotatedMember == null || annotationIntrospector == null) {
            jsonSerializer = null;
        } else {
            Object findKeySerializer = annotationIntrospector.findKeySerializer(annotatedMember);
            jsonSerializer = findKeySerializer != null ? serializerProvider.serializerInstance(annotatedMember, findKeySerializer) : null;
            Object findContentSerializer = annotationIntrospector.findContentSerializer(annotatedMember);
            if (findContentSerializer != null) {
                jsonSerializer3 = serializerProvider.serializerInstance(annotatedMember, findContentSerializer);
            }
        }
        if (!(beanProperty == null || (contentInclusion = beanProperty.findPropertyInclusion(serializerProvider.getConfig(), Map.class).getContentInclusion()) == null || contentInclusion == JsonInclude.Include.USE_DEFAULTS)) {
            obj = contentInclusion;
        }
        if (jsonSerializer3 == null) {
            jsonSerializer3 = this._valueSerializer;
        }
        JsonSerializer findConvertingContentSerializer = findConvertingContentSerializer(serializerProvider, beanProperty, jsonSerializer3);
        if (findConvertingContentSerializer != null) {
            findConvertingContentSerializer = serializerProvider.handleSecondaryContextualization(findConvertingContentSerializer, beanProperty);
        } else if (this._valueTypeIsStatic && !this._valueType.isJavaLangObject()) {
            findConvertingContentSerializer = serializerProvider.findValueSerializer(this._valueType, beanProperty);
        }
        JsonSerializer jsonSerializer4 = findConvertingContentSerializer;
        if (jsonSerializer == null) {
            jsonSerializer = this._keySerializer;
        }
        if (jsonSerializer == null) {
            jsonSerializer2 = serializerProvider.findKeySerializer(this._keyType, beanProperty);
        } else {
            jsonSerializer2 = serializerProvider.handleSecondaryContextualization(jsonSerializer, beanProperty);
        }
        JsonSerializer jsonSerializer5 = jsonSerializer2;
        HashSet<String> hashSet2 = this._ignoredEntries;
        boolean z2 = false;
        if (annotationIntrospector == null || annotatedMember == null) {
            hashSet = hashSet2;
            z = false;
        } else {
            String[] findPropertiesToIgnore = annotationIntrospector.findPropertiesToIgnore(annotatedMember, true);
            if (findPropertiesToIgnore != null) {
                hashSet2 = hashSet2 == null ? new HashSet<>() : new HashSet<>(hashSet2);
                for (String add : findPropertiesToIgnore) {
                    hashSet2.add(add);
                }
            }
            Boolean findSerializationSortAlphabetically = annotationIntrospector.findSerializationSortAlphabetically(annotatedMember);
            if (findSerializationSortAlphabetically != null && findSerializationSortAlphabetically.booleanValue()) {
                z2 = true;
            }
            hashSet = hashSet2;
            z = z2;
        }
        MapSerializer withResolved = withResolved(beanProperty, jsonSerializer5, jsonSerializer4, hashSet, z);
        if (obj != this._suppressableValue) {
            withResolved = withResolved.withContentInclusion(obj);
        }
        return (beanProperty == null || (member = beanProperty.getMember()) == null || (findFilterId = annotationIntrospector.findFilterId(member)) == null) ? withResolved : withResolved.withFilterId(findFilterId);
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    public boolean isEmpty(SerializerProvider serializerProvider, Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            return true;
        }
        Object obj = this._suppressableValue;
        if (obj == null || obj == JsonInclude.Include.ALWAYS) {
            return false;
        }
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer != null) {
            for (Object next : map.values()) {
                if (next != null && !jsonSerializer.isEmpty(serializerProvider, next)) {
                    return false;
                }
            }
            return true;
        }
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Object next2 : map.values()) {
            if (next2 != null) {
                Class<?> cls = next2.getClass();
                JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                if (serializerFor == null) {
                    try {
                        serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                        propertySerializerMap = this._dynamicValueSerializers;
                    } catch (JsonMappingException unused) {
                        return false;
                    }
                }
                if (!serializerFor.isEmpty(serializerProvider, next2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasSingleElement(Map<?, ?> map) {
        return map.size() == 1;
    }

    public JsonSerializer<?> getKeySerializer() {
        return this._keySerializer;
    }

    public void serialize(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.setCurrentValue(map);
        if (!map.isEmpty()) {
            Object obj = this._suppressableValue;
            if (obj == JsonInclude.Include.ALWAYS) {
                obj = null;
            } else if (obj == null && !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
                obj = JsonInclude.Include.NON_NULL;
            }
            Object obj2 = obj;
            if (this._sortKeys || serializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = _orderEntries(map);
            }
            Map<?, ?> map2 = map;
            Object obj3 = this._filterId;
            if (obj3 != null) {
                serializeFilteredFields(map2, jsonGenerator, serializerProvider, findPropertyFilter(serializerProvider, obj3, map2), obj2);
            } else if (obj2 != null) {
                serializeOptionalFields(map2, jsonGenerator, serializerProvider, obj2);
            } else {
                JsonSerializer<Object> jsonSerializer = this._valueSerializer;
                if (jsonSerializer != null) {
                    serializeFieldsUsing(map2, jsonGenerator, serializerProvider, jsonSerializer);
                } else {
                    serializeFields(map2, jsonGenerator, serializerProvider);
                }
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        typeSerializer.writeTypePrefixForObject(map, jsonGenerator);
        jsonGenerator.setCurrentValue(map);
        if (!map.isEmpty()) {
            Object obj = this._suppressableValue;
            if (obj == JsonInclude.Include.ALWAYS) {
                obj = null;
            } else if (obj == null && !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
                obj = JsonInclude.Include.NON_NULL;
            }
            Object obj2 = obj;
            if (this._sortKeys || serializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = _orderEntries(map);
            }
            Object obj3 = this._filterId;
            if (obj3 != null) {
                serializeFilteredFields(map, jsonGenerator, serializerProvider, findPropertyFilter(serializerProvider, obj3, map), obj2);
            } else if (obj2 != null) {
                serializeOptionalFields(map, jsonGenerator, serializerProvider, obj2);
            } else {
                JsonSerializer<Object> jsonSerializer = this._valueSerializer;
                if (jsonSerializer != null) {
                    serializeFieldsUsing(map, jsonGenerator, serializerProvider, jsonSerializer);
                } else {
                    serializeFields(map, jsonGenerator, serializerProvider);
                }
            }
        }
        typeSerializer.writeTypeSuffixForObject(map, jsonGenerator);
    }

    public void serializeFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map, jsonGenerator, serializerProvider, (Object) null);
            return;
        }
        JsonSerializer<Object> jsonSerializer2 = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Map.Entry next : map.entrySet()) {
            Object value = next.getValue();
            Object key = next.getKey();
            if (key == null) {
                serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
            } else if (hashSet == null || !hashSet.contains(key)) {
                jsonSerializer2.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                Class<?> cls = value.getClass();
                JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                if (serializerFor == null) {
                    if (this._valueType.hasGenericTypes()) {
                        jsonSerializer = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                    } else {
                        jsonSerializer = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                    serializerFor = jsonSerializer;
                    propertySerializerMap = this._dynamicValueSerializers;
                }
                try {
                    serializerFor.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, "" + key);
                }
            }
        }
    }

    public void serializeOptionalFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, Object obj) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> jsonSerializer2;
        JsonSerializer<Object> jsonSerializer3;
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map, jsonGenerator, serializerProvider, obj);
            return;
        }
        HashSet<String> hashSet = this._ignoredEntries;
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Map.Entry next : map.entrySet()) {
            Object key = next.getKey();
            if (key == null) {
                jsonSerializer = serializerProvider.findNullKeySerializer(this._keyType, this._property);
            } else if (hashSet == null || !hashSet.contains(key)) {
                jsonSerializer = this._keySerializer;
            }
            Object value = next.getValue();
            if (value != null) {
                jsonSerializer2 = this._valueSerializer;
                if (jsonSerializer2 == null) {
                    Class<?> cls = value.getClass();
                    JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        if (this._valueType.hasGenericTypes()) {
                            jsonSerializer3 = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                        } else {
                            jsonSerializer3 = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                        }
                        jsonSerializer2 = jsonSerializer3;
                        propertySerializerMap = this._dynamicValueSerializers;
                    } else {
                        jsonSerializer2 = serializerFor;
                    }
                }
                if (obj == JsonInclude.Include.NON_EMPTY && jsonSerializer2.isEmpty(serializerProvider, value)) {
                }
            } else if (obj == null) {
                jsonSerializer2 = serializerProvider.getDefaultNullValueSerializer();
            }
            try {
                jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
                jsonSerializer2.serialize(value, jsonGenerator, serializerProvider);
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, "" + key);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeFieldsUsing(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException {
        JsonSerializer<Object> jsonSerializer2 = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        for (Map.Entry next : map.entrySet()) {
            Object key = next.getKey();
            if (hashSet == null || !hashSet.contains(key)) {
                if (key == null) {
                    serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer2.serialize(key, jsonGenerator, serializerProvider);
                }
                Object value = next.getValue();
                if (value == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else if (typeSerializer == null) {
                    try {
                        jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, "" + key);
                    }
                } else {
                    jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
                }
            }
        }
    }

    public void serializeFilteredFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyFilter propertyFilter, Object obj) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> jsonSerializer2;
        JsonSerializer<Object> jsonSerializer3;
        HashSet<String> hashSet = this._ignoredEntries;
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        MapProperty mapProperty = new MapProperty(this._valueTypeSerializer, this._property);
        for (Map.Entry next : map.entrySet()) {
            Object key = next.getKey();
            if (hashSet == null || !hashSet.contains(key)) {
                if (key == null) {
                    jsonSerializer = serializerProvider.findNullKeySerializer(this._keyType, this._property);
                } else {
                    jsonSerializer = this._keySerializer;
                }
                Object value = next.getValue();
                if (value != null) {
                    jsonSerializer2 = this._valueSerializer;
                    if (jsonSerializer2 == null) {
                        Class<?> cls = value.getClass();
                        JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            if (this._valueType.hasGenericTypes()) {
                                jsonSerializer3 = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                            } else {
                                jsonSerializer3 = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                            }
                            jsonSerializer2 = jsonSerializer3;
                            propertySerializerMap = this._dynamicValueSerializers;
                        } else {
                            jsonSerializer2 = serializerFor;
                        }
                    }
                    if (obj == JsonInclude.Include.NON_EMPTY && jsonSerializer2.isEmpty(serializerProvider, value)) {
                    }
                } else if (obj == null) {
                    jsonSerializer2 = serializerProvider.getDefaultNullValueSerializer();
                }
                mapProperty.reset(key, jsonSerializer, jsonSerializer2);
                try {
                    propertyFilter.serializeAsField(value, jsonGenerator, serializerProvider, mapProperty);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, "" + key);
                }
            }
        }
    }

    @Deprecated
    public void serializeFilteredFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyFilter propertyFilter) throws IOException {
        serializeFilteredFields(map, jsonGenerator, serializerProvider, propertyFilter, serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES) ? null : JsonInclude.Include.NON_NULL);
    }

    /* access modifiers changed from: protected */
    public void serializeTypedFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, Object obj) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        JsonSerializer<Object> jsonSerializer2;
        JsonSerializer<Object> jsonSerializer3;
        HashSet<String> hashSet = this._ignoredEntries;
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Map.Entry next : map.entrySet()) {
            Object key = next.getKey();
            if (key == null) {
                jsonSerializer = serializerProvider.findNullKeySerializer(this._keyType, this._property);
            } else if (hashSet == null || !hashSet.contains(key)) {
                jsonSerializer = this._keySerializer;
            }
            Object value = next.getValue();
            if (value != null) {
                Class<?> cls = value.getClass();
                JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                if (serializerFor == null) {
                    if (this._valueType.hasGenericTypes()) {
                        jsonSerializer3 = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                    } else {
                        jsonSerializer3 = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                    jsonSerializer2 = jsonSerializer3;
                    propertySerializerMap = this._dynamicValueSerializers;
                } else {
                    jsonSerializer2 = serializerFor;
                }
                if (obj == JsonInclude.Include.NON_EMPTY && jsonSerializer2.isEmpty(serializerProvider, value)) {
                }
            } else if (obj == null) {
                jsonSerializer2 = serializerProvider.getDefaultNullValueSerializer();
            }
            jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
            try {
                jsonSerializer2.serializeWithType(value, jsonGenerator, serializerProvider, this._valueTypeSerializer);
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, "" + key);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void serializeTypedFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        serializeTypedFields(map, jsonGenerator, serializerProvider, serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES) ? null : JsonInclude.Include.NON_NULL);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(SlideshowPageFragment.ARG_OBJECT, true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonMapFormatVisitor expectMapFormat = jsonFormatVisitorWrapper == null ? null : jsonFormatVisitorWrapper.expectMapFormat(javaType);
        if (expectMapFormat != null) {
            expectMapFormat.keyFormat(this._keySerializer, this._keyType);
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findAndAddDynamic(this._dynamicValueSerializers, this._valueType, jsonFormatVisitorWrapper.getProvider());
            }
            expectMapFormat.valueFormat(jsonSerializer, this._valueType);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(cls, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicValueSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicValueSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public Map<?, ?> _orderEntries(Map<?, ?> map) {
        if (map instanceof SortedMap) {
            return map;
        }
        return new TreeMap(map);
    }
}

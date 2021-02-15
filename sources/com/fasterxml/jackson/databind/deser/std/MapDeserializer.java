package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JacksonStdImpl
public class MapDeserializer extends ContainerDeserializerBase<Map<Object, Object>> implements ContextualDeserializer, ResolvableDeserializer {
    private static final long serialVersionUID = 1;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected final boolean _hasDefaultCreator;
    protected HashSet<String> _ignorableProperties;
    protected final KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected boolean _standardStringKey;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;
    protected final TypeDeserializer _valueTypeDeserializer;

    public MapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType);
        this._mapType = javaType;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = valueInstantiator;
        this._hasDefaultCreator = valueInstantiator.canCreateUsingDefault();
        this._delegateDeserializer = null;
        this._propertyBasedCreator = null;
        this._standardStringKey = _isStdKeyDeser(javaType, keyDeserializer);
    }

    protected MapDeserializer(MapDeserializer mapDeserializer) {
        super(mapDeserializer._mapType);
        this._mapType = mapDeserializer._mapType;
        this._keyDeserializer = mapDeserializer._keyDeserializer;
        this._valueDeserializer = mapDeserializer._valueDeserializer;
        this._valueTypeDeserializer = mapDeserializer._valueTypeDeserializer;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = mapDeserializer._ignorableProperties;
        this._standardStringKey = mapDeserializer._standardStringKey;
    }

    protected MapDeserializer(MapDeserializer mapDeserializer, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, HashSet<String> hashSet) {
        super(mapDeserializer._mapType);
        JavaType javaType = mapDeserializer._mapType;
        this._mapType = javaType;
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
        this._valueInstantiator = mapDeserializer._valueInstantiator;
        this._propertyBasedCreator = mapDeserializer._propertyBasedCreator;
        this._delegateDeserializer = mapDeserializer._delegateDeserializer;
        this._hasDefaultCreator = mapDeserializer._hasDefaultCreator;
        this._ignorableProperties = hashSet;
        this._standardStringKey = _isStdKeyDeser(javaType, keyDeserializer);
    }

    /* access modifiers changed from: protected */
    public MapDeserializer withResolved(KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer, HashSet<String> hashSet) {
        if (this._keyDeserializer == keyDeserializer && this._valueDeserializer == jsonDeserializer && this._valueTypeDeserializer == typeDeserializer && this._ignorableProperties == hashSet) {
            return this;
        }
        return new MapDeserializer(this, keyDeserializer, (JsonDeserializer<Object>) jsonDeserializer, typeDeserializer, hashSet);
    }

    /* access modifiers changed from: protected */
    public final boolean _isStdKeyDeser(JavaType javaType, KeyDeserializer keyDeserializer) {
        JavaType keyType;
        if (keyDeserializer == null || (keyType = javaType.getKeyType()) == null) {
            return true;
        }
        Class<?> rawClass = keyType.getRawClass();
        if ((rawClass == String.class || rawClass == Object.class) && isDefaultKeyDeserializer(keyDeserializer)) {
            return true;
        }
        return false;
    }

    public void setIgnorableProperties(String[] strArr) {
        this._ignorableProperties = (strArr == null || strArr.length == 0) ? null : ArrayBuilders.arrayToSet(strArr);
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext.getConfig());
            if (delegateType != null) {
                this._delegateDeserializer = findDeserializer(deserializationContext, delegateType, (BeanProperty) null);
            } else {
                throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._mapType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
        }
        if (this._valueInstantiator.canCreateFromObjectWith()) {
            this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext, this._valueInstantiator, this._valueInstantiator.getFromObjectArguments(deserializationContext.getConfig()));
        }
        this._standardStringKey = _isStdKeyDeser(this._mapType, this._keyDeserializer);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer jsonDeserializer;
        AnnotatedMember member;
        HashSet<String> hashSet;
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        if (keyDeserializer == null) {
            keyDeserializer = deserializationContext.findKeyDeserializer(this._mapType.getKeyType(), beanProperty);
        } else if (keyDeserializer instanceof ContextualKeyDeserializer) {
            keyDeserializer = ((ContextualKeyDeserializer) keyDeserializer).createContextual(deserializationContext, beanProperty);
        }
        JsonDeserializer jsonDeserializer2 = this._valueDeserializer;
        if (beanProperty != null) {
            jsonDeserializer2 = findConvertingContentDeserializer(deserializationContext, beanProperty, jsonDeserializer2);
        }
        JavaType contentType = this._mapType.getContentType();
        if (jsonDeserializer2 == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(contentType, beanProperty);
        } else {
            jsonDeserializer = deserializationContext.handleSecondaryContextualization(jsonDeserializer2, beanProperty, contentType);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        HashSet<String> hashSet2 = this._ignorableProperties;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (!(annotationIntrospector == null || beanProperty == null || (member = beanProperty.getMember()) == null)) {
            String[] findPropertiesToIgnore = annotationIntrospector.findPropertiesToIgnore(member, false);
            if (findPropertiesToIgnore != null) {
                if (hashSet2 != null) {
                    hashSet = new HashSet<>(hashSet2);
                }
                hashSet2 = hashSet;
                for (String add : findPropertiesToIgnore) {
                    hashSet2.add(add);
                }
            }
        }
        return withResolved(keyDeserializer, typeDeserializer, jsonDeserializer, hashSet2);
    }

    public JavaType getContentType() {
        return this._mapType.getContentType();
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public boolean isCachable() {
        return this._valueDeserializer == null && this._keyDeserializer == null && this._valueTypeDeserializer == null && this._ignorableProperties == null;
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingCreator(jsonParser, deserializationContext);
        }
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Map) this._valueInstantiator.createUsingDelegate(deserializationContext, jsonDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (this._hasDefaultCreator) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.FIELD_NAME || currentToken == JsonToken.END_OBJECT) {
                Map<Object, Object> map = (Map) this._valueInstantiator.createUsingDefault(deserializationContext);
                if (this._standardStringKey) {
                    _readAndBindStringMap(jsonParser, deserializationContext, map);
                    return map;
                }
                _readAndBind(jsonParser, deserializationContext, map);
                return map;
            } else if (currentToken == JsonToken.VALUE_STRING) {
                return (Map) this._valueInstantiator.createFromString(deserializationContext, jsonParser.getText());
            } else {
                return (Map) _deserializeFromEmpty(jsonParser, deserializationContext);
            }
        } else {
            throw deserializationContext.instantiationException(getMapClass(), "No default constructor found");
        }
    }

    public Map<Object, Object> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException {
        jsonParser.setCurrentValue(map);
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken != JsonToken.START_OBJECT && currentToken != JsonToken.FIELD_NAME) {
            throw deserializationContext.mappingException(getMapClass());
        } else if (this._standardStringKey) {
            _readAndBindStringMap(jsonParser, deserializationContext, map);
            return map;
        } else {
            _readAndBind(jsonParser, deserializationContext, map);
            return map;
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public final Class<?> getMapClass() {
        return this._mapType.getRawClass();
    }

    public JavaType getValueType() {
        return this._mapType;
    }

    /* access modifiers changed from: protected */
    public final void _readAndBind(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException {
        String str;
        Object obj;
        KeyDeserializer keyDeserializer = this._keyDeserializer;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        boolean z = jsonDeserializer.getObjectIdReader() != null;
        MapReferringAccumulator mapReferringAccumulator = z ? new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), map) : null;
        if (jsonParser.isExpectedStartObjectToken()) {
            str = jsonParser.nextFieldName();
        } else {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.END_OBJECT) {
                if (currentToken == JsonToken.FIELD_NAME) {
                    str = jsonParser.getCurrentName();
                } else {
                    throw deserializationContext.mappingException(this._mapType.getRawClass(), jsonParser.getCurrentToken());
                }
            } else {
                return;
            }
        }
        while (str != null) {
            Object deserializeKey = keyDeserializer.deserializeKey(str, deserializationContext);
            JsonToken nextToken = jsonParser.nextToken();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(str)) {
                try {
                    if (nextToken == JsonToken.VALUE_NULL) {
                        obj = jsonDeserializer.getNullValue(deserializationContext);
                    } else if (typeDeserializer == null) {
                        obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    } else {
                        obj = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    if (z) {
                        mapReferringAccumulator.put(deserializeKey, obj);
                    } else {
                        map.put(deserializeKey, obj);
                    }
                } catch (UnresolvedForwardReference e) {
                    handleUnresolvedReference(jsonParser, mapReferringAccumulator, deserializeKey, e);
                } catch (Exception e2) {
                    wrapAndThrow(e2, map, str);
                }
            } else {
                jsonParser.skipChildren();
            }
            str = jsonParser.nextFieldName();
        }
    }

    /* access modifiers changed from: protected */
    public final void _readAndBindStringMap(JsonParser jsonParser, DeserializationContext deserializationContext, Map<Object, Object> map) throws IOException {
        String str;
        Object obj;
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        boolean z = jsonDeserializer.getObjectIdReader() != null;
        MapReferringAccumulator mapReferringAccumulator = z ? new MapReferringAccumulator(this._mapType.getContentType().getRawClass(), map) : null;
        if (jsonParser.isExpectedStartObjectToken()) {
            str = jsonParser.nextFieldName();
        } else {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != JsonToken.END_OBJECT) {
                if (currentToken == JsonToken.FIELD_NAME) {
                    str = jsonParser.getCurrentName();
                } else {
                    throw deserializationContext.mappingException(this._mapType.getRawClass(), jsonParser.getCurrentToken());
                }
            } else {
                return;
            }
        }
        while (str != null) {
            JsonToken nextToken = jsonParser.nextToken();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(str)) {
                try {
                    if (nextToken == JsonToken.VALUE_NULL) {
                        obj = jsonDeserializer.getNullValue(deserializationContext);
                    } else if (typeDeserializer == null) {
                        obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                    } else {
                        obj = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                    }
                    if (z) {
                        mapReferringAccumulator.put(str, obj);
                    } else {
                        map.put(str, obj);
                    }
                } catch (UnresolvedForwardReference e) {
                    handleUnresolvedReference(jsonParser, mapReferringAccumulator, str, e);
                } catch (Exception e2) {
                    wrapAndThrow(e2, map, str);
                }
            } else {
                jsonParser.skipChildren();
            }
            str = jsonParser.nextFieldName();
        }
    }

    public Map<Object, Object> _deserializeUsingCreator(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String str;
        Object obj;
        PropertyBasedCreator propertyBasedCreator = this._propertyBasedCreator;
        PropertyValueBuffer startBuilding = propertyBasedCreator.startBuilding(jsonParser, deserializationContext, (ObjectIdReader) null);
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (jsonParser.isExpectedStartObjectToken()) {
            str = jsonParser.nextFieldName();
        } else {
            str = jsonParser.hasToken(JsonToken.FIELD_NAME) ? jsonParser.getCurrentName() : null;
        }
        while (str != null) {
            JsonToken nextToken = jsonParser.nextToken();
            HashSet<String> hashSet = this._ignorableProperties;
            if (hashSet == null || !hashSet.contains(str)) {
                SettableBeanProperty findCreatorProperty = propertyBasedCreator.findCreatorProperty(str);
                if (findCreatorProperty == null) {
                    Object deserializeKey = this._keyDeserializer.deserializeKey(str, deserializationContext);
                    try {
                        if (nextToken == JsonToken.VALUE_NULL) {
                            obj = jsonDeserializer.getNullValue(deserializationContext);
                        } else if (typeDeserializer == null) {
                            obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
                        } else {
                            obj = jsonDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
                        }
                        startBuilding.bufferMapProperty(deserializeKey, obj);
                    } catch (Exception e) {
                        wrapAndThrow(e, this._mapType.getRawClass(), str);
                        return null;
                    }
                } else if (startBuilding.assignParameter(findCreatorProperty, findCreatorProperty.deserialize(jsonParser, deserializationContext))) {
                    jsonParser.nextToken();
                    try {
                        Map<Object, Object> map = (Map) propertyBasedCreator.build(deserializationContext, startBuilding);
                        _readAndBind(jsonParser, deserializationContext, map);
                        return map;
                    } catch (Exception e2) {
                        wrapAndThrow(e2, this._mapType.getRawClass(), str);
                        return null;
                    }
                }
            } else {
                jsonParser.skipChildren();
            }
            str = jsonParser.nextFieldName();
        }
        try {
            return (Map) propertyBasedCreator.build(deserializationContext, startBuilding);
        } catch (Exception e3) {
            wrapAndThrow(e3, this._mapType.getRawClass(), (String) null);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void wrapAndThrow(Throwable th, Object obj) throws IOException {
        wrapAndThrow(th, obj, (String) null);
    }

    private void handleUnresolvedReference(JsonParser jsonParser, MapReferringAccumulator mapReferringAccumulator, Object obj, UnresolvedForwardReference unresolvedForwardReference) throws JsonMappingException {
        if (mapReferringAccumulator != null) {
            unresolvedForwardReference.getRoid().appendReferring(mapReferringAccumulator.handleUnresolvedReference(unresolvedForwardReference, obj));
            return;
        }
        throw JsonMappingException.from(jsonParser, "Unresolved forward reference but no identity info.", (Throwable) unresolvedForwardReference);
    }

    private static final class MapReferringAccumulator {
        private List<MapReferring> _accumulator = new ArrayList();
        private Map<Object, Object> _result;
        private final Class<?> _valueType;

        public MapReferringAccumulator(Class<?> cls, Map<Object, Object> map) {
            this._valueType = cls;
            this._result = map;
        }

        public void put(Object obj, Object obj2) {
            if (this._accumulator.isEmpty()) {
                this._result.put(obj, obj2);
                return;
            }
            List<MapReferring> list = this._accumulator;
            list.get(list.size() - 1).next.put(obj, obj2);
        }

        public ReadableObjectId.Referring handleUnresolvedReference(UnresolvedForwardReference unresolvedForwardReference, Object obj) {
            MapReferring mapReferring = new MapReferring(this, unresolvedForwardReference, this._valueType, obj);
            this._accumulator.add(mapReferring);
            return mapReferring;
        }

        public void resolveForwardReference(Object obj, Object obj2) throws IOException {
            Iterator<MapReferring> it = this._accumulator.iterator();
            Map<Object, Object> map = this._result;
            while (it.hasNext()) {
                MapReferring next = it.next();
                if (next.hasId(obj)) {
                    it.remove();
                    map.put(next.key, obj2);
                    map.putAll(next.next);
                    return;
                }
                map = next.next;
            }
            throw new IllegalArgumentException("Trying to resolve a forward reference with id [" + obj + "] that wasn't previously seen as unresolved.");
        }
    }

    static final class MapReferring extends ReadableObjectId.Referring {
        private final MapReferringAccumulator _parent;
        public final Object key;
        public final Map<Object, Object> next = new LinkedHashMap();

        MapReferring(MapReferringAccumulator mapReferringAccumulator, UnresolvedForwardReference unresolvedForwardReference, Class<?> cls, Object obj) {
            super(unresolvedForwardReference, cls);
            this._parent = mapReferringAccumulator;
            this.key = obj;
        }

        public void handleResolvedForwardReference(Object obj, Object obj2) throws IOException {
            this._parent.resolveForwardReference(obj, obj2);
        }
    }
}

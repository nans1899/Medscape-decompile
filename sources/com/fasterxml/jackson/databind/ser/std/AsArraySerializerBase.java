package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.lang.reflect.Type;

public abstract class AsArraySerializerBase<T> extends ContainerSerializer<T> implements ContextualSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected final JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final Boolean _unwrapSingle;
    protected final TypeSerializer _valueTypeSerializer;

    /* access modifiers changed from: protected */
    public abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    public abstract AsArraySerializerBase<T> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected AsArraySerializerBase(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(cls, false);
        boolean z2 = false;
        this._elementType = javaType;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueTypeSerializer = typeSerializer;
        this._property = null;
        this._elementSerializer = jsonSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
        this._unwrapSingle = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @Deprecated
    protected AsArraySerializerBase(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(cls, false);
        boolean z2 = false;
        this._elementType = javaType;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._elementSerializer = jsonSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
        this._unwrapSingle = null;
    }

    protected AsArraySerializerBase(AsArraySerializerBase<?> asArraySerializerBase, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super((ContainerSerializer<?>) asArraySerializerBase);
        this._elementType = asArraySerializerBase._elementType;
        this._staticTyping = asArraySerializerBase._staticTyping;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._elementSerializer = jsonSerializer;
        this._dynamicSerializers = asArraySerializerBase._dynamicSerializers;
        this._unwrapSingle = bool;
    }

    @Deprecated
    protected AsArraySerializerBase(AsArraySerializerBase<?> asArraySerializerBase, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        this(asArraySerializerBase, beanProperty, typeSerializer, jsonSerializer, asArraySerializerBase._unwrapSingle);
    }

    @Deprecated
    public final AsArraySerializerBase<T> withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer) {
        return withResolved(beanProperty, typeSerializer, jsonSerializer, this._unwrapSingle);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: java.lang.Boolean} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        r2 = r2.findContentSerializer(r3);
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r7, com.fasterxml.jackson.databind.BeanProperty r8) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r6 = this;
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r0 = r6._valueTypeSerializer
            if (r0 == 0) goto L_0x0008
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r0 = r0.forProperty(r8)
        L_0x0008:
            r1 = 0
            if (r8 == 0) goto L_0x0037
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r7.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r3 = r8.getMember()
            if (r3 == 0) goto L_0x0020
            java.lang.Object r2 = r2.findContentSerializer(r3)
            if (r2 == 0) goto L_0x0020
            com.fasterxml.jackson.databind.JsonSerializer r2 = r7.serializerInstance(r3, r2)
            goto L_0x0021
        L_0x0020:
            r2 = r1
        L_0x0021:
            com.fasterxml.jackson.databind.SerializationConfig r3 = r7.getConfig()
            java.lang.Class r4 = r6._handledType
            com.fasterxml.jackson.annotation.JsonFormat$Value r3 = r8.findPropertyFormat(r3, r4)
            if (r3 == 0) goto L_0x0033
            com.fasterxml.jackson.annotation.JsonFormat$Feature r1 = com.fasterxml.jackson.annotation.JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED
            java.lang.Boolean r1 = r3.getFeature(r1)
        L_0x0033:
            r5 = r2
            r2 = r1
            r1 = r5
            goto L_0x0038
        L_0x0037:
            r2 = r1
        L_0x0038:
            if (r1 != 0) goto L_0x003c
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r1 = r6._elementSerializer
        L_0x003c:
            com.fasterxml.jackson.databind.JsonSerializer r1 = r6.findConvertingContentSerializer(r7, r8, r1)
            if (r1 != 0) goto L_0x0057
            com.fasterxml.jackson.databind.JavaType r3 = r6._elementType
            if (r3 == 0) goto L_0x005b
            boolean r4 = r6._staticTyping
            if (r4 == 0) goto L_0x005b
            boolean r3 = r3.isJavaLangObject()
            if (r3 != 0) goto L_0x005b
            com.fasterxml.jackson.databind.JavaType r1 = r6._elementType
            com.fasterxml.jackson.databind.JsonSerializer r1 = r7.findValueSerializer((com.fasterxml.jackson.databind.JavaType) r1, (com.fasterxml.jackson.databind.BeanProperty) r8)
            goto L_0x005b
        L_0x0057:
            com.fasterxml.jackson.databind.JsonSerializer r1 = r7.handleSecondaryContextualization(r1, r8)
        L_0x005b:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r7 = r6._elementSerializer
            if (r1 != r7) goto L_0x006d
            com.fasterxml.jackson.databind.BeanProperty r7 = r6._property
            if (r8 != r7) goto L_0x006d
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r7 = r6._valueTypeSerializer
            if (r7 != r0) goto L_0x006d
            java.lang.Boolean r7 = r6._unwrapSingle
            if (r7 == r2) goto L_0x006c
            goto L_0x006d
        L_0x006c:
            return r6
        L_0x006d:
            com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase r7 = r6.withResolved(r8, r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    public void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (!serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED) || !hasSingleElement(t)) {
            jsonGenerator.writeStartArray();
            jsonGenerator.setCurrentValue(t);
            serializeContents(t, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
            return;
        }
        serializeContents(t, jsonGenerator, serializerProvider);
    }

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
        jsonGenerator.setCurrentValue(t);
        serializeContents(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        JavaType javaType = this._elementType;
        if (javaType != null) {
            JsonNode jsonNode = null;
            if (javaType.getRawClass() != Object.class) {
                JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(javaType, this._property);
                if (findValueSerializer instanceof SchemaAware) {
                    jsonNode = ((SchemaAware) findValueSerializer).getSchema(serializerProvider, (Type) null);
                }
            }
            if (jsonNode == null) {
                jsonNode = JsonSchema.getDefaultSchemaNode();
            }
            createSchemaNode.set(FirebaseAnalytics.Param.ITEMS, jsonNode);
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        if (jsonSerializer == null) {
            jsonSerializer = jsonFormatVisitorWrapper.getProvider().findValueSerializer(this._elementType, this._property);
        }
        visitArrayFormat(jsonFormatVisitorWrapper, javaType, jsonSerializer, this._elementType);
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(cls, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSecondarySerializer.map) {
            this._dynamicSerializers = findAndAddSecondarySerializer.map;
        }
        return findAndAddSecondarySerializer.serializer;
    }
}

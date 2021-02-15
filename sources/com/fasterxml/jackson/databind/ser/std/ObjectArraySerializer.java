package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class ObjectArraySerializer extends ArraySerializerBase<Object[]> implements ContextualSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    public ObjectArraySerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(Object[].class);
        this._elementType = javaType;
        this._staticTyping = z;
        this._valueTypeSerializer = typeSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
        this._elementSerializer = jsonSerializer;
    }

    public ObjectArraySerializer(ObjectArraySerializer objectArraySerializer, TypeSerializer typeSerializer) {
        super((ArraySerializerBase<?>) objectArraySerializer);
        this._elementType = objectArraySerializer._elementType;
        this._valueTypeSerializer = typeSerializer;
        this._staticTyping = objectArraySerializer._staticTyping;
        this._dynamicSerializers = objectArraySerializer._dynamicSerializers;
        this._elementSerializer = objectArraySerializer._elementSerializer;
    }

    public ObjectArraySerializer(ObjectArraySerializer objectArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super(objectArraySerializer, beanProperty, bool);
        this._elementType = objectArraySerializer._elementType;
        this._valueTypeSerializer = typeSerializer;
        this._staticTyping = objectArraySerializer._staticTyping;
        this._dynamicSerializers = objectArraySerializer._dynamicSerializers;
        this._elementSerializer = jsonSerializer;
    }

    public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
        return new ObjectArraySerializer(this, beanProperty, this._valueTypeSerializer, this._elementSerializer, bool);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new ObjectArraySerializer(this._elementType, this._staticTyping, typeSerializer, this._elementSerializer);
    }

    public ObjectArraySerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        if (this._property == beanProperty && jsonSerializer == this._elementSerializer && this._valueTypeSerializer == typeSerializer && this._unwrapSingle == bool) {
            return this;
        }
        return new ObjectArraySerializer(this, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v14, resolved type: java.lang.Boolean} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        r3 = r3.findContentSerializer(r2);
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
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = r8.getMember()
            com.fasterxml.jackson.databind.AnnotationIntrospector r3 = r7.getAnnotationIntrospector()
            if (r2 == 0) goto L_0x0020
            java.lang.Object r3 = r3.findContentSerializer(r2)
            if (r3 == 0) goto L_0x0020
            com.fasterxml.jackson.databind.JsonSerializer r2 = r7.serializerInstance(r2, r3)
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
            com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer r7 = r6.withResolved(r8, r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    public boolean isEmpty(SerializerProvider serializerProvider, Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    public boolean hasSingleElement(Object[] objArr) {
        return objArr.length == 1;
    }

    public final void serialize(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int length = objArr.length;
        if (length != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
            jsonGenerator.writeStartArray(length);
            serializeContents(objArr, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
            return;
        }
        serializeContents(objArr, jsonGenerator, serializerProvider);
    }

    public void serializeContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (r0 != 0) {
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            if (jsonSerializer != null) {
                serializeContentsUsing(objArr, jsonGenerator, serializerProvider, jsonSerializer);
            } else if (this._valueTypeSerializer != null) {
                serializeTypedContents(objArr, jsonGenerator, serializerProvider);
            } else {
                try {
                    PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                    for (Object obj : objArr) {
                        if (obj == null) {
                            serializerProvider.defaultSerializeNull(jsonGenerator);
                        } else {
                            Class<?> cls = obj.getClass();
                            JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                            if (serializerFor == null) {
                                if (this._elementType.hasGenericTypes()) {
                                    serializerFor = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                                } else {
                                    serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                                }
                            }
                            serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                        }
                    }
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    e = e2;
                    while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                        e = e.getCause();
                    }
                    if (e instanceof Error) {
                        throw ((Error) e);
                    }
                    throw JsonMappingException.wrapWithPath(e, (Object) null, 0);
                }
            }
        }
    }

    public void serializeContentsUsing(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException {
        int length = objArr.length;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        int i = 0;
        Object obj = null;
        while (i < length) {
            try {
                obj = objArr[i];
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                e = e2;
                while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                    e = e.getCause();
                }
                if (e instanceof Error) {
                    throw ((Error) e);
                }
                throw JsonMappingException.wrapWithPath(e, obj, i);
            }
        }
    }

    public void serializeTypedContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        try {
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            for (Object obj : objArr) {
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class<?> cls = obj.getClass();
                    JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                    serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw ((Error) e);
            }
            throw JsonMappingException.wrapWithPath(e, (Object) null, 0);
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        if (type != null) {
            JavaType constructType = serializerProvider.constructType(type);
            if (constructType.isArrayType()) {
                Class<?> rawClass = ((ArrayType) constructType).getContentType().getRawClass();
                if (rawClass == Object.class) {
                    createSchemaNode.set(FirebaseAnalytics.Param.ITEMS, JsonSchema.getDefaultSchemaNode());
                } else {
                    JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(rawClass, this._property);
                    createSchemaNode.set(FirebaseAnalytics.Param.ITEMS, findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider, (Type) null) : JsonSchema.getDefaultSchemaNode());
                }
            }
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
        if (expectArrayFormat != null) {
            JavaType moreSpecificType = jsonFormatVisitorWrapper.getProvider().getTypeFactory().moreSpecificType(this._elementType, javaType.getContentType());
            if (moreSpecificType != null) {
                JsonSerializer<Object> jsonSerializer = this._elementSerializer;
                if (jsonSerializer == null) {
                    jsonSerializer = jsonFormatVisitorWrapper.getProvider().findValueSerializer(moreSpecificType, this._property);
                }
                expectArrayFormat.itemsFormat(jsonSerializer, moreSpecificType);
                return;
            }
            throw JsonMappingException.from(jsonFormatVisitorWrapper.getProvider(), "Could not resolve type");
        }
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

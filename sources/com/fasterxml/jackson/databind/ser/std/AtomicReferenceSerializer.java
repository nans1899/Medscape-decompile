package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceSerializer extends StdSerializer<AtomicReference<?>> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected final JsonInclude.Include _contentInclusion;
    protected transient PropertySerializerMap _dynamicSerializers;
    protected final BeanProperty _property;
    protected final JavaType _referredType;
    protected final NameTransformer _unwrapper;
    protected final JsonSerializer<Object> _valueSerializer;
    protected final TypeSerializer _valueTypeSerializer;

    public AtomicReferenceSerializer(ReferenceType referenceType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super((JavaType) referenceType);
        this._referredType = referenceType.getReferencedType();
        this._property = null;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer;
        this._unwrapper = null;
        this._contentInclusion = null;
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
    }

    protected AtomicReferenceSerializer(AtomicReferenceSerializer atomicReferenceSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, JsonInclude.Include include) {
        super((StdSerializer<?>) atomicReferenceSerializer);
        this._referredType = atomicReferenceSerializer._referredType;
        this._dynamicSerializers = atomicReferenceSerializer._dynamicSerializers;
        this._property = beanProperty;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer;
        this._unwrapper = nameTransformer;
        if (include == JsonInclude.Include.USE_DEFAULTS || include == JsonInclude.Include.ALWAYS) {
            this._contentInclusion = null;
        } else {
            this._contentInclusion = include;
        }
    }

    public JsonSerializer<AtomicReference<?>> unwrappingSerializer(NameTransformer nameTransformer) {
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer != null) {
            jsonSerializer = jsonSerializer.unwrappingSerializer(nameTransformer);
        }
        JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
        NameTransformer nameTransformer2 = this._unwrapper;
        if (nameTransformer2 != null) {
            nameTransformer = NameTransformer.chainedTransformer(nameTransformer, nameTransformer2);
        }
        return withResolved(this._property, this._valueTypeSerializer, jsonSerializer2, nameTransformer, this._contentInclusion);
    }

    /* access modifiers changed from: protected */
    public AtomicReferenceSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, NameTransformer nameTransformer, JsonInclude.Include include) {
        if (this._property == beanProperty && include == this._contentInclusion && this._valueTypeSerializer == typeSerializer && this._valueSerializer == jsonSerializer && this._unwrapper == nameTransformer) {
            return this;
        }
        return new AtomicReferenceSerializer(this, beanProperty, typeSerializer, jsonSerializer, nameTransformer, include);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        r8 = r9.findPropertyInclusion(r8.getConfig(), java.util.concurrent.atomic.AtomicReference.class).getContentInclusion();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r8, com.fasterxml.jackson.databind.BeanProperty r9) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r7 = this;
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r0 = r7._valueTypeSerializer
            if (r0 == 0) goto L_0x0008
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r0 = r0.forProperty(r9)
        L_0x0008:
            r3 = r0
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r0 = r7._valueSerializer
            if (r0 != 0) goto L_0x001c
            com.fasterxml.jackson.databind.JavaType r1 = r7._referredType
            boolean r1 = r7._useStatic(r8, r9, r1)
            if (r1 == 0) goto L_0x0020
            com.fasterxml.jackson.databind.JavaType r0 = r7._referredType
            com.fasterxml.jackson.databind.JsonSerializer r0 = r7._findSerializer((com.fasterxml.jackson.databind.SerializerProvider) r8, (com.fasterxml.jackson.databind.JavaType) r0, (com.fasterxml.jackson.databind.BeanProperty) r9)
            goto L_0x0020
        L_0x001c:
            com.fasterxml.jackson.databind.JsonSerializer r0 = r8.handlePrimaryContextualization(r0, r9)
        L_0x0020:
            r4 = r0
            com.fasterxml.jackson.annotation.JsonInclude$Include r0 = r7._contentInclusion
            if (r9 == 0) goto L_0x003b
            com.fasterxml.jackson.databind.SerializationConfig r8 = r8.getConfig()
            java.lang.Class<java.util.concurrent.atomic.AtomicReference> r1 = java.util.concurrent.atomic.AtomicReference.class
            com.fasterxml.jackson.annotation.JsonInclude$Value r8 = r9.findPropertyInclusion(r8, r1)
            com.fasterxml.jackson.annotation.JsonInclude$Include r8 = r8.getContentInclusion()
            if (r8 == r0) goto L_0x003b
            com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS
            if (r8 == r1) goto L_0x003b
            r6 = r8
            goto L_0x003c
        L_0x003b:
            r6 = r0
        L_0x003c:
            com.fasterxml.jackson.databind.util.NameTransformer r5 = r7._unwrapper
            r1 = r7
            r2 = r9
            com.fasterxml.jackson.databind.ser.std.AtomicReferenceSerializer r8 = r1.withResolved(r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.AtomicReferenceSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* access modifiers changed from: protected */
    public boolean _useStatic(SerializerProvider serializerProvider, BeanProperty beanProperty, JavaType javaType) {
        if (javaType.isJavaLangObject()) {
            return false;
        }
        if (javaType.isFinal() || javaType.useStaticType()) {
            return true;
        }
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        if (!(annotationIntrospector == null || beanProperty == null || beanProperty.getMember() == null)) {
            JsonSerialize.Typing findSerializationTyping = annotationIntrospector.findSerializationTyping(beanProperty.getMember());
            if (findSerializationTyping == JsonSerialize.Typing.STATIC) {
                return true;
            }
            if (findSerializationTyping == JsonSerialize.Typing.DYNAMIC) {
                return false;
            }
        }
        return serializerProvider.isEnabled(MapperFeature.USE_STATIC_TYPING);
    }

    public boolean isEmpty(SerializerProvider serializerProvider, AtomicReference<?> atomicReference) {
        Object obj;
        if (atomicReference == null || (obj = atomicReference.get()) == null) {
            return true;
        }
        if (this._contentInclusion == null) {
            return false;
        }
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer == null) {
            try {
                jsonSerializer = _findCachedSerializer(serializerProvider, atomicReference.getClass());
            } catch (JsonMappingException e) {
                throw new RuntimeJsonMappingException(e);
            }
        }
        return jsonSerializer.isEmpty(serializerProvider, obj);
    }

    public boolean isUnwrappingSerializer() {
        return this._unwrapper != null;
    }

    public void serialize(AtomicReference<?> atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object obj = atomicReference.get();
        if (obj != null) {
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findCachedSerializer(serializerProvider, obj.getClass());
            }
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            if (typeSerializer != null) {
                jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
            } else {
                jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
            }
        } else if (this._unwrapper == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        }
    }

    public void serializeWithType(AtomicReference<?> atomicReference, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        if (atomicReference.get() != null) {
            typeSerializer.writeTypePrefixForScalar(atomicReference, jsonGenerator);
            serialize(atomicReference, jsonGenerator, serializerProvider);
            typeSerializer.writeTypeSuffixForScalar(atomicReference, jsonGenerator);
        } else if (this._unwrapper == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        }
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer == null) {
            jsonSerializer = _findSerializer(jsonFormatVisitorWrapper.getProvider(), this._referredType, this._property);
            NameTransformer nameTransformer = this._unwrapper;
            if (nameTransformer != null) {
                jsonSerializer = jsonSerializer.unwrappingSerializer(nameTransformer);
            }
        }
        jsonSerializer.acceptJsonFormatVisitor(jsonFormatVisitorWrapper, this._referredType);
    }

    private final JsonSerializer<Object> _findCachedSerializer(SerializerProvider serializerProvider, Class<?> cls) throws JsonMappingException {
        JsonSerializer<Object> serializerFor = this._dynamicSerializers.serializerFor(cls);
        if (serializerFor != null) {
            return serializerFor;
        }
        JsonSerializer<Object> _findSerializer = _findSerializer(serializerProvider, cls, this._property);
        NameTransformer nameTransformer = this._unwrapper;
        if (nameTransformer != null) {
            _findSerializer = _findSerializer.unwrappingSerializer(nameTransformer);
        }
        JsonSerializer<Object> jsonSerializer = _findSerializer;
        this._dynamicSerializers = this._dynamicSerializers.newWith(cls, jsonSerializer);
        return jsonSerializer;
    }

    private final JsonSerializer<Object> _findSerializer(SerializerProvider serializerProvider, Class<?> cls, BeanProperty beanProperty) throws JsonMappingException {
        return serializerProvider.findTypedValueSerializer(cls, true, beanProperty);
    }

    private final JsonSerializer<Object> _findSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return serializerProvider.findTypedValueSerializer(javaType, true, beanProperty);
    }
}

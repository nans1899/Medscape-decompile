package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class MapProperty extends PropertyWriter {
    private static final long serialVersionUID = 1;
    protected Object _key;
    protected JsonSerializer<Object> _keySerializer;
    protected final BeanProperty _property;
    protected final TypeSerializer _typeSerializer;
    protected JsonSerializer<Object> _valueSerializer;

    @Deprecated
    public void depositSchemaProperty(ObjectNode objectNode, SerializerProvider serializerProvider) throws JsonMappingException {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MapProperty(TypeSerializer typeSerializer, BeanProperty beanProperty) {
        super(beanProperty == null ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : beanProperty.getMetadata());
        this._typeSerializer = typeSerializer;
        this._property = beanProperty;
    }

    public void reset(Object obj, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        this._key = obj;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
    }

    public String getName() {
        Object obj = this._key;
        if (obj instanceof String) {
            return (String) obj;
        }
        return String.valueOf(obj);
    }

    public PropertyName getFullName() {
        return new PropertyName(getName());
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        BeanProperty beanProperty = this._property;
        if (beanProperty == null) {
            return null;
        }
        return beanProperty.getAnnotation(cls);
    }

    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        BeanProperty beanProperty = this._property;
        if (beanProperty == null) {
            return null;
        }
        return beanProperty.getContextAnnotation(cls);
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        this._keySerializer.serialize(this._key, jsonGenerator, serializerProvider);
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            this._valueSerializer.serialize(obj, jsonGenerator, serializerProvider);
        } else {
            this._valueSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    public void serializeAsOmittedField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        if (!jsonGenerator.canOmitFields()) {
            jsonGenerator.writeOmittedField(getName());
        }
    }

    public void serializeAsElement(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            this._valueSerializer.serialize(obj, jsonGenerator, serializerProvider);
        } else {
            this._valueSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    public void serializeAsPlaceholder(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        jsonGenerator.writeNull();
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {
        BeanProperty beanProperty = this._property;
        if (beanProperty != null) {
            beanProperty.depositSchemaProperty(jsonObjectFormatVisitor, serializerProvider);
        }
    }

    public JavaType getType() {
        BeanProperty beanProperty = this._property;
        return beanProperty == null ? TypeFactory.unknownType() : beanProperty.getType();
    }

    public PropertyName getWrapperName() {
        BeanProperty beanProperty = this._property;
        if (beanProperty == null) {
            return null;
        }
        return beanProperty.getWrapperName();
    }

    public AnnotatedMember getMember() {
        BeanProperty beanProperty = this._property;
        if (beanProperty == null) {
            return null;
        }
        return beanProperty.getMember();
    }
}

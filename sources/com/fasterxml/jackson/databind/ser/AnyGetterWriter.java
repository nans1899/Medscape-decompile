package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import java.util.Map;

public class AnyGetterWriter {
    protected final AnnotatedMember _accessor;
    protected MapSerializer _mapSerializer;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _serializer;

    public AnyGetterWriter(BeanProperty beanProperty, AnnotatedMember annotatedMember, JsonSerializer<?> jsonSerializer) {
        this._accessor = annotatedMember;
        this._property = beanProperty;
        this._serializer = jsonSerializer;
        if (jsonSerializer instanceof MapSerializer) {
            this._mapSerializer = (MapSerializer) jsonSerializer;
        }
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object value = this._accessor.getValue(obj);
        if (value != null) {
            if (value instanceof Map) {
                MapSerializer mapSerializer = this._mapSerializer;
                if (mapSerializer != null) {
                    mapSerializer.serializeFields((Map) value, jsonGenerator, serializerProvider);
                } else {
                    this._serializer.serialize(value, jsonGenerator, serializerProvider);
                }
            } else {
                throw JsonMappingException.from(jsonGenerator, "Value returned by 'any-getter' (" + this._accessor.getName() + "()) not java.util.Map but " + value.getClass().getName());
            }
        }
    }

    public void getAndFilter(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, PropertyFilter propertyFilter) throws Exception {
        Object value = this._accessor.getValue(obj);
        if (value != null) {
            if (value instanceof Map) {
                MapSerializer mapSerializer = this._mapSerializer;
                if (mapSerializer != null) {
                    mapSerializer.serializeFilteredFields((Map) value, jsonGenerator, serializerProvider, propertyFilter, (Object) null);
                } else {
                    this._serializer.serialize(value, jsonGenerator, serializerProvider);
                }
            } else {
                throw JsonMappingException.from(jsonGenerator, "Value returned by 'any-getter' (" + this._accessor.getName() + "()) not java.util.Map but " + value.getClass().getName());
            }
        }
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._serializer;
        if (jsonSerializer instanceof ContextualSerializer) {
            JsonSerializer<?> handlePrimaryContextualization = serializerProvider.handlePrimaryContextualization(jsonSerializer, this._property);
            this._serializer = handlePrimaryContextualization;
            if (handlePrimaryContextualization instanceof MapSerializer) {
                this._mapSerializer = (MapSerializer) handlePrimaryContextualization;
            }
        }
    }
}

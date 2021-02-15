package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;

public class EnumSetSerializer extends AsArraySerializerBase<EnumSet<? extends Enum<?>>> {
    public EnumSetSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    public EnumSetSerializer(JavaType javaType) {
        super((Class<?>) EnumSet.class, javaType, true, (TypeSerializer) null, (JsonSerializer<Object>) null);
    }

    @Deprecated
    public EnumSetSerializer(JavaType javaType, BeanProperty beanProperty) {
        this(javaType);
    }

    public EnumSetSerializer(EnumSetSerializer enumSetSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super((AsArraySerializerBase<?>) enumSetSerializer, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    public EnumSetSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        return new EnumSetSerializer(this, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    public boolean isEmpty(SerializerProvider serializerProvider, EnumSet<? extends Enum<?>> enumSet) {
        return enumSet == null || enumSet.isEmpty();
    }

    public boolean hasSingleElement(EnumSet<? extends Enum<?>> enumSet) {
        return enumSet.size() == 1;
    }

    public final void serialize(EnumSet<? extends Enum<?>> enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int size = enumSet.size();
        if (size != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
            jsonGenerator.writeStartArray(size);
            serializeContents(enumSet, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
            return;
        }
        serializeContents(enumSet, jsonGenerator, serializerProvider);
    }

    public void serializeContents(EnumSet<? extends Enum<?>> enumSet, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            Enum enumR = (Enum) it.next();
            if (jsonSerializer == null) {
                jsonSerializer = serializerProvider.findValueSerializer((Class<?>) enumR.getDeclaringClass(), this._property);
            }
            jsonSerializer.serialize(enumR, jsonGenerator, serializerProvider);
        }
    }
}

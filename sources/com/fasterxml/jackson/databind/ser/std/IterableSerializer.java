package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IterableSerializer extends AsArraySerializerBase<Iterable<?>> {
    public IterableSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        super((Class<?>) Iterable.class, javaType, z, typeSerializer, (JsonSerializer<Object>) null);
    }

    public IterableSerializer(IterableSerializer iterableSerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super((AsArraySerializerBase<?>) iterableSerializer, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new IterableSerializer(this, this._property, typeSerializer, this._elementSerializer, this._unwrapSingle);
    }

    public IterableSerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        return new IterableSerializer(this, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    public boolean isEmpty(SerializerProvider serializerProvider, Iterable<?> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

    public boolean hasSingleElement(Iterable<?> iterable) {
        if (iterable == null) {
            return false;
        }
        Iterator<?> it = iterable.iterator();
        if (!it.hasNext()) {
            return false;
        }
        it.next();
        return !it.hasNext();
    }

    public final void serialize(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE) || !hasSingleElement(iterable)) {
            jsonGenerator.writeStartArray();
            serializeContents(iterable, jsonGenerator, serializerProvider);
            jsonGenerator.writeEndArray();
            return;
        }
        serializeContents(iterable, jsonGenerator, serializerProvider);
    }

    public void serializeContents(Iterable<?> iterable, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JsonSerializer<Object> jsonSerializer;
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            TypeSerializer typeSerializer = this._valueTypeSerializer;
            Class<?> cls = null;
            JsonSerializer<Object> jsonSerializer2 = null;
            do {
                Object next = it.next();
                if (next == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    JsonSerializer<Object> jsonSerializer3 = this._elementSerializer;
                    if (jsonSerializer3 == null) {
                        Class<?> cls2 = next.getClass();
                        if (cls2 != cls) {
                            jsonSerializer2 = serializerProvider.findValueSerializer(cls2, this._property);
                            cls = cls2;
                        }
                        jsonSerializer = jsonSerializer2;
                    } else {
                        JsonSerializer<Object> jsonSerializer4 = jsonSerializer3;
                        jsonSerializer = jsonSerializer2;
                        jsonSerializer2 = jsonSerializer4;
                    }
                    if (typeSerializer == null) {
                        jsonSerializer2.serialize(next, jsonGenerator, serializerProvider);
                    } else {
                        jsonSerializer2.serializeWithType(next, jsonGenerator, serializerProvider, typeSerializer);
                    }
                    jsonSerializer2 = jsonSerializer;
                }
            } while (it.hasNext());
        }
    }
}

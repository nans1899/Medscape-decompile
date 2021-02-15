package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.io.IOException;

public class RawValue implements JsonSerializable {
    protected Object _value;

    public RawValue(String str) {
        this._value = str;
    }

    public RawValue(SerializableString serializableString) {
        this._value = serializableString;
    }

    public RawValue(JsonSerializable jsonSerializable) {
        this._value = jsonSerializable;
    }

    protected RawValue(Object obj, boolean z) {
        this._value = obj;
    }

    public Object rawValue() {
        return this._value;
    }

    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object obj = this._value;
        if (obj instanceof JsonSerializable) {
            ((JsonSerializable) obj).serialize(jsonGenerator, serializerProvider);
        } else {
            _serialize(jsonGenerator);
        }
    }

    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        Object obj = this._value;
        if (obj instanceof JsonSerializable) {
            ((JsonSerializable) obj).serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
        } else if (obj instanceof SerializableString) {
            serialize(jsonGenerator, serializerProvider);
        }
    }

    public void serialize(JsonGenerator jsonGenerator) throws IOException {
        Object obj = this._value;
        if (obj instanceof JsonSerializable) {
            jsonGenerator.writeObject(obj);
        } else {
            _serialize(jsonGenerator);
        }
    }

    /* access modifiers changed from: protected */
    public void _serialize(JsonGenerator jsonGenerator) throws IOException {
        Object obj = this._value;
        if (obj instanceof SerializableString) {
            jsonGenerator.writeRawValue((SerializableString) obj);
        } else {
            jsonGenerator.writeRawValue(String.valueOf(obj));
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawValue)) {
            return false;
        }
        Object obj2 = this._value;
        Object obj3 = ((RawValue) obj)._value;
        if (obj2 == obj3) {
            return true;
        }
        if (obj2 == null || !obj2.equals(obj3)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object obj = this._value;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        Object[] objArr = new Object[1];
        Object obj = this._value;
        objArr[0] = obj == null ? "NULL" : obj.getClass().getName();
        return String.format("[RawValue of type %s]", objArr);
    }
}

package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;

public class POJONode extends ValueNode {
    protected final Object _value;

    public POJONode(Object obj) {
        this._value = obj;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.POJO;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_EMBEDDED_OBJECT;
    }

    public byte[] binaryValue() throws IOException {
        Object obj = this._value;
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        return super.binaryValue();
    }

    public String asText() {
        Object obj = this._value;
        return obj == null ? "null" : obj.toString();
    }

    public String asText(String str) {
        Object obj = this._value;
        return obj == null ? str : obj.toString();
    }

    public boolean asBoolean(boolean z) {
        Object obj = this._value;
        return (obj == null || !(obj instanceof Boolean)) ? z : ((Boolean) obj).booleanValue();
    }

    public int asInt(int i) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).intValue() : i;
    }

    public long asLong(long j) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).longValue() : j;
    }

    public double asDouble(double d) {
        Object obj = this._value;
        return obj instanceof Number ? ((Number) obj).doubleValue() : d;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Object obj = this._value;
        if (obj == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else if (obj instanceof JsonSerializable) {
            ((JsonSerializable) obj).serialize(jsonGenerator, serializerProvider);
        } else {
            jsonGenerator.writeObject(obj);
        }
    }

    public Object getPojo() {
        return this._value;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof POJONode)) {
            return _pojoEquals((POJONode) obj);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean _pojoEquals(POJONode pOJONode) {
        Object obj = this._value;
        if (obj == null) {
            return pOJONode._value == null;
        }
        return obj.equals(pOJONode._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        Object obj = this._value;
        if (obj instanceof byte[]) {
            return String.format("(binary value of %d bytes)", new Object[]{Integer.valueOf(((byte[]) obj).length)});
        } else if (!(obj instanceof RawValue)) {
            return String.valueOf(obj);
        } else {
            return String.format("(raw value '%s')", new Object[]{((RawValue) obj).toString()});
        }
    }
}

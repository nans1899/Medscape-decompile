package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FloatNode extends NumericNode {
    protected final float _value;

    public boolean isFloat() {
        return true;
    }

    public boolean isFloatingPointNumber() {
        return true;
    }

    public FloatNode(float f) {
        this._value = f;
    }

    public static FloatNode valueOf(float f) {
        return new FloatNode(f);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.FLOAT;
    }

    public boolean canConvertToInt() {
        float f = this._value;
        return f >= -2.14748365E9f && f <= 2.14748365E9f;
    }

    public boolean canConvertToLong() {
        float f = this._value;
        return f >= -9.223372E18f && f <= 9.223372E18f;
    }

    public Number numberValue() {
        return Float.valueOf(this._value);
    }

    public short shortValue() {
        return (short) ((int) this._value);
    }

    public int intValue() {
        return (int) this._value;
    }

    public long longValue() {
        return (long) this._value;
    }

    public float floatValue() {
        return this._value;
    }

    public double doubleValue() {
        return (double) this._value;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.valueOf((double) this._value);
    }

    public BigInteger bigIntegerValue() {
        return decimalValue().toBigInteger();
    }

    public String asText() {
        return Float.toString(this._value);
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(this._value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof FloatNode)) {
            return false;
        }
        if (Float.compare(this._value, ((FloatNode) obj)._value) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Float.floatToIntBits(this._value);
    }
}

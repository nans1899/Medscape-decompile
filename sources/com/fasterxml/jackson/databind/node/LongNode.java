package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class LongNode extends NumericNode {
    protected final long _value;

    public boolean canConvertToLong() {
        return true;
    }

    public boolean isIntegralNumber() {
        return true;
    }

    public boolean isLong() {
        return true;
    }

    public LongNode(long j) {
        this._value = j;
    }

    public static LongNode valueOf(long j) {
        return new LongNode(j);
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.LONG;
    }

    public boolean canConvertToInt() {
        long j = this._value;
        return j >= -2147483648L && j <= 2147483647L;
    }

    public Number numberValue() {
        return Long.valueOf(this._value);
    }

    public short shortValue() {
        return (short) ((int) this._value);
    }

    public int intValue() {
        return (int) this._value;
    }

    public long longValue() {
        return this._value;
    }

    public float floatValue() {
        return (float) this._value;
    }

    public double doubleValue() {
        return (double) this._value;
    }

    public BigDecimal decimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    public String asText() {
        return NumberOutput.toString(this._value);
    }

    public boolean asBoolean(boolean z) {
        return this._value != 0;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(this._value);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof LongNode) && ((LongNode) obj)._value == this._value) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this._value;
        return ((int) j) ^ ((int) (j >> 32));
    }
}

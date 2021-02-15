package com.fasterxml.jackson.databind.node;

import com.facebook.internal.ServerProtocol;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;

public class BooleanNode extends ValueNode {
    public static final BooleanNode FALSE = new BooleanNode(false);
    public static final BooleanNode TRUE = new BooleanNode(true);
    private final boolean _value;

    private BooleanNode(boolean z) {
        this._value = z;
    }

    public static BooleanNode getTrue() {
        return TRUE;
    }

    public static BooleanNode getFalse() {
        return FALSE;
    }

    public static BooleanNode valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.BOOLEAN;
    }

    public JsonToken asToken() {
        return this._value ? JsonToken.VALUE_TRUE : JsonToken.VALUE_FALSE;
    }

    public boolean booleanValue() {
        return this._value;
    }

    public String asText() {
        return this._value ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
    }

    public boolean asBoolean() {
        return this._value;
    }

    public boolean asBoolean(boolean z) {
        return this._value;
    }

    public int asInt(int i) {
        return this._value ? 1 : 0;
    }

    public long asLong(long j) {
        return this._value ? 1 : 0;
    }

    public double asDouble(double d) {
        if (this._value) {
            return 1.0d;
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeBoolean(this._value);
    }

    public int hashCode() {
        return this._value ? 3 : 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && (obj instanceof BooleanNode) && this._value == ((BooleanNode) obj)._value) {
            return true;
        }
        return false;
    }
}

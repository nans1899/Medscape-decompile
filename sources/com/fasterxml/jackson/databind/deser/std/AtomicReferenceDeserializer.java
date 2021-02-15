package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDeserializer extends StdDeserializer<AtomicReference<?>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final JavaType _referencedType;
    protected final JsonDeserializer<?> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public AtomicReferenceDeserializer(JavaType javaType) {
        this(javaType, (TypeDeserializer) null, (JsonDeserializer<?>) null);
    }

    public AtomicReferenceDeserializer(JavaType javaType, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        super((Class<?>) AtomicReference.class);
        this._referencedType = javaType;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = typeDeserializer;
    }

    public AtomicReferenceDeserializer withResolved(TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) {
        if (jsonDeserializer == this._valueDeserializer && typeDeserializer == this._valueTypeDeserializer) {
            return this;
        }
        return new AtomicReferenceDeserializer(this._referencedType, typeDeserializer, jsonDeserializer);
    }

    public AtomicReference<?> getNullValue(DeserializationContext deserializationContext) {
        return new AtomicReference<>();
    }

    @Deprecated
    public AtomicReference<?> getNullValue() {
        return new AtomicReference<>();
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer jsonDeserializer;
        JsonDeserializer jsonDeserializer2 = this._valueDeserializer;
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer = deserializationContext.findContextualValueDeserializer(this._referencedType, beanProperty);
        } else {
            jsonDeserializer = deserializationContext.handleSecondaryContextualization(jsonDeserializer2, beanProperty, this._referencedType);
        }
        if (typeDeserializer != null) {
            typeDeserializer = typeDeserializer.forProperty(beanProperty);
        }
        return withResolved(typeDeserializer, jsonDeserializer);
    }

    public AtomicReference<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        return new AtomicReference<>(typeDeserializer == null ? this._valueDeserializer.deserialize(jsonParser, deserializationContext) : this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer));
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return getNullValue(deserializationContext);
        }
        if (currentToken == null || !currentToken.isScalarValue()) {
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
        return deserialize(jsonParser, deserializationContext);
    }
}

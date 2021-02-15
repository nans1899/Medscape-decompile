package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;

public class NullifyingDeserializer extends StdDeserializer<Object> {
    public static final NullifyingDeserializer instance = new NullifyingDeserializer();
    private static final long serialVersionUID = 1;

    public NullifyingDeserializer() {
        super((Class<?>) Object.class);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.FIELD_NAME)) {
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == null || nextToken == JsonToken.END_OBJECT) {
                    return null;
                }
                jsonParser.skipChildren();
            }
        } else {
            jsonParser.skipChildren();
            return null;
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        int currentTokenId = jsonParser.getCurrentTokenId();
        if (currentTokenId == 1 || currentTokenId == 3 || currentTokenId == 5) {
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
        return null;
    }
}

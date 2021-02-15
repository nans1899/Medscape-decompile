package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.CompactStringObjectMap;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

@JacksonStdImpl
public class EnumDeserializer extends StdScalarDeserializer<Object> {
    private static final long serialVersionUID = 1;
    protected final CompactStringObjectMap _enumLookup;
    protected Object[] _enumsByIndex;

    public boolean isCachable() {
        return true;
    }

    public EnumDeserializer(EnumResolver enumResolver) {
        super((Class<?>) enumResolver.getEnumClass());
        this._enumLookup = enumResolver.constructLookup();
        this._enumsByIndex = enumResolver.getRawEnums();
    }

    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        Class<?> rawParameterType = annotatedMethod.getRawParameterType(0);
        if (deserializationConfig.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember(), deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new FactoryBasedDeserializer(cls, annotatedMethod, rawParameterType);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
            String text = jsonParser.getText();
            Object find = this._enumLookup.find(text);
            return find == null ? _deserializeAltString(jsonParser, deserializationContext, text) : find;
        } else if (currentToken != JsonToken.VALUE_NUMBER_INT) {
            return _deserializeOther(jsonParser, deserializationContext);
        } else {
            int intValue = jsonParser.getIntValue();
            if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                _failOnNumber(deserializationContext, jsonParser, intValue);
            }
            if (intValue >= 0) {
                Object[] objArr = this._enumsByIndex;
                if (intValue <= objArr.length) {
                    return objArr[intValue];
                }
            }
            if (deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            Integer valueOf = Integer.valueOf(intValue);
            Class<?> _enumClass = _enumClass();
            StringBuilder sb = new StringBuilder();
            sb.append("index value outside legal index range [0..");
            sb.append(this._enumsByIndex.length - 1);
            sb.append("]");
            throw deserializationContext.weirdNumberException(valueOf, _enumClass, sb.toString());
        }
    }

    private final Object _deserializeAltString(JsonParser jsonParser, DeserializationContext deserializationContext, String str) throws IOException {
        String trim = str.trim();
        if (trim.length() != 0) {
            char charAt = trim.charAt(0);
            if (charAt >= '0' && charAt <= '9') {
                try {
                    int parseInt = Integer.parseInt(trim);
                    if (deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                        _failOnNumber(deserializationContext, jsonParser, parseInt);
                    }
                    if (parseInt >= 0 && parseInt <= this._enumsByIndex.length) {
                        return this._enumsByIndex[parseInt];
                    }
                } catch (NumberFormatException unused) {
                }
            }
        } else if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
            return null;
        }
        if (deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            return null;
        }
        Class<?> _enumClass = _enumClass();
        throw deserializationContext.weirdStringException(trim, _enumClass, "value not one of declared Enum instance names: " + this._enumLookup.keys());
    }

    /* access modifiers changed from: protected */
    public Object _deserializeOther(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        jsonParser.getCurrentToken();
        if (!deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS) || !jsonParser.isExpectedStartArrayToken()) {
            throw deserializationContext.mappingException(_enumClass());
        }
        jsonParser.nextToken();
        Object deserialize = deserialize(jsonParser, deserializationContext);
        if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
            return deserialize;
        }
        JsonToken jsonToken = JsonToken.END_ARRAY;
        throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "Attempted to unwrap single value array for single '" + _enumClass().getName() + "' value but there was more than a single value in the array");
    }

    /* access modifiers changed from: protected */
    public void _failOnNumber(DeserializationContext deserializationContext, JsonParser jsonParser, int i) throws IOException {
        throw InvalidFormatException.from(jsonParser, String.format("Not allowed to deserialize Enum value out of JSON number (%d): disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow", new Object[]{Integer.valueOf(i)}), Integer.valueOf(i), _enumClass());
    }

    /* access modifiers changed from: protected */
    public Class<?> _enumClass() {
        return handledType();
    }

    protected static class FactoryBasedDeserializer extends StdDeserializer<Object> implements ContextualDeserializer {
        private static final long serialVersionUID = 1;
        protected final JsonDeserializer<?> _deser;
        protected final Method _factory;
        protected final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod, Class<?> cls2) {
            super(cls);
            this._factory = annotatedMethod.getAnnotated();
            this._inputType = cls2;
            this._deser = null;
        }

        protected FactoryBasedDeserializer(FactoryBasedDeserializer factoryBasedDeserializer, JsonDeserializer<?> jsonDeserializer) {
            super((Class<?>) factoryBasedDeserializer._valueClass);
            this._inputType = factoryBasedDeserializer._inputType;
            this._factory = factoryBasedDeserializer._factory;
            this._deser = jsonDeserializer;
        }

        public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
            return (this._deser != null || this._inputType == String.class) ? this : new FactoryBasedDeserializer(this, deserializationContext.findContextualValueDeserializer(deserializationContext.constructType(this._inputType), beanProperty));
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            Object obj;
            JsonDeserializer<?> jsonDeserializer = this._deser;
            if (jsonDeserializer != null) {
                obj = jsonDeserializer.deserialize(jsonParser, deserializationContext);
            } else {
                JsonToken currentToken = jsonParser.getCurrentToken();
                if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
                    obj = jsonParser.getText();
                } else {
                    obj = jsonParser.getValueAsString();
                }
            }
            try {
                return this._factory.invoke(this._valueClass, new Object[]{obj});
            } catch (Exception e) {
                Throwable rootCause = ClassUtil.getRootCause(e);
                if (rootCause instanceof IOException) {
                    throw ((IOException) rootCause);
                }
                throw deserializationContext.instantiationException((Class<?>) this._valueClass, rootCause);
            }
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            if (this._deser == null) {
                return deserialize(jsonParser, deserializationContext);
            }
            return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
        }
    }
}

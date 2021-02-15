package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;

public class NumberDeserializers {
    private static final HashSet<String> _classNames = new HashSet<>();

    static {
        Class[] clsArr = {Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, Number.class, BigDecimal.class, BigInteger.class};
        for (int i = 0; i < 11; i++) {
            _classNames.add(clsArr[i].getName());
        }
    }

    public static JsonDeserializer<?> find(Class<?> cls, String str) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return IntegerDeserializer.primitiveInstance;
            }
            if (cls == Boolean.TYPE) {
                return BooleanDeserializer.primitiveInstance;
            }
            if (cls == Long.TYPE) {
                return LongDeserializer.primitiveInstance;
            }
            if (cls == Double.TYPE) {
                return DoubleDeserializer.primitiveInstance;
            }
            if (cls == Character.TYPE) {
                return CharacterDeserializer.primitiveInstance;
            }
            if (cls == Byte.TYPE) {
                return ByteDeserializer.primitiveInstance;
            }
            if (cls == Short.TYPE) {
                return ShortDeserializer.primitiveInstance;
            }
            if (cls == Float.TYPE) {
                return FloatDeserializer.primitiveInstance;
            }
        } else if (!_classNames.contains(str)) {
            return null;
        } else {
            if (cls == Integer.class) {
                return IntegerDeserializer.wrapperInstance;
            }
            if (cls == Boolean.class) {
                return BooleanDeserializer.wrapperInstance;
            }
            if (cls == Long.class) {
                return LongDeserializer.wrapperInstance;
            }
            if (cls == Double.class) {
                return DoubleDeserializer.wrapperInstance;
            }
            if (cls == Character.class) {
                return CharacterDeserializer.wrapperInstance;
            }
            if (cls == Byte.class) {
                return ByteDeserializer.wrapperInstance;
            }
            if (cls == Short.class) {
                return ShortDeserializer.wrapperInstance;
            }
            if (cls == Float.class) {
                return FloatDeserializer.wrapperInstance;
            }
            if (cls == Number.class) {
                return NumberDeserializer.instance;
            }
            if (cls == BigDecimal.class) {
                return BigDecimalDeserializer.instance;
            }
            if (cls == BigInteger.class) {
                return BigIntegerDeserializer.instance;
            }
        }
        throw new IllegalArgumentException("Internal error: can't find deserializer for " + cls.getName());
    }

    protected static abstract class PrimitiveOrWrapperDeserializer<T> extends StdScalarDeserializer<T> {
        private static final long serialVersionUID = 1;
        protected final T _nullValue;
        protected final boolean _primitive;

        protected PrimitiveOrWrapperDeserializer(Class<T> cls, T t) {
            super((Class<?>) cls);
            this._nullValue = t;
            this._primitive = cls.isPrimitive();
        }

        public final T getNullValue(DeserializationContext deserializationContext) throws JsonMappingException {
            if (!this._primitive || !deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                return this._nullValue;
            }
            throw deserializationContext.mappingException("Can not map JSON null into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", handledType().toString());
        }

        @Deprecated
        public final T getNullValue() {
            return this._nullValue;
        }

        public T getEmptyValue(DeserializationContext deserializationContext) throws JsonMappingException {
            if (!this._primitive || !deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES)) {
                return this._nullValue;
            }
            throw deserializationContext.mappingException("Can not map Empty String as null into type %s (set DeserializationConfig.DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES to 'false' to allow)", handledType().toString());
        }
    }

    @JacksonStdImpl
    public static final class BooleanDeserializer extends PrimitiveOrWrapperDeserializer<Boolean> {
        static final BooleanDeserializer primitiveInstance = new BooleanDeserializer(Boolean.TYPE, Boolean.FALSE);
        private static final long serialVersionUID = 1;
        static final BooleanDeserializer wrapperInstance = new BooleanDeserializer(Boolean.class, (Boolean) null);

        public BooleanDeserializer(Class<Boolean> cls, Boolean bool) {
            super(cls, bool);
        }

        public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return _parseBoolean(jsonParser, deserializationContext);
        }

        public Boolean deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            return _parseBoolean(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class ByteDeserializer extends PrimitiveOrWrapperDeserializer<Byte> {
        static final ByteDeserializer primitiveInstance = new ByteDeserializer(Byte.TYPE, (byte) 0);
        private static final long serialVersionUID = 1;
        static final ByteDeserializer wrapperInstance = new ByteDeserializer(Byte.class, (Byte) null);

        public ByteDeserializer(Class<Byte> cls, Byte b) {
            super(cls, b);
        }

        public Byte deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return _parseByte(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class ShortDeserializer extends PrimitiveOrWrapperDeserializer<Short> {
        static final ShortDeserializer primitiveInstance = new ShortDeserializer(Short.TYPE, 0);
        private static final long serialVersionUID = 1;
        static final ShortDeserializer wrapperInstance = new ShortDeserializer(Short.class, (Short) null);

        public ShortDeserializer(Class<Short> cls, Short sh) {
            super(cls, sh);
        }

        public Short deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return _parseShort(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class CharacterDeserializer extends PrimitiveOrWrapperDeserializer<Character> {
        static final CharacterDeserializer primitiveInstance = new CharacterDeserializer(Character.TYPE, 0);
        private static final long serialVersionUID = 1;
        static final CharacterDeserializer wrapperInstance = new CharacterDeserializer(Character.class, (Character) null);

        public CharacterDeserializer(Class<Character> cls, Character ch) {
            super(cls, ch);
        }

        public Character deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            int intValue;
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId != 3) {
                if (currentTokenId == 6) {
                    String text = jsonParser.getText();
                    if (text.length() == 1) {
                        return Character.valueOf(text.charAt(0));
                    }
                    if (text.length() == 0) {
                        return (Character) getEmptyValue(deserializationContext);
                    }
                } else if (currentTokenId == 7 && (intValue = jsonParser.getIntValue()) >= 0 && intValue <= 65535) {
                    return Character.valueOf((char) intValue);
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Character deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                JsonToken jsonToken = JsonToken.END_ARRAY;
                throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException((Class<?>) this._valueClass, jsonParser.getCurrentToken());
        }
    }

    @JacksonStdImpl
    public static final class IntegerDeserializer extends PrimitiveOrWrapperDeserializer<Integer> {
        static final IntegerDeserializer primitiveInstance = new IntegerDeserializer(Integer.TYPE, 0);
        private static final long serialVersionUID = 1;
        static final IntegerDeserializer wrapperInstance = new IntegerDeserializer(Integer.class, (Integer) null);

        public boolean isCachable() {
            return true;
        }

        public IntegerDeserializer(Class<Integer> cls, Integer num) {
            super(cls, num);
        }

        public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            return _parseInteger(jsonParser, deserializationContext);
        }

        public Integer deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Integer.valueOf(jsonParser.getIntValue());
            }
            return _parseInteger(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static final class LongDeserializer extends PrimitiveOrWrapperDeserializer<Long> {
        static final LongDeserializer primitiveInstance = new LongDeserializer(Long.TYPE, 0L);
        private static final long serialVersionUID = 1;
        static final LongDeserializer wrapperInstance = new LongDeserializer(Long.class, (Long) null);

        public boolean isCachable() {
            return true;
        }

        public LongDeserializer(Class<Long> cls, Long l) {
            super(cls, l);
        }

        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (jsonParser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
                return Long.valueOf(jsonParser.getLongValue());
            }
            return _parseLong(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class FloatDeserializer extends PrimitiveOrWrapperDeserializer<Float> {
        static final FloatDeserializer primitiveInstance = new FloatDeserializer(Float.TYPE, Float.valueOf(0.0f));
        private static final long serialVersionUID = 1;
        static final FloatDeserializer wrapperInstance = new FloatDeserializer(Float.class, (Float) null);

        public FloatDeserializer(Class<Float> cls, Float f) {
            super(cls, f);
        }

        public Float deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return _parseFloat(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class DoubleDeserializer extends PrimitiveOrWrapperDeserializer<Double> {
        static final DoubleDeserializer primitiveInstance = new DoubleDeserializer(Double.TYPE, Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        private static final long serialVersionUID = 1;
        static final DoubleDeserializer wrapperInstance = new DoubleDeserializer(Double.class, (Double) null);

        public DoubleDeserializer(Class<Double> cls, Double d) {
            super(cls, d);
        }

        public Double deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return _parseDouble(jsonParser, deserializationContext);
        }

        public Double deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            return _parseDouble(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class NumberDeserializer extends StdScalarDeserializer<Object> {
        public static final NumberDeserializer instance = new NumberDeserializer();

        public NumberDeserializer() {
            super((Class<?>) Number.class);
        }

        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId != 3) {
                if (currentTokenId == 6) {
                    String trim = jsonParser.getText().trim();
                    if (trim.length() == 0) {
                        return getEmptyValue(deserializationContext);
                    }
                    if (_hasTextualNull(trim)) {
                        return getNullValue(deserializationContext);
                    }
                    if (_isPosInf(trim)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                    if (_isNegInf(trim)) {
                        return Double.valueOf(Double.NEGATIVE_INFINITY);
                    }
                    if (_isNaN(trim)) {
                        return Double.valueOf(Double.NaN);
                    }
                    try {
                        if (!_isIntNumber(trim)) {
                            if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                                return new BigDecimal(trim);
                            }
                            return new Double(trim);
                        } else if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                            return new BigInteger(trim);
                        } else {
                            long parseLong = Long.parseLong(trim);
                            if (deserializationContext.isEnabled(DeserializationFeature.USE_LONG_FOR_INTS) || parseLong > 2147483647L || parseLong < -2147483648L) {
                                return Long.valueOf(parseLong);
                            }
                            return Integer.valueOf((int) parseLong);
                        }
                    } catch (IllegalArgumentException unused) {
                        throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid number");
                    }
                } else if (currentTokenId != 7) {
                    if (currentTokenId == 8) {
                        if (deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                            return jsonParser.getDecimalValue();
                        }
                        return Double.valueOf(jsonParser.getDoubleValue());
                    }
                } else if (deserializationContext.hasSomeOfFeatures(F_MASK_INT_COERCIONS)) {
                    return _coerceIntegral(jsonParser, deserializationContext);
                } else {
                    return jsonParser.getNumberValue();
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                Object deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                JsonToken jsonToken = JsonToken.END_ARRAY;
                throw deserializationContext.wrongTokenException(jsonParser, jsonToken, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException((Class<?>) this._valueClass, jsonParser.getCurrentToken());
        }

        public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId == 6 || currentTokenId == 7 || currentTokenId == 8) {
                return deserialize(jsonParser, deserializationContext);
            }
            return typeDeserializer.deserializeTypedFromScalar(jsonParser, deserializationContext);
        }
    }

    @JacksonStdImpl
    public static class BigIntegerDeserializer extends StdScalarDeserializer<BigInteger> {
        public static final BigIntegerDeserializer instance = new BigIntegerDeserializer();

        public BigIntegerDeserializer() {
            super((Class<?>) BigInteger.class);
        }

        public BigInteger deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId != 3) {
                if (currentTokenId == 6) {
                    String trim = jsonParser.getText().trim();
                    if (trim.length() == 0) {
                        return null;
                    }
                    try {
                        return new BigInteger(trim);
                    } catch (IllegalArgumentException unused) {
                        throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid representation");
                    }
                } else if (currentTokenId == 7) {
                    int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType[jsonParser.getNumberType().ordinal()];
                    if (i == 1 || i == 2 || i == 3) {
                        return jsonParser.getBigIntegerValue();
                    }
                } else if (currentTokenId == 8) {
                    if (!deserializationContext.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                        _failDoubleToIntCoercion(jsonParser, deserializationContext, "java.math.BigInteger");
                    }
                    return jsonParser.getDecimalValue().toBigInteger();
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                BigInteger deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'BigInteger' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException((Class<?>) this._valueClass, jsonParser.getCurrentToken());
        }
    }

    /* renamed from: com.fasterxml.jackson.databind.deser.std.NumberDeserializers$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.fasterxml.jackson.core.JsonParser$NumberType[] r0 = com.fasterxml.jackson.core.JsonParser.NumberType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType = r0
                com.fasterxml.jackson.core.JsonParser$NumberType r1 = com.fasterxml.jackson.core.JsonParser.NumberType.INT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.core.JsonParser$NumberType r1 = com.fasterxml.jackson.core.JsonParser.NumberType.LONG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$core$JsonParser$NumberType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.core.JsonParser$NumberType r1 = com.fasterxml.jackson.core.JsonParser.NumberType.BIG_INTEGER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.NumberDeserializers.AnonymousClass1.<clinit>():void");
        }
    }

    @JacksonStdImpl
    public static class BigDecimalDeserializer extends StdScalarDeserializer<BigDecimal> {
        public static final BigDecimalDeserializer instance = new BigDecimalDeserializer();

        public BigDecimalDeserializer() {
            super((Class<?>) BigDecimal.class);
        }

        public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            int currentTokenId = jsonParser.getCurrentTokenId();
            if (currentTokenId != 3) {
                if (currentTokenId == 6) {
                    String trim = jsonParser.getText().trim();
                    if (trim.length() == 0) {
                        return null;
                    }
                    try {
                        return new BigDecimal(trim);
                    } catch (IllegalArgumentException unused) {
                        throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid representation");
                    }
                } else if (currentTokenId == 7 || currentTokenId == 8) {
                    return jsonParser.getDecimalValue();
                }
            } else if (deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                jsonParser.nextToken();
                BigDecimal deserialize = deserialize(jsonParser, deserializationContext);
                if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                    return deserialize;
                }
                throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single 'BigDecimal' value but there was more than a single value in the array");
            }
            throw deserializationContext.mappingException((Class<?>) this._valueClass, jsonParser.getCurrentToken());
        }
    }
}

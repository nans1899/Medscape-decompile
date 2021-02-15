package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> implements ContextualDeserializer {
    protected final Boolean _unwrapSingle;

    /* access modifiers changed from: protected */
    public abstract T handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    /* access modifiers changed from: protected */
    public abstract PrimitiveArrayDeserializers<?> withResolved(Boolean bool);

    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class<?>) cls);
        this._unwrapSingle = null;
    }

    protected PrimitiveArrayDeserializers(PrimitiveArrayDeserializers<?> primitiveArrayDeserializers, Boolean bool) {
        super((Class<?>) primitiveArrayDeserializers._valueClass);
        this._unwrapSingle = bool;
    }

    public static JsonDeserializer<?> forType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (cls == Long.TYPE) {
            return LongDeser.instance;
        }
        if (cls == Byte.TYPE) {
            return new ByteDeser();
        }
        if (cls == Short.TYPE) {
            return new ShortDeser();
        }
        if (cls == Float.TYPE) {
            return new FloatDeser();
        }
        if (cls == Double.TYPE) {
            return new DoubleDeser();
        }
        if (cls == Boolean.TYPE) {
            return new BooleanDeser();
        }
        if (cls == Character.TYPE) {
            return new CharDeser();
        }
        throw new IllegalStateException();
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        Boolean findFormatFeature = findFormatFeature(deserializationContext, beanProperty, this._valueClass, JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (findFormatFeature == this._unwrapSingle) {
            return this;
        }
        return withResolved(findFormatFeature);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public T handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.hasToken(JsonToken.VALUE_STRING) && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jsonParser.getText().length() == 0) {
            return null;
        }
        if (this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return handleSingleElementUnwrapped(jsonParser, deserializationContext);
        }
        throw deserializationContext.mappingException((Class<?>) this._valueClass);
    }

    @JacksonStdImpl
    static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        private static final long serialVersionUID = 1;

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return this;
        }

        public CharDeser() {
            super(char[].class);
        }

        protected CharDeser(CharDeser charDeser, Boolean bool) {
            super(charDeser, bool);
        }

        public char[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                char[] textCharacters = jsonParser.getTextCharacters();
                int textOffset = jsonParser.getTextOffset();
                int textLength = jsonParser.getTextLength();
                char[] cArr = new char[textLength];
                System.arraycopy(textCharacters, textOffset, cArr, 0, textLength);
                return cArr;
            } else if (jsonParser.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken nextToken = jsonParser.nextToken();
                    if (nextToken == JsonToken.END_ARRAY) {
                        return sb.toString().toCharArray();
                    }
                    if (nextToken == JsonToken.VALUE_STRING) {
                        String text = jsonParser.getText();
                        if (text.length() == 1) {
                            sb.append(text.charAt(0));
                        } else {
                            throw JsonMappingException.from(jsonParser, "Can not convert a JSON String of length " + text.length() + " into a char element of char array");
                        }
                    } else {
                        throw deserializationContext.mappingException((Class<?>) Character.TYPE);
                    }
                }
            } else {
                if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object embeddedObject = jsonParser.getEmbeddedObject();
                    if (embeddedObject == null) {
                        return null;
                    }
                    if (embeddedObject instanceof char[]) {
                        return (char[]) embeddedObject;
                    }
                    if (embeddedObject instanceof String) {
                        return ((String) embeddedObject).toCharArray();
                    }
                    if (embeddedObject instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) embeddedObject, false).toCharArray();
                    }
                }
                throw deserializationContext.mappingException((Class<?>) this._valueClass);
            }
        }

        /* access modifiers changed from: protected */
        public char[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            throw deserializationContext.mappingException((Class<?>) this._valueClass);
        }
    }

    @JacksonStdImpl
    static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        private static final long serialVersionUID = 1;

        public BooleanDeser() {
            super(boolean[].class);
        }

        protected BooleanDeser(BooleanDeser booleanDeser, Boolean bool) {
            super(booleanDeser, bool);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new BooleanDeser(this, bool);
        }

        public boolean[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (boolean[]) handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.BooleanBuilder booleanBuilder = deserializationContext.getArrayBuilders().getBooleanBuilder();
            boolean[] zArr = (boolean[]) booleanBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    boolean _parseBooleanPrimitive = _parseBooleanPrimitive(jsonParser, deserializationContext);
                    if (i >= zArr.length) {
                        zArr = (boolean[]) booleanBuilder.appendCompletedChunk(zArr, i);
                        i = 0;
                    }
                    int i2 = i + 1;
                    try {
                        zArr[i] = _parseBooleanPrimitive;
                        i = i2;
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        throw JsonMappingException.wrapWithPath((Throwable) e, (Object) zArr, booleanBuilder.bufferedSize() + i);
                    }
                } catch (Exception e2) {
                    e = e2;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) zArr, booleanBuilder.bufferedSize() + i);
                }
            }
            return (boolean[]) booleanBuilder.completeAndClearBuffer(zArr, i);
        }

        /* access modifiers changed from: protected */
        public boolean[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new boolean[]{_parseBooleanPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        private static final long serialVersionUID = 1;

        public ByteDeser() {
            super(byte[].class);
        }

        protected ByteDeser(ByteDeser byteDeser, Boolean bool) {
            super(byteDeser, bool);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new ByteDeser(this, bool);
        }

        /* JADX WARNING: Removed duplicated region for block: B:34:0x006c A[Catch:{ Exception -> 0x0084 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public byte[] deserialize(com.fasterxml.jackson.core.JsonParser r7, com.fasterxml.jackson.databind.DeserializationContext r8) throws java.io.IOException {
            /*
                r6 = this;
                com.fasterxml.jackson.core.JsonToken r0 = r7.getCurrentToken()
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING
                if (r0 != r1) goto L_0x0011
                com.fasterxml.jackson.core.Base64Variant r8 = r8.getBase64Variant()
                byte[] r7 = r7.getBinaryValue(r8)
                return r7
            L_0x0011:
                com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_EMBEDDED_OBJECT
                if (r0 != r1) goto L_0x0026
                java.lang.Object r0 = r7.getEmbeddedObject()
                if (r0 != 0) goto L_0x001d
                r7 = 0
                return r7
            L_0x001d:
                boolean r1 = r0 instanceof byte[]
                if (r1 == 0) goto L_0x0026
                byte[] r0 = (byte[]) r0
                byte[] r0 = (byte[]) r0
                return r0
            L_0x0026:
                boolean r0 = r7.isExpectedStartArrayToken()
                if (r0 != 0) goto L_0x0033
                java.lang.Object r7 = r6.handleNonArray(r7, r8)
                byte[] r7 = (byte[]) r7
                return r7
            L_0x0033:
                com.fasterxml.jackson.databind.util.ArrayBuilders r0 = r8.getArrayBuilders()
                com.fasterxml.jackson.databind.util.ArrayBuilders$ByteBuilder r0 = r0.getByteBuilder()
                java.lang.Object r1 = r0.resetAndStart()
                byte[] r1 = (byte[]) r1
                r2 = 0
                r3 = 0
            L_0x0043:
                com.fasterxml.jackson.core.JsonToken r4 = r7.nextToken()     // Catch:{ Exception -> 0x0084 }
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.END_ARRAY     // Catch:{ Exception -> 0x0084 }
                if (r4 == r5) goto L_0x007d
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT     // Catch:{ Exception -> 0x0084 }
                if (r4 == r5) goto L_0x0065
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT     // Catch:{ Exception -> 0x0084 }
                if (r4 != r5) goto L_0x0054
                goto L_0x0065
            L_0x0054:
                com.fasterxml.jackson.core.JsonToken r5 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL     // Catch:{ Exception -> 0x0084 }
                if (r4 != r5) goto L_0x005a
                r4 = 0
                goto L_0x0069
            L_0x005a:
                java.lang.Class r7 = r6._valueClass     // Catch:{ Exception -> 0x0084 }
                java.lang.Class r7 = r7.getComponentType()     // Catch:{ Exception -> 0x0084 }
                com.fasterxml.jackson.databind.JsonMappingException r7 = r8.mappingException((java.lang.Class<?>) r7)     // Catch:{ Exception -> 0x0084 }
                throw r7     // Catch:{ Exception -> 0x0084 }
            L_0x0065:
                byte r4 = r7.getByteValue()     // Catch:{ Exception -> 0x0084 }
            L_0x0069:
                int r5 = r1.length     // Catch:{ Exception -> 0x0084 }
                if (r3 < r5) goto L_0x0074
                java.lang.Object r5 = r0.appendCompletedChunk(r1, r3)     // Catch:{ Exception -> 0x0084 }
                byte[] r5 = (byte[]) r5     // Catch:{ Exception -> 0x0084 }
                r1 = r5
                r3 = 0
            L_0x0074:
                int r5 = r3 + 1
                r1[r3] = r4     // Catch:{ Exception -> 0x007a }
                r3 = r5
                goto L_0x0043
            L_0x007a:
                r7 = move-exception
                r3 = r5
                goto L_0x0085
            L_0x007d:
                java.lang.Object r7 = r0.completeAndClearBuffer(r1, r3)
                byte[] r7 = (byte[]) r7
                return r7
            L_0x0084:
                r7 = move-exception
            L_0x0085:
                int r8 = r0.bufferedSize()
                int r8 = r8 + r3
                com.fasterxml.jackson.databind.JsonMappingException r7 = com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath((java.lang.Throwable) r7, (java.lang.Object) r1, (int) r8)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers.ByteDeser.deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext):byte[]");
        }

        /* access modifiers changed from: protected */
        public byte[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            byte b;
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT || currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                b = jsonParser.getByteValue();
            } else if (currentToken == JsonToken.VALUE_NULL) {
                b = 0;
            } else {
                throw deserializationContext.mappingException(this._valueClass.getComponentType());
            }
            return new byte[]{b};
        }
    }

    @JacksonStdImpl
    static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        private static final long serialVersionUID = 1;

        public ShortDeser() {
            super(short[].class);
        }

        protected ShortDeser(ShortDeser shortDeser, Boolean bool) {
            super(shortDeser, bool);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new ShortDeser(this, bool);
        }

        public short[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (short[]) handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.ShortBuilder shortBuilder = deserializationContext.getArrayBuilders().getShortBuilder();
            short[] sArr = (short[]) shortBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    short _parseShortPrimitive = _parseShortPrimitive(jsonParser, deserializationContext);
                    if (i >= sArr.length) {
                        sArr = (short[]) shortBuilder.appendCompletedChunk(sArr, i);
                        i = 0;
                    }
                    int i2 = i + 1;
                    try {
                        sArr[i] = _parseShortPrimitive;
                        i = i2;
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        throw JsonMappingException.wrapWithPath((Throwable) e, (Object) sArr, shortBuilder.bufferedSize() + i);
                    }
                } catch (Exception e2) {
                    e = e2;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) sArr, shortBuilder.bufferedSize() + i);
                }
            }
            return (short[]) shortBuilder.completeAndClearBuffer(sArr, i);
        }

        /* access modifiers changed from: protected */
        public short[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new short[]{_parseShortPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser instance = new IntDeser();
        private static final long serialVersionUID = 1;

        public IntDeser() {
            super(int[].class);
        }

        protected IntDeser(IntDeser intDeser, Boolean bool) {
            super(intDeser, bool);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new IntDeser(this, bool);
        }

        public int[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (int[]) handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.IntBuilder intBuilder = deserializationContext.getArrayBuilders().getIntBuilder();
            int[] iArr = (int[]) intBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    int _parseIntPrimitive = _parseIntPrimitive(jsonParser, deserializationContext);
                    if (i >= iArr.length) {
                        iArr = (int[]) intBuilder.appendCompletedChunk(iArr, i);
                        i = 0;
                    }
                    int i2 = i + 1;
                    try {
                        iArr[i] = _parseIntPrimitive;
                        i = i2;
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        throw JsonMappingException.wrapWithPath((Throwable) e, (Object) iArr, intBuilder.bufferedSize() + i);
                    }
                } catch (Exception e2) {
                    e = e2;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) iArr, intBuilder.bufferedSize() + i);
                }
            }
            return (int[]) intBuilder.completeAndClearBuffer(iArr, i);
        }

        /* access modifiers changed from: protected */
        public int[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new int[]{_parseIntPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser instance = new LongDeser();
        private static final long serialVersionUID = 1;

        public LongDeser() {
            super(long[].class);
        }

        protected LongDeser(LongDeser longDeser, Boolean bool) {
            super(longDeser, bool);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new LongDeser(this, bool);
        }

        public long[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (long[]) handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.LongBuilder longBuilder = deserializationContext.getArrayBuilders().getLongBuilder();
            long[] jArr = (long[]) longBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    long _parseLongPrimitive = _parseLongPrimitive(jsonParser, deserializationContext);
                    if (i >= jArr.length) {
                        jArr = (long[]) longBuilder.appendCompletedChunk(jArr, i);
                        i = 0;
                    }
                    int i2 = i + 1;
                    try {
                        jArr[i] = _parseLongPrimitive;
                        i = i2;
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        throw JsonMappingException.wrapWithPath((Throwable) e, (Object) jArr, longBuilder.bufferedSize() + i);
                    }
                } catch (Exception e2) {
                    e = e2;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) jArr, longBuilder.bufferedSize() + i);
                }
            }
            return (long[]) longBuilder.completeAndClearBuffer(jArr, i);
        }

        /* access modifiers changed from: protected */
        public long[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new long[]{_parseLongPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        private static final long serialVersionUID = 1;

        public FloatDeser() {
            super(float[].class);
        }

        protected FloatDeser(FloatDeser floatDeser, Boolean bool) {
            super(floatDeser, bool);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new FloatDeser(this, bool);
        }

        public float[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (float[]) handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.FloatBuilder floatBuilder = deserializationContext.getArrayBuilders().getFloatBuilder();
            float[] fArr = (float[]) floatBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    float _parseFloatPrimitive = _parseFloatPrimitive(jsonParser, deserializationContext);
                    if (i >= fArr.length) {
                        fArr = (float[]) floatBuilder.appendCompletedChunk(fArr, i);
                        i = 0;
                    }
                    int i2 = i + 1;
                    try {
                        fArr[i] = _parseFloatPrimitive;
                        i = i2;
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        throw JsonMappingException.wrapWithPath((Throwable) e, (Object) fArr, floatBuilder.bufferedSize() + i);
                    }
                } catch (Exception e2) {
                    e = e2;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) fArr, floatBuilder.bufferedSize() + i);
                }
            }
            return (float[]) floatBuilder.completeAndClearBuffer(fArr, i);
        }

        /* access modifiers changed from: protected */
        public float[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new float[]{_parseFloatPrimitive(jsonParser, deserializationContext)};
        }
    }

    @JacksonStdImpl
    static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        private static final long serialVersionUID = 1;

        public DoubleDeser() {
            super(double[].class);
        }

        protected DoubleDeser(DoubleDeser doubleDeser, Boolean bool) {
            super(doubleDeser, bool);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean bool) {
            return new DoubleDeser(this, bool);
        }

        public double[] deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            if (!jsonParser.isExpectedStartArrayToken()) {
                return (double[]) handleNonArray(jsonParser, deserializationContext);
            }
            ArrayBuilders.DoubleBuilder doubleBuilder = deserializationContext.getArrayBuilders().getDoubleBuilder();
            double[] dArr = (double[]) doubleBuilder.resetAndStart();
            int i = 0;
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                try {
                    double _parseDoublePrimitive = _parseDoublePrimitive(jsonParser, deserializationContext);
                    if (i >= dArr.length) {
                        dArr = (double[]) doubleBuilder.appendCompletedChunk(dArr, i);
                        i = 0;
                    }
                    int i2 = i + 1;
                    try {
                        dArr[i] = _parseDoublePrimitive;
                        i = i2;
                    } catch (Exception e) {
                        e = e;
                        i = i2;
                        throw JsonMappingException.wrapWithPath((Throwable) e, (Object) dArr, doubleBuilder.bufferedSize() + i);
                    }
                } catch (Exception e2) {
                    e = e2;
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) dArr, doubleBuilder.bufferedSize() + i);
                }
            }
            return (double[]) doubleBuilder.completeAndClearBuffer(dArr, i);
        }

        /* access modifiers changed from: protected */
        public double[] handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return new double[]{_parseDoublePrimitive(jsonParser, deserializationContext)};
        }
    }
}

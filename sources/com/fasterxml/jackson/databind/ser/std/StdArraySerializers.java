package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class StdArraySerializers {
    protected static final HashMap<String, JsonSerializer<?>> _arraySerializers;

    static {
        HashMap<String, JsonSerializer<?>> hashMap = new HashMap<>();
        _arraySerializers = hashMap;
        hashMap.put(boolean[].class.getName(), new BooleanArraySerializer());
        _arraySerializers.put(byte[].class.getName(), new ByteArraySerializer());
        _arraySerializers.put(char[].class.getName(), new CharArraySerializer());
        _arraySerializers.put(short[].class.getName(), new ShortArraySerializer());
        _arraySerializers.put(int[].class.getName(), new IntArraySerializer());
        _arraySerializers.put(long[].class.getName(), new LongArraySerializer());
        _arraySerializers.put(float[].class.getName(), new FloatArraySerializer());
        _arraySerializers.put(double[].class.getName(), new DoubleArraySerializer());
    }

    protected StdArraySerializers() {
    }

    public static JsonSerializer<?> findStandardImpl(Class<?> cls) {
        return _arraySerializers.get(cls.getName());
    }

    protected static abstract class TypedPrimitiveArraySerializer<T> extends ArraySerializerBase<T> {
        protected final TypeSerializer _valueTypeSerializer;

        protected TypedPrimitiveArraySerializer(Class<T> cls) {
            super(cls);
            this._valueTypeSerializer = null;
        }

        protected TypedPrimitiveArraySerializer(TypedPrimitiveArraySerializer<T> typedPrimitiveArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, Boolean bool) {
            super(typedPrimitiveArraySerializer, beanProperty, bool);
            this._valueTypeSerializer = typeSerializer;
        }
    }

    @JacksonStdImpl
    public static class BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Boolean.class);

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public BooleanArraySerializer() {
            super(boolean[].class);
        }

        protected BooleanArraySerializer(BooleanArraySerializer booleanArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(booleanArraySerializer, beanProperty, bool);
        }

        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new BooleanArraySerializer(this, beanProperty, bool);
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public boolean isEmpty(SerializerProvider serializerProvider, boolean[] zArr) {
            return zArr == null || zArr.length == 0;
        }

        public boolean hasSingleElement(boolean[] zArr) {
            return zArr.length == 1;
        }

        public final void serialize(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = zArr.length;
            if (length != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
                jsonGenerator.writeStartArray(length);
                serializeContents(zArr, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndArray();
                return;
            }
            serializeContents(zArr, jsonGenerator, serializerProvider);
        }

        public void serializeContents(boolean[] zArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            for (boolean writeBoolean : zArr) {
                jsonGenerator.writeBoolean(writeBoolean);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            createSchemaNode.set(FirebaseAnalytics.Param.ITEMS, createSchemaNode("boolean"));
            return createSchemaNode;
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            visitArrayFormat(jsonFormatVisitorWrapper, javaType, JsonFormatTypes.BOOLEAN);
        }
    }

    @JacksonStdImpl
    public static class ShortArraySerializer extends TypedPrimitiveArraySerializer<short[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Short.TYPE);

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public ShortArraySerializer() {
            super(short[].class);
        }

        public ShortArraySerializer(ShortArraySerializer shortArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, Boolean bool) {
            super(shortArraySerializer, beanProperty, typeSerializer, bool);
        }

        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new ShortArraySerializer(this, beanProperty, this._valueTypeSerializer, bool);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new ShortArraySerializer(this, this._property, typeSerializer, this._unwrapSingle);
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public boolean isEmpty(SerializerProvider serializerProvider, short[] sArr) {
            return sArr == null || sArr.length == 0;
        }

        public boolean hasSingleElement(short[] sArr) {
            return sArr.length == 1;
        }

        public final void serialize(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = sArr.length;
            if (length != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
                jsonGenerator.writeStartArray(length);
                serializeContents(sArr, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndArray();
                return;
            }
            serializeContents(sArr, jsonGenerator, serializerProvider);
        }

        public void serializeContents(short[] sArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            int i = 0;
            if (this._valueTypeSerializer != null) {
                int length = sArr.length;
                while (i < length) {
                    this._valueTypeSerializer.writeTypePrefixForScalar((Object) null, jsonGenerator, Short.TYPE);
                    jsonGenerator.writeNumber(sArr[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar((Object) null, jsonGenerator);
                    i++;
                }
                return;
            }
            int length2 = sArr.length;
            while (i < length2) {
                jsonGenerator.writeNumber((int) sArr[i]);
                i++;
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("array", true).set(FirebaseAnalytics.Param.ITEMS, createSchemaNode("integer"));
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            visitArrayFormat(jsonFormatVisitorWrapper, javaType, JsonFormatTypes.INTEGER);
        }
    }

    @JacksonStdImpl
    public static class CharArraySerializer extends StdSerializer<char[]> {
        public CharArraySerializer() {
            super(char[].class);
        }

        public boolean isEmpty(SerializerProvider serializerProvider, char[] cArr) {
            return cArr == null || cArr.length == 0;
        }

        public void serialize(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                jsonGenerator.writeStartArray(cArr.length);
                _writeArrayContents(jsonGenerator, cArr);
                jsonGenerator.writeEndArray();
                return;
            }
            jsonGenerator.writeString(cArr, 0, cArr.length);
        }

        public void serializeWithType(char[] cArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
            if (serializerProvider.isEnabled(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS)) {
                typeSerializer.writeTypePrefixForArray(cArr, jsonGenerator);
                _writeArrayContents(jsonGenerator, cArr);
                typeSerializer.writeTypeSuffixForArray(cArr, jsonGenerator);
                return;
            }
            typeSerializer.writeTypePrefixForScalar(cArr, jsonGenerator);
            jsonGenerator.writeString(cArr, 0, cArr.length);
            typeSerializer.writeTypeSuffixForScalar(cArr, jsonGenerator);
        }

        private final void _writeArrayContents(JsonGenerator jsonGenerator, char[] cArr) throws IOException, JsonGenerationException {
            int length = cArr.length;
            for (int i = 0; i < length; i++) {
                jsonGenerator.writeString(cArr, i, 1);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            ObjectNode createSchemaNode = createSchemaNode("array", true);
            ObjectNode createSchemaNode2 = createSchemaNode("string");
            createSchemaNode2.put("type", "string");
            return createSchemaNode.set(FirebaseAnalytics.Param.ITEMS, createSchemaNode2);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            visitArrayFormat(jsonFormatVisitorWrapper, javaType, JsonFormatTypes.STRING);
        }
    }

    @JacksonStdImpl
    public static class IntArraySerializer extends ArraySerializerBase<int[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Integer.TYPE);

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public IntArraySerializer() {
            super(int[].class);
        }

        protected IntArraySerializer(IntArraySerializer intArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(intArraySerializer, beanProperty, bool);
        }

        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new IntArraySerializer(this, beanProperty, bool);
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public boolean isEmpty(SerializerProvider serializerProvider, int[] iArr) {
            return iArr == null || iArr.length == 0;
        }

        public boolean hasSingleElement(int[] iArr) {
            return iArr.length == 1;
        }

        public final void serialize(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = iArr.length;
            if (length != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
                jsonGenerator.writeStartArray(length);
                serializeContents(iArr, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndArray();
                return;
            }
            serializeContents(iArr, jsonGenerator, serializerProvider);
        }

        public void serializeContents(int[] iArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (int writeNumber : iArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("array", true).set(FirebaseAnalytics.Param.ITEMS, createSchemaNode("integer"));
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            visitArrayFormat(jsonFormatVisitorWrapper, javaType, JsonFormatTypes.INTEGER);
        }
    }

    @JacksonStdImpl
    public static class LongArraySerializer extends TypedPrimitiveArraySerializer<long[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Long.TYPE);

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public LongArraySerializer() {
            super(long[].class);
        }

        public LongArraySerializer(LongArraySerializer longArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, Boolean bool) {
            super(longArraySerializer, beanProperty, typeSerializer, bool);
        }

        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new LongArraySerializer(this, beanProperty, this._valueTypeSerializer, bool);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new LongArraySerializer(this, this._property, typeSerializer, this._unwrapSingle);
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public boolean isEmpty(SerializerProvider serializerProvider, long[] jArr) {
            return jArr == null || jArr.length == 0;
        }

        public boolean hasSingleElement(long[] jArr) {
            return jArr.length == 1;
        }

        public final void serialize(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = jArr.length;
            if (length != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
                jsonGenerator.writeStartArray(length);
                serializeContents(jArr, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndArray();
                return;
            }
            serializeContents(jArr, jsonGenerator, serializerProvider);
        }

        public void serializeContents(long[] jArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int i = 0;
            if (this._valueTypeSerializer != null) {
                int length = jArr.length;
                while (i < length) {
                    this._valueTypeSerializer.writeTypePrefixForScalar((Object) null, jsonGenerator, Long.TYPE);
                    jsonGenerator.writeNumber(jArr[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar((Object) null, jsonGenerator);
                    i++;
                }
                return;
            }
            int length2 = jArr.length;
            while (i < length2) {
                jsonGenerator.writeNumber(jArr[i]);
                i++;
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("array", true).set(FirebaseAnalytics.Param.ITEMS, createSchemaNode("number", true));
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            visitArrayFormat(jsonFormatVisitorWrapper, javaType, JsonFormatTypes.NUMBER);
        }
    }

    @JacksonStdImpl
    public static class FloatArraySerializer extends TypedPrimitiveArraySerializer<float[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Float.TYPE);

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public FloatArraySerializer() {
            super(float[].class);
        }

        public FloatArraySerializer(FloatArraySerializer floatArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, Boolean bool) {
            super(floatArraySerializer, beanProperty, typeSerializer, bool);
        }

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return new FloatArraySerializer(this, this._property, typeSerializer, this._unwrapSingle);
        }

        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new FloatArraySerializer(this, beanProperty, this._valueTypeSerializer, bool);
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public boolean isEmpty(SerializerProvider serializerProvider, float[] fArr) {
            return fArr == null || fArr.length == 0;
        }

        public boolean hasSingleElement(float[] fArr) {
            return fArr.length == 1;
        }

        public final void serialize(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = fArr.length;
            if (length != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
                jsonGenerator.writeStartArray(length);
                serializeContents(fArr, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndArray();
                return;
            }
            serializeContents(fArr, jsonGenerator, serializerProvider);
        }

        public void serializeContents(float[] fArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            int i = 0;
            if (this._valueTypeSerializer != null) {
                int length = fArr.length;
                while (i < length) {
                    this._valueTypeSerializer.writeTypePrefixForScalar((Object) null, jsonGenerator, Float.TYPE);
                    jsonGenerator.writeNumber(fArr[i]);
                    this._valueTypeSerializer.writeTypeSuffixForScalar((Object) null, jsonGenerator);
                    i++;
                }
                return;
            }
            int length2 = fArr.length;
            while (i < length2) {
                jsonGenerator.writeNumber(fArr[i]);
                i++;
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("array", true).set(FirebaseAnalytics.Param.ITEMS, createSchemaNode("number"));
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            visitArrayFormat(jsonFormatVisitorWrapper, javaType, JsonFormatTypes.NUMBER);
        }
    }

    @JacksonStdImpl
    public static class DoubleArraySerializer extends ArraySerializerBase<double[]> {
        private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(Double.TYPE);

        public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
            return this;
        }

        public JsonSerializer<?> getContentSerializer() {
            return null;
        }

        public DoubleArraySerializer() {
            super(double[].class);
        }

        protected DoubleArraySerializer(DoubleArraySerializer doubleArraySerializer, BeanProperty beanProperty, Boolean bool) {
            super(doubleArraySerializer, beanProperty, bool);
        }

        public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
            return new DoubleArraySerializer(this, beanProperty, bool);
        }

        public JavaType getContentType() {
            return VALUE_TYPE;
        }

        public boolean isEmpty(SerializerProvider serializerProvider, double[] dArr) {
            return dArr == null || dArr.length == 0;
        }

        public boolean hasSingleElement(double[] dArr) {
            return dArr.length == 1;
        }

        public final void serialize(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            int length = dArr.length;
            if (length != 1 || ((this._unwrapSingle != null || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) && this._unwrapSingle != Boolean.TRUE)) {
                jsonGenerator.writeStartArray(length);
                serializeContents(dArr, jsonGenerator, serializerProvider);
                jsonGenerator.writeEndArray();
                return;
            }
            serializeContents(dArr, jsonGenerator, serializerProvider);
        }

        public void serializeContents(double[] dArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            for (double writeNumber : dArr) {
                jsonGenerator.writeNumber(writeNumber);
            }
        }

        public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
            return createSchemaNode("array", true).set(FirebaseAnalytics.Param.ITEMS, createSchemaNode("number"));
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
            visitArrayFormat(jsonFormatVisitorWrapper, javaType, JsonFormatTypes.NUMBER);
        }
    }
}

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public abstract class StdSerializer<T> extends JsonSerializer<T> implements JsonFormatVisitable, SchemaAware, Serializable {
    private static final Object CONVERTING_CONTENT_CONVERTER_LOCK = new Object();
    private static final long serialVersionUID = 1;
    protected final Class<T> _handledType;

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    protected StdSerializer(Class<T> cls) {
        this._handledType = cls;
    }

    protected StdSerializer(JavaType javaType) {
        this._handledType = javaType.getRawClass();
    }

    protected StdSerializer(Class<?> cls, boolean z) {
        this._handledType = cls;
    }

    protected StdSerializer(StdSerializer<?> stdSerializer) {
        this._handledType = stdSerializer._handledType;
    }

    public Class<T> handledType() {
        return this._handledType;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        jsonFormatVisitorWrapper.expectAnyFormat(javaType);
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        return createSchemaNode("string");
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type, boolean z) throws JsonMappingException {
        ObjectNode objectNode = (ObjectNode) getSchema(serializerProvider, type);
        if (!z) {
            objectNode.put("required", !z);
        }
        return objectNode;
    }

    /* access modifiers changed from: protected */
    public ObjectNode createObjectNode() {
        return JsonNodeFactory.instance.objectNode();
    }

    /* access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String str) {
        ObjectNode createObjectNode = createObjectNode();
        createObjectNode.put("type", str);
        return createObjectNode;
    }

    /* access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String str, boolean z) {
        ObjectNode createSchemaNode = createSchemaNode(str);
        if (!z) {
            createSchemaNode.put("required", !z);
        }
        return createSchemaNode;
    }

    /* access modifiers changed from: protected */
    public void visitStringFormat(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        if (jsonFormatVisitorWrapper != null) {
            jsonFormatVisitorWrapper.expectStringFormat(javaType);
        }
    }

    /* access modifiers changed from: protected */
    public void visitStringFormat(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, JsonValueFormat jsonValueFormat) throws JsonMappingException {
        JsonStringFormatVisitor expectStringFormat;
        if (jsonFormatVisitorWrapper != null && (expectStringFormat = jsonFormatVisitorWrapper.expectStringFormat(javaType)) != null) {
            expectStringFormat.format(jsonValueFormat);
        }
    }

    /* access modifiers changed from: protected */
    public void visitIntFormat(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, JsonParser.NumberType numberType) throws JsonMappingException {
        JsonIntegerFormatVisitor expectIntegerFormat;
        if (jsonFormatVisitorWrapper != null && (expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType)) != null && numberType != null) {
            expectIntegerFormat.numberType(numberType);
        }
    }

    /* access modifiers changed from: protected */
    public void visitIntFormat(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, JsonParser.NumberType numberType, JsonValueFormat jsonValueFormat) throws JsonMappingException {
        JsonIntegerFormatVisitor expectIntegerFormat;
        if (jsonFormatVisitorWrapper != null && (expectIntegerFormat = jsonFormatVisitorWrapper.expectIntegerFormat(javaType)) != null) {
            if (numberType != null) {
                expectIntegerFormat.numberType(numberType);
            }
            if (jsonValueFormat != null) {
                expectIntegerFormat.format(jsonValueFormat);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void visitFloatFormat(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, JsonParser.NumberType numberType) throws JsonMappingException {
        JsonNumberFormatVisitor expectNumberFormat;
        if (jsonFormatVisitorWrapper != null && (expectNumberFormat = jsonFormatVisitorWrapper.expectNumberFormat(javaType)) != null) {
            expectNumberFormat.numberType(numberType);
        }
    }

    /* access modifiers changed from: protected */
    public void visitArrayFormat(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, JsonSerializer<?> jsonSerializer, JavaType javaType2) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat;
        if (jsonFormatVisitorWrapper != null && (expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType)) != null && jsonSerializer != null) {
            expectArrayFormat.itemsFormat(jsonSerializer, javaType2);
        }
    }

    /* access modifiers changed from: protected */
    public void visitArrayFormat(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType, JsonFormatTypes jsonFormatTypes) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat;
        if (jsonFormatVisitorWrapper != null && (expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType)) != null) {
            expectArrayFormat.itemsFormat(jsonFormatTypes);
        }
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, String str) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (!(th instanceof Error)) {
            boolean z = serializerProvider == null || serializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
            if (th instanceof IOException) {
                if (!z || !(th instanceof JsonMappingException)) {
                    throw ((IOException) th);
                }
            } else if (!z && (th instanceof RuntimeException)) {
                throw ((RuntimeException) th);
            }
            throw JsonMappingException.wrapWithPath(th, obj, str);
        }
        throw ((Error) th);
    }

    public void wrapAndThrow(SerializerProvider serializerProvider, Throwable th, Object obj, int i) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (!(th instanceof Error)) {
            boolean z = serializerProvider == null || serializerProvider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
            if (th instanceof IOException) {
                if (!z || !(th instanceof JsonMappingException)) {
                    throw ((IOException) th);
                }
            } else if (!z && (th instanceof RuntimeException)) {
                throw ((RuntimeException) th);
            }
            throw JsonMappingException.wrapWithPath(th, obj, i);
        }
        throw ((Error) th);
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultSerializer(JsonSerializer<?> jsonSerializer) {
        return ClassUtil.isJacksonStdImpl((Object) jsonSerializer);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findConvertingContentSerializer(SerializerProvider serializerProvider, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector;
        AnnotatedMember member;
        if (!(serializerProvider.getAttribute(CONVERTING_CONTENT_CONVERTER_LOCK) != null || (annotationIntrospector = serializerProvider.getAnnotationIntrospector()) == null || beanProperty == null || (member = beanProperty.getMember()) == null)) {
            serializerProvider.setAttribute(CONVERTING_CONTENT_CONVERTER_LOCK, (Object) Boolean.TRUE);
            try {
                Object findSerializationContentConverter = annotationIntrospector.findSerializationContentConverter(member);
                if (findSerializationContentConverter != null) {
                    Converter<Object, Object> converterInstance = serializerProvider.converterInstance(beanProperty.getMember(), findSerializationContentConverter);
                    JavaType outputType = converterInstance.getOutputType(serializerProvider.getTypeFactory());
                    JsonSerializer<Object> jsonSerializer2 = jsonSerializer;
                    if (jsonSerializer == null) {
                        jsonSerializer2 = jsonSerializer;
                        if (!outputType.isJavaLangObject()) {
                            jsonSerializer2 = serializerProvider.findValueSerializer(outputType);
                        }
                    }
                    return new StdDelegatingSerializer(converterInstance, outputType, jsonSerializer2);
                }
            } finally {
                serializerProvider.setAttribute(CONVERTING_CONTENT_CONVERTER_LOCK, (Object) null);
            }
        }
        return jsonSerializer;
    }

    /* access modifiers changed from: protected */
    public PropertyFilter findPropertyFilter(SerializerProvider serializerProvider, Object obj, Object obj2) throws JsonMappingException {
        FilterProvider filterProvider = serializerProvider.getFilterProvider();
        if (filterProvider != null) {
            return filterProvider.findPropertyFilter(obj, obj2);
        }
        throw JsonMappingException.from(serializerProvider, "Can not resolve PropertyFilter with id '" + obj + "'; no FilterProvider configured");
    }

    /* access modifiers changed from: protected */
    public JsonFormat.Value findFormatOverrides(SerializerProvider serializerProvider, BeanProperty beanProperty, Class<?> cls) {
        if (beanProperty != null) {
            return beanProperty.findPropertyFormat(serializerProvider.getConfig(), cls);
        }
        return serializerProvider.getDefaultPropertyFormat(cls);
    }

    /* access modifiers changed from: protected */
    public Boolean findFormatFeature(SerializerProvider serializerProvider, BeanProperty beanProperty, Class<?> cls, JsonFormat.Feature feature) {
        JsonFormat.Value findFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, cls);
        if (findFormatOverrides != null) {
            return findFormatOverrides.getFeature(feature);
        }
        return null;
    }
}

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>> extends StdSerializer<T> implements ContextualSerializer {
    protected final JsonSerializer<String> _serializer;
    protected final Boolean _unwrapSingle;

    public abstract JsonSerializer<?> _withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, Boolean bool);

    /* access modifiers changed from: protected */
    public abstract void acceptContentVisitor(JsonArrayFormatVisitor jsonArrayFormatVisitor) throws JsonMappingException;

    /* access modifiers changed from: protected */
    public abstract JsonNode contentSchema();

    protected StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
        this._serializer = null;
        this._unwrapSingle = null;
    }

    protected StaticListSerializerBase(StaticListSerializerBase<?> staticListSerializerBase, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super((StdSerializer<?>) staticListSerializerBase);
        this._serializer = jsonSerializer;
        this._unwrapSingle = bool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        r1 = r1.findContentSerializer(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r5, com.fasterxml.jackson.databind.BeanProperty r6) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r4 = this;
            r0 = 0
            if (r6 == 0) goto L_0x002e
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r5.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = r6.getMember()
            if (r2 == 0) goto L_0x0018
            java.lang.Object r1 = r1.findContentSerializer(r2)
            if (r1 == 0) goto L_0x0018
            com.fasterxml.jackson.databind.JsonSerializer r1 = r5.serializerInstance(r2, r1)
            goto L_0x0019
        L_0x0018:
            r1 = r0
        L_0x0019:
            com.fasterxml.jackson.databind.SerializationConfig r2 = r5.getConfig()
            java.lang.Class r3 = r4._handledType
            com.fasterxml.jackson.annotation.JsonFormat$Value r2 = r6.findPropertyFormat(r2, r3)
            if (r2 == 0) goto L_0x002c
            com.fasterxml.jackson.annotation.JsonFormat$Feature r3 = com.fasterxml.jackson.annotation.JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED
            java.lang.Boolean r2 = r2.getFeature(r3)
            goto L_0x0030
        L_0x002c:
            r2 = r0
            goto L_0x0030
        L_0x002e:
            r1 = r0
            r2 = r1
        L_0x0030:
            if (r1 != 0) goto L_0x0034
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.String> r1 = r4._serializer
        L_0x0034:
            com.fasterxml.jackson.databind.JsonSerializer r1 = r4.findConvertingContentSerializer(r5, r6, r1)
            if (r1 != 0) goto L_0x0041
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            com.fasterxml.jackson.databind.JsonSerializer r5 = r5.findValueSerializer((java.lang.Class<?>) r1, (com.fasterxml.jackson.databind.BeanProperty) r6)
            goto L_0x0045
        L_0x0041:
            com.fasterxml.jackson.databind.JsonSerializer r5 = r5.handleSecondaryContextualization(r1, r6)
        L_0x0045:
            boolean r1 = r4.isDefaultSerializer(r5)
            if (r1 == 0) goto L_0x004c
            goto L_0x004d
        L_0x004c:
            r0 = r5
        L_0x004d:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.String> r5 = r4._serializer
            if (r0 != r5) goto L_0x0056
            java.lang.Boolean r5 = r4._unwrapSingle
            if (r2 != r5) goto L_0x0056
            return r4
        L_0x0056:
            com.fasterxml.jackson.databind.JsonSerializer r5 = r4._withResolved(r6, r0, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    @Deprecated
    public boolean isEmpty(T t) {
        return isEmpty((SerializerProvider) null, t);
    }

    public boolean isEmpty(SerializerProvider serializerProvider, T t) {
        return t == null || t.size() == 0;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("array", true).set(FirebaseAnalytics.Param.ITEMS, contentSchema());
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        acceptContentVisitor(jsonFormatVisitorWrapper.expectArrayFormat(javaType));
    }
}

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;

@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer<Enum<?>> implements ContextualSerializer {
    private static final long serialVersionUID = 1;
    protected final Boolean _serializeAsIndex;
    protected final EnumValues _values;

    @Deprecated
    public EnumSerializer(EnumValues enumValues) {
        this(enumValues, (Boolean) null);
    }

    public EnumSerializer(EnumValues enumValues, Boolean bool) {
        super(enumValues.getEnumClass(), false);
        this._values = enumValues;
        this._serializeAsIndex = bool;
    }

    public static EnumSerializer construct(Class<?> cls, SerializationConfig serializationConfig, BeanDescription beanDescription, JsonFormat.Value value) {
        return new EnumSerializer(EnumValues.constructFromName(serializationConfig, cls), _isShapeWrittenUsingIndex(cls, value, true));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        r2 = _isShapeWrittenUsingIndex(r3.getType().getRawClass(), (r2 = r2.getAnnotationIntrospector().findFormat(r3.getMember())), false);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r2, com.fasterxml.jackson.databind.BeanProperty r3) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r1 = this;
            if (r3 == 0) goto L_0x0029
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r2.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r3.getMember()
            com.fasterxml.jackson.annotation.JsonFormat$Value r2 = r2.findFormat(r0)
            if (r2 == 0) goto L_0x0029
            com.fasterxml.jackson.databind.JavaType r3 = r3.getType()
            java.lang.Class r3 = r3.getRawClass()
            r0 = 0
            java.lang.Boolean r2 = _isShapeWrittenUsingIndex(r3, r2, r0)
            java.lang.Boolean r3 = r1._serializeAsIndex
            if (r2 == r3) goto L_0x0029
            com.fasterxml.jackson.databind.ser.std.EnumSerializer r3 = new com.fasterxml.jackson.databind.ser.std.EnumSerializer
            com.fasterxml.jackson.databind.util.EnumValues r0 = r1._values
            r3.<init>(r0, r2)
            return r3
        L_0x0029:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.EnumSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public EnumValues getEnumValues() {
        return this._values;
    }

    public final void serialize(Enum<?> enumR, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (_serializeAsIndex(serializerProvider)) {
            jsonGenerator.writeNumber(enumR.ordinal());
        } else if (serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
            jsonGenerator.writeString(enumR.toString());
        } else {
            jsonGenerator.writeString(this._values.serializedValueFor(enumR));
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        if (_serializeAsIndex(serializerProvider)) {
            return createSchemaNode("integer", true);
        }
        ObjectNode createSchemaNode = createSchemaNode("string", true);
        if (type != null && serializerProvider.constructType(type).isEnumType()) {
            ArrayNode putArray = createSchemaNode.putArray("enum");
            for (SerializableString value : this._values.values()) {
                putArray.add(value.getValue());
            }
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        SerializerProvider provider = jsonFormatVisitorWrapper.getProvider();
        if (_serializeAsIndex(provider)) {
            visitIntFormat(jsonFormatVisitorWrapper, javaType, JsonParser.NumberType.INT);
            return;
        }
        JsonStringFormatVisitor expectStringFormat = jsonFormatVisitorWrapper.expectStringFormat(javaType);
        if (expectStringFormat != null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            if (provider == null || !provider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                for (SerializableString value : this._values.values()) {
                    linkedHashSet.add(value.getValue());
                }
            } else {
                for (Enum<?> enumR : this._values.enums()) {
                    linkedHashSet.add(enumR.toString());
                }
            }
            expectStringFormat.enumTypes(linkedHashSet);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean _serializeAsIndex(SerializerProvider serializerProvider) {
        Boolean bool = this._serializeAsIndex;
        if (bool != null) {
            return bool.booleanValue();
        }
        return serializerProvider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class<?> cls, JsonFormat.Value value, boolean z) {
        JsonFormat.Shape shape = value == null ? null : value.getShape();
        if (shape == null || shape == JsonFormat.Shape.ANY || shape == JsonFormat.Shape.SCALAR) {
            return null;
        }
        if (shape == JsonFormat.Shape.STRING) {
            return Boolean.FALSE;
        }
        if (shape.isNumeric() || shape == JsonFormat.Shape.ARRAY) {
            return Boolean.TRUE;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unsupported serialization shape (");
        sb.append(shape);
        sb.append(") for Enum ");
        sb.append(cls.getName());
        sb.append(", not supported as ");
        sb.append(z ? Name.LABEL : "property");
        sb.append(" annotation");
        throw new IllegalArgumentException(sb.toString());
    }
}

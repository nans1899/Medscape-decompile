package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.medscape.android.slideshow.SlideshowPageFragment;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Iterator;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware {
    protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;
    protected final JsonFormat.Shape _serializationShape;
    protected final AnnotatedMember _typeId;

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase asArraySerializer();

    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    public abstract BeanSerializerBase withFilterId(Object obj);

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase withIgnorals(String[] strArr);

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter);

    protected BeanSerializerBase(JavaType javaType, BeanSerializerBuilder beanSerializerBuilder, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(javaType);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        JsonFormat.Shape shape = null;
        if (beanSerializerBuilder == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
            return;
        }
        this._typeId = beanSerializerBuilder.getTypeId();
        this._anyGetterWriter = beanSerializerBuilder.getAnyGetter();
        this._propertyFilterId = beanSerializerBuilder.getFilterId();
        this._objectIdWriter = beanSerializerBuilder.getObjectIdWriter();
        JsonFormat.Value findExpectedFormat = beanSerializerBuilder.getBeanDescription().findExpectedFormat((JsonFormat.Value) null);
        this._serializationShape = findExpectedFormat != null ? findExpectedFormat.getShape() : shape;
    }

    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(beanSerializerBase._handledType);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = beanSerializerBase._objectIdWriter;
        this._propertyFilterId = beanSerializerBase._propertyFilterId;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter) {
        this(beanSerializerBase, objectIdWriter, beanSerializerBase._propertyFilterId);
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter, Object obj) {
        super(beanSerializerBase._handledType);
        this._props = beanSerializerBase._props;
        this._filteredProps = beanSerializerBase._filteredProps;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = objectIdWriter;
        this._propertyFilterId = obj;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    /* JADX WARNING: type inference failed for: r11v12, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanSerializerBase(com.fasterxml.jackson.databind.ser.std.BeanSerializerBase r10, java.lang.String[] r11) {
        /*
            r9 = this;
            java.lang.Class r0 = r10._handledType
            r9.<init>(r0)
            java.util.HashSet r11 = com.fasterxml.jackson.databind.util.ArrayBuilders.arrayToSet(r11)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r0 = r10._props
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r1 = r10._filteredProps
            int r2 = r0.length
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r2)
            r4 = 0
            if (r1 != 0) goto L_0x0018
            r5 = r4
            goto L_0x001d
        L_0x0018:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r2)
        L_0x001d:
            r6 = 0
        L_0x001e:
            if (r6 >= r2) goto L_0x003a
            r7 = r0[r6]
            java.lang.String r8 = r7.getName()
            boolean r8 = r11.contains(r8)
            if (r8 == 0) goto L_0x002d
            goto L_0x0037
        L_0x002d:
            r3.add(r7)
            if (r1 == 0) goto L_0x0037
            r7 = r1[r6]
            r5.add(r7)
        L_0x0037:
            int r6 = r6 + 1
            goto L_0x001e
        L_0x003a:
            int r11 = r3.size()
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter[r11]
            java.lang.Object[] r11 = r3.toArray(r11)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter[]) r11
            r9._props = r11
            if (r5 != 0) goto L_0x004b
            goto L_0x0058
        L_0x004b:
            int r11 = r5.size()
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter[r11]
            java.lang.Object[] r11 = r5.toArray(r11)
            r4 = r11
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r4 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter[]) r4
        L_0x0058:
            r9._filteredProps = r4
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r11 = r10._typeId
            r9._typeId = r11
            com.fasterxml.jackson.databind.ser.AnyGetterWriter r11 = r10._anyGetterWriter
            r9._anyGetterWriter = r11
            com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r11 = r10._objectIdWriter
            r9._objectIdWriter = r11
            java.lang.Object r11 = r10._propertyFilterId
            r9._propertyFilterId = r11
            com.fasterxml.jackson.annotation.JsonFormat$Shape r10 = r10._serializationShape
            r9._serializationShape = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.<init>(com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, java.lang.String[]):void");
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase) {
        this(beanSerializerBase, beanSerializerBase._props, beanSerializerBase._filteredProps);
    }

    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, NameTransformer nameTransformer) {
        this(beanSerializerBase, rename(beanSerializerBase._props, nameTransformer), rename(beanSerializerBase._filteredProps, nameTransformer));
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] beanPropertyWriterArr, NameTransformer nameTransformer) {
        if (beanPropertyWriterArr == null || beanPropertyWriterArr.length == 0 || nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return beanPropertyWriterArr;
        }
        int length = beanPropertyWriterArr.length;
        BeanPropertyWriter[] beanPropertyWriterArr2 = new BeanPropertyWriter[length];
        for (int i = 0; i < length; i++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
            if (beanPropertyWriter != null) {
                beanPropertyWriterArr2[i] = beanPropertyWriter.rename(nameTransformer);
            }
        }
        return beanPropertyWriterArr2;
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter beanPropertyWriter;
        TypeSerializer typeSerializer;
        JsonSerializer<Object> findNullValueSerializer;
        BeanPropertyWriter beanPropertyWriter2;
        BeanPropertyWriter[] beanPropertyWriterArr = this._filteredProps;
        int length = beanPropertyWriterArr == null ? 0 : beanPropertyWriterArr.length;
        int length2 = this._props.length;
        for (int i = 0; i < length2; i++) {
            BeanPropertyWriter beanPropertyWriter3 = this._props[i];
            if (!beanPropertyWriter3.willSuppressNulls() && !beanPropertyWriter3.hasNullSerializer() && (findNullValueSerializer = serializerProvider.findNullValueSerializer(beanPropertyWriter3)) != null) {
                beanPropertyWriter3.assignNullSerializer(findNullValueSerializer);
                if (i < length && (beanPropertyWriter2 = this._filteredProps[i]) != null) {
                    beanPropertyWriter2.assignNullSerializer(findNullValueSerializer);
                }
            }
            if (!beanPropertyWriter3.hasSerializer()) {
                JsonSerializer<Object> findConvertingSerializer = findConvertingSerializer(serializerProvider, beanPropertyWriter3);
                if (findConvertingSerializer == null) {
                    JavaType serializationType = beanPropertyWriter3.getSerializationType();
                    if (serializationType == null) {
                        serializationType = beanPropertyWriter3.getType();
                        if (!serializationType.isFinal()) {
                            if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                                beanPropertyWriter3.setNonTrivialBaseType(serializationType);
                            }
                        }
                    }
                    JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(serializationType, (BeanProperty) beanPropertyWriter3);
                    findConvertingSerializer = (!serializationType.isContainerType() || (typeSerializer = (TypeSerializer) serializationType.getContentType().getTypeHandler()) == null || !(findValueSerializer instanceof ContainerSerializer)) ? findValueSerializer : ((ContainerSerializer) findValueSerializer).withValueTypeSerializer(typeSerializer);
                }
                beanPropertyWriter3.assignSerializer(findConvertingSerializer);
                if (i < length && (beanPropertyWriter = this._filteredProps[i]) != null) {
                    beanPropertyWriter.assignSerializer(findConvertingSerializer);
                }
            }
        }
        AnyGetterWriter anyGetterWriter = this._anyGetterWriter;
        if (anyGetterWriter != null) {
            anyGetterWriter.resolve(serializerProvider);
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> findConvertingSerializer(SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws JsonMappingException {
        AnnotatedMember member;
        Object findSerializationConverter;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        JsonSerializer<Object> jsonSerializer = null;
        if (annotationIntrospector == null || (member = beanPropertyWriter.getMember()) == null || (findSerializationConverter = annotationIntrospector.findSerializationConverter(member)) == null) {
            return null;
        }
        Converter<Object, Object> converterInstance = serializerProvider.converterInstance(beanPropertyWriter.getMember(), findSerializationConverter);
        JavaType outputType = converterInstance.getOutputType(serializerProvider.getTypeFactory());
        if (!outputType.isJavaLangObject()) {
            jsonSerializer = serializerProvider.findValueSerializer(outputType, (BeanProperty) beanPropertyWriter);
        }
        return new StdDelegatingSerializer(converterInstance, outputType, jsonSerializer);
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Shape shape;
        Object obj;
        ObjectIdWriter withSerializer;
        Object obj2;
        JsonFormat.Value findFormat;
        int i;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        String[] strArr = null;
        AnnotatedMember member = (beanProperty == null || annotationIntrospector == null) ? null : beanProperty.getMember();
        SerializationConfig config = serializerProvider.getConfig();
        if (member == null || (findFormat = annotationIntrospector.findFormat(member)) == null) {
            shape = null;
        } else {
            shape = findFormat.getShape();
            if (shape != this._serializationShape && this._handledType.isEnum() && ((i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[shape.ordinal()]) == 1 || i == 2 || i == 3)) {
                return serializerProvider.handlePrimaryContextualization(EnumSerializer.construct(this._handledType, serializerProvider.getConfig(), config.introspectClassAnnotations((Class<?>) this._handledType), findFormat), beanProperty);
            }
        }
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        if (member != null) {
            String[] findPropertiesToIgnore = annotationIntrospector.findPropertiesToIgnore(member, true);
            ObjectIdInfo findObjectIdInfo = annotationIntrospector.findObjectIdInfo(member);
            if (findObjectIdInfo != null) {
                ObjectIdInfo findObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(member, findObjectIdInfo);
                Class<? extends ObjectIdGenerator<?>> generatorType = findObjectReferenceInfo.getGeneratorType();
                JavaType javaType = serializerProvider.getTypeFactory().findTypeParameters(serializerProvider.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                    String simpleName = findObjectReferenceInfo.getPropertyName().getSimpleName();
                    int length = this._props.length;
                    int i2 = 0;
                    while (i2 != length) {
                        BeanPropertyWriter beanPropertyWriter = this._props[i2];
                        if (simpleName.equals(beanPropertyWriter.getName())) {
                            if (i2 > 0) {
                                BeanPropertyWriter[] beanPropertyWriterArr = this._props;
                                System.arraycopy(beanPropertyWriterArr, 0, beanPropertyWriterArr, 1, i2);
                                this._props[0] = beanPropertyWriter;
                                BeanPropertyWriter[] beanPropertyWriterArr2 = this._filteredProps;
                                if (beanPropertyWriterArr2 != null) {
                                    BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriterArr2[i2];
                                    System.arraycopy(beanPropertyWriterArr2, 0, beanPropertyWriterArr2, 1, i2);
                                    this._filteredProps[0] = beanPropertyWriter2;
                                }
                            }
                            objectIdWriter = ObjectIdWriter.construct(beanPropertyWriter.getType(), (PropertyName) null, (ObjectIdGenerator<?>) new PropertyBasedObjectIdGenerator(findObjectReferenceInfo, beanPropertyWriter), findObjectReferenceInfo.getAlwaysAsId());
                        } else {
                            i2++;
                        }
                    }
                    throw new IllegalArgumentException("Invalid Object Id definition for " + this._handledType.getName() + ": can not find property with name '" + simpleName + "'");
                }
                objectIdWriter = ObjectIdWriter.construct(javaType, findObjectReferenceInfo.getPropertyName(), serializerProvider.objectIdGeneratorInstance(member, findObjectReferenceInfo), findObjectReferenceInfo.getAlwaysAsId());
            } else if (objectIdWriter != null) {
                objectIdWriter = this._objectIdWriter.withAlwaysAsId(annotationIntrospector.findObjectReferenceInfo(member, new ObjectIdInfo(NAME_FOR_OBJECT_REF, (Class<?>) null, (Class<? extends ObjectIdGenerator<?>>) null, (Class<? extends ObjectIdResolver>) null)).getAlwaysAsId());
            }
            obj = annotationIntrospector.findFilterId(member);
            if (obj == null || ((obj2 = this._propertyFilterId) != null && obj.equals(obj2))) {
                obj = null;
            }
            strArr = findPropertiesToIgnore;
        } else {
            obj = null;
        }
        BeanSerializerBase withObjectIdWriter = (objectIdWriter == null || (withSerializer = objectIdWriter.withSerializer(serializerProvider.findValueSerializer(objectIdWriter.idType, beanProperty))) == this._objectIdWriter) ? this : withObjectIdWriter(withSerializer);
        if (!(strArr == null || strArr.length == 0)) {
            withObjectIdWriter = withObjectIdWriter.withIgnorals(strArr);
        }
        if (obj != null) {
            withObjectIdWriter = withObjectIdWriter.withFilterId(obj);
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        return shape == JsonFormat.Shape.ARRAY ? withObjectIdWriter.asArraySerializer() : withObjectIdWriter;
    }

    /* renamed from: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.fasterxml.jackson.annotation.JsonFormat$Shape[] r0 = com.fasterxml.jackson.annotation.JsonFormat.Shape.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape = r0
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.NUMBER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.NUMBER_INT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.AnonymousClass1.<clinit>():void");
        }
    }

    public Iterator<PropertyWriter> properties() {
        return Arrays.asList(this._props).iterator();
    }

    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        if (this._objectIdWriter != null) {
            jsonGenerator.setCurrentValue(obj);
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, typeSerializer);
            return;
        }
        String _customTypeId = this._typeId == null ? null : _customTypeId(obj);
        if (_customTypeId == null) {
            typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
        } else {
            typeSerializer.writeCustomTypePrefixForObject(obj, jsonGenerator, _customTypeId);
        }
        jsonGenerator.setCurrentValue(obj);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        if (_customTypeId == null) {
            typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
        } else {
            typeSerializer.writeCustomTypeSuffixForObject(obj, jsonGenerator, _customTypeId);
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, boolean z) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
                return;
            }
            if (z) {
                jsonGenerator.writeStartObject();
            }
            findObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
            } else {
                serializeFields(obj, jsonGenerator, serializerProvider);
            }
            if (z) {
                jsonGenerator.writeEndObject();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
            } else {
                _serializeObjectId(obj, jsonGenerator, serializerProvider, typeSerializer, findObjectId);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _serializeObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer, WritableObjectId writableObjectId) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        String _customTypeId = this._typeId == null ? null : _customTypeId(obj);
        if (_customTypeId == null) {
            typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
        } else {
            typeSerializer.writeCustomTypePrefixForObject(obj, jsonGenerator, _customTypeId);
        }
        writableObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        if (_customTypeId == null) {
            typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
        } else {
            typeSerializer.writeCustomTypeSuffixForObject(obj, jsonGenerator, _customTypeId);
        }
    }

    /* access modifiers changed from: protected */
    public final String _customTypeId(Object obj) {
        Object value = this._typeId.getValue(obj);
        if (value == null) {
            return "";
        }
        return value instanceof String ? (String) value : value.toString();
    }

    /* access modifiers changed from: protected */
    public void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        String str = "[anySetter]";
        if (this._filteredProps == null || serializerProvider.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        try {
            for (BeanPropertyWriter beanPropertyWriter : beanPropertyWriterArr) {
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
                }
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
            }
        } catch (Exception e) {
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            wrapAndThrow(serializerProvider, (Throwable) e, obj, str);
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException((Closeable) jsonGenerator, "Infinite recursion (StackOverflowError)", (Throwable) e2);
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, str));
            throw jsonMappingException;
        }
    }

    /* access modifiers changed from: protected */
    public void serializeFieldsFiltered(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        String str = "[anySetter]";
        if (this._filteredProps == null || serializerProvider.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        PropertyFilter findPropertyFilter = findPropertyFilter(serializerProvider, this._propertyFilterId, obj);
        if (findPropertyFilter == null) {
            serializeFields(obj, jsonGenerator, serializerProvider);
            return;
        }
        try {
            for (BeanPropertyWriter beanPropertyWriter : beanPropertyWriterArr) {
                if (beanPropertyWriter != null) {
                    findPropertyFilter.serializeAsField(obj, jsonGenerator, serializerProvider, beanPropertyWriter);
                }
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndFilter(obj, jsonGenerator, serializerProvider, findPropertyFilter);
            }
        } catch (Exception e) {
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            wrapAndThrow(serializerProvider, (Throwable) e, obj, str);
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException((Closeable) jsonGenerator, "Infinite recursion (StackOverflowError)", (Throwable) e2);
            if (0 != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[0].getName();
            }
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, str));
            throw jsonMappingException;
        }
    }

    @Deprecated
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        String id;
        ObjectNode createSchemaNode = createSchemaNode(SlideshowPageFragment.ARG_OBJECT, true);
        JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (!(jsonSerializableSchema == null || (id = jsonSerializableSchema.id()) == null || id.length() <= 0)) {
            createSchemaNode.put("id", id);
        }
        ObjectNode objectNode = createSchemaNode.objectNode();
        Object obj = this._propertyFilterId;
        PropertyFilter propertyFilter = null;
        if (obj != null) {
            propertyFilter = findPropertyFilter(serializerProvider, obj, (Object) null);
        }
        int i = 0;
        while (true) {
            BeanPropertyWriter[] beanPropertyWriterArr = this._props;
            if (i < beanPropertyWriterArr.length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (propertyFilter == null) {
                    beanPropertyWriter.depositSchemaProperty(objectNode, serializerProvider);
                } else {
                    propertyFilter.depositSchemaProperty((PropertyWriter) beanPropertyWriter, objectNode, serializerProvider);
                }
                i++;
            } else {
                createSchemaNode.set("properties", objectNode);
                return createSchemaNode;
            }
        }
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonObjectFormatVisitor expectObjectFormat;
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (jsonFormatVisitorWrapper != null && (expectObjectFormat = jsonFormatVisitorWrapper.expectObjectFormat(javaType)) != null) {
            SerializerProvider provider = jsonFormatVisitorWrapper.getProvider();
            int i = 0;
            Class<?> cls = null;
            if (this._propertyFilterId != null) {
                PropertyFilter findPropertyFilter = findPropertyFilter(jsonFormatVisitorWrapper.getProvider(), this._propertyFilterId, (Object) null);
                int length = this._props.length;
                while (i < length) {
                    findPropertyFilter.depositSchemaProperty((PropertyWriter) this._props[i], expectObjectFormat, provider);
                    i++;
                }
                return;
            }
            if (!(this._filteredProps == null || provider == null)) {
                cls = provider.getActiveView();
            }
            if (cls != null) {
                beanPropertyWriterArr = this._filteredProps;
            } else {
                beanPropertyWriterArr = this._props;
            }
            int length2 = beanPropertyWriterArr.length;
            while (i < length2) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.depositSchemaProperty(expectObjectFormat, provider);
                }
                i++;
            }
        }
    }
}

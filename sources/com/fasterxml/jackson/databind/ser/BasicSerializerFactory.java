package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.ByteBufferSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.CollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSetSerializer;
import com.fasterxml.jackson.databind.ser.std.InetAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.InetSocketAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.IterableSerializer;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.RandomAccess;
import java.util.TimeZone;

public abstract class BasicSerializerFactory extends SerializerFactory implements Serializable {
    protected static final HashMap<String, JsonSerializer<?>> _concrete;
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy;
    protected final SerializerFactoryConfig _factoryConfig;

    public abstract JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType) throws JsonMappingException;

    /* access modifiers changed from: protected */
    public abstract Iterable<Serializers> customSerializers();

    public abstract SerializerFactory withConfig(SerializerFactoryConfig serializerFactoryConfig);

    static {
        HashMap<String, Class<? extends JsonSerializer<?>>> hashMap = new HashMap<>();
        HashMap<String, JsonSerializer<?>> hashMap2 = new HashMap<>();
        hashMap2.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        hashMap2.put(StringBuffer.class.getName(), toStringSerializer);
        hashMap2.put(StringBuilder.class.getName(), toStringSerializer);
        hashMap2.put(Character.class.getName(), toStringSerializer);
        hashMap2.put(Character.TYPE.getName(), toStringSerializer);
        NumberSerializers.addAll(hashMap2);
        hashMap2.put(Boolean.TYPE.getName(), new BooleanSerializer(true));
        hashMap2.put(Boolean.class.getName(), new BooleanSerializer(false));
        hashMap2.put(BigInteger.class.getName(), new NumberSerializer(BigInteger.class));
        hashMap2.put(BigDecimal.class.getName(), new NumberSerializer(BigDecimal.class));
        hashMap2.put(Calendar.class.getName(), CalendarSerializer.instance);
        hashMap2.put(Date.class.getName(), DateSerializer.instance);
        for (Map.Entry next : StdJdkSerializers.all()) {
            Object value = next.getValue();
            if (value instanceof JsonSerializer) {
                hashMap2.put(((Class) next.getKey()).getName(), (JsonSerializer) value);
            } else if (value instanceof Class) {
                hashMap.put(((Class) next.getKey()).getName(), (Class) value);
            } else {
                throw new IllegalStateException("Internal error: unrecognized value of type " + next.getClass().getName());
            }
        }
        hashMap.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
        _concrete = hashMap2;
        _concreteLazy = hashMap;
    }

    protected BasicSerializerFactory(SerializerFactoryConfig serializerFactoryConfig) {
        this._factoryConfig = serializerFactoryConfig == null ? new SerializerFactoryConfig() : serializerFactoryConfig;
    }

    public SerializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public final SerializerFactory withAdditionalSerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalSerializers(serializers));
    }

    public final SerializerFactory withAdditionalKeySerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalKeySerializers(serializers));
    }

    public final SerializerFactory withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        return withConfig(this._factoryConfig.withSerializerModifier(beanSerializerModifier));
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x001b A[LOOP:0: B:3:0x001b->B:6:0x002b, LOOP_START, PHI: r2 
      PHI: (r2v11 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r2v0 com.fasterxml.jackson.databind.JsonSerializer<?>), (r2v14 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:2:0x0011, B:6:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> createKeySerializer(com.fasterxml.jackson.databind.SerializationConfig r5, com.fasterxml.jackson.databind.JavaType r6, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r7) {
        /*
            r4 = this;
            java.lang.Class r0 = r6.getRawClass()
            com.fasterxml.jackson.databind.BeanDescription r0 = r5.introspectClassAnnotations((java.lang.Class<?>) r0)
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r4._factoryConfig
            boolean r1 = r1.hasKeySerializers()
            r2 = 0
            if (r1 == 0) goto L_0x002d
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r4._factoryConfig
            java.lang.Iterable r1 = r1.keySerializers()
            java.util.Iterator r1 = r1.iterator()
        L_0x001b:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x002d
            java.lang.Object r2 = r1.next()
            com.fasterxml.jackson.databind.ser.Serializers r2 = (com.fasterxml.jackson.databind.ser.Serializers) r2
            com.fasterxml.jackson.databind.JsonSerializer r2 = r2.findSerializer(r5, r6, r0)
            if (r2 == 0) goto L_0x001b
        L_0x002d:
            if (r2 != 0) goto L_0x0071
            if (r7 != 0) goto L_0x0072
            java.lang.Class r7 = r6.getRawClass()
            r1 = 0
            com.fasterxml.jackson.databind.JsonSerializer r7 = com.fasterxml.jackson.databind.ser.std.StdKeySerializers.getStdKeySerializer(r5, r7, r1)
            if (r7 != 0) goto L_0x0072
            com.fasterxml.jackson.databind.BeanDescription r0 = r5.introspect(r6)
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r7 = r0.findJsonValueMethod()
            if (r7 == 0) goto L_0x0068
            java.lang.Class r1 = r7.getRawReturnType()
            r2 = 1
            com.fasterxml.jackson.databind.JsonSerializer r1 = com.fasterxml.jackson.databind.ser.std.StdKeySerializers.getStdKeySerializer(r5, r1, r2)
            java.lang.reflect.Method r7 = r7.getAnnotated()
            boolean r2 = r5.canOverrideAccessModifiers()
            if (r2 == 0) goto L_0x0062
            com.fasterxml.jackson.databind.MapperFeature r2 = com.fasterxml.jackson.databind.MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS
            boolean r2 = r5.isEnabled(r2)
            com.fasterxml.jackson.databind.util.ClassUtil.checkAndFixAccess(r7, r2)
        L_0x0062:
            com.fasterxml.jackson.databind.ser.std.JsonValueSerializer r2 = new com.fasterxml.jackson.databind.ser.std.JsonValueSerializer
            r2.<init>(r7, r1)
            goto L_0x0071
        L_0x0068:
            java.lang.Class r7 = r6.getRawClass()
            com.fasterxml.jackson.databind.JsonSerializer r7 = com.fasterxml.jackson.databind.ser.std.StdKeySerializers.getFallbackKeySerializer(r5, r7)
            goto L_0x0072
        L_0x0071:
            r7 = r2
        L_0x0072:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r4._factoryConfig
            boolean r1 = r1.hasSerializerModifiers()
            if (r1 == 0) goto L_0x0095
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r4._factoryConfig
            java.lang.Iterable r1 = r1.serializerModifiers()
            java.util.Iterator r1 = r1.iterator()
        L_0x0084:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0095
            java.lang.Object r2 = r1.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r2 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r2
            com.fasterxml.jackson.databind.JsonSerializer r7 = r2.modifyKeySerializer(r5, r6, r0, r7)
            goto L_0x0084
        L_0x0095:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.createKeySerializer(com.fasterxml.jackson.databind.SerializationConfig, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        Collection<NamedType> collection;
        AnnotatedClass classInfo = serializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder<?> findTypeResolver = serializationConfig.getAnnotationIntrospector().findTypeResolver(serializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = serializationConfig.getDefaultTyper(javaType);
            collection = null;
        } else {
            collection = serializationConfig.getSubtypeResolver().collectAndResolveSubtypesByClass(serializationConfig, classInfo);
        }
        if (findTypeResolver == null) {
            return null;
        }
        return findTypeResolver.buildTypeSerializer(serializationConfig, javaType, collection);
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByLookup(JavaType javaType, SerializationConfig serializationConfig, BeanDescription beanDescription, boolean z) {
        Class cls;
        String name = javaType.getRawClass().getName();
        JsonSerializer<?> jsonSerializer = _concrete.get(name);
        if (jsonSerializer != null || (cls = _concreteLazy.get(name)) == null) {
            return jsonSerializer;
        }
        try {
            return (JsonSerializer) cls.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to instantiate standard serializer (of type " + cls.getName() + "): " + e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByAnnotations(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        if (JsonSerializable.class.isAssignableFrom(javaType.getRawClass())) {
            return SerializableSerializer.instance;
        }
        AnnotatedMethod findJsonValueMethod = beanDescription.findJsonValueMethod();
        if (findJsonValueMethod == null) {
            return null;
        }
        Method annotated = findJsonValueMethod.getAnnotated();
        if (serializerProvider.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotated, serializerProvider.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new JsonValueSerializer(annotated, findSerializerFromAnnotation(serializerProvider, findJsonValueMethod));
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByPrimaryType(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        JsonSerializer<?> findOptionalStdSerializer = findOptionalStdSerializer(serializerProvider, javaType, beanDescription, z);
        if (findOptionalStdSerializer != null) {
            return findOptionalStdSerializer;
        }
        if (Calendar.class.isAssignableFrom(rawClass)) {
            return CalendarSerializer.instance;
        }
        if (Date.class.isAssignableFrom(rawClass)) {
            return DateSerializer.instance;
        }
        if (Map.Entry.class.isAssignableFrom(rawClass)) {
            JavaType findSuperType = javaType.findSuperType(Map.Entry.class);
            JavaType containedType = findSuperType.containedType(0);
            if (containedType == null) {
                containedType = TypeFactory.unknownType();
            }
            JavaType javaType2 = containedType;
            JavaType containedType2 = findSuperType.containedType(1);
            if (containedType2 == null) {
                containedType2 = TypeFactory.unknownType();
            }
            return buildMapEntrySerializer(serializerProvider.getConfig(), javaType, beanDescription, z, javaType2, containedType2);
        } else if (ByteBuffer.class.isAssignableFrom(rawClass)) {
            return new ByteBufferSerializer();
        } else {
            if (InetAddress.class.isAssignableFrom(rawClass)) {
                return new InetAddressSerializer();
            }
            if (InetSocketAddress.class.isAssignableFrom(rawClass)) {
                return new InetSocketAddressSerializer();
            }
            if (TimeZone.class.isAssignableFrom(rawClass)) {
                return new TimeZoneSerializer();
            }
            if (Charset.class.isAssignableFrom(rawClass)) {
                return ToStringSerializer.instance;
            }
            if (Number.class.isAssignableFrom(rawClass)) {
                JsonFormat.Value findExpectedFormat = beanDescription.findExpectedFormat((JsonFormat.Value) null);
                if (findExpectedFormat != null) {
                    int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[findExpectedFormat.getShape().ordinal()];
                    if (i == 1) {
                        return ToStringSerializer.instance;
                    }
                    if (i == 2 || i == 3) {
                        return null;
                    }
                }
                return NumberSerializer.instance;
            } else if (Enum.class.isAssignableFrom(rawClass)) {
                return buildEnumSerializer(serializerProvider.getConfig(), javaType, beanDescription);
            } else {
                return null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findOptionalStdSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findSerializer(serializerProvider.getConfig(), javaType, beanDescription);
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (Iterator.class.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters = serializationConfig.getTypeFactory().findTypeParameters(javaType, (Class<?>) Iterator.class);
            return buildIteratorSerializer(serializationConfig, javaType, beanDescription, z, (findTypeParameters == null || findTypeParameters.length != 1) ? TypeFactory.unknownType() : findTypeParameters[0]);
        } else if (Iterable.class.isAssignableFrom(rawClass)) {
            JavaType[] findTypeParameters2 = serializationConfig.getTypeFactory().findTypeParameters(javaType, (Class<?>) Iterable.class);
            return buildIterableSerializer(serializationConfig, javaType, beanDescription, z, (findTypeParameters2 == null || findTypeParameters2.length != 1) ? TypeFactory.unknownType() : findTypeParameters2[0]);
        } else if (CharSequence.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> findSerializerFromAnnotation(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findSerializer = serializerProvider.getAnnotationIntrospector().findSerializer(annotated);
        if (findSerializer == null) {
            return null;
        }
        return findConvertingSerializer(serializerProvider, annotated, serializerProvider.serializerInstance(annotated, findSerializer));
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findConvertingSerializer(SerializerProvider serializerProvider, Annotated annotated, JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        Converter<Object, Object> findConverter = findConverter(serializerProvider, annotated);
        if (findConverter == null) {
            return jsonSerializer;
        }
        return new StdDelegatingSerializer(findConverter, findConverter.getOutputType(serializerProvider.getTypeFactory()), jsonSerializer);
    }

    /* access modifiers changed from: protected */
    public Converter<Object, Object> findConverter(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findSerializationConverter = serializerProvider.getAnnotationIntrospector().findSerializationConverter(annotated);
        if (findSerializationConverter == null) {
            return null;
        }
        return serializerProvider.converterInstance(annotated, findSerializationConverter);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0070 A[LOOP:0: B:21:0x0070->B:24:0x008a, LOOP_START, PHI: r3 
      PHI: (r3v13 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r3v1 com.fasterxml.jackson.databind.JsonSerializer<?>), (r3v21 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:20:0x0068, B:24:0x008a] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildContainerSerializer(com.fasterxml.jackson.databind.SerializerProvider r19, com.fasterxml.jackson.databind.JavaType r20, com.fasterxml.jackson.databind.BeanDescription r21, boolean r22) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r18 = this;
            r8 = r18
            r1 = r19
            r7 = r21
            com.fasterxml.jackson.databind.SerializationConfig r0 = r19.getConfig()
            if (r22 != 0) goto L_0x0026
            boolean r2 = r20.useStaticType()
            if (r2 == 0) goto L_0x0026
            boolean r2 = r20.isContainerType()
            if (r2 == 0) goto L_0x0024
            com.fasterxml.jackson.databind.JavaType r2 = r20.getContentType()
            java.lang.Class r2 = r2.getRawClass()
            java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
            if (r2 == r3) goto L_0x0026
        L_0x0024:
            r2 = 1
            goto L_0x0028
        L_0x0026:
            r2 = r22
        L_0x0028:
            com.fasterxml.jackson.databind.JavaType r3 = r20.getContentType()
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r16 = r8.createTypeSerializer(r0, r3)
            if (r16 == 0) goto L_0x0035
            r2 = 0
            r4 = 0
            goto L_0x0036
        L_0x0035:
            r4 = r2
        L_0x0036:
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r2 = r21.getClassInfo()
            com.fasterxml.jackson.databind.JsonSerializer r17 = r8._findContentSerializer(r1, r2)
            boolean r2 = r20.isMapLikeType()
            r3 = 0
            if (r2 == 0) goto L_0x00b8
            r2 = r20
            com.fasterxml.jackson.databind.type.MapLikeType r2 = (com.fasterxml.jackson.databind.type.MapLikeType) r2
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r5 = r21.getClassInfo()
            com.fasterxml.jackson.databind.JsonSerializer r5 = r8._findKeySerializer(r1, r5)
            boolean r6 = r2.isTrueMapType()
            if (r6 == 0) goto L_0x0068
            com.fasterxml.jackson.databind.type.MapType r2 = (com.fasterxml.jackson.databind.type.MapType) r2
            r0 = r18
            r1 = r19
            r3 = r21
            r6 = r16
            r7 = r17
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.buildMapSerializer(r1, r2, r3, r4, r5, r6, r7)
            return r0
        L_0x0068:
            java.lang.Iterable r4 = r18.customSerializers()
            java.util.Iterator r4 = r4.iterator()
        L_0x0070:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x008c
            java.lang.Object r3 = r4.next()
            r9 = r3
            com.fasterxml.jackson.databind.ser.Serializers r9 = (com.fasterxml.jackson.databind.ser.Serializers) r9
            r10 = r0
            r11 = r2
            r12 = r21
            r13 = r5
            r14 = r16
            r15 = r17
            com.fasterxml.jackson.databind.JsonSerializer r3 = r9.findMapLikeSerializer(r10, r11, r12, r13, r14, r15)
            if (r3 == 0) goto L_0x0070
        L_0x008c:
            if (r3 != 0) goto L_0x0092
            com.fasterxml.jackson.databind.JsonSerializer r3 = r18.findSerializerByAnnotations(r19, r20, r21)
        L_0x0092:
            if (r3 == 0) goto L_0x00b7
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r8._factoryConfig
            boolean r1 = r1.hasSerializerModifiers()
            if (r1 == 0) goto L_0x00b7
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r8._factoryConfig
            java.lang.Iterable r1 = r1.serializerModifiers()
            java.util.Iterator r1 = r1.iterator()
        L_0x00a6:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x00b7
            java.lang.Object r4 = r1.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r4 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r4
            com.fasterxml.jackson.databind.JsonSerializer r3 = r4.modifyMapLikeSerializer(r0, r2, r7, r3)
            goto L_0x00a6
        L_0x00b7:
            return r3
        L_0x00b8:
            boolean r2 = r20.isCollectionLikeType()
            if (r2 == 0) goto L_0x012d
            r9 = r20
            com.fasterxml.jackson.databind.type.CollectionLikeType r9 = (com.fasterxml.jackson.databind.type.CollectionLikeType) r9
            boolean r2 = r9.isTrueCollectionType()
            if (r2 == 0) goto L_0x00da
            r2 = r9
            com.fasterxml.jackson.databind.type.CollectionType r2 = (com.fasterxml.jackson.databind.type.CollectionType) r2
            r0 = r18
            r1 = r19
            r3 = r21
            r5 = r16
            r6 = r17
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.buildCollectionSerializer(r1, r2, r3, r4, r5, r6)
            return r0
        L_0x00da:
            java.lang.Iterable r2 = r18.customSerializers()
            java.util.Iterator r10 = r2.iterator()
        L_0x00e2:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x0100
            java.lang.Object r2 = r10.next()
            com.fasterxml.jackson.databind.ser.Serializers r2 = (com.fasterxml.jackson.databind.ser.Serializers) r2
            r3 = r0
            r4 = r9
            r5 = r21
            r6 = r16
            r11 = r7
            r7 = r17
            com.fasterxml.jackson.databind.JsonSerializer r3 = r2.findCollectionLikeSerializer(r3, r4, r5, r6, r7)
            if (r3 == 0) goto L_0x00fe
            goto L_0x0101
        L_0x00fe:
            r7 = r11
            goto L_0x00e2
        L_0x0100:
            r11 = r7
        L_0x0101:
            if (r3 != 0) goto L_0x0107
            com.fasterxml.jackson.databind.JsonSerializer r3 = r18.findSerializerByAnnotations(r19, r20, r21)
        L_0x0107:
            if (r3 == 0) goto L_0x012c
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r8._factoryConfig
            boolean r1 = r1.hasSerializerModifiers()
            if (r1 == 0) goto L_0x012c
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r8._factoryConfig
            java.lang.Iterable r1 = r1.serializerModifiers()
            java.util.Iterator r1 = r1.iterator()
        L_0x011b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x012c
            java.lang.Object r2 = r1.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r2 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r2
            com.fasterxml.jackson.databind.JsonSerializer r3 = r2.modifyCollectionLikeSerializer(r0, r9, r11, r3)
            goto L_0x011b
        L_0x012c:
            return r3
        L_0x012d:
            r11 = r7
            boolean r0 = r20.isArrayType()
            if (r0 == 0) goto L_0x0147
            r2 = r20
            com.fasterxml.jackson.databind.type.ArrayType r2 = (com.fasterxml.jackson.databind.type.ArrayType) r2
            r0 = r18
            r1 = r19
            r3 = r21
            r5 = r16
            r6 = r17
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.buildArraySerializer(r1, r2, r3, r4, r5, r6)
            return r0
        L_0x0147:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildContainerSerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.BeanDescription, boolean):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* JADX WARNING: type inference failed for: r15v0, types: [com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object>, com.fasterxml.jackson.databind.JsonSerializer, java.lang.Object] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x000e A[LOOP:0: B:1:0x000e->B:4:0x0023, LOOP_START, PHI: r0 
      PHI: (r0v2 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r0v1 com.fasterxml.jackson.databind.JsonSerializer<?>), (r0v15 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:0:0x0000, B:4:0x0023] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0091  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildCollectionSerializer(com.fasterxml.jackson.databind.SerializerProvider r10, com.fasterxml.jackson.databind.type.CollectionType r11, com.fasterxml.jackson.databind.BeanDescription r12, boolean r13, com.fasterxml.jackson.databind.jsontype.TypeSerializer r14, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r15) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.SerializationConfig r6 = r10.getConfig()
            java.lang.Iterable r0 = r9.customSerializers()
            java.util.Iterator r7 = r0.iterator()
            r8 = 0
            r0 = r8
        L_0x000e:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0025
            java.lang.Object r0 = r7.next()
            com.fasterxml.jackson.databind.ser.Serializers r0 = (com.fasterxml.jackson.databind.ser.Serializers) r0
            r1 = r6
            r2 = r11
            r3 = r12
            r4 = r14
            r5 = r15
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.findCollectionSerializer(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x000e
        L_0x0025:
            if (r0 != 0) goto L_0x0099
            com.fasterxml.jackson.databind.JsonSerializer r0 = r9.findSerializerByAnnotations(r10, r11, r12)
            if (r0 != 0) goto L_0x0099
            com.fasterxml.jackson.annotation.JsonFormat$Value r10 = r12.findExpectedFormat(r8)
            if (r10 == 0) goto L_0x003c
            com.fasterxml.jackson.annotation.JsonFormat$Shape r10 = r10.getShape()
            com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT
            if (r10 != r1) goto L_0x003c
            return r8
        L_0x003c:
            java.lang.Class r10 = r11.getRawClass()
            java.lang.Class<java.util.EnumSet> r1 = java.util.EnumSet.class
            boolean r1 = r1.isAssignableFrom(r10)
            if (r1 == 0) goto L_0x0059
            com.fasterxml.jackson.databind.JavaType r10 = r11.getContentType()
            boolean r13 = r10.isEnumType()
            if (r13 != 0) goto L_0x0053
            goto L_0x0054
        L_0x0053:
            r8 = r10
        L_0x0054:
            com.fasterxml.jackson.databind.JsonSerializer r0 = r9.buildEnumSetSerializer(r8)
            goto L_0x0099
        L_0x0059:
            com.fasterxml.jackson.databind.JavaType r1 = r11.getContentType()
            java.lang.Class r1 = r1.getRawClass()
            boolean r10 = r9.isIndexedList(r10)
            if (r10 == 0) goto L_0x0080
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            if (r1 != r10) goto L_0x0076
            if (r15 == 0) goto L_0x0073
            boolean r10 = com.fasterxml.jackson.databind.util.ClassUtil.isJacksonStdImpl((java.lang.Object) r15)
            if (r10 == 0) goto L_0x008f
        L_0x0073:
            com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer r10 = com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer.instance
            goto L_0x007e
        L_0x0076:
            com.fasterxml.jackson.databind.JavaType r10 = r11.getContentType()
            com.fasterxml.jackson.databind.ser.ContainerSerializer r10 = r9.buildIndexedListSerializer(r10, r13, r14, r15)
        L_0x007e:
            r0 = r10
            goto L_0x008f
        L_0x0080:
            java.lang.Class<java.lang.String> r10 = java.lang.String.class
            if (r1 != r10) goto L_0x008f
            if (r15 == 0) goto L_0x008c
            boolean r10 = com.fasterxml.jackson.databind.util.ClassUtil.isJacksonStdImpl((java.lang.Object) r15)
            if (r10 == 0) goto L_0x008f
        L_0x008c:
            com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer r10 = com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer.instance
            goto L_0x007e
        L_0x008f:
            if (r0 != 0) goto L_0x0099
            com.fasterxml.jackson.databind.JavaType r10 = r11.getContentType()
            com.fasterxml.jackson.databind.ser.ContainerSerializer r0 = r9.buildCollectionSerializer(r10, r13, r14, r15)
        L_0x0099:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r10 = r9._factoryConfig
            boolean r10 = r10.hasSerializerModifiers()
            if (r10 == 0) goto L_0x00bc
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r10 = r9._factoryConfig
            java.lang.Iterable r10 = r10.serializerModifiers()
            java.util.Iterator r10 = r10.iterator()
        L_0x00ab:
            boolean r13 = r10.hasNext()
            if (r13 == 0) goto L_0x00bc
            java.lang.Object r13 = r10.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r13 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r13
            com.fasterxml.jackson.databind.JsonSerializer r0 = r13.modifyCollectionSerializer(r6, r11, r12, r0)
            goto L_0x00ab
        L_0x00bc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildCollectionSerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.type.CollectionType, com.fasterxml.jackson.databind.BeanDescription, boolean, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* access modifiers changed from: protected */
    public boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    public ContainerSerializer<?> buildIndexedListSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return new IndexedListSerializer(javaType, z, typeSerializer, jsonSerializer);
    }

    public ContainerSerializer<?> buildCollectionSerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        return new CollectionSerializer(javaType, z, typeSerializer, jsonSerializer);
    }

    public JsonSerializer<?> buildEnumSetSerializer(JavaType javaType) {
        return new EnumSetSerializer(javaType);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildMapSerializer(com.fasterxml.jackson.databind.SerializerProvider r18, com.fasterxml.jackson.databind.type.MapType r19, com.fasterxml.jackson.databind.BeanDescription r20, boolean r21, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r22, com.fasterxml.jackson.databind.jsontype.TypeSerializer r23, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r24) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r17 = this;
            r0 = r17
            r8 = r20
            com.fasterxml.jackson.databind.SerializationConfig r9 = r18.getConfig()
            java.lang.Iterable r1 = r17.customSerializers()
            java.util.Iterator r10 = r1.iterator()
            r1 = 0
        L_0x0011:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x002e
            java.lang.Object r1 = r10.next()
            com.fasterxml.jackson.databind.ser.Serializers r1 = (com.fasterxml.jackson.databind.ser.Serializers) r1
            r2 = r9
            r3 = r19
            r4 = r20
            r5 = r22
            r6 = r23
            r7 = r24
            com.fasterxml.jackson.databind.JsonSerializer r1 = r1.findMapSerializer(r2, r3, r4, r5, r6, r7)
            if (r1 == 0) goto L_0x0011
        L_0x002e:
            if (r1 != 0) goto L_0x0063
            com.fasterxml.jackson.databind.JsonSerializer r1 = r17.findSerializerByAnnotations(r18, r19, r20)
            if (r1 != 0) goto L_0x0063
            java.lang.Object r16 = r0.findFilterId(r9, r8)
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r9.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r2 = r20.getClassInfo()
            r3 = 1
            java.lang.String[] r10 = r1.findPropertiesToIgnore(r2, r3)
            r11 = r19
            r12 = r21
            r13 = r23
            r14 = r22
            r15 = r24
            com.fasterxml.jackson.databind.ser.std.MapSerializer r1 = com.fasterxml.jackson.databind.ser.std.MapSerializer.construct(r10, r11, r12, r13, r14, r15, r16)
            com.fasterxml.jackson.databind.JavaType r2 = r19.getContentType()
            java.lang.Object r2 = r0.findSuppressableContentValue(r9, r2, r8)
            if (r2 == 0) goto L_0x0063
            com.fasterxml.jackson.databind.ser.std.MapSerializer r1 = r1.withContentInclusion(r2)
        L_0x0063:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r2 = r0._factoryConfig
            boolean r2 = r2.hasSerializerModifiers()
            if (r2 == 0) goto L_0x0088
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r2 = r0._factoryConfig
            java.lang.Iterable r2 = r2.serializerModifiers()
            java.util.Iterator r2 = r2.iterator()
        L_0x0075:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0088
            java.lang.Object r3 = r2.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r3 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r3
            r4 = r19
            com.fasterxml.jackson.databind.JsonSerializer r1 = r3.modifyMapSerializer(r9, r4, r8, r1)
            goto L_0x0075
        L_0x0088:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildMapSerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.type.MapType, com.fasterxml.jackson.databind.BeanDescription, boolean, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* access modifiers changed from: protected */
    public Object findSuppressableContentValue(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonInclude.Value findPropertyInclusion = beanDescription.findPropertyInclusion(serializationConfig.getDefaultPropertyInclusion());
        if (findPropertyInclusion == null) {
            return null;
        }
        JsonInclude.Include contentInclusion = findPropertyInclusion.getContentInclusion();
        if (AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[contentInclusion.ordinal()] != 1) {
            return contentInclusion;
        }
        return null;
    }

    /* renamed from: com.fasterxml.jackson.databind.ser.BasicSerializerFactory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape;
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|(2:1|2)|3|(2:5|6)|7|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0038 */
        static {
            /*
                com.fasterxml.jackson.annotation.JsonInclude$Include[] r0 = com.fasterxml.jackson.annotation.JsonInclude.Include.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = r0
                r1 = 1
                com.fasterxml.jackson.annotation.JsonInclude$Include r2 = com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.JsonInclude$Include r3 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                com.fasterxml.jackson.annotation.JsonFormat$Shape[] r2 = com.fasterxml.jackson.annotation.JsonFormat.Shape.values()
                int r2 = r2.length
                int[] r2 = new int[r2]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape = r2
                com.fasterxml.jackson.annotation.JsonFormat$Shape r3 = com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING     // Catch:{ NoSuchFieldError -> 0x002e }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x002e }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x002e }
            L_0x002e:
                int[] r1 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x0038 }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r2 = com.fasterxml.jackson.annotation.JsonFormat.Shape.OBJECT     // Catch:{ NoSuchFieldError -> 0x0038 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0038 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0038 }
            L_0x0038:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.fasterxml.jackson.annotation.JsonFormat$Shape r1 = com.fasterxml.jackson.annotation.JsonFormat.Shape.ARRAY     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:1:0x000d A[LOOP:0: B:1:0x000d->B:4:0x0022, LOOP_START, PHI: r0 
      PHI: (r0v2 com.fasterxml.jackson.databind.JsonSerializer<?>) = (r0v1 com.fasterxml.jackson.databind.JsonSerializer<?>), (r0v15 com.fasterxml.jackson.databind.JsonSerializer<?>) binds: [B:0:0x0000, B:4:0x0022] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildArraySerializer(com.fasterxml.jackson.databind.SerializerProvider r8, com.fasterxml.jackson.databind.type.ArrayType r9, com.fasterxml.jackson.databind.BeanDescription r10, boolean r11, com.fasterxml.jackson.databind.jsontype.TypeSerializer r12, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r13) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r7 = this;
            com.fasterxml.jackson.databind.SerializationConfig r8 = r8.getConfig()
            java.lang.Iterable r0 = r7.customSerializers()
            java.util.Iterator r6 = r0.iterator()
            r0 = 0
        L_0x000d:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x0024
            java.lang.Object r0 = r6.next()
            com.fasterxml.jackson.databind.ser.Serializers r0 = (com.fasterxml.jackson.databind.ser.Serializers) r0
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r12
            r5 = r13
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.findArraySerializer(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x000d
        L_0x0024:
            if (r0 != 0) goto L_0x0048
            java.lang.Class r1 = r9.getRawClass()
            if (r13 == 0) goto L_0x0032
            boolean r2 = com.fasterxml.jackson.databind.util.ClassUtil.isJacksonStdImpl((java.lang.Object) r13)
            if (r2 == 0) goto L_0x003d
        L_0x0032:
            java.lang.Class<java.lang.String[]> r0 = java.lang.String[].class
            if (r0 != r1) goto L_0x0039
            com.fasterxml.jackson.databind.ser.impl.StringArraySerializer r0 = com.fasterxml.jackson.databind.ser.impl.StringArraySerializer.instance
            goto L_0x003d
        L_0x0039:
            com.fasterxml.jackson.databind.JsonSerializer r0 = com.fasterxml.jackson.databind.ser.std.StdArraySerializers.findStandardImpl(r1)
        L_0x003d:
            if (r0 != 0) goto L_0x0048
            com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer r0 = new com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer
            com.fasterxml.jackson.databind.JavaType r1 = r9.getContentType()
            r0.<init>(r1, r11, r12, r13)
        L_0x0048:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r11 = r7._factoryConfig
            boolean r11 = r11.hasSerializerModifiers()
            if (r11 == 0) goto L_0x006b
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r11 = r7._factoryConfig
            java.lang.Iterable r11 = r11.serializerModifiers()
            java.util.Iterator r11 = r11.iterator()
        L_0x005a:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x006b
            java.lang.Object r12 = r11.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r12 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r12
            com.fasterxml.jackson.databind.JsonSerializer r0 = r12.modifyArraySerializer(r8, r9, r10, r0)
            goto L_0x005a
        L_0x006b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildArraySerializer(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.type.ArrayType, com.fasterxml.jackson.databind.BeanDescription, boolean, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z, JavaType javaType2) throws JsonMappingException {
        return new IteratorSerializer(javaType2, z, createTypeSerializer(serializationConfig, javaType2));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        JavaType[] findTypeParameters = serializationConfig.getTypeFactory().findTypeParameters(javaType, (Class<?>) Iterator.class);
        return buildIteratorSerializer(serializationConfig, javaType, beanDescription, z, (findTypeParameters == null || findTypeParameters.length != 1) ? TypeFactory.unknownType() : findTypeParameters[0]);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z, JavaType javaType2) throws JsonMappingException {
        return new IterableSerializer(javaType2, z, createTypeSerializer(serializationConfig, javaType2));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        JavaType[] findTypeParameters = serializationConfig.getTypeFactory().findTypeParameters(javaType, (Class<?>) Iterable.class);
        return buildIterableSerializer(serializationConfig, javaType, beanDescription, z, (findTypeParameters == null || findTypeParameters.length != 1) ? TypeFactory.unknownType() : findTypeParameters[0]);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildMapEntrySerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z, JavaType javaType2, JavaType javaType3) throws JsonMappingException {
        return new MapEntrySerializer(javaType3, javaType2, javaType3, z, createTypeSerializer(serializationConfig, javaType3), (BeanProperty) null);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildEnumSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonFormat.Value findExpectedFormat = beanDescription.findExpectedFormat((JsonFormat.Value) null);
        if (findExpectedFormat == null || findExpectedFormat.getShape() != JsonFormat.Shape.OBJECT) {
            JsonSerializer<?> construct = EnumSerializer.construct(javaType.getRawClass(), serializationConfig, beanDescription, findExpectedFormat);
            if (this._factoryConfig.hasSerializerModifiers()) {
                for (BeanSerializerModifier modifyEnumSerializer : this._factoryConfig.serializerModifiers()) {
                    construct = modifyEnumSerializer.modifyEnumSerializer(serializationConfig, javaType, beanDescription, construct);
                }
            }
            return construct;
        }
        ((BasicBeanDescription) beanDescription).removeProperty("declaringClass");
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findKeySerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findKeySerializer = serializerProvider.getAnnotationIntrospector().findKeySerializer(annotated);
        if (findKeySerializer != null) {
            return serializerProvider.serializerInstance(annotated, findKeySerializer);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findContentSerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(annotated);
        if (findContentSerializer != null) {
            return serializerProvider.serializerInstance(annotated, findContentSerializer);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Object findFilterId(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        return serializationConfig.getAnnotationIntrospector().findFilterId(beanDescription.getClassInfo());
    }

    /* access modifiers changed from: protected */
    public boolean usesStaticTyping(SerializationConfig serializationConfig, BeanDescription beanDescription, TypeSerializer typeSerializer) {
        if (typeSerializer != null) {
            return false;
        }
        JsonSerialize.Typing findSerializationTyping = serializationConfig.getAnnotationIntrospector().findSerializationTyping(beanDescription.getClassInfo());
        if (findSerializationTyping == null || findSerializationTyping == JsonSerialize.Typing.DEFAULT_TYPING) {
            return serializationConfig.isEnabled(MapperFeature.USE_STATIC_TYPING);
        }
        if (findSerializationTyping == JsonSerialize.Typing.STATIC) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Class<?> _verifyAsClass(Object obj, String str, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Class) {
            Class<?> cls2 = (Class) obj;
            if (cls2 == cls || ClassUtil.isBogusClass(cls2)) {
                return null;
            }
            return cls2;
        }
        throw new IllegalStateException("AnnotationIntrospector." + str + "() returned value of type " + obj.getClass().getName() + ": expected type JsonSerializer or Class<JsonSerializer> instead");
    }
}

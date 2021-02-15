package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;

public class PropertyBuilder {
    private static final Object NO_DEFAULT_MARKER = Boolean.FALSE;
    protected final AnnotationIntrospector _annotationIntrospector = this._config.getAnnotationIntrospector();
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final JsonInclude.Value _defaultInclusion;

    public PropertyBuilder(SerializationConfig serializationConfig, BeanDescription beanDescription) {
        this._config = serializationConfig;
        this._beanDesc = beanDescription;
        this._defaultInclusion = beanDescription.findPropertyInclusion(serializationConfig.getDefaultPropertyInclusion());
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:52:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.ser.BeanPropertyWriter buildWriter(com.fasterxml.jackson.databind.SerializerProvider r14, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r15, com.fasterxml.jackson.databind.JavaType r16, com.fasterxml.jackson.databind.JsonSerializer<?> r17, com.fasterxml.jackson.databind.jsontype.TypeSerializer r18, com.fasterxml.jackson.databind.jsontype.TypeSerializer r19, com.fasterxml.jackson.databind.introspect.AnnotatedMember r20, boolean r21) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r13 = this;
            r0 = r13
            r1 = r19
            r11 = r20
            r5 = r16
            r2 = r21
            com.fasterxml.jackson.databind.JavaType r2 = r13.findSerializationType(r11, r2, r5)
            if (r1 == 0) goto L_0x0057
            if (r2 != 0) goto L_0x0012
            r2 = r5
        L_0x0012:
            com.fasterxml.jackson.databind.JavaType r3 = r2.getContentType()
            if (r3 == 0) goto L_0x0021
            com.fasterxml.jackson.databind.JavaType r1 = r2.withContentTypeHandler(r1)
            r1.getContentType()
            r8 = r1
            goto L_0x0058
        L_0x0021:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Problem trying to create BeanPropertyWriter for property '"
            r3.append(r4)
            java.lang.String r4 = r15.getName()
            r3.append(r4)
            java.lang.String r4 = "' (of type "
            r3.append(r4)
            com.fasterxml.jackson.databind.BeanDescription r4 = r0._beanDesc
            com.fasterxml.jackson.databind.JavaType r4 = r4.getType()
            r3.append(r4)
            java.lang.String r4 = "); serialization type "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = " has no content"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x0057:
            r8 = r2
        L_0x0058:
            r1 = 0
            r2 = 0
            com.fasterxml.jackson.annotation.JsonInclude$Value r3 = r0._defaultInclusion
            com.fasterxml.jackson.annotation.JsonInclude$Value r4 = r15.findInclusion()
            com.fasterxml.jackson.annotation.JsonInclude$Value r3 = r3.withOverrides(r4)
            com.fasterxml.jackson.annotation.JsonInclude$Include r3 = r3.getValueInclusion()
            com.fasterxml.jackson.annotation.JsonInclude$Include r4 = com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS
            if (r3 != r4) goto L_0x006e
            com.fasterxml.jackson.annotation.JsonInclude$Include r3 = com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS
        L_0x006e:
            int[] r4 = com.fasterxml.jackson.databind.ser.PropertyBuilder.AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include
            int r3 = r3.ordinal()
            r3 = r4[r3]
            r4 = 1
            if (r3 == r4) goto L_0x00a7
            r6 = 2
            if (r3 == r6) goto L_0x009c
            r6 = 3
            if (r3 == r6) goto L_0x0099
            r6 = 4
            if (r3 == r6) goto L_0x0083
            goto L_0x0084
        L_0x0083:
            r2 = 1
        L_0x0084:
            boolean r3 = r16.isContainerType()
            if (r3 == 0) goto L_0x0096
            com.fasterxml.jackson.databind.SerializationConfig r3 = r0._config
            com.fasterxml.jackson.databind.SerializationFeature r4 = com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS
            boolean r3 = r3.isEnabled(r4)
            if (r3 != 0) goto L_0x0096
            java.lang.Object r1 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
        L_0x0096:
            r10 = r1
            r9 = r2
            goto L_0x00d6
        L_0x0099:
            java.lang.Object r1 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
            goto L_0x00a4
        L_0x009c:
            boolean r2 = r16.isReferenceType()
            if (r2 == 0) goto L_0x00a4
            java.lang.Object r1 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
        L_0x00a4:
            r10 = r1
            r9 = 1
            goto L_0x00d6
        L_0x00a7:
            if (r8 != 0) goto L_0x00ab
            r1 = r5
            goto L_0x00ac
        L_0x00ab:
            r1 = r8
        L_0x00ac:
            com.fasterxml.jackson.annotation.JsonInclude$Value r3 = r0._defaultInclusion
            com.fasterxml.jackson.annotation.JsonInclude$Include r3 = r3.getValueInclusion()
            com.fasterxml.jackson.annotation.JsonInclude$Include r6 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
            if (r3 != r6) goto L_0x00bf
            java.lang.String r3 = r15.getName()
            java.lang.Object r1 = r13.getPropertyDefaultValue(r3, r11, r1)
            goto L_0x00c3
        L_0x00bf:
            java.lang.Object r1 = r13.getDefaultValue(r1)
        L_0x00c3:
            if (r1 != 0) goto L_0x00c6
            goto L_0x00a4
        L_0x00c6:
            java.lang.Class r3 = r1.getClass()
            boolean r3 = r3.isArray()
            if (r3 == 0) goto L_0x00d4
            java.lang.Object r1 = com.fasterxml.jackson.databind.util.ArrayBuilders.getArrayComparator(r1)
        L_0x00d4:
            r10 = r1
            r9 = 0
        L_0x00d6:
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r12 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter
            com.fasterxml.jackson.databind.BeanDescription r1 = r0._beanDesc
            com.fasterxml.jackson.databind.util.Annotations r4 = r1.getClassAnnotations()
            r1 = r12
            r2 = r15
            r3 = r20
            r5 = r16
            r6 = r17
            r7 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r0._annotationIntrospector
            java.lang.Object r1 = r1.findNullSerializer(r11)
            if (r1 == 0) goto L_0x00fb
            r2 = r14
            com.fasterxml.jackson.databind.JsonSerializer r1 = r14.serializerInstance(r11, r1)
            r12.assignNullSerializer(r1)
        L_0x00fb:
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r0._annotationIntrospector
            com.fasterxml.jackson.databind.util.NameTransformer r1 = r1.findUnwrappingNameTransformer(r11)
            if (r1 == 0) goto L_0x0107
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r12 = r12.unwrappingWriter(r1)
        L_0x0107:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder.buildWriter(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.introspect.AnnotatedMember, boolean):com.fasterxml.jackson.databind.ser.BeanPropertyWriter");
    }

    /* renamed from: com.fasterxml.jackson.databind.ser.PropertyBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.fasterxml.jackson.annotation.JsonInclude$Include[] r0 = com.fasterxml.jackson.annotation.JsonInclude.Include.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include = r0
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_ABSENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.annotation.JsonInclude$Include r1 = com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public JavaType findSerializationType(Annotated annotated, boolean z, JavaType javaType) throws JsonMappingException {
        JavaType refineSerializationType = this._annotationIntrospector.refineSerializationType(this._config, annotated, javaType);
        boolean z2 = true;
        if (refineSerializationType != javaType) {
            Class<?> rawClass = refineSerializationType.getRawClass();
            Class<?> rawClass2 = javaType.getRawClass();
            if (!rawClass.isAssignableFrom(rawClass2) && !rawClass2.isAssignableFrom(rawClass)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + annotated.getName() + "': class " + rawClass.getName() + " not a super-type of (declared) class " + rawClass2.getName());
            }
            javaType = refineSerializationType;
            z = true;
        }
        JsonSerialize.Typing findSerializationTyping = this._annotationIntrospector.findSerializationTyping(annotated);
        if (!(findSerializationTyping == null || findSerializationTyping == JsonSerialize.Typing.DEFAULT_TYPING)) {
            if (findSerializationTyping != JsonSerialize.Typing.STATIC) {
                z2 = false;
            }
            z = z2;
        }
        if (z) {
            return javaType.withStaticTyping();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Object getDefaultBean() {
        Object obj = this._defaultBean;
        if (obj == null) {
            obj = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (obj == null) {
                obj = NO_DEFAULT_MARKER;
            }
            this._defaultBean = obj;
        }
        if (obj == NO_DEFAULT_MARKER) {
            return null;
        }
        return this._defaultBean;
    }

    /* access modifiers changed from: protected */
    public Object getPropertyDefaultValue(String str, AnnotatedMember annotatedMember, JavaType javaType) {
        Object defaultBean = getDefaultBean();
        if (defaultBean == null) {
            return getDefaultValue(javaType);
        }
        try {
            return annotatedMember.getValue(defaultBean);
        } catch (Exception e) {
            return _throwWrapped(e, str, defaultBean);
        }
    }

    /* access modifiers changed from: protected */
    public Object getDefaultValue(JavaType javaType) {
        Class<?> rawClass = javaType.getRawClass();
        Class<?> primitiveType = ClassUtil.primitiveType(rawClass);
        if (primitiveType != null) {
            return ClassUtil.defaultValue(primitiveType);
        }
        if (javaType.isContainerType() || javaType.isReferenceType()) {
            return JsonInclude.Include.NON_EMPTY;
        }
        if (rawClass == String.class) {
            return "";
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Object _throwWrapped(Exception exc, String str, Object obj) {
        Throwable th;
        while (true) {
            Throwable cause = th.getCause();
            th = exc;
            if (cause == null) {
                break;
            }
            th = th.getCause();
        }
        if (th instanceof Error) {
            throw ((Error) th);
        } else if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else {
            throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
        }
    }
}

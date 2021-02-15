package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AnnotationIntrospector implements Versioned, Serializable {
    public void findAndAddVirtualProperties(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, List<BeanPropertyWriter> list) {
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        return visibilityChecker;
    }

    public String findClassDescription(AnnotatedClass annotatedClass) {
        return null;
    }

    public Object findContentDeserializer(Annotated annotated) {
        return null;
    }

    public Object findContentSerializer(Annotated annotated) {
        return null;
    }

    public JsonCreator.Mode findCreatorBinding(Annotated annotated) {
        return null;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        return null;
    }

    @Deprecated
    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Object findDeserializationConverter(Annotated annotated) {
        return null;
    }

    @Deprecated
    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        return null;
    }

    @Deprecated
    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Object findDeserializer(Annotated annotated) {
        return null;
    }

    public Object findFilterId(Annotated annotated) {
        return null;
    }

    public JsonFormat.Value findFormat(Annotated annotated) {
        return null;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        return null;
    }

    public String findImplicitPropertyName(AnnotatedMember annotatedMember) {
        return null;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        return null;
    }

    public Object findKeyDeserializer(Annotated annotated) {
        return null;
    }

    public Object findKeySerializer(Annotated annotated) {
        return null;
    }

    public PropertyName findNameForDeserialization(Annotated annotated) {
        return null;
    }

    public PropertyName findNameForSerialization(Annotated annotated) {
        return null;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        return null;
    }

    public Object findNullSerializer(Annotated annotated) {
        return null;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        return null;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        return objectIdInfo;
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        return null;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        return null;
    }

    public String[] findPropertiesToIgnore(Annotated annotated, boolean z) {
        return null;
    }

    public JsonProperty.Access findPropertyAccess(Annotated annotated) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return null;
    }

    public String findPropertyDefaultValue(Annotated annotated) {
        return null;
    }

    public String findPropertyDescription(Annotated annotated) {
        return null;
    }

    public Integer findPropertyIndex(Annotated annotated) {
        return null;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        return null;
    }

    public ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        return null;
    }

    public PropertyName findRootName(AnnotatedClass annotatedClass) {
        return null;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        return null;
    }

    @Deprecated
    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public Object findSerializationConverter(Annotated annotated) {
        return null;
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusion(Annotated annotated, JsonInclude.Include include) {
        return include;
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusionForContent(Annotated annotated, JsonInclude.Include include) {
        return include;
    }

    @Deprecated
    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        return null;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        return null;
    }

    public Boolean findSerializationSortAlphabetically(Annotated annotated) {
        return null;
    }

    @Deprecated
    public Class<?> findSerializationType(Annotated annotated) {
        return null;
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        return null;
    }

    public Object findSerializer(Annotated annotated) {
        return null;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        return null;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        return null;
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        return null;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        return null;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        return null;
    }

    public Class<?>[] findViews(Annotated annotated) {
        return null;
    }

    public PropertyName findWrapperName(Annotated annotated) {
        return null;
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return false;
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return false;
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return false;
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        return null;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        return false;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        return null;
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        return null;
    }

    public AnnotatedMethod resolveSetterConflict(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, AnnotatedMethod annotatedMethod2) {
        return null;
    }

    public abstract Version version();

    public static class ReferenceProperty {
        private final String _name;
        private final Type _type;

        public enum Type {
            MANAGED_REFERENCE,
            BACK_REFERENCE
        }

        public ReferenceProperty(Type type, String str) {
            this._type = type;
            this._name = str;
        }

        public static ReferenceProperty managed(String str) {
            return new ReferenceProperty(Type.MANAGED_REFERENCE, str);
        }

        public static ReferenceProperty back(String str) {
            return new ReferenceProperty(Type.BACK_REFERENCE, str);
        }

        public Type getType() {
            return this._type;
        }

        public String getName() {
            return this._name;
        }

        public boolean isManagedReference() {
            return this._type == Type.MANAGED_REFERENCE;
        }

        public boolean isBackReference() {
            return this._type == Type.BACK_REFERENCE;
        }
    }

    public static AnnotationIntrospector nopInstance() {
        return NopAnnotationIntrospector.instance;
    }

    public static AnnotationIntrospector pair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        return new AnnotationIntrospectorPair(annotationIntrospector, annotationIntrospector2);
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        return Collections.singletonList(this);
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
        collection.add(this);
        return collection;
    }

    @Deprecated
    public String[] findPropertiesToIgnore(Annotated annotated) {
        return findPropertiesToIgnore(annotated, true);
    }

    public JsonInclude.Value findPropertyInclusion(Annotated annotated) {
        return JsonInclude.Value.empty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        r0 = r12.getKeyType();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType refineSerializationType(com.fasterxml.jackson.databind.cfg.MapperConfig<?> r10, com.fasterxml.jackson.databind.introspect.Annotated r11, com.fasterxml.jackson.databind.JavaType r12) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.type.TypeFactory r10 = r10.getTypeFactory()
            java.lang.Class r0 = r9.findSerializationType(r11)
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = 4
            r6 = 0
            if (r0 == 0) goto L_0x0043
            boolean r7 = r12.hasRawClass(r0)
            if (r7 == 0) goto L_0x001b
            com.fasterxml.jackson.databind.JavaType r12 = r12.withStaticTyping()
            goto L_0x0043
        L_0x001b:
            com.fasterxml.jackson.databind.JavaType r12 = r10.constructGeneralizedType(r12, r0)     // Catch:{ IllegalArgumentException -> 0x0020 }
            goto L_0x0043
        L_0x0020:
            r10 = move-exception
            com.fasterxml.jackson.databind.JsonMappingException r7 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r12
            java.lang.String r12 = r0.getName()
            r5[r3] = r12
            java.lang.String r11 = r11.getName()
            r5[r2] = r11
            java.lang.String r11 = r10.getMessage()
            r5[r1] = r11
            java.lang.String r11 = "Failed to widen type %s with annotation (value %s), from '%s': %s"
            java.lang.String r11 = java.lang.String.format(r11, r5)
            r7.<init>((java.io.Closeable) r6, (java.lang.String) r11, (java.lang.Throwable) r10)
            throw r7
        L_0x0043:
            boolean r0 = r12.isMapLikeType()
            if (r0 == 0) goto L_0x008c
            com.fasterxml.jackson.databind.JavaType r0 = r12.getKeyType()
            java.lang.Class r7 = r9.findSerializationKeyType(r11, r0)
            if (r7 == 0) goto L_0x008c
            boolean r8 = r0.hasRawClass(r7)
            if (r8 == 0) goto L_0x005e
            com.fasterxml.jackson.databind.JavaType r0 = r0.withStaticTyping()
            goto L_0x0062
        L_0x005e:
            com.fasterxml.jackson.databind.JavaType r0 = r10.constructGeneralizedType(r0, r7)     // Catch:{ IllegalArgumentException -> 0x0069 }
        L_0x0062:
            com.fasterxml.jackson.databind.type.MapLikeType r12 = (com.fasterxml.jackson.databind.type.MapLikeType) r12
            com.fasterxml.jackson.databind.type.MapLikeType r12 = r12.withKeyType(r0)
            goto L_0x008c
        L_0x0069:
            r10 = move-exception
            com.fasterxml.jackson.databind.JsonMappingException r0 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r12
            java.lang.String r12 = r7.getName()
            r5[r3] = r12
            java.lang.String r11 = r11.getName()
            r5[r2] = r11
            java.lang.String r11 = r10.getMessage()
            r5[r1] = r11
            java.lang.String r11 = "Failed to widen key type of %s with concrete-type annotation (value %s), from '%s': %s"
            java.lang.String r11 = java.lang.String.format(r11, r5)
            r0.<init>((java.io.Closeable) r6, (java.lang.String) r11, (java.lang.Throwable) r10)
            throw r0
        L_0x008c:
            com.fasterxml.jackson.databind.JavaType r0 = r12.getContentType()
            if (r0 == 0) goto L_0x00cf
            java.lang.Class r7 = r9.findSerializationContentType(r11, r0)
            if (r7 == 0) goto L_0x00cf
            boolean r8 = r0.hasRawClass(r7)
            if (r8 == 0) goto L_0x00a3
            com.fasterxml.jackson.databind.JavaType r10 = r0.withStaticTyping()
            goto L_0x00a7
        L_0x00a3:
            com.fasterxml.jackson.databind.JavaType r10 = r10.constructGeneralizedType(r0, r7)     // Catch:{ IllegalArgumentException -> 0x00ac }
        L_0x00a7:
            com.fasterxml.jackson.databind.JavaType r12 = r12.withContentType(r10)
            goto L_0x00cf
        L_0x00ac:
            r10 = move-exception
            com.fasterxml.jackson.databind.JsonMappingException r0 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r12
            java.lang.String r12 = r7.getName()
            r5[r3] = r12
            java.lang.String r11 = r11.getName()
            r5[r2] = r11
            java.lang.String r11 = r10.getMessage()
            r5[r1] = r11
            java.lang.String r11 = "Failed to widen value type of %s with concrete-type annotation (value %s), from '%s': %s"
            java.lang.String r11 = java.lang.String.format(r11, r5)
            r0.<init>((java.io.Closeable) r6, (java.lang.String) r11, (java.lang.Throwable) r10)
            throw r0
        L_0x00cf:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.AnnotationIntrospector.refineSerializationType(com.fasterxml.jackson.databind.cfg.MapperConfig, com.fasterxml.jackson.databind.introspect.Annotated, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.JavaType");
    }

    public String findEnumValue(Enum<?> enumR) {
        return enumR.name();
    }

    public String[] findEnumValues(Class<?> cls, Enum<?>[] enumArr, String[] strArr) {
        int length = enumArr.length;
        for (int i = 0; i < length; i++) {
            strArr[i] = findEnumValue(enumArr[i]);
        }
        return strArr;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0044, code lost:
        r0 = r12.getKeyType();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JavaType refineDeserializationType(com.fasterxml.jackson.databind.cfg.MapperConfig<?> r10, com.fasterxml.jackson.databind.introspect.Annotated r11, com.fasterxml.jackson.databind.JavaType r12) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.type.TypeFactory r10 = r10.getTypeFactory()
            java.lang.Class r0 = r9.findDeserializationType(r11, r12)
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            r5 = 4
            r6 = 0
            if (r0 == 0) goto L_0x003e
            boolean r7 = r12.hasRawClass(r0)
            if (r7 != 0) goto L_0x003e
            com.fasterxml.jackson.databind.JavaType r12 = r10.constructSpecializedType(r12, r0)     // Catch:{ IllegalArgumentException -> 0x001b }
            goto L_0x003e
        L_0x001b:
            r10 = move-exception
            com.fasterxml.jackson.databind.JsonMappingException r7 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r12
            java.lang.String r12 = r0.getName()
            r5[r3] = r12
            java.lang.String r11 = r11.getName()
            r5[r2] = r11
            java.lang.String r11 = r10.getMessage()
            r5[r1] = r11
            java.lang.String r11 = "Failed to narrow type %s with annotation (value %s), from '%s': %s"
            java.lang.String r11 = java.lang.String.format(r11, r5)
            r7.<init>((java.io.Closeable) r6, (java.lang.String) r11, (java.lang.Throwable) r10)
            throw r7
        L_0x003e:
            boolean r0 = r12.isMapLikeType()
            if (r0 == 0) goto L_0x007d
            com.fasterxml.jackson.databind.JavaType r0 = r12.getKeyType()
            java.lang.Class r7 = r9.findDeserializationKeyType(r11, r0)
            if (r7 == 0) goto L_0x007d
            com.fasterxml.jackson.databind.JavaType r0 = r10.constructSpecializedType(r0, r7)     // Catch:{ IllegalArgumentException -> 0x005a }
            r8 = r12
            com.fasterxml.jackson.databind.type.MapLikeType r8 = (com.fasterxml.jackson.databind.type.MapLikeType) r8     // Catch:{ IllegalArgumentException -> 0x005a }
            com.fasterxml.jackson.databind.type.MapLikeType r12 = r8.withKeyType(r0)     // Catch:{ IllegalArgumentException -> 0x005a }
            goto L_0x007d
        L_0x005a:
            r10 = move-exception
            com.fasterxml.jackson.databind.JsonMappingException r0 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r12
            java.lang.String r12 = r7.getName()
            r5[r3] = r12
            java.lang.String r11 = r11.getName()
            r5[r2] = r11
            java.lang.String r11 = r10.getMessage()
            r5[r1] = r11
            java.lang.String r11 = "Failed to narrow key type of %s with concrete-type annotation (value %s), from '%s': %s"
            java.lang.String r11 = java.lang.String.format(r11, r5)
            r0.<init>((java.io.Closeable) r6, (java.lang.String) r11, (java.lang.Throwable) r10)
            throw r0
        L_0x007d:
            com.fasterxml.jackson.databind.JavaType r0 = r12.getContentType()
            if (r0 == 0) goto L_0x00b5
            java.lang.Class r7 = r9.findDeserializationContentType(r11, r0)
            if (r7 == 0) goto L_0x00b5
            com.fasterxml.jackson.databind.JavaType r10 = r10.constructSpecializedType(r0, r7)     // Catch:{ IllegalArgumentException -> 0x0092 }
            com.fasterxml.jackson.databind.JavaType r12 = r12.withContentType(r10)     // Catch:{ IllegalArgumentException -> 0x0092 }
            goto L_0x00b5
        L_0x0092:
            r10 = move-exception
            com.fasterxml.jackson.databind.JsonMappingException r0 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r5[r4] = r12
            java.lang.String r12 = r7.getName()
            r5[r3] = r12
            java.lang.String r11 = r11.getName()
            r5[r2] = r11
            java.lang.String r11 = r10.getMessage()
            r5[r1] = r11
            java.lang.String r11 = "Failed to narrow value type of %s with concrete-type annotation (value %s), from '%s': %s"
            java.lang.String r11 = java.lang.String.format(r11, r5)
            r0.<init>((java.io.Closeable) r6, (java.lang.String) r11, (java.lang.Throwable) r10)
            throw r0
        L_0x00b5:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.AnnotationIntrospector.refineDeserializationType(com.fasterxml.jackson.databind.cfg.MapperConfig, com.fasterxml.jackson.databind.introspect.Annotated, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.JavaType");
    }

    /* access modifiers changed from: protected */
    public <A extends Annotation> A _findAnnotation(Annotated annotated, Class<A> cls) {
        return annotated.getAnnotation(cls);
    }

    /* access modifiers changed from: protected */
    public boolean _hasAnnotation(Annotated annotated, Class<? extends Annotation> cls) {
        return annotated.hasAnnotation(cls);
    }

    /* access modifiers changed from: protected */
    public boolean _hasOneOf(Annotated annotated, Class<? extends Annotation>[] clsArr) {
        return annotated.hasOneOf(clsArr);
    }
}

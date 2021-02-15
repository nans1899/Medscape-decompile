package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnotationIntrospectorPair extends AnnotationIntrospector implements Serializable {
    private static final long serialVersionUID = 1;
    protected final AnnotationIntrospector _primary;
    protected final AnnotationIntrospector _secondary;

    public AnnotationIntrospectorPair(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        this._primary = annotationIntrospector;
        this._secondary = annotationIntrospector2;
    }

    public Version version() {
        return this._primary.version();
    }

    public static AnnotationIntrospector create(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        if (annotationIntrospector == null) {
            return annotationIntrospector2;
        }
        return annotationIntrospector2 == null ? annotationIntrospector : new AnnotationIntrospectorPair(annotationIntrospector, annotationIntrospector2);
    }

    public Collection<AnnotationIntrospector> allIntrospectors() {
        return allIntrospectors(new ArrayList());
    }

    public Collection<AnnotationIntrospector> allIntrospectors(Collection<AnnotationIntrospector> collection) {
        this._primary.allIntrospectors(collection);
        this._secondary.allIntrospectors(collection);
        return collection;
    }

    public boolean isAnnotationBundle(Annotation annotation) {
        return this._primary.isAnnotationBundle(annotation) || this._secondary.isAnnotationBundle(annotation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0016, code lost:
        r3 = r2._secondary.findRootName(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findRootName(com.fasterxml.jackson.databind.introspect.AnnotatedClass r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findRootName(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r3 = r0.findRootName(r3)
            return r3
        L_0x000f:
            boolean r1 = r0.hasSimpleName()
            if (r1 == 0) goto L_0x0016
            return r0
        L_0x0016:
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r3 = r1.findRootName(r3)
            if (r3 != 0) goto L_0x001f
            goto L_0x0020
        L_0x001f:
            r0 = r3
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findRootName(com.fasterxml.jackson.databind.introspect.AnnotatedClass):com.fasterxml.jackson.databind.PropertyName");
    }

    @Deprecated
    public String[] findPropertiesToIgnore(Annotated annotated) {
        String[] findPropertiesToIgnore = this._primary.findPropertiesToIgnore(annotated);
        return findPropertiesToIgnore == null ? this._secondary.findPropertiesToIgnore(annotated) : findPropertiesToIgnore;
    }

    public String[] findPropertiesToIgnore(Annotated annotated, boolean z) {
        String[] findPropertiesToIgnore = this._primary.findPropertiesToIgnore(annotated, z);
        return findPropertiesToIgnore == null ? this._secondary.findPropertiesToIgnore(annotated, z) : findPropertiesToIgnore;
    }

    public Boolean findIgnoreUnknownProperties(AnnotatedClass annotatedClass) {
        Boolean findIgnoreUnknownProperties = this._primary.findIgnoreUnknownProperties(annotatedClass);
        return findIgnoreUnknownProperties == null ? this._secondary.findIgnoreUnknownProperties(annotatedClass) : findIgnoreUnknownProperties;
    }

    public Boolean isIgnorableType(AnnotatedClass annotatedClass) {
        Boolean isIgnorableType = this._primary.isIgnorableType(annotatedClass);
        return isIgnorableType == null ? this._secondary.isIgnorableType(annotatedClass) : isIgnorableType;
    }

    public Object findFilterId(Annotated annotated) {
        Object findFilterId = this._primary.findFilterId(annotated);
        return findFilterId == null ? this._secondary.findFilterId(annotated) : findFilterId;
    }

    public Object findNamingStrategy(AnnotatedClass annotatedClass) {
        Object findNamingStrategy = this._primary.findNamingStrategy(annotatedClass);
        return findNamingStrategy == null ? this._secondary.findNamingStrategy(annotatedClass) : findNamingStrategy;
    }

    public String findClassDescription(AnnotatedClass annotatedClass) {
        String findClassDescription = this._primary.findClassDescription(annotatedClass);
        return (findClassDescription == null || findClassDescription.isEmpty()) ? this._secondary.findClassDescription(annotatedClass) : findClassDescription;
    }

    public VisibilityChecker<?> findAutoDetectVisibility(AnnotatedClass annotatedClass, VisibilityChecker<?> visibilityChecker) {
        return this._primary.findAutoDetectVisibility(annotatedClass, this._secondary.findAutoDetectVisibility(annotatedClass, visibilityChecker));
    }

    public TypeResolverBuilder<?> findTypeResolver(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, JavaType javaType) {
        TypeResolverBuilder<?> findTypeResolver = this._primary.findTypeResolver(mapperConfig, annotatedClass, javaType);
        return findTypeResolver == null ? this._secondary.findTypeResolver(mapperConfig, annotatedClass, javaType) : findTypeResolver;
    }

    public TypeResolverBuilder<?> findPropertyTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        TypeResolverBuilder<?> findPropertyTypeResolver = this._primary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType);
        return findPropertyTypeResolver == null ? this._secondary.findPropertyTypeResolver(mapperConfig, annotatedMember, javaType) : findPropertyTypeResolver;
    }

    public TypeResolverBuilder<?> findPropertyContentTypeResolver(MapperConfig<?> mapperConfig, AnnotatedMember annotatedMember, JavaType javaType) {
        TypeResolverBuilder<?> findPropertyContentTypeResolver = this._primary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType);
        return findPropertyContentTypeResolver == null ? this._secondary.findPropertyContentTypeResolver(mapperConfig, annotatedMember, javaType) : findPropertyContentTypeResolver;
    }

    public List<NamedType> findSubtypes(Annotated annotated) {
        List<NamedType> findSubtypes = this._primary.findSubtypes(annotated);
        List<NamedType> findSubtypes2 = this._secondary.findSubtypes(annotated);
        if (findSubtypes == null || findSubtypes.isEmpty()) {
            return findSubtypes2;
        }
        if (findSubtypes2 == null || findSubtypes2.isEmpty()) {
            return findSubtypes;
        }
        ArrayList arrayList = new ArrayList(findSubtypes.size() + findSubtypes2.size());
        arrayList.addAll(findSubtypes);
        arrayList.addAll(findSubtypes2);
        return arrayList;
    }

    public String findTypeName(AnnotatedClass annotatedClass) {
        String findTypeName = this._primary.findTypeName(annotatedClass);
        return (findTypeName == null || findTypeName.length() == 0) ? this._secondary.findTypeName(annotatedClass) : findTypeName;
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType(AnnotatedMember annotatedMember) {
        AnnotationIntrospector.ReferenceProperty findReferenceType = this._primary.findReferenceType(annotatedMember);
        return findReferenceType == null ? this._secondary.findReferenceType(annotatedMember) : findReferenceType;
    }

    public NameTransformer findUnwrappingNameTransformer(AnnotatedMember annotatedMember) {
        NameTransformer findUnwrappingNameTransformer = this._primary.findUnwrappingNameTransformer(annotatedMember);
        return findUnwrappingNameTransformer == null ? this._secondary.findUnwrappingNameTransformer(annotatedMember) : findUnwrappingNameTransformer;
    }

    public Object findInjectableValueId(AnnotatedMember annotatedMember) {
        Object findInjectableValueId = this._primary.findInjectableValueId(annotatedMember);
        return findInjectableValueId == null ? this._secondary.findInjectableValueId(annotatedMember) : findInjectableValueId;
    }

    public boolean hasIgnoreMarker(AnnotatedMember annotatedMember) {
        return this._primary.hasIgnoreMarker(annotatedMember) || this._secondary.hasIgnoreMarker(annotatedMember);
    }

    public Boolean hasRequiredMarker(AnnotatedMember annotatedMember) {
        Boolean hasRequiredMarker = this._primary.hasRequiredMarker(annotatedMember);
        return hasRequiredMarker == null ? this._secondary.hasRequiredMarker(annotatedMember) : hasRequiredMarker;
    }

    public Object findSerializer(Annotated annotated) {
        Object findSerializer = this._primary.findSerializer(annotated);
        return _isExplicitClassOrOb(findSerializer, JsonSerializer.None.class) ? findSerializer : this._secondary.findSerializer(annotated);
    }

    public Object findKeySerializer(Annotated annotated) {
        Object findKeySerializer = this._primary.findKeySerializer(annotated);
        return _isExplicitClassOrOb(findKeySerializer, JsonSerializer.None.class) ? findKeySerializer : this._secondary.findKeySerializer(annotated);
    }

    public Object findContentSerializer(Annotated annotated) {
        Object findContentSerializer = this._primary.findContentSerializer(annotated);
        return _isExplicitClassOrOb(findContentSerializer, JsonSerializer.None.class) ? findContentSerializer : this._secondary.findContentSerializer(annotated);
    }

    public Object findNullSerializer(Annotated annotated) {
        Object findNullSerializer = this._primary.findNullSerializer(annotated);
        return _isExplicitClassOrOb(findNullSerializer, JsonSerializer.None.class) ? findNullSerializer : this._secondary.findNullSerializer(annotated);
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusion(Annotated annotated, JsonInclude.Include include) {
        return this._primary.findSerializationInclusion(annotated, this._secondary.findSerializationInclusion(annotated, include));
    }

    @Deprecated
    public JsonInclude.Include findSerializationInclusionForContent(Annotated annotated, JsonInclude.Include include) {
        return this._primary.findSerializationInclusionForContent(annotated, this._secondary.findSerializationInclusionForContent(annotated, include));
    }

    public JsonInclude.Value findPropertyInclusion(Annotated annotated) {
        JsonInclude.Value findPropertyInclusion = this._secondary.findPropertyInclusion(annotated);
        JsonInclude.Value findPropertyInclusion2 = this._primary.findPropertyInclusion(annotated);
        if (findPropertyInclusion == null) {
            return findPropertyInclusion2;
        }
        return findPropertyInclusion.withOverrides(findPropertyInclusion2);
    }

    public JsonSerialize.Typing findSerializationTyping(Annotated annotated) {
        JsonSerialize.Typing findSerializationTyping = this._primary.findSerializationTyping(annotated);
        return findSerializationTyping == null ? this._secondary.findSerializationTyping(annotated) : findSerializationTyping;
    }

    public Object findSerializationConverter(Annotated annotated) {
        Object findSerializationConverter = this._primary.findSerializationConverter(annotated);
        return findSerializationConverter == null ? this._secondary.findSerializationConverter(annotated) : findSerializationConverter;
    }

    public Object findSerializationContentConverter(AnnotatedMember annotatedMember) {
        Object findSerializationContentConverter = this._primary.findSerializationContentConverter(annotatedMember);
        return findSerializationContentConverter == null ? this._secondary.findSerializationContentConverter(annotatedMember) : findSerializationContentConverter;
    }

    public Class<?>[] findViews(Annotated annotated) {
        Class<?>[] findViews = this._primary.findViews(annotated);
        return findViews == null ? this._secondary.findViews(annotated) : findViews;
    }

    public Boolean isTypeId(AnnotatedMember annotatedMember) {
        Boolean isTypeId = this._primary.isTypeId(annotatedMember);
        return isTypeId == null ? this._secondary.isTypeId(annotatedMember) : isTypeId;
    }

    public ObjectIdInfo findObjectIdInfo(Annotated annotated) {
        ObjectIdInfo findObjectIdInfo = this._primary.findObjectIdInfo(annotated);
        return findObjectIdInfo == null ? this._secondary.findObjectIdInfo(annotated) : findObjectIdInfo;
    }

    public ObjectIdInfo findObjectReferenceInfo(Annotated annotated, ObjectIdInfo objectIdInfo) {
        return this._primary.findObjectReferenceInfo(annotated, this._secondary.findObjectReferenceInfo(annotated, objectIdInfo));
    }

    public JsonFormat.Value findFormat(Annotated annotated) {
        JsonFormat.Value findFormat = this._primary.findFormat(annotated);
        JsonFormat.Value findFormat2 = this._secondary.findFormat(annotated);
        if (findFormat2 == null) {
            return findFormat;
        }
        return findFormat2.withOverrides(findFormat);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r3 = r2._secondary.findWrapperName(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findWrapperName(com.fasterxml.jackson.databind.introspect.Annotated r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findWrapperName(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findWrapperName(r3)
            goto L_0x001c
        L_0x000f:
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.USE_DEFAULT
            if (r0 != r1) goto L_0x001c
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r3 = r1.findWrapperName(r3)
            if (r3 == 0) goto L_0x001c
            r0 = r3
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findWrapperName(com.fasterxml.jackson.databind.introspect.Annotated):com.fasterxml.jackson.databind.PropertyName");
    }

    public String findPropertyDefaultValue(Annotated annotated) {
        String findPropertyDefaultValue = this._primary.findPropertyDefaultValue(annotated);
        return (findPropertyDefaultValue == null || findPropertyDefaultValue.isEmpty()) ? this._secondary.findPropertyDefaultValue(annotated) : findPropertyDefaultValue;
    }

    public String findPropertyDescription(Annotated annotated) {
        String findPropertyDescription = this._primary.findPropertyDescription(annotated);
        return findPropertyDescription == null ? this._secondary.findPropertyDescription(annotated) : findPropertyDescription;
    }

    public Integer findPropertyIndex(Annotated annotated) {
        Integer findPropertyIndex = this._primary.findPropertyIndex(annotated);
        return findPropertyIndex == null ? this._secondary.findPropertyIndex(annotated) : findPropertyIndex;
    }

    public String findImplicitPropertyName(AnnotatedMember annotatedMember) {
        String findImplicitPropertyName = this._primary.findImplicitPropertyName(annotatedMember);
        return findImplicitPropertyName == null ? this._secondary.findImplicitPropertyName(annotatedMember) : findImplicitPropertyName;
    }

    public JsonProperty.Access findPropertyAccess(Annotated annotated) {
        JsonProperty.Access findPropertyAccess = this._primary.findPropertyAccess(annotated);
        if (findPropertyAccess != null && findPropertyAccess != JsonProperty.Access.AUTO) {
            return findPropertyAccess;
        }
        JsonProperty.Access findPropertyAccess2 = this._secondary.findPropertyAccess(annotated);
        if (findPropertyAccess2 != null) {
            return findPropertyAccess2;
        }
        return JsonProperty.Access.AUTO;
    }

    public AnnotatedMethod resolveSetterConflict(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, AnnotatedMethod annotatedMethod2) {
        AnnotatedMethod resolveSetterConflict = this._primary.resolveSetterConflict(mapperConfig, annotatedMethod, annotatedMethod2);
        return resolveSetterConflict == null ? this._secondary.resolveSetterConflict(mapperConfig, annotatedMethod, annotatedMethod2) : resolveSetterConflict;
    }

    public JavaType refineSerializationType(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) throws JsonMappingException {
        return this._primary.refineSerializationType(mapperConfig, annotated, this._secondary.refineSerializationType(mapperConfig, annotated, javaType));
    }

    @Deprecated
    public Class<?> findSerializationType(Annotated annotated) {
        Class<?> findSerializationType = this._primary.findSerializationType(annotated);
        return findSerializationType == null ? this._secondary.findSerializationType(annotated) : findSerializationType;
    }

    @Deprecated
    public Class<?> findSerializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> findSerializationKeyType = this._primary.findSerializationKeyType(annotated, javaType);
        return findSerializationKeyType == null ? this._secondary.findSerializationKeyType(annotated, javaType) : findSerializationKeyType;
    }

    @Deprecated
    public Class<?> findSerializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> findSerializationContentType = this._primary.findSerializationContentType(annotated, javaType);
        return findSerializationContentType == null ? this._secondary.findSerializationContentType(annotated, javaType) : findSerializationContentType;
    }

    public String[] findSerializationPropertyOrder(AnnotatedClass annotatedClass) {
        String[] findSerializationPropertyOrder = this._primary.findSerializationPropertyOrder(annotatedClass);
        return findSerializationPropertyOrder == null ? this._secondary.findSerializationPropertyOrder(annotatedClass) : findSerializationPropertyOrder;
    }

    public Boolean findSerializationSortAlphabetically(Annotated annotated) {
        Boolean findSerializationSortAlphabetically = this._primary.findSerializationSortAlphabetically(annotated);
        return findSerializationSortAlphabetically == null ? this._secondary.findSerializationSortAlphabetically(annotated) : findSerializationSortAlphabetically;
    }

    public void findAndAddVirtualProperties(MapperConfig<?> mapperConfig, AnnotatedClass annotatedClass, List<BeanPropertyWriter> list) {
        this._primary.findAndAddVirtualProperties(mapperConfig, annotatedClass, list);
        this._secondary.findAndAddVirtualProperties(mapperConfig, annotatedClass, list);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r3 = r2._secondary.findNameForSerialization(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findNameForSerialization(com.fasterxml.jackson.databind.introspect.Annotated r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForSerialization(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForSerialization(r3)
            goto L_0x001c
        L_0x000f:
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.USE_DEFAULT
            if (r0 != r1) goto L_0x001c
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r3 = r1.findNameForSerialization(r3)
            if (r3 == 0) goto L_0x001c
            r0 = r3
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findNameForSerialization(com.fasterxml.jackson.databind.introspect.Annotated):com.fasterxml.jackson.databind.PropertyName");
    }

    public boolean hasAsValueAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAsValueAnnotation(annotatedMethod) || this._secondary.hasAsValueAnnotation(annotatedMethod);
    }

    public String findEnumValue(Enum<?> enumR) {
        String findEnumValue = this._primary.findEnumValue(enumR);
        return findEnumValue == null ? this._secondary.findEnumValue(enumR) : findEnumValue;
    }

    public String[] findEnumValues(Class<?> cls, Enum<?>[] enumArr, String[] strArr) {
        return this._primary.findEnumValues(cls, enumArr, this._secondary.findEnumValues(cls, enumArr, strArr));
    }

    public Object findDeserializer(Annotated annotated) {
        Object findDeserializer = this._primary.findDeserializer(annotated);
        return _isExplicitClassOrOb(findDeserializer, JsonDeserializer.None.class) ? findDeserializer : this._secondary.findDeserializer(annotated);
    }

    public Object findKeyDeserializer(Annotated annotated) {
        Object findKeyDeserializer = this._primary.findKeyDeserializer(annotated);
        return _isExplicitClassOrOb(findKeyDeserializer, KeyDeserializer.None.class) ? findKeyDeserializer : this._secondary.findKeyDeserializer(annotated);
    }

    public Object findContentDeserializer(Annotated annotated) {
        Object findContentDeserializer = this._primary.findContentDeserializer(annotated);
        return _isExplicitClassOrOb(findContentDeserializer, JsonDeserializer.None.class) ? findContentDeserializer : this._secondary.findContentDeserializer(annotated);
    }

    public Object findDeserializationConverter(Annotated annotated) {
        Object findDeserializationConverter = this._primary.findDeserializationConverter(annotated);
        return findDeserializationConverter == null ? this._secondary.findDeserializationConverter(annotated) : findDeserializationConverter;
    }

    public Object findDeserializationContentConverter(AnnotatedMember annotatedMember) {
        Object findDeserializationContentConverter = this._primary.findDeserializationContentConverter(annotatedMember);
        return findDeserializationContentConverter == null ? this._secondary.findDeserializationContentConverter(annotatedMember) : findDeserializationContentConverter;
    }

    public JavaType refineDeserializationType(MapperConfig<?> mapperConfig, Annotated annotated, JavaType javaType) throws JsonMappingException {
        return this._primary.refineDeserializationType(mapperConfig, annotated, this._secondary.refineDeserializationType(mapperConfig, annotated, javaType));
    }

    @Deprecated
    public Class<?> findDeserializationType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationType = this._primary.findDeserializationType(annotated, javaType);
        return findDeserializationType != null ? findDeserializationType : this._secondary.findDeserializationType(annotated, javaType);
    }

    @Deprecated
    public Class<?> findDeserializationKeyType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationKeyType = this._primary.findDeserializationKeyType(annotated, javaType);
        return findDeserializationKeyType == null ? this._secondary.findDeserializationKeyType(annotated, javaType) : findDeserializationKeyType;
    }

    @Deprecated
    public Class<?> findDeserializationContentType(Annotated annotated, JavaType javaType) {
        Class<?> findDeserializationContentType = this._primary.findDeserializationContentType(annotated, javaType);
        return findDeserializationContentType == null ? this._secondary.findDeserializationContentType(annotated, javaType) : findDeserializationContentType;
    }

    public Object findValueInstantiator(AnnotatedClass annotatedClass) {
        Object findValueInstantiator = this._primary.findValueInstantiator(annotatedClass);
        return findValueInstantiator == null ? this._secondary.findValueInstantiator(annotatedClass) : findValueInstantiator;
    }

    public Class<?> findPOJOBuilder(AnnotatedClass annotatedClass) {
        Class<?> findPOJOBuilder = this._primary.findPOJOBuilder(annotatedClass);
        return findPOJOBuilder == null ? this._secondary.findPOJOBuilder(annotatedClass) : findPOJOBuilder;
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig(AnnotatedClass annotatedClass) {
        JsonPOJOBuilder.Value findPOJOBuilderConfig = this._primary.findPOJOBuilderConfig(annotatedClass);
        return findPOJOBuilderConfig == null ? this._secondary.findPOJOBuilderConfig(annotatedClass) : findPOJOBuilderConfig;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r3 = r2._secondary.findNameForDeserialization(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName findNameForDeserialization(com.fasterxml.jackson.databind.introspect.Annotated r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._primary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForDeserialization(r3)
            if (r0 != 0) goto L_0x000f
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForDeserialization(r3)
            goto L_0x001c
        L_0x000f:
            com.fasterxml.jackson.databind.PropertyName r1 = com.fasterxml.jackson.databind.PropertyName.USE_DEFAULT
            if (r0 != r1) goto L_0x001c
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r2._secondary
            com.fasterxml.jackson.databind.PropertyName r3 = r1.findNameForDeserialization(r3)
            if (r3 == 0) goto L_0x001c
            r0 = r3
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair.findNameForDeserialization(com.fasterxml.jackson.databind.introspect.Annotated):com.fasterxml.jackson.databind.PropertyName");
    }

    public boolean hasAnySetterAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAnySetterAnnotation(annotatedMethod) || this._secondary.hasAnySetterAnnotation(annotatedMethod);
    }

    public boolean hasAnyGetterAnnotation(AnnotatedMethod annotatedMethod) {
        return this._primary.hasAnyGetterAnnotation(annotatedMethod) || this._secondary.hasAnyGetterAnnotation(annotatedMethod);
    }

    public boolean hasCreatorAnnotation(Annotated annotated) {
        return this._primary.hasCreatorAnnotation(annotated) || this._secondary.hasCreatorAnnotation(annotated);
    }

    public JsonCreator.Mode findCreatorBinding(Annotated annotated) {
        JsonCreator.Mode findCreatorBinding = this._primary.findCreatorBinding(annotated);
        if (findCreatorBinding != null) {
            return findCreatorBinding;
        }
        return this._secondary.findCreatorBinding(annotated);
    }

    /* access modifiers changed from: protected */
    public boolean _isExplicitClassOrOb(Object obj, Class<?> cls) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Class)) {
            return true;
        }
        Class<?> cls2 = (Class) obj;
        if (cls2 == cls || ClassUtil.isBogusClass(cls2)) {
            return false;
        }
        return true;
    }
}

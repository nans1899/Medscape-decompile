package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription extends BeanDescription {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected TypeBindings _bindings;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig<?> _config;
    protected ObjectIdInfo _objectIdInfo;
    protected final POJOPropertiesCollector _propCollector;
    protected List<BeanPropertyDefinition> _properties;

    protected BasicBeanDescription(POJOPropertiesCollector pOJOPropertiesCollector, JavaType javaType, AnnotatedClass annotatedClass) {
        super(javaType);
        AnnotationIntrospector annotationIntrospector;
        this._propCollector = pOJOPropertiesCollector;
        MapperConfig<?> config = pOJOPropertiesCollector.getConfig();
        this._config = config;
        if (config == null) {
            annotationIntrospector = null;
        } else {
            annotationIntrospector = config.getAnnotationIntrospector();
        }
        this._annotationIntrospector = annotationIntrospector;
        this._classInfo = annotatedClass;
    }

    protected BasicBeanDescription(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass, List<BeanPropertyDefinition> list) {
        super(javaType);
        AnnotationIntrospector annotationIntrospector = null;
        this._propCollector = null;
        this._config = mapperConfig;
        this._annotationIntrospector = mapperConfig != null ? mapperConfig.getAnnotationIntrospector() : annotationIntrospector;
        this._classInfo = annotatedClass;
        this._properties = list;
    }

    protected BasicBeanDescription(POJOPropertiesCollector pOJOPropertiesCollector) {
        this(pOJOPropertiesCollector, pOJOPropertiesCollector.getType(), pOJOPropertiesCollector.getClassDef());
        this._objectIdInfo = pOJOPropertiesCollector.getObjectIdInfo();
    }

    public static BasicBeanDescription forDeserialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        return new BasicBeanDescription(pOJOPropertiesCollector);
    }

    public static BasicBeanDescription forSerialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        return new BasicBeanDescription(pOJOPropertiesCollector);
    }

    public static BasicBeanDescription forOtherUse(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        return new BasicBeanDescription(mapperConfig, javaType, annotatedClass, Collections.emptyList());
    }

    /* access modifiers changed from: protected */
    public List<BeanPropertyDefinition> _properties() {
        if (this._properties == null) {
            this._properties = this._propCollector.getProperties();
        }
        return this._properties;
    }

    public boolean removeProperty(String str) {
        Iterator<BeanPropertyDefinition> it = _properties().iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(str)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean addProperty(BeanPropertyDefinition beanPropertyDefinition) {
        if (hasProperty(beanPropertyDefinition.getFullName())) {
            return false;
        }
        _properties().add(beanPropertyDefinition);
        return true;
    }

    public boolean hasProperty(PropertyName propertyName) {
        return findProperty(propertyName) != null;
    }

    public BeanPropertyDefinition findProperty(PropertyName propertyName) {
        for (BeanPropertyDefinition next : _properties()) {
            if (next.hasName(propertyName)) {
                return next;
            }
        }
        return null;
    }

    public AnnotatedClass getClassInfo() {
        return this._classInfo;
    }

    public ObjectIdInfo getObjectIdInfo() {
        return this._objectIdInfo;
    }

    public List<BeanPropertyDefinition> findProperties() {
        return _properties();
    }

    public AnnotatedMethod findJsonValueMethod() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        if (pOJOPropertiesCollector == null) {
            return null;
        }
        return pOJOPropertiesCollector.getJsonValueMethod();
    }

    public Set<String> getIgnoredPropertyNames() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        Set<String> ignoredPropertyNames = pOJOPropertiesCollector == null ? null : pOJOPropertiesCollector.getIgnoredPropertyNames();
        return ignoredPropertyNames == null ? Collections.emptySet() : ignoredPropertyNames;
    }

    public boolean hasKnownClassAnnotations() {
        return this._classInfo.hasAnnotations();
    }

    public Annotations getClassAnnotations() {
        return this._classInfo.getAnnotations();
    }

    @Deprecated
    public TypeBindings bindingsForBeanType() {
        return this._type.getBindings();
    }

    public JavaType resolveType(Type type) {
        if (type == null) {
            return null;
        }
        return this._config.getTypeFactory().constructType(type, this._type.getBindings());
    }

    public AnnotatedConstructor findDefaultConstructor() {
        return this._classInfo.getDefaultConstructor();
    }

    public AnnotatedMethod findAnySetter() throws IllegalArgumentException {
        Class<?> rawParameterType;
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        AnnotatedMethod anySetterMethod = pOJOPropertiesCollector == null ? null : pOJOPropertiesCollector.getAnySetterMethod();
        if (anySetterMethod == null || (rawParameterType = anySetterMethod.getRawParameterType(0)) == String.class || rawParameterType == Object.class) {
            return anySetterMethod;
        }
        throw new IllegalArgumentException("Invalid 'any-setter' annotation on method " + anySetterMethod.getName() + "(): first argument not of type String or Object, but " + rawParameterType.getName());
    }

    public Map<Object, AnnotatedMember> findInjectables() {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        if (pOJOPropertiesCollector != null) {
            return pOJOPropertiesCollector.getInjectables();
        }
        return Collections.emptyMap();
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._classInfo.getConstructors();
    }

    public Object instantiateBean(boolean z) {
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        if (defaultConstructor == null) {
            return null;
        }
        if (z) {
            defaultConstructor.fixAccess(this._config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        try {
            return defaultConstructor.getAnnotated().newInstance(new Object[0]);
        } catch (Exception e) {
            e = e;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw ((Error) e);
            } else if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            } else {
                throw new IllegalArgumentException("Failed to instantiate bean of type " + this._classInfo.getAnnotated().getName() + ": (" + e.getClass().getName() + ") " + e.getMessage(), e);
            }
        }
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._classInfo.findMethod(str, clsArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.findFormat(r2._classInfo);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.annotation.JsonFormat.Value findExpectedFormat(com.fasterxml.jackson.annotation.JsonFormat.Value r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._annotationIntrospector
            if (r0 == 0) goto L_0x000d
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r2._classInfo
            com.fasterxml.jackson.annotation.JsonFormat$Value r0 = r0.findFormat(r1)
            if (r0 == 0) goto L_0x000d
            return r0
        L_0x000d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.BasicBeanDescription.findExpectedFormat(com.fasterxml.jackson.annotation.JsonFormat$Value):com.fasterxml.jackson.annotation.JsonFormat$Value");
    }

    public Converter<Object, Object> findSerializationConverter() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return _createConverter(annotationIntrospector.findSerializationConverter(this._classInfo));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.findPropertyInclusion(r2._classInfo);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.annotation.JsonInclude.Value findPropertyInclusion(com.fasterxml.jackson.annotation.JsonInclude.Value r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._annotationIntrospector
            if (r0 == 0) goto L_0x0010
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r2._classInfo
            com.fasterxml.jackson.annotation.JsonInclude$Value r0 = r0.findPropertyInclusion(r1)
            if (r0 == 0) goto L_0x0010
            com.fasterxml.jackson.annotation.JsonInclude$Value r3 = r3.withOverrides(r0)
        L_0x0010:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.BasicBeanDescription.findPropertyInclusion(com.fasterxml.jackson.annotation.JsonInclude$Value):com.fasterxml.jackson.annotation.JsonInclude$Value");
    }

    public AnnotatedMember findAnyGetter() throws IllegalArgumentException {
        POJOPropertiesCollector pOJOPropertiesCollector = this._propCollector;
        AnnotatedMember anyGetter = pOJOPropertiesCollector == null ? null : pOJOPropertiesCollector.getAnyGetter();
        if (anyGetter != null) {
            if (!Map.class.isAssignableFrom(anyGetter.getRawType())) {
                throw new IllegalArgumentException("Invalid 'any-getter' annotation on method " + anyGetter.getName() + "(): return type is not instance of java.util.Map");
            }
        }
        return anyGetter;
    }

    public Map<String, AnnotatedMember> findBackReferenceProperties() {
        AnnotationIntrospector.ReferenceProperty findReferenceType;
        HashMap hashMap = null;
        for (BeanPropertyDefinition mutator : _properties()) {
            AnnotatedMember mutator2 = mutator.getMutator();
            if (!(mutator2 == null || (findReferenceType = this._annotationIntrospector.findReferenceType(mutator2)) == null || !findReferenceType.isBackReference())) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                String name = findReferenceType.getName();
                if (hashMap.put(name, mutator2) != null) {
                    throw new IllegalArgumentException("Multiple back-reference properties with name '" + name + "'");
                }
            }
        }
        return hashMap;
    }

    public List<AnnotatedMethod> getFactoryMethods() {
        List<AnnotatedMethod> staticMethods = this._classInfo.getStaticMethods();
        if (staticMethods.isEmpty()) {
            return staticMethods;
        }
        ArrayList arrayList = new ArrayList();
        for (AnnotatedMethod next : staticMethods) {
            if (isFactoryMethod(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public Constructor<?> findSingleArgConstructor(Class<?>... clsArr) {
        for (AnnotatedConstructor next : this._classInfo.getConstructors()) {
            if (next.getParameterCount() == 1) {
                Class<?> rawParameterType = next.getRawParameterType(0);
                for (Class<?> cls : clsArr) {
                    if (cls == rawParameterType) {
                        return next.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public Method findFactoryMethod(Class<?>... clsArr) {
        for (AnnotatedMethod next : this._classInfo.getStaticMethods()) {
            if (isFactoryMethod(next)) {
                Class<?> rawParameterType = next.getRawParameterType(0);
                for (Class<?> isAssignableFrom : clsArr) {
                    if (rawParameterType.isAssignableFrom(isAssignableFrom)) {
                        return next.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isFactoryMethod(AnnotatedMethod annotatedMethod) {
        Class<?> rawParameterType;
        if (!getBeanClass().isAssignableFrom(annotatedMethod.getRawReturnType())) {
            return false;
        }
        if (this._annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
            return true;
        }
        String name = annotatedMethod.getName();
        if ("valueOf".equals(name)) {
            return true;
        }
        if (!"fromString".equals(name) || 1 != annotatedMethod.getParameterCount() || ((rawParameterType = annotatedMethod.getRawParameterType(0)) != String.class && !CharSequence.class.isAssignableFrom(rawParameterType))) {
            return false;
        }
        return true;
    }

    @Deprecated
    public List<String> findCreatorPropertyNames() {
        List<PropertyName> findCreatorParameterNames = findCreatorParameterNames();
        if (findCreatorParameterNames.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(findCreatorParameterNames.size());
        for (PropertyName simpleName : findCreatorParameterNames) {
            arrayList.add(simpleName.getSimpleName());
        }
        return arrayList;
    }

    @Deprecated
    public List<PropertyName> findCreatorParameterNames() {
        PropertyName _findCreatorPropertyName;
        int i = 0;
        while (i < 2) {
            for (AnnotatedWithParams annotatedWithParams : i == 0 ? getConstructors() : getFactoryMethods()) {
                int parameterCount = annotatedWithParams.getParameterCount();
                if (parameterCount >= 1 && (_findCreatorPropertyName = _findCreatorPropertyName(annotatedWithParams.getParameter(0))) != null && !_findCreatorPropertyName.isEmpty()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(_findCreatorPropertyName);
                    for (int i2 = 1; i2 < parameterCount; i2++) {
                        arrayList.add(_findCreatorPropertyName(annotatedWithParams.getParameter(i2)));
                    }
                    return arrayList;
                }
            }
            i++;
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r3 = r2._annotationIntrospector.findImplicitPropertyName(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.PropertyName _findCreatorPropertyName(com.fasterxml.jackson.databind.introspect.AnnotatedParameter r3) {
        /*
            r2 = this;
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r2._annotationIntrospector
            com.fasterxml.jackson.databind.PropertyName r0 = r0.findNameForDeserialization(r3)
            if (r0 == 0) goto L_0x000e
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x0020
        L_0x000e:
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r2._annotationIntrospector
            java.lang.String r3 = r1.findImplicitPropertyName(r3)
            if (r3 == 0) goto L_0x0020
            boolean r1 = r3.isEmpty()
            if (r1 != 0) goto L_0x0020
            com.fasterxml.jackson.databind.PropertyName r0 = com.fasterxml.jackson.databind.PropertyName.construct(r3)
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.BasicBeanDescription._findCreatorPropertyName(com.fasterxml.jackson.databind.introspect.AnnotatedParameter):com.fasterxml.jackson.databind.PropertyName");
    }

    public Class<?> findPOJOBuilder() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findPOJOBuilder(this._classInfo);
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findPOJOBuilderConfig(this._classInfo);
    }

    public Converter<Object, Object> findDeserializationConverter() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return _createConverter(annotationIntrospector.findDeserializationConverter(this._classInfo));
    }

    public String findClassDescription() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findClassDescription(this._classInfo);
    }

    public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> collection, boolean z) {
        LinkedHashMap<String, AnnotatedField> linkedHashMap = new LinkedHashMap<>();
        for (BeanPropertyDefinition next : _properties()) {
            AnnotatedField field = next.getField();
            if (field != null) {
                String name = next.getName();
                if (collection == null || !collection.contains(name)) {
                    linkedHashMap.put(name, field);
                }
            }
        }
        return linkedHashMap;
    }

    public Converter<Object, Object> _createConverter(Object obj) {
        Converter<?, ?> converter = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Converter) {
            return (Converter) obj;
        }
        if (obj instanceof Class) {
            Class<Converter.None> cls = (Class) obj;
            if (cls == Converter.None.class || ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (Converter.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
                if (handlerInstantiator != null) {
                    converter = handlerInstantiator.converterInstance(this._config, this._classInfo, cls);
                }
                return converter == null ? (Converter) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : converter;
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<Converter>");
        }
        throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + obj.getClass().getName() + "; expected type Converter or Class<Converter> instead");
    }
}

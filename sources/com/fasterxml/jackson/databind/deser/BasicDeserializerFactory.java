package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
    private static final Class<?> CLASS_CHAR_BUFFER = CharSequence.class;
    private static final Class<?> CLASS_ITERABLE = Iterable.class;
    private static final Class<?> CLASS_MAP_ENTRY = Map.Entry.class;
    private static final Class<?> CLASS_OBJECT = Object.class;
    private static final Class<?> CLASS_STRING = String.class;
    protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks;
    static final HashMap<String, Class<? extends Map>> _mapFallbacks;
    protected final DeserializerFactoryConfig _factoryConfig;

    /* access modifiers changed from: protected */
    public abstract DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig);

    static {
        HashMap<String, Class<? extends Map>> hashMap = new HashMap<>();
        _mapFallbacks = hashMap;
        hashMap.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put(NavigableMap.class.getName(), TreeMap.class);
        _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        HashMap<String, Class<? extends Collection>> hashMap2 = new HashMap<>();
        _collectionFallbacks = hashMap2;
        hashMap2.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
    }

    protected BasicDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        this._factoryConfig = deserializerFactoryConfig;
    }

    public DeserializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers deserializers) {
        return withConfig(this._factoryConfig.withAdditionalDeserializers(deserializers));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(keyDeserializers));
    }

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        return withConfig(this._factoryConfig.withDeserializerModifier(beanDeserializerModifier));
    }

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        return withConfig(this._factoryConfig.withAbstractTypeResolver(abstractTypeResolver));
    }

    public final DeserializerFactory withValueInstantiators(ValueInstantiators valueInstantiators) {
        return withConfig(this._factoryConfig.withValueInstantiators(valueInstantiators));
    }

    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType _mapAbstractType2;
        while (true) {
            _mapAbstractType2 = _mapAbstractType2(deserializationConfig, javaType);
            if (_mapAbstractType2 == null) {
                return javaType;
            }
            Class<?> rawClass = javaType.getRawClass();
            Class<?> rawClass2 = _mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
            } else {
                javaType = _mapAbstractType2;
            }
        }
        throw new IllegalArgumentException("Invalid abstract type resolution from " + javaType + " to " + _mapAbstractType2 + ": latter is not a subtype of former");
    }

    private JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        if (!this._factoryConfig.hasAbstractTypeResolvers()) {
            return null;
        }
        for (AbstractTypeResolver findTypeMapping : this._factoryConfig.abstractTypeResolvers()) {
            JavaType findTypeMapping2 = findTypeMapping.findTypeMapping(deserializationConfig, javaType);
            if (findTypeMapping2 != null && findTypeMapping2.getRawClass() != rawClass) {
                return findTypeMapping2;
            }
        }
        return null;
    }

    public ValueInstantiator findValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        AnnotatedClass classInfo = beanDescription.getClassInfo();
        Object findValueInstantiator = deserializationContext.getAnnotationIntrospector().findValueInstantiator(classInfo);
        ValueInstantiator _valueInstantiatorInstance = findValueInstantiator != null ? _valueInstantiatorInstance(config, classInfo, findValueInstantiator) : null;
        if (_valueInstantiatorInstance == null && (_valueInstantiatorInstance = _findStdValueInstantiator(config, beanDescription)) == null) {
            _valueInstantiatorInstance = _constructDefaultValueInstantiator(deserializationContext, beanDescription);
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            for (ValueInstantiators next : this._factoryConfig.valueInstantiators()) {
                _valueInstantiatorInstance = next.findValueInstantiator(config, beanDescription, _valueInstantiatorInstance);
                if (_valueInstantiatorInstance == null) {
                    JsonParser parser = deserializationContext.getParser();
                    throw JsonMappingException.from(parser, "Broken registered ValueInstantiators (of type " + next.getClass().getName() + "): returned null ValueInstantiator");
                }
            }
        }
        if (_valueInstantiatorInstance.getIncompleteParameter() == null) {
            return _valueInstantiatorInstance;
        }
        AnnotatedParameter incompleteParameter = _valueInstantiatorInstance.getIncompleteParameter();
        AnnotatedWithParams owner = incompleteParameter.getOwner();
        throw new IllegalArgumentException("Argument #" + incompleteParameter.getIndex() + " of constructor " + owner + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
    }

    private ValueInstantiator _findStdValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        if (beanDescription.getBeanClass() == JsonLocation.class) {
            return new JsonLocationInstantiator();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        CreatorCollector creatorCollector = new CreatorCollector(beanDescription, deserializationContext.getConfig());
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        DeserializationConfig config = deserializationContext.getConfig();
        VisibilityChecker<?> findAutoDetectVisibility = annotationIntrospector.findAutoDetectVisibility(beanDescription.getClassInfo(), config.getDefaultVisibilityChecker());
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties = _findCreatorsFromProperties(deserializationContext, beanDescription);
        _addDeserializerFactoryMethods(deserializationContext, beanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector, _findCreatorsFromProperties);
        if (beanDescription.getType().isConcrete()) {
            _addDeserializerConstructors(deserializationContext, beanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector, _findCreatorsFromProperties);
        }
        return creatorCollector.constructValueInstantiator(config);
    }

    /* access modifiers changed from: protected */
    public Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> emptyMap = Collections.emptyMap();
        for (BeanPropertyDefinition next : beanDescription.findProperties()) {
            Iterator<AnnotatedParameter> constructorParameters = next.getConstructorParameters();
            while (true) {
                if (constructorParameters.hasNext()) {
                    AnnotatedParameter next2 = constructorParameters.next();
                    AnnotatedWithParams owner = next2.getOwner();
                    BeanPropertyDefinition[] beanPropertyDefinitionArr = emptyMap.get(owner);
                    int index = next2.getIndex();
                    if (beanPropertyDefinitionArr == null) {
                        if (emptyMap.isEmpty()) {
                            emptyMap = new LinkedHashMap<>();
                        }
                        beanPropertyDefinitionArr = new BeanPropertyDefinition[owner.getParameterCount()];
                        emptyMap.put(owner, beanPropertyDefinitionArr);
                    } else if (beanPropertyDefinitionArr[index] != null) {
                        throw new IllegalStateException("Conflict: parameter #" + index + " of " + owner + " bound to more than one property; " + beanPropertyDefinitionArr[index] + " vs " + next);
                    }
                    beanPropertyDefinitionArr[index] = next;
                }
            }
        }
        return emptyMap;
    }

    public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig deserializationConfig, Annotated annotated, Object obj) throws JsonMappingException {
        ValueInstantiator valueInstantiatorInstance;
        if (obj == null) {
            return null;
        }
        if (obj instanceof ValueInstantiator) {
            return (ValueInstantiator) obj;
        }
        if (obj instanceof Class) {
            Class cls = (Class) obj;
            if (ClassUtil.isBogusClass(cls)) {
                return null;
            }
            if (ValueInstantiator.class.isAssignableFrom(cls)) {
                HandlerInstantiator handlerInstantiator = deserializationConfig.getHandlerInstantiator();
                if (handlerInstantiator == null || (valueInstantiatorInstance = handlerInstantiator.valueInstantiatorInstance(deserializationConfig, annotated, cls)) == null) {
                    return (ValueInstantiator) ClassUtil.createInstance(cls, deserializationConfig.canOverrideAccessModifiers());
                }
                return valueInstantiatorInstance;
            }
            throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<ValueInstantiator>");
        }
        throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + obj.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerConstructors(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, Map<AnnotatedWithParams, BeanPropertyDefinition[]> map) throws JsonMappingException {
        Iterator<AnnotatedConstructor> it;
        BeanPropertyDefinition beanPropertyDefinition;
        PropertyName propertyName;
        int i;
        Iterator<AnnotatedConstructor> it2;
        int i2;
        SettableBeanProperty[] settableBeanPropertyArr;
        AnnotatedParameter annotatedParameter;
        BeanPropertyDefinition beanPropertyDefinition2;
        PropertyName propertyName2;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        AnnotatedConstructor findDefaultConstructor = beanDescription.findDefaultConstructor();
        if (findDefaultConstructor != null && (!creatorCollector.hasDefaultCreator() || annotationIntrospector2.hasCreatorAnnotation(findDefaultConstructor))) {
            creatorCollector2.setDefaultCreator(findDefaultConstructor);
        }
        Iterator<AnnotatedConstructor> it3 = beanDescription.getConstructors().iterator();
        LinkedList linkedList = null;
        while (it3.hasNext()) {
            AnnotatedConstructor next = it3.next();
            boolean hasCreatorAnnotation = annotationIntrospector2.hasCreatorAnnotation(next);
            BeanPropertyDefinition[] beanPropertyDefinitionArr = map.get(next);
            int parameterCount = next.getParameterCount();
            if (parameterCount == 1) {
                if (beanPropertyDefinitionArr == null) {
                    beanPropertyDefinition2 = null;
                } else {
                    beanPropertyDefinition2 = beanPropertyDefinitionArr[0];
                }
                if (_checkIfCreatorPropertyBased(annotationIntrospector2, next, beanPropertyDefinition2)) {
                    SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[1];
                    if (beanPropertyDefinition2 == null) {
                        propertyName2 = null;
                    } else {
                        propertyName2 = beanPropertyDefinition2.getFullName();
                    }
                    AnnotatedParameter parameter = next.getParameter(0);
                    SettableBeanProperty[] settableBeanPropertyArr3 = settableBeanPropertyArr2;
                    settableBeanPropertyArr3[0] = constructCreatorProperty(deserializationContext, beanDescription, propertyName2, 0, parameter, annotationIntrospector2.findInjectableValueId(parameter));
                    creatorCollector2.addPropertyCreator(next, hasCreatorAnnotation, settableBeanPropertyArr3);
                } else {
                    BeanPropertyDefinition beanPropertyDefinition3 = beanPropertyDefinition2;
                    _handleSingleArgumentConstructor(deserializationContext, beanDescription, visibilityChecker, annotationIntrospector, creatorCollector, next, hasCreatorAnnotation, visibilityChecker.isCreatorVisible((AnnotatedMember) next));
                    if (beanPropertyDefinition3 != null) {
                        ((POJOPropertyBuilder) beanPropertyDefinition3).removeConstructors();
                    }
                }
                it = it3;
            } else {
                VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
                int i3 = 0;
                SettableBeanProperty[] settableBeanPropertyArr4 = new SettableBeanProperty[parameterCount];
                AnnotatedParameter annotatedParameter2 = null;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (i4 < parameterCount) {
                    AnnotatedParameter parameter2 = next.getParameter(i4);
                    if (beanPropertyDefinitionArr == null) {
                        beanPropertyDefinition = null;
                    } else {
                        beanPropertyDefinition = beanPropertyDefinitionArr[i4];
                    }
                    Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter2);
                    if (beanPropertyDefinition == null) {
                        propertyName = null;
                    } else {
                        propertyName = beanPropertyDefinition.getFullName();
                    }
                    if (beanPropertyDefinition == null || !beanPropertyDefinition.isExplicitlyNamed()) {
                        AnnotatedParameter annotatedParameter3 = parameter2;
                        i = i4;
                        settableBeanPropertyArr = settableBeanPropertyArr4;
                        i2 = parameterCount;
                        it2 = it3;
                        annotatedParameter = annotatedParameter2;
                        if (findInjectableValueId != null) {
                            i6++;
                            settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i, annotatedParameter3, findInjectableValueId);
                        } else {
                            AnnotatedParameter annotatedParameter4 = annotatedParameter3;
                            if (annotationIntrospector2.findUnwrappingNameTransformer(annotatedParameter4) != null) {
                                settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, UNWRAPPED_CREATOR_PARAM_NAME, i, annotatedParameter4, (Object) null);
                                i3++;
                            } else if (hasCreatorAnnotation && propertyName != null && !propertyName.isEmpty()) {
                                i5++;
                                settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i, annotatedParameter4, findInjectableValueId);
                            } else if (annotatedParameter == null) {
                                annotatedParameter2 = annotatedParameter4;
                                i4 = i + 1;
                                settableBeanPropertyArr4 = settableBeanPropertyArr;
                                parameterCount = i2;
                                it3 = it2;
                                VisibilityChecker<?> visibilityChecker3 = visibilityChecker;
                                Map<AnnotatedWithParams, BeanPropertyDefinition[]> map2 = map;
                            }
                        }
                    } else {
                        i3++;
                        it2 = it3;
                        annotatedParameter = annotatedParameter2;
                        i = i4;
                        settableBeanPropertyArr = settableBeanPropertyArr4;
                        i2 = parameterCount;
                        settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i4, parameter2, findInjectableValueId);
                    }
                    annotatedParameter2 = annotatedParameter;
                    i4 = i + 1;
                    settableBeanPropertyArr4 = settableBeanPropertyArr;
                    parameterCount = i2;
                    it3 = it2;
                    VisibilityChecker<?> visibilityChecker32 = visibilityChecker;
                    Map<AnnotatedWithParams, BeanPropertyDefinition[]> map22 = map;
                }
                SettableBeanProperty[] settableBeanPropertyArr5 = settableBeanPropertyArr4;
                int i7 = parameterCount;
                it = it3;
                AnnotatedParameter annotatedParameter5 = annotatedParameter2;
                int i8 = i3 + i5;
                if (hasCreatorAnnotation || i3 > 0 || i6 > 0) {
                    if (i8 + i6 == i7) {
                        creatorCollector2.addPropertyCreator(next, hasCreatorAnnotation, settableBeanPropertyArr5);
                    } else if (i3 == 0 && i6 + 1 == i7) {
                        creatorCollector2.addDelegatingCreator(next, hasCreatorAnnotation, settableBeanPropertyArr5);
                    } else {
                        PropertyName _findImplicitParamName = _findImplicitParamName(annotatedParameter5, annotationIntrospector2);
                        if (_findImplicitParamName == null || _findImplicitParamName.isEmpty()) {
                            int index = annotatedParameter5.getIndex();
                            if (index != 0 || !ClassUtil.isNonStaticInnerClass(next.getDeclaringClass())) {
                                throw new IllegalArgumentException("Argument #" + index + " of constructor " + next + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
                            }
                            throw new IllegalArgumentException("Non-static inner classes like " + next.getDeclaringClass().getName() + " can not use @JsonCreator for constructors");
                        }
                    }
                }
                if (!creatorCollector.hasDefaultCreator()) {
                    if (linkedList == null) {
                        linkedList = new LinkedList();
                    }
                    linkedList.add(next);
                }
            }
            it3 = it;
        }
        if (linkedList != null && !creatorCollector.hasDelegatingCreator() && !creatorCollector.hasPropertyBasedCreator()) {
            _checkImplicitlyNamedConstructors(deserializationContext, beanDescription, visibilityChecker, annotationIntrospector, creatorCollector, linkedList);
        }
    }

    /* access modifiers changed from: protected */
    public void _checkImplicitlyNamedConstructors(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, List<AnnotatedConstructor> list) throws JsonMappingException {
        int i;
        Iterator<AnnotatedConstructor> it = list.iterator();
        AnnotatedConstructor annotatedConstructor = null;
        AnnotatedConstructor annotatedConstructor2 = null;
        SettableBeanProperty[] settableBeanPropertyArr = null;
        while (true) {
            if (!it.hasNext()) {
                annotatedConstructor = annotatedConstructor2;
                break;
            }
            AnnotatedConstructor next = it.next();
            if (visibilityChecker.isCreatorVisible((AnnotatedMember) next)) {
                int parameterCount = next.getParameterCount();
                SettableBeanProperty[] settableBeanPropertyArr2 = new SettableBeanProperty[parameterCount];
                int i2 = 0;
                while (true) {
                    if (i2 < parameterCount) {
                        AnnotatedParameter parameter = next.getParameter(i2);
                        PropertyName _findParamName = _findParamName(parameter, annotationIntrospector);
                        if (_findParamName == null || _findParamName.isEmpty()) {
                            break;
                        }
                        settableBeanPropertyArr2[i2] = constructCreatorProperty(deserializationContext, beanDescription, _findParamName, parameter.getIndex(), parameter, (Object) null);
                        i2++;
                    } else if (annotatedConstructor2 != null) {
                        break;
                    } else {
                        annotatedConstructor2 = next;
                        settableBeanPropertyArr = settableBeanPropertyArr2;
                    }
                }
            }
        }
        if (annotatedConstructor != null) {
            creatorCollector.addPropertyCreator(annotatedConstructor, false, settableBeanPropertyArr);
            BasicBeanDescription basicBeanDescription = (BasicBeanDescription) beanDescription;
            for (SettableBeanProperty settableBeanProperty : settableBeanPropertyArr) {
                PropertyName fullName = settableBeanProperty.getFullName();
                if (!basicBeanDescription.hasProperty(fullName)) {
                    basicBeanDescription.addProperty(SimpleBeanPropertyDefinition.construct((MapperConfig<?>) deserializationContext.getConfig(), settableBeanProperty.getMember(), fullName));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _checkIfCreatorPropertyBased(AnnotationIntrospector annotationIntrospector, AnnotatedWithParams annotatedWithParams, BeanPropertyDefinition beanPropertyDefinition) {
        String name;
        JsonCreator.Mode findCreatorBinding = annotationIntrospector.findCreatorBinding(annotatedWithParams);
        if (findCreatorBinding == JsonCreator.Mode.PROPERTIES) {
            return true;
        }
        if (findCreatorBinding == JsonCreator.Mode.DELEGATING) {
            return false;
        }
        if ((beanPropertyDefinition != null && beanPropertyDefinition.isExplicitlyNamed()) || annotationIntrospector.findInjectableValueId(annotatedWithParams.getParameter(0)) != null) {
            return true;
        }
        if (beanPropertyDefinition == null || (name = beanPropertyDefinition.getName()) == null || name.isEmpty() || !beanPropertyDefinition.couldSerialize()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentConstructor(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedConstructor annotatedConstructor, boolean z, boolean z2) throws JsonMappingException {
        Class<?> rawParameterType = annotatedConstructor.getRawParameterType(0);
        if (rawParameterType == String.class || rawParameterType == CharSequence.class) {
            if (z || z2) {
                creatorCollector.addStringCreator(annotatedConstructor, z);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z || z2) {
                creatorCollector.addIntCreator(annotatedConstructor, z);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z || z2) {
                creatorCollector.addLongCreator(annotatedConstructor, z);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z || z2) {
                creatorCollector.addDoubleCreator(annotatedConstructor, z);
            }
            return true;
        } else if (rawParameterType == Boolean.TYPE || rawParameterType == Boolean.class) {
            if (z || z2) {
                creatorCollector.addBooleanCreator(annotatedConstructor, z);
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            creatorCollector.addDelegatingCreator(annotatedConstructor, z, (SettableBeanProperty[]) null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerFactoryMethods(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, Map<AnnotatedWithParams, BeanPropertyDefinition[]> map) throws JsonMappingException {
        BeanPropertyDefinition beanPropertyDefinition;
        PropertyName propertyName;
        BasicDeserializerFactory basicDeserializerFactory;
        BeanPropertyDefinition beanPropertyDefinition2;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        DeserializationConfig config = deserializationContext.getConfig();
        for (AnnotatedMethod next : beanDescription.getFactoryMethods()) {
            boolean hasCreatorAnnotation = annotationIntrospector2.hasCreatorAnnotation(next);
            int parameterCount = next.getParameterCount();
            if (parameterCount != 0) {
                BeanPropertyDefinition[] beanPropertyDefinitionArr = map.get(next);
                int i = 0;
                if (parameterCount == 1) {
                    if (beanPropertyDefinitionArr == null) {
                        basicDeserializerFactory = this;
                        beanPropertyDefinition2 = null;
                    } else {
                        beanPropertyDefinition2 = beanPropertyDefinitionArr[0];
                        basicDeserializerFactory = this;
                    }
                    if (!basicDeserializerFactory._checkIfCreatorPropertyBased(annotationIntrospector2, next, beanPropertyDefinition2)) {
                        _handleSingleArgumentFactory(config, beanDescription, visibilityChecker, annotationIntrospector, creatorCollector, next, hasCreatorAnnotation);
                    }
                } else {
                    if (!hasCreatorAnnotation) {
                        continue;
                    }
                }
                SettableBeanProperty[] settableBeanPropertyArr = new SettableBeanProperty[parameterCount];
                AnnotatedParameter annotatedParameter = null;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i < parameterCount) {
                    AnnotatedParameter parameter = next.getParameter(i);
                    if (beanPropertyDefinitionArr == null) {
                        beanPropertyDefinition = null;
                    } else {
                        beanPropertyDefinition = beanPropertyDefinitionArr[i];
                    }
                    Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter);
                    if (beanPropertyDefinition == null) {
                        propertyName = null;
                    } else {
                        propertyName = beanPropertyDefinition.getFullName();
                    }
                    if (beanPropertyDefinition == null || !beanPropertyDefinition.isExplicitlyNamed()) {
                        AnnotatedParameter annotatedParameter2 = parameter;
                        if (findInjectableValueId != null) {
                            i4++;
                            settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i, annotatedParameter2, findInjectableValueId);
                        } else {
                            AnnotatedParameter annotatedParameter3 = annotatedParameter2;
                            if (annotationIntrospector2.findUnwrappingNameTransformer(annotatedParameter3) != null) {
                                settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, UNWRAPPED_CREATOR_PARAM_NAME, i, annotatedParameter3, (Object) null);
                                i3++;
                            } else {
                                AnnotatedParameter annotatedParameter4 = annotatedParameter3;
                                if (hasCreatorAnnotation && propertyName != null && !propertyName.isEmpty()) {
                                    i3++;
                                    settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i, annotatedParameter4, findInjectableValueId);
                                } else if (annotatedParameter == null) {
                                    annotatedParameter = annotatedParameter4;
                                }
                            }
                        }
                    } else {
                        i2++;
                        settableBeanPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, propertyName, i, parameter, findInjectableValueId);
                    }
                    i++;
                }
                int i5 = i2 + i3;
                if (hasCreatorAnnotation || i2 > 0 || i4 > 0) {
                    if (i5 + i4 == parameterCount) {
                        creatorCollector2.addPropertyCreator(next, hasCreatorAnnotation, settableBeanPropertyArr);
                    } else if (i2 == 0 && i4 + 1 == parameterCount) {
                        creatorCollector2.addDelegatingCreator(next, hasCreatorAnnotation, settableBeanPropertyArr);
                    } else {
                        throw new IllegalArgumentException("Argument #" + annotatedParameter.getIndex() + " of factory method " + next + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
                    }
                }
            } else if (hasCreatorAnnotation) {
                creatorCollector2.setDefaultCreator(next);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentFactory(DeserializationConfig deserializationConfig, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedMethod annotatedMethod, boolean z) throws JsonMappingException {
        Class<?> rawParameterType = annotatedMethod.getRawParameterType(0);
        if (rawParameterType == String.class || rawParameterType == CharSequence.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addStringCreator(annotatedMethod, z);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addIntCreator(annotatedMethod, z);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addLongCreator(annotatedMethod, z);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addDoubleCreator(annotatedMethod, z);
            }
            return true;
        } else if (rawParameterType == Boolean.TYPE || rawParameterType == Boolean.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addBooleanCreator(annotatedMethod, z);
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            creatorCollector.addDelegatingCreator(annotatedMethod, z, (SettableBeanProperty[]) null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructCreatorProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, PropertyName propertyName, int i, AnnotatedParameter annotatedParameter, Object obj) throws JsonMappingException {
        PropertyMetadata construct;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanDescription beanDescription2 = beanDescription;
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        DeserializationConfig config = deserializationContext.getConfig();
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            construct = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        } else {
            Boolean hasRequiredMarker = annotationIntrospector.hasRequiredMarker(annotatedParameter2);
            construct = PropertyMetadata.construct(hasRequiredMarker != null && hasRequiredMarker.booleanValue(), annotationIntrospector.findPropertyDescription(annotatedParameter2), annotationIntrospector.findPropertyIndex(annotatedParameter2), annotationIntrospector.findPropertyDefaultValue(annotatedParameter2));
        }
        PropertyMetadata propertyMetadata = construct;
        JavaType resolveType = beanDescription2.resolveType(annotatedParameter.getParameterType());
        BeanProperty.Std std = new BeanProperty.Std(propertyName, resolveType, annotationIntrospector.findWrapperName(annotatedParameter2), beanDescription.getClassAnnotations(), (AnnotatedMember) annotatedParameter, propertyMetadata);
        JavaType resolveType2 = resolveType(deserializationContext2, beanDescription2, resolveType, annotatedParameter2);
        if (resolveType2 != resolveType) {
            std = std.withType(resolveType2);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, annotatedParameter2);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext2, annotatedParameter2, resolveType2);
        TypeDeserializer typeDeserializer = (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, modifyTypeByAnnotation);
        }
        PropertyName wrapperName = std.getWrapperName();
        CreatorProperty creatorProperty = r2;
        CreatorProperty creatorProperty2 = new CreatorProperty(propertyName, modifyTypeByAnnotation, wrapperName, typeDeserializer, beanDescription.getClassAnnotations(), annotatedParameter, i, obj, propertyMetadata);
        return findDeserializerFromAnnotation != null ? creatorProperty.withValueDeserializer(deserializationContext2.handlePrimaryContextualization(findDeserializerFromAnnotation, creatorProperty, modifyTypeByAnnotation)) : creatorProperty;
    }

    /* access modifiers changed from: protected */
    public PropertyName _findParamName(AnnotatedParameter annotatedParameter, AnnotationIntrospector annotationIntrospector) {
        if (annotatedParameter == null || annotationIntrospector == null) {
            return null;
        }
        PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedParameter);
        if (findNameForDeserialization != null) {
            return findNameForDeserialization;
        }
        String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedParameter);
        if (findImplicitPropertyName == null || findImplicitPropertyName.isEmpty()) {
            return null;
        }
        return PropertyName.construct(findImplicitPropertyName);
    }

    /* access modifiers changed from: protected */
    public PropertyName _findImplicitParamName(AnnotatedParameter annotatedParameter, AnnotationIntrospector annotationIntrospector) {
        String findImplicitPropertyName = annotationIntrospector.findImplicitPropertyName(annotatedParameter);
        if (findImplicitPropertyName == null || findImplicitPropertyName.isEmpty()) {
            return null;
        }
        return PropertyName.construct(findImplicitPropertyName);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public PropertyName _findExplicitParamName(AnnotatedParameter annotatedParameter, AnnotationIntrospector annotationIntrospector) {
        if (annotatedParameter == null || annotationIntrospector == null) {
            return null;
        }
        return annotationIntrospector.findNameForDeserialization(annotatedParameter);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public boolean _hasExplicitParamName(AnnotatedParameter annotatedParameter, AnnotationIntrospector annotationIntrospector) {
        PropertyName findNameForDeserialization;
        if (annotatedParameter == null || annotationIntrospector == null || (findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedParameter)) == null || !findNameForDeserialization.hasSimpleName()) {
            return false;
        }
        return true;
    }

    public JsonDeserializer<?> createArrayDeserializer(DeserializationContext deserializationContext, ArrayType arrayType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, config, beanDescription, typeDeserializer2, jsonDeserializer);
        if (_findCustomArrayDeserializer == null) {
            if (jsonDeserializer == null) {
                Class<?> rawClass = contentType.getRawClass();
                if (contentType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(rawClass);
                }
                if (rawClass == String.class) {
                    return StringArrayDeserializer.instance;
                }
            }
            _findCustomArrayDeserializer = new ObjectArrayDeserializer(arrayType, jsonDeserializer, typeDeserializer2);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyArrayDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomArrayDeserializer = modifyArrayDeserializer.modifyArrayDeserializer(config, arrayType, beanDescription, _findCustomArrayDeserializer);
            }
        }
        return _findCustomArrayDeserializer;
    }

    public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext deserializationContext, CollectionType collectionType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType contentType = collectionType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType, config, beanDescription, typeDeserializer2, jsonDeserializer);
        if (_findCustomCollectionDeserializer == null) {
            Class<?> rawClass = collectionType.getRawClass();
            if (jsonDeserializer == null && EnumSet.class.isAssignableFrom(rawClass)) {
                _findCustomCollectionDeserializer = new EnumSetDeserializer(contentType, (JsonDeserializer<?>) null);
            }
        }
        if (_findCustomCollectionDeserializer == null) {
            if (collectionType.isInterface() || collectionType.isAbstract()) {
                CollectionType _mapAbstractCollectionType = _mapAbstractCollectionType(collectionType, config);
                if (_mapAbstractCollectionType != null) {
                    beanDescription = config.introspectForCreation(_mapAbstractCollectionType);
                    collectionType = _mapAbstractCollectionType;
                } else if (collectionType.getTypeHandler() != null) {
                    _findCustomCollectionDeserializer = AbstractDeserializer.constructForNonPOJO(beanDescription);
                } else {
                    throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + collectionType);
                }
            }
            if (_findCustomCollectionDeserializer == null) {
                ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
                if (!findValueInstantiator.canCreateUsingDefault() && collectionType.getRawClass() == ArrayBlockingQueue.class) {
                    return new ArrayBlockingQueueDeserializer(collectionType, jsonDeserializer, typeDeserializer2, findValueInstantiator);
                }
                if (contentType.getRawClass() == String.class) {
                    _findCustomCollectionDeserializer = new StringCollectionDeserializer(collectionType, jsonDeserializer, findValueInstantiator);
                } else {
                    _findCustomCollectionDeserializer = new CollectionDeserializer(collectionType, jsonDeserializer, typeDeserializer2, findValueInstantiator);
                }
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionDeserializer = modifyCollectionDeserializer.modifyCollectionDeserializer(config, collectionType, beanDescription, _findCustomCollectionDeserializer);
            }
        }
        return _findCustomCollectionDeserializer;
    }

    /* access modifiers changed from: protected */
    public CollectionType _mapAbstractCollectionType(JavaType javaType, DeserializationConfig deserializationConfig) {
        Class cls = _collectionFallbacks.get(javaType.getRawClass().getName());
        if (cls == null) {
            return null;
        }
        return (CollectionType) deserializationConfig.constructSpecializedType(javaType, cls);
    }

    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext deserializationContext, CollectionLikeType collectionLikeType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType contentType = collectionLikeType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        JsonDeserializer<?> _findCustomCollectionLikeDeserializer = _findCustomCollectionLikeDeserializer(collectionLikeType, config, beanDescription, typeDeserializer == null ? findTypeDeserializer(config, contentType) : typeDeserializer, jsonDeserializer);
        if (_findCustomCollectionLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionLikeDeserializer = modifyCollectionLikeDeserializer.modifyCollectionLikeDeserializer(config, collectionLikeType, beanDescription, _findCustomCollectionLikeDeserializer);
            }
        }
        return _findCustomCollectionLikeDeserializer;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonDeserializer<?> createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext r18, com.fasterxml.jackson.databind.type.MapType r19, com.fasterxml.jackson.databind.BeanDescription r20) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r17 = this;
            r7 = r17
            r8 = r19
            com.fasterxml.jackson.databind.DeserializationConfig r9 = r18.getConfig()
            com.fasterxml.jackson.databind.JavaType r10 = r19.getKeyType()
            com.fasterxml.jackson.databind.JavaType r0 = r19.getContentType()
            java.lang.Object r1 = r0.getValueHandler()
            r15 = r1
            com.fasterxml.jackson.databind.JsonDeserializer r15 = (com.fasterxml.jackson.databind.JsonDeserializer) r15
            java.lang.Object r1 = r10.getValueHandler()
            r14 = r1
            com.fasterxml.jackson.databind.KeyDeserializer r14 = (com.fasterxml.jackson.databind.KeyDeserializer) r14
            java.lang.Object r1 = r0.getTypeHandler()
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r1 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r1
            if (r1 != 0) goto L_0x002c
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r0 = r7.findTypeDeserializer(r9, r0)
            r13 = r0
            goto L_0x002d
        L_0x002c:
            r13 = r1
        L_0x002d:
            r0 = r17
            r1 = r19
            r2 = r9
            r3 = r20
            r4 = r14
            r5 = r13
            r6 = r15
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r0._findCustomMapDeserializer(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x00d7
            java.lang.Class r1 = r19.getRawClass()
            java.lang.Class<java.util.EnumMap> r2 = java.util.EnumMap.class
            boolean r2 = r2.isAssignableFrom(r1)
            if (r2 == 0) goto L_0x0064
            java.lang.Class r0 = r10.getRawClass()
            if (r0 == 0) goto L_0x005c
            boolean r0 = r0.isEnum()
            if (r0 == 0) goto L_0x005c
            com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer r0 = new com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer
            r2 = 0
            r0.<init>(r8, r2, r15, r13)
            goto L_0x0064
        L_0x005c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Can not construct EnumMap; generic (key) type not available"
            r0.<init>(r1)
            throw r0
        L_0x0064:
            if (r0 != 0) goto L_0x00d7
            boolean r2 = r19.isInterface()
            if (r2 != 0) goto L_0x0076
            boolean r2 = r19.isAbstract()
            if (r2 == 0) goto L_0x0073
            goto L_0x0076
        L_0x0073:
            r2 = r20
            goto L_0x009b
        L_0x0076:
            java.util.HashMap<java.lang.String, java.lang.Class<? extends java.util.Map>> r2 = _mapFallbacks
            java.lang.String r1 = r1.getName()
            java.lang.Object r1 = r2.get(r1)
            java.lang.Class r1 = (java.lang.Class) r1
            if (r1 == 0) goto L_0x0090
            com.fasterxml.jackson.databind.JavaType r1 = r9.constructSpecializedType(r8, r1)
            com.fasterxml.jackson.databind.type.MapType r1 = (com.fasterxml.jackson.databind.type.MapType) r1
            com.fasterxml.jackson.databind.BeanDescription r2 = r9.introspectForCreation(r1)
            r8 = r1
            goto L_0x009b
        L_0x0090:
            java.lang.Object r0 = r19.getTypeHandler()
            if (r0 == 0) goto L_0x00c0
            com.fasterxml.jackson.databind.deser.AbstractDeserializer r0 = com.fasterxml.jackson.databind.deser.AbstractDeserializer.constructForNonPOJO(r20)
            goto L_0x0073
        L_0x009b:
            if (r0 != 0) goto L_0x00d9
            r1 = r18
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r7.findValueInstantiator(r1, r2)
            com.fasterxml.jackson.databind.deser.std.MapDeserializer r1 = new com.fasterxml.jackson.databind.deser.std.MapDeserializer
            r11 = r1
            r12 = r8
            r3 = r13
            r13 = r0
            r16 = r3
            r11.<init>((com.fasterxml.jackson.databind.JavaType) r12, (com.fasterxml.jackson.databind.deser.ValueInstantiator) r13, (com.fasterxml.jackson.databind.KeyDeserializer) r14, (com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>) r15, (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r16)
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r9.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r3 = r2.getClassInfo()
            r4 = 0
            java.lang.String[] r0 = r0.findPropertiesToIgnore(r3, r4)
            r1.setIgnorableProperties(r0)
            r0 = r1
            goto L_0x00d9
        L_0x00c0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Can not find a deserializer for non-concrete Map type "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00d7:
            r2 = r20
        L_0x00d9:
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r7._factoryConfig
            boolean r1 = r1.hasDeserializerModifiers()
            if (r1 == 0) goto L_0x00fc
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r7._factoryConfig
            java.lang.Iterable r1 = r1.deserializerModifiers()
            java.util.Iterator r1 = r1.iterator()
        L_0x00eb:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x00fc
            java.lang.Object r3 = r1.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r3 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r3
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3.modifyMapDeserializer(r9, r8, r2, r0)
            goto L_0x00eb
        L_0x00fc:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.type.MapType, com.fasterxml.jackson.databind.BeanDescription):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext deserializationContext, MapLikeType mapLikeType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType keyType = mapLikeType.getKeyType();
        JavaType contentType = mapLikeType.getContentType();
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomMapLikeDeserializer = _findCustomMapLikeDeserializer(mapLikeType, config, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
        if (_findCustomMapLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyMapLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomMapLikeDeserializer = modifyMapLikeDeserializer.modifyMapLikeDeserializer(config, mapLikeType, beanDescription, _findCustomMapLikeDeserializer);
            }
        }
        return _findCustomMapLikeDeserializer;
    }

    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, beanDescription);
        if (_findCustomEnumDeserializer == null) {
            Iterator<AnnotatedMethod> it = beanDescription.getFactoryMethods().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnnotatedMethod next = it.next();
                if (deserializationContext.getAnnotationIntrospector().hasCreatorAnnotation(next)) {
                    if (next.getParameterCount() != 1 || !next.getRawReturnType().isAssignableFrom(rawClass)) {
                        throw new IllegalArgumentException("Unsuitable method (" + next + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
                    }
                    _findCustomEnumDeserializer = EnumDeserializer.deserializerForCreator(config, rawClass, next);
                }
            }
            if (_findCustomEnumDeserializer == null) {
                _findCustomEnumDeserializer = new EnumDeserializer(constructEnumResolver(rawClass, config, beanDescription.findJsonValueMethod()));
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyEnumDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomEnumDeserializer = modifyEnumDeserializer.modifyEnumDeserializer(config, javaType, beanDescription, _findCustomEnumDeserializer);
            }
        }
        return _findCustomEnumDeserializer;
    }

    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanDescription);
        if (_findCustomTreeNodeDeserializer != null) {
            return _findCustomTreeNodeDeserializer;
        }
        return JsonNodeDeserializer.getDeserializer(rawClass);
    }

    public JsonDeserializer<?> createReferenceDeserializer(DeserializationContext deserializationContext, ReferenceType referenceType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType contentType = referenceType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomReferenceDeserializer = _findCustomReferenceDeserializer(referenceType, config, beanDescription, typeDeserializer, jsonDeserializer);
        if (_findCustomReferenceDeserializer == null && AtomicReference.class.isAssignableFrom(referenceType.getRawClass())) {
            return new AtomicReferenceDeserializer(referenceType.getReferencedType(), typeDeserializer, _findCustomReferenceDeserializer);
        }
        if (_findCustomReferenceDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyReferenceDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomReferenceDeserializer = modifyReferenceDeserializer.modifyReferenceDeserializer(config, referenceType, beanDescription, _findCustomReferenceDeserializer);
            }
        }
        return _findCustomReferenceDeserializer;
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType mapAbstractType;
        AnnotatedClass classInfo = deserializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder findTypeResolver = deserializationConfig.getAnnotationIntrospector().findTypeResolver(deserializationConfig, classInfo, javaType);
        Collection<NamedType> collection = null;
        if (findTypeResolver == null) {
            findTypeResolver = deserializationConfig.getDefaultTyper(javaType);
            if (findTypeResolver == null) {
                return null;
            }
        } else {
            collection = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, classInfo);
        }
        if (findTypeResolver.getDefaultImpl() == null && javaType.isAbstract() && (mapAbstractType = mapAbstractType(deserializationConfig, javaType)) != null && mapAbstractType.getRawClass() != javaType.getRawClass()) {
            findTypeResolver = findTypeResolver.defaultImpl(mapAbstractType.getRawClass());
        }
        return findTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, collection);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findDeserializer(javaType, deserializationContext.getConfig(), beanDescription);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x001f A[LOOP:0: B:3:0x001f->B:6:0x002f, LOOP_START, PHI: r2 
      PHI: (r2v7 com.fasterxml.jackson.databind.KeyDeserializer) = (r2v0 com.fasterxml.jackson.databind.KeyDeserializer), (r2v10 com.fasterxml.jackson.databind.KeyDeserializer) binds: [B:2:0x000d, B:6:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.KeyDeserializer createKeyDeserializer(com.fasterxml.jackson.databind.DeserializationContext r6, com.fasterxml.jackson.databind.JavaType r7) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r5 = this;
            com.fasterxml.jackson.databind.DeserializationConfig r0 = r6.getConfig()
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r5._factoryConfig
            boolean r1 = r1.hasKeyDeserializers()
            r2 = 0
            if (r1 == 0) goto L_0x0031
            java.lang.Class r1 = r7.getRawClass()
            com.fasterxml.jackson.databind.BeanDescription r1 = r0.introspectClassAnnotations((java.lang.Class<?>) r1)
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r3 = r5._factoryConfig
            java.lang.Iterable r3 = r3.keyDeserializers()
            java.util.Iterator r3 = r3.iterator()
        L_0x001f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0031
            java.lang.Object r2 = r3.next()
            com.fasterxml.jackson.databind.deser.KeyDeserializers r2 = (com.fasterxml.jackson.databind.deser.KeyDeserializers) r2
            com.fasterxml.jackson.databind.KeyDeserializer r2 = r2.findKeyDeserializer(r7, r0, r1)
            if (r2 == 0) goto L_0x001f
        L_0x0031:
            if (r2 != 0) goto L_0x0042
            boolean r1 = r7.isEnumType()
            if (r1 == 0) goto L_0x003e
            com.fasterxml.jackson.databind.KeyDeserializer r6 = r5._createEnumKeyDeserializer(r6, r7)
            return r6
        L_0x003e:
            com.fasterxml.jackson.databind.KeyDeserializer r2 = com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers.findStringBasedKeyDeserializer(r0, r7)
        L_0x0042:
            if (r2 == 0) goto L_0x0067
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r6 = r5._factoryConfig
            boolean r6 = r6.hasDeserializerModifiers()
            if (r6 == 0) goto L_0x0067
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r6 = r5._factoryConfig
            java.lang.Iterable r6 = r6.deserializerModifiers()
            java.util.Iterator r6 = r6.iterator()
        L_0x0056:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x0067
            java.lang.Object r1 = r6.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r1 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r1
            com.fasterxml.jackson.databind.KeyDeserializer r2 = r1.modifyKeyDeserializer(r0, r7, r2)
            goto L_0x0056
        L_0x0067:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.createKeyDeserializer(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.KeyDeserializer");
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        Class<?> rawClass = javaType.getRawClass();
        BeanDescription introspect = config.introspect(javaType);
        KeyDeserializer findKeyDeserializerFromAnnotation = findKeyDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findKeyDeserializerFromAnnotation != null) {
            return findKeyDeserializerFromAnnotation;
        }
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, introspect);
        if (_findCustomEnumDeserializer != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, _findCustomEnumDeserializer);
        }
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, findDeserializerFromAnnotation);
        }
        EnumResolver constructEnumResolver = constructEnumResolver(rawClass, config, introspect.findJsonValueMethod());
        AnnotationIntrospector annotationIntrospector = config.getAnnotationIntrospector();
        for (AnnotatedMethod next : introspect.getFactoryMethods()) {
            if (annotationIntrospector.hasCreatorAnnotation(next)) {
                if (next.getParameterCount() != 1 || !next.getRawReturnType().isAssignableFrom(rawClass)) {
                    throw new IllegalArgumentException("Unsuitable method (" + next + ") decorated with @JsonCreator (for Enum type " + rawClass.getName() + ")");
                } else if (next.getRawParameterType(0) == String.class) {
                    if (config.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(next.getMember(), deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver, next);
                } else {
                    throw new IllegalArgumentException("Parameter #0 type for factory method (" + next + ") not suitable, must be java.lang.String");
                }
            }
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        TypeResolverBuilder<?> findPropertyTypeResolver = deserializationConfig.getAnnotationIntrospector().findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, javaType);
        }
        return findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, annotatedMember, javaType));
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        TypeResolverBuilder<?> findPropertyContentTypeResolver = deserializationConfig.getAnnotationIntrospector().findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        if (findPropertyContentTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, contentType);
        }
        return findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypesByTypeId(deserializationConfig, annotatedMember, contentType));
    }

    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType javaType2;
        JavaType javaType3;
        Class<?> rawClass = javaType.getRawClass();
        if (rawClass == CLASS_OBJECT) {
            DeserializationConfig config = deserializationContext.getConfig();
            if (this._factoryConfig.hasAbstractTypeResolvers()) {
                javaType2 = _findRemappedType(config, List.class);
                javaType3 = _findRemappedType(config, Map.class);
            } else {
                javaType2 = null;
                javaType3 = null;
            }
            return new UntypedObjectDeserializer(javaType2, javaType3);
        } else if (rawClass == CLASS_STRING || rawClass == CLASS_CHAR_BUFFER) {
            return StringDeserializer.instance;
        } else {
            if (rawClass == CLASS_ITERABLE) {
                TypeFactory typeFactory = deserializationContext.getTypeFactory();
                JavaType[] findTypeParameters = typeFactory.findTypeParameters(javaType, CLASS_ITERABLE);
                return createCollectionDeserializer(deserializationContext, typeFactory.constructCollectionType((Class<? extends Collection>) Collection.class, (findTypeParameters == null || findTypeParameters.length != 1) ? TypeFactory.unknownType() : findTypeParameters[0]), beanDescription);
            } else if (rawClass == CLASS_MAP_ENTRY) {
                JavaType containedType = javaType.containedType(0);
                if (containedType == null) {
                    containedType = TypeFactory.unknownType();
                }
                JavaType containedType2 = javaType.containedType(1);
                if (containedType2 == null) {
                    containedType2 = TypeFactory.unknownType();
                }
                TypeDeserializer typeDeserializer = (TypeDeserializer) containedType2.getTypeHandler();
                if (typeDeserializer == null) {
                    typeDeserializer = findTypeDeserializer(deserializationContext.getConfig(), containedType2);
                }
                return new MapEntryDeserializer(javaType, (KeyDeserializer) containedType.getValueHandler(), (JsonDeserializer<Object>) (JsonDeserializer) containedType2.getValueHandler(), typeDeserializer);
            } else {
                String name = rawClass.getName();
                if (rawClass.isPrimitive() || name.startsWith("java.")) {
                    JsonDeserializer<?> find = NumberDeserializers.find(rawClass, name);
                    if (find == null) {
                        find = DateDeserializers.find(rawClass, name);
                    }
                    if (find != null) {
                        return find;
                    }
                }
                if (rawClass == TokenBuffer.class) {
                    return new TokenBufferDeserializer();
                }
                JsonDeserializer<?> findOptionalStdDeserializer = findOptionalStdDeserializer(deserializationContext, javaType, beanDescription);
                if (findOptionalStdDeserializer != null) {
                    return findOptionalStdDeserializer;
                }
                return JdkDeserializers.find(rawClass, name);
            }
        }
    }

    /* access modifiers changed from: protected */
    public JavaType _findRemappedType(DeserializationConfig deserializationConfig, Class<?> cls) throws JsonMappingException {
        JavaType mapAbstractType = mapAbstractType(deserializationConfig, deserializationConfig.constructType(cls));
        if (mapAbstractType == null || mapAbstractType.hasRawClass(cls)) {
            return null;
        }
        return mapAbstractType;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findTreeNodeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findTreeNodeDeserializer2 = findTreeNodeDeserializer.findTreeNodeDeserializer(cls, deserializationConfig, beanDescription);
            if (findTreeNodeDeserializer2 != null) {
                return findTreeNodeDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomReferenceDeserializer(ReferenceType referenceType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findReferenceDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findReferenceDeserializer2 = findReferenceDeserializer.findReferenceDeserializer(referenceType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findReferenceDeserializer2 != null) {
                return findReferenceDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findBeanDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findBeanDeserializer2 = findBeanDeserializer.findBeanDeserializer(javaType, deserializationConfig, beanDescription);
            if (findBeanDeserializer2 != null) {
                return findBeanDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findArrayDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findArrayDeserializer2 = findArrayDeserializer.findArrayDeserializer(arrayType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findArrayDeserializer2 != null) {
                return findArrayDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findCollectionDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionDeserializer2 = findCollectionDeserializer.findCollectionDeserializer(collectionType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionDeserializer2 != null) {
                return findCollectionDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findCollectionLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionLikeDeserializer2 = findCollectionLikeDeserializer.findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionLikeDeserializer2 != null) {
                return findCollectionLikeDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findEnumDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findEnumDeserializer2 = findEnumDeserializer.findEnumDeserializer(cls, deserializationConfig, beanDescription);
            if (findEnumDeserializer2 != null) {
                return findEnumDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findMapDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapDeserializer2 = findMapDeserializer.findMapDeserializer(mapType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapDeserializer2 != null) {
                return findMapDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findMapLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapLikeDeserializer2 = findMapLikeDeserializer.findMapLikeDeserializer(mapLikeType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapLikeDeserializer2 != null) {
                return findMapLikeDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findDeserializer = deserializationContext.getAnnotationIntrospector().findDeserializer(annotated);
        if (findDeserializer == null) {
            return null;
        }
        return deserializationContext.deserializerInstance(annotated, findDeserializer);
    }

    /* access modifiers changed from: protected */
    public KeyDeserializer findKeyDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findKeyDeserializer = deserializationContext.getAnnotationIntrospector().findKeyDeserializer(annotated);
        if (findKeyDeserializer == null) {
            return null;
        }
        return deserializationContext.keyDeserializerInstance(annotated, findKeyDeserializer);
    }

    /* access modifiers changed from: protected */
    public <T extends JavaType> T modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, T t) throws JsonMappingException {
        JsonDeserializer<Object> deserializerInstance;
        JavaType keyType;
        KeyDeserializer keyDeserializerInstance;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            return t;
        }
        if (t.isMapLikeType() && (keyType = t.getKeyType()) != null && keyType.getValueHandler() == null && (keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotated, annotationIntrospector.findKeyDeserializer(annotated))) != null) {
            t = ((MapLikeType) t).withKeyValueHandler(keyDeserializerInstance);
            t.getKeyType();
        }
        JavaType contentType = t.getContentType();
        if (!(contentType == null || contentType.getValueHandler() != null || (deserializerInstance = deserializationContext.deserializerInstance(annotated, annotationIntrospector.findContentDeserializer(annotated))) == null)) {
            t = t.withContentValueHandler(deserializerInstance);
        }
        return annotationIntrospector.refineDeserializationType(deserializationContext.getConfig(), annotated, t);
    }

    /* access modifiers changed from: protected */
    public JavaType resolveType(DeserializationContext deserializationContext, BeanDescription beanDescription, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        TypeDeserializer typeDeserializer;
        TypeDeserializer findPropertyContentTypeDeserializer;
        KeyDeserializer keyDeserializerInstance;
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            return javaType;
        }
        if (!(!javaType.isMapLikeType() || javaType.getKeyType() == null || (keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotatedMember, annotationIntrospector.findKeyDeserializer(annotatedMember))) == null)) {
            javaType = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerInstance);
            javaType.getKeyType();
        }
        if (javaType.getContentType() != null) {
            JsonDeserializer<Object> deserializerInstance = deserializationContext.deserializerInstance(annotatedMember, annotationIntrospector.findContentDeserializer(annotatedMember));
            if (deserializerInstance != null) {
                javaType = javaType.withContentValueHandler(deserializerInstance);
            }
            if ((annotatedMember instanceof AnnotatedMember) && (findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember)) != null) {
                javaType = javaType.withContentTypeHandler(findPropertyContentTypeDeserializer);
            }
        }
        if (annotatedMember instanceof AnnotatedMember) {
            typeDeserializer = findPropertyTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember);
        } else {
            typeDeserializer = findTypeDeserializer(deserializationContext.getConfig(), javaType);
        }
        return typeDeserializer != null ? javaType.withTypeHandler(typeDeserializer) : javaType;
    }

    /* access modifiers changed from: protected */
    public EnumResolver constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig, AnnotatedMethod annotatedMethod) {
        if (annotatedMethod != null) {
            Method annotated = annotatedMethod.getAnnotated();
            if (deserializationConfig.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(annotated, deserializationConfig.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
            }
            return EnumResolver.constructUnsafeUsingMethod(cls, annotated);
        } else if (deserializationConfig.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {
            return EnumResolver.constructUnsafeUsingToString(cls);
        } else {
            return EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _findJsonValueFor(DeserializationConfig deserializationConfig, JavaType javaType) {
        if (javaType == null) {
            return null;
        }
        return deserializationConfig.introspect(javaType).findJsonValueMethod();
    }
}

package com.fasterxml.jackson.databind.deser;

import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.impl.NoClassDefFoundDeserializer;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    private static final Class<?>[] NO_VIEWS = new Class[0];
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    private static final long serialVersionUID = 1;

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    public DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig) {
        if (this._factoryConfig == deserializerFactoryConfig) {
            return this;
        }
        if (getClass() == BeanDeserializerFactory.class) {
            return new BeanDeserializerFactory(deserializerFactoryConfig);
        }
        throw new IllegalStateException("Subtype of BeanDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with " + "additional deserializer definitions");
    }

    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType materializeAbstractType;
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer<Object> _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, config, beanDescription);
        if (_findCustomBeanDeserializer != null) {
            return _findCustomBeanDeserializer;
        }
        if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isAbstract() && !javaType.isPrimitive() && (materializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription)) != null) {
            return buildBeanDeserializer(deserializationContext, materializeAbstractType, config.introspect(materializeAbstractType));
        }
        JsonDeserializer<?> findStdDeserializer = findStdDeserializer(deserializationContext, javaType, beanDescription);
        if (findStdDeserializer != null) {
            return findStdDeserializer;
        }
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        return buildBeanDeserializer(deserializationContext, javaType, beanDescription);
    }

    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) throws JsonMappingException {
        return buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.getConfig().introspectForBuilder(deserializationContext.constructType(cls)));
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> findDefaultDeserializer = findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (findDefaultDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                findDefaultDeserializer = modifyDeserializer.modifyDeserializer(deserializationContext.getConfig(), beanDescription, findDefaultDeserializer);
            }
        }
        return findDefaultDeserializer;
    }

    /* access modifiers changed from: protected */
    public JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        for (AbstractTypeResolver resolveAbstractType : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType2 = resolveAbstractType.resolveAbstractType(deserializationContext.getConfig(), beanDescription);
            if (resolveAbstractType2 != null) {
                return resolveAbstractType2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<?> jsonDeserializer;
        try {
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
            constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
            addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
            DeserializationConfig config = deserializationContext.getConfig();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                    constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
                }
            }
            if (!javaType.isAbstract() || findValueInstantiator.canInstantiate()) {
                jsonDeserializer = constructBeanDeserializerBuilder.build();
            } else {
                jsonDeserializer = constructBeanDeserializerBuilder.buildAbstract();
            }
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                    jsonDeserializer = modifyDeserializer.modifyDeserializer(config, beanDescription, jsonDeserializer);
                }
            }
            return jsonDeserializer;
        } catch (NoClassDefFoundError e) {
            return new NoClassDefFoundDeserializer(e);
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        String str;
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        JsonPOJOBuilder.Value findPOJOBuilderConfig = beanDescription.findPOJOBuilderConfig();
        if (findPOJOBuilderConfig == null) {
            str = "build";
        } else {
            str = findPOJOBuilderConfig.buildMethodName;
        }
        AnnotatedMethod findMethod = beanDescription.findMethod(str, (Class<?>[]) null);
        if (findMethod != null && config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(findMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        constructBeanDeserializerBuilder.setPOJOBuilder(findMethod, findPOJOBuilderConfig);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> buildBuilderBased = constructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                buildBuilderBased = modifyDeserializer.modifyDeserializer(config, beanDescription, buildBuilderBased);
            }
        }
        return buildBuilderBased;
    }

    /* access modifiers changed from: protected */
    public void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        PropertyBasedObjectIdGenerator propertyBasedObjectIdGenerator;
        JavaType javaType;
        SettableBeanProperty settableBeanProperty;
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo != null) {
            Class<? extends ObjectIdGenerator<?>> generatorType = objectIdInfo.getGeneratorType();
            ObjectIdResolver objectIdResolverInstance = deserializationContext.objectIdResolverInstance(beanDescription.getClassInfo(), objectIdInfo);
            if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                PropertyName propertyName = objectIdInfo.getPropertyName();
                settableBeanProperty = beanDeserializerBuilder.findProperty(propertyName);
                if (settableBeanProperty != null) {
                    JavaType type = settableBeanProperty.getType();
                    javaType = type;
                    propertyBasedObjectIdGenerator = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
                } else {
                    throw new IllegalArgumentException("Invalid Object Id definition for " + beanDescription.getBeanClass().getName() + ": can not find property with name '" + propertyName + "'");
                }
            } else {
                JavaType javaType2 = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                settableBeanProperty = null;
                propertyBasedObjectIdGenerator = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
                javaType = javaType2;
            }
            JsonDeserializer<Object> findRootValueDeserializer = deserializationContext.findRootValueDeserializer(javaType);
            beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(javaType, objectIdInfo.getPropertyName(), propertyBasedObjectIdGenerator, findRootValueDeserializer, settableBeanProperty, objectIdResolverInstance));
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        SettableBeanProperty constructSettableProperty;
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext, beanDescription));
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        AnnotatedMethod findMethod = beanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (!(findMethod == null || (constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct((MapperConfig<?>) deserializationContext.getConfig(), (AnnotatedMember) findMethod, new PropertyName("cause")), findMethod.getParameterType(0))) == null)) {
            constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("suppressed");
        constructBeanDeserializerBuilder.addIgnorable(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<?> build = constructBeanDeserializerBuilder.build();
        if (build instanceof BeanDeserializer) {
            build = new ThrowableDeserializer((BeanDeserializer) build);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                build = modifyDeserializer.modifyDeserializer(config, beanDescription, build);
            }
        }
        return build;
    }

    /* access modifiers changed from: protected */
    public BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext.getConfig());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x015a A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addBeanProps(com.fasterxml.jackson.databind.DeserializationContext r17, com.fasterxml.jackson.databind.BeanDescription r18, com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder r19) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r16 = this;
            r6 = r16
            r7 = r17
            r8 = r18
            r9 = r19
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r19.getValueInstantiator()
            com.fasterxml.jackson.databind.DeserializationConfig r1 = r17.getConfig()
            com.fasterxml.jackson.databind.deser.SettableBeanProperty[] r10 = r0.getFromObjectArguments(r1)
            com.fasterxml.jackson.databind.JavaType r0 = r18.getType()
            boolean r0 = r0.isAbstract()
            r11 = 1
            r12 = r0 ^ 1
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r17.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r18.getClassInfo()
            java.lang.Boolean r1 = r0.findIgnoreUnknownProperties(r1)
            if (r1 == 0) goto L_0x0034
            boolean r1 = r1.booleanValue()
            r9.setIgnoreUnknownProperties(r1)
        L_0x0034:
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r1 = r18.getClassInfo()
            r13 = 0
            java.lang.String[] r0 = r0.findPropertiesToIgnore(r1, r13)
            java.util.HashSet r5 = com.fasterxml.jackson.databind.util.ArrayBuilders.arrayToSet(r0)
            java.util.Iterator r0 = r5.iterator()
        L_0x0045:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0055
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            r9.addIgnorable(r1)
            goto L_0x0045
        L_0x0055:
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r0 = r18.findAnySetter()
            if (r0 == 0) goto L_0x0062
            com.fasterxml.jackson.databind.deser.SettableAnyProperty r1 = r6.constructAnySetter(r7, r8, r0)
            r9.setAnySetter(r1)
        L_0x0062:
            if (r0 != 0) goto L_0x007e
            java.util.Set r0 = r18.getIgnoredPropertyNames()
            if (r0 == 0) goto L_0x007e
            java.util.Iterator r0 = r0.iterator()
        L_0x006e:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x007e
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            r9.addIgnorable(r1)
            goto L_0x006e
        L_0x007e:
            com.fasterxml.jackson.databind.MapperFeature r0 = com.fasterxml.jackson.databind.MapperFeature.USE_GETTERS_AS_SETTERS
            boolean r0 = r7.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r0)
            if (r0 == 0) goto L_0x0090
            com.fasterxml.jackson.databind.MapperFeature r0 = com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS
            boolean r0 = r7.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r0)
            if (r0 == 0) goto L_0x0090
            r14 = 1
            goto L_0x0091
        L_0x0090:
            r14 = 0
        L_0x0091:
            java.util.List r4 = r18.findProperties()
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            java.util.List r0 = r0.filterBeanProps(r1, r2, r3, r4, r5)
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r6._factoryConfig
            boolean r1 = r1.hasDeserializerModifiers()
            if (r1 == 0) goto L_0x00c8
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r1 = r6._factoryConfig
            java.lang.Iterable r1 = r1.deserializerModifiers()
            java.util.Iterator r1 = r1.iterator()
        L_0x00b3:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00c8
            java.lang.Object r2 = r1.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r2 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r2
            com.fasterxml.jackson.databind.DeserializationConfig r3 = r17.getConfig()
            java.util.List r0 = r2.updateProperties(r3, r8, r0)
            goto L_0x00b3
        L_0x00c8:
            java.util.Iterator r0 = r0.iterator()
        L_0x00cc:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x018a
            java.lang.Object r1 = r0.next()
            com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r1 = (com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition) r1
            boolean r2 = r1.hasSetter()
            if (r2 == 0) goto L_0x00eb
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r2 = r1.getSetter()
            com.fasterxml.jackson.databind.JavaType r2 = r2.getParameterType(r13)
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r6.constructSettableProperty(r7, r8, r1, r2)
            goto L_0x0124
        L_0x00eb:
            boolean r2 = r1.hasField()
            if (r2 == 0) goto L_0x00fe
            com.fasterxml.jackson.databind.introspect.AnnotatedField r2 = r1.getField()
            com.fasterxml.jackson.databind.JavaType r2 = r2.getType()
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r6.constructSettableProperty(r7, r8, r1, r2)
            goto L_0x0124
        L_0x00fe:
            if (r14 == 0) goto L_0x0123
            boolean r2 = r1.hasGetter()
            if (r2 == 0) goto L_0x0123
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r2 = r1.getGetter()
            java.lang.Class r2 = r2.getRawType()
            java.lang.Class<java.util.Collection> r4 = java.util.Collection.class
            boolean r4 = r4.isAssignableFrom(r2)
            if (r4 != 0) goto L_0x011e
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            boolean r2 = r4.isAssignableFrom(r2)
            if (r2 == 0) goto L_0x0123
        L_0x011e:
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r6.constructSetterlessProperty(r7, r8, r1)
            goto L_0x0124
        L_0x0123:
            r2 = 0
        L_0x0124:
            if (r12 == 0) goto L_0x0170
            boolean r4 = r1.hasConstructorParameter()
            if (r4 == 0) goto L_0x0170
            java.lang.String r1 = r1.getName()
            if (r10 == 0) goto L_0x014d
            int r4 = r10.length
            r5 = 0
        L_0x0134:
            if (r5 >= r4) goto L_0x014d
            r15 = r10[r5]
            java.lang.String r3 = r15.getName()
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x014a
            boolean r3 = r15 instanceof com.fasterxml.jackson.databind.deser.CreatorProperty
            if (r3 == 0) goto L_0x014a
            r3 = r15
            com.fasterxml.jackson.databind.deser.CreatorProperty r3 = (com.fasterxml.jackson.databind.deser.CreatorProperty) r3
            goto L_0x014e
        L_0x014a:
            int r5 = r5 + 1
            goto L_0x0134
        L_0x014d:
            r3 = 0
        L_0x014e:
            if (r3 == 0) goto L_0x015a
            if (r2 == 0) goto L_0x0155
            r3.setFallbackSetter(r2)
        L_0x0155:
            r9.addCreatorProperty(r3)
            goto L_0x00cc
        L_0x015a:
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r0[r13] = r1
            java.lang.Class r1 = r18.getBeanClass()
            java.lang.String r1 = r1.getName()
            r0[r11] = r1
            java.lang.String r1 = "Could not find creator property with name '%s' (in class %s)"
            com.fasterxml.jackson.databind.JsonMappingException r0 = r7.mappingException((java.lang.String) r1, (java.lang.Object[]) r0)
            throw r0
        L_0x0170:
            if (r2 == 0) goto L_0x00cc
            java.lang.Class[] r1 = r1.findViews()
            if (r1 != 0) goto L_0x0182
            com.fasterxml.jackson.databind.MapperFeature r3 = com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION
            boolean r3 = r7.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r3)
            if (r3 != 0) goto L_0x0182
            java.lang.Class<?>[] r1 = NO_VIEWS
        L_0x0182:
            r2.setViews(r1)
            r9.addProperty(r2)
            goto L_0x00cc
        L_0x018a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.addBeanProps(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder):void");
    }

    /* access modifiers changed from: protected */
    public List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) throws JsonMappingException {
        ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition next : list) {
            String name = next.getName();
            if (!set.contains(name)) {
                if (!next.hasConstructorParameter()) {
                    Class<?> cls = null;
                    if (next.hasSetter()) {
                        cls = next.getSetter().getRawParameterType(0);
                    } else if (next.hasField()) {
                        cls = next.getField().getRawType();
                    }
                    if (cls != null && isIgnorableType(deserializationContext.getConfig(), beanDescription, cls, hashMap)) {
                        beanDeserializerBuilder.addIgnorable(name);
                    }
                }
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void addReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        JavaType javaType;
        Map<String, AnnotatedMember> findBackReferenceProperties = beanDescription.findBackReferenceProperties();
        if (findBackReferenceProperties != null) {
            for (Map.Entry next : findBackReferenceProperties.entrySet()) {
                String str = (String) next.getKey();
                AnnotatedMember annotatedMember = (AnnotatedMember) next.getValue();
                if (annotatedMember instanceof AnnotatedMethod) {
                    javaType = ((AnnotatedMethod) annotatedMember).getParameterType(0);
                } else {
                    javaType = annotatedMember.getType();
                }
                beanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), annotatedMember), javaType));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Map<Object, AnnotatedMember> findInjectables = beanDescription.findInjectables();
        if (findInjectables != null) {
            boolean canOverrideAccessModifiers = deserializationContext.canOverrideAccessModifiers();
            boolean z = canOverrideAccessModifiers && deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS);
            for (Map.Entry next : findInjectables.entrySet()) {
                AnnotatedMember annotatedMember = (AnnotatedMember) next.getValue();
                if (canOverrideAccessModifiers) {
                    annotatedMember.fixAccess(z);
                }
                beanDeserializerBuilder.addInjectable(PropertyName.construct(annotatedMember.getName()), annotatedMember.getType(), beanDescription.getClassAnnotations(), annotatedMember, next.getKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    public SettableAnyProperty constructAnySetter(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedMethod annotatedMethod) throws JsonMappingException {
        if (deserializationContext.canOverrideAccessModifiers()) {
            annotatedMethod.fixAccess(deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        JavaType parameterType = annotatedMethod.getParameterType(1);
        BeanProperty.Std std = new BeanProperty.Std(PropertyName.construct(annotatedMethod.getName()), parameterType, (PropertyName) null, beanDescription.getClassAnnotations(), (AnnotatedMember) annotatedMethod, PropertyMetadata.STD_OPTIONAL);
        JavaType resolveType = resolveType(deserializationContext, beanDescription, parameterType, annotatedMethod);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, annotatedMethod);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext, annotatedMethod, resolveType);
        return new SettableAnyProperty(std, annotatedMethod, modifyTypeByAnnotation, findDeserializerFromAnnotation == null ? (JsonDeserializer) modifyTypeByAnnotation.getValueHandler() : findDeserializerFromAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: com.fasterxml.jackson.databind.deser.SettableBeanProperty} */
    /* JADX WARNING: type inference failed for: r10v3, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty] */
    /* JADX WARNING: type inference failed for: r10v4, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty] */
    /* JADX WARNING: type inference failed for: r1v3, types: [com.fasterxml.jackson.databind.deser.impl.FieldProperty] */
    /* JADX WARNING: type inference failed for: r1v4, types: [com.fasterxml.jackson.databind.deser.impl.MethodProperty] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.SettableBeanProperty constructSettableProperty(com.fasterxml.jackson.databind.DeserializationContext r10, com.fasterxml.jackson.databind.BeanDescription r11, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r12, com.fasterxml.jackson.databind.JavaType r13) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r7 = r12.getNonConstructorMutator()
            boolean r0 = r10.canOverrideAccessModifiers()
            if (r0 == 0) goto L_0x0013
            com.fasterxml.jackson.databind.MapperFeature r0 = com.fasterxml.jackson.databind.MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS
            boolean r0 = r10.isEnabled((com.fasterxml.jackson.databind.MapperFeature) r0)
            r7.fixAccess(r0)
        L_0x0013:
            com.fasterxml.jackson.databind.BeanProperty$Std r8 = new com.fasterxml.jackson.databind.BeanProperty$Std
            com.fasterxml.jackson.databind.PropertyName r1 = r12.getFullName()
            com.fasterxml.jackson.databind.PropertyName r3 = r12.getWrapperName()
            com.fasterxml.jackson.databind.util.Annotations r4 = r11.getClassAnnotations()
            com.fasterxml.jackson.databind.PropertyMetadata r6 = r12.getMetadata()
            r0 = r8
            r2 = r13
            r5 = r7
            r0.<init>((com.fasterxml.jackson.databind.PropertyName) r1, (com.fasterxml.jackson.databind.JavaType) r2, (com.fasterxml.jackson.databind.PropertyName) r3, (com.fasterxml.jackson.databind.util.Annotations) r4, (com.fasterxml.jackson.databind.introspect.AnnotatedMember) r5, (com.fasterxml.jackson.databind.PropertyMetadata) r6)
            com.fasterxml.jackson.databind.JavaType r0 = r9.resolveType(r10, r11, r13, r7)
            if (r0 == r13) goto L_0x0034
            r8.withType(r0)
        L_0x0034:
            com.fasterxml.jackson.databind.JsonDeserializer r13 = r9.findDeserializerFromAnnotation(r10, r7)
            com.fasterxml.jackson.databind.JavaType r3 = r9.modifyTypeByAnnotation(r10, r7, r0)
            java.lang.Object r10 = r3.getTypeHandler()
            r4 = r10
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r4 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r4
            boolean r10 = r7 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod
            if (r10 == 0) goto L_0x0056
            com.fasterxml.jackson.databind.deser.impl.MethodProperty r10 = new com.fasterxml.jackson.databind.deser.impl.MethodProperty
            com.fasterxml.jackson.databind.util.Annotations r5 = r11.getClassAnnotations()
            r6 = r7
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r6 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethod) r6
            r1 = r10
            r2 = r12
            r1.<init>(r2, r3, r4, r5, r6)
            goto L_0x0064
        L_0x0056:
            com.fasterxml.jackson.databind.deser.impl.FieldProperty r10 = new com.fasterxml.jackson.databind.deser.impl.FieldProperty
            com.fasterxml.jackson.databind.util.Annotations r5 = r11.getClassAnnotations()
            r6 = r7
            com.fasterxml.jackson.databind.introspect.AnnotatedField r6 = (com.fasterxml.jackson.databind.introspect.AnnotatedField) r6
            r1 = r10
            r2 = r12
            r1.<init>(r2, r3, r4, r5, r6)
        L_0x0064:
            if (r13 == 0) goto L_0x006a
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r10 = r10.withValueDeserializer(r13)
        L_0x006a:
            com.fasterxml.jackson.databind.AnnotationIntrospector$ReferenceProperty r11 = r12.findReferenceType()
            if (r11 == 0) goto L_0x007d
            boolean r13 = r11.isManagedReference()
            if (r13 == 0) goto L_0x007d
            java.lang.String r11 = r11.getName()
            r10.setManagedReferenceName(r11)
        L_0x007d:
            com.fasterxml.jackson.databind.introspect.ObjectIdInfo r11 = r12.findObjectIdInfo()
            if (r11 == 0) goto L_0x0086
            r10.setObjectIdInfo(r11)
        L_0x0086:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.constructSettableProperty(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) throws JsonMappingException {
        AnnotatedMethod getter = beanPropertyDefinition.getGetter();
        if (deserializationContext.canOverrideAccessModifiers()) {
            getter.fixAccess(deserializationContext.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        JavaType type = getter.getType();
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, getter);
        JavaType resolveType = resolveType(deserializationContext, beanDescription, modifyTypeByAnnotation(deserializationContext, getter, type), getter);
        SetterlessProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, resolveType, (TypeDeserializer) resolveType.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        return findDeserializerFromAnnotation != null ? setterlessProperty.withValueDeserializer(findDeserializerFromAnnotation) : setterlessProperty;
    }

    /* access modifiers changed from: protected */
    public boolean isPotentialBeanType(Class<?> cls) {
        String canBeABeanType = ClassUtil.canBeABeanType(cls);
        if (canBeABeanType != null) {
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + canBeABeanType + ") as a Bean");
        } else if (!ClassUtil.isProxyType(cls)) {
            String isLocalType = ClassUtil.isLocalType(cls, true);
            if (isLocalType == null) {
                return true;
            }
            throw new IllegalArgumentException("Can not deserialize Class " + cls.getName() + " (of type " + isLocalType + ") as a Bean");
        } else {
            throw new IllegalArgumentException("Can not deserialize Proxy class " + cls.getName() + " as a Bean");
        }
    }

    /* access modifiers changed from: protected */
    public boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanDescription beanDescription, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool = map.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean isIgnorableType = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations(cls).getClassInfo());
        if (isIgnorableType == null) {
            return false;
        }
        return isIgnorableType.booleanValue();
    }
}

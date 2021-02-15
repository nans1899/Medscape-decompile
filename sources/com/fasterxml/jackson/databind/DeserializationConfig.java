package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatFeature;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

public final class DeserializationConfig extends MapperConfigBase<DeserializationFeature, DeserializationConfig> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final int _deserFeatures;
    protected final int _formatReadFeatures;
    protected final int _formatReadFeaturesToChange;
    protected final JsonNodeFactory _nodeFactory;
    protected final int _parserFeatures;
    protected final int _parserFeaturesToChange;
    protected final LinkedNode<DeserializationProblemHandler> _problemHandlers;

    public DeserializationConfig(BaseSettings baseSettings, SubtypeResolver subtypeResolver, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup) {
        super(baseSettings, subtypeResolver, simpleMixInResolver, rootNameLookup);
        this._deserFeatures = collectFeatureDefaults(DeserializationFeature.class);
        this._nodeFactory = JsonNodeFactory.instance;
        this._problemHandlers = null;
        this._parserFeatures = 0;
        this._parserFeaturesToChange = 0;
        this._formatReadFeatures = 0;
        this._formatReadFeaturesToChange = 0;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, int i, int i2, int i3, int i4, int i5, int i6) {
        super(deserializationConfig, i);
        this._deserFeatures = i2;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._parserFeatures = i3;
        this._parserFeaturesToChange = i4;
        this._formatReadFeatures = i5;
        this._formatReadFeaturesToChange = i6;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, SubtypeResolver subtypeResolver) {
        super(deserializationConfig, subtypeResolver);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, BaseSettings baseSettings) {
        super(deserializationConfig, baseSettings);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, JsonNodeFactory jsonNodeFactory) {
        super(deserializationConfig);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = jsonNodeFactory;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, LinkedNode<DeserializationProblemHandler> linkedNode) {
        super(deserializationConfig);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = linkedNode;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, PropertyName propertyName) {
        super(deserializationConfig, propertyName);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    private DeserializationConfig(DeserializationConfig deserializationConfig, Class<?> cls) {
        super(deserializationConfig, cls);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, ContextAttributes contextAttributes) {
        super(deserializationConfig, contextAttributes);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, SimpleMixInResolver simpleMixInResolver) {
        super(deserializationConfig, simpleMixInResolver);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    protected DeserializationConfig(DeserializationConfig deserializationConfig, SimpleMixInResolver simpleMixInResolver, RootNameLookup rootNameLookup) {
        super(deserializationConfig, simpleMixInResolver, rootNameLookup);
        this._deserFeatures = deserializationConfig._deserFeatures;
        this._problemHandlers = deserializationConfig._problemHandlers;
        this._nodeFactory = deserializationConfig._nodeFactory;
        this._parserFeatures = deserializationConfig._parserFeatures;
        this._parserFeaturesToChange = deserializationConfig._parserFeaturesToChange;
        this._formatReadFeatures = deserializationConfig._formatReadFeatures;
        this._formatReadFeaturesToChange = deserializationConfig._formatReadFeaturesToChange;
    }

    /* access modifiers changed from: protected */
    public BaseSettings getBaseSettings() {
        return this._base;
    }

    public DeserializationConfig with(MapperFeature... mapperFeatureArr) {
        int i = this._mapperFeatures;
        int i2 = i;
        for (MapperFeature mask : mapperFeatureArr) {
            i2 |= mask.getMask();
        }
        if (i2 == this._mapperFeatures) {
            return this;
        }
        return new DeserializationConfig(this, i2, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(MapperFeature... mapperFeatureArr) {
        int i = this._mapperFeatures;
        int i2 = i;
        for (MapperFeature mask : mapperFeatureArr) {
            i2 &= ~mask.getMask();
        }
        if (i2 == this._mapperFeatures) {
            return this;
        }
        return new DeserializationConfig(this, i2, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(MapperFeature mapperFeature, boolean z) {
        int i;
        if (z) {
            i = mapperFeature.getMask() | this._mapperFeatures;
        } else {
            i = (~mapperFeature.getMask()) & this._mapperFeatures;
        }
        int i2 = i;
        if (i2 == this._mapperFeatures) {
            return this;
        }
        return new DeserializationConfig(this, i2, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(ClassIntrospector classIntrospector) {
        return _withBase(this._base.withClassIntrospector(classIntrospector));
    }

    public DeserializationConfig with(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig with(VisibilityChecker<?> visibilityChecker) {
        return _withBase(this._base.withVisibilityChecker(visibilityChecker));
    }

    public DeserializationConfig withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
        return _withBase(this._base.withVisibility(propertyAccessor, visibility));
    }

    public DeserializationConfig with(TypeResolverBuilder<?> typeResolverBuilder) {
        return _withBase(this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    public DeserializationConfig with(SubtypeResolver subtypeResolver) {
        return this._subtypeResolver == subtypeResolver ? this : new DeserializationConfig(this, subtypeResolver);
    }

    public DeserializationConfig with(PropertyNamingStrategy propertyNamingStrategy) {
        return _withBase(this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    public DeserializationConfig withRootName(PropertyName propertyName) {
        if (propertyName == null) {
            if (this._rootName == null) {
                return this;
            }
        } else if (propertyName.equals(this._rootName)) {
            return this;
        }
        return new DeserializationConfig(this, propertyName);
    }

    public DeserializationConfig with(TypeFactory typeFactory) {
        return _withBase(this._base.withTypeFactory(typeFactory));
    }

    public DeserializationConfig with(DateFormat dateFormat) {
        return _withBase(this._base.withDateFormat(dateFormat));
    }

    public DeserializationConfig with(HandlerInstantiator handlerInstantiator) {
        return _withBase(this._base.withHandlerInstantiator(handlerInstantiator));
    }

    public DeserializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig withView(Class<?> cls) {
        return this._view == cls ? this : new DeserializationConfig(this, cls);
    }

    public DeserializationConfig with(Locale locale) {
        return _withBase(this._base.with(locale));
    }

    public DeserializationConfig with(TimeZone timeZone) {
        return _withBase(this._base.with(timeZone));
    }

    public DeserializationConfig with(Base64Variant base64Variant) {
        return _withBase(this._base.with(base64Variant));
    }

    public DeserializationConfig with(ContextAttributes contextAttributes) {
        return contextAttributes == this._attributes ? this : new DeserializationConfig(this, contextAttributes);
    }

    private final DeserializationConfig _withBase(BaseSettings baseSettings) {
        return this._base == baseSettings ? this : new DeserializationConfig(this, baseSettings);
    }

    public DeserializationConfig with(DeserializationFeature deserializationFeature) {
        int mask = this._deserFeatures | deserializationFeature.getMask();
        if (mask == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, mask, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        int mask = deserializationFeature.getMask() | this._deserFeatures;
        int i = mask;
        for (DeserializationFeature mask2 : deserializationFeatureArr) {
            i |= mask2.getMask();
        }
        if (i == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withFeatures(DeserializationFeature... deserializationFeatureArr) {
        int i = this._deserFeatures;
        int i2 = i;
        for (DeserializationFeature mask : deserializationFeatureArr) {
            i2 |= mask.getMask();
        }
        if (i2 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i2, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(DeserializationFeature deserializationFeature) {
        int i = this._deserFeatures & (~deserializationFeature.getMask());
        if (i == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        int i = (~deserializationFeature.getMask()) & this._deserFeatures;
        int i2 = i;
        for (DeserializationFeature mask : deserializationFeatureArr) {
            i2 &= ~mask.getMask();
        }
        if (i2 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i2, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withoutFeatures(DeserializationFeature... deserializationFeatureArr) {
        int i = this._deserFeatures;
        int i2 = i;
        for (DeserializationFeature mask : deserializationFeatureArr) {
            i2 &= ~mask.getMask();
        }
        if (i2 == this._deserFeatures) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, i2, this._parserFeatures, this._parserFeaturesToChange, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(JsonParser.Feature feature) {
        int mask = this._parserFeatures | feature.getMask();
        int mask2 = this._parserFeaturesToChange | feature.getMask();
        if (this._parserFeatures == mask && this._parserFeaturesToChange == mask2) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, mask, mask2, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withFeatures(JsonParser.Feature... featureArr) {
        int i = this._parserFeatures;
        int i2 = this._parserFeaturesToChange;
        int i3 = i;
        int i4 = i2;
        for (JsonParser.Feature mask : featureArr) {
            int mask2 = mask.getMask();
            i3 |= mask2;
            i4 |= mask2;
        }
        if (this._parserFeatures == i3 && this._parserFeaturesToChange == i4) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i3, i4, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig without(JsonParser.Feature feature) {
        int i = this._parserFeatures & (~feature.getMask());
        int mask = this._parserFeaturesToChange | feature.getMask();
        if (this._parserFeatures == i && this._parserFeaturesToChange == mask) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i, mask, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig withoutFeatures(JsonParser.Feature... featureArr) {
        int i = this._parserFeatures;
        int i2 = this._parserFeaturesToChange;
        int i3 = i;
        int i4 = i2;
        for (JsonParser.Feature mask : featureArr) {
            int mask2 = mask.getMask();
            i3 &= ~mask2;
            i4 |= mask2;
        }
        if (this._parserFeatures == i3 && this._parserFeaturesToChange == i4) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, i3, i4, this._formatReadFeatures, this._formatReadFeaturesToChange);
    }

    public DeserializationConfig with(FormatFeature formatFeature) {
        int mask = this._formatReadFeatures | formatFeature.getMask();
        int mask2 = this._formatReadFeaturesToChange | formatFeature.getMask();
        if (this._formatReadFeatures == mask && this._formatReadFeaturesToChange == mask2) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, mask, mask2);
    }

    public DeserializationConfig withFeatures(FormatFeature... formatFeatureArr) {
        int i = this._formatReadFeatures;
        int i2 = this._formatReadFeaturesToChange;
        int i3 = i;
        int i4 = i2;
        for (FormatFeature mask : formatFeatureArr) {
            int mask2 = mask.getMask();
            i3 |= mask2;
            i4 |= mask2;
        }
        if (this._formatReadFeatures == i3 && this._formatReadFeaturesToChange == i4) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, i3, i4);
    }

    public DeserializationConfig without(FormatFeature formatFeature) {
        int i = this._formatReadFeatures & (~formatFeature.getMask());
        int mask = this._formatReadFeaturesToChange | formatFeature.getMask();
        if (this._formatReadFeatures == i && this._formatReadFeaturesToChange == mask) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, i, mask);
    }

    public DeserializationConfig withoutFeatures(FormatFeature... formatFeatureArr) {
        int i = this._formatReadFeatures;
        int i2 = this._formatReadFeaturesToChange;
        int i3 = i;
        int i4 = i2;
        for (FormatFeature mask : formatFeatureArr) {
            int mask2 = mask.getMask();
            i3 &= ~mask2;
            i4 |= mask2;
        }
        if (this._formatReadFeatures == i3 && this._formatReadFeaturesToChange == i4) {
            return this;
        }
        return new DeserializationConfig(this, this._mapperFeatures, this._deserFeatures, this._parserFeatures, this._parserFeaturesToChange, i3, i4);
    }

    public DeserializationConfig with(JsonNodeFactory jsonNodeFactory) {
        if (this._nodeFactory == jsonNodeFactory) {
            return this;
        }
        return new DeserializationConfig(this, jsonNodeFactory);
    }

    public DeserializationConfig withHandler(DeserializationProblemHandler deserializationProblemHandler) {
        if (LinkedNode.contains(this._problemHandlers, deserializationProblemHandler)) {
            return this;
        }
        return new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) new LinkedNode(deserializationProblemHandler, this._problemHandlers));
    }

    public DeserializationConfig withNoProblemHandlers() {
        if (this._problemHandlers == null) {
            return this;
        }
        return new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) null);
    }

    public void initialize(JsonParser jsonParser) {
        int i = this._parserFeaturesToChange;
        if (i != 0) {
            jsonParser.overrideStdFeatures(this._parserFeatures, i);
        }
        int i2 = this._formatReadFeaturesToChange;
        if (i2 != 0) {
            jsonParser.overrideFormatFeatures(this._formatReadFeatures, i2);
        }
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            return super.getAnnotationIntrospector();
        }
        return NopAnnotationIntrospector.instance;
    }

    public BeanDescription introspectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forClassAnnotations(this, javaType, this);
    }

    public BeanDescription introspectDirectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker defaultVisibilityChecker = super.getDefaultVisibilityChecker();
        if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return !isEnabled(MapperFeature.AUTO_DETECT_FIELDS) ? defaultVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE) : defaultVisibilityChecker;
    }

    public JsonInclude.Value getDefaultPropertyInclusion() {
        return EMPTY_INCLUDE;
    }

    public JsonInclude.Value getDefaultPropertyInclusion(Class<?> cls) {
        return EMPTY_INCLUDE;
    }

    public JsonFormat.Value getDefaultPropertyFormat(Class<?> cls) {
        return EMPTY_FORMAT;
    }

    public boolean useRootWrapping() {
        if (this._rootName != null) {
            return !this._rootName.isEmpty();
        }
        return isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (deserializationFeature.getMask() & this._deserFeatures) != 0;
    }

    public final boolean isEnabled(JsonParser.Feature feature, JsonFactory jsonFactory) {
        if ((feature.getMask() & this._parserFeaturesToChange) == 0) {
            return jsonFactory.isEnabled(feature);
        }
        return (feature.getMask() & this._parserFeatures) != 0;
    }

    public final boolean hasDeserializationFeatures(int i) {
        return (this._deserFeatures & i) == i;
    }

    public final boolean hasSomeOfFeatures(int i) {
        return (i & this._deserFeatures) != 0;
    }

    public final int getDeserializationFeatures() {
        return this._deserFeatures;
    }

    public LinkedNode<DeserializationProblemHandler> getProblemHandlers() {
        return this._problemHandlers;
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._nodeFactory;
    }

    public <T extends BeanDescription> T introspect(JavaType javaType) {
        return getClassIntrospector().forDeserialization(this, javaType, this);
    }

    public <T extends BeanDescription> T introspectForCreation(JavaType javaType) {
        return getClassIntrospector().forCreation(this, javaType, this);
    }

    public <T extends BeanDescription> T introspectForBuilder(JavaType javaType) {
        return getClassIntrospector().forDeserializationWithBuilder(this, javaType, this);
    }

    public TypeDeserializer findTypeDeserializer(JavaType javaType) throws JsonMappingException {
        AnnotatedClass classInfo = introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        TypeResolverBuilder<?> findTypeResolver = getAnnotationIntrospector().findTypeResolver(this, classInfo, javaType);
        Collection<NamedType> collection = null;
        if (findTypeResolver == null) {
            findTypeResolver = getDefaultTyper(javaType);
            if (findTypeResolver == null) {
                return null;
            }
        } else {
            collection = getSubtypeResolver().collectAndResolveSubtypesByTypeId(this, classInfo);
        }
        return findTypeResolver.buildTypeDeserializer(this, javaType, collection);
    }
}

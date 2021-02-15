package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class SimpleModule extends Module implements Serializable {
    private static final long serialVersionUID = 1;
    protected SimpleAbstractTypeResolver _abstractTypes;
    protected BeanDeserializerModifier _deserializerModifier;
    protected SimpleDeserializers _deserializers;
    protected SimpleKeyDeserializers _keyDeserializers;
    protected SimpleSerializers _keySerializers;
    protected HashMap<Class<?>, Class<?>> _mixins;
    protected final String _name;
    protected PropertyNamingStrategy _namingStrategy;
    protected BeanSerializerModifier _serializerModifier;
    protected SimpleSerializers _serializers;
    protected LinkedHashSet<NamedType> _subtypes;
    protected SimpleValueInstantiators _valueInstantiators;
    protected final Version _version;

    public SimpleModule() {
        String str;
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._namingStrategy = null;
        if (getClass() == SimpleModule.class) {
            str = "SimpleModule-" + System.identityHashCode(this);
        } else {
            str = getClass().getName();
        }
        this._name = str;
        this._version = Version.unknownVersion();
    }

    public SimpleModule(String str) {
        this(str, Version.unknownVersion());
    }

    public SimpleModule(Version version) {
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._namingStrategy = null;
        this._name = version.getArtifactId();
        this._version = version;
    }

    public SimpleModule(String str, Version version) {
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._namingStrategy = null;
        this._name = str;
        this._version = version;
    }

    public SimpleModule(String str, Version version, Map<Class<?>, JsonDeserializer<?>> map) {
        this(str, version, map, (List<JsonSerializer<?>>) null);
    }

    public SimpleModule(String str, Version version, List<JsonSerializer<?>> list) {
        this(str, version, (Map<Class<?>, JsonDeserializer<?>>) null, list);
    }

    public SimpleModule(String str, Version version, Map<Class<?>, JsonDeserializer<?>> map, List<JsonSerializer<?>> list) {
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._namingStrategy = null;
        this._name = str;
        this._version = version;
        if (map != null) {
            this._deserializers = new SimpleDeserializers(map);
        }
        if (list != null) {
            this._serializers = new SimpleSerializers(list);
        }
    }

    public Object getTypeId() {
        if (getClass() == SimpleModule.class) {
            return null;
        }
        return super.getTypeId();
    }

    public void setSerializers(SimpleSerializers simpleSerializers) {
        this._serializers = simpleSerializers;
    }

    public void setDeserializers(SimpleDeserializers simpleDeserializers) {
        this._deserializers = simpleDeserializers;
    }

    public void setKeySerializers(SimpleSerializers simpleSerializers) {
        this._keySerializers = simpleSerializers;
    }

    public void setKeyDeserializers(SimpleKeyDeserializers simpleKeyDeserializers) {
        this._keyDeserializers = simpleKeyDeserializers;
    }

    public void setAbstractTypes(SimpleAbstractTypeResolver simpleAbstractTypeResolver) {
        this._abstractTypes = simpleAbstractTypeResolver;
    }

    public void setValueInstantiators(SimpleValueInstantiators simpleValueInstantiators) {
        this._valueInstantiators = simpleValueInstantiators;
    }

    public SimpleModule setDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        this._deserializerModifier = beanDeserializerModifier;
        return this;
    }

    public SimpleModule setSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        this._serializerModifier = beanSerializerModifier;
        return this;
    }

    /* access modifiers changed from: protected */
    public SimpleModule setNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        this._namingStrategy = propertyNamingStrategy;
        return this;
    }

    public SimpleModule addSerializer(JsonSerializer<?> jsonSerializer) {
        if (this._serializers == null) {
            this._serializers = new SimpleSerializers();
        }
        this._serializers.addSerializer(jsonSerializer);
        return this;
    }

    public <T> SimpleModule addSerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        if (this._serializers == null) {
            this._serializers = new SimpleSerializers();
        }
        this._serializers.addSerializer(cls, jsonSerializer);
        return this;
    }

    public <T> SimpleModule addKeySerializer(Class<? extends T> cls, JsonSerializer<T> jsonSerializer) {
        if (this._keySerializers == null) {
            this._keySerializers = new SimpleSerializers();
        }
        this._keySerializers.addSerializer(cls, jsonSerializer);
        return this;
    }

    public <T> SimpleModule addDeserializer(Class<T> cls, JsonDeserializer<? extends T> jsonDeserializer) {
        if (this._deserializers == null) {
            this._deserializers = new SimpleDeserializers();
        }
        this._deserializers.addDeserializer(cls, jsonDeserializer);
        return this;
    }

    public SimpleModule addKeyDeserializer(Class<?> cls, KeyDeserializer keyDeserializer) {
        if (this._keyDeserializers == null) {
            this._keyDeserializers = new SimpleKeyDeserializers();
        }
        this._keyDeserializers.addDeserializer(cls, keyDeserializer);
        return this;
    }

    public <T> SimpleModule addAbstractTypeMapping(Class<T> cls, Class<? extends T> cls2) {
        if (this._abstractTypes == null) {
            this._abstractTypes = new SimpleAbstractTypeResolver();
        }
        this._abstractTypes = this._abstractTypes.addMapping(cls, cls2);
        return this;
    }

    public SimpleModule addValueInstantiator(Class<?> cls, ValueInstantiator valueInstantiator) {
        if (this._valueInstantiators == null) {
            this._valueInstantiators = new SimpleValueInstantiators();
        }
        this._valueInstantiators = this._valueInstantiators.addValueInstantiator(cls, valueInstantiator);
        return this;
    }

    public SimpleModule registerSubtypes(Class<?>... clsArr) {
        if (this._subtypes == null) {
            this._subtypes = new LinkedHashSet<>(Math.max(16, clsArr.length));
        }
        for (Class<?> namedType : clsArr) {
            this._subtypes.add(new NamedType(namedType));
        }
        return this;
    }

    public SimpleModule registerSubtypes(NamedType... namedTypeArr) {
        if (this._subtypes == null) {
            this._subtypes = new LinkedHashSet<>(Math.max(16, namedTypeArr.length));
        }
        for (NamedType add : namedTypeArr) {
            this._subtypes.add(add);
        }
        return this;
    }

    public SimpleModule setMixInAnnotation(Class<?> cls, Class<?> cls2) {
        if (this._mixins == null) {
            this._mixins = new HashMap<>();
        }
        this._mixins.put(cls, cls2);
        return this;
    }

    public String getModuleName() {
        return this._name;
    }

    public void setupModule(Module.SetupContext setupContext) {
        SimpleSerializers simpleSerializers = this._serializers;
        if (simpleSerializers != null) {
            setupContext.addSerializers(simpleSerializers);
        }
        SimpleDeserializers simpleDeserializers = this._deserializers;
        if (simpleDeserializers != null) {
            setupContext.addDeserializers(simpleDeserializers);
        }
        SimpleSerializers simpleSerializers2 = this._keySerializers;
        if (simpleSerializers2 != null) {
            setupContext.addKeySerializers(simpleSerializers2);
        }
        SimpleKeyDeserializers simpleKeyDeserializers = this._keyDeserializers;
        if (simpleKeyDeserializers != null) {
            setupContext.addKeyDeserializers(simpleKeyDeserializers);
        }
        SimpleAbstractTypeResolver simpleAbstractTypeResolver = this._abstractTypes;
        if (simpleAbstractTypeResolver != null) {
            setupContext.addAbstractTypeResolver(simpleAbstractTypeResolver);
        }
        SimpleValueInstantiators simpleValueInstantiators = this._valueInstantiators;
        if (simpleValueInstantiators != null) {
            setupContext.addValueInstantiators(simpleValueInstantiators);
        }
        BeanDeserializerModifier beanDeserializerModifier = this._deserializerModifier;
        if (beanDeserializerModifier != null) {
            setupContext.addBeanDeserializerModifier(beanDeserializerModifier);
        }
        BeanSerializerModifier beanSerializerModifier = this._serializerModifier;
        if (beanSerializerModifier != null) {
            setupContext.addBeanSerializerModifier(beanSerializerModifier);
        }
        LinkedHashSet<NamedType> linkedHashSet = this._subtypes;
        if (linkedHashSet != null && linkedHashSet.size() > 0) {
            LinkedHashSet<NamedType> linkedHashSet2 = this._subtypes;
            setupContext.registerSubtypes((NamedType[]) linkedHashSet2.toArray(new NamedType[linkedHashSet2.size()]));
        }
        PropertyNamingStrategy propertyNamingStrategy = this._namingStrategy;
        if (propertyNamingStrategy != null) {
            setupContext.setNamingStrategy(propertyNamingStrategy);
        }
        HashMap<Class<?>, Class<?>> hashMap = this._mixins;
        if (hashMap != null) {
            for (Map.Entry next : hashMap.entrySet()) {
                setupContext.setMixInAnnotations((Class) next.getKey(), (Class) next.getValue());
            }
        }
    }

    public Version version() {
        return this._version;
    }
}
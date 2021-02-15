package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.NullifyingDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.pool.TypePool;

public abstract class TypeDeserializerBase extends TypeDeserializer implements Serializable {
    private static final long serialVersionUID = 1;
    protected final JavaType _baseType;
    protected final JavaType _defaultImpl;
    protected JsonDeserializer<Object> _defaultImplDeserializer;
    protected final Map<String, JsonDeserializer<Object>> _deserializers;
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;
    protected final boolean _typeIdVisible;
    protected final String _typePropertyName;

    public abstract TypeDeserializer forProperty(BeanProperty beanProperty);

    public abstract JsonTypeInfo.As getTypeInclusion();

    protected TypeDeserializerBase(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        this._baseType = javaType;
        this._idResolver = typeIdResolver;
        this._typePropertyName = str == null ? "" : str;
        this._typeIdVisible = z;
        this._deserializers = new ConcurrentHashMap(16, 0.75f, 2);
        if (cls == null) {
            this._defaultImpl = null;
        } else {
            this._defaultImpl = javaType.forcedNarrowBy(cls);
        }
        this._property = null;
    }

    protected TypeDeserializerBase(TypeDeserializerBase typeDeserializerBase, BeanProperty beanProperty) {
        this._baseType = typeDeserializerBase._baseType;
        this._idResolver = typeDeserializerBase._idResolver;
        this._typePropertyName = typeDeserializerBase._typePropertyName;
        this._typeIdVisible = typeDeserializerBase._typeIdVisible;
        this._deserializers = typeDeserializerBase._deserializers;
        this._defaultImpl = typeDeserializerBase._defaultImpl;
        this._defaultImplDeserializer = typeDeserializerBase._defaultImplDeserializer;
        this._property = beanProperty;
    }

    public String baseTypeName() {
        return this._baseType.getRawClass().getName();
    }

    public final String getPropertyName() {
        return this._typePropertyName;
    }

    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    public Class<?> getDefaultImpl() {
        JavaType javaType = this._defaultImpl;
        if (javaType == null) {
            return null;
        }
        return javaType.getRawClass();
    }

    public String toString() {
        return TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + getClass().getName() + "; base-type:" + this._baseType + "; id-resolver: " + this._idResolver + ']';
    }

    /* access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDeserializer(DeserializationContext deserializationContext, String str) throws IOException {
        JsonDeserializer<Object> jsonDeserializer;
        JsonDeserializer<Object> jsonDeserializer2 = this._deserializers.get(str);
        if (jsonDeserializer2 == null) {
            JavaType typeFromId = this._idResolver.typeFromId(deserializationContext, str);
            if (typeFromId == null) {
                jsonDeserializer2 = _findDefaultImplDeserializer(deserializationContext);
                if (jsonDeserializer2 == null) {
                    jsonDeserializer = _handleUnknownTypeId(deserializationContext, str, this._idResolver, this._baseType);
                }
                this._deserializers.put(str, jsonDeserializer2);
            } else {
                JavaType javaType = this._baseType;
                if (javaType != null && javaType.getClass() == typeFromId.getClass()) {
                    typeFromId = deserializationContext.getTypeFactory().constructSpecializedType(this._baseType, typeFromId.getRawClass());
                }
                jsonDeserializer = deserializationContext.findContextualValueDeserializer(typeFromId, this._property);
            }
            jsonDeserializer2 = jsonDeserializer;
            this._deserializers.put(str, jsonDeserializer2);
        }
        return jsonDeserializer2;
    }

    /* access modifiers changed from: protected */
    public final JsonDeserializer<Object> _findDefaultImplDeserializer(DeserializationContext deserializationContext) throws IOException {
        JsonDeserializer<Object> jsonDeserializer;
        JavaType javaType = this._defaultImpl;
        if (javaType == null) {
            if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE)) {
                return NullifyingDeserializer.instance;
            }
            return null;
        } else if (ClassUtil.isBogusClass(javaType.getRawClass())) {
            return NullifyingDeserializer.instance;
        } else {
            synchronized (this._defaultImpl) {
                if (this._defaultImplDeserializer == null) {
                    this._defaultImplDeserializer = deserializationContext.findContextualValueDeserializer(this._defaultImpl, this._property);
                }
                jsonDeserializer = this._defaultImplDeserializer;
            }
            return jsonDeserializer;
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public Object _deserializeWithNativeTypeId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return _deserializeWithNativeTypeId(jsonParser, deserializationContext, jsonParser.getTypeId());
    }

    /* access modifiers changed from: protected */
    public Object _deserializeWithNativeTypeId(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        JsonDeserializer<Object> jsonDeserializer;
        if (obj == null) {
            jsonDeserializer = _findDefaultImplDeserializer(deserializationContext);
            if (jsonDeserializer == null) {
                throw deserializationContext.mappingException("No (native) type id found when one was expected for polymorphic type handling");
            }
        } else {
            jsonDeserializer = _findDeserializer(deserializationContext, obj instanceof String ? (String) obj : String.valueOf(obj));
        }
        return jsonDeserializer.deserialize(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _handleUnknownTypeId(DeserializationContext deserializationContext, String str, TypeIdResolver typeIdResolver, JavaType javaType) throws IOException {
        String str2;
        if (typeIdResolver instanceof TypeIdResolverBase) {
            String descForKnownTypeIds = ((TypeIdResolverBase) typeIdResolver).getDescForKnownTypeIds();
            if (descForKnownTypeIds == null) {
                str2 = "known type ids are not statically known";
            } else {
                str2 = "known type ids = " + descForKnownTypeIds;
            }
        } else {
            str2 = null;
        }
        throw deserializationContext.unknownTypeException(this._baseType, str, str2);
    }
}

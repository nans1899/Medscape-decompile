package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.impl.FailingDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ConcreteBeanPropertyBase;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ViewMatcher;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

public abstract class SettableBeanProperty extends ConcreteBeanPropertyBase implements Serializable {
    protected static final JsonDeserializer<Object> MISSING_VALUE_DESERIALIZER = new FailingDeserializer("No _valueDeserializer assigned");
    protected final transient Annotations _contextAnnotations;
    protected String _managedReferenceName;
    protected ObjectIdInfo _objectIdInfo;
    protected final PropertyName _propName;
    protected int _propertyIndex;
    protected final JavaType _type;
    protected final JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;
    protected ViewMatcher _viewMatcher;
    protected final PropertyName _wrapperName;

    public abstract void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException;

    public abstract Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException;

    public abstract <A extends Annotation> A getAnnotation(Class<A> cls);

    public int getCreatorIndex() {
        return -1;
    }

    public Object getInjectableValueId() {
        return null;
    }

    public abstract AnnotatedMember getMember();

    public abstract void set(Object obj, Object obj2) throws IOException;

    public abstract Object setAndReturn(Object obj, Object obj2) throws IOException;

    public abstract SettableBeanProperty withName(PropertyName propertyName);

    public abstract SettableBeanProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer);

    protected SettableBeanProperty(BeanPropertyDefinition beanPropertyDefinition, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations) {
        this(beanPropertyDefinition.getFullName(), javaType, beanPropertyDefinition.getWrapperName(), typeDeserializer, annotations, beanPropertyDefinition.getMetadata());
    }

    @Deprecated
    protected SettableBeanProperty(String str, JavaType javaType, PropertyName propertyName, TypeDeserializer typeDeserializer, Annotations annotations, boolean z) {
        this(new PropertyName(str), javaType, propertyName, typeDeserializer, annotations, PropertyMetadata.construct(z, (String) null, (Integer) null, (String) null));
    }

    protected SettableBeanProperty(PropertyName propertyName, JavaType javaType, PropertyName propertyName2, TypeDeserializer typeDeserializer, Annotations annotations, PropertyMetadata propertyMetadata) {
        super(propertyMetadata);
        this._propertyIndex = -1;
        if (propertyName == null) {
            this._propName = PropertyName.NO_NAME;
        } else {
            this._propName = propertyName.internSimpleName();
        }
        this._type = javaType;
        this._wrapperName = propertyName2;
        this._contextAnnotations = annotations;
        this._viewMatcher = null;
        this._valueTypeDeserializer = typeDeserializer != null ? typeDeserializer.forProperty(this) : typeDeserializer;
        this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
    }

    protected SettableBeanProperty(PropertyName propertyName, JavaType javaType, PropertyMetadata propertyMetadata, JsonDeserializer<Object> jsonDeserializer) {
        super(propertyMetadata);
        this._propertyIndex = -1;
        if (propertyName == null) {
            this._propName = PropertyName.NO_NAME;
        } else {
            this._propName = propertyName.internSimpleName();
        }
        this._type = javaType;
        this._wrapperName = null;
        this._contextAnnotations = null;
        this._viewMatcher = null;
        this._valueTypeDeserializer = null;
        this._valueDeserializer = jsonDeserializer;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty) {
        super((ConcreteBeanPropertyBase) settableBeanProperty);
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._wrapperName = settableBeanProperty._wrapperName;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        this._viewMatcher = settableBeanProperty._viewMatcher;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty, JsonDeserializer<?> jsonDeserializer) {
        super((ConcreteBeanPropertyBase) settableBeanProperty);
        this._propertyIndex = -1;
        this._propName = settableBeanProperty._propName;
        this._type = settableBeanProperty._type;
        this._wrapperName = settableBeanProperty._wrapperName;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        if (jsonDeserializer == null) {
            this._valueDeserializer = MISSING_VALUE_DESERIALIZER;
        } else {
            this._valueDeserializer = jsonDeserializer;
        }
        this._viewMatcher = settableBeanProperty._viewMatcher;
    }

    protected SettableBeanProperty(SettableBeanProperty settableBeanProperty, PropertyName propertyName) {
        super((ConcreteBeanPropertyBase) settableBeanProperty);
        this._propertyIndex = -1;
        this._propName = propertyName;
        this._type = settableBeanProperty._type;
        this._wrapperName = settableBeanProperty._wrapperName;
        this._contextAnnotations = settableBeanProperty._contextAnnotations;
        this._valueDeserializer = settableBeanProperty._valueDeserializer;
        this._valueTypeDeserializer = settableBeanProperty._valueTypeDeserializer;
        this._managedReferenceName = settableBeanProperty._managedReferenceName;
        this._propertyIndex = settableBeanProperty._propertyIndex;
        this._viewMatcher = settableBeanProperty._viewMatcher;
    }

    public SettableBeanProperty withSimpleName(String str) {
        PropertyName propertyName = this._propName;
        PropertyName propertyName2 = propertyName == null ? new PropertyName(str) : propertyName.withSimpleName(str);
        return propertyName2 == this._propName ? this : withName(propertyName2);
    }

    @Deprecated
    public SettableBeanProperty withName(String str) {
        return withName(new PropertyName(str));
    }

    public void setManagedReferenceName(String str) {
        this._managedReferenceName = str;
    }

    public void setObjectIdInfo(ObjectIdInfo objectIdInfo) {
        this._objectIdInfo = objectIdInfo;
    }

    public void setViews(Class<?>[] clsArr) {
        if (clsArr == null) {
            this._viewMatcher = null;
        } else {
            this._viewMatcher = ViewMatcher.construct(clsArr);
        }
    }

    public void assignIndex(int i) {
        if (this._propertyIndex == -1) {
            this._propertyIndex = i;
            return;
        }
        throw new IllegalStateException("Property '" + getName() + "' already had index (" + this._propertyIndex + "), trying to assign " + i);
    }

    public final String getName() {
        return this._propName.getSimpleName();
    }

    public PropertyName getFullName() {
        return this._propName;
    }

    public JavaType getType() {
        return this._type;
    }

    public PropertyName getWrapperName() {
        return this._wrapperName;
    }

    public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
        return this._contextAnnotations.get(cls);
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {
        if (isRequired()) {
            jsonObjectFormatVisitor.property(this);
        } else {
            jsonObjectFormatVisitor.optionalProperty(this);
        }
    }

    /* access modifiers changed from: protected */
    public final Class<?> getDeclaringClass() {
        return getMember().getDeclaringClass();
    }

    public String getManagedReferenceName() {
        return this._managedReferenceName;
    }

    public ObjectIdInfo getObjectIdInfo() {
        return this._objectIdInfo;
    }

    public boolean hasValueDeserializer() {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        return (jsonDeserializer == null || jsonDeserializer == MISSING_VALUE_DESERIALIZER) ? false : true;
    }

    public boolean hasValueTypeDeserializer() {
        return this._valueTypeDeserializer != null;
    }

    public JsonDeserializer<Object> getValueDeserializer() {
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        if (jsonDeserializer == MISSING_VALUE_DESERIALIZER) {
            return null;
        }
        return jsonDeserializer;
    }

    public TypeDeserializer getValueTypeDeserializer() {
        return this._valueTypeDeserializer;
    }

    public boolean visibleInView(Class<?> cls) {
        ViewMatcher viewMatcher = this._viewMatcher;
        return viewMatcher == null || viewMatcher.isVisibleForView(cls);
    }

    public boolean hasViews() {
        return this._viewMatcher != null;
    }

    public int getPropertyIndex() {
        return this._propertyIndex;
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return this._valueDeserializer.getNullValue(deserializationContext);
        }
        TypeDeserializer typeDeserializer = this._valueTypeDeserializer;
        if (typeDeserializer != null) {
            return this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, typeDeserializer);
        }
        return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    /* access modifiers changed from: protected */
    public void _throwAsIOE(JsonParser jsonParser, Exception exc, Object obj) throws IOException {
        String str;
        if (exc instanceof IllegalArgumentException) {
            if (obj == null) {
                str = "[NULL]";
            } else {
                str = obj.getClass().getName();
            }
            StringBuilder sb = new StringBuilder("Problem deserializing property '");
            sb.append(getName());
            sb.append("' (expected type: ");
            sb.append(getType());
            sb.append("; actual type: ");
            sb.append(str);
            sb.append(")");
            String message = exc.getMessage();
            if (message != null) {
                sb.append(", problem: ");
                sb.append(message);
            } else {
                sb.append(" (no error message provided)");
            }
            throw JsonMappingException.from(jsonParser, sb.toString(), (Throwable) exc);
        }
        _throwAsIOE(jsonParser, exc);
    }

    /* access modifiers changed from: protected */
    public IOException _throwAsIOE(JsonParser jsonParser, Exception exc) throws IOException {
        if (!(exc instanceof IOException)) {
            boolean z = exc instanceof RuntimeException;
            Throwable th = exc;
            if (!z) {
                while (th.getCause() != null) {
                    th = th.getCause();
                }
                throw JsonMappingException.from(jsonParser, th.getMessage(), th);
            }
            throw ((RuntimeException) exc);
        }
        throw ((IOException) exc);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public IOException _throwAsIOE(Exception exc) throws IOException {
        return _throwAsIOE((JsonParser) null, exc);
    }

    /* access modifiers changed from: protected */
    public void _throwAsIOE(Exception exc, Object obj) throws IOException {
        _throwAsIOE((JsonParser) null, exc, obj);
    }

    public String toString() {
        return "[property '" + getName() + "']";
    }
}

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotationMap;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

public final class InnerClassProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected AnnotatedConstructor _annotated;
    protected final transient Constructor<?> _creator;
    protected final SettableBeanProperty _delegate;

    public InnerClassProperty(SettableBeanProperty settableBeanProperty, Constructor<?> constructor) {
        super(settableBeanProperty);
        this._delegate = settableBeanProperty;
        this._creator = constructor;
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, AnnotatedConstructor annotatedConstructor) {
        super(innerClassProperty);
        Constructor<?> constructor;
        this._delegate = innerClassProperty._delegate;
        this._annotated = annotatedConstructor;
        if (annotatedConstructor == null) {
            constructor = null;
        } else {
            constructor = annotatedConstructor.getAnnotated();
        }
        this._creator = constructor;
        if (constructor == null) {
            throw new IllegalArgumentException("Missing constructor (broken JDK (de)serialization?)");
        }
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) innerClassProperty, jsonDeserializer);
        this._delegate = innerClassProperty._delegate.withValueDeserializer(jsonDeserializer);
        this._creator = innerClassProperty._creator;
    }

    protected InnerClassProperty(InnerClassProperty innerClassProperty, PropertyName propertyName) {
        super((SettableBeanProperty) innerClassProperty, propertyName);
        this._delegate = innerClassProperty._delegate.withName(propertyName);
        this._creator = innerClassProperty._creator;
    }

    public InnerClassProperty withName(PropertyName propertyName) {
        return new InnerClassProperty(this, propertyName);
    }

    public InnerClassProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new InnerClassProperty(this, jsonDeserializer);
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        return this._delegate.getAnnotation(cls);
    }

    public AnnotatedMember getMember() {
        return this._delegate.getMember();
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        Object obj2;
        Object obj3;
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            obj2 = this._valueDeserializer.getNullValue(deserializationContext);
        } else if (this._valueTypeDeserializer != null) {
            obj2 = this._valueDeserializer.deserializeWithType(jsonParser, deserializationContext, this._valueTypeDeserializer);
        } else {
            try {
                obj3 = this._creator.newInstance(new Object[]{obj});
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e, "Failed to instantiate class " + this._creator.getDeclaringClass().getName() + ", problem: " + e.getMessage());
                obj3 = null;
            }
            this._valueDeserializer.deserialize(jsonParser, deserializationContext, obj3);
            obj2 = obj3;
        }
        set(obj, obj2);
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public final void set(Object obj, Object obj2) throws IOException {
        this._delegate.set(obj, obj2);
    }

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        return this._delegate.setAndReturn(obj, obj2);
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return new InnerClassProperty(this, this._annotated);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        if (this._annotated != null) {
            return this;
        }
        return new InnerClassProperty(this, new AnnotatedConstructor((TypeResolutionContext) null, this._creator, (AnnotationMap) null, (AnnotationMap[]) null));
    }
}

package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.UnresolvedForwardReference;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class ReadableObjectId {
    protected final ObjectIdGenerator.IdKey _key;
    protected LinkedList<Referring> _referringProperties;
    protected ObjectIdResolver _resolver;
    @Deprecated
    public final Object id;
    @Deprecated
    public Object item;

    public boolean tryToResolveUnresolved(DeserializationContext deserializationContext) {
        return false;
    }

    @Deprecated
    public ReadableObjectId(Object obj) {
        this.id = obj;
        this._key = null;
    }

    public ReadableObjectId(ObjectIdGenerator.IdKey idKey) {
        this._key = idKey;
        this.id = idKey.key;
    }

    public void setResolver(ObjectIdResolver objectIdResolver) {
        this._resolver = objectIdResolver;
    }

    public ObjectIdGenerator.IdKey getKey() {
        return this._key;
    }

    public void appendReferring(Referring referring) {
        if (this._referringProperties == null) {
            this._referringProperties = new LinkedList<>();
        }
        this._referringProperties.add(referring);
    }

    public void bindItem(Object obj) throws IOException {
        this._resolver.bindItem(this._key, obj);
        this.item = obj;
        LinkedList<Referring> linkedList = this._referringProperties;
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            this._referringProperties = null;
            while (it.hasNext()) {
                ((Referring) it.next()).handleResolvedForwardReference(this.id, obj);
            }
        }
    }

    public Object resolve() {
        Object resolveId = this._resolver.resolveId(this._key);
        this.item = resolveId;
        return resolveId;
    }

    public boolean hasReferringProperties() {
        LinkedList<Referring> linkedList = this._referringProperties;
        return linkedList != null && !linkedList.isEmpty();
    }

    public Iterator<Referring> referringProperties() {
        LinkedList<Referring> linkedList = this._referringProperties;
        if (linkedList == null) {
            return Collections.emptyList().iterator();
        }
        return linkedList.iterator();
    }

    public ObjectIdResolver getResolver() {
        return this._resolver;
    }

    public String toString() {
        return String.valueOf(this._key);
    }

    public static abstract class Referring {
        private final Class<?> _beanType;
        private final UnresolvedForwardReference _reference;

        public abstract void handleResolvedForwardReference(Object obj, Object obj2) throws IOException;

        public Referring(UnresolvedForwardReference unresolvedForwardReference, Class<?> cls) {
            this._reference = unresolvedForwardReference;
            this._beanType = cls;
        }

        public JsonLocation getLocation() {
            return this._reference.getLocation();
        }

        public Class<?> getBeanType() {
            return this._beanType;
        }

        public boolean hasId(Object obj) {
            return obj.equals(this._reference.getUnresolvedId());
        }
    }
}

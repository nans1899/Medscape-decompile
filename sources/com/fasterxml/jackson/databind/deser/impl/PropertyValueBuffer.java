package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.PropertyValue;
import java.io.IOException;
import java.util.BitSet;

public class PropertyValueBuffer {
    protected PropertyValue _buffered;
    protected final DeserializationContext _context;
    protected final Object[] _creatorParameters;
    protected Object _idValue;
    protected final ObjectIdReader _objectIdReader;
    protected int _paramsNeeded;
    protected int _paramsSeen;
    protected final BitSet _paramsSeenBig;
    protected final JsonParser _parser;

    public PropertyValueBuffer(JsonParser jsonParser, DeserializationContext deserializationContext, int i, ObjectIdReader objectIdReader) {
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._paramsNeeded = i;
        this._objectIdReader = objectIdReader;
        this._creatorParameters = new Object[i];
        if (i < 32) {
            this._paramsSeenBig = null;
        } else {
            this._paramsSeenBig = new BitSet();
        }
    }

    /* access modifiers changed from: protected */
    public Object[] getParameters(SettableBeanProperty[] settableBeanPropertyArr) throws JsonMappingException {
        if (this._paramsNeeded > 0) {
            int i = 0;
            if (this._paramsSeenBig != null) {
                int length = this._creatorParameters.length;
                while (true) {
                    int nextClearBit = this._paramsSeenBig.nextClearBit(i);
                    if (nextClearBit >= length) {
                        break;
                    }
                    this._creatorParameters[nextClearBit] = _findMissing(settableBeanPropertyArr[nextClearBit]);
                    i = nextClearBit + 1;
                }
            } else {
                int i2 = this._paramsSeen;
                int length2 = this._creatorParameters.length;
                while (i < length2) {
                    if ((i2 & 1) == 0) {
                        this._creatorParameters[i] = _findMissing(settableBeanPropertyArr[i]);
                    }
                    i++;
                    i2 >>= 1;
                }
            }
        }
        return this._creatorParameters;
    }

    /* access modifiers changed from: protected */
    public Object _findMissing(SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        if (settableBeanProperty.getInjectableValueId() != null) {
            return this._context.findInjectableValue(settableBeanProperty.getInjectableValueId(), settableBeanProperty, (Object) null);
        }
        if (settableBeanProperty.isRequired()) {
            throw this._context.mappingException("Missing required creator property '%s' (index %d)", settableBeanProperty.getName(), Integer.valueOf(settableBeanProperty.getCreatorIndex()));
        } else if (!this._context.isEnabled(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)) {
            return settableBeanProperty.getValueDeserializer().getNullValue(this._context);
        } else {
            throw this._context.mappingException("Missing creator property '%s' (index %d); DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES enabled", settableBeanProperty.getName(), Integer.valueOf(settableBeanProperty.getCreatorIndex()));
        }
    }

    public boolean readIdProperty(String str) throws IOException {
        ObjectIdReader objectIdReader = this._objectIdReader;
        if (objectIdReader == null || !str.equals(objectIdReader.propertyName.getSimpleName())) {
            return false;
        }
        this._idValue = this._objectIdReader.readObjectReference(this._parser, this._context);
        return true;
    }

    public Object handleIdValue(DeserializationContext deserializationContext, Object obj) throws IOException {
        ObjectIdReader objectIdReader = this._objectIdReader;
        if (objectIdReader != null) {
            Object obj2 = this._idValue;
            if (obj2 != null) {
                deserializationContext.findObjectId(obj2, objectIdReader.generator, this._objectIdReader.resolver).bindItem(obj);
                SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
                if (settableBeanProperty != null) {
                    return settableBeanProperty.setAndReturn(obj, this._idValue);
                }
            } else {
                throw deserializationContext.mappingException("No _idValue when handleIdValue called, on instance of %s", obj.getClass().getName());
            }
        }
        return obj;
    }

    /* access modifiers changed from: protected */
    public PropertyValue buffered() {
        return this._buffered;
    }

    public boolean isComplete() {
        return this._paramsNeeded <= 0;
    }

    public boolean assignParameter(SettableBeanProperty settableBeanProperty, Object obj) {
        int creatorIndex = settableBeanProperty.getCreatorIndex();
        this._creatorParameters[creatorIndex] = obj;
        BitSet bitSet = this._paramsSeenBig;
        if (bitSet == null) {
            int i = this._paramsSeen;
            int i2 = (1 << creatorIndex) | i;
            if (i == i2) {
                return false;
            }
            this._paramsSeen = i2;
            int i3 = this._paramsNeeded - 1;
            this._paramsNeeded = i3;
            if (i3 <= 0) {
                return true;
            }
            return false;
        } else if (bitSet.get(creatorIndex)) {
            return false;
        } else {
            int i4 = this._paramsNeeded - 1;
            this._paramsNeeded = i4;
            if (i4 <= 0) {
                return true;
            }
            this._paramsSeenBig.set(creatorIndex);
            return false;
        }
    }

    @Deprecated
    public boolean assignParameter(int i, Object obj) {
        this._creatorParameters[i] = obj;
        return false;
    }

    public void bufferProperty(SettableBeanProperty settableBeanProperty, Object obj) {
        this._buffered = new PropertyValue.Regular(this._buffered, obj, settableBeanProperty);
    }

    public void bufferAnyProperty(SettableAnyProperty settableAnyProperty, String str, Object obj) {
        this._buffered = new PropertyValue.Any(this._buffered, obj, settableAnyProperty, str);
    }

    public void bufferMapProperty(Object obj, Object obj2) {
        this._buffered = new PropertyValue.Map(this._buffered, obj2, obj);
    }
}

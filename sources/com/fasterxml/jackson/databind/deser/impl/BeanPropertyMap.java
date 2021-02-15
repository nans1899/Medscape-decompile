package com.fasterxml.jackson.databind.deser.impl;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BeanPropertyMap implements Iterable<SettableBeanProperty>, Serializable {
    private static final long serialVersionUID = 2;
    protected final boolean _caseInsensitive;
    private Object[] _hashArea;
    private int _hashMask;
    private SettableBeanProperty[] _propsInOrder;
    private int _size;
    private int _spillCount;

    private static final int findSize(int i) {
        if (i <= 5) {
            return 8;
        }
        if (i <= 12) {
            return 16;
        }
        int i2 = 32;
        while (i2 < i + (i >> 2)) {
            i2 += i2;
        }
        return i2;
    }

    public BeanPropertyMap(boolean z, Collection<SettableBeanProperty> collection) {
        this._caseInsensitive = z;
        this._propsInOrder = (SettableBeanProperty[]) collection.toArray(new SettableBeanProperty[collection.size()]);
        init(collection);
    }

    /* access modifiers changed from: protected */
    public void init(Collection<SettableBeanProperty> collection) {
        int size = collection.size();
        this._size = size;
        int findSize = findSize(size);
        this._hashMask = findSize - 1;
        int i = (findSize >> 1) + findSize;
        Object[] objArr = new Object[(i * 2)];
        int i2 = 0;
        for (SettableBeanProperty next : collection) {
            if (next != null) {
                String propertyName = getPropertyName(next);
                int _hashCode = _hashCode(propertyName);
                int i3 = _hashCode << 1;
                if (objArr[i3] != null) {
                    i3 = ((_hashCode >> 1) + findSize) << 1;
                    if (objArr[i3] != null) {
                        i3 = (i << 1) + i2;
                        i2 += 2;
                        if (i3 >= objArr.length) {
                            objArr = Arrays.copyOf(objArr, objArr.length + 4);
                        }
                    }
                }
                objArr[i3] = propertyName;
                objArr[i3 + 1] = next;
            }
        }
        this._hashArea = objArr;
        this._spillCount = i2;
    }

    public static BeanPropertyMap construct(Collection<SettableBeanProperty> collection, boolean z) {
        return new BeanPropertyMap(z, collection);
    }

    public BeanPropertyMap withProperty(SettableBeanProperty settableBeanProperty) {
        String propertyName = getPropertyName(settableBeanProperty);
        int length = this._hashArea.length;
        int i = 1;
        while (i < length) {
            SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) this._hashArea[i];
            if (settableBeanProperty2 == null || !settableBeanProperty2.getName().equals(propertyName)) {
                i += 2;
            } else {
                this._hashArea[i] = settableBeanProperty;
                this._propsInOrder[_findFromOrdered(settableBeanProperty2)] = settableBeanProperty;
                return this;
            }
        }
        int _hashCode = _hashCode(propertyName);
        int i2 = this._hashMask + 1;
        int i3 = _hashCode << 1;
        Object[] objArr = this._hashArea;
        if (objArr[i3] != null) {
            i3 = ((_hashCode >> 1) + i2) << 1;
            if (objArr[i3] != null) {
                int i4 = (i2 + (i2 >> 1)) << 1;
                int i5 = this._spillCount;
                i3 = i4 + i5;
                this._spillCount = i5 + 2;
                if (i3 >= objArr.length) {
                    this._hashArea = Arrays.copyOf(objArr, objArr.length + 4);
                }
            }
        }
        Object[] objArr2 = this._hashArea;
        objArr2[i3] = propertyName;
        objArr2[i3 + 1] = settableBeanProperty;
        SettableBeanProperty[] settableBeanPropertyArr = this._propsInOrder;
        int length2 = settableBeanPropertyArr.length;
        SettableBeanProperty[] settableBeanPropertyArr2 = (SettableBeanProperty[]) Arrays.copyOf(settableBeanPropertyArr, length2 + 1);
        this._propsInOrder = settableBeanPropertyArr2;
        settableBeanPropertyArr2[length2] = settableBeanProperty;
        return this;
    }

    public BeanPropertyMap assignIndexes() {
        int length = this._hashArea.length;
        int i = 0;
        for (int i2 = 1; i2 < length; i2 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[i2];
            if (settableBeanProperty != null) {
                settableBeanProperty.assignIndex(i);
                i++;
            }
        }
        return this;
    }

    public BeanPropertyMap renameAll(NameTransformer nameTransformer) {
        if (nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return this;
        }
        ArrayList arrayList = new ArrayList(r0);
        for (SettableBeanProperty settableBeanProperty : this._propsInOrder) {
            if (settableBeanProperty == null) {
                arrayList.add(settableBeanProperty);
            } else {
                arrayList.add(_rename(settableBeanProperty, nameTransformer));
            }
        }
        return new BeanPropertyMap(this._caseInsensitive, arrayList);
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        String propertyName = getPropertyName(settableBeanProperty);
        int _findIndexInHash = _findIndexInHash(propertyName);
        if (_findIndexInHash >= 0) {
            Object[] objArr = this._hashArea;
            objArr[_findIndexInHash] = settableBeanProperty;
            this._propsInOrder[_findFromOrdered((SettableBeanProperty) objArr[_findIndexInHash])] = settableBeanProperty;
            return;
        }
        throw new NoSuchElementException("No entry '" + propertyName + "' found, can't replace");
    }

    private List<SettableBeanProperty> properties() {
        ArrayList arrayList = new ArrayList(this._size);
        int length = this._hashArea.length;
        for (int i = 1; i < length; i += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[i];
            if (settableBeanProperty != null) {
                arrayList.add(settableBeanProperty);
            }
        }
        return arrayList;
    }

    public Iterator<SettableBeanProperty> iterator() {
        return properties().iterator();
    }

    public SettableBeanProperty[] getPropertiesInInsertionOrder() {
        return this._propsInOrder;
    }

    /* access modifiers changed from: protected */
    public final String getPropertyName(SettableBeanProperty settableBeanProperty) {
        boolean z = this._caseInsensitive;
        String name = settableBeanProperty.getName();
        return z ? name.toLowerCase() : name;
    }

    public SettableBeanProperty find(int i) {
        int length = this._hashArea.length;
        for (int i2 = 1; i2 < length; i2 += 2) {
            SettableBeanProperty settableBeanProperty = (SettableBeanProperty) this._hashArea[i2];
            if (settableBeanProperty != null && i == settableBeanProperty.getPropertyIndex()) {
                return settableBeanProperty;
            }
        }
        return null;
    }

    public SettableBeanProperty find(String str) {
        if (str != null) {
            if (this._caseInsensitive) {
                str = str.toLowerCase();
            }
            int hashCode = str.hashCode() & this._hashMask;
            int i = hashCode << 1;
            Object obj = this._hashArea[i];
            if (obj == str || str.equals(obj)) {
                return (SettableBeanProperty) this._hashArea[i + 1];
            }
            return _find2(str, hashCode, obj);
        }
        throw new IllegalArgumentException("Can not pass null property name");
    }

    private final SettableBeanProperty _find2(String str, int i, Object obj) {
        if (obj == null) {
            return null;
        }
        int i2 = this._hashMask + 1;
        int i3 = ((i >> 1) + i2) << 1;
        Object obj2 = this._hashArea[i3];
        if (str.equals(obj2)) {
            return (SettableBeanProperty) this._hashArea[i3 + 1];
        }
        if (obj2 != null) {
            int i4 = (i2 + (i2 >> 1)) << 1;
            int i5 = this._spillCount + i4;
            while (i4 < i5) {
                Object obj3 = this._hashArea[i4];
                if (obj3 == str || str.equals(obj3)) {
                    return (SettableBeanProperty) this._hashArea[i4 + 1];
                }
                i4 += 2;
            }
        }
        return null;
    }

    public int size() {
        return this._size;
    }

    public void remove(SettableBeanProperty settableBeanProperty) {
        ArrayList arrayList = new ArrayList(this._size);
        String propertyName = getPropertyName(settableBeanProperty);
        int length = this._hashArea.length;
        boolean z = false;
        for (int i = 1; i < length; i += 2) {
            SettableBeanProperty settableBeanProperty2 = (SettableBeanProperty) this._hashArea[i];
            if (settableBeanProperty2 != null) {
                if (z || !(z = propertyName.equals(settableBeanProperty2.getName()))) {
                    arrayList.add(settableBeanProperty2);
                } else {
                    this._propsInOrder[_findFromOrdered(settableBeanProperty2)] = null;
                }
            }
        }
        if (z) {
            init(arrayList);
            return;
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty.getName() + "' found, can't remove");
    }

    public boolean findDeserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException {
        SettableBeanProperty find = find(str);
        if (find == null) {
            return false;
        }
        try {
            find.deserializeAndSet(jsonParser, deserializationContext, obj);
            return true;
        } catch (Exception e) {
            wrapAndThrow(e, obj, str, deserializationContext);
            return true;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties=[");
        Iterator<SettableBeanProperty> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            SettableBeanProperty next = it.next();
            int i2 = i + 1;
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(next.getName());
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            sb.append(next.getType());
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            i = i2;
        }
        sb.append(']');
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0015, code lost:
        r3 = r0.unwrappingDeserializer(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.SettableBeanProperty _rename(com.fasterxml.jackson.databind.deser.SettableBeanProperty r2, com.fasterxml.jackson.databind.util.NameTransformer r3) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x0003
            return r2
        L_0x0003:
            java.lang.String r0 = r2.getName()
            java.lang.String r0 = r3.transform(r0)
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r2.withSimpleName(r0)
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r2.getValueDeserializer()
            if (r0 == 0) goto L_0x001f
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r0.unwrappingDeserializer(r3)
            if (r3 == r0) goto L_0x001f
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r2 = r2.withValueDeserializer(r3)
        L_0x001f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.impl.BeanPropertyMap._rename(com.fasterxml.jackson.databind.deser.SettableBeanProperty, com.fasterxml.jackson.databind.util.NameTransformer):com.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    /* access modifiers changed from: protected */
    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        while ((th instanceof InvocationTargetException) && th.getCause() != null) {
            th = th.getCause();
        }
        if (!(th instanceof Error)) {
            boolean z = deserializationContext == null || deserializationContext.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
            if (th instanceof IOException) {
                if (!z || !(th instanceof JsonProcessingException)) {
                    throw ((IOException) th);
                }
            } else if (!z && (th instanceof RuntimeException)) {
                throw ((RuntimeException) th);
            }
            throw JsonMappingException.wrapWithPath(th, obj, str);
        }
        throw ((Error) th);
    }

    private final int _findIndexInHash(String str) {
        int _hashCode = _hashCode(str);
        int i = _hashCode << 1;
        if (str.equals(this._hashArea[i])) {
            return i + 1;
        }
        int i2 = this._hashMask + 1;
        int i3 = ((_hashCode >> 1) + i2) << 1;
        if (str.equals(this._hashArea[i3])) {
            return i3 + 1;
        }
        int i4 = (i2 + (i2 >> 1)) << 1;
        int i5 = this._spillCount + i4;
        while (i4 < i5) {
            if (str.equals(this._hashArea[i4])) {
                return i4 + 1;
            }
            i4 += 2;
        }
        return -1;
    }

    private final int _findFromOrdered(SettableBeanProperty settableBeanProperty) {
        int length = this._propsInOrder.length;
        for (int i = 0; i < length; i++) {
            if (this._propsInOrder[i] == settableBeanProperty) {
                return i;
            }
        }
        throw new IllegalStateException("Illegal state: property '" + settableBeanProperty.getName() + "' missing from _propsInOrder");
    }

    private final int _hashCode(String str) {
        return str.hashCode() & this._hashMask;
    }
}

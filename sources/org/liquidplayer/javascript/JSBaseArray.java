package org.liquidplayer.javascript;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class JSBaseArray<T> extends JSFunction implements List<T> {
    int mLeftBuffer = 0;
    private int mRightBuffer = 0;
    JSBaseArray<T> mSuperList = null;
    Class<T> mType;

    JSBaseArray(JNIJSObject jNIJSObject, JSContext jSContext, Class<T> cls) {
        super(jNIJSObject, jSContext);
        this.mType = cls;
    }

    JSBaseArray(JSBaseArray<T> jSBaseArray, int i, int i2, Class<T> cls) {
        this.mType = cls;
        this.mLeftBuffer = i;
        this.mRightBuffer = i2;
        this.context = jSBaseArray.context;
        this.valueRef = jSBaseArray.valueRef();
        this.mSuperList = jSBaseArray;
    }

    JSBaseArray(JSContext jSContext, Class<T> cls) {
        this.context = jSContext;
        this.mType = cls;
    }

    public Object[] toArray(Class cls) {
        int size = size();
        Object[] objArr = (Object[]) Array.newInstance(cls, size);
        for (int i = 0; i < size; i++) {
            objArr[i] = elementAtIndex(i).toJavaObject(cls);
        }
        return objArr;
    }

    public Object[] toArray() {
        return toArray((Class) this.mType);
    }

    public T get(int i) {
        if (i < size()) {
            return elementAtIndex(i).toJavaObject(this.mType);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public boolean add(T t) {
        elementAtIndex(size(), t);
        return true;
    }

    public int size() {
        JSBaseArray<T> jSBaseArray = this.mSuperList;
        if (jSBaseArray == null) {
            return property(Name.LENGTH).toNumber().intValue();
        }
        return Math.max(0, (jSBaseArray.size() - this.mLeftBuffer) - this.mRightBuffer);
    }

    /* access modifiers changed from: protected */
    public JSValue arrayElement(int i) {
        return propertyAtIndex(i);
    }

    /* access modifiers changed from: protected */
    public void arrayElement(int i, T t) {
        propertyAtIndex(i, t);
    }

    private JSValue elementAtIndex(int i) {
        JSBaseArray<T> jSBaseArray = this.mSuperList;
        if (jSBaseArray == null) {
            return arrayElement(i);
        }
        return jSBaseArray.elementAtIndex(i + this.mLeftBuffer);
    }

    private void elementAtIndex(int i, T t) {
        JSBaseArray<T> jSBaseArray = this.mSuperList;
        if (jSBaseArray == null) {
            arrayElement(i, t);
        } else {
            jSBaseArray.elementAtIndex(i + this.mLeftBuffer, t);
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object obj) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(obj)) {
                return true;
            }
        }
        return false;
    }

    private class ArrayIterator implements ListIterator<T> {
        private int current;
        private Integer modifiable;

        ArrayIterator(JSBaseArray jSBaseArray) {
            this(0);
        }

        ArrayIterator(int i) {
            this.modifiable = null;
            i = i > JSBaseArray.this.size() ? JSBaseArray.this.size() : i;
            this.current = i < 0 ? 0 : i;
        }

        public boolean hasNext() {
            return this.current < JSBaseArray.this.size();
        }

        public boolean hasPrevious() {
            return this.current > 0;
        }

        public T next() {
            if (hasNext()) {
                this.modifiable = Integer.valueOf(this.current);
                JSBaseArray jSBaseArray = JSBaseArray.this;
                int i = this.current;
                this.current = i + 1;
                return jSBaseArray.get(i);
            }
            throw new NoSuchElementException();
        }

        public T previous() {
            if (hasPrevious()) {
                int i = this.current - 1;
                this.current = i;
                this.modifiable = Integer.valueOf(i);
                return JSBaseArray.this.get(this.current);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            Integer num = this.modifiable;
            if (num != null) {
                JSBaseArray.this.remove(num.intValue());
                this.current = this.modifiable.intValue();
                this.modifiable = null;
                return;
            }
            throw new NoSuchElementException();
        }

        public int nextIndex() {
            return this.current;
        }

        public int previousIndex() {
            return this.current - 1;
        }

        public void set(T t) {
            Integer num = this.modifiable;
            if (num != null) {
                JSBaseArray.this.set(num.intValue(), t);
                return;
            }
            throw new NoSuchElementException();
        }

        public void add(T t) {
            JSBaseArray jSBaseArray = JSBaseArray.this;
            int i = this.current;
            this.current = i + 1;
            jSBaseArray.add(i, t);
            this.modifiable = null;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayIterator(this);
    }

    public <U> U[] toArray(U[] uArr) {
        U[] uArr2 = (Object[]) Array.newInstance(uArr.getClass().getComponentType(), size());
        ArrayIterator arrayIterator = new ArrayIterator(this);
        int i = 0;
        while (arrayIterator.hasNext()) {
            uArr2[i] = arrayIterator.next();
            i++;
        }
        while (i < uArr2.length) {
            uArr2[i] = null;
            i++;
        }
        return uArr2;
    }

    public boolean remove(Object obj) {
        ArrayIterator arrayIterator = new ArrayIterator(this);
        while (arrayIterator.hasNext()) {
            if (arrayIterator.next().equals(obj)) {
                arrayIterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        for (Object contains : collection.toArray()) {
            if (!contains(contains)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends T> collection) {
        return addAll(size(), collection);
    }

    public boolean addAll(int i, Collection<? extends T> collection) {
        Object[] array = collection.toArray();
        int length = array.length;
        int i2 = 0;
        while (i2 < length) {
            add(i, array[i2]);
            i2++;
            i++;
        }
        return true;
    }

    public boolean removeAll(Collection<?> collection) {
        ListIterator listIterator = listIterator();
        boolean z = false;
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            Iterator<?> it = collection.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (next.equals(it.next())) {
                        listIterator.remove();
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean z;
        ListIterator listIterator = listIterator();
        boolean z2 = false;
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            Iterator<?> it = collection.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (next.equals(it.next())) {
                        z = false;
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
            }
            if (z) {
                listIterator.remove();
                z2 = true;
            }
        }
        return z2;
    }

    public void clear() {
        for (int size = size(); size > 0; size--) {
            remove(size - 1);
        }
    }

    public T set(int i, T t) {
        if (i < size()) {
            JSValue elementAtIndex = elementAtIndex(i);
            elementAtIndex(i, t);
            return elementAtIndex.toJavaObject(this.mType);
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    public T remove(int i) {
        throw new UnsupportedOperationException();
    }

    public int indexOf(Object obj) {
        ListIterator listIterator = listIterator();
        while (listIterator.hasNext()) {
            if (listIterator.next().equals(obj)) {
                return listIterator.nextIndex() - 1;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (listIterator.previous().equals(obj)) {
                return listIterator.previousIndex() + 1;
            }
        }
        return -1;
    }

    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    public ListIterator<T> listIterator(int i) {
        return new ArrayIterator(i);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (size() != list.size()) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = list.iterator();
        while (it.hasNext() && it2.hasNext()) {
            if (!it.next().equals(it2.next())) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i;
        Iterator it = iterator();
        int i2 = 1;
        while (it.hasNext()) {
            Object next = it.next();
            int i3 = i2 * 31;
            if (next == null) {
                i = 0;
            } else {
                i = next.hashCode();
            }
            i2 = i3 + i;
        }
        return i2;
    }
}

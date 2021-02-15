package com.bea.xml.stream.util;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class CircularQueue extends AbstractCollection {
    private static final int DEFAULT_CAPACITY = 256;
    private static final int MAX_CAPACITY = 1073741824;
    /* access modifiers changed from: private */
    public int bitmask;
    private int capacity;
    /* access modifiers changed from: private */
    public int consumerIndex;
    private int maxCapacity;
    /* access modifiers changed from: private */
    public int producerIndex;
    /* access modifiers changed from: private */
    public Object[] q;
    /* access modifiers changed from: private */
    public int size;

    public CircularQueue() {
        this(256);
    }

    public CircularQueue(int i) {
        this(i, 1073741824);
    }

    public CircularQueue(int i, int i2) {
        this.size = 0;
        this.producerIndex = 0;
        this.consumerIndex = 0;
        if (i > i2) {
            throw new IllegalArgumentException("Capacity greater than maximum");
        } else if (i2 <= 1073741824) {
            this.capacity = 1;
            while (true) {
                int i3 = this.capacity;
                if (i3 >= i) {
                    break;
                }
                this.capacity = i3 << 1;
            }
            this.maxCapacity = 1;
            while (true) {
                int i4 = this.maxCapacity;
                if (i4 < i2) {
                    this.maxCapacity = i4 << 1;
                } else {
                    int i5 = this.capacity;
                    this.bitmask = i5 - 1;
                    this.q = new Object[i5];
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Maximum capacity greater than allowed");
        }
    }

    private CircularQueue(CircularQueue circularQueue) {
        this.size = 0;
        this.producerIndex = 0;
        this.consumerIndex = 0;
        this.size = circularQueue.size;
        this.producerIndex = circularQueue.producerIndex;
        this.consumerIndex = circularQueue.consumerIndex;
        this.capacity = circularQueue.capacity;
        this.maxCapacity = circularQueue.maxCapacity;
        this.bitmask = circularQueue.bitmask;
        Object[] objArr = new Object[circularQueue.q.length];
        this.q = objArr;
        System.arraycopy(circularQueue.q, 0, objArr, 0, objArr.length);
    }

    private boolean expandQueue() {
        int i = this.capacity;
        if (i == this.maxCapacity) {
            return false;
        }
        Object[] objArr = this.q;
        int i2 = i + i;
        this.capacity = i2;
        this.bitmask = i2 - 1;
        Object[] objArr2 = new Object[i2];
        this.q = objArr2;
        int i3 = this.consumerIndex;
        System.arraycopy(objArr, i3, objArr2, 0, i - i3);
        int i4 = this.consumerIndex;
        if (i4 != 0) {
            System.arraycopy(objArr, 0, this.q, i - i4, i4);
        }
        this.consumerIndex = 0;
        this.producerIndex = this.size;
        return true;
    }

    public boolean add(Object obj) {
        if (this.size == this.capacity && !expandQueue()) {
            return false;
        }
        this.size++;
        Object[] objArr = this.q;
        int i = this.producerIndex;
        objArr[i] = obj;
        this.producerIndex = this.bitmask & (i + 1);
        return true;
    }

    public Object remove() {
        int i = this.size;
        if (i == 0) {
            return null;
        }
        this.size = i - 1;
        Object[] objArr = this.q;
        int i2 = this.consumerIndex;
        Object obj = objArr[i2];
        objArr[i2] = null;
        this.consumerIndex = this.bitmask & (i2 + 1);
        return obj;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    public Object peek() {
        if (this.size == 0) {
            return null;
        }
        return this.q[this.consumerIndex];
    }

    public void clear() {
        Arrays.fill(this.q, (Object) null);
        this.size = 0;
        this.producerIndex = 0;
        this.consumerIndex = 0;
    }

    public Object clone() {
        return new CircularQueue(this);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append(" - capacity: '");
        stringBuffer.append(capacity());
        stringBuffer.append("' size: '");
        stringBuffer.append(size());
        stringBuffer.append("'");
        StringBuffer stringBuffer2 = new StringBuffer(stringBuffer.toString());
        if (this.size > 0) {
            stringBuffer2.append(" elements:");
            for (int i = 0; i < this.size; i++) {
                stringBuffer2.append(10);
                stringBuffer2.append(9);
                stringBuffer2.append(this.q[(this.consumerIndex + i) & this.bitmask].toString());
            }
        }
        return stringBuffer2.toString();
    }

    public Iterator iterator() {
        return new Iterator() {
            private final int ci;
            private int i = this.ci;
            private final int pi = CircularQueue.this.producerIndex;
            private int s = CircularQueue.this.size;

            public boolean hasNext() {
                checkForModification();
                return this.s > 0;
            }

            public Object next() {
                checkForModification();
                int i2 = this.s;
                if (i2 != 0) {
                    this.s = i2 - 1;
                    Object[] access$300 = CircularQueue.this.q;
                    int i3 = this.i;
                    Object obj = access$300[i3];
                    this.i = (i3 + 1) & CircularQueue.this.bitmask;
                    return obj;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            {
                this.ci = CircularQueue.this.consumerIndex;
            }

            private void checkForModification() {
                if (this.ci != CircularQueue.this.consumerIndex) {
                    throw new ConcurrentModificationException();
                } else if (this.pi != CircularQueue.this.producerIndex) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}

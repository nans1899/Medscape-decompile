package org.liquidplayer.javascript;

import androidx.collection.LongSparseArray;
import java.lang.ref.WeakReference;

class JNIJSValue extends JNIObject {
    static final long ODDBALL_FALSE = 10;
    static final long ODDBALL_NULL = 6;
    static final long ODDBALL_TRUE = 14;
    static final long ODDBALL_UNDEFINED = 2;
    private static LongSparseArray<WeakReference<JNIJSValue>> objectsHash = new LongSparseArray<>();

    private native void Finalize(long j);

    private static native long canonicalReference(long j);

    private static native long createJSONString(long j) throws JNIJSException;

    private static native boolean isArray(long j);

    private static native boolean isBoolean(long j);

    private static native boolean isDate(long j);

    private static native boolean isEqual(long j, long j2) throws JNIJSException;

    private static native boolean isFloat32Array(long j);

    private static native boolean isFloat64Array(long j);

    private static native boolean isInt16Array(long j);

    private static native boolean isInt32Array(long j);

    private static native boolean isInt8Array(long j);

    private static native boolean isNull(long j);

    private static native boolean isNumber(long j);

    private static boolean isReferenceJNI(long j) {
        return (j & 1) == 1;
    }

    static boolean isReferenceObject(long j) {
        return (j & 3) == 3;
    }

    static boolean isReferencePrimitiveNumber(long j) {
        return (j & 3) == 0;
    }

    private static native boolean isStrictEqual(long j, long j2);

    private static native boolean isString(long j);

    private static native boolean isTypedArray(long j);

    private static native boolean isUint16Array(long j);

    private static native boolean isUint32Array(long j);

    private static native boolean isUint8Array(long j);

    private static native boolean isUint8ClampedArray(long j);

    private static native boolean isUndefined(long j);

    static native long makeFromJSONString(long j, String str);

    static native long makeNumber(long j, double d);

    static native long makeString(long j, String str);

    private static native boolean toBoolean(long j);

    private static native double toNumber(long j) throws JNIJSException;

    private static native long toObject(long j) throws JNIJSException;

    private static native String toStringCopy(long j) throws JNIJSException;

    protected JNIJSValue(long j) {
        super(j);
    }

    public void finalize() throws Throwable {
        super.finalize();
        if (isReferenceJNI(this.reference)) {
            Finalize(this.reference);
        }
    }

    public int hashCode() {
        return (int) this.reference;
    }

    /* access modifiers changed from: package-private */
    public boolean isUndefined() {
        return isUndefined(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isNull() {
        return isNull(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isBoolean() {
        return isBoolean(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isNumber() {
        return isNumber(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isString() {
        return isString(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isObject() {
        return isReferenceObject(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isArray() {
        return isArray(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isDate() {
        return isDate(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isTypedArray() {
        return isTypedArray(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isInt8Array() {
        return isInt8Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isInt16Array() {
        return isInt16Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isInt32Array() {
        return isInt32Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isUint8Array() {
        return isUint8Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isUint8ClampedArray() {
        return isUint8ClampedArray(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isUint32Array() {
        return isUint32Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isUint16Array() {
        return isUint16Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isFloat32Array() {
        return isFloat32Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isFloat64Array() {
        return isFloat64Array(this.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isEqual(JNIJSValue jNIJSValue) throws JNIJSException {
        return isEqual(this.reference, jNIJSValue.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isStrictEqual(JNIJSValue jNIJSValue) {
        return isStrictEqual(this.reference, jNIJSValue.reference);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue createJSONString() throws JNIJSException {
        return fromRef(createJSONString(this.reference));
    }

    /* access modifiers changed from: package-private */
    public boolean toBoolean() {
        return toBoolean(this.reference);
    }

    /* access modifiers changed from: package-private */
    public double toNumber() throws JNIJSException {
        return toNumber(this.reference);
    }

    /* access modifiers changed from: package-private */
    public String toStringCopy() throws JNIJSException {
        return toStringCopy(this.reference);
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject toObject() throws JNIJSException {
        if (this instanceof JNIJSObject) {
            return (JNIJSObject) this;
        }
        return JNIJSObject.fromRef(toObject(this.reference));
    }

    static JNIJSValue fromRef(long j) {
        JNIJSValue jNIJSValue;
        JNIJSValue jNIJSValue2;
        if (isReferenceJNI(j)) {
            WeakReference weakReference = objectsHash.get(j);
            if (weakReference != null && (jNIJSValue2 = (JNIJSValue) weakReference.get()) != null) {
                return jNIJSValue2;
            }
            if (isReferenceObject(j)) {
                jNIJSValue = new JNIJSObject(j);
            } else {
                jNIJSValue = new JNIJSValue(j);
            }
            objectsHash.put(j, new WeakReference(jNIJSValue));
            return jNIJSValue;
        } else if (j == ODDBALL_FALSE || j == ODDBALL_TRUE) {
            return new JNIJSBoolean(j);
        } else {
            if (j == 6) {
                return new JNIJSNull(j);
            }
            return j == 2 ? new JNIJSUndefined(j) : new JNIJSNumber(j);
        }
    }

    /* access modifiers changed from: package-private */
    public long canonicalReference() {
        return canonicalReference(this.reference);
    }
}

package org.liquidplayer.javascript;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

abstract class JNIJSPrimitive extends JNIJSValue {
    /* access modifiers changed from: package-private */
    public JNIJSValue createJSONString() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isArray() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isBoolean() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isDate() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isFloat32Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isFloat64Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isInt16Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isInt32Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isInt8Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isNull() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isNumber() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isObject() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isString() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isTypedArray() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isUint16Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isUint32Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isUint8Array() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isUint8ClampedArray() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isUndefined() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean primitiveEqual(JNIJSPrimitive jNIJSPrimitive) {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean primitiveStrictEqual(JNIJSPrimitive jNIJSPrimitive) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean toBoolean() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public double toNumber() {
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject toObject() {
        return null;
    }

    /* access modifiers changed from: package-private */
    public String toStringCopy() {
        return null;
    }

    JNIJSPrimitive(long j) {
        super(j);
    }

    /* access modifiers changed from: package-private */
    public boolean isEqual(JNIJSValue jNIJSValue) throws JNIJSException {
        if (jNIJSValue instanceof JNIJSPrimitive) {
            return primitiveEqual((JNIJSPrimitive) jNIJSValue);
        }
        return jNIJSValue.isEqual(this);
    }

    /* access modifiers changed from: package-private */
    public boolean isStrictEqual(JNIJSValue jNIJSValue) {
        if (jNIJSValue instanceof JNIJSPrimitive) {
            return primitiveStrictEqual((JNIJSPrimitive) jNIJSValue);
        }
        return jNIJSValue.isStrictEqual(this);
    }
}

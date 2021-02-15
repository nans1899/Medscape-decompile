package org.liquidplayer.javascript;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

class JNIJSNull extends JNIJSPrimitive {
    /* access modifiers changed from: package-private */
    public JNIJSValue createJSONString() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isNull() {
        return true;
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
    public String toStringCopy() {
        return "null";
    }

    JNIJSNull(long j) {
        super(j);
    }

    /* access modifiers changed from: protected */
    public boolean primitiveEqual(JNIJSPrimitive jNIJSPrimitive) {
        return (jNIJSPrimitive instanceof JNIJSNull) || (jNIJSPrimitive instanceof JNIJSUndefined);
    }

    /* access modifiers changed from: protected */
    public boolean primitiveStrictEqual(JNIJSPrimitive jNIJSPrimitive) {
        return jNIJSPrimitive instanceof JNIJSNull;
    }
}

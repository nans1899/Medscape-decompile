package org.liquidplayer.javascript;

import com.google.android.gms.ads.AdError;

class JNIJSUndefined extends JNIJSPrimitive {
    /* access modifiers changed from: package-private */
    public JNIJSValue createJSONString() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isUndefined() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean toBoolean() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public double toNumber() {
        return Double.NaN;
    }

    /* access modifiers changed from: package-private */
    public String toStringCopy() {
        return AdError.UNDEFINED_DOMAIN;
    }

    JNIJSUndefined(long j) {
        super(j);
    }

    /* access modifiers changed from: protected */
    public boolean primitiveEqual(JNIJSPrimitive jNIJSPrimitive) {
        return (jNIJSPrimitive instanceof JNIJSNull) || (jNIJSPrimitive instanceof JNIJSUndefined);
    }

    /* access modifiers changed from: protected */
    public boolean primitiveStrictEqual(JNIJSPrimitive jNIJSPrimitive) {
        return jNIJSPrimitive instanceof JNIJSUndefined;
    }
}

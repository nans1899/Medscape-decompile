package org.liquidplayer.javascript;

import com.facebook.internal.ServerProtocol;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

class JNIJSBoolean extends JNIJSPrimitive {
    /* access modifiers changed from: package-private */
    public JNIJSValue createJSONString() {
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isBoolean() {
        return true;
    }

    JNIJSBoolean(long j) {
        super(j);
    }

    /* access modifiers changed from: protected */
    public boolean primitiveEqual(JNIJSPrimitive jNIJSPrimitive) {
        if (jNIJSPrimitive instanceof JNIJSNumber) {
            return jNIJSPrimitive.primitiveEqual(this);
        }
        return (jNIJSPrimitive instanceof JNIJSBoolean) && ((JNIJSBoolean) jNIJSPrimitive).reference == this.reference;
    }

    /* access modifiers changed from: protected */
    public boolean primitiveStrictEqual(JNIJSPrimitive jNIJSPrimitive) {
        return (jNIJSPrimitive instanceof JNIJSBoolean) && ((JNIJSBoolean) jNIJSPrimitive).reference == this.reference;
    }

    /* access modifiers changed from: package-private */
    public boolean toBoolean() {
        return this.reference == 14;
    }

    /* access modifiers changed from: package-private */
    public double toNumber() {
        if (this.reference == 14) {
            return 1.0d;
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    /* access modifiers changed from: package-private */
    public String toStringCopy() {
        return this.reference == 14 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
    }
}

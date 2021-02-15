package org.liquidplayer.javascript;

class JNIJSContext extends JNIObject {
    private static native void Finalize(long j);

    private static native long createInGroup(long j);

    private static native long evaluateScript(long j, String str, String str2, int i) throws JNIJSException;

    private static native long getGlobalObject(long j);

    private static native long getGroup(long j);

    private JNIJSContext(long j) {
        super(j);
    }

    public void finalize() throws Throwable {
        super.finalize();
        Finalize(this.reference);
    }

    static JNIJSContext createContext(JNIJSContextGroup jNIJSContextGroup) {
        return fromRef(createInGroup(jNIJSContextGroup.reference));
    }

    /* access modifiers changed from: package-private */
    public JNIJSContextGroup getGroup() {
        return JNIJSContextGroup.fromRef(getGroup(this.reference));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject getGlobalObject() {
        return JNIJSObject.fromRef(getGlobalObject(this.reference));
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue makeUndefined() {
        return JNIJSValue.fromRef(2);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue makeNull() {
        return JNIJSValue.fromRef(6);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue makeBoolean(boolean z) {
        return JNIJSValue.fromRef(z ? 14 : 10);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue makeNumber(double d) {
        long doubleToLongBits = Double.doubleToLongBits(d);
        if (!JNIJSValue.isReferencePrimitiveNumber(doubleToLongBits)) {
            doubleToLongBits = JNIJSValue.makeNumber(this.reference, d);
        }
        return JNIJSValue.fromRef(doubleToLongBits);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue makeString(String str) {
        return JNIJSValue.fromRef(JNIJSValue.makeString(this.reference, str));
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue makeFromJSONString(String str) {
        return JNIJSValue.fromRef(JNIJSValue.makeFromJSONString(this.reference, str));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject make() {
        return JNIJSObject.fromRef(JNIJSObject.make(this.reference));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject makeArray(JNIJSValue[] jNIJSValueArr) throws JNIJSException {
        long[] jArr = new long[jNIJSValueArr.length];
        for (int i = 0; i < jNIJSValueArr.length; i++) {
            jArr[i] = jNIJSValueArr[i].reference;
        }
        return JNIJSObject.fromRef(JNIJSObject.makeArray(this.reference, jArr));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject makeDate(long[] jArr) {
        return JNIJSObject.fromRef(JNIJSObject.makeDate(this.reference, jArr));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject makeError(String str) {
        return JNIJSObject.fromRef(JNIJSObject.makeError(this.reference, str));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject makeRegExp(String str, String str2) throws JNIJSException {
        return JNIJSObject.fromRef(JNIJSObject.makeRegExp(this.reference, str, str2));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject makeFunction(String str, String str2, String str3, int i) throws JNIJSException {
        return JNIJSFunction.fromRef(JNIJSObject.makeFunction(this.reference, str, str2, str3, i));
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject makeFunctionWithCallback(JSFunction jSFunction, String str) {
        return JNIJSFunction.fromRef(JNIJSFunction.makeFunctionWithCallback(jSFunction, this.reference, str));
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue evaluateScript(String str, String str2, int i) throws JNIJSException {
        return JNIJSValue.fromRef(evaluateScript(this.reference, str, str2, i));
    }

    static JNIJSContext fromRef(long j) {
        return new JNIJSContext(j);
    }
}

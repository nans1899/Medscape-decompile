package org.liquidplayer.javascript;

class JNIJSObject extends JNIJSValue {
    private static native long callAsConstructor(long j, long[] jArr) throws JNIJSException;

    private static native long callAsFunction(long j, long j2, long[] jArr) throws JNIJSException;

    private static native String[] copyPropertyNames(long j);

    private static native boolean deleteProperty(long j, String str) throws JNIJSException;

    private static native long getProperty(long j, String str) throws JNIJSException;

    private static native long getPropertyAtIndex(long j, int i) throws JNIJSException;

    private static native long getPrototype(long j);

    private static native boolean hasProperty(long j, String str);

    private static native boolean isConstructor(long j);

    static native boolean isFunction(long j);

    static native long make(long j);

    static native long makeArray(long j, long[] jArr) throws JNIJSException;

    static native long makeDate(long j, long[] jArr);

    static native long makeError(long j, String str);

    static native long makeFunction(long j, String str, String str2, String str3, int i) throws JNIJSException;

    static native long makeRegExp(long j, String str, String str2) throws JNIJSException;

    private static native void setProperty(long j, String str, long j2, int i) throws JNIJSException;

    private static native void setPropertyAtIndex(long j, int i, long j2) throws JNIJSException;

    private static native void setPrototype(long j, long j2);

    protected JNIJSObject(long j) {
        super(j);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue getPrototype() {
        return JNIJSValue.fromRef(getPrototype(this.reference));
    }

    /* access modifiers changed from: package-private */
    public void setPrototype(JNIJSValue jNIJSValue) {
        setPrototype(this.reference, jNIJSValue.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean hasProperty(String str) {
        return hasProperty(this.reference, str);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue getProperty(String str) throws JNIJSException {
        return JNIJSValue.fromRef(getProperty(this.reference, str));
    }

    /* access modifiers changed from: package-private */
    public void setProperty(String str, JNIJSValue jNIJSValue, int i) throws JNIJSException {
        setProperty(this.reference, str, jNIJSValue.reference, i);
    }

    /* access modifiers changed from: package-private */
    public boolean deleteProperty(String str) throws JNIJSException {
        return deleteProperty(this.reference, str);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue getPropertyAtIndex(int i) throws JNIJSException {
        return JNIJSValue.fromRef(getPropertyAtIndex(this.reference, i));
    }

    /* access modifiers changed from: package-private */
    public void setPropertyAtIndex(int i, JNIJSValue jNIJSValue) throws JNIJSException {
        setPropertyAtIndex(this.reference, i, jNIJSValue.reference);
    }

    /* access modifiers changed from: package-private */
    public boolean isFunction() {
        return isFunction(this.reference);
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue callAsFunction(JNIJSObject jNIJSObject, JNIJSValue[] jNIJSValueArr) throws JNIJSException {
        long[] jArr = new long[jNIJSValueArr.length];
        for (int i = 0; i < jNIJSValueArr.length; i++) {
            jArr[i] = jNIJSValueArr[i].reference;
        }
        return JNIJSValue.fromRef(callAsFunction(this.reference, jNIJSObject == null ? 0 : jNIJSObject.reference, jArr));
    }

    /* access modifiers changed from: package-private */
    public boolean isConstructor() {
        return isConstructor(this.reference);
    }

    /* access modifiers changed from: package-private */
    public JNIJSObject callAsConstructor(JNIJSValue[] jNIJSValueArr) throws JNIJSException {
        long[] jArr = new long[jNIJSValueArr.length];
        for (int i = 0; i < jNIJSValueArr.length; i++) {
            jArr[i] = jNIJSValueArr[i].reference;
        }
        return fromRef(callAsConstructor(this.reference, jArr));
    }

    /* access modifiers changed from: package-private */
    public String[] copyPropertyNames() {
        return copyPropertyNames(this.reference);
    }

    static JNIJSObject fromRef(long j) {
        return (JNIJSObject) JNIJSValue.fromRef(j);
    }
}

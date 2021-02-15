package org.liquidplayer.javascript;

class JNIJSFunction {
    static native long makeFunctionWithCallback(JSFunction jSFunction, long j, String str);

    static native void setException(long j, long j2);

    JNIJSFunction() {
    }

    static JNIJSObject fromRef(long j) {
        return (JNIJSObject) JNIJSValue.fromRef(j);
    }
}

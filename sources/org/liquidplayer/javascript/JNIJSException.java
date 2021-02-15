package org.liquidplayer.javascript;

class JNIJSException extends Exception {
    final JNIJSValue exception;

    JNIJSException(long j) {
        this.exception = JNIJSValue.fromRef(j);
    }
}

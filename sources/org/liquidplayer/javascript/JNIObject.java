package org.liquidplayer.javascript;

abstract class JNIObject {
    protected long reference;

    JNIObject(long j) {
        this.reference = j;
    }
}

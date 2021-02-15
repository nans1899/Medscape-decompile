package org.liquidplayer.javascript;

class JNILoopPreserver extends JNIObject {
    private native void Finalize(long j);

    static native long create(long j);

    private static native void release(long j);

    JNILoopPreserver(long j) {
        super(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        Finalize(this.reference);
    }

    /* access modifiers changed from: package-private */
    public void release() {
        release(this.reference);
    }
}

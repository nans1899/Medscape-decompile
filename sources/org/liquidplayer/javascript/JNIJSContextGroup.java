package org.liquidplayer.javascript;

class JNIJSContextGroup extends JNIObject {
    private static native void Finalize(long j);

    private static native void TerminateExecution(long j);

    private static native long create();

    static native int createSnapshot(String str, String str2);

    private static native long createWithSnapshotFile(String str);

    private static native boolean isManaged(long j);

    private static native void runInContextGroup(long j, Object obj, Runnable runnable);

    private JNIJSContextGroup(long j) {
        super(j);
    }

    public void finalize() throws Throwable {
        super.finalize();
        Finalize(this.reference);
    }

    static JNIJSContextGroup createGroup() {
        return new JNIJSContextGroup(create());
    }

    static JNIJSContextGroup createGroupWithSnapshot(String str) {
        return new JNIJSContextGroup(createWithSnapshotFile(str));
    }

    static JNIJSContextGroup fromRef(long j) {
        return new JNIJSContextGroup(j);
    }

    /* access modifiers changed from: package-private */
    public boolean isManaged() {
        return isManaged(this.reference);
    }

    /* access modifiers changed from: package-private */
    public void runInContextGroup(Object obj, Runnable runnable) {
        runInContextGroup(this.reference, obj, runnable);
    }

    /* access modifiers changed from: package-private */
    public JNILoopPreserver newLoopPreserver() {
        return new JNILoopPreserver(JNILoopPreserver.create(this.reference));
    }

    /* access modifiers changed from: package-private */
    public void terminateExecution() {
        TerminateExecution(this.reference);
    }
}

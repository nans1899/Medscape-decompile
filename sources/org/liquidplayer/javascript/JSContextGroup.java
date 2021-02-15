package org.liquidplayer.javascript;

import android.os.Process;
import java.io.File;
import java.io.IOException;

public class JSContextGroup {
    private JNIJSContextGroup group;
    private boolean hasDedicatedThread;
    private int mContextGroupThreadTid;

    static {
        JSContext.init();
    }

    public class LoopPreserver {
        private JNILoopPreserver m_reference;

        LoopPreserver(JNILoopPreserver jNILoopPreserver) {
            this.m_reference = jNILoopPreserver;
        }

        public void release() {
            JNILoopPreserver jNILoopPreserver = this.m_reference;
            if (jNILoopPreserver != null) {
                jNILoopPreserver.release();
            }
        }
    }

    public JSContextGroup() {
        this.mContextGroupThreadTid = 0;
        this.group = JNIJSContextGroup.createGroup();
        this.hasDedicatedThread = false;
    }

    JSContextGroup(JNIJSContextGroup jNIJSContextGroup) {
        this.mContextGroupThreadTid = 0;
        this.group = jNIJSContextGroup;
        this.hasDedicatedThread = jNIJSContextGroup.isManaged();
    }

    JSContextGroup(long j) {
        this(JNIJSContextGroup.fromRef(j));
    }

    public JSContextGroup(File file) {
        this.mContextGroupThreadTid = 0;
        this.group = JNIJSContextGroup.createGroupWithSnapshot(file.getAbsolutePath());
        this.hasDedicatedThread = false;
    }

    public static File createSnapshot(String str, File file) throws IOException {
        int createSnapshot = JNIJSContextGroup.createSnapshot(str, file.getAbsolutePath());
        if (createSnapshot == -4) {
            throw new IOException("Could not close file");
        } else if (createSnapshot == -3) {
            throw new IOException("Could not write file");
        } else if (createSnapshot == -2) {
            throw new IOException("Unable to open for writing");
        } else if (createSnapshot == -1) {
            throw new IOException("Failed to create snapshot");
        } else if (createSnapshot == 0) {
            return file;
        } else {
            throw new IOException();
        }
    }

    public void terminateExecution() {
        this.group.terminateExecution();
    }

    private boolean hasDedicatedThread() {
        return this.hasDedicatedThread;
    }

    /* access modifiers changed from: package-private */
    public JNIJSContextGroup groupRef() {
        return this.group;
    }

    public long groupHash() {
        return this.group.reference;
    }

    /* access modifiers changed from: package-private */
    public boolean isOnThread() {
        return !hasDedicatedThread() || Process.myTid() == this.mContextGroupThreadTid;
    }

    private void inContextCallback(Runnable runnable) {
        this.mContextGroupThreadTid = Process.myTid();
        runnable.run();
    }

    public void schedule(Runnable runnable) {
        this.group.runInContextGroup(this, runnable);
    }

    public LoopPreserver keepAlive() {
        return new LoopPreserver(this.group.newLoopPreserver());
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof JSContextGroup) && groupRef() != null && groupRef().equals(((JSContextGroup) obj).groupRef()));
    }
}

package org.liquidplayer.node;

import android.content.Context;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSContextGroup;
import org.liquidplayer.javascript.JSException;
import org.liquidplayer.javascript.JSFunction;
import org.liquidplayer.javascript.JSObject;

public class Process {
    public static final int kMediaAccessPermissionsNone = 0;
    public static final int kMediaAccessPermissionsRW = 3;
    public static final int kMediaAccessPermissionsRead = 1;
    public static final int kMediaAccessPermissionsWrite = 2;
    /* access modifiers changed from: private */
    public final Context androidCtx;
    /* access modifiers changed from: private */
    public long exitCode;
    /* access modifiers changed from: private */
    public FileSystem fs = null;
    /* access modifiers changed from: private */
    public JSContext holdContext;
    private boolean isActive = false;
    private boolean isDone = false;
    protected WeakReference<JSContext> jscontext = new WeakReference<>((Object) null);
    private ArrayList<EventListener> listeners = new ArrayList<>();
    /* access modifiers changed from: private */
    public final int mediaAccessMask;
    private boolean notifiedExit = false;
    /* access modifiers changed from: private */
    public final long processRef;
    /* access modifiers changed from: private */
    public final String uniqueID;

    public interface EventListener {
        void onProcessAboutToExit(Process process, int i);

        void onProcessExit(Process process, int i);

        void onProcessFailed(Process process, Exception exc);

        void onProcessStart(Process process, JSContext jSContext);
    }

    public enum UninstallScope {
        Local,
        Global
    }

    /* access modifiers changed from: private */
    public native void dispose(long j);

    /* access modifiers changed from: private */
    public native void runInThread(long j);

    /* access modifiers changed from: private */
    public native void setFileSystem(long j, long j2);

    private native long start();

    public Process(Context context, String str, int i, EventListener eventListener) {
        addEventListener(eventListener);
        this.processRef = start();
        new Thread((ThreadGroup) null, new Runnable() {
            public void run() {
                android.os.Process.setThreadPriority(-4);
                Process process = Process.this;
                process.runInThread(process.processRef);
            }
        }, "nodejs").start();
        this.androidCtx = context;
        this.uniqueID = str;
        this.mediaAccessMask = i;
    }

    public synchronized void addEventListener(final EventListener eventListener) {
        if (!this.listeners.contains(eventListener)) {
            this.listeners.add(eventListener);
        }
        JSContext jSContext = (JSContext) this.jscontext.get();
        if (isActive() && jSContext != null) {
            jSContext.sync(new Runnable() {
                public void run() {
                    JSContext jSContext = (JSContext) Process.this.jscontext.get();
                    if (!Process.this.isActive() || jSContext == null) {
                        EventListener eventListener = eventListener;
                        Process process = Process.this;
                        eventListener.onProcessExit(process, Long.valueOf(process.exitCode).intValue());
                        return;
                    }
                    eventListener.onProcessStart(Process.this, jSContext);
                }
            });
        } else if (this.isDone) {
            eventListener.onProcessExit(this, Long.valueOf(this.exitCode).intValue());
        }
    }

    public synchronized void removeEventListener(EventListener eventListener) {
        this.listeners.remove(eventListener);
    }

    public boolean isActive() {
        return this.isActive && this.jscontext != null;
    }

    public void exit(int i) {
        JSContext jSContext = (JSContext) this.jscontext.get();
        if (isActive() && jSContext != null) {
            jSContext.evaluateScript("process.exit(" + i + ");");
        }
    }

    public JSContextGroup.LoopPreserver keepAlive() {
        JSContext jSContext = (JSContext) this.jscontext.get();
        if (!isActive() || jSContext == null) {
            return null;
        }
        return jSContext.getGroup().keepAlive();
    }

    public static void uninstall(Context context, String str, UninstallScope uninstallScope) {
        FileSystem.uninstallLocal(context, str);
        if (uninstallScope == UninstallScope.Global) {
            FileSystem.uninstallGlobal(context, str);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void eventOnStart(JSContext jSContext) {
        for (EventListener onProcessStart : (EventListener[]) this.listeners.toArray(new EventListener[0])) {
            onProcessStart.onProcessStart(this, jSContext);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void eventOnAboutToExit(long j) {
        this.exitCode = j;
        for (EventListener onProcessAboutToExit : (EventListener[]) this.listeners.toArray(new EventListener[0])) {
            onProcessAboutToExit.onProcessAboutToExit(this, Long.valueOf(j).intValue());
        }
    }

    /* access modifiers changed from: private */
    public synchronized void eventOnProcessFailed(Exception exc) {
        for (EventListener onProcessFailed : (EventListener[]) this.listeners.toArray(new EventListener[0])) {
            onProcessFailed.onProcessFailed(this, exc);
        }
    }

    private void eventOnExit(long j) {
        this.exitCode = j;
        if (!this.notifiedExit) {
            this.notifiedExit = true;
            for (EventListener onProcessExit : (EventListener[]) this.listeners.toArray(new EventListener[0])) {
                onProcessExit.onProcessExit(this, Long.valueOf(j).intValue());
            }
            FileSystem fileSystem = this.fs;
            if (fileSystem != null) {
                fileSystem.cleanUp();
            }
            this.fs = null;
        }
    }

    private class ProcessContext extends JSContext {
        private final long mJscCtxRef;

        ProcessContext(long j, JSContextGroup jSContextGroup, long j2) {
            super(j, jSContextGroup);
            this.mJscCtxRef = j2;
        }

        public long getJSCContext() {
            return this.mJscCtxRef;
        }
    }

    private void onNodeStarted(long j, long j2, long j3) {
        Class<JSContextGroup> cls = JSContextGroup.class;
        try {
            Constructor<JSContextGroup> declaredConstructor = cls.getDeclaredConstructor(new Class[]{Long.TYPE});
            declaredConstructor.setAccessible(true);
            long j4 = j;
            this.holdContext = new ProcessContext(j4, declaredConstructor.newInstance(new Object[]{Long.valueOf(j2)}), j3);
            this.jscontext = new WeakReference<>(this.holdContext);
            this.isActive = true;
            JSContext jSContext = this.holdContext;
            final long j5 = j;
            jSContext.property("__nodedroid_onLoad", new JSFunction(jSContext, "__nodedroid_onLoad") {
                public void __nodedroid_onLoad() {
                    JSContext jSContext = (JSContext) Process.this.jscontext.get();
                    if (Process.this.isActive() && jSContext != null) {
                        jSContext.deleteProperty("__nodedroid_onLoad");
                        FileSystem unused = Process.this.fs = new FileSystem(jSContext, Process.this.androidCtx, Process.this.uniqueID, Process.this.mediaAccessMask);
                        Process process = Process.this;
                        process.setFileSystem(j5, process.fs.valueHash());
                        AnonymousClass1 r1 = new JSFunction(this.context, "onExit") {
                            public void onExit(int i) {
                                Process.this.eventOnAboutToExit((long) i);
                            }
                        };
                        new JSFunction(this.context, "__onExit", new String[]{"exitFunc"}, "process.on('exit',exitFunc);", (String) null, 0).call((JSObject) null, r1);
                        AnonymousClass2 r2 = new JSFunction(this.context, "onUncaughtException") {
                            public void onUncaughtException(JSObject jSObject) {
                                Log.i("Unhandled", "There is an unhandled exception!");
                                Log.i("Unhandled", jSObject.toString());
                                Log.i("Unhandled", jSObject.property("stack").toString());
                                Process.this.eventOnProcessFailed(new JSException(jSObject));
                                this.context.evaluateScript("process.exit(process.exitCode === undefined ? -1 : process.exitCode)");
                            }
                        };
                        new JSFunction(this.context, "__onUncaughtException", new String[]{"handleFunc"}, "process.on('uncaughtException',handleFunc);", (String) null, 0).call((JSObject) null, r2);
                        JSObject object = jSContext.property("process").toObject().property("stdout").toObject();
                        object.property("write", new JSFunction(object.getContext(), "write") {
                            public void write(String str) {
                                Log.i("stdout", str);
                            }
                        });
                        JSObject object2 = jSContext.property("process").toObject().property("stderr").toObject();
                        object2.property("write", new JSFunction(object2.getContext(), "write") {
                            public void write(String str) {
                                Log.e("stderr", str);
                            }
                        });
                        jSContext.evaluateScript("global.process.versions.liquidcore = '0.6.2'");
                        Process.this.eventOnStart(jSContext);
                        JSContext unused2 = Process.this.holdContext = null;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onNodeExit(long j) {
        this.isActive = false;
        this.jscontext = null;
        this.isDone = true;
        eventOnExit(j);
        new Thread() {
            public void run() {
                Process process = Process.this;
                process.dispose(process.processRef);
            }
        }.start();
    }

    static {
        try {
            Method declaredMethod = JSContext.class.getDeclaredMethod("init", new Class[0]);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke((Object) null, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

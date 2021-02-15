package org.liquidplayer.javascript;

import android.util.LongSparseArray;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.concurrent.Semaphore;

public class JSContext extends JSObject {
    private JSContextGroup contextGroup;
    private JNIJSContext ctxRef;
    private IJSExceptionHandler exceptionHandler;
    private final LongSparseArray<WeakReference<JSObject>> objects;

    public interface IJSExceptionHandler {
        void handle(JSException jSException);
    }

    static void init() {
    }

    public long getJSCContext() {
        return 0;
    }

    static {
        System.loadLibrary("node");
        System.loadLibrary("liquidcore");
    }

    public void sync(final Runnable runnable) {
        if (isOnThread()) {
            runnable.run();
            return;
        }
        final Semaphore semaphore = new Semaphore(0);
        getGroup().schedule(new Runnable() {
            public void run() {
                runnable.run();
                semaphore.release();
            }
        });
        semaphore.acquireUninterruptibly();
    }

    private boolean isOnThread() {
        return this.ctxRef == null || getGroup().isOnThread();
    }

    protected JSContext(long j, JSContextGroup jSContextGroup) {
        this.objects = new LongSparseArray<>();
        this.context = this;
        this.contextGroup = jSContextGroup;
        JNIJSContext fromRef = JNIJSContext.fromRef(j);
        this.ctxRef = fromRef;
        this.valueRef = fromRef.getGlobalObject();
        addJSExports();
    }

    public JSContext() {
        this(new JSContextGroup());
    }

    public JSContext(JSContextGroup jSContextGroup) {
        this.objects = new LongSparseArray<>();
        this.context = this;
        this.contextGroup = jSContextGroup;
        JNIJSContext createContext = JNIJSContext.createContext(jSContextGroup.groupRef());
        this.ctxRef = createContext;
        this.valueRef = createContext.getGlobalObject();
        addJSExports();
        this.context.property("__NativeTimer__", new JSFunction(this.context, "__NativeTimer__") {
            public void __NativeTimer__(final JSFunction jSFunction) {
                final int intValue = jSFunction.property("millis").toNumber().intValue();
                new Thread() {
                    public void run() {
                        try {
                            Thread.sleep((long) intValue);
                        } catch (InterruptedException unused) {
                        } finally {
                            JSFunction jSFunction = jSFunction;
                            jSFunction.call(jSFunction, new Object[0]);
                        }
                    }
                }.start();
            }
        });
        this.context.evaluateScript(" let makeTimer = function(callback, millis) { \n   if (!callback || typeof callback !== 'function') { \n     throw new TypeError('[ERR_INVALID_CALLBACK]: Callback must be a function') \n   } \n\n   let args = Array.from(arguments) \n   args.shift() \n   args.shift() \n\n   var timer = function() { \n     if (!this.destroyed) { \n       if (this.interval) { \n         __NativeTimer__(this) \n       } else { \n         this.destroyed = true \n       } \n       this.callback.apply(this, this.args) \n     } \n   } \n\n   timer.callback = callback \n   timer.args = args \n   timer.millis = millis \n   timer.destroyed = false \n\n   return timer \n } \n\n function setTimeout(callback, millis) { \n   var timer = makeTimer(...arguments) \n   timer.interval = false \n   __NativeTimer__(timer) \n   return timer \n } \n\n function setInterval(callback, millis) { \n   var timer = makeTimer(...arguments) \n   timer.interval = true \n   __NativeTimer__(timer) \n   return timer \n }\n\n function clearTimeout(timer) { \n   if (timer && typeof timer === 'function') { \n      timer.destroyed = true \n   } \n } \n", "InitTimer", 1);
    }

    public JSContext(Class<?> cls) {
        this(new JSContextGroup(), cls);
    }

    public JSContext(JSContextGroup jSContextGroup, Class<?> cls) {
        this(jSContextGroup);
        for (Method method : cls.getDeclaredMethods()) {
            property(method.getName(), new JSFunction(this.context, method, (Class<? extends JSObject>) JSObject.class, (JSObject) this.context));
        }
    }

    public void setExceptionHandler(IJSExceptionHandler iJSExceptionHandler) {
        this.exceptionHandler = iJSExceptionHandler;
    }

    public void clearExceptionHandler() {
        this.exceptionHandler = null;
    }

    /* access modifiers changed from: package-private */
    public void throwJSException(JSException jSException) {
        IJSExceptionHandler iJSExceptionHandler = this.exceptionHandler;
        if (iJSExceptionHandler != null) {
            this.exceptionHandler = null;
            iJSExceptionHandler.handle(jSException);
            this.exceptionHandler = iJSExceptionHandler;
            return;
        }
        throw jSException;
    }

    public JSContextGroup getGroup() {
        if (this.contextGroup == null) {
            JNIJSContextGroup group = this.ctxRef.getGroup();
            if (group == null) {
                return null;
            }
            this.contextGroup = new JSContextGroup(group);
        }
        return this.contextGroup;
    }

    /* access modifiers changed from: package-private */
    public JNIJSContext ctxRef() {
        return this.ctxRef;
    }

    public JSValue evaluateScript(String str, String str2, int i) {
        if (str2 == null) {
            str2 = "<code>";
        }
        try {
            return new JSValue(this.ctxRef.evaluateScript(str, str2, i), this.context);
        } catch (JNIJSException e) {
            throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return new JSValue(this);
        }
    }

    public JSValue evaluateScript(String str) {
        return evaluateScript(str, (String) null, 0);
    }

    /* access modifiers changed from: package-private */
    public void persistObject(JSObject jSObject) {
        this.objects.put(jSObject.canonical(), new WeakReference(jSObject));
        jSObject.persisted = true;
    }

    /* access modifiers changed from: package-private */
    public void finalizeObject(JSObject jSObject) {
        this.objects.remove(jSObject.canonical());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: org.liquidplayer.javascript.JSObject} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.liquidplayer.javascript.JSObject getObjectFromRef(org.liquidplayer.javascript.JNIJSObject r4) {
        /*
            r3 = this;
            org.liquidplayer.javascript.JNIJSValue r0 = r3.valueRef()
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x000b
            return r3
        L_0x000b:
            android.util.LongSparseArray<java.lang.ref.WeakReference<org.liquidplayer.javascript.JSObject>> r0 = r3.objects
            long r1 = r4.canonicalReference()
            java.lang.Object r0 = r0.get(r1)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            r1 = 0
            if (r0 == 0) goto L_0x0021
            java.lang.Object r0 = r0.get()
            r1 = r0
            org.liquidplayer.javascript.JSObject r1 = (org.liquidplayer.javascript.JSObject) r1
        L_0x0021:
            if (r1 != 0) goto L_0x0052
            org.liquidplayer.javascript.JSObject r1 = new org.liquidplayer.javascript.JSObject
            r1.<init>((org.liquidplayer.javascript.JNIJSObject) r4, (org.liquidplayer.javascript.JSContext) r3)
            java.lang.Boolean r0 = r1.isArray()
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0038
            org.liquidplayer.javascript.JSArray r1 = new org.liquidplayer.javascript.JSArray
            r1.<init>((org.liquidplayer.javascript.JNIJSObject) r4, (org.liquidplayer.javascript.JSContext) r3)
            goto L_0x0052
        L_0x0038:
            java.lang.Boolean r0 = r1.isTypedArray()
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0047
            org.liquidplayer.javascript.JSTypedArray r1 = org.liquidplayer.javascript.JSTypedArray.from(r1)
            goto L_0x0052
        L_0x0047:
            boolean r0 = r1.isFunction()
            if (r0 == 0) goto L_0x0052
            org.liquidplayer.javascript.JSFunction r1 = new org.liquidplayer.javascript.JSFunction
            r1.<init>((org.liquidplayer.javascript.JNIJSObject) r4, (org.liquidplayer.javascript.JSContext) r3)
        L_0x0052:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.liquidplayer.javascript.JSContext.getObjectFromRef(org.liquidplayer.javascript.JNIJSObject):org.liquidplayer.javascript.JSObject");
    }
}

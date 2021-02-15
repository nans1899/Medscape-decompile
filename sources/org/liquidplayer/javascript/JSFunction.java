package org.liquidplayer.javascript;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class JSFunction extends JSObject {
    private JSObject invokeObject;
    protected Method method;
    private Class<?>[] pType;
    private Class<? extends JSObject> subclass;

    public JSFunction(JSContext jSContext, String str, String[] strArr, String str2, String str3, int i) {
        this.subclass = null;
        this.method = null;
        this.invokeObject = null;
        this.context = jSContext;
        StringBuilder sb = new StringBuilder("(function(");
        for (int i2 = 0; i2 < strArr.length; i2++) {
            sb.append(strArr[i2]);
            if (i2 < strArr.length - 1) {
                sb.append(",");
            }
        }
        sb.append("){");
        sb.append(str2);
        sb.append("})");
        try {
            this.valueRef = this.context.ctxRef().makeFunction(str, sb.toString(), str3 == null ? "<anonymous>" : str3, i);
        } catch (JNIJSException e) {
            this.valueRef = testException(e.exception);
        }
        addJSExports();
        this.context.persistObject(this);
    }

    public JSFunction(JSContext jSContext, String str, String str2, String... strArr) {
        this(jSContext, str, strArr, str2, (String) null, 1);
    }

    private JNIJSObject testException(JNIJSValue jNIJSValue) {
        this.context.throwJSException(new JSException(new JSValue(jNIJSValue, this.context)));
        return this.context.ctxRef().make();
    }

    public JSFunction(JSContext jSContext, Method method2, Class<? extends JSObject> cls, JSObject jSObject) {
        this.subclass = null;
        this.method = null;
        this.invokeObject = null;
        this.context = jSContext;
        this.method = method2;
        method2.setAccessible(true);
        this.pType = this.method.getParameterTypes();
        this.invokeObject = jSObject == null ? this : jSObject;
        this.valueRef = this.context.ctxRef().makeFunctionWithCallback(this, method2.getName());
        this.subclass = cls;
        addJSExports();
        this.context.persistObject(this);
        this.context.zombies.add(this);
    }

    public JSFunction(JSContext jSContext, Method method2, Class<? extends JSObject> cls) {
        this(jSContext, method2, cls, (JSObject) null);
    }

    public JSFunction(JSContext jSContext, Method method2) {
        this(jSContext, method2, (Class<? extends JSObject>) JSObject.class);
    }

    public JSFunction(JSContext jSContext) {
        this(jSContext, (String) null);
    }

    public JSFunction(JSContext jSContext, String str, Class<? extends JSObject> cls, JSObject jSObject) {
        this.subclass = null;
        this.method = null;
        this.invokeObject = null;
        this.context = jSContext;
        this.invokeObject = jSObject == null ? this : jSObject;
        str = str == null ? "__nullFunc" : str;
        Method[] methods = this.invokeObject.getClass().getMethods();
        int length = methods.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Method method2 = methods[i];
            if (method2.getName().equals(str)) {
                this.method = method2;
                break;
            }
            i++;
        }
        Method method3 = this.method;
        if (method3 == null) {
            this.valueRef = this.context.ctxRef().makeUndefined();
            this.context.throwJSException(new JSException(this.context, "No such method. Did you make it public?"));
        } else {
            method3.setAccessible(true);
            this.pType = this.method.getParameterTypes();
            this.valueRef = this.context.ctxRef().makeFunctionWithCallback(this, this.method.getName());
            this.subclass = cls;
            addJSExports();
        }
        this.context.persistObject(this);
        this.context.zombies.add(this);
    }

    public JSFunction(JSContext jSContext, String str, Class<? extends JSObject> cls) {
        this(jSContext, str, cls, (JSObject) null);
    }

    public JSFunction(JSContext jSContext, String str) {
        this(jSContext, str, (Class<? extends JSObject>) JSObject.class);
    }

    public JSFunction(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext);
        this.subclass = null;
        this.method = null;
        this.invokeObject = null;
    }

    public JSValue call(JSObject jSObject, Object... objArr) {
        return apply(jSObject, objArr);
    }

    private JNIJSValue[] argsToValueRefs(Object[] objArr) {
        JSValue jSValue;
        JSValue jSValue2;
        ArrayList arrayList = new ArrayList();
        if (objArr != null) {
            for (Object[] objArr2 : objArr) {
                if (objArr2 == null) {
                    break;
                }
                if (objArr2.getClass() == Void.class) {
                    jSValue = new JSValue(this.context);
                } else if (objArr2 instanceof JSValue) {
                    jSValue = (JSValue) objArr2;
                } else {
                    if (objArr2 instanceof Object[]) {
                        jSValue2 = new JSArray(this.context, objArr2, Object.class);
                    } else {
                        jSValue2 = new JSValue(this.context, (Object) objArr2);
                    }
                    jSValue = jSValue2;
                }
                arrayList.add(jSValue);
            }
        }
        JNIJSValue[] jNIJSValueArr = new JNIJSValue[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            jNIJSValueArr[i] = ((JSValue) arrayList.get(i)).valueRef();
        }
        return jNIJSValueArr;
    }

    public JSValue apply(JSObject jSObject, Object[] objArr) {
        JNIJSObject jNIJSObject;
        try {
            JNIJSObject JNI = JNI();
            if (jSObject == null) {
                jNIJSObject = null;
            } else {
                jNIJSObject = jSObject.JNI();
            }
            return new JSValue(JNI.callAsFunction(jNIJSObject, argsToValueRefs(objArr)), this.context);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return new JSValue(this.context);
        }
    }

    public JSValue call() {
        return call((JSObject) null, new Object[0]);
    }

    public JSObject newInstance(Object... objArr) {
        try {
            return this.context.getObjectFromRef(JNI().callAsConstructor(argsToValueRefs(objArr)));
        } catch (JNIJSException e) {
            return new JSObject(testException(e.exception), this.context);
        }
    }

    private long functionCallback(long j, long[] jArr) {
        JNIJSObject jNIJSObject;
        try {
            JSValue[] jSValueArr = new JSValue[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                JNIJSValue fromRef = JNIJSValue.fromRef(jArr[i]);
                if (fromRef.isObject()) {
                    jSValueArr[i] = this.context.getObjectFromRef(fromRef.toObject());
                } else {
                    jSValueArr[i] = new JSValue(fromRef, this.context);
                }
            }
            JSObject jSObject = null;
            if (!JNIJSValue.isReferenceObject(j)) {
                jNIJSObject = null;
            } else {
                jNIJSObject = JNIJSObject.fromRef(j);
            }
            if (jNIJSObject != null) {
                jSObject = this.context.getObjectFromRef(jNIJSObject);
            }
            JSValue function = function(jSObject, jSValueArr, this.invokeObject);
            if (function == null) {
                return 2;
            }
            return function.valueRef().reference;
        } catch (JNIJSException e) {
            e.printStackTrace();
            throw new AssertionError();
        } catch (JSException e2) {
            e2.printStackTrace();
            JNIJSFunction.setException(valueRef().reference, e2.getError().valueRef().reference);
            return 2;
        }
    }

    /* access modifiers changed from: protected */
    public JSValue function(JSObject jSObject, JSValue[] jSValueArr) {
        return function(jSObject, jSValueArr, this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v0, resolved type: org.liquidplayer.javascript.JSObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: org.liquidplayer.javascript.JSObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: org.liquidplayer.javascript.JSValue[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: org.liquidplayer.javascript.JSValue[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: org.liquidplayer.javascript.JSObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: org.liquidplayer.javascript.JSValue} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: org.liquidplayer.javascript.JSObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: org.liquidplayer.javascript.JSValue} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: org.liquidplayer.javascript.JSObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: org.liquidplayer.javascript.JSValue[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: org.liquidplayer.javascript.JSObject} */
    /* JADX WARNING: type inference failed for: r5v3, types: [org.liquidplayer.javascript.JSValue] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0091 A[Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0092 A[Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.liquidplayer.javascript.JSValue function(org.liquidplayer.javascript.JSObject r9, org.liquidplayer.javascript.JSValue[] r10, org.liquidplayer.javascript.JSObject r11) {
        /*
            r8 = this;
            java.lang.Class<?>[] r0 = r8.pType
            int r1 = r0.length
            java.lang.Object[] r2 = new java.lang.Object[r1]
            int r3 = r0.length
            r4 = 0
            r5 = 0
            if (r3 <= 0) goto L_0x0064
            int r3 = r10.length
            int r0 = r0.length
            if (r3 < r0) goto L_0x0064
            int r0 = r10.length
            int r0 = r0 + -1
            r0 = r10[r0]
            java.lang.Boolean r0 = r0.isArray()
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0064
            java.lang.Class<?>[] r0 = r8.pType
            int r3 = r0.length
            int r3 = r3 + -1
            r0 = r0[r3]
            boolean r0 = r0.isArray()
            if (r0 == 0) goto L_0x0064
            r0 = 0
        L_0x002b:
            int r3 = r1 + -1
            if (r0 >= r3) goto L_0x004b
            int r3 = r10.length
            if (r0 >= r3) goto L_0x0046
            r3 = r10[r0]
            if (r3 != 0) goto L_0x0039
            r2[r0] = r5
            goto L_0x0048
        L_0x0039:
            r3 = r10[r0]
            java.lang.Class<?>[] r6 = r8.pType
            r6 = r6[r0]
            java.lang.Object r3 = r3.toJavaObject(r6)
            r2[r0] = r3
            goto L_0x0048
        L_0x0046:
            r2[r0] = r5
        L_0x0048:
            int r0 = r0 + 1
            goto L_0x002b
        L_0x004b:
            int r1 = r10.length
            java.lang.Class<?>[] r6 = r8.pType
            int r6 = r6.length
            int r1 = r1 - r6
            int r1 = r1 + 1
            org.liquidplayer.javascript.JSValue[] r1 = new org.liquidplayer.javascript.JSValue[r1]
        L_0x0054:
            int r6 = r10.length
            if (r0 >= r6) goto L_0x0061
            int r6 = r4 + 1
            r7 = r10[r0]
            r1[r4] = r7
            int r0 = r0 + 1
            r4 = r6
            goto L_0x0054
        L_0x0061:
            r2[r3] = r1
            goto L_0x0082
        L_0x0064:
            if (r4 >= r1) goto L_0x0082
            int r0 = r10.length
            if (r4 >= r0) goto L_0x007d
            r0 = r10[r4]
            if (r0 != 0) goto L_0x0070
            r2[r4] = r5
            goto L_0x007f
        L_0x0070:
            r0 = r10[r4]
            java.lang.Class<?>[] r3 = r8.pType
            r3 = r3[r4]
            java.lang.Object r0 = r0.toJavaObject(r3)
            r2[r4] = r0
            goto L_0x007f
        L_0x007d:
            r2[r4] = r5
        L_0x007f:
            int r4 = r4 + 1
            goto L_0x0064
        L_0x0082:
            org.liquidplayer.javascript.JSObject r10 = r11.getThis()     // Catch:{ InvocationTargetException -> 0x00c2, IllegalAccessException -> 0x00af, all -> 0x00ad }
            r11.setThis(r9)     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            java.lang.reflect.Method r9 = r8.method     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            java.lang.Object r9 = r9.invoke(r11, r2)     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            if (r9 != 0) goto L_0x0092
            goto L_0x00a2
        L_0x0092:
            boolean r0 = r9 instanceof org.liquidplayer.javascript.JSValue     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            if (r0 == 0) goto L_0x009a
            org.liquidplayer.javascript.JSValue r9 = (org.liquidplayer.javascript.JSValue) r9     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            r5 = r9
            goto L_0x00a2
        L_0x009a:
            org.liquidplayer.javascript.JSValue r0 = new org.liquidplayer.javascript.JSValue     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            org.liquidplayer.javascript.JSContext r1 = r8.context     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            r0.<init>((org.liquidplayer.javascript.JSContext) r1, (java.lang.Object) r9)     // Catch:{ InvocationTargetException -> 0x00ab, IllegalAccessException -> 0x00a9 }
            r5 = r0
        L_0x00a2:
            r11.setThis(r10)
            goto L_0x00d8
        L_0x00a6:
            r9 = move-exception
            r5 = r10
            goto L_0x00d9
        L_0x00a9:
            r9 = move-exception
            goto L_0x00b1
        L_0x00ab:
            r9 = move-exception
            goto L_0x00c4
        L_0x00ad:
            r9 = move-exception
            goto L_0x00d9
        L_0x00af:
            r9 = move-exception
            r10 = r5
        L_0x00b1:
            org.liquidplayer.javascript.JSContext r0 = r8.context     // Catch:{ all -> 0x00a6 }
            org.liquidplayer.javascript.JSException r1 = new org.liquidplayer.javascript.JSException     // Catch:{ all -> 0x00a6 }
            org.liquidplayer.javascript.JSContext r2 = r8.context     // Catch:{ all -> 0x00a6 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00a6 }
            r1.<init>(r2, r9)     // Catch:{ all -> 0x00a6 }
            r0.throwJSException(r1)     // Catch:{ all -> 0x00a6 }
            goto L_0x00a2
        L_0x00c2:
            r9 = move-exception
            r10 = r5
        L_0x00c4:
            r9.printStackTrace()     // Catch:{ all -> 0x00a6 }
            org.liquidplayer.javascript.JSContext r0 = r8.context     // Catch:{ all -> 0x00a6 }
            org.liquidplayer.javascript.JSException r1 = new org.liquidplayer.javascript.JSException     // Catch:{ all -> 0x00a6 }
            org.liquidplayer.javascript.JSContext r2 = r8.context     // Catch:{ all -> 0x00a6 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x00a6 }
            r1.<init>(r2, r9)     // Catch:{ all -> 0x00a6 }
            r0.throwJSException(r1)     // Catch:{ all -> 0x00a6 }
            goto L_0x00a2
        L_0x00d8:
            return r5
        L_0x00d9:
            r11.setThis(r5)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.liquidplayer.javascript.JSFunction.function(org.liquidplayer.javascript.JSObject, org.liquidplayer.javascript.JSValue[], org.liquidplayer.javascript.JSObject):org.liquidplayer.javascript.JSValue");
    }

    /* access modifiers changed from: protected */
    public void constructor(JNIJSObject jNIJSObject, JSValue[] jSValueArr) {
        try {
            JSObject jSObject = (JSObject) this.subclass.getConstructor(new Class[0]).newInstance(new Object[0]);
            jSObject.context = this.context;
            jSObject.valueRef = jNIJSObject;
            jSObject.addJSExports();
            function(jSObject, jSValueArr);
            this.context.persistObject(jSObject);
            this.context.zombies.add(jSObject);
        } catch (NoSuchMethodException e) {
            this.context.throwJSException(new JSException(this.context, e.toString() + "If " + this.subclass.getName() + " is an embedded class, did you specify it as 'static'?"));
        } catch (InvocationTargetException e2) {
            this.context.throwJSException(new JSException(this.context, e2.toString() + "; Did you remember to call super?"));
        } catch (IllegalAccessException e3) {
            this.context.throwJSException(new JSException(this.context, e3.toString() + "; Is your constructor public?"));
        } catch (InstantiationException e4) {
            this.context.throwJSException(new JSException(this.context, e4.toString()));
        }
    }

    private void constructorCallback(long j, long[] jArr) {
        try {
            JSValue[] jSValueArr = new JSValue[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                JNIJSValue fromRef = JNIJSValue.fromRef(jArr[i]);
                if (fromRef.isObject()) {
                    jSValueArr[i] = this.context.getObjectFromRef(fromRef.toObject());
                } else {
                    jSValueArr[i] = new JSValue(fromRef, this.context);
                }
            }
            constructor(JNIJSObject.fromRef(j), jSValueArr);
        } catch (JNIJSException e) {
            e.printStackTrace();
            throw new AssertionError();
        } catch (JSException e2) {
            JNIJSFunction.setException(valueRef().reference, e2.getError().valueRef().reference);
        }
    }

    protected JSFunction() {
        this.subclass = null;
        this.method = null;
        this.invokeObject = null;
    }
}

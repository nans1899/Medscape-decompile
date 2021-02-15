package org.liquidplayer.javascript;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSObject extends JSValue {
    public static final int JSPropertyAttributeDontDelete = 8;
    public static final int JSPropertyAttributeDontEnum = 4;
    public static final int JSPropertyAttributeNone = 0;
    public static final int JSPropertyAttributeReadOnly = 2;
    private long canon;
    boolean persisted;
    private JSObject thiz;
    final List<JSObject> zombies;

    @Retention(RetentionPolicy.RUNTIME)
    public @interface jsexport {
        int attributes() default 0;

        Class type() default Object.class;
    }

    public class Property<T> {
        private Integer attributes = null;
        private String name = null;
        private Class pT;
        private T temp = null;

        private Property() {
        }

        public void set(T t) {
            this.temp = t;
            if (t != null) {
                this.pT = t.getClass();
            }
            String str = this.name;
            if (str != null) {
                Integer num = this.attributes;
                if (num != null) {
                    JSObject.this.property(str, t, num.intValue());
                    this.attributes = null;
                    return;
                }
                JSObject.this.property(str, t);
            }
        }

        public T get() {
            if (this.temp == null && this.pT == Object.class) {
                JSObject.this.context.throwJSException(new JSException(JSObject.this.context, "object has no defined type"));
                return null;
            }
            String str = this.name;
            if (str != null) {
                return JSObject.this.property(str).toJavaObject(this.pT);
            }
            return this.temp;
        }

        /* access modifiers changed from: private */
        public void setName(String str, Class cls, int i) {
            this.name = str;
            Integer valueOf = Integer.valueOf(i);
            this.attributes = valueOf;
            T t = this.temp;
            if (t != null) {
                JSObject.this.property(this.name, t, valueOf.intValue());
                this.attributes = null;
                return;
            }
            this.pT = cls;
            JSObject jSObject = JSObject.this;
            jSObject.property(this.name, new JSValue(jSObject.context));
        }
    }

    public JSObject(JSContext jSContext) {
        this.zombies = new ArrayList();
        this.thiz = null;
        this.canon = 0;
        this.persisted = false;
        this.context = jSContext;
        this.valueRef = this.context.ctxRef().make();
        addJSExports();
        this.context.persistObject(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: org.liquidplayer.javascript.JSObject$Property} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addJSExports() {
        /*
            r10 = this;
            java.lang.Class<org.liquidplayer.javascript.JSObject> r0 = org.liquidplayer.javascript.JSObject.class
            java.lang.Class r1 = r10.getClass()     // Catch:{ Exception -> 0x00a5 }
            java.lang.reflect.Field[] r1 = r1.getDeclaredFields()     // Catch:{ Exception -> 0x00a5 }
            int r2 = r1.length     // Catch:{ Exception -> 0x00a5 }
            r3 = 0
            r4 = 0
        L_0x000d:
            r5 = 1
            if (r4 >= r2) goto L_0x0070
            r6 = r1[r4]     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<org.liquidplayer.javascript.JSObject$jsexport> r7 = org.liquidplayer.javascript.JSObject.jsexport.class
            boolean r7 = r6.isAnnotationPresent(r7)     // Catch:{ Exception -> 0x00a5 }
            if (r7 == 0) goto L_0x006d
            r6.setAccessible(r5)     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<org.liquidplayer.javascript.JSObject$Property> r7 = org.liquidplayer.javascript.JSObject.Property.class
            java.lang.Class r8 = r6.getType()     // Catch:{ Exception -> 0x00a5 }
            boolean r7 = r7.isAssignableFrom(r8)     // Catch:{ Exception -> 0x00a5 }
            if (r7 == 0) goto L_0x006d
            java.lang.Object r7 = r6.get(r10)     // Catch:{ Exception -> 0x00a5 }
            org.liquidplayer.javascript.JSObject$Property r7 = (org.liquidplayer.javascript.JSObject.Property) r7     // Catch:{ Exception -> 0x00a5 }
            if (r7 != 0) goto L_0x004e
            java.lang.Class r7 = r6.getType()     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class[] r8 = new java.lang.Class[r5]     // Catch:{ Exception -> 0x00a5 }
            r8[r3] = r0     // Catch:{ Exception -> 0x00a5 }
            java.lang.reflect.Constructor r7 = r7.getDeclaredConstructor(r8)     // Catch:{ Exception -> 0x00a5 }
            r7.setAccessible(r5)     // Catch:{ Exception -> 0x00a5 }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x00a5 }
            r5[r3] = r10     // Catch:{ Exception -> 0x00a5 }
            java.lang.Object r5 = r7.newInstance(r5)     // Catch:{ Exception -> 0x00a5 }
            r7 = r5
            org.liquidplayer.javascript.JSObject$Property r7 = (org.liquidplayer.javascript.JSObject.Property) r7     // Catch:{ Exception -> 0x00a5 }
            r6.set(r10, r7)     // Catch:{ Exception -> 0x00a5 }
        L_0x004e:
            java.lang.String r5 = r6.getName()     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<org.liquidplayer.javascript.JSObject$jsexport> r8 = org.liquidplayer.javascript.JSObject.jsexport.class
            java.lang.annotation.Annotation r8 = r6.getAnnotation(r8)     // Catch:{ Exception -> 0x00a5 }
            org.liquidplayer.javascript.JSObject$jsexport r8 = (org.liquidplayer.javascript.JSObject.jsexport) r8     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class r8 = r8.type()     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<org.liquidplayer.javascript.JSObject$jsexport> r9 = org.liquidplayer.javascript.JSObject.jsexport.class
            java.lang.annotation.Annotation r6 = r6.getAnnotation(r9)     // Catch:{ Exception -> 0x00a5 }
            org.liquidplayer.javascript.JSObject$jsexport r6 = (org.liquidplayer.javascript.JSObject.jsexport) r6     // Catch:{ Exception -> 0x00a5 }
            int r6 = r6.attributes()     // Catch:{ Exception -> 0x00a5 }
            r7.setName(r5, r8, r6)     // Catch:{ Exception -> 0x00a5 }
        L_0x006d:
            int r4 = r4 + 1
            goto L_0x000d
        L_0x0070:
            java.lang.Class r1 = r10.getClass()     // Catch:{ Exception -> 0x00a5 }
            java.lang.reflect.Method[] r1 = r1.getDeclaredMethods()     // Catch:{ Exception -> 0x00a5 }
            int r2 = r1.length     // Catch:{ Exception -> 0x00a5 }
        L_0x0079:
            if (r3 >= r2) goto L_0x00b6
            r4 = r1[r3]     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<org.liquidplayer.javascript.JSObject$jsexport> r6 = org.liquidplayer.javascript.JSObject.jsexport.class
            boolean r6 = r4.isAnnotationPresent(r6)     // Catch:{ Exception -> 0x00a5 }
            if (r6 == 0) goto L_0x00a2
            r4.setAccessible(r5)     // Catch:{ Exception -> 0x00a5 }
            org.liquidplayer.javascript.JSFunction r6 = new org.liquidplayer.javascript.JSFunction     // Catch:{ Exception -> 0x00a5 }
            org.liquidplayer.javascript.JSContext r7 = r10.context     // Catch:{ Exception -> 0x00a5 }
            r6.<init>((org.liquidplayer.javascript.JSContext) r7, (java.lang.reflect.Method) r4, (java.lang.Class<? extends org.liquidplayer.javascript.JSObject>) r0, (org.liquidplayer.javascript.JSObject) r10)     // Catch:{ Exception -> 0x00a5 }
            java.lang.String r7 = r4.getName()     // Catch:{ Exception -> 0x00a5 }
            java.lang.Class<org.liquidplayer.javascript.JSObject$jsexport> r8 = org.liquidplayer.javascript.JSObject.jsexport.class
            java.lang.annotation.Annotation r4 = r4.getAnnotation(r8)     // Catch:{ Exception -> 0x00a5 }
            org.liquidplayer.javascript.JSObject$jsexport r4 = (org.liquidplayer.javascript.JSObject.jsexport) r4     // Catch:{ Exception -> 0x00a5 }
            int r4 = r4.attributes()     // Catch:{ Exception -> 0x00a5 }
            r10.property(r7, r6, r4)     // Catch:{ Exception -> 0x00a5 }
        L_0x00a2:
            int r3 = r3 + 1
            goto L_0x0079
        L_0x00a5:
            r0 = move-exception
            org.liquidplayer.javascript.JSContext r1 = r10.context
            org.liquidplayer.javascript.JSException r2 = new org.liquidplayer.javascript.JSException
            org.liquidplayer.javascript.JSContext r3 = r10.context
            java.lang.String r0 = r0.toString()
            r2.<init>(r3, r0)
            r1.throwJSException(r2)
        L_0x00b6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.liquidplayer.javascript.JSObject.addJSExports():void");
    }

    public JSObject() {
        this.zombies = new ArrayList();
        this.thiz = null;
        this.canon = 0;
        this.persisted = false;
    }

    protected JSObject(JNIJSObject jNIJSObject, JSContext jSContext) {
        super((JNIJSValue) jNIJSObject, jSContext);
        this.zombies = new ArrayList();
        this.thiz = null;
        this.canon = 0;
        this.persisted = false;
        this.context.persistObject(this);
    }

    public JSObject(JSContext jSContext, Class<?> cls) {
        this.zombies = new ArrayList();
        this.thiz = null;
        this.canon = 0;
        this.persisted = false;
        this.context = jSContext;
        this.valueRef = this.context.ctxRef().make();
        addJSExports();
        for (Method method : cls.getDeclaredMethods()) {
            property(method.getName(), new JSFunction(this.context, method, (Class<? extends JSObject>) JSObject.class, this));
        }
        this.context.persistObject(this);
    }

    public JSObject(JSContext jSContext, Map map) {
        this(jSContext);
        new JSObjectPropertiesMap(this, Object.class).putAll(map);
        addJSExports();
    }

    public boolean hasProperty(String str) {
        return JNI().hasProperty(str);
    }

    public JSValue property(String str) {
        try {
            return new JSValue(JNI().getProperty(str), this.context);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return new JSValue(this.context);
        }
    }

    public void property(String str, Object obj, int i) {
        try {
            JNI().setProperty(str, obj instanceof JSValue ? ((JSValue) obj).valueRef() : new JSValue(this.context, obj).valueRef(), i);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
        }
    }

    public void property(String str, Object obj) {
        property(str, obj, 0);
    }

    public boolean deleteProperty(String str) {
        try {
            return JNI().deleteProperty(str);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return false;
        }
    }

    public JSValue propertyAtIndex(int i) {
        try {
            return new JSValue(JNI().getPropertyAtIndex(i), this.context);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return new JSValue(this.context);
        }
    }

    public void propertyAtIndex(int i, Object obj) {
        JNIJSValue jNIJSValue;
        try {
            JNIJSObject JNI = JNI();
            if (obj instanceof JSValue) {
                jNIJSValue = ((JSValue) obj).valueRef();
            } else {
                jNIJSValue = new JSValue(this.context, obj).valueRef();
            }
            JNI.setPropertyAtIndex(i, jNIJSValue);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
        }
    }

    public String[] propertyNames() {
        return JNI().copyPropertyNames();
    }

    public boolean isFunction() {
        return JNI().isFunction();
    }

    public boolean isConstructor() {
        return JNI().isConstructor();
    }

    public int hashCode() {
        return (int) valueRef().reference;
    }

    public JSValue prototype() {
        return new JSValue(JNI().getPrototype(), this.context);
    }

    public void prototype(JSValue jSValue) {
        JNI().setPrototype(jSValue.valueRef());
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (this.context != null && this.persisted) {
            this.context.finalizeObject(this);
        }
        super.finalize();
    }

    /* access modifiers changed from: protected */
    public void setThis(JSObject jSObject) {
        this.thiz = jSObject;
    }

    public JSObject getThis() {
        return this.thiz;
    }

    public JSValue __nullFunc() {
        return new JSValue(this.context);
    }

    /* access modifiers changed from: protected */
    public JNIJSObject JNI() {
        return (JNIJSObject) valueRef();
    }

    /* access modifiers changed from: package-private */
    public long canonical() {
        if (this.canon == 0) {
            this.canon = valueRef().canonicalReference();
        }
        return this.canon;
    }
}

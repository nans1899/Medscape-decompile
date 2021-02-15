package org.mozilla.javascript;

import java.lang.reflect.Array;
import java.util.Map;
import kotlin.text.Typography;

public class NativeJavaClass extends NativeJavaObject implements Function {
    static final String javaClassPropertyName = "__javaObject__";
    static final long serialVersionUID = -6460763940409461664L;
    private Map<String, FieldAndMethods> staticFieldAndMethods;

    public String getClassName() {
        return "JavaClass";
    }

    public NativeJavaClass() {
    }

    public NativeJavaClass(Scriptable scriptable, Class<?> cls) {
        this(scriptable, cls, false);
    }

    public NativeJavaClass(Scriptable scriptable, Class<?> cls, boolean z) {
        super(scriptable, cls, (Class<?>) null, z);
    }

    /* access modifiers changed from: protected */
    public void initMembers() {
        Class cls = (Class) this.javaObject;
        this.members = JavaMembers.lookupClass(this.parent, cls, cls, this.isAdapter);
        this.staticFieldAndMethods = this.members.getFieldAndMethodsObjects(this, cls, true);
    }

    public boolean has(String str, Scriptable scriptable) {
        return this.members.has(str, true) || javaClassPropertyName.equals(str);
    }

    public Object get(String str, Scriptable scriptable) {
        FieldAndMethods fieldAndMethods;
        if (str.equals("prototype")) {
            return null;
        }
        Map<String, FieldAndMethods> map = this.staticFieldAndMethods;
        if (map != null && (fieldAndMethods = map.get(str)) != null) {
            return fieldAndMethods;
        }
        if (this.members.has(str, true)) {
            return this.members.get(this, str, this.javaObject, true);
        }
        Context context = Context.getContext();
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        WrapFactory wrapFactory = context.getWrapFactory();
        if (javaClassPropertyName.equals(str)) {
            return wrapFactory.wrap(context, topLevelScope, this.javaObject, ScriptRuntime.ClassClass);
        }
        Class<?> findNestedClass = findNestedClass(getClassObject(), str);
        if (findNestedClass != null) {
            Scriptable wrapJavaClass = wrapFactory.wrapJavaClass(context, topLevelScope, findNestedClass);
            wrapJavaClass.setParentScope(this);
            return wrapJavaClass;
        }
        throw this.members.reportMemberNotFound(str);
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        this.members.put(this, str, this.javaObject, obj, true);
    }

    public Object[] getIds() {
        return this.members.getIds(true);
    }

    public Class<?> getClassObject() {
        return (Class) super.unwrap();
    }

    public Object getDefaultValue(Class<?> cls) {
        if (cls == null || cls == ScriptRuntime.StringClass) {
            return toString();
        }
        if (cls == ScriptRuntime.BooleanClass) {
            return Boolean.TRUE;
        }
        return cls == ScriptRuntime.NumberClass ? ScriptRuntime.NaNobj : this;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length != 1 || !(objArr[0] instanceof Scriptable)) {
            return construct(context, scriptable, objArr);
        }
        Class<?> classObject = getClassObject();
        Scriptable scriptable3 = objArr[0];
        do {
            if ((scriptable3 instanceof Wrapper) && classObject.isInstance(((Wrapper) scriptable3).unwrap())) {
                return scriptable3;
            }
            scriptable3 = scriptable3.getPrototype();
        } while (scriptable3 != null);
        return construct(context, scriptable, objArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0086, code lost:
        if (r6 == null) goto L_0x0089;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.Scriptable construct(org.mozilla.javascript.Context r6, org.mozilla.javascript.Scriptable r7, java.lang.Object[] r8) {
        /*
            r5 = this;
            java.lang.Class r0 = r5.getClassObject()
            int r1 = r0.getModifiers()
            boolean r2 = java.lang.reflect.Modifier.isInterface(r1)
            if (r2 != 0) goto L_0x0036
            boolean r1 = java.lang.reflect.Modifier.isAbstract(r1)
            if (r1 != 0) goto L_0x0036
            org.mozilla.javascript.JavaMembers r1 = r5.members
            org.mozilla.javascript.NativeJavaMethod r1 = r1.ctors
            int r2 = r1.findCachedFunction(r6, r8)
            if (r2 < 0) goto L_0x0027
            org.mozilla.javascript.MemberBox[] r0 = r1.methods
            r0 = r0[r2]
            org.mozilla.javascript.Scriptable r6 = constructSpecific(r6, r7, r8, r0)
            return r6
        L_0x0027:
            java.lang.String r6 = org.mozilla.javascript.NativeJavaMethod.scriptSignature(r8)
            java.lang.String r7 = r0.getName()
            java.lang.String r8 = "msg.no.java.ctor"
            org.mozilla.javascript.EvaluatorException r6 = org.mozilla.javascript.Context.reportRuntimeError2(r8, r7, r6)
            throw r6
        L_0x0036:
            int r1 = r8.length
            if (r1 == 0) goto L_0x0096
            org.mozilla.javascript.Scriptable r1 = org.mozilla.javascript.ScriptableObject.getTopLevelScope(r5)
            java.lang.String r2 = "Dalvik"
            java.lang.String r3 = "java.vm.name"
            java.lang.String r3 = java.lang.System.getProperty(r3)     // Catch:{ Exception -> 0x0081 }
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x0081 }
            r3 = 0
            if (r2 == 0) goto L_0x0066
            boolean r2 = r0.isInterface()     // Catch:{ Exception -> 0x0081 }
            if (r2 == 0) goto L_0x0066
            r8 = r8[r3]     // Catch:{ Exception -> 0x0081 }
            org.mozilla.javascript.ScriptableObject r8 = org.mozilla.javascript.ScriptableObject.ensureScriptableObject(r8)     // Catch:{ Exception -> 0x0081 }
            java.lang.Object r8 = createInterfaceAdapter(r0, r8)     // Catch:{ Exception -> 0x0081 }
            org.mozilla.javascript.WrapFactory r1 = r6.getWrapFactory()     // Catch:{ Exception -> 0x0081 }
            r2 = 0
            org.mozilla.javascript.Scriptable r6 = r1.wrapAsJavaObject(r6, r7, r8, r2)     // Catch:{ Exception -> 0x0081 }
            return r6
        L_0x0066:
            java.lang.String r7 = "JavaAdapter"
            java.lang.Object r7 = r1.get((java.lang.String) r7, (org.mozilla.javascript.Scriptable) r1)     // Catch:{ Exception -> 0x0081 }
            java.lang.Object r2 = NOT_FOUND     // Catch:{ Exception -> 0x0081 }
            if (r7 == r2) goto L_0x0089
            org.mozilla.javascript.Function r7 = (org.mozilla.javascript.Function) r7     // Catch:{ Exception -> 0x0081 }
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0081 }
            r2[r3] = r5     // Catch:{ Exception -> 0x0081 }
            r4 = 1
            r8 = r8[r3]     // Catch:{ Exception -> 0x0081 }
            r2[r4] = r8     // Catch:{ Exception -> 0x0081 }
            org.mozilla.javascript.Scriptable r6 = r7.construct(r6, r1, r2)     // Catch:{ Exception -> 0x0081 }
            return r6
        L_0x0081:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            if (r6 == 0) goto L_0x0089
            goto L_0x008b
        L_0x0089:
            java.lang.String r6 = ""
        L_0x008b:
            java.lang.String r7 = r0.getName()
            java.lang.String r8 = "msg.cant.instantiate"
            org.mozilla.javascript.EvaluatorException r6 = org.mozilla.javascript.Context.reportRuntimeError2(r8, r6, r7)
            throw r6
        L_0x0096:
            java.lang.String r6 = "msg.adapter.zero.args"
            org.mozilla.javascript.EvaluatorException r6 = org.mozilla.javascript.Context.reportRuntimeError0(r6)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJavaClass.construct(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, java.lang.Object[]):org.mozilla.javascript.Scriptable");
    }

    static Scriptable constructSpecific(Context context, Scriptable scriptable, Object[] objArr, MemberBox memberBox) {
        Object constructInternal = constructInternal(objArr, memberBox);
        return context.getWrapFactory().wrapNewObject(context, ScriptableObject.getTopLevelScope(scriptable), constructInternal);
    }

    static Object constructInternal(Object[] objArr, MemberBox memberBox) {
        Object[] objArr2;
        Object obj;
        Class<?>[] clsArr = memberBox.argTypes;
        int i = 0;
        if (memberBox.vararg) {
            objArr2 = new Object[clsArr.length];
            for (int i2 = 0; i2 < clsArr.length - 1; i2++) {
                objArr2[i2] = Context.jsToJava(objArr[i2], clsArr[i2]);
            }
            if (objArr.length != clsArr.length || (objArr[objArr.length - 1] != null && !(objArr[objArr.length - 1] instanceof NativeArray) && !(objArr[objArr.length - 1] instanceof NativeJavaArray))) {
                Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
                Object newInstance = Array.newInstance(componentType, (objArr.length - clsArr.length) + 1);
                while (i < Array.getLength(newInstance)) {
                    Array.set(newInstance, i, Context.jsToJava(objArr[(clsArr.length - 1) + i], componentType));
                    i++;
                }
                obj = newInstance;
            } else {
                obj = Context.jsToJava(objArr[objArr.length - 1], clsArr[clsArr.length - 1]);
            }
            objArr2[clsArr.length - 1] = obj;
        } else {
            objArr2 = objArr;
            while (i < objArr2.length) {
                Object obj2 = objArr2[i];
                Object jsToJava = Context.jsToJava(obj2, clsArr[i]);
                if (jsToJava != obj2) {
                    if (objArr2 == objArr) {
                        objArr2 = (Object[]) objArr.clone();
                    }
                    objArr2[i] = jsToJava;
                }
                i++;
            }
        }
        return memberBox.newInstance(objArr2);
    }

    public String toString() {
        return "[JavaClass " + getClassObject().getName() + "]";
    }

    public boolean hasInstance(Scriptable scriptable) {
        if (!(scriptable instanceof Wrapper) || (scriptable instanceof NativeJavaClass)) {
            return false;
        }
        return getClassObject().isInstance(((Wrapper) scriptable).unwrap());
    }

    private static Class<?> findNestedClass(Class<?> cls, String str) {
        String str2 = cls.getName() + Typography.dollar + str;
        ClassLoader classLoader = cls.getClassLoader();
        if (classLoader == null) {
            return Kit.classOrNull(str2);
        }
        return Kit.classOrNull(classLoader, str2);
    }
}

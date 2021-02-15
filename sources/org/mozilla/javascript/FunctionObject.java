package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class FunctionObject extends BaseFunction {
    public static final int JAVA_BOOLEAN_TYPE = 3;
    public static final int JAVA_DOUBLE_TYPE = 4;
    public static final int JAVA_INT_TYPE = 2;
    public static final int JAVA_OBJECT_TYPE = 6;
    public static final int JAVA_SCRIPTABLE_TYPE = 5;
    public static final int JAVA_STRING_TYPE = 1;
    public static final int JAVA_UNSUPPORTED_TYPE = 0;
    private static final short VARARGS_CTOR = -2;
    private static final short VARARGS_METHOD = -1;
    private static boolean sawSecurityException = false;
    static final long serialVersionUID = -5332312783643935019L;
    private String functionName;
    private transient boolean hasVoidReturn;
    private boolean isStatic;
    MemberBox member;
    private int parmsLength;
    private transient int returnTypeTag;
    private transient byte[] typeTags;

    public FunctionObject(String str, Member member2, Scriptable scriptable) {
        if (member2 instanceof Constructor) {
            this.member = new MemberBox((Constructor<?>) (Constructor) member2);
            this.isStatic = true;
        } else {
            MemberBox memberBox = new MemberBox((Method) member2);
            this.member = memberBox;
            this.isStatic = memberBox.isStatic();
        }
        String name = this.member.getName();
        this.functionName = str;
        Class<?>[] clsArr = this.member.argTypes;
        int length = clsArr.length;
        int i = 0;
        if (length != 4 || (!clsArr[1].isArray() && !clsArr[2].isArray())) {
            this.parmsLength = length;
            if (length > 0) {
                this.typeTags = new byte[length];
                while (i != length) {
                    int typeTag = getTypeTag(clsArr[i]);
                    if (typeTag != 0) {
                        this.typeTags[i] = (byte) typeTag;
                        i++;
                    } else {
                        throw Context.reportRuntimeError2("msg.bad.parms", clsArr[i].getName(), name);
                    }
                }
            }
        } else if (clsArr[1].isArray()) {
            if (this.isStatic && clsArr[0] == ScriptRuntime.ContextClass && clsArr[1].getComponentType() == ScriptRuntime.ObjectClass && clsArr[2] == ScriptRuntime.FunctionClass && clsArr[3] == Boolean.TYPE) {
                this.parmsLength = -2;
            } else {
                throw Context.reportRuntimeError1("msg.varargs.ctor", name);
            }
        } else if (this.isStatic && clsArr[0] == ScriptRuntime.ContextClass && clsArr[1] == ScriptRuntime.ScriptableClass && clsArr[2].getComponentType() == ScriptRuntime.ObjectClass && clsArr[3] == ScriptRuntime.FunctionClass) {
            this.parmsLength = -1;
        } else {
            throw Context.reportRuntimeError1("msg.varargs.fun", name);
        }
        if (this.member.isMethod()) {
            Class<?> returnType = this.member.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        } else {
            Class<?> declaringClass = this.member.getDeclaringClass();
            if (!ScriptRuntime.ScriptableClass.isAssignableFrom(declaringClass)) {
                throw Context.reportRuntimeError1("msg.bad.ctor.return", declaringClass.getName());
            }
        }
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
    }

    public static int getTypeTag(Class<?> cls) {
        if (cls == ScriptRuntime.StringClass) {
            return 1;
        }
        if (cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE) {
            return 2;
        }
        if (cls == ScriptRuntime.BooleanClass || cls == Boolean.TYPE) {
            return 3;
        }
        if (cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
            return 4;
        }
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(cls)) {
            return 5;
        }
        return cls == ScriptRuntime.ObjectClass ? 6 : 0;
    }

    public static Object convertArg(Context context, Scriptable scriptable, Object obj, int i) {
        switch (i) {
            case 1:
                if (obj instanceof String) {
                    return obj;
                }
                return ScriptRuntime.toString(obj);
            case 2:
                if (obj instanceof Integer) {
                    return obj;
                }
                return Integer.valueOf(ScriptRuntime.toInt32(obj));
            case 3:
                if (obj instanceof Boolean) {
                    return obj;
                }
                return ScriptRuntime.toBoolean(obj) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                if (obj instanceof Double) {
                    return obj;
                }
                return new Double(ScriptRuntime.toNumber(obj));
            case 5:
                return ScriptRuntime.toObjectOrNull(context, obj, scriptable);
            case 6:
                return obj;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getArity() {
        int i = this.parmsLength;
        if (i < 0) {
            return 1;
        }
        return i;
    }

    public int getLength() {
        return getArity();
    }

    public String getFunctionName() {
        String str = this.functionName;
        return str == null ? "" : str;
    }

    public Member getMethodOrConstructor() {
        if (this.member.isMethod()) {
            return this.member.method();
        }
        return this.member.ctor();
    }

    static Method findSingleMethod(Method[] methodArr, String str) {
        int length = methodArr.length;
        Method method = null;
        for (int i = 0; i != length; i++) {
            Method method2 = methodArr[i];
            if (method2 != null && str.equals(method2.getName())) {
                if (method == null) {
                    method = method2;
                } else {
                    throw Context.reportRuntimeError2("msg.no.overload", str, method2.getDeclaringClass().getName());
                }
            }
        }
        return method;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.reflect.Method[] getMethodList(java.lang.Class<?> r6) {
        /*
            r0 = 0
            boolean r1 = sawSecurityException     // Catch:{ SecurityException -> 0x000a }
            if (r1 != 0) goto L_0x000d
            java.lang.reflect.Method[] r1 = r6.getDeclaredMethods()     // Catch:{ SecurityException -> 0x000a }
            goto L_0x000e
        L_0x000a:
            r1 = 1
            sawSecurityException = r1
        L_0x000d:
            r1 = r0
        L_0x000e:
            if (r1 != 0) goto L_0x0014
            java.lang.reflect.Method[] r1 = r6.getMethods()
        L_0x0014:
            r2 = 0
            r3 = 0
            r4 = 0
        L_0x0017:
            int r5 = r1.length
            if (r3 >= r5) goto L_0x003b
            boolean r5 = sawSecurityException
            if (r5 == 0) goto L_0x0027
            r5 = r1[r3]
            java.lang.Class r5 = r5.getDeclaringClass()
            if (r5 == r6) goto L_0x0036
            goto L_0x0033
        L_0x0027:
            r5 = r1[r3]
            int r5 = r5.getModifiers()
            boolean r5 = java.lang.reflect.Modifier.isPublic(r5)
            if (r5 != 0) goto L_0x0036
        L_0x0033:
            r1[r3] = r0
            goto L_0x0038
        L_0x0036:
            int r4 = r4 + 1
        L_0x0038:
            int r3 = r3 + 1
            goto L_0x0017
        L_0x003b:
            java.lang.reflect.Method[] r6 = new java.lang.reflect.Method[r4]
            r0 = 0
        L_0x003e:
            int r3 = r1.length
            if (r2 >= r3) goto L_0x004f
            r3 = r1[r2]
            if (r3 == 0) goto L_0x004c
            int r3 = r0 + 1
            r4 = r1[r2]
            r6[r0] = r4
            r0 = r3
        L_0x004c:
            int r2 = r2 + 1
            goto L_0x003e
        L_0x004f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.FunctionObject.getMethodList(java.lang.Class):java.lang.reflect.Method[]");
    }

    public void addAsConstructor(Scriptable scriptable, Scriptable scriptable2) {
        initAsConstructor(scriptable, scriptable2);
        defineProperty(scriptable, scriptable2.getClassName(), this, 2);
    }

    /* access modifiers changed from: package-private */
    public void initAsConstructor(Scriptable scriptable, Scriptable scriptable2) {
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
        setImmunePrototypeProperty(scriptable2);
        scriptable2.setParentScope(this);
        defineProperty(scriptable2, "constructor", this, 7);
        setParentScope(scriptable);
    }

    public static Object convertArg(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        int typeTag = getTypeTag(cls);
        if (typeTag != 0) {
            return convertArg(context, scriptable, obj, typeTag);
        }
        throw Context.reportRuntimeError1("msg.cant.convert", cls.getName());
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object call(org.mozilla.javascript.Context r9, org.mozilla.javascript.Scriptable r10, org.mozilla.javascript.Scriptable r11, java.lang.Object[] r12) {
        /*
            r8 = this;
            int r0 = r12.length
            r1 = 0
            r2 = 0
        L_0x0003:
            if (r2 >= r0) goto L_0x0016
            r3 = r12[r2]
            boolean r3 = r3 instanceof org.mozilla.javascript.ConsString
            if (r3 == 0) goto L_0x0013
            r3 = r12[r2]
            java.lang.String r3 = r3.toString()
            r12[r2] = r3
        L_0x0013:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x0016:
            int r2 = r8.parmsLength
            r3 = 0
            r4 = 1
            if (r2 >= 0) goto L_0x0063
            r0 = -1
            r5 = 3
            r6 = 2
            r7 = 4
            if (r2 != r0) goto L_0x0035
            java.lang.Object[] r0 = new java.lang.Object[r7]
            r0[r1] = r9
            r0[r4] = r11
            r0[r6] = r12
            r0[r5] = r8
            org.mozilla.javascript.MemberBox r11 = r8.member
            java.lang.Object r11 = r11.invoke(r3, r0)
        L_0x0032:
            r1 = 1
            goto L_0x00e9
        L_0x0035:
            if (r11 != 0) goto L_0x0039
            r11 = 1
            goto L_0x003a
        L_0x0039:
            r11 = 0
        L_0x003a:
            if (r11 == 0) goto L_0x003f
            java.lang.Boolean r11 = java.lang.Boolean.TRUE
            goto L_0x0041
        L_0x003f:
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
        L_0x0041:
            java.lang.Object[] r0 = new java.lang.Object[r7]
            r0[r1] = r9
            r0[r4] = r12
            r0[r6] = r8
            r0[r5] = r11
            org.mozilla.javascript.MemberBox r11 = r8.member
            boolean r11 = r11.isCtor()
            if (r11 == 0) goto L_0x005b
            org.mozilla.javascript.MemberBox r11 = r8.member
            java.lang.Object r11 = r11.newInstance(r0)
            goto L_0x00e9
        L_0x005b:
            org.mozilla.javascript.MemberBox r11 = r8.member
            java.lang.Object r11 = r11.invoke(r3, r0)
            goto L_0x00e9
        L_0x0063:
            boolean r2 = r8.isStatic
            if (r2 != 0) goto L_0x0090
            org.mozilla.javascript.MemberBox r2 = r8.member
            java.lang.Class r2 = r2.getDeclaringClass()
            boolean r5 = r2.isInstance(r11)
            if (r5 != 0) goto L_0x0090
            if (r11 != r10) goto L_0x0083
            org.mozilla.javascript.Scriptable r5 = r8.getParentScope()
            if (r10 == r5) goto L_0x0083
            boolean r2 = r2.isInstance(r5)
            if (r2 == 0) goto L_0x0084
            r11 = r5
            goto L_0x0084
        L_0x0083:
            r2 = 0
        L_0x0084:
            if (r2 == 0) goto L_0x0087
            goto L_0x0090
        L_0x0087:
            java.lang.String r9 = r8.functionName
            java.lang.String r10 = "msg.incompat.call"
            org.mozilla.javascript.EcmaError r9 = org.mozilla.javascript.ScriptRuntime.typeError1(r10, r9)
            throw r9
        L_0x0090:
            int r2 = r8.parmsLength
            if (r2 != r0) goto L_0x00b3
            r2 = r12
            r0 = 0
        L_0x0096:
            int r5 = r8.parmsLength
            if (r0 == r5) goto L_0x00d3
            r5 = r12[r0]
            byte[] r6 = r8.typeTags
            byte r6 = r6[r0]
            java.lang.Object r6 = convertArg((org.mozilla.javascript.Context) r9, (org.mozilla.javascript.Scriptable) r10, (java.lang.Object) r5, (int) r6)
            if (r5 == r6) goto L_0x00b0
            if (r2 != r12) goto L_0x00ae
            java.lang.Object r2 = r12.clone()
            java.lang.Object[] r2 = (java.lang.Object[]) r2
        L_0x00ae:
            r2[r0] = r6
        L_0x00b0:
            int r0 = r0 + 1
            goto L_0x0096
        L_0x00b3:
            if (r2 != 0) goto L_0x00b8
            java.lang.Object[] r2 = org.mozilla.javascript.ScriptRuntime.emptyArgs
            goto L_0x00d3
        L_0x00b8:
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r5 = 0
        L_0x00bb:
            int r6 = r8.parmsLength
            if (r5 == r6) goto L_0x00d3
            if (r5 >= r0) goto L_0x00c4
            r6 = r12[r5]
            goto L_0x00c6
        L_0x00c4:
            java.lang.Object r6 = org.mozilla.javascript.Undefined.instance
        L_0x00c6:
            byte[] r7 = r8.typeTags
            byte r7 = r7[r5]
            java.lang.Object r6 = convertArg((org.mozilla.javascript.Context) r9, (org.mozilla.javascript.Scriptable) r10, (java.lang.Object) r6, (int) r7)
            r2[r5] = r6
            int r5 = r5 + 1
            goto L_0x00bb
        L_0x00d3:
            org.mozilla.javascript.MemberBox r12 = r8.member
            boolean r12 = r12.isMethod()
            if (r12 == 0) goto L_0x00e3
            org.mozilla.javascript.MemberBox r12 = r8.member
            java.lang.Object r11 = r12.invoke(r11, r2)
            goto L_0x0032
        L_0x00e3:
            org.mozilla.javascript.MemberBox r11 = r8.member
            java.lang.Object r11 = r11.newInstance(r2)
        L_0x00e9:
            if (r1 == 0) goto L_0x00fe
            boolean r12 = r8.hasVoidReturn
            if (r12 == 0) goto L_0x00f2
            java.lang.Object r11 = org.mozilla.javascript.Undefined.instance
            goto L_0x00fe
        L_0x00f2:
            int r12 = r8.returnTypeTag
            if (r12 != 0) goto L_0x00fe
            org.mozilla.javascript.WrapFactory r12 = r9.getWrapFactory()
            java.lang.Object r11 = r12.wrap(r9, r10, r11, r3)
        L_0x00fe:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.FunctionObject.call(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    public Scriptable createObject(Context context, Scriptable scriptable) {
        if (this.member.isCtor() || this.parmsLength == -2) {
            return null;
        }
        try {
            Scriptable scriptable2 = (Scriptable) this.member.getDeclaringClass().newInstance();
            scriptable2.setPrototype(getClassPrototype());
            scriptable2.setParentScope(getParentScope());
            return scriptable2;
        } catch (Exception e) {
            throw Context.throwAsScriptRuntimeEx(e);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isVarArgsMethod() {
        return this.parmsLength == -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isVarArgsConstructor() {
        return this.parmsLength == -2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        if (this.parmsLength > 0) {
            Class<?>[] clsArr = this.member.argTypes;
            this.typeTags = new byte[this.parmsLength];
            for (int i = 0; i != this.parmsLength; i++) {
                this.typeTags[i] = (byte) getTypeTag(clsArr[i]);
            }
        }
        if (this.member.isMethod()) {
            Class<?> returnType = this.member.method().getReturnType();
            if (returnType == Void.TYPE) {
                this.hasVoidReturn = true;
            } else {
                this.returnTypeTag = getTypeTag(returnType);
            }
        }
    }
}

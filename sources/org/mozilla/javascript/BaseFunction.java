package org.mozilla.javascript;

import androidx.core.app.NotificationCompat;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.android.gms.ads.AdError;

public class BaseFunction extends IdScriptableObject implements Function {
    private static final Object FUNCTION_TAG = "Function";
    private static final int Id_apply = 4;
    private static final int Id_arguments = 5;
    private static final int Id_arity = 2;
    private static final int Id_bind = 6;
    private static final int Id_call = 5;
    private static final int Id_constructor = 1;
    private static final int Id_length = 1;
    private static final int Id_name = 3;
    private static final int Id_prototype = 4;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int MAX_INSTANCE_ID = 5;
    private static final int MAX_PROTOTYPE_ID = 6;
    static final long serialVersionUID = 5311394446546053859L;
    private Object prototypeProperty;
    private int prototypePropertyAttributes = 6;

    public int getArity() {
        return 0;
    }

    public String getClassName() {
        return "Function";
    }

    public String getFunctionName() {
        return "";
    }

    public int getLength() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return 5;
    }

    static void init(Scriptable scriptable, boolean z) {
        BaseFunction baseFunction = new BaseFunction();
        baseFunction.prototypePropertyAttributes = 7;
        baseFunction.exportAsJSClass(6, scriptable, z);
    }

    public BaseFunction() {
    }

    public BaseFunction(Scriptable scriptable, Scriptable scriptable2) {
        super(scriptable, scriptable2);
    }

    public String getTypeOf() {
        return avoidObjectDetection() ? AdError.UNDEFINED_DOMAIN : "function";
    }

    public boolean hasInstance(Scriptable scriptable) {
        Object property = ScriptableObject.getProperty((Scriptable) this, "prototype");
        if (property instanceof Scriptable) {
            return ScriptRuntime.jsDelegatesTo(scriptable, (Scriptable) property);
        }
        throw ScriptRuntime.typeError1("msg.instanceof.bad.prototype", getFunctionName());
    }

    /* access modifiers changed from: protected */
    public int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int length = str.length();
        int i2 = 6;
        if (length == 4) {
            str2 = "name";
            i = 3;
        } else if (length == 5) {
            str2 = "arity";
            i = 2;
        } else if (length != 6) {
            if (length == 9) {
                char charAt = str.charAt(0);
                if (charAt == 'a') {
                    str2 = "arguments";
                    i = 5;
                } else if (charAt == 'p') {
                    str2 = "prototype";
                    i = 4;
                }
            }
            str2 = null;
            i = 0;
        } else {
            str2 = Name.LENGTH;
            i = 1;
        }
        if (!(str2 == null || str2 == str || str2.equals(str))) {
            i = 0;
        }
        if (i == 0) {
            return super.findInstanceIdInfo(str);
        }
        if (i == 1 || i == 2 || i == 3) {
            i2 = 7;
        } else if (i != 4) {
            if (i != 5) {
                throw new IllegalStateException();
            }
        } else if (!hasPrototypeProperty()) {
            return 0;
        } else {
            i2 = this.prototypePropertyAttributes;
        }
        return instanceIdInfo(i2, i);
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        if (i == 1) {
            return Name.LENGTH;
        }
        if (i == 2) {
            return "arity";
        }
        if (i == 3) {
            return "name";
        }
        if (i != 4) {
            return i != 5 ? super.getInstanceIdName(i) : "arguments";
        }
        return "prototype";
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapInt(getLength());
        }
        if (i == 2) {
            return ScriptRuntime.wrapInt(getArity());
        }
        if (i == 3) {
            return getFunctionName();
        }
        if (i == 4) {
            return getPrototypeProperty();
        }
        if (i != 5) {
            return super.getInstanceIdValue(i);
        }
        return getArguments();
    }

    /* access modifiers changed from: protected */
    public void setInstanceIdValue(int i, Object obj) {
        if (i != 1 && i != 2 && i != 3) {
            if (i != 4) {
                if (i != 5) {
                    super.setInstanceIdValue(i, obj);
                    return;
                }
                if (obj == NOT_FOUND) {
                    Kit.codeBug();
                }
                defaultPut("arguments", obj);
            } else if ((this.prototypePropertyAttributes & 1) == 0) {
                if (obj == null) {
                    obj = UniqueTag.NULL_VALUE;
                }
                this.prototypeProperty = obj;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        idFunctionObject.setPrototype(this);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "toString";
                break;
            case 3:
                str = "toSource";
                break;
            case 4:
                i2 = 2;
                str = "apply";
                break;
            case 5:
                str = NotificationCompat.CATEGORY_CALL;
                break;
            case 6:
                str = "bind";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(FUNCTION_TAG, i, str, i2);
    }

    static boolean isApply(IdFunctionObject idFunctionObject) {
        return idFunctionObject.hasTag(FUNCTION_TAG) && idFunctionObject.methodId() == 4;
    }

    static boolean isApplyOrCall(IdFunctionObject idFunctionObject) {
        if (!idFunctionObject.hasTag(FUNCTION_TAG)) {
            return false;
        }
        int methodId = idFunctionObject.methodId();
        return methodId == 4 || methodId == 5;
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        int int32;
        Object[] objArr2;
        Scriptable scriptable3;
        if (!idFunctionObject.hasTag(FUNCTION_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        boolean z = true;
        int i = 0;
        switch (methodId) {
            case 1:
                return jsConstructor(context, scriptable, objArr);
            case 2:
                return realFunction(scriptable2, idFunctionObject).decompile(ScriptRuntime.toInt32(objArr, 0), 0);
            case 3:
                BaseFunction realFunction = realFunction(scriptable2, idFunctionObject);
                int i2 = 2;
                if (objArr.length != 0 && (int32 = ScriptRuntime.toInt32(objArr[0])) >= 0) {
                    i = int32;
                    i2 = 0;
                }
                return realFunction.decompile(i, i2);
            case 4:
            case 5:
                if (methodId != 4) {
                    z = false;
                }
                return ScriptRuntime.applyOrCall(z, context, scriptable, scriptable2, objArr);
            case 6:
                if (scriptable2 instanceof Callable) {
                    Callable callable = (Callable) scriptable2;
                    int length = objArr.length;
                    if (length > 0) {
                        Scriptable objectOrNull = ScriptRuntime.toObjectOrNull(context, objArr[0], scriptable);
                        int i3 = length - 1;
                        Object[] objArr3 = new Object[i3];
                        System.arraycopy(objArr, 1, objArr3, 0, i3);
                        scriptable3 = objectOrNull;
                        objArr2 = objArr3;
                    } else {
                        scriptable3 = null;
                        objArr2 = ScriptRuntime.emptyArgs;
                    }
                    return new BoundFunction(context, scriptable, callable, scriptable3, objArr2);
                }
                throw ScriptRuntime.notFunctionError(scriptable2);
            default:
                throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private BaseFunction realFunction(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        Object defaultValue = scriptable.getDefaultValue(ScriptRuntime.FunctionClass);
        if (defaultValue instanceof BaseFunction) {
            return (BaseFunction) defaultValue;
        }
        throw ScriptRuntime.typeError1("msg.incompat.call", idFunctionObject.getFunctionName());
    }

    public void setImmunePrototypeProperty(Object obj) {
        if ((this.prototypePropertyAttributes & 1) == 0) {
            if (obj == null) {
                obj = UniqueTag.NULL_VALUE;
            }
            this.prototypeProperty = obj;
            this.prototypePropertyAttributes = 7;
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: protected */
    public Scriptable getClassPrototype() {
        Object prototypeProperty2 = getPrototypeProperty();
        if (prototypeProperty2 instanceof Scriptable) {
            return (Scriptable) prototypeProperty2;
        }
        return ScriptableObject.getObjectPrototype(this);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return Undefined.instance;
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        Scriptable parentScope;
        Scriptable classPrototype;
        Scriptable createObject = createObject(context, scriptable);
        if (createObject != null) {
            Object call = call(context, scriptable, createObject, objArr);
            if (call instanceof Scriptable) {
                return (Scriptable) call;
            }
            return createObject;
        }
        Object call2 = call(context, scriptable, (Scriptable) null, objArr);
        if (call2 instanceof Scriptable) {
            Scriptable scriptable2 = (Scriptable) call2;
            if (scriptable2.getPrototype() == null && scriptable2 != (classPrototype = getClassPrototype())) {
                scriptable2.setPrototype(classPrototype);
            }
            if (scriptable2.getParentScope() != null || scriptable2 == (parentScope = getParentScope())) {
                return scriptable2;
            }
            scriptable2.setParentScope(parentScope);
            return scriptable2;
        }
        throw new IllegalStateException("Bad implementaion of call as constructor, name=" + getFunctionName() + " in " + getClass().getName());
    }

    public Scriptable createObject(Context context, Scriptable scriptable) {
        NativeObject nativeObject = new NativeObject();
        nativeObject.setPrototype(getClassPrototype());
        nativeObject.setParentScope(getParentScope());
        return nativeObject;
    }

    /* access modifiers changed from: package-private */
    public String decompile(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        if ((i2 & 1) == 0) {
            z = false;
        }
        if (!z) {
            stringBuffer.append("function ");
            stringBuffer.append(getFunctionName());
            stringBuffer.append("() {\n\t");
        }
        stringBuffer.append("[native code, arity=");
        stringBuffer.append(getArity());
        stringBuffer.append("]\n");
        if (!z) {
            stringBuffer.append("}\n");
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public boolean hasPrototypeProperty() {
        return this.prototypeProperty != null || (this instanceof NativeFunction);
    }

    /* access modifiers changed from: protected */
    public Object getPrototypeProperty() {
        Object obj = this.prototypeProperty;
        if (obj == null) {
            if (this instanceof NativeFunction) {
                return setupDefaultPrototype();
            }
            return Undefined.instance;
        } else if (obj == UniqueTag.NULL_VALUE) {
            return null;
        } else {
            return obj;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.lang.Object setupDefaultPrototype() {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.Object r0 = r3.prototypeProperty     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0009
            java.lang.Object r0 = r3.prototypeProperty     // Catch:{ all -> 0x0021 }
            monitor-exit(r3)
            return r0
        L_0x0009:
            org.mozilla.javascript.NativeObject r0 = new org.mozilla.javascript.NativeObject     // Catch:{ all -> 0x0021 }
            r0.<init>()     // Catch:{ all -> 0x0021 }
            java.lang.String r1 = "constructor"
            r2 = 2
            r0.defineProperty((java.lang.String) r1, (java.lang.Object) r3, (int) r2)     // Catch:{ all -> 0x0021 }
            r3.prototypeProperty = r0     // Catch:{ all -> 0x0021 }
            org.mozilla.javascript.Scriptable r1 = getObjectPrototype(r3)     // Catch:{ all -> 0x0021 }
            if (r1 == r0) goto L_0x001f
            r0.setPrototype(r1)     // Catch:{ all -> 0x0021 }
        L_0x001f:
            monitor-exit(r3)
            return r0
        L_0x0021:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.BaseFunction.setupDefaultPrototype():java.lang.Object");
    }

    private Object getArguments() {
        Object defaultGet = defaultGet("arguments");
        if (defaultGet != NOT_FOUND) {
            return defaultGet;
        }
        NativeCall findFunctionActivation = ScriptRuntime.findFunctionActivation(Context.getContext(), this);
        if (findFunctionActivation == null) {
            return null;
        }
        return findFunctionActivation.get("arguments", findFunctionActivation);
    }

    private static Object jsConstructor(Context context, Scriptable scriptable, Object[] objArr) {
        int i;
        int length = objArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("function ");
        if (context.getLanguageVersion() != 120) {
            stringBuffer.append("anonymous");
        }
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        int i2 = 0;
        while (true) {
            i = length - 1;
            if (i2 >= i) {
                break;
            }
            if (i2 > 0) {
                stringBuffer.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            }
            stringBuffer.append(ScriptRuntime.toString(objArr[i2]));
            i2++;
        }
        stringBuffer.append(") {");
        if (length != 0) {
            stringBuffer.append(ScriptRuntime.toString(objArr[i]));
        }
        stringBuffer.append("\n}");
        String stringBuffer2 = stringBuffer.toString();
        int[] iArr = new int[1];
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        if (sourcePositionFromStack == null) {
            iArr[0] = 1;
            sourcePositionFromStack = "<eval'ed string>";
        }
        String makeUrlForGeneratedScript = ScriptRuntime.makeUrlForGeneratedScript(false, sourcePositionFromStack, iArr[0]);
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        ErrorReporter forEval = DefaultErrorReporter.forEval(context.getErrorReporter());
        Evaluator createInterpreter = Context.createInterpreter();
        if (createInterpreter != null) {
            return context.compileFunction(topLevelScope, stringBuffer2, createInterpreter, forEval, makeUrlForGeneratedScript, 1, (Object) null);
        }
        throw new JavaScriptException("Interpreter not present", sourcePositionFromStack, iArr[0]);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r6) {
        /*
            r5 = this;
            int r0 = r6.length()
            r1 = 3
            r2 = 5
            r3 = 4
            r4 = 0
            if (r0 == r3) goto L_0x0030
            if (r0 == r2) goto L_0x002c
            r2 = 8
            if (r0 == r2) goto L_0x0019
            r1 = 11
            if (r0 == r1) goto L_0x0015
            goto L_0x0044
        L_0x0015:
            r1 = 1
            java.lang.String r0 = "constructor"
            goto L_0x0046
        L_0x0019:
            char r0 = r6.charAt(r1)
            r2 = 111(0x6f, float:1.56E-43)
            if (r0 != r2) goto L_0x0024
            java.lang.String r0 = "toSource"
            goto L_0x0046
        L_0x0024:
            r1 = 116(0x74, float:1.63E-43)
            if (r0 != r1) goto L_0x0044
            r1 = 2
            java.lang.String r0 = "toString"
            goto L_0x0046
        L_0x002c:
            java.lang.String r0 = "apply"
            r1 = 4
            goto L_0x0046
        L_0x0030:
            char r0 = r6.charAt(r4)
            r1 = 98
            if (r0 != r1) goto L_0x003c
            r1 = 6
            java.lang.String r0 = "bind"
            goto L_0x0046
        L_0x003c:
            r1 = 99
            if (r0 != r1) goto L_0x0044
            java.lang.String r0 = "call"
            r1 = 5
            goto L_0x0046
        L_0x0044:
            r0 = 0
            r1 = 0
        L_0x0046:
            if (r0 == 0) goto L_0x0051
            if (r0 == r6) goto L_0x0051
            boolean r6 = r0.equals(r6)
            if (r6 != 0) goto L_0x0051
            goto L_0x0052
        L_0x0051:
            r4 = r1
        L_0x0052:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.BaseFunction.findPrototypeId(java.lang.String):int");
    }
}

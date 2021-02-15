package org.mozilla.javascript;

class NativeScript extends BaseFunction {
    private static final int Id_compile = 3;
    private static final int Id_constructor = 1;
    private static final int Id_exec = 4;
    private static final int Id_toString = 2;
    private static final int MAX_PROTOTYPE_ID = 4;
    private static final Object SCRIPT_TAG = "Script";
    static final long serialVersionUID = -6795101161980121700L;
    private Script script;

    public int getArity() {
        return 0;
    }

    public String getClassName() {
        return "Script";
    }

    public int getLength() {
        return 0;
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeScript((Script) null).exportAsJSClass(4, scriptable, z);
    }

    private NativeScript(Script script2) {
        this.script = script2;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Script script2 = this.script;
        if (script2 != null) {
            return script2.exec(context, scriptable);
        }
        return Undefined.instance;
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw Context.reportRuntimeError0("msg.script.is.not.constructor");
    }

    /* access modifiers changed from: package-private */
    public String decompile(int i, int i2) {
        Script script2 = this.script;
        if (script2 instanceof NativeFunction) {
            return ((NativeFunction) script2).decompile(i, i2);
        }
        return super.decompile(i, i2);
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 0;
        if (i != 1) {
            if (i == 2) {
                str = "toString";
            } else if (i == 3) {
                str2 = "compile";
            } else if (i == 4) {
                str = "exec";
            } else {
                throw new IllegalArgumentException(String.valueOf(i));
            }
            initPrototypeMethod(SCRIPT_TAG, i, str, i2);
        }
        str2 = "constructor";
        str = str2;
        i2 = 1;
        initPrototypeMethod(SCRIPT_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(SCRIPT_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        String str = "";
        if (methodId == 1) {
            if (objArr.length != 0) {
                str = ScriptRuntime.toString(objArr[0]);
            }
            NativeScript nativeScript = new NativeScript(compile(context, str));
            ScriptRuntime.setObjectProtoAndParent(nativeScript, scriptable);
            return nativeScript;
        } else if (methodId == 2) {
            Script script2 = realThis(scriptable2, idFunctionObject).script;
            if (script2 == null) {
                return str;
            }
            return context.decompileScript(script2, 0);
        } else if (methodId == 3) {
            NativeScript realThis = realThis(scriptable2, idFunctionObject);
            realThis.script = compile(context, ScriptRuntime.toString(objArr, 0));
            return realThis;
        } else if (methodId != 4) {
            throw new IllegalArgumentException(String.valueOf(methodId));
        } else {
            throw Context.reportRuntimeError1("msg.cant.call.indirect", "exec");
        }
    }

    private static NativeScript realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeScript) {
            return (NativeScript) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    private static Script compile(Context context, String str) {
        int[] iArr = {0};
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        if (sourcePositionFromStack == null) {
            iArr[0] = 1;
            sourcePositionFromStack = "<Script object>";
        }
        return context.compileString(str, (Evaluator) null, DefaultErrorReporter.forEval(context.getErrorReporter()), sourcePositionFromStack, iArr[0], (Object) null);
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 4;
        if (length == 4) {
            str2 = "exec";
        } else if (length == 11) {
            i = 1;
            str2 = "constructor";
        } else if (length == 7) {
            i = 3;
            str2 = "compile";
        } else if (length != 8) {
            str2 = null;
            i = 0;
        } else {
            i = 2;
            str2 = "toString";
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}

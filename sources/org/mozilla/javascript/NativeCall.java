package org.mozilla.javascript;

public final class NativeCall extends IdScriptableObject {
    private static final Object CALL_TAG = "Call";
    private static final int Id_constructor = 1;
    private static final int MAX_PROTOTYPE_ID = 1;
    static final long serialVersionUID = -7471457301304454454L;
    NativeFunction function;
    Object[] originalArgs;
    transient NativeCall parentActivationCall;

    public String getClassName() {
        return "Call";
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeCall().exportAsJSClass(1, scriptable, z);
    }

    NativeCall() {
    }

    NativeCall(NativeFunction nativeFunction, Scriptable scriptable, Object[] objArr) {
        this.function = nativeFunction;
        setParentScope(scriptable);
        this.originalArgs = objArr == null ? ScriptRuntime.emptyArgs : objArr;
        int paramAndVarCount = nativeFunction.getParamAndVarCount();
        int paramCount = nativeFunction.getParamCount();
        if (paramAndVarCount != 0) {
            int i = 0;
            while (i < paramCount) {
                defineProperty(nativeFunction.getParamOrVarName(i), i < objArr.length ? objArr[i] : Undefined.instance, 4);
                i++;
            }
        }
        if (!super.has("arguments", this)) {
            defineProperty("arguments", (Object) new Arguments(this), 4);
        }
        if (paramAndVarCount != 0) {
            while (paramCount < paramAndVarCount) {
                String paramOrVarName = nativeFunction.getParamOrVarName(paramCount);
                if (!super.has(paramOrVarName, this)) {
                    if (nativeFunction.getParamOrVarConst(paramCount)) {
                        defineProperty(paramOrVarName, Undefined.instance, 13);
                    } else {
                        defineProperty(paramOrVarName, Undefined.instance, 4);
                    }
                }
                paramCount++;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        return str.equals("constructor") ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        if (i == 1) {
            initPrototypeMethod(CALL_TAG, i, "constructor", 1);
            return;
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(CALL_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId != 1) {
            throw new IllegalArgumentException(String.valueOf(methodId));
        } else if (scriptable2 == null) {
            ScriptRuntime.checkDeprecated(context, "Call");
            NativeCall nativeCall = new NativeCall();
            nativeCall.setPrototype(getObjectPrototype(scriptable));
            return nativeCall;
        } else {
            throw Context.reportRuntimeError1("msg.only.from.new", "Call");
        }
    }
}

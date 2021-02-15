package org.mozilla.javascript;

public final class NativeContinuation extends IdScriptableObject implements Function {
    private static final Object FTAG = "Continuation";
    private static final int Id_constructor = 1;
    private static final int MAX_PROTOTYPE_ID = 1;
    static final long serialVersionUID = 1794167133757605367L;
    private Object implementation;

    public String getClassName() {
        return "Continuation";
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        new NativeContinuation().exportAsJSClass(1, scriptable, z);
    }

    public Object getImplementation() {
        return this.implementation;
    }

    public void initImplementation(Object obj) {
        this.implementation = obj;
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw Context.reportRuntimeError("Direct call is not supported");
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return Interpreter.restartContinuation(this, context, scriptable, objArr);
    }

    public static boolean isContinuationConstructor(IdFunctionObject idFunctionObject) {
        return idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 1;
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        if (i == 1) {
            initPrototypeMethod(FTAG, i, "constructor", 0);
            return;
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(FTAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId != 1) {
            throw new IllegalArgumentException(String.valueOf(methodId));
        }
        throw Context.reportRuntimeError("Direct call is not supported");
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        String str2;
        int i;
        if (str.length() == 11) {
            i = 1;
            str2 = "constructor";
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}

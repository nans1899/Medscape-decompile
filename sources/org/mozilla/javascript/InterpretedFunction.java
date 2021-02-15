package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

final class InterpretedFunction extends NativeFunction implements Script {
    static final long serialVersionUID = 541475680333911468L;
    InterpreterData idata;
    SecurityController securityController;
    Object securityDomain;

    private InterpretedFunction(InterpreterData interpreterData, Object obj) {
        Object obj2;
        this.idata = interpreterData;
        SecurityController securityController2 = Context.getContext().getSecurityController();
        if (securityController2 != null) {
            obj2 = securityController2.getDynamicSecurityDomain(obj);
        } else if (obj == null) {
            obj2 = null;
        } else {
            throw new IllegalArgumentException();
        }
        this.securityController = securityController2;
        this.securityDomain = obj2;
    }

    private InterpretedFunction(InterpretedFunction interpretedFunction, int i) {
        this.idata = interpretedFunction.idata.itsNestedFunctions[i];
        this.securityController = interpretedFunction.securityController;
        this.securityDomain = interpretedFunction.securityDomain;
    }

    static InterpretedFunction createScript(InterpreterData interpreterData, Object obj) {
        return new InterpretedFunction(interpreterData, obj);
    }

    static InterpretedFunction createFunction(Context context, Scriptable scriptable, InterpreterData interpreterData, Object obj) {
        InterpretedFunction interpretedFunction = new InterpretedFunction(interpreterData, obj);
        interpretedFunction.initScriptFunction(context, scriptable);
        return interpretedFunction;
    }

    static InterpretedFunction createFunction(Context context, Scriptable scriptable, InterpretedFunction interpretedFunction, int i) {
        InterpretedFunction interpretedFunction2 = new InterpretedFunction(interpretedFunction, i);
        interpretedFunction2.initScriptFunction(context, scriptable);
        return interpretedFunction2;
    }

    public String getFunctionName() {
        return this.idata.itsName == null ? "" : this.idata.itsName;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!ScriptRuntime.hasTopCall(context)) {
            return ScriptRuntime.doTopCall(this, context, scriptable, scriptable2, objArr);
        }
        return Interpreter.interpret(this, context, scriptable, scriptable2, objArr);
    }

    public Object exec(Context context, Scriptable scriptable) {
        if (!isScript()) {
            throw new IllegalStateException();
        } else if (!ScriptRuntime.hasTopCall(context)) {
            return ScriptRuntime.doTopCall(this, context, scriptable, scriptable, ScriptRuntime.emptyArgs);
        } else {
            return Interpreter.interpret(this, context, scriptable, scriptable, ScriptRuntime.emptyArgs);
        }
    }

    public boolean isScript() {
        return this.idata.itsFunctionType == 0;
    }

    public String getEncodedSource() {
        return Interpreter.getEncodedSource(this.idata);
    }

    public DebuggableScript getDebuggableView() {
        return this.idata;
    }

    public Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        return Interpreter.resumeGenerator(context, scriptable, i, obj, obj2);
    }

    /* access modifiers changed from: protected */
    public int getLanguageVersion() {
        return this.idata.languageVersion;
    }

    /* access modifiers changed from: protected */
    public int getParamCount() {
        return this.idata.argCount;
    }

    /* access modifiers changed from: protected */
    public int getParamAndVarCount() {
        return this.idata.argNames.length;
    }

    /* access modifiers changed from: protected */
    public String getParamOrVarName(int i) {
        return this.idata.argNames[i];
    }

    /* access modifiers changed from: protected */
    public boolean getParamOrVarConst(int i) {
        return this.idata.argIsConst[i];
    }
}

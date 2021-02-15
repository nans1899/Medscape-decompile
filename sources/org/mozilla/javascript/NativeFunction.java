package org.mozilla.javascript;

import org.mozilla.javascript.debug.DebuggableScript;

public abstract class NativeFunction extends BaseFunction {
    static final long serialVersionUID = 8713897114082216401L;

    public DebuggableScript getDebuggableView() {
        return null;
    }

    public String getEncodedSource() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract int getLanguageVersion();

    /* access modifiers changed from: protected */
    public abstract int getParamAndVarCount();

    /* access modifiers changed from: protected */
    public abstract int getParamCount();

    /* access modifiers changed from: protected */
    public boolean getParamOrVarConst(int i) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract String getParamOrVarName(int i);

    public final void initScriptFunction(Context context, Scriptable scriptable) {
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
    }

    /* access modifiers changed from: package-private */
    public final String decompile(int i, int i2) {
        String encodedSource = getEncodedSource();
        if (encodedSource == null) {
            return super.decompile(i, i2);
        }
        UintMap uintMap = new UintMap(1);
        uintMap.put(1, i);
        return Decompiler.decompile(encodedSource, i2, uintMap);
    }

    public int getLength() {
        NativeCall findFunctionActivation;
        int paramCount = getParamCount();
        if (getLanguageVersion() == 120 && (findFunctionActivation = ScriptRuntime.findFunctionActivation(Context.getContext(), this)) != null) {
            return findFunctionActivation.originalArgs.length;
        }
        return paramCount;
    }

    public int getArity() {
        return getParamCount();
    }

    public String jsGet_name() {
        return getFunctionName();
    }

    public Object resumeGenerator(Context context, Scriptable scriptable, int i, Object obj, Object obj2) {
        throw new EvaluatorException("resumeGenerator() not implemented");
    }
}

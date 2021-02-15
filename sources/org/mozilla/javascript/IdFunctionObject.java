package org.mozilla.javascript;

public class IdFunctionObject extends BaseFunction {
    static final long serialVersionUID = -5332312783643935019L;
    private int arity;
    private String functionName;
    private final IdFunctionCall idcall;
    private final int methodId;
    private final Object tag;
    private boolean useCallAsConstructor;

    public IdFunctionObject(IdFunctionCall idFunctionCall, Object obj, int i, int i2) {
        if (i2 >= 0) {
            this.idcall = idFunctionCall;
            this.tag = obj;
            this.methodId = i;
            this.arity = i2;
            if (i2 < 0) {
                throw new IllegalArgumentException();
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    public IdFunctionObject(IdFunctionCall idFunctionCall, Object obj, int i, String str, int i2, Scriptable scriptable) {
        super(scriptable, (Scriptable) null);
        if (i2 < 0) {
            throw new IllegalArgumentException();
        } else if (str != null) {
            this.idcall = idFunctionCall;
            this.tag = obj;
            this.methodId = i;
            this.arity = i2;
            this.functionName = str;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void initFunction(String str, Scriptable scriptable) {
        if (str == null) {
            throw new IllegalArgumentException();
        } else if (scriptable != null) {
            this.functionName = str;
            setParentScope(scriptable);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final boolean hasTag(Object obj) {
        if (obj == null) {
            return this.tag == null;
        }
        return obj.equals(this.tag);
    }

    public final int methodId() {
        return this.methodId;
    }

    public final void markAsConstructor(Scriptable scriptable) {
        this.useCallAsConstructor = true;
        setImmunePrototypeProperty(scriptable);
    }

    public final void addAsProperty(Scriptable scriptable) {
        ScriptableObject.defineProperty(scriptable, this.functionName, this, 2);
    }

    public void exportAsScopeProperty() {
        addAsProperty(getParentScope());
    }

    public Scriptable getPrototype() {
        Scriptable prototype = super.getPrototype();
        if (prototype != null) {
            return prototype;
        }
        Scriptable functionPrototype = getFunctionPrototype(getParentScope());
        setPrototype(functionPrototype);
        return functionPrototype;
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        return this.idcall.execIdCall(this, context, scriptable, scriptable2, objArr);
    }

    public Scriptable createObject(Context context, Scriptable scriptable) {
        if (this.useCallAsConstructor) {
            return null;
        }
        throw ScriptRuntime.typeError1("msg.not.ctor", this.functionName);
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
            stringBuffer.append("() { ");
        }
        stringBuffer.append("[native code for ");
        IdFunctionCall idFunctionCall = this.idcall;
        if (idFunctionCall instanceof Scriptable) {
            stringBuffer.append(((Scriptable) idFunctionCall).getClassName());
            stringBuffer.append('.');
        }
        stringBuffer.append(getFunctionName());
        stringBuffer.append(", arity=");
        stringBuffer.append(getArity());
        stringBuffer.append(z ? "]\n" : "] }\n");
        return stringBuffer.toString();
    }

    public int getArity() {
        return this.arity;
    }

    public int getLength() {
        return getArity();
    }

    public String getFunctionName() {
        String str = this.functionName;
        return str == null ? "" : str;
    }

    public final RuntimeException unknown() {
        return new IllegalArgumentException("BAD FUNCTION ID=" + this.methodId + " MASTER=" + this.idcall);
    }
}

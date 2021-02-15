package org.mozilla.javascript;

public class BoundFunction extends BaseFunction {
    static final long serialVersionUID = 2118137342826470729L;
    private final Object[] boundArgs;
    private final Scriptable boundThis;
    private final int length;
    private final Callable targetFunction;

    public BoundFunction(Context context, Scriptable scriptable, Callable callable, Scriptable scriptable2, Object[] objArr) {
        this.targetFunction = callable;
        this.boundThis = scriptable2;
        this.boundArgs = objArr;
        if (callable instanceof BaseFunction) {
            this.length = Math.max(0, ((BaseFunction) callable).getLength() - objArr.length);
        } else {
            this.length = 0;
        }
        ScriptRuntime.setFunctionProtoAndParent(this, scriptable);
        BaseFunction typeErrorThrower = ScriptRuntime.typeErrorThrower();
        NativeObject nativeObject = new NativeObject();
        nativeObject.put("get", nativeObject, typeErrorThrower);
        nativeObject.put("set", nativeObject, typeErrorThrower);
        nativeObject.put("enumerable", nativeObject, false);
        nativeObject.put("configurable", nativeObject, false);
        nativeObject.preventExtensions();
        defineOwnProperty(context, "caller", nativeObject, false);
        defineOwnProperty(context, "arguments", nativeObject, false);
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Scriptable scriptable3 = this.boundThis;
        if (scriptable3 == null) {
            scriptable3 = ScriptRuntime.getTopCallScope(context);
        }
        return this.targetFunction.call(context, scriptable, scriptable3, concat(this.boundArgs, objArr));
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        Callable callable = this.targetFunction;
        if (callable instanceof Function) {
            return ((Function) callable).construct(context, scriptable, concat(this.boundArgs, objArr));
        }
        throw ScriptRuntime.typeError0("msg.not.ctor");
    }

    public boolean hasInstance(Scriptable scriptable) {
        Callable callable = this.targetFunction;
        if (callable instanceof Function) {
            return ((Function) callable).hasInstance(scriptable);
        }
        throw ScriptRuntime.typeError0("msg.not.ctor");
    }

    public int getLength() {
        return this.length;
    }

    private Object[] concat(Object[] objArr, Object[] objArr2) {
        Object[] objArr3 = new Object[(objArr.length + objArr2.length)];
        System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
        System.arraycopy(objArr2, 0, objArr3, objArr.length, objArr2.length);
        return objArr3;
    }
}

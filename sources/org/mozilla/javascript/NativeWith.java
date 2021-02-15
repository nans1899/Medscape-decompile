package org.mozilla.javascript;

import java.io.Serializable;

public class NativeWith implements Scriptable, IdFunctionCall, Serializable {
    private static final Object FTAG = "With";
    private static final int Id_constructor = 1;
    private static final long serialVersionUID = 1;
    protected Scriptable parent;
    protected Scriptable prototype;

    public String getClassName() {
        return "With";
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeWith nativeWith = new NativeWith();
        nativeWith.setParentScope(scriptable);
        nativeWith.setPrototype(ScriptableObject.getObjectPrototype(scriptable));
        IdFunctionObject idFunctionObject = new IdFunctionObject(nativeWith, FTAG, 1, "With", 0, scriptable);
        idFunctionObject.markAsConstructor(nativeWith);
        if (z) {
            idFunctionObject.sealObject();
        }
        idFunctionObject.exportAsScopeProperty();
    }

    private NativeWith() {
    }

    protected NativeWith(Scriptable scriptable, Scriptable scriptable2) {
        this.parent = scriptable;
        this.prototype = scriptable2;
    }

    public boolean has(String str, Scriptable scriptable) {
        Scriptable scriptable2 = this.prototype;
        return scriptable2.has(str, scriptable2);
    }

    public boolean has(int i, Scriptable scriptable) {
        Scriptable scriptable2 = this.prototype;
        return scriptable2.has(i, scriptable2);
    }

    public Object get(String str, Scriptable scriptable) {
        if (scriptable == this) {
            scriptable = this.prototype;
        }
        return this.prototype.get(str, scriptable);
    }

    public Object get(int i, Scriptable scriptable) {
        if (scriptable == this) {
            scriptable = this.prototype;
        }
        return this.prototype.get(i, scriptable);
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        if (scriptable == this) {
            scriptable = this.prototype;
        }
        this.prototype.put(str, scriptable, obj);
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (scriptable == this) {
            scriptable = this.prototype;
        }
        this.prototype.put(i, scriptable, obj);
    }

    public void delete(String str) {
        this.prototype.delete(str);
    }

    public void delete(int i) {
        this.prototype.delete(i);
    }

    public Scriptable getPrototype() {
        return this.prototype;
    }

    public void setPrototype(Scriptable scriptable) {
        this.prototype = scriptable;
    }

    public Scriptable getParentScope() {
        return this.parent;
    }

    public void setParentScope(Scriptable scriptable) {
        this.parent = scriptable;
    }

    public Object[] getIds() {
        return this.prototype.getIds();
    }

    public Object getDefaultValue(Class<?> cls) {
        return this.prototype.getDefaultValue(cls);
    }

    public boolean hasInstance(Scriptable scriptable) {
        return this.prototype.hasInstance(scriptable);
    }

    /* access modifiers changed from: protected */
    public Object updateDotQuery(boolean z) {
        throw new IllegalStateException();
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(FTAG) || idFunctionObject.methodId() != 1) {
            throw idFunctionObject.unknown();
        }
        throw Context.reportRuntimeError1("msg.cant.call.indirect", "With");
    }

    static boolean isWithFunction(Object obj) {
        if (!(obj instanceof IdFunctionObject)) {
            return false;
        }
        IdFunctionObject idFunctionObject = (IdFunctionObject) obj;
        if (!idFunctionObject.hasTag(FTAG) || idFunctionObject.methodId() != 1) {
            return false;
        }
        return true;
    }

    static Object newWithSpecial(Context context, Scriptable scriptable, Object[] objArr) {
        ScriptRuntime.checkDeprecated(context, "With");
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        NativeWith nativeWith = new NativeWith();
        nativeWith.setPrototype(objArr.length == 0 ? ScriptableObject.getObjectPrototype(topLevelScope) : ScriptRuntime.toObject(context, topLevelScope, objArr[0]));
        nativeWith.setParentScope(topLevelScope);
        return nativeWith;
    }
}

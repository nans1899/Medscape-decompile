package org.mozilla.javascript;

import java.util.EnumMap;

public class TopLevel extends IdScriptableObject {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final long serialVersionUID = -4648046356662472260L;
    private EnumMap<Builtins, BaseFunction> ctors;

    public enum Builtins {
        Object,
        Array,
        Function,
        String,
        Number,
        Boolean,
        RegExp,
        Error
    }

    public String getClassName() {
        return "global";
    }

    public void cacheBuiltins() {
        this.ctors = new EnumMap<>(Builtins.class);
        for (Builtins builtins : Builtins.values()) {
            Object property = ScriptableObject.getProperty((Scriptable) this, builtins.name());
            if (property instanceof BaseFunction) {
                this.ctors.put(builtins, (BaseFunction) property);
            }
        }
    }

    public static Function getBuiltinCtor(Context context, Scriptable scriptable, Builtins builtins) {
        BaseFunction builtinCtor;
        if (!(scriptable instanceof TopLevel) || (builtinCtor = ((TopLevel) scriptable).getBuiltinCtor(builtins)) == null) {
            return ScriptRuntime.getExistingCtor(context, scriptable, builtins.name());
        }
        return builtinCtor;
    }

    public static Scriptable getBuiltinPrototype(Scriptable scriptable, Builtins builtins) {
        Scriptable builtinPrototype;
        if (!(scriptable instanceof TopLevel) || (builtinPrototype = ((TopLevel) scriptable).getBuiltinPrototype(builtins)) == null) {
            return ScriptableObject.getClassPrototype(scriptable, builtins.name());
        }
        return builtinPrototype;
    }

    public BaseFunction getBuiltinCtor(Builtins builtins) {
        EnumMap<Builtins, BaseFunction> enumMap = this.ctors;
        if (enumMap != null) {
            return enumMap.get(builtins);
        }
        return null;
    }

    public Scriptable getBuiltinPrototype(Builtins builtins) {
        BaseFunction builtinCtor = getBuiltinCtor(builtins);
        Object prototypeProperty = builtinCtor != null ? builtinCtor.getPrototypeProperty() : null;
        if (prototypeProperty instanceof Scriptable) {
            return (Scriptable) prototypeProperty;
        }
        return null;
    }
}

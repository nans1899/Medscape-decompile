package org.mozilla.javascript;

import java.lang.reflect.Array;

public class NativeJavaArray extends NativeJavaObject {
    static final long serialVersionUID = -924022554283675333L;
    Object array;
    Class<?> cls;
    int length;

    public String getClassName() {
        return "JavaArray";
    }

    public static NativeJavaArray wrap(Scriptable scriptable, Object obj) {
        return new NativeJavaArray(scriptable, obj);
    }

    public Object unwrap() {
        return this.array;
    }

    public NativeJavaArray(Scriptable scriptable, Object obj) {
        super(scriptable, (Object) null, ScriptRuntime.ObjectClass);
        Class<?> cls2 = obj.getClass();
        if (cls2.isArray()) {
            this.array = obj;
            this.length = Array.getLength(obj);
            this.cls = cls2.getComponentType();
            return;
        }
        throw new RuntimeException("Array expected");
    }

    public boolean has(String str, Scriptable scriptable) {
        return str.equals(Name.LENGTH) || super.has(str, scriptable);
    }

    public boolean has(int i, Scriptable scriptable) {
        return i >= 0 && i < this.length;
    }

    public Object get(String str, Scriptable scriptable) {
        if (str.equals(Name.LENGTH)) {
            return Integer.valueOf(this.length);
        }
        Object obj = super.get(str, scriptable);
        if (obj != NOT_FOUND || ScriptableObject.hasProperty(getPrototype(), str)) {
            return obj;
        }
        throw Context.reportRuntimeError2("msg.java.member.not.found", this.array.getClass().getName(), str);
    }

    public Object get(int i, Scriptable scriptable) {
        if (i < 0 || i >= this.length) {
            return Undefined.instance;
        }
        Context context = Context.getContext();
        return context.getWrapFactory().wrap(context, this, Array.get(this.array, i), this.cls);
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        if (!str.equals(Name.LENGTH)) {
            throw Context.reportRuntimeError1("msg.java.array.member.not.found", str);
        }
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (i < 0 || i >= this.length) {
            throw Context.reportRuntimeError2("msg.java.array.index.out.of.bounds", String.valueOf(i), String.valueOf(this.length - 1));
        }
        Array.set(this.array, i, Context.jsToJava(obj, this.cls));
    }

    public Object getDefaultValue(Class<?> cls2) {
        if (cls2 == null || cls2 == ScriptRuntime.StringClass) {
            return this.array.toString();
        }
        if (cls2 == ScriptRuntime.BooleanClass) {
            return Boolean.TRUE;
        }
        return cls2 == ScriptRuntime.NumberClass ? ScriptRuntime.NaNobj : this;
    }

    public Object[] getIds() {
        int i = this.length;
        Object[] objArr = new Object[i];
        while (true) {
            i--;
            if (i < 0) {
                return objArr;
            }
            objArr[i] = Integer.valueOf(i);
        }
    }

    public boolean hasInstance(Scriptable scriptable) {
        if (!(scriptable instanceof Wrapper)) {
            return false;
        }
        return this.cls.isInstance(((Wrapper) scriptable).unwrap());
    }

    public Scriptable getPrototype() {
        if (this.prototype == null) {
            this.prototype = ScriptableObject.getArrayPrototype(getParentScope());
        }
        return this.prototype;
    }
}

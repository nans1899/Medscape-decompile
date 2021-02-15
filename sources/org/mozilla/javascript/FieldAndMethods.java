package org.mozilla.javascript;

import java.lang.reflect.Field;

/* compiled from: JavaMembers */
class FieldAndMethods extends NativeJavaMethod {
    static final long serialVersionUID = -9222428244284796755L;
    Field field;
    Object javaObject;

    FieldAndMethods(Scriptable scriptable, MemberBox[] memberBoxArr, Field field2) {
        super(memberBoxArr);
        this.field = field2;
        setParentScope(scriptable);
        setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
    }

    public Object getDefaultValue(Class<?> cls) {
        if (cls == ScriptRuntime.FunctionClass) {
            return this;
        }
        try {
            Object obj = this.field.get(this.javaObject);
            Class<?> type = this.field.getType();
            Context context = Context.getContext();
            Object wrap = context.getWrapFactory().wrap(context, this, obj, type);
            return wrap instanceof Scriptable ? ((Scriptable) wrap).getDefaultValue(cls) : wrap;
        } catch (IllegalAccessException unused) {
            throw Context.reportRuntimeError1("msg.java.internal.private", this.field.getName());
        }
    }
}

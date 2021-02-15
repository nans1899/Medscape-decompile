package org.mozilla.javascript;

import com.facebook.internal.ServerProtocol;

final class NativeBoolean extends IdScriptableObject {
    private static final Object BOOLEAN_TAG = "Boolean";
    private static final int Id_constructor = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 4;
    private static final int MAX_PROTOTYPE_ID = 4;
    static final long serialVersionUID = -3716996899943880933L;
    private boolean booleanValue;

    public String getClassName() {
        return "Boolean";
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeBoolean(false).exportAsJSClass(4, scriptable, z);
    }

    NativeBoolean(boolean z) {
        this.booleanValue = z;
    }

    public Object getDefaultValue(Class<?> cls) {
        if (cls == ScriptRuntime.BooleanClass) {
            return ScriptRuntime.wrapBoolean(this.booleanValue);
        }
        return super.getDefaultValue(cls);
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        if (i == 1) {
            str = "constructor";
            i2 = 1;
        } else if (i == 2) {
            str = "toString";
        } else if (i == 3) {
            str = "toSource";
        } else if (i == 4) {
            str = "valueOf";
        } else {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(BOOLEAN_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(BOOLEAN_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        boolean z = true;
        if (methodId == 1) {
            boolean z2 = false;
            if (objArr.length != 0) {
                if (!(objArr[0] instanceof ScriptableObject) || !objArr[0].avoidObjectDetection()) {
                    z = ScriptRuntime.toBoolean(objArr[0]);
                }
                z2 = z;
            }
            if (scriptable2 == null) {
                return new NativeBoolean(z2);
            }
            return ScriptRuntime.wrapBoolean(z2);
        } else if (scriptable2 instanceof NativeBoolean) {
            boolean z3 = ((NativeBoolean) scriptable2).booleanValue;
            if (methodId == 2) {
                return z3 ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
            }
            if (methodId == 3) {
                return z3 ? "(new Boolean(true))" : "(new Boolean(false))";
            }
            if (methodId == 4) {
                return ScriptRuntime.wrapBoolean(z3);
            }
            throw new IllegalArgumentException(String.valueOf(methodId));
        } else {
            throw incompatibleCallError(idFunctionObject);
        }
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 3;
        if (length == 7) {
            i = 4;
            str2 = "valueOf";
        } else {
            if (length == 8) {
                char charAt = str.charAt(3);
                if (charAt == 'o') {
                    str2 = "toSource";
                } else if (charAt == 't') {
                    i = 2;
                    str2 = "toString";
                }
            } else if (length == 11) {
                i = 1;
                str2 = "constructor";
            }
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}

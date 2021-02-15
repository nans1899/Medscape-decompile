package org.mozilla.javascript;

import com.facebook.share.internal.ShareConstants;

final class NativeError extends IdScriptableObject {
    private static final Object ERROR_TAG = "Error";
    private static final int Id_constructor = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    static final long serialVersionUID = -5338413581437645187L;
    private RhinoException stackProvider;

    public String getClassName() {
        return "Error";
    }

    NativeError() {
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeError nativeError = new NativeError();
        ScriptableObject.putProperty((Scriptable) nativeError, "name", (Object) "Error");
        ScriptableObject.putProperty((Scriptable) nativeError, ShareConstants.WEB_DIALOG_PARAM_MESSAGE, (Object) "");
        ScriptableObject.putProperty((Scriptable) nativeError, "fileName", (Object) "");
        ScriptableObject.putProperty((Scriptable) nativeError, "lineNumber", (Object) 0);
        nativeError.exportAsJSClass(3, scriptable, z);
    }

    static NativeError make(Context context, Scriptable scriptable, IdFunctionObject idFunctionObject, Object[] objArr) {
        NativeError nativeError = new NativeError();
        nativeError.setPrototype((Scriptable) idFunctionObject.get("prototype", idFunctionObject));
        nativeError.setParentScope(scriptable);
        int length = objArr.length;
        if (length >= 1) {
            ScriptableObject.putProperty((Scriptable) nativeError, ShareConstants.WEB_DIALOG_PARAM_MESSAGE, (Object) ScriptRuntime.toString(objArr[0]));
            if (length >= 2) {
                ScriptableObject.putProperty((Scriptable) nativeError, "fileName", objArr[1]);
                if (length >= 3) {
                    ScriptableObject.putProperty((Scriptable) nativeError, "lineNumber", (Object) Integer.valueOf(ScriptRuntime.toInt32(objArr[2])));
                }
            }
        }
        return nativeError;
    }

    public String toString() {
        Object js_toString = js_toString(this);
        return js_toString instanceof String ? (String) js_toString : super.toString();
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
        } else {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(ERROR_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(ERROR_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            return make(context, scriptable, idFunctionObject, objArr);
        }
        if (methodId == 2) {
            return js_toString(scriptable2);
        }
        if (methodId == 3) {
            return js_toSource(context, scriptable, scriptable2);
        }
        throw new IllegalArgumentException(String.valueOf(methodId));
    }

    public void setStackProvider(RhinoException rhinoException) {
        Class<NativeError> cls = NativeError.class;
        if (this.stackProvider == null) {
            this.stackProvider = rhinoException;
            try {
                defineProperty("stack", (Object) null, cls.getMethod("getStack", new Class[0]), cls.getMethod("setStack", new Class[]{Object.class}), 0);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Object getStack() {
        RhinoException rhinoException = this.stackProvider;
        Object scriptStackTrace = rhinoException == null ? NOT_FOUND : rhinoException.getScriptStackTrace();
        setStack(scriptStackTrace);
        return scriptStackTrace;
    }

    public void setStack(Object obj) {
        if (this.stackProvider != null) {
            this.stackProvider = null;
            delete("stack");
        }
        put("stack", this, obj);
    }

    private static Object js_toString(Scriptable scriptable) {
        Object property = ScriptableObject.getProperty(scriptable, "name");
        String scriptRuntime = (property == NOT_FOUND || property == Undefined.instance) ? "Error" : ScriptRuntime.toString(property);
        Object property2 = ScriptableObject.getProperty(scriptable, ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        if (property2 == NOT_FOUND || property2 == Undefined.instance) {
            return Undefined.instance;
        }
        return scriptRuntime + ": " + ScriptRuntime.toString(property2);
    }

    private static String js_toSource(Context context, Scriptable scriptable, Scriptable scriptable2) {
        int int32;
        Object property = ScriptableObject.getProperty(scriptable2, "name");
        Object property2 = ScriptableObject.getProperty(scriptable2, ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        Object property3 = ScriptableObject.getProperty(scriptable2, "fileName");
        Object property4 = ScriptableObject.getProperty(scriptable2, "lineNumber");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("(new ");
        if (property == NOT_FOUND) {
            property = Undefined.instance;
        }
        stringBuffer.append(ScriptRuntime.toString(property));
        stringBuffer.append("(");
        if (!(property2 == NOT_FOUND && property3 == NOT_FOUND && property4 == NOT_FOUND)) {
            if (property2 == NOT_FOUND) {
                property2 = "";
            }
            stringBuffer.append(ScriptRuntime.uneval(context, scriptable, property2));
            if (!(property3 == NOT_FOUND && property4 == NOT_FOUND)) {
                stringBuffer.append(", ");
                if (property3 == NOT_FOUND) {
                    property3 = "";
                }
                stringBuffer.append(ScriptRuntime.uneval(context, scriptable, property3));
                if (!(property4 == NOT_FOUND || (int32 = ScriptRuntime.toInt32(property4)) == 0)) {
                    stringBuffer.append(", ");
                    stringBuffer.append(ScriptRuntime.toString((double) int32));
                }
            }
        }
        stringBuffer.append("))");
        return stringBuffer.toString();
    }

    private static String getString(Scriptable scriptable, String str) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property == NOT_FOUND) {
            return "";
        }
        return ScriptRuntime.toString(property);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r5) {
        /*
            r4 = this;
            int r0 = r5.length()
            r1 = 3
            r2 = 0
            r3 = 8
            if (r0 != r3) goto L_0x001d
            char r0 = r5.charAt(r1)
            r3 = 111(0x6f, float:1.56E-43)
            if (r0 != r3) goto L_0x0015
            java.lang.String r0 = "toSource"
            goto L_0x0027
        L_0x0015:
            r1 = 116(0x74, float:1.63E-43)
            if (r0 != r1) goto L_0x0025
            r1 = 2
            java.lang.String r0 = "toString"
            goto L_0x0027
        L_0x001d:
            r1 = 11
            if (r0 != r1) goto L_0x0025
            r1 = 1
            java.lang.String r0 = "constructor"
            goto L_0x0027
        L_0x0025:
            r0 = 0
            r1 = 0
        L_0x0027:
            if (r0 == 0) goto L_0x0032
            if (r0 == r5) goto L_0x0032
            boolean r5 = r0.equals(r5)
            if (r5 != 0) goto L_0x0032
            goto L_0x0033
        L_0x0032:
            r2 = r1
        L_0x0033:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeError.findPrototypeId(java.lang.String):int");
    }
}

package org.mozilla.javascript.xml.impl.xmlbeans;

import com.dd.plist.ASCIIPropertyListParser;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

class Namespace extends IdScriptableObject {
    private static final int Id_constructor = 1;
    private static final int Id_prefix = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_uri = 2;
    private static final int MAX_INSTANCE_ID = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final Object NAMESPACE_TAG = "Namespace";
    static final long serialVersionUID = -5765755238131301744L;
    private XMLLibImpl lib;
    private String prefix;
    private String uri;

    public String getClassName() {
        return "Namespace";
    }

    public Namespace(XMLLibImpl xMLLibImpl, String str) {
        super(xMLLibImpl.globalScope(), xMLLibImpl.namespacePrototype);
        if (str != null) {
            this.lib = xMLLibImpl;
            this.prefix = str.length() == 0 ? "" : null;
            this.uri = str;
            return;
        }
        throw new IllegalArgumentException();
    }

    public Namespace(XMLLibImpl xMLLibImpl, String str, String str2) {
        super(xMLLibImpl.globalScope(), xMLLibImpl.namespacePrototype);
        if (str2 != null) {
            if (str2.length() == 0) {
                if (str == null) {
                    throw new IllegalArgumentException();
                } else if (str.length() != 0) {
                    throw new IllegalArgumentException();
                }
            }
            this.lib = xMLLibImpl;
            this.prefix = str;
            this.uri = str2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void exportAsJSClass(boolean z) {
        exportAsJSClass(3, this.lib.globalScope(), z);
    }

    public String uri() {
        return this.uri;
    }

    public String prefix() {
        return this.prefix;
    }

    public String toString() {
        return uri();
    }

    public String toLocaleString() {
        return toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Namespace)) {
            return false;
        }
        return equals((Namespace) obj);
    }

    public int hashCode() {
        return uri().hashCode();
    }

    /* access modifiers changed from: protected */
    public Object equivalentValues(Object obj) {
        if (!(obj instanceof Namespace)) {
            return Scriptable.NOT_FOUND;
        }
        return equals((Namespace) obj) ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean equals(Namespace namespace) {
        return uri().equals(namespace.uri());
    }

    public Object getDefaultValue(Class cls) {
        return uri();
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return super.getMaxInstanceId() + 2;
    }

    /* access modifiers changed from: protected */
    public int findInstanceIdInfo(String str) {
        int i;
        String str2;
        int length = str.length();
        int i2 = 0;
        if (length == 3) {
            str2 = "uri";
            i = 2;
        } else if (length == 6) {
            str2 = "prefix";
            i = 1;
        } else {
            str2 = null;
            i = 0;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            i2 = i;
        }
        if (i2 == 0) {
            return super.findInstanceIdInfo(str);
        }
        if (i2 == 1 || i2 == 2) {
            return instanceIdInfo(5, super.getMaxInstanceId() + i2);
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (maxInstanceId != 1) {
            return maxInstanceId != 2 ? super.getInstanceIdName(i) : "uri";
        }
        return "prefix";
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (maxInstanceId == 1) {
            String str = this.prefix;
            return str == null ? Undefined.instance : str;
        } else if (maxInstanceId != 2) {
            return super.getInstanceIdValue(i);
        } else {
            return this.uri;
        }
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
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.Namespace.findPrototypeId(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        int i2 = 0;
        if (i == 1) {
            str = "constructor";
            i2 = 2;
        } else if (i == 2) {
            str = "toString";
        } else if (i == 3) {
            str = "toSource";
        } else {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(NAMESPACE_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(NAMESPACE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        boolean z = true;
        if (methodId == 1) {
            if (scriptable2 != null) {
                z = false;
            }
            return jsConstructor(context, z, objArr);
        } else if (methodId == 2) {
            return realThis(scriptable2, idFunctionObject).toString();
        } else {
            if (methodId == 3) {
                return realThis(scriptable2, idFunctionObject).js_toSource();
            }
            throw new IllegalArgumentException(String.valueOf(methodId));
        }
    }

    private Namespace realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof Namespace) {
            return (Namespace) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    private Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (!z && objArr.length == 1) {
            return this.lib.castToNamespace(context, objArr[0]);
        }
        if (objArr.length == 0) {
            return this.lib.constructNamespace(context);
        }
        if (objArr.length == 1) {
            return this.lib.constructNamespace(context, objArr[0]);
        }
        return this.lib.constructNamespace(context, objArr[0], objArr[1]);
    }

    private String js_toSource() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        toSourceImpl(this.prefix, this.uri, stringBuffer);
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        return stringBuffer.toString();
    }

    static void toSourceImpl(String str, String str2, StringBuffer stringBuffer) {
        stringBuffer.append("new Namespace(");
        if (str2.length() != 0) {
            stringBuffer.append('\'');
            if (str != null) {
                stringBuffer.append(ScriptRuntime.escapeString(str, '\''));
                stringBuffer.append("', '");
            }
            stringBuffer.append(ScriptRuntime.escapeString(str2, '\''));
            stringBuffer.append('\'');
        } else if (!"".equals(str)) {
            throw new IllegalArgumentException(str);
        }
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
    }
}

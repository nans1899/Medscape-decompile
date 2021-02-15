package org.mozilla.javascript.xml.impl.xmlbeans;

import com.dd.plist.ASCIIPropertyListParser;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

final class QName extends IdScriptableObject {
    private static final int Id_constructor = 1;
    private static final int Id_localName = 1;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_uri = 2;
    private static final int MAX_INSTANCE_ID = 2;
    private static final int MAX_PROTOTYPE_ID = 3;
    private static final Object QNAME_TAG = "QName";
    static final long serialVersionUID = 416745167693026750L;
    XMLLibImpl lib;
    private String localName;
    private String prefix;
    private String uri;

    public String getClassName() {
        return "QName";
    }

    QName(XMLLibImpl xMLLibImpl, String str, String str2, String str3) {
        super(xMLLibImpl.globalScope(), xMLLibImpl.qnamePrototype);
        if (str2 != null) {
            this.lib = xMLLibImpl;
            this.uri = str;
            this.prefix = str3;
            this.localName = str2;
            return;
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public void exportAsJSClass(boolean z) {
        exportAsJSClass(3, this.lib.globalScope(), z);
    }

    public String toString() {
        String str = this.uri;
        if (str == null) {
            return "*::".concat(this.localName);
        }
        if (str.length() == 0) {
            return this.localName;
        }
        return this.uri + "::" + this.localName;
    }

    public String localName() {
        return this.localName;
    }

    /* access modifiers changed from: package-private */
    public String prefix() {
        String str = this.prefix;
        return str == null ? str : "";
    }

    /* access modifiers changed from: package-private */
    public String uri() {
        return this.uri;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof QName)) {
            return false;
        }
        return equals((QName) obj);
    }

    public int hashCode() {
        int hashCode = this.localName.hashCode();
        String str = this.uri;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    /* access modifiers changed from: protected */
    public Object equivalentValues(Object obj) {
        if (!(obj instanceof QName)) {
            return Scriptable.NOT_FOUND;
        }
        return equals((QName) obj) ? Boolean.TRUE : Boolean.FALSE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0015 A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean equals(org.mozilla.javascript.xml.impl.xmlbeans.QName r5) {
        /*
            r4 = this;
            java.lang.String r0 = r4.uri
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L_0x0017
            java.lang.String r0 = r5.uri
            if (r0 != 0) goto L_0x0015
            java.lang.String r0 = r4.localName
            java.lang.String r5 = r5.localName
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            r1 = 0
            goto L_0x0029
        L_0x0017:
            java.lang.String r3 = r5.uri
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0015
            java.lang.String r0 = r4.localName
            java.lang.String r5 = r5.localName
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x0015
        L_0x0029:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.QName.equals(org.mozilla.javascript.xml.impl.xmlbeans.QName):boolean");
    }

    public Object getDefaultValue(Class cls) {
        return toString();
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
        } else if (length == 9) {
            str2 = "localName";
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
        return "localName";
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (maxInstanceId == 1) {
            return this.localName;
        }
        if (maxInstanceId != 2) {
            return super.getInstanceIdValue(i);
        }
        return this.uri;
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
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.QName.findPrototypeId(java.lang.String):int");
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
        initPrototypeMethod(QNAME_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(QNAME_TAG)) {
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

    private QName realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof QName) {
            return (QName) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    private Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (!z && objArr.length == 1) {
            return this.lib.castToQName(context, objArr[0]);
        }
        if (objArr.length == 0) {
            return this.lib.constructQName(context, Undefined.instance);
        }
        if (objArr.length == 1) {
            return this.lib.constructQName(context, objArr[0]);
        }
        return this.lib.constructQName(context, objArr[0], objArr[1]);
    }

    private String js_toSource() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        toSourceImpl(this.uri, this.localName, this.prefix, stringBuffer);
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        return stringBuffer.toString();
    }

    private static void toSourceImpl(String str, String str2, String str3, StringBuffer stringBuffer) {
        stringBuffer.append("new QName(");
        if (str != null || str3 != null) {
            Namespace.toSourceImpl(str3, str, stringBuffer);
            stringBuffer.append(", ");
        } else if (!"*".equals(str2)) {
            stringBuffer.append("null, ");
        }
        stringBuffer.append('\'');
        stringBuffer.append(ScriptRuntime.escapeString(str2, '\''));
        stringBuffer.append("')");
    }
}

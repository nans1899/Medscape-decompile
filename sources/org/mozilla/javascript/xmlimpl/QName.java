package org.mozilla.javascript.xmlimpl;

import com.dd.plist.ASCIIPropertyListParser;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.IdScriptableObject;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xmlimpl.XmlNode;

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
    private XmlNode.QName delegate;
    private XMLLibImpl lib;
    private QName prototype;

    public String getClassName() {
        return "QName";
    }

    private QName() {
    }

    static QName create(XMLLibImpl xMLLibImpl, Scriptable scriptable, QName qName, XmlNode.QName qName2) {
        QName qName3 = new QName();
        qName3.lib = xMLLibImpl;
        qName3.setParentScope(scriptable);
        qName3.prototype = qName;
        qName3.setPrototype(qName);
        qName3.delegate = qName2;
        return qName3;
    }

    /* access modifiers changed from: package-private */
    public void exportAsJSClass(boolean z) {
        exportAsJSClass(3, getParentScope(), z);
    }

    public String toString() {
        if (this.delegate.getNamespace() == null) {
            return "*::" + localName();
        } else if (this.delegate.getNamespace().isGlobal()) {
            return localName();
        } else {
            return uri() + "::" + localName();
        }
    }

    public String localName() {
        if (this.delegate.getLocalName() == null) {
            return "*";
        }
        return this.delegate.getLocalName();
    }

    /* access modifiers changed from: package-private */
    public String prefix() {
        if (this.delegate.getNamespace() == null) {
            return null;
        }
        return this.delegate.getNamespace().getPrefix();
    }

    /* access modifiers changed from: package-private */
    public String uri() {
        if (this.delegate.getNamespace() == null) {
            return null;
        }
        return this.delegate.getNamespace().getUri();
    }

    /* access modifiers changed from: package-private */
    public final XmlNode.QName toNodeQname() {
        return this.delegate;
    }

    /* access modifiers changed from: package-private */
    public final XmlNode.QName getDelegate() {
        return this.delegate;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof QName)) {
            return false;
        }
        return equals((QName) obj);
    }

    public int hashCode() {
        return this.delegate.hashCode();
    }

    /* access modifiers changed from: protected */
    public Object equivalentValues(Object obj) {
        if (!(obj instanceof QName)) {
            return Scriptable.NOT_FOUND;
        }
        return equals((QName) obj) ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean equals(QName qName) {
        return this.delegate.equals(qName.delegate);
    }

    public Object getDefaultValue(Class<?> cls) {
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
            return localName();
        }
        if (maxInstanceId != 2) {
            return super.getInstanceIdValue(i);
        }
        return uri();
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
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.QName.findPrototypeId(java.lang.String):int");
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

    /* access modifiers changed from: package-private */
    public QName newQName(XMLLibImpl xMLLibImpl, String str, String str2, String str3) {
        XmlNode.Namespace namespace;
        QName qName = this.prototype;
        if (qName == null) {
            qName = this;
        }
        if (str3 != null) {
            namespace = XmlNode.Namespace.create(str3, str);
        } else {
            namespace = str != null ? XmlNode.Namespace.create(str) : null;
        }
        if (str2 != null && str2.equals("*")) {
            str2 = null;
        }
        return create(xMLLibImpl, getParentScope(), qName, XmlNode.QName.create(namespace, str2));
    }

    /* access modifiers changed from: package-private */
    public QName constructQName(XMLLibImpl xMLLibImpl, Context context, Object obj, Object obj2) {
        String str;
        Namespace namespace;
        String str2;
        if (obj2 instanceof QName) {
            if (obj == Undefined.instance) {
                return (QName) obj2;
            }
            ((QName) obj2).localName();
        }
        if (obj2 == Undefined.instance) {
            str = "";
        } else {
            str = ScriptRuntime.toString(obj2);
        }
        String str3 = null;
        if (obj == Undefined.instance) {
            if ("*".equals(str)) {
                obj = null;
            } else {
                obj = xMLLibImpl.getDefaultNamespace(context);
            }
        }
        if (obj == null) {
            namespace = null;
        } else if (obj instanceof Namespace) {
            namespace = (Namespace) obj;
        } else {
            namespace = xMLLibImpl.newNamespace(ScriptRuntime.toString(obj));
        }
        if (obj == null) {
            str2 = null;
        } else {
            str3 = namespace.uri();
            str2 = namespace.prefix();
        }
        return newQName(xMLLibImpl, str3, str, str2);
    }

    /* access modifiers changed from: package-private */
    public QName constructQName(XMLLibImpl xMLLibImpl, Context context, Object obj) {
        return constructQName(xMLLibImpl, context, Undefined.instance, obj);
    }

    /* access modifiers changed from: package-private */
    public QName castToQName(XMLLibImpl xMLLibImpl, Context context, Object obj) {
        if (obj instanceof QName) {
            return (QName) obj;
        }
        return constructQName(xMLLibImpl, context, obj);
    }

    private Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (!z && objArr.length == 1) {
            return castToQName(this.lib, context, objArr[0]);
        }
        if (objArr.length == 0) {
            return constructQName(this.lib, context, Undefined.instance);
        }
        if (objArr.length == 1) {
            return constructQName(this.lib, context, objArr[0]);
        }
        return constructQName(this.lib, context, objArr[0], objArr[1]);
    }

    private String js_toSource() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        toSourceImpl(uri(), localName(), prefix(), stringBuffer);
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

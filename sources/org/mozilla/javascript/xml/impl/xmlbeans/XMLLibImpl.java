package org.mozilla.javascript.xml.impl.xmlbeans;

import com.appboy.Constants;
import java.io.Serializable;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;

public final class XMLLibImpl extends XMLLib implements Serializable {
    private static final long serialVersionUID = 1;
    private Scriptable globalScope;
    boolean ignoreComments;
    boolean ignoreProcessingInstructions;
    boolean ignoreWhitespace;
    Namespace namespacePrototype;
    int prettyIndent;
    boolean prettyPrinting;
    QName qnamePrototype;
    XMLList xmlListPrototype;
    XML xmlPrototype;

    private static boolean isNCNameStartChar(int i) {
        if ((i & -128) == 0) {
            if (i >= 97) {
                return i <= 122;
            }
            if (i >= 65) {
                return i <= 90 || i == 95;
            }
        } else if ((i & -8192) == 0) {
            return (192 <= i && i <= 214) || (216 <= i && i <= 246) || ((248 <= i && i <= 767) || ((880 <= i && i <= 893) || 895 <= i));
        }
        return (8204 <= i && i <= 8205) || (8304 <= i && i <= 8591) || ((11264 <= i && i <= 12271) || ((12289 <= i && i <= 55295) || ((63744 <= i && i <= 64975) || ((65008 <= i && i <= 65533) || (65536 <= i && i <= 983039)))));
    }

    /* access modifiers changed from: package-private */
    public Scriptable globalScope() {
        return this.globalScope;
    }

    private XMLLibImpl(Scriptable scriptable) {
        this.globalScope = scriptable;
        defaultSettings();
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        XmlObject.class.getName();
        XMLLibImpl xMLLibImpl = new XMLLibImpl(scriptable);
        if (xMLLibImpl.bindToScope(scriptable) == xMLLibImpl) {
            xMLLibImpl.exportToScope(z);
        }
    }

    private void exportToScope(boolean z) {
        this.xmlPrototype = XML.createEmptyXML(this);
        this.xmlListPrototype = new XMLList(this);
        this.namespacePrototype = new Namespace(this, "", "");
        this.qnamePrototype = new QName(this, "", "", "");
        this.xmlPrototype.exportAsJSClass(z);
        this.xmlListPrototype.exportAsJSClass(z);
        this.namespacePrototype.exportAsJSClass(z);
        this.qnamePrototype.exportAsJSClass(z);
    }

    /* access modifiers changed from: package-private */
    public void defaultSettings() {
        this.ignoreComments = true;
        this.ignoreProcessingInstructions = true;
        this.ignoreWhitespace = true;
        this.prettyPrinting = true;
        this.prettyIndent = 2;
    }

    /* access modifiers changed from: package-private */
    public XMLName toAttributeName(Context context, Object obj) {
        String str;
        String str2 = "";
        if (obj instanceof String) {
            str = (String) obj;
        } else if (obj instanceof XMLName) {
            XMLName xMLName = (XMLName) obj;
            if (!xMLName.isAttributeName()) {
                xMLName.setAttributeName();
            }
            return xMLName;
        } else if (obj instanceof QName) {
            QName qName = (QName) obj;
            str2 = qName.uri();
            str = qName.localName();
        } else if ((obj instanceof Boolean) || (obj instanceof Number) || obj == Undefined.instance || obj == null) {
            throw badXMLName(obj);
        } else {
            str = ScriptRuntime.toString(obj);
        }
        XMLName formProperty = XMLName.formProperty(str2, str);
        formProperty.setAttributeName();
        return formProperty;
    }

    private static RuntimeException badXMLName(Object obj) {
        String str;
        if (obj instanceof Number) {
            str = "Can not construct XML name from number: ";
        } else if (obj instanceof Boolean) {
            str = "Can not construct XML name from boolean: ";
        } else if (obj == Undefined.instance || obj == null) {
            str = "Can not construct XML name from ";
        } else {
            throw new IllegalArgumentException(obj.toString());
        }
        return ScriptRuntime.typeError(str + ScriptRuntime.toString(obj));
    }

    /* access modifiers changed from: package-private */
    public XMLName toXMLName(Context context, Object obj) {
        if (obj instanceof XMLName) {
            return (XMLName) obj;
        }
        if (obj instanceof QName) {
            QName qName = (QName) obj;
            return XMLName.formProperty(qName.uri(), qName.localName());
        } else if (obj instanceof String) {
            return toXMLNameFromString(context, (String) obj);
        } else {
            if (!(obj instanceof Boolean) && !(obj instanceof Number) && obj != Undefined.instance && obj != null) {
                return toXMLNameFromString(context, ScriptRuntime.toString(obj));
            }
            throw badXMLName(obj);
        }
    }

    /* access modifiers changed from: package-private */
    public XMLName toXMLNameOrIndex(Context context, Object obj) {
        if (obj instanceof XMLName) {
            return (XMLName) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            long testUint32String = ScriptRuntime.testUint32String(str);
            if (testUint32String < 0) {
                return toXMLNameFromString(context, str);
            }
            ScriptRuntime.storeUint32Result(context, testUint32String);
            return null;
        } else if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            long j = (long) doubleValue;
            if (((double) j) != doubleValue || 0 > j || j > 4294967295L) {
                throw badXMLName(obj);
            }
            ScriptRuntime.storeUint32Result(context, j);
            return null;
        } else if (obj instanceof QName) {
            QName qName = (QName) obj;
            String uri = qName.uri();
            boolean z = false;
            if (uri != null && uri.length() == 0) {
                long testUint32String2 = ScriptRuntime.testUint32String(uri);
                if (testUint32String2 >= 0) {
                    ScriptRuntime.storeUint32Result(context, testUint32String2);
                    z = true;
                }
            }
            if (!z) {
                return XMLName.formProperty(uri, qName.localName());
            }
            return null;
        } else if ((obj instanceof Boolean) || obj == Undefined.instance || obj == null) {
            throw badXMLName(obj);
        } else {
            String scriptRuntime = ScriptRuntime.toString(obj);
            long testUint32String3 = ScriptRuntime.testUint32String(scriptRuntime);
            if (testUint32String3 < 0) {
                return toXMLNameFromString(context, scriptRuntime);
            }
            ScriptRuntime.storeUint32Result(context, testUint32String3);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public XMLName toXMLNameFromString(Context context, String str) {
        if (str != null) {
            int length = str.length();
            if (length != 0) {
                char charAt = str.charAt(0);
                if (charAt == '*') {
                    if (length == 1) {
                        return XMLName.formStar();
                    }
                } else if (charAt == '@') {
                    XMLName formProperty = XMLName.formProperty("", str.substring(1));
                    formProperty.setAttributeName();
                    return formProperty;
                }
            }
            return XMLName.formProperty(getDefaultNamespaceURI(context), str);
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public Namespace constructNamespace(Context context, Object obj) {
        String str;
        String str2;
        if (obj instanceof Namespace) {
            Namespace namespace = (Namespace) obj;
            str2 = namespace.prefix();
            str = namespace.uri();
        } else {
            if (obj instanceof QName) {
                QName qName = (QName) obj;
                String uri = qName.uri();
                if (uri != null) {
                    String prefix = qName.prefix();
                    str = uri;
                    str2 = prefix;
                } else {
                    str = qName.toString();
                }
            } else {
                str = ScriptRuntime.toString(obj);
                if (str.length() == 0) {
                    str2 = "";
                }
            }
            str2 = null;
        }
        return new Namespace(this, str2, str);
    }

    /* access modifiers changed from: package-private */
    public Namespace castToNamespace(Context context, Object obj) {
        if (obj instanceof Namespace) {
            return (Namespace) obj;
        }
        return constructNamespace(context, obj);
    }

    /* access modifiers changed from: package-private */
    public Namespace constructNamespace(Context context) {
        return new Namespace(this, "", "");
    }

    public Namespace constructNamespace(Context context, Object obj, Object obj2) {
        String str;
        if (obj2 instanceof QName) {
            QName qName = (QName) obj2;
            str = qName.uri();
            if (str == null) {
                str = qName.toString();
            }
        } else {
            str = ScriptRuntime.toString(obj2);
        }
        String str2 = "";
        if (str.length() == 0) {
            if (obj != Undefined.instance) {
                str2 = ScriptRuntime.toString(obj);
                if (str2.length() != 0) {
                    throw ScriptRuntime.typeError("Illegal prefix '" + str2 + "' for 'no namespace'.");
                }
            }
        } else if (obj != Undefined.instance && isXMLName(context, obj)) {
            str2 = ScriptRuntime.toString(obj);
        }
        return new Namespace(this, str2, str);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r2 = org.mozilla.javascript.ScriptRuntime.searchDefaultNamespace(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getDefaultNamespaceURI(org.mozilla.javascript.Context r2) {
        /*
            r1 = this;
            if (r2 != 0) goto L_0x0006
            org.mozilla.javascript.Context r2 = org.mozilla.javascript.Context.getCurrentContext()
        L_0x0006:
            if (r2 == 0) goto L_0x0019
            java.lang.Object r2 = org.mozilla.javascript.ScriptRuntime.searchDefaultNamespace(r2)
            if (r2 == 0) goto L_0x0019
            boolean r0 = r2 instanceof org.mozilla.javascript.xml.impl.xmlbeans.Namespace
            if (r0 == 0) goto L_0x0019
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r2 = (org.mozilla.javascript.xml.impl.xmlbeans.Namespace) r2
            java.lang.String r2 = r2.uri()
            goto L_0x001b
        L_0x0019:
            java.lang.String r2 = ""
        L_0x001b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl.getDefaultNamespaceURI(org.mozilla.javascript.Context):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public Namespace getDefaultNamespace(Context context) {
        if (context == null && (context = Context.getCurrentContext()) == null) {
            return this.namespacePrototype;
        }
        Object searchDefaultNamespace = ScriptRuntime.searchDefaultNamespace(context);
        if (searchDefaultNamespace == null) {
            return this.namespacePrototype;
        }
        if (searchDefaultNamespace instanceof Namespace) {
            return (Namespace) searchDefaultNamespace;
        }
        return this.namespacePrototype;
    }

    /* access modifiers changed from: package-private */
    public QName castToQName(Context context, Object obj) {
        if (obj instanceof QName) {
            return (QName) obj;
        }
        return constructQName(context, obj);
    }

    /* access modifiers changed from: package-private */
    public QName constructQName(Context context, Object obj) {
        if (!(obj instanceof QName)) {
            return constructQNameFromString(context, ScriptRuntime.toString(obj));
        }
        QName qName = (QName) obj;
        return new QName(this, qName.uri(), qName.localName(), qName.prefix());
    }

    /* access modifiers changed from: package-private */
    public QName constructQNameFromString(Context context, String str) {
        String str2;
        if (str != null) {
            String str3 = null;
            if ("*".equals(str)) {
                str2 = null;
            } else {
                Namespace defaultNamespace = getDefaultNamespace(context);
                str3 = defaultNamespace.uri();
                str2 = defaultNamespace.prefix();
            }
            return new QName(this, str3, str, str2);
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.xml.impl.xmlbeans.QName constructQName(org.mozilla.javascript.Context r3, java.lang.Object r4, java.lang.Object r5) {
        /*
            r2 = this;
            boolean r0 = r5 instanceof org.mozilla.javascript.xml.impl.xmlbeans.QName
            if (r0 == 0) goto L_0x000b
            org.mozilla.javascript.xml.impl.xmlbeans.QName r5 = (org.mozilla.javascript.xml.impl.xmlbeans.QName) r5
            java.lang.String r5 = r5.localName()
            goto L_0x000f
        L_0x000b:
            java.lang.String r5 = org.mozilla.javascript.ScriptRuntime.toString((java.lang.Object) r5)
        L_0x000f:
            java.lang.Object r0 = org.mozilla.javascript.Undefined.instance
            r1 = 0
            if (r4 != r0) goto L_0x0022
            java.lang.String r4 = "*"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x001d
            goto L_0x0024
        L_0x001d:
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r3 = r2.getDefaultNamespace(r3)
            goto L_0x0032
        L_0x0022:
            if (r4 != 0) goto L_0x0026
        L_0x0024:
            r3 = r1
            goto L_0x0032
        L_0x0026:
            boolean r0 = r4 instanceof org.mozilla.javascript.xml.impl.xmlbeans.Namespace
            if (r0 == 0) goto L_0x002e
            r3 = r4
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r3 = (org.mozilla.javascript.xml.impl.xmlbeans.Namespace) r3
            goto L_0x0032
        L_0x002e:
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r3 = r2.constructNamespace(r3, r4)
        L_0x0032:
            if (r3 != 0) goto L_0x0036
            r3 = r1
            goto L_0x003e
        L_0x0036:
            java.lang.String r1 = r3.uri()
            java.lang.String r3 = r3.prefix()
        L_0x003e:
            org.mozilla.javascript.xml.impl.xmlbeans.QName r4 = new org.mozilla.javascript.xml.impl.xmlbeans.QName
            r4.<init>(r2, r1, r5, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl.constructQName(org.mozilla.javascript.Context, java.lang.Object, java.lang.Object):org.mozilla.javascript.xml.impl.xmlbeans.QName");
    }

    /* access modifiers changed from: package-private */
    public Object addXMLObjects(Context context, XMLObject xMLObject, XMLObject xMLObject2) {
        XMLList xMLList = new XMLList(this);
        if (xMLObject instanceof XMLList) {
            XMLList xMLList2 = (XMLList) xMLObject;
            if (xMLList2.length() == 1) {
                xMLList.addToList(xMLList2.item(0));
            } else {
                xMLList = new XMLList(this, xMLObject);
            }
        } else {
            xMLList.addToList(xMLObject);
        }
        if (xMLObject2 instanceof XMLList) {
            XMLList xMLList3 = (XMLList) xMLObject2;
            for (int i = 0; i < xMLList3.length(); i++) {
                xMLList.addToList(xMLList3.item(i));
            }
        } else if (xMLObject2 instanceof XML) {
            xMLList.addToList(xMLObject2);
        }
        return xMLList;
    }

    public boolean isXMLName(Context context, Object obj) {
        try {
            String scriptRuntime = ScriptRuntime.toString(obj);
            int length = scriptRuntime.length();
            if (length == 0 || !isNCNameStartChar(scriptRuntime.charAt(0))) {
                return false;
            }
            for (int i = 1; i != length; i++) {
                if (!isNCNameChar(scriptRuntime.charAt(i))) {
                    return false;
                }
            }
            return true;
        } catch (EcmaError e) {
            if ("TypeError".equals(e.getName())) {
                return false;
            }
            throw e;
        }
    }

    private static boolean isNCNameChar(int i) {
        if ((i & -128) == 0) {
            return i >= 97 ? i <= 122 : i >= 65 ? i <= 90 || i == 95 : i >= 48 ? i <= 57 : i == 45 || i == 46;
        }
        if ((i & -8192) == 0) {
            return isNCNameStartChar(i) || i == 183 || (768 <= i && i <= 879);
        }
        if (isNCNameStartChar(i) || (8255 <= i && i <= 8256)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.xml.impl.xmlbeans.XMLName toQualifiedName(org.mozilla.javascript.Context r3, java.lang.Object r4, java.lang.Object r5) {
        /*
            r2 = this;
            boolean r0 = r5 instanceof org.mozilla.javascript.xml.impl.xmlbeans.QName
            if (r0 == 0) goto L_0x000b
            org.mozilla.javascript.xml.impl.xmlbeans.QName r5 = (org.mozilla.javascript.xml.impl.xmlbeans.QName) r5
            java.lang.String r5 = r5.localName()
            goto L_0x000f
        L_0x000b:
            java.lang.String r5 = org.mozilla.javascript.ScriptRuntime.toString((java.lang.Object) r5)
        L_0x000f:
            java.lang.Object r0 = org.mozilla.javascript.Undefined.instance
            r1 = 0
            if (r4 != r0) goto L_0x0022
            java.lang.String r4 = "*"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x001d
            goto L_0x0024
        L_0x001d:
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r3 = r2.getDefaultNamespace(r3)
            goto L_0x0032
        L_0x0022:
            if (r4 != 0) goto L_0x0026
        L_0x0024:
            r3 = r1
            goto L_0x0032
        L_0x0026:
            boolean r0 = r4 instanceof org.mozilla.javascript.xml.impl.xmlbeans.Namespace
            if (r0 == 0) goto L_0x002e
            r3 = r4
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r3 = (org.mozilla.javascript.xml.impl.xmlbeans.Namespace) r3
            goto L_0x0032
        L_0x002e:
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r3 = r2.constructNamespace(r3, r4)
        L_0x0032:
            if (r3 != 0) goto L_0x0035
            goto L_0x0039
        L_0x0035:
            java.lang.String r1 = r3.uri()
        L_0x0039:
            org.mozilla.javascript.xml.impl.xmlbeans.XMLName r3 = org.mozilla.javascript.xml.impl.xmlbeans.XMLName.formProperty(r1, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl.toQualifiedName(org.mozilla.javascript.Context, java.lang.Object, java.lang.Object):org.mozilla.javascript.xml.impl.xmlbeans.XMLName");
    }

    public Ref nameRef(Context context, Object obj, Scriptable scriptable, int i) {
        if ((i & 2) != 0) {
            return xmlPrimaryReference(context, toAttributeName(context, obj), scriptable);
        }
        throw Kit.codeBug();
    }

    public Ref nameRef(Context context, Object obj, Object obj2, Scriptable scriptable, int i) {
        XMLName qualifiedName = toQualifiedName(context, obj, obj2);
        if ((i & 2) != 0 && !qualifiedName.isAttributeName()) {
            qualifiedName.setAttributeName();
        }
        return xmlPrimaryReference(context, qualifiedName, scriptable);
    }

    private Ref xmlPrimaryReference(Context context, XMLName xMLName, Scriptable scriptable) {
        XMLObjectImpl xMLObjectImpl;
        XMLObjectImpl xMLObjectImpl2 = null;
        while (true) {
            if (scriptable instanceof XMLWithScope) {
                xMLObjectImpl = (XMLObjectImpl) scriptable.getPrototype();
                if (xMLObjectImpl.hasXMLProperty(xMLName)) {
                    break;
                } else if (xMLObjectImpl2 == null) {
                    xMLObjectImpl2 = xMLObjectImpl;
                }
            }
            scriptable = scriptable.getParentScope();
            if (scriptable == null) {
                xMLObjectImpl = xMLObjectImpl2;
                break;
            }
        }
        if (xMLObjectImpl != null) {
            xMLName.initXMLObject(xMLObjectImpl);
        }
        return xMLName;
    }

    public String escapeAttributeValue(Object obj) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        if (scriptRuntime.length() == 0) {
            return "";
        }
        XmlObject newInstance = XmlObject.Factory.newInstance();
        XmlCursor newCursor = newInstance.newCursor();
        newCursor.toNextToken();
        newCursor.beginElement(Constants.APPBOY_PUSH_CONTENT_KEY);
        newCursor.insertAttributeWithValue(Constants.APPBOY_PUSH_CONTENT_KEY, scriptRuntime);
        newCursor.dispose();
        String xmlObject = newInstance.toString();
        return xmlObject.substring(xmlObject.indexOf(34) + 1, xmlObject.lastIndexOf(34));
    }

    public String escapeTextValue(Object obj) {
        if (obj instanceof XMLObjectImpl) {
            return ((XMLObjectImpl) obj).toXMLString(0);
        }
        String scriptRuntime = ScriptRuntime.toString(obj);
        if (scriptRuntime.length() == 0) {
            return scriptRuntime;
        }
        XmlObject newInstance = XmlObject.Factory.newInstance();
        XmlCursor newCursor = newInstance.newCursor();
        newCursor.toNextToken();
        newCursor.beginElement(Constants.APPBOY_PUSH_CONTENT_KEY);
        newCursor.insertChars(scriptRuntime);
        newCursor.dispose();
        String xmlObject = newInstance.toString();
        int indexOf = xmlObject.indexOf(62) + 1;
        int lastIndexOf = xmlObject.lastIndexOf(60);
        return indexOf < lastIndexOf ? xmlObject.substring(indexOf, lastIndexOf) : "";
    }

    public Object toDefaultXmlNamespace(Context context, Object obj) {
        return constructNamespace(context, obj);
    }
}

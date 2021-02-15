package org.mozilla.javascript.xmlimpl;

import com.dd.plist.ASCIIPropertyListParser;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.EcmaError;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xmlimpl.XmlNode;

class XMLName extends Ref {
    static final long serialVersionUID = 3832176310755686977L;
    private boolean isAttributeName;
    private boolean isDescendants;
    private XmlNode.QName qname;
    private XMLObjectImpl xmlObject;

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

    static boolean accept(Object obj) {
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

    private XMLName() {
    }

    static XMLName formStar() {
        XMLName xMLName = new XMLName();
        xMLName.qname = XmlNode.QName.create((XmlNode.Namespace) null, (String) null);
        return xMLName;
    }

    static XMLName formProperty(XmlNode.Namespace namespace, String str) {
        if (str != null && str.equals("*")) {
            str = null;
        }
        XMLName xMLName = new XMLName();
        xMLName.qname = XmlNode.QName.create(namespace, str);
        return xMLName;
    }

    static XMLName formProperty(String str, String str2) {
        return formProperty(XmlNode.Namespace.create(str), str2);
    }

    static XMLName create(String str, String str2) {
        if (str2 != null) {
            int length = str2.length();
            if (length != 0) {
                char charAt = str2.charAt(0);
                if (charAt == '*') {
                    if (length == 1) {
                        return formStar();
                    }
                } else if (charAt == '@') {
                    XMLName formProperty = formProperty("", str2.substring(1));
                    formProperty.setAttributeName();
                    return formProperty;
                }
            }
            return formProperty(str, str2);
        }
        throw new IllegalArgumentException();
    }

    static XMLName create(XmlNode.QName qName, boolean z, boolean z2) {
        XMLName xMLName = new XMLName();
        xMLName.qname = qName;
        xMLName.isAttributeName = z;
        xMLName.isDescendants = z2;
        return xMLName;
    }

    static XMLName create(XmlNode.QName qName) {
        return create(qName, false, false);
    }

    /* access modifiers changed from: package-private */
    public void initXMLObject(XMLObjectImpl xMLObjectImpl) {
        if (xMLObjectImpl == null) {
            throw new IllegalArgumentException();
        } else if (this.xmlObject == null) {
            this.xmlObject = xMLObjectImpl;
        } else {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public String uri() {
        if (this.qname.getNamespace() == null) {
            return null;
        }
        return this.qname.getNamespace().getUri();
    }

    /* access modifiers changed from: package-private */
    public String localName() {
        if (this.qname.getLocalName() == null) {
            return "*";
        }
        return this.qname.getLocalName();
    }

    private void addDescendantChildren(XMLList xMLList, XML xml) {
        if (xml.isElement()) {
            XML[] children = xml.getChildren();
            for (int i = 0; i < children.length; i++) {
                if (matches(children[i])) {
                    xMLList.addToList(children[i]);
                }
                addDescendantChildren(xMLList, children[i]);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addMatchingAttributes(XMLList xMLList, XML xml) {
        if (xml.isElement()) {
            XML[] attributes = xml.getAttributes();
            for (int i = 0; i < attributes.length; i++) {
                if (matches(attributes[i])) {
                    xMLList.addToList(attributes[i]);
                }
            }
        }
    }

    private void addDescendantAttributes(XMLList xMLList, XML xml) {
        if (xml.isElement()) {
            addMatchingAttributes(xMLList, xml);
            XML[] children = xml.getChildren();
            for (XML addDescendantAttributes : children) {
                addDescendantAttributes(xMLList, addDescendantAttributes);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public XMLList matchDescendantAttributes(XMLList xMLList, XML xml) {
        xMLList.setTargets(xml, (XmlNode.QName) null);
        addDescendantAttributes(xMLList, xml);
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList matchDescendantChildren(XMLList xMLList, XML xml) {
        xMLList.setTargets(xml, (XmlNode.QName) null);
        addDescendantChildren(xMLList, xml);
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public void addDescendants(XMLList xMLList, XML xml) {
        if (isAttributeName()) {
            matchDescendantAttributes(xMLList, xml);
        } else {
            matchDescendantChildren(xMLList, xml);
        }
    }

    private void addAttributes(XMLList xMLList, XML xml) {
        addMatchingAttributes(xMLList, xml);
    }

    /* access modifiers changed from: package-private */
    public void addMatches(XMLList xMLList, XML xml) {
        if (isDescendants()) {
            addDescendants(xMLList, xml);
        } else if (isAttributeName()) {
            addAttributes(xMLList, xml);
        } else {
            XML[] children = xml.getChildren();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    if (matches(children[i])) {
                        xMLList.addToList(children[i]);
                    }
                }
            }
            xMLList.setTargets(xml, toQname());
        }
    }

    /* access modifiers changed from: package-private */
    public XMLList getMyValueOn(XML xml) {
        XMLList newXMLList = xml.newXMLList();
        addMatches(newXMLList, xml);
        return newXMLList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: org.mozilla.javascript.xmlimpl.XML} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: org.mozilla.javascript.xmlimpl.XMLObjectImpl} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: org.mozilla.javascript.xmlimpl.XMLList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: org.mozilla.javascript.xmlimpl.XML} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: org.mozilla.javascript.xmlimpl.XML} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v12, resolved type: org.mozilla.javascript.xmlimpl.XMLList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v13, resolved type: org.mozilla.javascript.xmlimpl.XML} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v14, resolved type: org.mozilla.javascript.xmlimpl.XML} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v15, resolved type: org.mozilla.javascript.xmlimpl.XML} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v16, resolved type: org.mozilla.javascript.xmlimpl.XML} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMyValueOn(org.mozilla.javascript.xmlimpl.XML r6, java.lang.Object r7) {
        /*
            r5 = this;
            if (r7 != 0) goto L_0x0005
            java.lang.String r7 = "null"
            goto L_0x000b
        L_0x0005:
            boolean r0 = r7 instanceof org.mozilla.javascript.Undefined
            if (r0 == 0) goto L_0x000b
            java.lang.String r7 = "undefined"
        L_0x000b:
            boolean r0 = r5.isAttributeName()
            if (r0 == 0) goto L_0x0016
            r6.setAttribute(r5, r7)
            goto L_0x00a5
        L_0x0016:
            java.lang.String r0 = r5.uri()
            if (r0 != 0) goto L_0x002d
            java.lang.String r0 = r5.localName()
            java.lang.String r1 = "*"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x002d
            r6.setChildren(r7)
            goto L_0x00a5
        L_0x002d:
            boolean r0 = r7 instanceof org.mozilla.javascript.xmlimpl.XMLObjectImpl
            r1 = 0
            if (r0 == 0) goto L_0x006f
            org.mozilla.javascript.xmlimpl.XMLObjectImpl r7 = (org.mozilla.javascript.xmlimpl.XMLObjectImpl) r7
            boolean r0 = r7 instanceof org.mozilla.javascript.xmlimpl.XML
            if (r0 == 0) goto L_0x0049
            r0 = r7
            org.mozilla.javascript.xmlimpl.XML r0 = (org.mozilla.javascript.xmlimpl.XML) r0
            boolean r0 = r0.isAttribute()
            if (r0 == 0) goto L_0x0049
            java.lang.String r7 = r7.toString()
            org.mozilla.javascript.xmlimpl.XML r7 = r6.makeXmlFromString(r5, r7)
        L_0x0049:
            boolean r0 = r7 instanceof org.mozilla.javascript.xmlimpl.XMLList
            if (r0 == 0) goto L_0x0077
            r0 = 0
        L_0x004e:
            int r2 = r7.length()
            if (r0 >= r2) goto L_0x0077
            r2 = r7
            org.mozilla.javascript.xmlimpl.XMLList r2 = (org.mozilla.javascript.xmlimpl.XMLList) r2
            org.mozilla.javascript.xmlimpl.XML r3 = r2.item(r0)
            boolean r4 = r3.isAttribute()
            if (r4 == 0) goto L_0x006c
            java.lang.String r3 = r3.toString()
            org.mozilla.javascript.xmlimpl.XML r3 = r6.makeXmlFromString(r5, r3)
            r2.replace(r0, r3)
        L_0x006c:
            int r0 = r0 + 1
            goto L_0x004e
        L_0x006f:
            java.lang.String r7 = org.mozilla.javascript.ScriptRuntime.toString((java.lang.Object) r7)
            org.mozilla.javascript.xmlimpl.XML r7 = r6.makeXmlFromString(r5, r7)
        L_0x0077:
            org.mozilla.javascript.xmlimpl.XMLList r0 = r6.getPropertyList(r5)
            int r2 = r0.length()
            if (r2 != 0) goto L_0x0085
            r6.appendChild(r7)
            goto L_0x00a5
        L_0x0085:
            r2 = 1
        L_0x0086:
            int r3 = r0.length()
            if (r2 >= r3) goto L_0x009a
            org.mozilla.javascript.xmlimpl.XML r3 = r0.item(r2)
            int r3 = r3.childIndex()
            r6.removeChild(r3)
            int r2 = r2 + 1
            goto L_0x0086
        L_0x009a:
            org.mozilla.javascript.xmlimpl.XML r0 = r0.item(r1)
            int r0 = r0.childIndex()
            r6.replace((int) r0, (java.lang.Object) r7)
        L_0x00a5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLName.setMyValueOn(org.mozilla.javascript.xmlimpl.XML, java.lang.Object):void");
    }

    public boolean has(Context context) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl == null) {
            return false;
        }
        return xMLObjectImpl.hasXMLProperty(this);
    }

    public Object get(Context context) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl != null) {
            return xMLObjectImpl.getXMLProperty(this);
        }
        throw ScriptRuntime.undefReadError(Undefined.instance, toString());
    }

    public Object set(Context context, Object obj) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl == null) {
            throw ScriptRuntime.undefWriteError(Undefined.instance, toString(), obj);
        } else if (!this.isDescendants) {
            xMLObjectImpl.putXMLProperty(this, obj);
            return obj;
        } else {
            throw Kit.codeBug();
        }
    }

    public boolean delete(Context context) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl == null) {
            return true;
        }
        xMLObjectImpl.deleteXMLProperty(this);
        return !this.xmlObject.hasXMLProperty(this);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.isDescendants) {
            stringBuffer.append("..");
        }
        if (this.isAttributeName) {
            stringBuffer.append('@');
        }
        if (uri() == null) {
            stringBuffer.append('*');
            if (localName().equals("*")) {
                return stringBuffer.toString();
            }
        } else {
            stringBuffer.append('\"');
            stringBuffer.append(uri());
            stringBuffer.append('\"');
        }
        stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        stringBuffer.append(localName());
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public final XmlNode.QName toQname() {
        return this.qname;
    }

    /* access modifiers changed from: package-private */
    public final boolean matchesLocalName(String str) {
        return localName().equals("*") || localName().equals(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean matchesElement(XmlNode.QName qName) {
        if (uri() == null || uri().equals(qName.getNamespace().getUri())) {
            return localName().equals("*") || localName().equals(qName.getLocalName());
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final boolean matches(XML xml) {
        XmlNode.QName nodeQname = xml.getNodeQname();
        String uri = nodeQname.getNamespace() != null ? nodeQname.getNamespace().getUri() : null;
        if (!this.isAttributeName) {
            if (uri() == null || (xml.isElement() && uri().equals(uri))) {
                if (localName().equals("*")) {
                    return true;
                }
                return xml.isElement() && localName().equals(nodeQname.getLocalName());
            }
        } else if (xml.isAttribute()) {
            return (uri() == null || uri().equals(uri)) && (localName().equals("*") || localName().equals(nodeQname.getLocalName()));
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isAttributeName() {
        return this.isAttributeName;
    }

    /* access modifiers changed from: package-private */
    public void setAttributeName() {
        this.isAttributeName = true;
    }

    /* access modifiers changed from: package-private */
    public boolean isDescendants() {
        return this.isDescendants;
    }

    /* access modifiers changed from: package-private */
    public void setIsDescendants() {
        this.isDescendants = true;
    }
}

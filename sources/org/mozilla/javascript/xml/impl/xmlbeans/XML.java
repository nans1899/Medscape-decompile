package org.mozilla.javascript.xml.impl.xmlbeans;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.AdError;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;

class XML extends XMLObjectImpl {
    private static final int APPEND_CHILD = 1;
    private static final int PREPEND_CHILD = 2;
    static final long serialVersionUID = -630969919086449092L;
    private XScriptAnnotation _anno;

    public String getClassName() {
        return "XML";
    }

    public boolean has(int i, Scriptable scriptable) {
        return i == 0;
    }

    /* access modifiers changed from: package-private */
    public int length() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public Object valueOf() {
        return this;
    }

    static final class XScriptAnnotation extends XmlCursor.XmlBookmark implements Serializable {
        private static final long serialVersionUID = 1;
        QName _name;
        XML _xScriptXML;

        XScriptAnnotation(XmlCursor xmlCursor) {
            this._name = xmlCursor.getName();
        }
    }

    static final class NamespaceDeclarations {
        private String _defaultNSURI;
        private StringBuffer _namespaceDecls = new StringBuffer();
        private int _prefixIdx = 0;

        NamespaceDeclarations(XmlCursor xmlCursor) {
            XmlCursor.TokenType unused = XML.skipNonElements(xmlCursor);
            this._defaultNSURI = xmlCursor.namespaceForPrefix("");
            if (isAnyDefaultNamespace()) {
                addDecl("", this._defaultNSURI);
            }
        }

        private void addDecl(String str, String str2) {
            String str3;
            StringBuffer stringBuffer = this._namespaceDecls;
            StringBuilder sb = new StringBuilder();
            if (str.length() > 0) {
                str3 = "declare namespace " + str;
            } else {
                str3 = "default element namespace";
            }
            sb.append(str3);
            sb.append(" = \"");
            sb.append(str2);
            sb.append("\"");
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            stringBuffer.append(sb.toString());
        }

        /* access modifiers changed from: package-private */
        public String getNextPrefix(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("NS");
            int i = this._prefixIdx;
            this._prefixIdx = i + 1;
            sb.append(i);
            String sb2 = sb.toString();
            StringBuffer stringBuffer = this._namespaceDecls;
            stringBuffer.append("declare namespace " + sb2 + " = " + "\"" + str + "\"" + IOUtils.LINE_SEPARATOR_UNIX);
            return sb2;
        }

        /* access modifiers changed from: package-private */
        public boolean isAnyDefaultNamespace() {
            String str = this._defaultNSURI;
            return str != null && str.length() > 0;
        }

        /* access modifiers changed from: package-private */
        public String getDeclarations() {
            return this._namespaceDecls.toString();
        }
    }

    private XML(XMLLibImpl xMLLibImpl, XScriptAnnotation xScriptAnnotation) {
        super(xMLLibImpl, xMLLibImpl.xmlPrototype);
        this._anno = xScriptAnnotation;
        xScriptAnnotation._xScriptXML = this;
    }

    /* JADX INFO: finally extract failed */
    static XML createEmptyXML(XMLLibImpl xMLLibImpl) {
        XmlCursor newCursor = XmlObject.Factory.newInstance().newCursor();
        try {
            XScriptAnnotation xScriptAnnotation = new XScriptAnnotation(newCursor);
            newCursor.setBookmark(xScriptAnnotation);
            newCursor.dispose();
            return new XML(xMLLibImpl, xScriptAnnotation);
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    private static XML createXML(XMLLibImpl xMLLibImpl, XmlCursor xmlCursor) {
        if (xmlCursor.currentTokenType().isStartdoc()) {
            xmlCursor.toFirstContentToken();
        }
        return new XML(xMLLibImpl, findAnnotation(xmlCursor));
    }

    private static XML createAttributeXML(XMLLibImpl xMLLibImpl, XmlCursor xmlCursor) {
        if (xmlCursor.isAttr()) {
            XScriptAnnotation xScriptAnnotation = new XScriptAnnotation(xmlCursor);
            xmlCursor.setBookmark(xScriptAnnotation);
            return new XML(xMLLibImpl, xScriptAnnotation);
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: finally extract failed */
    static XML createTextElement(XMLLibImpl xMLLibImpl, QName qName, String str) {
        XmlCursor newCursor = XmlObject.Factory.newInstance().newCursor();
        try {
            newCursor.toNextToken();
            newCursor.beginElement(qName.getLocalPart(), qName.getNamespaceURI());
            newCursor.insertChars(str);
            newCursor.toStartDoc();
            newCursor.toNextToken();
            XScriptAnnotation xScriptAnnotation = new XScriptAnnotation(newCursor);
            newCursor.setBookmark(xScriptAnnotation);
            newCursor.dispose();
            return new XML(xMLLibImpl, xScriptAnnotation);
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    static XML createFromXmlObject(XMLLibImpl xMLLibImpl, XmlObject xmlObject) {
        XmlCursor newCursor = xmlObject.newCursor();
        if (newCursor.currentTokenType().isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        try {
            XScriptAnnotation xScriptAnnotation = new XScriptAnnotation(newCursor);
            newCursor.setBookmark(xScriptAnnotation);
            newCursor.dispose();
            return new XML(xMLLibImpl, xScriptAnnotation);
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    static XML createFromJS(XMLLibImpl xMLLibImpl, Object obj) {
        String str;
        boolean z;
        XmlObject xmlObject;
        boolean z2;
        if (obj == null || obj == Undefined.instance) {
            str = "";
        } else if (obj instanceof XMLObjectImpl) {
            str = ((XMLObjectImpl) obj).toXMLString(0);
        } else {
            if (obj instanceof Wrapper) {
                Object unwrap = ((Wrapper) obj).unwrap();
                if (unwrap instanceof XmlObject) {
                    return createFromXmlObject(xMLLibImpl, (XmlObject) unwrap);
                }
            }
            str = ScriptRuntime.toString(obj);
        }
        if (!str.trim().startsWith("<>")) {
            if (str.indexOf(HtmlObject.HtmlMarkUp.OPEN_BRACKER) == -1) {
                str = "<textFragment>" + str + "</textFragment>";
                z = true;
            } else {
                z = false;
            }
            XmlOptions xmlOptions = new XmlOptions();
            if (xMLLibImpl.ignoreComments) {
                xmlOptions.put("LOAD_STRIP_COMMENTS");
            }
            if (xMLLibImpl.ignoreProcessingInstructions) {
                xmlOptions.put("LOAD_STRIP_PROCINSTS");
            }
            if (xMLLibImpl.ignoreWhitespace) {
                xmlOptions.put("LOAD_STRIP_WHITESPACE");
            }
            try {
                xmlObject = XmlObject.Factory.parse(str, xmlOptions);
                String defaultNamespaceURI = xMLLibImpl.getDefaultNamespaceURI(Context.getCurrentContext());
                if (defaultNamespaceURI.length() > 0) {
                    XmlCursor newCursor = xmlObject.newCursor();
                    boolean z3 = true;
                    while (!newCursor.toNextToken().isEnddoc()) {
                        if (newCursor.isStart()) {
                            newCursor.push();
                            while (true) {
                                if (newCursor.toNextToken().isAnyAttr()) {
                                    if (newCursor.isNamespace() && newCursor.getName().getLocalPart().length() == 0) {
                                        z2 = true;
                                        break;
                                    }
                                } else {
                                    z2 = false;
                                    break;
                                }
                            }
                            newCursor.pop();
                            if (z2) {
                                newCursor.toEndToken();
                            } else {
                                QName name = newCursor.getName();
                                if (name.getNamespaceURI().length() == 0) {
                                    newCursor.setName(new QName(defaultNamespaceURI, name.getLocalPart()));
                                }
                                if (z3) {
                                    newCursor.push();
                                    newCursor.toNextToken();
                                    newCursor.insertNamespace("", defaultNamespaceURI);
                                    newCursor.pop();
                                    z3 = false;
                                }
                            }
                        }
                    }
                    newCursor.dispose();
                }
            } catch (XmlException e) {
                if (e.getMessage().equals("error: Unexpected end of file after null")) {
                    xmlObject = XmlObject.Factory.newInstance();
                } else {
                    throw ScriptRuntime.typeError(e.getMessage());
                }
            } catch (Throwable unused) {
                throw ScriptRuntime.typeError("Not Parsable as XML");
            }
            XmlCursor newCursor2 = xmlObject.newCursor();
            if (newCursor2.currentTokenType().isStartdoc()) {
                newCursor2.toFirstContentToken();
            }
            if (z) {
                newCursor2.toFirstContentToken();
            }
            try {
                XScriptAnnotation xScriptAnnotation = new XScriptAnnotation(newCursor2);
                newCursor2.setBookmark(xScriptAnnotation);
                newCursor2.dispose();
                return new XML(xMLLibImpl, xScriptAnnotation);
            } catch (Throwable th) {
                newCursor2.dispose();
                throw th;
            }
        } else {
            throw ScriptRuntime.typeError("Invalid use of XML object anonymous tags <></>.");
        }
    }

    static XML getFromAnnotation(XMLLibImpl xMLLibImpl, XScriptAnnotation xScriptAnnotation) {
        if (xScriptAnnotation._xScriptXML == null) {
            xScriptAnnotation._xScriptXML = new XML(xMLLibImpl, xScriptAnnotation);
        }
        return xScriptAnnotation._xScriptXML;
    }

    /* access modifiers changed from: private */
    public static XmlCursor.TokenType skipNonElements(XmlCursor xmlCursor) {
        XmlCursor.TokenType currentTokenType = xmlCursor.currentTokenType();
        while (true) {
            if (!currentTokenType.isComment() && !currentTokenType.isProcinst()) {
                return currentTokenType;
            }
            currentTokenType = xmlCursor.toNextToken();
        }
    }

    protected static XScriptAnnotation findAnnotation(XmlCursor xmlCursor) {
        XScriptAnnotation bookmark = xmlCursor.getBookmark(XScriptAnnotation.class);
        if (bookmark == null) {
            bookmark = new XScriptAnnotation(xmlCursor);
            xmlCursor.setBookmark(bookmark);
        }
        return bookmark;
    }

    private XmlOptions getOptions() {
        XmlOptions xmlOptions = new XmlOptions();
        if (this.lib.ignoreComments) {
            xmlOptions.put("LOAD_STRIP_COMMENTS");
        }
        if (this.lib.ignoreProcessingInstructions) {
            xmlOptions.put("LOAD_STRIP_PROCINSTS");
        }
        if (this.lib.ignoreWhitespace) {
            xmlOptions.put("LOAD_STRIP_WHITESPACE");
        }
        if (this.lib.prettyPrinting) {
            xmlOptions.put("SAVE_PRETTY_PRINT", (Object) null);
            xmlOptions.put("SAVE_PRETTY_PRINT_INDENT", new Integer(this.lib.prettyIndent));
        }
        return xmlOptions;
    }

    private static String dumpNode(XmlCursor xmlCursor, XmlOptions xmlOptions) {
        if (xmlCursor.isText()) {
            return xmlCursor.getChars();
        }
        if (xmlCursor.isFinish()) {
            return "";
        }
        xmlCursor.push();
        boolean z = xmlCursor.isStartdoc() && !xmlCursor.toFirstChild();
        xmlCursor.pop();
        return z ? xmlCursor.getTextValue() : xmlCursor.xmlText(xmlOptions);
    }

    private XmlCursor newCursor() {
        XScriptAnnotation xScriptAnnotation = this._anno;
        if (xScriptAnnotation == null) {
            return XmlObject.Factory.newInstance().newCursor();
        }
        XmlCursor createCursor = xScriptAnnotation.createCursor();
        if (createCursor != null) {
            return createCursor;
        }
        XmlCursor newCursor = XmlObject.Factory.newInstance().newCursor();
        if (this._anno._name != null) {
            newCursor.toNextToken();
            newCursor.insertElement(this._anno._name);
            newCursor.toPrevSibling();
        }
        newCursor.setBookmark(this._anno);
        return newCursor;
    }

    private boolean moveToChild(XmlCursor xmlCursor, long j, boolean z, boolean z2) {
        long j2 = 0;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i >= 0) {
            if (!z2 && xmlCursor.currentTokenType().isStartdoc()) {
                xmlCursor.toFirstContentToken();
            }
            XmlCursor.TokenType firstContentToken = xmlCursor.toFirstContentToken();
            if (!firstContentToken.isNone() && !firstContentToken.isEnd()) {
                while (j != j2) {
                    XmlCursor.TokenType currentTokenType = xmlCursor.currentTokenType();
                    if (currentTokenType.isText()) {
                        xmlCursor.toNextToken();
                    } else if (currentTokenType.isStart()) {
                        xmlCursor.toEndToken();
                        xmlCursor.toNextToken();
                    } else if (!currentTokenType.isComment() && !currentTokenType.isProcinst()) {
                        return false;
                    }
                    j2++;
                }
                return true;
            } else if (!z || i != 0) {
                return false;
            } else {
                return true;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: package-private */
    public XmlCursor.TokenType tokenType() {
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        XmlCursor.TokenType currentTokenType = newCursor.currentTokenType();
        newCursor.dispose();
        return currentTokenType;
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean moveSrcToDest(org.apache.xmlbeans.XmlCursor r3, org.apache.xmlbeans.XmlCursor r4, boolean r5) {
        /*
            r2 = this;
        L_0x0000:
            if (r5 == 0) goto L_0x0010
            boolean r0 = r3.isInSameDocument(r4)
            if (r0 == 0) goto L_0x0010
            int r0 = r3.comparePosition(r4)
            if (r0 != 0) goto L_0x0010
            r3 = 0
            goto L_0x003e
        L_0x0010:
            org.apache.xmlbeans.XmlCursor$TokenType r0 = r4.currentTokenType()
            boolean r0 = r0.isStartdoc()
            if (r0 == 0) goto L_0x001d
            r4.toNextToken()
        L_0x001d:
            org.apache.xmlbeans.XmlCursor r0 = r2.copy(r3)
            r0.moveXml(r4)
            r0.dispose()
            org.apache.xmlbeans.XmlCursor$TokenType r0 = r3.currentTokenType()
            boolean r1 = r0.isStart()
            if (r1 != 0) goto L_0x003d
            boolean r1 = r0.isEnd()
            if (r1 != 0) goto L_0x003d
            boolean r0 = r0.isEnddoc()
            if (r0 == 0) goto L_0x0000
        L_0x003d:
            r3 = 1
        L_0x003e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XML.moveSrcToDest(org.apache.xmlbeans.XmlCursor, org.apache.xmlbeans.XmlCursor, boolean):boolean");
    }

    private XmlCursor copy(XmlCursor xmlCursor) {
        XmlCursor xmlCursor2;
        XmlObject newInstance = XmlObject.Factory.newInstance();
        if (xmlCursor.currentTokenType().isText()) {
            try {
                xmlCursor2 = XmlObject.Factory.parse("<x:fragment xmlns:x=\"http://www.openuri.org/fragment\">" + xmlCursor.getChars() + "</x:fragment>").newCursor();
                if (!xmlCursor.toNextSibling() && xmlCursor.currentTokenType().isText()) {
                    xmlCursor.toNextToken();
                }
            } catch (Exception e) {
                throw ScriptRuntime.typeError(e.getMessage());
            }
        } else {
            xmlCursor2 = newInstance.newCursor();
            xmlCursor2.toFirstContentToken();
            if (xmlCursor.currentTokenType() == XmlCursor.TokenType.STARTDOC) {
                xmlCursor.toNextToken();
            }
            xmlCursor.copyXml(xmlCursor2);
            if (!xmlCursor.toNextSibling() && xmlCursor.currentTokenType().isText()) {
                xmlCursor.toNextToken();
            }
        }
        xmlCursor2.toStartDoc();
        xmlCursor2.toFirstContentToken();
        return xmlCursor2;
    }

    private void insertChild(XmlCursor xmlCursor, Object obj) {
        if (obj != null && !(obj instanceof Undefined)) {
            if (obj instanceof XmlCursor) {
                moveSrcToDest((XmlCursor) obj, xmlCursor, true);
            } else if (obj instanceof XML) {
                XML xml = (XML) obj;
                if (xml.tokenType() == XmlCursor.TokenType.ATTR) {
                    insertChild(xmlCursor, xml.toString());
                    return;
                }
                XmlCursor newCursor = xml.newCursor();
                moveSrcToDest(newCursor, xmlCursor, true);
                newCursor.dispose();
            } else if (obj instanceof XMLList) {
                XMLList xMLList = (XMLList) obj;
                for (int i = 0; i < xMLList.length(); i++) {
                    insertChild(xmlCursor, xMLList.item(i));
                }
            } else {
                String scriptRuntime = ScriptRuntime.toString(obj);
                XmlCursor newCursor2 = XmlObject.Factory.newInstance().newCursor();
                newCursor2.toNextToken();
                newCursor2.insertChars(scriptRuntime);
                newCursor2.toPrevToken();
                moveSrcToDest(newCursor2, xmlCursor, true);
            }
        }
    }

    private void insertChild(XML xml, Object obj, int i) {
        XmlCursor newCursor = newCursor();
        XmlCursor.TokenType currentTokenType = newCursor.currentTokenType();
        XmlCursor newCursor2 = xml.newCursor();
        if (currentTokenType.isStartdoc()) {
            currentTokenType = newCursor.toFirstContentToken();
        }
        if (currentTokenType.isContainer()) {
            XmlCursor.TokenType nextToken = newCursor.toNextToken();
            while (true) {
                if (nextToken.isEnd()) {
                    break;
                } else if (!nextToken.isStart() || newCursor.comparePosition(newCursor2) != 0) {
                    if (nextToken.isStart()) {
                        newCursor.toEndToken();
                    }
                    nextToken = newCursor.toNextToken();
                } else {
                    if (i == 1) {
                        newCursor.toEndToken();
                        newCursor.toNextToken();
                    }
                    insertChild(newCursor, obj);
                }
            }
        }
        newCursor2.dispose();
        newCursor.dispose();
    }

    /* access modifiers changed from: protected */
    public void removeToken(XmlCursor xmlCursor) {
        XmlCursor newCursor = XmlObject.Factory.newInstance().newCursor();
        newCursor.toFirstContentToken();
        xmlCursor.moveXml(newCursor);
        newCursor.dispose();
    }

    /* access modifiers changed from: protected */
    public void removeChild(long j) {
        XmlCursor newCursor = newCursor();
        if (moveToChild(newCursor, j, false, false)) {
            removeToken(newCursor);
        }
        newCursor.dispose();
    }

    protected static QName computeQName(Object obj) {
        int indexOf;
        String str = null;
        if (!(obj instanceof String)) {
            return null;
        }
        String str2 = (String) obj;
        if (str2.startsWith("\"") && (indexOf = str2.indexOf(":")) != -1) {
            str = str2.substring(1, indexOf - 1);
            str2 = str2.substring(indexOf + 1);
        }
        if (str == null) {
            return new QName(str2);
        }
        return new QName(str, str2);
    }

    private void replace(XmlCursor xmlCursor, XML xml) {
        if (xmlCursor.isStartdoc()) {
            xmlCursor.toFirstContentToken();
        }
        removeToken(xmlCursor);
        XmlCursor newCursor = xml.newCursor();
        if (newCursor.currentTokenType().isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        moveSrcToDest(newCursor, xmlCursor, false);
        if (!xmlCursor.toPrevSibling()) {
            xmlCursor.toPrevToken();
        }
        xmlCursor.setBookmark(new XScriptAnnotation(xmlCursor));
        xmlCursor.toEndToken();
        xmlCursor.toNextToken();
        newCursor.dispose();
    }

    private boolean doPut(XMLName xMLName, XML xml, XMLObjectImpl xMLObjectImpl) {
        XML xml2;
        XmlCursor newCursor = xml.newCursor();
        try {
            int length = xMLObjectImpl.length();
            for (int i = 0; i < length; i++) {
                if (xMLObjectImpl instanceof XMLList) {
                    xml2 = ((XMLList) xMLObjectImpl).item(i);
                } else {
                    xml2 = (XML) xMLObjectImpl;
                }
                XmlCursor.TokenType tokenType = xml2.tokenType();
                if (tokenType == XmlCursor.TokenType.ATTR || tokenType == XmlCursor.TokenType.TEXT) {
                    xml2 = makeXmlFromString(this.lib, xMLName, xml2.toString());
                }
                if (i == 0) {
                    replace(newCursor, xml2);
                } else {
                    insertChild(newCursor, xml2);
                }
            }
            newCursor.dispose();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw ScriptRuntime.typeError(e.getMessage());
        } catch (Throwable th) {
            newCursor.dispose();
            throw th;
        }
    }

    private XML makeXmlFromString(XMLLibImpl xMLLibImpl, XMLName xMLName, String str) {
        try {
            return createTextElement(xMLLibImpl, new QName(xMLName.uri(), xMLName.localName()), str);
        } catch (Exception e) {
            throw ScriptRuntime.typeError(e.getMessage());
        }
    }

    private XMLList matchAttributes(XMLName xMLName) {
        XMLList xMLList = new XMLList(this.lib);
        XmlCursor newCursor = newCursor();
        if (newCursor.currentTokenType().isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        if (!newCursor.isStart() || !newCursor.toFirstAttribute()) {
            newCursor.dispose();
            return xMLList;
        }
        do {
            if (qnameMatches(xMLName, newCursor.getName())) {
                xMLList.addToList(createAttributeObject(newCursor));
            }
        } while (newCursor.toNextAttribute());
        newCursor.dispose();
        return xMLList;
    }

    private XML createAttributeObject(XmlCursor xmlCursor) {
        if (xmlCursor.currentTokenType().isAttr()) {
            return createAttributeXML(this.lib, xmlCursor);
        }
        return null;
    }

    public Object get(int i, Scriptable scriptable) {
        return i == 0 ? this : Scriptable.NOT_FOUND;
    }

    /* access modifiers changed from: package-private */
    public boolean hasXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName).length() > 0;
    }

    public Object[] getIds() {
        if (this.prototypeFlag) {
            return new Object[0];
        }
        return new Object[]{new Integer(0)};
    }

    public Object[] getIdsForDebug() {
        return getIds();
    }

    /* access modifiers changed from: package-private */
    public Object getXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName);
    }

    /* access modifiers changed from: package-private */
    public void putXMLProperty(XMLName xMLName, Object obj) {
        XMLObjectImpl xMLObjectImpl;
        if (!this.prototypeFlag) {
            if (obj == null) {
                obj = "null";
            } else if (obj instanceof Undefined) {
                obj = AdError.UNDEFINED_DOMAIN;
            }
            if (xMLName.isAttributeName()) {
                setAttribute(xMLName, obj);
            } else if (xMLName.uri() != null || !xMLName.localName().equals("*")) {
                if (obj instanceof XMLObjectImpl) {
                    xMLObjectImpl = (XMLObjectImpl) obj;
                    if ((xMLObjectImpl instanceof XML) && ((XML) xMLObjectImpl).tokenType() == XmlCursor.TokenType.ATTR) {
                        xMLObjectImpl = makeXmlFromString(this.lib, xMLName, xMLObjectImpl.toString());
                    }
                    if (xMLObjectImpl instanceof XMLList) {
                        for (int i = 0; i < xMLObjectImpl.length(); i++) {
                            XMLList xMLList = (XMLList) xMLObjectImpl;
                            XML item = xMLList.item(i);
                            if (item.tokenType() == XmlCursor.TokenType.ATTR) {
                                xMLList.replace(i, makeXmlFromString(this.lib, xMLName, item.toString()));
                            }
                        }
                    }
                } else {
                    xMLObjectImpl = makeXmlFromString(this.lib, xMLName, ScriptRuntime.toString(obj));
                }
                XMLList propertyList = getPropertyList(xMLName);
                if (propertyList.length() == 0) {
                    appendChild(xMLObjectImpl);
                    return;
                }
                for (int i2 = 1; i2 < propertyList.length(); i2++) {
                    removeChild((long) propertyList.item(i2).childIndex());
                }
                doPut(xMLName, propertyList.item(0), xMLObjectImpl);
            } else {
                setChildren(obj);
            }
        }
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        throw ScriptRuntime.typeError("Assignment to indexed XML is not allowed");
    }

    /* access modifiers changed from: package-private */
    public void deleteXMLProperty(XMLName xMLName) {
        if (xMLName.isDescendants() || !xMLName.isAttributeName()) {
            getPropertyList(xMLName).remove();
            return;
        }
        XmlCursor newCursor = newCursor();
        if (!xMLName.localName().equals("*")) {
            newCursor.removeAttribute(new QName(xMLName.uri(), xMLName.localName()));
        } else if (newCursor.toFirstAttribute()) {
            while (newCursor.currentTokenType().isAttr()) {
                newCursor.removeXml();
            }
        }
        newCursor.dispose();
    }

    public void delete(int i) {
        if (i == 0) {
            remove();
        }
    }

    /* access modifiers changed from: protected */
    public XScriptAnnotation getAnnotation() {
        return this._anno;
    }

    /* access modifiers changed from: protected */
    public void changeNS(String str, String str2) {
        XmlCursor newCursor = newCursor();
        do {
        } while (newCursor.toParent());
        XmlCursor.TokenType currentTokenType = newCursor.currentTokenType();
        if (currentTokenType.isStartdoc()) {
            currentTokenType = newCursor.toFirstContentToken();
        }
        if (currentTokenType.isStart()) {
            do {
                if (currentTokenType.isStart() || currentTokenType.isAttr() || currentTokenType.isNamespace()) {
                    QName name = newCursor.getName();
                    if (str.equals(name.getNamespaceURI())) {
                        newCursor.setName(new QName(str2, name.getLocalPart()));
                    }
                }
                currentTokenType = newCursor.toNextToken();
                if (currentTokenType.isEnddoc()) {
                    break;
                }
            } while (currentTokenType.isNone());
        }
        newCursor.dispose();
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        XmlCursor newCursor = newCursor();
        if (newCursor.currentTokenType().isStartdoc()) {
            XmlCursor.TokenType firstContentToken = newCursor.toFirstContentToken();
            while (!firstContentToken.isEnd() && !firstContentToken.isEnddoc()) {
                removeToken(newCursor);
                firstContentToken = newCursor.currentTokenType();
            }
        } else {
            removeToken(newCursor);
        }
        newCursor.dispose();
    }

    /* access modifiers changed from: package-private */
    public void replaceAll(XML xml) {
        XmlCursor newCursor = newCursor();
        replace(newCursor, xml);
        this._anno = xml._anno;
        newCursor.dispose();
    }

    /* access modifiers changed from: package-private */
    public void setAttribute(XMLName xMLName, Object obj) {
        if (xMLName.uri() != null || !xMLName.localName().equals("*")) {
            XmlCursor newCursor = newCursor();
            String scriptRuntime = ScriptRuntime.toString(obj);
            if (newCursor.currentTokenType().isStartdoc()) {
                newCursor.toFirstContentToken();
            }
            try {
                QName qName = new QName(xMLName.uri(), xMLName.localName());
                if (!newCursor.setAttributeText(qName, scriptRuntime)) {
                    if (newCursor.currentTokenType().isStart()) {
                        newCursor.toNextToken();
                    }
                    newCursor.insertAttributeWithValue(qName, scriptRuntime);
                }
                newCursor.dispose();
            } catch (Exception e) {
                throw ScriptRuntime.typeError(e.getMessage());
            }
        } else {
            throw ScriptRuntime.typeError("@* assignment not supported.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0084, code lost:
        if (r3.getLocalPart().equals(r1.getName().getLocalPart()) == false) goto L_0x003e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0090 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.xml.impl.xmlbeans.XMLList allChildNodes(java.lang.String r9) {
        /*
            r8 = this;
            org.mozilla.javascript.xml.impl.xmlbeans.XMLList r0 = new org.mozilla.javascript.xml.impl.xmlbeans.XMLList
            org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl r1 = r8.lib
            r0.<init>(r1)
            org.apache.xmlbeans.XmlCursor r1 = r8.newCursor()
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.currentTokenType()
            javax.xml.namespace.QName r3 = new javax.xml.namespace.QName
            java.lang.String r4 = "*"
            r3.<init>(r9, r4)
            boolean r5 = r2.isStartdoc()
            if (r5 == 0) goto L_0x0020
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.toFirstContentToken()
        L_0x0020:
            boolean r2 = r2.isContainer()
            if (r2 == 0) goto L_0x0095
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.toFirstContentToken()
        L_0x002a:
            boolean r5 = r2.isEnd()
            if (r5 != 0) goto L_0x0095
            boolean r5 = r2.isStart()
            r6 = 0
            if (r5 != 0) goto L_0x0040
            org.mozilla.javascript.xml.impl.xmlbeans.XML$XScriptAnnotation r3 = findAnnotation(r1)
            r0.addToList(r3)
        L_0x003e:
            r3 = r6
            goto L_0x0087
        L_0x0040:
            if (r9 == 0) goto L_0x005c
            int r5 = r9.length()
            if (r5 == 0) goto L_0x005c
            boolean r5 = r9.equals(r4)
            if (r5 != 0) goto L_0x005c
            javax.xml.namespace.QName r5 = r1.getName()
            java.lang.String r5 = r5.getNamespaceURI()
            boolean r5 = r5.equals(r9)
            if (r5 == 0) goto L_0x0087
        L_0x005c:
            org.mozilla.javascript.xml.impl.xmlbeans.XML$XScriptAnnotation r5 = findAnnotation(r1)
            r0.addToList(r5)
            if (r3 == 0) goto L_0x0087
            java.lang.String r5 = r3.getLocalPart()
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L_0x0074
            javax.xml.namespace.QName r3 = r1.getName()
            goto L_0x0087
        L_0x0074:
            java.lang.String r5 = r3.getLocalPart()
            javax.xml.namespace.QName r7 = r1.getName()
            java.lang.String r7 = r7.getLocalPart()
            boolean r5 = r5.equals(r7)
            if (r5 != 0) goto L_0x0087
            goto L_0x003e
        L_0x0087:
            boolean r2 = r2.isStart()
            if (r2 == 0) goto L_0x0090
            r1.toEndToken()
        L_0x0090:
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.toNextToken()
            goto L_0x002a
        L_0x0095:
            r1.dispose()
            r0.setTargets(r8, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XML.allChildNodes(java.lang.String):org.mozilla.javascript.xml.impl.xmlbeans.XMLList");
    }

    private XMLList matchDescendantAttributes(XMLName xMLName) {
        XMLList xMLList = new XMLList(this.lib);
        XmlCursor newCursor = newCursor();
        XmlCursor.TokenType currentTokenType = newCursor.currentTokenType();
        xMLList.setTargets(this, (QName) null);
        if (currentTokenType.isStartdoc()) {
            currentTokenType = newCursor.toFirstContentToken();
        }
        if (currentTokenType.isContainer()) {
            int i = 1;
            while (i > 0) {
                XmlCursor.TokenType nextToken = newCursor.toNextToken();
                if (nextToken.isAttr() && qnameMatches(xMLName, newCursor.getName())) {
                    xMLList.addToList(findAnnotation(newCursor));
                }
                if (nextToken.isStart()) {
                    i++;
                } else if (nextToken.isEnd()) {
                    i--;
                } else if (nextToken.isEnddoc()) {
                    break;
                }
            }
        }
        newCursor.dispose();
        return xMLList;
    }

    private XMLList matchDescendantChildren(XMLName xMLName) {
        XMLList xMLList = new XMLList(this.lib);
        XmlCursor newCursor = newCursor();
        XmlCursor.TokenType currentTokenType = newCursor.currentTokenType();
        xMLList.setTargets(this, (QName) null);
        if (currentTokenType.isStartdoc()) {
            currentTokenType = newCursor.toFirstContentToken();
        }
        if (currentTokenType.isContainer()) {
            int i = 1;
            while (i > 0) {
                XmlCursor.TokenType nextToken = newCursor.toNextToken();
                if (!nextToken.isAttr() && !nextToken.isEnd() && !nextToken.isEnddoc()) {
                    if (nextToken.isStart() || nextToken.isProcinst()) {
                        if (qnameMatches(xMLName, newCursor.getName())) {
                            xMLList.addToList(findAnnotation(newCursor));
                        }
                    } else if (xMLName.localName().equals("*")) {
                        xMLList.addToList(findAnnotation(newCursor));
                    }
                }
                if (nextToken.isStart()) {
                    i++;
                } else if (nextToken.isEnd()) {
                    i--;
                } else if (nextToken.isEnddoc()) {
                    break;
                }
            }
        }
        newCursor.dispose();
        return xMLList;
    }

    private XMLList matchChildren(XmlCursor.TokenType tokenType) {
        return matchChildren(tokenType, XMLName.formStar());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0082, code lost:
        if (r3.getLocalPart().equals(r1.getName().getLocalPart()) == false) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.xml.impl.xmlbeans.XMLList matchChildren(org.apache.xmlbeans.XmlCursor.TokenType r8, org.mozilla.javascript.xml.impl.xmlbeans.XMLName r9) {
        /*
            r7 = this;
            org.mozilla.javascript.xml.impl.xmlbeans.XMLList r0 = new org.mozilla.javascript.xml.impl.xmlbeans.XMLList
            org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl r1 = r7.lib
            r0.<init>(r1)
            org.apache.xmlbeans.XmlCursor r1 = r7.newCursor()
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.currentTokenType()
            javax.xml.namespace.QName r3 = new javax.xml.namespace.QName
            java.lang.String r4 = r9.uri()
            java.lang.String r5 = r9.localName()
            r3.<init>(r4, r5)
            boolean r4 = r2.isStartdoc()
            if (r4 == 0) goto L_0x0026
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.toFirstContentToken()
        L_0x0026:
            boolean r2 = r2.isContainer()
            if (r2 == 0) goto L_0x0093
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.toFirstContentToken()
        L_0x0030:
            boolean r4 = r2.isEnd()
            if (r4 != 0) goto L_0x0093
            r4 = 0
            if (r2 != r8) goto L_0x0085
            boolean r5 = r2.isStart()
            if (r5 != 0) goto L_0x004e
            boolean r5 = r2.isProcinst()
            if (r5 != 0) goto L_0x004e
            org.mozilla.javascript.xml.impl.xmlbeans.XML$XScriptAnnotation r3 = findAnnotation(r1)
            r0.addToList(r3)
        L_0x004c:
            r3 = r4
            goto L_0x0085
        L_0x004e:
            javax.xml.namespace.QName r5 = r1.getName()
            boolean r5 = r7.qnameMatches(r9, r5)
            if (r5 == 0) goto L_0x0085
            org.mozilla.javascript.xml.impl.xmlbeans.XML$XScriptAnnotation r5 = findAnnotation(r1)
            r0.addToList(r5)
            if (r3 == 0) goto L_0x0085
            java.lang.String r5 = r3.getLocalPart()
            java.lang.String r6 = "*"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0072
            javax.xml.namespace.QName r3 = r1.getName()
            goto L_0x0085
        L_0x0072:
            java.lang.String r5 = r3.getLocalPart()
            javax.xml.namespace.QName r6 = r1.getName()
            java.lang.String r6 = r6.getLocalPart()
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x0085
            goto L_0x004c
        L_0x0085:
            boolean r2 = r2.isStart()
            if (r2 == 0) goto L_0x008e
            r1.toEndToken()
        L_0x008e:
            org.apache.xmlbeans.XmlCursor$TokenType r2 = r1.toNextToken()
            goto L_0x0030
        L_0x0093:
            r1.dispose()
            org.apache.xmlbeans.XmlCursor$TokenType r9 = org.apache.xmlbeans.XmlCursor.TokenType.START
            if (r8 != r9) goto L_0x009d
            r0.setTargets(r7, r3)
        L_0x009d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XML.matchChildren(org.apache.xmlbeans.XmlCursor$TokenType, org.mozilla.javascript.xml.impl.xmlbeans.XMLName):org.mozilla.javascript.xml.impl.xmlbeans.XMLList");
    }

    private boolean qnameMatches(XMLName xMLName, QName qName) {
        return (xMLName.uri() == null || xMLName.uri().equals(qName.getNamespaceURI())) && (xMLName.localName().equals("*") || xMLName.localName().equals(qName.getLocalPart()));
    }

    /* access modifiers changed from: package-private */
    public XML addNamespace(Namespace namespace) {
        String prefix = namespace.prefix();
        if (prefix == null) {
            return this;
        }
        XmlCursor newCursor = newCursor();
        try {
            if (!newCursor.isContainer()) {
                return this;
            }
            if (!newCursor.getName().getNamespaceURI().equals("") || !prefix.equals("")) {
                String str = (String) NamespaceHelper.getAllNamespaces(this.lib, newCursor).get(prefix);
                if (str != null) {
                    if (str.equals(namespace.uri())) {
                        newCursor.dispose();
                        return this;
                    }
                    newCursor.push();
                    while (true) {
                        if (newCursor.toNextToken().isAnyAttr()) {
                            if (newCursor.isNamespace() && newCursor.getName().getLocalPart().equals(prefix)) {
                                newCursor.removeXml();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    newCursor.pop();
                }
                newCursor.toNextToken();
                newCursor.insertNamespace(prefix, namespace.uri());
                newCursor.dispose();
                return this;
            }
            newCursor.dispose();
            return this;
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public XML appendChild(Object obj) {
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        if (newCursor.isStart()) {
            newCursor.toEndToken();
        }
        insertChild(newCursor, obj);
        newCursor.dispose();
        return this;
    }

    /* access modifiers changed from: package-private */
    public XMLList attribute(XMLName xMLName) {
        return matchAttributes(xMLName);
    }

    /* access modifiers changed from: package-private */
    public XMLList attributes() {
        return matchAttributes(XMLName.formStar());
    }

    /* access modifiers changed from: package-private */
    public XMLList child(long j) {
        XMLList xMLList = new XMLList(this.lib);
        xMLList.setTargets(this, (QName) null);
        xMLList.addToList(getXmlChild(j));
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList child(XMLName xMLName) {
        if (xMLName == null) {
            return new XMLList(this.lib);
        }
        if (xMLName.localName().equals("*")) {
            return allChildNodes(xMLName.uri());
        }
        return matchChildren(XmlCursor.TokenType.START, xMLName);
    }

    /* access modifiers changed from: package-private */
    public XML getXmlChild(long j) {
        XmlCursor newCursor = newCursor();
        XML createXML = moveToChild(newCursor, j, false, true) ? createXML(this.lib, newCursor) : null;
        newCursor.dispose();
        return createXML;
    }

    /* access modifiers changed from: package-private */
    public int childIndex() {
        XmlCursor newCursor = newCursor();
        XmlCursor.TokenType currentTokenType = newCursor.currentTokenType();
        int i = 0;
        while (true) {
            if (!currentTokenType.isText()) {
                if (!currentTokenType.isStart()) {
                    if (!currentTokenType.isComment() && !currentTokenType.isProcinst()) {
                        break;
                    }
                    newCursor.toPrevToken();
                    currentTokenType = newCursor.currentTokenType();
                } else if (!newCursor.toPrevToken().isEnd()) {
                    break;
                } else {
                    newCursor.toNextToken();
                    if (!newCursor.toPrevSibling()) {
                        break;
                    }
                    i++;
                    currentTokenType = newCursor.currentTokenType();
                }
            } else {
                i++;
                if (!newCursor.toPrevSibling()) {
                    break;
                }
                currentTokenType = newCursor.currentTokenType();
            }
        }
        if (newCursor.currentTokenType().isStartdoc()) {
            i = -1;
        }
        newCursor.dispose();
        return i;
    }

    /* access modifiers changed from: package-private */
    public XMLList children() {
        return allChildNodes((String) null);
    }

    /* access modifiers changed from: package-private */
    public XMLList comments() {
        return matchChildren(XmlCursor.TokenType.COMMENT);
    }

    /* access modifiers changed from: package-private */
    public boolean contains(Object obj) {
        if (obj instanceof XML) {
            return equivalentXml(obj);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public Object copy() {
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        XML createEmptyXML = createEmptyXML(this.lib);
        XmlCursor newCursor2 = createEmptyXML.newCursor();
        newCursor2.toFirstContentToken();
        newCursor.copyXml(newCursor2);
        newCursor2.dispose();
        newCursor.dispose();
        return createEmptyXML;
    }

    /* access modifiers changed from: package-private */
    public XMLList descendants(XMLName xMLName) {
        if (xMLName.isAttributeName()) {
            return matchDescendantAttributes(xMLName);
        }
        return matchDescendantChildren(xMLName);
    }

    /* access modifiers changed from: package-private */
    public Object[] inScopeNamespaces() {
        XmlCursor newCursor = newCursor();
        Object[] inScopeNamespaces = NamespaceHelper.inScopeNamespaces(this.lib, newCursor);
        newCursor.dispose();
        return inScopeNamespaces;
    }

    /* access modifiers changed from: package-private */
    public XML insertChildAfter(Object obj, Object obj2) {
        if (obj == null) {
            prependChild(obj2);
        } else if (obj instanceof XML) {
            insertChild((XML) obj, obj2, 1);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML insertChildBefore(Object obj, Object obj2) {
        if (obj == null) {
            appendChild(obj2);
        } else if (obj instanceof XML) {
            insertChild((XML) obj, obj2, 2);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean hasOwnProperty(XMLName xMLName) {
        if (this.prototypeFlag) {
            if (findPrototypeId(xMLName.localName()) != 0) {
                return true;
            }
        } else if (getPropertyList(xMLName).length() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasComplexContent() {
        return !hasSimpleContent();
    }

    /* access modifiers changed from: package-private */
    public boolean hasSimpleContent() {
        XmlCursor newCursor = newCursor();
        if (newCursor.isAttr() || newCursor.isText()) {
            return true;
        }
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        boolean z = !newCursor.toFirstChild();
        newCursor.dispose();
        return z;
    }

    /* access modifiers changed from: package-private */
    public String localName() {
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        String str = null;
        if (newCursor.isStart() || newCursor.isAttr() || newCursor.isProcinst()) {
            str = newCursor.getName().getLocalPart();
        }
        newCursor.dispose();
        return str;
    }

    /* access modifiers changed from: package-private */
    public QName name() {
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        QName qName = null;
        if (newCursor.isStart() || newCursor.isAttr() || newCursor.isProcinst()) {
            QName name = newCursor.getName();
            if (newCursor.isProcinst()) {
                qName = new QName(this.lib, "", name.getLocalPart(), "");
            } else {
                qName = new QName(this.lib, name.getNamespaceURI(), name.getLocalPart(), name.getPrefix());
            }
        }
        newCursor.dispose();
        return qName;
    }

    /* access modifiers changed from: package-private */
    public Object namespace(String str) {
        Object obj;
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        Namespace namespace = null;
        if (str == null) {
            if (newCursor.isStart() || newCursor.isAttr()) {
                Object[] inScopeNamespaces = NamespaceHelper.inScopeNamespaces(this.lib, newCursor);
                XmlCursor newCursor2 = newCursor();
                if (newCursor2.isStartdoc()) {
                    newCursor2.toFirstContentToken();
                }
                obj = NamespaceHelper.getNamespace(this.lib, newCursor2, inScopeNamespaces);
                newCursor2.dispose();
            }
            newCursor.dispose();
            return namespace;
        }
        String str2 = (String) NamespaceHelper.getAllNamespaces(this.lib, newCursor).get(str);
        if (str2 == null) {
            obj = Undefined.instance;
        } else {
            namespace = new Namespace(this.lib, str, str2);
            newCursor.dispose();
            return namespace;
        }
        namespace = obj;
        newCursor.dispose();
        return namespace;
    }

    /* access modifiers changed from: package-private */
    public Object[] namespaceDeclarations() {
        XmlCursor newCursor = newCursor();
        Object[] namespaceDeclarations = NamespaceHelper.namespaceDeclarations(this.lib, newCursor);
        newCursor.dispose();
        return namespaceDeclarations;
    }

    /* access modifiers changed from: package-private */
    public Object nodeKind() {
        XmlCursor.TokenType tokenType = tokenType();
        if (tokenType == XmlCursor.TokenType.ATTR) {
            return "attribute";
        }
        if (tokenType == XmlCursor.TokenType.TEXT) {
            return "text";
        }
        if (tokenType == XmlCursor.TokenType.COMMENT) {
            return "comment";
        }
        if (tokenType == XmlCursor.TokenType.PROCINST) {
            return "processing-instruction";
        }
        if (tokenType == XmlCursor.TokenType.START) {
            return "element";
        }
        return "text";
    }

    /* access modifiers changed from: package-private */
    public void normalize() {
        XmlCursor newCursor = newCursor();
        XmlCursor.TokenType currentTokenType = newCursor.currentTokenType();
        if (currentTokenType.isStartdoc()) {
            currentTokenType = newCursor.toFirstContentToken();
        }
        if (currentTokenType.isContainer()) {
            int i = 1;
            String str = null;
            while (i > 0) {
                XmlCursor.TokenType nextToken = newCursor.toNextToken();
                if (nextToken == XmlCursor.TokenType.TEXT) {
                    String trim = newCursor.getChars().trim();
                    if (trim.trim().length() == 0) {
                        removeToken(newCursor);
                        newCursor.toPrevToken();
                    } else if (str == null) {
                        str = trim;
                    } else {
                        newCursor.toPrevToken();
                        removeToken(newCursor);
                        removeToken(newCursor);
                        newCursor.insertChars(str + trim);
                    }
                } else {
                    str = null;
                }
                if (nextToken.isStart()) {
                    i++;
                } else if (nextToken.isEnd()) {
                    i--;
                } else if (nextToken.isEnddoc()) {
                    break;
                }
            }
        }
        newCursor.dispose();
    }

    /* access modifiers changed from: package-private */
    public Object parent() {
        Object obj;
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            obj = Undefined.instance;
        } else if (!newCursor.toParent()) {
            obj = Undefined.instance;
        } else if (newCursor.isStartdoc()) {
            obj = Undefined.instance;
        } else {
            obj = getFromAnnotation(this.lib, findAnnotation(newCursor));
        }
        newCursor.dispose();
        return obj;
    }

    /* access modifiers changed from: package-private */
    public XML prependChild(Object obj) {
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        newCursor.toFirstContentToken();
        insertChild(newCursor, obj);
        newCursor.dispose();
        return this;
    }

    /* access modifiers changed from: package-private */
    public Object processingInstructions(XMLName xMLName) {
        return matchChildren(XmlCursor.TokenType.PROCINST, xMLName);
    }

    /* access modifiers changed from: package-private */
    public boolean propertyIsEnumerable(Object obj) {
        if (obj instanceof Integer) {
            if (((Integer) obj).intValue() == 0) {
                return true;
            }
        } else if (!(obj instanceof Number)) {
            return ScriptRuntime.toString(obj).equals(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else {
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || 1.0d / doubleValue <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0098, code lost:
        r0.dispose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x009b, code lost:
        return r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.xml.impl.xmlbeans.XML removeNamespace(org.mozilla.javascript.xml.impl.xmlbeans.Namespace r11) {
        /*
            r10 = this;
            org.apache.xmlbeans.XmlCursor r0 = r10.newCursor()
            boolean r1 = r0.isStartdoc()     // Catch:{ all -> 0x0120 }
            if (r1 == 0) goto L_0x000d
            r0.toFirstContentToken()     // Catch:{ all -> 0x0120 }
        L_0x000d:
            boolean r1 = r0.isStart()     // Catch:{ all -> 0x0120 }
            if (r1 != 0) goto L_0x0017
            r0.dispose()
            return r10
        L_0x0017:
            java.lang.String r1 = r11.prefix()     // Catch:{ all -> 0x0120 }
            java.lang.String r11 = r11.uri()     // Catch:{ all -> 0x0120 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x0120 }
            r2.<init>()     // Catch:{ all -> 0x0120 }
            r3 = 1
        L_0x0025:
            boolean r4 = r0.isEnd()     // Catch:{ all -> 0x0120 }
            if (r4 == 0) goto L_0x0032
            if (r3 == 0) goto L_0x002e
            goto L_0x0032
        L_0x002e:
            r0.dispose()
            return r10
        L_0x0032:
            boolean r4 = r0.isStart()     // Catch:{ all -> 0x0120 }
            if (r4 == 0) goto L_0x0108
            r2.clear()     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.NamespaceHelper.getNamespaces(r0, r2)     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.ObjArray r4 = new org.mozilla.javascript.ObjArray     // Catch:{ all -> 0x0120 }
            r4.<init>()     // Catch:{ all -> 0x0120 }
            java.util.Set r5 = r2.entrySet()     // Catch:{ all -> 0x0120 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0120 }
        L_0x004b:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0120 }
            if (r6 == 0) goto L_0x006e
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0120 }
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r7 = new org.mozilla.javascript.xml.impl.xmlbeans.Namespace     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl r8 = r10.lib     // Catch:{ all -> 0x0120 }
            java.lang.Object r9 = r6.getKey()     // Catch:{ all -> 0x0120 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0120 }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x0120 }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x0120 }
            r7.<init>(r8, r9, r6)     // Catch:{ all -> 0x0120 }
            r4.add(r7)     // Catch:{ all -> 0x0120 }
            goto L_0x004b
        L_0x006e:
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r5 = new org.mozilla.javascript.xml.impl.xmlbeans.Namespace     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl r6 = r10.lib     // Catch:{ all -> 0x0120 }
            r5.<init>(r6, r11)     // Catch:{ all -> 0x0120 }
            r4.add(r5)     // Catch:{ all -> 0x0120 }
            java.lang.Object[] r4 = r4.toArray()     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl r5 = r10.lib     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r5 = org.mozilla.javascript.xml.impl.xmlbeans.NamespaceHelper.getNamespace(r5, r0, r4)     // Catch:{ all -> 0x0120 }
            java.lang.String r6 = r5.uri()     // Catch:{ all -> 0x0120 }
            boolean r6 = r11.equals(r6)     // Catch:{ all -> 0x0120 }
            if (r6 == 0) goto L_0x009c
            if (r1 == 0) goto L_0x0098
            java.lang.String r5 = r5.prefix()     // Catch:{ all -> 0x0120 }
            boolean r5 = r1.equals(r5)     // Catch:{ all -> 0x0120 }
            if (r5 == 0) goto L_0x009c
        L_0x0098:
            r0.dispose()
            return r10
        L_0x009c:
            r0.push()     // Catch:{ all -> 0x0120 }
            boolean r5 = r0.toFirstAttribute()     // Catch:{ all -> 0x0120 }
        L_0x00a3:
            if (r5 == 0) goto L_0x00ca
            org.mozilla.javascript.xml.impl.xmlbeans.XMLLibImpl r5 = r10.lib     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.Namespace r5 = org.mozilla.javascript.xml.impl.xmlbeans.NamespaceHelper.getNamespace(r5, r0, r4)     // Catch:{ all -> 0x0120 }
            java.lang.String r6 = r5.uri()     // Catch:{ all -> 0x0120 }
            boolean r6 = r11.equals(r6)     // Catch:{ all -> 0x0120 }
            if (r6 == 0) goto L_0x00c5
            if (r1 == 0) goto L_0x00c1
            java.lang.String r5 = r5.prefix()     // Catch:{ all -> 0x0120 }
            boolean r5 = r1.equals(r5)     // Catch:{ all -> 0x0120 }
            if (r5 == 0) goto L_0x00c5
        L_0x00c1:
            r0.dispose()
            return r10
        L_0x00c5:
            boolean r5 = r0.toNextAttribute()     // Catch:{ all -> 0x0120 }
            goto L_0x00a3
        L_0x00ca:
            r0.pop()     // Catch:{ all -> 0x0120 }
            if (r1 != 0) goto L_0x00f7
            java.util.Set r4 = r2.entrySet()     // Catch:{ all -> 0x0120 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0120 }
        L_0x00d7:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0120 }
            if (r5 == 0) goto L_0x0108
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0120 }
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5     // Catch:{ all -> 0x0120 }
            java.lang.Object r6 = r5.getValue()     // Catch:{ all -> 0x0120 }
            boolean r6 = r6.equals(r11)     // Catch:{ all -> 0x0120 }
            if (r6 == 0) goto L_0x00d7
            java.lang.Object r5 = r5.getKey()     // Catch:{ all -> 0x0120 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.NamespaceHelper.removeNamespace(r0, r5)     // Catch:{ all -> 0x0120 }
            goto L_0x00d7
        L_0x00f7:
            java.lang.Object r4 = r2.get(r1)     // Catch:{ all -> 0x0120 }
            boolean r4 = r11.equals(r4)     // Catch:{ all -> 0x0120 }
            if (r4 == 0) goto L_0x0108
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x0120 }
            org.mozilla.javascript.xml.impl.xmlbeans.NamespaceHelper.removeNamespace(r0, r4)     // Catch:{ all -> 0x0120 }
        L_0x0108:
            org.apache.xmlbeans.XmlCursor$TokenType r4 = r0.toNextToken()     // Catch:{ all -> 0x0120 }
            int r4 = r4.intValue()     // Catch:{ all -> 0x0120 }
            r5 = 3
            if (r4 == r5) goto L_0x011c
            r5 = 4
            if (r4 == r5) goto L_0x0118
            goto L_0x0025
        L_0x0118:
            int r3 = r3 + -1
            goto L_0x0025
        L_0x011c:
            int r3 = r3 + 1
            goto L_0x0025
        L_0x0120:
            r11 = move-exception
            r0.dispose()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XML.removeNamespace(org.mozilla.javascript.xml.impl.xmlbeans.Namespace):org.mozilla.javascript.xml.impl.xmlbeans.XML");
    }

    /* access modifiers changed from: package-private */
    public XML replace(long j, Object obj) {
        XMLList child = child(j);
        if (child.length() > 0) {
            insertChildAfter(child.item(0), obj);
            removeChild(j);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML replace(XMLName xMLName, Object obj) {
        putXMLProperty(xMLName, obj);
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML setChildren(Object obj) {
        getPropertyList(XMLName.formStar()).remove();
        appendChild(obj);
        return this;
    }

    /* access modifiers changed from: package-private */
    public void setLocalName(String str) {
        XmlCursor newCursor = newCursor();
        try {
            if (newCursor.isStartdoc()) {
                newCursor.toFirstContentToken();
            }
            if (!newCursor.isText()) {
                if (!newCursor.isComment()) {
                    QName name = newCursor.getName();
                    newCursor.setName(new QName(name.getNamespaceURI(), str, name.getPrefix()));
                    newCursor.dispose();
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public void setName(QName qName) {
        XmlCursor newCursor = newCursor();
        try {
            if (newCursor.isStartdoc()) {
                newCursor.toFirstContentToken();
            }
            if (!newCursor.isText()) {
                if (!newCursor.isComment()) {
                    if (newCursor.isProcinst()) {
                        newCursor.setName(new QName(qName.localName()));
                    } else {
                        String prefix = qName.prefix();
                        if (prefix == null) {
                            prefix = "";
                        }
                        newCursor.setName(new QName(qName.uri(), qName.localName(), prefix));
                    }
                    newCursor.dispose();
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public void setNamespace(Namespace namespace) {
        XmlCursor newCursor = newCursor();
        try {
            if (newCursor.isStartdoc()) {
                newCursor.toFirstContentToken();
            }
            if (!newCursor.isText() && !newCursor.isComment()) {
                if (!newCursor.isProcinst()) {
                    String prefix = namespace.prefix();
                    if (prefix == null) {
                        prefix = "";
                    }
                    newCursor.setName(new QName(namespace.uri(), localName(), prefix));
                    newCursor.dispose();
                }
            }
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public XMLList text() {
        return matchChildren(XmlCursor.TokenType.TEXT);
    }

    public String toString() {
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        if (newCursor.isText()) {
            return newCursor.getChars();
        }
        if (!newCursor.isStart() || !hasSimpleContent()) {
            return toXMLString(0);
        }
        return newCursor.getTextValue();
    }

    /* access modifiers changed from: package-private */
    public String toSource(int i) {
        return toXMLString(i);
    }

    /* access modifiers changed from: package-private */
    public String toXMLString(int i) {
        String str;
        XmlCursor newCursor = newCursor();
        if (newCursor.isStartdoc()) {
            newCursor.toFirstContentToken();
        }
        try {
            if (newCursor.isText()) {
                str = newCursor.getChars();
            } else if (newCursor.isAttr()) {
                str = newCursor.getTextValue();
            } else {
                if (!newCursor.isComment()) {
                    if (!newCursor.isProcinst()) {
                        str = dumpNode(newCursor, getOptions());
                    }
                }
                str = dumpNode(newCursor, getOptions());
                if (str.startsWith("<xml-fragment>")) {
                    str = str.substring(14);
                }
                if (str.endsWith("</xml-fragment>")) {
                    str = str.substring(0, str.length() - 15);
                }
            }
            return str;
        } finally {
            newCursor.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean equivalentXml(Object obj) {
        if (obj instanceof XML) {
            XML xml = (XML) obj;
            XmlCursor.TokenType tokenType = tokenType();
            XmlCursor.TokenType tokenType2 = xml.tokenType();
            if (tokenType == XmlCursor.TokenType.ATTR || tokenType2 == XmlCursor.TokenType.ATTR || tokenType == XmlCursor.TokenType.TEXT || tokenType2 == XmlCursor.TokenType.TEXT) {
                return toString().equals(xml.toString());
            }
            XmlCursor newCursor = newCursor();
            XmlCursor newCursor2 = xml.newCursor();
            boolean nodesEqual = LogicalEquality.nodesEqual(newCursor, newCursor2);
            newCursor.dispose();
            newCursor2.dispose();
            return nodesEqual;
        } else if (obj instanceof XMLList) {
            XMLList xMLList = (XMLList) obj;
            if (xMLList.length() == 1) {
                return equivalentXml(xMLList.getXmlFromAnnotation(0));
            }
            return false;
        } else if (!hasSimpleContent()) {
            return false;
        } else {
            return toString().equals(ScriptRuntime.toString(obj));
        }
    }

    /* access modifiers changed from: package-private */
    public XMLList getPropertyList(XMLName xMLName) {
        if (xMLName.isDescendants()) {
            return descendants(xMLName);
        }
        if (xMLName.isAttributeName()) {
            return attribute(xMLName);
        }
        return child(xMLName);
    }

    /* access modifiers changed from: protected */
    public Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (objArr.length == 0) {
            return createFromJS(this.lib, "");
        }
        Object obj = objArr[0];
        if (z || !(obj instanceof XML)) {
            return createFromJS(this.lib, obj);
        }
        return obj;
    }

    public Scriptable getExtraMethodSource(Context context) {
        if (hasSimpleContent()) {
            return ScriptRuntime.toObjectOrNull(context, toString());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public XmlObject getXmlObject() {
        XmlCursor newCursor = newCursor();
        try {
            return newCursor.getObject();
        } finally {
            newCursor.dispose();
        }
    }
}

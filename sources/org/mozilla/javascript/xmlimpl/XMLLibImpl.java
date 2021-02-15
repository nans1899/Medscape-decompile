package org.mozilla.javascript.xmlimpl;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.Serializable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.Wrapper;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.XmlNode;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public final class XMLLibImpl extends XMLLib implements Serializable {
    private static final long serialVersionUID = 1;
    private Scriptable globalScope;
    private Namespace namespacePrototype;
    private XmlProcessor options = new XmlProcessor();
    private QName qnamePrototype;
    private XMLList xmlListPrototype;
    private XML xmlPrototype;

    public static Node toDomNode(Object obj) {
        if (obj instanceof XML) {
            return ((XML) obj).toDomNode();
        }
        throw new IllegalArgumentException("xmlObject is not an XML object in JavaScript.");
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        XMLLibImpl xMLLibImpl = new XMLLibImpl(scriptable);
        if (xMLLibImpl.bindToScope(scriptable) == xMLLibImpl) {
            xMLLibImpl.exportToScope(z);
        }
    }

    public void setIgnoreComments(boolean z) {
        this.options.setIgnoreComments(z);
    }

    public void setIgnoreWhitespace(boolean z) {
        this.options.setIgnoreWhitespace(z);
    }

    public void setIgnoreProcessingInstructions(boolean z) {
        this.options.setIgnoreProcessingInstructions(z);
    }

    public void setPrettyPrinting(boolean z) {
        this.options.setPrettyPrinting(z);
    }

    public void setPrettyIndent(int i) {
        this.options.setPrettyIndent(i);
    }

    public boolean isIgnoreComments() {
        return this.options.isIgnoreComments();
    }

    public boolean isIgnoreProcessingInstructions() {
        return this.options.isIgnoreProcessingInstructions();
    }

    public boolean isIgnoreWhitespace() {
        return this.options.isIgnoreWhitespace();
    }

    public boolean isPrettyPrinting() {
        return this.options.isPrettyPrinting();
    }

    public int getPrettyIndent() {
        return this.options.getPrettyIndent();
    }

    private XMLLibImpl(Scriptable scriptable) {
        this.globalScope = scriptable;
    }

    /* access modifiers changed from: package-private */
    public QName qnamePrototype() {
        return this.qnamePrototype;
    }

    /* access modifiers changed from: package-private */
    public Scriptable globalScope() {
        return this.globalScope;
    }

    /* access modifiers changed from: package-private */
    public XmlProcessor getProcessor() {
        return this.options;
    }

    private void exportToScope(boolean z) {
        this.xmlPrototype = newXML(XmlNode.createText(this.options, ""));
        this.xmlListPrototype = newXMLList();
        this.namespacePrototype = Namespace.create(this.globalScope, (Namespace) null, XmlNode.Namespace.GLOBAL);
        this.qnamePrototype = QName.create(this, this.globalScope, (QName) null, XmlNode.QName.create(XmlNode.Namespace.create(""), ""));
        this.xmlPrototype.exportAsJSClass(z);
        this.xmlListPrototype.exportAsJSClass(z);
        this.namespacePrototype.exportAsJSClass(z);
        this.qnamePrototype.exportAsJSClass(z);
    }

    /* access modifiers changed from: package-private */
    public XMLName toAttributeName(Context context, Object obj) {
        String str;
        if (obj instanceof XMLName) {
            return (XMLName) obj;
        }
        if (obj instanceof QName) {
            return XMLName.create(((QName) obj).getDelegate(), true, false);
        }
        if ((obj instanceof Boolean) || (obj instanceof Number) || obj == Undefined.instance || obj == null) {
            throw badXMLName(obj);
        }
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = ScriptRuntime.toString(obj);
        }
        if (str != null && str.equals("*")) {
            str = null;
        }
        return XMLName.create(XmlNode.QName.create(XmlNode.Namespace.create(""), str), true, false);
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
    public XMLName toXMLNameFromString(Context context, String str) {
        return XMLName.create(getDefaultNamespaceURI(context), str);
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
    public Object addXMLObjects(Context context, XMLObject xMLObject, XMLObject xMLObject2) {
        XMLList newXMLList = newXMLList();
        if (xMLObject instanceof XMLList) {
            XMLList xMLList = (XMLList) xMLObject;
            if (xMLList.length() == 1) {
                newXMLList.addToList(xMLList.item(0));
            } else {
                newXMLList = newXMLListFrom(xMLObject);
            }
        } else {
            newXMLList.addToList(xMLObject);
        }
        if (xMLObject2 instanceof XMLList) {
            XMLList xMLList2 = (XMLList) xMLObject2;
            for (int i = 0; i < xMLList2.length(); i++) {
                newXMLList.addToList(xMLList2.item(i));
            }
        } else if (xMLObject2 instanceof XML) {
            newXMLList.addToList(xMLObject2);
        }
        return newXMLList;
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

    /* access modifiers changed from: package-private */
    public Namespace castToNamespace(Context context, Object obj) {
        return this.namespacePrototype.castToNamespace(obj);
    }

    private String getDefaultNamespaceURI(Context context) {
        return getDefaultNamespace(context).uri();
    }

    /* access modifiers changed from: package-private */
    public Namespace newNamespace(String str) {
        return this.namespacePrototype.newNamespace(str);
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
    public Namespace[] createNamespaces(XmlNode.Namespace[] namespaceArr) {
        Namespace[] namespaceArr2 = new Namespace[namespaceArr.length];
        for (int i = 0; i < namespaceArr.length; i++) {
            namespaceArr2[i] = this.namespacePrototype.newNamespace(namespaceArr[i].getPrefix(), namespaceArr[i].getUri());
        }
        return namespaceArr2;
    }

    /* access modifiers changed from: package-private */
    public QName constructQName(Context context, Object obj, Object obj2) {
        return this.qnamePrototype.constructQName(this, context, obj, obj2);
    }

    /* access modifiers changed from: package-private */
    public QName newQName(String str, String str2, String str3) {
        return this.qnamePrototype.newQName(this, str, str2, str3);
    }

    /* access modifiers changed from: package-private */
    public QName constructQName(Context context, Object obj) {
        return this.qnamePrototype.constructQName(this, context, obj);
    }

    /* access modifiers changed from: package-private */
    public QName castToQName(Context context, Object obj) {
        return this.qnamePrototype.castToQName(this, context, obj);
    }

    /* access modifiers changed from: package-private */
    public QName newQName(XmlNode.QName qName) {
        return QName.create(this, this.globalScope, this.qnamePrototype, qName);
    }

    /* access modifiers changed from: package-private */
    public XML newXML(XmlNode xmlNode) {
        return new XML(this, this.globalScope, this.xmlPrototype, xmlNode);
    }

    /* access modifiers changed from: package-private */
    public final XML newXMLFromJs(Object obj) {
        String str;
        if (obj == null || obj == Undefined.instance) {
            str = "";
        } else {
            str = obj instanceof XMLObjectImpl ? ((XMLObjectImpl) obj).toXMLString() : ScriptRuntime.toString(obj);
        }
        if (str.trim().startsWith("<>")) {
            throw ScriptRuntime.typeError("Invalid use of XML object anonymous tags <></>.");
        } else if (str.indexOf(HtmlObject.HtmlMarkUp.OPEN_BRACKER) == -1) {
            return newXML(XmlNode.createText(this.options, str));
        } else {
            return parse(str);
        }
    }

    private XML parse(String str) {
        try {
            return newXML(XmlNode.createElement(this.options, getDefaultNamespaceURI(Context.getCurrentContext()), str));
        } catch (SAXException e) {
            throw ScriptRuntime.typeError("Cannot parse XML: " + e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public final XML ecmaToXml(Object obj) {
        if (obj == null || obj == Undefined.instance) {
            throw ScriptRuntime.typeError("Cannot convert " + obj + " to XML");
        } else if (obj instanceof XML) {
            return (XML) obj;
        } else {
            if (obj instanceof XMLList) {
                XMLList xMLList = (XMLList) obj;
                if (xMLList.getXML() != null) {
                    return xMLList.getXML();
                }
                throw ScriptRuntime.typeError("Cannot convert list of >1 element to XML");
            }
            if (obj instanceof Wrapper) {
                obj = ((Wrapper) obj).unwrap();
            }
            if (obj instanceof Node) {
                return newXML(XmlNode.createElementFromNode((Node) obj));
            }
            String scriptRuntime = ScriptRuntime.toString(obj);
            if (scriptRuntime.length() <= 0 || scriptRuntime.charAt(0) != '<') {
                return newXML(XmlNode.createText(this.options, scriptRuntime));
            }
            return parse(scriptRuntime);
        }
    }

    /* access modifiers changed from: package-private */
    public final XML newTextElementXML(XmlNode xmlNode, XmlNode.QName qName, String str) {
        return newXML(XmlNode.newElementWithText(this.options, xmlNode, qName, str));
    }

    /* access modifiers changed from: package-private */
    public XMLList newXMLList() {
        return new XMLList(this, this.globalScope, this.xmlListPrototype);
    }

    /* access modifiers changed from: package-private */
    public final XMLList newXMLListFrom(Object obj) {
        XMLList newXMLList = newXMLList();
        if (obj == null || (obj instanceof Undefined)) {
            return newXMLList;
        }
        if (obj instanceof XML) {
            newXMLList.getNodeList().add((XML) obj);
            return newXMLList;
        } else if (obj instanceof XMLList) {
            newXMLList.getNodeList().add(((XMLList) obj).getNodeList());
            return newXMLList;
        } else {
            String trim = ScriptRuntime.toString(obj).trim();
            if (!trim.startsWith("<>")) {
                trim = "<>" + trim + "</>";
            }
            String str = "<fragment>" + trim.substring(2);
            if (str.endsWith("</>")) {
                StringBuilder sb = new StringBuilder();
                sb.append(str.substring(0, str.length() - 3));
                sb.append("</fragment>");
                XMLList children = newXMLFromJs(sb.toString()).children();
                for (int i = 0; i < children.getNodeList().length(); i++) {
                    newXMLList.getNodeList().add((XML) children.item(i).copy());
                }
                return newXMLList;
            }
            throw ScriptRuntime.typeError("XML with anonymous tag missing end anonymous tag");
        }
    }

    /* access modifiers changed from: package-private */
    public XmlNode.QName toNodeQName(Context context, Object obj, Object obj2) {
        String str;
        XmlNode.Namespace namespace;
        if (obj2 instanceof QName) {
            str = ((QName) obj2).localName();
        } else {
            str = ScriptRuntime.toString(obj2);
        }
        if (obj == Undefined.instance) {
            if (!"*".equals(str)) {
                namespace = getDefaultNamespace(context).getDelegate();
                if (str != null && str.equals("*")) {
                    str = null;
                }
                return XmlNode.QName.create(namespace, str);
            }
        } else if (obj != null) {
            namespace = obj instanceof Namespace ? ((Namespace) obj).getDelegate() : this.namespacePrototype.constructNamespace(obj).getDelegate();
            str = null;
            return XmlNode.QName.create(namespace, str);
        }
        namespace = null;
        str = null;
        return XmlNode.QName.create(namespace, str);
    }

    /* access modifiers changed from: package-private */
    public XmlNode.QName toNodeQName(Context context, String str, boolean z) {
        XmlNode.Namespace delegate = getDefaultNamespace(context).getDelegate();
        if (str != null && str.equals("*")) {
            return XmlNode.QName.create((XmlNode.Namespace) null, (String) null);
        }
        if (z) {
            return XmlNode.QName.create(XmlNode.Namespace.GLOBAL, str);
        }
        return XmlNode.QName.create(delegate, str);
    }

    /* access modifiers changed from: package-private */
    public XmlNode.QName toNodeQName(Context context, Object obj, boolean z) {
        String str;
        if (obj instanceof XMLName) {
            return ((XMLName) obj).toQname();
        }
        if (obj instanceof QName) {
            return ((QName) obj).getDelegate();
        }
        if ((obj instanceof Boolean) || (obj instanceof Number) || obj == Undefined.instance || obj == null) {
            throw badXMLName(obj);
        }
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = ScriptRuntime.toString(obj);
        }
        return toNodeQName(context, str, z);
    }

    public boolean isXMLName(Context context, Object obj) {
        return XMLName.accept(obj);
    }

    public Object toDefaultXmlNamespace(Context context, Object obj) {
        return this.namespacePrototype.constructNamespace(obj);
    }

    public String escapeTextValue(Object obj) {
        return this.options.escapeTextValue(obj);
    }

    public String escapeAttributeValue(Object obj) {
        return this.options.escapeAttributeValue(obj);
    }

    public Ref nameRef(Context context, Object obj, Scriptable scriptable, int i) {
        if ((i & 2) != 0) {
            return xmlPrimaryReference(context, toAttributeName(context, obj), scriptable);
        }
        throw Kit.codeBug();
    }

    public Ref nameRef(Context context, Object obj, Object obj2, Scriptable scriptable, int i) {
        XMLName create = XMLName.create(toNodeQName(context, obj, obj2), false, false);
        if ((i & 2) != 0 && !create.isAttributeName()) {
            create.setAttributeName();
        }
        return xmlPrimaryReference(context, create, scriptable);
    }
}

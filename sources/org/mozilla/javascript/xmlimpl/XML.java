package org.mozilla.javascript.xmlimpl;

import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.XmlNode;
import org.w3c.dom.Node;

class XML extends XMLObjectImpl {
    static final long serialVersionUID = -630969919086449092L;
    private XmlNode node;

    public String getClassName() {
        return "XML";
    }

    /* access modifiers changed from: package-private */
    public final XML getXML() {
        return this;
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

    XML(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject, XmlNode xmlNode) {
        super(xMLLibImpl, scriptable, xMLObject);
        initialize(xmlNode);
    }

    /* access modifiers changed from: package-private */
    public void initialize(XmlNode xmlNode) {
        this.node = xmlNode;
        xmlNode.setXml(this);
    }

    /* access modifiers changed from: package-private */
    public void replaceWith(XML xml) {
        if (this.node.parent() == null) {
            initialize(xml.node);
        } else {
            this.node.replaceWith(xml.node);
        }
    }

    /* access modifiers changed from: package-private */
    public XML makeXmlFromString(XMLName xMLName, String str) {
        try {
            return newTextElementXML(this.node, xMLName.toQname(), str);
        } catch (Exception e) {
            throw ScriptRuntime.typeError(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public XmlNode getAnnotation() {
        return this.node;
    }

    public Object get(int i, Scriptable scriptable) {
        return i == 0 ? this : Scriptable.NOT_FOUND;
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        throw ScriptRuntime.typeError("Assignment to indexed XML is not allowed");
    }

    public Object[] getIds() {
        if (isPrototype()) {
            return new Object[0];
        }
        return new Object[]{0};
    }

    public void delete(int i) {
        if (i == 0) {
            remove();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName).length() > 0;
    }

    /* access modifiers changed from: package-private */
    public Object getXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName);
    }

    /* access modifiers changed from: package-private */
    public XmlNode.QName getNodeQname() {
        return this.node.getQname();
    }

    /* access modifiers changed from: package-private */
    public XML[] getChildren() {
        if (!isElement()) {
            return null;
        }
        XmlNode[] matchingChildren = this.node.getMatchingChildren(XmlNode.Filter.TRUE);
        int length = matchingChildren.length;
        XML[] xmlArr = new XML[length];
        for (int i = 0; i < length; i++) {
            xmlArr[i] = toXML(matchingChildren[i]);
        }
        return xmlArr;
    }

    /* access modifiers changed from: package-private */
    public XML[] getAttributes() {
        XmlNode[] attributes = this.node.getAttributes();
        int length = attributes.length;
        XML[] xmlArr = new XML[length];
        for (int i = 0; i < length; i++) {
            xmlArr[i] = toXML(attributes[i]);
        }
        return xmlArr;
    }

    /* access modifiers changed from: package-private */
    public XMLList getPropertyList(XMLName xMLName) {
        return xMLName.getMyValueOn(this);
    }

    /* access modifiers changed from: package-private */
    public void deleteXMLProperty(XMLName xMLName) {
        XMLList propertyList = getPropertyList(xMLName);
        for (int i = 0; i < propertyList.length(); i++) {
            propertyList.item(i).node.deleteMe();
        }
    }

    /* access modifiers changed from: package-private */
    public void putXMLProperty(XMLName xMLName, Object obj) {
        if (!isPrototype()) {
            xMLName.setMyValueOn(this, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasOwnProperty(XMLName xMLName) {
        if (isPrototype()) {
            if (findPrototypeId(xMLName.localName()) != 0) {
                return true;
            }
        } else if (getPropertyList(xMLName).length() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (objArr.length == 0 || objArr[0] == null || objArr[0] == Undefined.instance) {
            objArr = new Object[]{""};
        }
        XML ecmaToXml = ecmaToXml(objArr[0]);
        return z ? ecmaToXml.copy() : ecmaToXml;
    }

    public Scriptable getExtraMethodSource(Context context) {
        if (hasSimpleContent()) {
            return ScriptRuntime.toObjectOrNull(context, toString());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void removeChild(int i) {
        this.node.removeChild(i);
    }

    /* access modifiers changed from: package-private */
    public void normalize() {
        this.node.normalize();
    }

    private XML toXML(XmlNode xmlNode) {
        if (xmlNode.getXml() == null) {
            xmlNode.setXml(newXML(xmlNode));
        }
        return xmlNode.getXml();
    }

    /* access modifiers changed from: package-private */
    public void setAttribute(XMLName xMLName, Object obj) {
        if (!isElement()) {
            throw new IllegalStateException("Can only set attributes on elements.");
        } else if (xMLName.uri() != null || !xMLName.localName().equals("*")) {
            this.node.setAttribute(xMLName.toQname(), ScriptRuntime.toString(obj));
        } else {
            throw ScriptRuntime.typeError("@* assignment not supported.");
        }
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        this.node.deleteMe();
    }

    /* access modifiers changed from: package-private */
    public void addMatches(XMLList xMLList, XMLName xMLName) {
        xMLName.addMatches(xMLList, this);
    }

    /* access modifiers changed from: package-private */
    public XMLList elements(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        newXMLList.setTargets(this, xMLName.toQname());
        XmlNode[] matchingChildren = this.node.getMatchingChildren(XmlNode.Filter.ELEMENT);
        for (int i = 0; i < matchingChildren.length; i++) {
            if (xMLName.matches(toXML(matchingChildren[i]))) {
                newXMLList.addToList(toXML(matchingChildren[i]));
            }
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList child(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        XmlNode[] matchingChildren = this.node.getMatchingChildren(XmlNode.Filter.ELEMENT);
        for (int i = 0; i < matchingChildren.length; i++) {
            if (xMLName.matchesElement(matchingChildren[i].getQname())) {
                newXMLList.addToList(toXML(matchingChildren[i]));
            }
        }
        newXMLList.setTargets(this, xMLName.toQname());
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XML replace(XMLName xMLName, Object obj) {
        putXMLProperty(xMLName, obj);
        return this;
    }

    /* access modifiers changed from: package-private */
    public XMLList children() {
        XMLList newXMLList = newXMLList();
        newXMLList.setTargets(this, XMLName.formStar().toQname());
        XmlNode[] matchingChildren = this.node.getMatchingChildren(XmlNode.Filter.TRUE);
        for (XmlNode xml : matchingChildren) {
            newXMLList.addToList(toXML(xml));
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList child(int i) {
        XMLList newXMLList = newXMLList();
        newXMLList.setTargets(this, (XmlNode.QName) null);
        if (i >= 0 && i < this.node.getChildCount()) {
            newXMLList.addToList(getXmlChild(i));
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XML getXmlChild(int i) {
        XmlNode child = this.node.getChild(i);
        if (child.getXml() == null) {
            child.setXml(newXML(child));
        }
        return child.getXml();
    }

    /* access modifiers changed from: package-private */
    public XML getLastXmlChild() {
        int childCount = this.node.getChildCount() - 1;
        if (childCount < 0) {
            return null;
        }
        return getXmlChild(childCount);
    }

    /* access modifiers changed from: package-private */
    public int childIndex() {
        return this.node.getChildIndex();
    }

    /* access modifiers changed from: package-private */
    public boolean contains(Object obj) {
        if (obj instanceof XML) {
            return equivalentXml(obj);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean equivalentXml(Object obj) {
        if (obj instanceof XML) {
            return this.node.toXmlString(getProcessor()).equals(((XML) obj).node.toXmlString(getProcessor()));
        }
        if (obj instanceof XMLList) {
            XMLList xMLList = (XMLList) obj;
            if (xMLList.length() == 1) {
                return equivalentXml(xMLList.getXML());
            }
            return false;
        } else if (!hasSimpleContent()) {
            return false;
        } else {
            return toString().equals(ScriptRuntime.toString(obj));
        }
    }

    /* access modifiers changed from: package-private */
    public XMLObjectImpl copy() {
        return newXML(this.node.copy());
    }

    /* access modifiers changed from: package-private */
    public boolean hasSimpleContent() {
        if (isComment() || isProcessingInstruction()) {
            return false;
        }
        if (isText() || this.node.isAttributeType()) {
            return true;
        }
        return !this.node.hasChildElement();
    }

    /* access modifiers changed from: package-private */
    public boolean hasComplexContent() {
        return !hasSimpleContent();
    }

    /* access modifiers changed from: package-private */
    public boolean is(XML xml) {
        return this.node.isSameNode(xml.node);
    }

    /* access modifiers changed from: package-private */
    public Object nodeKind() {
        return ecmaClass();
    }

    /* access modifiers changed from: package-private */
    public Object parent() {
        if (this.node.parent() == null) {
            return null;
        }
        return newXML(this.node.parent());
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
    public XMLList comments() {
        XMLList newXMLList = newXMLList();
        this.node.addMatchingChildren(newXMLList, XmlNode.Filter.COMMENT);
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList text() {
        XMLList newXMLList = newXMLList();
        this.node.addMatchingChildren(newXMLList, XmlNode.Filter.TEXT);
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList processingInstructions(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        this.node.addMatchingChildren(newXMLList, XmlNode.Filter.PROCESSING_INSTRUCTION(xMLName));
        return newXMLList;
    }

    private XmlNode[] getNodesForInsert(Object obj) {
        if (obj instanceof XML) {
            return new XmlNode[]{((XML) obj).node};
        } else if (obj instanceof XMLList) {
            XMLList xMLList = (XMLList) obj;
            XmlNode[] xmlNodeArr = new XmlNode[xMLList.length()];
            for (int i = 0; i < xMLList.length(); i++) {
                xmlNodeArr[i] = xMLList.item(i).node;
            }
            return xmlNodeArr;
        } else {
            return new XmlNode[]{XmlNode.createText(getProcessor(), ScriptRuntime.toString(obj))};
        }
    }

    /* access modifiers changed from: package-private */
    public XML replace(int i, Object obj) {
        XMLList child = child(i);
        if (child.length() > 0) {
            insertChildAfter(child.item(0), obj);
            removeChild(i);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML prependChild(Object obj) {
        if (this.node.isParentType()) {
            this.node.insertChildrenAt(0, getNodesForInsert(obj));
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML appendChild(Object obj) {
        if (this.node.isParentType()) {
            XmlNode[] nodesForInsert = getNodesForInsert(obj);
            XmlNode xmlNode = this.node;
            xmlNode.insertChildrenAt(xmlNode.getChildCount(), nodesForInsert);
        }
        return this;
    }

    private int getChildIndexOf(XML xml) {
        for (int i = 0; i < this.node.getChildCount(); i++) {
            if (this.node.getChild(i).isSameNode(xml.node)) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public XML insertChildBefore(XML xml, Object obj) {
        if (xml == null) {
            appendChild(obj);
        } else {
            XmlNode[] nodesForInsert = getNodesForInsert(obj);
            int childIndexOf = getChildIndexOf(xml);
            if (childIndexOf != -1) {
                this.node.insertChildrenAt(childIndexOf, nodesForInsert);
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML insertChildAfter(XML xml, Object obj) {
        if (xml == null) {
            prependChild(obj);
        } else {
            XmlNode[] nodesForInsert = getNodesForInsert(obj);
            int childIndexOf = getChildIndexOf(xml);
            if (childIndexOf != -1) {
                this.node.insertChildrenAt(childIndexOf + 1, nodesForInsert);
            }
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML setChildren(Object obj) {
        if (!isElement()) {
            return this;
        }
        while (this.node.getChildCount() > 0) {
            this.node.removeChild(0);
        }
        this.node.insertChildrenAt(0, getNodesForInsert(obj));
        return this;
    }

    private void addInScopeNamespace(Namespace namespace) {
        if (!isElement() || namespace.prefix() == null) {
            return;
        }
        if (namespace.prefix().length() != 0 || namespace.uri().length() != 0) {
            if (this.node.getQname().getNamespace().getPrefix().equals(namespace.prefix())) {
                this.node.invalidateNamespacePrefix();
            }
            this.node.declareNamespace(namespace.prefix(), namespace.uri());
        }
    }

    /* access modifiers changed from: package-private */
    public Namespace[] inScopeNamespaces() {
        return createNamespaces(this.node.getInScopeNamespaces());
    }

    private XmlNode.Namespace adapt(Namespace namespace) {
        if (namespace.prefix() == null) {
            return XmlNode.Namespace.create(namespace.uri());
        }
        return XmlNode.Namespace.create(namespace.prefix(), namespace.uri());
    }

    /* access modifiers changed from: package-private */
    public XML removeNamespace(Namespace namespace) {
        if (!isElement()) {
            return this;
        }
        this.node.removeNamespace(adapt(namespace));
        return this;
    }

    /* access modifiers changed from: package-private */
    public XML addNamespace(Namespace namespace) {
        addInScopeNamespace(namespace);
        return this;
    }

    /* access modifiers changed from: package-private */
    public QName name() {
        if (isText() || isComment()) {
            return null;
        }
        if (isProcessingInstruction()) {
            return newQName("", this.node.getQname().getLocalName(), (String) null);
        }
        return newQName(this.node.getQname());
    }

    /* access modifiers changed from: package-private */
    public Namespace[] namespaceDeclarations() {
        return createNamespaces(this.node.getNamespaceDeclarations());
    }

    /* access modifiers changed from: package-private */
    public Namespace namespace(String str) {
        if (str == null) {
            return createNamespace(this.node.getNamespaceDeclaration());
        }
        return createNamespace(this.node.getNamespaceDeclaration(str));
    }

    /* access modifiers changed from: package-private */
    public String localName() {
        if (name() == null) {
            return null;
        }
        return name().localName();
    }

    /* access modifiers changed from: package-private */
    public void setLocalName(String str) {
        if (!isText() && !isComment()) {
            this.node.setLocalName(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void setName(QName qName) {
        if (!isText() && !isComment()) {
            if (isProcessingInstruction()) {
                this.node.setLocalName(qName.localName());
            } else {
                this.node.renameNode(qName.getDelegate());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setNamespace(Namespace namespace) {
        if (!isText() && !isComment() && !isProcessingInstruction()) {
            setName(newQName(namespace.uri(), localName(), namespace.prefix()));
        }
    }

    /* access modifiers changed from: package-private */
    public final String ecmaClass() {
        if (this.node.isTextType()) {
            return "text";
        }
        if (this.node.isAttributeType()) {
            return "attribute";
        }
        if (this.node.isCommentType()) {
            return "comment";
        }
        if (this.node.isProcessingInstructionType()) {
            return "processing-instruction";
        }
        if (this.node.isElementType()) {
            return "element";
        }
        throw new RuntimeException("Unrecognized type: " + this.node);
    }

    private String ecmaValue() {
        return this.node.ecmaValue();
    }

    private String ecmaToString() {
        if (isAttribute() || isText()) {
            return ecmaValue();
        }
        if (!hasSimpleContent()) {
            return toXMLString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.node.getChildCount(); i++) {
            XmlNode child = this.node.getChild(i);
            if (!child.isProcessingInstructionType() && !child.isCommentType()) {
                stringBuffer.append(new XML(getLib(), getParentScope(), (XMLObject) getPrototype(), child).toString());
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        return ecmaToString();
    }

    /* access modifiers changed from: package-private */
    public String toSource(int i) {
        return toXMLString();
    }

    /* access modifiers changed from: package-private */
    public String toXMLString() {
        return this.node.ecmaToXMLString(getProcessor());
    }

    /* access modifiers changed from: package-private */
    public final boolean isAttribute() {
        return this.node.isAttributeType();
    }

    /* access modifiers changed from: package-private */
    public final boolean isComment() {
        return this.node.isCommentType();
    }

    /* access modifiers changed from: package-private */
    public final boolean isText() {
        return this.node.isTextType();
    }

    /* access modifiers changed from: package-private */
    public final boolean isElement() {
        return this.node.isElementType();
    }

    /* access modifiers changed from: package-private */
    public final boolean isProcessingInstruction() {
        return this.node.isProcessingInstructionType();
    }

    /* access modifiers changed from: package-private */
    public Node toDomNode() {
        return this.node.toDomNode();
    }
}

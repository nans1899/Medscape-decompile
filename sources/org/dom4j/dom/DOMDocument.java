package org.dom4j.dom;

import java.util.ArrayList;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.tree.DefaultDocument;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class DOMDocument extends DefaultDocument implements Document {
    private static final DOMDocumentFactory DOCUMENT_FACTORY = ((DOMDocumentFactory) DOMDocumentFactory.getInstance());

    public NamedNodeMap getAttributes() {
        return null;
    }

    public String getNodeName() {
        return "#document";
    }

    public String getNodeValue() throws DOMException {
        return null;
    }

    public Document getOwnerDocument() {
        return null;
    }

    public void setNodeValue(String str) throws DOMException {
    }

    public DOMDocument() {
        init();
    }

    public DOMDocument(String str) {
        super(str);
        init();
    }

    public DOMDocument(DOMElement dOMElement) {
        super((Element) dOMElement);
        init();
    }

    public DOMDocument(DOMDocumentType dOMDocumentType) {
        super((DocumentType) dOMDocumentType);
        init();
    }

    public DOMDocument(DOMElement dOMElement, DOMDocumentType dOMDocumentType) {
        super(dOMElement, dOMDocumentType);
        init();
    }

    public DOMDocument(String str, DOMElement dOMElement, DOMDocumentType dOMDocumentType) {
        super(str, dOMElement, dOMDocumentType);
        init();
    }

    private void init() {
        setDocumentFactory(DOCUMENT_FACTORY);
    }

    public boolean supports(String str, String str2) {
        return DOMNodeHelper.supports(this, str, str2);
    }

    public String getNamespaceURI() {
        return DOMNodeHelper.getNamespaceURI(this);
    }

    public String getPrefix() {
        return DOMNodeHelper.getPrefix(this);
    }

    public void setPrefix(String str) throws DOMException {
        DOMNodeHelper.setPrefix(this, str);
    }

    public String getLocalName() {
        return DOMNodeHelper.getLocalName(this);
    }

    public Node getParentNode() {
        return DOMNodeHelper.getParentNode(this);
    }

    public NodeList getChildNodes() {
        return DOMNodeHelper.createNodeList(content());
    }

    public Node getFirstChild() {
        return DOMNodeHelper.asDOMNode(node(0));
    }

    public Node getLastChild() {
        return DOMNodeHelper.asDOMNode(node(nodeCount() - 1));
    }

    public Node getPreviousSibling() {
        return DOMNodeHelper.getPreviousSibling(this);
    }

    public Node getNextSibling() {
        return DOMNodeHelper.getNextSibling(this);
    }

    public Node insertBefore(Node node, Node node2) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.insertBefore(this, node, node2);
    }

    public Node replaceChild(Node node, Node node2) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.replaceChild(this, node, node2);
    }

    public Node removeChild(Node node) throws DOMException {
        return DOMNodeHelper.removeChild(this, node);
    }

    public Node appendChild(Node node) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.appendChild(this, node);
    }

    private void checkNewChildNode(Node node) throws DOMException {
        short nodeType = node.getNodeType();
        if (nodeType != 1 && nodeType != 8 && nodeType != 7 && nodeType != 10) {
            throw new DOMException(3, "Given node cannot be a child of document");
        }
    }

    public boolean hasChildNodes() {
        return nodeCount() > 0;
    }

    public Node cloneNode(boolean z) {
        return DOMNodeHelper.cloneNode(this, z);
    }

    public boolean isSupported(String str, String str2) {
        return DOMNodeHelper.isSupported(this, str, str2);
    }

    public boolean hasAttributes() {
        return DOMNodeHelper.hasAttributes(this);
    }

    public NodeList getElementsByTagName(String str) {
        ArrayList arrayList = new ArrayList();
        DOMNodeHelper.appendElementsByTagName(arrayList, this, str);
        return DOMNodeHelper.createNodeList(arrayList);
    }

    public NodeList getElementsByTagNameNS(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        DOMNodeHelper.appendElementsByTagNameNS(arrayList, this, str, str2);
        return DOMNodeHelper.createNodeList(arrayList);
    }

    public org.w3c.dom.DocumentType getDoctype() {
        return DOMNodeHelper.asDOMDocumentType(getDocType());
    }

    public DOMImplementation getImplementation() {
        if (getDocumentFactory() instanceof DOMImplementation) {
            return (DOMImplementation) getDocumentFactory();
        }
        return DOCUMENT_FACTORY;
    }

    public org.w3c.dom.Element getDocumentElement() {
        return DOMNodeHelper.asDOMElement(getRootElement());
    }

    public org.w3c.dom.Element createElement(String str) throws DOMException {
        return (org.w3c.dom.Element) getDocumentFactory().createElement(str);
    }

    public DocumentFragment createDocumentFragment() {
        DOMNodeHelper.notSupported();
        return null;
    }

    public Text createTextNode(String str) {
        return (Text) getDocumentFactory().createText(str);
    }

    public Comment createComment(String str) {
        return (Comment) getDocumentFactory().createComment(str);
    }

    public CDATASection createCDATASection(String str) throws DOMException {
        return (CDATASection) getDocumentFactory().createCDATA(str);
    }

    public ProcessingInstruction createProcessingInstruction(String str, String str2) throws DOMException {
        return (ProcessingInstruction) getDocumentFactory().createProcessingInstruction(str, str2);
    }

    public Attr createAttribute(String str) throws DOMException {
        return (Attr) getDocumentFactory().createAttribute((Element) null, getDocumentFactory().createQName(str), "");
    }

    public EntityReference createEntityReference(String str) throws DOMException {
        return (EntityReference) getDocumentFactory().createEntity(str, (String) null);
    }

    public Node importNode(Node node, boolean z) throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
    }

    public org.w3c.dom.Element createElementNS(String str, String str2) throws DOMException {
        return (org.w3c.dom.Element) getDocumentFactory().createElement(getDocumentFactory().createQName(str2, str));
    }

    public Attr createAttributeNS(String str, String str2) throws DOMException {
        return (Attr) getDocumentFactory().createAttribute((Element) null, getDocumentFactory().createQName(str2, str), (String) null);
    }

    public org.w3c.dom.Element getElementById(String str) {
        return DOMNodeHelper.asDOMElement(elementByID(str));
    }

    /* access modifiers changed from: protected */
    public DocumentFactory getDocumentFactory() {
        if (super.getDocumentFactory() == null) {
            return DOCUMENT_FACTORY;
        }
        return super.getDocumentFactory();
    }
}

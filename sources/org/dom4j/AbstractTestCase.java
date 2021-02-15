package org.dom4j;

import java.io.File;
import junit.framework.TestCase;
import org.dom4j.io.SAXReader;
import org.dom4j.util.NodeComparator;

public class AbstractTestCase extends TestCase {
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    protected Document document;

    protected AbstractTestCase() {
    }

    protected AbstractTestCase(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    public void log(String str) {
        System.out.println(str);
    }

    /* access modifiers changed from: protected */
    public Document getDocument() {
        return this.document;
    }

    /* access modifiers changed from: protected */
    public Document getDocument(String str) throws Exception {
        return getDocument(str, new SAXReader());
    }

    /* access modifiers changed from: protected */
    public Document getDocument(String str, SAXReader sAXReader) throws Exception {
        return sAXReader.read(getFile(str));
    }

    /* access modifiers changed from: protected */
    public File getFile(String str) {
        return new File(System.getProperty("user.dir"), str);
    }

    public void assertDocumentsEqual(Document document2, Document document3) throws Exception {
        boolean z = true;
        try {
            assertTrue("Doc1 not null", document2 != null);
            assertTrue("Doc2 not null", document3 != null);
            document2.normalize();
            document3.normalize();
            assertNodesEqual(document2, document3);
            if (new NodeComparator().compare(document2, document3) != 0) {
                z = false;
            }
            assertTrue("Documents are equal", z);
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer("Failed during comparison of: ");
            stringBuffer.append(document2);
            stringBuffer.append(" and: ");
            stringBuffer.append(document3);
            log(stringBuffer.toString());
            throw e;
        }
    }

    public void assertNodesEqual(Document document2, Document document3) {
        assertNodesEqual(document2.getDocType(), document3.getDocType());
        assertNodesEqualContent(document2, document3);
    }

    public void assertNodesEqual(Element element, Element element2) {
        assertNodesEqual(element.getQName(), element2.getQName());
        int attributeCount = element.attributeCount();
        int attributeCount2 = element2.attributeCount();
        StringBuffer stringBuffer = new StringBuffer("Elements have same number of attributes (");
        stringBuffer.append(attributeCount);
        stringBuffer.append(", ");
        stringBuffer.append(attributeCount2);
        stringBuffer.append(" for: ");
        stringBuffer.append(element);
        stringBuffer.append(" and ");
        stringBuffer.append(element2);
        assertEquals(stringBuffer.toString(), attributeCount, attributeCount2);
        for (int i = 0; i < attributeCount; i++) {
            Attribute attribute = element.attribute(i);
            assertNodesEqual(attribute, element2.attribute(attribute.getQName()));
        }
        assertNodesEqualContent(element, element2);
    }

    public void assertNodesEqual(Attribute attribute, Attribute attribute2) {
        assertNodesEqual(attribute.getQName(), attribute2.getQName());
        StringBuffer stringBuffer = new StringBuffer("Attribute values for: ");
        stringBuffer.append(attribute);
        stringBuffer.append(" and ");
        stringBuffer.append(attribute2);
        assertEquals(stringBuffer.toString(), attribute.getValue(), attribute2.getValue());
    }

    public void assertNodesEqual(QName qName, QName qName2) {
        StringBuffer stringBuffer = new StringBuffer("URIs equal for: ");
        stringBuffer.append(qName.getQualifiedName());
        stringBuffer.append(" and ");
        stringBuffer.append(qName2.getQualifiedName());
        assertEquals(stringBuffer.toString(), qName.getNamespaceURI(), qName2.getNamespaceURI());
        assertEquals("qualified names equal", qName.getQualifiedName(), qName2.getQualifiedName());
    }

    public void assertNodesEqual(CharacterData characterData, CharacterData characterData2) {
        StringBuffer stringBuffer = new StringBuffer("Text equal for: ");
        stringBuffer.append(characterData);
        stringBuffer.append(" and ");
        stringBuffer.append(characterData2);
        assertEquals(stringBuffer.toString(), characterData.getText(), characterData2.getText());
    }

    public void assertNodesEqual(DocumentType documentType, DocumentType documentType2) {
        if (documentType == documentType2) {
            return;
        }
        if (documentType == null) {
            StringBuffer stringBuffer = new StringBuffer("Missing DocType: ");
            stringBuffer.append(documentType2);
            assertTrue(stringBuffer.toString(), false);
        } else if (documentType2 == null) {
            StringBuffer stringBuffer2 = new StringBuffer("Missing DocType: ");
            stringBuffer2.append(documentType);
            assertTrue(stringBuffer2.toString(), false);
        } else {
            assertEquals("DocType name equal", documentType.getName(), documentType2.getName());
            assertEquals("DocType publicID equal", documentType.getPublicID(), documentType2.getPublicID());
            assertEquals("DocType systemID equal", documentType.getSystemID(), documentType2.getSystemID());
        }
    }

    public void assertNodesEqual(Entity entity, Entity entity2) {
        assertEquals("Entity names equal", entity.getName(), entity2.getName());
        assertEquals("Entity values equal", entity.getText(), entity2.getText());
    }

    public void assertNodesEqual(ProcessingInstruction processingInstruction, ProcessingInstruction processingInstruction2) {
        assertEquals("PI targets equal", processingInstruction.getTarget(), processingInstruction2.getTarget());
        assertEquals("PI text equal", processingInstruction.getText(), processingInstruction2.getText());
    }

    public void assertNodesEqual(Namespace namespace, Namespace namespace2) {
        assertEquals("Namespace prefixes not equal", namespace.getPrefix(), namespace2.getPrefix());
        assertEquals("Namespace URIs not equal", namespace.getURI(), namespace2.getURI());
    }

    public void assertNodesEqualContent(Branch branch, Branch branch2) {
        int nodeCount = branch.nodeCount();
        int nodeCount2 = branch2.nodeCount();
        if (nodeCount != nodeCount2) {
            StringBuffer stringBuffer = new StringBuffer("Content of: ");
            stringBuffer.append(branch);
            log(stringBuffer.toString());
            StringBuffer stringBuffer2 = new StringBuffer("is: ");
            stringBuffer2.append(branch.content());
            log(stringBuffer2.toString());
            StringBuffer stringBuffer3 = new StringBuffer("Content of: ");
            stringBuffer3.append(branch2);
            log(stringBuffer3.toString());
            StringBuffer stringBuffer4 = new StringBuffer("is: ");
            stringBuffer4.append(branch2.content());
            log(stringBuffer4.toString());
        }
        StringBuffer stringBuffer5 = new StringBuffer("Branches have same number of children (");
        stringBuffer5.append(nodeCount);
        stringBuffer5.append(", ");
        stringBuffer5.append(nodeCount2);
        stringBuffer5.append(" for: ");
        stringBuffer5.append(branch);
        stringBuffer5.append(" and ");
        stringBuffer5.append(branch2);
        assertEquals(stringBuffer5.toString(), nodeCount, nodeCount2);
        for (int i = 0; i < nodeCount; i++) {
            assertNodesEqual(branch.node(i), branch2.node(i));
        }
    }

    public void assertNodesEqual(Node node, Node node2) {
        short nodeType = node.getNodeType();
        assertTrue("Nodes are of same type: ", nodeType == node2.getNodeType());
        switch (nodeType) {
            case 1:
                assertNodesEqual((Element) node, (Element) node2);
                return;
            case 2:
                assertNodesEqual((Attribute) node, (Attribute) node2);
                return;
            case 3:
                assertNodesEqual((CharacterData) (Text) node, (CharacterData) (Text) node2);
                return;
            case 4:
                assertNodesEqual((CharacterData) (CDATA) node, (CharacterData) (CDATA) node2);
                return;
            case 5:
                assertNodesEqual((Entity) node, (Entity) node2);
                return;
            case 7:
                assertNodesEqual((ProcessingInstruction) node, (ProcessingInstruction) node2);
                return;
            case 8:
                assertNodesEqual((CharacterData) (Comment) node, (CharacterData) (Comment) node2);
                return;
            case 9:
                assertNodesEqual((Document) node, (Document) node2);
                return;
            case 10:
                assertNodesEqual((DocumentType) node, (DocumentType) node2);
                return;
            case 13:
                assertNodesEqual((Namespace) node, (Namespace) node2);
                return;
            default:
                StringBuffer stringBuffer = new StringBuffer("Invalid node types. node1: ");
                stringBuffer.append(node);
                stringBuffer.append(" and node2: ");
                stringBuffer.append(node2);
                assertTrue(stringBuffer.toString(), false);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.apache.xerces.jaxp.SAXParserFactoryImpl");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        System.setProperty("javax.xml.parsers.SAXParserFactory", cls.getName());
        Class<?> cls2 = class$1;
        if (cls2 == null) {
            try {
                cls2 = Class.forName("org.apache.xalan.processor.TransformerFactoryImpl");
                class$1 = cls2;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        System.setProperty("javax.xml.transform.TransformerFactory", cls2.getName());
        Document createDocument = DocumentHelper.createDocument();
        this.document = createDocument;
        Element addElement = createDocument.addElement("root");
        addElement.addElement("author").addAttribute("name", "James").addAttribute("location", "UK").addText("James Strachan").addElement("url").addText("http://sourceforge.net/users/jstrachan/");
        addElement.addElement("author").addAttribute("name", "Bob").addAttribute("location", "Canada").addText("Bob McWhirter").addElement("url").addText("http://sourceforge.net/users/werken/");
    }

    /* access modifiers changed from: protected */
    public Element getRootElement() {
        Element rootElement = this.document.getRootElement();
        assertTrue("Document has root element", rootElement != null);
        return rootElement;
    }
}

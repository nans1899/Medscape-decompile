package org.dom4j.xpath;

import com.appboy.Constants;
import java.util.Iterator;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;

public class GetPathTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.GetPathTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testGetPath() throws Exception {
        String str;
        String str2;
        log("Testing paths");
        testPath(this.document, "/");
        Element rootElement = this.document.getRootElement();
        testPath(rootElement, "/root");
        List elements = rootElement.elements();
        testPath((Node) elements.get(0), "/root/author", "/root/author[1]");
        int size = elements.size();
        for (int i = 0; i < size; i++) {
            if (size > 1) {
                StringBuffer stringBuffer = new StringBuffer("/root/author[");
                int i2 = i + 1;
                stringBuffer.append(i2);
                stringBuffer.append("]");
                str2 = stringBuffer.toString();
                StringBuffer stringBuffer2 = new StringBuffer("author[");
                stringBuffer2.append(i2);
                stringBuffer2.append("]");
                str = stringBuffer2.toString();
            } else {
                str2 = "/root/author";
                str = "author";
            }
            Element element = (Element) elements.get(i);
            testPath(element, "/root/author", str2);
            testRelativePath(rootElement, element, "author", str);
            Attribute attribute = element.attribute("name");
            StringBuffer stringBuffer3 = new StringBuffer("/root/author");
            stringBuffer3.append("/@name");
            String stringBuffer4 = stringBuffer3.toString();
            StringBuffer stringBuffer5 = new StringBuffer(String.valueOf(str2));
            stringBuffer5.append("/@name");
            testPath(attribute, stringBuffer4, stringBuffer5.toString());
            StringBuffer stringBuffer6 = new StringBuffer("author");
            stringBuffer6.append("/@name");
            String stringBuffer7 = stringBuffer6.toString();
            StringBuffer stringBuffer8 = new StringBuffer(String.valueOf(str));
            stringBuffer8.append("/@name");
            testRelativePath(rootElement, attribute, stringBuffer7, stringBuffer8.toString());
            Element element2 = element.element("url");
            StringBuffer stringBuffer9 = new StringBuffer("/root/author");
            stringBuffer9.append("/url");
            String stringBuffer10 = stringBuffer9.toString();
            StringBuffer stringBuffer11 = new StringBuffer(String.valueOf(str2));
            stringBuffer11.append("/url");
            testPath(element2, stringBuffer10, stringBuffer11.toString());
            StringBuffer stringBuffer12 = new StringBuffer("author");
            stringBuffer12.append("/url");
            String stringBuffer13 = stringBuffer12.toString();
            StringBuffer stringBuffer14 = new StringBuffer(String.valueOf(str));
            stringBuffer14.append("/url");
            testRelativePath(rootElement, element2, stringBuffer13, stringBuffer14.toString());
        }
    }

    public void testDefaultNamespace() throws Exception {
        Element rootElement = getDocument("/xml/test/defaultNamespace.xml").getRootElement();
        testPath(rootElement, "/*[name()='a']");
        Element element = (Element) rootElement.elements().get(0);
        testPath(element, "/*[name()='a']/*[name()='b']");
        testRelativePath(rootElement, element, "*[name()='b']");
    }

    public void testBug770410() {
        Element addElement = DocumentHelper.createDocument().addElement(Constants.APPBOY_PUSH_CONTENT_KEY).addElement("b");
        addElement.addElement("c");
        addElement.detach();
        assertSame(addElement, addElement.selectSingleNode(addElement.getPath(addElement)));
    }

    public void testBug569927() {
        Document createDocument = DocumentHelper.createDocument();
        Element addElement = createDocument.addElement(DocumentFactory.getInstance().createQName(Constants.APPBOY_PUSH_CONTENT_KEY, "ns", "uri://myuri"));
        QName createQName = DocumentFactory.getInstance().createQName("attribute", "ns", "uri://myuri");
        Attribute attribute = addElement.addAttribute(createQName, "test").attribute(createQName);
        assertSame(attribute, createDocument.selectSingleNode(attribute.getPath()));
        assertSame(attribute, createDocument.selectSingleNode(attribute.getUniquePath()));
    }

    /* access modifiers changed from: protected */
    public void testPath(Node node, String str) {
        testPath(node, str, str);
    }

    /* access modifiers changed from: protected */
    public void testPath(Node node, String str, String str2) {
        assertEquals("getPath expression should be what is expected", str, node.getPath());
        assertEquals("getUniquePath expression should be what is expected", str2, node.getUniquePath());
    }

    /* access modifiers changed from: protected */
    public void testRelativePath(Element element, Node node, String str) {
        testRelativePath(element, node, str, str);
    }

    /* access modifiers changed from: protected */
    public void testRelativePath(Element element, Node node, String str, String str2) {
        assertEquals("relative getPath expression should be what is expected", str, node.getPath(element));
        assertEquals("relative getUniquePath expression not correct", str2, node.getUniquePath(element));
    }

    /* access modifiers changed from: protected */
    public void testBranchPath(Branch branch) {
        testNodePath(branch);
        if (branch instanceof Element) {
            Iterator attributeIterator = ((Element) branch).attributeIterator();
            while (attributeIterator.hasNext()) {
                testNodePath((Node) attributeIterator.next());
            }
        }
        Iterator nodeIterator = branch.nodeIterator();
        while (nodeIterator.hasNext()) {
            Node node = (Node) nodeIterator.next();
            if (node instanceof Branch) {
                testBranchPath((Branch) node);
            } else {
                testNodePath(node);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void testNodePath(Node node) {
        String path = node.getPath();
        StringBuffer stringBuffer = new StringBuffer("Path: ");
        stringBuffer.append(path);
        stringBuffer.append(" node: ");
        stringBuffer.append(node);
        log(stringBuffer.toString());
    }
}

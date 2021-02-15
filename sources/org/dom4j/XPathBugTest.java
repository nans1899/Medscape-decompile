package org.dom4j;

import java.io.PrintStream;
import java.util.List;
import junit.textui.TestRunner;

public class XPathBugTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.XPathBugTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        Element element = (Element) getDocument("/xml/rabo1ae.xml").selectSingleNode("/m:Msg/m:Contents/m:Content");
        boolean z = true;
        assertTrue("root is not null", element != null);
        Namespace namespaceForPrefix = element.getNamespaceForPrefix("ab");
        assertTrue("Found namespace", namespaceForPrefix != null);
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Found: ");
        stringBuffer.append(namespaceForPrefix.getURI());
        printStream.println(stringBuffer.toString());
        Element element2 = (Element) element.selectSingleNode("ab:RaboPayLoad[@id='1234123']");
        if (element2 == null) {
            z = false;
        }
        assertTrue("element is not null", z);
        assertEquals("RateType is correct", "CRRNT", element2.valueOf("ab:AccountingEntry/ab:RateType"));
    }

    public void testRobLebowitz() throws Exception {
        List selectNodes;
        List selectNodes2 = DocumentHelper.parseText("<ul>    <ul>        <li/>            <ul>                <li/>            </ul>        <li/>    </ul></ul>").selectNodes("//ul | //ol");
        for (int i = 0; i < selectNodes2.size(); i++) {
            Element element = (Element) selectNodes2.get(i);
            List selectNodes3 = element.selectNodes("ancestor::ul");
            if ((selectNodes3 == null || selectNodes3.size() <= 0) && (selectNodes = element.selectNodes("ancestor::ol")) != null) {
                selectNodes.size();
            }
        }
    }

    public void testStefan() throws Exception {
        DocumentHelper.createXPath("/x").evaluate(DocumentHelper.parseText("<foo>hello</foo>"));
    }

    public void testMikeSkells() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        Element addElement = createDocument.addElement("root");
        addElement.addElement("child1").addElement("child11");
        addElement.addElement("child2").addElement("child21");
        System.out.println(createDocument.asXML());
        XPath createXPath = createDocument.createXPath("/root/child1/child11");
        XPath createXPath2 = createDocument.createXPath("/root/child2/child21");
        Node selectSingleNode = createXPath.selectSingleNode(addElement);
        Node selectSingleNode2 = createXPath2.selectSingleNode(addElement);
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("test1= ");
        stringBuffer.append(createXPath);
        printStream.println(stringBuffer.toString());
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer("test2= ");
        stringBuffer2.append(createXPath2);
        printStream2.println(stringBuffer2.toString());
        PrintStream printStream3 = System.out;
        StringBuffer stringBuffer3 = new StringBuffer("Position1 Xpath = ");
        stringBuffer3.append(selectSingleNode.getUniquePath());
        printStream3.println(stringBuffer3.toString());
        PrintStream printStream4 = System.out;
        StringBuffer stringBuffer4 = new StringBuffer("Position2 Xpath = ");
        stringBuffer4.append(selectSingleNode2.getUniquePath());
        printStream4.println(stringBuffer4.toString());
        PrintStream printStream5 = System.out;
        StringBuffer stringBuffer5 = new StringBuffer("test2.matches(position1) : ");
        stringBuffer5.append(createXPath2.matches(selectSingleNode));
        printStream5.println(stringBuffer5.toString());
        assertTrue("test1.matches(position1)", createXPath.matches(selectSingleNode));
        assertTrue("test2.matches(position2)", createXPath2.matches(selectSingleNode2));
        assertTrue("test2.matches(position1) should be false", !createXPath2.matches(selectSingleNode));
    }
}

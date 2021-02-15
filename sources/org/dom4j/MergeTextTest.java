package org.dom4j;

import java.util.Iterator;
import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;

public class MergeTextTest extends AbstractTestCase {
    private static final String INPUT_XML_FILE = "/xml/test/mergetext.xml";
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.MergeTextTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testNoAdjacentText() throws Exception {
        SAXReader sAXReader = new SAXReader();
        sAXReader.setMergeAdjacentText(true);
        Document document = getDocument(INPUT_XML_FILE, sAXReader);
        checkNoAdjacent(document.getRootElement());
        StringBuffer stringBuffer = new StringBuffer("No adjacent Text nodes in ");
        stringBuffer.append(document.asXML());
        log(stringBuffer.toString());
    }

    private void checkNoAdjacent(Element element) {
        Iterator nodeIterator = element.nodeIterator();
        Node node = null;
        while (nodeIterator.hasNext()) {
            Node node2 = (Node) nodeIterator.next();
            if ((node2 instanceof Text) && node != null && (node instanceof Text)) {
                StringBuffer stringBuffer = new StringBuffer("Node: ");
                stringBuffer.append(node2);
                stringBuffer.append(" is text and so is its ");
                stringBuffer.append("preceding sibling: ");
                stringBuffer.append(node);
                fail(stringBuffer.toString());
            } else if (node2 instanceof Element) {
                checkNoAdjacent((Element) node2);
            }
            node = node2;
        }
    }
}

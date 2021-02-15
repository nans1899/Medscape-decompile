package org.dom4j.datatype;

import java.util.List;
import org.dom4j.AbstractTestCase;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;

public class AbstractDataTypeTestCase extends AbstractTestCase {
    /* access modifiers changed from: protected */
    public void testNodes(String str, Class cls) {
        List<Node> selectNodes = this.document.selectNodes(str);
        assertTrue("Results are not empty", !selectNodes.isEmpty());
        for (Node node : selectNodes) {
            if (node instanceof Element) {
                Element element = (Element) node;
                testDataType(element, element.getData(), cls);
            } else if (node instanceof Attribute) {
                Attribute attribute = (Attribute) node;
                testDataType(attribute, attribute.getData(), cls);
            } else {
                StringBuffer stringBuffer = new StringBuffer("Did not find an attribute or element: ");
                stringBuffer.append(node);
                assertTrue(stringBuffer.toString(), false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void testDataType(Node node, Object obj, Class cls) {
        assertTrue("Data object is not null", obj != null);
        StringBuffer stringBuffer = new StringBuffer("Data object is of the correct type. Expected: ");
        stringBuffer.append(cls.getName());
        stringBuffer.append(" and found: ");
        stringBuffer.append(obj.getClass().getName());
        assertTrue(stringBuffer.toString(), cls.isAssignableFrom(obj.getClass()));
    }
}

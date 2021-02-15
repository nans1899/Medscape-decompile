package org.dom4j;

import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import java.io.OutputStream;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.io.XMLWriter;

public class BackedListTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.BackedListTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        mutate((Element) this.document.selectSingleNode("/root"));
        mutate((Element) this.document.selectSingleNode("//author"));
    }

    public void testAddRemove() throws Exception {
        List elements = ((Element) this.document.selectSingleNode("/root")).elements();
        try {
            elements.add(0, (Element) elements.get(elements.size() - 1));
            fail();
        } catch (IllegalAddException unused) {
        }
    }

    public void testAddWithIndex() throws Exception {
        DocumentFactory instance = DocumentFactory.getInstance();
        Element element = (Element) this.document.selectSingleNode("/root");
        List elements = element.elements();
        assertEquals(2, elements.size());
        elements.add(1, instance.createElement("dummy1"));
        assertEquals(3, element.elements().size());
        List elements2 = element.elements("author");
        assertEquals(2, elements2.size());
        elements2.add(1, instance.createElement("dummy2"));
        List elements3 = element.elements();
        assertEquals(4, elements3.size());
        assertEquals("dummy1", ((Node) elements3.get(1)).getName());
        assertEquals("dummy2", ((Node) elements3.get(2)).getName());
        elements3.add(elements3.size(), instance.createElement("dummy3"));
        List elements4 = element.elements("author");
        elements4.add(elements4.size(), instance.createElement("dummy4"));
    }

    /* access modifiers changed from: protected */
    public void mutate(Element element) throws Exception {
        DocumentFactory instance = DocumentFactory.getInstance();
        List elements = element.elements();
        elements.add(instance.createElement(JSONAPISpecConstants.LAST));
        boolean z = false;
        elements.add(0, instance.createElement(JSONAPISpecConstants.FIRST));
        if (elements.size() == element.elements().size()) {
            z = true;
        }
        assertTrue("Both lists should contain same number of elements", z);
        XMLWriter xMLWriter = new XMLWriter((OutputStream) System.out);
        StringBuffer stringBuffer = new StringBuffer("Element content is now: ");
        stringBuffer.append(element.content());
        log(stringBuffer.toString());
        xMLWriter.write(element);
    }
}

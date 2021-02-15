package org.dom4j.io;

import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;

public class DOMReaderTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.DOMReaderTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testBug972737() throws Exception {
        Document read = new DOMReader().read(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream("<schema targetNamespace='http://SharedTest.org/xsd'         xmlns='http://www.w3.org/2001/XMLSchema'         xmlns:xsd='http://www.w3.org/2001/XMLSchema'>    <complexType name='AllStruct'>        <all>            <element name='arString' type='xsd:string'/>            <element name='varInt' type='xsd:int'/>        </all>    </complexType></schema>".getBytes())));
        assertEquals(2, read.getRootElement().declaredNamespaces().size());
        System.out.println(read.asXML());
    }
}

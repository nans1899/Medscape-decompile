package org.dom4j.io;

import java.io.StringWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class DocumentSourceTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.DocumentSourceTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testBug555549() throws Exception {
        Document parseText = DocumentHelper.parseText("<field id='Description' type='textarea'>line1\r\nline2</field>");
        Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
        StringWriter stringWriter = new StringWriter();
        newTransformer.transform(new DocumentSource(parseText), new StreamResult(stringWriter));
        System.out.println(stringWriter.toString());
        assertTrue(stringWriter.toString().indexOf("&#13") == -1);
    }
}

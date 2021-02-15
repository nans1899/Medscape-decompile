package org.dom4j;

import java.io.StringWriter;
import java.io.Writer;
import junit.textui.TestRunner;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class LineFeedTest extends AbstractTestCase {
    private static final String ATT_TEXT = "Hello&#xa;There&#xa;&lt;&gt;&amp;";
    private static final String EXPECTED_ATT_TEXT = "Hello There <>&";
    private static final String EXPECTED_TEXT = "Hello\nThere\n<>&";
    private static final String TEXT = "Hello\nThere\n&lt;&gt;&amp;";
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.LineFeedTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testElement() throws Exception {
        assertEquals(EXPECTED_TEXT, DocumentHelper.parseText("<elem>Hello\nThere\n&lt;&gt;&amp;</elem>").getRootElement().getText());
    }

    public void testAttribute() throws Exception {
        assertEquals(EXPECTED_ATT_TEXT, DocumentHelper.parseText("<elem attr=\"Hello\nThere\n&lt;&gt;&amp;\"/>").getRootElement().attributeValue("attr"));
        assertEquals(EXPECTED_TEXT, DocumentHelper.parseText("<elem attr=\"Hello&#xa;There&#xa;&lt;&gt;&amp;\"/>").getRootElement().attributeValue("attr"));
    }

    public void testCDATA() throws Exception {
        assertEquals(EXPECTED_TEXT, DocumentHelper.parseText("<elem><![CDATA[Hello\nThere\n<>&]]></elem>").getRootElement().getText());
    }

    public void testXmlWriter() throws Exception {
        Element createElement = DocumentHelper.createElement("elem");
        Document createDocument = DocumentHelper.createDocument(createElement);
        createElement.addCDATA(EXPECTED_TEXT);
        StringWriter stringWriter = new StringWriter();
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter, OutputFormat.createPrettyPrint());
        xMLWriter.write(createDocument);
        xMLWriter.close();
        assertEquals(EXPECTED_TEXT, DocumentHelper.parseText(stringWriter.toString()).getRootElement().getText());
    }
}

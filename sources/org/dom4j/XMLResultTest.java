package org.dom4j;

import java.io.StringWriter;
import java.io.Writer;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import junit.textui.TestRunner;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLResult;
import org.dom4j.io.XMLWriter;

public class XMLResultTest extends AbstractTestCase {
    protected static final boolean VERBOSE = false;
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.XMLResultTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testWriter() throws Exception {
        Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
        DocumentSource documentSource = new DocumentSource(this.document);
        OutputFormat createCompactFormat = OutputFormat.createCompactFormat();
        StringWriter stringWriter = new StringWriter();
        newTransformer.transform(documentSource, new XMLResult((Writer) stringWriter, createCompactFormat));
        String stringWriter2 = stringWriter.toString();
        StringWriter stringWriter3 = new StringWriter();
        new XMLWriter((Writer) stringWriter3, createCompactFormat).write(this.document);
        assertEquals("The text output should be identical", stringWriter3.toString(), stringWriter2);
    }
}

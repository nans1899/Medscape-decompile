package org.dom4j.io;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;

public class XPP3ReaderTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.XPP3ReaderTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testRussian() throws Exception {
        Document read = new XPP3Reader().read(getFile("/xml/russArticle.xml"));
        read.getRootElement();
        StringWriter stringWriter = new StringWriter();
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter);
        OutputFormat.createPrettyPrint().setEncoding("koi8-r");
        xMLWriter.write(read);
        log(stringWriter.toString());
    }

    public void testRussian2() throws Exception {
        Document read = new XPP3Reader().read(getFile("/xml/russArticle.xml"));
        XMLWriter xMLWriter = new XMLWriter(new OutputFormat("", false, "koi8-r"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xMLWriter.setOutputStream(byteArrayOutputStream);
        xMLWriter.write(read);
        xMLWriter.flush();
        xMLWriter.close();
        log(byteArrayOutputStream.toString());
    }
}

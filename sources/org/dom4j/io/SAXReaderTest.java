package org.dom4j.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SAXReaderTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.SAXReaderTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testReadFile() throws Exception {
        new SAXReader().read(getFile("/xml/#.xml"));
    }

    public void testEncoding() throws Exception {
        SAXReader sAXReader = new SAXReader();
        sAXReader.setEncoding("ISO-8859-1");
        assertEquals("encoding incorrect", "ISO-8859-1", sAXReader.read((Reader) new StringReader("<?xml version='1.0' encoding='ISO-8859-1'?><root/>")).getXMLEncoding());
    }

    public void testRussian() throws Exception {
        Document document = getDocument("/xml/russArticle.xml");
        assertEquals("encoding not correct", "koi8-r", document.getXMLEncoding());
        document.getRootElement();
        StringWriter stringWriter = new StringWriter();
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter);
        OutputFormat.createPrettyPrint().setEncoding("koi8-r");
        xMLWriter.write(document);
        log(stringWriter.toString());
    }

    public void testRussian2() throws Exception {
        Document document = getDocument("/xml/russArticle.xml");
        XMLWriter xMLWriter = new XMLWriter(new OutputFormat("", false, "koi8-r"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        xMLWriter.setOutputStream(byteArrayOutputStream);
        xMLWriter.write(document);
        xMLWriter.flush();
        xMLWriter.close();
        log(byteArrayOutputStream.toString());
    }

    public void testBug833765() throws Exception {
        SAXReader sAXReader = new SAXReader();
        sAXReader.setIncludeExternalDTDDeclarations(true);
        getDocument("/xml/dtd/external.xml", sAXReader);
    }

    public void testBug527062() throws Exception {
        List selectNodes = getDocument("/xml/test/test.xml").selectNodes("//broked/junk");
        for (int i = 0; i < selectNodes.size(); i++) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer("Found node: ");
            stringBuffer.append(((Element) selectNodes.get(i)).getStringValue());
            printStream.println(stringBuffer.toString());
        }
        assertEquals("hi there", ((Element) selectNodes.get(0)).getStringValue());
        assertEquals("hello world", ((Element) selectNodes.get(1)).getStringValue());
    }

    public void testEscapedComment() throws Exception {
        Document parseText = DocumentHelper.parseText("<eg>&lt;!-- &lt;head> &amp; &lt;body> --&gt;</eg>");
        Element rootElement = parseText.getRootElement();
        System.out.println(parseText.asXML());
        assertEquals("<!-- <head> & <body> -->", rootElement.getText());
    }
}

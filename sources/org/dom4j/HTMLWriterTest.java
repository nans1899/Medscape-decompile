package org.dom4j;

import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import junit.textui.TestRunner;
import org.apache.commons.io.IOUtils;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

public class HTMLWriterTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.HTMLWriterTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testWriter() throws Exception {
        Document parseText = DocumentHelper.parseText("<html> <body><![CDATA[First&nbsp;test]]></body> </html>");
        StringWriter stringWriter = new StringWriter();
        new HTMLWriter((Writer) stringWriter).write(parseText);
        String stringWriter2 = stringWriter.toString();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("expects: ");
        stringBuffer.append("\n<html>\n  <body>First&nbsp;test</body>\n</html>\n");
        printStream.println(stringBuffer.toString());
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer("output: ");
        stringBuffer2.append(stringWriter2);
        printStream2.println(stringBuffer2.toString());
        assertEquals("Output is correct", "\n<html>\n  <body>First&nbsp;test</body>\n</html>\n", stringWriter2);
    }

    public void testBug923882() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        Element addElement = createDocument.addElement("root");
        addElement.addText("this is ");
        addElement.addText(" sim");
        addElement.addText("ple text ");
        addElement.addElement("child");
        addElement.addText(" contai");
        addElement.addText("ning spaces and");
        addElement.addText(" multiple textnodes");
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndentSize(4);
        outputFormat.setNewlines(true);
        outputFormat.setTrimText(true);
        outputFormat.setExpandEmptyElements(true);
        StringWriter stringWriter = new StringWriter();
        new HTMLWriter((Writer) stringWriter, outputFormat).write(createDocument);
        String stringWriter2 = stringWriter.toString();
        log(stringWriter2);
        int indexOf = stringWriter2.indexOf("<root");
        int indexOf2 = stringWriter2.indexOf("/root>") + 6;
        StringBuffer stringBuffer = new StringBuffer("<root>this is simple text");
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        stringBuffer.append("    <child></child>containing spaces and multiple textnodes");
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        stringBuffer.append("</root>");
        String stringBuffer2 = stringBuffer.toString();
        System.out.println("Expected:");
        System.out.println(stringBuffer2);
        System.out.println("Obtained:");
        System.out.println(stringWriter2.substring(indexOf, indexOf2));
        assertEquals(stringBuffer2, stringWriter2.substring(indexOf, indexOf2));
    }

    public void testBug923882asWriter() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTMLWriter hTMLWriter = new HTMLWriter((Writer) stringWriter, OutputFormat.createPrettyPrint());
        hTMLWriter.characters("wor".toCharArray(), 0, 3);
        hTMLWriter.characters("d-being-cut".toCharArray(), 0, 11);
        assertEquals("word-being-cut", stringWriter.toString());
        StringWriter stringWriter2 = new StringWriter();
        HTMLWriter hTMLWriter2 = new HTMLWriter((Writer) stringWriter2, OutputFormat.createPrettyPrint());
        hTMLWriter2.characters("    wor".toCharArray(), 0, 7);
        hTMLWriter2.characters("d being    ".toCharArray(), 0, 11);
        hTMLWriter2.characters("  cut".toCharArray(), 0, 5);
        assertEquals("word being cut", stringWriter2.toString());
    }

    public void testBug923882asWriterWithEmptyCharArray() throws Exception {
        StringWriter stringWriter = new StringWriter();
        HTMLWriter hTMLWriter = new HTMLWriter((Writer) stringWriter, OutputFormat.createPrettyPrint());
        hTMLWriter.characters("wor".toCharArray(), 0, 3);
        hTMLWriter.characters(new char[0], 0, 0);
        hTMLWriter.characters("d-being-cut".toCharArray(), 0, 11);
        assertEquals("word-being-cut", stringWriter.toString());
    }

    public void testBug619415() throws Exception {
        Document document = getDocument("/xml/test/dosLineFeeds.xml");
        StringWriter stringWriter = new StringWriter();
        boolean z = false;
        new HTMLWriter((Writer) stringWriter, new OutputFormat("", false)).write(document);
        String stringWriter2 = stringWriter.toString();
        System.out.println(stringWriter2);
        assertTrue(stringWriter2.indexOf("Mary had a little lamb.") > -1);
        if (stringWriter2.indexOf("Hello, this is a test.") > -1) {
            z = true;
        }
        assertTrue(z);
    }
}

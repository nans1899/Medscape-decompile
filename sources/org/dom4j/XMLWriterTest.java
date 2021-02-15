package org.dom4j;

import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import junit.textui.TestRunner;
import net.bytebuddy.asm.Advice;
import org.apache.commons.io.IOUtils;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.BaseElement;
import org.dom4j.tree.DefaultDocument;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class XMLWriterTest extends AbstractTestCase {
    protected static final boolean VERBOSE = false;
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.XMLWriterTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testBug1180791() throws Exception {
        SAXReader sAXReader = new SAXReader();
        Document read = sAXReader.read((Reader) new StringReader("<?xml version=\"1.0\"?><root><foo>bar</foo></root>"));
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setNewlines(true);
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, outputFormat).write(read);
        System.out.println(stringWriter.toString());
        Document read2 = sAXReader.read((Reader) new StringReader(stringWriter.toString()));
        StringWriter stringWriter2 = new StringWriter();
        new XMLWriter((Writer) stringWriter2, outputFormat).write(read2);
        System.out.println(stringWriter2.toString());
    }

    public void testBug1119733() throws Exception {
        Document parseText = DocumentHelper.parseText("<root><code>foo</code> bar</root>");
        StringWriter stringWriter = new StringWriter();
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter, OutputFormat.createPrettyPrint());
        xMLWriter.write(parseText);
        xMLWriter.close();
        String stringWriter2 = stringWriter.toString();
        System.out.println(stringWriter2);
        assertEquals("whitespace problem", -1, stringWriter2.indexOf("</code>bar"));
    }

    public void testBug1119733WithSAXEvents() throws Exception {
        StringWriter stringWriter = new StringWriter();
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter, OutputFormat.createPrettyPrint());
        xMLWriter.startDocument();
        xMLWriter.startElement((String) null, "root", "root", new AttributesImpl());
        xMLWriter.startElement((String) null, "code", "code", new AttributesImpl());
        xMLWriter.characters(new char[]{'f', 'o', 'o'}, 0, 3);
        xMLWriter.endElement((String) null, "code", "code");
        xMLWriter.characters(new char[]{' ', 'b', 'a', Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName.SYMBOL}, 0, 4);
        xMLWriter.endElement((String) null, "root", "root");
        xMLWriter.endDocument();
        xMLWriter.close();
        String stringWriter2 = stringWriter.toString();
        System.out.println(stringWriter2);
        assertEquals("whitespace problem", -1, stringWriter2.indexOf("</code>bar"));
    }

    public void testWriter() throws Exception {
        Document document = this.document;
        StringWriter stringWriter = new StringWriter();
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter);
        xMLWriter.write((Object) document);
        xMLWriter.close();
        assertTrue("Output text is bigger than 10 characters", stringWriter.toString().length() > 10);
    }

    public void testEncodingFormats() throws Exception {
        testEncoding("UTF-8");
        testEncoding("UTF-16");
        testEncoding("ISO-8859-1");
    }

    public void testWritingEmptyElement() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        Element addElement = createDocument.addElement("grandfather");
        Element addElement2 = addElement.addElement("parent");
        addElement2.addElement("child1");
        addElement2.addElement("child2").setText("test");
        addElement.addElement("parent").addElement("child3").setText("test");
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, OutputFormat.createPrettyPrint()).write(createDocument);
        String stringWriter2 = stringWriter.toString();
        System.out.println(stringWriter2);
        assertTrue("child2 not present", stringWriter2.indexOf("<child2>test</child2>") != -1);
    }

    /* access modifiers changed from: protected */
    public void testEncoding(String str) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputFormat createPrettyPrint = OutputFormat.createPrettyPrint();
        createPrettyPrint.setEncoding(str);
        XMLWriter xMLWriter = new XMLWriter((OutputStream) byteArrayOutputStream, createPrettyPrint);
        xMLWriter.write(this.document);
        xMLWriter.close();
        StringBuffer stringBuffer = new StringBuffer("Wrote to encoding: ");
        stringBuffer.append(str);
        log(stringBuffer.toString());
    }

    public void testWriterBug() throws Exception {
        DefaultDocument defaultDocument = new DefaultDocument((Element) new BaseElement("project"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = true;
        new XMLWriter((OutputStream) byteArrayOutputStream, new OutputFormat("\t", true, "ISO-8859-1")).write((Document) defaultDocument);
        Document read = new SAXReader().read((InputStream) new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        if (read.getRootElement() == null) {
            z = false;
        }
        assertTrue("Generated document has a root element", z);
        assertEquals("Generated document has corrent named root element", read.getRootElement().getName(), "project");
    }

    public void testNamespaceBug() throws Exception {
        Document createDocument = DocumentHelper.createDocument();
        createDocument.addElement("root", "ns1").addElement("joe", "ns2").addElement("zot", "ns1");
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, OutputFormat.createPrettyPrint()).write(createDocument);
        Element rootElement = DocumentHelper.parseText(stringWriter.toString()).getRootElement();
        assertEquals("root has incorrect namespace", "ns1", rootElement.getNamespaceURI());
        Element element = (Element) rootElement.elementIterator().next();
        assertEquals("joe has correct namespace", "ns2", element.getNamespaceURI());
        assertEquals("zot has correct namespace", "ns1", ((Element) element.elementIterator().next()).getNamespaceURI());
    }

    public void testContentHandler() throws Exception {
        StringWriter stringWriter = new StringWriter();
        OutputFormat createPrettyPrint = OutputFormat.createPrettyPrint();
        createPrettyPrint.setEncoding("iso-8859-1");
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter, createPrettyPrint);
        generateXML(xMLWriter);
        xMLWriter.close();
        assertEquals("Document contains the correct text", "jeejee", DocumentHelper.parseText(stringWriter.toString()).valueOf("/processes[@name='arvojoo']"));
    }

    public void testWhitespaceBug() throws Exception {
        Document parseText = DocumentHelper.parseText("<notes> This is a      multiline\n\rentry</notes>");
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndentSize(4);
        outputFormat.setNewlines(true);
        outputFormat.setTrimText(true);
        outputFormat.setExpandEmptyElements(true);
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, outputFormat).write(parseText);
        String stringWriter2 = stringWriter.toString();
        log(stringWriter2);
        Document parseText2 = DocumentHelper.parseText(stringWriter2);
        assertEquals("valueOf() returns the correct text padding", "This is a multiline entry", parseText2.valueOf("/notes"));
        assertEquals("getText() returns the correct text padding", "This is a multiline entry", parseText2.getRootElement().getText());
    }

    public void testWhitespaceBug2() throws Exception {
        Document createDocument = DocumentHelper.createDocument();
        Element addElement = createDocument.addElement("root").addElement("meaning");
        addElement.addText("to li");
        addElement.addText("ve");
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setEncoding("UTF-8");
        outputFormat.setIndentSize(4);
        outputFormat.setNewlines(true);
        outputFormat.setTrimText(true);
        outputFormat.setExpandEmptyElements(true);
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, outputFormat).write(createDocument);
        String stringWriter2 = stringWriter.toString();
        log(stringWriter2);
        Document parseText = DocumentHelper.parseText(stringWriter2);
        assertEquals("valueOf() returns the correct text padding", "to live", parseText.valueOf("/root/meaning"));
        assertEquals("getText() returns the correct text padding", "to live", parseText.getRootElement().element("meaning").getText());
    }

    public void testPadding() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        Element addElement = createDocument.addElement("root");
        addElement.addText("prefix    ");
        addElement.addElement("b");
        addElement.addText("      suffix");
        OutputFormat outputFormat = new OutputFormat("", false);
        outputFormat.setOmitEncoding(true);
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setExpandEmptyElements(true);
        outputFormat.setPadText(true);
        outputFormat.setTrimText(true);
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, outputFormat).write(createDocument);
        String stringWriter2 = stringWriter.toString();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("xml: ");
        stringBuffer.append(stringWriter2);
        printStream.println(stringBuffer.toString());
        assertEquals("<root>prefix <b></b> suffix</root>", stringWriter2);
    }

    public void testPadding2() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        Element addElement = createDocument.addElement("root");
        addElement.addText("prefix");
        addElement.addElement("b");
        addElement.addText("suffix");
        OutputFormat outputFormat = new OutputFormat("", false);
        outputFormat.setOmitEncoding(true);
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setExpandEmptyElements(true);
        outputFormat.setPadText(true);
        outputFormat.setTrimText(true);
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, outputFormat).write(createDocument);
        String stringWriter2 = stringWriter.toString();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("xml: ");
        stringBuffer.append(stringWriter2);
        printStream.println(stringBuffer.toString());
        assertEquals("<root>prefix<b></b>suffix</root>", stringWriter2);
    }

    public void testPrettyPrinting() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        createDocument.addElement("summary").addAttribute(OmnitureConstants.OMNITURE_FILTER_DATE, "6/7/8").addElement("orderline").addText("puffins").addElement("ranjit").addComment("Ranjit is a happy Puffin");
        XMLWriter xMLWriter = new XMLWriter((OutputStream) System.out, OutputFormat.createPrettyPrint());
        xMLWriter.write(createDocument);
        Document createDocument2 = DocumentFactory.getInstance().createDocument();
        createDocument2.addElement("summary").addAttribute(OmnitureConstants.OMNITURE_FILTER_DATE, "6/7/8").addElement("orderline").addText("puffins").addElement("ranjit").addComment("Ranjit is a happy Puffin").addComment("another comment").addElement("anotherElement");
        xMLWriter.write(createDocument2);
    }

    public void testAttributeQuotes() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        createDocument.addElement("root").addAttribute("test", "text with ' in it");
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter, OutputFormat.createCompactFormat()).write(createDocument);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root test=\"text with ' in it\"/>", stringWriter.toString());
    }

    public void testBug868408() throws Exception {
        Document document = getDocument("/xml/web.xml");
        assertEquals(document.asXML(), DocumentHelper.parseText(document.asXML()).asXML());
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
        new XMLWriter((Writer) stringWriter, outputFormat).write(createDocument);
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

    public void testEscapeXML() throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputFormat outputFormat = new OutputFormat((String) null, false, "ISO-8859-2");
        outputFormat.setSuppressDeclaration(true);
        XMLWriter xMLWriter = new XMLWriter((OutputStream) byteArrayOutputStream, outputFormat);
        Document createDocument = DocumentFactory.getInstance().createDocument();
        createDocument.addElement("root").setText("bla &#c bla");
        xMLWriter.write(createDocument);
        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
        System.out.println(byteArrayOutputStream2);
        Document parseText = DocumentHelper.parseText(byteArrayOutputStream2);
        parseText.normalize();
        System.out.println(parseText.getRootElement().getText());
        assertNodesEqual(createDocument, parseText);
    }

    public void testWriteEntities() throws Exception {
        SAXReader sAXReader = new SAXReader("org.apache.xerces.parsers.SAXParser");
        sAXReader.setIncludeInternalDTDDeclarations(true);
        Document read = sAXReader.read((Reader) new StringReader("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<!DOCTYPE xml [<!ENTITY copy \"&#169;\"> <!ENTITY trade \"&#8482;\"> <!ENTITY deg \"&#x00b0;\"> <!ENTITY gt \"&#62;\"> <!ENTITY sup2 \"&#x00b2;\"> <!ENTITY frac14 \"&#x00bc;\"> <!ENTITY quot \"&#34;\"> <!ENTITY frac12 \"&#x00bd;\"> <!ENTITY euro \"&#x20ac;\"> <!ENTITY Omega \"&#937;\"> ]>\n<root />"));
        StringWriter stringWriter = new StringWriter();
        new XMLWriter((Writer) stringWriter).write(read);
        String stringWriter2 = stringWriter.toString();
        System.out.println(stringWriter2);
        assertNodesEqual(read, DocumentHelper.parseText(stringWriter2));
    }

    public void testEscapeChars() throws Exception {
        Document createDocument = DocumentFactory.getInstance().createDocument();
        createDocument.addElement("root").setText("blahblah ");
        XMLWriter xMLWriter = new XMLWriter();
        StringWriter stringWriter = new StringWriter();
        xMLWriter.setWriter(stringWriter);
        xMLWriter.setMaximumAllowedCharacter(127);
        xMLWriter.write(createDocument);
        stringWriter.toString();
    }

    public void testEscapeText() throws SAXException {
        StringWriter stringWriter = new StringWriter();
        XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter);
        boolean z = false;
        xMLWriter.setEscapeText(false);
        xMLWriter.startDocument();
        xMLWriter.characters("<test></test>".toCharArray(), 0, 13);
        xMLWriter.endDocument();
        String stringWriter2 = stringWriter.toString();
        System.out.println(stringWriter2);
        if (stringWriter2.indexOf("<test>") != -1) {
            z = true;
        }
        assertTrue(z);
    }

    public void testNullCData() {
        Element createElement = DocumentHelper.createElement("test");
        createElement.add(DocumentHelper.createElement("another").addCDATA((String) null));
        Document createDocument = DocumentHelper.createDocument(createElement);
        assertEquals(-1, createElement.asXML().indexOf("null"));
        assertEquals(-1, createDocument.asXML().indexOf("null"));
        System.out.println(createElement.asXML());
        System.out.println(createDocument.asXML());
    }

    /* access modifiers changed from: protected */
    public void generateXML(ContentHandler contentHandler) throws SAXException {
        contentHandler.startDocument();
        AttributesImpl attributesImpl = new AttributesImpl();
        attributesImpl.clear();
        attributesImpl.addAttribute("", "", "name", "CDATA", "arvojoo");
        contentHandler.startElement("", "", "processes", attributesImpl);
        char[] charArray = "jeejee".toCharArray();
        contentHandler.characters(charArray, 0, charArray.length);
        contentHandler.endElement("", "", "processes");
        contentHandler.endDocument();
    }
}

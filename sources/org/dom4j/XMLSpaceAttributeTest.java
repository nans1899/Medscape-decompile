package org.dom4j;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import junit.textui.TestRunner;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class XMLSpaceAttributeTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.XMLSpaceAttributeTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testWithTextTrimOn() throws Exception {
        Document parseText = DocumentHelper.parseText("<top ><row><col>   This is a test!</col></row><row><col xml:space='preserve' >   This is a test!</col></row><row><col>   This is a test!</col></row></top>");
        ((Element) parseText.selectSingleNode("/top/row[2]/col")).setText("   New Text TrimOn! ");
        Document parseText2 = DocumentHelper.parseText(rewriteToXmlString(parseText, true));
        Element element = (Element) parseText2.selectSingleNode("/top/row[2]/col");
        assertEquals("compared element text expecting whitespace", "   New Text TrimOn! ", element.getText());
        assertEquals("compared element getTextTrim", "New Text TrimOn!", element.getTextTrim());
        assertEquals("compared element text expecting trimmed whitespace", "This is a test!", ((Element) parseText2.selectSingleNode("/top/row[3]/col")).getText());
    }

    public void testWithTextTrimOff() throws Exception {
        Document parseText = DocumentHelper.parseText("<top ><row><col>   This is a test!</col></row><row><col xml:space='preserve' >   This is a test!</col></row><row><col>   This is a test!</col></row></top>");
        ((Element) parseText.selectSingleNode("/top/row[2]/col")).setText("   New Text TrimOff! ");
        assertEquals("compared element text expecting whitespace", "   New Text TrimOff! ", ((Element) DocumentHelper.parseText(rewriteToXmlString(parseText, false)).selectSingleNode("/top/row[2]/col")).getText());
    }

    public void testWithTextTrimOnFollow() throws Exception {
        Document parseText = DocumentHelper.parseText("<top ><row><col>   This is a test!</col></row><row><col xml:space='preserve' ><a><b>   This is embedded!</b></a><a><b>   This is space=preserve too!</b></a></col></row><row><col>   This is a test!</col></row></top>");
        ((Element) parseText.selectSingleNode("/top/row[2]/col/a[1]/b")).setText("   New Text TrimOnFollow! ");
        Document parseText2 = DocumentHelper.parseText(rewriteToXmlString(parseText, true));
        assertEquals("compared element text expecting whitespace", "   New Text TrimOnFollow! ", ((Element) parseText2.selectSingleNode("/top/row[2]/col/a[1]/b")).getText());
        Element element = (Element) parseText2.selectSingleNode("/top/row[2]/col/a[2]/b");
        assertEquals("compared element text follow trimmed whitespace", "   This is space=preserve too!", element.getText());
        assertEquals("compared element getTextTrim", "This is space=preserve too!", element.getTextTrim());
        assertEquals("compared element text follow trimmed whitespace", "This is a test!", ((Element) parseText2.selectSingleNode("/top/row[3]/col")).getText());
    }

    public void testWithTextTrimOnNested() throws Exception {
        Document parseText = DocumentHelper.parseText("<top ><row><col>   This is a test!</col></row><row><col xml:space='preserve' ><a><b>   This is embedded! </b><b xml:space='default' >   This should do global default! </b><b>   This is embedded! </b></a></col></row><row><col>   This is a test!</col></row></top>");
        ((Element) parseText.selectSingleNode("/top/row[2]/col/a[1]/b")).setText("   New Text TrimOnNested! ");
        Document parseText2 = DocumentHelper.parseText(rewriteToXmlString(parseText, true));
        assertEquals("compared element text expecting whitespace", "   New Text TrimOnNested! ", ((Element) parseText2.selectSingleNode("/top/row[2]/col/a[1]/b[1]")).getText());
        assertEquals("compared element text nested trimmed whitespace", "This should do global default!", ((Element) parseText2.selectSingleNode("/top/row[2]/col/a[1]/b[2]")).getText());
        assertEquals("compared element text nested preserved whitespace", "   This is embedded! ", ((Element) parseText2.selectSingleNode("/top/row[2]/col/a[1]/b[3]")).getText());
    }

    private String rewriteToXmlString(Document document, boolean z) throws IOException {
        OutputFormat createCompactFormat = OutputFormat.createCompactFormat();
        createCompactFormat.setIndent(true);
        createCompactFormat.setNewlines(true);
        createCompactFormat.setExpandEmptyElements(false);
        createCompactFormat.setSuppressDeclaration(false);
        createCompactFormat.setOmitEncoding(false);
        createCompactFormat.setEncoding("UTF-8");
        createCompactFormat.setTrimText(z);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        XMLWriter xMLWriter = new XMLWriter(createCompactFormat);
        xMLWriter.setOutputStream(bufferedOutputStream);
        xMLWriter.write(document);
        xMLWriter.close();
        return byteArrayOutputStream.toString();
    }

    public void testWithEscapeTextTrimOn() throws Exception {
        Document parseText = DocumentHelper.parseText(rewriteToXmlString(DocumentHelper.parseText("<top ><row><col>   This is a test!</col></row><row><col xml:space='preserve' >   This is a test!\r\nWith a new line, special character like &amp; , and\t tab.</col></row><row><col>   This is a test!\r\nWith a new line, special character like &amp; , and\t tab.</col></row></top>"), true));
        assertEquals("compared element text expecting whitespace", "   This is a test!\nWith a new line, special character like & , and\t tab.", ((Element) parseText.selectSingleNode("/top/row[2]/col")).getText());
        assertEquals("compared element text expecting whitespace", "This is a test! With a new line, special character like & , and tab.", ((Element) parseText.selectSingleNode("/top/row[3]/col")).getText());
    }
}

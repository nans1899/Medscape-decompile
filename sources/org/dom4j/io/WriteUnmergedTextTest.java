package org.dom4j.io;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;

public class WriteUnmergedTextTest extends AbstractTestCase {
    protected static final boolean VERBOSE = true;
    static /* synthetic */ Class class$0;
    private String inputText = "<?xml version = \"1.0\"?><TestEscapedEntities><TEXT>Test using &lt; &amp; &gt;</TEXT></TestEscapedEntities>";

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.WriteUnmergedTextTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public String readwriteText(OutputFormat outputFormat, boolean z) throws Exception {
        XMLWriter xMLWriter;
        StringWriter stringWriter = new StringWriter();
        StringReader stringReader = new StringReader(this.inputText);
        SAXReader sAXReader = new SAXReader();
        sAXReader.setMergeAdjacentText(z);
        Document read = sAXReader.read((Reader) stringReader);
        if (outputFormat == null) {
            xMLWriter = new XMLWriter((Writer) stringWriter);
        } else {
            xMLWriter = new XMLWriter((Writer) stringWriter, outputFormat);
        }
        xMLWriter.write(read);
        xMLWriter.close();
        return stringWriter.toString();
    }

    public void testWithoutFormatNonMerged() throws Exception {
        boolean z = false;
        String readwriteText = readwriteText((OutputFormat) null, false);
        log("Text output is [");
        log(readwriteText);
        log("]. Done");
        assertTrue("Output text contains \"&amp;\"", readwriteText.lastIndexOf("&amp;") >= 0);
        if (readwriteText.lastIndexOf("&lt;") >= 0) {
            z = true;
        }
        assertTrue("Output text contains \"&lt;\"", z);
    }

    public void testWithCompactFormatNonMerged() throws Exception {
        boolean z = false;
        String readwriteText = readwriteText(OutputFormat.createCompactFormat(), false);
        log("Text output is [");
        log(readwriteText);
        log("]. Done");
        assertTrue("Output text contains \"&amp;\"", readwriteText.lastIndexOf("&amp;") >= 0);
        if (readwriteText.lastIndexOf("&lt;") >= 0) {
            z = true;
        }
        assertTrue("Output text contains \"&lt;\"", z);
    }

    public void testWithPrettyPrintFormatNonMerged() throws Exception {
        boolean z = false;
        String readwriteText = readwriteText(OutputFormat.createPrettyPrint(), false);
        log("Text output is [");
        log(readwriteText);
        log("]. Done");
        assertTrue("Output text contains \"&amp;\"", readwriteText.lastIndexOf("&amp;") >= 0);
        if (readwriteText.lastIndexOf("&lt;") >= 0) {
            z = true;
        }
        assertTrue("Output text contains \"&lt;\"", z);
    }

    public void testWithoutFormatMerged() throws Exception {
        boolean z = true;
        String readwriteText = readwriteText((OutputFormat) null, true);
        log("Text output is [");
        log(readwriteText);
        log("]. Done");
        assertTrue("Output text contains \"&amp;\"", readwriteText.lastIndexOf("&amp;") >= 0);
        if (readwriteText.lastIndexOf("&lt;") < 0) {
            z = false;
        }
        assertTrue("Output text contains \"&lt;\"", z);
    }

    public void testWithCompactFormatMerged() throws Exception {
        boolean z = true;
        String readwriteText = readwriteText(OutputFormat.createCompactFormat(), true);
        log("Text output is [");
        log(readwriteText);
        log("]. Done");
        assertTrue("Output text contains \"&amp;\"", readwriteText.lastIndexOf("&amp;") >= 0);
        if (readwriteText.lastIndexOf("&lt;") < 0) {
            z = false;
        }
        assertTrue("Output text contains \"&lt;\"", z);
    }

    public void testWithPrettyPrintFormatMerged() throws Exception {
        boolean z = true;
        String readwriteText = readwriteText(OutputFormat.createPrettyPrint(), true);
        log("Text output is [");
        log(readwriteText);
        log("]. Done");
        assertTrue("Output text contains \"&amp;\"", readwriteText.lastIndexOf("&amp;") >= 0);
        if (readwriteText.lastIndexOf("&lt;") < 0) {
            z = false;
        }
        assertTrue("Output text contains \"&lt;\"", z);
    }
}

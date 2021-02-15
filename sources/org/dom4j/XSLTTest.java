package org.dom4j;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import junit.textui.TestRunner;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;

public class XSLTTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.XSLTTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testTransform() throws Exception {
        Document transform = transform("/xml/nitf/ashtml.xsl");
        boolean z = true;
        assertTrue("Transformed Document is not null", transform != null);
        assertTrue("At least one <h1>", transform.selectNodes("/html//h1").size() > 0);
        if (transform.selectNodes("//p").size() <= 0) {
            z = false;
        }
        assertTrue("At least one <p>", z);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = getDocument("/xml/nitf/sample.xml");
    }

    /* access modifiers changed from: protected */
    public Document transform(String str) throws Exception {
        assertTrue("Document is not null", this.document != null);
        Transformer newTransformer = TransformerFactory.newInstance().newTransformer(new StreamSource(getFile(str)));
        DocumentSource documentSource = new DocumentSource(this.document);
        DocumentResult documentResult = new DocumentResult();
        newTransformer.transform(documentSource, documentResult);
        return documentResult.getDocument();
    }
}

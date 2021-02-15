package org.dom4j;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.dtd.ElementDecl;
import org.dom4j.io.SAXReader;

public class DocTypeTest extends AbstractTestCase {
    protected static final String INPUT_XML_FILE = "/xml/dtd/internal.xml";
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.DocTypeTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testDocType() throws Exception {
        SAXReader sAXReader = new SAXReader();
        boolean z = true;
        sAXReader.setIncludeInternalDTDDeclarations(true);
        DocumentType docType = getDocument(INPUT_XML_FILE, sAXReader).getDocType();
        assertTrue("Has DOCTYPE", docType != null);
        List internalDeclarations = docType.getInternalDeclarations();
        if (internalDeclarations == null || internalDeclarations.isEmpty()) {
            z = false;
        }
        assertTrue("DOCTYPE has declarations", z);
        ElementDecl elementDecl = (ElementDecl) internalDeclarations.get(0);
        assertEquals("name is correct", "greeting", elementDecl.getName());
        assertEquals("model is correct", "(#PCDATA)", elementDecl.getModel());
        StringBuffer stringBuffer = new StringBuffer("<!ELEMENT ");
        stringBuffer.append(elementDecl.getName());
        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(elementDecl.getModel());
        stringBuffer.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        assertEquals("toString() is correct", stringBuffer.toString(), elementDecl.toString());
    }
}

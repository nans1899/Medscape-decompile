package org.dom4j.datatype;

import junit.textui.TestRunner;
import org.dom4j.DocumentFactory;

public class ManualSchemaTest extends AutoSchemaTest {
    static /* synthetic */ Class class$0;

    /* access modifiers changed from: protected */
    public String getDocumentURI() {
        return "/xml/test/schema/personal.xml";
    }

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.datatype.ManualSchemaTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    /* access modifiers changed from: protected */
    public DocumentFactory loadDocumentFactory() throws Exception {
        DatatypeDocumentFactory datatypeDocumentFactory = new DatatypeDocumentFactory();
        datatypeDocumentFactory.loadSchema(getDocument("/xml/test/schema/personal.xsd"));
        return datatypeDocumentFactory;
    }
}

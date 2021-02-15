package org.dom4j.datatype;

import junit.textui.TestRunner;
import org.dom4j.DocumentFactory;

public class ManualSchemaPrefixTest extends AutoSchemaTest {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.datatype.ManualSchemaPrefixTest");
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
        datatypeDocumentFactory.loadSchema(getDocument("/xml/test/schema/personal-prefix.xsd"));
        return datatypeDocumentFactory;
    }
}

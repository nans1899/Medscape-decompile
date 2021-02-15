package org.dom4j.datatype;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.io.SAXReader;

public class SchemaParseTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.datatype.SchemaParseTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testParseSchema() throws Exception {
        DatatypeDocumentFactory datatypeDocumentFactory = new DatatypeDocumentFactory();
        SAXReader sAXReader = new SAXReader();
        sAXReader.setDocumentFactory(datatypeDocumentFactory);
        datatypeDocumentFactory.loadSchema(getDocument("/xml/test/LuisSchema.xsd", sAXReader));
        log("Loaded the schema");
    }
}

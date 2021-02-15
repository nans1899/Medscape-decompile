package org.dom4j.datatype;

import junit.textui.TestRunner;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;

public class AutoSchemaTest extends AbstractDataTypeTestCase {
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    static /* synthetic */ Class class$2;
    static /* synthetic */ Class class$3;
    static /* synthetic */ Class class$4;

    /* access modifiers changed from: protected */
    public String getDocumentURI() {
        return "/xml/test/schema/personal-schema.xml";
    }

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.datatype.AutoSchemaTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testIntAttribute() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Integer");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//person/@x", cls);
    }

    public void testIntElement() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Integer");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//person/salary", cls);
    }

    public void testString() throws Exception {
        Class<?> cls = class$2;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.String");
                class$2 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//person/note", cls);
    }

    public void testDate() throws Exception {
        Class<?> cls = class$3;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$3 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//person/@d", cls);
    }

    public void testDateTime() throws Exception {
        Class<?> cls = class$3;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$3 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//person/@dt", cls);
    }

    public void testInteger() throws Exception {
        Class<?> cls = class$4;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$4 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//person/@age", cls);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = getDocument(getDocumentURI(), new SAXReader(loadDocumentFactory()));
    }

    /* access modifiers changed from: protected */
    public DocumentFactory loadDocumentFactory() throws Exception {
        return DatatypeDocumentFactory.getInstance();
    }
}

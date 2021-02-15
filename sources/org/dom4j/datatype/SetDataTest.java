package org.dom4j.datatype;

import com.appboy.Constants;
import com.medscape.android.settings.Settings;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.math.BigInteger;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class SetDataTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    static /* synthetic */ Class class$2;
    private DatatypeDocumentFactory factory = new DatatypeDocumentFactory();

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.datatype.SetDataTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testAttribute() throws Exception {
        QName createQName = this.factory.createQName("person");
        QName createQName2 = this.factory.createQName(Settings.AGE);
        Element createElement = this.factory.createElement(createQName);
        createElement.addAttribute(createQName2, UserProfile.PHYSICIAN_ID);
        Attribute attribute = createElement.attribute(createQName2);
        assertTrue("Created DatatypeAttribute not correct", attribute instanceof DatatypeAttribute);
        StringBuffer stringBuffer = new StringBuffer("Found attribute: ");
        stringBuffer.append(attribute);
        log(stringBuffer.toString());
        Object data = attribute.getData();
        BigInteger bigInteger = new BigInteger(UserProfile.PHYSICIAN_ID);
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        assertEquals("Data is correct type", cls, data.getClass());
        assertEquals("Set age correctly", bigInteger, data);
        attribute.setValue("32");
        assertEquals("Set age correctly", new BigInteger("32"), attribute.getData());
        try {
            attribute.setValue("abc");
            fail("Appeared to set an invalid value");
        } catch (IllegalArgumentException unused) {
        }
    }

    public void testAttributeWithNamespace() throws Exception {
        QName createQName = this.factory.createQName("person", Constants.APPBOY_PUSH_TITLE_KEY, "urn://testing");
        QName createQName2 = this.factory.createQName(Settings.AGE, Constants.APPBOY_PUSH_TITLE_KEY, "urn://testing");
        Element createElement = this.factory.createElement(createQName);
        createElement.addAttribute(createQName2, UserProfile.PHYSICIAN_ID);
        Attribute attribute = createElement.attribute(createQName2);
        assertTrue("Created DatatypeAttribute not correct", attribute instanceof DatatypeAttribute);
        StringBuffer stringBuffer = new StringBuffer("Found attribute: ");
        stringBuffer.append(attribute);
        log(stringBuffer.toString());
        Object data = attribute.getData();
        BigInteger bigInteger = new BigInteger(UserProfile.PHYSICIAN_ID);
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        assertEquals("Data is correct type", cls, data.getClass());
        assertEquals("Set age correctly", bigInteger, data);
        attribute.setValue("32");
        assertEquals("Set age correctly", new BigInteger("32"), attribute.getData());
        try {
            attribute.setValue("abc");
            fail("Appeared to set an invalid value");
        } catch (IllegalArgumentException unused) {
        }
    }

    public void testElement() throws Exception {
        QName createQName = this.factory.createQName("person");
        Element addElement = this.factory.createElement(createQName).addElement(this.factory.createQName("numberOfCars"));
        StringBuffer stringBuffer = new StringBuffer("Found element: ");
        stringBuffer.append(addElement);
        log(stringBuffer.toString());
        Short sh = new Short(10);
        addElement.setData(sh);
        Object data = addElement.getData();
        Class<?> cls = class$2;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Short");
                class$2 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        assertEquals("Data is correct type", cls, data.getClass());
        assertEquals("Set cars correctly", sh, data);
        addElement.setData(new Short(32));
        assertEquals("Set cars correctly", new Short(32), addElement.getData());
        addElement.setText(UserProfile.STUDENT_ID);
        assertEquals("Set cars correctly", new Short(34), addElement.getData());
        try {
            addElement.setText("abc");
            fail("Appeared to set an invalid value");
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        Document document = getDocument("/xml/test/schema/personal.xsd");
        this.factory.loadSchema(document);
        this.factory.loadSchema(document, new Namespace(Constants.APPBOY_PUSH_TITLE_KEY, "urn://testing"));
    }
}

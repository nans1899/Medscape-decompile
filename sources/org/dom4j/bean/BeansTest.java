package org.dom4j.bean;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.io.SAXReader;

public class BeansTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.bean.BeansTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testReadXML() throws Exception {
        getDocument("/xml/bean/gui.xml", new SAXReader(BeanDocumentFactory.getInstance()));
    }
}

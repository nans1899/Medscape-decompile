package org.dom4j.tree;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;

public class CDataTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.tree.CDataTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testNullTest() {
        assertEquals("CData not correct", "<![CDATA[]]>", new DefaultCDATA((String) null).asXML());
    }

    public void testNormal() {
        assertEquals("CData not correct", "<![CDATA[sample]]>", new DefaultCDATA("sample").asXML());
    }

    public void testLongCData() throws Exception {
        getDocument("xml/test/longCDATA.xml");
    }
}

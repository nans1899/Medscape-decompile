package org.dom4j;

import junit.textui.TestRunner;

public class SetTextTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.SetTextTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testSetText1() throws Exception {
        Node selectSingleNode = this.document.selectSingleNode("//root/author[1]/url");
        selectSingleNode.setText("newURL");
        assertEquals("newURL", selectSingleNode.getText());
        assertTrue(selectSingleNode instanceof Element);
        assertEquals(0, ((Element) selectSingleNode).elements().size());
    }

    public void testSetText2() throws Exception {
        Node selectSingleNode = this.document.selectSingleNode("//root/author[1]");
        selectSingleNode.setText("Strachem James");
        assertEquals("Strachem James", selectSingleNode.getText());
        assertTrue(selectSingleNode instanceof Element);
        assertEquals(1, ((Element) selectSingleNode).elements().size());
    }
}

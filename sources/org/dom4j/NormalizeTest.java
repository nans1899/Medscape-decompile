package org.dom4j;

import junit.textui.TestRunner;

public class NormalizeTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.NormalizeTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testNormalize() throws Exception {
        String asXML = this.document.asXML();
        this.document.normalize();
        String asXML2 = this.document.asXML();
        StringBuffer stringBuffer = new StringBuffer("Initial: ");
        stringBuffer.append(asXML);
        log(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer("Normalized: ");
        stringBuffer2.append(asXML2);
        log(stringBuffer2.toString());
        assertEquals("Should not trim text", " node ", this.document.valueOf("/dummy/full"));
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = DocumentHelper.parseText("<dummy> <full> node </full> with text <and>another node</and> </dummy>");
    }
}

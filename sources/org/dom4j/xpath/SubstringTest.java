package org.dom4j.xpath;

import java.io.File;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SubstringTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.SubstringTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testSubstring() throws Exception {
        testSubstring("//field[substring(@id,1,2)='11']", new String[]{"1100", "1101"});
        testSubstring("//field[substring(@id,3)='11']", new String[]{"2111", "3111"});
    }

    /* access modifiers changed from: protected */
    public void testSubstring(String str, String[] strArr) throws Exception {
        StringBuffer stringBuffer = new StringBuffer("Using XPath: ");
        stringBuffer.append(str);
        log(stringBuffer.toString());
        List selectNodes = this.document.selectNodes(str);
        StringBuffer stringBuffer2 = new StringBuffer("Found: ");
        stringBuffer2.append(selectNodes);
        log(stringBuffer2.toString());
        int length = strArr.length;
        StringBuffer stringBuffer3 = new StringBuffer("List should contain ");
        stringBuffer3.append(length);
        stringBuffer3.append(" results: ");
        stringBuffer3.append(selectNodes);
        assertTrue(stringBuffer3.toString(), selectNodes.size() == length);
        for (int i = 0; i < length; i++) {
            assertEquals(((Element) selectNodes.get(i)).attributeValue("id"), strArr[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = new SAXReader().read(new File("xml/test/fields.xml"));
    }
}

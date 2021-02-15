package org.dom4j.xpath;

import java.io.File;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.io.SAXReader;

public class SortByTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.SortByTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        List selectNodes = this.document.selectNodes("//SPEAKER", "NAME");
        StringBuffer stringBuffer = new StringBuffer("Number of SPEAKER instances: ");
        stringBuffer.append(selectNodes.size());
        log(stringBuffer.toString());
        List selectNodes2 = this.document.selectNodes("//SPEAKER", ".", true);
        StringBuffer stringBuffer2 = new StringBuffer("Number of distinct SPEAKER instances: ");
        stringBuffer2.append(selectNodes2.size());
        log(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer("Number of distinct SPEAKER instances: ");
        stringBuffer3.append(selectNodes2.size());
        log(stringBuffer3.toString());
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = new SAXReader().read(new File("xml/much_ado.xml"));
    }
}

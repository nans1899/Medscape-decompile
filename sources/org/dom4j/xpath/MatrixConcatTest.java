package org.dom4j.xpath;

import java.io.File;
import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

public class MatrixConcatTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.xpath.MatrixConcatTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testMatrixConcat() throws Exception {
        testMatrixConcat("'EQUITY_',/product/cashflows/CashFlow/XREF", new String[]{"EQUITY_CF1", "EQUITY_CF2", "EQUITY_CF3"});
        testMatrixConcat("'EQUITY_','BAR_',/product/cashflows/CashFlow/XREF", new String[]{"EQUITY_BAR_CF1", "EQUITY_BAR_CF2", "EQUITY_BAR_CF3"});
    }

    /* access modifiers changed from: protected */
    public void testMatrixConcat(String str, String[] strArr) throws Exception {
        StringBuffer stringBuffer = new StringBuffer("Using XPath: ");
        stringBuffer.append(str);
        log(stringBuffer.toString());
        Document document = this.document;
        StringBuffer stringBuffer2 = new StringBuffer("matrix-concat(");
        stringBuffer2.append(str);
        stringBuffer2.append(")");
        List selectNodes = document.selectNodes(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer("Found: ");
        stringBuffer3.append(selectNodes);
        log(stringBuffer3.toString());
        int length = strArr.length;
        StringBuffer stringBuffer4 = new StringBuffer("List should contain ");
        stringBuffer4.append(length);
        stringBuffer4.append(" results: ");
        stringBuffer4.append(selectNodes);
        assertTrue(stringBuffer4.toString(), selectNodes.size() == length);
        for (int i = 0; i < length; i++) {
            assertEquals(selectNodes.get(i), strArr[i]);
        }
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = new SAXReader().read(new File("xml/test/product.xml"));
    }
}

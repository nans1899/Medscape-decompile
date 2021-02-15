package org.dom4j;

import java.util.List;
import junit.textui.TestRunner;
import org.dom4j.util.IndexedDocumentFactory;

public class IndexedElementTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.IndexedElementTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testXPaths() throws Exception {
        testXPath("//author");
    }

    /* access modifiers changed from: protected */
    public void testXPath(String str) {
        String str2;
        List selectNodes = this.document.selectNodes(str);
        StringBuffer stringBuffer = new StringBuffer("Searched path: ");
        stringBuffer.append(str);
        log(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer("Found        : ");
        stringBuffer2.append(selectNodes.size());
        stringBuffer2.append(" result(s)");
        log(stringBuffer2.toString());
        log("Results");
        boolean z = false;
        if (selectNodes == null) {
            log("null");
        } else {
            log("[");
            int size = selectNodes.size();
            for (int i = 0; i < size; i++) {
                Object obj = selectNodes.get(i);
                if (obj instanceof Node) {
                    str2 = ((Node) obj).asXML();
                } else if (obj != null) {
                    str2 = obj.toString();
                } else {
                    str2 = "null";
                }
                StringBuffer stringBuffer3 = new StringBuffer("    ");
                stringBuffer3.append(str2);
                log(stringBuffer3.toString());
            }
            log("]");
        }
        log("...........................................");
        if (selectNodes.size() > 0) {
            z = true;
        }
        assertTrue("Found some results", z);
    }

    /* access modifiers changed from: protected */
    public Document createDocument() {
        return IndexedDocumentFactory.getInstance().createDocument();
    }
}

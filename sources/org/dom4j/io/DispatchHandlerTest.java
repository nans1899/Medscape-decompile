package org.dom4j.io;

import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;

public class DispatchHandlerTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.DispatchHandlerTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testBug611445() throws Exception {
        MyHandler myHandler = new MyHandler((MyHandler) null);
        SAXReader sAXReader = new SAXReader();
        sAXReader.addHandler("/products/product/colour", myHandler);
        sAXReader.read(getFile("/xml/test/sample.xml"));
        assertEquals(3, myHandler.getCount());
        sAXReader.read(getFile("/xml/test/sample.xml"));
        assertEquals(6, myHandler.getCount());
    }

    private static class MyHandler implements ElementHandler {
        private int count;

        public void onEnd(ElementPath elementPath) {
        }

        private MyHandler() {
            this.count = 0;
        }

        /* synthetic */ MyHandler(MyHandler myHandler) {
            this();
        }

        public void onStart(ElementPath elementPath) {
            this.count++;
        }

        /* access modifiers changed from: package-private */
        public int getCount() {
            return this.count;
        }
    }
}

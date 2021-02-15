package org.dom4j;

import java.io.File;
import junit.textui.TestRunner;
import org.apache.commons.io.IOUtils;
import org.dom4j.io.SAXReader;

public class EmbeddedHandlerTest extends AbstractTestCase {
    private static final int MAIN_READER = 0;
    private static final int ON_END_READER = 1;
    static /* synthetic */ Class class$0;
    /* access modifiers changed from: private */
    public StringBuffer[] results = {new StringBuffer(), new StringBuffer()};
    protected int test;
    protected String[] testDocuments = {"xml/test/FranzBeilMain.xml"};

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.EmbeddedHandlerTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testMainReader() throws Exception {
        this.test = 0;
        readDocuments();
    }

    public void testOnEndReader() throws Exception {
        this.test = 1;
        readDocuments();
    }

    public void testBothReaders() throws Exception {
        testMainReader();
        testOnEndReader();
        if (!this.results[0].toString().equals(this.results[1].toString())) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Results of tests should be equal!\n");
            StringBuffer stringBuffer2 = new StringBuffer("Results testMainReader():\n");
            stringBuffer2.append(this.results[0].toString());
            stringBuffer.append(stringBuffer2.toString());
            StringBuffer stringBuffer3 = new StringBuffer("Results testOnEndReader():\n");
            stringBuffer3.append(this.results[1].toString());
            stringBuffer.append(stringBuffer3.toString());
            throw new Exception(stringBuffer.toString());
        }
    }

    private void readDocuments() throws Exception {
        int i = 0;
        while (true) {
            String[] strArr = this.testDocuments;
            if (i < strArr.length) {
                String parent = getFile(strArr[i]).getParent();
                SAXReader sAXReader = new SAXReader();
                sAXReader.addHandler("/main/import", new MainHandler(parent));
                getDocument(this.testDocuments[i], sAXReader);
                i++;
            } else {
                return;
            }
        }
    }

    class MainHandler implements ElementHandler {
        private String mainDir;
        private SAXReader mainReader;

        public void onStart(ElementPath elementPath) {
        }

        public MainHandler(String str) {
            SAXReader sAXReader = new SAXReader();
            this.mainReader = sAXReader;
            this.mainDir = str;
            sAXReader.addHandler("/import/stuff", new EmbeddedHandler());
        }

        public void onEnd(ElementPath elementPath) {
            Element element;
            String value = elementPath.getCurrent().attribute("href").getValue();
            Element current = elementPath.getCurrent();
            Element parent = current.getParent();
            SAXReader sAXReader = new SAXReader();
            sAXReader.addHandler("/import/stuff", new EmbeddedHandler());
            StringBuffer stringBuffer = new StringBuffer(String.valueOf(this.mainDir));
            stringBuffer.append(File.separator);
            stringBuffer.append(value);
            File file = new File(stringBuffer.toString());
            try {
                if (EmbeddedHandlerTest.this.test == 0) {
                    element = this.mainReader.read(file).getRootElement();
                } else {
                    if (EmbeddedHandlerTest.this.test == 1) {
                        element = sAXReader.read(file).getRootElement();
                    }
                    element = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            current.detach();
            parent.add(element);
        }
    }

    public class EmbeddedHandler implements ElementHandler {
        public void onEnd(ElementPath elementPath) {
        }

        public EmbeddedHandler() {
        }

        public void onStart(ElementPath elementPath) {
            StringBuffer stringBuffer = EmbeddedHandlerTest.this.results[EmbeddedHandlerTest.this.test];
            StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(elementPath.getCurrent().attribute("name").getValue()));
            stringBuffer2.append(IOUtils.LINE_SEPARATOR_UNIX);
            stringBuffer.append(stringBuffer2.toString());
        }
    }
}

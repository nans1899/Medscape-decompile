package org.dom4j;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import junit.textui.TestRunner;

public class IteratorTest extends AbstractTestCase {
    private static final int NUMELE = 10;
    static /* synthetic */ Class class$0;
    protected Document iterDocument;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.IteratorTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        Document createDocument = DocumentHelper.createDocument();
        this.iterDocument = createDocument;
        Element addElement = createDocument.addElement("root");
        for (int i = 0; i < 10; i++) {
            addElement.addElement("iterator test").addAttribute("instance", Integer.toString(i));
        }
    }

    public void testElementCount() throws Exception {
        Element rootElement = this.iterDocument.getRootElement();
        boolean z = true;
        assertTrue("Has root element", rootElement != null);
        List elements = rootElement.elements("iterator test");
        int size = elements.size();
        StringBuffer stringBuffer = new StringBuffer("Root has ");
        stringBuffer.append(size);
        stringBuffer.append(" children");
        String stringBuffer2 = stringBuffer.toString();
        if (elements == null || size != 10) {
            z = false;
        }
        assertTrue(stringBuffer2, z);
    }

    public void testPlainIteration() throws Exception {
        Element rootElement = this.iterDocument.getRootElement();
        List elements = rootElement.elements("iterator test");
        Iterator elementIterator = rootElement.elementIterator("iterator test");
        int size = elements.size();
        boolean z = false;
        int i = 0;
        while (elementIterator.hasNext()) {
            Element element = (Element) elementIterator.next();
            StringBuffer stringBuffer = new StringBuffer("instance ");
            stringBuffer.append(element.attribute("instance").getValue());
            stringBuffer.append(" equals ");
            stringBuffer.append(i);
            assertEquals(stringBuffer.toString(), element.attribute("instance").getValue(), Integer.toString(i));
            i++;
        }
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(size));
        stringBuffer2.append(" elements iterated");
        String stringBuffer3 = stringBuffer2.toString();
        if (i == size) {
            z = true;
        }
        assertTrue(stringBuffer3, z);
    }

    public void testSkipAlternates() throws Exception {
        Element rootElement = this.iterDocument.getRootElement();
        List elements = rootElement.elements("iterator test");
        Iterator elementIterator = rootElement.elementIterator("iterator test");
        int size = elements.size();
        boolean z = false;
        int i = 0;
        while (elementIterator.hasNext()) {
            Element element = (Element) elementIterator.next();
            StringBuffer stringBuffer = new StringBuffer("instance ");
            stringBuffer.append(element.attribute("instance").getValue());
            stringBuffer.append(" equals ");
            int i2 = i * 2;
            stringBuffer.append(i2);
            assertEquals(stringBuffer.toString(), element.attribute("instance").getValue(), Integer.toString(i2));
            elementIterator.next();
            i++;
        }
        int i3 = size / 2;
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(i3));
        stringBuffer2.append(" alternate elements iterated");
        String stringBuffer3 = stringBuffer2.toString();
        if (i == i3) {
            z = true;
        }
        assertTrue(stringBuffer3, z);
    }

    public void testNoHasNext() throws Exception {
        Element rootElement = this.iterDocument.getRootElement();
        List elements = rootElement.elements("iterator test");
        Iterator elementIterator = rootElement.elementIterator("iterator test");
        int size = elements.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            Element element = (Element) elementIterator.next();
            StringBuffer stringBuffer = new StringBuffer("instance ");
            stringBuffer.append(element.attribute("instance").getValue());
            stringBuffer.append(" equals ");
            stringBuffer.append(i);
            assertEquals(stringBuffer.toString(), element.attribute("instance").getValue(), Integer.toString(i));
            PrintStream printStream = System.out;
            StringBuffer stringBuffer2 = new StringBuffer("instance ");
            stringBuffer2.append(element.attribute("instance").getValue());
            stringBuffer2.append(" equals ");
            stringBuffer2.append(i);
            printStream.println(stringBuffer2.toString());
        }
        try {
            Element element2 = (Element) elementIterator.next();
            if (element2 != null) {
                StringBuffer stringBuffer3 = new StringBuffer("no more elements,value instead is ");
                stringBuffer3.append(element2.attribute("instance").getValue());
                String stringBuffer4 = stringBuffer3.toString();
                if (element2 == null) {
                    z = true;
                }
                assertTrue(stringBuffer4, z);
            }
        } catch (Exception e) {
            assertTrue("Real iterators throw NoSuchElementException", e instanceof NoSuchElementException);
        }
    }

    public void testExtraHasNexts() throws Exception {
        Element rootElement = this.iterDocument.getRootElement();
        List elements = rootElement.elements("iterator test");
        Iterator elementIterator = rootElement.elementIterator("iterator test");
        int size = elements.size();
        boolean z = false;
        int i = 0;
        while (elementIterator.hasNext()) {
            Element element = (Element) elementIterator.next();
            StringBuffer stringBuffer = new StringBuffer("instance ");
            stringBuffer.append(element.attribute("instance").getValue());
            stringBuffer.append(" equals ");
            stringBuffer.append(i);
            assertEquals(stringBuffer.toString(), element.attribute("instance").getValue(), Integer.toString(i));
            elementIterator.hasNext();
            i++;
        }
        StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(size));
        stringBuffer2.append(" elements iterated with extra hasNexts");
        String stringBuffer3 = stringBuffer2.toString();
        if (i == size) {
            z = true;
        }
        assertTrue(stringBuffer3, z);
    }
}

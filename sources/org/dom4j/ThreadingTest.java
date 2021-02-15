package org.dom4j;

import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.TimedTest;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class ThreadingTest extends AbstractTestCase {
    private static final FieldPosition FIELD_ZERO = new FieldPosition(0);
    private static final ThreadLocal FORMATTER_CACHE = new ThreadLocal();
    private static final String SEPERATOR = " - ";
    static /* synthetic */ Class class$0;

    public ThreadingTest(String str) {
        super(str);
    }

    private static void preformat(StringBuffer stringBuffer, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) FORMATTER_CACHE.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS zzz");
            FORMATTER_CACHE.set(simpleDateFormat);
        }
        stringBuffer.append("[");
        simpleDateFormat.format(date, stringBuffer, FIELD_ZERO);
        stringBuffer.append(" (");
        stringBuffer.append(currentTimeMillis);
        stringBuffer.append(") ]");
        stringBuffer.append(SEPERATOR);
        stringBuffer.append(getThreadId());
        stringBuffer.append(SEPERATOR);
        stringBuffer.append(str);
        stringBuffer.append(SEPERATOR);
    }

    private static String getThreadId() {
        return Thread.currentThread().getName();
    }

    public void testCombo() {
        try {
            System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                Element rootElement = DocumentHelper.parseText("<ROOT xmlns:t0=\"http://www.lse.com/t0\" >  <ctx><type>Context</type></ctx>  <A><B><C><D>This is a TEST</D></C></B></A>  <t0:Signon><A>xyz</A><t0:Cust>customer</t0:Cust></t0:Signon></ROOT>").getRootElement();
                assertEquals("test t0:Signon ", "<t0:Signon xmlns:t0=\"http://www.lse.com/t0\"><A>xyz</A><t0:Cust>customer</t0:Cust></t0:Signon>", rootElement.element(QName.get("Signon", Namespace.get("t0", "http://www.lse.com/t0"))).asXML());
                Element createElement = DocumentHelper.createElement(rootElement.getQName("Test"));
                createElement.setText(String.valueOf(System.currentTimeMillis()));
                rootElement.add(createElement);
                QName qName = rootElement.getQName("Test2");
                Element createElement2 = DocumentHelper.createElement(qName);
                long currentTimeMillis = System.currentTimeMillis();
                createElement2.setText(String.valueOf(currentTimeMillis));
                rootElement.add(createElement2);
                Element element = rootElement.element(qName);
                element.detach();
                element.setQName(qName);
                rootElement.add(element);
                String asXML = element.asXML();
                StringBuffer stringBuffer = new StringBuffer("<Test2>");
                stringBuffer.append(currentTimeMillis);
                stringBuffer.append("</Test2>");
                assertEquals("test Test2 ", stringBuffer.toString(), asXML);
                QName qName2 = rootElement.getQName("Test3");
                Element createElement3 = DocumentHelper.createElement(qName2);
                long currentTimeMillis2 = System.currentTimeMillis();
                createElement3.setText(String.valueOf(currentTimeMillis2));
                rootElement.add(createElement3);
                Element element2 = rootElement.element(qName2);
                element2.detach();
                element2.setQName(qName2);
                rootElement.add(element2);
                String asXML2 = element2.asXML();
                StringBuffer stringBuffer2 = new StringBuffer("<Test3>");
                stringBuffer2.append(currentTimeMillis2);
                stringBuffer2.append("</Test3>");
                assertEquals("test Test3 ", stringBuffer2.toString(), asXML2);
                QName qName3 = element2.getQName("Test4");
                Element createElement4 = DocumentHelper.createElement(qName3);
                long currentTimeMillis3 = System.currentTimeMillis();
                createElement4.setText(String.valueOf(currentTimeMillis3));
                rootElement.add(createElement4);
                Element element3 = rootElement.element(qName3);
                element3.detach();
                element3.setQName(qName3);
                rootElement.add(element3);
                String asXML3 = element3.asXML();
                StringBuffer stringBuffer3 = new StringBuffer("<Test4>");
                stringBuffer3.append(currentTimeMillis3);
                stringBuffer3.append("</Test4>");
                assertEquals("test Test4 ", stringBuffer3.toString(), asXML3);
            }
            System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            StringBuffer stringBuffer4 = new StringBuffer("Exception in test: ");
            stringBuffer4.append(e.getMessage());
            assertTrue(stringBuffer4.toString(), false);
        }
    }

    public void testQNameCache() {
        try {
            System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                Element rootElement = DocumentHelper.parseText("<ROOT xmlns:t0=\"http://www.lse.com/t0\" >  <ctx><type>Context</type></ctx>  <A><B><C><D>This is a TEST</D></C></B></A>  <t0:Signon><A>xyz</A><t0:Cust>customer</t0:Cust></t0:Signon></ROOT>").getRootElement();
                assertEquals("test test ", "<test/>", fetchValue(DocumentHelper.createQName("test")));
                assertEquals("test test again ", "<test/>", fetchValue(DocumentHelper.createQName("test")));
                assertEquals("test t0:Signon ", "<t0:Signon xmlns:t0=\"http://www.lse.com/t0\"/>", fetchValue(rootElement.getQName("t0:Signon")));
            }
            System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            StringBuffer stringBuffer = new StringBuffer("Exception in test: ");
            stringBuffer.append(e.getMessage());
            assertTrue(stringBuffer.toString(), false);
        }
    }

    public String fetchValue(QName qName) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HtmlObject.HtmlMarkUp.OPEN_BRACKER);
        String namespacePrefix = qName.getNamespacePrefix();
        if (namespacePrefix != null && namespacePrefix.length() > 0) {
            stringBuffer.append(namespacePrefix);
            stringBuffer.append(":");
        }
        stringBuffer.append(qName.getName());
        String namespaceURI = qName.getNamespaceURI();
        if (namespaceURI != null && namespaceURI.length() > 0) {
            stringBuffer.append(" xmlns");
            if (namespacePrefix != null && namespacePrefix.length() > 0) {
                stringBuffer.append(":");
                stringBuffer.append(namespacePrefix);
            }
            stringBuffer.append("=\"");
            stringBuffer.append(namespaceURI);
            stringBuffer.append("\"");
        }
        stringBuffer.append("/>");
        return stringBuffer.toString();
    }

    public static Test suite() {
        TestSuite testSuite = new TestSuite();
        testSuite.addTest(makeRepeatedLoadTest(5, 10, "testCombo"));
        testSuite.addTest(makeRepeatedLoadTest(5, 10, "testQNameCache"));
        return testSuite;
    }

    protected static Test makeRepeatedLoadTest(int i, int i2, String str) {
        return new TimedTest(new LoadTest(new RepeatedTest(new ThreadingTest(str), i2), i), (long) ((i * 1000 * i2) + 120000));
    }

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.ThreadingTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }
}

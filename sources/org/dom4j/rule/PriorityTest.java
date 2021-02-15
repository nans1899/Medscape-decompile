package org.dom4j.rule;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.PrintStream;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.DocumentFactory;

public class PriorityTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.rule.PriorityTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testNameNode() throws Exception {
        testPriority("foo", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public void testFilter() throws Exception {
        testPriority("foo[@id='123']", 0.5d);
    }

    public void testURI() throws Exception {
        testPriority("foo:*", -0.25d);
    }

    public void testAnyNode() throws Exception {
        testPriority("*", -0.5d);
    }

    /* access modifiers changed from: protected */
    public void testPriority(String str, double d) throws Exception {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("parsing: ");
        stringBuffer.append(str);
        printStream.println(stringBuffer.toString());
        Pattern createPattern = DocumentFactory.getInstance().createPattern(str);
        double priority = createPattern.getPriority();
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer("expr: ");
        stringBuffer2.append(str);
        stringBuffer2.append(" has priority: ");
        stringBuffer2.append(priority);
        printStream2.println(stringBuffer2.toString());
        PrintStream printStream3 = System.out;
        StringBuffer stringBuffer3 = new StringBuffer("pattern: ");
        stringBuffer3.append(createPattern);
        printStream3.println(stringBuffer3.toString());
        StringBuffer stringBuffer4 = new StringBuffer("expr: ");
        stringBuffer4.append(str);
        assertEquals(stringBuffer4.toString(), new Double(d), new Double(priority));
    }
}

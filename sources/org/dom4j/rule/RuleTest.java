package org.dom4j.rule;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;

public class RuleTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;
    protected DocumentFactory factory = new DocumentFactory();

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.rule.RuleTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testOrder() throws Exception {
        testGreater("foo", "*");
    }

    /* access modifiers changed from: protected */
    public void testGreater(String str, String str2) throws Exception {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("parsing: ");
        stringBuffer.append(str);
        stringBuffer.append(" and ");
        stringBuffer.append(str2);
        printStream.println(stringBuffer.toString());
        Rule createRule = createRule(str);
        Rule createRule2 = createRule(str2);
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer("rule1: ");
        stringBuffer2.append(createRule);
        stringBuffer2.append(" rule2: ");
        stringBuffer2.append(createRule2);
        printStream2.println(stringBuffer2.toString());
        int compareTo = createRule.compareTo(createRule2);
        PrintStream printStream3 = System.out;
        StringBuffer stringBuffer3 = new StringBuffer("Comparison: ");
        stringBuffer3.append(compareTo);
        printStream3.println(stringBuffer3.toString());
        boolean z = true;
        assertTrue("r1 > r2", compareTo > 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(createRule);
        arrayList.add(createRule2);
        Collections.sort(arrayList);
        assertTrue("r2 should be first", arrayList.get(0) == createRule2);
        assertTrue("r1 should be next", arrayList.get(1) == createRule);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(createRule2);
        arrayList2.add(createRule);
        Collections.sort(arrayList2);
        assertTrue("r2 should be first", arrayList2.get(0) == createRule2);
        if (arrayList2.get(1) != createRule) {
            z = false;
        }
        assertTrue("r1 should be next", z);
    }

    public void testDocument() {
        Rule createRule = createRule("/");
        Document createDocument = this.factory.createDocument();
        createDocument.addElement("foo");
        assertTrue("/ matches document", createRule.matches(createDocument));
        assertTrue("/ does not match root element", !createRule.matches(createDocument.getRootElement()));
    }

    public void testTextMatchesCDATA() {
        assertTrue("text() matches CDATA", createRule("text()").matches(this.factory.createCDATA("<>&")));
    }

    /* access modifiers changed from: protected */
    public Rule createRule(String str) {
        return new Rule(this.factory.createPattern(str));
    }
}

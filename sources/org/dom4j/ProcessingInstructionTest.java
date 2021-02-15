package org.dom4j;

import junit.textui.TestRunner;

public class ProcessingInstructionTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.ProcessingInstructionTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testParseValues() {
        ProcessingInstruction createProcessingInstruction = DocumentHelper.createProcessingInstruction("pi", " abc='123' def=\"2!=3\" ghi=' 4 = '");
        assertEquals(3, createProcessingInstruction.getValues().size());
        assertEquals("123", createProcessingInstruction.getValue("abc"));
        assertEquals("2!=3", createProcessingInstruction.getValue("def"));
        assertEquals(" 4 = ", createProcessingInstruction.getValue("ghi"));
    }

    public void testBug787428() {
        assertEquals("/abc/cde[@id='qqq']", DocumentHelper.createProcessingInstruction("merge", "xpath=\"/abc/cde[@id='qqq']\"").getValue("xpath"));
    }
}

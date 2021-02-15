package org.dom4j.tree;

import java.io.PrintStream;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;

public class NamespaceCacheTest extends AbstractTestCase {
    private static final int ITERATIONCOUNT = 10000;
    private static final int THREADCOUNT = 50;
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.tree.NamespaceCacheTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testGetSameNamespaceSingleThread() {
        long currentTimeMillis = System.currentTimeMillis();
        new SameNSTest(this, (SameNSTest) null).run();
        long currentTimeMillis2 = System.currentTimeMillis();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Same NS Single took ");
        stringBuffer.append(currentTimeMillis2 - currentTimeMillis);
        stringBuffer.append(" ms");
        printStream.println(stringBuffer.toString());
    }

    public void testGetSameNamespaceMultiThread() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        runMultiThreadedTest(new SameNSTest(this, (SameNSTest) null));
        long currentTimeMillis2 = System.currentTimeMillis();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Different NS Single took ");
        stringBuffer.append(currentTimeMillis2 - currentTimeMillis);
        stringBuffer.append(" ms");
        printStream.println(stringBuffer.toString());
    }

    public void testGetNewNamespaceSingleThread() {
        long currentTimeMillis = System.currentTimeMillis();
        new DifferentNSTest(this, (DifferentNSTest) null).run();
        long currentTimeMillis2 = System.currentTimeMillis();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Same NS Multi took ");
        stringBuffer.append(currentTimeMillis2 - currentTimeMillis);
        stringBuffer.append(" ms");
        printStream.println(stringBuffer.toString());
    }

    public void testGetNewNamespaceMultiThread() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        runMultiThreadedTest(new DifferentNSTest(this, (DifferentNSTest) null));
        long currentTimeMillis2 = System.currentTimeMillis();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("Different NS Multi took ");
        stringBuffer.append(currentTimeMillis2 - currentTimeMillis);
        stringBuffer.append(" ms");
        printStream.println(stringBuffer.toString());
    }

    private void runMultiThreadedTest(Runnable runnable) throws Exception {
        Thread[] threadArr = new Thread[50];
        for (int i = 0; i < 50; i++) {
            threadArr[i] = new Thread(new SameNSTest(this, (SameNSTest) null));
        }
        for (int i2 = 0; i2 < 50; i2++) {
            threadArr[i2].start();
        }
        for (int i3 = 0; i3 < 50; i3++) {
            threadArr[i3].join();
        }
    }

    private class SameNSTest implements Runnable {
        private SameNSTest() {
        }

        /* synthetic */ SameNSTest(NamespaceCacheTest namespaceCacheTest, SameNSTest sameNSTest) {
            this();
        }

        public void run() {
            NamespaceCache namespaceCache = new NamespaceCache();
            for (int i = 0; i < 10000; i++) {
                namespaceCache.get("prefix", "uri");
            }
        }
    }

    private class DifferentNSTest implements Runnable {
        private DifferentNSTest() {
        }

        /* synthetic */ DifferentNSTest(NamespaceCacheTest namespaceCacheTest, DifferentNSTest differentNSTest) {
            this();
        }

        public void run() {
            NamespaceCache namespaceCache = new NamespaceCache();
            for (int i = 0; i < 10000; i++) {
                namespaceCache.get("prefix", Integer.toString(i));
            }
        }
    }
}

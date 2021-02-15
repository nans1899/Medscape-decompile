package org.dom4j.util;

import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.TimedTest;
import java.io.PrintStream;
import java.util.Map;
import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PerThreadSingletonTest extends TestCase {
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    private static ThreadLocal reference = new ThreadLocal();
    private static SingletonStrategy singleton;

    public PerThreadSingletonTest(String str) {
        super(str);
    }

    public void setUp() throws Exception {
        PerThreadSingletonTest.super.setUp();
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.util.PerThreadSingletonTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        synchronized (cls) {
            if (singleton == null) {
                PerThreadSingleton perThreadSingleton = new PerThreadSingleton();
                singleton = perThreadSingleton;
                Class<?> cls2 = class$1;
                if (cls2 == null) {
                    try {
                        cls2 = Class.forName("java.util.HashMap");
                        class$1 = cls2;
                    } catch (ClassNotFoundException e2) {
                        throw new NoClassDefFoundError(e2.getMessage());
                    }
                }
                perThreadSingleton.setSingletonClassName(cls2.getName());
            }
        }
    }

    public void tearDown() throws Exception {
        PerThreadSingletonTest.super.tearDown();
    }

    public void testInstance() throws Exception {
        String name = Thread.currentThread().getName();
        Map map = (Map) singleton.instance();
        if (map.containsKey(name) || reference.get() == null) {
            map.put(name, "new value");
            reference.set(map);
        } else {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer("tid=");
            stringBuffer.append(name);
            stringBuffer.append(" map=");
            stringBuffer.append(map);
            printStream.println(stringBuffer.toString());
            PrintStream printStream2 = System.out;
            StringBuffer stringBuffer2 = new StringBuffer("reference=");
            stringBuffer2.append(reference);
            printStream2.println(stringBuffer2.toString());
            PrintStream printStream3 = System.out;
            StringBuffer stringBuffer3 = new StringBuffer("singleton=");
            stringBuffer3.append(singleton);
            printStream3.println(stringBuffer3.toString());
            fail("created singleton more than once");
        }
        assertEquals("testInstance", "new value", (String) map.get(name));
        Map map2 = (Map) singleton.instance();
        assertEquals("testInstance", "new value", (String) map2.get(name));
        assertEquals("testInstance reference", reference.get(), map2);
    }

    public static Test suite() {
        TestSuite testSuite = new TestSuite();
        testSuite.addTest(makeRepeatedLoadTest(5, 100, "testInstance"));
        return testSuite;
    }

    protected static Test makeRepeatedLoadTest(int i, int i2, String str) {
        return new TimedTest(new LoadTest(new RepeatedTest(new PerThreadSingletonTest(str), i2), i), (long) ((i * 1000 * i2) + 1200));
    }
}

package org.dom4j.util;

import java.util.Map;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class SimpleSingletonTest extends TestCase {
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    private static Object reference;
    private static SingletonStrategy singleton;

    public SimpleSingletonTest(String str) {
        super(str);
    }

    public void setUp() throws Exception {
        SimpleSingletonTest.super.setUp();
        if (singleton == null) {
            PerThreadSingleton perThreadSingleton = new PerThreadSingleton();
            singleton = perThreadSingleton;
            Class<?> cls = class$0;
            if (cls == null) {
                try {
                    cls = Class.forName("java.util.HashMap");
                    class$0 = cls;
                } catch (ClassNotFoundException e) {
                    throw new NoClassDefFoundError(e.getMessage());
                }
            }
            perThreadSingleton.setSingletonClassName(cls.getName());
        }
    }

    public void tearDown() throws Exception {
        SimpleSingletonTest.super.tearDown();
    }

    public void testFirstInstance() throws Exception {
        Map map = (Map) singleton.instance();
        assertEquals("testInstance", (String) null, (String) map.get("Test"));
        map.put("Test", "new value");
        Map map2 = (Map) singleton.instance();
        reference = map2;
        assertEquals("testFirstInstance", "new value", (String) map2.get("Test"));
    }

    public void testSecondInstance() throws Exception {
        Map map = (Map) singleton.instance();
        assertEquals("testSecondInstance reference", reference, map);
        assertEquals("testInstance", "new value", (String) map.get("Test"));
    }

    public static Test suite() {
        TestSuite testSuite = new TestSuite();
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.util.SimpleSingletonTest");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testSuite.addTest(TestSuite.createTest(cls, "testFirstInstance"));
        Class<?> cls2 = class$1;
        if (cls2 == null) {
            try {
                cls2 = Class.forName("org.dom4j.util.SimpleSingletonTest");
                class$1 = cls2;
            } catch (ClassNotFoundException e2) {
                throw new NoClassDefFoundError(e2.getMessage());
            }
        }
        testSuite.addTest(TestSuite.createTest(cls2, "testSecondInstance"));
        return testSuite;
    }
}

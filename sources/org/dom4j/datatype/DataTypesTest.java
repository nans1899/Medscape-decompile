package org.dom4j.datatype;

import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;

public class DataTypesTest extends AbstractDataTypeTestCase {
    static /* synthetic */ Class class$0;
    static /* synthetic */ Class class$1;
    static /* synthetic */ Class class$10;
    static /* synthetic */ Class class$11;
    static /* synthetic */ Class class$2;
    static /* synthetic */ Class class$3;
    static /* synthetic */ Class class$4;
    static /* synthetic */ Class class$5;
    static /* synthetic */ Class class$6;
    static /* synthetic */ Class class$7;
    static /* synthetic */ Class class$8;
    static /* synthetic */ Class class$9;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.datatype.DataTypesTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testgMonthDay() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//gMonthDayTag", cls);
    }

    public void testgDay() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//gDayTag", cls);
    }

    public void testgMonth() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//gMonthTag", cls);
    }

    public void testDate() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//dateTag", cls);
    }

    public void testTime() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//timeTag", cls);
    }

    public void testDateTime() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//dateTimeTag", cls);
    }

    public void testgYearMonth() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//gYearMonthTag", cls);
    }

    public void testgYear() throws Exception {
        Class<?> cls = class$1;
        if (cls == null) {
            try {
                cls = Class.forName("java.util.Calendar");
                class$1 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//gYearTag", cls);
    }

    public void testBoolean() throws Exception {
        Class<?> cls = class$2;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Boolean");
                class$2 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//booleanTag", cls);
    }

    public void testBase64Binary() throws Exception {
        Class<?> cls = class$3;
        if (cls == null) {
            try {
                cls = Class.forName("[B");
                class$3 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//base64BinaryTag", cls);
    }

    public void testHexBinary() throws Exception {
        Class<?> cls = class$3;
        if (cls == null) {
            try {
                cls = Class.forName("[B");
                class$3 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//hexBinaryTag", cls);
    }

    public void testFloat() throws Exception {
        Class<?> cls = class$4;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Float");
                class$4 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//floatTag", cls);
    }

    public void testDouble() throws Exception {
        Class<?> cls = class$5;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Double");
                class$5 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//doubleTag", cls);
    }

    public void testDecimal() throws Exception {
        Class<?> cls = class$6;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigDecimal");
                class$6 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//decimalTag", cls);
    }

    public void testInteger() throws Exception {
        Class<?> cls = class$7;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$7 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//integerTag", cls);
    }

    public void testNonPositiveInteger() throws Exception {
        Class<?> cls = class$7;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$7 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//nonPositiveIntegerTag", cls);
    }

    public void testNegativeInteger() throws Exception {
        Class<?> cls = class$7;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$7 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//negativeIntegerTag", cls);
    }

    public void testLong() throws Exception {
        Class<?> cls = class$8;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Long");
                class$8 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//longTag", cls);
    }

    public void testInt() throws Exception {
        Class<?> cls = class$9;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Integer");
                class$9 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//intTag", cls);
    }

    public void testShort() throws Exception {
        Class<?> cls = class$10;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Short");
                class$10 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//shortTag", cls);
    }

    public void testByte() throws Exception {
        Class<?> cls = class$11;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Byte");
                class$11 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//byteTag", cls);
    }

    public void testNonNegativeInteger() throws Exception {
        Class<?> cls = class$7;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$7 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//nonNegativeIntegerTag", cls);
    }

    public void testUnsignedLong() throws Exception {
        Class<?> cls = class$7;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$7 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//unsignedLongTag", cls);
    }

    public void testUnsignedInt() throws Exception {
        Class<?> cls = class$8;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Long");
                class$8 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//unsignedIntTag", cls);
    }

    public void testUnsignedShort() throws Exception {
        Class<?> cls = class$9;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Integer");
                class$9 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//unsignedShortTag", cls);
    }

    public void testUnsignedByte() throws Exception {
        Class<?> cls = class$10;
        if (cls == null) {
            try {
                cls = Class.forName("java.lang.Short");
                class$10 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//unsignedByteTag", cls);
    }

    public void testPositiveInteger() throws Exception {
        Class<?> cls = class$7;
        if (cls == null) {
            try {
                cls = Class.forName("java.math.BigInteger");
                class$7 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        testNodes("//positiveIntegerTag", cls);
    }

    /* access modifiers changed from: protected */
    public void setUp() throws Exception {
        super.setUp();
        this.document = getDocument("/xml/test/schema/test.xml", new SAXReader(DatatypeDocumentFactory.getInstance()));
    }
}

package org.objenesis.instantiator.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

public final class ClassDefinitionUtils {
    public static final int ACC_ABSTRACT = 1024;
    public static final int ACC_ANNOTATION = 8192;
    public static final int ACC_ENUM = 16384;
    public static final int ACC_FINAL = 16;
    public static final int ACC_INTERFACE = 512;
    public static final int ACC_PUBLIC = 1;
    public static final int ACC_SUPER = 32;
    public static final int ACC_SYNTHETIC = 4096;
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_InvokeDynamic = 18;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Utf8 = 1;
    public static final byte[] MAGIC = {-54, -2, -70, -66};
    public static final byte OPS_aload_0 = 42;
    public static final byte OPS_areturn = -80;
    public static final byte OPS_dup = 89;
    public static final byte OPS_invokespecial = -73;
    public static final byte OPS_new = -69;
    public static final byte OPS_return = -79;
    private static final ProtectionDomain PROTECTION_DOMAIN = ((ProtectionDomain) AccessController.doPrivileged(new PrivilegedAction<ProtectionDomain>() {
        public ProtectionDomain run() {
            return ClassDefinitionUtils.class.getProtectionDomain();
        }
    }));
    public static final byte[] VERSION = {0, 0, 0, 49};

    private ClassDefinitionUtils() {
    }

    public static <T> Class<T> defineClass(String str, byte[] bArr, ClassLoader classLoader) throws Exception {
        Class<T> defineClass = UnsafeUtils.getUnsafe().defineClass(str, bArr, 0, bArr.length, classLoader, PROTECTION_DOMAIN);
        Class.forName(str, true, classLoader);
        return defineClass;
    }

    public static byte[] readClass(String str) throws IOException {
        byte[] bArr = new byte[2500];
        InputStream resourceAsStream = ClassDefinitionUtils.class.getClassLoader().getResourceAsStream(classNameToResource(str));
        try {
            int read = resourceAsStream.read(bArr);
            if (read < 2500) {
                byte[] bArr2 = new byte[read];
                System.arraycopy(bArr, 0, bArr2, 0, read);
                return bArr2;
            }
            throw new IllegalArgumentException("The class is longer that 2500 bytes which is currently unsupported");
        } finally {
            resourceAsStream.close();
        }
    }

    public static void writeClass(String str, byte[] bArr) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
        try {
            bufferedOutputStream.write(bArr);
        } finally {
            bufferedOutputStream.close();
        }
    }

    public static String classNameToInternalClassName(String str) {
        return str.replace('.', '/');
    }

    public static String classNameToResource(String str) {
        return classNameToInternalClassName(str) + ".class";
    }

    public static <T> Class<T> getExistingClass(ClassLoader classLoader, String str) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}

package org.mozilla.javascript;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.Map;

public class Kit {
    private static Method Throwable_initCause;

    public static int xDigitToInt(int i, int i2) {
        int i3;
        if (i <= 57) {
            i3 = i - 48;
            if (i3 < 0) {
                return -1;
            }
        } else if (i <= 70) {
            if (65 > i) {
                return -1;
            }
            i3 = i - 55;
        } else if (i > 102 || 97 > i) {
            return -1;
        } else {
            i3 = i - 87;
        }
        return i3 | (i2 << 4);
    }

    static {
        try {
            Class<?> classOrNull = classOrNull("java.lang.Throwable");
            Throwable_initCause = classOrNull.getMethod("initCause", new Class[]{classOrNull});
        } catch (Exception unused) {
        }
    }

    public static Class<?> classOrNull(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException | IllegalArgumentException | LinkageError | SecurityException unused) {
            return null;
        }
    }

    public static Class<?> classOrNull(ClassLoader classLoader, String str) {
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException | IllegalArgumentException | LinkageError | SecurityException unused) {
            return null;
        }
    }

    static Object newInstanceOrNull(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException | LinkageError | SecurityException unused) {
            return null;
        }
    }

    static boolean testIfCanLoadRhinoClasses(ClassLoader classLoader) {
        Class<?> cls = ScriptRuntime.ContextFactoryClass;
        return classOrNull(classLoader, cls.getName()) == cls;
    }

    public static RuntimeException initCause(RuntimeException runtimeException, Throwable th) {
        Method method = Throwable_initCause;
        if (method != null) {
            try {
                method.invoke(runtimeException, new Object[]{th});
            } catch (Exception unused) {
            }
        }
        return runtimeException;
    }

    public static Object addListener(Object obj, Object obj2) {
        if (obj2 == null) {
            throw new IllegalArgumentException();
        } else if (obj2 instanceof Object[]) {
            throw new IllegalArgumentException();
        } else if (obj == null) {
            return obj2;
        } else {
            if (!(obj instanceof Object[])) {
                return new Object[]{obj, obj2};
            }
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            if (length >= 2) {
                Object[] objArr2 = new Object[(length + 1)];
                System.arraycopy(objArr, 0, objArr2, 0, length);
                objArr2[length] = obj2;
                return objArr2;
            }
            throw new IllegalArgumentException();
        }
    }

    public static Object removeListener(Object obj, Object obj2) {
        if (obj2 == null) {
            throw new IllegalArgumentException();
        } else if (obj2 instanceof Object[]) {
            throw new IllegalArgumentException();
        } else if (obj == obj2) {
            return null;
        } else {
            if (!(obj instanceof Object[])) {
                return obj;
            }
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            if (length < 2) {
                throw new IllegalArgumentException();
            } else if (length != 2) {
                int i = length;
                do {
                    i--;
                    if (objArr[i] == obj2) {
                        Object[] objArr2 = new Object[(length - 1)];
                        System.arraycopy(objArr, 0, objArr2, 0, i);
                        int i2 = i + 1;
                        System.arraycopy(objArr, i2, objArr2, i, length - i2);
                        return objArr2;
                    }
                } while (i != 0);
                return obj;
            } else if (objArr[1] == obj2) {
                return objArr[0];
            } else {
                if (objArr[0] == obj2) {
                    return objArr[1];
                }
                return obj;
            }
        }
    }

    public static Object getListener(Object obj, int i) {
        if (i == 0) {
            if (obj == null) {
                return null;
            }
            if (!(obj instanceof Object[])) {
                return obj;
            }
            Object[] objArr = (Object[]) obj;
            if (objArr.length >= 2) {
                return objArr[0];
            }
            throw new IllegalArgumentException();
        } else if (i != 1) {
            Object[] objArr2 = (Object[]) obj;
            int length = objArr2.length;
            if (length < 2) {
                throw new IllegalArgumentException();
            } else if (i == length) {
                return null;
            } else {
                return objArr2[i];
            }
        } else if (obj instanceof Object[]) {
            return ((Object[]) obj)[1];
        } else {
            if (obj != null) {
                return null;
            }
            throw new IllegalArgumentException();
        }
    }

    static Object initHash(Map<Object, Object> map, Object obj, Object obj2) {
        synchronized (map) {
            Object obj3 = map.get(obj);
            if (obj3 == null) {
                map.put(obj, obj2);
            } else {
                obj2 = obj3;
            }
        }
        return obj2;
    }

    private static final class ComplexKey {
        private int hash;
        private Object key1;
        private Object key2;

        ComplexKey(Object obj, Object obj2) {
            this.key1 = obj;
            this.key2 = obj2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ComplexKey)) {
                return false;
            }
            ComplexKey complexKey = (ComplexKey) obj;
            if (!this.key1.equals(complexKey.key1) || !this.key2.equals(complexKey.key2)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (this.hash == 0) {
                this.hash = this.key1.hashCode() ^ this.key2.hashCode();
            }
            return this.hash;
        }
    }

    public static Object makeHashKeyFromPair(Object obj, Object obj2) {
        if (obj == null) {
            throw new IllegalArgumentException();
        } else if (obj2 != null) {
            return new ComplexKey(obj, obj2);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String readReader(Reader reader) throws IOException {
        char[] cArr = new char[512];
        int i = 0;
        while (true) {
            int read = reader.read(cArr, i, cArr.length - i);
            if (read < 0) {
                return new String(cArr, 0, i);
            }
            i += read;
            if (i == cArr.length) {
                char[] cArr2 = new char[(cArr.length * 2)];
                System.arraycopy(cArr, 0, cArr2, 0, i);
                cArr = cArr2;
            }
        }
    }

    public static byte[] readStream(InputStream inputStream, int i) throws IOException {
        if (i > 0) {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (true) {
                int read = inputStream.read(bArr, i2, bArr.length - i2);
                if (read < 0) {
                    break;
                }
                i2 += read;
                if (i2 == bArr.length) {
                    byte[] bArr2 = new byte[(bArr.length * 2)];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    bArr = bArr2;
                }
            }
            if (i2 == bArr.length) {
                return bArr;
            }
            byte[] bArr3 = new byte[i2];
            System.arraycopy(bArr, 0, bArr3, 0, i2);
            return bArr3;
        }
        throw new IllegalArgumentException("Bad initialBufferCapacity: " + i);
    }

    public static RuntimeException codeBug() throws RuntimeException {
        IllegalStateException illegalStateException = new IllegalStateException("FAILED ASSERTION");
        illegalStateException.printStackTrace(System.err);
        throw illegalStateException;
    }

    public static RuntimeException codeBug(String str) throws RuntimeException {
        IllegalStateException illegalStateException = new IllegalStateException("FAILED ASSERTION: " + str);
        illegalStateException.printStackTrace(System.err);
        throw illegalStateException;
    }
}

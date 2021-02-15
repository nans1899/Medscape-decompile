package net.bytebuddy.agent;

import java.lang.reflect.InvocationTargetException;

public class Attacher {
    private static final String ATTACH_METHOD_NAME = "attach";
    private static final String DETACH_METHOD_NAME = "detach";
    private static final String LOAD_AGENT_METHOD_NAME = "loadAgent";
    private static final Object STATIC_MEMBER = null;

    private Attacher() {
        throw new UnsupportedOperationException("This class is a utility class and not supposed to be instantiated");
    }

    public static void main(String[] strArr) {
        String str;
        try {
            if (strArr.length >= 4) {
                if (strArr[3].length() != 0) {
                    StringBuilder sb = new StringBuilder(strArr[3].substring(1));
                    for (int i = 4; i < strArr.length; i++) {
                        sb.append(' ');
                        sb.append(strArr[i]);
                    }
                    str = sb.toString();
                    install(Class.forName(strArr[0]), strArr[1], strArr[2], str);
                }
            }
            str = null;
            install(Class.forName(strArr[0]), strArr[1], strArr[2], str);
        } catch (Exception unused) {
            System.exit(1);
        }
    }

    protected static void install(Class<?> cls, String str, String str2, String str3) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object invoke = cls.getMethod(ATTACH_METHOD_NAME, new Class[]{String.class}).invoke(STATIC_MEMBER, new Object[]{str});
        try {
            cls.getMethod(LOAD_AGENT_METHOD_NAME, new Class[]{String.class, String.class}).invoke(invoke, new Object[]{str2, str3});
        } finally {
            cls.getMethod(DETACH_METHOD_NAME, new Class[0]).invoke(invoke, new Object[0]);
        }
    }
}

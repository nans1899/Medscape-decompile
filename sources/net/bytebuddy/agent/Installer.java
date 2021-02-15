package net.bytebuddy.agent;

import java.lang.instrument.Instrumentation;

public class Installer {
    private static volatile Instrumentation instrumentation;

    private Installer() {
        throw new UnsupportedOperationException("This class is a utility class and not supposed to be instantiated");
    }

    public static Instrumentation getInstrumentation() {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new RuntimePermission("getInstrumentation"));
        }
        Instrumentation instrumentation2 = instrumentation;
        if (instrumentation2 != null) {
            return instrumentation2;
        }
        throw new IllegalStateException("The Byte Buddy agent is not loaded or this method is not called via the system class loader");
    }

    public static void premain(String str, Instrumentation instrumentation2) {
        instrumentation = instrumentation2;
    }

    public static void agentmain(String str, Instrumentation instrumentation2) {
        instrumentation = instrumentation2;
    }
}

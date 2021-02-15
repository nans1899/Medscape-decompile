package org.mockito.internal.util;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Platform {
    private static final Pattern JAVA_8_DEV_VERSION_SCHEME = Pattern.compile("1\\.8\\.0b\\d+_u(\\d+)");
    private static final Pattern JAVA_8_RELEASE_VERSION_SCHEME = Pattern.compile("1\\.8\\.0_(\\d+)(?:-ea)?(?:-b\\d+)?");
    public static final String JAVA_VERSION = System.getProperty("java.specification.version");
    public static final String JVM_INFO = System.getProperty("java.vm.info");
    public static final String JVM_NAME = System.getProperty("java.vm.name");
    public static final String JVM_VENDOR = System.getProperty("java.vm.vendor");
    public static final String JVM_VENDOR_VERSION = System.getProperty("java.vm.version");
    public static final String JVM_VERSION = System.getProperty("java.runtime.version");
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String OS_VERSION = System.getProperty("os.version");

    private Platform() {
    }

    public static boolean isAndroid() {
        return System.getProperty("java.vendor", "").toLowerCase(Locale.US).contains("android");
    }

    public static boolean isAndroidMockMakerRequired() {
        return Boolean.getBoolean("org.mockito.mock.android");
    }

    public static String describe() {
        String format = String.format("Java               : %s\nJVM vendor name    : %s\nJVM vendor version : %s\nJVM name           : %s\nJVM version        : %s\nJVM info           : %s\nOS name            : %s\nOS version         : %s\n", new Object[]{JAVA_VERSION, JVM_VENDOR, JVM_VENDOR_VERSION, JVM_NAME, JVM_VERSION, JVM_INFO, OS_NAME, OS_VERSION});
        if (!isAndroid()) {
            return format;
        }
        return StringUtil.join("IMPORTANT INFORMATION FOR ANDROID USERS:", "", "The regular Byte Buddy mock makers cannot generate code on an Android VM!", "To resolve this, please use the 'mockito-android' dependency for your application:", "http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22mockito-android%22%20g%3A%22org.mockito%22", "", format);
    }

    public static boolean isJava8BelowUpdate45() {
        return isJava8BelowUpdate45(JVM_VERSION);
    }

    static boolean isJava8BelowUpdate45(String str) {
        Matcher matcher = JAVA_8_RELEASE_VERSION_SCHEME.matcher(str);
        if (!matcher.matches()) {
            Matcher matcher2 = JAVA_8_DEV_VERSION_SCHEME.matcher(str);
            if (!matcher2.matches()) {
                return Pattern.compile("1\\.8\\.0-b\\d+").matcher(str).matches();
            }
            if (Integer.parseInt(matcher2.group(1)) < 45) {
                return true;
            }
            return false;
        } else if (Integer.parseInt(matcher.group(1)) < 45) {
            return true;
        } else {
            return false;
        }
    }

    public static String warnForVM(String str, String str2, String str3, String str4) {
        return warnForVM(JVM_NAME, str, str2, str3, str4);
    }

    static String warnForVM(String str, String str2, String str3, String str4, String str5) {
        if (str2 == null || !str.contains(str2)) {
            return (str4 == null || !str.contains(str4)) ? "" : str5;
        }
        return str3;
    }
}

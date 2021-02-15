package org.unbescape;

import java.util.Properties;

public final class Unbescape {
    public static final String BUILD_TIMESTAMP;
    public static final String VERSION;
    public static final int VERSION_BUILD;
    public static final int VERSION_MAJOR;
    public static final int VERSION_MINOR;
    public static final String VERSION_TYPE;

    static {
        String str;
        String str2 = null;
        try {
            Properties properties = new Properties();
            properties.load(ClassLoaderUtils.loadResourceAsStream("org/unbescape/unbescape.properties"));
            str = properties.getProperty("version");
            try {
                str2 = properties.getProperty("build.date");
            } catch (Exception unused) {
            }
        } catch (Exception unused2) {
            str = null;
        }
        VERSION = str;
        BUILD_TIMESTAMP = str2;
        if (str == null || str.trim().length() == 0) {
            VERSION_MAJOR = 0;
            VERSION_MINOR = 0;
            VERSION_BUILD = 0;
            VERSION_TYPE = "UNKNOWN";
            return;
        }
        try {
            String str3 = VERSION;
            int indexOf = str3.indexOf(46);
            VERSION_MAJOR = Integer.parseInt(str3.substring(0, indexOf));
            String substring = str3.substring(indexOf + 1);
            int indexOf2 = substring.indexOf(46);
            VERSION_MINOR = Integer.parseInt(substring.substring(0, indexOf2));
            String substring2 = substring.substring(indexOf2 + 1);
            int indexOf3 = substring2.indexOf(46);
            if (indexOf3 < 0) {
                indexOf3 = substring2.indexOf(45);
            }
            VERSION_BUILD = Integer.parseInt(substring2.substring(0, indexOf3));
            VERSION_TYPE = substring2.substring(indexOf3 + 1);
        } catch (Exception unused3) {
            throw new ExceptionInInitializerError("Exception during initialization of Unbescape versioning utilities. Identified Unbescape version is '" + VERSION + "', which does not follow the {major}.{minor}.{build}[.|-]{type} scheme");
        }
    }

    public static boolean isVersionStableRelease() {
        return "RELEASE".equals(VERSION_TYPE);
    }

    private Unbescape() {
    }
}

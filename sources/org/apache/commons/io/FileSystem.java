package org.apache.commons.io;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public enum FileSystem {
    GENERIC(false, false, Integer.MAX_VALUE, Integer.MAX_VALUE, new char[]{0}, new String[0]),
    LINUX(true, true, 255, 4096, new char[]{0, '/'}, new String[0]),
    MAC_OSX(true, true, 255, 1024, new char[]{0, '/', ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER}, new String[0]),
    WINDOWS(false, true, 255, 32000, new char[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, '\"', '*', '/', ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER, '<', '>', '?', '\\', '|'}, new String[]{"AUX", "COM1", "COM2", "COM3", "COM4", "COM5", "COM6", "COM7", "COM8", "COM9", "CON", "LPT1", "LPT2", "LPT3", "LPT4", "LPT5", "LPT6", "LPT7", "LPT8", "LPT9", "NUL", "PRN"});
    
    private static final boolean IS_OS_LINUX = false;
    private static final boolean IS_OS_MAC = false;
    private static final boolean IS_OS_WINDOWS = false;
    private static final String OS_NAME_WINDOWS_PREFIX = "Windows";
    private final boolean casePreserving;
    private final boolean caseSensitive;
    private final char[] illegalFileNameChars;
    private final int maxFileNameLength;
    private final int maxPathLength;
    private final String[] reservedFileNames;

    static {
        IS_OS_LINUX = getOsMatchesName("Linux");
        IS_OS_MAC = getOsMatchesName("Mac");
        IS_OS_WINDOWS = getOsMatchesName(OS_NAME_WINDOWS_PREFIX);
    }

    public static FileSystem getCurrent() {
        if (IS_OS_LINUX) {
            return LINUX;
        }
        if (IS_OS_MAC) {
            return MAC_OSX;
        }
        if (IS_OS_WINDOWS) {
            return WINDOWS;
        }
        return GENERIC;
    }

    private static boolean getOsMatchesName(String str) {
        return isOsNameMatch(getSystemProperty("os.name"), str);
    }

    private static String getSystemProperty(String str) {
        try {
            return System.getProperty(str);
        } catch (SecurityException unused) {
            PrintStream printStream = System.err;
            printStream.println("Caught a SecurityException reading the system property '" + str + "'; the SystemUtils property value will default to null.");
            return null;
        }
    }

    private static boolean isOsNameMatch(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str.toUpperCase(Locale.ROOT).startsWith(str2.toUpperCase(Locale.ROOT));
    }

    private FileSystem(boolean z, boolean z2, int i, int i2, char[] cArr, String[] strArr) {
        this.maxFileNameLength = i;
        this.maxPathLength = i2;
        this.illegalFileNameChars = (char[]) Objects.requireNonNull(cArr, "illegalFileNameChars");
        this.reservedFileNames = (String[]) Objects.requireNonNull(strArr, "reservedFileNames");
        this.caseSensitive = z;
        this.casePreserving = z2;
    }

    public char[] getIllegalFileNameChars() {
        return (char[]) this.illegalFileNameChars.clone();
    }

    public int getMaxFileNameLength() {
        return this.maxFileNameLength;
    }

    public int getMaxPathLength() {
        return this.maxPathLength;
    }

    public String[] getReservedFileNames() {
        return (String[]) this.reservedFileNames.clone();
    }

    public boolean isCasePreserving() {
        return this.casePreserving;
    }

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    private boolean isIllegalFileNameChar(char c) {
        return Arrays.binarySearch(this.illegalFileNameChars, c) >= 0;
    }

    public boolean isLegalFileName(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0 || charSequence.length() > this.maxFileNameLength || isReservedFileName(charSequence)) {
            return false;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (isIllegalFileNameChar(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean isReservedFileName(CharSequence charSequence) {
        return Arrays.binarySearch(this.reservedFileNames, charSequence) >= 0;
    }

    public String toLegalFileName(String str, char c) {
        Object obj;
        if (isIllegalFileNameChar(c)) {
            Object[] objArr = new Object[3];
            if (c == 0) {
                obj = "\\0";
            } else {
                obj = Character.valueOf(c);
            }
            objArr[0] = obj;
            objArr[1] = name();
            objArr[2] = Arrays.toString(this.illegalFileNameChars);
            throw new IllegalArgumentException(String.format("The replacement character '%s' cannot be one of the %s illegal characters: %s", objArr));
        }
        int length = str.length();
        int i = this.maxFileNameLength;
        if (length > i) {
            str = str.substring(0, i);
        }
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (isIllegalFileNameChar(charArray[i2])) {
                charArray[i2] = c;
                z = true;
            }
        }
        return z ? String.valueOf(charArray) : str;
    }
}

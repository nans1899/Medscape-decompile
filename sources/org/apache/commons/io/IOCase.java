package org.apache.commons.io;

import java.util.Objects;

public enum IOCase {
    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM("System", !FilenameUtils.isSystemWindows());
    
    private static final long serialVersionUID = -6343169151696340687L;
    private final String name;
    private final transient boolean sensitive;

    public static IOCase forName(String str) {
        for (IOCase iOCase : values()) {
            if (iOCase.getName().equals(str)) {
                return iOCase;
            }
        }
        throw new IllegalArgumentException("Invalid IOCase name: " + str);
    }

    private IOCase(String str, boolean z) {
        this.name = str;
        this.sensitive = z;
    }

    private Object readResolve() {
        return forName(this.name);
    }

    public String getName() {
        return this.name;
    }

    public boolean isCaseSensitive() {
        return this.sensitive;
    }

    public int checkCompareTo(String str, String str2) {
        Objects.requireNonNull(str, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str.compareTo(str2) : str.compareToIgnoreCase(str2);
    }

    public boolean checkEquals(String str, String str2) {
        Objects.requireNonNull(str, "str1");
        Objects.requireNonNull(str2, "str2");
        return this.sensitive ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public boolean checkStartsWith(String str, String str2) {
        return str.regionMatches(!this.sensitive, 0, str2, 0, str2.length());
    }

    public boolean checkEndsWith(String str, String str2) {
        int length = str2.length();
        return str.regionMatches(!this.sensitive, str.length() - length, str2, 0, length);
    }

    public int checkIndexOf(String str, int i, String str2) {
        int length = str.length() - str2.length();
        if (length < i) {
            return -1;
        }
        while (i <= length) {
            if (checkRegionMatches(str, i, str2)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public boolean checkRegionMatches(String str, int i, String str2) {
        return str.regionMatches(!this.sensitive, i, str2, 0, str2.length());
    }

    public String toString() {
        return this.name;
    }
}

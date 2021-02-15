package com.wbmd.qxcalculator.util;

public class Version implements Comparable<Version> {
    private String version;

    public final String get() {
        return this.version;
    }

    public Version(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Version can not be null");
        } else if (str.matches("[0-9]+(\\.[0-9]+)*")) {
            this.version = str;
        } else {
            throw new IllegalArgumentException("Invalid version format: " + str);
        }
    }

    public int compareTo(Version version2) {
        if (version2 == null) {
            return 1;
        }
        String[] split = get().split("\\.");
        String[] split2 = version2.get().split("\\.");
        int max = Math.max(split.length, split2.length);
        int i = 0;
        while (i < max) {
            int parseInt = i < split.length ? Integer.parseInt(split[i]) : 0;
            int parseInt2 = i < split2.length ? Integer.parseInt(split2[i]) : 0;
            if (parseInt < parseInt2) {
                return -1;
            }
            if (parseInt > parseInt2) {
                return 1;
            }
            i++;
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && compareTo((Version) obj) == 0) {
            return true;
        }
        return false;
    }
}

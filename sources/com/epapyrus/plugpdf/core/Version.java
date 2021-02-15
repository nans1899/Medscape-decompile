package com.epapyrus.plugpdf.core;

class Version {
    private static final String mVerName = "2.9.42.34 (ac97b53.dirty)";

    public static String getVersionName() {
        return mVerName;
    }

    Version() {
    }

    public static int getMajorVersion() {
        return Integer.parseInt(mVerName.split("\\.")[0]);
    }
}

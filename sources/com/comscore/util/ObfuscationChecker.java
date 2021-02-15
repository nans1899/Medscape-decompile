package com.comscore.util;

public class ObfuscationChecker {
    private static final String a = "com.comscore.util";
    private static final String b = "ObfuscationChecker";

    public boolean isCodeObfuscated() {
        Class<ObfuscationChecker> cls = ObfuscationChecker.class;
        return !a.equals(cls.getPackage().getName()) || !b.equals(cls.getSimpleName());
    }
}

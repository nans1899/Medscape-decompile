package org.unbescape;

import java.io.IOException;
import java.io.InputStream;

final class ClassLoaderUtils {
    private static final ClassLoader classClassLoader = getClassClassLoader(ClassLoaderUtils.class);
    private static final ClassLoader systemClassLoader;
    private static final boolean systemClassLoaderAccessibleFromClassClassLoader;

    static {
        ClassLoader systemClassLoader2 = getSystemClassLoader();
        systemClassLoader = systemClassLoader2;
        systemClassLoaderAccessibleFromClassClassLoader = isKnownClassLoaderAccessibleFrom(systemClassLoader2, classClassLoader);
    }

    static InputStream loadResourceAsStream(String str) throws IOException {
        InputStream findResourceAsStream = findResourceAsStream(str);
        if (findResourceAsStream != null) {
            return findResourceAsStream;
        }
        throw new IOException("Could not locate resource '" + str + "' in the application's class path");
    }

    static InputStream findResourceAsStream(String str) {
        ClassLoader classLoader;
        InputStream resourceAsStream;
        InputStream resourceAsStream2;
        InputStream resourceAsStream3;
        ClassLoader threadContextClassLoader = getThreadContextClassLoader();
        if (threadContextClassLoader != null && (resourceAsStream3 = threadContextClassLoader.getResourceAsStream(str)) != null) {
            return resourceAsStream3;
        }
        if (isKnownLeafClassLoader(threadContextClassLoader)) {
            return null;
        }
        ClassLoader classLoader2 = classClassLoader;
        if (classLoader2 != null && classLoader2 != threadContextClassLoader && (resourceAsStream2 = classLoader2.getResourceAsStream(str)) != null) {
            return resourceAsStream2;
        }
        if (systemClassLoaderAccessibleFromClassClassLoader || (classLoader = systemClassLoader) == null || classLoader == threadContextClassLoader || classLoader == classClassLoader || (resourceAsStream = classLoader.getResourceAsStream(str)) == null) {
            return null;
        }
        return resourceAsStream;
    }

    private static ClassLoader getThreadContextClassLoader() {
        try {
            return Thread.currentThread().getContextClassLoader();
        } catch (SecurityException unused) {
            return null;
        }
    }

    private static ClassLoader getClassClassLoader(Class<?> cls) {
        try {
            return cls.getClassLoader();
        } catch (SecurityException unused) {
            return null;
        }
    }

    private static ClassLoader getSystemClassLoader() {
        try {
            return ClassLoader.getSystemClassLoader();
        } catch (SecurityException unused) {
            return null;
        }
    }

    private static boolean isKnownClassLoaderAccessibleFrom(ClassLoader classLoader, ClassLoader classLoader2) {
        if (classLoader2 == null) {
            return false;
        }
        while (classLoader2 != null && classLoader2 != classLoader) {
            try {
                classLoader2 = classLoader2.getParent();
            } catch (SecurityException unused) {
                return false;
            }
        }
        return classLoader2 != null && classLoader2 == classLoader;
    }

    private static boolean isKnownLeafClassLoader(ClassLoader classLoader) {
        if (classLoader != null && isKnownClassLoaderAccessibleFrom(classClassLoader, classLoader)) {
            return systemClassLoaderAccessibleFromClassClassLoader;
        }
        return false;
    }

    private ClassLoaderUtils() {
    }
}

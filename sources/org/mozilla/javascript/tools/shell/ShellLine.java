package org.mozilla.javascript.tools.shell;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Scriptable;

public class ShellLine {
    public static InputStream getStream(Scriptable scriptable) {
        Class<?> classOrNull;
        ClassLoader classLoader = ShellLine.class.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        if (classLoader == null || (classOrNull = Kit.classOrNull(classLoader, "jline.ConsoleReader")) == null) {
            return null;
        }
        try {
            Object newInstance = classOrNull.getConstructor(new Class[0]).newInstance(new Object[0]);
            classOrNull.getMethod("setBellEnabled", new Class[]{Boolean.TYPE}).invoke(newInstance, new Object[]{Boolean.FALSE});
            Class<?> classOrNull2 = Kit.classOrNull(classLoader, "jline.Completor");
            classOrNull.getMethod("addCompletor", new Class[]{classOrNull2}).invoke(newInstance, new Object[]{Proxy.newProxyInstance(classLoader, new Class[]{classOrNull2}, new FlexibleCompletor(classOrNull2, scriptable))});
            return (InputStream) Kit.classOrNull(classLoader, "jline.ConsoleReaderInputStream").getConstructor(new Class[]{classOrNull}).newInstance(new Object[]{newInstance});
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }
}

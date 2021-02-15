package javax.xml.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Properties;

class FactoryFinder {
    static /* synthetic */ Class class$javax$xml$stream$FactoryFinder = null;
    private static boolean debug = false;

    FactoryFinder() {
    }

    static {
        try {
            debug = System.getProperty("xml.stream.debug") != null;
        } catch (Exception unused) {
        }
    }

    private static void debugPrintln(String str) {
        if (debug) {
            PrintStream printStream = System.err;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("STREAM: ");
            stringBuffer.append(str);
            printStream.println(stringBuffer.toString());
        }
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    private static ClassLoader findClassLoader() throws FactoryConfigurationError {
        Class cls;
        try {
            StringBuffer stringBuffer = new StringBuffer();
            if (class$javax$xml$stream$FactoryFinder == null) {
                cls = class$("javax.xml.stream.FactoryFinder");
                class$javax$xml$stream$FactoryFinder = cls;
            } else {
                cls = class$javax$xml$stream$FactoryFinder;
            }
            stringBuffer.append(cls.getName());
            stringBuffer.append("$ClassLoaderFinderConcrete");
            return ((ClassLoaderFinder) Class.forName(stringBuffer.toString()).newInstance()).getContextClassLoader();
        } catch (LinkageError unused) {
            Class cls2 = class$javax$xml$stream$FactoryFinder;
            if (cls2 == null) {
                cls2 = class$("javax.xml.stream.FactoryFinder");
                class$javax$xml$stream$FactoryFinder = cls2;
            }
            return cls2.getClassLoader();
        } catch (ClassNotFoundException unused2) {
            Class cls3 = class$javax$xml$stream$FactoryFinder;
            if (cls3 == null) {
                cls3 = class$("javax.xml.stream.FactoryFinder");
                class$javax$xml$stream$FactoryFinder = cls3;
            }
            return cls3.getClassLoader();
        } catch (Exception e) {
            throw new FactoryConfigurationError(e.toString(), e);
        }
    }

    private static Object newInstance(String str, ClassLoader classLoader) throws FactoryConfigurationError {
        Class<?> cls;
        if (classLoader == null) {
            try {
                cls = Class.forName(str);
            } catch (ClassNotFoundException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Provider ");
                stringBuffer.append(str);
                stringBuffer.append(" not found");
                throw new FactoryConfigurationError(stringBuffer.toString(), (Exception) e);
            } catch (Exception e2) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Provider ");
                stringBuffer2.append(str);
                stringBuffer2.append(" could not be instantiated: ");
                stringBuffer2.append(e2);
                throw new FactoryConfigurationError(stringBuffer2.toString(), e2);
            }
        } else {
            cls = classLoader.loadClass(str);
        }
        return cls.newInstance();
    }

    static Object find(String str) throws FactoryConfigurationError {
        return find(str, (String) null);
    }

    static Object find(String str, String str2) throws FactoryConfigurationError {
        return find(str, str2, findClassLoader());
    }

    static Object find(String str, String str2, ClassLoader classLoader) throws FactoryConfigurationError {
        InputStream inputStream;
        try {
            String property = System.getProperty(str);
            if (property != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("found system property");
                stringBuffer.append(property);
                debugPrintln(stringBuffer.toString());
                return newInstance(property, classLoader);
            }
        } catch (SecurityException unused) {
        }
        try {
            String property2 = System.getProperty("java.home");
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(property2);
            stringBuffer2.append(File.separator);
            stringBuffer2.append("lib");
            stringBuffer2.append(File.separator);
            stringBuffer2.append("jaxp.properties");
            File file = new File(stringBuffer2.toString());
            if (file.exists()) {
                Properties properties = new Properties();
                properties.load(new FileInputStream(file));
                String property3 = properties.getProperty(str);
                if (property3 != null && property3.length() > 0) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("found java.home property ");
                    stringBuffer3.append(property3);
                    debugPrintln(stringBuffer3.toString());
                    return newInstance(property3, classLoader);
                }
            }
        } catch (Exception e) {
            if (debug) {
                e.printStackTrace();
            }
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("META-INF/services/");
        stringBuffer4.append(str);
        String stringBuffer5 = stringBuffer4.toString();
        if (classLoader == null) {
            try {
                inputStream = ClassLoader.getSystemResourceAsStream(stringBuffer5);
            } catch (Exception e2) {
                if (debug) {
                    e2.printStackTrace();
                }
            }
        } else {
            inputStream = classLoader.getResourceAsStream(stringBuffer5);
        }
        if (inputStream != null) {
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append("found ");
            stringBuffer6.append(stringBuffer5);
            debugPrintln(stringBuffer6.toString());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            if (readLine != null && !"".equals(readLine)) {
                StringBuffer stringBuffer7 = new StringBuffer();
                stringBuffer7.append("loaded from services: ");
                stringBuffer7.append(readLine);
                debugPrintln(stringBuffer7.toString());
                return newInstance(readLine, classLoader);
            }
        }
        if (str2 != null) {
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append("loaded from fallback value: ");
            stringBuffer8.append(str2);
            debugPrintln(stringBuffer8.toString());
            return newInstance(str2, classLoader);
        }
        StringBuffer stringBuffer9 = new StringBuffer();
        stringBuffer9.append("Provider for ");
        stringBuffer9.append(str);
        stringBuffer9.append(" cannot be found");
        throw new FactoryConfigurationError(stringBuffer9.toString(), (Exception) null);
    }

    private static abstract class ClassLoaderFinder {
        /* access modifiers changed from: package-private */
        public abstract ClassLoader getContextClassLoader();

        private ClassLoaderFinder() {
        }
    }

    static class ClassLoaderFinderConcrete extends ClassLoaderFinder {
        ClassLoaderFinderConcrete() {
            super();
        }

        /* access modifiers changed from: package-private */
        public ClassLoader getContextClassLoader() {
            return Thread.currentThread().getContextClassLoader();
        }
    }
}

package net.bytebuddy.utility;

import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.NamedElement;

public class JavaModule implements NamedElement.WithOptionalName {
    private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
    public static final JavaModule UNSUPPORTED = null;
    private final Object module;

    protected JavaModule(Object obj) {
        this.module = obj;
    }

    public static JavaModule ofType(Class<?> cls) {
        return DISPATCHER.moduleOf(cls);
    }

    public static JavaModule of(Object obj) {
        if (JavaType.MODULE.isInstance(obj)) {
            return new JavaModule(obj);
        }
        throw new IllegalArgumentException("Not a Java module: " + obj);
    }

    public static boolean isSupported() {
        return DISPATCHER.isAlive();
    }

    public boolean isNamed() {
        return DISPATCHER.isNamed(this.module);
    }

    public String getActualName() {
        return DISPATCHER.getName(this.module);
    }

    public InputStream getResourceAsStream(String str) {
        return DISPATCHER.getResourceAsStream(this.module, str);
    }

    public ClassLoader getClassLoader() {
        return DISPATCHER.getClassLoader(this.module);
    }

    public Object unwrap() {
        return this.module;
    }

    public boolean canRead(JavaModule javaModule) {
        return DISPATCHER.canRead(this.module, javaModule.unwrap());
    }

    public void addReads(Instrumentation instrumentation, JavaModule javaModule) {
        DISPATCHER.addReads(instrumentation, this.module, javaModule.unwrap());
    }

    public int hashCode() {
        return this.module.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof JavaModule)) {
            return false;
        }
        return this.module.equals(((JavaModule) obj).module);
    }

    public String toString() {
        return this.module.toString();
    }

    protected interface Dispatcher {
        void addReads(Instrumentation instrumentation, Object obj, Object obj2);

        boolean canRead(Object obj, Object obj2);

        ClassLoader getClassLoader(Object obj);

        String getName(Object obj);

        InputStream getResourceAsStream(Object obj, String str);

        boolean isAlive();

        boolean isNamed(Object obj);

        JavaModule moduleOf(Class<?> cls);

        public enum CreationAction implements PrivilegedAction<Dispatcher> {
            INSTANCE;

            public Dispatcher run() {
                try {
                    Class<?> cls = Class.forName("java.lang.Module");
                    return new Enabled(Class.class.getMethod("getModule", new Class[0]), cls.getMethod("getClassLoader", new Class[0]), cls.getMethod("isNamed", new Class[0]), cls.getMethod("getName", new Class[0]), cls.getMethod("getResourceAsStream", new Class[]{String.class}), cls.getMethod("canRead", new Class[]{cls}), Instrumentation.class.getMethod("isModifiableModule", new Class[]{cls}), Instrumentation.class.getMethod("redefineModule", new Class[]{cls, Set.class, Map.class, Map.class, Set.class, Map.class}));
                } catch (Exception unused) {
                    return Disabled.INSTANCE;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Enabled implements Dispatcher {
            private static final Object[] NO_ARGUMENTS = new Object[0];
            private final Method canRead;
            private final Method getClassLoader;
            private final Method getModule;
            private final Method getName;
            private final Method getResourceAsStream;
            private final Method isModifiableModule;
            private final Method isNamed;
            private final Method redefineModule;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Enabled enabled = (Enabled) obj;
                return this.getModule.equals(enabled.getModule) && this.getClassLoader.equals(enabled.getClassLoader) && this.isNamed.equals(enabled.isNamed) && this.getName.equals(enabled.getName) && this.getResourceAsStream.equals(enabled.getResourceAsStream) && this.canRead.equals(enabled.canRead) && this.isModifiableModule.equals(enabled.isModifiableModule) && this.redefineModule.equals(enabled.redefineModule);
            }

            public int hashCode() {
                return ((((((((((((((527 + this.getModule.hashCode()) * 31) + this.getClassLoader.hashCode()) * 31) + this.isNamed.hashCode()) * 31) + this.getName.hashCode()) * 31) + this.getResourceAsStream.hashCode()) * 31) + this.canRead.hashCode()) * 31) + this.isModifiableModule.hashCode()) * 31) + this.redefineModule.hashCode();
            }

            public boolean isAlive() {
                return true;
            }

            protected Enabled(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8) {
                this.getModule = method;
                this.getClassLoader = method2;
                this.isNamed = method3;
                this.getName = method4;
                this.getResourceAsStream = method5;
                this.canRead = method6;
                this.isModifiableModule = method7;
                this.redefineModule = method8;
            }

            public JavaModule moduleOf(Class<?> cls) {
                try {
                    return new JavaModule(this.getModule.invoke(cls, NO_ARGUMENTS));
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getModule, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getModule, e2.getCause());
                }
            }

            public InputStream getResourceAsStream(Object obj, String str) {
                try {
                    return (InputStream) this.getResourceAsStream.invoke(obj, new Object[]{str});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getResourceAsStream, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getResourceAsStream, e2.getCause());
                }
            }

            public ClassLoader getClassLoader(Object obj) {
                try {
                    return (ClassLoader) this.getClassLoader.invoke(obj, NO_ARGUMENTS);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getClassLoader, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getClassLoader, e2.getCause());
                }
            }

            public boolean isNamed(Object obj) {
                try {
                    return ((Boolean) this.isNamed.invoke(obj, NO_ARGUMENTS)).booleanValue();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.isNamed, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.isNamed, e2.getCause());
                }
            }

            public String getName(Object obj) {
                try {
                    return (String) this.getName.invoke(obj, NO_ARGUMENTS);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.getName, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.getName, e2.getCause());
                }
            }

            public boolean canRead(Object obj, Object obj2) {
                try {
                    return ((Boolean) this.canRead.invoke(obj, new Object[]{obj2})).booleanValue();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access " + this.canRead, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke " + this.canRead, e2.getCause());
                }
            }

            public void addReads(Instrumentation instrumentation, Object obj, Object obj2) {
                try {
                    if (((Boolean) this.isModifiableModule.invoke(instrumentation, new Object[]{obj})).booleanValue()) {
                        try {
                            this.redefineModule.invoke(instrumentation, new Object[]{obj, Collections.singleton(obj2), Collections.emptyMap(), Collections.emptyMap(), Collections.emptySet(), Collections.emptyMap()});
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access " + this.redefineModule, e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Cannot invoke " + this.redefineModule, e2.getCause());
                        }
                    } else {
                        throw new IllegalStateException(obj + " is not modifiable");
                    }
                } catch (IllegalAccessException e3) {
                    throw new IllegalStateException("Cannot access " + this.redefineModule, e3);
                } catch (InvocationTargetException e4) {
                    throw new IllegalStateException("Cannot invoke " + this.redefineModule, e4.getCause());
                }
            }
        }

        public enum Disabled implements Dispatcher {
            INSTANCE;

            public boolean isAlive() {
                return false;
            }

            public JavaModule moduleOf(Class<?> cls) {
                return JavaModule.UNSUPPORTED;
            }

            public ClassLoader getClassLoader(Object obj) {
                throw new UnsupportedOperationException("Current VM does not support modules");
            }

            public boolean isNamed(Object obj) {
                throw new UnsupportedOperationException("Current VM does not support modules");
            }

            public String getName(Object obj) {
                throw new UnsupportedOperationException("Current VM does not support modules");
            }

            public InputStream getResourceAsStream(Object obj, String str) {
                throw new UnsupportedOperationException("Current VM does not support modules");
            }

            public boolean canRead(Object obj, Object obj2) {
                throw new UnsupportedOperationException("Current VM does not support modules");
            }

            public void addReads(Instrumentation instrumentation, Object obj, Object obj2) {
                throw new UnsupportedOperationException("Current VM does not support modules");
            }
        }
    }
}

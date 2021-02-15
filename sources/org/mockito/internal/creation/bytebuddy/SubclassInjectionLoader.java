package org.mockito.internal.creation.bytebuddy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.mockito.codegen.InjectionBase;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.Platform;
import org.mockito.internal.util.StringUtil;

class SubclassInjectionLoader implements SubclassLoader {
    private static final String ERROR_MESSAGE = StringUtil.join("The current JVM does not support any class injection mechanism.", "", "Currently, Mockito supports injection via neither by method handle lookups or using sun.misc.Unsafe", "Neither seems to be available on your current JVM.");
    private final SubclassLoader loader;

    SubclassInjectionLoader() {
        if (!Boolean.getBoolean("org.mockito.internal.noUnsafeInjection") && ClassInjector.UsingReflection.isAvailable()) {
            this.loader = new WithReflection();
        } else if (ClassInjector.UsingLookup.isAvailable()) {
            this.loader = tryLookup();
        } else {
            throw new MockitoException(StringUtil.join(ERROR_MESSAGE, "", Platform.describe()));
        }
    }

    private static SubclassLoader tryLookup() {
        try {
            Class<?> cls = Class.forName("java.lang.invoke.MethodHandles");
            Object invoke = cls.getMethod("lookup", new Class[0]).invoke((Object) null, new Object[0]);
            Method method = cls.getMethod("privateLookupIn", new Class[]{Class.class, Class.forName("java.lang.invoke.MethodHandles$Lookup")});
            return new WithLookup(invoke, method.invoke((Object) null, new Object[]{InjectionBase.class, invoke}), method);
        } catch (Exception e) {
            throw new MockitoException(StringUtil.join(ERROR_MESSAGE, "", Platform.describe()), e);
        }
    }

    private static class WithReflection implements SubclassLoader {
        public boolean isDisrespectingOpenness() {
            return true;
        }

        private WithReflection() {
        }

        public ClassLoadingStrategy<ClassLoader> resolveStrategy(Class<?> cls, ClassLoader classLoader, boolean z) {
            ClassLoadingStrategy.Default defaultR = ClassLoadingStrategy.Default.INJECTION;
            Class<InjectionBase> cls2 = cls;
            if (!z) {
                cls2 = InjectionBase.class;
            }
            return defaultR.with(cls2.getProtectionDomain());
        }
    }

    private static class WithLookup implements SubclassLoader {
        private final Object codegenLookup;
        private final Object lookup;
        private final Method privateLookupIn;

        public boolean isDisrespectingOpenness() {
            return false;
        }

        WithLookup(Object obj, Object obj2, Method method) {
            this.lookup = obj;
            this.codegenLookup = obj2;
            this.privateLookupIn = method;
        }

        public ClassLoadingStrategy<ClassLoader> resolveStrategy(Class<?> cls, ClassLoader classLoader, boolean z) {
            if (z) {
                try {
                    return ClassLoadingStrategy.UsingLookup.of(this.privateLookupIn.invoke((Object) null, new Object[]{cls, this.lookup}));
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof IllegalAccessException) {
                        return ClassLoadingStrategy.Default.WRAPPER.with(cls.getProtectionDomain());
                    }
                    throw e.getCause();
                } catch (Throwable th) {
                    throw new MockitoException(StringUtil.join("The Java module system prevents Mockito from defining a mock class in the same package as " + cls, "", "To overcome this, you must open and export the mocked type to Mockito.", "Remember that you can also do so programmatically if the mocked class is defined by the same module as your test code", th));
                }
            } else if (classLoader == InjectionBase.class.getClassLoader()) {
                return ClassLoadingStrategy.UsingLookup.of(this.codegenLookup);
            } else {
                return ClassLoadingStrategy.Default.WRAPPER.with(cls.getProtectionDomain());
            }
        }
    }

    public boolean isDisrespectingOpenness() {
        return this.loader.isDisrespectingOpenness();
    }

    public ClassLoadingStrategy<ClassLoader> resolveStrategy(Class<?> cls, ClassLoader classLoader, boolean z) {
        return this.loader.resolveStrategy(cls, classLoader, z);
    }
}

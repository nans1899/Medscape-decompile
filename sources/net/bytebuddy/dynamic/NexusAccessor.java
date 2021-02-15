package net.bytebuddy.dynamic;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
public class NexusAccessor {
    private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
    @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
    private final ReferenceQueue<? super ClassLoader> referenceQueue;

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0027 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r4 != r5) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r5 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.Class r2 = r4.getClass()
            java.lang.Class r3 = r5.getClass()
            if (r2 == r3) goto L_0x0013
            return r1
        L_0x0013:
            java.lang.ref.ReferenceQueue<? super java.lang.ClassLoader> r2 = r4.referenceQueue
            net.bytebuddy.dynamic.NexusAccessor r5 = (net.bytebuddy.dynamic.NexusAccessor) r5
            java.lang.ref.ReferenceQueue<? super java.lang.ClassLoader> r5 = r5.referenceQueue
            if (r5 == 0) goto L_0x0024
            if (r2 == 0) goto L_0x0026
            boolean r5 = r2.equals(r5)
            if (r5 != 0) goto L_0x0027
            return r1
        L_0x0024:
            if (r2 == 0) goto L_0x0027
        L_0x0026:
            return r1
        L_0x0027:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.NexusAccessor.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        ReferenceQueue<? super ClassLoader> referenceQueue2 = this.referenceQueue;
        if (referenceQueue2 != null) {
            return 527 + referenceQueue2.hashCode();
        }
        return 527;
    }

    public NexusAccessor() {
        this(Nexus.NO_QUEUE);
    }

    public NexusAccessor(ReferenceQueue<? super ClassLoader> referenceQueue2) {
        this.referenceQueue = referenceQueue2;
    }

    public static boolean isAlive() {
        return DISPATCHER.isAlive();
    }

    public static void clean(Reference<? extends ClassLoader> reference) {
        DISPATCHER.clean(reference);
    }

    public void register(String str, ClassLoader classLoader, int i, LoadedTypeInitializer loadedTypeInitializer) {
        if (loadedTypeInitializer.isAlive()) {
            DISPATCHER.register(str, classLoader, this.referenceQueue, i, loadedTypeInitializer);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class InitializationAppender implements ByteCodeAppender {
        private final int identification;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.identification == ((InitializationAppender) obj).identification;
        }

        public int hashCode() {
            return 527 + this.identification;
        }

        public InitializationAppender(int i) {
            this.identification = i;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            try {
                StackManipulation[] stackManipulationArr = new StackManipulation[1];
                StackManipulation[] stackManipulationArr2 = new StackManipulation[10];
                stackManipulationArr2[0] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(ClassLoader.class.getMethod("getSystemClassLoader", new Class[0])));
                stackManipulationArr2[1] = new TextConstant(Nexus.class.getName());
                stackManipulationArr2[2] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(ClassLoader.class.getMethod("loadClass", new Class[]{String.class})));
                stackManipulationArr2[3] = new TextConstant("initialize");
                stackManipulationArr2[4] = ArrayFactory.forType(TypeDescription.Generic.CLASS).withValues(Arrays.asList(new StackManipulation[]{ClassConstant.of(TypeDescription.CLASS), ClassConstant.of(TypeDescription.ForLoadedType.of(Integer.TYPE))}));
                stackManipulationArr2[5] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Class.class.getMethod("getMethod", new Class[]{String.class, Class[].class})));
                stackManipulationArr2[6] = NullConstant.INSTANCE;
                ArrayFactory forType = ArrayFactory.forType(TypeDescription.Generic.OBJECT);
                StackManipulation[] stackManipulationArr3 = new StackManipulation[2];
                stackManipulationArr3[0] = ClassConstant.of(methodDescription.getDeclaringType().asErasure());
                StackManipulation[] stackManipulationArr4 = new StackManipulation[2];
                try {
                    stackManipulationArr4[0] = IntegerConstant.forValue(this.identification);
                    stackManipulationArr4[1] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Integer.class.getMethod("valueOf", new Class[]{Integer.TYPE})));
                    stackManipulationArr3[1] = new StackManipulation.Compound(stackManipulationArr4);
                    stackManipulationArr2[7] = forType.withValues(Arrays.asList(stackManipulationArr3));
                    stackManipulationArr2[8] = MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Method.class.getMethod("invoke", new Class[]{Object.class, Object[].class})));
                    stackManipulationArr2[9] = Removal.SINGLE;
                    stackManipulationArr[0] = new StackManipulation.Compound(stackManipulationArr2);
                    return new ByteCodeAppender.Simple(stackManipulationArr).apply(methodVisitor, context, methodDescription);
                } catch (NoSuchMethodException e) {
                    e = e;
                    throw new IllegalStateException("Cannot locate method", e);
                }
            } catch (NoSuchMethodException e2) {
                e = e2;
                throw new IllegalStateException("Cannot locate method", e);
            }
        }
    }

    protected interface Dispatcher {
        void clean(Reference<? extends ClassLoader> reference);

        boolean isAlive();

        void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, LoadedTypeInitializer loadedTypeInitializer);

        public enum CreationAction implements PrivilegedAction<Dispatcher> {
            INSTANCE;

            public Dispatcher run() {
                if (Boolean.getBoolean(Nexus.PROPERTY)) {
                    return new Unavailable("Nexus injection was explicitly disabled");
                }
                try {
                    Class cls = new ClassInjector.UsingReflection(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.NO_PROTECTION_DOMAIN).inject(Collections.singletonMap(TypeDescription.ForLoadedType.of(Nexus.class), ClassFileLocator.ForClassLoader.read((Class<?>) Nexus.class))).get(TypeDescription.ForLoadedType.of(Nexus.class));
                    return new Available(cls.getMethod("register", new Class[]{String.class, ClassLoader.class, ReferenceQueue.class, Integer.TYPE, Object.class}), cls.getMethod("clean", new Class[]{Reference.class}));
                } catch (Exception e) {
                    try {
                        Class<?> loadClass = ClassLoader.getSystemClassLoader().loadClass(Nexus.class.getName());
                        return new Available(loadClass.getMethod("register", new Class[]{String.class, ClassLoader.class, ReferenceQueue.class, Integer.TYPE, Object.class}), loadClass.getMethod("clean", new Class[]{Reference.class}));
                    } catch (Exception unused) {
                        return new Unavailable(e.toString());
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Available implements Dispatcher {
            private static final Object STATIC_METHOD = null;
            private final Method clean;
            private final Method register;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Available available = (Available) obj;
                return this.register.equals(available.register) && this.clean.equals(available.clean);
            }

            public int hashCode() {
                return ((527 + this.register.hashCode()) * 31) + this.clean.hashCode();
            }

            public boolean isAlive() {
                return true;
            }

            protected Available(Method method, Method method2) {
                this.register = method;
                this.clean = method2;
            }

            public void clean(Reference<? extends ClassLoader> reference) {
                try {
                    this.clean.invoke(STATIC_METHOD, new Object[]{reference});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access: " + this.clean, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke: " + this.clean, e2.getCause());
                }
            }

            public void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, LoadedTypeInitializer loadedTypeInitializer) {
                try {
                    this.register.invoke(STATIC_METHOD, new Object[]{str, classLoader, referenceQueue, Integer.valueOf(i), loadedTypeInitializer});
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Cannot access: " + this.register, e);
                } catch (InvocationTargetException e2) {
                    throw new IllegalStateException("Cannot invoke: " + this.register, e2.getCause());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Unavailable implements Dispatcher {
            private final String message;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.message.equals(((Unavailable) obj).message);
            }

            public int hashCode() {
                return 527 + this.message.hashCode();
            }

            public boolean isAlive() {
                return false;
            }

            protected Unavailable(String str) {
                this.message = str;
            }

            public void clean(Reference<? extends ClassLoader> reference) {
                throw new UnsupportedOperationException("Could not initialize Nexus accessor: " + this.message);
            }

            public void register(String str, ClassLoader classLoader, ReferenceQueue<? super ClassLoader> referenceQueue, int i, LoadedTypeInitializer loadedTypeInitializer) {
                throw new UnsupportedOperationException("Could not initialize Nexus accessor: " + this.message);
            }
        }
    }
}

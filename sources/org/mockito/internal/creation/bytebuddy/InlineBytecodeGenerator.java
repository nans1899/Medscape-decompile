package org.mockito.internal.creation.bytebuddy;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.RandomString;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.creation.bytebuddy.MockMethodAdvice;
import org.mockito.internal.creation.bytebuddy.inject.MockMethodDispatcher;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;
import org.mockito.internal.util.concurrent.WeakConcurrentSet;
import org.mockito.mock.SerializableMode;

public class InlineBytecodeGenerator implements BytecodeGenerator, ClassFileTransformer {
    static final Set<Class<?>> EXCLUDES = new HashSet(Arrays.asList(new Class[]{Class.class, Boolean.class, Byte.class, Short.class, Character.class, Integer.class, Long.class, Float.class, Double.class, String.class}));
    private static final String PRELOAD = "org.mockito.inline.preload";
    private final ByteBuddy byteBuddy = new ByteBuddy().with(TypeValidation.DISABLED).with((Implementation.Context.Factory) Implementation.Context.Disabled.Factory.INSTANCE).with((MethodGraph.Compiler) MethodGraph.Compiler.ForDeclaredMethods.INSTANCE);
    private final Method canRead;
    private final Method getModule;
    private final Instrumentation instrumentation;
    private volatile Throwable lastException;
    private final AsmVisitorWrapper mockTransformer;
    private final WeakConcurrentSet<Class<?>> mocked = new WeakConcurrentSet<>(WeakConcurrentSet.Cleaner.INLINE);
    private final Method redefineModule;
    private final BytecodeGenerator subclassEngine;

    public InlineBytecodeGenerator(Instrumentation instrumentation2, WeakConcurrentMap<Object, MockMethodInterceptor> weakConcurrentMap) {
        Method method;
        Method method2;
        preload();
        this.instrumentation = instrumentation2;
        String make = RandomString.make();
        this.subclassEngine = new TypeCachingBytecodeGenerator(new SubclassBytecodeGenerator(MethodDelegation.withDefaultConfiguration().withBinders((TargetMethodAnnotationDrivenBinder.ParameterBinder<?>[]) new TargetMethodAnnotationDrivenBinder.ParameterBinder[]{TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFixedValue.OfConstant.of(MockMethodAdvice.Identifier.class, make)}).to((Class<?>) MockMethodAdvice.ForReadObject.class), ElementMatchers.isAbstract().or(ElementMatchers.isNative()).or(ElementMatchers.isToString())), false);
        this.mockTransformer = new AsmVisitorWrapper.ForDeclaredMethods().method((ElementMatcher<? super MethodDescription>) ElementMatchers.isVirtual().and(ElementMatchers.not(ElementMatchers.isBridge().or(ElementMatchers.isHashCode()).or(ElementMatchers.isEquals()).or(ElementMatchers.isDefaultFinalizer()))).and(ElementMatchers.not(ElementMatchers.isDeclaredBy((ElementMatcher<? super TypeDescription>) ElementMatchers.nameStartsWith("java.")).and(ElementMatchers.isPackagePrivate()))), Advice.withCustomMapping().bind(MockMethodAdvice.Identifier.class, (Object) make).to(MockMethodAdvice.class)).method((ElementMatcher<? super MethodDescription>) ElementMatchers.isHashCode(), Advice.withCustomMapping().bind(MockMethodAdvice.Identifier.class, (Object) make).to(MockMethodAdvice.ForHashCode.class)).method((ElementMatcher<? super MethodDescription>) ElementMatchers.isEquals(), Advice.withCustomMapping().bind(MockMethodAdvice.Identifier.class, (Object) make).to(MockMethodAdvice.ForEquals.class));
        Method method3 = null;
        try {
            Method method4 = Class.class.getMethod("getModule", new Class[0]);
            method = method4.getReturnType().getMethod("canRead", new Class[]{method4.getReturnType()});
            Method method5 = method4;
            method2 = Instrumentation.class.getMethod("redefineModule", new Class[]{method4.getReturnType(), Set.class, Map.class, Map.class, Set.class, Map.class});
            method3 = method5;
        } catch (Exception unused) {
            method2 = null;
            method = null;
        }
        this.getModule = method3;
        this.canRead = method;
        this.redefineModule = method2;
        MockMethodDispatcher.set(make, new MockMethodAdvice(weakConcurrentMap, make));
        instrumentation2.addTransformer(this, true);
    }

    private static void preload() {
        String property = System.getProperty(PRELOAD);
        if (property == null) {
            property = "java.lang.WeakPairMap,java.lang.WeakPairMap$Pair,java.lang.WeakPairMap$Pair$Weak";
        }
        for (String cls : property.split(",")) {
            try {
                Class.forName(cls, false, (ClassLoader) null);
            } catch (ClassNotFoundException unused) {
            }
        }
    }

    public <T> Class<? extends T> mockClass(MockFeatures<T> mockFeatures) {
        boolean z = !mockFeatures.interfaces.isEmpty() || mockFeatures.serializableMode != SerializableMode.NONE || Modifier.isAbstract(mockFeatures.mockedType.getModifiers());
        checkSupportedCombination(z, mockFeatures);
        synchronized (this) {
            triggerRetransformation(mockFeatures);
        }
        if (z) {
            return this.subclassEngine.mockClass(mockFeatures);
        }
        return mockFeatures.mockedType;
    }

    private <T> void triggerRetransformation(MockFeatures<T> mockFeatures) {
        HashSet<Class> hashSet = new HashSet<>();
        Class cls = mockFeatures.mockedType;
        do {
            if (this.mocked.add(cls)) {
                hashSet.add(cls);
                addInterfaces(hashSet, cls.getInterfaces());
            }
            cls = cls.getSuperclass();
        } while (cls != null);
        if (!hashSet.isEmpty()) {
            try {
                assureCanReadMockito(hashSet);
                this.instrumentation.retransformClasses((Class[]) hashSet.toArray(new Class[hashSet.size()]));
                Throwable th = this.lastException;
                if (th == null) {
                    this.lastException = null;
                } else {
                    throw new IllegalStateException(StringUtil.join("Byte Buddy could not instrument all classes within the mock's type hierarchy", "", "This problem should never occur for javac-compiled classes. This problem has been observed for classes that are:", " - Compiled by older versions of scalac", " - Classes that are part of the Android distribution"), th);
                }
            } catch (Exception e) {
                for (Class remove : hashSet) {
                    this.mocked.remove(remove);
                }
                throw new MockitoException("Could not modify all classes " + hashSet, e);
            } catch (Throwable th2) {
                this.lastException = null;
                throw th2;
            }
        }
    }

    private void assureCanReadMockito(Set<Class<?>> set) {
        if (this.redefineModule != null) {
            HashSet hashSet = new HashSet();
            try {
                Object invoke = this.getModule.invoke(Class.forName("org.mockito.internal.creation.bytebuddy.inject.MockMethodDispatcher", false, (ClassLoader) null), new Object[0]);
                for (Class<?> invoke2 : set) {
                    Object invoke3 = this.getModule.invoke(invoke2, new Object[0]);
                    if (!hashSet.contains(invoke3)) {
                        if (!((Boolean) this.canRead.invoke(invoke3, new Object[]{invoke})).booleanValue()) {
                            hashSet.add(invoke3);
                        }
                    }
                }
                for (Object next : hashSet) {
                    this.redefineModule.invoke(this.instrumentation, new Object[]{next, Collections.singleton(invoke), Collections.emptyMap(), Collections.emptyMap(), Collections.emptySet(), Collections.emptyMap()});
                }
            } catch (Exception e) {
                throw new IllegalStateException(StringUtil.join("Could not adjust module graph to make the mock instance dispatcher visible to some classes", "", "At least one of those modules: " + hashSet + " is not reading the unnamed module of the bootstrap loader", "Without such a read edge, the classes that are redefined to become mocks cannot access the mock dispatcher.", "To circumvent this, Mockito attempted to add a read edge to this module what failed for an unexpected reason"), e);
            }
        }
    }

    private <T> void checkSupportedCombination(boolean z, MockFeatures<T> mockFeatures) {
        if (z && !mockFeatures.mockedType.isArray() && !mockFeatures.mockedType.isPrimitive() && Modifier.isFinal(mockFeatures.mockedType.getModifiers())) {
            throw new MockitoException("Unsupported settings with this type '" + mockFeatures.mockedType.getName() + "'");
        }
    }

    private void addInterfaces(Set<Class<?>> set, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (this.mocked.add(cls)) {
                set.add(cls);
                addInterfaces(set, cls.getInterfaces());
            }
        }
    }

    public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
        if (cls != null && this.mocked.contains(cls) && !EXCLUDES.contains(cls)) {
            try {
                return this.byteBuddy.redefine(cls, ClassFileLocator.Simple.of(cls.getName(), bArr)).visit(new ParameterWritingVisitorWrapper(cls)).visit(this.mockTransformer).make().getBytes();
            } catch (Throwable th) {
                this.lastException = th;
            }
        }
        return null;
    }

    private static class ParameterWritingVisitorWrapper extends AsmVisitorWrapper.AbstractBase {
        private final Class<?> type;

        private ParameterWritingVisitorWrapper(Class<?> cls) {
            this.type = cls;
        }

        public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
            return context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V8) ? new ParameterAddingClassVisitor(classVisitor, new TypeDescription.ForLoadedType(this.type)) : classVisitor;
        }

        private static class ParameterAddingClassVisitor extends ClassVisitor {
            private final TypeDescription typeDescription;

            private ParameterAddingClassVisitor(ClassVisitor classVisitor, TypeDescription typeDescription2) {
                super(OpenedClassReader.ASM_API, classVisitor);
                this.typeDescription = typeDescription2;
            }

            public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                ElementMatcher.Junction junction;
                MethodVisitor visitMethod = super.visitMethod(i, str, str2, str3, strArr);
                MethodList<MethodDescription.InDefinedShape> declaredMethods = this.typeDescription.getDeclaredMethods();
                if (str.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                    junction = ElementMatchers.isConstructor();
                } else {
                    junction = ElementMatchers.named(str);
                }
                MethodList methodList = (MethodList) declaredMethods.filter(junction.and(ElementMatchers.hasDescriptor(str2)));
                if (methodList.size() != 1 || !((MethodDescription) methodList.getOnly()).getParameters().hasExplicitMetaData()) {
                    return visitMethod;
                }
                Iterator it = ((MethodDescription) methodList.getOnly()).getParameters().iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription = (ParameterDescription) it.next();
                    visitMethod.visitParameter(parameterDescription.getName(), parameterDescription.getModifiers());
                }
                return new MethodParameterStrippingMethodVisitor(visitMethod);
            }
        }

        private static class MethodParameterStrippingMethodVisitor extends MethodVisitor {
            public void visitParameter(String str, int i) {
            }

            public MethodParameterStrippingMethodVisitor(MethodVisitor methodVisitor) {
                super(Opcodes.ASM5, methodVisitor);
            }
        }
    }
}

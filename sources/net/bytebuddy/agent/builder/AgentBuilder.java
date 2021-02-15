package net.bytebuddy.agent.builder;

import java.io.File;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.EntryPoint;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.TypeManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.NexusAccessor;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaModule;
import net.bytebuddy.utility.JavaType;

public interface AgentBuilder {

    public interface ClassFileBufferStrategy {

        public enum Default implements ClassFileBufferStrategy {
            RETAINING {
                public ClassFileLocator resolve(String str, byte[] bArr, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                    return ClassFileLocator.Simple.of(str, bArr);
                }
            },
            DISCARDING {
                public ClassFileLocator resolve(String str, byte[] bArr, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                    return ClassFileLocator.NoOp.INSTANCE;
                }
            }
        }

        ClassFileLocator resolve(String str, byte[] bArr, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain);
    }

    public interface Identified {

        public interface Extendable extends AgentBuilder, Identified {
            AgentBuilder asTerminalTransformation();
        }

        public interface Narrowable extends Matchable<Narrowable>, Identified {
        }

        Extendable transform(Transformer transformer);
    }

    public interface Ignored extends Matchable<Ignored>, AgentBuilder {
    }

    public interface RedefinitionListenable extends AgentBuilder {

        public interface WithImplicitDiscoveryStrategy extends RedefinitionListenable {
            RedefinitionListenable redefineOnly(Class<?>... clsArr);

            RedefinitionListenable with(RedefinitionStrategy.DiscoveryStrategy discoveryStrategy);
        }

        public interface WithoutBatchStrategy extends WithImplicitDiscoveryStrategy {
            WithImplicitDiscoveryStrategy with(RedefinitionStrategy.BatchAllocator batchAllocator);
        }

        RedefinitionListenable with(RedefinitionStrategy.Listener listener);

        AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler);

        AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler, ElementMatcher<? super Throwable> elementMatcher);
    }

    public interface TransformerDecorator {

        public enum NoOp implements TransformerDecorator {
            INSTANCE;

            public ResettableClassFileTransformer decorate(ResettableClassFileTransformer resettableClassFileTransformer) {
                return resettableClassFileTransformer;
            }
        }

        ResettableClassFileTransformer decorate(ResettableClassFileTransformer resettableClassFileTransformer);
    }

    AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection);

    AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Class<?>... clsArr);

    AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, JavaModule... javaModuleArr);

    AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection);

    AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Class<?>... clsArr);

    AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, JavaModule... javaModuleArr);

    AgentBuilder disableBootstrapInjection();

    AgentBuilder disableClassFormatChanges();

    AgentBuilder disableNativeMethodPrefix();

    AgentBuilder enableBootstrapInjection(Instrumentation instrumentation, File file);

    AgentBuilder enableNativeMethodPrefix(String str);

    AgentBuilder enableUnsafeBootstrapInjection();

    Ignored ignore(RawMatcher rawMatcher);

    Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher);

    Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

    Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

    ResettableClassFileTransformer installOn(Instrumentation instrumentation);

    ResettableClassFileTransformer installOn(Instrumentation instrumentation, TransformerDecorator transformerDecorator);

    ResettableClassFileTransformer installOnByteBuddyAgent();

    ResettableClassFileTransformer installOnByteBuddyAgent(TransformerDecorator transformerDecorator);

    ClassFileTransformer makeRaw();

    Identified.Narrowable type(RawMatcher rawMatcher);

    Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher);

    Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

    Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

    RedefinitionListenable.WithoutBatchStrategy with(RedefinitionStrategy redefinitionStrategy);

    AgentBuilder with(ByteBuddy byteBuddy);

    AgentBuilder with(CircularityLock circularityLock);

    AgentBuilder with(ClassFileBufferStrategy classFileBufferStrategy);

    AgentBuilder with(DescriptionStrategy descriptionStrategy);

    AgentBuilder with(FallbackStrategy fallbackStrategy);

    AgentBuilder with(InitializationStrategy initializationStrategy);

    AgentBuilder with(InstallationListener installationListener);

    AgentBuilder with(LambdaInstrumentationStrategy lambdaInstrumentationStrategy);

    AgentBuilder with(Listener listener);

    AgentBuilder with(LocationStrategy locationStrategy);

    AgentBuilder with(PoolStrategy poolStrategy);

    AgentBuilder with(TypeStrategy typeStrategy);

    public interface Matchable<T extends Matchable<T>> {
        T and(RawMatcher rawMatcher);

        T and(ElementMatcher<? super TypeDescription> elementMatcher);

        T and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

        T and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

        T or(RawMatcher rawMatcher);

        T or(ElementMatcher<? super TypeDescription> elementMatcher);

        T or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2);

        T or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3);

        public static abstract class AbstractBase<S extends Matchable<S>> implements Matchable<S> {
            public S and(ElementMatcher<? super TypeDescription> elementMatcher) {
                return and(elementMatcher, ElementMatchers.any());
            }

            public S and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return and(elementMatcher, elementMatcher2, ElementMatchers.any());
            }

            public S and(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return and((RawMatcher) new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, elementMatcher3));
            }

            public S or(ElementMatcher<? super TypeDescription> elementMatcher) {
                return or(elementMatcher, ElementMatchers.any());
            }

            public S or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return or(elementMatcher, elementMatcher2, ElementMatchers.any());
            }

            public S or(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return or((RawMatcher) new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, elementMatcher3));
            }
        }
    }

    public interface RawMatcher {
        boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain);

        public enum Trivial implements RawMatcher {
            MATCHING(true),
            NON_MATCHING(false);
            
            private final boolean matches;

            private Trivial(boolean z) {
                this.matches = z;
            }

            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.matches;
            }
        }

        public enum ForLoadState implements RawMatcher {
            LOADED(false),
            UNLOADED(true);
            
            private final boolean unloaded;

            private ForLoadState(boolean z) {
                this.unloaded = z;
            }

            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return (cls == null) == this.unloaded;
            }
        }

        public enum ForResolvableTypes implements RawMatcher {
            INSTANCE;

            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                if (cls == null) {
                    return true;
                }
                try {
                    return Class.forName(cls.getName(), true, classLoader) == cls;
                } catch (Throwable unused) {
                    return false;
                }
            }

            public RawMatcher inverted() {
                return new Inversion(this);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Conjunction implements RawMatcher {
            private final RawMatcher left;
            private final RawMatcher right;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Conjunction conjunction = (Conjunction) obj;
                return this.left.equals(conjunction.left) && this.right.equals(conjunction.right);
            }

            public int hashCode() {
                return ((527 + this.left.hashCode()) * 31) + this.right.hashCode();
            }

            protected Conjunction(RawMatcher rawMatcher, RawMatcher rawMatcher2) {
                this.left = rawMatcher;
                this.right = rawMatcher2;
            }

            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.left.matches(typeDescription, classLoader, javaModule, cls, protectionDomain) && this.right.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Disjunction implements RawMatcher {
            private final RawMatcher left;
            private final RawMatcher right;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Disjunction disjunction = (Disjunction) obj;
                return this.left.equals(disjunction.left) && this.right.equals(disjunction.right);
            }

            public int hashCode() {
                return ((527 + this.left.hashCode()) * 31) + this.right.hashCode();
            }

            protected Disjunction(RawMatcher rawMatcher, RawMatcher rawMatcher2) {
                this.left = rawMatcher;
                this.right = rawMatcher2;
            }

            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.left.matches(typeDescription, classLoader, javaModule, cls, protectionDomain) || this.right.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Inversion implements RawMatcher {
            private final RawMatcher matcher;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Inversion) obj).matcher);
            }

            public int hashCode() {
                return 527 + this.matcher.hashCode();
            }

            public Inversion(RawMatcher rawMatcher) {
                this.matcher = rawMatcher;
            }

            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return !this.matcher.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForElementMatchers implements RawMatcher {
            private final ElementMatcher<? super ClassLoader> classLoaderMatcher;
            private final ElementMatcher<? super JavaModule> moduleMatcher;
            private final ElementMatcher<? super TypeDescription> typeMatcher;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForElementMatchers forElementMatchers = (ForElementMatchers) obj;
                return this.typeMatcher.equals(forElementMatchers.typeMatcher) && this.classLoaderMatcher.equals(forElementMatchers.classLoaderMatcher) && this.moduleMatcher.equals(forElementMatchers.moduleMatcher);
            }

            public int hashCode() {
                return ((((527 + this.typeMatcher.hashCode()) * 31) + this.classLoaderMatcher.hashCode()) * 31) + this.moduleMatcher.hashCode();
            }

            public ForElementMatchers(ElementMatcher<? super TypeDescription> elementMatcher) {
                this(elementMatcher, ElementMatchers.any());
            }

            public ForElementMatchers(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                this(elementMatcher, elementMatcher2, ElementMatchers.any());
            }

            public ForElementMatchers(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                this.typeMatcher = elementMatcher;
                this.classLoaderMatcher = elementMatcher2;
                this.moduleMatcher = elementMatcher3;
            }

            public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                return this.moduleMatcher.matches(javaModule) && this.classLoaderMatcher.matches(classLoader) && this.typeMatcher.matches(typeDescription);
            }
        }
    }

    public interface Listener {
        public static final boolean LOADED = true;

        public static abstract class Adapter implements Listener {
            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
            }
        }

        public enum NoOp implements Listener {
            INSTANCE;

            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
            }
        }

        void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z);

        void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z);

        void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th);

        void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z);

        void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType);

        @HashCodeAndEqualsPlugin.Enhance
        public static class StreamWriting implements Listener {
            protected static final String PREFIX = "[Byte Buddy]";
            private final PrintStream printStream;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.printStream.equals(((StreamWriting) obj).printStream);
            }

            public int hashCode() {
                return 527 + this.printStream.hashCode();
            }

            public StreamWriting(PrintStream printStream2) {
                this.printStream = printStream2;
            }

            public static StreamWriting toSystemOut() {
                return new StreamWriting(System.out);
            }

            public static StreamWriting toSystemError() {
                return new StreamWriting(System.err);
            }

            public Listener withTransformationsOnly() {
                return new WithTransformationsOnly(this);
            }

            public Listener withErrorsOnly() {
                return new WithErrorsOnly(this);
            }

            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                this.printStream.printf("[Byte Buddy] DISCOVERY %s [%s, %s, loaded=%b]%n", new Object[]{str, classLoader, javaModule, Boolean.valueOf(z)});
            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                this.printStream.printf("[Byte Buddy] TRANSFORM %s [%s, %s, loaded=%b]%n", new Object[]{typeDescription.getName(), classLoader, javaModule, Boolean.valueOf(z)});
            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                this.printStream.printf("[Byte Buddy] IGNORE %s [%s, %s, loaded=%b]%n", new Object[]{typeDescription.getName(), classLoader, javaModule, Boolean.valueOf(z)});
            }

            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                synchronized (this.printStream) {
                    this.printStream.printf("[Byte Buddy] ERROR %s [%s, %s, loaded=%b]%n", new Object[]{str, classLoader, javaModule, Boolean.valueOf(z)});
                    th.printStackTrace(this.printStream);
                }
            }

            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                this.printStream.printf("[Byte Buddy] COMPLETE %s [%s, %s, loaded=%b]%n", new Object[]{str, classLoader, javaModule, Boolean.valueOf(z)});
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Filtering implements Listener {
            private final Listener delegate;
            private final ElementMatcher<? super String> matcher;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Filtering filtering = (Filtering) obj;
                return this.matcher.equals(filtering.matcher) && this.delegate.equals(filtering.delegate);
            }

            public int hashCode() {
                return ((527 + this.matcher.hashCode()) * 31) + this.delegate.hashCode();
            }

            public Filtering(ElementMatcher<? super String> elementMatcher, Listener listener) {
                this.matcher = elementMatcher;
                this.delegate = listener;
            }

            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                if (this.matcher.matches(str)) {
                    this.delegate.onDiscovery(str, classLoader, javaModule, z);
                }
            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                if (this.matcher.matches(typeDescription.getName())) {
                    this.delegate.onTransformation(typeDescription, classLoader, javaModule, z, dynamicType);
                }
            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                if (this.matcher.matches(typeDescription.getName())) {
                    this.delegate.onIgnored(typeDescription, classLoader, javaModule, z);
                }
            }

            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                if (this.matcher.matches(str)) {
                    this.delegate.onError(str, classLoader, javaModule, z, th);
                }
            }

            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                if (this.matcher.matches(str)) {
                    this.delegate.onComplete(str, classLoader, javaModule, z);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class WithTransformationsOnly extends Adapter {
            private final Listener delegate;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithTransformationsOnly) obj).delegate);
            }

            public int hashCode() {
                return 527 + this.delegate.hashCode();
            }

            public WithTransformationsOnly(Listener listener) {
                this.delegate = listener;
            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                this.delegate.onTransformation(typeDescription, classLoader, javaModule, z, dynamicType);
            }

            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                this.delegate.onError(str, classLoader, javaModule, z, th);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class WithErrorsOnly extends Adapter {
            private final Listener delegate;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.delegate.equals(((WithErrorsOnly) obj).delegate);
            }

            public int hashCode() {
                return 527 + this.delegate.hashCode();
            }

            public WithErrorsOnly(Listener listener) {
                this.delegate = listener;
            }

            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                this.delegate.onError(str, classLoader, javaModule, z, th);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ModuleReadEdgeCompleting extends Adapter {
            private final boolean addTargetEdge;
            private final Instrumentation instrumentation;
            private final Set<? extends JavaModule> modules;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ModuleReadEdgeCompleting moduleReadEdgeCompleting = (ModuleReadEdgeCompleting) obj;
                return this.addTargetEdge == moduleReadEdgeCompleting.addTargetEdge && this.instrumentation.equals(moduleReadEdgeCompleting.instrumentation) && this.modules.equals(moduleReadEdgeCompleting.modules);
            }

            public int hashCode() {
                return ((((527 + this.instrumentation.hashCode()) * 31) + (this.addTargetEdge ? 1 : 0)) * 31) + this.modules.hashCode();
            }

            public ModuleReadEdgeCompleting(Instrumentation instrumentation2, boolean z, Set<? extends JavaModule> set) {
                this.instrumentation = instrumentation2;
                this.addTargetEdge = z;
                this.modules = set;
            }

            public static Listener of(Instrumentation instrumentation2, boolean z, Class<?>... clsArr) {
                HashSet hashSet = new HashSet();
                for (Class<?> ofType : clsArr) {
                    hashSet.add(JavaModule.ofType(ofType));
                }
                return hashSet.isEmpty() ? NoOp.INSTANCE : new ModuleReadEdgeCompleting(instrumentation2, z, hashSet);
            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                if (javaModule != JavaModule.UNSUPPORTED && javaModule.isNamed()) {
                    for (JavaModule javaModule2 : this.modules) {
                        if (!javaModule.canRead(javaModule2)) {
                            javaModule.addReads(this.instrumentation, javaModule2);
                        }
                        if (this.addTargetEdge && !javaModule2.canRead(javaModule)) {
                            javaModule2.addReads(this.instrumentation, javaModule);
                        }
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Compound implements Listener {
            private final List<Listener> listeners;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.listeners.equals(((Compound) obj).listeners);
            }

            public int hashCode() {
                return 527 + this.listeners.hashCode();
            }

            public Compound(Listener... listenerArr) {
                this((List<? extends Listener>) Arrays.asList(listenerArr));
            }

            public Compound(List<? extends Listener> list) {
                this.listeners = new ArrayList();
                for (Listener listener : list) {
                    if (listener instanceof Compound) {
                        this.listeners.addAll(((Compound) listener).listeners);
                    } else if (!(listener instanceof NoOp)) {
                        this.listeners.add(listener);
                    }
                }
            }

            public void onDiscovery(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                for (Listener onDiscovery : this.listeners) {
                    onDiscovery.onDiscovery(str, classLoader, javaModule, z);
                }
            }

            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z, DynamicType dynamicType) {
                for (Listener onTransformation : this.listeners) {
                    onTransformation.onTransformation(typeDescription, classLoader, javaModule, z, dynamicType);
                }
            }

            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                for (Listener onIgnored : this.listeners) {
                    onIgnored.onIgnored(typeDescription, classLoader, javaModule, z);
                }
            }

            public void onError(String str, ClassLoader classLoader, JavaModule javaModule, boolean z, Throwable th) {
                for (Listener onError : this.listeners) {
                    onError.onError(str, classLoader, javaModule, z, th);
                }
            }

            public void onComplete(String str, ClassLoader classLoader, JavaModule javaModule, boolean z) {
                for (Listener onComplete : this.listeners) {
                    onComplete.onComplete(str, classLoader, javaModule, z);
                }
            }
        }
    }

    public interface CircularityLock {

        public enum Inactive implements CircularityLock {
            INSTANCE;

            public boolean acquire() {
                return true;
            }

            public void release() {
            }
        }

        boolean acquire();

        void release();

        public static class Default extends ThreadLocal<Boolean> implements CircularityLock {
            private static final Boolean NOT_ACQUIRED = null;

            public boolean acquire() {
                if (get() != NOT_ACQUIRED) {
                    return false;
                }
                set(true);
                return true;
            }

            public void release() {
                set(NOT_ACQUIRED);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Global implements CircularityLock {
            private final Lock lock;
            private final long time;
            private final TimeUnit timeUnit;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Global global = (Global) obj;
                return this.time == global.time && this.timeUnit.equals(global.timeUnit) && this.lock.equals(global.lock);
            }

            public int hashCode() {
                long j = this.time;
                return ((((527 + this.lock.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.timeUnit.hashCode();
            }

            public Global() {
                this(0, TimeUnit.MILLISECONDS);
            }

            public Global(long j, TimeUnit timeUnit2) {
                this.lock = new ReentrantLock();
                this.time = j;
                this.timeUnit = timeUnit2;
            }

            public boolean acquire() {
                try {
                    if (this.time == 0) {
                        return this.lock.tryLock();
                    }
                    return this.lock.tryLock(this.time, this.timeUnit);
                } catch (InterruptedException unused) {
                    return false;
                }
            }

            public void release() {
                this.lock.unlock();
            }
        }
    }

    public interface TypeStrategy {

        public enum Default implements TypeStrategy {
            REBASE {
                public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                    return byteBuddy.rebase(typeDescription, classFileLocator, methodNameTransformer);
                }
            },
            REDEFINE {
                public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                    return byteBuddy.redefine(typeDescription, classFileLocator);
                }
            },
            REDEFINE_FROZEN {
                public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                    return byteBuddy.with((InstrumentedType.Factory) InstrumentedType.Factory.Default.FROZEN).redefine(typeDescription, classFileLocator).ignoreAlso((LatentMatcher<? super MethodDescription>) LatentMatcher.ForSelfDeclaredMethod.NOT_DECLARED);
                }
            },
            DECORATE {
                public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                    return byteBuddy.decorate(typeDescription, classFileLocator);
                }
            }
        }

        DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain);

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForBuildEntryPoint implements TypeStrategy {
            private final EntryPoint entryPoint;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.entryPoint.equals(((ForBuildEntryPoint) obj).entryPoint);
            }

            public int hashCode() {
                return 527 + this.entryPoint.hashCode();
            }

            public ForBuildEntryPoint(EntryPoint entryPoint2) {
                this.entryPoint = entryPoint2;
            }

            public DynamicType.Builder<?> builder(TypeDescription typeDescription, ByteBuddy byteBuddy, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer, ClassLoader classLoader, JavaModule javaModule, ProtectionDomain protectionDomain) {
                return this.entryPoint.transform(typeDescription, byteBuddy, classFileLocator, methodNameTransformer);
            }
        }
    }

    public interface Transformer {

        public enum NoOp implements Transformer {
            INSTANCE;

            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return builder;
            }
        }

        DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule);

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForBuildPlugin implements Transformer {
            private final Plugin plugin;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.plugin.equals(((ForBuildPlugin) obj).plugin);
            }

            public int hashCode() {
                return 527 + this.plugin.hashCode();
            }

            public ForBuildPlugin(Plugin plugin2) {
                this.plugin = plugin2;
            }

            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return this.plugin.apply(builder, typeDescription, ClassFileLocator.ForClassLoader.of(classLoader));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForAdvice implements Transformer {
            private final Advice.WithCustomMapping advice;
            private final Assigner assigner;
            private final ClassFileLocator classFileLocator;
            private final List<Entry> entries;
            private final Advice.ExceptionHandler exceptionHandler;
            private final LocationStrategy locationStrategy;
            private final PoolStrategy poolStrategy;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForAdvice forAdvice = (ForAdvice) obj;
                return this.advice.equals(forAdvice.advice) && this.exceptionHandler.equals(forAdvice.exceptionHandler) && this.assigner.equals(forAdvice.assigner) && this.classFileLocator.equals(forAdvice.classFileLocator) && this.poolStrategy.equals(forAdvice.poolStrategy) && this.locationStrategy.equals(forAdvice.locationStrategy) && this.entries.equals(forAdvice.entries);
            }

            public int hashCode() {
                return ((((((((((((527 + this.advice.hashCode()) * 31) + this.exceptionHandler.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.classFileLocator.hashCode()) * 31) + this.poolStrategy.hashCode()) * 31) + this.locationStrategy.hashCode()) * 31) + this.entries.hashCode();
            }

            public ForAdvice() {
                this(Advice.withCustomMapping());
            }

            public ForAdvice(Advice.WithCustomMapping withCustomMapping) {
                this(withCustomMapping, Advice.ExceptionHandler.Default.SUPPRESSING, Assigner.DEFAULT, ClassFileLocator.NoOp.INSTANCE, PoolStrategy.Default.FAST, LocationStrategy.ForClassLoader.STRONG, Collections.emptyList());
            }

            protected ForAdvice(Advice.WithCustomMapping withCustomMapping, Advice.ExceptionHandler exceptionHandler2, Assigner assigner2, ClassFileLocator classFileLocator2, PoolStrategy poolStrategy2, LocationStrategy locationStrategy2, List<Entry> list) {
                this.advice = withCustomMapping;
                this.exceptionHandler = exceptionHandler2;
                this.assigner = assigner2;
                this.classFileLocator = classFileLocator2;
                this.poolStrategy = poolStrategy2;
                this.locationStrategy = locationStrategy2;
                this.entries = list;
            }

            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                ClassFileLocator.Compound compound = new ClassFileLocator.Compound(this.classFileLocator, this.locationStrategy.classFileLocator(classLoader, javaModule));
                TypePool typePool = this.poolStrategy.typePool(compound, classLoader);
                AsmVisitorWrapper.ForDeclaredMethods forDeclaredMethods = new AsmVisitorWrapper.ForDeclaredMethods();
                for (Entry next : this.entries) {
                    forDeclaredMethods = forDeclaredMethods.invokable((ElementMatcher<? super MethodDescription>) next.getMatcher().resolve(typeDescription), next.resolve(this.advice, typePool, compound).withAssigner(this.assigner).withExceptionHandler(this.exceptionHandler));
                }
                return builder.visit(forDeclaredMethods);
            }

            public ForAdvice with(PoolStrategy poolStrategy2) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, poolStrategy2, this.locationStrategy, this.entries);
            }

            public ForAdvice with(LocationStrategy locationStrategy2) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, this.poolStrategy, locationStrategy2, this.entries);
            }

            public ForAdvice withExceptionHandler(Advice.ExceptionHandler exceptionHandler2) {
                return new ForAdvice(this.advice, exceptionHandler2, this.assigner, this.classFileLocator, this.poolStrategy, this.locationStrategy, this.entries);
            }

            public ForAdvice with(Assigner assigner2) {
                return new ForAdvice(this.advice, this.exceptionHandler, assigner2, this.classFileLocator, this.poolStrategy, this.locationStrategy, this.entries);
            }

            public ForAdvice include(ClassLoader... classLoaderArr) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                for (ClassLoader of : classLoaderArr) {
                    linkedHashSet.add(ClassFileLocator.ForClassLoader.of(of));
                }
                return include((List<? extends ClassFileLocator>) new ArrayList(linkedHashSet));
            }

            public ForAdvice include(ClassFileLocator... classFileLocatorArr) {
                return include((List<? extends ClassFileLocator>) Arrays.asList(classFileLocatorArr));
            }

            public ForAdvice include(List<? extends ClassFileLocator> list) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, new ClassFileLocator.Compound((List<? extends ClassFileLocator>) CompoundList.of(this.classFileLocator, list)), this.poolStrategy, this.locationStrategy, this.entries);
            }

            public ForAdvice advice(ElementMatcher<? super MethodDescription> elementMatcher, String str) {
                return advice((LatentMatcher<? super MethodDescription>) new LatentMatcher.Resolved(elementMatcher), str);
            }

            public ForAdvice advice(LatentMatcher<? super MethodDescription> latentMatcher, String str) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, this.poolStrategy, this.locationStrategy, CompoundList.of(this.entries, new Entry.ForUnifiedAdvice(latentMatcher, str)));
            }

            public ForAdvice advice(ElementMatcher<? super MethodDescription> elementMatcher, String str, String str2) {
                return advice((LatentMatcher<? super MethodDescription>) new LatentMatcher.Resolved(elementMatcher), str, str2);
            }

            public ForAdvice advice(LatentMatcher<? super MethodDescription> latentMatcher, String str, String str2) {
                return new ForAdvice(this.advice, this.exceptionHandler, this.assigner, this.classFileLocator, this.poolStrategy, this.locationStrategy, CompoundList.of(this.entries, new Entry.ForSplitAdvice(latentMatcher, str, str2)));
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static abstract class Entry {
                private final LatentMatcher<? super MethodDescription> matcher;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Entry) obj).matcher);
                }

                public int hashCode() {
                    return 527 + this.matcher.hashCode();
                }

                /* access modifiers changed from: protected */
                public abstract Advice resolve(Advice.WithCustomMapping withCustomMapping, TypePool typePool, ClassFileLocator classFileLocator);

                protected Entry(LatentMatcher<? super MethodDescription> latentMatcher) {
                    this.matcher = latentMatcher;
                }

                /* access modifiers changed from: protected */
                public LatentMatcher<? super MethodDescription> getMatcher() {
                    return this.matcher;
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ForUnifiedAdvice extends Entry {
                    protected final String name;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.name.equals(((ForUnifiedAdvice) obj).name);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.name.hashCode();
                    }

                    protected ForUnifiedAdvice(LatentMatcher<? super MethodDescription> latentMatcher, String str) {
                        super(latentMatcher);
                        this.name = str;
                    }

                    /* access modifiers changed from: protected */
                    public Advice resolve(Advice.WithCustomMapping withCustomMapping, TypePool typePool, ClassFileLocator classFileLocator) {
                        return withCustomMapping.to(typePool.describe(this.name).resolve(), classFileLocator);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ForSplitAdvice extends Entry {
                    private final String enter;
                    private final String exit;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForSplitAdvice forSplitAdvice = (ForSplitAdvice) obj;
                        return this.enter.equals(forSplitAdvice.enter) && this.exit.equals(forSplitAdvice.exit);
                    }

                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.enter.hashCode()) * 31) + this.exit.hashCode();
                    }

                    protected ForSplitAdvice(LatentMatcher<? super MethodDescription> latentMatcher, String str, String str2) {
                        super(latentMatcher);
                        this.enter = str;
                        this.exit = str2;
                    }

                    /* access modifiers changed from: protected */
                    public Advice resolve(Advice.WithCustomMapping withCustomMapping, TypePool typePool, ClassFileLocator classFileLocator) {
                        return withCustomMapping.to(typePool.describe(this.enter).resolve(), typePool.describe(this.exit).resolve(), classFileLocator);
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Compound implements Transformer {
            private final List<Transformer> transformers;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.transformers.equals(((Compound) obj).transformers);
            }

            public int hashCode() {
                return 527 + this.transformers.hashCode();
            }

            public Compound(Transformer... transformerArr) {
                this((List<? extends Transformer>) Arrays.asList(transformerArr));
            }

            public Compound(List<? extends Transformer> list) {
                this.transformers = new ArrayList();
                for (Transformer transformer : list) {
                    if (transformer instanceof Compound) {
                        this.transformers.addAll(((Compound) transformer).transformers);
                    } else if (!(transformer instanceof NoOp)) {
                        this.transformers.add(transformer);
                    }
                }
            }

            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                for (Transformer transform : this.transformers) {
                    builder = transform.transform(builder, typeDescription, classLoader, javaModule);
                }
                return builder;
            }
        }
    }

    public interface PoolStrategy {
        TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader);

        public enum Default implements PoolStrategy {
            EXTENDED(TypePool.Default.ReaderMode.EXTENDED),
            FAST(TypePool.Default.ReaderMode.FAST);
            
            private final TypePool.Default.ReaderMode readerMode;

            private Default(TypePool.Default.ReaderMode readerMode2) {
                this.readerMode = readerMode2;
            }

            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return new TypePool.Default.WithLazyResolution(TypePool.CacheProvider.Simple.withObjectType(), classFileLocator, this.readerMode);
            }
        }

        public enum Eager implements PoolStrategy {
            EXTENDED(TypePool.Default.ReaderMode.EXTENDED),
            FAST(TypePool.Default.ReaderMode.FAST);
            
            private final TypePool.Default.ReaderMode readerMode;

            private Eager(TypePool.Default.ReaderMode readerMode2) {
                this.readerMode = readerMode2;
            }

            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return new TypePool.Default(TypePool.CacheProvider.Simple.withObjectType(), classFileLocator, this.readerMode);
            }
        }

        public enum ClassLoading implements PoolStrategy {
            EXTENDED(TypePool.Default.ReaderMode.EXTENDED),
            FAST(TypePool.Default.ReaderMode.FAST);
            
            private final TypePool.Default.ReaderMode readerMode;

            private ClassLoading(TypePool.Default.ReaderMode readerMode2) {
                this.readerMode = readerMode2;
            }

            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return TypePool.ClassLoading.of(classLoader, new TypePool.Default.WithLazyResolution(TypePool.CacheProvider.Simple.withObjectType(), classFileLocator, this.readerMode));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class WithTypePoolCache implements PoolStrategy {
            protected final TypePool.Default.ReaderMode readerMode;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.readerMode.equals(((WithTypePoolCache) obj).readerMode);
            }

            public int hashCode() {
                return 527 + this.readerMode.hashCode();
            }

            /* access modifiers changed from: protected */
            public abstract TypePool.CacheProvider locate(ClassLoader classLoader);

            protected WithTypePoolCache(TypePool.Default.ReaderMode readerMode2) {
                this.readerMode = readerMode2;
            }

            public TypePool typePool(ClassFileLocator classFileLocator, ClassLoader classLoader) {
                return new TypePool.Default.WithLazyResolution(locate(classLoader), classFileLocator, this.readerMode);
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Simple extends WithTypePoolCache {
                private final ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> cacheProviders;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.cacheProviders.equals(((Simple) obj).cacheProviders);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.cacheProviders.hashCode();
                }

                public Simple(ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> concurrentMap) {
                    this(TypePool.Default.ReaderMode.FAST, concurrentMap);
                }

                public Simple(TypePool.Default.ReaderMode readerMode, ConcurrentMap<? super ClassLoader, TypePool.CacheProvider> concurrentMap) {
                    super(readerMode);
                    this.cacheProviders = concurrentMap;
                }

                /* access modifiers changed from: protected */
                public TypePool.CacheProvider locate(ClassLoader classLoader) {
                    if (classLoader == null) {
                        classLoader = getBootstrapMarkerLoader();
                    }
                    TypePool.CacheProvider cacheProvider = (TypePool.CacheProvider) this.cacheProviders.get(classLoader);
                    while (cacheProvider == null) {
                        cacheProvider = TypePool.CacheProvider.Simple.withObjectType();
                        TypePool.CacheProvider putIfAbsent = this.cacheProviders.putIfAbsent(classLoader, cacheProvider);
                        if (putIfAbsent != null) {
                            cacheProvider = putIfAbsent;
                        }
                    }
                    return cacheProvider;
                }

                /* access modifiers changed from: protected */
                public ClassLoader getBootstrapMarkerLoader() {
                    return ClassLoader.getSystemClassLoader();
                }
            }
        }
    }

    public interface InitializationStrategy {

        public interface Dispatcher {

            public interface InjectorFactory {
                ClassInjector resolve();
            }

            DynamicType.Builder<?> apply(DynamicType.Builder<?> builder);

            void register(DynamicType dynamicType, ClassLoader classLoader, InjectorFactory injectorFactory);
        }

        public enum NoOp implements InitializationStrategy, Dispatcher {
            INSTANCE;

            public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder) {
                return builder;
            }

            public Dispatcher dispatcher() {
                return this;
            }

            public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
            }
        }

        Dispatcher dispatcher();

        public enum Minimal implements InitializationStrategy, Dispatcher {
            INSTANCE;

            public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder) {
                return builder;
            }

            public Dispatcher dispatcher() {
                return this;
            }

            public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
                Map<TypeDescription, byte[]> auxiliaryTypes = dynamicType.getAuxiliaryTypes();
                LinkedHashMap linkedHashMap = new LinkedHashMap(auxiliaryTypes);
                for (TypeDescription next : auxiliaryTypes.keySet()) {
                    if (!next.getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) AuxiliaryType.SignatureRelevant.class)) {
                        linkedHashMap.remove(next);
                    }
                }
                if (!linkedHashMap.isEmpty()) {
                    ClassInjector resolve = injectorFactory.resolve();
                    Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
                    for (Map.Entry next2 : resolve.inject(linkedHashMap).entrySet()) {
                        loadedTypeInitializers.get(next2.getKey()).onLoad((Class) next2.getValue());
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class SelfInjection implements InitializationStrategy {
            protected final NexusAccessor nexusAccessor;

            /* access modifiers changed from: protected */
            public abstract Dispatcher dispatcher(int i);

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.nexusAccessor.equals(((SelfInjection) obj).nexusAccessor);
            }

            public int hashCode() {
                return 527 + this.nexusAccessor.hashCode();
            }

            protected SelfInjection(NexusAccessor nexusAccessor2) {
                this.nexusAccessor = nexusAccessor2;
            }

            public Dispatcher dispatcher() {
                return dispatcher(new Random().nextInt());
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static abstract class Dispatcher implements Dispatcher {
                protected final int identification;
                protected final NexusAccessor nexusAccessor;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Dispatcher dispatcher = (Dispatcher) obj;
                    return this.identification == dispatcher.identification && this.nexusAccessor.equals(dispatcher.nexusAccessor);
                }

                public int hashCode() {
                    return ((527 + this.nexusAccessor.hashCode()) * 31) + this.identification;
                }

                protected Dispatcher(NexusAccessor nexusAccessor2, int i) {
                    this.nexusAccessor = nexusAccessor2;
                    this.identification = i;
                }

                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder) {
                    return builder.initializer((ByteCodeAppender) new NexusAccessor.InitializationAppender(this.identification));
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class InjectingInitializer implements LoadedTypeInitializer {
                    private final ClassInjector classInjector;
                    private final TypeDescription instrumentedType;
                    private final Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers;
                    private final Map<TypeDescription, byte[]> rawAuxiliaryTypes;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        InjectingInitializer injectingInitializer = (InjectingInitializer) obj;
                        return this.instrumentedType.equals(injectingInitializer.instrumentedType) && this.rawAuxiliaryTypes.equals(injectingInitializer.rawAuxiliaryTypes) && this.loadedTypeInitializers.equals(injectingInitializer.loadedTypeInitializers) && this.classInjector.equals(injectingInitializer.classInjector);
                    }

                    public int hashCode() {
                        return ((((((527 + this.instrumentedType.hashCode()) * 31) + this.rawAuxiliaryTypes.hashCode()) * 31) + this.loadedTypeInitializers.hashCode()) * 31) + this.classInjector.hashCode();
                    }

                    public boolean isAlive() {
                        return true;
                    }

                    protected InjectingInitializer(TypeDescription typeDescription, Map<TypeDescription, byte[]> map, Map<TypeDescription, LoadedTypeInitializer> map2, ClassInjector classInjector2) {
                        this.instrumentedType = typeDescription;
                        this.rawAuxiliaryTypes = map;
                        this.loadedTypeInitializers = map2;
                        this.classInjector = classInjector2;
                    }

                    public void onLoad(Class<?> cls) {
                        for (Map.Entry next : this.classInjector.inject(this.rawAuxiliaryTypes).entrySet()) {
                            this.loadedTypeInitializers.get(next.getKey()).onLoad((Class) next.getValue());
                        }
                        this.loadedTypeInitializers.get(this.instrumentedType).onLoad(cls);
                    }
                }
            }

            public static class Split extends SelfInjection {
                public Split() {
                    this(new NexusAccessor());
                }

                public Split(NexusAccessor nexusAccessor) {
                    super(nexusAccessor);
                }

                /* access modifiers changed from: protected */
                public Dispatcher dispatcher(int i) {
                    return new Dispatcher(this.nexusAccessor, i);
                }

                protected static class Dispatcher extends Dispatcher {
                    protected Dispatcher(NexusAccessor nexusAccessor, int i) {
                        super(nexusAccessor, i);
                    }

                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: java.lang.Object} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: net.bytebuddy.implementation.LoadedTypeInitializer} */
                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void register(net.bytebuddy.dynamic.DynamicType r8, java.lang.ClassLoader r9, net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher.InjectorFactory r10) {
                        /*
                            r7 = this;
                            java.util.Map r0 = r8.getAuxiliaryTypes()
                            boolean r1 = r0.isEmpty()
                            if (r1 != 0) goto L_0x009e
                            net.bytebuddy.description.type.TypeDescription r1 = r8.getTypeDescription()
                            net.bytebuddy.dynamic.loading.ClassInjector r10 = r10.resolve()
                            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
                            r2.<init>(r0)
                            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
                            r3.<init>(r0)
                            java.util.Set r0 = r0.keySet()
                            java.util.Iterator r0 = r0.iterator()
                        L_0x0024:
                            boolean r4 = r0.hasNext()
                            if (r4 == 0) goto L_0x0043
                            java.lang.Object r4 = r0.next()
                            net.bytebuddy.description.type.TypeDescription r4 = (net.bytebuddy.description.type.TypeDescription) r4
                            net.bytebuddy.description.annotation.AnnotationList r5 = r4.getDeclaredAnnotations()
                            java.lang.Class<net.bytebuddy.implementation.auxiliary.AuxiliaryType$SignatureRelevant> r6 = net.bytebuddy.implementation.auxiliary.AuxiliaryType.SignatureRelevant.class
                            boolean r5 = r5.isAnnotationPresent((java.lang.Class<? extends java.lang.annotation.Annotation>) r6)
                            if (r5 == 0) goto L_0x003e
                            r5 = r3
                            goto L_0x003f
                        L_0x003e:
                            r5 = r2
                        L_0x003f:
                            r5.remove(r4)
                            goto L_0x0024
                        L_0x0043:
                            java.util.Map r0 = r8.getLoadedTypeInitializers()
                            boolean r4 = r2.isEmpty()
                            if (r4 != 0) goto L_0x0079
                            java.util.Map r4 = r10.inject(r2)
                            java.util.Set r4 = r4.entrySet()
                            java.util.Iterator r4 = r4.iterator()
                        L_0x0059:
                            boolean r5 = r4.hasNext()
                            if (r5 == 0) goto L_0x0079
                            java.lang.Object r5 = r4.next()
                            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
                            java.lang.Object r6 = r5.getKey()
                            java.lang.Object r6 = r0.get(r6)
                            net.bytebuddy.implementation.LoadedTypeInitializer r6 = (net.bytebuddy.implementation.LoadedTypeInitializer) r6
                            java.lang.Object r5 = r5.getValue()
                            java.lang.Class r5 = (java.lang.Class) r5
                            r6.onLoad(r5)
                            goto L_0x0059
                        L_0x0079:
                            java.util.HashMap r4 = new java.util.HashMap
                            r4.<init>(r0)
                            java.util.Set r0 = r0.keySet()
                            java.util.Set r2 = r2.keySet()
                            r0.removeAll(r2)
                            int r0 = r4.size()
                            r2 = 1
                            if (r0 <= r2) goto L_0x0096
                            net.bytebuddy.agent.builder.AgentBuilder$InitializationStrategy$SelfInjection$Dispatcher$InjectingInitializer r0 = new net.bytebuddy.agent.builder.AgentBuilder$InitializationStrategy$SelfInjection$Dispatcher$InjectingInitializer
                            r0.<init>(r1, r3, r4, r10)
                            goto L_0x00ad
                        L_0x0096:
                            java.lang.Object r10 = r4.get(r1)
                            r0 = r10
                            net.bytebuddy.implementation.LoadedTypeInitializer r0 = (net.bytebuddy.implementation.LoadedTypeInitializer) r0
                            goto L_0x00ad
                        L_0x009e:
                            java.util.Map r10 = r8.getLoadedTypeInitializers()
                            net.bytebuddy.description.type.TypeDescription r0 = r8.getTypeDescription()
                            java.lang.Object r10 = r10.get(r0)
                            r0 = r10
                            net.bytebuddy.implementation.LoadedTypeInitializer r0 = (net.bytebuddy.implementation.LoadedTypeInitializer) r0
                        L_0x00ad:
                            net.bytebuddy.dynamic.NexusAccessor r10 = r7.nexusAccessor
                            net.bytebuddy.description.type.TypeDescription r8 = r8.getTypeDescription()
                            java.lang.String r8 = r8.getName()
                            int r1 = r7.identification
                            r10.register(r8, r9, r1, r0)
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split.Dispatcher.register(net.bytebuddy.dynamic.DynamicType, java.lang.ClassLoader, net.bytebuddy.agent.builder.AgentBuilder$InitializationStrategy$Dispatcher$InjectorFactory):void");
                    }
                }
            }

            public static class Lazy extends SelfInjection {
                public Lazy() {
                    this(new NexusAccessor());
                }

                public Lazy(NexusAccessor nexusAccessor) {
                    super(nexusAccessor);
                }

                /* access modifiers changed from: protected */
                public Dispatcher dispatcher(int i) {
                    return new Dispatcher(this.nexusAccessor, i);
                }

                protected static class Dispatcher extends Dispatcher {
                    protected Dispatcher(NexusAccessor nexusAccessor, int i) {
                        super(nexusAccessor, i);
                    }

                    public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
                        LoadedTypeInitializer loadedTypeInitializer;
                        Map<TypeDescription, byte[]> auxiliaryTypes = dynamicType.getAuxiliaryTypes();
                        if (auxiliaryTypes.isEmpty()) {
                            loadedTypeInitializer = dynamicType.getLoadedTypeInitializers().get(dynamicType.getTypeDescription());
                        } else {
                            loadedTypeInitializer = new Dispatcher.InjectingInitializer(dynamicType.getTypeDescription(), auxiliaryTypes, dynamicType.getLoadedTypeInitializers(), injectorFactory.resolve());
                        }
                        this.nexusAccessor.register(dynamicType.getTypeDescription().getName(), classLoader, this.identification, loadedTypeInitializer);
                    }
                }
            }

            public static class Eager extends SelfInjection {
                public Eager() {
                    this(new NexusAccessor());
                }

                public Eager(NexusAccessor nexusAccessor) {
                    super(nexusAccessor);
                }

                /* access modifiers changed from: protected */
                public Dispatcher dispatcher(int i) {
                    return new Dispatcher(this.nexusAccessor, i);
                }

                protected static class Dispatcher extends Dispatcher {
                    protected Dispatcher(NexusAccessor nexusAccessor, int i) {
                        super(nexusAccessor, i);
                    }

                    public void register(DynamicType dynamicType, ClassLoader classLoader, Dispatcher.InjectorFactory injectorFactory) {
                        Map<TypeDescription, byte[]> auxiliaryTypes = dynamicType.getAuxiliaryTypes();
                        Map<TypeDescription, LoadedTypeInitializer> loadedTypeInitializers = dynamicType.getLoadedTypeInitializers();
                        if (!auxiliaryTypes.isEmpty()) {
                            for (Map.Entry next : injectorFactory.resolve().inject(auxiliaryTypes).entrySet()) {
                                loadedTypeInitializers.get(next.getKey()).onLoad((Class) next.getValue());
                            }
                        }
                        this.nexusAccessor.register(dynamicType.getTypeDescription().getName(), classLoader, this.identification, loadedTypeInitializers.get(dynamicType.getTypeDescription()));
                    }
                }
            }
        }
    }

    public interface DescriptionStrategy {
        TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule);

        boolean isLoadedFirst();

        public enum Default implements DescriptionStrategy {
            HYBRID(true) {
                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    if (cls == null) {
                        return typePool.describe(str).resolve();
                    }
                    return TypeDescription.ForLoadedType.of(cls);
                }
            },
            POOL_ONLY(false) {
                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    return typePool.describe(str).resolve();
                }
            },
            POOL_FIRST(false) {
                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    TypePool.Resolution describe = typePool.describe(str);
                    if (describe.isResolved() || cls == null) {
                        return describe.resolve();
                    }
                    return TypeDescription.ForLoadedType.of(cls);
                }
            };
            
            private final boolean loadedFirst;

            private Default(boolean z) {
                this.loadedFirst = z;
            }

            public DescriptionStrategy withSuperTypeLoading() {
                return new SuperTypeLoading(this);
            }

            public boolean isLoadedFirst() {
                return this.loadedFirst;
            }

            public DescriptionStrategy withSuperTypeLoading(ExecutorService executorService) {
                return new SuperTypeLoading.Asynchronous(this, executorService);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class SuperTypeLoading implements DescriptionStrategy {
            private final DescriptionStrategy delegate;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.delegate.equals(((SuperTypeLoading) obj).delegate);
            }

            public int hashCode() {
                return 527 + this.delegate.hashCode();
            }

            public SuperTypeLoading(DescriptionStrategy descriptionStrategy) {
                this.delegate = descriptionStrategy;
            }

            public boolean isLoadedFirst() {
                return this.delegate.isLoadedFirst();
            }

            public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                TypeDescription apply = this.delegate.apply(str, cls, typePool, circularityLock, classLoader, javaModule);
                return apply instanceof TypeDescription.ForLoadedType ? apply : new TypeDescription.SuperTypeLoading(apply, classLoader, new UnlockingClassLoadingDelegate(circularityLock));
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class UnlockingClassLoadingDelegate implements TypeDescription.SuperTypeLoading.ClassLoadingDelegate {
                private final CircularityLock circularityLock;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.circularityLock.equals(((UnlockingClassLoadingDelegate) obj).circularityLock);
                }

                public int hashCode() {
                    return 527 + this.circularityLock.hashCode();
                }

                protected UnlockingClassLoadingDelegate(CircularityLock circularityLock2) {
                    this.circularityLock = circularityLock2;
                }

                public Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException {
                    this.circularityLock.release();
                    try {
                        return Class.forName(str, false, classLoader);
                    } finally {
                        this.circularityLock.acquire();
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Asynchronous implements DescriptionStrategy {
                private final DescriptionStrategy delegate;
                private final ExecutorService executorService;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Asynchronous asynchronous = (Asynchronous) obj;
                    return this.delegate.equals(asynchronous.delegate) && this.executorService.equals(asynchronous.executorService);
                }

                public int hashCode() {
                    return ((527 + this.delegate.hashCode()) * 31) + this.executorService.hashCode();
                }

                public Asynchronous(DescriptionStrategy descriptionStrategy, ExecutorService executorService2) {
                    this.delegate = descriptionStrategy;
                    this.executorService = executorService2;
                }

                public boolean isLoadedFirst() {
                    return this.delegate.isLoadedFirst();
                }

                public TypeDescription apply(String str, Class<?> cls, TypePool typePool, CircularityLock circularityLock, ClassLoader classLoader, JavaModule javaModule) {
                    TypeDescription apply = this.delegate.apply(str, cls, typePool, circularityLock, classLoader, javaModule);
                    return apply instanceof TypeDescription.ForLoadedType ? apply : new TypeDescription.SuperTypeLoading(apply, classLoader, new ThreadSwitchingClassLoadingDelegate(this.executorService));
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ThreadSwitchingClassLoadingDelegate implements TypeDescription.SuperTypeLoading.ClassLoadingDelegate {
                    private final ExecutorService executorService;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.executorService.equals(((ThreadSwitchingClassLoadingDelegate) obj).executorService);
                    }

                    public int hashCode() {
                        return 527 + this.executorService.hashCode();
                    }

                    protected ThreadSwitchingClassLoadingDelegate(ExecutorService executorService2) {
                        this.executorService = executorService2;
                    }

                    public Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException {
                        boolean z = classLoader != null && Thread.holdsLock(classLoader);
                        AtomicBoolean atomicBoolean = new AtomicBoolean(z);
                        Future submit = this.executorService.submit(z ? new NotifyingClassLoadingAction(str, classLoader, atomicBoolean) : new SimpleClassLoadingAction(str, classLoader));
                        while (z) {
                            try {
                                if (!atomicBoolean.get()) {
                                    break;
                                }
                                classLoader.wait();
                            } catch (ExecutionException e) {
                                throw new IllegalStateException("Could not load " + str + " asynchronously", e.getCause());
                            } catch (Exception e2) {
                                throw new IllegalStateException("Could not load " + str + " asynchronously", e2);
                            }
                        }
                        return (Class) submit.get();
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    protected static class SimpleClassLoadingAction implements Callable<Class<?>> {
                        private final ClassLoader classLoader;
                        private final String name;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            SimpleClassLoadingAction simpleClassLoadingAction = (SimpleClassLoadingAction) obj;
                            return this.name.equals(simpleClassLoadingAction.name) && this.classLoader.equals(simpleClassLoadingAction.classLoader);
                        }

                        public int hashCode() {
                            return ((527 + this.name.hashCode()) * 31) + this.classLoader.hashCode();
                        }

                        protected SimpleClassLoadingAction(String str, ClassLoader classLoader2) {
                            this.name = str;
                            this.classLoader = classLoader2;
                        }

                        public Class<?> call() throws ClassNotFoundException {
                            return Class.forName(this.name, false, this.classLoader);
                        }
                    }

                    protected static class NotifyingClassLoadingAction implements Callable<Class<?>> {
                        private final ClassLoader classLoader;
                        private final String name;
                        private final AtomicBoolean signal;

                        protected NotifyingClassLoadingAction(String str, ClassLoader classLoader2, AtomicBoolean atomicBoolean) {
                            this.name = str;
                            this.classLoader = classLoader2;
                            this.signal = atomicBoolean;
                        }

                        public Class<?> call() throws ClassNotFoundException {
                            Class<?> cls;
                            synchronized (this.classLoader) {
                                try {
                                    cls = Class.forName(this.name, false, this.classLoader);
                                    this.signal.set(false);
                                    this.classLoader.notifyAll();
                                } catch (Throwable th) {
                                    this.signal.set(false);
                                    this.classLoader.notifyAll();
                                    throw th;
                                }
                            }
                            return cls;
                        }
                    }
                }
            }
        }
    }

    public interface LocationStrategy {
        ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule);

        public enum NoOp implements LocationStrategy {
            INSTANCE;

            public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                return ClassFileLocator.NoOp.INSTANCE;
            }
        }

        public enum ForClassLoader implements LocationStrategy {
            STRONG {
                public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                    return ClassFileLocator.ForClassLoader.of(classLoader);
                }
            },
            WEAK {
                public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                    return ClassFileLocator.ForClassLoader.WeaklyReferenced.of(classLoader);
                }
            };

            public LocationStrategy withFallbackTo(ClassFileLocator... classFileLocatorArr) {
                return withFallbackTo((Collection<? extends ClassFileLocator>) Arrays.asList(classFileLocatorArr));
            }

            public LocationStrategy withFallbackTo(Collection<? extends ClassFileLocator> collection) {
                ArrayList arrayList = new ArrayList(collection.size());
                for (ClassFileLocator simple : collection) {
                    arrayList.add(new Simple(simple));
                }
                return withFallbackTo((List<? extends LocationStrategy>) arrayList);
            }

            public LocationStrategy withFallbackTo(LocationStrategy... locationStrategyArr) {
                return withFallbackTo((List<? extends LocationStrategy>) Arrays.asList(locationStrategyArr));
            }

            public LocationStrategy withFallbackTo(List<? extends LocationStrategy> list) {
                ArrayList arrayList = new ArrayList(list.size() + 1);
                arrayList.add(this);
                arrayList.addAll(list);
                return new Compound((List<? extends LocationStrategy>) arrayList);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Simple implements LocationStrategy {
            private final ClassFileLocator classFileLocator;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.classFileLocator.equals(((Simple) obj).classFileLocator);
            }

            public int hashCode() {
                return 527 + this.classFileLocator.hashCode();
            }

            public Simple(ClassFileLocator classFileLocator2) {
                this.classFileLocator = classFileLocator2;
            }

            public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                return this.classFileLocator;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Compound implements LocationStrategy {
            private final List<LocationStrategy> locationStrategies;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.locationStrategies.equals(((Compound) obj).locationStrategies);
            }

            public int hashCode() {
                return 527 + this.locationStrategies.hashCode();
            }

            public Compound(LocationStrategy... locationStrategyArr) {
                this((List<? extends LocationStrategy>) Arrays.asList(locationStrategyArr));
            }

            public Compound(List<? extends LocationStrategy> list) {
                this.locationStrategies = new ArrayList();
                for (LocationStrategy locationStrategy : list) {
                    if (locationStrategy instanceof Compound) {
                        this.locationStrategies.addAll(((Compound) locationStrategy).locationStrategies);
                    } else if (!(locationStrategy instanceof NoOp)) {
                        this.locationStrategies.add(locationStrategy);
                    }
                }
            }

            public ClassFileLocator classFileLocator(ClassLoader classLoader, JavaModule javaModule) {
                ArrayList arrayList = new ArrayList(this.locationStrategies.size());
                for (LocationStrategy classFileLocator : this.locationStrategies) {
                    arrayList.add(classFileLocator.classFileLocator(classLoader, javaModule));
                }
                return new ClassFileLocator.Compound((List<? extends ClassFileLocator>) arrayList);
            }
        }
    }

    public interface FallbackStrategy {
        boolean isFallback(Class<?> cls, Throwable th);

        public enum Simple implements FallbackStrategy {
            ENABLED(true),
            DISABLED(false);
            
            private final boolean enabled;

            private Simple(boolean z) {
                this.enabled = z;
            }

            public boolean isFallback(Class<?> cls, Throwable th) {
                return this.enabled;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ByThrowableType implements FallbackStrategy {
            private final Set<? extends Class<? extends Throwable>> types;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.types.equals(((ByThrowableType) obj).types);
            }

            public int hashCode() {
                return 527 + this.types.hashCode();
            }

            public ByThrowableType(Class<? extends Throwable>... clsArr) {
                this((Set<? extends Class<? extends Throwable>>) new HashSet(Arrays.asList(clsArr)));
            }

            public ByThrowableType(Set<? extends Class<? extends Throwable>> set) {
                this.types = set;
            }

            public static FallbackStrategy ofOptionalTypes() {
                return new ByThrowableType((Class<? extends Throwable>[]) new Class[]{LinkageError.class, TypeNotPresentException.class});
            }

            public boolean isFallback(Class<?> cls, Throwable th) {
                for (Class isInstance : this.types) {
                    if (isInstance.isInstance(th)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    public interface InstallationListener {
        public static final Throwable SUPPRESS_ERROR = null;

        public static abstract class Adapter implements InstallationListener {
            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                return th;
            }

            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }
        }

        public enum NoOp implements InstallationListener {
            INSTANCE;

            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                return th;
            }

            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }
        }

        void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer);

        Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th);

        void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer);

        void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer);

        public enum ErrorSuppressing implements InstallationListener {
            INSTANCE;

            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
            }

            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                return SUPPRESS_ERROR;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class StreamWriting implements InstallationListener {
            protected static final String PREFIX = "[Byte Buddy]";
            private final PrintStream printStream;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.printStream.equals(((StreamWriting) obj).printStream);
            }

            public int hashCode() {
                return 527 + this.printStream.hashCode();
            }

            public StreamWriting(PrintStream printStream2) {
                this.printStream = printStream2;
            }

            public static InstallationListener toSystemOut() {
                return new StreamWriting(System.out);
            }

            public static InstallationListener toSystemError() {
                return new StreamWriting(System.err);
            }

            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                this.printStream.printf("[Byte Buddy] BEFORE_INSTALL %s on %s%n", new Object[]{resettableClassFileTransformer, instrumentation});
            }

            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                this.printStream.printf("[Byte Buddy] INSTALL %s on %s%n", new Object[]{resettableClassFileTransformer, instrumentation});
            }

            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                synchronized (this.printStream) {
                    this.printStream.printf("[Byte Buddy] ERROR %s on %s%n", new Object[]{resettableClassFileTransformer, instrumentation});
                    th.printStackTrace(this.printStream);
                }
                return th;
            }

            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                this.printStream.printf("[Byte Buddy] RESET %s on %s%n", new Object[]{resettableClassFileTransformer, instrumentation});
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Compound implements InstallationListener {
            private final List<InstallationListener> installationListeners;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.installationListeners.equals(((Compound) obj).installationListeners);
            }

            public int hashCode() {
                return 527 + this.installationListeners.hashCode();
            }

            public Compound(InstallationListener... installationListenerArr) {
                this((List<? extends InstallationListener>) Arrays.asList(installationListenerArr));
            }

            public Compound(List<? extends InstallationListener> list) {
                this.installationListeners = new ArrayList();
                for (InstallationListener installationListener : list) {
                    if (installationListener instanceof Compound) {
                        this.installationListeners.addAll(((Compound) installationListener).installationListeners);
                    } else if (!(installationListener instanceof NoOp)) {
                        this.installationListeners.add(installationListener);
                    }
                }
            }

            public void onBeforeInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                for (InstallationListener onBeforeInstall : this.installationListeners) {
                    onBeforeInstall.onBeforeInstall(instrumentation, resettableClassFileTransformer);
                }
            }

            public void onInstall(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                for (InstallationListener onInstall : this.installationListeners) {
                    onInstall.onInstall(instrumentation, resettableClassFileTransformer);
                }
            }

            public Throwable onError(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer, Throwable th) {
                for (InstallationListener next : this.installationListeners) {
                    if (th == SUPPRESS_ERROR) {
                        return SUPPRESS_ERROR;
                    }
                    th = next.onError(instrumentation, resettableClassFileTransformer, th);
                }
                return th;
            }

            public void onReset(Instrumentation instrumentation, ResettableClassFileTransformer resettableClassFileTransformer) {
                for (InstallationListener onReset : this.installationListeners) {
                    onReset.onReset(instrumentation, resettableClassFileTransformer);
                }
            }
        }
    }

    public enum RedefinitionStrategy {
        DISABLED(false, false) {
            public void apply(Instrumentation instrumentation, Listener listener, CircularityLock circularityLock, PoolStrategy poolStrategy, LocationStrategy locationStrategy, DiscoveryStrategy discoveryStrategy, BatchAllocator batchAllocator, Listener listener2, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, RawMatcher rawMatcher, RawMatcher rawMatcher2) {
            }

            /* access modifiers changed from: protected */
            public void check(Instrumentation instrumentation) {
                throw new IllegalStateException("Cannot apply redefinition on disabled strategy");
            }

            /* access modifiers changed from: protected */
            public Collector make() {
                throw new IllegalStateException("A disabled redefinition strategy cannot create a collector");
            }
        },
        REDEFINITION(true, false) {
            /* access modifiers changed from: protected */
            public void check(Instrumentation instrumentation) {
                if (!instrumentation.isRedefineClassesSupported()) {
                    throw new IllegalStateException("Cannot apply redefinition on " + instrumentation);
                }
            }

            /* access modifiers changed from: protected */
            public Collector make() {
                return new Collector.ForRedefinition();
            }
        },
        RETRANSFORMATION(true, true) {
            /* access modifiers changed from: protected */
            public void check(Instrumentation instrumentation) {
                if (!DISPATCHER.isRetransformClassesSupported(instrumentation)) {
                    throw new IllegalStateException("Cannot apply redefinition on " + instrumentation);
                }
            }

            /* access modifiers changed from: protected */
            public Collector make() {
                return new Collector.ForRetransformation();
            }
        };
        
        protected static final Dispatcher DISPATCHER = null;
        private final boolean enabled;
        private final boolean retransforming;

        /* access modifiers changed from: protected */
        public abstract void check(Instrumentation instrumentation);

        /* access modifiers changed from: protected */
        public abstract Collector make();

        static {
            DISPATCHER = (Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE);
        }

        private RedefinitionStrategy(boolean z, boolean z2) {
            this.enabled = z;
            this.retransforming = z2;
        }

        /* access modifiers changed from: protected */
        public boolean isRetransforming() {
            return this.retransforming;
        }

        /* access modifiers changed from: protected */
        public boolean isEnabled() {
            return this.enabled;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: net.bytebuddy.agent.builder.AgentBuilder$Listener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: net.bytebuddy.agent.builder.AgentBuilder$Listener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: java.lang.Class} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: net.bytebuddy.agent.builder.AgentBuilder$RawMatcher} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v10, resolved type: net.bytebuddy.utility.JavaModule} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: net.bytebuddy.utility.JavaModule} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: net.bytebuddy.agent.builder.AgentBuilder$Listener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: net.bytebuddy.agent.builder.AgentBuilder$Listener} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: net.bytebuddy.pool.TypePool} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: java.lang.Class} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: net.bytebuddy.agent.builder.AgentBuilder$RawMatcher} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: boolean} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v16, resolved type: net.bytebuddy.agent.builder.AgentBuilder$Listener} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00a6  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x00d0 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void apply(java.lang.instrument.Instrumentation r25, net.bytebuddy.agent.builder.AgentBuilder.Listener r26, net.bytebuddy.agent.builder.AgentBuilder.CircularityLock r27, net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy r28, net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy r29, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy r30, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator r31, net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener r32, net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy r33, net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy r34, net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy r35, net.bytebuddy.agent.builder.AgentBuilder.RawMatcher r36, net.bytebuddy.agent.builder.AgentBuilder.RawMatcher r37) {
            /*
                r24 = this;
                r9 = r25
                r8 = r26
                r24.check(r25)
                r0 = r30
                java.lang.Iterable r0 = r0.resolve(r9)
                java.util.Iterator r19 = r0.iterator()
                r20 = 0
                r21 = 0
            L_0x0015:
                boolean r0 = r19.hasNext()
                if (r0 == 0) goto L_0x0133
                java.lang.Object r0 = r19.next()
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Collector r22 = r24.make()
                java.util.Iterator r23 = r0.iterator()
            L_0x0029:
                boolean r0 = r23.hasNext()
                if (r0 == 0) goto L_0x0118
                java.lang.Object r0 = r23.next()
                r15 = r0
                java.lang.Class r15 = (java.lang.Class) r15
                if (r15 == 0) goto L_0x0114
                boolean r0 = r15.isArray()
                if (r0 != 0) goto L_0x0114
                r14 = r33
                boolean r0 = r14.isInstrumented(r15)
                if (r0 != 0) goto L_0x0047
                goto L_0x0029
            L_0x0047:
                net.bytebuddy.utility.JavaModule r13 = net.bytebuddy.utility.JavaModule.ofType(r15)
                r12 = 1
                java.lang.ClassLoader r0 = r15.getClassLoader()     // Catch:{ all -> 0x00df }
                r11 = r29
                net.bytebuddy.dynamic.ClassFileLocator r0 = r11.classFileLocator(r0, r13)     // Catch:{ all -> 0x00df }
                java.lang.ClassLoader r1 = r15.getClassLoader()     // Catch:{ all -> 0x00df }
                r10 = r28
                net.bytebuddy.pool.TypePool r7 = r10.typePool(r0, r1)     // Catch:{ all -> 0x00df }
                java.lang.String r2 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r15)     // Catch:{ all -> 0x009b }
                java.lang.ClassLoader r6 = r15.getClassLoader()     // Catch:{ all -> 0x009b }
                r1 = r34
                r3 = r15
                r4 = r7
                r5 = r27
                r8 = r7
                r7 = r13
                net.bytebuddy.description.type.TypeDescription r0 = r1.apply(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0099 }
                net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Dispatcher r1 = DISPATCHER     // Catch:{ all -> 0x0099 }
                boolean r1 = r1.isModifiableClass(r9, r15)     // Catch:{ all -> 0x0099 }
                if (r1 != 0) goto L_0x007f
                r18 = 1
                goto L_0x0081
            L_0x007f:
                r18 = 0
            L_0x0081:
                r10 = r22
                r11 = r36
                r7 = 1
                r12 = r37
                r6 = r13
                r13 = r26
                r14 = r0
                r5 = r15
                r16 = r5
                r17 = r6
                r10.consider(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ all -> 0x0097 }
                r10 = r35
                goto L_0x00cb
            L_0x0097:
                r0 = move-exception
                goto L_0x00a0
            L_0x0099:
                r0 = move-exception
                goto L_0x009d
            L_0x009b:
                r0 = move-exception
                r8 = r7
            L_0x009d:
                r6 = r13
                r5 = r15
                r7 = 1
            L_0x00a0:
                boolean r1 = r34.isLoadedFirst()     // Catch:{ all -> 0x00d8 }
                if (r1 == 0) goto L_0x00d0
                r10 = r35
                boolean r1 = r10.isFallback(r5, r0)     // Catch:{ all -> 0x00ce }
                if (r1 == 0) goto L_0x00d2
                java.lang.String r0 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r5)     // Catch:{ all -> 0x00ce }
                net.bytebuddy.pool.TypePool$Resolution r0 = r8.describe(r0)     // Catch:{ all -> 0x00ce }
                net.bytebuddy.description.type.TypeDescription r0 = r0.resolve()     // Catch:{ all -> 0x00ce }
                r1 = r22
                r2 = r36
                r3 = r37
                r4 = r26
                r8 = r5
                r5 = r0
                r11 = r6
                r6 = r8
                r12 = 1
                r7 = r11
                r1.consider(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00d6 }
            L_0x00cb:
                r13 = r26
                goto L_0x0111
            L_0x00ce:
                r0 = move-exception
                goto L_0x00db
            L_0x00d0:
                r10 = r35
            L_0x00d2:
                r8 = r5
                r11 = r6
                r12 = 1
                throw r0     // Catch:{ all -> 0x00d6 }
            L_0x00d6:
                r0 = move-exception
                goto L_0x00e4
            L_0x00d8:
                r0 = move-exception
                r10 = r35
            L_0x00db:
                r8 = r5
                r11 = r6
                r12 = 1
                goto L_0x00e4
            L_0x00df:
                r0 = move-exception
                r10 = r35
                r11 = r13
                r8 = r15
            L_0x00e4:
                r6 = r0
                java.lang.String r2 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r8)     // Catch:{ all -> 0x0102 }
                java.lang.ClassLoader r3 = r8.getClassLoader()     // Catch:{ all -> 0x0102 }
                r5 = 1
                r1 = r26
                r4 = r11
                r1.onError(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x0102 }
                java.lang.String r0 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r8)     // Catch:{ all -> 0x00cb }
                java.lang.ClassLoader r1 = r8.getClassLoader()     // Catch:{ all -> 0x00cb }
                r13 = r26
                r13.onComplete(r0, r1, r11, r12)     // Catch:{ all -> 0x0111 }
                goto L_0x0111
            L_0x0102:
                r0 = move-exception
                r13 = r26
                java.lang.String r1 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r8)     // Catch:{ all -> 0x0111 }
                java.lang.ClassLoader r2 = r8.getClassLoader()     // Catch:{ all -> 0x0111 }
                r13.onComplete(r1, r2, r11, r12)     // Catch:{ all -> 0x0111 }
                throw r0     // Catch:{ all -> 0x0111 }
            L_0x0111:
                r8 = r13
                goto L_0x0029
            L_0x0114:
                r10 = r35
                goto L_0x0029
            L_0x0118:
                r10 = r35
                r13 = r8
                r1 = r22
                r2 = r25
                r3 = r27
                r4 = r29
                r5 = r26
                r6 = r31
                r7 = r32
                r8 = r21
                int r21 = r1.apply(r2, r3, r4, r5, r6, r7, r8)
                r8 = r26
                goto L_0x0015
            L_0x0133:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.apply(java.lang.instrument.Instrumentation, net.bytebuddy.agent.builder.AgentBuilder$Listener, net.bytebuddy.agent.builder.AgentBuilder$CircularityLock, net.bytebuddy.agent.builder.AgentBuilder$PoolStrategy, net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy, net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$DiscoveryStrategy, net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$BatchAllocator, net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Listener, net.bytebuddy.agent.builder.AgentBuilder$LambdaInstrumentationStrategy, net.bytebuddy.agent.builder.AgentBuilder$DescriptionStrategy, net.bytebuddy.agent.builder.AgentBuilder$FallbackStrategy, net.bytebuddy.agent.builder.AgentBuilder$RawMatcher, net.bytebuddy.agent.builder.AgentBuilder$RawMatcher):void");
        }

        public interface BatchAllocator {
            public static final int FIRST_BATCH = 0;

            Iterable<? extends List<Class<?>>> batch(List<Class<?>> list);

            public enum ForTotal implements BatchAllocator {
                INSTANCE;

                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    if (list.isEmpty()) {
                        return Collections.emptySet();
                    }
                    return Collections.singleton(list);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForFixedSize implements BatchAllocator {
                private final int size;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.size == ((ForFixedSize) obj).size;
                }

                public int hashCode() {
                    return 527 + this.size;
                }

                protected ForFixedSize(int i) {
                    this.size = i;
                }

                public static BatchAllocator ofSize(int i) {
                    if (i > 0) {
                        return new ForFixedSize(i);
                    }
                    if (i == 0) {
                        return ForTotal.INSTANCE;
                    }
                    throw new IllegalArgumentException("Cannot define a batch with a negative size: " + i);
                }

                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    while (i < list.size()) {
                        arrayList.add(new ArrayList(list.subList(i, Math.min(list.size(), this.size + i))));
                        i += this.size;
                    }
                    return arrayList;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForMatchedGrouping implements BatchAllocator {
                private final Collection<? extends ElementMatcher<? super TypeDescription>> matchers;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matchers.equals(((ForMatchedGrouping) obj).matchers);
                }

                public int hashCode() {
                    return 527 + this.matchers.hashCode();
                }

                public ForMatchedGrouping(ElementMatcher<? super TypeDescription>... elementMatcherArr) {
                    this((Collection<? extends ElementMatcher<? super TypeDescription>>) new LinkedHashSet(Arrays.asList(elementMatcherArr)));
                }

                public ForMatchedGrouping(Collection<? extends ElementMatcher<? super TypeDescription>> collection) {
                    this.matchers = collection;
                }

                public BatchAllocator withMinimum(int i) {
                    return Slicing.withMinimum(i, this);
                }

                public BatchAllocator withMaximum(int i) {
                    return Slicing.withMaximum(i, this);
                }

                public BatchAllocator withinRange(int i, int i2) {
                    return Slicing.withinRange(i, i2, this);
                }

                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    ArrayList arrayList = new ArrayList();
                    for (ElementMatcher put : this.matchers) {
                        linkedHashMap.put(put, new ArrayList());
                    }
                    for (Class next : list) {
                        Iterator<? extends ElementMatcher<? super TypeDescription>> it = this.matchers.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                arrayList.add(next);
                                break;
                            }
                            ElementMatcher elementMatcher = (ElementMatcher) it.next();
                            if (elementMatcher.matches(TypeDescription.ForLoadedType.of(next))) {
                                ((List) linkedHashMap.get(elementMatcher)).add(next);
                                break;
                            }
                        }
                    }
                    ArrayList arrayList2 = new ArrayList(this.matchers.size() + 1);
                    for (List list2 : linkedHashMap.values()) {
                        if (!list2.isEmpty()) {
                            arrayList2.add(list2);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        arrayList2.add(arrayList);
                    }
                    return arrayList2;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Slicing implements BatchAllocator {
                private final BatchAllocator batchAllocator;
                private final int maximum;
                private final int minimum;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Slicing slicing = (Slicing) obj;
                    return this.minimum == slicing.minimum && this.maximum == slicing.maximum && this.batchAllocator.equals(slicing.batchAllocator);
                }

                public int hashCode() {
                    return ((((527 + this.minimum) * 31) + this.maximum) * 31) + this.batchAllocator.hashCode();
                }

                protected Slicing(int i, int i2, BatchAllocator batchAllocator2) {
                    this.minimum = i;
                    this.maximum = i2;
                    this.batchAllocator = batchAllocator2;
                }

                public static BatchAllocator withMinimum(int i, BatchAllocator batchAllocator2) {
                    return withinRange(i, Integer.MAX_VALUE, batchAllocator2);
                }

                public static BatchAllocator withMaximum(int i, BatchAllocator batchAllocator2) {
                    return withinRange(1, i, batchAllocator2);
                }

                public static BatchAllocator withinRange(int i, int i2, BatchAllocator batchAllocator2) {
                    if (i <= 0) {
                        throw new IllegalArgumentException("Minimum must be a positive number: " + i);
                    } else if (i <= i2) {
                        return new Slicing(i, i2, batchAllocator2);
                    } else {
                        throw new IllegalArgumentException("Minimum must not be bigger than maximum: " + i + " >" + i2);
                    }
                }

                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    return new SlicingIterable(this.minimum, this.maximum, this.batchAllocator.batch(list));
                }

                protected static class SlicingIterable implements Iterable<List<Class<?>>> {
                    private final Iterable<? extends List<Class<?>>> iterable;
                    private final int maximum;
                    private final int minimum;

                    protected SlicingIterable(int i, int i2, Iterable<? extends List<Class<?>>> iterable2) {
                        this.minimum = i;
                        this.maximum = i2;
                        this.iterable = iterable2;
                    }

                    public Iterator<List<Class<?>>> iterator() {
                        return new SlicingIterator(this.minimum, this.maximum, this.iterable.iterator());
                    }

                    protected static class SlicingIterator implements Iterator<List<Class<?>>> {
                        private List<Class<?>> buffer;
                        private final Iterator<? extends List<Class<?>>> iterator;
                        private final int maximum;
                        private final int minimum;

                        protected SlicingIterator(int i, int i2, Iterator<? extends List<Class<?>>> it) {
                            this.minimum = i;
                            this.maximum = i2;
                            this.iterator = it;
                            this.buffer = new ArrayList();
                        }

                        public boolean hasNext() {
                            return !this.buffer.isEmpty() || this.iterator.hasNext();
                        }

                        public List<Class<?>> next() {
                            if (this.buffer.isEmpty()) {
                                this.buffer = (List) this.iterator.next();
                            }
                            while (this.buffer.size() < this.minimum && this.iterator.hasNext()) {
                                this.buffer.addAll((Collection) this.iterator.next());
                            }
                            int size = this.buffer.size();
                            int i = this.maximum;
                            if (size > i) {
                                try {
                                    return this.buffer.subList(0, i);
                                } finally {
                                    List<Class<?>> list = this.buffer;
                                    this.buffer = new ArrayList(list.subList(this.maximum, list.size()));
                                }
                            } else {
                                try {
                                    return this.buffer;
                                } finally {
                                    this.buffer = new ArrayList();
                                }
                            }
                        }

                        public void remove() {
                            throw new UnsupportedOperationException("remove");
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Partitioning implements BatchAllocator {
                private final int parts;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.parts == ((Partitioning) obj).parts;
                }

                public int hashCode() {
                    return 527 + this.parts;
                }

                protected Partitioning(int i) {
                    this.parts = i;
                }

                public static BatchAllocator of(int i) {
                    if (i >= 1) {
                        return new Partitioning(i);
                    }
                    throw new IllegalArgumentException("A batch size must be positive: " + i);
                }

                public Iterable<? extends List<Class<?>>> batch(List<Class<?>> list) {
                    if (list.isEmpty()) {
                        return Collections.emptyList();
                    }
                    ArrayList arrayList = new ArrayList();
                    int size = list.size() / this.parts;
                    int size2 = list.size() % this.parts;
                    int i = size2;
                    while (i < list.size()) {
                        int i2 = i + size;
                        arrayList.add(new ArrayList(list.subList(i, i2)));
                        i = i2;
                    }
                    if (arrayList.isEmpty()) {
                        return Collections.singletonList(list);
                    }
                    ((List) arrayList.get(0)).addAll(0, list.subList(0, size2));
                    return arrayList;
                }
            }
        }

        public interface Listener {

            public enum ErrorEscalating implements Listener {
                FAIL_FAST {
                    public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                    }

                    public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                        throw new IllegalStateException("Could not transform any of " + list, th);
                    }
                },
                FAIL_LAST {
                    public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                        return Collections.emptyList();
                    }

                    public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                        if (!map.isEmpty()) {
                            throw new IllegalStateException("Could not transform any of " + map);
                        }
                    }
                };

                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                }
            }

            void onBatch(int i, List<Class<?>> list, List<Class<?>> list2);

            void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map);

            Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2);

            public enum NoOp implements Listener {
                INSTANCE;

                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                }

                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                }

                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    return Collections.emptyList();
                }
            }

            public enum Yielding implements Listener {
                INSTANCE;

                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                }

                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    if (i > 0) {
                        Thread.yield();
                    }
                }

                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    return Collections.emptyList();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class Adapter implements Listener {
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass();
                }

                public int hashCode() {
                    return 17;
                }

                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                }

                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                }

                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    return Collections.emptyList();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class BatchReallocator extends Adapter {
                private final BatchAllocator batchAllocator;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.batchAllocator.equals(((BatchReallocator) obj).batchAllocator);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.batchAllocator.hashCode();
                }

                public BatchReallocator(BatchAllocator batchAllocator2) {
                    this.batchAllocator = batchAllocator2;
                }

                public static Listener splitting() {
                    return new BatchReallocator(new BatchAllocator.Partitioning(2));
                }

                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    if (list.size() < 2) {
                        return Collections.emptyList();
                    }
                    return this.batchAllocator.batch(list);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Pausing extends Adapter {
                private final long value;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((Pausing) obj).value;
                }

                public int hashCode() {
                    long j = this.value;
                    return (super.hashCode() * 31) + ((int) (j ^ (j >>> 32)));
                }

                protected Pausing(long j) {
                    this.value = j;
                }

                public static Listener of(long j, TimeUnit timeUnit) {
                    int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
                    if (i > 0) {
                        return new Pausing(timeUnit.toMillis(j));
                    }
                    if (i == 0) {
                        return NoOp.INSTANCE;
                    }
                    throw new IllegalArgumentException("Cannot sleep for a non-positive amount of time: " + j);
                }

                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    if (i > 0) {
                        try {
                            Thread.sleep(this.value);
                        } catch (InterruptedException e) {
                            throw new RuntimeException("Sleep was interrupted", e);
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class StreamWriting implements Listener {
                private final PrintStream printStream;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.printStream.equals(((StreamWriting) obj).printStream);
                }

                public int hashCode() {
                    return 527 + this.printStream.hashCode();
                }

                public StreamWriting(PrintStream printStream2) {
                    this.printStream = printStream2;
                }

                public static Listener toSystemOut() {
                    return new StreamWriting(System.out);
                }

                public static Listener toSystemError() {
                    return new StreamWriting(System.err);
                }

                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    this.printStream.printf("[Byte Buddy] REDEFINE BATCH #%d [%d of %d type(s)]%n", new Object[]{Integer.valueOf(i), Integer.valueOf(list.size()), Integer.valueOf(list2.size())});
                }

                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    synchronized (this.printStream) {
                        this.printStream.printf("[Byte Buddy] REDEFINE ERROR #%d [%d of %d type(s)]%n", new Object[]{Integer.valueOf(i), Integer.valueOf(list.size()), Integer.valueOf(list2.size())});
                        th.printStackTrace(this.printStream);
                    }
                    return Collections.emptyList();
                }

                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                    this.printStream.printf("[Byte Buddy] REDEFINE COMPLETE #%d batch(es) containing %d types [%d failed batch(es)]%n", new Object[]{Integer.valueOf(i), Integer.valueOf(list.size()), Integer.valueOf(map.size())});
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Compound implements Listener {
                private final List<Listener> listeners;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.listeners.equals(((Compound) obj).listeners);
                }

                public int hashCode() {
                    return 527 + this.listeners.hashCode();
                }

                public Compound(Listener... listenerArr) {
                    this((List<? extends Listener>) Arrays.asList(listenerArr));
                }

                public Compound(List<? extends Listener> list) {
                    this.listeners = new ArrayList();
                    for (Listener listener : list) {
                        if (listener instanceof Compound) {
                            this.listeners.addAll(((Compound) listener).listeners);
                        } else if (!(listener instanceof NoOp)) {
                            this.listeners.add(listener);
                        }
                    }
                }

                public void onBatch(int i, List<Class<?>> list, List<Class<?>> list2) {
                    for (Listener onBatch : this.listeners) {
                        onBatch.onBatch(i, list, list2);
                    }
                }

                public Iterable<? extends List<Class<?>>> onError(int i, List<Class<?>> list, Throwable th, List<Class<?>> list2) {
                    ArrayList arrayList = new ArrayList();
                    for (Listener onError : this.listeners) {
                        arrayList.add(onError.onError(i, list, th, list2));
                    }
                    return new CompoundIterable(arrayList);
                }

                public void onComplete(int i, List<Class<?>> list, Map<List<Class<?>>, Throwable> map) {
                    for (Listener onComplete : this.listeners) {
                        onComplete.onComplete(i, list, map);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class CompoundIterable implements Iterable<List<Class<?>>> {
                    private final List<Iterable<? extends List<Class<?>>>> iterables;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.iterables.equals(((CompoundIterable) obj).iterables);
                    }

                    public int hashCode() {
                        return 527 + this.iterables.hashCode();
                    }

                    protected CompoundIterable(List<Iterable<? extends List<Class<?>>>> list) {
                        this.iterables = list;
                    }

                    public Iterator<List<Class<?>>> iterator() {
                        return new CompoundIterator(new ArrayList(this.iterables));
                    }

                    protected static class CompoundIterator implements Iterator<List<Class<?>>> {
                        private final List<Iterable<? extends List<Class<?>>>> backlog;
                        private Iterator<? extends List<Class<?>>> current;

                        protected CompoundIterator(List<Iterable<? extends List<Class<?>>>> list) {
                            this.backlog = list;
                            forward();
                        }

                        public boolean hasNext() {
                            Iterator<? extends List<Class<?>>> it = this.current;
                            return it != null && it.hasNext();
                        }

                        public List<Class<?>> next() {
                            try {
                                if (this.current != null) {
                                    return (List) this.current.next();
                                }
                                throw new NoSuchElementException();
                            } finally {
                                forward();
                            }
                        }

                        private void forward() {
                            while (true) {
                                Iterator<? extends List<Class<?>>> it = this.current;
                                if ((it == null || !it.hasNext()) && !this.backlog.isEmpty()) {
                                    this.current = this.backlog.remove(0).iterator();
                                } else {
                                    return;
                                }
                            }
                        }

                        public void remove() {
                            throw new UnsupportedOperationException("remove");
                        }
                    }
                }
            }
        }

        public interface DiscoveryStrategy {
            Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation);

            public enum SinglePass implements DiscoveryStrategy {
                INSTANCE;

                public Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation) {
                    return Collections.singleton(Arrays.asList(instrumentation.getAllLoadedClasses()));
                }
            }

            public enum Reiterating implements DiscoveryStrategy {
                INSTANCE;

                public Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation) {
                    return new ReiteratingIterable(instrumentation);
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ReiteratingIterable implements Iterable<Iterable<Class<?>>> {
                    private final Instrumentation instrumentation;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.instrumentation.equals(((ReiteratingIterable) obj).instrumentation);
                    }

                    public int hashCode() {
                        return 527 + this.instrumentation.hashCode();
                    }

                    protected ReiteratingIterable(Instrumentation instrumentation2) {
                        this.instrumentation = instrumentation2;
                    }

                    public Iterator<Iterable<Class<?>>> iterator() {
                        return new ReiteratingIterator(this.instrumentation);
                    }
                }

                protected static class ReiteratingIterator implements Iterator<Iterable<Class<?>>> {
                    private final Instrumentation instrumentation;
                    private final Set<Class<?>> processed;
                    private List<Class<?>> types;

                    protected ReiteratingIterator(Instrumentation instrumentation2) {
                        this.instrumentation = instrumentation2;
                        this.processed = new HashSet();
                    }

                    public boolean hasNext() {
                        if (this.types == null) {
                            this.types = new ArrayList();
                            for (Class cls : this.instrumentation.getAllLoadedClasses()) {
                                if (cls != null && this.processed.add(cls)) {
                                    this.types.add(cls);
                                }
                            }
                        }
                        return !this.types.isEmpty();
                    }

                    public Iterable<Class<?>> next() {
                        if (hasNext()) {
                            try {
                                return this.types;
                            } finally {
                                this.types = null;
                            }
                        } else {
                            throw new NoSuchElementException();
                        }
                    }

                    public void remove() {
                        throw new UnsupportedOperationException("remove");
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Explicit implements DiscoveryStrategy {
                private final Set<Class<?>> types;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.types.equals(((Explicit) obj).types);
                }

                public int hashCode() {
                    return 527 + this.types.hashCode();
                }

                public Explicit(Class<?>... clsArr) {
                    this((Set<Class<?>>) new LinkedHashSet(Arrays.asList(clsArr)));
                }

                public Explicit(Set<Class<?>> set) {
                    this.types = set;
                }

                public Iterable<Iterable<Class<?>>> resolve(Instrumentation instrumentation) {
                    return Collections.singleton(this.types);
                }
            }
        }

        public interface ResubmissionScheduler {
            boolean isAlive();

            Cancelable schedule(Runnable runnable);

            public interface Cancelable {

                public enum NoOp implements Cancelable {
                    INSTANCE;

                    public void cancel() {
                    }
                }

                void cancel();

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForFuture implements Cancelable {
                    private final Future<?> future;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.future.equals(((ForFuture) obj).future);
                    }

                    public int hashCode() {
                        return 527 + this.future.hashCode();
                    }

                    public ForFuture(Future<?> future2) {
                        this.future = future2;
                    }

                    public void cancel() {
                        this.future.cancel(true);
                    }
                }
            }

            public enum NoOp implements ResubmissionScheduler {
                INSTANCE;

                public boolean isAlive() {
                    return false;
                }

                public Cancelable schedule(Runnable runnable) {
                    return Cancelable.NoOp.INSTANCE;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class AtFixedRate implements ResubmissionScheduler {
                private final ScheduledExecutorService scheduledExecutorService;
                private final long time;
                private final TimeUnit timeUnit;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AtFixedRate atFixedRate = (AtFixedRate) obj;
                    return this.time == atFixedRate.time && this.timeUnit.equals(atFixedRate.timeUnit) && this.scheduledExecutorService.equals(atFixedRate.scheduledExecutorService);
                }

                public int hashCode() {
                    long j = this.time;
                    return ((((527 + this.scheduledExecutorService.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.timeUnit.hashCode();
                }

                public AtFixedRate(ScheduledExecutorService scheduledExecutorService2, long j, TimeUnit timeUnit2) {
                    this.scheduledExecutorService = scheduledExecutorService2;
                    this.time = j;
                    this.timeUnit = timeUnit2;
                }

                public boolean isAlive() {
                    return !this.scheduledExecutorService.isShutdown();
                }

                public Cancelable schedule(Runnable runnable) {
                    ScheduledExecutorService scheduledExecutorService2 = this.scheduledExecutorService;
                    long j = this.time;
                    return new Cancelable.ForFuture(scheduledExecutorService2.scheduleAtFixedRate(runnable, j, j, this.timeUnit));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class WithFixedDelay implements ResubmissionScheduler {
                private final ScheduledExecutorService scheduledExecutorService;
                private final long time;
                private final TimeUnit timeUnit;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    WithFixedDelay withFixedDelay = (WithFixedDelay) obj;
                    return this.time == withFixedDelay.time && this.timeUnit.equals(withFixedDelay.timeUnit) && this.scheduledExecutorService.equals(withFixedDelay.scheduledExecutorService);
                }

                public int hashCode() {
                    long j = this.time;
                    return ((((527 + this.scheduledExecutorService.hashCode()) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.timeUnit.hashCode();
                }

                public WithFixedDelay(ScheduledExecutorService scheduledExecutorService2, long j, TimeUnit timeUnit2) {
                    this.scheduledExecutorService = scheduledExecutorService2;
                    this.time = j;
                    this.timeUnit = timeUnit2;
                }

                public boolean isAlive() {
                    return !this.scheduledExecutorService.isShutdown();
                }

                public Cancelable schedule(Runnable runnable) {
                    ScheduledExecutorService scheduledExecutorService2 = this.scheduledExecutorService;
                    long j = this.time;
                    return new Cancelable.ForFuture(scheduledExecutorService2.scheduleWithFixedDelay(runnable, j, j, this.timeUnit));
                }
            }
        }

        protected interface ResubmissionStrategy {
            Installation apply(Instrumentation instrumentation, LocationStrategy locationStrategy, Listener listener, InstallationListener installationListener, CircularityLock circularityLock, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy, BatchAllocator batchAllocator, Listener listener2);

            public enum Disabled implements ResubmissionStrategy {
                INSTANCE;

                public Installation apply(Instrumentation instrumentation, LocationStrategy locationStrategy, Listener listener, InstallationListener installationListener, CircularityLock circularityLock, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy, BatchAllocator batchAllocator, Listener listener2) {
                    return new Installation(listener, installationListener);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Enabled implements ResubmissionStrategy {
                private final ElementMatcher<? super Throwable> matcher;
                private final ResubmissionScheduler resubmissionScheduler;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Enabled enabled = (Enabled) obj;
                    return this.resubmissionScheduler.equals(enabled.resubmissionScheduler) && this.matcher.equals(enabled.matcher);
                }

                public int hashCode() {
                    return ((527 + this.resubmissionScheduler.hashCode()) * 31) + this.matcher.hashCode();
                }

                protected Enabled(ResubmissionScheduler resubmissionScheduler2, ElementMatcher<? super Throwable> elementMatcher) {
                    this.resubmissionScheduler = resubmissionScheduler2;
                    this.matcher = elementMatcher;
                }

                public Installation apply(Instrumentation instrumentation, LocationStrategy locationStrategy, Listener listener, InstallationListener installationListener, CircularityLock circularityLock, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy, BatchAllocator batchAllocator, Listener listener2) {
                    Listener listener3 = listener;
                    InstallationListener installationListener2 = installationListener;
                    if (!redefinitionStrategy.isEnabled() || !this.resubmissionScheduler.isAlive()) {
                        return new Installation(listener3, installationListener2);
                    }
                    ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                    return new Installation(new Listener.Compound(new ResubmissionListener(this.matcher, concurrentHashMap), listener3), new InstallationListener.Compound(new ResubmissionInstallationListener(this.resubmissionScheduler, instrumentation, locationStrategy, listener, circularityLock, rawMatcher, redefinitionStrategy, batchAllocator, listener2, concurrentHashMap), installationListener2));
                }

                protected static class ResubmissionListener extends Listener.Adapter {
                    private final ElementMatcher<? super Throwable> matcher;
                    private final ConcurrentMap<StorageKey, Set<String>> types;

                    protected ResubmissionListener(ElementMatcher<? super Throwable> elementMatcher, ConcurrentMap<StorageKey, Set<String>> concurrentMap) {
                        this.matcher = elementMatcher;
                        this.types = concurrentMap;
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
                        r3 = new net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy.Enabled.ResubmissionListener.ConcurrentHashSet();
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onError(java.lang.String r1, java.lang.ClassLoader r2, net.bytebuddy.utility.JavaModule r3, boolean r4, java.lang.Throwable r5) {
                        /*
                            r0 = this;
                            if (r4 != 0) goto L_0x0031
                            net.bytebuddy.matcher.ElementMatcher<? super java.lang.Throwable> r3 = r0.matcher
                            boolean r3 = r3.matches(r5)
                            if (r3 == 0) goto L_0x0031
                            java.util.concurrent.ConcurrentMap<net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$StorageKey, java.util.Set<java.lang.String>> r3 = r0.types
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$LookupKey r4 = new net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$LookupKey
                            r4.<init>(r2)
                            java.lang.Object r3 = r3.get(r4)
                            java.util.Set r3 = (java.util.Set) r3
                            if (r3 != 0) goto L_0x002e
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$ResubmissionListener$ConcurrentHashSet r3 = new net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$ResubmissionListener$ConcurrentHashSet
                            r3.<init>()
                            java.util.concurrent.ConcurrentMap<net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$StorageKey, java.util.Set<java.lang.String>> r4 = r0.types
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$StorageKey r5 = new net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$StorageKey
                            r5.<init>(r2)
                            java.lang.Object r2 = r4.putIfAbsent(r5, r3)
                            java.util.Set r2 = (java.util.Set) r2
                            if (r2 == 0) goto L_0x002e
                            r3 = r2
                        L_0x002e:
                            r3.add(r1)
                        L_0x0031:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy.Enabled.ResubmissionListener.onError(java.lang.String, java.lang.ClassLoader, net.bytebuddy.utility.JavaModule, boolean, java.lang.Throwable):void");
                    }

                    protected static class ConcurrentHashSet<T> extends AbstractSet<T> {
                        private final ConcurrentMap<T, Boolean> delegate;

                        protected ConcurrentHashSet() {
                            this.delegate = new ConcurrentHashMap();
                        }

                        public boolean add(T t) {
                            return this.delegate.put(t, Boolean.TRUE) == null;
                        }

                        public boolean remove(Object obj) {
                            return this.delegate.remove(obj) != null;
                        }

                        public Iterator<T> iterator() {
                            return this.delegate.keySet().iterator();
                        }

                        public int size() {
                            return this.delegate.size();
                        }
                    }
                }

                protected static class ResubmissionInstallationListener extends InstallationListener.Adapter implements Runnable {
                    private volatile ResubmissionScheduler.Cancelable cancelable;
                    private final CircularityLock circularityLock;
                    private final Instrumentation instrumentation;
                    private final Listener listener;
                    private final LocationStrategy locationStrategy;
                    private final RawMatcher matcher;
                    private final BatchAllocator redefinitionBatchAllocator;
                    private final Listener redefinitionBatchListener;
                    private final RedefinitionStrategy redefinitionStrategy;
                    private final ResubmissionScheduler resubmissionScheduler;
                    private final ConcurrentMap<StorageKey, Set<String>> types;

                    protected ResubmissionInstallationListener(ResubmissionScheduler resubmissionScheduler2, Instrumentation instrumentation2, LocationStrategy locationStrategy2, Listener listener2, CircularityLock circularityLock2, RawMatcher rawMatcher, RedefinitionStrategy redefinitionStrategy2, BatchAllocator batchAllocator, Listener listener3, ConcurrentMap<StorageKey, Set<String>> concurrentMap) {
                        this.resubmissionScheduler = resubmissionScheduler2;
                        this.instrumentation = instrumentation2;
                        this.locationStrategy = locationStrategy2;
                        this.listener = listener2;
                        this.circularityLock = circularityLock2;
                        this.matcher = rawMatcher;
                        this.redefinitionStrategy = redefinitionStrategy2;
                        this.redefinitionBatchAllocator = batchAllocator;
                        this.redefinitionBatchListener = listener3;
                        this.types = concurrentMap;
                    }

                    public void onInstall(Instrumentation instrumentation2, ResettableClassFileTransformer resettableClassFileTransformer) {
                        this.cancelable = this.resubmissionScheduler.schedule(this);
                    }

                    public void onReset(Instrumentation instrumentation2, ResettableClassFileTransformer resettableClassFileTransformer) {
                        ResubmissionScheduler.Cancelable cancelable2 = this.cancelable;
                        if (cancelable2 != null) {
                            cancelable2.cancel();
                        }
                    }

                    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x00ae */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void run() {
                        /*
                            r13 = this;
                            net.bytebuddy.agent.builder.AgentBuilder$CircularityLock r0 = r13.circularityLock
                            boolean r0 = r0.acquire()
                            java.util.concurrent.ConcurrentMap<net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$StorageKey, java.util.Set<java.lang.String>> r1 = r13.types     // Catch:{ all -> 0x00ec }
                            java.util.Set r1 = r1.entrySet()     // Catch:{ all -> 0x00ec }
                            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00ec }
                            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x00ec }
                            r2.<init>()     // Catch:{ all -> 0x00ec }
                        L_0x0015:
                            boolean r3 = java.lang.Thread.interrupted()     // Catch:{ all -> 0x00ec }
                            if (r3 != 0) goto L_0x00c5
                            boolean r3 = r1.hasNext()     // Catch:{ all -> 0x00ec }
                            if (r3 == 0) goto L_0x00c5
                            java.lang.Object r3 = r1.next()     // Catch:{ all -> 0x00ec }
                            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch:{ all -> 0x00ec }
                            java.lang.Object r4 = r3.getKey()     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$StorageKey r4 = (net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy.Enabled.StorageKey) r4     // Catch:{ all -> 0x00ec }
                            java.lang.Object r4 = r4.get()     // Catch:{ all -> 0x00ec }
                            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x00ec }
                            if (r4 != 0) goto L_0x0046
                            java.lang.Object r5 = r3.getKey()     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Enabled$StorageKey r5 = (net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy.Enabled.StorageKey) r5     // Catch:{ all -> 0x00ec }
                            boolean r5 = r5.isBootstrapLoader()     // Catch:{ all -> 0x00ec }
                            if (r5 == 0) goto L_0x0042
                            goto L_0x0046
                        L_0x0042:
                            r1.remove()     // Catch:{ all -> 0x00ec }
                            goto L_0x0015
                        L_0x0046:
                            java.lang.Object r3 = r3.getValue()     // Catch:{ all -> 0x00ec }
                            java.util.Set r3 = (java.util.Set) r3     // Catch:{ all -> 0x00ec }
                            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00ec }
                        L_0x0050:
                            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x00ec }
                            if (r5 == 0) goto L_0x0015
                            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x00ae }
                            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x00ae }
                            r6 = 0
                            java.lang.Class r5 = java.lang.Class.forName(r5, r6, r4)     // Catch:{ all -> 0x00ae }
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Dispatcher r6 = net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DISPATCHER     // Catch:{ all -> 0x0088 }
                            java.lang.instrument.Instrumentation r7 = r13.instrumentation     // Catch:{ all -> 0x0088 }
                            boolean r6 = r6.isModifiableClass(r7, r5)     // Catch:{ all -> 0x0088 }
                            if (r6 == 0) goto L_0x00ae
                            net.bytebuddy.agent.builder.AgentBuilder$RawMatcher r7 = r13.matcher     // Catch:{ all -> 0x0088 }
                            net.bytebuddy.description.type.TypeDescription r8 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.of(r5)     // Catch:{ all -> 0x0088 }
                            java.lang.ClassLoader r9 = r5.getClassLoader()     // Catch:{ all -> 0x0088 }
                            net.bytebuddy.utility.JavaModule r10 = net.bytebuddy.utility.JavaModule.ofType(r5)     // Catch:{ all -> 0x0088 }
                            java.security.ProtectionDomain r12 = r5.getProtectionDomain()     // Catch:{ all -> 0x0088 }
                            r11 = r5
                            boolean r6 = r7.matches(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0088 }
                            if (r6 == 0) goto L_0x00ae
                            r2.add(r5)     // Catch:{ all -> 0x0088 }
                            goto L_0x00ae
                        L_0x0088:
                            r6 = move-exception
                            r12 = r6
                            r6 = 1
                            net.bytebuddy.agent.builder.AgentBuilder$Listener r7 = r13.listener     // Catch:{ all -> 0x00b2 }
                            java.lang.String r8 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r5)     // Catch:{ all -> 0x00b2 }
                            java.lang.ClassLoader r9 = r5.getClassLoader()     // Catch:{ all -> 0x00b2 }
                            net.bytebuddy.utility.JavaModule r10 = net.bytebuddy.utility.JavaModule.ofType(r5)     // Catch:{ all -> 0x00b2 }
                            r11 = 1
                            r7.onError(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x00b2 }
                            net.bytebuddy.agent.builder.AgentBuilder$Listener r7 = r13.listener     // Catch:{ all -> 0x00ae }
                            java.lang.String r8 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r5)     // Catch:{ all -> 0x00ae }
                            java.lang.ClassLoader r9 = r5.getClassLoader()     // Catch:{ all -> 0x00ae }
                            net.bytebuddy.utility.JavaModule r5 = net.bytebuddy.utility.JavaModule.ofType(r5)     // Catch:{ all -> 0x00ae }
                            r7.onComplete(r8, r9, r5, r6)     // Catch:{ all -> 0x00ae }
                        L_0x00ae:
                            r3.remove()     // Catch:{ all -> 0x00ec }
                            goto L_0x0050
                        L_0x00b2:
                            r7 = move-exception
                            net.bytebuddy.agent.builder.AgentBuilder$Listener r8 = r13.listener     // Catch:{ all -> 0x00ae }
                            java.lang.String r9 = net.bytebuddy.description.type.TypeDescription.ForLoadedType.getName(r5)     // Catch:{ all -> 0x00ae }
                            java.lang.ClassLoader r10 = r5.getClassLoader()     // Catch:{ all -> 0x00ae }
                            net.bytebuddy.utility.JavaModule r5 = net.bytebuddy.utility.JavaModule.ofType(r5)     // Catch:{ all -> 0x00ae }
                            r8.onComplete(r9, r10, r5, r6)     // Catch:{ all -> 0x00ae }
                            throw r7     // Catch:{ all -> 0x00ae }
                        L_0x00c5:
                            boolean r1 = r2.isEmpty()     // Catch:{ all -> 0x00ec }
                            if (r1 != 0) goto L_0x00e4
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy r1 = r13.redefinitionStrategy     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Collector r3 = r1.make()     // Catch:{ all -> 0x00ec }
                            r3.include(r2)     // Catch:{ all -> 0x00ec }
                            java.lang.instrument.Instrumentation r4 = r13.instrumentation     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$CircularityLock r5 = r13.circularityLock     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy r6 = r13.locationStrategy     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$Listener r7 = r13.listener     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$BatchAllocator r8 = r13.redefinitionBatchAllocator     // Catch:{ all -> 0x00ec }
                            net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Listener r9 = r13.redefinitionBatchListener     // Catch:{ all -> 0x00ec }
                            r10 = 0
                            r3.apply(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x00ec }
                        L_0x00e4:
                            if (r0 == 0) goto L_0x00eb
                            net.bytebuddy.agent.builder.AgentBuilder$CircularityLock r0 = r13.circularityLock
                            r0.release()
                        L_0x00eb:
                            return
                        L_0x00ec:
                            r1 = move-exception
                            if (r0 == 0) goto L_0x00f4
                            net.bytebuddy.agent.builder.AgentBuilder$CircularityLock r0 = r13.circularityLock
                            r0.release()
                        L_0x00f4:
                            throw r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy.Enabled.ResubmissionInstallationListener.run():void");
                    }
                }

                protected static class LookupKey {
                    /* access modifiers changed from: private */
                    public final ClassLoader classLoader;
                    /* access modifiers changed from: private */
                    public final int hashCode;

                    protected LookupKey(ClassLoader classLoader2) {
                        this.classLoader = classLoader2;
                        this.hashCode = System.identityHashCode(classLoader2);
                    }

                    public int hashCode() {
                        return this.hashCode;
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj instanceof LookupKey) {
                            if (this.classLoader == ((LookupKey) obj).classLoader) {
                                return true;
                            }
                            return false;
                        } else if (!(obj instanceof StorageKey)) {
                            return false;
                        } else {
                            StorageKey storageKey = (StorageKey) obj;
                            if (this.hashCode == storageKey.hashCode && this.classLoader == storageKey.get()) {
                                return true;
                            }
                            return false;
                        }
                    }
                }

                protected static class StorageKey extends WeakReference<ClassLoader> {
                    /* access modifiers changed from: private */
                    public final int hashCode;

                    protected StorageKey(ClassLoader classLoader) {
                        super(classLoader);
                        this.hashCode = System.identityHashCode(classLoader);
                    }

                    /* access modifiers changed from: protected */
                    public boolean isBootstrapLoader() {
                        return this.hashCode == 0;
                    }

                    public int hashCode() {
                        return this.hashCode;
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj instanceof LookupKey) {
                            LookupKey lookupKey = (LookupKey) obj;
                            if (this.hashCode == lookupKey.hashCode && get() == lookupKey.classLoader) {
                                return true;
                            }
                            return false;
                        } else if (!(obj instanceof StorageKey)) {
                            return false;
                        } else {
                            StorageKey storageKey = (StorageKey) obj;
                            if (this.hashCode == storageKey.hashCode && get() == storageKey.get()) {
                                return true;
                            }
                            return false;
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Installation {
                private final InstallationListener installationListener;
                private final Listener listener;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Installation installation = (Installation) obj;
                    return this.listener.equals(installation.listener) && this.installationListener.equals(installation.installationListener);
                }

                public int hashCode() {
                    return ((527 + this.listener.hashCode()) * 31) + this.installationListener.hashCode();
                }

                protected Installation(Listener listener2, InstallationListener installationListener2) {
                    this.listener = listener2;
                    this.installationListener = installationListener2;
                }

                /* access modifiers changed from: protected */
                public Listener getListener() {
                    return this.listener;
                }

                /* access modifiers changed from: protected */
                public InstallationListener getInstallationListener() {
                    return this.installationListener;
                }
            }
        }

        protected interface Dispatcher {
            boolean isModifiableClass(Instrumentation instrumentation, Class<?> cls);

            boolean isRetransformClassesSupported(Instrumentation instrumentation);

            void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) throws UnmodifiableClassException;

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        return new ForJava6CapableVm(Instrumentation.class.getMethod("isModifiableClass", new Class[]{Class.class}), Instrumentation.class.getMethod("isRetransformClassesSupported", new Class[0]), Instrumentation.class.getMethod("retransformClasses", new Class[]{Class[].class}));
                    } catch (NoSuchMethodException unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public boolean isRetransformClassesSupported(Instrumentation instrumentation) {
                    return false;
                }

                public boolean isModifiableClass(Instrumentation instrumentation, Class<?> cls) {
                    return !cls.isArray() && !cls.isPrimitive();
                }

                public void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) {
                    throw new UnsupportedOperationException("The current VM does not support retransformation");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava6CapableVm implements Dispatcher {
                private final Method isModifiableClass;
                private final Method isRetransformClassesSupported;
                private final Method retransformClasses;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava6CapableVm forJava6CapableVm = (ForJava6CapableVm) obj;
                    return this.isModifiableClass.equals(forJava6CapableVm.isModifiableClass) && this.isRetransformClassesSupported.equals(forJava6CapableVm.isRetransformClassesSupported) && this.retransformClasses.equals(forJava6CapableVm.retransformClasses);
                }

                public int hashCode() {
                    return ((((527 + this.isModifiableClass.hashCode()) * 31) + this.isRetransformClassesSupported.hashCode()) * 31) + this.retransformClasses.hashCode();
                }

                protected ForJava6CapableVm(Method method, Method method2, Method method3) {
                    this.isModifiableClass = method;
                    this.isRetransformClassesSupported = method2;
                    this.retransformClasses = method3;
                }

                public boolean isModifiableClass(Instrumentation instrumentation, Class<?> cls) {
                    try {
                        return ((Boolean) this.isModifiableClass.invoke(instrumentation, new Object[]{cls})).booleanValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#isModifiableClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#isModifiableClass", e2.getCause());
                    }
                }

                public boolean isRetransformClassesSupported(Instrumentation instrumentation) {
                    try {
                        return ((Boolean) this.isRetransformClassesSupported.invoke(instrumentation, new Object[0])).booleanValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#isRetransformClassesSupported", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#isRetransformClassesSupported", e2.getCause());
                    }
                }

                public void retransformClasses(Instrumentation instrumentation, Class<?>[] clsArr) throws UnmodifiableClassException {
                    try {
                        this.retransformClasses.invoke(instrumentation, new Object[]{clsArr});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#retransformClasses", e);
                    } catch (InvocationTargetException e2) {
                        UnmodifiableClassException cause = e2.getCause();
                        if (cause instanceof UnmodifiableClassException) {
                            throw cause;
                        }
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#retransformClasses", cause);
                    }
                }
            }
        }

        protected static abstract class Collector {
            private static final Class<?> NO_LOADED_TYPE = null;
            protected final List<Class<?>> types;

            /* access modifiers changed from: protected */
            public abstract void doApply(Instrumentation instrumentation, CircularityLock circularityLock, List<Class<?>> list, LocationStrategy locationStrategy, Listener listener) throws UnmodifiableClassException, ClassNotFoundException;

            protected Collector() {
                this.types = new ArrayList();
            }

            /* access modifiers changed from: protected */
            public void consider(RawMatcher rawMatcher, RawMatcher rawMatcher2, Listener listener, TypeDescription typeDescription, Class<?> cls, JavaModule javaModule) {
                consider(rawMatcher, rawMatcher2, listener, typeDescription, cls, NO_LOADED_TYPE, javaModule, false);
            }

            /* access modifiers changed from: protected */
            public void consider(RawMatcher rawMatcher, RawMatcher rawMatcher2, Listener listener, TypeDescription typeDescription, Class<?> cls, Class<?> cls2, JavaModule javaModule, boolean z) {
                if (!z) {
                    if (!rawMatcher2.matches(typeDescription, cls.getClassLoader(), javaModule, cls2, cls.getProtectionDomain())) {
                        if (rawMatcher.matches(typeDescription, cls.getClassLoader(), javaModule, cls2, cls.getProtectionDomain()) && this.types.add(cls)) {
                            return;
                        }
                    }
                }
                boolean z2 = true;
                try {
                    listener.onIgnored(typeDescription, cls.getClassLoader(), javaModule, cls2 != null);
                    String name = typeDescription.getName();
                    ClassLoader classLoader = cls.getClassLoader();
                    if (cls2 == null) {
                        z2 = false;
                    }
                    listener.onComplete(name, classLoader, javaModule, z2);
                } catch (Throwable unused) {
                }
            }

            /* access modifiers changed from: protected */
            public void include(List<Class<?>> list) {
                this.types.addAll(list);
            }

            /* access modifiers changed from: protected */
            public int apply(Instrumentation instrumentation, CircularityLock circularityLock, LocationStrategy locationStrategy, Listener listener, BatchAllocator batchAllocator, Listener listener2, int i) {
                HashMap hashMap = new HashMap();
                PrependableIterator prependableIterator = new PrependableIterator(batchAllocator.batch(this.types));
                while (prependableIterator.hasNext()) {
                    List next = prependableIterator.next();
                    listener2.onBatch(i, next, this.types);
                    try {
                        doApply(instrumentation, circularityLock, next, locationStrategy, listener);
                    } catch (Throwable th) {
                        prependableIterator.prepend(listener2.onError(i, next, th, this.types));
                        hashMap.put(next, th);
                    }
                    i++;
                }
                listener2.onComplete(i, this.types, hashMap);
                return i;
            }

            protected static class PrependableIterator implements Iterator<List<Class<?>>> {
                private final LinkedList<Iterator<? extends List<Class<?>>>> backlog;
                private Iterator<? extends List<Class<?>>> current;

                protected PrependableIterator(Iterable<? extends List<Class<?>>> iterable) {
                    this.current = iterable.iterator();
                    this.backlog = new LinkedList<>();
                }

                public void prepend(Iterable<? extends List<Class<?>>> iterable) {
                    Iterator<? extends List<Class<?>>> it = iterable.iterator();
                    if (it.hasNext()) {
                        if (this.current.hasNext()) {
                            this.backlog.addLast(this.current);
                        }
                        this.current = it;
                    }
                }

                public boolean hasNext() {
                    return this.current.hasNext();
                }

                /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: 
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
                    	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
                    */
                public java.util.List<java.lang.Class<?>> next() {
                    /*
                        r2 = this;
                        java.util.Iterator<? extends java.util.List<java.lang.Class<?>>> r0 = r2.current     // Catch:{ all -> 0x0024 }
                        java.lang.Object r0 = r0.next()     // Catch:{ all -> 0x0024 }
                        java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0024 }
                    L_0x0008:
                        java.util.Iterator<? extends java.util.List<java.lang.Class<?>>> r1 = r2.current
                        boolean r1 = r1.hasNext()
                        if (r1 != 0) goto L_0x0023
                        java.util.LinkedList<java.util.Iterator<? extends java.util.List<java.lang.Class<?>>>> r1 = r2.backlog
                        boolean r1 = r1.isEmpty()
                        if (r1 != 0) goto L_0x0023
                        java.util.LinkedList<java.util.Iterator<? extends java.util.List<java.lang.Class<?>>>> r1 = r2.backlog
                        java.lang.Object r1 = r1.removeLast()
                        java.util.Iterator r1 = (java.util.Iterator) r1
                        r2.current = r1
                        goto L_0x0008
                    L_0x0023:
                        return r0
                    L_0x0024:
                        r0 = move-exception
                    L_0x0025:
                        java.util.Iterator<? extends java.util.List<java.lang.Class<?>>> r1 = r2.current
                        boolean r1 = r1.hasNext()
                        if (r1 != 0) goto L_0x0040
                        java.util.LinkedList<java.util.Iterator<? extends java.util.List<java.lang.Class<?>>>> r1 = r2.backlog
                        boolean r1 = r1.isEmpty()
                        if (r1 != 0) goto L_0x0040
                        java.util.LinkedList<java.util.Iterator<? extends java.util.List<java.lang.Class<?>>>> r1 = r2.backlog
                        java.lang.Object r1 = r1.removeLast()
                        java.util.Iterator r1 = (java.util.Iterator) r1
                        r2.current = r1
                        goto L_0x0025
                    L_0x0040:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Collector.PrependableIterator.next():java.util.List");
                }

                public void remove() {
                    throw new UnsupportedOperationException("remove");
                }
            }

            protected static class ForRedefinition extends Collector {
                protected ForRedefinition() {
                }

                /* access modifiers changed from: protected */
                public void doApply(Instrumentation instrumentation, CircularityLock circularityLock, List<Class<?>> list, LocationStrategy locationStrategy, Listener listener) throws UnmodifiableClassException, ClassNotFoundException {
                    JavaModule ofType;
                    ArrayList arrayList = new ArrayList(list.size());
                    for (Class next : list) {
                        try {
                            arrayList.add(new ClassDefinition(next, locationStrategy.classFileLocator(next.getClassLoader(), JavaModule.ofType(next)).locate(TypeDescription.ForLoadedType.getName(next)).resolve()));
                        } catch (Throwable th) {
                            listener.onComplete(TypeDescription.ForLoadedType.getName(next), next.getClassLoader(), ofType, true);
                            throw th;
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        circularityLock.release();
                        try {
                            instrumentation.redefineClasses((ClassDefinition[]) arrayList.toArray(new ClassDefinition[arrayList.size()]));
                        } finally {
                            circularityLock.acquire();
                        }
                    }
                }
            }

            protected static class ForRetransformation extends Collector {
                protected ForRetransformation() {
                }

                /* access modifiers changed from: protected */
                public void doApply(Instrumentation instrumentation, CircularityLock circularityLock, List<Class<?>> list, LocationStrategy locationStrategy, Listener listener) throws UnmodifiableClassException {
                    if (!list.isEmpty()) {
                        circularityLock.release();
                        try {
                            RedefinitionStrategy.DISPATCHER.retransformClasses(instrumentation, (Class[]) list.toArray(new Class[list.size()]));
                        } finally {
                            circularityLock.acquire();
                        }
                    }
                }
            }
        }
    }

    public enum LambdaInstrumentationStrategy {
        ENABLED {
            /* access modifiers changed from: protected */
            public boolean isInstrumented(Class<?> cls) {
                return true;
            }

            /* access modifiers changed from: protected */
            public void apply(ByteBuddy byteBuddy, Instrumentation instrumentation, ClassFileTransformer classFileTransformer) {
                if (LambdaFactory.register(classFileTransformer, new LambdaInstanceFactory(byteBuddy))) {
                    try {
                        Class<?> cls = Class.forName("java.lang.invoke.LambdaMetafactory");
                        byteBuddy.with((Implementation.Context.Factory) Implementation.Context.Disabled.Factory.INSTANCE).redefine(cls).visit(new AsmVisitorWrapper.ForDeclaredMethods().method((ElementMatcher<? super MethodDescription>) ElementMatchers.named("metafactory"), MetaFactoryRedirection.INSTANCE).method((ElementMatcher<? super MethodDescription>) ElementMatchers.named("altMetafactory"), AlternativeMetaFactoryRedirection.INSTANCE)).make().load(cls.getClassLoader(), ClassReloadingStrategy.of(instrumentation));
                    } catch (ClassNotFoundException unused) {
                    }
                }
            }
        },
        DISABLED {
            /* access modifiers changed from: protected */
            public void apply(ByteBuddy byteBuddy, Instrumentation instrumentation, ClassFileTransformer classFileTransformer) {
            }

            /* access modifiers changed from: protected */
            public boolean isInstrumented(Class<?> cls) {
                return cls == null || !cls.getName().contains("/");
            }
        };
        
        protected static final MethodVisitor IGNORE_ORIGINAL = null;
        /* access modifiers changed from: private */
        public static final String UNSAFE_CLASS = null;

        /* access modifiers changed from: protected */
        public abstract void apply(ByteBuddy byteBuddy, Instrumentation instrumentation, ClassFileTransformer classFileTransformer);

        /* access modifiers changed from: protected */
        public abstract boolean isInstrumented(Class<?> cls);

        public static void release(ClassFileTransformer classFileTransformer, Instrumentation instrumentation) {
            if (LambdaFactory.release(classFileTransformer)) {
                try {
                    ClassReloadingStrategy.of(instrumentation).reset(Class.forName("java.lang.invoke.LambdaMetafactory"));
                } catch (Exception e) {
                    throw new IllegalStateException("Could not release lambda transformer", e);
                }
            }
        }

        public static LambdaInstrumentationStrategy of(boolean z) {
            return z ? ENABLED : DISABLED;
        }

        public boolean isEnabled() {
            return this == ENABLED;
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class LambdaInstanceFactory {
            private static final String FIELD_PREFIX = "arg$";
            private static final String LAMBDA_FACTORY = "get$Lambda";
            private static final AtomicInteger LAMBDA_NAME_COUNTER = null;
            private static final String LAMBDA_TYPE_INFIX = "$$Lambda$ByteBuddy$";
            private static final Class<?> NOT_PREVIOUSLY_DEFINED = null;
            private final ByteBuddy byteBuddy;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.byteBuddy.equals(((LambdaInstanceFactory) obj).byteBuddy);
            }

            public int hashCode() {
                return 527 + this.byteBuddy.hashCode();
            }

            static {
                LAMBDA_NAME_COUNTER = new AtomicInteger();
            }

            protected LambdaInstanceFactory(ByteBuddy byteBuddy2) {
                this.byteBuddy = byteBuddy2;
            }

            public byte[] make(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2, Collection<? extends ClassFileTransformer> collection) {
                char c;
                String str2 = str;
                List<Class<?>> list3 = list;
                JavaConstant.MethodType ofLoaded = JavaConstant.MethodType.ofLoaded(obj2);
                JavaConstant.MethodType ofLoaded2 = JavaConstant.MethodType.ofLoaded(obj3);
                JavaConstant.MethodHandle ofLoaded3 = JavaConstant.MethodHandle.ofLoaded(obj4, obj);
                JavaConstant.MethodType ofLoaded4 = JavaConstant.MethodType.ofLoaded(obj5);
                Class<?> lookupType = JavaConstant.MethodHandle.lookupType(obj);
                String str3 = lookupType.getName() + LAMBDA_TYPE_INFIX + LAMBDA_NAME_COUNTER.incrementAndGet();
                DynamicType.Builder intercept = this.byteBuddy.subclass((TypeDefinition) ofLoaded.getReturnType(), (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).modifiers(TypeManifestation.FINAL, Visibility.PUBLIC).implement((List<? extends Type>) list3).name(str3).defineConstructor(Visibility.PUBLIC).withParameters((Collection<? extends TypeDefinition>) ofLoaded.getParameterTypes()).intercept(ConstructorImplementation.INSTANCE).method(ElementMatchers.named(str).and(ElementMatchers.takesArguments((Iterable<? extends TypeDescription>) ofLoaded2.getParameterTypes())).and(ElementMatchers.returns(ofLoaded2.getReturnType()))).intercept(new LambdaMethodImplementation(ofLoaded3, ofLoaded4));
                int i = 0;
                for (TypeDescription defineField : ofLoaded.getParameterTypes()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(FIELD_PREFIX);
                    i++;
                    sb.append(i);
                    intercept = intercept.defineField(sb.toString(), (TypeDefinition) defineField, Visibility.PRIVATE, FieldManifestation.FINAL);
                }
                if (!ofLoaded.getParameterTypes().isEmpty()) {
                    intercept = intercept.defineMethod(LAMBDA_FACTORY, (TypeDefinition) ofLoaded.getReturnType(), Visibility.PRIVATE, Ownership.STATIC).withParameters((Collection<? extends TypeDefinition>) ofLoaded.getParameterTypes()).intercept(FactoryImplementation.INSTANCE);
                }
                if (z) {
                    if (!list3.contains(Serializable.class)) {
                        c = 0;
                        intercept = intercept.implement(Serializable.class);
                    } else {
                        c = 0;
                    }
                    ModifierContributor.ForMethod[] forMethodArr = new ModifierContributor.ForMethod[1];
                    forMethodArr[c] = Visibility.PRIVATE;
                    intercept = intercept.defineMethod("writeReplace", (Type) Object.class, forMethodArr).intercept(new SerializationImplementation(TypeDescription.ForLoadedType.of(lookupType), ofLoaded.getReturnType(), str, ofLoaded2, ofLoaded3, JavaConstant.MethodType.ofLoaded(obj5)));
                } else if (ofLoaded.getReturnType().isAssignableTo((Class<?>) Serializable.class)) {
                    intercept = intercept.defineMethod("readObject", (Type) Void.TYPE, Visibility.PRIVATE).withParameters(ObjectInputStream.class).throwing(NotSerializableException.class).intercept(ExceptionMethod.throwing((Class<? extends Throwable>) NotSerializableException.class, "Non-serializable lambda")).defineMethod("writeObject", (Type) Void.TYPE, Visibility.PRIVATE).withParameters(ObjectOutputStream.class).throwing(NotSerializableException.class).intercept(ExceptionMethod.throwing((Class<? extends Throwable>) NotSerializableException.class, "Non-serializable lambda"));
                }
                for (Object ofLoaded5 : list2) {
                    JavaConstant.MethodType ofLoaded6 = JavaConstant.MethodType.ofLoaded(ofLoaded5);
                    intercept = intercept.defineMethod(str2, (TypeDefinition) ofLoaded6.getReturnType(), MethodManifestation.BRIDGE, Visibility.PUBLIC).withParameters((Collection<? extends TypeDefinition>) ofLoaded6.getParameterTypes()).intercept(new BridgeMethodImplementation(str2, ofLoaded2));
                }
                byte[] bytes = intercept.make().getBytes();
                for (ClassFileTransformer transform : collection) {
                    try {
                        byte[] transform2 = transform.transform(lookupType.getClassLoader(), str3.replace('.', '/'), NOT_PREVIOUSLY_DEFINED, lookupType.getProtectionDomain(), bytes);
                        if (transform2 != null) {
                            bytes = transform2;
                        }
                    } catch (Throwable unused) {
                    }
                }
                return bytes;
            }

            protected enum ConstructorImplementation implements Implementation {
                INSTANCE;
                
                /* access modifiers changed from: private */
                public final MethodDescription.InDefinedShape objectConstructor;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.getInstrumentedType().getDeclaredFields());
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class Appender implements ByteCodeAppender {
                    private final List<FieldDescription.InDefinedShape> declaredFields;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.declaredFields.equals(((Appender) obj).declaredFields);
                    }

                    public int hashCode() {
                        return 527 + this.declaredFields.hashCode();
                    }

                    protected Appender(List<FieldDescription.InDefinedShape> list) {
                        this.declaredFields = list;
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        ArrayList arrayList = new ArrayList(this.declaredFields.size() * 3);
                        Iterator it = methodDescription.getParameters().iterator();
                        while (it.hasNext()) {
                            ParameterDescription parameterDescription = (ParameterDescription) it.next();
                            arrayList.add(MethodVariableAccess.loadThis());
                            arrayList.add(MethodVariableAccess.load(parameterDescription));
                            arrayList.add(FieldAccess.forField(this.declaredFields.get(parameterDescription.getIndex())).write());
                        }
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(ConstructorImplementation.INSTANCE.objectConstructor), new StackManipulation.Compound((List<? extends StackManipulation>) arrayList), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            protected enum FactoryImplementation implements Implementation {
                INSTANCE;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.getInstrumentedType());
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class Appender implements ByteCodeAppender {
                    private final TypeDescription instrumentedType;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((Appender) obj).instrumentedType);
                    }

                    public int hashCode() {
                        return 527 + this.instrumentedType.hashCode();
                    }

                    protected Appender(TypeDescription typeDescription) {
                        this.instrumentedType = typeDescription;
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(TypeCreation.of(this.instrumentedType), Duplication.SINGLE, MethodVariableAccess.allArgumentsOf(methodDescription), MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) this.instrumentedType.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly()), MethodReturn.REFERENCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class LambdaMethodImplementation implements Implementation {
                private final JavaConstant.MethodType specializedLambdaMethod;
                private final JavaConstant.MethodHandle targetMethod;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    LambdaMethodImplementation lambdaMethodImplementation = (LambdaMethodImplementation) obj;
                    return this.targetMethod.equals(lambdaMethodImplementation.targetMethod) && this.specializedLambdaMethod.equals(lambdaMethodImplementation.specializedLambdaMethod);
                }

                public int hashCode() {
                    return ((527 + this.targetMethod.hashCode()) * 31) + this.specializedLambdaMethod.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected LambdaMethodImplementation(JavaConstant.MethodHandle methodHandle, JavaConstant.MethodType methodType) {
                    this.targetMethod = methodHandle;
                    this.specializedLambdaMethod = methodType;
                }

                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender((MethodDescription) ((MethodList) this.targetMethod.getOwnerType().getDeclaredMethods().filter(ElementMatchers.hasMethodName(this.targetMethod.getName()).and(ElementMatchers.returns(this.targetMethod.getReturnType())).and(ElementMatchers.takesArguments((Iterable<? extends TypeDescription>) this.targetMethod.getParameterTypes())))).getOnly(), this.specializedLambdaMethod, target.getInstrumentedType().getDeclaredFields());
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class Appender implements ByteCodeAppender {
                    private final List<FieldDescription.InDefinedShape> declaredFields;
                    private final JavaConstant.MethodType specializedLambdaMethod;
                    private final MethodDescription targetMethod;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Appender appender = (Appender) obj;
                        return this.targetMethod.equals(appender.targetMethod) && this.specializedLambdaMethod.equals(appender.specializedLambdaMethod) && this.declaredFields.equals(appender.declaredFields);
                    }

                    public int hashCode() {
                        return ((((527 + this.targetMethod.hashCode()) * 31) + this.specializedLambdaMethod.hashCode()) * 31) + this.declaredFields.hashCode();
                    }

                    protected Appender(MethodDescription methodDescription, JavaConstant.MethodType methodType, List<FieldDescription.InDefinedShape> list) {
                        this.targetMethod = methodDescription;
                        this.specializedLambdaMethod = methodType;
                        this.declaredFields = list;
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        StackManipulation stackManipulation;
                        TypeDescription.Generic generic;
                        if (this.targetMethod.isConstructor()) {
                            stackManipulation = new StackManipulation.Compound(TypeCreation.of(this.targetMethod.getDeclaringType().asErasure()), Duplication.SINGLE);
                        } else {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        }
                        ArrayList arrayList = new ArrayList((this.declaredFields.size() * 2) + 1);
                        for (FieldDescription.InDefinedShape forField : this.declaredFields) {
                            arrayList.add(MethodVariableAccess.loadThis());
                            arrayList.add(FieldAccess.forField(forField).read());
                        }
                        ArrayList arrayList2 = new ArrayList(methodDescription.getParameters().size() * 2);
                        Iterator it = methodDescription.getParameters().iterator();
                        while (it.hasNext()) {
                            ParameterDescription parameterDescription = (ParameterDescription) it.next();
                            arrayList2.add(MethodVariableAccess.load(parameterDescription));
                            arrayList2.add(Assigner.DEFAULT.assign(parameterDescription.getType(), ((TypeDescription) this.specializedLambdaMethod.getParameterTypes().get(parameterDescription.getIndex())).asGenericType(), Assigner.Typing.DYNAMIC));
                        }
                        StackManipulation[] stackManipulationArr = new StackManipulation[6];
                        stackManipulationArr[0] = stackManipulation;
                        stackManipulationArr[1] = new StackManipulation.Compound((List<? extends StackManipulation>) arrayList);
                        stackManipulationArr[2] = new StackManipulation.Compound((List<? extends StackManipulation>) arrayList2);
                        stackManipulationArr[3] = MethodInvocation.invoke(this.targetMethod);
                        Assigner assigner = Assigner.DEFAULT;
                        if (this.targetMethod.isConstructor()) {
                            generic = this.targetMethod.getDeclaringType().asGenericType();
                        } else {
                            generic = this.targetMethod.getReturnType();
                        }
                        stackManipulationArr[4] = assigner.assign(generic, this.specializedLambdaMethod.getReturnType().asGenericType(), Assigner.Typing.DYNAMIC);
                        stackManipulationArr[5] = MethodReturn.of(this.specializedLambdaMethod.getReturnType());
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class SerializationImplementation implements Implementation {
                private final JavaConstant.MethodType lambdaMethod;
                private final String lambdaMethodName;
                private final TypeDescription lambdaType;
                private final JavaConstant.MethodType specializedMethod;
                private final JavaConstant.MethodHandle targetMethod;
                private final TypeDescription targetType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    SerializationImplementation serializationImplementation = (SerializationImplementation) obj;
                    return this.lambdaMethodName.equals(serializationImplementation.lambdaMethodName) && this.targetType.equals(serializationImplementation.targetType) && this.lambdaType.equals(serializationImplementation.lambdaType) && this.lambdaMethod.equals(serializationImplementation.lambdaMethod) && this.targetMethod.equals(serializationImplementation.targetMethod) && this.specializedMethod.equals(serializationImplementation.specializedMethod);
                }

                public int hashCode() {
                    return ((((((((((527 + this.targetType.hashCode()) * 31) + this.lambdaType.hashCode()) * 31) + this.lambdaMethodName.hashCode()) * 31) + this.lambdaMethod.hashCode()) * 31) + this.targetMethod.hashCode()) * 31) + this.specializedMethod.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected SerializationImplementation(TypeDescription typeDescription, TypeDescription typeDescription2, String str, JavaConstant.MethodType methodType, JavaConstant.MethodHandle methodHandle, JavaConstant.MethodType methodType2) {
                    this.targetType = typeDescription;
                    this.lambdaType = typeDescription2;
                    this.lambdaMethodName = str;
                    this.lambdaMethod = methodType;
                    this.targetMethod = methodHandle;
                    this.specializedMethod = methodType2;
                }

                public ByteCodeAppender appender(Implementation.Target target) {
                    try {
                        TypeDescription of = TypeDescription.ForLoadedType.of(Class.forName("java.lang.invoke.SerializedLambda"));
                        ArrayList arrayList = new ArrayList(target.getInstrumentedType().getDeclaredFields().size());
                        for (FieldDescription.InDefinedShape inDefinedShape : target.getInstrumentedType().getDeclaredFields()) {
                            arrayList.add(new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField(inDefinedShape).read(), Assigner.DEFAULT.assign(inDefinedShape.getType(), TypeDescription.Generic.OBJECT, Assigner.Typing.STATIC)));
                        }
                        return new ByteCodeAppender.Simple(new StackManipulation.Compound(TypeCreation.of(of), Duplication.SINGLE, ClassConstant.of(this.targetType), new TextConstant(this.lambdaType.getInternalName()), new TextConstant(this.lambdaMethodName), new TextConstant(this.lambdaMethod.getDescriptor()), IntegerConstant.forValue(this.targetMethod.getHandleType().getIdentifier()), new TextConstant(this.targetMethod.getOwnerType().getInternalName()), new TextConstant(this.targetMethod.getName()), new TextConstant(this.targetMethod.getDescriptor()), new TextConstant(this.specializedMethod.getDescriptor()), ArrayFactory.forType(TypeDescription.Generic.OBJECT).withValues(arrayList), MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) of.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly()), MethodReturn.REFERENCE));
                    } catch (ClassNotFoundException e) {
                        throw new IllegalStateException("Cannot find class for lambda serialization", e);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class BridgeMethodImplementation implements Implementation {
                private final JavaConstant.MethodType lambdaMethod;
                private final String lambdaMethodName;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    BridgeMethodImplementation bridgeMethodImplementation = (BridgeMethodImplementation) obj;
                    return this.lambdaMethodName.equals(bridgeMethodImplementation.lambdaMethodName) && this.lambdaMethod.equals(bridgeMethodImplementation.lambdaMethod);
                }

                public int hashCode() {
                    return ((527 + this.lambdaMethodName.hashCode()) * 31) + this.lambdaMethod.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected BridgeMethodImplementation(String str, JavaConstant.MethodType methodType) {
                    this.lambdaMethodName = str;
                    this.lambdaMethod = methodType;
                }

                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.invokeSuper(new MethodDescription.SignatureToken(this.lambdaMethodName, this.lambdaMethod.getReturnType(), this.lambdaMethod.getParameterTypes())));
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class Appender implements ByteCodeAppender {
                    private final Implementation.SpecialMethodInvocation bridgeTargetInvocation;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.bridgeTargetInvocation.equals(((Appender) obj).bridgeTargetInvocation);
                    }

                    public int hashCode() {
                        return 527 + this.bridgeTargetInvocation.hashCode();
                    }

                    protected Appender(Implementation.SpecialMethodInvocation specialMethodInvocation) {
                        this.bridgeTargetInvocation = specialMethodInvocation;
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        StackManipulation stackManipulation;
                        ByteCodeAppender[] byteCodeAppenderArr = new ByteCodeAppender[1];
                        StackManipulation[] stackManipulationArr = new StackManipulation[4];
                        stackManipulationArr[0] = MethodVariableAccess.allArgumentsOf(methodDescription).asBridgeOf(this.bridgeTargetInvocation.getMethodDescription()).prependThisReference();
                        Implementation.SpecialMethodInvocation specialMethodInvocation = this.bridgeTargetInvocation;
                        stackManipulationArr[1] = specialMethodInvocation;
                        if (specialMethodInvocation.getMethodDescription().getReturnType().asErasure().isAssignableTo(methodDescription.getReturnType().asErasure())) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = TypeCasting.to(methodDescription.getReceiverType());
                        }
                        stackManipulationArr[2] = stackManipulation;
                        stackManipulationArr[3] = MethodReturn.of(methodDescription.getReturnType());
                        byteCodeAppenderArr[0] = new ByteCodeAppender.Simple(stackManipulationArr);
                        return new ByteCodeAppender.Compound(byteCodeAppenderArr).apply(methodVisitor, context, methodDescription);
                    }
                }
            }
        }

        protected enum MetaFactoryRedirection implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
            INSTANCE;

            public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
                MethodVisitor methodVisitor2 = methodVisitor;
                methodVisitor.visitCode();
                MethodVisitor methodVisitor3 = methodVisitor;
                methodVisitor3.visitMethodInsn(184, LambdaInstrumentationStrategy.UNSAFE_CLASS, "getUnsafe", "()L" + LambdaInstrumentationStrategy.UNSAFE_CLASS + ";", false);
                methodVisitor2.visitVarInsn(58, 6);
                methodVisitor2.visitVarInsn(25, 6);
                methodVisitor2.visitVarInsn(25, 0);
                methodVisitor3.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "lookupClass", "()Ljava/lang/Class;", false);
                methodVisitor3.visitMethodInsn(184, "java/lang/ClassLoader", "getSystemClassLoader", "()Ljava/lang/ClassLoader;", false);
                methodVisitor2.visitLdcInsn("net.bytebuddy.agent.builder.LambdaFactory");
                methodVisitor.visitMethodInsn(182, "java/lang/ClassLoader", "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;", false);
                methodVisitor2.visitLdcInsn(TypeProxy.REFLECTION_METHOD);
                methodVisitor2.visitIntInsn(16, 9);
                methodVisitor2.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(4);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/lang/String;"));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(5);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(6);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(7);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(8);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 6);
                methodVisitor2.visitFieldInsn(178, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 7);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 8);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor2.visitInsn(83);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
                methodVisitor2.visitInsn(1);
                methodVisitor2.visitIntInsn(16, 9);
                methodVisitor2.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitVarInsn(25, 0);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(4);
                methodVisitor2.visitVarInsn(25, 1);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(5);
                methodVisitor2.visitVarInsn(25, 2);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(6);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(7);
                methodVisitor2.visitVarInsn(25, 4);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(8);
                methodVisitor2.visitVarInsn(25, 5);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 6);
                methodVisitor2.visitInsn(3);
                MethodVisitor methodVisitor4 = methodVisitor;
                methodVisitor4.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 7);
                methodVisitor4.visitMethodInsn(184, "java/util/Collections", "emptyList", "()Ljava/util/List;", false);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 8);
                methodVisitor4.visitMethodInsn(184, "java/util/Collections", "emptyList", "()Ljava/util/List;", false);
                methodVisitor2.visitInsn(83);
                methodVisitor4.visitMethodInsn(182, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
                methodVisitor2.visitTypeInsn(192, "[B");
                methodVisitor2.visitInsn(1);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "defineAnonymousClass", "(Ljava/lang/Class;[B[Ljava/lang/Object;)Ljava/lang/Class;", false);
                methodVisitor2.visitVarInsn(58, 7);
                methodVisitor2.visitVarInsn(25, 6);
                methodVisitor2.visitVarInsn(25, 7);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "ensureClassInitialized", "(Ljava/lang/Class;)V", false);
                methodVisitor2.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodType", "parameterCount", "()I", false);
                Label label = new Label();
                methodVisitor2.visitJumpInsn(154, label);
                methodVisitor2.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitVarInsn(25, 2);
                MethodVisitor methodVisitor5 = methodVisitor;
                methodVisitor5.visitMethodInsn(182, "java/lang/invoke/MethodType", "returnType", "()Ljava/lang/Class;", false);
                methodVisitor2.visitVarInsn(25, 7);
                methodVisitor5.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredConstructors", "()[Ljava/lang/reflect/Constructor;", false);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                MethodVisitor methodVisitor6 = methodVisitor;
                methodVisitor6.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_DESCRIPTOR, false);
                methodVisitor6.visitMethodInsn(184, "java/lang/invoke/MethodHandles", "constant", "(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor6.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                Label label2 = new Label();
                methodVisitor2.visitJumpInsn(167, label2);
                methodVisitor2.visitLabel(label);
                methodVisitor.visitFrame(1, 2, new Object[]{LambdaInstrumentationStrategy.UNSAFE_CLASS, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME}, 0, (Object[]) null);
                methodVisitor2.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitFieldInsn(178, "java/lang/invoke/MethodHandles$Lookup", "IMPL_LOOKUP", "Ljava/lang/invoke/MethodHandles$Lookup;");
                methodVisitor2.visitVarInsn(25, 7);
                methodVisitor2.visitLdcInsn("get$Lambda");
                methodVisitor2.visitVarInsn(25, 2);
                MethodVisitor methodVisitor7 = methodVisitor;
                methodVisitor7.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "findStatic", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor7.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                methodVisitor2.visitLabel(label2);
                methodVisitor.visitFrame(4, 0, (Object[]) null, 1, new Object[]{"java/lang/invoke/CallSite"});
                methodVisitor2.visitInsn(176);
                methodVisitor2.visitMaxs(8, 8);
                methodVisitor.visitEnd();
                return LambdaInstrumentationStrategy.IGNORE_ORIGINAL;
            }
        }

        protected enum AlternativeMetaFactoryRedirection implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
            INSTANCE;

            public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
                MethodVisitor methodVisitor2 = methodVisitor;
                methodVisitor.visitCode();
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitInsn(6);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitTypeInsn(192, "java/lang/Integer");
                methodVisitor.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I", false);
                methodVisitor2.visitVarInsn(54, 4);
                methodVisitor2.visitInsn(7);
                methodVisitor2.visitVarInsn(54, 5);
                methodVisitor2.visitVarInsn(21, 4);
                methodVisitor2.visitInsn(5);
                methodVisitor2.visitInsn(126);
                Label label = new Label();
                methodVisitor2.visitJumpInsn(153, label);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitVarInsn(21, 5);
                methodVisitor2.visitIincInsn(5, 1);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitTypeInsn(192, "java/lang/Integer");
                MethodVisitor methodVisitor3 = methodVisitor;
                Label label2 = label;
                methodVisitor3.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I", false);
                methodVisitor2.visitVarInsn(54, 7);
                methodVisitor2.visitVarInsn(21, 7);
                methodVisitor2.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME);
                methodVisitor2.visitVarInsn(58, 6);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitVarInsn(21, 5);
                methodVisitor2.visitVarInsn(25, 6);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitVarInsn(21, 7);
                String str = TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME;
                methodVisitor3.visitMethodInsn(184, "java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", false);
                methodVisitor2.visitVarInsn(21, 5);
                methodVisitor2.visitVarInsn(21, 7);
                methodVisitor2.visitInsn(96);
                methodVisitor2.visitVarInsn(54, 5);
                Label label3 = new Label();
                methodVisitor2.visitJumpInsn(167, label3);
                methodVisitor2.visitLabel(label2);
                methodVisitor.visitFrame(1, 2, new Object[]{Opcodes.INTEGER, Opcodes.INTEGER}, 0, (Object[]) null);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitTypeInsn(189, str);
                methodVisitor2.visitVarInsn(58, 6);
                methodVisitor2.visitLabel(label3);
                methodVisitor.visitFrame(1, 1, new Object[]{"[Ljava/lang/Class;"}, 0, (Object[]) null);
                methodVisitor2.visitVarInsn(21, 4);
                methodVisitor2.visitInsn(7);
                methodVisitor2.visitInsn(126);
                Label label4 = new Label();
                methodVisitor2.visitJumpInsn(153, label4);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitVarInsn(21, 5);
                methodVisitor2.visitIincInsn(5, 1);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitTypeInsn(192, "java/lang/Integer");
                methodVisitor.visitMethodInsn(182, "java/lang/Integer", "intValue", "()I", false);
                methodVisitor2.visitVarInsn(54, 8);
                methodVisitor2.visitVarInsn(21, 8);
                methodVisitor2.visitTypeInsn(189, "java/lang/invoke/MethodType");
                methodVisitor2.visitVarInsn(58, 7);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitVarInsn(21, 5);
                methodVisitor2.visitVarInsn(25, 7);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitVarInsn(21, 8);
                methodVisitor.visitMethodInsn(184, "java/lang/System", "arraycopy", "(Ljava/lang/Object;ILjava/lang/Object;II)V", false);
                Label label5 = new Label();
                methodVisitor2.visitJumpInsn(167, label5);
                methodVisitor2.visitLabel(label4);
                methodVisitor.visitFrame(3, 0, (Object[]) null, 0, (Object[]) null);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitTypeInsn(189, "java/lang/invoke/MethodType");
                methodVisitor2.visitVarInsn(58, 7);
                methodVisitor2.visitLabel(label5);
                methodVisitor.visitFrame(1, 1, new Object[]{"[Ljava/lang/invoke/MethodType;"}, 0, (Object[]) null);
                String access$1100 = LambdaInstrumentationStrategy.UNSAFE_CLASS;
                methodVisitor.visitMethodInsn(184, access$1100, "getUnsafe", "()L" + LambdaInstrumentationStrategy.UNSAFE_CLASS + ";", false);
                methodVisitor2.visitVarInsn(58, 8);
                methodVisitor2.visitVarInsn(25, 8);
                methodVisitor2.visitVarInsn(25, 0);
                MethodVisitor methodVisitor4 = methodVisitor;
                methodVisitor4.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "lookupClass", "()Ljava/lang/Class;", false);
                methodVisitor4.visitMethodInsn(184, "java/lang/ClassLoader", "getSystemClassLoader", "()Ljava/lang/ClassLoader;", false);
                methodVisitor2.visitLdcInsn("net.bytebuddy.agent.builder.LambdaFactory");
                methodVisitor.visitMethodInsn(182, "java/lang/ClassLoader", "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;", false);
                methodVisitor2.visitLdcInsn(TypeProxy.REFLECTION_METHOD);
                methodVisitor2.visitIntInsn(16, 9);
                methodVisitor2.visitTypeInsn(189, str);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(4);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/lang/String;"));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(5);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(6);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(7);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(8);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 6);
                methodVisitor2.visitFieldInsn(178, "java/lang/Boolean", "TYPE", "Ljava/lang/Class;");
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 7);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 8);
                methodVisitor2.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType("Ljava/util/List;"));
                methodVisitor2.visitInsn(83);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
                methodVisitor2.visitInsn(1);
                methodVisitor2.visitIntInsn(16, 9);
                methodVisitor2.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitVarInsn(25, 0);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(4);
                methodVisitor2.visitVarInsn(25, 1);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(5);
                methodVisitor2.visitVarInsn(25, 2);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(6);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(7);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitInsn(4);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitInsn(8);
                methodVisitor2.visitVarInsn(25, 3);
                methodVisitor2.visitInsn(5);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 6);
                methodVisitor2.visitVarInsn(21, 4);
                methodVisitor2.visitInsn(4);
                methodVisitor2.visitInsn(126);
                Label label6 = new Label();
                methodVisitor2.visitJumpInsn(153, label6);
                methodVisitor2.visitInsn(4);
                Label label7 = new Label();
                methodVisitor2.visitJumpInsn(167, label7);
                methodVisitor2.visitLabel(label6);
                methodVisitor.visitFrame(0, 9, new Object[]{"java/lang/invoke/MethodHandles$Lookup", "java/lang/String", "java/lang/invoke/MethodType", "[Ljava/lang/Object;", Opcodes.INTEGER, Opcodes.INTEGER, "[Ljava/lang/Class;", "[Ljava/lang/invoke/MethodType;", LambdaInstrumentationStrategy.UNSAFE_CLASS}, 7, new Object[]{LambdaInstrumentationStrategy.UNSAFE_CLASS, str, "java/lang/reflect/Method", Opcodes.NULL, "[Ljava/lang/Object;", "[Ljava/lang/Object;", Opcodes.INTEGER});
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitLabel(label7);
                MethodVisitor methodVisitor5 = methodVisitor;
                methodVisitor5.visitFrame(0, 9, new Object[]{"java/lang/invoke/MethodHandles$Lookup", "java/lang/String", "java/lang/invoke/MethodType", "[Ljava/lang/Object;", Opcodes.INTEGER, Opcodes.INTEGER, "[Ljava/lang/Class;", "[Ljava/lang/invoke/MethodType;", LambdaInstrumentationStrategy.UNSAFE_CLASS}, 8, new Object[]{LambdaInstrumentationStrategy.UNSAFE_CLASS, str, "java/lang/reflect/Method", Opcodes.NULL, "[Ljava/lang/Object;", "[Ljava/lang/Object;", Opcodes.INTEGER, Opcodes.INTEGER});
                methodVisitor5.visitMethodInsn(184, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 7);
                methodVisitor2.visitVarInsn(25, 6);
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;", false);
                methodVisitor2.visitInsn(83);
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitIntInsn(16, 8);
                methodVisitor2.visitVarInsn(25, 7);
                MethodVisitor methodVisitor6 = methodVisitor;
                methodVisitor6.visitMethodInsn(184, "java/util/Arrays", "asList", "([Ljava/lang/Object;)Ljava/util/List;", false);
                methodVisitor2.visitInsn(83);
                methodVisitor6.visitMethodInsn(182, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
                methodVisitor2.visitTypeInsn(192, "[B");
                methodVisitor2.visitInsn(1);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "defineAnonymousClass", "(Ljava/lang/Class;[B[Ljava/lang/Object;)Ljava/lang/Class;", false);
                methodVisitor2.visitVarInsn(58, 9);
                methodVisitor2.visitVarInsn(25, 8);
                methodVisitor2.visitVarInsn(25, 9);
                methodVisitor.visitMethodInsn(182, LambdaInstrumentationStrategy.UNSAFE_CLASS, "ensureClassInitialized", "(Ljava/lang/Class;)V", false);
                methodVisitor2.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodType", "parameterCount", "()I", false);
                Label label8 = new Label();
                methodVisitor2.visitJumpInsn(154, label8);
                methodVisitor2.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitVarInsn(25, 2);
                methodVisitor.visitMethodInsn(182, "java/lang/invoke/MethodType", "returnType", "()Ljava/lang/Class;", false);
                methodVisitor2.visitVarInsn(25, 9);
                methodVisitor.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "getDeclaredConstructors", "()[Ljava/lang/reflect/Constructor;", false);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitInsn(50);
                methodVisitor2.visitInsn(3);
                methodVisitor2.visitTypeInsn(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
                MethodVisitor methodVisitor7 = methodVisitor;
                methodVisitor7.visitMethodInsn(182, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_NAME, TypeProxy.SilentConstruction.Appender.NEW_INSTANCE_METHOD_DESCRIPTOR, false);
                methodVisitor7.visitMethodInsn(184, "java/lang/invoke/MethodHandles", "constant", "(Ljava/lang/Class;Ljava/lang/Object;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor7.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                Label label9 = new Label();
                methodVisitor2.visitJumpInsn(167, label9);
                methodVisitor2.visitLabel(label8);
                methodVisitor.visitFrame(1, 1, new Object[]{str}, 0, (Object[]) null);
                methodVisitor2.visitTypeInsn(187, "java/lang/invoke/ConstantCallSite");
                methodVisitor2.visitInsn(89);
                methodVisitor2.visitFieldInsn(178, "java/lang/invoke/MethodHandles$Lookup", "IMPL_LOOKUP", "Ljava/lang/invoke/MethodHandles$Lookup;");
                methodVisitor2.visitVarInsn(25, 9);
                methodVisitor2.visitLdcInsn("get$Lambda");
                methodVisitor2.visitVarInsn(25, 2);
                MethodVisitor methodVisitor8 = methodVisitor;
                methodVisitor8.visitMethodInsn(182, "java/lang/invoke/MethodHandles$Lookup", "findStatic", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/MethodHandle;", false);
                methodVisitor8.visitMethodInsn(183, "java/lang/invoke/ConstantCallSite", MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Ljava/lang/invoke/MethodHandle;)V", false);
                methodVisitor2.visitLabel(label9);
                methodVisitor.visitFrame(4, 0, (Object[]) null, 1, new Object[]{"java/lang/invoke/CallSite"});
                methodVisitor2.visitInsn(176);
                methodVisitor2.visitMaxs(9, 10);
                methodVisitor.visitEnd();
                return LambdaInstrumentationStrategy.IGNORE_ORIGINAL;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Default implements AgentBuilder {
        private static final CircularityLock DEFAULT_LOCK = new CircularityLock.Default();
        /* access modifiers changed from: private */
        public static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private static final String INSTALLER_TYPE = "net.bytebuddy.agent.Installer";
        private static final String INSTRUMENTATION_GETTER = "getInstrumentation";
        /* access modifiers changed from: private */
        public static final Class<?> NO_LOADED_TYPE = null;
        /* access modifiers changed from: private */
        public static final byte[] NO_TRANSFORMATION = null;
        private static final Object STATIC_MEMBER = null;
        protected final BootstrapInjectionStrategy bootstrapInjectionStrategy;
        protected final ByteBuddy byteBuddy;
        protected final CircularityLock circularityLock;
        protected final ClassFileBufferStrategy classFileBufferStrategy;
        protected final DescriptionStrategy descriptionStrategy;
        protected final FallbackStrategy fallbackStrategy;
        protected final RawMatcher ignoredTypeMatcher;
        protected final InitializationStrategy initializationStrategy;
        protected final InstallationListener installationListener;
        protected final LambdaInstrumentationStrategy lambdaInstrumentationStrategy;
        protected final Listener listener;
        protected final LocationStrategy locationStrategy;
        protected final NativeMethodStrategy nativeMethodStrategy;
        protected final PoolStrategy poolStrategy;
        protected final RedefinitionStrategy.BatchAllocator redefinitionBatchAllocator;
        protected final RedefinitionStrategy.DiscoveryStrategy redefinitionDiscoveryStrategy;
        protected final RedefinitionStrategy.Listener redefinitionListener;
        protected final RedefinitionStrategy.ResubmissionStrategy redefinitionResubmissionStrategy;
        protected final RedefinitionStrategy redefinitionStrategy;
        protected final Transformation transformation;
        protected final TypeStrategy typeStrategy;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Default defaultR = (Default) obj;
            return this.redefinitionStrategy.equals(defaultR.redefinitionStrategy) && this.lambdaInstrumentationStrategy.equals(defaultR.lambdaInstrumentationStrategy) && this.byteBuddy.equals(defaultR.byteBuddy) && this.listener.equals(defaultR.listener) && this.circularityLock.equals(defaultR.circularityLock) && this.poolStrategy.equals(defaultR.poolStrategy) && this.typeStrategy.equals(defaultR.typeStrategy) && this.locationStrategy.equals(defaultR.locationStrategy) && this.nativeMethodStrategy.equals(defaultR.nativeMethodStrategy) && this.initializationStrategy.equals(defaultR.initializationStrategy) && this.redefinitionDiscoveryStrategy.equals(defaultR.redefinitionDiscoveryStrategy) && this.redefinitionBatchAllocator.equals(defaultR.redefinitionBatchAllocator) && this.redefinitionListener.equals(defaultR.redefinitionListener) && this.redefinitionResubmissionStrategy.equals(defaultR.redefinitionResubmissionStrategy) && this.bootstrapInjectionStrategy.equals(defaultR.bootstrapInjectionStrategy) && this.descriptionStrategy.equals(defaultR.descriptionStrategy) && this.fallbackStrategy.equals(defaultR.fallbackStrategy) && this.classFileBufferStrategy.equals(defaultR.classFileBufferStrategy) && this.installationListener.equals(defaultR.installationListener) && this.ignoredTypeMatcher.equals(defaultR.ignoredTypeMatcher) && this.transformation.equals(defaultR.transformation);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((((((((((527 + this.byteBuddy.hashCode()) * 31) + this.listener.hashCode()) * 31) + this.circularityLock.hashCode()) * 31) + this.poolStrategy.hashCode()) * 31) + this.typeStrategy.hashCode()) * 31) + this.locationStrategy.hashCode()) * 31) + this.nativeMethodStrategy.hashCode()) * 31) + this.initializationStrategy.hashCode()) * 31) + this.redefinitionStrategy.hashCode()) * 31) + this.redefinitionDiscoveryStrategy.hashCode()) * 31) + this.redefinitionBatchAllocator.hashCode()) * 31) + this.redefinitionListener.hashCode()) * 31) + this.redefinitionResubmissionStrategy.hashCode()) * 31) + this.bootstrapInjectionStrategy.hashCode()) * 31) + this.lambdaInstrumentationStrategy.hashCode()) * 31) + this.descriptionStrategy.hashCode()) * 31) + this.fallbackStrategy.hashCode()) * 31) + this.classFileBufferStrategy.hashCode()) * 31) + this.installationListener.hashCode()) * 31) + this.ignoredTypeMatcher.hashCode()) * 31) + this.transformation.hashCode();
        }

        public Default() {
            this(new ByteBuddy());
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Default(net.bytebuddy.ByteBuddy r27) {
            /*
                r26 = this;
                r0 = r26
                r1 = r27
                net.bytebuddy.agent.builder.AgentBuilder$Listener$NoOp r2 = net.bytebuddy.agent.builder.AgentBuilder.Listener.NoOp.INSTANCE
                net.bytebuddy.agent.builder.AgentBuilder$CircularityLock r3 = DEFAULT_LOCK
                net.bytebuddy.agent.builder.AgentBuilder$PoolStrategy$Default r4 = net.bytebuddy.agent.builder.AgentBuilder.PoolStrategy.Default.FAST
                net.bytebuddy.agent.builder.AgentBuilder$TypeStrategy$Default r5 = net.bytebuddy.agent.builder.AgentBuilder.TypeStrategy.Default.REBASE
                net.bytebuddy.agent.builder.AgentBuilder$LocationStrategy$ForClassLoader r6 = net.bytebuddy.agent.builder.AgentBuilder.LocationStrategy.ForClassLoader.STRONG
                net.bytebuddy.agent.builder.AgentBuilder$Default$NativeMethodStrategy$Disabled r7 = net.bytebuddy.agent.builder.AgentBuilder.Default.NativeMethodStrategy.Disabled.INSTANCE
                net.bytebuddy.agent.builder.AgentBuilder$InitializationStrategy$SelfInjection$Split r9 = new net.bytebuddy.agent.builder.AgentBuilder$InitializationStrategy$SelfInjection$Split
                r8 = r9
                r9.<init>()
                net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy r9 = net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DISABLED
                net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$DiscoveryStrategy$SinglePass r10 = net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.SinglePass.INSTANCE
                net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$BatchAllocator$ForTotal r11 = net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.BatchAllocator.ForTotal.INSTANCE
                net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$Listener$NoOp r12 = net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.Listener.NoOp.INSTANCE
                net.bytebuddy.agent.builder.AgentBuilder$RedefinitionStrategy$ResubmissionStrategy$Disabled r13 = net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.ResubmissionStrategy.Disabled.INSTANCE
                net.bytebuddy.agent.builder.AgentBuilder$Default$BootstrapInjectionStrategy$Disabled r14 = net.bytebuddy.agent.builder.AgentBuilder.Default.BootstrapInjectionStrategy.Disabled.INSTANCE
                net.bytebuddy.agent.builder.AgentBuilder$LambdaInstrumentationStrategy r15 = net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy.DISABLED
                net.bytebuddy.agent.builder.AgentBuilder$DescriptionStrategy$Default r16 = net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy.Default.HYBRID
                net.bytebuddy.agent.builder.AgentBuilder$FallbackStrategy r17 = net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy.ByThrowableType.ofOptionalTypes()
                net.bytebuddy.agent.builder.AgentBuilder$ClassFileBufferStrategy$Default r18 = net.bytebuddy.agent.builder.AgentBuilder.ClassFileBufferStrategy.Default.RETAINING
                net.bytebuddy.agent.builder.AgentBuilder$InstallationListener$NoOp r19 = net.bytebuddy.agent.builder.AgentBuilder.InstallationListener.NoOp.INSTANCE
                r22 = r0
                net.bytebuddy.agent.builder.AgentBuilder$RawMatcher$Disjunction r0 = new net.bytebuddy.agent.builder.AgentBuilder$RawMatcher$Disjunction
                r20 = r0
                net.bytebuddy.agent.builder.AgentBuilder$RawMatcher$ForElementMatchers r1 = new net.bytebuddy.agent.builder.AgentBuilder$RawMatcher$ForElementMatchers
                r23 = r2
                net.bytebuddy.matcher.ElementMatcher$Junction r2 = net.bytebuddy.matcher.ElementMatchers.any()
                r24 = r3
                net.bytebuddy.matcher.ElementMatcher$Junction r3 = net.bytebuddy.matcher.ElementMatchers.isBootstrapClassLoader()
                r25 = r4
                net.bytebuddy.matcher.ElementMatcher$Junction r4 = net.bytebuddy.matcher.ElementMatchers.isExtensionClassLoader()
                net.bytebuddy.matcher.ElementMatcher$Junction r3 = r3.or(r4)
                r1.<init>(r2, r3)
                net.bytebuddy.agent.builder.AgentBuilder$RawMatcher$ForElementMatchers r2 = new net.bytebuddy.agent.builder.AgentBuilder$RawMatcher$ForElementMatchers
                java.lang.String r3 = "net.bytebuddy."
                net.bytebuddy.matcher.ElementMatcher$Junction r3 = net.bytebuddy.matcher.ElementMatchers.nameStartsWith(r3)
                java.lang.String r4 = "sun.reflect."
                net.bytebuddy.matcher.ElementMatcher$Junction r4 = net.bytebuddy.matcher.ElementMatchers.nameStartsWith(r4)
                net.bytebuddy.matcher.ElementMatcher$Junction r3 = r3.or(r4)
                net.bytebuddy.matcher.ElementMatcher$Junction r4 = net.bytebuddy.matcher.ElementMatchers.isSynthetic()
                net.bytebuddy.matcher.ElementMatcher$Junction r3 = r3.or(r4)
                r2.<init>(r3)
                r0.<init>(r1, r2)
                net.bytebuddy.agent.builder.AgentBuilder$Default$Transformation$Ignored r21 = net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Ignored.INSTANCE
                r1 = r27
                r0 = r22
                r2 = r23
                r3 = r24
                r4 = r25
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.Default.<init>(net.bytebuddy.ByteBuddy):void");
        }

        protected Default(ByteBuddy byteBuddy2, Listener listener2, CircularityLock circularityLock2, PoolStrategy poolStrategy2, TypeStrategy typeStrategy2, LocationStrategy locationStrategy2, NativeMethodStrategy nativeMethodStrategy2, InitializationStrategy initializationStrategy2, RedefinitionStrategy redefinitionStrategy2, RedefinitionStrategy.DiscoveryStrategy discoveryStrategy, RedefinitionStrategy.BatchAllocator batchAllocator, RedefinitionStrategy.Listener listener3, RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy2, LambdaInstrumentationStrategy lambdaInstrumentationStrategy2, DescriptionStrategy descriptionStrategy2, FallbackStrategy fallbackStrategy2, ClassFileBufferStrategy classFileBufferStrategy2, InstallationListener installationListener2, RawMatcher rawMatcher, Transformation transformation2) {
            this.byteBuddy = byteBuddy2;
            this.listener = listener2;
            this.circularityLock = circularityLock2;
            this.poolStrategy = poolStrategy2;
            this.typeStrategy = typeStrategy2;
            this.locationStrategy = locationStrategy2;
            this.nativeMethodStrategy = nativeMethodStrategy2;
            this.initializationStrategy = initializationStrategy2;
            this.redefinitionStrategy = redefinitionStrategy2;
            this.redefinitionDiscoveryStrategy = discoveryStrategy;
            this.redefinitionBatchAllocator = batchAllocator;
            this.redefinitionListener = listener3;
            this.redefinitionResubmissionStrategy = resubmissionStrategy;
            this.bootstrapInjectionStrategy = bootstrapInjectionStrategy2;
            this.lambdaInstrumentationStrategy = lambdaInstrumentationStrategy2;
            this.descriptionStrategy = descriptionStrategy2;
            this.fallbackStrategy = fallbackStrategy2;
            this.classFileBufferStrategy = classFileBufferStrategy2;
            this.installationListener = installationListener2;
            this.ignoredTypeMatcher = rawMatcher;
            this.transformation = transformation2;
        }

        public static AgentBuilder of(Plugin... pluginArr) {
            return of((List<? extends Plugin>) Arrays.asList(pluginArr));
        }

        public static AgentBuilder of(List<? extends Plugin> list) {
            return of((EntryPoint) EntryPoint.Default.REBASE, list);
        }

        public static AgentBuilder of(EntryPoint entryPoint, Plugin... pluginArr) {
            return of(entryPoint, (List<? extends Plugin>) Arrays.asList(pluginArr));
        }

        public static AgentBuilder of(EntryPoint entryPoint, List<? extends Plugin> list) {
            return of(entryPoint, ClassFileVersion.ofThisVm(), list);
        }

        public static AgentBuilder of(ClassFileVersion classFileVersion, Plugin... pluginArr) {
            return of(classFileVersion, (List<? extends Plugin>) Arrays.asList(pluginArr));
        }

        public static AgentBuilder of(ClassFileVersion classFileVersion, List<? extends Plugin> list) {
            return of((EntryPoint) EntryPoint.Default.REBASE, classFileVersion, list);
        }

        public static AgentBuilder of(EntryPoint entryPoint, ClassFileVersion classFileVersion, Plugin... pluginArr) {
            return of(entryPoint, classFileVersion, (List<? extends Plugin>) Arrays.asList(pluginArr));
        }

        public static AgentBuilder of(EntryPoint entryPoint, ClassFileVersion classFileVersion, List<? extends Plugin> list) {
            AgentBuilder with = new Default(entryPoint.byteBuddy(classFileVersion)).with((TypeStrategy) new TypeStrategy.ForBuildEntryPoint(entryPoint));
            for (Plugin plugin : list) {
                with = with.type((ElementMatcher<? super TypeDescription>) plugin).transform(new Transformer.ForBuildPlugin(plugin));
            }
            return with;
        }

        public AgentBuilder with(ByteBuddy byteBuddy2) {
            return new Default(byteBuddy2, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(Listener listener2) {
            ByteBuddy byteBuddy2 = this.byteBuddy;
            Listener.Compound compound = r4;
            Listener.Compound compound2 = new Listener.Compound(this.listener, listener2);
            return new Default(byteBuddy2, compound, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(CircularityLock circularityLock2) {
            return new Default(this.byteBuddy, this.listener, circularityLock2, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(TypeStrategy typeStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, typeStrategy2, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(PoolStrategy poolStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, poolStrategy2, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(LocationStrategy locationStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, locationStrategy2, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder enableNativeMethodPrefix(String str) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, NativeMethodStrategy.ForPrefix.of(str), this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder disableNativeMethodPrefix() {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, NativeMethodStrategy.Disabled.INSTANCE, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public RedefinitionListenable.WithoutBatchStrategy with(RedefinitionStrategy redefinitionStrategy2) {
            return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, redefinitionStrategy2, RedefinitionStrategy.DiscoveryStrategy.SinglePass.INSTANCE, RedefinitionStrategy.BatchAllocator.ForTotal.INSTANCE, RedefinitionStrategy.Listener.NoOp.INSTANCE, RedefinitionStrategy.ResubmissionStrategy.Disabled.INSTANCE, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(InitializationStrategy initializationStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, initializationStrategy2, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(LambdaInstrumentationStrategy lambdaInstrumentationStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, lambdaInstrumentationStrategy2, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(DescriptionStrategy descriptionStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, descriptionStrategy2, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(FallbackStrategy fallbackStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, fallbackStrategy2, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(ClassFileBufferStrategy classFileBufferStrategy2) {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, classFileBufferStrategy2, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder with(InstallationListener installationListener2) {
            ByteBuddy byteBuddy2 = this.byteBuddy;
            Listener listener2 = this.listener;
            CircularityLock circularityLock2 = this.circularityLock;
            PoolStrategy poolStrategy2 = this.poolStrategy;
            TypeStrategy typeStrategy2 = this.typeStrategy;
            LocationStrategy locationStrategy2 = this.locationStrategy;
            NativeMethodStrategy nativeMethodStrategy2 = this.nativeMethodStrategy;
            InitializationStrategy initializationStrategy2 = this.initializationStrategy;
            RedefinitionStrategy redefinitionStrategy2 = this.redefinitionStrategy;
            RedefinitionStrategy.DiscoveryStrategy discoveryStrategy = this.redefinitionDiscoveryStrategy;
            RedefinitionStrategy.BatchAllocator batchAllocator = this.redefinitionBatchAllocator;
            RedefinitionStrategy.Listener listener3 = this.redefinitionListener;
            RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy = this.redefinitionResubmissionStrategy;
            BootstrapInjectionStrategy bootstrapInjectionStrategy2 = this.bootstrapInjectionStrategy;
            LambdaInstrumentationStrategy lambdaInstrumentationStrategy2 = this.lambdaInstrumentationStrategy;
            DescriptionStrategy descriptionStrategy2 = this.descriptionStrategy;
            FallbackStrategy fallbackStrategy2 = this.fallbackStrategy;
            ClassFileBufferStrategy classFileBufferStrategy2 = this.classFileBufferStrategy;
            InstallationListener.Compound compound = r1;
            ByteBuddy byteBuddy3 = byteBuddy2;
            InstallationListener.Compound compound2 = new InstallationListener.Compound(this.installationListener, installationListener2);
            return new Default(byteBuddy3, listener2, circularityLock2, poolStrategy2, typeStrategy2, locationStrategy2, nativeMethodStrategy2, initializationStrategy2, redefinitionStrategy2, discoveryStrategy, batchAllocator, listener3, resubmissionStrategy, bootstrapInjectionStrategy2, lambdaInstrumentationStrategy2, descriptionStrategy2, fallbackStrategy2, classFileBufferStrategy2, compound, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder enableBootstrapInjection(Instrumentation instrumentation, File file) {
            ByteBuddy byteBuddy2 = this.byteBuddy;
            ByteBuddy byteBuddy3 = byteBuddy2;
            File file2 = file;
            return new Default(byteBuddy3, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, new BootstrapInjectionStrategy.Enabled(file2, instrumentation), this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder enableUnsafeBootstrapInjection() {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, BootstrapInjectionStrategy.Unsafe.INSTANCE, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder disableBootstrapInjection() {
            return new Default(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, BootstrapInjectionStrategy.Disabled.INSTANCE, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder disableClassFormatChanges() {
            return new Default(this.byteBuddy.with((Implementation.Context.Factory) Implementation.Context.Disabled.Factory.INSTANCE), this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy == TypeStrategy.Default.DECORATE ? TypeStrategy.Default.DECORATE : TypeStrategy.Default.REDEFINE_FROZEN, this.locationStrategy, NativeMethodStrategy.Disabled.INSTANCE, InitializationStrategy.NoOp.INSTANCE, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
        }

        public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Class<?>... clsArr) {
            return JavaModule.isSupported() ? with(Listener.ModuleReadEdgeCompleting.of(instrumentation, false, clsArr)) : this;
        }

        public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
            return assureReadEdgeTo(instrumentation, (Collection<? extends JavaModule>) Arrays.asList(javaModuleArr));
        }

        public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
            return with((Listener) new Listener.ModuleReadEdgeCompleting(instrumentation, false, new HashSet(collection)));
        }

        public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Class<?>... clsArr) {
            return JavaModule.isSupported() ? with(Listener.ModuleReadEdgeCompleting.of(instrumentation, true, clsArr)) : this;
        }

        public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
            return assureReadEdgeFromAndTo(instrumentation, (Collection<? extends JavaModule>) Arrays.asList(javaModuleArr));
        }

        public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
            return with((Listener) new Listener.ModuleReadEdgeCompleting(instrumentation, true, new HashSet(collection)));
        }

        public Identified.Narrowable type(RawMatcher rawMatcher) {
            return new Transforming(rawMatcher, Transformer.NoOp.INSTANCE, true);
        }

        public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher) {
            return type(elementMatcher, ElementMatchers.any());
        }

        public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
            return type(elementMatcher, elementMatcher2, ElementMatchers.any());
        }

        public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
            return type((RawMatcher) new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, ElementMatchers.not(ElementMatchers.supportsModules()).or(elementMatcher3)));
        }

        public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher) {
            return ignore(elementMatcher, ElementMatchers.any());
        }

        public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
            return ignore(elementMatcher, elementMatcher2, ElementMatchers.any());
        }

        public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
            return ignore((RawMatcher) new RawMatcher.ForElementMatchers(elementMatcher, elementMatcher2, ElementMatchers.not(ElementMatchers.supportsModules()).or(elementMatcher3)));
        }

        public Ignored ignore(RawMatcher rawMatcher) {
            return new Ignoring(rawMatcher);
        }

        public ResettableClassFileTransformer makeRaw() {
            return makeRaw(this.listener, InstallationListener.NoOp.INSTANCE);
        }

        private ResettableClassFileTransformer makeRaw(Listener listener2, InstallationListener installationListener2) {
            ExecutingTransformer.Factory factory = ExecutingTransformer.FACTORY;
            return factory.make(this.byteBuddy, listener2, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, installationListener2, this.ignoredTypeMatcher, this.transformation, this.circularityLock);
        }

        public ResettableClassFileTransformer installOn(Instrumentation instrumentation) {
            return installOn(instrumentation, TransformerDecorator.NoOp.INSTANCE);
        }

        public ResettableClassFileTransformer installOn(Instrumentation instrumentation, TransformerDecorator transformerDecorator) {
            Default defaultR;
            ResettableClassFileTransformer resettableClassFileTransformer;
            Instrumentation instrumentation2;
            ResettableClassFileTransformer resettableClassFileTransformer2;
            Instrumentation instrumentation3 = instrumentation;
            if (this.circularityLock.acquire()) {
                try {
                    RedefinitionStrategy.ResubmissionStrategy.Installation apply = this.redefinitionResubmissionStrategy.apply(instrumentation, this.locationStrategy, this.listener, this.installationListener, this.circularityLock, new RawMatcher.Conjunction(new RawMatcher.Inversion(this.ignoredTypeMatcher), this.transformation), this.redefinitionStrategy, this.redefinitionBatchAllocator, this.redefinitionListener);
                    ResettableClassFileTransformer decorate = transformerDecorator.decorate(makeRaw(apply.getListener(), apply.getInstallationListener()));
                    apply.getInstallationListener().onBeforeInstall(instrumentation3, decorate);
                    try {
                        DISPATCHER.addTransformer(instrumentation3, decorate, this.redefinitionStrategy.isRetransforming());
                        if (this.nativeMethodStrategy.isEnabled(instrumentation3)) {
                            DISPATCHER.setNativeMethodPrefix(instrumentation3, decorate, this.nativeMethodStrategy.getPrefix());
                        }
                        this.lambdaInstrumentationStrategy.apply(this.byteBuddy, instrumentation3, decorate);
                        if (this.redefinitionStrategy.isEnabled()) {
                            RedefinitionStrategy redefinitionStrategy2 = this.redefinitionStrategy;
                            Listener listener2 = apply.getListener();
                            CircularityLock circularityLock2 = this.circularityLock;
                            PoolStrategy poolStrategy2 = this.poolStrategy;
                            LocationStrategy locationStrategy2 = this.locationStrategy;
                            RedefinitionStrategy.DiscoveryStrategy discoveryStrategy = this.redefinitionDiscoveryStrategy;
                            RedefinitionStrategy.BatchAllocator batchAllocator = this.redefinitionBatchAllocator;
                            RedefinitionStrategy.Listener listener3 = this.redefinitionListener;
                            LambdaInstrumentationStrategy lambdaInstrumentationStrategy2 = this.lambdaInstrumentationStrategy;
                            DescriptionStrategy descriptionStrategy2 = this.descriptionStrategy;
                            FallbackStrategy fallbackStrategy2 = this.fallbackStrategy;
                            Transformation transformation2 = this.transformation;
                            RawMatcher rawMatcher = this.ignoredTypeMatcher;
                            resettableClassFileTransformer2 = decorate;
                            instrumentation2 = instrumentation3;
                            try {
                                redefinitionStrategy2.apply(instrumentation, listener2, circularityLock2, poolStrategy2, locationStrategy2, discoveryStrategy, batchAllocator, listener3, lambdaInstrumentationStrategy2, descriptionStrategy2, fallbackStrategy2, transformation2, rawMatcher);
                            } catch (Throwable th) {
                                th = th;
                            }
                        } else {
                            resettableClassFileTransformer2 = decorate;
                            instrumentation2 = instrumentation3;
                        }
                        resettableClassFileTransformer = resettableClassFileTransformer2;
                    } catch (Throwable th2) {
                        th = th2;
                        resettableClassFileTransformer2 = decorate;
                        instrumentation2 = instrumentation3;
                        try {
                            resettableClassFileTransformer = resettableClassFileTransformer2;
                            Throwable onError = apply.getInstallationListener().onError(instrumentation2, resettableClassFileTransformer, th);
                            if (onError != null) {
                                defaultR = this;
                                try {
                                    instrumentation2.removeTransformer(resettableClassFileTransformer);
                                    throw new IllegalStateException("Could not install class file transformer", onError);
                                } catch (Throwable th3) {
                                    th = th3;
                                    defaultR.circularityLock.release();
                                    throw th;
                                }
                            }
                            apply.getInstallationListener().onInstall(instrumentation2, resettableClassFileTransformer);
                            this.circularityLock.release();
                            return resettableClassFileTransformer;
                        } catch (Throwable th4) {
                            th = th4;
                            defaultR = this;
                            defaultR.circularityLock.release();
                            throw th;
                        }
                    }
                    apply.getInstallationListener().onInstall(instrumentation2, resettableClassFileTransformer);
                    this.circularityLock.release();
                    return resettableClassFileTransformer;
                } catch (Throwable th5) {
                    th = th5;
                    defaultR = this;
                    defaultR.circularityLock.release();
                    throw th;
                }
            } else {
                throw new IllegalStateException("Could not acquire the circularity lock upon installation.");
            }
        }

        public ResettableClassFileTransformer installOnByteBuddyAgent() {
            return installOnByteBuddyAgent(TransformerDecorator.NoOp.INSTANCE);
        }

        public ResettableClassFileTransformer installOnByteBuddyAgent(TransformerDecorator transformerDecorator) {
            try {
                return installOn((Instrumentation) ClassLoader.getSystemClassLoader().loadClass(INSTALLER_TYPE).getMethod(INSTRUMENTATION_GETTER, new Class[0]).invoke(STATIC_MEMBER, new Object[0]), transformerDecorator);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                throw new IllegalStateException("The Byte Buddy agent is not installed or not accessible", e2);
            }
        }

        protected interface Dispatcher {
            void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z);

            boolean isNativeMethodPrefixSupported(Instrumentation instrumentation);

            void setNativeMethodPrefix(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, String str);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        return new ForJava6CapableVm(Instrumentation.class.getMethod("isNativeMethodPrefixSupported", new Class[0]), Instrumentation.class.getMethod("setNativeMethodPrefix", new Class[]{ClassFileTransformer.class, String.class}), Instrumentation.class.getMethod("addTransformer", new Class[]{ClassFileTransformer.class, Boolean.TYPE}));
                    } catch (NoSuchMethodException unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public boolean isNativeMethodPrefixSupported(Instrumentation instrumentation) {
                    return false;
                }

                public void setNativeMethodPrefix(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, String str) {
                    throw new UnsupportedOperationException("The current VM does not support native method prefixes");
                }

                public void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z) {
                    if (!z) {
                        instrumentation.addTransformer(classFileTransformer);
                        return;
                    }
                    throw new UnsupportedOperationException("The current VM does not support retransformation");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava6CapableVm implements Dispatcher {
                private final Method addTransformer;
                private final Method isNativeMethodPrefixSupported;
                private final Method setNativeMethodPrefix;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava6CapableVm forJava6CapableVm = (ForJava6CapableVm) obj;
                    return this.isNativeMethodPrefixSupported.equals(forJava6CapableVm.isNativeMethodPrefixSupported) && this.setNativeMethodPrefix.equals(forJava6CapableVm.setNativeMethodPrefix) && this.addTransformer.equals(forJava6CapableVm.addTransformer);
                }

                public int hashCode() {
                    return ((((527 + this.isNativeMethodPrefixSupported.hashCode()) * 31) + this.setNativeMethodPrefix.hashCode()) * 31) + this.addTransformer.hashCode();
                }

                protected ForJava6CapableVm(Method method, Method method2, Method method3) {
                    this.isNativeMethodPrefixSupported = method;
                    this.setNativeMethodPrefix = method2;
                    this.addTransformer = method3;
                }

                public boolean isNativeMethodPrefixSupported(Instrumentation instrumentation) {
                    try {
                        return ((Boolean) this.isNativeMethodPrefixSupported.invoke(instrumentation, new Object[0])).booleanValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#isNativeMethodPrefixSupported", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#isNativeMethodPrefixSupported", e2.getCause());
                    }
                }

                public void setNativeMethodPrefix(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, String str) {
                    try {
                        this.setNativeMethodPrefix.invoke(instrumentation, new Object[]{classFileTransformer, str});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#setNativeMethodPrefix", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#setNativeMethodPrefix", e2.getCause());
                    }
                }

                public void addTransformer(Instrumentation instrumentation, ClassFileTransformer classFileTransformer, boolean z) {
                    try {
                        this.addTransformer.invoke(instrumentation, new Object[]{classFileTransformer, Boolean.valueOf(z)});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.instrument.Instrumentation#addTransformer", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.instrument.Instrumentation#addTransformer", e2.getCause());
                    }
                }
            }
        }

        protected interface BootstrapInjectionStrategy {
            ClassInjector make(ProtectionDomain protectionDomain);

            public enum Disabled implements BootstrapInjectionStrategy {
                INSTANCE;

                public ClassInjector make(ProtectionDomain protectionDomain) {
                    throw new IllegalStateException("Injecting classes into the bootstrap class loader was not enabled");
                }
            }

            public enum Unsafe implements BootstrapInjectionStrategy {
                INSTANCE;

                public ClassInjector make(ProtectionDomain protectionDomain) {
                    return new ClassInjector.UsingUnsafe(ClassLoadingStrategy.BOOTSTRAP_LOADER, protectionDomain);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Enabled implements BootstrapInjectionStrategy {
                private final File folder;
                private final Instrumentation instrumentation;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Enabled enabled = (Enabled) obj;
                    return this.folder.equals(enabled.folder) && this.instrumentation.equals(enabled.instrumentation);
                }

                public int hashCode() {
                    return ((527 + this.folder.hashCode()) * 31) + this.instrumentation.hashCode();
                }

                public Enabled(File file, Instrumentation instrumentation2) {
                    this.folder = file;
                    this.instrumentation = instrumentation2;
                }

                public ClassInjector make(ProtectionDomain protectionDomain) {
                    return ClassInjector.UsingInstrumentation.of(this.folder, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, this.instrumentation);
                }
            }
        }

        protected interface NativeMethodStrategy {
            String getPrefix();

            boolean isEnabled(Instrumentation instrumentation);

            MethodNameTransformer resolve();

            public enum Disabled implements NativeMethodStrategy {
                INSTANCE;

                public boolean isEnabled(Instrumentation instrumentation) {
                    return false;
                }

                public MethodNameTransformer resolve() {
                    return MethodNameTransformer.Suffixing.withRandomSuffix();
                }

                public String getPrefix() {
                    throw new IllegalStateException("A disabled native method strategy does not define a method name prefix");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForPrefix implements NativeMethodStrategy {
                private final String prefix;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.prefix.equals(((ForPrefix) obj).prefix);
                }

                public int hashCode() {
                    return 527 + this.prefix.hashCode();
                }

                protected ForPrefix(String str) {
                    this.prefix = str;
                }

                protected static NativeMethodStrategy of(String str) {
                    if (str.length() != 0) {
                        return new ForPrefix(str);
                    }
                    throw new IllegalArgumentException("A method name prefix must not be the empty string");
                }

                public MethodNameTransformer resolve() {
                    return new MethodNameTransformer.Prefixing(this.prefix);
                }

                public boolean isEnabled(Instrumentation instrumentation) {
                    if (Default.DISPATCHER.isNativeMethodPrefixSupported(instrumentation)) {
                        return true;
                    }
                    throw new IllegalArgumentException("A prefix for native methods is not supported: " + instrumentation);
                }

                public String getPrefix() {
                    return this.prefix;
                }
            }
        }

        protected interface Transformation extends RawMatcher {
            Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool);

            public interface Resolution {

                public interface Decoratable extends Resolution {
                    Resolution append(Transformer transformer);
                }

                byte[] apply(InitializationStrategy initializationStrategy, ClassFileLocator classFileLocator, TypeStrategy typeStrategy, ByteBuddy byteBuddy, NativeMethodStrategy nativeMethodStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, AccessControlContext accessControlContext, Listener listener);

                Resolution asDecoratorOf(Resolution resolution);

                Sort getSort();

                Resolution prepend(Decoratable decoratable);

                public enum Sort {
                    TERMINAL(true),
                    DECORATOR(true),
                    UNDEFINED(false);
                    
                    private final boolean alive;

                    private Sort(boolean z) {
                        this.alive = z;
                    }

                    /* access modifiers changed from: protected */
                    public boolean isAlive() {
                        return this.alive;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class Unresolved implements Resolution {
                    private final ClassLoader classLoader;
                    private final boolean loaded;
                    private final JavaModule module;
                    private final TypeDescription typeDescription;

                    public Resolution asDecoratorOf(Resolution resolution) {
                        return resolution;
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Unresolved unresolved = (Unresolved) obj;
                        return this.loaded == unresolved.loaded && this.typeDescription.equals(unresolved.typeDescription) && this.classLoader.equals(unresolved.classLoader) && this.module.equals(unresolved.module);
                    }

                    public int hashCode() {
                        return ((((((527 + this.typeDescription.hashCode()) * 31) + this.classLoader.hashCode()) * 31) + this.module.hashCode()) * 31) + (this.loaded ? 1 : 0);
                    }

                    public Resolution prepend(Decoratable decoratable) {
                        return decoratable;
                    }

                    protected Unresolved(TypeDescription typeDescription2, ClassLoader classLoader2, JavaModule javaModule, boolean z) {
                        this.typeDescription = typeDescription2;
                        this.classLoader = classLoader2;
                        this.module = javaModule;
                        this.loaded = z;
                    }

                    public Sort getSort() {
                        return Sort.UNDEFINED;
                    }

                    public byte[] apply(InitializationStrategy initializationStrategy, ClassFileLocator classFileLocator, TypeStrategy typeStrategy, ByteBuddy byteBuddy, NativeMethodStrategy nativeMethodStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, AccessControlContext accessControlContext, Listener listener) {
                        listener.onIgnored(this.typeDescription, this.classLoader, this.module, this.loaded);
                        return Default.NO_TRANSFORMATION;
                    }
                }
            }

            public enum Ignored implements Transformation {
                INSTANCE;

                public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                    return false;
                }

                public Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                    return new Resolution.Unresolved(typeDescription, classLoader, javaModule, z);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Simple implements Transformation {
                private final boolean decorator;
                private final RawMatcher rawMatcher;
                private final Transformer transformer;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Simple simple = (Simple) obj;
                    return this.decorator == simple.decorator && this.rawMatcher.equals(simple.rawMatcher) && this.transformer.equals(simple.transformer);
                }

                public int hashCode() {
                    return ((((527 + this.rawMatcher.hashCode()) * 31) + this.transformer.hashCode()) * 31) + (this.decorator ? 1 : 0);
                }

                protected Simple(RawMatcher rawMatcher2, Transformer transformer2, boolean z) {
                    this.rawMatcher = rawMatcher2;
                    this.transformer = transformer2;
                    this.decorator = z;
                }

                public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                    return this.rawMatcher.matches(typeDescription, classLoader, javaModule, cls, protectionDomain);
                }

                public Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                    if (!matches(typeDescription, classLoader, javaModule, cls, protectionDomain)) {
                        return new Resolution.Unresolved(typeDescription, classLoader, javaModule, z);
                    }
                    return new Resolution(typeDescription, classLoader, javaModule, protectionDomain, z, typePool, this.transformer, this.decorator);
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class Resolution implements Resolution.Decoratable {
                    private final ClassLoader classLoader;
                    private final boolean decorator;
                    private final boolean loaded;
                    private final JavaModule module;
                    private final ProtectionDomain protectionDomain;
                    private final Transformer transformer;
                    private final TypeDescription typeDescription;
                    private final TypePool typePool;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Resolution resolution = (Resolution) obj;
                        return this.loaded == resolution.loaded && this.decorator == resolution.decorator && this.typeDescription.equals(resolution.typeDescription) && this.classLoader.equals(resolution.classLoader) && this.module.equals(resolution.module) && this.protectionDomain.equals(resolution.protectionDomain) && this.typePool.equals(resolution.typePool) && this.transformer.equals(resolution.transformer);
                    }

                    public int hashCode() {
                        return ((((((((((((((527 + this.typeDescription.hashCode()) * 31) + this.classLoader.hashCode()) * 31) + this.module.hashCode()) * 31) + this.protectionDomain.hashCode()) * 31) + (this.loaded ? 1 : 0)) * 31) + this.typePool.hashCode()) * 31) + this.transformer.hashCode()) * 31) + (this.decorator ? 1 : 0);
                    }

                    protected Resolution(TypeDescription typeDescription2, ClassLoader classLoader2, JavaModule javaModule, ProtectionDomain protectionDomain2, boolean z, TypePool typePool2, Transformer transformer2, boolean z2) {
                        this.typeDescription = typeDescription2;
                        this.classLoader = classLoader2;
                        this.module = javaModule;
                        this.protectionDomain = protectionDomain2;
                        this.loaded = z;
                        this.typePool = typePool2;
                        this.transformer = transformer2;
                        this.decorator = z2;
                    }

                    public Resolution.Sort getSort() {
                        return this.decorator ? Resolution.Sort.DECORATOR : Resolution.Sort.TERMINAL;
                    }

                    public Resolution asDecoratorOf(Resolution resolution) {
                        return resolution.prepend(this);
                    }

                    public Resolution prepend(Resolution.Decoratable decoratable) {
                        return decoratable.append(this.transformer);
                    }

                    public Resolution append(Transformer transformer2) {
                        return new Resolution(this.typeDescription, this.classLoader, this.module, this.protectionDomain, this.loaded, this.typePool, new Transformer.Compound(this.transformer, transformer2), this.decorator);
                    }

                    public byte[] apply(InitializationStrategy initializationStrategy, ClassFileLocator classFileLocator, TypeStrategy typeStrategy, ByteBuddy byteBuddy, NativeMethodStrategy nativeMethodStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, AccessControlContext accessControlContext, Listener listener) {
                        InitializationStrategy.Dispatcher dispatcher = initializationStrategy.dispatcher();
                        DynamicType.Unloaded<?> make = dispatcher.apply(this.transformer.transform(typeStrategy.builder(this.typeDescription, byteBuddy, classFileLocator, nativeMethodStrategy.resolve(), this.classLoader, this.module, this.protectionDomain), this.typeDescription, this.classLoader, this.module)).make(TypeResolutionStrategy.Disabled.INSTANCE, this.typePool);
                        ClassLoader classLoader2 = this.classLoader;
                        dispatcher.register(make, classLoader2, new BootstrapClassLoaderCapableInjectorFactory(bootstrapInjectionStrategy, classLoader2, this.protectionDomain));
                        listener.onTransformation(this.typeDescription, this.classLoader, this.module, this.loaded, make);
                        return make.getBytes();
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    protected static class BootstrapClassLoaderCapableInjectorFactory implements InitializationStrategy.Dispatcher.InjectorFactory {
                        private final BootstrapInjectionStrategy bootstrapInjectionStrategy;
                        private final ClassLoader classLoader;
                        private final ProtectionDomain protectionDomain;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            BootstrapClassLoaderCapableInjectorFactory bootstrapClassLoaderCapableInjectorFactory = (BootstrapClassLoaderCapableInjectorFactory) obj;
                            return this.bootstrapInjectionStrategy.equals(bootstrapClassLoaderCapableInjectorFactory.bootstrapInjectionStrategy) && this.classLoader.equals(bootstrapClassLoaderCapableInjectorFactory.classLoader) && this.protectionDomain.equals(bootstrapClassLoaderCapableInjectorFactory.protectionDomain);
                        }

                        public int hashCode() {
                            return ((((527 + this.bootstrapInjectionStrategy.hashCode()) * 31) + this.classLoader.hashCode()) * 31) + this.protectionDomain.hashCode();
                        }

                        protected BootstrapClassLoaderCapableInjectorFactory(BootstrapInjectionStrategy bootstrapInjectionStrategy2, ClassLoader classLoader2, ProtectionDomain protectionDomain2) {
                            this.bootstrapInjectionStrategy = bootstrapInjectionStrategy2;
                            this.classLoader = classLoader2;
                            this.protectionDomain = protectionDomain2;
                        }

                        public ClassInjector resolve() {
                            return this.classLoader == null ? this.bootstrapInjectionStrategy.make(this.protectionDomain) : new ClassInjector.UsingReflection(this.classLoader, this.protectionDomain);
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Compound implements Transformation {
                private final List<Transformation> transformations;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.transformations.equals(((Compound) obj).transformations);
                }

                public int hashCode() {
                    return 527 + this.transformations.hashCode();
                }

                protected Compound(Transformation... transformationArr) {
                    this((List<? extends Transformation>) Arrays.asList(transformationArr));
                }

                protected Compound(List<? extends Transformation> list) {
                    this.transformations = new ArrayList();
                    for (Transformation transformation : list) {
                        if (transformation instanceof Compound) {
                            this.transformations.addAll(((Compound) transformation).transformations);
                        } else if (!(transformation instanceof Ignored)) {
                            this.transformations.add(transformation);
                        }
                    }
                }

                public boolean matches(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, ProtectionDomain protectionDomain) {
                    for (Transformation matches : this.transformations) {
                        if (matches.matches(typeDescription, classLoader, javaModule, cls, protectionDomain)) {
                            return true;
                        }
                    }
                    return false;
                }

                public Resolution resolve(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                    JavaModule javaModule2;
                    ClassLoader classLoader2;
                    TypeDescription typeDescription2;
                    boolean z2;
                    if (cls != null) {
                        typeDescription2 = typeDescription;
                        classLoader2 = classLoader;
                        javaModule2 = javaModule;
                        z2 = true;
                    } else {
                        z2 = false;
                        typeDescription2 = typeDescription;
                        classLoader2 = classLoader;
                        javaModule2 = javaModule;
                    }
                    Resolution unresolved = new Resolution.Unresolved(typeDescription2, classLoader2, javaModule2, z2);
                    for (Transformation resolve : this.transformations) {
                        Resolution resolve2 = resolve.resolve(typeDescription, classLoader, javaModule, cls, z, protectionDomain, typePool);
                        int i = AnonymousClass1.$SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort[resolve2.getSort().ordinal()];
                        if (i == 1) {
                            return unresolved.asDecoratorOf(resolve2);
                        }
                        if (i == 2) {
                            unresolved = unresolved.asDecoratorOf(resolve2);
                        } else if (i != 3) {
                            throw new IllegalStateException("Unexpected resolution type: " + resolve2.getSort());
                        }
                    }
                    return unresolved;
                }
            }
        }

        protected static class ExecutingTransformer extends ResettableClassFileTransformer.AbstractBase {
            protected static final Factory FACTORY = ((Factory) AccessController.doPrivileged(Factory.CreationAction.INSTANCE));
            private final AccessControlContext accessControlContext = AccessController.getContext();
            private final BootstrapInjectionStrategy bootstrapInjectionStrategy;
            private final ByteBuddy byteBuddy;
            private final CircularityLock circularityLock;
            private final ClassFileBufferStrategy classFileBufferStrategy;
            private final DescriptionStrategy descriptionStrategy;
            private final FallbackStrategy fallbackStrategy;
            private final RawMatcher ignoredTypeMatcher;
            private final InitializationStrategy initializationStrategy;
            private final InstallationListener installationListener;
            private final LambdaInstrumentationStrategy lambdaInstrumentationStrategy;
            private final Listener listener;
            private final LocationStrategy locationStrategy;
            private final NativeMethodStrategy nativeMethodStrategy;
            private final PoolStrategy poolStrategy;
            private final Transformation transformation;
            private final TypeStrategy typeStrategy;

            public ExecutingTransformer(ByteBuddy byteBuddy2, Listener listener2, PoolStrategy poolStrategy2, TypeStrategy typeStrategy2, LocationStrategy locationStrategy2, NativeMethodStrategy nativeMethodStrategy2, InitializationStrategy initializationStrategy2, BootstrapInjectionStrategy bootstrapInjectionStrategy2, LambdaInstrumentationStrategy lambdaInstrumentationStrategy2, DescriptionStrategy descriptionStrategy2, FallbackStrategy fallbackStrategy2, ClassFileBufferStrategy classFileBufferStrategy2, InstallationListener installationListener2, RawMatcher rawMatcher, Transformation transformation2, CircularityLock circularityLock2) {
                this.byteBuddy = byteBuddy2;
                this.typeStrategy = typeStrategy2;
                this.poolStrategy = poolStrategy2;
                this.locationStrategy = locationStrategy2;
                this.listener = listener2;
                this.nativeMethodStrategy = nativeMethodStrategy2;
                this.initializationStrategy = initializationStrategy2;
                this.bootstrapInjectionStrategy = bootstrapInjectionStrategy2;
                this.lambdaInstrumentationStrategy = lambdaInstrumentationStrategy2;
                this.descriptionStrategy = descriptionStrategy2;
                this.fallbackStrategy = fallbackStrategy2;
                this.classFileBufferStrategy = classFileBufferStrategy2;
                this.installationListener = installationListener2;
                this.ignoredTypeMatcher = rawMatcher;
                this.transformation = transformation2;
                this.circularityLock = circularityLock2;
            }

            public byte[] transform(ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (!this.circularityLock.acquire()) {
                    return Default.NO_TRANSFORMATION;
                }
                try {
                    return (byte[]) AccessController.doPrivileged(new LegacyVmDispatcher(classLoader, str, cls, protectionDomain, bArr), this.accessControlContext);
                } finally {
                    this.circularityLock.release();
                }
            }

            /* access modifiers changed from: protected */
            public byte[] transform(Object obj, ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                if (!this.circularityLock.acquire()) {
                    return Default.NO_TRANSFORMATION;
                }
                try {
                    return (byte[]) AccessController.doPrivileged(new Java9CapableVmDispatcher(obj, classLoader, str, cls, protectionDomain, bArr), this.accessControlContext);
                } finally {
                    this.circularityLock.release();
                }
            }

            /* JADX INFO: finally extract failed */
            /* access modifiers changed from: private */
            public byte[] transform(JavaModule javaModule, ClassLoader classLoader, String str, Class<?> cls, ProtectionDomain protectionDomain, byte[] bArr) {
                TypePool typePool;
                ClassFileLocator.Compound compound;
                JavaModule javaModule2 = javaModule;
                ClassLoader classLoader2 = classLoader;
                String str2 = str;
                Class<?> cls2 = cls;
                if (str2 == null || !this.lambdaInstrumentationStrategy.isInstrumented(cls2)) {
                    return Default.NO_TRANSFORMATION;
                }
                String replace = str2.replace('/', '.');
                boolean z = true;
                try {
                    this.listener.onDiscovery(replace, classLoader2, javaModule2, cls2 != null);
                    ClassFileLocator.Compound compound2 = new ClassFileLocator.Compound(this.classFileBufferStrategy.resolve(replace, bArr, classLoader, javaModule, protectionDomain), this.locationStrategy.classFileLocator(classLoader2, javaModule2));
                    typePool = this.poolStrategy.typePool(compound2, classLoader2);
                    compound = compound2;
                    byte[] doTransform = doTransform(javaModule, classLoader, replace, cls, cls2 != null, protectionDomain, typePool, compound2);
                    Listener listener2 = this.listener;
                    if (cls2 == null) {
                        z = false;
                    }
                    listener2.onComplete(replace, classLoader2, javaModule2, z);
                    return doTransform;
                } catch (Throwable th) {
                    try {
                        this.listener.onError(replace, classLoader, javaModule, cls2 != null, th);
                        byte[] access$1300 = Default.NO_TRANSFORMATION;
                        Listener listener3 = this.listener;
                        if (cls2 == null) {
                            z = false;
                        }
                        listener3.onComplete(replace, classLoader2, javaModule2, z);
                        return access$1300;
                    } catch (Throwable th2) {
                        Listener listener4 = this.listener;
                        if (cls2 == null) {
                            z = false;
                        }
                        listener4.onComplete(replace, classLoader2, javaModule2, z);
                        throw th2;
                    }
                }
            }

            private byte[] doTransform(JavaModule javaModule, ClassLoader classLoader, String str, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool, ClassFileLocator classFileLocator) {
                return resolve(javaModule, classLoader, str, cls, z, protectionDomain, typePool).apply(this.initializationStrategy, classFileLocator, this.typeStrategy, this.byteBuddy, this.nativeMethodStrategy, this.bootstrapInjectionStrategy, this.accessControlContext, this.listener);
            }

            private Transformation.Resolution resolve(JavaModule javaModule, ClassLoader classLoader, String str, Class<?> cls, boolean z, ProtectionDomain protectionDomain, TypePool typePool) {
                TypeDescription apply = this.descriptionStrategy.apply(str, cls, typePool, this.circularityLock, classLoader, javaModule);
                if (this.ignoredTypeMatcher.matches(apply, classLoader, javaModule, cls, protectionDomain)) {
                    JavaModule javaModule2 = javaModule;
                    return new Transformation.Resolution.Unresolved(apply, classLoader, javaModule, z);
                }
                JavaModule javaModule3 = javaModule;
                ClassLoader classLoader2 = classLoader;
                boolean z2 = z;
                return this.transformation.resolve(apply, classLoader, javaModule, cls, z, protectionDomain, typePool);
            }

            public synchronized boolean reset(Instrumentation instrumentation, RedefinitionStrategy redefinitionStrategy, RedefinitionStrategy.DiscoveryStrategy discoveryStrategy, RedefinitionStrategy.BatchAllocator batchAllocator, RedefinitionStrategy.Listener listener2) {
                Instrumentation instrumentation2 = instrumentation;
                synchronized (this) {
                    if (!instrumentation2.removeTransformer(this)) {
                        return false;
                    }
                    redefinitionStrategy.apply(instrumentation, Listener.NoOp.INSTANCE, CircularityLock.Inactive.INSTANCE, this.poolStrategy, this.locationStrategy, discoveryStrategy, batchAllocator, listener2, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.transformation, this.ignoredTypeMatcher);
                    this.installationListener.onReset(instrumentation2, this);
                    return true;
                }
            }

            protected interface Factory {
                ResettableClassFileTransformer make(ByteBuddy byteBuddy, Listener listener, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, ClassFileBufferStrategy classFileBufferStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation, CircularityLock circularityLock);

                public enum CreationAction implements PrivilegedAction<Factory> {
                    INSTANCE;

                    public Factory run() {
                        try {
                            DynamicType.Builder<ExecutingTransformer> subclass = new ByteBuddy().with(TypeValidation.DISABLED).subclass(ExecutingTransformer.class);
                            return new ForJava9CapableVm(subclass.name(ExecutingTransformer.class.getName() + "$ByteBuddy$ModuleSupport").method(ElementMatchers.named("transform").and(ElementMatchers.takesArgument(0, (Class<?>) JavaType.MODULE.load()))).intercept(MethodCall.invoke(ExecutingTransformer.class.getDeclaredMethod("transform", new Class[]{Object.class, ClassLoader.class, String.class, Class.class, ProtectionDomain.class, byte[].class})).onSuper().withAllArguments()).make().load(ExecutingTransformer.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER_PERSISTENT.with(ExecutingTransformer.class.getProtectionDomain())).getLoaded().getDeclaredConstructor(new Class[]{ByteBuddy.class, Listener.class, PoolStrategy.class, TypeStrategy.class, LocationStrategy.class, NativeMethodStrategy.class, InitializationStrategy.class, BootstrapInjectionStrategy.class, LambdaInstrumentationStrategy.class, DescriptionStrategy.class, FallbackStrategy.class, ClassFileBufferStrategy.class, InstallationListener.class, RawMatcher.class, Transformation.class, CircularityLock.class}));
                        } catch (Exception unused) {
                            return ForLegacyVm.INSTANCE;
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForJava9CapableVm implements Factory {
                    private final Constructor<? extends ResettableClassFileTransformer> executingTransformer;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.executingTransformer.equals(((ForJava9CapableVm) obj).executingTransformer);
                    }

                    public int hashCode() {
                        return 527 + this.executingTransformer.hashCode();
                    }

                    protected ForJava9CapableVm(Constructor<? extends ResettableClassFileTransformer> constructor) {
                        this.executingTransformer = constructor;
                    }

                    public ResettableClassFileTransformer make(ByteBuddy byteBuddy, Listener listener, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, ClassFileBufferStrategy classFileBufferStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation, CircularityLock circularityLock) {
                        try {
                            return (ResettableClassFileTransformer) this.executingTransformer.newInstance(new Object[]{byteBuddy, listener, poolStrategy, typeStrategy, locationStrategy, nativeMethodStrategy, initializationStrategy, bootstrapInjectionStrategy, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, classFileBufferStrategy, installationListener, rawMatcher, transformation, circularityLock});
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access " + this.executingTransformer, e);
                        } catch (InstantiationException e2) {
                            throw new IllegalStateException("Cannot instantiate " + this.executingTransformer.getDeclaringClass(), e2);
                        } catch (InvocationTargetException e3) {
                            throw new IllegalStateException("Cannot invoke " + this.executingTransformer, e3.getCause());
                        }
                    }
                }

                public enum ForLegacyVm implements Factory {
                    INSTANCE;

                    public ResettableClassFileTransformer make(ByteBuddy byteBuddy, Listener listener, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, ClassFileBufferStrategy classFileBufferStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation, CircularityLock circularityLock) {
                        return new ExecutingTransformer(byteBuddy, listener, poolStrategy, typeStrategy, locationStrategy, nativeMethodStrategy, initializationStrategy, bootstrapInjectionStrategy, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, classFileBufferStrategy, installationListener, rawMatcher, transformation, circularityLock);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class LegacyVmDispatcher implements PrivilegedAction<byte[]> {
                private final byte[] binaryRepresentation;
                private final Class<?> classBeingRedefined;
                private final ClassLoader classLoader;
                private final String internalTypeName;
                private final ProtectionDomain protectionDomain;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    LegacyVmDispatcher legacyVmDispatcher = (LegacyVmDispatcher) obj;
                    return this.internalTypeName.equals(legacyVmDispatcher.internalTypeName) && this.classLoader.equals(legacyVmDispatcher.classLoader) && this.classBeingRedefined.equals(legacyVmDispatcher.classBeingRedefined) && this.protectionDomain.equals(legacyVmDispatcher.protectionDomain) && Arrays.equals(this.binaryRepresentation, legacyVmDispatcher.binaryRepresentation) && ExecutingTransformer.this.equals(ExecutingTransformer.this);
                }

                public int hashCode() {
                    return ((((((((((527 + this.classLoader.hashCode()) * 31) + this.internalTypeName.hashCode()) * 31) + this.classBeingRedefined.hashCode()) * 31) + this.protectionDomain.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + ExecutingTransformer.this.hashCode();
                }

                protected LegacyVmDispatcher(ClassLoader classLoader2, String str, Class<?> cls, ProtectionDomain protectionDomain2, byte[] bArr) {
                    this.classLoader = classLoader2;
                    this.internalTypeName = str;
                    this.classBeingRedefined = cls;
                    this.protectionDomain = protectionDomain2;
                    this.binaryRepresentation = bArr;
                }

                public byte[] run() {
                    return ExecutingTransformer.this.transform(JavaModule.UNSUPPORTED, this.classLoader, this.internalTypeName, this.classBeingRedefined, this.protectionDomain, this.binaryRepresentation);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class Java9CapableVmDispatcher implements PrivilegedAction<byte[]> {
                private final byte[] binaryRepresentation;
                private final Class<?> classBeingRedefined;
                private final ClassLoader classLoader;
                private final String internalTypeName;
                private final ProtectionDomain protectionDomain;
                private final Object rawModule;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Java9CapableVmDispatcher java9CapableVmDispatcher = (Java9CapableVmDispatcher) obj;
                    return this.internalTypeName.equals(java9CapableVmDispatcher.internalTypeName) && this.rawModule.equals(java9CapableVmDispatcher.rawModule) && this.classLoader.equals(java9CapableVmDispatcher.classLoader) && this.classBeingRedefined.equals(java9CapableVmDispatcher.classBeingRedefined) && this.protectionDomain.equals(java9CapableVmDispatcher.protectionDomain) && Arrays.equals(this.binaryRepresentation, java9CapableVmDispatcher.binaryRepresentation) && ExecutingTransformer.this.equals(ExecutingTransformer.this);
                }

                public int hashCode() {
                    return ((((((((((((527 + this.rawModule.hashCode()) * 31) + this.classLoader.hashCode()) * 31) + this.internalTypeName.hashCode()) * 31) + this.classBeingRedefined.hashCode()) * 31) + this.protectionDomain.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + ExecutingTransformer.this.hashCode();
                }

                protected Java9CapableVmDispatcher(Object obj, ClassLoader classLoader2, String str, Class<?> cls, ProtectionDomain protectionDomain2, byte[] bArr) {
                    this.rawModule = obj;
                    this.classLoader = classLoader2;
                    this.internalTypeName = str;
                    this.classBeingRedefined = cls;
                    this.protectionDomain = protectionDomain2;
                    this.binaryRepresentation = bArr;
                }

                public byte[] run() {
                    return ExecutingTransformer.this.transform(JavaModule.of(this.rawModule), this.classLoader, this.internalTypeName, this.classBeingRedefined, this.protectionDomain, this.binaryRepresentation);
                }
            }
        }

        protected abstract class Delegator<T extends Matchable<T>> extends Matchable.AbstractBase<T> implements AgentBuilder {
            /* access modifiers changed from: protected */
            public abstract AgentBuilder materialize();

            protected Delegator() {
            }

            public AgentBuilder with(ByteBuddy byteBuddy) {
                return materialize().with(byteBuddy);
            }

            public AgentBuilder with(Listener listener) {
                return materialize().with(listener);
            }

            public AgentBuilder with(CircularityLock circularityLock) {
                return materialize().with(circularityLock);
            }

            public AgentBuilder with(TypeStrategy typeStrategy) {
                return materialize().with(typeStrategy);
            }

            public AgentBuilder with(PoolStrategy poolStrategy) {
                return materialize().with(poolStrategy);
            }

            public AgentBuilder with(LocationStrategy locationStrategy) {
                return materialize().with(locationStrategy);
            }

            public AgentBuilder with(InitializationStrategy initializationStrategy) {
                return materialize().with(initializationStrategy);
            }

            public RedefinitionListenable.WithoutBatchStrategy with(RedefinitionStrategy redefinitionStrategy) {
                return materialize().with(redefinitionStrategy);
            }

            public AgentBuilder with(LambdaInstrumentationStrategy lambdaInstrumentationStrategy) {
                return materialize().with(lambdaInstrumentationStrategy);
            }

            public AgentBuilder with(DescriptionStrategy descriptionStrategy) {
                return materialize().with(descriptionStrategy);
            }

            public AgentBuilder with(FallbackStrategy fallbackStrategy) {
                return materialize().with(fallbackStrategy);
            }

            public AgentBuilder with(ClassFileBufferStrategy classFileBufferStrategy) {
                return materialize().with(classFileBufferStrategy);
            }

            public AgentBuilder with(InstallationListener installationListener) {
                return materialize().with(installationListener);
            }

            public AgentBuilder enableBootstrapInjection(Instrumentation instrumentation, File file) {
                return materialize().enableBootstrapInjection(instrumentation, file);
            }

            public AgentBuilder enableUnsafeBootstrapInjection() {
                return materialize().enableUnsafeBootstrapInjection();
            }

            public AgentBuilder disableBootstrapInjection() {
                return materialize().disableBootstrapInjection();
            }

            public AgentBuilder enableNativeMethodPrefix(String str) {
                return materialize().enableNativeMethodPrefix(str);
            }

            public AgentBuilder disableNativeMethodPrefix() {
                return materialize().disableNativeMethodPrefix();
            }

            public AgentBuilder disableClassFormatChanges() {
                return materialize().disableClassFormatChanges();
            }

            public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Class<?>... clsArr) {
                return materialize().assureReadEdgeTo(instrumentation, clsArr);
            }

            public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
                return materialize().assureReadEdgeTo(instrumentation, javaModuleArr);
            }

            public AgentBuilder assureReadEdgeTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
                return materialize().assureReadEdgeTo(instrumentation, collection);
            }

            public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Class<?>... clsArr) {
                return materialize().assureReadEdgeFromAndTo(instrumentation, clsArr);
            }

            public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, JavaModule... javaModuleArr) {
                return materialize().assureReadEdgeFromAndTo(instrumentation, javaModuleArr);
            }

            public AgentBuilder assureReadEdgeFromAndTo(Instrumentation instrumentation, Collection<? extends JavaModule> collection) {
                return materialize().assureReadEdgeFromAndTo(instrumentation, collection);
            }

            public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher) {
                return materialize().type(elementMatcher);
            }

            public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return materialize().type(elementMatcher, elementMatcher2);
            }

            public Identified.Narrowable type(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return materialize().type(elementMatcher, elementMatcher2, elementMatcher3);
            }

            public Identified.Narrowable type(RawMatcher rawMatcher) {
                return materialize().type(rawMatcher);
            }

            public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher) {
                return materialize().ignore(elementMatcher);
            }

            public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2) {
                return materialize().ignore(elementMatcher, elementMatcher2);
            }

            public Ignored ignore(ElementMatcher<? super TypeDescription> elementMatcher, ElementMatcher<? super ClassLoader> elementMatcher2, ElementMatcher<? super JavaModule> elementMatcher3) {
                return materialize().ignore(elementMatcher, elementMatcher2, elementMatcher3);
            }

            public Ignored ignore(RawMatcher rawMatcher) {
                return materialize().ignore(rawMatcher);
            }

            public ClassFileTransformer makeRaw() {
                return materialize().makeRaw();
            }

            public ResettableClassFileTransformer installOn(Instrumentation instrumentation) {
                return materialize().installOn(instrumentation);
            }

            public ResettableClassFileTransformer installOn(Instrumentation instrumentation, TransformerDecorator transformerDecorator) {
                return materialize().installOn(instrumentation, transformerDecorator);
            }

            public ResettableClassFileTransformer installOnByteBuddyAgent() {
                return materialize().installOnByteBuddyAgent();
            }

            public ResettableClassFileTransformer installOnByteBuddyAgent(TransformerDecorator transformerDecorator) {
                return materialize().installOnByteBuddyAgent(transformerDecorator);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Ignoring extends Delegator<Ignored> implements Ignored {
            private final RawMatcher rawMatcher;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Ignoring ignoring = (Ignoring) obj;
                return this.rawMatcher.equals(ignoring.rawMatcher) && Default.this.equals(Default.this);
            }

            public int hashCode() {
                return ((527 + this.rawMatcher.hashCode()) * 31) + Default.this.hashCode();
            }

            protected Ignoring(RawMatcher rawMatcher2) {
                super();
                this.rawMatcher = rawMatcher2;
            }

            /* access modifiers changed from: protected */
            public AgentBuilder materialize() {
                return new Default(Default.this.byteBuddy, Default.this.listener, Default.this.circularityLock, Default.this.poolStrategy, Default.this.typeStrategy, Default.this.locationStrategy, Default.this.nativeMethodStrategy, Default.this.initializationStrategy, Default.this.redefinitionStrategy, Default.this.redefinitionDiscoveryStrategy, Default.this.redefinitionBatchAllocator, Default.this.redefinitionListener, Default.this.redefinitionResubmissionStrategy, Default.this.bootstrapInjectionStrategy, Default.this.lambdaInstrumentationStrategy, Default.this.descriptionStrategy, Default.this.fallbackStrategy, Default.this.classFileBufferStrategy, Default.this.installationListener, this.rawMatcher, Default.this.transformation);
            }

            public Ignored and(RawMatcher rawMatcher2) {
                return new Ignoring(new RawMatcher.Conjunction(this.rawMatcher, rawMatcher2));
            }

            public Ignored or(RawMatcher rawMatcher2) {
                return new Ignoring(new RawMatcher.Disjunction(this.rawMatcher, rawMatcher2));
            }
        }

        protected static class Redefining extends Default implements RedefinitionListenable.WithoutBatchStrategy {
            public /* bridge */ /* synthetic */ ClassFileTransformer makeRaw() {
                return super.makeRaw();
            }

            protected Redefining(ByteBuddy byteBuddy, Listener listener, CircularityLock circularityLock, PoolStrategy poolStrategy, TypeStrategy typeStrategy, LocationStrategy locationStrategy, NativeMethodStrategy nativeMethodStrategy, InitializationStrategy initializationStrategy, RedefinitionStrategy redefinitionStrategy, RedefinitionStrategy.DiscoveryStrategy discoveryStrategy, RedefinitionStrategy.BatchAllocator batchAllocator, RedefinitionStrategy.Listener listener2, RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy, BootstrapInjectionStrategy bootstrapInjectionStrategy, LambdaInstrumentationStrategy lambdaInstrumentationStrategy, DescriptionStrategy descriptionStrategy, FallbackStrategy fallbackStrategy, ClassFileBufferStrategy classFileBufferStrategy, InstallationListener installationListener, RawMatcher rawMatcher, Transformation transformation) {
                super(byteBuddy, listener, circularityLock, poolStrategy, typeStrategy, locationStrategy, nativeMethodStrategy, initializationStrategy, redefinitionStrategy, discoveryStrategy, batchAllocator, listener2, resubmissionStrategy, bootstrapInjectionStrategy, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, classFileBufferStrategy, installationListener, rawMatcher, transformation);
            }

            public RedefinitionListenable.WithImplicitDiscoveryStrategy with(RedefinitionStrategy.BatchAllocator batchAllocator) {
                if (this.redefinitionStrategy.isEnabled()) {
                    return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, batchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
                }
                throw new IllegalStateException("Cannot set redefinition batch allocator when redefinition is disabled");
            }

            public RedefinitionListenable redefineOnly(Class<?>... clsArr) {
                return with((RedefinitionStrategy.DiscoveryStrategy) new RedefinitionStrategy.DiscoveryStrategy.Explicit(clsArr));
            }

            public RedefinitionListenable with(RedefinitionStrategy.DiscoveryStrategy discoveryStrategy) {
                if (this.redefinitionStrategy.isEnabled()) {
                    return new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, discoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
                }
                throw new IllegalStateException("Cannot set redefinition discovery strategy when redefinition is disabled");
            }

            public RedefinitionListenable with(RedefinitionStrategy.Listener listener) {
                if (this.redefinitionStrategy.isEnabled()) {
                    ByteBuddy byteBuddy = this.byteBuddy;
                    Listener listener2 = this.listener;
                    CircularityLock circularityLock = this.circularityLock;
                    PoolStrategy poolStrategy = this.poolStrategy;
                    TypeStrategy typeStrategy = this.typeStrategy;
                    LocationStrategy locationStrategy = this.locationStrategy;
                    NativeMethodStrategy nativeMethodStrategy = this.nativeMethodStrategy;
                    InitializationStrategy initializationStrategy = this.initializationStrategy;
                    RedefinitionStrategy redefinitionStrategy = this.redefinitionStrategy;
                    RedefinitionStrategy.DiscoveryStrategy discoveryStrategy = this.redefinitionDiscoveryStrategy;
                    RedefinitionStrategy.BatchAllocator batchAllocator = this.redefinitionBatchAllocator;
                    RedefinitionStrategy.Listener.Compound compound = r15;
                    Redefining redefining = r2;
                    RedefinitionStrategy.Listener.Compound compound2 = new RedefinitionStrategy.Listener.Compound(this.redefinitionListener, listener);
                    Redefining redefining2 = new Redefining(byteBuddy, listener2, circularityLock, poolStrategy, typeStrategy, locationStrategy, nativeMethodStrategy, initializationStrategy, redefinitionStrategy, discoveryStrategy, batchAllocator, compound, this.redefinitionResubmissionStrategy, this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
                    return redefining;
                }
                throw new IllegalStateException("Cannot set redefinition listener when redefinition is disabled");
            }

            public AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler) {
                return withResubmission(resubmissionScheduler, ElementMatchers.any());
            }

            public AgentBuilder withResubmission(RedefinitionStrategy.ResubmissionScheduler resubmissionScheduler, ElementMatcher<? super Throwable> elementMatcher) {
                if (this.redefinitionStrategy.isEnabled()) {
                    Redefining redefining = r2;
                    Redefining redefining2 = new Redefining(this.byteBuddy, this.listener, this.circularityLock, this.poolStrategy, this.typeStrategy, this.locationStrategy, this.nativeMethodStrategy, this.initializationStrategy, this.redefinitionStrategy, this.redefinitionDiscoveryStrategy, this.redefinitionBatchAllocator, this.redefinitionListener, new RedefinitionStrategy.ResubmissionStrategy.Enabled(resubmissionScheduler, elementMatcher), this.bootstrapInjectionStrategy, this.lambdaInstrumentationStrategy, this.descriptionStrategy, this.fallbackStrategy, this.classFileBufferStrategy, this.installationListener, this.ignoredTypeMatcher, this.transformation);
                    return redefining;
                }
                throw new IllegalStateException("Cannot enable redefinition resubmission when redefinition is disabled");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Transforming extends Delegator<Identified.Narrowable> implements Identified.Extendable, Identified.Narrowable {
            private final boolean decorator;
            private final RawMatcher rawMatcher;
            private final Transformer transformer;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Transforming transforming = (Transforming) obj;
                return this.decorator == transforming.decorator && this.rawMatcher.equals(transforming.rawMatcher) && this.transformer.equals(transforming.transformer) && Default.this.equals(Default.this);
            }

            public int hashCode() {
                return ((((((527 + this.rawMatcher.hashCode()) * 31) + this.transformer.hashCode()) * 31) + (this.decorator ? 1 : 0)) * 31) + Default.this.hashCode();
            }

            protected Transforming(RawMatcher rawMatcher2, Transformer transformer2, boolean z) {
                super();
                this.rawMatcher = rawMatcher2;
                this.transformer = transformer2;
                this.decorator = z;
            }

            /* access modifiers changed from: protected */
            public AgentBuilder materialize() {
                ByteBuddy byteBuddy = Default.this.byteBuddy;
                Listener listener = Default.this.listener;
                CircularityLock circularityLock = Default.this.circularityLock;
                PoolStrategy poolStrategy = Default.this.poolStrategy;
                TypeStrategy typeStrategy = Default.this.typeStrategy;
                LocationStrategy locationStrategy = Default.this.locationStrategy;
                NativeMethodStrategy nativeMethodStrategy = Default.this.nativeMethodStrategy;
                InitializationStrategy initializationStrategy = Default.this.initializationStrategy;
                RedefinitionStrategy redefinitionStrategy = Default.this.redefinitionStrategy;
                RedefinitionStrategy.DiscoveryStrategy discoveryStrategy = Default.this.redefinitionDiscoveryStrategy;
                RedefinitionStrategy.BatchAllocator batchAllocator = Default.this.redefinitionBatchAllocator;
                RedefinitionStrategy.Listener listener2 = Default.this.redefinitionListener;
                RedefinitionStrategy.ResubmissionStrategy resubmissionStrategy = Default.this.redefinitionResubmissionStrategy;
                BootstrapInjectionStrategy bootstrapInjectionStrategy = Default.this.bootstrapInjectionStrategy;
                LambdaInstrumentationStrategy lambdaInstrumentationStrategy = Default.this.lambdaInstrumentationStrategy;
                DescriptionStrategy descriptionStrategy = Default.this.descriptionStrategy;
                FallbackStrategy fallbackStrategy = Default.this.fallbackStrategy;
                ClassFileBufferStrategy classFileBufferStrategy = Default.this.classFileBufferStrategy;
                InstallationListener installationListener = Default.this.installationListener;
                RawMatcher rawMatcher2 = Default.this.ignoredTypeMatcher;
                Transformation.Compound compound = r1;
                ByteBuddy byteBuddy2 = byteBuddy;
                Listener listener3 = listener;
                CircularityLock circularityLock2 = circularityLock;
                PoolStrategy poolStrategy2 = poolStrategy;
                TypeStrategy typeStrategy2 = typeStrategy;
                Transformation.Compound compound2 = new Transformation.Compound(Default.this.transformation, new Transformation.Simple(this.rawMatcher, this.transformer, this.decorator));
                return new Default(byteBuddy2, listener3, circularityLock2, poolStrategy2, typeStrategy2, locationStrategy, nativeMethodStrategy, initializationStrategy, redefinitionStrategy, discoveryStrategy, batchAllocator, listener2, resubmissionStrategy, bootstrapInjectionStrategy, lambdaInstrumentationStrategy, descriptionStrategy, fallbackStrategy, classFileBufferStrategy, installationListener, rawMatcher2, compound);
            }

            public Identified.Extendable transform(Transformer transformer2) {
                return new Transforming(this.rawMatcher, new Transformer.Compound(this.transformer, transformer2), this.decorator);
            }

            public AgentBuilder asTerminalTransformation() {
                return new Transforming(this.rawMatcher, this.transformer, false);
            }

            public Identified.Narrowable and(RawMatcher rawMatcher2) {
                return new Transforming(new RawMatcher.Conjunction(this.rawMatcher, rawMatcher2), this.transformer, this.decorator);
            }

            public Identified.Narrowable or(RawMatcher rawMatcher2) {
                return new Transforming(new RawMatcher.Disjunction(this.rawMatcher, rawMatcher2), this.transformer, this.decorator);
            }
        }
    }

    /* renamed from: net.bytebuddy.agent.builder.AgentBuilder$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                net.bytebuddy.agent.builder.AgentBuilder$Default$Transformation$Resolution$Sort[] r0 = net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution.Sort.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort = r0
                net.bytebuddy.agent.builder.AgentBuilder$Default$Transformation$Resolution$Sort r1 = net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution.Sort.TERMINAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort     // Catch:{ NoSuchFieldError -> 0x001d }
                net.bytebuddy.agent.builder.AgentBuilder$Default$Transformation$Resolution$Sort r1 = net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution.Sort.DECORATOR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$net$bytebuddy$agent$builder$AgentBuilder$Default$Transformation$Resolution$Sort     // Catch:{ NoSuchFieldError -> 0x0028 }
                net.bytebuddy.agent.builder.AgentBuilder$Default$Transformation$Resolution$Sort r1 = net.bytebuddy.agent.builder.AgentBuilder.Default.Transformation.Resolution.Sort.UNDEFINED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.agent.builder.AgentBuilder.AnonymousClass1.<clinit>():void");
        }
    }
}

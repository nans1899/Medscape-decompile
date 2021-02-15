package org.mockito.internal.creation.bytebuddy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.SynchronizationState;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.loading.MultipleParentClassLoader;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.mockito.codegen.InjectionBase;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.creation.bytebuddy.ByteBuddyCrossClassLoaderSerializationSupport;
import org.mockito.internal.creation.bytebuddy.MockMethodInterceptor;
import org.mockito.internal.util.StringUtil;
import org.mockito.mock.SerializableMode;

class SubclassBytecodeGenerator implements BytecodeGenerator {
    private static final String CODEGEN_PACKAGE = "org.mockito.codegen.";
    private final ByteBuddy byteBuddy;
    private final Implementation dispatcher;
    private final Implementation equals;
    private final ModuleHandler handler;
    private final Implementation hashCode;
    private final SubclassLoader loader;
    private final ElementMatcher<? super MethodDescription> matcher;
    private final Random random;
    private final Implementation readReplace;
    private final Implementation writeReplace;

    public SubclassBytecodeGenerator() {
        this(new SubclassInjectionLoader());
    }

    public SubclassBytecodeGenerator(SubclassLoader subclassLoader) {
        this(subclassLoader, (Implementation) null, ElementMatchers.any());
    }

    public SubclassBytecodeGenerator(Implementation implementation, ElementMatcher<? super MethodDescription> elementMatcher) {
        this(new SubclassInjectionLoader(), implementation, elementMatcher);
    }

    protected SubclassBytecodeGenerator(SubclassLoader subclassLoader, Implementation implementation, ElementMatcher<? super MethodDescription> elementMatcher) {
        this.dispatcher = MethodDelegation.to((Class<?>) MockMethodInterceptor.DispatcherDefaultingToRealMethod.class);
        this.hashCode = MethodDelegation.to((Class<?>) MockMethodInterceptor.ForHashCode.class);
        this.equals = MethodDelegation.to((Class<?>) MockMethodInterceptor.ForEquals.class);
        this.writeReplace = MethodDelegation.to((Class<?>) MockMethodInterceptor.ForWriteReplace.class);
        this.loader = subclassLoader;
        this.readReplace = implementation;
        this.matcher = elementMatcher;
        this.byteBuddy = new ByteBuddy().with(TypeValidation.DISABLED);
        Random random2 = new Random();
        this.random = random2;
        this.handler = ModuleHandler.make(this.byteBuddy, subclassLoader, random2);
    }

    public <T> Class<? extends T> mockClass(MockFeatures<T> mockFeatures) {
        String str;
        Annotation[] annotationArr;
        MethodAttributeAppender.Factory factory;
        ClassLoader build = new MultipleParentClassLoader.Builder().appendMostSpecific((Collection<? extends Class<?>>) getAllTypes(mockFeatures.mockedType)).appendMostSpecific((Collection<? extends Class<?>>) mockFeatures.interfaces).appendMostSpecific(Thread.currentThread().getContextClassLoader()).appendMostSpecific((Class<?>[]) new Class[]{MockAccess.class}).build();
        boolean z = build == mockFeatures.mockedType.getClassLoader() && mockFeatures.serializableMode != SerializableMode.ACROSS_CLASSLOADERS && !isComingFromJDK(mockFeatures.mockedType) && (this.loader.isDisrespectingOpenness() || this.handler.isOpened(mockFeatures.mockedType, MockAccess.class));
        if (z || ((this.loader instanceof MultipleParentClassLoader) && !isComingFromJDK(mockFeatures.mockedType))) {
            str = mockFeatures.mockedType.getName();
        } else {
            str = InjectionBase.class.getPackage().getName() + "." + mockFeatures.mockedType.getSimpleName();
        }
        String format = String.format("%s$%s$%d", new Object[]{str, "MockitoMock", Integer.valueOf(Math.abs(this.random.nextInt()))});
        if (z) {
            this.handler.adjustModuleGraph(mockFeatures.mockedType, MockAccess.class, false, true);
            for (Class next : mockFeatures.interfaces) {
                this.handler.adjustModuleGraph(next, mockFeatures.mockedType, true, false);
                this.handler.adjustModuleGraph(mockFeatures.mockedType, next, false, true);
            }
        } else {
            boolean isExported = this.handler.isExported(mockFeatures.mockedType);
            Iterator<Class<?>> it = mockFeatures.interfaces.iterator();
            while (isExported && it.hasNext()) {
                isExported = this.handler.isExported(it.next());
            }
            if (isExported) {
                assertVisibility(mockFeatures.mockedType);
                for (Class<?> assertVisibility : mockFeatures.interfaces) {
                    assertVisibility(assertVisibility);
                }
            } else {
                Class<?> injectionBase = this.handler.injectionBase(build, str);
                assertVisibility(mockFeatures.mockedType);
                this.handler.adjustModuleGraph(mockFeatures.mockedType, injectionBase, true, false);
                for (Class next2 : mockFeatures.interfaces) {
                    assertVisibility(next2);
                    this.handler.adjustModuleGraph(next2, injectionBase, true, false);
                }
            }
        }
        DynamicType.Builder ignoreAlso = this.byteBuddy.subclass(mockFeatures.mockedType).name(format).ignoreAlso((ElementMatcher<? super MethodDescription>) isGroovyMethod());
        if (mockFeatures.stripAnnotations) {
            annotationArr = new Annotation[0];
        } else {
            annotationArr = mockFeatures.mockedType.getAnnotations();
        }
        DynamicType.Builder.MethodDefinition transform = ignoreAlso.annotateType(annotationArr).implement((List<? extends Type>) new ArrayList(mockFeatures.interfaces)).method(this.matcher).intercept(this.dispatcher).transform(Transformer.ForMethod.withModifiers(SynchronizationState.PLAIN));
        if (mockFeatures.stripAnnotations) {
            factory = MethodAttributeAppender.NoOp.INSTANCE;
        } else {
            factory = MethodAttributeAppender.ForInstrumentedMethod.INCLUDING_RECEIVER;
        }
        DynamicType.Builder intercept = transform.attribute(factory).method(ElementMatchers.isHashCode()).intercept(this.hashCode).method(ElementMatchers.isEquals()).intercept(this.equals).serialVersionUid(42).defineField("mockitoInterceptor", (Type) MockMethodInterceptor.class, Visibility.PRIVATE).implement(MockAccess.class).intercept(FieldAccessor.ofBeanProperty());
        if (mockFeatures.serializableMode == SerializableMode.ACROSS_CLASSLOADERS) {
            intercept = intercept.implement(ByteBuddyCrossClassLoaderSerializationSupport.CrossClassLoaderSerializableMock.class).intercept(this.writeReplace);
        }
        if (this.readReplace != null) {
            intercept = intercept.defineMethod("readObject", (Type) Void.TYPE, Visibility.PRIVATE).withParameters(ObjectInputStream.class).throwing(ClassNotFoundException.class, IOException.class).intercept(this.readReplace);
        }
        if (format.startsWith(CODEGEN_PACKAGE) || (build instanceof MultipleParentClassLoader)) {
            intercept = intercept.ignoreAlso((ElementMatcher<? super MethodDescription>) ElementMatchers.isPackagePrivate().or(ElementMatchers.returns((ElementMatcher<? super TypeDescription>) ElementMatchers.isPackagePrivate())).or(ElementMatchers.hasParameters(ElementMatchers.whereAny(ElementMatchers.hasType(ElementMatchers.isPackagePrivate())))));
        }
        return intercept.make().load(build, this.loader.resolveStrategy(mockFeatures.mockedType, build, z)).getLoaded();
    }

    private <T> Collection<Class<? super T>> getAllTypes(Class<T> cls) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(cls);
        for (Class<? super T> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            linkedList.add(cls2);
        }
        return linkedList;
    }

    private static ElementMatcher<MethodDescription> isGroovyMethod() {
        return ElementMatchers.isDeclaredBy((ElementMatcher<? super TypeDescription>) ElementMatchers.named("groovy.lang.GroovyObjectSupport"));
    }

    private boolean isComingFromJDK(Class<?> cls) {
        return (cls.getPackage() != null && "Java Runtime Environment".equalsIgnoreCase(cls.getPackage().getImplementationTitle())) || cls.getName().startsWith("java.") || cls.getName().startsWith("javax.");
    }

    private static void assertVisibility(Class<?> cls) {
        if (!Modifier.isPublic(cls.getModifiers())) {
            throw new MockitoException(StringUtil.join("Cannot create mock for " + cls, "", "The type is not public and its mock class is loaded by a different class loader.", "This can have multiple reasons:", " - You are mocking a class with additional interfaces of another class loader", " - Mockito is loaded by a different class loader than the mocked type (e.g. with OSGi)", " - The thread's context class loader is different than the mock's class loader"));
        }
    }
}

package net.bytebuddy.asm;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.bytecode.Addition;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.SerializedConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.visitor.ExceptionTableSensitiveMethodVisitor;
import net.bytebuddy.utility.visitor.LineNumberPrependingMethodVisitor;
import net.bytebuddy.utility.visitor.StackAwareMethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
public class Advice implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper, Implementation {
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape BACKUP_ARGUMENTS;
    private static final MethodDescription.InDefinedShape INLINE_ENTER;
    private static final MethodDescription.InDefinedShape INLINE_EXIT;
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape ON_THROWABLE;
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape PREPEND_LINE_NUMBER;
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape REPEAT_ON;
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape SKIP_ON;
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape SUPPRESS_ENTER;
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape SUPPRESS_EXIT;
    private static final ClassReader UNDEFINED = null;
    private final Assigner assigner;
    private final Implementation delegate;
    private final ExceptionHandler exceptionHandler;
    private final Dispatcher.Resolved.ForMethodEnter methodEnter;
    private final Dispatcher.Resolved.ForMethodExit methodExit;

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface AllArguments {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Argument {
        boolean optional() default false;

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;

        int value();
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Enter {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Exit {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface FieldValue {
        Class<?> declaringType() default void.class;

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;

        String value();
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Local {
        String value();
    }

    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OnMethodEnter {
        boolean inline() default true;

        boolean prependLineNumber() default true;

        Class<?> skipOn() default void.class;

        Class<? extends Throwable> suppress() default NoExceptionHandler.class;
    }

    @Documented
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface OnMethodExit {
        boolean backupArguments() default true;

        boolean inline() default true;

        Class<? extends Throwable> onThrowable() default NoExceptionHandler.class;

        Class<?> repeatOn() default void.class;

        Class<? extends Throwable> suppress() default NoExceptionHandler.class;
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Origin {
        public static final String DEFAULT = "";

        String value() default "";
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Return {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface StubValue {
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface This {
        boolean optional() default false;

        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.STATIC;
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Thrown {
        boolean readOnly() default true;

        Assigner.Typing typing() default Assigner.Typing.DYNAMIC;
    }

    @Documented
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Unused {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Advice advice = (Advice) obj;
        return this.methodEnter.equals(advice.methodEnter) && this.methodExit.equals(advice.methodExit) && this.assigner.equals(advice.assigner) && this.exceptionHandler.equals(advice.exceptionHandler) && this.delegate.equals(advice.delegate);
    }

    public int hashCode() {
        return ((((((((527 + this.methodEnter.hashCode()) * 31) + this.methodExit.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.exceptionHandler.hashCode()) * 31) + this.delegate.hashCode();
    }

    static {
        MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(OnMethodEnter.class).getDeclaredMethods();
        SKIP_ON = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("skipOn"))).getOnly();
        PREPEND_LINE_NUMBER = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("prependLineNumber"))).getOnly();
        INLINE_ENTER = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("inline"))).getOnly();
        SUPPRESS_ENTER = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("suppress"))).getOnly();
        MethodList<MethodDescription.InDefinedShape> declaredMethods2 = TypeDescription.ForLoadedType.of(OnMethodExit.class).getDeclaredMethods();
        REPEAT_ON = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods2.filter(ElementMatchers.named("repeatOn"))).getOnly();
        ON_THROWABLE = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods2.filter(ElementMatchers.named("onThrowable"))).getOnly();
        BACKUP_ARGUMENTS = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods2.filter(ElementMatchers.named("backupArguments"))).getOnly();
        INLINE_EXIT = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods2.filter(ElementMatchers.named("inline"))).getOnly();
        SUPPRESS_EXIT = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods2.filter(ElementMatchers.named("suppress"))).getOnly();
    }

    protected Advice(Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit) {
        this(forMethodEnter, forMethodExit, Assigner.DEFAULT, ExceptionHandler.Default.SUPPRESSING, SuperMethodCall.INSTANCE);
    }

    private Advice(Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, Assigner assigner2, ExceptionHandler exceptionHandler2, Implementation implementation) {
        this.methodEnter = forMethodEnter;
        this.methodExit = forMethodExit;
        this.assigner = assigner2;
        this.exceptionHandler = exceptionHandler2;
        this.delegate = implementation;
    }

    public static Advice to(Class<?> cls) {
        return to(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public static Advice to(Class<?> cls, ClassFileLocator classFileLocator) {
        return to(TypeDescription.ForLoadedType.of(cls), classFileLocator);
    }

    public static Advice to(TypeDescription typeDescription) {
        return to(typeDescription, (ClassFileLocator) ClassFileLocator.NoOp.INSTANCE);
    }

    public static Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return to(typeDescription, classFileLocator, (List<? extends OffsetMapping.Factory<?>>) Collections.emptyList());
    }

    protected static Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator, List<? extends OffsetMapping.Factory<?>> list) {
        ClassReader classReader;
        Dispatcher.Unresolved unresolved = Dispatcher.Inactive.INSTANCE;
        Dispatcher.Unresolved unresolved2 = Dispatcher.Inactive.INSTANCE;
        for (MethodDescription.InDefinedShape inDefinedShape : typeDescription.getDeclaredMethods()) {
            unresolved = locate(OnMethodEnter.class, INLINE_ENTER, unresolved, inDefinedShape);
            unresolved2 = locate(OnMethodExit.class, INLINE_EXIT, unresolved2, inDefinedShape);
        }
        if (unresolved.isAlive() || unresolved2.isAlive()) {
            try {
                if (!unresolved.isBinary()) {
                    if (!unresolved2.isBinary()) {
                        classReader = UNDEFINED;
                        return new Advice(unresolved.asMethodEnter(list, classReader, unresolved2), unresolved2.asMethodExit(list, classReader, unresolved));
                    }
                }
                classReader = OpenedClassReader.of(classFileLocator.locate(typeDescription.getName()).resolve());
                return new Advice(unresolved.asMethodEnter(list, classReader, unresolved2), unresolved2.asMethodExit(list, classReader, unresolved));
            } catch (IOException e) {
                throw new IllegalStateException("Error reading class file of " + typeDescription, e);
            }
        } else {
            throw new IllegalArgumentException("No advice defined by " + typeDescription);
        }
    }

    public static Advice to(Class<?> cls, Class<?> cls2) {
        ClassFileLocator classFileLocator;
        ClassLoader classLoader = cls.getClassLoader();
        ClassLoader classLoader2 = cls2.getClassLoader();
        if (classLoader == classLoader2) {
            classFileLocator = ClassFileLocator.ForClassLoader.of(classLoader);
        } else {
            classFileLocator = new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader), ClassFileLocator.ForClassLoader.of(classLoader2));
        }
        return to(cls, cls2, classFileLocator);
    }

    public static Advice to(Class<?> cls, Class<?> cls2, ClassFileLocator classFileLocator) {
        return to(TypeDescription.ForLoadedType.of(cls), TypeDescription.ForLoadedType.of(cls2), classFileLocator);
    }

    public static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2) {
        return to(typeDescription, typeDescription2, (ClassFileLocator) ClassFileLocator.NoOp.INSTANCE);
    }

    public static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator) {
        return to(typeDescription, typeDescription2, classFileLocator, Collections.emptyList());
    }

    protected static Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator, List<? extends OffsetMapping.Factory<?>> list) {
        Dispatcher.Unresolved unresolved = Dispatcher.Inactive.INSTANCE;
        Dispatcher.Unresolved unresolved2 = Dispatcher.Inactive.INSTANCE;
        for (MethodDescription.InDefinedShape locate : typeDescription.getDeclaredMethods()) {
            unresolved = locate(OnMethodEnter.class, INLINE_ENTER, unresolved, locate);
        }
        if (unresolved.isAlive()) {
            for (MethodDescription.InDefinedShape locate2 : typeDescription2.getDeclaredMethods()) {
                unresolved2 = locate(OnMethodExit.class, INLINE_EXIT, unresolved2, locate2);
            }
            if (unresolved2.isAlive()) {
                try {
                    return new Advice(unresolved.asMethodEnter(list, unresolved.isBinary() ? OpenedClassReader.of(classFileLocator.locate(typeDescription.getName()).resolve()) : UNDEFINED, unresolved2), unresolved2.asMethodExit(list, unresolved2.isBinary() ? OpenedClassReader.of(classFileLocator.locate(typeDescription2.getName()).resolve()) : UNDEFINED, unresolved));
                } catch (IOException e) {
                    throw new IllegalStateException("Error reading class file of " + typeDescription + " or " + typeDescription2, e);
                }
            } else {
                throw new IllegalArgumentException("No exit advice defined by " + typeDescription2);
            }
        } else {
            throw new IllegalArgumentException("No enter advice defined by " + typeDescription);
        }
    }

    private static Dispatcher.Unresolved locate(Class<? extends Annotation> cls, MethodDescription.InDefinedShape inDefinedShape, Dispatcher.Unresolved unresolved, MethodDescription.InDefinedShape inDefinedShape2) {
        AnnotationDescription.Loadable<? extends Annotation> ofType = inDefinedShape2.getDeclaredAnnotations().ofType(cls);
        if (ofType == null) {
            return unresolved;
        }
        if (unresolved.isAlive()) {
            throw new IllegalStateException("Duplicate advice for " + unresolved + " and " + inDefinedShape2);
        } else if (inDefinedShape2.isStatic()) {
            return ((Boolean) ofType.getValue(inDefinedShape).resolve(Boolean.class)).booleanValue() ? new Dispatcher.Inlining(inDefinedShape2) : new Dispatcher.Delegating(inDefinedShape2);
        } else {
            throw new IllegalStateException("Advice for " + inDefinedShape2 + " is not static");
        }
    }

    public static WithCustomMapping withCustomMapping() {
        return new WithCustomMapping();
    }

    public AsmVisitorWrapper.ForDeclaredMethods on(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new AsmVisitorWrapper.ForDeclaredMethods().invokable(elementMatcher, this);
    }

    public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
        return (methodDescription.isAbstract() || methodDescription.isNative()) ? methodVisitor : doWrap(typeDescription, methodDescription, methodVisitor, context, i, i2);
    }

    /* access modifiers changed from: protected */
    public MethodVisitor doWrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, int i, int i2) {
        TypeDescription typeDescription2 = typeDescription;
        MethodDescription methodDescription2 = methodDescription;
        LineNumberPrependingMethodVisitor lineNumberPrependingMethodVisitor = this.methodEnter.isPrependLineNumber() ? new LineNumberPrependingMethodVisitor(methodVisitor) : methodVisitor;
        if (!this.methodExit.isAlive()) {
            return new AdviceVisitor.WithoutExitAdvice(lineNumberPrependingMethodVisitor, context, this.assigner, this.exceptionHandler.resolve(methodDescription2, typeDescription), typeDescription, methodDescription, this.methodEnter, i, i2);
        } else if (this.methodExit.getThrowable().represents(NoExceptionHandler.class)) {
            return new AdviceVisitor.WithExitAdvice.WithoutExceptionHandling(lineNumberPrependingMethodVisitor, context, this.assigner, this.exceptionHandler.resolve(methodDescription2, typeDescription), typeDescription, methodDescription, this.methodEnter, this.methodExit, i, i2);
        } else if (!methodDescription.isConstructor()) {
            Assigner assigner2 = this.assigner;
            StackManipulation resolve = this.exceptionHandler.resolve(methodDescription2, typeDescription);
            Dispatcher.Resolved.ForMethodEnter forMethodEnter = this.methodEnter;
            Dispatcher.Resolved.ForMethodExit forMethodExit = this.methodExit;
            return new AdviceVisitor.WithExitAdvice.WithExceptionHandling(lineNumberPrependingMethodVisitor, context, assigner2, resolve, typeDescription, methodDescription, forMethodEnter, forMethodExit, i, i2, forMethodExit.getThrowable());
        } else {
            throw new IllegalStateException("Cannot catch exception during constructor call for " + methodDescription2);
        }
    }

    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return this.delegate.prepare(instrumentedType);
    }

    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(this, target, this.delegate.appender(target));
    }

    public Advice withAssigner(Assigner assigner2) {
        return new Advice(this.methodEnter, this.methodExit, assigner2, this.exceptionHandler, this.delegate);
    }

    public Advice withExceptionPrinting() {
        return withExceptionHandler((ExceptionHandler) ExceptionHandler.Default.PRINTING);
    }

    public Advice withExceptionHandler(StackManipulation stackManipulation) {
        return withExceptionHandler((ExceptionHandler) new ExceptionHandler.Simple(stackManipulation));
    }

    public Advice withExceptionHandler(ExceptionHandler exceptionHandler2) {
        return new Advice(this.methodEnter, this.methodExit, this.assigner, exceptionHandler2, this.delegate);
    }

    public Implementation wrap(Implementation implementation) {
        return new Advice(this.methodEnter, this.methodExit, this.assigner, this.exceptionHandler, implementation);
    }

    public interface OffsetMapping {

        public enum Sort {
            ENTER {
                public boolean isPremature(MethodDescription methodDescription) {
                    return methodDescription.isConstructor();
                }
            },
            EXIT {
                public boolean isPremature(MethodDescription methodDescription) {
                    return false;
                }
            };

            public abstract boolean isPremature(MethodDescription methodDescription);
        }

        Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort);

        public interface Target {
            StackManipulation resolveIncrement(int i);

            StackManipulation resolveRead();

            StackManipulation resolveWrite();

            public static abstract class AbstractReadOnlyAdapter implements Target {
                public StackManipulation resolveWrite() {
                    throw new IllegalStateException("Cannot write to read-only value");
                }

                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot write to read-only value");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class ForDefaultValue implements Target {
                protected final StackManipulation readAssignment;
                protected final TypeDefinition typeDefinition;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForDefaultValue forDefaultValue = (ForDefaultValue) obj;
                    return this.typeDefinition.equals(forDefaultValue.typeDefinition) && this.readAssignment.equals(forDefaultValue.readAssignment);
                }

                public int hashCode() {
                    return ((527 + this.typeDefinition.hashCode()) * 31) + this.readAssignment.hashCode();
                }

                protected ForDefaultValue(TypeDefinition typeDefinition2, StackManipulation stackManipulation) {
                    this.typeDefinition = typeDefinition2;
                    this.readAssignment = stackManipulation;
                }

                public StackManipulation resolveRead() {
                    return new StackManipulation.Compound(DefaultValue.of(this.typeDefinition), this.readAssignment);
                }

                public static class ReadOnly extends ForDefaultValue {
                    public ReadOnly(TypeDefinition typeDefinition) {
                        this(typeDefinition, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                        super(typeDefinition, stackManipulation);
                    }

                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only default value");
                    }

                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only default value");
                    }
                }

                public static class ReadWrite extends ForDefaultValue {
                    public ReadWrite(TypeDefinition typeDefinition) {
                        this(typeDefinition, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(TypeDefinition typeDefinition, StackManipulation stackManipulation) {
                        super(typeDefinition, stackManipulation);
                    }

                    public StackManipulation resolveWrite() {
                        return Removal.of(this.typeDefinition);
                    }

                    public StackManipulation resolveIncrement(int i) {
                        return StackManipulation.Trivial.INSTANCE;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class ForVariable implements Target {
                protected final int offset;
                protected final StackManipulation readAssignment;
                protected final TypeDefinition typeDefinition;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForVariable forVariable = (ForVariable) obj;
                    return this.offset == forVariable.offset && this.typeDefinition.equals(forVariable.typeDefinition) && this.readAssignment.equals(forVariable.readAssignment);
                }

                public int hashCode() {
                    return ((((527 + this.typeDefinition.hashCode()) * 31) + this.offset) * 31) + this.readAssignment.hashCode();
                }

                protected ForVariable(TypeDefinition typeDefinition2, int i, StackManipulation stackManipulation) {
                    this.typeDefinition = typeDefinition2;
                    this.offset = i;
                    this.readAssignment = stackManipulation;
                }

                public StackManipulation resolveRead() {
                    return new StackManipulation.Compound(MethodVariableAccess.of(this.typeDefinition).loadFrom(this.offset), this.readAssignment);
                }

                public static class ReadOnly extends ForVariable {
                    public ReadOnly(TypeDefinition typeDefinition, int i) {
                        this(typeDefinition, i, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation) {
                        super(typeDefinition, i, stackManipulation);
                    }

                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only parameter " + this.typeDefinition + " at " + this.offset);
                    }

                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only variable " + this.typeDefinition + " at " + this.offset);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ReadWrite extends ForVariable {
                    private final StackManipulation writeAssignment;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.writeAssignment.equals(((ReadWrite) obj).writeAssignment);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.writeAssignment.hashCode();
                    }

                    public ReadWrite(TypeDefinition typeDefinition, int i) {
                        this(typeDefinition, i, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(TypeDefinition typeDefinition, int i, StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        super(typeDefinition, i, stackManipulation);
                        this.writeAssignment = stackManipulation2;
                    }

                    public StackManipulation resolveWrite() {
                        return new StackManipulation.Compound(this.writeAssignment, MethodVariableAccess.of(this.typeDefinition).storeAt(this.offset));
                    }

                    public StackManipulation resolveIncrement(int i) {
                        if (this.typeDefinition.represents(Integer.TYPE)) {
                            return MethodVariableAccess.of(this.typeDefinition).increment(this.offset, i);
                        }
                        return new StackManipulation.Compound(resolveRead(), IntegerConstant.forValue(1), Addition.INTEGER, resolveWrite());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class ForArray implements Target {
                protected final TypeDescription.Generic target;
                protected final List<? extends StackManipulation> valueReads;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForArray forArray = (ForArray) obj;
                    return this.target.equals(forArray.target) && this.valueReads.equals(forArray.valueReads);
                }

                public int hashCode() {
                    return ((527 + this.target.hashCode()) * 31) + this.valueReads.hashCode();
                }

                protected ForArray(TypeDescription.Generic generic, List<? extends StackManipulation> list) {
                    this.target = generic;
                    this.valueReads = list;
                }

                public StackManipulation resolveRead() {
                    return ArrayFactory.forType(this.target).withValues(this.valueReads);
                }

                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot increment read-only array value");
                }

                public static class ReadOnly extends ForArray {
                    public ReadOnly(TypeDescription.Generic generic, List<? extends StackManipulation> list) {
                        super(generic, list);
                    }

                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only array value");
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ReadWrite extends ForArray {
                    private final List<? extends StackManipulation> valueWrites;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.valueWrites.equals(((ReadWrite) obj).valueWrites);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.valueWrites.hashCode();
                    }

                    public ReadWrite(TypeDescription.Generic generic, List<? extends StackManipulation> list, List<? extends StackManipulation> list2) {
                        super(generic, list);
                        this.valueWrites = list2;
                    }

                    public StackManipulation resolveWrite() {
                        return ArrayAccess.of(this.target).forEach(this.valueWrites);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class ForField implements Target {
                protected final FieldDescription fieldDescription;
                protected final StackManipulation readAssignment;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForField forField = (ForField) obj;
                    return this.fieldDescription.equals(forField.fieldDescription) && this.readAssignment.equals(forField.readAssignment);
                }

                public int hashCode() {
                    return ((527 + this.fieldDescription.hashCode()) * 31) + this.readAssignment.hashCode();
                }

                protected ForField(FieldDescription fieldDescription2, StackManipulation stackManipulation) {
                    this.fieldDescription = fieldDescription2;
                    this.readAssignment = stackManipulation;
                }

                public StackManipulation resolveRead() {
                    StackManipulation stackManipulation;
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    if (this.fieldDescription.isStatic()) {
                        stackManipulation = StackManipulation.Trivial.INSTANCE;
                    } else {
                        stackManipulation = MethodVariableAccess.loadThis();
                    }
                    stackManipulationArr[0] = stackManipulation;
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    stackManipulationArr[2] = this.readAssignment;
                    return new StackManipulation.Compound(stackManipulationArr);
                }

                public static class ReadOnly extends ForField {
                    public ReadOnly(FieldDescription fieldDescription) {
                        this(fieldDescription, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadOnly(FieldDescription fieldDescription, StackManipulation stackManipulation) {
                        super(fieldDescription, stackManipulation);
                    }

                    public StackManipulation resolveWrite() {
                        throw new IllegalStateException("Cannot write to read-only field value");
                    }

                    public StackManipulation resolveIncrement(int i) {
                        throw new IllegalStateException("Cannot write to read-only field value");
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ReadWrite extends ForField {
                    private final StackManipulation writeAssignment;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.writeAssignment.equals(((ReadWrite) obj).writeAssignment);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.writeAssignment.hashCode();
                    }

                    public ReadWrite(FieldDescription fieldDescription) {
                        this(fieldDescription, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE);
                    }

                    public ReadWrite(FieldDescription fieldDescription, StackManipulation stackManipulation, StackManipulation stackManipulation2) {
                        super(fieldDescription, stackManipulation);
                        this.writeAssignment = stackManipulation2;
                    }

                    public StackManipulation resolveWrite() {
                        StackManipulation stackManipulation;
                        if (this.fieldDescription.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = new StackManipulation.Compound(MethodVariableAccess.loadThis(), Duplication.SINGLE.flipOver(this.fieldDescription.getType()), Removal.SINGLE);
                        }
                        return new StackManipulation.Compound(this.writeAssignment, stackManipulation, FieldAccess.forField(this.fieldDescription).write());
                    }

                    public StackManipulation resolveIncrement(int i) {
                        return new StackManipulation.Compound(resolveRead(), IntegerConstant.forValue(i), Addition.INTEGER, resolveWrite());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForStackManipulation implements Target {
                private final StackManipulation stackManipulation;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.stackManipulation.equals(((ForStackManipulation) obj).stackManipulation);
                }

                public int hashCode() {
                    return 527 + this.stackManipulation.hashCode();
                }

                public ForStackManipulation(StackManipulation stackManipulation2) {
                    this.stackManipulation = stackManipulation2;
                }

                public static Target of(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForStackManipulation(MethodConstant.of(inDefinedShape));
                }

                public static Target of(TypeDescription typeDescription) {
                    return new ForStackManipulation(ClassConstant.of(typeDescription));
                }

                public static Target of(Object obj) {
                    if (obj == null) {
                        return new ForStackManipulation(NullConstant.INSTANCE);
                    }
                    if (obj instanceof Boolean) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Boolean) obj).booleanValue()));
                    }
                    if (obj instanceof Byte) {
                        return new ForStackManipulation(IntegerConstant.forValue((int) ((Byte) obj).byteValue()));
                    }
                    if (obj instanceof Short) {
                        return new ForStackManipulation(IntegerConstant.forValue((int) ((Short) obj).shortValue()));
                    }
                    if (obj instanceof Character) {
                        return new ForStackManipulation(IntegerConstant.forValue((int) ((Character) obj).charValue()));
                    }
                    if (obj instanceof Integer) {
                        return new ForStackManipulation(IntegerConstant.forValue(((Integer) obj).intValue()));
                    }
                    if (obj instanceof Long) {
                        return new ForStackManipulation(LongConstant.forValue(((Long) obj).longValue()));
                    }
                    if (obj instanceof Float) {
                        return new ForStackManipulation(FloatConstant.forValue(((Float) obj).floatValue()));
                    }
                    if (obj instanceof Double) {
                        return new ForStackManipulation(DoubleConstant.forValue(((Double) obj).doubleValue()));
                    }
                    if (obj instanceof String) {
                        return new ForStackManipulation(new TextConstant((String) obj));
                    }
                    throw new IllegalArgumentException("Not a constant value: " + obj);
                }

                public StackManipulation resolveRead() {
                    return this.stackManipulation;
                }

                public StackManipulation resolveWrite() {
                    throw new IllegalStateException("Cannot write to constant value: " + this.stackManipulation);
                }

                public StackManipulation resolveIncrement(int i) {
                    throw new IllegalStateException("Cannot write to constant value: " + this.stackManipulation);
                }
            }
        }

        public interface Factory<T extends Annotation> {
            Class<T> getAnnotationType();

            OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType);

            public enum AdviceType {
                DELEGATION(true),
                INLINING(false);
                
                private final boolean delegation;

                private AdviceType(boolean z) {
                    this.delegation = z;
                }

                public boolean isDelegation() {
                    return this.delegation;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Simple<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final OffsetMapping offsetMapping;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Simple simple = (Simple) obj;
                    return this.annotationType.equals(simple.annotationType) && this.offsetMapping.equals(simple.offsetMapping);
                }

                public int hashCode() {
                    return ((527 + this.annotationType.hashCode()) * 31) + this.offsetMapping.hashCode();
                }

                public Simple(Class<T> cls, OffsetMapping offsetMapping2) {
                    this.annotationType = cls;
                    this.offsetMapping = offsetMapping2;
                }

                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType) {
                    return this.offsetMapping;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Illegal<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((Illegal) obj).annotationType);
                }

                public int hashCode() {
                    return 527 + this.annotationType.hashCode();
                }

                public Illegal(Class<T> cls) {
                    this.annotationType = cls;
                }

                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, AdviceType adviceType) {
                    throw new IllegalStateException("Usage of " + this.annotationType + " is not allowed on " + inDefinedShape);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class ForArgument implements OffsetMapping {
            protected final boolean readOnly;
            protected final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForArgument forArgument = (ForArgument) obj;
                return this.readOnly == forArgument.readOnly && this.typing.equals(forArgument.typing) && this.target.equals(forArgument.target);
            }

            public int hashCode() {
                return ((((527 + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            /* access modifiers changed from: protected */
            public abstract ParameterDescription resolve(MethodDescription methodDescription);

            protected ForArgument(TypeDescription.Generic generic, boolean z, Assigner.Typing typing2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                ParameterDescription resolve = resolve(methodDescription);
                StackManipulation assign = assigner.assign(resolve.getType(), this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + resolve + " to " + this.target);
                } else if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(resolve.getType(), argumentHandler.argument(resolve.getOffset()), assign);
                } else {
                    StackManipulation assign2 = assigner.assign(this.target, resolve.getType(), this.typing);
                    if (assign2.isValid()) {
                        return new Target.ForVariable.ReadWrite(resolve.getType(), argumentHandler.argument(resolve.getOffset()), assign, assign2);
                    }
                    throw new IllegalStateException("Cannot assign " + resolve + " to " + this.target);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Unresolved extends ForArgument {
                private final int index;
                private final boolean optional;

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
                    Unresolved unresolved = (Unresolved) obj;
                    return this.index == unresolved.index && this.optional == unresolved.optional;
                }

                public int hashCode() {
                    return (((super.hashCode() * 31) + this.index) * 31) + (this.optional ? 1 : 0);
                }

                protected Unresolved(TypeDescription.Generic generic, Argument argument) {
                    this(generic, argument.readOnly(), argument.typing(), argument.value(), argument.optional());
                }

                protected Unresolved(ParameterDescription parameterDescription) {
                    this(parameterDescription.getType(), true, Assigner.Typing.STATIC, parameterDescription.getIndex());
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, int i) {
                    this(generic, z, typing, i, false);
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, int i, boolean z2) {
                    super(generic, z, typing);
                    this.index = i;
                    this.optional = z2;
                }

                /* access modifiers changed from: protected */
                public ParameterDescription resolve(MethodDescription methodDescription) {
                    ParameterList<?> parameters = methodDescription.getParameters();
                    int size = parameters.size();
                    int i = this.index;
                    if (size > i) {
                        return (ParameterDescription) parameters.get(i);
                    }
                    throw new IllegalStateException(methodDescription + " does not define an index " + this.index);
                }

                public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                    if (!this.optional || methodDescription.getParameters().size() > this.index) {
                        return super.resolve(typeDescription, methodDescription, assigner, argumentHandler, sort);
                    }
                    return this.readOnly ? new Target.ForDefaultValue.ReadOnly(this.target) : new Target.ForDefaultValue.ReadWrite(this.target);
                }

                protected enum Factory implements Factory<Argument> {
                    INSTANCE;

                    public Class<Argument> getAnnotationType() {
                        return Argument.class;
                    }

                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Argument> loadable, Factory.AdviceType adviceType) {
                        if (!adviceType.isDelegation() || loadable.loadSilent().readOnly()) {
                            return new Unresolved(inDefinedShape.getType(), loadable.loadSilent());
                        }
                        throw new IllegalStateException("Cannot define writable field access for " + inDefinedShape + " when using delegation");
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Resolved extends ForArgument {
                private final ParameterDescription parameterDescription;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.parameterDescription.equals(((Resolved) obj).parameterDescription);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.parameterDescription.hashCode();
                }

                public Resolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, ParameterDescription parameterDescription2) {
                    super(generic, z, typing);
                    this.parameterDescription = parameterDescription2;
                }

                /* access modifiers changed from: protected */
                public ParameterDescription resolve(MethodDescription methodDescription) {
                    if (this.parameterDescription.getDeclaringMethod().equals(methodDescription)) {
                        return this.parameterDescription;
                    }
                    throw new IllegalStateException(this.parameterDescription + " is not a parameter of " + methodDescription);
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class Factory<T extends Annotation> implements Factory<T> {
                    private final Class<T> annotationType;
                    private final ParameterDescription parameterDescription;
                    private final boolean readOnly;
                    private final Assigner.Typing typing;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Factory factory = (Factory) obj;
                        return this.readOnly == factory.readOnly && this.typing.equals(factory.typing) && this.annotationType.equals(factory.annotationType) && this.parameterDescription.equals(factory.parameterDescription);
                    }

                    public int hashCode() {
                        return ((((((527 + this.annotationType.hashCode()) * 31) + this.parameterDescription.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
                    }

                    public Factory(Class<T> cls, ParameterDescription parameterDescription2) {
                        this(cls, parameterDescription2, true, Assigner.Typing.STATIC);
                    }

                    public Factory(Class<T> cls, ParameterDescription parameterDescription2, boolean z, Assigner.Typing typing2) {
                        this.annotationType = cls;
                        this.parameterDescription = parameterDescription2;
                        this.readOnly = z;
                        this.typing = typing2;
                    }

                    public Class<T> getAnnotationType() {
                        return this.annotationType;
                    }

                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                        return new Resolved(inDefinedShape.getType(), this.readOnly, this.typing, this.parameterDescription);
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForThisReference implements OffsetMapping {
            private final boolean optional;
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForThisReference forThisReference = (ForThisReference) obj;
                return this.readOnly == forThisReference.readOnly && this.optional == forThisReference.optional && this.typing.equals(forThisReference.typing) && this.target.equals(forThisReference.target);
            }

            public int hashCode() {
                return ((((((527 + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode()) * 31) + (this.optional ? 1 : 0);
            }

            protected ForThisReference(TypeDescription.Generic generic, This thisR) {
                this(generic, thisR.readOnly(), thisR.typing(), thisR.optional());
            }

            public ForThisReference(TypeDescription.Generic generic, boolean z, Assigner.Typing typing2, boolean z2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing2;
                this.optional = z2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                if (!methodDescription.isStatic() && !sort.isPremature(methodDescription)) {
                    StackManipulation assign = assigner.assign(typeDescription.asGenericType(), this.target, this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + typeDescription + " to " + this.target);
                    } else if (this.readOnly) {
                        return new Target.ForVariable.ReadOnly(typeDescription.asGenericType(), argumentHandler.argument(0), assign);
                    } else {
                        StackManipulation assign2 = assigner.assign(this.target, typeDescription.asGenericType(), this.typing);
                        if (assign2.isValid()) {
                            return new Target.ForVariable.ReadWrite(typeDescription.asGenericType(), argumentHandler.argument(0), assign, assign2);
                        }
                        throw new IllegalStateException("Cannot assign " + this.target + " to " + typeDescription);
                    }
                } else if (this.optional) {
                    return this.readOnly ? new Target.ForDefaultValue.ReadOnly(typeDescription) : new Target.ForDefaultValue.ReadWrite(typeDescription);
                } else {
                    throw new IllegalStateException("Cannot map this reference for static method or constructor start: " + methodDescription);
                }
            }

            protected enum Factory implements Factory<This> {
                INSTANCE;

                public Class<This> getAnnotationType() {
                    return This.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<This> loadable, Factory.AdviceType adviceType) {
                    if (!adviceType.isDelegation() || loadable.loadSilent().readOnly()) {
                        return new ForThisReference(inDefinedShape.getType(), loadable.loadSilent());
                    }
                    throw new IllegalStateException("Cannot write to this reference for " + inDefinedShape + " in read-only context");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForAllArguments implements OffsetMapping {
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForAllArguments forAllArguments = (ForAllArguments) obj;
                return this.readOnly == forAllArguments.readOnly && this.typing.equals(forAllArguments.typing) && this.target.equals(forAllArguments.target);
            }

            public int hashCode() {
                return ((((527 + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForAllArguments(TypeDescription.Generic generic, AllArguments allArguments) {
                this(generic, allArguments.readOnly(), allArguments.typing());
            }

            public ForAllArguments(TypeDescription.Generic generic, boolean z, Assigner.Typing typing2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                Iterator it = methodDescription.getParameters().iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription = (ParameterDescription) it.next();
                    StackManipulation assign = assigner.assign(parameterDescription.getType(), this.target, this.typing);
                    if (assign.isValid()) {
                        arrayList.add(new StackManipulation.Compound(MethodVariableAccess.of(parameterDescription.getType()).loadFrom(argumentHandler.argument(parameterDescription.getOffset())), assign));
                    } else {
                        throw new IllegalStateException("Cannot assign " + parameterDescription + " to " + this.target);
                    }
                }
                if (this.readOnly) {
                    return new Target.ForArray.ReadOnly(this.target, arrayList);
                }
                ArrayList arrayList2 = new ArrayList(methodDescription.getParameters().size());
                Iterator it2 = methodDescription.getParameters().iterator();
                while (it2.hasNext()) {
                    ParameterDescription parameterDescription2 = (ParameterDescription) it2.next();
                    StackManipulation assign2 = assigner.assign(this.target, parameterDescription2.getType(), this.typing);
                    if (assign2.isValid()) {
                        arrayList2.add(new StackManipulation.Compound(assign2, MethodVariableAccess.of(parameterDescription2.getType()).storeAt(argumentHandler.argument(parameterDescription2.getOffset()))));
                    } else {
                        throw new IllegalStateException("Cannot assign " + this.target + " to " + parameterDescription2);
                    }
                }
                return new Target.ForArray.ReadWrite(this.target, arrayList, arrayList2);
            }

            protected enum Factory implements Factory<AllArguments> {
                INSTANCE;

                public Class<AllArguments> getAnnotationType() {
                    return AllArguments.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<AllArguments> loadable, Factory.AdviceType adviceType) {
                    TypeDescription.Generic generic;
                    if (!inDefinedShape.getType().represents(Object.class) && !inDefinedShape.getType().isArray()) {
                        throw new IllegalStateException("Cannot use AllArguments annotation on a non-array type");
                    } else if (!adviceType.isDelegation() || loadable.loadSilent().readOnly()) {
                        if (inDefinedShape.getType().represents(Object.class)) {
                            generic = TypeDescription.Generic.OBJECT;
                        } else {
                            generic = inDefinedShape.getType().getComponentType();
                        }
                        return new ForAllArguments(generic, loadable.loadSilent());
                    } else {
                        throw new IllegalStateException("Cannot define writable field access for " + inDefinedShape);
                    }
                }
            }
        }

        public enum ForInstrumentedType implements OffsetMapping {
            INSTANCE;

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                return Target.ForStackManipulation.of(typeDescription);
            }
        }

        public enum ForInstrumentedMethod implements OffsetMapping {
            METHOD {
                /* access modifiers changed from: protected */
                public boolean isRepresentable(MethodDescription methodDescription) {
                    return methodDescription.isMethod();
                }
            },
            CONSTRUCTOR {
                /* access modifiers changed from: protected */
                public boolean isRepresentable(MethodDescription methodDescription) {
                    return methodDescription.isConstructor();
                }
            },
            EXECUTABLE {
                /* access modifiers changed from: protected */
                public boolean isRepresentable(MethodDescription methodDescription) {
                    return true;
                }
            };

            /* access modifiers changed from: protected */
            public abstract boolean isRepresentable(MethodDescription methodDescription);

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                if (isRepresentable(methodDescription)) {
                    return Target.ForStackManipulation.of((MethodDescription.InDefinedShape) methodDescription.asDefined());
                }
                throw new IllegalStateException("Cannot represent " + methodDescription + " as given method constant");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class ForField implements OffsetMapping {
            /* access modifiers changed from: private */
            public static final MethodDescription.InDefinedShape DECLARING_TYPE;
            /* access modifiers changed from: private */
            public static final MethodDescription.InDefinedShape READ_ONLY;
            /* access modifiers changed from: private */
            public static final MethodDescription.InDefinedShape TYPING;
            /* access modifiers changed from: private */
            public static final MethodDescription.InDefinedShape VALUE;
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForField forField = (ForField) obj;
                return this.readOnly == forField.readOnly && this.typing.equals(forField.typing) && this.target.equals(forField.target);
            }

            public int hashCode() {
                return ((((527 + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            /* access modifiers changed from: protected */
            public abstract FieldDescription resolve(TypeDescription typeDescription);

            static {
                MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(FieldValue.class).getDeclaredMethods();
                VALUE = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("value"))).getOnly();
                DECLARING_TYPE = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("declaringType"))).getOnly();
                READ_ONLY = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("readOnly"))).getOnly();
                TYPING = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("typing"))).getOnly();
            }

            public ForField(TypeDescription.Generic generic, boolean z, Assigner.Typing typing2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                FieldDescription resolve = resolve(typeDescription);
                if (!resolve.isStatic() && methodDescription.isStatic()) {
                    throw new IllegalStateException("Cannot read non-static field " + resolve + " from static method " + methodDescription);
                } else if (!sort.isPremature(methodDescription) || resolve.isStatic()) {
                    StackManipulation assign = assigner.assign(resolve.getType(), this.target, this.typing);
                    if (!assign.isValid()) {
                        throw new IllegalStateException("Cannot assign " + resolve + " to " + this.target);
                    } else if (this.readOnly) {
                        return new Target.ForField.ReadOnly(resolve, assign);
                    } else {
                        StackManipulation assign2 = assigner.assign(this.target, resolve.getType(), this.typing);
                        if (assign2.isValid()) {
                            return new Target.ForField.ReadWrite((FieldDescription) resolve.asDefined(), assign, assign2);
                        }
                        throw new IllegalStateException("Cannot assign " + this.target + " to " + resolve);
                    }
                } else {
                    throw new IllegalStateException("Cannot access non-static field before calling constructor: " + methodDescription);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class Unresolved extends ForField {
                private final String name;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.name.equals(((Unresolved) obj).name);
                }

                /* access modifiers changed from: protected */
                public abstract FieldLocator fieldLocator(TypeDescription typeDescription);

                public int hashCode() {
                    return (super.hashCode() * 31) + this.name.hashCode();
                }

                public Unresolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str) {
                    super(generic, z, typing);
                    this.name = str;
                }

                /* access modifiers changed from: protected */
                public FieldDescription resolve(TypeDescription typeDescription) {
                    FieldLocator.Resolution locate = fieldLocator(typeDescription).locate(this.name);
                    if (locate.isResolved()) {
                        return locate.getField();
                    }
                    throw new IllegalStateException("Cannot locate field named " + this.name + " for " + typeDescription);
                }

                public static class WithImplicitType extends Unresolved {
                    protected WithImplicitType(TypeDescription.Generic generic, AnnotationDescription.Loadable<FieldValue> loadable) {
                        this(generic, ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(ForField.TYPING).loadSilent(Assigner.Typing.class.getClassLoader()).resolve(Assigner.Typing.class), (String) loadable.getValue(ForField.VALUE).resolve(String.class));
                    }

                    public WithImplicitType(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str) {
                        super(generic, z, typing, str);
                    }

                    /* access modifiers changed from: protected */
                    public FieldLocator fieldLocator(TypeDescription typeDescription) {
                        return new FieldLocator.ForClassHierarchy(typeDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class WithExplicitType extends Unresolved {
                    private final TypeDescription declaringType;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.declaringType.equals(((WithExplicitType) obj).declaringType);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.declaringType.hashCode();
                    }

                    protected WithExplicitType(TypeDescription.Generic generic, AnnotationDescription.Loadable<FieldValue> loadable, TypeDescription typeDescription) {
                        this(generic, ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue(), (Assigner.Typing) loadable.getValue(ForField.TYPING).loadSilent(Assigner.Typing.class.getClassLoader()).resolve(Assigner.Typing.class), (String) loadable.getValue(ForField.VALUE).resolve(String.class), typeDescription);
                    }

                    public WithExplicitType(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, String str, TypeDescription typeDescription) {
                        super(generic, z, typing, str);
                        this.declaringType = typeDescription;
                    }

                    /* access modifiers changed from: protected */
                    public FieldLocator fieldLocator(TypeDescription typeDescription) {
                        if (this.declaringType.represents(TargetType.class) || typeDescription.isAssignableTo(this.declaringType)) {
                            return new FieldLocator.ForExactType(TargetType.resolve(this.declaringType, typeDescription));
                        }
                        throw new IllegalStateException(this.declaringType + " is no super type of " + typeDescription);
                    }
                }

                protected enum Factory implements Factory<FieldValue> {
                    INSTANCE;

                    public Class<FieldValue> getAnnotationType() {
                        return FieldValue.class;
                    }

                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<FieldValue> loadable, Factory.AdviceType adviceType) {
                        if (!adviceType.isDelegation() || ((Boolean) loadable.getValue(ForField.READ_ONLY).resolve(Boolean.class)).booleanValue()) {
                            TypeDescription typeDescription = (TypeDescription) loadable.getValue(ForField.DECLARING_TYPE).resolve(TypeDescription.class);
                            if (typeDescription.represents(Void.TYPE)) {
                                return new WithImplicitType(inDefinedShape.getType(), loadable);
                            }
                            return new WithExplicitType(inDefinedShape.getType(), loadable, typeDescription);
                        }
                        throw new IllegalStateException("Cannot write to field for " + inDefinedShape + " in read-only context");
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Resolved extends ForField {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Resolved) obj).fieldDescription);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.fieldDescription.hashCode();
                }

                public Resolved(TypeDescription.Generic generic, boolean z, Assigner.Typing typing, FieldDescription fieldDescription2) {
                    super(generic, z, typing);
                    this.fieldDescription = fieldDescription2;
                }

                /* access modifiers changed from: protected */
                public FieldDescription resolve(TypeDescription typeDescription) {
                    if (!this.fieldDescription.isStatic() && !this.fieldDescription.getDeclaringType().asErasure().isAssignableFrom(typeDescription)) {
                        throw new IllegalStateException(this.fieldDescription + " is no member of " + typeDescription);
                    } else if (this.fieldDescription.isAccessibleTo(typeDescription)) {
                        return this.fieldDescription;
                    } else {
                        throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class Factory<T extends Annotation> implements Factory<T> {
                    private final Class<T> annotationType;
                    private final FieldDescription fieldDescription;
                    private final boolean readOnly;
                    private final Assigner.Typing typing;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Factory factory = (Factory) obj;
                        return this.readOnly == factory.readOnly && this.typing.equals(factory.typing) && this.annotationType.equals(factory.annotationType) && this.fieldDescription.equals(factory.fieldDescription);
                    }

                    public int hashCode() {
                        return ((((((527 + this.annotationType.hashCode()) * 31) + this.fieldDescription.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
                    }

                    public Factory(Class<T> cls, FieldDescription fieldDescription2) {
                        this(cls, fieldDescription2, true, Assigner.Typing.STATIC);
                    }

                    public Factory(Class<T> cls, FieldDescription fieldDescription2, boolean z, Assigner.Typing typing2) {
                        this.annotationType = cls;
                        this.fieldDescription = fieldDescription2;
                        this.readOnly = z;
                        this.typing = typing2;
                    }

                    public Class<T> getAnnotationType() {
                        return this.annotationType;
                    }

                    public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                        return new Resolved(inDefinedShape.getType(), this.readOnly, this.typing, this.fieldDescription);
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForOrigin implements OffsetMapping {
            private static final char DELIMITER = '#';
            private static final char ESCAPE = '\\';
            private final List<Renderer> renderers;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.renderers.equals(((ForOrigin) obj).renderers);
            }

            public int hashCode() {
                return 527 + this.renderers.hashCode();
            }

            public ForOrigin(List<Renderer> list) {
                this.renderers = list;
            }

            public static OffsetMapping parse(String str) {
                int i;
                if (str.equals("")) {
                    return new ForOrigin(Collections.singletonList(Renderer.ForStringRepresentation.INSTANCE));
                }
                ArrayList arrayList = new ArrayList(str.length());
                int indexOf = str.indexOf(35);
                int i2 = 0;
                while (indexOf != -1) {
                    if (indexOf != 0) {
                        int i3 = indexOf - 1;
                        if (str.charAt(i3) == '\\' && (indexOf == 1 || str.charAt(indexOf - 2) != '\\')) {
                            arrayList.add(new Renderer.ForConstantValue(str.substring(i2, Math.max(0, i3)) + DELIMITER));
                            i = indexOf + 1;
                            i2 = i;
                            indexOf = str.indexOf(35, i2);
                        }
                    }
                    int i4 = indexOf + 1;
                    if (str.length() != i4) {
                        arrayList.add(new Renderer.ForConstantValue(str.substring(i2, indexOf).replace("\\\\", "\\")));
                        char charAt = str.charAt(i4);
                        if (charAt == 'd') {
                            arrayList.add(Renderer.ForDescriptor.INSTANCE);
                        } else if (charAt != 'm') {
                            switch (charAt) {
                                case 'r':
                                    arrayList.add(Renderer.ForReturnTypeName.INSTANCE);
                                    break;
                                case 's':
                                    arrayList.add(Renderer.ForJavaSignature.INSTANCE);
                                    break;
                                case 't':
                                    arrayList.add(Renderer.ForTypeName.INSTANCE);
                                    break;
                                default:
                                    throw new IllegalStateException("Illegal sort descriptor " + str.charAt(i4) + " for " + str);
                            }
                        } else {
                            arrayList.add(Renderer.ForMethodName.INSTANCE);
                        }
                        i = indexOf + 2;
                        i2 = i;
                        indexOf = str.indexOf(35, i2);
                    } else {
                        throw new IllegalStateException("Missing sort descriptor for " + str + " at index " + indexOf);
                    }
                }
                arrayList.add(new Renderer.ForConstantValue(str.substring(i2)));
                return new ForOrigin(arrayList);
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StringBuilder sb = new StringBuilder();
                for (Renderer apply : this.renderers) {
                    sb.append(apply.apply(typeDescription, methodDescription));
                }
                return Target.ForStackManipulation.of((Object) sb.toString());
            }

            public interface Renderer {
                String apply(TypeDescription typeDescription, MethodDescription methodDescription);

                public enum ForMethodName implements Renderer {
                    INSTANCE;
                    
                    public static final char SYMBOL = 'm';

                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getInternalName();
                    }
                }

                public enum ForTypeName implements Renderer {
                    INSTANCE;
                    
                    public static final char SYMBOL = 't';

                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return typeDescription.getName();
                    }
                }

                public enum ForDescriptor implements Renderer {
                    INSTANCE;
                    
                    public static final char SYMBOL = 'd';

                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getDescriptor();
                    }
                }

                public enum ForJavaSignature implements Renderer {
                    INSTANCE;
                    
                    public static final char SYMBOL = 's';

                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        StringBuilder sb = new StringBuilder("(");
                        boolean z = false;
                        for (TypeDescription typeDescription2 : methodDescription.getParameters().asTypeList().asErasures()) {
                            if (z) {
                                sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                            } else {
                                z = true;
                            }
                            sb.append(typeDescription2.getName());
                        }
                        sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
                        return sb.toString();
                    }
                }

                public enum ForReturnTypeName implements Renderer {
                    INSTANCE;
                    
                    public static final char SYMBOL = 'r';

                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.getReturnType().asErasure().getName();
                    }
                }

                public enum ForStringRepresentation implements Renderer {
                    INSTANCE;

                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return methodDescription.toString();
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForConstantValue implements Renderer {
                    private final String value;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.value.equals(((ForConstantValue) obj).value);
                    }

                    public int hashCode() {
                        return 527 + this.value.hashCode();
                    }

                    public ForConstantValue(String str) {
                        this.value = str;
                    }

                    public String apply(TypeDescription typeDescription, MethodDescription methodDescription) {
                        return this.value;
                    }
                }
            }

            protected enum Factory implements Factory<Origin> {
                INSTANCE;

                public Class<Origin> getAnnotationType() {
                    return Origin.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Origin> loadable, Factory.AdviceType adviceType) {
                    if (inDefinedShape.getType().asErasure().represents(Class.class)) {
                        return ForInstrumentedType.INSTANCE;
                    }
                    if (inDefinedShape.getType().asErasure().represents(Method.class)) {
                        return ForInstrumentedMethod.METHOD;
                    }
                    if (inDefinedShape.getType().asErasure().represents(Constructor.class)) {
                        return ForInstrumentedMethod.CONSTRUCTOR;
                    }
                    if (JavaType.EXECUTABLE.getTypeStub().equals(inDefinedShape.getType().asErasure())) {
                        return ForInstrumentedMethod.EXECUTABLE;
                    }
                    if (inDefinedShape.getType().asErasure().isAssignableFrom((Class<?>) String.class)) {
                        return ForOrigin.parse(loadable.loadSilent().value());
                    }
                    throw new IllegalStateException("Non-supported type " + inDefinedShape.getType() + " for @Origin annotation");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForUnusedValue implements OffsetMapping {
            private final TypeDefinition target;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.target.equals(((ForUnusedValue) obj).target);
            }

            public int hashCode() {
                return 527 + this.target.hashCode();
            }

            public ForUnusedValue(TypeDefinition typeDefinition) {
                this.target = typeDefinition;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                return new Target.ForDefaultValue.ReadWrite(this.target);
            }

            protected enum Factory implements Factory<Unused> {
                INSTANCE;

                public Class<Unused> getAnnotationType() {
                    return Unused.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Unused> loadable, Factory.AdviceType adviceType) {
                    return new ForUnusedValue(inDefinedShape.getType());
                }
            }
        }

        public enum ForStubValue implements OffsetMapping, Factory<StubValue> {
            INSTANCE;

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                return new Target.ForDefaultValue.ReadOnly(methodDescription.getReturnType(), assigner.assign(methodDescription.getReturnType(), TypeDescription.Generic.OBJECT, Assigner.Typing.DYNAMIC));
            }

            public Class<StubValue> getAnnotationType() {
                return StubValue.class;
            }

            public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<StubValue> loadable, Factory.AdviceType adviceType) {
                if (inDefinedShape.getType().represents(Object.class)) {
                    return this;
                }
                throw new IllegalStateException("Cannot use StubValue on non-Object parameter type " + inDefinedShape);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForEnterValue implements OffsetMapping {
            private final TypeDescription.Generic enterType;
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForEnterValue forEnterValue = (ForEnterValue) obj;
                return this.readOnly == forEnterValue.readOnly && this.typing.equals(forEnterValue.typing) && this.target.equals(forEnterValue.target) && this.enterType.equals(forEnterValue.enterType);
            }

            public int hashCode() {
                return ((((((527 + this.target.hashCode()) * 31) + this.enterType.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForEnterValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, Enter enter) {
                this(generic, generic2, enter.readOnly(), enter.typing());
            }

            public ForEnterValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, boolean z, Assigner.Typing typing2) {
                this.target = generic;
                this.enterType = generic2;
                this.readOnly = z;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.enterType, this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.enterType + " to " + this.target);
                } else if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(this.target, argumentHandler.enter(), assign);
                } else {
                    StackManipulation assign2 = assigner.assign(this.target, this.enterType, this.typing);
                    if (assign2.isValid()) {
                        return new Target.ForVariable.ReadWrite(this.target, argumentHandler.enter(), assign, assign2);
                    }
                    throw new IllegalStateException("Cannot assign " + this.target + " to " + this.enterType);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory<Enter> {
                private final TypeDefinition enterType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.enterType.equals(((Factory) obj).enterType);
                }

                public int hashCode() {
                    return 527 + this.enterType.hashCode();
                }

                protected Factory(TypeDefinition typeDefinition) {
                    this.enterType = typeDefinition;
                }

                protected static Factory<Enter> of(TypeDefinition typeDefinition) {
                    return typeDefinition.represents(Void.TYPE) ? new Factory.Illegal(Enter.class) : new Factory(typeDefinition);
                }

                public Class<Enter> getAnnotationType() {
                    return Enter.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Enter> loadable, Factory.AdviceType adviceType) {
                    if (!adviceType.isDelegation() || loadable.loadSilent().readOnly()) {
                        return new ForEnterValue(inDefinedShape.getType(), this.enterType.asGenericType(), loadable.loadSilent());
                    }
                    throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForExitValue implements OffsetMapping {
            private final TypeDescription.Generic exitType;
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForExitValue forExitValue = (ForExitValue) obj;
                return this.readOnly == forExitValue.readOnly && this.typing.equals(forExitValue.typing) && this.target.equals(forExitValue.target) && this.exitType.equals(forExitValue.exitType);
            }

            public int hashCode() {
                return ((((((527 + this.target.hashCode()) * 31) + this.exitType.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForExitValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, Exit exit) {
                this(generic, generic2, exit.readOnly(), exit.typing());
            }

            public ForExitValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, boolean z, Assigner.Typing typing2) {
                this.target = generic;
                this.exitType = generic2;
                this.readOnly = z;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.exitType, this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + this.exitType + " to " + this.target);
                } else if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(this.target, argumentHandler.exit(), assign);
                } else {
                    StackManipulation assign2 = assigner.assign(this.target, this.exitType, this.typing);
                    if (assign2.isValid()) {
                        return new Target.ForVariable.ReadWrite(this.target, argumentHandler.exit(), assign, assign2);
                    }
                    throw new IllegalStateException("Cannot assign " + this.target + " to " + this.exitType);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory<Exit> {
                private final TypeDefinition exitType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.exitType.equals(((Factory) obj).exitType);
                }

                public int hashCode() {
                    return 527 + this.exitType.hashCode();
                }

                protected Factory(TypeDefinition typeDefinition) {
                    this.exitType = typeDefinition;
                }

                protected static Factory<Exit> of(TypeDefinition typeDefinition) {
                    return typeDefinition.represents(Void.TYPE) ? new Factory.Illegal(Exit.class) : new Factory(typeDefinition);
                }

                public Class<Exit> getAnnotationType() {
                    return Exit.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Exit> loadable, Factory.AdviceType adviceType) {
                    if (!adviceType.isDelegation() || loadable.loadSilent().readOnly()) {
                        return new ForExitValue(inDefinedShape.getType(), this.exitType.asGenericType(), loadable.loadSilent());
                    }
                    throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForLocalValue implements OffsetMapping {
            private final TypeDescription.Generic localType;
            private final String name;
            private final TypeDescription.Generic target;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForLocalValue forLocalValue = (ForLocalValue) obj;
                return this.name.equals(forLocalValue.name) && this.target.equals(forLocalValue.target) && this.localType.equals(forLocalValue.localType);
            }

            public int hashCode() {
                return ((((527 + this.target.hashCode()) * 31) + this.localType.hashCode()) * 31) + this.name.hashCode();
            }

            public ForLocalValue(TypeDescription.Generic generic, TypeDescription.Generic generic2, String str) {
                this.target = generic;
                this.localType = generic2;
                this.name = str;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.localType, this.target, Assigner.Typing.STATIC);
                StackManipulation assign2 = assigner.assign(this.target, this.localType, Assigner.Typing.STATIC);
                if (assign.isValid() && assign2.isValid()) {
                    return new Target.ForVariable.ReadWrite(this.target, argumentHandler.named(this.name), assign, assign2);
                }
                throw new IllegalStateException("Cannot assign " + this.localType + " to " + this.target);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory<Local> {
                private final Map<String, TypeDefinition> namedTypes;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.namedTypes.equals(((Factory) obj).namedTypes);
                }

                public int hashCode() {
                    return 527 + this.namedTypes.hashCode();
                }

                protected Factory(Map<String, TypeDefinition> map) {
                    this.namedTypes = map;
                }

                public Class<Local> getAnnotationType() {
                    return Local.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Local> loadable, Factory.AdviceType adviceType) {
                    String value = loadable.loadSilent().value();
                    TypeDefinition typeDefinition = this.namedTypes.get(value);
                    if (typeDefinition != null) {
                        return new ForLocalValue(inDefinedShape.getType(), typeDefinition.asGenericType(), value);
                    }
                    throw new IllegalStateException("Named local variable is unknown: " + value);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForReturnValue implements OffsetMapping {
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForReturnValue forReturnValue = (ForReturnValue) obj;
                return this.readOnly == forReturnValue.readOnly && this.typing.equals(forReturnValue.typing) && this.target.equals(forReturnValue.target);
            }

            public int hashCode() {
                return ((((527 + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForReturnValue(TypeDescription.Generic generic, Return returnR) {
                this(generic, returnR.readOnly(), returnR.typing());
            }

            public ForReturnValue(TypeDescription.Generic generic, boolean z, Assigner.Typing typing2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(methodDescription.getReturnType(), this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign " + methodDescription.getReturnType() + " to " + this.target);
                } else if (!this.readOnly) {
                    StackManipulation assign2 = assigner.assign(this.target, methodDescription.getReturnType(), this.typing);
                    if (!assign2.isValid()) {
                        throw new IllegalStateException("Cannot assign " + this.target + " to " + methodDescription.getReturnType());
                    } else if (methodDescription.getReturnType().represents(Void.TYPE)) {
                        return new Target.ForDefaultValue.ReadWrite(this.target);
                    } else {
                        return new Target.ForVariable.ReadWrite(methodDescription.getReturnType(), argumentHandler.returned(), assign, assign2);
                    }
                } else if (methodDescription.getReturnType().represents(Void.TYPE)) {
                    return new Target.ForDefaultValue.ReadOnly(this.target);
                } else {
                    return new Target.ForVariable.ReadOnly(methodDescription.getReturnType(), argumentHandler.returned(), assign);
                }
            }

            protected enum Factory implements Factory<Return> {
                INSTANCE;

                public Class<Return> getAnnotationType() {
                    return Return.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Return> loadable, Factory.AdviceType adviceType) {
                    if (!adviceType.isDelegation() || loadable.loadSilent().readOnly()) {
                        return new ForReturnValue(inDefinedShape.getType(), loadable.loadSilent());
                    }
                    throw new IllegalStateException("Cannot write return value for " + inDefinedShape + " in read-only context");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForThrowable implements OffsetMapping {
            private final boolean readOnly;
            private final TypeDescription.Generic target;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForThrowable forThrowable = (ForThrowable) obj;
                return this.readOnly == forThrowable.readOnly && this.typing.equals(forThrowable.typing) && this.target.equals(forThrowable.target);
            }

            public int hashCode() {
                return ((((527 + this.target.hashCode()) * 31) + (this.readOnly ? 1 : 0)) * 31) + this.typing.hashCode();
            }

            protected ForThrowable(TypeDescription.Generic generic, Thrown thrown) {
                this(generic, thrown.readOnly(), thrown.typing());
            }

            public ForThrowable(TypeDescription.Generic generic, boolean z, Assigner.Typing typing2) {
                this.target = generic;
                this.readOnly = z;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(TypeDescription.THROWABLE.asGenericType(), this.target, this.typing);
                if (!assign.isValid()) {
                    throw new IllegalStateException("Cannot assign Throwable to " + this.target);
                } else if (this.readOnly) {
                    return new Target.ForVariable.ReadOnly(TypeDescription.THROWABLE, argumentHandler.thrown(), assign);
                } else {
                    StackManipulation assign2 = assigner.assign(this.target, TypeDescription.THROWABLE.asGenericType(), this.typing);
                    if (assign2.isValid()) {
                        return new Target.ForVariable.ReadWrite(TypeDescription.THROWABLE, argumentHandler.thrown(), assign, assign2);
                    }
                    throw new IllegalStateException("Cannot assign " + this.target + " to Throwable");
                }
            }

            protected enum Factory implements Factory<Thrown> {
                INSTANCE;

                protected static Factory<?> of(MethodDescription.InDefinedShape inDefinedShape) {
                    return ((TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class)).represents(NoExceptionHandler.class) ? new Factory.Illegal(Thrown.class) : INSTANCE;
                }

                public Class<Thrown> getAnnotationType() {
                    return Thrown.class;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<Thrown> loadable, Factory.AdviceType adviceType) {
                    if (!adviceType.isDelegation() || loadable.loadSilent().readOnly()) {
                        return new ForThrowable(inDefinedShape.getType(), loadable.loadSilent());
                    }
                    throw new IllegalStateException("Cannot use writable " + inDefinedShape + " on read-only parameter");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForStackManipulation implements OffsetMapping {
            private final StackManipulation stackManipulation;
            private final TypeDescription.Generic targetType;
            private final TypeDescription.Generic typeDescription;
            private final Assigner.Typing typing;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForStackManipulation forStackManipulation = (ForStackManipulation) obj;
                return this.typing.equals(forStackManipulation.typing) && this.stackManipulation.equals(forStackManipulation.stackManipulation) && this.typeDescription.equals(forStackManipulation.typeDescription) && this.targetType.equals(forStackManipulation.targetType);
            }

            public int hashCode() {
                return ((((((527 + this.stackManipulation.hashCode()) * 31) + this.typeDescription.hashCode()) * 31) + this.targetType.hashCode()) * 31) + this.typing.hashCode();
            }

            public ForStackManipulation(StackManipulation stackManipulation2, TypeDescription.Generic generic, TypeDescription.Generic generic2, Assigner.Typing typing2) {
                this.stackManipulation = stackManipulation2;
                this.typeDescription = generic;
                this.targetType = generic2;
                this.typing = typing2;
            }

            public Target resolve(TypeDescription typeDescription2, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.typeDescription, this.targetType, this.typing);
                if (assign.isValid()) {
                    return new Target.ForStackManipulation(new StackManipulation.Compound(this.stackManipulation, assign));
                }
                throw new IllegalStateException("Cannot assign " + this.typeDescription + " to " + this.targetType);
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Factory<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final StackManipulation stackManipulation;
                private final TypeDescription.Generic typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    return this.annotationType.equals(factory.annotationType) && this.stackManipulation.equals(factory.stackManipulation) && this.typeDescription.equals(factory.typeDescription);
                }

                public int hashCode() {
                    return ((((527 + this.annotationType.hashCode()) * 31) + this.stackManipulation.hashCode()) * 31) + this.typeDescription.hashCode();
                }

                public Factory(Class<T> cls, TypeDescription typeDescription2) {
                    this(cls, ClassConstant.of(typeDescription2), TypeDescription.CLASS.asGenericType());
                }

                public Factory(Class<T> cls, EnumerationDescription enumerationDescription) {
                    this(cls, FieldAccess.forEnumeration(enumerationDescription), enumerationDescription.getEnumerationType().asGenericType());
                }

                public Factory(Class<T> cls, StackManipulation stackManipulation2, TypeDescription.Generic generic) {
                    this.annotationType = cls;
                    this.stackManipulation = stackManipulation2;
                    this.typeDescription = generic;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, Object obj) {
                    StackManipulation stackManipulation2;
                    TypeDescription typeDescription2;
                    if (obj == null) {
                        return new OfDefaultValue(cls);
                    }
                    if (obj instanceof Boolean) {
                        stackManipulation2 = IntegerConstant.forValue(((Boolean) obj).booleanValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Boolean.TYPE);
                    } else if (obj instanceof Byte) {
                        stackManipulation2 = IntegerConstant.forValue((int) ((Byte) obj).byteValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Byte.TYPE);
                    } else if (obj instanceof Short) {
                        stackManipulation2 = IntegerConstant.forValue((int) ((Short) obj).shortValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Short.TYPE);
                    } else if (obj instanceof Character) {
                        stackManipulation2 = IntegerConstant.forValue((int) ((Character) obj).charValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Character.TYPE);
                    } else if (obj instanceof Integer) {
                        stackManipulation2 = IntegerConstant.forValue(((Integer) obj).intValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Integer.TYPE);
                    } else if (obj instanceof Long) {
                        stackManipulation2 = LongConstant.forValue(((Long) obj).longValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Long.TYPE);
                    } else if (obj instanceof Float) {
                        stackManipulation2 = FloatConstant.forValue(((Float) obj).floatValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Float.TYPE);
                    } else if (obj instanceof Double) {
                        stackManipulation2 = DoubleConstant.forValue(((Double) obj).doubleValue());
                        typeDescription2 = TypeDescription.ForLoadedType.of(Double.TYPE);
                    } else if (obj instanceof String) {
                        TextConstant textConstant = new TextConstant((String) obj);
                        typeDescription2 = TypeDescription.STRING;
                        stackManipulation2 = textConstant;
                    } else {
                        throw new IllegalStateException("Not a constant value: " + obj);
                    }
                    return new Factory(cls, stackManipulation2, typeDescription2.asGenericType());
                }

                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForStackManipulation(this.stackManipulation, this.typeDescription, inDefinedShape.getType(), Assigner.Typing.STATIC);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class OfDefaultValue<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.annotationType.equals(((OfDefaultValue) obj).annotationType);
                }

                public int hashCode() {
                    return 527 + this.annotationType.hashCode();
                }

                public OfDefaultValue(Class<T> cls) {
                    this.annotationType = cls;
                }

                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForStackManipulation(DefaultValue.of(inDefinedShape.getType()), inDefinedShape.getType(), inDefinedShape.getType(), Assigner.Typing.STATIC);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class OfAnnotationProperty<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final MethodDescription.InDefinedShape property;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    OfAnnotationProperty ofAnnotationProperty = (OfAnnotationProperty) obj;
                    return this.annotationType.equals(ofAnnotationProperty.annotationType) && this.property.equals(ofAnnotationProperty.property);
                }

                public int hashCode() {
                    return ((527 + this.annotationType.hashCode()) * 31) + this.property.hashCode();
                }

                protected OfAnnotationProperty(Class<T> cls, MethodDescription.InDefinedShape inDefinedShape) {
                    this.annotationType = cls;
                    this.property = inDefinedShape;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, String str) {
                    if (cls.isAnnotation()) {
                        try {
                            return new OfAnnotationProperty(cls, new MethodDescription.ForLoadedMethod(cls.getMethod(str, new Class[0])));
                        } catch (NoSuchMethodException e) {
                            throw new IllegalArgumentException("Cannot find a property " + str + " on " + cls, e);
                        }
                    } else {
                        throw new IllegalArgumentException("Not an annotation type: " + cls);
                    }
                }

                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    Factory<S> factory;
                    Object resolve = loadable.getValue(this.property).resolve();
                    if (resolve instanceof TypeDescription) {
                        factory = new Factory<>(this.annotationType, (TypeDescription) resolve);
                    } else if (resolve instanceof EnumerationDescription) {
                        factory = new Factory<>(this.annotationType, (EnumerationDescription) resolve);
                    } else if (!(resolve instanceof AnnotationDescription)) {
                        factory = Factory.of(this.annotationType, resolve);
                    } else {
                        throw new IllegalStateException("Cannot bind annotation as fixed value for " + this.property);
                    }
                    return factory.make(inDefinedShape, loadable, adviceType);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForSerializedValue implements OffsetMapping {
            private final StackManipulation deserialization;
            private final TypeDescription.Generic target;
            private final TypeDescription typeDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForSerializedValue forSerializedValue = (ForSerializedValue) obj;
                return this.target.equals(forSerializedValue.target) && this.typeDescription.equals(forSerializedValue.typeDescription) && this.deserialization.equals(forSerializedValue.deserialization);
            }

            public int hashCode() {
                return ((((527 + this.target.hashCode()) * 31) + this.typeDescription.hashCode()) * 31) + this.deserialization.hashCode();
            }

            public ForSerializedValue(TypeDescription.Generic generic, TypeDescription typeDescription2, StackManipulation stackManipulation) {
                this.target = generic;
                this.typeDescription = typeDescription2;
                this.deserialization = stackManipulation;
            }

            public Target resolve(TypeDescription typeDescription2, MethodDescription methodDescription, Assigner assigner, ArgumentHandler argumentHandler, Sort sort) {
                StackManipulation assign = assigner.assign(this.typeDescription.asGenericType(), this.target, Assigner.Typing.DYNAMIC);
                if (assign.isValid()) {
                    return new Target.ForStackManipulation(new StackManipulation.Compound(this.deserialization, assign));
                }
                throw new IllegalStateException("Cannot assign " + this.typeDescription + " to " + this.target);
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Factory<T extends Annotation> implements Factory<T> {
                private final Class<T> annotationType;
                private final StackManipulation deserialization;
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    return this.annotationType.equals(factory.annotationType) && this.typeDescription.equals(factory.typeDescription) && this.deserialization.equals(factory.deserialization);
                }

                public int hashCode() {
                    return ((((527 + this.annotationType.hashCode()) * 31) + this.typeDescription.hashCode()) * 31) + this.deserialization.hashCode();
                }

                protected Factory(Class<T> cls, TypeDescription typeDescription2, StackManipulation stackManipulation) {
                    this.annotationType = cls;
                    this.typeDescription = typeDescription2;
                    this.deserialization = stackManipulation;
                }

                public static <S extends Annotation> Factory<S> of(Class<S> cls, Serializable serializable, Class<?> cls2) {
                    if (cls2.isInstance(serializable)) {
                        return new Factory(cls, TypeDescription.ForLoadedType.of(cls2), SerializedConstant.of(serializable));
                    }
                    throw new IllegalArgumentException(serializable + " is no instance of " + cls2);
                }

                public Class<T> getAnnotationType() {
                    return this.annotationType;
                }

                public OffsetMapping make(ParameterDescription.InDefinedShape inDefinedShape, AnnotationDescription.Loadable<T> loadable, Factory.AdviceType adviceType) {
                    return new ForSerializedValue(inDefinedShape.getType(), this.typeDescription, this.deserialization);
                }
            }
        }
    }

    public interface ArgumentHandler {
        public static final int THIS_REFERENCE = 0;

        public enum Factory {
            SIMPLE {
                /* access modifiers changed from: protected */
                public ForInstrumentedMethod resolve(MethodDescription methodDescription, TypeDefinition typeDefinition, TypeDefinition typeDefinition2, Map<String, TypeDefinition> map) {
                    return new ForInstrumentedMethod.Default.Simple(methodDescription, typeDefinition2, new TreeMap(map), typeDefinition);
                }
            },
            COPYING {
                /* access modifiers changed from: protected */
                public ForInstrumentedMethod resolve(MethodDescription methodDescription, TypeDefinition typeDefinition, TypeDefinition typeDefinition2, Map<String, TypeDefinition> map) {
                    return new ForInstrumentedMethod.Default.Copying(methodDescription, typeDefinition2, new TreeMap(map), typeDefinition);
                }
            };

            /* access modifiers changed from: protected */
            public abstract ForInstrumentedMethod resolve(MethodDescription methodDescription, TypeDefinition typeDefinition, TypeDefinition typeDefinition2, Map<String, TypeDefinition> map);
        }

        int argument(int i);

        int enter();

        int exit();

        int named(String str);

        int returned();

        int thrown();

        public interface ForInstrumentedMethod extends ArgumentHandler {
            ForAdvice bindEnter(MethodDescription methodDescription);

            ForAdvice bindExit(MethodDescription methodDescription, boolean z);

            List<TypeDescription> getNamedTypes();

            boolean isCopyingArguments();

            int prepare(MethodVisitor methodVisitor);

            int variable(int i);

            public static abstract class Default implements ForInstrumentedMethod {
                protected final TypeDefinition enterType;
                protected final TypeDefinition exitType;
                protected final MethodDescription instrumentedMethod;
                protected final TreeMap<String, TypeDefinition> namedTypes;

                protected Default(MethodDescription methodDescription, TypeDefinition typeDefinition, TreeMap<String, TypeDefinition> treeMap, TypeDefinition typeDefinition2) {
                    this.instrumentedMethod = methodDescription;
                    this.namedTypes = treeMap;
                    this.exitType = typeDefinition;
                    this.enterType = typeDefinition2;
                }

                public int exit() {
                    return this.instrumentedMethod.getStackSize();
                }

                public int named(String str) {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.headMap(str).values());
                }

                public int enter() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values());
                }

                public int returned() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize();
                }

                public int thrown() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize() + this.instrumentedMethod.getReturnType().getStackSize().getSize();
                }

                public ForAdvice bindEnter(MethodDescription methodDescription) {
                    return new ForAdvice.Default.ForMethodEnter(this.instrumentedMethod, methodDescription, this.exitType, this.namedTypes);
                }

                public ForAdvice bindExit(MethodDescription methodDescription, boolean z) {
                    return new ForAdvice.Default.ForMethodExit(this.instrumentedMethod, methodDescription, this.exitType, this.namedTypes, this.enterType, z ? StackSize.ZERO : StackSize.SINGLE);
                }

                public List<TypeDescription> getNamedTypes() {
                    ArrayList arrayList = new ArrayList(this.namedTypes.size());
                    for (TypeDefinition asErasure : this.namedTypes.values()) {
                        arrayList.add(asErasure.asErasure());
                    }
                    return arrayList;
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class Simple extends Default {
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass();
                    }

                    public int hashCode() {
                        return 17;
                    }

                    public boolean isCopyingArguments() {
                        return false;
                    }

                    public int prepare(MethodVisitor methodVisitor) {
                        return 0;
                    }

                    protected Simple(MethodDescription methodDescription, TypeDefinition typeDefinition, TreeMap<String, TypeDefinition> treeMap, TypeDefinition typeDefinition2) {
                        super(methodDescription, typeDefinition, treeMap, typeDefinition2);
                    }

                    public int argument(int i) {
                        return i < this.instrumentedMethod.getStackSize() ? i : i + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize();
                    }

                    public int variable(int i) {
                        return i < (this.instrumentedMethod.isStatic() ^ true ? 1 : 0) + this.instrumentedMethod.getParameters().size() ? i : i + (this.exitType.represents(Void.TYPE) ^ true ? 1 : 0) + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + (this.enterType.represents(Void.TYPE) ^ true ? 1 : 0);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class Copying extends Default {
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass();
                    }

                    public int hashCode() {
                        return 17;
                    }

                    public boolean isCopyingArguments() {
                        return true;
                    }

                    protected Copying(MethodDescription methodDescription, TypeDefinition typeDefinition, TreeMap<String, TypeDefinition> treeMap, TypeDefinition typeDefinition2) {
                        super(methodDescription, typeDefinition, treeMap, typeDefinition2);
                    }

                    public int argument(int i) {
                        return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize() + i;
                    }

                    public int variable(int i) {
                        return (this.instrumentedMethod.isStatic() ^ true ? 1 : 0) + this.instrumentedMethod.getParameters().size() + (this.exitType.represents(Void.TYPE) ^ true ? 1 : 0) + this.namedTypes.size() + (this.enterType.represents(Void.TYPE) ^ true ? 1 : 0) + i;
                    }

                    public int prepare(MethodVisitor methodVisitor) {
                        StackSize stackSize;
                        if (!this.instrumentedMethod.isStatic()) {
                            methodVisitor.visitVarInsn(25, 0);
                            methodVisitor.visitVarInsn(58, this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize());
                            stackSize = StackSize.SINGLE;
                        } else {
                            stackSize = StackSize.ZERO;
                        }
                        Iterator it = this.instrumentedMethod.getParameters().iterator();
                        while (it.hasNext()) {
                            ParameterDescription parameterDescription = (ParameterDescription) it.next();
                            Type type = Type.getType(parameterDescription.getType().asErasure().getDescriptor());
                            methodVisitor.visitVarInsn(type.getOpcode(21), parameterDescription.getOffset());
                            methodVisitor.visitVarInsn(type.getOpcode(54), this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize() + parameterDescription.getOffset());
                            stackSize = stackSize.maximum(parameterDescription.getType().getStackSize());
                        }
                        return stackSize.getSize();
                    }
                }
            }
        }

        public interface ForAdvice extends ArgumentHandler {
            int mapped(int i);

            public static abstract class Default implements ForAdvice {
                protected final MethodDescription adviceMethod;
                protected final TypeDefinition exitType;
                protected final MethodDescription instrumentedMethod;
                protected final TreeMap<String, TypeDefinition> namedTypes;

                public int argument(int i) {
                    return i;
                }

                protected Default(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDefinition typeDefinition, TreeMap<String, TypeDefinition> treeMap) {
                    this.instrumentedMethod = methodDescription;
                    this.adviceMethod = methodDescription2;
                    this.exitType = typeDefinition;
                    this.namedTypes = treeMap;
                }

                public int exit() {
                    return this.instrumentedMethod.getStackSize();
                }

                public int named(String str) {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.headMap(str).values());
                }

                public int enter() {
                    return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values());
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ForMethodEnter extends Default {
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass();
                    }

                    public int hashCode() {
                        return 17;
                    }

                    protected ForMethodEnter(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDefinition typeDefinition, TreeMap<String, TypeDefinition> treeMap) {
                        super(methodDescription, methodDescription2, typeDefinition, treeMap);
                    }

                    public int returned() {
                        throw new IllegalStateException("Cannot resolve the return value offset during enter advice");
                    }

                    public int thrown() {
                        throw new IllegalStateException("Cannot resolve the thrown value offset during enter advice");
                    }

                    public int mapped(int i) {
                        return (((this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize()) + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values())) - this.adviceMethod.getStackSize()) + i;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class ForMethodExit extends Default {
                    private final TypeDefinition enterType;
                    private final StackSize throwableSize;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForMethodExit forMethodExit = (ForMethodExit) obj;
                        return this.throwableSize.equals(forMethodExit.throwableSize) && this.enterType.equals(forMethodExit.enterType);
                    }

                    public int hashCode() {
                        return ((527 + this.enterType.hashCode()) * 31) + this.throwableSize.hashCode();
                    }

                    protected ForMethodExit(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDefinition typeDefinition, TreeMap<String, TypeDefinition> treeMap, TypeDefinition typeDefinition2, StackSize stackSize) {
                        super(methodDescription, methodDescription2, typeDefinition, treeMap);
                        this.enterType = typeDefinition2;
                        this.throwableSize = stackSize;
                    }

                    public int returned() {
                        return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize();
                    }

                    public int thrown() {
                        return this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize() + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values()) + this.enterType.getStackSize().getSize() + this.instrumentedMethod.getReturnType().getStackSize().getSize();
                    }

                    public int mapped(int i) {
                        return ((((((this.instrumentedMethod.getStackSize() + this.exitType.getStackSize().getSize()) + StackSize.of((Collection<? extends TypeDefinition>) this.namedTypes.values())) + this.enterType.getStackSize().getSize()) + this.instrumentedMethod.getReturnType().getStackSize().getSize()) + this.throwableSize.getSize()) - this.adviceMethod.getStackSize()) + i;
                    }
                }
            }
        }
    }

    protected interface MethodSizeHandler {
        public static final int UNDEFINED_SIZE = 32767;

        public interface ForAdvice extends MethodSizeHandler {
            void recordMaxima(int i, int i2);

            void requireLocalVariableLengthPadding(int i);

            void requireStackSizePadding(int i);
        }

        public interface ForInstrumentedMethod extends MethodSizeHandler {
            ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape);

            ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape);

            int compoundLocalVariableLength(int i);

            int compoundStackSize(int i);
        }

        public enum NoOp implements ForInstrumentedMethod, ForAdvice {
            INSTANCE;

            public ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            public int compoundLocalVariableLength(int i) {
                return MethodSizeHandler.UNDEFINED_SIZE;
            }

            public int compoundStackSize(int i) {
                return MethodSizeHandler.UNDEFINED_SIZE;
            }

            public void recordMaxima(int i, int i2) {
            }

            public void requireLocalVariableLength(int i) {
            }

            public void requireLocalVariableLengthPadding(int i) {
            }

            public void requireStackSize(int i) {
            }

            public void requireStackSizePadding(int i) {
            }
        }

        void requireLocalVariableLength(int i);

        void requireStackSize(int i);

        public static abstract class Default implements ForInstrumentedMethod {
            protected final List<? extends TypeDescription> initialTypes;
            protected final MethodDescription instrumentedMethod;
            protected int localVariableLength;
            protected final List<? extends TypeDescription> postMethodTypes;
            protected final List<? extends TypeDescription> preMethodTypes;
            protected int stackSize;

            protected Default(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3) {
                this.instrumentedMethod = methodDescription;
                this.initialTypes = list;
                this.preMethodTypes = list2;
                this.postMethodTypes = list3;
            }

            protected static ForInstrumentedMethod of(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, boolean z, int i) {
                if ((i & 3) != 0) {
                    return NoOp.INSTANCE;
                }
                if (z) {
                    return new WithCopiedArguments(methodDescription, list, list2, list3);
                }
                return new WithRetainedArguments(methodDescription, list, list2, list3);
            }

            public ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return new ForAdvice(inDefinedShape, this.instrumentedMethod.getStackSize() + StackSize.of((Collection<? extends TypeDefinition>) this.initialTypes));
            }

            public void requireStackSize(int i) {
                this.stackSize = Math.max(this.stackSize, i);
            }

            public void requireLocalVariableLength(int i) {
                this.localVariableLength = Math.max(this.localVariableLength, i);
            }

            public int compoundStackSize(int i) {
                return Math.max(this.stackSize, i);
            }

            public int compoundLocalVariableLength(int i) {
                return Math.max(this.localVariableLength, i + StackSize.of((Collection<? extends TypeDefinition>) this.postMethodTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.initialTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.preMethodTypes));
            }

            protected static class WithRetainedArguments extends Default {
                protected WithRetainedArguments(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3) {
                    super(methodDescription, list, list2, list3);
                }

                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForAdvice(inDefinedShape, this.instrumentedMethod.getStackSize() + StackSize.of((Collection<? extends TypeDefinition>) this.postMethodTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.initialTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.preMethodTypes));
                }

                public int compoundLocalVariableLength(int i) {
                    return Math.max(this.localVariableLength, i + StackSize.of((Collection<? extends TypeDefinition>) this.postMethodTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.initialTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.preMethodTypes));
                }
            }

            protected static class WithCopiedArguments extends Default {
                protected WithCopiedArguments(MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3) {
                    super(methodDescription, list, list2, list3);
                }

                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForAdvice(inDefinedShape, (this.instrumentedMethod.getStackSize() * 2) + StackSize.of((Collection<? extends TypeDefinition>) this.initialTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.preMethodTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.postMethodTypes));
                }

                public int compoundLocalVariableLength(int i) {
                    return Math.max(this.localVariableLength, i + this.instrumentedMethod.getStackSize() + StackSize.of((Collection<? extends TypeDefinition>) this.postMethodTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.initialTypes) + StackSize.of((Collection<? extends TypeDefinition>) this.preMethodTypes));
                }
            }

            protected class ForAdvice implements ForAdvice {
                private final MethodDescription.InDefinedShape adviceMethod;
                private final int baseLocalVariableLength;
                private int localVariableLengthPadding;
                private int stackSizePadding;

                protected ForAdvice(MethodDescription.InDefinedShape inDefinedShape, int i) {
                    this.adviceMethod = inDefinedShape;
                    this.baseLocalVariableLength = i;
                }

                public void requireStackSize(int i) {
                    Default.this.requireStackSize(i);
                }

                public void requireLocalVariableLength(int i) {
                    Default.this.requireLocalVariableLength(i);
                }

                public void requireStackSizePadding(int i) {
                    this.stackSizePadding = Math.max(this.stackSizePadding, i);
                }

                public void requireLocalVariableLengthPadding(int i) {
                    this.localVariableLengthPadding = Math.max(this.localVariableLengthPadding, i);
                }

                public void recordMaxima(int i, int i2) {
                    Default.this.requireStackSize(i + this.stackSizePadding);
                    Default.this.requireLocalVariableLength((i2 - this.adviceMethod.getStackSize()) + this.baseLocalVariableLength + this.localVariableLengthPadding);
                }
            }
        }
    }

    protected interface StackMapFrameHandler {

        public interface ForAdvice extends StackMapFrameHandler {
        }

        public interface ForInstrumentedMethod extends StackMapFrameHandler {
            ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape);

            ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape);

            int getReaderHint();

            void injectInitializationFrame(MethodVisitor methodVisitor);

            void injectPostCompletionFrame(MethodVisitor methodVisitor);

            void injectStartFrame(MethodVisitor methodVisitor);
        }

        public enum NoOp implements ForInstrumentedMethod, ForAdvice {
            INSTANCE;

            public ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                return this;
            }

            public int getReaderHint() {
                return 4;
            }

            public void injectCompletionFrame(MethodVisitor methodVisitor) {
            }

            public void injectExceptionFrame(MethodVisitor methodVisitor) {
            }

            public void injectInitializationFrame(MethodVisitor methodVisitor) {
            }

            public void injectPostCompletionFrame(MethodVisitor methodVisitor) {
            }

            public void injectReturnFrame(MethodVisitor methodVisitor) {
            }

            public void injectStartFrame(MethodVisitor methodVisitor) {
            }

            public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
            }
        }

        void injectCompletionFrame(MethodVisitor methodVisitor);

        void injectExceptionFrame(MethodVisitor methodVisitor);

        void injectReturnFrame(MethodVisitor methodVisitor);

        void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2);

        public static abstract class Default implements ForInstrumentedMethod {
            protected static final Object[] EMPTY = new Object[0];
            protected int currentFrameDivergence;
            protected final boolean expandFrames;
            protected final List<? extends TypeDescription> initialTypes;
            protected final MethodDescription instrumentedMethod;
            protected final TypeDescription instrumentedType;
            protected final List<? extends TypeDescription> postMethodTypes;
            protected final List<? extends TypeDescription> preMethodTypes;

            protected enum TranslationMode {
                COPY {
                    /* access modifiers changed from: protected */
                    public int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int size = methodDescription.getParameters().size() + (methodDescription.isStatic() ^ true ? 1 : 0);
                        System.arraycopy(objArr, 0, objArr2, 0, size);
                        return size;
                    }

                    /* access modifiers changed from: protected */
                    public boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        return (methodDescription.isConstructor() && Opcodes.UNINITIALIZED_THIS.equals(obj)) || Default.toFrame(typeDescription).equals(obj);
                    }
                },
                ENTER {
                    /* access modifiers changed from: protected */
                    public int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        Object obj;
                        int i = 0;
                        if (!methodDescription.isStatic()) {
                            if (methodDescription.isConstructor()) {
                                obj = Opcodes.UNINITIALIZED_THIS;
                            } else {
                                obj = Default.toFrame(typeDescription);
                            }
                            objArr2[0] = obj;
                            i = 1;
                        }
                        for (TypeDescription frame : methodDescription.getParameters().asTypeList().asErasures()) {
                            objArr2[i] = Default.toFrame(frame);
                            i++;
                        }
                        return i;
                    }

                    /* access modifiers changed from: protected */
                    public boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        if (methodDescription.isConstructor()) {
                            return Opcodes.UNINITIALIZED_THIS.equals(obj);
                        }
                        return Default.toFrame(typeDescription).equals(obj);
                    }
                },
                EXIT {
                    /* access modifiers changed from: protected */
                    public int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2) {
                        int i = 0;
                        if (!methodDescription.isStatic()) {
                            objArr2[0] = Default.toFrame(typeDescription);
                            i = 1;
                        }
                        for (TypeDescription frame : methodDescription.getParameters().asTypeList().asErasures()) {
                            objArr2[i] = Default.toFrame(frame);
                            i++;
                        }
                        return i;
                    }

                    /* access modifiers changed from: protected */
                    public boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj) {
                        return Default.toFrame(typeDescription).equals(obj);
                    }
                };

                /* access modifiers changed from: protected */
                public abstract int copy(TypeDescription typeDescription, MethodDescription methodDescription, MethodDescription methodDescription2, Object[] objArr, Object[] objArr2);

                /* access modifiers changed from: protected */
                public abstract boolean isPossibleThisFrameValue(TypeDescription typeDescription, MethodDescription methodDescription, Object obj);
            }

            protected Default(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, boolean z) {
                this.instrumentedType = typeDescription;
                this.instrumentedMethod = methodDescription;
                this.initialTypes = list;
                this.preMethodTypes = list2;
                this.postMethodTypes = list3;
                this.expandFrames = z;
            }

            protected static ForInstrumentedMethod of(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, boolean z, boolean z2, ClassFileVersion classFileVersion, int i, int i2) {
                if ((i & 2) == 0) {
                    if (!classFileVersion.isLessThan(ClassFileVersion.JAVA_V6)) {
                        boolean z3 = true;
                        if (!z) {
                            if ((i2 & 8) == 0) {
                                z3 = false;
                            }
                            TypeDescription typeDescription2 = typeDescription;
                            MethodDescription methodDescription2 = methodDescription;
                            return new Trivial(typeDescription, methodDescription, z3);
                        }
                        TypeDescription typeDescription3 = typeDescription;
                        MethodDescription methodDescription3 = methodDescription;
                        if (z2) {
                            return new WithPreservedArguments.UsingArgumentCopy(typeDescription, methodDescription, list, list2, list3, (i2 & 8) != 0);
                        }
                        return new WithPreservedArguments.RequiringConsistentShape(typeDescription, methodDescription, list, list2, list3, (i2 & 8) != 0);
                    }
                }
                return NoOp.INSTANCE;
            }

            protected static Object toFrame(TypeDescription typeDescription) {
                if (typeDescription.represents(Boolean.TYPE) || typeDescription.represents(Byte.TYPE) || typeDescription.represents(Short.TYPE) || typeDescription.represents(Character.TYPE) || typeDescription.represents(Integer.TYPE)) {
                    return Opcodes.INTEGER;
                }
                if (typeDescription.represents(Long.TYPE)) {
                    return Opcodes.LONG;
                }
                if (typeDescription.represents(Float.TYPE)) {
                    return Opcodes.FLOAT;
                }
                if (typeDescription.represents(Double.TYPE)) {
                    return Opcodes.DOUBLE;
                }
                return typeDescription.getInternalName();
            }

            public ForAdvice bindEnter(MethodDescription.InDefinedShape inDefinedShape) {
                return new ForAdvice(inDefinedShape, this.initialTypes, this.preMethodTypes, TranslationMode.ENTER);
            }

            public int getReaderHint() {
                return this.expandFrames ? 8 : 0;
            }

            /* access modifiers changed from: protected */
            public void translateFrame(MethodVisitor methodVisitor, TranslationMode translationMode, MethodDescription methodDescription, List<? extends TypeDescription> list, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                Object[] objArr3;
                int i4;
                int i5;
                MethodDescription methodDescription2 = methodDescription;
                int i6 = i;
                int i7 = i2;
                Object[] objArr4 = objArr;
                if (i6 != -1 && i6 != 0) {
                    if (i6 == 1) {
                        this.currentFrameDivergence += i7;
                    } else if (i6 == 2) {
                        int i8 = this.currentFrameDivergence - i7;
                        this.currentFrameDivergence = i8;
                        if (i8 < 0) {
                            throw new IllegalStateException(methodDescription2 + " dropped " + Math.abs(this.currentFrameDivergence) + " implicit frames");
                        }
                    } else if (!(i6 == 3 || i6 == 4)) {
                        throw new IllegalArgumentException("Unexpected frame type: " + i6);
                    }
                    i4 = i7;
                    objArr3 = objArr4;
                } else if (methodDescription.getParameters().size() + (methodDescription.isStatic() ^ true ? 1 : 0) <= i7) {
                    int i9 = 0;
                    if (methodDescription.isStatic()) {
                        TranslationMode translationMode2 = translationMode;
                        i5 = 0;
                    } else {
                        if (translationMode.isPossibleThisFrameValue(this.instrumentedType, this.instrumentedMethod, objArr4[0])) {
                            i5 = 1;
                        } else {
                            throw new IllegalStateException(methodDescription2 + " is inconsistent for 'this' reference: " + objArr4[0]);
                        }
                    }
                    while (i9 < methodDescription.getParameters().size()) {
                        int i10 = i9 + i5;
                        if (toFrame(((ParameterDescription) methodDescription.getParameters().get(i9)).getType().asErasure()).equals(objArr4[i10])) {
                            i9++;
                        } else {
                            throw new IllegalStateException(methodDescription2 + " is inconsistent at " + i9 + ": " + objArr4[i10]);
                        }
                    }
                    int size = ((i7 - (methodDescription.isStatic() ^ true ? 1 : 0)) - methodDescription.getParameters().size()) + (this.instrumentedMethod.isStatic() ^ true ? 1 : 0) + this.instrumentedMethod.getParameters().size() + list.size();
                    Object[] objArr5 = new Object[size];
                    int copy = translationMode.copy(this.instrumentedType, this.instrumentedMethod, methodDescription, objArr, objArr5);
                    for (TypeDescription frame : list) {
                        objArr5[copy] = toFrame(frame);
                        copy++;
                    }
                    int i11 = size - copy;
                    System.arraycopy(objArr4, methodDescription.getParameters().size() + (methodDescription.isStatic() ^ true ? 1 : 0), objArr5, copy, i11);
                    this.currentFrameDivergence = i11;
                    i4 = size;
                    objArr3 = objArr5;
                } else {
                    throw new IllegalStateException("Inconsistent frame length for " + methodDescription2 + ": " + i7);
                }
                methodVisitor.visitFrame(i, i4, objArr3, i3, objArr2);
            }

            /* access modifiers changed from: protected */
            public void injectFullFrame(MethodVisitor methodVisitor, List<? extends TypeDescription> list, List<? extends TypeDescription> list2) {
                int i = 1;
                int size = this.instrumentedMethod.getParameters().size() + (this.instrumentedMethod.isStatic() ^ true ? 1 : 0) + list.size();
                Object[] objArr = new Object[size];
                if (!this.instrumentedMethod.isStatic()) {
                    objArr[0] = toFrame(this.instrumentedType);
                } else {
                    i = 0;
                }
                for (TypeDescription frame : this.instrumentedMethod.getParameters().asTypeList().asErasures()) {
                    objArr[i] = toFrame(frame);
                    i++;
                }
                for (TypeDescription frame2 : list) {
                    objArr[i] = toFrame(frame2);
                    i++;
                }
                int size2 = list2.size();
                Object[] objArr2 = new Object[size2];
                int i2 = 0;
                for (TypeDescription frame3 : list2) {
                    objArr2[i2] = toFrame(frame3);
                    i2++;
                }
                methodVisitor.visitFrame(this.expandFrames ? -1 : 0, size, objArr, size2, objArr2);
                this.currentFrameDivergence = 0;
            }

            protected static class Trivial extends Default {
                public void injectInitializationFrame(MethodVisitor methodVisitor) {
                }

                public void injectStartFrame(MethodVisitor methodVisitor) {
                }

                protected Trivial(TypeDescription typeDescription, MethodDescription methodDescription, boolean z) {
                    super(typeDescription, methodDescription, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), z);
                }

                public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                    methodVisitor.visitFrame(i, i2, objArr, i3, objArr2);
                }

                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    throw new IllegalStateException("Did not expect exit advice " + inDefinedShape + " for " + this.instrumentedMethod);
                }

                public void injectReturnFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect return frame for " + this.instrumentedMethod);
                }

                public void injectExceptionFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect exception frame for " + this.instrumentedMethod);
                }

                public void injectCompletionFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect completion frame for " + this.instrumentedMethod);
                }

                public void injectPostCompletionFrame(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Did not expect post completion frame for " + this.instrumentedMethod);
                }
            }

            protected static abstract class WithPreservedArguments extends Default {
                protected WithPreservedArguments(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, boolean z) {
                    super(typeDescription, methodDescription, list, list2, list3, z);
                }

                public ForAdvice bindExit(MethodDescription.InDefinedShape inDefinedShape) {
                    return new ForAdvice(inDefinedShape, CompoundList.of(this.initialTypes, this.preMethodTypes, this.postMethodTypes), Collections.emptyList(), TranslationMode.EXIT);
                }

                public void injectReturnFrame(MethodVisitor methodVisitor) {
                    List list;
                    if (this.expandFrames || this.currentFrameDivergence != 0) {
                        List of = CompoundList.of(this.initialTypes, this.preMethodTypes);
                        if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                            list = Collections.emptyList();
                        } else {
                            list = Collections.singletonList(this.instrumentedMethod.getReturnType().asErasure());
                        }
                        injectFullFrame(methodVisitor, of, list);
                    } else if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        methodVisitor.visitFrame(3, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                    } else {
                        methodVisitor.visitFrame(4, EMPTY.length, EMPTY, 1, new Object[]{toFrame(this.instrumentedMethod.getReturnType().asErasure())});
                    }
                }

                public void injectExceptionFrame(MethodVisitor methodVisitor) {
                    if (this.expandFrames || this.currentFrameDivergence != 0) {
                        injectFullFrame(methodVisitor, CompoundList.of(this.initialTypes, this.preMethodTypes), Collections.singletonList(TypeDescription.THROWABLE));
                        return;
                    }
                    methodVisitor.visitFrame(4, EMPTY.length, EMPTY, 1, new Object[]{Type.getInternalName(Throwable.class)});
                }

                public void injectCompletionFrame(MethodVisitor methodVisitor) {
                    if (this.expandFrames || this.currentFrameDivergence != 0) {
                        injectFullFrame(methodVisitor, CompoundList.of(this.initialTypes, this.preMethodTypes, this.postMethodTypes), Collections.emptyList());
                        return;
                    }
                    int size = this.postMethodTypes.size();
                    Object[] objArr = new Object[size];
                    int i = 0;
                    for (TypeDescription frame : this.postMethodTypes) {
                        objArr[i] = toFrame(frame);
                        i++;
                    }
                    methodVisitor.visitFrame(1, size, objArr, EMPTY.length, EMPTY);
                }

                public void injectPostCompletionFrame(MethodVisitor methodVisitor) {
                    if (this.expandFrames || this.currentFrameDivergence != 0) {
                        injectFullFrame(methodVisitor, CompoundList.of(this.initialTypes, this.preMethodTypes, this.postMethodTypes), Collections.emptyList());
                        return;
                    }
                    methodVisitor.visitFrame(3, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                }

                public void injectInitializationFrame(MethodVisitor methodVisitor) {
                    if (!this.initialTypes.isEmpty()) {
                        int i = 0;
                        if (this.expandFrames || this.initialTypes.size() >= 4) {
                            int i2 = 1;
                            int size = (this.instrumentedMethod.isStatic() ^ true ? 1 : 0) + this.instrumentedMethod.getParameters().size() + this.initialTypes.size();
                            Object[] objArr = new Object[size];
                            if (this.instrumentedMethod.isConstructor()) {
                                objArr[0] = Opcodes.UNINITIALIZED_THIS;
                            } else if (!this.instrumentedMethod.isStatic()) {
                                objArr[0] = toFrame(this.instrumentedType);
                            } else {
                                i2 = 0;
                            }
                            for (TypeDescription frame : this.instrumentedMethod.getParameters().asTypeList().asErasures()) {
                                objArr[i2] = toFrame(frame);
                                i2++;
                            }
                            for (TypeDescription frame2 : this.initialTypes) {
                                objArr[i2] = toFrame(frame2);
                                i2++;
                            }
                            methodVisitor.visitFrame(this.expandFrames ? -1 : 0, size, objArr, EMPTY.length, EMPTY);
                            return;
                        }
                        int size2 = this.initialTypes.size();
                        Object[] objArr2 = new Object[size2];
                        for (TypeDescription frame3 : this.initialTypes) {
                            objArr2[i] = toFrame(frame3);
                            i++;
                        }
                        methodVisitor.visitFrame(1, size2, objArr2, EMPTY.length, EMPTY);
                    }
                }

                protected static class RequiringConsistentShape extends WithPreservedArguments {
                    public void injectStartFrame(MethodVisitor methodVisitor) {
                    }

                    protected RequiringConsistentShape(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, boolean z) {
                        super(typeDescription, methodDescription, list, list2, list3, z);
                    }

                    public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                        translateFrame(methodVisitor, TranslationMode.COPY, this.instrumentedMethod, CompoundList.of(this.initialTypes, this.preMethodTypes), i, i2, objArr, i3, objArr2);
                    }
                }

                protected static class UsingArgumentCopy extends WithPreservedArguments {
                    protected UsingArgumentCopy(TypeDescription typeDescription, MethodDescription methodDescription, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, List<? extends TypeDescription> list3, boolean z) {
                        super(typeDescription, methodDescription, list, list2, list3, z);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:18:0x007b A[LOOP:0: B:16:0x0075->B:18:0x007b, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f9 A[LOOP:1: B:33:0x00f3->B:35:0x00f9, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:39:0x0115 A[LOOP:2: B:37:0x010f->B:39:0x0115, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:43:0x0131 A[LOOP:3: B:41:0x012b->B:43:0x0131, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:46:0x0149  */
                    /* JADX WARNING: Removed duplicated region for block: B:48:0x0151  */
                    /* JADX WARNING: Removed duplicated region for block: B:54:0x017c A[LOOP:4: B:52:0x0176->B:54:0x017c, LOOP_END] */
                    /* JADX WARNING: Removed duplicated region for block: B:57:0x0190  */
                    /* JADX WARNING: Removed duplicated region for block: B:58:0x0193  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void injectStartFrame(net.bytebuddy.jar.asm.MethodVisitor r11) {
                        /*
                            r10 = this;
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isStatic()
                            r1 = 1
                            if (r0 == 0) goto L_0x0015
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r0 = r0.getParameters()
                            boolean r0 = r0.isEmpty()
                            if (r0 != 0) goto L_0x019d
                        L_0x0015:
                            boolean r0 = r10.expandFrames
                            r2 = 0
                            if (r0 != 0) goto L_0x0097
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isStatic()
                            r0 = r0 ^ r1
                            net.bytebuddy.description.method.MethodDescription r3 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r3 = r3.getParameters()
                            int r3 = r3.size()
                            int r0 = r0 + r3
                            r3 = 4
                            if (r0 >= r3) goto L_0x0097
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isStatic()
                            r0 = r0 ^ r1
                            net.bytebuddy.description.method.MethodDescription r3 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r3 = r3.getParameters()
                            int r3 = r3.size()
                            int r6 = r0 + r3
                            java.lang.Object[] r7 = new java.lang.Object[r6]
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isConstructor()
                            if (r0 == 0) goto L_0x0052
                            java.lang.Integer r0 = net.bytebuddy.jar.asm.Opcodes.UNINITIALIZED_THIS
                            r7[r2] = r0
                        L_0x0050:
                            r2 = 1
                            goto L_0x0063
                        L_0x0052:
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isStatic()
                            if (r0 != 0) goto L_0x0063
                            net.bytebuddy.description.type.TypeDescription r0 = r10.instrumentedType
                            java.lang.Object r0 = toFrame(r0)
                            r7[r2] = r0
                            goto L_0x0050
                        L_0x0063:
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r0 = r0.getParameters()
                            net.bytebuddy.description.type.TypeList$Generic r0 = r0.asTypeList()
                            net.bytebuddy.description.type.TypeList r0 = r0.asErasures()
                            java.util.Iterator r0 = r0.iterator()
                        L_0x0075:
                            boolean r3 = r0.hasNext()
                            if (r3 == 0) goto L_0x008b
                            java.lang.Object r3 = r0.next()
                            net.bytebuddy.description.type.TypeDescription r3 = (net.bytebuddy.description.type.TypeDescription) r3
                            int r4 = r2 + 1
                            java.lang.Object r3 = toFrame(r3)
                            r7[r2] = r3
                            r2 = r4
                            goto L_0x0075
                        L_0x008b:
                            r5 = 1
                            java.lang.Object[] r0 = EMPTY
                            int r8 = r0.length
                            java.lang.Object[] r9 = EMPTY
                            r4 = r11
                            r4.visitFrame(r5, r6, r7, r8, r9)
                            goto L_0x019d
                        L_0x0097:
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isStatic()
                            r3 = 2
                            if (r0 == 0) goto L_0x00a2
                            r0 = 0
                            goto L_0x00a3
                        L_0x00a2:
                            r0 = 2
                        L_0x00a3:
                            net.bytebuddy.description.method.MethodDescription r4 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r4 = r4.getParameters()
                            int r4 = r4.size()
                            int r4 = r4 * 2
                            int r0 = r0 + r4
                            java.util.List r3 = r10.initialTypes
                            int r3 = r3.size()
                            int r0 = r0 + r3
                            java.util.List r3 = r10.preMethodTypes
                            int r3 = r3.size()
                            int r6 = r0 + r3
                            java.lang.Object[] r7 = new java.lang.Object[r6]
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isConstructor()
                            if (r0 == 0) goto L_0x00cf
                            java.lang.Integer r0 = net.bytebuddy.jar.asm.Opcodes.UNINITIALIZED_THIS
                            r7[r2] = r0
                        L_0x00cd:
                            r0 = 1
                            goto L_0x00e1
                        L_0x00cf:
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            boolean r0 = r0.isStatic()
                            if (r0 != 0) goto L_0x00e0
                            net.bytebuddy.description.type.TypeDescription r0 = r10.instrumentedType
                            java.lang.Object r0 = toFrame(r0)
                            r7[r2] = r0
                            goto L_0x00cd
                        L_0x00e0:
                            r0 = 0
                        L_0x00e1:
                            net.bytebuddy.description.method.MethodDescription r3 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r3 = r3.getParameters()
                            net.bytebuddy.description.type.TypeList$Generic r3 = r3.asTypeList()
                            net.bytebuddy.description.type.TypeList r3 = r3.asErasures()
                            java.util.Iterator r3 = r3.iterator()
                        L_0x00f3:
                            boolean r4 = r3.hasNext()
                            if (r4 == 0) goto L_0x0109
                            java.lang.Object r4 = r3.next()
                            net.bytebuddy.description.type.TypeDescription r4 = (net.bytebuddy.description.type.TypeDescription) r4
                            int r5 = r0 + 1
                            java.lang.Object r4 = toFrame(r4)
                            r7[r0] = r4
                            r0 = r5
                            goto L_0x00f3
                        L_0x0109:
                            java.util.List r3 = r10.initialTypes
                            java.util.Iterator r3 = r3.iterator()
                        L_0x010f:
                            boolean r4 = r3.hasNext()
                            if (r4 == 0) goto L_0x0125
                            java.lang.Object r4 = r3.next()
                            net.bytebuddy.description.type.TypeDescription r4 = (net.bytebuddy.description.type.TypeDescription) r4
                            int r5 = r0 + 1
                            java.lang.Object r4 = toFrame(r4)
                            r7[r0] = r4
                            r0 = r5
                            goto L_0x010f
                        L_0x0125:
                            java.util.List r3 = r10.preMethodTypes
                            java.util.Iterator r3 = r3.iterator()
                        L_0x012b:
                            boolean r4 = r3.hasNext()
                            if (r4 == 0) goto L_0x0141
                            java.lang.Object r4 = r3.next()
                            net.bytebuddy.description.type.TypeDescription r4 = (net.bytebuddy.description.type.TypeDescription) r4
                            int r5 = r0 + 1
                            java.lang.Object r4 = toFrame(r4)
                            r7[r0] = r4
                            r0 = r5
                            goto L_0x012b
                        L_0x0141:
                            net.bytebuddy.description.method.MethodDescription r3 = r10.instrumentedMethod
                            boolean r3 = r3.isConstructor()
                            if (r3 == 0) goto L_0x0151
                            int r3 = r0 + 1
                            java.lang.Integer r4 = net.bytebuddy.jar.asm.Opcodes.UNINITIALIZED_THIS
                            r7[r0] = r4
                        L_0x014f:
                            r0 = r3
                            goto L_0x0164
                        L_0x0151:
                            net.bytebuddy.description.method.MethodDescription r3 = r10.instrumentedMethod
                            boolean r3 = r3.isStatic()
                            if (r3 != 0) goto L_0x0164
                            int r3 = r0 + 1
                            net.bytebuddy.description.type.TypeDescription r4 = r10.instrumentedType
                            java.lang.Object r4 = toFrame(r4)
                            r7[r0] = r4
                            goto L_0x014f
                        L_0x0164:
                            net.bytebuddy.description.method.MethodDescription r3 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r3 = r3.getParameters()
                            net.bytebuddy.description.type.TypeList$Generic r3 = r3.asTypeList()
                            net.bytebuddy.description.type.TypeList r3 = r3.asErasures()
                            java.util.Iterator r3 = r3.iterator()
                        L_0x0176:
                            boolean r4 = r3.hasNext()
                            if (r4 == 0) goto L_0x018c
                            java.lang.Object r4 = r3.next()
                            net.bytebuddy.description.type.TypeDescription r4 = (net.bytebuddy.description.type.TypeDescription) r4
                            int r5 = r0 + 1
                            java.lang.Object r4 = toFrame(r4)
                            r7[r0] = r4
                            r0 = r5
                            goto L_0x0176
                        L_0x018c:
                            boolean r0 = r10.expandFrames
                            if (r0 == 0) goto L_0x0193
                            r2 = -1
                            r5 = -1
                            goto L_0x0194
                        L_0x0193:
                            r5 = 0
                        L_0x0194:
                            java.lang.Object[] r0 = EMPTY
                            int r8 = r0.length
                            java.lang.Object[] r9 = EMPTY
                            r4 = r11
                            r4.visitFrame(r5, r6, r7, r8, r9)
                        L_0x019d:
                            net.bytebuddy.description.method.MethodDescription r11 = r10.instrumentedMethod
                            boolean r11 = r11.isStatic()
                            r11 = r11 ^ r1
                            net.bytebuddy.description.method.MethodDescription r0 = r10.instrumentedMethod
                            net.bytebuddy.description.method.ParameterList r0 = r0.getParameters()
                            int r0 = r0.size()
                            int r11 = r11 + r0
                            r10.currentFrameDivergence = r11
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.StackMapFrameHandler.Default.WithPreservedArguments.UsingArgumentCopy.injectStartFrame(net.bytebuddy.jar.asm.MethodVisitor):void");
                    }

                    public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                        Object[] objArr3;
                        int i4;
                        boolean z;
                        Object obj;
                        int i5 = i;
                        int i6 = i2;
                        Object[] objArr4 = objArr;
                        int i7 = 1;
                        if (i5 == -1 || i5 == 0) {
                            int size = (this.instrumentedMethod.isStatic() ^ true ? 1 : 0) + i6 + this.instrumentedMethod.getParameters().size() + this.initialTypes.size() + this.preMethodTypes.size();
                            Object[] objArr5 = new Object[size];
                            if (this.instrumentedMethod.isConstructor()) {
                                int i8 = 0;
                                while (true) {
                                    if (i8 >= i6) {
                                        z = false;
                                        break;
                                    } else if (objArr4[i8] == Opcodes.UNINITIALIZED_THIS) {
                                        z = true;
                                        break;
                                    } else {
                                        i8++;
                                    }
                                }
                                if (z) {
                                    obj = Opcodes.UNINITIALIZED_THIS;
                                } else {
                                    obj = toFrame(this.instrumentedType);
                                }
                                objArr5[0] = obj;
                            } else if (!this.instrumentedMethod.isStatic()) {
                                objArr5[0] = toFrame(this.instrumentedType);
                            } else {
                                i7 = 0;
                            }
                            for (TypeDescription frame : this.instrumentedMethod.getParameters().asTypeList().asErasures()) {
                                objArr5[i7] = toFrame(frame);
                                i7++;
                            }
                            for (TypeDescription frame2 : this.initialTypes) {
                                objArr5[i7] = toFrame(frame2);
                                i7++;
                            }
                            for (TypeDescription frame3 : this.preMethodTypes) {
                                objArr5[i7] = toFrame(frame3);
                                i7++;
                            }
                            System.arraycopy(objArr, 0, objArr5, i7, i2);
                            this.currentFrameDivergence = size;
                            i4 = size;
                            objArr3 = objArr5;
                        } else {
                            if (i5 == 1) {
                                this.currentFrameDivergence += i6;
                            } else if (i5 == 2) {
                                this.currentFrameDivergence -= i6;
                            } else if (!(i5 == 3 || i5 == 4)) {
                                throw new IllegalArgumentException("Unexpected frame type: " + i);
                            }
                            objArr3 = objArr4;
                            i4 = i6;
                        }
                        methodVisitor.visitFrame(i, i4, objArr3, i3, objArr2);
                    }
                }
            }

            protected class ForAdvice implements ForAdvice {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final List<? extends TypeDescription> endTypes;
                protected final List<? extends TypeDescription> startTypes;
                protected final TranslationMode translationMode;

                protected ForAdvice(MethodDescription.InDefinedShape inDefinedShape, List<? extends TypeDescription> list, List<? extends TypeDescription> list2, TranslationMode translationMode2) {
                    this.adviceMethod = inDefinedShape;
                    this.startTypes = list;
                    this.endTypes = list2;
                    this.translationMode = translationMode2;
                }

                public void translateFrame(MethodVisitor methodVisitor, int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                    Default.this.translateFrame(methodVisitor, this.translationMode, this.adviceMethod, this.startTypes, i, i2, objArr, i3, objArr2);
                }

                public void injectReturnFrame(MethodVisitor methodVisitor) {
                    List list;
                    if (Default.this.expandFrames || Default.this.currentFrameDivergence != 0) {
                        Default defaultR = Default.this;
                        List<? extends TypeDescription> list2 = this.startTypes;
                        if (this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            list = Collections.emptyList();
                        } else {
                            list = Collections.singletonList(this.adviceMethod.getReturnType().asErasure());
                        }
                        defaultR.injectFullFrame(methodVisitor, list2, list);
                    } else if (this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                        methodVisitor.visitFrame(3, Default.EMPTY.length, Default.EMPTY, Default.EMPTY.length, Default.EMPTY);
                    } else {
                        methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Default.toFrame(this.adviceMethod.getReturnType().asErasure())});
                    }
                }

                public void injectExceptionFrame(MethodVisitor methodVisitor) {
                    if (Default.this.expandFrames || Default.this.currentFrameDivergence != 0) {
                        Default.this.injectFullFrame(methodVisitor, this.startTypes, Collections.singletonList(TypeDescription.THROWABLE));
                        return;
                    }
                    methodVisitor.visitFrame(4, Default.EMPTY.length, Default.EMPTY, 1, new Object[]{Type.getInternalName(Throwable.class)});
                }

                public void injectCompletionFrame(MethodVisitor methodVisitor) {
                    if (Default.this.expandFrames || Default.this.currentFrameDivergence != 0 || this.endTypes.size() >= 4) {
                        Default.this.injectFullFrame(methodVisitor, CompoundList.of(this.startTypes, this.endTypes), Collections.emptyList());
                    } else if (this.endTypes.isEmpty()) {
                        methodVisitor.visitFrame(3, Default.EMPTY.length, Default.EMPTY, Default.EMPTY.length, Default.EMPTY);
                    } else {
                        int size = this.endTypes.size();
                        Object[] objArr = new Object[size];
                        int i = 0;
                        for (TypeDescription frame : this.endTypes) {
                            objArr[i] = Default.toFrame(frame);
                            i++;
                        }
                        methodVisitor.visitFrame(1, size, objArr, Default.EMPTY.length, Default.EMPTY);
                    }
                }
            }
        }
    }

    public interface ExceptionHandler {

        public enum Default implements ExceptionHandler {
            SUPPRESSING {
                public StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription) {
                    return Removal.SINGLE;
                }
            },
            PRINTING {
                public StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription) {
                    try {
                        return MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(Throwable.class.getMethod("printStackTrace", new Class[0])));
                    } catch (NoSuchMethodException unused) {
                        throw new IllegalStateException("Cannot locate Throwable::printStackTrace");
                    }
                }
            }
        }

        StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription);

        @HashCodeAndEqualsPlugin.Enhance
        public static class Simple implements ExceptionHandler {
            private final StackManipulation stackManipulation;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.stackManipulation.equals(((Simple) obj).stackManipulation);
            }

            public int hashCode() {
                return 527 + this.stackManipulation.hashCode();
            }

            public Simple(StackManipulation stackManipulation2) {
                this.stackManipulation = stackManipulation2;
            }

            public StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription) {
                return this.stackManipulation;
            }
        }
    }

    protected interface Dispatcher {
        public static final AnnotationVisitor IGNORE_ANNOTATION = null;
        public static final MethodVisitor IGNORE_METHOD = null;

        public interface Bound {
            void apply();

            void initialize();

            void prepare();
        }

        public interface Unresolved extends Dispatcher {
            Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved);

            Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved);

            Map<String, TypeDefinition> getNamedTypes();

            boolean isBinary();
        }

        TypeDefinition getAdviceType();

        boolean isAlive();

        public interface SuppressionHandler {

            public interface Bound {
                void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition);

                void onEndWithSkip(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition);

                void onPrepare(MethodVisitor methodVisitor);

                void onStart(MethodVisitor methodVisitor);
            }

            public enum NoOp implements SuppressionHandler, Bound {
                INSTANCE;

                public Bound bind(StackManipulation stackManipulation) {
                    return this;
                }

                public void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                }

                public void onEndWithSkip(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                }

                public void onPrepare(MethodVisitor methodVisitor) {
                }

                public void onStart(MethodVisitor methodVisitor) {
                }
            }

            Bound bind(StackManipulation stackManipulation);

            @HashCodeAndEqualsPlugin.Enhance
            public static class Suppressing implements SuppressionHandler {
                private final TypeDescription suppressedType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.suppressedType.equals(((Suppressing) obj).suppressedType);
                }

                public int hashCode() {
                    return 527 + this.suppressedType.hashCode();
                }

                protected Suppressing(TypeDescription typeDescription) {
                    this.suppressedType = typeDescription;
                }

                protected static SuppressionHandler of(TypeDescription typeDescription) {
                    return typeDescription.represents(NoExceptionHandler.class) ? NoOp.INSTANCE : new Suppressing(typeDescription);
                }

                public Bound bind(StackManipulation stackManipulation) {
                    return new Bound(this.suppressedType, stackManipulation);
                }

                protected static class Bound implements Bound {
                    private final Label endOfMethod = new Label();
                    private final StackManipulation exceptionHandler;
                    private final Label startOfMethod = new Label();
                    private final TypeDescription suppressedType;

                    protected Bound(TypeDescription typeDescription, StackManipulation stackManipulation) {
                        this.suppressedType = typeDescription;
                        this.exceptionHandler = stackManipulation;
                    }

                    public void onPrepare(MethodVisitor methodVisitor) {
                        Label label = this.startOfMethod;
                        Label label2 = this.endOfMethod;
                        methodVisitor.visitTryCatchBlock(label, label2, label2, this.suppressedType.getInternalName());
                    }

                    public void onStart(MethodVisitor methodVisitor) {
                        methodVisitor.visitLabel(this.startOfMethod);
                    }

                    public void onEnd(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                        methodVisitor.visitLabel(this.endOfMethod);
                        forAdvice2.injectExceptionFrame(methodVisitor);
                        forAdvice.requireStackSize(this.exceptionHandler.apply(methodVisitor, context).getMaximalSize() + 1);
                        if (typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Byte.TYPE) || typeDefinition.represents(Short.TYPE) || typeDefinition.represents(Character.TYPE) || typeDefinition.represents(Integer.TYPE)) {
                            methodVisitor.visitInsn(3);
                        } else if (typeDefinition.represents(Long.TYPE)) {
                            methodVisitor.visitInsn(9);
                        } else if (typeDefinition.represents(Float.TYPE)) {
                            methodVisitor.visitInsn(11);
                        } else if (typeDefinition.represents(Double.TYPE)) {
                            methodVisitor.visitInsn(14);
                        } else if (!typeDefinition.represents(Void.TYPE)) {
                            methodVisitor.visitInsn(1);
                        }
                    }

                    public void onEndWithSkip(MethodVisitor methodVisitor, Implementation.Context context, MethodSizeHandler.ForAdvice forAdvice, StackMapFrameHandler.ForAdvice forAdvice2, TypeDefinition typeDefinition) {
                        Label label = new Label();
                        methodVisitor.visitJumpInsn(167, label);
                        onEnd(methodVisitor, context, forAdvice, forAdvice2, typeDefinition);
                        methodVisitor.visitLabel(label);
                        forAdvice2.injectReturnFrame(methodVisitor);
                    }
                }
            }
        }

        public interface RelocationHandler {

            public interface Bound {
                public static final int NO_REQUIRED_SIZE = 0;

                int apply(MethodVisitor methodVisitor, int i);
            }

            public enum Disabled implements RelocationHandler, Bound {
                INSTANCE;

                public int apply(MethodVisitor methodVisitor, int i) {
                    return 0;
                }

                public Bound bind(MethodDescription methodDescription, Relocation relocation) {
                    return this;
                }
            }

            Bound bind(MethodDescription methodDescription, Relocation relocation);

            public interface Relocation {
                void apply(MethodVisitor methodVisitor);

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForLabel implements Relocation {
                    private final Label label;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.label.equals(((ForLabel) obj).label);
                    }

                    public int hashCode() {
                        return 527 + this.label.hashCode();
                    }

                    public ForLabel(Label label2) {
                        this.label = label2;
                    }

                    public void apply(MethodVisitor methodVisitor) {
                        methodVisitor.visitJumpInsn(167, this.label);
                    }
                }
            }

            public enum ForValue implements RelocationHandler {
                INTEGER(21, 154, 153, 0) {
                    /* access modifiers changed from: protected */
                    public void convertValue(MethodVisitor methodVisitor) {
                    }
                },
                LONG(22, 154, 153, 0) {
                    /* access modifiers changed from: protected */
                    public void convertValue(MethodVisitor methodVisitor) {
                        methodVisitor.visitInsn(136);
                    }
                },
                FLOAT(23, 154, 153, 2) {
                    /* access modifiers changed from: protected */
                    public void convertValue(MethodVisitor methodVisitor) {
                        methodVisitor.visitInsn(11);
                        methodVisitor.visitInsn(149);
                    }
                },
                DOUBLE(24, 154, 153, 4) {
                    /* access modifiers changed from: protected */
                    public void convertValue(MethodVisitor methodVisitor) {
                        methodVisitor.visitInsn(14);
                        methodVisitor.visitInsn(151);
                    }
                },
                REFERENCE(25, 199, 198, 0) {
                    /* access modifiers changed from: protected */
                    public void convertValue(MethodVisitor methodVisitor) {
                    }
                };
                
                /* access modifiers changed from: private */
                public final int defaultJump;
                /* access modifiers changed from: private */
                public final int load;
                /* access modifiers changed from: private */
                public final int nonDefaultJump;
                /* access modifiers changed from: private */
                public final int requiredSize;

                /* access modifiers changed from: protected */
                public abstract void convertValue(MethodVisitor methodVisitor);

                private ForValue(int i, int i2, int i3, int i4) {
                    this.load = i;
                    this.defaultJump = i2;
                    this.nonDefaultJump = i3;
                    this.requiredSize = i4;
                }

                protected static RelocationHandler of(TypeDefinition typeDefinition, boolean z) {
                    ForValue forValue;
                    if (typeDefinition.represents(Long.TYPE)) {
                        forValue = LONG;
                    } else if (typeDefinition.represents(Float.TYPE)) {
                        forValue = FLOAT;
                    } else if (typeDefinition.represents(Double.TYPE)) {
                        forValue = DOUBLE;
                    } else if (typeDefinition.represents(Void.TYPE)) {
                        throw new IllegalStateException("Cannot skip on default value for void return type");
                    } else if (typeDefinition.isPrimitive()) {
                        forValue = INTEGER;
                    } else {
                        forValue = REFERENCE;
                    }
                    if (!z) {
                        return forValue;
                    }
                    forValue.getClass();
                    return new Inverted();
                }

                public Bound bind(MethodDescription methodDescription, Relocation relocation) {
                    return new Bound(methodDescription, relocation, false);
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class Inverted implements RelocationHandler {
                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && ForValue.this.equals(ForValue.this);
                    }

                    public int hashCode() {
                        return 527 + ForValue.this.hashCode();
                    }

                    protected Inverted() {
                    }

                    public Bound bind(MethodDescription methodDescription, Relocation relocation) {
                        return new Bound(methodDescription, relocation, true);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class Bound implements Bound {
                    private final MethodDescription instrumentedMethod;
                    private final boolean inverted;
                    private final Relocation relocation;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Bound bound = (Bound) obj;
                        return this.inverted == bound.inverted && ForValue.this.equals(ForValue.this) && this.instrumentedMethod.equals(bound.instrumentedMethod) && this.relocation.equals(bound.relocation);
                    }

                    public int hashCode() {
                        return ((((((527 + this.instrumentedMethod.hashCode()) * 31) + this.relocation.hashCode()) * 31) + (this.inverted ? 1 : 0)) * 31) + ForValue.this.hashCode();
                    }

                    protected Bound(MethodDescription methodDescription, Relocation relocation2, boolean z) {
                        this.instrumentedMethod = methodDescription;
                        this.relocation = relocation2;
                        this.inverted = z;
                    }

                    public int apply(MethodVisitor methodVisitor, int i) {
                        int i2;
                        if (!this.instrumentedMethod.isConstructor()) {
                            methodVisitor.visitVarInsn(ForValue.this.load, i);
                            ForValue.this.convertValue(methodVisitor);
                            Label label = new Label();
                            if (this.inverted) {
                                i2 = ForValue.this.nonDefaultJump;
                            } else {
                                i2 = ForValue.this.defaultJump;
                            }
                            methodVisitor.visitJumpInsn(i2, label);
                            this.relocation.apply(methodVisitor);
                            methodVisitor.visitLabel(label);
                            return ForValue.this.requiredSize;
                        }
                        throw new IllegalStateException("Cannot skip code execution from constructor: " + this.instrumentedMethod);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForType implements RelocationHandler {
                /* access modifiers changed from: private */
                public final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForType) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                protected ForType(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                protected static RelocationHandler of(TypeDescription typeDescription2, TypeDefinition typeDefinition) {
                    if (typeDescription2.represents(Void.TYPE)) {
                        return Disabled.INSTANCE;
                    }
                    if (typeDescription2.represents(OnDefaultValue.class)) {
                        return ForValue.of(typeDefinition, false);
                    }
                    if (typeDescription2.represents(OnNonDefaultValue.class)) {
                        return ForValue.of(typeDefinition, true);
                    }
                    if (!typeDescription2.isPrimitive() && !typeDefinition.isPrimitive()) {
                        return new ForType(typeDescription2);
                    }
                    throw new IllegalStateException("Cannot skip method by instance type for primitive return type " + typeDefinition);
                }

                public Bound bind(MethodDescription methodDescription, Relocation relocation) {
                    return new Bound(methodDescription, relocation);
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class Bound implements Bound {
                    private final MethodDescription instrumentedMethod;
                    private final Relocation relocation;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Bound bound = (Bound) obj;
                        return this.instrumentedMethod.equals(bound.instrumentedMethod) && this.relocation.equals(bound.relocation) && ForType.this.equals(ForType.this);
                    }

                    public int hashCode() {
                        return ((((527 + this.instrumentedMethod.hashCode()) * 31) + this.relocation.hashCode()) * 31) + ForType.this.hashCode();
                    }

                    protected Bound(MethodDescription methodDescription, Relocation relocation2) {
                        this.instrumentedMethod = methodDescription;
                        this.relocation = relocation2;
                    }

                    public int apply(MethodVisitor methodVisitor, int i) {
                        if (!this.instrumentedMethod.isConstructor()) {
                            methodVisitor.visitVarInsn(25, i);
                            methodVisitor.visitTypeInsn(193, ForType.this.typeDescription.getInternalName());
                            Label label = new Label();
                            methodVisitor.visitJumpInsn(153, label);
                            this.relocation.apply(methodVisitor);
                            methodVisitor.visitLabel(label);
                            return 0;
                        }
                        throw new IllegalStateException("Cannot skip code execution from constructor: " + this.instrumentedMethod);
                    }
                }
            }
        }

        public interface Resolved extends Dispatcher {

            public interface ForMethodEnter extends Resolved {
                Map<String, TypeDefinition> getNamedTypes();

                boolean isPrependLineNumber();
            }

            public interface ForMethodExit extends Resolved {
                ArgumentHandler.Factory getArgumentHandlerFactory();

                TypeDescription getThrowable();
            }

            Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation);

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class AbstractBase implements Resolved {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final Map<Integer, OffsetMapping> offsetMappings;
                protected final RelocationHandler relocationHandler;
                protected final SuppressionHandler suppressionHandler;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AbstractBase abstractBase = (AbstractBase) obj;
                    return this.adviceMethod.equals(abstractBase.adviceMethod) && this.offsetMappings.equals(abstractBase.offsetMappings) && this.suppressionHandler.equals(abstractBase.suppressionHandler) && this.relocationHandler.equals(abstractBase.relocationHandler);
                }

                public int hashCode() {
                    return ((((((527 + this.adviceMethod.hashCode()) * 31) + this.offsetMappings.hashCode()) * 31) + this.suppressionHandler.hashCode()) * 31) + this.relocationHandler.hashCode();
                }

                public boolean isAlive() {
                    return true;
                }

                protected AbstractBase(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDescription typeDescription, TypeDescription typeDescription2, OffsetMapping.Factory.AdviceType adviceType) {
                    this.adviceMethod = inDefinedShape;
                    HashMap hashMap = new HashMap();
                    for (OffsetMapping.Factory factory : list) {
                        hashMap.put(TypeDescription.ForLoadedType.of(factory.getAnnotationType()), factory);
                    }
                    this.offsetMappings = new LinkedHashMap();
                    for (ParameterDescription.InDefinedShape inDefinedShape2 : inDefinedShape.getParameters()) {
                        OffsetMapping offsetMapping = null;
                        for (AnnotationDescription annotationDescription : inDefinedShape2.getDeclaredAnnotations()) {
                            OffsetMapping.Factory factory2 = (OffsetMapping.Factory) hashMap.get(annotationDescription.getAnnotationType());
                            if (factory2 != null) {
                                OffsetMapping make = factory2.make(inDefinedShape2, annotationDescription.prepare(factory2.getAnnotationType()), adviceType);
                                if (offsetMapping == null) {
                                    offsetMapping = make;
                                } else {
                                    throw new IllegalStateException(inDefinedShape2 + " is bound to both " + make + " and " + offsetMapping);
                                }
                            }
                        }
                        Map<Integer, OffsetMapping> map = this.offsetMappings;
                        Integer valueOf = Integer.valueOf(inDefinedShape2.getOffset());
                        if (offsetMapping == null) {
                            offsetMapping = new OffsetMapping.ForArgument.Unresolved(inDefinedShape2);
                        }
                        map.put(valueOf, offsetMapping);
                    }
                    this.suppressionHandler = SuppressionHandler.Suppressing.of(typeDescription);
                    this.relocationHandler = RelocationHandler.ForType.of(typeDescription2, inDefinedShape.getReturnType());
                }
            }
        }

        public enum Inactive implements Unresolved, Resolved.ForMethodEnter, Resolved.ForMethodExit, Bound {
            INSTANCE;

            public void apply() {
            }

            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved) {
                return this;
            }

            public Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved) {
                return this;
            }

            public Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                return this;
            }

            public void initialize() {
            }

            public boolean isAlive() {
                return false;
            }

            public boolean isBinary() {
                return false;
            }

            public boolean isPrependLineNumber() {
                return false;
            }

            public void prepare() {
            }

            public TypeDescription getAdviceType() {
                return TypeDescription.VOID;
            }

            public Map<String, TypeDefinition> getNamedTypes() {
                return Collections.emptyMap();
            }

            public TypeDescription getThrowable() {
                return NoExceptionHandler.DESCRIPTION;
            }

            public ArgumentHandler.Factory getArgumentHandlerFactory() {
                return ArgumentHandler.Factory.SIMPLE;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Inlining implements Unresolved {
            protected final MethodDescription.InDefinedShape adviceMethod;
            private final Map<String, TypeDefinition> namedTypes = new HashMap();

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Inlining inlining = (Inlining) obj;
                return this.adviceMethod.equals(inlining.adviceMethod) && this.namedTypes.equals(inlining.namedTypes);
            }

            public int hashCode() {
                return ((527 + this.adviceMethod.hashCode()) * 31) + this.namedTypes.hashCode();
            }

            public boolean isAlive() {
                return true;
            }

            public boolean isBinary() {
                return true;
            }

            protected Inlining(MethodDescription.InDefinedShape inDefinedShape) {
                this.adviceMethod = inDefinedShape;
                for (ParameterDescription parameterDescription : (ParameterList) inDefinedShape.getParameters().filter(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Local.class))) {
                    String value = parameterDescription.getDeclaredAnnotations().ofType(Local.class).loadSilent().value();
                    TypeDefinition put = this.namedTypes.put(value, parameterDescription.getType());
                    if (put != null && !put.equals(parameterDescription.getType())) {
                        throw new IllegalStateException("Local variable for " + value + " is defined with inconsistent types");
                    }
                }
            }

            public TypeDescription getAdviceType() {
                return this.adviceMethod.getReturnType().asErasure();
            }

            public Map<String, TypeDefinition> getNamedTypes() {
                return this.namedTypes;
            }

            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved) {
                return Resolved.ForMethodEnter.of(this.adviceMethod, this.namedTypes, list, unresolved.getAdviceType(), classReader, unresolved.isAlive());
            }

            public Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved) {
                Map<String, TypeDefinition> namedTypes2 = unresolved.getNamedTypes();
                for (Map.Entry next : this.namedTypes.entrySet()) {
                    TypeDefinition typeDefinition = this.namedTypes.get(next.getKey());
                    if (typeDefinition == null) {
                        throw new IllegalStateException(this.adviceMethod + " attempts use of undeclared local variable " + ((String) next.getKey()));
                    } else if (!typeDefinition.equals(next.getValue())) {
                        throw new IllegalStateException(this.adviceMethod + " does not read variable " + ((String) next.getKey()) + " as " + typeDefinition);
                    }
                }
                return Resolved.ForMethodExit.of(this.adviceMethod, namedTypes2, list, classReader, unresolved.getAdviceType());
            }

            protected static abstract class Resolved extends Resolved.AbstractBase {
                protected final ClassReader classReader;

                /* access modifiers changed from: protected */
                public abstract MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2);

                /* access modifiers changed from: protected */
                public abstract Map<Integer, TypeDefinition> resolveInitializationTypes(ArgumentHandler argumentHandler);

                protected Resolved(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDescription typeDescription, TypeDescription typeDescription2, ClassReader classReader2) {
                    super(inDefinedShape, list, typeDescription, typeDescription2, OffsetMapping.Factory.AdviceType.INLINING);
                    this.classReader = classReader2;
                }

                protected class AdviceMethodInliner extends ClassVisitor implements Bound {
                    protected final ArgumentHandler.ForInstrumentedMethod argumentHandler;
                    protected final Assigner assigner;
                    protected final ClassReader classReader;
                    protected final Implementation.Context implementationContext;
                    protected final MethodDescription instrumentedMethod;
                    protected final TypeDescription instrumentedType;
                    protected final List<Label> labels = new ArrayList();
                    protected final MethodSizeHandler.ForInstrumentedMethod methodSizeHandler;
                    protected final MethodVisitor methodVisitor;
                    protected final RelocationHandler.Bound relocationHandler;
                    protected final StackMapFrameHandler.ForInstrumentedMethod stackMapFrameHandler;
                    protected final SuppressionHandler.Bound suppressionHandler;

                    protected AdviceMethodInliner(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor2, Implementation.Context context, Assigner assigner2, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2, ClassReader classReader2) {
                        super(OpenedClassReader.ASM_API);
                        this.instrumentedType = typeDescription;
                        this.instrumentedMethod = methodDescription;
                        this.methodVisitor = methodVisitor2;
                        this.implementationContext = context;
                        this.assigner = assigner2;
                        this.argumentHandler = forInstrumentedMethod;
                        this.methodSizeHandler = forInstrumentedMethod2;
                        this.stackMapFrameHandler = forInstrumentedMethod3;
                        this.suppressionHandler = bound;
                        this.classReader = classReader2;
                        this.relocationHandler = bound2;
                    }

                    public void prepare() {
                        this.classReader.accept(new ExceptionTableExtractor(), 6);
                        this.suppressionHandler.onPrepare(this.methodVisitor);
                    }

                    public void initialize() {
                        for (Map.Entry next : Resolved.this.resolveInitializationTypes(this.argumentHandler).entrySet()) {
                            if (((TypeDefinition) next.getValue()).represents(Boolean.TYPE) || ((TypeDefinition) next.getValue()).represents(Byte.TYPE) || ((TypeDefinition) next.getValue()).represents(Short.TYPE) || ((TypeDefinition) next.getValue()).represents(Character.TYPE) || ((TypeDefinition) next.getValue()).represents(Integer.TYPE)) {
                                this.methodVisitor.visitInsn(3);
                                this.methodVisitor.visitVarInsn(54, ((Integer) next.getKey()).intValue());
                            } else if (((TypeDefinition) next.getValue()).represents(Long.TYPE)) {
                                this.methodVisitor.visitInsn(9);
                                this.methodVisitor.visitVarInsn(55, ((Integer) next.getKey()).intValue());
                            } else if (((TypeDefinition) next.getValue()).represents(Float.TYPE)) {
                                this.methodVisitor.visitInsn(11);
                                this.methodVisitor.visitVarInsn(56, ((Integer) next.getKey()).intValue());
                            } else if (((TypeDefinition) next.getValue()).represents(Double.TYPE)) {
                                this.methodVisitor.visitInsn(14);
                                this.methodVisitor.visitVarInsn(57, ((Integer) next.getKey()).intValue());
                            } else {
                                this.methodVisitor.visitInsn(1);
                                this.methodVisitor.visitVarInsn(58, ((Integer) next.getKey()).intValue());
                            }
                            this.methodSizeHandler.requireStackSize(((TypeDefinition) next.getValue()).getStackSize().getSize());
                        }
                    }

                    public void apply() {
                        this.classReader.accept(this, this.stackMapFrameHandler.getReaderHint() | 2);
                    }

                    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                        String str4 = str;
                        return (!Resolved.this.adviceMethod.getInternalName().equals(str) || !Resolved.this.adviceMethod.getDescriptor().equals(str2)) ? Dispatcher.IGNORE_METHOD : new ExceptionTableSubstitutor(Resolved.this.apply(this.methodVisitor, this.implementationContext, this.assigner, this.argumentHandler, this.methodSizeHandler, this.stackMapFrameHandler, this.instrumentedType, this.instrumentedMethod, this.suppressionHandler, this.relocationHandler));
                    }

                    protected class ExceptionTableExtractor extends ClassVisitor {
                        protected ExceptionTableExtractor() {
                            super(OpenedClassReader.ASM_API);
                        }

                        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                            if (!Resolved.this.adviceMethod.getInternalName().equals(str) || !Resolved.this.adviceMethod.getDescriptor().equals(str2)) {
                                return Dispatcher.IGNORE_METHOD;
                            }
                            AdviceMethodInliner adviceMethodInliner = AdviceMethodInliner.this;
                            return new ExceptionTableCollector(adviceMethodInliner.methodVisitor);
                        }
                    }

                    protected class ExceptionTableCollector extends MethodVisitor {
                        private final MethodVisitor methodVisitor;

                        protected ExceptionTableCollector(MethodVisitor methodVisitor2) {
                            super(OpenedClassReader.ASM_API);
                            this.methodVisitor = methodVisitor2;
                        }

                        public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
                            this.methodVisitor.visitTryCatchBlock(label, label2, label3, str);
                            AdviceMethodInliner.this.labels.addAll(Arrays.asList(new Label[]{label, label2, label3}));
                        }

                        public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
                            return this.methodVisitor.visitTryCatchAnnotation(i, typePath, str, z);
                        }
                    }

                    protected class ExceptionTableSubstitutor extends MethodVisitor {
                        private int index;
                        private final Map<Label, Label> substitutions = new IdentityHashMap();

                        protected ExceptionTableSubstitutor(MethodVisitor methodVisitor) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                        }

                        public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
                            Map<Label, Label> map = this.substitutions;
                            List<Label> list = AdviceMethodInliner.this.labels;
                            int i = this.index;
                            this.index = i + 1;
                            map.put(label, list.get(i));
                            Map<Label, Label> map2 = this.substitutions;
                            List<Label> list2 = AdviceMethodInliner.this.labels;
                            int i2 = this.index;
                            this.index = i2 + 1;
                            map2.put(label2, list2.get(i2));
                            List<Label> list3 = AdviceMethodInliner.this.labels;
                            int i3 = this.index;
                            this.index = i3 + 1;
                            Label label4 = list3.get(i3);
                            this.substitutions.put(label3, label4);
                            ((CodeTranslationVisitor) this.mv).propagateHandler(label4);
                        }

                        public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
                            return Dispatcher.IGNORE_ANNOTATION;
                        }

                        public void visitLabel(Label label) {
                            super.visitLabel(resolve(label));
                        }

                        public void visitJumpInsn(int i, Label label) {
                            super.visitJumpInsn(i, resolve(label));
                        }

                        public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
                            super.visitTableSwitchInsn(i, i2, label, resolve(labelArr));
                        }

                        public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
                            super.visitLookupSwitchInsn(resolve(label), iArr, resolve(labelArr));
                        }

                        private Label[] resolve(Label[] labelArr) {
                            Label[] labelArr2 = new Label[labelArr.length];
                            int length = labelArr.length;
                            int i = 0;
                            int i2 = 0;
                            while (i < length) {
                                labelArr2[i2] = resolve(labelArr[i]);
                                i++;
                                i2++;
                            }
                            return labelArr2;
                        }

                        private Label resolve(Label label) {
                            Label label2 = this.substitutions.get(label);
                            return label2 == null ? label : label2;
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static abstract class ForMethodEnter extends Resolved implements Resolved.ForMethodEnter {
                    private final Map<String, TypeDefinition> namedTypes;
                    private final boolean prependLineNumber;

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
                        ForMethodEnter forMethodEnter = (ForMethodEnter) obj;
                        return this.prependLineNumber == forMethodEnter.prependLineNumber && this.namedTypes.equals(forMethodEnter.namedTypes);
                    }

                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.namedTypes.hashCode()) * 31) + (this.prependLineNumber ? 1 : 0);
                    }

                    /* JADX WARNING: Illegal instructions before constructor call */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    protected ForMethodEnter(net.bytebuddy.description.method.MethodDescription.InDefinedShape r7, java.util.Map<java.lang.String, net.bytebuddy.description.type.TypeDefinition> r8, java.util.List<? extends net.bytebuddy.asm.Advice.OffsetMapping.Factory<?>> r9, net.bytebuddy.description.type.TypeDefinition r10, net.bytebuddy.jar.asm.ClassReader r11) {
                        /*
                            r6 = this;
                            r0 = 13
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory[] r0 = new net.bytebuddy.asm.Advice.OffsetMapping.Factory[r0]
                            net.bytebuddy.asm.Advice$OffsetMapping$ForArgument$Unresolved$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE
                            r2 = 0
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForAllArguments$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForAllArguments.Factory.INSTANCE
                            r2 = 1
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForThisReference$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForThisReference.Factory.INSTANCE
                            r2 = 2
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForField$Unresolved$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved.Factory.INSTANCE
                            r2 = 3
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForOrigin$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Factory.INSTANCE
                            r2 = 4
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForUnusedValue$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForUnusedValue.Factory.INSTANCE
                            r2 = 5
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForStubValue r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForStubValue.INSTANCE
                            r2 = 6
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForThrowable$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForThrowable.Factory.INSTANCE
                            r2 = 7
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory r10 = net.bytebuddy.asm.Advice.OffsetMapping.ForExitValue.Factory.of(r10)
                            r1 = 8
                            r0[r1] = r10
                            net.bytebuddy.asm.Advice$OffsetMapping$ForLocalValue$Factory r10 = new net.bytebuddy.asm.Advice$OffsetMapping$ForLocalValue$Factory
                            r10.<init>(r8)
                            r1 = 9
                            r0[r1] = r10
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory$Illegal r10 = new net.bytebuddy.asm.Advice$OffsetMapping$Factory$Illegal
                            java.lang.Class<net.bytebuddy.asm.Advice$Thrown> r1 = net.bytebuddy.asm.Advice.Thrown.class
                            r10.<init>(r1)
                            r1 = 10
                            r0[r1] = r10
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory$Illegal r10 = new net.bytebuddy.asm.Advice$OffsetMapping$Factory$Illegal
                            java.lang.Class<net.bytebuddy.asm.Advice$Enter> r1 = net.bytebuddy.asm.Advice.Enter.class
                            r10.<init>(r1)
                            r1 = 11
                            r0[r1] = r10
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory$Illegal r10 = new net.bytebuddy.asm.Advice$OffsetMapping$Factory$Illegal
                            java.lang.Class<net.bytebuddy.asm.Advice$Return> r1 = net.bytebuddy.asm.Advice.Return.class
                            r10.<init>(r1)
                            r1 = 12
                            r0[r1] = r10
                            java.util.List r10 = java.util.Arrays.asList(r0)
                            java.util.List r2 = net.bytebuddy.utility.CompoundList.of(r10, r9)
                            net.bytebuddy.description.annotation.AnnotationList r9 = r7.getDeclaredAnnotations()
                            java.lang.Class<net.bytebuddy.asm.Advice$OnMethodEnter> r10 = net.bytebuddy.asm.Advice.OnMethodEnter.class
                            net.bytebuddy.description.annotation.AnnotationDescription$Loadable r9 = r9.ofType(r10)
                            net.bytebuddy.description.method.MethodDescription$InDefinedShape r10 = net.bytebuddy.asm.Advice.SUPPRESS_ENTER
                            net.bytebuddy.description.annotation.AnnotationValue r9 = r9.getValue(r10)
                            java.lang.Class<net.bytebuddy.description.type.TypeDescription> r10 = net.bytebuddy.description.type.TypeDescription.class
                            java.lang.Object r9 = r9.resolve(r10)
                            r3 = r9
                            net.bytebuddy.description.type.TypeDescription r3 = (net.bytebuddy.description.type.TypeDescription) r3
                            net.bytebuddy.description.annotation.AnnotationList r9 = r7.getDeclaredAnnotations()
                            java.lang.Class<net.bytebuddy.asm.Advice$OnMethodEnter> r10 = net.bytebuddy.asm.Advice.OnMethodEnter.class
                            net.bytebuddy.description.annotation.AnnotationDescription$Loadable r9 = r9.ofType(r10)
                            net.bytebuddy.description.method.MethodDescription$InDefinedShape r10 = net.bytebuddy.asm.Advice.SKIP_ON
                            net.bytebuddy.description.annotation.AnnotationValue r9 = r9.getValue(r10)
                            java.lang.Class<net.bytebuddy.description.type.TypeDescription> r10 = net.bytebuddy.description.type.TypeDescription.class
                            java.lang.Object r9 = r9.resolve(r10)
                            r4 = r9
                            net.bytebuddy.description.type.TypeDescription r4 = (net.bytebuddy.description.type.TypeDescription) r4
                            r0 = r6
                            r1 = r7
                            r5 = r11
                            r0.<init>(r1, r2, r3, r4, r5)
                            r6.namedTypes = r8
                            net.bytebuddy.description.annotation.AnnotationList r7 = r7.getDeclaredAnnotations()
                            java.lang.Class<net.bytebuddy.asm.Advice$OnMethodEnter> r8 = net.bytebuddy.asm.Advice.OnMethodEnter.class
                            net.bytebuddy.description.annotation.AnnotationDescription$Loadable r7 = r7.ofType(r8)
                            net.bytebuddy.description.method.MethodDescription$InDefinedShape r8 = net.bytebuddy.asm.Advice.PREPEND_LINE_NUMBER
                            net.bytebuddy.description.annotation.AnnotationValue r7 = r7.getValue(r8)
                            java.lang.Class<java.lang.Boolean> r8 = java.lang.Boolean.class
                            java.lang.Object r7 = r7.resolve(r8)
                            java.lang.Boolean r7 = (java.lang.Boolean) r7
                            boolean r7 = r7.booleanValue()
                            r6.prependLineNumber = r7
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodEnter.<init>(net.bytebuddy.description.method.MethodDescription$InDefinedShape, java.util.Map, java.util.List, net.bytebuddy.description.type.TypeDefinition, net.bytebuddy.jar.asm.ClassReader):void");
                    }

                    protected static Resolved.ForMethodEnter of(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, ClassReader classReader, boolean z) {
                        return z ? new WithRetainedEnterType(inDefinedShape, map, list, typeDefinition, classReader) : new WithDiscardedEnterType(inDefinedShape, map, list, typeDefinition, classReader);
                    }

                    /* access modifiers changed from: protected */
                    public Map<Integer, TypeDefinition> resolveInitializationTypes(ArgumentHandler argumentHandler) {
                        TreeMap treeMap = new TreeMap();
                        for (Map.Entry next : this.namedTypes.entrySet()) {
                            treeMap.put(Integer.valueOf(argumentHandler.named((String) next.getKey())), next.getValue());
                        }
                        return treeMap;
                    }

                    public Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        MethodDescription methodDescription2 = methodDescription;
                        return new AdviceMethodInliner(typeDescription, methodDescription2, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, forInstrumentedMethod3, this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription2, relocation), this.classReader);
                    }

                    public boolean isPrependLineNumber() {
                        return this.prependLineNumber;
                    }

                    public Map<String, TypeDefinition> getNamedTypes() {
                        return this.namedTypes;
                    }

                    /* access modifiers changed from: protected */
                    public MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        return doApply(methodVisitor, context, assigner, forInstrumentedMethod.bindEnter(this.adviceMethod), forInstrumentedMethod2.bindEnter(this.adviceMethod), forInstrumentedMethod3.bindEnter(this.adviceMethod), typeDescription, methodDescription, bound, bound2);
                    }

                    /* access modifiers changed from: protected */
                    public MethodVisitor doApply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        HashMap hashMap = new HashMap();
                        for (Map.Entry entry : this.offsetMappings.entrySet()) {
                            hashMap.put(entry.getKey(), ((OffsetMapping) entry.getValue()).resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.ENTER));
                        }
                        return new CodeTranslationVisitor.ForMethodEnter(methodVisitor, context, forAdvice, forAdvice2, forAdvice3, methodDescription, this.adviceMethod, hashMap, bound, bound2);
                    }

                    protected static class WithRetainedEnterType extends ForMethodEnter {
                        protected WithRetainedEnterType(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, ClassReader classReader) {
                            super(inDefinedShape, map, list, typeDefinition, classReader);
                        }

                        public TypeDefinition getAdviceType() {
                            return this.adviceMethod.getReturnType();
                        }
                    }

                    protected static class WithDiscardedEnterType extends ForMethodEnter {
                        protected WithDiscardedEnterType(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, ClassReader classReader) {
                            super(inDefinedShape, map, list, typeDefinition, classReader);
                        }

                        public TypeDefinition getAdviceType() {
                            return TypeDescription.VOID;
                        }

                        /* access modifiers changed from: protected */
                        public MethodVisitor doApply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                            forAdvice2.requireLocalVariableLengthPadding(this.adviceMethod.getReturnType().getStackSize().getSize());
                            return super.doApply(methodVisitor, context, assigner, forAdvice, forAdvice2, forAdvice3, typeDescription, methodDescription, bound, bound2);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static abstract class ForMethodExit extends Resolved implements Resolved.ForMethodExit {
                    private final boolean backupArguments;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.backupArguments == ((ForMethodExit) obj).backupArguments;
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + (this.backupArguments ? 1 : 0);
                    }

                    /* JADX WARNING: Illegal instructions before constructor call */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    protected ForMethodExit(net.bytebuddy.description.method.MethodDescription.InDefinedShape r7, java.util.Map<java.lang.String, net.bytebuddy.description.type.TypeDefinition> r8, java.util.List<? extends net.bytebuddy.asm.Advice.OffsetMapping.Factory<?>> r9, net.bytebuddy.jar.asm.ClassReader r10, net.bytebuddy.description.type.TypeDefinition r11) {
                        /*
                            r6 = this;
                            r0 = 12
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory[] r0 = new net.bytebuddy.asm.Advice.OffsetMapping.Factory[r0]
                            net.bytebuddy.asm.Advice$OffsetMapping$ForArgument$Unresolved$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE
                            r2 = 0
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForAllArguments$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForAllArguments.Factory.INSTANCE
                            r2 = 1
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForThisReference$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForThisReference.Factory.INSTANCE
                            r2 = 2
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForField$Unresolved$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForField.Unresolved.Factory.INSTANCE
                            r2 = 3
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForOrigin$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Factory.INSTANCE
                            r2 = 4
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForUnusedValue$Factory r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForUnusedValue.Factory.INSTANCE
                            r2 = 5
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$ForStubValue r1 = net.bytebuddy.asm.Advice.OffsetMapping.ForStubValue.INSTANCE
                            r2 = 6
                            r0[r2] = r1
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory r11 = net.bytebuddy.asm.Advice.OffsetMapping.ForEnterValue.Factory.of(r11)
                            r1 = 7
                            r0[r1] = r11
                            net.bytebuddy.description.type.TypeDescription$Generic r11 = r7.getReturnType()
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory r11 = net.bytebuddy.asm.Advice.OffsetMapping.ForExitValue.Factory.of(r11)
                            r1 = 8
                            r0[r1] = r11
                            net.bytebuddy.asm.Advice$OffsetMapping$ForLocalValue$Factory r11 = new net.bytebuddy.asm.Advice$OffsetMapping$ForLocalValue$Factory
                            r11.<init>(r8)
                            r8 = 9
                            r0[r8] = r11
                            net.bytebuddy.asm.Advice$OffsetMapping$ForReturnValue$Factory r8 = net.bytebuddy.asm.Advice.OffsetMapping.ForReturnValue.Factory.INSTANCE
                            r11 = 10
                            r0[r11] = r8
                            net.bytebuddy.asm.Advice$OffsetMapping$Factory r8 = net.bytebuddy.asm.Advice.OffsetMapping.ForThrowable.Factory.of(r7)
                            r11 = 11
                            r0[r11] = r8
                            java.util.List r8 = java.util.Arrays.asList(r0)
                            java.util.List r2 = net.bytebuddy.utility.CompoundList.of(r8, r9)
                            net.bytebuddy.description.annotation.AnnotationList r8 = r7.getDeclaredAnnotations()
                            java.lang.Class<net.bytebuddy.asm.Advice$OnMethodExit> r9 = net.bytebuddy.asm.Advice.OnMethodExit.class
                            net.bytebuddy.description.annotation.AnnotationDescription$Loadable r8 = r8.ofType(r9)
                            net.bytebuddy.description.method.MethodDescription$InDefinedShape r9 = net.bytebuddy.asm.Advice.SUPPRESS_EXIT
                            net.bytebuddy.description.annotation.AnnotationValue r8 = r8.getValue(r9)
                            java.lang.Class<net.bytebuddy.description.type.TypeDescription> r9 = net.bytebuddy.description.type.TypeDescription.class
                            java.lang.Object r8 = r8.resolve(r9)
                            r3 = r8
                            net.bytebuddy.description.type.TypeDescription r3 = (net.bytebuddy.description.type.TypeDescription) r3
                            net.bytebuddy.description.annotation.AnnotationList r8 = r7.getDeclaredAnnotations()
                            java.lang.Class<net.bytebuddy.asm.Advice$OnMethodExit> r9 = net.bytebuddy.asm.Advice.OnMethodExit.class
                            net.bytebuddy.description.annotation.AnnotationDescription$Loadable r8 = r8.ofType(r9)
                            net.bytebuddy.description.method.MethodDescription$InDefinedShape r9 = net.bytebuddy.asm.Advice.REPEAT_ON
                            net.bytebuddy.description.annotation.AnnotationValue r8 = r8.getValue(r9)
                            java.lang.Class<net.bytebuddy.description.type.TypeDescription> r9 = net.bytebuddy.description.type.TypeDescription.class
                            java.lang.Object r8 = r8.resolve(r9)
                            r4 = r8
                            net.bytebuddy.description.type.TypeDescription r4 = (net.bytebuddy.description.type.TypeDescription) r4
                            r0 = r6
                            r1 = r7
                            r5 = r10
                            r0.<init>(r1, r2, r3, r4, r5)
                            net.bytebuddy.description.annotation.AnnotationList r7 = r7.getDeclaredAnnotations()
                            java.lang.Class<net.bytebuddy.asm.Advice$OnMethodExit> r8 = net.bytebuddy.asm.Advice.OnMethodExit.class
                            net.bytebuddy.description.annotation.AnnotationDescription$Loadable r7 = r7.ofType(r8)
                            net.bytebuddy.description.method.MethodDescription$InDefinedShape r8 = net.bytebuddy.asm.Advice.BACKUP_ARGUMENTS
                            net.bytebuddy.description.annotation.AnnotationValue r7 = r7.getValue(r8)
                            java.lang.Class<java.lang.Boolean> r8 = java.lang.Boolean.class
                            java.lang.Object r7 = r7.resolve(r8)
                            java.lang.Boolean r7 = (java.lang.Boolean) r7
                            boolean r7 = r7.booleanValue()
                            r6.backupArguments = r7
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.Dispatcher.Inlining.Resolved.ForMethodExit.<init>(net.bytebuddy.description.method.MethodDescription$InDefinedShape, java.util.Map, java.util.List, net.bytebuddy.jar.asm.ClassReader, net.bytebuddy.description.type.TypeDefinition):void");
                    }

                    protected static Resolved.ForMethodExit of(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                        TypeDescription typeDescription = (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class);
                        return typeDescription.represents(NoExceptionHandler.class) ? new WithoutExceptionHandler(inDefinedShape, map, list, classReader, typeDefinition) : new WithExceptionHandler(inDefinedShape, map, list, classReader, typeDefinition, typeDescription);
                    }

                    /* access modifiers changed from: protected */
                    public Map<Integer, TypeDefinition> resolveInitializationTypes(ArgumentHandler argumentHandler) {
                        if (this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            return Collections.emptyMap();
                        }
                        return Collections.singletonMap(Integer.valueOf(argumentHandler.exit()), this.adviceMethod.getReturnType());
                    }

                    /* access modifiers changed from: protected */
                    public MethodVisitor apply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        return doApply(methodVisitor, context, assigner, forInstrumentedMethod.bindExit(this.adviceMethod, getThrowable().represents(NoExceptionHandler.class)), forInstrumentedMethod2.bindExit(this.adviceMethod), forInstrumentedMethod3.bindExit(this.adviceMethod), typeDescription, methodDescription, bound, bound2);
                    }

                    private MethodVisitor doApply(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, TypeDescription typeDescription, MethodDescription methodDescription, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        HashMap hashMap = new HashMap();
                        for (Map.Entry entry : this.offsetMappings.entrySet()) {
                            hashMap.put(entry.getKey(), ((OffsetMapping) entry.getValue()).resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.EXIT));
                        }
                        return new CodeTranslationVisitor.ForMethodExit(methodVisitor, context, forAdvice, forAdvice2, forAdvice3, methodDescription, this.adviceMethod, hashMap, bound, bound2);
                    }

                    public ArgumentHandler.Factory getArgumentHandlerFactory() {
                        return this.backupArguments ? ArgumentHandler.Factory.COPYING : ArgumentHandler.Factory.SIMPLE;
                    }

                    public TypeDefinition getAdviceType() {
                        return this.adviceMethod.getReturnType();
                    }

                    public Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        MethodDescription methodDescription2 = methodDescription;
                        return new AdviceMethodInliner(typeDescription, methodDescription2, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, forInstrumentedMethod3, this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription2, relocation), this.classReader);
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    protected static class WithExceptionHandler extends ForMethodExit {
                        private final TypeDescription throwable;

                        public boolean equals(Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.throwable.equals(((WithExceptionHandler) obj).throwable);
                        }

                        public int hashCode() {
                            return (super.hashCode() * 31) + this.throwable.hashCode();
                        }

                        protected WithExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition, TypeDescription typeDescription) {
                            super(inDefinedShape, map, list, classReader, typeDefinition);
                            this.throwable = typeDescription;
                        }

                        public TypeDescription getThrowable() {
                            return this.throwable;
                        }
                    }

                    protected static class WithoutExceptionHandler extends ForMethodExit {
                        protected WithoutExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, TypeDefinition typeDefinition) {
                            super(inDefinedShape, map, list, classReader, typeDefinition);
                        }

                        public TypeDescription getThrowable() {
                            return NoExceptionHandler.DESCRIPTION;
                        }
                    }
                }
            }

            protected static abstract class CodeTranslationVisitor extends MethodVisitor {
                protected final MethodDescription.InDefinedShape adviceMethod;
                protected final ArgumentHandler.ForAdvice argumentHandler;
                protected final Label endOfMethod = new Label();
                protected final Implementation.Context implementationContext;
                protected final MethodSizeHandler.ForAdvice methodSizeHandler;
                protected final MethodVisitor methodVisitor;
                private final Map<Integer, OffsetMapping.Target> offsetMappings;
                private final RelocationHandler.Bound relocationHandler;
                protected final StackMapFrameHandler.ForAdvice stackMapFrameHandler;
                private final SuppressionHandler.Bound suppressionHandler;

                /* access modifiers changed from: protected */
                public abstract int getReturnValueOffset();

                public void visitAnnotableParameterCount(int i, boolean z) {
                }

                public void visitAttribute(Attribute attribute) {
                }

                public void visitParameter(String str, int i) {
                }

                protected CodeTranslationVisitor(MethodVisitor methodVisitor2, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, MethodDescription methodDescription, MethodDescription.InDefinedShape inDefinedShape, Map<Integer, OffsetMapping.Target> map, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                    super(OpenedClassReader.ASM_API, new StackAwareMethodVisitor(methodVisitor2, methodDescription));
                    this.methodVisitor = methodVisitor2;
                    this.implementationContext = context;
                    this.argumentHandler = forAdvice;
                    this.methodSizeHandler = forAdvice2;
                    this.stackMapFrameHandler = forAdvice3;
                    this.adviceMethod = inDefinedShape;
                    this.offsetMappings = map;
                    this.suppressionHandler = bound;
                    this.relocationHandler = bound2;
                }

                /* access modifiers changed from: protected */
                public void propagateHandler(Label label) {
                    ((StackAwareMethodVisitor) this.mv).register(label, Collections.singletonList(StackSize.SINGLE));
                }

                public AnnotationVisitor visitAnnotationDefault() {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                    return Dispatcher.IGNORE_ANNOTATION;
                }

                public void visitCode() {
                    this.suppressionHandler.onStart(this.methodVisitor);
                }

                public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                    this.stackMapFrameHandler.translateFrame(this.methodVisitor, i, i2, objArr, i3, objArr2);
                }

                public void visitVarInsn(int i, int i2) {
                    StackSize stackSize;
                    StackManipulation stackManipulation;
                    OffsetMapping.Target target = this.offsetMappings.get(Integer.valueOf(i2));
                    if (target != null) {
                        switch (i) {
                            case 21:
                            case 23:
                            case 25:
                                stackManipulation = target.resolveRead();
                                stackSize = StackSize.SINGLE;
                                break;
                            case 22:
                            case 24:
                                stackManipulation = target.resolveRead();
                                stackSize = StackSize.DOUBLE;
                                break;
                            default:
                                switch (i) {
                                    case 54:
                                    case 55:
                                    case 56:
                                    case 57:
                                    case 58:
                                        stackManipulation = target.resolveWrite();
                                        stackSize = StackSize.ZERO;
                                        break;
                                    default:
                                        throw new IllegalStateException("Unexpected opcode: " + i);
                                }
                        }
                        this.methodSizeHandler.requireStackSizePadding(stackManipulation.apply(this.mv, this.implementationContext).getMaximalSize() - stackSize.getSize());
                        return;
                    }
                    this.mv.visitVarInsn(i, this.argumentHandler.mapped(i2));
                }

                public void visitIincInsn(int i, int i2) {
                    OffsetMapping.Target target = this.offsetMappings.get(Integer.valueOf(i));
                    if (target != null) {
                        this.methodSizeHandler.requireStackSizePadding(target.resolveIncrement(i2).apply(this.mv, this.implementationContext).getMaximalSize());
                    } else {
                        this.mv.visitIincInsn(this.argumentHandler.mapped(i), i2);
                    }
                }

                public void visitInsn(int i) {
                    switch (i) {
                        case 172:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(54, 21, StackSize.SINGLE));
                            break;
                        case 173:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(55, 22, StackSize.DOUBLE));
                            break;
                        case 174:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(56, 23, StackSize.SINGLE));
                            break;
                        case 175:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(57, 24, StackSize.DOUBLE));
                            break;
                        case 176:
                            this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(58, 25, StackSize.SINGLE));
                            break;
                        case 177:
                            ((StackAwareMethodVisitor) this.mv).drainStack();
                            break;
                        default:
                            this.mv.visitInsn(i);
                            return;
                    }
                    this.mv.visitJumpInsn(167, this.endOfMethod);
                }

                public void visitEnd() {
                    this.suppressionHandler.onEnd(this.methodVisitor, this.implementationContext, this.methodSizeHandler, this.stackMapFrameHandler, this.adviceMethod.getReturnType());
                    this.methodVisitor.visitLabel(this.endOfMethod);
                    if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(54, getReturnValueOffset());
                    } else if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(55, getReturnValueOffset());
                    } else if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(56, getReturnValueOffset());
                    } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(57, getReturnValueOffset());
                    } else if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(58, getReturnValueOffset());
                    }
                    this.methodSizeHandler.requireStackSize(this.relocationHandler.apply(this.methodVisitor, getReturnValueOffset()));
                    this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor);
                }

                public void visitMaxs(int i, int i2) {
                    this.methodSizeHandler.recordMaxima(i, i2);
                }

                protected static class ForMethodEnter extends CodeTranslationVisitor {
                    protected ForMethodEnter(MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, MethodDescription methodDescription, MethodDescription.InDefinedShape inDefinedShape, Map<Integer, OffsetMapping.Target> map, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        super(methodVisitor, context, forAdvice, forAdvice2, forAdvice3, methodDescription, inDefinedShape, map, bound, bound2);
                    }

                    /* access modifiers changed from: protected */
                    public int getReturnValueOffset() {
                        return this.argumentHandler.enter();
                    }
                }

                protected static class ForMethodExit extends CodeTranslationVisitor {
                    protected ForMethodExit(MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, MethodDescription methodDescription, MethodDescription.InDefinedShape inDefinedShape, Map<Integer, OffsetMapping.Target> map, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        super(methodVisitor, context, forAdvice, forAdvice2, forAdvice3, methodDescription, inDefinedShape, map, bound, bound2);
                    }

                    /* access modifiers changed from: protected */
                    public int getReturnValueOffset() {
                        return this.argumentHandler.exit();
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Delegating implements Unresolved {
            protected final MethodDescription.InDefinedShape adviceMethod;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.adviceMethod.equals(((Delegating) obj).adviceMethod);
            }

            public int hashCode() {
                return 527 + this.adviceMethod.hashCode();
            }

            public boolean isAlive() {
                return true;
            }

            public boolean isBinary() {
                return false;
            }

            protected Delegating(MethodDescription.InDefinedShape inDefinedShape) {
                this.adviceMethod = inDefinedShape;
            }

            public TypeDescription getAdviceType() {
                return this.adviceMethod.getReturnType().asErasure();
            }

            public Map<String, TypeDefinition> getNamedTypes() {
                return Collections.emptyMap();
            }

            public Resolved.ForMethodEnter asMethodEnter(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved) {
                return Resolved.ForMethodEnter.of(this.adviceMethod, list, unresolved.getAdviceType(), unresolved.isAlive());
            }

            public Resolved.ForMethodExit asMethodExit(List<? extends OffsetMapping.Factory<?>> list, ClassReader classReader, Unresolved unresolved) {
                Map<String, TypeDefinition> namedTypes = unresolved.getNamedTypes();
                for (ParameterDescription parameterDescription : (ParameterList) this.adviceMethod.getParameters().filter(ElementMatchers.isAnnotatedWith((Class<? extends Annotation>) Local.class))) {
                    String value = parameterDescription.getDeclaredAnnotations().ofType(Local.class).loadSilent().value();
                    TypeDefinition typeDefinition = namedTypes.get(value);
                    if (typeDefinition == null) {
                        throw new IllegalStateException(this.adviceMethod + " attempts use of undeclared local variable " + value);
                    } else if (!typeDefinition.equals(parameterDescription.getType())) {
                        throw new IllegalStateException(this.adviceMethod + " does not read variable " + value + " as " + typeDefinition);
                    }
                }
                return Resolved.ForMethodExit.of(this.adviceMethod, namedTypes, list, unresolved.getAdviceType());
            }

            protected static abstract class Resolved extends Resolved.AbstractBase {
                /* access modifiers changed from: protected */
                public abstract Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation);

                protected Resolved(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDescription typeDescription, TypeDescription typeDescription2) {
                    super(inDefinedShape, list, typeDescription, typeDescription2, OffsetMapping.Factory.AdviceType.DELEGATION);
                }

                public Bound bind(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                    if (this.adviceMethod.isVisibleTo(typeDescription)) {
                        return resolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod, forInstrumentedMethod2, forInstrumentedMethod3, stackManipulation, relocation);
                    }
                    throw new IllegalStateException(this.adviceMethod + " is not visible to " + methodDescription.getDeclaringType());
                }

                protected static abstract class AdviceMethodWriter implements Bound {
                    private static final int EMPTY = 0;
                    protected final MethodDescription.InDefinedShape adviceMethod;
                    protected final ArgumentHandler.ForAdvice argumentHandler;
                    protected final Implementation.Context implementationContext;
                    protected final MethodSizeHandler.ForAdvice methodSizeHandler;
                    protected final MethodVisitor methodVisitor;
                    private final List<OffsetMapping.Target> offsetMappings;
                    private final RelocationHandler.Bound relocationHandler;
                    protected final StackMapFrameHandler.ForAdvice stackMapFrameHandler;
                    private final SuppressionHandler.Bound suppressionHandler;

                    /* access modifiers changed from: protected */
                    public abstract int getReturnValueOffset();

                    protected AdviceMethodWriter(MethodDescription.InDefinedShape inDefinedShape, List<OffsetMapping.Target> list, MethodVisitor methodVisitor2, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        this.adviceMethod = inDefinedShape;
                        this.offsetMappings = list;
                        this.methodVisitor = methodVisitor2;
                        this.implementationContext = context;
                        this.argumentHandler = forAdvice;
                        this.methodSizeHandler = forAdvice2;
                        this.stackMapFrameHandler = forAdvice3;
                        this.suppressionHandler = bound;
                        this.relocationHandler = bound2;
                    }

                    public void prepare() {
                        this.suppressionHandler.onPrepare(this.methodVisitor);
                    }

                    public void apply() {
                        this.suppressionHandler.onStart(this.methodVisitor);
                        int i = 0;
                        int i2 = 0;
                        int i3 = 0;
                        for (OffsetMapping.Target resolveRead : this.offsetMappings) {
                            i += ((ParameterDescription.InDefinedShape) this.adviceMethod.getParameters().get(i2)).getType().getStackSize().getSize();
                            i3 = Math.max(i3, resolveRead.resolveRead().apply(this.methodVisitor, this.implementationContext).getMaximalSize() + i);
                            i2++;
                        }
                        this.methodVisitor.visitMethodInsn(184, this.adviceMethod.getDeclaringType().getInternalName(), this.adviceMethod.getInternalName(), this.adviceMethod.getDescriptor(), false);
                        SuppressionHandler.Bound bound = this.suppressionHandler;
                        MethodVisitor methodVisitor2 = this.methodVisitor;
                        Implementation.Context context = this.implementationContext;
                        MethodSizeHandler.ForAdvice forAdvice = this.methodSizeHandler;
                        bound.onEndWithSkip(methodVisitor2, context, forAdvice, this.stackMapFrameHandler, this.adviceMethod.getReturnType());
                        if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                            this.methodVisitor.visitVarInsn(54, getReturnValueOffset());
                        } else if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                            this.methodVisitor.visitVarInsn(55, getReturnValueOffset());
                        } else if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                            this.methodVisitor.visitVarInsn(56, getReturnValueOffset());
                        } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                            this.methodVisitor.visitVarInsn(57, getReturnValueOffset());
                        } else if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                            this.methodVisitor.visitVarInsn(58, getReturnValueOffset());
                        }
                        this.methodSizeHandler.requireStackSize(this.relocationHandler.apply(this.methodVisitor, getReturnValueOffset()));
                        this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor);
                        this.methodSizeHandler.recordMaxima(Math.max(i3, this.adviceMethod.getReturnType().getStackSize().getSize()), 0);
                    }

                    protected static class ForMethodEnter extends AdviceMethodWriter {
                        public void initialize() {
                        }

                        protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                            super(inDefinedShape, list, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2);
                        }

                        /* access modifiers changed from: protected */
                        public int getReturnValueOffset() {
                            return this.argumentHandler.enter();
                        }
                    }

                    protected static class ForMethodExit extends AdviceMethodWriter {
                        protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, List<OffsetMapping.Target> list, MethodVisitor methodVisitor, Implementation.Context context, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                            super(inDefinedShape, list, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2);
                        }

                        public void initialize() {
                            if (this.adviceMethod.getReturnType().represents(Boolean.TYPE) || this.adviceMethod.getReturnType().represents(Byte.TYPE) || this.adviceMethod.getReturnType().represents(Short.TYPE) || this.adviceMethod.getReturnType().represents(Character.TYPE) || this.adviceMethod.getReturnType().represents(Integer.TYPE)) {
                                this.methodVisitor.visitInsn(3);
                                this.methodVisitor.visitVarInsn(54, this.argumentHandler.exit());
                            } else if (this.adviceMethod.getReturnType().represents(Long.TYPE)) {
                                this.methodVisitor.visitInsn(9);
                                this.methodVisitor.visitVarInsn(55, this.argumentHandler.exit());
                            } else if (this.adviceMethod.getReturnType().represents(Float.TYPE)) {
                                this.methodVisitor.visitInsn(11);
                                this.methodVisitor.visitVarInsn(56, this.argumentHandler.exit());
                            } else if (this.adviceMethod.getReturnType().represents(Double.TYPE)) {
                                this.methodVisitor.visitInsn(14);
                                this.methodVisitor.visitVarInsn(57, this.argumentHandler.exit());
                            } else if (!this.adviceMethod.getReturnType().represents(Void.TYPE)) {
                                this.methodVisitor.visitInsn(1);
                                this.methodVisitor.visitVarInsn(58, this.argumentHandler.exit());
                            }
                            this.methodSizeHandler.requireStackSize(this.adviceMethod.getReturnType().getStackSize().getSize());
                        }

                        /* access modifiers changed from: protected */
                        public int getReturnValueOffset() {
                            return this.argumentHandler.exit();
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static abstract class ForMethodEnter extends Resolved implements Resolved.ForMethodEnter {
                    private final boolean prependLineNumber;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.prependLineNumber == ((ForMethodEnter) obj).prependLineNumber;
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + (this.prependLineNumber ? 1 : 0);
                    }

                    protected ForMethodEnter(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                        super(inDefinedShape, CompoundList.of(Arrays.asList(new OffsetMapping.Factory[]{OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, OffsetMapping.ForExitValue.Factory.of(typeDefinition), new OffsetMapping.Factory.Illegal(Thrown.class), new OffsetMapping.Factory.Illegal(Enter.class), new OffsetMapping.Factory.Illegal(Local.class), new OffsetMapping.Factory.Illegal(Return.class)}), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SUPPRESS_ENTER).resolve(TypeDescription.class), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.SKIP_ON).resolve(TypeDescription.class));
                        this.prependLineNumber = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodEnter.class).getValue(Advice.PREPEND_LINE_NUMBER).resolve(Boolean.class)).booleanValue();
                    }

                    protected static Resolved.ForMethodEnter of(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, boolean z) {
                        return z ? new WithRetainedEnterType(inDefinedShape, list, typeDefinition) : new WithDiscardedEnterType(inDefinedShape, list, typeDefinition);
                    }

                    public boolean isPrependLineNumber() {
                        return this.prependLineNumber;
                    }

                    public Map<String, TypeDefinition> getNamedTypes() {
                        return Collections.emptyMap();
                    }

                    /* access modifiers changed from: protected */
                    public Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        return doResolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod.bindEnter(this.adviceMethod), forInstrumentedMethod2.bindEnter(this.adviceMethod), forInstrumentedMethod3.bindEnter(this.adviceMethod), this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription, relocation));
                    }

                    /* access modifiers changed from: protected */
                    public Bound doResolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        ArrayList arrayList = new ArrayList(this.offsetMappings.size());
                        for (OffsetMapping resolve : this.offsetMappings.values()) {
                            arrayList.add(resolve.resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.ENTER));
                        }
                        return new AdviceMethodWriter.ForMethodEnter(this.adviceMethod, arrayList, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2);
                    }

                    protected static class WithRetainedEnterType extends ForMethodEnter {
                        protected WithRetainedEnterType(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                            super(inDefinedShape, list, typeDefinition);
                        }

                        public TypeDefinition getAdviceType() {
                            return this.adviceMethod.getReturnType();
                        }
                    }

                    protected static class WithDiscardedEnterType extends ForMethodEnter {
                        protected WithDiscardedEnterType(MethodDescription.InDefinedShape inDefinedShape, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                            super(inDefinedShape, list, typeDefinition);
                        }

                        public TypeDefinition getAdviceType() {
                            return TypeDescription.VOID;
                        }

                        /* access modifiers changed from: protected */
                        public Bound doResolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                            forAdvice2.requireLocalVariableLengthPadding(this.adviceMethod.getReturnType().getStackSize().getSize());
                            return super.doResolve(typeDescription, methodDescription, methodVisitor, context, assigner, forAdvice, forAdvice2, forAdvice3, bound, bound2);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static abstract class ForMethodExit extends Resolved implements Resolved.ForMethodExit {
                    private final boolean backupArguments;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.backupArguments == ((ForMethodExit) obj).backupArguments;
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + (this.backupArguments ? 1 : 0);
                    }

                    protected ForMethodExit(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                        super(inDefinedShape, CompoundList.of(Arrays.asList(new OffsetMapping.Factory[]{OffsetMapping.ForArgument.Unresolved.Factory.INSTANCE, OffsetMapping.ForAllArguments.Factory.INSTANCE, OffsetMapping.ForThisReference.Factory.INSTANCE, OffsetMapping.ForField.Unresolved.Factory.INSTANCE, OffsetMapping.ForOrigin.Factory.INSTANCE, OffsetMapping.ForUnusedValue.Factory.INSTANCE, OffsetMapping.ForStubValue.INSTANCE, OffsetMapping.ForEnterValue.Factory.of(typeDefinition), OffsetMapping.ForExitValue.Factory.of(inDefinedShape.getReturnType()), new OffsetMapping.ForLocalValue.Factory(map), OffsetMapping.ForReturnValue.Factory.INSTANCE, OffsetMapping.ForThrowable.Factory.of(inDefinedShape)}), (List) list), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.SUPPRESS_EXIT).resolve(TypeDescription.class), (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.REPEAT_ON).resolve(TypeDescription.class));
                        this.backupArguments = ((Boolean) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.BACKUP_ARGUMENTS).resolve(Boolean.class)).booleanValue();
                    }

                    protected static Resolved.ForMethodExit of(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                        TypeDescription typeDescription = (TypeDescription) inDefinedShape.getDeclaredAnnotations().ofType(OnMethodExit.class).getValue(Advice.ON_THROWABLE).resolve(TypeDescription.class);
                        return typeDescription.represents(NoExceptionHandler.class) ? new WithoutExceptionHandler(inDefinedShape, map, list, typeDefinition) : new WithExceptionHandler(inDefinedShape, map, list, typeDefinition, typeDescription);
                    }

                    /* access modifiers changed from: protected */
                    public Bound resolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForInstrumentedMethod forInstrumentedMethod, MethodSizeHandler.ForInstrumentedMethod forInstrumentedMethod2, StackMapFrameHandler.ForInstrumentedMethod forInstrumentedMethod3, StackManipulation stackManipulation, RelocationHandler.Relocation relocation) {
                        return doResolve(typeDescription, methodDescription, methodVisitor, context, assigner, forInstrumentedMethod.bindExit(this.adviceMethod, getThrowable().represents(NoExceptionHandler.class)), forInstrumentedMethod2.bindExit(this.adviceMethod), forInstrumentedMethod3.bindExit(this.adviceMethod), this.suppressionHandler.bind(stackManipulation), this.relocationHandler.bind(methodDescription, relocation));
                    }

                    private Bound doResolve(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, ArgumentHandler.ForAdvice forAdvice, MethodSizeHandler.ForAdvice forAdvice2, StackMapFrameHandler.ForAdvice forAdvice3, SuppressionHandler.Bound bound, RelocationHandler.Bound bound2) {
                        ArrayList arrayList = new ArrayList(this.offsetMappings.size());
                        for (OffsetMapping resolve : this.offsetMappings.values()) {
                            arrayList.add(resolve.resolve(typeDescription, methodDescription, assigner, forAdvice, OffsetMapping.Sort.EXIT));
                        }
                        return new AdviceMethodWriter.ForMethodExit(this.adviceMethod, arrayList, methodVisitor, context, forAdvice, forAdvice2, forAdvice3, bound, bound2);
                    }

                    public ArgumentHandler.Factory getArgumentHandlerFactory() {
                        return this.backupArguments ? ArgumentHandler.Factory.COPYING : ArgumentHandler.Factory.SIMPLE;
                    }

                    public TypeDefinition getAdviceType() {
                        return this.adviceMethod.getReturnType();
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    protected static class WithExceptionHandler extends ForMethodExit {
                        private final TypeDescription throwable;

                        public boolean equals(Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.throwable.equals(((WithExceptionHandler) obj).throwable);
                        }

                        public int hashCode() {
                            return (super.hashCode() * 31) + this.throwable.hashCode();
                        }

                        protected WithExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition, TypeDescription typeDescription) {
                            super(inDefinedShape, map, list, typeDefinition);
                            this.throwable = typeDescription;
                        }

                        public TypeDescription getThrowable() {
                            return this.throwable;
                        }
                    }

                    protected static class WithoutExceptionHandler extends ForMethodExit {
                        protected WithoutExceptionHandler(MethodDescription.InDefinedShape inDefinedShape, Map<String, TypeDefinition> map, List<? extends OffsetMapping.Factory<?>> list, TypeDefinition typeDefinition) {
                            super(inDefinedShape, map, list, typeDefinition);
                        }

                        public TypeDescription getThrowable() {
                            return NoExceptionHandler.DESCRIPTION;
                        }
                    }
                }
            }
        }
    }

    protected static abstract class AdviceVisitor extends ExceptionTableSensitiveMethodVisitor implements Dispatcher.RelocationHandler.Relocation {
        protected final ArgumentHandler.ForInstrumentedMethod argumentHandler;
        protected final MethodDescription instrumentedMethod;
        private final Dispatcher.Bound methodEnter;
        protected final Dispatcher.Bound methodExit;
        protected final MethodSizeHandler.ForInstrumentedMethod methodSizeHandler;
        protected final MethodVisitor methodVisitor;
        private final Label preparationStart = new Label();
        protected final StackMapFrameHandler.ForInstrumentedMethod stackMapFrameHandler;

        /* access modifiers changed from: protected */
        public abstract void onUserEnd();

        /* access modifiers changed from: protected */
        public abstract void onUserPrepare();

        /* access modifiers changed from: protected */
        public abstract void onUserStart();

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected AdviceVisitor(MethodVisitor methodVisitor2, MethodVisitor methodVisitor3, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, Dispatcher.Resolved.ForMethodExit forMethodExit, List<? extends TypeDescription> list, int i, int i2) {
            super(OpenedClassReader.ASM_API, methodVisitor3);
            List list2;
            List list3;
            MethodDescription methodDescription2 = methodDescription;
            this.methodVisitor = methodVisitor2;
            this.instrumentedMethod = methodDescription2;
            this.argumentHandler = forMethodExit.getArgumentHandlerFactory().resolve(methodDescription2, forMethodEnter.getAdviceType(), forMethodExit.getAdviceType(), forMethodEnter.getNamedTypes());
            if (forMethodExit.getAdviceType().represents(Void.TYPE)) {
                list2 = Collections.emptyList();
            } else {
                list2 = Collections.singletonList(forMethodExit.getAdviceType().asErasure());
            }
            List<S> of = CompoundList.of(list2, (List) this.argumentHandler.getNamedTypes());
            if (forMethodEnter.getAdviceType().represents(Void.TYPE)) {
                list3 = Collections.emptyList();
            } else {
                list3 = Collections.singletonList(forMethodEnter.getAdviceType().asErasure());
            }
            List list4 = list3;
            this.methodSizeHandler = MethodSizeHandler.Default.of(methodDescription, of, list4, list, this.argumentHandler.isCopyingArguments(), i);
            StackMapFrameHandler.ForInstrumentedMethod of2 = StackMapFrameHandler.Default.of(typeDescription, methodDescription, of, list4, list, forMethodExit.isAlive(), this.argumentHandler.isCopyingArguments(), context.getClassFileVersion(), i, i2);
            this.stackMapFrameHandler = of2;
            TypeDescription typeDescription2 = typeDescription;
            MethodDescription methodDescription3 = methodDescription;
            MethodVisitor methodVisitor4 = methodVisitor2;
            Implementation.Context context2 = context;
            Assigner assigner2 = assigner;
            StackManipulation stackManipulation2 = stackManipulation;
            this.methodEnter = forMethodEnter.bind(typeDescription2, methodDescription3, methodVisitor4, context2, assigner2, this.argumentHandler, this.methodSizeHandler, of2, stackManipulation2, this);
            this.methodExit = forMethodExit.bind(typeDescription2, methodDescription3, methodVisitor4, context2, assigner2, this.argumentHandler, this.methodSizeHandler, this.stackMapFrameHandler, stackManipulation2, new Dispatcher.RelocationHandler.Relocation.ForLabel(this.preparationStart));
        }

        /* access modifiers changed from: protected */
        public void onAfterExceptionTable() {
            this.methodEnter.prepare();
            onUserPrepare();
            this.methodExit.prepare();
            this.methodEnter.initialize();
            this.methodExit.initialize();
            this.stackMapFrameHandler.injectInitializationFrame(this.methodVisitor);
            this.methodEnter.apply();
            this.methodVisitor.visitLabel(this.preparationStart);
            this.methodSizeHandler.requireStackSize(this.argumentHandler.prepare(this.methodVisitor));
            this.stackMapFrameHandler.injectStartFrame(this.methodVisitor);
            onUserStart();
        }

        /* access modifiers changed from: protected */
        public void onVisitVarInsn(int i, int i2) {
            this.mv.visitVarInsn(i, this.argumentHandler.argument(i2));
        }

        /* access modifiers changed from: protected */
        public void onVisitIincInsn(int i, int i2) {
            this.mv.visitIincInsn(this.argumentHandler.argument(i), i2);
        }

        public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
            this.stackMapFrameHandler.translateFrame(this.methodVisitor, i, i2, objArr, i3, objArr2);
        }

        public void visitMaxs(int i, int i2) {
            onUserEnd();
            this.methodVisitor.visitMaxs(this.methodSizeHandler.compoundStackSize(i), this.methodSizeHandler.compoundLocalVariableLength(i2));
        }

        public void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i) {
            this.mv.visitLocalVariable(str, str2, str3, label, label2, this.argumentHandler.variable(i));
        }

        public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
            int[] iArr2 = iArr;
            int[] iArr3 = new int[iArr2.length];
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                iArr3[i2] = this.argumentHandler.variable(iArr2[i2]);
            }
            return this.mv.visitLocalVariableAnnotation(i, typePath, labelArr, labelArr2, iArr3, str, z);
        }

        protected static class WithoutExitAdvice extends AdviceVisitor {
            /* access modifiers changed from: protected */
            public void onUserEnd() {
            }

            /* access modifiers changed from: protected */
            public void onUserPrepare() {
            }

            /* access modifiers changed from: protected */
            public void onUserStart() {
            }

            protected WithoutExitAdvice(MethodVisitor methodVisitor, Implementation.Context context, Assigner assigner, StackManipulation stackManipulation, TypeDescription typeDescription, MethodDescription methodDescription, Dispatcher.Resolved.ForMethodEnter forMethodEnter, int i, int i2) {
                super(methodVisitor, methodVisitor, context, assigner, stackManipulation, typeDescription, methodDescription, forMethodEnter, Dispatcher.Inactive.INSTANCE, Collections.emptyList(), i, i2);
            }

            public void apply(MethodVisitor methodVisitor) {
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    methodVisitor.visitInsn(3);
                    methodVisitor.visitInsn(172);
                } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    methodVisitor.visitInsn(9);
                    methodVisitor.visitInsn(173);
                } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    methodVisitor.visitInsn(11);
                    methodVisitor.visitInsn(174);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    methodVisitor.visitInsn(14);
                    methodVisitor.visitInsn(175);
                } else if (this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    methodVisitor.visitInsn(177);
                } else {
                    methodVisitor.visitInsn(1);
                    methodVisitor.visitInsn(176);
                }
            }
        }

        protected static abstract class WithExitAdvice extends AdviceVisitor {
            protected final Label returnHandler = new Label();

            /* access modifiers changed from: protected */
            public abstract void onExitAdviceReturn();

            /* access modifiers changed from: protected */
            public abstract void onUserReturn();

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            protected WithExitAdvice(net.bytebuddy.jar.asm.MethodVisitor r14, net.bytebuddy.implementation.Implementation.Context r15, net.bytebuddy.implementation.bytecode.assign.Assigner r16, net.bytebuddy.implementation.bytecode.StackManipulation r17, net.bytebuddy.description.type.TypeDescription r18, net.bytebuddy.description.method.MethodDescription r19, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter r20, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit r21, java.util.List<? extends net.bytebuddy.description.type.TypeDescription> r22, int r23, int r24) {
                /*
                    r13 = this;
                    net.bytebuddy.utility.visitor.StackAwareMethodVisitor r2 = new net.bytebuddy.utility.visitor.StackAwareMethodVisitor
                    r1 = r14
                    r7 = r19
                    r2.<init>(r14, r7)
                    r0 = r13
                    r3 = r15
                    r4 = r16
                    r5 = r17
                    r6 = r18
                    r8 = r20
                    r9 = r21
                    r10 = r22
                    r11 = r23
                    r12 = r24
                    r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
                    net.bytebuddy.jar.asm.Label r0 = new net.bytebuddy.jar.asm.Label
                    r0.<init>()
                    r1 = r13
                    r1.returnHandler = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice.<init>(net.bytebuddy.jar.asm.MethodVisitor, net.bytebuddy.implementation.Implementation$Context, net.bytebuddy.implementation.bytecode.assign.Assigner, net.bytebuddy.implementation.bytecode.StackManipulation, net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.method.MethodDescription, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodEnter, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodExit, java.util.List, int, int):void");
            }

            public void apply(MethodVisitor methodVisitor) {
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    methodVisitor.visitInsn(3);
                } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    methodVisitor.visitInsn(9);
                } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    methodVisitor.visitInsn(11);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    methodVisitor.visitInsn(14);
                } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    methodVisitor.visitInsn(1);
                }
                methodVisitor.visitJumpInsn(167, this.returnHandler);
            }

            /* access modifiers changed from: protected */
            public void onVisitInsn(int i) {
                switch (i) {
                    case 172:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(54, 21, StackSize.SINGLE));
                        break;
                    case 173:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(55, 22, StackSize.DOUBLE));
                        break;
                    case 174:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(56, 23, StackSize.SINGLE));
                        break;
                    case 175:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(57, 24, StackSize.DOUBLE));
                        break;
                    case 176:
                        this.methodSizeHandler.requireLocalVariableLength(((StackAwareMethodVisitor) this.mv).drainStack(58, 25, StackSize.SINGLE));
                        break;
                    case 177:
                        ((StackAwareMethodVisitor) this.mv).drainStack();
                        break;
                    default:
                        this.mv.visitInsn(i);
                        return;
                }
                this.mv.visitJumpInsn(167, this.returnHandler);
            }

            /* access modifiers changed from: protected */
            public void onUserEnd() {
                this.methodVisitor.visitLabel(this.returnHandler);
                onUserReturn();
                this.stackMapFrameHandler.injectCompletionFrame(this.methodVisitor);
                this.methodExit.apply();
                onExitAdviceReturn();
                if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                    this.methodVisitor.visitVarInsn(21, this.argumentHandler.returned());
                    this.methodVisitor.visitInsn(172);
                } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                    this.methodVisitor.visitVarInsn(22, this.argumentHandler.returned());
                    this.methodVisitor.visitInsn(173);
                } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                    this.methodVisitor.visitVarInsn(23, this.argumentHandler.returned());
                    this.methodVisitor.visitInsn(174);
                } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                    this.methodVisitor.visitVarInsn(24, this.argumentHandler.returned());
                    this.methodVisitor.visitInsn(175);
                } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                    this.methodVisitor.visitVarInsn(25, this.argumentHandler.returned());
                    this.methodVisitor.visitInsn(176);
                } else {
                    this.methodVisitor.visitInsn(177);
                }
                this.methodSizeHandler.requireStackSize(this.instrumentedMethod.getReturnType().getStackSize().getSize());
            }

            protected static class WithoutExceptionHandling extends WithExitAdvice {
                /* access modifiers changed from: protected */
                public void onExitAdviceReturn() {
                }

                /* access modifiers changed from: protected */
                public void onUserPrepare() {
                }

                /* access modifiers changed from: protected */
                public void onUserStart() {
                }

                /* JADX WARNING: Illegal instructions before constructor call */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                protected WithoutExceptionHandling(net.bytebuddy.jar.asm.MethodVisitor r14, net.bytebuddy.implementation.Implementation.Context r15, net.bytebuddy.implementation.bytecode.assign.Assigner r16, net.bytebuddy.implementation.bytecode.StackManipulation r17, net.bytebuddy.description.type.TypeDescription r18, net.bytebuddy.description.method.MethodDescription r19, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter r20, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit r21, int r22, int r23) {
                    /*
                        r13 = this;
                        net.bytebuddy.description.type.TypeDescription$Generic r0 = r19.getReturnType()
                        java.lang.Class r1 = java.lang.Void.TYPE
                        boolean r0 = r0.represents(r1)
                        if (r0 == 0) goto L_0x0011
                        java.util.List r0 = java.util.Collections.emptyList()
                        goto L_0x001d
                    L_0x0011:
                        net.bytebuddy.description.type.TypeDescription$Generic r0 = r19.getReturnType()
                        net.bytebuddy.description.type.TypeDescription r0 = r0.asErasure()
                        java.util.List r0 = java.util.Collections.singletonList(r0)
                    L_0x001d:
                        r10 = r0
                        r1 = r13
                        r2 = r14
                        r3 = r15
                        r4 = r16
                        r5 = r17
                        r6 = r18
                        r7 = r19
                        r8 = r20
                        r9 = r21
                        r11 = r22
                        r12 = r23
                        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice.WithoutExceptionHandling.<init>(net.bytebuddy.jar.asm.MethodVisitor, net.bytebuddy.implementation.Implementation$Context, net.bytebuddy.implementation.bytecode.assign.Assigner, net.bytebuddy.implementation.bytecode.StackManipulation, net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.method.MethodDescription, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodEnter, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodExit, int, int):void");
                }

                /* access modifiers changed from: protected */
                public void onUserReturn() {
                    if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(54, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(55, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(56, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(57, this.argumentHandler.returned());
                    } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                        this.methodVisitor.visitVarInsn(58, this.argumentHandler.returned());
                    }
                }
            }

            protected static class WithExceptionHandling extends WithExitAdvice {
                private final Label exceptionHandler;
                private final TypeDescription throwable;
                protected final Label userStart;

                /* JADX WARNING: Illegal instructions before constructor call */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                protected WithExceptionHandling(net.bytebuddy.jar.asm.MethodVisitor r14, net.bytebuddy.implementation.Implementation.Context r15, net.bytebuddy.implementation.bytecode.assign.Assigner r16, net.bytebuddy.implementation.bytecode.StackManipulation r17, net.bytebuddy.description.type.TypeDescription r18, net.bytebuddy.description.method.MethodDescription r19, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodEnter r20, net.bytebuddy.asm.Advice.Dispatcher.Resolved.ForMethodExit r21, int r22, int r23, net.bytebuddy.description.type.TypeDescription r24) {
                    /*
                        r13 = this;
                        r12 = r13
                        net.bytebuddy.description.type.TypeDescription$Generic r0 = r19.getReturnType()
                        java.lang.Class r1 = java.lang.Void.TYPE
                        boolean r0 = r0.represents(r1)
                        if (r0 == 0) goto L_0x0014
                        net.bytebuddy.description.type.TypeDescription r0 = net.bytebuddy.description.type.TypeDescription.THROWABLE
                        java.util.List r0 = java.util.Collections.singletonList(r0)
                        goto L_0x002b
                    L_0x0014:
                        r0 = 2
                        net.bytebuddy.description.type.TypeDescription[] r0 = new net.bytebuddy.description.type.TypeDescription[r0]
                        r1 = 0
                        net.bytebuddy.description.type.TypeDescription$Generic r2 = r19.getReturnType()
                        net.bytebuddy.description.type.TypeDescription r2 = r2.asErasure()
                        r0[r1] = r2
                        r1 = 1
                        net.bytebuddy.description.type.TypeDescription r2 = net.bytebuddy.description.type.TypeDescription.THROWABLE
                        r0[r1] = r2
                        java.util.List r0 = java.util.Arrays.asList(r0)
                    L_0x002b:
                        r9 = r0
                        r0 = r13
                        r1 = r14
                        r2 = r15
                        r3 = r16
                        r4 = r17
                        r5 = r18
                        r6 = r19
                        r7 = r20
                        r8 = r21
                        r10 = r22
                        r11 = r23
                        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
                        r0 = r24
                        r12.throwable = r0
                        net.bytebuddy.jar.asm.Label r0 = new net.bytebuddy.jar.asm.Label
                        r0.<init>()
                        r12.exceptionHandler = r0
                        net.bytebuddy.jar.asm.Label r0 = new net.bytebuddy.jar.asm.Label
                        r0.<init>()
                        r12.userStart = r0
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.Advice.AdviceVisitor.WithExitAdvice.WithExceptionHandling.<init>(net.bytebuddy.jar.asm.MethodVisitor, net.bytebuddy.implementation.Implementation$Context, net.bytebuddy.implementation.bytecode.assign.Assigner, net.bytebuddy.implementation.bytecode.StackManipulation, net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.method.MethodDescription, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodEnter, net.bytebuddy.asm.Advice$Dispatcher$Resolved$ForMethodExit, int, int, net.bytebuddy.description.type.TypeDescription):void");
                }

                /* access modifiers changed from: protected */
                public void onUserPrepare() {
                    this.methodVisitor.visitTryCatchBlock(this.userStart, this.returnHandler, this.exceptionHandler, this.throwable.getInternalName());
                }

                /* access modifiers changed from: protected */
                public void onUserStart() {
                    this.methodVisitor.visitLabel(this.userStart);
                }

                /* access modifiers changed from: protected */
                public void onUserReturn() {
                    this.stackMapFrameHandler.injectReturnFrame(this.methodVisitor);
                    if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                        this.methodVisitor.visitVarInsn(54, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                        this.methodVisitor.visitVarInsn(55, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                        this.methodVisitor.visitVarInsn(56, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                        this.methodVisitor.visitVarInsn(57, this.argumentHandler.returned());
                    } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        this.methodVisitor.visitVarInsn(58, this.argumentHandler.returned());
                    }
                    this.methodVisitor.visitInsn(1);
                    this.methodVisitor.visitVarInsn(58, this.argumentHandler.thrown());
                    Label label = new Label();
                    this.methodVisitor.visitJumpInsn(167, label);
                    this.methodVisitor.visitLabel(this.exceptionHandler);
                    this.stackMapFrameHandler.injectExceptionFrame(this.methodVisitor);
                    this.methodVisitor.visitVarInsn(58, this.argumentHandler.thrown());
                    if (this.instrumentedMethod.getReturnType().represents(Boolean.TYPE) || this.instrumentedMethod.getReturnType().represents(Byte.TYPE) || this.instrumentedMethod.getReturnType().represents(Short.TYPE) || this.instrumentedMethod.getReturnType().represents(Character.TYPE) || this.instrumentedMethod.getReturnType().represents(Integer.TYPE)) {
                        this.methodVisitor.visitInsn(3);
                        this.methodVisitor.visitVarInsn(54, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Long.TYPE)) {
                        this.methodVisitor.visitInsn(9);
                        this.methodVisitor.visitVarInsn(55, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Float.TYPE)) {
                        this.methodVisitor.visitInsn(11);
                        this.methodVisitor.visitVarInsn(56, this.argumentHandler.returned());
                    } else if (this.instrumentedMethod.getReturnType().represents(Double.TYPE)) {
                        this.methodVisitor.visitInsn(14);
                        this.methodVisitor.visitVarInsn(57, this.argumentHandler.returned());
                    } else if (!this.instrumentedMethod.getReturnType().represents(Void.TYPE)) {
                        this.methodVisitor.visitInsn(1);
                        this.methodVisitor.visitVarInsn(58, this.argumentHandler.returned());
                    }
                    this.methodVisitor.visitLabel(label);
                    this.methodSizeHandler.requireStackSize(StackSize.SINGLE.getSize());
                }

                /* access modifiers changed from: protected */
                public void onExitAdviceReturn() {
                    this.methodVisitor.visitVarInsn(25, this.argumentHandler.thrown());
                    Label label = new Label();
                    this.methodVisitor.visitJumpInsn(198, label);
                    this.methodVisitor.visitVarInsn(25, this.argumentHandler.thrown());
                    this.methodVisitor.visitInsn(191);
                    this.methodVisitor.visitLabel(label);
                    this.stackMapFrameHandler.injectPostCompletionFrame(this.methodVisitor);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class Appender implements ByteCodeAppender {
        private final Advice advice;
        private final ByteCodeAppender delegate;
        private final Implementation.Target implementationTarget;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Appender appender = (Appender) obj;
            return this.advice.equals(appender.advice) && this.implementationTarget.equals(appender.implementationTarget) && this.delegate.equals(appender.delegate);
        }

        public int hashCode() {
            return ((((527 + this.advice.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + this.delegate.hashCode();
        }

        protected Appender(Advice advice2, Implementation.Target target, ByteCodeAppender byteCodeAppender) {
            this.advice = advice2;
            this.implementationTarget = target;
            this.delegate = byteCodeAppender;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            EmulatingMethodVisitor emulatingMethodVisitor = new EmulatingMethodVisitor(methodVisitor, this.delegate);
            return emulatingMethodVisitor.resolve(this.advice.doWrap(this.implementationTarget.getInstrumentedType(), methodDescription, emulatingMethodVisitor, context, 0, 0), context, methodDescription);
        }

        protected static class EmulatingMethodVisitor extends MethodVisitor {
            private final ByteCodeAppender delegate;
            private int localVariableLength;
            private int stackSize;

            public void visitCode() {
            }

            public void visitEnd() {
            }

            protected EmulatingMethodVisitor(MethodVisitor methodVisitor, ByteCodeAppender byteCodeAppender) {
                super(OpenedClassReader.ASM_API, methodVisitor);
                this.delegate = byteCodeAppender;
            }

            /* access modifiers changed from: protected */
            public ByteCodeAppender.Size resolve(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                methodVisitor.visitCode();
                ByteCodeAppender.Size apply = this.delegate.apply(methodVisitor, context, methodDescription);
                methodVisitor.visitMaxs(apply.getOperandStackSize(), apply.getLocalVariableSize());
                methodVisitor.visitEnd();
                return new ByteCodeAppender.Size(this.stackSize, this.localVariableLength);
            }

            public void visitMaxs(int i, int i2) {
                this.stackSize = i;
                this.localVariableLength = i2;
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class WithCustomMapping {
        private final Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> offsetMappings;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.offsetMappings.equals(((WithCustomMapping) obj).offsetMappings);
        }

        public int hashCode() {
            return 527 + this.offsetMappings.hashCode();
        }

        protected WithCustomMapping() {
            this(Collections.emptyMap());
        }

        protected WithCustomMapping(Map<Class<? extends Annotation>, OffsetMapping.Factory<?>> map) {
            this.offsetMappings = map;
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Object obj) {
            return bind(OffsetMapping.ForStackManipulation.Factory.of(cls, obj));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Field field) {
            return bind(cls, (FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, FieldDescription fieldDescription) {
            return bind(new OffsetMapping.ForField.Resolved.Factory(cls, fieldDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Method method, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("A parameter cannot be negative: " + i);
            } else if (method.getParameterTypes().length > i) {
                return bind(cls, (ParameterDescription) new MethodDescription.ForLoadedMethod(method).getParameters().get(i));
            } else {
                throw new IllegalArgumentException(method + " does not declare a parameter with index " + i);
            }
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Constructor<?> constructor, int i) {
            if (i < 0) {
                throw new IllegalArgumentException("A parameter cannot be negative: " + i);
            } else if (constructor.getParameterTypes().length > i) {
                return bind(cls, (ParameterDescription) new MethodDescription.ForLoadedConstructor(constructor).getParameters().get(i));
            } else {
                throw new IllegalArgumentException(constructor + " does not declare a parameter with index " + i);
            }
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, ParameterDescription parameterDescription) {
            return bind(new OffsetMapping.ForArgument.Resolved.Factory(cls, parameterDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Class<?> cls2) {
            return bind(cls, TypeDescription.ForLoadedType.of(cls2));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, TypeDescription typeDescription) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, typeDescription));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, Enum<?> enumR) {
            return bind(cls, (EnumerationDescription) new EnumerationDescription.ForLoadedEnumeration(enumR));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, EnumerationDescription enumerationDescription) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, enumerationDescription));
        }

        public <T extends Annotation> WithCustomMapping bindSerialized(Class<T> cls, Serializable serializable) {
            return bindSerialized(cls, serializable, serializable.getClass());
        }

        public <T extends Annotation, S extends Serializable> WithCustomMapping bindSerialized(Class<T> cls, S s, Class<? super S> cls2) {
            return bind(OffsetMapping.ForSerializedValue.Factory.of(cls, s, cls2));
        }

        public <T extends Annotation> WithCustomMapping bindProperty(Class<T> cls, String str) {
            return bind(OffsetMapping.ForStackManipulation.OfAnnotationProperty.of(cls, str));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, StackManipulation stackManipulation, java.lang.reflect.Type type) {
            return bind(cls, stackManipulation, TypeDefinition.Sort.describe(type));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, StackManipulation stackManipulation, TypeDescription.Generic generic) {
            return bind(new OffsetMapping.ForStackManipulation.Factory(cls, stackManipulation, generic));
        }

        public <T extends Annotation> WithCustomMapping bind(Class<T> cls, OffsetMapping offsetMapping) {
            return bind(new OffsetMapping.Factory.Simple(cls, offsetMapping));
        }

        public WithCustomMapping bind(OffsetMapping.Factory<?> factory) {
            HashMap hashMap = new HashMap(this.offsetMappings);
            if (!factory.getAnnotationType().isAnnotation()) {
                throw new IllegalArgumentException("Not an annotation type: " + factory.getAnnotationType());
            } else if (hashMap.put(factory.getAnnotationType(), factory) == null) {
                return new WithCustomMapping(hashMap);
            } else {
                throw new IllegalArgumentException("Annotation type already mapped: " + factory.getAnnotationType());
            }
        }

        public Advice to(Class<?> cls) {
            return to(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
        }

        public Advice to(Class<?> cls, ClassFileLocator classFileLocator) {
            return to(TypeDescription.ForLoadedType.of(cls), classFileLocator);
        }

        public Advice to(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            return Advice.to(typeDescription, classFileLocator, (List<? extends OffsetMapping.Factory<?>>) new ArrayList(this.offsetMappings.values()));
        }

        public Advice to(Class<?> cls, Class<?> cls2) {
            ClassFileLocator classFileLocator;
            ClassLoader classLoader = cls.getClassLoader();
            ClassLoader classLoader2 = cls2.getClassLoader();
            if (classLoader == classLoader2) {
                classFileLocator = ClassFileLocator.ForClassLoader.of(classLoader);
            } else {
                classFileLocator = new ClassFileLocator.Compound(ClassFileLocator.ForClassLoader.of(classLoader), ClassFileLocator.ForClassLoader.of(classLoader2));
            }
            return to(cls, cls2, classFileLocator);
        }

        public Advice to(Class<?> cls, Class<?> cls2, ClassFileLocator classFileLocator) {
            return to(TypeDescription.ForLoadedType.of(cls), TypeDescription.ForLoadedType.of(cls2), classFileLocator);
        }

        public Advice to(TypeDescription typeDescription, TypeDescription typeDescription2) {
            return to(typeDescription, typeDescription2, (ClassFileLocator) ClassFileLocator.NoOp.INSTANCE);
        }

        public Advice to(TypeDescription typeDescription, TypeDescription typeDescription2, ClassFileLocator classFileLocator) {
            return Advice.to(typeDescription, typeDescription2, classFileLocator, new ArrayList(this.offsetMappings.values()));
        }
    }

    private static class NoExceptionHandler extends Throwable {
        /* access modifiers changed from: private */
        public static final TypeDescription DESCRIPTION = TypeDescription.ForLoadedType.of(NoExceptionHandler.class);

        private NoExceptionHandler() {
            throw new UnsupportedOperationException("This class only serves as a marker type and should not be instantiated");
        }
    }

    public static final class OnDefaultValue {
        private OnDefaultValue() {
            throw new UnsupportedOperationException("This class only serves as a marker type and should not be instantiated");
        }
    }

    public static final class OnNonDefaultValue {
        private OnNonDefaultValue() {
            throw new UnsupportedOperationException("This class only serves as a marker type and should not be instantiated");
        }
    }
}

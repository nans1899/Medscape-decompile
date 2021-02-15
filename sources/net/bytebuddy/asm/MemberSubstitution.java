package net.bytebuddy.asm;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.FilterableList;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;

@HashCodeAndEqualsPlugin.Enhance
public class MemberSubstitution implements AsmVisitorWrapper.ForDeclaredMethods.MethodVisitorWrapper {
    private final MethodGraph.Compiler methodGraphCompiler;
    private final Replacement.Factory replacementFactory;
    private final boolean strict;
    private final TypePoolResolver typePoolResolver;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MemberSubstitution memberSubstitution = (MemberSubstitution) obj;
        return this.strict == memberSubstitution.strict && this.methodGraphCompiler.equals(memberSubstitution.methodGraphCompiler) && this.typePoolResolver.equals(memberSubstitution.typePoolResolver) && this.replacementFactory.equals(memberSubstitution.replacementFactory);
    }

    public int hashCode() {
        return ((((((527 + this.methodGraphCompiler.hashCode()) * 31) + (this.strict ? 1 : 0)) * 31) + this.typePoolResolver.hashCode()) * 31) + this.replacementFactory.hashCode();
    }

    protected MemberSubstitution(boolean z) {
        this(MethodGraph.Compiler.DEFAULT, TypePoolResolver.OfImplicitPool.INSTANCE, z, Replacement.NoOp.INSTANCE);
    }

    protected MemberSubstitution(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver2, boolean z, Replacement.Factory factory) {
        this.methodGraphCompiler = compiler;
        this.typePoolResolver = typePoolResolver2;
        this.strict = z;
        this.replacementFactory = factory;
    }

    public static MemberSubstitution strict() {
        return new MemberSubstitution(true);
    }

    public static MemberSubstitution relaxed() {
        return new MemberSubstitution(false);
    }

    public WithoutSpecification element(ElementMatcher<? super ByteCodeElement> elementMatcher) {
        return new WithoutSpecification.ForMatchedByteCodeElement(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public WithoutSpecification.ForMatchedField field(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new WithoutSpecification.ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public WithoutSpecification.ForMatchedMethod method(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new WithoutSpecification.ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public WithoutSpecification constructor(ElementMatcher<? super MethodDescription> elementMatcher) {
        return invokable(ElementMatchers.isConstructor().and(elementMatcher));
    }

    public WithoutSpecification invokable(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new WithoutSpecification.ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, elementMatcher);
    }

    public MemberSubstitution with(MethodGraph.Compiler compiler) {
        return new MemberSubstitution(compiler, this.typePoolResolver, this.strict, this.replacementFactory);
    }

    public MemberSubstitution with(TypePoolResolver typePoolResolver2) {
        return new MemberSubstitution(this.methodGraphCompiler, typePoolResolver2, this.strict, this.replacementFactory);
    }

    public AsmVisitorWrapper.ForDeclaredMethods on(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new AsmVisitorWrapper.ForDeclaredMethods().invokable(elementMatcher, this);
    }

    public MethodVisitor wrap(TypeDescription typeDescription, MethodDescription methodDescription, MethodVisitor methodVisitor, Implementation.Context context, TypePool typePool, int i, int i2) {
        TypePool resolve = this.typePoolResolver.resolve(typeDescription, methodDescription, typePool);
        return new SubstitutingMethodVisitor(methodVisitor, typeDescription, this.methodGraphCompiler, this.strict, this.replacementFactory.make(typeDescription, methodDescription, resolve), context, resolve);
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static abstract class WithoutSpecification {
        protected final MethodGraph.Compiler methodGraphCompiler;
        protected final Replacement.Factory replacementFactory;
        protected final boolean strict;
        protected final TypePoolResolver typePoolResolver;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WithoutSpecification withoutSpecification = (WithoutSpecification) obj;
            return this.strict == withoutSpecification.strict && this.methodGraphCompiler.equals(withoutSpecification.methodGraphCompiler) && this.typePoolResolver.equals(withoutSpecification.typePoolResolver) && this.replacementFactory.equals(withoutSpecification.replacementFactory);
        }

        public int hashCode() {
            return ((((((527 + this.methodGraphCompiler.hashCode()) * 31) + this.typePoolResolver.hashCode()) * 31) + (this.strict ? 1 : 0)) * 31) + this.replacementFactory.hashCode();
        }

        public abstract MemberSubstitution replaceWith(Substitution.Factory factory);

        protected WithoutSpecification(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver2, boolean z, Replacement.Factory factory) {
            this.methodGraphCompiler = compiler;
            this.typePoolResolver = typePoolResolver2;
            this.strict = z;
            this.replacementFactory = factory;
        }

        public MemberSubstitution stub() {
            return replaceWith((Substitution.Factory) Substitution.Stubbing.INSTANCE);
        }

        public MemberSubstitution replaceWith(Field field) {
            return replaceWith((FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public MemberSubstitution replaceWith(FieldDescription fieldDescription) {
            return replaceWith((Substitution.Factory) new Substitution.ForFieldAccess.OfGivenField(fieldDescription));
        }

        public MemberSubstitution replaceWithField(ElementMatcher<? super FieldDescription> elementMatcher) {
            return replaceWith((Substitution.Factory) new Substitution.ForFieldAccess.OfMatchedField(elementMatcher));
        }

        public MemberSubstitution replaceWith(Method method) {
            return replaceWith((MethodDescription) new MethodDescription.ForLoadedMethod(method));
        }

        public MemberSubstitution replaceWith(MethodDescription methodDescription) {
            if (methodDescription.isMethod()) {
                return replaceWith((Substitution.Factory) new Substitution.ForMethodInvocation.OfGivenMethod(methodDescription));
            }
            throw new IllegalArgumentException("Cannot use " + methodDescription + " as a replacement");
        }

        public MemberSubstitution replaceWithMethod(ElementMatcher<? super MethodDescription> elementMatcher) {
            return replaceWithMethod(elementMatcher, this.methodGraphCompiler);
        }

        public MemberSubstitution replaceWithMethod(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
            return replaceWith((Substitution.Factory) new Substitution.ForMethodInvocation.OfMatchedMethod(elementMatcher, compiler));
        }

        public MemberSubstitution replaceWithInstrumentedMethod() {
            return replaceWith((Substitution.Factory) Substitution.ForMethodInvocation.OfInstrumentedMethod.INSTANCE);
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class ForMatchedByteCodeElement extends WithoutSpecification {
            private final ElementMatcher<? super ByteCodeElement> matcher;

            public boolean equals(Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.matcher.equals(((ForMatchedByteCodeElement) obj).matcher);
            }

            public int hashCode() {
                return (super.hashCode() * 31) + this.matcher.hashCode();
            }

            protected ForMatchedByteCodeElement(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super ByteCodeElement> elementMatcher) {
                super(compiler, typePoolResolver, z, factory);
                this.matcher = elementMatcher;
            }

            public MemberSubstitution replaceWith(Substitution.Factory factory) {
                return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Replacement.Factory.Compound(this.replacementFactory, Replacement.ForElementMatchers.Factory.of(this.matcher, factory)));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMatchedField extends WithoutSpecification {
            private final boolean matchRead;
            private final boolean matchWrite;
            private final ElementMatcher<? super FieldDescription.InDefinedShape> matcher;

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
                ForMatchedField forMatchedField = (ForMatchedField) obj;
                return this.matchRead == forMatchedField.matchRead && this.matchWrite == forMatchedField.matchWrite && this.matcher.equals(forMatchedField.matcher);
            }

            public int hashCode() {
                return (((((super.hashCode() * 31) + this.matcher.hashCode()) * 31) + (this.matchRead ? 1 : 0)) * 31) + (this.matchWrite ? 1 : 0);
            }

            protected ForMatchedField(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
                this(compiler, typePoolResolver, z, factory, elementMatcher, true, true);
            }

            protected ForMatchedField(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, boolean z2, boolean z3) {
                super(compiler, typePoolResolver, z, factory);
                this.matcher = elementMatcher;
                this.matchRead = z2;
                this.matchWrite = z3;
            }

            public WithoutSpecification onRead() {
                return new ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, this.matcher, true, false);
            }

            public WithoutSpecification onWrite() {
                return new ForMatchedField(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, this.matcher, false, true);
            }

            public MemberSubstitution replaceWith(Substitution.Factory factory) {
                return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Replacement.Factory.Compound(this.replacementFactory, Replacement.ForElementMatchers.Factory.ofField(this.matcher, this.matchRead, this.matchWrite, factory)));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMatchedMethod extends WithoutSpecification {
            private final boolean includeSuperCalls;
            private final boolean includeVirtualCalls;
            private final ElementMatcher<? super MethodDescription> matcher;

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
                ForMatchedMethod forMatchedMethod = (ForMatchedMethod) obj;
                return this.includeVirtualCalls == forMatchedMethod.includeVirtualCalls && this.includeSuperCalls == forMatchedMethod.includeSuperCalls && this.matcher.equals(forMatchedMethod.matcher);
            }

            public int hashCode() {
                return (((((super.hashCode() * 31) + this.matcher.hashCode()) * 31) + (this.includeVirtualCalls ? 1 : 0)) * 31) + (this.includeSuperCalls ? 1 : 0);
            }

            protected ForMatchedMethod(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super MethodDescription> elementMatcher) {
                this(compiler, typePoolResolver, z, factory, elementMatcher, true, true);
            }

            protected ForMatchedMethod(MethodGraph.Compiler compiler, TypePoolResolver typePoolResolver, boolean z, Replacement.Factory factory, ElementMatcher<? super MethodDescription> elementMatcher, boolean z2, boolean z3) {
                super(compiler, typePoolResolver, z, factory);
                this.matcher = elementMatcher;
                this.includeVirtualCalls = z2;
                this.includeSuperCalls = z3;
            }

            public WithoutSpecification onVirtualCall() {
                return new ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, ElementMatchers.isVirtual().and(this.matcher), true, false);
            }

            public WithoutSpecification onSuperCall() {
                return new ForMatchedMethod(this.methodGraphCompiler, this.typePoolResolver, this.strict, this.replacementFactory, ElementMatchers.isVirtual().and(this.matcher), false, true);
            }

            public MemberSubstitution replaceWith(Substitution.Factory factory) {
                return new MemberSubstitution(this.methodGraphCompiler, this.typePoolResolver, this.strict, new Replacement.Factory.Compound(this.replacementFactory, Replacement.ForElementMatchers.Factory.ofMethod(this.matcher, this.includeVirtualCalls, this.includeSuperCalls, factory)));
            }
        }
    }

    public interface TypePoolResolver {

        public enum OfImplicitPool implements TypePoolResolver {
            INSTANCE;

            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return typePool;
            }
        }

        TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool);

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForExplicitPool implements TypePoolResolver {
            private final TypePool typePool;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typePool.equals(((ForExplicitPool) obj).typePool);
            }

            public int hashCode() {
                return 527 + this.typePool.hashCode();
            }

            public ForExplicitPool(TypePool typePool2) {
                this.typePool = typePool2;
            }

            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool2) {
                return this.typePool;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForClassFileLocator implements TypePoolResolver {
            private final ClassFileLocator classFileLocator;
            private final TypePool.Default.ReaderMode readerMode;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForClassFileLocator forClassFileLocator = (ForClassFileLocator) obj;
                return this.readerMode.equals(forClassFileLocator.readerMode) && this.classFileLocator.equals(forClassFileLocator.classFileLocator);
            }

            public int hashCode() {
                return ((527 + this.classFileLocator.hashCode()) * 31) + this.readerMode.hashCode();
            }

            public ForClassFileLocator(ClassFileLocator classFileLocator2) {
                this(classFileLocator2, TypePool.Default.ReaderMode.FAST);
            }

            public ForClassFileLocator(ClassFileLocator classFileLocator2, TypePool.Default.ReaderMode readerMode2) {
                this.classFileLocator = classFileLocator2;
                this.readerMode = readerMode2;
            }

            public static TypePoolResolver of(ClassLoader classLoader) {
                return new ForClassFileLocator(ClassFileLocator.ForClassLoader.of(classLoader));
            }

            public TypePool resolve(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return new TypePool.Default(new TypePool.CacheProvider.Simple(), this.classFileLocator, this.readerMode, typePool);
            }
        }
    }

    public interface Substitution {

        public interface Factory {
            Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool);
        }

        StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2);

        public enum Stubbing implements Substitution, Factory {
            INSTANCE;

            public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return this;
            }

            public StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                ArrayList arrayList = new ArrayList(generic.size());
                for (int size = generic.size() - 1; size >= 0; size--) {
                    arrayList.add(Removal.of((TypeDefinition) generic.get(size)));
                }
                return new StackManipulation.Compound((List<? extends StackManipulation>) CompoundList.of(arrayList, DefaultValue.of(generic2.asErasure())));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForFieldAccess implements Substitution {
            private final FieldResolver fieldResolver;
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForFieldAccess forFieldAccess = (ForFieldAccess) obj;
                return this.instrumentedType.equals(forFieldAccess.instrumentedType) && this.fieldResolver.equals(forFieldAccess.fieldResolver);
            }

            public int hashCode() {
                return ((527 + this.instrumentedType.hashCode()) * 31) + this.fieldResolver.hashCode();
            }

            public ForFieldAccess(TypeDescription typeDescription, FieldResolver fieldResolver2) {
                this.instrumentedType = typeDescription;
                this.fieldResolver = fieldResolver2;
            }

            public StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                FieldDescription resolve = this.fieldResolver.resolve(typeDescription, byteCodeElement, generic, generic2);
                if (!resolve.isAccessibleTo(this.instrumentedType)) {
                    throw new IllegalStateException(this.instrumentedType + " cannot access " + resolve);
                } else if (generic2.represents(Void.TYPE)) {
                    if (generic.size() != (resolve.isStatic() ? 1 : 2)) {
                        throw new IllegalStateException("Cannot set " + resolve + " with " + generic);
                    } else if (!resolve.isStatic() && !((TypeDescription.Generic) generic.get(0)).asErasure().isAssignableTo(resolve.getDeclaringType().asErasure())) {
                        throw new IllegalStateException("Cannot set " + resolve + " on " + generic.get(0));
                    } else if (((TypeDescription.Generic) generic.get(resolve.isStatic() ^ true ? 1 : 0)).asErasure().isAssignableTo(resolve.getType().asErasure())) {
                        return FieldAccess.forField(resolve).write();
                    } else {
                        throw new IllegalStateException("Cannot set " + resolve + " to " + generic.get(resolve.isStatic() ^ true ? 1 : 0));
                    }
                } else if (generic.size() != (true ^ resolve.isStatic())) {
                    throw new IllegalStateException("Cannot set " + resolve + " with " + generic);
                } else if (!resolve.isStatic() && !((TypeDescription.Generic) generic.get(0)).asErasure().isAssignableTo(resolve.getDeclaringType().asErasure())) {
                    throw new IllegalStateException("Cannot get " + resolve + " on " + generic.get(0));
                } else if (resolve.getType().asErasure().isAssignableTo(generic2.asErasure())) {
                    return FieldAccess.forField(resolve).read();
                } else {
                    throw new IllegalStateException("Cannot get " + resolve + " as " + generic2);
                }
            }

            public interface FieldResolver {
                FieldDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2);

                @HashCodeAndEqualsPlugin.Enhance
                public static class Simple implements FieldResolver {
                    private final FieldDescription fieldDescription;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Simple) obj).fieldDescription);
                    }

                    public int hashCode() {
                        return 527 + this.fieldDescription.hashCode();
                    }

                    public Simple(FieldDescription fieldDescription2) {
                        this.fieldDescription = fieldDescription2;
                    }

                    public FieldDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        return this.fieldDescription;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForElementMatcher implements FieldResolver {
                    private final TypeDescription instrumentedType;
                    private final ElementMatcher<? super FieldDescription> matcher;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForElementMatcher forElementMatcher = (ForElementMatcher) obj;
                        return this.instrumentedType.equals(forElementMatcher.instrumentedType) && this.matcher.equals(forElementMatcher.matcher);
                    }

                    public int hashCode() {
                        return ((527 + this.instrumentedType.hashCode()) * 31) + this.matcher.hashCode();
                    }

                    protected ForElementMatcher(TypeDescription typeDescription, ElementMatcher<? super FieldDescription> elementMatcher) {
                        this.instrumentedType = typeDescription;
                        this.matcher = elementMatcher;
                    }

                    public FieldDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        if (generic.isEmpty()) {
                            throw new IllegalStateException("Cannot substitute parameterless instruction with " + byteCodeElement);
                        } else if (((TypeDescription.Generic) generic.get(0)).isPrimitive() || ((TypeDescription.Generic) generic.get(0)).isArray()) {
                            throw new IllegalStateException("Cannot access field on primitive or array type for " + byteCodeElement);
                        } else {
                            TypeDefinition typeDefinition = (TypeDefinition) generic.get(0);
                            do {
                                FieldList fieldList = (FieldList) typeDefinition.getDeclaredFields().filter(ElementMatchers.not(ElementMatchers.isStatic()).and(ElementMatchers.isVisibleTo(this.instrumentedType)).and(this.matcher));
                                if (fieldList.size() == 1) {
                                    return (FieldDescription) fieldList.getOnly();
                                }
                                if (fieldList.size() <= 1) {
                                    typeDefinition = typeDefinition.getSuperClass();
                                } else {
                                    throw new IllegalStateException("Ambiguous field location of " + fieldList);
                                }
                            } while (typeDefinition != null);
                            throw new IllegalStateException("Cannot locate field matching " + this.matcher + " on " + typeDescription);
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class OfGivenField implements Factory {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((OfGivenField) obj).fieldDescription);
                }

                public int hashCode() {
                    return 527 + this.fieldDescription.hashCode();
                }

                public OfGivenField(FieldDescription fieldDescription2) {
                    this.fieldDescription = fieldDescription2;
                }

                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForFieldAccess(typeDescription, new FieldResolver.Simple(this.fieldDescription));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class OfMatchedField implements Factory {
                private final ElementMatcher<? super FieldDescription> matcher;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((OfMatchedField) obj).matcher);
                }

                public int hashCode() {
                    return 527 + this.matcher.hashCode();
                }

                public OfMatchedField(ElementMatcher<? super FieldDescription> elementMatcher) {
                    this.matcher = elementMatcher;
                }

                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForFieldAccess(typeDescription, new FieldResolver.ForElementMatcher(typeDescription, this.matcher));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodInvocation implements Substitution {
            private static final int THIS_REFERENCE = 0;
            private final TypeDescription instrumentedType;
            private final MethodResolver methodResolver;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForMethodInvocation forMethodInvocation = (ForMethodInvocation) obj;
                return this.instrumentedType.equals(forMethodInvocation.instrumentedType) && this.methodResolver.equals(forMethodInvocation.methodResolver);
            }

            public int hashCode() {
                return ((527 + this.instrumentedType.hashCode()) * 31) + this.methodResolver.hashCode();
            }

            public ForMethodInvocation(TypeDescription typeDescription, MethodResolver methodResolver2) {
                this.instrumentedType = typeDescription;
                this.methodResolver = methodResolver2;
            }

            public StackManipulation resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                TypeList.Generic generic3;
                MethodDescription resolve = this.methodResolver.resolve(typeDescription, byteCodeElement, generic, generic2);
                if (resolve.isAccessibleTo(this.instrumentedType)) {
                    if (resolve.isStatic()) {
                        generic3 = resolve.getParameters().asTypeList();
                    } else {
                        generic3 = new TypeList.Generic.Explicit((List<? extends TypeDefinition>) CompoundList.of(resolve.getDeclaringType(), resolve.getParameters().asTypeList()));
                    }
                    if (!resolve.getReturnType().asErasure().isAssignableTo(generic2.asErasure())) {
                        throw new IllegalStateException("Cannot assign return value of " + resolve + " to " + generic2);
                    } else if (generic3.size() == generic.size()) {
                        int i = 0;
                        while (i < generic3.size()) {
                            if (((TypeDescription.Generic) generic3.get(i)).asErasure().isAssignableTo(((TypeDescription.Generic) generic.get(i)).asErasure())) {
                                i++;
                            } else {
                                throw new IllegalStateException("Cannot invoke " + resolve + " on " + generic);
                            }
                        }
                        if (resolve.isVirtual()) {
                            return MethodInvocation.invoke(resolve).virtual(((TypeDescription.Generic) generic3.get(0)).asErasure());
                        }
                        return MethodInvocation.invoke(resolve);
                    } else {
                        throw new IllegalStateException("Cannot invoke " + resolve + " on " + generic);
                    }
                } else {
                    throw new IllegalStateException(this.instrumentedType + " cannot access " + resolve);
                }
            }

            public interface MethodResolver {
                MethodDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2);

                @HashCodeAndEqualsPlugin.Enhance
                public static class Simple implements MethodResolver {
                    private final MethodDescription methodDescription;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((Simple) obj).methodDescription);
                    }

                    public int hashCode() {
                        return 527 + this.methodDescription.hashCode();
                    }

                    public Simple(MethodDescription methodDescription2) {
                        this.methodDescription = methodDescription2;
                    }

                    public MethodDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        return this.methodDescription;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class Matching implements MethodResolver {
                    private final TypeDescription instrumentedType;
                    private final ElementMatcher<? super MethodDescription> matcher;
                    private final MethodGraph.Compiler methodGraphCompiler;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Matching matching = (Matching) obj;
                        return this.instrumentedType.equals(matching.instrumentedType) && this.methodGraphCompiler.equals(matching.methodGraphCompiler) && this.matcher.equals(matching.matcher);
                    }

                    public int hashCode() {
                        return ((((527 + this.instrumentedType.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.matcher.hashCode();
                    }

                    public Matching(TypeDescription typeDescription, MethodGraph.Compiler compiler, ElementMatcher<? super MethodDescription> elementMatcher) {
                        this.instrumentedType = typeDescription;
                        this.methodGraphCompiler = compiler;
                        this.matcher = elementMatcher;
                    }

                    public MethodDescription resolve(TypeDescription typeDescription, ByteCodeElement byteCodeElement, TypeList.Generic generic, TypeDescription.Generic generic2) {
                        if (generic.isEmpty()) {
                            throw new IllegalStateException("Cannot substitute parameterless instruction with " + byteCodeElement);
                        } else if (((TypeDescription.Generic) generic.get(0)).isPrimitive() || ((TypeDescription.Generic) generic.get(0)).isArray()) {
                            throw new IllegalStateException("Cannot invoke method on primitive or array type for " + byteCodeElement);
                        } else {
                            TypeDefinition typeDefinition = (TypeDefinition) generic.get(0);
                            List of = CompoundList.of(this.methodGraphCompiler.compile(typeDefinition, this.instrumentedType).listNodes().asMethodList().filter(this.matcher), (FilterableList) typeDefinition.getDeclaredMethods().filter(ElementMatchers.isPrivate().and(ElementMatchers.isVisibleTo(this.instrumentedType)).and(this.matcher)));
                            if (of.size() == 1) {
                                return (MethodDescription) of.get(0);
                            }
                            throw new IllegalStateException("Not exactly one method that matches " + this.matcher + ": " + of);
                        }
                    }
                }
            }

            enum OfInstrumentedMethod implements Factory {
                INSTANCE;

                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForMethodInvocation(typeDescription, new MethodResolver.Simple(methodDescription));
                }
            }

            public static class OfGivenMethod implements Factory {
                private final MethodDescription methodDescription;

                public OfGivenMethod(MethodDescription methodDescription2) {
                    this.methodDescription = methodDescription2;
                }

                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription2, TypePool typePool) {
                    return new ForMethodInvocation(typeDescription, new MethodResolver.Simple(this.methodDescription));
                }
            }

            public static class OfMatchedMethod implements Factory {
                private final ElementMatcher<? super MethodDescription> matcher;
                private final MethodGraph.Compiler methodGraphCompiler;

                public OfMatchedMethod(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
                    this.matcher = elementMatcher;
                    this.methodGraphCompiler = compiler;
                }

                public Substitution make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForMethodInvocation(typeDescription, new MethodResolver.Matching(typeDescription, this.methodGraphCompiler, this.matcher));
                }
            }
        }
    }

    protected interface Replacement {
        Binding bind(FieldDescription.InDefinedShape inDefinedShape, boolean z);

        Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, InvocationType invocationType);

        public interface Binding {
            boolean isBound();

            StackManipulation make(TypeList.Generic generic, TypeDescription.Generic generic2);

            public enum Unresolved implements Binding {
                INSTANCE;

                public boolean isBound() {
                    return false;
                }

                public StackManipulation make(TypeList.Generic generic, TypeDescription.Generic generic2) {
                    throw new IllegalStateException();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Resolved implements Binding {
                private final Substitution substitution;
                private final ByteCodeElement target;
                private final TypeDescription targetType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Resolved resolved = (Resolved) obj;
                    return this.targetType.equals(resolved.targetType) && this.target.equals(resolved.target) && this.substitution.equals(resolved.substitution);
                }

                public int hashCode() {
                    return ((((527 + this.targetType.hashCode()) * 31) + this.target.hashCode()) * 31) + this.substitution.hashCode();
                }

                public boolean isBound() {
                    return true;
                }

                protected Resolved(TypeDescription typeDescription, ByteCodeElement byteCodeElement, Substitution substitution2) {
                    this.targetType = typeDescription;
                    this.target = byteCodeElement;
                    this.substitution = substitution2;
                }

                public StackManipulation make(TypeList.Generic generic, TypeDescription.Generic generic2) {
                    return this.substitution.resolve(this.targetType, this.target, generic, generic2);
                }
            }
        }

        public interface Factory {
            Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool);

            @HashCodeAndEqualsPlugin.Enhance
            public static class Compound implements Factory {
                private final List<Factory> factories;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.factories.equals(((Compound) obj).factories);
                }

                public int hashCode() {
                    return 527 + this.factories.hashCode();
                }

                protected Compound(Factory... factoryArr) {
                    this((List<? extends Factory>) Arrays.asList(factoryArr));
                }

                protected Compound(List<? extends Factory> list) {
                    this.factories = new ArrayList();
                    for (Factory factory : list) {
                        if (factory instanceof Compound) {
                            this.factories.addAll(((Compound) factory).factories);
                        } else if (!(factory instanceof NoOp)) {
                            this.factories.add(factory);
                        }
                    }
                }

                public Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    ArrayList arrayList = new ArrayList();
                    for (Factory make : this.factories) {
                        arrayList.add(make.make(typeDescription, methodDescription, typePool));
                    }
                    return new ForFirstBinding(arrayList);
                }
            }
        }

        public enum InvocationType {
            VIRTUAL,
            SUPER,
            OTHER;

            protected static InvocationType of(int i, MethodDescription methodDescription) {
                if (i != 182) {
                    if (i == 183) {
                        return methodDescription.isVirtual() ? SUPER : OTHER;
                    }
                    if (i != 185) {
                        return OTHER;
                    }
                }
                return VIRTUAL;
            }

            /* access modifiers changed from: protected */
            public boolean matches(boolean z, boolean z2) {
                int i = AnonymousClass1.$SwitchMap$net$bytebuddy$asm$MemberSubstitution$Replacement$InvocationType[ordinal()];
                if (i == 1) {
                    return z;
                }
                if (i != 2) {
                    return true;
                }
                return z2;
            }
        }

        public enum NoOp implements Replacement, Factory {
            INSTANCE;

            public Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                return this;
            }

            public Binding bind(FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                return Binding.Unresolved.INSTANCE;
            }

            public Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, InvocationType invocationType) {
                return Binding.Unresolved.INSTANCE;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForElementMatchers implements Replacement {
            private final ElementMatcher<? super FieldDescription.InDefinedShape> fieldMatcher;
            private final boolean includeSuperCalls;
            private final boolean includeVirtualCalls;
            private final boolean matchFieldRead;
            private final boolean matchFieldWrite;
            private final ElementMatcher<? super MethodDescription> methodMatcher;
            private final Substitution substitution;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForElementMatchers forElementMatchers = (ForElementMatchers) obj;
                return this.matchFieldRead == forElementMatchers.matchFieldRead && this.matchFieldWrite == forElementMatchers.matchFieldWrite && this.includeVirtualCalls == forElementMatchers.includeVirtualCalls && this.includeSuperCalls == forElementMatchers.includeSuperCalls && this.fieldMatcher.equals(forElementMatchers.fieldMatcher) && this.methodMatcher.equals(forElementMatchers.methodMatcher) && this.substitution.equals(forElementMatchers.substitution);
            }

            public int hashCode() {
                return ((((((((((((527 + this.fieldMatcher.hashCode()) * 31) + this.methodMatcher.hashCode()) * 31) + (this.matchFieldRead ? 1 : 0)) * 31) + (this.matchFieldWrite ? 1 : 0)) * 31) + (this.includeVirtualCalls ? 1 : 0)) * 31) + (this.includeSuperCalls ? 1 : 0)) * 31) + this.substitution.hashCode();
            }

            protected ForElementMatchers(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, ElementMatcher<? super MethodDescription> elementMatcher2, boolean z, boolean z2, boolean z3, boolean z4, Substitution substitution2) {
                this.fieldMatcher = elementMatcher;
                this.methodMatcher = elementMatcher2;
                this.matchFieldRead = z;
                this.matchFieldWrite = z2;
                this.includeVirtualCalls = z3;
                this.includeSuperCalls = z4;
                this.substitution = substitution2;
            }

            public Binding bind(FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                if (!z ? this.matchFieldRead : this.matchFieldWrite) {
                    if (this.fieldMatcher.matches(inDefinedShape)) {
                        return new Binding.Resolved(inDefinedShape.getDeclaringType(), inDefinedShape, this.substitution);
                    }
                }
                return Binding.Unresolved.INSTANCE;
            }

            public Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, InvocationType invocationType) {
                return (!invocationType.matches(this.includeVirtualCalls, this.includeSuperCalls) || !this.methodMatcher.matches(methodDescription)) ? Binding.Unresolved.INSTANCE : new Binding.Resolved(typeDescription, methodDescription, this.substitution);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private final ElementMatcher<? super FieldDescription.InDefinedShape> fieldMatcher;
                private final boolean includeSuperCalls;
                private final boolean includeVirtualCalls;
                private final boolean matchFieldRead;
                private final boolean matchFieldWrite;
                private final ElementMatcher<? super MethodDescription> methodMatcher;
                private final Substitution.Factory substitutionFactory;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    return this.matchFieldRead == factory.matchFieldRead && this.matchFieldWrite == factory.matchFieldWrite && this.includeVirtualCalls == factory.includeVirtualCalls && this.includeSuperCalls == factory.includeSuperCalls && this.fieldMatcher.equals(factory.fieldMatcher) && this.methodMatcher.equals(factory.methodMatcher) && this.substitutionFactory.equals(factory.substitutionFactory);
                }

                public int hashCode() {
                    return ((((((((((((527 + this.fieldMatcher.hashCode()) * 31) + this.methodMatcher.hashCode()) * 31) + (this.matchFieldRead ? 1 : 0)) * 31) + (this.matchFieldWrite ? 1 : 0)) * 31) + (this.includeVirtualCalls ? 1 : 0)) * 31) + (this.includeSuperCalls ? 1 : 0)) * 31) + this.substitutionFactory.hashCode();
                }

                protected Factory(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, ElementMatcher<? super MethodDescription> elementMatcher2, boolean z, boolean z2, boolean z3, boolean z4, Substitution.Factory factory) {
                    this.fieldMatcher = elementMatcher;
                    this.methodMatcher = elementMatcher2;
                    this.matchFieldRead = z;
                    this.matchFieldWrite = z2;
                    this.includeVirtualCalls = z3;
                    this.includeSuperCalls = z4;
                    this.substitutionFactory = factory;
                }

                protected static Factory of(ElementMatcher<? super ByteCodeElement> elementMatcher, Substitution.Factory factory) {
                    return new Factory(elementMatcher, elementMatcher, true, true, true, true, factory);
                }

                protected static Factory ofField(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, boolean z, boolean z2, Substitution.Factory factory) {
                    return new Factory(elementMatcher, ElementMatchers.none(), z, z2, false, false, factory);
                }

                protected static Factory ofMethod(ElementMatcher<? super MethodDescription> elementMatcher, boolean z, boolean z2, Substitution.Factory factory) {
                    return new Factory(ElementMatchers.none(), elementMatcher, false, false, z, z2, factory);
                }

                public Replacement make(TypeDescription typeDescription, MethodDescription methodDescription, TypePool typePool) {
                    return new ForElementMatchers(this.fieldMatcher, this.methodMatcher, this.matchFieldRead, this.matchFieldWrite, this.includeVirtualCalls, this.includeSuperCalls, this.substitutionFactory.make(typeDescription, methodDescription, typePool));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForFirstBinding implements Replacement {
            private final List<? extends Replacement> replacements;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.replacements.equals(((ForFirstBinding) obj).replacements);
            }

            public int hashCode() {
                return 527 + this.replacements.hashCode();
            }

            protected ForFirstBinding(List<? extends Replacement> list) {
                this.replacements = list;
            }

            public Binding bind(FieldDescription.InDefinedShape inDefinedShape, boolean z) {
                for (Replacement bind : this.replacements) {
                    Binding bind2 = bind.bind(inDefinedShape, z);
                    if (bind2.isBound()) {
                        return bind2;
                    }
                }
                return Binding.Unresolved.INSTANCE;
            }

            public Binding bind(TypeDescription typeDescription, MethodDescription methodDescription, InvocationType invocationType) {
                for (Replacement bind : this.replacements) {
                    Binding bind2 = bind.bind(typeDescription, methodDescription, invocationType);
                    if (bind2.isBound()) {
                        return bind2;
                    }
                }
                return Binding.Unresolved.INSTANCE;
            }
        }
    }

    /* renamed from: net.bytebuddy.asm.MemberSubstitution$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$asm$MemberSubstitution$Replacement$InvocationType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                net.bytebuddy.asm.MemberSubstitution$Replacement$InvocationType[] r0 = net.bytebuddy.asm.MemberSubstitution.Replacement.InvocationType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$net$bytebuddy$asm$MemberSubstitution$Replacement$InvocationType = r0
                net.bytebuddy.asm.MemberSubstitution$Replacement$InvocationType r1 = net.bytebuddy.asm.MemberSubstitution.Replacement.InvocationType.VIRTUAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$net$bytebuddy$asm$MemberSubstitution$Replacement$InvocationType     // Catch:{ NoSuchFieldError -> 0x001d }
                net.bytebuddy.asm.MemberSubstitution$Replacement$InvocationType r1 = net.bytebuddy.asm.MemberSubstitution.Replacement.InvocationType.SUPER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.asm.MemberSubstitution.AnonymousClass1.<clinit>():void");
        }
    }

    protected static class SubstitutingMethodVisitor extends MethodVisitor {
        private final Implementation.Context implementationContext;
        private final TypeDescription instrumentedType;
        private final MethodGraph.Compiler methodGraphCompiler;
        private final Replacement replacement;
        private int stackSizeBuffer = 0;
        private final boolean strict;
        private final TypePool typePool;

        protected SubstitutingMethodVisitor(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodGraph.Compiler compiler, boolean z, Replacement replacement2, Implementation.Context context, TypePool typePool2) {
            super(OpenedClassReader.ASM_API, methodVisitor);
            this.instrumentedType = typeDescription;
            this.methodGraphCompiler = compiler;
            this.strict = z;
            this.replacement = replacement2;
            this.implementationContext = context;
            this.typePool = typePool2;
        }

        public void visitFieldInsn(int i, String str, String str2, String str3) {
            ElementMatcher.Junction junction;
            TypeDescription.Generic generic;
            TypeList.Generic generic2;
            TypePool.Resolution describe = this.typePool.describe(str.replace('/', '.'));
            if (describe.isResolved()) {
                FieldList<FieldDescription.InDefinedShape> declaredFields = describe.resolve().getDeclaredFields();
                if (this.strict) {
                    junction = ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3));
                } else {
                    junction = ElementMatchers.failSafe(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                }
                FieldList fieldList = (FieldList) declaredFields.filter(junction);
                if (!fieldList.isEmpty()) {
                    Replacement.Binding bind = this.replacement.bind((FieldDescription.InDefinedShape) fieldList.getOnly(), i == 181 || i == 179);
                    if (bind.isBound()) {
                        switch (i) {
                            case 178:
                                generic2 = new TypeList.Generic.Empty();
                                generic = ((FieldDescription.InDefinedShape) fieldList.getOnly()).getType();
                                break;
                            case 179:
                                generic2 = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) fieldList.getOnly()).getType());
                                generic = TypeDescription.Generic.VOID;
                                break;
                            case 180:
                                generic2 = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) fieldList.getOnly()).getDeclaringType());
                                generic = ((FieldDescription.InDefinedShape) fieldList.getOnly()).getType();
                                break;
                            case 181:
                                generic2 = new TypeList.Generic.Explicit(((FieldDescription.InDefinedShape) fieldList.getOnly()).getDeclaringType(), ((FieldDescription.InDefinedShape) fieldList.getOnly()).getType());
                                generic = TypeDescription.Generic.VOID;
                                break;
                            default:
                                throw new AssertionError();
                        }
                        this.stackSizeBuffer = Math.max(this.stackSizeBuffer, bind.make(generic2, generic).apply(this.mv, this.implementationContext).getMaximalSize() - generic.getStackSize().getSize());
                        return;
                    }
                } else if (this.strict) {
                    throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + "." + str2 + str3 + " using " + this.typePool);
                }
            } else if (this.strict) {
                throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + " using " + this.typePool);
            }
            super.visitFieldInsn(i, str, str2, str3);
        }

        public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
            MethodList methodList;
            TypeList.Generic generic;
            TypeDescription.Generic generic2;
            StackSize stackSize;
            ElementMatcher.Junction junction;
            ElementMatcher.Junction junction2;
            ElementMatcher.Junction junction3;
            ElementMatcher.Junction junction4;
            TypePool.Resolution describe = this.typePool.describe(str.replace('/', '.'));
            if (describe.isResolved()) {
                if (i == 183 && str2.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods = describe.resolve().getDeclaredMethods();
                    if (this.strict) {
                        junction4 = ElementMatchers.isConstructor().and(ElementMatchers.hasDescriptor(str3));
                    } else {
                        junction4 = ElementMatchers.failSafe(ElementMatchers.isConstructor().and(ElementMatchers.hasDescriptor(str3)));
                    }
                    methodList = (MethodList) declaredMethods.filter(junction4);
                } else if (i == 184 || i == 183) {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods2 = describe.resolve().getDeclaredMethods();
                    if (this.strict) {
                        junction = ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3));
                    } else {
                        junction = ElementMatchers.failSafe(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                    }
                    methodList = (MethodList) declaredMethods2.filter(junction);
                } else {
                    MethodList<MethodDescription.InDefinedShape> declaredMethods3 = describe.resolve().getDeclaredMethods();
                    if (this.strict) {
                        junction2 = ElementMatchers.isPrivate().and(ElementMatchers.not(ElementMatchers.isStatic())).and(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                    } else {
                        junction2 = ElementMatchers.failSafe(ElementMatchers.isPrivate().and(ElementMatchers.not(ElementMatchers.isStatic())).and(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3))));
                    }
                    methodList = (MethodList) declaredMethods3.filter(junction2);
                    if (methodList.isEmpty()) {
                        MethodList<?> asMethodList = this.methodGraphCompiler.compile(describe.resolve(), this.instrumentedType).listNodes().asMethodList();
                        if (this.strict) {
                            junction3 = ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3));
                        } else {
                            junction3 = ElementMatchers.failSafe(ElementMatchers.named(str2).and(ElementMatchers.hasDescriptor(str3)));
                        }
                        methodList = (MethodList) asMethodList.filter(junction3);
                    }
                }
                if (!methodList.isEmpty()) {
                    Replacement.Binding bind = this.replacement.bind(describe.resolve(), (MethodDescription) methodList.getOnly(), Replacement.InvocationType.of(i, (MethodDescription) methodList.getOnly()));
                    if (bind.isBound()) {
                        int i2 = this.stackSizeBuffer;
                        if (((MethodDescription) methodList.getOnly()).isStatic() || ((MethodDescription) methodList.getOnly()).isConstructor()) {
                            generic = ((MethodDescription) methodList.getOnly()).getParameters().asTypeList();
                        } else {
                            generic = new TypeList.Generic.Explicit((List<? extends TypeDefinition>) CompoundList.of(describe.resolve(), ((MethodDescription) methodList.getOnly()).getParameters().asTypeList()));
                        }
                        if (((MethodDescription) methodList.getOnly()).isConstructor()) {
                            generic2 = ((MethodDescription) methodList.getOnly()).getDeclaringType().asGenericType();
                        } else {
                            generic2 = ((MethodDescription) methodList.getOnly()).getReturnType();
                        }
                        int maximalSize = bind.make(generic, generic2).apply(this.mv, this.implementationContext).getMaximalSize();
                        if (((MethodDescription) methodList.getOnly()).isConstructor()) {
                            stackSize = StackSize.SINGLE;
                        } else {
                            stackSize = ((MethodDescription) methodList.getOnly()).getReturnType().getStackSize();
                        }
                        this.stackSizeBuffer = Math.max(i2, maximalSize - stackSize.getSize());
                        if (((MethodDescription) methodList.getOnly()).isConstructor()) {
                            this.stackSizeBuffer = Math.max(this.stackSizeBuffer, new StackManipulation.Compound(Duplication.SINGLE.flipOver(TypeDescription.OBJECT), Removal.SINGLE, Removal.SINGLE, Duplication.SINGLE.flipOver(TypeDescription.OBJECT), Removal.SINGLE, Removal.SINGLE).apply(this.mv, this.implementationContext).getMaximalSize() + StackSize.SINGLE.getSize());
                            return;
                        }
                        return;
                    }
                } else if (this.strict) {
                    throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + "." + str2 + str3 + " using " + this.typePool);
                }
            } else if (this.strict) {
                throw new IllegalStateException("Could not resolve " + str.replace('/', '.') + " using " + this.typePool);
            }
            super.visitMethodInsn(i, str, str2, str3, z);
        }

        public void visitMaxs(int i, int i2) {
            super.visitMaxs(i + this.stackSizeBuffer, i2);
        }
    }
}

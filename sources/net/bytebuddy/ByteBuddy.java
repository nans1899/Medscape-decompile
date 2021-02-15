package net.bytebuddy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.EnumerationState;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.TypeManifestation;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.ClassWriterStrategy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.inline.DecoratingDynamicTypeBuilder;
import net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer;
import net.bytebuddy.dynamic.scaffold.inline.RebaseDynamicTypeBuilder;
import net.bytebuddy.dynamic.scaffold.inline.RedefinitionDynamicTypeBuilder;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.dynamic.scaffold.subclass.SubclassDynamicTypeBuilder;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class ByteBuddy {
    private static final String BYTE_BUDDY_DEFAULT_PREFIX = "ByteBuddy";
    private static final String BYTE_BUDDY_DEFAULT_SUFFIX = "auxiliary";
    protected final AnnotationRetention annotationRetention;
    protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
    protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
    protected final ClassFileVersion classFileVersion;
    protected final ClassWriterStrategy classWriterStrategy;
    protected final LatentMatcher<? super MethodDescription> ignoredMethods;
    protected final Implementation.Context.Factory implementationContextFactory;
    protected final InstrumentedType.Factory instrumentedTypeFactory;
    protected final MethodGraph.Compiler methodGraphCompiler;
    protected final NamingStrategy namingStrategy;
    protected final TypeValidation typeValidation;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ByteBuddy byteBuddy = (ByteBuddy) obj;
        return this.annotationRetention.equals(byteBuddy.annotationRetention) && this.typeValidation.equals(byteBuddy.typeValidation) && this.classFileVersion.equals(byteBuddy.classFileVersion) && this.namingStrategy.equals(byteBuddy.namingStrategy) && this.auxiliaryTypeNamingStrategy.equals(byteBuddy.auxiliaryTypeNamingStrategy) && this.annotationValueFilterFactory.equals(byteBuddy.annotationValueFilterFactory) && this.implementationContextFactory.equals(byteBuddy.implementationContextFactory) && this.methodGraphCompiler.equals(byteBuddy.methodGraphCompiler) && this.instrumentedTypeFactory.equals(byteBuddy.instrumentedTypeFactory) && this.ignoredMethods.equals(byteBuddy.ignoredMethods) && this.classWriterStrategy.equals(byteBuddy.classWriterStrategy);
    }

    public int hashCode() {
        return ((((((((((((((((((((527 + this.classFileVersion.hashCode()) * 31) + this.namingStrategy.hashCode()) * 31) + this.auxiliaryTypeNamingStrategy.hashCode()) * 31) + this.annotationValueFilterFactory.hashCode()) * 31) + this.annotationRetention.hashCode()) * 31) + this.implementationContextFactory.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.instrumentedTypeFactory.hashCode()) * 31) + this.ignoredMethods.hashCode()) * 31) + this.typeValidation.hashCode()) * 31) + this.classWriterStrategy.hashCode();
    }

    public ByteBuddy() {
        this(ClassFileVersion.ofThisVm(ClassFileVersion.JAVA_V6));
    }

    public ByteBuddy(ClassFileVersion classFileVersion2) {
        this(classFileVersion2, new NamingStrategy.SuffixingRandom(BYTE_BUDDY_DEFAULT_PREFIX), new AuxiliaryType.NamingStrategy.SuffixingRandom(BYTE_BUDDY_DEFAULT_SUFFIX), AnnotationValueFilter.Default.APPEND_DEFAULTS, AnnotationRetention.ENABLED, Implementation.Context.Default.Factory.INSTANCE, MethodGraph.Compiler.DEFAULT, InstrumentedType.Factory.Default.MODIFIABLE, TypeValidation.ENABLED, ClassWriterStrategy.Default.CONSTANT_POOL_RETAINING, new LatentMatcher.Resolved(ElementMatchers.isSynthetic().or(ElementMatchers.isDefaultFinalizer())));
    }

    protected ByteBuddy(ClassFileVersion classFileVersion2, NamingStrategy namingStrategy2, AuxiliaryType.NamingStrategy namingStrategy3, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, InstrumentedType.Factory factory3, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, LatentMatcher<? super MethodDescription> latentMatcher) {
        this.classFileVersion = classFileVersion2;
        this.namingStrategy = namingStrategy2;
        this.auxiliaryTypeNamingStrategy = namingStrategy3;
        this.annotationValueFilterFactory = factory;
        this.annotationRetention = annotationRetention2;
        this.implementationContextFactory = factory2;
        this.methodGraphCompiler = compiler;
        this.instrumentedTypeFactory = factory3;
        this.typeValidation = typeValidation2;
        this.classWriterStrategy = classWriterStrategy2;
        this.ignoredMethods = latentMatcher;
    }

    public <T> DynamicType.Builder<T> subclass(Class<T> cls) {
        return subclass((TypeDefinition) TypeDescription.ForLoadedType.of(cls));
    }

    public <T> DynamicType.Builder<T> subclass(Class<T> cls, ConstructorStrategy constructorStrategy) {
        return subclass((TypeDefinition) TypeDescription.ForLoadedType.of(cls), constructorStrategy);
    }

    public DynamicType.Builder<?> subclass(Type type) {
        return subclass((TypeDefinition) TypeDefinition.Sort.describe(type));
    }

    public DynamicType.Builder<?> subclass(Type type, ConstructorStrategy constructorStrategy) {
        return subclass((TypeDefinition) TypeDefinition.Sort.describe(type), constructorStrategy);
    }

    public DynamicType.Builder<?> subclass(TypeDefinition typeDefinition) {
        return subclass(typeDefinition, (ConstructorStrategy) ConstructorStrategy.Default.IMITATE_SUPER_CLASS_OPENING);
    }

    public DynamicType.Builder<?> subclass(TypeDefinition typeDefinition, ConstructorStrategy constructorStrategy) {
        TypeList.Generic generic;
        TypeDescription.Generic generic2;
        TypeDefinition typeDefinition2 = typeDefinition;
        if (typeDefinition.isPrimitive() || typeDefinition.isArray() || typeDefinition.isFinal()) {
            throw new IllegalArgumentException("Cannot subclass primitive, array or final types: " + typeDefinition2);
        }
        if (typeDefinition.isInterface()) {
            generic2 = TypeDescription.Generic.OBJECT;
            generic = new TypeList.Generic.Explicit(typeDefinition2);
        } else {
            generic2 = typeDefinition.asGenericType();
            generic = new TypeList.Generic.Empty();
        }
        return new SubclassDynamicTypeBuilder(this.instrumentedTypeFactory.subclass(this.namingStrategy.subclass(typeDefinition.asGenericType()), ModifierContributor.Resolver.of(Visibility.PUBLIC, TypeManifestation.PLAIN).resolve(typeDefinition.getModifiers()), generic2).withInterfaces(generic), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, constructorStrategy);
    }

    public DynamicType.Builder<?> makeInterface() {
        return makeInterface((Collection<? extends TypeDefinition>) Collections.emptyList());
    }

    public <T> DynamicType.Builder<T> makeInterface(Class<T> cls) {
        return makeInterface((List<? extends Type>) Collections.singletonList(cls));
    }

    public DynamicType.Builder<?> makeInterface(Type... typeArr) {
        return makeInterface((List<? extends Type>) Arrays.asList(typeArr));
    }

    public DynamicType.Builder<?> makeInterface(List<? extends Type> list) {
        return makeInterface((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
    }

    public DynamicType.Builder<?> makeInterface(TypeDefinition... typeDefinitionArr) {
        return makeInterface((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
    }

    public DynamicType.Builder<?> makeInterface(Collection<? extends TypeDefinition> collection) {
        return subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).implement(collection).modifiers(TypeManifestation.INTERFACE, Visibility.PUBLIC);
    }

    public DynamicType.Builder<?> makePackage(String str) {
        InstrumentedType.Factory factory = this.instrumentedTypeFactory;
        return new SubclassDynamicTypeBuilder(factory.subclass(str + "." + PackageDescription.PACKAGE_CLASS_NAME, PackageDescription.PACKAGE_MODIFIERS, TypeDescription.Generic.OBJECT), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, ConstructorStrategy.Default.NO_CONSTRUCTORS);
    }

    public DynamicType.Builder<? extends Annotation> makeAnnotation() {
        return new SubclassDynamicTypeBuilder(this.instrumentedTypeFactory.subclass(this.namingStrategy.subclass(TypeDescription.Generic.ANNOTATION), ModifierContributor.Resolver.of(Visibility.PUBLIC, TypeManifestation.ANNOTATION).resolve(), TypeDescription.Generic.OBJECT).withInterfaces(new TypeList.Generic.Explicit(TypeDescription.Generic.ANNOTATION)), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, ConstructorStrategy.Default.NO_CONSTRUCTORS);
    }

    public DynamicType.Builder<? extends Enum<?>> makeEnumeration(String... strArr) {
        return makeEnumeration((Collection<? extends String>) Arrays.asList(strArr));
    }

    public DynamicType.Builder<? extends Enum<?>> makeEnumeration(Collection<? extends String> collection) {
        if (!collection.isEmpty()) {
            TypeDescription.Generic build = TypeDescription.Generic.Builder.parameterizedType((Class<?>) Enum.class, TargetType.class).build();
            InstrumentedType.WithFlexibleName subclass = this.instrumentedTypeFactory.subclass(this.namingStrategy.subclass(build), ModifierContributor.Resolver.of(Visibility.PUBLIC, TypeManifestation.FINAL, EnumerationState.ENUMERATION).resolve(), build);
            ClassFileVersion classFileVersion2 = this.classFileVersion;
            AuxiliaryType.NamingStrategy namingStrategy2 = this.auxiliaryTypeNamingStrategy;
            AnnotationValueFilter.Factory factory = this.annotationValueFilterFactory;
            AnnotationRetention annotationRetention2 = this.annotationRetention;
            Implementation.Context.Factory factory2 = this.implementationContextFactory;
            MethodGraph.Compiler compiler = this.methodGraphCompiler;
            TypeValidation typeValidation2 = this.typeValidation;
            ClassWriterStrategy classWriterStrategy2 = this.classWriterStrategy;
            ClassWriterStrategy classWriterStrategy3 = classWriterStrategy2;
            DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial defineConstructor = new SubclassDynamicTypeBuilder(subclass, classFileVersion2, namingStrategy2, factory, annotationRetention2, factory2, compiler, typeValidation2, classWriterStrategy3, this.ignoredMethods, ConstructorStrategy.Default.NO_CONSTRUCTORS).defineConstructor(Visibility.PRIVATE);
            Type[] typeArr = {String.class, Integer.TYPE};
            DynamicType.Builder.MethodDefinition.ExceptionDefinition<TargetType> withParameters = defineConstructor.withParameters(typeArr).intercept(SuperMethodCall.INSTANCE).defineMethod("valueOf", (Type) TargetType.class, Visibility.PUBLIC, Ownership.STATIC).withParameters(String.class);
            MethodCall withOwnType = MethodCall.invoke((MethodDescription) ((MethodList) build.getDeclaredMethods().filter(ElementMatchers.named("valueOf").and(ElementMatchers.takesArguments((Class<?>[]) new Class[]{Class.class, String.class})))).getOnly()).withOwnType();
            int[] iArr = {0};
            return withParameters.intercept(withOwnType.withArgument(iArr).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC)).defineMethod("values", (Type) TargetType[].class, Visibility.PUBLIC, Ownership.STATIC).intercept(new EnumerationImplementation(new ArrayList(collection)));
        }
        throw new IllegalArgumentException("Require at least one enumeration constant");
    }

    public <T> DynamicType.Builder<T> redefine(Class<T> cls) {
        return redefine(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public <T> DynamicType.Builder<T> redefine(Class<T> cls, ClassFileLocator classFileLocator) {
        return redefine(TypeDescription.ForLoadedType.of(cls), classFileLocator);
    }

    public <T> DynamicType.Builder<T> redefine(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        TypeDescription typeDescription2 = typeDescription;
        if (!typeDescription.isArray() && !typeDescription.isPrimitive()) {
            return new RedefinitionDynamicTypeBuilder(this.instrumentedTypeFactory.represent(typeDescription2), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, typeDescription, classFileLocator);
        }
        throw new IllegalArgumentException("Cannot redefine array or primitive type: " + typeDescription2);
    }

    public <T> DynamicType.Builder<T> rebase(Class<T> cls) {
        return rebase(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public <T> DynamicType.Builder<T> rebase(Class<T> cls, ClassFileLocator classFileLocator) {
        return rebase(TypeDescription.ForLoadedType.of(cls), classFileLocator);
    }

    public <T> DynamicType.Builder<T> rebase(Class<T> cls, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
        return rebase(TypeDescription.ForLoadedType.of(cls), classFileLocator, methodNameTransformer);
    }

    public <T> DynamicType.Builder<T> rebase(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        return rebase(typeDescription, classFileLocator, MethodNameTransformer.Suffixing.withRandomSuffix());
    }

    public <T> DynamicType.Builder<T> rebase(TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer) {
        TypeDescription typeDescription2 = typeDescription;
        if (!typeDescription.isArray() && !typeDescription.isPrimitive()) {
            return new RebaseDynamicTypeBuilder(this.instrumentedTypeFactory.represent(typeDescription2), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, typeDescription, classFileLocator, methodNameTransformer);
        }
        throw new IllegalArgumentException("Cannot rebase array or primitive type: " + typeDescription2);
    }

    public DynamicType.Builder<?> rebase(Package packageR, ClassFileLocator classFileLocator) {
        return rebase((PackageDescription) new PackageDescription.ForLoadedPackage(packageR), classFileLocator);
    }

    public DynamicType.Builder<?> rebase(PackageDescription packageDescription, ClassFileLocator classFileLocator) {
        return rebase((TypeDescription) new TypeDescription.ForPackageDescription(packageDescription), classFileLocator);
    }

    public <T> DynamicType.Builder<T> decorate(Class<T> cls) {
        return decorate(cls, ClassFileLocator.ForClassLoader.of(cls.getClassLoader()));
    }

    public <T> DynamicType.Builder<T> decorate(Class<T> cls, ClassFileLocator classFileLocator) {
        return decorate(TypeDescription.ForLoadedType.of(cls), classFileLocator);
    }

    public <T> DynamicType.Builder<T> decorate(TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        if (typeDescription.isArray() || typeDescription.isPrimitive()) {
            throw new IllegalArgumentException("Cannot decorate array or primitive type: " + typeDescription);
        }
        return new DecoratingDynamicTypeBuilder(typeDescription, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, classFileLocator);
    }

    public ByteBuddy with(ClassFileVersion classFileVersion2) {
        return new ByteBuddy(classFileVersion2, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(NamingStrategy namingStrategy2) {
        return new ByteBuddy(this.classFileVersion, namingStrategy2, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(AuxiliaryType.NamingStrategy namingStrategy2) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, namingStrategy2, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(AnnotationValueFilter.Factory factory) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, factory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(AnnotationRetention annotationRetention2) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, annotationRetention2, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(Implementation.Context.Factory factory) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, factory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(MethodGraph.Compiler compiler) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, compiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(InstrumentedType.Factory factory) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, factory, this.typeValidation, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(TypeValidation typeValidation2) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, typeValidation2, this.classWriterStrategy, this.ignoredMethods);
    }

    public ByteBuddy with(ClassWriterStrategy classWriterStrategy2) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, classWriterStrategy2, this.ignoredMethods);
    }

    public ByteBuddy ignore(ElementMatcher<? super MethodDescription> elementMatcher) {
        return ignore((LatentMatcher<? super MethodDescription>) new LatentMatcher.Resolved(elementMatcher));
    }

    public ByteBuddy ignore(LatentMatcher<? super MethodDescription> latentMatcher) {
        return new ByteBuddy(this.classFileVersion, this.namingStrategy, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.instrumentedTypeFactory, this.typeValidation, this.classWriterStrategy, latentMatcher);
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class EnumerationImplementation implements Implementation {
        protected static final String CLONE_METHOD_NAME = "clone";
        private static final int ENUM_FIELD_MODIFIERS = 25;
        private static final String ENUM_VALUES = "$VALUES";
        protected static final String ENUM_VALUES_METHOD_NAME = "values";
        protected static final String ENUM_VALUE_OF_METHOD_NAME = "valueOf";
        private final List<String> values;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.values.equals(((EnumerationImplementation) obj).values);
        }

        public int hashCode() {
            return 527 + this.values.hashCode();
        }

        protected EnumerationImplementation(List<String> list) {
            this.values = list;
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            for (String token : this.values) {
                instrumentedType = instrumentedType.withField(new FieldDescription.Token(token, 16409, TargetType.DESCRIPTION.asGenericType()));
            }
            return instrumentedType.withField(new FieldDescription.Token(ENUM_VALUES, 4121, TypeDescription.ArrayProjection.of(TargetType.DESCRIPTION).asGenericType())).withInitializer((ByteCodeAppender) new InitializationAppender(this.values));
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new ValuesMethodAppender(target.getInstrumentedType());
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class ValuesMethodAppender implements ByteCodeAppender {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ValuesMethodAppender) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            protected ValuesMethodAppender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                FieldDescription fieldDescription = (FieldDescription) ((FieldList) this.instrumentedType.getDeclaredFields().filter(ElementMatchers.named(EnumerationImplementation.ENUM_VALUES))).getOnly();
                return new ByteCodeAppender.Size(new StackManipulation.Compound(FieldAccess.forField(fieldDescription).read(), MethodInvocation.invoke((MethodDescription) ((MethodList) TypeDescription.Generic.OBJECT.getDeclaredMethods().filter(ElementMatchers.named(EnumerationImplementation.CLONE_METHOD_NAME))).getOnly()).virtual(fieldDescription.getType().asErasure()), TypeCasting.to(fieldDescription.getType().asErasure()), MethodReturn.REFERENCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class InitializationAppender implements ByteCodeAppender {
            private final List<String> values;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.values.equals(((InitializationAppender) obj).values);
            }

            public int hashCode() {
                return 527 + this.values.hashCode();
            }

            protected InitializationAppender(List<String> list) {
                this.values = list;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                TypeDescription asErasure = methodDescription.getDeclaringType().asErasure();
                MethodDescription methodDescription2 = (MethodDescription) ((MethodList) asErasure.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments((Class<?>[]) new Class[]{String.class, Integer.TYPE})))).getOnly();
                StackManipulation stackManipulation = StackManipulation.Trivial.INSTANCE;
                ArrayList<FieldDescription> arrayList = new ArrayList<>(this.values.size());
                int i = 0;
                for (String next : this.values) {
                    FieldDescription fieldDescription = (FieldDescription) ((FieldList) asErasure.getDeclaredFields().filter(ElementMatchers.named(next))).getOnly();
                    StackManipulation compound = new StackManipulation.Compound(stackManipulation, TypeCreation.of(asErasure), Duplication.SINGLE, new TextConstant(next), IntegerConstant.forValue(i), MethodInvocation.invoke(methodDescription2), FieldAccess.forField(fieldDescription).write());
                    arrayList.add(fieldDescription);
                    i++;
                    stackManipulation = compound;
                }
                ArrayList arrayList2 = new ArrayList(this.values.size());
                for (FieldDescription forField : arrayList) {
                    arrayList2.add(FieldAccess.forField(forField).read());
                }
                return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulation, ArrayFactory.forType(asErasure.asGenericType()).withValues(arrayList2), FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) asErasure.getDeclaredFields().filter(ElementMatchers.named(EnumerationImplementation.ENUM_VALUES))).getOnly()).write()).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }
    }
}

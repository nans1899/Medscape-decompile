package net.bytebuddy.implementation.bind.annotation;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.ExceptionMethod;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldProxy {
    Class<?> declaringType() default void.class;

    boolean serializableProxy() default false;

    String value() default "";

    @HashCodeAndEqualsPlugin.Enhance
    public static class Binder extends TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFieldBinding<FieldProxy> {
        private static final MethodDescription.InDefinedShape DECLARING_TYPE;
        private static final MethodDescription.InDefinedShape FIELD_NAME;
        private static final MethodDescription.InDefinedShape SERIALIZABLE_PROXY;
        private final FieldResolver.Factory fieldResolverFactory;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.fieldResolverFactory.equals(((Binder) obj).fieldResolverFactory);
        }

        public int hashCode() {
            return 527 + this.fieldResolverFactory.hashCode();
        }

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(FieldProxy.class).getDeclaredMethods();
            DECLARING_TYPE = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("declaringType"))).getOnly();
            FIELD_NAME = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("value"))).getOnly();
            SERIALIZABLE_PROXY = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("serializableProxy"))).getOnly();
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(Class<?> cls) {
            return install(TypeDescription.ForLoadedType.of(cls));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(TypeDescription typeDescription) {
            if (!typeDescription.isInterface()) {
                throw new IllegalArgumentException(typeDescription + " is not an interface");
            } else if (!typeDescription.getInterfaces().isEmpty()) {
                throw new IllegalArgumentException(typeDescription + " must not extend other interfaces");
            } else if (typeDescription.isPublic()) {
                MethodList methodList = (MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isAbstract());
                if (methodList.size() == 2) {
                    MethodList methodList2 = (MethodList) methodList.filter(ElementMatchers.isGetter((Class<?>) Object.class));
                    if (methodList2.size() == 1) {
                        MethodList methodList3 = (MethodList) methodList.filter(ElementMatchers.isSetter((Class<?>) Object.class));
                        if (methodList3.size() == 1) {
                            return new Binder(typeDescription, (MethodDescription.InDefinedShape) methodList2.getOnly(), (MethodDescription.InDefinedShape) methodList3.getOnly());
                        }
                        throw new IllegalArgumentException(typeDescription + " does not declare a setter with an Object type");
                    }
                    throw new IllegalArgumentException(typeDescription + " does not declare a getter with an Object type");
                }
                throw new IllegalArgumentException(typeDescription + " does not declare exactly two non-abstract methods");
            } else {
                throw new IllegalArgumentException(typeDescription + " is not public");
            }
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(Class<?> cls, Class<?> cls2) {
            return install(TypeDescription.ForLoadedType.of(cls), TypeDescription.ForLoadedType.of(cls2));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldProxy> install(TypeDescription typeDescription, TypeDescription typeDescription2) {
            MethodDescription.InDefinedShape onlyMethod = onlyMethod(typeDescription);
            if (!onlyMethod.getReturnType().asErasure().represents(Object.class)) {
                throw new IllegalArgumentException(onlyMethod + " must take a single Object-typed parameter");
            } else if (onlyMethod.getParameters().size() == 0) {
                MethodDescription.InDefinedShape onlyMethod2 = onlyMethod(typeDescription2);
                if (!onlyMethod2.getReturnType().asErasure().represents(Void.TYPE)) {
                    throw new IllegalArgumentException(onlyMethod2 + " must return void");
                } else if (onlyMethod2.getParameters().size() == 1 && ((ParameterDescription.InDefinedShape) onlyMethod2.getParameters().get(0)).getType().asErasure().represents(Object.class)) {
                    return new Binder(onlyMethod, onlyMethod2);
                } else {
                    throw new IllegalArgumentException(onlyMethod2 + " must declare a single Object-typed parameters");
                }
            } else {
                throw new IllegalArgumentException(onlyMethod + " must not declare parameters");
            }
        }

        private static MethodDescription.InDefinedShape onlyMethod(TypeDescription typeDescription) {
            if (!typeDescription.isInterface()) {
                throw new IllegalArgumentException(typeDescription + " is not an interface");
            } else if (!typeDescription.getInterfaces().isEmpty()) {
                throw new IllegalArgumentException(typeDescription + " must not extend other interfaces");
            } else if (typeDescription.isPublic()) {
                MethodList methodList = (MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isAbstract());
                if (methodList.size() == 1) {
                    return (MethodDescription.InDefinedShape) methodList.getOnly();
                }
                throw new IllegalArgumentException(typeDescription + " must declare exactly one abstract method");
            } else {
                throw new IllegalArgumentException(typeDescription + " is not public");
            }
        }

        protected Binder(MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
            this(new FieldResolver.Factory.Simplex(inDefinedShape, inDefinedShape2));
        }

        protected Binder(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
            this(new FieldResolver.Factory.Duplex(typeDescription, inDefinedShape, inDefinedShape2));
        }

        protected Binder(FieldResolver.Factory factory) {
            this.fieldResolverFactory = factory;
        }

        public Class<FieldProxy> getHandledType() {
            return FieldProxy.class;
        }

        /* access modifiers changed from: protected */
        public String fieldName(AnnotationDescription.Loadable<FieldProxy> loadable) {
            return (String) loadable.getValue(FIELD_NAME).resolve(String.class);
        }

        /* access modifiers changed from: protected */
        public TypeDescription declaringType(AnnotationDescription.Loadable<FieldProxy> loadable) {
            return (TypeDescription) loadable.getValue(DECLARING_TYPE).resolve(TypeDescription.class);
        }

        /* access modifiers changed from: protected */
        public MethodDelegationBinder.ParameterBinding<?> bind(FieldDescription fieldDescription, AnnotationDescription.Loadable<FieldProxy> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner) {
            FieldResolver resolve = this.fieldResolverFactory.resolve(parameterDescription.getType().asErasure(), fieldDescription);
            if (!resolve.isResolved()) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            return new MethodDelegationBinder.ParameterBinding.Anonymous(new AccessorProxy(fieldDescription, target.getInstrumentedType(), resolve, assigner, ((Boolean) loadable.getValue(SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue()));
        }

        protected interface FieldResolver {
            DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory);

            TypeDescription getProxyType();

            boolean isResolved();

            public interface Factory {
                FieldResolver resolve(TypeDescription typeDescription, FieldDescription fieldDescription);

                @HashCodeAndEqualsPlugin.Enhance
                public static class Duplex implements Factory {
                    private final MethodDescription.InDefinedShape getterMethod;
                    private final TypeDescription proxyType;
                    private final MethodDescription.InDefinedShape setterMethod;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Duplex duplex = (Duplex) obj;
                        return this.proxyType.equals(duplex.proxyType) && this.getterMethod.equals(duplex.getterMethod) && this.setterMethod.equals(duplex.setterMethod);
                    }

                    public int hashCode() {
                        return ((((527 + this.proxyType.hashCode()) * 31) + this.getterMethod.hashCode()) * 31) + this.setterMethod.hashCode();
                    }

                    protected Duplex(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
                        this.proxyType = typeDescription;
                        this.getterMethod = inDefinedShape;
                        this.setterMethod = inDefinedShape2;
                    }

                    public FieldResolver resolve(TypeDescription typeDescription, FieldDescription fieldDescription) {
                        if (typeDescription.equals(this.proxyType)) {
                            return new ForGetterSetterPair(this.proxyType, this.getterMethod, this.setterMethod);
                        }
                        throw new IllegalStateException("Cannot use @FieldProxy on a non-installed type");
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class Simplex implements Factory {
                    private final MethodDescription.InDefinedShape getterMethod;
                    private final MethodDescription.InDefinedShape setterMethod;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Simplex simplex = (Simplex) obj;
                        return this.getterMethod.equals(simplex.getterMethod) && this.setterMethod.equals(simplex.setterMethod);
                    }

                    public int hashCode() {
                        return ((527 + this.getterMethod.hashCode()) * 31) + this.setterMethod.hashCode();
                    }

                    protected Simplex(MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
                        this.getterMethod = inDefinedShape;
                        this.setterMethod = inDefinedShape2;
                    }

                    public FieldResolver resolve(TypeDescription typeDescription, FieldDescription fieldDescription) {
                        if (typeDescription.equals(this.getterMethod.getDeclaringType())) {
                            return new ForGetter(this.getterMethod);
                        }
                        if (typeDescription.equals(this.setterMethod.getDeclaringType())) {
                            return fieldDescription.isFinal() ? Unresolved.INSTANCE : new ForSetter(this.setterMethod);
                        }
                        throw new IllegalStateException("Cannot use @FieldProxy on a non-installed type");
                    }
                }
            }

            public enum Unresolved implements FieldResolver {
                INSTANCE;

                public boolean isResolved() {
                    return false;
                }

                public TypeDescription getProxyType() {
                    throw new IllegalStateException("Cannot read type for unresolved field resolver");
                }

                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    throw new IllegalStateException("Cannot apply unresolved field resolver");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForGetter implements FieldResolver {
                private final MethodDescription.InDefinedShape getterMethod;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.getterMethod.equals(((ForGetter) obj).getterMethod);
                }

                public int hashCode() {
                    return 527 + this.getterMethod.hashCode();
                }

                public boolean isResolved() {
                    return true;
                }

                protected ForGetter(MethodDescription.InDefinedShape inDefinedShape) {
                    this.getterMethod = inDefinedShape;
                }

                public TypeDescription getProxyType() {
                    return this.getterMethod.getDeclaringType();
                }

                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    return builder.method(ElementMatchers.definedMethod(ElementMatchers.is(this.getterMethod))).intercept(new FieldGetter(fieldDescription, assigner, methodAccessorFactory));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForSetter implements FieldResolver {
                private final MethodDescription.InDefinedShape setterMethod;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.setterMethod.equals(((ForSetter) obj).setterMethod);
                }

                public int hashCode() {
                    return 527 + this.setterMethod.hashCode();
                }

                public boolean isResolved() {
                    return true;
                }

                protected ForSetter(MethodDescription.InDefinedShape inDefinedShape) {
                    this.setterMethod = inDefinedShape;
                }

                public TypeDescription getProxyType() {
                    return this.setterMethod.getDeclaringType();
                }

                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    return builder.method(ElementMatchers.is(this.setterMethod)).intercept(new FieldSetter(fieldDescription, assigner, methodAccessorFactory));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForGetterSetterPair implements FieldResolver {
                private final MethodDescription.InDefinedShape getterMethod;
                private final TypeDescription proxyType;
                private final MethodDescription.InDefinedShape setterMethod;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForGetterSetterPair forGetterSetterPair = (ForGetterSetterPair) obj;
                    return this.proxyType.equals(forGetterSetterPair.proxyType) && this.getterMethod.equals(forGetterSetterPair.getterMethod) && this.setterMethod.equals(forGetterSetterPair.setterMethod);
                }

                public int hashCode() {
                    return ((((527 + this.proxyType.hashCode()) * 31) + this.getterMethod.hashCode()) * 31) + this.setterMethod.hashCode();
                }

                public boolean isResolved() {
                    return true;
                }

                protected ForGetterSetterPair(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodDescription.InDefinedShape inDefinedShape2) {
                    this.proxyType = typeDescription;
                    this.getterMethod = inDefinedShape;
                    this.setterMethod = inDefinedShape2;
                }

                public TypeDescription getProxyType() {
                    return this.proxyType;
                }

                public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, FieldDescription fieldDescription, Assigner assigner, MethodAccessorFactory methodAccessorFactory) {
                    Implementation implementation;
                    DynamicType.Builder.MethodDefinition.ImplementationDefinition<T> method = builder.method(ElementMatchers.is(this.getterMethod)).intercept(new FieldGetter(fieldDescription, assigner, methodAccessorFactory)).method(ElementMatchers.is(this.setterMethod));
                    if (fieldDescription.isFinal()) {
                        implementation = ExceptionMethod.throwing((Class<? extends Throwable>) UnsupportedOperationException.class, "Cannot set final field " + fieldDescription);
                    } else {
                        implementation = new FieldSetter(fieldDescription, assigner, methodAccessorFactory);
                    }
                    return method.intercept(implementation);
                }
            }
        }

        protected enum StaticFieldConstructor implements Implementation {
            INSTANCE;
            
            /* access modifiers changed from: private */
            public final MethodDescription objectTypeDefaultConstructor;

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            public ByteCodeAppender appender(Implementation.Target target) {
                return new ByteCodeAppender.Simple(MethodVariableAccess.loadThis(), MethodInvocation.invoke(this.objectTypeDefaultConstructor), MethodReturn.VOID);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class InstanceFieldConstructor implements Implementation {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((InstanceFieldConstructor) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            protected InstanceFieldConstructor(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType2) {
                return instrumentedType2.withField(new FieldDescription.Token("instance", 18, this.instrumentedType.asGenericType()));
            }

            public ByteCodeAppender appender(Implementation.Target target) {
                return new Appender(target);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Appender implements ByteCodeAppender {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Appender) obj).fieldDescription);
                }

                public int hashCode() {
                    return 527 + this.fieldDescription.hashCode();
                }

                protected Appender(Implementation.Target target) {
                    this.fieldDescription = (FieldDescription) ((FieldList) target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named("instance"))).getOnly();
                }

                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(StaticFieldConstructor.INSTANCE.objectTypeDefaultConstructor), MethodVariableAccess.allArgumentsOf((MethodDescription) methodDescription.asDefined()).prependThisReference(), FieldAccess.forField(this.fieldDescription).write(), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class FieldGetter implements Implementation {
            /* access modifiers changed from: private */
            public final Assigner assigner;
            /* access modifiers changed from: private */
            public final FieldDescription fieldDescription;
            /* access modifiers changed from: private */
            public final MethodAccessorFactory methodAccessorFactory;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                FieldGetter fieldGetter = (FieldGetter) obj;
                return this.fieldDescription.equals(fieldGetter.fieldDescription) && this.assigner.equals(fieldGetter.assigner) && this.methodAccessorFactory.equals(fieldGetter.methodAccessorFactory);
            }

            public int hashCode() {
                return ((((527 + this.fieldDescription.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.methodAccessorFactory.hashCode();
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected FieldGetter(FieldDescription fieldDescription2, Assigner assigner2, MethodAccessorFactory methodAccessorFactory2) {
                this.fieldDescription = fieldDescription2;
                this.assigner = assigner2;
                this.methodAccessorFactory = methodAccessorFactory2;
            }

            public ByteCodeAppender appender(Implementation.Target target) {
                return new Appender(target);
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class Appender implements ByteCodeAppender {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Appender appender = (Appender) obj;
                    return this.typeDescription.equals(appender.typeDescription) && FieldGetter.this.equals(FieldGetter.this);
                }

                public int hashCode() {
                    return ((527 + this.typeDescription.hashCode()) * 31) + FieldGetter.this.hashCode();
                }

                protected Appender(Implementation.Target target) {
                    this.typeDescription = target.getInstrumentedType();
                }

                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                    StackManipulation stackManipulation;
                    MethodDescription.InDefinedShape registerGetterFor = FieldGetter.this.methodAccessorFactory.registerGetterFor(FieldGetter.this.fieldDescription, MethodAccessorFactory.AccessType.DEFAULT);
                    StackManipulation[] stackManipulationArr = new StackManipulation[4];
                    if (FieldGetter.this.fieldDescription.isStatic()) {
                        stackManipulation = StackManipulation.Trivial.INSTANCE;
                    } else {
                        stackManipulation = new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) this.typeDescription.getDeclaredFields().filter(ElementMatchers.named("instance"))).getOnly()).read());
                    }
                    stackManipulationArr[0] = stackManipulation;
                    stackManipulationArr[1] = MethodInvocation.invoke((MethodDescription) registerGetterFor);
                    stackManipulationArr[2] = FieldGetter.this.assigner.assign(registerGetterFor.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC);
                    stackManipulationArr[3] = MethodReturn.of(methodDescription.getReturnType().asErasure());
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class FieldSetter implements Implementation {
            /* access modifiers changed from: private */
            public final Assigner assigner;
            /* access modifiers changed from: private */
            public final FieldDescription fieldDescription;
            /* access modifiers changed from: private */
            public final MethodAccessorFactory methodAccessorFactory;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                FieldSetter fieldSetter = (FieldSetter) obj;
                return this.fieldDescription.equals(fieldSetter.fieldDescription) && this.assigner.equals(fieldSetter.assigner) && this.methodAccessorFactory.equals(fieldSetter.methodAccessorFactory);
            }

            public int hashCode() {
                return ((((527 + this.fieldDescription.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.methodAccessorFactory.hashCode();
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected FieldSetter(FieldDescription fieldDescription2, Assigner assigner2, MethodAccessorFactory methodAccessorFactory2) {
                this.fieldDescription = fieldDescription2;
                this.assigner = assigner2;
                this.methodAccessorFactory = methodAccessorFactory2;
            }

            public ByteCodeAppender appender(Implementation.Target target) {
                return new Appender(target);
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class Appender implements ByteCodeAppender {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Appender appender = (Appender) obj;
                    return this.typeDescription.equals(appender.typeDescription) && FieldSetter.this.equals(FieldSetter.this);
                }

                public int hashCode() {
                    return ((527 + this.typeDescription.hashCode()) * 31) + FieldSetter.this.hashCode();
                }

                protected Appender(Implementation.Target target) {
                    this.typeDescription = target.getInstrumentedType();
                }

                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                    StackManipulation stackManipulation;
                    TypeDescription.Generic type = ((ParameterDescription) methodDescription.getParameters().get(0)).getType();
                    MethodDescription.InDefinedShape registerSetterFor = FieldSetter.this.methodAccessorFactory.registerSetterFor(FieldSetter.this.fieldDescription, MethodAccessorFactory.AccessType.DEFAULT);
                    StackManipulation[] stackManipulationArr = new StackManipulation[5];
                    if (FieldSetter.this.fieldDescription.isStatic()) {
                        stackManipulation = StackManipulation.Trivial.INSTANCE;
                    } else {
                        stackManipulation = new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) this.typeDescription.getDeclaredFields().filter(ElementMatchers.named("instance"))).getOnly()).read());
                    }
                    stackManipulationArr[0] = stackManipulation;
                    stackManipulationArr[1] = MethodVariableAccess.of(type).loadFrom(1);
                    stackManipulationArr[2] = FieldSetter.this.assigner.assign(type, ((ParameterDescription) registerSetterFor.getParameters().get(0)).getType(), Assigner.Typing.DYNAMIC);
                    stackManipulationArr[3] = MethodInvocation.invoke((MethodDescription) registerSetterFor);
                    stackManipulationArr[4] = MethodReturn.VOID;
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class AccessorProxy implements AuxiliaryType, StackManipulation {
            protected static final String FIELD_NAME = "instance";
            private final Assigner assigner;
            private final FieldDescription fieldDescription;
            private final FieldResolver fieldResolver;
            private final TypeDescription instrumentedType;
            private final boolean serializableProxy;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                AccessorProxy accessorProxy = (AccessorProxy) obj;
                return this.serializableProxy == accessorProxy.serializableProxy && this.fieldDescription.equals(accessorProxy.fieldDescription) && this.instrumentedType.equals(accessorProxy.instrumentedType) && this.fieldResolver.equals(accessorProxy.fieldResolver) && this.assigner.equals(accessorProxy.assigner) && Binder.this.equals(Binder.this);
            }

            public int hashCode() {
                return ((((((((((527 + this.fieldDescription.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + this.fieldResolver.hashCode()) * 31) + this.assigner.hashCode()) * 31) + (this.serializableProxy ? 1 : 0)) * 31) + Binder.this.hashCode();
            }

            public boolean isValid() {
                return true;
            }

            protected AccessorProxy(FieldDescription fieldDescription2, TypeDescription typeDescription, FieldResolver fieldResolver2, Assigner assigner2, boolean z) {
                this.fieldDescription = fieldDescription2;
                this.instrumentedType = typeDescription;
                this.fieldResolver = fieldResolver2;
                this.assigner = assigner2;
                this.serializableProxy = z;
            }

            public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
                List list;
                FieldResolver fieldResolver2 = this.fieldResolver;
                DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<T> defineConstructor = new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).subclass((TypeDefinition) this.fieldResolver.getProxyType(), (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement((Type[]) this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).defineConstructor(new ModifierContributor.ForMethod[0]);
                if (this.fieldDescription.isStatic()) {
                    list = Collections.emptyList();
                } else {
                    list = Collections.singletonList(this.instrumentedType);
                }
                return fieldResolver2.apply(defineConstructor.withParameters((Collection<? extends TypeDefinition>) list).intercept(this.fieldDescription.isStatic() ? StaticFieldConstructor.INSTANCE : new InstanceFieldConstructor(this.instrumentedType)), this.fieldDescription, this.assigner, methodAccessorFactory).make();
            }

            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                StackManipulation stackManipulation;
                TypeDescription register = context.register(this);
                StackManipulation[] stackManipulationArr = new StackManipulation[4];
                stackManipulationArr[0] = TypeCreation.of(register);
                stackManipulationArr[1] = Duplication.SINGLE;
                if (this.fieldDescription.isStatic()) {
                    stackManipulation = StackManipulation.Trivial.INSTANCE;
                } else {
                    stackManipulation = MethodVariableAccess.loadThis();
                }
                stackManipulationArr[2] = stackManipulation;
                stackManipulationArr[3] = MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly());
                return new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context);
            }
        }
    }
}

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
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Morph {
    boolean defaultMethod() default false;

    Class<?> defaultTarget() default void.class;

    boolean serializableProxy() default false;

    @HashCodeAndEqualsPlugin.Enhance
    public static class Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Morph> {
        private static final MethodDescription.InDefinedShape DEFAULT_METHOD;
        private static final MethodDescription.InDefinedShape DEFAULT_TARGET;
        private static final MethodDescription.InDefinedShape SERIALIZABLE_PROXY;
        private final MethodDescription forwardingMethod;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.forwardingMethod.equals(((Binder) obj).forwardingMethod);
        }

        public int hashCode() {
            return 527 + this.forwardingMethod.hashCode();
        }

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Morph.class).getDeclaredMethods();
            SERIALIZABLE_PROXY = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("serializableProxy"))).getOnly();
            DEFAULT_METHOD = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("defaultMethod"))).getOnly();
            DEFAULT_TARGET = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("defaultTarget"))).getOnly();
        }

        protected Binder(MethodDescription methodDescription) {
            this.forwardingMethod = methodDescription;
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Morph> install(Class<?> cls) {
            return install(TypeDescription.ForLoadedType.of(cls));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Morph> install(TypeDescription typeDescription) {
            return new Binder(onlyMethod(typeDescription));
        }

        private static MethodDescription onlyMethod(TypeDescription typeDescription) {
            if (!typeDescription.isInterface()) {
                throw new IllegalArgumentException(typeDescription + " is not an interface");
            } else if (!typeDescription.getInterfaces().isEmpty()) {
                throw new IllegalArgumentException(typeDescription + " must not extend other interfaces");
            } else if (typeDescription.isPublic()) {
                MethodList methodList = (MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isAbstract());
                if (methodList.size() == 1) {
                    MethodDescription methodDescription = (MethodDescription) methodList.getOnly();
                    if (!methodDescription.getReturnType().asErasure().represents(Object.class)) {
                        throw new IllegalArgumentException(methodDescription + " does not return an Object-type");
                    } else if (methodDescription.getParameters().size() == 1 && ((ParameterDescription) methodDescription.getParameters().get(0)).getType().asErasure().represents(Object[].class)) {
                        return methodDescription;
                    } else {
                        throw new IllegalArgumentException(methodDescription + " does not take a single argument of type Object[]");
                    }
                } else {
                    throw new IllegalArgumentException(typeDescription + " must declare exactly one abstract method");
                }
            } else {
                throw new IllegalArgumentException(typeDescription + " is mot public");
            }
        }

        public Class<Morph> getHandledType() {
            return Morph.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Morph> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            Implementation.SpecialMethodInvocation resolve;
            if (parameterDescription.getType().asErasure().equals(this.forwardingMethod.getDeclaringType())) {
                TypeDescription typeDescription = (TypeDescription) loadable.getValue(DEFAULT_TARGET).resolve(TypeDescription.class);
                if (!typeDescription.represents(Void.TYPE) || ((Boolean) loadable.getValue(DEFAULT_METHOD).resolve(Boolean.class)).booleanValue()) {
                    resolve = (typeDescription.represents(Void.TYPE) ? DefaultMethodLocator.Implicit.INSTANCE : new DefaultMethodLocator.Explicit(typeDescription)).resolve(target, methodDescription);
                } else {
                    resolve = target.invokeSuper(methodDescription.asSignatureToken());
                }
                Implementation.SpecialMethodInvocation specialMethodInvocation = resolve;
                if (!specialMethodInvocation.isValid()) {
                    return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new RedirectionProxy(this.forwardingMethod.getDeclaringType().asErasure(), target.getInstrumentedType(), specialMethodInvocation, assigner, ((Boolean) loadable.getValue(SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue()));
            }
            throw new IllegalStateException("Illegal use of @Morph for " + parameterDescription + " which was installed for " + this.forwardingMethod.getDeclaringType());
        }

        protected interface DefaultMethodLocator {
            Implementation.SpecialMethodInvocation resolve(Implementation.Target target, MethodDescription methodDescription);

            public enum Implicit implements DefaultMethodLocator {
                INSTANCE;

                public Implementation.SpecialMethodInvocation resolve(Implementation.Target target, MethodDescription methodDescription) {
                    return target.invokeDefault(methodDescription.asSignatureToken());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Explicit implements DefaultMethodLocator {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Explicit) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                public Explicit(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public Implementation.SpecialMethodInvocation resolve(Implementation.Target target, MethodDescription methodDescription) {
                    if (this.typeDescription.isInterface()) {
                        return target.invokeDefault(methodDescription.asSignatureToken(), this.typeDescription);
                    }
                    throw new IllegalStateException(methodDescription + " method carries default method call parameter on non-interface type");
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class RedirectionProxy implements AuxiliaryType, StackManipulation {
            protected static final String FIELD_NAME = "target";
            private final Assigner assigner;
            private final TypeDescription instrumentedType;
            private final TypeDescription morphingType;
            private final boolean serializableProxy;
            private final Implementation.SpecialMethodInvocation specialMethodInvocation;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                RedirectionProxy redirectionProxy = (RedirectionProxy) obj;
                return this.serializableProxy == redirectionProxy.serializableProxy && this.morphingType.equals(redirectionProxy.morphingType) && this.instrumentedType.equals(redirectionProxy.instrumentedType) && this.specialMethodInvocation.equals(redirectionProxy.specialMethodInvocation) && this.assigner.equals(redirectionProxy.assigner);
            }

            public int hashCode() {
                return ((((((((527 + this.morphingType.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + this.specialMethodInvocation.hashCode()) * 31) + this.assigner.hashCode()) * 31) + (this.serializableProxy ? 1 : 0);
            }

            public boolean isValid() {
                return true;
            }

            protected RedirectionProxy(TypeDescription typeDescription, TypeDescription typeDescription2, Implementation.SpecialMethodInvocation specialMethodInvocation2, Assigner assigner2, boolean z) {
                this.morphingType = typeDescription;
                this.instrumentedType = typeDescription2;
                this.specialMethodInvocation = specialMethodInvocation2;
                this.assigner = assigner2;
                this.serializableProxy = z;
            }

            public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
                List list;
                DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<T> defineConstructor = new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).subclass((TypeDefinition) this.morphingType, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement((Type[]) this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).defineConstructor(new ModifierContributor.ForMethod[0]);
                if (this.specialMethodInvocation.getMethodDescription().isStatic()) {
                    list = Collections.emptyList();
                } else {
                    list = Collections.singletonList(this.instrumentedType);
                }
                return defineConstructor.withParameters((Collection<? extends TypeDefinition>) list).intercept(this.specialMethodInvocation.getMethodDescription().isStatic() ? StaticFieldConstructor.INSTANCE : new InstanceFieldConstructor(this.instrumentedType)).method(ElementMatchers.isAbstract().and(ElementMatchers.isDeclaredBy(this.morphingType))).intercept(new MethodCall(methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT), this.assigner)).make();
            }

            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                StackManipulation stackManipulation;
                TypeDescription register = context.register(this);
                StackManipulation[] stackManipulationArr = new StackManipulation[4];
                stackManipulationArr[0] = TypeCreation.of(register);
                stackManipulationArr[1] = Duplication.SINGLE;
                if (this.specialMethodInvocation.getMethodDescription().isStatic()) {
                    stackManipulation = StackManipulation.Trivial.INSTANCE;
                } else {
                    stackManipulation = MethodVariableAccess.loadThis();
                }
                stackManipulationArr[2] = stackManipulation;
                stackManipulationArr[3] = MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly());
                return new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context);
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
                    return instrumentedType2.withField(new FieldDescription.Token("target", 18, this.instrumentedType.asGenericType()));
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
                        this.fieldDescription = (FieldDescription) ((FieldList) target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named("target"))).getOnly();
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(StaticFieldConstructor.INSTANCE.objectTypeDefaultConstructor), MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), FieldAccess.forField(this.fieldDescription).write(), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class MethodCall implements Implementation {
                /* access modifiers changed from: private */
                public final MethodDescription accessorMethod;
                /* access modifiers changed from: private */
                public final Assigner assigner;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    MethodCall methodCall = (MethodCall) obj;
                    return this.accessorMethod.equals(methodCall.accessorMethod) && this.assigner.equals(methodCall.assigner);
                }

                public int hashCode() {
                    return ((527 + this.accessorMethod.hashCode()) * 31) + this.assigner.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected MethodCall(MethodDescription methodDescription, Assigner assigner2) {
                    this.accessorMethod = methodDescription;
                    this.assigner = assigner2;
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
                        return this.typeDescription.equals(appender.typeDescription) && MethodCall.this.equals(MethodCall.this);
                    }

                    public int hashCode() {
                        return ((527 + this.typeDescription.hashCode()) * 31) + MethodCall.this.hashCode();
                    }

                    protected Appender(Implementation.Target target) {
                        this.typeDescription = target.getInstrumentedType();
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        StackManipulation stackManipulation;
                        StackManipulation loadFrom = MethodVariableAccess.REFERENCE.loadFrom(1);
                        StackManipulation[] stackManipulationArr = new StackManipulation[MethodCall.this.accessorMethod.getParameters().size()];
                        int i = 0;
                        for (TypeDescription.Generic assign : MethodCall.this.accessorMethod.getParameters().asTypeList()) {
                            stackManipulationArr[i] = new StackManipulation.Compound(loadFrom, IntegerConstant.forValue(i), ArrayAccess.REFERENCE.load(), MethodCall.this.assigner.assign(TypeDescription.Generic.OBJECT, assign, Assigner.Typing.DYNAMIC));
                            i++;
                        }
                        StackManipulation[] stackManipulationArr2 = new StackManipulation[5];
                        if (MethodCall.this.accessorMethod.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) this.typeDescription.getDeclaredFields().filter(ElementMatchers.named("target"))).getOnly()).read());
                        }
                        stackManipulationArr2[0] = stackManipulation;
                        stackManipulationArr2[1] = new StackManipulation.Compound(stackManipulationArr);
                        stackManipulationArr2[2] = MethodInvocation.invoke(MethodCall.this.accessorMethod);
                        stackManipulationArr2[3] = MethodCall.this.assigner.assign(MethodCall.this.accessorMethod.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC);
                        stackManipulationArr2[4] = MethodReturn.REFERENCE;
                        MethodVisitor methodVisitor2 = methodVisitor;
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr2).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }
        }
    }
}

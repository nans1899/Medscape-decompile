package net.bytebuddy.implementation.auxiliary;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

@HashCodeAndEqualsPlugin.Enhance
public class TypeProxy implements AuxiliaryType {
    public static final String INSTANCE_FIELD = "target";
    public static final String REFLECTION_METHOD = "make";
    private final boolean ignoreFinalizer;
    /* access modifiers changed from: private */
    public final Implementation.Target implementationTarget;
    /* access modifiers changed from: private */
    public final InvocationFactory invocationFactory;
    /* access modifiers changed from: private */
    public final TypeDescription proxiedType;
    private final boolean serializableProxy;

    public interface InvocationFactory {

        public enum Default implements InvocationFactory {
            SUPER_METHOD {
                public Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription) {
                    return target.invokeDominant(methodDescription.asSignatureToken());
                }
            },
            DEFAULT_METHOD {
                public Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription) {
                    return target.invokeDefault(methodDescription.asSignatureToken(), typeDescription);
                }
            }
        }

        Implementation.SpecialMethodInvocation invoke(Implementation.Target target, TypeDescription typeDescription, MethodDescription methodDescription);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypeProxy typeProxy = (TypeProxy) obj;
        return this.ignoreFinalizer == typeProxy.ignoreFinalizer && this.serializableProxy == typeProxy.serializableProxy && this.proxiedType.equals(typeProxy.proxiedType) && this.implementationTarget.equals(typeProxy.implementationTarget) && this.invocationFactory.equals(typeProxy.invocationFactory);
    }

    public int hashCode() {
        return ((((((((527 + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + this.invocationFactory.hashCode()) * 31) + (this.ignoreFinalizer ? 1 : 0)) * 31) + (this.serializableProxy ? 1 : 0);
    }

    public TypeProxy(TypeDescription typeDescription, Implementation.Target target, InvocationFactory invocationFactory2, boolean z, boolean z2) {
        this.proxiedType = typeDescription;
        this.implementationTarget = target;
        this.invocationFactory = invocationFactory2;
        this.ignoreFinalizer = z;
        this.serializableProxy = z2;
    }

    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        return new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).ignore((ElementMatcher<? super MethodDescription>) this.ignoreFinalizer ? ElementMatchers.isFinalizer() : ElementMatchers.none()).subclass((TypeDefinition) this.proxiedType).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement((Type[]) this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).method(ElementMatchers.any()).intercept(new MethodCall(methodAccessorFactory)).defineMethod(REFLECTION_METHOD, (Type) TargetType.class, Ownership.STATIC).intercept(SilentConstruction.INSTANCE).make();
    }

    protected enum AbstractMethodErrorThrow implements StackManipulation {
        INSTANCE;
        
        private final StackManipulation implementation;

        public boolean isValid() {
            return this.implementation.isValid();
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return this.implementation.apply(methodVisitor, context);
        }
    }

    protected enum SilentConstruction implements Implementation {
        INSTANCE;

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType());
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class Appender implements ByteCodeAppender {
            public static final String GET_DECLARED_CONSTRUCTOR_METHOD_DESCRIPTOR = "([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;";
            public static final String GET_DECLARED_CONSTRUCTOR_METHOD_NAME = "getDeclaredConstructor";
            public static final String GET_REFLECTION_FACTORY_METHOD_DESCRIPTOR = "()Lsun/reflect/ReflectionFactory;";
            public static final String GET_REFLECTION_FACTORY_METHOD_NAME = "getReflectionFactory";
            public static final String JAVA_LANG_CLASS_INTERNAL_NAME = "java/lang/Class";
            public static final String JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME = "java/lang/reflect/Constructor";
            public static final String JAVA_LANG_OBJECT_DESCRIPTOR = "Ljava/lang/Object;";
            public static final String JAVA_LANG_OBJECT_INTERNAL_NAME = "java/lang/Object";
            public static final String NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_DESCRIPTOR = "(Ljava/lang/Class;Ljava/lang/reflect/Constructor;)Ljava/lang/reflect/Constructor;";
            public static final String NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_NAME = "newConstructorForSerialization";
            public static final String NEW_INSTANCE_METHOD_DESCRIPTOR = "([Ljava/lang/Object;)Ljava/lang/Object;";
            public static final String NEW_INSTANCE_METHOD_NAME = "newInstance";
            public static final String REFLECTION_FACTORY_INTERNAL_NAME = "sun/reflect/ReflectionFactory";
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

            private Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                methodVisitor.visitMethodInsn(184, REFLECTION_FACTORY_INTERNAL_NAME, GET_REFLECTION_FACTORY_METHOD_NAME, GET_REFLECTION_FACTORY_METHOD_DESCRIPTOR, false);
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(this.instrumentedType.getDescriptor()));
                methodVisitor.visitLdcInsn(net.bytebuddy.jar.asm.Type.getType(JAVA_LANG_OBJECT_DESCRIPTOR));
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, JAVA_LANG_CLASS_INTERNAL_NAME);
                MethodVisitor methodVisitor2 = methodVisitor;
                methodVisitor2.visitMethodInsn(182, JAVA_LANG_CLASS_INTERNAL_NAME, GET_DECLARED_CONSTRUCTOR_METHOD_NAME, GET_DECLARED_CONSTRUCTOR_METHOD_DESCRIPTOR, false);
                methodVisitor2.visitMethodInsn(182, REFLECTION_FACTORY_INTERNAL_NAME, NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_NAME, NEW_CONSTRUCTOR_FOR_SERIALIZATION_METHOD_DESCRIPTOR, false);
                methodVisitor.visitInsn(3);
                methodVisitor.visitTypeInsn(189, JAVA_LANG_OBJECT_INTERNAL_NAME);
                methodVisitor.visitMethodInsn(182, JAVA_LANG_CONSTRUCTOR_INTERNAL_NAME, NEW_INSTANCE_METHOD_NAME, NEW_INSTANCE_METHOD_DESCRIPTOR, false);
                methodVisitor.visitTypeInsn(192, this.instrumentedType.getInternalName());
                methodVisitor.visitInsn(176);
                return new ByteCodeAppender.Size(4, 0);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForSuperMethodByConstructor implements StackManipulation {
        private final List<TypeDescription> constructorParameters;
        private final boolean ignoreFinalizer;
        private final Implementation.Target implementationTarget;
        private final TypeDescription proxiedType;
        private final boolean serializableProxy;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForSuperMethodByConstructor forSuperMethodByConstructor = (ForSuperMethodByConstructor) obj;
            return this.ignoreFinalizer == forSuperMethodByConstructor.ignoreFinalizer && this.serializableProxy == forSuperMethodByConstructor.serializableProxy && this.proxiedType.equals(forSuperMethodByConstructor.proxiedType) && this.implementationTarget.equals(forSuperMethodByConstructor.implementationTarget) && this.constructorParameters.equals(forSuperMethodByConstructor.constructorParameters);
        }

        public int hashCode() {
            return ((((((((527 + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + this.constructorParameters.hashCode()) * 31) + (this.ignoreFinalizer ? 1 : 0)) * 31) + (this.serializableProxy ? 1 : 0);
        }

        public boolean isValid() {
            return true;
        }

        public ForSuperMethodByConstructor(TypeDescription typeDescription, Implementation.Target target, List<TypeDescription> list, boolean z, boolean z2) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.constructorParameters = list;
            this.ignoreFinalizer = z;
            this.serializableProxy = z2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.SUPER_METHOD, this.ignoreFinalizer, this.serializableProxy));
            StackManipulation[] stackManipulationArr = new StackManipulation[this.constructorParameters.size()];
            int i = 0;
            for (TypeDescription of : this.constructorParameters) {
                stackManipulationArr[i] = DefaultValue.of(of);
                i++;
            }
            return new StackManipulation.Compound(TypeCreation.of(register), Duplication.SINGLE, new StackManipulation.Compound(stackManipulationArr), MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments((Iterable<? extends TypeDescription>) this.constructorParameters)))).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) register.getDeclaredFields().filter(ElementMatchers.named("target"))).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForSuperMethodByReflectionFactory implements StackManipulation {
        private final boolean ignoreFinalizer;
        private final Implementation.Target implementationTarget;
        private final TypeDescription proxiedType;
        private final boolean serializableProxy;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForSuperMethodByReflectionFactory forSuperMethodByReflectionFactory = (ForSuperMethodByReflectionFactory) obj;
            return this.ignoreFinalizer == forSuperMethodByReflectionFactory.ignoreFinalizer && this.serializableProxy == forSuperMethodByReflectionFactory.serializableProxy && this.proxiedType.equals(forSuperMethodByReflectionFactory.proxiedType) && this.implementationTarget.equals(forSuperMethodByReflectionFactory.implementationTarget);
        }

        public int hashCode() {
            return ((((((527 + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + (this.ignoreFinalizer ? 1 : 0)) * 31) + (this.serializableProxy ? 1 : 0);
        }

        public boolean isValid() {
            return true;
        }

        public ForSuperMethodByReflectionFactory(TypeDescription typeDescription, Implementation.Target target, boolean z, boolean z2) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.ignoreFinalizer = z;
            this.serializableProxy = z2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.SUPER_METHOD, this.ignoreFinalizer, this.serializableProxy));
            return new StackManipulation.Compound(MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.named(TypeProxy.REFLECTION_METHOD).and(ElementMatchers.takesArguments(0)))).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) register.getDeclaredFields().filter(ElementMatchers.named("target"))).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForDefaultMethod implements StackManipulation {
        private final Implementation.Target implementationTarget;
        private final TypeDescription proxiedType;
        private final boolean serializableProxy;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForDefaultMethod forDefaultMethod = (ForDefaultMethod) obj;
            return this.serializableProxy == forDefaultMethod.serializableProxy && this.proxiedType.equals(forDefaultMethod.proxiedType) && this.implementationTarget.equals(forDefaultMethod.implementationTarget);
        }

        public int hashCode() {
            return ((((527 + this.proxiedType.hashCode()) * 31) + this.implementationTarget.hashCode()) * 31) + (this.serializableProxy ? 1 : 0);
        }

        public boolean isValid() {
            return true;
        }

        public ForDefaultMethod(TypeDescription typeDescription, Implementation.Target target, boolean z) {
            this.proxiedType = typeDescription;
            this.implementationTarget = target;
            this.serializableProxy = z;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new TypeProxy(this.proxiedType, this.implementationTarget, InvocationFactory.Default.DEFAULT_METHOD, true, this.serializableProxy));
            return new StackManipulation.Compound(TypeCreation.of(register), Duplication.SINGLE, MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly()), Duplication.SINGLE, MethodVariableAccess.loadThis(), FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) register.getDeclaredFields().filter(ElementMatchers.named("target"))).getOnly()).write()).apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class MethodCall implements Implementation {
        /* access modifiers changed from: private */
        public final MethodAccessorFactory methodAccessorFactory;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MethodCall methodCall = (MethodCall) obj;
            return this.methodAccessorFactory.equals(methodCall.methodAccessorFactory) && TypeProxy.this.equals(TypeProxy.this);
        }

        public int hashCode() {
            return ((527 + this.methodAccessorFactory.hashCode()) * 31) + TypeProxy.this.hashCode();
        }

        protected MethodCall(MethodAccessorFactory methodAccessorFactory2) {
            this.methodAccessorFactory = methodAccessorFactory2;
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType.withField(new FieldDescription.Token("target", 65, TypeProxy.this.implementationTarget.getInstrumentedType().asGenericType()));
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType());
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Appender implements ByteCodeAppender {
            /* access modifiers changed from: private */
            public final StackManipulation fieldLoadingInstruction;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.fieldLoadingInstruction.equals(appender.fieldLoadingInstruction) && MethodCall.this.equals(MethodCall.this);
            }

            public int hashCode() {
                return ((527 + this.fieldLoadingInstruction.hashCode()) * 31) + MethodCall.this.hashCode();
            }

            protected Appender(TypeDescription typeDescription) {
                this.fieldLoadingInstruction = FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) typeDescription.getDeclaredFields().filter(ElementMatchers.named("target"))).getOnly()).read();
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                Implementation.SpecialMethodInvocation invoke = TypeProxy.this.invocationFactory.invoke(TypeProxy.this.implementationTarget, TypeProxy.this.proxiedType, methodDescription);
                return new ByteCodeAppender.Size((invoke.isValid() ? new AccessorMethodInvocation(methodDescription, invoke) : AbstractMethodErrorThrow.INSTANCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class AccessorMethodInvocation implements StackManipulation {
                private final MethodDescription instrumentedMethod;
                private final Implementation.SpecialMethodInvocation specialMethodInvocation;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AccessorMethodInvocation accessorMethodInvocation = (AccessorMethodInvocation) obj;
                    return this.instrumentedMethod.equals(accessorMethodInvocation.instrumentedMethod) && this.specialMethodInvocation.equals(accessorMethodInvocation.specialMethodInvocation) && Appender.this.equals(Appender.this);
                }

                public int hashCode() {
                    return ((((527 + this.instrumentedMethod.hashCode()) * 31) + this.specialMethodInvocation.hashCode()) * 31) + Appender.this.hashCode();
                }

                protected AccessorMethodInvocation(MethodDescription methodDescription, Implementation.SpecialMethodInvocation specialMethodInvocation2) {
                    this.instrumentedMethod = methodDescription;
                    this.specialMethodInvocation = specialMethodInvocation2;
                }

                public boolean isValid() {
                    return this.specialMethodInvocation.isValid();
                }

                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    MethodDescription.InDefinedShape registerAccessorFor = MethodCall.this.methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT);
                    return new StackManipulation.Compound(MethodVariableAccess.loadThis(), Appender.this.fieldLoadingInstruction, MethodVariableAccess.allArgumentsOf(this.instrumentedMethod).asBridgeOf(registerAccessorFor), MethodInvocation.invoke(registerAccessorFor), MethodReturn.of(this.instrumentedMethod.getReturnType())).apply(methodVisitor, context);
                }
            }
        }
    }
}

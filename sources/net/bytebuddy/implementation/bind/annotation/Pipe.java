package net.bytebuddy.implementation.bind.annotation;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
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
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
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
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Pipe {
    boolean serializableProxy() default false;

    @HashCodeAndEqualsPlugin.Enhance
    public static class Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Pipe> {
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

        protected Binder(MethodDescription methodDescription) {
            this.forwardingMethod = methodDescription;
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Pipe> install(Class<?> cls) {
            return install(TypeDescription.ForLoadedType.of(cls));
        }

        public static TargetMethodAnnotationDrivenBinder.ParameterBinder<Pipe> install(TypeDescription typeDescription) {
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
                    } else if (methodDescription.getParameters().size() == 1 && ((ParameterDescription) methodDescription.getParameters().getOnly()).getType().asErasure().represents(Object.class)) {
                        return methodDescription;
                    } else {
                        throw new IllegalArgumentException(methodDescription + " does not take a single Object-typed argument");
                    }
                } else {
                    throw new IllegalArgumentException(typeDescription + " must declare exactly one abstract method");
                }
            } else {
                throw new IllegalArgumentException(typeDescription + " is mot public");
            }
        }

        public Class<Pipe> getHandledType() {
            return Pipe.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Pipe> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            if (!parameterDescription.getType().asErasure().equals(this.forwardingMethod.getDeclaringType())) {
                throw new IllegalStateException("Illegal use of @Pipe for " + parameterDescription + " which was installed for " + this.forwardingMethod.getDeclaringType());
            } else if (methodDescription.isStatic()) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            } else {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new Redirection(this.forwardingMethod.getDeclaringType().asErasure(), methodDescription, assigner, loadable.loadSilent().serializableProxy()));
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class Redirection implements AuxiliaryType, StackManipulation {
            private static final String FIELD_NAME_PREFIX = "argument";
            private final Assigner assigner;
            private final TypeDescription forwardingType;
            private final boolean serializableProxy;
            private final MethodDescription sourceMethod;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Redirection redirection = (Redirection) obj;
                return this.serializableProxy == redirection.serializableProxy && this.forwardingType.equals(redirection.forwardingType) && this.sourceMethod.equals(redirection.sourceMethod) && this.assigner.equals(redirection.assigner);
            }

            public int hashCode() {
                return ((((((527 + this.forwardingType.hashCode()) * 31) + this.sourceMethod.hashCode()) * 31) + this.assigner.hashCode()) * 31) + (this.serializableProxy ? 1 : 0);
            }

            public boolean isValid() {
                return true;
            }

            protected Redirection(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner2, boolean z) {
                this.forwardingType = typeDescription;
                this.sourceMethod = methodDescription;
                this.assigner = assigner2;
                this.serializableProxy = z;
            }

            private static LinkedHashMap<String, TypeDescription> extractFields(MethodDescription methodDescription) {
                TypeList<TypeDescription> asErasures = methodDescription.getParameters().asTypeList().asErasures();
                LinkedHashMap<String, TypeDescription> linkedHashMap = new LinkedHashMap<>();
                int i = 0;
                for (TypeDescription put : asErasures) {
                    int i2 = i + 1;
                    linkedHashMap.put(fieldName(i), put);
                    i = i2;
                }
                return linkedHashMap;
            }

            private static String fieldName(int i) {
                return FIELD_NAME_PREFIX + i;
            }

            public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
                LinkedHashMap extractFields = extractFields(this.sourceMethod);
                DynamicType.Builder intercept = new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).subclass((TypeDefinition) this.forwardingType, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement((Type[]) this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).method(ElementMatchers.isAbstract().and(ElementMatchers.isDeclaredBy(this.forwardingType))).intercept(new MethodCall(this.sourceMethod, this.assigner)).defineConstructor(new ModifierContributor.ForMethod[0]).withParameters((Collection<? extends TypeDefinition>) extractFields.values()).intercept(ConstructorCall.INSTANCE);
                for (Map.Entry entry : extractFields.entrySet()) {
                    intercept = intercept.defineField((String) entry.getKey(), (TypeDefinition) entry.getValue(), Visibility.PRIVATE);
                }
                return intercept.make();
            }

            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                TypeDescription register = context.register(this);
                return new StackManipulation.Compound(TypeCreation.of(register), Duplication.SINGLE, MethodVariableAccess.allArgumentsOf(this.sourceMethod), MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly())).apply(methodVisitor, context);
            }

            protected enum ConstructorCall implements Implementation {
                INSTANCE;
                
                /* access modifiers changed from: private */
                public final MethodDescription.InDefinedShape objectTypeDefaultConstructor;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public ByteCodeAppender appender(Implementation.Target target) {
                    return new Appender(target.getInstrumentedType());
                }

                @HashCodeAndEqualsPlugin.Enhance
                private static class Appender implements ByteCodeAppender {
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
                        FieldList<FieldDescription.InDefinedShape> declaredFields = this.instrumentedType.getDeclaredFields();
                        StackManipulation[] stackManipulationArr = new StackManipulation[declaredFields.size()];
                        int i = 0;
                        for (FieldDescription forField : declaredFields) {
                            stackManipulationArr[i] = new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodVariableAccess.load((ParameterDescription) methodDescription.getParameters().get(i)), FieldAccess.forField(forField).write());
                            i++;
                        }
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodInvocation.invoke(ConstructorCall.INSTANCE.objectTypeDefaultConstructor), new StackManipulation.Compound(stackManipulationArr), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class MethodCall implements Implementation {
                /* access modifiers changed from: private */
                public final Assigner assigner;
                /* access modifiers changed from: private */
                public final MethodDescription redirectedMethod;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    MethodCall methodCall = (MethodCall) obj;
                    return this.redirectedMethod.equals(methodCall.redirectedMethod) && this.assigner.equals(methodCall.assigner);
                }

                public int hashCode() {
                    return ((527 + this.redirectedMethod.hashCode()) * 31) + this.assigner.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                private MethodCall(MethodDescription methodDescription, Assigner assigner2) {
                    this.redirectedMethod = methodDescription;
                    this.assigner = assigner2;
                }

                public ByteCodeAppender appender(Implementation.Target target) {
                    if (this.redirectedMethod.isAccessibleTo(target.getInstrumentedType())) {
                        return new Appender(target.getInstrumentedType());
                    }
                    throw new IllegalStateException("Cannot invoke " + this.redirectedMethod + " from outside of class via @Pipe proxy");
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                private class Appender implements ByteCodeAppender {
                    private final TypeDescription instrumentedType;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Appender appender = (Appender) obj;
                        return this.instrumentedType.equals(appender.instrumentedType) && MethodCall.this.equals(MethodCall.this);
                    }

                    public int hashCode() {
                        return ((527 + this.instrumentedType.hashCode()) * 31) + MethodCall.this.hashCode();
                    }

                    private Appender(TypeDescription typeDescription) {
                        this.instrumentedType = typeDescription;
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        FieldList<FieldDescription.InDefinedShape> declaredFields = this.instrumentedType.getDeclaredFields();
                        StackManipulation[] stackManipulationArr = new StackManipulation[declaredFields.size()];
                        int i = 0;
                        for (FieldDescription forField : declaredFields) {
                            stackManipulationArr[i] = new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField(forField).read());
                            i++;
                        }
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(1), MethodCall.this.assigner.assign(TypeDescription.Generic.OBJECT, MethodCall.this.redirectedMethod.getDeclaringType().asGenericType(), Assigner.Typing.DYNAMIC), new StackManipulation.Compound(stackManipulationArr), MethodInvocation.invoke(MethodCall.this.redirectedMethod), MethodCall.this.assigner.assign(MethodCall.this.redirectedMethod.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC), MethodReturn.REFERENCE).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                }
            }
        }
    }
}

package net.bytebuddy.implementation.auxiliary;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.subclass.ConstructorStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodAccessorFactory;
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

@HashCodeAndEqualsPlugin.Enhance
public class MethodCallProxy implements AuxiliaryType {
    private static final String FIELD_NAME_PREFIX = "argument";
    private final Assigner assigner;
    private final boolean serializableProxy;
    private final Implementation.SpecialMethodInvocation specialMethodInvocation;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MethodCallProxy methodCallProxy = (MethodCallProxy) obj;
        return this.serializableProxy == methodCallProxy.serializableProxy && this.specialMethodInvocation.equals(methodCallProxy.specialMethodInvocation) && this.assigner.equals(methodCallProxy.assigner);
    }

    public int hashCode() {
        return ((((527 + this.specialMethodInvocation.hashCode()) * 31) + (this.serializableProxy ? 1 : 0)) * 31) + this.assigner.hashCode();
    }

    public MethodCallProxy(Implementation.SpecialMethodInvocation specialMethodInvocation2, boolean z) {
        this(specialMethodInvocation2, z, Assigner.DEFAULT);
    }

    public MethodCallProxy(Implementation.SpecialMethodInvocation specialMethodInvocation2, boolean z, Assigner assigner2) {
        this.specialMethodInvocation = specialMethodInvocation2;
        this.serializableProxy = z;
        this.assigner = assigner2;
    }

    private static LinkedHashMap<String, TypeDescription> extractFields(MethodDescription methodDescription) {
        LinkedHashMap<String, TypeDescription> linkedHashMap = new LinkedHashMap<>();
        int i = 0;
        if (!methodDescription.isStatic()) {
            linkedHashMap.put(fieldName(0), methodDescription.getDeclaringType().asErasure());
            i = 1;
        }
        Iterator it = methodDescription.getParameters().iterator();
        while (it.hasNext()) {
            linkedHashMap.put(fieldName(i), ((ParameterDescription) it.next()).getType().asErasure());
            i++;
        }
        return linkedHashMap;
    }

    private static String fieldName(int i) {
        return FIELD_NAME_PREFIX + i;
    }

    public DynamicType make(String str, ClassFileVersion classFileVersion, MethodAccessorFactory methodAccessorFactory) {
        MethodDescription.InDefinedShape registerAccessorFor = methodAccessorFactory.registerAccessorFor(this.specialMethodInvocation, MethodAccessorFactory.AccessType.DEFAULT);
        LinkedHashMap extractFields = extractFields(registerAccessorFor);
        DynamicType.Builder intercept = new ByteBuddy(classFileVersion).with(TypeValidation.DISABLED).with((MethodGraph.Compiler) PrecomputedMethodGraph.INSTANCE).subclass(Object.class, (ConstructorStrategy) ConstructorStrategy.Default.NO_CONSTRUCTORS).name(str).modifiers(DEFAULT_TYPE_MODIFIER).implement(Runnable.class, Callable.class).intercept(new MethodCall(registerAccessorFor, this.assigner)).implement((Type[]) this.serializableProxy ? new Class[]{Serializable.class} : new Class[0]).defineConstructor(new ModifierContributor.ForMethod[0]).withParameters((Collection<? extends TypeDefinition>) extractFields.values()).intercept(ConstructorCall.INSTANCE);
        for (Map.Entry entry : extractFields.entrySet()) {
            intercept = intercept.defineField((String) entry.getKey(), (TypeDefinition) entry.getValue(), Visibility.PRIVATE);
        }
        return intercept.make();
    }

    protected enum PrecomputedMethodGraph implements MethodGraph.Compiler {
        INSTANCE;
        
        private final MethodGraph.Linked methodGraph;

        public MethodGraph.Linked compile(TypeDescription typeDescription) {
            return compile(typeDescription, typeDescription);
        }

        public MethodGraph.Linked compile(TypeDefinition typeDefinition, TypeDescription typeDescription) {
            return this.methodGraph;
        }
    }

    protected enum ConstructorCall implements Implementation {
        INSTANCE;
        
        /* access modifiers changed from: private */
        public final MethodDescription objectTypeDefaultConstructor;

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
            return new Appender(target.getInstrumentedType());
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Appender implements ByteCodeAppender {
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
                ArrayList arrayList = new ArrayList(declaredFields.size());
                for (FieldDescription forField : declaredFields) {
                    arrayList.add(new StackManipulation.Compound(MethodVariableAccess.loadThis(), FieldAccess.forField(forField).read()));
                }
                return new ByteCodeAppender.Size(new StackManipulation.Compound(new StackManipulation.Compound((List<? extends StackManipulation>) arrayList), MethodInvocation.invoke(MethodCall.this.accessorMethod), MethodCall.this.assigner.assign(MethodCall.this.accessorMethod.getReturnType(), methodDescription.getReturnType(), Assigner.Typing.DYNAMIC), MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class AssignableSignatureCall implements StackManipulation {
        private final boolean serializable;
        private final Implementation.SpecialMethodInvocation specialMethodInvocation;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AssignableSignatureCall assignableSignatureCall = (AssignableSignatureCall) obj;
            return this.serializable == assignableSignatureCall.serializable && this.specialMethodInvocation.equals(assignableSignatureCall.specialMethodInvocation);
        }

        public int hashCode() {
            return ((527 + this.specialMethodInvocation.hashCode()) * 31) + (this.serializable ? 1 : 0);
        }

        public boolean isValid() {
            return true;
        }

        public AssignableSignatureCall(Implementation.SpecialMethodInvocation specialMethodInvocation2, boolean z) {
            this.specialMethodInvocation = specialMethodInvocation2;
            this.serializable = z;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(new MethodCallProxy(this.specialMethodInvocation, this.serializable));
            return new StackManipulation.Compound(TypeCreation.of(register), Duplication.SINGLE, MethodVariableAccess.allArgumentsOf(this.specialMethodInvocation.getMethodDescription()).prependThisReference(), MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly())).apply(methodVisitor, context);
        }
    }
}

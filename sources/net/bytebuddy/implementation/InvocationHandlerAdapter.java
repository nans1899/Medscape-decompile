package net.bytebuddy.implementation;

import java.lang.reflect.InvocationHandler;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.RandomString;

@HashCodeAndEqualsPlugin.Enhance
public abstract class InvocationHandlerAdapter implements Implementation {
    private static final boolean CACHED = true;
    /* access modifiers changed from: private */
    public static final TypeDescription.Generic INVOCATION_HANDLER_TYPE = TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(InvocationHandler.class);
    private static final boolean PRIVILEGED = true;
    private static final boolean UNCACHED = false;
    private static final boolean UNPRIVILEGED = false;
    protected final Assigner assigner;
    protected final boolean cached;
    protected final String fieldName;
    protected final boolean privileged;

    public interface AssignerConfigurable extends Implementation {
        Implementation withAssigner(Assigner assigner);
    }

    public interface WithoutPrivilegeConfiguration extends AssignerConfigurable {
        AssignerConfigurable withPrivilegedLookup();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InvocationHandlerAdapter invocationHandlerAdapter = (InvocationHandlerAdapter) obj;
        return this.cached == invocationHandlerAdapter.cached && this.privileged == invocationHandlerAdapter.privileged && this.fieldName.equals(invocationHandlerAdapter.fieldName) && this.assigner.equals(invocationHandlerAdapter.assigner);
    }

    public int hashCode() {
        return ((((((527 + this.fieldName.hashCode()) * 31) + this.assigner.hashCode()) * 31) + (this.cached ? 1 : 0)) * 31) + (this.privileged ? 1 : 0);
    }

    public abstract Implementation withAssigner(Assigner assigner2);

    public abstract AssignerConfigurable withPrivilegedLookup();

    public abstract WithoutPrivilegeConfiguration withoutMethodCache();

    protected InvocationHandlerAdapter(String str, boolean z, boolean z2, Assigner assigner2) {
        this.fieldName = str;
        this.cached = z;
        this.privileged = z2;
        this.assigner = assigner2;
    }

    public static InvocationHandlerAdapter of(InvocationHandler invocationHandler) {
        return of(invocationHandler, "invocationHandler$" + RandomString.hashOf(invocationHandler.hashCode()));
    }

    public static InvocationHandlerAdapter of(InvocationHandler invocationHandler, String str) {
        return new ForInstance(str, true, false, Assigner.DEFAULT, invocationHandler);
    }

    public static InvocationHandlerAdapter toField(String str) {
        return toField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
    }

    public static InvocationHandlerAdapter toField(String str, FieldLocator.Factory factory) {
        return new ForField(str, true, false, Assigner.DEFAULT, factory);
    }

    private List<StackManipulation> argumentValuesOf(MethodDescription methodDescription) {
        TypeList.Generic<TypeDescription.Generic> asTypeList = methodDescription.getParameters().asTypeList();
        ArrayList arrayList = new ArrayList(asTypeList.size());
        int i = 1;
        for (TypeDescription.Generic generic : asTypeList) {
            arrayList.add(new StackManipulation.Compound(MethodVariableAccess.of(generic).loadFrom(i), this.assigner.assign(generic, TypeDescription.Generic.OBJECT, Assigner.Typing.STATIC)));
            i += generic.getStackSize().getSize();
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription, StackManipulation stackManipulation, FieldDescription fieldDescription) {
        MethodConstant.CanCache canCache;
        if (!methodDescription.isStatic()) {
            if (this.privileged) {
                canCache = MethodConstant.ofPrivileged((MethodDescription.InDefinedShape) methodDescription.asDefined());
            } else {
                canCache = MethodConstant.of((MethodDescription.InDefinedShape) methodDescription.asDefined());
            }
            StackManipulation[] stackManipulationArr = new StackManipulation[8];
            stackManipulationArr[0] = stackManipulation;
            stackManipulationArr[1] = FieldAccess.forField(fieldDescription).read();
            stackManipulationArr[2] = MethodVariableAccess.loadThis();
            StackManipulation stackManipulation2 = canCache;
            if (this.cached) {
                stackManipulation2 = canCache.cached();
            }
            stackManipulationArr[3] = stackManipulation2;
            stackManipulationArr[4] = ArrayFactory.forType(TypeDescription.Generic.OBJECT).withValues(argumentValuesOf(methodDescription));
            stackManipulationArr[5] = MethodInvocation.invoke((MethodDescription) INVOCATION_HANDLER_TYPE.getDeclaredMethods().getOnly());
            stackManipulationArr[6] = this.assigner.assign(TypeDescription.Generic.OBJECT, methodDescription.getReturnType(), Assigner.Typing.DYNAMIC);
            stackManipulationArr[7] = MethodReturn.of(methodDescription.getReturnType());
            return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }
        throw new IllegalStateException("It is not possible to apply an invocation handler onto the static method " + methodDescription);
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ForInstance extends InvocationHandlerAdapter implements WithoutPrivilegeConfiguration {
        private static final String PREFIX = "invocationHandler";
        protected final InvocationHandler invocationHandler;

        public boolean equals(Object obj) {
            if (!InvocationHandlerAdapter.super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.invocationHandler.equals(((ForInstance) obj).invocationHandler);
        }

        public int hashCode() {
            return (InvocationHandlerAdapter.super.hashCode() * 31) + this.invocationHandler.hashCode();
        }

        protected ForInstance(String str, boolean z, boolean z2, Assigner assigner, InvocationHandler invocationHandler2) {
            super(str, z, z2, assigner);
            this.invocationHandler = invocationHandler2;
        }

        public WithoutPrivilegeConfiguration withoutMethodCache() {
            return new ForInstance(this.fieldName, false, this.privileged, this.assigner, this.invocationHandler);
        }

        public Implementation withAssigner(Assigner assigner) {
            return new ForInstance(this.fieldName, this.cached, this.privileged, assigner, this.invocationHandler);
        }

        public AssignerConfigurable withPrivilegedLookup() {
            return new ForInstance(this.fieldName, this.cached, true, this.assigner, this.invocationHandler);
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType.withField(new FieldDescription.Token(this.fieldName, 4169, InvocationHandlerAdapter.INVOCATION_HANDLER_TYPE)).withInitializer((LoadedTypeInitializer) new LoadedTypeInitializer.ForStaticField(this.fieldName, this.invocationHandler));
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
                return this.instrumentedType.equals(appender.instrumentedType) && ForInstance.this.equals(ForInstance.this);
            }

            public int hashCode() {
                return ((527 + this.instrumentedType.hashCode()) * 31) + ForInstance.this.hashCode();
            }

            protected Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                return ForInstance.this.apply(methodVisitor, context, methodDescription, StackManipulation.Trivial.INSTANCE, (FieldDescription) ((FieldList) this.instrumentedType.getDeclaredFields().filter(ElementMatchers.named(ForInstance.this.fieldName).and(ElementMatchers.genericFieldType(InvocationHandlerAdapter.INVOCATION_HANDLER_TYPE)))).getOnly());
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ForField extends InvocationHandlerAdapter implements WithoutPrivilegeConfiguration {
        private final FieldLocator.Factory fieldLocatorFactory;

        public boolean equals(Object obj) {
            if (!InvocationHandlerAdapter.super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.fieldLocatorFactory.equals(((ForField) obj).fieldLocatorFactory);
        }

        public int hashCode() {
            return (InvocationHandlerAdapter.super.hashCode() * 31) + this.fieldLocatorFactory.hashCode();
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForField(String str, boolean z, boolean z2, Assigner assigner, FieldLocator.Factory factory) {
            super(str, z, z2, assigner);
            this.fieldLocatorFactory = factory;
        }

        public WithoutPrivilegeConfiguration withoutMethodCache() {
            return new ForField(this.fieldName, false, this.privileged, this.assigner, this.fieldLocatorFactory);
        }

        public Implementation withAssigner(Assigner assigner) {
            return new ForField(this.fieldName, this.cached, this.privileged, assigner, this.fieldLocatorFactory);
        }

        public AssignerConfigurable withPrivilegedLookup() {
            return new ForField(this.fieldName, this.cached, true, this.assigner, this.fieldLocatorFactory);
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            FieldLocator.Resolution locate = this.fieldLocatorFactory.make(target.getInstrumentedType()).locate(this.fieldName);
            if (!locate.isResolved()) {
                throw new IllegalStateException("Could not find a field named '" + this.fieldName + "' for " + target.getInstrumentedType());
            } else if (locate.getField().getType().asErasure().isAssignableTo((Class<?>) InvocationHandler.class)) {
                return new Appender(locate.getField());
            } else {
                throw new IllegalStateException("Field " + locate.getField() + " does not declare a type that is assignable to invocation handler");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Appender implements ByteCodeAppender {
            private final FieldDescription fieldDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.fieldDescription.equals(appender.fieldDescription) && ForField.this.equals(ForField.this);
            }

            public int hashCode() {
                return ((527 + this.fieldDescription.hashCode()) * 31) + ForField.this.hashCode();
            }

            protected Appender(FieldDescription fieldDescription2) {
                this.fieldDescription = fieldDescription2;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                StackManipulation stackManipulation;
                ForField forField = ForField.this;
                if (this.fieldDescription.isStatic()) {
                    stackManipulation = StackManipulation.Trivial.INSTANCE;
                } else {
                    stackManipulation = MethodVariableAccess.loadThis();
                }
                return forField.apply(methodVisitor, context, methodDescription, stackManipulation, this.fieldDescription);
            }
        }
    }
}

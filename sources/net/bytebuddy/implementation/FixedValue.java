package net.bytebuddy.implementation;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

@HashCodeAndEqualsPlugin.Enhance
public abstract class FixedValue implements Implementation {
    protected final Assigner assigner;
    protected final Assigner.Typing typing;

    public interface AssignerConfigurable extends Implementation {
        Implementation withAssigner(Assigner assigner, Assigner.Typing typing);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FixedValue fixedValue = (FixedValue) obj;
        return this.typing.equals(fixedValue.typing) && this.assigner.equals(fixedValue.assigner);
    }

    public int hashCode() {
        return ((527 + this.assigner.hashCode()) * 31) + this.typing.hashCode();
    }

    protected FixedValue(Assigner assigner2, Assigner.Typing typing2) {
        this.assigner = assigner2;
        this.typing = typing2;
    }

    public static AssignerConfigurable value(Object obj) {
        Class<?> cls = obj.getClass();
        if (cls == String.class) {
            return new ForPoolValue((StackManipulation) new TextConstant((String) obj), TypeDescription.STRING);
        }
        if (cls == Class.class) {
            return new ForPoolValue(ClassConstant.of(TypeDescription.ForLoadedType.of((Class) obj)), TypeDescription.CLASS);
        }
        if (cls == Boolean.class) {
            return new ForPoolValue(IntegerConstant.forValue(((Boolean) obj).booleanValue()), (Class<?>) Boolean.TYPE);
        }
        if (cls == Byte.class) {
            return new ForPoolValue(IntegerConstant.forValue((int) ((Byte) obj).byteValue()), (Class<?>) Byte.TYPE);
        }
        if (cls == Short.class) {
            return new ForPoolValue(IntegerConstant.forValue((int) ((Short) obj).shortValue()), (Class<?>) Short.TYPE);
        }
        if (cls == Character.class) {
            return new ForPoolValue(IntegerConstant.forValue((int) ((Character) obj).charValue()), (Class<?>) Character.TYPE);
        }
        if (cls == Integer.class) {
            return new ForPoolValue(IntegerConstant.forValue(((Integer) obj).intValue()), (Class<?>) Integer.TYPE);
        }
        if (cls == Long.class) {
            return new ForPoolValue(LongConstant.forValue(((Long) obj).longValue()), (Class<?>) Long.TYPE);
        }
        if (cls == Float.class) {
            return new ForPoolValue(FloatConstant.forValue(((Float) obj).floatValue()), (Class<?>) Float.TYPE);
        }
        if (cls == Double.class) {
            return new ForPoolValue(DoubleConstant.forValue(((Double) obj).doubleValue()), (Class<?>) Double.TYPE);
        }
        if (JavaType.METHOD_HANDLE.getTypeStub().isAssignableFrom(cls)) {
            return new ForPoolValue((StackManipulation) new JavaConstantValue(JavaConstant.MethodHandle.ofLoaded(obj)), cls);
        }
        if (JavaType.METHOD_TYPE.getTypeStub().represents(cls)) {
            return new ForPoolValue((StackManipulation) new JavaConstantValue(JavaConstant.MethodType.ofLoaded(obj)), cls);
        }
        return reference(obj);
    }

    public static AssignerConfigurable reference(Object obj) {
        return new ForValue(obj);
    }

    public static AssignerConfigurable reference(Object obj, String str) {
        return new ForValue(str, obj);
    }

    public static AssignerConfigurable value(TypeDescription typeDescription) {
        return new ForPoolValue(ClassConstant.of(typeDescription), TypeDescription.CLASS);
    }

    public static AssignerConfigurable value(JavaConstant javaConstant) {
        return new ForPoolValue((StackManipulation) new JavaConstantValue(javaConstant), javaConstant.getType());
    }

    public static AssignerConfigurable argument(int i) {
        if (i >= 0) {
            return new ForArgument(i);
        }
        throw new IllegalArgumentException("Argument index cannot be negative: " + i);
    }

    public static AssignerConfigurable self() {
        return new ForThisValue();
    }

    public static Implementation nullValue() {
        return ForNullValue.INSTANCE;
    }

    public static AssignerConfigurable originType() {
        return new ForOriginType();
    }

    /* access modifiers changed from: protected */
    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription, TypeDescription.Generic generic, StackManipulation stackManipulation) {
        StackManipulation assign = this.assigner.assign(generic, methodDescription.getReturnType(), this.typing);
        if (assign.isValid()) {
            return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulation, assign, MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }
        throw new IllegalArgumentException("Cannot return value of type " + generic + " for " + methodDescription);
    }

    protected enum ForNullValue implements Implementation, ByteCodeAppender {
        INSTANCE;

        public ByteCodeAppender appender(Implementation.Target target) {
            return this;
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            if (!methodDescription.getReturnType().isPrimitive()) {
                return new ByteCodeAppender.Simple(NullConstant.INSTANCE, MethodReturn.REFERENCE).apply(methodVisitor, context, methodDescription);
            }
            throw new IllegalStateException("Cannot return null from " + methodDescription);
        }
    }

    protected static class ForOriginType extends FixedValue implements AssignerConfigurable {
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForOriginType() {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        private ForOriginType(Assigner assigner, Assigner.Typing typing) {
            super(assigner, typing);
        }

        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForOriginType(assigner, typing);
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getOriginType().asErasure());
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Appender implements ByteCodeAppender {
            private final TypeDescription originType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.originType.equals(appender.originType) && ForOriginType.this.equals(ForOriginType.this);
            }

            public int hashCode() {
                return ((527 + this.originType.hashCode()) * 31) + ForOriginType.this.hashCode();
            }

            protected Appender(TypeDescription typeDescription) {
                this.originType = typeDescription;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                return ForOriginType.this.apply(methodVisitor, context, methodDescription, TypeDescription.CLASS.asGenericType(), ClassConstant.of(this.originType));
            }
        }
    }

    protected static class ForThisValue extends FixedValue implements AssignerConfigurable {
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForThisValue() {
            super(Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        private ForThisValue(Assigner assigner, Assigner.Typing typing) {
            super(assigner, typing);
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType());
        }

        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForThisValue(assigner, typing);
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

            protected Appender(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                if (methodDescription.isStatic() || !this.instrumentedType.isAssignableTo(methodDescription.getReturnType().asErasure())) {
                    throw new IllegalStateException("Cannot return 'this' from " + methodDescription);
                }
                return new ByteCodeAppender.Simple(MethodVariableAccess.loadThis(), MethodReturn.REFERENCE).apply(methodVisitor, context, methodDescription);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ForArgument extends FixedValue implements AssignerConfigurable, ByteCodeAppender {
        private final int index;

        public ByteCodeAppender appender(Implementation.Target target) {
            return this;
        }

        public boolean equals(Object obj) {
            if (!FixedValue.super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.index == ((ForArgument) obj).index;
        }

        public int hashCode() {
            return (FixedValue.super.hashCode() * 31) + this.index;
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForArgument(int i) {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC, i);
        }

        private ForArgument(Assigner assigner, Assigner.Typing typing, int i) {
            super(assigner, typing);
            this.index = i;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            if (methodDescription.getParameters().size() > this.index) {
                ParameterDescription parameterDescription = (ParameterDescription) methodDescription.getParameters().get(this.index);
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription), this.assigner.assign(parameterDescription.getType(), methodDescription.getReturnType(), this.typing), MethodReturn.of(methodDescription.getReturnType()));
                if (compound.isValid()) {
                    return new ByteCodeAppender.Size(compound.apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
                throw new IllegalStateException("Cannot assign " + methodDescription.getReturnType() + " to " + parameterDescription);
            }
            throw new IllegalStateException(methodDescription + " does not define a parameter with index " + this.index);
        }

        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForArgument(assigner, typing, this.index);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ForPoolValue extends FixedValue implements AssignerConfigurable, ByteCodeAppender {
        private final TypeDescription loadedType;
        private final StackManipulation valueLoadInstruction;

        public ByteCodeAppender appender(Implementation.Target target) {
            return this;
        }

        public boolean equals(Object obj) {
            if (!FixedValue.super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForPoolValue forPoolValue = (ForPoolValue) obj;
            return this.valueLoadInstruction.equals(forPoolValue.valueLoadInstruction) && this.loadedType.equals(forPoolValue.loadedType);
        }

        public int hashCode() {
            return (((FixedValue.super.hashCode() * 31) + this.valueLoadInstruction.hashCode()) * 31) + this.loadedType.hashCode();
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForPoolValue(StackManipulation stackManipulation, Class<?> cls) {
            this(stackManipulation, TypeDescription.ForLoadedType.of(cls));
        }

        protected ForPoolValue(StackManipulation stackManipulation, TypeDescription typeDescription) {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC, stackManipulation, typeDescription);
        }

        private ForPoolValue(Assigner assigner, Assigner.Typing typing, StackManipulation stackManipulation, TypeDescription typeDescription) {
            super(assigner, typing);
            this.valueLoadInstruction = stackManipulation;
            this.loadedType = typeDescription;
        }

        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForPoolValue(assigner, typing, this.valueLoadInstruction, this.loadedType);
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return apply(methodVisitor, context, methodDescription, this.loadedType.asGenericType(), this.valueLoadInstruction);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ForValue extends FixedValue implements AssignerConfigurable {
        private static final String PREFIX = "value";
        /* access modifiers changed from: private */
        public final String fieldName;
        /* access modifiers changed from: private */
        @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
        public final TypeDescription.Generic fieldType;
        private final Object value;

        public boolean equals(Object obj) {
            if (!FixedValue.super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForValue forValue = (ForValue) obj;
            return this.fieldName.equals(forValue.fieldName) && this.value.equals(forValue.value);
        }

        public int hashCode() {
            return (((FixedValue.super.hashCode() * 31) + this.fieldName.hashCode()) * 31) + this.value.hashCode();
        }

        protected ForValue(Object obj) {
            this("value$" + RandomString.hashOf(obj.hashCode()), obj);
        }

        protected ForValue(String str, Object obj) {
            this(Assigner.DEFAULT, Assigner.Typing.STATIC, str, obj);
        }

        private ForValue(Assigner assigner, Assigner.Typing typing, String str, Object obj) {
            super(assigner, typing);
            this.fieldName = str;
            this.value = obj;
            this.fieldType = TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(obj.getClass());
        }

        public Implementation withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForValue(assigner, typing, this.fieldName, this.value);
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType.withField(new FieldDescription.Token(this.fieldName, 4169, this.fieldType)).withInitializer((LoadedTypeInitializer) new LoadedTypeInitializer.ForStaticField(this.fieldName, this.value));
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new StaticFieldByteCodeAppender(target.getInstrumentedType());
        }

        @HashCodeAndEqualsPlugin.Enhance
        private class StaticFieldByteCodeAppender implements ByteCodeAppender {
            private final StackManipulation fieldGetAccess;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldGetAccess.equals(((StaticFieldByteCodeAppender) obj).fieldGetAccess);
            }

            public int hashCode() {
                return 527 + this.fieldGetAccess.hashCode();
            }

            private StaticFieldByteCodeAppender(TypeDescription typeDescription) {
                this.fieldGetAccess = FieldAccess.forField((FieldDescription.InDefinedShape) ((FieldList) typeDescription.getDeclaredFields().filter(ElementMatchers.named(ForValue.this.fieldName))).getOnly()).read();
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                ForValue forValue = ForValue.this;
                return forValue.apply(methodVisitor, context, methodDescription, forValue.fieldType, this.fieldGetAccess);
            }
        }
    }
}

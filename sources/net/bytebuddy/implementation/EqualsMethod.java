package net.bytebuddy.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.InstanceCheck;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

@HashCodeAndEqualsPlugin.Enhance
public class EqualsMethod implements Implementation {
    /* access modifiers changed from: private */
    public static final MethodDescription.InDefinedShape EQUALS = ((MethodDescription.InDefinedShape) ((MethodList) TypeDescription.OBJECT.getDeclaredMethods().filter(ElementMatchers.isEquals())).getOnly());
    private final Comparator<? super FieldDescription.InDefinedShape> comparator;
    private final ElementMatcher.Junction<? super FieldDescription.InDefinedShape> ignored;
    private final ElementMatcher.Junction<? super FieldDescription.InDefinedShape> nonNullable;
    private final SuperClassCheck superClassCheck;
    private final TypeCompatibilityCheck typeCompatibilityCheck;

    protected enum NaturalOrderComparator implements Comparator<FieldDescription.InDefinedShape> {
        INSTANCE;

        public int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            return 0;
        }
    }

    protected enum SuperClassCheck {
        DISABLED {
            /* access modifiers changed from: protected */
            public StackManipulation resolve(TypeDescription typeDescription) {
                return StackManipulation.Trivial.INSTANCE;
            }
        },
        ENABLED {
            /* access modifiers changed from: protected */
            public StackManipulation resolve(TypeDescription typeDescription) {
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass != null) {
                    return new StackManipulation.Compound(MethodVariableAccess.loadThis(), MethodVariableAccess.REFERENCE.loadFrom(1), MethodInvocation.invoke(EqualsMethod.EQUALS).special(superClass.asErasure()), ConditionalReturn.onZeroInteger());
                }
                throw new IllegalStateException(typeDescription + " does not declare a super class");
            }
        };

        /* access modifiers changed from: protected */
        public abstract StackManipulation resolve(TypeDescription typeDescription);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EqualsMethod equalsMethod = (EqualsMethod) obj;
        return this.superClassCheck.equals(equalsMethod.superClassCheck) && this.typeCompatibilityCheck.equals(equalsMethod.typeCompatibilityCheck) && this.ignored.equals(equalsMethod.ignored) && this.nonNullable.equals(equalsMethod.nonNullable) && this.comparator.equals(equalsMethod.comparator);
    }

    public int hashCode() {
        return ((((((((527 + this.superClassCheck.hashCode()) * 31) + this.typeCompatibilityCheck.hashCode()) * 31) + this.ignored.hashCode()) * 31) + this.nonNullable.hashCode()) * 31) + this.comparator.hashCode();
    }

    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return instrumentedType;
    }

    protected EqualsMethod(SuperClassCheck superClassCheck2) {
        this(superClassCheck2, TypeCompatibilityCheck.EXACT, ElementMatchers.none(), ElementMatchers.none(), NaturalOrderComparator.INSTANCE);
    }

    private EqualsMethod(SuperClassCheck superClassCheck2, TypeCompatibilityCheck typeCompatibilityCheck2, ElementMatcher.Junction<? super FieldDescription.InDefinedShape> junction, ElementMatcher.Junction<? super FieldDescription.InDefinedShape> junction2, Comparator<? super FieldDescription.InDefinedShape> comparator2) {
        this.superClassCheck = superClassCheck2;
        this.typeCompatibilityCheck = typeCompatibilityCheck2;
        this.ignored = junction;
        this.nonNullable = junction2;
        this.comparator = comparator2;
    }

    public static EqualsMethod requiringSuperClassEquality() {
        return new EqualsMethod(SuperClassCheck.ENABLED);
    }

    public static EqualsMethod isolated() {
        return new EqualsMethod(SuperClassCheck.DISABLED);
    }

    public EqualsMethod withIgnoredFields(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new EqualsMethod(this.superClassCheck, this.typeCompatibilityCheck, this.ignored.or(elementMatcher), this.nonNullable, this.comparator);
    }

    public EqualsMethod withNonNullableFields(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new EqualsMethod(this.superClassCheck, this.typeCompatibilityCheck, this.ignored, this.nonNullable.or(elementMatcher), this.comparator);
    }

    public EqualsMethod withPrimitiveTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_PRIMITIVE_TYPES);
    }

    public EqualsMethod withEnumerationTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_ENUMERATION_TYPES);
    }

    public EqualsMethod withPrimitiveWrapperTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_PRIMITIVE_WRAPPER_TYPES);
    }

    public EqualsMethod withStringTypedFieldsFirst() {
        return withFieldOrder(TypePropertyComparator.FOR_STRING_TYPES);
    }

    public EqualsMethod withFieldOrder(Comparator<? super FieldDescription.InDefinedShape> comparator2) {
        return new EqualsMethod(this.superClassCheck, this.typeCompatibilityCheck, this.ignored, this.nonNullable, new CompoundComparator((Comparator<? super FieldDescription.InDefinedShape>[]) new Comparator[]{this.comparator, comparator2}));
    }

    public Implementation withSubclassEquality() {
        return new EqualsMethod(this.superClassCheck, TypeCompatibilityCheck.SUBCLASS, this.ignored, this.nonNullable, this.comparator);
    }

    public ByteCodeAppender appender(Implementation.Target target) {
        if (!target.getInstrumentedType().isInterface()) {
            ArrayList arrayList = new ArrayList(target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.not(ElementMatchers.isStatic().or(this.ignored))));
            Collections.sort(arrayList, this.comparator);
            return new Appender(target.getInstrumentedType(), new StackManipulation.Compound(this.superClassCheck.resolve(target.getInstrumentedType()), MethodVariableAccess.loadThis(), MethodVariableAccess.REFERENCE.loadFrom(1), ConditionalReturn.onIdentity().returningTrue(), this.typeCompatibilityCheck.resolve(target.getInstrumentedType())), arrayList, this.nonNullable);
        }
        throw new IllegalStateException("Cannot implement meaningful equals method for " + target.getInstrumentedType());
    }

    protected enum TypeCompatibilityCheck {
        EXACT {
            public StackManipulation resolve(TypeDescription typeDescription) {
                return new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(1), ConditionalReturn.onNullValue(), MethodVariableAccess.REFERENCE.loadFrom(0), MethodInvocation.invoke(GET_CLASS), MethodVariableAccess.REFERENCE.loadFrom(1), MethodInvocation.invoke(GET_CLASS), ConditionalReturn.onNonIdentity());
            }
        },
        SUBCLASS {
            /* access modifiers changed from: protected */
            public StackManipulation resolve(TypeDescription typeDescription) {
                return new StackManipulation.Compound(MethodVariableAccess.REFERENCE.loadFrom(1), InstanceCheck.of(typeDescription), ConditionalReturn.onZeroInteger());
            }
        };
        
        protected static final MethodDescription.InDefinedShape GET_CLASS = null;

        /* access modifiers changed from: protected */
        public abstract StackManipulation resolve(TypeDescription typeDescription);

        static {
            GET_CLASS = (MethodDescription.InDefinedShape) ((MethodList) TypeDescription.ForLoadedType.of(Object.class).getDeclaredMethods().filter(ElementMatchers.named("getClass"))).getOnly();
        }
    }

    protected interface NullValueGuard {
        StackManipulation after();

        StackManipulation before();

        int getRequiredVariablePadding();

        public enum NoOp implements NullValueGuard {
            INSTANCE;

            public StackManipulation before() {
                return StackManipulation.Trivial.INSTANCE;
            }

            public StackManipulation after() {
                return StackManipulation.Trivial.INSTANCE;
            }

            public int getRequiredVariablePadding() {
                return StackSize.ZERO.getSize();
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class UsingJump implements NullValueGuard {
            /* access modifiers changed from: private */
            public static final Object[] EMPTY = new Object[0];
            /* access modifiers changed from: private */
            public static final Object[] REFERENCE = {Type.getInternalName(Object.class)};
            /* access modifiers changed from: private */
            public final Label endOfBlock = new Label();
            /* access modifiers changed from: private */
            public final Label firstValueNull = new Label();
            /* access modifiers changed from: private */
            public final MethodDescription instrumentedMethod;
            /* access modifiers changed from: private */
            public final Label secondValueNull = new Label();

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                UsingJump usingJump = (UsingJump) obj;
                return this.instrumentedMethod.equals(usingJump.instrumentedMethod) && this.firstValueNull.equals(usingJump.firstValueNull) && this.secondValueNull.equals(usingJump.secondValueNull) && this.endOfBlock.equals(usingJump.endOfBlock);
            }

            public int getRequiredVariablePadding() {
                return 2;
            }

            public int hashCode() {
                return ((((((527 + this.instrumentedMethod.hashCode()) * 31) + this.firstValueNull.hashCode()) * 31) + this.secondValueNull.hashCode()) * 31) + this.endOfBlock.hashCode();
            }

            protected UsingJump(MethodDescription methodDescription) {
                this.instrumentedMethod = methodDescription;
            }

            public StackManipulation before() {
                return new BeforeInstruction();
            }

            public StackManipulation after() {
                return new AfterInstruction();
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class BeforeInstruction implements StackManipulation {
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && UsingJump.this.equals(UsingJump.this);
                }

                public int hashCode() {
                    return 527 + UsingJump.this.hashCode();
                }

                public boolean isValid() {
                    return true;
                }

                protected BeforeInstruction() {
                }

                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    methodVisitor.visitVarInsn(58, UsingJump.this.instrumentedMethod.getStackSize());
                    methodVisitor.visitVarInsn(58, UsingJump.this.instrumentedMethod.getStackSize() + 1);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize() + 1);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize());
                    methodVisitor.visitJumpInsn(198, UsingJump.this.secondValueNull);
                    methodVisitor.visitJumpInsn(198, UsingJump.this.firstValueNull);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize() + 1);
                    methodVisitor.visitVarInsn(25, UsingJump.this.instrumentedMethod.getStackSize());
                    return new StackManipulation.Size(0, 0);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
            protected class AfterInstruction implements StackManipulation {
                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && UsingJump.this.equals(UsingJump.this);
                }

                public int hashCode() {
                    return 527 + UsingJump.this.hashCode();
                }

                public boolean isValid() {
                    return true;
                }

                protected AfterInstruction() {
                }

                public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                    methodVisitor.visitJumpInsn(167, UsingJump.this.endOfBlock);
                    methodVisitor.visitLabel(UsingJump.this.secondValueNull);
                    if (context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V6)) {
                        methodVisitor.visitFrame(4, UsingJump.EMPTY.length, UsingJump.EMPTY, UsingJump.REFERENCE.length, UsingJump.REFERENCE);
                    }
                    methodVisitor.visitJumpInsn(198, UsingJump.this.endOfBlock);
                    methodVisitor.visitLabel(UsingJump.this.firstValueNull);
                    if (context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V6)) {
                        methodVisitor.visitFrame(3, UsingJump.EMPTY.length, UsingJump.EMPTY, UsingJump.EMPTY.length, UsingJump.EMPTY);
                    }
                    methodVisitor.visitInsn(3);
                    methodVisitor.visitInsn(172);
                    methodVisitor.visitLabel(UsingJump.this.endOfBlock);
                    if (context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V6)) {
                        methodVisitor.visitFrame(3, UsingJump.EMPTY.length, UsingJump.EMPTY, UsingJump.EMPTY.length, UsingJump.EMPTY);
                    }
                    return new StackManipulation.Size(0, 0);
                }
            }
        }
    }

    protected enum ValueComparator implements StackManipulation {
        LONG {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(148);
                return new StackManipulation.Size(-2, 0);
            }
        },
        FLOAT {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(149);
                return new StackManipulation.Size(-1, 0);
            }
        },
        DOUBLE {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(151);
                return new StackManipulation.Size(-2, 0);
            }
        },
        BOOLEAN_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([Z[Z)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        BYTE_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([B[B)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        SHORT_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([S[S)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        CHARACTER_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([C[C)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        INTEGER_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([I[I)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        LONG_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([J[J)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        FLOAT_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([F[F)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        DOUBLE_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([D[D)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        REFERENCE_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "equals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        },
        NESTED_ARRAY {
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitMethodInsn(184, "java/util/Arrays", "deepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", false);
                return new StackManipulation.Size(-1, 0);
            }
        };

        public boolean isValid() {
            return true;
        }

        public static StackManipulation of(TypeDefinition typeDefinition) {
            if (typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Byte.TYPE) || typeDefinition.represents(Short.TYPE) || typeDefinition.represents(Character.TYPE) || typeDefinition.represents(Integer.TYPE)) {
                return ConditionalReturn.onNonEqualInteger();
            }
            if (typeDefinition.represents(Long.TYPE)) {
                return new StackManipulation.Compound(LONG, ConditionalReturn.onNonZeroInteger());
            } else if (typeDefinition.represents(Float.TYPE)) {
                return new StackManipulation.Compound(FLOAT, ConditionalReturn.onNonZeroInteger());
            } else if (typeDefinition.represents(Double.TYPE)) {
                return new StackManipulation.Compound(DOUBLE, ConditionalReturn.onNonZeroInteger());
            } else if (typeDefinition.represents(boolean[].class)) {
                return new StackManipulation.Compound(BOOLEAN_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.represents(byte[].class)) {
                return new StackManipulation.Compound(BYTE_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.represents(short[].class)) {
                return new StackManipulation.Compound(SHORT_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.represents(char[].class)) {
                return new StackManipulation.Compound(CHARACTER_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.represents(int[].class)) {
                return new StackManipulation.Compound(INTEGER_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.represents(long[].class)) {
                return new StackManipulation.Compound(LONG_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.represents(float[].class)) {
                return new StackManipulation.Compound(FLOAT_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.represents(double[].class)) {
                return new StackManipulation.Compound(DOUBLE_ARRAY, ConditionalReturn.onZeroInteger());
            } else if (typeDefinition.isArray()) {
                StackManipulation[] stackManipulationArr = new StackManipulation[2];
                stackManipulationArr[0] = typeDefinition.getComponentType().isArray() ? NESTED_ARRAY : REFERENCE_ARRAY;
                stackManipulationArr[1] = ConditionalReturn.onZeroInteger();
                return new StackManipulation.Compound(stackManipulationArr);
            } else {
                return new StackManipulation.Compound(MethodInvocation.invoke(EqualsMethod.EQUALS).virtual(typeDefinition.asErasure()), ConditionalReturn.onZeroInteger());
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class Appender implements ByteCodeAppender {
        private final StackManipulation baseline;
        private final List<FieldDescription.InDefinedShape> fieldDescriptions;
        private final TypeDescription instrumentedType;
        private final ElementMatcher<? super FieldDescription.InDefinedShape> nonNullable;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Appender appender = (Appender) obj;
            return this.instrumentedType.equals(appender.instrumentedType) && this.baseline.equals(appender.baseline) && this.fieldDescriptions.equals(appender.fieldDescriptions) && this.nonNullable.equals(appender.nonNullable);
        }

        public int hashCode() {
            return ((((((527 + this.instrumentedType.hashCode()) * 31) + this.baseline.hashCode()) * 31) + this.fieldDescriptions.hashCode()) * 31) + this.nonNullable.hashCode();
        }

        protected Appender(TypeDescription typeDescription, StackManipulation stackManipulation, List<FieldDescription.InDefinedShape> list, ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
            this.instrumentedType = typeDescription;
            this.baseline = stackManipulation;
            this.fieldDescriptions = list;
            this.nonNullable = elementMatcher;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            if (methodDescription.isStatic()) {
                throw new IllegalStateException("Hash code method must not be static: " + methodDescription);
            } else if (methodDescription.getParameters().size() != 1 || ((ParameterDescription) methodDescription.getParameters().getOnly()).getType().isPrimitive()) {
                throw new IllegalStateException();
            } else if (methodDescription.getReturnType().represents(Boolean.TYPE)) {
                ArrayList arrayList = new ArrayList((this.fieldDescriptions.size() * 8) + 3);
                arrayList.add(this.baseline);
                int i = 0;
                for (FieldDescription.InDefinedShape next : this.fieldDescriptions) {
                    arrayList.add(MethodVariableAccess.loadThis());
                    arrayList.add(FieldAccess.forField(next).read());
                    arrayList.add(MethodVariableAccess.REFERENCE.loadFrom(1));
                    arrayList.add(TypeCasting.to(this.instrumentedType));
                    arrayList.add(FieldAccess.forField(next).read());
                    NullValueGuard usingJump = (next.getType().isPrimitive() || next.getType().isArray() || this.nonNullable.matches(next)) ? NullValueGuard.NoOp.INSTANCE : new NullValueGuard.UsingJump(methodDescription);
                    arrayList.add(usingJump.before());
                    arrayList.add(ValueComparator.of(next.getType()));
                    arrayList.add(usingJump.after());
                    i = Math.max(i, usingJump.getRequiredVariablePadding());
                }
                arrayList.add(IntegerConstant.forValue(true));
                arrayList.add(MethodReturn.INTEGER);
                return new ByteCodeAppender.Size(new StackManipulation.Compound((List<? extends StackManipulation>) arrayList).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize() + i);
            } else {
                throw new IllegalStateException("Hash code method does not return primitive boolean: " + methodDescription);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class ConditionalReturn implements StackManipulation {
        private static final Object[] EMPTY = new Object[0];
        private final int jumpCondition;
        private final int value;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ConditionalReturn conditionalReturn = (ConditionalReturn) obj;
            return this.jumpCondition == conditionalReturn.jumpCondition && this.value == conditionalReturn.value;
        }

        public int hashCode() {
            return ((527 + this.jumpCondition) * 31) + this.value;
        }

        public boolean isValid() {
            return true;
        }

        protected ConditionalReturn(int i) {
            this(i, 3);
        }

        private ConditionalReturn(int i, int i2) {
            this.jumpCondition = i;
            this.value = i2;
        }

        protected static ConditionalReturn onZeroInteger() {
            return new ConditionalReturn(154);
        }

        protected static ConditionalReturn onNonZeroInteger() {
            return new ConditionalReturn(153);
        }

        protected static ConditionalReturn onNullValue() {
            return new ConditionalReturn(199);
        }

        protected static ConditionalReturn onNonIdentity() {
            return new ConditionalReturn(165);
        }

        protected static ConditionalReturn onIdentity() {
            return new ConditionalReturn(166);
        }

        protected static ConditionalReturn onNonEqualInteger() {
            return new ConditionalReturn(159);
        }

        /* access modifiers changed from: protected */
        public StackManipulation returningTrue() {
            return new ConditionalReturn(this.jumpCondition, 4);
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            Label label = new Label();
            methodVisitor.visitJumpInsn(this.jumpCondition, label);
            methodVisitor.visitInsn(this.value);
            methodVisitor.visitInsn(172);
            methodVisitor.visitLabel(label);
            if (context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V6)) {
                Object[] objArr = EMPTY;
                methodVisitor.visitFrame(3, objArr.length, objArr, objArr.length, objArr);
            }
            return new StackManipulation.Size(-1, 1);
        }
    }

    protected enum TypePropertyComparator implements Comparator<FieldDescription.InDefinedShape> {
        FOR_PRIMITIVE_TYPES {
            public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                return super.compare((FieldDescription.InDefinedShape) obj, (FieldDescription.InDefinedShape) obj2);
            }

            /* access modifiers changed from: protected */
            public boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.isPrimitive();
            }
        },
        FOR_ENUMERATION_TYPES {
            public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                return super.compare((FieldDescription.InDefinedShape) obj, (FieldDescription.InDefinedShape) obj2);
            }

            /* access modifiers changed from: protected */
            public boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.isEnum();
            }
        },
        FOR_STRING_TYPES {
            public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                return super.compare((FieldDescription.InDefinedShape) obj, (FieldDescription.InDefinedShape) obj2);
            }

            /* access modifiers changed from: protected */
            public boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.represents(String.class);
            }
        },
        FOR_PRIMITIVE_WRAPPER_TYPES {
            public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                return super.compare((FieldDescription.InDefinedShape) obj, (FieldDescription.InDefinedShape) obj2);
            }

            /* access modifiers changed from: protected */
            public boolean resolve(TypeDefinition typeDefinition) {
                return typeDefinition.asErasure().isPrimitiveWrapper();
            }
        };

        /* access modifiers changed from: protected */
        public abstract boolean resolve(TypeDefinition typeDefinition);

        public int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            if (!resolve(inDefinedShape.getType()) || resolve(inDefinedShape2.getType())) {
                return (resolve(inDefinedShape.getType()) || !resolve(inDefinedShape2.getType())) ? 0 : 1;
            }
            return -1;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class CompoundComparator implements Comparator<FieldDescription.InDefinedShape> {
        private final List<Comparator<? super FieldDescription.InDefinedShape>> comparators;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.comparators.equals(((CompoundComparator) obj).comparators);
        }

        public int hashCode() {
            return 527 + this.comparators.hashCode();
        }

        protected CompoundComparator(Comparator<? super FieldDescription.InDefinedShape>... comparatorArr) {
            this((List<? extends Comparator<? super FieldDescription.InDefinedShape>>) Arrays.asList(comparatorArr));
        }

        protected CompoundComparator(List<? extends Comparator<? super FieldDescription.InDefinedShape>> list) {
            this.comparators = new ArrayList();
            for (Comparator comparator : list) {
                if (comparator instanceof CompoundComparator) {
                    this.comparators.addAll(((CompoundComparator) comparator).comparators);
                } else if (!(comparator instanceof NaturalOrderComparator)) {
                    this.comparators.add(comparator);
                }
            }
        }

        public int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            for (Comparator<? super FieldDescription.InDefinedShape> compare : this.comparators) {
                int compare2 = compare.compare(inDefinedShape, inDefinedShape2);
                if (compare2 != 0) {
                    return compare2;
                }
            }
            return 0;
        }
    }
}

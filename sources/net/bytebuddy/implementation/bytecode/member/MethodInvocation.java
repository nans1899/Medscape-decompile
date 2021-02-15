package net.bytebuddy.implementation.bytecode.member;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.Collection;
import java.util.List;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.MethodVisitor;

public enum MethodInvocation {
    VIRTUAL(182, 5, 182, 5),
    INTERFACE(185, 9, 185, 9),
    STATIC(184, 6, 184, 6),
    SPECIAL(183, 7, 183, 7),
    SPECIAL_CONSTRUCTOR(183, 8, 183, 8),
    VIRTUAL_PRIVATE(182, 5, 183, 7),
    INTERFACE_PRIVATE(185, 9, 183, 7);
    
    /* access modifiers changed from: private */
    public final int handle;
    /* access modifiers changed from: private */
    public final int legacyHandle;
    /* access modifiers changed from: private */
    public final int legacyOpcode;
    /* access modifiers changed from: private */
    public final int opcode;

    public interface WithImplicitInvocationTargetType extends StackManipulation {
        StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<?> list2);

        StackManipulation onHandle(HandleType handleType);

        StackManipulation special(TypeDescription typeDescription);

        StackManipulation virtual(TypeDescription typeDescription);
    }

    private MethodInvocation(int i, int i2, int i3, int i4) {
        this.opcode = i;
        this.handle = i2;
        this.legacyOpcode = i3;
        this.legacyHandle = i4;
    }

    public static WithImplicitInvocationTargetType invoke(MethodDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isTypeInitializer()) {
            return IllegalInvocation.INSTANCE;
        }
        if (inDefinedShape.isStatic()) {
            MethodInvocation methodInvocation = STATIC;
            methodInvocation.getClass();
            return new Invocation(methodInvocation, inDefinedShape);
        } else if (inDefinedShape.isConstructor()) {
            MethodInvocation methodInvocation2 = SPECIAL_CONSTRUCTOR;
            methodInvocation2.getClass();
            return new Invocation(methodInvocation2, inDefinedShape);
        } else if (inDefinedShape.isPrivate()) {
            MethodInvocation methodInvocation3 = inDefinedShape.getDeclaringType().isInterface() ? INTERFACE_PRIVATE : VIRTUAL_PRIVATE;
            methodInvocation3.getClass();
            return new Invocation(methodInvocation3, inDefinedShape);
        } else if (inDefinedShape.getDeclaringType().isInterface()) {
            MethodInvocation methodInvocation4 = INTERFACE;
            methodInvocation4.getClass();
            return new Invocation(methodInvocation4, inDefinedShape);
        } else {
            MethodInvocation methodInvocation5 = VIRTUAL;
            methodInvocation5.getClass();
            return new Invocation(methodInvocation5, inDefinedShape);
        }
    }

    public static WithImplicitInvocationTargetType invoke(MethodDescription methodDescription) {
        MethodDescription.InDefinedShape inDefinedShape = (MethodDescription.InDefinedShape) methodDescription.asDefined();
        if (inDefinedShape.getReturnType().asErasure().equals(methodDescription.getReturnType().asErasure())) {
            return invoke(inDefinedShape);
        }
        return OfGenericMethod.of(methodDescription, invoke(inDefinedShape));
    }

    protected enum IllegalInvocation implements WithImplicitInvocationTargetType {
        INSTANCE;

        public boolean isValid() {
            return false;
        }

        public StackManipulation virtual(TypeDescription typeDescription) {
            return StackManipulation.Illegal.INSTANCE;
        }

        public StackManipulation special(TypeDescription typeDescription) {
            return StackManipulation.Illegal.INSTANCE;
        }

        public StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<?> list2) {
            return StackManipulation.Illegal.INSTANCE;
        }

        public StackManipulation onHandle(HandleType handleType) {
            return StackManipulation.Illegal.INSTANCE;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackManipulation.Illegal.INSTANCE.apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class OfGenericMethod implements WithImplicitInvocationTargetType {
        private final WithImplicitInvocationTargetType invocation;
        private final TypeDescription targetType;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            OfGenericMethod ofGenericMethod = (OfGenericMethod) obj;
            return this.targetType.equals(ofGenericMethod.targetType) && this.invocation.equals(ofGenericMethod.invocation);
        }

        public int hashCode() {
            return ((527 + this.targetType.hashCode()) * 31) + this.invocation.hashCode();
        }

        protected OfGenericMethod(TypeDescription typeDescription, WithImplicitInvocationTargetType withImplicitInvocationTargetType) {
            this.targetType = typeDescription;
            this.invocation = withImplicitInvocationTargetType;
        }

        protected static WithImplicitInvocationTargetType of(MethodDescription methodDescription, WithImplicitInvocationTargetType withImplicitInvocationTargetType) {
            return new OfGenericMethod(methodDescription.getReturnType().asErasure(), withImplicitInvocationTargetType);
        }

        public StackManipulation virtual(TypeDescription typeDescription) {
            return new StackManipulation.Compound(this.invocation.virtual(typeDescription), TypeCasting.to(this.targetType));
        }

        public StackManipulation special(TypeDescription typeDescription) {
            return new StackManipulation.Compound(this.invocation.special(typeDescription), TypeCasting.to(this.targetType));
        }

        public StackManipulation dynamic(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, List<?> list2) {
            return this.invocation.dynamic(str, typeDescription, list, list2);
        }

        public StackManipulation onHandle(HandleType handleType) {
            return new StackManipulation.Compound(this.invocation.onHandle(handleType), TypeCasting.to(this.targetType));
        }

        public boolean isValid() {
            return this.invocation.isValid();
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return new StackManipulation.Compound(this.invocation, TypeCasting.to(this.targetType)).apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class Invocation implements WithImplicitInvocationTargetType {
        private final MethodDescription.InDefinedShape methodDescription;
        private final TypeDescription typeDescription;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Invocation invocation = (Invocation) obj;
            return MethodInvocation.this.equals(MethodInvocation.this) && this.typeDescription.equals(invocation.typeDescription) && this.methodDescription.equals(invocation.methodDescription);
        }

        public int hashCode() {
            return ((((527 + this.typeDescription.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + MethodInvocation.this.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        protected Invocation(MethodInvocation methodInvocation, MethodDescription.InDefinedShape inDefinedShape) {
            this(inDefinedShape, inDefinedShape.getDeclaringType());
        }

        protected Invocation(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription2) {
            this.typeDescription = typeDescription2;
            this.methodDescription = inDefinedShape;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            int i;
            if (MethodInvocation.this.opcode == MethodInvocation.this.legacyOpcode || context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V11)) {
                i = MethodInvocation.this.opcode;
            } else {
                i = MethodInvocation.this.legacyOpcode;
            }
            methodVisitor.visitMethodInsn(i, this.typeDescription.getInternalName(), this.methodDescription.getInternalName(), this.methodDescription.getDescriptor(), this.typeDescription.isInterface());
            int size = this.methodDescription.getReturnType().getStackSize().getSize() - this.methodDescription.getStackSize();
            return new StackManipulation.Size(size, Math.max(0, size));
        }

        public StackManipulation virtual(TypeDescription typeDescription2) {
            if (this.methodDescription.isConstructor() || this.methodDescription.isStatic()) {
                return StackManipulation.Illegal.INSTANCE;
            }
            if (this.methodDescription.isPrivate()) {
                return this.methodDescription.getDeclaringType().equals(typeDescription2) ? this : StackManipulation.Illegal.INSTANCE;
            }
            if (!typeDescription2.isInterface()) {
                MethodInvocation methodInvocation = MethodInvocation.VIRTUAL;
                methodInvocation.getClass();
                return new Invocation(this.methodDescription, typeDescription2);
            } else if (this.methodDescription.getDeclaringType().represents(Object.class)) {
                return this;
            } else {
                MethodInvocation methodInvocation2 = MethodInvocation.INTERFACE;
                methodInvocation2.getClass();
                return new Invocation(this.methodDescription, typeDescription2);
            }
        }

        public StackManipulation special(TypeDescription typeDescription2) {
            if (!this.methodDescription.isSpecializableFor(typeDescription2)) {
                return StackManipulation.Illegal.INSTANCE;
            }
            MethodInvocation methodInvocation = MethodInvocation.SPECIAL;
            methodInvocation.getClass();
            return new Invocation(this.methodDescription, typeDescription2);
        }

        public StackManipulation dynamic(String str, TypeDescription typeDescription2, List<? extends TypeDescription> list, List<?> list2) {
            if (!this.methodDescription.isInvokeBootstrap()) {
                return StackManipulation.Illegal.INSTANCE;
            }
            return new DynamicInvocation(str, typeDescription2, new TypeList.Explicit(list), (MethodDescription.InDefinedShape) this.methodDescription.asDefined(), list2);
        }

        public StackManipulation onHandle(HandleType handleType) {
            return new HandleInvocation(this.methodDescription, handleType);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class DynamicInvocation implements StackManipulation {
        private final List<?> arguments;
        private final MethodDescription.InDefinedShape bootstrapMethod;
        private final String methodName;
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            DynamicInvocation dynamicInvocation = (DynamicInvocation) obj;
            return MethodInvocation.this.equals(MethodInvocation.this) && this.methodName.equals(dynamicInvocation.methodName) && this.returnType.equals(dynamicInvocation.returnType) && this.parameterTypes.equals(dynamicInvocation.parameterTypes) && this.bootstrapMethod.equals(dynamicInvocation.bootstrapMethod) && this.arguments.equals(dynamicInvocation.arguments);
        }

        public int hashCode() {
            return ((((((((((527 + this.methodName.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode()) * 31) + this.bootstrapMethod.hashCode()) * 31) + this.arguments.hashCode()) * 31) + MethodInvocation.this.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        public DynamicInvocation(String str, TypeDescription typeDescription, List<? extends TypeDescription> list, MethodDescription.InDefinedShape inDefinedShape, List<?> list2) {
            this.methodName = str;
            this.returnType = typeDescription;
            this.parameterTypes = list;
            this.bootstrapMethod = inDefinedShape;
            this.arguments = list2;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            int i;
            StringBuilder sb = new StringBuilder("(");
            for (TypeDescription descriptor : this.parameterTypes) {
                sb.append(descriptor.getDescriptor());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            sb.append(this.returnType.getDescriptor());
            String sb2 = sb.toString();
            String str = this.methodName;
            if (MethodInvocation.this.handle == MethodInvocation.this.legacyHandle || context.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V11)) {
                i = MethodInvocation.this.handle;
            } else {
                i = MethodInvocation.this.legacyHandle;
            }
            Handle handle = new Handle(i, this.bootstrapMethod.getDeclaringType().getInternalName(), this.bootstrapMethod.getInternalName(), this.bootstrapMethod.getDescriptor(), this.bootstrapMethod.getDeclaringType().isInterface());
            List<?> list = this.arguments;
            methodVisitor.visitInvokeDynamicInsn(str, sb2, handle, list.toArray(new Object[list.size()]));
            int size = this.returnType.getStackSize().getSize() - StackSize.of((Collection<? extends TypeDefinition>) this.parameterTypes);
            return new StackManipulation.Size(size, Math.max(size, 0));
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class HandleInvocation implements StackManipulation {
        private static final String METHOD_HANDLE = "java/lang/invoke/MethodHandle";
        private final MethodDescription.InDefinedShape methodDescription;
        private final HandleType type;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HandleInvocation handleInvocation = (HandleInvocation) obj;
            return this.type.equals(handleInvocation.type) && this.methodDescription.equals(handleInvocation.methodDescription);
        }

        public int hashCode() {
            return ((527 + this.methodDescription.hashCode()) * 31) + this.type.hashCode();
        }

        public boolean isValid() {
            return true;
        }

        protected HandleInvocation(MethodDescription.InDefinedShape inDefinedShape, HandleType handleType) {
            this.methodDescription = inDefinedShape;
            this.type = handleType;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            String str;
            String methodName = this.type.getMethodName();
            if (this.methodDescription.isStatic() || this.methodDescription.isConstructor()) {
                str = this.methodDescription.getDescriptor();
            } else {
                str = "(" + this.methodDescription.getDeclaringType().getDescriptor() + this.methodDescription.getDescriptor().substring(1);
            }
            methodVisitor.visitMethodInsn(182, METHOD_HANDLE, methodName, str, false);
            int size = this.methodDescription.getReturnType().getStackSize().getSize() - (this.methodDescription.getStackSize() + 1);
            return new StackManipulation.Size(size, Math.max(0, size));
        }
    }

    public enum HandleType {
        EXACT("invokeExact"),
        REGULAR("invoke");
        
        private final String methodName;

        private HandleType(String str) {
            this.methodName = str;
        }

        /* access modifiers changed from: protected */
        public String getMethodName() {
            return this.methodName;
        }
    }
}

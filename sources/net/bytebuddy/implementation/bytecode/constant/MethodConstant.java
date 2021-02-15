package net.bytebuddy.implementation.bytecode.constant;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.PrivilegedMemberLookupAction;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;

public abstract class MethodConstant implements StackManipulation {
    protected final MethodDescription.InDefinedShape methodDescription;

    public interface CanCache extends StackManipulation {
        StackManipulation cached();
    }

    /* access modifiers changed from: protected */
    public abstract MethodDescription.InDefinedShape accessorMethod();

    public boolean isValid() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract StackManipulation methodName();

    protected MethodConstant(MethodDescription.InDefinedShape inDefinedShape) {
        this.methodDescription = inDefinedShape;
    }

    public static CanCache of(MethodDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isTypeInitializer()) {
            return CanCacheIllegal.INSTANCE;
        }
        if (inDefinedShape.isConstructor()) {
            return new ForConstructor(inDefinedShape);
        }
        return new ForMethod(inDefinedShape);
    }

    public static CanCache ofPrivileged(MethodDescription.InDefinedShape inDefinedShape) {
        if (inDefinedShape.isTypeInitializer()) {
            return CanCacheIllegal.INSTANCE;
        }
        if (inDefinedShape.isConstructor()) {
            return new ForConstructor(inDefinedShape).privileged();
        }
        return new ForMethod(inDefinedShape).privileged();
    }

    protected static List<StackManipulation> typeConstantsFor(List<TypeDescription> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (TypeDescription of : list) {
            arrayList.add(ClassConstant.of(of));
        }
        return arrayList;
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        return new StackManipulation.Compound(ClassConstant.of(this.methodDescription.getDeclaringType()), methodName(), ArrayFactory.forType(TypeDescription.Generic.OfNonGenericType.CLASS).withValues(typeConstantsFor(this.methodDescription.getParameters().asTypeList().asErasures())), MethodInvocation.invoke(accessorMethod())).apply(methodVisitor, context);
    }

    /* access modifiers changed from: protected */
    public CanCache privileged() {
        return new PrivilegedLookup(this.methodDescription, methodName());
    }

    public int hashCode() {
        return this.methodDescription.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.methodDescription.equals(((MethodConstant) obj).methodDescription);
    }

    protected enum CanCacheIllegal implements CanCache {
        INSTANCE;

        public boolean isValid() {
            return false;
        }

        public StackManipulation cached() {
            return StackManipulation.Illegal.INSTANCE;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return StackManipulation.Illegal.INSTANCE.apply(methodVisitor, context);
        }
    }

    protected static class ForMethod extends MethodConstant implements CanCache {
        private static final MethodDescription.InDefinedShape GET_DECLARED_METHOD;
        private static final MethodDescription.InDefinedShape GET_METHOD;

        static {
            try {
                GET_METHOD = new MethodDescription.ForLoadedMethod(Class.class.getMethod("getMethod", new Class[]{String.class, Class[].class}));
                GET_DECLARED_METHOD = new MethodDescription.ForLoadedMethod(Class.class.getMethod("getDeclaredMethod", new Class[]{String.class, Class[].class}));
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Could not locate method lookup", e);
            }
        }

        protected ForMethod(MethodDescription.InDefinedShape inDefinedShape) {
            super(inDefinedShape);
        }

        /* access modifiers changed from: protected */
        public StackManipulation methodName() {
            return new TextConstant(this.methodDescription.getInternalName());
        }

        /* access modifiers changed from: protected */
        public MethodDescription.InDefinedShape accessorMethod() {
            return this.methodDescription.isPublic() ? GET_METHOD : GET_DECLARED_METHOD;
        }

        public StackManipulation cached() {
            return new CachedMethod(this);
        }
    }

    protected static class ForConstructor extends MethodConstant implements CanCache {
        private static final MethodDescription.InDefinedShape GET_CONSTRUCTOR;
        private static final MethodDescription.InDefinedShape GET_DECLARED_CONSTRUCTOR;

        static {
            try {
                GET_CONSTRUCTOR = new MethodDescription.ForLoadedMethod(Class.class.getMethod("getConstructor", new Class[]{Class[].class}));
                GET_DECLARED_CONSTRUCTOR = new MethodDescription.ForLoadedMethod(Class.class.getMethod(TypeProxy.SilentConstruction.Appender.GET_DECLARED_CONSTRUCTOR_METHOD_NAME, new Class[]{Class[].class}));
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Could not locate Class::getDeclaredConstructor", e);
            }
        }

        protected ForConstructor(MethodDescription.InDefinedShape inDefinedShape) {
            super(inDefinedShape);
        }

        /* access modifiers changed from: protected */
        public StackManipulation methodName() {
            return StackManipulation.Trivial.INSTANCE;
        }

        /* access modifiers changed from: protected */
        public MethodDescription.InDefinedShape accessorMethod() {
            return this.methodDescription.isPublic() ? GET_CONSTRUCTOR : GET_DECLARED_CONSTRUCTOR;
        }

        public StackManipulation cached() {
            return new CachedConstructor(this);
        }
    }

    protected static class PrivilegedLookup implements StackManipulation, CanCache {
        private static final MethodDescription.InDefinedShape DO_PRIVILEGED;
        private final MethodDescription.InDefinedShape methodDescription;
        private final StackManipulation methodName;

        static {
            try {
                DO_PRIVILEGED = new MethodDescription.ForLoadedMethod(AccessController.class.getMethod("doPrivileged", new Class[]{PrivilegedExceptionAction.class}));
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException("Cannot locate AccessController::doPrivileged", e);
            }
        }

        protected PrivilegedLookup(MethodDescription.InDefinedShape inDefinedShape, StackManipulation stackManipulation) {
            this.methodDescription = inDefinedShape;
            this.methodName = stackManipulation;
        }

        public boolean isValid() {
            return this.methodName.isValid();
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            TypeDescription register = context.register(PrivilegedMemberLookupAction.of(this.methodDescription));
            StackManipulation[] stackManipulationArr = new StackManipulation[8];
            stackManipulationArr[0] = TypeCreation.of(register);
            stackManipulationArr[1] = Duplication.SINGLE;
            stackManipulationArr[2] = ClassConstant.of(this.methodDescription.getDeclaringType());
            stackManipulationArr[3] = this.methodName;
            stackManipulationArr[4] = ArrayFactory.forType(TypeDescription.Generic.OfNonGenericType.CLASS).withValues(MethodConstant.typeConstantsFor(this.methodDescription.getParameters().asTypeList().asErasures()));
            stackManipulationArr[5] = MethodInvocation.invoke((MethodDescription.InDefinedShape) ((MethodList) register.getDeclaredMethods().filter(ElementMatchers.isConstructor())).getOnly());
            stackManipulationArr[6] = MethodInvocation.invoke(DO_PRIVILEGED);
            stackManipulationArr[7] = TypeCasting.to(TypeDescription.ForLoadedType.of(this.methodDescription.isConstructor() ? Constructor.class : Method.class));
            return new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context);
        }

        public StackManipulation cached() {
            return this.methodDescription.isConstructor() ? new CachedConstructor(this) : new CachedMethod(this);
        }

        public int hashCode() {
            return this.methodDescription.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.methodDescription.equals(((PrivilegedLookup) obj).methodDescription);
        }
    }

    protected static class CachedMethod implements StackManipulation {
        private static final TypeDescription METHOD_TYPE = TypeDescription.ForLoadedType.of(Method.class);
        private final StackManipulation methodConstant;

        protected CachedMethod(StackManipulation stackManipulation) {
            this.methodConstant = stackManipulation;
        }

        public boolean isValid() {
            return this.methodConstant.isValid();
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return FieldAccess.forField(context.cache(this.methodConstant, METHOD_TYPE)).read().apply(methodVisitor, context);
        }

        public int hashCode() {
            return this.methodConstant.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.methodConstant.equals(((CachedMethod) obj).methodConstant);
        }
    }

    protected static class CachedConstructor implements StackManipulation {
        private static final TypeDescription CONSTRUCTOR_TYPE = TypeDescription.ForLoadedType.of(Constructor.class);
        private final StackManipulation constructorConstant;

        protected CachedConstructor(StackManipulation stackManipulation) {
            this.constructorConstant = stackManipulation;
        }

        public boolean isValid() {
            return this.constructorConstant.isValid();
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return FieldAccess.forField(context.cache(this.constructorConstant, CONSTRUCTOR_TYPE)).read().apply(methodVisitor, context);
        }

        public int hashCode() {
            return this.constructorConstant.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.constructorConstant.equals(((CachedConstructor) obj).constructorConstant);
        }
    }
}

package net.bytebuddy.dynamic.scaffold.inline;

import java.util.Map;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
public class RebaseImplementationTarget extends Implementation.Target.AbstractBase {
    private final Map<MethodDescription.SignatureToken, MethodRebaseResolver.Resolution> rebaseableMethods;

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.rebaseableMethods.equals(((RebaseImplementationTarget) obj).rebaseableMethods);
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.rebaseableMethods.hashCode();
    }

    protected RebaseImplementationTarget(TypeDescription typeDescription, MethodGraph.Linked linked, Implementation.Target.AbstractBase.DefaultMethodInvocation defaultMethodInvocation, Map<MethodDescription.SignatureToken, MethodRebaseResolver.Resolution> map) {
        super(typeDescription, linked, defaultMethodInvocation);
        this.rebaseableMethods = map;
    }

    protected static Implementation.Target of(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion, MethodRebaseResolver methodRebaseResolver) {
        return new RebaseImplementationTarget(typeDescription, linked, Implementation.Target.AbstractBase.DefaultMethodInvocation.of(classFileVersion), methodRebaseResolver.asTokenMap());
    }

    public Implementation.SpecialMethodInvocation invokeSuper(MethodDescription.SignatureToken signatureToken) {
        MethodRebaseResolver.Resolution resolution = this.rebaseableMethods.get(signatureToken);
        if (resolution == null) {
            return invokeSuper(this.methodGraph.getSuperClassGraph().locate(signatureToken));
        }
        return invokeSuper(resolution);
    }

    private Implementation.SpecialMethodInvocation invokeSuper(MethodGraph.Node node) {
        return node.getSort().isResolved() ? Implementation.SpecialMethodInvocation.Simple.of(node.getRepresentative(), this.instrumentedType.getSuperClass().asErasure()) : Implementation.SpecialMethodInvocation.Illegal.INSTANCE;
    }

    private Implementation.SpecialMethodInvocation invokeSuper(MethodRebaseResolver.Resolution resolution) {
        if (resolution.isRebased()) {
            return RebasedMethodInvocation.of(resolution.getResolvedMethod(), this.instrumentedType, resolution.getAdditionalArguments());
        }
        return Implementation.SpecialMethodInvocation.Simple.of(resolution.getResolvedMethod(), this.instrumentedType);
    }

    public TypeDescription getOriginType() {
        return this.instrumentedType;
    }

    protected static class RebasedMethodInvocation extends Implementation.SpecialMethodInvocation.AbstractBase {
        private final TypeDescription instrumentedType;
        private final MethodDescription methodDescription;
        private final StackManipulation stackManipulation;

        protected RebasedMethodInvocation(MethodDescription methodDescription2, TypeDescription typeDescription, StackManipulation stackManipulation2) {
            this.methodDescription = methodDescription2;
            this.instrumentedType = typeDescription;
            this.stackManipulation = stackManipulation2;
        }

        protected static Implementation.SpecialMethodInvocation of(MethodDescription methodDescription2, TypeDescription typeDescription, StackManipulation stackManipulation2) {
            StackManipulation stackManipulation3;
            if (methodDescription2.isStatic()) {
                stackManipulation3 = MethodInvocation.invoke(methodDescription2);
            } else {
                stackManipulation3 = MethodInvocation.invoke(methodDescription2).special(typeDescription);
            }
            if (!stackManipulation3.isValid()) {
                return Implementation.SpecialMethodInvocation.Illegal.INSTANCE;
            }
            return new RebasedMethodInvocation(methodDescription2, typeDescription, new StackManipulation.Compound(stackManipulation2, stackManipulation3));
        }

        public MethodDescription getMethodDescription() {
            return this.methodDescription;
        }

        public TypeDescription getTypeDescription() {
            return this.instrumentedType;
        }

        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            return this.stackManipulation.apply(methodVisitor, context);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Factory implements Implementation.Target.Factory {
        private final MethodRebaseResolver methodRebaseResolver;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.methodRebaseResolver.equals(((Factory) obj).methodRebaseResolver);
        }

        public int hashCode() {
            return 527 + this.methodRebaseResolver.hashCode();
        }

        public Factory(MethodRebaseResolver methodRebaseResolver2) {
            this.methodRebaseResolver = methodRebaseResolver2;
        }

        public Implementation.Target make(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion) {
            return RebaseImplementationTarget.of(typeDescription, linked, classFileVersion, this.methodRebaseResolver);
        }
    }
}

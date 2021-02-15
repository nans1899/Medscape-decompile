package net.bytebuddy.dynamic.scaffold.subclass;

import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.matcher.ElementMatchers;

@HashCodeAndEqualsPlugin.Enhance
public class SubclassImplementationTarget extends Implementation.Target.AbstractBase {
    protected final OriginTypeResolver originTypeResolver;

    public enum OriginTypeResolver {
        SUPER_CLASS {
            /* access modifiers changed from: protected */
            public TypeDefinition identify(TypeDescription typeDescription) {
                return typeDescription.getSuperClass();
            }
        },
        LEVEL_TYPE {
            /* access modifiers changed from: protected */
            public TypeDefinition identify(TypeDescription typeDescription) {
                return typeDescription;
            }
        };

        /* access modifiers changed from: protected */
        public abstract TypeDefinition identify(TypeDescription typeDescription);
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.originTypeResolver.equals(((SubclassImplementationTarget) obj).originTypeResolver);
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.originTypeResolver.hashCode();
    }

    protected SubclassImplementationTarget(TypeDescription typeDescription, MethodGraph.Linked linked, Implementation.Target.AbstractBase.DefaultMethodInvocation defaultMethodInvocation, OriginTypeResolver originTypeResolver2) {
        super(typeDescription, linked, defaultMethodInvocation);
        this.originTypeResolver = originTypeResolver2;
    }

    public Implementation.SpecialMethodInvocation invokeSuper(MethodDescription.SignatureToken signatureToken) {
        if (signatureToken.getName().equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME)) {
            return invokeConstructor(signatureToken);
        }
        return invokeMethod(signatureToken);
    }

    private Implementation.SpecialMethodInvocation invokeConstructor(MethodDescription.SignatureToken signatureToken) {
        MethodList methodList;
        TypeDescription.Generic superClass = this.instrumentedType.getSuperClass();
        if (superClass == null) {
            methodList = new MethodList.Empty();
        } else {
            methodList = (MethodList) superClass.getDeclaredMethods().filter(ElementMatchers.hasSignature(signatureToken).and(ElementMatchers.isVisibleTo(this.instrumentedType)));
        }
        return methodList.size() == 1 ? Implementation.SpecialMethodInvocation.Simple.of((MethodDescription) methodList.getOnly(), this.instrumentedType.getSuperClass().asErasure()) : Implementation.SpecialMethodInvocation.Illegal.INSTANCE;
    }

    private Implementation.SpecialMethodInvocation invokeMethod(MethodDescription.SignatureToken signatureToken) {
        MethodGraph.Node locate = this.methodGraph.getSuperClassGraph().locate(signatureToken);
        return locate.getSort().isUnique() ? Implementation.SpecialMethodInvocation.Simple.of(locate.getRepresentative(), this.instrumentedType.getSuperClass().asErasure()) : Implementation.SpecialMethodInvocation.Illegal.INSTANCE;
    }

    public TypeDefinition getOriginType() {
        return this.originTypeResolver.identify(this.instrumentedType);
    }

    public enum Factory implements Implementation.Target.Factory {
        SUPER_CLASS(OriginTypeResolver.SUPER_CLASS),
        LEVEL_TYPE(OriginTypeResolver.LEVEL_TYPE);
        
        private final OriginTypeResolver originTypeResolver;

        private Factory(OriginTypeResolver originTypeResolver2) {
            this.originTypeResolver = originTypeResolver2;
        }

        public Implementation.Target make(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion) {
            return new SubclassImplementationTarget(typeDescription, linked, Implementation.Target.AbstractBase.DefaultMethodInvocation.of(classFileVersion), this.originTypeResolver);
        }
    }
}

package net.bytebuddy.dynamic.scaffold.inline;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class InliningImplementationMatcher implements LatentMatcher<MethodDescription> {
    private final LatentMatcher<? super MethodDescription> ignoredMethods;
    private final ElementMatcher<? super MethodDescription> predefinedMethodSignatures;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InliningImplementationMatcher inliningImplementationMatcher = (InliningImplementationMatcher) obj;
        return this.ignoredMethods.equals(inliningImplementationMatcher.ignoredMethods) && this.predefinedMethodSignatures.equals(inliningImplementationMatcher.predefinedMethodSignatures);
    }

    public int hashCode() {
        return ((527 + this.ignoredMethods.hashCode()) * 31) + this.predefinedMethodSignatures.hashCode();
    }

    protected InliningImplementationMatcher(LatentMatcher<? super MethodDescription> latentMatcher, ElementMatcher<? super MethodDescription> elementMatcher) {
        this.ignoredMethods = latentMatcher;
        this.predefinedMethodSignatures = elementMatcher;
    }

    protected static LatentMatcher<MethodDescription> of(LatentMatcher<? super MethodDescription> latentMatcher, TypeDescription typeDescription) {
        ElementMatcher.Junction junction;
        ElementMatcher.Junction none = ElementMatchers.none();
        for (MethodDescription methodDescription : typeDescription.getDeclaredMethods()) {
            if (methodDescription.isConstructor()) {
                junction = ElementMatchers.isConstructor();
            } else {
                junction = ElementMatchers.named(methodDescription.getName());
            }
            none = none.or(junction.and(ElementMatchers.returns(methodDescription.getReturnType().asErasure())).and(ElementMatchers.takesArguments((Iterable<? extends TypeDescription>) methodDescription.getParameters().asTypeList().asErasures())));
        }
        return new InliningImplementationMatcher(latentMatcher, none);
    }

    public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
        return ElementMatchers.not(this.ignoredMethods.resolve(typeDescription)).and(ElementMatchers.isVirtual().and(ElementMatchers.not(ElementMatchers.isFinal())).or(ElementMatchers.isDeclaredBy(typeDescription))).or(ElementMatchers.isDeclaredBy(typeDescription).and(ElementMatchers.not(this.predefinedMethodSignatures)));
    }
}

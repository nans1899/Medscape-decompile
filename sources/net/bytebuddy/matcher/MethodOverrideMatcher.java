package net.bytebuddy.matcher;

import java.util.List;
import java.util.Set;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class MethodOverrideMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription.Generic> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((MethodOverrideMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public MethodOverrideMatcher(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        this.matcher = elementMatcher;
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean matches(T r5) {
        /*
            r4 = this;
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            net.bytebuddy.description.type.TypeDefinition r1 = r5.getDeclaringType()
            java.util.Iterator r1 = r1.iterator()
        L_0x000d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x002b
            java.lang.Object r2 = r1.next()
            net.bytebuddy.description.type.TypeDefinition r2 = (net.bytebuddy.description.type.TypeDefinition) r2
            boolean r3 = r4.matches(r5, r2)
            if (r3 != 0) goto L_0x0029
            net.bytebuddy.description.type.TypeList$Generic r2 = r2.getInterfaces()
            boolean r2 = r4.matches(r5, r2, r0)
            if (r2 == 0) goto L_0x000d
        L_0x0029:
            r5 = 1
            return r5
        L_0x002b:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.matcher.MethodOverrideMatcher.matches(net.bytebuddy.description.method.MethodDescription):boolean");
    }

    private boolean matches(MethodDescription methodDescription, List<? extends TypeDefinition> list, Set<TypeDescription> set) {
        for (TypeDefinition typeDefinition : list) {
            if (set.add(typeDefinition.asErasure()) && (matches(methodDescription, typeDefinition) || matches(methodDescription, typeDefinition.getInterfaces(), set))) {
                return true;
            }
        }
        return false;
    }

    private boolean matches(MethodDescription methodDescription, TypeDefinition typeDefinition) {
        for (MethodDescription asSignatureToken : (MethodList) typeDefinition.getDeclaredMethods().filter(ElementMatchers.isVirtual())) {
            if (asSignatureToken.asSignatureToken().equals(methodDescription.asSignatureToken())) {
                return this.matcher.matches(typeDefinition.asGenericType());
            }
        }
        return false;
    }

    public String toString() {
        return "isOverriddenFrom(" + this.matcher + ")";
    }
}

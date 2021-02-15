package net.bytebuddy.matcher;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class HasSuperTypeMatcher<T extends TypeDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super TypeDescription.Generic> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((HasSuperTypeMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public HasSuperTypeMatcher(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        this.matcher = elementMatcher;
    }

    public boolean matches(T t) {
        HashSet hashSet = new HashSet();
        Iterator it = t.iterator();
        while (it.hasNext()) {
            TypeDefinition typeDefinition = (TypeDefinition) it.next();
            if (!hashSet.add(typeDefinition.asErasure())) {
                return false;
            }
            if (this.matcher.matches(typeDefinition.asGenericType())) {
                return true;
            }
            LinkedList linkedList = new LinkedList(typeDefinition.getInterfaces());
            while (true) {
                if (!linkedList.isEmpty()) {
                    TypeDefinition typeDefinition2 = (TypeDefinition) linkedList.removeFirst();
                    if (hashSet.add(typeDefinition2.asErasure())) {
                        if (this.matcher.matches(typeDefinition2.asGenericType())) {
                            return true;
                        }
                        linkedList.addAll(typeDefinition2.getInterfaces());
                    }
                }
            }
        }
        return false;
    }

    public String toString() {
        return "hasSuperType(" + this.matcher + ")";
    }
}

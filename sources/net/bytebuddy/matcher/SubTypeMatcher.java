package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class SubTypeMatcher<T extends TypeDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final TypeDescription typeDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((SubTypeMatcher) obj).typeDescription);
    }

    public int hashCode() {
        return 527 + this.typeDescription.hashCode();
    }

    public SubTypeMatcher(TypeDescription typeDescription2) {
        this.typeDescription = typeDescription2;
    }

    public boolean matches(T t) {
        return t.isAssignableTo(this.typeDescription);
    }

    public String toString() {
        return "isSubTypeOf(" + this.typeDescription + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}

package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class SuperTypeMatcher<T extends TypeDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final TypeDescription typeDescription;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((SuperTypeMatcher) obj).typeDescription);
    }

    public int hashCode() {
        return 527 + this.typeDescription.hashCode();
    }

    public SuperTypeMatcher(TypeDescription typeDescription2) {
        this.typeDescription = typeDescription2;
    }

    public boolean matches(T t) {
        return t.isAssignableFrom(this.typeDescription);
    }

    public String toString() {
        return "isSuperTypeOf(" + this.typeDescription + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}

package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Iterator;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class CollectionErasureMatcher<T extends Iterable<? extends TypeDefinition>> extends ElementMatcher.Junction.AbstractBase<T> {
    private final ElementMatcher<? super Iterable<? extends TypeDescription>> matcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.matcher.equals(((CollectionErasureMatcher) obj).matcher);
    }

    public int hashCode() {
        return 527 + this.matcher.hashCode();
    }

    public CollectionErasureMatcher(ElementMatcher<? super Iterable<? extends TypeDescription>> elementMatcher) {
        this.matcher = elementMatcher;
    }

    public boolean matches(T t) {
        ArrayList arrayList = new ArrayList();
        Iterator it = t.iterator();
        while (it.hasNext()) {
            arrayList.add(((TypeDefinition) it.next()).asErasure());
        }
        return this.matcher.matches(arrayList);
    }

    public String toString() {
        return "erasures(" + this.matcher + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}

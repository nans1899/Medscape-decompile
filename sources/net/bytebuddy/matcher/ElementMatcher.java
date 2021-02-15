package net.bytebuddy.matcher;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

public interface ElementMatcher<T> {
    boolean matches(T t);

    public interface Junction<S> extends ElementMatcher<S> {
        <U extends S> Junction<U> and(ElementMatcher<? super U> elementMatcher);

        <U extends S> Junction<U> or(ElementMatcher<? super U> elementMatcher);

        public static abstract class AbstractBase<V> implements Junction<V> {
            public <U extends V> Junction<U> and(ElementMatcher<? super U> elementMatcher) {
                return new Conjunction(this, elementMatcher);
            }

            public <U extends V> Junction<U> or(ElementMatcher<? super U> elementMatcher) {
                return new Disjunction(this, elementMatcher);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Conjunction<W> extends AbstractBase<W> {
            private final ElementMatcher<? super W> left;
            private final ElementMatcher<? super W> right;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Conjunction conjunction = (Conjunction) obj;
                return this.left.equals(conjunction.left) && this.right.equals(conjunction.right);
            }

            public int hashCode() {
                return ((527 + this.left.hashCode()) * 31) + this.right.hashCode();
            }

            public Conjunction(ElementMatcher<? super W> elementMatcher, ElementMatcher<? super W> elementMatcher2) {
                this.left = elementMatcher;
                this.right = elementMatcher2;
            }

            public boolean matches(W w) {
                return this.left.matches(w) && this.right.matches(w);
            }

            public String toString() {
                return "(" + this.left + " and " + this.right + ASCIIPropertyListParser.ARRAY_END_TOKEN;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Disjunction<W> extends AbstractBase<W> {
            private final ElementMatcher<? super W> left;
            private final ElementMatcher<? super W> right;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Disjunction disjunction = (Disjunction) obj;
                return this.left.equals(disjunction.left) && this.right.equals(disjunction.right);
            }

            public int hashCode() {
                return ((527 + this.left.hashCode()) * 31) + this.right.hashCode();
            }

            public Disjunction(ElementMatcher<? super W> elementMatcher, ElementMatcher<? super W> elementMatcher2) {
                this.left = elementMatcher;
                this.right = elementMatcher2;
            }

            public boolean matches(W w) {
                return this.left.matches(w) || this.right.matches(w);
            }

            public String toString() {
                return "(" + this.left + " or " + this.right + ASCIIPropertyListParser.ARRAY_END_TOKEN;
            }
        }
    }
}

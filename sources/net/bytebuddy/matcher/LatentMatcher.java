package net.bytebuddy.matcher;

import java.util.Arrays;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

public interface LatentMatcher<T> {
    ElementMatcher<? super T> resolve(TypeDescription typeDescription);

    public enum ForSelfDeclaredMethod implements LatentMatcher<MethodDescription> {
        DECLARED(false),
        NOT_DECLARED(true);
        
        private final boolean inverted;

        private ForSelfDeclaredMethod(boolean z) {
            this.inverted = z;
        }

        public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
            if (this.inverted) {
                return ElementMatchers.not(ElementMatchers.isDeclaredBy(typeDescription));
            }
            return ElementMatchers.isDeclaredBy(typeDescription);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Resolved<S> implements LatentMatcher<S> {
        private final ElementMatcher<? super S> matcher;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Resolved) obj).matcher);
        }

        public int hashCode() {
            return 527 + this.matcher.hashCode();
        }

        public Resolved(ElementMatcher<? super S> elementMatcher) {
            this.matcher = elementMatcher;
        }

        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            return this.matcher;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForFieldToken implements LatentMatcher<FieldDescription> {
        private final FieldDescription.Token token;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.token.equals(((ForFieldToken) obj).token);
        }

        public int hashCode() {
            return 527 + this.token.hashCode();
        }

        public ForFieldToken(FieldDescription.Token token2) {
            this.token = token2;
        }

        public ElementMatcher<? super FieldDescription> resolve(TypeDescription typeDescription) {
            return new ResolvedMatcher(this.token.asSignatureToken(typeDescription));
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class ResolvedMatcher implements ElementMatcher<FieldDescription> {
            private final FieldDescription.SignatureToken signatureToken;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.signatureToken.equals(((ResolvedMatcher) obj).signatureToken);
            }

            public int hashCode() {
                return 527 + this.signatureToken.hashCode();
            }

            protected ResolvedMatcher(FieldDescription.SignatureToken signatureToken2) {
                this.signatureToken = signatureToken2;
            }

            public boolean matches(FieldDescription fieldDescription) {
                return fieldDescription.asSignatureToken().equals(this.signatureToken);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForMethodToken implements LatentMatcher<MethodDescription> {
        private final MethodDescription.Token token;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.token.equals(((ForMethodToken) obj).token);
        }

        public int hashCode() {
            return 527 + this.token.hashCode();
        }

        public ForMethodToken(MethodDescription.Token token2) {
            this.token = token2;
        }

        public ElementMatcher<? super MethodDescription> resolve(TypeDescription typeDescription) {
            return new ResolvedMatcher(this.token.asSignatureToken(typeDescription));
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class ResolvedMatcher implements ElementMatcher<MethodDescription> {
            private final MethodDescription.SignatureToken signatureToken;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.signatureToken.equals(((ResolvedMatcher) obj).signatureToken);
            }

            public int hashCode() {
                return 527 + this.signatureToken.hashCode();
            }

            protected ResolvedMatcher(MethodDescription.SignatureToken signatureToken2) {
                this.signatureToken = signatureToken2;
            }

            public boolean matches(MethodDescription methodDescription) {
                return methodDescription.asSignatureToken().equals(this.signatureToken);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Conjunction<S> implements LatentMatcher<S> {
        private final List<? extends LatentMatcher<? super S>> matchers;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matchers.equals(((Conjunction) obj).matchers);
        }

        public int hashCode() {
            return 527 + this.matchers.hashCode();
        }

        public Conjunction(LatentMatcher<? super S>... latentMatcherArr) {
            this(Arrays.asList(latentMatcherArr));
        }

        public Conjunction(List<? extends LatentMatcher<? super S>> list) {
            this.matchers = list;
        }

        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            ElementMatcher.Junction any = ElementMatchers.any();
            for (LatentMatcher resolve : this.matchers) {
                any = any.and(resolve.resolve(typeDescription));
            }
            return any;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Disjunction<S> implements LatentMatcher<S> {
        private final List<? extends LatentMatcher<? super S>> matchers;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.matchers.equals(((Disjunction) obj).matchers);
        }

        public int hashCode() {
            return 527 + this.matchers.hashCode();
        }

        public Disjunction(LatentMatcher<? super S>... latentMatcherArr) {
            this(Arrays.asList(latentMatcherArr));
        }

        public Disjunction(List<? extends LatentMatcher<? super S>> list) {
            this.matchers = list;
        }

        public ElementMatcher<? super S> resolve(TypeDescription typeDescription) {
            ElementMatcher.Junction none = ElementMatchers.none();
            for (LatentMatcher resolve : this.matchers) {
                none = none.or(resolve.resolve(typeDescription));
            }
            return none;
        }
    }
}

package net.bytebuddy.matcher;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;

@HashCodeAndEqualsPlugin.Enhance
public class MethodSortMatcher<T extends MethodDescription> extends ElementMatcher.Junction.AbstractBase<T> {
    private final Sort sort;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.sort.equals(((MethodSortMatcher) obj).sort);
    }

    public int hashCode() {
        return 527 + this.sort.hashCode();
    }

    public MethodSortMatcher(Sort sort2) {
        this.sort = sort2;
    }

    public boolean matches(T t) {
        return this.sort.isSort(t);
    }

    public String toString() {
        return this.sort.getDescription();
    }

    public enum Sort {
        METHOD("isMethod()") {
            /* access modifiers changed from: protected */
            public boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isMethod();
            }
        },
        CONSTRUCTOR("isConstructor()") {
            /* access modifiers changed from: protected */
            public boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isConstructor();
            }
        },
        TYPE_INITIALIZER("isTypeInitializer()") {
            /* access modifiers changed from: protected */
            public boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isTypeInitializer();
            }
        },
        VIRTUAL("isVirtual()") {
            /* access modifiers changed from: protected */
            public boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isVirtual();
            }
        },
        DEFAULT_METHOD("isDefaultMethod()") {
            /* access modifiers changed from: protected */
            public boolean isSort(MethodDescription methodDescription) {
                return methodDescription.isDefaultMethod();
            }
        };
        
        private final String description;

        /* access modifiers changed from: protected */
        public abstract boolean isSort(MethodDescription methodDescription);

        private Sort(String str) {
            this.description = str;
        }

        /* access modifiers changed from: protected */
        public String getDescription() {
            return this.description;
        }
    }
}

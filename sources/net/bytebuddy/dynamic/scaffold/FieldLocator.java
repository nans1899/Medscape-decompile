package net.bytebuddy.dynamic.scaffold;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

public interface FieldLocator {

    public interface Factory {
        FieldLocator make(TypeDescription typeDescription);
    }

    Resolution locate(String str);

    Resolution locate(String str, TypeDescription typeDescription);

    public interface Resolution {
        FieldDescription getField();

        boolean isResolved();

        public enum Illegal implements Resolution {
            INSTANCE;

            public boolean isResolved() {
                return false;
            }

            public FieldDescription getField() {
                throw new IllegalStateException("Could not locate field");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Simple implements Resolution {
            private final FieldDescription fieldDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Simple) obj).fieldDescription);
            }

            public int hashCode() {
                return 527 + this.fieldDescription.hashCode();
            }

            public boolean isResolved() {
                return true;
            }

            protected Simple(FieldDescription fieldDescription2) {
                this.fieldDescription = fieldDescription2;
            }

            public FieldDescription getField() {
                return this.fieldDescription;
            }
        }
    }

    public enum NoOp implements FieldLocator, Factory {
        INSTANCE;

        public FieldLocator make(TypeDescription typeDescription) {
            return this;
        }

        public Resolution locate(String str) {
            return Resolution.Illegal.INSTANCE;
        }

        public Resolution locate(String str, TypeDescription typeDescription) {
            return Resolution.Illegal.INSTANCE;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static abstract class AbstractBase implements FieldLocator {
        protected final TypeDescription accessingType;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.accessingType.equals(((AbstractBase) obj).accessingType);
        }

        public int hashCode() {
            return 527 + this.accessingType.hashCode();
        }

        /* access modifiers changed from: protected */
        public abstract FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher);

        protected AbstractBase(TypeDescription typeDescription) {
            this.accessingType = typeDescription;
        }

        public Resolution locate(String str) {
            FieldList<?> locate = locate((ElementMatcher<? super FieldDescription>) ElementMatchers.named(str).and(ElementMatchers.isVisibleTo(this.accessingType)));
            return locate.size() == 1 ? new Resolution.Simple((FieldDescription) locate.getOnly()) : Resolution.Illegal.INSTANCE;
        }

        public Resolution locate(String str, TypeDescription typeDescription) {
            FieldList<?> locate = locate((ElementMatcher<? super FieldDescription>) ElementMatchers.named(str).and(ElementMatchers.fieldType(typeDescription)).and(ElementMatchers.isVisibleTo(this.accessingType)));
            return locate.size() == 1 ? new Resolution.Simple((FieldDescription) locate.getOnly()) : Resolution.Illegal.INSTANCE;
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForExactType extends AbstractBase {
        private final TypeDescription typeDescription;

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForExactType) obj).typeDescription);
        }

        public int hashCode() {
            return (super.hashCode() * 31) + this.typeDescription.hashCode();
        }

        public ForExactType(TypeDescription typeDescription2) {
            this(typeDescription2, typeDescription2);
        }

        public ForExactType(TypeDescription typeDescription2, TypeDescription typeDescription3) {
            super(typeDescription3);
            this.typeDescription = typeDescription2;
        }

        /* access modifiers changed from: protected */
        public FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher) {
            return (FieldList) this.typeDescription.getDeclaredFields().filter(elementMatcher);
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Factory implements Factory {
            private final TypeDescription typeDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Factory) obj).typeDescription);
            }

            public int hashCode() {
                return 527 + this.typeDescription.hashCode();
            }

            public Factory(TypeDescription typeDescription2) {
                this.typeDescription = typeDescription2;
            }

            public FieldLocator make(TypeDescription typeDescription2) {
                return new ForExactType(this.typeDescription, typeDescription2);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForClassHierarchy extends AbstractBase {
        private final TypeDescription typeDescription;

        public boolean equals(Object obj) {
            if (!super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForClassHierarchy) obj).typeDescription);
        }

        public int hashCode() {
            return (super.hashCode() * 31) + this.typeDescription.hashCode();
        }

        public ForClassHierarchy(TypeDescription typeDescription2) {
            this(typeDescription2, typeDescription2);
        }

        public ForClassHierarchy(TypeDescription typeDescription2, TypeDescription typeDescription3) {
            super(typeDescription3);
            this.typeDescription = typeDescription2;
        }

        /* access modifiers changed from: protected */
        public FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher) {
            for (TypeDefinition declaredFields : this.typeDescription) {
                FieldList<?> fieldList = (FieldList) declaredFields.getDeclaredFields().filter(elementMatcher);
                if (!fieldList.isEmpty()) {
                    return fieldList;
                }
            }
            return new FieldList.Empty();
        }

        public enum Factory implements Factory {
            INSTANCE;

            public FieldLocator make(TypeDescription typeDescription) {
                return new ForClassHierarchy(typeDescription);
            }
        }
    }

    public static class ForTopLevelType extends AbstractBase {
        protected ForTopLevelType(TypeDescription typeDescription) {
            super(typeDescription);
        }

        /* access modifiers changed from: protected */
        public FieldList<?> locate(ElementMatcher<? super FieldDescription> elementMatcher) {
            return (FieldList) this.accessingType.getDeclaredFields().filter(elementMatcher);
        }

        public enum Factory implements Factory {
            INSTANCE;

            public FieldLocator make(TypeDescription typeDescription) {
                return new ForTopLevelType(typeDescription);
            }
        }
    }
}

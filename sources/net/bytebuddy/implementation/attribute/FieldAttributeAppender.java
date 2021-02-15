package net.bytebuddy.implementation.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.jar.asm.FieldVisitor;

public interface FieldAttributeAppender {

    public enum NoOp implements FieldAttributeAppender, Factory {
        INSTANCE;

        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
        }

        public FieldAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }
    }

    void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter);

    public interface Factory {
        FieldAttributeAppender make(TypeDescription typeDescription);

        @HashCodeAndEqualsPlugin.Enhance
        public static class Compound implements Factory {
            private final List<Factory> factories;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.factories.equals(((Compound) obj).factories);
            }

            public int hashCode() {
                return 527 + this.factories.hashCode();
            }

            public Compound(Factory... factoryArr) {
                this((List<? extends Factory>) Arrays.asList(factoryArr));
            }

            public Compound(List<? extends Factory> list) {
                this.factories = new ArrayList();
                for (Factory factory : list) {
                    if (factory instanceof Compound) {
                        this.factories.addAll(((Compound) factory).factories);
                    } else if (!(factory instanceof NoOp)) {
                        this.factories.add(factory);
                    }
                }
            }

            public FieldAttributeAppender make(TypeDescription typeDescription) {
                ArrayList arrayList = new ArrayList(this.factories.size());
                for (Factory make : this.factories) {
                    arrayList.add(make.make(typeDescription));
                }
                return new Compound((List<? extends FieldAttributeAppender>) arrayList);
            }
        }
    }

    public enum ForInstrumentedField implements FieldAttributeAppender, Factory {
        INSTANCE;

        public FieldAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }

        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppender = (AnnotationAppender) fieldDescription.getType().accept(AnnotationAppender.ForTypeAnnotations.ofFieldType(new AnnotationAppender.Default(new AnnotationAppender.Target.OnField(fieldVisitor)), annotationValueFilter));
            for (AnnotationDescription append : fieldDescription.getDeclaredAnnotations()) {
                annotationAppender = annotationAppender.append(append, annotationValueFilter);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Explicit implements FieldAttributeAppender, Factory {
        private final List<? extends AnnotationDescription> annotations;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.annotations.equals(((Explicit) obj).annotations);
        }

        public int hashCode() {
            return 527 + this.annotations.hashCode();
        }

        public FieldAttributeAppender make(TypeDescription typeDescription) {
            return this;
        }

        public Explicit(List<? extends AnnotationDescription> list) {
            this.annotations = list;
        }

        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppender = new AnnotationAppender.Default(new AnnotationAppender.Target.OnField(fieldVisitor));
            for (AnnotationDescription append : this.annotations) {
                annotationAppender = annotationAppender.append(append, annotationValueFilter);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Compound implements FieldAttributeAppender {
        private final List<FieldAttributeAppender> fieldAttributeAppenders;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.fieldAttributeAppenders.equals(((Compound) obj).fieldAttributeAppenders);
        }

        public int hashCode() {
            return 527 + this.fieldAttributeAppenders.hashCode();
        }

        public Compound(FieldAttributeAppender... fieldAttributeAppenderArr) {
            this((List<? extends FieldAttributeAppender>) Arrays.asList(fieldAttributeAppenderArr));
        }

        public Compound(List<? extends FieldAttributeAppender> list) {
            this.fieldAttributeAppenders = new ArrayList();
            for (FieldAttributeAppender fieldAttributeAppender : list) {
                if (fieldAttributeAppender instanceof Compound) {
                    this.fieldAttributeAppenders.addAll(((Compound) fieldAttributeAppender).fieldAttributeAppenders);
                } else if (!(fieldAttributeAppender instanceof NoOp)) {
                    this.fieldAttributeAppenders.add(fieldAttributeAppender);
                }
            }
        }

        public void apply(FieldVisitor fieldVisitor, FieldDescription fieldDescription, AnnotationValueFilter annotationValueFilter) {
            for (FieldAttributeAppender apply : this.fieldAttributeAppenders) {
                apply.apply(fieldVisitor, fieldDescription, annotationValueFilter);
            }
        }
    }
}

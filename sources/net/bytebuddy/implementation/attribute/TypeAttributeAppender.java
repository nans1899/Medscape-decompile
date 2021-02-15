package net.bytebuddy.implementation.attribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.jar.asm.ClassVisitor;

public interface TypeAttributeAppender {

    public enum NoOp implements TypeAttributeAppender {
        INSTANCE;

        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
        }
    }

    void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter);

    public enum ForInstrumentedType implements TypeAttributeAppender {
        INSTANCE;

        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender ofTypeVariable = AnnotationAppender.ForTypeAnnotations.ofTypeVariable(new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor)), annotationValueFilter, true, typeDescription.getTypeVariables());
            TypeDescription.Generic superClass = typeDescription.getSuperClass();
            if (superClass != null) {
                ofTypeVariable = (AnnotationAppender) superClass.accept(AnnotationAppender.ForTypeAnnotations.ofSuperClass(ofTypeVariable, annotationValueFilter));
            }
            int i = 0;
            for (TypeDescription.Generic accept : typeDescription.getInterfaces()) {
                ofTypeVariable = (AnnotationAppender) accept.accept(AnnotationAppender.ForTypeAnnotations.ofInterfaceType(ofTypeVariable, annotationValueFilter, i));
                i++;
            }
            for (AnnotationDescription append : typeDescription.getDeclaredAnnotations()) {
                ofTypeVariable = ofTypeVariable.append(append, annotationValueFilter);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Differentiating implements TypeAttributeAppender {
            private final int annotationIndex;
            private final int interfaceTypeIndex;
            private final int typeVariableIndex;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Differentiating differentiating = (Differentiating) obj;
                return this.annotationIndex == differentiating.annotationIndex && this.typeVariableIndex == differentiating.typeVariableIndex && this.interfaceTypeIndex == differentiating.interfaceTypeIndex;
            }

            public int hashCode() {
                return ((((527 + this.annotationIndex) * 31) + this.typeVariableIndex) * 31) + this.interfaceTypeIndex;
            }

            public Differentiating(TypeDescription typeDescription) {
                this(typeDescription.getDeclaredAnnotations().size(), typeDescription.getTypeVariables().size(), typeDescription.getInterfaces().size());
            }

            protected Differentiating(int i, int i2, int i3) {
                this.annotationIndex = i;
                this.typeVariableIndex = i2;
                this.interfaceTypeIndex = i3;
            }

            public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
                AnnotationAppender annotationAppender = new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor));
                AnnotationAppender.ForTypeAnnotations.ofTypeVariable(annotationAppender, annotationValueFilter, true, this.typeVariableIndex, typeDescription.getTypeVariables());
                TypeList.Generic interfaces = typeDescription.getInterfaces();
                int i = this.interfaceTypeIndex;
                for (TypeDescription.Generic accept : (TypeList.Generic) interfaces.subList(i, interfaces.size())) {
                    annotationAppender = (AnnotationAppender) accept.accept(AnnotationAppender.ForTypeAnnotations.ofInterfaceType(annotationAppender, annotationValueFilter, i));
                    i++;
                }
                AnnotationList declaredAnnotations = typeDescription.getDeclaredAnnotations();
                for (AnnotationDescription append : (AnnotationList) declaredAnnotations.subList(this.annotationIndex, declaredAnnotations.size())) {
                    annotationAppender = annotationAppender.append(append, annotationValueFilter);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Explicit implements TypeAttributeAppender {
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

        public Explicit(List<? extends AnnotationDescription> list) {
            this.annotations = list;
        }

        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            AnnotationAppender annotationAppender = new AnnotationAppender.Default(new AnnotationAppender.Target.OnType(classVisitor));
            for (AnnotationDescription append : this.annotations) {
                annotationAppender = annotationAppender.append(append, annotationValueFilter);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Compound implements TypeAttributeAppender {
        private final List<TypeAttributeAppender> typeAttributeAppenders;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.typeAttributeAppenders.equals(((Compound) obj).typeAttributeAppenders);
        }

        public int hashCode() {
            return 527 + this.typeAttributeAppenders.hashCode();
        }

        public Compound(TypeAttributeAppender... typeAttributeAppenderArr) {
            this((List<? extends TypeAttributeAppender>) Arrays.asList(typeAttributeAppenderArr));
        }

        public Compound(List<? extends TypeAttributeAppender> list) {
            this.typeAttributeAppenders = new ArrayList();
            for (TypeAttributeAppender typeAttributeAppender : list) {
                if (typeAttributeAppender instanceof Compound) {
                    this.typeAttributeAppenders.addAll(((Compound) typeAttributeAppender).typeAttributeAppenders);
                } else if (!(typeAttributeAppender instanceof NoOp)) {
                    this.typeAttributeAppenders.add(typeAttributeAppender);
                }
            }
        }

        public void apply(ClassVisitor classVisitor, TypeDescription typeDescription, AnnotationValueFilter annotationValueFilter) {
            for (TypeAttributeAppender apply : this.typeAttributeAppenders) {
                apply.apply(classVisitor, typeDescription, annotationValueFilter);
            }
        }
    }
}

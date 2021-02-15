package net.bytebuddy.build;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.EqualsMethod;
import net.bytebuddy.implementation.HashCodeMethod;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

@Enhance
public class HashCodeAndEqualsPlugin implements Plugin, Plugin.Factory {

    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Enhance {

        public enum InvokeSuper {
            IF_DECLARED {
                /* access modifiers changed from: protected */
                public HashCodeMethod hashCodeMethod(TypeDescription typeDescription) {
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    while (superClass != null && !superClass.represents(Object.class)) {
                        if (superClass.asErasure().getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Enhance.class)) {
                            return HashCodeMethod.usingSuperClassOffset();
                        }
                        MethodList methodList = (MethodList) superClass.getDeclaredMethods().filter(ElementMatchers.isHashCode());
                        if (methodList.isEmpty()) {
                            superClass = superClass.getSuperClass();
                        } else if (((MethodDescription) methodList.getOnly()).isAbstract()) {
                            return HashCodeMethod.usingDefaultOffset();
                        } else {
                            return HashCodeMethod.usingSuperClassOffset();
                        }
                    }
                    return HashCodeMethod.usingDefaultOffset();
                }

                /* access modifiers changed from: protected */
                public EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    TypeDefinition superClass = typeDescription.getSuperClass();
                    while (superClass != null && !superClass.represents(Object.class)) {
                        if (superClass.asErasure().getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Enhance.class)) {
                            return EqualsMethod.requiringSuperClassEquality();
                        }
                        MethodList methodList = (MethodList) superClass.getDeclaredMethods().filter(ElementMatchers.isHashCode());
                        if (methodList.isEmpty()) {
                            superClass = superClass.getSuperClass().asErasure();
                        } else if (((MethodDescription) methodList.getOnly()).isAbstract()) {
                            return EqualsMethod.isolated();
                        } else {
                            return EqualsMethod.requiringSuperClassEquality();
                        }
                    }
                    return EqualsMethod.isolated();
                }
            },
            IF_ANNOTATED {
                /* access modifiers changed from: protected */
                public HashCodeMethod hashCodeMethod(TypeDescription typeDescription) {
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    if (superClass == null || !superClass.asErasure().getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Enhance.class)) {
                        return HashCodeMethod.usingDefaultOffset();
                    }
                    return HashCodeMethod.usingSuperClassOffset();
                }

                /* access modifiers changed from: protected */
                public EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    if (superClass == null || !superClass.asErasure().getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Enhance.class)) {
                        return EqualsMethod.isolated();
                    }
                    return EqualsMethod.requiringSuperClassEquality();
                }
            },
            ALWAYS {
                /* access modifiers changed from: protected */
                public HashCodeMethod hashCodeMethod(TypeDescription typeDescription) {
                    return HashCodeMethod.usingSuperClassOffset();
                }

                /* access modifiers changed from: protected */
                public EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    return EqualsMethod.requiringSuperClassEquality();
                }
            },
            NEVER {
                /* access modifiers changed from: protected */
                public HashCodeMethod hashCodeMethod(TypeDescription typeDescription) {
                    return HashCodeMethod.usingDefaultOffset();
                }

                /* access modifiers changed from: protected */
                public EqualsMethod equalsMethod(TypeDescription typeDescription) {
                    return EqualsMethod.isolated();
                }
            };

            /* access modifiers changed from: protected */
            public abstract EqualsMethod equalsMethod(TypeDescription typeDescription);

            /* access modifiers changed from: protected */
            public abstract HashCodeMethod hashCodeMethod(TypeDescription typeDescription);
        }

        boolean includeSyntheticFields() default false;

        InvokeSuper invokeSuper() default InvokeSuper.IF_DECLARED;

        boolean permitSubclassEquality() default false;

        boolean simpleComparisonsFirst() default true;
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Sorted {
        public static final int DEFAULT = 0;

        int value();
    }

    @Documented
    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ValueHandling {

        public enum Sort {
            IGNORE,
            REVERSE_NULLABILITY
        }

        Sort value();
    }

    public void close() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass();
    }

    public int hashCode() {
        return 17;
    }

    public Plugin make() {
        return this;
    }

    /* access modifiers changed from: protected */
    public ElementMatcher<FieldDescription> nonNullable(ElementMatcher<FieldDescription> elementMatcher) {
        return elementMatcher;
    }

    public boolean matches(TypeDescription typeDescription) {
        return typeDescription.getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Enhance.class);
    }

    public DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
        ElementMatcher.Junction junction;
        ElementMatcher.Junction junction2;
        Enhance loadSilent = typeDescription.getDeclaredAnnotations().ofType(Enhance.class).loadSilent();
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition<?> receiverTypeDefinition = builder;
        if (((MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isHashCode())).isEmpty()) {
            DynamicType.Builder.MethodDefinition.ImplementationDefinition<?> method = builder.method(ElementMatchers.isHashCode());
            HashCodeMethod hashCodeMethod = loadSilent.invokeSuper().hashCodeMethod(typeDescription);
            if (loadSilent.includeSyntheticFields()) {
                junction2 = ElementMatchers.none();
            } else {
                junction2 = ElementMatchers.isSynthetic();
            }
            receiverTypeDefinition = method.intercept(hashCodeMethod.withIgnoredFields(junction2).withIgnoredFields(new ValueMatcher(ValueHandling.Sort.IGNORE)).withNonNullableFields(nonNullable(new ValueMatcher(ValueHandling.Sort.REVERSE_NULLABILITY))));
        }
        if (!((MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isEquals())).isEmpty()) {
            return receiverTypeDefinition;
        }
        EqualsMethod equalsMethod = loadSilent.invokeSuper().equalsMethod(typeDescription);
        if (loadSilent.includeSyntheticFields()) {
            junction = ElementMatchers.none();
        } else {
            junction = ElementMatchers.isSynthetic();
        }
        EqualsMethod withFieldOrder = equalsMethod.withIgnoredFields(junction).withIgnoredFields(new ValueMatcher(ValueHandling.Sort.IGNORE)).withNonNullableFields(nonNullable(new ValueMatcher(ValueHandling.Sort.REVERSE_NULLABILITY))).withFieldOrder(AnnotationOrderComparator.INSTANCE);
        EqualsMethod equalsMethod2 = withFieldOrder;
        if (loadSilent.simpleComparisonsFirst()) {
            equalsMethod2 = withFieldOrder.withPrimitiveTypedFieldsFirst().withEnumerationTypedFieldsFirst().withPrimitiveWrapperTypedFieldsFirst().withStringTypedFieldsFirst();
        }
        DynamicType.Builder.MethodDefinition.ImplementationDefinition<T> method2 = receiverTypeDefinition.method(ElementMatchers.isEquals());
        Implementation implementation = equalsMethod2;
        if (loadSilent.permitSubclassEquality()) {
            implementation = equalsMethod2.withSubclassEquality();
        }
        return method2.intercept(implementation);
    }

    @Enhance
    public static class WithNonNullableFields extends HashCodeAndEqualsPlugin {
        public boolean equals(Object obj) {
            if (!HashCodeAndEqualsPlugin.super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass();
        }

        public int hashCode() {
            return HashCodeAndEqualsPlugin.super.hashCode();
        }

        public /* bridge */ /* synthetic */ boolean matches(Object obj) {
            return HashCodeAndEqualsPlugin.super.matches((TypeDescription) obj);
        }

        /* access modifiers changed from: protected */
        public ElementMatcher<FieldDescription> nonNullable(ElementMatcher<FieldDescription> elementMatcher) {
            return ElementMatchers.not(elementMatcher);
        }
    }

    protected enum AnnotationOrderComparator implements Comparator<FieldDescription.InDefinedShape> {
        INSTANCE;

        public int compare(FieldDescription.InDefinedShape inDefinedShape, FieldDescription.InDefinedShape inDefinedShape2) {
            int i;
            int i2;
            AnnotationDescription.Loadable<Sorted> ofType = inDefinedShape.getDeclaredAnnotations().ofType(Sorted.class);
            AnnotationDescription.Loadable<Sorted> ofType2 = inDefinedShape2.getDeclaredAnnotations().ofType(Sorted.class);
            if (ofType == null) {
                i = 0;
            } else {
                i = ofType.loadSilent().value();
            }
            if (ofType2 == null) {
                i2 = 0;
            } else {
                i2 = ofType2.loadSilent().value();
            }
            if (i > i2) {
                return -1;
            }
            if (i < i2) {
                return 1;
            }
            return 0;
        }
    }

    @Enhance
    protected static class ValueMatcher implements ElementMatcher<FieldDescription> {
        private final ValueHandling.Sort sort;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.sort.equals(((ValueMatcher) obj).sort);
        }

        public int hashCode() {
            return 527 + this.sort.hashCode();
        }

        protected ValueMatcher(ValueHandling.Sort sort2) {
            this.sort = sort2;
        }

        public boolean matches(FieldDescription fieldDescription) {
            AnnotationDescription.Loadable<ValueHandling> ofType = fieldDescription.getDeclaredAnnotations().ofType(ValueHandling.class);
            return ofType != null && ofType.loadSilent().value() == this.sort;
        }
    }
}

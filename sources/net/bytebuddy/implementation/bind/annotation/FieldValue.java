package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.matcher.ElementMatchers;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldValue {
    Class<?> declaringType() default void.class;

    String value() default "";

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldValue> {
        INSTANCE(new Delegate());
        
        /* access modifiers changed from: private */
        public static final MethodDescription.InDefinedShape DECLARING_TYPE = null;
        /* access modifiers changed from: private */
        public static final MethodDescription.InDefinedShape FIELD_NAME = null;
        private final TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldValue> delegate;

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(FieldValue.class).getDeclaredMethods();
            DECLARING_TYPE = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("declaringType"))).getOnly();
            FIELD_NAME = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("value"))).getOnly();
        }

        private Binder(TargetMethodAnnotationDrivenBinder.ParameterBinder<FieldValue> parameterBinder) {
            this.delegate = parameterBinder;
        }

        public Class<FieldValue> getHandledType() {
            return this.delegate.getHandledType();
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<FieldValue> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            return this.delegate.bind(loadable, methodDescription, parameterDescription, target, assigner, typing);
        }

        protected static class Delegate extends TargetMethodAnnotationDrivenBinder.ParameterBinder.ForFieldBinding<FieldValue> {
            protected Delegate() {
            }

            public Class<FieldValue> getHandledType() {
                return FieldValue.class;
            }

            /* access modifiers changed from: protected */
            public String fieldName(AnnotationDescription.Loadable<FieldValue> loadable) {
                return (String) loadable.getValue(Binder.FIELD_NAME).resolve(String.class);
            }

            /* access modifiers changed from: protected */
            public TypeDescription declaringType(AnnotationDescription.Loadable<FieldValue> loadable) {
                return (TypeDescription) loadable.getValue(Binder.DECLARING_TYPE).resolve(TypeDescription.class);
            }

            /* access modifiers changed from: protected */
            public MethodDelegationBinder.ParameterBinding<?> bind(FieldDescription fieldDescription, AnnotationDescription.Loadable<FieldValue> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner) {
                StackManipulation stackManipulation;
                StackManipulation[] stackManipulationArr = new StackManipulation[3];
                if (fieldDescription.isStatic()) {
                    stackManipulation = StackManipulation.Trivial.INSTANCE;
                } else {
                    stackManipulation = MethodVariableAccess.loadThis();
                }
                stackManipulationArr[0] = stackManipulation;
                stackManipulationArr[1] = FieldAccess.forField(fieldDescription).read();
                stackManipulationArr[2] = assigner.assign(fieldDescription.getType(), parameterDescription.getType(), RuntimeType.Verifier.check(parameterDescription));
                StackManipulation.Compound compound = new StackManipulation.Compound(stackManipulationArr);
                return compound.isValid() ? new MethodDelegationBinder.ParameterBinding.Anonymous(compound) : MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
        }
    }
}

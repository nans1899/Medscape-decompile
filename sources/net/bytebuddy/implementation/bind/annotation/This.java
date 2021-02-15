package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface This {
    boolean optional() default false;

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<This> {
        INSTANCE;

        public Class<This> getHandledType() {
            return This.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<This> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            StackManipulation stackManipulation;
            if (parameterDescription.getType().isPrimitive()) {
                throw new IllegalStateException(parameterDescription + " uses a primitive type with a @This annotation");
            } else if (parameterDescription.getType().isArray()) {
                throw new IllegalStateException(parameterDescription + " uses an array type with a @This annotation");
            } else if (methodDescription.isStatic() && !loadable.loadSilent().optional()) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            } else {
                if (methodDescription.isStatic()) {
                    stackManipulation = NullConstant.INSTANCE;
                } else {
                    stackManipulation = new StackManipulation.Compound(MethodVariableAccess.loadThis(), assigner.assign(target.getInstrumentedType().asGenericType(), parameterDescription.getType(), typing));
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(stackManipulation);
            }
        }
    }
}

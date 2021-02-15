package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StubValue {

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<StubValue> {
        INSTANCE;

        public Class<StubValue> getHandledType() {
            return StubValue.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<StubValue> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            StackManipulation stackManipulation;
            if (parameterDescription.getType().represents(Object.class)) {
                if (methodDescription.getReturnType().represents(Void.TYPE)) {
                    stackManipulation = NullConstant.INSTANCE;
                } else {
                    stackManipulation = new StackManipulation.Compound(DefaultValue.of(methodDescription.getReturnType().asErasure()), assigner.assign(methodDescription.getReturnType(), TypeDescription.Generic.OBJECT, typing));
                }
                return new MethodDelegationBinder.ParameterBinding.Anonymous(stackManipulation);
            }
            throw new IllegalStateException(parameterDescription + " uses StubValue annotation on non-Object type");
        }
    }
}

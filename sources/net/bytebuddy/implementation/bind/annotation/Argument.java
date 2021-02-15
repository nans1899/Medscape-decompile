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
import net.bytebuddy.implementation.bind.ArgumentTypeResolver;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Argument {

    public enum BindingMechanic {
        UNIQUE {
            /* access modifiers changed from: protected */
            public MethodDelegationBinder.ParameterBinding<?> makeBinding(TypeDescription.Generic generic, TypeDescription.Generic generic2, int i, Assigner assigner, Assigner.Typing typing, int i2) {
                return MethodDelegationBinder.ParameterBinding.Unique.of(new StackManipulation.Compound(MethodVariableAccess.of(generic).loadFrom(i2), assigner.assign(generic, generic2, typing)), new ArgumentTypeResolver.ParameterIndexToken(i));
            }
        },
        ANONYMOUS {
            /* access modifiers changed from: protected */
            public MethodDelegationBinder.ParameterBinding<?> makeBinding(TypeDescription.Generic generic, TypeDescription.Generic generic2, int i, Assigner assigner, Assigner.Typing typing, int i2) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new StackManipulation.Compound(MethodVariableAccess.of(generic).loadFrom(i2), assigner.assign(generic, generic2, typing)));
            }
        };

        /* access modifiers changed from: protected */
        public abstract MethodDelegationBinder.ParameterBinding<?> makeBinding(TypeDescription.Generic generic, TypeDescription.Generic generic2, int i, Assigner assigner, Assigner.Typing typing, int i2);
    }

    BindingMechanic bindingMechanic() default BindingMechanic.UNIQUE;

    int value();

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Argument> {
        INSTANCE;

        public Class<Argument> getHandledType() {
            return Argument.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Argument> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            Argument loadSilent = loadable.loadSilent();
            if (loadSilent.value() < 0) {
                throw new IllegalArgumentException("@Argument annotation on " + parameterDescription + " specifies negative index");
            } else if (methodDescription.getParameters().size() <= loadSilent.value()) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            } else {
                return loadSilent.bindingMechanic().makeBinding(((ParameterDescription) methodDescription.getParameters().get(loadSilent.value())).getType(), parameterDescription.getType(), loadSilent.value(), assigner, typing, ((ParameterDescription) methodDescription.getParameters().get(loadSilent.value())).getOffset());
            }
        }
    }
}

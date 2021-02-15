package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.MethodConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Origin {
    boolean cache() default true;

    boolean privileged() default false;

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Origin> {
        INSTANCE;

        private static StackManipulation methodConstant(Origin origin, MethodDescription.InDefinedShape inDefinedShape) {
            MethodConstant.CanCache canCache;
            if (origin.privileged()) {
                canCache = MethodConstant.ofPrivileged(inDefinedShape);
            } else {
                canCache = MethodConstant.of(inDefinedShape);
            }
            return origin.cache() ? canCache.cached() : canCache;
        }

        public Class<Origin> getHandledType() {
            return Origin.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Origin> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            TypeDescription asErasure = parameterDescription.getType().asErasure();
            if (asErasure.represents(Class.class)) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(ClassConstant.of(target.getOriginType().asErasure()));
            }
            if (asErasure.represents(Method.class)) {
                return methodDescription.isMethod() ? new MethodDelegationBinder.ParameterBinding.Anonymous(methodConstant(loadable.loadSilent(), (MethodDescription.InDefinedShape) methodDescription.asDefined())) : MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            if (asErasure.represents(Constructor.class)) {
                return methodDescription.isConstructor() ? new MethodDelegationBinder.ParameterBinding.Anonymous(methodConstant(loadable.loadSilent(), (MethodDescription.InDefinedShape) methodDescription.asDefined())) : MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            }
            if (JavaType.EXECUTABLE.getTypeStub().equals(asErasure)) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(methodConstant(loadable.loadSilent(), (MethodDescription.InDefinedShape) methodDescription.asDefined()));
            }
            if (asErasure.represents(String.class)) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new TextConstant(methodDescription.toString()));
            }
            if (asErasure.represents(Integer.TYPE)) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(IntegerConstant.forValue(methodDescription.getModifiers()));
            }
            if (asErasure.equals(JavaType.METHOD_HANDLE.getTypeStub())) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new JavaConstantValue(JavaConstant.MethodHandle.of((MethodDescription.InDefinedShape) methodDescription.asDefined())));
            }
            if (asErasure.equals(JavaType.METHOD_TYPE.getTypeStub())) {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new JavaConstantValue(JavaConstant.MethodType.of((MethodDescription) methodDescription.asDefined())));
            }
            throw new IllegalStateException("The " + parameterDescription + " method's " + parameterDescription.getIndex() + " parameter is annotated with a Origin annotation with an argument not representing a Class, Method, Constructor, String, int, MethodType or MethodHandle type");
        }
    }
}

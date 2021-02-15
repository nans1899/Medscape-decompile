package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.matcher.ElementMatchers;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    Class<?> proxyType() default void.class;

    boolean serializableProxy() default false;

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Default> {
        INSTANCE;
        
        private static final MethodDescription.InDefinedShape PROXY_TYPE = null;
        private static final MethodDescription.InDefinedShape SERIALIZABLE_PROXY = null;

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Default.class).getDeclaredMethods();
            SERIALIZABLE_PROXY = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("serializableProxy"))).getOnly();
            PROXY_TYPE = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("proxyType"))).getOnly();
        }

        public Class<Default> getHandledType() {
            return Default.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Default> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            TypeDescription resolve = TypeLocator.ForType.of((TypeDescription) loadable.getValue(PROXY_TYPE).resolve(TypeDescription.class)).resolve(parameterDescription.getType());
            if (!resolve.isInterface()) {
                throw new IllegalStateException(parameterDescription + " uses the @Default annotation on an invalid type");
            } else if (methodDescription.isStatic() || !target.getInstrumentedType().getInterfaces().asErasures().contains(resolve)) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            } else {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(new TypeProxy.ForDefaultMethod(resolve, target, ((Boolean) loadable.getValue(SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue()));
            }
        }

        protected interface TypeLocator {
            TypeDescription resolve(TypeDescription.Generic generic);

            public enum ForParameterType implements TypeLocator {
                INSTANCE;

                public TypeDescription resolve(TypeDescription.Generic generic) {
                    return generic.asErasure();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForType implements TypeLocator {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForType) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                protected ForType(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                protected static TypeLocator of(TypeDescription typeDescription2) {
                    if (typeDescription2.represents(Void.TYPE)) {
                        return ForParameterType.INSTANCE;
                    }
                    if (typeDescription2.isInterface()) {
                        return new ForType(typeDescription2);
                    }
                    throw new IllegalStateException("Cannot assign proxy to " + typeDescription2);
                }

                public TypeDescription resolve(TypeDescription.Generic generic) {
                    if (this.typeDescription.isAssignableTo(generic.asErasure())) {
                        return this.typeDescription;
                    }
                    throw new IllegalStateException("Impossible to assign " + this.typeDescription + " to parameter of type " + generic);
                }
            }
        }
    }
}

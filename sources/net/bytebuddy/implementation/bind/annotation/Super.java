package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.matcher.ElementMatchers;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {
    Class<?>[] constructorParameters() default {};

    boolean ignoreFinalizer() default true;

    Class<?> proxyType() default void.class;

    boolean serializableProxy() default false;

    Instantiation strategy() default Instantiation.CONSTRUCTOR;

    public enum Instantiation {
        CONSTRUCTOR {
            /* access modifiers changed from: protected */
            public StackManipulation proxyFor(TypeDescription typeDescription, Implementation.Target target, AnnotationDescription.Loadable<Super> loadable) {
                return new TypeProxy.ForSuperMethodByConstructor(typeDescription, target, Arrays.asList((Object[]) loadable.getValue(Instantiation.CONSTRUCTOR_PARAMETERS).resolve(TypeDescription[].class)), ((Boolean) loadable.getValue(Instantiation.IGNORE_FINALIZER).resolve(Boolean.class)).booleanValue(), ((Boolean) loadable.getValue(Instantiation.SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue());
            }
        },
        UNSAFE {
            /* access modifiers changed from: protected */
            public StackManipulation proxyFor(TypeDescription typeDescription, Implementation.Target target, AnnotationDescription.Loadable<Super> loadable) {
                return new TypeProxy.ForSuperMethodByReflectionFactory(typeDescription, target, ((Boolean) loadable.getValue(Instantiation.IGNORE_FINALIZER).resolve(Boolean.class)).booleanValue(), ((Boolean) loadable.getValue(Instantiation.SERIALIZABLE_PROXY).resolve(Boolean.class)).booleanValue());
            }
        };
        
        /* access modifiers changed from: private */
        public static final MethodDescription.InDefinedShape CONSTRUCTOR_PARAMETERS = null;
        /* access modifiers changed from: private */
        public static final MethodDescription.InDefinedShape IGNORE_FINALIZER = null;
        /* access modifiers changed from: private */
        public static final MethodDescription.InDefinedShape SERIALIZABLE_PROXY = null;

        /* access modifiers changed from: protected */
        public abstract StackManipulation proxyFor(TypeDescription typeDescription, Implementation.Target target, AnnotationDescription.Loadable<Super> loadable);

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Super.class).getDeclaredMethods();
            IGNORE_FINALIZER = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("ignoreFinalizer"))).getOnly();
            SERIALIZABLE_PROXY = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("serializableProxy"))).getOnly();
            CONSTRUCTOR_PARAMETERS = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("constructorParameters"))).getOnly();
        }
    }

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<Super> {
        INSTANCE;
        
        private static final MethodDescription.InDefinedShape PROXY_TYPE = null;
        private static final MethodDescription.InDefinedShape STRATEGY = null;

        static {
            MethodList<MethodDescription.InDefinedShape> declaredMethods = TypeDescription.ForLoadedType.of(Super.class).getDeclaredMethods();
            STRATEGY = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("strategy"))).getOnly();
            PROXY_TYPE = (MethodDescription.InDefinedShape) ((MethodList) declaredMethods.filter(ElementMatchers.named("proxyType"))).getOnly();
        }

        public Class<Super> getHandledType() {
            return Super.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<Super> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            if (parameterDescription.getType().isPrimitive() || parameterDescription.getType().isArray()) {
                throw new IllegalStateException(parameterDescription + " uses the @Super annotation on an invalid type");
            }
            TypeDescription resolve = TypeLocator.ForType.of((TypeDescription) loadable.getValue(PROXY_TYPE).resolve(TypeDescription.class)).resolve(target.getInstrumentedType(), parameterDescription.getType());
            if (resolve.isFinal()) {
                throw new IllegalStateException("Cannot extend final type as @Super proxy: " + resolve);
            } else if (methodDescription.isStatic() || !target.getInstrumentedType().isAssignableTo(resolve)) {
                return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
            } else {
                return new MethodDelegationBinder.ParameterBinding.Anonymous(((Instantiation) ((EnumerationDescription) loadable.getValue(STRATEGY).resolve(EnumerationDescription.class)).load(Instantiation.class)).proxyFor(resolve, target, loadable));
            }
        }

        protected interface TypeLocator {

            public enum ForInstrumentedType implements TypeLocator {
                INSTANCE;

                public TypeDescription resolve(TypeDescription typeDescription, TypeDescription.Generic generic) {
                    return typeDescription;
                }
            }

            TypeDescription resolve(TypeDescription typeDescription, TypeDescription.Generic generic);

            public enum ForParameterType implements TypeLocator {
                INSTANCE;

                public TypeDescription resolve(TypeDescription typeDescription, TypeDescription.Generic generic) {
                    TypeDescription asErasure = generic.asErasure();
                    return asErasure.equals(typeDescription) ? typeDescription : asErasure;
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
                    if (typeDescription2.represents(TargetType.class)) {
                        return ForInstrumentedType.INSTANCE;
                    }
                    if (!typeDescription2.isPrimitive() && !typeDescription2.isArray()) {
                        return new ForType(typeDescription2);
                    }
                    throw new IllegalStateException("Cannot assign proxy to " + typeDescription2);
                }

                public TypeDescription resolve(TypeDescription typeDescription2, TypeDescription.Generic generic) {
                    if (this.typeDescription.isAssignableTo(generic.asErasure())) {
                        return this.typeDescription;
                    }
                    throw new IllegalStateException("Impossible to assign " + this.typeDescription + " to parameter of type " + generic);
                }
            }
        }
    }
}

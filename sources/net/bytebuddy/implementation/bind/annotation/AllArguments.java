package net.bytebuddy.implementation.bind.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.utility.CompoundList;

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AllArguments {
    boolean includeSelf() default false;

    Assignment value() default Assignment.STRICT;

    public enum Assignment {
        STRICT(true),
        SLACK(false);
        
        private final boolean strict;

        private Assignment(boolean z) {
            this.strict = z;
        }

        /* access modifiers changed from: protected */
        public boolean isStrict() {
            return this.strict;
        }
    }

    public enum Binder implements TargetMethodAnnotationDrivenBinder.ParameterBinder<AllArguments> {
        INSTANCE;

        public Class<AllArguments> getHandledType() {
            return AllArguments.class;
        }

        public MethodDelegationBinder.ParameterBinding<?> bind(AnnotationDescription.Loadable<AllArguments> loadable, MethodDescription methodDescription, ParameterDescription parameterDescription, Implementation.Target target, Assigner assigner, Assigner.Typing typing) {
            TypeDescription.Generic generic;
            List<TypeDescription.Generic> list;
            if (parameterDescription.getType().represents(Object.class)) {
                generic = TypeDescription.Generic.OBJECT;
            } else if (parameterDescription.getType().isArray()) {
                generic = parameterDescription.getType().getComponentType();
            } else {
                throw new IllegalStateException("Expected an array type for all argument annotation on " + methodDescription);
            }
            int i = (methodDescription.isStatic() || !loadable.loadSilent().includeSelf()) ? 0 : 1;
            ArrayList arrayList = new ArrayList(methodDescription.getParameters().size() + i);
            int i2 = (methodDescription.isStatic() || i != 0) ? 0 : 1;
            if (i != 0) {
                list = CompoundList.of(target.getInstrumentedType().asGenericType(), methodDescription.getParameters().asTypeList());
            } else {
                list = methodDescription.getParameters().asTypeList();
            }
            for (TypeDescription.Generic generic2 : list) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.of(generic2).loadFrom(i2), assigner.assign(generic2, generic, typing));
                if (compound.isValid()) {
                    arrayList.add(compound);
                } else if (loadable.loadSilent().value().isStrict()) {
                    return MethodDelegationBinder.ParameterBinding.Illegal.INSTANCE;
                }
                i2 += generic2.getStackSize().getSize();
            }
            return new MethodDelegationBinder.ParameterBinding.Anonymous(ArrayFactory.forType(generic).withValues(arrayList));
        }
    }
}

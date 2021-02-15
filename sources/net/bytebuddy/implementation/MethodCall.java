package net.bytebuddy.implementation;

import androidx.core.app.NotificationCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;
import net.bytebuddy.implementation.bytecode.collection.ArrayFactory;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

@HashCodeAndEqualsPlugin.Enhance
public class MethodCall implements Implementation.Composable {
    protected final List<ArgumentLoader.Factory> argumentLoaders;
    protected final Assigner assigner;
    protected final MethodInvoker.Factory methodInvoker;
    protected final MethodLocator.Factory methodLocator;
    protected final TargetHandler.Factory targetHandler;
    protected final TerminationHandler.Factory terminationHandler;
    protected final Assigner.Typing typing;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MethodCall methodCall = (MethodCall) obj;
        return this.typing.equals(methodCall.typing) && this.methodLocator.equals(methodCall.methodLocator) && this.targetHandler.equals(methodCall.targetHandler) && this.argumentLoaders.equals(methodCall.argumentLoaders) && this.methodInvoker.equals(methodCall.methodInvoker) && this.terminationHandler.equals(methodCall.terminationHandler) && this.assigner.equals(methodCall.assigner);
    }

    public int hashCode() {
        return ((((((((((((527 + this.methodLocator.hashCode()) * 31) + this.targetHandler.hashCode()) * 31) + this.argumentLoaders.hashCode()) * 31) + this.methodInvoker.hashCode()) * 31) + this.terminationHandler.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.typing.hashCode();
    }

    protected MethodCall(MethodLocator.Factory factory, TargetHandler.Factory factory2, List<ArgumentLoader.Factory> list, MethodInvoker.Factory factory3, TerminationHandler.Factory factory4, Assigner assigner2, Assigner.Typing typing2) {
        this.methodLocator = factory;
        this.targetHandler = factory2;
        this.argumentLoaders = list;
        this.methodInvoker = factory3;
        this.terminationHandler = factory4;
        this.assigner = assigner2;
        this.typing = typing2;
    }

    public static WithoutSpecifiedTarget invoke(Method method) {
        return invoke((MethodDescription) new MethodDescription.ForLoadedMethod(method));
    }

    public static WithoutSpecifiedTarget invoke(Constructor<?> constructor) {
        return invoke((MethodDescription) new MethodDescription.ForLoadedConstructor(constructor));
    }

    public static WithoutSpecifiedTarget invoke(MethodDescription methodDescription) {
        return invoke((MethodLocator.Factory) new MethodLocator.ForExplicitMethod(methodDescription));
    }

    public static WithoutSpecifiedTarget invoke(ElementMatcher<? super MethodDescription> elementMatcher) {
        return invoke(elementMatcher, MethodGraph.Compiler.DEFAULT);
    }

    public static WithoutSpecifiedTarget invoke(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
        return invoke((MethodLocator.Factory) new MethodLocator.ForElementMatcher.Factory(elementMatcher, compiler));
    }

    public static WithoutSpecifiedTarget invoke(MethodLocator.Factory factory) {
        return new WithoutSpecifiedTarget(factory);
    }

    public static WithoutSpecifiedTarget invokeSelf() {
        return new WithoutSpecifiedTarget(MethodLocator.ForInstrumentedMethod.INSTANCE);
    }

    public static MethodCall invokeSuper() {
        return invokeSelf().onSuper();
    }

    public static Implementation.Composable call(Callable<?> callable) {
        try {
            return invoke(Callable.class.getMethod(NotificationCompat.CATEGORY_CALL, new Class[0])).on(callable, Callable.class).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Callable::call method", e);
        }
    }

    public static Implementation.Composable run(Runnable runnable) {
        try {
            return invoke(Runnable.class.getMethod("run", new Class[0])).on(runnable, Runnable.class).withAssigner(Assigner.DEFAULT, Assigner.Typing.DYNAMIC);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Runnable::run method", e);
        }
    }

    public static MethodCall construct(Constructor<?> constructor) {
        return construct((MethodDescription) new MethodDescription.ForLoadedConstructor(constructor));
    }

    public static MethodCall construct(MethodDescription methodDescription) {
        if (methodDescription.isConstructor()) {
            return new MethodCall(new MethodLocator.ForExplicitMethod(methodDescription), TargetHandler.ForConstructingInvocation.Factory.INSTANCE, Collections.emptyList(), MethodInvoker.ForContextualInvocation.Factory.INSTANCE, TerminationHandler.Simple.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
        }
        throw new IllegalArgumentException("Not a constructor: " + methodDescription);
    }

    public MethodCall with(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object of : objArr) {
            arrayList.add(ArgumentLoader.ForStackManipulation.of(of));
        }
        return with((List<? extends ArgumentLoader.Factory>) arrayList);
    }

    public MethodCall with(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription of : typeDescriptionArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(ClassConstant.of(of), (Type) Class.class));
        }
        return with((List<? extends ArgumentLoader.Factory>) arrayList);
    }

    public MethodCall with(EnumerationDescription... enumerationDescriptionArr) {
        ArrayList arrayList = new ArrayList(enumerationDescriptionArr.length);
        for (EnumerationDescription enumerationDescription : enumerationDescriptionArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation(FieldAccess.forEnumeration(enumerationDescription), (TypeDefinition) enumerationDescription.getEnumerationType()));
        }
        return with((List<? extends ArgumentLoader.Factory>) arrayList);
    }

    public MethodCall with(JavaConstant... javaConstantArr) {
        ArrayList arrayList = new ArrayList(javaConstantArr.length);
        for (JavaConstant javaConstant : javaConstantArr) {
            arrayList.add(new ArgumentLoader.ForStackManipulation((StackManipulation) new JavaConstantValue(javaConstant), (TypeDefinition) javaConstant.getType()));
        }
        return with((List<? extends ArgumentLoader.Factory>) arrayList);
    }

    public MethodCall withReference(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            arrayList.add(obj == null ? ArgumentLoader.ForNullConstant.INSTANCE : new ArgumentLoader.ForInstance.Factory(obj));
        }
        return with((List<? extends ArgumentLoader.Factory>) arrayList);
    }

    public MethodCall withArgument(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            if (i2 >= 0) {
                arrayList.add(new ArgumentLoader.ForMethodParameter.Factory(i2));
                i++;
            } else {
                throw new IllegalArgumentException("Negative index: " + i2);
            }
        }
        return with((List<? extends ArgumentLoader.Factory>) arrayList);
    }

    public MethodCall withAllArguments() {
        return with(ArgumentLoader.ForMethodParameter.OfInstrumentedMethod.INSTANCE);
    }

    public MethodCall withArgumentArray() {
        return with(ArgumentLoader.ForMethodParameterArray.ForInstrumentedMethod.INSTANCE);
    }

    public MethodCall withArgumentArrayElements(int i) {
        if (i >= 0) {
            return with(new ArgumentLoader.ForMethodParameterArrayElement.OfInvokedMethod(i));
        }
        throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
    }

    public MethodCall withArgumentArrayElements(int i, int i2) {
        return withArgumentArrayElements(i, 0, i2);
    }

    public MethodCall withArgumentArrayElements(int i, int i2, int i3) {
        if (i < 0) {
            throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
        } else if (i2 < 0) {
            throw new IllegalArgumentException("An array index cannot be negative: " + i2);
        } else if (i3 == 0) {
            return this;
        } else {
            if (i3 >= 0) {
                ArrayList arrayList = new ArrayList(i3);
                for (int i4 = 0; i4 < i3; i4++) {
                    arrayList.add(new ArgumentLoader.ForMethodParameterArrayElement.OfParameter(i, i2 + i4));
                }
                return with((List<? extends ArgumentLoader.Factory>) arrayList);
            }
            throw new IllegalArgumentException("Size cannot be negative: " + i3);
        }
    }

    public MethodCall withThis() {
        return with(ArgumentLoader.ForThisReference.Factory.INSTANCE);
    }

    public MethodCall withOwnType() {
        return with(ArgumentLoader.ForInstrumentedType.Factory.INSTANCE);
    }

    public MethodCall withField(String... strArr) {
        return withField(FieldLocator.ForClassHierarchy.Factory.INSTANCE, strArr);
    }

    public MethodCall withField(FieldLocator.Factory factory, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String factory2 : strArr) {
            arrayList.add(new ArgumentLoader.ForField.Factory(factory2, factory));
        }
        return with((List<? extends ArgumentLoader.Factory>) arrayList);
    }

    public MethodCall withMethodCall(MethodCall methodCall) {
        return with(new ArgumentLoader.ForMethodCall.Factory(methodCall));
    }

    public MethodCall with(StackManipulation stackManipulation, Type type) {
        return with(stackManipulation, (TypeDefinition) TypeDefinition.Sort.describe(type));
    }

    public MethodCall with(StackManipulation stackManipulation, TypeDefinition typeDefinition) {
        return with(new ArgumentLoader.ForStackManipulation(stackManipulation, typeDefinition));
    }

    public MethodCall with(ArgumentLoader.Factory... factoryArr) {
        return with((List<? extends ArgumentLoader.Factory>) Arrays.asList(factoryArr));
    }

    public MethodCall with(List<? extends ArgumentLoader.Factory> list) {
        return new MethodCall(this.methodLocator, this.targetHandler, CompoundList.of(this.argumentLoaders, (List<ArgumentLoader.Factory>) list), this.methodInvoker, this.terminationHandler, this.assigner, this.typing);
    }

    public FieldSetting setsField(Field field) {
        return setsField((FieldDescription) new FieldDescription.ForLoadedField(field));
    }

    public FieldSetting setsField(FieldDescription fieldDescription) {
        return new FieldSetting(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, new TerminationHandler.FieldSetting.Explicit(fieldDescription), this.assigner, this.typing));
    }

    public FieldSetting setsField(ElementMatcher<? super FieldDescription> elementMatcher) {
        return new FieldSetting(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, new TerminationHandler.FieldSetting.Implicit(elementMatcher), this.assigner, this.typing));
    }

    public Implementation.Composable withAssigner(Assigner assigner2, Assigner.Typing typing2) {
        return new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, this.terminationHandler, assigner2, typing2);
    }

    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, TerminationHandler.Simple.DROPPING, this.assigner, this.typing), implementation);
    }

    public Implementation.Composable andThen(Implementation.Composable composable) {
        return new Implementation.Compound.Composable((Implementation) new MethodCall(this.methodLocator, this.targetHandler, this.argumentLoaders, this.methodInvoker, TerminationHandler.Simple.DROPPING, this.assigner, this.typing), composable);
    }

    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        for (ArgumentLoader.Factory prepare : this.argumentLoaders) {
            instrumentedType = prepare.prepare(instrumentedType);
        }
        return this.targetHandler.prepare(instrumentedType);
    }

    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(target, this.terminationHandler.make(target.getInstrumentedType()));
    }

    public interface MethodLocator {

        public interface Factory {
            MethodLocator make(TypeDescription typeDescription);
        }

        public enum ForInstrumentedMethod implements MethodLocator, Factory {
            INSTANCE;

            public MethodLocator make(TypeDescription typeDescription) {
                return this;
            }

            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                return methodDescription;
            }
        }

        MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription);

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForExplicitMethod implements MethodLocator, Factory {
            private final MethodDescription methodDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForExplicitMethod) obj).methodDescription);
            }

            public int hashCode() {
                return 527 + this.methodDescription.hashCode();
            }

            public MethodLocator make(TypeDescription typeDescription) {
                return this;
            }

            protected ForExplicitMethod(MethodDescription methodDescription2) {
                this.methodDescription = methodDescription2;
            }

            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription2) {
                return this.methodDescription;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForElementMatcher implements MethodLocator {
            private final TypeDescription instrumentedType;
            private final ElementMatcher<? super MethodDescription> matcher;
            private final MethodGraph.Compiler methodGraphCompiler;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForElementMatcher forElementMatcher = (ForElementMatcher) obj;
                return this.instrumentedType.equals(forElementMatcher.instrumentedType) && this.matcher.equals(forElementMatcher.matcher) && this.methodGraphCompiler.equals(forElementMatcher.methodGraphCompiler);
            }

            public int hashCode() {
                return ((((527 + this.instrumentedType.hashCode()) * 31) + this.matcher.hashCode()) * 31) + this.methodGraphCompiler.hashCode();
            }

            protected ForElementMatcher(TypeDescription typeDescription, ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
                this.instrumentedType = typeDescription;
                this.matcher = elementMatcher;
                this.methodGraphCompiler = compiler;
            }

            public MethodDescription resolve(TypeDescription typeDescription, MethodDescription methodDescription) {
                List of = CompoundList.of(this.instrumentedType.getSuperClass().getDeclaredMethods().filter(ElementMatchers.isConstructor().and(this.matcher)), (U) this.methodGraphCompiler.compile(typeDescription, this.instrumentedType).listNodes().asMethodList().filter(this.matcher));
                if (of.size() == 1) {
                    return (MethodDescription) of.get(0);
                }
                throw new IllegalStateException(this.instrumentedType + " does not define exactly one virtual method or constructor for " + this.matcher);
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Factory implements Factory {
                private final ElementMatcher<? super MethodDescription> matcher;
                private final MethodGraph.Compiler methodGraphCompiler;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    return this.matcher.equals(factory.matcher) && this.methodGraphCompiler.equals(factory.methodGraphCompiler);
                }

                public int hashCode() {
                    return ((527 + this.matcher.hashCode()) * 31) + this.methodGraphCompiler.hashCode();
                }

                public Factory(ElementMatcher<? super MethodDescription> elementMatcher, MethodGraph.Compiler compiler) {
                    this.matcher = elementMatcher;
                    this.methodGraphCompiler = compiler;
                }

                public MethodLocator make(TypeDescription typeDescription) {
                    return new ForElementMatcher(typeDescription, this.matcher, this.methodGraphCompiler);
                }
            }
        }
    }

    public interface ArgumentLoader {

        public interface ArgumentProvider {
            List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2);
        }

        public interface Factory extends InstrumentedType.Prepareable {
            ArgumentProvider make(Implementation.Target target);
        }

        StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing);

        public enum ForNullConstant implements ArgumentLoader, ArgumentProvider, Factory {
            INSTANCE;

            public ArgumentProvider make(Implementation.Target target) {
                return this;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                if (!parameterDescription.getType().isPrimitive()) {
                    return NullConstant.INSTANCE;
                }
                throw new IllegalStateException("Cannot assign null to " + parameterDescription);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForThisReference implements ArgumentLoader, ArgumentProvider {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForThisReference) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            public ForThisReference(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                if (!methodDescription.isStatic()) {
                    return Collections.singletonList(this);
                }
                throw new IllegalStateException(methodDescription + " is static and cannot supply an invoker instance");
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.loadThis(), assigner.assign(this.instrumentedType.asGenericType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + this.instrumentedType + " to " + parameterDescription);
            }

            public enum Factory implements Factory {
                INSTANCE;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public ArgumentProvider make(Implementation.Target target) {
                    return new ForThisReference(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForInstrumentedType implements ArgumentLoader, ArgumentProvider {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForInstrumentedType) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            public ForInstrumentedType(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(ClassConstant.of(this.instrumentedType), assigner.assign(TypeDescription.Generic.OfNonGenericType.ForLoadedType.CLASS, parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign Class value to " + parameterDescription);
            }

            public enum Factory implements Factory {
                INSTANCE;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public ArgumentProvider make(Implementation.Target target) {
                    return new ForInstrumentedType(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodParameter implements ArgumentLoader {
            private final int index;
            private final MethodDescription instrumentedMethod;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForMethodParameter forMethodParameter = (ForMethodParameter) obj;
                return this.index == forMethodParameter.index && this.instrumentedMethod.equals(forMethodParameter.instrumentedMethod);
            }

            public int hashCode() {
                return ((527 + this.index) * 31) + this.instrumentedMethod.hashCode();
            }

            public ForMethodParameter(int i, MethodDescription methodDescription) {
                this.index = i;
                this.instrumentedMethod = methodDescription;
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                ParameterDescription parameterDescription2 = (ParameterDescription) this.instrumentedMethod.getParameters().get(this.index);
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription2), assigner.assign(parameterDescription2.getType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + parameterDescription2 + " to " + parameterDescription + " for " + this.instrumentedMethod);
            }

            protected enum OfInstrumentedMethod implements Factory, ArgumentProvider {
                INSTANCE;

                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                    Iterator it = methodDescription.getParameters().iterator();
                    while (it.hasNext()) {
                        arrayList.add(new ForMethodParameter(((ParameterDescription) it.next()).getIndex(), methodDescription));
                    }
                    return arrayList;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory, ArgumentProvider {
                private final int index;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((Factory) obj).index;
                }

                public int hashCode() {
                    return 527 + this.index;
                }

                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public Factory(int i) {
                    this.index = i;
                }

                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (this.index < methodDescription.getParameters().size()) {
                        return Collections.singletonList(new ForMethodParameter(this.index, methodDescription));
                    }
                    throw new IllegalStateException(methodDescription + " does not have a parameter with index " + this.index);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodParameterArray implements ArgumentLoader {
            private final ParameterList<?> parameters;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.parameters.equals(((ForMethodParameterArray) obj).parameters);
            }

            public int hashCode() {
                return 527 + this.parameters.hashCode();
            }

            public ForMethodParameterArray(ParameterList<?> parameterList) {
                this.parameters = parameterList;
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                TypeDescription.Generic generic;
                if (parameterDescription.getType().represents(Object.class)) {
                    generic = TypeDescription.Generic.OBJECT;
                } else if (parameterDescription.getType().isArray()) {
                    generic = parameterDescription.getType().getComponentType();
                } else {
                    throw new IllegalStateException("Cannot set method parameter array for non-array type: " + parameterDescription);
                }
                ArrayList arrayList = new ArrayList(this.parameters.size());
                Iterator it = this.parameters.iterator();
                while (it.hasNext()) {
                    ParameterDescription parameterDescription2 = (ParameterDescription) it.next();
                    StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(parameterDescription2), assigner.assign(parameterDescription2.getType(), generic, typing));
                    if (compound.isValid()) {
                        arrayList.add(compound);
                    } else {
                        throw new IllegalStateException("Cannot assign " + parameterDescription2 + " to " + generic);
                    }
                }
                return new StackManipulation.Compound(ArrayFactory.forType(generic).withValues(arrayList));
            }

            public enum ForInstrumentedMethod implements Factory, ArgumentProvider {
                INSTANCE;

                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    return Collections.singletonList(new ForMethodParameterArray(methodDescription.getParameters()));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodParameterArrayElement implements ArgumentLoader {
            private final int index;
            private final ParameterDescription parameterDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForMethodParameterArrayElement forMethodParameterArrayElement = (ForMethodParameterArrayElement) obj;
                return this.index == forMethodParameterArrayElement.index && this.parameterDescription.equals(forMethodParameterArrayElement.parameterDescription);
            }

            public int hashCode() {
                return ((527 + this.parameterDescription.hashCode()) * 31) + this.index;
            }

            public ForMethodParameterArrayElement(ParameterDescription parameterDescription2, int i) {
                this.parameterDescription = parameterDescription2;
                this.index = i;
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription2, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(MethodVariableAccess.load(this.parameterDescription), IntegerConstant.forValue(this.index), ArrayAccess.of(this.parameterDescription.getType().getComponentType()).load(), assigner.assign(this.parameterDescription.getType().getComponentType(), parameterDescription2.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + this.parameterDescription.getType().getComponentType() + " to " + parameterDescription2);
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class OfParameter implements Factory, ArgumentProvider {
                private final int arrayIndex;
                private final int index;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    OfParameter ofParameter = (OfParameter) obj;
                    return this.index == ofParameter.index && this.arrayIndex == ofParameter.arrayIndex;
                }

                public int hashCode() {
                    return ((527 + this.index) * 31) + this.arrayIndex;
                }

                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public OfParameter(int i, int i2) {
                    this.index = i;
                    this.arrayIndex = i2;
                }

                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (methodDescription.getParameters().size() <= this.index) {
                        throw new IllegalStateException(methodDescription + " does not declare a parameter with index " + this.index);
                    } else if (((ParameterDescription) methodDescription.getParameters().get(this.index)).getType().isArray()) {
                        return Collections.singletonList(new ForMethodParameterArrayElement((ParameterDescription) methodDescription.getParameters().get(this.index), this.arrayIndex));
                    } else {
                        throw new IllegalStateException("Cannot access an item from non-array parameter " + methodDescription.getParameters().get(this.index));
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class OfInvokedMethod implements Factory, ArgumentProvider {
                private final int index;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((OfInvokedMethod) obj).index;
                }

                public int hashCode() {
                    return 527 + this.index;
                }

                public ArgumentProvider make(Implementation.Target target) {
                    return this;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public OfInvokedMethod(int i) {
                    this.index = i;
                }

                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    if (methodDescription.getParameters().size() <= this.index) {
                        throw new IllegalStateException(methodDescription + " does not declare a parameter with index " + this.index);
                    } else if (((ParameterDescription) methodDescription.getParameters().get(this.index)).getType().isArray()) {
                        ArrayList arrayList = new ArrayList(methodDescription.getParameters().size());
                        for (int i = 0; i < methodDescription2.getParameters().size(); i = i + 1 + 1) {
                            arrayList.add(new ForMethodParameterArrayElement((ParameterDescription) methodDescription.getParameters().get(this.index), i));
                        }
                        return arrayList;
                    } else {
                        throw new IllegalStateException("Cannot access an item from non-array parameter " + methodDescription.getParameters().get(this.index));
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForInstance implements ArgumentLoader, ArgumentProvider {
            private final FieldDescription fieldDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForInstance) obj).fieldDescription);
            }

            public int hashCode() {
                return 527 + this.fieldDescription.hashCode();
            }

            public ForInstance(FieldDescription fieldDescription2) {
                this.fieldDescription = fieldDescription2;
            }

            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(FieldAccess.forField(this.fieldDescription).read(), assigner.assign(this.fieldDescription.getType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign " + this.fieldDescription.getType() + " to " + parameterDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private static final String FIELD_PREFIX = "methodCall";
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
                private final String name = ("methodCall$" + RandomString.make());
                private final Object value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value.equals(((Factory) obj).value);
                }

                public int hashCode() {
                    return 527 + this.value.hashCode();
                }

                public Factory(Object obj) {
                    this.value = obj;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withField(new FieldDescription.Token(this.name, 4105, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(this.value.getClass()))).withInitializer((LoadedTypeInitializer) new LoadedTypeInitializer.ForStaticField(this.name, this.value));
                }

                public ArgumentProvider make(Implementation.Target target) {
                    return new ForInstance((FieldDescription) ((FieldList) target.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named(this.name))).getOnly());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForField implements ArgumentLoader {
            private final FieldDescription fieldDescription;
            private final MethodDescription instrumentedMethod;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForField forField = (ForField) obj;
                return this.fieldDescription.equals(forField.fieldDescription) && this.instrumentedMethod.equals(forField.instrumentedMethod);
            }

            public int hashCode() {
                return ((527 + this.fieldDescription.hashCode()) * 31) + this.instrumentedMethod.hashCode();
            }

            public ForField(FieldDescription fieldDescription2, MethodDescription methodDescription) {
                this.fieldDescription = fieldDescription2;
                this.instrumentedMethod = methodDescription;
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation stackManipulation;
                if (this.fieldDescription.isStatic() || !this.instrumentedMethod.isStatic()) {
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    if (this.fieldDescription.isStatic()) {
                        stackManipulation = StackManipulation.Trivial.INSTANCE;
                    } else {
                        stackManipulation = MethodVariableAccess.loadThis();
                    }
                    stackManipulationArr[0] = stackManipulation;
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    stackManipulationArr[2] = assigner.assign(this.fieldDescription.getType(), parameterDescription.getType(), typing);
                    StackManipulation.Compound compound = new StackManipulation.Compound(stackManipulationArr);
                    if (compound.isValid()) {
                        return compound;
                    }
                    throw new IllegalStateException("Cannot assign " + this.fieldDescription + " to " + parameterDescription);
                }
                throw new IllegalStateException("Cannot access non-static " + this.fieldDescription + " from " + this.instrumentedMethod);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class ArgumentProvider implements ArgumentProvider {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ArgumentProvider) obj).fieldDescription);
                }

                public int hashCode() {
                    return 527 + this.fieldDescription.hashCode();
                }

                protected ArgumentProvider(FieldDescription fieldDescription2) {
                    this.fieldDescription = fieldDescription2;
                }

                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    return Collections.singletonList(new ForField(this.fieldDescription, methodDescription));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private final FieldLocator.Factory fieldLocatorFactory;
                private final String name;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    return this.name.equals(factory.name) && this.fieldLocatorFactory.equals(factory.fieldLocatorFactory);
                }

                public int hashCode() {
                    return ((527 + this.name.hashCode()) * 31) + this.fieldLocatorFactory.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public Factory(String str, FieldLocator.Factory factory) {
                    this.name = str;
                    this.fieldLocatorFactory = factory;
                }

                public ArgumentProvider make(Implementation.Target target) {
                    FieldLocator.Resolution locate = this.fieldLocatorFactory.make(target.getInstrumentedType()).locate(this.name);
                    if (locate.isResolved()) {
                        return new ArgumentProvider(locate.getField());
                    }
                    throw new IllegalStateException("Could not locate field '" + this.name + "' on " + target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodCall implements ArgumentLoader {
            private final Appender appender;
            private final MethodDescription instrumentedMethod;
            private final MethodDescription methodDescription;
            private final TargetHandler.Resolved targetHandler;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForMethodCall forMethodCall = (ForMethodCall) obj;
                return this.appender.equals(forMethodCall.appender) && this.methodDescription.equals(forMethodCall.methodDescription) && this.instrumentedMethod.equals(forMethodCall.instrumentedMethod) && this.targetHandler.equals(forMethodCall.targetHandler);
            }

            public int hashCode() {
                return ((((((527 + this.appender.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.instrumentedMethod.hashCode()) * 31) + this.targetHandler.hashCode();
            }

            public ForMethodCall(Appender appender2, MethodDescription methodDescription2, MethodDescription methodDescription3, TargetHandler.Resolved resolved) {
                this.appender = appender2;
                this.methodDescription = methodDescription2;
                this.instrumentedMethod = methodDescription3;
                this.targetHandler = resolved;
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation.Compound compound = new StackManipulation.Compound(this.appender.toStackManipulation(this.instrumentedMethod, this.methodDescription, this.targetHandler), assigner.assign(this.methodDescription.getReturnType(), parameterDescription.getType(), typing));
                if (compound.isValid()) {
                    return compound;
                }
                throw new IllegalStateException("Cannot assign return type of " + this.methodDescription + " to " + parameterDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class ArgumentProvider implements ArgumentProvider {
                private final Appender appender;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.appender.equals(((ArgumentProvider) obj).appender);
                }

                public int hashCode() {
                    return 527 + this.appender.hashCode();
                }

                protected ArgumentProvider(Appender appender2) {
                    this.appender = appender2;
                }

                public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                    TargetHandler.Resolved resolve = this.appender.targetHandler.resolve(methodDescription);
                    Appender appender2 = this.appender;
                    return Collections.singletonList(new ForMethodCall(appender2, appender2.toInvokedMethod(methodDescription, resolve), methodDescription, resolve));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private final MethodCall methodCall;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodCall.equals(((Factory) obj).methodCall);
                }

                public int hashCode() {
                    return 527 + this.methodCall.hashCode();
                }

                public Factory(MethodCall methodCall2) {
                    this.methodCall = methodCall2;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return this.methodCall.prepare(instrumentedType);
                }

                public ArgumentProvider make(Implementation.Target target) {
                    MethodCall methodCall2 = this.methodCall;
                    methodCall2.getClass();
                    return new ArgumentProvider(new Appender(target, TerminationHandler.Simple.IGNORING));
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForStackManipulation implements ArgumentLoader, ArgumentProvider, Factory {
            private final StackManipulation stackManipulation;
            private final TypeDefinition typeDefinition;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForStackManipulation forStackManipulation = (ForStackManipulation) obj;
                return this.stackManipulation.equals(forStackManipulation.stackManipulation) && this.typeDefinition.equals(forStackManipulation.typeDefinition);
            }

            public int hashCode() {
                return ((527 + this.stackManipulation.hashCode()) * 31) + this.typeDefinition.hashCode();
            }

            public ArgumentProvider make(Implementation.Target target) {
                return this;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            public ForStackManipulation(StackManipulation stackManipulation2, Type type) {
                this(stackManipulation2, (TypeDefinition) TypeDefinition.Sort.describe(type));
            }

            public ForStackManipulation(StackManipulation stackManipulation2, TypeDefinition typeDefinition2) {
                this.stackManipulation = stackManipulation2;
                this.typeDefinition = typeDefinition2;
            }

            public static Factory of(Object obj) {
                if (obj == null) {
                    return ForNullConstant.INSTANCE;
                }
                if (obj instanceof String) {
                    return new ForStackManipulation((StackManipulation) new TextConstant((String) obj), (Type) String.class);
                }
                if (obj instanceof Boolean) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Boolean) obj).booleanValue()), (Type) Boolean.TYPE);
                }
                if (obj instanceof Byte) {
                    return new ForStackManipulation(IntegerConstant.forValue((int) ((Byte) obj).byteValue()), (Type) Byte.TYPE);
                }
                if (obj instanceof Short) {
                    return new ForStackManipulation(IntegerConstant.forValue((int) ((Short) obj).shortValue()), (Type) Short.TYPE);
                }
                if (obj instanceof Character) {
                    return new ForStackManipulation(IntegerConstant.forValue((int) ((Character) obj).charValue()), (Type) Character.TYPE);
                }
                if (obj instanceof Integer) {
                    return new ForStackManipulation(IntegerConstant.forValue(((Integer) obj).intValue()), (Type) Integer.TYPE);
                }
                if (obj instanceof Long) {
                    return new ForStackManipulation(LongConstant.forValue(((Long) obj).longValue()), (Type) Long.TYPE);
                }
                if (obj instanceof Float) {
                    return new ForStackManipulation(FloatConstant.forValue(((Float) obj).floatValue()), (Type) Float.TYPE);
                }
                if (obj instanceof Double) {
                    return new ForStackManipulation(DoubleConstant.forValue(((Double) obj).doubleValue()), (Type) Double.TYPE);
                }
                if (obj instanceof Class) {
                    return new ForStackManipulation(ClassConstant.of(TypeDescription.ForLoadedType.of((Class) obj)), (Type) Class.class);
                }
                if (JavaType.METHOD_HANDLE.isInstance(obj)) {
                    return new ForStackManipulation((StackManipulation) new JavaConstantValue(JavaConstant.MethodHandle.ofLoaded(obj)), (TypeDefinition) JavaType.METHOD_HANDLE.getTypeStub());
                }
                if (JavaType.METHOD_TYPE.isInstance(obj)) {
                    return new ForStackManipulation((StackManipulation) new JavaConstantValue(JavaConstant.MethodType.ofLoaded(obj)), (TypeDefinition) JavaType.METHOD_TYPE.getTypeStub());
                }
                if (!(obj instanceof Enum)) {
                    return new ForInstance.Factory(obj);
                }
                EnumerationDescription.ForLoadedEnumeration forLoadedEnumeration = new EnumerationDescription.ForLoadedEnumeration((Enum) obj);
                return new ForStackManipulation(FieldAccess.forEnumeration(forLoadedEnumeration), (TypeDefinition) forLoadedEnumeration.getEnumerationType());
            }

            public List<ArgumentLoader> resolve(MethodDescription methodDescription, MethodDescription methodDescription2) {
                return Collections.singletonList(this);
            }

            public StackManipulation toStackManipulation(ParameterDescription parameterDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation assign = assigner.assign(this.typeDefinition.asGenericType(), parameterDescription.getType(), typing);
                if (assign.isValid()) {
                    return new StackManipulation.Compound(this.stackManipulation, assign);
                }
                throw new IllegalStateException("Cannot assign " + parameterDescription + " to " + this.typeDefinition);
            }
        }
    }

    protected interface TargetHandler {

        public interface Factory extends InstrumentedType.Prepareable {
            TargetHandler make(Implementation.Target target);
        }

        public interface Resolved {
            TypeDescription getTypeDescription();

            StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing);
        }

        Resolved resolve(MethodDescription methodDescription);

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForSelfOrStaticInvocation implements TargetHandler {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForSelfOrStaticInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            protected ForSelfOrStaticInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public Resolved resolve(MethodDescription methodDescription) {
                return new Resolved(this.instrumentedType, methodDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Resolved implements Resolved {
                private final MethodDescription instrumentedMethod;
                private final TypeDescription instrumentedType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Resolved resolved = (Resolved) obj;
                    return this.instrumentedType.equals(resolved.instrumentedType) && this.instrumentedMethod.equals(resolved.instrumentedMethod);
                }

                public int hashCode() {
                    return ((527 + this.instrumentedType.hashCode()) * 31) + this.instrumentedMethod.hashCode();
                }

                protected Resolved(TypeDescription typeDescription, MethodDescription methodDescription) {
                    this.instrumentedType = typeDescription;
                    this.instrumentedMethod = methodDescription;
                }

                public TypeDescription getTypeDescription() {
                    return this.instrumentedType;
                }

                public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    StackManipulation stackManipulation;
                    if (this.instrumentedMethod.isStatic() && !methodDescription.isStatic() && !methodDescription.isConstructor()) {
                        throw new IllegalStateException("Cannot invoke " + methodDescription + " from " + this.instrumentedMethod);
                    } else if (!methodDescription.isConstructor() || (this.instrumentedMethod.isConstructor() && (this.instrumentedType.equals(methodDescription.getDeclaringType().asErasure()) || this.instrumentedType.getSuperClass().asErasure().equals(methodDescription.getDeclaringType().asErasure())))) {
                        StackManipulation[] stackManipulationArr = new StackManipulation[2];
                        if (methodDescription.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = MethodVariableAccess.loadThis();
                        }
                        stackManipulationArr[0] = stackManipulation;
                        stackManipulationArr[1] = methodDescription.isConstructor() ? Duplication.SINGLE : StackManipulation.Trivial.INSTANCE;
                        return new StackManipulation.Compound(stackManipulationArr);
                    } else {
                        throw new IllegalStateException("Cannot invoke " + methodDescription + " from " + this.instrumentedMethod + " in " + this.instrumentedType);
                    }
                }
            }

            protected enum Factory implements Factory {
                INSTANCE;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public TargetHandler make(Implementation.Target target) {
                    return new ForSelfOrStaticInvocation(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForConstructingInvocation implements TargetHandler, Resolved {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForConstructingInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            public Resolved resolve(MethodDescription methodDescription) {
                return this;
            }

            protected ForConstructingInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public TypeDescription getTypeDescription() {
                return this.instrumentedType;
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                return new StackManipulation.Compound(TypeCreation.of(methodDescription.getDeclaringType().asErasure()), Duplication.SINGLE);
            }

            enum Factory implements Factory {
                INSTANCE;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public TargetHandler make(Implementation.Target target) {
                    return new ForConstructingInvocation(target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForValue implements TargetHandler, Resolved {
            private final FieldDescription.InDefinedShape fieldDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForValue) obj).fieldDescription);
            }

            public int hashCode() {
                return 527 + this.fieldDescription.hashCode();
            }

            public Resolved resolve(MethodDescription methodDescription) {
                return this;
            }

            protected ForValue(FieldDescription.InDefinedShape inDefinedShape) {
                this.fieldDescription = inDefinedShape;
            }

            public TypeDescription getTypeDescription() {
                return this.fieldDescription.getType().asErasure();
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation assign = assigner.assign(this.fieldDescription.getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                if (assign.isValid()) {
                    return new StackManipulation.Compound(FieldAccess.forField(this.fieldDescription).read(), assign);
                }
                throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.fieldDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private static final String FIELD_PREFIX = "invocationTarget";
                private final TypeDescription.Generic fieldType;
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
                private final String name = ("invocationTarget$" + RandomString.make());
                private final Object target;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Factory factory = (Factory) obj;
                    return this.target.equals(factory.target) && this.fieldType.equals(factory.fieldType);
                }

                public int hashCode() {
                    return ((527 + this.target.hashCode()) * 31) + this.fieldType.hashCode();
                }

                protected Factory(Object obj, TypeDescription.Generic generic) {
                    this.target = obj;
                    this.fieldType = generic;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withField(new FieldDescription.Token(this.name, 4169, this.fieldType)).withInitializer((LoadedTypeInitializer) new LoadedTypeInitializer.ForStaticField(this.name, this.target));
                }

                public TargetHandler make(Implementation.Target target2) {
                    return new ForValue((FieldDescription.InDefinedShape) ((FieldList) target2.getInstrumentedType().getDeclaredFields().filter(ElementMatchers.named(this.name))).getOnly());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForField implements TargetHandler, Resolved {
            private final FieldDescription fieldDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForField) obj).fieldDescription);
            }

            public int hashCode() {
                return 527 + this.fieldDescription.hashCode();
            }

            public Resolved resolve(MethodDescription methodDescription) {
                return this;
            }

            protected ForField(FieldDescription fieldDescription2) {
                this.fieldDescription = fieldDescription2;
            }

            public TypeDescription getTypeDescription() {
                return this.fieldDescription.getType().asErasure();
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation stackManipulation;
                if (methodDescription.isInvokableOn(this.fieldDescription.getType().asErasure())) {
                    StackManipulation assign = assigner.assign(this.fieldDescription.getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                    if (assign.isValid()) {
                        StackManipulation[] stackManipulationArr = new StackManipulation[3];
                        if (methodDescription.isStatic() || this.fieldDescription.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = MethodVariableAccess.loadThis();
                        }
                        stackManipulationArr[0] = stackManipulation;
                        stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                        stackManipulationArr[2] = assign;
                        return new StackManipulation.Compound(stackManipulationArr);
                    }
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.fieldDescription);
                }
                throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.fieldDescription);
            }

            protected interface Location {
                FieldDescription resolve(TypeDescription typeDescription);

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForImplicitField implements Location {
                    private final FieldLocator.Factory fieldLocatorFactory;
                    private final String name;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForImplicitField forImplicitField = (ForImplicitField) obj;
                        return this.name.equals(forImplicitField.name) && this.fieldLocatorFactory.equals(forImplicitField.fieldLocatorFactory);
                    }

                    public int hashCode() {
                        return ((527 + this.name.hashCode()) * 31) + this.fieldLocatorFactory.hashCode();
                    }

                    protected ForImplicitField(String str, FieldLocator.Factory factory) {
                        this.name = str;
                        this.fieldLocatorFactory = factory;
                    }

                    public FieldDescription resolve(TypeDescription typeDescription) {
                        FieldLocator.Resolution locate = this.fieldLocatorFactory.make(typeDescription).locate(this.name);
                        if (locate.isResolved()) {
                            return locate.getField();
                        }
                        throw new IllegalStateException("Could not locate field name " + this.name + " on " + typeDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForExplicitField implements Location {
                    private final FieldDescription fieldDescription;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForExplicitField) obj).fieldDescription);
                    }

                    public int hashCode() {
                        return 527 + this.fieldDescription.hashCode();
                    }

                    protected ForExplicitField(FieldDescription fieldDescription2) {
                        this.fieldDescription = fieldDescription2;
                    }

                    public FieldDescription resolve(TypeDescription typeDescription) {
                        if (this.fieldDescription.isStatic() || typeDescription.isAssignableTo(this.fieldDescription.getType().asErasure())) {
                            return this.fieldDescription;
                        }
                        throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private final Location location;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.location.equals(((Factory) obj).location);
                }

                public int hashCode() {
                    return 527 + this.location.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected Factory(Location location2) {
                    this.location = location2;
                }

                public TargetHandler make(Implementation.Target target) {
                    FieldDescription resolve = this.location.resolve(target.getInstrumentedType());
                    if (resolve.isStatic() || target.getInstrumentedType().isAssignableTo(resolve.getDeclaringType().asErasure())) {
                        return new ForField(resolve);
                    }
                    throw new IllegalStateException("Cannot access " + resolve + " from " + target.getInstrumentedType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodParameter implements TargetHandler, Factory {
            private final int index;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.index == ((ForMethodParameter) obj).index;
            }

            public int hashCode() {
                return 527 + this.index;
            }

            public TargetHandler make(Implementation.Target target) {
                return this;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected ForMethodParameter(int i) {
                this.index = i;
            }

            public Resolved resolve(MethodDescription methodDescription) {
                if (methodDescription.getParameters().size() >= this.index) {
                    return new Resolved((ParameterDescription) methodDescription.getParameters().get(this.index));
                }
                throw new IllegalArgumentException(methodDescription + " does not have a parameter with index " + this.index);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Resolved implements Resolved {
                private final ParameterDescription parameterDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.parameterDescription.equals(((Resolved) obj).parameterDescription);
                }

                public int hashCode() {
                    return 527 + this.parameterDescription.hashCode();
                }

                protected Resolved(ParameterDescription parameterDescription2) {
                    this.parameterDescription = parameterDescription2;
                }

                public TypeDescription getTypeDescription() {
                    return this.parameterDescription.getType().asErasure();
                }

                public StackManipulation toStackManipulation(MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    StackManipulation assign = assigner.assign(this.parameterDescription.getType(), methodDescription.getDeclaringType().asGenericType(), typing);
                    if (assign.isValid()) {
                        return new StackManipulation.Compound(MethodVariableAccess.load(this.parameterDescription), assign);
                    }
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.parameterDescription.getType());
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodCall implements TargetHandler {
            private final Appender appender;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.appender.equals(((ForMethodCall) obj).appender);
            }

            public int hashCode() {
                return 527 + this.appender.hashCode();
            }

            protected ForMethodCall(Appender appender2) {
                this.appender = appender2;
            }

            public Resolved resolve(MethodDescription methodDescription) {
                Resolved resolve = this.appender.targetHandler.resolve(methodDescription);
                Appender appender2 = this.appender;
                return new Resolved(appender2, appender2.toInvokedMethod(methodDescription, resolve), methodDescription, resolve);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Resolved implements Resolved {
                private final Appender appender;
                private final MethodDescription instrumentedMethod;
                private final MethodDescription methodDescription;
                private final Resolved targetHandler;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Resolved resolved = (Resolved) obj;
                    return this.appender.equals(resolved.appender) && this.methodDescription.equals(resolved.methodDescription) && this.instrumentedMethod.equals(resolved.instrumentedMethod) && this.targetHandler.equals(resolved.targetHandler);
                }

                public int hashCode() {
                    return ((((((527 + this.appender.hashCode()) * 31) + this.methodDescription.hashCode()) * 31) + this.instrumentedMethod.hashCode()) * 31) + this.targetHandler.hashCode();
                }

                protected Resolved(Appender appender2, MethodDescription methodDescription2, MethodDescription methodDescription3, Resolved resolved) {
                    this.appender = appender2;
                    this.methodDescription = methodDescription2;
                    this.instrumentedMethod = methodDescription3;
                    this.targetHandler = resolved;
                }

                public TypeDescription getTypeDescription() {
                    return this.methodDescription.getReturnType().asErasure();
                }

                public StackManipulation toStackManipulation(MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                    StackManipulation assign = assigner.assign(this.methodDescription.getReturnType(), methodDescription2.getDeclaringType().asGenericType(), typing);
                    if (assign.isValid()) {
                        return new StackManipulation.Compound(this.appender.toStackManipulation(this.instrumentedMethod, this.methodDescription, this.targetHandler), assign);
                    }
                    throw new IllegalStateException("Cannot invoke " + methodDescription2 + " on " + this.methodDescription.getReturnType());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private final MethodCall methodCall;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodCall.equals(((Factory) obj).methodCall);
                }

                public int hashCode() {
                    return 527 + this.methodCall.hashCode();
                }

                public Factory(MethodCall methodCall2) {
                    this.methodCall = methodCall2;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return this.methodCall.prepare(instrumentedType);
                }

                public TargetHandler make(Implementation.Target target) {
                    MethodCall methodCall2 = this.methodCall;
                    methodCall2.getClass();
                    return new ForMethodCall(new Appender(target, TerminationHandler.Simple.IGNORING));
                }
            }
        }
    }

    protected interface MethodInvoker {

        public interface Factory {
            MethodInvoker make(TypeDescription typeDescription);
        }

        StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target);

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForContextualInvocation implements MethodInvoker {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForContextualInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            protected ForContextualInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (methodDescription.isVirtual() && !methodDescription.isInvokableOn(this.instrumentedType)) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.instrumentedType);
                } else if (methodDescription.isVirtual()) {
                    return MethodInvocation.invoke(methodDescription).virtual(this.instrumentedType);
                } else {
                    return MethodInvocation.invoke(methodDescription);
                }
            }

            enum Factory implements Factory {
                INSTANCE;

                public MethodInvoker make(TypeDescription typeDescription) {
                    return new ForContextualInvocation(typeDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForVirtualInvocation implements MethodInvoker {
            private final TypeDescription typeDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForVirtualInvocation) obj).typeDescription);
            }

            public int hashCode() {
                return 527 + this.typeDescription.hashCode();
            }

            protected ForVirtualInvocation(TypeDescription typeDescription2) {
                this.typeDescription = typeDescription2;
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (!methodDescription.isVirtual()) {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " virtually");
                } else if (methodDescription.isInvokableOn(this.typeDescription)) {
                    return MethodInvocation.invoke(methodDescription).virtual(this.typeDescription);
                } else {
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.typeDescription);
                }
            }

            protected enum WithImplicitType implements MethodInvoker, Factory {
                INSTANCE;

                public MethodInvoker make(TypeDescription typeDescription) {
                    return this;
                }

                public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                    if (methodDescription.isVirtual()) {
                        return MethodInvocation.invoke(methodDescription);
                    }
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " virtually");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Factory implements Factory {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((Factory) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                protected Factory(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public MethodInvoker make(TypeDescription typeDescription2) {
                    if (this.typeDescription.asErasure().isAccessibleTo(typeDescription2)) {
                        return new ForVirtualInvocation(this.typeDescription);
                    }
                    throw new IllegalStateException(this.typeDescription + " is not accessible to " + typeDescription2);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForSuperMethodInvocation implements MethodInvoker {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForSuperMethodInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            protected ForSuperMethodInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (methodDescription.isInvokableOn(target.getOriginType().asErasure())) {
                    Implementation.SpecialMethodInvocation invokeDominant = target.invokeDominant(methodDescription.asSignatureToken());
                    if (invokeDominant.isValid()) {
                        return invokeDominant;
                    }
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " as a super method");
                }
                throw new IllegalStateException("Cannot invoke " + methodDescription + " as super method of " + this.instrumentedType);
            }

            enum Factory implements Factory {
                INSTANCE;

                public MethodInvoker make(TypeDescription typeDescription) {
                    if (typeDescription.getSuperClass() != null) {
                        return new ForSuperMethodInvocation(typeDescription);
                    }
                    throw new IllegalStateException("Cannot invoke super method for " + typeDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForDefaultMethodInvocation implements MethodInvoker {
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.instrumentedType.equals(((ForDefaultMethodInvocation) obj).instrumentedType);
            }

            public int hashCode() {
                return 527 + this.instrumentedType.hashCode();
            }

            protected ForDefaultMethodInvocation(TypeDescription typeDescription) {
                this.instrumentedType = typeDescription;
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, Implementation.Target target) {
                if (methodDescription.isInvokableOn(this.instrumentedType)) {
                    Implementation.SpecialMethodInvocation invokeDefault = target.invokeDefault(methodDescription.asSignatureToken(), methodDescription.getDeclaringType().asErasure());
                    if (invokeDefault.isValid()) {
                        return invokeDefault;
                    }
                    throw new IllegalStateException("Cannot invoke " + methodDescription + " on " + this.instrumentedType);
                }
                throw new IllegalStateException("Cannot invoke " + methodDescription + " as default method of " + this.instrumentedType);
            }

            enum Factory implements Factory {
                INSTANCE;

                public MethodInvoker make(TypeDescription typeDescription) {
                    return new ForDefaultMethodInvocation(typeDescription);
                }
            }
        }
    }

    protected interface TerminationHandler {

        public interface Factory {
            TerminationHandler make(TypeDescription typeDescription);
        }

        StackManipulation prepare();

        StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing);

        public enum Simple implements TerminationHandler, Factory {
            RETURNING {
                public StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                    TypeDescription.Generic generic;
                    if (methodDescription.isConstructor()) {
                        generic = methodDescription.getDeclaringType().asGenericType();
                    } else {
                        generic = methodDescription.getReturnType();
                    }
                    StackManipulation assign = assigner.assign(generic, methodDescription2.getReturnType(), typing);
                    if (assign.isValid()) {
                        return new StackManipulation.Compound(assign, MethodReturn.of(methodDescription2.getReturnType()));
                    }
                    throw new IllegalStateException("Cannot return " + methodDescription.getReturnType() + " from " + methodDescription2);
                }
            },
            DROPPING {
                public StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                    TypeDefinition typeDefinition;
                    if (methodDescription.isConstructor()) {
                        typeDefinition = methodDescription.getDeclaringType();
                    } else {
                        typeDefinition = methodDescription.getReturnType();
                    }
                    return Removal.of(typeDefinition);
                }
            },
            IGNORING {
                public StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                    return StackManipulation.Trivial.INSTANCE;
                }
            };

            public TerminationHandler make(TypeDescription typeDescription) {
                return this;
            }

            public StackManipulation prepare() {
                return StackManipulation.Trivial.INSTANCE;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class FieldSetting implements TerminationHandler {
            private final FieldDescription fieldDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((FieldSetting) obj).fieldDescription);
            }

            public int hashCode() {
                return 527 + this.fieldDescription.hashCode();
            }

            protected FieldSetting(FieldDescription fieldDescription2) {
                this.fieldDescription = fieldDescription2;
            }

            public StackManipulation prepare() {
                if (this.fieldDescription.isStatic()) {
                    return StackManipulation.Trivial.INSTANCE;
                }
                return MethodVariableAccess.loadThis();
            }

            public StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, Assigner assigner, Assigner.Typing typing) {
                StackManipulation assign = assigner.assign(methodDescription.getReturnType(), this.fieldDescription.getType(), typing);
                if (assign.isValid()) {
                    return new StackManipulation.Compound(assign, FieldAccess.forField(this.fieldDescription).write());
                }
                throw new IllegalStateException("Cannot assign result of " + methodDescription + " to " + this.fieldDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Explicit implements Factory {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Explicit) obj).fieldDescription);
                }

                public int hashCode() {
                    return 527 + this.fieldDescription.hashCode();
                }

                protected Explicit(FieldDescription fieldDescription2) {
                    this.fieldDescription = fieldDescription2;
                }

                public TerminationHandler make(TypeDescription typeDescription) {
                    if (!this.fieldDescription.isStatic() && !typeDescription.isAssignableTo(this.fieldDescription.getDeclaringType().asErasure())) {
                        throw new IllegalStateException("Cannot set " + this.fieldDescription + " from " + typeDescription);
                    } else if (this.fieldDescription.isAccessibleTo(typeDescription)) {
                        return new FieldSetting(this.fieldDescription);
                    } else {
                        throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Implicit implements Factory {
                private final ElementMatcher<? super FieldDescription> matcher;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.matcher.equals(((Implicit) obj).matcher);
                }

                public int hashCode() {
                    return 527 + this.matcher.hashCode();
                }

                protected Implicit(ElementMatcher<? super FieldDescription> elementMatcher) {
                    this.matcher = elementMatcher;
                }

                public TerminationHandler make(TypeDescription typeDescription) {
                    TypeDefinition typeDefinition = typeDescription;
                    do {
                        FieldList fieldList = (FieldList) typeDefinition.getDeclaredFields().filter(ElementMatchers.isAccessibleTo(typeDescription).and(this.matcher));
                        if (fieldList.size() == 1) {
                            return new FieldSetting((FieldDescription) fieldList.getOnly());
                        }
                        if (fieldList.size() != 2) {
                            typeDefinition = typeDefinition.getSuperClass();
                        } else {
                            throw new IllegalStateException(this.matcher + " is ambigous and resolved: " + fieldList);
                        }
                    } while (typeDefinition != null);
                    throw new IllegalStateException(this.matcher + " does not locate any accessible fields for " + typeDescription);
                }
            }
        }
    }

    public static class WithoutSpecifiedTarget extends MethodCall {
        protected WithoutSpecifiedTarget(MethodLocator.Factory factory) {
            super(factory, TargetHandler.ForSelfOrStaticInvocation.Factory.INSTANCE, Collections.emptyList(), MethodInvoker.ForContextualInvocation.Factory.INSTANCE, TerminationHandler.Simple.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        public MethodCall on(Object obj) {
            return on(obj, obj.getClass());
        }

        public <T> MethodCall on(T t, Class<? super T> cls) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForValue.Factory(t, TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(cls)), this.argumentLoaders, new MethodInvoker.ForVirtualInvocation.Factory(TypeDescription.ForLoadedType.of(cls)), this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onArgument(int i) {
            if (i >= 0) {
                return new MethodCall(this.methodLocator, new TargetHandler.ForMethodParameter(i), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
            }
            throw new IllegalArgumentException("An argument index cannot be negative: " + i);
        }

        public MethodCall onField(String str) {
            return onField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
        }

        public MethodCall onField(String str, FieldLocator.Factory factory) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForField.Factory(new TargetHandler.ForField.Location.ForImplicitField(str, factory)), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onField(Field field) {
            return onField((FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public MethodCall onField(FieldDescription fieldDescription) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForField.Factory(new TargetHandler.ForField.Location.ForExplicitField(fieldDescription)), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onMethodCall(MethodCall methodCall) {
            return new MethodCall(this.methodLocator, new TargetHandler.ForMethodCall.Factory(methodCall), this.argumentLoaders, MethodInvoker.ForVirtualInvocation.WithImplicitType.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onSuper() {
            return new MethodCall(this.methodLocator, TargetHandler.ForSelfOrStaticInvocation.Factory.INSTANCE, this.argumentLoaders, MethodInvoker.ForSuperMethodInvocation.Factory.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }

        public MethodCall onDefault() {
            return new MethodCall(this.methodLocator, TargetHandler.ForSelfOrStaticInvocation.Factory.INSTANCE, this.argumentLoaders, MethodInvoker.ForDefaultMethodInvocation.Factory.INSTANCE, this.terminationHandler, this.assigner, this.typing);
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class FieldSetting implements Implementation.Composable {
        private final MethodCall methodCall;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.methodCall.equals(((FieldSetting) obj).methodCall);
        }

        public int hashCode() {
            return 527 + this.methodCall.hashCode();
        }

        protected FieldSetting(MethodCall methodCall2) {
            this.methodCall = methodCall2;
        }

        public Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new FieldSetting((MethodCall) this.methodCall.withAssigner(assigner, typing));
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return this.methodCall.prepare(instrumentedType);
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new ByteCodeAppender.Compound(this.methodCall.appender(target), Appender.INSTANCE);
        }

        public Implementation andThen(Implementation implementation) {
            return new Implementation.Compound(this.methodCall, implementation);
        }

        public Implementation.Composable andThen(Implementation.Composable composable) {
            return new Implementation.Compound.Composable((Implementation) this.methodCall, composable);
        }

        protected enum Appender implements ByteCodeAppender {
            INSTANCE;

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                if (methodDescription.getReturnType().represents(Void.TYPE)) {
                    return new ByteCodeAppender.Size(MethodReturn.VOID.apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
                throw new IllegalStateException("Instrumented method " + methodDescription + " does not return void for field setting method call");
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class Appender implements ByteCodeAppender {
        private final List<ArgumentLoader.ArgumentProvider> argumentProviders;
        private final Implementation.Target implementationTarget;
        private final MethodInvoker methodInvoker;
        private final MethodLocator methodLocator;
        /* access modifiers changed from: private */
        public final TargetHandler targetHandler;
        private final TerminationHandler terminationHandler;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Appender appender = (Appender) obj;
            return this.implementationTarget.equals(appender.implementationTarget) && this.methodLocator.equals(appender.methodLocator) && this.argumentProviders.equals(appender.argumentProviders) && this.methodInvoker.equals(appender.methodInvoker) && this.targetHandler.equals(appender.targetHandler) && this.terminationHandler.equals(appender.terminationHandler) && MethodCall.this.equals(MethodCall.this);
        }

        public int hashCode() {
            return ((((((((((((527 + this.implementationTarget.hashCode()) * 31) + this.methodLocator.hashCode()) * 31) + this.argumentProviders.hashCode()) * 31) + this.methodInvoker.hashCode()) * 31) + this.targetHandler.hashCode()) * 31) + this.terminationHandler.hashCode()) * 31) + MethodCall.this.hashCode();
        }

        protected Appender(Implementation.Target target, TerminationHandler terminationHandler2) {
            this.implementationTarget = target;
            this.methodLocator = MethodCall.this.methodLocator.make(target.getInstrumentedType());
            this.argumentProviders = new ArrayList(MethodCall.this.argumentLoaders.size());
            for (ArgumentLoader.Factory make : MethodCall.this.argumentLoaders) {
                this.argumentProviders.add(make.make(target));
            }
            this.methodInvoker = MethodCall.this.methodInvoker.make(target.getInstrumentedType());
            this.targetHandler = MethodCall.this.targetHandler.make(target);
            this.terminationHandler = terminationHandler2;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            TargetHandler.Resolved resolve = this.targetHandler.resolve(methodDescription);
            return new ByteCodeAppender.Size(new StackManipulation.Compound(this.terminationHandler.prepare(), toStackManipulation(methodDescription, toInvokedMethod(methodDescription, resolve), resolve)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }

        /* access modifiers changed from: protected */
        public MethodDescription toInvokedMethod(MethodDescription methodDescription, TargetHandler.Resolved resolved) {
            return this.methodLocator.resolve(resolved.getTypeDescription(), methodDescription);
        }

        /* access modifiers changed from: protected */
        public StackManipulation toStackManipulation(MethodDescription methodDescription, MethodDescription methodDescription2, TargetHandler.Resolved resolved) {
            if (methodDescription2.isAccessibleTo(this.implementationTarget.getInstrumentedType())) {
                ArrayList<ArgumentLoader> arrayList = new ArrayList<>();
                for (ArgumentLoader.ArgumentProvider resolve : this.argumentProviders) {
                    arrayList.addAll(resolve.resolve(methodDescription, methodDescription2));
                }
                ParameterList<?> parameters = methodDescription2.getParameters();
                if (parameters.size() == arrayList.size()) {
                    Iterator it = parameters.iterator();
                    ArrayList arrayList2 = new ArrayList(arrayList.size());
                    for (ArgumentLoader stackManipulation : arrayList) {
                        arrayList2.add(stackManipulation.toStackManipulation((ParameterDescription) it.next(), MethodCall.this.assigner, MethodCall.this.typing));
                    }
                    return new StackManipulation.Compound(resolved.toStackManipulation(methodDescription2, MethodCall.this.assigner, MethodCall.this.typing), new StackManipulation.Compound((List<? extends StackManipulation>) arrayList2), this.methodInvoker.toStackManipulation(methodDescription2, this.implementationTarget), this.terminationHandler.toStackManipulation(methodDescription2, methodDescription, MethodCall.this.assigner, MethodCall.this.typing));
                }
                throw new IllegalStateException(methodDescription2 + " does not accept " + arrayList.size() + " arguments");
            }
            throw new IllegalStateException("Cannot access " + methodDescription2 + " from " + this.implementationTarget.getInstrumentedType());
        }
    }
}

package net.bytebuddy.implementation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import net.bytebuddy.implementation.bind.annotation.TargetMethodAnnotationDrivenBinder;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.FilterableList;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.RandomString;

@HashCodeAndEqualsPlugin.Enhance
public class MethodDelegation implements Implementation.Composable {
    private final MethodDelegationBinder.AmbiguityResolver ambiguityResolver;
    private final Assigner assigner;
    private final MethodDelegationBinder.BindingResolver bindingResolver;
    private final ImplementationDelegate implementationDelegate;
    private final List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> parameterBinders;
    private final MethodDelegationBinder.TerminationHandler terminationHandler;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MethodDelegation methodDelegation = (MethodDelegation) obj;
        return this.implementationDelegate.equals(methodDelegation.implementationDelegate) && this.parameterBinders.equals(methodDelegation.parameterBinders) && this.ambiguityResolver.equals(methodDelegation.ambiguityResolver) && this.terminationHandler.equals(methodDelegation.terminationHandler) && this.bindingResolver.equals(methodDelegation.bindingResolver) && this.assigner.equals(methodDelegation.assigner);
    }

    public int hashCode() {
        return ((((((((((527 + this.implementationDelegate.hashCode()) * 31) + this.parameterBinders.hashCode()) * 31) + this.ambiguityResolver.hashCode()) * 31) + this.terminationHandler.hashCode()) * 31) + this.bindingResolver.hashCode()) * 31) + this.assigner.hashCode();
    }

    protected MethodDelegation(ImplementationDelegate implementationDelegate2, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, MethodDelegationBinder.AmbiguityResolver ambiguityResolver2, MethodDelegationBinder.BindingResolver bindingResolver2) {
        this(implementationDelegate2, list, ambiguityResolver2, MethodDelegationBinder.TerminationHandler.Default.RETURNING, bindingResolver2, Assigner.DEFAULT);
    }

    private MethodDelegation(ImplementationDelegate implementationDelegate2, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, MethodDelegationBinder.AmbiguityResolver ambiguityResolver2, MethodDelegationBinder.TerminationHandler terminationHandler2, MethodDelegationBinder.BindingResolver bindingResolver2, Assigner assigner2) {
        this.implementationDelegate = implementationDelegate2;
        this.parameterBinders = list;
        this.terminationHandler = terminationHandler2;
        this.ambiguityResolver = ambiguityResolver2;
        this.bindingResolver = bindingResolver2;
        this.assigner = assigner2;
    }

    public static MethodDelegation to(Class<?> cls) {
        return withDefaultConfiguration().to(cls);
    }

    public static MethodDelegation to(TypeDescription typeDescription) {
        return withDefaultConfiguration().to(typeDescription);
    }

    public static MethodDelegation to(Object obj) {
        return withDefaultConfiguration().to(obj);
    }

    public static MethodDelegation to(Object obj, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, compiler);
    }

    public static MethodDelegation to(Object obj, String str) {
        return withDefaultConfiguration().to(obj, str);
    }

    public static MethodDelegation to(Object obj, String str, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, str, compiler);
    }

    public static MethodDelegation to(Object obj, Type type) {
        return withDefaultConfiguration().to(obj, type);
    }

    public static MethodDelegation to(Object obj, Type type, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, type, compiler);
    }

    public static MethodDelegation to(Object obj, Type type, String str) {
        return withDefaultConfiguration().to(obj, type, str);
    }

    public static MethodDelegation to(Object obj, Type type, String str, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().to(obj, type, str, compiler);
    }

    public static MethodDelegation toConstructor(Class<?> cls) {
        return withDefaultConfiguration().toConstructor(cls);
    }

    public static MethodDelegation toConstructor(TypeDescription typeDescription) {
        return withDefaultConfiguration().toConstructor(typeDescription);
    }

    public static MethodDelegation toField(String str) {
        return withDefaultConfiguration().toField(str);
    }

    public static MethodDelegation toField(String str, FieldLocator.Factory factory) {
        return withDefaultConfiguration().toField(str, factory);
    }

    public static MethodDelegation toField(String str, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().toField(str, compiler);
    }

    public static MethodDelegation toField(String str, FieldLocator.Factory factory, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().toField(str, factory, compiler);
    }

    public static MethodDelegation toMethodReturnOf(String str) {
        return withDefaultConfiguration().toMethodReturnOf(str);
    }

    public static MethodDelegation toMethodReturnOf(String str, MethodGraph.Compiler compiler) {
        return withDefaultConfiguration().toMethodReturnOf(str, compiler);
    }

    public static WithCustomProperties withDefaultConfiguration() {
        return new WithCustomProperties(MethodDelegationBinder.AmbiguityResolver.DEFAULT, TargetMethodAnnotationDrivenBinder.ParameterBinder.DEFAULTS);
    }

    public static WithCustomProperties withEmptyConfiguration() {
        return new WithCustomProperties(MethodDelegationBinder.AmbiguityResolver.NoOp.INSTANCE, Collections.emptyList());
    }

    public Implementation.Composable withAssigner(Assigner assigner2) {
        return new MethodDelegation(this.implementationDelegate, this.parameterBinders, this.ambiguityResolver, this.terminationHandler, this.bindingResolver, assigner2);
    }

    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(new MethodDelegation(this.implementationDelegate, this.parameterBinders, this.ambiguityResolver, MethodDelegationBinder.TerminationHandler.Default.DROPPING, this.bindingResolver, this.assigner), implementation);
    }

    public Implementation.Composable andThen(Implementation.Composable composable) {
        return new Implementation.Compound.Composable((Implementation) new MethodDelegation(this.implementationDelegate, this.parameterBinders, this.ambiguityResolver, MethodDelegationBinder.TerminationHandler.Default.DROPPING, this.bindingResolver, this.assigner), composable);
    }

    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return this.implementationDelegate.prepare(instrumentedType);
    }

    public ByteCodeAppender appender(Implementation.Target target) {
        ImplementationDelegate.Compiled compile = this.implementationDelegate.compile(target.getInstrumentedType());
        return new Appender(target, new MethodDelegationBinder.Processor(compile.getRecords(), this.ambiguityResolver, this.bindingResolver), this.terminationHandler, this.assigner, compile);
    }

    protected interface ImplementationDelegate extends InstrumentedType.Prepareable {
        public static final String FIELD_NAME_PREFIX = "delegate";

        Compiled compile(TypeDescription typeDescription);

        public interface Compiled {
            List<MethodDelegationBinder.Record> getRecords();

            MethodDelegationBinder.MethodInvoker invoke();

            StackManipulation prepare(MethodDescription methodDescription);

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForStaticCall implements Compiled {
                private final List<MethodDelegationBinder.Record> records;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.records.equals(((ForStaticCall) obj).records);
                }

                public int hashCode() {
                    return 527 + this.records.hashCode();
                }

                protected ForStaticCall(List<MethodDelegationBinder.Record> list) {
                    this.records = list;
                }

                public StackManipulation prepare(MethodDescription methodDescription) {
                    return StackManipulation.Trivial.INSTANCE;
                }

                public MethodDelegationBinder.MethodInvoker invoke() {
                    return MethodDelegationBinder.MethodInvoker.Simple.INSTANCE;
                }

                public List<MethodDelegationBinder.Record> getRecords() {
                    return this.records;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForField implements Compiled {
                private final FieldDescription fieldDescription;
                private final List<MethodDelegationBinder.Record> records;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForField forField = (ForField) obj;
                    return this.fieldDescription.equals(forField.fieldDescription) && this.records.equals(forField.records);
                }

                public int hashCode() {
                    return ((527 + this.fieldDescription.hashCode()) * 31) + this.records.hashCode();
                }

                protected ForField(FieldDescription fieldDescription2, List<MethodDelegationBinder.Record> list) {
                    this.fieldDescription = fieldDescription2;
                    this.records = list;
                }

                public StackManipulation prepare(MethodDescription methodDescription) {
                    StackManipulation stackManipulation;
                    if (!methodDescription.isStatic() || this.fieldDescription.isStatic()) {
                        StackManipulation[] stackManipulationArr = new StackManipulation[2];
                        if (this.fieldDescription.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = MethodVariableAccess.loadThis();
                        }
                        stackManipulationArr[0] = stackManipulation;
                        stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                        return new StackManipulation.Compound(stackManipulationArr);
                    }
                    throw new IllegalStateException("Cannot read " + this.fieldDescription + " from " + methodDescription);
                }

                public MethodDelegationBinder.MethodInvoker invoke() {
                    return new MethodDelegationBinder.MethodInvoker.Virtual(this.fieldDescription.getType().asErasure());
                }

                public List<MethodDelegationBinder.Record> getRecords() {
                    return this.records;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForMethodReturn implements Compiled {
                private final MethodDescription methodDescription;
                private final List<MethodDelegationBinder.Record> records;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForMethodReturn forMethodReturn = (ForMethodReturn) obj;
                    return this.methodDescription.equals(forMethodReturn.methodDescription) && this.records.equals(forMethodReturn.records);
                }

                public int hashCode() {
                    return ((527 + this.methodDescription.hashCode()) * 31) + this.records.hashCode();
                }

                protected ForMethodReturn(MethodDescription methodDescription2, List<MethodDelegationBinder.Record> list) {
                    this.methodDescription = methodDescription2;
                    this.records = list;
                }

                public StackManipulation prepare(MethodDescription methodDescription2) {
                    StackManipulation stackManipulation;
                    if (!methodDescription2.isStatic() || this.methodDescription.isStatic()) {
                        StackManipulation[] stackManipulationArr = new StackManipulation[2];
                        if (this.methodDescription.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = MethodVariableAccess.loadThis();
                        }
                        stackManipulationArr[0] = stackManipulation;
                        stackManipulationArr[1] = MethodInvocation.invoke(this.methodDescription);
                        return new StackManipulation.Compound(stackManipulationArr);
                    }
                    throw new IllegalStateException("Cannot invoke " + this.methodDescription + " from " + methodDescription2);
                }

                public MethodDelegationBinder.MethodInvoker invoke() {
                    return new MethodDelegationBinder.MethodInvoker.Virtual(this.methodDescription.getReturnType().asErasure());
                }

                public List<MethodDelegationBinder.Record> getRecords() {
                    return this.records;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForConstruction implements Compiled {
                private final List<MethodDelegationBinder.Record> records;
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForConstruction forConstruction = (ForConstruction) obj;
                    return this.typeDescription.equals(forConstruction.typeDescription) && this.records.equals(forConstruction.records);
                }

                public int hashCode() {
                    return ((527 + this.typeDescription.hashCode()) * 31) + this.records.hashCode();
                }

                protected ForConstruction(TypeDescription typeDescription2, List<MethodDelegationBinder.Record> list) {
                    this.typeDescription = typeDescription2;
                    this.records = list;
                }

                public StackManipulation prepare(MethodDescription methodDescription) {
                    return new StackManipulation.Compound(TypeCreation.of(this.typeDescription), Duplication.SINGLE);
                }

                public MethodDelegationBinder.MethodInvoker invoke() {
                    return MethodDelegationBinder.MethodInvoker.Simple.INSTANCE;
                }

                public List<MethodDelegationBinder.Record> getRecords() {
                    return this.records;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForStaticMethod implements ImplementationDelegate {
            private final List<MethodDelegationBinder.Record> records;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.records.equals(((ForStaticMethod) obj).records);
            }

            public int hashCode() {
                return 527 + this.records.hashCode();
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected ForStaticMethod(List<MethodDelegationBinder.Record> list) {
                this.records = list;
            }

            protected static ImplementationDelegate of(MethodList<?> methodList, MethodDelegationBinder methodDelegationBinder) {
                ArrayList arrayList = new ArrayList(methodList.size());
                Iterator it = methodList.iterator();
                while (it.hasNext()) {
                    arrayList.add(methodDelegationBinder.compile((MethodDescription) it.next()));
                }
                return new ForStaticMethod(arrayList);
            }

            public Compiled compile(TypeDescription typeDescription) {
                return new Compiled.ForStaticCall(this.records);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class ForField implements ImplementationDelegate {
            protected final String fieldName;
            protected final ElementMatcher<? super MethodDescription> matcher;
            protected final MethodGraph.Compiler methodGraphCompiler;
            protected final List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> parameterBinders;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForField forField = (ForField) obj;
                return this.fieldName.equals(forField.fieldName) && this.methodGraphCompiler.equals(forField.methodGraphCompiler) && this.parameterBinders.equals(forField.parameterBinders) && this.matcher.equals(forField.matcher);
            }

            public int hashCode() {
                return ((((((527 + this.fieldName.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.parameterBinders.hashCode()) * 31) + this.matcher.hashCode();
            }

            /* access modifiers changed from: protected */
            public abstract FieldDescription resolve(TypeDescription typeDescription);

            protected ForField(String str, MethodGraph.Compiler compiler, List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, ElementMatcher<? super MethodDescription> elementMatcher) {
                this.fieldName = str;
                this.methodGraphCompiler = compiler;
                this.parameterBinders = list;
                this.matcher = elementMatcher;
            }

            public Compiled compile(TypeDescription typeDescription) {
                FieldDescription resolve = resolve(typeDescription);
                if (resolve.getType().asErasure().isVisibleTo(typeDescription)) {
                    MethodList<MethodDescription> methodList = (MethodList) this.methodGraphCompiler.compile(resolve.getType(), typeDescription).listNodes().asMethodList().filter(this.matcher);
                    ArrayList arrayList = new ArrayList(methodList.size());
                    MethodDelegationBinder of = TargetMethodAnnotationDrivenBinder.of(this.parameterBinders);
                    for (MethodDescription compile : methodList) {
                        arrayList.add(of.compile(compile));
                    }
                    return new Compiled.ForField(resolve, arrayList);
                }
                throw new IllegalStateException(resolve + " is not visible to " + typeDescription);
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class WithInstance extends ForField {
                private final TypeDescription.Generic fieldType;
                private final Object target;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    WithInstance withInstance = (WithInstance) obj;
                    return this.target.equals(withInstance.target) && this.fieldType.equals(withInstance.fieldType);
                }

                public int hashCode() {
                    return (((super.hashCode() * 31) + this.target.hashCode()) * 31) + this.fieldType.hashCode();
                }

                protected WithInstance(String str, MethodGraph.Compiler compiler, List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, ElementMatcher<? super MethodDescription> elementMatcher, Object obj, TypeDescription.Generic generic) {
                    super(str, compiler, list, elementMatcher);
                    this.target = obj;
                    this.fieldType = generic;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withField(new FieldDescription.Token(this.fieldName, 4169, this.fieldType)).withInitializer((LoadedTypeInitializer) new LoadedTypeInitializer.ForStaticField(this.fieldName, this.target));
                }

                /* access modifiers changed from: protected */
                public FieldDescription resolve(TypeDescription typeDescription) {
                    if (this.fieldType.asErasure().isVisibleTo(typeDescription)) {
                        return (FieldDescription) ((FieldList) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.fieldName).and(ElementMatchers.fieldType(this.fieldType.asErasure())))).getOnly();
                    }
                    throw new IllegalStateException(this.fieldType + " is not visible to " + typeDescription);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class WithLookup extends ForField {
                private final FieldLocator.Factory fieldLocatorFactory;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldLocatorFactory.equals(((WithLookup) obj).fieldLocatorFactory);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.fieldLocatorFactory.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected WithLookup(String str, MethodGraph.Compiler compiler, List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, ElementMatcher<? super MethodDescription> elementMatcher, FieldLocator.Factory factory) {
                    super(str, compiler, list, elementMatcher);
                    this.fieldLocatorFactory = factory;
                }

                /* access modifiers changed from: protected */
                public FieldDescription resolve(TypeDescription typeDescription) {
                    FieldLocator.Resolution locate = this.fieldLocatorFactory.make(typeDescription).locate(this.fieldName);
                    if (locate.isResolved()) {
                        return locate.getField();
                    }
                    throw new IllegalStateException("Could not locate " + this.fieldName + " on " + typeDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForMethodReturn implements ImplementationDelegate {
            private final ElementMatcher<? super MethodDescription> matcher;
            private final MethodGraph.Compiler methodGraphCompiler;
            private final String name;
            private final List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> parameterBinders;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForMethodReturn forMethodReturn = (ForMethodReturn) obj;
                return this.name.equals(forMethodReturn.name) && this.methodGraphCompiler.equals(forMethodReturn.methodGraphCompiler) && this.parameterBinders.equals(forMethodReturn.parameterBinders) && this.matcher.equals(forMethodReturn.matcher);
            }

            public int hashCode() {
                return ((((((527 + this.name.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.parameterBinders.hashCode()) * 31) + this.matcher.hashCode();
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected ForMethodReturn(String str, MethodGraph.Compiler compiler, List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, ElementMatcher<? super MethodDescription> elementMatcher) {
                this.name = str;
                this.methodGraphCompiler = compiler;
                this.parameterBinders = list;
                this.matcher = elementMatcher;
            }

            public Compiled compile(TypeDescription typeDescription) {
                MethodList methodList = (MethodList) new MethodList.Explicit(CompoundList.of(typeDescription.getDeclaredMethods().filter(ElementMatchers.isStatic().or(ElementMatchers.isPrivate())), (FilterableList) this.methodGraphCompiler.compile(typeDescription).listNodes().asMethodList())).filter(ElementMatchers.named(this.name).and(ElementMatchers.takesArguments(0)).and(ElementMatchers.not(ElementMatchers.returns((ElementMatcher<? super TypeDescription>) ElementMatchers.isPrimitive().or(ElementMatchers.isArray())))));
                if (methodList.size() != 1) {
                    throw new IllegalStateException(typeDescription + " does not define method without arguments with name " + this.name + ": " + methodList);
                } else if (((MethodDescription) methodList.getOnly()).getReturnType().asErasure().isVisibleTo(typeDescription)) {
                    MethodList<MethodDescription> methodList2 = (MethodList) this.methodGraphCompiler.compile(((MethodDescription) methodList.getOnly()).getReturnType(), typeDescription).listNodes().asMethodList().filter(this.matcher);
                    ArrayList arrayList = new ArrayList(methodList2.size());
                    MethodDelegationBinder of = TargetMethodAnnotationDrivenBinder.of(this.parameterBinders);
                    for (MethodDescription compile : methodList2) {
                        arrayList.add(of.compile(compile));
                    }
                    return new Compiled.ForMethodReturn((MethodDescription) methodList.get(0), arrayList);
                } else {
                    throw new IllegalStateException(methodList.getOnly() + " is not visible to " + typeDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForConstruction implements ImplementationDelegate {
            private final List<MethodDelegationBinder.Record> records;
            private final TypeDescription typeDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ForConstruction forConstruction = (ForConstruction) obj;
                return this.typeDescription.equals(forConstruction.typeDescription) && this.records.equals(forConstruction.records);
            }

            public int hashCode() {
                return ((527 + this.typeDescription.hashCode()) * 31) + this.records.hashCode();
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected ForConstruction(TypeDescription typeDescription2, List<MethodDelegationBinder.Record> list) {
                this.typeDescription = typeDescription2;
                this.records = list;
            }

            protected static ImplementationDelegate of(TypeDescription typeDescription2, MethodList<?> methodList, MethodDelegationBinder methodDelegationBinder) {
                ArrayList arrayList = new ArrayList(methodList.size());
                Iterator it = methodList.iterator();
                while (it.hasNext()) {
                    arrayList.add(methodDelegationBinder.compile((MethodDescription) it.next()));
                }
                return new ForConstruction(typeDescription2, arrayList);
            }

            public Compiled compile(TypeDescription typeDescription2) {
                return new Compiled.ForConstruction(this.typeDescription, this.records);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class Appender implements ByteCodeAppender {
        private final Assigner assigner;
        private final ImplementationDelegate.Compiled compiled;
        private final Implementation.Target implementationTarget;
        private final MethodDelegationBinder.Record processor;
        private final MethodDelegationBinder.TerminationHandler terminationHandler;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Appender appender = (Appender) obj;
            return this.implementationTarget.equals(appender.implementationTarget) && this.processor.equals(appender.processor) && this.terminationHandler.equals(appender.terminationHandler) && this.assigner.equals(appender.assigner) && this.compiled.equals(appender.compiled);
        }

        public int hashCode() {
            return ((((((((527 + this.implementationTarget.hashCode()) * 31) + this.processor.hashCode()) * 31) + this.terminationHandler.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.compiled.hashCode();
        }

        protected Appender(Implementation.Target target, MethodDelegationBinder.Record record, MethodDelegationBinder.TerminationHandler terminationHandler2, Assigner assigner2, ImplementationDelegate.Compiled compiled2) {
            this.implementationTarget = target;
            this.processor = record;
            this.terminationHandler = terminationHandler2;
            this.assigner = assigner2;
            this.compiled = compiled2;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            return new ByteCodeAppender.Size(new StackManipulation.Compound(this.compiled.prepare(methodDescription), this.processor.bind(this.implementationTarget, methodDescription, this.terminationHandler, this.compiled.invoke(), this.assigner)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class WithCustomProperties {
        private final MethodDelegationBinder.AmbiguityResolver ambiguityResolver;
        private final MethodDelegationBinder.BindingResolver bindingResolver;
        private final ElementMatcher<? super MethodDescription> matcher;
        private final List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> parameterBinders;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            WithCustomProperties withCustomProperties = (WithCustomProperties) obj;
            return this.ambiguityResolver.equals(withCustomProperties.ambiguityResolver) && this.parameterBinders.equals(withCustomProperties.parameterBinders) && this.bindingResolver.equals(withCustomProperties.bindingResolver) && this.matcher.equals(withCustomProperties.matcher);
        }

        public int hashCode() {
            return ((((((527 + this.ambiguityResolver.hashCode()) * 31) + this.parameterBinders.hashCode()) * 31) + this.bindingResolver.hashCode()) * 31) + this.matcher.hashCode();
        }

        protected WithCustomProperties(MethodDelegationBinder.AmbiguityResolver ambiguityResolver2, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list) {
            this(ambiguityResolver2, list, MethodDelegationBinder.BindingResolver.Default.INSTANCE, ElementMatchers.any());
        }

        private WithCustomProperties(MethodDelegationBinder.AmbiguityResolver ambiguityResolver2, List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list, MethodDelegationBinder.BindingResolver bindingResolver2, ElementMatcher<? super MethodDescription> elementMatcher) {
            this.ambiguityResolver = ambiguityResolver2;
            this.parameterBinders = list;
            this.bindingResolver = bindingResolver2;
            this.matcher = elementMatcher;
        }

        public WithCustomProperties withResolvers(MethodDelegationBinder.AmbiguityResolver... ambiguityResolverArr) {
            return withResolvers((List<? extends MethodDelegationBinder.AmbiguityResolver>) Arrays.asList(ambiguityResolverArr));
        }

        public WithCustomProperties withResolvers(List<? extends MethodDelegationBinder.AmbiguityResolver> list) {
            return new WithCustomProperties(new MethodDelegationBinder.AmbiguityResolver.Compound((List<? extends MethodDelegationBinder.AmbiguityResolver>) CompoundList.of(this.ambiguityResolver, list)), this.parameterBinders, this.bindingResolver, this.matcher);
        }

        public WithCustomProperties withBinders(TargetMethodAnnotationDrivenBinder.ParameterBinder<?>... parameterBinderArr) {
            return withBinders((List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>>) Arrays.asList(parameterBinderArr));
        }

        public WithCustomProperties withBinders(List<? extends TargetMethodAnnotationDrivenBinder.ParameterBinder<?>> list) {
            return new WithCustomProperties(this.ambiguityResolver, CompoundList.of(this.parameterBinders, (List<TargetMethodAnnotationDrivenBinder.ParameterBinder<?>>) list), this.bindingResolver, this.matcher);
        }

        public WithCustomProperties withBindingResolver(MethodDelegationBinder.BindingResolver bindingResolver2) {
            return new WithCustomProperties(this.ambiguityResolver, this.parameterBinders, bindingResolver2, this.matcher);
        }

        public WithCustomProperties filter(ElementMatcher<? super MethodDescription> elementMatcher) {
            return new WithCustomProperties(this.ambiguityResolver, this.parameterBinders, this.bindingResolver, new ElementMatcher.Junction.Conjunction(this.matcher, elementMatcher));
        }

        public MethodDelegation to(Class<?> cls) {
            return to(TypeDescription.ForLoadedType.of(cls));
        }

        public MethodDelegation to(TypeDescription typeDescription) {
            if (typeDescription.isArray()) {
                throw new IllegalArgumentException("Cannot delegate to array " + typeDescription);
            } else if (!typeDescription.isPrimitive()) {
                return new MethodDelegation(ImplementationDelegate.ForStaticMethod.of((MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isStatic().and(this.matcher)), TargetMethodAnnotationDrivenBinder.of(this.parameterBinders)), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
            } else {
                throw new IllegalArgumentException("Cannot delegate to primitive " + typeDescription);
            }
        }

        public MethodDelegation to(Object obj) {
            return to(obj, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, MethodGraph.Compiler compiler) {
            return to(obj, (Type) obj.getClass(), compiler);
        }

        public MethodDelegation to(Object obj, String str) {
            return to(obj, str, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, String str, MethodGraph.Compiler compiler) {
            return to(obj, obj.getClass(), str, compiler);
        }

        public MethodDelegation to(Object obj, Type type) {
            return to(obj, type, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, Type type, MethodGraph.Compiler compiler) {
            return to(obj, type, "delegate$" + RandomString.hashOf(obj.hashCode()), compiler);
        }

        public MethodDelegation to(Object obj, Type type, String str) {
            return to(obj, type, str, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation to(Object obj, Type type, String str, MethodGraph.Compiler compiler) {
            TypeDescription.Generic describe = TypeDefinition.Sort.describe(type);
            if (describe.asErasure().isInstance(obj)) {
                return new MethodDelegation(new ImplementationDelegate.ForField.WithInstance(str, compiler, this.parameterBinders, this.matcher, obj, describe), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
            }
            throw new IllegalArgumentException(obj + " is not an instance of " + type);
        }

        public MethodDelegation toConstructor(Class<?> cls) {
            return toConstructor(TypeDescription.ForLoadedType.of(cls));
        }

        public MethodDelegation toConstructor(TypeDescription typeDescription) {
            return new MethodDelegation(ImplementationDelegate.ForConstruction.of(typeDescription, (MethodList) typeDescription.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(this.matcher)), TargetMethodAnnotationDrivenBinder.of(this.parameterBinders)), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
        }

        public MethodDelegation toField(String str) {
            return toField(str, (FieldLocator.Factory) FieldLocator.ForClassHierarchy.Factory.INSTANCE);
        }

        public MethodDelegation toField(String str, FieldLocator.Factory factory) {
            return toField(str, factory, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation toField(String str, MethodGraph.Compiler compiler) {
            return toField(str, FieldLocator.ForClassHierarchy.Factory.INSTANCE, compiler);
        }

        public MethodDelegation toField(String str, FieldLocator.Factory factory, MethodGraph.Compiler compiler) {
            return new MethodDelegation(new ImplementationDelegate.ForField.WithLookup(str, compiler, this.parameterBinders, this.matcher, factory), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
        }

        public MethodDelegation toMethodReturnOf(String str) {
            return toMethodReturnOf(str, MethodGraph.Compiler.DEFAULT);
        }

        public MethodDelegation toMethodReturnOf(String str, MethodGraph.Compiler compiler) {
            return new MethodDelegation(new ImplementationDelegate.ForMethodReturn(str, compiler, this.parameterBinders, this.matcher), this.parameterBinders, this.ambiguityResolver, this.bindingResolver);
        }
    }
}

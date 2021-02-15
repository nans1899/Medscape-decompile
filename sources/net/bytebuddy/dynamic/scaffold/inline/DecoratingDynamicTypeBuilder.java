package net.bytebuddy.dynamic.scaffold.inline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.ClassWriterStrategy;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;

@HashCodeAndEqualsPlugin.Enhance
public class DecoratingDynamicTypeBuilder<T> extends DynamicType.Builder.AbstractBase<T> {
    private final AnnotationRetention annotationRetention;
    private final AnnotationValueFilter.Factory annotationValueFilterFactory;
    private final AsmVisitorWrapper asmVisitorWrapper;
    private final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
    private final List<DynamicType> auxiliaryTypes;
    private final ClassFileLocator classFileLocator;
    private final ClassFileVersion classFileVersion;
    private final ClassWriterStrategy classWriterStrategy;
    private final LatentMatcher<? super MethodDescription> ignoredMethods;
    private final Implementation.Context.Factory implementationContextFactory;
    private final TypeDescription instrumentedType;
    private final MethodGraph.Compiler methodGraphCompiler;
    private final TypeAttributeAppender typeAttributeAppender;
    private final TypeValidation typeValidation;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DecoratingDynamicTypeBuilder decoratingDynamicTypeBuilder = (DecoratingDynamicTypeBuilder) obj;
        return this.annotationRetention.equals(decoratingDynamicTypeBuilder.annotationRetention) && this.typeValidation.equals(decoratingDynamicTypeBuilder.typeValidation) && this.instrumentedType.equals(decoratingDynamicTypeBuilder.instrumentedType) && this.typeAttributeAppender.equals(decoratingDynamicTypeBuilder.typeAttributeAppender) && this.asmVisitorWrapper.equals(decoratingDynamicTypeBuilder.asmVisitorWrapper) && this.classFileVersion.equals(decoratingDynamicTypeBuilder.classFileVersion) && this.auxiliaryTypeNamingStrategy.equals(decoratingDynamicTypeBuilder.auxiliaryTypeNamingStrategy) && this.annotationValueFilterFactory.equals(decoratingDynamicTypeBuilder.annotationValueFilterFactory) && this.implementationContextFactory.equals(decoratingDynamicTypeBuilder.implementationContextFactory) && this.methodGraphCompiler.equals(decoratingDynamicTypeBuilder.methodGraphCompiler) && this.classWriterStrategy.equals(decoratingDynamicTypeBuilder.classWriterStrategy) && this.ignoredMethods.equals(decoratingDynamicTypeBuilder.ignoredMethods) && this.auxiliaryTypes.equals(decoratingDynamicTypeBuilder.auxiliaryTypes) && this.classFileLocator.equals(decoratingDynamicTypeBuilder.classFileLocator);
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((527 + this.instrumentedType.hashCode()) * 31) + this.typeAttributeAppender.hashCode()) * 31) + this.asmVisitorWrapper.hashCode()) * 31) + this.classFileVersion.hashCode()) * 31) + this.auxiliaryTypeNamingStrategy.hashCode()) * 31) + this.annotationValueFilterFactory.hashCode()) * 31) + this.annotationRetention.hashCode()) * 31) + this.implementationContextFactory.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.typeValidation.hashCode()) * 31) + this.classWriterStrategy.hashCode()) * 31) + this.ignoredMethods.hashCode()) * 31) + this.auxiliaryTypes.hashCode()) * 31) + this.classFileLocator.hashCode();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DecoratingDynamicTypeBuilder(net.bytebuddy.description.type.TypeDescription r17, net.bytebuddy.ClassFileVersion r18, net.bytebuddy.implementation.auxiliary.AuxiliaryType.NamingStrategy r19, net.bytebuddy.implementation.attribute.AnnotationValueFilter.Factory r20, net.bytebuddy.implementation.attribute.AnnotationRetention r21, net.bytebuddy.implementation.Implementation.Context.Factory r22, net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler r23, net.bytebuddy.dynamic.scaffold.TypeValidation r24, net.bytebuddy.dynamic.scaffold.ClassWriterStrategy r25, net.bytebuddy.matcher.LatentMatcher<? super net.bytebuddy.description.method.MethodDescription> r26, net.bytebuddy.dynamic.ClassFileLocator r27) {
        /*
            r16 = this;
            boolean r0 = r21.isEnabled()
            if (r0 == 0) goto L_0x000e
            net.bytebuddy.implementation.attribute.TypeAttributeAppender$ForInstrumentedType$Differentiating r0 = new net.bytebuddy.implementation.attribute.TypeAttributeAppender$ForInstrumentedType$Differentiating
            r2 = r17
            r0.<init>(r2)
            goto L_0x0012
        L_0x000e:
            r2 = r17
            net.bytebuddy.implementation.attribute.TypeAttributeAppender$ForInstrumentedType r0 = net.bytebuddy.implementation.attribute.TypeAttributeAppender.ForInstrumentedType.INSTANCE
        L_0x0012:
            r3 = r0
            net.bytebuddy.asm.AsmVisitorWrapper$NoOp r4 = net.bytebuddy.asm.AsmVisitorWrapper.NoOp.INSTANCE
            java.util.List r14 = java.util.Collections.emptyList()
            r1 = r16
            r2 = r17
            r5 = r18
            r6 = r19
            r7 = r20
            r8 = r21
            r9 = r22
            r10 = r23
            r11 = r24
            r12 = r25
            r13 = r26
            r15 = r27
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.scaffold.inline.DecoratingDynamicTypeBuilder.<init>(net.bytebuddy.description.type.TypeDescription, net.bytebuddy.ClassFileVersion, net.bytebuddy.implementation.auxiliary.AuxiliaryType$NamingStrategy, net.bytebuddy.implementation.attribute.AnnotationValueFilter$Factory, net.bytebuddy.implementation.attribute.AnnotationRetention, net.bytebuddy.implementation.Implementation$Context$Factory, net.bytebuddy.dynamic.scaffold.MethodGraph$Compiler, net.bytebuddy.dynamic.scaffold.TypeValidation, net.bytebuddy.dynamic.scaffold.ClassWriterStrategy, net.bytebuddy.matcher.LatentMatcher, net.bytebuddy.dynamic.ClassFileLocator):void");
    }

    protected DecoratingDynamicTypeBuilder(TypeDescription typeDescription, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, ClassFileVersion classFileVersion2, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, LatentMatcher<? super MethodDescription> latentMatcher, List<DynamicType> list, ClassFileLocator classFileLocator2) {
        this.instrumentedType = typeDescription;
        this.typeAttributeAppender = typeAttributeAppender2;
        this.asmVisitorWrapper = asmVisitorWrapper2;
        this.classFileVersion = classFileVersion2;
        this.auxiliaryTypeNamingStrategy = namingStrategy;
        this.annotationValueFilterFactory = factory;
        this.annotationRetention = annotationRetention2;
        this.implementationContextFactory = factory2;
        this.methodGraphCompiler = compiler;
        this.typeValidation = typeValidation2;
        this.classWriterStrategy = classWriterStrategy2;
        this.ignoredMethods = latentMatcher;
        this.auxiliaryTypes = list;
        this.classFileLocator = classFileLocator2;
    }

    public DynamicType.Builder<T> visit(AsmVisitorWrapper asmVisitorWrapper2) {
        return new DecoratingDynamicTypeBuilder(this.instrumentedType, this.typeAttributeAppender, new AsmVisitorWrapper.Compound(this.asmVisitorWrapper, asmVisitorWrapper2), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes, this.classFileLocator);
    }

    public DynamicType.Builder<T> name(String str) {
        throw new UnsupportedOperationException("Cannot change name of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> modifiers(int i) {
        throw new UnsupportedOperationException("Cannot change modifiers of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> merge(Collection<? extends ModifierContributor.ForType> collection) {
        throw new UnsupportedOperationException("Cannot change modifiers of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> topLevelType() {
        throw new UnsupportedOperationException("Cannot change type declaration of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder.InnerTypeDefinition.ForType<T> innerTypeOf(TypeDescription typeDescription) {
        throw new UnsupportedOperationException("Cannot change type declaration of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder.InnerTypeDefinition<T> innerTypeOf(MethodDescription.InDefinedShape inDefinedShape) {
        throw new UnsupportedOperationException("Cannot change type declaration of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> declaredTypes(Collection<? extends TypeDescription> collection) {
        throw new UnsupportedOperationException("Cannot change type declaration of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> nestHost(TypeDescription typeDescription) {
        throw new UnsupportedOperationException("Cannot change type declaration of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> nestMembers(Collection<? extends TypeDescription> collection) {
        throw new UnsupportedOperationException("Cannot change type declaration of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> attribute(TypeAttributeAppender typeAttributeAppender2) {
        return new DecoratingDynamicTypeBuilder(this.instrumentedType, new TypeAttributeAppender.Compound(this.typeAttributeAppender, typeAttributeAppender2), this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes, this.classFileLocator);
    }

    public DynamicType.Builder<T> annotateType(Collection<? extends AnnotationDescription> collection) {
        return attribute(new TypeAttributeAppender.Explicit(new ArrayList(collection)));
    }

    public DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional<T> implement(Collection<? extends TypeDefinition> collection) {
        throw new UnsupportedOperationException("Cannot implement interface for decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> initializer(ByteCodeAppender byteCodeAppender) {
        throw new UnsupportedOperationException("Cannot add initializer of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> initializer(LoadedTypeInitializer loadedTypeInitializer) {
        throw new UnsupportedOperationException("Cannot add initializer of decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder.TypeVariableDefinition<T> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
        throw new UnsupportedOperationException("Cannot add type variable to decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
        throw new UnsupportedOperationException("Cannot transform decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder.FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, int i) {
        throw new UnsupportedOperationException("Cannot define field for decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder.FieldDefinition.Valuable<T> field(LatentMatcher<? super FieldDescription> latentMatcher) {
        throw new UnsupportedOperationException("Cannot change field for decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher) {
        return new DecoratingDynamicTypeBuilder(this.instrumentedType, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, new LatentMatcher.Disjunction((LatentMatcher<? super S>[]) new LatentMatcher[]{this.ignoredMethods, latentMatcher}), this.auxiliaryTypes, this.classFileLocator);
    }

    public DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, int i) {
        throw new UnsupportedOperationException("Cannot define method for decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder.MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(int i) {
        throw new UnsupportedOperationException("Cannot define constructor for decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder.MethodDefinition.ImplementationDefinition<T> invokable(LatentMatcher<? super MethodDescription> latentMatcher) {
        throw new UnsupportedOperationException("Cannot intercept method for decorated type: " + this.instrumentedType);
    }

    public DynamicType.Builder<T> require(Collection<DynamicType> collection) {
        return new DecoratingDynamicTypeBuilder(this.instrumentedType, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, CompoundList.of(this.auxiliaryTypes, (List<DynamicType>) new ArrayList(collection)), this.classFileLocator);
    }

    public DynamicType.Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy) {
        return make(typeResolutionStrategy, TypePool.Empty.INSTANCE);
    }

    public DynamicType.Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
        TypeDescription typeDescription = this.instrumentedType;
        return TypeWriter.Default.forDecoration(typeDescription, this.classFileVersion, this.auxiliaryTypes, CompoundList.of(this.methodGraphCompiler.compile(typeDescription).listNodes().asMethodList().filter(ElementMatchers.not(this.ignoredMethods.resolve(this.instrumentedType))), this.instrumentedType.getDeclaredMethods().filter(ElementMatchers.not(ElementMatchers.isVirtual()))), this.typeAttributeAppender, this.asmVisitorWrapper, this.annotationValueFilterFactory, this.annotationRetention, this.auxiliaryTypeNamingStrategy, this.implementationContextFactory, this.typeValidation, this.classWriterStrategy, typePool, this.classFileLocator).make(typeResolutionStrategy.resolve());
    }
}

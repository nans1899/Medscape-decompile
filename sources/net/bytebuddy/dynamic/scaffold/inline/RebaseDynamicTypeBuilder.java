package net.bytebuddy.dynamic.scaffold.inline;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.ClassWriterStrategy;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;

@HashCodeAndEqualsPlugin.Enhance
public class RebaseDynamicTypeBuilder<T> extends AbstractInliningDynamicTypeBuilder<T> {
    private final MethodNameTransformer methodNameTransformer;

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.methodNameTransformer.equals(((RebaseDynamicTypeBuilder) obj).methodNameTransformer);
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.methodNameTransformer.hashCode();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public RebaseDynamicTypeBuilder(net.bytebuddy.dynamic.scaffold.InstrumentedType.WithFlexibleName r20, net.bytebuddy.ClassFileVersion r21, net.bytebuddy.implementation.auxiliary.AuxiliaryType.NamingStrategy r22, net.bytebuddy.implementation.attribute.AnnotationValueFilter.Factory r23, net.bytebuddy.implementation.attribute.AnnotationRetention r24, net.bytebuddy.implementation.Implementation.Context.Factory r25, net.bytebuddy.dynamic.scaffold.MethodGraph.Compiler r26, net.bytebuddy.dynamic.scaffold.TypeValidation r27, net.bytebuddy.dynamic.scaffold.ClassWriterStrategy r28, net.bytebuddy.matcher.LatentMatcher<? super net.bytebuddy.description.method.MethodDescription> r29, net.bytebuddy.description.type.TypeDescription r30, net.bytebuddy.dynamic.ClassFileLocator r31, net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer r32) {
        /*
            r19 = this;
            net.bytebuddy.dynamic.scaffold.FieldRegistry$Default r2 = new net.bytebuddy.dynamic.scaffold.FieldRegistry$Default
            r2.<init>()
            net.bytebuddy.dynamic.scaffold.MethodRegistry$Default r3 = new net.bytebuddy.dynamic.scaffold.MethodRegistry$Default
            r3.<init>()
            boolean r0 = r24.isEnabled()
            if (r0 == 0) goto L_0x0018
            net.bytebuddy.implementation.attribute.TypeAttributeAppender$ForInstrumentedType$Differentiating r0 = new net.bytebuddy.implementation.attribute.TypeAttributeAppender$ForInstrumentedType$Differentiating
            r14 = r30
            r0.<init>(r14)
            goto L_0x001c
        L_0x0018:
            r14 = r30
            net.bytebuddy.implementation.attribute.TypeAttributeAppender$ForInstrumentedType r0 = net.bytebuddy.implementation.attribute.TypeAttributeAppender.ForInstrumentedType.INSTANCE
        L_0x001c:
            r4 = r0
            net.bytebuddy.asm.AsmVisitorWrapper$NoOp r5 = net.bytebuddy.asm.AsmVisitorWrapper.NoOp.INSTANCE
            java.util.List r15 = java.util.Collections.emptyList()
            r0 = r19
            r1 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
            r12 = r27
            r13 = r28
            r14 = r29
            r16 = r30
            r17 = r31
            r18 = r32
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.scaffold.inline.RebaseDynamicTypeBuilder.<init>(net.bytebuddy.dynamic.scaffold.InstrumentedType$WithFlexibleName, net.bytebuddy.ClassFileVersion, net.bytebuddy.implementation.auxiliary.AuxiliaryType$NamingStrategy, net.bytebuddy.implementation.attribute.AnnotationValueFilter$Factory, net.bytebuddy.implementation.attribute.AnnotationRetention, net.bytebuddy.implementation.Implementation$Context$Factory, net.bytebuddy.dynamic.scaffold.MethodGraph$Compiler, net.bytebuddy.dynamic.scaffold.TypeValidation, net.bytebuddy.dynamic.scaffold.ClassWriterStrategy, net.bytebuddy.matcher.LatentMatcher, net.bytebuddy.description.type.TypeDescription, net.bytebuddy.dynamic.ClassFileLocator, net.bytebuddy.dynamic.scaffold.inline.MethodNameTransformer):void");
    }

    protected RebaseDynamicTypeBuilder(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, LatentMatcher<? super MethodDescription> latentMatcher, List<? extends DynamicType> list, TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodNameTransformer methodNameTransformer2) {
        super(withFlexibleName, fieldRegistry, methodRegistry, typeAttributeAppender, asmVisitorWrapper, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, classWriterStrategy, latentMatcher, list, typeDescription, classFileLocator);
        this.methodNameTransformer = methodNameTransformer2;
    }

    /* access modifiers changed from: protected */
    public DynamicType.Builder<T> materialize(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry, MethodRegistry methodRegistry, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, LatentMatcher<? super MethodDescription> latentMatcher, List<? extends DynamicType> list) {
        return new RebaseDynamicTypeBuilder(withFlexibleName, fieldRegistry, methodRegistry, typeAttributeAppender, asmVisitorWrapper, classFileVersion, namingStrategy, factory, annotationRetention, factory2, compiler, typeValidation, classWriterStrategy, latentMatcher, list, this.originalType, this.classFileLocator, this.methodNameTransformer);
    }

    public DynamicType.Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
        MethodRegistry.Prepared prepare = this.methodRegistry.prepare(this.instrumentedType, this.methodGraphCompiler, this.typeValidation, InliningImplementationMatcher.of(this.ignoredMethods, this.originalType));
        MethodRebaseResolver make = MethodRebaseResolver.Default.make(prepare.getInstrumentedType(), new HashSet(this.originalType.getDeclaredMethods().asTokenList(ElementMatchers.is((Object) this.originalType)).filter(RebaseableMatcher.of(prepare.getInstrumentedType(), prepare.getInstrumentedMethods()))), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.methodNameTransformer);
        return TypeWriter.Default.forRebasing(prepare, this.auxiliaryTypes, this.fieldRegistry.compile(prepare.getInstrumentedType()), this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.annotationValueFilterFactory, this.annotationRetention, this.auxiliaryTypeNamingStrategy, this.implementationContextFactory, this.typeValidation, this.classWriterStrategy, typePool, this.originalType, this.classFileLocator, make).make(typeResolutionStrategy.resolve());
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class RebaseableMatcher implements ElementMatcher<MethodDescription.Token> {
        private final Set<MethodDescription.Token> tokens;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.tokens.equals(((RebaseableMatcher) obj).tokens);
        }

        public int hashCode() {
            return 527 + this.tokens.hashCode();
        }

        protected RebaseableMatcher(Set<MethodDescription.Token> set) {
            this.tokens = set;
        }

        protected static ElementMatcher<MethodDescription.Token> of(TypeDescription typeDescription, MethodList<?> methodList) {
            return new RebaseableMatcher(new HashSet(methodList.asTokenList(ElementMatchers.is((Object) typeDescription))));
        }

        public boolean matches(MethodDescription.Token token) {
            return this.tokens.contains(token);
        }
    }
}

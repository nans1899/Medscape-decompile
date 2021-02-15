package net.bytebuddy.dynamic.scaffold.subclass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;

public interface ConstructorStrategy {
    List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription);

    MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry);

    public enum Default implements ConstructorStrategy {
        NO_CONSTRUCTORS {
            /* access modifiers changed from: protected */
            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry;
            }

            /* access modifiers changed from: protected */
            public List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                return Collections.emptyList();
            }
        },
        DEFAULT_CONSTRUCTOR {
            /* access modifiers changed from: protected */
            public List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodList;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodList = new MethodList.Empty();
                } else {
                    methodList = (MethodList) superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0)).and(ElementMatchers.isVisibleTo(typeDescription)));
                }
                if (methodList.size() == 1) {
                    return Collections.singletonList(new MethodDescription.Token(1));
                }
                throw new IllegalArgumentException(typeDescription.getSuperClass() + " declares no constructor that is visible to " + typeDescription);
            }

            /* access modifiers changed from: protected */
            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS {
            /* access modifiers changed from: protected */
            public List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodList;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodList = new MethodList.Empty();
                } else {
                    methodList = (MethodList) superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.isVisibleTo(typeDescription)));
                }
                return methodList.asTokenList(ElementMatchers.is((Object) typeDescription));
            }

            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS_PUBLIC {
            /* access modifiers changed from: protected */
            public List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodList;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodList = new MethodList.Empty();
                } else {
                    methodList = (MethodList) superClass.getDeclaredMethods().filter(ElementMatchers.isPublic().and(ElementMatchers.isConstructor()));
                }
                return methodList.asTokenList(ElementMatchers.is((Object) typeDescription));
            }

            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        },
        IMITATE_SUPER_CLASS_OPENING {
            /* access modifiers changed from: protected */
            public int resolveModifier(int i) {
                return 1;
            }

            /* access modifiers changed from: protected */
            public List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription) {
                MethodList methodList;
                TypeDescription.Generic superClass = typeDescription.getSuperClass();
                if (superClass == null) {
                    methodList = new MethodList.Empty();
                } else {
                    methodList = (MethodList) superClass.getDeclaredMethods().filter(ElementMatchers.isConstructor().and(ElementMatchers.isVisibleTo(typeDescription)));
                }
                return methodList.asTokenList(ElementMatchers.is((Object) typeDescription));
            }

            public MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory) {
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor()), new MethodRegistry.Handler.ForImplementation(SuperMethodCall.INSTANCE), factory, Transformer.NoOp.make());
            }
        };

        /* access modifiers changed from: protected */
        public abstract List<MethodDescription.Token> doExtractConstructors(TypeDescription typeDescription);

        /* access modifiers changed from: protected */
        public abstract MethodRegistry doInject(MethodRegistry methodRegistry, MethodAttributeAppender.Factory factory);

        /* access modifiers changed from: protected */
        public int resolveModifier(int i) {
            return i;
        }

        public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
            List<MethodDescription.Token> doExtractConstructors = doExtractConstructors(typeDescription);
            ArrayList arrayList = new ArrayList(doExtractConstructors.size());
            for (MethodDescription.Token next : doExtractConstructors) {
                arrayList.add(new MethodDescription.Token(next.getName(), resolveModifier(next.getModifiers()), next.getTypeVariableTokens(), next.getReturnType(), next.getParameterTokens(), next.getExceptionTypes(), next.getAnnotations(), next.getDefaultValue(), TypeDescription.Generic.UNDEFINED));
            }
            return arrayList;
        }

        public MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry) {
            return doInject(methodRegistry, MethodAttributeAppender.NoOp.INSTANCE);
        }

        public ConstructorStrategy with(MethodAttributeAppender.Factory factory) {
            return new WithMethodAttributeAppenderFactory(this, factory);
        }

        public ConstructorStrategy withInheritedAnnotations() {
            return new WithMethodAttributeAppenderFactory(this, MethodAttributeAppender.ForInstrumentedMethod.EXCLUDING_RECEIVER);
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class WithMethodAttributeAppenderFactory implements ConstructorStrategy {
            private final Default delegate;
            private final MethodAttributeAppender.Factory methodAttributeAppenderFactory;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                WithMethodAttributeAppenderFactory withMethodAttributeAppenderFactory = (WithMethodAttributeAppenderFactory) obj;
                return this.delegate.equals(withMethodAttributeAppenderFactory.delegate) && this.methodAttributeAppenderFactory.equals(withMethodAttributeAppenderFactory.methodAttributeAppenderFactory);
            }

            public int hashCode() {
                return ((527 + this.delegate.hashCode()) * 31) + this.methodAttributeAppenderFactory.hashCode();
            }

            protected WithMethodAttributeAppenderFactory(Default defaultR, MethodAttributeAppender.Factory factory) {
                this.delegate = defaultR;
                this.methodAttributeAppenderFactory = factory;
            }

            public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
                return this.delegate.extractConstructors(typeDescription);
            }

            public MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry) {
                return this.delegate.doInject(methodRegistry, this.methodAttributeAppenderFactory);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForDefaultConstructor implements ConstructorStrategy {
        private final ElementMatcher<? super MethodDescription> elementMatcher;
        private final MethodAttributeAppender.Factory methodAttributeAppenderFactory;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForDefaultConstructor forDefaultConstructor = (ForDefaultConstructor) obj;
            return this.elementMatcher.equals(forDefaultConstructor.elementMatcher) && this.methodAttributeAppenderFactory.equals(forDefaultConstructor.methodAttributeAppenderFactory);
        }

        public int hashCode() {
            return ((527 + this.elementMatcher.hashCode()) * 31) + this.methodAttributeAppenderFactory.hashCode();
        }

        public ForDefaultConstructor() {
            this((ElementMatcher<? super MethodDescription>) ElementMatchers.any());
        }

        public ForDefaultConstructor(ElementMatcher<? super MethodDescription> elementMatcher2) {
            this(elementMatcher2, MethodAttributeAppender.NoOp.INSTANCE);
        }

        public ForDefaultConstructor(MethodAttributeAppender.Factory factory) {
            this(ElementMatchers.any(), factory);
        }

        public ForDefaultConstructor(ElementMatcher<? super MethodDescription> elementMatcher2, MethodAttributeAppender.Factory factory) {
            this.elementMatcher = elementMatcher2;
            this.methodAttributeAppenderFactory = factory;
        }

        public List<MethodDescription.Token> extractConstructors(TypeDescription typeDescription) {
            if (!((MethodList) typeDescription.getSuperClass().getDeclaredMethods().filter(ElementMatchers.isConstructor())).isEmpty()) {
                return Collections.singletonList(new MethodDescription.Token(1));
            }
            throw new IllegalStateException("Cannot define default constructor for class without super class constructor");
        }

        public MethodRegistry inject(TypeDescription typeDescription, MethodRegistry methodRegistry) {
            MethodList methodList = (MethodList) typeDescription.getSuperClass().getDeclaredMethods().filter(ElementMatchers.isConstructor().and(this.elementMatcher));
            if (!methodList.isEmpty()) {
                if (!((MethodList) methodList.filter(ElementMatchers.takesArguments(0))).isEmpty()) {
                    methodList = (MethodList) methodList.filter(ElementMatchers.takesArguments(0));
                } else if (methodList.size() > 1) {
                    throw new IllegalStateException("More than one possible super constructor for constructor delegation: " + methodList);
                }
                MethodCall invoke = MethodCall.invoke((MethodDescription) methodList.getOnly());
                for (TypeDescription defaultValue : ((MethodDescription) methodList.getOnly()).getParameters().asTypeList().asErasures()) {
                    invoke = invoke.with(defaultValue.getDefaultValue());
                }
                return methodRegistry.append(new LatentMatcher.Resolved(ElementMatchers.isConstructor().and(ElementMatchers.takesArguments(0))), new MethodRegistry.Handler.ForImplementation(invoke), this.methodAttributeAppenderFactory, Transformer.NoOp.make());
            }
            throw new IllegalStateException("No possible candidate for super constructor invocation in " + typeDescription.getSuperClass());
        }
    }
}

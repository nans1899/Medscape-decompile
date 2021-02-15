package net.bytebuddy.description.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.FilterableList;

public interface ParameterList<T extends ParameterDescription> extends FilterableList<T, ParameterList<T>> {
    ParameterList<ParameterDescription.InDefinedShape> asDefined();

    ByteCodeElement.Token.TokenList<ParameterDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher);

    TypeList.Generic asTypeList();

    boolean hasExplicitMetaData();

    public static abstract class AbstractBase<S extends ParameterDescription> extends FilterableList.AbstractBase<S, ParameterList<S>> implements ParameterList<S> {
        /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean hasExplicitMetaData() {
            /*
                r3 = this;
                java.util.Iterator r0 = r3.iterator()
            L_0x0004:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x001e
                java.lang.Object r1 = r0.next()
                net.bytebuddy.description.method.ParameterDescription r1 = (net.bytebuddy.description.method.ParameterDescription) r1
                boolean r2 = r1.isNamed()
                if (r2 == 0) goto L_0x001c
                boolean r1 = r1.hasModifiers()
                if (r1 != 0) goto L_0x0004
            L_0x001c:
                r0 = 0
                return r0
            L_0x001e:
                r0 = 1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.method.ParameterList.AbstractBase.hasExplicitMetaData():boolean");
        }

        public ByteCodeElement.Token.TokenList<ParameterDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((ParameterDescription) it.next()).asToken(elementMatcher));
            }
            return new ByteCodeElement.Token.TokenList<>(arrayList);
        }

        public TypeList.Generic asTypeList() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((ParameterDescription) it.next()).getType());
            }
            return new TypeList.Generic.Explicit((List<? extends TypeDefinition>) arrayList);
        }

        public ParameterList<ParameterDescription.InDefinedShape> asDefined() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((ParameterDescription) it.next()).asDefined());
            }
            return new Explicit(arrayList);
        }

        /* access modifiers changed from: protected */
        public ParameterList<S> wrap(List<S> list) {
            return new Explicit(list);
        }
    }

    public static abstract class ForLoadedExecutable<T> extends AbstractBase<ParameterDescription.InDefinedShape> {
        private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        protected final T executable;
        protected final ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource;

        protected ForLoadedExecutable(T t, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource2) {
            this.executable = t;
            this.parameterAnnotationSource = parameterAnnotationSource2;
        }

        public static ParameterList<ParameterDescription.InDefinedShape> of(Constructor<?> constructor) {
            return of(constructor, (ParameterDescription.ForLoadedParameter.ParameterAnnotationSource) new ParameterDescription.ForLoadedParameter.ParameterAnnotationSource.ForLoadedConstructor(constructor));
        }

        public static ParameterList<ParameterDescription.InDefinedShape> of(Constructor<?> constructor, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource2) {
            return DISPATCHER.describe(constructor, parameterAnnotationSource2);
        }

        public static ParameterList<ParameterDescription.InDefinedShape> of(Method method) {
            return of(method, (ParameterDescription.ForLoadedParameter.ParameterAnnotationSource) new ParameterDescription.ForLoadedParameter.ParameterAnnotationSource.ForLoadedMethod(method));
        }

        public static ParameterList<ParameterDescription.InDefinedShape> of(Method method, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource2) {
            return DISPATCHER.describe(method, parameterAnnotationSource2);
        }

        public int size() {
            return DISPATCHER.getParameterCount(this.executable);
        }

        protected interface Dispatcher {
            ParameterList<ParameterDescription.InDefinedShape> describe(Constructor<?> constructor, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource);

            ParameterList<ParameterDescription.InDefinedShape> describe(Method method, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource);

            int getParameterCount(Object obj);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        return new ForJava8CapableVm(Class.forName("java.lang.reflect.Executable").getMethod("getParameterCount", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public int getParameterCount(Object obj) {
                    throw new IllegalStateException("Cannot dispatch method for java.lang.reflect.Executable");
                }

                public ParameterList<ParameterDescription.InDefinedShape> describe(Constructor<?> constructor, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource) {
                    return new OfLegacyVmConstructor(constructor, parameterAnnotationSource);
                }

                public ParameterList<ParameterDescription.InDefinedShape> describe(Method method, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource) {
                    return new OfLegacyVmMethod(method, parameterAnnotationSource);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava8CapableVm implements Dispatcher {
                private static final Object[] NO_ARGUMENTS = new Object[0];
                private final Method getParameterCount;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.getParameterCount.equals(((ForJava8CapableVm) obj).getParameterCount);
                }

                public int hashCode() {
                    return 527 + this.getParameterCount.hashCode();
                }

                protected ForJava8CapableVm(Method method) {
                    this.getParameterCount = method;
                }

                public int getParameterCount(Object obj) {
                    try {
                        return ((Integer) this.getParameterCount.invoke(obj, NO_ARGUMENTS)).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#getModifiers", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#getModifiers", e2.getCause());
                    }
                }

                public ParameterList<ParameterDescription.InDefinedShape> describe(Constructor<?> constructor, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource) {
                    return new OfConstructor(constructor, parameterAnnotationSource);
                }

                public ParameterList<ParameterDescription.InDefinedShape> describe(Method method, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource) {
                    return new OfMethod(method, parameterAnnotationSource);
                }
            }
        }

        protected static class OfConstructor extends ForLoadedExecutable<Constructor<?>> {
            protected OfConstructor(Constructor<?> constructor, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource) {
                super(constructor, parameterAnnotationSource);
            }

            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfConstructor((Constructor) this.executable, i, this.parameterAnnotationSource);
            }
        }

        protected static class OfMethod extends ForLoadedExecutable<Method> {
            protected OfMethod(Method method, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource) {
                super(method, parameterAnnotationSource);
            }

            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfMethod((Method) this.executable, i, this.parameterAnnotationSource);
            }
        }

        protected static class OfLegacyVmConstructor extends AbstractBase<ParameterDescription.InDefinedShape> {
            private final Constructor<?> constructor;
            private final ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource;
            private final Class<?>[] parameterType;

            protected OfLegacyVmConstructor(Constructor<?> constructor2, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource2) {
                this.constructor = constructor2;
                this.parameterType = constructor2.getParameterTypes();
                this.parameterAnnotationSource = parameterAnnotationSource2;
            }

            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfLegacyVmConstructor(this.constructor, i, this.parameterType, this.parameterAnnotationSource);
            }

            public int size() {
                return this.parameterType.length;
            }
        }

        protected static class OfLegacyVmMethod extends AbstractBase<ParameterDescription.InDefinedShape> {
            private final Method method;
            private final ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource;
            private final Class<?>[] parameterType;

            protected OfLegacyVmMethod(Method method2, ParameterDescription.ForLoadedParameter.ParameterAnnotationSource parameterAnnotationSource2) {
                this.method = method2;
                this.parameterType = method2.getParameterTypes();
                this.parameterAnnotationSource = parameterAnnotationSource2;
            }

            public ParameterDescription.InDefinedShape get(int i) {
                return new ParameterDescription.ForLoadedParameter.OfLegacyVmMethod(this.method, i, this.parameterType, this.parameterAnnotationSource);
            }

            public int size() {
                return this.parameterType.length;
            }
        }
    }

    public static class Explicit<S extends ParameterDescription> extends AbstractBase<S> {
        private final List<? extends S> parameterDescriptions;

        public Explicit(S... sArr) {
            this(Arrays.asList(sArr));
        }

        public Explicit(List<? extends S> list) {
            this.parameterDescriptions = list;
        }

        public S get(int i) {
            return (ParameterDescription) this.parameterDescriptions.get(i);
        }

        public int size() {
            return this.parameterDescriptions.size();
        }

        public static class ForTypes extends AbstractBase<ParameterDescription.InDefinedShape> {
            private final MethodDescription.InDefinedShape methodDescription;
            private final List<? extends TypeDefinition> typeDefinitions;

            public ForTypes(MethodDescription.InDefinedShape inDefinedShape, TypeDefinition... typeDefinitionArr) {
                this(inDefinedShape, (List<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            public ForTypes(MethodDescription.InDefinedShape inDefinedShape, List<? extends TypeDefinition> list) {
                this.methodDescription = inDefinedShape;
                this.typeDefinitions = list;
            }

            public ParameterDescription.InDefinedShape get(int i) {
                int i2 = !this.methodDescription.isStatic();
                for (int i3 = 0; i3 < i; i3++) {
                    i2 += ((TypeDefinition) this.typeDefinitions.get(i3)).getStackSize().getSize();
                }
                return new ParameterDescription.Latent(this.methodDescription, ((TypeDefinition) this.typeDefinitions.get(i)).asGenericType(), i, i2);
            }

            public int size() {
                return this.typeDefinitions.size();
            }
        }
    }

    public static class ForTokens extends AbstractBase<ParameterDescription.InDefinedShape> {
        private final MethodDescription.InDefinedShape declaringMethod;
        private final List<? extends ParameterDescription.Token> tokens;

        public ForTokens(MethodDescription.InDefinedShape inDefinedShape, List<? extends ParameterDescription.Token> list) {
            this.declaringMethod = inDefinedShape;
            this.tokens = list;
        }

        public ParameterDescription.InDefinedShape get(int i) {
            int i2 = !this.declaringMethod.isStatic();
            for (ParameterDescription.Token type : this.tokens.subList(0, i)) {
                i2 += type.getType().getStackSize().getSize();
            }
            return new ParameterDescription.Latent(this.declaringMethod, (ParameterDescription.Token) this.tokens.get(i), i, i2);
        }

        public int size() {
            return this.tokens.size();
        }
    }

    public static class TypeSubstituting extends AbstractBase<ParameterDescription.InGenericShape> {
        private final MethodDescription.InGenericShape declaringMethod;
        private final List<? extends ParameterDescription> parameterDescriptions;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(MethodDescription.InGenericShape inGenericShape, List<? extends ParameterDescription> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
            this.declaringMethod = inGenericShape;
            this.parameterDescriptions = list;
            this.visitor = visitor2;
        }

        public ParameterDescription.InGenericShape get(int i) {
            return new ParameterDescription.TypeSubstituting(this.declaringMethod, (ParameterDescription) this.parameterDescriptions.get(i), this.visitor);
        }

        public int size() {
            return this.parameterDescriptions.size();
        }
    }

    public static class Empty<S extends ParameterDescription> extends FilterableList.Empty<S, ParameterList<S>> implements ParameterList<S> {
        public ParameterList<ParameterDescription.InDefinedShape> asDefined() {
            return this;
        }

        public boolean hasExplicitMetaData() {
            return true;
        }

        public TypeList.Generic asTypeList() {
            return new TypeList.Generic.Empty();
        }

        public ByteCodeElement.Token.TokenList<ParameterDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new ByteCodeElement.Token.TokenList<>((S[]) new ParameterDescription.Token[0]);
        }
    }
}

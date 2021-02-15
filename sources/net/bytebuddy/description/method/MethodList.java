package net.bytebuddy.description.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.FilterableList;

public interface MethodList<T extends MethodDescription> extends FilterableList<T, MethodList<T>> {
    MethodList<MethodDescription.InDefinedShape> asDefined();

    ByteCodeElement.Token.TokenList<MethodDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher);

    public static abstract class AbstractBase<S extends MethodDescription> extends FilterableList.AbstractBase<S, MethodList<S>> implements MethodList<S> {
        /* access modifiers changed from: protected */
        public MethodList<S> wrap(List<S> list) {
            return new Explicit(list);
        }

        public ByteCodeElement.Token.TokenList<MethodDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((MethodDescription) it.next()).asToken(elementMatcher));
            }
            return new ByteCodeElement.Token.TokenList<>(arrayList);
        }

        public MethodList<MethodDescription.InDefinedShape> asDefined() {
            ArrayList arrayList = new ArrayList(size());
            Iterator it = iterator();
            while (it.hasNext()) {
                arrayList.add(((MethodDescription) it.next()).asDefined());
            }
            return new Explicit(arrayList);
        }
    }

    public static class ForLoadedMethods extends AbstractBase<MethodDescription.InDefinedShape> {
        private final List<? extends Constructor<?>> constructors;
        private final List<? extends Method> methods;

        public ForLoadedMethods(Class<?> cls) {
            this((Constructor<?>[]) cls.getDeclaredConstructors(), cls.getDeclaredMethods());
        }

        public ForLoadedMethods(Constructor<?>[] constructorArr, Method[] methodArr) {
            this((List<? extends Constructor<?>>) Arrays.asList(constructorArr), (List<? extends Method>) Arrays.asList(methodArr));
        }

        public ForLoadedMethods(List<? extends Constructor<?>> list, List<? extends Method> list2) {
            this.constructors = list;
            this.methods = list2;
        }

        public MethodDescription.InDefinedShape get(int i) {
            if (i < this.constructors.size()) {
                return new MethodDescription.ForLoadedConstructor((Constructor) this.constructors.get(i));
            }
            return new MethodDescription.ForLoadedMethod((Method) this.methods.get(i - this.constructors.size()));
        }

        public int size() {
            return this.constructors.size() + this.methods.size();
        }
    }

    public static class Explicit<S extends MethodDescription> extends AbstractBase<S> {
        private final List<? extends S> methodDescriptions;

        public Explicit(S... sArr) {
            this(Arrays.asList(sArr));
        }

        public Explicit(List<? extends S> list) {
            this.methodDescriptions = list;
        }

        public S get(int i) {
            return (MethodDescription) this.methodDescriptions.get(i);
        }

        public int size() {
            return this.methodDescriptions.size();
        }
    }

    public static class ForTokens extends AbstractBase<MethodDescription.InDefinedShape> {
        private final TypeDescription declaringType;
        private final List<? extends MethodDescription.Token> tokens;

        public ForTokens(TypeDescription typeDescription, MethodDescription.Token... tokenArr) {
            this(typeDescription, (List<? extends MethodDescription.Token>) Arrays.asList(tokenArr));
        }

        public ForTokens(TypeDescription typeDescription, List<? extends MethodDescription.Token> list) {
            this.declaringType = typeDescription;
            this.tokens = list;
        }

        public MethodDescription.InDefinedShape get(int i) {
            return new MethodDescription.Latent(this.declaringType, (MethodDescription.Token) this.tokens.get(i));
        }

        public int size() {
            return this.tokens.size();
        }
    }

    public static class TypeSubstituting extends AbstractBase<MethodDescription.InGenericShape> {
        protected final TypeDescription.Generic declaringType;
        protected final List<? extends MethodDescription> methodDescriptions;
        protected final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, List<? extends MethodDescription> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
            this.declaringType = generic;
            this.methodDescriptions = list;
            this.visitor = visitor2;
        }

        public MethodDescription.InGenericShape get(int i) {
            return new MethodDescription.TypeSubstituting(this.declaringType, (MethodDescription) this.methodDescriptions.get(i), this.visitor);
        }

        public int size() {
            return this.methodDescriptions.size();
        }
    }

    public static class Empty<S extends MethodDescription> extends FilterableList.Empty<S, MethodList<S>> implements MethodList<S> {
        public MethodList<MethodDescription.InDefinedShape> asDefined() {
            return this;
        }

        public ByteCodeElement.Token.TokenList<MethodDescription.Token> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new ByteCodeElement.Token.TokenList<>((S[]) new MethodDescription.Token[0]);
        }
    }
}

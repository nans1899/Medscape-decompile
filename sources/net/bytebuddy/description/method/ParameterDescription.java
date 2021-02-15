package net.bytebuddy.description.method;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.AbstractList;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.matcher.ElementMatcher;

public interface ParameterDescription extends AnnotationSource, NamedElement.WithRuntimeName, NamedElement.WithOptionalName, ModifierReviewable.ForParameterDescription, ByteCodeElement.TypeDependant<InDefinedShape, Token> {
    public static final String NAME_PREFIX = "arg";

    public interface InDefinedShape extends ParameterDescription {

        public static abstract class AbstractBase extends AbstractBase implements InDefinedShape {
            public InDefinedShape asDefined() {
                return this;
            }
        }

        MethodDescription.InDefinedShape getDeclaringMethod();
    }

    public interface InGenericShape extends ParameterDescription {
        MethodDescription.InGenericShape getDeclaringMethod();
    }

    MethodDescription getDeclaringMethod();

    int getIndex();

    int getOffset();

    TypeDescription.Generic getType();

    boolean hasModifiers();

    public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements ParameterDescription {
        public int getModifiers() {
            return 0;
        }

        public String getName() {
            return ParameterDescription.NAME_PREFIX.concat(String.valueOf(getIndex()));
        }

        public String getInternalName() {
            return getName();
        }

        public String getActualName() {
            return isNamed() ? getName() : "";
        }

        public int getOffset() {
            int i;
            TypeList asErasures = getDeclaringMethod().getParameters().asTypeList().asErasures();
            if (getDeclaringMethod().isStatic()) {
                i = StackSize.ZERO.getSize();
            } else {
                i = StackSize.SINGLE.getSize();
            }
            for (int i2 = 0; i2 < getIndex(); i2++) {
                i += ((TypeDescription) asErasures.get(i2)).getStackSize().getSize();
            }
            return i;
        }

        public Token asToken(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new Token((TypeDescription.Generic) getType().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getDeclaredAnnotations(), isNamed() ? getName() : Token.NO_NAME, hasModifiers() ? Integer.valueOf(getModifiers()) : Token.NO_MODIFIERS);
        }

        public int hashCode() {
            return getDeclaringMethod().hashCode() ^ getIndex();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ParameterDescription)) {
                return false;
            }
            ParameterDescription parameterDescription = (ParameterDescription) obj;
            if (!getDeclaringMethod().equals(parameterDescription.getDeclaringMethod()) || getIndex() != parameterDescription.getIndex()) {
                return false;
            }
            return true;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder(Modifier.toString(getModifiers()));
            if (getModifiers() != 0) {
                sb.append(' ');
            }
            if (isVarArgs()) {
                str = getType().asErasure().getName().replaceFirst("\\[\\]$", "...");
            } else {
                str = getType().asErasure().getName();
            }
            sb.append(str);
            sb.append(' ');
            sb.append(getName());
            return sb.toString();
        }
    }

    public static abstract class ForLoadedParameter<T extends AccessibleObject> extends InDefinedShape.AbstractBase {
        private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        protected final T executable;
        protected final int index;
        protected final ParameterAnnotationSource parameterAnnotationSource;

        protected ForLoadedParameter(T t, int i, ParameterAnnotationSource parameterAnnotationSource2) {
            this.executable = t;
            this.index = i;
            this.parameterAnnotationSource = parameterAnnotationSource2;
        }

        public String getName() {
            return DISPATCHER.getName(this.executable, this.index);
        }

        public int getIndex() {
            return this.index;
        }

        public boolean isNamed() {
            return DISPATCHER.isNamePresent(this.executable, this.index);
        }

        public int getModifiers() {
            return DISPATCHER.getModifiers(this.executable, this.index);
        }

        public boolean hasModifiers() {
            return isNamed() || getModifiers() != 0;
        }

        public interface ParameterAnnotationSource {
            Annotation[][] getParameterAnnotations();

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForLoadedConstructor implements ParameterAnnotationSource {
                private final Constructor<?> constructor;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.constructor.equals(((ForLoadedConstructor) obj).constructor);
                }

                public int hashCode() {
                    return 527 + this.constructor.hashCode();
                }

                public ForLoadedConstructor(Constructor<?> constructor2) {
                    this.constructor = constructor2;
                }

                public Annotation[][] getParameterAnnotations() {
                    return this.constructor.getParameterAnnotations();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForLoadedMethod implements ParameterAnnotationSource {
                private final Method method;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.method.equals(((ForLoadedMethod) obj).method);
                }

                public int hashCode() {
                    return 527 + this.method.hashCode();
                }

                public ForLoadedMethod(Method method2) {
                    this.method = method2;
                }

                public Annotation[][] getParameterAnnotations() {
                    return this.method.getParameterAnnotations();
                }
            }
        }

        protected interface Dispatcher {
            int getModifiers(AccessibleObject accessibleObject, int i);

            String getName(AccessibleObject accessibleObject, int i);

            boolean isNamePresent(AccessibleObject accessibleObject, int i);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        Class<?> cls = Class.forName("java.lang.reflect.Executable");
                        Class<?> cls2 = Class.forName("java.lang.reflect.Parameter");
                        return new ForJava8CapableVm(cls.getMethod("getParameters", new Class[0]), cls2.getMethod("getName", new Class[0]), cls2.getMethod("isNamePresent", new Class[0]), cls2.getMethod("getModifiers", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava8CapableVm implements Dispatcher {
                private static final Object[] NO_ARGUMENTS = new Object[0];
                private final Method getModifiers;
                private final Method getName;
                private final Method getParameters;
                private final Method isNamePresent;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava8CapableVm forJava8CapableVm = (ForJava8CapableVm) obj;
                    return this.getParameters.equals(forJava8CapableVm.getParameters) && this.getName.equals(forJava8CapableVm.getName) && this.isNamePresent.equals(forJava8CapableVm.isNamePresent) && this.getModifiers.equals(forJava8CapableVm.getModifiers);
                }

                public int hashCode() {
                    return ((((((527 + this.getParameters.hashCode()) * 31) + this.getName.hashCode()) * 31) + this.isNamePresent.hashCode()) * 31) + this.getModifiers.hashCode();
                }

                protected ForJava8CapableVm(Method method, Method method2, Method method3, Method method4) {
                    this.getParameters = method;
                    this.getName = method2;
                    this.isNamePresent = method3;
                    this.getModifiers = method4;
                }

                public int getModifiers(AccessibleObject accessibleObject, int i) {
                    try {
                        return ((Integer) this.getModifiers.invoke(getParameter(accessibleObject, i), NO_ARGUMENTS)).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#getModifiers", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#getModifiers", e2.getCause());
                    }
                }

                public boolean isNamePresent(AccessibleObject accessibleObject, int i) {
                    try {
                        return ((Boolean) this.isNamePresent.invoke(getParameter(accessibleObject, i), NO_ARGUMENTS)).booleanValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#isNamePresent", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#isNamePresent", e2.getCause());
                    }
                }

                public String getName(AccessibleObject accessibleObject, int i) {
                    try {
                        return (String) this.getName.invoke(getParameter(accessibleObject, i), NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Parameter#getName", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Parameter#getName", e2.getCause());
                    }
                }

                private Object getParameter(AccessibleObject accessibleObject, int i) {
                    try {
                        return Array.get(this.getParameters.invoke(accessibleObject, NO_ARGUMENTS), i);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getParameters", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getParameters", e2.getCause());
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public int getModifiers(AccessibleObject accessibleObject, int i) {
                    throw new UnsupportedOperationException("Cannot dispatch method for java.lang.reflect.Parameter");
                }

                public boolean isNamePresent(AccessibleObject accessibleObject, int i) {
                    throw new UnsupportedOperationException("Cannot dispatch method for java.lang.reflect.Parameter");
                }

                public String getName(AccessibleObject accessibleObject, int i) {
                    throw new UnsupportedOperationException("Cannot dispatch method for java.lang.reflect.Parameter");
                }
            }
        }

        protected static class OfMethod extends ForLoadedParameter<Method> {
            protected OfMethod(Method method, int i, ParameterAnnotationSource parameterAnnotationSource) {
                super(method, i, parameterAnnotationSource);
            }

            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedMethod((Method) this.executable);
            }

            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(((Method) this.executable).getParameterTypes()[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfMethodParameter((Method) this.executable, this.index, ((Method) this.executable).getParameterTypes());
            }

            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.ForLoadedAnnotations(this.parameterAnnotationSource.getParameterAnnotations()[this.index]);
            }
        }

        protected static class OfConstructor extends ForLoadedParameter<Constructor<?>> {
            protected OfConstructor(Constructor<?> constructor, int i, ParameterAnnotationSource parameterAnnotationSource) {
                super(constructor, i, parameterAnnotationSource);
            }

            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedConstructor((Constructor) this.executable);
            }

            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(((Constructor) this.executable).getParameterTypes()[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfConstructorParameter((Constructor) this.executable, this.index, ((Constructor) this.executable).getParameterTypes());
            }

            public AnnotationList getDeclaredAnnotations() {
                Annotation[][] parameterAnnotations = this.parameterAnnotationSource.getParameterAnnotations();
                MethodDescription.InDefinedShape declaringMethod = getDeclaringMethod();
                if (parameterAnnotations.length == declaringMethod.getParameters().size() || !declaringMethod.getDeclaringType().isInnerClass()) {
                    return new AnnotationList.ForLoadedAnnotations(parameterAnnotations[this.index]);
                }
                return this.index == 0 ? new AnnotationList.Empty() : new AnnotationList.ForLoadedAnnotations(parameterAnnotations[this.index - 1]);
            }
        }

        protected static class OfLegacyVmMethod extends InDefinedShape.AbstractBase {
            private final int index;
            private final Method method;
            private final ParameterAnnotationSource parameterAnnotationSource;
            private final Class<?>[] parameterType;

            public boolean hasModifiers() {
                return false;
            }

            public boolean isNamed() {
                return false;
            }

            protected OfLegacyVmMethod(Method method2, int i, Class<?>[] clsArr, ParameterAnnotationSource parameterAnnotationSource2) {
                this.method = method2;
                this.index = i;
                this.parameterType = clsArr;
                this.parameterAnnotationSource = parameterAnnotationSource2;
            }

            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(this.parameterType[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfMethodParameter(this.method, this.index, this.parameterType);
            }

            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedMethod(this.method);
            }

            public int getIndex() {
                return this.index;
            }

            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.ForLoadedAnnotations(this.parameterAnnotationSource.getParameterAnnotations()[this.index]);
            }
        }

        protected static class OfLegacyVmConstructor extends InDefinedShape.AbstractBase {
            private final Constructor<?> constructor;
            private final int index;
            private final ParameterAnnotationSource parameterAnnotationSource;
            private final Class<?>[] parameterType;

            public boolean hasModifiers() {
                return false;
            }

            public boolean isNamed() {
                return false;
            }

            protected OfLegacyVmConstructor(Constructor<?> constructor2, int i, Class<?>[] clsArr, ParameterAnnotationSource parameterAnnotationSource2) {
                this.constructor = constructor2;
                this.index = i;
                this.parameterType = clsArr;
                this.parameterAnnotationSource = parameterAnnotationSource2;
            }

            public TypeDescription.Generic getType() {
                if (TypeDescription.AbstractBase.RAW_TYPES) {
                    return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(this.parameterType[this.index]);
                }
                return new TypeDescription.Generic.LazyProjection.OfConstructorParameter(this.constructor, this.index, this.parameterType);
            }

            public MethodDescription.InDefinedShape getDeclaringMethod() {
                return new MethodDescription.ForLoadedConstructor(this.constructor);
            }

            public int getIndex() {
                return this.index;
            }

            public AnnotationList getDeclaredAnnotations() {
                MethodDescription.InDefinedShape declaringMethod = getDeclaringMethod();
                Annotation[][] parameterAnnotations = this.parameterAnnotationSource.getParameterAnnotations();
                if (parameterAnnotations.length == declaringMethod.getParameters().size() || !declaringMethod.getDeclaringType().isInnerClass()) {
                    return new AnnotationList.ForLoadedAnnotations(parameterAnnotations[this.index]);
                }
                return this.index == 0 ? new AnnotationList.Empty() : new AnnotationList.ForLoadedAnnotations(parameterAnnotations[this.index - 1]);
            }
        }
    }

    public static class Latent extends InDefinedShape.AbstractBase {
        private final List<? extends AnnotationDescription> declaredAnnotations;
        private final MethodDescription.InDefinedShape declaringMethod;
        private final int index;
        private final Integer modifiers;
        private final String name;
        private final int offset;
        private final TypeDescription.Generic parameterType;

        public Latent(MethodDescription.InDefinedShape inDefinedShape, Token token, int i, int i2) {
            this(inDefinedShape, token.getType(), token.getAnnotations(), token.getName(), token.getModifiers(), i, i2);
        }

        public Latent(MethodDescription.InDefinedShape inDefinedShape, TypeDescription.Generic generic, int i, int i2) {
            this(inDefinedShape, generic, Collections.emptyList(), Token.NO_NAME, Token.NO_MODIFIERS, i, i2);
        }

        public Latent(MethodDescription.InDefinedShape inDefinedShape, TypeDescription.Generic generic, List<? extends AnnotationDescription> list, String str, Integer num, int i, int i2) {
            this.declaringMethod = inDefinedShape;
            this.parameterType = generic;
            this.declaredAnnotations = list;
            this.name = str;
            this.modifiers = num;
            this.index = i;
            this.offset = i2;
        }

        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.parameterType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of((ParameterDescription) this));
        }

        public MethodDescription.InDefinedShape getDeclaringMethod() {
            return this.declaringMethod;
        }

        public int getIndex() {
            return this.index;
        }

        public int getOffset() {
            return this.offset;
        }

        public boolean isNamed() {
            return this.name != null;
        }

        public boolean hasModifiers() {
            return this.modifiers != null;
        }

        public String getName() {
            if (isNamed()) {
                return this.name;
            }
            return super.getName();
        }

        public int getModifiers() {
            if (hasModifiers()) {
                return this.modifiers.intValue();
            }
            return super.getModifiers();
        }

        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.declaredAnnotations);
        }
    }

    public static class TypeSubstituting extends AbstractBase implements InGenericShape {
        private final MethodDescription.InGenericShape declaringMethod;
        private final ParameterDescription parameterDescription;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(MethodDescription.InGenericShape inGenericShape, ParameterDescription parameterDescription2, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
            this.declaringMethod = inGenericShape;
            this.parameterDescription = parameterDescription2;
            this.visitor = visitor2;
        }

        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.parameterDescription.getType().accept(this.visitor);
        }

        public MethodDescription.InGenericShape getDeclaringMethod() {
            return this.declaringMethod;
        }

        public int getIndex() {
            return this.parameterDescription.getIndex();
        }

        public boolean isNamed() {
            return this.parameterDescription.isNamed();
        }

        public boolean hasModifiers() {
            return this.parameterDescription.hasModifiers();
        }

        public int getOffset() {
            return this.parameterDescription.getOffset();
        }

        public String getName() {
            return this.parameterDescription.getName();
        }

        public int getModifiers() {
            return this.parameterDescription.getModifiers();
        }

        public AnnotationList getDeclaredAnnotations() {
            return this.parameterDescription.getDeclaredAnnotations();
        }

        public InDefinedShape asDefined() {
            return (InDefinedShape) this.parameterDescription.asDefined();
        }
    }

    public static class Token implements ByteCodeElement.Token<Token> {
        public static final Integer NO_MODIFIERS = null;
        public static final String NO_NAME = null;
        private final List<? extends AnnotationDescription> annotations;
        private final Integer modifiers;
        private final String name;
        private final TypeDescription.Generic type;

        public Token(TypeDescription.Generic generic) {
            this(generic, Collections.emptyList());
        }

        public Token(TypeDescription.Generic generic, List<? extends AnnotationDescription> list) {
            this(generic, list, NO_NAME, NO_MODIFIERS);
        }

        public Token(TypeDescription.Generic generic, String str, Integer num) {
            this(generic, Collections.emptyList(), str, num);
        }

        public Token(TypeDescription.Generic generic, List<? extends AnnotationDescription> list, String str, Integer num) {
            this.type = generic;
            this.annotations = list;
            this.name = str;
            this.modifiers = num;
        }

        public TypeDescription.Generic getType() {
            return this.type;
        }

        public AnnotationList getAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }

        public String getName() {
            return this.name;
        }

        public Integer getModifiers() {
            return this.modifiers;
        }

        public Token accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            return new Token((TypeDescription.Generic) this.type.accept(visitor), this.annotations, this.name, this.modifiers);
        }

        public int hashCode() {
            int hashCode = ((this.type.hashCode() * 31) + this.annotations.hashCode()) * 31;
            String str = this.name;
            int i = 0;
            int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
            Integer num = this.modifiers;
            if (num != null) {
                i = num.hashCode();
            }
            return hashCode2 + i;
        }

        public boolean equals(Object obj) {
            String str;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.type.equals(token.type) && this.annotations.equals(token.annotations) && ((str = this.name) == null ? token.name == null : str.equals(token.name))) {
                Integer num = this.modifiers;
                if (num != null) {
                    if (num.equals(token.modifiers)) {
                        return true;
                    }
                } else if (token.modifiers == null) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "ParameterDescription.Token{type=" + this.type + ", annotations=" + this.annotations + ", name='" + this.name + '\'' + ", modifiers=" + this.modifiers + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        }

        public static class TypeList extends AbstractList<Token> {
            private final List<? extends TypeDefinition> typeDescriptions;

            public TypeList(List<? extends TypeDefinition> list) {
                this.typeDescriptions = list;
            }

            public Token get(int i) {
                return new Token(((TypeDefinition) this.typeDescriptions.get(i)).asGenericType());
            }

            public int size() {
                return this.typeDescriptions.size();
            }
        }
    }
}

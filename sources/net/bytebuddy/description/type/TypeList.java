package net.bytebuddy.description.type;

import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.FilterableList;

public interface TypeList extends FilterableList<TypeDescription, TypeList> {
    public static final String[] NO_INTERFACES = null;

    int getStackSize();

    String[] toInternalNames();

    public static abstract class AbstractBase extends FilterableList.AbstractBase<TypeDescription, TypeList> implements TypeList {
        /* access modifiers changed from: protected */
        public TypeList wrap(List<TypeDescription> list) {
            return new Explicit((List<? extends TypeDescription>) list);
        }

        public int getStackSize() {
            return StackSize.of((Collection<? extends TypeDefinition>) this);
        }

        public String[] toInternalNames() {
            int size = size();
            String[] strArr = new String[size];
            Iterator it = iterator();
            int i = 0;
            while (it.hasNext()) {
                strArr[i] = ((TypeDescription) it.next()).getInternalName();
                i++;
            }
            return size == 0 ? NO_INTERFACES : strArr;
        }
    }

    public static class ForLoadedTypes extends AbstractBase {
        private final List<? extends Class<?>> types;

        public ForLoadedTypes(Class<?>... clsArr) {
            this((List<? extends Class<?>>) Arrays.asList(clsArr));
        }

        public ForLoadedTypes(List<? extends Class<?>> list) {
            this.types = list;
        }

        public TypeDescription get(int i) {
            return TypeDescription.ForLoadedType.of((Class) this.types.get(i));
        }

        public int size() {
            return this.types.size();
        }

        public String[] toInternalNames() {
            int size = this.types.size();
            String[] strArr = new String[size];
            int i = 0;
            for (Class internalName : this.types) {
                strArr[i] = Type.getInternalName(internalName);
                i++;
            }
            return size == 0 ? NO_INTERFACES : strArr;
        }
    }

    public static class Explicit extends AbstractBase {
        private final List<? extends TypeDescription> typeDescriptions;

        public Explicit(TypeDescription... typeDescriptionArr) {
            this((List<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
        }

        public Explicit(List<? extends TypeDescription> list) {
            this.typeDescriptions = list;
        }

        public TypeDescription get(int i) {
            return (TypeDescription) this.typeDescriptions.get(i);
        }

        public int size() {
            return this.typeDescriptions.size();
        }
    }

    public static class Empty extends FilterableList.Empty<TypeDescription, TypeList> implements TypeList {
        public int getStackSize() {
            return 0;
        }

        public String[] toInternalNames() {
            return NO_INTERFACES;
        }
    }

    public interface Generic extends FilterableList<TypeDescription.Generic, Generic> {
        Generic accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor);

        TypeList asErasures();

        Generic asRawTypes();

        ByteCodeElement.Token.TokenList<TypeVariableToken> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher);

        int getStackSize();

        public static abstract class AbstractBase extends FilterableList.AbstractBase<TypeDescription.Generic, Generic> implements Generic {
            /* access modifiers changed from: protected */
            public Generic wrap(List<TypeDescription.Generic> list) {
                return new Explicit((List<? extends TypeDefinition>) list);
            }

            public Generic accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
                ArrayList arrayList = new ArrayList(size());
                Iterator it = iterator();
                while (it.hasNext()) {
                    arrayList.add(((TypeDescription.Generic) it.next()).accept(visitor));
                }
                return new Explicit((List<? extends TypeDefinition>) arrayList);
            }

            public ByteCodeElement.Token.TokenList<TypeVariableToken> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
                ArrayList arrayList = new ArrayList(size());
                Iterator it = iterator();
                while (it.hasNext()) {
                    arrayList.add(TypeVariableToken.of((TypeDescription.Generic) it.next(), elementMatcher));
                }
                return new ByteCodeElement.Token.TokenList<>(arrayList);
            }

            public int getStackSize() {
                Iterator it = iterator();
                int i = 0;
                while (it.hasNext()) {
                    i += ((TypeDescription.Generic) it.next()).getStackSize().getSize();
                }
                return i;
            }

            public TypeList asErasures() {
                ArrayList arrayList = new ArrayList(size());
                Iterator it = iterator();
                while (it.hasNext()) {
                    arrayList.add(((TypeDescription.Generic) it.next()).asErasure());
                }
                return new Explicit((List<? extends TypeDescription>) arrayList);
            }

            public Generic asRawTypes() {
                ArrayList arrayList = new ArrayList(size());
                Iterator it = iterator();
                while (it.hasNext()) {
                    arrayList.add(((TypeDescription.Generic) it.next()).asRawType());
                }
                return new Explicit((List<? extends TypeDefinition>) arrayList);
            }
        }

        public static class Explicit extends AbstractBase {
            private final List<? extends TypeDefinition> typeDefinitions;

            public Explicit(TypeDefinition... typeDefinitionArr) {
                this((List<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            public Explicit(List<? extends TypeDefinition> list) {
                this.typeDefinitions = list;
            }

            public TypeDescription.Generic get(int i) {
                return ((TypeDefinition) this.typeDefinitions.get(i)).asGenericType();
            }

            public int size() {
                return this.typeDefinitions.size();
            }
        }

        public static class ForLoadedTypes extends AbstractBase {
            private final List<? extends java.lang.reflect.Type> types;

            public ForLoadedTypes(java.lang.reflect.Type... typeArr) {
                this((List<? extends java.lang.reflect.Type>) Arrays.asList(typeArr));
            }

            public ForLoadedTypes(List<? extends java.lang.reflect.Type> list) {
                this.types = list;
            }

            public TypeDescription.Generic get(int i) {
                return TypeDefinition.Sort.describe((java.lang.reflect.Type) this.types.get(i));
            }

            public int size() {
                return this.types.size();
            }

            public static class OfTypeVariables extends AbstractBase {
                private final List<TypeVariable<?>> typeVariables;

                protected OfTypeVariables(TypeVariable<?>... typeVariableArr) {
                    this((List<TypeVariable<?>>) Arrays.asList(typeVariableArr));
                }

                protected OfTypeVariables(List<TypeVariable<?>> list) {
                    this.typeVariables = list;
                }

                public static Generic of(GenericDeclaration genericDeclaration) {
                    return new OfTypeVariables((TypeVariable<?>[]) genericDeclaration.getTypeParameters());
                }

                public TypeDescription.Generic get(int i) {
                    TypeVariable typeVariable = this.typeVariables.get(i);
                    return TypeDefinition.Sort.describe(typeVariable, TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveTypeVariable(typeVariable));
                }

                public int size() {
                    return this.typeVariables.size();
                }
            }
        }

        public static class ForDetachedTypes extends AbstractBase {
            private final List<? extends TypeDescription.Generic> detachedTypes;
            private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

            public ForDetachedTypes(List<? extends TypeDescription.Generic> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
                this.detachedTypes = list;
                this.visitor = visitor2;
            }

            public static Generic attachVariables(TypeDescription typeDescription, List<? extends TypeVariableToken> list) {
                return new OfTypeVariables(typeDescription, list, TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(typeDescription));
            }

            public static Generic attach(FieldDescription fieldDescription, List<? extends TypeDescription.Generic> list) {
                return new ForDetachedTypes(list, TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(fieldDescription));
            }

            public static Generic attach(MethodDescription methodDescription, List<? extends TypeDescription.Generic> list) {
                return new ForDetachedTypes(list, TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(methodDescription));
            }

            public static Generic attachVariables(MethodDescription methodDescription, List<? extends TypeVariableToken> list) {
                return new OfTypeVariables(methodDescription, list, TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(methodDescription));
            }

            public static Generic attach(ParameterDescription parameterDescription, List<? extends TypeDescription.Generic> list) {
                return new ForDetachedTypes(list, TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of(parameterDescription));
            }

            public TypeDescription.Generic get(int i) {
                return (TypeDescription.Generic) ((TypeDescription.Generic) this.detachedTypes.get(i)).accept(this.visitor);
            }

            public int size() {
                return this.detachedTypes.size();
            }

            public static class WithResolvedErasure extends AbstractBase {
                private final List<? extends TypeDescription.Generic> detachedTypes;
                private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

                public WithResolvedErasure(List<? extends TypeDescription.Generic> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
                    this.detachedTypes = list;
                    this.visitor = visitor2;
                }

                public TypeDescription.Generic get(int i) {
                    return new TypeDescription.Generic.LazyProjection.WithResolvedErasure((TypeDescription.Generic) this.detachedTypes.get(i), this.visitor);
                }

                public int size() {
                    return this.detachedTypes.size();
                }
            }

            public static class OfTypeVariables extends AbstractBase {
                private final List<? extends TypeVariableToken> detachedTypeVariables;
                private final TypeVariableSource typeVariableSource;
                private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

                public OfTypeVariables(TypeVariableSource typeVariableSource2, List<? extends TypeVariableToken> list, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
                    this.typeVariableSource = typeVariableSource2;
                    this.detachedTypeVariables = list;
                    this.visitor = visitor2;
                }

                public TypeDescription.Generic get(int i) {
                    return new AttachedTypeVariable(this.typeVariableSource, (TypeVariableToken) this.detachedTypeVariables.get(i), this.visitor);
                }

                public int size() {
                    return this.detachedTypeVariables.size();
                }

                protected static class AttachedTypeVariable extends TypeDescription.Generic.OfTypeVariable {
                    private final TypeVariableSource typeVariableSource;
                    private final TypeVariableToken typeVariableToken;
                    private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

                    protected AttachedTypeVariable(TypeVariableSource typeVariableSource2, TypeVariableToken typeVariableToken2, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
                        this.typeVariableSource = typeVariableSource2;
                        this.typeVariableToken = typeVariableToken2;
                        this.visitor = visitor2;
                    }

                    public Generic getUpperBounds() {
                        return this.typeVariableToken.getBounds().accept(this.visitor);
                    }

                    public TypeVariableSource getTypeVariableSource() {
                        return this.typeVariableSource;
                    }

                    public String getSymbol() {
                        return this.typeVariableToken.getSymbol();
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return this.typeVariableToken.getAnnotations();
                    }
                }
            }
        }

        public static class OfLoadedInterfaceTypes extends AbstractBase {
            private final Class<?> type;

            public OfLoadedInterfaceTypes(Class<?> cls) {
                this.type = cls;
            }

            public TypeDescription.Generic get(int i) {
                Class<?> cls = this.type;
                return new TypeProjection(cls, i, cls.getInterfaces());
            }

            public int size() {
                return this.type.getInterfaces().length;
            }

            public TypeList asErasures() {
                return new ForLoadedTypes((Class<?>[]) this.type.getInterfaces());
            }

            private static class TypeProjection extends TypeDescription.Generic.LazyProjection.WithLazyNavigation.OfAnnotatedElement {
                private final Class<?>[] erasure;
                private final int index;
                private transient /* synthetic */ TypeDescription.Generic resolved;
                private final Class<?> type;

                private TypeProjection(Class<?> cls, int i, Class<?>[] clsArr) {
                    this.type = cls;
                    this.index = i;
                    this.erasure = clsArr;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public TypeDescription.Generic resolve() {
                    TypeDescription.Generic generic;
                    if (this.resolved != null) {
                        generic = null;
                    } else {
                        java.lang.reflect.Type[] genericInterfaces = this.type.getGenericInterfaces();
                        if (this.erasure.length == genericInterfaces.length) {
                            generic = TypeDefinition.Sort.describe(genericInterfaces[this.index], getAnnotationReader());
                        } else {
                            generic = asRawType();
                        }
                    }
                    if (generic == null) {
                        return this.resolved;
                    }
                    this.resolved = generic;
                    return generic;
                }

                public TypeDescription asErasure() {
                    return TypeDescription.ForLoadedType.of(this.erasure[this.index]);
                }

                /* access modifiers changed from: protected */
                public TypeDescription.Generic.AnnotationReader getAnnotationReader() {
                    return TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveInterfaceType(this.type, this.index);
                }
            }
        }

        public static class OfConstructorExceptionTypes extends AbstractBase {
            private final Constructor<?> constructor;

            public OfConstructorExceptionTypes(Constructor<?> constructor2) {
                this.constructor = constructor2;
            }

            public TypeDescription.Generic get(int i) {
                Constructor<?> constructor2 = this.constructor;
                return new TypeProjection(constructor2, i, constructor2.getExceptionTypes());
            }

            public int size() {
                return this.constructor.getExceptionTypes().length;
            }

            public TypeList asErasures() {
                return new ForLoadedTypes((Class<?>[]) this.constructor.getExceptionTypes());
            }

            private static class TypeProjection extends TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement {
                private final Constructor<?> constructor;
                private final Class<?>[] erasure;
                private final int index;
                private transient /* synthetic */ TypeDescription.Generic resolved;

                private TypeProjection(Constructor<?> constructor2, int i, Class<?>[] clsArr) {
                    this.constructor = constructor2;
                    this.index = i;
                    this.erasure = clsArr;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public TypeDescription.Generic resolve() {
                    TypeDescription.Generic generic;
                    if (this.resolved != null) {
                        generic = null;
                    } else {
                        java.lang.reflect.Type[] genericExceptionTypes = this.constructor.getGenericExceptionTypes();
                        if (this.erasure.length == genericExceptionTypes.length) {
                            generic = TypeDefinition.Sort.describe(genericExceptionTypes[this.index], getAnnotationReader());
                        } else {
                            generic = asRawType();
                        }
                    }
                    if (generic == null) {
                        return this.resolved;
                    }
                    this.resolved = generic;
                    return generic;
                }

                public TypeDescription asErasure() {
                    return TypeDescription.ForLoadedType.of(this.erasure[this.index]);
                }

                /* access modifiers changed from: protected */
                public TypeDescription.Generic.AnnotationReader getAnnotationReader() {
                    return TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveExceptionType(this.constructor, this.index);
                }
            }
        }

        public static class OfMethodExceptionTypes extends AbstractBase {
            private final Method method;

            public OfMethodExceptionTypes(Method method2) {
                this.method = method2;
            }

            public TypeDescription.Generic get(int i) {
                Method method2 = this.method;
                return new TypeProjection(method2, i, method2.getExceptionTypes());
            }

            public int size() {
                return this.method.getExceptionTypes().length;
            }

            public TypeList asErasures() {
                return new ForLoadedTypes((Class<?>[]) this.method.getExceptionTypes());
            }

            private static class TypeProjection extends TypeDescription.Generic.LazyProjection.WithEagerNavigation.OfAnnotatedElement {
                private final Class<?>[] erasure;
                private final int index;
                private final Method method;
                private transient /* synthetic */ TypeDescription.Generic resolved;

                public TypeProjection(Method method2, int i, Class<?>[] clsArr) {
                    this.method = method2;
                    this.index = i;
                    this.erasure = clsArr;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public TypeDescription.Generic resolve() {
                    TypeDescription.Generic generic;
                    if (this.resolved != null) {
                        generic = null;
                    } else {
                        java.lang.reflect.Type[] genericExceptionTypes = this.method.getGenericExceptionTypes();
                        if (this.erasure.length == genericExceptionTypes.length) {
                            generic = TypeDefinition.Sort.describe(genericExceptionTypes[this.index], getAnnotationReader());
                        } else {
                            generic = asRawType();
                        }
                    }
                    if (generic == null) {
                        return this.resolved;
                    }
                    this.resolved = generic;
                    return generic;
                }

                public TypeDescription asErasure() {
                    return TypeDescription.ForLoadedType.of(this.erasure[this.index]);
                }

                /* access modifiers changed from: protected */
                public TypeDescription.Generic.AnnotationReader getAnnotationReader() {
                    return TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveExceptionType(this.method, this.index);
                }
            }
        }

        public static class Empty extends FilterableList.Empty<TypeDescription.Generic, Generic> implements Generic {
            public Generic asRawTypes() {
                return this;
            }

            public int getStackSize() {
                return 0;
            }

            public TypeList asErasures() {
                return new Empty();
            }

            public Generic accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
                return new Empty();
            }

            public ByteCodeElement.Token.TokenList<TypeVariableToken> asTokenList(ElementMatcher<? super TypeDescription> elementMatcher) {
                return new ByteCodeElement.Token.TokenList<>((S[]) new TypeVariableToken[0]);
            }
        }
    }
}

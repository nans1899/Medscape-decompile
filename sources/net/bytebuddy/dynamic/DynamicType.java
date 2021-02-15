package net.bytebuddy.dynamic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.MethodManifestation;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import net.bytebuddy.dynamic.scaffold.ClassWriterStrategy;
import net.bytebuddy.dynamic.scaffold.FieldRegistry;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.EqualsMethod;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.HashCodeMethod;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.ToStringMethod;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.LatentMatcher;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;

public interface DynamicType {

    public interface Loaded<T> extends DynamicType {
        Class<? extends T> getLoaded();

        Map<TypeDescription, Class<?>> getLoadedAuxiliaryTypes();
    }

    public interface Unloaded<T> extends DynamicType {
        Unloaded<T> include(List<? extends DynamicType> list);

        Unloaded<T> include(DynamicType... dynamicTypeArr);

        Loaded<T> load(ClassLoader classLoader);

        <S extends ClassLoader> Loaded<T> load(S s, ClassLoadingStrategy<? super S> classLoadingStrategy);
    }

    Map<TypeDescription, byte[]> getAllTypes();

    Map<TypeDescription, byte[]> getAuxiliaryTypes();

    byte[] getBytes();

    Map<TypeDescription, LoadedTypeInitializer> getLoadedTypeInitializers();

    TypeDescription getTypeDescription();

    boolean hasAliveLoadedTypeInitializers();

    File inject(File file) throws IOException;

    File inject(File file, File file2) throws IOException;

    Map<TypeDescription, File> saveIn(File file) throws IOException;

    File toJar(File file) throws IOException;

    File toJar(File file, Manifest manifest) throws IOException;

    public interface Builder<T> {

        public interface InnerTypeDefinition<S> extends Builder<S> {

            public interface ForType<U> extends InnerTypeDefinition<U> {
                Builder<U> asMemberType();
            }

            Builder<S> asAnonymousType();
        }

        Builder<T> annotateType(Collection<? extends AnnotationDescription> collection);

        Builder<T> annotateType(List<? extends Annotation> list);

        Builder<T> annotateType(Annotation... annotationArr);

        Builder<T> annotateType(AnnotationDescription... annotationDescriptionArr);

        Builder<T> attribute(TypeAttributeAppender typeAttributeAppender);

        MethodDefinition.ImplementationDefinition<T> constructor(ElementMatcher<? super MethodDescription> elementMatcher);

        Builder<T> declaredTypes(Collection<? extends TypeDescription> collection);

        Builder<T> declaredTypes(List<? extends Class<?>> list);

        Builder<T> declaredTypes(Class<?>... clsArr);

        Builder<T> declaredTypes(TypeDescription... typeDescriptionArr);

        FieldDefinition.Optional.Valuable<T> define(Field field);

        FieldDefinition.Optional.Valuable<T> define(FieldDescription fieldDescription);

        MethodDefinition.ImplementationDefinition<T> define(Constructor<?> constructor);

        MethodDefinition.ImplementationDefinition<T> define(Method method);

        MethodDefinition.ImplementationDefinition<T> define(MethodDescription methodDescription);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineConstructor(ModifierContributor.ForMethod... forMethodArr);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, int i);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, Collection<? extends ModifierContributor.ForField> collection);

        FieldDefinition.Optional.Valuable<T> defineField(String str, Type type, ModifierContributor.ForField... forFieldArr);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, int i);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForField> collection);

        FieldDefinition.Optional.Valuable<T> defineField(String str, TypeDefinition typeDefinition, ModifierContributor.ForField... forFieldArr);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, Type type, ModifierContributor.ForMethod... forMethodArr);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, int i);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForMethod> collection);

        MethodDefinition.ParameterDefinition.Initial<T> defineMethod(String str, TypeDefinition typeDefinition, ModifierContributor.ForMethod... forMethodArr);

        FieldDefinition.Optional<T> defineProperty(String str, Type type);

        FieldDefinition.Optional<T> defineProperty(String str, Type type, boolean z);

        FieldDefinition.Optional<T> defineProperty(String str, TypeDefinition typeDefinition);

        FieldDefinition.Optional<T> defineProperty(String str, TypeDefinition typeDefinition, boolean z);

        FieldDefinition.Valuable<T> field(ElementMatcher<? super FieldDescription> elementMatcher);

        FieldDefinition.Valuable<T> field(LatentMatcher<? super FieldDescription> latentMatcher);

        Builder<T> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher);

        Builder<T> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(Collection<? extends TypeDefinition> collection);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(List<? extends Type> list);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(Type... typeArr);

        MethodDefinition.ImplementationDefinition.Optional<T> implement(TypeDefinition... typeDefinitionArr);

        Builder<T> initializer(LoadedTypeInitializer loadedTypeInitializer);

        Builder<T> initializer(ByteCodeAppender byteCodeAppender);

        InnerTypeDefinition.ForType<T> innerTypeOf(Class<?> cls);

        InnerTypeDefinition.ForType<T> innerTypeOf(TypeDescription typeDescription);

        InnerTypeDefinition<T> innerTypeOf(Constructor<?> constructor);

        InnerTypeDefinition<T> innerTypeOf(Method method);

        InnerTypeDefinition<T> innerTypeOf(MethodDescription.InDefinedShape inDefinedShape);

        MethodDefinition.ImplementationDefinition<T> invokable(ElementMatcher<? super MethodDescription> elementMatcher);

        MethodDefinition.ImplementationDefinition<T> invokable(LatentMatcher<? super MethodDescription> latentMatcher);

        Unloaded<T> make();

        Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy);

        Unloaded<T> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool);

        Unloaded<T> make(TypePool typePool);

        Builder<T> merge(Collection<? extends ModifierContributor.ForType> collection);

        Builder<T> merge(ModifierContributor.ForType... forTypeArr);

        MethodDefinition.ImplementationDefinition<T> method(ElementMatcher<? super MethodDescription> elementMatcher);

        Builder<T> modifiers(int i);

        Builder<T> modifiers(Collection<? extends ModifierContributor.ForType> collection);

        Builder<T> modifiers(ModifierContributor.ForType... forTypeArr);

        Builder<T> name(String str);

        Builder<T> nestHost(Class<?> cls);

        Builder<T> nestHost(TypeDescription typeDescription);

        Builder<T> nestMembers(Collection<? extends TypeDescription> collection);

        Builder<T> nestMembers(List<? extends Class<?>> list);

        Builder<T> nestMembers(Class<?>... clsArr);

        Builder<T> nestMembers(TypeDescription... typeDescriptionArr);

        Builder<T> noNestMate();

        Builder<T> require(Collection<DynamicType> collection);

        Builder<T> require(TypeDescription typeDescription, byte[] bArr);

        Builder<T> require(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer);

        Builder<T> require(DynamicType... dynamicTypeArr);

        FieldDefinition.Optional<T> serialVersionUid(long j);

        Builder<T> topLevelType();

        Builder<T> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer);

        TypeVariableDefinition<T> typeVariable(String str);

        TypeVariableDefinition<T> typeVariable(String str, Collection<? extends TypeDefinition> collection);

        TypeVariableDefinition<T> typeVariable(String str, List<? extends Type> list);

        TypeVariableDefinition<T> typeVariable(String str, Type... typeArr);

        TypeVariableDefinition<T> typeVariable(String str, TypeDefinition... typeDefinitionArr);

        Builder<T> visit(AsmVisitorWrapper asmVisitorWrapper);

        Builder<T> withHashCodeEquals();

        Builder<T> withToString();

        public interface TypeVariableDefinition<S> extends Builder<S> {
            TypeVariableDefinition<S> annotateTypeVariable(Collection<? extends AnnotationDescription> collection);

            TypeVariableDefinition<S> annotateTypeVariable(List<? extends Annotation> list);

            TypeVariableDefinition<S> annotateTypeVariable(Annotation... annotationArr);

            TypeVariableDefinition<S> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr);

            public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements TypeVariableDefinition<U> {
                public TypeVariableDefinition<U> annotateTypeVariable(Annotation... annotationArr) {
                    return annotateTypeVariable((List<? extends Annotation>) Arrays.asList(annotationArr));
                }

                public TypeVariableDefinition<U> annotateTypeVariable(List<? extends Annotation> list) {
                    return annotateTypeVariable((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                public TypeVariableDefinition<U> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr) {
                    return annotateTypeVariable((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }
            }
        }

        public interface FieldDefinition<S> {

            public interface Valuable<U> extends FieldDefinition<U> {
                Optional<U> value(double d);

                Optional<U> value(float f);

                Optional<U> value(int i);

                Optional<U> value(long j);

                Optional<U> value(String str);

                Optional<U> value(boolean z);
            }

            Optional<S> annotateField(Collection<? extends AnnotationDescription> collection);

            Optional<S> annotateField(List<? extends Annotation> list);

            Optional<S> annotateField(Annotation... annotationArr);

            Optional<S> annotateField(AnnotationDescription... annotationDescriptionArr);

            Optional<S> attribute(FieldAttributeAppender.Factory factory);

            Optional<S> transform(Transformer<FieldDescription> transformer);

            public interface Optional<U> extends FieldDefinition<U>, Builder<U> {

                public interface Valuable<V> extends Valuable<V>, Optional<V> {

                    public static abstract class AbstractBase<U> extends AbstractBase<U> implements Valuable<U> {
                        /* access modifiers changed from: protected */
                        public abstract Optional<U> defaultValue(Object obj);

                        public Optional<U> value(boolean z) {
                            return defaultValue(Integer.valueOf(z ? 1 : 0));
                        }

                        public Optional<U> value(int i) {
                            return defaultValue(Integer.valueOf(i));
                        }

                        public Optional<U> value(long j) {
                            return defaultValue(Long.valueOf(j));
                        }

                        public Optional<U> value(float f) {
                            return defaultValue(Float.valueOf(f));
                        }

                        public Optional<U> value(double d) {
                            return defaultValue(Double.valueOf(d));
                        }

                        public Optional<U> value(String str) {
                            if (str != null) {
                                return defaultValue(str);
                            }
                            throw new IllegalArgumentException("Cannot set null as a default value");
                        }

                        @HashCodeAndEqualsPlugin.Enhance
                        private static abstract class Adapter<V> extends AbstractBase<V> {
                            @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                            protected final Object defaultValue;
                            protected final FieldAttributeAppender.Factory fieldAttributeAppenderFactory;
                            protected final Transformer<FieldDescription> transformer;

                            /* JADX WARNING: Removed duplicated region for block: B:23:0x003d A[RETURN] */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public boolean equals(java.lang.Object r5) {
                                /*
                                    r4 = this;
                                    r0 = 1
                                    if (r4 != r5) goto L_0x0004
                                    return r0
                                L_0x0004:
                                    r1 = 0
                                    if (r5 != 0) goto L_0x0008
                                    return r1
                                L_0x0008:
                                    java.lang.Class r2 = r4.getClass()
                                    java.lang.Class r3 = r5.getClass()
                                    if (r2 == r3) goto L_0x0013
                                    return r1
                                L_0x0013:
                                    net.bytebuddy.implementation.attribute.FieldAttributeAppender$Factory r2 = r4.fieldAttributeAppenderFactory
                                    net.bytebuddy.dynamic.DynamicType$Builder$FieldDefinition$Optional$Valuable$AbstractBase$Adapter r5 = (net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter) r5
                                    net.bytebuddy.implementation.attribute.FieldAttributeAppender$Factory r3 = r5.fieldAttributeAppenderFactory
                                    boolean r2 = r2.equals(r3)
                                    if (r2 != 0) goto L_0x0020
                                    return r1
                                L_0x0020:
                                    net.bytebuddy.dynamic.Transformer<net.bytebuddy.description.field.FieldDescription> r2 = r4.transformer
                                    net.bytebuddy.dynamic.Transformer<net.bytebuddy.description.field.FieldDescription> r3 = r5.transformer
                                    boolean r2 = r2.equals(r3)
                                    if (r2 != 0) goto L_0x002b
                                    return r1
                                L_0x002b:
                                    java.lang.Object r2 = r4.defaultValue
                                    java.lang.Object r5 = r5.defaultValue
                                    if (r5 == 0) goto L_0x003a
                                    if (r2 == 0) goto L_0x003c
                                    boolean r5 = r2.equals(r5)
                                    if (r5 != 0) goto L_0x003d
                                    return r1
                                L_0x003a:
                                    if (r2 == 0) goto L_0x003d
                                L_0x003c:
                                    return r1
                                L_0x003d:
                                    return r0
                                */
                                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional.Valuable.AbstractBase.Adapter.equals(java.lang.Object):boolean");
                            }

                            public int hashCode() {
                                int hashCode = (((527 + this.fieldAttributeAppenderFactory.hashCode()) * 31) + this.transformer.hashCode()) * 31;
                                Object obj = this.defaultValue;
                                return obj != null ? hashCode + obj.hashCode() : hashCode;
                            }

                            /* access modifiers changed from: protected */
                            public abstract Optional<V> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer2, Object obj);

                            protected Adapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer2, Object obj) {
                                this.fieldAttributeAppenderFactory = factory;
                                this.transformer = transformer2;
                                this.defaultValue = obj;
                            }

                            public Optional<V> attribute(FieldAttributeAppender.Factory factory) {
                                return materialize(new FieldAttributeAppender.Factory.Compound(this.fieldAttributeAppenderFactory, factory), this.transformer, this.defaultValue);
                            }

                            public Optional<V> transform(Transformer<FieldDescription> transformer2) {
                                return materialize(this.fieldAttributeAppenderFactory, new Transformer.Compound((Transformer<S>[]) new Transformer[]{this.transformer, transformer2}), this.defaultValue);
                            }

                            /* access modifiers changed from: protected */
                            public Optional<V> defaultValue(Object obj) {
                                return materialize(this.fieldAttributeAppenderFactory, this.transformer, obj);
                            }
                        }
                    }
                }

                public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements Optional<U> {
                    public Optional<U> annotateField(Annotation... annotationArr) {
                        return annotateField((List<? extends Annotation>) Arrays.asList(annotationArr));
                    }

                    public Optional<U> annotateField(List<? extends Annotation> list) {
                        return annotateField((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                    }

                    public Optional<U> annotateField(AnnotationDescription... annotationDescriptionArr) {
                        return annotateField((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                    }
                }
            }
        }

        public interface MethodDefinition<S> extends Builder<S> {
            MethodDefinition<S> annotateMethod(Collection<? extends AnnotationDescription> collection);

            MethodDefinition<S> annotateMethod(List<? extends Annotation> list);

            MethodDefinition<S> annotateMethod(Annotation... annotationArr);

            MethodDefinition<S> annotateMethod(AnnotationDescription... annotationDescriptionArr);

            MethodDefinition<S> annotateParameter(int i, Collection<? extends AnnotationDescription> collection);

            MethodDefinition<S> annotateParameter(int i, List<? extends Annotation> list);

            MethodDefinition<S> annotateParameter(int i, Annotation... annotationArr);

            MethodDefinition<S> annotateParameter(int i, AnnotationDescription... annotationDescriptionArr);

            MethodDefinition<S> attribute(MethodAttributeAppender.Factory factory);

            MethodDefinition<S> transform(Transformer<MethodDescription> transformer);

            public interface ReceiverTypeDefinition<U> extends MethodDefinition<U> {
                MethodDefinition<U> receiverType(AnnotatedElement annotatedElement);

                MethodDefinition<U> receiverType(TypeDescription.Generic generic);

                public static abstract class AbstractBase<V> extends AbstractBase<V> implements ReceiverTypeDefinition<V> {
                    public MethodDefinition<V> receiverType(AnnotatedElement annotatedElement) {
                        return receiverType(TypeDescription.Generic.AnnotationReader.DISPATCHER.resolve(annotatedElement));
                    }
                }
            }

            public interface ImplementationDefinition<U> {

                public interface Optional<V> extends ImplementationDefinition<V>, Builder<V> {
                }

                <W> ReceiverTypeDefinition<U> defaultValue(W w, Class<? extends W> cls);

                ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue);

                ReceiverTypeDefinition<U> intercept(Implementation implementation);

                ReceiverTypeDefinition<U> withoutCode();

                public static abstract class AbstractBase<V> implements ImplementationDefinition<V> {
                    public <W> ReceiverTypeDefinition<V> defaultValue(W w, Class<? extends W> cls) {
                        return defaultValue(AnnotationDescription.ForLoadedAnnotation.asValue(w, cls));
                    }
                }
            }

            public interface TypeVariableDefinition<U> extends ImplementationDefinition<U> {
                Annotatable<U> typeVariable(String str);

                Annotatable<U> typeVariable(String str, Collection<? extends TypeDefinition> collection);

                Annotatable<U> typeVariable(String str, List<? extends Type> list);

                Annotatable<U> typeVariable(String str, Type... typeArr);

                Annotatable<U> typeVariable(String str, TypeDefinition... typeDefinitionArr);

                public interface Annotatable<V> extends TypeVariableDefinition<V> {
                    Annotatable<V> annotateTypeVariable(Collection<? extends AnnotationDescription> collection);

                    Annotatable<V> annotateTypeVariable(List<? extends Annotation> list);

                    Annotatable<V> annotateTypeVariable(Annotation... annotationArr);

                    Annotatable<V> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr);

                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                        public Annotatable<W> annotateTypeVariable(Annotation... annotationArr) {
                            return annotateTypeVariable((List<? extends Annotation>) Arrays.asList(annotationArr));
                        }

                        public Annotatable<W> annotateTypeVariable(List<? extends Annotation> list) {
                            return annotateTypeVariable((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                        }

                        public Annotatable<W> annotateTypeVariable(AnnotationDescription... annotationDescriptionArr) {
                            return annotateTypeVariable((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                        }

                        protected static abstract class Adapter<X> extends AbstractBase<X> {
                            /* access modifiers changed from: protected */
                            public abstract ParameterDefinition<X> materialize();

                            protected Adapter() {
                            }

                            public Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                return materialize().typeVariable(str, collection);
                            }

                            public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                return materialize().intercept(implementation);
                            }

                            public ReceiverTypeDefinition<X> withoutCode() {
                                return materialize().withoutCode();
                            }

                            public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                return materialize().defaultValue(annotationValue);
                            }

                            public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                return materialize().defaultValue(v, cls);
                            }
                        }
                    }
                }

                public static abstract class AbstractBase<V> extends ImplementationDefinition.AbstractBase<V> implements TypeVariableDefinition<V> {
                    public Annotatable<V> typeVariable(String str) {
                        return typeVariable(str, (List<? extends Type>) Collections.singletonList(Object.class));
                    }

                    public Annotatable<V> typeVariable(String str, Type... typeArr) {
                        return typeVariable(str, (List<? extends Type>) Arrays.asList(typeArr));
                    }

                    public Annotatable<V> typeVariable(String str, List<? extends Type> list) {
                        return typeVariable(str, (Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                    }

                    public Annotatable<V> typeVariable(String str, TypeDefinition... typeDefinitionArr) {
                        return typeVariable(str, (Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                    }
                }
            }

            public interface ExceptionDefinition<U> extends TypeVariableDefinition<U> {
                ExceptionDefinition<U> throwing(Collection<? extends TypeDefinition> collection);

                ExceptionDefinition<U> throwing(List<? extends Type> list);

                ExceptionDefinition<U> throwing(Type... typeArr);

                ExceptionDefinition<U> throwing(TypeDefinition... typeDefinitionArr);

                public static abstract class AbstractBase<V> extends TypeVariableDefinition.AbstractBase<V> implements ExceptionDefinition<V> {
                    public ExceptionDefinition<V> throwing(Type... typeArr) {
                        return throwing((List<? extends Type>) Arrays.asList(typeArr));
                    }

                    public ExceptionDefinition<V> throwing(List<? extends Type> list) {
                        return throwing((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                    }

                    public ExceptionDefinition<V> throwing(TypeDefinition... typeDefinitionArr) {
                        return throwing((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                    }
                }
            }

            public interface ParameterDefinition<U> extends ExceptionDefinition<U> {
                Annotatable<U> withParameter(Type type, String str, int i);

                Annotatable<U> withParameter(Type type, String str, Collection<? extends ModifierContributor.ForParameter> collection);

                Annotatable<U> withParameter(Type type, String str, ModifierContributor.ForParameter... forParameterArr);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, int i);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, Collection<? extends ModifierContributor.ForParameter> collection);

                Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, ModifierContributor.ForParameter... forParameterArr);

                public interface Annotatable<V> extends ParameterDefinition<V> {
                    Annotatable<V> annotateParameter(Collection<? extends AnnotationDescription> collection);

                    Annotatable<V> annotateParameter(List<? extends Annotation> list);

                    Annotatable<V> annotateParameter(Annotation... annotationArr);

                    Annotatable<V> annotateParameter(AnnotationDescription... annotationDescriptionArr);

                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                        public Annotatable<W> annotateParameter(Annotation... annotationArr) {
                            return annotateParameter((List<? extends Annotation>) Arrays.asList(annotationArr));
                        }

                        public Annotatable<W> annotateParameter(List<? extends Annotation> list) {
                            return annotateParameter((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                        }

                        public Annotatable<W> annotateParameter(AnnotationDescription... annotationDescriptionArr) {
                            return annotateParameter((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                        }

                        protected static abstract class Adapter<X> extends AbstractBase<X> {
                            /* access modifiers changed from: protected */
                            public abstract ParameterDefinition<X> materialize();

                            protected Adapter() {
                            }

                            public Annotatable<X> withParameter(TypeDefinition typeDefinition, String str, int i) {
                                return materialize().withParameter(typeDefinition, str, i);
                            }

                            public ExceptionDefinition<X> throwing(Collection<? extends TypeDefinition> collection) {
                                return materialize().throwing(collection);
                            }

                            public TypeVariableDefinition.Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                return materialize().typeVariable(str, collection);
                            }

                            public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                return materialize().intercept(implementation);
                            }

                            public ReceiverTypeDefinition<X> withoutCode() {
                                return materialize().withoutCode();
                            }

                            public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                return materialize().defaultValue(annotationValue);
                            }

                            public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                return materialize().defaultValue(v, cls);
                            }
                        }
                    }
                }

                public interface Simple<V> extends ExceptionDefinition<V> {
                    Annotatable<V> withParameter(Type type);

                    Annotatable<V> withParameter(TypeDefinition typeDefinition);

                    public interface Annotatable<V> extends Simple<V> {
                        Annotatable<V> annotateParameter(Collection<? extends AnnotationDescription> collection);

                        Annotatable<V> annotateParameter(List<? extends Annotation> list);

                        Annotatable<V> annotateParameter(Annotation... annotationArr);

                        Annotatable<V> annotateParameter(AnnotationDescription... annotationDescriptionArr);

                        public static abstract class AbstractBase<W> extends AbstractBase<W> implements Annotatable<W> {
                            public Annotatable<W> annotateParameter(Annotation... annotationArr) {
                                return annotateParameter((List<? extends Annotation>) Arrays.asList(annotationArr));
                            }

                            public Annotatable<W> annotateParameter(List<? extends Annotation> list) {
                                return annotateParameter((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                            }

                            public Annotatable<W> annotateParameter(AnnotationDescription... annotationDescriptionArr) {
                                return annotateParameter((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                            }

                            protected static abstract class Adapter<X> extends AbstractBase<X> {
                                /* access modifiers changed from: protected */
                                public abstract Simple<X> materialize();

                                protected Adapter() {
                                }

                                public Annotatable<X> withParameter(TypeDefinition typeDefinition) {
                                    return materialize().withParameter(typeDefinition);
                                }

                                public ExceptionDefinition<X> throwing(Collection<? extends TypeDefinition> collection) {
                                    return materialize().throwing(collection);
                                }

                                public TypeVariableDefinition.Annotatable<X> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                                    return materialize().typeVariable(str, collection);
                                }

                                public ReceiverTypeDefinition<X> intercept(Implementation implementation) {
                                    return materialize().intercept(implementation);
                                }

                                public ReceiverTypeDefinition<X> withoutCode() {
                                    return materialize().withoutCode();
                                }

                                public ReceiverTypeDefinition<X> defaultValue(AnnotationValue<?, ?> annotationValue) {
                                    return materialize().defaultValue(annotationValue);
                                }

                                public <V> ReceiverTypeDefinition<X> defaultValue(V v, Class<? extends V> cls) {
                                    return materialize().defaultValue(v, cls);
                                }
                            }
                        }
                    }

                    public static abstract class AbstractBase<W> extends ExceptionDefinition.AbstractBase<W> implements Simple<W> {
                        public Annotatable<W> withParameter(Type type) {
                            return withParameter((TypeDefinition) TypeDefinition.Sort.describe(type));
                        }
                    }
                }

                public interface Initial<V> extends ParameterDefinition<V>, Simple<V> {
                    ExceptionDefinition<V> withParameters(Collection<? extends TypeDefinition> collection);

                    ExceptionDefinition<V> withParameters(List<? extends Type> list);

                    ExceptionDefinition<V> withParameters(Type... typeArr);

                    ExceptionDefinition<V> withParameters(TypeDefinition... typeDefinitionArr);

                    public static abstract class AbstractBase<W> extends AbstractBase<W> implements Initial<W> {
                        public Simple.Annotatable<W> withParameter(Type type) {
                            return withParameter((TypeDefinition) TypeDefinition.Sort.describe(type));
                        }

                        public ExceptionDefinition<W> withParameters(Type... typeArr) {
                            return withParameters((List<? extends Type>) Arrays.asList(typeArr));
                        }

                        public ExceptionDefinition<W> withParameters(List<? extends Type> list) {
                            return withParameters((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
                        }

                        public ExceptionDefinition<W> withParameters(TypeDefinition... typeDefinitionArr) {
                            return withParameters((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
                        }

                        public ExceptionDefinition<W> withParameters(Collection<? extends TypeDefinition> collection) {
                            Simple simple = this;
                            for (TypeDefinition withParameter : collection) {
                                simple = simple.withParameter(withParameter);
                            }
                            return simple;
                        }
                    }
                }

                public static abstract class AbstractBase<V> extends ExceptionDefinition.AbstractBase<V> implements ParameterDefinition<V> {
                    public Annotatable<V> withParameter(Type type, String str, ModifierContributor.ForParameter... forParameterArr) {
                        return withParameter(type, str, (Collection<? extends ModifierContributor.ForParameter>) Arrays.asList(forParameterArr));
                    }

                    public Annotatable<V> withParameter(Type type, String str, Collection<? extends ModifierContributor.ForParameter> collection) {
                        return withParameter(type, str, ModifierContributor.Resolver.of(collection).resolve());
                    }

                    public Annotatable<V> withParameter(Type type, String str, int i) {
                        return withParameter((TypeDefinition) TypeDefinition.Sort.describe(type), str, i);
                    }

                    public Annotatable<V> withParameter(TypeDefinition typeDefinition, String str, ModifierContributor.ForParameter... forParameterArr) {
                        return withParameter(typeDefinition, str, (Collection<? extends ModifierContributor.ForParameter>) Arrays.asList(forParameterArr));
                    }

                    public Annotatable<V> withParameter(TypeDefinition typeDefinition, String str, Collection<? extends ModifierContributor.ForParameter> collection) {
                        return withParameter(typeDefinition, str, ModifierContributor.Resolver.of(collection).resolve());
                    }
                }
            }

            public static abstract class AbstractBase<U> extends AbstractBase.Delegator<U> implements MethodDefinition<U> {
                public MethodDefinition<U> annotateMethod(Annotation... annotationArr) {
                    return annotateMethod((List<? extends Annotation>) Arrays.asList(annotationArr));
                }

                public MethodDefinition<U> annotateMethod(List<? extends Annotation> list) {
                    return annotateMethod((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                public MethodDefinition<U> annotateMethod(AnnotationDescription... annotationDescriptionArr) {
                    return annotateMethod((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }

                public MethodDefinition<U> annotateParameter(int i, Annotation... annotationArr) {
                    return annotateParameter(i, (List<? extends Annotation>) Arrays.asList(annotationArr));
                }

                public MethodDefinition<U> annotateParameter(int i, List<? extends Annotation> list) {
                    return annotateParameter(i, (Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
                }

                public MethodDefinition<U> annotateParameter(int i, AnnotationDescription... annotationDescriptionArr) {
                    return annotateParameter(i, (Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static abstract class Adapter<V> extends ReceiverTypeDefinition.AbstractBase<V> {
                    protected final MethodRegistry.Handler handler;
                    protected final MethodAttributeAppender.Factory methodAttributeAppenderFactory;
                    protected final Transformer<MethodDescription> transformer;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Adapter adapter = (Adapter) obj;
                        return this.handler.equals(adapter.handler) && this.methodAttributeAppenderFactory.equals(adapter.methodAttributeAppenderFactory) && this.transformer.equals(adapter.transformer);
                    }

                    public int hashCode() {
                        return ((((527 + this.handler.hashCode()) * 31) + this.methodAttributeAppenderFactory.hashCode()) * 31) + this.transformer.hashCode();
                    }

                    /* access modifiers changed from: protected */
                    public abstract MethodDefinition<V> materialize(MethodRegistry.Handler handler2, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer2);

                    protected Adapter(MethodRegistry.Handler handler2, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer2) {
                        this.handler = handler2;
                        this.methodAttributeAppenderFactory = factory;
                        this.transformer = transformer2;
                    }

                    public MethodDefinition<V> attribute(MethodAttributeAppender.Factory factory) {
                        return materialize(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, factory), this.transformer);
                    }

                    public MethodDefinition<V> transform(Transformer<MethodDescription> transformer2) {
                        return materialize(this.handler, this.methodAttributeAppenderFactory, new Transformer.Compound((Transformer<S>[]) new Transformer[]{this.transformer, transformer2}));
                    }
                }
            }
        }

        public static abstract class AbstractBase<S> implements Builder<S> {
            public InnerTypeDefinition.ForType<S> innerTypeOf(Class<?> cls) {
                return innerTypeOf(TypeDescription.ForLoadedType.of(cls));
            }

            public InnerTypeDefinition<S> innerTypeOf(Method method) {
                return innerTypeOf((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method));
            }

            public InnerTypeDefinition<S> innerTypeOf(Constructor<?> constructor) {
                return innerTypeOf((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(constructor));
            }

            public Builder<S> declaredTypes(Class<?>... clsArr) {
                return declaredTypes((List<? extends Class<?>>) Arrays.asList(clsArr));
            }

            public Builder<S> declaredTypes(TypeDescription... typeDescriptionArr) {
                return declaredTypes((Collection<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
            }

            public Builder<S> declaredTypes(List<? extends Class<?>> list) {
                return declaredTypes((Collection<? extends TypeDescription>) new TypeList.ForLoadedTypes(list));
            }

            public Builder<S> noNestMate() {
                return nestHost(TargetType.DESCRIPTION);
            }

            public Builder<S> nestHost(Class<?> cls) {
                return nestHost(TypeDescription.ForLoadedType.of(cls));
            }

            public Builder<S> nestMembers(Class<?>... clsArr) {
                return nestMembers((List<? extends Class<?>>) Arrays.asList(clsArr));
            }

            public Builder<S> nestMembers(TypeDescription... typeDescriptionArr) {
                return nestMembers((Collection<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
            }

            public Builder<S> nestMembers(List<? extends Class<?>> list) {
                return nestMembers((Collection<? extends TypeDescription>) new TypeList.ForLoadedTypes(list));
            }

            public Builder<S> annotateType(Annotation... annotationArr) {
                return annotateType((List<? extends Annotation>) Arrays.asList(annotationArr));
            }

            public Builder<S> annotateType(List<? extends Annotation> list) {
                return annotateType((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Builder<S> annotateType(AnnotationDescription... annotationDescriptionArr) {
                return annotateType((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Builder<S> modifiers(ModifierContributor.ForType... forTypeArr) {
                return modifiers((Collection<? extends ModifierContributor.ForType>) Arrays.asList(forTypeArr));
            }

            public Builder<S> modifiers(Collection<? extends ModifierContributor.ForType> collection) {
                return modifiers(ModifierContributor.Resolver.of(collection).resolve());
            }

            public Builder<S> merge(ModifierContributor.ForType... forTypeArr) {
                return merge((Collection<? extends ModifierContributor.ForType>) Arrays.asList(forTypeArr));
            }

            public MethodDefinition.ImplementationDefinition.Optional<S> implement(Type... typeArr) {
                return implement((List<? extends Type>) Arrays.asList(typeArr));
            }

            public MethodDefinition.ImplementationDefinition.Optional<S> implement(List<? extends Type> list) {
                return implement((Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
            }

            public MethodDefinition.ImplementationDefinition.Optional<S> implement(TypeDefinition... typeDefinitionArr) {
                return implement((Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            public TypeVariableDefinition<S> typeVariable(String str) {
                return typeVariable(str, TypeDescription.Generic.OBJECT);
            }

            public TypeVariableDefinition<S> typeVariable(String str, Type... typeArr) {
                return typeVariable(str, (List<? extends Type>) Arrays.asList(typeArr));
            }

            public TypeVariableDefinition<S> typeVariable(String str, List<? extends Type> list) {
                return typeVariable(str, (Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
            }

            public TypeVariableDefinition<S> typeVariable(String str, TypeDefinition... typeDefinitionArr) {
                return typeVariable(str, (Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, ModifierContributor.ForField... forFieldArr) {
                return defineField(str, type, (Collection<? extends ModifierContributor.ForField>) Arrays.asList(forFieldArr));
            }

            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, Collection<? extends ModifierContributor.ForField> collection) {
                return defineField(str, type, ModifierContributor.Resolver.of(collection).resolve());
            }

            public FieldDefinition.Optional.Valuable<S> defineField(String str, Type type, int i) {
                return defineField(str, (TypeDefinition) TypeDefinition.Sort.describe(type), i);
            }

            public FieldDefinition.Optional.Valuable<S> defineField(String str, TypeDefinition typeDefinition, ModifierContributor.ForField... forFieldArr) {
                return defineField(str, typeDefinition, (Collection<? extends ModifierContributor.ForField>) Arrays.asList(forFieldArr));
            }

            public FieldDefinition.Optional.Valuable<S> defineField(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForField> collection) {
                return defineField(str, typeDefinition, ModifierContributor.Resolver.of(collection).resolve());
            }

            public FieldDefinition.Optional.Valuable<S> define(Field field) {
                return define((FieldDescription) new FieldDescription.ForLoadedField(field));
            }

            public FieldDefinition.Optional.Valuable<S> define(FieldDescription fieldDescription) {
                return defineField(fieldDescription.getName(), (TypeDefinition) fieldDescription.getType(), fieldDescription.getModifiers());
            }

            public FieldDefinition.Optional<S> serialVersionUid(long j) {
                return defineField("serialVersionUID", (Type) Long.TYPE, Visibility.PRIVATE, FieldManifestation.FINAL, Ownership.STATIC).value(j);
            }

            public FieldDefinition.Valuable<S> field(ElementMatcher<? super FieldDescription> elementMatcher) {
                return field((LatentMatcher<? super FieldDescription>) new LatentMatcher.Resolved(elementMatcher));
            }

            public Builder<S> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher) {
                return ignoreAlso((LatentMatcher<? super MethodDescription>) new LatentMatcher.Resolved(elementMatcher));
            }

            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, ModifierContributor.ForMethod... forMethodArr) {
                return defineMethod(str, type, (Collection<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
            }

            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineMethod(str, type, ModifierContributor.Resolver.of(collection).resolve());
            }

            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, Type type, int i) {
                return defineMethod(str, (TypeDefinition) TypeDefinition.Sort.describe(type), i);
            }

            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, TypeDefinition typeDefinition, ModifierContributor.ForMethod... forMethodArr) {
                return defineMethod(str, typeDefinition, (Collection<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
            }

            public MethodDefinition.ParameterDefinition.Initial<S> defineMethod(String str, TypeDefinition typeDefinition, Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineMethod(str, typeDefinition, ModifierContributor.Resolver.of(collection).resolve());
            }

            public MethodDefinition.ParameterDefinition.Initial<S> defineConstructor(ModifierContributor.ForMethod... forMethodArr) {
                return defineConstructor((Collection<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
            }

            public MethodDefinition.ParameterDefinition.Initial<S> defineConstructor(Collection<? extends ModifierContributor.ForMethod> collection) {
                return defineConstructor(ModifierContributor.Resolver.of(collection).resolve());
            }

            public MethodDefinition.ImplementationDefinition<S> define(Method method) {
                return define((MethodDescription) new MethodDescription.ForLoadedMethod(method));
            }

            public MethodDefinition.ImplementationDefinition<S> define(Constructor<?> constructor) {
                return define((MethodDescription) new MethodDescription.ForLoadedConstructor(constructor));
            }

            public MethodDefinition.ImplementationDefinition<S> define(MethodDescription methodDescription) {
                MethodDefinition.ParameterDefinition.Initial initial;
                MethodDefinition.ParameterDefinition parameterDefinition;
                if (methodDescription.isConstructor()) {
                    initial = defineConstructor(methodDescription.getModifiers());
                } else {
                    initial = defineMethod(methodDescription.getInternalName(), (TypeDefinition) methodDescription.getReturnType(), methodDescription.getModifiers());
                }
                ParameterList<?> parameters = methodDescription.getParameters();
                if (parameters.hasExplicitMetaData()) {
                    Iterator it = parameters.iterator();
                    MethodDefinition.ParameterDefinition parameterDefinition2 = initial;
                    while (it.hasNext()) {
                        ParameterDescription parameterDescription = (ParameterDescription) it.next();
                        parameterDefinition2 = parameterDefinition2.withParameter((TypeDefinition) parameterDescription.getType(), parameterDescription.getName(), parameterDescription.getModifiers());
                    }
                    parameterDefinition = parameterDefinition2;
                } else {
                    parameterDefinition = initial.withParameters((Collection<? extends TypeDefinition>) parameters.asTypeList());
                }
                MethodDefinition.TypeVariableDefinition throwing = parameterDefinition.throwing((Collection<? extends TypeDefinition>) methodDescription.getExceptionTypes());
                for (TypeDescription.Generic generic : methodDescription.getTypeVariables()) {
                    throwing = throwing.typeVariable(generic.getSymbol(), (Collection<? extends TypeDefinition>) generic.getUpperBounds());
                }
                return throwing;
            }

            public FieldDefinition.Optional<S> defineProperty(String str, Type type) {
                return defineProperty(str, (TypeDefinition) TypeDefinition.Sort.describe(type));
            }

            public FieldDefinition.Optional<S> defineProperty(String str, Type type, boolean z) {
                return defineProperty(str, (TypeDefinition) TypeDefinition.Sort.describe(type), z);
            }

            public FieldDefinition.Optional<S> defineProperty(String str, TypeDefinition typeDefinition) {
                return defineProperty(str, typeDefinition, false);
            }

            public FieldDefinition.Optional<S> defineProperty(String str, TypeDefinition typeDefinition, boolean z) {
                Builder builder;
                FieldManifestation fieldManifestation;
                if (str.length() == 0) {
                    throw new IllegalArgumentException("A bean property cannot have an empty name");
                } else if (!typeDefinition.represents(Void.TYPE)) {
                    if (!z) {
                        builder = defineMethod("set" + Character.toUpperCase(str.charAt(0)) + str.substring(1), (Type) Void.TYPE, Visibility.PUBLIC).withParameters(typeDefinition).intercept(FieldAccessor.ofField(str));
                        fieldManifestation = FieldManifestation.PLAIN;
                    } else {
                        fieldManifestation = FieldManifestation.FINAL;
                        builder = this;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append((typeDefinition.represents(Boolean.TYPE) || typeDefinition.represents(Boolean.class)) ? "is" : "get");
                    sb.append(Character.toUpperCase(str.charAt(0)));
                    sb.append(str.substring(1));
                    return builder.defineMethod(sb.toString(), typeDefinition, Visibility.PUBLIC).intercept(FieldAccessor.ofField(str)).defineField(str, typeDefinition, Visibility.PRIVATE, fieldManifestation);
                } else {
                    throw new IllegalArgumentException("A bean property cannot have a void type");
                }
            }

            public MethodDefinition.ImplementationDefinition<S> method(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(ElementMatchers.isMethod().and(elementMatcher));
            }

            public MethodDefinition.ImplementationDefinition<S> constructor(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable(ElementMatchers.isConstructor().and(elementMatcher));
            }

            public MethodDefinition.ImplementationDefinition<S> invokable(ElementMatcher<? super MethodDescription> elementMatcher) {
                return invokable((LatentMatcher<? super MethodDescription>) new LatentMatcher.Resolved(elementMatcher));
            }

            public Builder<S> withHashCodeEquals() {
                return method(ElementMatchers.isHashCode()).intercept(HashCodeMethod.usingDefaultOffset().withIgnoredFields(ElementMatchers.isSynthetic())).method(ElementMatchers.isEquals()).intercept(EqualsMethod.isolated().withIgnoredFields(ElementMatchers.isSynthetic()));
            }

            public Builder<S> withToString() {
                return method(ElementMatchers.isToString()).intercept(ToStringMethod.prefixedBySimpleClassName());
            }

            public Builder<S> require(TypeDescription typeDescription, byte[] bArr) {
                return require(typeDescription, bArr, LoadedTypeInitializer.NoOp.INSTANCE);
            }

            public Builder<S> require(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer) {
                return require(new Default(typeDescription, bArr, loadedTypeInitializer, Collections.emptyList()));
            }

            public Builder<S> require(DynamicType... dynamicTypeArr) {
                return require((Collection<DynamicType>) Arrays.asList(dynamicTypeArr));
            }

            public Unloaded<S> make(TypePool typePool) {
                return make(TypeResolutionStrategy.Passive.INSTANCE, typePool);
            }

            public Unloaded<S> make() {
                return make((TypeResolutionStrategy) TypeResolutionStrategy.Passive.INSTANCE);
            }

            public static abstract class Delegator<U> extends AbstractBase<U> {
                /* access modifiers changed from: protected */
                public abstract Builder<U> materialize();

                public Builder<U> visit(AsmVisitorWrapper asmVisitorWrapper) {
                    return materialize().visit(asmVisitorWrapper);
                }

                public Builder<U> initializer(LoadedTypeInitializer loadedTypeInitializer) {
                    return materialize().initializer(loadedTypeInitializer);
                }

                public Builder<U> annotateType(Collection<? extends AnnotationDescription> collection) {
                    return materialize().annotateType(collection);
                }

                public Builder<U> attribute(TypeAttributeAppender typeAttributeAppender) {
                    return materialize().attribute(typeAttributeAppender);
                }

                public Builder<U> modifiers(int i) {
                    return materialize().modifiers(i);
                }

                public Builder<U> merge(Collection<? extends ModifierContributor.ForType> collection) {
                    return materialize().merge(collection);
                }

                public Builder<U> name(String str) {
                    return materialize().name(str);
                }

                public Builder<U> topLevelType() {
                    return materialize().topLevelType();
                }

                public InnerTypeDefinition.ForType<U> innerTypeOf(TypeDescription typeDescription) {
                    return materialize().innerTypeOf(typeDescription);
                }

                public InnerTypeDefinition<U> innerTypeOf(MethodDescription.InDefinedShape inDefinedShape) {
                    return materialize().innerTypeOf(inDefinedShape);
                }

                public Builder<U> declaredTypes(Collection<? extends TypeDescription> collection) {
                    return materialize().declaredTypes(collection);
                }

                public Builder<U> nestHost(TypeDescription typeDescription) {
                    return materialize().nestHost(typeDescription);
                }

                public Builder<U> nestMembers(Collection<? extends TypeDescription> collection) {
                    return materialize().nestMembers(collection);
                }

                public MethodDefinition.ImplementationDefinition.Optional<U> implement(Collection<? extends TypeDefinition> collection) {
                    return materialize().implement(collection);
                }

                public Builder<U> initializer(ByteCodeAppender byteCodeAppender) {
                    return materialize().initializer(byteCodeAppender);
                }

                public Builder<U> ignoreAlso(ElementMatcher<? super MethodDescription> elementMatcher) {
                    return materialize().ignoreAlso(elementMatcher);
                }

                public Builder<U> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize().ignoreAlso(latentMatcher);
                }

                public TypeVariableDefinition<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                    return materialize().typeVariable(str, collection);
                }

                public Builder<U> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
                    return materialize().transform(elementMatcher, transformer);
                }

                public FieldDefinition.Optional.Valuable<U> defineField(String str, TypeDefinition typeDefinition, int i) {
                    return materialize().defineField(str, typeDefinition, i);
                }

                public FieldDefinition.Valuable<U> field(LatentMatcher<? super FieldDescription> latentMatcher) {
                    return materialize().field(latentMatcher);
                }

                public MethodDefinition.ParameterDefinition.Initial<U> defineMethod(String str, TypeDefinition typeDefinition, int i) {
                    return materialize().defineMethod(str, typeDefinition, i);
                }

                public MethodDefinition.ParameterDefinition.Initial<U> defineConstructor(int i) {
                    return materialize().defineConstructor(i);
                }

                public MethodDefinition.ImplementationDefinition<U> invokable(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize().invokable(latentMatcher);
                }

                public Builder<U> require(Collection<DynamicType> collection) {
                    return materialize().require(collection);
                }

                public Unloaded<U> make() {
                    return materialize().make();
                }

                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy) {
                    return materialize().make(typeResolutionStrategy);
                }

                public Unloaded<U> make(TypePool typePool) {
                    return materialize().make(typePool);
                }

                public Unloaded<U> make(TypeResolutionStrategy typeResolutionStrategy, TypePool typePool) {
                    return materialize().make(typeResolutionStrategy, typePool);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class Adapter<U> extends AbstractBase<U> {
                protected final AnnotationRetention annotationRetention;
                protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
                protected final AsmVisitorWrapper asmVisitorWrapper;
                protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
                protected final List<? extends DynamicType> auxiliaryTypes;
                protected final ClassFileVersion classFileVersion;
                protected final ClassWriterStrategy classWriterStrategy;
                protected final FieldRegistry fieldRegistry;
                protected final LatentMatcher<? super MethodDescription> ignoredMethods;
                protected final Implementation.Context.Factory implementationContextFactory;
                protected final InstrumentedType.WithFlexibleName instrumentedType;
                protected final MethodGraph.Compiler methodGraphCompiler;
                protected final MethodRegistry methodRegistry;
                protected final TypeAttributeAppender typeAttributeAppender;
                protected final TypeValidation typeValidation;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Adapter adapter = (Adapter) obj;
                    return this.annotationRetention.equals(adapter.annotationRetention) && this.typeValidation.equals(adapter.typeValidation) && this.instrumentedType.equals(adapter.instrumentedType) && this.fieldRegistry.equals(adapter.fieldRegistry) && this.methodRegistry.equals(adapter.methodRegistry) && this.typeAttributeAppender.equals(adapter.typeAttributeAppender) && this.asmVisitorWrapper.equals(adapter.asmVisitorWrapper) && this.classFileVersion.equals(adapter.classFileVersion) && this.auxiliaryTypeNamingStrategy.equals(adapter.auxiliaryTypeNamingStrategy) && this.annotationValueFilterFactory.equals(adapter.annotationValueFilterFactory) && this.implementationContextFactory.equals(adapter.implementationContextFactory) && this.methodGraphCompiler.equals(adapter.methodGraphCompiler) && this.classWriterStrategy.equals(adapter.classWriterStrategy) && this.ignoredMethods.equals(adapter.ignoredMethods) && this.auxiliaryTypes.equals(adapter.auxiliaryTypes);
                }

                public int hashCode() {
                    return ((((((((((((((((((((((((((((527 + this.instrumentedType.hashCode()) * 31) + this.fieldRegistry.hashCode()) * 31) + this.methodRegistry.hashCode()) * 31) + this.typeAttributeAppender.hashCode()) * 31) + this.asmVisitorWrapper.hashCode()) * 31) + this.classFileVersion.hashCode()) * 31) + this.auxiliaryTypeNamingStrategy.hashCode()) * 31) + this.annotationValueFilterFactory.hashCode()) * 31) + this.annotationRetention.hashCode()) * 31) + this.implementationContextFactory.hashCode()) * 31) + this.methodGraphCompiler.hashCode()) * 31) + this.typeValidation.hashCode()) * 31) + this.classWriterStrategy.hashCode()) * 31) + this.ignoredMethods.hashCode()) * 31) + this.auxiliaryTypes.hashCode();
                }

                /* access modifiers changed from: protected */
                public abstract Builder<U> materialize(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry2, MethodRegistry methodRegistry2, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, ClassFileVersion classFileVersion2, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, LatentMatcher<? super MethodDescription> latentMatcher, List<? extends DynamicType> list);

                protected Adapter(InstrumentedType.WithFlexibleName withFlexibleName, FieldRegistry fieldRegistry2, MethodRegistry methodRegistry2, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, ClassFileVersion classFileVersion2, AuxiliaryType.NamingStrategy namingStrategy, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, Implementation.Context.Factory factory2, MethodGraph.Compiler compiler, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, LatentMatcher<? super MethodDescription> latentMatcher, List<? extends DynamicType> list) {
                    this.instrumentedType = withFlexibleName;
                    this.fieldRegistry = fieldRegistry2;
                    this.methodRegistry = methodRegistry2;
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
                }

                public FieldDefinition.Optional.Valuable<U> defineField(String str, TypeDefinition typeDefinition, int i) {
                    return new FieldDefinitionAdapter(this, new FieldDescription.Token(str, i, typeDefinition.asGenericType()));
                }

                public FieldDefinition.Valuable<U> field(LatentMatcher<? super FieldDescription> latentMatcher) {
                    return new FieldMatchAdapter(this, latentMatcher);
                }

                public MethodDefinition.ParameterDefinition.Initial<U> defineMethod(String str, TypeDefinition typeDefinition, int i) {
                    return new MethodDefinitionAdapter(new MethodDescription.Token(str, i, typeDefinition.asGenericType()));
                }

                public MethodDefinition.ParameterDefinition.Initial<U> defineConstructor(int i) {
                    return new MethodDefinitionAdapter(new MethodDescription.Token(i));
                }

                public MethodDefinition.ImplementationDefinition<U> invokable(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return new MethodMatchAdapter(latentMatcher);
                }

                public MethodDefinition.ImplementationDefinition.Optional<U> implement(Collection<? extends TypeDefinition> collection) {
                    return new OptionalMethodMatchAdapter(new TypeList.Generic.Explicit((List<? extends TypeDefinition>) new ArrayList(collection)));
                }

                public Builder<U> ignoreAlso(LatentMatcher<? super MethodDescription> latentMatcher) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, new LatentMatcher.Disjunction((LatentMatcher<? super S>[]) new LatentMatcher[]{this.ignoredMethods, latentMatcher}), this.auxiliaryTypes);
                }

                public Builder<U> initializer(ByteCodeAppender byteCodeAppender) {
                    return materialize(this.instrumentedType.withInitializer(byteCodeAppender), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> initializer(LoadedTypeInitializer loadedTypeInitializer) {
                    return materialize(this.instrumentedType.withInitializer(loadedTypeInitializer), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> name(String str) {
                    return materialize(this.instrumentedType.withName(str), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> modifiers(int i) {
                    return materialize(this.instrumentedType.withModifiers(i), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> merge(Collection<? extends ModifierContributor.ForType> collection) {
                    return materialize(this.instrumentedType.withModifiers(ModifierContributor.Resolver.of(collection).resolve(this.instrumentedType.getModifiers())), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> topLevelType() {
                    return materialize(this.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingType(TypeDescription.UNDEFINED).withLocalClass(false), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public InnerTypeDefinition.ForType<U> innerTypeOf(TypeDescription typeDescription) {
                    return new InnerTypeDefinitionForTypeAdapter(typeDescription);
                }

                public InnerTypeDefinition<U> innerTypeOf(MethodDescription.InDefinedShape inDefinedShape) {
                    return inDefinedShape.isTypeInitializer() ? new InnerTypeDefinitionForTypeAdapter(inDefinedShape.getDeclaringType()) : new InnerTypeDefinitionForMethodAdapter(inDefinedShape);
                }

                public Builder<U> declaredTypes(Collection<? extends TypeDescription> collection) {
                    return materialize(this.instrumentedType.withDeclaredTypes(new TypeList.Explicit((List<? extends TypeDescription>) new ArrayList(collection))), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> nestHost(TypeDescription typeDescription) {
                    return materialize(this.instrumentedType.withNestHost(typeDescription), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> nestMembers(Collection<? extends TypeDescription> collection) {
                    return materialize(this.instrumentedType.withNestMembers(new TypeList.Explicit((List<? extends TypeDescription>) new ArrayList(collection))), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public TypeVariableDefinition<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                    return new TypeVariableDefinitionAdapter(new TypeVariableToken(str, new TypeList.Generic.Explicit((List<? extends TypeDefinition>) new ArrayList(collection))));
                }

                public Builder<U> transform(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
                    return materialize(this.instrumentedType.withTypeVariables(elementMatcher, transformer), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> attribute(TypeAttributeAppender typeAttributeAppender2) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, new TypeAttributeAppender.Compound(this.typeAttributeAppender, typeAttributeAppender2), this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> annotateType(Collection<? extends AnnotationDescription> collection) {
                    return materialize(this.instrumentedType.withAnnotations(new ArrayList(collection)), this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> visit(AsmVisitorWrapper asmVisitorWrapper2) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, new AsmVisitorWrapper.Compound(this.asmVisitorWrapper, asmVisitorWrapper2), this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, this.auxiliaryTypes);
                }

                public Builder<U> require(Collection<DynamicType> collection) {
                    return materialize(this.instrumentedType, this.fieldRegistry, this.methodRegistry, this.typeAttributeAppender, this.asmVisitorWrapper, this.classFileVersion, this.auxiliaryTypeNamingStrategy, this.annotationValueFilterFactory, this.annotationRetention, this.implementationContextFactory, this.methodGraphCompiler, this.typeValidation, this.classWriterStrategy, this.ignoredMethods, CompoundList.of(this.auxiliaryTypes, (List<? extends DynamicType>) new ArrayList(collection)));
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class InnerTypeDefinitionForTypeAdapter extends Delegator<U> implements InnerTypeDefinition.ForType<U> {
                    private final TypeDescription typeDescription;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        InnerTypeDefinitionForTypeAdapter innerTypeDefinitionForTypeAdapter = (InnerTypeDefinitionForTypeAdapter) obj;
                        return this.typeDescription.equals(innerTypeDefinitionForTypeAdapter.typeDescription) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return ((527 + this.typeDescription.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected InnerTypeDefinitionForTypeAdapter(TypeDescription typeDescription2) {
                        this.typeDescription = typeDescription2;
                    }

                    public Builder<U> asAnonymousType() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingType(this.typeDescription).withAnonymousClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    public Builder<U> asMemberType() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withDeclaringType(this.typeDescription).withEnclosingType(this.typeDescription).withAnonymousClass(false).withLocalClass(false), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    /* access modifiers changed from: protected */
                    public Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingType(this.typeDescription).withLocalClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class InnerTypeDefinitionForMethodAdapter extends Delegator<U> implements InnerTypeDefinition<U> {
                    private final MethodDescription.InDefinedShape methodDescription;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        InnerTypeDefinitionForMethodAdapter innerTypeDefinitionForMethodAdapter = (InnerTypeDefinitionForMethodAdapter) obj;
                        return this.methodDescription.equals(innerTypeDefinitionForMethodAdapter.methodDescription) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return ((527 + this.methodDescription.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected InnerTypeDefinitionForMethodAdapter(MethodDescription.InDefinedShape inDefinedShape) {
                        this.methodDescription = inDefinedShape;
                    }

                    public Builder<U> asAnonymousType() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingMethod(this.methodDescription).withAnonymousClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    /* access modifiers changed from: protected */
                    public Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withDeclaringType(TypeDescription.UNDEFINED).withEnclosingMethod(this.methodDescription).withLocalClass(true), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class TypeVariableDefinitionAdapter extends TypeVariableDefinition.AbstractBase<U> {
                    private final TypeVariableToken token;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        TypeVariableDefinitionAdapter typeVariableDefinitionAdapter = (TypeVariableDefinitionAdapter) obj;
                        return this.token.equals(typeVariableDefinitionAdapter.token) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return ((527 + this.token.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected TypeVariableDefinitionAdapter(TypeVariableToken typeVariableToken) {
                        this.token = typeVariableToken;
                    }

                    public TypeVariableDefinition<U> annotateTypeVariable(Collection<? extends AnnotationDescription> collection) {
                        return new TypeVariableDefinitionAdapter(new TypeVariableToken(this.token.getSymbol(), this.token.getBounds(), CompoundList.of(this.token.getAnnotations(), (AnnotationList) new ArrayList(collection))));
                    }

                    /* access modifiers changed from: protected */
                    public Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withTypeVariable(this.token), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class FieldDefinitionAdapter extends FieldDefinition.Optional.Valuable.AbstractBase.Adapter<U> {
                    private final FieldDescription.Token token;

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
                        FieldDefinitionAdapter fieldDefinitionAdapter = (FieldDefinitionAdapter) obj;
                        return this.token.equals(fieldDefinitionAdapter.token) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.token.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected FieldDefinitionAdapter(Adapter adapter, FieldDescription.Token token2) {
                        this(FieldAttributeAppender.ForInstrumentedField.INSTANCE, Transformer.NoOp.make(), FieldDescription.NO_DEFAULT_VALUE, token2);
                    }

                    protected FieldDefinitionAdapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj, FieldDescription.Token token2) {
                        super(factory, transformer, obj);
                        this.token = token2;
                    }

                    public FieldDefinition.Optional<U> annotateField(Collection<? extends AnnotationDescription> collection) {
                        return new FieldDefinitionAdapter(this.fieldAttributeAppenderFactory, this.transformer, this.defaultValue, new FieldDescription.Token(this.token.getName(), this.token.getModifiers(), this.token.getType(), CompoundList.of(this.token.getAnnotations(), (AnnotationList) new ArrayList(collection))));
                    }

                    /* access modifiers changed from: protected */
                    public Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withField(this.token), Adapter.this.fieldRegistry.prepend(new LatentMatcher.ForFieldToken(this.token), this.fieldAttributeAppenderFactory, this.defaultValue, this.transformer), Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    /* access modifiers changed from: protected */
                    public FieldDefinition.Optional<U> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj) {
                        return new FieldDefinitionAdapter(factory, transformer, obj, this.token);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class FieldMatchAdapter extends FieldDefinition.Optional.Valuable.AbstractBase.Adapter<U> {
                    private final LatentMatcher<? super FieldDescription> matcher;

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
                        FieldMatchAdapter fieldMatchAdapter = (FieldMatchAdapter) obj;
                        return this.matcher.equals(fieldMatchAdapter.matcher) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return (((super.hashCode() * 31) + this.matcher.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected FieldMatchAdapter(Adapter adapter, LatentMatcher<? super FieldDescription> latentMatcher) {
                        this(FieldAttributeAppender.NoOp.INSTANCE, Transformer.NoOp.make(), FieldDescription.NO_DEFAULT_VALUE, latentMatcher);
                    }

                    protected FieldMatchAdapter(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj, LatentMatcher<? super FieldDescription> latentMatcher) {
                        super(factory, transformer, obj);
                        this.matcher = latentMatcher;
                    }

                    public FieldDefinition.Optional<U> annotateField(Collection<? extends AnnotationDescription> collection) {
                        return attribute(new FieldAttributeAppender.Explicit(new ArrayList(collection)));
                    }

                    /* access modifiers changed from: protected */
                    public Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType, Adapter.this.fieldRegistry.prepend(this.matcher, this.fieldAttributeAppenderFactory, this.defaultValue, this.transformer), Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    /* access modifiers changed from: protected */
                    public FieldDefinition.Optional<U> materialize(FieldAttributeAppender.Factory factory, Transformer<FieldDescription> transformer, Object obj) {
                        return new FieldMatchAdapter(factory, transformer, obj, this.matcher);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class MethodDefinitionAdapter extends MethodDefinition.ParameterDefinition.Initial.AbstractBase<U> {
                    /* access modifiers changed from: private */
                    public final MethodDescription.Token token;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        MethodDefinitionAdapter methodDefinitionAdapter = (MethodDefinitionAdapter) obj;
                        return this.token.equals(methodDefinitionAdapter.token) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return ((527 + this.token.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected MethodDefinitionAdapter(MethodDescription.Token token2) {
                        this.token = token2;
                    }

                    public MethodDefinition.ParameterDefinition.Annotatable<U> withParameter(TypeDefinition typeDefinition, String str, int i) {
                        return new ParameterAnnotationAdapter(new ParameterDescription.Token(typeDefinition.asGenericType(), str, Integer.valueOf(i)));
                    }

                    public MethodDefinition.ParameterDefinition.Simple.Annotatable<U> withParameter(TypeDefinition typeDefinition) {
                        return new SimpleParameterAnnotationAdapter(new ParameterDescription.Token(typeDefinition.asGenericType()));
                    }

                    public MethodDefinition.ExceptionDefinition<U> throwing(Collection<? extends TypeDefinition> collection) {
                        return new MethodDefinitionAdapter(new MethodDescription.Token(this.token.getName(), this.token.getModifiers(), this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), CompoundList.of(this.token.getExceptionTypes(), (TypeList.Generic) new TypeList.Generic.Explicit((List<? extends TypeDefinition>) new ArrayList(collection))), this.token.getAnnotations(), this.token.getDefaultValue(), this.token.getReceiverType()));
                    }

                    public MethodDefinition.TypeVariableDefinition.Annotatable<U> typeVariable(String str, Collection<? extends TypeDefinition> collection) {
                        return new TypeVariableAnnotationAdapter(new TypeVariableToken(str, new TypeList.Generic.Explicit((List<? extends TypeDefinition>) new ArrayList(collection))));
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return materialize(new MethodRegistry.Handler.ForImplementation(implementation));
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        int i;
                        Adapter adapter = Adapter.this;
                        String name = this.token.getName();
                        if ((this.token.getModifiers() & 256) == 0) {
                            i = ModifierContributor.Resolver.of(MethodManifestation.ABSTRACT).resolve(this.token.getModifiers());
                        } else {
                            i = this.token.getModifiers();
                        }
                        return new MethodDefinitionAdapter(new MethodDescription.Token(name, i, this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), this.token.getExceptionTypes(), this.token.getAnnotations(), this.token.getDefaultValue(), this.token.getReceiverType())).materialize(MethodRegistry.Handler.ForAbstractMethod.INSTANCE);
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return new MethodDefinitionAdapter(new MethodDescription.Token(this.token.getName(), ModifierContributor.Resolver.of(MethodManifestation.ABSTRACT).resolve(this.token.getModifiers()), this.token.getTypeVariableTokens(), this.token.getReturnType(), this.token.getParameterTokens(), this.token.getExceptionTypes(), this.token.getAnnotations(), annotationValue, this.token.getReceiverType())).materialize(new MethodRegistry.Handler.ForAnnotationValue(annotationValue));
                    }

                    private MethodDefinition.ReceiverTypeDefinition<U> materialize(MethodRegistry.Handler handler) {
                        return new AnnotationAdapter(this, handler);
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class TypeVariableAnnotationAdapter extends MethodDefinition.TypeVariableDefinition.Annotatable.AbstractBase.Adapter<U> {
                        private final TypeVariableToken token;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            TypeVariableAnnotationAdapter typeVariableAnnotationAdapter = (TypeVariableAnnotationAdapter) obj;
                            return this.token.equals(typeVariableAnnotationAdapter.token) && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        public int hashCode() {
                            return ((527 + this.token.hashCode()) * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected TypeVariableAnnotationAdapter(TypeVariableToken typeVariableToken) {
                            this.token = typeVariableToken;
                        }

                        /* access modifiers changed from: protected */
                        public MethodDefinition.ParameterDefinition<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), CompoundList.of(MethodDefinitionAdapter.this.token.getTypeVariableTokens(), this.token), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }

                        public MethodDefinition.TypeVariableDefinition.Annotatable<U> annotateTypeVariable(Collection<? extends AnnotationDescription> collection) {
                            return new TypeVariableAnnotationAdapter(new TypeVariableToken(this.token.getSymbol(), this.token.getBounds(), CompoundList.of(this.token.getAnnotations(), (AnnotationList) new ArrayList(collection))));
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class ParameterAnnotationAdapter extends MethodDefinition.ParameterDefinition.Annotatable.AbstractBase.Adapter<U> {
                        private final ParameterDescription.Token token;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            ParameterAnnotationAdapter parameterAnnotationAdapter = (ParameterAnnotationAdapter) obj;
                            return this.token.equals(parameterAnnotationAdapter.token) && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        public int hashCode() {
                            return ((527 + this.token.hashCode()) * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected ParameterAnnotationAdapter(ParameterDescription.Token token2) {
                            this.token = token2;
                        }

                        public MethodDefinition.ParameterDefinition.Annotatable<U> annotateParameter(Collection<? extends AnnotationDescription> collection) {
                            return new ParameterAnnotationAdapter(new ParameterDescription.Token(this.token.getType(), CompoundList.of(this.token.getAnnotations(), (AnnotationList) new ArrayList(collection)), this.token.getName(), this.token.getModifiers()));
                        }

                        /* access modifiers changed from: protected */
                        public MethodDefinition.ParameterDefinition<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), CompoundList.of(MethodDefinitionAdapter.this.token.getParameterTokens(), this.token), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class SimpleParameterAnnotationAdapter extends MethodDefinition.ParameterDefinition.Simple.Annotatable.AbstractBase.Adapter<U> {
                        private final ParameterDescription.Token token;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            SimpleParameterAnnotationAdapter simpleParameterAnnotationAdapter = (SimpleParameterAnnotationAdapter) obj;
                            return this.token.equals(simpleParameterAnnotationAdapter.token) && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        public int hashCode() {
                            return ((527 + this.token.hashCode()) * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected SimpleParameterAnnotationAdapter(ParameterDescription.Token token2) {
                            this.token = token2;
                        }

                        public MethodDefinition.ParameterDefinition.Simple.Annotatable<U> annotateParameter(Collection<? extends AnnotationDescription> collection) {
                            return new SimpleParameterAnnotationAdapter(new ParameterDescription.Token(this.token.getType(), CompoundList.of(this.token.getAnnotations(), (AnnotationList) new ArrayList(collection)), this.token.getName(), this.token.getModifiers()));
                        }

                        /* access modifiers changed from: protected */
                        public MethodDefinition.ParameterDefinition.Simple<U> materialize() {
                            return new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), CompoundList.of(MethodDefinitionAdapter.this.token.getParameterTokens(), this.token), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotationAdapter extends MethodDefinition.AbstractBase.Adapter<U> {
                        public boolean equals(Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && MethodDefinitionAdapter.this.equals(MethodDefinitionAdapter.this);
                        }

                        public int hashCode() {
                            return (super.hashCode() * 31) + MethodDefinitionAdapter.this.hashCode();
                        }

                        protected AnnotationAdapter(MethodDefinitionAdapter methodDefinitionAdapter, MethodRegistry.Handler handler) {
                            this(handler, MethodAttributeAppender.ForInstrumentedMethod.INCLUDING_RECEIVER, Transformer.NoOp.make());
                        }

                        protected AnnotationAdapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            super(handler, factory, transformer);
                        }

                        public MethodDefinition<U> receiverType(TypeDescription.Generic generic) {
                            MethodDefinitionAdapter methodDefinitionAdapter = new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), generic));
                            methodDefinitionAdapter.getClass();
                            return new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        public MethodDefinition<U> annotateMethod(Collection<? extends AnnotationDescription> collection) {
                            MethodDefinitionAdapter methodDefinitionAdapter = new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), MethodDefinitionAdapter.this.token.getParameterTokens(), MethodDefinitionAdapter.this.token.getExceptionTypes(), CompoundList.of(MethodDefinitionAdapter.this.token.getAnnotations(), (AnnotationList) new ArrayList(collection)), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                            methodDefinitionAdapter.getClass();
                            return new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        public MethodDefinition<U> annotateParameter(int i, Collection<? extends AnnotationDescription> collection) {
                            ArrayList arrayList = new ArrayList(MethodDefinitionAdapter.this.token.getParameterTokens());
                            arrayList.set(i, new ParameterDescription.Token(((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getType(), CompoundList.of(((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getAnnotations(), (AnnotationList) new ArrayList(collection)), ((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getName(), ((ParameterDescription.Token) MethodDefinitionAdapter.this.token.getParameterTokens().get(i)).getModifiers()));
                            MethodDefinitionAdapter methodDefinitionAdapter = new MethodDefinitionAdapter(new MethodDescription.Token(MethodDefinitionAdapter.this.token.getName(), MethodDefinitionAdapter.this.token.getModifiers(), MethodDefinitionAdapter.this.token.getTypeVariableTokens(), MethodDefinitionAdapter.this.token.getReturnType(), arrayList, MethodDefinitionAdapter.this.token.getExceptionTypes(), MethodDefinitionAdapter.this.token.getAnnotations(), MethodDefinitionAdapter.this.token.getDefaultValue(), MethodDefinitionAdapter.this.token.getReceiverType()));
                            methodDefinitionAdapter.getClass();
                            return new AnnotationAdapter(this.handler, this.methodAttributeAppenderFactory, this.transformer);
                        }

                        /* access modifiers changed from: protected */
                        public MethodDefinition<U> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            return new AnnotationAdapter(handler, factory, transformer);
                        }

                        /* access modifiers changed from: protected */
                        public Builder<U> materialize() {
                            return Adapter.this.materialize(Adapter.this.instrumentedType.withMethod(MethodDefinitionAdapter.this.token), Adapter.this.fieldRegistry, Adapter.this.methodRegistry.prepend(new LatentMatcher.ForMethodToken(MethodDefinitionAdapter.this.token), this.handler, this.methodAttributeAppenderFactory, this.transformer), Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class MethodMatchAdapter extends MethodDefinition.ImplementationDefinition.AbstractBase<U> {
                    /* access modifiers changed from: private */
                    public final LatentMatcher<? super MethodDescription> matcher;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        MethodMatchAdapter methodMatchAdapter = (MethodMatchAdapter) obj;
                        return this.matcher.equals(methodMatchAdapter.matcher) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return ((527 + this.matcher.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected MethodMatchAdapter(LatentMatcher<? super MethodDescription> latentMatcher) {
                        this.matcher = latentMatcher;
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return materialize(new MethodRegistry.Handler.ForImplementation(implementation));
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        return materialize(MethodRegistry.Handler.ForAbstractMethod.INSTANCE);
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return materialize(new MethodRegistry.Handler.ForAnnotationValue(annotationValue));
                    }

                    private MethodDefinition.ReceiverTypeDefinition<U> materialize(MethodRegistry.Handler handler) {
                        return new AnnotationAdapter(this, handler);
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotationAdapter extends MethodDefinition.AbstractBase.Adapter<U> {
                        public boolean equals(Object obj) {
                            if (!super.equals(obj)) {
                                return false;
                            }
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && MethodMatchAdapter.this.equals(MethodMatchAdapter.this);
                        }

                        public int hashCode() {
                            return (super.hashCode() * 31) + MethodMatchAdapter.this.hashCode();
                        }

                        protected AnnotationAdapter(MethodMatchAdapter methodMatchAdapter, MethodRegistry.Handler handler) {
                            this(handler, MethodAttributeAppender.NoOp.INSTANCE, Transformer.NoOp.make());
                        }

                        protected AnnotationAdapter(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            super(handler, factory, transformer);
                        }

                        public MethodDefinition<U> receiverType(TypeDescription.Generic generic) {
                            return new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.ForReceiverType(generic)), this.transformer);
                        }

                        public MethodDefinition<U> annotateMethod(Collection<? extends AnnotationDescription> collection) {
                            return new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.Explicit(new ArrayList(collection))), this.transformer);
                        }

                        public MethodDefinition<U> annotateParameter(int i, Collection<? extends AnnotationDescription> collection) {
                            return new AnnotationAdapter(this.handler, new MethodAttributeAppender.Factory.Compound(this.methodAttributeAppenderFactory, new MethodAttributeAppender.Explicit(i, (List<? extends AnnotationDescription>) new ArrayList(collection))), this.transformer);
                        }

                        /* access modifiers changed from: protected */
                        public MethodDefinition<U> materialize(MethodRegistry.Handler handler, MethodAttributeAppender.Factory factory, Transformer<MethodDescription> transformer) {
                            return new AnnotationAdapter(handler, factory, transformer);
                        }

                        /* access modifiers changed from: protected */
                        public Builder<U> materialize() {
                            return Adapter.this.materialize(Adapter.this.instrumentedType, Adapter.this.fieldRegistry, Adapter.this.methodRegistry.prepend(MethodMatchAdapter.this.matcher, this.handler, this.methodAttributeAppenderFactory, this.transformer), Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class OptionalMethodMatchAdapter extends Delegator<U> implements MethodDefinition.ImplementationDefinition.Optional<U> {
                    private final TypeList.Generic interfaces;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        OptionalMethodMatchAdapter optionalMethodMatchAdapter = (OptionalMethodMatchAdapter) obj;
                        return this.interfaces.equals(optionalMethodMatchAdapter.interfaces) && Adapter.this.equals(Adapter.this);
                    }

                    public int hashCode() {
                        return ((527 + this.interfaces.hashCode()) * 31) + Adapter.this.hashCode();
                    }

                    protected OptionalMethodMatchAdapter(TypeList.Generic generic) {
                        this.interfaces = generic;
                    }

                    /* access modifiers changed from: protected */
                    public Builder<U> materialize() {
                        Adapter adapter = Adapter.this;
                        return adapter.materialize(adapter.instrumentedType.withInterfaces(this.interfaces), Adapter.this.fieldRegistry, Adapter.this.methodRegistry, Adapter.this.typeAttributeAppender, Adapter.this.asmVisitorWrapper, Adapter.this.classFileVersion, Adapter.this.auxiliaryTypeNamingStrategy, Adapter.this.annotationValueFilterFactory, Adapter.this.annotationRetention, Adapter.this.implementationContextFactory, Adapter.this.methodGraphCompiler, Adapter.this.typeValidation, Adapter.this.classWriterStrategy, Adapter.this.ignoredMethods, Adapter.this.auxiliaryTypes);
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> intercept(Implementation implementation) {
                        return interfaceType().intercept(implementation);
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> withoutCode() {
                        return interfaceType().withoutCode();
                    }

                    public MethodDefinition.ReceiverTypeDefinition<U> defaultValue(AnnotationValue<?, ?> annotationValue) {
                        return interfaceType().defaultValue(annotationValue);
                    }

                    public <V> MethodDefinition.ReceiverTypeDefinition<U> defaultValue(V v, Class<? extends V> cls) {
                        return interfaceType().defaultValue(v, cls);
                    }

                    private MethodDefinition.ImplementationDefinition<U> interfaceType() {
                        ElementMatcher.Junction none = ElementMatchers.none();
                        for (TypeDescription isSuperTypeOf : this.interfaces.asErasures()) {
                            none = none.or(ElementMatchers.isSuperTypeOf(isSuperTypeOf));
                        }
                        return materialize().invokable((ElementMatcher<? super MethodDescription>) ElementMatchers.isDeclaredBy((ElementMatcher<? super TypeDescription>) ElementMatchers.isInterface().and(none)));
                    }
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Default implements DynamicType {
        private static final int BUFFER_SIZE = 1024;
        private static final String CLASS_FILE_EXTENSION = ".class";
        protected static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private static final int END_OF_FILE = -1;
        private static final int FROM_BEGINNING = 0;
        private static final String MANIFEST_VERSION = "1.0";
        private static final String TEMP_SUFFIX = "tmp";
        protected final List<? extends DynamicType> auxiliaryTypes;
        protected final byte[] binaryRepresentation;
        protected final LoadedTypeInitializer loadedTypeInitializer;
        protected final TypeDescription typeDescription;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Default defaultR = (Default) obj;
            return this.typeDescription.equals(defaultR.typeDescription) && Arrays.equals(this.binaryRepresentation, defaultR.binaryRepresentation) && this.loadedTypeInitializer.equals(defaultR.loadedTypeInitializer) && this.auxiliaryTypes.equals(defaultR.auxiliaryTypes);
        }

        public int hashCode() {
            return ((((((527 + this.typeDescription.hashCode()) * 31) + Arrays.hashCode(this.binaryRepresentation)) * 31) + this.loadedTypeInitializer.hashCode()) * 31) + this.auxiliaryTypes.hashCode();
        }

        public Default(TypeDescription typeDescription2, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer2, List<? extends DynamicType> list) {
            this.typeDescription = typeDescription2;
            this.binaryRepresentation = bArr;
            this.loadedTypeInitializer = loadedTypeInitializer2;
            this.auxiliaryTypes = list;
        }

        public TypeDescription getTypeDescription() {
            return this.typeDescription;
        }

        public Map<TypeDescription, byte[]> getAllTypes() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put(this.typeDescription, this.binaryRepresentation);
            for (DynamicType allTypes : this.auxiliaryTypes) {
                linkedHashMap.putAll(allTypes.getAllTypes());
            }
            return linkedHashMap;
        }

        public Map<TypeDescription, LoadedTypeInitializer> getLoadedTypeInitializers() {
            HashMap hashMap = new HashMap();
            for (DynamicType loadedTypeInitializers : this.auxiliaryTypes) {
                hashMap.putAll(loadedTypeInitializers.getLoadedTypeInitializers());
            }
            hashMap.put(this.typeDescription, this.loadedTypeInitializer);
            return hashMap;
        }

        public boolean hasAliveLoadedTypeInitializers() {
            for (LoadedTypeInitializer isAlive : getLoadedTypeInitializers().values()) {
                if (isAlive.isAlive()) {
                    return true;
                }
            }
            return false;
        }

        public byte[] getBytes() {
            return this.binaryRepresentation;
        }

        public Map<TypeDescription, byte[]> getAuxiliaryTypes() {
            HashMap hashMap = new HashMap();
            for (DynamicType dynamicType : this.auxiliaryTypes) {
                hashMap.put(dynamicType.getTypeDescription(), dynamicType.getBytes());
                hashMap.putAll(dynamicType.getAuxiliaryTypes());
            }
            return hashMap;
        }

        /* JADX INFO: finally extract failed */
        public Map<TypeDescription, File> saveIn(File file) throws IOException {
            HashMap hashMap = new HashMap();
            File file2 = new File(file, this.typeDescription.getName().replace('.', File.separatorChar) + ".class");
            if (file2.getParentFile() == null || file2.getParentFile().isDirectory() || file2.getParentFile().mkdirs()) {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                try {
                    fileOutputStream.write(this.binaryRepresentation);
                    fileOutputStream.close();
                    hashMap.put(this.typeDescription, file2);
                    for (DynamicType saveIn : this.auxiliaryTypes) {
                        hashMap.putAll(saveIn.saveIn(file));
                    }
                    return hashMap;
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } else {
                throw new IllegalArgumentException("Could not create directory: " + file2.getParentFile());
            }
        }

        public File inject(File file, File file2) throws IOException {
            if (file.equals(file2)) {
                return inject(file);
            }
            return doInject(file, file2);
        }

        public File inject(File file) throws IOException {
            boolean delete;
            File doInject = doInject(file, File.createTempFile(file.getName(), TEMP_SUFFIX));
            try {
                if (DISPATCHER.copy(doInject, file) && !delete) {
                }
                return file;
            } finally {
                if (!doInject.delete()) {
                    doInject.deleteOnExit();
                }
            }
        }

        private File doInject(File file, File file2) throws IOException {
            JarOutputStream jarOutputStream;
            JarInputStream jarInputStream = new JarInputStream(new FileInputStream(file));
            try {
                if (!file2.isFile()) {
                    if (!file2.createNewFile()) {
                        throw new IllegalArgumentException("Could not create file: " + file2);
                    }
                }
                Manifest manifest = jarInputStream.getManifest();
                jarOutputStream = manifest == null ? new JarOutputStream(new FileOutputStream(file2)) : new JarOutputStream(new FileOutputStream(file2), manifest);
                Map<TypeDescription, byte[]> auxiliaryTypes2 = getAuxiliaryTypes();
                HashMap hashMap = new HashMap();
                for (Map.Entry next : auxiliaryTypes2.entrySet()) {
                    hashMap.put(((TypeDescription) next.getKey()).getInternalName() + ".class", next.getValue());
                }
                hashMap.put(this.typeDescription.getInternalName() + ".class", this.binaryRepresentation);
                while (true) {
                    JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
                    if (nextJarEntry == null) {
                        break;
                    }
                    byte[] bArr = (byte[]) hashMap.remove(nextJarEntry.getName());
                    if (bArr == null) {
                        jarOutputStream.putNextEntry(nextJarEntry);
                        byte[] bArr2 = new byte[1024];
                        while (true) {
                            int read = jarInputStream.read(bArr2);
                            if (read == -1) {
                                break;
                            }
                            jarOutputStream.write(bArr2, 0, read);
                        }
                    } else {
                        jarOutputStream.putNextEntry(new JarEntry(nextJarEntry.getName()));
                        jarOutputStream.write(bArr);
                    }
                    jarInputStream.closeEntry();
                    jarOutputStream.closeEntry();
                }
                for (Map.Entry entry : hashMap.entrySet()) {
                    jarOutputStream.putNextEntry(new JarEntry((String) entry.getKey()));
                    jarOutputStream.write((byte[]) entry.getValue());
                    jarOutputStream.closeEntry();
                }
                jarOutputStream.close();
                jarInputStream.close();
                return file2;
            } catch (Throwable th) {
                jarInputStream.close();
                throw th;
            }
        }

        public File toJar(File file) throws IOException {
            Manifest manifest = new Manifest();
            manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
            return toJar(file, manifest);
        }

        public File toJar(File file, Manifest manifest) throws IOException {
            if (file.isFile() || file.createNewFile()) {
                JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(file), manifest);
                try {
                    for (Map.Entry next : getAuxiliaryTypes().entrySet()) {
                        jarOutputStream.putNextEntry(new JarEntry(((TypeDescription) next.getKey()).getInternalName() + ".class"));
                        jarOutputStream.write((byte[]) next.getValue());
                        jarOutputStream.closeEntry();
                    }
                    jarOutputStream.putNextEntry(new JarEntry(this.typeDescription.getInternalName() + ".class"));
                    jarOutputStream.write(this.binaryRepresentation);
                    jarOutputStream.closeEntry();
                    return file;
                } finally {
                    jarOutputStream.close();
                }
            } else {
                throw new IllegalArgumentException("Could not create file: " + file);
            }
        }

        protected interface Dispatcher {
            boolean copy(File file, File file2) throws IOException;

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        Class<?> cls = Class.forName("java.nio.file.Path");
                        Object[] objArr = (Object[]) Array.newInstance(Class.forName("java.nio.file.CopyOption"), 1);
                        objArr[0] = Enum.valueOf(Class.forName("java.nio.file.StandardCopyOption"), "REPLACE_EXISTING");
                        return new ForJava7CapableVm(File.class.getMethod("toPath", new Class[0]), Class.forName("java.nio.file.Files").getMethod("move", new Class[]{cls, cls, objArr.getClass()}), objArr);
                    } catch (Throwable unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public boolean copy(File file, File file2) throws IOException {
                    FileOutputStream fileOutputStream;
                    FileInputStream fileInputStream = new FileInputStream(file);
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read != -1) {
                                fileOutputStream.write(bArr, 0, read);
                            } else {
                                fileOutputStream.close();
                                fileInputStream.close();
                                return true;
                            }
                        }
                    } catch (Throwable th) {
                        fileInputStream.close();
                        throw th;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava7CapableVm implements Dispatcher {
                private final Object[] copyOptions;
                private final Method move;
                private final Method toPath;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava7CapableVm forJava7CapableVm = (ForJava7CapableVm) obj;
                    return this.toPath.equals(forJava7CapableVm.toPath) && this.move.equals(forJava7CapableVm.move) && Arrays.equals(this.copyOptions, forJava7CapableVm.copyOptions);
                }

                public int hashCode() {
                    return ((((527 + this.toPath.hashCode()) * 31) + this.move.hashCode()) * 31) + Arrays.hashCode(this.copyOptions);
                }

                protected ForJava7CapableVm(Method method, Method method2, Object[] objArr) {
                    this.toPath = method;
                    this.move = method2;
                    this.copyOptions = objArr;
                }

                public boolean copy(File file, File file2) throws IOException {
                    try {
                        this.move.invoke((Object) null, new Object[]{this.toPath.invoke(file, new Object[0]), this.toPath.invoke(file2, new Object[0]), this.copyOptions});
                        return false;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access NIO file copy", e);
                    } catch (InvocationTargetException e2) {
                        Throwable cause = e2.getCause();
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        throw new IllegalStateException("Cannot execute NIO file copy", cause);
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Unloaded<T> extends Default implements Unloaded<T> {
            private final TypeResolutionStrategy.Resolved typeResolutionStrategy;

            public boolean equals(Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.typeResolutionStrategy.equals(((Unloaded) obj).typeResolutionStrategy);
            }

            public int hashCode() {
                return (super.hashCode() * 31) + this.typeResolutionStrategy.hashCode();
            }

            public Unloaded(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list, TypeResolutionStrategy.Resolved resolved) {
                super(typeDescription, bArr, loadedTypeInitializer, list);
                this.typeResolutionStrategy = resolved;
            }

            public Loaded<T> load(ClassLoader classLoader) {
                if (classLoader instanceof InjectionClassLoader) {
                    InjectionClassLoader injectionClassLoader = (InjectionClassLoader) classLoader;
                    if (!injectionClassLoader.isSealed()) {
                        return load(injectionClassLoader, InjectionClassLoader.Strategy.INSTANCE);
                    }
                }
                return load(classLoader, ClassLoadingStrategy.Default.WRAPPER);
            }

            public <S extends ClassLoader> Loaded<T> load(S s, ClassLoadingStrategy<? super S> classLoadingStrategy) {
                return new Loaded(this.typeDescription, this.binaryRepresentation, this.loadedTypeInitializer, this.auxiliaryTypes, this.typeResolutionStrategy.initialize(this, s, classLoadingStrategy));
            }

            public Unloaded<T> include(DynamicType... dynamicTypeArr) {
                return include((List<? extends DynamicType>) Arrays.asList(dynamicTypeArr));
            }

            public Unloaded<T> include(List<? extends DynamicType> list) {
                return new Unloaded(this.typeDescription, this.binaryRepresentation, this.loadedTypeInitializer, CompoundList.of(this.auxiliaryTypes, (List) list), this.typeResolutionStrategy);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class Loaded<T> extends Default implements Loaded<T> {
            private final Map<TypeDescription, Class<?>> loadedTypes;

            public boolean equals(Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.loadedTypes.equals(((Loaded) obj).loadedTypes);
            }

            public int hashCode() {
                return (super.hashCode() * 31) + this.loadedTypes.hashCode();
            }

            protected Loaded(TypeDescription typeDescription, byte[] bArr, LoadedTypeInitializer loadedTypeInitializer, List<? extends DynamicType> list, Map<TypeDescription, Class<?>> map) {
                super(typeDescription, bArr, loadedTypeInitializer, list);
                this.loadedTypes = map;
            }

            public Class<? extends T> getLoaded() {
                return this.loadedTypes.get(this.typeDescription);
            }

            public Map<TypeDescription, Class<?>> getLoadedAuxiliaryTypes() {
                HashMap hashMap = new HashMap(this.loadedTypes);
                hashMap.remove(this.typeDescription);
                return hashMap;
            }
        }
    }
}

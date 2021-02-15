package net.bytebuddy.matcher;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.matcher.CachingMatcher;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.MethodSortMatcher;
import net.bytebuddy.matcher.ModifierMatcher;
import net.bytebuddy.matcher.StringMatcher;
import net.bytebuddy.utility.JavaModule;

public final class ElementMatchers {
    private static final ClassLoader BOOTSTRAP_CLASSLOADER = null;

    private ElementMatchers() {
        throw new UnsupportedOperationException("This class is a utility class and not supposed to be instantiated");
    }

    public static <T> ElementMatcher.Junction<T> failSafe(ElementMatcher<? super T> elementMatcher) {
        return new FailSafeMatcher(elementMatcher, false);
    }

    public static <T> ElementMatcher.Junction<T> cached(ElementMatcher<? super T> elementMatcher, ConcurrentMap<? super T, Boolean> concurrentMap) {
        return new CachingMatcher(elementMatcher, concurrentMap);
    }

    public static <T> ElementMatcher.Junction<T> cached(ElementMatcher<? super T> elementMatcher, int i) {
        if (i >= 1) {
            return new CachingMatcher.WithInlineEviction(elementMatcher, new ConcurrentHashMap(), i);
        }
        throw new IllegalArgumentException("Eviction size must be a positive number: " + i);
    }

    public static <T> ElementMatcher.Junction<T> is(Object obj) {
        return obj == null ? new NullMatcher() : new EqualityMatcher(obj);
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> is(Field field) {
        return is((FieldDescription.InDefinedShape) new FieldDescription.ForLoadedField(field));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> is(FieldDescription.InDefinedShape inDefinedShape) {
        return definedField(new EqualityMatcher(inDefinedShape));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> definedField(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new DefinedShapeMatcher(elementMatcher);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> is(Method method) {
        return is((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> is(Constructor<?> constructor) {
        return is((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(constructor));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> is(MethodDescription.InDefinedShape inDefinedShape) {
        return definedMethod(new EqualityMatcher(inDefinedShape));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> definedMethod(ElementMatcher<? super MethodDescription.InDefinedShape> elementMatcher) {
        return new DefinedShapeMatcher(elementMatcher);
    }

    public static <T extends ParameterDescription> ElementMatcher.Junction<T> is(ParameterDescription.InDefinedShape inDefinedShape) {
        return definedParameter(new EqualityMatcher(inDefinedShape));
    }

    public static <T extends ParameterDescription> ElementMatcher.Junction<T> definedParameter(ElementMatcher<? super ParameterDescription.InDefinedShape> elementMatcher) {
        return new DefinedShapeMatcher(elementMatcher);
    }

    public static <T extends ParameterDescription> ElementMatcher.Junction<T> hasType(ElementMatcher<? super TypeDescription> elementMatcher) {
        return hasGenericType(erasure(elementMatcher));
    }

    public static <T extends ParameterDescription> ElementMatcher.Junction<T> hasGenericType(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return new MethodParameterTypeMatcher(elementMatcher);
    }

    public static <T extends ParameterDescription> ElementMatcher.Junction<T> isMandated() {
        return new ModifierMatcher(ModifierMatcher.Mode.MANDATED);
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> is(Type type) {
        return is((Object) TypeDefinition.Sort.describe(type));
    }

    public static <T extends AnnotationDescription> ElementMatcher.Junction<T> is(Annotation annotation) {
        return is((Object) AnnotationDescription.ForLoadedAnnotation.of(annotation));
    }

    public static <T> ElementMatcher.Junction<T> not(ElementMatcher<? super T> elementMatcher) {
        return new NegatingMatcher(elementMatcher);
    }

    public static <T> ElementMatcher.Junction<T> any() {
        return new BooleanMatcher(true);
    }

    public static <T> ElementMatcher.Junction<T> none() {
        return new BooleanMatcher(false);
    }

    public static <T> ElementMatcher.Junction<T> anyOf(Object... objArr) {
        return anyOf((Iterable<?>) Arrays.asList(objArr));
    }

    public static <T> ElementMatcher.Junction<T> anyOf(Iterable<?> iterable) {
        ElementMatcher.Junction<U> junction = null;
        for (Object next : iterable) {
            if (junction == null) {
                junction = is((Object) next);
            } else {
                junction = junction.or(is((Object) next));
            }
        }
        return junction == null ? none() : junction;
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> anyOf(Type... typeArr) {
        return anyOf((Iterable<?>) new TypeList.Generic.ForLoadedTypes(typeArr));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> anyOf(Constructor<?>... constructorArr) {
        return definedMethod(anyOf((Iterable<?>) new MethodList.ForLoadedMethods(constructorArr, new Method[0])));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> anyOf(Method... methodArr) {
        return definedMethod(anyOf((Iterable<?>) new MethodList.ForLoadedMethods((Constructor<?>[]) new Constructor[0], methodArr)));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> anyOf(Field... fieldArr) {
        return definedField(anyOf((Iterable<?>) new FieldList.ForLoadedFields(fieldArr)));
    }

    public static <T extends AnnotationDescription> ElementMatcher.Junction<T> anyOf(Annotation... annotationArr) {
        return anyOf((Iterable<?>) new AnnotationList.ForLoadedAnnotations(annotationArr));
    }

    public static <T> ElementMatcher.Junction<T> noneOf(Object... objArr) {
        return noneOf((Iterable<?>) Arrays.asList(objArr));
    }

    public static <T> ElementMatcher.Junction<T> noneOf(Iterable<?> iterable) {
        ElementMatcher.Junction<U> junction = null;
        for (Object next : iterable) {
            if (junction == null) {
                junction = not(is((Object) next));
            } else {
                junction = junction.and(not(is((Object) next)));
            }
        }
        return junction == null ? any() : junction;
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> noneOf(Type... typeArr) {
        return noneOf((Iterable<?>) new TypeList.Generic.ForLoadedTypes(typeArr));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> noneOf(Constructor<?>... constructorArr) {
        return definedMethod(noneOf((Iterable<?>) new MethodList.ForLoadedMethods(constructorArr, new Method[0])));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> noneOf(Method... methodArr) {
        return definedMethod(noneOf((Iterable<?>) new MethodList.ForLoadedMethods((Constructor<?>[]) new Constructor[0], methodArr)));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> noneOf(Field... fieldArr) {
        return definedField(noneOf((Iterable<?>) new FieldList.ForLoadedFields(fieldArr)));
    }

    public static <T extends AnnotationDescription> ElementMatcher.Junction<T> noneOf(Annotation... annotationArr) {
        return noneOf((Iterable<?>) new AnnotationList.ForLoadedAnnotations(annotationArr));
    }

    public static <T> ElementMatcher.Junction<Iterable<? extends T>> whereAny(ElementMatcher<? super T> elementMatcher) {
        return new CollectionItemMatcher(elementMatcher);
    }

    public static <T> ElementMatcher.Junction<Iterable<? extends T>> whereNone(ElementMatcher<? super T> elementMatcher) {
        return not(whereAny(elementMatcher));
    }

    public static <T extends TypeDescription.Generic> ElementMatcher.Junction<T> erasure(Class<?> cls) {
        return erasure((ElementMatcher<? super TypeDescription>) is((Type) cls));
    }

    public static <T extends TypeDescription.Generic> ElementMatcher.Junction<T> erasure(TypeDescription typeDescription) {
        return erasure((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends TypeDescription.Generic> ElementMatcher.Junction<T> erasure(ElementMatcher<? super TypeDescription> elementMatcher) {
        return new ErasureMatcher(elementMatcher);
    }

    public static <T extends Iterable<? extends TypeDescription.Generic>> ElementMatcher.Junction<T> erasures(Class<?>... clsArr) {
        return erasures((Iterable<? extends TypeDescription>) new TypeList.ForLoadedTypes(clsArr));
    }

    public static <T extends Iterable<? extends TypeDescription.Generic>> ElementMatcher.Junction<T> erasures(TypeDescription... typeDescriptionArr) {
        return erasures((Iterable<? extends TypeDescription>) Arrays.asList(typeDescriptionArr));
    }

    public static <T extends Iterable<? extends TypeDescription.Generic>> ElementMatcher.Junction<T> erasures(Iterable<? extends TypeDescription> iterable) {
        ArrayList arrayList = new ArrayList();
        for (TypeDescription is : iterable) {
            arrayList.add(is((Object) is));
        }
        return erasures((ElementMatcher<? super Iterable<? extends TypeDescription>>) new CollectionOneToOneMatcher(arrayList));
    }

    public static <T extends Iterable<? extends TypeDescription.Generic>> ElementMatcher.Junction<T> erasures(ElementMatcher<? super Iterable<? extends TypeDescription>> elementMatcher) {
        return new CollectionErasureMatcher(elementMatcher);
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> isVariable(String str) {
        return isVariable((ElementMatcher<? super NamedElement>) named(str));
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> isVariable(ElementMatcher<? super NamedElement> elementMatcher) {
        return new TypeSortMatcher(anyOf(TypeDefinition.Sort.VARIABLE, TypeDefinition.Sort.VARIABLE_SYMBOLIC)).and(elementMatcher);
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> named(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.EQUALS_FULLY));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> namedIgnoreCase(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.EQUALS_FULLY_IGNORE_CASE));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> nameStartsWith(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.STARTS_WITH));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> nameStartsWithIgnoreCase(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.STARTS_WITH_IGNORE_CASE));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> nameEndsWith(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.ENDS_WITH));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> nameEndsWithIgnoreCase(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.ENDS_WITH_IGNORE_CASE));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> nameContains(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.CONTAINS));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> nameContainsIgnoreCase(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.CONTAINS_IGNORE_CASE));
    }

    public static <T extends NamedElement> ElementMatcher.Junction<T> nameMatches(String str) {
        return new NameMatcher(new StringMatcher(str, StringMatcher.Mode.MATCHES));
    }

    public static <T extends NamedElement.WithOptionalName> ElementMatcher.Junction<T> isNamed() {
        return new IsNamedMatcher();
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> hasDescriptor(String str) {
        return new DescriptorMatcher(new StringMatcher(str, StringMatcher.Mode.EQUALS_FULLY));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isDeclaredBy(Class<?> cls) {
        return isDeclaredBy(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isDeclaredBy(TypeDescription typeDescription) {
        return isDeclaredBy((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isDeclaredBy(ElementMatcher<? super TypeDescription> elementMatcher) {
        return isDeclaredByGeneric((ElementMatcher<? super TypeDescription.Generic>) erasure(elementMatcher));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isDeclaredByGeneric(Type type) {
        return isDeclaredByGeneric(TypeDefinition.Sort.describe(type));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isDeclaredByGeneric(TypeDescription.Generic generic) {
        return isDeclaredByGeneric((ElementMatcher<? super TypeDescription.Generic>) is((Object) generic));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isDeclaredByGeneric(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return new DeclaringTypeMatcher(elementMatcher);
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isVisibleTo(Class<?> cls) {
        return isVisibleTo(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isVisibleTo(TypeDescription typeDescription) {
        return new VisibilityMatcher(typeDescription);
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isAccessibleTo(Class<?> cls) {
        return isAccessibleTo(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends ByteCodeElement> ElementMatcher.Junction<T> isAccessibleTo(TypeDescription typeDescription) {
        return new AccessibilityMatcher(typeDescription);
    }

    public static <T extends ModifierReviewable.OfAbstraction> ElementMatcher.Junction<T> isAbstract() {
        return new ModifierMatcher(ModifierMatcher.Mode.ABSTRACT);
    }

    public static <T extends ModifierReviewable.OfEnumeration> ElementMatcher.Junction<T> isEnum() {
        return new ModifierMatcher(ModifierMatcher.Mode.ENUMERATION);
    }

    public static <T extends AnnotationSource> ElementMatcher.Junction<T> isAnnotatedWith(Class<? extends Annotation> cls) {
        return isAnnotatedWith(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends AnnotationSource> ElementMatcher.Junction<T> isAnnotatedWith(TypeDescription typeDescription) {
        return isAnnotatedWith((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends AnnotationSource> ElementMatcher.Junction<T> isAnnotatedWith(ElementMatcher<? super TypeDescription> elementMatcher) {
        return declaresAnnotation(annotationType(elementMatcher));
    }

    public static <T extends AnnotationSource> ElementMatcher.Junction<T> declaresAnnotation(ElementMatcher<? super AnnotationDescription> elementMatcher) {
        return new DeclaringAnnotationMatcher(new CollectionItemMatcher(elementMatcher));
    }

    public static <T extends ModifierReviewable.OfByteCodeElement> ElementMatcher.Junction<T> isPublic() {
        return new ModifierMatcher(ModifierMatcher.Mode.PUBLIC);
    }

    public static <T extends ModifierReviewable.OfByteCodeElement> ElementMatcher.Junction<T> isProtected() {
        return new ModifierMatcher(ModifierMatcher.Mode.PROTECTED);
    }

    public static <T extends ModifierReviewable.OfByteCodeElement> ElementMatcher.Junction<T> isPackagePrivate() {
        return not(isPublic().or(isProtected()).or(isPrivate()));
    }

    public static <T extends ModifierReviewable.OfByteCodeElement> ElementMatcher.Junction<T> isPrivate() {
        return new ModifierMatcher(ModifierMatcher.Mode.PRIVATE);
    }

    public static <T extends ModifierReviewable.OfByteCodeElement> ElementMatcher.Junction<T> isStatic() {
        return new ModifierMatcher(ModifierMatcher.Mode.STATIC);
    }

    public static <T extends ModifierReviewable> ElementMatcher.Junction<T> isFinal() {
        return new ModifierMatcher(ModifierMatcher.Mode.FINAL);
    }

    public static <T extends ModifierReviewable> ElementMatcher.Junction<T> isSynthetic() {
        return new ModifierMatcher(ModifierMatcher.Mode.SYNTHETIC);
    }

    public static <T extends ModifierReviewable.ForMethodDescription> ElementMatcher.Junction<T> isSynchronized() {
        return new ModifierMatcher(ModifierMatcher.Mode.SYNCHRONIZED);
    }

    public static <T extends ModifierReviewable.ForMethodDescription> ElementMatcher.Junction<T> isNative() {
        return new ModifierMatcher(ModifierMatcher.Mode.NATIVE);
    }

    public static <T extends ModifierReviewable.ForMethodDescription> ElementMatcher.Junction<T> isStrict() {
        return new ModifierMatcher(ModifierMatcher.Mode.STRICT);
    }

    public static <T extends ModifierReviewable.ForMethodDescription> ElementMatcher.Junction<T> isVarArgs() {
        return new ModifierMatcher(ModifierMatcher.Mode.VAR_ARGS);
    }

    public static <T extends ModifierReviewable.ForMethodDescription> ElementMatcher.Junction<T> isBridge() {
        return new ModifierMatcher(ModifierMatcher.Mode.BRIDGE);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> returnsGeneric(Type type) {
        return returnsGeneric(TypeDefinition.Sort.describe(type));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> returnsGeneric(TypeDescription.Generic generic) {
        return returnsGeneric((ElementMatcher<? super TypeDescription.Generic>) is((Object) generic));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> returns(Class<?> cls) {
        return returnsGeneric((ElementMatcher<? super TypeDescription.Generic>) erasure(cls));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> returns(TypeDescription typeDescription) {
        return returns((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> returns(ElementMatcher<? super TypeDescription> elementMatcher) {
        return returnsGeneric((ElementMatcher<? super TypeDescription.Generic>) erasure(elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> returnsGeneric(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return new MethodReturnTypeMatcher(elementMatcher);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesGenericArgument(int i, Type type) {
        return takesGenericArgument(i, TypeDefinition.Sort.describe(type));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesGenericArgument(int i, TypeDescription.Generic generic) {
        return takesGenericArgument(i, (ElementMatcher<? super TypeDescription.Generic>) is((Object) generic));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesGenericArgument(int i, ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return takesGenericArguments((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) new CollectionElementMatcher(i, elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesGenericArguments(Type... typeArr) {
        return takesGenericArguments((List<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(typeArr));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesGenericArguments(TypeDefinition... typeDefinitionArr) {
        return takesGenericArguments((List<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesGenericArguments(List<? extends TypeDefinition> list) {
        ArrayList arrayList = new ArrayList();
        for (TypeDefinition is : list) {
            arrayList.add(is((Object) is));
        }
        return takesGenericArguments((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) new CollectionOneToOneMatcher(arrayList));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesGenericArguments(ElementMatcher<? super Iterable<? extends TypeDescription.Generic>> elementMatcher) {
        return new MethodParametersMatcher(new MethodParameterTypesMatcher(elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArgument(int i, Class<?> cls) {
        return takesArgument(i, TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArgument(int i, TypeDescription typeDescription) {
        return takesArgument(i, (ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArgument(int i, ElementMatcher<? super TypeDescription> elementMatcher) {
        return takesGenericArgument(i, (ElementMatcher<? super TypeDescription.Generic>) erasure(elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArguments(Class<?>... clsArr) {
        return takesGenericArguments((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) erasures(clsArr));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArguments(TypeDescription... typeDescriptionArr) {
        return takesGenericArguments((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) erasures(typeDescriptionArr));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArguments(Iterable<? extends TypeDescription> iterable) {
        ArrayList arrayList = new ArrayList();
        for (TypeDescription erasure : iterable) {
            arrayList.add(erasure(erasure));
        }
        return takesGenericArguments((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) new CollectionOneToOneMatcher(arrayList));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArguments(ElementMatcher<? super Iterable<? extends TypeDescription>> elementMatcher) {
        return new MethodParametersMatcher(new MethodParameterTypesMatcher(erasures(elementMatcher)));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> takesArguments(int i) {
        return new MethodParametersMatcher(new CollectionSizeMatcher(i));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> hasParameters(ElementMatcher<? super Iterable<? extends ParameterDescription>> elementMatcher) {
        return new MethodParametersMatcher(elementMatcher);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> canThrow(Class<? extends Throwable> cls) {
        return canThrow(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> canThrow(TypeDescription typeDescription) {
        if (typeDescription.isAssignableTo((Class<?>) RuntimeException.class) || typeDescription.isAssignableTo((Class<?>) Error.class)) {
            return new BooleanMatcher(true);
        }
        return declaresGenericException((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) new CollectionItemMatcher(erasure((ElementMatcher<? super TypeDescription>) isSuperTypeOf(typeDescription))));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> declaresGenericException(Type type) {
        return declaresGenericException(TypeDefinition.Sort.describe(type));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> declaresGenericException(TypeDescription.Generic generic) {
        return (generic.getSort().isWildcard() || !generic.asErasure().isAssignableTo((Class<?>) Throwable.class)) ? new BooleanMatcher(false) : declaresGenericException((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) new CollectionItemMatcher(is((Object) generic)));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> declaresException(Class<? extends Throwable> cls) {
        return declaresException(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> declaresException(TypeDescription typeDescription) {
        return typeDescription.isAssignableTo((Class<?>) Throwable.class) ? declaresGenericException((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) new CollectionItemMatcher(erasure(typeDescription))) : new BooleanMatcher(false);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> declaresGenericException(ElementMatcher<? super Iterable<? extends TypeDescription.Generic>> elementMatcher) {
        return new MethodExceptionTypeMatcher(elementMatcher);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isOverriddenFrom(Class<?> cls) {
        return isOverriddenFrom(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isOverriddenFrom(TypeDescription typeDescription) {
        return isOverriddenFrom((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isOverriddenFrom(ElementMatcher<? super TypeDescription> elementMatcher) {
        return isOverriddenFromGeneric((ElementMatcher<? super TypeDescription.Generic>) erasure(elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isOverriddenFromGeneric(Type type) {
        return isOverriddenFromGeneric(TypeDefinition.Sort.describe(type));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isOverriddenFromGeneric(TypeDescription.Generic generic) {
        return isOverriddenFromGeneric((ElementMatcher<? super TypeDescription.Generic>) is((Object) generic));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isOverriddenFromGeneric(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return new MethodOverrideMatcher(elementMatcher);
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> isInterface() {
        return new ModifierMatcher(ModifierMatcher.Mode.INTERFACE);
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> isAnnotation() {
        return new ModifierMatcher(ModifierMatcher.Mode.ANNOTATION);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isMethod() {
        return new MethodSortMatcher(MethodSortMatcher.Sort.METHOD);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isConstructor() {
        return new MethodSortMatcher(MethodSortMatcher.Sort.CONSTRUCTOR);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isTypeInitializer() {
        return new MethodSortMatcher(MethodSortMatcher.Sort.TYPE_INITIALIZER);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isVirtual() {
        return new MethodSortMatcher(MethodSortMatcher.Sort.VIRTUAL);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isDefaultMethod() {
        return new MethodSortMatcher(MethodSortMatcher.Sort.DEFAULT_METHOD);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isDefaultConstructor() {
        return isConstructor().and(takesArguments(0));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isDefaultFinalizer() {
        return isFinalizer().and(isDeclaredBy(TypeDescription.OBJECT));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isFinalizer() {
        return named("finalize").and(takesArguments(0)).and(returns(TypeDescription.VOID));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isHashCode() {
        return named("hashCode").and(takesArguments(0)).and(returns((Class<?>) Integer.TYPE));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isEquals() {
        return named("equals").and(takesArguments(TypeDescription.OBJECT)).and(returns((Class<?>) Boolean.TYPE));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isClone() {
        return named("clone").and(takesArguments(0));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isToString() {
        return named("toString").and(takesArguments(0)).and(returns(TypeDescription.STRING));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isSetter() {
        return nameStartsWith("set").and(takesArguments(1)).and(returns(TypeDescription.VOID));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isSetter(String str) {
        ElementMatcher.Junction junction;
        ElementMatcher.Junction isSetter = isSetter();
        if (str.length() == 0) {
            junction = named("set");
        } else {
            junction = named("set" + Character.toUpperCase(str.charAt(0)) + str.substring(1));
        }
        return isSetter.and(junction);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isSetter(Class<?> cls) {
        return isSetter(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGenericSetter(Type type) {
        return isGenericSetter(TypeDefinition.Sort.describe(type));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isSetter(TypeDescription typeDescription) {
        return isSetter((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGenericSetter(TypeDescription.Generic generic) {
        return isGenericSetter((ElementMatcher<? super TypeDescription.Generic>) is((Object) generic));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isSetter(ElementMatcher<? super TypeDescription> elementMatcher) {
        return isGenericSetter((ElementMatcher<? super TypeDescription.Generic>) erasure(elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGenericSetter(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return isSetter().and(takesGenericArguments((ElementMatcher<? super Iterable<? extends TypeDescription.Generic>>) new CollectionOneToOneMatcher(Collections.singletonList(elementMatcher))));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGetter() {
        return takesArguments(0).and(not(returns(TypeDescription.VOID))).and(nameStartsWith("get").or(nameStartsWith("is").and(returnsGeneric((ElementMatcher<? super TypeDescription.Generic>) anyOf(Boolean.TYPE, Boolean.class)))));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGetter(String str) {
        ElementMatcher.Junction junction;
        ElementMatcher.Junction isGetter = isGetter();
        if (str.length() == 0) {
            junction = named("get").or(named("is"));
        } else {
            ElementMatcher.Junction named = named("get" + Character.toUpperCase(str.charAt(0)) + str.substring(1));
            junction = named.or(named("is" + Character.toUpperCase(str.charAt(0)) + str.substring(1)));
        }
        return isGetter.and(junction);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGetter(Class<?> cls) {
        return isGetter(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGenericGetter(Type type) {
        return isGenericGetter(TypeDefinition.Sort.describe(type));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGetter(TypeDescription typeDescription) {
        return isGetter((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGenericGetter(TypeDescription.Generic generic) {
        return isGenericGetter((ElementMatcher<? super TypeDescription.Generic>) is((Object) generic));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGetter(ElementMatcher<? super TypeDescription> elementMatcher) {
        return isGenericGetter((ElementMatcher<? super TypeDescription.Generic>) erasure(elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> isGenericGetter(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return isGetter().and(returnsGeneric(elementMatcher));
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> hasMethodName(String str) {
        if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(str)) {
            return isConstructor();
        }
        if (MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(str)) {
            return isTypeInitializer();
        }
        return named(str);
    }

    public static <T extends MethodDescription> ElementMatcher.Junction<T> hasSignature(MethodDescription.SignatureToken signatureToken) {
        return new SignatureTokenMatcher(is((Object) signatureToken));
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> isSubTypeOf(Class<?> cls) {
        return isSubTypeOf(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> isSubTypeOf(TypeDescription typeDescription) {
        return new SubTypeMatcher(typeDescription);
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> isSuperTypeOf(Class<?> cls) {
        return isSuperTypeOf(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> isSuperTypeOf(TypeDescription typeDescription) {
        return new SuperTypeMatcher(typeDescription);
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> hasSuperType(ElementMatcher<? super TypeDescription> elementMatcher) {
        return hasGenericSuperType(erasure(elementMatcher));
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> hasGenericSuperType(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return new HasSuperTypeMatcher(elementMatcher);
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> inheritsAnnotation(Class<?> cls) {
        return inheritsAnnotation(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> inheritsAnnotation(TypeDescription typeDescription) {
        return inheritsAnnotation((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> inheritsAnnotation(ElementMatcher<? super TypeDescription> elementMatcher) {
        return hasAnnotation(annotationType(elementMatcher));
    }

    public static <T extends TypeDescription> ElementMatcher.Junction<T> hasAnnotation(ElementMatcher<? super AnnotationDescription> elementMatcher) {
        return new InheritedAnnotationMatcher(new CollectionItemMatcher(elementMatcher));
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> declaresField(ElementMatcher<? super FieldDescription> elementMatcher) {
        return new DeclaringFieldMatcher(new CollectionItemMatcher(elementMatcher));
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> declaresMethod(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new DeclaringMethodMatcher(new CollectionItemMatcher(elementMatcher));
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> ofSort(TypeDefinition.Sort sort) {
        return ofSort((ElementMatcher<? super TypeDefinition.Sort>) is((Object) sort));
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> ofSort(ElementMatcher<? super TypeDefinition.Sort> elementMatcher) {
        return new TypeSortMatcher(elementMatcher);
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> isPrimitive() {
        return new PrimitiveTypeMatcher();
    }

    public static <T extends TypeDefinition> ElementMatcher.Junction<T> isArray() {
        return new ArrayTypeMatcher();
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> genericFieldType(Type type) {
        return genericFieldType(TypeDefinition.Sort.describe(type));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> genericFieldType(TypeDescription.Generic generic) {
        return genericFieldType((ElementMatcher<? super TypeDescription.Generic>) is((Object) generic));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> genericFieldType(ElementMatcher<? super TypeDescription.Generic> elementMatcher) {
        return new FieldTypeMatcher(elementMatcher);
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> fieldType(Class<?> cls) {
        return fieldType(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> fieldType(TypeDescription typeDescription) {
        return fieldType((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> fieldType(ElementMatcher<? super TypeDescription> elementMatcher) {
        return genericFieldType((ElementMatcher<? super TypeDescription.Generic>) erasure(elementMatcher));
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> isVolatile() {
        return new ModifierMatcher(ModifierMatcher.Mode.VOLATILE);
    }

    public static <T extends FieldDescription> ElementMatcher.Junction<T> isTransient() {
        return new ModifierMatcher(ModifierMatcher.Mode.TRANSIENT);
    }

    public static <T extends AnnotationDescription> ElementMatcher.Junction<T> annotationType(Class<? extends Annotation> cls) {
        return annotationType(TypeDescription.ForLoadedType.of(cls));
    }

    public static <T extends AnnotationDescription> ElementMatcher.Junction<T> annotationType(TypeDescription typeDescription) {
        return annotationType((ElementMatcher<? super TypeDescription>) is((Object) typeDescription));
    }

    public static <T extends AnnotationDescription> ElementMatcher.Junction<T> annotationType(ElementMatcher<? super TypeDescription> elementMatcher) {
        return new AnnotationTypeMatcher(elementMatcher);
    }

    public static <T extends ClassLoader> ElementMatcher.Junction<T> isBootstrapClassLoader() {
        return new NullMatcher();
    }

    public static <T extends ClassLoader> ElementMatcher.Junction<T> isSystemClassLoader() {
        return new EqualityMatcher(ClassLoader.getSystemClassLoader());
    }

    public static <T extends ClassLoader> ElementMatcher.Junction<T> isExtensionClassLoader() {
        ClassLoader parent = ClassLoader.getSystemClassLoader().getParent();
        return parent == null ? none() : new EqualityMatcher(parent);
    }

    public static <T extends ClassLoader> ElementMatcher.Junction<T> isChildOf(ClassLoader classLoader) {
        if (classLoader == BOOTSTRAP_CLASSLOADER) {
            return new BooleanMatcher(true);
        }
        return hasChild(is((Object) classLoader));
    }

    public static <T extends ClassLoader> ElementMatcher.Junction<T> hasChild(ElementMatcher<? super ClassLoader> elementMatcher) {
        return new ClassLoaderHierarchyMatcher(elementMatcher);
    }

    public static <T extends ClassLoader> ElementMatcher.Junction<T> isParentOf(ClassLoader classLoader) {
        return classLoader == BOOTSTRAP_CLASSLOADER ? isBootstrapClassLoader() : new ClassLoaderParentMatcher(classLoader);
    }

    public static <T extends ClassLoader> ElementMatcher.Junction<T> ofType(ElementMatcher<? super TypeDescription> elementMatcher) {
        return new InstanceTypeMatcher(elementMatcher);
    }

    public static <T extends JavaModule> ElementMatcher.Junction<T> supportsModules() {
        return not(new NullMatcher());
    }
}

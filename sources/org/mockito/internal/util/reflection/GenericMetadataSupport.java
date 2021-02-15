package org.mockito.internal.util.reflection;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.Checks;

public abstract class GenericMetadataSupport {
    protected Map<TypeVariable<?>, Type> contextualActualTypeParameters = new HashMap();

    public interface BoundedType extends Type {
        Type firstBound();

        Type[] interfaceBounds();
    }

    public Class<?>[] rawExtraInterfaces() {
        return new Class[0];
    }

    public abstract Class<?> rawType();

    /* access modifiers changed from: protected */
    public void registerAllTypeVariables(Type type) {
        LinkedList linkedList = new LinkedList();
        HashSet hashSet = new HashSet();
        linkedList.add(type);
        while (!linkedList.isEmpty()) {
            Type type2 = (Type) linkedList.poll();
            if (type2 != null && !hashSet.contains(type2)) {
                registerTypeVariablesOn(type2);
                hashSet.add(type2);
                Class<?> extractRawTypeOf = extractRawTypeOf(type2);
                linkedList.add(extractRawTypeOf.getGenericSuperclass());
                linkedList.addAll(Arrays.asList(extractRawTypeOf.getGenericInterfaces()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public Class<?> extractRawTypeOf(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof BoundedType) {
            return extractRawTypeOf(((BoundedType) type).firstBound());
        }
        if (type instanceof TypeVariable) {
            return extractRawTypeOf(this.contextualActualTypeParameters.get(type));
        }
        throw new MockitoException("Raw extraction not supported for : '" + type + "'");
    }

    /* access modifiers changed from: protected */
    public void registerTypeVariablesOn(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            TypeVariable[] typeParameters = ((Class) parameterizedType.getRawType()).getTypeParameters();
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (int i = 0; i < actualTypeArguments.length; i++) {
                TypeVariable typeVariable = typeParameters[i];
                Type type2 = actualTypeArguments[i];
                if (type2 instanceof TypeVariable) {
                    registerTypeVariableIfNotPresent((TypeVariable) type2);
                    if (this.contextualActualTypeParameters.containsKey(typeVariable)) {
                    }
                }
                if (type2 instanceof WildcardType) {
                    this.contextualActualTypeParameters.put(typeVariable, boundsOf((WildcardType) type2));
                } else if (typeVariable != type2) {
                    this.contextualActualTypeParameters.put(typeVariable, type2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void registerTypeParametersOn(TypeVariable<?>[] typeVariableArr) {
        for (TypeVariable<?> registerTypeVariableIfNotPresent : typeVariableArr) {
            registerTypeVariableIfNotPresent(registerTypeVariableIfNotPresent);
        }
    }

    private void registerTypeVariableIfNotPresent(TypeVariable<?> typeVariable) {
        if (!this.contextualActualTypeParameters.containsKey(typeVariable)) {
            this.contextualActualTypeParameters.put(typeVariable, boundsOf(typeVariable));
        }
    }

    private BoundedType boundsOf(TypeVariable<?> typeVariable) {
        if (typeVariable.getBounds()[0] instanceof TypeVariable) {
            return boundsOf((TypeVariable<?>) (TypeVariable) typeVariable.getBounds()[0]);
        }
        return new TypeVarBoundedType(typeVariable);
    }

    private BoundedType boundsOf(WildcardType wildcardType) {
        WildCardBoundedType wildCardBoundedType = new WildCardBoundedType(wildcardType);
        return wildCardBoundedType.firstBound() instanceof TypeVariable ? boundsOf((TypeVariable<?>) (TypeVariable) wildCardBoundedType.firstBound()) : wildCardBoundedType;
    }

    public List<Type> extraInterfaces() {
        return Collections.emptyList();
    }

    public boolean hasRawExtraInterfaces() {
        return rawExtraInterfaces().length > 0;
    }

    public Map<TypeVariable<?>, Type> actualTypeArguments() {
        TypeVariable[] typeParameters = rawType().getTypeParameters();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (TypeVariable typeVariable : typeParameters) {
            linkedHashMap.put(typeVariable, getActualTypeArgumentFor(typeVariable));
        }
        return linkedHashMap;
    }

    /* access modifiers changed from: protected */
    public Type getActualTypeArgumentFor(TypeVariable<?> typeVariable) {
        Type type = this.contextualActualTypeParameters.get(typeVariable);
        return type instanceof TypeVariable ? getActualTypeArgumentFor((TypeVariable) type) : type;
    }

    public GenericMetadataSupport resolveGenericReturnType(Method method) {
        Type genericReturnType = method.getGenericReturnType();
        int i = 0;
        while (genericReturnType instanceof GenericArrayType) {
            i++;
            genericReturnType = ((GenericArrayType) genericReturnType).getGenericComponentType();
        }
        GenericMetadataSupport resolveGenericType = resolveGenericType(genericReturnType, method);
        if (i == 0) {
            return resolveGenericType;
        }
        return new GenericArrayReturnType(resolveGenericType, i);
    }

    private GenericMetadataSupport resolveGenericType(Type type, Method method) {
        if (type instanceof Class) {
            return new NotGenericReturnTypeSupport(this, type);
        }
        if (type instanceof ParameterizedType) {
            return new ParameterizedReturnType(this, method.getTypeParameters(), (ParameterizedType) type);
        }
        if (type instanceof TypeVariable) {
            return new TypeVariableReturnType(this, method.getTypeParameters(), (TypeVariable) type);
        }
        throw new MockitoException("Ouch, it shouldn't happen, type '" + type.getClass().getCanonicalName() + "' on method : '" + method.toGenericString() + "' is not supported : " + type);
    }

    public static GenericMetadataSupport inferFrom(Type type) {
        Checks.checkNotNull(type, "type");
        if (type instanceof Class) {
            return new FromClassGenericMetadataSupport((Class) type);
        }
        if (type instanceof ParameterizedType) {
            return new FromParameterizedTypeGenericMetadataSupport((ParameterizedType) type);
        }
        throw new MockitoException("Type meta-data for this Type (" + type.getClass().getCanonicalName() + ") is not supported : " + type);
    }

    private static class FromClassGenericMetadataSupport extends GenericMetadataSupport {
        private final Class<?> clazz;

        public FromClassGenericMetadataSupport(Class<?> cls) {
            this.clazz = cls;
            registerTypeParametersOn(cls.getTypeParameters());
            registerAllTypeVariables(cls);
        }

        public Class<?> rawType() {
            return this.clazz;
        }
    }

    private static class FromParameterizedTypeGenericMetadataSupport extends GenericMetadataSupport {
        private final ParameterizedType parameterizedType;

        public FromParameterizedTypeGenericMetadataSupport(ParameterizedType parameterizedType2) {
            this.parameterizedType = parameterizedType2;
            readActualTypeParameters();
        }

        private void readActualTypeParameters() {
            registerAllTypeVariables(this.parameterizedType);
        }

        public Class<?> rawType() {
            return (Class) this.parameterizedType.getRawType();
        }
    }

    private static class ParameterizedReturnType extends GenericMetadataSupport {
        private final ParameterizedType parameterizedType;
        private final TypeVariable<?>[] typeParameters;

        public ParameterizedReturnType(GenericMetadataSupport genericMetadataSupport, TypeVariable<?>[] typeVariableArr, ParameterizedType parameterizedType2) {
            this.parameterizedType = parameterizedType2;
            this.typeParameters = typeVariableArr;
            this.contextualActualTypeParameters = genericMetadataSupport.contextualActualTypeParameters;
            readTypeParameters();
            readTypeVariables();
        }

        private void readTypeParameters() {
            registerTypeParametersOn(this.typeParameters);
        }

        private void readTypeVariables() {
            registerTypeVariablesOn(this.parameterizedType);
        }

        public Class<?> rawType() {
            return (Class) this.parameterizedType.getRawType();
        }
    }

    private static class TypeVariableReturnType extends GenericMetadataSupport {
        private List<Type> extraInterfaces;
        private Class<?> rawType;
        private final TypeVariable<?>[] typeParameters;
        private final TypeVariable<?> typeVariable;

        public TypeVariableReturnType(GenericMetadataSupport genericMetadataSupport, TypeVariable<?>[] typeVariableArr, TypeVariable<?> typeVariable2) {
            this.typeParameters = typeVariableArr;
            this.typeVariable = typeVariable2;
            this.contextualActualTypeParameters = genericMetadataSupport.contextualActualTypeParameters;
            readTypeParameters();
            readTypeVariables();
        }

        private void readTypeParameters() {
            registerTypeParametersOn(this.typeParameters);
        }

        private void readTypeVariables() {
            for (Type registerTypeVariablesOn : this.typeVariable.getBounds()) {
                registerTypeVariablesOn(registerTypeVariablesOn);
            }
            registerTypeParametersOn(new TypeVariable[]{this.typeVariable});
            registerTypeVariablesOn(getActualTypeArgumentFor(this.typeVariable));
        }

        public Class<?> rawType() {
            if (this.rawType == null) {
                this.rawType = extractRawTypeOf(this.typeVariable);
            }
            return this.rawType;
        }

        public List<Type> extraInterfaces() {
            List<Type> list = this.extraInterfaces;
            if (list != null) {
                return list;
            }
            Type extractActualBoundedTypeOf = extractActualBoundedTypeOf(this.typeVariable);
            if (extractActualBoundedTypeOf instanceof BoundedType) {
                List<Type> asList = Arrays.asList(((BoundedType) extractActualBoundedTypeOf).interfaceBounds());
                this.extraInterfaces = asList;
                return asList;
            } else if (extractActualBoundedTypeOf instanceof ParameterizedType) {
                List<Type> singletonList = Collections.singletonList(extractActualBoundedTypeOf);
                this.extraInterfaces = singletonList;
                return singletonList;
            } else if (extractActualBoundedTypeOf instanceof Class) {
                List<Type> emptyList = Collections.emptyList();
                this.extraInterfaces = emptyList;
                return emptyList;
            } else {
                throw new MockitoException("Cannot extract extra-interfaces from '" + this.typeVariable + "' : '" + extractActualBoundedTypeOf + "'");
            }
        }

        public Class<?>[] rawExtraInterfaces() {
            List<Type> extraInterfaces2 = extraInterfaces();
            ArrayList arrayList = new ArrayList();
            for (Type extractRawTypeOf : extraInterfaces2) {
                Class<?> extractRawTypeOf2 = extractRawTypeOf(extractRawTypeOf);
                if (!rawType().equals(extractRawTypeOf2)) {
                    arrayList.add(extractRawTypeOf2);
                }
            }
            return (Class[]) arrayList.toArray(new Class[arrayList.size()]);
        }

        private Type extractActualBoundedTypeOf(Type type) {
            if (type instanceof TypeVariable) {
                return extractActualBoundedTypeOf((Type) this.contextualActualTypeParameters.get(type));
            }
            if (!(type instanceof BoundedType)) {
                return type;
            }
            Type extractActualBoundedTypeOf = extractActualBoundedTypeOf(((BoundedType) type).firstBound());
            return !(extractActualBoundedTypeOf instanceof BoundedType) ? type : extractActualBoundedTypeOf;
        }
    }

    private static class GenericArrayReturnType extends GenericMetadataSupport {
        private final int arity;
        private final GenericMetadataSupport genericArrayType;

        public GenericArrayReturnType(GenericMetadataSupport genericMetadataSupport, int i) {
            this.genericArrayType = genericMetadataSupport;
            this.arity = i;
        }

        public Class<?> rawType() {
            Class<?> rawType = this.genericArrayType.rawType();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.arity; i++) {
                sb.append("[");
            }
            try {
                sb.append("L");
                sb.append(rawType.getName());
                sb.append(";");
                return Class.forName(sb.toString(), false, rawType.getClassLoader());
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("This was not supposed to happend", e);
            }
        }
    }

    private static class NotGenericReturnTypeSupport extends GenericMetadataSupport {
        private final Class<?> returnType;

        public NotGenericReturnTypeSupport(GenericMetadataSupport genericMetadataSupport, Type type) {
            this.returnType = (Class) type;
            this.contextualActualTypeParameters = genericMetadataSupport.contextualActualTypeParameters;
            registerAllTypeVariables(this.returnType);
        }

        public Class<?> rawType() {
            return this.returnType;
        }
    }

    public static class TypeVarBoundedType implements BoundedType {
        /* access modifiers changed from: private */
        public final TypeVariable<?> typeVariable;

        public TypeVarBoundedType(TypeVariable<?> typeVariable2) {
            this.typeVariable = typeVariable2;
        }

        public Type firstBound() {
            return this.typeVariable.getBounds()[0];
        }

        public Type[] interfaceBounds() {
            Type[] typeArr = new Type[(this.typeVariable.getBounds().length - 1)];
            System.arraycopy(this.typeVariable.getBounds(), 1, typeArr, 0, this.typeVariable.getBounds().length - 1);
            return typeArr;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.typeVariable.equals(((TypeVarBoundedType) obj).typeVariable);
        }

        public int hashCode() {
            return this.typeVariable.hashCode();
        }

        public String toString() {
            return "{firstBound=" + firstBound() + ", interfaceBounds=" + Arrays.deepToString(interfaceBounds()) + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        }

        public TypeVariable<?> typeVariable() {
            return this.typeVariable;
        }
    }

    public static class WildCardBoundedType implements BoundedType {
        private final WildcardType wildcard;

        public Type[] interfaceBounds() {
            return new Type[0];
        }

        public WildCardBoundedType(WildcardType wildcardType) {
            this.wildcard = wildcardType;
        }

        public Type firstBound() {
            Type[] lowerBounds = this.wildcard.getLowerBounds();
            return lowerBounds.length != 0 ? lowerBounds[0] : this.wildcard.getUpperBounds()[0];
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.wildcard.equals(((TypeVarBoundedType) obj).typeVariable);
        }

        public int hashCode() {
            return this.wildcard.hashCode();
        }

        public String toString() {
            return "{firstBound=" + firstBound() + ", interfaceBounds=[]}";
        }

        public WildcardType wildCard() {
            return this.wildcard;
        }
    }
}

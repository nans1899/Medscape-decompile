package com.fasterxml.jackson.databind.type;

import com.appboy.Constants;
import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.databind.JavaType;
import java.io.Serializable;
import java.lang.reflect.TypeVariable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TypeBindings implements Serializable {
    private static final TypeBindings EMPTY = new TypeBindings(NO_STRINGS, NO_TYPES, (String[]) null);
    private static final String[] NO_STRINGS = new String[0];
    private static final JavaType[] NO_TYPES = new JavaType[0];
    private static final long serialVersionUID = 1;
    private final int _hashCode;
    private final String[] _names;
    private final JavaType[] _types;
    private final String[] _unboundVariables;

    private TypeBindings(String[] strArr, JavaType[] javaTypeArr, String[] strArr2) {
        this._names = strArr == null ? NO_STRINGS : strArr;
        javaTypeArr = javaTypeArr == null ? NO_TYPES : javaTypeArr;
        this._types = javaTypeArr;
        if (this._names.length == javaTypeArr.length) {
            int length = javaTypeArr.length;
            int i = 1;
            for (int i2 = 0; i2 < length; i2++) {
                i += this._types[i2].hashCode();
            }
            this._unboundVariables = strArr2;
            this._hashCode = i;
            return;
        }
        throw new IllegalArgumentException("Mismatching names (" + this._names.length + "), types (" + this._types.length + ")");
    }

    public static TypeBindings emptyBindings() {
        return EMPTY;
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        String[] strArr = this._names;
        if (strArr == null || strArr.length == 0) {
            return EMPTY;
        }
        return this;
    }

    public static TypeBindings create(Class<?> cls, List<JavaType> list) {
        return create(cls, (list == null || list.isEmpty()) ? NO_TYPES : (JavaType[]) list.toArray(new JavaType[list.size()]));
    }

    public static TypeBindings create(Class<?> cls, JavaType[] javaTypeArr) {
        String[] strArr;
        if (javaTypeArr == null) {
            javaTypeArr = NO_TYPES;
        } else {
            int length = javaTypeArr.length;
            if (length == 1) {
                return create(cls, javaTypeArr[0]);
            }
            if (length == 2) {
                return create(cls, javaTypeArr[0], javaTypeArr[1]);
            }
        }
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length == 0) {
            strArr = NO_STRINGS;
        } else {
            int length2 = typeParameters.length;
            strArr = new String[length2];
            for (int i = 0; i < length2; i++) {
                strArr[i] = typeParameters[i].getName();
            }
        }
        if (strArr.length == javaTypeArr.length) {
            return new TypeBindings(strArr, javaTypeArr, (String[]) null);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can not create TypeBindings for class ");
        sb.append(cls.getName());
        sb.append(" with ");
        sb.append(javaTypeArr.length);
        sb.append(" type parameter");
        sb.append(javaTypeArr.length == 1 ? "" : Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
        sb.append(": class expects ");
        sb.append(strArr.length);
        throw new IllegalArgumentException(sb.toString());
    }

    public static TypeBindings create(Class<?> cls, JavaType javaType) {
        int i;
        TypeVariable[] paramsFor1 = TypeParamStash.paramsFor1(cls);
        if (paramsFor1 == null) {
            i = 0;
        } else {
            i = paramsFor1.length;
        }
        if (i == 1) {
            return new TypeBindings(new String[]{paramsFor1[0].getName()}, new JavaType[]{javaType}, (String[]) null);
        }
        throw new IllegalArgumentException("Can not create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + i);
    }

    public static TypeBindings create(Class<?> cls, JavaType javaType, JavaType javaType2) {
        int i;
        TypeVariable[] paramsFor2 = TypeParamStash.paramsFor2(cls);
        if (paramsFor2 == null) {
            i = 0;
        } else {
            i = paramsFor2.length;
        }
        if (i == 2) {
            return new TypeBindings(new String[]{paramsFor2[0].getName(), paramsFor2[1].getName()}, new JavaType[]{javaType, javaType2}, (String[]) null);
        }
        throw new IllegalArgumentException("Can not create TypeBindings for class " + cls.getName() + " with 2 type parameters: class expects " + i);
    }

    public static TypeBindings createIfNeeded(Class<?> cls, JavaType javaType) {
        int i;
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null) {
            i = 0;
        } else {
            i = typeParameters.length;
        }
        if (i == 0) {
            return EMPTY;
        }
        if (i == 1) {
            return new TypeBindings(new String[]{typeParameters[0].getName()}, new JavaType[]{javaType}, (String[]) null);
        }
        throw new IllegalArgumentException("Can not create TypeBindings for class " + cls.getName() + " with 1 type parameter: class expects " + i);
    }

    public static TypeBindings createIfNeeded(Class<?> cls, JavaType[] javaTypeArr) {
        TypeVariable[] typeParameters = cls.getTypeParameters();
        if (typeParameters == null || typeParameters.length == 0) {
            return EMPTY;
        }
        if (javaTypeArr == null) {
            javaTypeArr = NO_TYPES;
        }
        int length = typeParameters.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = typeParameters[i].getName();
        }
        if (length == javaTypeArr.length) {
            return new TypeBindings(strArr, javaTypeArr, (String[]) null);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can not create TypeBindings for class ");
        sb.append(cls.getName());
        sb.append(" with ");
        sb.append(javaTypeArr.length);
        sb.append(" type parameter");
        sb.append(javaTypeArr.length == 1 ? "" : Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
        sb.append(": class expects ");
        sb.append(length);
        throw new IllegalArgumentException(sb.toString());
    }

    public TypeBindings withUnboundVariable(String str) {
        String[] strArr;
        String[] strArr2 = this._unboundVariables;
        int length = strArr2 == null ? 0 : strArr2.length;
        if (length == 0) {
            strArr = new String[1];
        } else {
            strArr = (String[]) Arrays.copyOf(this._unboundVariables, length + 1);
        }
        strArr[length] = str;
        return new TypeBindings(this._names, this._types, strArr);
    }

    public JavaType findBoundType(String str) {
        int length = this._names.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(this._names[i])) {
                return this._types[i];
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return this._types.length == 0;
    }

    public int size() {
        return this._types.length;
    }

    public String getBoundName(int i) {
        if (i < 0) {
            return null;
        }
        String[] strArr = this._names;
        if (i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    public JavaType getBoundType(int i) {
        if (i < 0) {
            return null;
        }
        JavaType[] javaTypeArr = this._types;
        if (i >= javaTypeArr.length) {
            return null;
        }
        return javaTypeArr[i];
    }

    public List<JavaType> getTypeParameters() {
        JavaType[] javaTypeArr = this._types;
        if (javaTypeArr.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.asList(javaTypeArr);
    }

    public boolean hasUnbound(String str) {
        String[] strArr = this._unboundVariables;
        if (strArr == null) {
            return false;
        }
        int length = strArr.length;
        do {
            length--;
            if (length < 0) {
                return false;
            }
        } while (!str.equals(this._unboundVariables[length]));
        return true;
    }

    public String toString() {
        if (this._types.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        int length = this._types.length;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            }
            sb.append(this._types[i].getGenericSignature());
        }
        sb.append('>');
        return sb.toString();
    }

    public int hashCode() {
        return this._hashCode;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        TypeBindings typeBindings = (TypeBindings) obj;
        int length = this._types.length;
        if (length != typeBindings.size()) {
            return false;
        }
        JavaType[] javaTypeArr = typeBindings._types;
        for (int i = 0; i < length; i++) {
            if (!javaTypeArr[i].equals(this._types[i])) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public JavaType[] typeParameterArray() {
        return this._types;
    }

    static class TypeParamStash {
        private static final TypeVariable<?>[] VARS_ABSTRACT_LIST = AbstractList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ARRAY_LIST = ArrayList.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_COLLECTION = Collection.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_HASH_MAP = HashMap.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_ITERABLE = Iterable.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LINKED_HASH_MAP = LinkedHashMap.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_LIST = List.class.getTypeParameters();
        private static final TypeVariable<?>[] VARS_MAP = Map.class.getTypeParameters();

        TypeParamStash() {
        }

        public static TypeVariable<?>[] paramsFor1(Class<?> cls) {
            if (cls == Collection.class) {
                return VARS_COLLECTION;
            }
            if (cls == List.class) {
                return VARS_LIST;
            }
            if (cls == ArrayList.class) {
                return VARS_ARRAY_LIST;
            }
            if (cls == AbstractList.class) {
                return VARS_ABSTRACT_LIST;
            }
            if (cls == Iterable.class) {
                return VARS_ITERABLE;
            }
            return cls.getTypeParameters();
        }

        public static TypeVariable<?>[] paramsFor2(Class<?> cls) {
            if (cls == Map.class) {
                return VARS_MAP;
            }
            if (cls == HashMap.class) {
                return VARS_HASH_MAP;
            }
            if (cls == LinkedHashMap.class) {
                return VARS_LINKED_HASH_MAP;
            }
            return cls.getTypeParameters();
        }
    }
}

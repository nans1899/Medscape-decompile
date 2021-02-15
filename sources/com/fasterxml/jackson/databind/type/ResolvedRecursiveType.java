package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ResolvedRecursiveType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _referencedType;

    /* access modifiers changed from: protected */
    @Deprecated
    public JavaType _narrow(Class<?> cls) {
        return this;
    }

    public boolean isContainerType() {
        return false;
    }

    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return null;
    }

    public String toString() {
        return null;
    }

    public JavaType withContentType(JavaType javaType) {
        return this;
    }

    public JavaType withContentTypeHandler(Object obj) {
        return this;
    }

    public JavaType withContentValueHandler(Object obj) {
        return this;
    }

    public JavaType withStaticTyping() {
        return this;
    }

    public JavaType withTypeHandler(Object obj) {
        return this;
    }

    public JavaType withValueHandler(Object obj) {
        return this;
    }

    public ResolvedRecursiveType(Class<?> cls, TypeBindings typeBindings) {
        super(cls, typeBindings, (JavaType) null, (JavaType[]) null, 0, (Object) null, (Object) null, false);
    }

    public void setReference(JavaType javaType) {
        if (this._referencedType == null) {
            this._referencedType = javaType;
            return;
        }
        throw new IllegalStateException("Trying to re-set self reference; old value = " + this._referencedType + ", new = " + javaType);
    }

    public JavaType getSelfReferencedType() {
        return this._referencedType;
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        return this._referencedType.getGenericSignature(sb);
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return this._referencedType.getErasedSignature(sb);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            return ((ResolvedRecursiveType) obj).getSelfReferencedType().equals(getSelfReferencedType());
        }
        return false;
    }
}

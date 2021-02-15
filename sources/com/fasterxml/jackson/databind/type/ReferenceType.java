package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ReferenceType extends SimpleType {
    private static final long serialVersionUID = 1;
    protected final JavaType _referencedType;

    public boolean isReferenceType() {
        return true;
    }

    protected ReferenceType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2.hashCode(), obj, obj2, z);
        this._referencedType = javaType2;
    }

    protected ReferenceType(TypeBase typeBase, JavaType javaType) {
        super(typeBase);
        this._referencedType = javaType;
    }

    public static ReferenceType upgradeFrom(JavaType javaType, JavaType javaType2) {
        if (javaType2 == null) {
            throw new IllegalArgumentException("Missing referencedType");
        } else if (javaType instanceof TypeBase) {
            return new ReferenceType((TypeBase) javaType, javaType2);
        } else {
            throw new IllegalArgumentException("Can not upgrade from an instance of " + javaType.getClass());
        }
    }

    public static ReferenceType construct(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2) {
        return new ReferenceType(cls, typeBindings, javaType, javaTypeArr, javaType2, (Object) null, (Object) null, false);
    }

    @Deprecated
    public static ReferenceType construct(Class<?> cls, JavaType javaType) {
        return new ReferenceType(cls, TypeBindings.emptyBindings(), (JavaType) null, (JavaType[]) null, javaType, (Object) null, (Object) null, false);
    }

    public JavaType withContentType(JavaType javaType) {
        if (this._referencedType == javaType) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, javaType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public ReferenceType withTypeHandler(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._valueHandler, obj, this._asStatic);
    }

    public ReferenceType withContentTypeHandler(Object obj) {
        if (obj == this._referencedType.getTypeHandler()) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public ReferenceType withValueHandler(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, obj, this._typeHandler, this._asStatic);
    }

    public ReferenceType withContentValueHandler(Object obj) {
        if (obj == this._referencedType.getValueHandler()) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    public ReferenceType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }

    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new ReferenceType(cls, this._bindings, javaType, javaTypeArr, this._referencedType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        return this._class.getName() + '<' + this._referencedType.toCanonical();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public JavaType _narrow(Class<?> cls) {
        return new ReferenceType(cls, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    public JavaType getContentType() {
        return this._referencedType;
    }

    public JavaType getReferencedType() {
        return this._referencedType;
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append('<');
        StringBuilder genericSignature = this._referencedType.getGenericSignature(sb);
        genericSignature.append(';');
        return genericSignature;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[reference type, class ");
        sb.append(buildCanonicalName());
        sb.append('<');
        sb.append(this._referencedType);
        sb.append('>');
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        ReferenceType referenceType = (ReferenceType) obj;
        if (referenceType._class != this._class) {
            return false;
        }
        return this._referencedType.equals(referenceType._referencedType);
    }
}

package net.bytebuddy.asm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ConstantDynamic;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.OpenedClassReader;

@HashCodeAndEqualsPlugin.Enhance
public class TypeReferenceAdjustment extends AsmVisitorWrapper.AbstractBase {
    private final ElementMatcher.Junction<? super TypeDescription> filter;
    private final boolean strict;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypeReferenceAdjustment typeReferenceAdjustment = (TypeReferenceAdjustment) obj;
        return this.strict == typeReferenceAdjustment.strict && this.filter.equals(typeReferenceAdjustment.filter);
    }

    public int hashCode() {
        return ((true + (this.strict ? 1 : 0)) * 31) + this.filter.hashCode();
    }

    protected TypeReferenceAdjustment(boolean z, ElementMatcher.Junction<? super TypeDescription> junction) {
        this.strict = z;
        this.filter = junction;
    }

    public static TypeReferenceAdjustment strict() {
        return new TypeReferenceAdjustment(true, ElementMatchers.none());
    }

    public static TypeReferenceAdjustment relaxed() {
        return new TypeReferenceAdjustment(false, ElementMatchers.none());
    }

    public TypeReferenceAdjustment filter(ElementMatcher<? super TypeDescription> elementMatcher) {
        return new TypeReferenceAdjustment(this.strict, this.filter.or(elementMatcher));
    }

    public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
        return new TypeReferenceClassVisitor(classVisitor, this.strict, this.filter, typePool);
    }

    protected static class TypeReferenceClassVisitor extends ClassVisitor {
        /* access modifiers changed from: private */
        public static final AnnotationVisitor IGNORE_ANNOTATION = null;
        private static final FieldVisitor IGNORE_FIELD = null;
        private static final MethodVisitor IGNORE_METHOD = null;
        private final ElementMatcher<? super TypeDescription> filter;
        /* access modifiers changed from: private */
        public final Set<String> observedTypes = new HashSet();
        private final boolean strict;
        private final TypePool typePool;
        private final Set<String> visitedInnerTypes = new HashSet();

        protected TypeReferenceClassVisitor(ClassVisitor classVisitor, boolean z, ElementMatcher<? super TypeDescription> elementMatcher, TypePool typePool2) {
            super(OpenedClassReader.ASM_API, classVisitor);
            this.typePool = typePool2;
            this.strict = z;
            this.filter = elementMatcher;
        }

        public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
            if (str3 != null) {
                this.observedTypes.add(str3);
            }
            if (strArr != null) {
                this.observedTypes.addAll(Arrays.asList(strArr));
            }
            super.visit(i, i2, str, str2, str3, strArr);
        }

        public void visitNestHost(String str) {
            this.observedTypes.add(str);
            super.visitNestHost(str);
        }

        public void visitOuterClass(String str, String str2, String str3) {
            this.observedTypes.add(str);
            super.visitOuterClass(str, str2, str3);
        }

        public void visitNestMember(String str) {
            this.observedTypes.add(str);
            super.visitNestMember(str);
        }

        public void visitInnerClass(String str, String str2, String str3, int i) {
            this.visitedInnerTypes.add(str);
            super.visitInnerClass(str, str2, str3, i);
        }

        public AnnotationVisitor visitAnnotation(String str, boolean z) {
            this.observedTypes.add(Type.getType(str).getInternalName());
            AnnotationVisitor visitAnnotation = super.visitAnnotation(str, z);
            if (visitAnnotation != null) {
                return new TypeReferenceAnnotationVisitor(visitAnnotation);
            }
            return IGNORE_ANNOTATION;
        }

        public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
            this.observedTypes.add(Type.getType(str).getInternalName());
            AnnotationVisitor visitTypeAnnotation = super.visitTypeAnnotation(i, typePath, str, z);
            if (visitTypeAnnotation != null) {
                return new TypeReferenceAnnotationVisitor(visitTypeAnnotation);
            }
            return IGNORE_ANNOTATION;
        }

        public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
            FieldVisitor visitField = super.visitField(i, str, str2, str3, obj);
            if (visitField == null) {
                return IGNORE_FIELD;
            }
            resolve(Type.getType(str2));
            return new TypeReferenceFieldVisitor(visitField);
        }

        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
            MethodVisitor visitMethod = super.visitMethod(i, str, str2, str3, strArr);
            if (visitMethod == null) {
                return IGNORE_METHOD;
            }
            resolve(Type.getType(str2));
            if (strArr != null) {
                this.observedTypes.addAll(Arrays.asList(strArr));
            }
            return new TypeReferenceMethodVisitor(visitMethod);
        }

        public void visitEnd() {
            for (String next : this.observedTypes) {
                if (this.visitedInnerTypes.add(next)) {
                    TypePool.Resolution describe = this.typePool.describe(next.replace('/', '.'));
                    if (describe.isResolved()) {
                        TypeDescription resolve = describe.resolve();
                        if (!this.filter.matches(resolve)) {
                            while (resolve != null && resolve.isNestedClass()) {
                                String internalName = resolve.getInternalName();
                                String str = null;
                                String internalName2 = resolve.isMemberType() ? resolve.getDeclaringType().getInternalName() : null;
                                if (!resolve.isAnonymousType()) {
                                    str = resolve.getSimpleName();
                                }
                                super.visitInnerClass(internalName, internalName2, str, resolve.getModifiers());
                                do {
                                    try {
                                        resolve = resolve.getEnclosingType();
                                        if (resolve == null || this.visitedInnerTypes.add(resolve.getInternalName())) {
                                        }
                                        resolve = resolve.getEnclosingType();
                                        break;
                                    } catch (RuntimeException e) {
                                        if (this.strict) {
                                            throw e;
                                        }
                                    }
                                } while (this.visitedInnerTypes.add(resolve.getInternalName()));
                            }
                        } else {
                            continue;
                        }
                    } else if (this.strict) {
                        throw new IllegalStateException("Could not locate type for: " + next.replace('/', '.'));
                    }
                }
            }
            super.visitEnd();
        }

        /* access modifiers changed from: protected */
        public void resolve(Type type) {
            if (type.getSort() == 11) {
                resolve(type.getReturnType());
                for (Type resolve : type.getArgumentTypes()) {
                    resolve(resolve);
                }
                return;
            }
            while (type.getSort() == 9) {
                type = type.getElementType();
            }
            if (type.getSort() == 10) {
                this.observedTypes.add(type.getInternalName());
            }
        }

        /* access modifiers changed from: protected */
        public void resolve(Handle handle) {
            this.observedTypes.add(handle.getOwner());
            Type type = Type.getType(handle.getDesc());
            resolve(type.getReturnType());
            for (Type resolve : type.getArgumentTypes()) {
                resolve(resolve);
            }
        }

        /* access modifiers changed from: protected */
        public void resolve(ConstantDynamic constantDynamic) {
            Type type = Type.getType(constantDynamic.getDescriptor());
            resolve(type.getReturnType());
            for (Type resolve : type.getArgumentTypes()) {
                resolve(resolve);
            }
            resolve(constantDynamic.getBootstrapMethod());
            for (int i = 0; i < constantDynamic.getBootstrapMethodArgumentCount(); i++) {
                resolve(constantDynamic.getBootstrapMethodArgument(i));
            }
        }

        /* access modifiers changed from: protected */
        public void resolveInternalName(String str) {
            while (str.startsWith("[")) {
                str = str.substring(1);
            }
            this.observedTypes.add(str);
        }

        /* access modifiers changed from: protected */
        public void resolve(Object obj) {
            if (obj instanceof Type) {
                resolve((Type) obj);
            } else if (obj instanceof Handle) {
                resolve((Handle) obj);
            } else if (obj instanceof ConstantDynamic) {
                resolve((ConstantDynamic) obj);
            }
        }

        protected class TypeReferenceAnnotationVisitor extends AnnotationVisitor {
            protected TypeReferenceAnnotationVisitor(AnnotationVisitor annotationVisitor) {
                super(OpenedClassReader.ASM_API, annotationVisitor);
            }

            public void visit(String str, Object obj) {
                TypeReferenceClassVisitor.this.resolve(obj);
                super.visit(str, obj);
            }

            public void visitEnum(String str, String str2, String str3) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str2).getInternalName());
                super.visitEnum(str, str2, str3);
            }

            public AnnotationVisitor visitAnnotation(String str, String str2) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str2).getInternalName());
                AnnotationVisitor visitAnnotation = super.visitAnnotation(str, str2);
                if (visitAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public AnnotationVisitor visitArray(String str) {
                AnnotationVisitor visitArray = super.visitArray(str);
                if (visitArray != null) {
                    return new TypeReferenceAnnotationVisitor(visitArray);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }
        }

        protected class TypeReferenceFieldVisitor extends FieldVisitor {
            protected TypeReferenceFieldVisitor(FieldVisitor fieldVisitor) {
                super(OpenedClassReader.ASM_API, fieldVisitor);
            }

            public AnnotationVisitor visitAnnotation(String str, boolean z) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str).getInternalName());
                AnnotationVisitor visitAnnotation = super.visitAnnotation(str, z);
                if (visitAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }
        }

        protected class TypeReferenceMethodVisitor extends MethodVisitor {
            protected TypeReferenceMethodVisitor(MethodVisitor methodVisitor) {
                super(OpenedClassReader.ASM_API, methodVisitor);
            }

            public AnnotationVisitor visitAnnotationDefault() {
                AnnotationVisitor visitAnnotationDefault = super.visitAnnotationDefault();
                if (visitAnnotationDefault != null) {
                    return new TypeReferenceAnnotationVisitor(visitAnnotationDefault);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public AnnotationVisitor visitAnnotation(String str, boolean z) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str).getInternalName());
                AnnotationVisitor visitAnnotation = super.visitAnnotation(str, z);
                if (visitAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str).getInternalName());
                AnnotationVisitor visitTypeAnnotation = super.visitTypeAnnotation(i, typePath, str, z);
                if (visitTypeAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitTypeAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str).getInternalName());
                AnnotationVisitor visitParameterAnnotation = super.visitParameterAnnotation(i, str, z);
                if (visitParameterAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitParameterAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public AnnotationVisitor visitInsnAnnotation(int i, TypePath typePath, String str, boolean z) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str).getInternalName());
                AnnotationVisitor visitInsnAnnotation = super.visitInsnAnnotation(i, typePath, str, z);
                if (visitInsnAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitInsnAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str).getInternalName());
                AnnotationVisitor visitTryCatchAnnotation = super.visitTryCatchAnnotation(i, typePath, str, z);
                if (visitTryCatchAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitTryCatchAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z) {
                TypeReferenceClassVisitor.this.observedTypes.add(Type.getType(str).getInternalName());
                AnnotationVisitor visitLocalVariableAnnotation = super.visitLocalVariableAnnotation(i, typePath, labelArr, labelArr2, iArr, str, z);
                if (visitLocalVariableAnnotation != null) {
                    return new TypeReferenceAnnotationVisitor(visitLocalVariableAnnotation);
                }
                return TypeReferenceClassVisitor.IGNORE_ANNOTATION;
            }

            public void visitTypeInsn(int i, String str) {
                TypeReferenceClassVisitor.this.resolveInternalName(str);
                super.visitTypeInsn(i, str);
            }

            public void visitFieldInsn(int i, String str, String str2, String str3) {
                TypeReferenceClassVisitor.this.resolveInternalName(str);
                TypeReferenceClassVisitor.this.resolve(Type.getType(str3));
                super.visitFieldInsn(i, str, str2, str3);
            }

            public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
                TypeReferenceClassVisitor.this.resolveInternalName(str);
                TypeReferenceClassVisitor.this.resolve(Type.getType(str3));
                super.visitMethodInsn(i, str, str2, str3, z);
            }

            public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object[] objArr) {
                TypeReferenceClassVisitor.this.resolve(Type.getType(str2));
                TypeReferenceClassVisitor.this.resolve(handle);
                for (Object resolve : objArr) {
                    TypeReferenceClassVisitor.this.resolve(resolve);
                }
                super.visitInvokeDynamicInsn(str, str2, handle, objArr);
            }

            public void visitLdcInsn(Object obj) {
                TypeReferenceClassVisitor.this.resolve(obj);
                super.visitLdcInsn(obj);
            }

            public void visitMultiANewArrayInsn(String str, int i) {
                TypeReferenceClassVisitor.this.resolve(Type.getType(str));
                super.visitMultiANewArrayInsn(str, i);
            }

            public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
                if (str != null) {
                    TypeReferenceClassVisitor.this.observedTypes.add(str);
                }
                super.visitTryCatchBlock(label, label2, label3, str);
            }
        }
    }
}

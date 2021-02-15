package net.bytebuddy.implementation.attribute;

import java.lang.reflect.Array;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.TypeReference;

public interface AnnotationAppender {
    public static final String NO_NAME = null;

    AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter);

    AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter, int i, String str);

    public interface Target {
        AnnotationVisitor visit(String str, boolean z);

        AnnotationVisitor visit(String str, boolean z, int i, String str2);

        @HashCodeAndEqualsPlugin.Enhance
        public static class OnType implements Target {
            private final ClassVisitor classVisitor;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.classVisitor.equals(((OnType) obj).classVisitor);
            }

            public int hashCode() {
                return 527 + this.classVisitor.hashCode();
            }

            public OnType(ClassVisitor classVisitor2) {
                this.classVisitor = classVisitor2;
            }

            public AnnotationVisitor visit(String str, boolean z) {
                return this.classVisitor.visitAnnotation(str, z);
            }

            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.classVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class OnMethod implements Target {
            private final MethodVisitor methodVisitor;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodVisitor.equals(((OnMethod) obj).methodVisitor);
            }

            public int hashCode() {
                return 527 + this.methodVisitor.hashCode();
            }

            public OnMethod(MethodVisitor methodVisitor2) {
                this.methodVisitor = methodVisitor2;
            }

            public AnnotationVisitor visit(String str, boolean z) {
                return this.methodVisitor.visitAnnotation(str, z);
            }

            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.methodVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class OnMethodParameter implements Target {
            private final MethodVisitor methodVisitor;
            private final int parameterIndex;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                OnMethodParameter onMethodParameter = (OnMethodParameter) obj;
                return this.parameterIndex == onMethodParameter.parameterIndex && this.methodVisitor.equals(onMethodParameter.methodVisitor);
            }

            public int hashCode() {
                return ((527 + this.methodVisitor.hashCode()) * 31) + this.parameterIndex;
            }

            public OnMethodParameter(MethodVisitor methodVisitor2, int i) {
                this.methodVisitor = methodVisitor2;
                this.parameterIndex = i;
            }

            public AnnotationVisitor visit(String str, boolean z) {
                return this.methodVisitor.visitParameterAnnotation(this.parameterIndex, str, z);
            }

            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.methodVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class OnField implements Target {
            private final FieldVisitor fieldVisitor;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldVisitor.equals(((OnField) obj).fieldVisitor);
            }

            public int hashCode() {
                return 527 + this.fieldVisitor.hashCode();
            }

            public OnField(FieldVisitor fieldVisitor2) {
                this.fieldVisitor = fieldVisitor2;
            }

            public AnnotationVisitor visit(String str, boolean z) {
                return this.fieldVisitor.visitAnnotation(str, z);
            }

            public AnnotationVisitor visit(String str, boolean z, int i, String str2) {
                return this.fieldVisitor.visitTypeAnnotation(i, TypePath.fromString(str2), str, z);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Default implements AnnotationAppender {
        private final Target target;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.target.equals(((Default) obj).target);
        }

        public int hashCode() {
            return 527 + this.target.hashCode();
        }

        public Default(Target target2) {
            this.target = target2;
        }

        private static void handle(AnnotationVisitor annotationVisitor, AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter) {
            for (MethodDescription.InDefinedShape inDefinedShape : annotationDescription.getAnnotationType().getDeclaredMethods()) {
                if (annotationValueFilter.isRelevant(annotationDescription, inDefinedShape)) {
                    apply(annotationVisitor, inDefinedShape.getReturnType().asErasure(), inDefinedShape.getName(), annotationDescription.getValue(inDefinedShape).resolve());
                }
            }
            annotationVisitor.visitEnd();
        }

        public static void apply(AnnotationVisitor annotationVisitor, TypeDescription typeDescription, String str, Object obj) {
            if (typeDescription.isArray()) {
                AnnotationVisitor visitArray = annotationVisitor.visitArray(str);
                int length = Array.getLength(obj);
                TypeDescription componentType = typeDescription.getComponentType();
                for (int i = 0; i < length; i++) {
                    apply(visitArray, componentType, NO_NAME, Array.get(obj, i));
                }
                visitArray.visitEnd();
            } else if (typeDescription.isAnnotation()) {
                handle(annotationVisitor.visitAnnotation(str, typeDescription.getDescriptor()), (AnnotationDescription) obj, AnnotationValueFilter.Default.APPEND_DEFAULTS);
            } else if (typeDescription.isEnum()) {
                annotationVisitor.visitEnum(str, typeDescription.getDescriptor(), ((EnumerationDescription) obj).getValue());
            } else if (typeDescription.represents(Class.class)) {
                annotationVisitor.visit(str, Type.getType(((TypeDescription) obj).getDescriptor()));
            } else {
                annotationVisitor.visit(str, obj);
            }
        }

        public AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter) {
            int i = AnonymousClass1.$SwitchMap$java$lang$annotation$RetentionPolicy[annotationDescription.getRetention().ordinal()];
            if (i == 1) {
                doAppend(annotationDescription, true, annotationValueFilter);
            } else if (i == 2) {
                doAppend(annotationDescription, false, annotationValueFilter);
            } else if (i != 3) {
                throw new IllegalStateException("Unexpected retention policy: " + annotationDescription.getRetention());
            }
            return this;
        }

        private void doAppend(AnnotationDescription annotationDescription, boolean z, AnnotationValueFilter annotationValueFilter) {
            handle(this.target.visit(annotationDescription.getAnnotationType().getDescriptor(), z), annotationDescription, annotationValueFilter);
        }

        public AnnotationAppender append(AnnotationDescription annotationDescription, AnnotationValueFilter annotationValueFilter, int i, String str) {
            int i2 = AnonymousClass1.$SwitchMap$java$lang$annotation$RetentionPolicy[annotationDescription.getRetention().ordinal()];
            if (i2 == 1) {
                doAppend(annotationDescription, true, annotationValueFilter, i, str);
            } else if (i2 == 2) {
                doAppend(annotationDescription, false, annotationValueFilter, i, str);
            } else if (i2 != 3) {
                throw new IllegalStateException("Unexpected retention policy: " + annotationDescription.getRetention());
            }
            return this;
        }

        private void doAppend(AnnotationDescription annotationDescription, boolean z, AnnotationValueFilter annotationValueFilter, int i, String str) {
            handle(this.target.visit(annotationDescription.getAnnotationType().getDescriptor(), z, i, str), annotationDescription, annotationValueFilter);
        }
    }

    /* renamed from: net.bytebuddy.implementation.attribute.AnnotationAppender$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$lang$annotation$RetentionPolicy;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                java.lang.annotation.RetentionPolicy[] r0 = java.lang.annotation.RetentionPolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$lang$annotation$RetentionPolicy = r0
                java.lang.annotation.RetentionPolicy r1 = java.lang.annotation.RetentionPolicy.RUNTIME     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$lang$annotation$RetentionPolicy     // Catch:{ NoSuchFieldError -> 0x001d }
                java.lang.annotation.RetentionPolicy r1 = java.lang.annotation.RetentionPolicy.CLASS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$lang$annotation$RetentionPolicy     // Catch:{ NoSuchFieldError -> 0x0028 }
                java.lang.annotation.RetentionPolicy r1 = java.lang.annotation.RetentionPolicy.SOURCE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.implementation.attribute.AnnotationAppender.AnonymousClass1.<clinit>():void");
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class ForTypeAnnotations implements TypeDescription.Generic.Visitor<AnnotationAppender> {
        private static final char COMPONENT_TYPE_PATH = '[';
        private static final String EMPTY_TYPE_PATH = "";
        private static final char INDEXED_TYPE_DELIMITER = ';';
        private static final char INNER_CLASS_PATH = '.';
        private static final int SUPER_CLASS_INDEX = -1;
        public static final boolean VARIABLE_ON_INVOKEABLE = false;
        public static final boolean VARIABLE_ON_TYPE = true;
        private static final char WILDCARD_TYPE_PATH = '*';
        private final AnnotationAppender annotationAppender;
        private final AnnotationValueFilter annotationValueFilter;
        private final String typePath;
        private final int typeReference;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ForTypeAnnotations forTypeAnnotations = (ForTypeAnnotations) obj;
            return this.typeReference == forTypeAnnotations.typeReference && this.typePath.equals(forTypeAnnotations.typePath) && this.annotationAppender.equals(forTypeAnnotations.annotationAppender) && this.annotationValueFilter.equals(forTypeAnnotations.annotationValueFilter);
        }

        public int hashCode() {
            return ((((((527 + this.annotationAppender.hashCode()) * 31) + this.annotationValueFilter.hashCode()) * 31) + this.typeReference) * 31) + this.typePath.hashCode();
        }

        protected ForTypeAnnotations(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2, TypeReference typeReference2) {
            this(annotationAppender2, annotationValueFilter2, typeReference2.getValue(), "");
        }

        protected ForTypeAnnotations(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2, int i, String str) {
            this.annotationAppender = annotationAppender2;
            this.annotationValueFilter = annotationValueFilter2;
            this.typeReference = i;
            this.typePath = str;
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofSuperClass(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2) {
            return new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newSuperTypeReference(-1));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofInterfaceType(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2, int i) {
            return new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newSuperTypeReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofFieldType(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2) {
            return new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newTypeReference(19));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofMethodReturnType(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2) {
            return new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newTypeReference(20));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofMethodParameterType(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2, int i) {
            return new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newFormalParameterReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofExceptionType(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2, int i) {
            return new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newExceptionReference(i));
        }

        public static TypeDescription.Generic.Visitor<AnnotationAppender> ofReceiverType(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2) {
            return new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newTypeReference(21));
        }

        public static AnnotationAppender ofTypeVariable(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2, boolean z, List<? extends TypeDescription.Generic> list) {
            return ofTypeVariable(annotationAppender2, annotationValueFilter2, z, 0, list);
        }

        public static AnnotationAppender ofTypeVariable(AnnotationAppender annotationAppender2, AnnotationValueFilter annotationValueFilter2, boolean z, int i, List<? extends TypeDescription.Generic> list) {
            int i2;
            int i3;
            if (z) {
                i2 = 17;
                i3 = 0;
            } else {
                i2 = 18;
                i3 = 1;
            }
            for (TypeDescription.Generic generic : list.subList(i, list.size())) {
                int value = TypeReference.newTypeParameterReference(i3, i).getValue();
                for (AnnotationDescription append : generic.getDeclaredAnnotations()) {
                    annotationAppender2 = annotationAppender2.append(append, annotationValueFilter2, value, "");
                }
                int i4 = (((TypeDescription.Generic) generic.getUpperBounds().get(0)).getSort().isTypeVariable() || !((TypeDescription.Generic) generic.getUpperBounds().get(0)).isInterface()) ? 0 : 1;
                for (TypeDescription.Generic accept : generic.getUpperBounds()) {
                    annotationAppender2 = (AnnotationAppender) accept.accept(new ForTypeAnnotations(annotationAppender2, annotationValueFilter2, TypeReference.newTypeParameterBoundReference(i2, i, i4)));
                    i4++;
                }
                i++;
            }
            return annotationAppender2;
        }

        public AnnotationAppender onGenericArray(TypeDescription.Generic generic) {
            TypeDescription.Generic componentType = generic.getComponentType();
            AnnotationAppender apply = apply(generic, this.typePath);
            AnnotationValueFilter annotationValueFilter2 = this.annotationValueFilter;
            int i = this.typeReference;
            return (AnnotationAppender) componentType.accept(new ForTypeAnnotations(apply, annotationValueFilter2, i, this.typePath + '['));
        }

        public AnnotationAppender onWildcard(TypeDescription.Generic generic) {
            TypeDescription.Generic generic2;
            TypeList.Generic lowerBounds = generic.getLowerBounds();
            if (lowerBounds.isEmpty()) {
                generic2 = (TypeDescription.Generic) generic.getUpperBounds().getOnly();
            } else {
                generic2 = (TypeDescription.Generic) lowerBounds.getOnly();
            }
            AnnotationAppender apply = apply(generic, this.typePath);
            AnnotationValueFilter annotationValueFilter2 = this.annotationValueFilter;
            int i = this.typeReference;
            return (AnnotationAppender) generic2.accept(new ForTypeAnnotations(apply, annotationValueFilter2, i, this.typePath + '*'));
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: net.bytebuddy.implementation.attribute.AnnotationAppender} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public net.bytebuddy.implementation.attribute.AnnotationAppender onParameterizedType(net.bytebuddy.description.type.TypeDescription.Generic r10) {
            /*
                r9 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = r9.typePath
                r0.<init>(r1)
                r1 = 0
                r2 = 0
            L_0x0009:
                net.bytebuddy.description.type.TypeDescription r3 = r10.asErasure()
                int r3 = r3.getInnerClassCount()
                if (r2 >= r3) goto L_0x001b
                r3 = 46
                r0.append(r3)
                int r2 = r2 + 1
                goto L_0x0009
            L_0x001b:
                java.lang.String r2 = r0.toString()
                net.bytebuddy.implementation.attribute.AnnotationAppender r2 = r9.apply(r10, r2)
                net.bytebuddy.description.type.TypeDescription$Generic r3 = r10.getOwnerType()
                if (r3 == 0) goto L_0x003a
                net.bytebuddy.implementation.attribute.AnnotationAppender$ForTypeAnnotations r4 = new net.bytebuddy.implementation.attribute.AnnotationAppender$ForTypeAnnotations
                net.bytebuddy.implementation.attribute.AnnotationValueFilter r5 = r9.annotationValueFilter
                int r6 = r9.typeReference
                java.lang.String r7 = r9.typePath
                r4.<init>(r2, r5, r6, r7)
                java.lang.Object r2 = r3.accept(r4)
                net.bytebuddy.implementation.attribute.AnnotationAppender r2 = (net.bytebuddy.implementation.attribute.AnnotationAppender) r2
            L_0x003a:
                net.bytebuddy.description.type.TypeList$Generic r10 = r10.getTypeArguments()
                java.util.Iterator r10 = r10.iterator()
            L_0x0042:
                boolean r3 = r10.hasNext()
                if (r3 == 0) goto L_0x007a
                java.lang.Object r3 = r10.next()
                net.bytebuddy.description.type.TypeDescription$Generic r3 = (net.bytebuddy.description.type.TypeDescription.Generic) r3
                net.bytebuddy.implementation.attribute.AnnotationAppender$ForTypeAnnotations r4 = new net.bytebuddy.implementation.attribute.AnnotationAppender$ForTypeAnnotations
                net.bytebuddy.implementation.attribute.AnnotationValueFilter r5 = r9.annotationValueFilter
                int r6 = r9.typeReference
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r8 = r0.toString()
                r7.append(r8)
                int r8 = r1 + 1
                r7.append(r1)
                r1 = 59
                r7.append(r1)
                java.lang.String r1 = r7.toString()
                r4.<init>(r2, r5, r6, r1)
                java.lang.Object r1 = r3.accept(r4)
                r2 = r1
                net.bytebuddy.implementation.attribute.AnnotationAppender r2 = (net.bytebuddy.implementation.attribute.AnnotationAppender) r2
                r1 = r8
                goto L_0x0042
            L_0x007a:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.implementation.attribute.AnnotationAppender.ForTypeAnnotations.onParameterizedType(net.bytebuddy.description.type.TypeDescription$Generic):net.bytebuddy.implementation.attribute.AnnotationAppender");
        }

        public AnnotationAppender onTypeVariable(TypeDescription.Generic generic) {
            return apply(generic, this.typePath);
        }

        public AnnotationAppender onNonGenericType(TypeDescription.Generic generic) {
            StringBuilder sb = new StringBuilder(this.typePath);
            for (int i = 0; i < generic.asErasure().getInnerClassCount(); i++) {
                sb.append('.');
            }
            AnnotationAppender apply = apply(generic, sb.toString());
            if (!generic.isArray()) {
                return apply;
            }
            TypeDescription.Generic componentType = generic.getComponentType();
            AnnotationValueFilter annotationValueFilter2 = this.annotationValueFilter;
            int i2 = this.typeReference;
            return (AnnotationAppender) componentType.accept(new ForTypeAnnotations(apply, annotationValueFilter2, i2, this.typePath + '['));
        }

        private AnnotationAppender apply(TypeDescription.Generic generic, String str) {
            AnnotationAppender annotationAppender2 = this.annotationAppender;
            for (AnnotationDescription append : generic.getDeclaredAnnotations()) {
                annotationAppender2 = annotationAppender2.append(append, this.annotationValueFilter, this.typeReference, str);
            }
            return annotationAppender2;
        }
    }
}

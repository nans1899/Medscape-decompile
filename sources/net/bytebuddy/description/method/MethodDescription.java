package net.bytebuddy.description.method;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;

public interface MethodDescription extends TypeVariableSource, ModifierReviewable.ForMethodDescription, NamedElement.WithGenericName, ByteCodeElement, ByteCodeElement.TypeDependant<InDefinedShape, Token> {
    public static final String CONSTRUCTOR_INTERNAL_NAME = "<init>";
    public static final String TYPE_INITIALIZER_INTERNAL_NAME = "<clinit>";
    public static final int TYPE_INITIALIZER_MODIFIER = 8;
    public static final InDefinedShape UNDEFINED = null;

    public interface InGenericShape extends MethodDescription {
        TypeDescription.Generic getDeclaringType();

        ParameterList<ParameterDescription.InGenericShape> getParameters();
    }

    SignatureToken asSignatureToken();

    TypeToken asTypeToken();

    int getActualModifiers();

    int getActualModifiers(boolean z);

    int getActualModifiers(boolean z, Visibility visibility);

    <T> T getDefaultValue(Class<T> cls);

    AnnotationValue<?, ?> getDefaultValue();

    TypeList.Generic getExceptionTypes();

    ParameterList<?> getParameters();

    TypeDescription.Generic getReceiverType();

    TypeDescription.Generic getReturnType();

    int getStackSize();

    boolean isBridgeCompatible(TypeToken typeToken);

    boolean isConstantBootstrap();

    boolean isConstantBootstrap(List<?> list);

    boolean isConstructor();

    boolean isDefaultMethod();

    boolean isDefaultValue();

    boolean isDefaultValue(AnnotationValue<?, ?> annotationValue);

    boolean isInvokableOn(TypeDescription typeDescription);

    boolean isInvokeBootstrap();

    boolean isInvokeBootstrap(List<?> list);

    boolean isMethod();

    boolean isSpecializableFor(TypeDescription typeDescription);

    boolean isTypeInitializer();

    boolean isVirtual();

    boolean represents(Constructor<?> constructor);

    boolean represents(Method method);

    public interface InDefinedShape extends MethodDescription {
        TypeDescription getDeclaringType();

        ParameterList<ParameterDescription.InDefinedShape> getParameters();

        public static abstract class AbstractBase extends AbstractBase implements InDefinedShape {
            public InDefinedShape asDefined() {
                return this;
            }

            public TypeDescription.Generic getReceiverType() {
                if (isStatic()) {
                    return TypeDescription.Generic.UNDEFINED;
                }
                if (!isConstructor()) {
                    return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(getDeclaringType());
                }
                TypeDescription declaringType = getDeclaringType();
                TypeDescription enclosingType = getDeclaringType().getEnclosingType();
                if (enclosingType == null) {
                    return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(declaringType);
                }
                if (declaringType.isStatic()) {
                    return enclosingType.asGenericType();
                }
                return TypeDescription.Generic.OfParameterizedType.ForGenerifiedErasure.of(enclosingType);
            }
        }
    }

    public static abstract class AbstractBase extends TypeVariableSource.AbstractBase implements MethodDescription {
        private static final int SOURCE_MODIFIERS = 1343;

        public int getStackSize() {
            return getParameters().asTypeList().getStackSize() + (isStatic() ^ true ? 1 : 0);
        }

        public boolean isMethod() {
            return !isConstructor() && !isTypeInitializer();
        }

        public boolean isConstructor() {
            return MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(getInternalName());
        }

        public boolean isTypeInitializer() {
            return MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME.equals(getInternalName());
        }

        public boolean represents(Method method) {
            return equals(new ForLoadedMethod(method));
        }

        public boolean represents(Constructor<?> constructor) {
            return equals(new ForLoadedConstructor(constructor));
        }

        public String getName() {
            if (isMethod()) {
                return getInternalName();
            }
            return getDeclaringType().asErasure().getName();
        }

        public String getActualName() {
            return isMethod() ? getName() : "";
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder();
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            for (TypeDescription descriptor : getParameters().asTypeList().asErasures()) {
                sb.append(descriptor.getDescriptor());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            sb.append(getReturnType().asErasure().getDescriptor());
            return sb.toString();
        }

        /* JADX WARNING: Removed duplicated region for block: B:32:0x00be A[Catch:{ GenericSignatureFormatError -> 0x00f5 }] */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x00ed A[Catch:{ GenericSignatureFormatError -> 0x00f5 }] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00f2 A[Catch:{ GenericSignatureFormatError -> 0x00f5 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String getGenericSignature() {
            /*
                r8 = this;
                net.bytebuddy.jar.asm.signature.SignatureWriter r0 = new net.bytebuddy.jar.asm.signature.SignatureWriter     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r0.<init>()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeList$Generic r1 = r8.getTypeVariables()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r2 = 1
                r3 = 0
                r4 = 0
            L_0x0010:
                boolean r5 = r1.hasNext()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r5 == 0) goto L_0x004f
                java.lang.Object r4 = r1.next()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic r4 = (net.bytebuddy.description.type.TypeDescription.Generic) r4     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                java.lang.String r5 = r4.getSymbol()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r0.visitFormalTypeParameter(r5)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeList$Generic r4 = r4.getUpperBounds()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                java.util.Iterator r4 = r4.iterator()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r5 = 1
            L_0x002c:
                boolean r6 = r4.hasNext()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r6 == 0) goto L_0x004d
                java.lang.Object r6 = r4.next()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic r6 = (net.bytebuddy.description.type.TypeDescription.Generic) r6     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor r7 = new net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r5 == 0) goto L_0x0041
                net.bytebuddy.jar.asm.signature.SignatureVisitor r5 = r0.visitClassBound()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                goto L_0x0045
            L_0x0041:
                net.bytebuddy.jar.asm.signature.SignatureVisitor r5 = r0.visitInterfaceBound()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
            L_0x0045:
                r7.<init>(r5)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r6.accept(r7)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r5 = 0
                goto L_0x002c
            L_0x004d:
                r4 = 1
                goto L_0x0010
            L_0x004f:
                net.bytebuddy.description.method.ParameterList r1 = r8.getParameters()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeList$Generic r1 = r1.asTypeList()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
            L_0x005b:
                boolean r5 = r1.hasNext()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r5 == 0) goto L_0x0084
                java.lang.Object r5 = r1.next()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic r5 = (net.bytebuddy.description.type.TypeDescription.Generic) r5     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor r6 = new net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.jar.asm.signature.SignatureVisitor r7 = r0.visitParameterType()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r6.<init>(r7)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r5.accept(r6)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r4 != 0) goto L_0x0082
                net.bytebuddy.description.type.TypeDefinition$Sort r4 = r5.getSort()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                boolean r4 = r4.isNonGeneric()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r4 != 0) goto L_0x0080
                goto L_0x0082
            L_0x0080:
                r4 = 0
                goto L_0x005b
            L_0x0082:
                r4 = 1
                goto L_0x005b
            L_0x0084:
                net.bytebuddy.description.type.TypeDescription$Generic r1 = r8.getReturnType()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor r5 = new net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.jar.asm.signature.SignatureVisitor r6 = r0.visitReturnType()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r5.<init>(r6)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r1.accept(r5)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r4 != 0) goto L_0x00a3
                net.bytebuddy.description.type.TypeDefinition$Sort r1 = r1.getSort()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                boolean r1 = r1.isNonGeneric()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r1 != 0) goto L_0x00a1
                goto L_0x00a3
            L_0x00a1:
                r1 = 0
                goto L_0x00a4
            L_0x00a3:
                r1 = 1
            L_0x00a4:
                net.bytebuddy.description.type.TypeList$Generic r4 = r8.getExceptionTypes()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDefinition$Sort r5 = net.bytebuddy.description.type.TypeDefinition.Sort.NON_GENERIC     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.matcher.ElementMatcher$Junction r5 = net.bytebuddy.matcher.ElementMatchers.ofSort((net.bytebuddy.description.type.TypeDefinition.Sort) r5)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.matcher.ElementMatcher$Junction r5 = net.bytebuddy.matcher.ElementMatchers.not(r5)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.matcher.FilterableList r5 = r4.filter(r5)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeList$Generic r5 = (net.bytebuddy.description.type.TypeList.Generic) r5     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                boolean r5 = r5.isEmpty()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r5 != 0) goto L_0x00eb
                java.util.Iterator r4 = r4.iterator()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
            L_0x00c2:
                boolean r5 = r4.hasNext()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r5 == 0) goto L_0x00eb
                java.lang.Object r5 = r4.next()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic r5 = (net.bytebuddy.description.type.TypeDescription.Generic) r5     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor r6 = new net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                net.bytebuddy.jar.asm.signature.SignatureVisitor r7 = r0.visitExceptionType()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r6.<init>(r7)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                r5.accept(r6)     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r1 != 0) goto L_0x00e9
                net.bytebuddy.description.type.TypeDefinition$Sort r1 = r5.getSort()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                boolean r1 = r1.isNonGeneric()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                if (r1 != 0) goto L_0x00e7
                goto L_0x00e9
            L_0x00e7:
                r1 = 0
                goto L_0x00c2
            L_0x00e9:
                r1 = 1
                goto L_0x00c2
            L_0x00eb:
                if (r1 == 0) goto L_0x00f2
                java.lang.String r0 = r0.toString()     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
                goto L_0x00f4
            L_0x00f2:
                java.lang.String r0 = NON_GENERIC_SIGNATURE     // Catch:{ GenericSignatureFormatError -> 0x00f5 }
            L_0x00f4:
                return r0
            L_0x00f5:
                java.lang.String r0 = NON_GENERIC_SIGNATURE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.method.MethodDescription.AbstractBase.getGenericSignature():java.lang.String");
        }

        public int getActualModifiers() {
            return getModifiers() | (getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Deprecated.class) ? 131072 : 0);
        }

        public int getActualModifiers(boolean z) {
            if (z) {
                return getActualModifiers() & -1281;
            }
            return (getActualModifiers() & -257) | 1024;
        }

        public int getActualModifiers(boolean z, Visibility visibility) {
            return ModifierContributor.Resolver.of(Collections.singleton(getVisibility().expandTo(visibility))).resolve(getActualModifiers(z));
        }

        public boolean isVisibleTo(TypeDescription typeDescription) {
            return (isVirtual() || getDeclaringType().asErasure().isVisibleTo(typeDescription)) && (isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || ((isProtected() && getDeclaringType().asErasure().isAssignableFrom(typeDescription)) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure()))));
        }

        public boolean isAccessibleTo(TypeDescription typeDescription) {
            return ((isVirtual() || getDeclaringType().asErasure().isVisibleTo(typeDescription)) && (isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure())))) || (isPrivate() && typeDescription.isNestMateOf(getDeclaringType().asErasure()));
        }

        public boolean isVirtual() {
            return !isConstructor() && !isPrivate() && !isStatic() && !isTypeInitializer();
        }

        public boolean isDefaultMethod() {
            return !isAbstract() && !isBridge() && getDeclaringType().isInterface();
        }

        public boolean isSpecializableFor(TypeDescription typeDescription) {
            if (isStatic()) {
                return false;
            }
            if (isPrivate() || isConstructor()) {
                return getDeclaringType().equals(typeDescription);
            }
            if (isAbstract() || !getDeclaringType().asErasure().isAssignableFrom(typeDescription)) {
                return false;
            }
            return true;
        }

        public <T> T getDefaultValue(Class<T> cls) {
            return cls.cast(getDefaultValue());
        }

        public boolean isInvokableOn(TypeDescription typeDescription) {
            return !isStatic() && !isTypeInitializer() && isVisibleTo(typeDescription) && (!isVirtual() ? getDeclaringType().asErasure().equals(typeDescription) : getDeclaringType().asErasure().isAssignableFrom(typeDescription));
        }

        private boolean isBootstrap(TypeDescription typeDescription) {
            TypeList asErasures = getParameters().asTypeList().asErasures();
            int size = asErasures.size();
            if (size == 0) {
                return false;
            }
            if (size == 1) {
                return ((TypeDescription) asErasures.getOnly()).represents(Object[].class);
            }
            if (size != 2) {
                if (size != 3) {
                    if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) asErasures.get(0))) {
                        return false;
                    }
                    if ((((TypeDescription) asErasures.get(1)).represents(Object.class) || ((TypeDescription) asErasures.get(1)).represents(String.class)) && ((TypeDescription) asErasures.get(2)).isAssignableFrom(typeDescription)) {
                        return true;
                    }
                    return false;
                } else if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) asErasures.get(0))) {
                    return false;
                } else {
                    if (!((TypeDescription) asErasures.get(1)).represents(Object.class) && !((TypeDescription) asErasures.get(1)).represents(String.class)) {
                        return false;
                    }
                    if (((TypeDescription) asErasures.get(2)).represents(Object[].class) || ((TypeDescription) asErasures.get(2)).isAssignableFrom(typeDescription)) {
                        return true;
                    }
                    return false;
                }
            } else if (!JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().isAssignableTo((TypeDescription) asErasures.get(0)) || !((TypeDescription) asErasures.get(1)).represents(Object[].class)) {
                return false;
            } else {
                return true;
            }
        }

        private boolean isBootstrap(List<?> list) {
            for (Object next : list) {
                if (next != null) {
                    Class<?> cls = next.getClass();
                    if (cls != String.class && cls != Integer.class && cls != Long.class && cls != Float.class && cls != Double.class && !TypeDescription.class.isAssignableFrom(cls) && !JavaConstant.class.isAssignableFrom(cls)) {
                        throw new IllegalArgumentException("Not a Java constant representation: " + next);
                    }
                } else {
                    throw new IllegalArgumentException("The null value is not a bootstrap constant");
                }
            }
            TypeList asErasures = getParameters().asTypeList().asErasures();
            int i = 4;
            if (asErasures.size() >= 4) {
                Iterator<?> it = list.iterator();
                for (TypeDescription typeDescription : (TypeList) asErasures.subList(3, asErasures.size())) {
                    boolean z = !it.hasNext();
                    if (!z) {
                        Object next2 = it.next();
                        z = (!(next2 instanceof JavaConstant) || !((JavaConstant) next2).getType().isAssignableTo(typeDescription)) && (!typeDescription.represents(Class.class) || !(next2 instanceof TypeDescription) || ((TypeDescription) next2).isPrimitive()) && ((!typeDescription.represents(String.class) || next2.getClass() != String.class) && ((!typeDescription.represents(Integer.TYPE) || next2.getClass() != Integer.class) && ((!typeDescription.represents(Long.TYPE) || next2.getClass() != Long.class) && ((!typeDescription.represents(Float.TYPE) || next2.getClass() != Float.class) && (!typeDescription.represents(Double.TYPE) || next2.getClass() != Double.class)))));
                    }
                    if (!z) {
                        i++;
                    } else if (i != asErasures.size() || !typeDescription.represents(Object[].class)) {
                        return false;
                    } else {
                        return true;
                    }
                }
                return true;
            } else if (list.isEmpty() || ((TypeDescription) asErasures.get(asErasures.size() - 1)).represents(Object[].class)) {
                return true;
            } else {
                return false;
            }
        }

        public boolean isInvokeBootstrap() {
            TypeDescription asErasure = getReturnType().asErasure();
            if (isMethod()) {
                if (!isStatic()) {
                    return false;
                }
                if (!JavaType.CALL_SITE.getTypeStub().isAssignableFrom(asErasure) && !JavaType.CALL_SITE.getTypeStub().isAssignableTo(asErasure)) {
                    return false;
                }
            }
            if (!isConstructor() || JavaType.CALL_SITE.getTypeStub().isAssignableFrom(getDeclaringType().asErasure())) {
                return isBootstrap(JavaType.METHOD_TYPE.getTypeStub());
            }
            return false;
        }

        public boolean isInvokeBootstrap(List<?> list) {
            return isInvokeBootstrap() && isBootstrap(list);
        }

        public boolean isConstantBootstrap() {
            if (getParameters().isEmpty() || !((ParameterDescription) getParameters().get(0)).getType().asErasure().equals(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub()) || !isBootstrap(TypeDescription.CLASS)) {
                return false;
            }
            return true;
        }

        public boolean isConstantBootstrap(List<?> list) {
            return isConstantBootstrap() && isBootstrap(list);
        }

        public boolean isDefaultValue() {
            return !isConstructor() && !isStatic() && getReturnType().asErasure().isAnnotationReturnType() && getParameters().isEmpty();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0098, code lost:
            if (isEnumerationType(r0, (net.bytebuddy.description.enumeration.EnumerationDescription) r6) == false) goto L_0x009a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b1, code lost:
            if (isAnnotationType(r0, (net.bytebuddy.description.annotation.AnnotationDescription) r6) == false) goto L_0x00b3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isDefaultValue(net.bytebuddy.description.annotation.AnnotationValue<?, ?> r6) {
            /*
                r5 = this;
                boolean r0 = r5.isDefaultValue()
                r1 = 0
                if (r0 != 0) goto L_0x0008
                return r1
            L_0x0008:
                net.bytebuddy.description.type.TypeDescription$Generic r0 = r5.getReturnType()
                net.bytebuddy.description.type.TypeDescription r0 = r0.asErasure()
                java.lang.Object r6 = r6.resolve()
                java.lang.Class r2 = java.lang.Boolean.TYPE
                boolean r2 = r0.represents(r2)
                r3 = 1
                if (r2 == 0) goto L_0x0021
                boolean r2 = r6 instanceof java.lang.Boolean
                if (r2 != 0) goto L_0x016d
            L_0x0021:
                java.lang.Class r2 = java.lang.Byte.TYPE
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x002d
                boolean r2 = r6 instanceof java.lang.Byte
                if (r2 != 0) goto L_0x016d
            L_0x002d:
                java.lang.Class r2 = java.lang.Character.TYPE
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0039
                boolean r2 = r6 instanceof java.lang.Character
                if (r2 != 0) goto L_0x016d
            L_0x0039:
                java.lang.Class r2 = java.lang.Short.TYPE
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0045
                boolean r2 = r6 instanceof java.lang.Short
                if (r2 != 0) goto L_0x016d
            L_0x0045:
                java.lang.Class r2 = java.lang.Integer.TYPE
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0051
                boolean r2 = r6 instanceof java.lang.Integer
                if (r2 != 0) goto L_0x016d
            L_0x0051:
                java.lang.Class r2 = java.lang.Long.TYPE
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x005d
                boolean r2 = r6 instanceof java.lang.Long
                if (r2 != 0) goto L_0x016d
            L_0x005d:
                java.lang.Class r2 = java.lang.Float.TYPE
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0069
                boolean r2 = r6 instanceof java.lang.Float
                if (r2 != 0) goto L_0x016d
            L_0x0069:
                java.lang.Class r2 = java.lang.Double.TYPE
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0075
                boolean r2 = r6 instanceof java.lang.Double
                if (r2 != 0) goto L_0x016d
            L_0x0075:
                java.lang.Class<java.lang.String> r2 = java.lang.String.class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0081
                boolean r2 = r6 instanceof java.lang.String
                if (r2 != 0) goto L_0x016d
            L_0x0081:
                java.lang.Class<java.lang.Enum> r2 = java.lang.Enum.class
                boolean r2 = r0.isAssignableTo((java.lang.Class<?>) r2)
                if (r2 == 0) goto L_0x009a
                boolean r2 = r6 instanceof net.bytebuddy.description.enumeration.EnumerationDescription
                if (r2 == 0) goto L_0x009a
                net.bytebuddy.description.enumeration.EnumerationDescription[] r2 = new net.bytebuddy.description.enumeration.EnumerationDescription[r3]
                r4 = r6
                net.bytebuddy.description.enumeration.EnumerationDescription r4 = (net.bytebuddy.description.enumeration.EnumerationDescription) r4
                r2[r1] = r4
                boolean r2 = isEnumerationType(r0, r2)
                if (r2 != 0) goto L_0x016d
            L_0x009a:
                java.lang.Class<java.lang.annotation.Annotation> r2 = java.lang.annotation.Annotation.class
                boolean r2 = r0.isAssignableTo((java.lang.Class<?>) r2)
                if (r2 == 0) goto L_0x00b3
                boolean r2 = r6 instanceof net.bytebuddy.description.annotation.AnnotationDescription
                if (r2 == 0) goto L_0x00b3
                net.bytebuddy.description.annotation.AnnotationDescription[] r2 = new net.bytebuddy.description.annotation.AnnotationDescription[r3]
                r4 = r6
                net.bytebuddy.description.annotation.AnnotationDescription r4 = (net.bytebuddy.description.annotation.AnnotationDescription) r4
                r2[r1] = r4
                boolean r2 = isAnnotationType(r0, r2)
                if (r2 != 0) goto L_0x016d
            L_0x00b3:
                java.lang.Class<java.lang.Class> r2 = java.lang.Class.class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x00bf
                boolean r2 = r6 instanceof net.bytebuddy.description.type.TypeDescription
                if (r2 != 0) goto L_0x016d
            L_0x00bf:
                java.lang.Class<boolean[]> r2 = boolean[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x00cb
                boolean r2 = r6 instanceof boolean[]
                if (r2 != 0) goto L_0x016d
            L_0x00cb:
                java.lang.Class<byte[]> r2 = byte[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x00d7
                boolean r2 = r6 instanceof byte[]
                if (r2 != 0) goto L_0x016d
            L_0x00d7:
                java.lang.Class<char[]> r2 = char[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x00e3
                boolean r2 = r6 instanceof char[]
                if (r2 != 0) goto L_0x016d
            L_0x00e3:
                java.lang.Class<short[]> r2 = short[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x00ef
                boolean r2 = r6 instanceof short[]
                if (r2 != 0) goto L_0x016d
            L_0x00ef:
                java.lang.Class<int[]> r2 = int[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x00fb
                boolean r2 = r6 instanceof int[]
                if (r2 != 0) goto L_0x016d
            L_0x00fb:
                java.lang.Class<long[]> r2 = long[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0107
                boolean r2 = r6 instanceof long[]
                if (r2 != 0) goto L_0x016d
            L_0x0107:
                java.lang.Class<float[]> r2 = float[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x0113
                boolean r2 = r6 instanceof float[]
                if (r2 != 0) goto L_0x016d
            L_0x0113:
                java.lang.Class<double[]> r2 = double[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x011f
                boolean r2 = r6 instanceof double[]
                if (r2 != 0) goto L_0x016d
            L_0x011f:
                java.lang.Class<java.lang.String[]> r2 = java.lang.String[].class
                boolean r2 = r0.represents(r2)
                if (r2 == 0) goto L_0x012b
                boolean r2 = r6 instanceof java.lang.String[]
                if (r2 != 0) goto L_0x016d
            L_0x012b:
                java.lang.Class<java.lang.Enum[]> r2 = java.lang.Enum[].class
                boolean r2 = r0.isAssignableTo((java.lang.Class<?>) r2)
                if (r2 == 0) goto L_0x0146
                boolean r2 = r6 instanceof net.bytebuddy.description.enumeration.EnumerationDescription[]
                if (r2 == 0) goto L_0x0146
                net.bytebuddy.description.type.TypeDescription r2 = r0.getComponentType()
                r4 = r6
                net.bytebuddy.description.enumeration.EnumerationDescription[] r4 = (net.bytebuddy.description.enumeration.EnumerationDescription[]) r4
                net.bytebuddy.description.enumeration.EnumerationDescription[] r4 = (net.bytebuddy.description.enumeration.EnumerationDescription[]) r4
                boolean r2 = isEnumerationType(r2, r4)
                if (r2 != 0) goto L_0x016d
            L_0x0146:
                java.lang.Class<java.lang.annotation.Annotation[]> r2 = java.lang.annotation.Annotation[].class
                boolean r2 = r0.isAssignableTo((java.lang.Class<?>) r2)
                if (r2 == 0) goto L_0x0161
                boolean r2 = r6 instanceof net.bytebuddy.description.annotation.AnnotationDescription[]
                if (r2 == 0) goto L_0x0161
                net.bytebuddy.description.type.TypeDescription r2 = r0.getComponentType()
                r4 = r6
                net.bytebuddy.description.annotation.AnnotationDescription[] r4 = (net.bytebuddy.description.annotation.AnnotationDescription[]) r4
                net.bytebuddy.description.annotation.AnnotationDescription[] r4 = (net.bytebuddy.description.annotation.AnnotationDescription[]) r4
                boolean r2 = isAnnotationType(r2, r4)
                if (r2 != 0) goto L_0x016d
            L_0x0161:
                java.lang.Class<java.lang.Class[]> r2 = java.lang.Class[].class
                boolean r0 = r0.represents(r2)
                if (r0 == 0) goto L_0x016e
                boolean r6 = r6 instanceof net.bytebuddy.description.type.TypeDescription[]
                if (r6 == 0) goto L_0x016e
            L_0x016d:
                r1 = 1
            L_0x016e:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.method.MethodDescription.AbstractBase.isDefaultValue(net.bytebuddy.description.annotation.AnnotationValue):boolean");
        }

        private static boolean isEnumerationType(TypeDescription typeDescription, EnumerationDescription... enumerationDescriptionArr) {
            for (EnumerationDescription enumerationType : enumerationDescriptionArr) {
                if (!enumerationType.getEnumerationType().equals(typeDescription)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isAnnotationType(TypeDescription typeDescription, AnnotationDescription... annotationDescriptionArr) {
            for (AnnotationDescription annotationType : annotationDescriptionArr) {
                if (!annotationType.getAnnotationType().equals(typeDescription)) {
                    return false;
                }
            }
            return true;
        }

        public TypeVariableSource getEnclosingSource() {
            if (isStatic()) {
                return TypeVariableSource.UNDEFINED;
            }
            return getDeclaringType().asErasure();
        }

        public <T> T accept(TypeVariableSource.Visitor<T> visitor) {
            return visitor.onMethod((InDefinedShape) asDefined());
        }

        public boolean isGenerified() {
            return !getTypeVariables().isEmpty();
        }

        public Token asToken(ElementMatcher<? super TypeDescription> elementMatcher) {
            TypeDescription.Generic generic;
            TypeDescription.Generic receiverType = getReceiverType();
            String internalName = getInternalName();
            int modifiers = getModifiers();
            ByteCodeElement.Token.TokenList<TypeVariableToken> asTokenList = getTypeVariables().asTokenList(elementMatcher);
            TypeDescription.Generic generic2 = (TypeDescription.Generic) getReturnType().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher));
            ByteCodeElement.Token.TokenList<ParameterDescription.Token> asTokenList2 = getParameters().asTokenList(elementMatcher);
            TypeList.Generic accept = getExceptionTypes().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher));
            AnnotationList declaredAnnotations = getDeclaredAnnotations();
            AnnotationValue<?, ?> defaultValue = getDefaultValue();
            if (receiverType == null) {
                generic = TypeDescription.Generic.UNDEFINED;
            } else {
                generic = (TypeDescription.Generic) receiverType.accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher));
            }
            return new Token(internalName, modifiers, asTokenList, generic2, asTokenList2, accept, declaredAnnotations, defaultValue, generic);
        }

        public SignatureToken asSignatureToken() {
            return new SignatureToken(getInternalName(), getReturnType().asErasure(), getParameters().asTypeList().asErasures());
        }

        public TypeToken asTypeToken() {
            return new TypeToken(getReturnType().asErasure(), getParameters().asTypeList().asErasures());
        }

        public boolean isBridgeCompatible(TypeToken typeToken) {
            TypeList asErasures = getParameters().asTypeList().asErasures();
            List<TypeDescription> parameterTypes = typeToken.getParameterTypes();
            if (asErasures.size() != parameterTypes.size()) {
                return false;
            }
            for (int i = 0; i < asErasures.size(); i++) {
                if (!((TypeDescription) asErasures.get(i)).equals(parameterTypes.get(i)) && (((TypeDescription) asErasures.get(i)).isPrimitive() || parameterTypes.get(i).isPrimitive())) {
                    return false;
                }
            }
            TypeDescription asErasure = getReturnType().asErasure();
            TypeDescription returnType = typeToken.getReturnType();
            if (asErasure.equals(returnType) || (!asErasure.isPrimitive() && !returnType.isPrimitive())) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return ((((((getDeclaringType().hashCode() + 17) * 31) + getInternalName().hashCode()) * 31) + getReturnType().asErasure().hashCode()) * 31) + getParameters().asTypeList().asErasures().hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodDescription)) {
                return false;
            }
            MethodDescription methodDescription = (MethodDescription) obj;
            if (!getInternalName().equals(methodDescription.getInternalName()) || !getDeclaringType().equals(methodDescription.getDeclaringType()) || !getReturnType().asErasure().equals(methodDescription.getReturnType().asErasure()) || !getParameters().asTypeList().asErasures().equals(methodDescription.getParameters().asTypeList().asErasures())) {
                return false;
            }
            return true;
        }

        public String toGenericString() {
            StringBuilder sb = new StringBuilder();
            int modifiers = getModifiers() & SOURCE_MODIFIERS;
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers));
                sb.append(' ');
            }
            if (isMethod()) {
                sb.append(getReturnType().getActualName());
                sb.append(' ');
                sb.append(getDeclaringType().asErasure().getActualName());
                sb.append('.');
            }
            sb.append(getName());
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            boolean z = true;
            boolean z2 = true;
            for (TypeDescription.Generic generic : getParameters().asTypeList()) {
                if (!z2) {
                    sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                } else {
                    z2 = false;
                }
                sb.append(generic.getActualName());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            TypeList.Generic<TypeDescription.Generic> exceptionTypes = getExceptionTypes();
            if (!exceptionTypes.isEmpty()) {
                sb.append(" throws ");
                for (TypeDescription.Generic generic2 : exceptionTypes) {
                    if (!z) {
                        sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                    } else {
                        z = false;
                    }
                    sb.append(generic2.getActualName());
                }
            }
            return sb.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            int modifiers = getModifiers() & SOURCE_MODIFIERS;
            if (modifiers != 0) {
                sb.append(Modifier.toString(modifiers));
                sb.append(' ');
            }
            if (isMethod()) {
                sb.append(getReturnType().asErasure().getActualName());
                sb.append(' ');
                sb.append(getDeclaringType().asErasure().getActualName());
                sb.append('.');
            }
            sb.append(getName());
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            boolean z = true;
            boolean z2 = true;
            for (TypeDescription typeDescription : getParameters().asTypeList().asErasures()) {
                if (!z2) {
                    sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                } else {
                    z2 = false;
                }
                sb.append(typeDescription.getActualName());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            TypeList<TypeDescription> asErasures = getExceptionTypes().asErasures();
            if (!asErasures.isEmpty()) {
                sb.append(" throws ");
                for (TypeDescription typeDescription2 : asErasures) {
                    if (!z) {
                        sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                    } else {
                        z = false;
                    }
                    sb.append(typeDescription2.getActualName());
                }
            }
            return sb.toString();
        }
    }

    public static class ForLoadedConstructor extends InDefinedShape.AbstractBase implements ParameterDescription.ForLoadedParameter.ParameterAnnotationSource {
        private final Constructor<?> constructor;
        private transient /* synthetic */ AnnotationList declaredAnnotations;
        private transient /* synthetic */ Annotation[][] parameterAnnotations;
        private transient /* synthetic */ ParameterList parameters;

        public String getInternalName() {
            return MethodDescription.CONSTRUCTOR_INTERNAL_NAME;
        }

        public boolean isConstructor() {
            return true;
        }

        public boolean isTypeInitializer() {
            return false;
        }

        public boolean represents(Method method) {
            return false;
        }

        public ForLoadedConstructor(Constructor<?> constructor2) {
            this.constructor = constructor2;
        }

        public TypeDescription getDeclaringType() {
            return TypeDescription.ForLoadedType.of(this.constructor.getDeclaringClass());
        }

        public TypeDescription.Generic getReturnType() {
            return TypeDescription.Generic.VOID;
        }

        @CachedReturnPlugin.Enhance("parameters")
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            ParameterList<ParameterDescription.InDefinedShape> of = this.parameters != null ? null : ParameterList.ForLoadedExecutable.of(this.constructor, (ParameterDescription.ForLoadedParameter.ParameterAnnotationSource) this);
            if (of == null) {
                return this.parameters;
            }
            this.parameters = of;
            return of;
        }

        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.OfConstructorExceptionTypes(this.constructor);
        }

        public boolean represents(Constructor<?> constructor2) {
            return this.constructor.equals(constructor2) || equals(new ForLoadedConstructor(constructor2));
        }

        public String getName() {
            return this.constructor.getName();
        }

        public int getModifiers() {
            return this.constructor.getModifiers();
        }

        public boolean isSynthetic() {
            return this.constructor.isSynthetic();
        }

        public String getDescriptor() {
            return Type.getConstructorDescriptor(this.constructor);
        }

        public AnnotationValue<?, ?> getDefaultValue() {
            return AnnotationValue.UNDEFINED;
        }

        @CachedReturnPlugin.Enhance("declaredAnnotations")
        public AnnotationList getDeclaredAnnotations() {
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations = this.declaredAnnotations != null ? null : new AnnotationList.ForLoadedAnnotations(this.constructor.getDeclaredAnnotations());
            if (forLoadedAnnotations == null) {
                return this.declaredAnnotations;
            }
            this.declaredAnnotations = forLoadedAnnotations;
            return forLoadedAnnotations;
        }

        public TypeList.Generic getTypeVariables() {
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of(this.constructor);
        }

        public TypeDescription.Generic getReceiverType() {
            TypeDescription.Generic resolveReceiverType = TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveReceiverType(this.constructor);
            return resolveReceiverType == null ? super.getReceiverType() : resolveReceiverType;
        }

        @CachedReturnPlugin.Enhance("parameterAnnotations")
        public Annotation[][] getParameterAnnotations() {
            Annotation[][] parameterAnnotations2 = this.parameterAnnotations != null ? null : this.constructor.getParameterAnnotations();
            if (parameterAnnotations2 == null) {
                return this.parameterAnnotations;
            }
            this.parameterAnnotations = parameterAnnotations2;
            return parameterAnnotations2;
        }
    }

    public static class ForLoadedMethod extends InDefinedShape.AbstractBase implements ParameterDescription.ForLoadedParameter.ParameterAnnotationSource {
        private transient /* synthetic */ AnnotationList declaredAnnotations;
        private final Method method;
        private transient /* synthetic */ Annotation[][] parameterAnnotations;
        private transient /* synthetic */ ParameterList parameters;

        public boolean isConstructor() {
            return false;
        }

        public boolean isTypeInitializer() {
            return false;
        }

        public boolean represents(Constructor<?> constructor) {
            return false;
        }

        public ForLoadedMethod(Method method2) {
            this.method = method2;
        }

        public TypeDescription getDeclaringType() {
            return TypeDescription.ForLoadedType.of(this.method.getDeclaringClass());
        }

        public TypeDescription.Generic getReturnType() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(this.method.getReturnType());
            }
            return new TypeDescription.Generic.LazyProjection.ForLoadedReturnType(this.method);
        }

        @CachedReturnPlugin.Enhance("parameters")
        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            ParameterList<ParameterDescription.InDefinedShape> of = this.parameters != null ? null : ParameterList.ForLoadedExecutable.of(this.method, (ParameterDescription.ForLoadedParameter.ParameterAnnotationSource) this);
            if (of == null) {
                return this.parameters;
            }
            this.parameters = of;
            return of;
        }

        public TypeList.Generic getExceptionTypes() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeList.Generic.ForLoadedTypes((java.lang.reflect.Type[]) this.method.getExceptionTypes());
            }
            return new TypeList.Generic.OfMethodExceptionTypes(this.method);
        }

        public boolean isBridge() {
            return this.method.isBridge();
        }

        public boolean represents(Method method2) {
            return this.method.equals(method2) || equals(new ForLoadedMethod(method2));
        }

        public String getName() {
            return this.method.getName();
        }

        public int getModifiers() {
            return this.method.getModifiers();
        }

        public boolean isSynthetic() {
            return this.method.isSynthetic();
        }

        public String getInternalName() {
            return this.method.getName();
        }

        public String getDescriptor() {
            return Type.getMethodDescriptor(this.method);
        }

        public Method getLoadedMethod() {
            return this.method;
        }

        @CachedReturnPlugin.Enhance("declaredAnnotations")
        public AnnotationList getDeclaredAnnotations() {
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations = this.declaredAnnotations != null ? null : new AnnotationList.ForLoadedAnnotations(this.method.getDeclaredAnnotations());
            if (forLoadedAnnotations == null) {
                return this.declaredAnnotations;
            }
            this.declaredAnnotations = forLoadedAnnotations;
            return forLoadedAnnotations;
        }

        public AnnotationValue<?, ?> getDefaultValue() {
            Object defaultValue = this.method.getDefaultValue();
            if (defaultValue == null) {
                return AnnotationValue.UNDEFINED;
            }
            return AnnotationDescription.ForLoadedAnnotation.asValue(defaultValue, this.method.getReturnType());
        }

        public TypeList.Generic getTypeVariables() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return new TypeList.Generic.Empty();
            }
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of(this.method);
        }

        public TypeDescription.Generic getReceiverType() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return super.getReceiverType();
            }
            TypeDescription.Generic resolveReceiverType = TypeDescription.Generic.AnnotationReader.DISPATCHER.resolveReceiverType(this.method);
            return resolveReceiverType == null ? super.getReceiverType() : resolveReceiverType;
        }

        @CachedReturnPlugin.Enhance("parameterAnnotations")
        public Annotation[][] getParameterAnnotations() {
            Annotation[][] parameterAnnotations2 = this.parameterAnnotations != null ? null : this.method.getParameterAnnotations();
            if (parameterAnnotations2 == null) {
                return this.parameterAnnotations;
            }
            this.parameterAnnotations = parameterAnnotations2;
            return parameterAnnotations2;
        }
    }

    public static class Latent extends InDefinedShape.AbstractBase {
        private final List<? extends AnnotationDescription> declaredAnnotations;
        private final TypeDescription declaringType;
        private final AnnotationValue<?, ?> defaultValue;
        private final List<? extends TypeDescription.Generic> exceptionTypes;
        private final String internalName;
        private final int modifiers;
        private final List<? extends ParameterDescription.Token> parameterTokens;
        private final TypeDescription.Generic receiverType;
        private final TypeDescription.Generic returnType;
        private final List<? extends TypeVariableToken> typeVariables;

        public Latent(TypeDescription typeDescription, Token token) {
            this(typeDescription, token.getName(), token.getModifiers(), token.getTypeVariableTokens(), token.getReturnType(), token.getParameterTokens(), token.getExceptionTypes(), token.getAnnotations(), token.getDefaultValue(), token.getReceiverType());
        }

        public Latent(TypeDescription typeDescription, String str, int i, List<? extends TypeVariableToken> list, TypeDescription.Generic generic, List<? extends ParameterDescription.Token> list2, List<? extends TypeDescription.Generic> list3, List<? extends AnnotationDescription> list4, AnnotationValue<?, ?> annotationValue, TypeDescription.Generic generic2) {
            this.declaringType = typeDescription;
            this.internalName = str;
            this.modifiers = i;
            this.typeVariables = list;
            this.returnType = generic;
            this.parameterTokens = list2;
            this.exceptionTypes = list3;
            this.declaredAnnotations = list4;
            this.defaultValue = annotationValue;
            this.receiverType = generic2;
        }

        public TypeList.Generic getTypeVariables() {
            return TypeList.Generic.ForDetachedTypes.attachVariables((MethodDescription) this, this.typeVariables);
        }

        public TypeDescription.Generic getReturnType() {
            return (TypeDescription.Generic) this.returnType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of((MethodDescription) this));
        }

        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
            return new ParameterList.ForTokens(this, this.parameterTokens);
        }

        public TypeList.Generic getExceptionTypes() {
            return TypeList.Generic.ForDetachedTypes.attach((MethodDescription) this, this.exceptionTypes);
        }

        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.declaredAnnotations);
        }

        public String getInternalName() {
            return this.internalName;
        }

        public TypeDescription getDeclaringType() {
            return this.declaringType;
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public AnnotationValue<?, ?> getDefaultValue() {
            return this.defaultValue;
        }

        public TypeDescription.Generic getReceiverType() {
            TypeDescription.Generic generic = this.receiverType;
            if (generic == null) {
                return super.getReceiverType();
            }
            return (TypeDescription.Generic) generic.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of((MethodDescription) this));
        }

        public static class TypeInitializer extends InDefinedShape.AbstractBase {
            private final TypeDescription typeDescription;

            public String getInternalName() {
                return MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME;
            }

            public int getModifiers() {
                return 8;
            }

            public TypeInitializer(TypeDescription typeDescription2) {
                this.typeDescription = typeDescription2;
            }

            public TypeDescription.Generic getReturnType() {
                return TypeDescription.Generic.VOID;
            }

            public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                return new ParameterList.Empty();
            }

            public TypeList.Generic getExceptionTypes() {
                return new TypeList.Generic.Empty();
            }

            public AnnotationValue<?, ?> getDefaultValue() {
                return AnnotationValue.UNDEFINED;
            }

            public TypeList.Generic getTypeVariables() {
                return new TypeList.Generic.Empty();
            }

            public AnnotationList getDeclaredAnnotations() {
                return new AnnotationList.Empty();
            }

            public TypeDescription getDeclaringType() {
                return this.typeDescription;
            }
        }
    }

    public static class TypeSubstituting extends AbstractBase implements InGenericShape {
        private final TypeDescription.Generic declaringType;
        private final MethodDescription methodDescription;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, MethodDescription methodDescription2, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
            this.declaringType = generic;
            this.methodDescription = methodDescription2;
            this.visitor = visitor2;
        }

        public TypeDescription.Generic getReturnType() {
            return (TypeDescription.Generic) this.methodDescription.getReturnType().accept(this.visitor);
        }

        public TypeList.Generic getTypeVariables() {
            return (TypeList.Generic) this.methodDescription.getTypeVariables().accept(this.visitor).filter(ElementMatchers.ofSort(TypeDefinition.Sort.VARIABLE));
        }

        public ParameterList<ParameterDescription.InGenericShape> getParameters() {
            return new ParameterList.TypeSubstituting(this, this.methodDescription.getParameters(), this.visitor);
        }

        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.ForDetachedTypes(this.methodDescription.getExceptionTypes(), this.visitor);
        }

        public AnnotationValue<?, ?> getDefaultValue() {
            return this.methodDescription.getDefaultValue();
        }

        public TypeDescription.Generic getReceiverType() {
            TypeDescription.Generic receiverType = this.methodDescription.getReceiverType();
            if (receiverType == null) {
                return TypeDescription.Generic.UNDEFINED;
            }
            return (TypeDescription.Generic) receiverType.accept(this.visitor);
        }

        public AnnotationList getDeclaredAnnotations() {
            return this.methodDescription.getDeclaredAnnotations();
        }

        public TypeDescription.Generic getDeclaringType() {
            return this.declaringType;
        }

        public int getModifiers() {
            return this.methodDescription.getModifiers();
        }

        public String getInternalName() {
            return this.methodDescription.getInternalName();
        }

        public InDefinedShape asDefined() {
            return (InDefinedShape) this.methodDescription.asDefined();
        }

        public boolean isConstructor() {
            return this.methodDescription.isConstructor();
        }

        public boolean isMethod() {
            return this.methodDescription.isMethod();
        }

        public boolean isTypeInitializer() {
            return this.methodDescription.isTypeInitializer();
        }
    }

    public static class Token implements ByteCodeElement.Token<Token> {
        private final List<? extends AnnotationDescription> annotations;
        private final AnnotationValue<?, ?> defaultValue;
        private final List<? extends TypeDescription.Generic> exceptionTypes;
        private final int modifiers;
        private final String name;
        private final List<? extends ParameterDescription.Token> parameterTokens;
        private final TypeDescription.Generic receiverType;
        private final TypeDescription.Generic returnType;
        private final List<? extends TypeVariableToken> typeVariableTokens;

        public Token(int i) {
            this(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, i, TypeDescription.Generic.VOID);
        }

        public Token(String str, int i, TypeDescription.Generic generic) {
            this(str, i, generic, Collections.emptyList());
        }

        public Token(String str, int i, TypeDescription.Generic generic, List<? extends TypeDescription.Generic> list) {
            this(str, i, Collections.emptyList(), generic, new ParameterDescription.Token.TypeList(list), Collections.emptyList(), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED);
        }

        public Token(String str, int i, List<? extends TypeVariableToken> list, TypeDescription.Generic generic, List<? extends ParameterDescription.Token> list2, List<? extends TypeDescription.Generic> list3, List<? extends AnnotationDescription> list4, AnnotationValue<?, ?> annotationValue, TypeDescription.Generic generic2) {
            this.name = str;
            this.modifiers = i;
            this.typeVariableTokens = list;
            this.returnType = generic;
            this.parameterTokens = list2;
            this.exceptionTypes = list3;
            this.annotations = list4;
            this.defaultValue = annotationValue;
            this.receiverType = generic2;
        }

        public String getName() {
            return this.name;
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public ByteCodeElement.Token.TokenList<TypeVariableToken> getTypeVariableTokens() {
            return new ByteCodeElement.Token.TokenList<>(this.typeVariableTokens);
        }

        public TypeDescription.Generic getReturnType() {
            return this.returnType;
        }

        public ByteCodeElement.Token.TokenList<ParameterDescription.Token> getParameterTokens() {
            return new ByteCodeElement.Token.TokenList<>(this.parameterTokens);
        }

        public TypeList.Generic getExceptionTypes() {
            return new TypeList.Generic.Explicit((List<? extends TypeDefinition>) this.exceptionTypes);
        }

        public AnnotationList getAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }

        public AnnotationValue<?, ?> getDefaultValue() {
            return this.defaultValue;
        }

        public TypeDescription.Generic getReceiverType() {
            return this.receiverType;
        }

        public Token accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            TypeDescription.Generic generic;
            String str = this.name;
            int i = this.modifiers;
            ByteCodeElement.Token.TokenList<TypeVariableToken> accept = getTypeVariableTokens().accept(visitor);
            TypeDescription.Generic generic2 = (TypeDescription.Generic) this.returnType.accept(visitor);
            ByteCodeElement.Token.TokenList<ParameterDescription.Token> accept2 = getParameterTokens().accept(visitor);
            TypeList.Generic accept3 = getExceptionTypes().accept(visitor);
            List<? extends AnnotationDescription> list = this.annotations;
            AnnotationValue<?, ?> annotationValue = this.defaultValue;
            TypeDescription.Generic generic3 = this.receiverType;
            if (generic3 == null) {
                generic = TypeDescription.Generic.UNDEFINED;
            } else {
                generic = (TypeDescription.Generic) generic3.accept(visitor);
            }
            return new Token(str, i, accept, generic2, accept2, accept3, list, annotationValue, generic);
        }

        public SignatureToken asSignatureToken(TypeDescription typeDescription) {
            TypeDescription.Generic.Visitor.Reducing reducing = new TypeDescription.Generic.Visitor.Reducing(typeDescription, this.typeVariableTokens);
            ArrayList arrayList = new ArrayList(this.parameterTokens.size());
            for (ParameterDescription.Token type : this.parameterTokens) {
                arrayList.add(type.getType().accept(reducing));
            }
            return new SignatureToken(this.name, (TypeDescription) this.returnType.accept(reducing), arrayList);
        }

        public int hashCode() {
            int hashCode = ((((((((((((this.name.hashCode() * 31) + this.modifiers) * 31) + this.typeVariableTokens.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTokens.hashCode()) * 31) + this.exceptionTypes.hashCode()) * 31) + this.annotations.hashCode()) * 31;
            AnnotationValue<?, ?> annotationValue = this.defaultValue;
            int i = 0;
            int hashCode2 = (hashCode + (annotationValue != null ? annotationValue.hashCode() : 0)) * 31;
            TypeDescription.Generic generic = this.receiverType;
            if (generic != null) {
                i = generic.hashCode();
            }
            return hashCode2 + i;
        }

        public boolean equals(Object obj) {
            AnnotationValue<?, ?> annotationValue;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Token token = (Token) obj;
            if (this.modifiers == token.modifiers && this.name.equals(token.name) && this.typeVariableTokens.equals(token.typeVariableTokens) && this.returnType.equals(token.returnType) && this.parameterTokens.equals(token.parameterTokens) && this.exceptionTypes.equals(token.exceptionTypes) && this.annotations.equals(token.annotations) && ((annotationValue = this.defaultValue) == null ? token.defaultValue == null : annotationValue.equals(token.defaultValue))) {
                TypeDescription.Generic generic = this.receiverType;
                if (generic != null) {
                    if (generic.equals(token.receiverType)) {
                        return true;
                    }
                } else if (token.receiverType == null) {
                    return true;
                }
            }
            return false;
        }

        public String toString() {
            return "MethodDescription.Token{name='" + this.name + '\'' + ", modifiers=" + this.modifiers + ", typeVariableTokens=" + this.typeVariableTokens + ", returnType=" + this.returnType + ", parameterTokens=" + this.parameterTokens + ", exceptionTypes=" + this.exceptionTypes + ", annotations=" + this.annotations + ", defaultValue=" + this.defaultValue + ", receiverType=" + this.receiverType + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
        }
    }

    public static class SignatureToken {
        private final String name;
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        public SignatureToken(String str, TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.name = str;
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public List<TypeDescription> getParameterTypes() {
            return this.parameterTypes;
        }

        public TypeToken asTypeToken() {
            return new TypeToken(this.returnType, this.parameterTypes);
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureToken)) {
                return false;
            }
            SignatureToken signatureToken = (SignatureToken) obj;
            if (!this.name.equals(signatureToken.name) || !this.returnType.equals(signatureToken.returnType) || !this.parameterTypes.equals(signatureToken.parameterTypes)) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.returnType);
            sb.append(' ');
            sb.append(this.name);
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            boolean z = true;
            for (TypeDescription typeDescription : this.parameterTypes) {
                if (z) {
                    z = false;
                } else {
                    sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                }
                sb.append(typeDescription);
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            return sb.toString();
        }
    }

    public static class TypeToken {
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        public TypeToken(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public List<TypeDescription> getParameterTypes() {
            return this.parameterTypes;
        }

        public int hashCode() {
            return (this.returnType.hashCode() * 31) + this.parameterTypes.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TypeToken)) {
                return false;
            }
            TypeToken typeToken = (TypeToken) obj;
            if (!this.returnType.equals(typeToken.returnType) || !this.parameterTypes.equals(typeToken.parameterTypes)) {
                return false;
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            for (TypeDescription descriptor : this.parameterTypes) {
                sb.append(descriptor.getDescriptor());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            sb.append(this.returnType.getDescriptor());
            return sb.toString();
        }
    }
}

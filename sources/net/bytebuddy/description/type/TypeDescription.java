package net.bytebuddy.description.type;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.TypeVariableSource;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationSource;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;
import okhttp3.HttpUrl;

public interface TypeDescription extends TypeDefinition, ByteCodeElement, TypeVariableSource {
    public static final TypeList.Generic ARRAY_INTERFACES = new TypeList.Generic.ForLoadedTypes(Cloneable.class, Serializable.class);
    public static final TypeDescription CLASS = new ForLoadedType(Class.class);
    public static final TypeDescription OBJECT = new ForLoadedType(Object.class);
    public static final TypeDescription STRING = new ForLoadedType(String.class);
    public static final TypeDescription THROWABLE = new ForLoadedType(Throwable.class);
    public static final TypeDescription UNDEFINED = null;
    public static final TypeDescription VOID = new ForLoadedType(Void.TYPE);

    TypeDescription asBoxed();

    TypeDescription asUnboxed();

    int getActualModifiers(boolean z);

    String getCanonicalName();

    TypeDescription getComponentType();

    FieldList<FieldDescription.InDefinedShape> getDeclaredFields();

    MethodList<MethodDescription.InDefinedShape> getDeclaredMethods();

    TypeList getDeclaredTypes();

    TypeDescription getDeclaringType();

    Object getDefaultValue();

    MethodDescription.InDefinedShape getEnclosingMethod();

    TypeDescription getEnclosingType();

    AnnotationList getInheritedAnnotations();

    int getInnerClassCount();

    TypeDescription getNestHost();

    TypeList getNestMembers();

    PackageDescription getPackage();

    String getSimpleName();

    boolean isAnnotationReturnType();

    boolean isAnnotationValue();

    boolean isAnnotationValue(Object obj);

    boolean isAnonymousType();

    boolean isAssignableFrom(Class<?> cls);

    boolean isAssignableFrom(TypeDescription typeDescription);

    boolean isAssignableTo(Class<?> cls);

    boolean isAssignableTo(TypeDescription typeDescription);

    boolean isInHierarchyWith(Class<?> cls);

    boolean isInHierarchyWith(TypeDescription typeDescription);

    boolean isInnerClass();

    boolean isInstance(Object obj);

    boolean isLocalType();

    boolean isMemberType();

    boolean isNestHost();

    boolean isNestMateOf(Class<?> cls);

    boolean isNestMateOf(TypeDescription typeDescription);

    boolean isNestedClass();

    boolean isPackageType();

    boolean isPrimitiveWrapper();

    boolean isSamePackage(TypeDescription typeDescription);

    public interface Generic extends TypeDefinition, AnnotationSource {
        public static final Generic ANNOTATION = new OfNonGenericType.ForLoadedType(Annotation.class);
        public static final Generic CLASS = new OfNonGenericType.ForLoadedType(Class.class);
        public static final Generic OBJECT = new OfNonGenericType.ForLoadedType(Object.class);
        public static final Generic UNDEFINED = null;
        public static final Generic VOID = new OfNonGenericType.ForLoadedType(Void.TYPE);

        <T> T accept(Visitor<T> visitor);

        Generic asRawType();

        Generic findBindingOf(Generic generic);

        Generic getComponentType();

        FieldList<FieldDescription.InGenericShape> getDeclaredFields();

        MethodList<MethodDescription.InGenericShape> getDeclaredMethods();

        TypeList.Generic getLowerBounds();

        Generic getOwnerType();

        String getSymbol();

        TypeList.Generic getTypeArguments();

        TypeVariableSource getTypeVariableSource();

        TypeList.Generic getUpperBounds();

        public interface Visitor<T> {

            public enum NoOp implements Visitor<Generic> {
                INSTANCE;

                public Generic onGenericArray(Generic generic) {
                    return generic;
                }

                public Generic onNonGenericType(Generic generic) {
                    return generic;
                }

                public Generic onParameterizedType(Generic generic) {
                    return generic;
                }

                public Generic onTypeVariable(Generic generic) {
                    return generic;
                }

                public Generic onWildcard(Generic generic) {
                    return generic;
                }
            }

            T onGenericArray(Generic generic);

            T onNonGenericType(Generic generic);

            T onParameterizedType(Generic generic);

            T onTypeVariable(Generic generic);

            T onWildcard(Generic generic);

            public enum TypeErasing implements Visitor<Generic> {
                INSTANCE;

                public Generic onGenericArray(Generic generic) {
                    return generic.asRawType();
                }

                public Generic onWildcard(Generic generic) {
                    throw new IllegalArgumentException("Cannot erase a wildcard type: " + generic);
                }

                public Generic onParameterizedType(Generic generic) {
                    return generic.asRawType();
                }

                public Generic onTypeVariable(Generic generic) {
                    return generic.asRawType();
                }

                public Generic onNonGenericType(Generic generic) {
                    return generic.asRawType();
                }
            }

            public enum AnnotationStripper implements Visitor<Generic> {
                INSTANCE;

                public Generic onGenericArray(Generic generic) {
                    return new OfGenericArray.Latent((Generic) generic.getComponentType().accept(this), AnnotationSource.Empty.INSTANCE);
                }

                public Generic onWildcard(Generic generic) {
                    return new OfWildcardType.Latent(generic.getUpperBounds().accept(this), generic.getLowerBounds().accept(this), AnnotationSource.Empty.INSTANCE);
                }

                public Generic onParameterizedType(Generic generic) {
                    Generic generic2;
                    Generic ownerType = generic.getOwnerType();
                    TypeDescription asErasure = generic.asErasure();
                    if (ownerType == null) {
                        generic2 = Generic.UNDEFINED;
                    } else {
                        generic2 = (Generic) ownerType.accept(this);
                    }
                    return new OfParameterizedType.Latent(asErasure, generic2, generic.getTypeArguments().accept(this), AnnotationSource.Empty.INSTANCE);
                }

                public Generic onTypeVariable(Generic generic) {
                    return new NonAnnotatedTypeVariable(generic);
                }

                public Generic onNonGenericType(Generic generic) {
                    if (generic.isArray()) {
                        return new OfGenericArray.Latent(onNonGenericType(generic.getComponentType()), AnnotationSource.Empty.INSTANCE);
                    }
                    return new OfNonGenericType.Latent(generic.asErasure(), AnnotationSource.Empty.INSTANCE);
                }

                protected static class NonAnnotatedTypeVariable extends OfTypeVariable {
                    private final Generic typeVariable;

                    protected NonAnnotatedTypeVariable(Generic generic) {
                        this.typeVariable = generic;
                    }

                    public TypeList.Generic getUpperBounds() {
                        return this.typeVariable.getUpperBounds();
                    }

                    public TypeVariableSource getTypeVariableSource() {
                        return this.typeVariable.getTypeVariableSource();
                    }

                    public String getSymbol() {
                        return this.typeVariable.getSymbol();
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return new AnnotationList.Empty();
                    }
                }
            }

            public enum Assigner implements Visitor<Dispatcher> {
                INSTANCE;

                public Dispatcher onGenericArray(Generic generic) {
                    return new Dispatcher.ForGenericArray(generic);
                }

                public Dispatcher onWildcard(Generic generic) {
                    throw new IllegalArgumentException("A wildcard is not a first level type: " + this);
                }

                public Dispatcher onParameterizedType(Generic generic) {
                    return new Dispatcher.ForParameterizedType(generic);
                }

                public Dispatcher onTypeVariable(Generic generic) {
                    return new Dispatcher.ForTypeVariable(generic);
                }

                public Dispatcher onNonGenericType(Generic generic) {
                    return new Dispatcher.ForNonGenericType(generic.asErasure());
                }

                public interface Dispatcher {
                    boolean isAssignableFrom(Generic generic);

                    public static abstract class AbstractBase implements Dispatcher, Visitor<Boolean> {
                        public boolean isAssignableFrom(Generic generic) {
                            return ((Boolean) generic.accept(this)).booleanValue();
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class ForNonGenericType extends AbstractBase {
                        private final TypeDescription typeDescription;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForNonGenericType) obj).typeDescription);
                        }

                        public int hashCode() {
                            return 527 + this.typeDescription.hashCode();
                        }

                        protected ForNonGenericType(TypeDescription typeDescription2) {
                            this.typeDescription = typeDescription2;
                        }

                        public Boolean onGenericArray(Generic generic) {
                            boolean z;
                            if (this.typeDescription.isArray()) {
                                z = ((Boolean) generic.getComponentType().accept(new ForNonGenericType(this.typeDescription.getComponentType()))).booleanValue();
                            } else {
                                z = this.typeDescription.represents(Object.class) || TypeDescription.ARRAY_INTERFACES.contains(this.typeDescription.asGenericType());
                            }
                            return Boolean.valueOf(z);
                        }

                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        public Boolean onParameterizedType(Generic generic) {
                            if (this.typeDescription.equals(generic.asErasure())) {
                                return true;
                            }
                            Generic superClass = generic.getSuperClass();
                            if (superClass != null && isAssignableFrom(superClass)) {
                                return true;
                            }
                            for (Generic isAssignableFrom : generic.getInterfaces()) {
                                if (isAssignableFrom(isAssignableFrom)) {
                                    return true;
                                }
                            }
                            return Boolean.valueOf(this.typeDescription.represents(Object.class));
                        }

                        public Boolean onTypeVariable(Generic generic) {
                            for (Generic isAssignableFrom : generic.getUpperBounds()) {
                                if (isAssignableFrom(isAssignableFrom)) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        public Boolean onNonGenericType(Generic generic) {
                            return Boolean.valueOf(this.typeDescription.isAssignableFrom(generic.asErasure()));
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class ForTypeVariable extends AbstractBase {
                        private final Generic typeVariable;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.typeVariable.equals(((ForTypeVariable) obj).typeVariable);
                        }

                        public int hashCode() {
                            return 527 + this.typeVariable.hashCode();
                        }

                        protected ForTypeVariable(Generic generic) {
                            this.typeVariable = generic;
                        }

                        public Boolean onGenericArray(Generic generic) {
                            return false;
                        }

                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        public Boolean onParameterizedType(Generic generic) {
                            return false;
                        }

                        public Boolean onTypeVariable(Generic generic) {
                            if (generic.equals(this.typeVariable)) {
                                return true;
                            }
                            for (Generic isAssignableFrom : generic.getUpperBounds()) {
                                if (isAssignableFrom(isAssignableFrom)) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        public Boolean onNonGenericType(Generic generic) {
                            return false;
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class ForParameterizedType extends AbstractBase {
                        private final Generic parameterizedType;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.parameterizedType.equals(((ForParameterizedType) obj).parameterizedType);
                        }

                        public int hashCode() {
                            return 527 + this.parameterizedType.hashCode();
                        }

                        protected ForParameterizedType(Generic generic) {
                            this.parameterizedType = generic;
                        }

                        public Boolean onGenericArray(Generic generic) {
                            return false;
                        }

                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        public Boolean onParameterizedType(Generic generic) {
                            if (this.parameterizedType.asErasure().equals(generic.asErasure())) {
                                Generic ownerType = this.parameterizedType.getOwnerType();
                                Generic ownerType2 = generic.getOwnerType();
                                if (ownerType != null && ownerType2 != null && !((Dispatcher) ownerType.accept(Assigner.INSTANCE)).isAssignableFrom(ownerType2)) {
                                    return false;
                                }
                                TypeList.Generic typeArguments = this.parameterizedType.getTypeArguments();
                                TypeList.Generic typeArguments2 = generic.getTypeArguments();
                                if (typeArguments.size() == typeArguments2.size()) {
                                    for (int i = 0; i < typeArguments.size(); i++) {
                                        if (!((Dispatcher) ((Generic) typeArguments.get(i)).accept(ParameterAssigner.INSTANCE)).isAssignableFrom((Generic) typeArguments2.get(i))) {
                                            return false;
                                        }
                                    }
                                    return true;
                                }
                                throw new IllegalArgumentException("Incompatible generic types: " + generic + " and " + this.parameterizedType);
                            }
                            Generic superClass = generic.getSuperClass();
                            if (superClass != null && isAssignableFrom(superClass)) {
                                return true;
                            }
                            for (Generic isAssignableFrom : generic.getInterfaces()) {
                                if (isAssignableFrom(isAssignableFrom)) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        public Boolean onTypeVariable(Generic generic) {
                            for (Generic isAssignableFrom : generic.getUpperBounds()) {
                                if (isAssignableFrom(isAssignableFrom)) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        public Boolean onNonGenericType(Generic generic) {
                            if (this.parameterizedType.asErasure().equals(generic.asErasure())) {
                                return true;
                            }
                            Generic superClass = generic.getSuperClass();
                            if (superClass != null && isAssignableFrom(superClass)) {
                                return true;
                            }
                            for (Generic isAssignableFrom : generic.getInterfaces()) {
                                if (isAssignableFrom(isAssignableFrom)) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        protected enum ParameterAssigner implements Visitor<Dispatcher> {
                            INSTANCE;

                            public Dispatcher onGenericArray(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            public Dispatcher onWildcard(Generic generic) {
                                TypeList.Generic lowerBounds = generic.getLowerBounds();
                                if (lowerBounds.isEmpty()) {
                                    return new CovariantBinding((Generic) generic.getUpperBounds().getOnly());
                                }
                                return new ContravariantBinding((Generic) lowerBounds.getOnly());
                            }

                            public Dispatcher onParameterizedType(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            public Dispatcher onTypeVariable(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            public Dispatcher onNonGenericType(Generic generic) {
                                return new InvariantBinding(generic);
                            }

                            @HashCodeAndEqualsPlugin.Enhance
                            protected static class InvariantBinding implements Dispatcher {
                                private final Generic typeDescription;

                                public boolean equals(Object obj) {
                                    if (this == obj) {
                                        return true;
                                    }
                                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((InvariantBinding) obj).typeDescription);
                                }

                                public int hashCode() {
                                    return 527 + this.typeDescription.hashCode();
                                }

                                protected InvariantBinding(Generic generic) {
                                    this.typeDescription = generic;
                                }

                                public boolean isAssignableFrom(Generic generic) {
                                    return generic.equals(this.typeDescription);
                                }
                            }

                            @HashCodeAndEqualsPlugin.Enhance
                            protected static class CovariantBinding implements Dispatcher {
                                private final Generic upperBound;

                                public boolean equals(Object obj) {
                                    if (this == obj) {
                                        return true;
                                    }
                                    return obj != null && getClass() == obj.getClass() && this.upperBound.equals(((CovariantBinding) obj).upperBound);
                                }

                                public int hashCode() {
                                    return 527 + this.upperBound.hashCode();
                                }

                                protected CovariantBinding(Generic generic) {
                                    this.upperBound = generic;
                                }

                                public boolean isAssignableFrom(Generic generic) {
                                    if (generic.getSort().isWildcard()) {
                                        return generic.getLowerBounds().isEmpty() && ((Dispatcher) this.upperBound.accept(Assigner.INSTANCE)).isAssignableFrom((Generic) generic.getUpperBounds().getOnly());
                                    }
                                    return ((Dispatcher) this.upperBound.accept(Assigner.INSTANCE)).isAssignableFrom(generic);
                                }
                            }

                            @HashCodeAndEqualsPlugin.Enhance
                            protected static class ContravariantBinding implements Dispatcher {
                                private final Generic lowerBound;

                                public boolean equals(Object obj) {
                                    if (this == obj) {
                                        return true;
                                    }
                                    return obj != null && getClass() == obj.getClass() && this.lowerBound.equals(((ContravariantBinding) obj).lowerBound);
                                }

                                public int hashCode() {
                                    return 527 + this.lowerBound.hashCode();
                                }

                                protected ContravariantBinding(Generic generic) {
                                    this.lowerBound = generic;
                                }

                                public boolean isAssignableFrom(Generic generic) {
                                    if (generic.getSort().isWildcard()) {
                                        TypeList.Generic lowerBounds = generic.getLowerBounds();
                                        if (lowerBounds.isEmpty() || !((Dispatcher) ((Generic) lowerBounds.getOnly()).accept(Assigner.INSTANCE)).isAssignableFrom(this.lowerBound)) {
                                            return false;
                                        }
                                        return true;
                                    } else if (generic.getSort().isWildcard() || ((Dispatcher) generic.accept(Assigner.INSTANCE)).isAssignableFrom(this.lowerBound)) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    public static class ForGenericArray extends AbstractBase {
                        private final Generic genericArray;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.genericArray.equals(((ForGenericArray) obj).genericArray);
                        }

                        public int hashCode() {
                            return 527 + this.genericArray.hashCode();
                        }

                        protected ForGenericArray(Generic generic) {
                            this.genericArray = generic;
                        }

                        public Boolean onGenericArray(Generic generic) {
                            return Boolean.valueOf(((Dispatcher) this.genericArray.getComponentType().accept(Assigner.INSTANCE)).isAssignableFrom(generic.getComponentType()));
                        }

                        public Boolean onWildcard(Generic generic) {
                            throw new IllegalArgumentException("A wildcard is not a first-level type: " + generic);
                        }

                        public Boolean onParameterizedType(Generic generic) {
                            return false;
                        }

                        public Boolean onTypeVariable(Generic generic) {
                            return false;
                        }

                        public Boolean onNonGenericType(Generic generic) {
                            return Boolean.valueOf(generic.isArray() && ((Dispatcher) this.genericArray.getComponentType().accept(Assigner.INSTANCE)).isAssignableFrom(generic.getComponentType()));
                        }
                    }
                }
            }

            public enum Validator implements Visitor<Boolean> {
                SUPER_CLASS(false, false, false, false) {
                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(super.onNonGenericType(generic).booleanValue() && !generic.isInterface());
                    }

                    public Boolean onParameterizedType(Generic generic) {
                        return Boolean.valueOf(!generic.isInterface());
                    }
                },
                INTERFACE(false, false, false, false) {
                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(super.onNonGenericType(generic).booleanValue() && generic.isInterface());
                    }

                    public Boolean onParameterizedType(Generic generic) {
                        return Boolean.valueOf(generic.isInterface());
                    }
                },
                TYPE_VARIABLE(false, false, true, false),
                FIELD(true, true, true, false),
                METHOD_RETURN(true, true, true, true),
                METHOD_PARAMETER(true, true, true, false),
                EXCEPTION(false, false, true, false) {
                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public Boolean onParameterizedType(Generic generic) {
                        return false;
                    }

                    public Boolean onTypeVariable(Generic generic) {
                        for (Generic accept : generic.getUpperBounds()) {
                            if (((Boolean) accept.accept(this)).booleanValue()) {
                                return true;
                            }
                        }
                        return false;
                    }

                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(generic.asErasure().isAssignableTo((Class<?>) Throwable.class));
                    }
                },
                RECEIVER(false, false, false, false);
                
                private final boolean acceptsArray;
                private final boolean acceptsPrimitive;
                private final boolean acceptsVariable;
                private final boolean acceptsVoid;

                private Validator(boolean z, boolean z2, boolean z3, boolean z4) {
                    this.acceptsArray = z;
                    this.acceptsPrimitive = z2;
                    this.acceptsVariable = z3;
                    this.acceptsVoid = z4;
                }

                public Boolean onGenericArray(Generic generic) {
                    return Boolean.valueOf(this.acceptsArray);
                }

                public Boolean onWildcard(Generic generic) {
                    return false;
                }

                public Boolean onParameterizedType(Generic generic) {
                    return true;
                }

                public Boolean onTypeVariable(Generic generic) {
                    return Boolean.valueOf(this.acceptsVariable);
                }

                public Boolean onNonGenericType(Generic generic) {
                    return Boolean.valueOf((this.acceptsArray || !generic.isArray()) && (this.acceptsPrimitive || !generic.isPrimitive()) && (this.acceptsVoid || !generic.represents(Void.TYPE)));
                }

                public enum ForTypeAnnotations implements Visitor<Boolean> {
                    INSTANCE;
                    
                    private final ElementType typeParameter;
                    private final ElementType typeUse;

                    /* JADX WARNING: Removed duplicated region for block: B:3:0x0013  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public static boolean ofFormalTypeVariable(net.bytebuddy.description.type.TypeDescription.Generic r4) {
                        /*
                            java.util.HashSet r0 = new java.util.HashSet
                            r0.<init>()
                            net.bytebuddy.description.annotation.AnnotationList r4 = r4.getDeclaredAnnotations()
                            java.util.Iterator r4 = r4.iterator()
                        L_0x000d:
                            boolean r1 = r4.hasNext()
                            if (r1 == 0) goto L_0x0033
                            java.lang.Object r1 = r4.next()
                            net.bytebuddy.description.annotation.AnnotationDescription r1 = (net.bytebuddy.description.annotation.AnnotationDescription) r1
                            java.util.Set r2 = r1.getElementTypes()
                            net.bytebuddy.description.type.TypeDescription$Generic$Visitor$Validator$ForTypeAnnotations r3 = INSTANCE
                            java.lang.annotation.ElementType r3 = r3.typeParameter
                            boolean r2 = r2.contains(r3)
                            if (r2 == 0) goto L_0x0031
                            net.bytebuddy.description.type.TypeDescription r1 = r1.getAnnotationType()
                            boolean r1 = r0.add(r1)
                            if (r1 != 0) goto L_0x000d
                        L_0x0031:
                            r4 = 0
                            return r4
                        L_0x0033:
                            r4 = 1
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.ofFormalTypeVariable(net.bytebuddy.description.type.TypeDescription$Generic):boolean");
                    }

                    public Boolean onGenericArray(Generic generic) {
                        return Boolean.valueOf(isValid(generic) && ((Boolean) generic.getComponentType().accept(this)).booleanValue());
                    }

                    public Boolean onWildcard(Generic generic) {
                        if (!isValid(generic)) {
                            return false;
                        }
                        TypeList.Generic lowerBounds = generic.getLowerBounds();
                        if (lowerBounds.isEmpty()) {
                            lowerBounds = generic.getUpperBounds();
                        }
                        return (Boolean) ((Generic) lowerBounds.getOnly()).accept(this);
                    }

                    public Boolean onParameterizedType(Generic generic) {
                        if (!isValid(generic)) {
                            return false;
                        }
                        Generic ownerType = generic.getOwnerType();
                        if (ownerType != null && !((Boolean) ownerType.accept(this)).booleanValue()) {
                            return false;
                        }
                        for (Generic accept : generic.getTypeArguments()) {
                            if (!((Boolean) accept.accept(this)).booleanValue()) {
                                return false;
                            }
                        }
                        return true;
                    }

                    public Boolean onTypeVariable(Generic generic) {
                        return Boolean.valueOf(isValid(generic));
                    }

                    public Boolean onNonGenericType(Generic generic) {
                        return Boolean.valueOf(isValid(generic) && (!generic.isArray() || ((Boolean) generic.getComponentType().accept(this)).booleanValue()));
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:3:0x0013  */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    private boolean isValid(net.bytebuddy.description.type.TypeDescription.Generic r5) {
                        /*
                            r4 = this;
                            java.util.HashSet r0 = new java.util.HashSet
                            r0.<init>()
                            net.bytebuddy.description.annotation.AnnotationList r5 = r5.getDeclaredAnnotations()
                            java.util.Iterator r5 = r5.iterator()
                        L_0x000d:
                            boolean r1 = r5.hasNext()
                            if (r1 == 0) goto L_0x0031
                            java.lang.Object r1 = r5.next()
                            net.bytebuddy.description.annotation.AnnotationDescription r1 = (net.bytebuddy.description.annotation.AnnotationDescription) r1
                            java.util.Set r2 = r1.getElementTypes()
                            java.lang.annotation.ElementType r3 = r4.typeUse
                            boolean r2 = r2.contains(r3)
                            if (r2 == 0) goto L_0x002f
                            net.bytebuddy.description.type.TypeDescription r1 = r1.getAnnotationType()
                            boolean r1 = r0.add(r1)
                            if (r1 != 0) goto L_0x000d
                        L_0x002f:
                            r5 = 0
                            return r5
                        L_0x0031:
                            r5 = 1
                            return r5
                        */
                        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.isValid(net.bytebuddy.description.type.TypeDescription$Generic):boolean");
                    }
                }
            }

            public enum Reifying implements Visitor<Generic> {
                INITIATING {
                    public Generic onParameterizedType(Generic generic) {
                        return generic;
                    }

                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }
                },
                INHERITING {
                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onTypeVariable(Generic generic) {
                        return super.onTypeVariable(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public Generic onParameterizedType(Generic generic) {
                        return new OfParameterizedType.ForReifiedType(generic);
                    }
                };

                public Generic onGenericArray(Generic generic) {
                    throw new IllegalArgumentException("Cannot reify a generic array: " + generic);
                }

                public Generic onWildcard(Generic generic) {
                    throw new IllegalArgumentException("Cannot reify a wildcard: " + generic);
                }

                public Generic onTypeVariable(Generic generic) {
                    throw new IllegalArgumentException("Cannot reify a type variable: " + generic);
                }

                public Generic onNonGenericType(Generic generic) {
                    TypeDescription asErasure = generic.asErasure();
                    return asErasure.isGenerified() ? new OfNonGenericType.ForReifiedErasure(asErasure) : generic;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForSignatureVisitor implements Visitor<SignatureVisitor> {
                private static final int ONLY_CHARACTER = 0;
                protected final SignatureVisitor signatureVisitor;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.signatureVisitor.equals(((ForSignatureVisitor) obj).signatureVisitor);
                }

                public int hashCode() {
                    return 527 + this.signatureVisitor.hashCode();
                }

                public ForSignatureVisitor(SignatureVisitor signatureVisitor2) {
                    this.signatureVisitor = signatureVisitor2;
                }

                public SignatureVisitor onGenericArray(Generic generic) {
                    generic.getComponentType().accept(new ForSignatureVisitor(this.signatureVisitor.visitArrayType()));
                    return this.signatureVisitor;
                }

                public SignatureVisitor onWildcard(Generic generic) {
                    throw new IllegalStateException("Unexpected wildcard: " + generic);
                }

                public SignatureVisitor onParameterizedType(Generic generic) {
                    onOwnableType(generic);
                    this.signatureVisitor.visitEnd();
                    return this.signatureVisitor;
                }

                private void onOwnableType(Generic generic) {
                    Generic ownerType = generic.getOwnerType();
                    if (ownerType == null || !ownerType.getSort().isParameterized()) {
                        this.signatureVisitor.visitClassType(generic.asErasure().getInternalName());
                    } else {
                        onOwnableType(ownerType);
                        this.signatureVisitor.visitInnerClassType(generic.asErasure().getSimpleName());
                    }
                    for (Generic accept : generic.getTypeArguments()) {
                        accept.accept(new OfTypeArgument(this.signatureVisitor));
                    }
                }

                public SignatureVisitor onTypeVariable(Generic generic) {
                    this.signatureVisitor.visitTypeVariable(generic.getSymbol());
                    return this.signatureVisitor;
                }

                public SignatureVisitor onNonGenericType(Generic generic) {
                    if (generic.isArray()) {
                        generic.getComponentType().accept(new ForSignatureVisitor(this.signatureVisitor.visitArrayType()));
                    } else if (generic.isPrimitive()) {
                        this.signatureVisitor.visitBaseType(generic.asErasure().getDescriptor().charAt(0));
                    } else {
                        this.signatureVisitor.visitClassType(generic.asErasure().getInternalName());
                        this.signatureVisitor.visitEnd();
                    }
                    return this.signatureVisitor;
                }

                protected static class OfTypeArgument extends ForSignatureVisitor {
                    protected OfTypeArgument(SignatureVisitor signatureVisitor) {
                        super(signatureVisitor);
                    }

                    public SignatureVisitor onWildcard(Generic generic) {
                        TypeList.Generic upperBounds = generic.getUpperBounds();
                        TypeList.Generic lowerBounds = generic.getLowerBounds();
                        if (lowerBounds.isEmpty() && ((Generic) upperBounds.getOnly()).represents(Object.class)) {
                            this.signatureVisitor.visitTypeArgument();
                        } else if (!lowerBounds.isEmpty()) {
                            ((Generic) lowerBounds.getOnly()).accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument('-')));
                        } else {
                            ((Generic) upperBounds.getOnly()).accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument(SignatureVisitor.EXTENDS)));
                        }
                        return this.signatureVisitor;
                    }

                    public SignatureVisitor onGenericArray(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument('=')));
                        return this.signatureVisitor;
                    }

                    public SignatureVisitor onParameterizedType(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument('=')));
                        return this.signatureVisitor;
                    }

                    public SignatureVisitor onTypeVariable(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument('=')));
                        return this.signatureVisitor;
                    }

                    public SignatureVisitor onNonGenericType(Generic generic) {
                        generic.accept(new ForSignatureVisitor(this.signatureVisitor.visitTypeArgument('=')));
                        return this.signatureVisitor;
                    }
                }
            }

            public static abstract class Substitutor implements Visitor<Generic> {
                /* access modifiers changed from: protected */
                public abstract Generic onSimpleType(Generic generic);

                public Generic onParameterizedType(Generic generic) {
                    Generic generic2;
                    Generic ownerType = generic.getOwnerType();
                    ArrayList arrayList = new ArrayList(generic.getTypeArguments().size());
                    for (Generic accept : generic.getTypeArguments()) {
                        arrayList.add(accept.accept(this));
                    }
                    TypeDescription asErasure = ((Generic) generic.asRawType().accept(this)).asErasure();
                    if (ownerType == null) {
                        generic2 = Generic.UNDEFINED;
                    } else {
                        generic2 = (Generic) ownerType.accept(this);
                    }
                    return new OfParameterizedType.Latent(asErasure, generic2, arrayList, generic);
                }

                public Generic onGenericArray(Generic generic) {
                    return new OfGenericArray.Latent((Generic) generic.getComponentType().accept(this), generic);
                }

                public Generic onWildcard(Generic generic) {
                    return new OfWildcardType.Latent(generic.getUpperBounds().accept(this), generic.getLowerBounds().accept(this), generic);
                }

                public Generic onNonGenericType(Generic generic) {
                    if (generic.isArray()) {
                        return new OfGenericArray.Latent((Generic) generic.getComponentType().accept(this), generic);
                    }
                    return onSimpleType(generic);
                }

                public static abstract class WithoutTypeSubstitution extends Substitutor {
                    public Generic onNonGenericType(Generic generic) {
                        return generic;
                    }

                    /* access modifiers changed from: protected */
                    public Generic onSimpleType(Generic generic) {
                        return generic;
                    }

                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForAttachment extends Substitutor {
                    private final TypeDescription declaringType;
                    private final TypeVariableSource typeVariableSource;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForAttachment forAttachment = (ForAttachment) obj;
                        return this.declaringType.equals(forAttachment.declaringType) && this.typeVariableSource.equals(forAttachment.typeVariableSource);
                    }

                    public int hashCode() {
                        return ((527 + this.declaringType.hashCode()) * 31) + this.typeVariableSource.hashCode();
                    }

                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    protected ForAttachment(TypeDefinition typeDefinition, TypeVariableSource typeVariableSource2) {
                        this(typeDefinition.asErasure(), typeVariableSource2);
                    }

                    protected ForAttachment(TypeDescription typeDescription, TypeVariableSource typeVariableSource2) {
                        this.declaringType = typeDescription;
                        this.typeVariableSource = typeVariableSource2;
                    }

                    public static ForAttachment of(FieldDescription fieldDescription) {
                        return new ForAttachment(fieldDescription.getDeclaringType(), (TypeVariableSource) fieldDescription.getDeclaringType().asErasure());
                    }

                    public static ForAttachment of(MethodDescription methodDescription) {
                        return new ForAttachment(methodDescription.getDeclaringType(), (TypeVariableSource) methodDescription);
                    }

                    public static ForAttachment of(ParameterDescription parameterDescription) {
                        return new ForAttachment(parameterDescription.getDeclaringMethod().getDeclaringType(), (TypeVariableSource) parameterDescription.getDeclaringMethod());
                    }

                    public static ForAttachment of(TypeDescription typeDescription) {
                        return new ForAttachment(typeDescription, (TypeVariableSource) typeDescription);
                    }

                    public Generic onTypeVariable(Generic generic) {
                        Generic findVariable = this.typeVariableSource.findVariable(generic.getSymbol());
                        if (findVariable != null) {
                            return new OfTypeVariable.WithAnnotationOverlay(findVariable, generic);
                        }
                        throw new IllegalArgumentException("Cannot attach undefined variable: " + generic);
                    }

                    /* access modifiers changed from: protected */
                    public Generic onSimpleType(Generic generic) {
                        return generic.represents(TargetType.class) ? new OfNonGenericType.Latent(this.declaringType, generic) : generic;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForDetachment extends Substitutor {
                    private final ElementMatcher<? super TypeDescription> typeMatcher;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.typeMatcher.equals(((ForDetachment) obj).typeMatcher);
                    }

                    public int hashCode() {
                        return 527 + this.typeMatcher.hashCode();
                    }

                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public ForDetachment(ElementMatcher<? super TypeDescription> elementMatcher) {
                        this.typeMatcher = elementMatcher;
                    }

                    public static Visitor<Generic> of(TypeDefinition typeDefinition) {
                        return new ForDetachment(ElementMatchers.is((Object) typeDefinition));
                    }

                    public Generic onTypeVariable(Generic generic) {
                        return new OfTypeVariable.Symbolic(generic.getSymbol(), generic);
                    }

                    /* access modifiers changed from: protected */
                    public Generic onSimpleType(Generic generic) {
                        return this.typeMatcher.matches(generic.asErasure()) ? new OfNonGenericType.Latent(TargetType.DESCRIPTION, generic.getOwnerType(), (AnnotationSource) generic) : generic;
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForTypeVariableBinding extends WithoutTypeSubstitution {
                    /* access modifiers changed from: private */
                    public final Generic parameterizedType;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.parameterizedType.equals(((ForTypeVariableBinding) obj).parameterizedType);
                    }

                    public int hashCode() {
                        return 527 + this.parameterizedType.hashCode();
                    }

                    protected ForTypeVariableBinding(Generic generic) {
                        this.parameterizedType = generic;
                    }

                    public Generic onTypeVariable(Generic generic) {
                        return (Generic) generic.getTypeVariableSource().accept(new TypeVariableSubstitutor(generic));
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class TypeVariableSubstitutor implements TypeVariableSource.Visitor<Generic> {
                        private final Generic typeVariable;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            TypeVariableSubstitutor typeVariableSubstitutor = (TypeVariableSubstitutor) obj;
                            return this.typeVariable.equals(typeVariableSubstitutor.typeVariable) && ForTypeVariableBinding.this.equals(ForTypeVariableBinding.this);
                        }

                        public int hashCode() {
                            return ((527 + this.typeVariable.hashCode()) * 31) + ForTypeVariableBinding.this.hashCode();
                        }

                        protected TypeVariableSubstitutor(Generic generic) {
                            this.typeVariable = generic;
                        }

                        public Generic onType(TypeDescription typeDescription) {
                            Generic findBindingOf = ForTypeVariableBinding.this.parameterizedType.findBindingOf(this.typeVariable);
                            return findBindingOf == null ? this.typeVariable.asRawType() : findBindingOf;
                        }

                        public Generic onMethod(MethodDescription.InDefinedShape inDefinedShape) {
                            return new RetainedMethodTypeVariable(this.typeVariable);
                        }
                    }

                    protected class RetainedMethodTypeVariable extends OfTypeVariable {
                        private final Generic typeVariable;

                        protected RetainedMethodTypeVariable(Generic generic) {
                            this.typeVariable = generic;
                        }

                        public TypeList.Generic getUpperBounds() {
                            return this.typeVariable.getUpperBounds().accept(ForTypeVariableBinding.this);
                        }

                        public TypeVariableSource getTypeVariableSource() {
                            return this.typeVariable.getTypeVariableSource();
                        }

                        public String getSymbol() {
                            return this.typeVariable.getSymbol();
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return this.typeVariable.getDeclaredAnnotations();
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForTokenNormalization extends Substitutor {
                    private final TypeDescription typeDescription;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForTokenNormalization) obj).typeDescription);
                    }

                    public int hashCode() {
                        return 527 + this.typeDescription.hashCode();
                    }

                    public /* bridge */ /* synthetic */ Object onGenericArray(Generic generic) {
                        return super.onGenericArray(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onNonGenericType(Generic generic) {
                        return super.onNonGenericType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onParameterizedType(Generic generic) {
                        return super.onParameterizedType(generic);
                    }

                    public /* bridge */ /* synthetic */ Object onWildcard(Generic generic) {
                        return super.onWildcard(generic);
                    }

                    public ForTokenNormalization(TypeDescription typeDescription2) {
                        this.typeDescription = typeDescription2;
                    }

                    /* access modifiers changed from: protected */
                    public Generic onSimpleType(Generic generic) {
                        return generic.represents(TargetType.class) ? new OfNonGenericType.Latent(this.typeDescription, generic) : generic;
                    }

                    public Generic onTypeVariable(Generic generic) {
                        return new OfTypeVariable.Symbolic(generic.getSymbol(), generic);
                    }
                }
            }

            public static class ForRawType implements Visitor<Generic> {
                private final TypeDescription declaringType;

                public Generic onNonGenericType(Generic generic) {
                    return generic;
                }

                public ForRawType(TypeDescription typeDescription) {
                    this.declaringType = typeDescription;
                }

                public Generic onGenericArray(Generic generic) {
                    return this.declaringType.isGenerified() ? new OfNonGenericType.Latent(generic.asErasure(), generic) : generic;
                }

                public Generic onWildcard(Generic generic) {
                    throw new IllegalStateException("Did not expect wildcard on top-level: " + generic);
                }

                public Generic onParameterizedType(Generic generic) {
                    return this.declaringType.isGenerified() ? new OfNonGenericType.Latent(generic.asErasure(), generic) : generic;
                }

                public Generic onTypeVariable(Generic generic) {
                    return this.declaringType.isGenerified() ? new OfNonGenericType.Latent(generic.asErasure(), generic) : generic;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class Reducing implements Visitor<TypeDescription> {
                private final TypeDescription declaringType;
                private final List<? extends TypeVariableToken> typeVariableTokens;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Reducing reducing = (Reducing) obj;
                    return this.declaringType.equals(reducing.declaringType) && this.typeVariableTokens.equals(reducing.typeVariableTokens);
                }

                public int hashCode() {
                    return ((527 + this.declaringType.hashCode()) * 31) + this.typeVariableTokens.hashCode();
                }

                public Reducing(TypeDescription typeDescription, TypeVariableToken... typeVariableTokenArr) {
                    this(typeDescription, (List<? extends TypeVariableToken>) Arrays.asList(typeVariableTokenArr));
                }

                public Reducing(TypeDescription typeDescription, List<? extends TypeVariableToken> list) {
                    this.declaringType = typeDescription;
                    this.typeVariableTokens = list;
                }

                public TypeDescription onGenericArray(Generic generic) {
                    return TargetType.resolve(generic.asErasure(), this.declaringType);
                }

                public TypeDescription onWildcard(Generic generic) {
                    throw new IllegalStateException("A wildcard cannot be a top-level type: " + generic);
                }

                public TypeDescription onParameterizedType(Generic generic) {
                    return TargetType.resolve(generic.asErasure(), this.declaringType);
                }

                public TypeDescription onTypeVariable(Generic generic) {
                    for (TypeVariableToken typeVariableToken : this.typeVariableTokens) {
                        if (generic.getSymbol().equals(typeVariableToken.getSymbol())) {
                            return (TypeDescription) ((Generic) typeVariableToken.getBounds().get(0)).accept(this);
                        }
                    }
                    return TargetType.resolve(this.declaringType.findVariable(generic.getSymbol()).asErasure(), this.declaringType);
                }

                public TypeDescription onNonGenericType(Generic generic) {
                    return TargetType.resolve(generic.asErasure(), this.declaringType);
                }
            }
        }

        public interface AnnotationReader {
            public static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));

            AnnotationList asList();

            AnnotationReader ofComponentType();

            AnnotationReader ofOuterClass();

            AnnotationReader ofOwnerType();

            AnnotationReader ofTypeArgument(int i);

            AnnotationReader ofTypeVariableBoundType(int i);

            AnnotationReader ofWildcardLowerBoundType(int i);

            AnnotationReader ofWildcardUpperBoundType(int i);

            AnnotatedElement resolve();

            public interface Dispatcher {
                public static final Object[] NO_ARGUMENTS = new Object[0];

                Generic resolve(AnnotatedElement annotatedElement);

                AnnotationReader resolveExceptionType(AccessibleObject accessibleObject, int i);

                AnnotationReader resolveFieldType(Field field);

                AnnotationReader resolveInterfaceType(Class<?> cls, int i);

                AnnotationReader resolveParameterType(AccessibleObject accessibleObject, int i);

                Generic resolveReceiverType(AccessibleObject accessibleObject);

                AnnotationReader resolveReturnType(Method method);

                AnnotationReader resolveSuperClassType(Class<?> cls);

                AnnotationReader resolveTypeVariable(TypeVariable<?> typeVariable);

                public enum CreationAction implements PrivilegedAction<Dispatcher> {
                    INSTANCE;

                    public Dispatcher run() {
                        try {
                            return new ForJava8CapableVm(Class.class.getMethod("getAnnotatedSuperclass", new Class[0]), Class.class.getMethod("getAnnotatedInterfaces", new Class[0]), Field.class.getMethod("getAnnotatedType", new Class[0]), Method.class.getMethod("getAnnotatedReturnType", new Class[0]), Class.forName("java.lang.reflect.Executable").getMethod("getAnnotatedParameterTypes", new Class[0]), Class.forName("java.lang.reflect.Executable").getMethod("getAnnotatedExceptionTypes", new Class[0]), Class.forName("java.lang.reflect.Executable").getMethod("getAnnotatedReceiverType", new Class[0]), Class.forName("java.lang.reflect.AnnotatedType").getMethod("getType", new Class[0]));
                        } catch (RuntimeException e) {
                            throw e;
                        } catch (Exception unused) {
                            return ForLegacyVm.INSTANCE;
                        }
                    }
                }

                public enum ForLegacyVm implements Dispatcher {
                    INSTANCE;

                    public AnnotationReader resolveTypeVariable(TypeVariable<?> typeVariable) {
                        return NoOp.INSTANCE;
                    }

                    public AnnotationReader resolveSuperClassType(Class<?> cls) {
                        return NoOp.INSTANCE;
                    }

                    public AnnotationReader resolveInterfaceType(Class<?> cls, int i) {
                        return NoOp.INSTANCE;
                    }

                    public AnnotationReader resolveFieldType(Field field) {
                        return NoOp.INSTANCE;
                    }

                    public AnnotationReader resolveReturnType(Method method) {
                        return NoOp.INSTANCE;
                    }

                    public AnnotationReader resolveParameterType(AccessibleObject accessibleObject, int i) {
                        return NoOp.INSTANCE;
                    }

                    public AnnotationReader resolveExceptionType(AccessibleObject accessibleObject, int i) {
                        return NoOp.INSTANCE;
                    }

                    public Generic resolveReceiverType(AccessibleObject accessibleObject) {
                        return Generic.UNDEFINED;
                    }

                    public Generic resolve(AnnotatedElement annotatedElement) {
                        throw new UnsupportedOperationException("Loaded annotated type cannot be represented on this VM");
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForJava8CapableVm implements Dispatcher {
                    /* access modifiers changed from: private */
                    public final Method getAnnotatedExceptionTypes;
                    /* access modifiers changed from: private */
                    public final Method getAnnotatedInterfaces;
                    /* access modifiers changed from: private */
                    public final Method getAnnotatedParameterTypes;
                    private final Method getAnnotatedReceiverType;
                    /* access modifiers changed from: private */
                    public final Method getAnnotatedReturnType;
                    /* access modifiers changed from: private */
                    public final Method getAnnotatedSuperclass;
                    /* access modifiers changed from: private */
                    public final Method getAnnotatedType;
                    private final Method getType;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        ForJava8CapableVm forJava8CapableVm = (ForJava8CapableVm) obj;
                        return this.getAnnotatedSuperclass.equals(forJava8CapableVm.getAnnotatedSuperclass) && this.getAnnotatedInterfaces.equals(forJava8CapableVm.getAnnotatedInterfaces) && this.getAnnotatedType.equals(forJava8CapableVm.getAnnotatedType) && this.getAnnotatedReturnType.equals(forJava8CapableVm.getAnnotatedReturnType) && this.getAnnotatedParameterTypes.equals(forJava8CapableVm.getAnnotatedParameterTypes) && this.getAnnotatedExceptionTypes.equals(forJava8CapableVm.getAnnotatedExceptionTypes) && this.getAnnotatedReceiverType.equals(forJava8CapableVm.getAnnotatedReceiverType) && this.getType.equals(forJava8CapableVm.getType);
                    }

                    public int hashCode() {
                        return ((((((((((((((527 + this.getAnnotatedSuperclass.hashCode()) * 31) + this.getAnnotatedInterfaces.hashCode()) * 31) + this.getAnnotatedType.hashCode()) * 31) + this.getAnnotatedReturnType.hashCode()) * 31) + this.getAnnotatedParameterTypes.hashCode()) * 31) + this.getAnnotatedExceptionTypes.hashCode()) * 31) + this.getAnnotatedReceiverType.hashCode()) * 31) + this.getType.hashCode();
                    }

                    protected ForJava8CapableVm(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8) {
                        this.getAnnotatedSuperclass = method;
                        this.getAnnotatedInterfaces = method2;
                        this.getAnnotatedType = method3;
                        this.getAnnotatedReturnType = method4;
                        this.getAnnotatedParameterTypes = method5;
                        this.getAnnotatedExceptionTypes = method6;
                        this.getAnnotatedReceiverType = method7;
                        this.getType = method8;
                    }

                    public AnnotationReader resolveTypeVariable(TypeVariable<?> typeVariable) {
                        return new AnnotatedTypeVariableType(typeVariable);
                    }

                    public AnnotationReader resolveSuperClassType(Class<?> cls) {
                        return new AnnotatedSuperClass(cls);
                    }

                    public AnnotationReader resolveInterfaceType(Class<?> cls, int i) {
                        return new AnnotatedInterfaceType(cls, i);
                    }

                    public AnnotationReader resolveFieldType(Field field) {
                        return new AnnotatedFieldType(field);
                    }

                    public AnnotationReader resolveReturnType(Method method) {
                        return new AnnotatedReturnType(method);
                    }

                    public AnnotationReader resolveParameterType(AccessibleObject accessibleObject, int i) {
                        return new AnnotatedParameterizedType(accessibleObject, i);
                    }

                    public AnnotationReader resolveExceptionType(AccessibleObject accessibleObject, int i) {
                        return new AnnotatedExceptionType(accessibleObject, i);
                    }

                    public Generic resolveReceiverType(AccessibleObject accessibleObject) {
                        try {
                            return resolve((AnnotatedElement) this.getAnnotatedReceiverType.invoke(accessibleObject, NO_ARGUMENTS));
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getAnnotatedReceiverType", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getAnnotatedReceiverType", e2.getCause());
                        }
                    }

                    public Generic resolve(AnnotatedElement annotatedElement) {
                        if (annotatedElement != null) {
                            return TypeDefinition.Sort.describe((Type) this.getType.invoke(annotatedElement, NO_ARGUMENTS), new Resolved(annotatedElement));
                        }
                        try {
                            return Generic.UNDEFINED;
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedType#getType", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedType#getType", e2.getCause());
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    protected static class Resolved extends Delegator {
                        private final AnnotatedElement annotatedElement;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.annotatedElement.equals(((Resolved) obj).annotatedElement);
                        }

                        public int hashCode() {
                            return 527 + this.annotatedElement.hashCode();
                        }

                        protected Resolved(AnnotatedElement annotatedElement2) {
                            this.annotatedElement = annotatedElement2;
                        }

                        public AnnotatedElement resolve() {
                            return this.annotatedElement;
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance
                    protected static class AnnotatedTypeVariableType extends Delegator {
                        private final TypeVariable<?> typeVariable;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            return obj != null && getClass() == obj.getClass() && this.typeVariable.equals(((AnnotatedTypeVariableType) obj).typeVariable);
                        }

                        public int hashCode() {
                            return 527 + this.typeVariable.hashCode();
                        }

                        protected AnnotatedTypeVariableType(TypeVariable<?> typeVariable2) {
                            this.typeVariable = typeVariable2;
                        }

                        /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.reflect.TypeVariable<?>, java.lang.reflect.AnnotatedElement] */
                        public AnnotatedElement resolve() {
                            return this.typeVariable;
                        }

                        public AnnotationReader ofTypeVariableBoundType(int i) {
                            return new ForTypeVariableBoundType.OfFormalTypeVariable(this.typeVariable, i);
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotatedSuperClass extends Delegator {
                        private final Class<?> type;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            AnnotatedSuperClass annotatedSuperClass = (AnnotatedSuperClass) obj;
                            return this.type.equals(annotatedSuperClass.type) && ForJava8CapableVm.this.equals(ForJava8CapableVm.this);
                        }

                        public int hashCode() {
                            return ((527 + this.type.hashCode()) * 31) + ForJava8CapableVm.this.hashCode();
                        }

                        protected AnnotatedSuperClass(Class<?> cls) {
                            this.type = cls;
                        }

                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) ForJava8CapableVm.this.getAnnotatedSuperclass.invoke(this.type, NO_ARGUMENTS);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.Class#getAnnotatedSuperclass", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.Class#getAnnotatedSuperclass", e2.getCause());
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotatedInterfaceType extends Delegator {
                        private final int index;
                        private final Class<?> type;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            AnnotatedInterfaceType annotatedInterfaceType = (AnnotatedInterfaceType) obj;
                            return this.index == annotatedInterfaceType.index && this.type.equals(annotatedInterfaceType.type) && ForJava8CapableVm.this.equals(ForJava8CapableVm.this);
                        }

                        public int hashCode() {
                            return ((((527 + this.type.hashCode()) * 31) + this.index) * 31) + ForJava8CapableVm.this.hashCode();
                        }

                        protected AnnotatedInterfaceType(Class<?> cls, int i) {
                            this.type = cls;
                            this.index = i;
                        }

                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) Array.get(ForJava8CapableVm.this.getAnnotatedInterfaces.invoke(this.type, new Object[0]), this.index);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.Class#getAnnotatedInterfaces", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.Class#getAnnotatedInterfaces", e2.getCause());
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotatedFieldType extends Delegator {
                        private final Field field;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            AnnotatedFieldType annotatedFieldType = (AnnotatedFieldType) obj;
                            return this.field.equals(annotatedFieldType.field) && ForJava8CapableVm.this.equals(ForJava8CapableVm.this);
                        }

                        public int hashCode() {
                            return ((527 + this.field.hashCode()) * 31) + ForJava8CapableVm.this.hashCode();
                        }

                        protected AnnotatedFieldType(Field field2) {
                            this.field = field2;
                        }

                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) ForJava8CapableVm.this.getAnnotatedType.invoke(this.field, NO_ARGUMENTS);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Field#getAnnotatedType", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Field#getAnnotatedType", e2.getCause());
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotatedReturnType extends Delegator {
                        private final Method method;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            AnnotatedReturnType annotatedReturnType = (AnnotatedReturnType) obj;
                            return this.method.equals(annotatedReturnType.method) && ForJava8CapableVm.this.equals(ForJava8CapableVm.this);
                        }

                        public int hashCode() {
                            return ((527 + this.method.hashCode()) * 31) + ForJava8CapableVm.this.hashCode();
                        }

                        protected AnnotatedReturnType(Method method2) {
                            this.method = method2;
                        }

                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) ForJava8CapableVm.this.getAnnotatedReturnType.invoke(this.method, NO_ARGUMENTS);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Method#getAnnotatedReturnType", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Method#getAnnotatedReturnType", e2.getCause());
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotatedParameterizedType extends Delegator {
                        private final AccessibleObject executable;
                        private final int index;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) obj;
                            return this.index == annotatedParameterizedType.index && this.executable.equals(annotatedParameterizedType.executable) && ForJava8CapableVm.this.equals(ForJava8CapableVm.this);
                        }

                        public int hashCode() {
                            return ((((527 + this.executable.hashCode()) * 31) + this.index) * 31) + ForJava8CapableVm.this.hashCode();
                        }

                        protected AnnotatedParameterizedType(AccessibleObject accessibleObject, int i) {
                            this.executable = accessibleObject;
                            this.index = i;
                        }

                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) Array.get(ForJava8CapableVm.this.getAnnotatedParameterTypes.invoke(this.executable, NO_ARGUMENTS), this.index);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getAnnotatedParameterTypes", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getAnnotatedParameterTypes", e2.getCause());
                            }
                        }
                    }

                    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                    protected class AnnotatedExceptionType extends Delegator {
                        private final AccessibleObject executable;
                        private final int index;

                        public boolean equals(Object obj) {
                            if (this == obj) {
                                return true;
                            }
                            if (obj == null || getClass() != obj.getClass()) {
                                return false;
                            }
                            AnnotatedExceptionType annotatedExceptionType = (AnnotatedExceptionType) obj;
                            return this.index == annotatedExceptionType.index && this.executable.equals(annotatedExceptionType.executable) && ForJava8CapableVm.this.equals(ForJava8CapableVm.this);
                        }

                        public int hashCode() {
                            return ((((527 + this.executable.hashCode()) * 31) + this.index) * 31) + ForJava8CapableVm.this.hashCode();
                        }

                        protected AnnotatedExceptionType(AccessibleObject accessibleObject, int i) {
                            this.executable = accessibleObject;
                            this.index = i;
                        }

                        public AnnotatedElement resolve() {
                            try {
                                return (AnnotatedElement) Array.get(ForJava8CapableVm.this.getAnnotatedExceptionTypes.invoke(this.executable, NO_ARGUMENTS), this.index);
                            } catch (IllegalAccessException e) {
                                throw new IllegalStateException("Cannot access java.lang.reflect.Executable#getAnnotatedExceptionTypes", e);
                            } catch (InvocationTargetException e2) {
                                throw new IllegalStateException("Error invoking java.lang.reflect.Executable#getAnnotatedExceptionTypes", e2.getCause());
                            }
                        }
                    }
                }
            }

            public enum NoOp implements AnnotationReader, AnnotatedElement {
                INSTANCE;

                public Annotation[] getDeclaredAnnotations() {
                    return new Annotation[0];
                }

                public AnnotationReader ofComponentType() {
                    return this;
                }

                public AnnotationReader ofOuterClass() {
                    return this;
                }

                public AnnotationReader ofOwnerType() {
                    return this;
                }

                public AnnotationReader ofTypeArgument(int i) {
                    return this;
                }

                public AnnotationReader ofTypeVariableBoundType(int i) {
                    return this;
                }

                public AnnotationReader ofWildcardLowerBoundType(int i) {
                    return this;
                }

                public AnnotationReader ofWildcardUpperBoundType(int i) {
                    return this;
                }

                public AnnotatedElement resolve() {
                    return this;
                }

                public AnnotationList asList() {
                    return new AnnotationList.Empty();
                }

                public boolean isAnnotationPresent(Class<? extends Annotation> cls) {
                    throw new IllegalStateException("Cannot resolve annotations for no-op reader: " + this);
                }

                public <T extends Annotation> T getAnnotation(Class<T> cls) {
                    throw new IllegalStateException("Cannot resolve annotations for no-op reader: " + this);
                }

                public Annotation[] getAnnotations() {
                    throw new IllegalStateException("Cannot resolve annotations for no-op reader: " + this);
                }
            }

            public static abstract class Delegator implements AnnotationReader {
                protected static final Object[] NO_ARGUMENTS = new Object[0];

                public AnnotationReader ofWildcardUpperBoundType(int i) {
                    return new ForWildcardUpperBoundType(this, i);
                }

                public AnnotationReader ofWildcardLowerBoundType(int i) {
                    return new ForWildcardLowerBoundType(this, i);
                }

                public AnnotationReader ofTypeVariableBoundType(int i) {
                    return new ForTypeVariableBoundType(this, i);
                }

                public AnnotationReader ofTypeArgument(int i) {
                    return new ForTypeArgument(this, i);
                }

                public AnnotationReader ofOwnerType() {
                    return ForOwnerType.of(this);
                }

                public AnnotationReader ofOuterClass() {
                    return ForOwnerType.of(this);
                }

                public AnnotationReader ofComponentType() {
                    return new ForComponentType(this);
                }

                public AnnotationList asList() {
                    return new AnnotationList.ForLoadedAnnotations(resolve().getDeclaredAnnotations());
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static abstract class Chained extends Delegator {
                    protected static final Method NOT_AVAILABLE = null;
                    protected final AnnotationReader annotationReader;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.annotationReader.equals(((Chained) obj).annotationReader);
                    }

                    public int hashCode() {
                        return 527 + this.annotationReader.hashCode();
                    }

                    /* access modifiers changed from: protected */
                    public abstract AnnotatedElement resolve(AnnotatedElement annotatedElement);

                    protected Chained(AnnotationReader annotationReader2) {
                        this.annotationReader = annotationReader2;
                    }

                    protected static Method of(String str, String str2) {
                        try {
                            return Class.forName(str).getMethod(str2, new Class[0]);
                        } catch (Exception unused) {
                            return NOT_AVAILABLE;
                        }
                    }

                    public AnnotatedElement resolve() {
                        return resolve(this.annotationReader.resolve());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForWildcardUpperBoundType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_UPPER_BOUNDS = of("java.lang.reflect.AnnotatedWildcardType", "getAnnotatedUpperBounds");
                private final int index;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((ForWildcardUpperBoundType) obj).index;
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.index;
                }

                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForWildcardUpperBoundType(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                /* access modifiers changed from: protected */
                public AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        Object invoke = GET_ANNOTATED_UPPER_BOUNDS.invoke(annotatedElement, NO_ARGUMENTS);
                        if (Array.getLength(invoke) == 0) {
                            return NoOp.INSTANCE;
                        }
                        return (AnnotatedElement) Array.get(invoke, this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedWildcardType#getAnnotatedUpperBounds", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedWildcardType#getAnnotatedUpperBounds", e2.getCause());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForWildcardLowerBoundType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_LOWER_BOUNDS = of("java.lang.reflect.AnnotatedWildcardType", "getAnnotatedLowerBounds");
                private final int index;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((ForWildcardLowerBoundType) obj).index;
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.index;
                }

                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForWildcardLowerBoundType(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                /* access modifiers changed from: protected */
                public AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) Array.get(GET_ANNOTATED_LOWER_BOUNDS.invoke(annotatedElement, NO_ARGUMENTS), this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedWildcardType#getAnnotatedLowerBounds", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedWildcardType#getAnnotatedLowerBounds", e2.getCause());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForTypeVariableBoundType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_BOUNDS = of("java.lang.reflect.AnnotatedTypeVariable", "getAnnotatedBounds");
                private final int index;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((ForTypeVariableBoundType) obj).index;
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.index;
                }

                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForTypeVariableBoundType(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                /* access modifiers changed from: protected */
                public AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) Array.get(GET_ANNOTATED_BOUNDS.invoke(annotatedElement, NO_ARGUMENTS), this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedTypeVariable#getAnnotatedBounds", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedTypeVariable#getAnnotatedBounds", e2.getCause());
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class OfFormalTypeVariable extends Delegator {
                    private static final Method GET_ANNOTATED_BOUNDS = Delegator.Chained.of(TypeVariable.class.getName(), "getAnnotatedBounds");
                    private final int index;
                    private final TypeVariable<?> typeVariable;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        OfFormalTypeVariable ofFormalTypeVariable = (OfFormalTypeVariable) obj;
                        return this.index == ofFormalTypeVariable.index && this.typeVariable.equals(ofFormalTypeVariable.typeVariable);
                    }

                    public int hashCode() {
                        return ((527 + this.typeVariable.hashCode()) * 31) + this.index;
                    }

                    protected OfFormalTypeVariable(TypeVariable<?> typeVariable2, int i) {
                        this.typeVariable = typeVariable2;
                        this.index = i;
                    }

                    public AnnotatedElement resolve() {
                        try {
                            return (AnnotatedElement) Array.get(GET_ANNOTATED_BOUNDS.invoke(this.typeVariable, NO_ARGUMENTS), this.index);
                        } catch (ClassCastException unused) {
                            return NoOp.INSTANCE;
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("Cannot access java.lang.reflect.TypeVariable#getAnnotatedBounds", e);
                        } catch (InvocationTargetException e2) {
                            throw new IllegalStateException("Error invoking java.lang.reflect.TypeVariable#getAnnotatedBounds", e2.getCause());
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForTypeArgument extends Delegator.Chained {
                private static final Method GET_ANNOTATED_ACTUAL_TYPE_ARGUMENTS = of("java.lang.reflect.AnnotatedParameterizedType", "getAnnotatedActualTypeArguments");
                private final int index;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((ForTypeArgument) obj).index;
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.index;
                }

                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForTypeArgument(AnnotationReader annotationReader, int i) {
                    super(annotationReader);
                    this.index = i;
                }

                /* access modifiers changed from: protected */
                public AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) Array.get(GET_ANNOTATED_ACTUAL_TYPE_ARGUMENTS.invoke(annotatedElement, NO_ARGUMENTS), this.index);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedParameterizedType#getAnnotatedActualTypeArguments", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedParameterizedType#getAnnotatedActualTypeArguments", e2.getCause());
                    }
                }
            }

            public static class ForComponentType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_GENERIC_COMPONENT_TYPE = of("java.lang.reflect.AnnotatedArrayType", "getAnnotatedGenericComponentType");

                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                protected ForComponentType(AnnotationReader annotationReader) {
                    super(annotationReader);
                }

                /* access modifiers changed from: protected */
                public AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        return (AnnotatedElement) GET_ANNOTATED_GENERIC_COMPONENT_TYPE.invoke(annotatedElement, NO_ARGUMENTS);
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedArrayType#getAnnotatedGenericComponentType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedArrayType#getAnnotatedGenericComponentType", e2.getCause());
                    }
                }
            }

            public static class ForOwnerType extends Delegator.Chained {
                private static final Method GET_ANNOTATED_OWNER_TYPE = of("java.lang.reflect.AnnotatedType", "getAnnotatedOwnerType");

                public /* bridge */ /* synthetic */ AnnotatedElement resolve() {
                    return super.resolve();
                }

                /* access modifiers changed from: private */
                public static AnnotationReader of(AnnotationReader annotationReader) {
                    return GET_ANNOTATED_OWNER_TYPE == null ? NoOp.INSTANCE : new ForOwnerType(annotationReader);
                }

                protected ForOwnerType(AnnotationReader annotationReader) {
                    super(annotationReader);
                }

                /* access modifiers changed from: protected */
                public AnnotatedElement resolve(AnnotatedElement annotatedElement) {
                    try {
                        AnnotatedElement annotatedElement2 = (AnnotatedElement) GET_ANNOTATED_OWNER_TYPE.invoke(annotatedElement, NO_ARGUMENTS);
                        return annotatedElement2 == null ? NoOp.INSTANCE : annotatedElement2;
                    } catch (ClassCastException unused) {
                        return NoOp.INSTANCE;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.AnnotatedType#getAnnotatedOwnerType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.AnnotatedType#getAnnotatedOwnerType", e2.getCause());
                    }
                }
            }
        }

        public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements Generic {
            public Generic asGenericType() {
                return this;
            }

            public int getModifiers() {
                return asErasure().getModifiers();
            }

            public Generic asRawType() {
                return asErasure().asGenericType();
            }

            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }
        }

        public static abstract class OfNonGenericType extends AbstractBase {
            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.NON_GENERIC;
            }

            public Generic getSuperClass() {
                TypeDescription asErasure = asErasure();
                Generic superClass = asErasure.getSuperClass();
                if (AbstractBase.RAW_TYPES) {
                    return superClass;
                }
                return superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, new Visitor.ForRawType(asErasure), AnnotationSource.Empty.INSTANCE);
            }

            public TypeList.Generic getInterfaces() {
                TypeDescription asErasure = asErasure();
                if (AbstractBase.RAW_TYPES) {
                    return asErasure.getInterfaces();
                }
                return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(asErasure.getInterfaces(), new Visitor.ForRawType(asErasure));
            }

            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                TypeDescription asErasure = asErasure();
                return new FieldList.TypeSubstituting(this, asErasure.getDeclaredFields(), AbstractBase.RAW_TYPES ? Visitor.NoOp.INSTANCE : new Visitor.ForRawType(asErasure));
            }

            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                TypeDescription asErasure = asErasure();
                return new MethodList.TypeSubstituting(this, asErasure.getDeclaredMethods(), AbstractBase.RAW_TYPES ? Visitor.NoOp.INSTANCE : new Visitor.ForRawType(asErasure));
            }

            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A non-generic type does not imply type arguments: " + this);
            }

            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A non-generic type does not imply type arguments: " + this);
            }

            public <T> T accept(Visitor<T> visitor) {
                return visitor.onNonGenericType(this);
            }

            public String getTypeName() {
                return asErasure().getTypeName();
            }

            public TypeList.Generic getUpperBounds() {
                throw new IllegalStateException("A non-generic type does not imply upper type bounds: " + this);
            }

            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A non-generic type does not imply lower type bounds: " + this);
            }

            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A non-generic type does not imply a type variable source: " + this);
            }

            public String getSymbol() {
                throw new IllegalStateException("A non-generic type does not imply a symbol: " + this);
            }

            public StackSize getStackSize() {
                return asErasure().getStackSize();
            }

            public String getActualName() {
                return asErasure().getActualName();
            }

            public boolean isArray() {
                return asErasure().isArray();
            }

            public boolean isPrimitive() {
                return asErasure().isPrimitive();
            }

            public boolean represents(Type type) {
                return asErasure().represents(type);
            }

            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }

            public int hashCode() {
                return asErasure().hashCode();
            }

            public boolean equals(Object obj) {
                return this == obj || asErasure().equals(obj);
            }

            public String toString() {
                return asErasure().toString();
            }

            public static class ForLoadedType extends OfNonGenericType {
                private static final Map<Class<?>, Generic> TYPE_CACHE;
                private final AnnotationReader annotationReader;
                private final Class<?> type;

                static {
                    HashMap hashMap = new HashMap();
                    TYPE_CACHE = hashMap;
                    hashMap.put(TargetType.class, new ForLoadedType(TargetType.class));
                    TYPE_CACHE.put(Object.class, new ForLoadedType(Object.class));
                    TYPE_CACHE.put(String.class, new ForLoadedType(String.class));
                    TYPE_CACHE.put(Boolean.class, new ForLoadedType(Boolean.class));
                    TYPE_CACHE.put(Byte.class, new ForLoadedType(Byte.class));
                    TYPE_CACHE.put(Short.class, new ForLoadedType(Short.class));
                    TYPE_CACHE.put(Character.class, new ForLoadedType(Character.class));
                    TYPE_CACHE.put(Integer.class, new ForLoadedType(Integer.class));
                    TYPE_CACHE.put(Long.class, new ForLoadedType(Long.class));
                    TYPE_CACHE.put(Float.class, new ForLoadedType(Float.class));
                    TYPE_CACHE.put(Double.class, new ForLoadedType(Double.class));
                    TYPE_CACHE.put(Void.TYPE, new ForLoadedType(Void.TYPE));
                    TYPE_CACHE.put(Boolean.TYPE, new ForLoadedType(Boolean.TYPE));
                    TYPE_CACHE.put(Byte.TYPE, new ForLoadedType(Byte.TYPE));
                    TYPE_CACHE.put(Short.TYPE, new ForLoadedType(Short.TYPE));
                    TYPE_CACHE.put(Character.TYPE, new ForLoadedType(Character.TYPE));
                    TYPE_CACHE.put(Integer.TYPE, new ForLoadedType(Integer.TYPE));
                    TYPE_CACHE.put(Long.TYPE, new ForLoadedType(Long.TYPE));
                    TYPE_CACHE.put(Float.TYPE, new ForLoadedType(Float.TYPE));
                    TYPE_CACHE.put(Double.TYPE, new ForLoadedType(Double.TYPE));
                }

                public ForLoadedType(Class<?> cls) {
                    this(cls, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(Class<?> cls, AnnotationReader annotationReader2) {
                    this.type = cls;
                    this.annotationReader = annotationReader2;
                }

                public static Generic of(Class<?> cls) {
                    Generic generic = TYPE_CACHE.get(cls);
                    return generic == null ? new ForLoadedType(cls) : generic;
                }

                public TypeDescription asErasure() {
                    return ForLoadedType.of(this.type);
                }

                public Generic getOwnerType() {
                    Class<?> declaringClass = this.type.getDeclaringClass();
                    if (declaringClass == null) {
                        return Generic.UNDEFINED;
                    }
                    return new ForLoadedType(declaringClass, this.annotationReader.ofOuterClass());
                }

                public Generic getComponentType() {
                    Class<?> componentType = this.type.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return new ForLoadedType(componentType, this.annotationReader.ofComponentType());
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                public boolean represents(Type type2) {
                    return this.type == type2 || super.represents(type2);
                }
            }

            public static class ForErasure extends OfNonGenericType {
                private final TypeDescription typeDescription;

                public ForErasure(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public TypeDescription asErasure() {
                    return this.typeDescription;
                }

                public Generic getOwnerType() {
                    TypeDescription declaringType = this.typeDescription.getDeclaringType();
                    if (declaringType == null) {
                        return Generic.UNDEFINED;
                    }
                    return declaringType.asGenericType();
                }

                public Generic getComponentType() {
                    TypeDescription componentType = this.typeDescription.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return componentType.asGenericType();
                }

                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }

            public static class Latent extends OfNonGenericType {
                private final AnnotationSource annotationSource;
                private final Generic declaringType;
                private final TypeDescription typeDescription;

                public Latent(TypeDescription typeDescription2, AnnotationSource annotationSource2) {
                    this(typeDescription2, typeDescription2.getDeclaringType(), annotationSource2);
                }

                /* JADX WARNING: Illegal instructions before constructor call */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                private Latent(net.bytebuddy.description.type.TypeDescription r1, net.bytebuddy.description.type.TypeDescription r2, net.bytebuddy.description.annotation.AnnotationSource r3) {
                    /*
                        r0 = this;
                        if (r2 != 0) goto L_0x0005
                        net.bytebuddy.description.type.TypeDescription$Generic r2 = net.bytebuddy.description.type.TypeDescription.Generic.UNDEFINED
                        goto L_0x0009
                    L_0x0005:
                        net.bytebuddy.description.type.TypeDescription$Generic r2 = r2.asGenericType()
                    L_0x0009:
                        r0.<init>((net.bytebuddy.description.type.TypeDescription) r1, (net.bytebuddy.description.type.TypeDescription.Generic) r2, (net.bytebuddy.description.annotation.AnnotationSource) r3)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.Generic.OfNonGenericType.Latent.<init>(net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.annotation.AnnotationSource):void");
                }

                protected Latent(TypeDescription typeDescription2, Generic generic, AnnotationSource annotationSource2) {
                    this.typeDescription = typeDescription2;
                    this.declaringType = generic;
                    this.annotationSource = annotationSource2;
                }

                public Generic getOwnerType() {
                    return this.declaringType;
                }

                public Generic getComponentType() {
                    TypeDescription componentType = this.typeDescription.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return componentType.asGenericType();
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                public TypeDescription asErasure() {
                    return this.typeDescription;
                }
            }

            public static class ForReifiedErasure extends OfNonGenericType {
                private final TypeDescription typeDescription;

                protected ForReifiedErasure(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                protected static Generic of(TypeDescription typeDescription2) {
                    return typeDescription2.isGenerified() ? new ForReifiedErasure(typeDescription2) : new ForErasure(typeDescription2);
                }

                public Generic getSuperClass() {
                    Generic superClass = this.typeDescription.getSuperClass();
                    return superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, Visitor.Reifying.INHERITING);
                }

                public TypeList.Generic getInterfaces() {
                    return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(this.typeDescription.getInterfaces(), Visitor.Reifying.INHERITING);
                }

                public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                    return new FieldList.TypeSubstituting(this, this.typeDescription.getDeclaredFields(), Visitor.TypeErasing.INSTANCE);
                }

                public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                    return new MethodList.TypeSubstituting(this, this.typeDescription.getDeclaredMethods(), Visitor.TypeErasing.INSTANCE);
                }

                public TypeDescription asErasure() {
                    return this.typeDescription;
                }

                public Generic getOwnerType() {
                    TypeDescription declaringType = this.typeDescription.getDeclaringType();
                    if (declaringType == null) {
                        return Generic.UNDEFINED;
                    }
                    return of(declaringType);
                }

                public Generic getComponentType() {
                    TypeDescription componentType = this.typeDescription.getComponentType();
                    if (componentType == null) {
                        return Generic.UNDEFINED;
                    }
                    return of(componentType);
                }

                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }
        }

        public static abstract class OfGenericArray extends AbstractBase {
            public boolean isArray() {
                return true;
            }

            public boolean isPrimitive() {
                return false;
            }

            public TypeDefinition.Sort getSort() {
                return getComponentType().getSort().isNonGeneric() ? TypeDefinition.Sort.NON_GENERIC : TypeDefinition.Sort.GENERIC_ARRAY;
            }

            public TypeDescription asErasure() {
                return ArrayProjection.of(getComponentType().asErasure(), 1);
            }

            public Generic getSuperClass() {
                return Generic.OBJECT;
            }

            public TypeList.Generic getInterfaces() {
                return TypeDescription.ARRAY_INTERFACES;
            }

            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                return new FieldList.Empty();
            }

            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                return new MethodList.Empty();
            }

            public TypeList.Generic getUpperBounds() {
                throw new IllegalStateException("A generic array type does not imply upper type bounds: " + this);
            }

            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A generic array type does not imply lower type bounds: " + this);
            }

            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A generic array type does not imply a type variable source: " + this);
            }

            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A generic array type does not imply type arguments: " + this);
            }

            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A generic array type does not imply type arguments: " + this);
            }

            public Generic getOwnerType() {
                return Generic.UNDEFINED;
            }

            public String getSymbol() {
                throw new IllegalStateException("A generic array type does not imply a symbol: " + this);
            }

            public String getTypeName() {
                if (getSort().isNonGeneric()) {
                    return asErasure().getTypeName();
                }
                return toString();
            }

            public String getActualName() {
                if (getSort().isNonGeneric()) {
                    return asErasure().getActualName();
                }
                return toString();
            }

            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }

            public <T> T accept(Visitor<T> visitor) {
                if (getSort().isNonGeneric()) {
                    return visitor.onNonGenericType(this);
                }
                return visitor.onGenericArray(this);
            }

            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            public int hashCode() {
                if (getSort().isNonGeneric()) {
                    return asErasure().hashCode();
                }
                return getComponentType().hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (getSort().isNonGeneric()) {
                    return asErasure().equals(obj);
                }
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                if (!generic.getSort().isGenericArray() || !getComponentType().equals(generic.getComponentType())) {
                    return false;
                }
                return true;
            }

            public String toString() {
                if (getSort().isNonGeneric()) {
                    return asErasure().toString();
                }
                return getComponentType().getTypeName() + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            }

            public static class ForLoadedType extends OfGenericArray {
                private final AnnotationReader annotationReader;
                private final GenericArrayType genericArrayType;

                public ForLoadedType(GenericArrayType genericArrayType2) {
                    this(genericArrayType2, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(GenericArrayType genericArrayType2, AnnotationReader annotationReader2) {
                    this.genericArrayType = genericArrayType2;
                    this.annotationReader = annotationReader2;
                }

                public Generic getComponentType() {
                    return TypeDefinition.Sort.describe(this.genericArrayType.getGenericComponentType(), this.annotationReader.ofComponentType());
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                public boolean represents(Type type) {
                    return this.genericArrayType == type || super.represents(type);
                }
            }

            public static class Latent extends OfGenericArray {
                private final AnnotationSource annotationSource;
                private final Generic componentType;

                public Latent(Generic generic, AnnotationSource annotationSource2) {
                    this.componentType = generic;
                    this.annotationSource = annotationSource2;
                }

                public Generic getComponentType() {
                    return this.componentType;
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }
            }
        }

        public static abstract class OfWildcardType extends AbstractBase {
            public static final String SYMBOL = "?";

            public boolean isArray() {
                return false;
            }

            public boolean isPrimitive() {
                return false;
            }

            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.WILDCARD;
            }

            public TypeDescription asErasure() {
                throw new IllegalStateException("A wildcard does not represent an erasable type: " + this);
            }

            public Generic getSuperClass() {
                throw new IllegalStateException("A wildcard does not imply a super type definition: " + this);
            }

            public TypeList.Generic getInterfaces() {
                throw new IllegalStateException("A wildcard does not imply an interface type definition: " + this);
            }

            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                throw new IllegalStateException("A wildcard does not imply field definitions: " + this);
            }

            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                throw new IllegalStateException("A wildcard does not imply method definitions: " + this);
            }

            public Generic getComponentType() {
                throw new IllegalStateException("A wildcard does not imply a component type: " + this);
            }

            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A wildcard does not imply a type variable source: " + this);
            }

            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A wildcard does not imply type arguments: " + this);
            }

            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A wildcard does not imply type arguments: " + this);
            }

            public Generic getOwnerType() {
                throw new IllegalStateException("A wildcard does not imply an owner type: " + this);
            }

            public String getSymbol() {
                throw new IllegalStateException("A wildcard does not imply a symbol: " + this);
            }

            public String getTypeName() {
                return toString();
            }

            public String getActualName() {
                return toString();
            }

            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }

            public Iterator<TypeDefinition> iterator() {
                throw new IllegalStateException("A wildcard does not imply a super type definition: " + this);
            }

            public <T> T accept(Visitor<T> visitor) {
                return visitor.onWildcard(this);
            }

            public StackSize getStackSize() {
                throw new IllegalStateException("A wildcard does not imply an operand stack size: " + this);
            }

            public int hashCode() {
                int i = 1;
                int i2 = 1;
                for (Generic hashCode : getLowerBounds()) {
                    i2 = (i2 * 31) + hashCode.hashCode();
                }
                for (Generic hashCode2 : getUpperBounds()) {
                    i = (i * 31) + hashCode2.hashCode();
                }
                return i2 ^ i;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                if (!generic.getSort().isWildcard() || !getUpperBounds().equals(generic.getUpperBounds()) || !getLowerBounds().equals(generic.getLowerBounds())) {
                    return false;
                }
                return true;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder(SYMBOL);
                TypeList.Generic lowerBounds = getLowerBounds();
                if (!lowerBounds.isEmpty()) {
                    sb.append(" super ");
                } else {
                    lowerBounds = getUpperBounds();
                    if (((Generic) lowerBounds.getOnly()).equals(Generic.OBJECT)) {
                        return SYMBOL;
                    }
                    sb.append(" extends ");
                }
                sb.append(((Generic) lowerBounds.getOnly()).getTypeName());
                return sb.toString();
            }

            public static class ForLoadedType extends OfWildcardType {
                private final AnnotationReader annotationReader;
                private final WildcardType wildcardType;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public ForLoadedType(WildcardType wildcardType2) {
                    this(wildcardType2, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(WildcardType wildcardType2, AnnotationReader annotationReader2) {
                    this.wildcardType = wildcardType2;
                    this.annotationReader = annotationReader2;
                }

                public TypeList.Generic getUpperBounds() {
                    return new WildcardUpperBoundTypeList(this.wildcardType.getUpperBounds(), this.annotationReader);
                }

                public TypeList.Generic getLowerBounds() {
                    return new WildcardLowerBoundTypeList(this.wildcardType.getLowerBounds(), this.annotationReader);
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                public boolean represents(Type type) {
                    return this.wildcardType == type || super.represents(type);
                }

                protected static class WildcardUpperBoundTypeList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] upperBound;

                    protected WildcardUpperBoundTypeList(Type[] typeArr, AnnotationReader annotationReader2) {
                        this.upperBound = typeArr;
                        this.annotationReader = annotationReader2;
                    }

                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.upperBound[i], this.annotationReader.ofWildcardUpperBoundType(i));
                    }

                    public int size() {
                        return this.upperBound.length;
                    }
                }

                protected static class WildcardLowerBoundTypeList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] lowerBound;

                    protected WildcardLowerBoundTypeList(Type[] typeArr, AnnotationReader annotationReader2) {
                        this.lowerBound = typeArr;
                        this.annotationReader = annotationReader2;
                    }

                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.lowerBound[i], this.annotationReader.ofWildcardLowerBoundType(i));
                    }

                    public int size() {
                        return this.lowerBound.length;
                    }
                }
            }

            public static class Latent extends OfWildcardType {
                private final AnnotationSource annotationSource;
                private final List<? extends Generic> lowerBounds;
                private final List<? extends Generic> upperBounds;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                protected Latent(List<? extends Generic> list, List<? extends Generic> list2, AnnotationSource annotationSource2) {
                    this.upperBounds = list;
                    this.lowerBounds = list2;
                    this.annotationSource = annotationSource2;
                }

                public static Generic unbounded(AnnotationSource annotationSource2) {
                    return new Latent(Collections.singletonList(Generic.OBJECT), Collections.emptyList(), annotationSource2);
                }

                public static Generic boundedAbove(Generic generic, AnnotationSource annotationSource2) {
                    return new Latent(Collections.singletonList(generic), Collections.emptyList(), annotationSource2);
                }

                public static Generic boundedBelow(Generic generic, AnnotationSource annotationSource2) {
                    return new Latent(Collections.singletonList(Generic.OBJECT), Collections.singletonList(generic), annotationSource2);
                }

                public TypeList.Generic getUpperBounds() {
                    return new TypeList.Generic.Explicit((List<? extends TypeDefinition>) this.upperBounds);
                }

                public TypeList.Generic getLowerBounds() {
                    return new TypeList.Generic.Explicit((List<? extends TypeDefinition>) this.lowerBounds);
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }
            }
        }

        public static abstract class OfParameterizedType extends AbstractBase {

            protected enum RenderingDelegate {
                FOR_LEGACY_VM {
                    /* access modifiers changed from: protected */
                    public void apply(StringBuilder sb, TypeDescription typeDescription, Generic generic) {
                        String str;
                        if (generic != null) {
                            sb.append(generic.getTypeName());
                            sb.append('.');
                            if (generic.getSort().isParameterized()) {
                                str = typeDescription.getSimpleName();
                            } else {
                                str = typeDescription.getName();
                            }
                            sb.append(str);
                            return;
                        }
                        sb.append(typeDescription.getName());
                    }
                },
                FOR_JAVA_8_CAPABLE_VM {
                    /* access modifiers changed from: protected */
                    public void apply(StringBuilder sb, TypeDescription typeDescription, Generic generic) {
                        if (generic != null) {
                            sb.append(generic.getTypeName());
                            sb.append(Typography.dollar);
                            if (generic.getSort().isParameterized()) {
                                String name = typeDescription.getName();
                                sb.append(name.replace(generic.asErasure().getName() + "$", ""));
                                return;
                            }
                            sb.append(typeDescription.getSimpleName());
                            return;
                        }
                        sb.append(typeDescription.getName());
                    }
                };
                
                protected static final RenderingDelegate CURRENT = null;

                /* access modifiers changed from: protected */
                public abstract void apply(StringBuilder sb, TypeDescription typeDescription, Generic generic);
            }

            public boolean isArray() {
                return false;
            }

            public boolean isPrimitive() {
                return false;
            }

            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.PARAMETERIZED;
            }

            public Generic getSuperClass() {
                Generic superClass = asErasure().getSuperClass();
                return superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            public TypeList.Generic getInterfaces() {
                return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(asErasure().getInterfaces(), new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                return new FieldList.TypeSubstituting(this, asErasure().getDeclaredFields(), new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                return new MethodList.TypeSubstituting(this, asErasure().getDeclaredMethods(), new Visitor.Substitutor.ForTypeVariableBinding(this));
            }

            /* JADX WARNING: Removed duplicated region for block: B:4:0x001c  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public net.bytebuddy.description.type.TypeDescription.Generic findBindingOf(net.bytebuddy.description.type.TypeDescription.Generic r7) {
                /*
                    r6 = this;
                    r0 = r6
                L_0x0001:
                    net.bytebuddy.description.type.TypeList$Generic r1 = r0.getTypeArguments()
                    net.bytebuddy.description.type.TypeDescription r2 = r0.asErasure()
                    net.bytebuddy.description.type.TypeList$Generic r2 = r2.getTypeVariables()
                    r3 = 0
                L_0x000e:
                    int r4 = r1.size()
                    int r5 = r2.size()
                    int r4 = java.lang.Math.min(r4, r5)
                    if (r3 >= r4) goto L_0x0030
                    java.lang.Object r4 = r2.get(r3)
                    boolean r4 = r7.equals(r4)
                    if (r4 == 0) goto L_0x002d
                    java.lang.Object r7 = r1.get(r3)
                    net.bytebuddy.description.type.TypeDescription$Generic r7 = (net.bytebuddy.description.type.TypeDescription.Generic) r7
                    return r7
                L_0x002d:
                    int r3 = r3 + 1
                    goto L_0x000e
                L_0x0030:
                    net.bytebuddy.description.type.TypeDescription$Generic r0 = r0.getOwnerType()
                    if (r0 == 0) goto L_0x0040
                    net.bytebuddy.description.type.TypeDefinition$Sort r1 = r0.getSort()
                    boolean r1 = r1.isParameterized()
                    if (r1 != 0) goto L_0x0001
                L_0x0040:
                    net.bytebuddy.description.type.TypeDescription$Generic r7 = net.bytebuddy.description.type.TypeDescription.Generic.UNDEFINED
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.Generic.OfParameterizedType.findBindingOf(net.bytebuddy.description.type.TypeDescription$Generic):net.bytebuddy.description.type.TypeDescription$Generic");
            }

            public TypeList.Generic getUpperBounds() {
                throw new IllegalStateException("A parameterized type does not imply upper bounds: " + this);
            }

            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A parameterized type does not imply lower bounds: " + this);
            }

            public Generic getComponentType() {
                throw new IllegalStateException("A parameterized type does not imply a component type: " + this);
            }

            public TypeVariableSource getTypeVariableSource() {
                throw new IllegalStateException("A parameterized type does not imply a type variable source: " + this);
            }

            public String getSymbol() {
                throw new IllegalStateException("A parameterized type does not imply a symbol: " + this);
            }

            public String getTypeName() {
                return toString();
            }

            public String getActualName() {
                return toString();
            }

            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }

            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }

            public <T> T accept(Visitor<T> visitor) {
                return visitor.onParameterizedType(this);
            }

            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            public int hashCode() {
                int i;
                int i2 = 1;
                for (Generic hashCode : getTypeArguments()) {
                    i2 = (i2 * 31) + hashCode.hashCode();
                }
                Generic ownerType = getOwnerType();
                if (ownerType == null) {
                    i = asErasure().hashCode();
                } else {
                    i = ownerType.hashCode();
                }
                return i ^ i2;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                if (!generic.getSort().isParameterized()) {
                    return false;
                }
                Generic ownerType = getOwnerType();
                Generic ownerType2 = generic.getOwnerType();
                if (!asErasure().equals(generic.asErasure()) || ((ownerType == null && ownerType2 != null) || ((ownerType != null && !ownerType.equals(ownerType2)) || !getTypeArguments().equals(generic.getTypeArguments())))) {
                    return false;
                }
                return true;
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                RenderingDelegate.CURRENT.apply(sb, asErasure(), getOwnerType());
                TypeList.Generic<Generic> typeArguments = getTypeArguments();
                if (!typeArguments.isEmpty()) {
                    sb.append('<');
                    boolean z = false;
                    for (Generic generic : typeArguments) {
                        if (z) {
                            sb.append(", ");
                        }
                        sb.append(generic.getTypeName());
                        z = true;
                    }
                    sb.append('>');
                }
                return sb.toString();
            }

            public static class ForLoadedType extends OfParameterizedType {
                private final AnnotationReader annotationReader;
                private final ParameterizedType parameterizedType;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public ForLoadedType(ParameterizedType parameterizedType2) {
                    this(parameterizedType2, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(ParameterizedType parameterizedType2, AnnotationReader annotationReader2) {
                    this.parameterizedType = parameterizedType2;
                    this.annotationReader = annotationReader2;
                }

                public TypeList.Generic getTypeArguments() {
                    return new ParameterArgumentTypeList(this.parameterizedType.getActualTypeArguments(), this.annotationReader);
                }

                public Generic getOwnerType() {
                    Type ownerType = this.parameterizedType.getOwnerType();
                    if (ownerType == null) {
                        return Generic.UNDEFINED;
                    }
                    return TypeDefinition.Sort.describe(ownerType, this.annotationReader.ofOwnerType());
                }

                public TypeDescription asErasure() {
                    return ForLoadedType.of((Class) this.parameterizedType.getRawType());
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                public boolean represents(Type type) {
                    return this.parameterizedType == type || super.represents(type);
                }

                protected static class ParameterArgumentTypeList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] argumentType;

                    protected ParameterArgumentTypeList(Type[] typeArr, AnnotationReader annotationReader2) {
                        this.argumentType = typeArr;
                        this.annotationReader = annotationReader2;
                    }

                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.argumentType[i], this.annotationReader.ofTypeArgument(i));
                    }

                    public int size() {
                        return this.argumentType.length;
                    }
                }
            }

            public static class Latent extends OfParameterizedType {
                private final AnnotationSource annotationSource;
                private final Generic ownerType;
                private final List<? extends Generic> parameters;
                private final TypeDescription rawType;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public Latent(TypeDescription typeDescription, Generic generic, List<? extends Generic> list, AnnotationSource annotationSource2) {
                    this.rawType = typeDescription;
                    this.ownerType = generic;
                    this.parameters = list;
                    this.annotationSource = annotationSource2;
                }

                public TypeDescription asErasure() {
                    return this.rawType;
                }

                public Generic getOwnerType() {
                    return this.ownerType;
                }

                public TypeList.Generic getTypeArguments() {
                    return new TypeList.Generic.Explicit((List<? extends TypeDefinition>) this.parameters);
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }
            }

            public static class ForReifiedType extends OfParameterizedType {
                private final Generic parameterizedType;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                protected ForReifiedType(Generic generic) {
                    this.parameterizedType = generic;
                }

                public Generic getSuperClass() {
                    Generic superClass = super.getSuperClass();
                    return superClass == null ? Generic.UNDEFINED : new LazyProjection.WithResolvedErasure(superClass, Visitor.Reifying.INHERITING);
                }

                public TypeList.Generic getInterfaces() {
                    return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(super.getInterfaces(), Visitor.Reifying.INHERITING);
                }

                public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                    return new FieldList.TypeSubstituting(this, super.getDeclaredFields(), Visitor.TypeErasing.INSTANCE);
                }

                public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                    return new MethodList.TypeSubstituting(this, super.getDeclaredMethods(), Visitor.TypeErasing.INSTANCE);
                }

                public TypeList.Generic getTypeArguments() {
                    return new TypeList.Generic.ForDetachedTypes(this.parameterizedType.getTypeArguments(), Visitor.TypeErasing.INSTANCE);
                }

                public Generic getOwnerType() {
                    Generic ownerType = this.parameterizedType.getOwnerType();
                    if (ownerType == null) {
                        return Generic.UNDEFINED;
                    }
                    return (Generic) ownerType.accept(Visitor.Reifying.INHERITING);
                }

                public TypeDescription asErasure() {
                    return this.parameterizedType.asErasure();
                }

                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }

            public static class ForGenerifiedErasure extends OfParameterizedType {
                private final TypeDescription typeDescription;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                protected ForGenerifiedErasure(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public static Generic of(TypeDescription typeDescription2) {
                    return typeDescription2.isGenerified() ? new ForGenerifiedErasure(typeDescription2) : new OfNonGenericType.ForErasure(typeDescription2);
                }

                public TypeDescription asErasure() {
                    return this.typeDescription;
                }

                public TypeList.Generic getTypeArguments() {
                    return new TypeList.Generic.ForDetachedTypes(this.typeDescription.getTypeVariables(), Visitor.AnnotationStripper.INSTANCE);
                }

                public Generic getOwnerType() {
                    TypeDescription declaringType = this.typeDescription.getDeclaringType();
                    if (declaringType == null) {
                        return Generic.UNDEFINED;
                    }
                    return of(declaringType);
                }

                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }
            }
        }

        public static abstract class OfTypeVariable extends AbstractBase {
            public boolean isArray() {
                return false;
            }

            public boolean isPrimitive() {
                return false;
            }

            public TypeDefinition.Sort getSort() {
                return TypeDefinition.Sort.VARIABLE;
            }

            public TypeDescription asErasure() {
                TypeList.Generic upperBounds = getUpperBounds();
                if (upperBounds.isEmpty()) {
                    return TypeDescription.OBJECT;
                }
                return ((Generic) upperBounds.get(0)).asErasure();
            }

            public Generic getSuperClass() {
                throw new IllegalStateException("A type variable does not imply a super type definition: " + this);
            }

            public TypeList.Generic getInterfaces() {
                throw new IllegalStateException("A type variable does not imply an interface type definition: " + this);
            }

            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                throw new IllegalStateException("A type variable does not imply field definitions: " + this);
            }

            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                throw new IllegalStateException("A type variable does not imply method definitions: " + this);
            }

            public Generic getComponentType() {
                throw new IllegalStateException("A type variable does not imply a component type: " + this);
            }

            public TypeList.Generic getTypeArguments() {
                throw new IllegalStateException("A type variable does not imply type arguments: " + this);
            }

            public Generic findBindingOf(Generic generic) {
                throw new IllegalStateException("A type variable does not imply type arguments: " + this);
            }

            public TypeList.Generic getLowerBounds() {
                throw new IllegalStateException("A type variable does not imply lower bounds: " + this);
            }

            public Generic getOwnerType() {
                throw new IllegalStateException("A type variable does not imply an owner type: " + this);
            }

            public String getTypeName() {
                return toString();
            }

            public String getActualName() {
                return getSymbol();
            }

            public <T> T accept(Visitor<T> visitor) {
                return visitor.onTypeVariable(this);
            }

            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            public boolean represents(Type type) {
                return equals(TypeDefinition.Sort.describe(type));
            }

            public Iterator<TypeDefinition> iterator() {
                throw new IllegalStateException("A type variable does not imply a super type definition: " + this);
            }

            public int hashCode() {
                return getTypeVariableSource().hashCode() ^ getSymbol().hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Generic)) {
                    return false;
                }
                Generic generic = (Generic) obj;
                if (!generic.getSort().isTypeVariable() || !getSymbol().equals(generic.getSymbol()) || !getTypeVariableSource().equals(generic.getTypeVariableSource())) {
                    return false;
                }
                return true;
            }

            public String toString() {
                return getSymbol();
            }

            public static class Symbolic extends AbstractBase {
                private final AnnotationSource annotationSource;
                private final String symbol;

                public boolean isArray() {
                    return false;
                }

                public boolean isPrimitive() {
                    return false;
                }

                public Symbolic(String str, AnnotationSource annotationSource2) {
                    this.symbol = str;
                    this.annotationSource = annotationSource2;
                }

                public TypeDefinition.Sort getSort() {
                    return TypeDefinition.Sort.VARIABLE_SYMBOLIC;
                }

                public String getSymbol() {
                    return this.symbol;
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                public TypeDescription asErasure() {
                    throw new IllegalStateException("A symbolic type variable does not imply an erasure: " + this);
                }

                public TypeList.Generic getUpperBounds() {
                    throw new IllegalStateException("A symbolic type variable does not imply an upper type bound: " + this);
                }

                public TypeVariableSource getTypeVariableSource() {
                    throw new IllegalStateException("A symbolic type variable does not imply a variable source: " + this);
                }

                public Generic getSuperClass() {
                    throw new IllegalStateException("A symbolic type variable does not imply a super type definition: " + this);
                }

                public TypeList.Generic getInterfaces() {
                    throw new IllegalStateException("A symbolic type variable does not imply an interface type definition: " + this);
                }

                public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                    throw new IllegalStateException("A symbolic type variable does not imply field definitions: " + this);
                }

                public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                    throw new IllegalStateException("A symbolic type variable does not imply method definitions: " + this);
                }

                public Generic getComponentType() {
                    throw new IllegalStateException("A symbolic type variable does not imply a component type: " + this);
                }

                public TypeList.Generic getTypeArguments() {
                    throw new IllegalStateException("A symbolic type variable does not imply type arguments: " + this);
                }

                public Generic findBindingOf(Generic generic) {
                    throw new IllegalStateException("A symbolic type variable does not imply type arguments: " + this);
                }

                public TypeList.Generic getLowerBounds() {
                    throw new IllegalStateException("A symbolic type variable does not imply lower bounds: " + this);
                }

                public Generic getOwnerType() {
                    throw new IllegalStateException("A symbolic type variable does not imply an owner type: " + this);
                }

                public String getTypeName() {
                    return toString();
                }

                public String getActualName() {
                    return getSymbol();
                }

                public <T> T accept(Visitor<T> visitor) {
                    return visitor.onTypeVariable(this);
                }

                public StackSize getStackSize() {
                    return StackSize.SINGLE;
                }

                public boolean represents(Type type) {
                    if (type != null) {
                        return false;
                    }
                    throw null;
                }

                public Iterator<TypeDefinition> iterator() {
                    throw new IllegalStateException("A symbolic type variable does not imply a super type definition: " + this);
                }

                public int hashCode() {
                    return this.symbol.hashCode();
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (!(obj instanceof Generic)) {
                        return false;
                    }
                    Generic generic = (Generic) obj;
                    if (!generic.getSort().isTypeVariable() || !getSymbol().equals(generic.getSymbol())) {
                        return false;
                    }
                    return true;
                }

                public String toString() {
                    return getSymbol();
                }
            }

            public static class ForLoadedType extends OfTypeVariable {
                private final AnnotationReader annotationReader;
                private final TypeVariable<?> typeVariable;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public ForLoadedType(TypeVariable<?> typeVariable2) {
                    this(typeVariable2, AnnotationReader.NoOp.INSTANCE);
                }

                protected ForLoadedType(TypeVariable<?> typeVariable2, AnnotationReader annotationReader2) {
                    this.typeVariable = typeVariable2;
                    this.annotationReader = annotationReader2;
                }

                public TypeVariableSource getTypeVariableSource() {
                    Object genericDeclaration = this.typeVariable.getGenericDeclaration();
                    if (genericDeclaration instanceof Class) {
                        return ForLoadedType.of((Class) genericDeclaration);
                    }
                    if (genericDeclaration instanceof Method) {
                        return new MethodDescription.ForLoadedMethod((Method) genericDeclaration);
                    }
                    if (genericDeclaration instanceof Constructor) {
                        return new MethodDescription.ForLoadedConstructor((Constructor) genericDeclaration);
                    }
                    throw new IllegalStateException("Unknown declaration: " + genericDeclaration);
                }

                public TypeList.Generic getUpperBounds() {
                    return new TypeVariableBoundList(this.typeVariable.getBounds(), this.annotationReader);
                }

                public String getSymbol() {
                    return this.typeVariable.getName();
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationReader.asList();
                }

                public boolean represents(Type type) {
                    return this.typeVariable == type || super.represents(type);
                }

                protected static class TypeVariableBoundList extends TypeList.Generic.AbstractBase {
                    private final AnnotationReader annotationReader;
                    private final Type[] bound;

                    protected TypeVariableBoundList(Type[] typeArr, AnnotationReader annotationReader2) {
                        this.bound = typeArr;
                        this.annotationReader = annotationReader2;
                    }

                    public Generic get(int i) {
                        return TypeDefinition.Sort.describe(this.bound[i], this.annotationReader.ofTypeVariableBoundType(i));
                    }

                    public int size() {
                        return this.bound.length;
                    }
                }
            }

            public static class WithAnnotationOverlay extends OfTypeVariable {
                private final AnnotationSource annotationSource;
                private final Generic typeVariable;

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public WithAnnotationOverlay(Generic generic, AnnotationSource annotationSource2) {
                    this.typeVariable = generic;
                    this.annotationSource = annotationSource2;
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                public TypeList.Generic getUpperBounds() {
                    return this.typeVariable.getUpperBounds();
                }

                public TypeVariableSource getTypeVariableSource() {
                    return this.typeVariable.getTypeVariableSource();
                }

                public String getSymbol() {
                    return this.typeVariable.getSymbol();
                }
            }
        }

        public static abstract class LazyProjection extends AbstractBase {
            /* access modifiers changed from: protected */
            public abstract Generic resolve();

            public TypeDefinition.Sort getSort() {
                return resolve().getSort();
            }

            public FieldList<FieldDescription.InGenericShape> getDeclaredFields() {
                return resolve().getDeclaredFields();
            }

            public MethodList<MethodDescription.InGenericShape> getDeclaredMethods() {
                return resolve().getDeclaredMethods();
            }

            public TypeList.Generic getUpperBounds() {
                return resolve().getUpperBounds();
            }

            public TypeList.Generic getLowerBounds() {
                return resolve().getLowerBounds();
            }

            public Generic getComponentType() {
                return resolve().getComponentType();
            }

            public TypeList.Generic getTypeArguments() {
                return resolve().getTypeArguments();
            }

            public Generic findBindingOf(Generic generic) {
                return resolve().findBindingOf(generic);
            }

            public TypeVariableSource getTypeVariableSource() {
                return resolve().getTypeVariableSource();
            }

            public Generic getOwnerType() {
                return resolve().getOwnerType();
            }

            public String getTypeName() {
                return resolve().getTypeName();
            }

            public String getSymbol() {
                return resolve().getSymbol();
            }

            public String getActualName() {
                return resolve().getActualName();
            }

            public <T> T accept(Visitor<T> visitor) {
                return resolve().accept(visitor);
            }

            public StackSize getStackSize() {
                return asErasure().getStackSize();
            }

            public boolean isArray() {
                return asErasure().isArray();
            }

            public boolean isPrimitive() {
                return asErasure().isPrimitive();
            }

            public boolean represents(Type type) {
                return resolve().represents(type);
            }

            public int hashCode() {
                return resolve().hashCode();
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof TypeDefinition) && resolve().equals(obj));
            }

            public String toString() {
                return resolve().toString();
            }

            public static abstract class WithLazyNavigation extends LazyProjection {
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public Generic getSuperClass() {
                    return LazySuperClass.of(this);
                }

                public TypeList.Generic getInterfaces() {
                    return LazyInterfaceList.of(this);
                }

                public Iterator<TypeDefinition> iterator() {
                    return new TypeDefinition.SuperClassIterator(this);
                }

                protected static class LazySuperClass extends WithLazyNavigation {
                    private final LazyProjection delegate;
                    private transient /* synthetic */ Generic resolved;

                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    protected LazySuperClass(LazyProjection lazyProjection) {
                        this.delegate = lazyProjection;
                    }

                    protected static Generic of(LazyProjection lazyProjection) {
                        return lazyProjection.asErasure().getSuperClass() == null ? Generic.UNDEFINED : new LazySuperClass(lazyProjection);
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return resolve().getDeclaredAnnotations();
                    }

                    public TypeDescription asErasure() {
                        return this.delegate.asErasure().getSuperClass().asErasure();
                    }

                    /* access modifiers changed from: protected */
                    @CachedReturnPlugin.Enhance("resolved")
                    public Generic resolve() {
                        Generic superClass = this.resolved != null ? null : this.delegate.resolve().getSuperClass();
                        if (superClass == null) {
                            return this.resolved;
                        }
                        this.resolved = superClass;
                        return superClass;
                    }
                }

                protected static class LazyInterfaceType extends WithLazyNavigation {
                    private final LazyProjection delegate;
                    private final int index;
                    private final Generic rawInterface;
                    private transient /* synthetic */ Generic resolved;

                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    protected LazyInterfaceType(LazyProjection lazyProjection, int i, Generic generic) {
                        this.delegate = lazyProjection;
                        this.index = i;
                        this.rawInterface = generic;
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return resolve().getDeclaredAnnotations();
                    }

                    public TypeDescription asErasure() {
                        return this.rawInterface.asErasure();
                    }

                    /* access modifiers changed from: protected */
                    @CachedReturnPlugin.Enhance("resolved")
                    public Generic resolve() {
                        Generic generic = this.resolved != null ? null : (Generic) this.delegate.resolve().getInterfaces().get(this.index);
                        if (generic == null) {
                            return this.resolved;
                        }
                        this.resolved = generic;
                        return generic;
                    }
                }

                protected static class LazyInterfaceList extends TypeList.Generic.AbstractBase {
                    private final LazyProjection delegate;
                    private final TypeList.Generic rawInterfaces;

                    protected LazyInterfaceList(LazyProjection lazyProjection, TypeList.Generic generic) {
                        this.delegate = lazyProjection;
                        this.rawInterfaces = generic;
                    }

                    protected static TypeList.Generic of(LazyProjection lazyProjection) {
                        return new LazyInterfaceList(lazyProjection, lazyProjection.asErasure().getInterfaces());
                    }

                    public Generic get(int i) {
                        return new LazyInterfaceType(this.delegate, i, (Generic) this.rawInterfaces.get(i));
                    }

                    public int size() {
                        return this.rawInterfaces.size();
                    }
                }

                protected static abstract class OfAnnotatedElement extends WithLazyNavigation {
                    /* access modifiers changed from: protected */
                    public abstract AnnotationReader getAnnotationReader();

                    protected OfAnnotatedElement() {
                    }

                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return getAnnotationReader().asList();
                    }
                }
            }

            public static abstract class WithEagerNavigation extends LazyProjection {
                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public Generic getSuperClass() {
                    return resolve().getSuperClass();
                }

                public TypeList.Generic getInterfaces() {
                    return resolve().getInterfaces();
                }

                public Iterator<TypeDefinition> iterator() {
                    return resolve().iterator();
                }

                protected static abstract class OfAnnotatedElement extends WithEagerNavigation {
                    /* access modifiers changed from: protected */
                    public abstract AnnotationReader getAnnotationReader();

                    protected OfAnnotatedElement() {
                    }

                    public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                        return super.getComponentType();
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return getAnnotationReader().asList();
                    }
                }
            }

            public static class ForLoadedSuperClass extends WithLazyNavigation.OfAnnotatedElement {
                private transient /* synthetic */ Generic resolved;
                private final Class<?> type;

                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public ForLoadedSuperClass(Class<?> cls) {
                    this.type = cls;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public Generic resolve() {
                    Generic generic;
                    if (this.resolved != null) {
                        generic = null;
                    } else {
                        Type genericSuperclass = this.type.getGenericSuperclass();
                        if (genericSuperclass == null) {
                            generic = Generic.UNDEFINED;
                        } else {
                            generic = TypeDefinition.Sort.describe(genericSuperclass, getAnnotationReader());
                        }
                    }
                    if (generic == null) {
                        return this.resolved;
                    }
                    this.resolved = generic;
                    return generic;
                }

                public TypeDescription asErasure() {
                    Class<? super Object> superclass = this.type.getSuperclass();
                    if (superclass == null) {
                        return TypeDescription.UNDEFINED;
                    }
                    return ForLoadedType.of(superclass);
                }

                /* access modifiers changed from: protected */
                public AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveSuperClassType(this.type);
                }
            }

            public static class ForLoadedFieldType extends WithEagerNavigation.OfAnnotatedElement {
                private final Field field;
                private transient /* synthetic */ Generic resolved;

                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public ForLoadedFieldType(Field field2) {
                    this.field = field2;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public Generic resolve() {
                    Generic describe = this.resolved != null ? null : TypeDefinition.Sort.describe(this.field.getGenericType(), getAnnotationReader());
                    if (describe == null) {
                        return this.resolved;
                    }
                    this.resolved = describe;
                    return describe;
                }

                public TypeDescription asErasure() {
                    return ForLoadedType.of(this.field.getType());
                }

                /* access modifiers changed from: protected */
                public AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveFieldType(this.field);
                }
            }

            public static class ForLoadedReturnType extends WithEagerNavigation.OfAnnotatedElement {
                private final Method method;
                private transient /* synthetic */ Generic resolved;

                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public ForLoadedReturnType(Method method2) {
                    this.method = method2;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public Generic resolve() {
                    Generic describe = this.resolved != null ? null : TypeDefinition.Sort.describe(this.method.getGenericReturnType(), getAnnotationReader());
                    if (describe == null) {
                        return this.resolved;
                    }
                    this.resolved = describe;
                    return describe;
                }

                public TypeDescription asErasure() {
                    return ForLoadedType.of(this.method.getReturnType());
                }

                /* access modifiers changed from: protected */
                public AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveReturnType(this.method);
                }
            }

            public static class OfConstructorParameter extends WithEagerNavigation.OfAnnotatedElement {
                private final Constructor<?> constructor;
                private transient /* synthetic */ Generic delegate;
                private final Class<?>[] erasure;
                private final int index;

                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public OfConstructorParameter(Constructor<?> constructor2, int i, Class<?>[] clsArr) {
                    this.constructor = constructor2;
                    this.index = i;
                    this.erasure = clsArr;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("delegate")
                public Generic resolve() {
                    Generic generic;
                    if (this.delegate != null) {
                        generic = null;
                    } else {
                        Type[] genericParameterTypes = this.constructor.getGenericParameterTypes();
                        Class<?>[] clsArr = this.erasure;
                        if (clsArr.length == genericParameterTypes.length) {
                            generic = TypeDefinition.Sort.describe(genericParameterTypes[this.index], getAnnotationReader());
                        } else {
                            generic = OfNonGenericType.ForLoadedType.of(clsArr[this.index]);
                        }
                    }
                    if (generic == null) {
                        return this.delegate;
                    }
                    this.delegate = generic;
                    return generic;
                }

                public TypeDescription asErasure() {
                    return ForLoadedType.of(this.erasure[this.index]);
                }

                /* access modifiers changed from: protected */
                public AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveParameterType(this.constructor, this.index);
                }
            }

            public static class OfMethodParameter extends WithEagerNavigation.OfAnnotatedElement {
                private final Class<?>[] erasure;
                private final int index;
                private final Method method;
                private transient /* synthetic */ Generic resolved;

                public /* bridge */ /* synthetic */ AnnotationList getDeclaredAnnotations() {
                    return super.getDeclaredAnnotations();
                }

                public OfMethodParameter(Method method2, int i, Class<?>[] clsArr) {
                    this.method = method2;
                    this.index = i;
                    this.erasure = clsArr;
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public Generic resolve() {
                    Generic generic;
                    if (this.resolved != null) {
                        generic = null;
                    } else {
                        Type[] genericParameterTypes = this.method.getGenericParameterTypes();
                        Class<?>[] clsArr = this.erasure;
                        if (clsArr.length == genericParameterTypes.length) {
                            generic = TypeDefinition.Sort.describe(genericParameterTypes[this.index], getAnnotationReader());
                        } else {
                            generic = OfNonGenericType.ForLoadedType.of(clsArr[this.index]);
                        }
                    }
                    if (generic == null) {
                        return this.resolved;
                    }
                    this.resolved = generic;
                    return generic;
                }

                public TypeDescription asErasure() {
                    return ForLoadedType.of(this.erasure[this.index]);
                }

                /* access modifiers changed from: protected */
                public AnnotationReader getAnnotationReader() {
                    return AnnotationReader.DISPATCHER.resolveParameterType(this.method, this.index);
                }
            }

            public static class WithResolvedErasure extends WithEagerNavigation {
                private final AnnotationSource annotationSource;
                private final Generic delegate;
                private transient /* synthetic */ Generic resolved;
                private final Visitor<? extends Generic> visitor;

                public WithResolvedErasure(Generic generic, Visitor<? extends Generic> visitor2) {
                    this(generic, visitor2, generic);
                }

                public WithResolvedErasure(Generic generic, Visitor<? extends Generic> visitor2, AnnotationSource annotationSource2) {
                    this.delegate = generic;
                    this.visitor = visitor2;
                    this.annotationSource = annotationSource2;
                }

                public AnnotationList getDeclaredAnnotations() {
                    return this.annotationSource.getDeclaredAnnotations();
                }

                public TypeDescription asErasure() {
                    return this.delegate.asErasure();
                }

                /* access modifiers changed from: protected */
                @CachedReturnPlugin.Enhance("resolved")
                public Generic resolve() {
                    Generic generic = this.resolved != null ? null : (Generic) this.delegate.accept(this.visitor);
                    if (generic == null) {
                        return this.resolved;
                    }
                    this.resolved = generic;
                    return generic;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class Builder {
            private static final Type UNDEFINED = null;
            protected final List<? extends AnnotationDescription> annotations;

            /* access modifiers changed from: protected */
            public abstract Builder doAnnotate(List<? extends AnnotationDescription> list);

            /* access modifiers changed from: protected */
            public abstract Generic doBuild();

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.annotations.equals(((Builder) obj).annotations);
            }

            public int hashCode() {
                return 527 + this.annotations.hashCode();
            }

            protected Builder(List<? extends AnnotationDescription> list) {
                this.annotations = list;
            }

            public static Builder rawType(Class<?> cls) {
                return rawType(ForLoadedType.of(cls));
            }

            public static Builder rawType(TypeDescription typeDescription) {
                return new OfNonGenericType(typeDescription);
            }

            public static Builder rawType(Class<?> cls, Generic generic) {
                return rawType(ForLoadedType.of(cls), generic);
            }

            public static Builder rawType(TypeDescription typeDescription, Generic generic) {
                TypeDescription declaringType = typeDescription.getDeclaringType();
                if (declaringType == null && generic != null) {
                    throw new IllegalArgumentException(typeDescription + " does not have a declaring type: " + generic);
                } else if (declaringType == null || (generic != null && declaringType.equals(generic.asErasure()))) {
                    return new OfNonGenericType(typeDescription, generic);
                } else {
                    throw new IllegalArgumentException(generic + " is not the declaring type of " + typeDescription);
                }
            }

            public static Generic unboundWildcard() {
                return unboundWildcard((Collection<? extends AnnotationDescription>) Collections.emptySet());
            }

            public static Generic unboundWildcard(Annotation... annotationArr) {
                return unboundWildcard((List<? extends Annotation>) Arrays.asList(annotationArr));
            }

            public static Generic unboundWildcard(List<? extends Annotation> list) {
                return unboundWildcard((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public static Generic unboundWildcard(AnnotationDescription... annotationDescriptionArr) {
                return unboundWildcard((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public static Generic unboundWildcard(Collection<? extends AnnotationDescription> collection) {
                return OfWildcardType.Latent.unbounded(new AnnotationSource.Explicit((List<? extends AnnotationDescription>) new ArrayList(collection)));
            }

            public static Builder typeVariable(String str) {
                return new OfTypeVariable(str);
            }

            public static Builder parameterizedType(Class<?> cls, Type... typeArr) {
                return parameterizedType(cls, (List<? extends Type>) Arrays.asList(typeArr));
            }

            public static Builder parameterizedType(Class<?> cls, List<? extends Type> list) {
                return parameterizedType(cls, UNDEFINED, list);
            }

            public static Builder parameterizedType(Class<?> cls, Type type, List<? extends Type> list) {
                Generic generic;
                TypeDescription of = ForLoadedType.of(cls);
                if (type == null) {
                    generic = null;
                } else {
                    generic = TypeDefinition.Sort.describe(type);
                }
                return parameterizedType(of, generic, (Collection<? extends TypeDefinition>) new TypeList.Generic.ForLoadedTypes(list));
            }

            public static Builder parameterizedType(TypeDescription typeDescription, TypeDefinition... typeDefinitionArr) {
                return parameterizedType(typeDescription, (Collection<? extends TypeDefinition>) Arrays.asList(typeDefinitionArr));
            }

            public static Builder parameterizedType(TypeDescription typeDescription, Collection<? extends TypeDefinition> collection) {
                return parameterizedType(typeDescription, Generic.UNDEFINED, collection);
            }

            public static Builder parameterizedType(TypeDescription typeDescription, Generic generic, Collection<? extends TypeDefinition> collection) {
                TypeDescription declaringType = typeDescription.getDeclaringType();
                if (generic == null && declaringType != null && typeDescription.isStatic()) {
                    generic = declaringType.asGenericType();
                }
                if (!typeDescription.represents(TargetType.class)) {
                    if (!typeDescription.isGenerified()) {
                        throw new IllegalArgumentException(typeDescription + " is not a parameterized type");
                    } else if (generic == null && declaringType != null && !typeDescription.isStatic()) {
                        throw new IllegalArgumentException(typeDescription + " requires an owner type");
                    } else if (generic != null && !generic.asErasure().equals(declaringType)) {
                        throw new IllegalArgumentException(generic + " does not represent required owner for " + typeDescription);
                    } else if (generic != null && (typeDescription.isStatic() ^ generic.getSort().isNonGeneric())) {
                        throw new IllegalArgumentException(generic + " does not define the correct parameters for owning " + typeDescription);
                    } else if (typeDescription.getTypeVariables().size() != collection.size()) {
                        throw new IllegalArgumentException(collection + " does not contain number of required parameters for " + typeDescription);
                    }
                }
                return new OfParameterizedType(typeDescription, generic, new TypeList.Generic.Explicit((List<? extends TypeDefinition>) new ArrayList(collection)));
            }

            public Generic asWildcardUpperBound() {
                return asWildcardUpperBound((Collection<? extends AnnotationDescription>) Collections.emptySet());
            }

            public Generic asWildcardUpperBound(Annotation... annotationArr) {
                return asWildcardUpperBound((List<? extends Annotation>) Arrays.asList(annotationArr));
            }

            public Generic asWildcardUpperBound(List<? extends Annotation> list) {
                return asWildcardUpperBound((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Generic asWildcardUpperBound(AnnotationDescription... annotationDescriptionArr) {
                return asWildcardUpperBound((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Generic asWildcardUpperBound(Collection<? extends AnnotationDescription> collection) {
                return OfWildcardType.Latent.boundedAbove(build(), new AnnotationSource.Explicit((List<? extends AnnotationDescription>) new ArrayList(collection)));
            }

            public Generic asWildcardLowerBound() {
                return asWildcardLowerBound((Collection<? extends AnnotationDescription>) Collections.emptySet());
            }

            public Generic asWildcardLowerBound(Annotation... annotationArr) {
                return asWildcardLowerBound((List<? extends Annotation>) Arrays.asList(annotationArr));
            }

            public Generic asWildcardLowerBound(List<? extends Annotation> list) {
                return asWildcardLowerBound((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Generic asWildcardLowerBound(AnnotationDescription... annotationDescriptionArr) {
                return asWildcardLowerBound((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Generic asWildcardLowerBound(Collection<? extends AnnotationDescription> collection) {
                return OfWildcardType.Latent.boundedBelow(build(), new AnnotationSource.Explicit((List<? extends AnnotationDescription>) new ArrayList(collection)));
            }

            public Builder asArray() {
                return asArray(1);
            }

            public Builder asArray(int i) {
                if (i >= 1) {
                    Generic build = build();
                    while (true) {
                        i--;
                        if (i <= 0) {
                            return new OfGenericArrayType(build);
                        }
                        build = new OfGenericArray.Latent(build, AnnotationSource.Empty.INSTANCE);
                    }
                } else {
                    throw new IllegalArgumentException("Cannot define an array of a non-positive arity: " + i);
                }
            }

            public Builder annotate(Annotation... annotationArr) {
                return annotate((List<? extends Annotation>) Arrays.asList(annotationArr));
            }

            public Builder annotate(List<? extends Annotation> list) {
                return annotate((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Builder annotate(AnnotationDescription... annotationDescriptionArr) {
                return annotate((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Builder annotate(Collection<? extends AnnotationDescription> collection) {
                return doAnnotate(new ArrayList(collection));
            }

            public Generic build() {
                return doBuild();
            }

            public Generic build(Annotation... annotationArr) {
                return build((List<? extends Annotation>) Arrays.asList(annotationArr));
            }

            public Generic build(List<? extends Annotation> list) {
                return build((Collection<? extends AnnotationDescription>) new AnnotationList.ForLoadedAnnotations(list));
            }

            public Generic build(AnnotationDescription... annotationDescriptionArr) {
                return build((Collection<? extends AnnotationDescription>) Arrays.asList(annotationDescriptionArr));
            }

            public Generic build(Collection<? extends AnnotationDescription> collection) {
                return doAnnotate(new ArrayList(collection)).doBuild();
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class OfNonGenericType extends Builder {
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.REVERSE_NULLABILITY)
                private final Generic ownerType;
                private final TypeDescription typeDescription;

                /* JADX WARNING: Removed duplicated region for block: B:22:0x0039 A[RETURN] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public boolean equals(java.lang.Object r5) {
                    /*
                        r4 = this;
                        boolean r0 = super.equals(r5)
                        r1 = 0
                        if (r0 != 0) goto L_0x0008
                        return r1
                    L_0x0008:
                        r0 = 1
                        if (r4 != r5) goto L_0x000c
                        return r0
                    L_0x000c:
                        if (r5 != 0) goto L_0x000f
                        return r1
                    L_0x000f:
                        java.lang.Class r2 = r4.getClass()
                        java.lang.Class r3 = r5.getClass()
                        if (r2 == r3) goto L_0x001a
                        return r1
                    L_0x001a:
                        net.bytebuddy.description.type.TypeDescription r2 = r4.typeDescription
                        net.bytebuddy.description.type.TypeDescription$Generic$Builder$OfNonGenericType r5 = (net.bytebuddy.description.type.TypeDescription.Generic.Builder.OfNonGenericType) r5
                        net.bytebuddy.description.type.TypeDescription r3 = r5.typeDescription
                        boolean r2 = r2.equals(r3)
                        if (r2 != 0) goto L_0x0027
                        return r1
                    L_0x0027:
                        net.bytebuddy.description.type.TypeDescription$Generic r2 = r4.ownerType
                        net.bytebuddy.description.type.TypeDescription$Generic r5 = r5.ownerType
                        if (r5 == 0) goto L_0x0036
                        if (r2 == 0) goto L_0x0038
                        boolean r5 = r2.equals(r5)
                        if (r5 != 0) goto L_0x0039
                        return r1
                    L_0x0036:
                        if (r2 == 0) goto L_0x0039
                    L_0x0038:
                        return r1
                    L_0x0039:
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.Generic.Builder.OfNonGenericType.equals(java.lang.Object):boolean");
                }

                public int hashCode() {
                    int hashCode = ((super.hashCode() * 31) + this.typeDescription.hashCode()) * 31;
                    Generic generic = this.ownerType;
                    return generic != null ? hashCode + generic.hashCode() : hashCode;
                }

                protected OfNonGenericType(TypeDescription typeDescription2) {
                    this(typeDescription2, typeDescription2.getDeclaringType());
                }

                /* JADX WARNING: Illegal instructions before constructor call */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                private OfNonGenericType(net.bytebuddy.description.type.TypeDescription r1, net.bytebuddy.description.type.TypeDescription r2) {
                    /*
                        r0 = this;
                        if (r2 != 0) goto L_0x0005
                        net.bytebuddy.description.type.TypeDescription$Generic r2 = net.bytebuddy.description.type.TypeDescription.Generic.UNDEFINED
                        goto L_0x0009
                    L_0x0005:
                        net.bytebuddy.description.type.TypeDescription$Generic r2 = r2.asGenericType()
                    L_0x0009:
                        r0.<init>((net.bytebuddy.description.type.TypeDescription) r1, (net.bytebuddy.description.type.TypeDescription.Generic) r2)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.Generic.Builder.OfNonGenericType.<init>(net.bytebuddy.description.type.TypeDescription, net.bytebuddy.description.type.TypeDescription):void");
                }

                protected OfNonGenericType(TypeDescription typeDescription2, Generic generic) {
                    this(typeDescription2, generic, Collections.emptyList());
                }

                protected OfNonGenericType(TypeDescription typeDescription2, Generic generic, List<? extends AnnotationDescription> list) {
                    super(list);
                    this.ownerType = generic;
                    this.typeDescription = typeDescription2;
                }

                /* access modifiers changed from: protected */
                public Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfNonGenericType(this.typeDescription, this.ownerType, CompoundList.of(this.annotations, (List) list));
                }

                /* access modifiers changed from: protected */
                public Generic doBuild() {
                    if (!this.typeDescription.represents(Void.TYPE) || this.annotations.isEmpty()) {
                        return new OfNonGenericType.Latent(this.typeDescription, this.ownerType, (AnnotationSource) new AnnotationSource.Explicit((List<? extends AnnotationDescription>) this.annotations));
                    }
                    throw new IllegalArgumentException("The void non-type cannot be annotated");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class OfParameterizedType extends Builder {
                private final Generic ownerType;
                private final List<? extends Generic> parameterTypes;
                private final TypeDescription rawType;

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
                    OfParameterizedType ofParameterizedType = (OfParameterizedType) obj;
                    return this.rawType.equals(ofParameterizedType.rawType) && this.ownerType.equals(ofParameterizedType.ownerType) && this.parameterTypes.equals(ofParameterizedType.parameterTypes);
                }

                public int hashCode() {
                    return (((((super.hashCode() * 31) + this.rawType.hashCode()) * 31) + this.ownerType.hashCode()) * 31) + this.parameterTypes.hashCode();
                }

                protected OfParameterizedType(TypeDescription typeDescription, Generic generic, List<? extends Generic> list) {
                    this(typeDescription, generic, list, Collections.emptyList());
                }

                protected OfParameterizedType(TypeDescription typeDescription, Generic generic, List<? extends Generic> list, List<? extends AnnotationDescription> list2) {
                    super(list2);
                    this.rawType = typeDescription;
                    this.ownerType = generic;
                    this.parameterTypes = list;
                }

                /* access modifiers changed from: protected */
                public Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfParameterizedType(this.rawType, this.ownerType, this.parameterTypes, CompoundList.of(this.annotations, (List) list));
                }

                /* access modifiers changed from: protected */
                public Generic doBuild() {
                    return new OfParameterizedType.Latent(this.rawType, this.ownerType, this.parameterTypes, new AnnotationSource.Explicit((List<? extends AnnotationDescription>) this.annotations));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class OfGenericArrayType extends Builder {
                private final Generic componentType;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.componentType.equals(((OfGenericArrayType) obj).componentType);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.componentType.hashCode();
                }

                protected OfGenericArrayType(Generic generic) {
                    this(generic, Collections.emptyList());
                }

                protected OfGenericArrayType(Generic generic, List<? extends AnnotationDescription> list) {
                    super(list);
                    this.componentType = generic;
                }

                /* access modifiers changed from: protected */
                public Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfGenericArrayType(this.componentType, CompoundList.of(this.annotations, (List) list));
                }

                /* access modifiers changed from: protected */
                public Generic doBuild() {
                    return new OfGenericArray.Latent(this.componentType, new AnnotationSource.Explicit((List<? extends AnnotationDescription>) this.annotations));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class OfTypeVariable extends Builder {
                private final String symbol;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.symbol.equals(((OfTypeVariable) obj).symbol);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.symbol.hashCode();
                }

                protected OfTypeVariable(String str) {
                    this(str, Collections.emptyList());
                }

                protected OfTypeVariable(String str, List<? extends AnnotationDescription> list) {
                    super(list);
                    this.symbol = str;
                }

                /* access modifiers changed from: protected */
                public Builder doAnnotate(List<? extends AnnotationDescription> list) {
                    return new OfTypeVariable(this.symbol, CompoundList.of(this.annotations, (List) list));
                }

                /* access modifiers changed from: protected */
                public Generic doBuild() {
                    return new OfTypeVariable.Symbolic(this.symbol, new AnnotationSource.Explicit((List<? extends AnnotationDescription>) this.annotations));
                }
            }
        }
    }

    public static abstract class AbstractBase extends TypeVariableSource.AbstractBase implements TypeDescription {
        public static final boolean RAW_TYPES;

        public TypeDescription asErasure() {
            return this;
        }

        static {
            boolean z;
            try {
                z = Boolean.parseBoolean((String) AccessController.doPrivileged(new GetSystemPropertyAction(TypeDefinition.RAW_TYPES_PROPERTY)));
            } catch (Exception unused) {
                z = false;
            }
            RAW_TYPES = z;
        }

        private static boolean isAssignable(TypeDescription typeDescription, TypeDescription typeDescription2) {
            if (typeDescription.equals(typeDescription2)) {
                return true;
            }
            if (typeDescription2.isArray()) {
                if (typeDescription.isArray()) {
                    return isAssignable(typeDescription.getComponentType(), typeDescription2.getComponentType());
                }
                if (typeDescription.represents(Object.class) || ARRAY_INTERFACES.contains(typeDescription.asGenericType())) {
                    return true;
                }
                return false;
            } else if (typeDescription.represents(Object.class)) {
                return !typeDescription2.isPrimitive();
            } else {
                Generic superClass = typeDescription2.getSuperClass();
                if (superClass != null && typeDescription.isAssignableFrom(superClass.asErasure())) {
                    return true;
                }
                if (typeDescription.isInterface()) {
                    for (TypeDescription isAssignableFrom : typeDescription2.getInterfaces().asErasures()) {
                        if (typeDescription.isAssignableFrom(isAssignableFrom)) {
                            return true;
                        }
                    }
                }
                return false;
            }
        }

        public boolean isAssignableFrom(Class<?> cls) {
            return isAssignableFrom(ForLoadedType.of(cls));
        }

        public boolean isAssignableFrom(TypeDescription typeDescription) {
            return isAssignable(this, typeDescription);
        }

        public boolean isAssignableTo(Class<?> cls) {
            return isAssignableTo(ForLoadedType.of(cls));
        }

        public boolean isAssignableTo(TypeDescription typeDescription) {
            return isAssignable(typeDescription, this);
        }

        public boolean isInHierarchyWith(Class<?> cls) {
            return isAssignableTo(cls) || isAssignableFrom(cls);
        }

        public boolean isInHierarchyWith(TypeDescription typeDescription) {
            return isAssignableTo(typeDescription) || isAssignableFrom(typeDescription);
        }

        public Generic asGenericType() {
            return new Generic.OfNonGenericType.ForErasure(this);
        }

        public TypeDefinition.Sort getSort() {
            return TypeDefinition.Sort.NON_GENERIC;
        }

        public boolean isInstance(Object obj) {
            return isAssignableFrom(obj.getClass());
        }

        public boolean isAnnotationValue(Object obj) {
            if ((represents(Class.class) && (obj instanceof TypeDescription)) || (((obj instanceof AnnotationDescription) && ((AnnotationDescription) obj).getAnnotationType().equals(this)) || (((obj instanceof EnumerationDescription) && ((EnumerationDescription) obj).getEnumerationType().equals(this)) || ((represents(String.class) && (obj instanceof String)) || ((represents(Boolean.TYPE) && (obj instanceof Boolean)) || ((represents(Byte.TYPE) && (obj instanceof Byte)) || ((represents(Short.TYPE) && (obj instanceof Short)) || ((represents(Character.TYPE) && (obj instanceof Character)) || ((represents(Integer.TYPE) && (obj instanceof Integer)) || ((represents(Long.TYPE) && (obj instanceof Long)) || ((represents(Float.TYPE) && (obj instanceof Float)) || ((represents(Double.TYPE) && (obj instanceof Double)) || ((represents(String[].class) && (obj instanceof String[])) || ((represents(boolean[].class) && (obj instanceof boolean[])) || ((represents(byte[].class) && (obj instanceof byte[])) || ((represents(short[].class) && (obj instanceof short[])) || ((represents(char[].class) && (obj instanceof char[])) || ((represents(int[].class) && (obj instanceof int[])) || ((represents(long[].class) && (obj instanceof long[])) || ((represents(float[].class) && (obj instanceof float[])) || ((represents(double[].class) && (obj instanceof double[])) || (represents(Class[].class) && (obj instanceof TypeDescription[]))))))))))))))))))))))) {
                return true;
            }
            if (isAssignableTo((Class<?>) Annotation[].class) && (obj instanceof AnnotationDescription[])) {
                for (AnnotationDescription annotationType : (AnnotationDescription[]) obj) {
                    if (!annotationType.getAnnotationType().equals(getComponentType())) {
                        return false;
                    }
                }
                return true;
            } else if (!isAssignableTo((Class<?>) Enum[].class) || !(obj instanceof EnumerationDescription[])) {
                return false;
            } else {
                for (EnumerationDescription enumerationType : (EnumerationDescription[]) obj) {
                    if (!enumerationType.getEnumerationType().equals(getComponentType())) {
                        return false;
                    }
                }
                return true;
            }
        }

        public String getInternalName() {
            return getName().replace('.', '/');
        }

        public int getActualModifiers(boolean z) {
            int i;
            int modifiers = getModifiers() | (getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Deprecated.class) ? 131072 : 0);
            if (isPrivate()) {
                i = modifiers & -11;
            } else {
                i = isProtected() ? (modifiers & -13) | 1 : modifiers & -9;
            }
            return z ? i | 32 : i;
        }

        /* JADX WARNING: Removed duplicated region for block: B:26:0x0087 A[Catch:{ GenericSignatureFormatError -> 0x00b4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00ac A[Catch:{ GenericSignatureFormatError -> 0x00b4 }] */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x00b1 A[Catch:{ GenericSignatureFormatError -> 0x00b4 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String getGenericSignature() {
            /*
                r8 = this;
                net.bytebuddy.jar.asm.signature.SignatureWriter r0 = new net.bytebuddy.jar.asm.signature.SignatureWriter     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r0.<init>()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeList$Generic r1 = r8.getTypeVariables()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                java.util.Iterator r1 = r1.iterator()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r2 = 1
                r3 = 0
                r4 = 0
            L_0x0010:
                boolean r5 = r1.hasNext()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r5 == 0) goto L_0x0055
                java.lang.Object r4 = r1.next()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeDescription$Generic r4 = (net.bytebuddy.description.type.TypeDescription.Generic) r4     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                java.lang.String r5 = r4.getSymbol()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r0.visitFormalTypeParameter(r5)     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeList$Generic r4 = r4.getUpperBounds()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                java.util.Iterator r4 = r4.iterator()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
            L_0x002b:
                boolean r5 = r4.hasNext()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r5 == 0) goto L_0x0053
                java.lang.Object r5 = r4.next()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeDescription$Generic r5 = (net.bytebuddy.description.type.TypeDescription.Generic) r5     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor r6 = new net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeDescription r7 = r5.asErasure()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                boolean r7 = r7.isInterface()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r7 == 0) goto L_0x0048
                net.bytebuddy.jar.asm.signature.SignatureVisitor r7 = r0.visitInterfaceBound()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                goto L_0x004c
            L_0x0048:
                net.bytebuddy.jar.asm.signature.SignatureVisitor r7 = r0.visitClassBound()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
            L_0x004c:
                r6.<init>(r7)     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r5.accept(r6)     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                goto L_0x002b
            L_0x0053:
                r4 = 1
                goto L_0x0010
            L_0x0055:
                net.bytebuddy.description.type.TypeDescription$Generic r1 = r8.getSuperClass()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r1 != 0) goto L_0x005d
                net.bytebuddy.description.type.TypeDescription$Generic r1 = net.bytebuddy.description.type.TypeDescription.Generic.OBJECT     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
            L_0x005d:
                net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor r5 = new net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.jar.asm.signature.SignatureVisitor r6 = r0.visitSuperclass()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r5.<init>(r6)     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r1.accept(r5)     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r4 != 0) goto L_0x0078
                net.bytebuddy.description.type.TypeDefinition$Sort r1 = r1.getSort()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                boolean r1 = r1.isNonGeneric()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r1 != 0) goto L_0x0076
                goto L_0x0078
            L_0x0076:
                r1 = 0
                goto L_0x0079
            L_0x0078:
                r1 = 1
            L_0x0079:
                net.bytebuddy.description.type.TypeList$Generic r4 = r8.getInterfaces()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                java.util.Iterator r4 = r4.iterator()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
            L_0x0081:
                boolean r5 = r4.hasNext()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r5 == 0) goto L_0x00aa
                java.lang.Object r5 = r4.next()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeDescription$Generic r5 = (net.bytebuddy.description.type.TypeDescription.Generic) r5     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor r6 = new net.bytebuddy.description.type.TypeDescription$Generic$Visitor$ForSignatureVisitor     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                net.bytebuddy.jar.asm.signature.SignatureVisitor r7 = r0.visitInterface()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r6.<init>(r7)     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                r5.accept(r6)     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r1 != 0) goto L_0x00a8
                net.bytebuddy.description.type.TypeDefinition$Sort r1 = r5.getSort()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                boolean r1 = r1.isNonGeneric()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                if (r1 != 0) goto L_0x00a6
                goto L_0x00a8
            L_0x00a6:
                r1 = 0
                goto L_0x0081
            L_0x00a8:
                r1 = 1
                goto L_0x0081
            L_0x00aa:
                if (r1 == 0) goto L_0x00b1
                java.lang.String r0 = r0.toString()     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
                goto L_0x00b3
            L_0x00b1:
                java.lang.String r0 = NON_GENERIC_SIGNATURE     // Catch:{ GenericSignatureFormatError -> 0x00b4 }
            L_0x00b3:
                return r0
            L_0x00b4:
                java.lang.String r0 = NON_GENERIC_SIGNATURE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.AbstractBase.getGenericSignature():java.lang.String");
        }

        public boolean isSamePackage(TypeDescription typeDescription) {
            PackageDescription packageDescription = getPackage();
            PackageDescription packageDescription2 = typeDescription.getPackage();
            if (packageDescription == null || packageDescription2 == null) {
                return packageDescription == packageDescription2;
            }
            return packageDescription.equals(packageDescription2);
        }

        public boolean isVisibleTo(TypeDescription typeDescription) {
            return isPrimitive() || (!isArray() ? isPublic() || isProtected() || isSamePackage(typeDescription) : getComponentType().isVisibleTo(typeDescription));
        }

        public boolean isAccessibleTo(TypeDescription typeDescription) {
            return isPrimitive() || (!isArray() ? isPublic() || isSamePackage(typeDescription) : getComponentType().isVisibleTo(typeDescription));
        }

        public AnnotationList getInheritedAnnotations() {
            Generic superClass = getSuperClass();
            AnnotationList<AnnotationDescription> declaredAnnotations = getDeclaredAnnotations();
            if (superClass == null) {
                return declaredAnnotations;
            }
            HashSet hashSet = new HashSet();
            for (AnnotationDescription annotationType : declaredAnnotations) {
                hashSet.add(annotationType.getAnnotationType());
            }
            return new AnnotationList.Explicit((List<? extends AnnotationDescription>) CompoundList.of(declaredAnnotations, (AnnotationList<AnnotationDescription>) superClass.asErasure().getInheritedAnnotations().inherited(hashSet)));
        }

        public String getActualName() {
            if (!isArray()) {
                return getName();
            }
            TypeDescription typeDescription = this;
            int i = 0;
            do {
                i++;
                typeDescription = typeDescription.getComponentType();
            } while (typeDescription.isArray());
            StringBuilder sb = new StringBuilder();
            sb.append(typeDescription.getActualName());
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        public boolean isPrimitiveWrapper() {
            return represents(Boolean.class) || represents(Byte.class) || represents(Short.class) || represents(Character.class) || represents(Integer.class) || represents(Long.class) || represents(Float.class) || represents(Double.class);
        }

        public boolean isAnnotationReturnType() {
            return isPrimitive() || represents(String.class) || (isAssignableTo((Class<?>) Enum.class) && !represents(Enum.class)) || ((isAssignableTo((Class<?>) Annotation.class) && !represents(Annotation.class)) || represents(Class.class) || (isArray() && !getComponentType().isArray() && getComponentType().isAnnotationReturnType()));
        }

        public boolean isAnnotationValue() {
            return isPrimitive() || represents(String.class) || isAssignableTo((Class<?>) TypeDescription.class) || isAssignableTo((Class<?>) AnnotationDescription.class) || isAssignableTo((Class<?>) EnumerationDescription.class) || (isArray() && !getComponentType().isArray() && getComponentType().isAnnotationValue());
        }

        public boolean represents(Type type) {
            return equals(TypeDefinition.Sort.describe(type));
        }

        public String getTypeName() {
            return getName();
        }

        public TypeVariableSource getEnclosingSource() {
            MethodDescription.InDefinedShape enclosingMethod = getEnclosingMethod();
            if (enclosingMethod == null) {
                return isStatic() ? TypeVariableSource.UNDEFINED : getEnclosingType();
            }
            return enclosingMethod;
        }

        public <T> T accept(TypeVariableSource.Visitor<T> visitor) {
            return visitor.onType(this);
        }

        public boolean isPackageType() {
            return getSimpleName().equals(PackageDescription.PACKAGE_CLASS_NAME);
        }

        public boolean isGenerified() {
            TypeDescription declaringType;
            if (!getTypeVariables().isEmpty()) {
                return true;
            }
            if (!isStatic() && (declaringType = getDeclaringType()) != null && declaringType.isGenerified()) {
                return true;
            }
            return false;
        }

        public int getInnerClassCount() {
            TypeDescription declaringType;
            if (!isStatic() && (declaringType = getDeclaringType()) != null) {
                return declaringType.getInnerClassCount() + 1;
            }
            return 0;
        }

        public boolean isInnerClass() {
            return !isStatic() && isNestedClass();
        }

        public boolean isNestedClass() {
            return getDeclaringType() != null;
        }

        public TypeDescription asBoxed() {
            if (represents(Boolean.TYPE)) {
                return ForLoadedType.of(Boolean.class);
            }
            if (represents(Byte.TYPE)) {
                return ForLoadedType.of(Byte.class);
            }
            if (represents(Short.TYPE)) {
                return ForLoadedType.of(Short.class);
            }
            if (represents(Character.TYPE)) {
                return ForLoadedType.of(Character.class);
            }
            if (represents(Integer.TYPE)) {
                return ForLoadedType.of(Integer.class);
            }
            if (represents(Long.TYPE)) {
                return ForLoadedType.of(Long.class);
            }
            if (represents(Float.TYPE)) {
                return ForLoadedType.of(Float.class);
            }
            return represents(Double.TYPE) ? ForLoadedType.of(Double.class) : this;
        }

        public TypeDescription asUnboxed() {
            if (represents(Boolean.class)) {
                return ForLoadedType.of(Boolean.TYPE);
            }
            if (represents(Byte.class)) {
                return ForLoadedType.of(Byte.TYPE);
            }
            if (represents(Short.class)) {
                return ForLoadedType.of(Short.TYPE);
            }
            if (represents(Character.class)) {
                return ForLoadedType.of(Character.TYPE);
            }
            if (represents(Integer.class)) {
                return ForLoadedType.of(Integer.TYPE);
            }
            if (represents(Long.class)) {
                return ForLoadedType.of(Long.TYPE);
            }
            if (represents(Float.class)) {
                return ForLoadedType.of(Float.TYPE);
            }
            return represents(Double.class) ? ForLoadedType.of(Double.TYPE) : this;
        }

        public Object getDefaultValue() {
            if (represents(Boolean.TYPE)) {
                return false;
            }
            if (represents(Byte.TYPE)) {
                return (byte) 0;
            }
            if (represents(Short.TYPE)) {
                return (short) 0;
            }
            if (represents(Character.TYPE)) {
                return 0;
            }
            if (represents(Integer.TYPE)) {
                return 0;
            }
            if (represents(Long.TYPE)) {
                return 0L;
            }
            if (represents(Float.TYPE)) {
                return Float.valueOf(0.0f);
            }
            if (represents(Double.TYPE)) {
                return Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            }
            return null;
        }

        public boolean isNestHost() {
            return equals(getNestHost());
        }

        public boolean isNestMateOf(Class<?> cls) {
            return isNestMateOf(ForLoadedType.of(cls));
        }

        public boolean isNestMateOf(TypeDescription typeDescription) {
            return getNestHost().equals(typeDescription.getNestHost());
        }

        public boolean isMemberType() {
            return !isLocalType() && !isAnonymousType() && getDeclaringType() != null;
        }

        public Iterator<TypeDefinition> iterator() {
            return new TypeDefinition.SuperClassIterator(this);
        }

        public int hashCode() {
            return getName().hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TypeDefinition)) {
                return false;
            }
            TypeDefinition typeDefinition = (TypeDefinition) obj;
            if (!typeDefinition.getSort().isNonGeneric() || !getName().equals(typeDefinition.asErasure().getName())) {
                return false;
            }
            return true;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            if (isPrimitive()) {
                str = "";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(isInterface() ? "interface" : Name.LABEL);
                sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                str = sb2.toString();
            }
            sb.append(str);
            sb.append(getName());
            return sb.toString();
        }

        public static abstract class OfSimpleType extends AbstractBase {
            public boolean isArray() {
                return false;
            }

            public boolean isPrimitive() {
                return false;
            }

            public TypeDescription getComponentType() {
                return TypeDescription.UNDEFINED;
            }

            public String getDescriptor() {
                return "L" + getInternalName() + ";";
            }

            public String getCanonicalName() {
                if (isAnonymousType() || isLocalType()) {
                    return NO_NAME;
                }
                String internalName = getInternalName();
                TypeDescription enclosingType = getEnclosingType();
                if (enclosingType != null) {
                    if (internalName.startsWith(enclosingType.getInternalName() + "$")) {
                        return enclosingType.getCanonicalName() + "." + internalName.substring(enclosingType.getInternalName().length() + 1);
                    }
                }
                return getName();
            }

            public String getSimpleName() {
                int i;
                String internalName = getInternalName();
                TypeDescription enclosingType = getEnclosingType();
                if (enclosingType != null) {
                    if (internalName.startsWith(enclosingType.getInternalName() + "$")) {
                        i = enclosingType.getInternalName().length();
                        i++;
                        if (i >= internalName.length() || Character.isLetter(internalName.charAt(i))) {
                            return internalName.substring(i);
                        }
                        i++;
                        return internalName.substring(i);
                    }
                }
                i = internalName.lastIndexOf(47);
                if (i == -1) {
                    return internalName;
                }
                return internalName.substring(i);
            }

            public StackSize getStackSize() {
                return StackSize.SINGLE;
            }

            public static abstract class WithDelegation extends OfSimpleType {
                /* access modifiers changed from: protected */
                public abstract TypeDescription delegate();

                public /* bridge */ /* synthetic */ TypeDefinition getComponentType() {
                    return super.getComponentType();
                }

                public Generic getSuperClass() {
                    return delegate().getSuperClass();
                }

                public TypeList.Generic getInterfaces() {
                    return delegate().getInterfaces();
                }

                public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
                    return delegate().getDeclaredFields();
                }

                public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
                    return delegate().getDeclaredMethods();
                }

                public TypeDescription getDeclaringType() {
                    return delegate().getDeclaringType();
                }

                public MethodDescription.InDefinedShape getEnclosingMethod() {
                    return delegate().getEnclosingMethod();
                }

                public TypeDescription getEnclosingType() {
                    return delegate().getEnclosingType();
                }

                public TypeList getDeclaredTypes() {
                    return delegate().getDeclaredTypes();
                }

                public boolean isAnonymousType() {
                    return delegate().isAnonymousType();
                }

                public boolean isLocalType() {
                    return delegate().isLocalType();
                }

                public PackageDescription getPackage() {
                    return delegate().getPackage();
                }

                public AnnotationList getDeclaredAnnotations() {
                    return delegate().getDeclaredAnnotations();
                }

                public TypeList.Generic getTypeVariables() {
                    return delegate().getTypeVariables();
                }

                public int getModifiers() {
                    return delegate().getModifiers();
                }

                public String getGenericSignature() {
                    return delegate().getGenericSignature();
                }

                public int getActualModifiers(boolean z) {
                    return delegate().getActualModifiers(z);
                }

                public TypeDescription getNestHost() {
                    return delegate().getNestHost();
                }

                public TypeList getNestMembers() {
                    return delegate().getNestMembers();
                }
            }
        }
    }

    public static class ForLoadedType extends AbstractBase implements Serializable {
        private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private static final Map<Class<?>, TypeDescription> TYPE_CACHE;
        private static final long serialVersionUID = 1;
        private transient /* synthetic */ AnnotationList declaredAnnotations;
        private transient /* synthetic */ FieldList declaredFields;
        private transient /* synthetic */ MethodList declaredMethods;
        private final Class<?> type;

        static {
            HashMap hashMap = new HashMap();
            TYPE_CACHE = hashMap;
            hashMap.put(TargetType.class, new ForLoadedType(TargetType.class));
            TYPE_CACHE.put(Object.class, new ForLoadedType(Object.class));
            TYPE_CACHE.put(String.class, new ForLoadedType(String.class));
            TYPE_CACHE.put(Boolean.class, new ForLoadedType(Boolean.class));
            TYPE_CACHE.put(Byte.class, new ForLoadedType(Byte.class));
            TYPE_CACHE.put(Short.class, new ForLoadedType(Short.class));
            TYPE_CACHE.put(Character.class, new ForLoadedType(Character.class));
            TYPE_CACHE.put(Integer.class, new ForLoadedType(Integer.class));
            TYPE_CACHE.put(Long.class, new ForLoadedType(Long.class));
            TYPE_CACHE.put(Float.class, new ForLoadedType(Float.class));
            TYPE_CACHE.put(Double.class, new ForLoadedType(Double.class));
            TYPE_CACHE.put(Void.TYPE, new ForLoadedType(Void.TYPE));
            TYPE_CACHE.put(Boolean.TYPE, new ForLoadedType(Boolean.TYPE));
            TYPE_CACHE.put(Byte.TYPE, new ForLoadedType(Byte.TYPE));
            TYPE_CACHE.put(Short.TYPE, new ForLoadedType(Short.TYPE));
            TYPE_CACHE.put(Character.TYPE, new ForLoadedType(Character.TYPE));
            TYPE_CACHE.put(Integer.TYPE, new ForLoadedType(Integer.TYPE));
            TYPE_CACHE.put(Long.TYPE, new ForLoadedType(Long.TYPE));
            TYPE_CACHE.put(Float.TYPE, new ForLoadedType(Float.TYPE));
            TYPE_CACHE.put(Double.TYPE, new ForLoadedType(Double.TYPE));
        }

        public ForLoadedType(Class<?> cls) {
            this.type = cls;
        }

        public static String getName(Class<?> cls) {
            String name = cls.getName();
            int indexOf = name.indexOf(47);
            return indexOf == -1 ? name : name.substring(0, indexOf);
        }

        public static TypeDescription of(Class<?> cls) {
            TypeDescription typeDescription = TYPE_CACHE.get(cls);
            return typeDescription == null ? new ForLoadedType(cls) : typeDescription;
        }

        public boolean isAssignableFrom(Class<?> cls) {
            return this.type.isAssignableFrom(cls) || super.isAssignableFrom(cls);
        }

        public boolean isAssignableFrom(TypeDescription typeDescription) {
            return ((typeDescription instanceof ForLoadedType) && this.type.isAssignableFrom(((ForLoadedType) typeDescription).type)) || super.isAssignableFrom(typeDescription);
        }

        public boolean isAssignableTo(Class<?> cls) {
            return cls.isAssignableFrom(this.type) || super.isAssignableTo(cls);
        }

        public boolean isAssignableTo(TypeDescription typeDescription) {
            return ((typeDescription instanceof ForLoadedType) && ((ForLoadedType) typeDescription).type.isAssignableFrom(this.type)) || super.isAssignableTo(typeDescription);
        }

        public boolean isInHierarchyWith(Class<?> cls) {
            return cls.isAssignableFrom(this.type) || this.type.isAssignableFrom(cls) || super.isInHierarchyWith(cls);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0019, code lost:
            if (r3.type.isAssignableFrom(r0.type) == false) goto L_0x001b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean isInHierarchyWith(net.bytebuddy.description.type.TypeDescription r4) {
            /*
                r3 = this;
                boolean r0 = r4 instanceof net.bytebuddy.description.type.TypeDescription.ForLoadedType
                if (r0 == 0) goto L_0x001b
                r0 = r4
                net.bytebuddy.description.type.TypeDescription$ForLoadedType r0 = (net.bytebuddy.description.type.TypeDescription.ForLoadedType) r0
                java.lang.Class<?> r1 = r0.type
                java.lang.Class<?> r2 = r3.type
                boolean r1 = r1.isAssignableFrom(r2)
                if (r1 != 0) goto L_0x0021
                java.lang.Class<?> r1 = r3.type
                java.lang.Class<?> r0 = r0.type
                boolean r0 = r1.isAssignableFrom(r0)
                if (r0 != 0) goto L_0x0021
            L_0x001b:
                boolean r4 = super.isInHierarchyWith((net.bytebuddy.description.type.TypeDescription) r4)
                if (r4 == 0) goto L_0x0023
            L_0x0021:
                r4 = 1
                goto L_0x0024
            L_0x0023:
                r4 = 0
            L_0x0024:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.type.TypeDescription.ForLoadedType.isInHierarchyWith(net.bytebuddy.description.type.TypeDescription):boolean");
        }

        public boolean represents(Type type2) {
            return type2 == this.type || super.represents(type2);
        }

        public TypeDescription getComponentType() {
            Class<?> componentType = this.type.getComponentType();
            if (componentType == null) {
                return TypeDescription.UNDEFINED;
            }
            return of(componentType);
        }

        public boolean isArray() {
            return this.type.isArray();
        }

        public boolean isPrimitive() {
            return this.type.isPrimitive();
        }

        public boolean isAnnotation() {
            return this.type.isAnnotation();
        }

        public Generic getSuperClass() {
            if (!RAW_TYPES) {
                return this.type.getSuperclass() == null ? Generic.UNDEFINED : new Generic.LazyProjection.ForLoadedSuperClass(this.type);
            }
            if (this.type.getSuperclass() == null) {
                return Generic.UNDEFINED;
            }
            return Generic.OfNonGenericType.ForLoadedType.of(this.type.getSuperclass());
        }

        public TypeList.Generic getInterfaces() {
            if (!RAW_TYPES) {
                return isArray() ? ARRAY_INTERFACES : new TypeList.Generic.OfLoadedInterfaceTypes(this.type);
            }
            if (isArray()) {
                return ARRAY_INTERFACES;
            }
            return new TypeList.Generic.ForLoadedTypes((Type[]) this.type.getInterfaces());
        }

        public TypeDescription getDeclaringType() {
            Class<?> declaringClass = this.type.getDeclaringClass();
            if (declaringClass == null) {
                return TypeDescription.UNDEFINED;
            }
            return of(declaringClass);
        }

        public MethodDescription.InDefinedShape getEnclosingMethod() {
            Method enclosingMethod = this.type.getEnclosingMethod();
            Constructor<?> enclosingConstructor = this.type.getEnclosingConstructor();
            if (enclosingMethod != null) {
                return new MethodDescription.ForLoadedMethod(enclosingMethod);
            }
            if (enclosingConstructor != null) {
                return new MethodDescription.ForLoadedConstructor(enclosingConstructor);
            }
            return MethodDescription.UNDEFINED;
        }

        public TypeDescription getEnclosingType() {
            Class<?> enclosingClass = this.type.getEnclosingClass();
            if (enclosingClass == null) {
                return TypeDescription.UNDEFINED;
            }
            return of(enclosingClass);
        }

        public TypeList getDeclaredTypes() {
            return new TypeList.ForLoadedTypes((Class<?>[]) this.type.getDeclaredClasses());
        }

        public String getSimpleName() {
            String simpleName = this.type.getSimpleName();
            int indexOf = simpleName.indexOf(47);
            if (indexOf == -1) {
                return simpleName;
            }
            StringBuilder sb = new StringBuilder(simpleName.substring(0, indexOf));
            for (Class<?> cls = this.type; cls.isArray(); cls = cls.getComponentType()) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        public boolean isAnonymousType() {
            return this.type.isAnonymousClass();
        }

        public boolean isLocalType() {
            return this.type.isLocalClass();
        }

        public boolean isMemberType() {
            return this.type.isMemberClass();
        }

        @CachedReturnPlugin.Enhance("declaredFields")
        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            FieldList.ForLoadedFields forLoadedFields = this.declaredFields != null ? null : new FieldList.ForLoadedFields(this.type.getDeclaredFields());
            if (forLoadedFields == null) {
                return this.declaredFields;
            }
            this.declaredFields = forLoadedFields;
            return forLoadedFields;
        }

        @CachedReturnPlugin.Enhance("declaredMethods")
        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            MethodList.ForLoadedMethods forLoadedMethods = this.declaredMethods != null ? null : new MethodList.ForLoadedMethods(this.type);
            if (forLoadedMethods == null) {
                return this.declaredMethods;
            }
            this.declaredMethods = forLoadedMethods;
            return forLoadedMethods;
        }

        public PackageDescription getPackage() {
            if (this.type.isArray() || this.type.isPrimitive()) {
                return PackageDescription.UNDEFINED;
            }
            Package packageR = this.type.getPackage();
            if (packageR != null) {
                return new PackageDescription.ForLoadedPackage(packageR);
            }
            String name = this.type.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf == -1) {
                return new PackageDescription.Simple("");
            }
            return new PackageDescription.Simple(name.substring(0, lastIndexOf));
        }

        public StackSize getStackSize() {
            return StackSize.of(this.type);
        }

        public String getName() {
            return getName(this.type);
        }

        public String getCanonicalName() {
            String canonicalName = this.type.getCanonicalName();
            if (canonicalName == null) {
                return NO_NAME;
            }
            int indexOf = canonicalName.indexOf(47);
            if (indexOf == -1) {
                return canonicalName;
            }
            StringBuilder sb = new StringBuilder(canonicalName.substring(0, indexOf));
            for (Class<?> cls = this.type; cls.isArray(); cls = cls.getComponentType()) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        public String getDescriptor() {
            String name = this.type.getName();
            int indexOf = name.indexOf(47);
            if (indexOf == -1) {
                return net.bytebuddy.jar.asm.Type.getDescriptor(this.type);
            }
            return "L" + name.substring(0, indexOf).replace('.', '/') + ";";
        }

        public int getModifiers() {
            return this.type.getModifiers();
        }

        public TypeList.Generic getTypeVariables() {
            if (RAW_TYPES) {
                return new TypeList.Generic.Empty();
            }
            return TypeList.Generic.ForLoadedTypes.OfTypeVariables.of(this.type);
        }

        @CachedReturnPlugin.Enhance("declaredAnnotations")
        public AnnotationList getDeclaredAnnotations() {
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations = this.declaredAnnotations != null ? null : new AnnotationList.ForLoadedAnnotations(this.type.getDeclaredAnnotations());
            if (forLoadedAnnotations == null) {
                return this.declaredAnnotations;
            }
            this.declaredAnnotations = forLoadedAnnotations;
            return forLoadedAnnotations;
        }

        public Generic asGenericType() {
            return Generic.OfNonGenericType.ForLoadedType.of(this.type);
        }

        public TypeDescription getNestHost() {
            return of(DISPATCHER.getNestHost(this.type));
        }

        public TypeList getNestMembers() {
            return new TypeList.ForLoadedTypes((Class<?>[]) DISPATCHER.getNestMembers(this.type));
        }

        public boolean isNestHost() {
            return DISPATCHER.getNestHost(this.type) == this.type;
        }

        public boolean isNestMateOf(Class<?> cls) {
            return DISPATCHER.isNestmateOf(this.type, cls) || super.isNestMateOf(of(cls));
        }

        public boolean isNestMateOf(TypeDescription typeDescription) {
            return ((typeDescription instanceof ForLoadedType) && DISPATCHER.isNestmateOf(this.type, ((ForLoadedType) typeDescription).type)) || super.isNestMateOf(typeDescription);
        }

        protected interface Dispatcher {

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public Class<?> getNestHost(Class<?> cls) {
                    return cls;
                }

                public Class<?>[] getNestMembers(Class<?> cls) {
                    return new Class[]{cls};
                }

                public boolean isNestmateOf(Class<?> cls, Class<?> cls2) {
                    return cls == cls2;
                }
            }

            Class<?> getNestHost(Class<?> cls);

            Class<?>[] getNestMembers(Class<?> cls);

            boolean isNestmateOf(Class<?> cls, Class<?> cls2);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        return new ForJava11CapableVm(Class.class.getMethod("getNestHost", new Class[0]), Class.class.getMethod("getNestMembers", new Class[0]), Class.class.getMethod("isNestmateOf", new Class[]{Class.class}));
                    } catch (NoSuchMethodException unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            public static class ForJava11CapableVm implements Dispatcher {
                private final Method getNestHost;
                private final Method getNestMembers;
                private final Method isNestmateOf;

                protected ForJava11CapableVm(Method method, Method method2, Method method3) {
                    this.getNestHost = method;
                    this.getNestMembers = method2;
                    this.isNestmateOf = method3;
                }

                public Class<?> getNestHost(Class<?> cls) {
                    try {
                        return (Class) this.getNestHost.invoke(cls, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access Class::getNestHost", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Could not invoke Class:getNestHost", e2.getCause());
                    }
                }

                public Class<?>[] getNestMembers(Class<?> cls) {
                    try {
                        return (Class[]) this.getNestMembers.invoke(cls, new Object[0]);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access Class::getNestMembers", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Could not invoke Class:getNestMembers", e2.getCause());
                    }
                }

                public boolean isNestmateOf(Class<?> cls, Class<?> cls2) {
                    try {
                        return ((Boolean) this.isNestmateOf.invoke(cls, new Object[]{cls2})).booleanValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Could not access Class::isNestmateOf", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Could not invoke Class:isNestmateOf", e2.getCause());
                    }
                }
            }
        }
    }

    public static class ArrayProjection extends AbstractBase {
        private static final int ARRAY_EXCLUDED = 8712;
        private static final int ARRAY_IMPLIED = 1040;
        private final int arity;
        private final TypeDescription componentType;

        public TypeDescription getNestHost() {
            return this;
        }

        public boolean isAnonymousType() {
            return false;
        }

        public boolean isArray() {
            return true;
        }

        public boolean isLocalType() {
            return false;
        }

        public boolean isMemberType() {
            return false;
        }

        public boolean isPrimitive() {
            return false;
        }

        protected ArrayProjection(TypeDescription typeDescription, int i) {
            this.componentType = typeDescription;
            this.arity = i;
        }

        public static TypeDescription of(TypeDescription typeDescription) {
            return of(typeDescription, 1);
        }

        public static TypeDescription of(TypeDescription typeDescription, int i) {
            if (i >= 0) {
                while (typeDescription.isArray()) {
                    typeDescription = typeDescription.getComponentType();
                    i++;
                }
                return i == 0 ? typeDescription : new ArrayProjection(typeDescription, i);
            }
            throw new IllegalArgumentException("Arrays cannot have a negative arity");
        }

        public TypeDescription getComponentType() {
            return this.arity == 1 ? this.componentType : new ArrayProjection(this.componentType, this.arity - 1);
        }

        public Generic getSuperClass() {
            return Generic.OBJECT;
        }

        public TypeList.Generic getInterfaces() {
            return ARRAY_INTERFACES;
        }

        public MethodDescription.InDefinedShape getEnclosingMethod() {
            return MethodDescription.UNDEFINED;
        }

        public TypeDescription getEnclosingType() {
            return TypeDescription.UNDEFINED;
        }

        public TypeList getDeclaredTypes() {
            return new TypeList.Empty();
        }

        public String getSimpleName() {
            StringBuilder sb = new StringBuilder(this.componentType.getSimpleName());
            for (int i = 0; i < this.arity; i++) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        public String getCanonicalName() {
            String canonicalName = this.componentType.getCanonicalName();
            if (canonicalName == null) {
                return NO_NAME;
            }
            StringBuilder sb = new StringBuilder(canonicalName);
            for (int i = 0; i < this.arity; i++) {
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            return sb.toString();
        }

        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return new FieldList.Empty();
        }

        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return new MethodList.Empty();
        }

        public StackSize getStackSize() {
            return StackSize.SINGLE;
        }

        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Empty();
        }

        public AnnotationList getInheritedAnnotations() {
            return new AnnotationList.Empty();
        }

        public PackageDescription getPackage() {
            return PackageDescription.UNDEFINED;
        }

        public String getName() {
            String descriptor = this.componentType.getDescriptor();
            StringBuilder sb = new StringBuilder(descriptor.length() + this.arity);
            for (int i = 0; i < this.arity; i++) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            }
            for (int i2 = 0; i2 < descriptor.length(); i2++) {
                char charAt = descriptor.charAt(i2);
                if (charAt == '/') {
                    charAt = '.';
                }
                sb.append(charAt);
            }
            return sb.toString();
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.arity; i++) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            }
            sb.append(this.componentType.getDescriptor());
            return sb.toString();
        }

        public TypeDescription getDeclaringType() {
            return TypeDescription.UNDEFINED;
        }

        public int getModifiers() {
            return (getComponentType().getModifiers() & -8713) | ARRAY_IMPLIED;
        }

        public TypeList.Generic getTypeVariables() {
            return new TypeList.Generic.Empty();
        }

        public TypeList getNestMembers() {
            return new TypeList.Explicit(this);
        }
    }

    public static class Latent extends AbstractBase.OfSimpleType {
        private final List<? extends Generic> interfaces;
        private final int modifiers;
        private final String name;
        private final Generic superClass;

        public Latent(String str, int i, Generic generic, Generic... genericArr) {
            this(str, i, generic, (List<? extends Generic>) Arrays.asList(genericArr));
        }

        public Latent(String str, int i, Generic generic, List<? extends Generic> list) {
            this.name = str;
            this.modifiers = i;
            this.superClass = generic;
            this.interfaces = list;
        }

        public Generic getSuperClass() {
            return this.superClass;
        }

        public TypeList.Generic getInterfaces() {
            return new TypeList.Generic.Explicit((List<? extends TypeDefinition>) this.interfaces);
        }

        public MethodDescription.InDefinedShape getEnclosingMethod() {
            throw new IllegalStateException("Cannot resolve enclosing method of a latent type description: " + this);
        }

        public TypeDescription getEnclosingType() {
            throw new IllegalStateException("Cannot resolve enclosing type of a latent type description: " + this);
        }

        public TypeList getDeclaredTypes() {
            throw new IllegalStateException("Cannot resolve inner types of a latent type description: " + this);
        }

        public boolean isAnonymousType() {
            throw new IllegalStateException("Cannot resolve anonymous type property of a latent type description: " + this);
        }

        public boolean isLocalType() {
            throw new IllegalStateException("Cannot resolve local class property of a latent type description: " + this);
        }

        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            throw new IllegalStateException("Cannot resolve declared fields of a latent type description: " + this);
        }

        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            throw new IllegalStateException("Cannot resolve declared methods of a latent type description: " + this);
        }

        public PackageDescription getPackage() {
            String str;
            String name2 = getName();
            int lastIndexOf = name2.lastIndexOf(46);
            if (lastIndexOf == -1) {
                str = "";
            } else {
                str = name2.substring(0, lastIndexOf);
            }
            return new PackageDescription.Simple(str);
        }

        public AnnotationList getDeclaredAnnotations() {
            throw new IllegalStateException("Cannot resolve declared annotations of a latent type description: " + this);
        }

        public TypeDescription getDeclaringType() {
            throw new IllegalStateException("Cannot resolve declared type of a latent type description: " + this);
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public String getName() {
            return this.name;
        }

        public TypeList.Generic getTypeVariables() {
            throw new IllegalStateException("Cannot resolve type variables of a latent type description: " + this);
        }

        public TypeDescription getNestHost() {
            throw new IllegalStateException("Cannot resolve nest host of a latent type description: " + this);
        }

        public TypeList getNestMembers() {
            throw new IllegalStateException("Cannot resolve nest mates of a latent type description: " + this);
        }
    }

    public static class ForPackageDescription extends AbstractBase.OfSimpleType {
        private final PackageDescription packageDescription;

        public int getModifiers() {
            return PackageDescription.PACKAGE_MODIFIERS;
        }

        public TypeDescription getNestHost() {
            return this;
        }

        public boolean isAnonymousType() {
            return false;
        }

        public boolean isLocalType() {
            return false;
        }

        public ForPackageDescription(PackageDescription packageDescription2) {
            this.packageDescription = packageDescription2;
        }

        public Generic getSuperClass() {
            return Generic.OBJECT;
        }

        public TypeList.Generic getInterfaces() {
            return new TypeList.Generic.Empty();
        }

        public MethodDescription.InDefinedShape getEnclosingMethod() {
            return MethodDescription.UNDEFINED;
        }

        public TypeDescription getEnclosingType() {
            return TypeDescription.UNDEFINED;
        }

        public TypeList getDeclaredTypes() {
            return new TypeList.Empty();
        }

        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return new FieldList.Empty();
        }

        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return new MethodList.Empty();
        }

        public PackageDescription getPackage() {
            return this.packageDescription;
        }

        public AnnotationList getDeclaredAnnotations() {
            return this.packageDescription.getDeclaredAnnotations();
        }

        public TypeDescription getDeclaringType() {
            return TypeDescription.UNDEFINED;
        }

        public TypeList.Generic getTypeVariables() {
            return new TypeList.Generic.Empty();
        }

        public String getName() {
            return this.packageDescription.getName() + "." + PackageDescription.PACKAGE_CLASS_NAME;
        }

        public TypeList getNestMembers() {
            return new TypeList.Explicit(this);
        }
    }

    public static class SuperTypeLoading extends AbstractBase {
        private final ClassLoader classLoader;
        private final ClassLoadingDelegate classLoadingDelegate;
        private final TypeDescription delegate;

        public SuperTypeLoading(TypeDescription typeDescription, ClassLoader classLoader2) {
            this(typeDescription, classLoader2, ClassLoadingDelegate.Simple.INSTANCE);
        }

        public SuperTypeLoading(TypeDescription typeDescription, ClassLoader classLoader2, ClassLoadingDelegate classLoadingDelegate2) {
            this.delegate = typeDescription;
            this.classLoader = classLoader2;
            this.classLoadingDelegate = classLoadingDelegate2;
        }

        public AnnotationList getDeclaredAnnotations() {
            return this.delegate.getDeclaredAnnotations();
        }

        public int getModifiers() {
            return this.delegate.getModifiers();
        }

        public TypeList.Generic getTypeVariables() {
            return this.delegate.getTypeVariables();
        }

        public String getDescriptor() {
            return this.delegate.getDescriptor();
        }

        public String getName() {
            return this.delegate.getName();
        }

        public Generic getSuperClass() {
            Generic superClass = this.delegate.getSuperClass();
            return superClass == null ? Generic.UNDEFINED : new ClassLoadingTypeProjection(superClass, this.classLoader, this.classLoadingDelegate);
        }

        public TypeList.Generic getInterfaces() {
            return new ClassLoadingTypeList(this.delegate.getInterfaces(), this.classLoader, this.classLoadingDelegate);
        }

        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return this.delegate.getDeclaredFields();
        }

        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return this.delegate.getDeclaredMethods();
        }

        public StackSize getStackSize() {
            return this.delegate.getStackSize();
        }

        public boolean isArray() {
            return this.delegate.isArray();
        }

        public boolean isPrimitive() {
            return this.delegate.isPrimitive();
        }

        public TypeDescription getComponentType() {
            return this.delegate.getComponentType();
        }

        public TypeDescription getDeclaringType() {
            return this.delegate.getDeclaringType();
        }

        public TypeList getDeclaredTypes() {
            return this.delegate.getDeclaredTypes();
        }

        public MethodDescription.InDefinedShape getEnclosingMethod() {
            return this.delegate.getEnclosingMethod();
        }

        public TypeDescription getEnclosingType() {
            return this.delegate.getEnclosingType();
        }

        public String getSimpleName() {
            return this.delegate.getSimpleName();
        }

        public String getCanonicalName() {
            return this.delegate.getCanonicalName();
        }

        public boolean isAnonymousType() {
            return this.delegate.isAnonymousType();
        }

        public boolean isLocalType() {
            return this.delegate.isLocalType();
        }

        public PackageDescription getPackage() {
            return this.delegate.getPackage();
        }

        public TypeDescription getNestHost() {
            return this.delegate.getNestHost();
        }

        public TypeList getNestMembers() {
            return this.delegate.getNestMembers();
        }

        public interface ClassLoadingDelegate {
            Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException;

            public enum Simple implements ClassLoadingDelegate {
                INSTANCE;

                public Class<?> load(String str, ClassLoader classLoader) throws ClassNotFoundException {
                    return Class.forName(str, false, classLoader);
                }
            }
        }

        protected static class ClassLoadingTypeProjection extends Generic.LazyProjection {
            private final ClassLoader classLoader;
            private final ClassLoadingDelegate classLoadingDelegate;
            private final Generic delegate;
            private transient /* synthetic */ TypeDescription erasure;
            private transient /* synthetic */ TypeList.Generic interfaces;
            private transient /* synthetic */ Generic superClass;

            protected ClassLoadingTypeProjection(Generic generic, ClassLoader classLoader2, ClassLoadingDelegate classLoadingDelegate2) {
                this.delegate = generic;
                this.classLoader = classLoader2;
                this.classLoadingDelegate = classLoadingDelegate2;
            }

            public AnnotationList getDeclaredAnnotations() {
                return this.delegate.getDeclaredAnnotations();
            }

            @CachedReturnPlugin.Enhance("erasure")
            public TypeDescription asErasure() {
                TypeDescription typeDescription;
                if (this.erasure != null) {
                    typeDescription = null;
                } else {
                    try {
                        typeDescription = ForLoadedType.of(this.classLoadingDelegate.load(this.delegate.asErasure().getName(), this.classLoader));
                    } catch (ClassNotFoundException unused) {
                        typeDescription = this.delegate.asErasure();
                    }
                }
                if (typeDescription == null) {
                    return this.erasure;
                }
                this.erasure = typeDescription;
                return typeDescription;
            }

            /* access modifiers changed from: protected */
            public Generic resolve() {
                return this.delegate;
            }

            @CachedReturnPlugin.Enhance("superClass")
            public Generic getSuperClass() {
                Generic generic;
                if (this.superClass != null) {
                    generic = null;
                } else {
                    generic = this.delegate.getSuperClass();
                    if (generic == null) {
                        generic = Generic.UNDEFINED;
                    } else {
                        try {
                            generic = new ClassLoadingTypeProjection(generic, this.classLoadingDelegate.load(this.delegate.asErasure().getName(), this.classLoader).getClassLoader(), this.classLoadingDelegate);
                        } catch (ClassNotFoundException unused) {
                        }
                    }
                }
                if (generic == null) {
                    return this.superClass;
                }
                this.superClass = generic;
                return generic;
            }

            @CachedReturnPlugin.Enhance("interfaces")
            public TypeList.Generic getInterfaces() {
                TypeList.Generic generic;
                if (this.interfaces != null) {
                    generic = null;
                } else {
                    generic = this.delegate.getInterfaces();
                    try {
                        generic = new ClassLoadingTypeList(generic, this.classLoadingDelegate.load(this.delegate.asErasure().getName(), this.classLoader).getClassLoader(), this.classLoadingDelegate);
                    } catch (ClassNotFoundException unused) {
                    }
                }
                if (generic == null) {
                    return this.interfaces;
                }
                this.interfaces = generic;
                return generic;
            }

            public Iterator<TypeDefinition> iterator() {
                return new TypeDefinition.SuperClassIterator(this);
            }
        }

        protected static class ClassLoadingTypeList extends TypeList.Generic.AbstractBase {
            private final ClassLoader classLoader;
            private final ClassLoadingDelegate classLoadingDelegate;
            private final TypeList.Generic delegate;

            protected ClassLoadingTypeList(TypeList.Generic generic, ClassLoader classLoader2, ClassLoadingDelegate classLoadingDelegate2) {
                this.delegate = generic;
                this.classLoader = classLoader2;
                this.classLoadingDelegate = classLoadingDelegate2;
            }

            public Generic get(int i) {
                return new ClassLoadingTypeProjection((Generic) this.delegate.get(i), this.classLoader, this.classLoadingDelegate);
            }

            public int size() {
                return this.delegate.size();
            }
        }
    }
}

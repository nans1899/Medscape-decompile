package net.bytebuddy.dynamic.scaffold.inline;

import androidx.core.view.InputDeviceCompat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.auxiliary.TrivialType;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;

public interface MethodRebaseResolver {
    Map<MethodDescription.SignatureToken, Resolution> asTokenMap();

    List<DynamicType> getAuxiliaryTypes();

    Resolution resolve(MethodDescription.InDefinedShape inDefinedShape);

    public enum Disabled implements MethodRebaseResolver {
        INSTANCE;

        public Resolution resolve(MethodDescription.InDefinedShape inDefinedShape) {
            return new Resolution.Preserved(inDefinedShape);
        }

        public List<DynamicType> getAuxiliaryTypes() {
            return Collections.emptyList();
        }

        public Map<MethodDescription.SignatureToken, Resolution> asTokenMap() {
            return Collections.emptyMap();
        }
    }

    public interface Resolution {
        StackManipulation getAdditionalArguments();

        MethodDescription.InDefinedShape getResolvedMethod();

        boolean isRebased();

        @HashCodeAndEqualsPlugin.Enhance
        public static class Preserved implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((Preserved) obj).methodDescription);
            }

            public int hashCode() {
                return 527 + this.methodDescription.hashCode();
            }

            public boolean isRebased() {
                return false;
            }

            public Preserved(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            public StackManipulation getAdditionalArguments() {
                throw new IllegalStateException("Cannot process additional arguments for non-rebased method: " + this.methodDescription);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForRebasedMethod implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForRebasedMethod) obj).methodDescription);
            }

            public int hashCode() {
                return 527 + this.methodDescription.hashCode();
            }

            public boolean isRebased() {
                return true;
            }

            protected ForRebasedMethod(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            public static Resolution of(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodNameTransformer methodNameTransformer) {
                return new ForRebasedMethod(new RebasedMethod(typeDescription, inDefinedShape, methodNameTransformer));
            }

            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            public StackManipulation getAdditionalArguments() {
                return StackManipulation.Trivial.INSTANCE;
            }

            protected static class RebasedMethod extends MethodDescription.InDefinedShape.AbstractBase {
                private final TypeDescription instrumentedType;
                private final MethodDescription.InDefinedShape methodDescription;
                private final MethodNameTransformer methodNameTransformer;

                protected RebasedMethod(TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, MethodNameTransformer methodNameTransformer2) {
                    this.instrumentedType = typeDescription;
                    this.methodDescription = inDefinedShape;
                    this.methodNameTransformer = methodNameTransformer2;
                }

                public TypeDescription.Generic getReturnType() {
                    return this.methodDescription.getReturnType().asRawType();
                }

                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes((MethodDescription.InDefinedShape) this, (List<? extends TypeDefinition>) this.methodDescription.getParameters().asTypeList().asRawTypes());
                }

                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
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
                    return this.methodDescription.getDeclaringType();
                }

                public int getModifiers() {
                    int i = 0;
                    int i2 = (this.methodDescription.isStatic() ? 8 : 0) | 4096;
                    if (this.methodDescription.isNative()) {
                        i = 256;
                    }
                    return i2 | i | (this.instrumentedType.isInterface() ? 1 : 2);
                }

                public String getInternalName() {
                    return this.methodNameTransformer.transform(this.methodDescription);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForRebasedConstructor implements Resolution {
            private final MethodDescription.InDefinedShape methodDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForRebasedConstructor) obj).methodDescription);
            }

            public int hashCode() {
                return 527 + this.methodDescription.hashCode();
            }

            public boolean isRebased() {
                return true;
            }

            protected ForRebasedConstructor(MethodDescription.InDefinedShape inDefinedShape) {
                this.methodDescription = inDefinedShape;
            }

            public static Resolution of(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
                return new ForRebasedConstructor(new RebasedConstructor(inDefinedShape, typeDescription));
            }

            public MethodDescription.InDefinedShape getResolvedMethod() {
                return this.methodDescription;
            }

            public StackManipulation getAdditionalArguments() {
                return NullConstant.INSTANCE;
            }

            protected static class RebasedConstructor extends MethodDescription.InDefinedShape.AbstractBase {
                private final MethodDescription.InDefinedShape methodDescription;
                private final TypeDescription placeholderType;

                public String getInternalName() {
                    return MethodDescription.CONSTRUCTOR_INTERNAL_NAME;
                }

                public int getModifiers() {
                    return InputDeviceCompat.SOURCE_TOUCHSCREEN;
                }

                protected RebasedConstructor(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
                    this.methodDescription = inDefinedShape;
                    this.placeholderType = typeDescription;
                }

                public TypeDescription.Generic getReturnType() {
                    return TypeDescription.Generic.VOID;
                }

                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes((MethodDescription.InDefinedShape) this, (List<? extends TypeDefinition>) CompoundList.of(this.methodDescription.getParameters().asTypeList().asErasures(), this.placeholderType));
                }

                public TypeList.Generic getExceptionTypes() {
                    return this.methodDescription.getExceptionTypes().asRawTypes();
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
                    return this.methodDescription.getDeclaringType();
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Default implements MethodRebaseResolver {
        private final List<DynamicType> dynamicTypes;
        private final Map<MethodDescription.InDefinedShape, Resolution> resolutions;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Default defaultR = (Default) obj;
            return this.resolutions.equals(defaultR.resolutions) && this.dynamicTypes.equals(defaultR.dynamicTypes);
        }

        public int hashCode() {
            return ((527 + this.resolutions.hashCode()) * 31) + this.dynamicTypes.hashCode();
        }

        protected Default(Map<MethodDescription.InDefinedShape, Resolution> map, List<DynamicType> list) {
            this.resolutions = map;
            this.dynamicTypes = list;
        }

        public static MethodRebaseResolver make(TypeDescription typeDescription, Set<? extends MethodDescription.Token> set, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, MethodNameTransformer methodNameTransformer) {
            Resolution resolution;
            HashMap hashMap = new HashMap();
            DynamicType dynamicType = null;
            for (MethodDescription.InDefinedShape inDefinedShape : typeDescription.getDeclaredMethods()) {
                if (set.contains(inDefinedShape.asToken(ElementMatchers.is((Object) typeDescription)))) {
                    if (inDefinedShape.isConstructor()) {
                        if (dynamicType == null) {
                            dynamicType = TrivialType.SIGNATURE_RELEVANT.make(namingStrategy.name(typeDescription), classFileVersion, MethodAccessorFactory.Illegal.INSTANCE);
                        }
                        resolution = Resolution.ForRebasedConstructor.of(inDefinedShape, dynamicType.getTypeDescription());
                    } else {
                        resolution = Resolution.ForRebasedMethod.of(typeDescription, inDefinedShape, methodNameTransformer);
                    }
                    hashMap.put(inDefinedShape, resolution);
                }
            }
            if (dynamicType == null) {
                return new Default(hashMap, Collections.emptyList());
            }
            return new Default(hashMap, Collections.singletonList(dynamicType));
        }

        public Resolution resolve(MethodDescription.InDefinedShape inDefinedShape) {
            Resolution resolution = this.resolutions.get(inDefinedShape);
            return resolution == null ? new Resolution.Preserved(inDefinedShape) : resolution;
        }

        public List<DynamicType> getAuxiliaryTypes() {
            return this.dynamicTypes;
        }

        public Map<MethodDescription.SignatureToken, Resolution> asTokenMap() {
            HashMap hashMap = new HashMap();
            for (Map.Entry next : this.resolutions.entrySet()) {
                hashMap.put(((MethodDescription.InDefinedShape) next.getKey()).asSignatureToken(), next.getValue());
            }
            return hashMap;
        }
    }
}

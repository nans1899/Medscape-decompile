package net.bytebuddy.dynamic.scaffold;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.scaffold.MethodRegistry;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver;
import net.bytebuddy.dynamic.scaffold.inline.RebaseImplementationTarget;
import net.bytebuddy.dynamic.scaffold.subclass.SubclassImplementationTarget;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.attribute.AnnotationAppender;
import net.bytebuddy.implementation.attribute.AnnotationRetention;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.attribute.FieldAttributeAppender;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.implementation.attribute.TypeAttributeAppender;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.ClassReader;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.ClassWriter;
import net.bytebuddy.jar.asm.ConstantDynamic;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.jar.asm.TypePath;
import net.bytebuddy.jar.asm.commons.ClassRemapper;
import net.bytebuddy.jar.asm.commons.Remapper;
import net.bytebuddy.jar.asm.commons.SimpleRemapper;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;
import net.bytebuddy.utility.visitor.MetadataAwareClassVisitor;

public interface TypeWriter<T> {
    public static final String DUMP_PROPERTY = "net.bytebuddy.dump";

    DynamicType.Unloaded<T> make(TypeResolutionStrategy.Resolved resolved);

    public interface FieldPool {
        Record target(FieldDescription fieldDescription);

        public interface Record {
            void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory);

            void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory);

            FieldDescription getField();

            FieldAttributeAppender getFieldAppender();

            boolean isImplicit();

            Object resolveDefault(Object obj);

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForImplicitField implements Record {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((ForImplicitField) obj).fieldDescription);
                }

                public int hashCode() {
                    return 527 + this.fieldDescription.hashCode();
                }

                public boolean isImplicit() {
                    return true;
                }

                public ForImplicitField(FieldDescription fieldDescription2) {
                    this.fieldDescription = fieldDescription2;
                }

                public FieldDescription getField() {
                    return this.fieldDescription;
                }

                public FieldAttributeAppender getFieldAppender() {
                    throw new IllegalStateException("An implicit field record does not expose a field appender: " + this);
                }

                public Object resolveDefault(Object obj) {
                    throw new IllegalStateException("An implicit field record does not expose a default value: " + this);
                }

                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    FieldVisitor visitField = classVisitor.visitField(this.fieldDescription.getActualModifiers(), this.fieldDescription.getInternalName(), this.fieldDescription.getDescriptor(), this.fieldDescription.getGenericSignature(), FieldDescription.NO_DEFAULT_VALUE);
                    if (visitField != null) {
                        FieldAttributeAppender.ForInstrumentedField forInstrumentedField = FieldAttributeAppender.ForInstrumentedField.INSTANCE;
                        FieldDescription fieldDescription2 = this.fieldDescription;
                        forInstrumentedField.apply(visitField, fieldDescription2, factory.on(fieldDescription2));
                        visitField.visitEnd();
                    }
                }

                public void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory) {
                    throw new IllegalStateException("An implicit field record is not intended for partial application: " + this);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForExplicitField implements Record {
                private final FieldAttributeAppender attributeAppender;
                private final Object defaultValue;
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForExplicitField forExplicitField = (ForExplicitField) obj;
                    return this.attributeAppender.equals(forExplicitField.attributeAppender) && this.defaultValue.equals(forExplicitField.defaultValue) && this.fieldDescription.equals(forExplicitField.fieldDescription);
                }

                public int hashCode() {
                    return ((((527 + this.attributeAppender.hashCode()) * 31) + this.defaultValue.hashCode()) * 31) + this.fieldDescription.hashCode();
                }

                public boolean isImplicit() {
                    return false;
                }

                public ForExplicitField(FieldAttributeAppender fieldAttributeAppender, Object obj, FieldDescription fieldDescription2) {
                    this.attributeAppender = fieldAttributeAppender;
                    this.defaultValue = obj;
                    this.fieldDescription = fieldDescription2;
                }

                public FieldDescription getField() {
                    return this.fieldDescription;
                }

                public FieldAttributeAppender getFieldAppender() {
                    return this.attributeAppender;
                }

                public Object resolveDefault(Object obj) {
                    Object obj2 = this.defaultValue;
                    return obj2 == null ? obj : obj2;
                }

                public void apply(ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                    FieldVisitor visitField = classVisitor.visitField(this.fieldDescription.getActualModifiers(), this.fieldDescription.getInternalName(), this.fieldDescription.getDescriptor(), this.fieldDescription.getGenericSignature(), resolveDefault(FieldDescription.NO_DEFAULT_VALUE));
                    if (visitField != null) {
                        FieldAttributeAppender fieldAttributeAppender = this.attributeAppender;
                        FieldDescription fieldDescription2 = this.fieldDescription;
                        fieldAttributeAppender.apply(visitField, fieldDescription2, factory.on(fieldDescription2));
                        visitField.visitEnd();
                    }
                }

                public void apply(FieldVisitor fieldVisitor, AnnotationValueFilter.Factory factory) {
                    FieldAttributeAppender fieldAttributeAppender = this.attributeAppender;
                    FieldDescription fieldDescription2 = this.fieldDescription;
                    fieldAttributeAppender.apply(fieldVisitor, fieldDescription2, factory.on(fieldDescription2));
                }
            }
        }

        public enum Disabled implements FieldPool {
            INSTANCE;

            public Record target(FieldDescription fieldDescription) {
                throw new IllegalStateException("Cannot look up field from disabld pool");
            }
        }
    }

    public interface MethodPool {
        Record target(MethodDescription methodDescription);

        public interface Record {
            void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory);

            void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory);

            void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory);

            ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context);

            void applyHead(MethodVisitor methodVisitor);

            MethodDescription getMethod();

            Sort getSort();

            Visibility getVisibility();

            Record prepend(ByteCodeAppender byteCodeAppender);

            public enum Sort {
                SKIPPED(false, false),
                DEFINED(true, false),
                IMPLEMENTED(true, true);
                
                private final boolean define;
                private final boolean implement;

                private Sort(boolean z, boolean z2) {
                    this.define = z;
                    this.implement = z2;
                }

                public boolean isDefined() {
                    return this.define;
                }

                public boolean isImplemented() {
                    return this.implement;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForNonImplementedMethod implements Record {
                private final MethodDescription methodDescription;

                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                }

                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodDescription.equals(((ForNonImplementedMethod) obj).methodDescription);
                }

                public int hashCode() {
                    return 527 + this.methodDescription.hashCode();
                }

                public ForNonImplementedMethod(MethodDescription methodDescription2) {
                    this.methodDescription = methodDescription2;
                }

                public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    throw new IllegalStateException("Cannot apply body for non-implemented method on " + this.methodDescription);
                }

                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                    throw new IllegalStateException("Cannot apply code for non-implemented method on " + this.methodDescription);
                }

                public void applyHead(MethodVisitor methodVisitor) {
                    throw new IllegalStateException("Cannot apply head for non-implemented method on " + this.methodDescription);
                }

                public MethodDescription getMethod() {
                    return this.methodDescription;
                }

                public Visibility getVisibility() {
                    return this.methodDescription.getVisibility();
                }

                public Sort getSort() {
                    return Sort.SKIPPED;
                }

                public Record prepend(ByteCodeAppender byteCodeAppender) {
                    MethodDescription methodDescription2 = this.methodDescription;
                    return new ForDefinedMethod.WithBody(methodDescription2, new ByteCodeAppender.Compound(byteCodeAppender, new ByteCodeAppender.Simple(DefaultValue.of(methodDescription2.getReturnType()), MethodReturn.of(this.methodDescription.getReturnType()))));
                }
            }

            public static abstract class ForDefinedMethod implements Record {
                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    MethodVisitor visitMethod = classVisitor.visitMethod(getMethod().getActualModifiers(getSort().isImplemented(), getVisibility()), getMethod().getInternalName(), getMethod().getDescriptor(), getMethod().getGenericSignature(), getMethod().getExceptionTypes().asErasures().toInternalNames());
                    if (visitMethod != null) {
                        ParameterList<?> parameters = getMethod().getParameters();
                        if (parameters.hasExplicitMetaData()) {
                            Iterator it = parameters.iterator();
                            while (it.hasNext()) {
                                ParameterDescription parameterDescription = (ParameterDescription) it.next();
                                visitMethod.visitParameter(parameterDescription.getName(), parameterDescription.getModifiers());
                            }
                        }
                        applyHead(visitMethod);
                        applyBody(visitMethod, context, factory);
                        visitMethod.visitEnd();
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class WithBody extends ForDefinedMethod {
                    private final ByteCodeAppender byteCodeAppender;
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final MethodDescription methodDescription;
                    private final Visibility visibility;

                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        WithBody withBody = (WithBody) obj;
                        return this.visibility.equals(withBody.visibility) && this.methodDescription.equals(withBody.methodDescription) && this.byteCodeAppender.equals(withBody.byteCodeAppender) && this.methodAttributeAppender.equals(withBody.methodAttributeAppender);
                    }

                    public int hashCode() {
                        return ((((((527 + this.methodDescription.hashCode()) * 31) + this.byteCodeAppender.hashCode()) * 31) + this.methodAttributeAppender.hashCode()) * 31) + this.visibility.hashCode();
                    }

                    public WithBody(MethodDescription methodDescription2, ByteCodeAppender byteCodeAppender2) {
                        this(methodDescription2, byteCodeAppender2, MethodAttributeAppender.NoOp.INSTANCE, methodDescription2.getVisibility());
                    }

                    public WithBody(MethodDescription methodDescription2, ByteCodeAppender byteCodeAppender2, MethodAttributeAppender methodAttributeAppender2, Visibility visibility2) {
                        this.methodDescription = methodDescription2;
                        this.byteCodeAppender = byteCodeAppender2;
                        this.methodAttributeAppender = methodAttributeAppender2;
                        this.visibility = visibility2;
                    }

                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    public Sort getSort() {
                        return Sort.IMPLEMENTED;
                    }

                    public Visibility getVisibility() {
                        return this.visibility;
                    }

                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                        methodVisitor.visitCode();
                        ByteCodeAppender.Size applyCode = applyCode(methodVisitor, context);
                        methodVisitor.visitMaxs(applyCode.getOperandStackSize(), applyCode.getLocalVariableSize());
                    }

                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender2 = this.methodAttributeAppender;
                        MethodDescription methodDescription2 = this.methodDescription;
                        methodAttributeAppender2.apply(methodVisitor, methodDescription2, factory.on(methodDescription2));
                    }

                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        return this.byteCodeAppender.apply(methodVisitor, context, this.methodDescription);
                    }

                    public Record prepend(ByteCodeAppender byteCodeAppender2) {
                        return new WithBody(this.methodDescription, new ByteCodeAppender.Compound(byteCodeAppender2, this.byteCodeAppender), this.methodAttributeAppender, this.visibility);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class WithoutBody extends ForDefinedMethod {
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final MethodDescription methodDescription;
                    private final Visibility visibility;

                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        WithoutBody withoutBody = (WithoutBody) obj;
                        return this.visibility.equals(withoutBody.visibility) && this.methodDescription.equals(withoutBody.methodDescription) && this.methodAttributeAppender.equals(withoutBody.methodAttributeAppender);
                    }

                    public int hashCode() {
                        return ((((527 + this.methodDescription.hashCode()) * 31) + this.methodAttributeAppender.hashCode()) * 31) + this.visibility.hashCode();
                    }

                    public WithoutBody(MethodDescription methodDescription2, MethodAttributeAppender methodAttributeAppender2, Visibility visibility2) {
                        this.methodDescription = methodDescription2;
                        this.methodAttributeAppender = methodAttributeAppender2;
                        this.visibility = visibility2;
                    }

                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    public Sort getSort() {
                        return Sort.DEFINED;
                    }

                    public Visibility getVisibility() {
                        return this.visibility;
                    }

                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                    }

                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender2 = this.methodAttributeAppender;
                        MethodDescription methodDescription2 = this.methodDescription;
                        methodAttributeAppender2.apply(methodVisitor, methodDescription2, factory.on(methodDescription2));
                    }

                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        throw new IllegalStateException("Cannot apply code for abstract method on " + this.methodDescription);
                    }

                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        throw new IllegalStateException("Cannot prepend code for abstract method on " + this.methodDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class WithAnnotationDefaultValue extends ForDefinedMethod {
                    private final AnnotationValue<?, ?> annotationValue;
                    private final MethodAttributeAppender methodAttributeAppender;
                    private final MethodDescription methodDescription;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        WithAnnotationDefaultValue withAnnotationDefaultValue = (WithAnnotationDefaultValue) obj;
                        return this.methodDescription.equals(withAnnotationDefaultValue.methodDescription) && this.annotationValue.equals(withAnnotationDefaultValue.annotationValue) && this.methodAttributeAppender.equals(withAnnotationDefaultValue.methodAttributeAppender);
                    }

                    public int hashCode() {
                        return ((((527 + this.methodDescription.hashCode()) * 31) + this.annotationValue.hashCode()) * 31) + this.methodAttributeAppender.hashCode();
                    }

                    public WithAnnotationDefaultValue(MethodDescription methodDescription2, AnnotationValue<?, ?> annotationValue2, MethodAttributeAppender methodAttributeAppender2) {
                        this.methodDescription = methodDescription2;
                        this.annotationValue = annotationValue2;
                        this.methodAttributeAppender = methodAttributeAppender2;
                    }

                    public MethodDescription getMethod() {
                        return this.methodDescription;
                    }

                    public Sort getSort() {
                        return Sort.DEFINED;
                    }

                    public Visibility getVisibility() {
                        return this.methodDescription.getVisibility();
                    }

                    public void applyHead(MethodVisitor methodVisitor) {
                        if (this.methodDescription.isDefaultValue(this.annotationValue)) {
                            AnnotationVisitor visitAnnotationDefault = methodVisitor.visitAnnotationDefault();
                            AnnotationAppender.Default.apply(visitAnnotationDefault, this.methodDescription.getReturnType().asErasure(), AnnotationAppender.NO_NAME, this.annotationValue.resolve());
                            visitAnnotationDefault.visitEnd();
                            return;
                        }
                        throw new IllegalStateException("Cannot set " + this.annotationValue + " as default for " + this.methodDescription);
                    }

                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender2 = this.methodAttributeAppender;
                        MethodDescription methodDescription2 = this.methodDescription;
                        methodAttributeAppender2.apply(methodVisitor, methodDescription2, factory.on(methodDescription2));
                    }

                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        throw new IllegalStateException("Cannot apply attributes for default value on " + this.methodDescription);
                    }

                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        throw new IllegalStateException("Cannot apply code for default value on " + this.methodDescription);
                    }

                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        throw new IllegalStateException("Cannot prepend code for default value on " + this.methodDescription);
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class OfVisibilityBridge extends ForDefinedMethod implements ByteCodeAppender {
                    private final MethodAttributeAppender attributeAppender;
                    private final MethodDescription bridgeTarget;
                    private final TypeDescription bridgeType;
                    private final MethodDescription visibilityBridge;

                    public void applyHead(MethodVisitor methodVisitor) {
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        OfVisibilityBridge ofVisibilityBridge = (OfVisibilityBridge) obj;
                        return this.visibilityBridge.equals(ofVisibilityBridge.visibilityBridge) && this.bridgeTarget.equals(ofVisibilityBridge.bridgeTarget) && this.bridgeType.equals(ofVisibilityBridge.bridgeType) && this.attributeAppender.equals(ofVisibilityBridge.attributeAppender);
                    }

                    public int hashCode() {
                        return ((((((527 + this.visibilityBridge.hashCode()) * 31) + this.bridgeTarget.hashCode()) * 31) + this.bridgeType.hashCode()) * 31) + this.attributeAppender.hashCode();
                    }

                    protected OfVisibilityBridge(MethodDescription methodDescription, MethodDescription methodDescription2, TypeDescription typeDescription, MethodAttributeAppender methodAttributeAppender) {
                        this.visibilityBridge = methodDescription;
                        this.bridgeTarget = methodDescription2;
                        this.bridgeType = typeDescription;
                        this.attributeAppender = methodAttributeAppender;
                    }

                    public static Record of(TypeDescription typeDescription, MethodDescription methodDescription, MethodAttributeAppender methodAttributeAppender) {
                        TypeDefinition typeDefinition = null;
                        if (methodDescription.isDefaultMethod()) {
                            TypeDescription asErasure = methodDescription.getDeclaringType().asErasure();
                            for (TypeDescription typeDescription2 : (TypeList) typeDescription.getInterfaces().asErasures().filter(ElementMatchers.isSubTypeOf(asErasure))) {
                                if (typeDefinition == null || asErasure.isAssignableTo(typeDefinition.asErasure())) {
                                    typeDefinition = typeDescription2;
                                }
                            }
                        }
                        if (typeDefinition == null) {
                            typeDefinition = typeDescription.getSuperClass();
                        }
                        return new OfVisibilityBridge(new VisibilityBridge(typeDescription, methodDescription), methodDescription, typeDefinition.asErasure(), methodAttributeAppender);
                    }

                    public MethodDescription getMethod() {
                        return this.visibilityBridge;
                    }

                    public Sort getSort() {
                        return Sort.IMPLEMENTED;
                    }

                    public Visibility getVisibility() {
                        return this.bridgeTarget.getVisibility();
                    }

                    public Record prepend(ByteCodeAppender byteCodeAppender) {
                        return new WithBody(this.visibilityBridge, new ByteCodeAppender.Compound(this, byteCodeAppender), this.attributeAppender, this.bridgeTarget.getVisibility());
                    }

                    public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                        applyAttributes(methodVisitor, factory);
                        methodVisitor.visitCode();
                        ByteCodeAppender.Size applyCode = applyCode(methodVisitor, context);
                        methodVisitor.visitMaxs(applyCode.getOperandStackSize(), applyCode.getLocalVariableSize());
                    }

                    public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                        MethodAttributeAppender methodAttributeAppender = this.attributeAppender;
                        MethodDescription methodDescription = this.visibilityBridge;
                        methodAttributeAppender.apply(methodVisitor, methodDescription, factory.on(methodDescription));
                    }

                    public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                        return apply(methodVisitor, context, this.visibilityBridge);
                    }

                    public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                        return new ByteCodeAppender.Simple(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), MethodInvocation.invoke(this.bridgeTarget).special(this.bridgeType), MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context, methodDescription);
                    }

                    protected static class VisibilityBridge extends MethodDescription.InDefinedShape.AbstractBase {
                        private final MethodDescription bridgeTarget;
                        private final TypeDescription instrumentedType;

                        protected VisibilityBridge(TypeDescription typeDescription, MethodDescription methodDescription) {
                            this.instrumentedType = typeDescription;
                            this.bridgeTarget = methodDescription;
                        }

                        public TypeDescription getDeclaringType() {
                            return this.instrumentedType;
                        }

                        public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                            return new ParameterList.Explicit.ForTypes((MethodDescription.InDefinedShape) this, (List<? extends TypeDefinition>) this.bridgeTarget.getParameters().asTypeList().asRawTypes());
                        }

                        public TypeDescription.Generic getReturnType() {
                            return this.bridgeTarget.getReturnType().asRawType();
                        }

                        public TypeList.Generic getExceptionTypes() {
                            return this.bridgeTarget.getExceptionTypes().asRawTypes();
                        }

                        public AnnotationValue<?, ?> getDefaultValue() {
                            return AnnotationValue.UNDEFINED;
                        }

                        public TypeList.Generic getTypeVariables() {
                            return new TypeList.Generic.Empty();
                        }

                        public AnnotationList getDeclaredAnnotations() {
                            return this.bridgeTarget.getDeclaredAnnotations();
                        }

                        public int getModifiers() {
                            return (this.bridgeTarget.getModifiers() | 4096 | 64) & -257;
                        }

                        public String getInternalName() {
                            return this.bridgeTarget.getName();
                        }
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class AccessBridgeWrapper implements Record {
                private final MethodAttributeAppender attributeAppender;
                private final MethodDescription bridgeTarget;
                private final Set<MethodDescription.TypeToken> bridgeTypes;
                private final Record delegate;
                private final TypeDescription instrumentedType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AccessBridgeWrapper accessBridgeWrapper = (AccessBridgeWrapper) obj;
                    return this.delegate.equals(accessBridgeWrapper.delegate) && this.instrumentedType.equals(accessBridgeWrapper.instrumentedType) && this.bridgeTarget.equals(accessBridgeWrapper.bridgeTarget) && this.bridgeTypes.equals(accessBridgeWrapper.bridgeTypes) && this.attributeAppender.equals(accessBridgeWrapper.attributeAppender);
                }

                public int hashCode() {
                    return ((((((((527 + this.delegate.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + this.bridgeTarget.hashCode()) * 31) + this.bridgeTypes.hashCode()) * 31) + this.attributeAppender.hashCode();
                }

                protected AccessBridgeWrapper(Record record, TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, MethodAttributeAppender methodAttributeAppender) {
                    this.delegate = record;
                    this.instrumentedType = typeDescription;
                    this.bridgeTarget = methodDescription;
                    this.bridgeTypes = set;
                    this.attributeAppender = methodAttributeAppender;
                }

                public static Record of(Record record, TypeDescription typeDescription, MethodDescription methodDescription, Set<MethodDescription.TypeToken> set, MethodAttributeAppender methodAttributeAppender) {
                    HashSet hashSet = new HashSet();
                    for (MethodDescription.TypeToken next : set) {
                        if (methodDescription.isBridgeCompatible(next)) {
                            hashSet.add(next);
                        }
                    }
                    if (!hashSet.isEmpty()) {
                        return (!typeDescription.isInterface() || record.getSort().isImplemented()) ? new AccessBridgeWrapper(record, typeDescription, methodDescription, hashSet, methodAttributeAppender) : record;
                    }
                    return record;
                }

                public Sort getSort() {
                    return this.delegate.getSort();
                }

                public MethodDescription getMethod() {
                    return this.bridgeTarget;
                }

                public Visibility getVisibility() {
                    return this.delegate.getVisibility();
                }

                public Record prepend(ByteCodeAppender byteCodeAppender) {
                    return new AccessBridgeWrapper(this.delegate.prepend(byteCodeAppender), this.instrumentedType, this.bridgeTarget, this.bridgeTypes, this.attributeAppender);
                }

                public void apply(ClassVisitor classVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    StackManipulation stackManipulation;
                    this.delegate.apply(classVisitor, context, factory);
                    for (MethodDescription.TypeToken accessorBridge : this.bridgeTypes) {
                        AccessorBridge accessorBridge2 = new AccessorBridge(this.bridgeTarget, accessorBridge, this.instrumentedType);
                        BridgeTarget bridgeTarget2 = new BridgeTarget(this.bridgeTarget, this.instrumentedType);
                        MethodVisitor visitMethod = classVisitor.visitMethod(accessorBridge2.getActualModifiers(true, getVisibility()), accessorBridge2.getInternalName(), accessorBridge2.getDescriptor(), MethodDescription.NON_GENERIC_SIGNATURE, accessorBridge2.getExceptionTypes().asErasures().toInternalNames());
                        if (visitMethod != null) {
                            this.attributeAppender.apply(visitMethod, accessorBridge2, factory.on(this.instrumentedType));
                            visitMethod.visitCode();
                            StackManipulation[] stackManipulationArr = new StackManipulation[4];
                            stackManipulationArr[0] = MethodVariableAccess.allArgumentsOf(accessorBridge2).asBridgeOf(bridgeTarget2).prependThisReference();
                            stackManipulationArr[1] = MethodInvocation.invoke((MethodDescription.InDefinedShape) bridgeTarget2).virtual(this.instrumentedType);
                            if (bridgeTarget2.getReturnType().asErasure().isAssignableTo(accessorBridge2.getReturnType().asErasure())) {
                                stackManipulation = StackManipulation.Trivial.INSTANCE;
                            } else {
                                stackManipulation = TypeCasting.to(accessorBridge2.getReturnType().asErasure());
                            }
                            stackManipulationArr[2] = stackManipulation;
                            stackManipulationArr[3] = MethodReturn.of(accessorBridge2.getReturnType());
                            ByteCodeAppender.Size apply = new ByteCodeAppender.Simple(stackManipulationArr).apply(visitMethod, context, accessorBridge2);
                            visitMethod.visitMaxs(apply.getOperandStackSize(), apply.getLocalVariableSize());
                            visitMethod.visitEnd();
                        }
                    }
                }

                public void applyHead(MethodVisitor methodVisitor) {
                    this.delegate.applyHead(methodVisitor);
                }

                public void applyBody(MethodVisitor methodVisitor, Implementation.Context context, AnnotationValueFilter.Factory factory) {
                    this.delegate.applyBody(methodVisitor, context, factory);
                }

                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                    this.delegate.applyAttributes(methodVisitor, factory);
                }

                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Implementation.Context context) {
                    return this.delegate.applyCode(methodVisitor, context);
                }

                protected static class AccessorBridge extends MethodDescription.InDefinedShape.AbstractBase {
                    private final MethodDescription bridgeTarget;
                    private final MethodDescription.TypeToken bridgeType;
                    private final TypeDescription instrumentedType;

                    protected AccessorBridge(MethodDescription methodDescription, MethodDescription.TypeToken typeToken, TypeDescription typeDescription) {
                        this.bridgeTarget = methodDescription;
                        this.bridgeType = typeToken;
                        this.instrumentedType = typeDescription;
                    }

                    public TypeDescription getDeclaringType() {
                        return this.instrumentedType;
                    }

                    public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                        return new ParameterList.Explicit.ForTypes((MethodDescription.InDefinedShape) this, (List<? extends TypeDefinition>) this.bridgeType.getParameterTypes());
                    }

                    public TypeDescription.Generic getReturnType() {
                        return this.bridgeType.getReturnType().asGenericType();
                    }

                    public TypeList.Generic getExceptionTypes() {
                        return this.bridgeTarget.getExceptionTypes().accept(TypeDescription.Generic.Visitor.TypeErasing.INSTANCE);
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

                    public int getModifiers() {
                        return (this.bridgeTarget.getModifiers() | 64 | 4096) & -1281;
                    }

                    public String getInternalName() {
                        return this.bridgeTarget.getInternalName();
                    }
                }

                protected static class BridgeTarget extends MethodDescription.InDefinedShape.AbstractBase {
                    private final MethodDescription bridgeTarget;
                    private final TypeDescription instrumentedType;

                    protected BridgeTarget(MethodDescription methodDescription, TypeDescription typeDescription) {
                        this.bridgeTarget = methodDescription;
                        this.instrumentedType = typeDescription;
                    }

                    public TypeDescription getDeclaringType() {
                        return this.instrumentedType;
                    }

                    public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                        return new ParameterList.ForTokens(this, this.bridgeTarget.getParameters().asTokenList(ElementMatchers.is((Object) this.instrumentedType)));
                    }

                    public TypeDescription.Generic getReturnType() {
                        return this.bridgeTarget.getReturnType();
                    }

                    public TypeList.Generic getExceptionTypes() {
                        return this.bridgeTarget.getExceptionTypes();
                    }

                    public AnnotationValue<?, ?> getDefaultValue() {
                        return this.bridgeTarget.getDefaultValue();
                    }

                    public TypeList.Generic getTypeVariables() {
                        return this.bridgeTarget.getTypeVariables();
                    }

                    public AnnotationList getDeclaredAnnotations() {
                        return this.bridgeTarget.getDeclaredAnnotations();
                    }

                    public int getModifiers() {
                        return this.bridgeTarget.getModifiers();
                    }

                    public String getInternalName() {
                        return this.bridgeTarget.getInternalName();
                    }
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static abstract class Default<S> implements TypeWriter<S> {
        protected static final String DUMP_FOLDER;
        /* access modifiers changed from: private */
        public static final String NO_REFERENCE = null;
        protected final AnnotationRetention annotationRetention;
        protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
        protected final AsmVisitorWrapper asmVisitorWrapper;
        protected final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
        protected final List<? extends DynamicType> auxiliaryTypes;
        protected final ClassFileVersion classFileVersion;
        protected final ClassWriterStrategy classWriterStrategy;
        protected final FieldPool fieldPool;
        protected final FieldList<FieldDescription.InDefinedShape> fields;
        protected final Implementation.Context.Factory implementationContextFactory;
        protected final MethodList<?> instrumentedMethods;
        protected final TypeDescription instrumentedType;
        protected final LoadedTypeInitializer loadedTypeInitializer;
        protected final MethodList<?> methods;
        protected final TypeAttributeAppender typeAttributeAppender;
        protected final TypeInitializer typeInitializer;
        protected final TypePool typePool;
        protected final TypeValidation typeValidation;

        /* access modifiers changed from: protected */
        public abstract Default<S>.UnresolvedType create(TypeInitializer typeInitializer2);

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Default defaultR = (Default) obj;
            return this.annotationRetention.equals(defaultR.annotationRetention) && this.typeValidation.equals(defaultR.typeValidation) && this.instrumentedType.equals(defaultR.instrumentedType) && this.classFileVersion.equals(defaultR.classFileVersion) && this.fieldPool.equals(defaultR.fieldPool) && this.auxiliaryTypes.equals(defaultR.auxiliaryTypes) && this.fields.equals(defaultR.fields) && this.methods.equals(defaultR.methods) && this.instrumentedMethods.equals(defaultR.instrumentedMethods) && this.loadedTypeInitializer.equals(defaultR.loadedTypeInitializer) && this.typeInitializer.equals(defaultR.typeInitializer) && this.typeAttributeAppender.equals(defaultR.typeAttributeAppender) && this.asmVisitorWrapper.equals(defaultR.asmVisitorWrapper) && this.annotationValueFilterFactory.equals(defaultR.annotationValueFilterFactory) && this.auxiliaryTypeNamingStrategy.equals(defaultR.auxiliaryTypeNamingStrategy) && this.implementationContextFactory.equals(defaultR.implementationContextFactory) && this.classWriterStrategy.equals(defaultR.classWriterStrategy) && this.typePool.equals(defaultR.typePool);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((((527 + this.instrumentedType.hashCode()) * 31) + this.classFileVersion.hashCode()) * 31) + this.fieldPool.hashCode()) * 31) + this.auxiliaryTypes.hashCode()) * 31) + this.fields.hashCode()) * 31) + this.methods.hashCode()) * 31) + this.instrumentedMethods.hashCode()) * 31) + this.loadedTypeInitializer.hashCode()) * 31) + this.typeInitializer.hashCode()) * 31) + this.typeAttributeAppender.hashCode()) * 31) + this.asmVisitorWrapper.hashCode()) * 31) + this.annotationValueFilterFactory.hashCode()) * 31) + this.annotationRetention.hashCode()) * 31) + this.auxiliaryTypeNamingStrategy.hashCode()) * 31) + this.implementationContextFactory.hashCode()) * 31) + this.typeValidation.hashCode()) * 31) + this.classWriterStrategy.hashCode()) * 31) + this.typePool.hashCode();
        }

        static {
            String str;
            try {
                str = (String) AccessController.doPrivileged(new GetSystemPropertyAction(TypeWriter.DUMP_PROPERTY));
            } catch (RuntimeException unused) {
                str = null;
            }
            DUMP_FOLDER = str;
        }

        protected Default(TypeDescription typeDescription, ClassFileVersion classFileVersion2, FieldPool fieldPool2, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, LoadedTypeInitializer loadedTypeInitializer2, TypeInitializer typeInitializer2, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, TypePool typePool2) {
            this.instrumentedType = typeDescription;
            this.classFileVersion = classFileVersion2;
            this.fieldPool = fieldPool2;
            this.auxiliaryTypes = list;
            this.fields = fieldList;
            this.methods = methodList;
            this.instrumentedMethods = methodList2;
            this.loadedTypeInitializer = loadedTypeInitializer2;
            this.typeInitializer = typeInitializer2;
            this.typeAttributeAppender = typeAttributeAppender2;
            this.asmVisitorWrapper = asmVisitorWrapper2;
            this.auxiliaryTypeNamingStrategy = namingStrategy;
            this.annotationValueFilterFactory = factory;
            this.annotationRetention = annotationRetention2;
            this.implementationContextFactory = factory2;
            this.typeValidation = typeValidation2;
            this.classWriterStrategy = classWriterStrategy2;
            this.typePool = typePool2;
        }

        public static <U> TypeWriter<U> forCreation(MethodRegistry.Compiled compiled, List<? extends DynamicType> list, FieldPool fieldPool2, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, ClassFileVersion classFileVersion2, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, TypePool typePool2) {
            FieldPool fieldPool3 = fieldPool2;
            ClassFileVersion classFileVersion3 = classFileVersion2;
            return new ForCreation(compiled.getInstrumentedType(), classFileVersion3, fieldPool3, compiled, list, compiled.getInstrumentedType().getDeclaredFields(), compiled.getMethods(), compiled.getInstrumentedMethods(), compiled.getLoadedTypeInitializer(), compiled.getTypeInitializer(), typeAttributeAppender2, asmVisitorWrapper2, factory, annotationRetention2, namingStrategy, factory2, typeValidation2, classWriterStrategy2, typePool2);
        }

        public static <U> TypeWriter<U> forRedefinition(MethodRegistry.Prepared prepared, List<? extends DynamicType> list, FieldPool fieldPool2, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, ClassFileVersion classFileVersion2, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, TypePool typePool2, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
            List<? extends DynamicType> list2 = list;
            FieldPool fieldPool3 = fieldPool2;
            TypeAttributeAppender typeAttributeAppender3 = typeAttributeAppender2;
            AsmVisitorWrapper asmVisitorWrapper3 = asmVisitorWrapper2;
            ClassFileVersion classFileVersion3 = classFileVersion2;
            AnnotationValueFilter.Factory factory3 = factory;
            AnnotationRetention annotationRetention3 = annotationRetention2;
            AuxiliaryType.NamingStrategy namingStrategy2 = namingStrategy;
            Implementation.Context.Factory factory4 = factory2;
            TypeValidation typeValidation3 = typeValidation2;
            ClassWriterStrategy classWriterStrategy3 = classWriterStrategy2;
            TypePool typePool3 = typePool2;
            TypeDescription typeDescription2 = typeDescription;
            ClassFileLocator classFileLocator2 = classFileLocator;
            return new ForInlining.WithFullProcessing(prepared.getInstrumentedType(), classFileVersion3, fieldPool3, list2, prepared.getInstrumentedType().getDeclaredFields(), prepared.getMethods(), prepared.getInstrumentedMethods(), prepared.getLoadedTypeInitializer(), prepared.getTypeInitializer(), typeAttributeAppender3, asmVisitorWrapper3, factory3, annotationRetention3, namingStrategy2, factory4, typeValidation3, classWriterStrategy3, typePool3, typeDescription2, classFileLocator2, prepared, SubclassImplementationTarget.Factory.LEVEL_TYPE, MethodRebaseResolver.Disabled.INSTANCE);
        }

        public static <U> TypeWriter<U> forRebasing(MethodRegistry.Prepared prepared, List<? extends DynamicType> list, FieldPool fieldPool2, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, ClassFileVersion classFileVersion2, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, TypePool typePool2, TypeDescription typeDescription, ClassFileLocator classFileLocator, MethodRebaseResolver methodRebaseResolver) {
            FieldPool fieldPool3 = fieldPool2;
            TypeAttributeAppender typeAttributeAppender3 = typeAttributeAppender2;
            AsmVisitorWrapper asmVisitorWrapper3 = asmVisitorWrapper2;
            ClassFileVersion classFileVersion3 = classFileVersion2;
            AnnotationValueFilter.Factory factory3 = factory;
            AnnotationRetention annotationRetention3 = annotationRetention2;
            AuxiliaryType.NamingStrategy namingStrategy2 = namingStrategy;
            Implementation.Context.Factory factory4 = factory2;
            TypeValidation typeValidation3 = typeValidation2;
            ClassWriterStrategy classWriterStrategy3 = classWriterStrategy2;
            TypePool typePool3 = typePool2;
            TypeDescription typeDescription2 = typeDescription;
            ClassFileLocator classFileLocator2 = classFileLocator;
            TypeDescription instrumentedType2 = prepared.getInstrumentedType();
            List<S> of = CompoundList.of(list, (List<? extends DynamicType>) methodRebaseResolver.getAuxiliaryTypes());
            FieldList<FieldDescription.InDefinedShape> declaredFields = prepared.getInstrumentedType().getDeclaredFields();
            MethodList<?> methods2 = prepared.getMethods();
            MethodList<?> instrumentedMethods2 = prepared.getInstrumentedMethods();
            LoadedTypeInitializer loadedTypeInitializer2 = prepared.getLoadedTypeInitializer();
            TypeInitializer typeInitializer2 = prepared.getTypeInitializer();
            RebaseImplementationTarget.Factory factory5 = r0;
            RebaseImplementationTarget.Factory factory6 = new RebaseImplementationTarget.Factory(methodRebaseResolver);
            return new ForInlining.WithFullProcessing(instrumentedType2, classFileVersion3, fieldPool3, of, declaredFields, methods2, instrumentedMethods2, loadedTypeInitializer2, typeInitializer2, typeAttributeAppender3, asmVisitorWrapper3, factory3, annotationRetention3, namingStrategy2, factory4, typeValidation3, classWriterStrategy3, typePool3, typeDescription2, classFileLocator2, prepared, factory5, methodRebaseResolver);
        }

        public static <U> TypeWriter<U> forDecoration(TypeDescription typeDescription, ClassFileVersion classFileVersion2, List<? extends DynamicType> list, List<? extends MethodDescription> list2, TypeAttributeAppender typeAttributeAppender2, AsmVisitorWrapper asmVisitorWrapper2, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention2, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation2, ClassWriterStrategy classWriterStrategy2, TypePool typePool2, ClassFileLocator classFileLocator) {
            return new ForInlining.WithDecorationOnly(typeDescription, classFileVersion2, list, new MethodList.Explicit(list2), typeAttributeAppender2, asmVisitorWrapper2, factory, annotationRetention2, namingStrategy, factory2, typeValidation2, classWriterStrategy2, typePool2, classFileLocator);
        }

        public DynamicType.Unloaded<S> make(TypeResolutionStrategy.Resolved resolved) {
            Default<S>.UnresolvedType create = create(resolved.injectedInto(this.typeInitializer));
            ClassDumpAction.dump(DUMP_FOLDER, this.instrumentedType, false, create.getBinaryRepresentation());
            return create.toDynamicType(resolved);
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class UnresolvedType {
            private final List<? extends DynamicType> auxiliaryTypes;
            private final byte[] binaryRepresentation;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                UnresolvedType unresolvedType = (UnresolvedType) obj;
                return Arrays.equals(this.binaryRepresentation, unresolvedType.binaryRepresentation) && this.auxiliaryTypes.equals(unresolvedType.auxiliaryTypes) && Default.this.equals(Default.this);
            }

            public int hashCode() {
                return ((((527 + Arrays.hashCode(this.binaryRepresentation)) * 31) + this.auxiliaryTypes.hashCode()) * 31) + Default.this.hashCode();
            }

            protected UnresolvedType(byte[] bArr, List<? extends DynamicType> list) {
                this.binaryRepresentation = bArr;
                this.auxiliaryTypes = list;
            }

            /* access modifiers changed from: protected */
            public DynamicType.Unloaded<S> toDynamicType(TypeResolutionStrategy.Resolved resolved) {
                return new DynamicType.Default.Unloaded(Default.this.instrumentedType, this.binaryRepresentation, Default.this.loadedTypeInitializer, CompoundList.of(Default.this.auxiliaryTypes, this.auxiliaryTypes), resolved);
            }

            /* access modifiers changed from: protected */
            public byte[] getBinaryRepresentation() {
                return this.binaryRepresentation;
            }
        }

        protected static class ValidatingClassVisitor extends ClassVisitor {
            private static final FieldVisitor IGNORE_FIELD = null;
            private static final MethodVisitor IGNORE_METHOD = null;
            private static final String NO_PARAMETERS = "()";
            private static final String RETURNS_VOID = "V";
            private static final String STRING_DESCRIPTOR = "Ljava/lang/String;";
            /* access modifiers changed from: private */
            public Constraint constraint;

            protected ValidatingClassVisitor(ClassVisitor classVisitor) {
                super(OpenedClassReader.ASM_API, classVisitor);
            }

            protected static ClassVisitor of(ClassVisitor classVisitor, TypeValidation typeValidation) {
                return typeValidation.isEnabled() ? new ValidatingClassVisitor(classVisitor) : classVisitor;
            }

            public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                ClassFileVersion ofMinorMajor = ClassFileVersion.ofMinorMajor(i);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new Constraint.ForClassFileVersion(ofMinorMajor));
                if (str.endsWith("/package-info")) {
                    arrayList.add(Constraint.ForPackageType.INSTANCE);
                } else if ((i2 & 8192) != 0) {
                    if (ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V5)) {
                        arrayList.add(ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V8) ? Constraint.ForAnnotation.JAVA_8 : Constraint.ForAnnotation.CLASSIC);
                    } else {
                        throw new IllegalStateException("Cannot define an annotation type for class file version " + ofMinorMajor);
                    }
                } else if ((i2 & 512) != 0) {
                    arrayList.add(ofMinorMajor.isAtLeast(ClassFileVersion.JAVA_V8) ? Constraint.ForInterface.JAVA_8 : Constraint.ForInterface.CLASSIC);
                } else if ((i2 & 1024) != 0) {
                    arrayList.add(Constraint.ForClass.ABSTRACT);
                } else {
                    arrayList.add(Constraint.ForClass.MANIFEST);
                }
                Constraint.Compound compound = new Constraint.Compound(arrayList);
                this.constraint = compound;
                boolean z = true;
                boolean z2 = strArr != null;
                if (str2 == null) {
                    z = false;
                }
                compound.assertType(i2, z2, z);
                super.visit(i, i2, str, str2, str3, strArr);
            }

            public AnnotationVisitor visitAnnotation(String str, boolean z) {
                this.constraint.assertAnnotation();
                return super.visitAnnotation(str, z);
            }

            public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                this.constraint.assertTypeAnnotation();
                return super.visitTypeAnnotation(i, typePath, str, z);
            }

            public void visitNestHost(String str) {
                this.constraint.assertNestMate();
                super.visitNestHost(str);
            }

            public void visitNestMember(String str) {
                this.constraint.assertNestMate();
                super.visitNestMember(str);
            }

            public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
                Class cls;
                int i2;
                int i3;
                if (obj != null) {
                    char charAt = str2.charAt(0);
                    if (charAt != 'F') {
                        if (!(charAt == 'S' || charAt == 'Z' || charAt == 'I')) {
                            if (charAt != 'J') {
                                switch (charAt) {
                                    case 'B':
                                    case 'C':
                                        break;
                                    case 'D':
                                        cls = Double.class;
                                        break;
                                    default:
                                        if (str2.equals(STRING_DESCRIPTOR)) {
                                            cls = String.class;
                                            break;
                                        } else {
                                            throw new IllegalStateException("Cannot define a default value for type of field " + str);
                                        }
                                }
                            } else {
                                cls = Long.class;
                            }
                        }
                        cls = Integer.class;
                    } else {
                        cls = Float.class;
                    }
                    if (!cls.isInstance(obj)) {
                        throw new IllegalStateException("Field " + str + " defines an incompatible default value " + obj);
                    } else if (cls == Integer.class) {
                        char charAt2 = str2.charAt(0);
                        if (charAt2 == 'B') {
                            i3 = -128;
                            i2 = 127;
                        } else if (charAt2 == 'C') {
                            i2 = 65535;
                            i3 = 0;
                        } else if (charAt2 == 'S') {
                            i3 = -32768;
                            i2 = Advice.MethodSizeHandler.UNDEFINED_SIZE;
                        } else if (charAt2 != 'Z') {
                            i3 = Integer.MIN_VALUE;
                            i2 = Integer.MAX_VALUE;
                        } else {
                            i3 = 0;
                            i2 = 1;
                        }
                        int intValue = ((Integer) obj).intValue();
                        if (intValue < i3 || intValue > i2) {
                            throw new IllegalStateException("Field " + str + " defines an incompatible default value " + obj);
                        }
                    }
                }
                this.constraint.assertField(str, (i & 1) != 0, (i & 8) != 0, (i & 16) != 0, str3 != null);
                FieldVisitor visitField = super.visitField(i, str, str2, str3, obj);
                return visitField == null ? IGNORE_FIELD : new ValidatingFieldVisitor(visitField);
            }

            public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
                int i2 = i;
                String str4 = str;
                String str5 = str2;
                this.constraint.assertMethod(str, (i2 & 1024) != 0, (i2 & 1) != 0, (i2 & 2) != 0, (i2 & 8) != 0, !str4.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME) && !str4.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME) && (i2 & 10) == 0, str4.equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME), !str5.startsWith(NO_PARAMETERS) || str5.endsWith("V"), str3 != null);
                MethodVisitor visitMethod = super.visitMethod(i, str, str2, str3, strArr);
                return visitMethod == null ? IGNORE_METHOD : new ValidatingMethodVisitor(visitMethod, str4);
            }

            protected interface Constraint {
                void assertAnnotation();

                void assertDefaultMethodCall();

                void assertDefaultValue(String str);

                void assertDynamicValueInConstantPool();

                void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4);

                void assertHandleInConstantPool();

                void assertInvokeDynamic();

                void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8);

                void assertMethodTypeInConstantPool();

                void assertNestMate();

                void assertSubRoutine();

                void assertType(int i, boolean z, boolean z2);

                void assertTypeAnnotation();

                void assertTypeInConstantPool();

                public enum ForClass implements Constraint {
                    MANIFEST(true),
                    ABSTRACT(false);
                    
                    private final boolean manifestType;

                    public void assertAnnotation() {
                    }

                    public void assertDefaultMethodCall() {
                    }

                    public void assertDynamicValueInConstantPool() {
                    }

                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                    }

                    public void assertHandleInConstantPool() {
                    }

                    public void assertInvokeDynamic() {
                    }

                    public void assertMethodTypeInConstantPool() {
                    }

                    public void assertNestMate() {
                    }

                    public void assertSubRoutine() {
                    }

                    public void assertType(int i, boolean z, boolean z2) {
                    }

                    public void assertTypeAnnotation() {
                    }

                    public void assertTypeInConstantPool() {
                    }

                    private ForClass(boolean z) {
                        this.manifestType = z;
                    }

                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (z && this.manifestType) {
                            throw new IllegalStateException("Cannot define abstract method '" + str + "' for non-abstract class");
                        }
                    }

                    public void assertDefaultValue(String str) {
                        throw new IllegalStateException("Cannot define default value for '" + str + "' for non-annotation type");
                    }
                }

                public enum ForPackageType implements Constraint {
                    INSTANCE;

                    public void assertAnnotation() {
                    }

                    public void assertDefaultMethodCall() {
                    }

                    public void assertDefaultValue(String str) {
                    }

                    public void assertDynamicValueInConstantPool() {
                    }

                    public void assertHandleInConstantPool() {
                    }

                    public void assertInvokeDynamic() {
                    }

                    public void assertMethodTypeInConstantPool() {
                    }

                    public void assertNestMate() {
                    }

                    public void assertSubRoutine() {
                    }

                    public void assertTypeAnnotation() {
                    }

                    public void assertTypeInConstantPool() {
                    }

                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        throw new IllegalStateException("Cannot define a field for a package description type");
                    }

                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        throw new IllegalStateException("Cannot define a method for a package description type");
                    }

                    public void assertType(int i, boolean z, boolean z2) {
                        if (i != 5632) {
                            throw new IllegalStateException("A package description type must define 5632 as modifier");
                        } else if (z) {
                            throw new IllegalStateException("Cannot implement interface for package type");
                        }
                    }
                }

                public enum ForInterface implements Constraint {
                    CLASSIC(true),
                    JAVA_8(false);
                    
                    private final boolean classic;

                    public void assertAnnotation() {
                    }

                    public void assertDefaultMethodCall() {
                    }

                    public void assertDynamicValueInConstantPool() {
                    }

                    public void assertHandleInConstantPool() {
                    }

                    public void assertInvokeDynamic() {
                    }

                    public void assertMethodTypeInConstantPool() {
                    }

                    public void assertNestMate() {
                    }

                    public void assertSubRoutine() {
                    }

                    public void assertType(int i, boolean z, boolean z2) {
                    }

                    public void assertTypeAnnotation() {
                    }

                    public void assertTypeInConstantPool() {
                    }

                    private ForInterface(boolean z) {
                        this.classic = z;
                    }

                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (!z2 || !z || !z3) {
                            throw new IllegalStateException("Cannot only define public, static, final field '" + str + "' for interface type");
                        }
                    }

                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            return;
                        }
                        if (z6) {
                            throw new IllegalStateException("Cannot define constructor for interface type");
                        } else if (this.classic && !z2) {
                            throw new IllegalStateException("Cannot define non-public method '" + str + "' for interface type");
                        } else if (this.classic && !z5) {
                            throw new IllegalStateException("Cannot define non-virtual method '" + str + "' for a pre-Java 8 interface type");
                        } else if (this.classic && !z) {
                            throw new IllegalStateException("Cannot define default method '" + str + "' for pre-Java 8 interface type");
                        }
                    }

                    public void assertDefaultValue(String str) {
                        throw new IllegalStateException("Cannot define default value for '" + str + "' for non-annotation type");
                    }
                }

                public enum ForAnnotation implements Constraint {
                    CLASSIC(true),
                    JAVA_8(false);
                    
                    private final boolean classic;

                    public void assertAnnotation() {
                    }

                    public void assertDefaultMethodCall() {
                    }

                    public void assertDefaultValue(String str) {
                    }

                    public void assertDynamicValueInConstantPool() {
                    }

                    public void assertHandleInConstantPool() {
                    }

                    public void assertInvokeDynamic() {
                    }

                    public void assertMethodTypeInConstantPool() {
                    }

                    public void assertNestMate() {
                    }

                    public void assertSubRoutine() {
                    }

                    public void assertTypeAnnotation() {
                    }

                    public void assertTypeInConstantPool() {
                    }

                    private ForAnnotation(boolean z) {
                        this.classic = z;
                    }

                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (!z2 || !z || !z3) {
                            throw new IllegalStateException("Cannot only define public, static, final field '" + str + "' for interface type");
                        }
                    }

                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (str.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            return;
                        }
                        if (z6) {
                            throw new IllegalStateException("Cannot define constructor for interface type");
                        } else if (this.classic && !z5) {
                            throw new IllegalStateException("Cannot define non-virtual method '" + str + "' for a pre-Java 8 annotation type");
                        } else if (!z4 && z7) {
                            throw new IllegalStateException("Cannot define method '" + str + "' with the given signature as an annotation type method");
                        }
                    }

                    public void assertType(int i, boolean z, boolean z2) {
                        if ((i & 512) == 0) {
                            throw new IllegalStateException("Cannot define annotation type without interface modifier");
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class ForClassFileVersion implements Constraint {
                    private final ClassFileVersion classFileVersion;

                    public void assertDefaultValue(String str) {
                    }

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.classFileVersion.equals(((ForClassFileVersion) obj).classFileVersion);
                    }

                    public int hashCode() {
                        return 527 + this.classFileVersion.hashCode();
                    }

                    protected ForClassFileVersion(ClassFileVersion classFileVersion2) {
                        this.classFileVersion = classFileVersion2;
                    }

                    public void assertType(int i, boolean z, boolean z2) {
                        if ((i & 8192) != 0 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define annotation type for class file version " + this.classFileVersion);
                        } else if (z2 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define a generic type for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        if (z4 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define generic field '" + str + "' for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        if (z8 && !this.classFileVersion.isAtLeast(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot define generic method '" + str + "' for class file version " + this.classFileVersion);
                        } else if (!z5 && z) {
                            throw new IllegalStateException("Cannot define static or non-virtual method '" + str + "' to be abstract");
                        }
                    }

                    public void assertAnnotation() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write annotations for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertTypeAnnotation() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write type annotations for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertDefaultMethodCall() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V8)) {
                            throw new IllegalStateException("Cannot invoke default method for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertTypeInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write type to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertMethodTypeInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write method type to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertHandleInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write method handle to constant pool for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertInvokeDynamic() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V7)) {
                            throw new IllegalStateException("Cannot write invoke dynamic instruction for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertSubRoutine() {
                        if (this.classFileVersion.isGreaterThan(ClassFileVersion.JAVA_V5)) {
                            throw new IllegalStateException("Cannot write subroutine for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertDynamicValueInConstantPool() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V11)) {
                            throw new IllegalStateException("Cannot write dynamic constant for class file version " + this.classFileVersion);
                        }
                    }

                    public void assertNestMate() {
                        if (this.classFileVersion.isLessThan(ClassFileVersion.JAVA_V11)) {
                            throw new IllegalStateException("Cannot define nest mate for class file version " + this.classFileVersion);
                        }
                    }
                }

                @HashCodeAndEqualsPlugin.Enhance
                public static class Compound implements Constraint {
                    private final List<Constraint> constraints = new ArrayList();

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.constraints.equals(((Compound) obj).constraints);
                    }

                    public int hashCode() {
                        return 527 + this.constraints.hashCode();
                    }

                    public Compound(List<? extends Constraint> list) {
                        for (Constraint constraint : list) {
                            if (constraint instanceof Compound) {
                                this.constraints.addAll(((Compound) constraint).constraints);
                            } else {
                                this.constraints.add(constraint);
                            }
                        }
                    }

                    public void assertType(int i, boolean z, boolean z2) {
                        for (Constraint assertType : this.constraints) {
                            assertType.assertType(i, z, z2);
                        }
                    }

                    public void assertField(String str, boolean z, boolean z2, boolean z3, boolean z4) {
                        for (Constraint assertField : this.constraints) {
                            assertField.assertField(str, z, z2, z3, z4);
                        }
                    }

                    public void assertMethod(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                        for (Constraint assertMethod : this.constraints) {
                            assertMethod.assertMethod(str, z, z2, z3, z4, z5, z6, z7, z8);
                        }
                    }

                    public void assertDefaultValue(String str) {
                        for (Constraint assertDefaultValue : this.constraints) {
                            assertDefaultValue.assertDefaultValue(str);
                        }
                    }

                    public void assertDefaultMethodCall() {
                        for (Constraint assertDefaultMethodCall : this.constraints) {
                            assertDefaultMethodCall.assertDefaultMethodCall();
                        }
                    }

                    public void assertAnnotation() {
                        for (Constraint assertAnnotation : this.constraints) {
                            assertAnnotation.assertAnnotation();
                        }
                    }

                    public void assertTypeAnnotation() {
                        for (Constraint assertTypeAnnotation : this.constraints) {
                            assertTypeAnnotation.assertTypeAnnotation();
                        }
                    }

                    public void assertTypeInConstantPool() {
                        for (Constraint assertTypeInConstantPool : this.constraints) {
                            assertTypeInConstantPool.assertTypeInConstantPool();
                        }
                    }

                    public void assertMethodTypeInConstantPool() {
                        for (Constraint assertMethodTypeInConstantPool : this.constraints) {
                            assertMethodTypeInConstantPool.assertMethodTypeInConstantPool();
                        }
                    }

                    public void assertHandleInConstantPool() {
                        for (Constraint assertHandleInConstantPool : this.constraints) {
                            assertHandleInConstantPool.assertHandleInConstantPool();
                        }
                    }

                    public void assertInvokeDynamic() {
                        for (Constraint assertInvokeDynamic : this.constraints) {
                            assertInvokeDynamic.assertInvokeDynamic();
                        }
                    }

                    public void assertSubRoutine() {
                        for (Constraint assertSubRoutine : this.constraints) {
                            assertSubRoutine.assertSubRoutine();
                        }
                    }

                    public void assertDynamicValueInConstantPool() {
                        for (Constraint assertDynamicValueInConstantPool : this.constraints) {
                            assertDynamicValueInConstantPool.assertDynamicValueInConstantPool();
                        }
                    }

                    public void assertNestMate() {
                        for (Constraint assertNestMate : this.constraints) {
                            assertNestMate.assertNestMate();
                        }
                    }
                }
            }

            protected class ValidatingFieldVisitor extends FieldVisitor {
                protected ValidatingFieldVisitor(FieldVisitor fieldVisitor) {
                    super(OpenedClassReader.ASM_API, fieldVisitor);
                }

                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    ValidatingClassVisitor.this.constraint.assertAnnotation();
                    return super.visitAnnotation(str, z);
                }
            }

            protected class ValidatingMethodVisitor extends MethodVisitor {
                private final String name;

                protected ValidatingMethodVisitor(MethodVisitor methodVisitor, String str) {
                    super(OpenedClassReader.ASM_API, methodVisitor);
                    this.name = str;
                }

                public AnnotationVisitor visitAnnotation(String str, boolean z) {
                    ValidatingClassVisitor.this.constraint.assertAnnotation();
                    return super.visitAnnotation(str, z);
                }

                public AnnotationVisitor visitAnnotationDefault() {
                    ValidatingClassVisitor.this.constraint.assertDefaultValue(this.name);
                    return super.visitAnnotationDefault();
                }

                public void visitLdcInsn(Object obj) {
                    if (obj instanceof Type) {
                        switch (((Type) obj).getSort()) {
                            case 9:
                            case 10:
                                ValidatingClassVisitor.this.constraint.assertTypeInConstantPool();
                                break;
                            case 11:
                                ValidatingClassVisitor.this.constraint.assertMethodTypeInConstantPool();
                                break;
                        }
                    } else if (obj instanceof Handle) {
                        ValidatingClassVisitor.this.constraint.assertHandleInConstantPool();
                    } else if (obj instanceof ConstantDynamic) {
                        ValidatingClassVisitor.this.constraint.assertDynamicValueInConstantPool();
                    }
                    super.visitLdcInsn(obj);
                }

                public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
                    if (z && i == 183) {
                        ValidatingClassVisitor.this.constraint.assertDefaultMethodCall();
                    }
                    super.visitMethodInsn(i, str, str2, str3, z);
                }

                public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object[] objArr) {
                    ValidatingClassVisitor.this.constraint.assertInvokeDynamic();
                    for (Object obj : objArr) {
                        if (obj instanceof ConstantDynamic) {
                            ValidatingClassVisitor.this.constraint.assertDynamicValueInConstantPool();
                        }
                    }
                    super.visitInvokeDynamicInsn(str, str2, handle, objArr);
                }

                public void visitJumpInsn(int i, Label label) {
                    if (i == 168) {
                        ValidatingClassVisitor.this.constraint.assertSubRoutine();
                    }
                    super.visitJumpInsn(i, label);
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class ForInlining<U> extends Default<U> {
            /* access modifiers changed from: private */
            public static final AnnotationVisitor IGNORE_ANNOTATION = null;
            /* access modifiers changed from: private */
            public static final FieldVisitor IGNORE_FIELD = null;
            /* access modifiers changed from: private */
            public static final MethodVisitor IGNORE_METHOD = null;
            protected final ClassFileLocator classFileLocator;
            protected final TypeDescription originalType;

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
                ForInlining forInlining = (ForInlining) obj;
                return this.originalType.equals(forInlining.originalType) && this.classFileLocator.equals(forInlining.classFileLocator);
            }

            public int hashCode() {
                return (((super.hashCode() * 31) + this.originalType.hashCode()) * 31) + this.classFileLocator.hashCode();
            }

            /* access modifiers changed from: protected */
            public abstract ClassVisitor writeTo(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2);

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            protected ForInlining(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, TypeDescription typeDescription2, ClassFileLocator classFileLocator2) {
                super(typeDescription, classFileVersion, fieldPool, list, fieldList, methodList, methodList2, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool);
                this.originalType = typeDescription2;
                this.classFileLocator = classFileLocator2;
            }

            /* access modifiers changed from: protected */
            public Default<U>.UnresolvedType create(TypeInitializer typeInitializer) {
                try {
                    int mergeWriter = this.asmVisitorWrapper.mergeWriter(0);
                    int mergeReader = this.asmVisitorWrapper.mergeReader(0);
                    byte[] resolve = this.classFileLocator.locate(this.originalType.getName()).resolve();
                    ClassDumpAction.dump(DUMP_FOLDER, this.instrumentedType, true, resolve);
                    ClassReader of = OpenedClassReader.of(resolve);
                    ClassWriter resolve2 = this.classWriterStrategy.resolve(mergeWriter, this.typePool, of);
                    ContextRegistry contextRegistry = new ContextRegistry();
                    of.accept(writeTo(ValidatingClassVisitor.of(resolve2, this.typeValidation), typeInitializer, contextRegistry, mergeWriter, mergeReader), mergeReader);
                    return new UnresolvedType(resolve2.toByteArray(), contextRegistry.getAuxiliaryTypes());
                } catch (IOException e) {
                    throw new RuntimeException("The class file could not be written", e);
                }
            }

            protected static class ContextRegistry {
                private Implementation.Context.ExtractableView implementationContext;

                protected ContextRegistry() {
                }

                public void setImplementationContext(Implementation.Context.ExtractableView extractableView) {
                    this.implementationContext = extractableView;
                }

                public List<DynamicType> getAuxiliaryTypes() {
                    return this.implementationContext.getAuxiliaryTypes();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class WithFullProcessing<V> extends ForInlining<V> {
                /* access modifiers changed from: private */
                public final Implementation.Target.Factory implementationTargetFactory;
                /* access modifiers changed from: private */
                public final MethodRebaseResolver methodRebaseResolver;
                /* access modifiers changed from: private */
                public final MethodRegistry.Prepared methodRegistry;

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
                    WithFullProcessing withFullProcessing = (WithFullProcessing) obj;
                    return this.methodRegistry.equals(withFullProcessing.methodRegistry) && this.implementationTargetFactory.equals(withFullProcessing.implementationTargetFactory) && this.methodRebaseResolver.equals(withFullProcessing.methodRebaseResolver);
                }

                public int hashCode() {
                    return (((((super.hashCode() * 31) + this.methodRegistry.hashCode()) * 31) + this.implementationTargetFactory.hashCode()) * 31) + this.methodRebaseResolver.hashCode();
                }

                /* JADX INFO: super call moved to the top of the method (can break code semantics) */
                protected WithFullProcessing(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool, TypeDescription typeDescription2, ClassFileLocator classFileLocator, MethodRegistry.Prepared prepared, Implementation.Target.Factory factory3, MethodRebaseResolver methodRebaseResolver2) {
                    super(typeDescription, classFileVersion, fieldPool, list, fieldList, methodList, methodList2, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool, typeDescription2, classFileLocator);
                    this.methodRegistry = prepared;
                    this.implementationTargetFactory = factory3;
                    this.methodRebaseResolver = methodRebaseResolver2;
                }

                /* access modifiers changed from: protected */
                public ClassVisitor writeTo(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2) {
                    RedefinitionClassVisitor redefinitionClassVisitor = new RedefinitionClassVisitor(classVisitor, typeInitializer, contextRegistry, i, i2);
                    return this.originalType.getName().equals(this.instrumentedType.getName()) ? redefinitionClassVisitor : new OpenedClassRemapper(redefinitionClassVisitor, new SimpleRemapper(this.originalType.getInternalName(), this.instrumentedType.getInternalName()));
                }

                protected static class OpenedClassRemapper extends ClassRemapper {
                    protected OpenedClassRemapper(ClassVisitor classVisitor, Remapper remapper) {
                        super(OpenedClassReader.ASM_API, classVisitor, remapper);
                    }
                }

                protected interface InitializationHandler {
                    void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView);

                    public static class Creating extends TypeInitializer.Drain.Default implements InitializationHandler {
                        protected Creating(TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory) {
                            super(typeDescription, methodPool, factory);
                        }

                        public void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                            extractableView.drain(this, classVisitor, this.annotationValueFilterFactory);
                        }
                    }

                    public static abstract class Appending extends MethodVisitor implements InitializationHandler, TypeInitializer.Drain {
                        protected final AnnotationValueFilter.Factory annotationValueFilterFactory;
                        protected final FrameWriter frameWriter;
                        protected final TypeDescription instrumentedType;
                        protected int localVariableLength;
                        protected final MethodPool.Record record;
                        protected int stackSize;

                        /* access modifiers changed from: protected */
                        public abstract void onComplete(Implementation.Context context);

                        /* access modifiers changed from: protected */
                        public abstract void onStart();

                        public abstract void visitEnd();

                        protected Appending(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record2, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                            this.instrumentedType = typeDescription;
                            this.record = record2;
                            this.annotationValueFilterFactory = factory;
                            if (!z) {
                                this.frameWriter = FrameWriter.NoOp.INSTANCE;
                            } else if (z2) {
                                this.frameWriter = FrameWriter.Expanding.INSTANCE;
                            } else {
                                this.frameWriter = new FrameWriter.Active();
                            }
                        }

                        protected static InitializationHandler of(boolean z, MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z2, boolean z3) {
                            if (z) {
                                return withDrain(methodVisitor, typeDescription, methodPool, factory, z2, z3);
                            }
                            return withoutDrain(methodVisitor, typeDescription, methodPool, factory, z2, z3);
                        }

                        private static WithDrain withDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            MethodPool.Record target = methodPool.target(new MethodDescription.Latent.TypeInitializer(typeDescription));
                            return target.getSort().isImplemented() ? new WithDrain.WithActiveRecord(methodVisitor, typeDescription, target, factory, z, z2) : new WithDrain.WithoutActiveRecord(methodVisitor, typeDescription, target, factory, z, z2);
                        }

                        private static WithoutDrain withoutDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool methodPool, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                            MethodPool.Record target = methodPool.target(new MethodDescription.Latent.TypeInitializer(typeDescription));
                            return target.getSort().isImplemented() ? new WithoutDrain.WithActiveRecord(methodVisitor, typeDescription, target, factory, z, z2) : new WithoutDrain.WithoutActiveRecord(methodVisitor, typeDescription, target, factory);
                        }

                        public void visitCode() {
                            this.record.applyAttributes(this.mv, this.annotationValueFilterFactory);
                            super.visitCode();
                            onStart();
                        }

                        public void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2) {
                            super.visitFrame(i, i2, objArr, i3, objArr2);
                            this.frameWriter.onFrame(i, i2);
                        }

                        public void visitMaxs(int i, int i2) {
                            this.stackSize = i;
                            this.localVariableLength = i2;
                        }

                        public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context) {
                            ByteCodeAppender.Size apply = typeInitializer.apply(this.mv, context, new MethodDescription.Latent.TypeInitializer(this.instrumentedType));
                            this.stackSize = Math.max(this.stackSize, apply.getOperandStackSize());
                            this.localVariableLength = Math.max(this.localVariableLength, apply.getLocalVariableSize());
                            onComplete(context);
                        }

                        public void complete(ClassVisitor classVisitor, Implementation.Context.ExtractableView extractableView) {
                            extractableView.drain(this, classVisitor, this.annotationValueFilterFactory);
                            this.mv.visitMaxs(this.stackSize, this.localVariableLength);
                            this.mv.visitEnd();
                        }

                        protected interface FrameWriter {
                            public static final Object[] EMPTY = new Object[0];

                            public enum NoOp implements FrameWriter {
                                INSTANCE;

                                public void emitFrame(MethodVisitor methodVisitor) {
                                }

                                public void onFrame(int i, int i2) {
                                }
                            }

                            void emitFrame(MethodVisitor methodVisitor);

                            void onFrame(int i, int i2);

                            public enum Expanding implements FrameWriter {
                                INSTANCE;

                                public void onFrame(int i, int i2) {
                                }

                                public void emitFrame(MethodVisitor methodVisitor) {
                                    methodVisitor.visitFrame(-1, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                                }
                            }

                            public static class Active implements FrameWriter {
                                private int currentLocalVariableLength;

                                public void onFrame(int i, int i2) {
                                    if (i == -1 || i == 0) {
                                        this.currentLocalVariableLength = i2;
                                    } else if (i == 1) {
                                        this.currentLocalVariableLength += i2;
                                    } else if (i == 2) {
                                        this.currentLocalVariableLength -= i2;
                                    } else if (i != 3 && i != 4) {
                                        throw new IllegalStateException("Unexpected frame type: " + i);
                                    }
                                }

                                public void emitFrame(MethodVisitor methodVisitor) {
                                    int i = this.currentLocalVariableLength;
                                    if (i == 0) {
                                        methodVisitor.visitFrame(3, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                                    } else if (i > 3) {
                                        methodVisitor.visitFrame(0, EMPTY.length, EMPTY, EMPTY.length, EMPTY);
                                    } else {
                                        methodVisitor.visitFrame(2, i, EMPTY, EMPTY.length, EMPTY);
                                    }
                                    this.currentLocalVariableLength = 0;
                                }
                            }
                        }

                        protected static abstract class WithoutDrain extends Appending {
                            /* access modifiers changed from: protected */
                            public void onStart() {
                            }

                            public void visitEnd() {
                            }

                            protected WithoutDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                super(methodVisitor, typeDescription, record, factory, z, z2);
                            }

                            protected static class WithoutActiveRecord extends WithoutDrain {
                                /* access modifiers changed from: protected */
                                public void onComplete(Implementation.Context context) {
                                }

                                protected WithoutActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory) {
                                    super(methodVisitor, typeDescription, record, factory, false, false);
                                }
                            }

                            protected static class WithActiveRecord extends WithoutDrain {
                                private final Label label = new Label();

                                protected WithActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                    super(methodVisitor, typeDescription, record, factory, z, z2);
                                }

                                public void visitInsn(int i) {
                                    if (i == 177) {
                                        this.mv.visitJumpInsn(167, this.label);
                                    } else {
                                        super.visitInsn(i);
                                    }
                                }

                                /* access modifiers changed from: protected */
                                public void onComplete(Implementation.Context context) {
                                    this.mv.visitLabel(this.label);
                                    this.frameWriter.emitFrame(this.mv);
                                    ByteCodeAppender.Size applyCode = this.record.applyCode(this.mv, context);
                                    this.stackSize = Math.max(this.stackSize, applyCode.getOperandStackSize());
                                    this.localVariableLength = Math.max(this.localVariableLength, applyCode.getLocalVariableSize());
                                }
                            }
                        }

                        protected static abstract class WithDrain extends Appending {
                            protected final Label appended = new Label();
                            protected final Label original = new Label();

                            /* access modifiers changed from: protected */
                            public abstract void onAfterComplete(Implementation.Context context);

                            protected WithDrain(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                super(methodVisitor, typeDescription, record, factory, z, z2);
                            }

                            /* access modifiers changed from: protected */
                            public void onStart() {
                                this.mv.visitJumpInsn(167, this.appended);
                                this.mv.visitLabel(this.original);
                                this.frameWriter.emitFrame(this.mv);
                            }

                            public void visitEnd() {
                                this.mv.visitLabel(this.appended);
                                this.frameWriter.emitFrame(this.mv);
                            }

                            /* access modifiers changed from: protected */
                            public void onComplete(Implementation.Context context) {
                                this.mv.visitJumpInsn(167, this.original);
                                onAfterComplete(context);
                            }

                            protected static class WithoutActiveRecord extends WithDrain {
                                /* access modifiers changed from: protected */
                                public void onAfterComplete(Implementation.Context context) {
                                }

                                protected WithoutActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                    super(methodVisitor, typeDescription, record, factory, z, z2);
                                }
                            }

                            protected static class WithActiveRecord extends WithDrain {
                                private final Label label = new Label();

                                protected WithActiveRecord(MethodVisitor methodVisitor, TypeDescription typeDescription, MethodPool.Record record, AnnotationValueFilter.Factory factory, boolean z, boolean z2) {
                                    super(methodVisitor, typeDescription, record, factory, z, z2);
                                }

                                public void visitInsn(int i) {
                                    if (i == 177) {
                                        this.mv.visitJumpInsn(167, this.label);
                                    } else {
                                        super.visitInsn(i);
                                    }
                                }

                                /* access modifiers changed from: protected */
                                public void onAfterComplete(Implementation.Context context) {
                                    this.mv.visitLabel(this.label);
                                    this.frameWriter.emitFrame(this.mv);
                                    ByteCodeAppender.Size applyCode = this.record.applyCode(this.mv, context);
                                    this.stackSize = Math.max(this.stackSize, applyCode.getOperandStackSize());
                                    this.localVariableLength = Math.max(this.localVariableLength, applyCode.getLocalVariableSize());
                                }
                            }
                        }
                    }
                }

                protected class RedefinitionClassVisitor extends MetadataAwareClassVisitor {
                    private final ContextRegistry contextRegistry;
                    private final LinkedHashMap<String, FieldDescription> declarableFields = new LinkedHashMap<>();
                    private final LinkedHashMap<String, MethodDescription> declarableMethods;
                    private final LinkedHashMap<String, TypeDescription> declaredTypes;
                    /* access modifiers changed from: private */
                    public Implementation.Context.ExtractableView implementationContext;
                    private InitializationHandler initializationHandler;
                    private MethodPool methodPool;
                    private final Set<String> nestMembers;
                    private final int readerFlags;
                    private boolean retainDeprecationModifiers;
                    private final TypeInitializer typeInitializer;
                    private final int writerFlags;

                    protected RedefinitionClassVisitor(ClassVisitor classVisitor, TypeInitializer typeInitializer2, ContextRegistry contextRegistry2, int i, int i2) {
                        super(OpenedClassReader.ASM_API, classVisitor);
                        this.typeInitializer = typeInitializer2;
                        this.contextRegistry = contextRegistry2;
                        this.writerFlags = i;
                        this.readerFlags = i2;
                        for (FieldDescription fieldDescription : WithFullProcessing.this.fields) {
                            LinkedHashMap<String, FieldDescription> linkedHashMap = this.declarableFields;
                            linkedHashMap.put(fieldDescription.getInternalName() + fieldDescription.getDescriptor(), fieldDescription);
                        }
                        this.declarableMethods = new LinkedHashMap<>();
                        for (MethodDescription methodDescription : WithFullProcessing.this.instrumentedMethods) {
                            LinkedHashMap<String, MethodDescription> linkedHashMap2 = this.declarableMethods;
                            linkedHashMap2.put(methodDescription.getInternalName() + methodDescription.getDescriptor(), methodDescription);
                        }
                        if (WithFullProcessing.this.instrumentedType.isNestHost()) {
                            this.nestMembers = new LinkedHashSet();
                            for (TypeDescription internalName : (TypeList) WithFullProcessing.this.instrumentedType.getNestMembers().filter(ElementMatchers.not(ElementMatchers.is((Object) WithFullProcessing.this.instrumentedType)))) {
                                this.nestMembers.add(internalName.getInternalName());
                            }
                        } else {
                            this.nestMembers = Collections.emptySet();
                        }
                        this.declaredTypes = new LinkedHashMap<>();
                        for (TypeDescription typeDescription : WithFullProcessing.this.instrumentedType.getDeclaredTypes()) {
                            this.declaredTypes.put(typeDescription.getInternalName(), typeDescription);
                        }
                    }

                    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                        String str4;
                        ClassFileVersion ofMinorMajor = ClassFileVersion.ofMinorMajor(i);
                        this.methodPool = WithFullProcessing.this.methodRegistry.compile(WithFullProcessing.this.implementationTargetFactory, ofMinorMajor);
                        this.initializationHandler = new InitializationHandler.Creating(WithFullProcessing.this.instrumentedType, this.methodPool, WithFullProcessing.this.annotationValueFilterFactory);
                        this.implementationContext = WithFullProcessing.this.implementationContextFactory.make(WithFullProcessing.this.instrumentedType, WithFullProcessing.this.auxiliaryTypeNamingStrategy, this.typeInitializer, ofMinorMajor, WithFullProcessing.this.classFileVersion);
                        this.retainDeprecationModifiers = ofMinorMajor.isLessThan(ClassFileVersion.JAVA_V5);
                        this.contextRegistry.setImplementationContext(this.implementationContext);
                        this.cv = WithFullProcessing.this.asmVisitorWrapper.wrap(WithFullProcessing.this.instrumentedType, this.cv, this.implementationContext, WithFullProcessing.this.typePool, WithFullProcessing.this.fields, WithFullProcessing.this.methods, this.writerFlags, this.readerFlags);
                        ClassVisitor classVisitor = this.cv;
                        int i3 = 0;
                        int actualModifiers = WithFullProcessing.this.instrumentedType.getActualModifiers((i2 & 32) != 0 && !WithFullProcessing.this.instrumentedType.isInterface()) | resolveDeprecationModifiers(i2);
                        if ((i2 & 16) != 0 && WithFullProcessing.this.instrumentedType.isAnonymousType()) {
                            i3 = 16;
                        }
                        int i4 = actualModifiers | i3;
                        String internalName = WithFullProcessing.this.instrumentedType.getInternalName();
                        if (!TypeDescription.AbstractBase.RAW_TYPES) {
                            str2 = WithFullProcessing.this.instrumentedType.getGenericSignature();
                        }
                        String str5 = str2;
                        if (WithFullProcessing.this.instrumentedType.getSuperClass() == null) {
                            str4 = WithFullProcessing.this.instrumentedType.isInterface() ? TypeDescription.OBJECT.getInternalName() : Default.NO_REFERENCE;
                        } else {
                            str4 = WithFullProcessing.this.instrumentedType.getSuperClass().asErasure().getInternalName();
                        }
                        classVisitor.visit(i, i4, internalName, str5, str4, WithFullProcessing.this.instrumentedType.getInterfaces().asErasures().toInternalNames());
                    }

                    /* access modifiers changed from: protected */
                    public void onVisitNestHost(String str) {
                        onNestHost();
                    }

                    /* access modifiers changed from: protected */
                    public void onNestHost() {
                        if (!WithFullProcessing.this.instrumentedType.isNestHost()) {
                            this.cv.visitNestHost(WithFullProcessing.this.instrumentedType.getNestHost().getInternalName());
                        }
                    }

                    /* access modifiers changed from: protected */
                    public void onVisitOuterClass(String str, String str2, String str3) {
                        try {
                            onOuterType();
                        } catch (Throwable unused) {
                            this.cv.visitOuterClass(str, str2, str3);
                        }
                    }

                    /* access modifiers changed from: protected */
                    public void onOuterType() {
                        MethodDescription.InDefinedShape enclosingMethod = WithFullProcessing.this.instrumentedType.getEnclosingMethod();
                        if (enclosingMethod != null) {
                            this.cv.visitOuterClass(enclosingMethod.getDeclaringType().getInternalName(), enclosingMethod.getInternalName(), enclosingMethod.getDescriptor());
                        } else if (WithFullProcessing.this.instrumentedType.isLocalType() || WithFullProcessing.this.instrumentedType.isAnonymousType()) {
                            this.cv.visitOuterClass(WithFullProcessing.this.instrumentedType.getEnclosingType().getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE);
                        }
                    }

                    /* access modifiers changed from: protected */
                    public void onAfterAttributes() {
                        WithFullProcessing.this.typeAttributeAppender.apply(this.cv, WithFullProcessing.this.instrumentedType, WithFullProcessing.this.annotationValueFilterFactory.on(WithFullProcessing.this.instrumentedType));
                    }

                    /* access modifiers changed from: protected */
                    public AnnotationVisitor onVisitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                        if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                            return this.cv.visitTypeAnnotation(i, typePath, str, z);
                        }
                        return ForInlining.IGNORE_ANNOTATION;
                    }

                    /* access modifiers changed from: protected */
                    public AnnotationVisitor onVisitAnnotation(String str, boolean z) {
                        if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                            return this.cv.visitAnnotation(str, z);
                        }
                        return ForInlining.IGNORE_ANNOTATION;
                    }

                    /* access modifiers changed from: protected */
                    public FieldVisitor onVisitField(int i, String str, String str2, String str3, Object obj) {
                        LinkedHashMap<String, FieldDescription> linkedHashMap = this.declarableFields;
                        FieldDescription fieldDescription = (FieldDescription) linkedHashMap.remove(str + str2);
                        if (fieldDescription != null) {
                            FieldPool.Record target = WithFullProcessing.this.fieldPool.target(fieldDescription);
                            if (!target.isImplicit()) {
                                return redefine(target, obj, i, str3);
                            }
                        }
                        return this.cv.visitField(i, str, str2, str3, obj);
                    }

                    /* access modifiers changed from: protected */
                    public FieldVisitor redefine(FieldPool.Record record, Object obj, int i, String str) {
                        FieldDescription field = record.getField();
                        ClassVisitor classVisitor = this.cv;
                        int actualModifiers = field.getActualModifiers() | resolveDeprecationModifiers(i);
                        String internalName = field.getInternalName();
                        String descriptor = field.getDescriptor();
                        if (!TypeDescription.AbstractBase.RAW_TYPES) {
                            str = field.getGenericSignature();
                        }
                        FieldVisitor visitField = classVisitor.visitField(actualModifiers, internalName, descriptor, str, record.resolveDefault(obj));
                        return visitField == null ? ForInlining.IGNORE_FIELD : new AttributeObtainingFieldVisitor(visitField, record);
                    }

                    /* access modifiers changed from: protected */
                    public MethodVisitor onVisitMethod(int i, String str, String str2, String str3, String[] strArr) {
                        String str4;
                        int i2 = i;
                        String str5 = str;
                        boolean z = true;
                        if (str5.equals(MethodDescription.TYPE_INITIALIZER_INTERNAL_NAME)) {
                            MethodVisitor visitMethod = this.cv.visitMethod(i, str, str2, str3, strArr);
                            if (visitMethod == null) {
                                return ForInlining.IGNORE_METHOD;
                            }
                            InitializationHandler of = InitializationHandler.Appending.of(this.implementationContext.isEnabled(), visitMethod, WithFullProcessing.this.instrumentedType, this.methodPool, WithFullProcessing.this.annotationValueFilterFactory, (this.writerFlags & 2) == 0 && this.implementationContext.getClassFileVersion().isAtLeast(ClassFileVersion.JAVA_V6), (this.readerFlags & 8) != 0);
                            this.initializationHandler = of;
                            return (MethodVisitor) of;
                        }
                        LinkedHashMap<String, MethodDescription> linkedHashMap = this.declarableMethods;
                        MethodDescription methodDescription = (MethodDescription) linkedHashMap.remove(str5 + str2);
                        if (methodDescription == null) {
                            return this.cv.visitMethod(i, str, str2, str3, strArr);
                        }
                        if ((i2 & 1024) != 0) {
                            str4 = str3;
                        } else {
                            str4 = str3;
                            z = false;
                        }
                        return redefine(methodDescription, z, i2, str4);
                    }

                    /* access modifiers changed from: protected */
                    public MethodVisitor redefine(MethodDescription methodDescription, boolean z, int i, String str) {
                        String str2;
                        MethodPool.Record target = this.methodPool.target(methodDescription);
                        if (!target.getSort().isDefined()) {
                            ClassVisitor classVisitor = this.cv;
                            int actualModifiers = methodDescription.getActualModifiers() | resolveDeprecationModifiers(i);
                            String internalName = methodDescription.getInternalName();
                            String descriptor = methodDescription.getDescriptor();
                            if (!TypeDescription.AbstractBase.RAW_TYPES) {
                                str = methodDescription.getGenericSignature();
                            }
                            return classVisitor.visitMethod(actualModifiers, internalName, descriptor, str, methodDescription.getExceptionTypes().asErasures().toInternalNames());
                        }
                        MethodDescription method = target.getMethod();
                        ClassVisitor classVisitor2 = this.cv;
                        int resolve = ModifierContributor.Resolver.of(Collections.singleton(target.getVisibility())).resolve(method.getActualModifiers(target.getSort().isImplemented())) | resolveDeprecationModifiers(i);
                        String internalName2 = method.getInternalName();
                        String descriptor2 = method.getDescriptor();
                        if (TypeDescription.AbstractBase.RAW_TYPES) {
                            str2 = str;
                        } else {
                            str2 = method.getGenericSignature();
                        }
                        MethodVisitor visitMethod = classVisitor2.visitMethod(resolve, internalName2, descriptor2, str2, method.getExceptionTypes().asErasures().toInternalNames());
                        if (visitMethod == null) {
                            return ForInlining.IGNORE_METHOD;
                        }
                        if (z) {
                            return new AttributeObtainingMethodVisitor(visitMethod, target);
                        }
                        if (!methodDescription.isNative()) {
                            return new CodePreservingMethodVisitor(visitMethod, target, WithFullProcessing.this.methodRebaseResolver.resolve((MethodDescription.InDefinedShape) method.asDefined()));
                        }
                        MethodRebaseResolver.Resolution resolve2 = WithFullProcessing.this.methodRebaseResolver.resolve((MethodDescription.InDefinedShape) method.asDefined());
                        if (resolve2.isRebased()) {
                            int actualModifiers2 = resolve2.getResolvedMethod().getActualModifiers() | resolveDeprecationModifiers(i);
                            String internalName3 = resolve2.getResolvedMethod().getInternalName();
                            String descriptor3 = resolve2.getResolvedMethod().getDescriptor();
                            if (!TypeDescription.AbstractBase.RAW_TYPES) {
                                str = method.getGenericSignature();
                            }
                            MethodVisitor visitMethod2 = super.visitMethod(actualModifiers2, internalName3, descriptor3, str, resolve2.getResolvedMethod().getExceptionTypes().asErasures().toInternalNames());
                            if (visitMethod2 != null) {
                                visitMethod2.visitEnd();
                            }
                        }
                        return new AttributeObtainingMethodVisitor(visitMethod, target);
                    }

                    /* access modifiers changed from: protected */
                    public void onVisitInnerClass(String str, String str2, String str3, int i) {
                        String str4;
                        String str5;
                        if (!str.equals(WithFullProcessing.this.instrumentedType.getInternalName())) {
                            TypeDescription typeDescription = (TypeDescription) this.declaredTypes.remove(str);
                            if (typeDescription == null) {
                                this.cv.visitInnerClass(str, str2, str3, i);
                                return;
                            }
                            ClassVisitor classVisitor = this.cv;
                            if (typeDescription.isMemberType() || (str2 != null && str3 == null && typeDescription.isAnonymousType())) {
                                str4 = WithFullProcessing.this.instrumentedType.getInternalName();
                            } else {
                                str4 = Default.NO_REFERENCE;
                            }
                            if (typeDescription.isAnonymousType()) {
                                str5 = Default.NO_REFERENCE;
                            } else {
                                str5 = typeDescription.getSimpleName();
                            }
                            classVisitor.visitInnerClass(str, str4, str5, typeDescription.getModifiers());
                        }
                    }

                    /* access modifiers changed from: protected */
                    public void onVisitNestMember(String str) {
                        if (WithFullProcessing.this.instrumentedType.isNestHost() && this.nestMembers.remove(str)) {
                            this.cv.visitNestMember(str);
                        }
                    }

                    /* access modifiers changed from: protected */
                    public void onVisitEnd() {
                        String str;
                        String str2;
                        for (FieldDescription target : this.declarableFields.values()) {
                            WithFullProcessing.this.fieldPool.target(target).apply(this.cv, WithFullProcessing.this.annotationValueFilterFactory);
                        }
                        for (MethodDescription target2 : this.declarableMethods.values()) {
                            this.methodPool.target(target2).apply(this.cv, this.implementationContext, WithFullProcessing.this.annotationValueFilterFactory);
                        }
                        this.initializationHandler.complete(this.cv, this.implementationContext);
                        TypeDescription declaringType = WithFullProcessing.this.instrumentedType.getDeclaringType();
                        if (declaringType != null) {
                            this.cv.visitInnerClass(WithFullProcessing.this.instrumentedType.getInternalName(), declaringType.getInternalName(), WithFullProcessing.this.instrumentedType.getSimpleName(), WithFullProcessing.this.instrumentedType.getModifiers());
                        } else if (WithFullProcessing.this.instrumentedType.isLocalType()) {
                            this.cv.visitInnerClass(WithFullProcessing.this.instrumentedType.getInternalName(), Default.NO_REFERENCE, WithFullProcessing.this.instrumentedType.getSimpleName(), WithFullProcessing.this.instrumentedType.getModifiers());
                        } else if (WithFullProcessing.this.instrumentedType.isAnonymousType()) {
                            this.cv.visitInnerClass(WithFullProcessing.this.instrumentedType.getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE, WithFullProcessing.this.instrumentedType.getModifiers());
                        }
                        for (TypeDescription next : this.declaredTypes.values()) {
                            ClassVisitor classVisitor = this.cv;
                            String internalName = next.getInternalName();
                            if (next.isMemberType()) {
                                str = WithFullProcessing.this.instrumentedType.getInternalName();
                            } else {
                                str = Default.NO_REFERENCE;
                            }
                            if (next.isAnonymousType()) {
                                str2 = Default.NO_REFERENCE;
                            } else {
                                str2 = next.getSimpleName();
                            }
                            classVisitor.visitInnerClass(internalName, str, str2, next.getModifiers());
                        }
                        this.cv.visitEnd();
                    }

                    private int resolveDeprecationModifiers(int i) {
                        return (!this.retainDeprecationModifiers || (i & 131072) == 0) ? 0 : 131072;
                    }

                    protected class AttributeObtainingFieldVisitor extends FieldVisitor {
                        private final FieldPool.Record record;

                        protected AttributeObtainingFieldVisitor(FieldVisitor fieldVisitor, FieldPool.Record record2) {
                            super(OpenedClassReader.ASM_API, fieldVisitor);
                            this.record = record2;
                        }

                        public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitTypeAnnotation(i, typePath, str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public AnnotationVisitor visitAnnotation(String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitAnnotation(str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public void visitEnd() {
                            this.record.apply(this.fv, WithFullProcessing.this.annotationValueFilterFactory);
                            super.visitEnd();
                        }
                    }

                    protected class CodePreservingMethodVisitor extends MethodVisitor {
                        private final MethodVisitor actualMethodVisitor;
                        private final MethodPool.Record record;
                        private final MethodRebaseResolver.Resolution resolution;

                        protected CodePreservingMethodVisitor(MethodVisitor methodVisitor, MethodPool.Record record2, MethodRebaseResolver.Resolution resolution2) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                            this.actualMethodVisitor = methodVisitor;
                            this.record = record2;
                            this.resolution = resolution2;
                            record2.applyHead(methodVisitor);
                        }

                        public AnnotationVisitor visitAnnotationDefault() {
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitTypeAnnotation(i, typePath, str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public AnnotationVisitor visitAnnotation(String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitAnnotation(str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public void visitAnnotableParameterCount(int i, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                super.visitAnnotableParameterCount(i, z);
                            }
                        }

                        public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitParameterAnnotation(i, str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public void visitCode() {
                            MethodVisitor methodVisitor;
                            this.record.applyBody(this.actualMethodVisitor, RedefinitionClassVisitor.this.implementationContext, WithFullProcessing.this.annotationValueFilterFactory);
                            this.actualMethodVisitor.visitEnd();
                            if (this.resolution.isRebased()) {
                                methodVisitor = RedefinitionClassVisitor.this.cv.visitMethod(this.resolution.getResolvedMethod().getActualModifiers(), this.resolution.getResolvedMethod().getInternalName(), this.resolution.getResolvedMethod().getDescriptor(), this.resolution.getResolvedMethod().getGenericSignature(), this.resolution.getResolvedMethod().getExceptionTypes().asErasures().toInternalNames());
                            } else {
                                methodVisitor = ForInlining.IGNORE_METHOD;
                            }
                            this.mv = methodVisitor;
                            super.visitCode();
                        }

                        public void visitMaxs(int i, int i2) {
                            super.visitMaxs(i, Math.max(i2, this.resolution.getResolvedMethod().getStackSize()));
                        }
                    }

                    protected class AttributeObtainingMethodVisitor extends MethodVisitor {
                        private final MethodVisitor actualMethodVisitor;
                        private final MethodPool.Record record;

                        protected AttributeObtainingMethodVisitor(MethodVisitor methodVisitor, MethodPool.Record record2) {
                            super(OpenedClassReader.ASM_API, methodVisitor);
                            this.actualMethodVisitor = methodVisitor;
                            this.record = record2;
                            record2.applyHead(methodVisitor);
                        }

                        public AnnotationVisitor visitAnnotationDefault() {
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitTypeAnnotation(i, typePath, str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public AnnotationVisitor visitAnnotation(String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitAnnotation(str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public void visitAnnotableParameterCount(int i, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                super.visitAnnotableParameterCount(i, z);
                            }
                        }

                        public AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z) {
                            if (WithFullProcessing.this.annotationRetention.isEnabled()) {
                                return super.visitParameterAnnotation(i, str, z);
                            }
                            return ForInlining.IGNORE_ANNOTATION;
                        }

                        public void visitCode() {
                            this.mv = ForInlining.IGNORE_METHOD;
                        }

                        public void visitEnd() {
                            this.record.applyBody(this.actualMethodVisitor, RedefinitionClassVisitor.this.implementationContext, WithFullProcessing.this.annotationValueFilterFactory);
                            this.actualMethodVisitor.visitEnd();
                        }
                    }
                }
            }

            protected static class WithDecorationOnly<V> extends ForInlining<V> {
                /* JADX WARNING: Illegal instructions before constructor call */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                protected WithDecorationOnly(net.bytebuddy.description.type.TypeDescription r22, net.bytebuddy.ClassFileVersion r23, java.util.List<? extends net.bytebuddy.dynamic.DynamicType> r24, net.bytebuddy.description.method.MethodList<?> r25, net.bytebuddy.implementation.attribute.TypeAttributeAppender r26, net.bytebuddy.asm.AsmVisitorWrapper r27, net.bytebuddy.implementation.attribute.AnnotationValueFilter.Factory r28, net.bytebuddy.implementation.attribute.AnnotationRetention r29, net.bytebuddy.implementation.auxiliary.AuxiliaryType.NamingStrategy r30, net.bytebuddy.implementation.Implementation.Context.Factory r31, net.bytebuddy.dynamic.scaffold.TypeValidation r32, net.bytebuddy.dynamic.scaffold.ClassWriterStrategy r33, net.bytebuddy.pool.TypePool r34, net.bytebuddy.dynamic.ClassFileLocator r35) {
                    /*
                        r21 = this;
                        r0 = r21
                        r1 = r22
                        r19 = r22
                        r2 = r23
                        r4 = r24
                        r6 = r25
                        r10 = r26
                        r11 = r27
                        r12 = r28
                        r13 = r29
                        r14 = r30
                        r15 = r31
                        r16 = r32
                        r17 = r33
                        r18 = r34
                        r20 = r35
                        net.bytebuddy.dynamic.scaffold.TypeWriter$FieldPool$Disabled r3 = net.bytebuddy.dynamic.scaffold.TypeWriter.FieldPool.Disabled.INSTANCE
                        net.bytebuddy.dynamic.scaffold.TypeWriter$Default$ForInlining$WithDecorationOnly$LazyFieldList r7 = new net.bytebuddy.dynamic.scaffold.TypeWriter$Default$ForInlining$WithDecorationOnly$LazyFieldList
                        r5 = r7
                        r8 = r22
                        r7.<init>(r8)
                        net.bytebuddy.description.method.MethodList$Empty r8 = new net.bytebuddy.description.method.MethodList$Empty
                        r7 = r8
                        r8.<init>()
                        net.bytebuddy.implementation.LoadedTypeInitializer$NoOp r8 = net.bytebuddy.implementation.LoadedTypeInitializer.NoOp.INSTANCE
                        net.bytebuddy.dynamic.scaffold.TypeInitializer$None r9 = net.bytebuddy.dynamic.scaffold.TypeInitializer.None.INSTANCE
                        r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.dynamic.scaffold.TypeWriter.Default.ForInlining.WithDecorationOnly.<init>(net.bytebuddy.description.type.TypeDescription, net.bytebuddy.ClassFileVersion, java.util.List, net.bytebuddy.description.method.MethodList, net.bytebuddy.implementation.attribute.TypeAttributeAppender, net.bytebuddy.asm.AsmVisitorWrapper, net.bytebuddy.implementation.attribute.AnnotationValueFilter$Factory, net.bytebuddy.implementation.attribute.AnnotationRetention, net.bytebuddy.implementation.auxiliary.AuxiliaryType$NamingStrategy, net.bytebuddy.implementation.Implementation$Context$Factory, net.bytebuddy.dynamic.scaffold.TypeValidation, net.bytebuddy.dynamic.scaffold.ClassWriterStrategy, net.bytebuddy.pool.TypePool, net.bytebuddy.dynamic.ClassFileLocator):void");
                }

                /* access modifiers changed from: protected */
                public ClassVisitor writeTo(ClassVisitor classVisitor, TypeInitializer typeInitializer, ContextRegistry contextRegistry, int i, int i2) {
                    if (!typeInitializer.isDefined()) {
                        return new DecorationClassVisitor(classVisitor, contextRegistry, i, i2);
                    }
                    throw new UnsupportedOperationException("Cannot apply a type initializer for a decoration");
                }

                protected static class LazyFieldList extends FieldList.AbstractBase<FieldDescription.InDefinedShape> {
                    private final TypeDescription instrumentedType;

                    protected LazyFieldList(TypeDescription typeDescription) {
                        this.instrumentedType = typeDescription;
                    }

                    public FieldDescription.InDefinedShape get(int i) {
                        return (FieldDescription.InDefinedShape) this.instrumentedType.getDeclaredFields().get(i);
                    }

                    public int size() {
                        return this.instrumentedType.getDeclaredFields().size();
                    }
                }

                protected class DecorationClassVisitor extends MetadataAwareClassVisitor implements TypeInitializer.Drain {
                    private final ContextRegistry contextRegistry;
                    private Implementation.Context.ExtractableView implementationContext;
                    private final int readerFlags;
                    private final int writerFlags;

                    public void apply(ClassVisitor classVisitor, TypeInitializer typeInitializer, Implementation.Context context) {
                    }

                    /* access modifiers changed from: protected */
                    public void onNestHost() {
                    }

                    /* access modifiers changed from: protected */
                    public void onOuterType() {
                    }

                    protected DecorationClassVisitor(ClassVisitor classVisitor, ContextRegistry contextRegistry2, int i, int i2) {
                        super(OpenedClassReader.ASM_API, classVisitor);
                        this.contextRegistry = contextRegistry2;
                        this.writerFlags = i;
                        this.readerFlags = i2;
                    }

                    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
                        Implementation.Context.ExtractableView make = WithDecorationOnly.this.implementationContextFactory.make(WithDecorationOnly.this.instrumentedType, WithDecorationOnly.this.auxiliaryTypeNamingStrategy, WithDecorationOnly.this.typeInitializer, ClassFileVersion.ofMinorMajor(i), WithDecorationOnly.this.classFileVersion);
                        this.implementationContext = make;
                        this.contextRegistry.setImplementationContext(make);
                        this.cv = WithDecorationOnly.this.asmVisitorWrapper.wrap(WithDecorationOnly.this.instrumentedType, this.cv, this.implementationContext, WithDecorationOnly.this.typePool, WithDecorationOnly.this.fields, WithDecorationOnly.this.methods, this.writerFlags, this.readerFlags);
                        this.cv.visit(i, i2, str, str2, str3, strArr);
                    }

                    /* access modifiers changed from: protected */
                    public AnnotationVisitor onVisitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
                        if (WithDecorationOnly.this.annotationRetention.isEnabled()) {
                            return this.cv.visitTypeAnnotation(i, typePath, str, z);
                        }
                        return ForInlining.IGNORE_ANNOTATION;
                    }

                    /* access modifiers changed from: protected */
                    public AnnotationVisitor onVisitAnnotation(String str, boolean z) {
                        if (WithDecorationOnly.this.annotationRetention.isEnabled()) {
                            return this.cv.visitAnnotation(str, z);
                        }
                        return ForInlining.IGNORE_ANNOTATION;
                    }

                    /* access modifiers changed from: protected */
                    public void onAfterAttributes() {
                        WithDecorationOnly.this.typeAttributeAppender.apply(this.cv, WithDecorationOnly.this.instrumentedType, WithDecorationOnly.this.annotationValueFilterFactory.on(WithDecorationOnly.this.instrumentedType));
                    }

                    /* access modifiers changed from: protected */
                    public void onVisitEnd() {
                        this.implementationContext.drain(this, this.cv, WithDecorationOnly.this.annotationValueFilterFactory);
                        this.cv.visitEnd();
                    }
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForCreation<U> extends Default<U> {
            private final MethodPool methodPool;

            public boolean equals(Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.methodPool.equals(((ForCreation) obj).methodPool);
            }

            public int hashCode() {
                return (super.hashCode() * 31) + this.methodPool.hashCode();
            }

            protected ForCreation(TypeDescription typeDescription, ClassFileVersion classFileVersion, FieldPool fieldPool, MethodPool methodPool2, List<? extends DynamicType> list, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, MethodList<?> methodList2, LoadedTypeInitializer loadedTypeInitializer, TypeInitializer typeInitializer, TypeAttributeAppender typeAttributeAppender, AsmVisitorWrapper asmVisitorWrapper, AnnotationValueFilter.Factory factory, AnnotationRetention annotationRetention, AuxiliaryType.NamingStrategy namingStrategy, Implementation.Context.Factory factory2, TypeValidation typeValidation, ClassWriterStrategy classWriterStrategy, TypePool typePool) {
                super(typeDescription, classFileVersion, fieldPool, list, fieldList, methodList, methodList2, loadedTypeInitializer, typeInitializer, typeAttributeAppender, asmVisitorWrapper, factory, annotationRetention, namingStrategy, factory2, typeValidation, classWriterStrategy, typePool);
                this.methodPool = methodPool2;
            }

            /* access modifiers changed from: protected */
            public Default<U>.UnresolvedType create(TypeInitializer typeInitializer) {
                TypeDescription typeDescription;
                String str;
                String str2;
                int mergeWriter = this.asmVisitorWrapper.mergeWriter(0);
                ClassWriter resolve = this.classWriterStrategy.resolve(mergeWriter, this.typePool);
                Implementation.Context.ExtractableView make = this.implementationContextFactory.make(this.instrumentedType, this.auxiliaryTypeNamingStrategy, typeInitializer, this.classFileVersion, this.classFileVersion);
                ClassVisitor wrap = this.asmVisitorWrapper.wrap(this.instrumentedType, ValidatingClassVisitor.of(resolve, this.typeValidation), make, this.typePool, this.fields, this.methods, mergeWriter, this.asmVisitorWrapper.mergeReader(0));
                int minorMajorVersion = this.classFileVersion.getMinorMajorVersion();
                int actualModifiers = this.instrumentedType.getActualModifiers(!this.instrumentedType.isInterface());
                String internalName = this.instrumentedType.getInternalName();
                String genericSignature = this.instrumentedType.getGenericSignature();
                if (this.instrumentedType.getSuperClass() == null) {
                    typeDescription = TypeDescription.OBJECT;
                } else {
                    typeDescription = this.instrumentedType.getSuperClass().asErasure();
                }
                wrap.visit(minorMajorVersion, actualModifiers, internalName, genericSignature, typeDescription.getInternalName(), this.instrumentedType.getInterfaces().asErasures().toInternalNames());
                if (!this.instrumentedType.isNestHost()) {
                    wrap.visitNestHost(this.instrumentedType.getNestHost().getInternalName());
                }
                MethodDescription.InDefinedShape enclosingMethod = this.instrumentedType.getEnclosingMethod();
                if (enclosingMethod != null) {
                    wrap.visitOuterClass(enclosingMethod.getDeclaringType().getInternalName(), enclosingMethod.getInternalName(), enclosingMethod.getDescriptor());
                } else if (this.instrumentedType.isLocalType() || this.instrumentedType.isAnonymousType()) {
                    wrap.visitOuterClass(this.instrumentedType.getEnclosingType().getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE);
                }
                this.typeAttributeAppender.apply(wrap, this.instrumentedType, this.annotationValueFilterFactory.on(this.instrumentedType));
                for (FieldDescription target : this.fields) {
                    this.fieldPool.target(target).apply(wrap, this.annotationValueFilterFactory);
                }
                for (MethodDescription target2 : this.instrumentedMethods) {
                    this.methodPool.target(target2).apply(wrap, make, this.annotationValueFilterFactory);
                }
                make.drain(new TypeInitializer.Drain.Default(this.instrumentedType, this.methodPool, this.annotationValueFilterFactory), wrap, this.annotationValueFilterFactory);
                if (this.instrumentedType.isNestHost()) {
                    for (TypeDescription internalName2 : (TypeList) this.instrumentedType.getNestMembers().filter(ElementMatchers.not(ElementMatchers.is((Object) this.instrumentedType)))) {
                        wrap.visitNestMember(internalName2.getInternalName());
                    }
                }
                TypeDescription declaringType = this.instrumentedType.getDeclaringType();
                if (declaringType != null) {
                    wrap.visitInnerClass(this.instrumentedType.getInternalName(), declaringType.getInternalName(), this.instrumentedType.getSimpleName(), this.instrumentedType.getModifiers());
                } else if (this.instrumentedType.isLocalType()) {
                    wrap.visitInnerClass(this.instrumentedType.getInternalName(), Default.NO_REFERENCE, this.instrumentedType.getSimpleName(), this.instrumentedType.getModifiers());
                } else if (this.instrumentedType.isAnonymousType()) {
                    wrap.visitInnerClass(this.instrumentedType.getInternalName(), Default.NO_REFERENCE, Default.NO_REFERENCE, this.instrumentedType.getModifiers());
                }
                for (TypeDescription typeDescription2 : this.instrumentedType.getDeclaredTypes()) {
                    String internalName3 = typeDescription2.getInternalName();
                    if (typeDescription2.isMemberType()) {
                        str = this.instrumentedType.getInternalName();
                    } else {
                        str = Default.NO_REFERENCE;
                    }
                    if (typeDescription2.isAnonymousType()) {
                        str2 = Default.NO_REFERENCE;
                    } else {
                        str2 = typeDescription2.getSimpleName();
                    }
                    wrap.visitInnerClass(internalName3, str, str2, typeDescription2.getModifiers());
                }
                wrap.visitEnd();
                return new UnresolvedType(resolve.toByteArray(), make.getAuxiliaryTypes());
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class ClassDumpAction implements PrivilegedExceptionAction<Void> {
            private static final Void NOTHING = null;
            private final byte[] binaryRepresentation;
            private final TypeDescription instrumentedType;
            private final boolean original;
            private final String target;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                ClassDumpAction classDumpAction = (ClassDumpAction) obj;
                return this.original == classDumpAction.original && this.target.equals(classDumpAction.target) && this.instrumentedType.equals(classDumpAction.instrumentedType) && Arrays.equals(this.binaryRepresentation, classDumpAction.binaryRepresentation);
            }

            public int hashCode() {
                return ((((((527 + this.target.hashCode()) * 31) + this.instrumentedType.hashCode()) * 31) + (this.original ? 1 : 0)) * 31) + Arrays.hashCode(this.binaryRepresentation);
            }

            protected ClassDumpAction(String str, TypeDescription typeDescription, boolean z, byte[] bArr) {
                this.target = str;
                this.instrumentedType = typeDescription;
                this.original = z;
                this.binaryRepresentation = bArr;
            }

            protected static void dump(String str, TypeDescription typeDescription, boolean z, byte[] bArr) {
                if (str != null) {
                    try {
                        AccessController.doPrivileged(new ClassDumpAction(str, typeDescription, z, bArr));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            public Void run() throws Exception {
                String str = this.target;
                StringBuilder sb = new StringBuilder();
                sb.append(this.instrumentedType.getName());
                sb.append(this.original ? "-original." : ".");
                sb.append(System.currentTimeMillis());
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str, sb.toString()));
                try {
                    fileOutputStream.write(this.binaryRepresentation);
                    return NOTHING;
                } finally {
                    fileOutputStream.close();
                }
            }
        }
    }
}

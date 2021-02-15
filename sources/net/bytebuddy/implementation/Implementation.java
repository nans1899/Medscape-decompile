package net.bytebuddy.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import net.bytebuddy.implementation.MethodAccessorFactory;
import net.bytebuddy.implementation.attribute.AnnotationValueFilter;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.RandomString;

public interface Implementation extends InstrumentedType.Prepareable {

    public interface Composable extends Implementation {
        Composable andThen(Composable composable);

        Implementation andThen(Implementation implementation);
    }

    ByteCodeAppender appender(Target target);

    public interface SpecialMethodInvocation extends StackManipulation {
        MethodDescription getMethodDescription();

        TypeDescription getTypeDescription();

        public enum Illegal implements SpecialMethodInvocation {
            INSTANCE;

            public boolean isValid() {
                return false;
            }

            public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                throw new IllegalStateException("Cannot implement an undefined method");
            }

            public MethodDescription getMethodDescription() {
                throw new IllegalStateException("An illegal special method invocation must not be applied");
            }

            public TypeDescription getTypeDescription() {
                throw new IllegalStateException("An illegal special method invocation must not be applied");
            }
        }

        public static abstract class AbstractBase implements SpecialMethodInvocation {
            public boolean isValid() {
                return true;
            }

            public int hashCode() {
                return (getMethodDescription().asSignatureToken().hashCode() * 31) + getTypeDescription().hashCode();
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof SpecialMethodInvocation)) {
                    return false;
                }
                SpecialMethodInvocation specialMethodInvocation = (SpecialMethodInvocation) obj;
                if (!getMethodDescription().asSignatureToken().equals(specialMethodInvocation.getMethodDescription().asSignatureToken()) || !getTypeDescription().equals(specialMethodInvocation.getTypeDescription())) {
                    return false;
                }
                return true;
            }
        }

        public static class Simple extends AbstractBase {
            private final MethodDescription methodDescription;
            private final StackManipulation stackManipulation;
            private final TypeDescription typeDescription;

            protected Simple(MethodDescription methodDescription2, TypeDescription typeDescription2, StackManipulation stackManipulation2) {
                this.methodDescription = methodDescription2;
                this.typeDescription = typeDescription2;
                this.stackManipulation = stackManipulation2;
            }

            public static SpecialMethodInvocation of(MethodDescription methodDescription2, TypeDescription typeDescription2) {
                StackManipulation special = MethodInvocation.invoke(methodDescription2).special(typeDescription2);
                return special.isValid() ? new Simple(methodDescription2, typeDescription2, special) : Illegal.INSTANCE;
            }

            public MethodDescription getMethodDescription() {
                return this.methodDescription;
            }

            public TypeDescription getTypeDescription() {
                return this.typeDescription;
            }

            public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                return this.stackManipulation.apply(methodVisitor, context);
            }
        }
    }

    public interface Target {

        public interface Factory {
            Target make(TypeDescription typeDescription, MethodGraph.Linked linked, ClassFileVersion classFileVersion);
        }

        TypeDescription getInstrumentedType();

        TypeDefinition getOriginType();

        SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken);

        SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken, TypeDescription typeDescription);

        SpecialMethodInvocation invokeDominant(MethodDescription.SignatureToken signatureToken);

        SpecialMethodInvocation invokeSuper(MethodDescription.SignatureToken signatureToken);

        @HashCodeAndEqualsPlugin.Enhance
        public static abstract class AbstractBase implements Target {
            protected final DefaultMethodInvocation defaultMethodInvocation;
            protected final TypeDescription instrumentedType;
            protected final MethodGraph.Linked methodGraph;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                AbstractBase abstractBase = (AbstractBase) obj;
                return this.defaultMethodInvocation.equals(abstractBase.defaultMethodInvocation) && this.instrumentedType.equals(abstractBase.instrumentedType) && this.methodGraph.equals(abstractBase.methodGraph);
            }

            public int hashCode() {
                return ((((527 + this.instrumentedType.hashCode()) * 31) + this.methodGraph.hashCode()) * 31) + this.defaultMethodInvocation.hashCode();
            }

            protected AbstractBase(TypeDescription typeDescription, MethodGraph.Linked linked, DefaultMethodInvocation defaultMethodInvocation2) {
                this.instrumentedType = typeDescription;
                this.methodGraph = linked;
                this.defaultMethodInvocation = defaultMethodInvocation2;
            }

            public TypeDescription getInstrumentedType() {
                return this.instrumentedType;
            }

            public SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken) {
                SpecialMethodInvocation specialMethodInvocation = SpecialMethodInvocation.Illegal.INSTANCE;
                for (TypeDescription invokeDefault : this.instrumentedType.getInterfaces().asErasures()) {
                    SpecialMethodInvocation invokeDefault2 = invokeDefault(signatureToken, invokeDefault);
                    if (invokeDefault2.isValid()) {
                        if (specialMethodInvocation.isValid()) {
                            return SpecialMethodInvocation.Illegal.INSTANCE;
                        }
                        specialMethodInvocation = invokeDefault2;
                    }
                }
                return specialMethodInvocation;
            }

            public SpecialMethodInvocation invokeDefault(MethodDescription.SignatureToken signatureToken, TypeDescription typeDescription) {
                return this.defaultMethodInvocation.apply(this.methodGraph.getInterfaceGraph(typeDescription).locate(signatureToken), typeDescription);
            }

            public SpecialMethodInvocation invokeDominant(MethodDescription.SignatureToken signatureToken) {
                SpecialMethodInvocation invokeSuper = invokeSuper(signatureToken);
                return invokeSuper.isValid() ? invokeSuper : invokeDefault(signatureToken);
            }

            protected enum DefaultMethodInvocation {
                ENABLED {
                    /* access modifiers changed from: protected */
                    public SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription) {
                        return node.getSort().isUnique() ? SpecialMethodInvocation.Simple.of(node.getRepresentative(), typeDescription) : SpecialMethodInvocation.Illegal.INSTANCE;
                    }
                },
                DISABLED {
                    /* access modifiers changed from: protected */
                    public SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription) {
                        return SpecialMethodInvocation.Illegal.INSTANCE;
                    }
                };

                /* access modifiers changed from: protected */
                public abstract SpecialMethodInvocation apply(MethodGraph.Node node, TypeDescription typeDescription);

                public static DefaultMethodInvocation of(ClassFileVersion classFileVersion) {
                    return classFileVersion.isAtLeast(ClassFileVersion.JAVA_V8) ? ENABLED : DISABLED;
                }
            }
        }
    }

    public interface Context extends MethodAccessorFactory {

        public interface Factory {
            ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2);
        }

        FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription);

        ClassFileVersion getClassFileVersion();

        TypeDescription getInstrumentedType();

        TypeDescription register(AuxiliaryType auxiliaryType);

        public interface ExtractableView extends Context {
            void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory);

            List<DynamicType> getAuxiliaryTypes();

            boolean isEnabled();

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class AbstractBase implements ExtractableView {
                protected final ClassFileVersion classFileVersion;
                protected final TypeDescription instrumentedType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AbstractBase abstractBase = (AbstractBase) obj;
                    return this.instrumentedType.equals(abstractBase.instrumentedType) && this.classFileVersion.equals(abstractBase.classFileVersion);
                }

                public int hashCode() {
                    return ((527 + this.instrumentedType.hashCode()) * 31) + this.classFileVersion.hashCode();
                }

                protected AbstractBase(TypeDescription typeDescription, ClassFileVersion classFileVersion2) {
                    this.instrumentedType = typeDescription;
                    this.classFileVersion = classFileVersion2;
                }

                public TypeDescription getInstrumentedType() {
                    return this.instrumentedType;
                }

                public ClassFileVersion getClassFileVersion() {
                    return this.classFileVersion;
                }
            }
        }

        public static class Disabled extends ExtractableView.AbstractBase {
            public boolean isEnabled() {
                return false;
            }

            protected Disabled(TypeDescription typeDescription, ClassFileVersion classFileVersion) {
                super(typeDescription, classFileVersion);
            }

            public List<DynamicType> getAuxiliaryTypes() {
                return Collections.emptyList();
            }

            public void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                drain.apply(classVisitor, TypeInitializer.None.INSTANCE, this);
            }

            public TypeDescription register(AuxiliaryType auxiliaryType) {
                throw new IllegalStateException("Registration of auxiliary types was disabled: " + auxiliaryType);
            }

            public MethodDescription.InDefinedShape registerAccessorFor(SpecialMethodInvocation specialMethodInvocation, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of method accessors was disabled: " + specialMethodInvocation.getMethodDescription());
            }

            public MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of field accessor was disabled: " + fieldDescription);
            }

            public MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                throw new IllegalStateException("Registration of field accessor was disabled: " + fieldDescription);
            }

            public FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription) {
                throw new IllegalStateException("Field values caching was disabled: " + typeDescription);
            }

            public enum Factory implements Factory {
                INSTANCE;

                public ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2) {
                    if (!typeInitializer.isDefined()) {
                        return new Disabled(typeDescription, classFileVersion);
                    }
                    throw new IllegalStateException("Cannot define type initializer which was explicitly disabled: " + typeInitializer);
                }
            }
        }

        public static class Default extends ExtractableView.AbstractBase {
            public static final String ACCESSOR_METHOD_SUFFIX = "accessor";
            public static final String FIELD_CACHE_PREFIX = "cachedValue";
            private final ClassFileVersion auxiliaryClassFileVersion;
            private final AuxiliaryType.NamingStrategy auxiliaryTypeNamingStrategy;
            private final Map<AuxiliaryType, DynamicType> auxiliaryTypes = new HashMap();
            private boolean fieldCacheCanAppendEntries = true;
            private final Map<SpecialMethodInvocation, DelegationRecord> registeredAccessorMethods = new HashMap();
            private final Map<FieldCacheEntry, FieldDescription.InDefinedShape> registeredFieldCacheEntries = new HashMap();
            private final Set<FieldDescription.InDefinedShape> registeredFieldCacheFields = new HashSet();
            private final Map<FieldDescription, DelegationRecord> registeredGetters = new HashMap();
            private final Map<FieldDescription, DelegationRecord> registeredSetters = new HashMap();
            private final String suffix = RandomString.make();
            private final TypeInitializer typeInitializer;

            public boolean isEnabled() {
                return true;
            }

            protected Default(TypeDescription typeDescription, ClassFileVersion classFileVersion, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer2, ClassFileVersion classFileVersion2) {
                super(typeDescription, classFileVersion);
                this.auxiliaryTypeNamingStrategy = namingStrategy;
                this.typeInitializer = typeInitializer2;
                this.auxiliaryClassFileVersion = classFileVersion2;
            }

            public MethodDescription.InDefinedShape registerAccessorFor(SpecialMethodInvocation specialMethodInvocation, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecord;
                DelegationRecord delegationRecord2 = this.registeredAccessorMethods.get(specialMethodInvocation);
                if (delegationRecord2 == null) {
                    delegationRecord = new AccessorMethodDelegation(this.instrumentedType, this.suffix, accessType, specialMethodInvocation);
                } else {
                    delegationRecord = delegationRecord2.with(accessType);
                }
                this.registeredAccessorMethods.put(specialMethodInvocation, delegationRecord);
                return delegationRecord.getMethod();
            }

            public MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecord;
                DelegationRecord delegationRecord2 = this.registeredGetters.get(fieldDescription);
                if (delegationRecord2 == null) {
                    delegationRecord = new FieldGetterDelegation(this.instrumentedType, this.suffix, accessType, fieldDescription);
                } else {
                    delegationRecord = delegationRecord2.with(accessType);
                }
                this.registeredGetters.put(fieldDescription, delegationRecord);
                return delegationRecord.getMethod();
            }

            public MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, MethodAccessorFactory.AccessType accessType) {
                DelegationRecord delegationRecord;
                DelegationRecord delegationRecord2 = this.registeredSetters.get(fieldDescription);
                if (delegationRecord2 == null) {
                    delegationRecord = new FieldSetterDelegation(this.instrumentedType, this.suffix, accessType, fieldDescription);
                } else {
                    delegationRecord = delegationRecord2.with(accessType);
                }
                this.registeredSetters.put(fieldDescription, delegationRecord);
                return delegationRecord.getMethod();
            }

            public TypeDescription register(AuxiliaryType auxiliaryType) {
                DynamicType dynamicType = this.auxiliaryTypes.get(auxiliaryType);
                if (dynamicType == null) {
                    dynamicType = auxiliaryType.make(this.auxiliaryTypeNamingStrategy.name(this.instrumentedType), this.auxiliaryClassFileVersion, this);
                    this.auxiliaryTypes.put(auxiliaryType, dynamicType);
                }
                return dynamicType.getTypeDescription();
            }

            public List<DynamicType> getAuxiliaryTypes() {
                return new ArrayList(this.auxiliaryTypes.values());
            }

            public FieldDescription.InDefinedShape cache(StackManipulation stackManipulation, TypeDescription typeDescription) {
                FieldCacheEntry fieldCacheEntry = new FieldCacheEntry(stackManipulation, typeDescription);
                FieldDescription.InDefinedShape inDefinedShape = this.registeredFieldCacheEntries.get(fieldCacheEntry);
                if (inDefinedShape != null) {
                    return inDefinedShape;
                }
                if (this.fieldCacheCanAppendEntries) {
                    int hashCode = stackManipulation.hashCode();
                    while (true) {
                        int i = hashCode + 1;
                        CacheValueField cacheValueField = new CacheValueField(this.instrumentedType, typeDescription.asGenericType(), this.suffix, hashCode);
                        if (this.registeredFieldCacheFields.add(cacheValueField)) {
                            this.registeredFieldCacheEntries.put(fieldCacheEntry, cacheValueField);
                            return cacheValueField;
                        }
                        hashCode = i;
                    }
                } else {
                    throw new IllegalStateException("Cached values cannot be registered after defining the type initializer for " + this.instrumentedType);
                }
            }

            public void drain(TypeInitializer.Drain drain, ClassVisitor classVisitor, AnnotationValueFilter.Factory factory) {
                this.fieldCacheCanAppendEntries = false;
                TypeInitializer typeInitializer2 = this.typeInitializer;
                for (Map.Entry next : this.registeredFieldCacheEntries.entrySet()) {
                    FieldVisitor visitField = classVisitor.visitField(((FieldDescription.InDefinedShape) next.getValue()).getModifiers(), ((FieldDescription.InDefinedShape) next.getValue()).getInternalName(), ((FieldDescription.InDefinedShape) next.getValue()).getDescriptor(), ((FieldDescription.InDefinedShape) next.getValue()).getGenericSignature(), FieldDescription.NO_DEFAULT_VALUE);
                    if (visitField != null) {
                        visitField.visitEnd();
                        typeInitializer2 = typeInitializer2.expandWith(((FieldCacheEntry) next.getKey()).storeIn((FieldDescription) next.getValue()));
                    }
                }
                drain.apply(classVisitor, typeInitializer2, this);
                for (DelegationRecord apply : this.registeredAccessorMethods.values()) {
                    apply.apply(classVisitor, this, factory);
                }
                for (DelegationRecord apply2 : this.registeredGetters.values()) {
                    apply2.apply(classVisitor, this, factory);
                }
                for (DelegationRecord apply3 : this.registeredSetters.values()) {
                    apply3.apply(classVisitor, this, factory);
                }
            }

            protected static class CacheValueField extends FieldDescription.InDefinedShape.AbstractBase {
                private final TypeDescription.Generic fieldType;
                private final TypeDescription instrumentedType;
                private final String name;

                protected CacheValueField(TypeDescription typeDescription, TypeDescription.Generic generic, String str, int i) {
                    this.instrumentedType = typeDescription;
                    this.fieldType = generic;
                    this.name = "cachedValue$" + str + "$" + RandomString.hashOf(i);
                }

                public TypeDescription.Generic getType() {
                    return this.fieldType;
                }

                public AnnotationList getDeclaredAnnotations() {
                    return new AnnotationList.Empty();
                }

                public TypeDescription getDeclaringType() {
                    return this.instrumentedType;
                }

                public int getModifiers() {
                    return (this.instrumentedType.isInterface() ? 1 : 2) | 4120;
                }

                public String getName() {
                    return this.name;
                }
            }

            protected static class FieldCacheEntry implements StackManipulation {
                private final TypeDescription fieldType;
                private final StackManipulation fieldValue;

                protected FieldCacheEntry(StackManipulation stackManipulation, TypeDescription typeDescription) {
                    this.fieldValue = stackManipulation;
                    this.fieldType = typeDescription;
                }

                /* access modifiers changed from: protected */
                public ByteCodeAppender storeIn(FieldDescription fieldDescription) {
                    return new ByteCodeAppender.Simple(this, FieldAccess.forField(fieldDescription).write());
                }

                /* access modifiers changed from: protected */
                public TypeDescription getFieldType() {
                    return this.fieldType;
                }

                public boolean isValid() {
                    return this.fieldValue.isValid();
                }

                public StackManipulation.Size apply(MethodVisitor methodVisitor, Context context) {
                    return this.fieldValue.apply(methodVisitor, context);
                }

                public int hashCode() {
                    return (this.fieldValue.hashCode() * 31) + this.fieldType.hashCode();
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    FieldCacheEntry fieldCacheEntry = (FieldCacheEntry) obj;
                    if (!this.fieldValue.equals(fieldCacheEntry.fieldValue) || !this.fieldType.equals(fieldCacheEntry.fieldType)) {
                        return false;
                    }
                    return true;
                }
            }

            protected static abstract class AbstractPropertyAccessorMethod extends MethodDescription.InDefinedShape.AbstractBase {
                /* access modifiers changed from: protected */
                public abstract int getBaseModifiers();

                protected AbstractPropertyAccessorMethod() {
                }

                public int getModifiers() {
                    return getBaseModifiers() | 4096 | (getDeclaringType().isInterface() ? 1 : 16);
                }
            }

            protected static class AccessorMethod extends AbstractPropertyAccessorMethod {
                private final TypeDescription instrumentedType;
                private final MethodDescription methodDescription;
                private final String name;

                protected AccessorMethod(TypeDescription typeDescription, MethodDescription methodDescription2, String str) {
                    this.instrumentedType = typeDescription;
                    this.methodDescription = methodDescription2;
                    this.name = methodDescription2.getInternalName() + "$" + Default.ACCESSOR_METHOD_SUFFIX + "$" + str;
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
                    return this.instrumentedType;
                }

                /* access modifiers changed from: protected */
                public int getBaseModifiers() {
                    return this.methodDescription.isStatic() ? 8 : 0;
                }

                public String getInternalName() {
                    return this.name;
                }
            }

            protected static class FieldGetter extends AbstractPropertyAccessorMethod {
                private final FieldDescription fieldDescription;
                private final TypeDescription instrumentedType;
                private final String name;

                protected FieldGetter(TypeDescription typeDescription, FieldDescription fieldDescription2, String str) {
                    this.instrumentedType = typeDescription;
                    this.fieldDescription = fieldDescription2;
                    this.name = fieldDescription2.getName() + "$" + Default.ACCESSOR_METHOD_SUFFIX + "$" + str;
                }

                public TypeDescription.Generic getReturnType() {
                    return this.fieldDescription.getType().asRawType();
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
                    return this.instrumentedType;
                }

                /* access modifiers changed from: protected */
                public int getBaseModifiers() {
                    return this.fieldDescription.isStatic() ? 8 : 0;
                }

                public String getInternalName() {
                    return this.name;
                }
            }

            protected static class FieldSetter extends AbstractPropertyAccessorMethod {
                private final FieldDescription fieldDescription;
                private final TypeDescription instrumentedType;
                private final String name;

                protected FieldSetter(TypeDescription typeDescription, FieldDescription fieldDescription2, String str) {
                    this.instrumentedType = typeDescription;
                    this.fieldDescription = fieldDescription2;
                    this.name = fieldDescription2.getName() + "$" + Default.ACCESSOR_METHOD_SUFFIX + "$" + str;
                }

                public TypeDescription.Generic getReturnType() {
                    return TypeDescription.Generic.VOID;
                }

                public ParameterList<ParameterDescription.InDefinedShape> getParameters() {
                    return new ParameterList.Explicit.ForTypes((MethodDescription.InDefinedShape) this, (List<? extends TypeDefinition>) Collections.singletonList(this.fieldDescription.getType().asRawType()));
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
                    return this.instrumentedType;
                }

                /* access modifiers changed from: protected */
                public int getBaseModifiers() {
                    return this.fieldDescription.isStatic() ? 8 : 0;
                }

                public String getInternalName() {
                    return this.name;
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static abstract class DelegationRecord extends TypeWriter.MethodPool.Record.ForDefinedMethod implements ByteCodeAppender {
                protected final MethodDescription.InDefinedShape methodDescription;
                protected final Visibility visibility;

                public void applyAttributes(MethodVisitor methodVisitor, AnnotationValueFilter.Factory factory) {
                }

                public void applyHead(MethodVisitor methodVisitor) {
                }

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    DelegationRecord delegationRecord = (DelegationRecord) obj;
                    return this.visibility.equals(delegationRecord.visibility) && this.methodDescription.equals(delegationRecord.methodDescription);
                }

                public int hashCode() {
                    return ((527 + this.methodDescription.hashCode()) * 31) + this.visibility.hashCode();
                }

                /* access modifiers changed from: protected */
                public abstract DelegationRecord with(MethodAccessorFactory.AccessType accessType);

                protected DelegationRecord(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility2) {
                    this.methodDescription = inDefinedShape;
                    this.visibility = visibility2;
                }

                public MethodDescription.InDefinedShape getMethod() {
                    return this.methodDescription;
                }

                public TypeWriter.MethodPool.Record.Sort getSort() {
                    return TypeWriter.MethodPool.Record.Sort.IMPLEMENTED;
                }

                public Visibility getVisibility() {
                    return this.visibility;
                }

                public void applyBody(MethodVisitor methodVisitor, Context context, AnnotationValueFilter.Factory factory) {
                    methodVisitor.visitCode();
                    ByteCodeAppender.Size applyCode = applyCode(methodVisitor, context);
                    methodVisitor.visitMaxs(applyCode.getOperandStackSize(), applyCode.getLocalVariableSize());
                }

                public ByteCodeAppender.Size applyCode(MethodVisitor methodVisitor, Context context) {
                    return apply(methodVisitor, context, getMethod());
                }

                public TypeWriter.MethodPool.Record prepend(ByteCodeAppender byteCodeAppender) {
                    throw new UnsupportedOperationException("Cannot prepend code to a delegation for " + this.methodDescription);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class AccessorMethodDelegation extends DelegationRecord {
                private final StackManipulation accessorMethodInvocation;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.accessorMethodInvocation.equals(((AccessorMethodDelegation) obj).accessorMethodInvocation);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.accessorMethodInvocation.hashCode();
                }

                protected AccessorMethodDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, SpecialMethodInvocation specialMethodInvocation) {
                    this(new AccessorMethod(typeDescription, specialMethodInvocation.getMethodDescription(), str), accessType.getVisibility(), specialMethodInvocation);
                }

                private AccessorMethodDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, StackManipulation stackManipulation) {
                    super(inDefinedShape, visibility);
                    this.accessorMethodInvocation = stackManipulation;
                }

                /* access modifiers changed from: protected */
                public DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new AccessorMethodDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.accessorMethodInvocation);
                }

                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), this.accessorMethodInvocation, MethodReturn.of(methodDescription.getReturnType())).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class FieldGetterDelegation extends DelegationRecord {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((FieldGetterDelegation) obj).fieldDescription);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.fieldDescription.hashCode();
                }

                protected FieldGetterDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, FieldDescription fieldDescription2) {
                    this(new FieldGetter(typeDescription, fieldDescription2, str), accessType.getVisibility(), fieldDescription2);
                }

                private FieldGetterDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, FieldDescription fieldDescription2) {
                    super(inDefinedShape, visibility);
                    this.fieldDescription = fieldDescription2;
                }

                /* access modifiers changed from: protected */
                public DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new FieldGetterDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.fieldDescription);
                }

                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    StackManipulation stackManipulation;
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    if (this.fieldDescription.isStatic()) {
                        stackManipulation = StackManipulation.Trivial.INSTANCE;
                    } else {
                        stackManipulation = MethodVariableAccess.loadThis();
                    }
                    stackManipulationArr[0] = stackManipulation;
                    stackManipulationArr[1] = FieldAccess.forField(this.fieldDescription).read();
                    stackManipulationArr[2] = MethodReturn.of(this.fieldDescription.getType());
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class FieldSetterDelegation extends DelegationRecord {
                private final FieldDescription fieldDescription;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((FieldSetterDelegation) obj).fieldDescription);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.fieldDescription.hashCode();
                }

                protected FieldSetterDelegation(TypeDescription typeDescription, String str, MethodAccessorFactory.AccessType accessType, FieldDescription fieldDescription2) {
                    this(new FieldSetter(typeDescription, fieldDescription2, str), accessType.getVisibility(), fieldDescription2);
                }

                private FieldSetterDelegation(MethodDescription.InDefinedShape inDefinedShape, Visibility visibility, FieldDescription fieldDescription2) {
                    super(inDefinedShape, visibility);
                    this.fieldDescription = fieldDescription2;
                }

                /* access modifiers changed from: protected */
                public DelegationRecord with(MethodAccessorFactory.AccessType accessType) {
                    return new FieldSetterDelegation(this.methodDescription, this.visibility.expandTo(accessType.getVisibility()), this.fieldDescription);
                }

                public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Context context, MethodDescription methodDescription) {
                    return new ByteCodeAppender.Size(new StackManipulation.Compound(MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference(), FieldAccess.forField(this.fieldDescription).write(), MethodReturn.VOID).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                }
            }

            public enum Factory implements Factory {
                INSTANCE;

                public ExtractableView make(TypeDescription typeDescription, AuxiliaryType.NamingStrategy namingStrategy, TypeInitializer typeInitializer, ClassFileVersion classFileVersion, ClassFileVersion classFileVersion2) {
                    return new Default(typeDescription, classFileVersion, namingStrategy, typeInitializer, classFileVersion2);
                }
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Compound implements Implementation {
        /* access modifiers changed from: private */
        public final List<Implementation> implementations;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.implementations.equals(((Compound) obj).implementations);
        }

        public int hashCode() {
            return 527 + this.implementations.hashCode();
        }

        public Compound(Implementation... implementationArr) {
            this((List<? extends Implementation>) Arrays.asList(implementationArr));
        }

        public Compound(List<? extends Implementation> list) {
            this.implementations = new ArrayList();
            for (Implementation implementation : list) {
                if (implementation instanceof Composable) {
                    Composable composable = (Composable) implementation;
                    this.implementations.addAll(composable.implementations);
                    this.implementations.add(composable.composable);
                } else if (implementation instanceof Compound) {
                    this.implementations.addAll(((Compound) implementation).implementations);
                } else {
                    this.implementations.add(implementation);
                }
            }
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            for (Implementation prepare : this.implementations) {
                instrumentedType = prepare.prepare(instrumentedType);
            }
            return instrumentedType;
        }

        public ByteCodeAppender appender(Target target) {
            ByteCodeAppender[] byteCodeAppenderArr = new ByteCodeAppender[this.implementations.size()];
            int i = 0;
            for (Implementation appender : this.implementations) {
                byteCodeAppenderArr[i] = appender.appender(target);
                i++;
            }
            return new ByteCodeAppender.Compound(byteCodeAppenderArr);
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Composable implements Composable {
            /* access modifiers changed from: private */
            public final Composable composable;
            /* access modifiers changed from: private */
            public final List<Implementation> implementations;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Composable composable2 = (Composable) obj;
                return this.composable.equals(composable2.composable) && this.implementations.equals(composable2.implementations);
            }

            public int hashCode() {
                return ((527 + this.composable.hashCode()) * 31) + this.implementations.hashCode();
            }

            public Composable(Implementation implementation, Composable composable2) {
                this((List<? extends Implementation>) Collections.singletonList(implementation), composable2);
            }

            public Composable(List<? extends Implementation> list, Composable composable2) {
                this.implementations = new ArrayList();
                for (Implementation implementation : list) {
                    if (implementation instanceof Composable) {
                        Composable composable3 = (Composable) implementation;
                        this.implementations.addAll(composable3.implementations);
                        this.implementations.add(composable3.composable);
                    } else if (implementation instanceof Compound) {
                        this.implementations.addAll(((Compound) implementation).implementations);
                    } else {
                        this.implementations.add(implementation);
                    }
                }
                if (composable2 instanceof Composable) {
                    Composable composable4 = (Composable) composable2;
                    this.implementations.addAll(composable4.implementations);
                    this.composable = composable4.composable;
                    return;
                }
                this.composable = composable2;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                for (Implementation prepare : this.implementations) {
                    instrumentedType = prepare.prepare(instrumentedType);
                }
                return this.composable.prepare(instrumentedType);
            }

            public ByteCodeAppender appender(Target target) {
                ByteCodeAppender[] byteCodeAppenderArr = new ByteCodeAppender[(this.implementations.size() + 1)];
                int i = 0;
                for (Implementation appender : this.implementations) {
                    byteCodeAppenderArr[i] = appender.appender(target);
                    i++;
                }
                byteCodeAppenderArr[i] = this.composable.appender(target);
                return new ByteCodeAppender.Compound(byteCodeAppenderArr);
            }

            public Implementation andThen(Implementation implementation) {
                return new Compound((List<? extends Implementation>) CompoundList.of(this.implementations, this.composable.andThen(implementation)));
            }

            public Composable andThen(Composable composable2) {
                return new Composable((List<? extends Implementation>) this.implementations, this.composable.andThen(composable2));
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    public static class Simple implements Implementation {
        private final ByteCodeAppender byteCodeAppender;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.byteCodeAppender.equals(((Simple) obj).byteCodeAppender);
        }

        public int hashCode() {
            return 527 + this.byteCodeAppender.hashCode();
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        public Simple(ByteCodeAppender... byteCodeAppenderArr) {
            this.byteCodeAppender = new ByteCodeAppender.Compound(byteCodeAppenderArr);
        }

        public Simple(StackManipulation... stackManipulationArr) {
            this.byteCodeAppender = new ByteCodeAppender.Simple(stackManipulationArr);
        }

        public ByteCodeAppender appender(Target target) {
            return this.byteCodeAppender;
        }
    }
}

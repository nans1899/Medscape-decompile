package net.bytebuddy.implementation;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DefaultValue;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

@HashCodeAndEqualsPlugin.Enhance
public abstract class FieldAccessor implements Implementation {
    protected final Assigner assigner;
    protected final FieldLocation fieldLocation;
    protected final Assigner.Typing typing;

    public interface AssignerConfigurable extends PropertyConfigurable {
        PropertyConfigurable withAssigner(Assigner assigner, Assigner.Typing typing);
    }

    public interface OwnerTypeLocatable extends AssignerConfigurable {
        AssignerConfigurable in(Class<?> cls);

        AssignerConfigurable in(TypeDescription typeDescription);

        AssignerConfigurable in(FieldLocator.Factory factory);
    }

    public interface PropertyConfigurable extends Implementation {
        Implementation.Composable setsArgumentAt(int i);

        Implementation.Composable setsDefaultValue();

        Implementation.Composable setsFieldValueOf(String str);

        Implementation.Composable setsFieldValueOf(Field field);

        Implementation.Composable setsFieldValueOf(FieldDescription fieldDescription);

        Implementation.Composable setsFieldValueOf(FieldNameExtractor fieldNameExtractor);

        Implementation.Composable setsReference(Object obj);

        Implementation.Composable setsReference(Object obj, String str);

        Implementation.Composable setsValue(Object obj);

        Implementation.Composable setsValue(TypeDescription typeDescription);

        Implementation.Composable setsValue(StackManipulation stackManipulation, Type type);

        Implementation.Composable setsValue(StackManipulation stackManipulation, TypeDescription.Generic generic);

        Implementation.Composable setsValue(JavaConstant javaConstant);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FieldAccessor fieldAccessor = (FieldAccessor) obj;
        return this.typing.equals(fieldAccessor.typing) && this.fieldLocation.equals(fieldAccessor.fieldLocation) && this.assigner.equals(fieldAccessor.assigner);
    }

    public int hashCode() {
        return ((((527 + this.fieldLocation.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.typing.hashCode();
    }

    protected FieldAccessor(FieldLocation fieldLocation2, Assigner assigner2, Assigner.Typing typing2) {
        this.fieldLocation = fieldLocation2;
        this.assigner = assigner2;
        this.typing = typing2;
    }

    public static OwnerTypeLocatable ofField(String str) {
        return of((FieldNameExtractor) new FieldNameExtractor.ForFixedValue(str));
    }

    public static OwnerTypeLocatable ofBeanProperty() {
        return of((FieldNameExtractor) FieldNameExtractor.ForBeanProperty.INSTANCE);
    }

    public static OwnerTypeLocatable of(FieldNameExtractor fieldNameExtractor) {
        return new ForImplicitProperty(new FieldLocation.Relative(fieldNameExtractor));
    }

    public static AssignerConfigurable of(Field field) {
        return of((FieldDescription) new FieldDescription.ForLoadedField(field));
    }

    public static AssignerConfigurable of(FieldDescription fieldDescription) {
        return new ForImplicitProperty(new FieldLocation.Absolute(fieldDescription));
    }

    protected interface FieldLocation {

        public interface Prepared {
            FieldDescription resolve(MethodDescription methodDescription);
        }

        Prepared prepare(TypeDescription typeDescription);

        FieldLocation with(FieldLocator.Factory factory);

        @HashCodeAndEqualsPlugin.Enhance
        public static class Absolute implements FieldLocation, Prepared {
            private final FieldDescription fieldDescription;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.fieldDescription.equals(((Absolute) obj).fieldDescription);
            }

            public int hashCode() {
                return 527 + this.fieldDescription.hashCode();
            }

            protected Absolute(FieldDescription fieldDescription2) {
                this.fieldDescription = fieldDescription2;
            }

            public FieldLocation with(FieldLocator.Factory factory) {
                throw new IllegalStateException("Cannot specify a field locator factory for an absolute field location");
            }

            public Prepared prepare(TypeDescription typeDescription) {
                if (!this.fieldDescription.isStatic() && !typeDescription.isAssignableTo(this.fieldDescription.getDeclaringType().asErasure())) {
                    throw new IllegalStateException(this.fieldDescription + " is not declared by " + typeDescription);
                } else if (this.fieldDescription.isAccessibleTo(typeDescription)) {
                    return this;
                } else {
                    throw new IllegalStateException("Cannot access " + this.fieldDescription + " from " + typeDescription);
                }
            }

            public FieldDescription resolve(MethodDescription methodDescription) {
                return this.fieldDescription;
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Relative implements FieldLocation {
            private final FieldLocator.Factory fieldLocatorFactory;
            private final FieldNameExtractor fieldNameExtractor;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Relative relative = (Relative) obj;
                return this.fieldNameExtractor.equals(relative.fieldNameExtractor) && this.fieldLocatorFactory.equals(relative.fieldLocatorFactory);
            }

            public int hashCode() {
                return ((527 + this.fieldNameExtractor.hashCode()) * 31) + this.fieldLocatorFactory.hashCode();
            }

            protected Relative(FieldNameExtractor fieldNameExtractor2) {
                this(fieldNameExtractor2, FieldLocator.ForClassHierarchy.Factory.INSTANCE);
            }

            private Relative(FieldNameExtractor fieldNameExtractor2, FieldLocator.Factory factory) {
                this.fieldNameExtractor = fieldNameExtractor2;
                this.fieldLocatorFactory = factory;
            }

            public FieldLocation with(FieldLocator.Factory factory) {
                return new Relative(this.fieldNameExtractor, factory);
            }

            public Prepared prepare(TypeDescription typeDescription) {
                return new Prepared(this.fieldNameExtractor, this.fieldLocatorFactory.make(typeDescription));
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Prepared implements Prepared {
                private final FieldLocator fieldLocator;
                private final FieldNameExtractor fieldNameExtractor;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Prepared prepared = (Prepared) obj;
                    return this.fieldNameExtractor.equals(prepared.fieldNameExtractor) && this.fieldLocator.equals(prepared.fieldLocator);
                }

                public int hashCode() {
                    return ((527 + this.fieldNameExtractor.hashCode()) * 31) + this.fieldLocator.hashCode();
                }

                protected Prepared(FieldNameExtractor fieldNameExtractor2, FieldLocator fieldLocator2) {
                    this.fieldNameExtractor = fieldNameExtractor2;
                    this.fieldLocator = fieldLocator2;
                }

                public FieldDescription resolve(MethodDescription methodDescription) {
                    FieldLocator.Resolution locate = this.fieldLocator.locate(this.fieldNameExtractor.resolve(methodDescription));
                    if (locate.isResolved()) {
                        return locate.getField();
                    }
                    throw new IllegalStateException("Cannot resolve field for " + methodDescription + " using " + this.fieldLocator);
                }
            }
        }
    }

    public interface FieldNameExtractor {
        String resolve(MethodDescription methodDescription);

        public enum ForBeanProperty implements FieldNameExtractor {
            INSTANCE;

            public String resolve(MethodDescription methodDescription) {
                int i;
                String internalName = methodDescription.getInternalName();
                if (internalName.startsWith("get") || internalName.startsWith("set")) {
                    i = 3;
                } else if (internalName.startsWith("is")) {
                    i = 2;
                } else {
                    throw new IllegalArgumentException(methodDescription + " does not follow Java bean naming conventions");
                }
                String substring = internalName.substring(i);
                if (substring.length() != 0) {
                    return Character.toLowerCase(substring.charAt(0)) + substring.substring(1);
                }
                throw new IllegalArgumentException(methodDescription + " does not specify a bean name");
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class ForFixedValue implements FieldNameExtractor {
            private final String name;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.name.equals(((ForFixedValue) obj).name);
            }

            public int hashCode() {
                return 527 + this.name.hashCode();
            }

            protected ForFixedValue(String str) {
                this.name = str;
            }

            public String resolve(MethodDescription methodDescription) {
                return this.name;
            }
        }
    }

    protected static class ForImplicitProperty extends FieldAccessor implements OwnerTypeLocatable {
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        protected ForImplicitProperty(FieldLocation fieldLocation) {
            this(fieldLocation, Assigner.DEFAULT, Assigner.Typing.STATIC);
        }

        private ForImplicitProperty(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing) {
            super(fieldLocation, assigner, typing);
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType(), this.fieldLocation.prepare(target.getInstrumentedType()));
        }

        public Implementation.Composable setsArgumentAt(int i) {
            if (i >= 0) {
                return new ForSetter.OfParameterValue(this.fieldLocation, this.assigner, this.typing, ForSetter.TerminationHandler.RETURNING, i);
            }
            throw new IllegalArgumentException("A parameter index cannot be negative: " + i);
        }

        public Implementation.Composable setsDefaultValue() {
            return new ForSetter.OfDefaultValue(this.fieldLocation, this.assigner, this.typing, ForSetter.TerminationHandler.RETURNING);
        }

        public Implementation.Composable setsValue(Object obj) {
            Class<?> cls = obj.getClass();
            if (cls == String.class) {
                return setsValue((StackManipulation) new TextConstant((String) obj), (Type) String.class);
            }
            if (cls == Class.class) {
                return setsValue(ClassConstant.of(TypeDescription.ForLoadedType.of((Class) obj)), (Type) Class.class);
            }
            if (cls == Boolean.class) {
                return setsValue(IntegerConstant.forValue(((Boolean) obj).booleanValue()), (Type) Boolean.TYPE);
            }
            if (cls == Byte.class) {
                return setsValue(IntegerConstant.forValue((int) ((Byte) obj).byteValue()), (Type) Byte.TYPE);
            }
            if (cls == Short.class) {
                return setsValue(IntegerConstant.forValue((int) ((Short) obj).shortValue()), (Type) Short.TYPE);
            }
            if (cls == Character.class) {
                return setsValue(IntegerConstant.forValue((int) ((Character) obj).charValue()), (Type) Character.TYPE);
            }
            if (cls == Integer.class) {
                return setsValue(IntegerConstant.forValue(((Integer) obj).intValue()), (Type) Integer.TYPE);
            }
            if (cls == Long.class) {
                return setsValue(LongConstant.forValue(((Long) obj).longValue()), (Type) Long.TYPE);
            }
            if (cls == Float.class) {
                return setsValue(FloatConstant.forValue(((Float) obj).floatValue()), (Type) Float.TYPE);
            }
            if (cls == Double.class) {
                return setsValue(DoubleConstant.forValue(((Double) obj).doubleValue()), (Type) Double.TYPE);
            }
            if (JavaType.METHOD_HANDLE.getTypeStub().isAssignableFrom(cls)) {
                return setsValue((StackManipulation) new JavaConstantValue(JavaConstant.MethodHandle.ofLoaded(obj)), (Type) cls);
            }
            if (JavaType.METHOD_TYPE.getTypeStub().represents(cls)) {
                return setsValue((StackManipulation) new JavaConstantValue(JavaConstant.MethodType.ofLoaded(obj)), (Type) cls);
            }
            return setsReference(obj);
        }

        public Implementation.Composable setsValue(TypeDescription typeDescription) {
            return setsValue(ClassConstant.of(typeDescription), (Type) Class.class);
        }

        public Implementation.Composable setsValue(JavaConstant javaConstant) {
            return setsValue((StackManipulation) new JavaConstantValue(javaConstant), javaConstant.getType().asGenericType());
        }

        public Implementation.Composable setsValue(StackManipulation stackManipulation, Type type) {
            return setsValue(stackManipulation, TypeDefinition.Sort.describe(type));
        }

        public Implementation.Composable setsValue(StackManipulation stackManipulation, TypeDescription.Generic generic) {
            return new ForSetter.OfConstantValue(this.fieldLocation, this.assigner, this.typing, ForSetter.TerminationHandler.RETURNING, generic, stackManipulation);
        }

        public Implementation.Composable setsReference(Object obj) {
            return setsReference(obj, "fixedFieldValue$" + RandomString.hashOf(obj.hashCode()));
        }

        public Implementation.Composable setsReference(Object obj, String str) {
            return new ForSetter.OfReferenceValue(this.fieldLocation, this.assigner, this.typing, ForSetter.TerminationHandler.RETURNING, obj, str);
        }

        public Implementation.Composable setsFieldValueOf(Field field) {
            return setsFieldValueOf((FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public Implementation.Composable setsFieldValueOf(FieldDescription fieldDescription) {
            return new ForSetter.OfFieldValue(this.fieldLocation, this.assigner, this.typing, ForSetter.TerminationHandler.RETURNING, new FieldLocation.Absolute(fieldDescription));
        }

        public Implementation.Composable setsFieldValueOf(String str) {
            return setsFieldValueOf((FieldNameExtractor) new FieldNameExtractor.ForFixedValue(str));
        }

        public Implementation.Composable setsFieldValueOf(FieldNameExtractor fieldNameExtractor) {
            return new ForSetter.OfFieldValue(this.fieldLocation, this.assigner, this.typing, ForSetter.TerminationHandler.RETURNING, new FieldLocation.Relative(fieldNameExtractor));
        }

        public PropertyConfigurable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new ForImplicitProperty(this.fieldLocation, assigner, typing);
        }

        public AssignerConfigurable in(Class<?> cls) {
            return in(TypeDescription.ForLoadedType.of(cls));
        }

        public AssignerConfigurable in(TypeDescription typeDescription) {
            return in((FieldLocator.Factory) new FieldLocator.ForExactType.Factory(typeDescription));
        }

        public AssignerConfigurable in(FieldLocator.Factory factory) {
            return new ForImplicitProperty(this.fieldLocation.with(factory), this.assigner, this.typing);
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Appender implements ByteCodeAppender {
            private final FieldLocation.Prepared fieldLocation;
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.instrumentedType.equals(appender.instrumentedType) && this.fieldLocation.equals(appender.fieldLocation) && ForImplicitProperty.this.equals(ForImplicitProperty.this);
            }

            public int hashCode() {
                return ((((527 + this.instrumentedType.hashCode()) * 31) + this.fieldLocation.hashCode()) * 31) + ForImplicitProperty.this.hashCode();
            }

            protected Appender(TypeDescription typeDescription, FieldLocation.Prepared prepared) {
                this.instrumentedType = typeDescription;
                this.fieldLocation = prepared;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                StackManipulation stackManipulation;
                StackManipulation.Compound compound;
                if (methodDescription.isMethod()) {
                    FieldDescription resolve = this.fieldLocation.resolve(methodDescription);
                    if (resolve.isStatic() || !methodDescription.isStatic()) {
                        if (resolve.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = MethodVariableAccess.loadThis();
                        }
                        if (!methodDescription.getReturnType().represents(Void.TYPE)) {
                            compound = new StackManipulation.Compound(stackManipulation, FieldAccess.forField(resolve).read(), ForImplicitProperty.this.assigner.assign(resolve.getType(), methodDescription.getReturnType(), ForImplicitProperty.this.typing), MethodReturn.of(methodDescription.getReturnType()));
                        } else if (!methodDescription.getReturnType().represents(Void.TYPE) || methodDescription.getParameters().size() != 1) {
                            throw new IllegalArgumentException("Method " + context + " is no bean property");
                        } else if (!resolve.isFinal() || !methodDescription.isMethod()) {
                            compound = new StackManipulation.Compound(stackManipulation, MethodVariableAccess.load((ParameterDescription) methodDescription.getParameters().get(0)), ForImplicitProperty.this.assigner.assign(((ParameterDescription) methodDescription.getParameters().get(0)).getType(), resolve.getType(), ForImplicitProperty.this.typing), FieldAccess.forField(resolve).write(), MethodReturn.VOID);
                        } else {
                            throw new IllegalStateException("Cannot set final field " + resolve + " from " + methodDescription);
                        }
                        if (compound.isValid()) {
                            return new ByteCodeAppender.Size(compound.apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                        }
                        throw new IllegalStateException("Cannot set or get value of " + methodDescription + " using " + resolve);
                    }
                    throw new IllegalStateException("Cannot set instance field " + resolve + " from " + methodDescription);
                }
                throw new IllegalArgumentException(methodDescription + " does not describe a field getter or setter");
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static abstract class ForSetter<T> extends FieldAccessor implements Implementation.Composable {
        /* access modifiers changed from: private */
        public final TerminationHandler terminationHandler;

        protected enum TerminationHandler {
            RETURNING {
                /* access modifiers changed from: protected */
                public StackManipulation resolve(MethodDescription methodDescription) {
                    if (methodDescription.getReturnType().represents(Void.TYPE)) {
                        return MethodReturn.VOID;
                    }
                    throw new IllegalStateException("Cannot implement setter with return value for " + methodDescription);
                }
            },
            NON_OPERATIONAL {
                /* access modifiers changed from: protected */
                public StackManipulation resolve(MethodDescription methodDescription) {
                    return StackManipulation.Trivial.INSTANCE;
                }
            };

            /* access modifiers changed from: protected */
            public abstract StackManipulation resolve(MethodDescription methodDescription);
        }

        public boolean equals(Object obj) {
            if (!FieldAccessor.super.equals(obj)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.terminationHandler.equals(((ForSetter) obj).terminationHandler);
        }

        public int hashCode() {
            return (FieldAccessor.super.hashCode() * 31) + this.terminationHandler.hashCode();
        }

        /* access modifiers changed from: protected */
        public abstract T initialize(TypeDescription typeDescription);

        /* access modifiers changed from: protected */
        public abstract StackManipulation resolve(T t, FieldDescription fieldDescription, TypeDescription typeDescription, MethodDescription methodDescription);

        protected ForSetter(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, TerminationHandler terminationHandler2) {
            super(fieldLocation, assigner, typing);
            this.terminationHandler = terminationHandler2;
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return new Appender(target.getInstrumentedType(), initialize(target.getInstrumentedType()), this.fieldLocation.prepare(target.getInstrumentedType()));
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class OfParameterValue extends ForSetter<Void> {
            private final int index;

            public boolean equals(Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.index == ((OfParameterValue) obj).index;
            }

            public int hashCode() {
                return (super.hashCode() * 31) + this.index;
            }

            /* access modifiers changed from: protected */
            public Void initialize(TypeDescription typeDescription) {
                return null;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected OfParameterValue(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, TerminationHandler terminationHandler, int i) {
                super(fieldLocation, assigner, typing, terminationHandler);
                this.index = i;
            }

            /* access modifiers changed from: protected */
            public StackManipulation resolve(Void voidR, FieldDescription fieldDescription, TypeDescription typeDescription, MethodDescription methodDescription) {
                if (methodDescription.getParameters().size() > this.index) {
                    return new StackManipulation.Compound(MethodVariableAccess.load((ParameterDescription) methodDescription.getParameters().get(this.index)), this.assigner.assign(((ParameterDescription) methodDescription.getParameters().get(this.index)).getType(), fieldDescription.getType(), this.typing));
                }
                throw new IllegalStateException(methodDescription + " does not define a parameter with index " + this.index);
            }

            public Implementation andThen(Implementation implementation) {
                return new Implementation.Compound(new OfParameterValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.index), implementation);
            }

            public Implementation.Composable andThen(Implementation.Composable composable) {
                return new Implementation.Compound.Composable((Implementation) new OfParameterValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.index), composable);
            }
        }

        protected static class OfDefaultValue extends ForSetter<Void> {
            /* access modifiers changed from: protected */
            public Void initialize(TypeDescription typeDescription) {
                return null;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected OfDefaultValue(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, TerminationHandler terminationHandler) {
                super(fieldLocation, assigner, typing, terminationHandler);
            }

            /* access modifiers changed from: protected */
            public StackManipulation resolve(Void voidR, FieldDescription fieldDescription, TypeDescription typeDescription, MethodDescription methodDescription) {
                return DefaultValue.of(fieldDescription.getType());
            }

            public Implementation andThen(Implementation implementation) {
                return new Implementation.Compound(new OfDefaultValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL), implementation);
            }

            public Implementation.Composable andThen(Implementation.Composable composable) {
                return new Implementation.Compound.Composable((Implementation) new OfDefaultValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL), composable);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class OfConstantValue extends ForSetter<Void> {
            private final StackManipulation stackManipulation;
            private final TypeDescription.Generic typeDescription;

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
                OfConstantValue ofConstantValue = (OfConstantValue) obj;
                return this.typeDescription.equals(ofConstantValue.typeDescription) && this.stackManipulation.equals(ofConstantValue.stackManipulation);
            }

            public int hashCode() {
                return (((super.hashCode() * 31) + this.typeDescription.hashCode()) * 31) + this.stackManipulation.hashCode();
            }

            /* access modifiers changed from: protected */
            public Void initialize(TypeDescription typeDescription2) {
                return null;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected OfConstantValue(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, TerminationHandler terminationHandler, TypeDescription.Generic generic, StackManipulation stackManipulation2) {
                super(fieldLocation, assigner, typing, terminationHandler);
                this.typeDescription = generic;
                this.stackManipulation = stackManipulation2;
            }

            /* access modifiers changed from: protected */
            public StackManipulation resolve(Void voidR, FieldDescription fieldDescription, TypeDescription typeDescription2, MethodDescription methodDescription) {
                return new StackManipulation.Compound(this.stackManipulation, this.assigner.assign(this.typeDescription, fieldDescription.getType(), this.typing));
            }

            public Implementation andThen(Implementation implementation) {
                return new Implementation.Compound(new OfConstantValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.typeDescription, this.stackManipulation), implementation);
            }

            public Implementation.Composable andThen(Implementation.Composable composable) {
                return new Implementation.Compound.Composable((Implementation) new OfConstantValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.typeDescription, this.stackManipulation), composable);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class OfReferenceValue extends ForSetter<FieldDescription.InDefinedShape> {
            protected static final String PREFIX = "fixedFieldValue";
            private final String name;
            private final Object value;

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
                OfReferenceValue ofReferenceValue = (OfReferenceValue) obj;
                return this.name.equals(ofReferenceValue.name) && this.value.equals(ofReferenceValue.value);
            }

            public int hashCode() {
                return (((super.hashCode() * 31) + this.value.hashCode()) * 31) + this.name.hashCode();
            }

            protected OfReferenceValue(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, TerminationHandler terminationHandler, Object obj, String str) {
                super(fieldLocation, assigner, typing, terminationHandler);
                this.value = obj;
                this.name = str;
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType.withField(new FieldDescription.Token(this.name, 4105, TypeDescription.ForLoadedType.of(this.value.getClass()).asGenericType())).withInitializer((LoadedTypeInitializer) new LoadedTypeInitializer.ForStaticField(this.name, this.value));
            }

            /* access modifiers changed from: protected */
            public FieldDescription.InDefinedShape initialize(TypeDescription typeDescription) {
                return (FieldDescription.InDefinedShape) ((FieldList) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.name))).getOnly();
            }

            /* access modifiers changed from: protected */
            public StackManipulation resolve(FieldDescription.InDefinedShape inDefinedShape, FieldDescription fieldDescription, TypeDescription typeDescription, MethodDescription methodDescription) {
                if (!fieldDescription.isFinal() || !methodDescription.isMethod()) {
                    return new StackManipulation.Compound(FieldAccess.forField(inDefinedShape).read(), this.assigner.assign(TypeDescription.ForLoadedType.of(this.value.getClass()).asGenericType(), fieldDescription.getType(), this.typing));
                }
                throw new IllegalArgumentException("Cannot set final field " + fieldDescription + " from " + methodDescription);
            }

            public Implementation andThen(Implementation implementation) {
                return new Implementation.Compound(new OfReferenceValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.value, this.name), implementation);
            }

            public Implementation.Composable andThen(Implementation.Composable composable) {
                return new Implementation.Compound.Composable((Implementation) new OfReferenceValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.value, this.name), composable);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        protected static class OfFieldValue extends ForSetter<FieldLocation.Prepared> {
            private final FieldLocation target;

            public boolean equals(Object obj) {
                if (!super.equals(obj)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                return obj != null && getClass() == obj.getClass() && this.target.equals(((OfFieldValue) obj).target);
            }

            public int hashCode() {
                return (super.hashCode() * 31) + this.target.hashCode();
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                return instrumentedType;
            }

            protected OfFieldValue(FieldLocation fieldLocation, Assigner assigner, Assigner.Typing typing, TerminationHandler terminationHandler, FieldLocation fieldLocation2) {
                super(fieldLocation, assigner, typing, terminationHandler);
                this.target = fieldLocation2;
            }

            /* access modifiers changed from: protected */
            public FieldLocation.Prepared initialize(TypeDescription typeDescription) {
                return this.target.prepare(typeDescription);
            }

            /* access modifiers changed from: protected */
            public StackManipulation resolve(FieldLocation.Prepared prepared, FieldDescription fieldDescription, TypeDescription typeDescription, MethodDescription methodDescription) {
                StackManipulation stackManipulation;
                FieldDescription resolve = prepared.resolve(methodDescription);
                if (resolve.isStatic() || !methodDescription.isStatic()) {
                    StackManipulation[] stackManipulationArr = new StackManipulation[3];
                    if (resolve.isStatic()) {
                        stackManipulation = StackManipulation.Trivial.INSTANCE;
                    } else {
                        stackManipulation = MethodVariableAccess.loadThis();
                    }
                    stackManipulationArr[0] = stackManipulation;
                    stackManipulationArr[1] = FieldAccess.forField(resolve).read();
                    stackManipulationArr[2] = this.assigner.assign(resolve.getType(), fieldDescription.getType(), this.typing);
                    return new StackManipulation.Compound(stackManipulationArr);
                }
                throw new IllegalStateException("Cannot set instance field " + fieldDescription + " from " + methodDescription);
            }

            public Implementation andThen(Implementation implementation) {
                return new Implementation.Compound(new OfFieldValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.target), implementation);
            }

            public Implementation.Composable andThen(Implementation.Composable composable) {
                return new Implementation.Compound.Composable((Implementation) new OfFieldValue(this.fieldLocation, this.assigner, this.typing, TerminationHandler.NON_OPERATIONAL, this.target), composable);
            }
        }

        @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
        protected class Appender implements ByteCodeAppender {
            private final FieldLocation.Prepared fieldLocation;
            private final T initialized;
            private final TypeDescription instrumentedType;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Appender appender = (Appender) obj;
                return this.instrumentedType.equals(appender.instrumentedType) && this.initialized.equals(appender.initialized) && this.fieldLocation.equals(appender.fieldLocation) && ForSetter.this.equals(ForSetter.this);
            }

            public int hashCode() {
                return ((((((527 + this.instrumentedType.hashCode()) * 31) + this.initialized.hashCode()) * 31) + this.fieldLocation.hashCode()) * 31) + ForSetter.this.hashCode();
            }

            protected Appender(TypeDescription typeDescription, T t, FieldLocation.Prepared prepared) {
                this.instrumentedType = typeDescription;
                this.initialized = t;
                this.fieldLocation = prepared;
            }

            public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
                StackManipulation stackManipulation;
                FieldDescription resolve = this.fieldLocation.resolve(methodDescription);
                if (!resolve.isStatic() && methodDescription.isStatic()) {
                    throw new IllegalStateException("Cannot set instance field " + resolve + " from " + methodDescription);
                } else if (!resolve.isFinal() || !methodDescription.isMethod()) {
                    StackManipulation resolve2 = ForSetter.this.resolve(this.initialized, resolve, this.instrumentedType, methodDescription);
                    if (resolve2.isValid()) {
                        StackManipulation[] stackManipulationArr = new StackManipulation[4];
                        if (methodDescription.isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = MethodVariableAccess.loadThis();
                        }
                        stackManipulationArr[0] = stackManipulation;
                        stackManipulationArr[1] = resolve2;
                        stackManipulationArr[2] = FieldAccess.forField(resolve).write();
                        stackManipulationArr[3] = ForSetter.this.terminationHandler.resolve(methodDescription);
                        return new ByteCodeAppender.Size(new StackManipulation.Compound(stackManipulationArr).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
                    }
                    throw new IllegalStateException("Set value cannot be assigned to " + resolve);
                } else {
                    throw new IllegalStateException("Cannot set final field " + resolve + " from " + methodDescription);
                }
            }
        }
    }
}

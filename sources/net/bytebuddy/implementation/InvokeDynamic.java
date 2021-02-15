package net.bytebuddy.implementation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.method.ParameterList;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.scaffold.FieldLocator;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.Removal;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.ClassConstant;
import net.bytebuddy.implementation.bytecode.constant.DoubleConstant;
import net.bytebuddy.implementation.bytecode.constant.FloatConstant;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.JavaConstantValue;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import net.bytebuddy.implementation.bytecode.constant.TextConstant;
import net.bytebuddy.implementation.bytecode.member.FieldAccess;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.implementation.bytecode.member.MethodVariableAccess;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.JavaConstant;
import net.bytebuddy.utility.JavaType;
import net.bytebuddy.utility.RandomString;

@HashCodeAndEqualsPlugin.Enhance
public class InvokeDynamic implements Implementation.Composable {
    protected final Assigner assigner;
    protected final MethodDescription.InDefinedShape bootstrapMethod;
    protected final List<?> handleArguments;
    protected final InvocationProvider invocationProvider;
    protected final TerminationHandler terminationHandler;
    protected final Assigner.Typing typing;

    protected enum TerminationHandler {
        RETURNING {
            /* access modifiers changed from: protected */
            public StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                StackManipulation assign = assigner.assign(typeDescription.asGenericType(), methodDescription.getReturnType(), typing);
                if (assign.isValid()) {
                    return new StackManipulation.Compound(assign, MethodReturn.of(methodDescription.getReturnType()));
                }
                throw new IllegalStateException("Cannot return " + typeDescription + " from " + methodDescription);
            }
        },
        DROPPING {
            /* access modifiers changed from: protected */
            public StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                TypeDefinition typeDefinition;
                if (methodDescription.isConstructor()) {
                    typeDefinition = methodDescription.getDeclaringType();
                } else {
                    typeDefinition = methodDescription.getReturnType();
                }
                return Removal.of(typeDefinition);
            }
        };

        /* access modifiers changed from: protected */
        public abstract StackManipulation resolve(MethodDescription methodDescription, TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InvokeDynamic invokeDynamic = (InvokeDynamic) obj;
        return this.terminationHandler.equals(invokeDynamic.terminationHandler) && this.typing.equals(invokeDynamic.typing) && this.bootstrapMethod.equals(invokeDynamic.bootstrapMethod) && this.handleArguments.equals(invokeDynamic.handleArguments) && this.invocationProvider.equals(invokeDynamic.invocationProvider) && this.assigner.equals(invokeDynamic.assigner);
    }

    public int hashCode() {
        return ((((((((((527 + this.bootstrapMethod.hashCode()) * 31) + this.handleArguments.hashCode()) * 31) + this.invocationProvider.hashCode()) * 31) + this.terminationHandler.hashCode()) * 31) + this.assigner.hashCode()) * 31) + this.typing.hashCode();
    }

    protected InvokeDynamic(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider2, TerminationHandler terminationHandler2, Assigner assigner2, Assigner.Typing typing2) {
        this.bootstrapMethod = inDefinedShape;
        this.handleArguments = list;
        this.invocationProvider = invocationProvider2;
        this.terminationHandler = terminationHandler2;
        this.assigner = assigner2;
        this.typing = typing2;
    }

    public static WithImplicitTarget bootstrap(Method method, Object... objArr) {
        return bootstrap((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method), objArr);
    }

    public static WithImplicitTarget bootstrap(Method method, List<?> list) {
        return bootstrap((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method), list);
    }

    public static WithImplicitTarget bootstrap(Constructor<?> constructor, Object... objArr) {
        return bootstrap((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(constructor), objArr);
    }

    public static WithImplicitTarget bootstrap(Constructor<?> constructor, List<?> list) {
        return bootstrap((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(constructor), list);
    }

    public static WithImplicitTarget bootstrap(MethodDescription.InDefinedShape inDefinedShape, Object... objArr) {
        return bootstrap(inDefinedShape, (List<?>) Arrays.asList(objArr));
    }

    public static WithImplicitTarget bootstrap(MethodDescription.InDefinedShape inDefinedShape, List<?> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object next : list) {
            if (next == null) {
                next = JavaConstant.Dynamic.ofNullConstant();
            } else if (next instanceof Class) {
                Class cls = (Class) next;
                if (cls.isPrimitive()) {
                    next = JavaConstant.Dynamic.ofPrimitiveType((Class<?>) cls);
                } else {
                    next = TypeDescription.ForLoadedType.of(cls);
                }
            } else {
                if (next instanceof TypeDescription) {
                    TypeDescription typeDescription = (TypeDescription) next;
                    if (typeDescription.isPrimitive()) {
                        next = JavaConstant.Dynamic.ofPrimitiveType(typeDescription);
                    }
                }
                if (JavaType.METHOD_HANDLE.isInstance(next)) {
                    next = JavaConstant.MethodHandle.ofLoaded(next);
                } else if (JavaType.METHOD_TYPE.isInstance(next)) {
                    next = JavaConstant.MethodType.ofLoaded(next);
                }
            }
            arrayList.add(next);
        }
        if (inDefinedShape.isInvokeBootstrap(arrayList)) {
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            for (Object next2 : arrayList) {
                if (next2 instanceof TypeDescription) {
                    next2 = Type.getType(((TypeDescription) next2).getDescriptor());
                } else if (next2 instanceof JavaConstant) {
                    next2 = ((JavaConstant) next2).asConstantPoolValue();
                }
                arrayList2.add(next2);
            }
            return new WithImplicitTarget(inDefinedShape, arrayList2, new InvocationProvider.Default(), TerminationHandler.RETURNING, Assigner.DEFAULT, Assigner.Typing.STATIC);
        }
        throw new IllegalArgumentException("Not a valid bootstrap method " + inDefinedShape + " for " + arrayList);
    }

    public static WithImplicitArguments lambda(Method method, Class<?> cls) {
        return lambda((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method), TypeDescription.ForLoadedType.of(cls));
    }

    public static WithImplicitArguments lambda(Method method, Class<?> cls, MethodGraph.Compiler compiler) {
        return lambda((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method), TypeDescription.ForLoadedType.of(cls), compiler);
    }

    public static WithImplicitArguments lambda(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
        return lambda(inDefinedShape, typeDescription, MethodGraph.Compiler.Default.forJavaHierarchy());
    }

    public static WithImplicitArguments lambda(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription, MethodGraph.Compiler compiler) {
        TypeDescription typeDescription2 = typeDescription;
        if (typeDescription.isInterface()) {
            MethodList methodList = (MethodList) compiler.compile(typeDescription2).listNodes().asMethodList().filter(ElementMatchers.isAbstract());
            if (methodList.size() == 1) {
                return bootstrap((MethodDescription.InDefinedShape) new MethodDescription.Latent(new TypeDescription.Latent("java.lang.invoke.LambdaMetafactory", 1, TypeDescription.Generic.OBJECT, new TypeDescription.Generic[0]), "metafactory", 9, Collections.emptyList(), JavaType.CALL_SITE.getTypeStub().asGenericType(), Arrays.asList(new ParameterDescription.Token[]{new ParameterDescription.Token(JavaType.METHOD_HANDLES_LOOKUP.getTypeStub().asGenericType()), new ParameterDescription.Token(TypeDescription.STRING.asGenericType()), new ParameterDescription.Token(JavaType.METHOD_TYPE.getTypeStub().asGenericType()), new ParameterDescription.Token(JavaType.METHOD_TYPE.getTypeStub().asGenericType()), new ParameterDescription.Token(JavaType.METHOD_HANDLE.getTypeStub().asGenericType()), new ParameterDescription.Token(JavaType.METHOD_TYPE.getTypeStub().asGenericType())}), Collections.emptyList(), Collections.emptyList(), AnnotationValue.UNDEFINED, TypeDescription.Generic.UNDEFINED), JavaConstant.MethodType.of((MethodDescription) methodList.asDefined().getOnly()), JavaConstant.MethodHandle.of(inDefinedShape), JavaConstant.MethodType.of((MethodDescription) methodList.asDefined().getOnly())).invoke(methodList.asDefined().getOnly().getInternalName());
            }
            throw new IllegalArgumentException(typeDescription2 + " does not define exactly one abstract method: " + methodList);
        }
        throw new IllegalArgumentException(typeDescription2 + " is not an interface type");
    }

    public InvokeDynamic withBooleanValue(boolean... zArr) {
        ArrayList arrayList = new ArrayList(zArr.length);
        for (boolean forBooleanConstant : zArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForBooleanConstant(forBooleanConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withByteValue(byte... bArr) {
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte forByteConstant : bArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForByteConstant(forByteConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withShortValue(short... sArr) {
        ArrayList arrayList = new ArrayList(sArr.length);
        for (short forShortConstant : sArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForShortConstant(forShortConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withCharacterValue(char... cArr) {
        ArrayList arrayList = new ArrayList(cArr.length);
        for (char forCharacterConstant : cArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForCharacterConstant(forCharacterConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withIntegerValue(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int forIntegerConstant : iArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForIntegerConstant(forIntegerConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withLongValue(long... jArr) {
        ArrayList arrayList = new ArrayList(jArr.length);
        for (long forLongConstant : jArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForLongConstant(forLongConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withFloatValue(float... fArr) {
        ArrayList arrayList = new ArrayList(fArr.length);
        for (float forFloatConstant : fArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForFloatConstant(forFloatConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withDoubleValue(double... dArr) {
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double forDoubleConstant : dArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForDoubleConstant(forDoubleConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withValue(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object of : objArr) {
            arrayList.add(InvocationProvider.ArgumentProvider.ConstantPoolWrapper.of(of));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public WithImplicitType withReference(Object obj) {
        return new WithImplicitType.OfInstance(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, this.assigner, this.typing, obj);
    }

    public InvokeDynamic withReference(Object... objArr) {
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object of : objArr) {
            arrayList.add(InvocationProvider.ArgumentProvider.ForInstance.of(of));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withType(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription forClassConstant : typeDescriptionArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForClassConstant(forClassConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withEnumeration(EnumerationDescription... enumerationDescriptionArr) {
        ArrayList arrayList = new ArrayList(enumerationDescriptionArr.length);
        for (EnumerationDescription forEnumerationValue : enumerationDescriptionArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForEnumerationValue(forEnumerationValue));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withInstance(JavaConstant... javaConstantArr) {
        ArrayList arrayList = new ArrayList(javaConstantArr.length);
        for (JavaConstant forJavaConstant : javaConstantArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForJavaConstant(forJavaConstant));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withNullValue(Class<?>... clsArr) {
        return withNullValue((TypeDescription[]) new TypeList.ForLoadedTypes(clsArr).toArray(new TypeDescription[clsArr.length]));
    }

    public InvokeDynamic withNullValue(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        int length = typeDescriptionArr.length;
        int i = 0;
        while (i < length) {
            TypeDescription typeDescription = typeDescriptionArr[i];
            if (!typeDescription.isPrimitive()) {
                arrayList.add(new InvocationProvider.ArgumentProvider.ForNullValue(typeDescription));
                i++;
            } else {
                throw new IllegalArgumentException("Cannot assign null to primitive type: " + typeDescription);
            }
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withArgument(int... iArr) {
        ArrayList arrayList = new ArrayList(iArr.length);
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            if (i2 >= 0) {
                arrayList.add(new InvocationProvider.ArgumentProvider.ForMethodParameter(i2));
                i++;
            } else {
                throw new IllegalArgumentException("Method parameter indices cannot be negative: " + i2);
            }
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public WithImplicitType withArgument(int i) {
        if (i >= 0) {
            return new WithImplicitType.OfArgument(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, this.assigner, this.typing, i);
        }
        throw new IllegalArgumentException("Method parameter indices cannot be negative: " + i);
    }

    public InvokeDynamic withThis(Class<?>... clsArr) {
        return withThis((TypeDescription[]) new TypeList.ForLoadedTypes(clsArr).toArray(new TypeDescription[clsArr.length]));
    }

    public InvokeDynamic withThis(TypeDescription... typeDescriptionArr) {
        ArrayList arrayList = new ArrayList(typeDescriptionArr.length);
        for (TypeDescription forThisInstance : typeDescriptionArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForThisInstance(forThisInstance));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withMethodArguments() {
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(InvocationProvider.ArgumentProvider.ForInterceptedMethodParameters.INSTANCE), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withImplicitAndMethodArguments() {
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(InvocationProvider.ArgumentProvider.ForInterceptedMethodInstanceAndParameters.INSTANCE), this.terminationHandler, this.assigner, this.typing);
    }

    public InvokeDynamic withField(String... strArr) {
        return withField((FieldLocator.Factory) FieldLocator.ForClassHierarchy.Factory.INSTANCE, strArr);
    }

    public InvokeDynamic withField(FieldLocator.Factory factory, String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String forField : strArr) {
            arrayList.add(new InvocationProvider.ArgumentProvider.ForField(forField, factory));
        }
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArguments(arrayList), this.terminationHandler, this.assigner, this.typing);
    }

    public WithImplicitType withField(String str) {
        return withField(str, (FieldLocator.Factory) FieldLocator.ForClassHierarchy.Factory.INSTANCE);
    }

    public WithImplicitType withField(String str, FieldLocator.Factory factory) {
        return new WithImplicitType.OfField(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, this.assigner, this.typing, str, factory);
    }

    public Implementation.Composable withAssigner(Assigner assigner2, Assigner.Typing typing2) {
        return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, assigner2, typing2);
    }

    public Implementation andThen(Implementation implementation) {
        return new Implementation.Compound(new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider, TerminationHandler.DROPPING, this.assigner, this.typing), implementation);
    }

    public Implementation.Composable andThen(Implementation.Composable composable) {
        return new Implementation.Compound.Composable((Implementation) new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider, TerminationHandler.DROPPING, this.assigner, this.typing), composable);
    }

    public InstrumentedType prepare(InstrumentedType instrumentedType) {
        return this.invocationProvider.prepare(instrumentedType);
    }

    public ByteCodeAppender appender(Implementation.Target target) {
        return new Appender(target.getInstrumentedType());
    }

    protected interface InvocationProvider {
        InvocationProvider appendArgument(ArgumentProvider argumentProvider);

        InvocationProvider appendArguments(List<ArgumentProvider> list);

        Target make(MethodDescription methodDescription);

        InstrumentedType prepare(InstrumentedType instrumentedType);

        InvocationProvider withNameProvider(NameProvider nameProvider);

        InvocationProvider withReturnTypeProvider(ReturnTypeProvider returnTypeProvider);

        InvocationProvider withoutArguments();

        public interface Target {
            Resolved resolve(TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing);

            public interface Resolved {
                String getInternalName();

                List<TypeDescription> getParameterTypes();

                TypeDescription getReturnType();

                StackManipulation getStackManipulation();

                @HashCodeAndEqualsPlugin.Enhance
                public static class Simple implements Resolved {
                    private final String internalName;
                    private final List<TypeDescription> parameterTypes;
                    private final TypeDescription returnType;
                    private final StackManipulation stackManipulation;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Simple simple = (Simple) obj;
                        return this.internalName.equals(simple.internalName) && this.stackManipulation.equals(simple.stackManipulation) && this.returnType.equals(simple.returnType) && this.parameterTypes.equals(simple.parameterTypes);
                    }

                    public int hashCode() {
                        return ((((((527 + this.stackManipulation.hashCode()) * 31) + this.internalName.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode();
                    }

                    public Simple(StackManipulation stackManipulation2, String str, TypeDescription typeDescription, List<TypeDescription> list) {
                        this.stackManipulation = stackManipulation2;
                        this.internalName = str;
                        this.returnType = typeDescription;
                        this.parameterTypes = list;
                    }

                    public StackManipulation getStackManipulation() {
                        return this.stackManipulation;
                    }

                    public TypeDescription getReturnType() {
                        return this.returnType;
                    }

                    public String getInternalName() {
                        return this.internalName;
                    }

                    public List<TypeDescription> getParameterTypes() {
                        return this.parameterTypes;
                    }
                }
            }
        }

        public interface ArgumentProvider {
            InstrumentedType prepare(InstrumentedType instrumentedType);

            Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing);

            public enum ForInterceptedMethodInstanceAndParameters implements ArgumentProvider {
                INSTANCE;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    List list;
                    StackManipulation prependThisReference = MethodVariableAccess.allArgumentsOf(methodDescription).prependThisReference();
                    if (methodDescription.isStatic()) {
                        list = methodDescription.getParameters().asTypeList().asErasures();
                    } else {
                        list = CompoundList.of(methodDescription.getDeclaringType().asErasure(), methodDescription.getParameters().asTypeList().asErasures());
                    }
                    return new Resolved.Simple(prependThisReference, (List<TypeDescription>) list);
                }
            }

            public enum ForInterceptedMethodParameters implements ArgumentProvider {
                INSTANCE;

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple((StackManipulation) MethodVariableAccess.allArgumentsOf(methodDescription), (List<TypeDescription>) methodDescription.getParameters().asTypeList().asErasures());
                }
            }

            public enum ConstantPoolWrapper {
                BOOLEAN(Boolean.TYPE, Boolean.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue(((Boolean) obj).booleanValue()));
                    }
                },
                BYTE(Byte.TYPE, Byte.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue((int) ((Byte) obj).byteValue()));
                    }
                },
                SHORT(Short.TYPE, Short.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue((int) ((Short) obj).shortValue()));
                    }
                },
                CHARACTER(Character.TYPE, Character.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue((int) ((Character) obj).charValue()));
                    }
                },
                INTEGER(Integer.TYPE, Integer.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(IntegerConstant.forValue(((Integer) obj).intValue()));
                    }
                },
                LONG(Long.TYPE, Long.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(LongConstant.forValue(((Long) obj).longValue()));
                    }
                },
                FLOAT(Float.TYPE, Float.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(FloatConstant.forValue(((Float) obj).floatValue()));
                    }
                },
                DOUBLE(Double.TYPE, Double.class) {
                    /* access modifiers changed from: protected */
                    public ArgumentProvider make(Object obj) {
                        return new WrappingArgumentProvider(DoubleConstant.forValue(((Double) obj).doubleValue()));
                    }
                };
                
                /* access modifiers changed from: private */
                public final TypeDescription primitiveType;
                /* access modifiers changed from: private */
                public final TypeDescription wrapperType;

                /* access modifiers changed from: protected */
                public abstract ArgumentProvider make(Object obj);

                private ConstantPoolWrapper(Class<?> cls, Class<?> cls2) {
                    this.primitiveType = TypeDescription.ForLoadedType.of(cls);
                    this.wrapperType = TypeDescription.ForLoadedType.of(cls2);
                }

                public static ArgumentProvider of(Object obj) {
                    if (obj instanceof Boolean) {
                        return BOOLEAN.make(obj);
                    }
                    if (obj instanceof Byte) {
                        return BYTE.make(obj);
                    }
                    if (obj instanceof Short) {
                        return SHORT.make(obj);
                    }
                    if (obj instanceof Character) {
                        return CHARACTER.make(obj);
                    }
                    if (obj instanceof Integer) {
                        return INTEGER.make(obj);
                    }
                    if (obj instanceof Long) {
                        return LONG.make(obj);
                    }
                    if (obj instanceof Float) {
                        return FLOAT.make(obj);
                    }
                    if (obj instanceof Double) {
                        return DOUBLE.make(obj);
                    }
                    if (obj instanceof String) {
                        return new ForStringConstant((String) obj);
                    }
                    if (obj instanceof Class) {
                        return new ForClassConstant(TypeDescription.ForLoadedType.of((Class) obj));
                    }
                    if (obj instanceof Enum) {
                        return new ForEnumerationValue(new EnumerationDescription.ForLoadedEnumeration((Enum) obj));
                    }
                    if (JavaType.METHOD_HANDLE.isInstance(obj)) {
                        return new ForJavaConstant(JavaConstant.MethodHandle.ofLoaded(obj));
                    }
                    if (JavaType.METHOD_TYPE.isInstance(obj)) {
                        return new ForJavaConstant(JavaConstant.MethodType.ofLoaded(obj));
                    }
                    return ForInstance.of(obj);
                }

                @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
                protected class WrappingArgumentProvider implements ArgumentProvider {
                    private final StackManipulation stackManipulation;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        WrappingArgumentProvider wrappingArgumentProvider = (WrappingArgumentProvider) obj;
                        return ConstantPoolWrapper.this.equals(ConstantPoolWrapper.this) && this.stackManipulation.equals(wrappingArgumentProvider.stackManipulation);
                    }

                    public int hashCode() {
                        return ((527 + this.stackManipulation.hashCode()) * 31) + ConstantPoolWrapper.this.hashCode();
                    }

                    public InstrumentedType prepare(InstrumentedType instrumentedType) {
                        return instrumentedType;
                    }

                    protected WrappingArgumentProvider(StackManipulation stackManipulation2) {
                        this.stackManipulation = stackManipulation2;
                    }

                    public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                        return new Resolved.Simple((StackManipulation) new StackManipulation.Compound(this.stackManipulation, assigner.assign(ConstantPoolWrapper.this.primitiveType.asGenericType(), ConstantPoolWrapper.this.wrapperType.asGenericType(), typing)), ConstantPoolWrapper.this.wrapperType);
                    }
                }
            }

            public interface Resolved {
                StackManipulation getLoadInstruction();

                List<TypeDescription> getLoadedTypes();

                @HashCodeAndEqualsPlugin.Enhance
                public static class Simple implements Resolved {
                    private final List<TypeDescription> loadedTypes;
                    private final StackManipulation stackManipulation;

                    public boolean equals(Object obj) {
                        if (this == obj) {
                            return true;
                        }
                        if (obj == null || getClass() != obj.getClass()) {
                            return false;
                        }
                        Simple simple = (Simple) obj;
                        return this.stackManipulation.equals(simple.stackManipulation) && this.loadedTypes.equals(simple.loadedTypes);
                    }

                    public int hashCode() {
                        return ((527 + this.stackManipulation.hashCode()) * 31) + this.loadedTypes.hashCode();
                    }

                    public Simple(StackManipulation stackManipulation2, TypeDescription typeDescription) {
                        this(stackManipulation2, (List<TypeDescription>) Collections.singletonList(typeDescription));
                    }

                    public Simple(StackManipulation stackManipulation2, List<TypeDescription> list) {
                        this.stackManipulation = stackManipulation2;
                        this.loadedTypes = list;
                    }

                    public StackManipulation getLoadInstruction() {
                        return this.stackManipulation;
                    }

                    public List<TypeDescription> getLoadedTypes() {
                        return this.loadedTypes;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForThisInstance implements ArgumentProvider {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForThisInstance) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForThisInstance(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public Resolved resolve(TypeDescription typeDescription2, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    if (methodDescription.isStatic()) {
                        throw new IllegalStateException("Cannot get this instance from static method: " + methodDescription);
                    } else if (typeDescription2.isAssignableTo(this.typeDescription)) {
                        return new Resolved.Simple(MethodVariableAccess.loadThis(), this.typeDescription);
                    } else {
                        throw new IllegalStateException(typeDescription2 + " is not assignable to " + typeDescription2);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForInstance implements ArgumentProvider {
                private static final String FIELD_PREFIX = "invokeDynamic";
                private final TypeDescription fieldType;
                @HashCodeAndEqualsPlugin.ValueHandling(HashCodeAndEqualsPlugin.ValueHandling.Sort.IGNORE)
                private final String name = ("invokeDynamic$" + RandomString.make());
                private final Object value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForInstance forInstance = (ForInstance) obj;
                    return this.value.equals(forInstance.value) && this.fieldType.equals(forInstance.fieldType);
                }

                public int hashCode() {
                    return ((527 + this.value.hashCode()) * 31) + this.fieldType.hashCode();
                }

                protected ForInstance(Object obj, TypeDescription typeDescription) {
                    this.value = obj;
                    this.fieldType = typeDescription;
                }

                protected static ArgumentProvider of(Object obj) {
                    return new ForInstance(obj, TypeDescription.ForLoadedType.of(obj.getClass()));
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    FieldDescription fieldDescription = (FieldDescription) ((FieldList) typeDescription.getDeclaredFields().filter(ElementMatchers.named(this.name))).getOnly();
                    StackManipulation assign = assigner.assign(fieldDescription.getType(), this.fieldType.asGenericType(), typing);
                    if (assign.isValid()) {
                        return new Resolved.Simple((StackManipulation) new StackManipulation.Compound(FieldAccess.forField(fieldDescription).read(), assign), fieldDescription.getType().asErasure());
                    }
                    throw new IllegalStateException("Cannot assign " + fieldDescription + " to " + this.fieldType);
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType.withField(new FieldDescription.Token(this.name, 4169, this.fieldType.asGenericType())).withInitializer((LoadedTypeInitializer) new LoadedTypeInitializer.ForStaticField(this.name, this.value));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForField implements ArgumentProvider {
                protected final FieldLocator.Factory fieldLocatorFactory;
                protected final String fieldName;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForField forField = (ForField) obj;
                    return this.fieldName.equals(forField.fieldName) && this.fieldLocatorFactory.equals(forField.fieldLocatorFactory);
                }

                public int hashCode() {
                    return ((527 + this.fieldName.hashCode()) * 31) + this.fieldLocatorFactory.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForField(String str, FieldLocator.Factory factory) {
                    this.fieldName = str;
                    this.fieldLocatorFactory = factory;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    StackManipulation stackManipulation;
                    FieldLocator.Resolution locate = this.fieldLocatorFactory.make(typeDescription).locate(this.fieldName);
                    if (!locate.isResolved()) {
                        throw new IllegalStateException("Cannot find a field " + this.fieldName + " for " + typeDescription);
                    } else if (locate.getField().isStatic() || !methodDescription.isStatic()) {
                        StackManipulation[] stackManipulationArr = new StackManipulation[2];
                        if (locate.getField().isStatic()) {
                            stackManipulation = StackManipulation.Trivial.INSTANCE;
                        } else {
                            stackManipulation = MethodVariableAccess.loadThis();
                        }
                        stackManipulationArr[0] = stackManipulation;
                        stackManipulationArr[1] = FieldAccess.forField(locate.getField()).read();
                        return doResolve(new StackManipulation.Compound(stackManipulationArr), locate.getField().getType(), assigner, typing);
                    } else {
                        throw new IllegalStateException("Cannot access non-static " + locate.getField() + " from " + methodDescription);
                    }
                }

                /* access modifiers changed from: protected */
                public Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(stackManipulation, generic.asErasure());
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class WithExplicitType extends ForField {
                    private final TypeDescription typeDescription;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((WithExplicitType) obj).typeDescription);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.typeDescription.hashCode();
                    }

                    protected WithExplicitType(String str, FieldLocator.Factory factory, TypeDescription typeDescription2) {
                        super(str, factory);
                        this.typeDescription = typeDescription2;
                    }

                    /* access modifiers changed from: protected */
                    public Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                        StackManipulation assign = assigner.assign(generic, this.typeDescription.asGenericType(), typing);
                        if (assign.isValid()) {
                            return new Resolved.Simple((StackManipulation) new StackManipulation.Compound(stackManipulation, assign), this.typeDescription);
                        }
                        throw new IllegalStateException("Cannot assign " + generic + " to " + this.typeDescription);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForMethodParameter implements ArgumentProvider {
                protected final int index;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.index == ((ForMethodParameter) obj).index;
                }

                public int hashCode() {
                    return 527 + this.index;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForMethodParameter(int i) {
                    this.index = i;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    ParameterList<?> parameters = methodDescription.getParameters();
                    if (this.index < parameters.size()) {
                        return doResolve(MethodVariableAccess.load((ParameterDescription) parameters.get(this.index)), ((ParameterDescription) parameters.get(this.index)).getType(), assigner, typing);
                    }
                    throw new IllegalStateException("No parameter " + this.index + " for " + methodDescription);
                }

                /* access modifiers changed from: protected */
                public Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(stackManipulation, generic.asErasure());
                }

                @HashCodeAndEqualsPlugin.Enhance
                protected static class WithExplicitType extends ForMethodParameter {
                    private final TypeDescription typeDescription;

                    public boolean equals(Object obj) {
                        if (!super.equals(obj)) {
                            return false;
                        }
                        if (this == obj) {
                            return true;
                        }
                        return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((WithExplicitType) obj).typeDescription);
                    }

                    public int hashCode() {
                        return (super.hashCode() * 31) + this.typeDescription.hashCode();
                    }

                    protected WithExplicitType(int i, TypeDescription typeDescription2) {
                        super(i);
                        this.typeDescription = typeDescription2;
                    }

                    /* access modifiers changed from: protected */
                    public Resolved doResolve(StackManipulation stackManipulation, TypeDescription.Generic generic, Assigner assigner, Assigner.Typing typing) {
                        StackManipulation assign = assigner.assign(generic, this.typeDescription.asGenericType(), typing);
                        if (assign.isValid()) {
                            return new Resolved.Simple((StackManipulation) new StackManipulation.Compound(stackManipulation, assign), this.typeDescription);
                        }
                        throw new IllegalStateException("Cannot assign " + generic + " to " + this.typeDescription);
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForBooleanConstant implements ArgumentProvider {
                private final boolean value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForBooleanConstant) obj).value;
                }

                public int hashCode() {
                    return true + (this.value ? 1 : 0);
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForBooleanConstant(boolean z) {
                    this.value = z;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue(this.value), TypeDescription.ForLoadedType.of(Boolean.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForByteConstant implements ArgumentProvider {
                private final byte value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForByteConstant) obj).value;
                }

                public int hashCode() {
                    return 527 + this.value;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForByteConstant(byte b) {
                    this.value = b;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue((int) this.value), TypeDescription.ForLoadedType.of(Byte.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForShortConstant implements ArgumentProvider {
                private final short value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForShortConstant) obj).value;
                }

                public int hashCode() {
                    return 527 + this.value;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForShortConstant(short s) {
                    this.value = s;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue((int) this.value), TypeDescription.ForLoadedType.of(Short.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForCharacterConstant implements ArgumentProvider {
                private final char value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForCharacterConstant) obj).value;
                }

                public int hashCode() {
                    return 527 + this.value;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForCharacterConstant(char c) {
                    this.value = c;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue((int) this.value), TypeDescription.ForLoadedType.of(Character.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForIntegerConstant implements ArgumentProvider {
                private final int value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForIntegerConstant) obj).value;
                }

                public int hashCode() {
                    return 527 + this.value;
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForIntegerConstant(int i) {
                    this.value = i;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(IntegerConstant.forValue(this.value), TypeDescription.ForLoadedType.of(Integer.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForLongConstant implements ArgumentProvider {
                private final long value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForLongConstant) obj).value;
                }

                public int hashCode() {
                    long j = this.value;
                    return 527 + ((int) (j ^ (j >>> 32)));
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForLongConstant(long j) {
                    this.value = j;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(LongConstant.forValue(this.value), TypeDescription.ForLoadedType.of(Long.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForFloatConstant implements ArgumentProvider {
                private final float value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForFloatConstant) obj).value;
                }

                public int hashCode() {
                    return 527 + Float.floatToIntBits(this.value);
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForFloatConstant(float f) {
                    this.value = f;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(FloatConstant.forValue(this.value), TypeDescription.ForLoadedType.of(Float.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForDoubleConstant implements ArgumentProvider {
                private final double value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value == ((ForDoubleConstant) obj).value;
                }

                public int hashCode() {
                    long doubleToLongBits = Double.doubleToLongBits(this.value);
                    return 527 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForDoubleConstant(double d) {
                    this.value = d;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(DoubleConstant.forValue(this.value), TypeDescription.ForLoadedType.of(Double.TYPE));
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForStringConstant implements ArgumentProvider {
                private final String value;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.value.equals(((ForStringConstant) obj).value);
                }

                public int hashCode() {
                    return 527 + this.value.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForStringConstant(String str) {
                    this.value = str;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple((StackManipulation) new TextConstant(this.value), TypeDescription.STRING);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForClassConstant implements ArgumentProvider {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForClassConstant) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForClassConstant(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public Resolved resolve(TypeDescription typeDescription2, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(ClassConstant.of(this.typeDescription), TypeDescription.CLASS);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForEnumerationValue implements ArgumentProvider {
                private final EnumerationDescription enumerationDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.enumerationDescription.equals(((ForEnumerationValue) obj).enumerationDescription);
                }

                public int hashCode() {
                    return 527 + this.enumerationDescription.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForEnumerationValue(EnumerationDescription enumerationDescription2) {
                    this.enumerationDescription = enumerationDescription2;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple(FieldAccess.forEnumeration(this.enumerationDescription), this.enumerationDescription.getEnumerationType());
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForNullValue implements ArgumentProvider {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForNullValue) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForNullValue(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public Resolved resolve(TypeDescription typeDescription2, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple((StackManipulation) NullConstant.INSTANCE, this.typeDescription);
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJavaConstant implements ArgumentProvider {
                private final JavaConstant javaConstant;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.javaConstant.equals(((ForJavaConstant) obj).javaConstant);
                }

                public int hashCode() {
                    return 527 + this.javaConstant.hashCode();
                }

                public InstrumentedType prepare(InstrumentedType instrumentedType) {
                    return instrumentedType;
                }

                protected ForJavaConstant(JavaConstant javaConstant2) {
                    this.javaConstant = javaConstant2;
                }

                public Resolved resolve(TypeDescription typeDescription, MethodDescription methodDescription, Assigner assigner, Assigner.Typing typing) {
                    return new Resolved.Simple((StackManipulation) new JavaConstantValue(this.javaConstant), this.javaConstant.getType());
                }
            }
        }

        public interface NameProvider {
            String resolve(MethodDescription methodDescription);

            public enum ForInterceptedMethod implements NameProvider {
                INSTANCE;

                public String resolve(MethodDescription methodDescription) {
                    return methodDescription.getInternalName();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForExplicitName implements NameProvider {
                private final String internalName;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.internalName.equals(((ForExplicitName) obj).internalName);
                }

                public int hashCode() {
                    return 527 + this.internalName.hashCode();
                }

                protected ForExplicitName(String str) {
                    this.internalName = str;
                }

                public String resolve(MethodDescription methodDescription) {
                    return this.internalName;
                }
            }
        }

        public interface ReturnTypeProvider {
            TypeDescription resolve(MethodDescription methodDescription);

            public enum ForInterceptedMethod implements ReturnTypeProvider {
                INSTANCE;

                public TypeDescription resolve(MethodDescription methodDescription) {
                    return methodDescription.getReturnType().asErasure();
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForExplicitType implements ReturnTypeProvider {
                private final TypeDescription typeDescription;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.typeDescription.equals(((ForExplicitType) obj).typeDescription);
                }

                public int hashCode() {
                    return 527 + this.typeDescription.hashCode();
                }

                protected ForExplicitType(TypeDescription typeDescription2) {
                    this.typeDescription = typeDescription2;
                }

                public TypeDescription resolve(MethodDescription methodDescription) {
                    return this.typeDescription;
                }
            }
        }

        @HashCodeAndEqualsPlugin.Enhance
        public static class Default implements InvocationProvider {
            private final List<ArgumentProvider> argumentProviders;
            private final NameProvider nameProvider;
            private final ReturnTypeProvider returnTypeProvider;

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || getClass() != obj.getClass()) {
                    return false;
                }
                Default defaultR = (Default) obj;
                return this.nameProvider.equals(defaultR.nameProvider) && this.returnTypeProvider.equals(defaultR.returnTypeProvider) && this.argumentProviders.equals(defaultR.argumentProviders);
            }

            public int hashCode() {
                return ((((527 + this.nameProvider.hashCode()) * 31) + this.returnTypeProvider.hashCode()) * 31) + this.argumentProviders.hashCode();
            }

            protected Default() {
                this(NameProvider.ForInterceptedMethod.INSTANCE, ReturnTypeProvider.ForInterceptedMethod.INSTANCE, Collections.singletonList(ArgumentProvider.ForInterceptedMethodInstanceAndParameters.INSTANCE));
            }

            protected Default(NameProvider nameProvider2, ReturnTypeProvider returnTypeProvider2, List<ArgumentProvider> list) {
                this.nameProvider = nameProvider2;
                this.returnTypeProvider = returnTypeProvider2;
                this.argumentProviders = list;
            }

            public Target make(MethodDescription methodDescription) {
                return new Target(this.nameProvider.resolve(methodDescription), this.returnTypeProvider.resolve(methodDescription), this.argumentProviders, methodDescription);
            }

            public InvocationProvider appendArguments(List<ArgumentProvider> list) {
                return new Default(this.nameProvider, this.returnTypeProvider, CompoundList.of(this.argumentProviders, list));
            }

            public InvocationProvider appendArgument(ArgumentProvider argumentProvider) {
                return new Default(this.nameProvider, this.returnTypeProvider, CompoundList.of(this.argumentProviders, argumentProvider));
            }

            public InvocationProvider withoutArguments() {
                return new Default(this.nameProvider, this.returnTypeProvider, Collections.emptyList());
            }

            public InvocationProvider withNameProvider(NameProvider nameProvider2) {
                return new Default(nameProvider2, this.returnTypeProvider, this.argumentProviders);
            }

            public InvocationProvider withReturnTypeProvider(ReturnTypeProvider returnTypeProvider2) {
                return new Default(this.nameProvider, returnTypeProvider2, this.argumentProviders);
            }

            public InstrumentedType prepare(InstrumentedType instrumentedType) {
                for (ArgumentProvider prepare : this.argumentProviders) {
                    instrumentedType = prepare.prepare(instrumentedType);
                }
                return instrumentedType;
            }

            @HashCodeAndEqualsPlugin.Enhance
            protected static class Target implements Target {
                private final List<ArgumentProvider> argumentProviders;
                private final MethodDescription instrumentedMethod;
                private final String internalName;
                private final TypeDescription returnType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    Target target = (Target) obj;
                    return this.internalName.equals(target.internalName) && this.returnType.equals(target.returnType) && this.argumentProviders.equals(target.argumentProviders) && this.instrumentedMethod.equals(target.instrumentedMethod);
                }

                public int hashCode() {
                    return ((((((527 + this.internalName.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.argumentProviders.hashCode()) * 31) + this.instrumentedMethod.hashCode();
                }

                protected Target(String str, TypeDescription typeDescription, List<ArgumentProvider> list, MethodDescription methodDescription) {
                    this.internalName = str;
                    this.returnType = typeDescription;
                    this.argumentProviders = list;
                    this.instrumentedMethod = methodDescription;
                }

                public Target.Resolved resolve(TypeDescription typeDescription, Assigner assigner, Assigner.Typing typing) {
                    StackManipulation[] stackManipulationArr = new StackManipulation[this.argumentProviders.size()];
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    for (ArgumentProvider resolve : this.argumentProviders) {
                        ArgumentProvider.Resolved resolve2 = resolve.resolve(typeDescription, this.instrumentedMethod, assigner, typing);
                        arrayList.addAll(resolve2.getLoadedTypes());
                        stackManipulationArr[i] = resolve2.getLoadInstruction();
                        i++;
                    }
                    return new Target.Resolved.Simple(new StackManipulation.Compound(stackManipulationArr), this.internalName, this.returnType, arrayList);
                }
            }
        }
    }

    protected static abstract class AbstractDelegator extends InvokeDynamic {
        /* access modifiers changed from: protected */
        public abstract InvokeDynamic materialize();

        protected AbstractDelegator(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        public InvokeDynamic withBooleanValue(boolean... zArr) {
            return materialize().withBooleanValue(zArr);
        }

        public InvokeDynamic withByteValue(byte... bArr) {
            return materialize().withByteValue(bArr);
        }

        public InvokeDynamic withShortValue(short... sArr) {
            return materialize().withShortValue(sArr);
        }

        public InvokeDynamic withCharacterValue(char... cArr) {
            return materialize().withCharacterValue(cArr);
        }

        public InvokeDynamic withIntegerValue(int... iArr) {
            return materialize().withIntegerValue(iArr);
        }

        public InvokeDynamic withLongValue(long... jArr) {
            return materialize().withLongValue(jArr);
        }

        public InvokeDynamic withFloatValue(float... fArr) {
            return materialize().withFloatValue(fArr);
        }

        public InvokeDynamic withDoubleValue(double... dArr) {
            return materialize().withDoubleValue(dArr);
        }

        public InvokeDynamic withValue(Object... objArr) {
            return materialize().withValue(objArr);
        }

        public WithImplicitType withReference(Object obj) {
            return materialize().withReference(obj);
        }

        public InvokeDynamic withReference(Object... objArr) {
            return materialize().withReference(objArr);
        }

        public InvokeDynamic withType(TypeDescription... typeDescriptionArr) {
            return materialize().withType(typeDescriptionArr);
        }

        public InvokeDynamic withInstance(JavaConstant... javaConstantArr) {
            return materialize().withInstance(javaConstantArr);
        }

        public InvokeDynamic withNullValue(Class<?>... clsArr) {
            return materialize().withNullValue(clsArr);
        }

        public InvokeDynamic withNullValue(TypeDescription... typeDescriptionArr) {
            return materialize().withNullValue(typeDescriptionArr);
        }

        public InvokeDynamic withArgument(int... iArr) {
            return materialize().withArgument(iArr);
        }

        public WithImplicitType withArgument(int i) {
            return materialize().withArgument(i);
        }

        public InvokeDynamic withThis(Class<?>... clsArr) {
            return materialize().withThis(clsArr);
        }

        public InvokeDynamic withThis(TypeDescription... typeDescriptionArr) {
            return materialize().withThis(typeDescriptionArr);
        }

        public InvokeDynamic withMethodArguments() {
            return materialize().withMethodArguments();
        }

        public InvokeDynamic withImplicitAndMethodArguments() {
            return materialize().withImplicitAndMethodArguments();
        }

        public InvokeDynamic withField(String... strArr) {
            return materialize().withField(strArr);
        }

        public InvokeDynamic withEnumeration(EnumerationDescription... enumerationDescriptionArr) {
            return materialize().withEnumeration(enumerationDescriptionArr);
        }

        public InvokeDynamic withField(FieldLocator.Factory factory, String... strArr) {
            return materialize().withField(factory, strArr);
        }

        public WithImplicitType withField(String str) {
            return materialize().withField(str);
        }

        public WithImplicitType withField(String str, FieldLocator.Factory factory) {
            return materialize().withField(str, factory);
        }

        public Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return materialize().withAssigner(assigner, typing);
        }

        public Implementation andThen(Implementation implementation) {
            return materialize().andThen(implementation);
        }

        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return materialize().prepare(instrumentedType);
        }

        public ByteCodeAppender appender(Implementation.Target target) {
            return materialize().appender(target);
        }
    }

    public static class WithImplicitArguments extends AbstractDelegator {
        public /* bridge */ /* synthetic */ Implementation andThen(Implementation implementation) {
            return super.andThen(implementation);
        }

        public /* bridge */ /* synthetic */ ByteCodeAppender appender(Implementation.Target target) {
            return super.appender(target);
        }

        public /* bridge */ /* synthetic */ InstrumentedType prepare(InstrumentedType instrumentedType) {
            return super.prepare(instrumentedType);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withArgument(int i) {
            return super.withArgument(i);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withArgument(int[] iArr) {
            return super.withArgument(iArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withBooleanValue(boolean[] zArr) {
            return super.withBooleanValue(zArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withByteValue(byte[] bArr) {
            return super.withByteValue(bArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withCharacterValue(char[] cArr) {
            return super.withCharacterValue(cArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withDoubleValue(double[] dArr) {
            return super.withDoubleValue(dArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withEnumeration(EnumerationDescription[] enumerationDescriptionArr) {
            return super.withEnumeration(enumerationDescriptionArr);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withField(String str) {
            return super.withField(str);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withField(String str, FieldLocator.Factory factory) {
            return super.withField(str, factory);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withField(FieldLocator.Factory factory, String[] strArr) {
            return super.withField(factory, strArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withField(String[] strArr) {
            return super.withField(strArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withFloatValue(float[] fArr) {
            return super.withFloatValue(fArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withImplicitAndMethodArguments() {
            return super.withImplicitAndMethodArguments();
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withInstance(JavaConstant[] javaConstantArr) {
            return super.withInstance(javaConstantArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withIntegerValue(int[] iArr) {
            return super.withIntegerValue(iArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withLongValue(long[] jArr) {
            return super.withLongValue(jArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withMethodArguments() {
            return super.withMethodArguments();
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(Class[] clsArr) {
            return super.withNullValue((Class<?>[]) clsArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(TypeDescription[] typeDescriptionArr) {
            return super.withNullValue(typeDescriptionArr);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withReference(Object obj) {
            return super.withReference(obj);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withReference(Object[] objArr) {
            return super.withReference(objArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withShortValue(short[] sArr) {
            return super.withShortValue(sArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withThis(Class[] clsArr) {
            return super.withThis((Class<?>[]) clsArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withThis(TypeDescription[] typeDescriptionArr) {
            return super.withThis(typeDescriptionArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withType(TypeDescription[] typeDescriptionArr) {
            return super.withType(typeDescriptionArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withValue(Object[] objArr) {
            return super.withValue(objArr);
        }

        protected WithImplicitArguments(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        public InvokeDynamic withoutArguments() {
            return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withoutArguments(), this.terminationHandler, this.assigner, this.typing);
        }

        /* access modifiers changed from: protected */
        public InvokeDynamic materialize() {
            return withoutArguments();
        }

        public WithImplicitArguments withAssigner(Assigner assigner, Assigner.Typing typing) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider, this.terminationHandler, assigner, typing);
        }
    }

    public static class WithImplicitTarget extends WithImplicitArguments {
        protected WithImplicitTarget(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        public WithImplicitArguments invoke(Class<?> cls) {
            return invoke(TypeDescription.ForLoadedType.of(cls));
        }

        public WithImplicitArguments invoke(TypeDescription typeDescription) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withReturnTypeProvider(new InvocationProvider.ReturnTypeProvider.ForExplicitType(typeDescription)), this.terminationHandler, this.assigner, this.typing);
        }

        public WithImplicitArguments invoke(String str) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withNameProvider(new InvocationProvider.NameProvider.ForExplicitName(str)), this.terminationHandler, this.assigner, this.typing);
        }

        public WithImplicitArguments invoke(String str, Class<?> cls) {
            return invoke(str, TypeDescription.ForLoadedType.of(cls));
        }

        public WithImplicitArguments invoke(String str, TypeDescription typeDescription) {
            return new WithImplicitArguments(this.bootstrapMethod, this.handleArguments, this.invocationProvider.withNameProvider(new InvocationProvider.NameProvider.ForExplicitName(str)).withReturnTypeProvider(new InvocationProvider.ReturnTypeProvider.ForExplicitType(typeDescription)), this.terminationHandler, this.assigner, this.typing);
        }
    }

    public static abstract class WithImplicitType extends AbstractDelegator {
        public abstract InvokeDynamic as(TypeDescription typeDescription);

        public /* bridge */ /* synthetic */ Implementation andThen(Implementation implementation) {
            return super.andThen(implementation);
        }

        public /* bridge */ /* synthetic */ ByteCodeAppender appender(Implementation.Target target) {
            return super.appender(target);
        }

        public /* bridge */ /* synthetic */ InstrumentedType prepare(InstrumentedType instrumentedType) {
            return super.prepare(instrumentedType);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withArgument(int i) {
            return super.withArgument(i);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withArgument(int[] iArr) {
            return super.withArgument(iArr);
        }

        public /* bridge */ /* synthetic */ Implementation.Composable withAssigner(Assigner assigner, Assigner.Typing typing) {
            return super.withAssigner(assigner, typing);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withBooleanValue(boolean[] zArr) {
            return super.withBooleanValue(zArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withByteValue(byte[] bArr) {
            return super.withByteValue(bArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withCharacterValue(char[] cArr) {
            return super.withCharacterValue(cArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withDoubleValue(double[] dArr) {
            return super.withDoubleValue(dArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withEnumeration(EnumerationDescription[] enumerationDescriptionArr) {
            return super.withEnumeration(enumerationDescriptionArr);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withField(String str) {
            return super.withField(str);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withField(String str, FieldLocator.Factory factory) {
            return super.withField(str, factory);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withField(FieldLocator.Factory factory, String[] strArr) {
            return super.withField(factory, strArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withField(String[] strArr) {
            return super.withField(strArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withFloatValue(float[] fArr) {
            return super.withFloatValue(fArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withImplicitAndMethodArguments() {
            return super.withImplicitAndMethodArguments();
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withInstance(JavaConstant[] javaConstantArr) {
            return super.withInstance(javaConstantArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withIntegerValue(int[] iArr) {
            return super.withIntegerValue(iArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withLongValue(long[] jArr) {
            return super.withLongValue(jArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withMethodArguments() {
            return super.withMethodArguments();
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(Class[] clsArr) {
            return super.withNullValue((Class<?>[]) clsArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withNullValue(TypeDescription[] typeDescriptionArr) {
            return super.withNullValue(typeDescriptionArr);
        }

        public /* bridge */ /* synthetic */ WithImplicitType withReference(Object obj) {
            return super.withReference(obj);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withReference(Object[] objArr) {
            return super.withReference(objArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withShortValue(short[] sArr) {
            return super.withShortValue(sArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withThis(Class[] clsArr) {
            return super.withThis((Class<?>[]) clsArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withThis(TypeDescription[] typeDescriptionArr) {
            return super.withThis(typeDescriptionArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withType(TypeDescription[] typeDescriptionArr) {
            return super.withType(typeDescriptionArr);
        }

        public /* bridge */ /* synthetic */ InvokeDynamic withValue(Object[] objArr) {
            return super.withValue(objArr);
        }

        protected WithImplicitType(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing) {
            super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
        }

        public InvokeDynamic as(Class<?> cls) {
            return as(TypeDescription.ForLoadedType.of(cls));
        }

        protected static class OfInstance extends WithImplicitType {
            private final InvocationProvider.ArgumentProvider argumentProvider;
            private final Object value;

            protected OfInstance(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing, Object obj) {
                super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
                this.value = obj;
                this.argumentProvider = InvocationProvider.ArgumentProvider.ForInstance.of(obj);
            }

            public InvokeDynamic as(TypeDescription typeDescription) {
                if (typeDescription.asBoxed().isInstance(this.value)) {
                    return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForInstance(this.value, typeDescription)), this.terminationHandler, this.assigner, this.typing);
                }
                throw new IllegalArgumentException(this.value + " is not of type " + typeDescription);
            }

            /* access modifiers changed from: protected */
            public InvokeDynamic materialize() {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(this.argumentProvider), this.terminationHandler, this.assigner, this.typing);
            }
        }

        protected static class OfArgument extends WithImplicitType {
            private final int index;

            protected OfArgument(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing, int i) {
                super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
                this.index = i;
            }

            public InvokeDynamic as(TypeDescription typeDescription) {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForMethodParameter.WithExplicitType(this.index, typeDescription)), this.terminationHandler, this.assigner, this.typing);
            }

            /* access modifiers changed from: protected */
            public InvokeDynamic materialize() {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForMethodParameter(this.index)), this.terminationHandler, this.assigner, this.typing);
            }
        }

        protected static class OfField extends WithImplicitType {
            private final FieldLocator.Factory fieldLocatorFactory;
            private final String fieldName;

            protected OfField(MethodDescription.InDefinedShape inDefinedShape, List<?> list, InvocationProvider invocationProvider, TerminationHandler terminationHandler, Assigner assigner, Assigner.Typing typing, String str, FieldLocator.Factory factory) {
                super(inDefinedShape, list, invocationProvider, terminationHandler, assigner, typing);
                this.fieldName = str;
                this.fieldLocatorFactory = factory;
            }

            public InvokeDynamic as(TypeDescription typeDescription) {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForField.WithExplicitType(this.fieldName, this.fieldLocatorFactory, typeDescription)), this.terminationHandler, this.assigner, this.typing);
            }

            /* access modifiers changed from: protected */
            public InvokeDynamic materialize() {
                return new InvokeDynamic(this.bootstrapMethod, this.handleArguments, this.invocationProvider.appendArgument(new InvocationProvider.ArgumentProvider.ForField(this.fieldName, this.fieldLocatorFactory)), this.terminationHandler, this.assigner, this.typing);
            }
        }
    }

    @HashCodeAndEqualsPlugin.Enhance(includeSyntheticFields = true)
    protected class Appender implements ByteCodeAppender {
        private final TypeDescription instrumentedType;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Appender appender = (Appender) obj;
            return this.instrumentedType.equals(appender.instrumentedType) && InvokeDynamic.this.equals(InvokeDynamic.this);
        }

        public int hashCode() {
            return ((527 + this.instrumentedType.hashCode()) * 31) + InvokeDynamic.this.hashCode();
        }

        public Appender(TypeDescription typeDescription) {
            this.instrumentedType = typeDescription;
        }

        public ByteCodeAppender.Size apply(MethodVisitor methodVisitor, Implementation.Context context, MethodDescription methodDescription) {
            InvocationProvider.Target.Resolved resolve = InvokeDynamic.this.invocationProvider.make(methodDescription).resolve(this.instrumentedType, InvokeDynamic.this.assigner, InvokeDynamic.this.typing);
            return new ByteCodeAppender.Size(new StackManipulation.Compound(resolve.getStackManipulation(), MethodInvocation.invoke(InvokeDynamic.this.bootstrapMethod).dynamic(resolve.getInternalName(), resolve.getReturnType(), resolve.getParameterTypes(), InvokeDynamic.this.handleArguments), InvokeDynamic.this.terminationHandler.resolve(methodDescription, resolve.getReturnType(), InvokeDynamic.this.assigner, InvokeDynamic.this.typing)).apply(methodVisitor, context).getMaximalSize(), methodDescription.getStackSize());
        }
    }
}

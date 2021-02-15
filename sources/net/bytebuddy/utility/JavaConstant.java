package net.bytebuddy.utility;

import com.dd.plist.ASCIIPropertyListParser;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.enumeration.EnumerationDescription;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.jar.asm.ConstantDynamic;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Type;

public interface JavaConstant {
    Object asConstantPoolValue();

    TypeDescription getType();

    public static class MethodType implements JavaConstant {
        private static final Dispatcher DISPATCHER = ((Dispatcher) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        protected MethodType(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            this.returnType = typeDescription;
            this.parameterTypes = list;
        }

        public static MethodType ofLoaded(Object obj) {
            if (JavaType.METHOD_TYPE.isInstance(obj)) {
                return of(DISPATCHER.returnType(obj), (Class<?>[]) DISPATCHER.parameterArray(obj));
            }
            throw new IllegalArgumentException("Expected method type object: " + obj);
        }

        public static MethodType of(Class<?> cls, Class<?>... clsArr) {
            return of(TypeDescription.ForLoadedType.of(cls), (List<? extends TypeDescription>) new TypeList.ForLoadedTypes(clsArr));
        }

        public static MethodType of(TypeDescription typeDescription, List<? extends TypeDescription> list) {
            return new MethodType(typeDescription, list);
        }

        public static MethodType of(Method method) {
            return of((MethodDescription) new MethodDescription.ForLoadedMethod(method));
        }

        public static MethodType of(Constructor<?> constructor) {
            return of((MethodDescription) new MethodDescription.ForLoadedConstructor(constructor));
        }

        public static MethodType of(MethodDescription methodDescription) {
            return new MethodType(methodDescription.getReturnType().asErasure(), methodDescription.getParameters().asTypeList().asErasures());
        }

        public static MethodType ofSetter(Field field) {
            return ofSetter((FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public static MethodType ofSetter(FieldDescription fieldDescription) {
            return new MethodType(TypeDescription.VOID, Collections.singletonList(fieldDescription.getType().asErasure()));
        }

        public static MethodType ofGetter(Field field) {
            return ofGetter((FieldDescription) new FieldDescription.ForLoadedField(field));
        }

        public static MethodType ofGetter(FieldDescription fieldDescription) {
            return new MethodType(fieldDescription.getType().asErasure(), Collections.emptyList());
        }

        public static MethodType ofConstant(Object obj) {
            return ofConstant(obj.getClass());
        }

        public static MethodType ofConstant(Class<?> cls) {
            return ofConstant(TypeDescription.ForLoadedType.of(cls));
        }

        public static MethodType ofConstant(TypeDescription typeDescription) {
            return new MethodType(typeDescription, Collections.emptyList());
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public TypeList getParameterTypes() {
            return new TypeList.Explicit(this.parameterTypes);
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder("(");
            for (TypeDescription descriptor : this.parameterTypes) {
                sb.append(descriptor.getDescriptor());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            sb.append(this.returnType.getDescriptor());
            return sb.toString();
        }

        public Object asConstantPoolValue() {
            StringBuilder sb = new StringBuilder();
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            for (TypeDescription descriptor : getParameterTypes()) {
                sb.append(descriptor.getDescriptor());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            sb.append(getReturnType().getDescriptor());
            return Type.getMethodType(sb.toString());
        }

        public TypeDescription getType() {
            return JavaType.METHOD_TYPE.getTypeStub();
        }

        public int hashCode() {
            return (this.returnType.hashCode() * 31) + this.parameterTypes.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodType)) {
                return false;
            }
            MethodType methodType = (MethodType) obj;
            if (!this.parameterTypes.equals(methodType.parameterTypes) || !this.returnType.equals(methodType.returnType)) {
                return false;
            }
            return true;
        }

        protected interface Dispatcher {
            Class<?>[] parameterArray(Object obj);

            Class<?> returnType(Object obj);

            public enum CreationAction implements PrivilegedAction<Dispatcher> {
                INSTANCE;

                public Dispatcher run() {
                    try {
                        Class<?> load = JavaType.METHOD_TYPE.load();
                        return new ForJava7CapableVm(load.getMethod("returnType", new Class[0]), load.getMethod("parameterArray", new Class[0]));
                    } catch (Exception unused) {
                        return ForLegacyVm.INSTANCE;
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava7CapableVm implements Dispatcher {
                private static final Object[] NO_ARGUMENTS = new Object[0];
                private final Method parameterArray;
                private final Method returnType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    ForJava7CapableVm forJava7CapableVm = (ForJava7CapableVm) obj;
                    return this.returnType.equals(forJava7CapableVm.returnType) && this.parameterArray.equals(forJava7CapableVm.parameterArray);
                }

                public int hashCode() {
                    return ((527 + this.returnType.hashCode()) * 31) + this.parameterArray.hashCode();
                }

                protected ForJava7CapableVm(Method method, Method method2) {
                    this.returnType = method;
                    this.parameterArray = method2;
                }

                public Class<?> returnType(Object obj) {
                    try {
                        return (Class) this.returnType.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodType#returnType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodType#returnType", e2.getCause());
                    }
                }

                public Class<?>[] parameterArray(Object obj) {
                    try {
                        return (Class[]) this.parameterArray.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodType#parameterArray", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodType#parameterArray", e2.getCause());
                    }
                }
            }

            public enum ForLegacyVm implements Dispatcher {
                INSTANCE;

                public Class<?> returnType(Object obj) {
                    throw new UnsupportedOperationException("Unsupported type for the current JVM: java.lang.invoke.MethodType");
                }

                public Class<?>[] parameterArray(Object obj) {
                    throw new UnsupportedOperationException("Unsupported type for the current JVM: java.lang.invoke.MethodType");
                }
            }
        }
    }

    public static class MethodHandle implements JavaConstant {
        private static final Dispatcher.Initializable DISPATCHER = ((Dispatcher.Initializable) AccessController.doPrivileged(Dispatcher.CreationAction.INSTANCE));
        private final HandleType handleType;
        private final String name;
        private final TypeDescription ownerType;
        private final List<? extends TypeDescription> parameterTypes;
        private final TypeDescription returnType;

        protected MethodHandle(HandleType handleType2, TypeDescription typeDescription, String str, TypeDescription typeDescription2, List<? extends TypeDescription> list) {
            this.handleType = handleType2;
            this.ownerType = typeDescription;
            this.name = str;
            this.returnType = typeDescription2;
            this.parameterTypes = list;
        }

        public static MethodHandle ofLoaded(Object obj) {
            return ofLoaded(obj, DISPATCHER.publicLookup());
        }

        public static MethodHandle ofLoaded(Object obj, Object obj2) {
            if (!JavaType.METHOD_HANDLE.isInstance(obj)) {
                throw new IllegalArgumentException("Expected method handle object: " + obj);
            } else if (JavaType.METHOD_HANDLES_LOOKUP.isInstance(obj2)) {
                Dispatcher initialize = DISPATCHER.initialize();
                Object reveal = initialize.reveal(obj2, obj);
                Object methodType = initialize.getMethodType(reveal);
                return new MethodHandle(HandleType.of(initialize.getReferenceKind(reveal)), TypeDescription.ForLoadedType.of(initialize.getDeclaringClass(reveal)), initialize.getName(reveal), TypeDescription.ForLoadedType.of(initialize.returnType(methodType)), new TypeList.ForLoadedTypes(initialize.parameterArray(methodType)));
            } else {
                throw new IllegalArgumentException("Expected method handle lookup object: " + obj2);
            }
        }

        public static MethodHandle of(Method method) {
            return of((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method));
        }

        public static MethodHandle of(Constructor<?> constructor) {
            return of((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(constructor));
        }

        public static MethodHandle of(MethodDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.of(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), inDefinedShape.getReturnType().asErasure(), inDefinedShape.getParameters().asTypeList().asErasures());
        }

        public static MethodHandle ofSpecial(Method method, Class<?> cls) {
            return ofSpecial((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method), TypeDescription.ForLoadedType.of(cls));
        }

        public static MethodHandle ofSpecial(MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription) {
            if (inDefinedShape.isSpecializableFor(typeDescription)) {
                return new MethodHandle(HandleType.ofSpecial(inDefinedShape), typeDescription, inDefinedShape.getInternalName(), inDefinedShape.getReturnType().asErasure(), inDefinedShape.getParameters().asTypeList().asErasures());
            }
            throw new IllegalArgumentException("Cannot specialize " + inDefinedShape + " for " + typeDescription);
        }

        public static MethodHandle ofGetter(Field field) {
            return ofGetter((FieldDescription.InDefinedShape) new FieldDescription.ForLoadedField(field));
        }

        public static MethodHandle ofGetter(FieldDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.ofGetter(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), inDefinedShape.getType().asErasure(), Collections.emptyList());
        }

        public static MethodHandle ofSetter(Field field) {
            return ofSetter((FieldDescription.InDefinedShape) new FieldDescription.ForLoadedField(field));
        }

        public static MethodHandle ofSetter(FieldDescription.InDefinedShape inDefinedShape) {
            return new MethodHandle(HandleType.ofSetter(inDefinedShape), inDefinedShape.getDeclaringType().asErasure(), inDefinedShape.getInternalName(), TypeDescription.VOID, Collections.singletonList(inDefinedShape.getType().asErasure()));
        }

        public Object asConstantPoolValue() {
            StringBuilder sb = new StringBuilder();
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            for (TypeDescription descriptor : getParameterTypes()) {
                sb.append(descriptor.getDescriptor());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            sb.append(getReturnType().getDescriptor());
            return new Handle(getHandleType().getIdentifier(), getOwnerType().getInternalName(), getName(), sb.toString(), getOwnerType().isInterface());
        }

        public TypeDescription getType() {
            return JavaType.METHOD_HANDLE.getTypeStub();
        }

        public HandleType getHandleType() {
            return this.handleType;
        }

        public TypeDescription getOwnerType() {
            return this.ownerType;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getReturnType() {
            return this.returnType;
        }

        public TypeList getParameterTypes() {
            return new TypeList.Explicit(this.parameterTypes);
        }

        public String getDescriptor() {
            StringBuilder sb = new StringBuilder();
            sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
            for (TypeDescription descriptor : this.parameterTypes) {
                sb.append(descriptor.getDescriptor());
            }
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            sb.append(this.returnType.getDescriptor());
            return sb.toString();
        }

        public int hashCode() {
            return (((((((this.handleType.hashCode() * 31) + this.ownerType.hashCode()) * 31) + this.name.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterTypes.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodHandle)) {
                return false;
            }
            MethodHandle methodHandle = (MethodHandle) obj;
            if (this.handleType != methodHandle.handleType || !this.name.equals(methodHandle.name) || !this.ownerType.equals(methodHandle.ownerType) || !this.parameterTypes.equals(methodHandle.parameterTypes) || !this.returnType.equals(methodHandle.returnType)) {
                return false;
            }
            return true;
        }

        public static Class<?> lookupType(Object obj) {
            return DISPATCHER.lookupType(obj);
        }

        protected interface Dispatcher {

            public interface Initializable {
                Dispatcher initialize();

                Class<?> lookupType(Object obj);

                Object publicLookup();
            }

            Class<?> getDeclaringClass(Object obj);

            Object getMethodType(Object obj);

            String getName(Object obj);

            int getReferenceKind(Object obj);

            List<? extends Class<?>> parameterArray(Object obj);

            Class<?> returnType(Object obj);

            Object reveal(Object obj, Object obj2);

            public enum CreationAction implements PrivilegedAction<Initializable> {
                INSTANCE;

                /* JADX WARNING: Code restructure failed: missing block: B:11:0x0104, code lost:
                    return new net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.ForJava7CapableVm(java.lang.Class.forName("java.lang.invoke.MethodHandles").getMethod("publicLookup", new java.lang.Class[0]), java.lang.Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getName", new java.lang.Class[0]), java.lang.Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getDeclaringClass", new java.lang.Class[0]), java.lang.Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getReferenceKind", new java.lang.Class[0]), java.lang.Class.forName("java.lang.invoke.MethodHandleInfo").getMethod("getMethodType", new java.lang.Class[0]), net.bytebuddy.utility.JavaType.METHOD_TYPE.load().getMethod("returnType", new java.lang.Class[0]), net.bytebuddy.utility.JavaType.METHOD_TYPE.load().getMethod("parameterArray", new java.lang.Class[0]), net.bytebuddy.utility.JavaType.METHOD_HANDLES_LOOKUP.load().getMethod(r23, new java.lang.Class[0]), java.lang.Class.forName("java.lang.invoke.MethodHandleInfo").getConstructor(new java.lang.Class[]{net.bytebuddy.utility.JavaType.METHOD_HANDLE.load()}));
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:14:0x0107, code lost:
                    return net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.ForLegacyVm.INSTANCE;
                 */
                /* JADX WARNING: Failed to process nested try/catch */
                /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0090 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.Initializable run() {
                    /*
                        r34 = this;
                        java.lang.String r0 = "lookupClass"
                        java.lang.String r1 = "parameterArray"
                        java.lang.String r2 = "returnType"
                        java.lang.String r3 = "getMethodType"
                        java.lang.String r4 = "getReferenceKind"
                        java.lang.String r5 = "getDeclaringClass"
                        java.lang.String r6 = "getName"
                        java.lang.String r7 = "publicLookup"
                        java.lang.String r8 = "java.lang.invoke.MethodHandles"
                        java.lang.String r9 = "java.lang.invoke.MethodHandleInfo"
                        r11 = 0
                        net.bytebuddy.utility.JavaConstant$MethodHandle$Dispatcher$ForJava8CapableVm r22 = new net.bytebuddy.utility.JavaConstant$MethodHandle$Dispatcher$ForJava8CapableVm     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r12 = java.lang.Class.forName(r8)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r13 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r13 = r12.getMethod(r7, r13)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r12 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r14 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r14 = r12.getMethod(r6, r14)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r12 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r15 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r15 = r12.getMethod(r5, r15)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r12 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r10 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r16 = r12.getMethod(r4, r10)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r10 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r12 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r17 = r10.getMethod(r3, r12)     // Catch:{ Exception -> 0x008e }
                        net.bytebuddy.utility.JavaType r10 = net.bytebuddy.utility.JavaType.METHOD_TYPE     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r10 = r10.load()     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r12 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r18 = r10.getMethod(r2, r12)     // Catch:{ Exception -> 0x008e }
                        net.bytebuddy.utility.JavaType r10 = net.bytebuddy.utility.JavaType.METHOD_TYPE     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r10 = r10.load()     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r12 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r19 = r10.getMethod(r1, r12)     // Catch:{ Exception -> 0x008e }
                        net.bytebuddy.utility.JavaType r10 = net.bytebuddy.utility.JavaType.METHOD_HANDLES_LOOKUP     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r10 = r10.load()     // Catch:{ Exception -> 0x008e }
                        java.lang.Class[] r12 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x008e }
                        java.lang.reflect.Method r20 = r10.getMethod(r0, r12)     // Catch:{ Exception -> 0x008e }
                        net.bytebuddy.utility.JavaType r10 = net.bytebuddy.utility.JavaType.METHOD_HANDLES_LOOKUP     // Catch:{ Exception -> 0x008e }
                        java.lang.Class r10 = r10.load()     // Catch:{ Exception -> 0x008e }
                        java.lang.String r12 = "revealDirect"
                        r23 = r0
                        r11 = 1
                        java.lang.Class[] r0 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x0090 }
                        net.bytebuddy.utility.JavaType r11 = net.bytebuddy.utility.JavaType.METHOD_HANDLE     // Catch:{ Exception -> 0x0090 }
                        java.lang.Class r11 = r11.load()     // Catch:{ Exception -> 0x0090 }
                        r21 = 0
                        r0[r21] = r11     // Catch:{ Exception -> 0x0090 }
                        java.lang.reflect.Method r21 = r10.getMethod(r12, r0)     // Catch:{ Exception -> 0x0090 }
                        r12 = r22
                        r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ Exception -> 0x0090 }
                        return r22
                    L_0x008e:
                        r23 = r0
                    L_0x0090:
                        net.bytebuddy.utility.JavaConstant$MethodHandle$Dispatcher$ForJava7CapableVm r0 = new net.bytebuddy.utility.JavaConstant$MethodHandle$Dispatcher$ForJava7CapableVm     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ Exception -> 0x0105 }
                        r10 = 0
                        java.lang.Class[] r11 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Method r25 = r8.getMethod(r7, r11)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r7 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class[] r8 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Method r26 = r7.getMethod(r6, r8)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r6 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class[] r7 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Method r27 = r6.getMethod(r5, r7)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r5 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class[] r6 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Method r28 = r5.getMethod(r4, r6)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r4 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class[] r5 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Method r29 = r4.getMethod(r3, r5)     // Catch:{ Exception -> 0x0105 }
                        net.bytebuddy.utility.JavaType r3 = net.bytebuddy.utility.JavaType.METHOD_TYPE     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r3 = r3.load()     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class[] r4 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Method r30 = r3.getMethod(r2, r4)     // Catch:{ Exception -> 0x0105 }
                        net.bytebuddy.utility.JavaType r2 = net.bytebuddy.utility.JavaType.METHOD_TYPE     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r2 = r2.load()     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class[] r3 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Method r31 = r2.getMethod(r1, r3)     // Catch:{ Exception -> 0x0105 }
                        net.bytebuddy.utility.JavaType r1 = net.bytebuddy.utility.JavaType.METHOD_HANDLES_LOOKUP     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r1 = r1.load()     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class[] r2 = new java.lang.Class[r10]     // Catch:{ Exception -> 0x0105 }
                        r3 = r23
                        java.lang.reflect.Method r32 = r1.getMethod(r3, r2)     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r1 = java.lang.Class.forName(r9)     // Catch:{ Exception -> 0x0105 }
                        r2 = 1
                        java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0105 }
                        net.bytebuddy.utility.JavaType r3 = net.bytebuddy.utility.JavaType.METHOD_HANDLE     // Catch:{ Exception -> 0x0105 }
                        java.lang.Class r3 = r3.load()     // Catch:{ Exception -> 0x0105 }
                        r4 = 0
                        r2[r4] = r3     // Catch:{ Exception -> 0x0105 }
                        java.lang.reflect.Constructor r33 = r1.getConstructor(r2)     // Catch:{ Exception -> 0x0105 }
                        r24 = r0
                        r24.<init>(r25, r26, r27, r28, r29, r30, r31, r32, r33)     // Catch:{ Exception -> 0x0105 }
                        return r0
                    L_0x0105:
                        net.bytebuddy.utility.JavaConstant$MethodHandle$Dispatcher$ForLegacyVm r0 = net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.ForLegacyVm.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.utility.JavaConstant.MethodHandle.Dispatcher.CreationAction.run():net.bytebuddy.utility.JavaConstant$MethodHandle$Dispatcher$Initializable");
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static abstract class AbstractBase implements Dispatcher, Initializable {
                private static final Object[] NO_ARGUMENTS = new Object[0];
                protected final Method getDeclaringClass;
                protected final Method getMethodType;
                protected final Method getName;
                protected final Method getReferenceKind;
                protected final Method lookupClass;
                protected final Method parameterArray;
                protected final Method publicLookup;
                protected final Method returnType;

                public boolean equals(Object obj) {
                    if (this == obj) {
                        return true;
                    }
                    if (obj == null || getClass() != obj.getClass()) {
                        return false;
                    }
                    AbstractBase abstractBase = (AbstractBase) obj;
                    return this.publicLookup.equals(abstractBase.publicLookup) && this.getName.equals(abstractBase.getName) && this.getDeclaringClass.equals(abstractBase.getDeclaringClass) && this.getReferenceKind.equals(abstractBase.getReferenceKind) && this.getMethodType.equals(abstractBase.getMethodType) && this.returnType.equals(abstractBase.returnType) && this.parameterArray.equals(abstractBase.parameterArray) && this.lookupClass.equals(abstractBase.lookupClass);
                }

                public int hashCode() {
                    return ((((((((((((((527 + this.publicLookup.hashCode()) * 31) + this.getName.hashCode()) * 31) + this.getDeclaringClass.hashCode()) * 31) + this.getReferenceKind.hashCode()) * 31) + this.getMethodType.hashCode()) * 31) + this.returnType.hashCode()) * 31) + this.parameterArray.hashCode()) * 31) + this.lookupClass.hashCode();
                }

                protected AbstractBase(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8) {
                    this.publicLookup = method;
                    this.getName = method2;
                    this.getDeclaringClass = method3;
                    this.getReferenceKind = method4;
                    this.getMethodType = method5;
                    this.returnType = method6;
                    this.parameterArray = method7;
                    this.lookupClass = method8;
                }

                public Object publicLookup() {
                    try {
                        return this.publicLookup.invoke((Object) null, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles#publicLookup", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles#publicLookup", e2.getCause());
                    }
                }

                public Object getMethodType(Object obj) {
                    try {
                        return this.getMethodType.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getMethodType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getMethodType", e2.getCause());
                    }
                }

                public int getReferenceKind(Object obj) {
                    try {
                        return ((Integer) this.getReferenceKind.invoke(obj, NO_ARGUMENTS)).intValue();
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getReferenceKind", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getReferenceKind", e2.getCause());
                    }
                }

                public Class<?> getDeclaringClass(Object obj) {
                    try {
                        return (Class) this.getDeclaringClass.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getDeclaringClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getDeclaringClass", e2.getCause());
                    }
                }

                public String getName(Object obj) {
                    try {
                        return (String) this.getName.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandleInfo#getName", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandleInfo#getName", e2.getCause());
                    }
                }

                public Class<?> returnType(Object obj) {
                    try {
                        return (Class) this.returnType.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodType#returnType", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.MethodType#returnType", e2.getCause());
                    }
                }

                public List<? extends Class<?>> parameterArray(Object obj) {
                    try {
                        return Arrays.asList((Class[]) this.parameterArray.invoke(obj, NO_ARGUMENTS));
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.MethodType#parameterArray", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.MethodType#parameterArray", e2.getCause());
                    }
                }

                public Class<?> lookupType(Object obj) {
                    try {
                        return (Class) this.lookupClass.invoke(obj, NO_ARGUMENTS);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.reflect.MethodHandles.Lookup#lookupClass", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.reflect.MethodHandles.Lookup#lookupClass", e2.getCause());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava8CapableVm extends AbstractBase {
                private final Method revealDirect;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.revealDirect.equals(((ForJava8CapableVm) obj).revealDirect);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.revealDirect.hashCode();
                }

                public Dispatcher initialize() {
                    return this;
                }

                protected ForJava8CapableVm(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8, Method method9) {
                    super(method, method2, method3, method4, method5, method6, method7, method8);
                    this.revealDirect = method9;
                }

                public Object reveal(Object obj, Object obj2) {
                    try {
                        return this.revealDirect.invoke(obj, new Object[]{obj2});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodHandles.Lookup#revealDirect", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodHandles.Lookup#revealDirect", e2.getCause());
                    }
                }
            }

            @HashCodeAndEqualsPlugin.Enhance
            public static class ForJava7CapableVm extends AbstractBase implements PrivilegedAction<Dispatcher> {
                private final Constructor<?> methodInfo;

                public boolean equals(Object obj) {
                    if (!super.equals(obj)) {
                        return false;
                    }
                    if (this == obj) {
                        return true;
                    }
                    return obj != null && getClass() == obj.getClass() && this.methodInfo.equals(((ForJava7CapableVm) obj).methodInfo);
                }

                public int hashCode() {
                    return (super.hashCode() * 31) + this.methodInfo.hashCode();
                }

                protected ForJava7CapableVm(Method method, Method method2, Method method3, Method method4, Method method5, Method method6, Method method7, Method method8, Constructor<?> constructor) {
                    super(method, method2, method3, method4, method5, method6, method7, method8);
                    this.methodInfo = constructor;
                }

                public Dispatcher initialize() {
                    return (Dispatcher) AccessController.doPrivileged(this);
                }

                public Dispatcher run() {
                    this.methodInfo.setAccessible(true);
                    this.getName.setAccessible(true);
                    this.getDeclaringClass.setAccessible(true);
                    this.getReferenceKind.setAccessible(true);
                    this.getMethodType.setAccessible(true);
                    return this;
                }

                public Object reveal(Object obj, Object obj2) {
                    try {
                        return this.methodInfo.newInstance(new Object[]{obj2});
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Cannot access java.lang.invoke.MethodInfo()", e);
                    } catch (InvocationTargetException e2) {
                        throw new IllegalStateException("Error invoking java.lang.invoke.MethodInfo()", e2.getCause());
                    } catch (InstantiationException e3) {
                        throw new IllegalStateException("Error constructing java.lang.invoke.MethodInfo", e3);
                    }
                }
            }

            public enum ForLegacyVm implements Initializable {
                INSTANCE;

                public Dispatcher initialize() {
                    throw new UnsupportedOperationException("Unsupported type on current JVM: java.lang.invoke.MethodHandle");
                }

                public Object publicLookup() {
                    throw new UnsupportedOperationException("Unsupported type on current JVM: java.lang.invoke.MethodHandle");
                }

                public Class<?> lookupType(Object obj) {
                    throw new UnsupportedOperationException("Unsupported type on current JVM: java.lang.invoke.MethodHandle");
                }
            }
        }

        public enum HandleType {
            INVOKE_VIRTUAL(5),
            INVOKE_STATIC(6),
            INVOKE_SPECIAL(7),
            INVOKE_INTERFACE(9),
            INVOKE_SPECIAL_CONSTRUCTOR(8),
            PUT_FIELD(3),
            GET_FIELD(1),
            PUT_STATIC_FIELD(4),
            GET_STATIC_FIELD(2);
            
            private final int identifier;

            private HandleType(int i) {
                this.identifier = i;
            }

            protected static HandleType of(MethodDescription.InDefinedShape inDefinedShape) {
                if (inDefinedShape.isStatic()) {
                    return INVOKE_STATIC;
                }
                if (inDefinedShape.isPrivate()) {
                    return INVOKE_SPECIAL;
                }
                if (inDefinedShape.isConstructor()) {
                    return INVOKE_SPECIAL_CONSTRUCTOR;
                }
                if (inDefinedShape.getDeclaringType().isInterface()) {
                    return INVOKE_INTERFACE;
                }
                return INVOKE_VIRTUAL;
            }

            protected static HandleType of(int i) {
                for (HandleType handleType : values()) {
                    if (handleType.getIdentifier() == i) {
                        return handleType;
                    }
                }
                throw new IllegalArgumentException("Unknown handle type: " + i);
            }

            protected static HandleType ofSpecial(MethodDescription.InDefinedShape inDefinedShape) {
                if (!inDefinedShape.isStatic() && !inDefinedShape.isAbstract()) {
                    return inDefinedShape.isConstructor() ? INVOKE_SPECIAL_CONSTRUCTOR : INVOKE_SPECIAL;
                }
                throw new IllegalArgumentException("Cannot invoke " + inDefinedShape + " via invokespecial");
            }

            protected static HandleType ofGetter(FieldDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape.isStatic() ? GET_STATIC_FIELD : GET_FIELD;
            }

            protected static HandleType ofSetter(FieldDescription.InDefinedShape inDefinedShape) {
                return inDefinedShape.isStatic() ? PUT_STATIC_FIELD : PUT_FIELD;
            }

            public int getIdentifier() {
                return this.identifier;
            }
        }
    }

    public static class Dynamic implements JavaConstant {
        private static final String CONSTANT_BOOTSTRAPS = "java/lang/invoke/ConstantBootstraps";
        private final TypeDescription typeDescription;
        private final ConstantDynamic value;

        protected Dynamic(ConstantDynamic constantDynamic, TypeDescription typeDescription2) {
            this.value = constantDynamic;
            this.typeDescription = typeDescription2;
        }

        public static Dynamic ofNullConstant() {
            return new Dynamic(new ConstantDynamic("nullConstant", TypeDescription.OBJECT.getDescriptor(), new Handle(6, CONSTANT_BOOTSTRAPS, "nullConstant", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", false), new Object[0]), TypeDescription.OBJECT);
        }

        public static JavaConstant ofPrimitiveType(Class<?> cls) {
            return ofPrimitiveType(TypeDescription.ForLoadedType.of(cls));
        }

        public static JavaConstant ofPrimitiveType(TypeDescription typeDescription2) {
            if (typeDescription2.isPrimitive()) {
                return new Dynamic(new ConstantDynamic(typeDescription2.getDescriptor(), TypeDescription.CLASS.getDescriptor(), new Handle(6, CONSTANT_BOOTSTRAPS, "primitiveClass", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Class;", false), new Object[0]), TypeDescription.CLASS);
            }
            throw new IllegalArgumentException("Not a primitive type: " + typeDescription2);
        }

        public static JavaConstant ofEnumeration(Enum<?> enumR) {
            return ofEnumeration((EnumerationDescription) new EnumerationDescription.ForLoadedEnumeration(enumR));
        }

        public static JavaConstant ofEnumeration(EnumerationDescription enumerationDescription) {
            return new Dynamic(new ConstantDynamic(enumerationDescription.getValue(), enumerationDescription.getEnumerationType().getDescriptor(), new Handle(6, CONSTANT_BOOTSTRAPS, "enumConstant", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Enum;", false), new Object[0]), enumerationDescription.getEnumerationType());
        }

        public static Dynamic ofField(Field field) {
            return ofField((FieldDescription.InDefinedShape) new FieldDescription.ForLoadedField(field));
        }

        public static Dynamic ofField(FieldDescription.InDefinedShape inDefinedShape) {
            boolean z;
            if (!inDefinedShape.isStatic() || !inDefinedShape.isFinal()) {
                throw new IllegalArgumentException("Field must be static and final: " + inDefinedShape);
            }
            if (inDefinedShape.getType().isPrimitive()) {
                z = inDefinedShape.getType().asErasure().asBoxed().equals(inDefinedShape.getType().asErasure());
            } else {
                z = inDefinedShape.getDeclaringType().equals(inDefinedShape.getType().asErasure());
            }
            return new Dynamic(new ConstantDynamic(inDefinedShape.getInternalName(), inDefinedShape.getDescriptor(), new Handle(6, CONSTANT_BOOTSTRAPS, "getStaticFinal", z ? "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;" : "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/Object;", false), z ? new Object[0] : new Object[]{Type.getType(inDefinedShape.getDeclaringType().getDescriptor())}), inDefinedShape.getType().asErasure());
        }

        public static Dynamic ofInvocation(Method method, Object... objArr) {
            return ofInvocation(method, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic ofInvocation(Method method, List<?> list) {
            return ofInvocation((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method), list);
        }

        public static Dynamic ofInvocation(Constructor<?> constructor, Object... objArr) {
            return ofInvocation(constructor, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic ofInvocation(Constructor<?> constructor, List<?> list) {
            return ofInvocation((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(constructor), list);
        }

        public static Dynamic ofInvocation(MethodDescription.InDefinedShape inDefinedShape, Object... objArr) {
            return ofInvocation(inDefinedShape, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic ofInvocation(MethodDescription.InDefinedShape inDefinedShape, List<?> list) {
            List list2;
            TypeDescription typeDescription2;
            TypeDescription typeDescription3;
            if (inDefinedShape.isConstructor() || !inDefinedShape.getReturnType().represents(Void.TYPE)) {
                int i = 1;
                if (inDefinedShape.getParameters().size() + ((inDefinedShape.isStatic() || inDefinedShape.isConstructor()) ? 0 : 1) == list.size()) {
                    if (inDefinedShape.isStatic() || inDefinedShape.isConstructor()) {
                        list2 = inDefinedShape.getParameters().asTypeList().asErasures();
                    } else {
                        list2 = CompoundList.of(inDefinedShape.getDeclaringType(), inDefinedShape.getParameters().asTypeList().asErasures());
                    }
                    Iterator it = list2.iterator();
                    ArrayList arrayList = new ArrayList(list.size());
                    Iterator<?> it2 = list.iterator();
                    while (it2.hasNext()) {
                        Object next = it2.next();
                        if (next == null) {
                            next = ofNullConstant();
                        } else if (next instanceof Class) {
                            Class cls = (Class) next;
                            if (cls.isPrimitive()) {
                                next = ofPrimitiveType((Class<?>) cls);
                            } else {
                                next = TypeDescription.ForLoadedType.of(cls);
                            }
                        } else {
                            if (next instanceof TypeDescription) {
                                TypeDescription typeDescription4 = (TypeDescription) next;
                                if (typeDescription4.isPrimitive()) {
                                    next = ofPrimitiveType(typeDescription4);
                                }
                            }
                            if (JavaType.METHOD_HANDLE.isInstance(next)) {
                                next = MethodHandle.ofLoaded(next);
                            } else if (JavaType.METHOD_TYPE.isInstance(next)) {
                                next = MethodType.ofLoaded(next);
                            }
                        }
                        if (next instanceof JavaConstant) {
                            typeDescription3 = ((JavaConstant) next).getType();
                        } else if (next instanceof TypeDescription) {
                            typeDescription3 = TypeDescription.CLASS;
                        } else {
                            typeDescription3 = TypeDescription.ForLoadedType.of(next.getClass());
                        }
                        if (typeDescription3.asBoxed().isAssignableTo(((TypeDescription) it.next()).asBoxed())) {
                            arrayList.add(next);
                        } else {
                            throw new IllegalArgumentException("Cannot assign argument of type " + typeDescription3 + " to " + inDefinedShape);
                        }
                    }
                    Object[] objArr = new Object[(arrayList.size() + 1)];
                    objArr[0] = new Handle(inDefinedShape.isConstructor() ? 8 : 6, inDefinedShape.getDeclaringType().getInternalName(), inDefinedShape.getInternalName(), inDefinedShape.getDescriptor(), false);
                    for (Object next2 : arrayList) {
                        if (next2 instanceof TypeDescription) {
                            next2 = Type.getType(((TypeDescription) next2).getDescriptor());
                        } else if (next2 instanceof JavaConstant) {
                            next2 = ((JavaConstant) next2).asConstantPoolValue();
                        }
                        objArr[i] = next2;
                        i++;
                    }
                    if (inDefinedShape.isConstructor()) {
                        typeDescription2 = inDefinedShape.getDeclaringType();
                    } else {
                        typeDescription2 = inDefinedShape.getReturnType().asErasure();
                    }
                    return new Dynamic(new ConstantDynamic("invoke", typeDescription2.getDescriptor(), new Handle(6, CONSTANT_BOOTSTRAPS, "invoke", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/invoke/MethodHandle;[Ljava/lang/Object;)Ljava/lang/Object;", false), objArr), inDefinedShape.isConstructor() ? inDefinedShape.getDeclaringType() : inDefinedShape.getReturnType().asErasure());
                }
                throw new IllegalArgumentException("Cannot assign " + list + " to " + inDefinedShape);
            }
            throw new IllegalArgumentException("Bootstrap method is no constructor or non-void static factory: " + inDefinedShape);
        }

        public static JavaConstant ofVarHandle(Field field) {
            return ofVarHandle((FieldDescription.InDefinedShape) new FieldDescription.ForLoadedField(field));
        }

        public static JavaConstant ofVarHandle(FieldDescription.InDefinedShape inDefinedShape) {
            return new Dynamic(new ConstantDynamic(inDefinedShape.getInternalName(), JavaType.VAR_HANDLE.getTypeStub().getDescriptor(), new Handle(6, CONSTANT_BOOTSTRAPS, inDefinedShape.isStatic() ? "staticFieldVarHandle" : "fieldVarHandle", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/invoke/VarHandle;", false), Type.getType(inDefinedShape.getDeclaringType().getDescriptor()), Type.getType(inDefinedShape.getType().asErasure().getDescriptor())), JavaType.VAR_HANDLE.getTypeStub());
        }

        public static JavaConstant ofArrayVarHandle(Class<?> cls) {
            return ofArrayVarHandle(TypeDescription.ForLoadedType.of(cls));
        }

        public static JavaConstant ofArrayVarHandle(TypeDescription typeDescription2) {
            if (typeDescription2.isArray()) {
                return new Dynamic(new ConstantDynamic("arrayVarHandle", JavaType.VAR_HANDLE.getTypeStub().getDescriptor(), new Handle(6, CONSTANT_BOOTSTRAPS, "arrayVarHandle", "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)Ljava/lang/invoke/VarHandle;", false), Type.getType(typeDescription2.getDescriptor())), JavaType.VAR_HANDLE.getTypeStub());
            }
            throw new IllegalArgumentException("Not an array type: " + typeDescription2);
        }

        public static Dynamic bootstrap(String str, Method method, Object... objArr) {
            return bootstrap(str, method, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic bootstrap(String str, Method method, List<?> list) {
            return bootstrap(str, (MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(method), list);
        }

        public static Dynamic bootstrap(String str, Constructor<?> constructor, Object... objArr) {
            return bootstrap(str, constructor, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic bootstrap(String str, Constructor<?> constructor, List<?> list) {
            return bootstrap(str, (MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(constructor), list);
        }

        public static Dynamic bootstrap(String str, MethodDescription.InDefinedShape inDefinedShape, Object... objArr) {
            return bootstrap(str, inDefinedShape, (List<?>) Arrays.asList(objArr));
        }

        public static Dynamic bootstrap(String str, MethodDescription.InDefinedShape inDefinedShape, List<?> list) {
            TypeDescription typeDescription2;
            TypeDescription typeDescription3;
            if (str.length() == 0 || str.contains(".")) {
                throw new IllegalArgumentException("Not a valid field name: " + str);
            }
            ArrayList arrayList = new ArrayList(list.size());
            for (Object next : list) {
                if (next == null) {
                    next = ofNullConstant();
                } else if (next instanceof Class) {
                    Class cls = (Class) next;
                    if (cls.isPrimitive()) {
                        next = ofPrimitiveType((Class<?>) cls);
                    } else {
                        next = TypeDescription.ForLoadedType.of(cls);
                    }
                } else {
                    if (next instanceof TypeDescription) {
                        TypeDescription typeDescription4 = (TypeDescription) next;
                        if (typeDescription4.isPrimitive()) {
                            next = ofPrimitiveType(typeDescription4);
                        }
                    }
                    if (JavaType.METHOD_HANDLE.isInstance(next)) {
                        next = MethodHandle.ofLoaded(next);
                    } else if (JavaType.METHOD_TYPE.isInstance(next)) {
                        next = MethodType.ofLoaded(next);
                    }
                }
                arrayList.add(next);
            }
            if (inDefinedShape.isConstantBootstrap(arrayList)) {
                Object[] objArr = new Object[arrayList.size()];
                int i = 0;
                for (Object next2 : arrayList) {
                    if (next2 instanceof TypeDescription) {
                        next2 = Type.getType(((TypeDescription) next2).getDescriptor());
                    } else if (next2 instanceof JavaConstant) {
                        next2 = ((JavaConstant) next2).asConstantPoolValue();
                    }
                    objArr[i] = next2;
                    i++;
                }
                if (inDefinedShape.isConstructor()) {
                    typeDescription2 = inDefinedShape.getDeclaringType();
                } else {
                    typeDescription2 = inDefinedShape.getReturnType().asErasure();
                }
                ConstantDynamic constantDynamic = new ConstantDynamic(str, typeDescription2.getDescriptor(), new Handle(inDefinedShape.isConstructor() ? 8 : 6, inDefinedShape.getDeclaringType().getInternalName(), inDefinedShape.getInternalName(), inDefinedShape.getDescriptor(), false), objArr);
                if (inDefinedShape.isConstructor()) {
                    typeDescription3 = inDefinedShape.getDeclaringType();
                } else {
                    typeDescription3 = inDefinedShape.getReturnType().asErasure();
                }
                return new Dynamic(constantDynamic, typeDescription3);
            }
            throw new IllegalArgumentException("Not a valid bootstrap method " + inDefinedShape + " for " + arrayList);
        }

        public JavaConstant withType(Class<?> cls) {
            return withType(TypeDescription.ForLoadedType.of(cls));
        }

        public JavaConstant withType(TypeDescription typeDescription2) {
            if (typeDescription2.represents(Void.TYPE)) {
                throw new IllegalArgumentException("Constant value cannot represent void");
            } else if (!this.value.getBootstrapMethod().getName().equals(MethodDescription.CONSTRUCTOR_INTERNAL_NAME) ? !typeDescription2.asBoxed().isInHierarchyWith(this.typeDescription.asBoxed()) : !this.typeDescription.isAssignableTo(typeDescription2)) {
                throw new IllegalArgumentException(typeDescription2 + " is not compatible with bootstrapped type " + this.typeDescription);
            } else {
                Object[] objArr = new Object[this.value.getBootstrapMethodArgumentCount()];
                for (int i = 0; i < this.value.getBootstrapMethodArgumentCount(); i++) {
                    objArr[i] = this.value.getBootstrapMethodArgument(i);
                }
                return new Dynamic(new ConstantDynamic(this.value.getName(), typeDescription2.getDescriptor(), this.value.getBootstrapMethod(), objArr), typeDescription2);
            }
        }

        public Object asConstantPoolValue() {
            return this.value;
        }

        public TypeDescription getType() {
            return this.typeDescription;
        }

        public int hashCode() {
            return (this.value.hashCode() * 31) + this.typeDescription.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Dynamic dynamic = (Dynamic) obj;
            if (!this.value.equals(dynamic.value) || !this.typeDescription.equals(dynamic.typeDescription)) {
                return false;
            }
            return true;
        }
    }
}

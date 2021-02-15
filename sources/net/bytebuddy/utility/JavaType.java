package net.bytebuddy.utility;

import androidx.core.view.InputDeviceCompat;
import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.List;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;

public enum JavaType {
    CONSTABLE("java.lang.constant.Constable", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[0]),
    TYPE_DESCRIPTOR("java.lang.invoke.TypeDescriptor", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[0]),
    TYPE_DESCRIPTOR_OF_FIELD("java.lang.invoke.TypeDescriptor$OfField", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[]{TYPE_DESCRIPTOR.getTypeStub()}),
    TYPE_DESCRIPTOR_OF_METHOD("java.lang.invoke.TypeDescriptor$OfMethod", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[]{TYPE_DESCRIPTOR.getTypeStub()}),
    CONSTANT_DESCRIPTION("java.lang.constant.ConstantDesc", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[0]),
    DYNAMIC_CONSTANT_DESCRIPTION("java.lang.constant.DynamicConstantDesc", (int) InputDeviceCompat.SOURCE_GAMEPAD, (String) TypeDescription.OBJECT, (int) new TypeDefinition[]{CONSTANT_DESCRIPTION.getTypeStub()}),
    CLASS_DESCRIPTION("java.lang.constant.ClassDesc", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[]{CONSTANT_DESCRIPTION.getTypeStub(), TYPE_DESCRIPTOR_OF_FIELD.getTypeStub()}),
    METHOD_TYPE_DESCRIPTION("java.lang.constant.MethodTypeDesc", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[]{CONSTANT_DESCRIPTION.getTypeStub(), TYPE_DESCRIPTOR_OF_METHOD.getTypeStub()}),
    METHOD_HANDLE_DESCRIPTION("java.lang.constant.MethodHandleDesc", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[]{CONSTANT_DESCRIPTION.getTypeStub()}),
    DIRECT_METHOD_HANDLE_DESCRIPTION("java.lang.constant.DirectMethodHandleDesc", 1537, (String) TypeDescription.UNDEFINED, (int) new TypeDefinition[]{METHOD_HANDLE_DESCRIPTION.getTypeStub()}),
    METHOD_HANDLE("java.lang.invoke.MethodHandle", (int) InputDeviceCompat.SOURCE_GAMEPAD, (String) TypeDescription.OBJECT, (int) new TypeDefinition[]{CONSTABLE.getTypeStub()}),
    METHOD_HANDLES("java.lang.invoke.MethodHandles", 1, (String) Object.class, (int) new Type[0]),
    METHOD_TYPE("java.lang.invoke.MethodType", 17, (String) TypeDescription.OBJECT, (int) new TypeDefinition[]{CONSTABLE.getTypeStub(), TYPE_DESCRIPTOR_OF_METHOD.getTypeStub(), TypeDescription.ForLoadedType.of(Serializable.class)}),
    METHOD_HANDLES_LOOKUP("java.lang.invoke.MethodHandles$Lookup", 25, (String) Object.class, (int) new Type[0]),
    CALL_SITE("java.lang.invoke.CallSite", (int) InputDeviceCompat.SOURCE_GAMEPAD, (String) Object.class, (int) new Type[0]),
    VAR_HANDLE("java.lang.invoke.VarHandle", (int) InputDeviceCompat.SOURCE_GAMEPAD, (String) TypeDescription.Generic.OBJECT, (int) new TypeDefinition[]{CONSTABLE.getTypeStub()}),
    PARAMETER("java.lang.reflect.Parameter", 17, (String) Object.class, (int) new Type[]{AnnotatedElement.class}),
    EXECUTABLE("java.lang.reflect.Executable", (int) InputDeviceCompat.SOURCE_GAMEPAD, (String) AccessibleObject.class, (int) new Type[]{Member.class, GenericDeclaration.class}),
    MODULE("java.lang.Module", 17, (String) Object.class, (int) new Type[]{AnnotatedElement.class});
    
    private final TypeDescription typeDescription;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private JavaType(java.lang.String r10, int r11, java.lang.reflect.Type r12, java.lang.reflect.Type... r13) {
        /*
            r7 = this;
            if (r12 != 0) goto L_0x0005
            net.bytebuddy.description.type.TypeDescription$Generic r12 = net.bytebuddy.description.type.TypeDescription.Generic.UNDEFINED
            goto L_0x0009
        L_0x0005:
            net.bytebuddy.description.type.TypeDescription$Generic r12 = net.bytebuddy.description.type.TypeDefinition.Sort.describe(r12)
        L_0x0009:
            r5 = r12
            net.bytebuddy.description.type.TypeList$Generic$ForLoadedTypes r6 = new net.bytebuddy.description.type.TypeList$Generic$ForLoadedTypes
            r6.<init>((java.lang.reflect.Type[]) r13)
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>((java.lang.String) r1, (int) r2, (java.lang.String) r3, (int) r4, (net.bytebuddy.description.type.TypeDescription.Generic) r5, (net.bytebuddy.description.type.TypeList.Generic) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.utility.JavaType.<init>(java.lang.String, int, java.lang.String, int, java.lang.reflect.Type, java.lang.reflect.Type[]):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private JavaType(java.lang.String r10, int r11, net.bytebuddy.description.type.TypeDefinition r12, net.bytebuddy.description.type.TypeDefinition... r13) {
        /*
            r7 = this;
            if (r12 != 0) goto L_0x0005
            net.bytebuddy.description.type.TypeDescription$Generic r12 = net.bytebuddy.description.type.TypeDescription.Generic.UNDEFINED
            goto L_0x0009
        L_0x0005:
            net.bytebuddy.description.type.TypeDescription$Generic r12 = r12.asGenericType()
        L_0x0009:
            r5 = r12
            net.bytebuddy.description.type.TypeList$Generic$Explicit r6 = new net.bytebuddy.description.type.TypeList$Generic$Explicit
            r6.<init>((net.bytebuddy.description.type.TypeDefinition[]) r13)
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>((java.lang.String) r1, (int) r2, (java.lang.String) r3, (int) r4, (net.bytebuddy.description.type.TypeDescription.Generic) r5, (net.bytebuddy.description.type.TypeList.Generic) r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.utility.JavaType.<init>(java.lang.String, int, java.lang.String, int, net.bytebuddy.description.type.TypeDefinition, net.bytebuddy.description.type.TypeDefinition[]):void");
    }

    private JavaType(String str, int i, TypeDescription.Generic generic, TypeList.Generic generic2) {
        this.typeDescription = new TypeDescription.Latent(str, i, generic, (List<? extends TypeDescription.Generic>) generic2);
    }

    public TypeDescription getTypeStub() {
        return this.typeDescription;
    }

    @CachedReturnPlugin.Enhance
    public Class<?> load() throws ClassNotFoundException {
        Class<?> cls = this.load_enN46UUS != null ? null : Class.forName(this.typeDescription.getName(), false, ClassLoadingStrategy.BOOTSTRAP_LOADER);
        if (cls == null) {
            return this.load_enN46UUS;
        }
        this.load_enN46UUS = cls;
        return cls;
    }

    public TypeDescription loadAsDescription() throws ClassNotFoundException {
        return TypeDescription.ForLoadedType.of(load());
    }

    @CachedReturnPlugin.Enhance
    public boolean isAvailable() {
        boolean z = false;
        if (!this.isAvailable_6IelXPaR) {
            try {
                load();
                z = true;
            } catch (ClassNotFoundException unused) {
            }
        }
        if (!z) {
            return true;
        }
        this.isAvailable_6IelXPaR = true;
        return z;
    }

    public boolean isInstance(Object obj) {
        if (!isAvailable()) {
            return false;
        }
        try {
            return load().isInstance(obj);
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}

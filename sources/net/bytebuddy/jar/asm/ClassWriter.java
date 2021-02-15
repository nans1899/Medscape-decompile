package net.bytebuddy.jar.asm;

import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.jar.asm.Attribute;

public class ClassWriter extends ClassVisitor {
    public static final int COMPUTE_FRAMES = 2;
    public static final int COMPUTE_MAXS = 1;
    private int accessFlags;
    private int compute;
    private ByteVector debugExtension;
    private int enclosingClassIndex;
    private int enclosingMethodIndex;
    private Attribute firstAttribute;
    private FieldWriter firstField;
    private MethodWriter firstMethod;
    private ByteVector innerClasses;
    private int interfaceCount;
    private int[] interfaces;
    private FieldWriter lastField;
    private MethodWriter lastMethod;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private ModuleWriter moduleWriter;
    private int nestHostClassIndex;
    private ByteVector nestMemberClasses;
    private int numberOfInnerClasses;
    private int numberOfNestMemberClasses;
    private int signatureIndex;
    private int sourceFileIndex;
    private int superClass;
    private final SymbolTable symbolTable;
    private int thisClass;
    private int version;

    public final void visitEnd() {
    }

    public ClassWriter(int i) {
        this((ClassReader) null, i);
    }

    public ClassWriter(ClassReader classReader, int i) {
        super(Opcodes.ASM7);
        this.symbolTable = classReader == null ? new SymbolTable(this) : new SymbolTable(this, classReader);
        if ((i & 2) != 0) {
            this.compute = 4;
        } else if ((i & 1) != 0) {
            this.compute = 1;
        } else {
            this.compute = 0;
        }
    }

    public final void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        int i3;
        this.version = i;
        this.accessFlags = i2;
        int i4 = i & 65535;
        this.thisClass = this.symbolTable.setMajorVersionAndClassName(i4, str);
        if (str2 != null) {
            this.signatureIndex = this.symbolTable.addConstantUtf8(str2);
        }
        if (str3 == null) {
            i3 = 0;
        } else {
            i3 = this.symbolTable.addConstantClass(str3).index;
        }
        this.superClass = i3;
        if (strArr != null && strArr.length > 0) {
            int length = strArr.length;
            this.interfaceCount = length;
            this.interfaces = new int[length];
            for (int i5 = 0; i5 < this.interfaceCount; i5++) {
                this.interfaces[i5] = this.symbolTable.addConstantClass(strArr[i5]).index;
            }
        }
        if (this.compute == 1 && i4 >= 51) {
            this.compute = 2;
        }
    }

    public final void visitSource(String str, String str2) {
        if (str != null) {
            this.sourceFileIndex = this.symbolTable.addConstantUtf8(str);
        }
        if (str2 != null) {
            this.debugExtension = new ByteVector().encodeUtf8(str2, 0, Integer.MAX_VALUE);
        }
    }

    public final ModuleVisitor visitModule(String str, int i, String str2) {
        int i2;
        SymbolTable symbolTable2 = this.symbolTable;
        int i3 = symbolTable2.addConstantModule(str).index;
        if (str2 == null) {
            i2 = 0;
        } else {
            i2 = this.symbolTable.addConstantUtf8(str2);
        }
        ModuleWriter moduleWriter2 = new ModuleWriter(symbolTable2, i3, i, i2);
        this.moduleWriter = moduleWriter2;
        return moduleWriter2;
    }

    public void visitNestHost(String str) {
        this.nestHostClassIndex = this.symbolTable.addConstantClass(str).index;
    }

    public final void visitOuterClass(String str, String str2, String str3) {
        this.enclosingClassIndex = this.symbolTable.addConstantClass(str).index;
        if (str2 != null && str3 != null) {
            this.enclosingMethodIndex = this.symbolTable.addConstantNameAndType(str2, str3);
        }
    }

    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(this.symbolTable.addConstantUtf8(str)).putShort(0);
        if (z) {
            AnnotationWriter annotationWriter = new AnnotationWriter(this.symbolTable, byteVector, this.lastRuntimeVisibleAnnotation);
            this.lastRuntimeVisibleAnnotation = annotationWriter;
            return annotationWriter;
        }
        AnnotationWriter annotationWriter2 = new AnnotationWriter(this.symbolTable, byteVector, this.lastRuntimeInvisibleAnnotation);
        this.lastRuntimeInvisibleAnnotation = annotationWriter2;
        return annotationWriter2;
    }

    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        TypeReference.putTarget(i, byteVector);
        TypePath.put(typePath, byteVector);
        byteVector.putShort(this.symbolTable.addConstantUtf8(str)).putShort(0);
        if (z) {
            AnnotationWriter annotationWriter = new AnnotationWriter(this.symbolTable, byteVector, this.lastRuntimeVisibleTypeAnnotation);
            this.lastRuntimeVisibleTypeAnnotation = annotationWriter;
            return annotationWriter;
        }
        AnnotationWriter annotationWriter2 = new AnnotationWriter(this.symbolTable, byteVector, this.lastRuntimeInvisibleTypeAnnotation);
        this.lastRuntimeInvisibleTypeAnnotation = annotationWriter2;
        return annotationWriter2;
    }

    public final void visitAttribute(Attribute attribute) {
        attribute.nextAttribute = this.firstAttribute;
        this.firstAttribute = attribute;
    }

    public void visitNestMember(String str) {
        if (this.nestMemberClasses == null) {
            this.nestMemberClasses = new ByteVector();
        }
        this.numberOfNestMemberClasses++;
        this.nestMemberClasses.putShort(this.symbolTable.addConstantClass(str).index);
    }

    public final void visitInnerClass(String str, String str2, String str3, int i) {
        if (this.innerClasses == null) {
            this.innerClasses = new ByteVector();
        }
        Symbol addConstantClass = this.symbolTable.addConstantClass(str);
        if (addConstantClass.info == 0) {
            this.numberOfInnerClasses++;
            this.innerClasses.putShort(addConstantClass.index);
            int i2 = 0;
            this.innerClasses.putShort(str2 == null ? 0 : this.symbolTable.addConstantClass(str2).index);
            ByteVector byteVector = this.innerClasses;
            if (str3 != null) {
                i2 = this.symbolTable.addConstantUtf8(str3);
            }
            byteVector.putShort(i2);
            this.innerClasses.putShort(i);
            addConstantClass.info = this.numberOfInnerClasses;
        }
    }

    public final FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        FieldWriter fieldWriter = new FieldWriter(this.symbolTable, i, str, str2, str3, obj);
        if (this.firstField == null) {
            this.firstField = fieldWriter;
        } else {
            this.lastField.fv = fieldWriter;
        }
        this.lastField = fieldWriter;
        return fieldWriter;
    }

    public final MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        MethodWriter methodWriter = new MethodWriter(this.symbolTable, i, str, str2, str3, strArr, this.compute);
        if (this.firstMethod == null) {
            this.firstMethod = methodWriter;
        } else {
            this.lastMethod.mv = methodWriter;
        }
        this.lastMethod = methodWriter;
        return methodWriter;
    }

    public byte[] toByteArray() throws ClassTooLargeException, MethodTooLargeException {
        int i;
        int i2;
        int i3;
        int i4 = (this.interfaceCount * 2) + 24;
        int i5 = 0;
        for (FieldWriter fieldWriter = this.firstField; fieldWriter != null; fieldWriter = (FieldWriter) fieldWriter.fv) {
            i5++;
            i4 += fieldWriter.computeFieldInfoSize();
        }
        int i6 = 0;
        for (MethodWriter methodWriter = this.firstMethod; methodWriter != null; methodWriter = (MethodWriter) methodWriter.mv) {
            i6++;
            i4 += methodWriter.computeMethodInfoSize();
        }
        ByteVector byteVector = this.innerClasses;
        if (byteVector != null) {
            i4 += byteVector.length + 8;
            this.symbolTable.addConstantUtf8("InnerClasses");
            i = 1;
        } else {
            i = 0;
        }
        if (this.enclosingClassIndex != 0) {
            i++;
            i4 += 10;
            this.symbolTable.addConstantUtf8("EnclosingMethod");
        }
        if ((this.accessFlags & 4096) != 0 && (this.version & 65535) < 49) {
            i++;
            i4 += 6;
            this.symbolTable.addConstantUtf8("Synthetic");
        }
        if (this.signatureIndex != 0) {
            i++;
            i4 += 8;
            this.symbolTable.addConstantUtf8("Signature");
        }
        if (this.sourceFileIndex != 0) {
            i++;
            i4 += 8;
            this.symbolTable.addConstantUtf8("SourceFile");
        }
        ByteVector byteVector2 = this.debugExtension;
        if (byteVector2 != null) {
            i++;
            i4 += byteVector2.length + 6;
            this.symbolTable.addConstantUtf8("SourceDebugExtension");
        }
        if ((this.accessFlags & 131072) != 0) {
            i++;
            i4 += 6;
            this.symbolTable.addConstantUtf8("Deprecated");
        }
        AnnotationWriter annotationWriter = this.lastRuntimeVisibleAnnotation;
        if (annotationWriter != null) {
            i++;
            i4 += annotationWriter.computeAnnotationsSize("RuntimeVisibleAnnotations");
        }
        AnnotationWriter annotationWriter2 = this.lastRuntimeInvisibleAnnotation;
        if (annotationWriter2 != null) {
            i++;
            i4 += annotationWriter2.computeAnnotationsSize("RuntimeInvisibleAnnotations");
        }
        AnnotationWriter annotationWriter3 = this.lastRuntimeVisibleTypeAnnotation;
        if (annotationWriter3 != null) {
            i++;
            i4 += annotationWriter3.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
        }
        AnnotationWriter annotationWriter4 = this.lastRuntimeInvisibleTypeAnnotation;
        String str = "RuntimeVisibleTypeAnnotations";
        if (annotationWriter4 != null) {
            i++;
            i4 += annotationWriter4.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
        }
        if (this.symbolTable.computeBootstrapMethodsSize() > 0) {
            i++;
            i4 += this.symbolTable.computeBootstrapMethodsSize();
        }
        ModuleWriter moduleWriter2 = this.moduleWriter;
        if (moduleWriter2 != null) {
            i += moduleWriter2.getAttributeCount();
            i4 += this.moduleWriter.computeAttributesSize();
        }
        String str2 = "RuntimeInvisibleTypeAnnotations";
        if (this.nestHostClassIndex != 0) {
            i++;
            i4 += 8;
            this.symbolTable.addConstantUtf8("NestHost");
        }
        ByteVector byteVector3 = this.nestMemberClasses;
        String str3 = "NestHost";
        if (byteVector3 != null) {
            i++;
            i4 += byteVector3.length + 8;
            this.symbolTable.addConstantUtf8("NestMembers");
        }
        Attribute attribute = this.firstAttribute;
        if (attribute != null) {
            i4 += this.firstAttribute.computeAttributesSize(this.symbolTable);
            i += attribute.getAttributeCount();
        }
        int constantPoolLength = i4 + this.symbolTable.getConstantPoolLength();
        int constantPoolCount = this.symbolTable.getConstantPoolCount();
        String str4 = "NestMembers";
        if (constantPoolCount <= 65535) {
            ByteVector byteVector4 = new ByteVector(constantPoolLength);
            byteVector4.putInt(-889275714).putInt(this.version);
            this.symbolTable.putConstantPool(byteVector4);
            byteVector4.putShort((~((this.version & 65535) < 49 ? 4096 : 0)) & this.accessFlags).putShort(this.thisClass).putShort(this.superClass);
            byteVector4.putShort(this.interfaceCount);
            for (int i7 = 0; i7 < this.interfaceCount; i7++) {
                byteVector4.putShort(this.interfaces[i7]);
            }
            byteVector4.putShort(i5);
            for (FieldWriter fieldWriter2 = this.firstField; fieldWriter2 != null; fieldWriter2 = (FieldWriter) fieldWriter2.fv) {
                fieldWriter2.putFieldInfo(byteVector4);
            }
            byteVector4.putShort(i6);
            boolean z = false;
            boolean z2 = false;
            for (MethodWriter methodWriter2 = this.firstMethod; methodWriter2 != null; methodWriter2 = (MethodWriter) methodWriter2.mv) {
                z |= methodWriter2.hasFrames();
                z2 |= methodWriter2.hasAsmInstructions();
                methodWriter2.putMethodInfo(byteVector4);
            }
            byteVector4.putShort(i);
            if (this.innerClasses != null) {
                byteVector4.putShort(this.symbolTable.addConstantUtf8("InnerClasses")).putInt(this.innerClasses.length + 2).putShort(this.numberOfInnerClasses).putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
            }
            if (this.enclosingClassIndex != 0) {
                byteVector4.putShort(this.symbolTable.addConstantUtf8("EnclosingMethod")).putInt(4).putShort(this.enclosingClassIndex).putShort(this.enclosingMethodIndex);
            }
            if ((this.accessFlags & 4096) != 0 && (this.version & 65535) < 49) {
                byteVector4.putShort(this.symbolTable.addConstantUtf8("Synthetic")).putInt(0);
            }
            if (this.signatureIndex != 0) {
                i2 = 2;
                byteVector4.putShort(this.symbolTable.addConstantUtf8("Signature")).putInt(2).putShort(this.signatureIndex);
            } else {
                i2 = 2;
            }
            if (this.sourceFileIndex != 0) {
                byteVector4.putShort(this.symbolTable.addConstantUtf8("SourceFile")).putInt(i2).putShort(this.sourceFileIndex);
            }
            ByteVector byteVector5 = this.debugExtension;
            if (byteVector5 != null) {
                int i8 = byteVector5.length;
                i3 = 0;
                byteVector4.putShort(this.symbolTable.addConstantUtf8("SourceDebugExtension")).putInt(i8).putByteArray(this.debugExtension.data, 0, i8);
            } else {
                i3 = 0;
            }
            if ((this.accessFlags & 131072) != 0) {
                byteVector4.putShort(this.symbolTable.addConstantUtf8("Deprecated")).putInt(i3);
            }
            AnnotationWriter annotationWriter5 = this.lastRuntimeVisibleAnnotation;
            if (annotationWriter5 != null) {
                annotationWriter5.putAnnotations(this.symbolTable.addConstantUtf8("RuntimeVisibleAnnotations"), byteVector4);
            }
            AnnotationWriter annotationWriter6 = this.lastRuntimeInvisibleAnnotation;
            if (annotationWriter6 != null) {
                annotationWriter6.putAnnotations(this.symbolTable.addConstantUtf8("RuntimeInvisibleAnnotations"), byteVector4);
            }
            AnnotationWriter annotationWriter7 = this.lastRuntimeVisibleTypeAnnotation;
            if (annotationWriter7 != null) {
                annotationWriter7.putAnnotations(this.symbolTable.addConstantUtf8(str), byteVector4);
            }
            AnnotationWriter annotationWriter8 = this.lastRuntimeInvisibleTypeAnnotation;
            if (annotationWriter8 != null) {
                annotationWriter8.putAnnotations(this.symbolTable.addConstantUtf8(str2), byteVector4);
            }
            this.symbolTable.putBootstrapMethods(byteVector4);
            ModuleWriter moduleWriter3 = this.moduleWriter;
            if (moduleWriter3 != null) {
                moduleWriter3.putAttributes(byteVector4);
            }
            if (this.nestHostClassIndex != 0) {
                byteVector4.putShort(this.symbolTable.addConstantUtf8(str3)).putInt(2).putShort(this.nestHostClassIndex);
            }
            if (this.nestMemberClasses != null) {
                byteVector4.putShort(this.symbolTable.addConstantUtf8(str4)).putInt(this.nestMemberClasses.length + 2).putShort(this.numberOfNestMemberClasses).putByteArray(this.nestMemberClasses.data, 0, this.nestMemberClasses.length);
            }
            Attribute attribute2 = this.firstAttribute;
            if (attribute2 != null) {
                attribute2.putAttributes(this.symbolTable, byteVector4);
            }
            if (z2) {
                return replaceAsmInstructions(byteVector4.data, z);
            }
            return byteVector4.data;
        }
        throw new ClassTooLargeException(this.symbolTable.getClassName(), constantPoolCount);
    }

    private byte[] replaceAsmInstructions(byte[] bArr, boolean z) {
        Attribute[] attributePrototypes = getAttributePrototypes();
        this.firstField = null;
        this.lastField = null;
        this.firstMethod = null;
        this.lastMethod = null;
        this.lastRuntimeVisibleAnnotation = null;
        this.lastRuntimeInvisibleAnnotation = null;
        this.lastRuntimeVisibleTypeAnnotation = null;
        this.lastRuntimeInvisibleTypeAnnotation = null;
        this.moduleWriter = null;
        int i = 0;
        this.nestHostClassIndex = 0;
        this.numberOfNestMemberClasses = 0;
        this.nestMemberClasses = null;
        this.firstAttribute = null;
        this.compute = z ? 3 : 0;
        ClassReader classReader = new ClassReader(bArr, 0, false);
        if (z) {
            i = 8;
        }
        classReader.accept(this, attributePrototypes, i | 256);
        return toByteArray();
    }

    private Attribute[] getAttributePrototypes() {
        Attribute.Set set = new Attribute.Set();
        set.addAttributes(this.firstAttribute);
        for (FieldWriter fieldWriter = this.firstField; fieldWriter != null; fieldWriter = (FieldWriter) fieldWriter.fv) {
            fieldWriter.collectAttributePrototypes(set);
        }
        for (MethodWriter methodWriter = this.firstMethod; methodWriter != null; methodWriter = (MethodWriter) methodWriter.mv) {
            methodWriter.collectAttributePrototypes(set);
        }
        return set.toArray();
    }

    public int newConst(Object obj) {
        return this.symbolTable.addConstant(obj).index;
    }

    public int newUTF8(String str) {
        return this.symbolTable.addConstantUtf8(str);
    }

    public int newClass(String str) {
        return this.symbolTable.addConstantClass(str).index;
    }

    public int newMethodType(String str) {
        return this.symbolTable.addConstantMethodType(str).index;
    }

    public int newModule(String str) {
        return this.symbolTable.addConstantModule(str).index;
    }

    public int newPackage(String str) {
        return this.symbolTable.addConstantPackage(str).index;
    }

    @Deprecated
    public int newHandle(int i, String str, String str2, String str3) {
        return newHandle(i, str, str2, str3, i == 9);
    }

    public int newHandle(int i, String str, String str2, String str3, boolean z) {
        return this.symbolTable.addConstantMethodHandle(i, str, str2, str3, z).index;
    }

    public int newConstantDynamic(String str, String str2, Handle handle, Object... objArr) {
        return this.symbolTable.addConstantDynamic(str, str2, handle, objArr).index;
    }

    public int newInvokeDynamic(String str, String str2, Handle handle, Object... objArr) {
        return this.symbolTable.addConstantInvokeDynamic(str, str2, handle, objArr).index;
    }

    public int newField(String str, String str2, String str3) {
        return this.symbolTable.addConstantFieldref(str, str2, str3).index;
    }

    public int newMethod(String str, String str2, String str3, boolean z) {
        return this.symbolTable.addConstantMethodref(str, str2, str3, z).index;
    }

    public int newNameType(String str, String str2) {
        return this.symbolTable.addConstantNameAndType(str, str2);
    }

    /* access modifiers changed from: protected */
    public String getCommonSuperClass(String str, String str2) {
        ClassLoader classLoader = getClassLoader();
        try {
            Class cls = Class.forName(str.replace('/', '.'), false, classLoader);
            try {
                Class<?> cls2 = Class.forName(str2.replace('/', '.'), false, classLoader);
                if (cls.isAssignableFrom(cls2)) {
                    return str;
                }
                if (cls2.isAssignableFrom(cls)) {
                    return str2;
                }
                if (cls.isInterface() || cls2.isInterface()) {
                    return TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME;
                }
                do {
                    cls = cls.getSuperclass();
                } while (!cls.isAssignableFrom(cls2));
                return cls.getName().replace('.', '/');
            } catch (ClassNotFoundException e) {
                throw new TypeNotPresentException(str2, e);
            }
        } catch (ClassNotFoundException e2) {
            throw new TypeNotPresentException(str, e2);
        }
    }

    /* access modifiers changed from: protected */
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }
}

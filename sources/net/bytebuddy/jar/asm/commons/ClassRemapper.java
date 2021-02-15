package net.bytebuddy.jar.asm.commons;

import java.util.List;
import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.ModuleVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.TypePath;

public class ClassRemapper extends ClassVisitor {
    protected String className;
    protected final Remapper remapper;

    public ClassRemapper(ClassVisitor classVisitor, Remapper remapper2) {
        this(Opcodes.ASM7, classVisitor, remapper2);
    }

    protected ClassRemapper(int i, ClassVisitor classVisitor, Remapper remapper2) {
        super(i, classVisitor);
        this.remapper = remapper2;
    }

    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        String[] strArr2;
        this.className = str;
        String mapType = this.remapper.mapType(str);
        String mapSignature = this.remapper.mapSignature(str2, false);
        String mapType2 = this.remapper.mapType(str3);
        if (strArr == null) {
            strArr2 = null;
        } else {
            strArr2 = this.remapper.mapTypes(strArr);
        }
        super.visit(i, i2, mapType, mapSignature, mapType2, strArr2);
    }

    public ModuleVisitor visitModule(String str, int i, String str2) {
        ModuleVisitor visitModule = super.visitModule(this.remapper.mapModuleName(str), i, str2);
        if (visitModule == null) {
            return null;
        }
        return createModuleRemapper(visitModule);
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        AnnotationVisitor visitAnnotation = super.visitAnnotation(this.remapper.mapDesc(str), z);
        if (visitAnnotation == null) {
            return null;
        }
        return createAnnotationRemapper(visitAnnotation);
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        AnnotationVisitor visitTypeAnnotation = super.visitTypeAnnotation(i, typePath, this.remapper.mapDesc(str), z);
        if (visitTypeAnnotation == null) {
            return null;
        }
        return createAnnotationRemapper(visitTypeAnnotation);
    }

    public void visitAttribute(Attribute attribute) {
        if (attribute instanceof ModuleHashesAttribute) {
            List list = ((ModuleHashesAttribute) attribute).modules;
            for (int i = 0; i < list.size(); i++) {
                list.set(i, this.remapper.mapModuleName((String) list.get(i)));
            }
        }
        super.visitAttribute(attribute);
    }

    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        Object obj2;
        String mapFieldName = this.remapper.mapFieldName(this.className, str, str2);
        String mapDesc = this.remapper.mapDesc(str2);
        String mapSignature = this.remapper.mapSignature(str3, true);
        if (obj == null) {
            obj2 = null;
        } else {
            obj2 = this.remapper.mapValue(obj);
        }
        FieldVisitor visitField = super.visitField(i, mapFieldName, mapDesc, mapSignature, obj2);
        if (visitField == null) {
            return null;
        }
        return createFieldRemapper(visitField);
    }

    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        String[] strArr2;
        String mapMethodDesc = this.remapper.mapMethodDesc(str2);
        String mapMethodName = this.remapper.mapMethodName(this.className, str, str2);
        String mapSignature = this.remapper.mapSignature(str3, false);
        if (strArr == null) {
            strArr2 = null;
        } else {
            strArr2 = this.remapper.mapTypes(strArr);
        }
        MethodVisitor visitMethod = super.visitMethod(i, mapMethodName, mapMethodDesc, mapSignature, strArr2);
        if (visitMethod == null) {
            return null;
        }
        return createMethodRemapper(visitMethod);
    }

    public void visitInnerClass(String str, String str2, String str3, int i) {
        String str4;
        String mapType = this.remapper.mapType(str);
        String str5 = null;
        if (str2 == null) {
            str4 = null;
        } else {
            str4 = this.remapper.mapType(str2);
        }
        if (str3 != null) {
            str5 = this.remapper.mapInnerClassName(str, str2, str3);
        }
        super.visitInnerClass(mapType, str4, str5, i);
    }

    public void visitOuterClass(String str, String str2, String str3) {
        String str4;
        String mapType = this.remapper.mapType(str);
        String str5 = null;
        if (str2 == null) {
            str4 = null;
        } else {
            str4 = this.remapper.mapMethodName(str, str2, str3);
        }
        if (str3 != null) {
            str5 = this.remapper.mapMethodDesc(str3);
        }
        super.visitOuterClass(mapType, str4, str5);
    }

    public void visitNestHost(String str) {
        super.visitNestHost(this.remapper.mapType(str));
    }

    public void visitNestMember(String str) {
        super.visitNestMember(this.remapper.mapType(str));
    }

    /* access modifiers changed from: protected */
    public FieldVisitor createFieldRemapper(FieldVisitor fieldVisitor) {
        return new FieldRemapper(this.api, fieldVisitor, this.remapper);
    }

    /* access modifiers changed from: protected */
    public MethodVisitor createMethodRemapper(MethodVisitor methodVisitor) {
        return new MethodRemapper(this.api, methodVisitor, this.remapper);
    }

    /* access modifiers changed from: protected */
    public AnnotationVisitor createAnnotationRemapper(AnnotationVisitor annotationVisitor) {
        return new AnnotationRemapper(this.api, annotationVisitor, this.remapper);
    }

    /* access modifiers changed from: protected */
    public ModuleVisitor createModuleRemapper(ModuleVisitor moduleVisitor) {
        return new ModuleRemapper(this.api, moduleVisitor, this.remapper);
    }
}

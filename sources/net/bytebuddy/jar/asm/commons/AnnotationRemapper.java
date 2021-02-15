package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class AnnotationRemapper extends AnnotationVisitor {
    protected final Remapper remapper;

    public AnnotationRemapper(AnnotationVisitor annotationVisitor, Remapper remapper2) {
        this(Opcodes.ASM7, annotationVisitor, remapper2);
    }

    protected AnnotationRemapper(int i, AnnotationVisitor annotationVisitor, Remapper remapper2) {
        super(i, annotationVisitor);
        this.remapper = remapper2;
    }

    public void visit(String str, Object obj) {
        super.visit(str, this.remapper.mapValue(obj));
    }

    public void visitEnum(String str, String str2, String str3) {
        super.visitEnum(str, this.remapper.mapDesc(str2), str3);
    }

    public AnnotationVisitor visitAnnotation(String str, String str2) {
        AnnotationVisitor visitAnnotation = super.visitAnnotation(str, this.remapper.mapDesc(str2));
        if (visitAnnotation == null) {
            return null;
        }
        return visitAnnotation == this.av ? this : new AnnotationRemapper(this.api, visitAnnotation, this.remapper);
    }

    public AnnotationVisitor visitArray(String str) {
        AnnotationVisitor visitArray = super.visitArray(str);
        if (visitArray == null) {
            return null;
        }
        return visitArray == this.av ? this : new AnnotationRemapper(this.api, visitArray, this.remapper);
    }
}

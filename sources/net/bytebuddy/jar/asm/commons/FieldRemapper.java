package net.bytebuddy.jar.asm.commons;

import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.jar.asm.TypePath;

public class FieldRemapper extends FieldVisitor {
    protected final Remapper remapper;

    public FieldRemapper(FieldVisitor fieldVisitor, Remapper remapper2) {
        this(Opcodes.ASM7, fieldVisitor, remapper2);
    }

    protected FieldRemapper(int i, FieldVisitor fieldVisitor, Remapper remapper2) {
        super(i, fieldVisitor);
        this.remapper = remapper2;
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        AnnotationVisitor visitAnnotation = super.visitAnnotation(this.remapper.mapDesc(str), z);
        if (visitAnnotation == null) {
            return null;
        }
        return new AnnotationRemapper(this.api, visitAnnotation, this.remapper);
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        AnnotationVisitor visitTypeAnnotation = super.visitTypeAnnotation(i, typePath, this.remapper.mapDesc(str), z);
        if (visitTypeAnnotation == null) {
            return null;
        }
        return new AnnotationRemapper(this.api, visitTypeAnnotation, this.remapper);
    }
}

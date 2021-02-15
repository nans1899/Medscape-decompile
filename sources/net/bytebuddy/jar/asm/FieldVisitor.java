package net.bytebuddy.jar.asm;

public abstract class FieldVisitor {
    protected final int api;
    protected FieldVisitor fv;

    public FieldVisitor(int i) {
        this(i, (FieldVisitor) null);
    }

    public FieldVisitor(int i, FieldVisitor fieldVisitor) {
        if (i == 393216 || i == 327680 || i == 262144 || i == 458752) {
            this.api = i;
            this.fv = fieldVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        FieldVisitor fieldVisitor = this.fv;
        if (fieldVisitor != null) {
            return fieldVisitor.visitAnnotation(str, z);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (this.api >= 327680) {
            FieldVisitor fieldVisitor = this.fv;
            if (fieldVisitor != null) {
                return fieldVisitor.visitTypeAnnotation(i, typePath, str, z);
            }
            return null;
        }
        throw new UnsupportedOperationException("This feature requires ASM5");
    }

    public void visitAttribute(Attribute attribute) {
        FieldVisitor fieldVisitor = this.fv;
        if (fieldVisitor != null) {
            fieldVisitor.visitAttribute(attribute);
        }
    }

    public void visitEnd() {
        FieldVisitor fieldVisitor = this.fv;
        if (fieldVisitor != null) {
            fieldVisitor.visitEnd();
        }
    }
}

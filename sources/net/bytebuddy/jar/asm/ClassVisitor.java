package net.bytebuddy.jar.asm;

public abstract class ClassVisitor {
    protected final int api;
    /* access modifiers changed from: protected */
    public ClassVisitor cv;

    public ClassVisitor(int i) {
        this(i, (ClassVisitor) null);
    }

    public ClassVisitor(int i, ClassVisitor classVisitor) {
        if (i == 393216 || i == 327680 || i == 262144 || i == 458752) {
            this.api = i;
            this.cv = classVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visit(i, i2, str, str2, str3, strArr);
        }
    }

    public void visitSource(String str, String str2) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitSource(str, str2);
        }
    }

    public ModuleVisitor visitModule(String str, int i, String str2) {
        if (this.api >= 393216) {
            ClassVisitor classVisitor = this.cv;
            if (classVisitor != null) {
                return classVisitor.visitModule(str, i, str2);
            }
            return null;
        }
        throw new UnsupportedOperationException("This feature requires ASM6");
    }

    public void visitNestHost(String str) {
        if (this.api >= 458752) {
            ClassVisitor classVisitor = this.cv;
            if (classVisitor != null) {
                classVisitor.visitNestHost(str);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("This feature requires ASM7");
    }

    public void visitOuterClass(String str, String str2, String str3) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitOuterClass(str, str2, str3);
        }
    }

    public AnnotationVisitor visitAnnotation(String str, boolean z) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            return classVisitor.visitAnnotation(str, z);
        }
        return null;
    }

    public AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        if (this.api >= 327680) {
            ClassVisitor classVisitor = this.cv;
            if (classVisitor != null) {
                return classVisitor.visitTypeAnnotation(i, typePath, str, z);
            }
            return null;
        }
        throw new UnsupportedOperationException("This feature requires ASM5");
    }

    public void visitAttribute(Attribute attribute) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitAttribute(attribute);
        }
    }

    public void visitNestMember(String str) {
        if (this.api >= 458752) {
            ClassVisitor classVisitor = this.cv;
            if (classVisitor != null) {
                classVisitor.visitNestMember(str);
                return;
            }
            return;
        }
        throw new UnsupportedOperationException("This feature requires ASM7");
    }

    public void visitInnerClass(String str, String str2, String str3, int i) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitInnerClass(str, str2, str3, i);
        }
    }

    public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            return classVisitor.visitField(i, str, str2, str3, obj);
        }
        return null;
    }

    public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            return classVisitor.visitMethod(i, str, str2, str3, strArr);
        }
        return null;
    }

    public void visitEnd() {
        ClassVisitor classVisitor = this.cv;
        if (classVisitor != null) {
            classVisitor.visitEnd();
        }
    }
}

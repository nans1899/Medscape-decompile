package net.bytebuddy.utility.visitor;

import net.bytebuddy.jar.asm.AnnotationVisitor;
import net.bytebuddy.jar.asm.Attribute;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.TypePath;

public abstract class MetadataAwareClassVisitor extends ClassVisitor {
    private boolean triggerAttributes = true;
    private boolean triggerNestHost = true;
    private boolean triggerOuterClass = true;

    /* access modifiers changed from: protected */
    public abstract void onAfterAttributes();

    /* access modifiers changed from: protected */
    public abstract void onNestHost();

    /* access modifiers changed from: protected */
    public abstract void onOuterType();

    protected MetadataAwareClassVisitor(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    private void considerTriggerNestHost() {
        if (this.triggerNestHost) {
            this.triggerNestHost = false;
            onNestHost();
        }
    }

    private void considerTriggerOuterClass() {
        if (this.triggerOuterClass) {
            this.triggerOuterClass = false;
            onOuterType();
        }
    }

    private void considerTriggerAfterAttributes() {
        if (this.triggerAttributes) {
            this.triggerAttributes = false;
            onAfterAttributes();
        }
    }

    public final void visitNestHost(String str) {
        this.triggerNestHost = false;
        onVisitNestHost(str);
    }

    /* access modifiers changed from: protected */
    public void onVisitNestHost(String str) {
        super.visitNestHost(str);
    }

    public final void visitOuterClass(String str, String str2, String str3) {
        considerTriggerNestHost();
        this.triggerOuterClass = false;
        onVisitOuterClass(str, str2, str3);
    }

    /* access modifiers changed from: protected */
    public void onVisitOuterClass(String str, String str2, String str3) {
        super.visitOuterClass(str, str2, str3);
    }

    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        return onVisitAnnotation(str, z);
    }

    /* access modifiers changed from: protected */
    public AnnotationVisitor onVisitAnnotation(String str, boolean z) {
        return super.visitAnnotation(str, z);
    }

    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        return onVisitTypeAnnotation(i, typePath, str, z);
    }

    /* access modifiers changed from: protected */
    public AnnotationVisitor onVisitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        return super.visitTypeAnnotation(i, typePath, str, z);
    }

    public final void visitAttribute(Attribute attribute) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        onVisitAttribute(attribute);
    }

    /* access modifiers changed from: protected */
    public void onVisitAttribute(Attribute attribute) {
        super.visitAttribute(attribute);
    }

    public final void visitNestMember(String str) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        onVisitNestMember(str);
    }

    /* access modifiers changed from: protected */
    public void onVisitNestMember(String str) {
        super.visitNestMember(str);
    }

    public final void visitInnerClass(String str, String str2, String str3, int i) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        onVisitInnerClass(str, str2, str3, i);
    }

    /* access modifiers changed from: protected */
    public void onVisitInnerClass(String str, String str2, String str3, int i) {
        super.visitInnerClass(str, str2, str3, i);
    }

    public final FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        return onVisitField(i, str, str2, str3, obj);
    }

    /* access modifiers changed from: protected */
    public FieldVisitor onVisitField(int i, String str, String str2, String str3, Object obj) {
        return super.visitField(i, str, str2, str3, obj);
    }

    public final MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        return onVisitMethod(i, str, str2, str3, strArr);
    }

    /* access modifiers changed from: protected */
    public MethodVisitor onVisitMethod(int i, String str, String str2, String str3, String[] strArr) {
        return super.visitMethod(i, str, str2, str3, strArr);
    }

    public final void visitEnd() {
        considerTriggerNestHost();
        considerTriggerOuterClass();
        considerTriggerAfterAttributes();
        onVisitEnd();
    }

    /* access modifiers changed from: protected */
    public void onVisitEnd() {
        super.visitEnd();
    }
}

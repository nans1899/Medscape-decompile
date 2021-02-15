package net.bytebuddy.utility.visitor;

import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;

public abstract class ExceptionTableSensitiveMethodVisitor extends MethodVisitor {
    private boolean trigger = true;

    /* access modifiers changed from: protected */
    public abstract void onAfterExceptionTable();

    protected ExceptionTableSensitiveMethodVisitor(int i, MethodVisitor methodVisitor) {
        super(i, methodVisitor);
    }

    private void considerEndOfExceptionTable() {
        if (this.trigger) {
            this.trigger = false;
            onAfterExceptionTable();
        }
    }

    public final void visitLabel(Label label) {
        considerEndOfExceptionTable();
        onVisitLabel(label);
    }

    /* access modifiers changed from: protected */
    public void onVisitLabel(Label label) {
        super.visitLabel(label);
    }

    public final void visitIntInsn(int i, int i2) {
        considerEndOfExceptionTable();
        onVisitIntInsn(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onVisitIntInsn(int i, int i2) {
        super.visitIntInsn(i, i2);
    }

    public final void visitVarInsn(int i, int i2) {
        considerEndOfExceptionTable();
        onVisitVarInsn(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onVisitVarInsn(int i, int i2) {
        super.visitVarInsn(i, i2);
    }

    public final void visitTypeInsn(int i, String str) {
        considerEndOfExceptionTable();
        onVisitTypeInsn(i, str);
    }

    /* access modifiers changed from: protected */
    public void onVisitTypeInsn(int i, String str) {
        super.visitTypeInsn(i, str);
    }

    public final void visitFieldInsn(int i, String str, String str2, String str3) {
        considerEndOfExceptionTable();
        onVisitFieldInsn(i, str, str2, str3);
    }

    /* access modifiers changed from: protected */
    public void onVisitFieldInsn(int i, String str, String str2, String str3) {
        super.visitFieldInsn(i, str, str2, str3);
    }

    public final void visitMethodInsn(int i, String str, String str2, String str3) {
        considerEndOfExceptionTable();
        onVisitMethodInsn(i, str, str2, str3);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void onVisitMethodInsn(int i, String str, String str2, String str3) {
        considerEndOfExceptionTable();
        super.visitMethodInsn(i, str, str2, str3);
    }

    public final void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        considerEndOfExceptionTable();
        onVisitMethodInsn(i, str, str2, str3, z);
    }

    /* access modifiers changed from: protected */
    public void onVisitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        super.visitMethodInsn(i, str, str2, str3, z);
    }

    public final void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        considerEndOfExceptionTable();
        onVisitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    /* access modifiers changed from: protected */
    public void onVisitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        super.visitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    public final void visitJumpInsn(int i, Label label) {
        considerEndOfExceptionTable();
        onVisitJumpInsn(i, label);
    }

    /* access modifiers changed from: protected */
    public void onVisitJumpInsn(int i, Label label) {
        super.visitJumpInsn(i, label);
    }

    public final void visitLdcInsn(Object obj) {
        considerEndOfExceptionTable();
        onVisitLdcInsn(obj);
    }

    /* access modifiers changed from: protected */
    public void onVisitLdcInsn(Object obj) {
        super.visitLdcInsn(obj);
    }

    public final void visitIincInsn(int i, int i2) {
        considerEndOfExceptionTable();
        onVisitIincInsn(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onVisitIincInsn(int i, int i2) {
        super.visitIincInsn(i, i2);
    }

    public final void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        considerEndOfExceptionTable();
        onVisitTableSwitchInsn(i, i2, label, labelArr);
    }

    /* access modifiers changed from: protected */
    public void onVisitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        super.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    public final void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        considerEndOfExceptionTable();
        onVisitLookupSwitchInsn(label, iArr, labelArr);
    }

    /* access modifiers changed from: protected */
    public void onVisitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        super.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    public final void visitMultiANewArrayInsn(String str, int i) {
        considerEndOfExceptionTable();
        onVisitMultiANewArrayInsn(str, i);
    }

    /* access modifiers changed from: protected */
    public void onVisitMultiANewArrayInsn(String str, int i) {
        super.visitMultiANewArrayInsn(str, i);
    }

    public final void visitInsn(int i) {
        considerEndOfExceptionTable();
        onVisitInsn(i);
    }

    /* access modifiers changed from: protected */
    public void onVisitInsn(int i) {
        super.visitInsn(i);
    }
}

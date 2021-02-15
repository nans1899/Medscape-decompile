package net.bytebuddy.utility.visitor;

import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.utility.OpenedClassReader;

public class LineNumberPrependingMethodVisitor extends ExceptionTableSensitiveMethodVisitor {
    private boolean prependLineNumber = true;
    private final Label startOfMethod = new Label();

    public LineNumberPrependingMethodVisitor(MethodVisitor methodVisitor) {
        super(OpenedClassReader.ASM_API, methodVisitor);
    }

    /* access modifiers changed from: protected */
    public void onAfterExceptionTable() {
        super.visitLabel(this.startOfMethod);
    }

    public void visitLineNumber(int i, Label label) {
        if (this.prependLineNumber) {
            label = this.startOfMethod;
            this.prependLineNumber = false;
        }
        super.visitLineNumber(i, label);
    }
}

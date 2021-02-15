package net.bytebuddy.jar.asm.signature;

public abstract class SignatureVisitor {
    public static final char EXTENDS = '+';
    public static final char INSTANCEOF = '=';
    public static final char SUPER = '-';
    protected final int api;

    public SignatureVisitor visitArrayType() {
        return this;
    }

    public void visitBaseType(char c) {
    }

    public SignatureVisitor visitClassBound() {
        return this;
    }

    public void visitClassType(String str) {
    }

    public void visitEnd() {
    }

    public SignatureVisitor visitExceptionType() {
        return this;
    }

    public void visitFormalTypeParameter(String str) {
    }

    public void visitInnerClassType(String str) {
    }

    public SignatureVisitor visitInterface() {
        return this;
    }

    public SignatureVisitor visitInterfaceBound() {
        return this;
    }

    public SignatureVisitor visitParameterType() {
        return this;
    }

    public SignatureVisitor visitReturnType() {
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        return this;
    }

    public SignatureVisitor visitTypeArgument(char c) {
        return this;
    }

    public void visitTypeArgument() {
    }

    public void visitTypeVariable(String str) {
    }

    public SignatureVisitor(int i) {
        if (i == 393216 || i == 327680 || i == 262144 || i == 458752) {
            this.api = i;
            return;
        }
        throw new IllegalArgumentException();
    }
}

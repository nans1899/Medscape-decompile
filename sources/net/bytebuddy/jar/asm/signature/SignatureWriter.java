package net.bytebuddy.jar.asm.signature;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.pool.TypePool;

public class SignatureWriter extends SignatureVisitor {
    private int argumentStack;
    private boolean hasFormals;
    private boolean hasParameters;
    private final StringBuilder stringBuilder = new StringBuilder();

    public SignatureVisitor visitClassBound() {
        return this;
    }

    public SignatureVisitor visitInterface() {
        return this;
    }

    public SignatureWriter() {
        super(Opcodes.ASM7);
    }

    public void visitFormalTypeParameter(String str) {
        if (!this.hasFormals) {
            this.hasFormals = true;
            this.stringBuilder.append('<');
        }
        this.stringBuilder.append(str);
        this.stringBuilder.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
    }

    public SignatureVisitor visitInterfaceBound() {
        this.stringBuilder.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        return this;
    }

    public SignatureVisitor visitSuperclass() {
        endFormals();
        return this;
    }

    public SignatureVisitor visitParameterType() {
        endFormals();
        if (!this.hasParameters) {
            this.hasParameters = true;
            this.stringBuilder.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        }
        return this;
    }

    public SignatureVisitor visitReturnType() {
        endFormals();
        if (!this.hasParameters) {
            this.stringBuilder.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        }
        this.stringBuilder.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        return this;
    }

    public SignatureVisitor visitExceptionType() {
        this.stringBuilder.append('^');
        return this;
    }

    public void visitBaseType(char c) {
        this.stringBuilder.append(c);
    }

    public void visitTypeVariable(String str) {
        this.stringBuilder.append(ASCIIPropertyListParser.DATE_APPLE_DATE_TIME_DELIMITER);
        this.stringBuilder.append(str);
        this.stringBuilder.append(';');
    }

    public SignatureVisitor visitArrayType() {
        this.stringBuilder.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        return this;
    }

    public void visitClassType(String str) {
        this.stringBuilder.append('L');
        this.stringBuilder.append(str);
        this.argumentStack *= 2;
    }

    public void visitInnerClassType(String str) {
        endArguments();
        this.stringBuilder.append('.');
        this.stringBuilder.append(str);
        this.argumentStack *= 2;
    }

    public void visitTypeArgument() {
        int i = this.argumentStack;
        if (i % 2 == 0) {
            this.argumentStack = i | 1;
            this.stringBuilder.append('<');
        }
        this.stringBuilder.append('*');
    }

    public SignatureVisitor visitTypeArgument(char c) {
        int i = this.argumentStack;
        if (i % 2 == 0) {
            this.argumentStack = i | 1;
            this.stringBuilder.append('<');
        }
        if (c != '=') {
            this.stringBuilder.append(c);
        }
        return this;
    }

    public void visitEnd() {
        endArguments();
        this.stringBuilder.append(';');
    }

    public String toString() {
        return this.stringBuilder.toString();
    }

    private void endFormals() {
        if (this.hasFormals) {
            this.hasFormals = false;
            this.stringBuilder.append('>');
        }
    }

    private void endArguments() {
        if (this.argumentStack % 2 == 1) {
            this.stringBuilder.append('>');
        }
        this.argumentStack /= 2;
    }
}

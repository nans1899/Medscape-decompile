package net.bytebuddy.jar.asm.signature;

public class SignatureReader {
    private final String signatureValue;

    public SignatureReader(String str) {
        this.signatureValue = str;
    }

    public void accept(SignatureVisitor signatureVisitor) {
        char charAt;
        String str = this.signatureValue;
        int length = str.length();
        int i = 0;
        if (str.charAt(0) == '<') {
            i = 2;
            do {
                int indexOf = str.indexOf(58, i);
                signatureVisitor.visitFormalTypeParameter(str.substring(i - 1, indexOf));
                int i2 = indexOf + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 == 'L' || charAt2 == '[' || charAt2 == 'T') {
                    i2 = parseType(str, i2, signatureVisitor.visitClassBound());
                }
                while (true) {
                    i = i2 + 1;
                    charAt = str.charAt(i2);
                    if (charAt != ':') {
                        break;
                    }
                    i2 = parseType(str, i, signatureVisitor.visitInterfaceBound());
                }
            } while (charAt != '>');
        }
        if (str.charAt(i) == '(') {
            int i3 = i + 1;
            while (str.charAt(i3) != ')') {
                i3 = parseType(str, i3, signatureVisitor.visitParameterType());
            }
            int parseType = parseType(str, i3 + 1, signatureVisitor.visitReturnType());
            while (parseType < length) {
                parseType = parseType(str, parseType + 1, signatureVisitor.visitExceptionType());
            }
            return;
        }
        int parseType2 = parseType(str, i, signatureVisitor.visitSuperclass());
        while (parseType2 < length) {
            parseType2 = parseType(str, parseType2, signatureVisitor.visitInterface());
        }
    }

    public void acceptType(SignatureVisitor signatureVisitor) {
        parseType(this.signatureValue, 0, signatureVisitor);
    }

    private static int parseType(String str, int i, SignatureVisitor signatureVisitor) {
        int i2 = i + 1;
        char charAt = str.charAt(i);
        if (charAt != 'F') {
            if (charAt == 'L') {
                int i3 = i2;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    int i4 = i2 + 1;
                    char charAt2 = str.charAt(i2);
                    if (charAt2 == '.' || charAt2 == ';') {
                        if (!z) {
                            String substring = str.substring(i3, i4 - 1);
                            if (z2) {
                                signatureVisitor.visitInnerClassType(substring);
                            } else {
                                signatureVisitor.visitClassType(substring);
                            }
                        }
                        if (charAt2 == ';') {
                            signatureVisitor.visitEnd();
                            return i4;
                        }
                        i2 = i4;
                        i3 = i2;
                        z = false;
                        z2 = true;
                    } else if (charAt2 == '<') {
                        String substring2 = str.substring(i3, i4 - 1);
                        if (z2) {
                            signatureVisitor.visitInnerClassType(substring2);
                        } else {
                            signatureVisitor.visitClassType(substring2);
                        }
                        i2 = i4;
                        while (true) {
                            char charAt3 = str.charAt(i2);
                            if (charAt3 == '>') {
                                break;
                            } else if (charAt3 == '*') {
                                i2++;
                                signatureVisitor.visitTypeArgument();
                            } else if (charAt3 == '+' || charAt3 == '-') {
                                i2 = parseType(str, i2 + 1, signatureVisitor.visitTypeArgument(charAt3));
                            } else {
                                i2 = parseType(str, i2, signatureVisitor.visitTypeArgument('='));
                            }
                        }
                        z = true;
                    } else {
                        i2 = i4;
                    }
                }
            } else if (!(charAt == 'V' || charAt == 'I' || charAt == 'J' || charAt == 'S')) {
                if (charAt == 'T') {
                    int indexOf = str.indexOf(59, i2);
                    signatureVisitor.visitTypeVariable(str.substring(i2, indexOf));
                    return indexOf + 1;
                } else if (charAt != 'Z') {
                    if (charAt == '[') {
                        return parseType(str, i2, signatureVisitor.visitArrayType());
                    }
                    switch (charAt) {
                        case 'B':
                        case 'C':
                        case 'D':
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                }
            }
        }
        signatureVisitor.visitBaseType(charAt);
        return i2;
    }
}

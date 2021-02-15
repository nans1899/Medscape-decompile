package net.bytebuddy.jar.asm;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public final class MethodTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 6807380416709738314L;
    private final String className;
    private final int codeSize;
    private final String descriptor;
    private final String methodName;

    public MethodTooLargeException(String str, String str2, String str3, int i) {
        super("Method too large: " + str + "." + str2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str3);
        this.className = str;
        this.methodName = str2;
        this.descriptor = str3;
        this.codeSize = i;
    }

    public String getClassName() {
        return this.className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public int getCodeSize() {
        return this.codeSize;
    }
}

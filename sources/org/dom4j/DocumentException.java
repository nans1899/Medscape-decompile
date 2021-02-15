package org.dom4j;

import java.io.PrintStream;
import java.io.PrintWriter;

public class DocumentException extends Exception {
    private Throwable nestedException;

    public DocumentException() {
        super("Error occurred in DOM4J application.");
    }

    public DocumentException(String str) {
        super(str);
    }

    public DocumentException(Throwable th) {
        super(th.getMessage());
        this.nestedException = th;
    }

    public DocumentException(String str, Throwable th) {
        super(str);
        this.nestedException = th;
    }

    public Throwable getNestedException() {
        return this.nestedException;
    }

    public String getMessage() {
        if (this.nestedException == null) {
            return super.getMessage();
        }
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.getMessage()));
        stringBuffer.append(" Nested exception: ");
        stringBuffer.append(this.nestedException.getMessage());
        return stringBuffer.toString();
    }

    public void printStackTrace() {
        super.printStackTrace();
        if (this.nestedException != null) {
            System.err.print("Nested exception: ");
            this.nestedException.printStackTrace();
        }
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.nestedException != null) {
            printStream.println("Nested exception: ");
            this.nestedException.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.nestedException != null) {
            printWriter.println("Nested exception: ");
            this.nestedException.printStackTrace(printWriter);
        }
    }
}

package org.jaxen.saxpath;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SAXPathException extends Exception {
    private static double javaVersion = 1.4d;
    private static final long serialVersionUID = 4826444568928720706L;
    private Throwable cause;
    private boolean causeSet = false;

    static {
        try {
            javaVersion = Double.valueOf(System.getProperty("java.version").substring(0, 3)).doubleValue();
        } catch (Exception unused) {
        }
    }

    public SAXPathException(String str) {
        super(str);
    }

    public SAXPathException(Throwable th) {
        super(th.getMessage());
        initCause(th);
    }

    public SAXPathException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public Throwable getCause() {
        return this.cause;
    }

    public Throwable initCause(Throwable th) {
        if (this.causeSet) {
            throw new IllegalStateException("Cause cannot be reset");
        } else if (th != this) {
            this.causeSet = true;
            this.cause = th;
            return this;
        } else {
            throw new IllegalArgumentException("Exception cannot be its own cause");
        }
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (javaVersion < 1.4d && getCause() != null) {
            printStream.print("Caused by: ");
            getCause().printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (javaVersion < 1.4d && getCause() != null) {
            printWriter.print("Caused by: ");
            getCause().printStackTrace(printWriter);
        }
    }
}

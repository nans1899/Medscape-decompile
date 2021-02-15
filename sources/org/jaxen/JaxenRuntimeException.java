package org.jaxen;

import java.io.PrintStream;
import java.io.PrintWriter;

public class JaxenRuntimeException extends RuntimeException {
    private static final long serialVersionUID = -930309761511911193L;
    private Throwable cause;
    private boolean causeSet = false;

    public JaxenRuntimeException(Throwable th) {
        super(th.getMessage());
        initCause(th);
    }

    public JaxenRuntimeException(String str) {
        super(str);
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
        if (JaxenException.javaVersion < 1.4d && getCause() != null) {
            printStream.print("Caused by: ");
            getCause().printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (JaxenException.javaVersion < 1.4d && getCause() != null) {
            printWriter.print("Caused by: ");
            getCause().printStackTrace(printWriter);
        }
    }
}

package org.jaxen;

public class FunctionCallException extends JaxenException {
    private static final long serialVersionUID = 7908649612495640943L;

    public FunctionCallException(String str) {
        super(str);
    }

    public FunctionCallException(Throwable th) {
        super(th);
    }

    public FunctionCallException(String str, Exception exc) {
        super(str, exc);
    }

    public Throwable getNestedException() {
        return getCause();
    }
}

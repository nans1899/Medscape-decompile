package org.objenesis;

public class ObjenesisException extends RuntimeException {
    private static final long serialVersionUID = -2677230016262426968L;

    public ObjenesisException(String str) {
        super(str);
    }

    public ObjenesisException(Throwable th) {
        super(th);
    }

    public ObjenesisException(String str, Throwable th) {
        super(str, th);
    }
}

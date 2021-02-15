package org.jaxen;

import org.jaxen.saxpath.SAXPathException;

public class JaxenException extends SAXPathException {
    static double javaVersion = 1.4d;
    private static final long serialVersionUID = 7132891439526672639L;

    static {
        try {
            javaVersion = Double.valueOf(System.getProperty("java.version").substring(0, 3)).doubleValue();
        } catch (RuntimeException unused) {
        }
    }

    public JaxenException(String str) {
        super(str);
    }

    public JaxenException(Throwable th) {
        super(th);
    }

    public JaxenException(String str, Throwable th) {
        super(str, th);
    }
}

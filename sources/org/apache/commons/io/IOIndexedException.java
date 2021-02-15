package org.apache.commons.io;

import java.io.IOException;

public class IOIndexedException extends IOException {
    private static final long serialVersionUID = 1;
    private final int index;

    public IOIndexedException(int i, Throwable th) {
        super(toMessage(i, th), th);
        this.index = i;
    }

    protected static String toMessage(int i, Throwable th) {
        String str = "Null";
        String simpleName = th == null ? str : th.getClass().getSimpleName();
        if (th != null) {
            str = th.getMessage();
        }
        return String.format("%s #%,d: %s", new Object[]{simpleName, Integer.valueOf(i), str});
    }

    public int getIndex() {
        return this.index;
    }
}

package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;

public class ClosedWriter extends Writer {
    public static final ClosedWriter CLOSED_WRITER = new ClosedWriter();

    public void close() throws IOException {
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        throw new IOException("write(" + new String(cArr) + ", " + i + ", " + i2 + ") failed: stream is closed");
    }

    public void flush() throws IOException {
        throw new IOException("flush() failed: stream is closed");
    }
}

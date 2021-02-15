package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;

public class BrokenReader extends Reader {
    private final IOException exception;

    public BrokenReader(IOException iOException) {
        this.exception = iOException;
    }

    public BrokenReader() {
        this(new IOException("Broken reader"));
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        throw this.exception;
    }

    public long skip(long j) throws IOException {
        throw this.exception;
    }

    public boolean ready() throws IOException {
        throw this.exception;
    }

    public void mark(int i) throws IOException {
        throw this.exception;
    }

    public synchronized void reset() throws IOException {
        throw this.exception;
    }

    public void close() throws IOException {
        throw this.exception;
    }
}

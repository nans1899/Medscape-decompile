package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;

public class ClosedReader extends Reader {
    public static final ClosedReader CLOSED_READER = new ClosedReader();

    public void close() throws IOException {
    }

    public int read(char[] cArr, int i, int i2) {
        return -1;
    }
}

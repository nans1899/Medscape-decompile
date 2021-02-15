package org.apache.commons.io.input;

import java.io.Reader;

public class CloseShieldReader extends ProxyReader {
    public CloseShieldReader(Reader reader) {
        super(reader);
    }

    public void close() {
        this.in = ClosedReader.CLOSED_READER;
    }
}

package org.apache.commons.io.output;

import java.io.Writer;

public class CloseShieldWriter extends ProxyWriter {
    public CloseShieldWriter(Writer writer) {
        super(writer);
    }

    public void close() {
        this.out = ClosedWriter.CLOSED_WRITER;
    }
}

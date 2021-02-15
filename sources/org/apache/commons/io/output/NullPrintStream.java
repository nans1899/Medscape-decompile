package org.apache.commons.io.output;

import java.io.PrintStream;

public class NullPrintStream extends PrintStream {
    public static final NullPrintStream NULL_PRINT_STREAM = new NullPrintStream();

    public NullPrintStream() {
        super(NullOutputStream.NULL_OUTPUT_STREAM);
    }
}

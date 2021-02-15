package org.apache.commons.io.output;

import java.io.IOException;

public class NullAppendable implements Appendable {
    public static final NullAppendable INSTANCE = new NullAppendable();

    public Appendable append(char c) throws IOException {
        return this;
    }

    public Appendable append(CharSequence charSequence) throws IOException {
        return this;
    }

    public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
        return this;
    }

    private NullAppendable() {
    }
}

package org.simpleframework.xml.stream;

import java.io.IOException;
import java.io.Writer;

class OutputBuffer {
    private StringBuilder text = new StringBuilder();

    public void append(char c) {
        this.text.append(c);
    }

    public void append(String str) {
        this.text.append(str);
    }

    public void append(char[] cArr) {
        this.text.append(cArr, 0, cArr.length);
    }

    public void append(char[] cArr, int i, int i2) {
        this.text.append(cArr, i, i2);
    }

    public void append(String str, int i, int i2) {
        this.text.append(str, i, i2);
    }

    public void write(Writer writer) throws IOException {
        writer.append(this.text);
    }

    public void clear() {
        this.text.setLength(0);
    }
}

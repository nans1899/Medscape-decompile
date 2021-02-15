package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public abstract class AbstractCharacterFilterReader extends FilterReader {
    /* access modifiers changed from: protected */
    public abstract boolean filter(int i);

    protected AbstractCharacterFilterReader(Reader reader) {
        super(reader);
    }

    public int read() throws IOException {
        int read;
        do {
            read = this.in.read();
        } while (filter(read));
        return read;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        int read = super.read(cArr, i, i2);
        if (read == -1) {
            return -1;
        }
        int i3 = i - 1;
        for (int i4 = i; i4 < i + read; i4++) {
            if (!filter(cArr[i4]) && (i3 = i3 + 1) < i4) {
                cArr[i3] = cArr[i4];
            }
        }
        return (i3 - i) + 1;
    }
}

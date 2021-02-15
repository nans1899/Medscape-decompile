package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SequenceReader extends Reader {
    private Reader reader;
    private Iterator<? extends Reader> readers;

    public SequenceReader(Iterable<? extends Reader> iterable) {
        this.readers = ((Iterable) Objects.requireNonNull(iterable, "readers")).iterator();
        this.reader = nextReader();
    }

    public SequenceReader(Reader... readerArr) {
        this((Iterable<? extends Reader>) Arrays.asList(readerArr));
    }

    public void close() throws IOException {
        this.readers = null;
        this.reader = null;
    }

    private Reader nextReader() {
        if (this.readers.hasNext()) {
            return (Reader) this.readers.next();
        }
        return null;
    }

    public int read() throws IOException {
        int i = -1;
        while (true) {
            Reader reader2 = this.reader;
            if (reader2 == null || (i = reader2.read()) != -1) {
                return i;
            }
            this.reader = nextReader();
        }
        return i;
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        Objects.requireNonNull(cArr, "cbuf");
        if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + i + ", length=" + i2);
        }
        int i3 = 0;
        while (true) {
            Reader reader2 = this.reader;
            if (reader2 == null) {
                break;
            }
            int read = reader2.read(cArr, i, i2);
            if (read == -1) {
                this.reader = nextReader();
            } else {
                i3 += read;
                i += read;
                i2 -= read;
                if (i2 <= 0) {
                    break;
                }
            }
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }
}

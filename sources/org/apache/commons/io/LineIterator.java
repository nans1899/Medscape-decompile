package org.apache.commons.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LineIterator implements Iterator<String>, Closeable {
    private final BufferedReader bufferedReader;
    private String cachedLine;
    private boolean finished = false;

    /* access modifiers changed from: protected */
    public boolean isValidLine(String str) {
        return true;
    }

    public LineIterator(Reader reader) throws IllegalArgumentException {
        if (reader == null) {
            throw new IllegalArgumentException("Reader must not be null");
        } else if (reader instanceof BufferedReader) {
            this.bufferedReader = (BufferedReader) reader;
        } else {
            this.bufferedReader = new BufferedReader(reader);
        }
    }

    public boolean hasNext() {
        String readLine;
        if (this.cachedLine != null) {
            return true;
        }
        if (this.finished) {
            return false;
        }
        do {
            try {
                readLine = this.bufferedReader.readLine();
                if (readLine == null) {
                    this.finished = true;
                    return false;
                }
            } catch (IOException e) {
                IOUtils.closeQuietly(this, new Consumer(e) {
                    public final /* synthetic */ IOException f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void accept(Object obj) {
                        this.f$0.addSuppressed((IOException) obj);
                    }
                });
                throw new IllegalStateException(e);
            }
        } while (!isValidLine(readLine));
        this.cachedLine = readLine;
        return true;
    }

    public String next() {
        return nextLine();
    }

    public String nextLine() {
        if (hasNext()) {
            String str = this.cachedLine;
            this.cachedLine = null;
            return str;
        }
        throw new NoSuchElementException("No more lines");
    }

    public void close() throws IOException {
        this.finished = true;
        this.cachedLine = null;
        IOUtils.close((Closeable) this.bufferedReader);
    }

    public void remove() {
        throw new UnsupportedOperationException("Remove unsupported on LineIterator");
    }

    @Deprecated
    public static void closeQuietly(LineIterator lineIterator) {
        IOUtils.closeQuietly((Closeable) lineIterator);
    }
}

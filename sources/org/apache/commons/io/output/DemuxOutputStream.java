package org.apache.commons.io.output;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

public class DemuxOutputStream extends OutputStream {
    private final InheritableThreadLocal<OutputStream> outputStreamThreadLocal = new InheritableThreadLocal<>();

    public OutputStream bindStream(OutputStream outputStream) {
        OutputStream outputStream2 = (OutputStream) this.outputStreamThreadLocal.get();
        this.outputStreamThreadLocal.set(outputStream);
        return outputStream2;
    }

    public void close() throws IOException {
        IOUtils.close((Closeable) this.outputStreamThreadLocal.get());
    }

    public void flush() throws IOException {
        OutputStream outputStream = (OutputStream) this.outputStreamThreadLocal.get();
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    public void write(int i) throws IOException {
        OutputStream outputStream = (OutputStream) this.outputStreamThreadLocal.get();
        if (outputStream != null) {
            outputStream.write(i);
        }
    }
}

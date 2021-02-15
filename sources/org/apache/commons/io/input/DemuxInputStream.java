package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class DemuxInputStream extends InputStream {
    private final InheritableThreadLocal<InputStream> inputStream = new InheritableThreadLocal<>();

    public InputStream bindStream(InputStream inputStream2) {
        InputStream inputStream3 = (InputStream) this.inputStream.get();
        this.inputStream.set(inputStream2);
        return inputStream3;
    }

    public void close() throws IOException {
        IOUtils.close((Closeable) this.inputStream.get());
    }

    public int read() throws IOException {
        InputStream inputStream2 = (InputStream) this.inputStream.get();
        if (inputStream2 != null) {
            return inputStream2.read();
        }
        return -1;
    }
}

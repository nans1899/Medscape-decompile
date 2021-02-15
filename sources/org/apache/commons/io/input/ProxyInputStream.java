package org.apache.commons.io.input;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOConsumer;

public abstract class ProxyInputStream extends FilterInputStream {
    /* access modifiers changed from: protected */
    public void afterRead(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void beforeRead(int i) throws IOException {
    }

    public ProxyInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public int read() throws IOException {
        int i = 1;
        try {
            beforeRead(1);
            int read = this.in.read();
            if (read == -1) {
                i = -1;
            }
            afterRead(i);
            return read;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    public int read(byte[] bArr) throws IOException {
        try {
            beforeRead(IOUtils.length(bArr));
            int read = this.in.read(bArr);
            afterRead(read);
            return read;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            beforeRead(i2);
            int read = this.in.read(bArr, i, i2);
            afterRead(read);
            return read;
        } catch (IOException e) {
            handleIOException(e);
            return -1;
        }
    }

    public long skip(long j) throws IOException {
        try {
            return this.in.skip(j);
        } catch (IOException e) {
            handleIOException(e);
            return 0;
        }
    }

    public int available() throws IOException {
        try {
            return super.available();
        } catch (IOException e) {
            handleIOException(e);
            return 0;
        }
    }

    public void close() throws IOException {
        IOUtils.close(this.in, new IOConsumer() {
            public final void accept(Object obj) {
                ProxyInputStream.this.handleIOException((IOException) obj);
            }

            public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer) {
                return IOConsumer.CC.$default$andThen(this, iOConsumer);
            }
        });
    }

    public synchronized void mark(int i) {
        this.in.mark(i);
    }

    public synchronized void reset() throws IOException {
        try {
            this.in.reset();
        } catch (IOException e) {
            handleIOException(e);
        }
        return;
    }

    public boolean markSupported() {
        return this.in.markSupported();
    }

    /* access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw iOException;
    }
}

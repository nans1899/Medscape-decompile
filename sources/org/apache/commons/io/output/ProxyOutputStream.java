package org.apache.commons.io.output;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOConsumer;

public class ProxyOutputStream extends FilterOutputStream {
    /* access modifiers changed from: protected */
    public void afterWrite(int i) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void beforeWrite(int i) throws IOException {
    }

    public ProxyOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void write(int i) throws IOException {
        try {
            beforeWrite(1);
            this.out.write(i);
            afterWrite(1);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void write(byte[] bArr) throws IOException {
        try {
            int length = IOUtils.length(bArr);
            beforeWrite(length);
            this.out.write(bArr);
            afterWrite(length);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            beforeWrite(i2);
            this.out.write(bArr, i, i2);
            afterWrite(i2);
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void flush() throws IOException {
        try {
            this.out.flush();
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    public void close() throws IOException {
        IOUtils.close(this.out, new IOConsumer() {
            public final void accept(Object obj) {
                ProxyOutputStream.this.handleIOException((IOException) obj);
            }

            public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer) {
                return IOConsumer.CC.$default$andThen(this, iOConsumer);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw iOException;
    }
}

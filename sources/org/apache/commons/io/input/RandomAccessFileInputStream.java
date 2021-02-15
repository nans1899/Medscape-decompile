package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Objects;

public class RandomAccessFileInputStream extends InputStream {
    private final boolean closeOnClose;
    private final RandomAccessFile randomAccessFile;

    public RandomAccessFileInputStream(RandomAccessFile randomAccessFile2) {
        this(randomAccessFile2, false);
    }

    public RandomAccessFileInputStream(RandomAccessFile randomAccessFile2, boolean z) {
        this.randomAccessFile = (RandomAccessFile) Objects.requireNonNull(randomAccessFile2, "file");
        this.closeOnClose = z;
    }

    public int available() throws IOException {
        long availableLong = availableLong();
        if (availableLong > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) availableLong;
    }

    public long availableLong() throws IOException {
        return this.randomAccessFile.length() - this.randomAccessFile.getFilePointer();
    }

    public void close() throws IOException {
        super.close();
        if (this.closeOnClose) {
            this.randomAccessFile.close();
        }
    }

    public RandomAccessFile getRandomAccessFile() {
        return this.randomAccessFile;
    }

    public boolean isCloseOnClose() {
        return this.closeOnClose;
    }

    public int read() throws IOException {
        return this.randomAccessFile.read();
    }

    public int read(byte[] bArr) throws IOException {
        return this.randomAccessFile.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.randomAccessFile.read(bArr, i, i2);
    }

    private void seek(long j) throws IOException {
        this.randomAccessFile.seek(j);
    }

    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0;
        }
        long filePointer = this.randomAccessFile.getFilePointer();
        long length = this.randomAccessFile.length();
        if (filePointer >= length) {
            return 0;
        }
        long j2 = j + filePointer;
        if (j2 > length) {
            j2 = length - 1;
        }
        if (j2 > 0) {
            seek(j2);
        }
        return this.randomAccessFile.getFilePointer() - filePointer;
    }
}

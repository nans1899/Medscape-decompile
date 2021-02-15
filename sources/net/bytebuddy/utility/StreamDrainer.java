package net.bytebuddy.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

@HashCodeAndEqualsPlugin.Enhance
public class StreamDrainer {
    public static final StreamDrainer DEFAULT = new StreamDrainer();
    public static final int DEFAULT_BUFFER_SIZE = 1024;
    private static final int END_OF_STREAM = -1;
    private static final int FROM_BEGINNING = 0;
    private final int bufferSize;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.bufferSize == ((StreamDrainer) obj).bufferSize;
    }

    public int hashCode() {
        return 527 + this.bufferSize;
    }

    public StreamDrainer() {
        this(1024);
    }

    public StreamDrainer(int i) {
        this.bufferSize = i;
    }

    public byte[] drain(InputStream inputStream) throws IOException {
        int read;
        ArrayList<byte[]> arrayList = new ArrayList<>();
        byte[] bArr = new byte[this.bufferSize];
        int i = 0;
        do {
            read = inputStream.read(bArr, i, this.bufferSize - i);
            i += read > 0 ? read : 0;
            if (i == this.bufferSize) {
                arrayList.add(bArr);
                bArr = new byte[this.bufferSize];
                i = 0;
            }
        } while (read != -1);
        byte[] bArr2 = new byte[((arrayList.size() * this.bufferSize) + i)];
        int i2 = 0;
        for (byte[] arraycopy : arrayList) {
            int i3 = this.bufferSize;
            System.arraycopy(arraycopy, 0, bArr2, i2 * i3, i3);
            i2++;
        }
        System.arraycopy(bArr, 0, bArr2, i2 * this.bufferSize, i);
        return bArr2;
    }
}

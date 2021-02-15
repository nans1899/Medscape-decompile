package mnetinternal;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.common.base.Ascii;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.bytebuddy.pool.TypePool;

public class o {
    private static final Logger c = Logger.getLogger(o.class.getName());
    private static final byte[] d = new byte[4096];
    final RandomAccessFile a;
    int b;
    private int e;
    private a f;
    private a g;
    private final byte[] h = new byte[16];

    public interface c {
        void a(InputStream inputStream, int i);
    }

    public o(File file) {
        if (!file.exists()) {
            a(file);
        }
        this.a = b(file);
        f();
    }

    private static void b(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(byte[] bArr, int... iArr) {
        int i = 0;
        for (int b2 : iArr) {
            b(bArr, i, b2);
            i += 4;
        }
    }

    private static int a(byte[] bArr, int i) {
        return ((bArr[i] & 255) << Ascii.CAN) + ((bArr[i + 1] & 255) << Ascii.DLE) + ((bArr[i + 2] & 255) << 8) + (bArr[i + 3] & 255);
    }

    private void f() {
        this.a.seek(0);
        this.a.readFully(this.h);
        int a2 = a(this.h, 0);
        this.b = a2;
        if (((long) a2) > this.a.length()) {
            throw new IOException("File is truncated. Expected length: " + this.b + ", Actual length: " + this.a.length());
        } else if (this.b != 0) {
            this.e = a(this.h, 4);
            int a3 = a(this.h, 8);
            int a4 = a(this.h, 12);
            this.f = a(a3);
            this.g = a(a4);
        } else {
            throw new IOException("File is corrupt; length stored in header is 0.");
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        a(this.h, i, i2, i3, i4);
        this.a.seek(0);
        this.a.write(this.h);
    }

    private a a(int i) {
        if (i == 0) {
            return a.a;
        }
        b(i, this.h, 0, 4);
        return new a(i, a(this.h, 0));
    }

    /* JADX INFO: finally extract failed */
    private static void a(File file) {
        File file2 = new File(file.getPath() + ".tmp");
        RandomAccessFile b2 = b(file2);
        try {
            b2.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            b2.seek(0);
            byte[] bArr = new byte[16];
            a(bArr, 4096, 0, 0, 0);
            b2.write(bArr);
            b2.close();
            if (!file2.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            b2.close();
            throw th;
        }
    }

    private static RandomAccessFile b(File file) {
        return new RandomAccessFile(file, "rwd");
    }

    /* access modifiers changed from: private */
    public int b(int i) {
        int i2 = this.b;
        return i < i2 ? i : (i + 16) - i2;
    }

    private void a(int i, byte[] bArr, int i2, int i3) {
        int b2 = b(i);
        int i4 = b2 + i3;
        int i5 = this.b;
        if (i4 <= i5) {
            this.a.seek((long) b2);
            this.a.write(bArr, i2, i3);
            return;
        }
        int i6 = i5 - b2;
        this.a.seek((long) b2);
        this.a.write(bArr, i2, i6);
        this.a.seek(16);
        this.a.write(bArr, i2 + i6, i3 - i6);
    }

    private void a(int i, int i2) {
        while (i2 > 0) {
            int min = Math.min(i2, d.length);
            a(i, d, 0, min);
            i2 -= min;
            i += min;
        }
    }

    /* access modifiers changed from: private */
    public void b(int i, byte[] bArr, int i2, int i3) {
        int b2 = b(i);
        int i4 = b2 + i3;
        int i5 = this.b;
        if (i4 <= i5) {
            this.a.seek((long) b2);
            this.a.readFully(bArr, i2, i3);
            return;
        }
        int i6 = i5 - b2;
        this.a.seek((long) b2);
        this.a.readFully(bArr, i2, i6);
        this.a.seek(16);
        this.a.readFully(bArr, i2 + i6, i3 - i6);
    }

    public synchronized void a(byte[] bArr, int i, int i2) {
        int i3;
        b(bArr, "buffer");
        if ((i | i2) < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        }
        c(i2);
        boolean a2 = a();
        if (a2) {
            i3 = 16;
        } else {
            i3 = b(this.g.b + 4 + this.g.c);
        }
        a aVar = new a(i3, i2);
        b(this.h, 0, i2);
        a(aVar.b, this.h, 0, 4);
        a(aVar.b + 4, bArr, i, i2);
        a(this.b, this.e + 1, a2 ? aVar.b : this.f.b, aVar.b);
        this.g = aVar;
        this.e++;
        if (a2) {
            this.f = aVar;
        }
    }

    private int g() {
        if (this.e == 0) {
            return 16;
        }
        if (this.g.b >= this.f.b) {
            return (this.g.b - this.f.b) + 4 + this.g.c + 16;
        }
        return (((this.g.b + 4) + this.g.c) + this.b) - this.f.b;
    }

    private int h() {
        return this.b - g();
    }

    public synchronized boolean a() {
        return this.e == 0;
    }

    private void c(int i) {
        int i2 = i + 4;
        int h2 = h();
        if (h2 < i2) {
            int i3 = this.b;
            do {
                h2 += i3;
                i3 <<= 1;
            } while (h2 < i2);
            d(i3);
            int b2 = b(this.g.b + 4 + this.g.c);
            if (b2 <= this.f.b) {
                FileChannel channel = this.a.getChannel();
                channel.position((long) this.b);
                int i4 = b2 - 16;
                long j = (long) i4;
                if (channel.transferTo(16, j, channel) == j) {
                    a(16, i4);
                } else {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.g.b < this.f.b) {
                int i5 = (this.b + this.g.b) - 16;
                a(i3, this.e, this.f.b, i5);
                this.g = new a(i5, this.g.c);
            } else {
                a(i3, this.e, this.f.b, this.g.b);
            }
            this.b = i3;
        }
    }

    private void d(int i) {
        this.a.setLength((long) i);
        this.a.getChannel().force(true);
    }

    public synchronized byte[] b() {
        if (a()) {
            return null;
        }
        int i = this.f.c;
        byte[] bArr = new byte[i];
        b(this.f.b + 4, bArr, 0, i);
        return bArr;
    }

    public synchronized void a(c cVar) {
        int i = this.f.b;
        for (int i2 = 0; i2 < this.e; i2++) {
            a a2 = a(i);
            cVar.a(new b(a2), a2.c);
            i = b(a2.b + 4 + a2.c);
        }
    }

    /* access modifiers changed from: private */
    public static <T> T b(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    private final class b extends InputStream {
        private int b;
        private int c;

        private b(a aVar) {
            this.b = o.this.b(aVar.b + 4);
            this.c = aVar.c;
        }

        public int read(byte[] bArr, int i, int i2) {
            Object unused = o.b(bArr, "buffer");
            if ((i | i2) < 0 || i2 > bArr.length - i) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = this.c;
            if (i3 <= 0) {
                return -1;
            }
            if (i2 > i3) {
                i2 = i3;
            }
            o.this.b(this.b, bArr, i, i2);
            this.b = o.this.b(this.b + i2);
            this.c -= i2;
            return i2;
        }

        public int read() {
            if (this.c == 0) {
                return -1;
            }
            o.this.a.seek((long) this.b);
            int read = o.this.a.read();
            this.b = o.this.b(this.b + 1);
            this.c--;
            return read;
        }
    }

    public synchronized int c() {
        return this.e;
    }

    public synchronized void d() {
        if (a()) {
            throw new NoSuchElementException();
        } else if (this.e == 1) {
            e();
        } else {
            int i = this.f.c + 4;
            a(this.f.b, i);
            int b2 = b(this.f.b + i);
            b(b2, this.h, 0, 4);
            int a2 = a(this.h, 0);
            a(this.b, this.e - 1, b2, this.g.b);
            this.e--;
            this.f = new a(b2, a2);
        }
    }

    public synchronized void e() {
        this.a.seek(0);
        this.a.write(d);
        a(4096, 0, 0, 0);
        this.e = 0;
        this.f = a.a;
        this.g = a.a;
        if (this.b > 4096) {
            d(4096);
        }
        this.b = 4096;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        sb.append("fileLength=");
        sb.append(this.b);
        sb.append(", size=");
        sb.append(this.e);
        sb.append(", first=");
        sb.append(this.f);
        sb.append(", last=");
        sb.append(this.g);
        sb.append(", element lengths=[");
        try {
            a((c) new c() {
                boolean a = true;

                public void a(InputStream inputStream, int i) {
                    if (this.a) {
                        this.a = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(i);
                }
            });
        } catch (IOException e2) {
            c.log(Level.WARNING, "read error", e2);
        }
        sb.append("]]");
        return sb.toString();
    }

    static class a {
        static final a a = new a(0, 0);
        final int b;
        final int c;

        a(int i, int i2) {
            this.b = i;
            this.c = i2;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.b + ", length = " + this.c + "]";
        }
    }
}

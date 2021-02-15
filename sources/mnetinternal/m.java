package mnetinternal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import mnetinternal.n;
import mnetinternal.o;

public class m<T> implements n<T> {
    private final o a;
    private final b b = new b();
    private final File c;
    /* access modifiers changed from: private */
    public final a<T> d;
    private n.a<T> e;

    public interface a<T> {
        T a(byte[] bArr);

        void a(T t, OutputStream outputStream);
    }

    public m(File file, a<T> aVar) {
        this.c = file;
        this.d = aVar;
        this.a = new o(file);
    }

    public int a() {
        return this.a.c();
    }

    public final void a(T t) {
        try {
            this.b.reset();
            this.d.a(t, this.b);
            this.a.a(this.b.a(), 0, this.b.size());
            if (this.e != null) {
                this.e.a(this, t);
            }
        } catch (IOException e2) {
            throw new l("Failed to add entry.", e2, this.c);
        }
    }

    public T b() {
        try {
            byte[] b2 = this.a.b();
            if (b2 == null) {
                return null;
            }
            return this.d.a(b2);
        } catch (IOException e2) {
            throw new l("Failed to peek.", e2, this.c);
        }
    }

    public final void c() {
        try {
            this.a.d();
            if (this.e != null) {
                this.e.a(this);
            }
        } catch (IOException e2) {
            throw new l("Failed to remove.", e2, this.c);
        }
    }

    public void a(final n.a<T> aVar) {
        if (aVar != null) {
            try {
                this.a.a((o.c) new o.c() {
                    public void a(InputStream inputStream, int i) {
                        byte[] bArr = new byte[i];
                        inputStream.read(bArr, 0, i);
                        n.a aVar = aVar;
                        m mVar = m.this;
                        aVar.a(mVar, mVar.d.a(bArr));
                    }
                });
            } catch (IOException e2) {
                throw new l("Unable to iterate over QueueFile contents.", e2, this.c);
            }
        }
        this.e = aVar;
    }

    private static class b extends ByteArrayOutputStream {
        public byte[] a() {
            return this.buf;
        }
    }
}

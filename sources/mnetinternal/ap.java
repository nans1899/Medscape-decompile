package mnetinternal;

import com.mnet.gson.n;
import com.mnet.gson.p;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public abstract class ap<T> {
    /* access modifiers changed from: protected */
    public abstract T b(InputStream inputStream);

    public T c(InputStream inputStream) {
        return b(inputStream);
    }

    /* access modifiers changed from: protected */
    public n d(InputStream inputStream) {
        try {
            return (n) new p().a((Reader) new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } finally {
            da.a((Closeable) inputStream);
        }
    }
}

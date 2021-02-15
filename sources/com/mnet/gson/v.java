package com.mnet.gson;

import com.mnet.gson.internal.bind.c;
import com.mnet.gson.internal.bind.d;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public abstract class v<T> {
    public abstract T read(h hVar);

    public abstract void write(j jVar, T t);

    public final void toJson(Writer writer, T t) {
        write(new j(writer), t);
    }

    public final v<T> nullSafe() {
        return new v<T>() {
            public void write(j jVar, T t) {
                if (t == null) {
                    jVar.f();
                } else {
                    v.this.write(jVar, t);
                }
            }

            public T read(h hVar) {
                if (hVar.f() != i.NULL) {
                    return v.this.read(hVar);
                }
                hVar.j();
                return null;
            }
        };
    }

    public final String toJson(T t) {
        StringWriter stringWriter = new StringWriter();
        try {
            toJson(stringWriter, t);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final k toJsonTree(T t) {
        try {
            d dVar = new d();
            write(dVar, t);
            return dVar.a();
        } catch (IOException e) {
            throw new l((Throwable) e);
        }
    }

    public final T fromJson(Reader reader) {
        return read(new h(reader));
    }

    public final T fromJson(String str) {
        return fromJson((Reader) new StringReader(str));
    }

    public final T fromJsonTree(k kVar) {
        try {
            return read(new c(kVar));
        } catch (IOException e) {
            throw new l((Throwable) e);
        }
    }
}

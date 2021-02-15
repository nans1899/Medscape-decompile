package com.mnet.gson.internal.bind;

import com.mnet.gson.h;
import com.mnet.gson.k;
import com.mnet.gson.m;
import com.mnet.gson.n;
import com.mnet.gson.q;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import mnetinternal.j;

public final class d extends j {
    private static final Writer a = new Writer() {
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    };
    private static final q b = new q("closed");
    private final List<k> c = new ArrayList();
    private String d;
    private k e = m.a;

    public void flush() {
    }

    public d() {
        super(a);
    }

    public k a() {
        if (this.c.isEmpty()) {
            return this.e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.c);
    }

    private k j() {
        List<k> list = this.c;
        return list.get(list.size() - 1);
    }

    private void a(k kVar) {
        if (this.d != null) {
            if (!kVar.l() || i()) {
                ((n) j()).a(this.d, kVar);
            }
            this.d = null;
        } else if (this.c.isEmpty()) {
            this.e = kVar;
        } else {
            k j = j();
            if (j instanceof h) {
                ((h) j).a(kVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public j b() {
        h hVar = new h();
        a((k) hVar);
        this.c.add(hVar);
        return this;
    }

    public j c() {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof h) {
            List<k> list = this.c;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public j d() {
        n nVar = new n();
        a((k) nVar);
        this.c.add(nVar);
        return this;
    }

    public j e() {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof n) {
            List<k> list = this.c;
            list.remove(list.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public j a(String str) {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof n) {
            this.d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public j b(String str) {
        if (str == null) {
            return f();
        }
        a((k) new q(str));
        return this;
    }

    public j f() {
        a((k) m.a);
        return this;
    }

    public j a(boolean z) {
        a((k) new q(Boolean.valueOf(z)));
        return this;
    }

    public j a(Boolean bool) {
        if (bool == null) {
            return f();
        }
        a((k) new q(bool));
        return this;
    }

    public j a(double d2) {
        if (g() || (!Double.isNaN(d2) && !Double.isInfinite(d2))) {
            a((k) new q((Number) Double.valueOf(d2)));
            return this;
        }
        throw new IllegalArgumentException("JSON forbids NaN and infinities: " + d2);
    }

    public j a(long j) {
        a((k) new q((Number) Long.valueOf(j)));
        return this;
    }

    public j a(Number number) {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        a((k) new q(number));
        return this;
    }

    public void close() {
        if (this.c.isEmpty()) {
            this.c.add(b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}

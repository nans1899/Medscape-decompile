package com.mnet.gson;

import com.mnet.gson.internal.i;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import mnetinternal.j;

public abstract class k {
    public boolean i() {
        return this instanceof h;
    }

    public boolean j() {
        return this instanceof n;
    }

    public boolean k() {
        return this instanceof q;
    }

    public boolean l() {
        return this instanceof m;
    }

    public n m() {
        if (j()) {
            return (n) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public h n() {
        if (i()) {
            return (h) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public q o() {
        if (k()) {
            return (q) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    public m p() {
        if (l()) {
            return (m) this;
        }
        throw new IllegalStateException("This is not a JSON Null.");
    }

    public boolean h() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* access modifiers changed from: package-private */
    public Boolean q() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int g() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public BigDecimal e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            j jVar = new j(stringWriter);
            jVar.b(true);
            i.a(this, jVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}

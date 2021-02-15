package com.mnet.gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class h extends k implements Iterable<k> {
    private final List<k> a = new ArrayList();

    public void a(k kVar) {
        if (kVar == null) {
            kVar = m.a;
        }
        this.a.add(kVar);
    }

    public int a() {
        return this.a.size();
    }

    public Iterator<k> iterator() {
        return this.a.iterator();
    }

    public k a(int i) {
        return this.a.get(i);
    }

    public Number b() {
        if (this.a.size() == 1) {
            return this.a.get(0).b();
        }
        throw new IllegalStateException();
    }

    public String c() {
        if (this.a.size() == 1) {
            return this.a.get(0).c();
        }
        throw new IllegalStateException();
    }

    public double d() {
        if (this.a.size() == 1) {
            return this.a.get(0).d();
        }
        throw new IllegalStateException();
    }

    public BigDecimal e() {
        if (this.a.size() == 1) {
            return this.a.get(0).e();
        }
        throw new IllegalStateException();
    }

    public long f() {
        if (this.a.size() == 1) {
            return this.a.get(0).f();
        }
        throw new IllegalStateException();
    }

    public int g() {
        if (this.a.size() == 1) {
            return this.a.get(0).g();
        }
        throw new IllegalStateException();
    }

    public boolean h() {
        if (this.a.size() == 1) {
            return this.a.get(0).h();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof h) && ((h) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}

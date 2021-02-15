package com.mnet.gson.internal.bind;

import com.mnet.gson.k;
import com.mnet.gson.m;
import com.mnet.gson.n;
import com.mnet.gson.q;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;
import mnetinternal.h;
import mnetinternal.i;
import net.bytebuddy.pool.TypePool;

public final class c extends h {
    private static final Reader b = new Reader() {
        public int read(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void close() {
            throw new AssertionError();
        }
    };
    private static final Object c = new Object();
    private Object[] d = new Object[32];
    private int e = 0;
    private String[] f = new String[32];
    private int[] g = new int[32];

    public c(k kVar) {
        super(b);
        a((Object) kVar);
    }

    public void a() {
        a(i.BEGIN_ARRAY);
        a((Object) ((com.mnet.gson.h) s()).iterator());
        this.g[this.e - 1] = 0;
    }

    public void b() {
        a(i.END_ARRAY);
        t();
        t();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public void c() {
        a(i.BEGIN_OBJECT);
        a((Object) ((n) s()).a().iterator());
    }

    public void d() {
        a(i.END_OBJECT);
        t();
        t();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public boolean e() {
        i f2 = f();
        return (f2 == i.END_OBJECT || f2 == i.END_ARRAY) ? false : true;
    }

    public i f() {
        if (this.e == 0) {
            return i.END_DOCUMENT;
        }
        Object s = s();
        if (s instanceof Iterator) {
            boolean z = this.d[this.e - 2] instanceof n;
            Iterator it = (Iterator) s;
            if (!it.hasNext()) {
                return z ? i.END_OBJECT : i.END_ARRAY;
            }
            if (z) {
                return i.NAME;
            }
            a(it.next());
            return f();
        } else if (s instanceof n) {
            return i.BEGIN_OBJECT;
        } else {
            if (s instanceof com.mnet.gson.h) {
                return i.BEGIN_ARRAY;
            }
            if (s instanceof q) {
                q qVar = (q) s;
                if (qVar.s()) {
                    return i.STRING;
                }
                if (qVar.a()) {
                    return i.BOOLEAN;
                }
                if (qVar.r()) {
                    return i.NUMBER;
                }
                throw new AssertionError();
            } else if (s instanceof m) {
                return i.NULL;
            } else {
                if (s == c) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    private Object s() {
        return this.d[this.e - 1];
    }

    private Object t() {
        Object[] objArr = this.d;
        int i = this.e - 1;
        this.e = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    private void a(i iVar) {
        if (f() != iVar) {
            throw new IllegalStateException("Expected " + iVar + " but was " + f() + u());
        }
    }

    public String g() {
        a(i.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) s()).next();
        String str = (String) entry.getKey();
        this.f[this.e - 1] = str;
        a(entry.getValue());
        return str;
    }

    public String h() {
        i f2 = f();
        if (f2 == i.STRING || f2 == i.NUMBER) {
            String c2 = ((q) t()).c();
            int i = this.e;
            if (i > 0) {
                int[] iArr = this.g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return c2;
        }
        throw new IllegalStateException("Expected " + i.STRING + " but was " + f2 + u());
    }

    public boolean i() {
        a(i.BOOLEAN);
        boolean h = ((q) t()).h();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return h;
    }

    public void j() {
        a(i.NULL);
        t();
        int i = this.e;
        if (i > 0) {
            int[] iArr = this.g;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    public double k() {
        i f2 = f();
        if (f2 == i.NUMBER || f2 == i.STRING) {
            double d2 = ((q) s()).d();
            if (q() || (!Double.isNaN(d2) && !Double.isInfinite(d2))) {
                t();
                int i = this.e;
                if (i > 0) {
                    int[] iArr = this.g;
                    int i2 = i - 1;
                    iArr[i2] = iArr[i2] + 1;
                }
                return d2;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + d2);
        }
        throw new IllegalStateException("Expected " + i.NUMBER + " but was " + f2 + u());
    }

    public long l() {
        i f2 = f();
        if (f2 == i.NUMBER || f2 == i.STRING) {
            long f3 = ((q) s()).f();
            t();
            int i = this.e;
            if (i > 0) {
                int[] iArr = this.g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return f3;
        }
        throw new IllegalStateException("Expected " + i.NUMBER + " but was " + f2 + u());
    }

    public int m() {
        i f2 = f();
        if (f2 == i.NUMBER || f2 == i.STRING) {
            int g2 = ((q) s()).g();
            t();
            int i = this.e;
            if (i > 0) {
                int[] iArr = this.g;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return g2;
        }
        throw new IllegalStateException("Expected " + i.NUMBER + " but was " + f2 + u());
    }

    public void close() {
        this.d = new Object[]{c};
        this.e = 1;
    }

    public void n() {
        if (f() == i.NAME) {
            g();
            this.f[this.e - 2] = "null";
        } else {
            t();
            this.f[this.e - 1] = "null";
        }
        int[] iArr = this.g;
        int i = this.e - 1;
        iArr[i] = iArr[i] + 1;
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void o() {
        a(i.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) s()).next();
        a(entry.getValue());
        a((Object) new q((String) entry.getKey()));
    }

    private void a(Object obj) {
        int i = this.e;
        Object[] objArr = this.d;
        if (i == objArr.length) {
            Object[] objArr2 = new Object[(i * 2)];
            int[] iArr = new int[(i * 2)];
            String[] strArr = new String[(i * 2)];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.g, 0, iArr, 0, this.e);
            System.arraycopy(this.f, 0, strArr, 0, this.e);
            this.d = objArr2;
            this.g = iArr;
            this.f = strArr;
        }
        Object[] objArr3 = this.d;
        int i2 = this.e;
        this.e = i2 + 1;
        objArr3[i2] = obj;
    }

    public String p() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        int i = 0;
        while (i < this.e) {
            Object[] objArr = this.d;
            if (objArr[i] instanceof com.mnet.gson.h) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                    sb.append(this.g[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof n) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.f;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    private String u() {
        return " at path " + p();
    }
}

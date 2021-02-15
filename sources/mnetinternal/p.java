package mnetinternal;

import com.mnet.gson.m;
import com.mnet.gson.n;
import com.mnet.gson.q;
import com.mnet.gson.t;
import com.mnet.gson.v;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class p {
    public static final v<Byte> a = new v<Byte>() {
        /* renamed from: a */
        public Byte read(h hVar) {
            try {
                return Byte.valueOf((byte) hVar.m());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Byte b) {
            jVar.a((Number) b);
        }
    }.nullSafe();
    public static final v<Short> b = new v<Short>() {
        /* renamed from: a */
        public Short read(h hVar) {
            try {
                return Short.valueOf((short) hVar.m());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Short sh) {
            jVar.a((Number) sh);
        }
    }.nullSafe();
    public static final v<Integer> c = new v<Integer>() {
        /* renamed from: a */
        public Integer read(h hVar) {
            try {
                return Integer.valueOf(hVar.m());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Integer num) {
            jVar.a((Number) num);
        }
    }.nullSafe();
    public static final v<Long> d = new v<Long>() {
        /* renamed from: a */
        public Long read(h hVar) {
            try {
                return Long.valueOf(hVar.l());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Long l) {
            jVar.a((Number) l);
        }
    }.nullSafe();
    public static final v<Float> e = new v<Float>() {
        /* renamed from: a */
        public Float read(h hVar) {
            return Float.valueOf((float) hVar.k());
        }

        /* renamed from: a */
        public void write(j jVar, Float f) {
            jVar.a((Number) f);
        }
    }.nullSafe();
    public static final v<Double> f = new v<Double>() {
        /* renamed from: a */
        public Double read(h hVar) {
            return Double.valueOf(hVar.k());
        }

        /* renamed from: a */
        public void write(j jVar, Double d) {
            jVar.a((Number) d);
        }
    }.nullSafe();
    public static final v<ArrayList<Integer>> g = new d(c, new a());
    public static final v<ArrayList<Long>> h = new d(d, new a());
    public static final v<ArrayList<Double>> i = new d(f, new a());
    public static final v<ArrayList<Short>> j = new d(b, new a());
    public static final v<ArrayList<Float>> k = new d(e, new a());
    public static final v<ArrayList<Boolean>> l = new d(com.mnet.gson.internal.bind.i.e, new a());
    public static final v<ArrayList<Byte>> m = new d(a, new a());
    static final v<String> n = com.mnet.gson.internal.bind.i.A.nullSafe();
    public static final v<com.mnet.gson.k> o = com.mnet.gson.internal.bind.i.X.nullSafe();
    public static final v<n> p = new v<n>() {
        /* renamed from: a */
        public void write(j jVar, n nVar) {
            p.o.write(jVar, nVar);
        }

        /* renamed from: a */
        public n read(h hVar) {
            com.mnet.gson.k read = p.o.read(hVar);
            if (read == null || !read.j()) {
                return null;
            }
            return read.m();
        }
    }.nullSafe();
    public static final v<com.mnet.gson.h> q = new v<com.mnet.gson.h>() {
        /* renamed from: a */
        public void write(j jVar, com.mnet.gson.h hVar) {
            p.o.write(jVar, hVar);
        }

        /* renamed from: a */
        public com.mnet.gson.h read(h hVar) {
            com.mnet.gson.k read = p.o.read(hVar);
            if (read == null || !read.i()) {
                return null;
            }
            return read.n();
        }
    }.nullSafe();
    public static final v<q> r = new v<q>() {
        /* renamed from: a */
        public void write(j jVar, q qVar) {
            p.o.write(jVar, qVar);
        }

        /* renamed from: a */
        public q read(h hVar) {
            com.mnet.gson.k read = p.o.read(hVar);
            if (read == null || !read.k()) {
                return null;
            }
            return read.o();
        }
    }.nullSafe();
    public static final v<m> s = new v<m>() {
        /* renamed from: a */
        public void write(j jVar, m mVar) {
            p.o.write(jVar, mVar);
        }

        /* renamed from: a */
        public m read(h hVar) {
            com.mnet.gson.k read = p.o.read(hVar);
            if (read == null || !read.l()) {
                return null;
            }
            return read.p();
        }
    }.nullSafe();

    public static final class j {
        public static int a(h hVar, int i) {
            if (hVar.f() == i.NULL) {
                hVar.j();
                return i;
            }
            try {
                return hVar.m();
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }
    }

    public static final class k {
        public static long a(h hVar, long j) {
            if (hVar.f() == i.NULL) {
                hVar.j();
                return j;
            }
            try {
                return hVar.l();
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }
    }

    public static final class i {
        public static float a(h hVar, float f) {
            if (hVar.f() == i.NULL) {
                hVar.j();
                return f;
            }
            try {
                return (float) hVar.k();
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }
    }

    public static final class h {
        public static double a(h hVar, double d) {
            if (hVar.f() == i.NULL) {
                hVar.j();
                return d;
            }
            try {
                return hVar.k();
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }
    }

    public static final class g {
        public static boolean a(h hVar, boolean z) {
            i f = hVar.f();
            if (f == i.NULL) {
                hVar.j();
                return z;
            } else if (f == i.STRING) {
                return Boolean.parseBoolean(hVar.h());
            } else {
                return hVar.i();
            }
        }
    }

    public static final class c<V> implements com.mnet.gson.internal.g<List<V>> {
        /* renamed from: b */
        public List<V> a() {
            return new ArrayList();
        }
    }

    public static final class a<V> implements com.mnet.gson.internal.g<ArrayList<V>> {
        /* renamed from: b */
        public ArrayList<V> a() {
            return new ArrayList<>();
        }
    }

    public static final class b<K, V> implements com.mnet.gson.internal.g<HashMap<K, V>> {
        /* renamed from: b */
        public HashMap<K, V> a() {
            return new HashMap<>();
        }
    }

    public static final class e<K, V> implements com.mnet.gson.internal.g<Map<K, V>> {
        /* renamed from: b */
        public Map<K, V> a() {
            return new LinkedHashMap();
        }
    }

    public static final class d<V, T extends Collection<V>> extends v<T> {
        private final v<V> a;
        private final com.mnet.gson.internal.g<T> b;

        public d(v<V> vVar, com.mnet.gson.internal.g<T> gVar) {
            this.a = vVar;
            this.b = gVar;
        }

        /* renamed from: a */
        public void write(j jVar, T t) {
            if (t == null) {
                jVar.f();
                return;
            }
            jVar.b();
            for (Object write : t) {
                this.a.write(jVar, write);
            }
            jVar.c();
        }

        /* renamed from: a */
        public T read(h hVar) {
            if (i.NULL == hVar.f()) {
                hVar.j();
                return null;
            }
            T t = (Collection) this.b.a();
            hVar.a();
            while (hVar.e()) {
                t.add(this.a.read(hVar));
            }
            hVar.b();
            return t;
        }
    }

    public static final class f<K, V, T extends Map<K, V>> extends v<T> {
        private final com.mnet.gson.internal.g<T> a;
        private final v<V> b;
        private final v<K> c;

        public f(v<K> vVar, v<V> vVar2, com.mnet.gson.internal.g<T> gVar) {
            this.c = vVar;
            this.b = vVar2;
            this.a = gVar;
        }

        /* renamed from: a */
        public void write(j jVar, T t) {
            if (t == null) {
                jVar.f();
                return;
            }
            ArrayList arrayList = new ArrayList(t.size());
            ArrayList arrayList2 = new ArrayList(t.size());
            int i = 0;
            boolean z = false;
            for (Map.Entry entry : t.entrySet()) {
                com.mnet.gson.k jsonTree = this.c.toJsonTree(entry.getKey());
                arrayList.add(jsonTree);
                arrayList2.add(entry.getValue());
                z |= jsonTree.i() || jsonTree.j();
            }
            if (z) {
                jVar.b();
                while (i < arrayList.size()) {
                    jVar.b();
                    com.mnet.gson.internal.i.a((com.mnet.gson.k) arrayList.get(i), jVar);
                    this.b.write(jVar, arrayList2.get(i));
                    jVar.c();
                    i++;
                }
                jVar.c();
                return;
            }
            jVar.d();
            while (i < arrayList.size()) {
                jVar.a(a((com.mnet.gson.k) arrayList.get(i)));
                this.b.write(jVar, arrayList2.get(i));
                i++;
            }
            jVar.e();
        }

        /* renamed from: a */
        public T read(h hVar) {
            i f = hVar.f();
            if (f == i.NULL) {
                hVar.j();
                return null;
            }
            T t = (Map) this.a.a();
            if (f == i.BEGIN_ARRAY) {
                hVar.a();
                while (hVar.e()) {
                    hVar.a();
                    K read = this.c.read(hVar);
                    if (t.put(read, this.b.read(hVar)) == null) {
                        hVar.b();
                    } else {
                        throw new t("duplicate key: " + read);
                    }
                }
                hVar.b();
            } else {
                hVar.c();
                while (hVar.e()) {
                    com.mnet.gson.internal.d.a.a(hVar);
                    K read2 = this.c.read(hVar);
                    if (t.put(read2, this.b.read(hVar)) != null) {
                        throw new t("duplicate key: " + read2);
                    }
                }
                hVar.d();
            }
            return t;
        }

        private static String a(com.mnet.gson.k kVar) {
            if (kVar.k()) {
                q o = kVar.o();
                if (o.r()) {
                    return String.valueOf(o.b());
                }
                if (o.a()) {
                    return Boolean.toString(o.h());
                }
                if (o.s()) {
                    return o.c();
                }
                throw new AssertionError();
            } else if (kVar.l()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }
}

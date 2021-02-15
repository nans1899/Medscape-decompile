package com.mnet.gson;

import com.mnet.gson.internal.Excluder;
import com.mnet.gson.internal.bind.CollectionTypeAdapterFactory;
import com.mnet.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.mnet.gson.internal.bind.MapTypeAdapterFactory;
import com.mnet.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.mnet.gson.internal.bind.b;
import com.mnet.gson.internal.bind.d;
import com.mnet.gson.internal.bind.f;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.internal.c;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.k;

public final class e {
    private static final g<?> a = new g<Object>() {
    };
    private final ThreadLocal<Map<g<?>, a<?>>> b;
    private final Map<g<?>, v<?>> c;
    private final List<w> d;
    private final c e;
    private final Excluder f;
    private final d g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final JsonAdapterAnnotationTypeAdapterFactory m;

    public e() {
        this(Excluder.a, c.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, u.a, Collections.emptyList());
    }

    e(Excluder excluder, d dVar, Map<Type, g<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, u uVar, List<w> list) {
        this.b = new ThreadLocal<>();
        this.c = new ConcurrentHashMap();
        this.e = new c(map);
        this.f = excluder;
        this.g = dVar;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(i.Y);
        arrayList.add(com.mnet.gson.internal.bind.e.a);
        arrayList.add(excluder);
        arrayList.addAll(list);
        arrayList.add(i.D);
        arrayList.add(i.m);
        arrayList.add(i.g);
        arrayList.add(i.i);
        arrayList.add(i.k);
        v<Number> a2 = a(uVar);
        arrayList.add(i.a(Long.TYPE, Long.class, a2));
        arrayList.add(i.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(i.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(i.x);
        arrayList.add(i.o);
        arrayList.add(i.q);
        arrayList.add(i.a(AtomicLong.class, a(a2)));
        arrayList.add(i.a(AtomicLongArray.class, b(a2)));
        arrayList.add(i.s);
        arrayList.add(i.z);
        arrayList.add(i.F);
        arrayList.add(i.H);
        arrayList.add(i.a(BigDecimal.class, i.B));
        arrayList.add(i.a(BigInteger.class, i.C));
        arrayList.add(i.J);
        arrayList.add(i.L);
        arrayList.add(i.P);
        arrayList.add(i.R);
        arrayList.add(i.W);
        arrayList.add(i.N);
        arrayList.add(i.d);
        arrayList.add(b.a);
        arrayList.add(i.U);
        arrayList.add(com.mnet.gson.internal.bind.g.a);
        arrayList.add(f.a);
        arrayList.add(i.S);
        arrayList.add(com.mnet.gson.internal.bind.a.a);
        arrayList.add(i.b);
        arrayList.add(new CollectionTypeAdapterFactory(this.e));
        arrayList.add(new MapTypeAdapterFactory(this.e, z2));
        JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.e);
        this.m = jsonAdapterAnnotationTypeAdapterFactory;
        arrayList.add(jsonAdapterAnnotationTypeAdapterFactory);
        arrayList.add(i.Z);
        arrayList.add(new ReflectiveTypeAdapterFactory(this.e, dVar, excluder, this.m));
        this.d = Collections.unmodifiableList(arrayList);
    }

    private v<Number> a(boolean z) {
        if (z) {
            return i.v;
        }
        return new v<Number>() {
            /* renamed from: a */
            public Double read(h hVar) {
                if (hVar.f() != mnetinternal.i.NULL) {
                    return Double.valueOf(hVar.k());
                }
                hVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, Number number) {
                if (number == null) {
                    jVar.f();
                    return;
                }
                e.a(number.doubleValue());
                jVar.a(number);
            }
        };
    }

    private v<Number> b(boolean z) {
        if (z) {
            return i.u;
        }
        return new v<Number>() {
            /* renamed from: a */
            public Float read(h hVar) {
                if (hVar.f() != mnetinternal.i.NULL) {
                    return Float.valueOf((float) hVar.k());
                }
                hVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, Number number) {
                if (number == null) {
                    jVar.f();
                    return;
                }
                e.a((double) number.floatValue());
                jVar.a(number);
            }
        };
    }

    static void a(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException(d2 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static v<Number> a(u uVar) {
        if (uVar == u.a) {
            return i.t;
        }
        return new v<Number>() {
            /* renamed from: a */
            public Number read(h hVar) {
                if (hVar.f() != mnetinternal.i.NULL) {
                    return Long.valueOf(hVar.l());
                }
                hVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, Number number) {
                if (number == null) {
                    jVar.f();
                } else {
                    jVar.b(number.toString());
                }
            }
        };
    }

    private static v<AtomicLong> a(final v<Number> vVar) {
        return new v<AtomicLong>() {
            /* renamed from: a */
            public void write(j jVar, AtomicLong atomicLong) {
                vVar.write(jVar, Long.valueOf(atomicLong.get()));
            }

            /* renamed from: a */
            public AtomicLong read(h hVar) {
                return new AtomicLong(((Number) vVar.read(hVar)).longValue());
            }
        }.nullSafe();
    }

    private static v<AtomicLongArray> b(final v<Number> vVar) {
        return new v<AtomicLongArray>() {
            /* renamed from: a */
            public void write(j jVar, AtomicLongArray atomicLongArray) {
                jVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    vVar.write(jVar, Long.valueOf(atomicLongArray.get(i)));
                }
                jVar.c();
            }

            /* renamed from: a */
            public AtomicLongArray read(h hVar) {
                ArrayList arrayList = new ArrayList();
                hVar.a();
                while (hVar.e()) {
                    arrayList.add(Long.valueOf(((Number) vVar.read(hVar)).longValue()));
                }
                hVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.nullSafe();
    }

    public <T> v<T> a(g<T> gVar) {
        v<T> vVar = this.c.get(gVar == null ? a : gVar);
        if (vVar != null) {
            return vVar;
        }
        Map map = this.b.get();
        boolean z = false;
        if (map == null) {
            map = new HashMap();
            this.b.set(map);
            z = true;
        }
        a aVar = (a) map.get(gVar);
        if (aVar != null) {
            return aVar;
        }
        try {
            a aVar2 = new a();
            map.put(gVar, aVar2);
            for (w create : this.d) {
                v<T> create2 = create.create(this, gVar);
                if (create2 != null) {
                    aVar2.a(create2);
                    this.c.put(gVar, create2);
                    return create2;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + gVar);
        } finally {
            map.remove(gVar);
            if (z) {
                this.b.remove();
            }
        }
    }

    public <T> v<T> a(w wVar, g<T> gVar) {
        if (!this.d.contains(wVar)) {
            wVar = this.m;
        }
        boolean z = false;
        for (w next : this.d) {
            if (z) {
                v<T> create = next.create(this, gVar);
                if (create != null) {
                    return create;
                }
            } else if (next == wVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + gVar);
    }

    public <T> v<T> a(Class<T> cls) {
        return a(g.b(cls));
    }

    public k a(Object obj) {
        if (obj == null) {
            return m.a;
        }
        return a(obj, (Type) obj.getClass());
    }

    public k a(Object obj, Type type) {
        d dVar = new d();
        a(obj, type, (j) dVar);
        return dVar.a();
    }

    public String b(Object obj) {
        if (obj == null) {
            return a((k) m.a);
        }
        return b(obj, obj.getClass());
    }

    public String b(Object obj, Type type) {
        StringWriter stringWriter = new StringWriter();
        a(obj, type, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Appendable appendable) {
        if (obj != null) {
            a(obj, (Type) obj.getClass(), appendable);
        } else {
            a((k) m.a, appendable);
        }
    }

    public void a(Object obj, Type type, Appendable appendable) {
        try {
            a(obj, type, a(com.mnet.gson.internal.i.a(appendable)));
        } catch (IOException e2) {
            throw new l((Throwable) e2);
        }
    }

    public void a(Object obj, Type type, j jVar) {
        v<?> a2 = a(g.a(type));
        boolean g2 = jVar.g();
        jVar.b(true);
        boolean h2 = jVar.h();
        jVar.c(this.i);
        boolean i2 = jVar.i();
        jVar.d(this.h);
        try {
            a2.write(jVar, obj);
            jVar.b(g2);
            jVar.c(h2);
            jVar.d(i2);
        } catch (IOException e2) {
            throw new l((Throwable) e2);
        } catch (Throwable th) {
            jVar.b(g2);
            jVar.c(h2);
            jVar.d(i2);
            throw th;
        }
    }

    public String a(k kVar) {
        StringWriter stringWriter = new StringWriter();
        a(kVar, (Appendable) stringWriter);
        return stringWriter.toString();
    }

    public void a(k kVar, Appendable appendable) {
        try {
            a(kVar, a(com.mnet.gson.internal.i.a(appendable)));
        } catch (IOException e2) {
            throw new l((Throwable) e2);
        }
    }

    public j a(Writer writer) {
        if (this.j) {
            writer.write(")]}'\n");
        }
        j jVar = new j(writer);
        if (this.k) {
            jVar.c("  ");
        }
        jVar.d(this.h);
        return jVar;
    }

    public h a(Reader reader) {
        h hVar = new h(reader);
        hVar.a(this.l);
        return hVar;
    }

    public void a(k kVar, j jVar) {
        boolean g2 = jVar.g();
        jVar.b(true);
        boolean h2 = jVar.h();
        jVar.c(this.i);
        boolean i2 = jVar.i();
        jVar.d(this.h);
        try {
            com.mnet.gson.internal.i.a(kVar, jVar);
            jVar.b(g2);
            jVar.c(h2);
            jVar.d(i2);
        } catch (IOException e2) {
            throw new l((Throwable) e2);
        } catch (Throwable th) {
            jVar.b(g2);
            jVar.c(h2);
            jVar.d(i2);
            throw th;
        }
    }

    public <T> T a(String str, Class<T> cls) {
        return com.mnet.gson.internal.h.a(cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) {
        if (str == null) {
            return null;
        }
        return a((Reader) new StringReader(str), type);
    }

    public <T> T a(Reader reader, Class<T> cls) {
        h a2 = a(reader);
        T a3 = a(a2, (Type) cls);
        a((Object) a3, a2);
        return com.mnet.gson.internal.h.a(cls).cast(a3);
    }

    public <T> T a(Reader reader, Type type) {
        h a2 = a(reader);
        T a3 = a(a2, type);
        a((Object) a3, a2);
        return a3;
    }

    private static void a(Object obj, h hVar) {
        if (obj != null) {
            try {
                if (hVar.f() != mnetinternal.i.END_DOCUMENT) {
                    throw new l("JSON document was not fully consumed.");
                }
            } catch (k e2) {
                throw new t((Throwable) e2);
            } catch (IOException e3) {
                throw new l((Throwable) e3);
            }
        }
    }

    public <T> T a(h hVar, Type type) {
        boolean q = hVar.q();
        hVar.a(true);
        try {
            hVar.f();
            T read = a(g.a(type)).read(hVar);
            hVar.a(q);
            return read;
        } catch (EOFException e2) {
            if (1 != 0) {
                hVar.a(q);
                return null;
            }
            throw new t((Throwable) e2);
        } catch (IllegalStateException e3) {
            throw new t((Throwable) e3);
        } catch (IOException e4) {
            throw new t((Throwable) e4);
        } catch (Throwable th) {
            hVar.a(q);
            throw th;
        }
    }

    public <T> T a(k kVar, Class<T> cls) {
        return com.mnet.gson.internal.h.a(cls).cast(a(kVar, (Type) cls));
    }

    public <T> T a(k kVar, Type type) {
        if (kVar == null) {
            return null;
        }
        return a((h) new com.mnet.gson.internal.bind.c(kVar), type);
    }

    static class a<T> extends v<T> {
        private v<T> a;

        a() {
        }

        public void a(v<T> vVar) {
            if (this.a == null) {
                this.a = vVar;
                return;
            }
            throw new AssertionError();
        }

        public T read(h hVar) {
            v<T> vVar = this.a;
            if (vVar != null) {
                return vVar.read(hVar);
            }
            throw new IllegalStateException();
        }

        public void write(j jVar, T t) {
            v<T> vVar = this.a;
            if (vVar != null) {
                vVar.write(jVar, t);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.h + "factories:" + this.d + ",instanceCreators:" + this.e + "}";
    }
}

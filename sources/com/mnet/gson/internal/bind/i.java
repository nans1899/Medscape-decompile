package com.mnet.gson.internal.bind;

import com.mnet.gson.internal.e;
import com.mnet.gson.k;
import com.mnet.gson.l;
import com.mnet.gson.m;
import com.mnet.gson.n;
import com.mnet.gson.q;
import com.mnet.gson.t;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;

public final class i {
    public static final v<String> A = new v<String>() {
        /* renamed from: a */
        public String read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (f == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            } else if (f == mnetinternal.i.BOOLEAN) {
                return Boolean.toString(hVar.i());
            } else {
                return hVar.h();
            }
        }

        /* renamed from: a */
        public void write(j jVar, String str) {
            jVar.b(str);
        }
    };
    public static final v<BigDecimal> B = new v<BigDecimal>() {
        /* renamed from: a */
        public BigDecimal read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            }
            try {
                return new BigDecimal(hVar.h());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, BigDecimal bigDecimal) {
            jVar.a((Number) bigDecimal);
        }
    };
    public static final v<BigInteger> C = new v<BigInteger>() {
        /* renamed from: a */
        public BigInteger read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            }
            try {
                return new BigInteger(hVar.h());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, BigInteger bigInteger) {
            jVar.a((Number) bigInteger);
        }
    };
    public static final w D = a(String.class, A);
    public static final v<StringBuilder> E;
    public static final w F;
    public static final v<StringBuffer> G;
    public static final w H;
    public static final v<URL> I;
    public static final w J;
    public static final v<URI> K;
    public static final w L;
    public static final v<InetAddress> M;
    public static final w N;
    public static final v<UUID> O;
    public static final w P;
    public static final v<Currency> Q;
    public static final w R;
    public static final w S = new TypeAdapters$26();
    public static final v<Calendar> T;
    public static final w U;
    public static final v<Locale> V;
    public static final w W;
    public static final v<k> X;
    public static final w Y;
    public static final w Z = new TypeAdapters$30();
    public static final v<Class> a;
    public static final w b;
    public static final v<BitSet> c;
    public static final w d;
    public static final v<Boolean> e = new v<Boolean>() {
        /* renamed from: a */
        public Boolean read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            } else if (hVar.f() == mnetinternal.i.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(hVar.h()));
            } else {
                return Boolean.valueOf(hVar.i());
            }
        }

        /* renamed from: a */
        public void write(j jVar, Boolean bool) {
            jVar.a(bool);
        }
    };
    public static final v<Boolean> f = new v<Boolean>() {
        /* renamed from: a */
        public Boolean read(h hVar) {
            if (hVar.f() != mnetinternal.i.NULL) {
                return Boolean.valueOf(hVar.h());
            }
            hVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(j jVar, Boolean bool) {
            jVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final w g = a(Boolean.TYPE, Boolean.class, e);
    public static final v<Number> h = new v<Number>() {
        /* renamed from: a */
        public Number read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) hVar.m());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Number number) {
            jVar.a(number);
        }
    };
    public static final w i = a(Byte.TYPE, Byte.class, h);
    public static final v<Number> j = new v<Number>() {
        /* renamed from: a */
        public Number read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) hVar.m());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Number number) {
            jVar.a(number);
        }
    };
    public static final w k = a(Short.TYPE, Short.class, j);
    public static final v<Number> l = new v<Number>() {
        /* renamed from: a */
        public Number read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            }
            try {
                return Integer.valueOf(hVar.m());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Number number) {
            jVar.a(number);
        }
    };
    public static final w m = a(Integer.TYPE, Integer.class, l);
    public static final v<AtomicInteger> n;
    public static final w o;
    public static final v<AtomicBoolean> p;
    public static final w q;
    public static final v<AtomicIntegerArray> r;
    public static final w s;
    public static final v<Number> t = new v<Number>() {
        /* renamed from: a */
        public Number read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            }
            try {
                return Long.valueOf(hVar.l());
            } catch (NumberFormatException e) {
                throw new t((Throwable) e);
            }
        }

        /* renamed from: a */
        public void write(j jVar, Number number) {
            jVar.a(number);
        }
    };
    public static final v<Number> u = new v<Number>() {
        /* renamed from: a */
        public Number read(h hVar) {
            if (hVar.f() != mnetinternal.i.NULL) {
                return Float.valueOf((float) hVar.k());
            }
            hVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(j jVar, Number number) {
            jVar.a(number);
        }
    };
    public static final v<Number> v = new v<Number>() {
        /* renamed from: a */
        public Number read(h hVar) {
            if (hVar.f() != mnetinternal.i.NULL) {
                return Double.valueOf(hVar.k());
            }
            hVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(j jVar, Number number) {
            jVar.a(number);
        }
    };
    public static final v<Number> w;
    public static final w x;
    public static final v<Character> y = new v<Character>() {
        /* renamed from: a */
        public Character read(h hVar) {
            if (hVar.f() == mnetinternal.i.NULL) {
                hVar.j();
                return null;
            }
            String h = hVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            throw new t("Expecting character, got: " + h);
        }

        /* renamed from: a */
        public void write(j jVar, Character ch) {
            jVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final w z = a(Character.TYPE, Character.class, y);

    static {
        AnonymousClass1 r0 = new v<Class>() {
            /* renamed from: a */
            public void write(j jVar, Class cls) {
                if (cls == null) {
                    jVar.f();
                    return;
                }
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
            }

            /* renamed from: a */
            public Class read(h hVar) {
                if (hVar.f() == mnetinternal.i.NULL) {
                    hVar.j();
                    return null;
                }
                throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
            }
        };
        a = r0;
        b = a(Class.class, r0);
        AnonymousClass12 r02 = new v<BitSet>() {
            /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
                if (java.lang.Integer.parseInt(r1) != 0) goto L_0x0076;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:25:0x0074, code lost:
                if (r8.m() != 0) goto L_0x0076;
             */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x0078  */
            /* JADX WARNING: Removed duplicated region for block: B:34:0x007b A[SYNTHETIC] */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.util.BitSet read(mnetinternal.h r8) {
                /*
                    r7 = this;
                    mnetinternal.i r0 = r8.f()
                    mnetinternal.i r1 = mnetinternal.i.NULL
                    if (r0 != r1) goto L_0x000d
                    r8.j()
                    r8 = 0
                    return r8
                L_0x000d:
                    java.util.BitSet r0 = new java.util.BitSet
                    r0.<init>()
                    r8.a()
                    mnetinternal.i r1 = r8.f()
                    r2 = 0
                    r3 = 0
                L_0x001b:
                    mnetinternal.i r4 = mnetinternal.i.END_ARRAY
                    if (r1 == r4) goto L_0x0082
                    int[] r4 = com.mnet.gson.internal.bind.i.AnonymousClass23.a
                    int r5 = r1.ordinal()
                    r4 = r4[r5]
                    r5 = 1
                    if (r4 == r5) goto L_0x0070
                    r6 = 2
                    if (r4 == r6) goto L_0x006b
                    r6 = 3
                    if (r4 != r6) goto L_0x0054
                    java.lang.String r1 = r8.h()
                    int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x003d }
                    if (r1 == 0) goto L_0x003b
                    goto L_0x0076
                L_0x003b:
                    r5 = 0
                    goto L_0x0076
                L_0x003d:
                    com.mnet.gson.t r8 = new com.mnet.gson.t
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r2 = "Error: Expecting: bitset number value (1, 0), Found: "
                    r0.append(r2)
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    r8.<init>((java.lang.String) r0)
                    throw r8
                L_0x0054:
                    com.mnet.gson.t r8 = new com.mnet.gson.t
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r2 = "Invalid bitset value type: "
                    r0.append(r2)
                    r0.append(r1)
                    java.lang.String r0 = r0.toString()
                    r8.<init>((java.lang.String) r0)
                    throw r8
                L_0x006b:
                    boolean r5 = r8.i()
                    goto L_0x0076
                L_0x0070:
                    int r1 = r8.m()
                    if (r1 == 0) goto L_0x003b
                L_0x0076:
                    if (r5 == 0) goto L_0x007b
                    r0.set(r3)
                L_0x007b:
                    int r3 = r3 + 1
                    mnetinternal.i r1 = r8.f()
                    goto L_0x001b
                L_0x0082:
                    r8.b()
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mnet.gson.internal.bind.i.AnonymousClass12.read(mnetinternal.h):java.util.BitSet");
            }

            /* renamed from: a */
            public void write(j jVar, BitSet bitSet) {
                if (bitSet == null) {
                    jVar.f();
                    return;
                }
                jVar.b();
                for (int i = 0; i < bitSet.length(); i++) {
                    jVar.a(bitSet.get(i) ? 1 : 0);
                }
                jVar.c();
            }
        };
        c = r02;
        d = a(BitSet.class, r02);
        v<AtomicInteger> nullSafe = new v<AtomicInteger>() {
            /* renamed from: a */
            public AtomicInteger read(h hVar) {
                try {
                    return new AtomicInteger(hVar.m());
                } catch (NumberFormatException e) {
                    throw new t((Throwable) e);
                }
            }

            /* renamed from: a */
            public void write(j jVar, AtomicInteger atomicInteger) {
                jVar.a((long) atomicInteger.get());
            }
        }.nullSafe();
        n = nullSafe;
        o = a(AtomicInteger.class, nullSafe);
        v<AtomicBoolean> nullSafe2 = new v<AtomicBoolean>() {
            /* renamed from: a */
            public AtomicBoolean read(h hVar) {
                return new AtomicBoolean(hVar.i());
            }

            /* renamed from: a */
            public void write(j jVar, AtomicBoolean atomicBoolean) {
                jVar.a(atomicBoolean.get());
            }
        }.nullSafe();
        p = nullSafe2;
        q = a(AtomicBoolean.class, nullSafe2);
        v<AtomicIntegerArray> nullSafe3 = new v<AtomicIntegerArray>() {
            /* renamed from: a */
            public AtomicIntegerArray read(h hVar) {
                ArrayList arrayList = new ArrayList();
                hVar.a();
                while (hVar.e()) {
                    try {
                        arrayList.add(Integer.valueOf(hVar.m()));
                    } catch (NumberFormatException e) {
                        throw new t((Throwable) e);
                    }
                }
                hVar.b();
                int size = arrayList.size();
                AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
                for (int i = 0; i < size; i++) {
                    atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
                }
                return atomicIntegerArray;
            }

            /* renamed from: a */
            public void write(j jVar, AtomicIntegerArray atomicIntegerArray) {
                jVar.b();
                int length = atomicIntegerArray.length();
                for (int i = 0; i < length; i++) {
                    jVar.a((long) atomicIntegerArray.get(i));
                }
                jVar.c();
            }
        }.nullSafe();
        r = nullSafe3;
        s = a(AtomicIntegerArray.class, nullSafe3);
        AnonymousClass6 r03 = new v<Number>() {
            /* renamed from: a */
            public Number read(h hVar) {
                mnetinternal.i f = hVar.f();
                int i = AnonymousClass23.a[f.ordinal()];
                if (i == 1) {
                    return new e(hVar.h());
                }
                if (i == 4) {
                    hVar.j();
                    return null;
                }
                throw new t("Expecting number, got: " + f);
            }

            /* renamed from: a */
            public void write(j jVar, Number number) {
                jVar.a(number);
            }
        };
        w = r03;
        x = a(Number.class, r03);
        AnonymousClass11 r04 = new v<StringBuilder>() {
            /* renamed from: a */
            public StringBuilder read(h hVar) {
                if (hVar.f() != mnetinternal.i.NULL) {
                    return new StringBuilder(hVar.h());
                }
                hVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, StringBuilder sb) {
                jVar.b(sb == null ? null : sb.toString());
            }
        };
        E = r04;
        F = a(StringBuilder.class, r04);
        AnonymousClass13 r05 = new v<StringBuffer>() {
            /* renamed from: a */
            public StringBuffer read(h hVar) {
                if (hVar.f() != mnetinternal.i.NULL) {
                    return new StringBuffer(hVar.h());
                }
                hVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, StringBuffer stringBuffer) {
                jVar.b(stringBuffer == null ? null : stringBuffer.toString());
            }
        };
        G = r05;
        H = a(StringBuffer.class, r05);
        AnonymousClass14 r06 = new v<URL>() {
            /* renamed from: a */
            public URL read(h hVar) {
                if (hVar.f() == mnetinternal.i.NULL) {
                    hVar.j();
                    return null;
                }
                String h = hVar.h();
                if ("null".equals(h)) {
                    return null;
                }
                return new URL(h);
            }

            /* renamed from: a */
            public void write(j jVar, URL url) {
                jVar.b(url == null ? null : url.toExternalForm());
            }
        };
        I = r06;
        J = a(URL.class, r06);
        AnonymousClass15 r07 = new v<URI>() {
            /* renamed from: a */
            public URI read(h hVar) {
                if (hVar.f() == mnetinternal.i.NULL) {
                    hVar.j();
                    return null;
                }
                try {
                    String h = hVar.h();
                    if ("null".equals(h)) {
                        return null;
                    }
                    return new URI(h);
                } catch (URISyntaxException e) {
                    throw new l((Throwable) e);
                }
            }

            /* renamed from: a */
            public void write(j jVar, URI uri) {
                jVar.b(uri == null ? null : uri.toASCIIString());
            }
        };
        K = r07;
        L = a(URI.class, r07);
        AnonymousClass16 r08 = new v<InetAddress>() {
            /* renamed from: a */
            public InetAddress read(h hVar) {
                if (hVar.f() != mnetinternal.i.NULL) {
                    return InetAddress.getByName(hVar.h());
                }
                hVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, InetAddress inetAddress) {
                jVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
            }
        };
        M = r08;
        N = b(InetAddress.class, r08);
        AnonymousClass17 r09 = new v<UUID>() {
            /* renamed from: a */
            public UUID read(h hVar) {
                if (hVar.f() != mnetinternal.i.NULL) {
                    return UUID.fromString(hVar.h());
                }
                hVar.j();
                return null;
            }

            /* renamed from: a */
            public void write(j jVar, UUID uuid) {
                jVar.b(uuid == null ? null : uuid.toString());
            }
        };
        O = r09;
        P = a(UUID.class, r09);
        v<Currency> nullSafe4 = new v<Currency>() {
            /* renamed from: a */
            public Currency read(h hVar) {
                return Currency.getInstance(hVar.h());
            }

            /* renamed from: a */
            public void write(j jVar, Currency currency) {
                jVar.b(currency.getCurrencyCode());
            }
        }.nullSafe();
        Q = nullSafe4;
        R = a(Currency.class, nullSafe4);
        AnonymousClass19 r010 = new v<Calendar>() {
            /* renamed from: a */
            public Calendar read(h hVar) {
                if (hVar.f() == mnetinternal.i.NULL) {
                    hVar.j();
                    return null;
                }
                hVar.c();
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (hVar.f() != mnetinternal.i.END_OBJECT) {
                    String g = hVar.g();
                    int m = hVar.m();
                    if ("year".equals(g)) {
                        i = m;
                    } else if ("month".equals(g)) {
                        i2 = m;
                    } else if ("dayOfMonth".equals(g)) {
                        i3 = m;
                    } else if ("hourOfDay".equals(g)) {
                        i4 = m;
                    } else if ("minute".equals(g)) {
                        i5 = m;
                    } else if ("second".equals(g)) {
                        i6 = m;
                    }
                }
                hVar.d();
                return new GregorianCalendar(i, i2, i3, i4, i5, i6);
            }

            /* renamed from: a */
            public void write(j jVar, Calendar calendar) {
                if (calendar == null) {
                    jVar.f();
                    return;
                }
                jVar.d();
                jVar.a("year");
                jVar.a((long) calendar.get(1));
                jVar.a("month");
                jVar.a((long) calendar.get(2));
                jVar.a("dayOfMonth");
                jVar.a((long) calendar.get(5));
                jVar.a("hourOfDay");
                jVar.a((long) calendar.get(11));
                jVar.a("minute");
                jVar.a((long) calendar.get(12));
                jVar.a("second");
                jVar.a((long) calendar.get(13));
                jVar.e();
            }
        };
        T = r010;
        U = b(Calendar.class, GregorianCalendar.class, r010);
        AnonymousClass20 r011 = new v<Locale>() {
            /* renamed from: a */
            public Locale read(h hVar) {
                String str = null;
                if (hVar.f() == mnetinternal.i.NULL) {
                    hVar.j();
                    return null;
                }
                StringTokenizer stringTokenizer = new StringTokenizer(hVar.h(), "_");
                String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
                if (stringTokenizer.hasMoreElements()) {
                    str = stringTokenizer.nextToken();
                }
                if (nextToken2 == null && str == null) {
                    return new Locale(nextToken);
                }
                if (str == null) {
                    return new Locale(nextToken, nextToken2);
                }
                return new Locale(nextToken, nextToken2, str);
            }

            /* renamed from: a */
            public void write(j jVar, Locale locale) {
                jVar.b(locale == null ? null : locale.toString());
            }
        };
        V = r011;
        W = a(Locale.class, r011);
        AnonymousClass21 r012 = new v<k>() {
            /* renamed from: a */
            public k read(h hVar) {
                switch (AnonymousClass23.a[hVar.f().ordinal()]) {
                    case 1:
                        return new q((Number) new e(hVar.h()));
                    case 2:
                        return new q(Boolean.valueOf(hVar.i()));
                    case 3:
                        return new q(hVar.h());
                    case 4:
                        hVar.j();
                        return m.a;
                    case 5:
                        com.mnet.gson.h hVar2 = new com.mnet.gson.h();
                        hVar.a();
                        while (hVar.e()) {
                            hVar2.a(read(hVar));
                        }
                        hVar.b();
                        return hVar2;
                    case 6:
                        n nVar = new n();
                        hVar.c();
                        while (hVar.e()) {
                            nVar.a(hVar.g(), read(hVar));
                        }
                        hVar.d();
                        return nVar;
                    default:
                        throw new IllegalArgumentException();
                }
            }

            /* renamed from: a */
            public void write(j jVar, k kVar) {
                if (kVar == null || kVar.l()) {
                    jVar.f();
                } else if (kVar.k()) {
                    q o = kVar.o();
                    if (o.r()) {
                        jVar.a(o.b());
                    } else if (o.a()) {
                        jVar.a(o.h());
                    } else {
                        jVar.b(o.c());
                    }
                } else if (kVar.i()) {
                    jVar.b();
                    Iterator<k> it = kVar.n().iterator();
                    while (it.hasNext()) {
                        write(jVar, it.next());
                    }
                    jVar.c();
                } else if (kVar.j()) {
                    jVar.d();
                    for (Map.Entry next : kVar.m().a()) {
                        jVar.a((String) next.getKey());
                        write(jVar, (k) next.getValue());
                    }
                    jVar.e();
                } else {
                    throw new IllegalArgumentException("Couldn't write " + kVar.getClass());
                }
            }
        };
        X = r012;
        Y = b(k.class, r012);
    }

    /* renamed from: com.mnet.gson.internal.bind.i$23  reason: invalid class name */
    static /* synthetic */ class AnonymousClass23 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                mnetinternal.i[] r0 = mnetinternal.i.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                mnetinternal.i r1 = mnetinternal.i.NUMBER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                mnetinternal.i r1 = mnetinternal.i.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                mnetinternal.i r1 = mnetinternal.i.STRING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                mnetinternal.i r1 = mnetinternal.i.NULL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                mnetinternal.i r1 = mnetinternal.i.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0049 }
                mnetinternal.i r1 = mnetinternal.i.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0054 }
                mnetinternal.i r1 = mnetinternal.i.END_DOCUMENT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0060 }
                mnetinternal.i r1 = mnetinternal.i.NAME     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x006c }
                mnetinternal.i r1 = mnetinternal.i.END_OBJECT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0078 }
                mnetinternal.i r1 = mnetinternal.i.END_ARRAY     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mnet.gson.internal.bind.i.AnonymousClass23.<clinit>():void");
        }
    }

    private static final class a<T extends Enum<T>> extends v<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public a(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    c cVar = (c) cls.getField(name).getAnnotation(c.class);
                    if (cVar != null) {
                        name = cVar.a();
                        for (String put : cVar.b()) {
                            this.a.put(put, enumR);
                        }
                    }
                    this.a.put(name, enumR);
                    this.b.put(enumR, name);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        /* renamed from: a */
        public T read(h hVar) {
            if (hVar.f() != mnetinternal.i.NULL) {
                return (Enum) this.a.get(hVar.h());
            }
            hVar.j();
            return null;
        }

        /* renamed from: a */
        public void write(j jVar, T t) {
            jVar.b(t == null ? null : this.b.get(t));
        }
    }

    public static <TT> w a(g<TT> gVar, v<TT> vVar) {
        return new TypeAdapters$31(gVar, vVar);
    }

    public static <TT> w a(Class<TT> cls, v<TT> vVar) {
        return new TypeAdapters$32(cls, vVar);
    }

    public static <TT> w a(Class<TT> cls, Class<TT> cls2, v<? super TT> vVar) {
        return new TypeAdapters$33(cls, cls2, vVar);
    }

    public static <TT> w b(Class<TT> cls, Class<? extends TT> cls2, v<? super TT> vVar) {
        return new TypeAdapters$34(cls, cls2, vVar);
    }

    public static <T1> w b(Class<T1> cls, v<T1> vVar) {
        return new TypeAdapters$35(cls, vVar);
    }
}

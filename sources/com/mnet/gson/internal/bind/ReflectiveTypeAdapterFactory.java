package com.mnet.gson.internal.bind;

import com.mnet.gson.d;
import com.mnet.gson.e;
import com.mnet.gson.internal.Excluder;
import com.mnet.gson.internal.c;
import com.mnet.gson.internal.h;
import com.mnet.gson.t;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import mnetinternal.g;
import mnetinternal.i;
import mnetinternal.j;

public final class ReflectiveTypeAdapterFactory implements w {
    private final c a;
    private final d b;
    private final Excluder c;
    private final JsonAdapterAnnotationTypeAdapterFactory d;

    public ReflectiveTypeAdapterFactory(c cVar, d dVar, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory) {
        this.a = cVar;
        this.b = dVar;
        this.c = excluder;
        this.d = jsonAdapterAnnotationTypeAdapterFactory;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.c);
    }

    static boolean a(Field field, boolean z, Excluder excluder) {
        return !excluder.a(field.getType(), z) && !excluder.a(field, z);
    }

    private List<String> a(Field field) {
        mnetinternal.c cVar = (mnetinternal.c) field.getAnnotation(mnetinternal.c.class);
        if (cVar == null) {
            return Collections.singletonList(this.b.a(field));
        }
        String a2 = cVar.a();
        String[] b2 = cVar.b();
        if (b2.length == 0) {
            return Collections.singletonList(a2);
        }
        ArrayList arrayList = new ArrayList(b2.length + 1);
        arrayList.add(a2);
        for (String add : b2) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        Class<? super T> a2 = gVar.a();
        if (!Object.class.isAssignableFrom(a2)) {
            return null;
        }
        return new a(this.a.a(gVar), a(eVar, (g<?>) gVar, (Class<?>) a2));
    }

    private b a(e eVar, Field field, String str, g<?> gVar, boolean z, boolean z2) {
        e eVar2 = eVar;
        g<?> gVar2 = gVar;
        final boolean a2 = h.a((Type) gVar.a());
        Field field2 = field;
        mnetinternal.b bVar = (mnetinternal.b) field.getAnnotation(mnetinternal.b.class);
        v<?> a3 = bVar != null ? this.d.a(this.a, eVar, gVar2, bVar) : null;
        final boolean z3 = a3 != null;
        if (a3 == null) {
            a3 = eVar.a(gVar2);
        }
        final v<?> vVar = a3;
        final Field field3 = field;
        final e eVar3 = eVar;
        final g<?> gVar3 = gVar;
        return new b(str, z, z2) {
            /* access modifiers changed from: package-private */
            public void a(j jVar, Object obj) {
                v vVar;
                Object obj2 = field3.get(obj);
                if (z3) {
                    vVar = vVar;
                } else {
                    vVar = new h(eVar3, vVar, gVar3.b());
                }
                vVar.write(jVar, obj2);
            }

            /* access modifiers changed from: package-private */
            public void a(mnetinternal.h hVar, Object obj) {
                Object read = vVar.read(hVar);
                if (read != null || !a2) {
                    field3.set(obj, read);
                }
            }

            public boolean a(Object obj) {
                if (this.i && field3.get(obj) != obj) {
                    return true;
                }
                return false;
            }
        };
    }

    private Map<String, b> a(e eVar, g<?> gVar, Class<?> cls) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b2 = gVar.b();
        g<?> gVar2 = gVar;
        Class<? super Object> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            boolean z = false;
            int i = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a2 = a(field, true);
                boolean a3 = a(field, z);
                if (a2 || a3) {
                    field.setAccessible(true);
                    Type a4 = com.mnet.gson.internal.b.a(gVar2.b(), (Class<?>) cls2, field.getGenericType());
                    List<String> a5 = a(field);
                    b bVar = null;
                    int i2 = 0;
                    while (i2 < a5.size()) {
                        String str = a5.get(i2);
                        boolean z2 = i2 != 0 ? false : a2;
                        String str2 = str;
                        int i3 = i2;
                        b bVar2 = bVar;
                        List<String> list = a5;
                        Field field2 = field;
                        bVar = bVar2 == null ? (b) linkedHashMap.put(str2, a(eVar, field, str2, g.a(a4), z2, a3)) : bVar2;
                        i2 = i3 + 1;
                        a2 = z2;
                        a5 = list;
                        field = field2;
                    }
                    b bVar3 = bVar;
                    if (bVar3 != null) {
                        throw new IllegalArgumentException(b2 + " declares multiple JSON fields named " + bVar3.h);
                    }
                }
                i++;
                z = false;
            }
            gVar2 = g.a(com.mnet.gson.internal.b.a(gVar2.b(), (Class<?>) cls2, cls2.getGenericSuperclass()));
            cls2 = gVar2.a();
        }
        return linkedHashMap;
    }

    static abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        /* access modifiers changed from: package-private */
        public abstract void a(mnetinternal.h hVar, Object obj);

        /* access modifiers changed from: package-private */
        public abstract void a(j jVar, Object obj);

        /* access modifiers changed from: package-private */
        public abstract boolean a(Object obj);

        protected b(String str, boolean z, boolean z2) {
            this.h = str;
            this.i = z;
            this.j = z2;
        }
    }

    public static final class a<T> extends v<T> {
        private final com.mnet.gson.internal.g<T> a;
        private final Map<String, b> b;

        a(com.mnet.gson.internal.g<T> gVar, Map<String, b> map) {
            this.a = gVar;
            this.b = map;
        }

        public T read(mnetinternal.h hVar) {
            if (hVar.f() == i.NULL) {
                hVar.j();
                return null;
            }
            T a2 = this.a.a();
            try {
                hVar.c();
                while (hVar.e()) {
                    b bVar = this.b.get(hVar.g());
                    if (bVar != null) {
                        if (bVar.j) {
                            bVar.a(hVar, (Object) a2);
                        }
                    }
                    hVar.n();
                }
                hVar.d();
                return a2;
            } catch (IllegalStateException e) {
                throw new t((Throwable) e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void write(j jVar, T t) {
            if (t == null) {
                jVar.f();
                return;
            }
            jVar.d();
            try {
                for (b next : this.b.values()) {
                    if (next.a(t)) {
                        jVar.a(next.h);
                        next.a(jVar, (Object) t);
                    }
                }
                jVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }
}

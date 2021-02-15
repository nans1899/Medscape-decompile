package com.mnet.gson.internal;

import com.mnet.gson.a;
import com.mnet.gson.b;
import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import mnetinternal.d;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;

public final class Excluder implements w, Cloneable {
    public static final Excluder a = new Excluder();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private boolean e;
    private List<a> f = Collections.emptyList();
    private List<a> g = Collections.emptyList();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Excluder clone() {
        try {
            return (Excluder) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        Class<? super T> a2 = gVar.a();
        final boolean a3 = a((Class<?>) a2, true);
        final boolean a4 = a((Class<?>) a2, false);
        if (!a3 && !a4) {
            return null;
        }
        final e eVar2 = eVar;
        final g<T> gVar2 = gVar;
        return new v<T>() {
            private v<T> f;

            public T read(h hVar) {
                if (!a4) {
                    return a().read(hVar);
                }
                hVar.n();
                return null;
            }

            public void write(j jVar, T t) {
                if (a3) {
                    jVar.f();
                } else {
                    a().write(jVar, t);
                }
            }

            private v<T> a() {
                v<T> vVar = this.f;
                if (vVar != null) {
                    return vVar;
                }
                v<T> a2 = eVar2.a((w) Excluder.this, gVar2);
                this.f = a2;
                return a2;
            }
        };
    }

    public boolean a(Field field, boolean z) {
        mnetinternal.a aVar;
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.b != -1.0d && !a((d) field.getAnnotation(d.class), (mnetinternal.e) field.getAnnotation(mnetinternal.e.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.e && ((aVar = (mnetinternal.a) field.getAnnotation(mnetinternal.a.class)) == null || (!z ? !aVar.b() : !aVar.a()))) {
            return true;
        }
        if ((!this.d && b(field.getType())) || a(field.getType())) {
            return true;
        }
        List<a> list = z ? this.f : this.g;
        if (list.isEmpty()) {
            return false;
        }
        b bVar = new b(field);
        for (a a2 : list) {
            if (a2.a(bVar)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((d) cls.getAnnotation(d.class), (mnetinternal.e) cls.getAnnotation(mnetinternal.e.class))) {
            return true;
        }
        if ((!this.d && b(cls)) || a(cls)) {
            return true;
        }
        for (a a2 : z ? this.f : this.g) {
            if (a2.a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(d dVar, mnetinternal.e eVar) {
        return a(dVar) && a(eVar);
    }

    private boolean a(d dVar) {
        return dVar == null || dVar.a() <= this.b;
    }

    private boolean a(mnetinternal.e eVar) {
        return eVar == null || eVar.a() > this.b;
    }
}

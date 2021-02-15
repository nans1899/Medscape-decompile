package mnetinternal;

import com.mnet.gson.internal.a;
import com.mnet.gson.internal.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class g<T> {
    final Class<? super T> a;
    final Type b;
    final int c = this.b.hashCode();

    protected g() {
        Type a2 = a(getClass());
        this.b = a2;
        this.a = b.e(a2);
    }

    g(Type type) {
        Type d = b.d((Type) a.a(type));
        this.b = d;
        this.a = b.e(d);
    }

    static Type a(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return b.d(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public final Class<? super T> a() {
        return this.a;
    }

    public final Type b() {
        return this.b;
    }

    public final int hashCode() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof g) && b.a(this.b, ((g) obj).b);
    }

    public final String toString() {
        return b.f(this.b);
    }

    public static g<?> a(Type type) {
        return new g<>(type);
    }

    public static <T> g<T> b(Class<T> cls) {
        return new g<>(cls);
    }
}

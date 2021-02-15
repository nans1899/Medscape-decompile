package com.mnet.gson.internal;

import com.mnet.gson.g;
import com.mnet.gson.l;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class c {
    private final Map<Type, g<?>> a;

    public c(Map<Type, g<?>> map) {
        this.a = map;
    }

    public <T> g<T> a(mnetinternal.g<T> gVar) {
        final Type b = gVar.b();
        Class<? super T> a2 = gVar.a();
        final g gVar2 = this.a.get(b);
        if (gVar2 != null) {
            return new g<T>() {
                public T a() {
                    return gVar2.a(b);
                }
            };
        }
        final g gVar3 = this.a.get(a2);
        if (gVar3 != null) {
            return new g<T>() {
                public T a() {
                    return gVar3.a(b);
                }
            };
        }
        g<T> a3 = a(a2);
        if (a3 != null) {
            return a3;
        }
        g<T> a4 = a(b, a2);
        if (a4 != null) {
            return a4;
        }
        return b(b, a2);
    }

    private <T> g<T> a(Class<? super T> cls) {
        try {
            final Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new g<T>() {
                public T a() {
                    try {
                        return declaredConstructor.newInstance((Object[]) null);
                    } catch (InstantiationException e) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e);
                    } catch (InvocationTargetException e2) {
                        throw new RuntimeException("Failed to invoke " + declaredConstructor + " with no args", e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> g<T> a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new g<T>() {
                    public T a() {
                        return new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new g<T>() {
                    public T a() {
                        Type type = type;
                        if (type instanceof ParameterizedType) {
                            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type2 instanceof Class) {
                                return EnumSet.noneOf((Class) type2);
                            }
                            throw new l("Invalid EnumSet type: " + type.toString());
                        }
                        throw new l("Invalid EnumSet type: " + type.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new g<T>() {
                    public T a() {
                        return new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new g<T>() {
                    public T a() {
                        return new ArrayDeque();
                    }
                };
            }
            return new g<T>() {
                public T a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new g<T>() {
                    public T a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new g<T>() {
                    public T a() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new g<T>() {
                    public T a() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(mnetinternal.g.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
                return new g<T>() {
                    public T a() {
                        return new f();
                    }
                };
            }
            return new g<T>() {
                public T a() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    private <T> g<T> b(final Type type, final Class<? super T> cls) {
        return new g<T>() {
            private final j d = j.a();

            public T a() {
                try {
                    return this.d.a(cls);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to invoke no-args constructor for " + type + ". Register an InstanceCreator with Gson for this type may fix this problem.", e);
                }
            }
        };
    }

    public String toString() {
        return this.a.toString();
    }
}

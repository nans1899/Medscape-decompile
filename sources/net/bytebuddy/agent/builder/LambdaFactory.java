package net.bytebuddy.agent.builder;

import java.lang.instrument.ClassFileTransformer;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.implementation.auxiliary.TypeProxy;

@HashCodeAndEqualsPlugin.Enhance
public class LambdaFactory {
    public static final Map<ClassFileTransformer, LambdaFactory> CLASS_FILE_TRANSFORMERS = new ConcurrentHashMap();
    private static final String FIELD_NAME = "CLASS_FILE_TRANSFORMERS";
    private final Method dispatcher;
    private final Object target;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LambdaFactory lambdaFactory = (LambdaFactory) obj;
        return this.target.equals(lambdaFactory.target) && this.dispatcher.equals(lambdaFactory.dispatcher);
    }

    public int hashCode() {
        return ((527 + this.target.hashCode()) * 31) + this.dispatcher.hashCode();
    }

    public LambdaFactory(Object obj, Method method) {
        this.target = obj;
        this.dispatcher = method;
    }

    public static boolean register(ClassFileTransformer classFileTransformer, Object obj) {
        Class cls;
        Map map;
        boolean isEmpty;
        ClassFileTransformer classFileTransformer2 = classFileTransformer;
        Class<LambdaFactory> cls2 = LambdaFactory.class;
        try {
            TypeDescription of = TypeDescription.ForLoadedType.of(cls2);
            cls = ClassInjector.UsingReflection.ofSystemClassLoader().inject(Collections.singletonMap(of, ClassFileLocator.ForClassLoader.read((Class<?>) cls2))).get(of);
            map = (Map) cls.getField(FIELD_NAME).get((Object) null);
            synchronized (map) {
                isEmpty = map.isEmpty();
                map.put(classFileTransformer2, cls.getConstructor(new Class[]{Object.class, Method.class}).newInstance(new Object[]{obj, obj.getClass().getMethod(TypeProxy.REFLECTION_METHOD, new Class[]{Object.class, String.class, Object.class, Object.class, Object.class, Object.class, Boolean.TYPE, List.class, List.class, Collection.class})}));
            }
            return isEmpty;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Could not register class file transformer", e2);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static boolean release(ClassFileTransformer classFileTransformer) {
        boolean z;
        try {
            Map map = (Map) ClassLoader.getSystemClassLoader().loadClass(LambdaFactory.class.getName()).getField(FIELD_NAME).get((Object) null);
            synchronized (map) {
                z = map.remove(classFileTransformer) != null && map.isEmpty();
            }
            return z;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Could not release class file transformer", e2);
        }
    }

    private byte[] invoke(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2, Collection<ClassFileTransformer> collection) {
        try {
            return (byte[]) this.dispatcher.invoke(this.target, new Object[]{obj, str, obj2, obj3, obj4, obj5, Boolean.valueOf(z), list, list2, collection});
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Cannot create class for lambda expression", e2);
        }
    }

    public static byte[] make(Object obj, String str, Object obj2, Object obj3, Object obj4, Object obj5, boolean z, List<Class<?>> list, List<?> list2) {
        return CLASS_FILE_TRANSFORMERS.values().iterator().next().invoke(obj, str, obj2, obj3, obj4, obj5, z, list, list2, CLASS_FILE_TRANSFORMERS.keySet());
    }
}

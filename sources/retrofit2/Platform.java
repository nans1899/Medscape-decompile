package retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import org.objenesis.strategy.PlatformDescription;
import retrofit2.CallAdapter;
import retrofit2.Converter;

class Platform {
    private static final Platform PLATFORM = findPlatform();
    private final boolean hasJava8Types;
    @Nullable
    private final Constructor<MethodHandles.Lookup> lookupConstructor;

    /* access modifiers changed from: package-private */
    @Nullable
    public Executor defaultCallbackExecutor() {
        return null;
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        if (PlatformDescription.DALVIK.equals(System.getProperty("java.vm.name"))) {
            return new Android();
        }
        return new Platform(true);
    }

    Platform(boolean z) {
        this.hasJava8Types = z;
        Constructor<MethodHandles.Lookup> constructor = null;
        if (z) {
            Class<MethodHandles.Lookup> cls = MethodHandles.Lookup.class;
            try {
                constructor = cls.getDeclaredConstructor(new Class[]{Class.class, Integer.TYPE});
                constructor.setAccessible(true);
            } catch (NoClassDefFoundError | NoSuchMethodException unused) {
            }
        }
        this.lookupConstructor = constructor;
    }

    /* access modifiers changed from: package-private */
    public List<? extends CallAdapter.Factory> defaultCallAdapterFactories(@Nullable Executor executor) {
        DefaultCallAdapterFactory defaultCallAdapterFactory = new DefaultCallAdapterFactory(executor);
        if (!this.hasJava8Types) {
            return Collections.singletonList(defaultCallAdapterFactory);
        }
        return Arrays.asList(new CallAdapter.Factory[]{CompletableFutureCallAdapterFactory.INSTANCE, defaultCallAdapterFactory});
    }

    /* access modifiers changed from: package-private */
    public int defaultCallAdapterFactoriesSize() {
        return this.hasJava8Types ? 2 : 1;
    }

    /* access modifiers changed from: package-private */
    public List<? extends Converter.Factory> defaultConverterFactories() {
        return this.hasJava8Types ? Collections.singletonList(OptionalConverterFactory.INSTANCE) : Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public int defaultConverterFactoriesSize() {
        return this.hasJava8Types ? 1 : 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isDefaultMethod(Method method) {
        return this.hasJava8Types && method.isDefault();
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
        MethodHandles.Lookup lookup;
        Constructor<MethodHandles.Lookup> constructor = this.lookupConstructor;
        if (constructor != null) {
            lookup = constructor.newInstance(new Object[]{cls, -1});
        } else {
            lookup = MethodHandles.lookup();
        }
        return lookup.unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }

    static final class Android extends Platform {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        Android() {
            super(Build.VERSION.SDK_INT >= 24);
        }

        public Executor defaultCallbackExecutor() {
            return new MainThreadExecutor();
        }

        /* access modifiers changed from: package-private */
        @Nullable
        public Object invokeDefaultMethod(Method method, Class<?> cls, Object obj, Object... objArr) throws Throwable {
            if (Build.VERSION.SDK_INT >= 26) {
                return Platform.super.invokeDefaultMethod(method, cls, obj, objArr);
            }
            throw new UnsupportedOperationException("Calling default methods on API 24 and 25 is not supported");
        }

        static final class MainThreadExecutor implements Executor {
            private final Handler handler = new Handler(Looper.getMainLooper());

            MainThreadExecutor() {
            }

            public void execute(Runnable runnable) {
                this.handler.post(runnable);
            }
        }
    }
}

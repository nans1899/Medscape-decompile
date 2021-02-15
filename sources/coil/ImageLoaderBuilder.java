package coil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import coil.ComponentRegistry;
import coil.EventListener;
import coil.bitmappool.BitmapPool;
import coil.memory.BitmapReferenceCounter;
import coil.memory.EmptyWeakMemoryCache;
import coil.memory.MemoryCache;
import coil.memory.RealWeakMemoryCache;
import coil.memory.WeakMemoryCache;
import coil.request.CachePolicy;
import coil.size.Precision;
import coil.transition.CrossfadeTransition;
import coil.transition.Transition;
import coil.util.Contexts;
import coil.util.Extensions;
import coil.util.Logger;
import coil.util.Utils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.Call;
import okhttp3.OkHttpClient;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0015J\u0010\u0010\u0007\u001a\u00020\u00002\b\b\u0001\u0010\u0019\u001a\u00020\bJ\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\t\u001a\u00020\u00002\b\b\u0001\u0010\u0019\u001a\u00020\bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u000bH\u0002J\u0014\u0010\n\u001a\u00020\u00002\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0 J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J\"\u0010!\u001a\u00020\u00002\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#¢\u0006\u0002\b&H\bJ\u000e\u0010'\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0015J\u000e\u0010'\u001a\u00020\u00002\u0006\u0010(\u001a\u00020)J\u000e\u0010*\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.J\u0010\u0010/\u001a\u00020\u00002\b\u00100\u001a\u0004\u0018\u000101J\u0010\u0010/\u001a\u00020\u00002\b\b\u0001\u00102\u001a\u00020)J\u0010\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u000205H\u0007J\u0010\u00103\u001a\u00020\u00002\u0006\u00106\u001a\u00020\u000fH\u0007J\u0010\u00107\u001a\u00020\u00002\b\u00100\u001a\u0004\u0018\u000101J\u0010\u00107\u001a\u00020\u00002\b\b\u0001\u00102\u001a\u00020)J\u0010\u0010\u0010\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u000e\u00108\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,J\u000e\u00109\u001a\u00020\u00002\u0006\u0010+\u001a\u00020,J\u0014\u0010:\u001a\u00020\u00002\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020;0 J\u000e\u0010:\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;J\u0010\u0010<\u001a\u00020\u00002\b\u00100\u001a\u0004\u0018\u000101J\u0010\u0010<\u001a\u00020\u00002\b\b\u0001\u00102\u001a\u00020)J\u000e\u0010=\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0015J\u0010\u0010?\u001a\u00020\u00002\u0006\u0010?\u001a\u00020@H\u0007R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00030\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcoil/ImageLoaderBuilder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "applicationContext", "kotlin.jvm.PlatformType", "availableMemoryPercentage", "", "bitmapPoolPercentage", "callFactory", "Lokhttp3/Call$Factory;", "defaults", "Lcoil/DefaultRequestOptions;", "eventListenerFactory", "Lcoil/EventListener$Factory;", "logger", "Lcoil/util/Logger;", "registry", "Lcoil/ComponentRegistry;", "trackWeakReferences", "", "allowHardware", "enable", "allowRgb565", "multiplier", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "build", "Lcoil/ImageLoader;", "buildDefaultCallFactory", "initializer", "Lkotlin/Function0;", "componentRegistry", "builder", "Lkotlin/Function1;", "Lcoil/ComponentRegistry$Builder;", "", "Lkotlin/ExtensionFunctionType;", "crossfade", "durationMillis", "", "diskCachePolicy", "policy", "Lcoil/request/CachePolicy;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "error", "drawable", "Landroid/graphics/drawable/Drawable;", "drawableResId", "eventListener", "listener", "Lcoil/EventListener;", "factory", "fallback", "memoryCachePolicy", "networkCachePolicy", "okHttpClient", "Lokhttp3/OkHttpClient;", "placeholder", "precision", "Lcoil/size/Precision;", "transition", "Lcoil/transition/Transition;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageLoaderBuilder.kt */
public final class ImageLoaderBuilder {
    /* access modifiers changed from: private */
    public final Context applicationContext;
    private double availableMemoryPercentage;
    private double bitmapPoolPercentage;
    private Call.Factory callFactory;
    private DefaultRequestOptions defaults = new DefaultRequestOptions((CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4095, (DefaultConstructorMarker) null);
    private EventListener.Factory eventListenerFactory;
    private Logger logger;
    private ComponentRegistry registry;
    private boolean trackWeakReferences;

    public ImageLoaderBuilder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.applicationContext = context.getApplicationContext();
        Utils utils = Utils.INSTANCE;
        Context context2 = this.applicationContext;
        Intrinsics.checkExpressionValueIsNotNull(context2, "applicationContext");
        this.availableMemoryPercentage = utils.getDefaultAvailableMemoryPercentage(context2);
        this.bitmapPoolPercentage = Utils.INSTANCE.getDefaultBitmapPoolPercentage();
        this.trackWeakReferences = true;
    }

    public final ImageLoaderBuilder okHttpClient(OkHttpClient okHttpClient) {
        Intrinsics.checkParameterIsNotNull(okHttpClient, "okHttpClient");
        return callFactory((Call.Factory) okHttpClient);
    }

    public final ImageLoaderBuilder okHttpClient(Function0<? extends OkHttpClient> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "initializer");
        return callFactory((Function0<? extends Call.Factory>) function0);
    }

    public final ImageLoaderBuilder callFactory(Call.Factory factory) {
        Intrinsics.checkParameterIsNotNull(factory, "callFactory");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.callFactory = factory;
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder callFactory(Function0<? extends Call.Factory> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "initializer");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.callFactory = Extensions.lazyCallFactory(function0);
        return imageLoaderBuilder;
    }

    public final /* synthetic */ ImageLoaderBuilder componentRegistry(Function1<? super ComponentRegistry.Builder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        ComponentRegistry.Builder builder = new ComponentRegistry.Builder();
        function1.invoke(builder);
        return componentRegistry(builder.build());
    }

    public final ImageLoaderBuilder componentRegistry(ComponentRegistry componentRegistry) {
        Intrinsics.checkParameterIsNotNull(componentRegistry, "registry");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.registry = componentRegistry;
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder availableMemoryPercentage(double d) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        if (d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && d <= 1.0d) {
            imageLoaderBuilder.availableMemoryPercentage = d;
            return imageLoaderBuilder;
        }
        throw new IllegalArgumentException("Multiplier must be within the range [0.0, 1.0].".toString());
    }

    public final ImageLoaderBuilder bitmapPoolPercentage(double d) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        if (d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && d <= 1.0d) {
            imageLoaderBuilder.bitmapPoolPercentage = d;
            return imageLoaderBuilder;
        }
        throw new IllegalArgumentException("Multiplier must be within the range [0.0, 1.0].".toString());
    }

    public final ImageLoaderBuilder dispatcher(CoroutineDispatcher coroutineDispatcher) {
        CoroutineDispatcher coroutineDispatcher2 = coroutineDispatcher;
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher2, "dispatcher");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, coroutineDispatcher2, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4094, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder allowHardware(boolean z) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, z, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4079, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder allowRgb565(boolean z) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, z, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4063, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder trackWeakReferences(boolean z) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.trackWeakReferences = z;
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder eventListener(EventListener eventListener) {
        Intrinsics.checkParameterIsNotNull(eventListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return eventListener(EventListener.Factory.Companion.create(eventListener));
    }

    public final ImageLoaderBuilder eventListener(EventListener.Factory factory) {
        Intrinsics.checkParameterIsNotNull(factory, "factory");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.eventListenerFactory = factory;
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder crossfade(boolean z) {
        return crossfade(z ? 100 : 0);
    }

    public final ImageLoaderBuilder crossfade(int i) {
        return transition(i > 0 ? new CrossfadeTransition(i) : Transition.NONE);
    }

    public final ImageLoaderBuilder transition(Transition transition) {
        Transition transition2 = transition;
        Intrinsics.checkParameterIsNotNull(transition2, "transition");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, transition2, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4093, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder precision(Precision precision) {
        Precision precision2 = precision;
        Intrinsics.checkParameterIsNotNull(precision2, "precision");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, precision2, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4091, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder bitmapConfig(Bitmap.Config config) {
        Bitmap.Config config2 = config;
        Intrinsics.checkParameterIsNotNull(config2, "bitmapConfig");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, config2, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4087, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder placeholder(int i) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        DefaultRequestOptions defaultRequestOptions = imageLoaderBuilder.defaults;
        Context context = imageLoaderBuilder.applicationContext;
        Intrinsics.checkExpressionValueIsNotNull(context, "applicationContext");
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(defaultRequestOptions, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, Contexts.getDrawableCompat(context, i), (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4031, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder placeholder(Drawable drawable) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, drawable, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4031, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder error(int i) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        DefaultRequestOptions defaultRequestOptions = imageLoaderBuilder.defaults;
        Context context = imageLoaderBuilder.applicationContext;
        Intrinsics.checkExpressionValueIsNotNull(context, "applicationContext");
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(defaultRequestOptions, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, Contexts.getDrawableCompat(context, i), (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 3967, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder error(Drawable drawable) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, drawable, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 3967, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder fallback(int i) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        DefaultRequestOptions defaultRequestOptions = imageLoaderBuilder.defaults;
        Context context = imageLoaderBuilder.applicationContext;
        Intrinsics.checkExpressionValueIsNotNull(context, "applicationContext");
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(defaultRequestOptions, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, Contexts.getDrawableCompat(context, i), (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 3967, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder fallback(Drawable drawable) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, drawable, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 3967, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder memoryCachePolicy(CachePolicy cachePolicy) {
        CachePolicy cachePolicy2 = cachePolicy;
        Intrinsics.checkParameterIsNotNull(cachePolicy2, "policy");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, cachePolicy2, (CachePolicy) null, (CachePolicy) null, 3583, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder diskCachePolicy(CachePolicy cachePolicy) {
        CachePolicy cachePolicy2 = cachePolicy;
        Intrinsics.checkParameterIsNotNull(cachePolicy2, "policy");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, cachePolicy2, (CachePolicy) null, 3071, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder networkCachePolicy(CachePolicy cachePolicy) {
        CachePolicy cachePolicy2 = cachePolicy;
        Intrinsics.checkParameterIsNotNull(cachePolicy2, "policy");
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.defaults = DefaultRequestOptions.copy$default(imageLoaderBuilder.defaults, (CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, cachePolicy2, 2047, (Object) null);
        return imageLoaderBuilder;
    }

    public final ImageLoaderBuilder logger(Logger logger2) {
        ImageLoaderBuilder imageLoaderBuilder = this;
        imageLoaderBuilder.logger = logger2;
        return imageLoaderBuilder;
    }

    public final ImageLoader build() {
        Utils utils = Utils.INSTANCE;
        Context context = this.applicationContext;
        Intrinsics.checkExpressionValueIsNotNull(context, "applicationContext");
        long calculateAvailableMemorySize = utils.calculateAvailableMemorySize(context, this.availableMemoryPercentage);
        int i = (int) (this.bitmapPoolPercentage * ((double) calculateAvailableMemorySize));
        int i2 = (int) (calculateAvailableMemorySize - ((long) i));
        BitmapPool create = BitmapPool.Companion.create(i, this.logger);
        WeakMemoryCache realWeakMemoryCache = this.trackWeakReferences ? new RealWeakMemoryCache() : EmptyWeakMemoryCache.INSTANCE;
        BitmapReferenceCounter bitmapReferenceCounter = new BitmapReferenceCounter(realWeakMemoryCache, create, this.logger);
        MemoryCache invoke = MemoryCache.Companion.invoke(realWeakMemoryCache, bitmapReferenceCounter, i2, this.logger);
        Context context2 = this.applicationContext;
        Intrinsics.checkExpressionValueIsNotNull(context2, "applicationContext");
        DefaultRequestOptions defaultRequestOptions = this.defaults;
        Call.Factory factory = this.callFactory;
        if (factory == null) {
            factory = buildDefaultCallFactory();
        }
        Call.Factory factory2 = factory;
        EventListener.Factory factory3 = this.eventListenerFactory;
        if (factory3 == null) {
            factory3 = EventListener.Factory.NONE;
        }
        EventListener.Factory factory4 = factory3;
        ComponentRegistry componentRegistry = this.registry;
        if (componentRegistry == null) {
            componentRegistry = new ComponentRegistry();
        }
        return new RealImageLoader(context2, defaultRequestOptions, create, bitmapReferenceCounter, invoke, realWeakMemoryCache, factory2, factory4, componentRegistry, this.logger);
    }

    private final Call.Factory buildDefaultCallFactory() {
        return Extensions.lazyCallFactory(new ImageLoaderBuilder$buildDefaultCallFactory$1(this));
    }
}

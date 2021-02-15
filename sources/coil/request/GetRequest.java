package coil.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.Lifecycle;
import coil.decode.Decoder;
import coil.fetch.Fetcher;
import coil.request.Request;
import coil.size.Precision;
import coil.size.Scale;
import coil.size.SizeResolver;
import coil.target.Target;
import coil.transform.Transformation;
import coil.transition.Transition;
import com.facebook.share.internal.ShareConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.Headers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 o2\u00020\u0001:\u0001oB\u0002\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\t\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\u001c\u0010\u001a\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0018\u00010\u001b\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\b\u0010 \u001a\u0004\u0018\u00010!\u0012\b\u0010\"\u001a\u0004\u0018\u00010!\u0012\b\u0010#\u001a\u0004\u0018\u00010$\u0012\b\u0010%\u001a\u0004\u0018\u00010$\u0012\b\u0010&\u001a\u0004\u0018\u00010$\u0012\u0006\u0010'\u001a\u00020(\u0012\u0006\u0010)\u001a\u00020*\u0012\b\b\u0001\u0010+\u001a\u00020,\u0012\b\u0010-\u001a\u0004\u0018\u00010.\u0012\b\b\u0001\u0010/\u001a\u00020,\u0012\b\u00100\u001a\u0004\u0018\u00010.¢\u0006\u0002\u00101J\u0012\u0010m\u001a\u00020n2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0018\u0010 \u001a\u0004\u0018\u00010!X\u0004¢\u0006\n\n\u0002\u00106\u001a\u0004\b4\u00105R\u0018\u0010\"\u001a\u0004\u0018\u00010!X\u0004¢\u0006\n\n\u0002\u00106\u001a\u0004\b7\u00105R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0016\u0010%\u001a\u0004\u0018\u00010$X\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0016\u0010\f\u001a\u0004\u0018\u00010\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0016\u0010-\u001a\u0004\u0018\u00010.X\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0014\u0010+\u001a\u00020,X\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0016\u00100\u001a\u0004\u0018\u00010.X\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010GR\u0014\u0010/\u001a\u00020,X\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010IR*\u0010\u001a\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d\u0018\u00010\u001bX\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR\u0014\u0010'\u001a\u00020(X\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010QR\u0016\u0010R\u001a\u0004\u0018\u00010SX\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010WR\u0016\u0010#\u001a\u0004\u0018\u00010$X\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010CR\u0016\u0010&\u001a\u0004\u0018\u00010$X\u0004¢\u0006\b\n\u0000\u001a\u0004\bY\u0010CR\u0014\u0010)\u001a\u00020*X\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u0004\u0018\u00010.X\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010GR\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010_R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010aR\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010cR\u0016\u0010d\u001a\u0004\u0018\u00010eX\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010gR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u00103R\u0016\u0010i\u001a\u0004\u0018\u00010jX\u0004¢\u0006\b\n\u0000\u001a\u0004\bk\u0010l¨\u0006p"}, d2 = {"Lcoil/request/GetRequest;", "Lcoil/request/Request;", "context", "Landroid/content/Context;", "data", "", "key", "", "aliasKeys", "", "listener", "Lcoil/request/Request$Listener;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "transformations", "Lcoil/transform/Transformation;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "colorSpace", "Landroid/graphics/ColorSpace;", "sizeResolver", "Lcoil/size/SizeResolver;", "scale", "Lcoil/size/Scale;", "precision", "Lcoil/size/Precision;", "fetcher", "Lkotlin/Pair;", "Ljava/lang/Class;", "Lcoil/fetch/Fetcher;", "decoder", "Lcoil/decode/Decoder;", "allowHardware", "", "allowRgb565", "memoryCachePolicy", "Lcoil/request/CachePolicy;", "diskCachePolicy", "networkCachePolicy", "headers", "Lokhttp3/Headers;", "parameters", "Lcoil/request/Parameters;", "errorResId", "", "errorDrawable", "Landroid/graphics/drawable/Drawable;", "fallbackResId", "fallbackDrawable", "(Landroid/content/Context;Ljava/lang/Object;Ljava/lang/String;Ljava/util/List;Lcoil/request/Request$Listener;Lkotlinx/coroutines/CoroutineDispatcher;Ljava/util/List;Landroid/graphics/Bitmap$Config;Landroid/graphics/ColorSpace;Lcoil/size/SizeResolver;Lcoil/size/Scale;Lcoil/size/Precision;Lkotlin/Pair;Lcoil/decode/Decoder;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lokhttp3/Headers;Lcoil/request/Parameters;ILandroid/graphics/drawable/Drawable;ILandroid/graphics/drawable/Drawable;)V", "getAliasKeys", "()Ljava/util/List;", "getAllowHardware", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getAllowRgb565", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "getColorSpace", "()Landroid/graphics/ColorSpace;", "getContext", "()Landroid/content/Context;", "getData", "()Ljava/lang/Object;", "getDecoder", "()Lcoil/decode/Decoder;", "getDiskCachePolicy", "()Lcoil/request/CachePolicy;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "getErrorDrawable$coil_base_release", "()Landroid/graphics/drawable/Drawable;", "getErrorResId$coil_base_release", "()I", "getFallbackDrawable$coil_base_release", "getFallbackResId$coil_base_release", "getFetcher", "()Lkotlin/Pair;", "getHeaders", "()Lokhttp3/Headers;", "getKey", "()Ljava/lang/String;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "getListener", "()Lcoil/request/Request$Listener;", "getMemoryCachePolicy", "getNetworkCachePolicy", "getParameters", "()Lcoil/request/Parameters;", "placeholder", "getPlaceholder", "getPrecision", "()Lcoil/size/Precision;", "getScale", "()Lcoil/size/Scale;", "getSizeResolver", "()Lcoil/size/SizeResolver;", "target", "Lcoil/target/Target;", "getTarget", "()Lcoil/target/Target;", "getTransformations", "transition", "Lcoil/transition/Transition;", "getTransition", "()Lcoil/transition/Transition;", "newBuilder", "Lcoil/request/GetRequestBuilder;", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Request.kt */
public final class GetRequest extends Request {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<String> aliasKeys;
    private final Boolean allowHardware;
    private final Boolean allowRgb565;
    private final Bitmap.Config bitmapConfig;
    private final ColorSpace colorSpace;
    private final Context context;
    private final Object data;
    private final Decoder decoder;
    private final CachePolicy diskCachePolicy;
    private final CoroutineDispatcher dispatcher;
    private final Drawable errorDrawable;
    private final int errorResId;
    private final Drawable fallbackDrawable;
    private final int fallbackResId;
    private final Pair<Class<?>, Fetcher<?>> fetcher;
    private final Headers headers;
    private final String key;
    private final Lifecycle lifecycle;
    private final Request.Listener listener;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Parameters parameters;
    private final Drawable placeholder;
    private final Precision precision;
    private final Scale scale;
    private final SizeResolver sizeResolver;
    private final Target target;
    private final List<Transformation> transformations;
    private final Transition transition;

    @JvmStatic
    public static final GetRequestBuilder builder(Context context2) {
        return Companion.builder(context2);
    }

    @JvmStatic
    public static final GetRequestBuilder builder(GetRequest getRequest) {
        return Companion.builder$default(Companion, getRequest, (Context) null, 2, (Object) null);
    }

    @JvmStatic
    public static final GetRequestBuilder builder(GetRequest getRequest, Context context2) {
        return Companion.builder(getRequest, context2);
    }

    public final GetRequestBuilder newBuilder() {
        return newBuilder$default(this, (Context) null, 1, (Object) null);
    }

    public Context getContext() {
        return this.context;
    }

    public Object getData() {
        return this.data;
    }

    public String getKey() {
        return this.key;
    }

    public List<String> getAliasKeys() {
        return this.aliasKeys;
    }

    public Request.Listener getListener() {
        return this.listener;
    }

    public CoroutineDispatcher getDispatcher() {
        return this.dispatcher;
    }

    public List<Transformation> getTransformations() {
        return this.transformations;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public ColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public SizeResolver getSizeResolver() {
        return this.sizeResolver;
    }

    public Scale getScale() {
        return this.scale;
    }

    public Precision getPrecision() {
        return this.precision;
    }

    public Pair<Class<?>, Fetcher<?>> getFetcher() {
        return this.fetcher;
    }

    public Decoder getDecoder() {
        return this.decoder;
    }

    public Boolean getAllowHardware() {
        return this.allowHardware;
    }

    public Boolean getAllowRgb565() {
        return this.allowRgb565;
    }

    public CachePolicy getMemoryCachePolicy() {
        return this.memoryCachePolicy;
    }

    public CachePolicy getDiskCachePolicy() {
        return this.diskCachePolicy;
    }

    public CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public Parameters getParameters() {
        return this.parameters;
    }

    public int getErrorResId$coil_base_release() {
        return this.errorResId;
    }

    public Drawable getErrorDrawable$coil_base_release() {
        return this.errorDrawable;
    }

    public int getFallbackResId$coil_base_release() {
        return this.fallbackResId;
    }

    public Drawable getFallbackDrawable$coil_base_release() {
        return this.fallbackDrawable;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetRequest(Context context2, Object obj, String str, List<String> list, Request.Listener listener2, CoroutineDispatcher coroutineDispatcher, List<? extends Transformation> list2, Bitmap.Config config, ColorSpace colorSpace2, SizeResolver sizeResolver2, Scale scale2, Precision precision2, Pair<? extends Class<?>, ? extends Fetcher<?>> pair, Decoder decoder2, Boolean bool, Boolean bool2, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, Headers headers2, Parameters parameters2, int i, Drawable drawable, int i2, Drawable drawable2) {
        super((DefaultConstructorMarker) null);
        Headers headers3 = headers2;
        Parameters parameters3 = parameters2;
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(list, "aliasKeys");
        Intrinsics.checkParameterIsNotNull(list2, "transformations");
        Intrinsics.checkParameterIsNotNull(headers3, "headers");
        Intrinsics.checkParameterIsNotNull(parameters3, "parameters");
        this.context = context2;
        this.data = obj;
        this.key = str;
        this.aliasKeys = list;
        this.listener = listener2;
        this.dispatcher = coroutineDispatcher;
        this.transformations = list2;
        this.bitmapConfig = config;
        this.colorSpace = colorSpace2;
        this.sizeResolver = sizeResolver2;
        this.scale = scale2;
        this.precision = precision2;
        this.fetcher = pair;
        this.decoder = decoder2;
        this.allowHardware = bool;
        this.allowRgb565 = bool2;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
        this.headers = headers3;
        this.parameters = parameters3;
        this.errorResId = i;
        this.errorDrawable = drawable;
        this.fallbackResId = i2;
        this.fallbackDrawable = drawable2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\b¢\u0006\u0002\b\u0007J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\b¢\u0006\u0002\b\u0007¨\u0006\n"}, d2 = {"Lcoil/request/GetRequest$Companion;", "", "()V", "Builder", "Lcoil/request/GetRequestBuilder;", "context", "Landroid/content/Context;", "builder", "request", "Lcoil/request/GetRequest;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Request.kt */
    public static final class Companion {
        @JvmStatic
        public final GetRequestBuilder builder(GetRequest getRequest) {
            return builder$default(this, getRequest, (Context) null, 2, (Object) null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final GetRequestBuilder builder(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new GetRequestBuilder(context);
        }

        public static /* synthetic */ GetRequestBuilder builder$default(Companion companion, GetRequest getRequest, Context context, int i, Object obj) {
            if ((i & 2) != 0) {
                context = getRequest.getContext();
            }
            Intrinsics.checkParameterIsNotNull(getRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new GetRequestBuilder(getRequest, context);
        }

        @JvmStatic
        public final GetRequestBuilder builder(GetRequest getRequest, Context context) {
            Intrinsics.checkParameterIsNotNull(getRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new GetRequestBuilder(getRequest, context);
        }
    }

    public Target getTarget() {
        return this.target;
    }

    public Transition getTransition() {
        return this.transition;
    }

    public Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    public Drawable getPlaceholder() {
        return this.placeholder;
    }

    public static /* synthetic */ GetRequestBuilder newBuilder$default(GetRequest getRequest, Context context2, int i, Object obj) {
        if ((i & 1) != 0) {
            context2 = getRequest.getContext();
        }
        return getRequest.newBuilder(context2);
    }

    public final GetRequestBuilder newBuilder(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        return new GetRequestBuilder(this, context2);
    }
}

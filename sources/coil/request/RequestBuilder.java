package coil.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.os.Build;
import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.fetch.Fetcher;
import coil.request.Parameters;
import coil.request.Request;
import coil.request.RequestBuilder;
import coil.size.PixelSize;
import coil.size.Precision;
import coil.size.Scale;
import coil.size.Size;
import coil.size.SizeResolver;
import coil.transform.Transformation;
import coil.util.Extensions;
import coil.util.Utils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.Headers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u000f\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0012\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\u001b\u00106\u001a\u00028\u00002\u0006\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u000b¢\u0006\u0002\u00109J\u001f\u0010\t\u001a\u00028\u00002\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0:\"\u00020\u000b¢\u0006\u0002\u0010;J\u0019\u0010\t\u001a\u00028\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010<J\u0013\u0010\f\u001a\u00028\u00002\u0006\u0010=\u001a\u00020\r¢\u0006\u0002\u0010>J\u0013\u0010\u000f\u001a\u00028\u00002\u0006\u0010=\u001a\u00020\r¢\u0006\u0002\u0010>J\u0013\u0010\u0010\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010?J\u0015\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u0013H\u0007¢\u0006\u0002\u0010@J\u0015\u0010\u0014\u001a\u00028\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010AJ\u0013\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010BJ\u0013\u0010\u0017\u001a\u00028\u00002\u0006\u0010C\u001a\u00020\u0018¢\u0006\u0002\u0010DJ\u0013\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010EJ\u0015\u0010F\u001a\u00028\u00002\b\u0010G\u001a\u0004\u0018\u00010\u001c¢\u0006\u0002\u0010HJ\u0015\u0010F\u001a\u00028\u00002\b\b\u0001\u0010I\u001a\u00020\u001e¢\u0006\u0002\u0010JJ\u0015\u0010K\u001a\u00028\u00002\b\u0010G\u001a\u0004\u0018\u00010\u001c¢\u0006\u0002\u0010HJ\u0015\u0010K\u001a\u00028\u00002\b\b\u0001\u0010I\u001a\u00020\u001e¢\u0006\u0002\u0010JJ(\u0010!\u001a\u00028\u0000\"\n\b\u0001\u0010L\u0018\u0001*\u00020\u00022\f\u0010!\u001a\b\u0012\u0004\u0012\u0002HL0$H\b¢\u0006\u0002\u0010MJ3\u0010!\u001a\u00028\u0000\"\b\b\u0001\u0010L*\u00020\u00022\f\u0010N\u001a\b\u0012\u0004\u0012\u0002HL0#2\f\u0010!\u001a\b\u0012\u0004\u0012\u0002HL0$H\u0001¢\u0006\u0002\u0010OJ\u0013\u0010%\u001a\u00028\u00002\u0006\u0010%\u001a\u00020P¢\u0006\u0002\u0010QJ\u0015\u0010'\u001a\u00028\u00002\b\u0010'\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010RJÌ\u0001\u0010(\u001a\u00028\u00002#\b\u0006\u0010S\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bU\u0012\b\b7\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020V0T2#\b\u0006\u0010W\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bU\u0012\b\b7\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020V0T28\b\u0006\u0010X\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bU\u0012\b\b7\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110Z¢\u0006\f\bU\u0012\b\b7\u0012\u0004\b\b([\u0012\u0004\u0012\u00020V0Y28\b\u0006\u0010\\\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\bU\u0012\b\b7\u0012\u0004\b\b(\u0006\u0012\u0013\u0012\u00110]¢\u0006\f\bU\u0012\b\b7\u0012\u0004\b\b(^\u0012\u0004\u0012\u00020V0YH\b¢\u0006\u0002\u0010_J\u0015\u0010(\u001a\u00028\u00002\b\u0010(\u001a\u0004\u0018\u00010)¢\u0006\u0002\u0010`J\u0013\u0010*\u001a\u00028\u00002\u0006\u0010C\u001a\u00020\u0018¢\u0006\u0002\u0010DJ\u0013\u0010+\u001a\u00028\u00002\u0006\u0010C\u001a\u00020\u0018¢\u0006\u0002\u0010DJ\u0013\u0010,\u001a\u00028\u00002\u0006\u0010,\u001a\u00020a¢\u0006\u0002\u0010bJ\u0013\u0010.\u001a\u00028\u00002\u0006\u0010.\u001a\u00020/¢\u0006\u0002\u0010cJ\u0013\u0010d\u001a\u00028\u00002\u0006\u00107\u001a\u00020\u000b¢\u0006\u0002\u0010RJ\u0013\u0010e\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u000b¢\u0006\u0002\u0010RJ\u0013\u00100\u001a\u00028\u00002\u0006\u00100\u001a\u000201¢\u0006\u0002\u0010fJ\u001b\u0010g\u001a\u00028\u00002\u0006\u00107\u001a\u00020\u000b2\u0006\u00108\u001a\u00020\u000b¢\u0006\u0002\u00109J+\u0010h\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u000b2\b\u00108\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u0010jJ\u0013\u0010k\u001a\u00028\u00002\u0006\u0010k\u001a\u00020l¢\u0006\u0002\u0010mJ\u0013\u0010k\u001a\u00028\u00002\u0006\u0010n\u001a\u000203¢\u0006\u0002\u0010oJ\u0015\u0010k\u001a\u00028\u00002\b\b\u0001\u0010k\u001a\u00020\u001e¢\u0006\u0002\u0010JJ\u001f\u0010k\u001a\u00028\u00002\b\b\u0001\u0010p\u001a\u00020\u001e2\b\b\u0001\u0010q\u001a\u00020\u001e¢\u0006\u0002\u0010rJ\u001f\u00104\u001a\u00028\u00002\u0012\u00104\u001a\n\u0012\u0006\b\u0001\u0012\u0002050:\"\u000205¢\u0006\u0002\u0010sJ\u0019\u00104\u001a\u00028\u00002\f\u00104\u001a\b\u0012\u0004\u0012\u0002050\n¢\u0006\u0002\u0010<R\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u0004\u0018\u00010\r8\u0004@\u0004X\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\r8\u0004@\u0004X\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00138\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u00020\u00048\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00168\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001c8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00020\u001e8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u0004\u0018\u00010\u001c8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010 \u001a\u00020\u001e8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R(\u0010!\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030#\u0012\b\u0012\u0006\u0012\u0002\b\u00030$\u0018\u00010\"8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u0004\u0018\u00010&8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\u0004\u0018\u00010\u000b8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\u0004\u0018\u00010)8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010*\u001a\u0004\u0018\u00010\u00188\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010+\u001a\u0004\u0018\u00010\u00188\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010,\u001a\u0004\u0018\u00010-8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u0004\u0018\u00010/8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\u0004\u0018\u0001018\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u0004\u0018\u0001038\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000R\u0018\u00104\u001a\b\u0012\u0004\u0012\u0002050\n8\u0004@\u0004X\u000e¢\u0006\u0002\n\u0000\u0001\u0002tu¨\u0006v"}, d2 = {"Lcoil/request/RequestBuilder;", "T", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "request", "Lcoil/request/Request;", "(Lcoil/request/Request;Landroid/content/Context;)V", "aliasKeys", "", "", "allowHardware", "", "Ljava/lang/Boolean;", "allowRgb565", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "colorSpace", "Landroid/graphics/ColorSpace;", "data", "decoder", "Lcoil/decode/Decoder;", "diskCachePolicy", "Lcoil/request/CachePolicy;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "errorDrawable", "Landroid/graphics/drawable/Drawable;", "errorResId", "", "fallbackDrawable", "fallbackResId", "fetcher", "Lkotlin/Pair;", "Ljava/lang/Class;", "Lcoil/fetch/Fetcher;", "headers", "Lokhttp3/Headers$Builder;", "key", "listener", "Lcoil/request/Request$Listener;", "memoryCachePolicy", "networkCachePolicy", "parameters", "Lcoil/request/Parameters$Builder;", "precision", "Lcoil/size/Precision;", "scale", "Lcoil/size/Scale;", "sizeResolver", "Lcoil/size/SizeResolver;", "transformations", "Lcoil/transform/Transformation;", "addHeader", "name", "value", "(Ljava/lang/String;Ljava/lang/String;)Lcoil/request/RequestBuilder;", "", "([Ljava/lang/String;)Lcoil/request/RequestBuilder;", "(Ljava/util/List;)Lcoil/request/RequestBuilder;", "enable", "(Z)Lcoil/request/RequestBuilder;", "(Landroid/graphics/Bitmap$Config;)Lcoil/request/RequestBuilder;", "(Landroid/graphics/ColorSpace;)Lcoil/request/RequestBuilder;", "(Ljava/lang/Object;)Lcoil/request/RequestBuilder;", "(Lcoil/decode/Decoder;)Lcoil/request/RequestBuilder;", "policy", "(Lcoil/request/CachePolicy;)Lcoil/request/RequestBuilder;", "(Lkotlinx/coroutines/CoroutineDispatcher;)Lcoil/request/RequestBuilder;", "error", "drawable", "(Landroid/graphics/drawable/Drawable;)Lcoil/request/RequestBuilder;", "drawableResId", "(I)Lcoil/request/RequestBuilder;", "fallback", "R", "(Lcoil/fetch/Fetcher;)Lcoil/request/RequestBuilder;", "type", "(Ljava/lang/Class;Lcoil/fetch/Fetcher;)Lcoil/request/RequestBuilder;", "Lokhttp3/Headers;", "(Lokhttp3/Headers;)Lcoil/request/RequestBuilder;", "(Ljava/lang/String;)Lcoil/request/RequestBuilder;", "onStart", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "", "onCancel", "onError", "Lkotlin/Function2;", "", "throwable", "onSuccess", "Lcoil/decode/DataSource;", "source", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)Lcoil/request/RequestBuilder;", "(Lcoil/request/Request$Listener;)Lcoil/request/RequestBuilder;", "Lcoil/request/Parameters;", "(Lcoil/request/Parameters;)Lcoil/request/RequestBuilder;", "(Lcoil/size/Precision;)Lcoil/request/RequestBuilder;", "removeHeader", "removeParameter", "(Lcoil/size/Scale;)Lcoil/request/RequestBuilder;", "setHeader", "setParameter", "cacheKey", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/String;)Lcoil/request/RequestBuilder;", "size", "Lcoil/size/Size;", "(Lcoil/size/Size;)Lcoil/request/RequestBuilder;", "resolver", "(Lcoil/size/SizeResolver;)Lcoil/request/RequestBuilder;", "width", "height", "(II)Lcoil/request/RequestBuilder;", "([Lcoil/transform/Transformation;)Lcoil/request/RequestBuilder;", "Lcoil/request/LoadRequestBuilder;", "Lcoil/request/GetRequestBuilder;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestBuilder.kt */
public abstract class RequestBuilder<T extends RequestBuilder<T>> {
    protected List<String> aliasKeys;
    protected Boolean allowHardware;
    protected Boolean allowRgb565;
    protected Bitmap.Config bitmapConfig;
    protected ColorSpace colorSpace;
    protected final Context context;
    protected Object data;
    protected Decoder decoder;
    protected CachePolicy diskCachePolicy;
    protected CoroutineDispatcher dispatcher;
    protected Drawable errorDrawable;
    protected int errorResId;
    protected Drawable fallbackDrawable;
    protected int fallbackResId;
    protected Pair<? extends Class<?>, ? extends Fetcher<?>> fetcher;
    protected Headers.Builder headers;
    protected String key;
    protected Request.Listener listener;
    protected CachePolicy memoryCachePolicy;
    protected CachePolicy networkCachePolicy;
    protected Parameters.Builder parameters;
    protected Precision precision;
    protected Scale scale;
    protected SizeResolver sizeResolver;
    protected List<? extends Transformation> transformations;

    public final T setParameter(String str, Object obj) {
        return setParameter$default(this, str, obj, (String) null, 4, (Object) null);
    }

    public /* synthetic */ RequestBuilder(Context context2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2);
    }

    public /* synthetic */ RequestBuilder(Request request, Context context2, DefaultConstructorMarker defaultConstructorMarker) {
        this(request, context2);
    }

    private RequestBuilder(Context context2) {
        this.context = context2;
        this.data = null;
        this.key = null;
        this.aliasKeys = CollectionsKt.emptyList();
        this.listener = null;
        this.dispatcher = null;
        this.transformations = CollectionsKt.emptyList();
        this.bitmapConfig = Utils.INSTANCE.getDefaultBitmapConfig();
        if (Build.VERSION.SDK_INT >= 26) {
            this.colorSpace = null;
        }
        this.sizeResolver = null;
        this.scale = null;
        this.precision = null;
        this.fetcher = null;
        this.decoder = null;
        Boolean bool = null;
        this.allowHardware = bool;
        this.allowRgb565 = bool;
        CachePolicy cachePolicy = null;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy;
        this.networkCachePolicy = cachePolicy;
        this.headers = null;
        this.parameters = null;
        this.errorResId = 0;
        Drawable drawable = null;
        this.errorDrawable = drawable;
        this.fallbackResId = 0;
        this.fallbackDrawable = drawable;
    }

    private RequestBuilder(Request request, Context context2) {
        this.context = context2;
        this.data = request.getData();
        this.key = request.getKey();
        this.aliasKeys = request.getAliasKeys();
        this.listener = request.getListener();
        this.dispatcher = request.getDispatcher();
        this.transformations = request.getTransformations();
        this.bitmapConfig = request.getBitmapConfig();
        if (Build.VERSION.SDK_INT >= 26) {
            this.colorSpace = request.getColorSpace();
        }
        this.sizeResolver = request.getSizeResolver();
        this.scale = request.getScale();
        this.precision = request.getPrecision();
        this.fetcher = request.getFetcher();
        this.decoder = request.getDecoder();
        this.allowHardware = request.getAllowHardware();
        this.allowRgb565 = request.getAllowRgb565();
        this.memoryCachePolicy = request.getMemoryCachePolicy();
        this.diskCachePolicy = request.getDiskCachePolicy();
        this.networkCachePolicy = request.getNetworkCachePolicy();
        this.headers = request.getHeaders().newBuilder();
        this.parameters = request.getParameters().newBuilder();
        this.errorResId = request.getErrorResId$coil_base_release();
        this.errorDrawable = request.getErrorDrawable$coil_base_release();
        this.fallbackResId = request.getFallbackResId$coil_base_release();
        this.fallbackDrawable = request.getFallbackDrawable$coil_base_release();
    }

    public static /* synthetic */ RequestBuilder listener$default(RequestBuilder requestBuilder, Function1 function1, Function1 function12, Function2 function2, Function2 function22, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                function1 = RequestBuilder$listener$1.INSTANCE;
            }
            if ((i & 2) != 0) {
                function12 = RequestBuilder$listener$2.INSTANCE;
            }
            if ((i & 4) != 0) {
                function2 = RequestBuilder$listener$3.INSTANCE;
            }
            if ((i & 8) != 0) {
                function22 = RequestBuilder$listener$4.INSTANCE;
            }
            Intrinsics.checkParameterIsNotNull(function1, "onStart");
            Intrinsics.checkParameterIsNotNull(function12, "onCancel");
            Intrinsics.checkParameterIsNotNull(function2, "onError");
            Intrinsics.checkParameterIsNotNull(function22, "onSuccess");
            return requestBuilder.listener(new RequestBuilder$listener$5(function1, function12, function2, function22));
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listener");
    }

    public final T listener(Function1<? super Request, Unit> function1, Function1<? super Request, Unit> function12, Function2<? super Request, ? super Throwable, Unit> function2, Function2<? super Request, ? super DataSource, Unit> function22) {
        Intrinsics.checkParameterIsNotNull(function1, "onStart");
        Intrinsics.checkParameterIsNotNull(function12, "onCancel");
        Intrinsics.checkParameterIsNotNull(function2, "onError");
        Intrinsics.checkParameterIsNotNull(function22, "onSuccess");
        return listener(new RequestBuilder$listener$5(function1, function12, function2, function22));
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [coil.fetch.Fetcher, coil.fetch.Fetcher<R>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ <R> T fetcher(coil.fetch.Fetcher<R> r3) {
        /*
            r2 = this;
            java.lang.String r0 = "fetcher"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            r0 = 4
            java.lang.String r1 = "R"
            kotlin.jvm.internal.Intrinsics.reifiedOperationMarker(r0, r1)
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            coil.request.RequestBuilder r3 = r2.fetcher(r0, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.request.RequestBuilder.fetcher(coil.fetch.Fetcher):coil.request.RequestBuilder");
    }

    public static /* synthetic */ RequestBuilder setParameter$default(RequestBuilder requestBuilder, String str, Object obj, String str2, int i, Object obj2) {
        if (obj2 == null) {
            if ((i & 4) != 0) {
                str2 = obj != null ? obj.toString() : null;
            }
            return requestBuilder.setParameter(str, obj, str2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setParameter");
    }

    public final T data(Object obj) {
        Object obj2 = this;
        T t = this;
        t.data = obj;
        return t;
    }

    public final T key(String str) {
        Object obj = this;
        T t = this;
        t.key = str;
        return t;
    }

    public final T aliasKeys(String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "aliasKeys");
        Object obj = this;
        T t = this;
        t.aliasKeys = ArraysKt.toList((T[]) strArr);
        return t;
    }

    public final T aliasKeys(List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "aliasKeys");
        Object obj = this;
        T t = this;
        t.aliasKeys = CollectionsKt.toList(list);
        return t;
    }

    public final T listener(Request.Listener listener2) {
        Object obj = this;
        T t = this;
        t.listener = listener2;
        return t;
    }

    public final T dispatcher(CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "dispatcher");
        Object obj = this;
        T t = this;
        t.dispatcher = coroutineDispatcher;
        return t;
    }

    public final T transformations(Transformation... transformationArr) {
        Intrinsics.checkParameterIsNotNull(transformationArr, "transformations");
        Object obj = this;
        T t = this;
        t.transformations = ArraysKt.toList((T[]) transformationArr);
        return t;
    }

    public final T transformations(List<? extends Transformation> list) {
        Intrinsics.checkParameterIsNotNull(list, "transformations");
        Object obj = this;
        T t = this;
        t.transformations = CollectionsKt.toList(list);
        return t;
    }

    public final T bitmapConfig(Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, "bitmapConfig");
        Object obj = this;
        T t = this;
        t.bitmapConfig = config;
        return t;
    }

    public final T colorSpace(ColorSpace colorSpace2) {
        Intrinsics.checkParameterIsNotNull(colorSpace2, "colorSpace");
        Object obj = this;
        T t = this;
        t.colorSpace = colorSpace2;
        return t;
    }

    public final T size(int i) {
        Object obj = this;
        T t = this;
        t.size(i, i);
        return t;
    }

    public final T size(int i, int i2) {
        Object obj = this;
        T t = this;
        t.size((Size) new PixelSize(i, i2));
        return t;
    }

    public final T size(Size size) {
        Intrinsics.checkParameterIsNotNull(size, "size");
        Object obj = this;
        T t = this;
        t.sizeResolver = SizeResolver.Companion.create(size);
        return t;
    }

    public final T size(SizeResolver sizeResolver2) {
        Intrinsics.checkParameterIsNotNull(sizeResolver2, "resolver");
        Object obj = this;
        T t = this;
        t.sizeResolver = sizeResolver2;
        return t;
    }

    public final T scale(Scale scale2) {
        Intrinsics.checkParameterIsNotNull(scale2, "scale");
        Object obj = this;
        T t = this;
        t.scale = scale2;
        return t;
    }

    public final T precision(Precision precision2) {
        Intrinsics.checkParameterIsNotNull(precision2, "precision");
        Object obj = this;
        T t = this;
        t.precision = precision2;
        return t;
    }

    public final <R> T fetcher(Class<R> cls, Fetcher<R> fetcher2) {
        Intrinsics.checkParameterIsNotNull(cls, "type");
        Intrinsics.checkParameterIsNotNull(fetcher2, "fetcher");
        Object obj = this;
        T t = this;
        t.fetcher = TuplesKt.to(cls, fetcher2);
        return t;
    }

    public final T decoder(Decoder decoder2) {
        Intrinsics.checkParameterIsNotNull(decoder2, "decoder");
        Object obj = this;
        T t = this;
        t.decoder = decoder2;
        return t;
    }

    public final T allowHardware(boolean z) {
        Object obj = this;
        T t = this;
        t.allowHardware = Boolean.valueOf(z);
        return t;
    }

    public final T allowRgb565(boolean z) {
        Object obj = this;
        T t = this;
        t.allowRgb565 = Boolean.valueOf(z);
        return t;
    }

    public final T memoryCachePolicy(CachePolicy cachePolicy) {
        Intrinsics.checkParameterIsNotNull(cachePolicy, "policy");
        Object obj = this;
        T t = this;
        t.memoryCachePolicy = cachePolicy;
        return t;
    }

    public final T diskCachePolicy(CachePolicy cachePolicy) {
        Intrinsics.checkParameterIsNotNull(cachePolicy, "policy");
        Object obj = this;
        T t = this;
        t.diskCachePolicy = cachePolicy;
        return t;
    }

    public final T networkCachePolicy(CachePolicy cachePolicy) {
        Intrinsics.checkParameterIsNotNull(cachePolicy, "policy");
        Object obj = this;
        T t = this;
        t.networkCachePolicy = cachePolicy;
        return t;
    }

    public final T headers(Headers headers2) {
        Intrinsics.checkParameterIsNotNull(headers2, "headers");
        Object obj = this;
        T t = this;
        t.headers = headers2.newBuilder();
        return t;
    }

    public final T addHeader(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "value");
        Object obj = this;
        T t = this;
        Headers.Builder builder = t.headers;
        if (builder == null) {
            builder = new Headers.Builder();
        }
        t.headers = builder.add(str, str2);
        return t;
    }

    public final T setHeader(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "value");
        Object obj = this;
        T t = this;
        Headers.Builder builder = t.headers;
        if (builder == null) {
            builder = new Headers.Builder();
        }
        t.headers = builder.set(str, str2);
        return t;
    }

    public final T removeHeader(String str) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Object obj = this;
        T t = this;
        Headers.Builder builder = t.headers;
        t.headers = builder != null ? builder.removeAll(str) : null;
        return t;
    }

    public final T parameters(Parameters parameters2) {
        Intrinsics.checkParameterIsNotNull(parameters2, "parameters");
        Object obj = this;
        T t = this;
        t.parameters = parameters2.newBuilder();
        return t;
    }

    public final T setParameter(String str, Object obj, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Object obj2 = this;
        T t = this;
        Parameters.Builder builder = t.parameters;
        if (builder == null) {
            builder = new Parameters.Builder();
        }
        builder.set(str, obj, str2);
        t.parameters = builder;
        return t;
    }

    public final T removeParameter(String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Object obj = this;
        T t = this;
        Parameters.Builder builder = t.parameters;
        if (builder != null) {
            builder.remove(str);
        }
        return t;
    }

    public final T error(int i) {
        Object obj = this;
        T t = this;
        t.errorResId = i;
        t.errorDrawable = Extensions.getEMPTY_DRAWABLE();
        return t;
    }

    public final T error(Drawable drawable) {
        Object obj = this;
        T t = this;
        if (drawable == null) {
            drawable = Extensions.getEMPTY_DRAWABLE();
        }
        t.errorDrawable = drawable;
        t.errorResId = 0;
        return t;
    }

    public final T fallback(int i) {
        Object obj = this;
        T t = this;
        t.fallbackResId = i;
        t.fallbackDrawable = Extensions.getEMPTY_DRAWABLE();
        return t;
    }

    public final T fallback(Drawable drawable) {
        Object obj = this;
        T t = this;
        if (drawable == null) {
            drawable = Extensions.getEMPTY_DRAWABLE();
        }
        t.fallbackDrawable = drawable;
        t.fallbackResId = 0;
        return t;
    }
}

package coil.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import androidx.lifecycle.Lifecycle;
import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.fetch.Fetcher;
import coil.size.Precision;
import coil.size.Scale;
import coil.size.SizeResolver;
import coil.target.Target;
import coil.transform.Transformation;
import coil.transition.Transition;
import coil.util.Requests;
import com.facebook.share.internal.ShareConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.Headers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001oB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u0004\u0018\u00010\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u0004\u0018\u00010\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u0004\u0018\u00010\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\u0004\u0018\u00010&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0013\u0010)\u001a\u0004\u0018\u00010*8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u0004\u0018\u00010*X \u0004¢\u0006\u0006\u001a\u0004\b.\u0010,R\u0012\u0010/\u001a\u000200X \u0004¢\u0006\u0006\u001a\u0004\b1\u00102R\u0013\u00103\u001a\u0004\u0018\u00010*8F¢\u0006\u0006\u001a\u0004\b4\u0010,R\u0014\u00105\u001a\u0004\u0018\u00010*X \u0004¢\u0006\u0006\u001a\u0004\b6\u0010,R\u0012\u00107\u001a\u000200X \u0004¢\u0006\u0006\u001a\u0004\b8\u00102R(\u00109\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030;\u0012\b\u0012\u0006\u0012\u0002\b\u00030<\u0018\u00010:X¦\u0004¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0012\u0010?\u001a\u00020@X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0014\u0010C\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\bD\u0010ER\u0014\u0010F\u001a\u0004\u0018\u00010GX¦\u0004¢\u0006\u0006\u001a\u0004\bH\u0010IR\u0014\u0010J\u001a\u0004\u0018\u00010KX¦\u0004¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0014\u0010N\u001a\u0004\u0018\u00010\"X¦\u0004¢\u0006\u0006\u001a\u0004\bO\u0010$R\u0014\u0010P\u001a\u0004\u0018\u00010\"X¦\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010$R\u0012\u0010R\u001a\u00020SX¦\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR\u0014\u0010V\u001a\u0004\u0018\u00010*X¦\u0004¢\u0006\u0006\u001a\u0004\bW\u0010,R\u0014\u0010X\u001a\u0004\u0018\u00010YX¦\u0004¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0014\u0010\\\u001a\u0004\u0018\u00010]X¦\u0004¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0014\u0010`\u001a\u0004\u0018\u00010aX¦\u0004¢\u0006\u0006\u001a\u0004\bb\u0010cR\u0014\u0010d\u001a\u0004\u0018\u00010eX¦\u0004¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0018\u0010h\u001a\b\u0012\u0004\u0012\u00020i0\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\bj\u0010\u0007R\u0014\u0010k\u001a\u0004\u0018\u00010lX¦\u0004¢\u0006\u0006\u001a\u0004\bm\u0010n\u0001\u0002pq¨\u0006r"}, d2 = {"Lcoil/request/Request;", "", "()V", "aliasKeys", "", "", "getAliasKeys", "()Ljava/util/List;", "allowHardware", "", "getAllowHardware", "()Ljava/lang/Boolean;", "allowRgb565", "getAllowRgb565", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "colorSpace", "Landroid/graphics/ColorSpace;", "getColorSpace", "()Landroid/graphics/ColorSpace;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "data", "getData", "()Ljava/lang/Object;", "decoder", "Lcoil/decode/Decoder;", "getDecoder", "()Lcoil/decode/Decoder;", "diskCachePolicy", "Lcoil/request/CachePolicy;", "getDiskCachePolicy", "()Lcoil/request/CachePolicy;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "error", "Landroid/graphics/drawable/Drawable;", "getError", "()Landroid/graphics/drawable/Drawable;", "errorDrawable", "getErrorDrawable$coil_base_release", "errorResId", "", "getErrorResId$coil_base_release", "()I", "fallback", "getFallback", "fallbackDrawable", "getFallbackDrawable$coil_base_release", "fallbackResId", "getFallbackResId$coil_base_release", "fetcher", "Lkotlin/Pair;", "Ljava/lang/Class;", "Lcoil/fetch/Fetcher;", "getFetcher", "()Lkotlin/Pair;", "headers", "Lokhttp3/Headers;", "getHeaders", "()Lokhttp3/Headers;", "key", "getKey", "()Ljava/lang/String;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "listener", "Lcoil/request/Request$Listener;", "getListener", "()Lcoil/request/Request$Listener;", "memoryCachePolicy", "getMemoryCachePolicy", "networkCachePolicy", "getNetworkCachePolicy", "parameters", "Lcoil/request/Parameters;", "getParameters", "()Lcoil/request/Parameters;", "placeholder", "getPlaceholder", "precision", "Lcoil/size/Precision;", "getPrecision", "()Lcoil/size/Precision;", "scale", "Lcoil/size/Scale;", "getScale", "()Lcoil/size/Scale;", "sizeResolver", "Lcoil/size/SizeResolver;", "getSizeResolver", "()Lcoil/size/SizeResolver;", "target", "Lcoil/target/Target;", "getTarget", "()Lcoil/target/Target;", "transformations", "Lcoil/transform/Transformation;", "getTransformations", "transition", "Lcoil/transition/Transition;", "getTransition", "()Lcoil/transition/Transition;", "Listener", "Lcoil/request/LoadRequest;", "Lcoil/request/GetRequest;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Request.kt */
public abstract class Request {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0017J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0017J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0017¨\u0006\r"}, d2 = {"Lcoil/request/Request$Listener;", "", "onCancel", "", "request", "Lcoil/request/Request;", "onError", "throwable", "", "onStart", "onSuccess", "source", "Lcoil/decode/DataSource;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: Request.kt */
    public interface Listener {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* compiled from: Request.kt */
        public static final class DefaultImpls {
            public static void onCancel(Listener listener, Request request) {
                Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            }

            public static void onError(Listener listener, Request request, Throwable th) {
                Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
                Intrinsics.checkParameterIsNotNull(th, "throwable");
            }

            public static void onStart(Listener listener, Request request) {
                Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            }

            public static void onSuccess(Listener listener, Request request, DataSource dataSource) {
                Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
                Intrinsics.checkParameterIsNotNull(dataSource, "source");
            }
        }

        void onCancel(Request request);

        void onError(Request request, Throwable th);

        void onStart(Request request);

        void onSuccess(Request request, DataSource dataSource);
    }

    public abstract List<String> getAliasKeys();

    public abstract Boolean getAllowHardware();

    public abstract Boolean getAllowRgb565();

    public abstract Bitmap.Config getBitmapConfig();

    public abstract ColorSpace getColorSpace();

    public abstract Context getContext();

    public abstract Object getData();

    public abstract Decoder getDecoder();

    public abstract CachePolicy getDiskCachePolicy();

    public abstract CoroutineDispatcher getDispatcher();

    public abstract Drawable getErrorDrawable$coil_base_release();

    public abstract int getErrorResId$coil_base_release();

    public abstract Drawable getFallbackDrawable$coil_base_release();

    public abstract int getFallbackResId$coil_base_release();

    public abstract Pair<Class<?>, Fetcher<?>> getFetcher();

    public abstract Headers getHeaders();

    public abstract String getKey();

    public abstract Lifecycle getLifecycle();

    public abstract Listener getListener();

    public abstract CachePolicy getMemoryCachePolicy();

    public abstract CachePolicy getNetworkCachePolicy();

    public abstract Parameters getParameters();

    public abstract Drawable getPlaceholder();

    public abstract Precision getPrecision();

    public abstract Scale getScale();

    public abstract SizeResolver getSizeResolver();

    public abstract Target getTarget();

    public abstract List<Transformation> getTransformations();

    public abstract Transition getTransition();

    private Request() {
    }

    public /* synthetic */ Request(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final Drawable getError() {
        return Requests.getDrawableCompat(this, getErrorDrawable$coil_base_release(), getErrorResId$coil_base_release());
    }

    public final Drawable getFallback() {
        return Requests.getDrawableCompat(this, getFallbackDrawable$coil_base_release(), getFallbackResId$coil_base_release());
    }
}

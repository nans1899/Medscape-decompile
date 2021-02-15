package coil.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import coil.decode.Decoder;
import coil.request.Parameters;
import coil.request.Request;
import coil.size.Precision;
import coil.size.Scale;
import coil.size.SizeResolver;
import coil.target.ImageViewTarget;
import coil.target.Target;
import coil.transition.CrossfadeTransition;
import coil.transition.Transition;
import coil.util.Extensions;
import com.facebook.share.internal.ShareConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.Headers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\rJ\u0010\u0010\b\u001a\u00020\u00002\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0010\u0010\b\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0010\u0010\u0019\u001a\u00020\u00002\b\u0010\u001a\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u0019\u001a\u00020\u00002\b\b\u0001\u0010\u001b\u001a\u00020\rJ|\u0010\u000e\u001a\u00020\u00002%\b\u0006\u0010\u001c\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020 0\u001d2%\b\u0006\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020 0\u001d2#\b\u0006\u0010#\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b($\u0012\u0004\u0012\u00020 0\u001dH\bJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&J\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00020\r8\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcoil/request/LoadRequestBuilder;", "Lcoil/request/RequestBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "request", "Lcoil/request/LoadRequest;", "(Lcoil/request/LoadRequest;Landroid/content/Context;)V", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "placeholderDrawable", "Landroid/graphics/drawable/Drawable;", "placeholderResId", "", "target", "Lcoil/target/Target;", "transition", "Lcoil/transition/Transition;", "build", "crossfade", "enable", "", "durationMillis", "owner", "Landroidx/lifecycle/LifecycleOwner;", "placeholder", "drawable", "drawableResId", "onStart", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "onError", "error", "onSuccess", "result", "imageView", "Landroid/widget/ImageView;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestBuilder.kt */
public final class LoadRequestBuilder extends RequestBuilder<LoadRequestBuilder> {
    private Lifecycle lifecycle;
    private Drawable placeholderDrawable;
    private int placeholderResId;
    private Target target;
    private Transition transition;

    public LoadRequestBuilder(LoadRequest loadRequest) {
        this(loadRequest, (Context) null, 2, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LoadRequestBuilder(Context context) {
        super(context, (DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.target = null;
        this.lifecycle = null;
        this.transition = null;
        this.placeholderResId = 0;
        this.placeholderDrawable = null;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LoadRequestBuilder(LoadRequest loadRequest, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(loadRequest, (i & 2) != 0 ? loadRequest.getContext() : context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LoadRequestBuilder(LoadRequest loadRequest, Context context) {
        super(loadRequest, context, (DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(loadRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.target = loadRequest.getTarget();
        this.lifecycle = loadRequest.getLifecycle();
        this.transition = loadRequest.getTransition();
        this.placeholderResId = loadRequest.getPlaceholderResId$coil_base_release();
        this.placeholderDrawable = loadRequest.getPlaceholderDrawable$coil_base_release();
    }

    public final LoadRequestBuilder target(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "imageView");
        LoadRequestBuilder loadRequestBuilder = this;
        loadRequestBuilder.target((Target) new ImageViewTarget(imageView));
        return loadRequestBuilder;
    }

    public static /* synthetic */ LoadRequestBuilder target$default(LoadRequestBuilder loadRequestBuilder, Function1 function1, Function1 function12, Function1 function13, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = LoadRequestBuilder$target$2.INSTANCE;
        }
        if ((i & 2) != 0) {
            function12 = LoadRequestBuilder$target$3.INSTANCE;
        }
        if ((i & 4) != 0) {
            function13 = LoadRequestBuilder$target$4.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(function1, "onStart");
        Intrinsics.checkParameterIsNotNull(function12, "onError");
        Intrinsics.checkParameterIsNotNull(function13, "onSuccess");
        return loadRequestBuilder.target((Target) new LoadRequestBuilder$target$5(function1, function12, function13));
    }

    public final LoadRequestBuilder target(Function1<? super Drawable, Unit> function1, Function1<? super Drawable, Unit> function12, Function1<? super Drawable, Unit> function13) {
        Intrinsics.checkParameterIsNotNull(function1, "onStart");
        Intrinsics.checkParameterIsNotNull(function12, "onError");
        Intrinsics.checkParameterIsNotNull(function13, "onSuccess");
        return target((Target) new LoadRequestBuilder$target$5(function1, function12, function13));
    }

    public final LoadRequestBuilder target(Target target2) {
        LoadRequestBuilder loadRequestBuilder = this;
        loadRequestBuilder.target = target2;
        return loadRequestBuilder;
    }

    public final LoadRequestBuilder crossfade(boolean z) {
        return crossfade(z ? 100 : 0);
    }

    public final LoadRequestBuilder crossfade(int i) {
        LoadRequestBuilder loadRequestBuilder = this;
        loadRequestBuilder.transition = i > 0 ? new CrossfadeTransition(i) : Transition.NONE;
        return loadRequestBuilder;
    }

    public final LoadRequestBuilder transition(Transition transition2) {
        Intrinsics.checkParameterIsNotNull(transition2, "transition");
        LoadRequestBuilder loadRequestBuilder = this;
        loadRequestBuilder.transition = transition2;
        return loadRequestBuilder;
    }

    public final LoadRequestBuilder lifecycle(LifecycleOwner lifecycleOwner) {
        LoadRequestBuilder loadRequestBuilder = this;
        loadRequestBuilder.lifecycle(lifecycleOwner != null ? lifecycleOwner.getLifecycle() : null);
        return loadRequestBuilder;
    }

    public final LoadRequestBuilder lifecycle(Lifecycle lifecycle2) {
        LoadRequestBuilder loadRequestBuilder = this;
        loadRequestBuilder.lifecycle = lifecycle2;
        return loadRequestBuilder;
    }

    public final LoadRequestBuilder placeholder(int i) {
        LoadRequestBuilder loadRequestBuilder = this;
        loadRequestBuilder.placeholderResId = i;
        loadRequestBuilder.placeholderDrawable = Extensions.getEMPTY_DRAWABLE();
        return loadRequestBuilder;
    }

    public final LoadRequestBuilder placeholder(Drawable drawable) {
        LoadRequestBuilder loadRequestBuilder = this;
        if (drawable == null) {
            drawable = Extensions.getEMPTY_DRAWABLE();
        }
        loadRequestBuilder.placeholderDrawable = drawable;
        loadRequestBuilder.placeholderResId = 0;
        return loadRequestBuilder;
    }

    public final LoadRequest build() {
        Context context = this.context;
        Object obj = this.data;
        String str = this.key;
        List list = this.aliasKeys;
        Request.Listener listener = this.listener;
        CoroutineDispatcher coroutineDispatcher = this.dispatcher;
        List list2 = this.transformations;
        Bitmap.Config config = this.bitmapConfig;
        ColorSpace colorSpace = this.colorSpace;
        SizeResolver sizeResolver = this.sizeResolver;
        Scale scale = this.scale;
        Precision precision = this.precision;
        Pair pair = this.fetcher;
        Decoder decoder = this.decoder;
        Boolean bool = this.allowHardware;
        Decoder decoder2 = decoder;
        Boolean bool2 = this.allowRgb565;
        CachePolicy cachePolicy = this.memoryCachePolicy;
        CachePolicy cachePolicy2 = this.diskCachePolicy;
        CachePolicy cachePolicy3 = this.networkCachePolicy;
        Boolean bool3 = bool;
        Headers.Builder builder = this.headers;
        Parameters parameters = null;
        Headers orEmpty = Extensions.orEmpty(builder != null ? builder.build() : null);
        CachePolicy cachePolicy4 = cachePolicy3;
        Intrinsics.checkExpressionValueIsNotNull(orEmpty, "headers?.build().orEmpty()");
        Parameters.Builder builder2 = this.parameters;
        if (builder2 != null) {
            parameters = builder2.build();
        }
        return new LoadRequest(context, obj, str, list, listener, coroutineDispatcher, list2, config, colorSpace, sizeResolver, scale, precision, pair, decoder2, bool3, bool2, cachePolicy, cachePolicy2, cachePolicy4, orEmpty, Extensions.orEmpty(parameters), this.target, this.transition, this.lifecycle, this.placeholderResId, this.placeholderDrawable, this.errorResId, this.errorDrawable, this.fallbackResId, this.fallbackDrawable);
    }
}

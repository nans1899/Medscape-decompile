package coil.request;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import coil.decode.Decoder;
import coil.request.Parameters;
import coil.request.Request;
import coil.size.Precision;
import coil.size.Scale;
import coil.size.SizeResolver;
import coil.util.Extensions;
import com.facebook.share.internal.ShareConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import okhttp3.Headers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\u0006¨\u0006\t"}, d2 = {"Lcoil/request/GetRequestBuilder;", "Lcoil/request/RequestBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "request", "Lcoil/request/GetRequest;", "(Lcoil/request/GetRequest;Landroid/content/Context;)V", "build", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestBuilder.kt */
public final class GetRequestBuilder extends RequestBuilder<GetRequestBuilder> {
    public GetRequestBuilder(GetRequest getRequest) {
        this(getRequest, (Context) null, 2, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetRequestBuilder(Context context) {
        super(context, (DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GetRequestBuilder(GetRequest getRequest, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(getRequest, (i & 2) != 0 ? getRequest.getContext() : context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetRequestBuilder(GetRequest getRequest, Context context) {
        super(getRequest, context, (DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(getRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final GetRequest build() {
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
        return new GetRequest(context, obj, str, list, listener, coroutineDispatcher, list2, config, colorSpace, sizeResolver, scale, precision, pair, decoder2, bool3, bool2, cachePolicy, cachePolicy2, cachePolicy4, orEmpty, Extensions.orEmpty(parameters), this.errorResId, this.errorDrawable, this.fallbackResId, this.fallbackDrawable);
    }
}

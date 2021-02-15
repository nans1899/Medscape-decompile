package coil.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import coil.DefaultRequestOptions;
import coil.fetch.Fetcher;
import coil.request.LoadRequest;
import coil.request.Request;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a\"\u0010\b\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0017\u0010\f\u001a\u0004\u0018\u00010\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\b\u001a+\u0010\r\u001a\n\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u000e\"\b\b\u0000\u0010\u000f*\u00020\u0010*\u00020\u00022\u0006\u0010\u0011\u001a\u0002H\u000fH\u0000¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"bitmapConfigOrDefault", "Landroid/graphics/Bitmap$Config;", "Lcoil/request/Request;", "defaults", "Lcoil/DefaultRequestOptions;", "errorOrDefault", "Landroid/graphics/drawable/Drawable;", "fallbackOrDefault", "getDrawableCompat", "drawable", "resId", "", "placeholderOrDefault", "validateFetcher", "Lcoil/fetch/Fetcher;", "T", "", "data", "(Lcoil/request/Request;Ljava/lang/Object;)Lcoil/fetch/Fetcher;", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* renamed from: coil.util.-Requests  reason: invalid class name */
/* compiled from: Requests.kt */
public final class Requests {
    public static final Drawable getDrawableCompat(Request request, Drawable drawable, int i) {
        Intrinsics.checkParameterIsNotNull(request, "$this$getDrawableCompat");
        if (!(drawable != Extensions.getEMPTY_DRAWABLE())) {
            drawable = null;
        }
        if (drawable != null) {
            return drawable;
        }
        if (i != 0) {
            return Contexts.getDrawableCompat(request.getContext(), i);
        }
        return null;
    }

    public static final Drawable placeholderOrDefault(Request request, DefaultRequestOptions defaultRequestOptions) {
        Intrinsics.checkParameterIsNotNull(request, "$this$placeholderOrDefault");
        Intrinsics.checkParameterIsNotNull(defaultRequestOptions, RemoteConfigComponent.DEFAULTS_FILE_NAME);
        return (!(request instanceof LoadRequest) || ((LoadRequest) request).getPlaceholderDrawable$coil_base_release() == null) ? defaultRequestOptions.getPlaceholder() : request.getPlaceholder();
    }

    public static final Drawable errorOrDefault(Request request, DefaultRequestOptions defaultRequestOptions) {
        Intrinsics.checkParameterIsNotNull(request, "$this$errorOrDefault");
        Intrinsics.checkParameterIsNotNull(defaultRequestOptions, RemoteConfigComponent.DEFAULTS_FILE_NAME);
        return (!(request instanceof LoadRequest) || request.getErrorDrawable$coil_base_release() == null) ? defaultRequestOptions.getError() : request.getError();
    }

    public static final Drawable fallbackOrDefault(Request request, DefaultRequestOptions defaultRequestOptions) {
        Intrinsics.checkParameterIsNotNull(request, "$this$fallbackOrDefault");
        Intrinsics.checkParameterIsNotNull(defaultRequestOptions, RemoteConfigComponent.DEFAULTS_FILE_NAME);
        return (!(request instanceof LoadRequest) || request.getFallbackDrawable$coil_base_release() == null) ? defaultRequestOptions.getFallback() : request.getFallback();
    }

    public static final Bitmap.Config bitmapConfigOrDefault(Request request, DefaultRequestOptions defaultRequestOptions) {
        Intrinsics.checkParameterIsNotNull(request, "$this$bitmapConfigOrDefault");
        Intrinsics.checkParameterIsNotNull(defaultRequestOptions, RemoteConfigComponent.DEFAULTS_FILE_NAME);
        Bitmap.Config bitmapConfig = request.getBitmapConfig();
        return bitmapConfig != null ? bitmapConfig : defaultRequestOptions.getBitmapConfig();
    }

    public static final <T> Fetcher<T> validateFetcher(Request request, T t) {
        Intrinsics.checkParameterIsNotNull(request, "$this$validateFetcher");
        Intrinsics.checkParameterIsNotNull(t, "data");
        Pair<Class<?>, Fetcher<?>> fetcher = request.getFetcher();
        if (fetcher == null) {
            return null;
        }
        Fetcher<T> component2 = fetcher.component2();
        if (!fetcher.component1().isAssignableFrom(t.getClass())) {
            throw new IllegalStateException((component2.getClass().getName() + " cannot handle data with type " + t.getClass().getName() + '.').toString());
        } else if (component2 != null) {
            return component2;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type coil.fetch.Fetcher<T>");
        }
    }
}

package coil;

import android.content.Context;
import coil.ImageLoader;
import coil.request.GetRequest;
import coil.request.LoadRequest;
import coil.request.RequestDisposable;
import coil.request.RequestResult;
import com.facebook.share.internal.ShareConstants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nHHø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0011\u0010\u0007\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\rH\bJ\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0004H\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0006H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcoil/Coil;", "", "()V", "imageLoader", "Lcoil/ImageLoader;", "imageLoaderFactory", "Lcoil/ImageLoaderFactory;", "execute", "Lcoil/request/RequestResult;", "request", "Lcoil/request/GetRequest;", "(Lcoil/request/GetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcoil/request/RequestDisposable;", "Lcoil/request/LoadRequest;", "context", "Landroid/content/Context;", "newImageLoader", "setImageLoader", "", "loader", "factory", "coil-singleton_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Coil.kt */
public final class Coil {
    public static final Coil INSTANCE = new Coil();
    private static ImageLoader imageLoader;
    private static ImageLoaderFactory imageLoaderFactory;

    private Coil() {
    }

    @JvmStatic
    public static final ImageLoader imageLoader(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        ImageLoader imageLoader2 = imageLoader;
        return imageLoader2 != null ? imageLoader2 : INSTANCE.newImageLoader(context);
    }

    @JvmStatic
    public static final void setImageLoader(ImageLoader imageLoader2) {
        Intrinsics.checkParameterIsNotNull(imageLoader2, "loader");
        setImageLoader((ImageLoaderFactory) new Coil$setImageLoader$1(imageLoader2));
    }

    @JvmStatic
    public static final RequestDisposable execute(LoadRequest loadRequest) {
        Intrinsics.checkParameterIsNotNull(loadRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        return imageLoader(loadRequest.getContext()).execute(loadRequest);
    }

    @JvmStatic
    public static final Object execute(GetRequest getRequest, Continuation<? super RequestResult> continuation) {
        return imageLoader(getRequest.getContext()).execute(getRequest, continuation);
    }

    @JvmStatic
    private static final Object execute$$forInline(GetRequest getRequest, Continuation continuation) {
        ImageLoader imageLoader2 = imageLoader(getRequest.getContext());
        InlineMarker.mark(0);
        Object execute = imageLoader2.execute(getRequest, continuation);
        InlineMarker.mark(1);
        return execute;
    }

    @JvmStatic
    public static final synchronized void setImageLoader(ImageLoaderFactory imageLoaderFactory2) {
        synchronized (Coil.class) {
            Intrinsics.checkParameterIsNotNull(imageLoaderFactory2, "factory");
            imageLoaderFactory = imageLoaderFactory2;
            ImageLoader imageLoader2 = imageLoader;
            imageLoader = null;
            if (imageLoader2 != null) {
                imageLoader2.shutdown();
            }
        }
    }

    private final synchronized ImageLoader newImageLoader(Context context) {
        ImageLoader imageLoader2;
        ImageLoader imageLoader3 = imageLoader;
        if (imageLoader3 != null) {
            return imageLoader3;
        }
        ImageLoaderFactory imageLoaderFactory2 = imageLoaderFactory;
        if (imageLoaderFactory2 == null || (imageLoader2 = imageLoaderFactory2.newImageLoader()) == null) {
            Context applicationContext = context.getApplicationContext();
            if (!(applicationContext instanceof ImageLoaderFactory)) {
                applicationContext = null;
            }
            ImageLoaderFactory imageLoaderFactory3 = (ImageLoaderFactory) applicationContext;
            imageLoader2 = imageLoaderFactory3 != null ? imageLoaderFactory3.newImageLoader() : null;
        }
        if (imageLoader2 == null) {
            ImageLoader.Companion companion = ImageLoader.Companion;
            imageLoader2 = new ImageLoaderBuilder(context).build();
        }
        imageLoaderFactory = null;
        setImageLoader(imageLoader2);
        return imageLoader2;
    }
}

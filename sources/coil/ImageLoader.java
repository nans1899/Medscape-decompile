package coil;

import android.content.Context;
import coil.request.GetRequest;
import coil.request.LoadRequest;
import coil.request.RequestDisposable;
import coil.request.RequestResult;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013J\b\u0010\u0006\u001a\u00020\u0007H'J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u0010\u0010\b\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H'J\b\u0010\u0012\u001a\u00020\u0007H'R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcoil/ImageLoader;", "", "defaults", "Lcoil/DefaultRequestOptions;", "getDefaults", "()Lcoil/DefaultRequestOptions;", "clearMemory", "", "execute", "Lcoil/request/RequestResult;", "request", "Lcoil/request/GetRequest;", "(Lcoil/request/GetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcoil/request/RequestDisposable;", "Lcoil/request/LoadRequest;", "invalidate", "key", "", "shutdown", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageLoader.kt */
public interface ImageLoader {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* renamed from: coil.ImageLoader$-CC  reason: invalid class name */
    /* compiled from: ImageLoader.kt */
    public final /* synthetic */ class CC {
        @JvmStatic
        public static ImageLoaderBuilder builder(Context context) {
            return ImageLoader.Companion.builder(context);
        }

        @JvmStatic
        public static ImageLoader create(Context context) {
            return ImageLoader.Companion.create(context);
        }
    }

    void clearMemory();

    RequestDisposable execute(LoadRequest loadRequest);

    Object execute(GetRequest getRequest, Continuation<? super RequestResult> continuation);

    DefaultRequestOptions getDefaults();

    void invalidate(String str);

    void shutdown();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\b¢\u0006\u0002\b\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcoil/ImageLoader$Companion;", "", "()V", "Builder", "Lcoil/ImageLoaderBuilder;", "context", "Landroid/content/Context;", "builder", "invoke", "Lcoil/ImageLoader;", "create", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ImageLoader.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @JvmStatic
        public final ImageLoaderBuilder builder(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new ImageLoaderBuilder(context);
        }

        @JvmStatic
        public final ImageLoader create(Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return new ImageLoaderBuilder(context).build();
        }
    }
}

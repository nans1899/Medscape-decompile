package coil.api;

import coil.request.LoadRequestBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcoil/request/LoadRequestBuilder;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ImageViews.kt */
public final class ImageViews$loadAny$1 extends Lambda implements Function1<LoadRequestBuilder, Unit> {
    public static final ImageViews$loadAny$1 INSTANCE = new ImageViews$loadAny$1();

    public ImageViews$loadAny$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((LoadRequestBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(LoadRequestBuilder loadRequestBuilder) {
        Intrinsics.checkParameterIsNotNull(loadRequestBuilder, "$receiver");
    }
}

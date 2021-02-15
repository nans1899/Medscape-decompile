package coil.request;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/graphics/drawable/Drawable;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: RequestBuilder.kt */
public final class LoadRequestBuilder$target$4 extends Lambda implements Function1<Drawable, Unit> {
    public static final LoadRequestBuilder$target$4 INSTANCE = new LoadRequestBuilder$target$4();

    public LoadRequestBuilder$target$4() {
        super(1);
    }

    public final void invoke(Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "it");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Drawable) obj);
        return Unit.INSTANCE;
    }
}

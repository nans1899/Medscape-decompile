package coil;

import android.graphics.drawable.BitmapDrawable;
import coil.RealImageLoader;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HH"}, d2 = {"size", "", "cached", "Landroid/graphics/drawable/BitmapDrawable;", "continuation", "Lkotlin/coroutines/Continuation;", "Lcoil/size/Size;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader$LazySizeResolver", f = "RealImageLoader.kt", i = {0, 0}, l = {474}, m = "size", n = {"this", "cached"}, s = {"L$0", "L$1"})
/* compiled from: RealImageLoader.kt */
public final class RealImageLoader$LazySizeResolver$size$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RealImageLoader.LazySizeResolver this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RealImageLoader$LazySizeResolver$size$1(RealImageLoader.LazySizeResolver lazySizeResolver, Continuation continuation) {
        super(continuation);
        this.this$0 = lazySizeResolver;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.size((BitmapDrawable) null, this);
    }
}

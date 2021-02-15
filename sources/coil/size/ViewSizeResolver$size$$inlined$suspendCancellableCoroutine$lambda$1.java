package coil.size;

import android.view.ViewTreeObserver;
import coil.size.ViewSizeResolver;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004¸\u0006\u0000"}, d2 = {"coil/size/ViewSizeResolver$size$3$preDrawListener$1", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "onPreDraw", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ViewSizeResolver.kt */
public final class ViewSizeResolver$size$$inlined$suspendCancellableCoroutine$lambda$1 implements ViewTreeObserver.OnPreDrawListener {
    final /* synthetic */ CancellableContinuation $continuation;
    final /* synthetic */ ViewTreeObserver $viewTreeObserver;
    final /* synthetic */ ViewSizeResolver this$0;

    ViewSizeResolver$size$$inlined$suspendCancellableCoroutine$lambda$1(ViewTreeObserver viewTreeObserver, CancellableContinuation cancellableContinuation, ViewSizeResolver viewSizeResolver) {
        this.$viewTreeObserver = viewTreeObserver;
        this.$continuation = cancellableContinuation;
        this.this$0 = viewSizeResolver;
    }

    public boolean onPreDraw() {
        PixelSize access$getSize = ViewSizeResolver.DefaultImpls.getSize(this.this$0, false);
        if (access$getSize == null) {
            return true;
        }
        ViewSizeResolver viewSizeResolver = this.this$0;
        ViewTreeObserver viewTreeObserver = this.$viewTreeObserver;
        Intrinsics.checkExpressionValueIsNotNull(viewTreeObserver, "viewTreeObserver");
        ViewSizeResolver.DefaultImpls.removePreDrawListenerSafe(viewSizeResolver, viewTreeObserver, this);
        Result.Companion companion = Result.Companion;
        this.$continuation.resumeWith(Result.m6constructorimpl(access$getSize));
        return true;
    }
}

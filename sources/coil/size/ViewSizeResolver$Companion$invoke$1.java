package coil.size;

import android.view.View;
import coil.size.ViewSizeResolver;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0006\u001a\u00028\u0000X\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"coil/size/ViewSizeResolver$Companion$invoke$1", "Lcoil/size/ViewSizeResolver;", "subtractPadding", "", "getSubtractPadding", "()Z", "view", "getView", "()Landroid/view/View;", "Landroid/view/View;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ViewSizeResolver.kt */
public final class ViewSizeResolver$Companion$invoke$1 implements ViewSizeResolver<T> {
    final /* synthetic */ boolean $subtractPadding;
    final /* synthetic */ View $view;
    private final boolean subtractPadding;
    private final T view;

    ViewSizeResolver$Companion$invoke$1(View view2, boolean z) {
        this.$view = view2;
        this.$subtractPadding = z;
        this.view = view2;
        this.subtractPadding = z;
    }

    public Object size(Continuation<? super Size> continuation) {
        return ViewSizeResolver.DefaultImpls.size(this, continuation);
    }

    public T getView() {
        return this.view;
    }

    public boolean getSubtractPadding() {
        return this.subtractPadding;
    }
}

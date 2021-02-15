package coil.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import coil.target.ViewTarget;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcoil/transition/TransitionTarget;", "T", "Landroid/view/View;", "Lcoil/target/ViewTarget;", "drawable", "Landroid/graphics/drawable/Drawable;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TransitionTarget.kt */
public interface TransitionTarget<T extends View> extends ViewTarget<T> {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: TransitionTarget.kt */
    public static final class DefaultImpls {
        public static <T extends View> void onError(TransitionTarget<T> transitionTarget, Drawable drawable) {
            ViewTarget.DefaultImpls.onError(transitionTarget, drawable);
        }

        public static <T extends View> void onStart(TransitionTarget<T> transitionTarget, Drawable drawable) {
            ViewTarget.DefaultImpls.onStart(transitionTarget, drawable);
        }

        public static <T extends View> void onSuccess(TransitionTarget<T> transitionTarget, Drawable drawable) {
            Intrinsics.checkParameterIsNotNull(drawable, "result");
            ViewTarget.DefaultImpls.onSuccess(transitionTarget, drawable);
        }
    }

    Drawable getDrawable();
}

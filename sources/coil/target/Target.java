package coil.target;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0017J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0017J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0005H\u0017¨\u0006\n"}, d2 = {"Lcoil/target/Target;", "", "onError", "", "error", "Landroid/graphics/drawable/Drawable;", "onStart", "placeholder", "onSuccess", "result", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Target.kt */
public interface Target {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: Target.kt */
    public static final class DefaultImpls {
        public static void onError(Target target, Drawable drawable) {
        }

        public static void onStart(Target target, Drawable drawable) {
        }

        public static void onSuccess(Target target, Drawable drawable) {
            Intrinsics.checkParameterIsNotNull(drawable, "result");
        }
    }

    void onError(Drawable drawable);

    void onStart(Drawable drawable);

    void onSuccess(Drawable drawable);
}

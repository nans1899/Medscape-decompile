package coil.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import coil.transition.TransitionTarget;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u00032\u00020\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\bH\u0016J\u0012\u0010\u001a\u001a\u00020\u00102\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\u001b\u001a\u00020\u0010H\u0014R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lcoil/target/ImageViewTarget;", "Lcoil/target/PoolableViewTarget;", "Landroid/widget/ImageView;", "Lcoil/transition/TransitionTarget;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "view", "(Landroid/widget/ImageView;)V", "drawable", "Landroid/graphics/drawable/Drawable;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "isStarted", "", "getView", "()Landroid/widget/ImageView;", "onClear", "", "onError", "error", "onStart", "placeholder", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "onSuccess", "result", "setDrawable", "updateAnimation", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageViewTarget.kt */
public class ImageViewTarget implements PoolableViewTarget<ImageView>, TransitionTarget<ImageView>, DefaultLifecycleObserver {
    private boolean isStarted;
    private final ImageView view;

    public /* synthetic */ void onCreate(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onCreate(this, lifecycleOwner);
    }

    public /* synthetic */ void onDestroy(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onDestroy(this, lifecycleOwner);
    }

    public /* synthetic */ void onPause(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onPause(this, lifecycleOwner);
    }

    public /* synthetic */ void onResume(LifecycleOwner lifecycleOwner) {
        DefaultLifecycleObserver.CC.$default$onResume(this, lifecycleOwner);
    }

    public ImageViewTarget(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "view");
        this.view = imageView;
    }

    public ImageView getView() {
        return this.view;
    }

    public Drawable getDrawable() {
        return getView().getDrawable();
    }

    public void onStart(Drawable drawable) {
        setDrawable(drawable);
    }

    public void onSuccess(Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "result");
        setDrawable(drawable);
    }

    public void onError(Drawable drawable) {
        setDrawable(drawable);
    }

    public void onClear() {
        setDrawable((Drawable) null);
    }

    public void onStart(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        this.isStarted = true;
        updateAnimation();
    }

    public void onStop(LifecycleOwner lifecycleOwner) {
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "owner");
        this.isStarted = false;
        updateAnimation();
    }

    /* access modifiers changed from: protected */
    public void setDrawable(Drawable drawable) {
        Drawable drawable2 = getView().getDrawable();
        if (!(drawable2 instanceof Animatable)) {
            drawable2 = null;
        }
        Animatable animatable = (Animatable) drawable2;
        if (animatable != null) {
            animatable.stop();
        }
        getView().setImageDrawable(drawable);
        updateAnimation();
    }

    /* access modifiers changed from: protected */
    public void updateAnimation() {
        Drawable drawable = getView().getDrawable();
        if (!(drawable instanceof Animatable)) {
            drawable = null;
        }
        Animatable animatable = (Animatable) drawable;
        if (animatable == null) {
            return;
        }
        if (this.isStarted) {
            animatable.start();
        } else {
            animatable.stop();
        }
    }
}

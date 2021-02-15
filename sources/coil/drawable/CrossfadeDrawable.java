package coil.drawable;

import android.content.res.ColorStateList;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import coil.decode.DecodeUtils;
import coil.size.Scale;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 Q2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001QB/\b\u0007\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0001\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001a\u001a\u00020\u001bH\u0016J!\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\t2\b\u0010\u001e\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\tH\u0017J\n\u0010$\u001a\u0004\u0018\u00010%H\u0017J\b\u0010&\u001a\u00020\tH\u0016J\b\u0010'\u001a\u00020\tH\u0016J\b\u0010(\u001a\u00020\tH\u0016J\u0010\u0010)\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u0001H\u0016J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u001bH\u0002J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u000200H\u0014J\u0010\u00101\u001a\u00020,2\u0006\u00102\u001a\u00020\tH\u0014J\u0010\u00103\u001a\u00020,2\u0006\u0010\u0019\u001a\u000204H\u0014J\u0010\u00105\u001a\u00020\u001b2\u0006\u00106\u001a\u00020\rH\u0016J \u00107\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u00012\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\u0018H\u0016J\u0010\u0010;\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020\tH\u0016J\u0012\u0010=\u001a\u00020\u001b2\b\u0010>\u001a\u0004\u0018\u00010%H\u0017J\u0010\u0010?\u001a\u00020\u001b2\u0006\u0010@\u001a\u00020\tH\u0017J\u0012\u0010A\u001a\u00020\u001b2\b\u0010B\u001a\u0004\u0018\u00010CH\u0017J\u0012\u0010D\u001a\u00020\u001b2\b\u0010E\u001a\u0004\u0018\u00010FH\u0017J\u0012\u0010G\u001a\u00020\u001b2\b\u0010H\u001a\u0004\u0018\u00010IH\u0017J\b\u0010\u0004\u001a\u00020\u001bH\u0016J\b\u0010J\u001a\u00020\u001bH\u0016J\u0010\u0010K\u001a\u00020,2\u0006\u00106\u001a\u00020\rH\u0016J\u0018\u0010L\u001a\u00020\u001b2\u0006\u0010*\u001a\u00020\u00012\u0006\u00108\u001a\u000209H\u0016J\u001d\u0010M\u001a\u00020\u001b2\u0006\u0010N\u001a\u00020\u00012\u0006\u0010O\u001a\u000200H\u0001¢\u0006\u0002\bPR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Lcoil/drawable/CrossfadeDrawable;", "Landroid/graphics/drawable/Drawable;", "Landroid/graphics/drawable/Drawable$Callback;", "Landroidx/vectordrawable/graphics/drawable/Animatable2Compat;", "start", "end", "scale", "Lcoil/size/Scale;", "durationMillis", "", "(Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Lcoil/size/Scale;I)V", "callbacks", "", "Landroidx/vectordrawable/graphics/drawable/Animatable2Compat$AnimationCallback;", "getDurationMillis", "()I", "getEnd", "()Landroid/graphics/drawable/Drawable;", "intrinsicHeight", "intrinsicWidth", "maxAlpha", "getScale", "()Lcoil/size/Scale;", "startTimeMillis", "", "state", "clearAnimationCallbacks", "", "computeIntrinsicDimension", "startSize", "endSize", "(Ljava/lang/Integer;Ljava/lang/Integer;)I", "draw", "canvas", "Landroid/graphics/Canvas;", "getAlpha", "getColorFilter", "Landroid/graphics/ColorFilter;", "getIntrinsicHeight", "getIntrinsicWidth", "getOpacity", "invalidateDrawable", "who", "isRunning", "", "markDone", "onBoundsChange", "bounds", "Landroid/graphics/Rect;", "onLevelChange", "level", "onStateChange", "", "registerAnimationCallback", "callback", "scheduleDrawable", "what", "Ljava/lang/Runnable;", "when", "setAlpha", "alpha", "setColorFilter", "colorFilter", "setTint", "tintColor", "setTintBlendMode", "blendMode", "Landroid/graphics/BlendMode;", "setTintList", "tint", "Landroid/content/res/ColorStateList;", "setTintMode", "tintMode", "Landroid/graphics/PorterDuff$Mode;", "stop", "unregisterAnimationCallback", "unscheduleDrawable", "updateBounds", "drawable", "targetBounds", "updateBounds$coil_base_release", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CrossfadeDrawable.kt */
public final class CrossfadeDrawable extends Drawable implements Drawable.Callback, Animatable2Compat {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEFAULT_DURATION = 100;
    private static final int STATE_DONE = 2;
    private static final int STATE_RUNNING = 1;
    private static final int STATE_START = 0;
    private final List<Animatable2Compat.AnimationCallback> callbacks;
    private final int durationMillis;
    private final Drawable end;
    private final int intrinsicHeight;
    private final int intrinsicWidth;
    private int maxAlpha;
    private final Scale scale;
    private Drawable start;
    private long startTimeMillis;
    private int state;

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2) {
        this(drawable, drawable2, (Scale) null, 0, 12, (DefaultConstructorMarker) null);
    }

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale2) {
        this(drawable, drawable2, scale2, 0, 8, (DefaultConstructorMarker) null);
    }

    public final Drawable getEnd() {
        return this.end;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(drawable, drawable2, (i2 & 4) != 0 ? Scale.FIT : scale2, (i2 & 8) != 0 ? 100 : i);
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final int getDurationMillis() {
        return this.durationMillis;
    }

    public CrossfadeDrawable(Drawable drawable, Drawable drawable2, Scale scale2, int i) {
        Intrinsics.checkParameterIsNotNull(scale2, "scale");
        this.start = drawable;
        this.end = drawable2;
        this.scale = scale2;
        this.durationMillis = i;
        this.callbacks = new ArrayList();
        Drawable drawable3 = this.start;
        Integer num = null;
        Integer valueOf = drawable3 != null ? Integer.valueOf(drawable3.getIntrinsicWidth()) : null;
        Drawable drawable4 = this.end;
        this.intrinsicWidth = computeIntrinsicDimension(valueOf, drawable4 != null ? Integer.valueOf(drawable4.getIntrinsicWidth()) : null);
        Drawable drawable5 = this.start;
        Integer valueOf2 = drawable5 != null ? Integer.valueOf(drawable5.getIntrinsicHeight()) : null;
        Drawable drawable6 = this.end;
        this.intrinsicHeight = computeIntrinsicDimension(valueOf2, drawable6 != null ? Integer.valueOf(drawable6.getIntrinsicHeight()) : num);
        this.maxAlpha = 255;
        if (this.durationMillis > 0) {
            Drawable drawable7 = this.start;
            if (drawable7 != null) {
                drawable7.setCallback(this);
            }
            Drawable drawable8 = this.end;
            if (drawable8 != null) {
                drawable8.setCallback(this);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("durationMillis must be > 0.".toString());
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcoil/drawable/CrossfadeDrawable$Companion;", "", "()V", "DEFAULT_DURATION", "", "STATE_DONE", "STATE_RUNNING", "STATE_START", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: CrossfadeDrawable.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public void draw(Canvas canvas) {
        Drawable drawable;
        Intrinsics.checkParameterIsNotNull(canvas, "canvas");
        int i = this.state;
        if (i == 0) {
            Drawable drawable2 = this.start;
            if (drawable2 != null) {
                drawable2.setAlpha(this.maxAlpha);
                drawable2.draw(canvas);
            }
        } else if (i == 2) {
            Drawable drawable3 = this.end;
            if (drawable3 != null) {
                drawable3.setAlpha(this.maxAlpha);
                drawable3.draw(canvas);
            }
        } else {
            double uptimeMillis = ((double) (SystemClock.uptimeMillis() - this.startTimeMillis)) / ((double) this.durationMillis);
            boolean z = uptimeMillis >= 1.0d;
            if (!z && (drawable = this.start) != null) {
                drawable.setAlpha(this.maxAlpha);
                drawable.draw(canvas);
            }
            Drawable drawable4 = this.end;
            if (drawable4 != null) {
                drawable4.setAlpha((int) (RangesKt.coerceIn(uptimeMillis, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d) * ((double) this.maxAlpha)));
                drawable4.draw(canvas);
            }
            if (z) {
                markDone();
            } else {
                invalidateSelf();
            }
        }
    }

    public int getAlpha() {
        return this.maxAlpha;
    }

    public void setAlpha(int i) {
        if (i >= 0 && 255 >= i) {
            this.maxAlpha = i;
            return;
        }
        throw new IllegalArgumentException(("Invalid alpha: " + i).toString());
    }

    public int getOpacity() {
        Drawable drawable = this.start;
        Drawable drawable2 = this.end;
        int i = this.state;
        if (i == 0) {
            if (drawable != null) {
                return drawable.getOpacity();
            }
            return -2;
        } else if (i == 2) {
            if (drawable2 != null) {
                return drawable2.getOpacity();
            }
            return -2;
        } else if (drawable != null && drawable2 != null) {
            return Drawable.resolveOpacity(drawable.getOpacity(), drawable2.getOpacity());
        } else {
            if (drawable != null) {
                return drawable.getOpacity();
            }
            if (drawable2 != null) {
                return drawable2.getOpacity();
            }
            return -2;
        }
    }

    public ColorFilter getColorFilter() {
        ColorFilter colorFilter;
        Drawable drawable;
        int i = this.state;
        if (i == 0) {
            Drawable drawable2 = this.start;
            if (drawable2 != null) {
                return drawable2.getColorFilter();
            }
            return null;
        } else if (i == 1) {
            Drawable drawable3 = this.end;
            if (drawable3 != null && (colorFilter = drawable3.getColorFilter()) != null) {
                return colorFilter;
            }
            Drawable drawable4 = this.start;
            if (drawable4 != null) {
                return drawable4.getColorFilter();
            }
            return null;
        } else if (i == 2 && (drawable = this.end) != null) {
            return drawable.getColorFilter();
        } else {
            return null;
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setColorFilter(colorFilter);
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "bounds");
        Drawable drawable = this.start;
        if (drawable != null) {
            updateBounds$coil_base_release(drawable, rect);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            updateBounds$coil_base_release(drawable2, rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable drawable = this.start;
        boolean level = drawable != null ? drawable.setLevel(i) : false;
        Drawable drawable2 = this.end;
        boolean level2 = drawable2 != null ? drawable2.setLevel(i) : false;
        if (level || level2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "state");
        Drawable drawable = this.start;
        boolean state2 = drawable != null ? drawable.setState(iArr) : false;
        Drawable drawable2 = this.end;
        boolean state3 = drawable2 != null ? drawable2.setState(iArr) : false;
        if (state2 || state3) {
            return true;
        }
        return false;
    }

    public int getIntrinsicWidth() {
        return this.intrinsicWidth;
    }

    public int getIntrinsicHeight() {
        return this.intrinsicHeight;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Intrinsics.checkParameterIsNotNull(drawable, "who");
        Intrinsics.checkParameterIsNotNull(runnable, "what");
        unscheduleSelf(runnable);
    }

    public void invalidateDrawable(Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "who");
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Intrinsics.checkParameterIsNotNull(drawable, "who");
        Intrinsics.checkParameterIsNotNull(runnable, "what");
        scheduleSelf(runnable, j);
    }

    public void setTint(int i) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTint(i);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTint(i);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTintList(colorStateList);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTintMode(mode);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTintMode(mode);
        }
    }

    public void setTintBlendMode(BlendMode blendMode) {
        Drawable drawable = this.start;
        if (drawable != null) {
            drawable.setTintBlendMode(blendMode);
        }
        Drawable drawable2 = this.end;
        if (drawable2 != null) {
            drawable2.setTintBlendMode(blendMode);
        }
    }

    public boolean isRunning() {
        return this.state == 1;
    }

    public void start() {
        Drawable drawable = this.start;
        Animatable animatable = null;
        if (!(drawable instanceof Animatable)) {
            drawable = null;
        }
        Animatable animatable2 = (Animatable) drawable;
        if (animatable2 != null) {
            animatable2.start();
        }
        Drawable drawable2 = this.end;
        if (drawable2 instanceof Animatable) {
            animatable = drawable2;
        }
        Animatable animatable3 = animatable;
        if (animatable3 != null) {
            animatable3.start();
        }
        if (this.state == 0) {
            this.state = 1;
            this.startTimeMillis = SystemClock.uptimeMillis();
            List<Animatable2Compat.AnimationCallback> list = this.callbacks;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                list.get(i).onAnimationStart(this);
            }
            invalidateSelf();
        }
    }

    public void stop() {
        Drawable drawable = this.start;
        Animatable animatable = null;
        if (!(drawable instanceof Animatable)) {
            drawable = null;
        }
        Animatable animatable2 = (Animatable) drawable;
        if (animatable2 != null) {
            animatable2.stop();
        }
        Drawable drawable2 = this.end;
        if (drawable2 instanceof Animatable) {
            animatable = drawable2;
        }
        Animatable animatable3 = animatable;
        if (animatable3 != null) {
            animatable3.stop();
        }
        if (this.state != 2) {
            markDone();
        }
    }

    public void registerAnimationCallback(Animatable2Compat.AnimationCallback animationCallback) {
        Intrinsics.checkParameterIsNotNull(animationCallback, "callback");
        this.callbacks.add(animationCallback);
    }

    public boolean unregisterAnimationCallback(Animatable2Compat.AnimationCallback animationCallback) {
        Intrinsics.checkParameterIsNotNull(animationCallback, "callback");
        return this.callbacks.remove(animationCallback);
    }

    public void clearAnimationCallbacks() {
        this.callbacks.clear();
    }

    public final void updateBounds$coil_base_release(Drawable drawable, Rect rect) {
        Intrinsics.checkParameterIsNotNull(drawable, "drawable");
        Intrinsics.checkParameterIsNotNull(rect, "targetBounds");
        int intrinsicWidth2 = drawable.getIntrinsicWidth();
        int intrinsicHeight2 = drawable.getIntrinsicHeight();
        if (intrinsicWidth2 <= 0 || intrinsicHeight2 <= 0) {
            drawable.setBounds(rect);
            return;
        }
        int width = rect.width();
        int height = rect.height();
        double computeSizeMultiplier = DecodeUtils.computeSizeMultiplier(intrinsicWidth2, intrinsicHeight2, width, height, this.scale);
        double d = (double) 2;
        int roundToInt = MathKt.roundToInt((((double) width) - (((double) intrinsicWidth2) * computeSizeMultiplier)) / d);
        int roundToInt2 = MathKt.roundToInt((((double) height) - (computeSizeMultiplier * ((double) intrinsicHeight2))) / d);
        drawable.setBounds(rect.left + roundToInt, rect.top + roundToInt2, rect.right - roundToInt, rect.bottom - roundToInt2);
    }

    private final int computeIntrinsicDimension(Integer num, Integer num2) {
        int i = -1;
        if (num != null && num.intValue() == -1) {
            return -1;
        }
        if (num2 != null && num2.intValue() == -1) {
            return -1;
        }
        int intValue = num != null ? num.intValue() : -1;
        if (num2 != null) {
            i = num2.intValue();
        }
        return Math.max(intValue, i);
    }

    private final void markDone() {
        this.state = 2;
        this.start = null;
        List<Animatable2Compat.AnimationCallback> list = this.callbacks;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            list.get(i).onAnimationEnd(this);
        }
    }
}

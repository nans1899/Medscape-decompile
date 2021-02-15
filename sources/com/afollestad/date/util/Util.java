package com.afollestad.date.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0002J$\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u0006H\u0007J$\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¨\u0006\u0011"}, d2 = {"Lcom/afollestad/date/util/Util;", "", "()V", "circleShape", "Landroid/graphics/drawable/Drawable;", "color", "", "coloredDrawable", "context", "Landroid/content/Context;", "shapeRes", "createCircularSelector", "selectedColor", "createTextSelector", "Landroid/content/res/ColorStateList;", "overColoredBackground", "", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: Util.kt */
public final class Util {
    public static final Util INSTANCE = new Util();

    private Util() {
    }

    public static /* synthetic */ ColorStateList createTextSelector$default(Util util, Context context, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return util.createTextSelector(context, i, z);
    }

    public final ColorStateList createTextSelector(Context context, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        int[][] iArr = {new int[]{-16842910}, new int[]{16842910, -16842913}, new int[]{16842910, 16842913}};
        int resolveColor$default = ContextsKt.resolveColor$default(context, 16842807, (Function0) null, 2, (Object) null);
        int resolveColor$default2 = ContextsKt.resolveColor$default(context, 16842806, (Function0) null, 2, (Object) null);
        int[] iArr2 = new int[3];
        iArr2[0] = resolveColor$default;
        iArr2[1] = resolveColor$default2;
        if (z) {
            i = ColorsKt.isColorDark$default(i, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1, (Object) null) ? -1 : ViewCompat.MEASURED_STATE_MASK;
        }
        iArr2[2] = i;
        return new ColorStateList(iArr, iArr2);
    }

    public final Drawable createCircularSelector(int i) {
        Drawable circleShape = circleShape(i);
        if (Build.VERSION.SDK_INT >= 21) {
            ColorStateList valueOf = ColorStateList.valueOf(i);
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842913}, circleShape);
            return new RippleDrawable(valueOf, stateListDrawable, circleShape);
        }
        StateListDrawable stateListDrawable2 = new StateListDrawable();
        Drawable mutate = circleShape.mutate();
        mutate.setAlpha((int) 76.5d);
        stateListDrawable2.addState(new int[]{16842910, 16842919}, mutate);
        stateListDrawable2.addState(new int[]{16842910, 16842913}, circleShape);
        return stateListDrawable2;
    }

    public final Drawable coloredDrawable(Context context, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Drawable drawable = ContextCompat.getDrawable(context, i);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        drawable.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
        drawable.setAlpha(Color.alpha(i2));
        Intrinsics.checkExpressionValueIsNotNull(drawable, "ContextCompat.getDrawabl… Color.alpha(color)\n    }");
        return drawable;
    }

    private final Drawable circleShape(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColors(new int[]{i, i});
        return gradientDrawable;
    }
}

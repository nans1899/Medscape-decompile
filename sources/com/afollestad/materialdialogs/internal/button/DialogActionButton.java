package com.afollestad.materialdialogs.internal.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import com.afollestad.materialdialogs.R;
import com.afollestad.materialdialogs.ThemeKt;
import com.afollestad.materialdialogs.utils.MDUtil;
import com.afollestad.materialdialogs.utils.ViewsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J%\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0014J\u0010\u0010\u0015\u001a\u00020\r2\b\b\u0001\u0010\u0016\u001a\u00020\bR\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcom/afollestad/materialdialogs/internal/button/DialogActionButton;", "Landroidx/appcompat/widget/AppCompatButton;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "disabledColor", "", "enabledColor", "enabledColorOverride", "Ljava/lang/Integer;", "setEnabled", "", "enabled", "", "update", "baseContext", "appContext", "stacked", "update$core", "updateTextColor", "color", "Companion", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: DialogActionButton.kt */
public final class DialogActionButton extends AppCompatButton {
    private static final int CASING_UPPER = 1;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int disabledColor;
    private int enabledColor;
    private Integer enabledColorOverride;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DialogActionButton(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/afollestad/materialdialogs/internal/button/DialogActionButton$Companion;", "", "()V", "CASING_UPPER", "", "core"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DialogActionButton.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DialogActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        setClickable(true);
        setFocusable(true);
    }

    public final void update$core(Context context, Context context2, boolean z) {
        int i;
        Intrinsics.checkParameterIsNotNull(context, "baseContext");
        Intrinsics.checkParameterIsNotNull(context2, "appContext");
        boolean z2 = true;
        if (MDUtil.INSTANCE.resolveInt(context2, R.attr.md_button_casing, 1) != 1) {
            z2 = false;
        }
        setSupportAllCaps(z2);
        boolean inferThemeIsLight = ThemeKt.inferThemeIsLight(context2);
        this.enabledColor = MDUtil.resolveColor$default(MDUtil.INSTANCE, context2, (Integer) null, Integer.valueOf(R.attr.md_color_button_text), new DialogActionButton$update$1(context2), 2, (Object) null);
        if (inferThemeIsLight) {
            i = R.color.md_disabled_text_light_theme;
        } else {
            i = R.color.md_disabled_text_dark_theme;
        }
        this.disabledColor = MDUtil.resolveColor$default(MDUtil.INSTANCE, context, Integer.valueOf(i), (Integer) null, (Function0) null, 12, (Object) null);
        Integer num = this.enabledColorOverride;
        setTextColor(num != null ? num.intValue() : this.enabledColor);
        Drawable resolveDrawable$default = MDUtil.resolveDrawable$default(MDUtil.INSTANCE, context, (Integer) null, Integer.valueOf(R.attr.md_button_selector), (Drawable) null, 10, (Object) null);
        if (Build.VERSION.SDK_INT >= 21 && (resolveDrawable$default instanceof RippleDrawable)) {
            MDUtil mDUtil = MDUtil.INSTANCE;
            int resolveColor$default = MDUtil.resolveColor$default(MDUtil.INSTANCE, context, (Integer) null, Integer.valueOf(R.attr.md_ripple_color), new DialogActionButton$update$2(context2), 2, (Object) null);
            if (resolveColor$default != 0) {
                ((RippleDrawable) resolveDrawable$default).setColor(ColorStateList.valueOf(resolveColor$default));
            }
        }
        setBackground(resolveDrawable$default);
        if (z) {
            ViewsKt.setGravityEndCompat(this);
        } else {
            setGravity(17);
        }
        setEnabled(isEnabled());
    }

    public final void updateTextColor(int i) {
        this.enabledColor = i;
        this.enabledColorOverride = Integer.valueOf(i);
        setEnabled(isEnabled());
    }

    public void setEnabled(boolean z) {
        int i;
        super.setEnabled(z);
        if (z) {
            Integer num = this.enabledColorOverride;
            i = num != null ? num.intValue() : this.enabledColor;
        } else {
            i = this.disabledColor;
        }
        setTextColor(i);
    }
}

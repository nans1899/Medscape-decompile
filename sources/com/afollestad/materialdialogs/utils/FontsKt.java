package com.afollestad.materialdialogs.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u0002\u001a+\u0010\u0006\u001a\u0004\u0018\u00010\u0001*\u00020\u00072\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0001¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"safeGetFont", "Landroid/graphics/Typeface;", "context", "Landroid/content/Context;", "res", "", "font", "Lcom/afollestad/materialdialogs/MaterialDialog;", "attr", "(Lcom/afollestad/materialdialogs/MaterialDialog;Ljava/lang/Integer;Ljava/lang/Integer;)Landroid/graphics/Typeface;", "core"}, k = 2, mv = {1, 1, 16})
/* compiled from: Fonts.kt */
public final class FontsKt {
    public static /* synthetic */ Typeface font$default(MaterialDialog materialDialog, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        return font(materialDialog, num, num2);
    }

    public static final Typeface font(MaterialDialog materialDialog, Integer num, Integer num2) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "$this$font");
        MDUtil.INSTANCE.assertOneSet("font", num2, num);
        if (num != null) {
            return safeGetFont(materialDialog.getWindowContext(), num.intValue());
        }
        if (num2 != null) {
            TypedArray obtainStyledAttributes = materialDialog.getWindowContext().getTheme().obtainStyledAttributes(new int[]{num2.intValue()});
            try {
                int resourceId = obtainStyledAttributes.getResourceId(0, 0);
                if (resourceId == 0) {
                    return null;
                }
                Typeface safeGetFont = safeGetFont(materialDialog.getWindowContext(), resourceId);
                obtainStyledAttributes.recycle();
                return safeGetFont;
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
    }

    private static final Typeface safeGetFont(Context context, int i) {
        try {
            return ResourcesCompat.getFont(context, i);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}

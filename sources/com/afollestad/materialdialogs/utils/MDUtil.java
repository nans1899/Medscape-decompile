package com.afollestad.materialdialogs.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000´\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010\nJ$\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0003\u0010\u000f\u001a\u00020\t2\b\b\u0003\u0010\u0010\u001a\u00020\tH\u0007J?\u0010\u0011\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010\u0016J?\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u00182%\b\u0002\u0010\u0014\u001a\u001f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\t\u0018\u00010\u001aH\u0007J,\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0013\u001a\u00020\t2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u0015H\u0007J;\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\"H\u0007¢\u0006\u0002\u0010#J$\u0010$\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0007J$\u0010%\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0013\u001a\u00020\t2\b\b\u0002\u0010 \u001a\u00020\tH\u0007J9\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010(\u001a\u00020)H\u0007¢\u0006\u0002\u0010*J9\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010+\u001a\u00020,2\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010(\u001a\u00020)H\u0007¢\u0006\u0002\u0010-J\f\u0010.\u001a\u00020\t*\u00020/H\u0007J%\u00100\u001a\u00020\t\"\b\b\u0000\u00101*\u000202*\u0002H12\b\b\u0001\u0010\u0012\u001a\u00020\tH\u0007¢\u0006\u0002\u00103J#\u00104\u001a\b\u0012\u0004\u0012\u00020\u000605*\u00020\u000e2\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u00106J\u0018\u00107\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t08*\u000209H\u0007J7\u0010:\u001a\u00020\u0004*\u0004\u0018\u00010\t2!\u0010;\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(<\u0012\u0004\u0012\u00020\u00040\u001aH\b¢\u0006\u0002\u0010=J/\u0010>\u001a\u0002H?\"\b\b\u0000\u0010?*\u000202*\u00020@2\b\b\u0002\u0010A\u001a\u00020\u000e2\b\b\u0001\u0010\u0012\u001a\u00020\tH\u0007¢\u0006\u0002\u0010BJ\u0016\u0010C\u001a\u00020)*\u00020\t2\b\b\u0002\u0010D\u001a\u00020EH\u0007J\f\u0010F\u001a\u00020)*\u00020\u000eH\u0007J3\u0010G\u001a\u00020\u0004*\u0004\u0018\u00010/2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0001\u0010H\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010I\u001a\u0004\u0018\u00010\tH\u0007¢\u0006\u0002\u0010JJ \u0010K\u001a\u00020\u0004*\u00020L2\u0012\u0010M\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00040\u001aH\u0007JE\u0010N\u001a\u00020\u0004\"\b\b\u0000\u00101*\u000202*\u0004\u0018\u0001H12\b\b\u0002\u0010O\u001a\u00020\t2\b\b\u0002\u0010P\u001a\u00020\t2\b\b\u0002\u0010Q\u001a\u00020\t2\b\b\u0002\u0010R\u001a\u00020\tH\u0007¢\u0006\u0002\u0010SJ4\u0010T\u001a\u00020\u0004\"\b\b\u0000\u00101*\u000202*\u0002H12\u0017\u0010;\u001a\u0013\u0012\u0004\u0012\u0002H1\u0012\u0004\u0012\u00020\u00040\u001a¢\u0006\u0002\bUH\u0007¢\u0006\u0002\u0010VJ4\u0010W\u001a\u00020\u0004\"\b\b\u0000\u00101*\u000202*\u0002H12\u0017\u0010;\u001a\u0013\u0012\u0004\u0012\u0002H1\u0012\u0004\u0012\u00020\u00040\u001a¢\u0006\u0002\bUH\u0007¢\u0006\u0002\u0010V¨\u0006X"}, d2 = {"Lcom/afollestad/materialdialogs/utils/MDUtil;", "", "()V", "assertOneSet", "", "method", "", "b", "a", "", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Integer;)V", "createColorSelector", "Landroid/content/res/ColorStateList;", "context", "Landroid/content/Context;", "unchecked", "checked", "resolveColor", "res", "attr", "fallback", "Lkotlin/Function0;", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function0;)I", "resolveColors", "", "attrs", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forAttr", "resolveDimen", "", "defaultValue", "resolveDrawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;", "resolveFloat", "resolveInt", "resolveString", "", "html", "", "(Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;Z)Ljava/lang/CharSequence;", "materialDialog", "Lcom/afollestad/materialdialogs/MaterialDialog;", "(Lcom/afollestad/materialdialogs/MaterialDialog;Ljava/lang/Integer;Ljava/lang/Integer;Z)Ljava/lang/CharSequence;", "additionalPaddingForFont", "Landroid/widget/TextView;", "dimenPx", "T", "Landroid/view/View;", "(Landroid/view/View;I)I", "getStringArray", "", "(Landroid/content/Context;Ljava/lang/Integer;)[Ljava/lang/String;", "getWidthAndHeight", "Lkotlin/Pair;", "Landroid/view/WindowManager;", "ifNotZero", "block", "value", "(Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;)V", "inflate", "R", "Landroid/view/ViewGroup;", "ctxt", "(Landroid/view/ViewGroup;Landroid/content/Context;I)Landroid/view/View;", "isColorDark", "threshold", "", "isLandscape", "maybeSetTextColor", "attrRes", "hintAttrRes", "(Landroid/widget/TextView;Landroid/content/Context;Ljava/lang/Integer;Ljava/lang/Integer;)V", "textChanged", "Landroid/widget/EditText;", "callback", "updatePadding", "left", "top", "right", "bottom", "(Landroid/view/View;IIII)V", "waitForHeight", "Lkotlin/ExtensionFunctionType;", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "waitForWidth", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: MDUtil.kt */
public final class MDUtil {
    public static final MDUtil INSTANCE = new MDUtil();

    private MDUtil() {
    }

    public static /* synthetic */ CharSequence resolveString$default(MDUtil mDUtil, MaterialDialog materialDialog, Integer num, Integer num2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return mDUtil.resolveString(materialDialog, num, num2, z);
    }

    public final CharSequence resolveString(MaterialDialog materialDialog, Integer num, Integer num2, boolean z) {
        Intrinsics.checkParameterIsNotNull(materialDialog, "materialDialog");
        return resolveString(materialDialog.getWindowContext(), num, num2, z);
    }

    public static /* synthetic */ CharSequence resolveString$default(MDUtil mDUtil, Context context, Integer num, Integer num2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return mDUtil.resolveString(context, num, num2, z);
    }

    public final CharSequence resolveString(Context context, Integer num, Integer num2, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        int intValue = num != null ? num.intValue() : num2 != null ? num2.intValue() : 0;
        if (intValue == 0) {
            return null;
        }
        CharSequence text = context.getResources().getText(intValue);
        Intrinsics.checkExpressionValueIsNotNull(text, "context.resources.getText(resourceId)");
        return z ? Html.fromHtml(text.toString()) : text;
    }

    public static /* synthetic */ Drawable resolveDrawable$default(MDUtil mDUtil, Context context, Integer num, Integer num2, Drawable drawable, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        if ((i & 8) != 0) {
            drawable = null;
        }
        return mDUtil.resolveDrawable(context, num, num2, drawable);
    }

    public final Drawable resolveDrawable(Context context, Integer num, Integer num2, Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (num2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{num2.intValue()});
            try {
                Drawable drawable2 = obtainStyledAttributes.getDrawable(0);
                if (drawable2 != null || drawable == null) {
                    drawable = drawable2;
                }
                return drawable;
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else if (num == null) {
            return drawable;
        } else {
            return ContextCompat.getDrawable(context, num.intValue());
        }
    }

    public static /* synthetic */ int resolveColor$default(MDUtil mDUtil, Context context, Integer num, Integer num2, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        return mDUtil.resolveColor(context, num, num2, function0);
    }

    public final int resolveColor(Context context, Integer num, Integer num2, Function0<Integer> function0) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        int i = 0;
        if (num2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{num2.intValue()});
            try {
                int color = obtainStyledAttributes.getColor(0, 0);
                if (color == 0 && function0 != null) {
                    return function0.invoke().intValue();
                }
                obtainStyledAttributes.recycle();
                return color;
            } finally {
                obtainStyledAttributes.recycle();
            }
        } else {
            if (num != null) {
                i = num.intValue();
            }
            return ContextCompat.getColor(context, i);
        }
    }

    public static /* synthetic */ int[] resolveColors$default(MDUtil mDUtil, Context context, int[] iArr, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = null;
        }
        return mDUtil.resolveColors(context, iArr, function1);
    }

    public final int[] resolveColors(Context context, int[] iArr, Function1<? super Integer, Integer> function1) {
        Integer invoke;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(iArr, "attrs");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(iArr);
        try {
            Iterable indices = ArraysKt.getIndices(iArr);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(indices, 10));
            Iterator it = indices.iterator();
            while (it.hasNext()) {
                int nextInt = ((IntIterator) it).nextInt();
                int i = 0;
                int color = obtainStyledAttributes.getColor(nextInt, 0);
                if (color != 0) {
                    i = color;
                } else if (!(function1 == null || (invoke = function1.invoke(Integer.valueOf(iArr[nextInt]))) == null)) {
                    i = invoke.intValue();
                }
                arrayList.add(Integer.valueOf(i));
            }
            return CollectionsKt.toIntArray((List) arrayList);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static /* synthetic */ int resolveInt$default(MDUtil mDUtil, Context context, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return mDUtil.resolveInt(context, i, i2);
    }

    public final int resolveInt(Context context, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getInt(0, i2);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static /* synthetic */ float resolveDimen$default(MDUtil mDUtil, Context context, int i, Function0 function0, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            function0 = null;
        }
        return mDUtil.resolveDimen(context, i, function0);
    }

    public final float resolveDimen(Context context, int i, Function0<Float> function0) {
        float f;
        Intrinsics.checkParameterIsNotNull(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        if (function0 != null) {
            try {
                Float invoke = function0.invoke();
                if (invoke != null) {
                    f = invoke.floatValue();
                    float dimension = obtainStyledAttributes.getDimension(0, f);
                    obtainStyledAttributes.recycle();
                    return dimension;
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
        f = 0.0f;
        float dimension2 = obtainStyledAttributes.getDimension(0, f);
        obtainStyledAttributes.recycle();
        return dimension2;
    }

    public static /* synthetic */ float resolveFloat$default(MDUtil mDUtil, Context context, int i, float f, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            f = 0.0f;
        }
        return mDUtil.resolveFloat(context, i, f);
    }

    public final float resolveFloat(Context context, int i, float f) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{i});
        try {
            return obtainStyledAttributes.getFloat(0, f);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public static /* synthetic */ boolean isColorDark$default(MDUtil mDUtil, int i, double d, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d = 0.5d;
        }
        return mDUtil.isColorDark(i, d);
    }

    public final boolean isColorDark(int i, double d) {
        return i != 0 && ((double) 1) - ((((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d)) / ((double) 255)) >= d;
    }

    public final <T extends View> int dimenPx(T t, int i) {
        Intrinsics.checkParameterIsNotNull(t, "$this$dimenPx");
        Context context = t.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return context.getResources().getDimensionPixelSize(i);
    }

    public final boolean isLandscape(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "$this$isLandscape");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        return resources.getConfiguration().orientation == 2;
    }

    public final void textChanged(EditText editText, Function1<? super CharSequence, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(editText, "$this$textChanged");
        Intrinsics.checkParameterIsNotNull(function1, "callback");
        editText.addTextChangedListener(new MDUtil$textChanged$1(function1));
    }

    public static /* synthetic */ void maybeSetTextColor$default(MDUtil mDUtil, TextView textView, Context context, Integer num, Integer num2, int i, Object obj) {
        if ((i & 4) != 0) {
            num2 = null;
        }
        mDUtil.maybeSetTextColor(textView, context, num, num2);
    }

    public final void maybeSetTextColor(TextView textView, Context context, Integer num, Integer num2) {
        int resolveColor$default;
        int resolveColor$default2;
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (textView == null) {
            return;
        }
        if (num != null || num2 != null) {
            if (!(num == null || (resolveColor$default2 = resolveColor$default(this, context, (Integer) null, num, (Function0) null, 10, (Object) null)) == 0)) {
                textView.setTextColor(resolveColor$default2);
            }
            if (num2 != null && (resolveColor$default = resolveColor$default(this, context, (Integer) null, num2, (Function0) null, 10, (Object) null)) != 0) {
                textView.setHintTextColor(resolveColor$default);
            }
        }
    }

    public final int additionalPaddingForFont(TextView textView) {
        Intrinsics.checkParameterIsNotNull(textView, "$this$additionalPaddingForFont");
        TextPaint paint = textView.getPaint();
        Intrinsics.checkExpressionValueIsNotNull(paint, "paint");
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f = fontMetrics.descent - fontMetrics.ascent;
        if (f > ((float) textView.getMeasuredHeight())) {
            return (int) (f - ((float) textView.getMeasuredHeight()));
        }
        return 0;
    }

    public final void ifNotZero(Integer num, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "block");
        if (num != null && num.intValue() != 0) {
            function1.invoke(num);
        }
    }

    public static /* synthetic */ ColorStateList createColorSelector$default(MDUtil mDUtil, Context context, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return mDUtil.createColorSelector(context, i, i2);
    }

    public final ColorStateList createColorSelector(Context context, int i, int i2) {
        int i3;
        int i4;
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (i2 == 0) {
            i3 = resolveColor$default(this, context, (Integer) null, Integer.valueOf(R.attr.colorControlActivated), (Function0) null, 10, (Object) null);
        } else {
            i3 = i2;
        }
        int[][] iArr = {new int[]{-16842912, -16842908}, new int[]{16842912}, new int[]{16842908}};
        int[] iArr2 = new int[3];
        if (i == 0) {
            i4 = resolveColor$default(this, context, (Integer) null, Integer.valueOf(R.attr.colorControlNormal), (Function0) null, 10, (Object) null);
        } else {
            i4 = i;
        }
        iArr2[0] = i4;
        iArr2[1] = i3;
        iArr2[2] = i3;
        return new ColorStateList(iArr, iArr2);
    }

    public final <T extends View> void waitForWidth(T t, Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "$this$waitForWidth");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        if (t.getMeasuredWidth() <= 0 || t.getMeasuredHeight() <= 0) {
            t.getViewTreeObserver().addOnGlobalLayoutListener(new MDUtil$waitForWidth$1(t, function1));
        } else {
            function1.invoke(t);
        }
    }

    public final <T extends View> void waitForHeight(T t, Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "$this$waitForHeight");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        if (t.getMeasuredWidth() <= 0 || t.getMeasuredHeight() <= 0) {
            t.getViewTreeObserver().addOnGlobalLayoutListener(new MDUtil$waitForHeight$1(t, function1));
        } else {
            function1.invoke(t);
        }
    }

    public final Pair<Integer, Integer> getWidthAndHeight(WindowManager windowManager) {
        Intrinsics.checkParameterIsNotNull(windowManager, "$this$getWidthAndHeight");
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return new Pair<>(Integer.valueOf(point.x), Integer.valueOf(point.y));
    }

    public static /* synthetic */ void updatePadding$default(MDUtil mDUtil, View view, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = view != null ? view.getPaddingLeft() : 0;
        }
        int i6 = i;
        if ((i5 & 2) != 0) {
            i2 = view != null ? view.getPaddingTop() : 0;
        }
        int i7 = i2;
        if ((i5 & 4) != 0) {
            i3 = view != null ? view.getPaddingRight() : 0;
        }
        int i8 = i3;
        if ((i5 & 8) != 0) {
            i4 = view != null ? view.getPaddingBottom() : 0;
        }
        mDUtil.updatePadding(view, i6, i7, i8, i4);
    }

    public final <T extends View> void updatePadding(T t, int i, int i2, int i3, int i4) {
        if ((t == null || i != t.getPaddingLeft() || i2 != t.getPaddingTop() || i3 != t.getPaddingRight() || i4 != t.getPaddingBottom()) && t != null) {
            t.setPadding(i, i2, i3, i4);
        }
    }

    public final void assertOneSet(String str, Object obj, Integer num) {
        Intrinsics.checkParameterIsNotNull(str, FirebaseAnalytics.Param.METHOD);
        if (num == null && obj == null) {
            throw new IllegalArgumentException(str + ": You must specify a resource ID or literal value");
        }
    }

    public final String[] getStringArray(Context context, Integer num) {
        Intrinsics.checkParameterIsNotNull(context, "$this$getStringArray");
        if (num == null) {
            return new String[0];
        }
        String[] stringArray = context.getResources().getStringArray(num.intValue());
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "resources.getStringArray(res)");
        return stringArray;
    }

    public static /* synthetic */ View inflate$default(MDUtil mDUtil, ViewGroup viewGroup, Context context, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            context = viewGroup.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
        }
        return mDUtil.inflate(viewGroup, context, i);
    }

    public final <R extends View> R inflate(ViewGroup viewGroup, Context context, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "$this$inflate");
        Intrinsics.checkParameterIsNotNull(context, "ctxt");
        R inflate = LayoutInflater.from(context).inflate(i, viewGroup, false);
        if (inflate != null) {
            return inflate;
        }
        throw new TypeCastException("null cannot be cast to non-null type R");
    }
}

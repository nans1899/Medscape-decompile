package com.webmd.medscape.live.explorelivevents.common;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0016\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007\u001a\u001b\u0010\u0007\u001a\u00020\u0001*\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0007¢\u0006\u0002\u0010\u000b\u001a\u0016\u0010\f\u001a\u00020\u0001*\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0004H\u0007\u001a\u0016\u0010\f\u001a\u00020\u0001*\u00020\u00062\b\b\u0001\u0010\u000e\u001a\u00020\u0004H\u0007\u001a\u001c\u0010\u000f\u001a\u00020\u0001*\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0014\u0010\u0012\u001a\u00020\u0001*\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0014"}, d2 = {"paintColor", "", "Landroid/view/View;", "colorRes", "", "paintTextColor", "Landroid/widget/TextView;", "setErrorImage", "Landroid/widget/ImageView;", "drawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/widget/ImageView;Ljava/lang/Integer;)V", "setFont", "Landroid/widget/EditText;", "fontRes", "setIconLeft", "event", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "setTint", "Landroid/widget/CheckBox;", "wbmd.explorelivevents_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: BindingAdapters.kt */
public final class BindingAdaptersKt {
    @BindingAdapter({"iconLeft", "drawableTintColor"})
    public static final void setIconLeft(TextView textView, LiveEvent liveEvent, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(textView, "$this$setIconLeft");
        Intrinsics.checkNotNullParameter(liveEvent, "event");
        if (Intrinsics.areEqual((Object) liveEvent.getLocation(), (Object) "Online")) {
            i2 = R.drawable.ic_camera;
        } else {
            i2 = R.drawable.ic_location;
        }
        int color = ContextCompat.getColor(textView.getContext(), i);
        textView.setCompoundDrawablesWithIntrinsicBounds(i2, 0, 0, 0);
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
    }

    @BindingAdapter({"checkboxTint"})
    public static final void setTint(CheckBox checkBox, int i) {
        Intrinsics.checkNotNullParameter(checkBox, "$this$setTint");
        CompoundButtonCompat.setButtonTintList(checkBox, new ColorStateList(new int[][]{new int[]{16842912}, new int[0]}, new int[]{ContextCompat.getColor(checkBox.getContext(), i), ContextCompat.getColor(checkBox.getContext(), R.color.dark_grey)}));
    }

    @BindingAdapter({"errorImage"})
    public static final void setErrorImage(ImageView imageView, Drawable drawable) {
        Intrinsics.checkNotNullParameter(imageView, "$this$setErrorImage");
        if (drawable != null) {
            Glide.with((View) imageView).load(drawable).into(imageView);
        }
    }

    @BindingAdapter({"errorImage"})
    public static final void setErrorImage(ImageView imageView, Integer num) {
        Intrinsics.checkNotNullParameter(imageView, "$this$setErrorImage");
        Context context = imageView.getContext();
        Intrinsics.checkNotNull(num);
        Drawable drawable = ContextCompat.getDrawable(context, num.intValue());
        if (drawable != null) {
            Glide.with((View) imageView).load(drawable).into(imageView);
        }
    }

    @BindingAdapter({"backgroundResource"})
    public static final void paintColor(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "$this$paintColor");
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), i));
    }

    @BindingAdapter({"textColorResource"})
    public static final void paintTextColor(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "$this$paintTextColor");
        textView.setTextColor(ContextCompat.getColor(textView.getContext(), i));
    }

    @BindingAdapter({"font"})
    public static final void setFont(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "$this$setFont");
        try {
            textView.setTypeface(ResourcesCompat.getFont(textView.getContext(), i));
        } catch (Exception unused) {
            Typeface typeface = textView.getTypeface();
            Intrinsics.checkNotNullExpressionValue(typeface, "this.typeface");
            textView.setTypeface(Typeface.create("Roboto", typeface.getStyle()));
        }
    }

    @BindingAdapter({"font"})
    public static final void setFont(EditText editText, int i) {
        Intrinsics.checkNotNullParameter(editText, "$this$setFont");
        try {
            editText.setTypeface(ResourcesCompat.getFont(editText.getContext(), i));
        } catch (Exception unused) {
            Typeface typeface = editText.getTypeface();
            Intrinsics.checkNotNullExpressionValue(typeface, "this.typeface");
            editText.setTypeface(Typeface.create("Roboto", typeface.getStyle()));
        }
    }
}

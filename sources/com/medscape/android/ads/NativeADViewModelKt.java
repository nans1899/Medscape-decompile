package com.medscape.android.ads;

import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0001¨\u0006\u0006"}, d2 = {"setClickableText", "", "view", "Landroid/widget/TextView;", "string", "Landroid/text/Spanned;", "medscape_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: NativeADViewModel.kt */
public final class NativeADViewModelKt {
    @BindingAdapter({"setClickableText"})
    public static final void setClickableText(TextView textView, Spanned spanned) {
        Intrinsics.checkNotNullParameter(textView, "view");
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        if (spanned != null) {
            textView.setText(spanned);
        }
    }
}

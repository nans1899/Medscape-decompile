package com.medscape.android.myinvites;

import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import androidx.databinding.BindingAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007¨\u0006\u0006"}, d2 = {"setTextToHtml", "", "textView", "Landroid/widget/TextView;", "value", "", "medscape_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: MyInvitationsAdapter.kt */
public final class MyInvitationsAdapterKt {
    @BindingAdapter({"htmlText"})
    public static final void setTextToHtml(TextView textView, String str) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        if (str != null) {
            textView.setText(HtmlCompat.fromHtml(str, 0));
        }
    }
}

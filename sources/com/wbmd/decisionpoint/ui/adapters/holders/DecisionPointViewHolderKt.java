package com.wbmd.decisionpoint.ui.adapters.holders;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0001¨\u0006\u0006"}, d2 = {"setDecisionPointImage", "", "view", "Landroid/widget/ImageView;", "url", "", "wbmd.decisionpoint_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: DecisionPointViewHolder.kt */
public final class DecisionPointViewHolderKt {
    @BindingAdapter({"decisionPointImageUrl"})
    public static final void setDecisionPointImage(ImageView imageView, String str) {
        Intrinsics.checkNotNullParameter(imageView, "view");
        CharSequence charSequence = str;
        if (!(charSequence == null || charSequence.length() == 0)) {
            Glide.with((View) imageView).load(str).into(imageView);
        }
    }
}

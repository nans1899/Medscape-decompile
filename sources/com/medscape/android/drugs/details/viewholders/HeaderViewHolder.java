package com.medscape.android.drugs.details.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0006\u0010\u0012\u001a\u00020\u000fR\"\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/medscape/android/drugs/details/viewholders/HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "header", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "getHeader", "()Landroid/widget/TextView;", "setHeader", "(Landroid/widget/TextView;)V", "getView", "()Landroid/view/View;", "bindHeader", "", "item", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "enableTextSelection", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HeaderViewHolder.kt */
public final class HeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView header;
    private final View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HeaderViewHolder(View view2) {
        super(view2);
        Intrinsics.checkNotNullParameter(view2, "view");
        this.view = view2;
        this.header = (TextView) view2.findViewById(R.id.text);
    }

    public final View getView() {
        return this.view;
    }

    public final TextView getHeader() {
        return this.header;
    }

    public final void setHeader(TextView textView) {
        this.header = textView;
    }

    public final void bindHeader(LineItem lineItem) {
        TextView textView = this.header;
        Intrinsics.checkNotNullExpressionValue(textView, "header");
        textView.setText(lineItem != null ? lineItem.getText() : null);
        if (lineItem != null) {
            View view2 = this.view;
            View view3 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view3, "itemView");
            view2.setPadding((int) Util.dpToPixel(view3.getContext(), lineItem.getIndentation() * 10), 0, 0, 0);
        }
    }

    public final void enableTextSelection() {
        TextView textView = this.header;
        if (textView != null) {
            Intrinsics.checkNotNullExpressionValue(textView, "header");
            textView.setEnabled(false);
            TextView textView2 = this.header;
            Intrinsics.checkNotNullExpressionValue(textView2, "header");
            textView2.setEnabled(true);
        }
    }
}

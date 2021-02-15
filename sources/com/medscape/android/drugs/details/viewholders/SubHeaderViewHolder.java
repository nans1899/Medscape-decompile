package com.medscape.android.drugs.details.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0006\u0010\u0011\u001a\u00020\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/medscape/android/drugs/details/viewholders/SubHeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "subHeader", "Landroid/widget/TextView;", "getSubHeader", "()Landroid/widget/TextView;", "setSubHeader", "(Landroid/widget/TextView;)V", "getView", "()Landroid/view/View;", "bindSubHeader", "", "item", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "enableTextSelection", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SubHeaderViewHolder.kt */
public final class SubHeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView subHeader;
    private final View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SubHeaderViewHolder(View view2) {
        super(view2);
        Intrinsics.checkNotNullParameter(view2, "view");
        this.view = view2;
        View findViewById = view2.findViewById(R.id.text);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById<TextView>(R.id.text)");
        this.subHeader = (TextView) findViewById;
    }

    public final View getView() {
        return this.view;
    }

    public final TextView getSubHeader() {
        return this.subHeader;
    }

    public final void setSubHeader(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.subHeader = textView;
    }

    public final void bindSubHeader(LineItem lineItem) {
        this.subHeader.setText(lineItem != null ? lineItem.getText() : null);
        if (lineItem != null) {
            View view2 = this.view;
            View view3 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view3, "itemView");
            view2.setPadding((int) Util.dpToPixel(view3.getContext(), lineItem.getIndentation() * 10), 0, 0, 0);
        }
    }

    public final void enableTextSelection() {
        TextView textView = this.subHeader;
        if (textView != null) {
            textView.setEnabled(false);
            this.subHeader.setEnabled(true);
        }
    }
}

package com.medscape.android.drugs.details.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.drugs.details.datamodels.InteractionListItem;
import com.medscape.android.drugs.details.datamodels.LineItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0006\u0010\u0014\u001a\u00020\u0011R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/drugs/details/viewholders/ExpandableContentView;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "setText", "(Landroid/widget/TextView;)V", "textDetails", "getTextDetails", "setTextDetails", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "enableTextSelection", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExpandableContentView.kt */
public final class ExpandableContentView extends RecyclerView.ViewHolder {
    private TextView text;
    private TextView textDetails;
    private final View view;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExpandableContentView(View view2) {
        super(view2);
        Intrinsics.checkNotNullParameter(view2, "view");
        this.view = view2;
        View findViewById = view2.findViewById(R.id.drug_detail_interaction_name);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.d…_detail_interaction_name)");
        this.text = (TextView) findViewById;
        View findViewById2 = this.view.findViewById(R.id.drug_detail_interaction_details);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.d…tail_interaction_details)");
        this.textDetails = (TextView) findViewById2;
    }

    public final View getView() {
        return this.view;
    }

    public final TextView getText() {
        return this.text;
    }

    public final void setText(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.text = textView;
    }

    public final TextView getTextDetails() {
        return this.textDetails;
    }

    public final void setTextDetails(TextView textView) {
        Intrinsics.checkNotNullParameter(textView, "<set-?>");
        this.textDetails = textView;
    }

    public final void bind(LineItem lineItem) {
        this.text.setText(lineItem != null ? lineItem.getText() : null);
        TextView textView = this.textDetails;
        if (lineItem != null) {
            InteractionListItem interactionListItem = (InteractionListItem) lineItem;
            textView.setText(interactionListItem.getDetails());
            if (interactionListItem.isDetailVisible()) {
                this.textDetails.setVisibility(0);
            } else {
                this.textDetails.setVisibility(8);
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InteractionListItem");
        }
    }

    public final void enableTextSelection() {
        this.textDetails.setEnabled(false);
        this.textDetails.setEnabled(true);
    }
}

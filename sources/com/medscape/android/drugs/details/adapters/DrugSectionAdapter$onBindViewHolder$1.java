package com.medscape.android.drugs.details.adapters;

import android.view.View;
import com.medscape.android.drugs.details.datamodels.InteractionListItem;
import com.medscape.android.drugs.details.datamodels.LineItem;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugSectionAdapter.kt */
final class DrugSectionAdapter$onBindViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ LineItem $item;
    final /* synthetic */ int $position;
    final /* synthetic */ DrugSectionAdapter this$0;

    DrugSectionAdapter$onBindViewHolder$1(DrugSectionAdapter drugSectionAdapter, int i, LineItem lineItem) {
        this.this$0 = drugSectionAdapter;
        this.$position = i;
        this.$item = lineItem;
    }

    public final void onClick(View view) {
        if (this.this$0.getPreviousDetailsShown() == this.$position) {
            LineItem lineItem = this.$item;
            if (lineItem != null) {
                ((InteractionListItem) lineItem).setDetailVisible(!((InteractionListItem) lineItem).isDetailVisible());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InteractionListItem");
            }
        } else {
            if (this.this$0.getPreviousDetailsShown() != -1) {
                LineItem lineItem2 = this.this$0.getMItems().get(this.this$0.getPreviousDetailsShown());
                if (lineItem2 != null) {
                    ((InteractionListItem) lineItem2).setDetailVisible(false);
                    DrugSectionAdapter drugSectionAdapter = this.this$0;
                    drugSectionAdapter.notifyItemChanged(drugSectionAdapter.getPreviousDetailsShown());
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InteractionListItem");
                }
            }
            LineItem lineItem3 = this.$item;
            if (lineItem3 == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InteractionListItem");
            } else if (!((InteractionListItem) lineItem3).isDetailVisible()) {
                ((InteractionListItem) this.$item).setDetailVisible(true);
                this.this$0.setPreviousDetailsShown(this.$position);
            }
        }
        this.this$0.notifyItemChanged(this.$position);
    }
}

package com.medscape.android.drugs.details.viewholders;

import android.view.View;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ContentViewHolder.kt */
final class ContentViewHolder$bindContent$1 implements View.OnClickListener {
    final /* synthetic */ ContentViewHolder this$0;

    ContentViewHolder$bindContent$1(ContentViewHolder contentViewHolder) {
        this.this$0 = contentViewHolder;
    }

    public final void onClick(View view) {
        DataViewHolder.DataListClickListener listener;
        if (this.this$0.getAdapterPosition() != -1 && (listener = this.this$0.getListener()) != null) {
            listener.onDataListItemClicked(this.this$0.getAdapterPosition());
        }
    }
}

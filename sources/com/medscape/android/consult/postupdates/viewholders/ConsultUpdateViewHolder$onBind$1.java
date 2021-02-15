package com.medscape.android.consult.postupdates.viewholders;

import android.view.View;
import com.medscape.android.consult.models.BodyUpdates;
import com.medscape.android.consult.models.ConsultPost;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ConsultUpdateViewHolder.kt */
final class ConsultUpdateViewHolder$onBind$1 implements View.OnClickListener {
    final /* synthetic */ BodyUpdates $bodyUpdates;
    final /* synthetic */ ConsultPost $consultPost;
    final /* synthetic */ ConsultUpdateViewHolder this$0;

    ConsultUpdateViewHolder$onBind$1(ConsultUpdateViewHolder consultUpdateViewHolder, ConsultPost consultPost, BodyUpdates bodyUpdates) {
        this.this$0 = consultUpdateViewHolder;
        this.$consultPost = consultPost;
        this.$bodyUpdates = bodyUpdates;
    }

    public final void onClick(View view) {
        if (this.$consultPost != null) {
            this.this$0.updateSelectedListener.onUpdateSelected(this.$consultPost, this.$bodyUpdates);
        }
    }
}

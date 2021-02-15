package com.medscape.android.reference.viewholders;

import android.view.View;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: TOCItemViewHolder.kt */
final class TOCItemViewHolder$bindData$1 implements View.OnClickListener {
    final /* synthetic */ ISectionItemClickListener $clickListener;
    final /* synthetic */ int $position;

    TOCItemViewHolder$bindData$1(ISectionItemClickListener iSectionItemClickListener, int i) {
        this.$clickListener = iSectionItemClickListener;
        this.$position = i;
    }

    public final void onClick(View view) {
        this.$clickListener.referenceSectionClicked(this.$position);
    }
}

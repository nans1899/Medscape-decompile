package com.medscape.android.activity.saved.adapters;

import android.view.View;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.activity.saved.model.TabLayoutElement;
import java.util.Map;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: SavedTabLayoutAdapter.kt */
final class SavedTabLayoutAdapter$onBindViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ TabLayoutElement $element;
    final /* synthetic */ int $position;
    final /* synthetic */ SavedTabLayoutAdapter this$0;

    SavedTabLayoutAdapter$onBindViewHolder$1(SavedTabLayoutAdapter savedTabLayoutAdapter, int i, TabLayoutElement tabLayoutElement) {
        this.this$0 = savedTabLayoutAdapter;
        this.$position = i;
        this.$element = tabLayoutElement;
    }

    public final void onClick(View view) {
        OmnitureManager.get().markModule(false, "content-tab", "tap", (Map<String, Object>) null);
        this.this$0.getListener().onTabClicked(this.$position, this.$element, true);
    }
}

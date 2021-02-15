package com.medscape.android.activity.calc.fragments;

import android.view.MenuItem;
import androidx.appcompat.widget.PopupMenu;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "item", "Landroid/view/MenuItem;", "kotlin.jvm.PlatformType", "onMenuItemClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: QuestionMultipleChoiceFragment.kt */
final class QuestionMultipleChoiceFragment$linkedCalculatorRowTapped$2 implements PopupMenu.OnMenuItemClickListener {
    final /* synthetic */ List $contentItems;
    final /* synthetic */ QuestionMultipleChoiceFragment this$0;

    QuestionMultipleChoiceFragment$linkedCalculatorRowTapped$2(QuestionMultipleChoiceFragment questionMultipleChoiceFragment, List list) {
        this.this$0 = questionMultipleChoiceFragment;
        this.$contentItems = list;
    }

    public final boolean onMenuItemClick(MenuItem menuItem) {
        QuestionMultipleChoiceFragment questionMultipleChoiceFragment = this.this$0;
        Intrinsics.checkNotNullExpressionValue(menuItem, ContentParser.ITEM);
        questionMultipleChoiceFragment.selectedLinkedCalculatorIndex = menuItem.getItemId();
        DBContentItem dBContentItem = (DBContentItem) this.$contentItems.get(this.this$0.selectedLinkedCalculatorIndex);
        QuestionMultipleChoiceFragment questionMultipleChoiceFragment2 = this.this$0;
        Intrinsics.checkNotNullExpressionValue(dBContentItem, "dbContentItem");
        questionMultipleChoiceFragment2.launchLinkedContentItem(dBContentItem, this.this$0.selectedLinkedCalculatorIndex);
        return true;
    }
}

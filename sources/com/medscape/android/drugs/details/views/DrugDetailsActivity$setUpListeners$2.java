package com.medscape.android.drugs.details.views;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.util.Util;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n \u0004*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "v", "Landroid/widget/TextView;", "kotlin.jvm.PlatformType", "actionId", "", "event", "Landroid/view/KeyEvent;", "onEditorAction"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivity.kt */
final class DrugDetailsActivity$setUpListeners$2 implements TextView.OnEditorActionListener {
    final /* synthetic */ DrugDetailsActivity this$0;

    DrugDetailsActivity$setUpListeners$2(DrugDetailsActivity drugDetailsActivity) {
        this.this$0 = drugDetailsActivity;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        boolean z = false;
        if (i != 3) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(textView, "v");
        if (textView.getText().toString().length() > 0) {
            z = true;
        }
        if (z) {
            if (DrugDetailsActivity.access$getToolbarViewModel$p(this.this$0).getLoadingItemsFinished()) {
                Util.hideKeyboard(this.this$0);
                this.this$0.getDrugSectionViewModel().startFind(textView.getText().toString());
                OmnitureManager.get().trackModule(this.this$0, Constants.OMNITURE_CHANNEL_REFERENCE, "find-on-page", "drgs", (Map<String, Object>) null);
            } else {
                DrugDetailsActivity drugDetailsActivity = this.this$0;
                Toast.makeText(drugDetailsActivity, drugDetailsActivity.getResources().getString(R.string.find_in_page_not_available), 1).show();
            }
        }
        return true;
    }
}

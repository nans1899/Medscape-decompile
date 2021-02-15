package com.medscape.android.activity.saved.views;

import android.content.res.Resources;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.PagerAdapter;
import com.medscape.android.R;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: SaveActivity.kt */
final class SaveActivity$setupActionBar$1 implements View.OnClickListener {
    final /* synthetic */ SaveActivity this$0;

    SaveActivity$setupActionBar$1(SaveActivity saveActivity) {
        this.this$0 = saveActivity;
    }

    public final void onClick(View view) {
        if (SaveActivity.access$getSaveViewModel$p(this.this$0).getEditModeActive()) {
            this.this$0.getDelete().setImageDrawable(ResourcesCompat.getDrawable(this.this$0.getResources(), R.drawable.ic_delete_selected, (Resources.Theme) null));
            SaveActivity.access$getSaveViewModel$p(this.this$0).setEditModeActive(false);
            PagerAdapter adapter = SaveActivity.access$getViewPager$p(this.this$0).getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        this.this$0.finish();
    }
}

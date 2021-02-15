package com.medscape.android.drugs.details.views;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.Observer;
import com.medscape.android.util.Util;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivity.kt */
final class DrugDetailsActivity$setUpObservers$1<T> implements Observer<Boolean> {
    final /* synthetic */ DrugDetailsActivity this$0;

    DrugDetailsActivity$setUpObservers$1(DrugDetailsActivity drugDetailsActivity) {
        this.this$0 = drugDetailsActivity;
    }

    public final void onChanged(Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            DrugDetailsActivity.access$getFindContainer$p(this.this$0).setVisibility(8);
            this.this$0.getToolbar().setVisibility(0);
            Util.hideKeyboard(this.this$0);
            ActionBar supportActionBar = this.this$0.getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }
            this.this$0.getDrugSectionViewModel().cancelFind();
            DrugDetailsActivity.access$getQueryEditText$p(this.this$0).getText().clear();
        } else {
            DrugDetailsActivity.access$getFindContainer$p(this.this$0).setVisibility(0);
            this.this$0.getToolbar().setVisibility(8);
            DrugDetailsActivity.access$getQueryEditText$p(this.this$0).requestFocus();
            Util.showKeyboard(this.this$0);
            ActionBar supportActionBar2 = this.this$0.getSupportActionBar();
            if (supportActionBar2 != null) {
                supportActionBar2.setDisplayHomeAsUpEnabled(false);
            }
        }
        this.this$0.invalidateOptionsMenu();
    }
}

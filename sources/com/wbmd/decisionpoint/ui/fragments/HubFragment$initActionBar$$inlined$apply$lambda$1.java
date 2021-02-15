package com.wbmd.decisionpoint.ui.fragments;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/wbmd/decisionpoint/ui/fragments/HubFragment$initActionBar$1$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: HubFragment.kt */
final class HubFragment$initActionBar$$inlined$apply$lambda$1 implements View.OnClickListener {
    final /* synthetic */ AppCompatActivity $parentActivity$inlined;
    final /* synthetic */ HubFragment this$0;

    HubFragment$initActionBar$$inlined$apply$lambda$1(HubFragment hubFragment, AppCompatActivity appCompatActivity) {
        this.this$0 = hubFragment;
        this.$parentActivity$inlined = appCompatActivity;
    }

    public final void onClick(View view) {
        this.$parentActivity$inlined.onBackPressed();
    }
}

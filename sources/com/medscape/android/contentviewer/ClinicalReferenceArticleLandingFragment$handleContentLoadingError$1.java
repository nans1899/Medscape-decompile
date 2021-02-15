package com.medscape.android.contentviewer;

import android.view.View;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
final class ClinicalReferenceArticleLandingFragment$handleContentLoadingError$1 implements View.OnClickListener {
    final /* synthetic */ ClinicalReferenceArticleLandingFragment this$0;

    ClinicalReferenceArticleLandingFragment$handleContentLoadingError$1(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment) {
        this.this$0 = clinicalReferenceArticleLandingFragment;
    }

    public final void onClick(View view) {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }
}

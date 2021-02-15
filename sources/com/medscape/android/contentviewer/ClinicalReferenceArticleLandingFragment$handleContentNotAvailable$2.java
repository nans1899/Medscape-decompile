package com.medscape.android.contentviewer;

import android.content.DialogInterface;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "dialog", "Landroid/content/DialogInterface;", "which", "", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
final class ClinicalReferenceArticleLandingFragment$handleContentNotAvailable$2 implements DialogInterface.OnClickListener {
    final /* synthetic */ ClinicalReferenceArticleLandingFragment this$0;

    ClinicalReferenceArticleLandingFragment$handleContentNotAvailable$2(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment) {
        this.this$0 = clinicalReferenceArticleLandingFragment;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}

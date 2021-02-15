package com.medscape.android.contentviewer;

import android.view.View;
import com.medscape.android.reference.ClinicalReferenceContentManager;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import com.medscape.android.util.MedscapeException;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
final class ClinicalReferenceArticleLandingFragment$handleContentNotDownloaded$1 implements Runnable {
    final /* synthetic */ String $action;
    final /* synthetic */ MedscapeException $mException;
    final /* synthetic */ ClinicalReferenceArticleLandingFragment this$0;

    ClinicalReferenceArticleLandingFragment$handleContentNotDownloaded$1(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment, MedscapeException medscapeException, String str) {
        this.this$0 = clinicalReferenceArticleLandingFragment;
        this.$mException = medscapeException;
        this.$action = str;
    }

    public final void run() {
        this.$mException.showSnackBar(this.this$0.getRootView(), -2, this.$action, new View.OnClickListener(this) {
            final /* synthetic */ ClinicalReferenceArticleLandingFragment$handleContentNotDownloaded$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                ClinicalReferenceContentManager mClinicalReferenceContentManager;
                ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p = this.this$0.this$0.crLandingViewModel;
                if (access$getCrLandingViewModel$p != null && (mClinicalReferenceContentManager = access$getCrLandingViewModel$p.getMClinicalReferenceContentManager()) != null) {
                    ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p2 = this.this$0.this$0.crLandingViewModel;
                    mClinicalReferenceContentManager.fetchArticleContent(access$getCrLandingViewModel$p2 != null ? access$getCrLandingViewModel$p2.getCrArticle() : null, this.this$0.this$0);
                }
            }
        });
    }
}

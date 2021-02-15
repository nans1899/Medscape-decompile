package com.medscape.android.contentviewer;

import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "textSize", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Integer;)V"}, k = 3, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
final class ClinicalReferenceArticleLandingFragment$setupObserver$1<T> implements Observer<Integer> {
    final /* synthetic */ ClinicalReferenceArticleLandingFragment this$0;

    ClinicalReferenceArticleLandingFragment$setupObserver$1(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment) {
        this.this$0 = clinicalReferenceArticleLandingFragment;
    }

    public final void onChanged(Integer num) {
        ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p = this.this$0.crLandingViewModel;
        if (access$getCrLandingViewModel$p != null) {
            TextView articleUpdateDate = this.this$0.getArticleUpdateDate();
            Intrinsics.checkNotNullExpressionValue(num, "textSize");
            access$getCrLandingViewModel$p.changeHeaderTextSize(articleUpdateDate, num.intValue());
        }
        ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p2 = this.this$0.crLandingViewModel;
        if (access$getCrLandingViewModel$p2 != null) {
            TextView updateLabel = this.this$0.getUpdateLabel();
            Intrinsics.checkNotNullExpressionValue(num, "textSize");
            access$getCrLandingViewModel$p2.changeHeaderTextSize(updateLabel, num.intValue());
        }
        ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p3 = this.this$0.crLandingViewModel;
        if (access$getCrLandingViewModel$p3 != null) {
            TextView articleAuthor = this.this$0.getArticleAuthor();
            Intrinsics.checkNotNullExpressionValue(num, "textSize");
            access$getCrLandingViewModel$p3.changeHeaderTextSize(articleAuthor, num.intValue());
        }
        ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p4 = this.this$0.crLandingViewModel;
        if (access$getCrLandingViewModel$p4 != null) {
            TextView authorLabel = this.this$0.getAuthorLabel();
            Intrinsics.checkNotNullExpressionValue(num, "textSize");
            access$getCrLandingViewModel$p4.changeHeaderTextSize(authorLabel, num.intValue());
        }
        ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p5 = this.this$0.crLandingViewModel;
        if (access$getCrLandingViewModel$p5 != null) {
            TextView chiefEditor = this.this$0.getChiefEditor();
            Intrinsics.checkNotNullExpressionValue(num, "textSize");
            access$getCrLandingViewModel$p5.changeHeaderTextSize(chiefEditor, num.intValue());
        }
        ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p6 = this.this$0.crLandingViewModel;
        if (access$getCrLandingViewModel$p6 != null) {
            TextView chiefLabel = this.this$0.getChiefLabel();
            Intrinsics.checkNotNullExpressionValue(num, "textSize");
            access$getCrLandingViewModel$p6.changeHeaderTextSize(chiefLabel, num.intValue());
        }
    }
}

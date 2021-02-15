package com.medscape.android.contentviewer;

import androidx.lifecycle.Observer;
import com.medscape.android.landingfeed.repository.Status;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/medscape/android/landingfeed/repository/Status;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
final class ClinicalReferenceArticleLandingFragment$setupObserver$2<T> implements Observer<Status> {
    final /* synthetic */ ClinicalReferenceArticleLandingFragment this$0;

    ClinicalReferenceArticleLandingFragment$setupObserver$2(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment) {
        this.this$0 = clinicalReferenceArticleLandingFragment;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.medscape.android.reference.model.ClinicalReferenceContent} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onChanged(com.medscape.android.landingfeed.repository.Status r5) {
        /*
            r4 = this;
            r0 = 2
            r1 = 1
            r2 = 0
            if (r5 != 0) goto L_0x0006
            goto L_0x0012
        L_0x0006:
            int[] r3 = com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment.WhenMappings.$EnumSwitchMapping$1
            int r5 = r5.ordinal()
            r5 = r3[r5]
            if (r5 == r1) goto L_0x0092
            if (r5 == r0) goto L_0x007c
        L_0x0012:
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r5 = r5.crLandingViewModel
            if (r5 == 0) goto L_0x001f
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel$ContentError r5 = r5.getContentErrorLoading()
            goto L_0x0020
        L_0x001f:
            r5 = r2
        L_0x0020:
            if (r5 == 0) goto L_0x00a7
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r5 = r5.crLandingViewModel
            if (r5 == 0) goto L_0x002f
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel$ContentError r5 = r5.getContentErrorLoading()
            goto L_0x0030
        L_0x002f:
            r5 = r2
        L_0x0030:
            if (r5 != 0) goto L_0x0034
            goto L_0x00a7
        L_0x0034:
            int[] r3 = com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment.WhenMappings.$EnumSwitchMapping$0
            int r5 = r5.ordinal()
            r5 = r3[r5]
            if (r5 == r1) goto L_0x005a
            if (r5 == r0) goto L_0x004a
            r0 = 3
            if (r5 == r0) goto L_0x0044
            goto L_0x00a7
        L_0x0044:
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            r5.handleContentNotDownloaded()
            goto L_0x00a7
        L_0x004a:
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r0 = r5.crLandingViewModel
            if (r0 == 0) goto L_0x0056
            java.lang.String r2 = r0.getErrMessage()
        L_0x0056:
            r5.handleContentLoadingError(r2)
            goto L_0x00a7
        L_0x005a:
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r5 = r5.crLandingViewModel
            if (r5 == 0) goto L_0x00a7
            java.lang.Boolean r5 = r5.getErrorAlert()
            if (r5 == 0) goto L_0x00a7
            boolean r5 = r5.booleanValue()
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r0 = r4.this$0
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r1 = r0.crLandingViewModel
            if (r1 == 0) goto L_0x0078
            java.lang.String r2 = r1.getErrorUrl()
        L_0x0078:
            r0.handleContentNotAvailable(r2, r5)
            goto L_0x00a7
        L_0x007c:
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            android.widget.ProgressBar r5 = r5.getProgressBar()
            r0 = 0
            r5.setVisibility(r0)
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            androidx.recyclerview.widget.RecyclerView r5 = r5.getSectionsList()
            r0 = 8
            r5.setVisibility(r0)
            goto L_0x00a7
        L_0x0092:
            com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment r5 = r4.this$0
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r0 = r5.crLandingViewModel
            if (r0 == 0) goto L_0x00a4
            com.medscape.android.reference.model.ClinicalReferenceArticle r0 = r0.getCrArticle()
            if (r0 == 0) goto L_0x00a4
            com.medscape.android.reference.model.ClinicalReferenceContent r2 = r0.getContent()
        L_0x00a4:
            r5.handleContentloaded(r2)
        L_0x00a7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment$setupObserver$2.onChanged(com.medscape.android.landingfeed.repository.Status):void");
    }
}

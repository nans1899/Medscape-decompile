package com.medscape.android.contentviewer;

import android.view.View;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "v", "Landroid/view/View;", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingFragment.kt */
final class ClinicalReferenceArticleLandingFragment$handleContentNotAvailable$3 implements View.OnClickListener {
    final /* synthetic */ String $url;
    final /* synthetic */ ClinicalReferenceArticleLandingFragment this$0;

    ClinicalReferenceArticleLandingFragment$handleContentNotAvailable$3(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment, String str) {
        this.this$0 = clinicalReferenceArticleLandingFragment;
        this.$url = str;
    }

    public final void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://emedicine.medscape.com/article/");
        ClinicalReferenceArticleViewModel access$getCrLandingViewModel$p = this.this$0.crLandingViewModel;
        sb.append(StringUtil.optString(access$getCrLandingViewModel$p != null ? access$getCrLandingViewModel$p.parseArticleIdFromUrl(this.$url) : null));
        Util.openInExternalBrowser(this.this$0.getActivity(), sb.toString());
    }
}

package com.medscape.android.activity.cme.views;

import androidx.lifecycle.Observer;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.analytics.ClickStreamManager;
import com.wbmd.ads.model.AdContentData;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "article", "Lcom/webmd/wbmdcmepulse/models/articles/Article;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: MedscapeCMEInfoActivity.kt */
final class MedscapeCMEInfoActivity$onCreate$1<T> implements Observer<Article> {
    final /* synthetic */ MedscapeCMEInfoActivity this$0;

    MedscapeCMEInfoActivity$onCreate$1(MedscapeCMEInfoActivity medscapeCMEInfoActivity) {
        this.this$0 = medscapeCMEInfoActivity;
    }

    public final void onChanged(Article article) {
        com.medscape.android.parser.model.Article recentlyArtcile;
        com.medscape.android.parser.model.Article recentlyArtcile2;
        if (article != null) {
            HashMap hashMap = new HashMap();
            Map map = hashMap;
            map.put(AdContentData.LEAD_SPECIALITY, article.leadSpec);
            map.put(AdContentData.LEAD_CONCEPT, article.leadConcept);
            map.put("cg", article.contentGroup);
            String generatePVID = OmnitureManager.get().generatePVID();
            ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
            com.medscape.android.parser.model.Article recentlyArtcile3 = this.this$0.getRecentlyArtcile();
            String str = null;
            String valueOf = String.valueOf(recentlyArtcile3 != null ? recentlyArtcile3.mArticleId : null);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            boolean z = true;
            Object[] objArr = new Object[1];
            com.medscape.android.parser.model.Article recentlyArtcile4 = this.this$0.getRecentlyArtcile();
            objArr[0] = String.valueOf(recentlyArtcile4 != null ? recentlyArtcile4.mArticleId : null);
            String format = String.format(OmnitureData.PAGE_NAME_ARTICLE_INFO_LAYER, Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            ClickStreamManager.INSTANCE.sendEvent(this.this$0, ClickstreamConstants.EventType.pageView, article.title, hashMap, (Impression[]) null, (String[]) null, generatePVID, chronicleIDUtil.generateAssetId(valueOf, (String) null, format));
            if (this.this$0.getRecentlyArtcile() == null) {
                this.this$0.setRecentlyArtcile(new com.medscape.android.parser.model.Article());
            }
            com.medscape.android.parser.model.Article recentlyArtcile5 = this.this$0.getRecentlyArtcile();
            if (recentlyArtcile5 != null) {
                recentlyArtcile5.mArticleId = article.id;
            }
            com.medscape.android.parser.model.Article recentlyArtcile6 = this.this$0.getRecentlyArtcile();
            CharSequence charSequence = recentlyArtcile6 != null ? recentlyArtcile6.mLink : null;
            if ((charSequence == null || StringsKt.isBlank(charSequence)) && (recentlyArtcile2 = this.this$0.getRecentlyArtcile()) != null) {
                recentlyArtcile2.mLink = Utilities.getCMEUrl(this.this$0, article.id);
            }
            com.medscape.android.parser.model.Article recentlyArtcile7 = this.this$0.getRecentlyArtcile();
            if (recentlyArtcile7 != null) {
                str = recentlyArtcile7.mTitle;
            }
            CharSequence charSequence2 = str;
            if (charSequence2 != null && !StringsKt.isBlank(charSequence2)) {
                z = false;
            }
            if (z && (recentlyArtcile = this.this$0.getRecentlyArtcile()) != null) {
                recentlyArtcile.mTitle = article.title;
            }
        }
        CMEHelper cMEHelper = CMEHelper.INSTANCE;
        MedscapeCMEInfoActivity medscapeCMEInfoActivity = this.this$0;
        cMEHelper.saveToRecentlyViewed(medscapeCMEInfoActivity, medscapeCMEInfoActivity.getRecentlyArtcile());
    }
}

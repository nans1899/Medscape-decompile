package com.medscape.android.activity.cme.views;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.analytics.ClickStreamManager;
import com.wbmd.ads.model.AdContentData;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.activities.CMETrackerActivity;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"Lcom/medscape/android/activity/cme/views/MedscapeCMETrackerActivity;", "Lcom/webmd/wbmdcmepulse/activities/CMETrackerActivity;", "()V", "getCMELaunchIntent", "Landroid/content/Intent;", "getCMEWebViewIntent", "sendArticleViewedEvent", "", "article", "Lcom/webmd/wbmdcmepulse/models/articles/Article;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeCMETrackerActivity.kt */
public final class MedscapeCMETrackerActivity extends CMETrackerActivity {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public Intent getCMELaunchIntent() {
        Context context = this;
        CMEHelper.registerSaveReceiver(context);
        return new Intent(context, MedscapeCMEInfoActivity.class);
    }

    public Intent getCMEWebViewIntent() {
        return new Intent(this, MedscapeCmeArticleWebActivity.class);
    }

    public void sendArticleViewedEvent(Article article) {
        if (article != null) {
            HashMap hashMap = new HashMap();
            Map map = hashMap;
            map.put(AdContentData.LEAD_SPECIALITY, article.leadSpec);
            map.put(AdContentData.LEAD_CONCEPT, article.leadConcept);
            map.put("cg", article.contentGroup);
            String generatePVID = OmnitureManager.get().generatePVID();
            ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
            String str = article.id;
            String str2 = article.assetId;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(OmnitureData.PAGE_NAME_ARTICLE_INFO_LAYER, Arrays.copyOf(new Object[]{article.id}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            ClickStreamManager.INSTANCE.sendEvent(this, ClickstreamConstants.EventType.pageView, article.title, hashMap, (Impression[]) null, (String[]) null, generatePVID, chronicleIDUtil.generateAssetId(str, str2, format));
        }
    }
}

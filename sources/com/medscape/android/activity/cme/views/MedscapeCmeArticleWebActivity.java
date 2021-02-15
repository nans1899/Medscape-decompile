package com.medscape.android.activity.cme.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.activity.rss.NewsManager;
import com.medscape.android.analytics.ClickStreamManager;
import com.medscape.android.parser.model.Article;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.activities.CmeArticleWebActivity;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/activity/cme/views/MedscapeCmeArticleWebActivity;", "Lcom/webmd/wbmdcmepulse/activities/CmeArticleWebActivity;", "()V", "recentlyArtcile", "Lcom/medscape/android/parser/model/Article;", "getRecentlyArtcile", "()Lcom/medscape/android/parser/model/Article;", "setRecentlyArtcile", "(Lcom/medscape/android/parser/model/Article;)V", "getUserAgentAppName", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "MyJavaScriptInterface", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeCmeArticleWebActivity.kt */
public final class MedscapeCmeArticleWebActivity extends CmeArticleWebActivity {
    private HashMap _$_findViewCache;
    private Article recentlyArtcile;

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

    /* access modifiers changed from: protected */
    public String getUserAgentAppName() {
        return Constants.USER_AGENT_APP_NAME;
    }

    public final Article getRecentlyArtcile() {
        return this.recentlyArtcile;
    }

    public final void setRecentlyArtcile(Article article) {
        this.recentlyArtcile = article;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.recentlyArtcile = intent != null ? (Article) intent.getParcelableExtra(Constants.EXTRA_RECENTLY_VIEWED_ARTICLE) : null;
        if (this.mWebView != null) {
            this.mWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/activity/cme/views/MedscapeCmeArticleWebActivity$MyJavaScriptInterface;", "", "(Lcom/medscape/android/activity/cme/views/MedscapeCmeArticleWebActivity;)V", "processHTML", "", "html", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MedscapeCmeArticleWebActivity.kt */
    public final class MyJavaScriptInterface {
        public MyJavaScriptInterface() {
        }

        @JavascriptInterface
        public final void processHTML(String str) {
            Article recentlyArtcile;
            Article recentlyArtcile2;
            Intrinsics.checkNotNullParameter(str, "html");
            HashMap hashMap = new HashMap();
            hashMap.putAll(NewsManager.getScreenMapsFromHtml(str));
            if (MedscapeCmeArticleWebActivity.this.getRecentlyArtcile() == null) {
                MedscapeCmeArticleWebActivity.this.setRecentlyArtcile(new Article());
            }
            Article recentlyArtcile3 = MedscapeCmeArticleWebActivity.this.getRecentlyArtcile();
            if (recentlyArtcile3 != null) {
                recentlyArtcile3.mArticleId = MedscapeCmeArticleWebActivity.this.mArticleId;
            }
            Article recentlyArtcile4 = MedscapeCmeArticleWebActivity.this.getRecentlyArtcile();
            CharSequence charSequence = recentlyArtcile4 != null ? recentlyArtcile4.mLink : null;
            if ((charSequence == null || StringsKt.isBlank(charSequence)) && (recentlyArtcile2 = MedscapeCmeArticleWebActivity.this.getRecentlyArtcile()) != null) {
                MedscapeCmeArticleWebActivity medscapeCmeArticleWebActivity = MedscapeCmeArticleWebActivity.this;
                recentlyArtcile2.mLink = Utilities.getCMEUrl(medscapeCmeArticleWebActivity, medscapeCmeArticleWebActivity.mArticleId);
            }
            Article recentlyArtcile5 = MedscapeCmeArticleWebActivity.this.getRecentlyArtcile();
            CharSequence charSequence2 = recentlyArtcile5 != null ? recentlyArtcile5.mTitle : null;
            if ((charSequence2 == null || StringsKt.isBlank(charSequence2)) && (recentlyArtcile = MedscapeCmeArticleWebActivity.this.getRecentlyArtcile()) != null) {
                recentlyArtcile.mTitle = MedscapeCmeArticleWebActivity.this.getArticleTitle();
            }
            String generatePVID = OmnitureManager.get().generatePVID();
            ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
            Article recentlyArtcile6 = MedscapeCmeArticleWebActivity.this.getRecentlyArtcile();
            String valueOf = String.valueOf(recentlyArtcile6 != null ? recentlyArtcile6.mArticleId : null);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[1];
            Article recentlyArtcile7 = MedscapeCmeArticleWebActivity.this.getRecentlyArtcile();
            objArr[0] = String.valueOf(recentlyArtcile7 != null ? recentlyArtcile7.mArticleId : null);
            String format = String.format(OmnitureData.PAGE_NAME_ARTICLE_INFO_LAYER, Arrays.copyOf(objArr, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            ClickStreamManager.INSTANCE.sendEvent(MedscapeCmeArticleWebActivity.this, ClickstreamConstants.EventType.pageView, MedscapeCmeArticleWebActivity.this.getArticleTitle(), hashMap, (Impression[]) null, (String[]) null, generatePVID, chronicleIDUtil.generateAssetId(valueOf, (String) null, format));
            CMEHelper cMEHelper = CMEHelper.INSTANCE;
            MedscapeCmeArticleWebActivity medscapeCmeArticleWebActivity2 = MedscapeCmeArticleWebActivity.this;
            cMEHelper.saveToRecentlyViewed(medscapeCmeArticleWebActivity2, medscapeCmeArticleWebActivity2.getRecentlyArtcile());
        }
    }
}

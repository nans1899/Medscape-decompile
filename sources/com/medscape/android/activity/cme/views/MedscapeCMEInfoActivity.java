package com.medscape.android.activity.cme.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.medscape.android.Constants;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.webmd.wbmdcmepulse.activities.CmeArticleInfoActivity;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0014R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/activity/cme/views/MedscapeCMEInfoActivity;", "Lcom/webmd/wbmdcmepulse/activities/CmeArticleInfoActivity;", "()V", "recentlyArtcile", "Lcom/medscape/android/parser/model/Article;", "getRecentlyArtcile", "()Lcom/medscape/android/parser/model/Article;", "setRecentlyArtcile", "(Lcom/medscape/android/parser/model/Article;)V", "getCMEWebViewIntent", "Landroid/content/Intent;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeCMEInfoActivity.kt */
public final class MedscapeCMEInfoActivity extends CmeArticleInfoActivity {
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
        Util.sendFirebaseContentInfo(this, FeedConstants.CME_ITEM, this.mArticleId);
        this.mArticle.observe(this, new MedscapeCMEInfoActivity$onCreate$1(this));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        AppboyEventsHandler.routeDailyEventsToFirebaseOrBraze(this, AppboyConstants.APPBOY_EVENT_CME_VIEWED);
    }

    /* access modifiers changed from: protected */
    public Intent getCMEWebViewIntent() {
        return new Intent(this, MedscapeCmeArticleWebActivity.class);
    }
}

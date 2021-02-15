package com.medscape.android.activity.decisionpoint;

import android.content.Intent;
import android.view.View;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.wbmd.decisionpoint.ui.activities.HubActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"Lcom/medscape/android/activity/decisionpoint/DecisionPointHubActivity;", "Lcom/wbmd/decisionpoint/ui/activities/HubActivity;", "()V", "onExploreClick", "", "url", "", "title", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DecisionPointHubActivity.kt */
public final class DecisionPointHubActivity extends HubActivity {
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

    public void onExploreClick(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "title");
        Intent intent = new Intent(this, DecisionPointWebActivity.class);
        intent.putExtra(WebviewUtil.LINK_EXTRA, str);
        intent.putExtra(WebviewUtil.TITLE_EXTRA, str2);
        intent.putExtra(WebviewUtil.SHAREABLE_EXTRA, true);
        startActivity(intent);
    }
}

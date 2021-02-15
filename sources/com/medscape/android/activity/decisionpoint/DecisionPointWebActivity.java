package com.medscape.android.activity.decisionpoint;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.medscape.android.R;
import com.medscape.android.activity.webviews.CommonWebViewActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/activity/decisionpoint/DecisionPointWebActivity;", "Lcom/medscape/android/activity/webviews/CommonWebViewActivity;", "()V", "setupActionBar", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DecisionPointWebActivity.kt */
public final class DecisionPointWebActivity extends CommonWebViewActivity {
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

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Context context = this;
        getWebViewModel().setActionBarTitle(context, getSupportActionBar(), getTitle());
        if (getSupportActionBar() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(context, R.color.button_normal));
            ActionBar supportActionBar2 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar2);
            supportActionBar2.setBackgroundDrawable(colorDrawable);
            ActionBar supportActionBar3 = getSupportActionBar();
            Intrinsics.checkNotNull(supportActionBar3);
            supportActionBar3.setDisplayShowTitleEnabled(true);
            setTitle(getWebViewModel().getTitle());
            getWebViewModel().setActionBarTitle(context, getSupportActionBar(), getWebViewModel().getTitle());
        }
    }
}

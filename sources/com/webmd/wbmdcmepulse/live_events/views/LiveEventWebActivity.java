package com.webmd.wbmdcmepulse.live_events.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CmeArticleWebActivity;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/views/LiveEventWebActivity;", "Lcom/webmd/wbmdcmepulse/activities/CmeArticleWebActivity;", "()V", "liveEventRegistrationUrl", "", "loadContent", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventWebActivity.kt */
public final class LiveEventWebActivity extends CmeArticleWebActivity {
    private HashMap _$_findViewCache;
    private String liveEventRegistrationUrl;

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
    public void onCreate(Bundle bundle) {
        String stringExtra = getIntent().getStringExtra(Constants.BUNDLE_KEY_LIVE_EVENT_REGISTRATION_URL);
        this.liveEventRegistrationUrl = stringExtra;
        if (stringExtra == null) {
            finish();
        } else if (StringsKt.startsWith$default(String.valueOf(stringExtra), "http://", false, 2, (Object) null)) {
            this.liveEventRegistrationUrl = StringsKt.replace$default(String.valueOf(this.liveEventRegistrationUrl), "http://", "https://", false, 4, (Object) null);
        }
        super.onCreate(bundle);
        loadContent();
    }

    /* access modifiers changed from: protected */
    public void loadContent() {
        setUpDefaultActionBar(getResources().getString(R.string.live_event_registration_string_title), true);
        initializeViews(this.liveEventRegistrationUrl);
        initCookies(this.liveEventRegistrationUrl);
        loadWebPage(this.liveEventRegistrationUrl);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}

package com.webmd.webmdrx.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.webmdrx.R;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\nH\u0014J\b\u0010\u0012\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/webmd/webmdrx/activities/RxFaqActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "mWebView", "Landroid/webkit/WebView;", "page", "", "getPage", "()Ljava/lang/String;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "sendOmniturePing", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxFaqActivity.kt */
public final class RxFaqActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private WebView mWebView;
    private final String page = "webmd.com/rx/drug-prices/showcard/faq/";

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

    public final String getPage() {
        return this.page;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rx_faq);
        View findViewById = findViewById(R.id.web_view_rx_faq);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.web_view_rx_faq)");
        WebView webView = (WebView) findViewById;
        this.mWebView = webView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        }
        webView.loadUrl("file:///android_asset/faqRx.html");
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (!getResources().getBoolean(R.bool.rx_is_professional)) {
            sendOmniturePing();
        }
        super.onResume();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    private final void sendOmniturePing() {
        WBMDOmnitureManager wBMDOmnitureManager = WBMDOmnitureManager.shared;
        Intrinsics.checkNotNullExpressionValue(wBMDOmnitureManager, "WBMDOmnitureManager.shared");
        String lastSentPage = wBMDOmnitureManager.getLastSentPage();
        if (!StringExtensions.isNullOrEmpty(lastSentPage)) {
            lastSentPage = StringExtensions.removeTrailingSlash(lastSentPage);
        }
        String removeTrailingSlash = StringExtensions.removeTrailingSlash(this.page);
        HashMap hashMap = new HashMap();
        hashMap.put("waap.section", "rx");
        WBMDOmnitureManager.sendPageView(removeTrailingSlash, hashMap, new WBMDOmnitureModule((String) null, (String) null, lastSentPage));
    }
}

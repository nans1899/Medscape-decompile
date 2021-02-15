package com.wbmd.decisionpoint.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.databinding.ActivityHubWebViewBinding;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\u0012\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/wbmd/decisionpoint/ui/activities/WebViewActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/wbmd/decisionpoint/databinding/ActivityHubWebViewBinding;", "url", "", "webTitle", "initActionBar", "", "initWebClient", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "Companion", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WebViewActivity.kt */
public final class WebViewActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String KEY_TITLE = "hub_web_view_title";
    private static final String KEY_URL = "hub_web_view_url";
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public ActivityHubWebViewBinding binding;
    private String url;
    private String webTitle;

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

    public static final /* synthetic */ ActivityHubWebViewBinding access$getBinding$p(WebViewActivity webViewActivity) {
        ActivityHubWebViewBinding activityHubWebViewBinding = webViewActivity.binding;
        if (activityHubWebViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return activityHubWebViewBinding;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/wbmd/decisionpoint/ui/activities/WebViewActivity$Companion;", "", "()V", "KEY_TITLE", "", "KEY_URL", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "url", "title", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: WebViewActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent newIntent(Context context, String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str2, "title");
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(WebViewActivity.KEY_URL, str);
            intent.putExtra(WebViewActivity.KEY_TITLE, str2);
            return intent;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_hub_web_view);
        Intrinsics.checkNotNullExpressionValue(contentView, "DataBindingUtil.setConte…ut.activity_hub_web_view)");
        this.binding = (ActivityHubWebViewBinding) contentView;
        initActionBar();
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.url = extras.getString(KEY_URL);
            this.webTitle = extras.getString(KEY_TITLE);
        }
        initWebClient();
    }

    private final void initWebClient() {
        String str = this.url;
        if (str != null) {
            ActivityHubWebViewBinding activityHubWebViewBinding = this.binding;
            if (activityHubWebViewBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            activityHubWebViewBinding.webView.loadUrl(str);
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) this.webTitle);
        }
        ActivityHubWebViewBinding activityHubWebViewBinding2 = this.binding;
        if (activityHubWebViewBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView = activityHubWebViewBinding2.webView;
        Intrinsics.checkNotNullExpressionValue(webView, "binding.webView");
        webView.setWebViewClient(new WebViewActivity$initWebClient$3(this));
    }

    private final void initActionBar() {
        ActivityHubWebViewBinding activityHubWebViewBinding = this.binding;
        if (activityHubWebViewBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        setSupportActionBar(activityHubWebViewBinding.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_back_white);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setTitle((CharSequence) "");
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

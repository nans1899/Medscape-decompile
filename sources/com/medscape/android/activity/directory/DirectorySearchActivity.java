package com.medscape.android.activity.directory;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.custom.CustomMenu;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import java.util.Map;

public class DirectorySearchActivity extends AbstractBreadcrumbNavigableActivity {
    protected static final int HANDLE_NETWORK_ERROR_ON_PAGE_FINISHED = 3;
    protected static final int HIDE_FOOTER = 1;
    protected static final int HIDE_PROGRESS_BAR = 2;
    /* access modifiers changed from: private */
    public View errorView;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                WebView webView = (WebView) DirectorySearchActivity.this.findViewById(R.id.webView);
                webView.loadUrl("javascript:document.getElementById('mobilelogo').style.display = 'none';");
                webView.loadUrl("javascript:document.getElementById('mobilefooter').style.display = 'none';");
            } else if (i == 2) {
                DirectorySearchActivity.this.progressBar.setVisibility(8);
            } else if (i == 3) {
                if (!DirectorySearchActivity.this.isFinishing()) {
                    DirectorySearchActivity.this.showDialog(5);
                }
                WebView webView2 = (WebView) DirectorySearchActivity.this.findViewById(R.id.webView);
                if (webView2.canGoBack()) {
                    webView2.goBack();
                }
                DirectorySearchActivity.this.h.sendEmptyMessage(1);
            }
            return true;
        }
    });
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.generic_webview);
        findViewById(R.id.ad).setVisibility(8);
        Util.setCookie(this);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        this.webView = (WebView) findViewById(R.id.webView);
        this.errorView = findViewById(R.id.noNetworkView);
        this.progressBar.setVisibility(0);
        findViewById(R.id.sectionHeaderTextView).setVisibility(8);
        if (!Util.isTestDriveTimeSet(this) || Util.isTestDriveTimeFinished(this)) {
            setTitle(getString(R.string.title_directory));
            init();
        } else {
            showDialog(8);
        }
        AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_DIRECTORY_VIEWED, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "webview-launch", "drcty", (Map<String, Object>) null);
    }

    /* access modifiers changed from: private */
    public void init() {
        if (!Util.isOnline(getApplicationContext())) {
            this.progressBar.setVisibility(8);
            this.errorView.setVisibility(0);
            ((Button) this.errorView.findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DirectorySearchActivity.this.errorView.setVisibility(8);
                    DirectorySearchActivity.this.progressBar.setVisibility(0);
                    DirectorySearchActivity.this.init();
                }
            });
            return;
        }
        WebView webView2 = this.webView;
        webView2.loadUrl("https://doctor.webmd.com/syn/home" + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl("https://doctor.webmd.com/syn/home"));
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new InsideWebViewClient());
        String addUserAgent = Util.addUserAgent(this.webView, this);
        if (!addUserAgent.toLowerCase().contains("mobile")) {
            this.webView.getSettings().setUserAgentString(Util.addMobileWebviewUserAgentString(addUserAgent));
        } else {
            this.webView.getSettings().setUserAgentString(addUserAgent);
        }
        this.webView.requestFocus();
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str != null) {
                if (str.startsWith("tel:")) {
                    Uri.parse(str);
                    DirectorySearchActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } else if (str.startsWith("geo")) {
                    Uri.parse(str);
                    DirectorySearchActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                } else if (str.startsWith("mail")) {
                    Uri.parse(str);
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    DirectorySearchActivity directorySearchActivity = DirectorySearchActivity.this;
                    if (Util.isEmailConfigured(directorySearchActivity, intent)) {
                        DirectorySearchActivity.this.startActivity(intent);
                    } else if (!directorySearchActivity.isFinishing()) {
                        DialogUtil.showAlertDialog(24, (String) null, directorySearchActivity.getString(R.string.alert_show_email_configure_message), directorySearchActivity).show();
                    }
                    return true;
                } else if (!Util.isOnline(DirectorySearchActivity.this)) {
                    if (!DirectorySearchActivity.this.isFinishing()) {
                        DirectorySearchActivity.this.showDialog(5);
                    }
                    DirectorySearchActivity.this.h.sendEmptyMessage(2);
                    return true;
                } else {
                    DirectorySearchActivity.this.progressBar.setVisibility(0);
                }
            }
            return false;
        }

        public void onPageFinished(WebView webView, String str) {
            Util.addZoomControl(webView);
            DirectorySearchActivity.this.h.sendEmptyMessage(2);
            if (!Util.isOnline(DirectorySearchActivity.this)) {
                DirectorySearchActivity.this.h.sendEmptyMessage(3);
            }
            DirectorySearchActivity.this.h.sendEmptyMessage(1);
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        String string = getResources().getString(R.string.alert_dialog_directory_network_connection_error_message);
        if (i == 5) {
            return DialogUtil.showAlertDialog(5, (String) null, string, this);
        }
        if (i != 8) {
            return null;
        }
        return DialogUtil.showAlertDialog(8, (String) null, getResources().getString(R.string.alert_dialog_signup_required_message), this);
    }

    public void onBackPressed() {
        if (CustomMenu.isShowing()) {
            CustomMenu.hide();
            return;
        }
        WebView webView2 = (WebView) findViewById(R.id.webView);
        if (webView2 == null || webView2.getUrl() == null) {
            super.onBackPressed();
        } else if (webView2.canGoBack()) {
            webView2.goBack();
        } else {
            finish();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}

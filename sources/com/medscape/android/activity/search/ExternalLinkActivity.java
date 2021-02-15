package com.medscape.android.activity.search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.medscape.android.R;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.custom.CustomMenu;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomWebView;

public class ExternalLinkActivity extends NavigableBaseActivity {
    protected static final int HIDE_PROGRESS_BAR = 1;
    protected static final int SHOW_ARTICLE_DOWNLOAD_ERROR_MSG = 3;
    private static final int SHOW_PROGRESS_BAR = 2;
    public static final String TAG = "ExternalLinkAcitivity";
    private CustomWebView articleWebView;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                ExternalLinkActivity.this.progressBar.setVisibility(8);
            } else if (i == 2) {
                ExternalLinkActivity.this.progressBar.setVisibility(0);
            } else if (i == 3 && !ExternalLinkActivity.this.isFinishing()) {
                ExternalLinkActivity.this.showDialog(16);
            }
            return true;
        }
    });
    /* access modifiers changed from: private */
    public ProgressBar progressBar;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r6) {
        /*
            r5 = this;
            super.onCreate(r6)
            r6 = 2131558984(0x7f0d0248, float:1.87433E38)
            r5.setContentView((int) r6)
            r6 = 2131363375(0x7f0a062f, float:1.8346557E38)
            android.view.View r6 = r5.findViewById(r6)
            android.widget.ProgressBar r6 = (android.widget.ProgressBar) r6
            r5.progressBar = r6
            com.medscape.android.util.Util.setCookie(r5)
            r6 = 2131362096(0x7f0a0130, float:1.8343963E38)
            android.view.View r6 = r5.findViewById(r6)
            com.medscape.android.view.CustomWebView r6 = (com.medscape.android.view.CustomWebView) r6
            r5.articleWebView = r6
            android.webkit.WebSettings r6 = r6.getSettings()
            com.medscape.android.view.CustomWebView r0 = r5.articleWebView
            java.lang.String r0 = com.medscape.android.util.Util.addUserAgent(r0, r5)
            r6.setUserAgentString(r0)
            com.medscape.android.view.CustomWebView r6 = r5.articleWebView
            android.webkit.WebSettings r6 = r6.getSettings()
            r0 = 1
            r6.setJavaScriptEnabled(r0)
            com.medscape.android.view.CustomWebView r6 = r5.articleWebView
            android.webkit.WebSettings r6 = r6.getSettings()
            r6.setBuiltInZoomControls(r0)
            com.medscape.android.view.CustomWebView r6 = r5.articleWebView
            com.medscape.android.activity.search.ExternalLinkActivity$InsideWebViewClient r1 = new com.medscape.android.activity.search.ExternalLinkActivity$InsideWebViewClient
            r2 = 0
            r1.<init>()
            r6.setWebViewClient(r1)
            com.medscape.android.view.CustomWebView r6 = r5.articleWebView
            android.webkit.WebSettings r6 = r6.getSettings()
            android.webkit.WebSettings$PluginState r1 = android.webkit.WebSettings.PluginState.ON
            r6.setPluginState(r1)
            android.content.Intent r6 = r5.getIntent()
            java.lang.String r1 = ""
            if (r6 == 0) goto L_0x007f
            android.content.Intent r6 = r5.getIntent()
            android.os.Bundle r6 = r6.getExtras()
            java.lang.String r1 = "url"
            java.lang.Object r6 = r6.get(r1)
            r1 = r6
            java.lang.String r1 = (java.lang.String) r1
            android.content.Intent r6 = r5.getIntent()
            java.lang.String r3 = "com.medscape.android.EXTRA_WEBVIEW_TITLE"
            java.lang.String r6 = r6.getStringExtra(r3)
            r4 = r1
            r1 = r6
            r6 = r4
            goto L_0x0080
        L_0x007f:
            r6 = r1
        L_0x0080:
            if (r1 == 0) goto L_0x00a9
            boolean r3 = r1.isEmpty()
            if (r3 != 0) goto L_0x00a9
            androidx.appcompat.app.ActionBar r3 = r5.getSupportActionBar()
            if (r3 == 0) goto L_0x00a3
            androidx.appcompat.app.ActionBar r3 = r5.getSupportActionBar()
            r3.setDisplayShowTitleEnabled(r0)
            androidx.appcompat.app.ActionBar r3 = r5.getSupportActionBar()
            r3.setLogo((android.graphics.drawable.Drawable) r2)
            androidx.appcompat.app.ActionBar r2 = r5.getSupportActionBar()
            r2.setDisplayHomeAsUpEnabled(r0)
        L_0x00a3:
            com.medscape.android.view.CustomWebView r0 = r5.articleWebView
            r2 = 0
            r0.setShouldChangeActivityTitle(r2)
        L_0x00a9:
            r5.setTitle(r1)
            com.medscape.android.view.CustomWebView r0 = r5.articleWebView
            r0.loadUrl(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.ExternalLinkActivity.onCreate(android.os.Bundle):void");
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (str.startsWith("tel:")) {
                Uri.parse(str);
                ExternalLinkActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("geo")) {
                Uri.parse(str);
                ExternalLinkActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("mail")) {
                Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                ExternalLinkActivity externalLinkActivity = ExternalLinkActivity.this;
                if (Util.isEmailConfigured(externalLinkActivity, intent)) {
                    ExternalLinkActivity.this.startActivity(intent);
                } else if (!externalLinkActivity.isFinishing()) {
                    DialogUtil.showAlertDialog(24, (String) null, externalLinkActivity.getString(R.string.alert_show_email_configure_message), externalLinkActivity).show();
                }
                return true;
            } else if (Util.isOnline(ExternalLinkActivity.this)) {
                return false;
            } else {
                ExternalLinkActivity.this.h.sendEmptyMessage(3);
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            ExternalLinkActivity.this.h.sendEmptyMessage(2);
        }

        public void onPageFinished(WebView webView, String str) {
            ExternalLinkActivity.this.h.sendEmptyMessage(1);
            Util.addZoomControl(webView);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (str2.contains("medscape.com")) {
                webView.stopLoading();
                webView.loadData("", (String) null, (String) null);
                ExternalLinkActivity.this.h.sendEmptyMessage(3);
            }
            super.onReceivedError(webView, i, str, str2);
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i != 16) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.alert_dialog_rss_article_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        return builder.create();
    }

    public void hideCustomeView() {
        if (this.articleWebView.inCustomView()) {
            this.articleWebView.hideCustomView();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (CustomMenu.isShowing()) {
                CustomMenu.hide();
                return true;
            }
            CustomWebView customWebView = this.articleWebView;
            if (customWebView != null && customWebView.inCustomView()) {
                hideCustomeView();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }
}

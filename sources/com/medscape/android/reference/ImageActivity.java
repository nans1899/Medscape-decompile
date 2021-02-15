package com.medscape.android.reference;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;

public class ImageActivity extends AbstractBreadcrumbNavigableActivity {
    protected static final int GET_NEXT_AD = 102;
    protected static final int HIDE_FOOTER = 20;
    protected static final int HIDE_PROGRESS_BAR = 1;
    private static final int START_TIMER = 101;
    private static final String TAG = "ImageActivity";
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                ImageActivity.this.progressBar.setVisibility(8);
            } else if (i == 20) {
                WebView webView = (WebView) ImageActivity.this.findViewById(R.id.webView);
                webView.loadUrl("javascript:document.getElementById('medscapeheader').style.display = 'none';");
                webView.loadUrl("javascript:document.getElementById('medscapefooter').style.display = 'none';");
            }
            return true;
        }
    });
    protected ProgressBar progressBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.generic_webview);
        findViewById(R.id.sectionHeaderTextView).setVisibility(8);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setUserAgentString(Util.addUserAgent(webView, this));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new InsideWebViewClient());
        String string = getIntent().getExtras().getString("htmlContet");
        String string2 = getIntent().getExtras().getString("url");
        setTitle(getIntent().getExtras().getString("header"));
        if (Util.isOnline(this)) {
            webView.loadDataWithBaseURL(string2, string, "text/html", "utf-8", (String) null);
            return;
        }
        this.h.sendEmptyMessage(1);
        if (!isFinishing()) {
            showDialog(5);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Image saved").setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_positive_button_text_OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            return builder.create();
        } else if (i != 5) {
            return onCreateDialog;
        } else {
            return DialogUtil.showAlertDialog(5, "", "Internet connection required to view images.", this);
        }
    }

    final class InsideWebViewClient extends WebViewClient {
        private static final String TAG = "InsideWebViewClient";

        InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return str.startsWith("save") || super.shouldOverrideUrlLoading(webView, str);
        }

        public void onPageFinished(WebView webView, String str) {
            ImageActivity.this.h.sendEmptyMessage(1);
            ImageActivity.this.h.sendEmptyMessage(20);
        }
    }

    public void onPause() {
        super.onPause();
    }
}

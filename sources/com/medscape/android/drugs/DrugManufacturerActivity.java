package com.medscape.android.drugs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;

public class DrugManufacturerActivity extends AbstractBreadcrumbNavigableActivity {
    protected static final int HANDLE_NETWORK_ERROR_ON_PAGE_FINISHED = 1;
    protected static final int HIDE_PROGRESS_BAR = 2;
    protected static final int SHOW_PROGRESS_BAR = 3;
    private static final String TAG = "DrugManufacturerActivity";
    private TextView closeButton;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    DrugManufacturerActivity.this.progressBar.setVisibility(8);
                } else if (i == 3) {
                    DrugManufacturerActivity.this.progressBar.setVisibility(0);
                }
            } else if (!DrugManufacturerActivity.this.isFinishing()) {
                DrugManufacturerActivity.this.showDialog(5);
            }
            return true;
        }
    });
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private String url;
    private WebView webView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.drug_manufacturer_webview);
        WebView webView2 = (WebView) findViewById(R.id.webView);
        this.webView = webView2;
        webView2.getSettings().setUserAgentString(Util.addUserAgent(this.webView, this));
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        this.closeButton = (TextView) findViewById(R.id.closeButton);
        this.webView.setWebViewClient(new InsideWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.url = getIntent().getExtras().getString("url");
        Util.setCookie(this);
        if (Util.isOnline(this)) {
            this.webView.loadUrl(this.url);
        } else {
            Intent intent = new Intent();
            intent.putExtra("url", this.url);
            setResult(1, intent);
            finish();
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.closeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DrugManufacturerActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            Uri parse = Uri.parse(str);
            if (str.startsWith("tel:")) {
                Uri.parse(str);
                DrugManufacturerActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("geo")) {
                Uri.parse(str);
                DrugManufacturerActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("mail")) {
                Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                DrugManufacturerActivity drugManufacturerActivity = DrugManufacturerActivity.this;
                if (Util.isEmailConfigured(drugManufacturerActivity, intent)) {
                    DrugManufacturerActivity.this.startActivity(intent);
                } else if (!drugManufacturerActivity.isFinishing()) {
                    DialogUtil.showAlertDialog(24, (String) null, drugManufacturerActivity.getString(R.string.alert_show_email_configure_message), drugManufacturerActivity).show();
                }
                return true;
            } else if (parse.getScheme().toLowerCase().equals("customurl")) {
                Util.openExternalApp(DrugManufacturerActivity.this, parse);
                return true;
            } else if (str.startsWith("browser") || str.startsWith("browsers")) {
                String str2 = str.split("//")[1];
                if (!str2.startsWith("http://") && !str2.startsWith("https://")) {
                    if (str.contains("browsers")) {
                        str2 = "https://" + str2;
                    } else {
                        str2 = "http://" + str2;
                    }
                }
                DrugManufacturerActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str2)));
                return true;
            } else if (Util.isOnline(DrugManufacturerActivity.this)) {
                return false;
            } else {
                if (!DrugManufacturerActivity.this.isFinishing()) {
                    DrugManufacturerActivity.this.showDialog(5);
                }
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            DrugManufacturerActivity.this.h.sendEmptyMessage(3);
        }

        public void onPageFinished(WebView webView, String str) {
            DrugManufacturerActivity.this.h.sendEmptyMessage(2);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            DrugManufacturerActivity.this.h.sendEmptyMessage(2);
            DrugManufacturerActivity.this.h.sendEmptyMessage(1);
            super.onReceivedError(webView, i, str, str2);
        }
    }

    /* access modifiers changed from: private */
    public void retryDownload() {
        if (Util.isOnline(this)) {
            this.webView.loadUrl(this.url);
            return;
        }
        this.h.sendEmptyMessage(3);
        this.h.sendEmptyMessage(1);
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i != 5) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.alert_dialog_rss_article_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_button_text_close), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                DrugManufacturerActivity.this.finish();
            }
        }).setNegativeButton(getString(R.string.alert_dialog_confirmation_retry_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                DrugManufacturerActivity.this.retryDownload();
            }
        });
        return builder.create();
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.hardKeyboardHidden == 1) {
            setRequestedOrientation(0);
        } else if (configuration.hardKeyboardHidden == 2) {
            setRequestedOrientation(1);
        }
        super.onConfigurationChanged(configuration);
    }

    public void onBackPressed() {
        this.webView.stopLoading();
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            finish();
        }
    }
}

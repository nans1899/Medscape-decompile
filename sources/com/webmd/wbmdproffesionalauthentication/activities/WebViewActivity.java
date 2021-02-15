package com.webmd.wbmdproffesionalauthentication.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.utilities.AuthComponentConstants;

public class WebViewActivity extends AppCompatActivity {
    ErrorDialog errorDialog;
    boolean isTimeout = true;
    ProgressDialog progressDialog;
    WebView webView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.activity_webview);
        setSupportActionBar((Toolbar) findViewById(R.id.web_view_toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ErrorDialog errorDialog2 = new ErrorDialog();
        this.errorDialog = errorDialog2;
        errorDialog2.setErrorMsg(getString(R.string.error_problem_loading_page));
        Intent intent = getIntent();
        String str2 = "";
        if (intent != null) {
            String stringExtra = intent.getStringExtra(AuthComponentConstants.EXTRA_LINK);
            str2 = intent.getStringExtra(AuthComponentConstants.EXTRA_TITLE);
            str = stringExtra;
        } else {
            str = str2;
        }
        getSupportActionBar().setTitle((CharSequence) str2);
        this.webView = (WebView) findViewById(R.id.forgot_password_web_view);
        AnonymousClass1 r1 = new WebViewClient() {
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(12000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (WebViewActivity.this.isTimeout) {
                            WebViewActivity.this.hideLoadingDialog();
                            WebViewActivity.this.errorDialog.show(WebViewActivity.this.getSupportFragmentManager(), "webview error dialog");
                        }
                    }
                }).start();
            }

            public void onPageFinished(WebView webView, String str) {
                WebViewActivity.this.isTimeout = false;
                WebViewActivity.this.hideLoadingDialog();
            }

            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                WebViewActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        WebViewActivity.this.hideLoadingDialog();
                        WebViewActivity.this.isTimeout = false;
                        WebViewActivity.this.errorDialog.show(WebViewActivity.this.getSupportFragmentManager(), "webview error dialog");
                    }
                });
            }
        };
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(r1);
        showLoadingDialog();
        this.webView.loadUrl(str);
    }

    private void showLoadingDialog() {
        this.progressDialog = ProgressDialog.show(this, "", getString(R.string.loading), true);
    }

    /* access modifiers changed from: private */
    public void hideLoadingDialog() {
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 != null && progressDialog2.isShowing()) {
            this.progressDialog.dismiss();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        onBackPressed();
        return super.onOptionsItemSelected(menuItem);
    }
}

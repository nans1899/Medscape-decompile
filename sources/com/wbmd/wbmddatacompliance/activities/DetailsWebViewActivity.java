package com.wbmd.wbmddatacompliance.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.wbmd.wbmddatacompliance.R;
import com.wbmd.wbmddatacompliance.utils.Constants;

public class DetailsWebViewActivity extends AppCompatActivity {
    private static final String TAG = DetailsWebViewActivity.class.getSimpleName();
    /* access modifiers changed from: private */
    public Button mButtonReload;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private String mUrl;
    /* access modifiers changed from: private */
    public View mViewNoNetwork;
    private WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_details_web_view);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mWebView = (WebView) findViewById(R.id.view_web_view);
        this.mViewNoNetwork = findViewById(R.id.view_no_network);
        this.mButtonReload = (Button) findViewById(R.id.button_reload);
        this.mUrl = getIntent().getStringExtra(Constants.EXTRA_URL_KEY);
        setUpActingBar(getIntent().getStringExtra(Constants.EXTRA_TITLE_KEY));
        setUpWebView();
    }

    public void onBackPressed() {
        handleUpOrBackAction();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        handleUpOrBackAction();
        return true;
    }

    private void handleUpOrBackAction() {
        finish();
    }

    private void setUpActingBar(String str) {
        getSupportActionBar().setTitle((CharSequence) str);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /* access modifiers changed from: private */
    public void setUpWebView() {
        String str = this.mUrl;
        if (str == null || str.trim() == "") {
            this.mViewNoNetwork.setVisibility(0);
            this.mButtonReload.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DetailsWebViewActivity.this.setUpWebView();
                    DetailsWebViewActivity.this.mViewNoNetwork.setVisibility(8);
                }
            });
            return;
        }
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setWebViewClient(new SimpleWebViewClient());
        this.mWebView.loadUrl(this.mUrl);
    }

    private class SimpleWebViewClient extends WebViewClient {
        private SimpleWebViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            DetailsWebViewActivity.this.mProgressBar.setVisibility(8);
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            DetailsWebViewActivity.this.mProgressBar.setVisibility(8);
            DetailsWebViewActivity.this.mViewNoNetwork.setVisibility(0);
            DetailsWebViewActivity.this.mButtonReload.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DetailsWebViewActivity.this.setUpWebView();
                    DetailsWebViewActivity.this.mViewNoNetwork.setVisibility(8);
                }
            });
        }
    }
}

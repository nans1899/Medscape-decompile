package com.medscape.android;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import com.medscape.android.base.BaseActivity;

public class PdfWebviewerActivity extends BaseActivity {
    private static final String GOOGLE_DOCS_PDF_VIEWER_BASE_URL = "http://drive.google.com/viewerng/viewer?embedded=true&url=%s";
    private static final String TAG = PdfWebviewerActivity.class.getSimpleName();
    private View mNoNetworkView;
    private String mPdfUrl;
    private ProgressBar mProgressBar;
    private WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_webview);
        this.mWebView = (WebView) findViewById(R.id.web_view);
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mNoNetworkView = findViewById(R.id.no_network_view);
        this.mPdfUrl = getIntent().getStringExtra("pdf_url");
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.loadUrl(String.format(GOOGLE_DOCS_PDF_VIEWER_BASE_URL, new Object[]{this.mPdfUrl}));
    }
}

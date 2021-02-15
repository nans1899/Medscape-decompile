package com.medscape.android.activity.rss;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractContentViewerActvity;
import com.medscape.android.activity.AndroidPdfViewerActivity;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.custom.CustomMenu;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomChromeClient;
import com.wbmd.wbmdcommons.utils.Extensions;

public class RSSExternalArticleActivity extends AbstractContentViewerActvity implements CustomChromeClient.OnPageLoaded {
    public static final String EXTRA_ARTICLE = "article";
    protected static final int HIDE_FOOTER = 2;
    protected static final int HIDE_PROGRESS_BAR = 1;
    protected static final int SHOW_ARTICLE_DOWNLOAD_ERROR_MSG = 3;
    private Article article;
    protected WebView articleWebView;
    private boolean dontShowTitle = false;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                RSSExternalArticleActivity.this.progressBar.setVisibility(8);
            } else if (i == 2) {
                WebView webView = (WebView) RSSExternalArticleActivity.this.findViewById(R.id.articleWebView);
                webView.loadUrl("javascript:document.getElementById('medscapeheader').style.display = 'none';");
                webView.loadUrl("javascript:document.getElementById('medscapefooter').style.display = 'none';");
                webView.loadUrl("javascript:document.getElementById('footerbar').style.display = 'none';");
            }
            return true;
        }
    });
    /* access modifiers changed from: private */
    public CustomChromeClient mFullScreenableChromeClient;
    /* access modifiers changed from: private */
    public View mNoNetworkView;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;

    /* access modifiers changed from: protected */
    public int getMenuItemsLayout() {
        return R.menu.content_share;
    }

    /* access modifiers changed from: protected */
    public boolean isContentTitleDisplayed() {
        return false;
    }

    public void saveContent() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.dontShowTitle = getIntent().getExtras().getBoolean("dontShowTitle");
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null && intent.getBooleanExtra("overrideOrientation", false)) {
            setRequestedOrientation(-1);
        }
        setContentView((int) R.layout.rss_article_w_fullscreen_video);
        this.isMedlineArticle = isMedlineArticle();
        Util.setCookie(this);
        this.article = (Article) getIntent().getParcelableExtra("article");
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        this.mNoNetworkView = findViewById(R.id.noNetworkView);
        WebView webView = (WebView) findViewById(R.id.articleWebView);
        this.articleWebView = webView;
        webView.getSettings().setUserAgentString(Util.addUserAgent(this.articleWebView, this));
        this.articleWebView.getSettings().setJavaScriptEnabled(true);
        this.articleWebView.getSettings().setBuiltInZoomControls(true);
        this.articleWebView.setWebViewClient(new InsideWebViewClient());
        this.articleWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        CustomChromeClient customChromeClient = new CustomChromeClient(this, this);
        this.mFullScreenableChromeClient = customChromeClient;
        this.articleWebView.setWebChromeClient(customChromeClient);
        this.articleWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                Log.i("_TAG", "onDownloadStart: ");
                if (str3.contains(".pdf")) {
                    Intent intent = new Intent(RSSExternalArticleActivity.this, AndroidPdfViewerActivity.class);
                    intent.putExtra("pdf_url", str);
                    RSSExternalArticleActivity.this.startActivity(intent);
                    RSSExternalArticleActivity.this.finish();
                }
            }
        });
        loadUrl();
        findViewById(R.id.ad).setVisibility(8);
        saveToRecentlyViewed();
    }

    /* access modifiers changed from: private */
    public void loadUrl() {
        if (!Util.isOnline(getApplicationContext())) {
            this.progressBar.setVisibility(8);
            ((Button) this.mNoNetworkView.findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    RSSExternalArticleActivity.this.mNoNetworkView.setVisibility(8);
                    RSSExternalArticleActivity.this.progressBar.setVisibility(0);
                    RSSExternalArticleActivity.this.loadUrl();
                }
            });
            this.mNoNetworkView.setVisibility(0);
            return;
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("url")) {
            this.articleWebView.loadUrl(extras.getString("url"));
        }
    }

    private void saveToRecentlyViewed() {
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("article")) {
            Bundle bundle = new Bundle(extras);
            bundle.putString("type", "news");
            RecentlyViewedSuggestionHelper.addToRecentlyViewed(this, ((Article) extras.getParcelable("article")).mTitle, bundle);
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i == 16) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.alert_dialog_rss_article_download_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            return builder.create();
        } else if (i == 23) {
            return DialogUtil.showAlertDialog(23, "", getResources().getString(R.string.alert_dialog_save_news_article), this);
        } else {
            if (i != 333) {
                return null;
            }
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setMessage("PDF viewing is not supported").setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_button_text_close), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            return builder2.create();
        }
    }

    public void onPageLoaded() {
        this.progressBar.setVisibility(8);
    }

    public void onTimeOut() {
        this.progressBar.setVisibility(8);
        ((Button) this.mNoNetworkView.findViewById(R.id.noNetworkBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RSSExternalArticleActivity.this.mNoNetworkView.setVisibility(8);
                RSSExternalArticleActivity.this.progressBar.setVisibility(0);
                RSSExternalArticleActivity.this.loadUrl();
            }
        });
        this.mNoNetworkView.setVisibility(0);
    }

    protected class InsideWebViewClient extends WebViewClient {
        protected InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (str.startsWith("tel:")) {
                Uri.parse(str);
                RSSExternalArticleActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("geo")) {
                Uri.parse(str);
                RSSExternalArticleActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("mail")) {
                Uri.parse(str);
                RSSExternalArticleActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (!str.endsWith(".pdf")) {
                return false;
            } else {
                Uri parse = Uri.parse(str);
                String queryParameter = parse.getQueryParameter("url");
                if (Extensions.IsNullOrEmpty(queryParameter)) {
                    RSSExternalArticleActivity.this.startActivity(new Intent("android.intent.action.VIEW", parse));
                } else {
                    Intent intent = new Intent(RSSExternalArticleActivity.this, AndroidPdfViewerActivity.class);
                    intent.putExtra("pdf_url", queryParameter);
                    RSSExternalArticleActivity.this.startActivity(intent);
                }
                WebView webView2 = (WebView) RSSExternalArticleActivity.this.findViewById(R.id.articleWebView);
                if (webView2 != null) {
                    if (webView2.canGoBack()) {
                        webView2.goBack();
                    } else {
                        RSSExternalArticleActivity.this.finish();
                    }
                }
                return true;
            }
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (RSSExternalArticleActivity.this.mFullScreenableChromeClient != null) {
                RSSExternalArticleActivity.this.mFullScreenableChromeClient.startTimer();
            }
        }

        public void onPageFinished(WebView webView, String str) {
            RSSExternalArticleActivity.this.h.sendEmptyMessage(1);
            RSSExternalArticleActivity.this.h.sendEmptyMessage(2);
            Util.addZoomControl(webView);
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            if (str2.contains("medscape.com")) {
                webView.stopLoading();
                webView.loadData("", (String) null, (String) null);
                RSSExternalArticleActivity.this.h.sendEmptyMessage(3);
            }
            super.onReceivedError(webView, i, str, str2);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && i != 3) {
            return false;
        }
        if (CustomMenu.isShowing()) {
            CustomMenu.hide();
            return true;
        } else if (this.articleWebView == null || !this.mFullScreenableChromeClient.inCustomView()) {
            WebView webView = (WebView) findViewById(R.id.articleWebView);
            if (webView == null) {
                return false;
            }
            if (webView.canGoBack()) {
                webView.goBack();
                return false;
            }
            finish();
            return false;
        } else {
            hideCustomeView();
            return true;
        }
    }

    public void hideCustomeView() {
        if (this.mFullScreenableChromeClient.inCustomView()) {
            this.mFullScreenableChromeClient.onHideCustomView();
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (isPromoContent()) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon((int) R.drawable.bttn_action_bar_transparent);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getActionBarToolBar().setNavigationIcon((int) R.drawable.ic_action_previous_item_blue);
        } else if (!isMedlineArticle()) {
            Drawable drawable = ContextCompat.getDrawable(this, R.drawable.news_logo);
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.white));
            getSupportActionBar().setLogo(drawable);
            getSupportActionBar().setIcon((int) R.drawable.news_logo);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Article article2 = this.article;
        if (article2 == null || !article2.mShareable) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isMedlineArticle() {
        Bundle extras = getIntent().getExtras();
        return (extras == null || extras.getString("url") == null || !extras.getString("url").contains(RecentlyViewedSuggestionHelper.TYPE_MEDLINE)) ? false : true;
    }

    private boolean isPromoContent() {
        Bundle extras = getIntent().getExtras();
        return (extras == null || extras.getString("contentType") == null || !extras.getString("contentType").contains("promo")) ? false : true;
    }

    public String getContentLink() {
        Article article2 = this.article;
        if (article2 == null || article2.mLink == null) {
            return MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        if (this.article.mLink.startsWith("http://")) {
            return this.article.mLink;
        }
        return "http://" + this.article.mLink;
    }

    public String getContentTitle() {
        Article article2 = this.article;
        return (article2 == null || article2.mTitle == null) ? MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR : this.article.mTitle.trim().replaceAll("\\<.*?>", "");
    }

    /* access modifiers changed from: protected */
    public String getContentTeaserForEmail() {
        Article article2 = this.article;
        return (article2 == null || article2.mDescription == null) ? MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR : this.article.mDescription.replaceAll("\\<.*?>", "");
    }

    public void onPause() {
        CustomChromeClient customChromeClient;
        super.onPause();
        if (this.articleWebView != null && (customChromeClient = this.mFullScreenableChromeClient) != null && customChromeClient.inCustomView()) {
            hideCustomeView();
        }
    }

    public void onResume() {
        super.onResume();
        WebView webView = this.articleWebView;
        if (webView != null) {
            webView.onResume();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        WebView webView = this.articleWebView;
        if (webView == null || !webView.canGoBack()) {
            super.onBackPressed();
        } else {
            this.articleWebView.goBack();
        }
    }
}

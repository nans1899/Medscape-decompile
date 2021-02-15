package com.medscape.android.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

public class AdBlockerWebViewAcitivity extends BaseActivity {
    private static final String AD_BLOCKER_LOCAL_URL = "file:///android_asset/adblocker.html";
    private static final String AD_BLOCKER_SERVER_URL = "https://img.medscape.com/pi/iphone/medscapeapp/adblocker.html";
    private static final String TAG = "AdBlockerWebViewAcitivity";
    private String header = "";
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    /* access modifiers changed from: private */
    public WebView webView;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.generic_webview);
        findViewById(R.id.sectionHeaderTextView).setVisibility(8);
        findViewById(R.id.ad).setVisibility(8);
        String stringExtra = getIntent().getStringExtra("header");
        this.header = stringExtra;
        if (stringExtra != null) {
            setTitle(stringExtra);
        } else {
            setTitle("");
        }
        Util.setCookie(this);
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progress);
        this.progressBar = progressBar2;
        progressBar2.setVisibility(0);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        WebView webView2 = (WebView) findViewById(R.id.webView);
        this.webView = webView2;
        webView2.getSettings().setUserAgentString(Util.addUserAgent(this.webView, this));
        this.webView.getSettings().setBuiltInZoomControls(true);
        this.webView.setScrollBarStyle(33554432);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebViewClient(new InsideWebViewClient());
        if (!Util.isOnline(this)) {
            this.webView.loadUrl(AD_BLOCKER_LOCAL_URL);
        } else {
            new ArticleDownloadTask(getApplicationContext()).execute(new String[]{AD_BLOCKER_SERVER_URL});
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null || i != 5) {
            return onCreateDialog;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.alert_dialog_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                AdBlockerWebViewAcitivity.this.finish();
            }
        });
        return builder.create();
    }

    public void onResume() {
        super.onResume();
    }

    final class InsideWebViewClient extends WebViewClient {
        public void onReceivedError(WebView webView, int i, String str, String str2) {
        }

        InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (str == null) {
                return false;
            }
            if (str.startsWith("tel:")) {
                Uri.parse(str);
                AdBlockerWebViewAcitivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.startsWith("geo")) {
                Uri.parse(str);
                AdBlockerWebViewAcitivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (!str.startsWith("mail")) {
                return false;
            } else {
                Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                AdBlockerWebViewAcitivity adBlockerWebViewAcitivity = AdBlockerWebViewAcitivity.this;
                if (Util.isEmailConfigured(adBlockerWebViewAcitivity, intent)) {
                    AdBlockerWebViewAcitivity.this.startActivity(intent);
                } else if (!adBlockerWebViewAcitivity.isFinishing()) {
                    DialogUtil.showAlertDialog(24, (String) null, adBlockerWebViewAcitivity.getString(R.string.alert_show_email_configure_message), adBlockerWebViewAcitivity).show();
                }
                return true;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            AdBlockerWebViewAcitivity.this.progressBar.setVisibility(8);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            AdBlockerWebViewAcitivity.this.progressBar.setVisibility(0);
        }
    }

    public class ArticleDownloadTask extends AsyncTask<String, String, String> {
        private Context mContext;

        public ArticleDownloadTask(Context context) {
            this.mContext = context;
        }

        /* access modifiers changed from: protected */
        public String doInBackground(String... strArr) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strArr[0] + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(strArr[0])).openConnection();
                httpURLConnection.setReadTimeout(Util.TIMEOUT);
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                        inputStream.close();
                        return byteArrayOutputStream2;
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (SocketException e2) {
                e2.printStackTrace();
                return null;
            } catch (SocketTimeoutException e3) {
                e3.printStackTrace();
                return null;
            } catch (UnknownHostException e4) {
                e4.printStackTrace();
                return null;
            } catch (Exception e5) {
                e5.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            if (str == null) {
                AdBlockerWebViewAcitivity.this.webView.loadUrl(AdBlockerWebViewAcitivity.AD_BLOCKER_LOCAL_URL);
            } else {
                AdBlockerWebViewAcitivity.this.webView.loadUrl(AdBlockerWebViewAcitivity.AD_BLOCKER_SERVER_URL);
            }
        }
    }
}

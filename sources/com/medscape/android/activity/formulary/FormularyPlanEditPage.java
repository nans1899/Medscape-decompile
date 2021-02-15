package com.medscape.android.activity.formulary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import java.util.Map;

public class FormularyPlanEditPage extends AbstractBreadcrumbNavigableActivity {
    private static final String SAVE_USER_PLAN_URI = "https://api.medscape.com/ws/services/formularyService/saveUserPlan";
    private static final int SHOW_FORMULARY_NETWORK_ERROR_DIALOG = 14;
    private static final String TAG = "FormularyDetailPage";
    private String mBrandId;
    private String mFileName = "formulary-2.html";
    private String mRemoteUrl = "https://reference.medscape.com/features/mobileutils/formulary-plan?";
    /* access modifiers changed from: private */
    public WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.formulary_webview_page);
        Bundle extras = getIntent().getExtras();
        String str = "";
        if (extras != null) {
            String string = extras.containsKey("TITLE") ? extras.getString("TITLE") : str;
            if (extras.containsKey(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY)) {
                str = extras.getString(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY);
            }
            this.mBrandId = str;
            str = string;
        }
        setTitle(str);
        Util.setCookie(this);
        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebView = webView;
        webView.getSettings().setUserAgentString(Util.addUserAgent(this.mWebView, this));
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.setScrollBarStyle(33554432);
        this.mWebView.getSettings().setCacheMode(2);
        if (Build.VERSION.SDK_INT < 19) {
            this.mWebView.addJavascriptInterface(new JsInterface(), "MedscapeAndroid");
        }
        this.mWebView.setWebViewClient(new InsideWebViewClient());
        getUrlByPlanId();
        this.mWebView.loadUrl(createRemoteUrl());
        sendBIPing();
    }

    /* access modifiers changed from: private */
    public void showMaxPlanLimitSnackBar() {
        new MedscapeException("You have reached the maximum number (20) of plans you can include").showSnackBar(this.mWebView, -1);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void getUrlByPlanId() {
        /*
            r7 = this;
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            java.lang.String r1 = "module_service"
            java.lang.String r0 = r0.getEnvironmentWithDefault(r7, r1)
            int r1 = r0.hashCode()
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            switch(r1) {
                case 206969445: goto L_0x004a;
                case 446124970: goto L_0x0040;
                case 568937877: goto L_0x0036;
                case 568961724: goto L_0x002c;
                case 568961725: goto L_0x0022;
                case 568961726: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0054
        L_0x0018:
            java.lang.String r1 = "environment_qa02"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0054
            r0 = 4
            goto L_0x0055
        L_0x0022:
            java.lang.String r1 = "environment_qa01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0054
            r0 = 2
            goto L_0x0055
        L_0x002c:
            java.lang.String r1 = "environment_qa00"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0054
            r0 = 1
            goto L_0x0055
        L_0x0036:
            java.lang.String r1 = "environment_perf"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0054
            r0 = 3
            goto L_0x0055
        L_0x0040:
            java.lang.String r1 = "environment_dev01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0054
            r0 = 5
            goto L_0x0055
        L_0x004a:
            java.lang.String r1 = "environment_production"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0054
            r0 = 0
            goto L_0x0055
        L_0x0054:
            r0 = -1
        L_0x0055:
            if (r0 == 0) goto L_0x007b
            if (r0 == r6) goto L_0x0076
            if (r0 == r5) goto L_0x0071
            if (r0 == r4) goto L_0x006c
            if (r0 == r3) goto L_0x0067
            if (r0 == r2) goto L_0x0062
            goto L_0x007f
        L_0x0062:
            java.lang.String r0 = "http://reference.dev01.medscape.com/features/mobileutils/formulary-plan?"
            r7.mRemoteUrl = r0
            goto L_0x007f
        L_0x0067:
            java.lang.String r0 = "http://reference.qa02.medscape.com/features/mobileutils/formulary-plan?"
            r7.mRemoteUrl = r0
            goto L_0x007f
        L_0x006c:
            java.lang.String r0 = "http://reference.perf.medscape.com/features/mobileutils/formulary-plan?"
            r7.mRemoteUrl = r0
            goto L_0x007f
        L_0x0071:
            java.lang.String r0 = "http://reference.qa01.medscape.com/features/mobileutils/formulary-plan?"
            r7.mRemoteUrl = r0
            goto L_0x007f
        L_0x0076:
            java.lang.String r0 = "http://reference.qa00.medscape.com/features/mobileutils/formulary-plan?"
            r7.mRemoteUrl = r0
            goto L_0x007f
        L_0x007b:
            java.lang.String r0 = "https://reference.medscape.com/features/mobileutils/formulary-plan?"
            r7.mRemoteUrl = r0
        L_0x007f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyPlanEditPage.getUrlByPlanId():void");
    }

    private String createRemoteUrl() {
        return (this.mRemoteUrl + "state=" + Settings.singleton(this).getSetting(Constants.USER_STATE_ID, "")) + "&userId=" + Settings.singleton(this).getSetting(Constants.REGISTERED_ID, "");
    }

    public void sendBIPing() {
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "formulary", "view", "" + this.mBrandId, "planselect", (Map<String, Object>) null);
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (!str.contains(FormularyPlanEditPage.SAVE_USER_PLAN_URI)) {
                return null;
            }
            checkPlanLimit();
            return null;
        }

        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (Build.VERSION.SDK_INT < 21 || !webResourceRequest.getUrl().toString().contains(FormularyPlanEditPage.SAVE_USER_PLAN_URI)) {
                return null;
            }
            checkPlanLimit();
            return null;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri.parse(str);
            return false;
        }

        private void checkPlanLimit() {
            FormularyPlanEditPage.this.mWebView.postDelayed(new Runnable() {
                public void run() {
                    if (Build.VERSION.SDK_INT >= 19) {
                        FormularyPlanEditPage.this.mWebView.evaluateJavascript("javascript:getIsMaxedPlansReached();", new ValueCallback<String>() {
                            public void onReceiveValue(String str) {
                                if (str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                                    FormularyPlanEditPage.this.showMaxPlanLimitSnackBar();
                                }
                            }
                        });
                    } else {
                        FormularyPlanEditPage.this.mWebView.loadUrl("javascript:getIsMaxedPlansReached();");
                    }
                }
            }, 200);
        }
    }

    public class JsInterface {
        public JsInterface() {
        }

        @JavascriptInterface
        public void onGetMaxPlansReached(String str) {
            if (str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                FormularyPlanEditPage.this.runOnUiThread(new Runnable() {
                    public void run() {
                        FormularyPlanEditPage.this.showMaxPlanLimitSnackBar();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null || i != 14) {
            return onCreateDialog;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error").setMessage("You must be connected to the Internet in order to setup Formulary").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_save) {
            return super.onOptionsItemSelected(menuItem);
        }
        Settings.singleton(this).saveSetting(Constants.PREF_FORMULARY_VISITED, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        finish();
        return false;
    }
}

package com.medscape.android.activity.formulary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import com.wbmd.wbmdcommons.utils.Extensions;
import java.util.Map;

public class FormularyMyPlanPage extends AbstractBreadcrumbNavigableActivity {
    public static final String CLASS_ID = "CLASS_ID";
    public static final String CONTENT_ID = "CONTENT_ID";
    public static final String FORMULARY_BRAND_ID_KEY = "BRAND_ID";
    private static final String FORMULARY_DATABASE_FILE_NAME = "formulary.zip";
    private static final int FORMULARY_EDIT_PAGE_REQUEST_CODE = 1;
    private static String FORMULARY_SERVICE_URL = "https://api.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?";
    public static final String FORMULARY_TITLE_KEY = "TITLE";
    public static final String IS_SINGLE_CLASS = "IS_SINGLE_CLASS";
    public static final String PLAN_ID = "PLAN_ID";
    public static final String PLAN_NAME = "PLAN_NAME";
    private static final int SHOW_FORMULARY_NETWORK_ERROR_DIALOG = 1;
    private static final String TAG = "FormularyMyPlanPage";
    /* access modifiers changed from: private */
    public String mBrandId;
    private int mBrandModelsCount;
    private String mContentId;
    private String mDrugName;
    private MedscapeException mException;
    private final String mFileName = "formulary_main.html";
    private String mTitle;
    /* access modifiers changed from: private */
    public WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.formulary_webview_page);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String str = "";
            this.mTitle = extras.containsKey("TITLE") ? extras.getString("TITLE") : str;
            this.mBrandId = extras.containsKey(FORMULARY_BRAND_ID_KEY) ? extras.getString(FORMULARY_BRAND_ID_KEY) : str;
            if (extras.containsKey(CONTENT_ID)) {
                str = extras.getString(CONTENT_ID);
            }
            this.mContentId = str;
            this.mDrugName = extras.getString("drugName");
        }
        this.mBrandModelsCount = getIntent().getIntExtra("brand_model_size", -1);
        setTitle(this.mTitle);
        this.mException = new MedscapeException(getResources().getString(R.string.internet_required));
        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebView = webView;
        webView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setUserAgentString(Util.addUserAgent(this.mWebView, this));
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.setScrollBarStyle(33554432);
        this.mWebView.setWebViewClient(new InsideWebViewClient());
        WebView webView2 = this.mWebView;
        webView2.loadUrl("file://" + FileHelper.getDataDirectory(this) + "/Medscape/" + "formulary_main.html");
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent intent;
        if (this.mBrandModelsCount == 1) {
            if (Extensions.IsNullOrEmpty(this.mDrugName)) {
                intent = new Intent(this, IndexedDrugFormularyListActivity.class);
            } else {
                intent = new Intent(this, DrugMonographMainActivity.class);
                intent.putExtra("drugName", this.mDrugName);
                intent.putExtra(Constants.EXTRA_CONTENT_ID, Integer.parseInt(this.mContentId));
            }
            intent.addFlags(67108864);
            intent.addFlags(268435456);
            startActivity(intent);
        } else {
            finish();
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        return true;
    }

    public void onBackPressed() {
        Intent intent;
        if (this.mBrandModelsCount == 1) {
            if (Extensions.IsNullOrEmpty(this.mDrugName)) {
                intent = new Intent(this, IndexedDrugFormularyListActivity.class);
            } else {
                intent = new Intent(this, DrugMonographMainActivity.class);
                intent.putExtra("drugName", this.mDrugName);
                intent.putExtra(Constants.EXTRA_CONTENT_ID, Integer.parseInt(this.mContentId));
            }
            intent.addFlags(67108864);
            intent.addFlags(268435456);
            startActivity(intent);
        } else {
            super.onBackPressed();
            finish();
        }
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFormularyURL() {
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
            java.lang.String r0 = "http://api.dev01.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            FORMULARY_SERVICE_URL = r0
            goto L_0x007f
        L_0x0067:
            java.lang.String r0 = "http://api.qa02.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            FORMULARY_SERVICE_URL = r0
            goto L_0x007f
        L_0x006c:
            java.lang.String r0 = "http://api.perf.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            FORMULARY_SERVICE_URL = r0
            goto L_0x007f
        L_0x0071:
            java.lang.String r0 = "http://api.qa01.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            FORMULARY_SERVICE_URL = r0
            goto L_0x007f
        L_0x0076:
            java.lang.String r0 = "http://api.qa00.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            FORMULARY_SERVICE_URL = r0
            goto L_0x007f
        L_0x007b:
            java.lang.String r0 = "https://api.medscape.com/ws/services/formularyMobileService/getFormulariesByUser?"
            FORMULARY_SERVICE_URL = r0
        L_0x007f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyMyPlanPage.setFormularyURL():void");
    }

    /* access modifiers changed from: private */
    public void loadPlans() {
        if (!Util.isOnline(getApplicationContext())) {
            this.mWebView.setVisibility(4);
            Util.hideKeyboard(this);
            this.mException.showSnackBar(this.mWebView, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
                public final void onClick(View view) {
                    FormularyMyPlanPage.this.lambda$loadPlans$0$FormularyMyPlanPage(view);
                }
            });
            return;
        }
        boolean z = false;
        this.mWebView.setVisibility(0);
        if (!FormularyDatabaseHelper.getInstance(this).isDatabaseExists()) {
            this.mWebView.loadUrl("javascript:creatPlan()");
        } else if (FormularyDatabaseHelper.getInstance(this).getUserPlans(this) > 0) {
            this.mWebView.loadUrl("javascript:showLoading();");
            new FormularyDatabaseService().execute(new Void[0]);
            sendBIPing(z);
        } else {
            this.mWebView.loadUrl("javascript:creatPlan()");
        }
        z = true;
        sendBIPing(z);
    }

    public /* synthetic */ void lambda$loadPlans$0$FormularyMyPlanPage(View view) {
        loadPlans();
        this.mException.dismissSnackBar();
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if (parse.getScheme().toLowerCase().contains("formularyinstall")) {
                FormularyMyPlanPage.this.openFormularyEditPage();
                return true;
            } else if (parse.getScheme().toLowerCase().contains("compare")) {
                FormularyMyPlanPage.this.openFormularyComparePage(parse.getQueryParameter("planId"), parse.getQueryParameter("planName"));
                return true;
            } else if (!parse.getScheme().toLowerCase().contains("tierinfo")) {
                return false;
            } else {
                FormularyMyPlanPage.this.openTierInfo();
                return true;
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (str.equalsIgnoreCase("file://" + FileHelper.getDataDirectory(FormularyMyPlanPage.this) + "/Medscape/" + "formulary_main.html")) {
                FormularyMyPlanPage.this.loadPlans();
            }
            super.onPageFinished(webView, str);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            executeFormularyDownloadService();
        }
    }

    /* access modifiers changed from: private */
    public void executeFormularyDownloadService() {
        if (Util.isOnline(this)) {
            new FormularyDownloadServicemain().execute(new String[]{FORMULARY_SERVICE_URL});
            return;
        }
        new MedscapeException(getResources().getString(R.string.internet_required)).showSnackBar(this.mWebView, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
            public void onClick(View view) {
                FormularyMyPlanPage.this.executeFormularyDownloadService();
            }
        });
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null || i != 1) {
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

    public void sendBIPing(boolean z) {
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "formulary", "view", "" + this.mBrandId, z ? "setup" : "myplans", (Map<String, Object>) null);
    }

    /* access modifiers changed from: private */
    public void openFormularyEditPage() {
        if (Util.isOnline(this)) {
            Intent intent = new Intent(this, FormularyPlanEditPage.class);
            intent.putExtra("TITLE", this.mTitle);
            intent.putExtra(FORMULARY_BRAND_ID_KEY, this.mBrandId);
            startActivityForResult(intent, 1);
            return;
        }
        new MedscapeException(getResources().getString(R.string.internet_required)).showSnackBar(this.mWebView, -2, getResources().getString(R.string.retry), new View.OnClickListener() {
            public void onClick(View view) {
                FormularyMyPlanPage.this.openFormularyEditPage();
            }
        });
    }

    /* access modifiers changed from: private */
    public void openFormularyComparePage(String str, String str2) {
        Intent intent = new Intent(this, FormularyComparePage.class);
        intent.putExtra("TITLE", this.mTitle);
        intent.putExtra(CONTENT_ID, this.mContentId);
        intent.putExtra(PLAN_ID, str);
        intent.putExtra(FORMULARY_BRAND_ID_KEY, this.mBrandId);
        intent.putExtra(PLAN_NAME, str2);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void openTierInfo() {
        Intent intent = new Intent(this, TierDescription.class);
        intent.putExtra("TITLE", "Formulary");
        intent.putExtra(FORMULARY_BRAND_ID_KEY, this.mBrandId);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setFormularyURL();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    private class FormularyDownloadServicemain extends AsyncTask<String, Void, Integer> {
        private static final int CONNECTION_ERROR = 2;
        private static final int FAIL = 0;
        private static final int SUCCESS = 1;

        private FormularyDownloadServicemain() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            FormularyMyPlanPage.this.mWebView.loadUrl("javascript:showLoading();");
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(String... strArr) {
            return Integer.valueOf(FormularyDownloadService.downloadZip(FormularyMyPlanPage.this, "", strArr[0]));
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            int intValue = num.intValue();
            if (intValue == 1) {
                FormularyMyPlanPage.this.formularyDownloadComplate();
            } else if (intValue != 2) {
                FormularyMyPlanPage.this.mWebView.loadUrl("javascript:hideLoading();");
            } else {
                FormularyMyPlanPage.this.showDialog(1);
                FormularyMyPlanPage.this.formularyDownloadComplate();
            }
        }
    }

    /* access modifiers changed from: private */
    public void formularyDownloadComplate() {
        if (FormularyDatabaseHelper.getInstance(this).getUserPlans(this) > 0) {
            this.mWebView.loadUrl("javascript:showLoading();");
            new FormularyDatabaseService().execute(new Void[0]);
            sendBIPing(false);
            return;
        }
        this.mWebView.loadUrl("javascript:creatPlan()");
        sendBIPing(true);
    }

    private class FormularyDatabaseService extends AsyncTask<Void, Void, String> {
        private FormularyDatabaseService() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            FormularyMyPlanPage.this.mWebView.loadUrl("javascript:showLoading();");
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e5, code lost:
            if (r4 == null) goto L_0x00ea;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.Void... r12) {
            /*
                r11 = this;
                java.lang.String r12 = "restrictionCodes"
                java.lang.String r0 = "tierName"
                java.lang.String r1 = "stateAbbreviation"
                java.lang.String r2 = "planName"
                com.medscape.android.activity.formulary.FormularyMyPlanPage r3 = com.medscape.android.activity.formulary.FormularyMyPlanPage.this
                com.medscape.android.activity.formulary.FormularyDatabaseHelper r3 = com.medscape.android.activity.formulary.FormularyDatabaseHelper.getInstance(r3)
                android.database.sqlite.SQLiteDatabase r3 = r3.getDatabase()
                if (r3 == 0) goto L_0x00f4
                r4 = 0
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r5.<init>()     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                org.json.JSONArray r6 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r6.<init>()     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r7 = "SELECT P.planName, P.planId, P.isMedicare, P.stateAbbreviation, F.tierName, F.restrictionCodes FROM tblFormulary F JOIN tblUserPlan P ON F.planId = P.planId WHERE F.brandId = ?"
                r8 = 1
                java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r9 = 0
                com.medscape.android.activity.formulary.FormularyMyPlanPage r10 = com.medscape.android.activity.formulary.FormularyMyPlanPage.this     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r10 = r10.mBrandId     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r8[r9] = r10     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                android.database.Cursor r4 = r3.rawQuery(r7, r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                if (r4 == 0) goto L_0x00b2
            L_0x0037:
                boolean r7 = r4.moveToNext()     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                if (r7 == 0) goto L_0x00b2
                org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r7.<init>()     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r8 = "healthPlanName"
                int r9 = r4.getColumnIndex(r2)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r9 = r4.getString(r9)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r7.put(r8, r9)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r8 = "healthPlanId"
                java.lang.String r9 = "planId"
                int r9 = r4.getColumnIndex(r9)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r9 = r4.getString(r9)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r7.put(r8, r9)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                int r8 = r4.getColumnIndex(r1)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r8 = r4.getString(r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r7.put(r1, r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                int r8 = r4.getColumnIndex(r2)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r8 = r4.getString(r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r9 = "Y"
                boolean r8 = r8.equals(r9)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r9 = "healthPlanType"
                if (r8 == 0) goto L_0x0081
                java.lang.String r8 = "medicare"
                r7.put(r9, r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                goto L_0x0086
            L_0x0081:
                java.lang.String r8 = "non-medicare"
                r7.put(r9, r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
            L_0x0086:
                int r8 = r4.getColumnIndex(r0)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r8 = r4.getString(r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r7.put(r0, r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                int r8 = r4.getColumnIndex(r12)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r8 = r4.getString(r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r9 = "restrictionCode"
                if (r8 == 0) goto L_0x00a9
                int r8 = r4.getColumnIndex(r12)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r8 = r4.getString(r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r7.put(r9, r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                goto L_0x00ae
            L_0x00a9:
                java.lang.String r8 = ""
                r7.put(r9, r8)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
            L_0x00ae:
                r6.put(r7)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                goto L_0x0037
            L_0x00b2:
                java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r12.<init>()     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r0 = "parseFormulary("
                r12.append(r0)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r0 = "formularyInfo"
                org.json.JSONObject r0 = r5.put(r0, r6)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                r12.append(r0)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r0 = ");"
                r12.append(r0)     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                java.lang.String r12 = r12.toString()     // Catch:{ JSONException -> 0x00e1, Exception -> 0x00da }
                if (r4 == 0) goto L_0x00d7
                r4.close()
            L_0x00d7:
                return r12
            L_0x00d8:
                r12 = move-exception
                goto L_0x00ee
            L_0x00da:
                r12 = move-exception
                r12.printStackTrace()     // Catch:{ all -> 0x00d8 }
                if (r4 == 0) goto L_0x00ea
                goto L_0x00e7
            L_0x00e1:
                r12 = move-exception
                r12.printStackTrace()     // Catch:{ all -> 0x00d8 }
                if (r4 == 0) goto L_0x00ea
            L_0x00e7:
                r4.close()
            L_0x00ea:
                r3.close()
                goto L_0x00f4
            L_0x00ee:
                if (r4 == 0) goto L_0x00f3
                r4.close()
            L_0x00f3:
                throw r12
            L_0x00f4:
                java.lang.String r12 = "parseFormulary ({formularyInfo:[ ]});"
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyMyPlanPage.FormularyDatabaseService.doInBackground(java.lang.Void[]):java.lang.String");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str != null) {
                WebView access$800 = FormularyMyPlanPage.this.mWebView;
                access$800.loadUrl(InAppMessageWebViewClient.JAVASCRIPT_PREFIX + str);
            }
            FormularyMyPlanPage.this.mWebView.loadUrl("javascript:hideLoading();");
        }
    }
}

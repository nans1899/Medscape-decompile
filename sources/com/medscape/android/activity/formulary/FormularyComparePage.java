package com.medscape.android.activity.formulary;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appboy.ui.inappmessage.InAppMessageWebViewClient;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.DrugDataHelper;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.helper.FileHelper;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.Util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FormularyComparePage extends AbstractBreadcrumbNavigableActivity {
    private static final String TAG = "FormularyComparePage";
    private boolean isSingleClass;
    /* access modifiers changed from: private */
    public String mBrandId;
    private String mClassId;
    /* access modifiers changed from: private */
    public String mContentId;
    private String mFileName = "formulary_compare.html";
    /* access modifiers changed from: private */
    public String mJavaScriptCall;
    /* access modifiers changed from: private */
    public String mPlanId;
    /* access modifiers changed from: private */
    public String mPlanName;
    /* access modifiers changed from: private */
    public String mTitle = "";
    /* access modifiers changed from: private */
    public WebView mWebView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        super.onCreate(bundle);
        setContentView((int) R.layout.formulary_webview_page);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("TITLE")) {
                str2 = extras.getString("TITLE");
            } else {
                str2 = "";
            }
            this.mTitle = str2;
            if (extras.containsKey(FormularyMyPlanPage.CONTENT_ID)) {
                str3 = extras.getString(FormularyMyPlanPage.CONTENT_ID);
            } else {
                str3 = "";
            }
            this.mContentId = str3;
            if (extras.containsKey(FormularyMyPlanPage.PLAN_NAME)) {
                str4 = extras.getString(FormularyMyPlanPage.PLAN_NAME);
            } else {
                str4 = "";
            }
            this.mPlanName = str4;
            if (extras.containsKey(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY)) {
                str5 = extras.getString(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY);
            } else {
                str5 = "";
            }
            this.mBrandId = str5;
            if (extras.containsKey(FormularyMyPlanPage.PLAN_ID)) {
                str6 = extras.getString(FormularyMyPlanPage.PLAN_ID);
            } else {
                str6 = "";
            }
            this.mPlanId = str6;
            boolean z = extras.containsKey(FormularyMyPlanPage.IS_SINGLE_CLASS) && extras.getBoolean(FormularyMyPlanPage.IS_SINGLE_CLASS);
            this.isSingleClass = z;
            if (z) {
                if (extras.containsKey(FormularyMyPlanPage.CLASS_ID)) {
                    str7 = extras.getString(FormularyMyPlanPage.CLASS_ID);
                } else {
                    str7 = "";
                }
                this.mClassId = str7;
            }
        }
        setTitle(this.mTitle);
        WebView webView = (WebView) findViewById(R.id.webview);
        this.mWebView = webView;
        webView.getSettings().setUserAgentString(Util.addUserAgent(this.mWebView, this));
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setBuiltInZoomControls(true);
        this.mWebView.setScrollBarStyle(33554432);
        this.mWebView.setWebViewClient(new InsideWebViewClient());
        WebView webView2 = this.mWebView;
        webView2.loadUrl("file://" + FileHelper.getDataDirectory(MedscapeApplication.get()) + "/Medscape/" + this.mFileName);
        if (!this.isSingleClass) {
            str = getFormuarlyClass(this.mContentId, this.mPlanName, this.mPlanId);
        } else {
            str = parseClassFormulary(this.mClassId, "", this.mPlanName, this.mPlanId);
        }
        this.mJavaScriptCall = str;
    }

    public void sendBIPing() {
        LogUtil.e(TAG, "sendBIPing() compare Brand id = %s ", this.mBrandId);
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "formulary", "view", "" + this.mBrandId, "compare", (Map<String, Object>) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00ab, code lost:
        if (r1 != null) goto L_0x00b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00b4, code lost:
        if (r1 == null) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b6, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b9, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00be, code lost:
        return "";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getFormuarlyClass(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            com.medscape.android.db.DatabaseHelper r0 = new com.medscape.android.db.DatabaseHelper
            android.content.Context r1 = r7.getApplicationContext()
            r0.<init>(r1)
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r0.getDatabase()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r3 = "SELECT N.ClassID as classId, N.ClassName as className FROM tblClassMapping M, tblClassNames N WHERE M.ClassID = N.ClassID AND (N.ParentID IS NOT NULL OR N.SingleLevel = 1) AND ContentID = ?"
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00b0 }
            r6 = 0
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x00b0 }
            r5[r6] = r8     // Catch:{ Exception -> 0x00b0 }
            android.database.Cursor r1 = r2.rawQuery(r3, r5)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r8 = "className"
            java.lang.String r2 = "classId"
            if (r1 == 0) goto L_0x0086
            int r3 = r1.getCount()     // Catch:{ Exception -> 0x00b0 }
            if (r3 <= r4) goto L_0x0086
            org.json.JSONObject r10 = new org.json.JSONObject     // Catch:{ Exception -> 0x00b0 }
            r10.<init>()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r3 = "planName"
            r10.put(r3, r9)     // Catch:{ Exception -> 0x00b0 }
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ Exception -> 0x00b0 }
            r9.<init>()     // Catch:{ Exception -> 0x00b0 }
        L_0x0039:
            boolean r3 = r1.moveToNext()     // Catch:{ Exception -> 0x00b0 }
            if (r3 == 0) goto L_0x005e
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x00b0 }
            r3.<init>()     // Catch:{ Exception -> 0x00b0 }
            int r4 = r1.getColumnIndex(r2)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = r1.getString(r4)     // Catch:{ Exception -> 0x00b0 }
            r3.put(r2, r4)     // Catch:{ Exception -> 0x00b0 }
            int r4 = r1.getColumnIndex(r8)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r4 = r1.getString(r4)     // Catch:{ Exception -> 0x00b0 }
            r3.put(r8, r4)     // Catch:{ Exception -> 0x00b0 }
            r9.put(r3)     // Catch:{ Exception -> 0x00b0 }
            goto L_0x0039
        L_0x005e:
            java.lang.String r8 = "classInfo"
            r10.put(r8, r9)     // Catch:{ Exception -> 0x00b0 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00b0 }
            r8.<init>()     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r9 = "parseClass("
            r8.append(r9)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r9 = r10.toString()     // Catch:{ Exception -> 0x00b0 }
            r8.append(r9)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r9 = ");"
            r8.append(r9)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x00b0 }
            if (r1 == 0) goto L_0x0082
            r1.close()
        L_0x0082:
            r0.close()
            return r8
        L_0x0086:
            if (r1 == 0) goto L_0x00ab
            boolean r3 = r1.moveToFirst()     // Catch:{ Exception -> 0x00b0 }
            if (r3 == 0) goto L_0x00ab
            int r2 = r1.getColumnIndex(r2)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x00b0 }
            int r8 = r1.getColumnIndex(r8)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r8 = r1.getString(r8)     // Catch:{ Exception -> 0x00b0 }
            java.lang.String r8 = r7.parseClassFormulary(r2, r8, r9, r10)     // Catch:{ Exception -> 0x00b0 }
            if (r1 == 0) goto L_0x00a7
            r1.close()
        L_0x00a7:
            r0.close()
            return r8
        L_0x00ab:
            if (r1 == 0) goto L_0x00b9
            goto L_0x00b6
        L_0x00ae:
            r8 = move-exception
            goto L_0x00bf
        L_0x00b0:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x00ae }
            if (r1 == 0) goto L_0x00b9
        L_0x00b6:
            r1.close()
        L_0x00b9:
            r0.close()
            java.lang.String r8 = ""
            return r8
        L_0x00bf:
            if (r1 == 0) goto L_0x00c4
            r1.close()
        L_0x00c4:
            r0.close()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyComparePage.getFormuarlyClass(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public String parseClassFormulary(String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        try {
            jSONObject.put("planName", str3);
            if (str2 == null || str2.equals("")) {
                str2 = getClassName(databaseHelper, str);
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("classId", str);
            jSONObject2.put("className", str2);
            HashMap<String, String> brandMap = getBrandMap(databaseHelper, str);
            JSONArray jSONArray = new JSONArray();
            FormularyDatabaseHelper instance = FormularyDatabaseHelper.getInstance(getApplicationContext());
            if (instance != null) {
                for (String next : brandMap.keySet()) {
                    JSONObject jSONObject3 = new JSONObject();
                    Cursor fetchFormularyInfo = fetchFormularyInfo(instance, next, str4);
                    if (fetchFormularyInfo != null) {
                        if (fetchFormularyInfo.moveToNext()) {
                            jSONObject3.put("brandId", next);
                            jSONObject3.put("brandName", brandMap.get(next));
                            jSONObject3.put("tierName", fetchFormularyInfo.getString(fetchFormularyInfo.getColumnIndex("tierName")));
                            jSONObject3.put("restrictionCode", fetchFormularyInfo.getString(fetchFormularyInfo.getColumnIndex("restrictionCodes")));
                            jSONArray.put(jSONObject3);
                        }
                        fetchFormularyInfo.close();
                    }
                }
                instance.close();
            }
            jSONObject2.put("FormularyInfo", jSONArray);
            jSONObject.put("classInfo", jSONObject2);
            sendBIPing();
            String str5 = "parseClassFormulary(" + jSONObject.toString() + ");";
            databaseHelper.close();
            return str5;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            databaseHelper.close();
            throw th;
        }
        databaseHelper.close();
        return "parseClassFormulary({})";
    }

    public Cursor fetchFormularyInfo(FormularyDatabaseHelper formularyDatabaseHelper, String str, String str2) {
        return formularyDatabaseHelper.getDatabase().rawQuery(FormularyDatabaseHelper.PARSE_FORMULARY_CLASS_QUERY, new String[]{str, str2});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        if (r1 == null) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0024, code lost:
        if (r1 != null) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0026, code lost:
        r1.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getClassName(com.medscape.android.db.DatabaseHelper r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r1 = 0
            android.database.sqlite.SQLiteDatabase r6 = r6.getDatabase()     // Catch:{ Exception -> 0x002c }
            java.lang.String r2 = "Select ClassName from tblClassNames where ClassID = ? AND (ParentID IS NOT NULL OR SingleLevel = 1) LIMIT 1"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x002c }
            r4 = 0
            r3[r4] = r7     // Catch:{ Exception -> 0x002c }
            android.database.Cursor r1 = r6.rawQuery(r2, r3)     // Catch:{ Exception -> 0x002c }
            boolean r6 = r1.moveToNext()     // Catch:{ Exception -> 0x002c }
            if (r6 == 0) goto L_0x0024
            java.lang.String r6 = "ClassName"
            int r6 = r1.getColumnIndex(r6)     // Catch:{ Exception -> 0x002c }
            java.lang.String r6 = r1.getString(r6)     // Catch:{ Exception -> 0x002c }
            r0 = r6
        L_0x0024:
            if (r1 == 0) goto L_0x0033
        L_0x0026:
            r1.close()
            goto L_0x0033
        L_0x002a:
            r6 = move-exception
            goto L_0x0034
        L_0x002c:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0033
            goto L_0x0026
        L_0x0033:
            return r0
        L_0x0034:
            if (r1 == 0) goto L_0x0039
            r1.close()
        L_0x0039:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyComparePage.getClassName(com.medscape.android.db.DatabaseHelper, java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        if (r1 == null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0038, code lost:
        if (r1 != null) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.HashMap<java.lang.String, java.lang.String> getBrandMap(com.medscape.android.db.DatabaseHelper r6, java.lang.String r7) {
        /*
            r5 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r6 = r6.getDatabase()     // Catch:{ Exception -> 0x003d }
            java.lang.String r2 = "select d.UniqueID as drugId,d.DrugName as drugName from tblClassMapping c,tblDrugs d where c.ContentId = d.ContentId AND c.ClassId = ? "
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x003d }
            r4 = 0
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x003d }
            r3[r4] = r7     // Catch:{ Exception -> 0x003d }
            android.database.Cursor r1 = r6.rawQuery(r2, r3)     // Catch:{ Exception -> 0x003d }
        L_0x001a:
            boolean r6 = r1.moveToNext()     // Catch:{ Exception -> 0x003d }
            if (r6 == 0) goto L_0x0038
            java.lang.String r6 = "drugId"
            int r6 = r1.getColumnIndex(r6)     // Catch:{ Exception -> 0x003d }
            java.lang.String r6 = r1.getString(r6)     // Catch:{ Exception -> 0x003d }
            java.lang.String r7 = "drugName"
            int r7 = r1.getColumnIndex(r7)     // Catch:{ Exception -> 0x003d }
            java.lang.String r7 = r1.getString(r7)     // Catch:{ Exception -> 0x003d }
            r0.put(r6, r7)     // Catch:{ Exception -> 0x003d }
            goto L_0x001a
        L_0x0038:
            if (r1 == 0) goto L_0x0046
            goto L_0x0043
        L_0x003b:
            r6 = move-exception
            goto L_0x0047
        L_0x003d:
            r6 = move-exception
            r6.printStackTrace()     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x0046
        L_0x0043:
            r1.close()
        L_0x0046:
            return r0
        L_0x0047:
            if (r1 == 0) goto L_0x004c
            r1.close()
        L_0x004c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyComparePage.getBrandMap(com.medscape.android.db.DatabaseHelper, java.lang.String):java.util.HashMap");
    }

    private class InsideWebViewClient extends WebViewClient {
        private InsideWebViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            WebView access$200 = FormularyComparePage.this.mWebView;
            access$200.loadUrl(InAppMessageWebViewClient.JAVASCRIPT_PREFIX + FormularyComparePage.this.mJavaScriptCall);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            LogUtil.e(FormularyComparePage.TAG, " shouldOverideUrl=%s", str);
            LogUtil.e(FormularyComparePage.TAG, " shouldOverideUrl mBrandId=%s", FormularyComparePage.this.mBrandId);
            Uri parse = Uri.parse(str);
            if (parse.getScheme().toLowerCase().equals("formularyclass")) {
                Intent intent = new Intent(FormularyComparePage.this, FormularyComparePage.class);
                intent.putExtra("TITLE", FormularyComparePage.this.mTitle);
                intent.putExtra(FormularyMyPlanPage.CONTENT_ID, FormularyComparePage.this.mContentId);
                intent.putExtra(FormularyMyPlanPage.PLAN_ID, FormularyComparePage.this.mPlanId);
                intent.putExtra(FormularyMyPlanPage.PLAN_NAME, FormularyComparePage.this.mPlanName);
                intent.putExtra(FormularyMyPlanPage.IS_SINGLE_CLASS, true);
                intent.putExtra(FormularyMyPlanPage.CLASS_ID, parse.getQueryParameter("classId"));
                intent.putExtra(FormularyMyPlanPage.FORMULARY_BRAND_ID_KEY, FormularyComparePage.this.mBrandId);
                FormularyComparePage.this.startActivity(intent);
                return true;
            } else if (!parse.getScheme().toLowerCase().equals("drug")) {
                return false;
            } else {
                String queryParameter = parse.getQueryParameter("brandid");
                String str2 = null;
                try {
                    str2 = URLDecoder.decode(parse.getQueryParameter("brandname"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                int findContentIdFromUniqueId = DrugDataHelper.findContentIdFromUniqueId(FormularyComparePage.this, queryParameter);
                if (!(findContentIdFromUniqueId == -1 || str2 == null)) {
                    DrugMonographMainActivity.startDrugMonoGraphAvtivity(FormularyComparePage.this, findContentIdFromUniqueId, str2);
                }
                return true;
            }
        }
    }
}

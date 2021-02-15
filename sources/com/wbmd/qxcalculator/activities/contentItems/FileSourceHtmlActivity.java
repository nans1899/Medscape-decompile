package com.wbmd.qxcalculator.activities.contentItems;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.tapstream.sdk.http.RequestBuilders;
import com.wbmd.omniture.utils.OmnitureHelper;
import com.wbmd.qxcalculator.QxContentStyle;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import java.util.ArrayList;

public class FileSourceHtmlActivity extends ContentItemActivity implements View.OnTouchListener {
    private static final String APP_READ_PACKAGE_NAME = "com.qxmd.readbyqxmd";
    private static final String KEY_ANALYTICS_NAME = "File Source Page";
    public static final String KEY_INTENT_URL = "FileSourceHtmlActivity.KEY_INTENT_URL";
    public static final String KEY_RAW_HTML = "FileSourceHtmlActivity.KEY_RAW_HTML";
    private static final String KEY_SHOULD_PROMPT_READ_INSTALL = "FileSourceHtmlActivity.KEY_SHOULD_PROMPT_READ_INSTALL";
    /* access modifiers changed from: private */
    public static WebView webView;
    /* access modifiers changed from: private */
    public boolean didJustTap;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private String rawHtml;
    /* access modifiers changed from: private */
    public boolean shouldPromptToInstallReadIfApplicable = true;
    private Handler touchUpHandler;
    private TouchUpRunnable touchUpRunnable;
    private String url;

    /* access modifiers changed from: protected */
    public boolean requiresContentItem() {
        return false;
    }

    private class TouchUpRunnable implements Runnable {
        private TouchUpRunnable() {
        }

        public void run() {
            Log.d("API", "reset tap");
            boolean unused = FileSourceHtmlActivity.this.didJustTap = false;
        }
    }

    public int getStatusBarColor() {
        return getResources().getColor(R.color.status_bar_color_default);
    }

    public int getActionBarColor() {
        return getResources().getColor(R.color.action_bar_color_default);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_file_source_html;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Drawable drawable;
        super.onCreate(bundle);
        this.rawHtml = getIntent().getStringExtra(KEY_RAW_HTML);
        this.contentItem = (ContentItem) getIntent().getParcelableExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM);
        if (this.contentItem == null || this.isMoreInfoSection) {
            this.url = getIntent().getStringExtra(KEY_INTENT_URL);
            setTitle(getString(R.string.app_full_name));
        } else {
            String str = this.contentItem.fileSource.source;
            this.url = str;
            if (str.toLowerCase().startsWith("market://")) {
                startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(this.url)));
                finish();
                return;
            } else if (this.url.toLowerCase().startsWith("http")) {
                this.url = replaceVariableValuesInUrl(this.url);
            } else {
                this.url = "file://" + FileHelper.getInstance().getResourceFolderPathForContentItemIdentifier(this.contentItem.identifier) + this.url;
            }
        }
        Log.d("API", "url " + this.url);
        if (this.url == null && this.rawHtml == null) {
            finish();
            return;
        }
        if (getSupportActionBar() != null && this.isMoreInfoSection) {
            getSupportActionBar().setTitle(R.string.more_info_activity_title);
        }
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        if (Build.VERSION.SDK_INT < 21) {
            drawable = getResources().getDrawable(R.drawable.custom_progress_bar);
        } else {
            drawable = getResources().getDrawable(R.drawable.custom_progress_bar, (Resources.Theme) null);
        }
        this.progressBar.setProgressDrawable(drawable);
        if (!isFinishing()) {
            this.touchUpHandler = new Handler();
            this.touchUpRunnable = new TouchUpRunnable();
            WebView webView2 = (WebView) findViewById(R.id.webview);
            webView = webView2;
            webView2.clearCache(false);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.getSettings().setDomStorageEnabled(true);
            webView.setOnTouchListener(this);
            webView.setWebViewClient(new WebViewClient() {
                /* JADX WARNING: Removed duplicated region for block: B:16:0x00a9  */
                /* JADX WARNING: Removed duplicated region for block: B:21:0x00bf  */
                /* JADX WARNING: Removed duplicated region for block: B:36:0x00bc A[SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public boolean shouldOverrideUrlLoading(android.webkit.WebView r7, java.lang.String r8) {
                    /*
                        r6 = this;
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        boolean r7 = r7.didJustTap
                        r0 = 0
                        r1 = 1
                        if (r7 == 0) goto L_0x00e9
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        boolean unused = r7.didJustTap = r0
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        java.lang.String r2 = r7.replaceHttpWithHttps(r8)
                        r7.tappedLink(r2)
                        java.lang.String r7 = r8.toLowerCase()
                        java.lang.String r2 = "mailto"
                        boolean r7 = r7.startsWith(r2)
                        if (r7 == 0) goto L_0x0062
                        android.net.MailTo r7 = android.net.MailTo.parse(r8)
                        android.content.Intent r8 = new android.content.Intent
                        java.lang.String r2 = "android.intent.action.SEND"
                        r8.<init>(r2)
                        java.lang.String[] r2 = new java.lang.String[r1]
                        java.lang.String r3 = r7.getTo()
                        r2[r0] = r3
                        java.lang.String r0 = "android.intent.extra.EMAIL"
                        r8.putExtra(r0, r2)
                        java.lang.String r0 = r7.getBody()
                        java.lang.String r2 = "android.intent.extra.TEXT"
                        r8.putExtra(r2, r0)
                        java.lang.String r0 = r7.getSubject()
                        java.lang.String r2 = "android.intent.extra.SUBJECT"
                        r8.putExtra(r2, r0)
                        java.lang.String r7 = r7.getCc()
                        java.lang.String r0 = "android.intent.extra.CC"
                        r8.putExtra(r0, r7)
                        java.lang.String r7 = "message/rfc822"
                        r8.setType(r7)
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        r7.startActivity(r8)
                        return r1
                    L_0x0062:
                        java.lang.String r7 = r8.toLowerCase()
                        java.lang.String r2 = "www.qxmd.com/pubmed/"
                        boolean r7 = r7.contains(r2)
                        if (r7 == 0) goto L_0x0078
                        java.lang.String r7 = "(?i)www.qxmd.com/pubmed/"
                        java.lang.String r2 = "readbyqxmd.com/read/"
                        java.lang.String r8 = r8.replaceAll(r7, r2)
                    L_0x0076:
                        r7 = 1
                        goto L_0x0086
                    L_0x0078:
                        java.lang.String r7 = r8.toLowerCase()
                        java.lang.String r2 = "read.qxmd.com"
                        boolean r7 = r7.contains(r2)
                        if (r7 == 0) goto L_0x0085
                        goto L_0x0076
                    L_0x0085:
                        r7 = 0
                    L_0x0086:
                        android.net.Uri r2 = android.net.Uri.parse(r8)
                        android.content.Intent r3 = new android.content.Intent
                        java.lang.String r4 = "android.intent.action.VIEW"
                        r3.<init>(r4, r2)
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r4 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        android.content.pm.PackageManager r4 = r4.getPackageManager()
                        r5 = 65536(0x10000, float:9.18355E-41)
                        java.util.List r3 = r4.queryIntentActivities(r3, r5)
                        java.util.Iterator r3 = r3.iterator()
                    L_0x00a1:
                        boolean r4 = r3.hasNext()
                        java.lang.String r5 = "com.qxmd.readbyqxmd"
                        if (r4 == 0) goto L_0x00bc
                        java.lang.Object r4 = r3.next()
                        android.content.pm.ResolveInfo r4 = (android.content.pm.ResolveInfo) r4
                        android.content.pm.ActivityInfo r4 = r4.activityInfo
                        java.lang.String r4 = r4.packageName
                        boolean r4 = r4.equals(r5)
                        if (r4 == 0) goto L_0x00a1
                        r7 = 1
                        r3 = 1
                        goto L_0x00bd
                    L_0x00bc:
                        r3 = 0
                    L_0x00bd:
                        if (r7 == 0) goto L_0x00e9
                        if (r3 == 0) goto L_0x00db
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        android.content.pm.PackageManager r7 = r7.getPackageManager()
                        android.content.Intent r7 = r7.getLaunchIntentForPackage(r5)
                        if (r7 == 0) goto L_0x00e9
                        java.lang.String r8 = "android.intent.action.CALL"
                        r7.setAction(r8)
                        r7.setData(r2)
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r8 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        r8.startActivity(r7)
                        return r1
                    L_0x00db:
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        boolean r7 = r7.shouldPromptToInstallReadIfApplicable
                        if (r7 == 0) goto L_0x00e9
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        r7.promptToInstallRead(r8)
                        return r1
                    L_0x00e9:
                        java.lang.String r7 = r8.toLowerCase()
                        java.lang.String r2 = ".pdf"
                        boolean r7 = r7.contains(r2)
                        if (r7 == 0) goto L_0x00fb
                        com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.this
                        r7.launchPdfReader(r8)
                        return r1
                    L_0x00fb:
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.AnonymousClass1.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
                }

                public void onPageFinished(WebView webView, String str) {
                    QxContentStyle.getInstance().applyCSSStyle(FileSourceHtmlActivity.webView);
                    super.onPageFinished(webView, str);
                }
            });
            webView.setWebChromeClient(new WebChromeClient() {
                public void onProgressChanged(WebView webView, int i) {
                    if (i < 15) {
                        FileSourceHtmlActivity.this.progressBar.setProgress(15);
                    } else {
                        FileSourceHtmlActivity.this.progressBar.setProgress(i);
                    }
                    if (i == 100) {
                        FileSourceHtmlActivity.this.progressBar.setVisibility(8);
                    } else {
                        FileSourceHtmlActivity.this.progressBar.setVisibility(0);
                    }
                }
            });
            if (bundle != null) {
                this.shouldPromptToInstallReadIfApplicable = bundle.getBoolean(KEY_SHOULD_PROMPT_READ_INSTALL, true);
                webView.restoreState(bundle);
                return;
            }
            String str2 = this.rawHtml;
            if (str2 != null) {
                webView.loadData(str2, "text/html", "UTF-8");
            } else {
                webView.loadUrl(this.url);
            }
        }
    }

    /* access modifiers changed from: private */
    public void promptToInstallRead(String str) {
        final String replaceHttpWithHttps = replaceHttpWithHttps(str);
        new AlertDialog.Builder(this).setTitle(R.string.prompt_install_read_title).setMessage(R.string.prompt_install_read_body).setNegativeButton(R.string.prompt_install_read_not_now, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean unused = FileSourceHtmlActivity.this.shouldPromptToInstallReadIfApplicable = false;
                FileSourceHtmlActivity.webView.loadUrl(replaceHttpWithHttps);
            }
        }).setPositiveButton(R.string.prompt_install_read_yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                FileSourceHtmlActivity.this.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=com.qxmd.readbyqxmd")));
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                boolean unused = FileSourceHtmlActivity.this.shouldPromptToInstallReadIfApplicable = false;
                FileSourceHtmlActivity.webView.loadUrl(replaceHttpWithHttps);
            }
        }).create().show();
    }

    /* access modifiers changed from: private */
    public void tappedLink(String str) {
        String replaceHttpWithHttps = replaceHttpWithHttps(str);
        AnalyticsHandler.getInstance().trackPageView(this, KEY_ANALYTICS_NAME);
        if (this.isMoreInfoSection) {
            AnalyticsHandler.getInstance().trackPageView(this, "More Information Page Link");
            AnalyticsHandler instance = AnalyticsHandler.getInstance();
            instance.trackPageView(this, this.trackerPageName + ": " + replaceHttpWithHttps);
            if (this.contentItem != null && this.contentItem.promotionToUse != null) {
                EventsManager.getInstance().trackLinkClicked(this.contentItem, replaceHttpWithHttps, this.contentItem.promotionToUse);
            }
        } else if (this.contentItem != null) {
            AnalyticsHandler instance2 = AnalyticsHandler.getInstance();
            instance2.trackPageView(this, this.contentItem.name + ": " + replaceHttpWithHttps);
            if (this.contentItem != null && this.contentItem.promotionToUse != null) {
                EventsManager.getInstance().trackLinkClicked(this.contentItem, replaceHttpWithHttps, this.contentItem.promotionToUse);
            }
        } else if (this.trackerPageName != null) {
            AnalyticsHandler instance3 = AnalyticsHandler.getInstance();
            instance3.trackPageView(this, this.trackerPageName + ": " + replaceHttpWithHttps);
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        Log.d("API", "touch up");
        this.didJustTap = true;
        this.touchUpHandler.removeCallbacks(this.touchUpRunnable);
        this.touchUpHandler.postDelayed(this.touchUpRunnable, 500);
        return false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        webView.saveState(bundle);
        bundle.putBoolean(KEY_SHOULD_PROMPT_READ_INSTALL, this.shouldPromptToInstallReadIfApplicable);
    }

    public void onResume() {
        super.onResume();
        webView.onResume();
        if (this.rawHtml != null) {
            return;
        }
        if (this.isMoreInfoSection) {
            AnalyticsHandler.getInstance().trackPageView(this, "More Information Page");
            if (this.trackerPageName != null) {
                AnalyticsHandler.getInstance().trackPageView(this, this.trackerPageName);
            }
            if (this.contentItem != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(getString(R.string.calc));
                arrayList.add(getString(R.string.info));
                arrayList.add(OmnitureHelper.INSTANCE.getCalculatorIdForOmniture(this.contentItem.identifier));
                arrayList.add(OmnitureHelper.INSTANCE.getCalcPageName(this.contentItem.name));
                OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList);
                if (this.contentItem.promotionToUse != null) {
                    EventsManager.getInstance().trackMoreInfoPageView(this.contentItem, this.contentItem.promotionToUse);
                    return;
                }
                return;
            }
            return;
        }
        AnalyticsHandler.getInstance().trackPageView(this, KEY_ANALYTICS_NAME);
        if (this.contentItem != null) {
            if (this.contentItem.fileSource != null && this.contentItem.fileSource.source != null && this.contentItem.fileSource.source.equalsIgnoreCase(getString(R.string.about_html))) {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(getString(R.string.settings));
                arrayList2.add(getString(R.string.about));
                OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList2);
            }
        } else if (this.trackerPageName != null) {
            AnalyticsHandler.getInstance().trackPageView(this, this.trackerPageName);
        }
    }

    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    public void finish() {
        super.finish();
        if (this.isMoreInfoSection) {
            overridePendingTransition(R.anim.close_enter_modal, R.anim.close_exit_modal);
        }
    }

    /* access modifiers changed from: private */
    public void launchPdfReader(String str) {
        Intent intent = new Intent(this, PdfViewerActivity.class);
        intent.putExtra(PdfViewerActivity.KEY_BUNDLE_URL, str);
        if (this.contentItem != null) {
            if (this.contentItem.trackerId != null && !this.contentItem.trackerId.isEmpty()) {
                intent.putExtra(QxMDActivity.KEY_TRACKER_ID, this.contentItem.trackerId);
                intent.putExtra(QxMDActivity.KEY_TRACKER_PAGE_NAME, str);
            }
            if (this.contentItem.promotionToUse != null) {
                EventsManager.getInstance().trackLinkClicked(this.contentItem, str, this.contentItem.promotionToUse);
            }
        } else if (this.trackerId != null) {
            intent.putExtra(QxMDActivity.KEY_TRACKER_ID, this.trackerId);
            intent.putExtra(QxMDActivity.KEY_TRACKER_PAGE_NAME, str);
        }
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        super.onBackPressed();
        return true;
    }

    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|(1:8)|(3:9|10|(1:16))|18|(1:22)|(3:23|24|(1:30))|32|(1:36)|37|38|(1:44)|46|(1:50)|51|52|(1:58)|59|60|(1:66)|(3:67|68|(1:73))|75|(8:77|78|(1:83)|84|85|(1:90)|92|(1:99)(2:95|96))(1:97)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:59:0x015e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:67:0x0184 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:84:0x01d0 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0046 */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01b2 A[SYNTHETIC, Splitter:B:77:0x01b2] */
    /* JADX WARNING: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String replaceVariableValuesInUrl(java.lang.String r15) {
        /*
            r14 = this;
            java.lang.String r0 = "$session_id"
            java.lang.String r1 = "$auth_token"
            java.lang.String r2 = "$email"
            java.lang.String r3 = "$workzip"
            java.lang.String r4 = "$location"
            java.lang.String r5 = "$specialty"
            java.lang.String r6 = "$profession"
            java.lang.String r7 = "$lastname"
            java.lang.String r8 = "$firstname"
            java.lang.String r9 = "%20"
            java.lang.String r10 = "+"
            java.lang.String r11 = "UTF-8"
            com.wbmd.qxcalculator.managers.UserManager r12 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            com.wbmd.qxcalculator.model.db.DBUser r12 = r12.getDbUser()
            boolean r13 = r15.contains(r8)     // Catch:{ UnsupportedEncodingException -> 0x0046 }
            if (r13 == 0) goto L_0x0046
            java.lang.String r13 = r12.getFirstName()     // Catch:{ UnsupportedEncodingException -> 0x0046 }
            if (r13 == 0) goto L_0x0046
            java.lang.String r13 = r12.getFirstName()     // Catch:{ UnsupportedEncodingException -> 0x0046 }
            boolean r13 = r13.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x0046 }
            if (r13 != 0) goto L_0x0046
            java.lang.String r13 = r12.getFirstName()     // Catch:{ UnsupportedEncodingException -> 0x0046 }
            java.lang.String r13 = java.net.URLEncoder.encode(r13, r11)     // Catch:{ UnsupportedEncodingException -> 0x0046 }
            java.lang.String r13 = r13.replace(r10, r9)     // Catch:{ UnsupportedEncodingException -> 0x0046 }
            java.lang.String r15 = r15.replace(r8, r13)     // Catch:{ UnsupportedEncodingException -> 0x0046 }
        L_0x0046:
            boolean r8 = r15.contains(r7)     // Catch:{ UnsupportedEncodingException -> 0x006d }
            if (r8 == 0) goto L_0x006e
            java.lang.String r8 = r12.getLastName()     // Catch:{ UnsupportedEncodingException -> 0x006d }
            if (r8 == 0) goto L_0x006e
            java.lang.String r8 = r12.getLastName()     // Catch:{ UnsupportedEncodingException -> 0x006d }
            boolean r8 = r8.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x006d }
            if (r8 != 0) goto L_0x006e
            java.lang.String r8 = r12.getLastName()     // Catch:{ UnsupportedEncodingException -> 0x006d }
            java.lang.String r8 = java.net.URLEncoder.encode(r8, r11)     // Catch:{ UnsupportedEncodingException -> 0x006d }
            java.lang.String r8 = r8.replace(r10, r9)     // Catch:{ UnsupportedEncodingException -> 0x006d }
            java.lang.String r15 = r15.replace(r7, r8)     // Catch:{ UnsupportedEncodingException -> 0x006d }
            goto L_0x006e
        L_0x006d:
        L_0x006e:
            java.lang.String r7 = "$profession_id"
            boolean r8 = r15.contains(r7)
            if (r8 == 0) goto L_0x008c
            com.wbmd.qxcalculator.model.db.DBProfession r8 = r12.getProfession()
            if (r8 == 0) goto L_0x008c
            com.wbmd.qxcalculator.model.db.DBProfession r8 = r12.getProfession()
            java.lang.Long r8 = r8.getIdentifier()
            java.lang.String r8 = r8.toString()
            java.lang.String r15 = r15.replace(r7, r8)
        L_0x008c:
            boolean r7 = r15.contains(r6)     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            if (r7 == 0) goto L_0x00c0
            com.wbmd.qxcalculator.model.db.DBProfession r7 = r12.getProfession()     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            java.lang.String r7 = r7.getName()     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            if (r7 == 0) goto L_0x00c0
            com.wbmd.qxcalculator.model.db.DBProfession r7 = r12.getProfession()     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            java.lang.String r7 = r7.getName()     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            boolean r7 = r7.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            if (r7 != 0) goto L_0x00c0
            com.wbmd.qxcalculator.model.db.DBProfession r7 = r12.getProfession()     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            java.lang.String r7 = r7.getName()     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            java.lang.String r7 = java.net.URLEncoder.encode(r7, r11)     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            java.lang.String r7 = r7.replace(r10, r9)     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            java.lang.String r15 = r15.replace(r6, r7)     // Catch:{ UnsupportedEncodingException -> 0x00bf }
            goto L_0x00c0
        L_0x00bf:
        L_0x00c0:
            java.lang.String r6 = "$specialty_id"
            boolean r7 = r15.contains(r6)
            if (r7 == 0) goto L_0x00de
            com.wbmd.qxcalculator.model.db.DBSpecialty r7 = r12.getSpecialty()
            if (r7 == 0) goto L_0x00de
            com.wbmd.qxcalculator.model.db.DBSpecialty r7 = r12.getSpecialty()
            java.lang.Long r7 = r7.getIdentifier()
            java.lang.String r7 = r7.toString()
            java.lang.String r15 = r15.replace(r6, r7)
        L_0x00de:
            boolean r6 = r15.contains(r5)     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            if (r6 == 0) goto L_0x0112
            com.wbmd.qxcalculator.model.db.DBSpecialty r6 = r12.getSpecialty()     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            java.lang.String r6 = r6.getName()     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            if (r6 == 0) goto L_0x0112
            com.wbmd.qxcalculator.model.db.DBSpecialty r6 = r12.getSpecialty()     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            java.lang.String r6 = r6.getName()     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            boolean r6 = r6.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            if (r6 != 0) goto L_0x0112
            com.wbmd.qxcalculator.model.db.DBSpecialty r6 = r12.getSpecialty()     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            java.lang.String r6 = r6.getName()     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            java.lang.String r6 = java.net.URLEncoder.encode(r6, r11)     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            java.lang.String r6 = r6.replace(r10, r9)     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            java.lang.String r15 = r15.replace(r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x0111 }
            goto L_0x0112
        L_0x0111:
        L_0x0112:
            java.lang.String r5 = "$location_id"
            boolean r6 = r15.contains(r5)
            if (r6 == 0) goto L_0x0130
            com.wbmd.qxcalculator.model.db.DBLocation r6 = r12.getLocation()
            if (r6 == 0) goto L_0x0130
            com.wbmd.qxcalculator.model.db.DBLocation r6 = r12.getLocation()
            java.lang.Long r6 = r6.getIdentifier()
            java.lang.String r6 = r6.toString()
            java.lang.String r15 = r15.replace(r5, r6)
        L_0x0130:
            boolean r5 = r15.contains(r4)     // Catch:{ UnsupportedEncodingException -> 0x015e }
            if (r5 == 0) goto L_0x015e
            com.wbmd.qxcalculator.model.db.DBLocation r5 = r12.getLocation()     // Catch:{ UnsupportedEncodingException -> 0x015e }
            if (r5 == 0) goto L_0x015e
            com.wbmd.qxcalculator.model.db.DBLocation r5 = r12.getLocation()     // Catch:{ UnsupportedEncodingException -> 0x015e }
            java.lang.String r5 = r5.getName()     // Catch:{ UnsupportedEncodingException -> 0x015e }
            boolean r5 = r5.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x015e }
            if (r5 != 0) goto L_0x015e
            com.wbmd.qxcalculator.model.db.DBLocation r5 = r12.getLocation()     // Catch:{ UnsupportedEncodingException -> 0x015e }
            java.lang.String r5 = r5.getName()     // Catch:{ UnsupportedEncodingException -> 0x015e }
            java.lang.String r5 = java.net.URLEncoder.encode(r5, r11)     // Catch:{ UnsupportedEncodingException -> 0x015e }
            java.lang.String r5 = r5.replace(r10, r9)     // Catch:{ UnsupportedEncodingException -> 0x015e }
            java.lang.String r15 = r15.replace(r4, r5)     // Catch:{ UnsupportedEncodingException -> 0x015e }
        L_0x015e:
            boolean r4 = r15.contains(r3)     // Catch:{ UnsupportedEncodingException -> 0x0184 }
            if (r4 == 0) goto L_0x0184
            java.lang.String r4 = r12.getZipCode()     // Catch:{ UnsupportedEncodingException -> 0x0184 }
            if (r4 == 0) goto L_0x0184
            java.lang.String r4 = r12.getZipCode()     // Catch:{ UnsupportedEncodingException -> 0x0184 }
            boolean r4 = r4.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x0184 }
            if (r4 != 0) goto L_0x0184
            java.lang.String r4 = r12.getZipCode()     // Catch:{ UnsupportedEncodingException -> 0x0184 }
            java.lang.String r4 = java.net.URLEncoder.encode(r4, r11)     // Catch:{ UnsupportedEncodingException -> 0x0184 }
            java.lang.String r4 = r4.replace(r10, r9)     // Catch:{ UnsupportedEncodingException -> 0x0184 }
            java.lang.String r15 = r15.replace(r3, r4)     // Catch:{ UnsupportedEncodingException -> 0x0184 }
        L_0x0184:
            com.wbmd.qxcalculator.managers.UserManager r3 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ UnsupportedEncodingException -> 0x01a7 }
            java.lang.String r3 = r3.getUserEmail()     // Catch:{ UnsupportedEncodingException -> 0x01a7 }
            boolean r4 = r15.contains(r2)     // Catch:{ UnsupportedEncodingException -> 0x01a7 }
            if (r4 == 0) goto L_0x01a8
            if (r3 == 0) goto L_0x01a8
            boolean r4 = r3.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x01a7 }
            if (r4 != 0) goto L_0x01a8
            java.lang.String r3 = java.net.URLEncoder.encode(r3, r11)     // Catch:{ UnsupportedEncodingException -> 0x01a7 }
            java.lang.String r3 = r3.replace(r10, r9)     // Catch:{ UnsupportedEncodingException -> 0x01a7 }
            java.lang.String r15 = r15.replace(r2, r3)     // Catch:{ UnsupportedEncodingException -> 0x01a7 }
            goto L_0x01a8
        L_0x01a7:
        L_0x01a8:
            com.wbmd.qxcalculator.managers.UserManager r2 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            boolean r2 = r2.hasFinishedUpgradingToUniversalAccounts()
            if (r2 == 0) goto L_0x020c
            com.wbmd.qxcalculator.managers.UserManager r2 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ UnsupportedEncodingException -> 0x01d0 }
            java.lang.String r2 = r2.getAuthKey()     // Catch:{ UnsupportedEncodingException -> 0x01d0 }
            boolean r3 = r15.contains(r1)     // Catch:{ UnsupportedEncodingException -> 0x01d0 }
            if (r3 == 0) goto L_0x01d0
            if (r2 == 0) goto L_0x01d0
            boolean r3 = r2.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x01d0 }
            if (r3 != 0) goto L_0x01d0
            java.lang.String r2 = java.net.URLEncoder.encode(r2, r11)     // Catch:{ UnsupportedEncodingException -> 0x01d0 }
            java.lang.String r15 = r15.replace(r1, r2)     // Catch:{ UnsupportedEncodingException -> 0x01d0 }
        L_0x01d0:
            com.wbmd.qxcalculator.managers.UserManager r1 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ UnsupportedEncodingException -> 0x01ef }
            java.lang.String r1 = r1.getSessionId()     // Catch:{ UnsupportedEncodingException -> 0x01ef }
            boolean r2 = r15.contains(r0)     // Catch:{ UnsupportedEncodingException -> 0x01ef }
            if (r2 == 0) goto L_0x01f0
            if (r1 == 0) goto L_0x01f0
            boolean r2 = r1.isEmpty()     // Catch:{ UnsupportedEncodingException -> 0x01ef }
            if (r2 != 0) goto L_0x01f0
            java.lang.String r1 = java.net.URLEncoder.encode(r1, r11)     // Catch:{ UnsupportedEncodingException -> 0x01ef }
            java.lang.String r15 = r15.replace(r0, r1)     // Catch:{ UnsupportedEncodingException -> 0x01ef }
            goto L_0x01f0
        L_0x01ef:
        L_0x01f0:
            com.wbmd.qxcalculator.managers.UserManager r0 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            java.lang.Long r0 = r0.getActiveUserId()
            java.lang.String r1 = "user_id"
            boolean r1 = r15.contains(r1)
            if (r1 == 0) goto L_0x020c
            if (r0 == 0) goto L_0x020c
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "$user_id"
            java.lang.String r15 = r15.replace(r1, r0)
        L_0x020c:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.replaceVariableValuesInUrl(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: private */
    public String replaceHttpWithHttps(String str) {
        return str.replace("http", RequestBuilders.DEFAULT_SCHEME);
    }
}

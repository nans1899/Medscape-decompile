package com.medscape.android.activity.calc;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.PopupMenu;
import androidx.exifinterface.media.ExifInterface;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.actions.SearchIntents;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractContentViewerActvity;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.drugs.AbstractCursorLoader;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.player.VideoPlayer;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.Map;

public class CalcArticleActivity extends AbstractContentViewerActvity implements LoaderManager.LoaderCallbacks<Cursor> {
    protected static final int HIDE_PROGRESS_BAR = 12;
    public static final int LOADER_CALCULATOR_TITLE = 1;
    private static final String LOCAL_BASE_URL = "file://";
    private static final String TAG = "CalcArticleActivity";
    private String baseUrl;
    private CalcArticle crArticle;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what != 12) {
                return true;
            }
            CalcArticleActivity.this.progressBar.setVisibility(8);
            return true;
        }
    });
    /* access modifiers changed from: private */
    public View mCalculatorView;
    /* access modifiers changed from: private */
    public String mCurrentId = "";
    private View mPopupView;
    private String mQueryString;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private String refLink = "";
    private String refModule = "";
    private WebView webView;

    /* access modifiers changed from: protected */
    public String getContentLink() {
        return "";
    }

    /* access modifiers changed from: protected */
    public String getContentTeaserForEmail() {
        return "";
    }

    /* access modifiers changed from: protected */
    public boolean isContentTitleDisplayed() {
        return true;
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        getWindow().setSoftInputMode(3);
        super.onCreate(bundle);
        setContentView((int) R.layout.calculator_webview);
        Util.setCookie(this);
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progress);
        this.progressBar = progressBar2;
        progressBar2.setVisibility(0);
        findViewById(R.id.sectionHeaderTextView).setVisibility(8);
        View findViewById = findViewById(R.id.custom_calculator_keyboard);
        this.mCalculatorView = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.crArticle = (CalcArticle) getIntent().getExtras().get("article");
        this.refModule = getIntent().hasExtra("wapp.mmodule") ? getIntent().getStringExtra("wapp.mmodule") : "";
        if (getIntent().hasExtra("wapp.mlink")) {
            str = getIntent().getStringExtra("wapp.mlink");
        } else {
            str = "";
        }
        this.refLink = str;
        CalcArticle calcArticle = this.crArticle;
        if (calcArticle == null || calcArticle.getTitle() == null || this.crArticle.getTitle().equals("")) {
            setTitle("");
        } else {
            setTitle(Html.fromHtml("<font color=#ffffff>" + this.crArticle.getTitle() + "</font>"));
        }
        String string = getIntent().getExtras().getString(SearchIntents.EXTRA_QUERY);
        if (string != null) {
            this.mQueryString = string;
        }
        this.baseUrl = "";
        WebView webView2 = (WebView) findViewById(R.id.webView);
        this.webView = webView2;
        webView2.getSettings().setUserAgentString(Util.addUserAgent(this.webView, this));
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setBuiltInZoomControls(false);
        this.webView.addJavascriptInterface(new CaclulatorScriptInterface(), "CALCULATOR");
        this.webView.setWebChromeClient(new WebChromeClient() {
            public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
                return super.onJsAlert(webView, str, str2, jsResult);
            }
        });
        loadURL();
        this.webView.setWebViewClient(new InsideWebViewClient());
        try {
            getSupportLoaderManager().initLoader(1, (Bundle) null, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToRecentlyViewed(String str) {
        Bundle bundle = new Bundle(2);
        bundle.putString("type", SearchHelper.TYPE_CALCULATOR);
        bundle.putSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE, this.crArticle);
        RecentlyViewedSuggestionHelper.addToRecentlyViewed(this, str, bundle);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0096 A[Catch:{ Exception -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadURL() {
        /*
            r6 = this;
            java.lang.String r0 = ".html"
            java.lang.String r1 = "/Medscape/"
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x009e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e }
            r3.<init>()     // Catch:{ Exception -> 0x009e }
            com.medscape.android.MedscapeApplication r4 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x009e }
            java.lang.String r4 = com.medscape.android.helper.FileHelper.getDataDirectory(r4)     // Catch:{ Exception -> 0x009e }
            r3.append(r4)     // Catch:{ Exception -> 0x009e }
            r3.append(r1)     // Catch:{ Exception -> 0x009e }
            com.medscape.android.activity.calc.model.CalcArticle r4 = r6.crArticle     // Catch:{ Exception -> 0x009e }
            java.lang.String r4 = r4.getCalcId()     // Catch:{ Exception -> 0x009e }
            r3.append(r4)     // Catch:{ Exception -> 0x009e }
            r3.append(r0)     // Catch:{ Exception -> 0x009e }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x009e }
            r2.<init>(r3)     // Catch:{ Exception -> 0x009e }
            java.lang.String r3 = r6.mQueryString     // Catch:{ Exception -> 0x009e }
            java.lang.String r4 = "file://"
            if (r3 == 0) goto L_0x006a
            java.lang.String r3 = r6.mQueryString     // Catch:{ Exception -> 0x009e }
            java.lang.String r5 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ Exception -> 0x009e }
            if (r3 != 0) goto L_0x006a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e }
            r0.<init>()     // Catch:{ Exception -> 0x009e }
            r0.append(r4)     // Catch:{ Exception -> 0x009e }
            com.medscape.android.MedscapeApplication r3 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x009e }
            java.lang.String r3 = com.medscape.android.helper.FileHelper.getDataDirectory(r3)     // Catch:{ Exception -> 0x009e }
            r0.append(r3)     // Catch:{ Exception -> 0x009e }
            r0.append(r1)     // Catch:{ Exception -> 0x009e }
            com.medscape.android.activity.calc.model.CalcArticle r1 = r6.crArticle     // Catch:{ Exception -> 0x009e }
            java.lang.String r1 = r1.getCalcId()     // Catch:{ Exception -> 0x009e }
            r0.append(r1)     // Catch:{ Exception -> 0x009e }
            java.lang.String r1 = ".html?"
            r0.append(r1)     // Catch:{ Exception -> 0x009e }
            java.lang.String r1 = r6.mQueryString     // Catch:{ Exception -> 0x009e }
            r0.append(r1)     // Catch:{ Exception -> 0x009e }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x009e }
            goto L_0x0090
        L_0x006a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009e }
            r3.<init>()     // Catch:{ Exception -> 0x009e }
            r3.append(r4)     // Catch:{ Exception -> 0x009e }
            com.medscape.android.MedscapeApplication r4 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x009e }
            java.lang.String r4 = com.medscape.android.helper.FileHelper.getDataDirectory(r4)     // Catch:{ Exception -> 0x009e }
            r3.append(r4)     // Catch:{ Exception -> 0x009e }
            r3.append(r1)     // Catch:{ Exception -> 0x009e }
            com.medscape.android.activity.calc.model.CalcArticle r1 = r6.crArticle     // Catch:{ Exception -> 0x009e }
            java.lang.String r1 = r1.getCalcId()     // Catch:{ Exception -> 0x009e }
            r3.append(r1)     // Catch:{ Exception -> 0x009e }
            r3.append(r0)     // Catch:{ Exception -> 0x009e }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x009e }
        L_0x0090:
            boolean r1 = r2.exists()     // Catch:{ Exception -> 0x009e }
            if (r1 == 0) goto L_0x00a2
            android.webkit.WebView r1 = r6.webView     // Catch:{ Exception -> 0x009e }
            r1.loadUrl(r0)     // Catch:{ Exception -> 0x009e }
            r6.baseUrl = r0     // Catch:{ Exception -> 0x009e }
            goto L_0x00a2
        L_0x009e:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.calc.CalcArticleActivity.loadURL():void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        invalidateOptionsMenu();
        if (this.refModule.isEmpty()) {
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "webview-launch", Constants.OMNITURE_MLINK_CALC, (Map<String, Object>) null);
        } else if (this.refLink.isEmpty()) {
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, this.refModule, Constants.OMNITURE_MLINK_CALC, (Map<String, Object>) null);
        } else {
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, this.refModule, this.refLink, (Map<String, Object>) null);
        }
        AppboyEventsHandler.routeDailyEventsToFirebaseOrBraze(this, AppboyConstants.APPBOY_EVENT_CALCULATOR_VIEWED);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            return prepareCalculatorTitleLoader();
        }
        throw new IllegalArgumentException("unknown loader: " + i);
    }

    private CalculatorCursorLoader prepareCalculatorTitleLoader() {
        try {
            return new CalculatorCursorLoader(this, new DatabaseHelper(this).getDatabase(), this.crArticle.getCalcId());
        } catch (Exception e) {
            Log.w(TAG, "onCreateLoader: failed to init DB", e);
            return null;
        }
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (loader.getId() == 1) {
            handleCalculatorTitleLoaded(cursor);
        }
    }

    private void handleCalculatorTitleLoaded(Cursor cursor) {
        if (cursor.moveToFirst()) {
            saveToRecentlyViewed(cursor.getString(0));
            this.crArticle.setTitle(cursor.getString(0));
            setTitle(Html.fromHtml("<font color=#ffffff>" + this.crArticle.getTitle() + "</font>"));
            getSupportLoaderManager().destroyLoader(1);
        }
    }

    class InsideWebViewClient extends WebViewClient {
        InsideWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            LogUtil.d(CalcArticleActivity.TAG, "shouldOverrideUrlLoading url = %s", str);
            if (str.contains("mp4")) {
                Intent intent = new Intent(CalcArticleActivity.this, VideoPlayer.class);
                intent.putExtra("path", str);
                intent.putExtra("articleTitle", CalcArticleActivity.this.getTitle());
                CalcArticleActivity.this.startActivity(intent);
                return true;
            } else if (str.startsWith("tel:")) {
                Uri.parse(str);
                CalcArticleActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (str.equals("back://blank")) {
                CalcArticleActivity.this.finish();
                return true;
            } else {
                Uri parse = Uri.parse(str);
                if (parse.getScheme().toLowerCase().contains("openkeyboard")) {
                    String unused = CalcArticleActivity.this.mCurrentId = parse.getHost();
                    CalcArticleActivity.this.openKeyboard();
                }
                if (parse.getScheme().toLowerCase().equals("showalert")) {
                    String decode = Uri.decode(parse.getQueryParameter("alert"));
                    AlertDialog.Builder builder = new AlertDialog.Builder(CalcArticleActivity.this, R.style.BlackTextDialog);
                    builder.setMessage(decode).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    if (!CalcArticleActivity.this.isFinishing()) {
                        builder.show();
                    }
                }
                if (parse.getScheme().toLowerCase().contains("closekeyboard")) {
                    CalcArticleActivity.this.closeKeyboard();
                }
                return false;
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            CalcArticleActivity.this.progressBar.setVisibility(8);
        }

        public void onPageFinished(WebView webView, String str) {
            CalcArticleActivity.this.h.sendEmptyMessage(12);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            return handleUserUpOrBackNavigation();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void calculatorClick(View view) {
        if (this.webView != null && !this.mCurrentId.equals("")) {
            switch (view.getId()) {
                case R.id.calculator_backspace:
                    if (!this.mCurrentId.equals("")) {
                        executeJavascript("javascript:backspaceField(" + this.mCurrentId + ");");
                        refreshResultButton();
                        return;
                    }
                    return;
                case R.id.calculator_clear_all:
                    executeJavascript("javascript:clearAllInput();");
                    refreshResultButton();
                    return;
                case R.id.calculator_decimal:
                    appendValue(".");
                    return;
                case R.id.calculator_eight:
                    appendValue(UserProfile.PHARMACIST_ID);
                    return;
                case R.id.calculator_five:
                    appendValue(UserProfile.NURSE_PRACTITIONER_ID);
                    return;
                case R.id.calculator_four:
                    appendValue("4");
                    return;
                case R.id.calculator_hide_keyboard:
                    closeKeyboard();
                    return;
                case R.id.calculator_nine:
                    appendValue("9");
                    return;
                case R.id.calculator_one:
                    appendValue(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                    return;
                case R.id.calculator_seven:
                    appendValue("7");
                    return;
                case R.id.calculator_six:
                    appendValue("6");
                    return;
                case R.id.calculator_three:
                    appendValue(ExifInterface.GPS_MEASUREMENT_3D);
                    return;
                case R.id.calculator_two:
                    appendValue(ExifInterface.GPS_MEASUREMENT_2D);
                    return;
                case R.id.calculator_view_result:
                    viewResults();
                    return;
                case R.id.calculator_zero:
                    appendValue(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                    return;
                default:
                    return;
            }
        }
    }

    public void appendValue(String str) {
        if (!this.mCurrentId.equals("")) {
            executeJavascript("javascript:appendToField(" + this.mCurrentId + ",'" + str + "');");
            refreshResultButton();
        }
    }

    public void viewResults() {
        closeKeyboard();
        executeJavascript("javascript:viewResults();");
        refreshResultButton();
    }

    public void onBackPressed() {
        handleUserUpOrBackNavigation();
    }

    private boolean handleUserUpOrBackNavigation() {
        View view = this.mCalculatorView;
        if (view == null || view.getVisibility() != 0) {
            if (this.webView.canGoBack()) {
                this.webView.goBack();
            } else {
                finish();
            }
            return true;
        }
        closeKeyboard();
        this.mCurrentId = "";
        return true;
    }

    public void closeKeyboard() {
        if (this.mCalculatorView.getVisibility() == 0) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.calculator_slide_out_down);
            this.mCalculatorView.setAnimation(loadAnimation);
            this.mCalculatorView.startAnimation(loadAnimation);
            executeJavascript("javascript:keyboardClosed();");
            this.mCalculatorView.setVisibility(8);
        }
    }

    public void refreshResultButton() {
        executeJavascript("javascript:var aAndroidViewResultObj = isResultButtonEnabled(); window.CALCULATOR.refreshButtonEnabled(aAndroidViewResultObj)");
    }

    private void executeJavascript(String str) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.webView.evaluateJavascript(str, (ValueCallback) null);
        } else {
            this.webView.loadUrl(str);
        }
    }

    public void openKeyboard() {
        this.mCalculatorView.post(new Runnable() {
            public void run() {
                if (CalcArticleActivity.this.mCalculatorView.getVisibility() != 0) {
                    Animation loadAnimation = AnimationUtils.loadAnimation(CalcArticleActivity.this, R.anim.calculator_slide_in);
                    CalcArticleActivity.this.mCalculatorView.setAnimation(loadAnimation);
                    CalcArticleActivity.this.mCalculatorView.startAnimation(loadAnimation);
                    CalcArticleActivity.this.mCalculatorView.setVisibility(0);
                }
                CalcArticleActivity.this.refreshResultButton();
            }
        });
    }

    class CaclulatorScriptInterface {
        CaclulatorScriptInterface() {
        }

        @JavascriptInterface
        public void refreshButtonEnabled(final boolean z) {
            CalcArticleActivity.this.mCalculatorView.post(new Runnable() {
                public void run() {
                    Button button = (Button) CalcArticleActivity.this.mCalculatorView.findViewById(R.id.calculator_view_result);
                    if (z) {
                        button.setTextColor(CalcArticleActivity.this.getResources().getColor(17170443));
                    } else {
                        button.setTextColor(CalcArticleActivity.this.getResources().getColor(17170432));
                    }
                    LogUtil.d("Test", "isEnabled = %s", z + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    button.setEnabled(z);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public boolean isContentSaved() {
        Cursor query;
        if (this.crArticle != null) {
            String maskedGuid = AuthenticationManager.getInstance(this).getMaskedGuid();
            if (StringUtil.isNotEmpty(maskedGuid) && (query = getContentResolver().query(CalcArticle.CalcArticles.CONTENT_URI, (String[]) null, "calcId=? AND (userGuid='' OR userGuid=?)", new String[]{String.valueOf(this.crArticle.getCalcId()), maskedGuid}, (String) null)) != null && query.moveToFirst()) {
                query.close();
                return true;
            }
        }
        return false;
    }

    public void saveContent() {
        if (this.crArticle != null) {
            if (isContentSaved()) {
                removeInfo(this.crArticle);
                OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
            } else if (saveInfo().booleanValue()) {
                if (!isFinishing()) {
                    Toast.makeText(this, getResources().getString(R.string.calculator_saved), 0).show();
                }
                AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_CALC_SAVED, this);
                MedscapeMenu.sendSaveBIPings(this, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MLINK_CALC);
            }
        }
        invalidateOptionsMenu();
    }

    private void removeInfo(CalcArticle calcArticle) {
        try {
            getContentResolver().delete(CalcArticle.CalcArticles.CONTENT_URI, "calcId like ? ", new String[]{String.valueOf(calcArticle.getCalcId().trim())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Boolean saveInfo() {
        try {
            String maskedGuid = AuthenticationManager.getInstance(this).getMaskedGuid();
            if (StringUtil.isNotEmpty(maskedGuid)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("isSaved", 1);
                contentValues.put("title", this.crArticle.getTitle());
                contentValues.put("type", Integer.valueOf(this.crArticle.getType()));
                contentValues.put("userGuid", maskedGuid);
                contentValues.put("calcId", this.crArticle.getCalcId());
                Uri insert = getContentResolver().insert(CalcArticle.CalcArticles.CONTENT_URI, contentValues);
                LogUtil.e(TAG, "Save crArticle.setTitle = %s", this.crArticle.getTitle());
                if (insert != null) {
                    LogUtil.e(TAG, "Save Article  urp = %s", insert.toString());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(isContentSaved() ? R.menu.content_page_save_full : R.menu.content_page_save_empty, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        setShareOptionVisibiity(menu);
        return true;
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
        }
    }

    /* access modifiers changed from: protected */
    public int getMenuItemsLayout() {
        return isContentSaved() ? R.menu.content_save_toggle : R.menu.content_save;
    }

    /* access modifiers changed from: protected */
    public String getContentTitle() {
        return this.crArticle.getTitle();
    }

    private static class CalculatorCursorLoader extends AbstractCursorLoader {
        static final String QUERY_CALCULATOR_TITLE_FOR_ID = "SELECT Title FROM tblCalcTitles WHERE CalcID = ?";
        private final String calcId;
        private final SQLiteDatabase db;

        CalculatorCursorLoader(Context context, SQLiteDatabase sQLiteDatabase, String str) {
            super(context);
            this.db = sQLiteDatabase;
            this.calcId = str;
        }

        /* access modifiers changed from: protected */
        public Cursor buildCursor() {
            return this.db.rawQuery(QUERY_CALCULATOR_TITLE_FOR_ID, new String[]{this.calcId});
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 82) {
            return super.onKeyUp(i, keyEvent);
        }
        if (this.mPopupView == null) {
            this.mPopupView = new View(this);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
            if (linearLayout != null) {
                linearLayout.addView(this.mPopupView);
            }
        }
        PopupMenu popupMenu = new PopupMenu(this, this.mPopupView);
        popupMenu.setOnMenuItemClickListener(new AbstractContentViewerActvity.SharePopupMenuItemListener());
        popupMenu.getMenuInflater().inflate(isContentSaved() ? R.menu.content_page_save_full : R.menu.content_page_save_empty, popupMenu.getMenu());
        setShareOptionVisibiity(popupMenu.getMenu());
        popupMenu.show();
        return true;
    }

    private void setShareOptionVisibiity(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.action_share);
        if (findItem != null) {
            findItem.setVisible(false);
        }
    }
}

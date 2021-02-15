package com.medscape.android.activity.install;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.reference.ClinicalReferenceInstallationRequestActivity;
import com.medscape.android.updater.OnUpdateListener;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.updater.UpdateUrls;
import com.medscape.android.updater.model.Update;
import com.medscape.android.updater.model.UpdateProcess;
import com.medscape.android.util.ConnectivityUtils;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.wbmd.wbmdcommons.utils.Extensions;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DrugInstallationActivity extends BaseActivity implements OnUpdateListener, ViewSwitcher.ViewFactory {
    private static final int MSG_SET_UPDATE_PLIST = 2;
    private static final int SET_MAX_PROGRESS = 3;
    private static final int SET_PROGRESS_LOADING_COUNT_MESSAGE = 5;
    private static final int SET_PROGRESS_MESSAGE = 4;
    private static final int START_COMPLETION_ACITIVITY = 11;
    protected static final int START_PROGRESS = 1;
    private static final String TAG = "DrugInstallationActivity";
    /* access modifiers changed from: private */
    public static int loadingCount = 1;
    private ConnectivityUtils _connectivityUtils = new ConnectivityUtils();
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                DrugInstallationActivity.this.progressbar.setProgress(Integer.parseInt(message.obj.toString()));
            } else if (message.what == 2) {
                DrugInstallationActivity.this.setUpdateTextview(message.obj);
            } else if (message.what == 3) {
                DrugInstallationActivity.this.progressbar.setProgress(0);
                DrugInstallationActivity.this.progressbar.setMax(Integer.parseInt(message.obj.toString()));
            } else if (message.what == 4) {
                DrugInstallationActivity.this.loadingTextView.setText(message.obj.toString());
            } else if (message.what == 5) {
                String obj = message.obj.toString();
                if (StringUtil.isNotEmpty(obj)) {
                    int unused = DrugInstallationActivity.loadingCount = Math.min(DrugInstallationActivity.loadingCount + 1, Integer.parseInt(obj));
                    TextSwitcher access$400 = DrugInstallationActivity.this.loadingCountTextView;
                    access$400.setText("Step " + DrugInstallationActivity.loadingCount + " of " + obj);
                }
            } else if (message.what == 11) {
                if (!DrugInstallationActivity.this.isFinishing()) {
                    DrugInstallationActivity.this.showDialog(11);
                }
            } else if (message.what == 15) {
                if (!DrugInstallationActivity.this.isFinishing()) {
                    DrugInstallationActivity.this.showDialog(15);
                }
            } else if (message.what == 26 && !DrugInstallationActivity.this.isFinishing()) {
                DrugInstallationActivity.this.showDialog(26);
            }
            return true;
        }
    });
    private String headerText;
    /* access modifiers changed from: private */
    public TextSwitcher loadingCountTextView;
    /* access modifiers changed from: private */
    public TextSwitcher loadingTextView;
    private String mTotalListSize = "";
    private UpdateManager mUpdateManager;
    private String pListText;
    /* access modifiers changed from: private */
    public ProgressBar progressbar;
    private List<Update> updateList;

    public void onCreate(Bundle bundle) {
        List<Update> list;
        super.onCreate(bundle);
        setContentView((int) R.layout.drug_installtion_layout);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.DownloadProgressBar);
        this.progressbar = progressBar;
        progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_bg));
        Animation loadAnimation = AnimationUtils.loadAnimation(this, 17432576);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(this, 17432577);
        TextSwitcher textSwitcher = (TextSwitcher) findViewById(R.id.loadingCountTextView);
        this.loadingCountTextView = textSwitcher;
        textSwitcher.setFactory(this);
        this.loadingCountTextView.setInAnimation(loadAnimation);
        this.loadingCountTextView.setOutAnimation(loadAnimation2);
        this.loadingCountTextView.setText("");
        TextSwitcher textSwitcher2 = (TextSwitcher) findViewById(R.id.loadingTextView);
        this.loadingTextView = textSwitcher2;
        textSwitcher2.setFactory(this);
        this.loadingTextView.setInAnimation(loadAnimation);
        this.loadingTextView.setOutAnimation(loadAnimation2);
        this.loadingTextView.setText(getResources().getString(R.string.loading_text));
        this._connectivityUtils.createLock(this);
        this._connectivityUtils.acquireLock();
        MedscapeApplication.get().listenForUpdates(this);
        this.mUpdateManager = MedscapeApplication.get().getUpdateManager();
        setMarketingScreen();
        if (!Settings.singleton(this).getSetting(Constants.PREF_DRUG_INSTALLTION_FAIL, AppEventsConstants.EVENT_PARAM_VALUE_NO).equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) || Settings.singleton(this).getSetting(Constants.PREF_DRUG_INSTALLTION_PLIST_TEXT, "").equals("")) {
            this.mUpdateManager.isVersionCheck = false;
            UpdateManager updateManager = this.mUpdateManager;
            updateManager.getUpdatePList2(1, UpdateUrls.getUrlForEnvironment(updateManager.getEnvironment(), UpdateUrls.UPDATEPLIST_URL));
            return;
        }
        setUpdateTextview(Settings.singleton(this).getSetting(Constants.PREF_DRUG_INSTALLTION_PLIST_TEXT, ""));
        loadUpdateList();
        String setting = Settings.singleton(this).getSetting(Constants.PREF_DRUG_INSTALLTION_FAIL_VERSION, "");
        if (setting.equals("") || (list = this.updateList) == null || list.size() <= 0) {
            this.mUpdateManager.isVersionCheck = false;
            UpdateManager updateManager2 = this.mUpdateManager;
            updateManager2.getUpdatePList2(1, UpdateUrls.getUrlForEnvironment(updateManager2.getEnvironment(), UpdateUrls.UPDATEPLIST_URL));
            return;
        }
        this.mUpdateManager.mServerVersion = (double) Float.parseFloat(setting);
        loadingCount = 0;
        int parseInt = Integer.parseInt(Settings.singleton(this).getSetting(Constants.PREF_DRUG_INSTALLTION_TOTAL_FILES, AppEventsConstants.EVENT_PARAM_VALUE_NO));
        if (parseInt != 0) {
            loadingCount = parseInt - this.updateList.size();
            this.mTotalListSize = String.valueOf(parseInt);
        } else {
            this.mTotalListSize = String.valueOf(this.updateList.size());
        }
        Message message = new Message();
        message.obj = this.mTotalListSize;
        message.what = 5;
        this.h.sendMessage(message);
        this.mUpdateManager.downloadAndInstallUpdate(1, this.updateList.get(0), Constants.DIRECTORY_MAIN, true);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    private void loadUpdateList() {
        this.updateList = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(Settings.singleton(this).getSetting(Constants.PREF_DRUG_INSTALLTION_URL_LIST, ""), "|");
        while (stringTokenizer.hasMoreElements()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken != null && !nextToken.equals("")) {
                Update update = new Update();
                update.setUrl(nextToken);
                this.updateList.add(update);
            }
        }
    }

    public void onUpdateAvailable(int i, List<Update> list, int i2) {
        if (Util.isSDCardAvailable()) {
            this.updateList = list;
            Update update = list.get(0);
            if (update.getUrl() != null && !update.getUrl().equals("")) {
                loadingCount = 0;
                Message message = new Message();
                this.mTotalListSize = String.valueOf(list.size());
                Settings singleton = Settings.singleton(this);
                singleton.saveSetting(Constants.PREF_DRUG_INSTALLTION_TOTAL_FILES, "" + list.size());
                message.obj = Integer.valueOf(list.size());
                message.what = 5;
                this.h.sendMessage(message);
                this.mUpdateManager.downloadAndInstallUpdate(1, list.get(0), Constants.DIRECTORY_MAIN, false);
            } else if (!update.getType().equalsIgnoreCase("Upgrade App")) {
                Message message2 = new Message();
                Settings.singleton(this).saveSetting(Constants.PREF_DRUG_INSTALLTION_PLIST_TEXT, update.getType());
                message2.what = 2;
                message2.obj = update.getType();
                this.h.sendMessage(message2);
                if (this.updateList.size() > 1) {
                    Update update2 = this.updateList.get(1);
                    if (update2.getUrl() == null || update2.getUrl().equals("")) {
                        this.mUpdateManager.getDrugPList(update2.getType());
                    }
                }
            }
        } else if (!isFinishing()) {
            showDialog(9);
        }
    }

    public void onUpdateComplete(int i, Update update) {
        if (this.updateList.size() > 0) {
            this.updateList.remove(0);
        }
        if (this.updateList.size() > 0) {
            if (this.updateList.get(0).getType() == null || !this.updateList.get(0).getType().equals("purgeSystem")) {
                this.loadingTextView.setText("Downloading...");
                this.mUpdateManager.downloadAndInstallUpdate(1, this.updateList.get(0), Constants.DIRECTORY_MAIN, false);
            } else {
                this.loadingTextView.setText("Executing...");
                this.mUpdateManager.markAllUpdatesCompleted(1);
                showCompletedScreen();
            }
            Handler handler = this.h;
            handler.sendMessage(Message.obtain(handler, 5, this.mTotalListSize));
        }
    }

    private void showCompletedScreen() {
        startActivity(new Intent(this, ClinicalReferenceInstallationRequestActivity.class));
        finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 11 && i2 == -1) {
            setResult(i2);
            finish();
        }
    }

    public void onUpdateNotAvailable(int i) {
        Settings.singleton(this).saveSetting(Constants.PREF_UPDATE_COMPLETE, "YES");
        setResult(0);
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i != 4 && super.onKeyDown(i, keyEvent);
    }

    public void onProgressUpdate(int i) {
        Message message = new Message();
        message.what = 1;
        message.obj = String.valueOf(i);
        this.h.sendMessage(message);
    }

    public void setProgressMessage(String str) {
        Message message = new Message();
        message.what = 4;
        message.obj = String.valueOf(str);
        this.h.sendMessage(message);
    }

    public void setMaxProgress(int i) {
        Message message = new Message();
        message.what = 3;
        message.obj = String.valueOf(i);
        this.h.sendMessage(message);
    }

    private void setMarketingScreen() {
        UpdateProcess currentUpdateProcess;
        UpdateProcess.Data data;
        String marketingUrl;
        WebView webView = (WebView) findViewById(R.id.installMarketingScreenWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        UpdateManager updateManager = this.mUpdateManager;
        if (updateManager != null && (currentUpdateProcess = updateManager.getCurrentUpdateProcess()) != null && (data = currentUpdateProcess.getData()) != null && (marketingUrl = data.getMarketingUrl()) != null) {
            Util.getApplicationVersion(this);
            String str = marketingUrl + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(marketingUrl);
            webView.loadUrl(str + com.wbmd.wbmdcommons.utils.Util.addBasicQueryParams(str));
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    return false;
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setUpdateTextview(Object obj) {
        int indexOf;
        String obj2 = obj.toString();
        if (!Extensions.IsNullOrEmpty(obj2) && (indexOf = obj2.indexOf(10)) > 0) {
            String substring = obj2.substring(0, indexOf);
            this.headerText = substring;
            if (!Extensions.IsNullOrEmpty(substring)) {
                this.pListText = obj2.substring(this.headerText.length() + 1);
            }
        }
    }

    public View makeView() {
        TextView textView = new TextView(this);
        textView.setGravity(49);
        textView.setTextSize(14.0f);
        return textView;
    }

    public void onNetworkError(int i) {
        if (i == 9) {
            saveDownloadUrl();
            this.h.sendEmptyMessage(11);
        } else if (i == 8) {
            this.h.sendEmptyMessage(15);
        } else if (i == 1) {
            this.h.sendEmptyMessage(11);
        } else if (i == 10) {
            this.h.sendEmptyMessage(26);
        }
    }

    private void saveDownloadUrl() {
        List<Update> list;
        if (!Settings.singleton(this).getSetting(Constants.PREF_DRUG_INSTALLTION_PLIST_TEXT, "").equals("") && (list = this.updateList) != null && list.size() > 0) {
            Settings.singleton(this).saveSetting(Constants.PREF_DRUG_INSTALLTION_FAIL, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            StringBuilder sb = new StringBuilder();
            for (Update next : this.updateList) {
                if (next.getUrl() != null && !next.getUrl().equals("")) {
                    sb.append(next.getUrl());
                    sb.append("|");
                }
            }
            Settings.singleton(this).saveSetting(Constants.PREF_DRUG_INSTALLTION_URL_LIST, sb.toString());
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i == 9) {
            return DialogUtil.showAlertDialog(9, (String) null, getResources().getString(R.string.alert_dialog_sdcard_required_message), this);
        }
        if (i == 11) {
            return DialogUtil.showAlertDialog(25, (String) null, getResources().getString(R.string.alert_dialog_drug_download_network_connection_error_message), this);
        }
        if (i == 15) {
            return DialogUtil.showAlertDialog(15, (String) null, "Not able to complete download at this time.", this);
        }
        if (i != 26) {
            return null;
        }
        return DialogUtil.showAlertDialog(26, (String) null, "Not able to complete download at this time.", this);
    }

    public void setUpdateList(List<Update> list) {
        this.updateList = list;
    }

    public List<Update> getUpdateList() {
        return this.updateList;
    }

    public void retryReferencePlistDownload() {
        if (!Util.isOnline(this)) {
            this.h.sendEmptyMessage(11);
        } else if (this.updateList.size() > 1) {
            Update update = this.updateList.get(1);
            if (update.getUrl() == null || update.getUrl().equals("")) {
                this.mUpdateManager.getDrugPList(update.getType());
            }
        }
    }

    public void retryDownload() {
        if (!Util.isOnline(this)) {
            this.h.sendEmptyMessage(11);
        } else {
            this.mUpdateManager.downloadAndInstallUpdate(1, getUpdateList().get(0), Constants.DIRECTORY_MAIN, true);
        }
    }

    public void retryDownloadGetContentURL() {
        if (!Util.isOnline(this)) {
            this.h.sendEmptyMessage(11);
            return;
        }
        Update update = getUpdateList().get(1);
        update.setUrl(update.getType());
        this.mUpdateManager.downloadAndInstallUpdate(1, update, Constants.DIRECTORY_MAIN, true);
    }
}
